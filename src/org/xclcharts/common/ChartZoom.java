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
 * @version v0.1
 */
package org.xclcharts.common;

import org.xclcharts.renderer.XChart;

import android.view.View;


/**
 * @ClassName ChartZoom
 * @Description  放大缩小图表
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */

public class ChartZoom implements IChartZoom {
	
	private View view;
	private XChart chart;
	private float scale = 0.1f; 
	private static final float FIX_WITH = 300f;
	private static final float FIX_HEIGHT = 400f;
	
	public ChartZoom(View view, XChart chart) {
		this.chart = chart;
		this.view = view;
	}

	@Override
	public void setZoomRate(float rate) {
		// TODO Auto-generated method stub
		scale = rate;
	}

	@Override
	public void zoomIn() {
		// TODO Auto-generated method stub
		 //放大
		 int offsetX = (int)(scale * chart.getWidth());
	   	 int offsetY =  (int)(scale * chart.getHeight());
	   	 
	   	 float newWidth = chart.getRight() + offsetX;
	   	 float newHeight = chart.getBottom() + offsetY;
	   	 	   	 
	   	 if(newWidth < FIX_WITH) return ; //newWidth = FIX_WITH;
	   	 if(newHeight < FIX_HEIGHT)return ; //newHeight = FIX_HEIGHT;
	   	
	   	 chart.setChartRange( 0.0f, 0.0f, newWidth, newHeight);
	   	 view.invalidate();
	}

	@Override
	public void zoomOut() {
		// TODO Auto-generated method stub
		 int offsetX = (int)(scale * chart.getWidth());
    	 int offsetY =  (int)(scale * chart.getHeight());
    	 
    	 float newWidth = chart.getRight() - offsetX;
	   	 float newHeight = chart.getBottom() - offsetY;
	   	 	   	 
	   	 if(newWidth < FIX_WITH)return ; //newWidth = FIX_WITH;
	   	 if(newHeight < FIX_HEIGHT)return ; //newHeight = FIX_HEIGHT;
	   	 
    	 
    	 chart.setChartRange( 0.0f, 0.0f, newWidth, newHeight);
					//chart.getRight() - offsetX, chart.getBottom() - offsetY);
    	 view.invalidate();
	}
	
}
