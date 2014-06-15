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

import org.xclcharts.renderer.XEnum;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

/**
 * @ClassName PlotGrid
 * @Description 主图表区网格类，用于定制其属性
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * * MODIFIED    YYYY-MM-DD   REASON
 */
public class PlotGrid {
	 //横向网格线
	 private Paint mPaintGridLinesHorizontal = null;
	 //竖向网格线
	 private Paint mPaintGridLinesVertical = null;
	 
	 //是否显示横向网格线
	 private boolean mGridLinesHorizontalVisible = false;
	 //是否显示竖向网格线
	 private boolean mGridLinesVerticalVisible = false;		
		
	 //图内部网格线的间隔色
	 private int mGridLinesOddColor = (int)Color.GREEN;	
	 private int mGridLinesEvenColor = (int)Color.BLUE;
	 //是否显示奇数行填充色
	 private boolean mOddRowFillVisible = false;
	//是否显示偶数行填充色
	 private boolean mEvenRowFillVisible = false;
	 
	 //横向网格线
	 private Paint mPaintOddFill = null;
	 //竖向网格线
	 private Paint mPaintEvenFill = null;
	 
	 // Solid、Dot、Dash。
	 private XEnum.LineDashStyle mHorizontalLineDashStyle = XEnum.LineDashStyle.SOLID;
	 private XEnum.LineDashStyle mVerticalLineDashStyle = XEnum.LineDashStyle.SOLID;
	
	public PlotGrid()
	{
		initPaint();
	}
	
	private void initPaint()
	{
		mPaintGridLinesHorizontal = new Paint();
		mPaintGridLinesVertical = new Paint();
		
		//mPaintGridLinesHorizontal.setColor((int)Color.rgb(239, 239, 239));
		
		mPaintGridLinesHorizontal.setColor((int)Color.rgb(180, 205, 230));
		//mPaintGridLinesVertical.setColor((int)Color.rgb(218, 218, 218));
		mPaintGridLinesVertical.setColor((int)Color.rgb(180, 205, 230));
		
		mPaintGridLinesHorizontal.setStrokeWidth(1);
		mPaintGridLinesVertical.setStrokeWidth(1);
		
		mPaintOddFill = new Paint();
		mPaintEvenFill = new Paint();
		
		mPaintOddFill.setStyle(Style.FILL);
		mPaintEvenFill.setStyle(Style.FILL);
		
		mPaintOddFill.setColor(Color.WHITE);
		mPaintEvenFill.setColor((int)Color.rgb(239, 239, 239)); 
		
		mPaintOddFill.setAntiAlias(true);
		mPaintEvenFill.setAntiAlias(true);
		
		mPaintGridLinesHorizontal.setAntiAlias(true);
		mPaintGridLinesVertical.setAntiAlias(true);
	}
	

	/**
	 * 设置奇数行填充色
	 * @param color 填充色
	 */
	public void setOddRowsFillColor(int color)
	{
		mGridLinesOddColor = color;
		mPaintOddFill.setColor(color);
	}
	/**
	 * 设置偶数行填充色
	 * @param color 填充色
	 */
	public void setEvenRowsFillColor(int color)
	{
		mGridLinesEvenColor = color;		
		mPaintOddFill.setColor(color);
	}
	
	/**
	 * 是否显示横向网格线
	 * @param visible 是否显示
	 */
	public void setHorizontalLinesVisible(boolean visible)
	{
		mGridLinesHorizontalVisible = visible;
	}
	
	/**
	 * 返回是否显示横向网格线
	 * @return 是否显示
	 */
	public boolean getHorizontalLinesVisible()
	{
		return mGridLinesHorizontalVisible;
	}	
	
	/**
	 * 是否显示竖向网格线
	 * @param visible 是否显示
	 */
	public void setVerticalLinesVisible(boolean visible)
	{
		mGridLinesVerticalVisible = visible;
	}
	
	/**
	 * 返回是否显示竖向网格线
	 * @return 是否显示
	 */
	public boolean getVerticalLinesVisible()
	{
		return mGridLinesVerticalVisible;
	}
	
	
	/**
	 * 设置是否显示奇数行填充
	 * @param visible 是否填充
	 */
	public void setOddRowsFillVisible(boolean visible)
	{
		mOddRowFillVisible = visible;
	}
	
	/**
	 * 返回是否显示奇数行填充
	 * @return 是否填充
	 */
	public boolean getOddRowsFillVisible()
	{
		return mOddRowFillVisible;
	}
	
	/**
	 * 设置是否显示偶数行填充
	 * @param visible 是否填充
	 */
	public void setEvenRowsFillVisible(boolean visible)
	{
		mEvenRowFillVisible = visible;
	}
	
	/**
	 * 返回是否显示偶数行填充
	 * @return 是否填充
	 */
	public boolean getEvenRowsFillVisible()
	{
		return mEvenRowFillVisible;
	}
		

	/**
	 * 开放横向网格线画笔
	 * @return 画笔
	 */
	public Paint getHorizontalLinesPaint()
	{
		return mPaintGridLinesHorizontal;	
	}
	 
	/**
	 * 开放竖向网格线画笔
	 * @return 画笔
	 */
	public Paint getVerticalLinesPaint()
	{
		return mPaintGridLinesVertical;	
	}
	
	/**
	 * 开放奇数行填充画笔
	 * @return 画笔
	 */
	public Paint getOddFillPaint()
	{
		return mPaintOddFill;	
	}
	 
	/**
	 * 开放偶数行填充画笔
	 * @return 画笔
	 */
	public Paint getEvenFillPaint()
	{
		return mPaintEvenFill;	
	}

	/**
	 * 返回竖向网格线当前绘制风格
	 * @return  绘制风格
	 */
	public XEnum.LineDashStyle getVerticalLinesDashStyle()
	{ 
		return mVerticalLineDashStyle;
	}

	/**
	 * 设置竖向网格线当前绘制风格
	 * @param style 绘制风格
	 */
	public void setVerticalLinesDashStyle(XEnum.LineDashStyle style)
	{
		mVerticalLineDashStyle = style;
	}
	
	/**
	 * 返回横向网格线当前绘制风格
	 * @return 绘制风格
	 */
	public XEnum.LineDashStyle getHorizontalLinesDashStyle()
	{
		return mHorizontalLineDashStyle;
	}

	/**
	 * 设置横向网格线当前绘制风格 
	 * @param style 绘制风格
	 */
	public void setHorizontalLinesDashStyle(XEnum.LineDashStyle style)
	{
		mHorizontalLineDashStyle = style;
	}
	 
}
