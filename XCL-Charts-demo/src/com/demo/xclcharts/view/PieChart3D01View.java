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
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version 1.0
 */
package com.demo.xclcharts.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.xclcharts.chart.PieChart3D;
import org.xclcharts.chart.PieData;
import org.xclcharts.event.click.ArcPosition;
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
 * @ClassName Pie3DChart01View
 * @Description  3D饼图的例子
 * 问动画效果的人太多了，其实图表库就应当只管绘图，动画效果就交给View或SurfaceView吧,
 * 	看看我弄的效果有多靓. ~_~ 
 *  依这个例子发挥发挥，可以让图从屏幕各个方向出现.
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class PieChart3D01View extends TouchView implements Runnable{

	private String TAG = "Pie3DChart01View";
	private PieChart3D chart = new PieChart3D();
	
	private LinkedList<PieData> chartData = new LinkedList<PieData>();	
	
	public PieChart3D01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}
	
	public PieChart3D01View(Context context, AttributeSet attrs){   
        super(context, attrs);   
        initView();
	 }
	 
	 public PieChart3D01View(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView();
	 }
	 
	 private void initView()
	 {
		 	chartDataSet();		
			chartRender();
			new Thread(this).start();
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
			//设置绘图区默认缩进px值
			int [] ltrb = getPieDefaultSpadding();
			chart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);	
			
			//设定数据源
			//chart.setDataSource(chartData);		
			
			//标题
			//chart.setTitle("个人专业技能分布");
			//chart.addSubtitle("(XCL-Charts Demo)");
			//chart.getPlotTitle().setTitlePosition(XEnum.Position.LOWER);
			
			//不显示key
			chart.getPlotLegend().hideLegend();
			//标签文本显示为白色
			chart.getLabelPaint().setColor(Color.WHITE);
			
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());
		}
	}
	private void chartDataSet()
	{
		//设置图表数据源			
		//PieData(标签，百分比，在饼图中对应的颜色)
		
		chartData.add(new PieData("PHP(15%)",15,
								(int)Color.rgb(1, 170, 255)));
		chartData.add(new PieData("Other",10,
								(int)Color.rgb(148, 42, 133),false));	
		chartData.add(new PieData("Oracle",40,(int)Color.rgb(241, 62, 1)));
		chartData.add(new PieData("Java",15,(int)Color.rgb(242, 167, 69)));	
		
		//将此比例块突出显示
		chartData.add(new PieData("C++(20%)",20,
								(int)Color.rgb(164, 233, 0),true));
	
		
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
	public void run() {
		// TODO Auto-generated method stub
		try {          
         	chartAnimation();         	
         }
         catch(Exception e) {
             Thread.currentThread().interrupt();
         }  
	}
	
	private void chartAnimation()
	{
		  try {         
			 //设置数据源
			  chart.setDataSource(chartData);
			  
			  for(int i=10;i>0;i--)
			  {
				  Thread.sleep(100);
				 // chart.setChartRange(0.0f, 0.0f,getScreenWidth()/i,getScreenHeight()/i);				  
				  chart.setChartRange(0.0f, 0.0f,this.getWidth()/i,this.getHeight()/i);				  
				  
				  if(1 == i)
				  {
					    //最末显示标题
						chart.setTitle("个人专业技能分布");
						chart.addSubtitle("(XCL-Charts Demo)");
						chart.getPlotTitle().setTitlePosition(XEnum.Position.LOWER);
						chart.setChartRange(0.0f, 0.0f,getWidth(),getHeight());
						
						//激活点击监听
						chart.ActiveListenItemClick();											
				  }
				  postInvalidate(); 
			  }
          }
          catch(Exception e) {
              Thread.currentThread().interrupt();
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
				" key:" +  pData.getKey() +
				" Label:" + pData.getLabel() ,
				Toast.LENGTH_SHORT).show();	
		
		/*
		 int k	= chart.getDataSource().size();
		 
		 Toast.makeText(this.getContext(),								
					" k:" +  Integer.toString(k) ,
					Toast.LENGTH_SHORT).show();	
		 */
	}
	
}
