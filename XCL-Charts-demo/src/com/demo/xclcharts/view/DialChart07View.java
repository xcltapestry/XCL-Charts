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
 * @Description Android图表基类库演示
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version 1.3
 */
package com.demo.xclcharts.view;

import java.util.ArrayList;
import java.util.List;

import org.xclcharts.chart.DialChart;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.view.GraphicalView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;

public class DialChart07View extends GraphicalView {

	private String TAG = "DialChart07View";	
	
	private DialChart chart = new DialChart();
	private DialChart chart180 = new DialChart();
	private DialChart chart90 = new DialChart();		
	private float mPercentage = 0.9f;
	
	public DialChart07View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}
	
	public DialChart07View(Context context, AttributeSet attrs){   
        super(context, attrs);   
        initView();
	 }
	 
	 public DialChart07View(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView();
	 }
	 
	 
	 private void initView()
	 {
		chartRender();
		chartRender90();
		chartRender180();
	 }
	 
	 @Override  
	    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
	        super.onSizeChanged(w, h, oldw, oldh);  
	        chart.setChartRange(w ,h ); 
	        chart180.setChartRange(w/2 ,h/3 ); 
	        chart90.setChartRange(w ,h/3 ); 
	    }  		
						
		public void chartRender()
		{
			try {								
							
				chart.setPadding(0, DensityUtil.dip2px(getContext(), 100), 0, 0);
				
				//设置标题背景			
				chart.setApplyBackgroundColor(true);
				chart.setBackgroundColor( Color.rgb(28, 129, 243) );
				//绘制边框
				chart.showRoundBorder();
				
				chart.setTotalAngle(270f);
						
				//设置当前百分比
				chart.getPointer().setPercentage(mPercentage);
				
				//设置指针长度
				chart.getPointer().setLength(0.65f,0.2f);	
				
				//增加轴
				addAxis();						
				/////////////////////////////////////////////////////////////
				//增加指针
				addPointer();
				//设置附加信息
				addAttrInfo();
				/////////////////////////////////////////////////////////////
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.toString());
			}
			
		}
		
				
		public void chartRender180()
		{
			try {	
				chart180.setTotalAngle(180f);
				chart180.setStartAngle(180f);
						
				//设置当前百分比
				chart180.getPointer().setPercentage(mPercentage);
				
				//设置指针长度
				chart180.getPointer().setPointerStyle(XEnum.PointerStyle.TRIANGLE);
				chart180.getPointer().setLength(0.65f,0.2f);	
				
				List<Float> ringPercentage = new ArrayList<Float>();			
				float rper = MathHelper.getInstance().div(1, 4); //相当于40%	//270, 4
				ringPercentage.add(rper);
				ringPercentage.add(rper);
				ringPercentage.add(rper);
				ringPercentage.add(rper);
				
				List<Integer> rcolor  = new ArrayList<Integer>();			
				rcolor.add(Color.rgb(242, 110, 131));
				rcolor.add(Color.rgb(238, 204, 71));
				rcolor.add(Color.rgb(42, 231, 250));
				rcolor.add(Color.rgb(140, 196, 27));						
				chart180.addStrokeRingAxis(0.75f,0.6f, ringPercentage, rcolor);
				chart180.getPlotAxis().get(0).getFillAxisPaint().setColor(Color.rgb(28, 129, 243) );
				
				Paint paintTB = new Paint();
				paintTB.setColor(Color.WHITE);
				paintTB.setTextAlign(Align.CENTER);
				paintTB.setTextSize(22);	
				paintTB.setAntiAlias(true);	
				chart180.getPlotAttrInfo().addAttributeInfo(XEnum.Location.BOTTOM, "180度仪表盘", 0.5f, paintTB);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.toString());
			}
			
		}
		
		public void chartRender90()
		{
			try {
				
				chart90.setPadding(DensityUtil.dip2px(getContext(), 150),0, 0, 0);
				chart90.setTotalAngle(90f);
				chart90.setStartAngle(270f);
								
				List<Float> ringPercentage = new ArrayList<Float>();			
				float rper = MathHelper.getInstance().div(1, 2); //相当于40%	//270, 4
				ringPercentage.add(rper);
				ringPercentage.add(rper);
				
				List<Integer> rcolor  = new ArrayList<Integer>();			
				rcolor.add(Color.rgb(242, 110, 131));
				rcolor.add(Color.rgb(238, 204, 71));				
				chart90.addStrokeRingAxis(0.75f,0.6f, ringPercentage, rcolor);				
				chart90.getPlotAxis().get(0).getFillAxisPaint().setColor(Color.rgb(28, 129, 243) );			
				chart90.getPointer().setLength(0.65f);	

				Paint paintTB = new Paint();
				paintTB.setColor(Color.WHITE);
				paintTB.setTextAlign(Align.CENTER);
				paintTB.setTextSize(22);	
				paintTB.setAntiAlias(true);	
				chart90.getPlotAttrInfo().addAttributeInfo(XEnum.Location.BOTTOM, "90度仪表盘", 0.5f, paintTB);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.toString());
			}
			
		}
		
		public void addAxis()
		{
			
			List<String> rlabels2  = new ArrayList<String>();
			for(int i=0;i<7;i++)
			{						
				rlabels2.add(Integer.toString(i * 10));			
			}
			chart.addInnerTicksAxis(0.7f, rlabels2);
			chart.getPlotAxis().get(0).getAxisPaint().setColor(Color.WHITE);
			chart.getPlotAxis().get(0).getAxisPaint().setStrokeWidth(8);
			chart.getPlotAxis().get(0).getTickMarksPaint().setColor(Color.WHITE);
			chart.getPlotAxis().get(0).getTickLabelPaint().setColor(Color.WHITE);
			
			List<String> rlabels3  = new ArrayList<String>();
			for(int i=0;i<5;i++)
			{
				if(0 == i)
				{
					rlabels3.add("");	
				}else					
					rlabels3.add(Integer.toString(i * 10));			
			}
			
			chart.addOuterTicksAxis(0.8f, rlabels3);
			chart.getPlotAxis().get(1).getAxisPaint().setColor(Color.RED);
			chart.getPlotAxis().get(1).getAxisPaint().setStrokeWidth(5);
													
			chart.getPointer().setPointerStyle(XEnum.PointerStyle.TRIANGLE);			
			chart.getPointer().getPointerPaint().setStrokeWidth(3);			
			chart.getPointer().getPointerPaint().setStyle(Style.FILL);		
	
			chart.getPointer().getPointerPaint().setColor(Color.rgb(242, 110, 131));		
			chart.getPointer().getBaseCirclePaint().setColor(Color.rgb(238, 204, 71));
			chart.getPointer().setBaseRadius(10f);
			
		}
		
		//增加指针
		public void addPointer()
		{							
		}
		
		
		private void addAttrInfo()
		{			
			Paint paintTB = new Paint();
			paintTB.setColor(Color.WHITE);
			paintTB.setTextAlign(Align.CENTER);
			paintTB.setTextSize(22);	
			paintTB.setAntiAlias(true);	
			chart.getPlotAttrInfo().addAttributeInfo(
						XEnum.Location.BOTTOM, "270度仪表盘", 0.5f, paintTB);
		}
		
		public void setCurrentStatus(float percentage)
		{								
			mPercentage =  percentage;
			
			//清理
			chart.clearAll();
			chart90.clearAll();
			chart180.clearAll();
			
			//设置当前百分比
			chart.getPointer().setPercentage(mPercentage);
			addAxis();
			//增加指针
			addPointer();
			addAttrInfo();
			
			chartRender180();
			chartRender90();
			chart90.getPointer().setPercentage(mPercentage);
			chart180.getPointer().setPercentage(mPercentage);
		}


		@Override
		public void render(Canvas canvas) {
			// TODO Auto-generated method stub
			 try{
				 	chart.render(canvas);
		            chart90.render(canvas);
		            chart180.render(canvas);
		            		           
		        } catch (Exception e){
		        	Log.e(TAG, e.toString());
		        }
		}

}

