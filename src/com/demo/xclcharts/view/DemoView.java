package com.demo.xclcharts.view;

import org.xclcharts.common.SysinfoHelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

@SuppressLint("NewApi")
public class DemoView extends View {
	
	protected int mScrWidth = 0;
	protected int mScrHeight = 0;

	public DemoView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		//禁用硬件加速
		disableHardwareAccelerated();	
		//得到屏幕信息
		getScreenInfo();
		//初始化绘图相关的东东		
		//initChart();
	}

	
	/**
	 * 用于初始化
	 */
	private void initChart()
	{
		
	}
	
	/**
	 * 绘制图表
	 * @param canvas 视图画布
	 */
	protected void drawChart(Canvas canvas)
	{
		
	}
	
	 public void onDraw(Canvas canvas)
	    {
		 
		  try {	
			  drawChart(canvas);	    	 		
		  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("ERROR-DemoView", e.toString());
		  }	
		  
	    }
	 
	 
	 /**
		 * 得到屏幕信息
		 */	
		private void getScreenInfo()
		{
			//屏幕信息
			DisplayMetrics dm = getResources().getDisplayMetrics();
			
			mScrWidth = dm.widthPixels;
			mScrHeight = dm.heightPixels;					
		}
		
		
		/**
		 * 禁用硬件加速.
		 * 原因:android自3.0引入了硬件加速，即使用GPU进行绘图,但它并不能完善的支持所有的绘图，
		 * 通常表现为内容不可见，异常或渲染错误。所以类了保证图表的正常显示，强制禁用掉.
		 */
		private void disableHardwareAccelerated()
		{
			SysinfoHelper sysinfo = new SysinfoHelper();
			if(sysinfo.supportHardwareAccelerated())
			{		
				// 是否开启了硬件加速,如开启将其禁掉，否则在有些机器上显示不出一些图形,如Rect或Path
				if(!isHardwareAccelerated())
				{
					setLayerType(View.LAYER_TYPE_SOFTWARE,null); 
				}
			}
		}
		
		public int getScreenWidth() {
			return mScrWidth;
		}


		public int getScreenHeight() {
			return mScrHeight;
		}
}
