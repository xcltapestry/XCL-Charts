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

package org.xclcharts.renderer.plot;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

/**
 * @ClassName PlotArea
 * @Description 主图表区类，用于定制其属性
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * * MODIFIED    YYYY-MM-DD   REASON
 */

public class PlotArea {
	
	//主图表区范围
	protected float mPlotLeft  = 0.0f;
	protected float mPlotTop  = 0.0f;
	protected float mPlotRight   = 0.0f;
	protected float mPlotBottom  = 0.0f;
	
	protected float mPlotWidth = 0.0f;
	protected float mPlotHeight = 0.0f;
	
	//主图表区背景色,即画X轴与Y轴围成的区域
	protected Paint mPlotBackgroundPaint = null;	
	
	//是否画背景色
	protected boolean mBackgroupColorVisible = false;
		
	
	public PlotArea()
	{
		init();
	}
	
	private void init()
	{
		mPlotBackgroundPaint = new Paint();
		mPlotBackgroundPaint.setStyle(Style.FILL);	
		mPlotBackgroundPaint.setColor(Color.WHITE);
	}
	
	
	/**
	 * 开放主图表区背景画笔，即画X轴与Y轴围成的区域的背景画笔。
	 * @return 画笔
	 */
	 public Paint getBackgroundPaint()
	 {
		 return mPlotBackgroundPaint;
	 }
	 
	 /**
	  * 设置是否显示背景色
	  * @param visible 是否显示背景色
	  */
	public void setBackgroupColorVisitle(boolean visible)
	{
		mBackgroupColorVisible = visible;
	}
	
	/**
	 * 设置是否显示背景色及其背景色的值
	 * @param visible 是否显示背景色
	 * @param color	      背景色
	 */
	public void setBackgroupColor(boolean visible,int color)
	{
		mBackgroupColorVisible = visible;
		getBackgroundPaint().setColor(color);
	}	
	

	/**
	 * 绘图区左边位置X坐标
	 * @return X坐标
	 */
	public float getPlotLeft() {
		return mPlotLeft;
	}

	/**
	 * 绘图区上方Y坐标
	 * @return Y坐标
	 */
	public float getPlotTop() {
		return mPlotTop;
	}
	
	/**
	 * 绘图区下方Y坐标
	 * @return Y坐标
	 */
	public float getPlotBottom() {
		return mPlotBottom;
	}
	
	/**
	 * 绘图区右边位置X坐标
	 * @return X坐标
	 */
	public float getPlotRight() {
		return mPlotRight;
	}
	
	/**
	 * 绘图区宽度
	 * @return 宽度
	 */
	public float getPlotWidth() {		
		mPlotWidth = (float)Math.abs(mPlotRight - mPlotLeft);		
		return mPlotWidth;
	}

	/**
	 * 绘图区高度
	 * @return 高度
	 */
	public float getPlotHeight() {		
		mPlotHeight = Math.abs(getPlotBottom() - getPlotTop());
		return mPlotHeight;
	}
		
}
