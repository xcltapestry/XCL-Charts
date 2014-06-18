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
package org.xclcharts.renderer;


import android.graphics.Canvas;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.renderer.axis.DataAxis;
import org.xclcharts.renderer.axis.DataAxisRender;
import org.xclcharts.renderer.axis.LabelsAxis;
import org.xclcharts.renderer.axis.LabelsAxisRender;
import org.xclcharts.renderer.plot.Legend;
import org.xclcharts.renderer.plot.LegendRender;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * @ClassName AxisChart
 * @Description 所有用到坐标类的图表的基类,主要用于定义和绘制坐标轴
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */

public class AxisChart extends XChart {
		
	//数据轴
	protected DataAxisRender dataAxis  = null;
	//标签轴
	protected LabelsAxisRender labelsAxis  = null;	
	//图例类
	protected LegendRender legend = null;
	
	//数据集的说明描述与图这间的空白间距
	private float mDataSetKeyDescMargin  = 10f;	
	//数据集的说明描述画笔
	private Paint mDataSetKeyDescPaint = null;
		
	//是否显示Key
	private boolean mPlotDataKeyVisible = false;
	
	public AxisChart() {
		// TODO Auto-generated constructor stub		
		super();
		initChart();		
	}
	
	
	/**
	 * 初始化设置
	 */
	private void initChart()
	{		
		//为坐标系设置一个默认间距
		//this.setPaddingPercent(15f, 15f, 20f, 5f);
		
		//数据轴
		dataAxis  = new DataAxisRender();
		//标签轴
		labelsAxis  = new LabelsAxisRender();				
		//图例
		legend = new LegendRender();
		
		mDataSetKeyDescPaint = new Paint();
		mDataSetKeyDescPaint.setColor(Color.BLACK);		
	}
	
	/**
	 * 设置是否需绘制图的key
	 * @param visible 是否显示
	 */
	public void setPlotKeyVisible(boolean visible)
	{
		mPlotDataKeyVisible = visible;
	}
	
	/**
	 * 是否需绘制图的key
	 * @return 是否显示
	 */
	public boolean getPlotKeyVisible()
	{
		return mPlotDataKeyVisible;
	}
	 
	 /**
	  * 开放Key绘制画笔
	  * @return 画笔
	  */
	 public Paint getPlotDataSetKeyPaint()
	 {		 
		 return mDataSetKeyDescPaint;
	 }
	 
	 /**
	  * 设置Key间距
	  * @param margin Key间距
	  */
	 public void setPlotDataSetKeyMargin(float margin)
	 {		 
		 mDataSetKeyDescMargin = margin;
	 }
	 
	 /**
	  * 返回Key间距
	  * @return Key间距
	  */
	 public float getPlotDataSetKeyMargin()
	 {
		 return mDataSetKeyDescMargin;
	 }


	 /**
	  * 开放数据轴绘制类
	  * @return 数据轴绘制类
	  */
	public DataAxis getDataAxis() {
		return dataAxis;
	}

	/**
	 * 开放标签轴绘制类
	 * @return 标签轴绘制类
	 */
	public LabelsAxis getLabelsAxis() {
		return labelsAxis;
	}

	/**
	 * 开放图例绘制类
	 * @return 图例绘制类
	 */
	public Legend getLegend() {
		return legend;
	}
	
	
	
	/**
	 * 计算主图表区范围内,这个还不完善, 如3D底座厚度...., 
	 *  本意是依标题之类，灵活计算高度，但发现在多图表混合时，不太好用.
	 */
	@Override
	protected void calcPlotRange()
	{				
		super.calcPlotRange();
		
		//图的内边距属性
		float perLeft = getPaddingPercentLeft();
		float perRight = getPaddingPercentRight();
		//float perTop = mPaddingPercentTop;
		float perBottom =  getPaddingPercentBottom();	
		
		float width = getChartWidth();
		float height = getChartHeight();		

		// 要依长宽比，区分横竖屏间的比例 [应当依长宽比设置对应的横竖比]
		if(width > height) //横屏
		{
			float scrPer = height / width;			
			//perTop += scrPer;
			perBottom += scrPer;
			perLeft -= scrPer;
			perRight -= scrPer;		
		}			
		
		DrawHelper dw = new DrawHelper();

		if(perLeft > 0)
		{
			float rederLeft = Math.round( height / 100 * perLeft);		
			if(this.getLegend().getLeftLegend().length() > 0)
			{	
				float legendLength = dw.getPaintFontHeight(getLegend().getLeftLegendPaint());
				if(legendLength > rederLeft) rederLeft = legendLength;
			}
			plotArea.setPlotLeft( getChartLeft() + rederLeft);
		}
		
		if(perRight > 0 )
		{
			float rederRight =  Math.round( width / 100 * perRight);
			if(this.getLegend().getRightLegend().length() > 0)
			{	
				float legendLength = dw.getPaintFontHeight(getLegend().getRightLegendPaint());	
				if(legendLength > rederRight ) rederRight = legendLength;
			}
			plotArea.setPlotRight(this.getChartRight() - rederRight);	
		}
		
		if(perBottom > 0 )
		{
			float rederBottom = Math.round( height / 100 * perBottom );
			if(this.getLegend().getLowerLegend().length() > 0)
			{			
				float legendHeight = dw.getPaintFontHeight(getLegend().getLowerLegendPaint());	
				if(legendHeight > rederBottom ) rederBottom = legendHeight;
			}
			plotArea.setPlotBottom(this.getChartBottom() - rederBottom);	
		}
	}
	
	
	protected float getAxisScreenWidth()
	{
		return(Math.abs(plotArea.getPlotRight() - plotArea.getPlotLeft()));
	}
	
	protected float getAxisScreenHeight()
	{
		return( Math.abs(plotArea.getPlotBottom() - plotArea.getPlotTop()));
	}
	

	public boolean render(Canvas canvas) throws Exception {
		// TODO Auto-generated method stub
	
		try {
		
			super.render(canvas);
			//计算主图表区范围
			 calcPlotRange();
			//画Plot Area背景			
			 plotArea.render(canvas);
			//画奇偶行填充,横竖网格线			
			 plotGrid.render(canvas);
			//画图例Legend

			//绘制标题
			renderTitle(canvas);
			//绘制图例
			legend.setRange(this);
			legend.render(canvas);
			
			
		}catch( Exception e){
			 throw e;
		}
		return true;
	}		
	 
	

}
