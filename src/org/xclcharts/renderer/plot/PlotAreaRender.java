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


import org.xclcharts.renderer.IRender;

import android.graphics.Canvas;

/**
 * @ClassName PlotAreaRender
 * @Description 主图表区绘制类，绘制背景等
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * * MODIFIED    YYYY-MM-DD   REASON
 */

public class PlotAreaRender extends PlotArea implements IRender{
	
	private Canvas mCanvas = null;
	
	private float mCenterX = 0.0f;
	private float mCenterY = 0.0f;
		
	public PlotAreaRender()
	{
		super();
		init();
	}
	
	private void init()
	{
		
	}
		
	/**
	 * 绘制背景
	 */
	protected void drawPlotBackgroup()
	{
		if(null == mCanvas) return;		
		if(mBackgroupColorVisible)
			this.mCanvas.drawRect(mPlotLeft,mPlotTop,
					mPlotRight,mPlotBottom, mPlotBackgroundPaint);
	}		
	
	/**
	 * 得到中心点X坐标
	 * @return X坐标
	 */
	public float getCenterX() {		
		mCenterX = ((float)Math.abs(mPlotLeft + (mPlotRight - mPlotLeft)/2));		
		return mCenterX;
	}

	/**
	 * 得到中心点Y坐标
	 * @return Y坐标
	 */
	public float getCenterY() {		
		mCenterY = ((float)Math.abs(mPlotBottom - (mPlotBottom - mPlotTop)/2));		
		return mCenterY;
	}
	
	public float getPlotLeft() {
		return mPlotLeft;
	}

	/**
	 * 设置绘图区的左边X坐标
	 * @param left
	 */
	public void setPlotLeft(float left) {
		this.mPlotLeft = left;
	}
	
	/**
	 * 设置绘图区的上面Y坐标
	 * @param top
	 */
	public void setPlotTop(float top) {
		this.mPlotTop = top;
	}

	/**
	 * 设置绘图区的右边X坐标
	 * @param right
	 */
	public void setPlotRight(float right) {
		this.mPlotRight = right;
	}
	
	/**
	 * 设置绘图区的底部Y坐标
	 * @param bottom
	 */
	public void setPlotBottom(float bottom) {
		this.mPlotBottom = bottom;
	}

	@Override
	public void setCanvas(Canvas canvas) {
		// TODO Auto-generated method stub
		mCanvas = canvas;
	}

	@Override
	public boolean render() throws Exception {
		// TODO Auto-generated method stub
		try{
			if(null == mCanvas) return false;
			drawPlotBackgroup();
		}catch( Exception e){
			 throw e;
		}
		return false;
	}

}
