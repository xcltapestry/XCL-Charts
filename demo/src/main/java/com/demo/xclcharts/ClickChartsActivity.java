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
 * @Description Android图表基类库演示
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version 1.0
 */

package com.demo.xclcharts;

import org.xclcharts.event.click.ArcPosition;
import org.xclcharts.event.click.ChartArcListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.demo.xclcharts.view.ClickPieChart01View;

public class ClickChartsActivity extends Activity {
		
	private ClickPieChart01View mCharts ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCharts = new ClickPieChart01View(this);			
		//Bundle bunde = this.getIntent().getExtras();  
		initActivity();
		setTitle("图表点击事件(View -> Activity)");
	}

	
	private void initActivity()
	{
		
		//完全动态创建,无须XML文件.
	       FrameLayout content = new FrameLayout(this);    
	       
	       //缩放控件放置在FrameLayout的上层，用于放大缩小图表
		   FrameLayout.LayoutParams frameParm = new FrameLayout.LayoutParams(
		   LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);  
		   frameParm.gravity = Gravity.BOTTOM|Gravity.RIGHT;  
		
		 
		   
		   //图表显示范围在占屏幕大小的90%的区域内
		   DisplayMetrics dm = getResources().getDisplayMetrics();		   
		   int scrWidth = (int) (dm.widthPixels * 0.9); 	
		   int scrHeight = (int) (dm.heightPixels * 0.9); 			   		
	       RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
	    		   													scrWidth, scrHeight);	
	       //居中显示
	        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);   
	        //图表view放入布局中，也可直接将图表view放入Activity对应的xml文件中
	        final RelativeLayout chartLayout = new RelativeLayout(this);  	
	        
	        chartLayout.addView( mCharts, layoutParams);
                   		  
	        //增加控件
		   ((ViewGroup) content).addView(chartLayout);		
		    setContentView(content);	
		    
		    
		    mCharts.setOnPlotClickListener(
		    		new ChartArcListener() {
				 
						  //可改成pointF,positionRecord,既当前点击点及位置记录
						  @Override
						  public void onClick(PointF point,ArcPosition positionRecord) {
						    
							  if( null == positionRecord) return;	
							 							 
							   /**
							    * 附注，如果数据源是从Activity传给view的，
							    * 则可在此通过 positionRecord.getDataID() 得到对应的id来取相关信息。
							    * 同理，其它图形也可依此形式来做，
							    * 	如onClick的参数ArcPosition依图形类别,
							    * 		可替换对应的PointPosition或BarPosition类
							    */
								Toast.makeText(ClickChartsActivity.this,									
										"[此处为Activity返回的信息] ID:" + positionRecord.getDataID() ,
										Toast.LENGTH_SHORT).show();
								
						  }
				 });
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, Menu.FIRST + 1, 0, "帮助");  
        menu.add(Menu.NONE, Menu.FIRST + 2, 0, "关于XCL-Charts"); 
		return true;
	}

	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        super.onOptionsItemSelected(item);
	        switch(item.getItemId())
	        {
	        case Menu.FIRST+1: 
	        	//String chartsHelp[] = getResources().getStringArray(R.array.chartsHelp);	        
	        	//String URL = chartsHelp[mSelected]; 	        	
	        	String URL =getResources().getString(R.string.helpurl);	        		        
		        Uri uri = Uri.parse(URL);  
		        Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);  
		        startActivity(intent2);  
		        finish();
	            break;
	        case Menu.FIRST+2:
		        Intent intent = new Intent();  
	    		intent.setClass(ClickChartsActivity.this,AboutActivity.class);    				
	    		startActivity(intent); 	        
	            break;
	        }
	        return true;
	    }
	
	

}
