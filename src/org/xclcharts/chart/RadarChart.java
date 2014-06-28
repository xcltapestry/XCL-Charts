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
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.LnChart;
import org.xclcharts.renderer.RdChart;
import org.xclcharts.renderer.XChart;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.axis.DataAxis;
import org.xclcharts.renderer.axis.DataAxisRender;
import org.xclcharts.renderer.axis.CategoryAxis;
import org.xclcharts.renderer.axis.CategoryAxisRender;
import org.xclcharts.renderer.line.PlotDot;
import org.xclcharts.renderer.line.PlotDotRender;
import org.xclcharts.renderer.line.PlotLine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.Log;


/**
 * @ClassName RadarChart
 * @Description  雷达图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */
public class RadarChart extends RdChart{
	
	private String TAG = "RadarChart";
	//数据轴
	private DataAxisRender dataAxis  = null;
	//分类轴
	private CategoryAxisRender CategoryAxis  = null;			
	//数据源
	private List<RadarData> mDataSet;	
				
	//用于计算的辅助类
	private MathHelper mCalc = new MathHelper();
	
	//依次存下每个圈上，每个标签节点的X,Y坐标
	private Float[][] mArrayDotX = null;
	private Float[][] mArrayDotY= null;
	
	private Float[][] mArrayLabelX = null;
	private Float[][] mArrayLabelY= null;
	
	
	//依次存下每个标签节点归属的圆心角度
	private Float[] mArrayLabelAgent = null;
	
	//透明度
  	private int mAreaAlpha = 100;
		
	public RadarChart()
	{
		super();
		initChart();
	}
		
	private void initChart()
	{		
		dataAxis = new DataAxisRender();
		CategoryAxis = new CategoryAxisRender();
		dataAxis.setHorizontalTickAlign(Align.LEFT);
		dataAxis.getAxisTickLabelPaint().setTextAlign(Align.RIGHT);	
		dataAxis.setTickMarksVisible(false);	
		
		showKeyLabels();
	}
	
	
	/**
	 * 返回数据轴
	 * @return 数据轴
	 */
	public DataAxis getDataAxis()
	{
		return dataAxis;
	}
	
	/**
	 * 返回分类轴
	 * @return 分类轴
	 */
	public CategoryAxis getCategoryAxis()
	{
		return CategoryAxis;
	}
	
	
	/**
	 * 分类轴的数据源
	 * 
	 * @param dataSeries
	 *            标签集
	 */
	public void setCategories(List<String> dataSeries) {
		CategoryAxis.setDataBuilding(dataSeries);
	}

	/**
	 * 设置数据轴的数据源
	 * 
	 * @param dataSeries
	 *            数据源
	 */
	public void setDataSource(List<RadarData> dataSeries) {
		this.mDataSet = dataSeries;
	}

	/**
	 * 返回图的数据源
	 * @return 数据源
	 */
	public List<RadarData> getDataSource() {
		return mDataSet;
	}
	
	/**
	 * 设置透明度,默认为100
	 * @param alpha 透明度
	 */
	public void setAreaAlpha(int alpha)
	{
		mAreaAlpha = alpha;
	}	

	
	private boolean validateParams()
	{
		//if( avgAgent > 270) return false;
		//if( avgAgent <=0 ) return false;
		//if(labelsCount < 3) return false;
		//if(dataCount < 3) return false;
		
		if(this.CategoryAxis.getDataSet().size() <= 0 )
		{
			Log.e(TAG,"标签数据源为空");
			return false;
		}
		
		if(this.mDataSet.size() <= 0 )
		{
			Log.e(TAG,"数据源为空");
			return false;
		}
		return true;
	}
	
