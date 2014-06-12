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

package org.xclcharts.chart.common;

import java.util.Random;

import org.xclcharts.renderer.XEnum;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Path;
import android.graphics.PathEffect;

/**
 * @ClassName DrawHelper
 * @Description  集中了绘制中，相关的一些小函数
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */


public class DrawHelper {

	public DrawHelper()
	{
	
	}
	
	
	/**
	 *  得到一个随机颜色
	 * @return 随机颜色
	 */
	public int randomColor() {
	    Random random = new Random();
        int red = random.nextInt(256) ;
        int green = random.nextInt(256) ;
        int blue = random.nextInt(256);
        return Color.rgb(red, green, blue);
    }
	
	/**
	 * 通过透明度，算出对应颜色的浅色应当是什么效果
	 * @param color 颜色
	 * @param alpha 透明度
	 * @return 浅色
	 */
	public int getLightColor(int color,int alpha)
	{
		Paint paint = new Paint();
		paint.setColor(color);
		paint.setAlpha(alpha);
		return paint.getColor();
	}
	
	/**
	 * 得到深色
	 * @param color 颜色
	 * @return 深色
	 */
	public int getDarkerColor(int color){
	    float[] hsv = new float[3];
	    Color.colorToHSV(color, hsv);	   
	    hsv[1] = hsv[1] + 0.1f; 
	    hsv[2] = hsv[2] - 0.1f; 
	    int darkerColor = Color.HSVToColor(hsv);
	    return  darkerColor ;
	}	
	
	/**
	 * 得到单个字的高度
	 * @param paint 画笔
	 * @return 高度
	 */
	public int getPaintFontHeight(Paint paint)
	{
	     FontMetrics fm = paint.getFontMetrics();
		 int charHeight = (int) Math.ceil(fm.descent - fm.ascent);
		 return charHeight;
	}
	 
	/**
	 * 得到字符串的宽度
	 * @param paint 画笔
	 * @param str 字符串
	 * @return 宽度
	 */
	public int getTextWidth(Paint paint,String str)
	{
		 int width = (int) Math.abs(paint.measureText(str, 0, str.length()));		 
		 return width;
	}
	
	/**
	 *  用于计算文字的竖直累加高度
	 * @param paint 画笔
	 * @param str   字符串
	 * @return 高度
	 */
	public int calcTextHeight(Paint paint,String str)
	{		
		return getPaintFontHeight(paint) * str.length();
	}
	
	/**
	 *  绘制旋转了指定角度的文字
	 * @param text	文字
	 * @param x		X坐标
	 * @param y		y坐标
	 * @param paint	画笔
	 * @param angle 角度
	 */
	public void drawRotateText(  String text , 
									float x ,float y,float angle,
									Canvas canvas,
									Paint paint 
									){
		if(angle != 0){
			canvas.rotate(angle, x, y); 
		}
		canvas.drawText(text, x, y, paint);
		if(angle != 0){
			canvas.rotate(-angle, x, y); 
		}
	}			
	
	/**
	 * 绘制等腰三角形
	 * @param baseLine		底线长度
	 * @param baseLnCentX	底线中心点X坐标
	 * @param baseLnCentY	底线中心点Y坐标
	 * @param direction		三角形方向
	 * @param style			填充风格
	 * @param canvas		画布
	 * @param paint			画笔
	 */
	public void drawTrigangle(	float baseLine,
								float baseLnCentX,
								float baseLnCentY,
								XEnum.TrigangleDirection direction,
								XEnum.TrigangleStyle style,
								Canvas canvas,
								Paint paint)
	{
		 // 计算偏移量
		   int offset = (int)(baseLine / 2 * Math.tan(60 * Math.PI / 180));
		   
		   Path path = new Path();
		   
		   // 计算三角形3个顶点的坐标
	        switch (direction)
	        {
	            case UP: //向上

	                path.moveTo(baseLnCentX- baseLine / 2 , baseLnCentY);
	                path.lineTo(baseLnCentX + baseLine / 2,baseLnCentY);
	                path.lineTo(baseLnCentX,baseLnCentY - offset);
	                path.close();
	                break;
	            
	            case DOWN: //向下

	                path.moveTo( baseLnCentX - baseLine / 2 , baseLnCentY);
	                path.lineTo(baseLnCentX + baseLine / 2,baseLnCentY);
	                path.lineTo(baseLnCentX,baseLnCentY + offset);
	                path.close();

	                break;
	            case LEFT: //向左

	                path.moveTo(baseLnCentX , baseLnCentY - baseLine / 2);
	                path.lineTo(baseLnCentX , baseLnCentY + baseLine / 2);
	                path.lineTo(baseLnCentX - offset ,baseLnCentY);
	                path.close();

	                break;
	            case RIGHT: //向右
	        
	                path.moveTo(baseLnCentX , baseLnCentY - baseLine / 2);
	                path.lineTo(baseLnCentX , baseLnCentY + baseLine / 2);
	                path.lineTo(baseLnCentX + offset ,baseLnCentY);
	                path.close();
	                break;
	        }
	  
	        
	        //三角形的填充风格
	        switch (style)
	        {
	            case OUTLINE : //空心
	                paint.setStyle(Paint.Style.STROKE);  
	                break;
	            case FILL : //FILL 
	               paint.setStyle(Paint.Style.FILL);  
	               break;
	        }
	        canvas.drawPath(path,paint);		 		 
	}
	
	/**
	 * 绘制点
	 * @param startX	起始点X坐标
	 * @param startY	起始点Y坐标
	 * @param stopX		终止点X坐标
	 * @param stopY		终止点Y坐标
	 * @param canvas	画布
	 * @param paint		画笔
	 */
	public void drawDotLine(float startX,float startY,
			 float stopX,float stopY,							 
			 Canvas canvas,
			 Paint paint)
	{
		PathEffect effects = new DashPathEffect(new float[] { 2, 2, 2, 2}, 1);  
		paint.setPathEffect(effects);  
		canvas.drawLine(startX, startY, stopX, stopY, paint); 
	}
	
	/**
	 * 绘制虚实线
	 * @param startX	起始点X坐标
	 * @param startY	起始点Y坐标
	 * @param stopX		终止点X坐标
	 * @param stopY		终止点Y坐标
	 * @param canvas	画布
	 * @param paint		画笔
	 */
	public void drawDashLine(float startX,float startY,
							 float stopX,float stopY,							 
							 Canvas canvas,
							 Paint paint)
	{
		//虚实线
		PathEffect effects = new DashPathEffect(new float[] { 4, 8, 5, 10}, 1);  
		paint.setPathEffect(effects);  
		canvas.drawLine(startX, startY, stopX, stopY, paint);  		
	}
	
	public void drawLine(XEnum.LineDashStyle style,
				float startX,float startY,
				float stopX,float stopY,			 
				Canvas canvas,
				Paint paint
			 	)
	{
		
		switch(style)
		{
		case SOLID:		
			canvas.drawLine(startX, startY, stopX, stopY, paint); 
			break;
		case DOT:
			drawDotLine(startX, startY, stopX, stopY, canvas,paint); 
			break;
		case DASH:		
			//虚实线 	
			drawDashLine(startX, startY, stopX, stopY, canvas,paint); 
			break;
		}		
	}
		
}
