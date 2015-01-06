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
import java.util.List;

import org.xclcharts.chart.GaugeChart;
import org.xclcharts.view.GraphicalView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;

/**
 * @ClassName GaugeChart01View
 * @Description  仪表盘例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class GaugeChart01View  extends GraphicalView {

	private String TAG = "GaugeChart01View";
	private GaugeChart chart = new GaugeChart();
	
	private List<String> mLabels = new ArrayList<String>();
	private List<Pair> mPartitionSet = new ArrayList<Pair>();		
	private float mAngle = 0.0f;

	
	public GaugeChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();	
	}
		
	public GaugeChart01View(Context context, AttributeSet attrs){   
        super(context, attrs);   
        initView();
	 }
	 
	 public GaugeChart01View(Context context, AttributeSet attrs, int defStyle) {
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
        //xml中的设置:  android:layout_width="300dip"   
        //			   android:layout_height="300dip"           
        chart.setChartRange(w ,h );        
        //绘图区范围
        //左右各缩进10%
        //int offsetX = DensityUtil.dip2px(getContext(), (float) (300 * 0.1)); 
        //偏移高度的25%下来
        //int offsetY = DensityUtil.dip2px(getContext(), (float) (300 * 0.25));        
       // chart.setPadding(offsetY, 0, 0,  0);
     
     }  
	 
	
	//从seekbar传入的值
	public void setAngle(float currentAngle)
	{
		mAngle = currentAngle;
	}	
		
	public void chartRender()
	{
		try {								
						
			//设置标题
			//chart.setTitle("刻度盘 ");
								
			//刻度步长
			chart.setTickSteps(10d);
			
			//标签(标签和步长分开，步长即刻度可以密点，标签可以松点)					
			chart.setCategories(mLabels);					
			//分区
			chart.setPartition(mPartitionSet);
			
			//设置当前指向角度(0-180).
			//chart.setCurrentAngle(90f);
			chart.setCurrentAngle(mAngle);
			//绘制边框
			chart.showRoundBorder();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());
		}
		
	}
	
	//分区[角度(0-mStartAngle)，颜色]		
	private void chartDataSet()
	{
		int Angle = 180/3;
		mPartitionSet.add(new Pair<Float,Integer>((float)Angle, (int)Color.rgb(73, 172, 72)));
		mPartitionSet.add(new Pair<Float,Integer>((float)Angle, (int)Color.rgb(247, 156, 27)));
		mPartitionSet.add(new Pair<Float,Integer>((float)Angle, (int)Color.rgb(224, 62, 54)));
	}
	
	private void chartLabels()
	{
		//标签		
		mLabels.add("起始");
		mLabels.add("安全");
		mLabels.add("警惕");
		mLabels.add("危险");
		mLabels.add("终止");
	}

	
	@Override
    public void render(Canvas canvas) {
        try{
        	
            chart.render(canvas);
        } catch (Exception e){
        	Log.e(TAG, e.toString());
        }
    }
}