	/**
	 * 用来绘制蜘蛛网线
	 */
	private void renderGridLines(Canvas canvas)
	{				
		Path lnPath = new Path();		
		for(int i=0; i < getAxisTickCount();i++)
		{			
			for(int j=0;j < getPlotAgentNumber();j++)
			{
				if(0 == j)
				{
					lnPath.moveTo( mArrayDotX[i][j], mArrayDotY[i][j]);
				}else{
					lnPath.lineTo( mArrayDotX[i][j], mArrayDotY[i][j]);
				}
			}
			lnPath.close();
			canvas.drawPath(lnPath, getLinePaint());
			lnPath.reset();
		}
	}
	
	
	/**
	 * 绘制各个圆心角的轴线
	 * @param canvas 画布
	 */
	private void renderAxisLines(Canvas canvas)
	{				
		float cirX = plotArea.getCenterX();
		float cirY = plotArea.getCenterY();
		
		//标签个数决定角的个数
		int labelsCount =  getPlotAgentNumber();
		//轴线tick总数
		int dataAxisTickCount = getAxisTickCount();
				
		//轴线总数
		int i = dataAxisTickCount - 1;
		for(int j=0;j < labelsCount ;j++)
		{														
			//用于绘制各个方向上的轴线
			canvas.drawLine(cirX,cirY, mArrayDotX[i][j], 
										mArrayDotY[i][j], getLinePaint());									
												      
		} //end for labels													
	}
	
	
	/**
	 * 绘制最外围的标签及主轴的刻度线标签
	 * @param canvas 画布
	 */
	private void renderAxisLabels(Canvas canvas)
	{				
		//标签个数决定角的个数
		int labelsCount =  getPlotAgentNumber();
		//轴线tick总数
		int dataAxisTickCount = getAxisTickCount();
						
		for(int i=0; i < dataAxisTickCount;i++)
		{			
			for(int j=0;j < labelsCount ;j++)
			{											
				//绘制最外围的标签
				if(i == dataAxisTickCount - 1  )
				{		
					//绘制最外围的标签		
					 String label = CategoryAxis.getDataSet().get(j);					
				        					
				     canvas.drawText(label, 
				    		 mArrayLabelX[i][j], mArrayLabelY[i][j], getLabelPaint());   					
				}
				
				//绘制主轴的刻度线与标签
				if(0 == j){ //显示在第一轴线上(即270度的那根线)										
					//绘制主轴(DataAxis)的刻度线				
					double tick = this.dataAxis.getAxisSteps() * i + dataAxis.getAxisMin();										
					dataAxis.renderAxisHorizontalTick(canvas,
							 mArrayDotX[i][j], mArrayDotY[i][j],
							 Double.toString(tick));
				}
			      
			} //end for labels		
									
		} //end for data
	}
	
	
	/**
	 * 轴线上tick总个数
	 * @return 总个数
	 */
	private int getAxisTickCount()
	{		
		return (int) Math.round(dataAxis.getAixTickCount() + 1);
	}
	
	/**
	 * 标签个数决定了图中角的个数
	 * @return 标签总个数
	 */
	private int getPlotAgentNumber()
	{
		return CategoryAxis.getDataSet().size();
	}
	
	private void calcAllPoint()
	{				
		float cirX = plotArea.getCenterX();
		float cirY = plotArea.getCenterY();
		
		//标签个数决定角的个数
		int labelsCount =  getPlotAgentNumber();
		//轴线tick总数
		int dataAxisTickCount = getAxisTickCount();
				
		//扇形角度,依标签个数决定 				
		float pAngle = 360 / labelsCount ; //   72f; 
		
		//270为中轴线所处圆心角
		float initOffsetAgent = 270f - pAngle;
		
		//依标签总个数算出环数,依数据刻度数决定
		float avgRadius = getRadius() / (dataAxisTickCount - 1);
		
		//当前半径
		float curRadius = 0.0f;
		//当前圆心角偏移量
		float offsetAgent = 0.0f;
		
		//坐标与圆心角
		mArrayDotX=new Float[dataAxisTickCount][labelsCount]; 
		mArrayDotY=new Float[dataAxisTickCount][labelsCount]; 		
		mArrayLabelAgent = new Float[labelsCount];
		
		mArrayLabelX=new Float[dataAxisTickCount][labelsCount]; 
		mArrayLabelY=new Float[dataAxisTickCount][labelsCount]; 
		
		DrawHelper dw = new DrawHelper();
		int labelHeight = dw.getPaintFontHeight(getLabelPaint());
		float labelRadius = this.getRadius() + labelHeight;
				
		for(int i=0; i < dataAxisTickCount;i++) //数据轴
		{
			curRadius = avgRadius * i; //当前半径长度，依此算出各节点坐标	
			
			for(int j=0;j < labelsCount ;j++)
			{				
				offsetAgent = initOffsetAgent +   pAngle * j;
				
				//计算位置
		        mCalc.calcArcEndPointXY(cirX,cirY,curRadius, offsetAgent + pAngle); 				    
		        //点的位置
		        mArrayDotX[i][j] = mCalc.getPosX();
		        mArrayDotY[i][j] = mCalc.getPosY();	
		        
		        //记下每个标签对应的圆心角
		        if(0 == i) mArrayLabelAgent[j] =  offsetAgent + pAngle ;
		        
		        //外围标签位置
		        mCalc.calcArcEndPointXY(cirX,cirY,labelRadius, offsetAgent + pAngle); 	    
		        mArrayLabelX[i][j] = mCalc.getPosX();
		        mArrayLabelY[i][j] = mCalc.getPosY();	
		        
		        
			} //end for labelCount					
		} //end for datacount		
				
	}
	
