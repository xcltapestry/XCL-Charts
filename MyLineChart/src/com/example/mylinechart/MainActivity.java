package com.example.mylinechart;

import java.util.ArrayList;

import com.data.Common;
import com.data.MyData;
import com.view.AxisView;
import com.view.StatisticsOneView;
import com.view.TitleView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;


/**
 * 折线图，结合HorizontalScrollView来展示图表
 * @author Seven
 */

public class MainActivity extends Activity {
	
//	private String[] xScaleArray;
//	private int[] yScaleArray;
//	private String[] levelName;
	
	private LinearLayout axisLayout = null;
	private HorizontalScrollView horizontal_layout = null;
	private LinearLayout threndLine_Layout = null;
	private LinearLayout title_layout = null;
	
	private TitleView titleView;
	private StatisticsOneView statisticsOneView;
	private AxisView axisY;
	private boolean mp=false;//
	

	private int count=0;
	private long firClick=0;
	private long secClick=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
		Common.screenWidth = mDisplayMetrics.widthPixels;
		Common.screenHeight = mDisplayMetrics.heightPixels;
		
		title_layout = (LinearLayout) findViewById(R.id.titleView);
		
		axisLayout = (LinearLayout) findViewById(R.id.axisY);
		RelativeLayout.LayoutParams aParams = (LayoutParams) axisLayout.getLayoutParams();
		aParams.height = Common.screenHeight *6/8;
		axisLayout.setLayoutParams(aParams);
		
		horizontal_layout= (HorizontalScrollView) findViewById(R.id.horizontal_layout);
		RelativeLayout.LayoutParams hParams = (LayoutParams) horizontal_layout.getLayoutParams();
		hParams.height = Common.screenHeight *6/8;
		hParams.setMargins(45, 0, 0, 0);
		horizontal_layout.setLayoutParams(hParams);
		
		threndLine_Layout = (LinearLayout) findViewById(R.id.thrend_line);
		//监听双击事件
		threndLine_Layout.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//滑动不会触发up事件，故在up触发时计数
				if(MotionEvent.ACTION_UP == event.getAction()){
					count++;  
					if(count == 1){  
						firClick = System.currentTimeMillis(); 
						new Thread(new Runnable(){   
						    public void run(){   
						        try {
									Thread.sleep(500);
									count=0;
									firClick=0;
									secClick=0;
								} catch (InterruptedException e) {
									e.printStackTrace();
								}   
						    }  
						}).start();

					} else if (count == 2){  
						secClick = System.currentTimeMillis();  
						if(secClick - firClick < 500){  
							if(mp==false)
								mp=true;
							else 
								mp=false;
							addView();
						}  
						count = 0;  
						firClick = 0;  
						secClick = 0; 
					}  
				}
				return true;
			}
		});
		
		//实例化View
		axisY = new AxisView(this);
		statisticsOneView = new StatisticsOneView(this);
		titleView = new TitleView(this);
		
		//自定义参数
		setTitle();
		setKey();
		setAxis();
		setData();
		
		addView();
	}
	
	private void setData() {
		
		int[] colors = getResources().getIntArray(R.array.aqi_choice_color);
		MyData data1 = new MyData();
		data1.setName("SO2");
		data1.setData( new int[]{55,202,178,158,256,299,  
								87,99,101,213,119,233,  
								95,45,76,68,149,56,  
								47,72,23,192,115,214} );
		data1.setColor(0xff8d77ea);
		
		MyData data2 = new MyData();
		data2.setName("CO");
		data2.setData( new int[]{40,210,190,140,240,250,  
								80,85,90,230,100,220,  
								70,30,70,80,130,40,  
								30,80,40,160,100,210} );
		data2.setColor(0xff43ce17);
		
		Common.DataSeries = new ArrayList<MyData>();
		Common.DataSeries.add(data1);
		Common.DataSeries.add(data2);
		
	}

	/**
	 * @title 标题
	 * @titleX 起始点X坐标
	 * @titleY 起始点Y坐标
	 * @titleColor title颜色
	 */
	private void setTitle(){
		Common.title = "成都空气质量";
		Common.titleX = 40;
		Common.titleY = 70;
		Common.titleColor = Color.GRAY;
	}
	
	/**
	 * @keyWidth 图例宽度
	 * @keyHeight 图例高度
	 * @keyToLeft 图例起始点X坐标
	 * @keyToTop 图例起始点Y坐标
	 * @keyToNext 图例之间的距离
	 * @keyTextPadding 图例文字和图的距离
	 */
	private void setKey(){
		//设置图例参数
		Common.keyWidth = 30;
		Common.keyHeight = 10;
		Common.keyToLeft = 200;
		Common.keyToTop = 80;
		Common.keyToNext = 80;
		Common.keyTextPadding = 5;
	}
	
	/**
	 * @xScaleArray X轴刻度
	 * @xScaleColor x轴颜色
	 * @yScaleArray y轴刻度
	 * @levelName y轴分级名称
	 * @yScaleColor y轴分级颜色
	 */
	private void setAxis(){
		//设置轴参数
		Common.xScaleArray = new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
//		Common.xScaleColor = Color.YELLOW;
		
		//yScaleArray需要比levelName和color多出一个数
		Common.yScaleArray = new int[]{23,25,50,100,200,300,500};
		Common.levelName = new String[]{"优","良","轻度","中度","重度","严重"};
		Common.yScaleColor = new int[]{0xff00ff00,0xffffff00,0xffffa500,0xffff4500,0xffdc143c,0xffa52a2a};
	}
	
	private void addView(){
		int width=0;
		if(mp==false)
			width=Common.screenWidth*7/8+10;
		else
			width=Common.screenWidth*5/2;
		
		statisticsOneView.initValue(width, Common.screenHeight *6/8-10, mp);
		axisY.initValue(Common.screenHeight *6/8-10);
		
		axisLayout.removeAllViews();
		axisLayout.addView(axisY);
		
		threndLine_Layout.removeAllViews();
		threndLine_Layout.addView(statisticsOneView);
		
		title_layout.removeAllViews();
		title_layout.addView(titleView);
	}
}
