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

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;

/**
 * @ClassName RoseChart
 * @Description  南丁格尔图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */
public class RoseChart extends PieChart{
	
	private Paint mPaintInner = new Paint();

	public RoseChart() {
		// TODO Auto-generated constructor stub
		super();	
		initChart();
	}
	
	private void initChart()
	{										
		//深色内环
		mPaintInner = new Paint();
		mPaintInner.setColor(Color.DKGRAY);
		//mPaintInner.setStyle(Style.STROKE);
		mPaintInner.setStyle(Style.FILL);		
		mPaintInner.setAntiAlias(true);		
		
		//白色标签
		getLabelsPaint().setColor(Color.WHITE);
		getLabelsPaint().setTextSize(22);
		getLabelsPaint().setTextAlign(Align.CENTER);	
				
	}
	
	/**
	 * 开放内部背景画笔
	 * @return 画笔
	 */
	public Paint getInnerPaint()
	{
		return mPaintInner;
	}
	
	@Override 
	protected boolean checkInput()
	{
		return true;
	}
	
	/**
	 * 绘制图
	 */
	protected void renderPlot()
	{			 							
			//计算中心点坐标
			float cirX = plotArea.getCenterX();
		    float cirY = plotArea.getCenterY();
	        float radius = getRadius();
	     
	        //画笔初始化
			Paint paintArc = new Paint();  
			paintArc.setAntiAlias(true);	
			paintArc.setStyle(Style.FILL);	
	        
	        //外环
	        mCanvas.drawCircle(cirX,cirY,radius,mPaintInner); 
	    
	        float Percentage = 0.0f;		 		
	 		float NewRaidus = 0.0f;		
	 		
	 		//数据源
	 		List<PieData> chartDataSource = this.getDataSource();
			
			//依参数个数，算出总个要算多少个扇区的角度
			Percentage = 360 / chartDataSource.size();
			Percentage = (float)(Math.round(Percentage *100))/100; 
			
	        
	        for(PieData cData : chartDataSource)
			{
				paintArc.setColor(cData.getSliceColor());	
				
				//将百分比转换为新扇区的半径    
				NewRaidus = (float) (radius * (cData.getPercentage()/ 100));  
	            NewRaidus = (float)(Math.round(NewRaidus *100))/100;    
	            
	            //在饼图中显示所占比例  
	            float NewarcLeft = cirX - NewRaidus;  
	            float NewarcTop  = cirY - NewRaidus ;  
	            float NewarcRight = cirX + NewRaidus ;  
	            float NewarcBottom = cirY + NewRaidus ;  
	            RectF NewarcRF = new RectF(NewarcLeft ,NewarcTop,NewarcRight,NewarcBottom);  
	            this.mCanvas.drawArc(NewarcRF, mOffsetAgent, Percentage, true, paintArc);       
				
	          //计算百分比标签  
	            this.mCalc.CalcArcEndPointXY(cirX, cirY, radius - radius/2/2, mOffsetAgent + Percentage/2); 
	            
	            //标识  
	           mCanvas.drawText(cData.getLabel(),mCalc.getPosX(), mCalc.getPosY() ,getLabelsPaint());             
	         
	          //下次的起始角度  
	            mOffsetAgent += Percentage;  
			}			
	        
	}
		

}
