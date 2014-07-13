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

import org.xclcharts.renderer.XEnum;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

/**
 * @ClassName Border
 * @Description  图边框基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * 
 */

public class Border {
	
	//线的基类画笔	
	private Paint mBorderLinePaint = new Paint();
	
	private XEnum.LineStyle mLineStyle = XEnum.LineStyle.SOLID;
	private XEnum.RectType mRectType = XEnum.RectType.ROUNDRECT;	
	private int mRaidus = 15;
	protected static final int mBorderSpadding = 5;

	public Border()
	{							
		mBorderLinePaint = new Paint();
		mBorderLinePaint.setAntiAlias(true);
		mBorderLinePaint.setColor((int)Color.rgb(26, 59, 105));
		mBorderLinePaint.setStyle(Style.STROKE);  		
		mBorderLinePaint.setStrokeWidth(2);
	}
	
	/**
	 * 开放线的画笔
	 * @return 画笔
	 */
	public Paint getLinePaint()
	{
		return mBorderLinePaint;
	}
	
	/**
	 * 设置线的颜色	
	 * @param color 线的颜色
	 */
	public void setBorderLineColor(int color) 
	{				
		mBorderLinePaint.setColor(color );
	}
	
	/**
	 * 
	 * @param style
	 */
	public void setBorderLineStyle(XEnum.LineStyle style) 
	{				
		mLineStyle = style;
	}	
	
	public void setBorderRectType(XEnum.RectType type) 
	{				
		mRectType = type;
	}
				
	public XEnum.LineStyle getBorderLineStyle()
	{
		return mLineStyle;		
	}
	
	public XEnum.RectType getBorderRectType()
	{
		return mRectType;
	}
	
	public void setRoundRadius(int radius)
	{
		mRaidus = radius;
	}
	
	public int getRoundRadius()
	{		
		return mRaidus;		
	}
	
	public int getBorderWidth()
	{
		int width = mBorderSpadding;		
		if(getBorderRectType() == XEnum.RectType.ROUNDRECT)
		{
			width += getRoundRadius();
		}		
		return width;
	}
}
