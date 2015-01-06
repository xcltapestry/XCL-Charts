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
 * @version 1.3
 */

package org.xclcharts.renderer.plot;

import org.xclcharts.renderer.XEnum;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

/**
 * @ClassName Pointer
 * @Description 指针类,定制其属性
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * 
 */

public class Pointer {
	
	private Paint mPaintPoint = null;
	private Paint mPaintBaseCircle = null;
	
	protected float mCenterX = 0.0f;
	protected float mCenterY = 0.0f;

	protected float mPercentage = 0.0f;
	protected float mPointerRadiusPercentage = 0.9f;	
	protected float mPointerTailRadiusPercentage = 0.0f;
	
	protected float mBaseRadius = 20f;
	
	private XEnum.PointerStyle mPointStyle = XEnum.PointerStyle.LINE;	
	private boolean mShowBaseCircle = true;
	
	
	public Pointer()
	{
		
	}
	
	/**
	 * 设置指针显示风格
	 * @param style 显示风格
	 */
	public void setPointerStyle(XEnum.PointerStyle style)
	{
		mPointStyle = style;
	}			
	
	/**
	 * 返回指针显示风格
	 * @return 显示风格
	 */
	public XEnum.PointerStyle getPointerStyle()
	{
		return mPointStyle;
	}
	
	/**
	 * 设置指针长度
	 * @param radiusPercentage  占总半径的比例
	 */
	 public void setLength(float radiusPercentage )
	 {		 
		 setLength(radiusPercentage,0);
	 }
	 
	 /**
	  * 设置指针长度
	  * @param radiusPercentage		占总半径的比例
	  * @param tailRadiusPercentage 尾部延长占总半径的比例
	  */
	 public void setLength(float radiusPercentage,float tailRadiusPercentage )
	 {		 
		 mPointerRadiusPercentage = radiusPercentage; 
		 mPointerTailRadiusPercentage = tailRadiusPercentage;
	 }
	 
			
	/**
	 * 开放指针画笔
	 * @return 指针画笔
	 */
	public Paint getPointerPaint()
	{
		if(null == mPaintPoint)
		{
			mPaintPoint = new Paint();
			mPaintPoint.setColor(Color.rgb(235, 138, 61));
			mPaintPoint.setStrokeWidth(3);
			mPaintPoint.setStyle(Style.FILL); 
			mPaintPoint.setAntiAlias(true);
		}
		return mPaintPoint;
	}
	
	/**
	 * 开放指针底部圆画笔
	 * @return 底部圆画笔
	 */
	public Paint getBaseCirclePaint()
	{
		if(null == mPaintBaseCircle)
		{
			mPaintBaseCircle = new Paint();
			mPaintBaseCircle.setStyle(Style.FILL);
			mPaintBaseCircle.setAntiAlias(true);	
			mPaintBaseCircle.setColor(Color.rgb(235, 138, 61));
			mPaintBaseCircle.setStrokeWidth(8);
		}
		return mPaintBaseCircle;
	}
	
	/**
	 * 开放指针底部半径
	 * @param radius 半径
	 */
	public void setBaseRadius(float radius)
	{
		mBaseRadius = radius;
	}
	
	/**
	 * 返回指针底部半径
	 * @return 半径
	 */
	public float getBaseRadius()
	{
		return mBaseRadius;
	}
	
	/**
	 * 不绘制底部圆
	 */
	public void hideBaseCircle()
	{
		mShowBaseCircle = false;
	}
	
	/**
	 * 绘制底部圆
	 */
	public void showBaseCircle()
	{
		mShowBaseCircle = true;
	}
	
	/**
	 * 是否绘制底部圆
	 * @return 是否绘制
	 */
	public boolean isShowBaseCircle()
	{		
		return mShowBaseCircle;
	}
		
	
	/**
	 * 设置指针指向的值，即当前比例(0 - 1)
	 * @param percentage 当前比例
	 */
	public void setPercentage(float percentage)
	{
		mPercentage = percentage;
	}
	
	/**
	 * 返回当前指针指向的比例
	 * @return 比例
	 */
	public float getPercentage()
	{
		return mPercentage;
	}
	
}
