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
 * @version 2.1
 */
package org.xclcharts.chart;

import java.util.Collections;
import java.util.List;

import org.xclcharts.common.DrawHelper;
import org.xclcharts.renderer.EventChart;
import org.xclcharts.renderer.XEnum;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Paint.Align;
import android.util.Log;

/**
 * @ClassName FunnelChart
 * @Description  漏斗图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public class FunnelChart extends EventChart {
	
	private  static final String TAG = "FunnelChart";
	
	private List<FunnelData> mDataSet;
	private XEnum.SortType mSortType = XEnum.SortType.DESC;
		
	//图的初始宽度
	private float mPlotWidthPercent = 100.f;
	private Paint mPaint = null;
	private Paint mPaintFunnelLine = null;
	private boolean mFunnelLineVisible = true;
	
	private Paint mPaintLabel = null;
	private Paint mPaintLabelLine = null;
	
	//同步标签颜色
	private boolean mIsLabelLineSyncColor = false;
	private boolean mIsLabelSyncColor = false;
	private boolean mIsShowLabelLine = false;
	private XEnum.HorizontalAlign mLabelAlign = XEnum.HorizontalAlign.CENTER;
	private boolean mLabelVisible = true;
			
	public FunnelChart() {		
				
	}
	
	@Override
	public XEnum.ChartType getType()
	{
		return XEnum.ChartType.FUNNEL;
	}
	
	/**
	 * 区域画笔
	 * @return 画笔
	 */
	public Paint getPaint()
	{
		if(null == mPaint)mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		return mPaint;
	}
	
	/**
	 * 各区域间的间隔线画笔
	 * @return 画笔
	 */
	public Paint getFunnelLinePaint()
	{
		if(null == mPaintFunnelLine)mPaintFunnelLine = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaintFunnelLine.setStrokeWidth(5);
		return mPaintFunnelLine;
	}
	
	/**
	 * 开放标签画笔
	 * @return 画笔
	 */
	public Paint getLabelPaint()
	{
		if(null == mPaintLabel)mPaintLabel = new Paint(Paint.ANTI_ALIAS_FLAG);
		return mPaintLabel;
	}
	
	/**
	 * 开放标签连接线画笔
	 * @return 画笔
	 */
	public Paint getLabelLinePaint()
	{
		if(null == mPaintLabelLine)mPaintLabelLine = new Paint(Paint.ANTI_ALIAS_FLAG);
		return mPaintLabelLine;
	}
	
	/**
	 * 设置用于绘图的宽度比例
	 * @param percent 比例
	 */
	public void setPlotWidthPercent(float percent)
	{
		mPlotWidthPercent = percent;
	}
	
	/**
	 * 设置报表的数据排序及显示方式
	 * @param type 显示方式
	 */
	public void setSortType(XEnum.SortType type)
	{
		mSortType = type;
	}
	
	/**
	 * 不显示标签连接线
	 */
	public void hideLabelLine()
	{
		mIsShowLabelLine = false;
	}
	
	/**
	 * 显示标签连接线
	 */
	public void showLabelLine()
	{
		mIsShowLabelLine = true;
	}
	
	/**
	 * 标签连接线显示状态
	 * @return 状态
	 */
	public boolean isShowLabelLine()
	{
		return mIsShowLabelLine;
	}
	
	/**
	 * 设置标签颜色与当地扇区颜色同步
	 */
	public void syncLabelLineColor()
	{
		mIsLabelLineSyncColor = true;
	}
	
	/**
	 * 设置折线标签颜色与当地扇区颜色同步
	 */
	public void syncLabelColor()
	{
		mIsLabelSyncColor = true;
	}
	
	/**
	 * 设置是否显示区域间隔线	
	 * @param visible 是否显示
	 */
	public void setFunnelLineVisible(boolean visible) 
	{
		mFunnelLineVisible = visible;
	}
	
	/**
	 * 返回是否显示区域间隔线	
	 * @return 是否显示
	 */
	public boolean getFunnelLineVisible()
	{
		return mFunnelLineVisible;
	}
	
	/**
	 * 设置是否在线上显示标签
	 * @param visible 是否显示
	 */
	public void setLabelVisible(boolean visible) 
	{
		mLabelVisible = visible;
	}
	
	/**
	 * 返回是否在线上显示标签
	 * @return 是否显示
	 */
	public boolean getLabelVisible()
	{
		return mLabelVisible;
	}
	
	/**
	 * 显示标签显示位置
	 * @param align 位置
	 */
	public void setLabelAlign(XEnum.HorizontalAlign align)
	{
		mLabelAlign = align;
		
		switch(mLabelAlign)
		{
			case LEFT:
				getLabelPaint().setTextAlign(Align.LEFT);
				showLabelLine();
				break;
			case CENTER:
				getLabelPaint().setTextAlign(Align.CENTER);
				break;
			case RIGHT:
				getLabelPaint().setTextAlign(Align.RIGHT);
				showLabelLine();
				break;
			default:	
				getLabelPaint().setTextAlign(Align.CENTER);
		}
		
	}
	
	/**
	 * 返回标签显示位置
	 * @return 位置
	 */
	public XEnum.HorizontalAlign getLabelAlign()
	{
		return mLabelAlign;
	}
	
	/**
	 * 返回图的数据源
	 * @return 数据源
	 */
	public List<FunnelData> getDataSource() {
		return mDataSet;
	}
	
	/**
	 * 设置数据源
	 * @param dataSet 数据集
	 */
	public void setDataSource(List<FunnelData> dataSet)
	{
		mDataSet = dataSet;
	}
	
	private boolean sortDataSet()
	{		
		if(null == mDataSet)
		{
			Log.e(TAG,"数据源为空!");
			return false;
		}		
					
		for(int i= mDataSet.size()- 1;i >= 0;i--)
		{
			FunnelData d = mDataSet.get(i);
			if( Float.compare(d.getData(), 0.0f) == -1 
					||  Float.compare(d.getData(), 0.0f) == 0)
			{
				mDataSet.remove(i);
			}		
		}
		if( mDataSet.size() == 0) return false;			
		if( XEnum.SortType.NORMAL != mSortType)Collections.sort(mDataSet);		
		return true;
	}
	
	
	private void drawTriangle(Canvas canvas,
					float cx,PointF start,PointF stop)
	{
		
		Path path = new Path();
		path.moveTo(start.x,start.y);
		path.lineTo(stop.x,stop.y);
		
		switch(mSortType){
			case DESC:
				path.lineTo(cx,plotArea.getBottom());
				break;
			case ASC:
			case NORMAL:
			default:
				path.lineTo(cx,plotArea.getTop() );
		}						
		path.close();
		getPaint().setColor( mDataSet.get(0).getColor());
		canvas.drawPath(path, getPaint());		
	}
	
	private float getHalfWidth(float funnelWidth,float data)
	{
		return funnelWidth  * (data/100) /2;
	}
	
	protected void renderPlotDesc(Canvas canvas,float cx,float funnelWidth,float funnelHeight)
	{
		int count = mDataSet.size();
		float halfWidth = 0.f;
		float bottomY = plotArea.getBottom();
		
		PointF pStart = new PointF();
		PointF pStop = new PointF();		
		
		pStart.x = cx - plotArea.getPlotWidth()/2;
		pStop.x = cx + plotArea.getPlotWidth()/2;
		pStart.y = pStop.y = plotArea.getBottom();
		
		halfWidth = funnelWidth /2;
		
		float labelY = 0.f;
		
		Path path = new Path();					
		for(int i=0;i<count;i++)
		{
			FunnelData d = mDataSet.get(i);
			
			path.reset();				
			if(i == 0)
			{					
				path.moveTo(cx,plotArea.getBottom());
			}else{					
				path.moveTo(pStart.x,pStart.y);
				path.lineTo(pStop.x,pStop.y);
			}	
			halfWidth = getHalfWidth(funnelWidth,d.getData());
		
			bottomY = sub(plotArea.getBottom(),i * funnelHeight);		
			
			labelY = bottomY - funnelHeight/2;	
						
			pStart.x = cx - halfWidth;
			pStart.y = bottomY - funnelHeight;
			
			pStop.x = cx + halfWidth;
			pStop.y = bottomY - funnelHeight;		
							
			path.lineTo(pStop.x,pStop.y);
			path.lineTo(pStart.x,pStart.y);
			
			this.getPaint().setColor(d.getColor());			
			path.close();
			if(d.getAlpha() != -1)getPaint().setAlpha(d.getAlpha());	
			canvas.drawPath(path, this.getPaint());	
			if(d.getAlpha() != -1)getPaint().setAlpha(255);
			
			if(i != count -1 && mFunnelLineVisible)
			{
				canvas.drawLine(pStart.x,pStart.y, pStop.x,pStop.y, this.getFunnelLinePaint());
			}
			renderLabels(canvas,d.getLabel(),cx,labelY,d.getColor());
		}				
	}
	
	protected void renderPlotAsc(Canvas canvas,float cx,float funnelWidth,float funnelHeight)
	{
		int count = mDataSet.size();
		float halfWidth = 0.f;
		float bottomY = plotArea.getBottom();
		
		PointF pStart = new PointF();
		PointF pStop = new PointF();
					
		pStart.x = cx - plotArea.getPlotWidth()/2;
		pStop.x = cx + plotArea.getPlotWidth()/2;		
		pStart.y = pStop.y =   plotArea.getBottom();
				
		float labelY = 0.f;
		halfWidth = funnelWidth /2;
		
		Path path = new Path();		
		for(int i=0;i<count;i++)
		{
			FunnelData d = mDataSet.get(i);			
			path.reset();									
			if(i == 0)//三角
			{					
				path.moveTo(cx,plotArea.getTop());					
			}else{					
				path.moveTo(pStart.x,pStart.y);
				path.lineTo(pStop.x,pStop.y);										
			}			
			
			halfWidth = getHalfWidth(funnelWidth,d.getData());							
			bottomY = add(plotArea.getTop(),i * funnelHeight);
			
			labelY = bottomY + funnelHeight/2;	
			
			pStart.x = cx - halfWidth;
			pStart.y = bottomY + funnelHeight;
			
			pStop.x = cx + halfWidth;
			pStop.y = bottomY + funnelHeight;
														
			path.lineTo(pStop.x,pStop.y);
			path.lineTo(pStart.x,pStart.y);
			path.close();
			
			this.getPaint().setColor(d.getColor());						
			if(d.getAlpha() != -1)getPaint().setAlpha(d.getAlpha());						
			canvas.drawPath(path, this.getPaint());					
			if(d.getAlpha() != -1)getPaint().setAlpha(255);
			
			if(i != count -1 && mFunnelLineVisible)
			{
				canvas.drawLine(pStart.x,pStart.y, pStop.x,pStop.y, this.getFunnelLinePaint());
			}
			
			renderLabels(canvas,d.getLabel(),cx, labelY,d.getColor());
		}				
	}
	
	private void renderPlotOne(Canvas canvas,float cx,float funnelWidth,float funnelHeight)
	{			
			FunnelData d = mDataSet.get(0);
			float halfWidth = getHalfWidth(funnelWidth,d.getData());
			
			PointF pStart = new PointF();
			PointF pStop = new PointF();
			
			pStart.x = cx - halfWidth;
			pStop.x = cx + halfWidth;			
			
			if(XEnum.SortType.DESC == mSortType)
			{
				pStart.y = pStop.y = plotArea.getTop();
			}else{
				pStart.y = pStop.y =   plotArea.getBottom();
			}									
			if(d.getAlpha() != -1)getPaint().setAlpha(d.getAlpha());	
			drawTriangle(canvas,cx, pStart,pStop );
			if(d.getAlpha() != -1)getPaint().setAlpha(255);
			
			float labelY = plotArea.getBottom() - plotArea.getHeight()/2;	
			renderLabels(canvas,d.getLabel(),cx, labelY,d.getColor());
			
			return ;				
	}
	
	protected void renderLabels(Canvas canvas,String label,float cx,float y,int color)
	{		
		if(!getLabelVisible())return;
		if("" == label) return;
		
		if(mIsLabelLineSyncColor)
		{
			getLabelLinePaint().setColor(color);
			getLabelPaint().setColor(color);
		}else if(mIsLabelSyncColor )
		{
			getLabelPaint().setColor(color);
		}
						
		if(isShowLabelLine())
		{
			float labelWidth = DrawHelper.getInstance().getTextWidth(getLabelPaint(), label);
			switch(getLabelAlign())
			{
				case LEFT:
					canvas.drawLine(cx, y, plotArea.getLeft() + labelWidth, y, getLabelLinePaint());					
					break;
				case CENTER:
					break;
				case RIGHT:
					canvas.drawLine(cx, y, plotArea.getRight() - labelWidth, y, getLabelLinePaint());	
					break;
				default:	
					break;
			}							
		}
		float labelX = 0.f;		
		switch(getLabelAlign())
		{
			case LEFT:
				labelX = plotArea.getLeft();				
				break;
			case CENTER:
				labelX = cx;
				break;
			case RIGHT:
				labelX = plotArea.getRight();
				break;
			default:	
				labelX = cx;
		}		
		canvas.drawText(label, labelX, y ,getLabelPaint());		
	}
	
	protected void renderPlot(Canvas canvas)
	{
		if(!sortDataSet())return;
		
		int count = mDataSet.size();
		float funnelWidth = plotArea.getPlotWidth() * (mPlotWidthPercent/100);
		float funnelHeight = this.plotArea.getHeight() /count;
		float cx = plotArea.getCenterX();
		
		if(1 == count)
		{			
			renderPlotOne(canvas,cx,funnelWidth,funnelHeight);
		}
		
		if(XEnum.SortType.DESC == mSortType)
		{
			renderPlotDesc(canvas,cx,funnelWidth,funnelHeight);
		}else{
			renderPlotAsc(canvas,cx,funnelWidth,funnelHeight);
		}			
	}
	
	@Override
	protected boolean postRender(Canvas canvas) throws Exception 
	{	
		try {
			super.postRender(canvas);
			
			//计算主图表区范围
			 calcPlotRange();
			//画Plot Area背景			
			 plotArea.render(canvas);	
			//绘制标题
			renderTitle(canvas);
			
			//绘制图表
			renderPlot(canvas);
			
			//显示焦点
			renderFocusShape(canvas);
		
			//响应提示
			renderToolTip(canvas);
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
	

}
