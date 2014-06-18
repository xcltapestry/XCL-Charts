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

import java.util.LinkedList;
import java.util.List;

import org.xclcharts.renderer.XEnum;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * @ClassName AreaData
 * @Description  面积图数据传输类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * * MODIFIED    YYYY-MM-DD   REASON
 */
public class AreaData extends LineData {
	
	private Paint mPaintOutline = null;
	
	private int mAreaFillColor = -999; // 255;
	
	public AreaData()
	{
		super();
		init();
	}
	
	/**
	 * 
	 * @param key		key值
	 * @param linecolor 线颜色
	 * @param dataSeries	数据序列
	 */
	public AreaData(String key,int linecolor,LinkedList<Double> dataSeries) 
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
		setLineLabel(key);	
		setLinePoint(dataSeries);
		setLineColor(lineColor);	
		setAreaFillColor(areaColor);
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
		setLineLabel(key);		
		setLineColor(color);
		setLinePoint(dataSeries);
		setDotStyle(dotStyle);
		setAreaFillColor(color);
	}
	
	private void init()
	{
		mPaintOutline = new Paint();
		mPaintOutline.setColor(Color.BLACK);
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
	
	
}
