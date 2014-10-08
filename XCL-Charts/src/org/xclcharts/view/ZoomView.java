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
 * @version 1.7
 */
package org.xclcharts.view;

import java.util.List;

import org.xclcharts.event.zoom.ChartZoom;
import org.xclcharts.event.zoom.IChartZoom;
import org.xclcharts.renderer.XChart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * @ClassName ZoomView
 * @Description  包含放大缩小图表函数的View基类,
 *                与enableScale()所对应的放大缩小不同，这个可以补捉到处理后的位置信息
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com) QQ群: 374780627
 */
public class ZoomView extends ChartView implements IChartZoom {
	
	 private ChartZoom mChartZoom[];

	 public ZoomView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub		
	 }	
	
	 public ZoomView(Context context, AttributeSet attrs){   
	        super(context, attrs);   	        
	 }
	 
	 public ZoomView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);		
	 }
	 
	@Override
	public void render(Canvas canvas) {
		// TODO Auto-generated method stub
		super.render(canvas);
	}
		
	@Override
	public List<XChart> bindChart() {
		// TODO Auto-generated method stub
		return null;
	}	
	//////////////////////////////////////////////////////	

		
	//////////////////////////////////////////////////////
	//供ZoomControls之类的控件或函数调用来放大缩小图表相关函数
	//////////////////////////////////////////////////////	
	private int getZoomCharts()
	{		
		 if(null != mChartZoom ) return mChartZoom.length;
		
		 List<XChart> listCharts = bindChart();
		 int count = listCharts.size();		 
		 if(0 == count) return 0;	
		 
		 mChartZoom = new ChartZoom[count];			 
		 for(int i=0;i<count;i++)
			 mChartZoom[i] = new ChartZoom(this,listCharts.get(i));			
		
		 return count;
	}		
	
	@Override
	public void setZoomRate(float rate) {
		// TODO Auto-generated method stub
	
		int count = getZoomCharts();
		for(int i=0;i<count;i++)
			mChartZoom[i].setZoomRate(rate);	
	}

	@Override
	public void zoomIn() {
		// TODO Auto-generated method stub
			
		int count = getZoomCharts();
		for(int i=0;i<count;i++)
			 mChartZoom[i].zoomIn();	
	}

	@Override
	public void zoomOut() {
		// TODO Auto-generated method stub

		int count = getZoomCharts();		
		for(int i=0;i<count;i++)
			mChartZoom[i].zoomOut();	
	}
	
	//////////////////////////////////////////////////////
}
