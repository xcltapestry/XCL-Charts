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

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.demo.xclcharts.view.CircleChart01View;
import com.demo.xclcharts.view.CircleChart02View;
import com.demo.xclcharts.view.GaugeChart01View;

/**
 * @ClassName SeekBarActivity
 * @Description  有使用SeekBar,通过滑动来动态展示图形和仪表盘
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */

public class SeekBarActivity extends Activity {
	
	private LinearLayout mLaychart = null;
	
	private CircleChart01View mCirChart = null;
	private CircleChart02View mCirChart2 = null;
	private GaugeChart01View mGaChart = null;
	
	private TextView mDesc = null;
	private int mSelected = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		setContentView(R.layout.activity_seek_bar);
		
		SeekBar seekBar = (SeekBar) this.findViewById(R.id.sk_process);		
		mLaychart = (LinearLayout) this.findViewById(R.id.lay_chart);		 
		mDesc=(TextView)findViewById(R.id.tv_process);
			
		Bundle bunde = this.getIntent().getExtras();  		
		String title = bunde.getString("title"); 
		this.setTitle(title);
		mSelected = bunde.getInt("selected");  
		
		if(0 == mSelected ) //仪表盘
		{
			mGaChart = new GaugeChart01View(this);
			mLaychart.addView(mGaChart); 
			seekBar.setMax(180);
		}else if(1 == mSelected ){ 
			mCirChart2 = new CircleChart02View(this);
			mLaychart.addView(mCirChart2); 
			seekBar.setMax(100);
		}else if(2 == mSelected ){ //半圆
			mCirChart = new CircleChart01View(this);
			mLaychart.addView(mCirChart); 
			seekBar.setMax(100);
		}
		
		 seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
	         
	            @Override
	            public void onStopTrackingTouch(SeekBar seekBar) {	               
	            }
	          
	            @Override
	            public void onStartTrackingTouch(SeekBar seekBar) {	                
	            }
	        
	            @Override
	            public void onProgressChanged(SeekBar seekBar, int progress,
	                    boolean fromUser) {
	            	
	            	mDesc.setText(Integer.toString(progress));	            
		            if(0 == mSelected )
		       		{
		                mGaChart.setAngle(progress);
		        		mGaChart.chartRender();
		        		mGaChart.invalidate();	            	
		       		}else if(1 == mSelected ){
		        		mCirChart2.setPercentage(progress);
		        		mCirChart2.chartRender();
		        		mCirChart2.invalidate();
		       		}else if(2 == mSelected ){
		        		mCirChart.setPercentage(progress);
		        		mCirChart.chartRender();
		        		mCirChart.invalidate();
		       		}
	               
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
	        	String URL = getResources().getString(R.string.helpurl);	        		        
		        Uri uri = Uri.parse(URL);  
		        Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);  
		        startActivity(intent2);  
		        finish();
	            break;
	        case Menu.FIRST+2:
		        Intent intent = new Intent();  
	    		intent.setClass(SeekBarActivity.this,AboutActivity.class);    				
	    		startActivity(intent); 	        
	            break;
	         default:
	        }
	        return true;
	    }

}
