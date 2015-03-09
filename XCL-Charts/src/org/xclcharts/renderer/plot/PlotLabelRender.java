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
 * @version 2.0
 */
package org.xclcharts.renderer.plot;

import org.xclcharts.common.DrawHelper;
import org.xclcharts.renderer.XEnum;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;


/**
 * @ClassName PlotLabelRender
 * @Description 用于绘制标签的类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *
 */
public class PlotLabelRender extends PlotLabel{
	
	private RectF mRectBox = null; 
	
	private int mBorderColor = -1;
	
	public PlotLabelRender()
	{
	}
	
	
	@Override
	public boolean drawLabel(Canvas canvas,Paint paint,String label,
									float cX,float cY,float itemAngle,int borderColor)
	{
		mBorderColor = borderColor;		
		return drawLabel(canvas,paint,label, cX, cY, itemAngle);
	}
	
	
	@Override
	public boolean drawLabel(Canvas canvas,Paint paint,String label,
			float cX,float cY,float itemAngle)
	{
		if("" == label||label.length() == 0) return false;
		if(null == canvas || null == paint) return false;
						
		float left = 0.0f,top = 0.0f,right=0.0f,bottom = 0.0f;
		
		float w = getLabelWidth(paint,label);
		float h = getLabelHeight(paint);
		
		float x = cX + mOffsetX ; 
		float y = cY - mOffsetY ; 
		
		if(XEnum.LabelBoxStyle.TEXT ==  mLabelBoxStyle)
		{
			DrawHelper.getInstance().drawRotateText(label,x ,y - mMargin ,itemAngle, canvas, paint);			
			return true;
		}
		
		if(XEnum.LabelBoxStyle.CIRCLE ==  mLabelBoxStyle)
		{
			initBox();
			
			float radius = mRadius;
			if(Float.compare(mRadius, 0.f) == 0|| Float.compare(mRadius, 0.f) == -1)
			{
				try{
				    radius = Math.max(w, h) ;
				    radius = radius /2 + 5.f;
				}catch(Exception ex){
					 radius = 25;
				}
			}					
			y = y - mMargin - radius;						
			canvas.drawCircle(x ,y ,radius, mBorder.getBackgroundPaint());
			
			if(mShowBoxBorder)
				canvas.drawCircle(x ,y , radius, mBorder.getLinePaint());
			
			DrawHelper.getInstance().drawRotateText(label,x ,y,itemAngle, canvas, paint);			
			return true;
		}else{
			
			left =  x - w/2 - mMargin ;
			right =  x + w/2  + mMargin ;
			top = y - h  -  mMargin ;
			bottom = y ;
								
			if(null == mRectBox) mRectBox = new RectF();
			mRectBox.left = left;
			mRectBox.right = right;
			mRectBox.top = top;
			mRectBox.bottom = bottom;	
							
			if(XEnum.LabelBoxStyle.CAPRECT ==  mLabelBoxStyle)
			{
			    drawCapBox(canvas,label,x ,y - mMargin ,itemAngle,paint);
			}else{ //RECT
				drawBox(canvas); 	
				DrawHelper.getInstance().drawRotateText(label,x ,y - mMargin ,itemAngle, canvas, paint);			
			}
			mRectBox.setEmpty();						
		}
	
		return true;
	}
	

	private float getLabelWidth(Paint paint,String label)
	{
		 return DrawHelper.getInstance().getTextWidth(paint, label);
	}
	
	private float getLabelHeight(Paint paint)
	{
		return DrawHelper.getInstance().getPaintFontHeight(paint);
	}	
	
	private void drawBox(Canvas canvas) 
	{			
		initBox();	
		if(mBorderColor != -1)mBorder.setBorderLineColor(mBorderColor);
		mBorder.renderBox(canvas, mRectBox,mShowBoxBorder,mShowBackground);		
	}
	
	//
	private void drawCapBox(Canvas canvas,
			String label,float labelX,float labelY,float labelAngle,Paint labelPaint)
	{		
		float AngleH = mRectBox.width() * mScale; //0.2f ; 
		
		mRectBox.top -= AngleH;
		mRectBox.bottom -= AngleH;
				
		initBox();	
		if(mBorderColor != -1)mBorder.setBorderLineColor(mBorderColor);		
		mBorder.renderCapBox(canvas, mRectBox,AngleH,mShowBoxBorder,mShowBackground);	
		DrawHelper.getInstance().drawRotateText(label,labelX ,labelY -AngleH ,labelAngle,
								canvas, labelPaint);
		
	}
		
}
