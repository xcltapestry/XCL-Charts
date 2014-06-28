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

import java.util.LinkedList;
import java.util.List;

import android.graphics.Color;

/**
 * @ClassName BarData
 * @Description 数据类, 所有柱形图都用这个传数据
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */
public class BarData {


		//线上每个点的值
		private List<Double> mDataSet;
		
		private List<Integer> mDataColor;

		//值
		private String mKey;
		
		//线上的颜色
		private Integer mColor;
		
		public BarData() {
			// TODO Auto-generated constructor stub
		}
		
		/**
		 *  构成一条完整的数据集合
		 * @param key	键值		
		 * @param color  颜色
		 * @param dataSeries   对应的数据集
		 */
		public BarData(String key,List<Double> dataSeries,Integer color) 
		{
			setKey(key);		
			setColor(color);
			setDataSet(dataSeries);
		}
		
		
		/**
		 * 构成一条完整的数据集合
		 * @param key	键值	
		 * @param dataSeries 对应的数据集
		 */
		public BarData(String key,Double dataSeries) 
		{
			setKey(key);
			
			List<Double> valueList= new LinkedList<Double>();	
			valueList.add(dataSeries);
			setDataSet(valueList);
			
			setColor(Color.BLACK);
		}
		
		
		/**
		 * 可用于处理单独针对某些柱子指定颜色的情况，常见于标签单柱的情况
		 * @param key			键值	
		 * @param dataSeries	对应的数据集
		 * @param dataColor		每个数据柱形所对应的显示颜色
		 * @param color			柱形颜色
		 */
		public BarData(String key,List<Double> dataSeries,
								  List<Integer> dataColor,Integer color) 
		{
			setKey(key);		
			setColor(color);
			setDataSet(dataSeries);
			setDataColor(dataColor);
		}
		
		/**
		 * 设置每个数据柱形所对应的显示颜色
		 * @param dataColor 柱形颜色集
		 */
		public void setDataColor(List<Integer> dataColor) 
		{
			mDataColor = dataColor;
		}
		
		/**
		 * 每个数据柱形所对应的显示颜色
		 * @return 柱形颜色集
		 */
		public List<Integer> getDataColor()
		{
			return mDataColor;
		}
		
		
		/**
		 * 设置数据源
		 * @param dataSeries 数据集合序列
		 */
		public void setDataSet(List<Double> dataSeries) 
		{
			mDataSet = dataSeries;
		}
		
		/**
		 * 设置Key值
		 * @param value Key值
		 */
		public void setKey(String value) 
		{
			mKey = value;
		}
			
		/**
		 * 设置颜色
		 * @param value 颜色
		 */
		public void setColor(Integer value) 
		{
			mColor = value;
		}

		/**
		 * 返回数据集合序列
		 * @return 集合序列
		 */
		public List<Double> getDataSet() {
			return mDataSet;
		}


		/**
		 * 返回Key值
		 * @return Key值
		 */
		public String getKey() {
			return mKey;
		}

		/**
		 * 返回颜色
		 * @return 颜色
		 */
		public Integer getColor() {
			return mColor;
		}
}
