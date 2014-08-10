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

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.demo.xclcharts.view.DialChart01View;
import com.demo.xclcharts.view.DialChart05View;

public class DialChartActivity extends Activity {
	
	DialChart01View chart = null;
	DialChart05View chart5 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dial_chart);
		
		
		chart = (DialChart01View)findViewById(R.id.circle_view); 
		chart5 = (DialChart05View)findViewById(R.id.circle_view2); 
		
		final Button button = (Button)findViewById(R.id.button1); 
		
		button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				int max = 100;
			    int min = 1;
				Random random = new Random();
				int p = random.nextInt(max)%(max-min+1) + min;					
				float pf = p / 100f;
				chart.setCurrentStatus(pf);
				chart.invalidate();
				
				chart5.setCurrentStatus(pf);
				chart5.invalidate();
			}
			
		  }
		);
				
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dial_chart, menu);
		return true;
	}

}
