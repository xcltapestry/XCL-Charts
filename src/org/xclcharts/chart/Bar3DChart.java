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
import org.xclcharts.renderer.bar.Bar;
import org.xclcharts.renderer.bar.Bar3D;

import android.util.Log;

/**
 * @ClassName Bar3DChart
 * @Description  3D柱形图的基类,包含横向和竖向3D柱形图
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */

public class Bar3DChart extends BarChart{
	
	//3D柱形绘制
	private Bar3D mBar3D  = new Bar3D();

	public Bar3DChart()
	{
		super();	
		setPlotKeyVisible(true);
	}
	
	/**
	 * 设置坐标系底座厚度 
	 * @param thickness 底座厚度 
	 */
	public void setAxis3DBaseThickness(int thickness)
	{
		mBar3D.setAxis3DBaseThickness(thickness);
	}
	
	/**
	 * 返回坐标系底座厚度 
	 * @return 底座厚度 
	 */
	public double getAxis3DBaseThickness()
	{
		return mBar3D.getAxis3DBaseThickness();
	}
	
	/**
	 * 设置柱形3D厚度
	 * @param thickness 厚度
	 */
	public void setBarThickness(int thickness)
	{
		mBar3D.setThickness(thickness);
	}
	
	/**
	 * 返回柱形3D厚度
	 * @return 厚度
	 */
	public double getBarThickness()
	{
		return  mBar3D.getThickness();
	}
	/**
	 * 设置3D偏转角度
	 * @param angle 角度
	 */
	public void setBarAngle(int angle)
	{
		mBar3D.setAngle(angle);
	}
	/**
	 * 返回3D偏转角度
	 * @return 角度
	 */
	public int getBarAngle()
	{
		return mBar3D.getAngle();
	}
	
	/**
	 * 设透明度
	 * @param alpha 透明度
	 */
	public void setBarAlpha(int alpha)
	{
		mBar3D.setAlpha(alpha);
	}
	
	/**
	 *  坐标基座颜色
	 * @param color 颜色
	 */
	public void setAxis3DBaseColor(int color)
	{
		mBar3D.setAxis3DBaseColor(color);
	}	
	
	@Override
	public Bar getBar()
	{
		return mBar3D;
	}
	
