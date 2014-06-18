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
package org.xclcharts.renderer.plot;


import org.xclcharts.renderer.IRender;
import org.xclcharts.renderer.XChart;
import org.xclcharts.common.DrawHelper;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * @ClassName Legend
 * @Description  图例(Legend)绘制类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * * MODIFIED    YYYY-MM-DD   REASON
 */

public class LegendRender extends Legend implements IRender{
	
	private DrawHelper mDrawHelper =  null;
	
	private XChart mChart = null;
	 	 	
	public LegendRender()
	{
		super();
		mDrawHelper = new DrawHelper();	
		
		getLeftLegendPaint().setTextSize(26);
		getLeftLegendPaint().setColor((int)Color.rgb(255, 153, 204));
		
		getLowerLegendPaint().setTextSize(26);
		getLowerLegendPaint().setColor((int)Color.rgb(58, 65, 83));
		
		getRightLegendPaint().setTextSize(26);
		getRightLegendPaint().setColor((int)Color.rgb(51, 204, 204));		
	}
	
	/**
	 * 传入chart给图例类
	 * @param chart 图基类
	 */
	public void setRange(XChart chart)
	{
		mChart = chart;
	}


	@Override
	public boolean render(Canvas canvas) {
		// TODO Auto-generated method stub
		
		if(null == mChart) return false;
		
		if(this.getLeftLegend().length() > 0)
		{
			drawLeftLegend(canvas,getLeftLegend(),mChart.getChartLeft(),mChart.getChartTop(),
										mChart.getChartRight(),mChart.getChartBottom());
		}
		
		if(this.getLowerLegend().length() > 0)
		{						
			drawLegendLower(canvas,getLowerLegend(),mChart.getChartLeft(),mChart.getChartTop(),
					mChart.getChartRight(),mChart.getChartBottom());
		}
		
		if(this.getRightLegend().length() > 0)
		{
			drawRightLegend(canvas,getRightLegend(),mChart.getChartLeft(),mChart.getChartTop(),
					mChart.getChartRight(),mChart.getChartBottom());
		}
		
		return true;
	}
	
			
	 /**
	  * 绘制左边图例
	  * @param legend	内容
	  * @param left		左边X坐标
	  * @param top		上方Y坐标
	  * @param right	右边X坐标
	  * @param bottom	下方Y坐标
	  */
	public void drawLeftLegend(Canvas canvas, String legend,double left,double top,
											 double right,double bottom) //,Canvas canvas)
	{							
		if(null == canvas) return ;
		
		//是否需要绘制图例
		if( 0 == legend.length() || "" == legend)return;
		
		 //计算图列宽度		 
		 double legendTextHeight = mDrawHelper.getTextWidth(mPaintLeftLegend,legend);
         
		 //画布与图表1/3的地方显示
		 float legendTextStartX = (float) Math.round(left + mPaintLeftLegend.getTextSize()) ; 		 
		 
         //图例Y坐标
		 float legendTextStartY = Math.round(top + (bottom - top ) /2 + legendTextHeight/2);
         
         //得到单个图例文字高度     		
         double legendCharHeight =0d;
         
         for(int i= 0; i< legend.length();i++)
         {        	 
        	 legendCharHeight = mDrawHelper.getTextWidth(mPaintLeftLegend,legend.substring(i, i+1));        	 
        	 //绘制文字，旋转-90得到横向效果
        	 mDrawHelper.drawRotateText(legend.substring(i, i+1),
					        			 legendTextStartX,legendTextStartY,
					        			 -90,
					        			 canvas, mPaintLeftLegend );
        	 legendTextStartY -= legendCharHeight;
         }
	}
	
	/**
	 * 绘制底部图例
	 * @param legend	内容
	 * @param left		左边X坐标
	 * @param top		上方Y坐标
	 * @param right		右边X坐标
	 * @param bottom	下方Y坐标
	 */
	public void drawLegendLower(Canvas canvas, String legend,double left,double top,double right,double bottom)
	{         
         if(null == canvas) return ;
 		 //是否需要绘制图例
 		 if( 0 == legend.length() )return;
 	
 		 //计算图列宽度		 
 		 double legendTextHeight = mDrawHelper.getPaintFontHeight(getLowerLegendPaint());          
 		 //画布与图表1/3的地方显示
 		 float legendTextStartX =  (float) Math.round(left +  (right - left) / 2) ; 		 
          //图例Y坐标
 		// float legendTextStartY = Math.round( bottom - legendTextHeight);
 		// mDrawHelper.drawRotateText(legend,
        //		 legendTextStartX,legendTextStartY,0,mCanvas, getLowerLegendPaint());	
 		 
 		//显示在 Chart与Plot bottom间的中间位置
 		//float legendY =(float) Math.abs((bottom - mChart.getPlotArea().getPlotBottom()) / 2 - (legendTextHeight/2));
 		
 		 float plotBottom =  mChart.getPlotArea().getPlotBottom();
 		 float legendY =(float) Math.abs(
 				plotBottom +
 				 ((bottom - plotBottom) / 2 - (legendTextHeight/2)) );
 		
 		
 		mDrawHelper.drawRotateText(legend,
       		 legendTextStartX,legendY,0,canvas, getLowerLegendPaint());
	}
	
	/**
	 * 绘制右边图例
	 * @param legend	内容
	 * @param left		左边X坐标
	 * @param top		上方Y坐标
	 * @param right		右边X坐标
	 * @param bottom	下方Y坐标
	 */
	public void drawRightLegend(Canvas canvas,String legend,double left,double top,double right,double bottom)
	{			
		if(null == canvas) return ;
		
		//是否需要绘制图例
		if( 0 == legend.length() || "" == legend)return;
		
		//计算图列高度，超过最大高度要用...表示,这个后面再加		 
		 int legendTextHeight = mDrawHelper.getTextWidth(mPaintRightLegend,legend);         
		 //画布与图表1/3的地方显示
		 float legendTextStartX =  (float) Math.round(right - mPaintRightLegend.getTextSize()) ;         
         //图例Y坐标
		 float legendTextStartY = (float) Math.round(top + (bottom - top -  legendTextHeight) /2 );	          
         //得到单个图例文字高度     		
 		 int legendCharHeight = 0 ;
         for(int i= 0; i< legend.length();i++)
         {        	 
        	 legendCharHeight = mDrawHelper.getTextWidth(mPaintRightLegend,legend.substring(i, i+1));        	 
        	 //绘制文字，旋转-90得到横向效果
        	 mDrawHelper.drawRotateText(legend.substring(i, i+1),
        			 legendTextStartX,legendTextStartY,90,canvas, mPaintRightLegend );
        	 legendTextStartY += legendCharHeight;
         }
	
	}


}
