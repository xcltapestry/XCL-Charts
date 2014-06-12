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

import org.xclcharts.renderer.bar.Bar;
import org.xclcharts.renderer.bar.FlatBar;

import android.graphics.Paint.Align;

/**
 * @ClassName StackBarChart
 * @Description  堆叠图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */

public class StackBarChart  extends BarChart{
	
	private FlatBar flatBar = null;
	private boolean mTotalLableVisible = true;

	public StackBarChart()
	{
		super();
		flatBar = new FlatBar();		
	}
	
	/**
	 * 是否在柱形的最上方，显示汇总标签
	 * @param visible
	 */
	public void setTotalLabelVisible(boolean visible)
	{
		mTotalLableVisible = visible;
	}
	
	@Override
	public Bar getBar()
	{
		return flatBar;
	}
	
	@Override
	protected void renderHorizontalBar()
	{
	
		renderHorizontalBarDataAxis();	
		renderHorizontalBarLabelAxis(); 		
		
		 float axisScreenWidth  =  getAxisScreenWidth(); 
		 float axisDataRange = (float) dataAxis.getAxisRange(); 	
		 float valueWidth = axisDataRange; 
		 
		//步长
		 float YSteps = getHorizontalYSteps();		
		 int   barHeight = (int) Math.round(YSteps * 0.5); 		 		
		
		//标签轴	
		 for(int r=0;r<labelsAxis.getDataSet().size();r++)
		 {				 
			 	 float currentX = plotArea.getPlotLeft();
				 float currentY = plotArea.getPlotBottom() - (r+1) * YSteps;				 
				 Double total = 0d;				 
				 
				//得到数据源
				List<BarData> chartDataSource = this.getDataSource();
					
				 for(int i=0;i<chartDataSource.size();i++) //轴上的每个标签各自所占的高度
				 {
					 BarData bd = chartDataSource.get(i);
					 flatBar.getBarPaint().setColor(bd.getColor());						 
					 if(bd.getDataSet().size() < r+1) continue; 						 
					 
					//参数值与最大值的比例  照搬到 y轴高度与矩形高度的比例上来	
					Double bv = bd.getDataSet().get(r);						
					total += bv;				
					float valuePostion = 0.0f;
					if(i == 0 )
					{					
						valuePostion = (float) Math.round( 
	                			axisScreenWidth * ( (bv - dataAxis.getAxisMin() ) / valueWidth)) ;						
					}else{						
						valuePostion = (float) Math.round( axisScreenWidth * ( (bv ) / valueWidth)) ;
					}
					
				   	//宽度                	
				   flatBar.renderBar(currentX ,currentY - barHeight/2,
						   			 currentX + valuePostion,currentY + barHeight/2,this.mCanvas); 	             	
				    				    
					//柱形的当前值
					flatBar.renderBarItemLabel(getFormatterItemLabel(bv),
												currentX + valuePostion/2, currentY , mCanvas);											
					currentX += valuePostion ;						 
				 }
				
				 //合计		
				 if(mTotalLableVisible)
				 {
					 float totalPostion = (float) Math.round(  axisScreenWidth/axisDataRange * (total- dataAxis.getAxisMin()) );					 
					 flatBar.renderBarItemLabel(getFormatterItemLabel(total), 
							 					plotArea.getPlotLeft()  - totalPostion, currentY, mCanvas);
				 }
		 }	
	 		 		 
	 	//Y轴线
		dataAxis.renderAxis(plotArea.getPlotLeft(), plotArea.getPlotBottom(), 
							  plotArea.getPlotLeft(), plotArea.getPlotTop());	
		 
		//X轴 线		
		labelsAxis.renderAxis(plotArea.getPlotLeft(), plotArea.getPlotBottom(), 
								  plotArea.getPlotRight(),  plotArea.getPlotBottom());	
		//画Key说明
		drawDataSetKey();				
	}
	
	@Override
	protected void renderVerticalBar()
	{
	
			//坐标布局
			renderVerticalBarDataAxis();
			renderVerticalBarLabelsAxis();
		
			//得到标签轴数据集
			List<String> dataSet =  labelsAxis.getDataSet();			 
			float XSteps = getVerticalXSteps(dataSet.size() + 1 );			 
			float axisScreenHeight  =  getAxisScreenHeight(); 
			float axisDataHeight =  (float) dataAxis.getAxisRange(); 
			int   barWidht =  (int) Math.round(XSteps * 0.5); 		
			
			//得到数据源
			List<BarData> chartDataSource = this.getDataSource();
			 
			 for(int r=0;r<dataSet.size();r++) //轴上的每个标签
			 {				 
				 	 float currentX = plotArea.getPlotLeft() + (r+1) * XSteps;	 	 			
					 float currentY = plotArea.getPlotBottom();
					 Double total = 0d;
					 
					 for(int i=0; i < chartDataSource.size();i++) //各自所占的高度
					 {
						 BarData bd = chartDataSource.get(i);
						 flatBar.getBarPaint().setColor(bd.getColor());						 
						 if(bd.getDataSet().size() < r+1) continue; 						 
						 
						//参数值与最大值的比例  照搬到 y轴高度与矩形高度的比例上来	
						Double bv = bd.getDataSet().get(r);						
						total += bv;
					
						float valuePostion = 0.0f;
						if(i == 0 )
						{							
							 valuePostion = (float) Math.round( 
									axisScreenHeight * ( (bv - dataAxis.getAxisMin() ) / axisDataHeight)) ;  							
						}else{
							valuePostion = (float) Math.round( 
									axisScreenHeight * ( (bv  ) / axisDataHeight)) ;
							
						}
						flatBar.renderBar(currentX - barWidht/2, currentY - valuePostion, 
										  currentX + barWidht /2, currentY, mCanvas);	 
						//柱形的当前值
						flatBar.renderBarItemLabel(getFormatterItemLabel(bv), 
													currentX, currentY - valuePostion/2, mCanvas);											
						currentY -= valuePostion ;						 
					 }
					 //合计					 
					 float totalPostion = (float) Math.round( axisScreenHeight/axisDataHeight * (total- dataAxis.getAxisMin()) );					 
					 flatBar.renderBarItemLabel(getFormatterItemLabel(total), 
							 					currentX, plotArea.getPlotBottom() - totalPostion, mCanvas);							
			 }			 
			 
			//轴 线		 		 
			 dataAxis.renderAxis(plotArea.getPlotLeft(),  plotArea.getPlotBottom(),
		 			   			 plotArea.getPlotRight(),  plotArea.getPlotBottom());
			 
			 drawDataSetKey();			 		
	}
	
	
}
