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
 * @version 1.0
 */

package org.xclcharts.chart;

import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.plot.PlotAttrInfo;
import org.xclcharts.renderer.plot.PlotAttrInfoRender;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Paint.Align;

/**
 * @ClassName DountChart
 * @Description  环形图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public class DountChart  extends PieChart{	

	//内环半径
	private int mFillRadius = 0;	
	private float mInnerRadius = 0.8f;
	
	//内环填充颜色
	private Paint mPaintFill = null;
	
	private Paint mPaintCenterText = null;
	private String mCenterText = "";
	
	//附加信息类
	private PlotAttrInfoRender plotAttrInfoRender = null;
	
	public DountChart()
	{		
		initChart();
	}
	
	private void initChart()
	{
		int fillColor = Color.BLACK;
		
		if(null != plotArea)
			fillColor =  plotArea.getBackgroundPaint().getColor();
		
		if(null == mPaintFill)
		{
			mPaintFill = new Paint();
			mPaintFill.setColor(fillColor); 
			mPaintFill.setAntiAlias(true);
		}
		
		if(null == plotAttrInfoRender)
			plotAttrInfoRender = new PlotAttrInfoRender();
			
		this.setLabelStyle(XEnum.SliceLabelStyle.OUTSIDE);
	}
	
	private void initCenterTextPaint()
	{
		if(null == mPaintCenterText)
		{
			mPaintCenterText = new Paint();
			mPaintCenterText.setAntiAlias(true);
			mPaintCenterText.setTextSize(28);
			mPaintCenterText.setTextAlign(Align.CENTER);
		}
	}
	
	/**
	 * 附加信息绘制处理类
	 * @return 信息基类
	 */
	public PlotAttrInfo getPlotAttrInfo()
	{
		return plotAttrInfoRender; 
	}
	
	
	/**
	 * 环内部填充画笔
	 * @return 画笔
	 */
	public Paint getInnerPaint()
	{
		return mPaintFill;
	}
		
	/**
	 * 设置环内部填充相对于环所占的比例
	 * @param precentage 环所占比例
	 */
	public void setInnerRadius(float precentage)
	{
		mInnerRadius = precentage;
	}
	
	/**
	 * 计算出环内部填充圆的半径
	 * @return 环的半径
	 */
	public float calcInnerRadius()
	{
		mFillRadius = (int) MathHelper.getInstance().round( mul(getRadius(),mInnerRadius), 2);
		return mFillRadius;
	}
	
	/**
	 * 开放绘制中心文字的画笔 
	 * @return 画笔 
	 */
	public Paint getCenterTextPaint()
	{
		initCenterTextPaint();
		return mPaintCenterText;
	}
	
	/**
	 * 设置中心点文字
	 * @param text 文字
	 */
	public void setCenterText(String text)
	{
		mCenterText = text;
	}
			
	/**
	 * 绘制中心点
	 * @param canvas 画布
	 */
	private void renderCenterText(Canvas canvas)
	{		
		if(mCenterText.length() > 0 )
		{			
			canvas.drawText(mCenterText, 
				plotArea.getCenterX(), plotArea.getCenterY(), getCenterTextPaint());
		}
	}

	@Override
	protected void renderLabelInside(Canvas canvas,String text,float itemAngle,
			 float cirX,float cirY,float radius,float calcAngle)
	{
		//显示在扇形的中心
		float calcRadius = mFillRadius + (radius - mFillRadius) /2 ;
		
		//计算百分比标签
		PointF point = MathHelper.getInstance().calcArcEndPointXY(
						cirX, cirY, calcRadius, calcAngle); 						 
		//标识
		DrawHelper.getInstance().drawRotateText(text, point.x, point.y, itemAngle, 
				canvas, getLabelPaint());
	}
	
	protected void renderInnderCircle(Canvas canvas)
	{
		//中心点坐标
		 float cirX = plotArea.getCenterX();
	     float cirY = plotArea.getCenterY();	     	    
	     canvas.drawCircle(cirX, cirY, mFillRadius, mPaintFill);	 
	     
	     //边框线
	     if(null != mPaintArcBorder)
	     {
	    	 canvas.drawCircle(cirX, cirY, mFillRadius, mPaintArcBorder);	 
	     }
	}
	
	protected void renderDount(Canvas canvas)
	{
		 //内部
		 renderInnderCircle(canvas);  
		 
		  //绘制附加信息
		  plotAttrInfoRender.renderAttrInfo(canvas, plotArea.getCenterX(), 
				  				plotArea.getCenterY(), this.getRadius());
	  
		 //中心文本	
	     renderCenterText(canvas);
	}
		
	/**
	 * 绘制图 -- 环形图的标签处理待改进 ***
	 */
	@Override
	protected boolean renderPlot(Canvas canvas)
	{
		 calcInnerRadius();
		 
		 super.renderPlot(canvas);
		 
		 renderDount(canvas);
		
		 /*
		 //内部
		 renderInnderCircle(canvas);  
		 
		  //绘制附加信息
		  plotAttrInfoRender.renderAttrInfo(canvas, plotArea.getCenterX(), 
				  				plotArea.getCenterY(), this.getRadius());
	  
		 //中心文本	
	     renderCenterText(canvas);
	     */
	     return true;
	}	

}
