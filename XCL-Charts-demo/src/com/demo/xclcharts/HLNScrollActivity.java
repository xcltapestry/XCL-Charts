package com.demo.xclcharts;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HLNScrollActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hlnscroll);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hlnscroll, menu);
		return true;
	}

}
