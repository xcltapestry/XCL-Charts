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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.demo.xclcharts.view.GraphicalView;

@SuppressLint("NewApi")
public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置没标题
		setContentView(R.layout.activity_about);

		GradientDrawable grad = new GradientDrawable(// 渐变色 WHITE
				Orientation.TOP_BOTTOM, new int[] {
						(int) Color.rgb(133, 224, 224),
						(int) Color.rgb(51, 136, 103) });
		getWindow().setBackgroundDrawable(grad);

		TextView tv_title = (TextView) findViewById(R.id.tv_title);
		String html = "<big><font color ='red'>XCL-Charts V0.1</font></big><br/>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;Android开源图表库,基于原生的Canvas来绘制各种图表。<br/>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;目前支持3D/非3D柱形图、3D/非3D饼图、堆叠图、面积图、" +
				"折线图、曲线图、环形图、南丁格尔玫瑰图、仪表盘、圆形图等等，并支持图表的混合显示及同数据源不同类型图表切换的功能。<br/>"
				+ "<br/><big><font color ='red'>License</font></big><br/>"
				+ "采用Apache v2 License开源协议。"
				+ "<br/><big><font color ='red'>代码托管地址</font></big><br/>"
				+ "https://github.com/xcltapestry/XCL-Charts " 
				+ "<br/> https://git.oschina.net/xclcharts/XCL-Charts";
		CharSequence charSequence = Html.fromHtml(html);
		tv_title.setText(charSequence);
		tv_title.setMovementMethod(LinkMovementMethod.getInstance());

		TextView tv_info = (TextView) findViewById(R.id.tv_info);
		final String htmlInfo = "<html><head><title></title></head><body>"
				+ " <br/><big>About the Author</big>"
				+ " <br/><b>熊传亮</b>"
				+ "   <br/>&nbsp;&nbsp;&nbsp;&nbsp;有Oracle 10g OCP 及IBM Certified Systems Expert认证，" +
				"目前在深圳是一名挂着DBA职位，写着C/C++的DBA。"
				+ " <b>^_^</b>"		
				+ " <br/><br/>有什么改进或建议可发邮件联系或至博客留言。 "
				+ " <br/><big>Blog:http://blog.csdn.net/xcl168</big> "
				+ " <br/><big>Mail:xcl_168@aliyun.com</big> "
				+ " <br/><br/>感谢 cubehead的大力修正及其它网友的支持与反馈。"
				
				+ "</body></html>";
		CharSequence charSequenceInfo = Html.fromHtml(htmlInfo);
		tv_info.setText(charSequenceInfo);
		tv_info.setMovementMethod(LinkMovementMethod.getInstance());
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		menu.add(Menu.NONE, Menu.FIRST + 1, 0, "官网");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case Menu.FIRST + 1:
			String URL = getResources().getString(R.string.helpurl);
			Uri uri = Uri.parse(URL);
			Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent2);
			finish();
			break;
		default:
		}
		return true;
	}

}
