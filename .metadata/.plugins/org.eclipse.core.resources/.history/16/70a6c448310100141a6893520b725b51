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

package org.xclcharts.renderer.axis;

import org.xclcharts.renderer.XEnum;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;


/**
 * @ClassName Axis
 * @Description  轴(axis)基类，定义了刻度，标签，等的属性
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class Axis {
	
	//轴线画笔
	private Paint mPaintAxis = null;
	
	//是否显示轴线
	private boolean mAxisLineVisible = true;	
	
	//数据轴刻度线与边上的标注画笔
	private Paint mPaintTickMarks = null;
	private Paint mPaintAxisTickLabel = null;	 
	 
	//数据轴刻度线与边上的标注是否显示
	private boolean mTickMarksVisible = true;
	private boolean mTickLabelVisible = true;
	 
	//刻度标记文字旋转角度
	private float mTickLabelRotateAgent = 0.0f; //-45f;
		
	//是否显示轴(包含轴线，刻度线和标签)
	private boolean mAxisVisible = true;	
	 
	//设置轴线条粗细
	private int mAxisLineWidth = 5;	
	 
	 //默认刻度线所占宽度
	private int mTickMarksLength = 15;	
	
	//刻度标记与轴的间距
	private int mTickLabelMargin = 10;	
	
	//标签显示位置，分别在轴的左边，中间，右边
	private Align mTickMarksAlign  = Align.RIGHT;
	
	//标签显示位置，分别在轴的上面，中间，底下
	private XEnum.Position mTickMarksPosition =  XEnum.Position.LOWER;
	 

	public Axis()
	{							
		initPaint();
	}
	
	private void initPaint()
	{
		mPaintAxis = new Paint();
		mPaintTickMarks = new Paint();
		mPaintAxisTickLabel = new Paint();		
		
		mPaintAxis.setColor(Color.BLACK);		
		mPaintAxis.setStrokeWidth(mAxisLineWidth);
		
		mPaintTickMarks.setColor(Color.BLACK);	
		mPaintTickMarks.setStrokeWidth(3);
		
		mPaintAxisTickLabel.setColor(Color.BLACK);	
		mPaintAxisTickLabel.setTextAlign(Align.RIGHT);
		mPaintAxisTickLabel.setTextSize(18);
		mPaintAxisTickLabel.setAntiAlias(true);		
	}
	
	/**
	 * 是否显示轴(包含轴线，刻度线和标签)
	 * @param visible 显示轴
	 */
	public void setVisible(boolean visible)
	{
		mAxisVisible = visible; 
	}
	
	/**
	 * 返回是否显示轴(包含轴线，刻度线和标签)
	 * @return 是否显示
	 */
	public boolean getVisible()
	{
		return mAxisVisible;
	}

	
	/**
	 * 设置是否显示轴线
	 * @param visible 是否显示轴线
	 */
	public void setAxisLineVisible(boolean visible)
	{
		mAxisLineVisible = visible; 
	}
	
	/**
	 * 返回是否显示轴线
	 * @return 是否显示
	 */
	public boolean getAxisLineVisible()
	{
		return mAxisLineVisible;
	}
	
	/**
	 * 开放轴线画笔
	 * @return 画笔
	 */
	public Paint getAxisPaint() {
		return mPaintAxis;
	}
	
	/**
	 * 开放轴刻度线画笔
	 * @return 画笔
	 */
	public Paint getTickMarksPaint() {
		return mPaintTickMarks;
	}

	/**
	 * 开放轴标签画笔
	 * @return	画笔
	 */
	public Paint getAxisTickLabelPaint() {
		return mPaintAxisTickLabel;
	}

	/**
	 * 设置是否显示轴刻度线
	 * @param visible 是否显示
	 */
	public void setTickMarksVisible(boolean visible) {
		this.mTickMarksVisible = visible;
	}
	
	/**
	 * 返回设置是否显示轴刻度线
	 * @return 是否显示
	 */
	public boolean getTickMarksVisible()
	{
		return mTickMarksVisible;
	}

	/**
	 * 设置是否显示轴标签
	 * @param visible 是否显示
	 */
	public void setTickLabelVisible(boolean visible) {
		this.mTickLabelVisible = visible;
	}

	/**
	 * 返回是否显示轴标签
	 * @return 是否显示
	 */
	public boolean getTickLabelVisible() {
		return this.mTickLabelVisible ;
	}
	
	/**
	 * 返回轴标签文字旋转角度
	 * @return 旋转角度
	 */
	public float getTickLabelRotateAgent() {
		return mTickLabelRotateAgent;
	}

	/**
	 * 设置轴标签文字旋转角度
	 * @param rotateAgent 旋转角度
	 */
	public void setTickLabelRotateAgent(float rotateAgent) {
		this.mTickLabelRotateAgent = rotateAgent;
	}
		
	/**
	 * 返回轴刻度线长度
	 * @return 刻度线长度
	 */
	public int getTickMarksLength()
	{
		return mTickMarksLength;
	}
	
	
	/**
	 * 设置轴刻度线与标签间的间距
	 * @param margin 间距
	 */
	public void setTickLabelMargin(int margin)
	{
		mTickLabelMargin = margin;
	}

	/**
	 * 返回轴刻度线与标签间的间距
	 * @return 间距
	 */
	public int getTickLabelMargin()
	{
		return mTickLabelMargin;
	}
	
	/**
	 *  设置时刻度显示在上，中，下哪个地方,针对横轴
	 * @param position 上方，居中，下方
	 */
	public void setVerticalTickPosition(XEnum.Position position)
	{
		mTickMarksPosition = position;
	}
	
	/**
	 * 返回轴上刻度线显示的位置
	 * @return 位置
	 */
	public XEnum.Position getVerticalTickPosition()
	{
		return mTickMarksPosition;
	}
	
	
	/**
	 * 设置刻度靠左，中，右哪个位置显示,针对竖轴
	 * @param align 靠左，居中，靠右
	 */
	public void setHorizontalTickAlign(Align align)
	{
		mTickMarksAlign = align;
	}
	
	public Align getHorizontalTickAlign()
	{
		return mTickMarksAlign;
	}
	
	
	
	//轴结束方式,以Default还是箭头什么的,暂不实现
	//public void setEndArrowType()
	//{
		
	//}
	
	
	
}
