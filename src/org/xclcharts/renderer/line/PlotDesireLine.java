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

package org.xclcharts.renderer.line;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.Log;

import org.xclcharts.chart.DesireLineData;
import org.xclcharts.renderer.axis.DataAxisRender;
import org.xclcharts.renderer.plot.PlotAreaRender;


/**
 * @ClassName PlotDesireLine
 * @Description  处理期望线(分界线)的绘制
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */

public class PlotDesireLine {
	
	private static final String TAG = "PlotDesireLine";
	
	//期望线画笔
	private Paint mPaintDesireLine = null;
	//期望线集合
	private List<DesireLineData> mDesireLineDataset;
			
	private DataAxisRender mDataAxis = null;
	private PlotAreaRender mPlotArea = null;
	private float mAxisScreenHeight = 0.0f;
	private float mAxisScreenWidth = 0.0f;
			
	public PlotDesireLine()
	{
		init();
	}
	
	private void init()
	{
		mPaintDesireLine = new Paint();
		mPaintDesireLine.setAntiAlias(true);
		mPaintDesireLine.setStrokeWidth(3);
		mPaintDesireLine.setTextSize(18);
		mPaintDesireLine.setTextAlign(Align.LEFT);
	}
	

	public void setVerticalPlot(DataAxisRender dataAxis,PlotAreaRender plotArea,float axisScreenHeight)
	{
		setDataAxis(dataAxis);
		setPlotArea(plotArea);
		setAxisScreenHeight(axisScreenHeight);		
	}
	
	public void setHorizontalPlot(DataAxisRender dataAxis,PlotAreaRender plotArea,float axisScreenWidth)
	{
		setDataAxis(dataAxis);
		setPlotArea(plotArea);
		setAxisScreenWidth(axisScreenWidth);		
	}
	
	
	private boolean validateParams()
	{
		if(null == mDataAxis)
		{
			Log.e(TAG, "数据轴基类没有传过来。");
			return false;
		}
		if(null == mPlotArea)
		{
			Log.e(TAG, "绘图区基类没有传过来。");
			return false;
		}
		
		//数据集没有传过来
		if(null == mDesireLineDataset)return false;
	
		return true;
	}
	
	
	/**
	 * 用来画竖向柱形图，横向的期望线
	 * @param canvas 画布
	 * @return 是否成功
	 */
	public boolean renderVerticalDesirelinesDataAxis(Canvas canvas) {
		
		if(!validateParams())return false;
		if(0.0f == mAxisScreenHeight)
		{
			Log.e(TAG, "轴的屏幕高度值没有传过来。");
			return false;
		}
		
		double axisHeight = mDataAxis.getAxisMax() - mDataAxis.getAxisMin();
		
		for(DesireLineData line : mDesireLineDataset)
		{			
			getDesireLinePaint().setColor(line.getColor());
			getDesireLinePaint().setStrokeWidth(line.getLineStroke());
			
			double  postion = mAxisScreenHeight * ( 
								(line.getDesireValue() - mDataAxis.getAxisMin()) /axisHeight  );
			
			float currentY = (float) (mPlotArea.getBottom() - postion); 
			
			canvas.drawLine(mPlotArea.getLeft(), currentY,
							mPlotArea.getRight(), currentY, this.getDesireLinePaint());
			
			if(line.getLabel().length()  > 0)
				canvas.drawText(line.getLabel(), mPlotArea.getRight(), currentY, getDesireLinePaint());
		}
		return true;
	}
	

	/**
	 * 用来画横向柱形图，竖向的期望线
	 * @param canvas 画布
	 * @return 是否成功
	 */
	public boolean renderHorizontalDesirelinesDataAxis(Canvas canvas) {
		
		if(!validateParams())return false;
		if(0.0f == mAxisScreenWidth)
		{
			Log.e(TAG, "轴的屏幕宽度值没有传过来。");
			return false;
		}
		
		double axisHeight = mDataAxis.getAxisMax() - mDataAxis.getAxisMin();
		
		for(DesireLineData line : mDesireLineDataset)
		{			
			getDesireLinePaint().setColor(line.getColor());
			getDesireLinePaint().setStrokeWidth(line.getLineStroke());
			
			double  postion = mAxisScreenWidth * ( 
					(line.getDesireValue() - mDataAxis.getAxisMin()) /axisHeight  );
			
			float currentX = (float) (mPlotArea.getLeft() + postion); 
			
			canvas.drawLine(currentX, mPlotArea.getBottom(),
									currentX, mPlotArea.getTop(), this.getDesireLinePaint());
			
			if(line.getLabel().length()  > 0)
				canvas.drawText(line.getLabel(), currentX, mPlotArea.getTop(), getDesireLinePaint());
		}		
		return true;
	}
	
	
  
	/**
	 * 开放期望线画笔
	 * @return 画笔
	 */
	public Paint getDesireLinePaint()
	{
		return mPaintDesireLine;
	}
	
	/**
	 * 设置期望线值
	 * @param desireLineDataSet 期望线数据集合
	 */
	public void setDesireLines(List<DesireLineData> desireLineDataSet)
	{
		mDesireLineDataset = desireLineDataSet;
	}
	
	
	/**
	 * 设置当前处理的数据轴
	 * @param dataAxis 数据轴
	 */
	public void setDataAxis(DataAxisRender dataAxis)
	{
		mDataAxis = dataAxis;
	}
	
	/**
	 * 设置绘图区
	 * @param plotArea 绘图区
	 */
	public void setPlotArea(PlotAreaRender plotArea)
	{
		mPlotArea = plotArea;
	}
	
	/**
	 * 设置轴的屏幕高度值
	 * @param height 高度
	 */
	public void setAxisScreenHeight(float height)
	{
		mAxisScreenHeight = height;
	}
	
	/**
	 * 设置轴的屏幕宽度值
	 * @param width 宽度
	 */
	public void setAxisScreenWidth(float width)
	{
		mAxisScreenWidth = width;
	}
	

	
	
}
