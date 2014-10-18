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
 * @version 1.7
 */
package org.xclcharts.renderer.info;

import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.line.PlotDot;

import android.graphics.Paint;
import android.graphics.Paint.Align;

/**
 * @ClassName ToolTip
 * @Description tooltip基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */
public class ToolTip extends DyInfo{
	
	public ToolTip()
	{
	
	}
	
	/*
	public void showRectHint()
	{
		setStyle(XEnum.DyInfoStyle.RECT);
	}
	
	public void showRoundRectHint()
	{
		setStyle(XEnum.DyInfoStyle.ROUNDRECT);
	}
	*/
	
	/**
	 * 设置是方形不是椭圆形框
	 * @param style 框显示风格
	 */
	public void setInfoStyle(XEnum.DyInfoStyle style)
	{
		setStyle(style);
	}
	
	/**
	 * 设置椭圆形边框半径
	 * @param x		半径
	 * @param y		半径
	 */
	public void setRoundRadius(float x,float y)
	{
		setStyle(XEnum.DyInfoStyle.ROUNDRECT);
		setRoundRectX(x);
		setRoundRectY(y);
	}
	
	/**
	 * 信息框显示在哪个位置
	 * @param align	位置
	 */
	public void setAlign(Align align)
	{
		mPositionAlign = align;
	}
	
	/**
	 * 点击位置坐标
	 * @param x		x坐标
	 * @param y		y坐标
	 */
	public void setCurrentXY(float x,float y)
	{
		setCenterXY(x,y);
	}
	
	/**
	 * 增加提示信息
	 * @param text	文本
	 * @param paint	绘制画笔
	 */
	public void addToolTip(String text,Paint paint)
	{
		addInfo(text,paint);
	}
	
	/**
	 * 增加提示信息
	 * @param dotStyle	图案风格
	 * @param text		文本
	 * @param paint		绘制画笔
	 */
	public void addToolTip(PlotDot dotStyle,String text,Paint paint)
	{
		addInfo(dotStyle,text,paint);
	}

}
