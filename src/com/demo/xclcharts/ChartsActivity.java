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
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.demo.xclcharts.view.AreaChart01View;
import com.demo.xclcharts.view.Bar3DChart01View;
import com.demo.xclcharts.view.Bar3DChart02View;
import com.demo.xclcharts.view.BarChart01View;
import com.demo.xclcharts.view.BarChart02View;
import com.demo.xclcharts.view.CircleChart01View;
import com.demo.xclcharts.view.CircleChart02View;
import com.demo.xclcharts.view.DountChart01View;
import com.demo.xclcharts.view.GaugeChart01View;
import com.demo.xclcharts.view.LineChart01View;
import com.demo.xclcharts.view.MultiAxisChart01View;
import com.demo.xclcharts.view.MultiAxisChart02View;
import com.demo.xclcharts.view.Pie3DChart01View;
import com.demo.xclcharts.view.PieChart01View;
import com.demo.xclcharts.view.RoseChart01View;
import com.demo.xclcharts.view.SplineChart01View;
import com.demo.xclcharts.view.StackBarChart01View;
import com.demo.xclcharts.view.StackBarChart02View;

/**
 * @ClassName ChartsActivity
 * @Description  展示各类图表
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class ChartsActivity extends Activity {
	
	private int mSelected = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); //设置没标题
		
		View[] mCharts = new View[]{new BarChart01View(this),
				new BarChart02View(this),
				
				new Bar3DChart01View(this),
				new Bar3DChart02View(this),
				new StackBarChart01View(this),
				new StackBarChart02View(this),
				new LineChart01View(this),
				new SplineChart01View(this),
				new AreaChart01View(this),
				new MultiAxisChart01View(this),
				new MultiAxisChart02View(this),
				
				new PieChart01View(this),
				new Pie3DChart01View(this),
				new DountChart01View(this),
				new RoseChart01View(this)}; 
		
		
		Bundle bunde = this.getIntent().getExtras();  
		mSelected = bunde.getInt("selected");  
		String title = bunde.getString("title"); 			
		
		if(mSelected > mCharts.length - 1){									
			setContentView(R.layout.activity_charts);
			this.setTitle(Integer.toString(mSelected));
		}else{
				
		    final LinearLayout layout = new LinearLayout(this);  					
	        layout.setOrientation(LinearLayout.VERTICAL);  	        
	        layout.setLayoutParams(new LinearLayout.LayoutParams(  
	        	    LinearLayout.LayoutParams.MATCH_PARENT,  
	        	    LinearLayout.LayoutParams.MATCH_PARENT));	   
	        layout.addView(mCharts[mSelected]);  	   
	        setContentView(layout); 		        
			this.setTitle(title);
		}
						
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, Menu.FIRST + 1, 0, "Help");  
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
	    		intent.setClass(ChartsActivity.this,AboutActivity.class);    				
	    		startActivity(intent); 	        
	            break;
	        }
	        return true;
	    }
}
