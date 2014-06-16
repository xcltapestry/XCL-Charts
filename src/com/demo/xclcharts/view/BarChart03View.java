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
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version v0.1
 */
package com.demo.xclcharts.view;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import org.xclcharts.chart.BarChart;
import org.xclcharts.chart.BarData;
import org.xclcharts.chart.DesireLineData;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Color;

/**
 * @ClassName BarChart03View
 * @Description 用于展示期望线与明细刻度线
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * MODIFIED    YYYY-MM-DD   REASON
 */
public class BarChart03View extends GraphicalView {
	
	//标签轴
	private List<String> chartLabels = new LinkedList<String>();
	private List<BarData> chartData = new LinkedList<BarData>();
	private List<DesireLineData> mDesireLineDataSet = new LinkedList<DesireLineData>();
	
	public BarChart03View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		chartLabels();
		chartDataSet();
		chartDesireLines();
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
				chart.setPadding(15, 20, 8, 10);
			}else{
				chart.setPadding(20, 20, 10, 8);
			}
					
			//标题
			chart.setChartTitle("小小熊 - 期末考试成绩");
			chart.setChartSubTitle("(XCL-Charts Demo)");	
			//数据源
			chart.setDataSource(chartData);
			chart.setLabels(chartLabels);	
			chart.setDesireLines(mDesireLineDataSet);
			
			//图例
			chart.getLegend().setLeftLegend("分数");
			chart.getLegend().setLowerLegend("科目");
			
			//数据轴
			chart.getDataAxis().setAxisMax(100);
			chart.getDataAxis().setAxisMin(0);
			chart.getDataAxis().setAxisSteps(5);		
			//指隔多少个轴刻度(即细刻度)后为主刻度
			chart.getDataAxis().setDetailModeSteps(4);
			
			//背景网格
			chart.getPlotGrid().setHorizontalLinesVisible(true);
									
			//横向显示柱形,如想看横向显示效果，可打开这两行的注释即可
			//chart.setChartDirection(XEnum.Direction.HORIZONTAL);
			//chart.getPlotGrid().setVerticalLinesVisible(true);
			
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
			
			//隐藏Key
			chart.setPlotKeyVisible(false);
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
		dataSeriesA.add(98d); 
		dataSeriesA.add(100d); 
		dataSeriesA.add(95d); 
		dataSeriesA.add(100d); 
		BarData BarDataA = new BarData("",dataSeriesA,(int)Color.rgb(53, 169, 239));
		
		
		chartData.add(BarDataA);
	}
	
	private void chartLabels()
	{
		chartLabels.add("语文"); 
		chartLabels.add("数学"); 
		chartLabels.add("英语"); 
		chartLabels.add("计算机"); 
	}	
	
	/**
	 * 期望线/分界线
	 */
	private void chartDesireLines()
	{				
		mDesireLineDataSet.add(new DesireLineData("及格线",60d,(int)Color.RED,7));		
		mDesireLineDataSet.add(new DesireLineData("优秀",90d,(int)Color.rgb(35, 172, 57),5));		
	}
	
}