	/**
	 * 3D时，隐藏旧那个刻度线标识
	 */
	protected void defaultAxisSetting()
	{
		super.defaultAxisSetting();
		try{
			switch (getChartDirection()) {
				case HORIZONTAL: {				
					labelsAxis.setAxisTickMarksVisible(false);		
					break;
				}
				case VERTICAL: {					
					labelsAxis.setAxisTickMarksVisible(false);				
					break;
				}
			}
		}catch(Exception ex){
			Log.e("ERROR-Bar3DChart", ex.toString());
		}
	}
	
	
	@Override
	protected void renderHorizontalBarLabelAxis() {
		// Y 轴
		// 标签横向间距高度
		float YSteps = (float) Math.ceil(this.getAxisScreenHeight()
				/ (this.labelsAxis.getDataSet().size() + 1));
		float currentY = 0.0f;
		for (int i = 0; i < labelsAxis.getDataSet().size(); i++) {
			// 依初超始Y坐标与标签间距算出当前刻度的Y坐标
			currentY = plotArea.getPlotBottom() - (i + 1) * YSteps;
			// 横的grid线
			plotGrid.renderGridLinesHorizontal(plotArea.getPlotLeft(),
					currentY, plotArea.getPlotRight(), currentY);
							
			// 标签
			float labelX = Math.round(plotArea.getPlotLeft() - mBar3D.getOffsetX() * 2);
			this.labelsAxis.renderAxisHorizontalTick(labelX,
					currentY, labelsAxis.getDataSet().get(i));
		}
	}
	
	
	@Override
	protected void renderHorizontalBar()
	{		
		renderHorizontalBarDataAxis();		
		 
		//x轴 线 [要向里突]
		 dataAxis.renderAxis(plotArea.getPlotLeft(), plotArea.getPlotBottom(), 
				 			 plotArea.getPlotRight(),  plotArea.getPlotBottom());	
		 //Y 轴           
		 renderHorizontalBarLabelAxis(); 	
			
			//Y轴线
		 mBar3D.render3DYAxis(plotArea.getPlotLeft(), plotArea.getPlotTop(), 
							 plotArea.getPlotRight(), plotArea.getPlotBottom(),mCanvas); 
			
			//得到Y 轴标签横向间距高度
			 float YSteps = getHorizontalYSteps();
	
			//得到数据源
			List<BarData> chartDataSource = this.getDataSource(); 
			//依柱形宽度，多柱形间的偏移值 与当前数据集的总数据个数得到当前标签柱形要占的高度	
			int barNumber = chartDataSource.size();
			int currNumber = 0;			
			
			List<Integer> ret = mBar3D.getBarHeightAndMargin(YSteps, barNumber);	
			int barHeight = ret.get(0);
			int barInnerMargin = ret.get(1);			
			int labelBarUseHeight = barNumber * barHeight + (barNumber - 1) * barInnerMargin;	
			
			float scrWidth = plotArea.getPlotWidth();
			float valueWidth = (float) dataAxis.getAxisRange();
			
			for(int i=0;i<barNumber;i++)
			{					    
				//得到标签对应的值数据集
				BarData bd = chartDataSource.get(i) ; 
				List<Double> barValues = bd.getDataSet(); 
				//设置成对应的颜色
				mBar3D.getBarPaint().setColor(bd.getColor());				
				int k = 1;						 
			    //画同标签下的所有柱形
				for(Double bv : barValues)
                {																																						
					float drawBarButtomY = plotArea.getPlotBottom() - (k) * YSteps + labelBarUseHeight / 2;							
					drawBarButtomY = drawBarButtomY - (barHeight + barInnerMargin ) * currNumber;
					
																				
                	//参数值与最大值的比例  照搬到 y轴高度与矩形高度的比例上来
                	float valuePostion = (float) Math.round( 
                			scrWidth * ( (bv - dataAxis.getAxisMin() ) / valueWidth)) ;                 	
                            	        
                	//画出柱形                	 	            
	                mBar3D.renderHorizontal3DBar(plotArea.getPlotLeft(), 
	                							drawBarButtomY - barHeight, 
						                		(float) Math.round(plotArea.getPlotLeft()  +  valuePostion), 
						                		drawBarButtomY, 
	                							mBar3D.getBarPaint().getColor(), this.mCanvas);
	                	             	
                	                               	
                	//在柱形的顶端显示上柱形的当前值	                
	                mBar3D.renderBarItemLabel(getFormatterItemLabel(bv),
	                		 (float) Math.round(plotArea.getPlotLeft() + valuePostion)  , 
	                		 (float) Math.round(drawBarButtomY - barHeight/2), mCanvas);
                               
                	k++;
                }
				currNumber ++;
			}	
			//画Key说明
			drawDataSetKey();	 
	}
	
	
	@Override
	protected void renderVerticalBarLabelsAxis() {
		// 标签轴(X 轴)
		float currentX = plotArea.getPlotLeft();

		// 得到标签轴数据集
		List<String> dataSet = labelsAxis.getDataSet();

		// 依传入的标签个数与轴总宽度算出要画的标签间距数是多少
		// 总宽度 / 标签个数 = 间距长度
		int XSteps = (int) Math.ceil(getAxisScreenWidth()
				/ (dataSet.size() + 1));
		
		//3D 偏移值		
	    double baseTickness = mBar3D.getAxis3DBaseThickness();
	    double baseAngle = mBar3D.getAngle();	
		double baseOffsetX = mBar3D.getOffsetX(baseTickness,baseAngle);
		double baseOffsetY = mBar3D.getOffsetY(baseTickness,baseAngle);	
	
		DrawHelper dw = new DrawHelper();
		double labelHeight = dw.getPaintFontHeight(labelsAxis.getAxisTickLabelsPaint());
		
	

		for (int i = 0; i < dataSet.size(); i++) {
			// 依初超始X坐标与标签间距算出当前刻度的X坐标
			currentX = Math.round(plotArea.getPlotLeft() + (i + 1) * XSteps);

			// 绘制横向网格线
			if (plotGrid.getVerticalLinesVisible()) {
				this.mCanvas.drawLine(currentX, plotArea.getPlotBottom(),
						currentX, plotArea.getPlotTop(),
						this.plotGrid.getVerticalLinesPaint());
			}
			// 画上标签/刻度线
			float currentY = Math.round(plotArea.getPlotBottom() + baseOffsetY + baseTickness+labelHeight);
			currentX = (float) (currentX - baseOffsetX);
			labelsAxis.renderAxisVerticalTick(currentX,currentY, dataSet.get(i));
		}
	}
		
	
	@Override
	protected void renderVerticalBar()
	{		
		renderVerticalBarDataAxis();			
		renderVerticalBarLabelsAxis();
		
		//标签轴(X 轴) 且在这画柱形    
		 float initX= plotArea.getPlotLeft();
		 float currentX = initX;		
			 		
		 //得到标签轴数据集
		List<String> dataSet =  labelsAxis.getDataSet();
				
		// 依传入的标签个数与轴总宽度算出要画的标签间距数是多少
		// 总宽度 / 标签个数 = 间距长度	
		int XSteps = (int) Math.ceil( plotArea.getPlotWidth()/ (dataSet.size() + 1 ));				 			 
	 	//X轴 线
		mBar3D.render3DXAxis(plotArea.getPlotLeft(), plotArea.getPlotBottom(),
							 plotArea.getPlotRight(), plotArea.getPlotBottom(), 
							 this.mCanvas); 
	
		//得到数据源
		List<BarData> chartDataSource = this.getDataSource();
		int barNumber = chartDataSource.size();
		int currNumber = 0;			
		
		List<Integer> ret = mBar3D.getBarWidthAndMargin(XSteps, barNumber);
		int barWidth = ret.get(0);
		int barInnerMargin = ret.get(1);
		int labelBarUseWidth = barNumber * barWidth + (barNumber - 1) * barInnerMargin;		
	
		 
		//开始处 X 轴 即标签轴                  
		for(int i=0;i<barNumber;i++)
		{
		    //依初超始X坐标与标签间距算出当前刻度的X坐标
			currentX = initX + (i+1) * XSteps; 
			
			//得到标签对应的值数据集				
			BarData bd = chartDataSource.get(i);
			List<Double> barValues = bd.getDataSet();
			//设成对应的颜色
			mBar3D.getBarPaint().setColor(bd.getColor());				
			
		   int k=0;					 
		   //画出标签下的所有柱形
		   for(Double bv : barValues)
           {
				//参数值与最大值的比例  照搬到 y轴高度与矩形高度的比例上来					
				float valuePostion = (float) Math.round( 
						plotArea.getPlotHeight() * ( (bv - dataAxis.getAxisMin() ) / dataAxis.getAxisRange())) ;              																
				float drawBarStartX = initX + (k + 1) * XSteps - labelBarUseWidth / 2;
				//计算同标签多柱 形时，新柱形的起始X坐标
				drawBarStartX = drawBarStartX + (barWidth + barInnerMargin ) * currNumber;
				//计算同标签多柱 形时，新柱形的结束X坐标
				float drawBarEndX = drawBarStartX + barWidth;	  					
				
				//画出柱形      
				mBar3D.renderVertical3DBar(drawBarStartX, 
           								(float) Math.round(plotArea.getPlotBottom()  -  valuePostion) , 
				               			drawBarEndX, 
				               			plotArea.getPlotBottom(),
				               			bd.getColor(), this.mCanvas);
        
			
           		//在柱形的顶端显示上柱形的当前值
           		mBar3D.renderBarItemLabel(getFormatterItemLabel(bv),
			                		 (float) Math.round(drawBarStartX + barWidth/2) ,
			                		 (float) Math.round(plotArea.getPlotBottom()  -  valuePostion), mCanvas);            				
				k++;                
           }	
			currNumber ++;				
		}
	 
		//绘制标签各柱形集的说明描述
		drawDataSetKey();
	}
	
	
}
