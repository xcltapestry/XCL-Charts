/**
 * Copyright 2014  XCL-Charts
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 	
 * @Project XCL-Charts 
 * @Description Android图表基类库
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version v0.1
 */
package org.xclcharts.chart;

import java.util.List;

import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XChart;
import org.xclcharts.renderer.XEnum;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;


/**
 * @ClassName RadarChart
 * @Description  雷达图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */
public class RadarChart extends XChart{
	
	//半径
	private float mRadius=0.0f;		
	
	//标签注释显示位置 [隐藏,Default,Center,Ouside,Line]
	//private XEnum.DisplayPostion mLablesDP;
	
	//开放标签画笔让用户设置
	private Paint mPaintLabels = null;
	
	//初始偏移角度
	protected int mOffsetAgent = 0;//180;
		
	//用于计算的辅助类
	protected MathHelper mCalc = new MathHelper();
	
	public RadarChart()
	{
		super();
		initChart();
	}
	
	
	private void initChart()
	{
		//标签显示位置
		//mLablesDP = XEnum.DisplayPostion.CENTER;
		
		mPaintLabels = new Paint();
		mPaintLabels.setColor(Color.BLACK);
		mPaintLabels.setTextSize(18);
		mPaintLabels.setAntiAlias(true);
	}
	
	@Override
	protected void calcPlotRange()
	{
		super.calcPlotRange();
		
		if(isVerticalScreen())
		{
			this.mRadius = this.plotArea.getPlotWidth() / 2;
		}else{
			this.mRadius =  this.plotArea.getPlotHeight() / 2;
		}
	}

	
	/**
	 * 设置饼图(pie chart)的半径
	 * @param radius 饼图的半径
	 */
	public void setRadius(final float radius)
	{
		mRadius = radius;
	}
	
	/**
	 * 返回半径
	 * @return 半径
	 */
	public float getRadius()
	{
		return mRadius;
	}
	
	/**
	 * 设置饼图(pie chart)起始偏移角度
	 * @param agent 偏移角度
	 */
	public void setInitialAngle(final int agent)
	{
		mOffsetAgent = agent;
	}
	

	/**
	 * 返回图的起始偏移角度
	 * @return 偏移角度
	 */
	public int getInitialAngle()
	{
		return mOffsetAgent;
	}

	/**
	 * 设置标签显示在扇区的哪个位置(里面，外面，隐藏)
	 * @param dp 显示位置
	 */
	public void setLablesDisplay(XEnum.DisplayPostion dp)
	{
		//mLablesDP = dp;
	}
	
	/**
	 * 开放标签画笔
	 * @return 画笔
	 */
	public Paint getLabelsPaint()
	{
		return mPaintLabels;
	}
	
	/**
	 * 设置图表的数据源
	 * @param piedata 来源数据集
	 */
	/*
	public void setDataSource(List<PieData> piedata)
	{
		this.mDataSet = piedata;
	}
	*/
	
	/**
	 * 返回数据轴的数据源
	 * @return 数据源
	 */
	/*
	public List<PieData> getDataSource()
	{
		return mDataSet;
	}
	*/
	
	
	private void renderGridLines()
	{
		mRadius = 50;
		int DuCount = 5; //labels.size(); //总环数
		
		float cirX = plotArea.getCenterX();
		float cirY = plotArea.getCenterY();
		
		
		float arcLeft = cirX - mRadius;
		float arcTop  = cirY - mRadius ;
		float arcRight = cirX + mRadius ;
		float arcBottom = cirY + mRadius ;
		RectF arcRF0 = new RectF(arcLeft ,arcTop,arcRight,arcBottom);
		
		
		//扇形角度 				
			float pAngle = 72f; 
			//偏移角度
			float pAngleOffset = 18f + 36f; 
			
			//分成五个环
			float avgRadius = mRadius / DuCount;
			
					
			for(int i=DuCount;i>=0;i--)
			{
				float curRadius = avgRadius * i;
				this.mCanvas.drawCircle(cirX,cirY,curRadius,mPaintLabels);
				if(i == 5)
				{  
					//最外环扇区	
					mCanvas.drawArc(arcRF0, pAngleOffset + pAngle * (DuCount -1) ,pAngle, true,mPaintLabels); 
				}			
			}		
				
	}
	
	
	/**
	 * 绘制key
	 */
	protected void renderKey()
	{
		
	}
	
	/**
	 * 绘制图
	 */
	protected void renderPlot()
	{
		renderGridLines();
	}
	
	public boolean render() throws Exception {
		// TODO Auto-generated method stub
	
		try {
		
			super.render();
			//计算主图表区范围
			 calcPlotRange();
			//画Plot Area背景			
			 plotArea.render();			 
			//画奇偶行填充,横竖网格线			
			 plotGrid.render();			
			 
			
			 
			//绘制标题
			renderTitle();
			
			 renderPlot();
			 
			
		}catch( Exception e){
			 throw e;
		}
		return true;
	}		
	

}
