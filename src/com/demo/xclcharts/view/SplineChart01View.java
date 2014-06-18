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

import java.util.LinkedHashMap;
import java.util.LinkedList;

import android.graphics.Canvas;
import org.xclcharts.chart.SplineChart;
import org.xclcharts.chart.SplineData;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.axis.DataAxis;
import org.xclcharts.renderer.axis.LabelsAxis;
import org.xclcharts.renderer.plot.PlotGrid;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
/**
 * @ClassName SplineChart01View
 * @Description  曲线图 的例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class SplineChart01View extends GraphicalView {

	//标签轴标签集合
	private LinkedList<String> lables = new LinkedList<String>();
	private LinkedList<SplineData> chartData = new LinkedList<SplineData>();
    SplineChart chart = new SplineChart();

	public SplineChart01View(Context context) {
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
			chart.setChartRange(0.0f, 0.0f, getScreenWidth(), getScreenHeight());
			chart.setPadding(20, 20, 13, 5);
			
			//数据源	
			chart.setLabels(lables);
			chart.setDataSource(chartData);
						
			//坐标系
			//数据轴最大值
			chart.getDataAxis().setAxisMax(100);
			//chart.getDataAxis().setAxisMin(0);
			//数据轴刻度间隔
			chart.getDataAxis().setAxisSteps(10);
			
			//标签轴最大值
			chart.setLabelsAxisMax(100);	
			//标签轴最小值
			chart.setLabelsAxisMin(0);	
			
			//设置图的背景色
			//chart.setBackgroupColor(true,Color.BLACK);
			//设置绘图区的背景色
			//chart.getPlotArea().setBackgroupColor(true, Color.WHITE);
			
			//背景网格
			PlotGrid plot = chart.getPlotGrid();			
			plot.setHorizontalLinesVisible(true);
			plot.setVerticalLinesVisible(true);			
			plot.getHorizontalLinesPaint().setStrokeWidth(3);
			plot.getHorizontalLinesPaint().setColor((int)Color.rgb(127, 204, 204));			
			plot.setHorizontalLinesDashStyle(XEnum.LineDashStyle.DOT);
		
			
			//标题
			chart.setChartTitle("Spline Chart");
			chart.setChartSubTitle("(XCL-Charts Demo)");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("ERROR-SplineChart01View", e.toString());
		}
	}
	private void chartDataSet()
	{
		//线1的数据集
		LinkedHashMap<Double,Double> linePoint1 = new LinkedHashMap<Double,Double>();
		linePoint1.put(5d, 8d);
		
		linePoint1.put(12d, 12d);
		linePoint1.put(25d, 15d);
		linePoint1.put(30d, 30d);
		linePoint1.put(45d, 25d);
		
		linePoint1.put(55d, 33d);
		linePoint1.put(62d, 45d);
		SplineData dataSeries1 = new SplineData("青菜萝卜够吃",linePoint1,
				(int)Color.rgb(54, 141, 238) );
		//线2的数据集
		LinkedHashMap<Double,Double> linePoint2 = new LinkedHashMap<Double,Double>();
		linePoint2.put(40d, 50d);
		linePoint2.put(55d, 55d);
		linePoint2.put(60d, 65d);
		linePoint2.put(65d, 85d);		
		
		linePoint2.put(72d, 70d);	
		linePoint2.put(85d, 68d);	
		SplineData dataSeries2 = new SplineData("饭管够",linePoint2,
				(int)Color.rgb(255, 165, 132) );
		
		dataSeries2.setLineLabelVisible(true);		
		dataSeries2.setDotStyle(XEnum.DotStyle.RECT);				
		dataSeries2.getPlotLabelsPaint().setColor(Color.RED);
						
		//设定数据源		
		chartData.add(dataSeries1);				
		chartData.add(dataSeries2);	
	}
	
	private void chartLabels()
	{
		lables.add("5:52:33");
		lables.add("5:52:35");
		lables.add("5:52:37");
		lables.add("5:52:39");
		lables.add("5:52:41");
		lables.add("5:52:43");
		lables.add("5:52:45");
	}

}
