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
 * @version 2.1
 */
package com.demo.xclcharts.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.xclcharts.chart.PointD;
import org.xclcharts.chart.SplineChart;
import org.xclcharts.chart.SplineData;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.XChart;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.view.ChartView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.util.Log;

/**
 * @ClassName DySplineChart
 * @Description  双线图例子 
 *  
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */

public class DySplineChart extends ChartView{

	private String TAG = "DySplineChart";
		
	private SplineChart chart1 = new SplineChart();
	//分类轴标签集合
	private LinkedList<String> labels = new LinkedList<String>();
	private LinkedList<SplineData> chartData = new LinkedList<SplineData>();
	
			
	private SplineChart chart2 = new SplineChart();
	private LinkedList<String> labels2 = new LinkedList<String>();
	private LinkedList<SplineData> chartData2 = new LinkedList<SplineData>();
				
	private SplineData dataSeries1 = null;	
	private SplineData dataSeries2 = null;
	
	final static int CHART1_X_TIME = 120;
	final static int CHART2_X_TIME = 60;
	
	
	public DySplineChart(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}
	
	 public DySplineChart(Context context, AttributeSet attrs){   
	        super(context, attrs);   
	        initView();
	 }
	 
	 public DySplineChart(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView();
	 }
	 	 		
	 @Override  
	  protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
	       super.onSizeChanged(w, h, oldw, oldh);  
	    
	       // android:layout_height="600dip" 
	  
	        float spad = DensityUtil.dip2px(getContext(), 15);
	        float top =  DensityUtil.dip2px(getContext(), 5);
	        
	        float viewHeight = DensityUtil.dip2px(getContext(),h);
	        viewHeight /=2;
	        
	    
	        float chart1Height = viewHeight / 5 * 3;
	        float row =  DensityUtil.dip2px(getContext(), 20);
	        chart1Height -= top;
	        chart1Height -= row;	        
	        chart1.setChartRange(0, top,w,chart1Height);
	     	        	       
	        float chart2Height = viewHeight / 5 * 2;		        	        
	        chart2.setChartRange(0,row+chart1Height,w,chart2Height);
	  
