package com.demo.xclcharts;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

public class GaugeChartActivity extends Activity {
	
	//圆
		com.demo.xclcharts.view.GaugeChart01View chart = null;
		//进度/状态
		TextView  process = null;
		SeekBar seekBar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gauge_chart);
		
		this.setTitle("仪表盘(Gauge Chart)");
		
		init();
		
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
    		intent.setClass(GaugeChartActivity.this,AboutActivity.class);    				
    		startActivity(intent); 	        
            break;
        }
        return true;
    }

	
	private void init()
	{
		chart = (com.demo.xclcharts.view.GaugeChart01View)findViewById(R.id.chart_view); 
		process = (TextView)findViewById(R.id.tv_process);          		
		seekBar = (SeekBar) this.findViewById(R.id.seekBar1);	
		seekBar.setMax(180);
				
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
	            	
	            	process.setText(Integer.toString(progress));
	            		            	               	       				       		
	       			chart.setAgent(progress);
	       			chart.chartRender();
	       			chart.invalidate();
	            }
	        });
	}
	
}
