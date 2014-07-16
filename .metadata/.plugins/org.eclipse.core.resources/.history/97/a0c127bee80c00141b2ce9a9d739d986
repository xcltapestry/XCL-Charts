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
package org.xclcharts.renderer.plot;

import org.xclcharts.common.DrawHelper;

import android.graphics.Canvas;
import android.graphics.RectF;
/**
 * @ClassName BorderRender
 * @Description  图边框绘制类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * 
 */
public class BorderRender extends Border {
	

	public BorderRender()
	{
		super();
	}
	

	/**
	 * 绘制边
	 * @param canvas
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	public void renderBorder(Canvas canvas,
							 float left,float top,float right,float bottom)
	{
		RectF rect = new RectF();
		rect.left = left + mBorderSpadding;
		rect.top = top + mBorderSpadding;
		rect.right = right - mBorderSpadding;
		rect.bottom = bottom - mBorderSpadding;				
	
		switch(getBorderLineStyle())
		{
		case SOLID:					
			break;
		case DOT:			
			getLinePaint().setPathEffect(DrawHelper.getInstance().getDotLineStyle());			
			break;
		case DASH:		
			//虚实线 	
			getLinePaint().setPathEffect(DrawHelper.getInstance().getDashLineStyle());
			break;
		}	
		
		switch(getBorderRectType())
		{
		case RECT:				
			canvas.drawRect(rect, getLinePaint());		
			break;
		case ROUNDRECT:			
			canvas.drawRoundRect(rect, getRoundRadius(), getRoundRadius(), getLinePaint());		
			break;
		}
	}
	
	
}
