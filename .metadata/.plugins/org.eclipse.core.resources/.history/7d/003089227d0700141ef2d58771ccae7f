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
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.XChart;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

/**
 * @ClassName BarChart02View
 * @Description  柱形图例子(横向)
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class BarChart02View extends TouchView {
	
	private String TAG = "BarChart02View";
	private BarChart chart = new BarChart();
	
	//标签轴
	private List<String> chartLabels = new LinkedList<String>();
	private List<BarData> chartData = new LinkedList<BarData>();

	public BarChart02View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		chartLabels();
		chartDataSet();	
		chartRender();
	}
	
	private void chartRender()
	{
		try {
						
			//图所占范围大小
			chart.setChartRange(0.0f, 0.0f, getScreenWidth(),getScreenHeight());
						
			if(chart.isVerticalScreen())
			{
				chart.setPadding(15, 20, 12, 10);
			}else{
				chart.setPadding(25, 20, 10, 5);
			}		
			
			chart.setTitle("每日收益情况");
			chart.addSubtitle("(XCL-Charts Demo)");		
			chart.setTitlePosition(XEnum.Position.CENTER);
			chart.setTitleAlign(XEnum.ChartTitleAlign.LEFT);
			
			//数据源
			chart.setDataSource(chartData);
			chart.setCategories(chartLabels);	
			
			//图例
			chart.getLegend().setLeftLegend("所售商品");
			chart.getLegend().setLowerLegend("纯利润(天)");
			chart.getLegend().setRightLegend("生意兴隆通四海,财源茂盛达三江。");
			
			//数据轴
			chart.getDataAxis().setAxisMax(500);
			chart.getDataAxis().setAxisMin(100);
			chart.getDataAxis().setAxisSteps(100);
			
			chart.getDataAxis().getAxisTickLabelPaint().
									setColor((int)Color.rgb(199, 88, 122));
			chart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){

				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub				
					String tmp = value+"万元";
					return tmp;
				}
				
			});
			
			/*
			chart.getCategoryAxis().setLabelFormatter(new IFormatterTextCallBack(){

				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub				
					String tmp = "~"+value+"]";
					return tmp;
				}				
			});
			*/
			
			//网格
			chart.getPlotGrid().showHorizontalLines();
			chart.getPlotGrid().showVerticalLines();
			chart.getPlotGrid().showEvenRowBgColor();
			
			//标签轴文字旋转-45度
			chart.getCategoryAxis().setTickLabelRotateAgent(-45f);
			//横向显示柱形
			chart.setChartDirection(XEnum.Direction.HORIZONTAL);
			//在柱形顶部显示值
			chart.getBar().setItemLabelVisible(true);
			
			chart.setItemLabelFormatter(new IFormatterDoubleCallBack() {
				@Override
				public String doubleFormatter(Double value) {
					// TODO Auto-generated method stub
					DecimalFormat df=new DecimalFormat("#0.00");					 
					String label = df.format(value).toString();
					return label;
				}});	        
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.toString());
			}
	}
	private void chartDataSet()
	{
		//标签对应的柱形数据集
		List<Double> dataSeriesA= new LinkedList<Double>();	
		dataSeriesA.add((double)200); 
		dataSeriesA.add((double)250); 
		dataSeriesA.add((double)400);
		BarData BarDataA = new BarData("小熊",dataSeriesA,(int)Color.rgb(0, 0,255));
		
		
		List<Double> dataSeriesB= new LinkedList<Double>();	
		dataSeriesB.add((double)300);
		dataSeriesB.add((double)150);
		dataSeriesB.add((double)450);
		BarData BarDataB = new BarData("小周",dataSeriesB,(int)Color.rgb(255, 0, 0));
		
		
		chartData.add(BarDataA);
		chartData.add(BarDataB);
	}
	
	private void chartLabels()
	{		
		chartLabels.add("擂茶"); 
		chartLabels.add("槟榔"); 				
		chartLabels.add("纯净水(强势插入，演示多行标签)"); 
	}

	@Override
    public void render(Canvas canvas) {
        try{
            chart.render(canvas);
        } catch (Exception e){
        	Log.e(TAG, e.toString());
        }
    }

	@Override
	public List<XChart> bindChart() {
		// TODO Auto-generated method stub		
		List<XChart> lst = new ArrayList<XChart>();
		lst.add(chart);		
		return lst;
	}
}
