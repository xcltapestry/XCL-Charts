package com.demo.xclcharts.view;

import java.util.LinkedList;

import org.xclcharts.chart.PieChart;
import org.xclcharts.chart.PieData;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.event.click.ArcPosition;
import org.xclcharts.event.click.ChartArcListener;
import org.xclcharts.renderer.XEnum;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

public class ClickPieChart01View extends GraphicalView {


	 private String TAG = "ClickPieChart01View";
	 private PieChart chart = new PieChart();	
	 private LinkedList<PieData> chartData = new LinkedList<PieData>();
	
	 private ChartArcListener onClickListener = null;;
	 
	 public ClickPieChart01View(Context context) {
		super(context);
		initView();
	 }	
	
	 public ClickPieChart01View(Context context, AttributeSet attrs){   
       super(context, attrs);   
       initView();
	 }
	 
	 public ClickPieChart01View(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	 }
	 
	 private void initView()
	 {
		 chartDataSet();	
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
			//标签显示(隐藏，显示在中间，显示在扇区外面,折线注释方式)
			chart.setLabelPosition(XEnum.SliceLabelPosition.LINE);			
			
			//图的内边距
			//注释折线较长，缩进要多些
			int [] ltrb = new int[4];
			ltrb[0] = DensityUtil.dip2px(getContext(), 60); //left	
			ltrb[1] = DensityUtil.dip2px(getContext(), 55); //top	
			ltrb[2] = DensityUtil.dip2px(getContext(), 60); //right
			ltrb[3] = DensityUtil.dip2px(getContext(), 50); //bottom				
							
			chart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);
			
			//设定数据源
			chart.setDataSource(chartData);												
		
			//标题
			chart.setTitle("擂茶配方比");
			chart.addSubtitle("(XCL-Charts Demo)");
			chart.setTitleVerticalAlign(XEnum.VerticalAlign.MIDDLE);
				
		
			//显示边框
			//chart.showRoundBorder();
			
			//激活点击监听
			chart.ActiveListenItemClick();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());
		}
	}

	private void chartDataSet()
	{
		//设置图表数据源		
		chartData.add(new PieData("芝麻","芝麻-15%",15,(int)Color.rgb(77, 83, 97)));
		chartData.add(new PieData("花生","花生-35%",35,(int)Color.rgb(148, 159, 181)));
		chartData.add(new PieData("茶叶","茶叶(25%)",25,(int)Color.rgb(253, 180, 90)));
		//将此比例块突出显示
		chartData.add(new PieData("其它","其它(炒米，炒花生之类)",25,(int)Color.rgb(180, 205, 230),true));
	}
	
	@Override
	public void render(Canvas canvas) {
		// TODO Auto-generated method stub
		 try{
	            chart.render(canvas);
	        } catch (Exception e){
	        	Log.e(TAG, e.toString());
	        }
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub		
		//super.onTouchEvent(event);		
		if(event.getAction() == MotionEvent.ACTION_UP) 
		{									
			triggerClick(event.getX(),event.getY());	
		}
		return true;
	}
	

	//触发监听
	private void triggerClick(float x,float y)
	{			
		
		ArcPosition record = chart.getPositionRecord(x,y);			
		if( null == record) return;
		
		PieData pData = chartData.get(record.getDataID());											
		Toast.makeText(this.getContext(),								
				"View Info key:" +  pData.getKey() +
				" Label:" + pData.getLabel() ,
				Toast.LENGTH_SHORT).show();
		
		if(null != onClickListener)
				onClickListener.onClick(new PointF(x,y), record);	
	}
	
	
	public void setOnPlotClickListener(ChartArcListener chartListener) 
	{
	  this.onClickListener = chartListener;
	 }
	


}