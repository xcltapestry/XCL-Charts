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

package org.xclcharts.chart;

import java.util.List;

import org.xclcharts.renderer.XEnum;

/**
 * @ClassName RadarData
 * @Description  雷达图数据传输基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */
public class RadarData extends LineData{
	
	//数据填充区域显示风格
	private XEnum.RadarDataAreaStyle mAreaStyle = XEnum.RadarDataAreaStyle.FILL;
	//网格线绘制风格 Solid、Dot、Dash。
	private XEnum.LineStyle mLineStyle = XEnum.LineStyle.SOLID;
	
	public RadarData() {
		// TODO Auto-generated constructor stub
		
		getPlotLine().setDotStyle(XEnum.DotStyle.HIDE);
	}
	
	/**
	 * 
	 * @param key	key值
	 * @param dataSeries 数据序列
	 * @param color		线颜色
	 * @param areaStyle	区域填充类型
	 */
	public RadarData(String key,					
					List<Double> dataSeries,
					int color,
					XEnum.RadarDataAreaStyle  areaStyle) 
	{
		setLabel(key);		
		setLineColor(color);
		setDataSet(dataSeries);
		setAreaStyle(areaStyle);
		getPlotLine().setDotStyle(XEnum.DotStyle.HIDE);
	}
	
	/**
	 * 设置雷达图数据填充区域显示风格
	 * @param style 填满或为空(即只显示线)
	 */
	public void setAreaStyle(XEnum.RadarDataAreaStyle style){
		mAreaStyle = style;
	}
	
	/**
	 * 返回雷达图数据当前的填充区域显示风格
	 * @return 填充区域显示风格
	 */
	public XEnum.RadarDataAreaStyle getAreaStyle()
	{
		return mAreaStyle;
	}
	
	 
	 
	/**
	 * 返回网格线当前绘制风格
	 * @return 绘制风格
	 */
	public XEnum.LineStyle getLineStyle()
	{
		return mLineStyle;
	}

	/**
	 * 设置网格线绘制风格 
	 * @param style 绘制风格
	 */
	public void setLineStyle(XEnum.LineStyle style)
	{
		mLineStyle = style;
	}
	 
	
	
}
