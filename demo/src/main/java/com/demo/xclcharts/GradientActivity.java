package com.demo.xclcharts;

import com.demo.xclcharts.view.PieChart02View;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class GradientActivity extends Activity {

	PieChart02View radialGradientView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_gradient);
		
		radialGradientView = new PieChart02View(this);
		setContentView(radialGradientView);
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
    		intent.setClass(GradientActivity.this,AboutActivity.class);    				
    		startActivity(intent); 	        
            break;
        }
        return true;
    }

}
