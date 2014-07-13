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

import org.xclcharts.chart.PieData;
import org.xclcharts.chart.RoseChart;
import org.xclcharts.renderer.XChart;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;

/**
 * @ClassName RoseChart01View
 * @Description  南丁格尔玫瑰图 的例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class RoseChart01View extends TouchView {

	private String TAG = "RoseChart01View";
	private RoseChart chart = new RoseChart();
	
	LinkedList<PieData> roseData = new LinkedList<PieData>();	
	
	public RoseChart01View(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			initView();
	}
	
	public RoseChart01View(Context context, AttributeSet attrs){   
        	super(context, attrs);   
        	initView();
	 }
	 
	 public RoseChart01View(Context context, AttributeSet attrs, int defStyle) {
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
			
			//设置绘图区默认缩进px值
			int [] ltrb = getPieDefaultSpadding();
			chart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);	
			
			//背景 
			chart.setApplyBackgroundColor(true);
			chart.setBackgroundColor(Color.BLACK);
			
			//数据源
			chart.setDataSource(roseData);							
				
			//设置标题
			chart.setTitle("南丁格尔玫瑰图");
			chart.addSubtitle("(XCL-Charts)");
			chart.getPlotTitle().getTitlePaint().setColor(Color.WHITE);
			chart.getPlotTitle().getSubtitlePaint().setColor(Color.WHITE);
			
			//设置标签显示位置,当前设置标签显示在扇区中间
			chart.setLabelPosition(XEnum.SliceLabelPosition.OUTSIDE);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());
		}
	}
	private void chartDataSet()
	{
		//设置图表数据源
							
		//PieData(标签，百分比，在饼图中对应的颜色)
		roseData.add(new PieData("PostgreSQL",40,(int)Color.rgb(77, 83, 97) ));
		roseData.add(new PieData("Sybase"	 ,50,(int)Color.rgb(148, 159, 181)));
		roseData.add(new PieData("DB2"		 ,60,(int)Color.rgb(253, 180, 90)));
		roseData.add(new PieData("国产及其它"	 ,35,(int)Color.rgb(52, 194, 188)));
		roseData.add(new PieData("SQL Server",70,(int)Color.rgb(39, 51, 72)));
		roseData.add(new PieData("DB2"		 ,80,(int)Color.rgb(255, 135, 195)));
		roseData.add(new PieData("Oracle"    ,90,(int)Color.rgb(215, 124, 124)));	
	}
	
	@Override
    public void render(Canvas canvas) {
        try{
        	canvas.drawColor(Color.BLACK);
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
