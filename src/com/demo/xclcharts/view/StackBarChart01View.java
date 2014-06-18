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
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
/**
 * @ClassName StackBarChart01View
 * @Description  堆叠图 的例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class StackBarChart01View extends GraphicalView {
	
	//标签轴
	List<String> chartLables = new LinkedList<String>();
	List<BarData> BarDataSet = new LinkedList<BarData>();
    StackBarChart chart = new StackBarChart();

	public StackBarChart01View(Context context) {
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
			chart.setChartDirection(XEnum.Direction.VERTICAL);
			if(chart.isVerticalScreen())
			{
			chart.setPadding(15, 20, 10, 5);
			}else{
				chart.setPadding(25, 30, 18, 5);
			}
			//数据源		
			chart.setLabels(chartLables);	
			chart.setDataSource(BarDataSet);
			
			//坐标系
			chart.getDataAxis().setAxisMax(1024);
			chart.getDataAxis().setAxisMin(0);
			chart.getDataAxis().setAxisSteps(64);
			//指定数据轴标签旋转-45度显示
			chart.getLabelsAxis().setAxisTickLabelsRotateAgent(-45f);	
			Paint labelPaint = chart.getLabelsAxis().getAxisTickLabelsPaint();			
			labelPaint.setTextAlign(Align.RIGHT);
			labelPaint.setColor((int)Color.rgb(0, 75, 106));
			
			//标题
			chart.setChartTitle("文件服务器空间使用情况");
			chart.setChartSubTitle("(XCL-Charts Demo)");
			chart.setChartTitleAlign(XEnum.ChartTitleAlign.CENTER);
			chart.setChartTitlePosition(XEnum.Position.CENTER);
			
			//图例
			chart.getLegend().setLeftLegend("单位(TB)");
			//chart.getLegend().setLowerLegend("文件服务器");
			//chart.getLegend().setRightLegend("右边图例");
			
			//背景网格
			chart.getPlotGrid().setEvenRowsFillVisible(true);
			chart.getPlotGrid().setOddRowsFillVisible(true);
			
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
			
			//定义标签轴标签显示格式
			chart.getLabelsAxis().setLabelFormatter(new IFormatterTextCallBack(){
	
				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub				
					String label = "IP-["+value+"]";
					return label;
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void chartDataSet()
	{
		//标签1对应的柱形数据集
		List<Double> valuesA= new LinkedList<Double>();	
		valuesA.add((double)200);
		valuesA.add((double)250);
		valuesA.add((double)400); 

		List<Double> valuesB= new LinkedList<Double>();	
		valuesB.add((double)300);
		valuesB.add((double)150); 
		valuesB.add((double)450); 

		
		BarDataSet.add(new BarData("已用空间",valuesA,(int)Color.rgb(0, 0,255)));
		BarDataSet.add(new BarData("空闲空间",valuesB,(int)Color.rgb(255, 0, 0)));
	}
	private void chartLabels()
	{
		chartLables.add("172.16.8.1"); 
		chartLables.add("172.16.8.6"); 
		chartLables.add("172.16.8.8"); 
	}

}
