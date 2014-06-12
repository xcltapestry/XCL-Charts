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


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @ClassName GraphicalView
 * @Description  展示XCL-Charts图表的View基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * 于Android低版本的用户，可通过注释"禁用硬件加速"来支持图表的显示
 */

@SuppressLint("NewApi")
public class GraphicalView extends View { 

	protected int mScrWidth = 0;
	protected int mScrHeight = 0;
	
	//定义一个内存中的图片,作为绘图缓冲区
	protected Bitmap mCacheBitmap = null;
    //定义canvas对象
	protected Canvas mCacheCanvas = null;

	public GraphicalView(Context context) {
		super(context);
		
		
		//禁用硬件加速
		disableHardwareAccelerated();	
		//得到屏幕信息
		getScreenInfo();
		
		initCache();
	}
	
	
	public void initCache()
	{	
		//生成缓存区
		createCacheBitmap(getScreenWidth(),getScreenHeight());		
		setCacheBitmapCanvas();
	}
	
	
	  public void onDraw(Canvas canvas)
	    {
		 
		  try {
	
		     Paint bmpPaint = new Paint();		   
		     canvas.drawBitmap(mCacheBitmap, 0, 0, bmpPaint);		    
		     mCacheBitmap.recycle();
		  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("GraphicalView", e.toString());
		  }	
	    }
	  
	
	//可在此通过调试图大小来达到放到缩小的效果
	public boolean dispatchTouchEvent(MotionEvent event) {

		  switch (event.getAction()) {

		  case MotionEvent.ACTION_DOWN:

		   System.out.println("down");
		   break;

		  case MotionEvent.ACTION_MOVE:
		   System.out.println("move");
		   break;

		  case MotionEvent.ACTION_UP:
		   System.out.println("up");
		   break;
		  }

		  return true;

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
	 * 禁用硬件加速
	 */
	private void disableHardwareAccelerated()
	{
		// 是否开启了硬件加速,如开启将其禁掉，否则在有些机器上显示不出一些图形,如Rect或Path
		if(!isHardwareAccelerated())
		{
			setLayerType(View.LAYER_TYPE_SOFTWARE,null); 
		}
	}
	
	/**
	 * 生成缓存区
	 */
	protected void createCacheBitmap(int width,int height)
	{							
	  mCacheBitmap = Bitmap.createBitmap(width,height,Config.ARGB_8888);		   					 
	}
	
	private void setCacheBitmapCanvas()
	{
		//生成画布
	    mCacheCanvas = new Canvas();	    
	    //将图绘制到内存中的mCacheBitmap上
	    mCacheCanvas.setBitmap(mCacheBitmap);
	}

	public int getScreenWidth() {
		return mScrWidth;
	}


	public int getScreenHeight() {
		return mScrHeight;
	}

	
}
