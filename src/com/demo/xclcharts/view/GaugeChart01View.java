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

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import org.xclcharts.chart.GaugeChart;

import android.content.Context;
import android.graphics.Color;
import android.util.Pair;

/**
 * @ClassName GaugeChart01View
 * @Description  仪表盘例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class GaugeChart01View  extends GraphicalView {

	private List<String> mLabels = new ArrayList<String>();
	private List<Pair> mPartitionSet = new ArrayList<Pair>();		
	private float mAgent = 0f;
	private GaugeChart chart = new GaugeChart();
	
	public GaugeChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
						
		chartLabels();
		chartDataSet();
		chartRender();
		
		//new Thread(this).start();
	}
    @Override
    public void render(Canvas canvas) {
        try {
            chart.render(canvas);
        } catch (Exception e){
        }
    }

	
	public void setAgent(float currentAgent)
	{
		mAgent = currentAgent;
	}
	
		
	public void chartRender()
	{
		try {								
			//图所占范围大小
			chart.setChartRange(0.0f, 0.0f,getScreenWidth() - 100,getScreenHeight());
			chart.setPadding(25, 20, 10, 10);
				
			//设置标题
			chart.setChartTitle("仪表盘 ");
								
			//刻度步长
			chart.setTickSteps(10d);
			
			//标签(标签和步长分开，步长即刻度可以密点，标签可以松点)					
			chart.setLabels(mLabels);					
			//分区
			chart.setPartition(mPartitionSet);
			
			//设置当前指向角度(0-180).
			//chart.setCurrentAgent(90f);
			chart.setCurrentAgent(mAgent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//分区[角度(0-mStartAgent)，颜色]		
	private void chartDataSet()
	{
		int agent = 180/3;
		mPartitionSet.add(new Pair<Integer,Integer>(agent, (int)Color.rgb(73, 172, 72)));
		mPartitionSet.add(new Pair<Integer,Integer>(agent, (int)Color.rgb(247, 156, 27)));
		mPartitionSet.add(new Pair<Integer,Integer>(agent, (int)Color.rgb(224, 62, 54)));
	}
	
	private void chartLabels()
	{
		//标签		
		mLabels.add("起始");
		mLabels.add("安全");
		mLabels.add("警惕");
		mLabels.add("危险");
		mLabels.add("终止");
	}

	/*
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
		
		while(!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(100);
                agent +=5f;
                if(agent > 180) agent = 10;
                //chartRender();
               // chart.setCurrentAgent(agent);
                //chart.render();
            }
            catch(Exception e) {
                Thread.currentThread().interrupt();
            }
            //postInvalidate();
            invalidate();
        }
	}
	*/
}
