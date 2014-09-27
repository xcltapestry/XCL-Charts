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
import android.graphics.PointF;
import android.graphics.Paint.Align;

public class ToolTip extends DyInfo{
	
	public ToolTip()
	{
		super();
	}
	
	
	public void showRectHint()
	{
		setStyle(XEnum.DyInfoStyle.RECT);
	}
	
	public void showRoundRectHint()
	{
		setStyle(XEnum.DyInfoStyle.ROUNDRECT);
	}
	
	public void showRoundRectHint(float x,float y)
	{
		setStyle(XEnum.DyInfoStyle.ROUNDRECT);
		setRoundRectX(x);
		setRoundRectY(y);
	}
	
	public void setAlign(Align align)
	{
		mPositionAlign = align;
	}
	
	public void setCurrentXY(float x,float y)
	{
		setCenterXY(x,y);
	}
	
	public void addToolTip(String text,Paint paint)
	{
		addInfo(text,paint);
	}
	
	public void addToolTip(PlotDot dotStyle,String text,Paint paint)
	{
		addInfo(dotStyle,text,paint);
	}

}
