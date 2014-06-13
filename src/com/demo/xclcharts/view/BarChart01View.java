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

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import org.xclcharts.chart.BarChart;
import org.xclcharts.chart.BarData;
import org.xclcharts.chart.LineChart;
import org.xclcharts.chart.LineData;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;

/**
 * @ClassName BarChart01View
 * @Description  柱形图例子(竖向)
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */

public class BarChart01View extends GraphicalView {
	
	//标签轴
	private List<String> chartLabels = new LinkedList<String>();
	private List<BarData> chartData = new LinkedList<BarData>();
	
	public BarChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		chartLabels();
		chartDataSet();
		chartRender();
		
	}
	
	private void chartRender()
	{
		try {
			
			BarChart chart = new BarChart();
			//图所占范围大小
			chart.setChartRange(0.0f, 0.0f, getScreenWidth(),getScreenHeight());
			chart.setCanvas(this.mCacheCanvas);
			if(chart.isVerticalScreen())
			{
				chart.setPadding(15, 20, 15, 5);
			}else{
				chart.setPadding(20, 20, 18, 5);
			}
			
		
			//标题
			chart.setChartTitle("主要数据库分布情况");
			chart.setChartSubTitle("(XCL-Charts Demo)");	
			chart.getPlotTitle().getChartTitlePaint().setColor(Color.BLUE);
			//数据源
			chart.setDataSource(chartData);
			chart.setLabels(chartLabels);	
			
			//图例
			chart.getLegend().setLeftLegend("数据库个数");
			chart.getLegend().setLowerLegend("分布位置");
			
			//数据轴
			chart.getDataAxis().setAxisMax(100);
			chart.getDataAxis().setAxisMin(0);
			chart.getDataAxis().setAxisSteps(5);
			
			//定义数据轴标签显示格式
			chart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){
	
				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub		
					Double tmp = Double.parseDouble(value);
					DecimalFormat df=new DecimalFormat("#0");
					String label = df.format(tmp).toString();				
					return (label);
				}
				
			});
			
			
			//在柱形顶部显示值
			chart.getBar().setItemLabelsVisible(true);
			//设定格式
			chart.setItemLabelFormatter(new IFormatterDoubleCallBack() {
				@Override
				public String doubleFormatter(Double value) {
					// TODO Auto-generated method stub
					DecimalFormat df=new DecimalFormat("#0");					 
					String label = df.format(value).toString();
					return label;
				}});
			//绘制图
			chart.render();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void chartDataSet()
	{
		//标签对应的柱形数据集
		List<Double> dataSeriesA= new LinkedList<Double>();	
		dataSeriesA.add(66d); 
		dataSeriesA.add(33d); 
		dataSeriesA.add(50d);
		BarData BarDataA = new BarData("Oracle",dataSeriesA,(int)Color.rgb(186, 20, 26));
		
		
		List<Double> dataSeriesB= new LinkedList<Double>();	
		dataSeriesB.add(32d);
		dataSeriesB.add(22d);
		dataSeriesB.add(18d);
		BarData BarDataB = new BarData("SQL Server",dataSeriesB,(int)Color.rgb(1, 188, 242));
		
		List<Double> dataSeriesC= new LinkedList<Double>();	
		dataSeriesC.add(79d);
		dataSeriesC.add(91d);
		dataSeriesC.add(65d);
		BarData BarDataC = new BarData("MySQL",dataSeriesC,(int)Color.rgb(0, 75, 106)); 
		
		chartData.add(BarDataA);
		chartData.add(BarDataB);
		chartData.add(BarDataC);
	}
	
	private void chartLabels()
	{
		chartLabels.add("福田数据中心"); 
		chartLabels.add("西丽数据中心"); 
		chartLabels.add("观澜数据中心"); 
	}	
		
	
}
