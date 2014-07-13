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
import java.util.LinkedList;
import java.util.List;

import org.xclcharts.chart.DountChart;
import org.xclcharts.chart.PieData;
import org.xclcharts.renderer.XChart;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;

/**
 * @ClassName DountChart01View
 * @Description  环形图例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class DountChart01View extends TouchView {

	private String TAG = "DountChart01View";
	private DountChart chart = new DountChart();
	
	LinkedList<PieData> lPieData = new LinkedList<PieData>();
	
	public DountChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}
	
	public DountChart01View(Context context, AttributeSet attrs){   
        super(context, attrs);   
        initView();
	 }
	 
	 public DountChart01View(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView();
	 }
	 
	 private void initView()
	 {
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
			chart.setPadding(20, 30, 15, 15);
			*/
			
			//设置绘图区默认缩进px值
			chart.setPadding(getChartTop(), getChartBottom(), getChartLeft(), getChartRight());
							
			//设置起始偏移角度
			chart.setInitialAngle(90);	
			
			//数据源
			chart.setDataSource(lPieData);
						
			//标签显示(隐藏，显示在中间，显示在扇区外面)
			chart.setLabelPosition(XEnum.SliceLabelPosition.OUTSIDE);
			
			//标题
			chart.setTitle("Dount Chart");
			chart.addSubtitle("(XCL-Charts Demo)");
			//显示key
			chart.getPlotKey().showKeyLabels();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());
		}
	}
	private void chartDataSet()
	{
		//设置图表数据源				
		//PieData(标签，百分比，在饼图中对应的颜色)
		lPieData.add(new PieData("Solaris","20%",20,(int)Color.rgb(77, 83, 97)));
		lPieData.add(new PieData("Aix","30%",30,(int)Color.rgb(148, 159, 181)));
		lPieData.add(new PieData("HP-UX","10%",10,(int)Color.rgb(253, 180, 90)));		
		lPieData.add(new PieData("Linux","40%",40,(int)Color.rgb(52, 194, 188)));
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
