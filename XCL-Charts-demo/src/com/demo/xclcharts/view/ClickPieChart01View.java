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
 * @version 1.2
 */
package com.demo.xclcharts.view;

import java.util.LinkedList;

import org.xclcharts.chart.PieChart;
import org.xclcharts.chart.PieData;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.event.click.ArcPosition;
import org.xclcharts.event.click.ChartArcListener;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.view.GraphicalView;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * @ClassName ClickPieChart01View
 * @Description  演示点击事件效果的例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class ClickPieChart01View extends GraphicalView {


	 private String TAG = "ClickPieChart01View";
	 private PieChart chart = new PieChart();	
	 private LinkedList<PieData> chartData = new LinkedList<PieData>();
	
	 private ChartArcListener onClickListener = null;;
	 
	 public ClickPieChart01View(Context context) {
		super(context);
		initView();
	 }	
	
	 public ClickPieChart01View(Context context, AttributeSet attrs){   
       super(context, attrs);   
       initView();
	 }
	 
	 public ClickPieChart01View(Context context, AttributeSet attrs, int defStyle) {
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
			//标签显示(隐藏，显示在中间，显示在扇区外面,折线注释方式)
			chart.setLabelStyle(XEnum.SliceLabelStyle.INSIDE);			
			
			//图的内边距
			
			//注释折线较长，缩进要多些
			int [] ltrb = new int[4];
			ltrb[0] = DensityUtil.dip2px(getContext(), 30); //left	
			ltrb[1] = DensityUtil.dip2px(getContext(), 55); //top	
			ltrb[2] = DensityUtil.dip2px(getContext(), 30); //right
			ltrb[3] = DensityUtil.dip2px(getContext(), 30); //bottom											
			chart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);
			
			
			//设定数据源
			chart.setDataSource(chartData);												
		
			//标题
			chart.setTitle("图表点击演示");
			chart.addSubtitle("(XCL-Charts Demo)");
			chart.setTitleVerticalAlign(XEnum.VerticalAlign.BOTTOM);
			
			chart.getLabelPaint().setTextSize(30);
			chart.getLabelPaint().setFakeBoldText(true);
			chart.getLabelPaint().setColor(Color.WHITE);
			
			//激活点击监听
			chart.ActiveListenItemClick();
			
			//标签
			chart.getPlotLabel().showBox();
			
			//显示标签框
			chart.getPlotLabel().showBox();
			chart.getPlotLabel().getBox().setBorderLineColor(Color.rgb(0, 126, 231));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());
		}
	}

	private void chartDataSet()
	{
		//设置图表数据源		
		
		chartData.add(new PieData("48","48%",45,Color.rgb(215, 124, 124)));
		chartData.add(new PieData("15","15%",15,Color.rgb(253, 180, 90)));
		chartData.add(new PieData("5","5%",5,Color.rgb(77, 83, 97)));
		chartData.add(new PieData("10","10%",10f,Color.rgb(253, 180, 90)));
		
		//将此比例块突出显示
		chartData.add(new PieData("其它","25%",25,Color.rgb(52, 194, 188),true));
	}
	
	@Override
	public void render(Canvas canvas) {
		// TODO Auto-generated method stub
		 try{
	            chart.render(canvas);
	        } catch (Exception e){
	        	Log.e(TAG, e.toString());
	        }
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub		
		//super.onTouchEvent(event);		
		if(event.getAction() == MotionEvent.ACTION_UP) 
		{									
			triggerClick(event.getX(),event.getY());	
		}
		return true;
	}
	

	//触发监听
	private void triggerClick(float x,float y)
	{			
		
		ArcPosition record = chart.getPositionRecord(x,y);			
		if( null == record) return;
		
		PieData pData = chartData.get(record.getDataID());											
		Toast.makeText(this.getContext(),								
				"[此处为View返回的信息] key:" +  pData.getKey() +
				" Label:" + pData.getLabel() ,
				Toast.LENGTH_SHORT).show();
		
		if(null != onClickListener)
				onClickListener.onClick(new PointF(x,y), record);	
	}
	
	
	//请注意:
	// 此处是饼图所以监听为 ChartArcListener
	// 如为柱图则监听为 ChartBarListener
	// 如为线图或雷达图，则监听为ChartPointListener
	public void setOnPlotClickListener(ChartArcListener chartListener) 
	{
	  this.onClickListener = chartListener;
	 }
	


}