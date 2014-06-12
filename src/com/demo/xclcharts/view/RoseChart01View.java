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

import org.xclcharts.chart.PieData;
import org.xclcharts.chart.RoseChart;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Color;

/**
 * @ClassName RoseChart01View
 * @Description  南丁格尔玫瑰图 的例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class RoseChart01View extends GraphicalView {

	LinkedList<PieData> lPieData = new LinkedList<PieData>();	
	
	public RoseChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		chartDataSet();
		chartRender();
	}
	
	private void chartRender()
	{
		try {						
			RoseChart chart = new RoseChart();
			//柱形图所占范围大小
			chart.setChartRange(0.0f, 0.0f,getScreenWidth(),getScreenHeight());
			chart.setCanvas(this.mCacheCanvas);		
			chart.setPadding(20, 20, 15, 15);
			chart.setBackgroupColor(true,Color.BLACK);
			
			//数据源
			chart.setDataSource(lPieData);							
				
			//设置标题
			chart.setChartTitle("南丁格尔玫瑰图");
			chart.setChartSubTitle("(XCL-Charts)");
			chart.getPlotTitle().getChartTitlePaint().setColor(Color.WHITE);
			chart.getPlotTitle().getChartSubTitlePaint().setColor(Color.WHITE);
			
			//设置标签显示位置,当前设置标签显示在扇区中间
			chart.setLablesDisplay(XEnum.DisplayPostion.OUTSIDE);
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
		lPieData.add(new PieData("PostgreSQL",40,(int)Color.rgb(77, 83, 97) ));
		lPieData.add(new PieData("Sybase"	 ,50,(int)Color.rgb(148, 159, 181)));
		lPieData.add(new PieData("DB2"		 ,60,(int)Color.rgb(253, 180, 90)));
		lPieData.add(new PieData("国产及其它"	 ,35,(int)Color.rgb(52, 194, 188)));
		lPieData.add(new PieData("SQL Server",70,(int)Color.rgb(39, 51, 72)));
		lPieData.add(new PieData("DB2"		 ,80,(int)Color.rgb(255, 135, 195)));
		lPieData.add(new PieData("Oracle"    ,90,(int)Color.rgb(215, 124, 124)));	
	}
	

}
