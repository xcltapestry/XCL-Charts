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

import org.xclcharts.chart.LineChart;
import org.xclcharts.chart.LineData;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Align;

/**
 * @ClassName LineChart01View
 * @Description  折线图的例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class LineChart01View extends DemoView {
	
	//标签集合
	private LinkedList<String> lables = new LinkedList<String>();
	private LinkedList<LineData> chartData = new LinkedList<LineData>();

	public LineChart01View(Context context) {
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
		try {				
			
			LineChart chart = new LineChart();
			//柱形图所占范围大小
			chart.setChartRange(0.0f, 0.0f,getScreenWidth(),getScreenHeight());
			chart.setCanvas(canvas);
			chart.setPadding(20, 20, 10, 5);
			
			//设定数据源
			chart.setLabels(lables);								
			chart.setDataSource(chartData);
			
			//数据轴最大值
			chart.getDataAxis().setAxisMax(100);
			//数据轴刻度间隔
			chart.getDataAxis().setAxisSteps(10);
			
			//背景网格
			chart.getPlotGrid().setHorizontalLinesVisible(true);
			chart.getPlotGrid().setVerticalLinesVisible(true);
			chart.getPlotGrid().setEvenRowsFillVisible(true);
			chart.getPlotGrid().setOddRowsFillVisible(true);
			
			chart.getPlotGrid().getHorizontalLinesPaint().setStrokeWidth(2);
			chart.getPlotGrid().setHorizontalLinesStyle(XEnum.LineStyle.DASH);
			chart.getPlotGrid().setVerticalLinesStyle(XEnum.LineStyle.DOT);
			
			chart.getPlotGrid().getHorizontalLinesPaint().setColor(Color.RED);
			chart.getPlotGrid().getVerticalLinesPaint().setColor(Color.BLUE);
			
			chart.setChartTitle("折线图(Line Chart)");
			chart.setChartSubTitle("(XCL-Charts Demo)");
			
			chart.getLegend().setLowerLegend("(年份)");
			
			chart.render();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void chartDataSet()
	{
		//Line 1
		LinkedList<Double> valuesA= new LinkedList<Double>();	
		valuesA.add(20d); 
		valuesA.add(10d); 
		valuesA.add(31d); 
		valuesA.add(40d);
		valuesA.add(0d);
		LineData lineData1 = new LineData("方块",valuesA,(int)Color.rgb(234, 83, 71));
		lineData1.setLineLabelVisible(true);		
		lineData1.setDotStyle(XEnum.DotStyle.RECT);				
		lineData1.getDotLabelPaint().setColor(Color.BLUE);
		lineData1.getDotLabelPaint().setTextSize(22);
		lineData1.getDotLabelPaint().setTextAlign(Align.RIGHT);		
		//Line 2
		LinkedList<Double> valuesB= new LinkedList<Double>();	
		valuesB.add((double)30); 
		valuesB.add((double)42); 
		valuesB.add((double)50); 	
		valuesB.add((double)60); 
		valuesB.add((double)40); 
		LineData lineData2 = new LineData("空心圆",valuesB,(int)Color.rgb(75, 166, 51));
		lineData2.setDotStyle(XEnum.DotStyle.RING);				
		lineData2.getPlotLine().getDotPaint().setColor(Color.BLACK);
		lineData2.setLineLabelVisible(true);		
		//Line 3
		LinkedList<Double> valuesC= new LinkedList<Double>();	
		valuesC.add(65d);
		valuesC.add(75d);
		valuesC.add(55d);
		valuesC.add(65d);
		valuesC.add(95d);
		LineData lineData3 = new LineData("实心圆",valuesC,(int)Color.rgb(123, 89, 168));
		lineData3.setDotStyle(XEnum.DotStyle.CIRCLE);
		//Line 4
		LinkedList<Double> valuesD= new LinkedList<Double>();	
		valuesD.add(50d);
		valuesD.add(60d);
		valuesD.add(80d);
		valuesD.add(84d);
		valuesD.add(90d);
		LineData lineData4 = new LineData("棱形",valuesD,(int)Color.rgb(84, 206, 231));		
		lineData4.setDotStyle(XEnum.DotStyle.PRISMATIC);
		//Line 5
		LinkedList<Double> valuesE= new LinkedList<Double>();	
		valuesE.add(0d);
		valuesE.add(80d);
		valuesE.add(85d);
		valuesE.add(90d);
		LineData lineData5 = new LineData("定制",valuesE,(int)Color.rgb(234, 142, 43));
		lineData5.setDotRadius(15);
		
		chartData.add(lineData1);
		chartData.add(lineData2);
		chartData.add(lineData3);
		chartData.add(lineData4);
		chartData.add(lineData5);
	}
	
	private void chartLabels()
	{
		lables.add("2010");
		lables.add("2011");
		lables.add("2012");
		lables.add("2013");
		lables.add("2014");
	}
	
}
