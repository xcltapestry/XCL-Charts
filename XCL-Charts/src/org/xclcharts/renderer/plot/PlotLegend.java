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
 * @version 1.0
 */
package org.xclcharts.renderer.plot;

import org.xclcharts.renderer.XEnum;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * @ClassName PlotLegend
 * @Description 用于设定图例的属性
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *
 */
public class PlotLegend {
	
		//数据集的说明描述与图这间的空白间距
		protected float mMargin  = 10f;	
		//数据集的说明描述画笔
		private Paint mKeyPaint = null;
					
		//是否显示图例
		private boolean mVisible = false;
		
		//图例起始偏移多少距离
		protected float mOffsetX = 0.0f;
		protected float mOffsetY = 0.0f;
		//行间距
		protected float mRowSpan = 10.0f;		
		protected float mColSpan = 10.0f;		
		
		//图例方向
		/*
		private XEnum.LegendType mLegendType = XEnum.LegendType.COLUMN;		
		private XEnum.HorizontalAlign mHorizontalAlign = XEnum.HorizontalAlign.RIGHT;		
		private XEnum.VerticalAlign mVerticalAlign = XEnum.VerticalAlign.MIDDLE;
		 */	
		private XEnum.LegendType mLegendType = XEnum.LegendType.ROW;		
		private XEnum.HorizontalAlign mHorizontalAlign = XEnum.HorizontalAlign.LEFT;
		private XEnum.VerticalAlign mVerticalAlign = XEnum.VerticalAlign.TOP;
	
		//box
		protected BorderRender mBorder = new BorderRender();
		protected boolean mShowBox = true;
		protected boolean mShowBoxBorder = true;
		protected boolean mShowBackground = true;
				
		public PlotLegend() {}

		/**
		 * 在图的上方显示图例
		 * 
		 */
		public void show()
		{
			mVisible = true;
		}
		
		/**
		 * 在图的上方不显示图例
		 */
		public void hide()
		{
			mVisible = false;
			if(null != mKeyPaint) mKeyPaint = null;
		}
						
		/**
		 * 是否需绘制图的图例
		 * @return 是否显示
		 */
		public boolean isShow()
		{
			return mVisible;
		}
		
		/**
		 * 不显示图例框		
		 */
		public void hideBox()
		{
			mShowBox = false;
		}
		
		/**
		 * 不显示图例边框		
		 */
		public void hideBorder()
		{
			mShowBoxBorder = false;
		}
		
		/**
		 * 不显示图例背景	
		 */
		public void hideBackground()
		{
			mShowBackground = false;
		}
		
		/**
		 * 显示图例框		
		 */
		public void showBox()
		{
			mShowBox = true;
			showBorder();
			showBackground();
		}
		
		/**
		 * 显示图例边框		
		 */
		public void showBorder()
		{
			mShowBoxBorder = true;
		}
		
		/**
		 * 显示图背景		
		 */
		public void showBackground()
		{
			mShowBackground = true;
		}
		
		 
		 /**
		  * 开放图例绘制画笔
		  * @return 画笔
		  */
		 public Paint getPaint()
		 {		 
			 if(null == mKeyPaint)
			 {
				mKeyPaint = new Paint();
				mKeyPaint.setColor(Color.BLACK);
				mKeyPaint.setAntiAlias(true);			
				mKeyPaint.setTextSize(15);	
			 }
			 return mKeyPaint;
		 }
		 
		 /**
		  * 设置图例间距
		  * @param margin Key间距
		  */
		 public void setLabelMargin(float margin)
		 {		 
			 mMargin = margin;
		 }
		 
		 
		 /**
		 * 设置行间距
		 * @param span 间距
		 */
		public void setRowSpan(float span)
		{
			mRowSpan = span;		
		}
		
		public void setColSpan(float span)
		{
			mColSpan = span;
		}
			
		 /**
		  * 返回图例间距
		  * @return Key间距
		  */
		 public float getLabelMargin()
		 {
			 return mMargin;
		 }
		 
		 /**
		  * 图例起始向X方向偏移多少距离
		  * @param offset 偏移值
		  */
		 public void setOffsetX(float offset)
		 {		 
			 mOffsetX = offset;
		 }
		 
		 /**
		  * 图例起始向Y方向偏移多少距离
		  * @param offset 偏移值
		  */
		 public void setOffsetY(float offset)
		 {		 
			 mOffsetY = offset;
		 }
		 
		 /**
		  * 图例显示类型:使用行类型横向显示，或使用列类型竖向显示 
		  * @param type 显示类型
		  */
		 public void setType(XEnum.LegendType type)
		 {
			 mLegendType = type;
		 }
		 
		 /**
		  * 返回图例显示类型
		  * @return 显示类型
		  */
		 public XEnum.LegendType getType()
		 {
			 return mLegendType;
		 }
		 		 
		 /**
		  * 设置横向显示方式位置
		  * @param align 位置
		  */
		 public void setHorizontalAlign(XEnum.HorizontalAlign align)
		 {
			 mHorizontalAlign = align;
		 }
		 
		 /**
		  * 返回横向显示方式位置
		  * @return	位置
		  */
		 public XEnum.HorizontalAlign getHorizontalAlign()
		 {
			 return mHorizontalAlign;
		 }		 
		 
		 /**
		  * 设置竖向显示方式位置
		  * @param align 位置
		  */
		 public void setVerticalAlign(XEnum.VerticalAlign align)
		 {
			 mVerticalAlign = align;
		 }
		 
		 /**
		  * 设置竖向显示方式位置
		  * @return	位置
		  */
		 public XEnum.VerticalAlign getVerticalAlign()
		 {
			 return mVerticalAlign;
		 }
		 
		
		 /**
		  * 开放图例框绘制基类
		  * @return  框绘制类
		  */
		 public Border getBox()
		 {		
			 return mBorder;
		 }
		 
		 
}
