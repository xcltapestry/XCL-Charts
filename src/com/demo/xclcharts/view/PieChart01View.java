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

import java.util.LinkedList;

import android.graphics.Canvas;
import org.xclcharts.chart.PieChart;
import org.xclcharts.chart.PieData;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Color;

/**
 * @ClassName PieChart01View
 * @Description  饼图的例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class PieChart01View extends GraphicalView {
	
	LinkedList<PieData> lPieData = new LinkedList<PieData>();
    PieChart chart = new PieChart();

	public PieChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		chartDataSet();
		chartRender();
	}

    @Override
    public void render(Canvas canvas) {
        try {
            chart.render(canvas);
        } catch (Exception e){
        }
    }
	
	private void chartRender()
	{
		try {					
			//图所占范围大小
			chart.setChartRange(0.0f, 0.0f, getScreenWidth(), getScreenHeight());
			//图的内边距
			chart.setPadding(10, 20, 15, 15);
			
			//设定数据源
			chart.setDataSource(lPieData);			
			
			//设置起始偏移角度(即第一个扇区从哪个角度开始绘制)
			chart.setInitialAngle(90);	
			
			//标签显示(隐藏，显示在中间，显示在扇区外面)
			chart.setLabelsDisplay(XEnum.DisplayPosition.OUTSIDE);
			
			chart.setChartTitle("Pie Chart");
			chart.getPlotTitle().setChartTitlePosition(XEnum.Position.LOWER);
			
			chart.setKeyVisible(true);
			//绘制
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void chartDataSet()
	{
		//设置图表数据源		
		lPieData.add(new PieData("HP","20%",20,(int)Color.rgb(155, 187, 90)));
		lPieData.add(new PieData("IBM","30%",30,(int)Color.rgb(191, 79, 75),false));
		lPieData.add(new PieData("DELL","10%",10,(int)Color.rgb(242, 167, 69)));
		//将此比例块突出显示
		lPieData.add(new PieData("EMC","40%",40,(int)Color.rgb(60, 173, 213),true));
	}

}
