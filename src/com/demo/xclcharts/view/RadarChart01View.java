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
package com.demo.xclcharts.view;

import org.xclcharts.chart.RadarChart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

/**
 * @ClassName RadarChart01View
 * @Description  雷达图例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class RadarChart01View extends DemoView {

	public RadarChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		initChart();
	}
	

	/**
	 * 用于初始化
	 */
	private void initChart()
	{			
		chartLabels();
		chartDataSet();	
	}
	
	/**
	 * 绘制图表
	 * @param canvas 视图画布
	 */
	protected void drawChart(Canvas canvas)
	{						
		chartRender(canvas);	
	}
	
	private void chartRender(Canvas canvas)
	{
		try{				
			RadarChart chart = new RadarChart();
			 
			//柱形图所占范围大小
			chart.setChartRange(0.0f, 0.0f,getScreenWidth(),getScreenHeight());
			chart.setCanvas(canvas);
			chart.setPadding(15, 30, 10, 5);
			
			chart.setChartTitle("雷达图");
			
			//绘制
			chart.render();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("ERROR-RadarChart01View", e.toString());
		}
		
	}
	
	private void chartDataSet()
	{
		
	}
	
	private void chartLabels()
	{
		
	}

}
