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

package org.xclcharts.renderer.axis;


import java.util.List;

import org.xclcharts.renderer.IRender;
import org.xclcharts.renderer.XEnum;

import android.graphics.Canvas;
import android.graphics.Paint.Align;

/**
 * @ClassName LabelsAxisRender
 * @Description 标签轴(Labels Axis)绘制类，绑定数据源并负责具体的绘制
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */

public class LabelsAxisRender extends LabelsAxis implements IRender{
	
	
	public LabelsAxisRender()
	{
		super();
		getAxisTickLabelsPaint().setTextAlign(Align.CENTER);		
		setAxisVerticalTickPosition(XEnum.Position.LOWER);
	}
	
	/**
	 * 返回数据源
	 * @return 数据源
	 */
	public List<String> getDataSet()
	{
		return this.mDataSet;
	}
	
	/**
	 * 绘制横向刻度标识
	 * @param centerX	点X坐标
	 * @param centerY	点Y坐标
	 * @param text	内容
	 */
	public 	void renderAxisHorizontalTick(Canvas canvas, float centerX,float centerY,String text)
	{		
		if(getVisible())
			renderHorizontalTick(canvas,centerX,centerY,text);
	}
	
	/**
	 * 绘制竖向刻度标识
	 * @param centerX	点X坐标
	 * @param centerY	点Y坐标
	 * @param text	内容
	 */
	public void renderAxisVerticalTick(Canvas canvas,float centerX,float centerY,String text)
	{
		
		if(getVisible())
			renderVerticalTick(canvas,centerX,centerY,text);
	}
	
	/**
	 * 绘制轴给
	 * @param startX 起始点X坐标
	 * @param startY 起始点Y坐标
	 * @param stopX	 终止点X坐标	
	 * @param stopY	 终止点Y坐标
	 */
	public void renderAxis(Canvas canvas,float startX,float startY,float stopX,float stopY)
	{
		if(getVisible() && getAxisLineVisible())
			canvas.drawLine(startX, startY, stopX, stopY, this.getAxisPaint());
	}
	


	@Override
	public boolean render(Canvas canvas) {
		// TODO Auto-generated method stub
		
		if(false == getVisible())return true;
		return true;
	}
	
	public void setDataBuilding(List<String> dataSet)
	{
		 mDataSet = dataSet;
	}
	
	

}
