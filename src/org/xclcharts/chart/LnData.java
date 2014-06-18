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
package org.xclcharts.chart;

import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.line.PlotLines;

import android.graphics.Paint;


/**
 * @ClassName LnData
 * @Description 线图(曲线图  折线图 面积图)数据基类 
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */

public class LnData {
	//标签轴用的到值
	private String mLables;
	//是否在点上显示标签
	private boolean mLablesVisible = false;

	//线的基类
	private PlotLines mPlotLines = null;
	
	public LnData()
	{
		mPlotLines = new PlotLines(); 
	}
	
	/**
	 * 设置是否在线上显示标签
	 * @param visible 是否显示
	 */
	public void setLineLabelVisible(boolean visible) 
	{
		mLablesVisible = visible;
	}
	
	/**
	 * 返回是否在线上显示标签
	 * @return 是否显示
	 */
	public boolean getLineLabelVisible()
	{
		return mLablesVisible;
	}
	
	/**
	 * 设置标签
	 * @param value 标签内容
	 */
	public void setLineLabel(String value) 
	{
		mLables = value;
	}
	
	/**
	 * 返回标签
	 * @return 标签
	 */
	public String getLineLabel() {
		return mLables;
	}
	
	/**
	 * 返回线的基类
	 * @return 线的基类
	 */
	public PlotLines getPlotLines()
	{
		return mPlotLines;
	}			
				
	/**
	 * 设置线的颜色	
	 * @param color
	 */
	public void setLineColor(int color) 
	{				
		mPlotLines.getPlotLinesPaint().setColor(color );
		mPlotLines.getPlotLabelsPaint().setColor(color );	
		mPlotLines.getPlotDotPaint().setColor(color );	
	}
	
	/**
	 * 返回线的颜色
	 * @return 线的颜色
	 */
	public int getLineColor() {
		return mPlotLines.getPlotLinesPaint().getColor();
	}
	
	/**
	 * 设置点的显示风格
	 * @param style 显示风格
	 */
	public void setDotStyle(XEnum.DotStyle style)
	{
		mPlotLines.setDotStyle(style);
	}
	
	/**
	 * 返回点的显示风格
	 * @return 显示风格
	 */
	public XEnum.DotStyle getDotStyle()
	{
		return mPlotLines.getDotStyle();
	}
	
	/**
	 * 设置当前记录的Key值
	 * @param value Key值
	 */
	public void setLineKey(String value) 
	{
		mLables = value;
	}
	/**
	 * 返回Key值
	 * @return Key值
	 */
	public String getLineKey() {
		return mLables;
	}
	
	/**
	 * 开放标签画笔
	 * @return 画笔
	 */
	public Paint getPlotLabelsPaint()
	{
		return mPlotLines.getPlotLabelsPaint();
	}
	
	/**
	 * 开放线的画笔
	 * @return 画笔
	 */
	public Paint getPlotLinesPaint()
	{
		return mPlotLines.getPlotLinesPaint();
	}
	
	/**
	 * 开放点的画笔
	 * @return 画笔
	 */
	public Paint getPlotDotPaint()
	{
		return mPlotLines.getPlotDotPaint();
	}
	
	/**
	 * 开放点的半径,用来决定绘制的点的图形的大小
	 * @param radius 半径
	 */
	public void setDotRadius(int radius)
	{
		 mPlotLines.getPlotDot().setDotRadius(radius);
	}
	
}
