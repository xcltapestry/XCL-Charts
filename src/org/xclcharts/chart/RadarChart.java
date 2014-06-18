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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XChart;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.axis.DataAxis;
import org.xclcharts.renderer.axis.DataAxisRender;
import org.xclcharts.renderer.axis.LabelsAxis;
import org.xclcharts.renderer.axis.LabelsAxisRender;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.Pair;


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
	
	private Paint mPaintWebLines = null;
	private Paint mPaintTick = null;
	
	//初始偏移角度
	protected int mOffsetAgent = 0;//180;
		
	//用于计算的辅助类
	protected MathHelper mCalc = new MathHelper();
	
	//依次存下每个圈上，每个标签节点的X,Y坐标
	private Float[][] arrayLabelX = null;
	private Float[][] arrayLabelY= null;
	
	//依次存下每个圈上，每个标签节点归属的角度及总偏移解雇 
	private Float[][] arrayAgent= null;
	private Float[][] arrayOffsetAgent = null;
	
	
	
	int dataCount= 8;
	int labelsCount = 5;
	
	//主数据轴
	//private DataAxis dataAxis = new DataAxisRender();
	
	//数据轴
		protected DataAxisRender dataAxis  = null;
		//标签轴
		protected LabelsAxisRender labelsAxis  = null;	
		
	//数据源
	protected List<LineData> mDataSet;
	
	
	public RadarChart()
	{
		super();
		initChart();
	}
	
	
	private void initChart()
	{
		
		dataAxis = new DataAxisRender();
		labelsAxis = new LabelsAxisRender();
		
		
		//标签显示位置
		//mLablesDP = XEnum.DisplayPostion.CENTER;
		
		mPaintLabels = new Paint();
		mPaintLabels.setColor(Color.RED);
		mPaintLabels.setTextSize(18);
		mPaintLabels.setAntiAlias(true);
		mPaintLabels.setStyle(Style.STROKE);
		mPaintLabels.setTextAlign(Align.CENTER);
		
		mPaintTick = new Paint();
		mPaintTick.setColor(Color.BLUE);
		mPaintTick.setTextSize(18);
		mPaintTick.setAntiAlias(true);
		mPaintTick.setStyle(Style.STROKE);
		mPaintTick.setTextAlign(Align.RIGHT);
		
		
		
		mPaintWebLines = new Paint();
		mPaintWebLines.setColor(Color.BLACK);
		mPaintWebLines.setAntiAlias(true);
		mPaintWebLines.setStyle(Style.STROKE);
		mPaintWebLines.setStrokeWidth(3);
		
		mPaintWebLines.setTextSize(22);
		
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
	 * 返回数据轴
	 * @return 数据轴
	 */
	public DataAxis getDataAxis()
	{
		return dataAxis;
	}
	
	/**
	 * 返回标签轴
	 * @return 标签轴
	 */
	public LabelsAxis getLabelsAxis()
	{
		return labelsAxis;
	}
	
	
	/**
	 * 标签轴的数据源
	 * 
	 * @param labels
	 *            标签集
	 */
	public void setLabels(List<String> labels) {
		labelsAxis.setDataBuilding(labels);
	}

	/**
	 * 设置数据轴的数据源
	 * 
	 * @param dataSeries
	 *            数据源
	 */
	public void setDataSource(List<LineData> dataSeries) {
		this.mDataSet = dataSeries;
	}

	/**
	 * 返回图的数据源
	 * @return 数据源
	 */
	public List<LineData> getDataSource() {
		return mDataSet;
	}
	
	
	private void checkedParm()
	{
		//if( avgAgent > 270) return false;
		//if( avgAgent <=0 ) return false;
		//if(labelsCount < 3) return false;
		//if(dataCount < 3) return false;
	}
	
	/**
	 * 用来绘制蜘蛛网线
	 */
	private void renderWebLines()
	{				
		Path lnPath = new Path();		
		for(int i=0; i <dataCount ;i++)
		{			
			for(int j=0;j < labelsCount ;j++)
			{
				if(0 == j)
				{
					lnPath.moveTo( arrayLabelX[i][j], arrayLabelY[i][j]);
				}else{
					lnPath.lineTo( arrayLabelX[i][j], arrayLabelY[i][j]);
				}
			}
			lnPath.close();
			mCanvas.drawPath(lnPath, mPaintWebLines);
			lnPath.reset();
		}
	}
	
	
	private void renderAxisLines()
	{
				
		float cirX = plotArea.getCenterX();
		float cirY = plotArea.getCenterY();
		
		for(int i=0; i <dataCount ;i++)
		{			
			
			/*	
			//用于绘制各个方向上的轴线
			mCanvas.drawLine(cirX,cirY, arrayLabelX[i][labelsCount-1], 
										arrayLabelY[i][labelsCount-1], mPaintWebLines);
			//绘制最外围的标签
			mCanvas.drawText(Integer.toString(i)+" - "+Integer.toString(labelsCount-1),
							 arrayLabelX[i][labelsCount-1], 
							 arrayLabelY[i][labelsCount-1], mPaintLabels);
						
			//绘制主轴的刻度线与标签,即显示在第一轴线上,那个圆心角为270度的那根线.
			//DataAxis 
			mCanvas.drawText("["+Integer.toString(i)+"]",
					 			arrayLabelX[i][0], arrayLabelY[i][0], mPaintTick);
			
			*/
			
			
			for(int j=0;j < labelsCount ;j++)
			{				
			
				
				//绘制最外围的标签
				if(i == dataCount - 1  )
				{
					//用于绘制各个方向上的轴线
					mCanvas.drawLine(cirX,cirY, arrayLabelX[i][j], 
												arrayLabelY[i][j], mPaintWebLines);
					
					
					mCanvas.drawText(Integer.toString(i)+" - "+Integer.toString(j),
							 arrayLabelX[i][j], arrayLabelY[i][j], mPaintLabels);
				}
				
				//绘制主轴的刻度线与标签
				if(0 == j){ //显示在第一轴线上(即270度的那根线)
					
					//DataAxis 
					mCanvas.drawLine(arrayLabelX[i][j] - 10, 
							arrayLabelY[i][j], arrayLabelX[i][j], 
									arrayLabelY[i][j], mPaintLabels);
					
					mCanvas.drawText("["+Integer.toString(i)+"]",
							 arrayLabelX[i][j]- 15, arrayLabelY[i][j], mPaintTick);
				}
			      
			} //end for labels		
			
			
			
		} //end for data
	}
	
	private void GetAllXY()
	{				
		float cirX = plotArea.getCenterX();
		float cirY = plotArea.getCenterY();
		
		//偏移角度
		//float pAngleOffset = 270f;
				
		//扇形角度,依标签个数决定 				
		float pAngle = 360 / labelsCount ; //   72f; 
		
		//270为中轴线所处圆心角
		float initOffsetAgent = 270f - pAngle;
		
		//依标签总个数算出环数,依数据刻度数决定
		float avgRadius = mRadius / dataCount;
		
		//当前半径
		float curRadius = 0.0f;
		//当前圆心角偏移量
		float offsetAgent = 0.0f;
		
		arrayLabelX=new Float[dataCount][labelsCount]; 
		arrayLabelY=new Float[dataCount][labelsCount]; 
				
		for(int i=0; i <dataCount ;i++)
		{
			curRadius = avgRadius * i; 	////当前半径长度，依此算出各节点坐标	
			Float[] arrX = new Float[labelsCount];
			Float[] arrY = new Float[labelsCount];
			
			for(int j=0;j < labelsCount ;j++)
			{				
				offsetAgent = initOffsetAgent +   pAngle * j;
				
				//计算百分比标签
		        mCalc.CalcArcEndPointXY(cirX,cirY,curRadius, offsetAgent + pAngle); 				    
		    
		        arrayLabelX[i][j] = mCalc.getPosX();
		        arrayLabelY[i][j] = mCalc.getPosY();				        
		        
		       // arrayOffsetAgent[i][j] = offsetAgent;
		    	//arrayAgent[i][j] = pAngle;
			} //end for labelCount					
		} //end for datacount		
				
	}
	
	private void renderWebArea()
	{
		
		//curRadius = 值占轴的比例

        //arrayOffsetAgent[i][j] = offsetAgent;
    	//arrayAgent[i][j] = pAngle;
		
		//计算百分比标签
        //mCalc.CalcArcEndPointXY(cirX,cirY,curRadius, offsetAgent + pAngle); 
    
        //得出点，用数组存下，
		// 再依次连接起来即可
		
		// dataAxis.getAxisMax() - dataAxis.getAxisMin()
		this.mCanvas.drawText("aaaaaaaa", 200, 300, mPaintLabels);
			
		for(LineData lineData : mDataSet)
		{
			
			//画各自的网
			List<Double> dataset =  lineData.getLinePoint();
			for(Double data : dataset){
			   Double dataPrc = (data - dataAxis.getAxisMin() ) / dataAxis.getAxisRange();
			   Double currRadius = mRadius * dataPrc;
			   //calc xy
			}
			
		}
        
	}
	
	
	private void renderGridLines()
	{
		mRadius = 300;
	
			GetAllXY();
			renderWebLines();
			renderAxisLines();
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
		//renderGridLines();
		
		GetAllXY();
		renderWebLines();
		renderAxisLines();
		renderWebArea();
		renderKey();
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
			// plotGrid.render();			
			 
			//绘制标题
			renderTitle();
			
			//绘制图表
			renderPlot();
			 
			
		}catch( Exception e){
			 throw e;
		}
		return true;
	}		
	

}
