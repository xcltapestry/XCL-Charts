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
import java.util.List;

import android.graphics.Canvas;
import org.xclcharts.chart.CircleChart;
import org.xclcharts.chart.PieData;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Color;

/**
 * @ClassName CircleChart01View
 * @Description  图形图例子(半圆)
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */

public class CircleChart01View extends GraphicalView {

	private List<PieData> mlPieData = new LinkedList<PieData>();	
	
	private String mDataInfo = "";
    CircleChart chart = new CircleChart();

    public CircleChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		setPercentage(0);
		chartRender();
	}

    @Override
    public void render(Canvas canvas) {
        try {
            chart.render(canvas);
        } catch (Exception e){
        }
    }

    public void chartRender()
	{
		try {			

			//图所占范围大小			
			if(getScreenWidth() < this.getScreenHeight())
			{
				chart.setChartRange(0.0f, 0.0f,getScreenWidth(),getScreenWidth());
			}else{
				chart.setChartRange(0.0f, 0.0f,getScreenHeight(),getScreenHeight());
			}

			//设置附加信息
			chart.setAttributeInfo(mDataInfo); 	
			//图的内间距
			chart.setPadding(20, 20, 15, 15);		
			
			//半圆方式显示
			chart.setCircleDisplayType(XEnum.CircleDisplayType.HALF);	
			
			//设置图表数据源			
			chart.setDataSource(mlPieData);				
			
			//绘制

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//百分比
	public void setPercentage(int per)
	{						
		if(per < 50)
		{
			mDataInfo = "轻松搞定";
		}else if(per < 70){
			mDataInfo = "充满活力";
		}else{
			mDataInfo = "不堪重负";
		}
		//PieData(标签，百分比，在饼图中对应的颜色)
		mlPieData.clear();		
		mlPieData.add(new PieData(Integer.toString(per)+"%",per,(int)Color.rgb(72, 201, 176)));		
	}


}
