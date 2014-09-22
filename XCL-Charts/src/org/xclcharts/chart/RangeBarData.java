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
 * @version 1.5
 */
package org.xclcharts.chart;

/**
 * @ClassName RangeBarData
 * @Description  范围柱形图的数据类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */
public class RangeBarData {
								
		private double mMax = 0d;
		private double mMin = 0d;
		private double mX = 0d;		
		
		public RangeBarData(){};
			
		/**
		 * 柱形数据
		 * @param min	最小值
		 * @param max	最大值
		 * @param color	柱形颜色
		 */
		/*public RangeBarData(double min, double max) {
			// TODO Auto-generated constructor stub
		
			setMax(max);			
			setMin(min);			
		}	
		*/
		public RangeBarData(double x,double min, double max) {
			// TODO Auto-generated constructor stub
			setX(x);
			setMax(max);			
			setMin(min);			
		}
		
		
		
		
		
		/**
		 * 最大值				
		 * @param max 最大值
		 */
		public void setMax(double max)
		{
			mMax = max;
		}
		
		/**
		 * 最小值
		 * @param min	最小值
		 */
		public void setMin(double min)
		{
			mMin = min;
		}
		
		/**
		 * 返回最大值
		 * @return	最大值
		 */
		public double getMax()
		{
			return mMax;
		}
		
		/**
		 * 返回最小值
		 * @return	最小值
		 */
		public double getMin()
		{
			return mMin;
		}		
		
		/**
		 * 返回X值
		 * @return	X值
		 */
		public void setX(double x)
		{
			mX = x;
		}
		
		/**
		 * 返回X值
		 * @return	X值
		 */
		public double getX()
		{
			return mX;
		}	
				
}
