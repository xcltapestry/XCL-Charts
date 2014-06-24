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

import android.view.MotionEvent;
import android.view.View;


/**
 * @ClassName ChartTouch
 * @Description  上下左右移动图表
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */

public class ChartTouch implements IChartTouch {
	
	private View view;
	private XChart chart;
  
	//单点移动的前后坐标位置
	private float afterX = 0.0f,afterY = 0.0f;   
	private float beforeX = 0.0f,beforeY = 0.0f;  
		
	
	public ChartTouch(View view, XChart chart) {
		this.chart = chart;
		this.view = view;
	}
	
	//用来设置图表的位置   
	
	public void setLocation(int x, int y) {
		// TODO Auto-generated method stub
		//if(0 != x && 0 != y)
    	//{
	    	chart.setChartRange(chart.getLeft() + x, chart.getTop() + y, 
	    						chart.getWidth(), chart.getHeight());
	    	view.invalidate();
    	//}
	}
	

	
	@Override
	 public void handleTouch(MotionEvent event) {  
         	    	
	        switch(event.getAction()) 
	        {  	          
	        case MotionEvent.ACTION_DOWN:  
	        	 if(event.getPointerCount() == 1) {
	        		 beforeX = event.getX();  
	        		 beforeY = event.getY();  
	        	 }
	            break;  
	        case MotionEvent.ACTION_MOVE:  
	           	              
	            if(event.getPointerCount() == 1) {     	            	
	            	afterX = event.getX();  
	 	            afterY = event.getY();  	 	            
	            	this.setLocation((int)(afterX-beforeX),(int)(afterY-beforeY));          	            	
	            	beforeX = afterX;  
	  	            beforeY = afterY; 
	            }	              	           
	            break;  
	              
	        case MotionEvent.ACTION_UP:  	        	
	        	afterX = afterY = 0.0f;   
	        	beforeX = beforeY = 0.0f;  
	            break;  
	        }  
	        
	    }  
	 
	
   
	

}
