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
 * @Copyright Copyright (c) 2014 XCL-Charts (www.xclcharts.com)
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version v0.1
 */
package org.xclcharts.renderer.plot;

import org.xclcharts.common.DrawHelper;
import org.xclcharts.renderer.IRender;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

/**
 * @ClassName PlotGridRender
 * @Description 主图表区网格绘制类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */

public class PlotGridRender extends PlotGrid implements IRender{
	
	private Canvas mCanvas = null;	
	private DrawHelper mDrawHelper = new DrawHelper();
	
	public PlotGridRender()
	{
		super();
	}
		
	/**
	 * 绘制奇数行填充
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	public void renderOddRowsFill(float left,float top,float right,float bottom)
	{
		 if(null != mCanvas && getOddRowsFillVisible())
		 {
			 mCanvas.drawRect( left,  bottom  ,right, top,getOddFillPaint());
		 }		
	}
	
	/**
	 * 绘制偶数行填充
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	public void renderEvenRowsFill(float left,float top,float right,float bottom)
	{
		 if(null != mCanvas && getEvenRowsFillVisible())
		 {				
			 mCanvas.drawRect( left,  bottom  ,right, top,getEvenFillPaint());
		 }		
	}
	
	/**
	 * 绘制横向网格线
	 * @param startX
	 * @param startY
	 * @param stopX
	 * @param stopY
	 */
	public void renderGridLinesHorizontal(float startX,float startY,float stopX,float stopY)
	{
		 if(null != mCanvas &&this.getHorizontalLinesVisible())
		 {	
			mDrawHelper.drawLine(this.getHorizontalLinesDashStyle(), startX, startY, stopX, stopY,
					mCanvas, getHorizontalLinesPaint());
		 }
	}
	
	/**
	 * 绘制竖向网格线
	 * @param startX
	 * @param startY
	 * @param stopX
	 * @param stopY
	 */
	public void renderGridLinesVertical(float startX,float startY,float stopX,float stopY)
	{
		 if(getVerticalLinesVisible())
		 {
			if(null == mCanvas) return;
			mDrawHelper.drawLine(this.getVerticalLinesDashStyle(), startX, startY, stopX, stopY,
					mCanvas, getVerticalLinesPaint());
			
		 }
	}
	
	
	@Override
	public void setCanvas(Canvas canvas) {
		// TODO Auto-generated method stub
		mCanvas = canvas;
	}

	@Override
	public boolean render() { //throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