	        chart1.setPadding(spad, 0.f, spad, 0.f);
	        chart2.setPadding(spad, 0.f, spad, 0.f);
	  }
	 

	 
	 private void initView()
	 {		
			 if(null == dataSeries1)dataSeries1 = new SplineData(); 
			 if(null == dataSeries2)dataSeries2 = new SplineData();
			 
			 dataSeries1.setDotStyle(XEnum.DotStyle.DOT);
			 dataSeries1.setLineColor((int)Color.rgb(54, 141, 238));
			 
			 dataSeries2.setDotStyle(XEnum.DotStyle.RING);
			 dataSeries2.setLineColor((int)Color.rgb(255, 165, 132));
			 
			 //把线弄细点
			 dataSeries1.getLinePaint().setStrokeWidth(2);
			 dataSeries2.getLinePaint().setStrokeWidth(2);
			 
			 //设定数据源		
			 chartData.add(dataSeries1);
			 chartData2.add(dataSeries2);
			
			chartLabels();	
			chartRender();
			
			chart2Labels();
			chart2Render();
			
			//chart1.disablePanMode();
			//chart2.disablePanMode();
			
			//chart1.getCategoryAxis().hide();
			chart1.setAxesClosed(true);
			
			//chart2.getCategoryAxis().hide();
			chart2.setAxesClosed(true);
	 }
	 
	 
	 private void chartRender()
		{
			try {									
				//chart1.setApplyBackgroundColor(true);
				//chart1.setBackgroundColor(Color.RED);
				
				//数据源	
				chart1.setCategories(labels);
				chart1.setDataSource(chartData);
							
				//坐标系
				//数据轴最大值
				chart1.getDataAxis().setAxisMax(240);
				chart1.getDataAxis().setAxisMin(30);
				//数据轴刻度间隔
				chart1.getDataAxis().setAxisSteps(10);
				
				//标签轴最大值
				chart1.setCategoryAxisMax(CHART1_X_TIME);	//120秒
				//标签轴最小值
				chart1.setCategoryAxisMin(0);	
						
				chart1.getDataAxis().getAxisPaint().setColor((int)Color.rgb(127, 204, 204));
				chart1.getCategoryAxis().getAxisPaint().setColor((int)Color.rgb(127, 204, 204));
				
				chart1.getDataAxis().getTickMarksPaint().setColor((int)Color.rgb(127, 204, 204));
				chart1.getCategoryAxis().getTickMarksPaint().setColor((int)Color.rgb(127, 204, 204));
				
				//居中
				chart1.getDataAxis().setHorizontalTickAlign(Align.CENTER);
				chart1.getDataAxis().getTickLabelPaint().setTextAlign(Align.CENTER);
				
				//居中显示轴 
				chart1.setDataAxisLocation(XEnum.AxisLocation.VERTICAL_CENTER);
				chart1.setCategoryAxisLocation(XEnum.AxisLocation.HORIZONTAL_CENTER);
			
				//标题
				//chart1.setTitle("Spline Chart");
				//chart1.addSubtitle("(XCL-Charts Demo)");
				
				chart1.getPlotLegend().hide();
				
				chart1.disableScale();
				chart1.setPlotPanMode(XEnum.PanMode.HORIZONTAL);
							
				chart1.setCrurveLineStyle(XEnum.CrurveLineStyle.BEELINE);
				
				//chart1.getDataAxis().hideAxisLine();
				chart1.getDataAxis().hideTickMarks();
				
				chart1.getPlotGrid().showHorizontalLines();
				chart1.getPlotGrid().showVerticalLines();
				
				//定义数据轴标签显示格式
				chart1.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){
		
					@Override
					public String textFormatter(String value) {
						// TODO Auto-generated method stub		
						Double tmp = Double.parseDouble(value);
											
						String label = "";
						if(tmp == 0 || tmp <= chart1.getDataAxis().getAxisMin())
						{
							label = tmp.toString();
							
						}else{
							Integer k = (int) (tmp%30);
							if( 0 == k)
							{
								DecimalFormat df=new DecimalFormat("#0");
								label = df.format(tmp).toString();
							}
						}						
						return label;
					}
					
				});
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e(TAG, e.toString());
			}
			
			
		}
	 
	 private void chart2Render()
		{
			try {
				//chart2.setApplyBackgroundColor(true);
				//chart2.setBackgroundColor(Color.GREEN);
				
				//数据源	
				chart2.setCategories(labels2);
				chart2.setDataSource(chartData2);
							
				//坐标系
				//数据轴最大值
				chart2.getDataAxis().setAxisMax(100); 
				//chart2.getDataAxis().setAxisMin(0);
				//数据轴刻度间隔
				chart2.getDataAxis().setAxisSteps(20);
				
				//标签轴最大值
				chart2.setCategoryAxisMax(CHART2_X_TIME);	//60秒
				//标签轴最小值
				chart2.setCategoryAxisMin(0);	
						
				chart2.getDataAxis().getAxisPaint().setColor((int)Color.rgb(127, 204, 204));
				chart2.getCategoryAxis().getAxisPaint().setColor((int)Color.rgb(127, 204, 204));
				
				chart2.getDataAxis().getTickMarksPaint().setColor((int)Color.rgb(127, 204, 204));
				chart2.getCategoryAxis().getTickMarksPaint().setColor((int)Color.rgb(127, 204, 204));
				
				//居中
				chart2.getDataAxis().setHorizontalTickAlign(Align.CENTER);
				chart2.getDataAxis().getTickLabelPaint().setTextAlign(Align.CENTER);
				
				//居中显示轴 
				chart2.setDataAxisLocation(XEnum.AxisLocation.VERTICAL_CENTER);
				chart2.setCategoryAxisLocation(XEnum.AxisLocation.HORIZONTAL_CENTER);
									
				chart2.getPlotLegend().hide();
				chart2.disableScale();
				chart2.setPlotPanMode(XEnum.PanMode.HORIZONTAL);
								
				//chart2.getDataAxis().hideAxisLine();
				chart2.getDataAxis().hideTickMarks();
				
				chart2.getPlotGrid().showHorizontalLines();
				chart2.getPlotGrid().showVerticalLines();
				
				//定义数据轴标签显示格式
				chart2.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){
		
					@Override
					public String textFormatter(String value) {
						// TODO Auto-generated method stub		
						Double tmp = Double.parseDouble(value);
											
						String label = "";
						if(tmp == 0 || tmp <= chart2.getDataAxis().getAxisMin())
						{
							label = tmp.toString();
							
						}else{
							Integer k = (int) (tmp%20);
							if( 0 == k)
							{
								DecimalFormat df=new DecimalFormat("#0");
								label = df.format(tmp).toString();
							}
						}						
						return label;
					}
					
				});
				
				//激活点击监听
				chart2.ActiveListenItemClick();
				//为了让触发更灵敏，可以扩大5px的点击监听范围
				chart2.extPointClickRange(5);
				chart2.showClikedFocus();
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e(TAG, e.toString());
			}
		}
	
		private void chartLabels()
		{
			for(int i=0;i<60;i+= 10)
			{
				labels.add(Integer.toString(i));
			}
		}
		
		private void chart2Labels()
		{
			for(int i=0;i<10;i+=2)
			{
				labels2.add(Integer.toString(i));
			}
		}
				
		public void refreshChart(List<PointD> linePoint1,
				                 List<PointD> linePoint2){
			
			dataSeries1.setLineDataSet(linePoint1);
			dataSeries2.setLineDataSet(linePoint2);
			this.invalidate();
		}
		

		@Override
	    public void render(Canvas canvas) {
	        try{
	        	
	        	//canvas.drawColor(Color.GRAY);
	            chart1.render(canvas);
	            chart2.render(canvas);
	        } catch (Exception e){
	        	Log.e(TAG, e.toString());
	        }
	    }

		@Override
		public List<XChart> bindChart() {
			// TODO Auto-generated method stub		
			List<XChart> lst = new ArrayList<XChart>();
			lst.add(chart1);		
			lst.add(chart2);	
			return lst;
		}
}
