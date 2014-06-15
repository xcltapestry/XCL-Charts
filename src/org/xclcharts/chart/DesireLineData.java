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
	private int mLineWidth = 0;
	
	public DesireLineData(
				  Double desireValue,
				  int color) 
	{		
		setDesireValue(desireValue);
		setColor(color);
	}
			
	public DesireLineData(String label,
						  Double desireValue,
						  int color,
						  int lineWidth) 
	{
		setLabel(label);
		setDesireValue(desireValue);
		setColor(color);
		setLineWidth(lineWidth);
	}

	public String getLabel() {
		return mLabel;
	}

	public void setLabel(String label) {
		this.mLabel = label;
	}

	public Double getDesireValue() {
		return mDesireValue;
	}

	public void setDesireValue(Double desireValue) {
		this.mDesireValue = desireValue;
	}

	public int getColor() {
		return mColor;
	}

	public void setColor(int color) {
		this.mColor = color;
	}

	public int getLineWidth() {
		return mLineWidth;
	}

	public void setLineWidth(int lineWidth) {
		this.mLineWidth = lineWidth;
	}
	
	public boolean isSetLineWidth()
	{
		return ((0 == mLineWidth)?false:true);
	}

}
