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

import android.graphics.Canvas;
import org.xclcharts.chart.BarChart;
import org.xclcharts.chart.BarData;
import org.xclcharts.chart.LineChart;
import org.xclcharts.chart.LineData;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.axis.DataAxis;
import org.xclcharts.renderer.axis.LabelsAxis;
import org.xclcharts.renderer.bar.Bar;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.IFormatterTextCallBack;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;

/**
 * @ClassName MultiAxisChart01View
 * @Description 主要演示一个轴上显示多种图的坐标系	
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class MultiAxisChart01View extends GraphicalView {
	//标签轴
		List<String> chartLables = new LinkedList<String>();
		List<BarData> chartData = new LinkedList<BarData>();
		
		//标签轴
		List<String> chartLablesLn = new LinkedList<String>();
		LinkedList<LineData> chartDataLn = new LinkedList<LineData>();
		
		BarChart chart = new BarChart();
		LineChart lnChart = new LineChart();

		public MultiAxisChart01View(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			chartLabels();
			chartDataLnSet();
			
			chartLnLabels();
			chartLnDataSet();
			
			chartRender();
			chartLnRender();

		}
    @Override
    public void render(Canvas canvas) {
        try {
            chart.render(canvas);
            lnChart.render(canvas);
        } catch (Exception e){
        }
    }

		private void chartRender()
		{
			try {
								
				//柱形图所占范围大小
				chart.setChartRange(0.0f, 0.0f, getScreenWidth(),getScreenHeight());
				chart.setChartDirection(XEnum.Direction.VERTICAL);	
				
				if(chart.isVerticalScreen())
				{
					chart.setPadding(20, 10, 10, 10);
				}else{
					chart.setPadding(20, 30, 18, 10);
				}
				
				//标题
				chart.setChartTitle("Virtual vs Native Oracle RAC Performance");
				chart.setChartSubTitle("(XCL-Charts Demo)");				
				chart.getPlotTitle().getChartTitlePaint().setTextSize(20);
				//图例
				chart.getLegend().setLeftLegend("Orders Per Minute (OPM)");
				chart.getLegend().setRightLegend("Average Response Time (RT)");			
				
				//轴
				renderBarAxis();
				//bar
				renderBar();
				
				//网格背景
				chart.getPlotGrid().setHorizontalLinesVisible(true);
				chart.getPlotGrid().setVerticalLinesVisible(false);
				chart.getPlotGrid().setEvenRowsFillVisible(true);
				chart.getPlotGrid().setOddRowsFillVisible(true);
				
				chart.setPlotKeyVisible(false);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		private void chartDataLnSet()
		{
			//标签1对应的柱形数据集
			List<Double> dataSeries1= new LinkedList<Double>();	
			dataSeries1.add(40000d); 
			dataSeries1.add(73000d); 
			List<Double> dataSeries2= new LinkedList<Double>();	
			dataSeries2.add(45000d); 
			dataSeries2.add(85000d); 
			BarData BarDataA = new BarData("Virtual OPM",dataSeries1,(int)Color.rgb(0, 221, 177));	
			BarData BarDataB = new BarData("Physical OPM",dataSeries2,(int)Color.rgb(238, 28, 161));		
						
			chartData.add(BarDataA);
			chartData.add(BarDataB);			
		}
		
		private void chartLabels()
		{			
			chartLables.add("4 Cores Per Node"); 
			chartLables.add("8 Cores per Node"); 	
		}
		
						
		/**
		 * 定制柱形顶上的标签格式
		 */
		private void renderBar(){
			
			Bar bar = chart.getBar();
			bar.setItemLabelsVisible(true);
			bar.setItemLabelsRotateAgent(-90);
			bar.getItemLabelsPaint().setFakeBoldText(true);
			
			chart.setItemLabelFormatter(new IFormatterDoubleCallBack() {
				@Override
				public String doubleFormatter(Double value) {
					// TODO Auto-generated method stub
					// DecimalFormat df=new DecimalFormat("#0.00");
					DecimalFormat df=new DecimalFormat("#0");
					return df.format(value).toString();
				}});	
		}
		
		/**
		 * 柱形图轴相关
		 */
		private void renderBarAxis()
		{
			//标签轴
			chart.setLabels(chartLables);	
			
			//数据轴
			chart.setDataSource(chartData);
			chart.getDataAxis().setAxisMax(90000);
			chart.getDataAxis().setAxisSteps(10000);					
			
			//定制数据轴上的标签格式
			chart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){
	
				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub				
				
				    double label=Double.parseDouble(value);
					DecimalFormat df=new DecimalFormat("#0");
					return df.format(label).toString();
				}
				
			});
			
			//定制标签轴标签的标签格式
			LabelsAxis labelAxis = chart.getLabelsAxis();
			labelAxis.setAxisTickLabelsRotateAgent(-15f);	
			labelAxis.getAxisTickLabelsPaint().setTextSize(15);
			labelAxis.getAxisTickLabelsPaint().setTextAlign(Align.CENTER);
			labelAxis.setLabelFormatter(new IFormatterTextCallBack(){
	
				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub				
					//String tmp = "c-["+value+"]";
					return value;
				}
				
			});
		}
		
		/**
		 * 折线图轴相关
		 */
		private void renderLnAxis()
		{
			//标签轴
			lnChart.setLabels(chartLablesLn);		
			lnChart.getLabelsAxis().setVisible(false);	
			
			//设定数据源						
			lnChart.setDataSource(chartDataLn);
			//数据轴
			DataAxis dataAxis = lnChart.getDataAxis();
			dataAxis.setAxisHorizontalTickAlign(Align.RIGHT);	
			dataAxis.setAxisMax(135);
			dataAxis.setAxisMin(0);
			dataAxis.setAxisSteps(5);			
			dataAxis.getAxisTickMarksPaint().setColor((int)Color.rgb(51, 204, 204));
			dataAxis.getAxisTickLabelsPaint().setTextAlign(Align.LEFT);
			dataAxis.getAxisTickLabelsPaint().setColor((int)Color.rgb(51, 204, 204));
		
			//把折线图默认的顶上的轴线隐藏
			lnChart.setTopAxisVisible(false);
			
			//定制数据轴上的标签格式
			lnChart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){
	
				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub				
				
				    double label=Double.parseDouble(value);
					DecimalFormat df=new DecimalFormat("#0");
					return df.format(label).toString();
				}
				
			});			
		}
		
		private void chartLnDataSet()
		{
			//用于在key上显示柱形的Key
			LinkedList<Double> BarKey1= new LinkedList<Double>();				
			BarKey1.add(0d); 
			LinkedList<Double> BarKey2= new LinkedList<Double>();				
			BarKey2.add(0d); 
			
			LineData lineDataBarKey1 = new LineData("Virtual OPM",BarKey1,(int)Color.rgb(0, 221, 177));
			LineData lineDataBarKey2 = new LineData("Physical OPM",BarKey2,(int)Color.rgb(238, 28, 161));
			lineDataBarKey1.setDotStyle(XEnum.DotStyle.RECT);
			lineDataBarKey2.setDotStyle(XEnum.DotStyle.RECT);
			chartDataLn.add(lineDataBarKey1);
			chartDataLn.add(lineDataBarKey2);	
			
			//实际的折线图的数据集
			//标签1对应的数据集
			LinkedList<Double> virtual= new LinkedList<Double>();	
			virtual.add(0d); 			
			virtual.add(95d); 
			virtual.add(100d); 
			virtual.add(0d); 
			
			//标签2对应的数据集
			LinkedList<Double> physical= new LinkedList<Double>();				
			physical.add(0d); 
			physical.add(110d); 
			physical.add(125d); 
			physical.add(0d); 
			
			//将标签与对应的数据集分别绑定
			LineData lineData1 = new LineData("Virtual RT",virtual,(int)Color.rgb(234, 83, 71));
			LineData lineData2 = new LineData("Physical RT",physical,(int)Color.rgb(75, 166, 51));
			lineData1.setDotStyle(XEnum.DotStyle.TRIANGLE);
			lineData1.getPlotDotPaint().setColor((int)Color.rgb(234, 83, 71));
						
			chartDataLn.add(lineData1);
			chartDataLn.add(lineData2);	
			
		}
		
		private void chartLnLabels()
		{
			//折线图坐标系是封闭，所以前后要加上两个空白的
			chartLablesLn.add(" "); 
			chartLablesLn.add("4 Cores Per Node"); 
			chartLablesLn.add("8 Cores per Node"); 	
			chartLablesLn.add(" "); 
		}
		
		private void chartLnRender()
		{
			try {
				
				//柱形图所占范围大小
				lnChart.setChartRange(0.0f, 0.0f, getScreenWidth(), getScreenHeight());

				if(lnChart.isVerticalScreen())
				{
					lnChart.setPadding(20, 10, 10, 10);
				}else{
					lnChart.setPadding(20, 30, 18, 10);
				}							
				renderLnAxis();
								
				lnChart.setPlotKeyVisible(true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
