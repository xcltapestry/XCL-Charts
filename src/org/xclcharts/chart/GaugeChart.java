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

import android.graphics.Canvas;
import org.xclcharts.renderer.CirChart;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.Log;
import android.util.Pair;

/**
 * @ClassName GaugeChart
 * @Description  刻度盘基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */

public class GaugeChart extends CirChart{
	
	/////////////////////////////////////////////
	//刻度步长
	private double mTickSteps = 10d;
	//标签
	private List<String> mLabels = null;
	
	//刻度
	private Paint mPaintTick = null;
	
	//指针
	private float mPointerAgent = 20f;		
	private Paint mPaintPointerLine = null;
	private Paint mPaintPinterCircle  = null;
	
	//分区填充色(比如绿，黄，红),以使区域更清楚
	private Paint mPaintPartitionFill = null;
	//环
	private Paint mPaintDount = null;

	//分区填充数据源(角色(0-180)，颜色)
	private List<Pair> mPartitionDataset = null;
	
	//180度
	private static final int mStartAgent = 180;
	

	public GaugeChart()
	{
		super();	
		initPaint();
	}
	
	private void initPaint()
	{
		getLabelsPaint().setTextSize(18);
		getLabelsPaint().setColor(Color.BLUE);
		
		mPaintTick = new Paint();
		mPaintTick.setStyle(Style.FILL);
		mPaintTick.setAntiAlias(true);	
		mPaintTick.setColor( (int)Color.rgb(50, 149, 222) ); 
		mPaintTick.setStrokeWidth(1);
		
		mPaintPointerLine = new Paint();
		mPaintPointerLine.setStyle(Style.FILL);
		mPaintPointerLine.setAntiAlias(true);	
		mPaintPointerLine.setColor(Color.BLACK);
		mPaintPointerLine.setStrokeWidth(8);
		
		mPaintPinterCircle = new Paint();
		mPaintPinterCircle.setStyle(Style.FILL);
		mPaintPinterCircle.setAntiAlias(true);	
		mPaintPinterCircle.setColor(Color.BLACK);
		mPaintPinterCircle.setStrokeWidth(8);
		
		mPaintPartitionFill = new Paint();
		mPaintPartitionFill.setStyle(Style.FILL);
		mPaintPartitionFill.setAntiAlias(true);	
		
		mPaintDount = new Paint();		
		mPaintDount.setStyle(Style.STROKE);
		mPaintDount.setColor((int)Color.rgb(50, 149, 222));
		mPaintDount.setAntiAlias(true);
		mPaintDount.setStrokeWidth(2);
		
	}
	
	/**
	 * 开放刻度画笔
	 * @return 画笔
	 */
	public Paint getTickPaint()
	{
		return mPaintTick;
	}
	
	/**
	 * 开放指针画笔
	 * @return 画笔
	 */
	public Paint getPinterCirclePaint()
	{
		return mPaintPinterCircle;
	}
	
	/**
	 * 开放指针底部圆画笔
	 * @return 画笔
	 */
	public Paint getPointerLinePaint()
	{
		return mPaintPointerLine;
	}

	/**
	 * 开放用来绘制颜色分区的画笔
	 * @return 画笔
	 */
	public Paint getPartitionFillPaint()
	{
		return mPaintPartitionFill;
	}
	
	/**
	 * 开放用来绘制外围环的画笔
	 * @return 画笔
	 */
	public Paint getDountPaint()
	{
	
		return mPaintDount;
	}
	
	/**
	 * 绘制饼图中显示所占比例 的扇区
	 * @param paintArc	画笔
	 * @param cirX	x坐标
	 * @param cirY	y坐标
	 * @param radius	半径
	 * @param offsetAgent	偏移角度
	 * @param curretAgent	当前角度
	 * @throws Exception	例外
	 */
	protected void drawPercent(Canvas canvas, Paint paintArc,
			final float cirX,
			final float cirY,
			final float radius,
			final float offsetAgent,
			final float curretAgent) throws Exception
	{
		try{		
			float arcLeft = cirX - radius;  
	        float arcTop  = cirY - radius ;  
	        float arcRight = cirX + radius ;  
	        float arcBottom = cirY + radius ;  
	        RectF arcRF0 = new RectF(arcLeft ,arcTop,arcRight,arcBottom);    
			
			//在饼图中显示所占比例  
			canvas.drawArc(arcRF0, offsetAgent, curretAgent, true, paintArc);
		  
		
		}catch( Exception e){
			throw e;
		}
	}	
			
	/**
	 * 设置刻度步长
	 * @param step 步长
	 */
	public void setTickSteps(double step)
	{
		mTickSteps = step;
	}		
	
	/**
	 * 设置标签集合,即显示在最外圈的那个文字标签。(标签和步长分开，步长即刻度可以密点，标签可以松点)
	 * @param labels 标签集合
	 */
	public void setLabels(List<String> labels)
	{
		mLabels = labels;
	}
	
	
	/**
	 * 设置分区填充数据集，[角度(0-mStartAgent)，颜色]，用以决定绘制多少个颜色区分，其所显示大小是多少.
	 * @param dataSet 角度,颜色
	 */
	public void setPartition(List<Pair> dataSet)
	{
		mPartitionDataset = dataSet;
	}	
	

	/**
	 * 设置指针指向的角度(0-180).
	 * @param agent 角度
	 */
	public void setCurrentAgent(float agent)
	{
		mPointerAgent = agent;
	}
	
