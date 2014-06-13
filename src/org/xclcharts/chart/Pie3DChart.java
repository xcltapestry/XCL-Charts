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
package org.xclcharts.chart;

import java.util.List;

import org.xclcharts.common.DrawHelper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * @ClassName Pie3DChart
 * @Description  3D饼图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */
public class Pie3DChart extends PieChart{
	
	//渲染层数
	private final int mRender3DLevel = 15; 
		
	public Pie3DChart() {
		// TODO Auto-generated constructor stub
		super();	     
	}

	@Override 
	protected void renderPlot()
	{		
		//计算中心点坐标		
		float cirX = plotArea.getCenterX();
	    float cirY = plotArea.getCenterY();	     
        float radius = getRadius();
              
        //确定去饼图范围
        float arcLeft = cirX - radius;  
        float arcTop  = cirY - radius ;  
        float arcRight = cirX + radius ;  
        float arcBottom = cirY + radius ;  
        RectF arcRF0 = new RectF(arcLeft ,arcTop,arcRight,arcBottom);   
        	       
        //画笔初始化
		Paint paintArc = new Paint();  
		paintArc.setAntiAlias(true);	
		
		
		//数据源
 		List<PieData> chartDataSource = this.getDataSource();
 		
 		int initOffsetAgent = mOffsetAgent;
		
		//3D
        float currentAgent = 0.0f;	        	        
		for(int i=0;i < mRender3DLevel;i++)
		{			
		  this.mCanvas.save(Canvas.MATRIX_SAVE_FLAG); 			
		  mCanvas.translate(0,mRender3DLevel - i );		  			  
		  for(int j=0;j< chartDataSource.size();j++)
		  {			  
			    PieData cData =  chartDataSource.get(j);			  
				paintArc.setColor(cData.getSliceColor());				
				currentAgent = cData.getSliceAgent();
				
			    if(cData.getSelected()) //指定突出哪个块
	            {				    			    	
			    	//偏移圆心点位置(默认偏移半径的1/10)
			    	float newRadius = radius /10;
			    	 //计算百分比标签
			        mCalc.CalcArcEndPointXY(cirX,cirY,newRadius,mOffsetAgent + currentAgent/2); 	
			        
			        float arcLeft2 = mCalc.getPosX() - radius;  
			        float arcTop2  = mCalc.getPosY() - radius ;  
			        float arcRight2 = mCalc.getPosX() + radius ;  
			        float arcBottom2 = mCalc.getPosY() + radius ;  
			        RectF arcRF1 = new RectF(arcLeft2 ,arcTop2,arcRight2,arcBottom2);   			        			        
			        mCanvas.drawArc(arcRF1, mOffsetAgent, currentAgent, true,paintArc); 
	            }else{	            
	            	mCanvas.drawArc(arcRF0, mOffsetAgent, currentAgent, true,paintArc); 
	            }			    			    
	            //下次的起始角度  
	            mOffsetAgent += currentAgent;  	            
	            //k += 2;
			}			
		    mCanvas.restore();  
		    mOffsetAgent = initOffsetAgent;
		}
		
		//平面
		DrawHelper dw = new DrawHelper();			
		currentAgent = 0.0f;	
	
		for(int j=0;j< chartDataSource.size();j++)
		{
		 	PieData cData =  chartDataSource.get(j);	
		 	currentAgent = cData.getSliceAgent();
		 	int darkColor =  dw.getDarkerColor((int)cData.getSliceColor());			  
		  	paintArc.setColor( darkColor); 						
			
		    if(cData.getSelected()) //指定突出哪个块
            {					    					    	
		    	//偏移圆心点位置(默认偏移半径的1/10)
		    	float newRadius = radius /10;
		    	 //计算百分比标签
		        mCalc.CalcArcEndPointXY(cirX,cirY,newRadius,mOffsetAgent + currentAgent/2); 	
		        
		        float arcLeft2 = mCalc.getPosX() - radius;  
		        float arcTop2  = mCalc.getPosY() - radius ;  
		        float arcRight2 = mCalc.getPosX() + radius ;  
		        float arcBottom2 = mCalc.getPosY() + radius ;  
		        RectF arcRF1 = new RectF(arcLeft2 ,arcTop2,arcRight2,arcBottom2);   
		        mCanvas.drawArc(arcRF1, mOffsetAgent, currentAgent, true,paintArc); 
		        drawLables(cData.getLabel(),mCalc.getPosX(), mCalc.getPosY(),
		        			radius,mOffsetAgent,currentAgent);                
            }else{            
            	mCanvas.drawArc(arcRF0, mOffsetAgent, currentAgent, true, paintArc);       	    
     	        drawLables(cData.getLabel(),cirX, cirY,radius,mOffsetAgent,currentAgent);
     	    }				    		    
           //下次的起始角度  
            mOffsetAgent += currentAgent;  		                    		        
		}			
		renderKey();
	}

}
