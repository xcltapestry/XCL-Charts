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

import org.xclcharts.chart.BarChart3D;
import org.xclcharts.chart.BarData;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.event.click.BarPosition;
import org.xclcharts.renderer.XChart;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * @ClassName Bar3DChart02View
 * @Description  3D柱形图例子(横向)
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class BarChart3D02View extends TouchView {
	
	private String TAG = "Bar3DChart02View";
	private BarChart3D chart = new BarChart3D();
	
	//轴数据源
	private List<String> chartLabels = new LinkedList<String>();
	private List<BarData> chartData = new LinkedList<BarData>();
	
	public BarChart3D02View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub	
		initView();
	}
	
	public BarChart3D02View(Context context, AttributeSet attrs){   
        super(context, attrs);   
        initView();
	 }
	 
	 public BarChart3D02View(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView();
	 }
	 
	 private void initView()
	 {
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
			//设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....	
			int [] ltrb = getBarLnDefaultSpadding();
			chart.setPadding(DensityUtil.dip2px(getContext(), 40),ltrb[1], ltrb[2],  ltrb[3]);			
			
			//指定显示为横向3D柱形
			chart.setChartDirection(XEnum.Direction.HORIZONTAL);
			
			//数据源			
			chart.setDataSource(chartData);
			chart.setCategories(chartLabels);	
			
			//坐标系
			chart.getDataAxis().setAxisMax(50);
			chart.getDataAxis().setAxisMin(10);
			chart.getDataAxis().setAxisSteps(10);
			chart.getCategoryAxis().setTickLabelRotateAngle(-45f);
			
			//标题
			chart.setTitle("本月原料进货情况");
			chart.addSubtitle("(XCL-Charts Demo)");		
			
			//轴标题
			chart.getAxisTitle().setLeftAxisTitle("原料");
			chart.getAxisTitle().setLowerAxisTitle("进货量");
			
			//背景网格
			chart.getPlotGrid().showHorizontalLines();
			chart.getPlotGrid().showVerticalLines();
			chart.getPlotGrid().showEvenRowBgColor();
			
			//定义数据轴标签显示格式
			chart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){
	
				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub		
					Double tmp = Double.parseDouble(value);
					DecimalFormat df=new DecimalFormat("#0");
					String label = df.format(tmp).toString();				
					return (label +"吨");
				}
				
			});
		
			//定义柱形上标签显示格式
			chart.getBar().setItemLabelVisible(true);
			chart.setItemLabelFormatter(new IFormatterDoubleCallBack() {
				@Override
				public String doubleFormatter(Double value) {
					// TODO Auto-generated method stub
					DecimalFormat df=new DecimalFormat("#0.00");					 
					String label = df.format(value).toString();
					return label;
				}});	       
		
			//定义基座颜色
			chart.setAxis3DBaseColor((int)Color.rgb(132, 162, 197));
			
			//激活点击监听
			chart.ActiveListenItemClick();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());
		}
	}

	private void chartDataSet()
	{
		//标签对应的柱形数据集
		List<Double> dataSeriesA= new LinkedList<Double>();	
		dataSeriesA.add(20d);
		dataSeriesA.add(28d);
		dataSeriesA.add(45d); 

		List<Double> dataSeriesB= new LinkedList<Double>();	
		dataSeriesB.add(30d);
		dataSeriesB.add(17d); 
		dataSeriesB.add(35d); 
		
		chartData.add(new BarData("湖南",dataSeriesA,(int)Color.rgb(224, 62, 54)));
		chartData.add(new BarData("福建",dataSeriesB,(int)Color.rgb(140, 71, 222)));
	}
	
	private void chartLabels()
	{	
		chartLabels.add("芝麻"); 
		chartLabels.add("茶叶"); 
		chartLabels.add("花生"); 
		
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
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub		
		super.onTouchEvent(event);		
		if(event.getAction() == MotionEvent.ACTION_UP) 
		{			
			triggerClick(event.getX(),event.getY());	
		}
		return true;
	}
	
	
	//触发监听
	private void triggerClick(float x,float y)
	{
		BarPosition record = chart.getPositionRecord(x,y);			
		if( null == record) return;
		
		BarData bData = chartData.get(record.getDataID());					
		Double bValue = bData.getDataSet().get(record.getDataChildID());			

		Toast.makeText(this.getContext(),
				"info:" + record.getRectInfo() +
				" Key:" + bData.getKey() + 							
				" Current Value:" + Double.toString(bValue), 
				Toast.LENGTH_SHORT).show();			
	}
	
}
