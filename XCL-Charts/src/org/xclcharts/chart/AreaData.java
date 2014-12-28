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

import android.graphics.Color;
import android.graphics.Shader;

/**
 * @ClassName AreaData
 * @Description  面积图数据传输类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * 
 */
public class AreaData extends LineData {
	
	private int mAreaFillColor = -999; // 255;
		
	//是否应用渲染模式
	private boolean mApplayGradient = false;
		
	private int mAreaBeginColor = Color.WHITE;
    private int mAreaEndColor = Color.WHITE;
	private Shader.TileMode mTileMode = Shader.TileMode.MIRROR;

	private XEnum.Direction mDirection = XEnum.Direction.VERTICAL;
	
	public AreaData()
	{
		super();
	}
	
	/**
	 * 
	 * @param key		key值
	 * @param linecolor 线颜色
	 * @param dataSeries	数据序列
	 */
	public AreaData(String key,int linecolor,List<Double> dataSeries) 
	{
		 super();
	}
	
	/**
	 * 
	 * @param key		key值
	 * @param dataSeries	数据序列
	 * @param lineColor	线颜色
	 * @param areaColor	区域填充颜色
	 */
	public AreaData(String key,List<Double> dataSeries,int lineColor,int areaColor) 
	{
		setLabel(key);	
		setDataSet(dataSeries);
		setLineColor(lineColor);	
		setAreaFillColor(areaColor);
		
		setAreaBeginColor(areaColor);
		setAreaEndColor(Color.WHITE);
		
	}
	
	public AreaData(String key,List<Double> dataSeries,int lineColor,
											int areaBeginColor,int areaEndColor) 
	{
		setLabel(key);	
		setDataSet(dataSeries);
		setLineColor(lineColor);	
		setAreaFillColor(areaBeginColor);

		setApplayGradient(true);
		setAreaBeginColor(areaBeginColor);
		setAreaEndColor(areaEndColor);
	}
	
	
	/**
	 * 
	 * @param key		key值
	 * @param dataSeries	数据序列
	 * @param color		线颜色
	 * @param dotStyle	坐标点绘制类型
	 */
	public AreaData(String key,					
					List<Double> dataSeries,
					int color,
					XEnum.DotStyle  dotStyle) 
	{
		setLabel(key);		
		setLineColor(color);
		setDataSet(dataSeries);
		setDotStyle(dotStyle);
		setAreaFillColor(color);
		
		setAreaBeginColor(color);
		setAreaEndColor(Color.WHITE);
	}
	
	
	/**
	 * 设置区域填充颜色
	 * @param color 区域填充颜色
	 */
	public void setAreaFillColor(int color)
	{
		mAreaFillColor = color;
	}
	
	/**
	 * 返回区域填充颜色
	 * @return 区域填充颜色
	 */
	public int getAreaFillColor() {
		return mAreaFillColor;
	}
	 		
	/**
	 * 设置 是否应用渲染模式
	 */
	public void setApplayGradient(boolean status)
	{
		mApplayGradient = status;
	}
	
	/**
	 * 返回是否应用渲染模式
	 * @return 状态
	 */
	public boolean getApplayGradient()
	{
		return mApplayGradient;
	}
	
	/**
	 * 设置渐变渲染方向	
	 * @param direction 方向
	 */
	public void setGradientDirection(XEnum.Direction direction)
	{
		mDirection = direction;
	}
	
	/**
	 * 返回渐变渲染方向	
	 * @return 方向	
	 */
	public XEnum.Direction getGradientDirection()
	{
		return mDirection;
	}
	
	/**
	 * 设置渲染模式
	 * @param tm	渲染模式
	 */
	 public void setGradientMode(Shader.TileMode tm)
	 {
		 mTileMode = tm;
	 }
	 
	 /**
	  * 返回渲染模式
	  * @return 渲染模式
	  */
	 public Shader.TileMode getGradientMode()
	 {
		 return mTileMode;
	 }
	 
	 /**
	  * 设置起始颜色
	  * @param color	颜色
	  */
	 public void setAreaBeginColor(int color)
	 {
		 mAreaBeginColor = color;
	 }
	 
	 /**
	  * 设置结束颜色
	  * @param color	颜色
	  */
	 public void setAreaEndColor(int color)
	 {
		 mAreaEndColor = color;
	 }
	 
	 
	 /**
	  * 返回起始颜色
	  * @return	颜色
	  */
	 public int getAreaBeginColor()
	 {
		 return mAreaBeginColor;
	 }
	 
	 /**
	  * 返回结束颜色
	  * @return	颜色
	  */
	 public int getAreaEndColor()
	 {
		 return mAreaEndColor;
	 }
	 
}
