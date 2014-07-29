package com.demo.xclcharts.view;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import org.xclcharts.chart.BarChart;
import org.xclcharts.chart.BarData;
import org.xclcharts.chart.CustomLineData;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class BarChart07View_left  extends GraphicalView {
	
		private String TAG = "BarChart07View_left";
		
		private BarChart chart = new BarChart();
		
		//轴数据源
		private List<String> chartLabels = new LinkedList<String>();
		private List<BarData> chartData = new LinkedList<BarData>();
		private List<CustomLineData> mCustomLineDataset = new LinkedList<CustomLineData>();
		
		
		public BarChart07View_left(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			 initView();
		}
		
		 public BarChart07View_left(Context context, AttributeSet attrs){   
		        super(context, attrs);   
		        initView();
		 }
	 			 
		 public BarChart07View_left(Context context, AttributeSet attrs, int defStyle) {
				super(context, attrs, defStyle);
				initView();
		 }
		 
		 private void initView()
		 {
				chartLabels();
				chartDataSet();
				chartDesireLines();
				chartRender();
		 }
		 
		@Override  
	    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
	        super.onSizeChanged(w, h, oldw, oldh);  
	    
	       //图所占范围大小
	        chart.setChartRange(w,h);
	    }  
		 
		 
		private void chartRender()
		{
			try {
				//标题
				chart.setTitle("柱形图左右移动演示");
				chart.addSubtitle("(XCL-Charts Demo)");	
				chart.setTitleAlign(XEnum.ChartTitleAlign.LEFT);
				//数据源
				chart.setDataSource(chartData);
				chart.setCategories(chartLabels);	
				chart.setCustomLines(mCustomLineDataset);
				
				//图例
				chart.getAxisTitle().setLeftAxisTitle("参考成年男性标准值");
				
				
				//数据轴
				chart.getDataAxis().setAxisMax(40);
				chart.getDataAxis().setAxisMin(0);
				chart.getDataAxis().setAxisSteps(5);		
				//指隔多少个轴刻度(即细刻度)后为主刻度
				chart.getDataAxis().setDetailModeSteps(2);
								
				//定义数据轴标签显示格式
				chart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){
		
					@Override
					public String textFormatter(String value) {
						// TODO Auto-generated method stub		
						Double tmp = Double.parseDouble(value);
						DecimalFormat df=new DecimalFormat("#0");
						String label = df.format(tmp).toString();				
						return (label);
					}
					
				});
			
				//隐藏Key
				chart.getPlotLegend().hideLegend();
				chart.getCategoryAxis().setVisible(false);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		private void chartDataSet()
		{
			//标签对应的柱形数据集
			List<Double> dataSeriesA= new LinkedList<Double>();	
			//依数据值确定对应的柱形颜色.
			List<Integer> dataColorA= new LinkedList<Integer>();	
			
			dataSeriesA.add(0d);
			dataColorA.add((int)Color.RED);
			
			//此地的颜色为Key值颜色及柱形的默认颜色
			BarData BarDataA = new BarData("",dataSeriesA,dataColorA,
											(int)Color.rgb(53, 169, 239));
			
			chartData.add(BarDataA);
		}
		
		private void chartLabels()
		{		
			for(Integer i=1;i<5;i++)
			{
				chartLabels.add(Integer.toString(i));
			}
		}	
		
		/**
		 * 期望线/分界线
		 */
		private void chartDesireLines()
		{										
			CustomLineData cl = new CustomLineData("适中",18.5d,(int)Color.rgb(77, 184, 73),3);
			cl.setLabelHorizontalPostion(XEnum.LabelAlign.LEFT);
			cl.hideLine();
			mCustomLineDataset.add(cl);											
		}
		
	
	@Override
    public void render(Canvas canvas) {
        try{
         
        	 //设置图表大小
	        chart.setChartRange(0,0, //10f, 10f,  
	        		this.getLayoutParams().width - 10,
	        		 this.getLayoutParams().height - 10);
	        //top,bottom,左右两图要一致，留70px空间用于显示左轴
	        chart.setPadding(70,120,0, 180);	//70是轴所点总宽度，在右边轴绘图时，偏移这个宽度就对好了
        	
            chart.render(canvas);
            
        } catch (Exception e){
        	Log.e(TAG, e.toString());
        }
    }
	
	@Override
	 public void onDraw(Canvas canvas){   	
	        //绘制
	        super.onDraw(canvas); 
	 }
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return false;
	}
		
}
