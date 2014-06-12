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
package com.demo.xclcharts.view;

import java.util.LinkedList;

import org.xclcharts.chart.Pie3DChart;
import org.xclcharts.chart.PieData;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Color;

/**
 * @ClassName Pie3DChart01View
 * @Description  3D饼图的例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class Pie3DChart01View extends GraphicalView {

	LinkedList<PieData> lPieData = new LinkedList<PieData>();	
	
	public Pie3DChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
					
		chartDataSet();
		chartRender();		
	}
	
	private void chartRender()
	{
		try {
			
			Pie3DChart chart = new Pie3DChart();
			//图所占范围大小
			chart.setChartRange(0.0f, 0.0f,getScreenWidth(),getScreenHeight());
			chart.setCanvas(this.mCacheCanvas);
			//图的内边距
			chart.setPadding(10, 20, 15, 15);
			
			//设定数据源
			chart.setDataSource(lPieData);		
			
			//标题
			chart.setChartTitle("3D Pie Chart");
			chart.getPlotTitle().setChartTitlePosition(XEnum.Postion.LOWER);
			
			chart.setKeyVisible(false);
			chart.getLabelsPaint().setColor(Color.WHITE);
			
			//绘制
			chart.render();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void chartDataSet()
	{
		//设置图表数据源
			
		//PieData(标签，百分比，在饼图中对应的颜色)
		
		lPieData.add(new PieData("PHP(15%)",15,
								(int)Color.rgb(1, 170, 255)));
		lPieData.add(new PieData("Other",10,
								(int)Color.rgb(148, 42, 133),false));	
		lPieData.add(new PieData("Oracle",40,(int)Color.rgb(241, 62, 1)));
		lPieData.add(new PieData("Java",15,(int)Color.rgb(242, 167, 69)));	
		
		//将此比例块突出显示
		lPieData.add(new PieData("C++(20%)",20,
								(int)Color.rgb(164, 233, 0),true));
	}
	

}
