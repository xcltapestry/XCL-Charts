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

/**
 * @ClassName DataAxis
 * @Description 数据轴(Data Axis)基类，主要用于设置步长及最大，最小值
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */

public class DataAxis extends XYAxis {

	//轴数据来源
	 private double mDataAxisMin = 0d;
	 private double mDataAxisMax = 0d;
	 private double mDataAxisSteps = 0d;	
	 private double mDetailModeSteps = 0d;
	 

	public DataAxis()
	{
		super();		
	}
	 
	/**
	 * 设置数据轴最小值,默认为0
	 * @param min 最小值
	 */
	 public void setAxisMin(double min)
	 {
		 mDataAxisMin = min;
	 }
	 /**
	  * 设置数据轴最大值
	  * @param max 最大值
	  */
	 public void setAxisMax(double max)
	 {
		 mDataAxisMax = max;
	 }
	 /**
	  * 设置数据轴步长
	  * @param steps 步长
	  */
	 public void setAxisSteps(double steps)
	 {
		 mDataAxisSteps = steps;
	 }
	 
	 
	 /**
	  * 设置后，会启用为明细模式，轴刻度线会分长短,背景线会分粗细
	  * @param steps 步长
	  */
	 public void setDetailModeSteps(double steps)
	 {
		 mDetailModeSteps = steps;
	 }
	 
		 
	 /**
	  * 返回数据轴最小值
	  * @return 最小值
	  */
	 public double getAxisMin() {
		return mDataAxisMin;
	 }

	/**
	 * 返回数据轴最大值
	 * @return 最大值
	 */
	 public double getAxisMax() {
		return mDataAxisMax;
	 }

	/**
	 * 返回数据轴步长
	 * @return 步长
	 */
	public double getAxisSteps() {
		return mDataAxisSteps;
	}	
	
	/**
	 * 返回区分刻度线明细的步长
	 * @return 步长
	 */
	public double getDetailModeSteps() {
		return mDetailModeSteps;
	}	
	
	/**
	 * 返回是否启用明细模式
	 * @return 是否有启用
	 */
	public boolean isDetailMode()
	{		
		return((0d == mDetailModeSteps)?false:true); 
	}
	
	
}
