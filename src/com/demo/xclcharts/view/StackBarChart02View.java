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
import org.xclcharts.chart.BarData;
import org.xclcharts.chart.StackBarChart;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.IFormatterTextCallBack;

import android.content.Context;
import android.graphics.Color;
/**
 * @ClassName StackBarChart02View
 * @Description  堆叠图 的例子(横向)
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class StackBarChart02View extends GraphicalView {

	//标签轴
	List<String> chartLabels = new LinkedList<String>();
	List<BarData> BarDataSet = new LinkedList<BarData>();
    StackBarChart chart = new StackBarChart();

	public StackBarChart02View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		chartLabels();
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
			//柱形图所占范围大小
			chart.setChartRange(0.0f, 0.0f,getScreenWidth(),getScreenHeight());
			//指定显示为横向柱形
			chart.setChartDirection(XEnum.Direction.HORIZONTAL);
			if(chart.isVerticalScreen())
			{
				chart.setPadding(15, 20, 10, 5);
			}else{
				chart.setPadding(25, 20, 18, 5);
			}
			
			//数据源		
			chart.setLabels(chartLabels);	
			chart.setDataSource(BarDataSet);
			
			//坐标系
			chart.getDataAxis().setAxisMax(1200);
			chart.getDataAxis().setAxisMin(100);
			chart.getDataAxis().setAxisSteps(100);
			//指定数据轴标签旋转-45度显示
			chart.getLabelsAxis().setAxisTickLabelsRotateAgent(-45f);			
			
			//标题
			chart.setChartTitle("费用预算与实际发生对比");
			chart.setChartSubTitle("(XCL-Charts Demo)");
			chart.setChartTitleAlign(XEnum.ChartTitleAlign.CENTER);
			
			//图例
			chart.getLegend().setLowerLegend("单位为(W)");
			
			//背景网格
			chart.getPlotGrid().setVerticalLinesVisible(true);
			chart.getPlotGrid().setEvenRowsFillVisible(true);
			chart.getPlotGrid().getEvenFillPaint().setColor((int)Color.rgb(225, 230, 246)); 
			
			//定义数据轴标签显示格式
			chart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){
	
				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub	
					
					DecimalFormat df=new DecimalFormat("#0");
					Double tmp = Double.parseDouble(value);
					String label = df.format(tmp).toString();					
					return label;
				}
				
			});
			
			//定义标签轴标签显示颜色
			chart.getLabelsAxis().getAxisTickLabelsPaint().
				setColor((int)Color.rgb(1, 188, 242));
					
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
		dataSeriesA.add(550d);
		dataSeriesA.add(400d); 

		List<Double> dataSeriesB= new LinkedList<Double>();	
		dataSeriesB.add(380d);
		dataSeriesB.add(452.57d); 
		dataSeriesB.add(657.65d); 

		
		BarDataSet.add(new BarData("预算(Budget)",dataSeriesA,(int)Color.rgb(64, 175, 240)));
		BarDataSet.add(new BarData("实际(Actual)",dataSeriesB,(int)Color.rgb(247, 156, 27)));
	}
	private void chartLabels()
	{
		chartLabels.add("一季度(Q1)"); 
		chartLabels.add("二季度(Q2)"); 
		chartLabels.add("三季度(Q3)"); 
	}

}
