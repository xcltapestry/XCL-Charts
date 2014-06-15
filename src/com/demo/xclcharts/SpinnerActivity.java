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
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version v0.1
 */
package com.demo.xclcharts;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.demo.xclcharts.view.SpinnerBarChart01View;
import com.demo.xclcharts.view.SpinnerPieChart01View;


/**
 * @ClassName SpinnerActivity
 * @Description 通过下拉来切换同数据源，不同显示风格的图表	
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */

public class SpinnerActivity extends Activity {
	
	private Spinner mSpinner = null;
	private LinearLayout mLaychart = null;
	private int mSelected = 0;
	private int mMoveHeight = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner);
		
		Bundle bunde = this.getIntent().getExtras();  		
		String title = bunde.getString("title"); 
		this.setTitle(title);
		mSelected = bunde.getInt("selected");  
			
		mLaychart = (LinearLayout) this.findViewById(R.id.lay_chart);			 		 		
		mSpinner = (Spinner) findViewById(R.id.spinner1);
		mMoveHeight = mSpinner.getHeight();		
	
		String[] mItems = null;
		// 建立数据源
		if(0 == mSelected )
		{
			mItems = getResources().getStringArray(R.array.spinnerbarstyle); 
		}else{
			mItems = getResources().getStringArray(R.array.spinnercirstyle);
		}

		ArrayAdapter<String> _Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems);	
		mSpinner.setAdapter(_Adapter);		
		mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		
				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
				}
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					
					renderChart(position);
				}
			});
		
		
		renderChart(0);
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
	    		intent.setClass(SpinnerActivity.this,AboutActivity.class);    				
	    		startActivity(intent); 	        
	            break;
	         default:
	        }
	        return true;
	    }
	private void renderChart(int position)
	{
		mLaychart.removeAllViews();
		if(0 == mSelected )
		{
			SpinnerBarChart01View barChart01= new SpinnerBarChart01View(this,position,mMoveHeight);
			mLaychart.addView(barChart01); 
		}else{
			SpinnerPieChart01View pieChart01= new SpinnerPieChart01View(this,position,mMoveHeight);
			mLaychart.addView(pieChart01); 
		}
		
	}

}