	private void renderDataArea(Canvas canvas)
	{
		float cirX = plotArea.getCenterX();
		float cirY = plotArea.getCenterY();
			
		for(RadarData lineData : mDataSet)
		{			
			//画各自的网
			List<Double> dataset =  lineData.getLinePoint();
			
			int dataSize = dataset.size();
			if(dataSize < 3)
			{
				Log.e("ERROR-RadarChart", "这几个数据可不够，最少三个起步.");
				continue;
			}
			
			Float[] arrayDataX = new Float[dataSize]; 
			Float[] arrayDataY = new Float[dataSize]; 
											
			int i = 0;
			for(Double data : dataset){
			   Double per = (data - dataAxis.getAxisMin() ) / dataAxis.getAxisRange();
			   float curRadius = (float) (getRadius() * per);
						   
			   //计算位置
		        mCalc.calcArcEndPointXY(cirX,cirY,curRadius, mArrayLabelAgent[i]); 
		        
		        //依Path还是Line来决定画线风格
		        arrayDataX[i] = mCalc.getPosX();
		        arrayDataY[i] = mCalc.getPosY();
		        
		        i++; //标签
			}
			
			//画线或填充
			switch(lineData.getAreaStyle())
			{
			case FILL:
				drawDataPath(canvas,lineData,arrayDataX,arrayDataY);
					break;
			case STROKE:
				renderDataLine(canvas,lineData,arrayDataX,arrayDataY);
				break;
			default:
				Log.e("ERROR-RadarChart","这类型不认识.");
			}
		}
        
	}
	
	private void renderDataLine(Canvas canvas,
								RadarData lineData,
								Float[] arrayDataX,Float[] arrayDataY )
	{
		float startX = 0.0f,startY = 0.0f;
		float initX = 0.0f,initY = 0.0f;
		DrawHelper drawHelper = new DrawHelper();		
		
		for(int p=0;p< arrayDataX.length;p++)
		{
			if(0 == p){
				initX = startX = arrayDataX[p];
				initY = startY = arrayDataY[p];
			}else{	
				drawHelper.drawLine(lineData.getLineStyle(), startX, startY,
						arrayDataX[p], arrayDataY[p],
						canvas, lineData.getPlotLine().getLinePaint());
				
								
				startX = arrayDataX[p];
				startY = arrayDataY[p];
			
			}
			//绘制点及对应的标签
			renderDotAndLabel(canvas,lineData,arrayDataX[p], arrayDataY[p],p);
			
		}
		//收尾
		drawHelper.drawLine(lineData.getLineStyle(), startX, startY,
				initX, initY,canvas, lineData.getPlotLine().getLinePaint());
		
	}
	
	
	private void drawDataPath(Canvas canvas,
			RadarData lineData,
			Float[] arrayDataX,Float[] arrayDataY )
	{
		float startX = 0.0f,startY = 0.0f;
		float initX = 0.0f,initY = 0.0f;
		
		Path pathArea = new Path();  

		for(int p=0;p< arrayDataX.length;p++)
		{
			if(0 == p){
				initX = startX = arrayDataX[p];
				initY = startY = arrayDataY[p];
				
				pathArea.moveTo(initX,initY);
			}else{
				pathArea.lineTo(arrayDataX[p] , arrayDataY[p]);   
				startX = arrayDataX[p];
				startY = arrayDataY[p];
			} 
			//绘制点及对应的标签
			renderDotAndLabel(canvas,lineData,arrayDataX[p], arrayDataY[p],p);
		}
		//收尾
		pathArea.lineTo(initX,initY);
		pathArea.close(); 
		
		lineData.getPlotLine().getLinePaint().setAlpha(mAreaAlpha);
		//lineData.getPlotLine().getLinePaint().setStyle(Style.STROKE);
		canvas.drawPath(pathArea, lineData.getPlotLine().getLinePaint());
	}
	
