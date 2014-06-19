package com.demo.xclcharts.view;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import org.xclcharts.chart.Bar3DChart;
import org.xclcharts.chart.BarChart;
import org.xclcharts.chart.BarData;
import org.xclcharts.chart.StackBarChart;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

public class SpinnerBarChart01View extends GraphicalView {
	
	private String TAG = "SpinnerBarChart01View";
	
	private int mChartStyle = 0;
	private int mOffsetHeight = 0;
	private BarChart mChart = null;
	//标签轴
	private List<String> chartLabels = new LinkedList<String>();
	private List<BarData> chartData = new LinkedList<BarData>();
	
	public SpinnerBarChart01View(Context context,int chartStyle,int offsetHeight) {
		super(context);
		// TODO Auto-generated constructor stub
		
		mChartStyle = chartStyle;
		mOffsetHeight = offsetHeight;		
		chartLabels();
		chartDataSet();
		chartRender();
	}
		
	private void initChart(int chartStyle)
	{
		switch(chartStyle)
		{
		case 0: //竖向柱形图
			mChart = new BarChart();
			//图例
			mChart.getLegend().setLeftLegend("百分比");			
			break;
		case 1:	//横向柱形图
			mChart = new BarChart();
			mChart.setChartDirection(XEnum.Direction.HORIZONTAL);
			break;
		case 2:	//竖向3D柱形图
			mChart = new Bar3DChart();
			break;
		case 3:	//横向3D柱形图
			mChart = new Bar3DChart();
			mChart.setChartDirection(XEnum.Direction.HORIZONTAL);
			break;
		case 4:	//竖向堆叠柱形图 
			mChart = new StackBarChart();
			((StackBarChart) mChart).setTotalLabelVisible(false);
			break;
		case 5:	//横向堆叠柱形图
			mChart = new StackBarChart();
			mChart.setChartDirection(XEnum.Direction.HORIZONTAL);
			((StackBarChart) mChart).setTotalLabelVisible(false);
			break;			
		}
		
		
	}
	
	public void chartRender()
	{
		try {
			
			initChart(mChartStyle);
			
			//图所占范围大小
			mChart.setChartRange(0.0f, mOffsetHeight, getScreenWidth(),getScreenHeight() - mOffsetHeight);
		
			if(mChart.isVerticalScreen())
			{
				mChart.setPadding(5, 40, 10, 15);
			}else{
				mChart.setPadding(10, 45, 15, 15);
			}
				
			//数据源
			mChart.setDataSource(chartData);
			mChart.setLabels(chartLabels);	
									
			//数据轴
			mChart.getDataAxis().setAxisMax(100);
			mChart.getDataAxis().setAxisMin(0);
			mChart.getDataAxis().setAxisSteps(20);
			
			//定义数据轴标签显示格式
			mChart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){
	
				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub		
					Double tmp = Double.parseDouble(value);
					DecimalFormat df = new DecimalFormat("#0");
					String label = df.format(tmp).toString();				
					return label+"%";
				}
				
			});		
			//定义柱形上标签显示格式
			mChart.getBar().setItemLabelsVisible(true);
			mChart.getBar().getItemLabelsPaint().setColor((int)Color.rgb(72, 61, 139)); 
			mChart.getBar().getItemLabelsPaint().setFakeBoldText(true);
			
			mChart.setItemLabelFormatter(new IFormatterDoubleCallBack() {
				@Override
				public String doubleFormatter(Double value) {
					// TODO Auto-generated method stub										
					DecimalFormat df=new DecimalFormat("#0");
					String label = df.format(value).toString();				
					return label+"%";
				}});	       
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());
		}
	}
	private void chartDataSet()
	{
		//标签对应的柱形数据集
		List<Double> dataSeriesA= new LinkedList<Double>();	
		dataSeriesA.add(50d); 
		dataSeriesA.add(25d);
		dataSeriesA.add(20d);
		BarData BarDataA = new BarData("Google",dataSeriesA,(int)Color.rgb(73, 135, 218));
				
		List<Double> dataSeriesB= new LinkedList<Double>();	
		dataSeriesB.add(35d); 		
		dataSeriesB.add(65d);
		dataSeriesB.add(75d);
		BarData BarDataB = new BarData("Baidu",dataSeriesB,(int)Color.rgb(224, 4, 0));
		
		List<Double> dataSeriesC= new LinkedList<Double>();	
		dataSeriesC.add(15d);
		dataSeriesC.add(10d);
		dataSeriesC.add(5d);
		BarData BarDataC = new BarData("Bing",dataSeriesC,(int)Color.rgb(255, 185, 0));
		
		chartData.add(BarDataA);
		chartData.add(BarDataB);
		chartData.add(BarDataC);
	}
	
	private void chartLabels()
	{
		chartLabels.add("路人甲"); 
		chartLabels.add("路人乙"); 
		chartLabels.add("路人丙"); 
	}	
		
	@Override
    public void render(Canvas canvas) {
        try{
        	mChart.render(canvas);
        } catch (Exception e){
        	Log.e(TAG, e.toString());
        }
    }
}
