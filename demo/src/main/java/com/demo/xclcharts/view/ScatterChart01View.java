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
 * @version 1.5
 */
package com.demo.xclcharts.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.xclcharts.chart.PointD;
import org.xclcharts.chart.ScatterChart;
import org.xclcharts.chart.ScatterData;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.event.click.PointPosition;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @ClassName ScatterChart01View
 * @Description  散点图例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */

public class ScatterChart01View extends DemoView {

	private String TAG = "ScatterChart01View";
	private ScatterChart chart = new ScatterChart();
	//分类轴标签集合
	private LinkedList<String> labels = new LinkedList<String>();
	private List<ScatterData> chartData = new LinkedList<ScatterData>();
	
	private Paint mPaintTooltips = new Paint(Paint.ANTI_ALIAS_FLAG);
	
	public ScatterChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}
	
	public ScatterChart01View(Context context, AttributeSet attrs){   
        super(context, attrs);   
        initView();
	 }
	 
	 public ScatterChart01View(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView();
	 }
	 
	 private void initView()
	 {
		chartLabels();
		chartDataSet();	
		chartRender();
		//綁定手势滑动事件
		this.bindTouch(this,chart);
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
			chart.setPadding(15, ltrb[1], 15, ltrb[3]);							
			
			//数据源	
			chart.setCategories(labels);
			chart.setDataSource(chartData);
						
			//坐标系
			//数据轴最大值
			chart.getDataAxis().setAxisMax(100);
			//chart.getDataAxis().setAxisMin(0);
			//数据轴刻度间隔
			chart.getDataAxis().setAxisSteps(10);
			
			//标签轴最大值
			chart.setCategoryAxisMax(7); 
			//标签轴最小值
			chart.setCategoryAxisMin(1);	
			
			chart.getDataAxis().setHorizontalTickAlign(Align.CENTER);
			chart.getDataAxis().getTickLabelPaint().setTextAlign(Align.CENTER);
			
			//chart.getDataAxis().setDetailModeSteps(4);
			
			
			chart.getDataAxis().getAxisPaint().setColor(Color.rgb(127, 204, 204));
			chart.getCategoryAxis().getAxisPaint().setColor(Color.rgb(127, 204, 204));
			
			chart.getDataAxis().getTickMarksPaint().setColor(Color.rgb(127, 204, 204));
			chart.getCategoryAxis().getTickMarksPaint().setColor(Color.rgb(127, 204, 204));
			
			chart.getDataAxis().getTickLabelPaint().setTextAlign(Align.LEFT);
			
			
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
			chart.setTitle("散点图");
			chart.addSubtitle("(XCL-Charts Demo)");
			
			//激活点击监听
			chart.ActiveListenItemClick();
			//为了让触发更灵敏，可以扩大5px的点击监听范围
			chart.extPointClickRange(10);
			
			chart.getPointPaint().setStrokeWidth(6);
			
			//显示十字交叉线
			chart.showDyLine();
			chart.getDyLine().setDyLineStyle(XEnum.DyLineStyle.BackwardDiagonal);
			
			//背景渐变
			chart.getPlotArea().setBackgroundColor(true, Color.rgb(83, 182, 203));
			chart.getPlotArea().setApplayGradient(true);
			chart.getPlotArea().setGradientDirection(XEnum.Direction.HORIZONTAL);
			chart.getPlotArea().setBeginColor(Color.WHITE);
			
			//图例
			chart.getPlotLegend().setType(XEnum.LegendType.COLUMN);
			chart.getPlotLegend().setVerticalAlign(XEnum.VerticalAlign.MIDDLE);
			chart.getPlotLegend().setHorizontalAlign(XEnum.HorizontalAlign.RIGHT);
			chart.getPlotLegend().getBox().setBorderRectType(XEnum.RectType.RECT);
			chart.getPlotLegend().showBox();
			
			//chart.disablePanMode();
			chart.disableScale();
			
			//不使用精确计算，忽略Java计算误差
			chart.disableHighPrecision();
			
			/*
			//定义交叉点标签显示格式,特别备注,因图的特殊性，所以返回格式为:  x值,y值
			//请自行分析定制
			chart.setDotLabelFormatter(new IFormatterTextCallBack(){
	
				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub						
					String label = "("+value+")";				
					return (label);
				}
				
			});*/
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, e.toString());
		}
	}
	private void chartDataSet()
	{
		//线1的数据集
		ArrayList<PointD> linePoint1 = new ArrayList<PointD>();
		
		linePoint1.add(new PointD(2d, 10d));		
		linePoint1.add(new PointD(6d, 10d));			
		linePoint1.add(new PointD(5d, 10d));		
		linePoint1.add(new PointD(3d, 10d));
		linePoint1.add(new PointD(4d, 10d));
				
		ScatterData dataSeries1 = new ScatterData("青菜萝卜够吃",linePoint1,
				Color.rgb(54, 141, 238),XEnum.DotStyle.DOT );	
		dataSeries1.setLabelVisible(true);	
		dataSeries1.getDotLabelPaint().setColor(Color.rgb(191, 79, 75));
		
		
		//线2的数据集
		ArrayList<PointD> linePoint2 = new ArrayList<PointD>();
		
		linePoint2.add(new PointD(2d, 5d));
		linePoint2.add(new PointD(5d, 5d));
		linePoint2.add(new PointD(6d, 5d));
		linePoint2.add(new PointD(3d, 5d));		
		
		linePoint2.add(new PointD(7d, 5d));	
		linePoint2.add(new PointD(4d, 5d));	
				
		ScatterData dataSeries2 = new ScatterData("饭管够",linePoint2,
				Color.rgb(155, 187, 90),XEnum.DotStyle.PRISMATIC );
		
		ArrayList<PointD> linePoint3 = new ArrayList<PointD>();
		linePoint3.add(new PointD(3d, 20d));
		
		linePoint3.add(new PointD(5d, 20d));
		linePoint3.add(new PointD(6d, 20d));
		ScatterData dataSeries3 = new ScatterData("哈哈",linePoint3,
				Color.rgb(54, 141, 238),XEnum.DotStyle.RING );	
		
		dataSeries3.setLabelVisible(true);
		dataSeries3.getPlotDot().setRingInnerColor(Color.rgb(242, 167, 69));
		dataSeries3.getDotLabelPaint().setTextAlign(Align.CENTER);
		
		ArrayList<PointD> linePoint4 = new ArrayList<PointD>();
		linePoint4.add(new PointD(2d, 70d));
		linePoint4.add(new PointD(3d, 79d));
		linePoint4.add(new PointD(5d, 75d));
		linePoint4.add(new PointD(7d, 75d));				
		ScatterData dataSeries4 = new ScatterData("XXX",linePoint4,
				Color.rgb(60, 173, 213),XEnum.DotStyle.X );		
		
			
		ArrayList<PointD> linePoint5 = new ArrayList<PointD>();
		
		linePoint5.add(new PointD(2d, 40d));
		linePoint5.add(new PointD(2d, 50d));
		linePoint5.add(new PointD(2d, 60d));
		
		linePoint5.add(new PointD(2.5d, 43d));
		linePoint5.add(new PointD(2.5d, 51d));
		linePoint5.add(new PointD(2.5d, 60d));
		
					
		linePoint5.add(new PointD(3d, 73d));
		linePoint5.add(new PointD(3d, 40d));
		linePoint5.add(new PointD(5d, 55d));
		linePoint5.add(new PointD(7d, 55d));				
								
		ScatterData dataSeries5 = new ScatterData("Cross",linePoint5,
				Color.RED,XEnum.DotStyle.CROSS );	
		
		//设定数据源		
		chartData.add(dataSeries1);				
		chartData.add(dataSeries2);	
		chartData.add(dataSeries3);	
		chartData.add(dataSeries4);
		chartData.add(dataSeries5);
	}
	
	private void chartLabels()
	{
		labels.add("1");
		labels.add("2");
		labels.add("3");
		labels.add("4");
		labels.add("5");
		labels.add("6");
		labels.add("7");
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
		
		//交叉线
		if(chart.getDyLineVisible())chart.getDyLine().setCurrentXY(x,y);		
		if(!chart.getListenItemClickStatus())
		{
			if(chart.getDyLineVisible()&&chart.getDyLine().isInvalidate())this.invalidate();
		}else{	
				PointPosition record = chart.getPositionRecord(x,y);			
				if( null == record) return;
		
				ScatterData lData = chartData.get(record.getDataID());
				List<PointD> linePoint =  lData.getDataSet();	
				int pos = record.getDataChildID();
				int i = 0;
				Iterator it = linePoint.iterator();
				while(it.hasNext())
				{
					PointD  entry=(PointD)it.next();	
					
					if(pos == i)
					{							 						
					     Double xValue = entry.x;
					     Double yValue = entry.y;					     
					     
					   //在点击处显示tooltip
						mPaintTooltips.setColor(Color.RED);				
						chart.getToolTip().setCurrentXY(x,y);
						chart.getToolTip().addToolTip(" Key:"+lData.getKey(),mPaintTooltips);		
						chart.getToolTip().addToolTip(
								" Current Value:" +Double.toString(xValue)+","+Double.toString(yValue),mPaintTooltips);
						this.invalidate();	
					     break;
					}
			        i++;
				}//end while
		} //end if		
		
		
	}
	
}