	private void renderDotAndLabel(Canvas canvas,
			RadarData lineData,
			//float previousX,float previousY,
			float currentX,float currentY,
			int listID)
	{
		PlotLine plotLine = lineData.getPlotLine();
		
		if(!plotLine.getDotStyle().equals(XEnum.DotStyle.HIDE))
    	{                		       	
    		PlotDot pDot = plotLine.getPlotDot();	  
    		PlotDotRender dotRender = new PlotDotRender();           		
			dotRender.renderDot(canvas,pDot,
    				currentX - pDot.getDotRadius() , currentY - pDot.getDotRadius(),
    				currentX , currentY,
    				lineData.getPlotLine().getDotPaint()); //标识图形            			                	
			
    	}
		//是否显示标签
		if(lineData.getLabelVisible())
		{			
			canvas.drawText(getFormatterDotLabel(lineData.getLinePoint().get(listID) ),
							currentX, currentY, 
							lineData.getPlotLine().getDotLabelPaint());
		}
		
	}

	
	
	/**
	 * 绘制key
	 */
	protected void renderKey(Canvas canvas)
	{
		 if(!getKeyVisible())return ;
		    
			DrawHelper dw = new DrawHelper();
			float textHeight = dw.getPaintFontHeight(getKeyPaint());
			float rectWidth = 2 *textHeight;		
			float currentX = 0.0f; 				
			float currentY = 0.0f;
			
			if(!isVerticalScreen()) //横屏
			{
				getKeyPaint().setTextAlign(Align.RIGHT);
				currentX = plotArea.getRight();
				currentY = this.plotArea.getTop() + textHeight;			
			}else{
				getKeyPaint().setTextAlign(Align.LEFT);
				currentX = plotArea.getLeft();
				currentY = this.plotArea.getBottom();			
			}			
			
			int totalTextWidth = 0;
			for(RadarData cData : mDataSet)
			{
				getKeyPaint().setColor(cData.getLineColor());							
				if( !isVerticalScreen()) //横屏
				{								
					canvas.drawRect(currentX			 , currentY,
										  currentX - rectWidth, currentY - textHeight, 
										  getKeyPaint());					
					
					canvas.drawText(cData.getLineKey(),currentX - rectWidth, currentY, getKeyPaint());
					currentY += textHeight;
				
				}else{ //竖屏			
					int keyTextWidth = dw.getTextWidth(getKeyPaint(), cData.getLineKey());
					totalTextWidth += keyTextWidth;
					
					if(totalTextWidth > plotArea.getWidth())
					{
						currentY += textHeight;
						currentX = plotArea.getLeft();
						totalTextWidth = 0;
					}				
					canvas.drawRect(currentX			   , currentY,
									 currentX + rectWidth, currentY - textHeight, 
									 getKeyPaint());						
					canvas.drawText(cData.getLineKey(), currentX + rectWidth, currentY, getKeyPaint());
					currentX += rectWidth + keyTextWidth + 5;
				}									
			}	
	}
	
	/**
	 * 绘制图
	 */
	protected void renderPlot(Canvas canvas)
	{
		if(!validateParams()) return;
		calcAllPoint();
		renderGridLines(canvas);
		renderAxisLines(canvas);
		renderDataArea(canvas);
		renderAxisLabels(canvas);		
		renderKey(canvas);
	}

	@Override
	protected boolean postRender(Canvas canvas) throws Exception 
	{	
		try {
			super.postRender(canvas);
			
			//计算主图表区范围
			 calcPlotRange();
			//画Plot Area背景			
			 plotArea.render(canvas);			 
			//画奇偶行填充,横竖网格线			
			// plotGrid.render();			
			 
			//绘制标题
			renderTitle(canvas);
			
			//绘制图表
			renderPlot(canvas);
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
	

}
