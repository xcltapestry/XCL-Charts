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

package org.xclcharts.renderer.bar;

import java.util.LinkedList;
import java.util.List;

import org.xclcharts.chart.common.DrawHelper;
import org.xclcharts.renderer.XEnum;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;

/**
 * @ClassName Bar
 * @Description  柱形基类，定义了柱形的一些属性
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */

public class Bar {
	
	//确定是横向柱形还是竖向柱形图
	private XEnum.Direction mBarDirection = XEnum.Direction.VERTICAL;
	
	//柱形画笔
	protected Paint mPaintBar = null;
	
	//文字画笔
	private Paint mPaintItemLabels = new Paint();		
	
	//柱形顶上文字偏移量
	private int mItemLabelsOffset = 10;
	//柱形顶上文字旋转角度
	private float mItemLabelsRotateAgent = 0.0f;	
	//是否显示柱形顶上文字标签
	private boolean mShowItemLabels = false;		
	
	public Bar()
	{				
		initPaint();
	}
	
	private void initPaint()
	{
		mPaintBar  = new Paint();
		mPaintBar.setColor((int)Color.rgb(252, 210, 9));
		mPaintBar.setStyle(Style.FILL);
		
		//柱形顶上的文字标签		
		mPaintItemLabels.setTextSize(12);
		mPaintItemLabels.setColor(Color.BLACK);
		mPaintItemLabels.setTextAlign(Align.CENTER);
	}
		
	/**
	 * 返回柱形的显示方向
	 * @return 显示方向
	 */
	public XEnum.Direction getBarDirection() {
		return mBarDirection;
	}

	/**
	 * 设置柱形的显示方向
	 * @param direction 显示方向
	 */
	public void setBarDirection(XEnum.Direction direction) {
		this.mBarDirection = direction;
	}

	/**
	 * 开放柱形画笔
	 * @return 画笔
	 */
	public Paint getBarPaint() {
		return mPaintBar;
	}
	
	/**
	 * 开放柱形顶部标签画笔
	 * @return 画笔
	 */
	public Paint getItemLabelsPaint() {
		return mPaintItemLabels;
	}

	/**
	 * 返回柱形顶部标签在显示时的偏移距离
	 * @return 偏移距离
	 */
	public int getItemLabelsOffset() {
		return mItemLabelsOffset;
	}

	/**
	 * 设置柱形顶部标签在显示时的偏移距离
	 * @param offset 偏移距离
	 */
	public void setItemLabelsOffset(int offset) {
		this.mItemLabelsOffset = offset;
	}

	/**
	 * 返回柱形顶部标签在显示时的旋转角度
	 * @return 旋转角度
	 */
	public float getItemLabelsRotateAgent() {
		return mItemLabelsRotateAgent;
	}

	/**
	 * 设置柱形顶部标签在显示时的旋转角度
	 * @param rotateAgent 旋转角度
	 */
	public void setItemLabelsRotateAgent(float rotateAgent) {
		this.mItemLabelsRotateAgent = rotateAgent;
	}

	/**
	 * 设置是否显示柱形顶部标签
	 * @param visible 是否显示
	 */
	public void setItemLabelsVisible(boolean visible) {
		this.mShowItemLabels = visible;
	}
	
	/**
	 * 返回是否显示柱形顶部标签
	 * @return 是否显示
	 */
	public boolean getItemLabelsVisible()
	{
		return mShowItemLabels;
	}
	
	/**
	 * 计算同标签多柱形时的Y分隔
	 * @param YSteps    Y轴步长
	 * @param barNumber  柱形个数
	 * @return 返回单个柱形的高度及间距
	 */	
	protected List<Integer> calcBarHeightAndMargin(float YSteps,int barNumber)
	{
			int labelBarTotalHeight = (int) Math.round(YSteps * 0.9);
			int barTotalInnerMargin = (int) Math.round(labelBarTotalHeight * 0.2);				
			int barInnerMargin = barTotalInnerMargin / barNumber;
			int barHeight = (labelBarTotalHeight - barTotalInnerMargin) / barNumber;
			
			List<Integer> ret = new LinkedList<Integer>();
			ret.add(barHeight);
			ret.add(barInnerMargin);
			return ret;
	}
	

	/**
	 * 计算同标签多柱形时的X分隔
	 * @param XSteps	X轴步长
	 * @param barNumber 柱形个数
	 * @return 返回单个柱形的宽度及间距
	 */
	protected List<Integer> calcBarWidthAndMargin(float XSteps,int barNumber)
	{
			int labelBarTotalWidth = (int) Math.round(XSteps * 0.9); 	
			int barTotalInnerMargin = (int) Math.round(labelBarTotalWidth * 0.2); 	
			int barTotalWidth = labelBarTotalWidth - barTotalInnerMargin;	   	
			
			int barInnerMargin = barTotalInnerMargin / barNumber;
			int barWidth = barTotalWidth / barNumber;				
			
			List<Integer> ret = new LinkedList<Integer>();
			ret.add(barWidth);
			ret.add(barInnerMargin);
			return ret;
	}
	
	/**
	 * 绘制柱形顶部标签
	 * @param text	内容	
	 * @param x		x坐标
	 * @param y		y坐标
	 * @param canvas 画布
	 */
	protected void drawBarItemLabel(String text,float x,float y,Canvas canvas)
	{
		//在柱形的顶端显示上柱形的当前值
			
		if(getItemLabelsVisible())
		{		
			DrawHelper dw = new DrawHelper();	
			dw.drawRotateText(text,
								x ,
								y,
	            			  getItemLabelsRotateAgent(),
	            			  canvas, 
	            			  getItemLabelsPaint());	
		}
	}

}
