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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Canvas;
import org.xclcharts.renderer.LnChart;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.line.PlotDot;
import org.xclcharts.renderer.line.PlotLine;

import android.graphics.Paint.Align;

/**
 * @ClassName LineChart
 * @Description  线图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */
public class LineChart extends LnChart{
	
	//数据源
	protected List<LineData> mDataSet;
	
	//数据轴显示在左边还是右边
	private XEnum.LineDataAxisPosition mDataAxisPosition = XEnum.LineDataAxisPosition.LEFT;

	
	public LineChart()
	{
		super();
		initChart();
	}

	private void initChart()
	{		
		defaultAxisSetting();		
	}
	
	/**
	 * 设置数据轴显示在哪边,默认是左边
	 * @param position
	 */
	public void setDataAxisDisplay(XEnum.LineDataAxisPosition position)
	{
		mDataAxisPosition = position;
		
			
		defaultAxisSetting();
	}
	
	/**
	 * 依数据库显示位置，设置相关的默认值
	 */
	private void defaultAxisSetting()
	{
		if(XEnum.LineDataAxisPosition.LEFT == mDataAxisPosition)
		{
			//renderVerticalDataAxis();
			labelsAxis.setHorizontalTickAlign(Align.CENTER);
			dataAxis.setHorizontalTickAlign(Align.LEFT);	
		}else{
			//renderVerticalDataAxisRight();			
			dataAxis.setHorizontalTickAlign(Align.RIGHT);
			dataAxis.getAxisTickLabelPaint().setTextAlign(Align.LEFT);			
		}	
	}
	 
		/**
		 * 标签轴的数据源
		 * @param labels 标签集
		 */
		public void setLabels(List<String> labels)
		{
			labelsAxis.setDataBuilding(labels);
		}
		
		/**
		 *  设置数据轴的数据源
		 * @param dataSet 数据源
		 */
		public void setDataSource(LinkedList<LineData> dataSet)
		{
			this.mDataSet = dataSet;		
		}
						
		private void renderLine(Canvas canvas, LineData bd,String type)
		{
			float initX =  plotArea.getLeft();
            float initY =  plotArea.getBottom();
             
			float lineStartX = initX;
            float lineStartY = initY;
            float lineEndX = 0.0f;
            float lineEndY = 0.0f;
            						
			float axisScreenHeight = getAxisScreenHeight();
			float axisDataHeight = (float) dataAxis.getAxisRange();		
			
			 //得到标签轴数据集
			 List<String> dataSet =  labelsAxis.getDataSet();
			 //步长
			int XSteps = (int) Math.ceil( getAxisScreenWidth()/ (dataSet.size() - 1)) ;
			
			List<Double> chartValues = bd.getLinePoint();			
			int j = 0;	
			
		    //画线
			for(Double bv : chartValues)
            {									
				
				
				//参数值与最大值的比例  照搬到 y轴高度与矩形高度的比例上来 	                                
            	float valuePostion = (float) Math.round( 
						axisScreenHeight * ( (bv - dataAxis.getAxisMin() ) / axisDataHeight)) ;  
            		                	
            	if(j == 0 )
				{
					lineStartX = initX;
					lineStartY = initY - valuePostion;
					
					lineEndX = lineStartX;
					lineEndY = lineStartY;
				}else{
					lineEndX =  initX + (j) * XSteps;
					lineEndY = initY - valuePostion;
				}            	
            
            	//如果值与最小值相等，即到了轴上，则忽略掉
				if(bv != dataAxis.getAxisMin())
				{
				
	            	PlotLine pLine = bd.getPlotLine();           
	            	if(type.equalsIgnoreCase("LINE"))
	            	{
	            		if( lineStartY != initY )	            			
	            			canvas.drawLine( lineStartX ,lineStartY ,lineEndX ,lineEndY,
	            												pLine.getLinePaint()); 
	            	}else if(type.equalsIgnoreCase("DOT2LABEL")){
	            		
	            		if(!pLine.getDotStyle().equals(XEnum.DotStyle.HIDE))
	                	{                		       	
	                		PlotDot pDot = pLine.getPlotDot();	                
	                		float rendEndX  = lineEndX  + pDot.getDotRadius();               		
	            			
	                		renderDot(canvas,pDot,
	                				lineStartX ,lineStartY ,
	                				lineEndX ,lineEndY,
	                				pLine.getDotPaint()); //标识图形            			                	
	            			lineEndX = rendEndX;
	                	}
	            		
	            		if(bd.getLineLabelVisible())
	                	{
	                		//fromatter
	                		canvas.drawText(Double.toString(bv) ,
	    							lineEndX, lineEndY,  pLine.getDotLabelPaint());
	                	}
	            	}else{
	            		return ;
	            	}      
				} //if(bv != dataAxis.getAxisMin())
            	
				lineStartX = lineEndX;
				lineStartY = lineEndY;

				j++;
            } 				
			
		}
		
		/**
		 * 绘制图表
		 */
		private void renderVerticalPlot(Canvas canvas)
		{			
								
			if(XEnum.LineDataAxisPosition.LEFT == mDataAxisPosition)
			{
				renderVerticalDataAxis(canvas);
			}else{
				renderVerticalDataAxisRight(canvas);
			}						
			renderVerticalLabelsAxis(canvas);
			
			List<LnData> lstKey = new ArrayList<LnData>();								
			//开始处 X 轴 即标签轴                  
			for(int i=0;i<mDataSet.size();i++)
			{								
				renderLine(canvas,mDataSet.get(i),"LINE");
				renderLine(canvas,mDataSet.get(i),"DOT2LABEL");
				lstKey.add(mDataSet.get(i));
			}			
			renderKey(canvas, lstKey);
		}	
		 
		
		public boolean render(Canvas canvas) throws Exception {
			// TODO Auto-generated method stub
		
			try {
				super.render(canvas);
				//绘制图表
				renderVerticalPlot(canvas);
				
			}catch( Exception e){
				 throw e;
			}
			return true;
		}
		 
		
}
