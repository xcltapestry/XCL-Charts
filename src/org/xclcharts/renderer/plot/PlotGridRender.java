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

/**
 * @ClassName PlotGridRender
 * @Description 主图表区网格绘制类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */

public class PlotGridRender extends PlotGrid implements IRender{
	
	private DrawHelper mDrawHelper = new DrawHelper();
	private boolean mPrimaryTickLine = false;
	
	private final int BLOB_WIDTH =  2;;
	
	public PlotGridRender()
	{
		super();
	}
	
	/**
	 * 是否为主Tick对应的网格线,如果是,在划线时需加粗
	 * @param primary 是否为主Tick如是则会加粗显示
	 */
	public void setPrimaryTickLine(boolean primary)
	{
		mPrimaryTickLine = primary;
	}
		
	/**
	 * 绘制奇数行填充
	 * @param left		左边X坐标
	 * @param top		顶上Y坐标
	 * @param right		右边X坐标
	 * @param bottom	底上Y坐标
	 */
	public void renderOddRowsFill(Canvas canvas,float left,float top,float right,float bottom)
	{
		 if(null != canvas && getOddRowsFillVisible())
		 {
             canvas.drawRect( left,  bottom  ,right, top,getOddFillPaint());
		 }		
	}
	
	/**
	 * 绘制偶数行填充
	 * @param left 		左边X坐标
	 * @param top		顶上Y坐标
	 * @param right		右边X坐标
	 * @param bottom	 底上Y坐标
	 */
	public void renderEvenRowsFill(Canvas canvas,float left,float top,float right,float bottom)
	{
		 if(null != canvas && getEvenRowsFillVisible())
		 {				
			 canvas.drawRect( left,  bottom  ,right, top,getEvenFillPaint());
		 }		
	}
	
		
	
	/**
	 * 绘制横向网格线
	 * @param startX	起始点X坐标
	 * @param startY	起始点Y坐标
	 * @param stopX		终止点X坐标
	 * @param stopY		终止点Y坐标
	 */
	public void renderGridLinesHorizontal(Canvas canvas,float startX,float startY,float stopX,float stopY)
	{
		 if(null != canvas &&this.getHorizontalLinesVisible())
		 {	
			 float initWidth = getHorizontalLinesPaint().getStrokeWidth() ;
			 if(mPrimaryTickLine) getHorizontalLinesPaint().setStrokeWidth( initWidth + BLOB_WIDTH );	
			 
			 mDrawHelper.drawLine(this.getHorizontalLinesDashStyle(), startX, startY, stopX, stopY,
					canvas, getHorizontalLinesPaint());
			 
			 if(mPrimaryTickLine)getHorizontalLinesPaint().setStrokeWidth(initWidth);
		 }
	}
	
	/**
	 * 绘制竖向网格线
	 * @param startX	起始点X坐标
	 * @param startY	起始点Y坐标
	 * @param stopX		终止点X坐标
	 * @param stopY		终止点Y坐标
	 */
	public void renderGridLinesVertical(Canvas canvas,float startX,float startY,float stopX,float stopY)
	{
		 if(null != canvas && getVerticalLinesVisible())
		 {						
			 float initWidth = getVerticalLinesPaint().getStrokeWidth() ;
			 if(mPrimaryTickLine) getVerticalLinesPaint().setStrokeWidth( initWidth + BLOB_WIDTH );	
			 
			 mDrawHelper.drawLine(this.getVerticalLinesDashStyle(), startX, startY, stopX, stopY,
					canvas, getVerticalLinesPaint());
			
			 if(mPrimaryTickLine)getVerticalLinesPaint().setStrokeWidth(initWidth);
			
		 }
	}
	
	


	@Override
	public boolean render(Canvas canvas) { //throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
