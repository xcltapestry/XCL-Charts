package com.demo.xclcharts.view;

import java.util.LinkedList;

import org.xclcharts.chart.DountChart;
import org.xclcharts.chart.Pie3DChart;
import org.xclcharts.chart.PieChart;
import org.xclcharts.chart.PieData;
import org.xclcharts.chart.RoseChart;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Color;

public class SpinnerPieChart01View extends GraphicalView {
	
	
	private PieChart mChart = null;
	private int mChartStyle = 0;
	private int mMoveHeight = 0;
	
	LinkedList<PieData> lPieData = new LinkedList<PieData>();

	public SpinnerPieChart01View(Context context,int chartStyle,int moveHeight) {
		super(context);
		// TODO Auto-generated constructor stub
		mChartStyle = chartStyle;
		mMoveHeight = moveHeight;
		chartDataSet();
		chartRender();
	}
	
	
     private void initChart(int chartStyle)
 	{
 		switch(chartStyle)
 		{
 		case 0: //饼图
 			mChart = new PieChart();
 			mChart.setLablesDisplay(XEnum.DisplayPostion.OUTSIDE);
 			
 			break;
 		case 1:	//3D饼图
 			mChart = new Pie3DChart();
 			mChart.setLablesDisplay(XEnum.DisplayPostion.CENTER);
 			mChart.getLabelsPaint().setColor(Color.WHITE); 
 			
 			break;
 		case 2:	//环形图
 			mChart = new DountChart();
 			break;
 		case 3:	//南丁格尔玫瑰图
 			mChart = new RoseChart();
 			mChart.setBackgroupColor(true, (int)Color.rgb(115, 153, 0));
 			((RoseChart) mChart).getInnerPaint().setColor((int)Color.rgb(153, 204, 0));
 			mChart.getLabelsPaint().setColor(Color.WHITE);
 			mChart.setLablesDisplay(XEnum.DisplayPostion.CENTER);
 			break;		
 		}
 	}
     
     private void chartRender()
 	{
 		try {					
 			initChart(mChartStyle);
 			//图所占范围大小 		
 			mChart.setChartRange( 0.0f,mMoveHeight,
 									getScreenWidth(),
 									getScreenHeight() - mMoveHeight);
 			mChart.setCanvas(this.mCacheCanvas);
 			//图的内边距
 			mChart.setPadding(5, 35, 15, 20);
 			
 			//设定数据源
 			mChart.setDataSource(lPieData);			
 			
 			//设置起始偏移角度(即第一个扇区从哪个角度开始绘制)
 			mChart.setInitialAngle(90);	
 			//显示Key值
 			mChart.setKeyVisible(true);
 			//绘制
 			mChart.render();
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
	}
	private void chartDataSet()
	{
		//设置图表数据源		
		lPieData.add(new PieData("User1","15%",15,(int)Color.rgb(203, 183, 60)));
		lPieData.add(new PieData("User2","25%",25,(int)Color.rgb(214, 222, 207),false));
		lPieData.add(new PieData("User3","10%",10,(int)Color.rgb(164, 202, 81)));
		//将此比例块突出显示
		lPieData.add(new PieData("User4","18%",18,(int)Color.rgb(1, 172, 241),true));
		lPieData.add(new PieData("User5","22%",22,(int)Color.rgb(99, 179, 150),true));
		lPieData.add(new PieData("User6","10%",10,(int)Color.rgb(52, 97, 138)));
	}
	
	
}
