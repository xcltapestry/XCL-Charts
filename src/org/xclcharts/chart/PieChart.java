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
import org.xclcharts.renderer.CirChart;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.Log;

/**
 * @ClassName PieChart
 * @Description  饼图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */
public class PieChart extends CirChart{

	//数据源
	private List<PieData> mDataSet;
	//绘制Key的画笔
	private Paint mPaintKey = null;
	//是否显示Key
	private boolean mKeyVisible = true;
	
	public PieChart()
	{
		super();
		
		mPaintKey = new Paint();
		mPaintKey.setColor(Color.BLACK);
		mPaintKey.setTextSize(18);
		mPaintKey.setStyle(Style.FILL);	
		mPaintKey.setAntiAlias(true);
	}
	
	/**
	 * 开放Key画笔
	 * @return 画笔
	 */
	public Paint getKeyPaint()
	{
		return mPaintKey;
	}
	
	/**
	 * 是否绘制Key
	 * @return Key值
	 */
	public boolean getKeyVisible()
	{
		return mKeyVisible;
	}
	
	/**
	 * 设置是否绘制Key
	 * @param visible 是否绘制Key
	 */
	public void setKeyVisible(boolean visible)
	{
		mKeyVisible = visible;
	}

	/**
	 * 设置图表的数据源
	 * @param piedata 来源数据集
	 */
	public void setDataSource(List<PieData> piedata)
	{
		this.mDataSet = piedata;
	}
	
	/**
	 * 返回数据轴的数据源
	 * @return 数据源
	 */
	public List<PieData> getDataSource()
	{
		return mDataSet;
	}

		
	/**
	 * 绘制指定角度扇区
	 * @param paintArc 画笔
	 * @param arcRF0   范围
	 * @param cData  数据集
	 * @param cirX   中心点X坐标
	 * @param cirY   中心点Y坐标
	 * @param radius  半径
	 * @param offsetAgent 偏移角度
	 * @param curretAgent 当前绘制角度
	 * @throws Exception  例外处理
	 */
	protected void drawSlice( Paint paintArc,RectF arcRF0,
							PieData cData,
							final float cirX,
							final float cirY,
							final float radius,
							final float offsetAgent,
							final float curretAgent) throws Exception
	{
		try{
		
			//在饼图中显示所占比例  
        	mCanvas.drawArc(arcRF0, offsetAgent, curretAgent, true, paintArc);                 
         
            //标签
        	drawLables(cData.getLabel(),cirX, cirY,
	        			radius,offsetAgent,curretAgent);          
		}catch( Exception e){
			throw e;
		}
	}
		
	
	/**
	 * 绘制指定角度扇区
	 * @param paintArc 画笔
	 * @param cData 数据集
	 * @param cirX  中心点X坐标
	 * @param cirY  中心点Y坐标
	 * @param radius 半径
	 * @param offsetAgent 偏移角度
	 * @param curretAgent 当前绘制角度
	 * @throws Exception  例外处理
	 */
	protected void drawSelectedSlice( Paint paintArc,
									PieData cData,
									final float cirX,
									final float cirY,
									final float radius,
									final float offsetAgent,
									final float curretAgent) throws Exception
	{
		try{
			//偏移圆心点位置(默认偏移半径的1/10)
	    	float newRadius = radius /10;
	    	 //计算百分比标签
	        mCalc.CalcArcEndPointXY(cirX,cirY,newRadius,offsetAgent + curretAgent/2); 	
	        
	        float arcLeft = mCalc.getPosX() - radius;  
	        float arcTop  = mCalc.getPosY() - radius ;  
	        float arcRight = mCalc.getPosX() + radius ;  
	        float arcBottom = mCalc.getPosY() + radius ;  
	        RectF arcRF1 = new RectF(arcLeft ,arcTop,arcRight,arcBottom);   
	        
	        //在饼图中显示所占比例  
	        mCanvas.drawArc(arcRF1, offsetAgent, curretAgent, true, paintArc);     
	        
	        //标签
	        drawLables(cData.getLabel(),mCalc.getPosX(), mCalc.getPosY(),
	        			radius,offsetAgent,curretAgent);	   
	        
		}catch( Exception e){
			 throw e;
		}
	}
	
