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

import java.util.List;

import org.xclcharts.renderer.XEnum;

/**
 * @ClassName LineData
 * @Description 数据类, 折线图,面积图都用这个传数据
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */

public class LineData extends LnData{
		
			//线上每个点的值
			private List<Double> mLinePoint;

			
			public LineData() {
				// TODO Auto-generated constructor stub
			}
			
			/**
			 *  构成一条完整的线条
			 * @param key	键值		
			 * @param color  线条颜色
			 * @param dataSeries   对应的数据集
			 */
			public LineData(String key,List<Double> dataSeries,int color) 
			{
				setLineLabel(key);	
				setLinePoint(dataSeries);
				setLineColor(color);				
			}
			
			/**
			 * 
			 * @param key	key值
			 * @param dataSeries 数据序列
			 * @param color		线颜色
			 * @param dotStyle	坐标点绘制类型
			 */
			public LineData(String key,					
							List<Double> dataSeries,
							int color,
							XEnum.DotStyle  dotStyle) 
			{
				setLineLabel(key);		
				setLineColor(color);
				setLinePoint(dataSeries);
				setDotStyle(dotStyle);
			}
							
			/**
			 * 设置绘制线的数据序列
			 * @param dataSeries 数据序列
			 */
			public void setLinePoint(List<Double> dataSeries) 
			{
				mLinePoint = dataSeries;
			}

			/**
			 * 返回绘制线的数据序列
			 * @return 绘制线的数据序列
			 */
			public List<Double> getLinePoint() {
				return mLinePoint;
			}
			
			
}
