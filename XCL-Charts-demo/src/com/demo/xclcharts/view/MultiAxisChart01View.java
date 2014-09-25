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
 * @version 1.0
 */
package com.demo.xclcharts.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.xclcharts.chart.BarChart;
import org.xclcharts.chart.BarData;
import org.xclcharts.chart.LineChart;
import org.xclcharts.chart.LineData;
import org.xclcharts.renderer.XChart;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.axis.DataAxis;
import org.xclcharts.renderer.axis.CategoryAxis;
import org.xclcharts.renderer.bar.Bar;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.IFormatterTextCallBack;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.util.Log;

/**
 * @ClassName MultiAxisChart01View
 * @Description 主要演示一个轴上显示多种图的坐标系	
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class MultiAxisChart01View extends DemoView {
	
	private String TAG = "MultiAxisChart01View";
	
	//标签轴
		List<String> chartLabels = new LinkedList<String>();
		List<BarData> chartDataset = new LinkedList<BarData>();
		
		//标签轴
		List<String> chartLabelsLn = new LinkedList<String>();
		LinkedList<LineData> chartDatasetLn = new LinkedList<LineData>();
		
		BarChart chart = new BarChart();
		LineChart lnChart = new LineChart();

		public MultiAxisChart01View(Context context) {
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
			chartDatasetLnSet();			
			chartLnLabels();
			chartLnDataSet();	
			chartRender();
			chartLnRender();
		}
		
		
		@Override  
	    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
	        super.onSizeChanged(w, h, oldw, oldh);  
	       //图所占范围大小
	        chart.setChartRange(w,h);
	        
	        lnChart.setChartRange(w,h);
	    }  
		
		
		private void chartRender()
		{
			try {
				//设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....		
				int [] ltrb = getBarLnDefaultSpadding();
				chart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);	
				
				chart.setChartDirection(XEnum.Direction.VERTICAL);
				//标题
				chart.setTitle("Virtual vs Native Oracle RAC Performance");
				chart.addSubtitle("(XCL-Charts Demo)");				
				chart.getPlotTitle().getTitlePaint().setTextSize(20);
				//图例
				chart.getAxisTitle().setLeftAxisTitle("Orders Per Minute (OPM)");
				chart.getAxisTitle().setRightAxisTitle("Average Response Time (RT)");			
				
				//轴
				renderBarAxis();
				//bar
				renderBar();
				
				//网格背景
				chart.getPlotGrid().showHorizontalLines();	
				chart.getPlotGrid().showEvenRowBgColor();
				chart.getPlotGrid().showOddRowBgColor();			
				//隐蔽Key值说明			
				chart.getPlotLegend().hideLegend();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.toString());
			}
		}
		private void chartDatasetLnSet()
		{
			//标签对应的柱形数据集
			List<Double> dataSeries1= new LinkedList<Double>();	
			dataSeries1.add(40000d); 
			dataSeries1.add(73000d); 
			List<Double> dataSeries2= new LinkedList<Double>();	
			dataSeries2.add(45000d); 
			dataSeries2.add(85000d); 
			BarData BarDataA = new BarData("Virtual OPM",dataSeries1,(int)Color.rgb(0, 221, 177));	
			BarData BarDataB = new BarData("Physical OPM",dataSeries2,(int)Color.rgb(238, 28, 161));		
						
			chartDataset.add(BarDataA);
			chartDataset.add(BarDataB);			
		}
		
		private void chartLabels()
		{			
			chartLabels.add("4 Cores Per Node"); 
			chartLabels.add("8 Cores per Node"); 	
		}
		
						
		/**
		 * 定制柱形顶上的标签格式
		 */
		private void renderBar(){
			
			Bar bar = chart.getBar();
			bar.setItemLabelVisible(true);
			bar.setItemLabelRotateAngle(-90);
			bar.getItemLabelPaint().setFakeBoldText(true);
			
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
			chart.setCategories(chartLabels);	
			
			//数据轴
			chart.setDataSource(chartDataset);
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
			CategoryAxis categoryAxis = chart.getCategoryAxis();
			categoryAxis.setTickLabelRotateAngle(-15f);	
			categoryAxis.getTickLabelPaint().setTextSize(15);
			categoryAxis.getTickLabelPaint().setTextAlign(Align.CENTER);
			categoryAxis.setLabelFormatter(new IFormatterTextCallBack(){
	
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
			lnChart.setCategories(chartLabelsLn);		
			lnChart.getCategoryAxis().setVisible(false);	
			
			//设定数据源						
			lnChart.setDataSource(chartDatasetLn);
			//数据轴
			DataAxis dataAxis = lnChart.getDataAxis();
			dataAxis.setHorizontalTickAlign(Align.RIGHT);	
			dataAxis.setAxisMax(135);
			dataAxis.setAxisMin(0);
			dataAxis.setAxisSteps(5);			
			dataAxis.getTickMarksPaint().setColor((int)Color.rgb(51, 204, 204));
			dataAxis.getTickLabelPaint().setTextAlign(Align.LEFT);
			dataAxis.getTickLabelPaint().setColor((int)Color.rgb(51, 204, 204));
		
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
			chartDatasetLn.add(lineDataBarKey1);
			chartDatasetLn.add(lineDataBarKey2);	
			
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
			lineData1.getDotPaint().setColor((int)Color.rgb(234, 83, 71));
						
			chartDatasetLn.add(lineData1);
			chartDatasetLn.add(lineData2);	
			
		}
		
		private void chartLnLabels()
		{
			//折线图坐标系是封闭，所以前后要加上两个空白的
			chartLabelsLn.add(" "); 
			chartLabelsLn.add("4 Cores Per Node"); 
			chartLabelsLn.add("8 Cores per Node"); 	
			chartLabelsLn.add(" "); 
		}
		
		private void chartLnRender()
		{
			try {
				//设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....		
				int [] ltrb = getBarLnDefaultSpadding();
				lnChart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);	
				
				renderLnAxis();
				
				lnChart.getPlotLegend().showLegend();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.toString());
			}
		}
		
		@Override
	    public void render(Canvas canvas) {
	        try{
	        	chart.render(canvas);
				lnChart.render(canvas);
	        } catch (Exception e){
	        	Log.e(TAG, e.toString());
	        }
	    }


		@Override
		public List<XChart> bindChart() {
			// TODO Auto-generated method stub
			List<XChart> lst = new ArrayList<XChart>();
			lst.add(chart);		
			lst.add(lnChart);
			return lst;
		}
}
