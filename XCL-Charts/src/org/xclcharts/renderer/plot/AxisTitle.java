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
 * @version 1.0
 */
package org.xclcharts.renderer.plot;

import org.xclcharts.renderer.XEnum;

import android.graphics.Color;
import android.graphics.Paint;
//import android.graphics.Paint.Align;
import android.graphics.Paint.Align;

/**
 * @ClassName AxisTitle
 * @Description  图例(AxisTitle)基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * 
 */
public class AxisTitle {
	
	//图例文字画笔
	 private Paint mPaintLeftAxisTitle = null;
	 private Paint mPaintLowerAxisTitle = null;
	 private Paint mPaintRightAxisTitle = null;
	//图例文字说明
	 private String mLeftAxisTitle = "";
	 private String mLowerAxisTitle = "";
	 private String mRightAxisTitle = "";	 
	 
	 protected XEnum.AxisTitleStyle mAxisTitleStyle = XEnum.AxisTitleStyle.NORMAL;
	 protected String mCrossPointTitle = "";
	 
	 //偏移距离
	 protected float mLeftAxisTitleOffsetX = 0.f;
	 protected float mRightAxisTitleOffsetX = 0.f;
	 protected float mLowerAxisTitleOffsetY = 0.f;
	 
	
	public AxisTitle()
	{							
		
	}
		
	private void initLeftAxisTitlePaint()
	{
		if(null == mPaintLeftAxisTitle)
		{
		 mPaintLeftAxisTitle = new Paint();
		 mPaintLeftAxisTitle.setTextAlign(Align.CENTER);
		 mPaintLeftAxisTitle.setAntiAlias(true);
		 mPaintLeftAxisTitle.setTextSize(26);
		 mPaintLeftAxisTitle.setColor(Color.rgb(255, 153, 204));
		}
	}
	
	private void initLowerAxisTitlePaint()
	{
		if(null == mPaintLowerAxisTitle)
		{
		 mPaintLowerAxisTitle = new Paint();
		 mPaintLowerAxisTitle.setTextAlign(Align.CENTER);
		 mPaintLowerAxisTitle.setAntiAlias(true);
		 
		 mPaintLowerAxisTitle.setTextSize(26);
		 mPaintLowerAxisTitle.setColor(Color.rgb(58, 65, 83));
		}
	}
	
	private void initRightAxisTitlePaint()
	{
		if(null == mPaintRightAxisTitle)
		{
		 mPaintRightAxisTitle = new Paint();
		 mPaintRightAxisTitle.setTextAlign(Align.CENTER);
		 mPaintRightAxisTitle.setAntiAlias(true);
		 
		 mPaintRightAxisTitle.setTextSize(26);
		 mPaintRightAxisTitle.setColor(Color.rgb(51, 204, 204));	
		}
	}
	
	/**
	 * 开放左边图例画笔
	 * @return 画笔
	 */
	public Paint getLeftTitlePaint() {
		initLeftAxisTitlePaint();
		return mPaintLeftAxisTitle;
	}

	/**
	 * 开放底部图例画笔
	 * @return 画笔
	 */
	public Paint getLowerTitlePaint() {
		initLowerAxisTitlePaint();
		return mPaintLowerAxisTitle;
	}

	/**
	 * 开放右边图例画笔
	 * @return 画笔
	 */
	public Paint getRightTitlePaint() {
		initRightAxisTitlePaint();
		return mPaintRightAxisTitle;
	}

	/**
	 * 设置左边图例内容
	 * @param title 图例内容
	 */
	public void setLeftTitle(String title) {
		this.mLeftAxisTitle = title;
	}

	/**
	 * 设置底部图例内容
	 * @param title 图例内容
	 */
	public void setLowerTitle(String title) {
		this.mLowerAxisTitle = title;
	}


	/**
	 * 设置右边图例内容
	 * @param title 图例内容
	 */
	public void setRightTitle(String title) {
		this.mRightAxisTitle = title;
	}
	
	/**
	 * 返回左边图例内容
	 * @return 图例内容
	 */
	public String getLeftTitle() {
		return mLeftAxisTitle;
	}
	
	/**
	 * 返回底部图例内容
	 * @return 图例内容
	 */
	public String getLowerTitle() {
		return mLowerAxisTitle;
	}
	
	/**
	 * 返回右边图例内容
	 * @return 图例内容
	 */
	public String getRightTitle() {
		return mRightAxisTitle;
	}
	
	/**
	 * 设置轴标题显示风格
	 * @param style
	 */
	public void setTitleStyle(XEnum.AxisTitleStyle style)
	{
		mAxisTitleStyle = style;
	}
	
	/**
	 * 设置 交叉点轴标题
	 * @param title  标题
	 */
	public void setCrossPointTitle(String title) {
		this.mCrossPointTitle = title;
	}
	
	/**
	 * 左边轴标题偏移距离
	 * @param offset 偏移距离
	 */
	public void setLeftAxisTitleOffsetX(float offset){
		mLeftAxisTitleOffsetX = offset;
	}
	
	/**
	 * 右边轴标题偏移距离
	 * @param offset 偏移距离
	 */
	public void setRightAxisTitleOffsetX(float offset){
		mRightAxisTitleOffsetX = offset;
	}
	
	/**
	 * 底部轴标题偏移距离
	 * @param offset 偏移距离
	 */
	public void setLowerAxisTitleOffsetY(float offset){
		mLowerAxisTitleOffsetY = offset;
	}
	
}
