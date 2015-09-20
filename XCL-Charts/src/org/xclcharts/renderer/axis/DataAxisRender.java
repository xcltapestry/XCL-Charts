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

package org.xclcharts.renderer.axis;


/**
 * @ClassName DataAxis
 * @Description 数据轴(Data Axis)绘制类，负责具体的绘制
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum;

import android.graphics.Canvas;

public class DataAxisRender extends DataAxis { 

	//当前刻度线ID
	private int mCurrentId = 0;
	
		
	public DataAxisRender()
	{	
	}
		
	/**
	 * 返回轴值的范围(即最大-最小值).
	 * @return 轴值范围
	 */
	public float getAxisRange()
	{
		//return (int) Math.abs(getAxisMax() - getAxisMin());		
		return MathHelper.getInstance().sub(getAxisMax(), getAxisMin());
	}
	
	
	/**
	 * 数据轴值范围(最大与最小之间的范围)  /  传的的步长  = 显示的Tick总数
	 * @return 显示的刻度标记总数
	 */
	public int getAixTickCount()
	{
		int tickCount = (int) Math.ceil( getAxisRange() / getAxisSteps() ) ;		
		return tickCount;
	}
	
	
	/**
	 * 设置当前刻度线在轴上的序号ID,注意,此序号与轴的值与关，仅用来说明是轴上的第几个标识
	 * @param id  刻度线ID
	 */
	public void setAxisTickCurrentID(int id)
	{
		mCurrentId = id;
	}
	
	/**
	 * 依据当前id序号与steps的比较来区分当前是否为主tick
	 * @return 是否为主tick
	 */
	public boolean isPrimaryTick()
	{		
		return isPrimaryTick(mCurrentId);		
	}
	
	public boolean isPrimaryTick(int id)
	{
		if(isDetailMode())
		{		
			if(0 == id && mShowFirstTick) return true;
			
			if( id < getDetailModeSteps() 
					|| id%getDetailModeSteps() != 0 )
				return false;
		}		
		return true;		
	}
	
	
	/**
	 * 用于处理明细横式下，细分部份的轴刻度线长度缩短为正常的一半，用来突出主明细刻度
	 * @return 刻度线长度
	 */
	@Override
	public int getTickMarksLength()
	{		
		int len = super.getTickMarksLength();		
		return(isPrimaryTick()?len:len/2);
	}
	
	/**
	 * 用于处理明细模式下，细分部份的标签不显示出来
	 */
	@Override
	public boolean isShowAxisLabels() {		
		return (!isPrimaryTick()?false:super.isShowAxisLabels());	
	}
	

	//public boolean isShowAxisDetailModeLabels() {		
	//	return (!isPrimaryTick()?false:isShowAxisLabels());	
	//}


	/**
	 * 绘制横向刻度标记
	 * @param chatLeft  图最左边
	 * @param plotLeft	绘图区最左边
	 * @param canvas	画布
	 * @param centerX	中心点X坐标
	 * @param centerY	中心点Y坐标
	 * @param text		文本
	 * @param isTickVisible	是否显示
	 */
	public 	void renderAxisHorizontalTick(float chatLeft,float plotLeft,
			Canvas canvas, 
			float centerX,float centerY,
			String text,
			boolean isTickVisible)
	{								
		renderHorizontalTick(chatLeft,plotLeft,canvas, 
							 centerX,centerY,text,centerX,centerY,isTickVisible);
	}


	/**
	 * 绘制竖向刻度标记
	 * @param canvas	画布
	 * @param centerX	中心点X坐标
	 * @param centerY	中心点Y坐标
	 * @param text		文本
	 */
	public void renderAxisVerticalTick(Canvas canvas,float centerX,float centerY,
			String text,
			boolean isTickVisible,XEnum.ODD_EVEN oe)
	{		
		renderVerticalTick(canvas,centerX,centerY,text,centerX,centerY,isTickVisible,oe);		
	}
		

	/**
	 * 绘制轴
	 * @param canvas 画布
	 * @param startX 起始点X坐标
	 * @param startY 起始点Y坐标
	 * @param stopX	  结束点X坐标	
	 * @param stopY	  结束点Y坐标
	 */
	public void renderAxis(Canvas canvas, float startX,float startY,float stopX,float stopY)
	{
		if(isShow() && isShowAxisLine())
		{
			drawAxisLine(canvas,startX, startY, stopX, stopY); 
		}
	}	
	
	public void renderAxisLine(Canvas canvas, float startX,float startY,float stopX,float stopY)
	{		
		drawAxisLine(canvas,startX, startY, stopX, stopY); 
	}	
	

}