	/**
	 * 绘制key
	 */
	protected void renderKey()
	{
		
		  if(!getKeyVisible())return ;
		    
			DrawHelper dw = new DrawHelper();
			float textHeight = dw.getPaintFontHeight(this.mPaintKey);
			float rectWidth = 2 *textHeight;		
			float currentX = 0.0f; 				
			float currentY = 0.0f;
			
			if(!isVerticalScreen()) //横屏
			{
				mPaintKey.setTextAlign(Align.RIGHT);
				currentX = plotArea.getPlotRight();
				currentY = this.plotArea.getPlotTop() + textHeight;			
			}else{
				mPaintKey.setTextAlign(Align.LEFT);
				currentX = plotArea.getPlotLeft();
				currentY = this.plotArea.getPlotBottom();			
			}			
			
			int totalTextWidth = 0;
			for(PieData cData : mDataSet)
			{
				mPaintKey.setColor(cData.getSliceColor());							
				if( !isVerticalScreen()) //横屏
				{								
					this.mCanvas.drawRect(currentX			 , currentY, 
										  currentX - rectWidth, currentY - textHeight, 
										  mPaintKey);					
					
					mCanvas.drawText(cData.getKey(),currentX - rectWidth, currentY, mPaintKey);			
					currentY += textHeight;
				
				}else{ //竖屏			
					int keyTextWidth = dw.getTextWidth(mPaintKey, cData.getKey());
					totalTextWidth += keyTextWidth;
					
					if(totalTextWidth > plotArea.getPlotWidth())
					{
						currentY += textHeight;
						currentX = plotArea.getPlotLeft();
						totalTextWidth = 0;
					}				
					mCanvas.drawRect(currentX			   , currentY, 
									 currentX + rectWidth, currentY - textHeight, 
									 mPaintKey);						
					mCanvas.drawText(cData.getKey(), currentX + rectWidth, currentY, mPaintKey);					
					currentX += rectWidth + keyTextWidth + 5;
				}									
			}	
	}

	/**
	 * 绘制图
	 */
	protected void renderPlot()
	{
		try{	
			
			//中心点坐标
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
			
			//用于存放当前百分比的圆心角度
	        float currentAgent = 0.0f;		
	        //float totalAgent = 0.0f;
			
			for(PieData cData : mDataSet)
			{
				paintArc.setColor(cData.getSliceColor());				
				currentAgent = cData.getSliceAgent();		
				
			    if(cData.getSelected()) //指定突出哪个块
	            {			    	            		            	
	            	drawSelectedSlice(paintArc,cData,
	            			cirX,cirY,radius,
	            			mOffsetAgent,currentAgent);			    		            		            		            
	            }else{
	            	drawSlice(paintArc,arcRF0,cData,
	            			cirX,cirY,radius,
	            			mOffsetAgent,currentAgent);	            	
	            }
	          //下次的起始角度  
	            mOffsetAgent += currentAgent;  
			}					
			renderKey();
		
		 }catch( Exception e){
			 Log.e("ERROR-PieChart",e.toString());
		 }
		
	}
	
	/**
	 * 检验传入参数,累加不能超过360度
	 * @return 是否通过效验
	 */
	protected boolean checkInput()
	{
		float totalAgent = 0.0f;				
		for(PieData cData : mDataSet)
		{
			totalAgent += cData.getSliceAgent();
			if( totalAgent > 360)
			{
				//圆心角总计大于360度
				Log.e("PieChart","传入参数不合理，圆心角总计大于360度. 现有圆心角合计:"+Float.toString(totalAgent));
				return false;
			}
		}
		return true;
	}
	
	public boolean render() throws Exception {
		// TODO Auto-generated method stub
	
		try {
			super.render();
			
			//检查值是否合理
	        if(false == checkInput())return false;
			
			//绘制图表
			renderPlot();
			
		}catch( Exception e){
			 throw e;
		}
		return true;
	}
	
	
	
}
