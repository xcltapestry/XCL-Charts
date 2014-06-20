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
package org.xclcharts.chart;

import android.graphics.Color;

/**
 * @ClassName DesireLineData
 * @Description 期望线类，期望线用于强调目标或底线
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * 
 */

public class DesireLineData {
	
	private String mLabel = "";
	private Double mDesireValue = 0d;
	private int mColor = Color.BLACK;
	private int mLineStroke = 0;
	
	/**
	 * 期望线
	 * @param desireValue	期望值
	 * @param color			线颜色
	 */
	public DesireLineData(
				  Double desireValue,
				  int color) 
	{		
		setDesireValue(desireValue);
		setColor(color);
	}
			
	/**
	 * 期望线
	 * @param label			标签
	 * @param desireValue	期望值
	 * @param color			线颜色
	 * @param stroke	线精细
	 */
	public DesireLineData(String label,
						  Double desireValue,
						  int color,
						  int stroke) 
	{
		setLabel(label);
		setDesireValue(desireValue);
		setColor(color);
		setLineStroke(stroke);
	}

	/**
	 * 返回标签
	 * @return 标签
	 */
	public String getLabel() {
		return mLabel;
	}

	/**
	 * 设置标签
	 * @param label 标签
	 */
	public void setLabel(String label) {
		this.mLabel = label;
	}

	/**
	 * 取得当前期望值
	 * @return 期望值
	 */
	public Double getDesireValue() {
		return mDesireValue;
	}

	/**
	 * 设置期望值
	 * @param desireValue 期望值
	 */
	public void setDesireValue(Double desireValue) {
		this.mDesireValue = desireValue;
	}

	/**
	 * 返回颜色
	 * @return 颜色
	 */
	public int getColor() {
		return mColor;
	}

	/**
	 * 设置颜色
	 * @param color 颜色
	 */
	public void setColor(int color) {
		this.mColor = color;
	}

	/**
	 * 得到当前线粗细
	 * @return 线粗细
	 */
	public int getLineStroke() {
		return mLineStroke;
	}

	/**
	 * 设置线的粗细
	 * @param stroke 粗细
	 */
	public void setLineStroke(int stroke) {
		this.mLineStroke = stroke;
	}
	
	/**
	 *  返回是否有手工指定线的精细
	 * @return 是否有指定
	 */
	public boolean isSetLineStroke()
	{
		return ((0 == mLineStroke)?false:true);
	}

}
