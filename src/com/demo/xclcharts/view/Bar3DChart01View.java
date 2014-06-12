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

import org.xclcharts.chart.Bar3DChart;
import org.xclcharts.chart.BarData;
import org.xclcharts.chart.common.IFormatterDoubleCallBack;
import org.xclcharts.chart.common.IFormatterTextCallBack;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Color;

/**
 * @ClassName Bar3DChart01View
 * @Description  3D柱形图例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class Bar3DChart01View extends GraphicalView {
	
	//标签轴
	private List<String> chartLabels = new LinkedList<String>();
	//数据轴
	private List<BarData> BarDataSet = new LinkedList<BarData>();
	
	
	public Bar3DChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
				
		chartLabels();
		chartDataSet();
		chartRender();				
	}
	
	private void chartRender()
	{
		try {						
			Bar3DChart chart = new Bar3DChart();
			chart.setCanvas(this.mCacheCanvas);
			//柱形图所占范围大小
			chart.setChartRange(0.0f, 0.0f,getScreenWidth(),getScreenHeight());
			//Plot的内边距比例		
			if(chart.isVerticalScreen())
			{
				chart.setPadding(15, 20, 10, 5);
			}else{
				chart.setPadding(25, 20, 18, 5);
			}
			
			//数据源			
			chart.setDataSource(BarDataSet);
			chart.setLabels(chartLabels);	
			
			//坐标系
			chart.getDataAxis().setAxisMax(500);
			chart.getDataAxis().setAxisMin(100);
			chart.getDataAxis().setAxisSteps(100);
			//chart.getLabelsAxis().setAxisTickLabelsRotateAgent(-45f);
			
			//标题
			chart.setChartTitle("本周水果销售情况");
			chart.setChartSubTitle("(XCL-Charts Demo)");		
			chart.setChartTitleAlign(XEnum.ChartTitleAlign.RIGHT);
			
			//背景网格
			chart.getPlotGrid().setHorizontalLinesVisible(true);
			chart.getPlotGrid().setVerticalLinesVisible(true);
			chart.getPlotGrid().setEvenRowsFillVisible(true);
			chart.getPlotGrid().setOddRowsFillVisible(true);
			
			//定义数据轴标签显示格式		
			chart.getDataAxis().setAxisTickLabelsRotateAgent(-45);
			chart.getDataAxis().getAxisTickMarksPaint().
					setColor((int)Color.rgb(186, 20, 26));
			chart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){
	
				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub							
					Double tmp = Double.parseDouble(value);
					DecimalFormat df=new DecimalFormat("#0");
					String label = df.format(tmp).toString();				
					return (label +"公斤");
				}
				
			});
			
			//定义标签轴标签显示格式
			chart.getLabelsAxis().setLabelFormatter(new IFormatterTextCallBack(){
	
				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub									
					return value;
				}
				
			});
			//定义柱形上标签显示格式
			chart.getBar().setItemLabelsVisible(true);
			chart.setItemLabelFormatter(new IFormatterDoubleCallBack() {
				@Override
				public String doubleFormatter(Double value) {
					// TODO Auto-generated method stub
					DecimalFormat df=new DecimalFormat("#0.00");					 
					String label = df.format(value).toString();
					return label;
				}});	        
		
			//绘制
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
		dataSeriesA.add(200d);
		dataSeriesA.add(250d);
		dataSeriesA.add(400d); 

		List<Double> dataSeriesB= new LinkedList<Double>();	
		dataSeriesB.add(300d);
		dataSeriesB.add(150d); 
		dataSeriesB.add(450d); 
		
		BarDataSet.add(new BarData("左边店",dataSeriesA,(int)Color.rgb(252, 210, 9)));
		BarDataSet.add(new BarData("右边店",dataSeriesB,(int)Color.rgb(55, 144, 206)));
	}
	
	private void chartLabels()
	{
		chartLabels.add("桃子(Peach)"); 
		chartLabels.add("梨子(Pear)"); 
		chartLabels.add("香蕉 (Banana)"); 
	}


}
