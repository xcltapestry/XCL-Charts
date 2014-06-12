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
 * @Copyright Copyright (c) 2014 XCL-Charts (www.xclcharts.com)
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version v0.1
 */
package com.demo.xclcharts.view;

import java.util.LinkedList;

import org.xclcharts.chart.CircleChart;
import org.xclcharts.chart.PieData;

import android.content.Context;
import android.graphics.Color;
/**
 * @ClassName CircleChart02View
 * @Description  图形图例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class CircleChart02View extends GraphicalView {
	
	//设置图表数据源
	private LinkedList<PieData> mlPieData = new LinkedList<PieData>();	
	private String mDataInfo = "";

	public CircleChart02View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
						
		setPercentage(0);
		chartRender();
	}
	
	public void chartRender()
	{
		try {
			CircleChart chart = new CircleChart();
			chart.setCanvas(this.mCacheCanvas);
							
			//图所占范围大小
			if(getScreenWidth() < this.getScreenHeight())
			{
				chart.setChartRange(0.0f, 0.0f,getScreenWidth(),getScreenWidth());
			}else{
				chart.setChartRange(0.0f, 0.0f,getScreenHeight(),getScreenHeight());
			}
			
			chart.setPadding(10, 10, 10, 15);				
							
			//设置信息			
			chart.setAttributeInfo(mDataInfo); 	
			//数据源
			chart.setDataSource(mlPieData);
			
			//背景色
			chart.getPaintBgCircle().setColor((int)Color.rgb(117, 197, 141));
			//深色
			chart.getPaintFillCircle().setColor((int)Color.rgb(77, 180, 123));
			//信息颜色
			chart.getPaintDataInfo().setColor((int)Color.rgb(243, 75, 125));
			
			chart.render();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//百分比
		public void setPercentage(int per)
		{					
			//PieData(标签，百分比，在饼图中对应的颜色)
			mlPieData.clear();	
			int color = (int)Color.rgb(72, 201, 176);
			if(per < 40)
			{
				mDataInfo = "容易容易";
			}else if(per < 60){
				mDataInfo = "严肃认真";
				color = (int)Color.rgb(246, 202, 13);
			}else{
				mDataInfo = "压力山大";
				color = (int)Color.rgb(243, 75, 125);
			}
			mlPieData.add(new PieData(Integer.toString(per)+"%",per,color));		
				
		}

}
