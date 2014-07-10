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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.xclcharts.chart.SplineChart;
import org.xclcharts.chart.SplineData;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.XChart;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.plot.PlotGrid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;


/**
 * @ClassName SplineChart02View
 * @Description  三角函数曲线图 的例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class SplineChart02View extends TouchView {

	private String TAG = "SplineChart02View";
	private SplineChart chart = new SplineChart();
	//分类轴标签集合
	private LinkedList<String> labels = new LinkedList<String>();
	private LinkedList<SplineData> chartData = new LinkedList<SplineData>();
	
	public SplineChart02View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		chartLabels();
		chartDataSet();	
		chartRender();
	}
	
	@Override  
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
        super.onSizeChanged(w, h, oldw, oldh);  
       //图所占范围大小
        chart.setChartRange(w,h);
    }  				
	
	private void chartRender()
	{
		try {
						
			//图所占范围大小
			/*
			chart.setChartRange(0.0f, 0.0f,getScreenWidth(),getScreenHeight());		
			chart.setPadding(15, 20, 13, 5);
			*/
			
			chart.setPadding(getChartTop(), getChartBottom(), getChartLeft(), getChartRight());
			
			//数据源	
			chart.setCategories(labels);
			chart.setDataSource(chartData);
						
			//坐标系
			//数据轴最大值
			chart.getDataAxis().setAxisMax(1);
			chart.getDataAxis().setAxisMin(-1);
			//数据轴刻度间隔
			chart.getDataAxis().setAxisSteps(0.5);
			
			//标签轴最大值
			chart.setCategoryAxisMax(360);	
			//标签轴最小值
			chart.setCategoryAxisMin(0);	
			
		
			
			//设置图的背景色
			//chart.setBackgroupColor(true,Color.BLACK);
			//设置绘图区的背景色
			//chart.getPlotArea().setBackgroupColor(true, Color.WHITE);
			
			//背景网格
			PlotGrid plot = chart.getPlotGrid();			
			plot.showHorizontalLines();
			plot.showVerticalLines();			
			plot.getHorizontalLinePaint().setStrokeWidth(3);
			plot.getHorizontalLinePaint().setColor((int)Color.rgb(127, 204, 204));			
			plot.setHorizontalLineStyle(XEnum.LineStyle.DOT);
		
			
			//把轴线设成和横向网络线一样和大小和颜色,演示下定制性，这块问得人较多
			chart.getDataAxis().getAxisPaint().setStrokeWidth(
					plot.getHorizontalLinePaint().getStrokeWidth());
			chart.getCategoryAxis().getAxisPaint().setStrokeWidth(
					plot.getHorizontalLinePaint().getStrokeWidth());
			
			chart.getDataAxis().getAxisPaint().setColor(
					plot.getHorizontalLinePaint().getColor());
			chart.getCategoryAxis().getAxisPaint().setColor(
					plot.getHorizontalLinePaint().getColor());
			
			chart.getDataAxis().getTickMarksPaint().setColor(
					plot.getHorizontalLinePaint().getColor());
			chart.getCategoryAxis().getTickMarksPaint().setColor(
					plot.getHorizontalLinePaint().getColor());
			
			
			//定义交叉点标签显示格式,特别备注,因曲线图的特殊性，所以返回格式为:  x值,y值
			//请自行分析定制
			chart.setDotLabelFormatter(new IFormatterTextCallBack(){
	
				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub						
					String label = "("+value+")";				
					return (label);
				}
				
			});
			//标题
			chart.setTitle("三角函数曲线图");
			chart.addSubtitle("(XCL-Charts Demo)");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, e.toString());
		}
	}
	private void chartDataSet()
	{

		LinkedHashMap<Double,Double> lineSin = new LinkedHashMap<Double,Double>();
		LinkedHashMap<Double,Double> lineCos = new LinkedHashMap<Double,Double>();
		
		int step = 2;
		int count = 360 / step + 1;
		for (int i = 0; i < count; i++) 
		{
			int angle = i * step; 
			double rAngle = Math.toRadians(angle);
			
			lineSin.put((double) angle, Math.sin(rAngle));	
			lineCos.put((double) angle, Math.cos(rAngle));	
		}
		
		SplineData dataSeriesSin = new SplineData("Sin",lineSin,(int)Color.rgb(54, 141, 238) );						
		SplineData dataSeriesCos = new SplineData("Cos",lineCos,(int)Color.rgb(255, 165, 132) );
		
		dataSeriesSin.setDotStyle(XEnum.DotStyle.HIDE);
		dataSeriesCos.setDotStyle(XEnum.DotStyle.HIDE);
		
		//设定数据源		
		chartData.add(dataSeriesSin);				
		chartData.add(dataSeriesCos);	
		
	}
	
	private void chartLabels()
	{		
		for(int i=0;i<=360;)
		{			 
			labels.add(Integer.toString(i));
			i+=45;
		}
		
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
