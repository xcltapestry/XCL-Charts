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
import java.util.List;

import android.graphics.Canvas;
import org.xclcharts.chart.AreaChart;
import org.xclcharts.chart.AreaData;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;


/**
 * @ClassName AreaChart01View
 * @Description  面积图例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */

public class AreaChart01View extends GraphicalView {
	
	//标签集合
	private LinkedList<String> mChartLabels = new LinkedList<String>();
	//数据集合
	private LinkedList<AreaData> mChartDataSet = new LinkedList<AreaData>();
    AreaChart chart = new AreaChart();
			

	public AreaChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
				
		chartLabels();
		chartDataSet();
		chartRender();
	}
	
	
	private void chartRender()
	{
		try{				
				//柱形图所占范围大小
				chart.setChartRange(0.0f, 0.0f,getScreenWidth(),getScreenHeight());
				chart.setPadding(15, 30, 10, 5);
											
				//轴数据源						
				//标签轴
				chart.setLabels(mChartLabels);
				//数据轴
				chart.setDataSource(mChartDataSet);
							
				//数据轴最大值
				chart.getDataAxis().setAxisMax(100);
				//数据轴刻度间隔
				chart.getDataAxis().setAxisSteps(10);
				
				//背景网格
				chart.getPlotGrid().setEvenRowsFillVisible(true);
				chart.getPlotGrid().setOddRowsFillVisible(true);
				
				//标题
				chart.setChartTitle("区域图(Area Chart)");
				chart.setChartSubTitle("(XCL-Charts Demo)");	
				//图例
				chart.getLegend().setLowerLegend("(年份)");
				
				//透明度
				//chart.setAreaAlpha(200);
				//显示键值
				chart.setPlotKeyVisible(true);
			
				//绘制
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("ERROR-AreaChart01View", e.toString());
		}
	}

    @Override
    public void render(Canvas canvas) {
        try{
            chart.render(canvas);
        } catch (Exception e){
        }
    }

    private void chartDataSet()
	{
		//将标签与对应的数据集分别绑定
		//标签对应的数据集
		List<Double> dataSeries1= new LinkedList<Double>();	
		dataSeries1.add((double)55); 
		dataSeries1.add((double)60); 
		dataSeries1.add((double)71); 
		dataSeries1.add((double)40);
		dataSeries1.add((double)35);
		
		List<Double> dataSeries2 = new LinkedList<Double>();			
		dataSeries2.add((double)10); 
		dataSeries2.add((double)22); 
		dataSeries2.add((double)30); 	
		dataSeries2.add((double)30); 
		dataSeries2.add((double)15); 
		
		//设置每条线各自的显示属性
		//key,数据集,线颜色,区域颜色
		AreaData line1 = new AreaData("小熊",dataSeries1,Color.BLUE,Color.YELLOW);
		//不显示点
		line1.setDotStyle(XEnum.DotStyle.HIDE);
		
		AreaData line2 = new AreaData("小小熊",dataSeries2,
											(int)Color.rgb(79, 200, 100),Color.GREEN);
		//设置线上每点对应标签的颜色
		line2.getPlotLabelsPaint().setColor(Color.RED);
		//设置点标签
		line2.setLineLabelVisible(true);
		
		mChartDataSet.add(line1);
		mChartDataSet.add(line2);	
	}
	
	private void chartLabels()
	{		
		mChartLabels.add("2010");
		mChartLabels.add("2011");
		mChartLabels.add("2012");
		mChartLabels.add("2013");
		mChartLabels.add("2014");
	}
	
}
