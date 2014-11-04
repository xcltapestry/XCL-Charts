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
	private Paint mPaintBorderLine = null;

	private XEnum.LineStyle mLineStyle = XEnum.LineStyle.SOLID;
	private XEnum.RectType mRectType = XEnum.RectType.ROUNDRECT;	
	private int mRaidus = 15;
	protected static final int mBorderSpadding = 5;
	
	//背景画笔
	protected Paint mPaintBackground = null;

	public Border()
	{							
									
	}
	
	
	/**
	 * 开放线的画笔
	 * @return 画笔
	 */
	public Paint getLinePaint()
	{
		if(null == mPaintBorderLine)
		{
			mPaintBorderLine = new Paint();
			mPaintBorderLine.setAntiAlias(true);
			mPaintBorderLine.setColor(Color.BLACK); //(int)Color.rgb(26, 59, 105));
			mPaintBorderLine.setStyle(Style.STROKE);  		
			mPaintBorderLine.setStrokeWidth(2);	
		}
		return mPaintBorderLine;
	}
		
	/**
	 * 设置线的颜色	
	 * @param color 线的颜色
	 */
	public void setBorderLineColor(int color) 
	{			
		getLinePaint().setColor(color );
	}
	
	/**
	 * 设置边框线类型
	 * @param style 线类型
	 */
	public void setBorderLineStyle(XEnum.LineStyle style) 
	{				
		mLineStyle = style;
	}	
	
	/**
	 * 设置边框类型
	 * @param type 框类型
	 */
	public void setBorderRectType(XEnum.RectType type) 
	{				
		mRectType = type;
	}
				
	/**
	 * 返回边框线类型
	 * @return 边框线类型
	 */
	public XEnum.LineStyle getBorderLineStyle()
	{
		return mLineStyle;		
	}
	
	/**
	 * 返回边框类型
	 * @return 边框类型
	 */
	public XEnum.RectType getBorderRectType()
	{
		return mRectType;
	}
	
	/**
	 * 设置角圆弧半径
	 * @param radius 半径
	 */
	public void setRoundRadius(int radius)
	{
		mRaidus = radius;
	}
	
	/**
	 * 返回角圆弧半径
	 * @return 半径
	 */
	public int getRoundRadius()
	{		
		return mRaidus;		
	}
	
	/**
	 * 返回边框所占宽度
	 * @return 边框所占宽度
	 */
	public int getBorderWidth()
	{
		int width = mBorderSpadding;		
		if(getBorderRectType() == XEnum.RectType.ROUNDRECT)
		{
			width += getRoundRadius();
		}		
		return width;
	}
	
	/**
	 * 开放背景画笔
	 * @return 画笔
	 */
	 public Paint getBackgroundPaint()
	{
		if(null == mPaintBackground)
		{
			mPaintBackground = new Paint();
			mPaintBackground.setAntiAlias(true);
			mPaintBackground.setStyle(Style.FILL);
			mPaintBackground.setColor(Color.WHITE);
			mPaintBackground.setAlpha(220);
		}
		return mPaintBackground;
	}
	
}
