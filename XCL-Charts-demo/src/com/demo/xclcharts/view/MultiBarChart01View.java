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
 * @version 1.0
 */
package com.demo.xclcharts.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.xclcharts.chart.BarChart;
import org.xclcharts.chart.BarData;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.XChart;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


/**
 * @ClassName MultiBarChart01View
 * @Description  展示柱形图的定制性
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */

public class MultiBarChart01View extends DemoView {
	
	private String TAG = "MultiBarchart201View";
	private BarChart chart = new BarChart();
	private BarChart chart2 = new BarChart();
	
	//标签轴
	private List<String> chartLabels = new LinkedList<String>();
	private List<BarData> chartData = new LinkedList<BarData>();
	private List<BarData> chartData2 = new LinkedList<BarData>();
	
	private int axisColor = Color.rgb(125, 223, 252);
	
	public MultiBarChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();				
	}
	
	public MultiBarChart01View(Context context, AttributeSet attrs){   
        super(context, attrs);   
        initView();
	 }
	 
	 public MultiBarChart01View(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView();
	 }
	 
	 private void initView()
	 {
		 	chartLabels();
			chartDataSet();
			chartDataSet2();
			chartRender();
			chartRender2();
			
	 }
	
	@Override  
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
        super.onSizeChanged(w, h, oldw, oldh);  
       //图所占范围大小
        chart.setChartRange(w,h);
        chart2.setChartRange(w,h);
    }  
	
	
	private void chartRender()
	{
		try {								
			//设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....		
			int [] ltrb = getBarLnDefaultSpadding();
			chart.setPadding(DensityUtil.dip2px(getContext(), 45),ltrb[1], ltrb[2],  ltrb[3]);			
		
			//数据源
			chart.setDataSource(chartData);
			chart.setCategories(chartLabels);				
			
			//数据轴
			chart.getDataAxis().setAxisMax(2500);
			chart.getDataAxis().setAxisMin(0);
			chart.getDataAxis().setAxisSteps(500);
								
			 //让柱子间没空白
			 chart.getBar().setBarInnerMargin(0d);
			 
			 //隐藏轴
			 chart.getDataAxis().setVisible(false);
			 chart.getCategoryAxis().setVisible(false);
						 
			 //将Bar风格设为Fill
			 chart.getBar().setBarStyle(XEnum.BarStyle.FILL);
			 
			 chart.setApplyBackgroundColor(true);
			 chart.setBackgroundColor(Color.rgb(19, 163, 224));						 
			 chart.showRoundBorder();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());
		}
	}
	
	
	private void chartRender2()
	{
		try {									 
			//设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....		
			int [] ltrb = getBarLnDefaultSpadding();
			chart2.setPadding(DensityUtil.dip2px(getContext(), 45),ltrb[1], ltrb[2], ltrb[3] );				
			
			//显示边框
			//chart2.showRoundBorder();		
			
			chart2.getBar().setBarStyle(XEnum.BarStyle.FILL);		
			
			
			//标题
			chart2.setTitle("负债率标准: 40%~60%");
			chart2.addSubtitle("(XCL-Charts Demo)");	
			chart2.getPlotTitle().getTitlePaint().setColor(axisColor);
			chart2.getPlotTitle().getSubtitlePaint().setColor(axisColor);
			//数据源
			chart2.setDataSource(chartData2);
			chart2.setCategories(chartLabels);	
			
			//轴标题
			chart2.getAxisTitle().setLeftAxisTitle("金额");
			chart2.getAxisTitle().setLowerAxisTitle("资产负债率");
			
			//数据轴
			chart2.getDataAxis().setAxisMax(2500);
			chart2.getDataAxis().setAxisMin(0);
			chart2.getDataAxis().setAxisSteps(500);
			
		
			//定义数据轴标签显示格式
			chart2.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){
	
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
			chart2.getBar().setItemLabelVisible(true);
			//设定格式
			chart2.setItemLabelFormatter(new IFormatterDoubleCallBack() {
				@Override
				public String doubleFormatter(Double value) {
					// TODO Auto-generated method stub
					DecimalFormat df=new DecimalFormat("#0");					 
					String label = df.format(value).toString();
					return label;
				}});
			
			 //让柱子间没空白
			 chart2.getBar().setBarInnerMargin(0d);
					
			//轴颜色					
			chart2.getDataAxis().getAxisPaint().setColor(axisColor);
			chart2.getCategoryAxis().getAxisPaint().setColor(axisColor);			
			chart2.getDataAxis().getTickMarksPaint().setColor(axisColor);
			chart2.getCategoryAxis().getTickMarksPaint().setColor(axisColor);
			
			chart2.getDataAxis().getTickLabelPaint().setColor(axisColor);
			chart2.getCategoryAxis().getTickLabelPaint().setColor(axisColor);
			
			chart2.getAxisTitle().getLeftAxisTitlePaint().setColor(axisColor);
			chart2.getAxisTitle().getLowerAxisTitlePaint().setColor(axisColor);
			
			//隐藏图例
			chart2.getPlotLegend().hideLegend();			
			//chart2.showRoundBorder();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());
		}
	}
	
	
	private void chartDataSet()
	{
		//标签对应的柱形数据集
		List<Double> dataSeriesA= new LinkedList<Double>();	
		dataSeriesA.add(2400d); 
		dataSeriesA.add(2400d); 
		dataSeriesA.add(2400d);
		dataSeriesA.add(2400d); 
		dataSeriesA.add(2400d);
		BarData BarDataA = new BarData("流动资产",dataSeriesA,(int)Color.rgb(58, 191, 247));
				
		List<Double> dataSeriesB= new LinkedList<Double>();	
		dataSeriesB.add(2000d);
		dataSeriesB.add(2000d);
		dataSeriesB.add(2000d);
		dataSeriesB.add(2000d);
		dataSeriesB.add(2000d);
		BarData BarDataB = new BarData("非流动资产",dataSeriesB,(int)Color.rgb(20, 181, 251));
		
	
		chartData.add(BarDataA);
		chartData.add(BarDataB);
		
		
		List<Double> dataSeriesAA= new LinkedList<Double>();	
		dataSeriesAA.add(0d); 
		BarData BarDataAA = new BarData("负债",dataSeriesAA,(int)Color.rgb(38, 137, 176));
		
		List<Double> dataSeriesBB= new LinkedList<Double>();	
		dataSeriesBB.add(0d);
		BarData BarDataBB = new BarData("所有者权益",dataSeriesBB,(int)Color.rgb(13, 116, 161));
		
		chartData.add(BarDataAA);
		chartData.add(BarDataBB);
		
	}
	
	private void chartDataSet2()
	{
		
		
		
		//标签对应的柱形数据集
		List<Double> dataSeriesA= new LinkedList<Double>();	
		dataSeriesA.add(1600d); 
		dataSeriesA.add(1700d); 
		dataSeriesA.add(1800d);
		dataSeriesA.add(1800d); 
		dataSeriesA.add(1500d);
		BarData BarDataA = new BarData("负债",dataSeriesA,(int)Color.rgb(38, 137, 176));
				
		List<Double> dataSeriesB= new LinkedList<Double>();	
		dataSeriesB.add(1500d);
		dataSeriesB.add(1300d);
		dataSeriesB.add(1400d);
		dataSeriesB.add(1200d);
		dataSeriesB.add(1600d);
		BarData BarDataB = new BarData("所有者权益",dataSeriesB,(int)Color.rgb(13, 116, 161));
			
		chartData2.add(BarDataA);
		chartData2.add(BarDataB);	
	}
	
	
	private void chartLabels()
	{
		chartLabels.add("20%"); 
		chartLabels.add("40%"); 
		chartLabels.add("60%"); 
		chartLabels.add("80%"); 
		chartLabels.add("100%"); 
	}	
		
	@Override
    public void render(Canvas canvas) {
        try{        	           
            chart.render(canvas);  
            chart2.render(canvas);  
            
            //绘制轴点
            float radius = 10f;
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(this.axisColor);            
            canvas.drawCircle(chart2.getPlotArea().getLeft(), chart2.getPlotArea().getBottom(), radius, paint);            
            canvas.drawCircle(chart2.getPlotArea().getRight(), chart2.getPlotArea().getBottom(), radius, paint);            
            canvas.drawCircle(chart2.getPlotArea().getLeft(), chart2.getPlotArea().getTop(), radius, paint);            
            
        } catch (Exception e){
        	Log.e(TAG, e.toString());
        }
    }			

	@Override
	public List<XChart> bindChart() {
		// TODO Auto-generated method stub		
		List<XChart> lst = new ArrayList<XChart>();
		lst.add(chart);
		lst.add(chart2);
		return lst;
	}

	//重载掉，让其不能移动,实际应用时，可直接继承GraphicalView即可.
	//此处是例子的权宜之计
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return false;
	}
	
}

