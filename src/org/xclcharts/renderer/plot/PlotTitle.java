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

import org.xclcharts.renderer.XEnum;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * @ClassName PlotTitle
 * @Description 标题类,定制其属性
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * * MODIFIED    YYYY-MM-DD   REASON
 */

public class PlotTitle {	

	//图表标题文字
	private  String mChartTitle = "";
	private  String mChartSubTitle = "";	
	//图表标题画笔
	private Paint mChartTitlePaint = null;
	private Paint mChartSubTitlePaint = null;	
	//图表标题显示位置
	private XEnum.ChartTitleAlign mChartTitleAlign = XEnum.ChartTitleAlign.CENTER;	
	//标题的显示位置(TOP,CENTER,BOTTOM)即是否靠最上面，还是Chart top与Plot top的中间位置，还是PLOT TOP的位置
	private XEnum.Postion mChartTitlePostion = XEnum.Postion.CENTER;
	
	public PlotTitle()
	{
		initPaint();
	}
	
	private void initPaint()
	{
		//标题
		//图表标题画笔
		mChartTitlePaint  = new Paint();
		mChartSubTitlePaint  = new Paint();
		mChartTitlePaint.setTextSize(32);
		mChartSubTitlePaint.setTextSize(22);
		
		mChartTitlePaint.setColor(Color.BLACK);
		mChartSubTitlePaint.setColor(Color.BLACK);	
		
		mChartTitlePaint.setAntiAlias(true);
		mChartSubTitlePaint.setAntiAlias(true);		
	}
	

		/**
		 * 设置标题
		 * @param title 标题内容
		 */
		public void setChartTitle(String title)
		{
			mChartTitle = title;		
		}
		
		/**
		 * 返回标题
		 * @return 标题
		 */
		public String getChartTitle()
		{
			return mChartTitle;
		}
		
		
		/**
		 * 设置子标题
		 * @param subtitle 子标题
		 */
		public void setChartSubTitle(String subtitle)
		{
			mChartSubTitle = subtitle;					
		}
		
		/**
		 * 返回子标题
		 * @return 子标题
		 */
		public String getChartSubTitle()
		{
			return mChartSubTitle;
		}
		 
		
		/**
		 * 开放标题画笔
		 * @return 画笔
		 */
		public Paint getChartTitlePaint()
		 {
			 return mChartTitlePaint ;
		 }

		
		/**
		 * 开放子标题画笔
		 * @return 画笔
		 */
		public Paint getChartSubTitlePaint()
		 {
			 return mChartSubTitlePaint ;
		 }
				
		/**
		 * 设置标题横向显示位置(靠左，居中，靠右)
		 * @param align 横向显示位置
		 */
		public void setChartTitleAlign(XEnum.ChartTitleAlign align)
		{
			mChartTitleAlign = align;
		}
			
		/**
		 * 返回标题横向显示位置
		 * @return 横向显示位置
		 */
		public XEnum.ChartTitleAlign getChartTitleAlign()
		{
			return mChartTitleAlign;
		}
		
		/**
		 * 设置标题上下显示位置,即图上边距与绘图区间哪个位置(靠上，居中，靠下).
		 * @param postion  上下显示位置
		 */
		public void setChartTitlePosition(XEnum.Postion postion)
		{
			mChartTitlePostion = postion;
		}
			
		/**
		 * 设置标题上下显示位置
		 * @return 上下显示位置
		 */
		public XEnum.Postion getChartTitlePosition()
		{
			return mChartTitlePostion;
		}
		
		//
		//public void setOffsetSpacing(double offset)
		//{
			
		//}
	
}
