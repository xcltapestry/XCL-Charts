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

import org.xclcharts.renderer.XEnum;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * @ClassName PlotDot
 * @Description  用于处理线条上的点
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */

public class PlotDot {

	private Paint mPaintDot = null;
	
	//Triangle 三角  	
	//线上的点为圆或角
	protected XEnum.DotStyle mDotStyle = XEnum.DotStyle.CIRCLE;
	
	private int mRadius = 10;
	
	public PlotDot()
	{
		init();
	}
	
	private void init()
	{
		mPaintDot = new Paint();
		mPaintDot.setColor(Color.BLUE);
		mPaintDot.setAntiAlias(true);
	}
	
	/**
	 * 开放绘制点的画笔
	 * @return 画笔
	 */
	public Paint getDotPaint()
	{
		return mPaintDot;
	}
	
	/**
	 * 设置点的显示风格
	 * @param style 显示风格
	 */
	public void setDotStyle( XEnum.DotStyle style)
	{
		mDotStyle = style;
	}
	
	/**
	 * 返回点的显示风格
	 * @return 显示风格
	 */
	public XEnum.DotStyle getDotStyle()
	{
		return mDotStyle;
	}
	
	/**
	 * 设置点的绘制半径大小，会依此指定的半径来绘制相关图形
	 * @param radius 半径
	 */
	public void setDotRadius(int radius)
	{
		mRadius = radius;
	}
	
	/**
	 * 返回点的绘制半径大小
	 * @return 半径
	 */
	public int getDotRadius()
	{
		return mRadius;
	}
	
	
}
