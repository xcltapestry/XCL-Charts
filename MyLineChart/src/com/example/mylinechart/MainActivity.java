package com.example.mylinechart;

import java.util.ArrayList;
import com.data.Common;
import com.data.MyData;
import com.data.XY;
import com.view.AxisXView;
import com.view.AxisYView;
import com.view.StatisticsOneView;
import com.view.TitleView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity {
	private LinearLayout axisYLayout = null;
	private LinearLayout axisXLayout = null;
	private LinearLayout threndLine_Layout = null;
	private LinearLayout title_layout = null;
	
	private TitleView titleView;
	private StatisticsOneView statisticsOneView;
	private AxisYView axisY;
	private AxisXView axisX;
	
	private XY xy = new XY();
	private float oldX = 0;
	private float oldY = 0;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
		Common.screenWidth = mDisplayMetrics.widthPixels;
		Common.screenHeight = mDisplayMetrics.heightPixels;
		
		//设置图区宽高、内容宽高
		Common.layoutWidth = Common.screenWidth *5/2;
		Common.layoutHeight = Common.screenHeight * 6/8;
		Common.viewWidth = Common.screenWidth *5/2;
		Common.viewHeight = Common.screenHeight *12/8;
		
		init();
		
		
		//监听双击事件
//		threndLine_Layout.setOnTouchListener(new View.OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				//滑动不会触发up事件，故在up触发时计数
//				if(MotionEvent.ACTION_UP == event.getAction()){
//					count++;  
//					if(count == 1){  
//						firClick = System.currentTimeMillis(); 
//						new Thread(new Runnable(){   
//						    public void run(){   
//						        try {
//									Thread.sleep(500);
//									count=0;
//									firClick=0;
//									secClick=0;
//								} catch (InterruptedException e) {
//									e.printStackTrace();
//								}   
//						    }  
//						}).start();
//
//					} else if (count == 2){  
//						secClick = System.currentTimeMillis();  
//						if(secClick - firClick < 500){  
//							if(mp==false)
//								mp=true;
//							else 
//								mp=false;
//							addView();
//						}  
//						count = 0;  
//						firClick = 0;  
//						secClick = 0; 
//					}  
//				}
//				return true;
//			}
//		});
		
		//自定义参数
		setTitle();
		setKey();
		setAxis();
		setData();
		
		//填充
		addView();
	}
	
	/**
	 * 初始化各绘图组件
	 * 包括设置高宽、位置
	 */
	private void init(){
		title_layout = (LinearLayout) findViewById(R.id.titleView);

		axisXLayout = (LinearLayout) findViewById(R.id.axisX);
		RelativeLayout.LayoutParams xParams = (LayoutParams) axisXLayout.getLayoutParams();
		xParams.height = Common.layoutHeight;
		xParams.width = Common.layoutWidth;
		xParams.setMargins(Common.YWidth, 0, 0, 0);
		axisXLayout.setLayoutParams(xParams);
		
		axisYLayout = (LinearLayout) findViewById(R.id.axisY);
		RelativeLayout.LayoutParams yParams = (LayoutParams) axisYLayout.getLayoutParams();
		yParams.height = Common.layoutHeight;
		yParams.setMargins(0, 0, 0, yParams.bottomMargin + Common.XHeight);
		axisYLayout.setLayoutParams(yParams);
		
		threndLine_Layout = (LinearLayout) findViewById(R.id.thrend_line);
		RelativeLayout.LayoutParams hParams = (LayoutParams) threndLine_Layout.getLayoutParams();
		hParams.height = Common.layoutHeight;
		hParams.width = Common.layoutWidth;
		hParams.setMargins(Common.YWidth, 0, 0, hParams.bottomMargin + Common.XHeight);
		threndLine_Layout.setLayoutParams(hParams);
		

		
		//实例化View
		axisY = new AxisYView(this);
		axisX = new AxisXView(this);
		statisticsOneView = new StatisticsOneView(this);
		titleView = new TitleView(this);
	}
	
	private void setData() {
		MyData data1 = new MyData();
		data1.setName("SO2");
		data1.setData( new int[]{55,202,178,158,256,299,  
								87,99,101,213,119,233,  
								95,45,76,68,149,56,  
								47,72,23,192,115,214} );
		data1.setColor(0xff8d77ea);
		
		MyData data2 = new MyData();
		data2.setName("CO");
		data2.setData( new int[]{-1,210,190,-1,240,250,  
								80,85,90,230,100,220,  
								70,30,70,80,130,40,  
								30,80,40,160,100,210} );
		data2.setColor(0xff43ce17);
		
		Common.DataSeries = new ArrayList<MyData>();
		Common.DataSeries.add(data1);
		Common.DataSeries.add(data2);
		
	}

	private void setTitle(){
		Common.title = "成都空气质量";
		Common.titleX = 40;
		Common.titleY = 70;
		Common.titleColor = Color.GRAY;
	}
	
	private void setKey(){
		//设置图例参数
		Common.keyWidth = 30;
		Common.keyHeight = 10;
		Common.keyToLeft = 200;
		Common.keyToTop = 80;
		Common.keyToNext = 80;
		Common.keyTextPadding = 5;
	}
	
	private void setAxis(){
		//设置轴参数
		Common.xScaleArray = new String[]{"0","100","200","300","400","500","600","700","800","900","1000","1100","1200","1300","1400","1500","1600","1700","1800","1900","2000","2100","2200","2300"};
//		Common.xScaleColor = Color.YELLOW;
		
		//yScaleArray需要比levelName和color多出一个数
		Common.yScaleArray = new int[]{23,25,50,100,200,300,500};
		Common.levelName = new String[]{"优","良","轻度","中度","重度","严重"};
		Common.yScaleColor = new int[]{0xff00ff00,0xffffff00,0xffffa500,0xffff4500,0xffdc143c,0xffa52a2a};
	}
	
	private void addView(){
		int width=0;
//		if(mp==false)
//			width=Common.screenWidth*7/8+10;
//		else
			width=Common.viewWidth;
			
		//设定初始定位Y坐标
		xy.y = Common.viewHeight - Common.layoutHeight;
		
		statisticsOneView.initValue(width, Common.viewHeight, true);//传入宽、高、是否在折线图上显示数据
		statisticsOneView.scrollTo(0, xy.y);
		
		axisY.initValue(Common.viewHeight);//传入高度
		axisY.scrollTo(0, xy.y);
		
		axisX.initValue(width, Common.viewHeight);//传入高度
		axisX.scrollTo(0, xy.y);
		
		axisYLayout.removeAllViews();
		axisYLayout.addView(axisY);
		
		axisXLayout.removeAllViews();
		axisXLayout.addView(axisX);
		
		threndLine_Layout.removeAllViews();
		threndLine_Layout.addView(statisticsOneView);
		
		title_layout.removeAllViews();
		title_layout.addView(titleView);
		
		//监听滑动事件
		statisticsOneView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					oldX = event.getX();
					oldY = event.getY();
				}
				if(event.getAction() == MotionEvent.ACTION_MOVE){
					parseXY( xy.x+=oldX-event.getX() , xy.y+=oldY-event.getY() , statisticsOneView.getWidth() , statisticsOneView.getHeight() , threndLine_Layout);
					statisticsOneView.scrollTo(xy.x, xy.y);
					axisY.scrollTo(0, xy.y);
					axisX.scrollTo(xy.x, Common.viewHeight - Common.layoutHeight);
					oldX = event.getX();
					oldY = event.getY();
				}
				return true;
			}
		});
	}
	
	protected void parseXY(float x,float y,int width,int height,LinearLayout parent) {
		int parentWidth = parent.getWidth();
		int parentHeight = parent.getHeight();
		if(x<0)
			xy.x = 0;
		else if(x > width-parentWidth)
			xy.x = width-parentWidth;
		else
			xy.x = (int) x;
		
		if(y<0)
			xy.y = 0;
		else if(y > height-parentHeight)
			xy.y = height-parentHeight;
		else
			xy.y = (int) y;
		
		//初步防抖
		if(width<=parentWidth)
			xy.x = 0;
		if(height<=parentHeight)
			xy.y = 0;
		System.out.println("y="+xy.y);
	}
}