	private void renderLabels(Canvas canvas)
	{		
		float stepsAgent = Math.round(180/(mLabels.size() - 1 ));
		float calcRadius = this.getRadius()  + getRadius()/10;		
				 
		float cirX = plotArea.getCenterX();
		float cirY = plotArea.getCenterY();
		getLabelsPaint().setTextAlign(Align.CENTER);	
		int i = 0;
		for(String label : mLabels)
		{							
			if(0 == i) //开头
			{			
				canvas.drawText(label,
						cirX - calcRadius, cirY ,this.getLabelsPaint());   			
			}else if(i == mLabels.size() -1 ){ //结尾
                canvas.drawText(label,
						cirX + calcRadius, cirY ,this.getLabelsPaint());   				
			}else{				
				//计算百分比标签
				mCalc.CalcArcEndPointXY(cirX, cirY, calcRadius, 180 + i *stepsAgent); 
				//标识
                canvas.drawText(label,
					 mCalc.getPosX(), mCalc.getPosY() ,this.getLabelsPaint());   
				
			}
			i++;
		}
	}
	
	/**
	 * 绘制刻度
	 */
	private void renderTick(Canvas canvas)
	{
		//步长角度
		double stepsAgent = Math.round(180/mTickSteps);		
		float cirX = plotArea.getCenterX();
		float cirY = plotArea.getCenterY();
		float tickRadius = Math.round(this.getRadius() * 0.9);
				
		for(int i=0;i<mTickSteps;i++)
		{
			if(0 == i)continue;			
			float agent =  (float) (180 + i *stepsAgent) ;				
			mCalc.CalcArcEndPointXY(cirX, cirY, getRadius(), agent); 			
			
			float startX = mCalc.getPosX();
			float startY = mCalc.getPosY();
			mCalc.CalcArcEndPointXY(cirX, cirY,tickRadius, agent); 		
			
			canvas.drawLine(startX, startY, mCalc.getPosX(), mCalc.getPosY(), mPaintTick);
		}
	}	
	
	/**
	 * 绘制指针
	 */
	private void renderPointerLine(Canvas canvas)
	{		
		if(mPointerAgent > 180) //爆表了 
		{
			Log.e("ERROR-GaugeChart","爆表了 !!!");
		}else if(mPointerAgent < 0){
			Log.e("ERROR-GaugeChart","负角度???!!!");
		}else{
			float currentRadius = Math.round(this.getRadius() * 0.9);
			float calcAgent =  Math.round( mPointerAgent + mStartAgent );
			float cirX = plotArea.getCenterX();
			float cirY = plotArea.getCenterY();
					
			mCalc.CalcArcEndPointXY(cirX, cirY, currentRadius, calcAgent);
            canvas.drawLine(cirX, cirY, mCalc.getPosX(), mCalc.getPosY(), mPaintPointerLine);
		}		
	}
	
	/**
	 * 绘制指针底部的圆
	 */
	private void renderPinterCircle(Canvas canvas)
	{
		float cirX = plotArea.getCenterX();
		float cirY = plotArea.getCenterY();
		canvas.drawCircle(cirX, cirY, Math.round(this.getRadius() * 0.05), mPaintPinterCircle);
	}
	
		
	/**
	 * 绘制内部颜色分区填充
	 * @throws Exception
	 */
	private void renderPartitionFill(Canvas canvas) throws Exception
	{		
		Integer totalAgent = 0;		
		 float newRadius = Math.round(getRadius() * 0.8);
						 
	     RectF rect =new RectF();
	     rect.left  = plotArea.getCenterX() - newRadius;
	     rect.top   = plotArea.getCenterY() - newRadius;
	     rect.right = plotArea.getCenterX() + newRadius;
	     rect.bottom= plotArea.getCenterY() + newRadius;  
	     
		 for(Pair pr : mPartitionDataset)
		 {			
			 Integer agentValue = (Integer) pr.first;					 
			 if(agentValue < 0){
					Log.e("ERROR","负角度???!!!");
			 }else if((totalAgent + agentValue) > 180)
		     {
		    	 Log.e("ERROR","输入的角度总计大于mStartAgent度");
		    	 return ;
		     }			 			 
			 mPaintPartitionFill.setColor((Integer) pr.second);				 
			 canvas.drawArc(rect, totalAgent + 180, agentValue, true, mPaintPartitionFill);
		     totalAgent += agentValue;
		 }
				 
	}

	/**
	 * 绘制环
	 * @throws Exception
	 */
	private void renderDount(Canvas canvas) throws Exception
	{		
		 drawPercent(canvas, mPaintDount,plotArea.getCenterX(),plotArea.getCenterY(),getRadius(),180, 180);
	}
	
	/**
	 * 绘制图
	 */
	protected void renderPlot(Canvas canvas)
	{
		try{	
			
			 //外环
			 renderDount(canvas);
		     //依角度画好刻度线
			// 计算出坐标点,从圆心到点间画线
		     renderTick(canvas);
			//画上用于标识分区的扇区
			 renderPartitionFill(canvas) ;
			 //画上外围标签
			 renderLabels(canvas);
			//最后再画指针
			 renderPointerLine(canvas);
			 //画上指针尾部的白色圆心
			 renderPinterCircle(canvas);
			
				
		}catch( Exception e){
			Log.e("ERROR",e.toString());
		}
		
	}
	
			
	public boolean render(Canvas canvas) throws Exception {
		// TODO Auto-generated method stub
	
		try {
			super.render(canvas);
			//绘制图表
			renderPlot(canvas);
			
		}catch( Exception e){
			 throw e;
		}
		return true;
	}
}
