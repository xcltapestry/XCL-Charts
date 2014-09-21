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
package org.xclcharts.renderer;

import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.event.click.PointPosition;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.Log;

/**
 * @ClassName RdChart
 * @Description  这是雷达图,极限图等图的基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public class RdChart extends EventChart {
	
	private String TAG = "RdChart";
	
	//半径
	private float mRadius=0.0f;	
	//初始偏移角度
	private int mOffsetAngle = 0;//180;		
					
	//格式化线中点的标签显示
	 private IFormatterDoubleCallBack mDotLabelFormatter;		
	 
	//开放标签和线画笔让用户设置
	private Paint mPaintLabel = null;	
	private Paint mPaintLine = null;
		
	
	public RdChart() {
		super();
		initChart();
	}
	
	private void initChart() {								
	}
	
	@Override
	protected void calcPlotRange()
	{
		super.calcPlotRange();		
		this.mRadius = Math.min( div(this.plotArea.getWidth() ,2f) , 
				 				 div(this.plotArea.getHeight(),2f) );	
	}
	
	/**
	 * 返回当前点击点的信息
	 * @param x 点击点X坐标
	 * @param y	点击点Y坐标
	 * @return 返回对应的位置记录
	 */
	public PointPosition getPositionRecord(float x,float y)
	{		
		return getPointRecord(x,y);
	}
		
	
	/**
	 * 返回半径
	 * @return 半径
	 */
	public float getRadius()
	{
		return mRadius;
	}
	
	/**
	 * 设置图起始偏移角度
	 * @param Angle 偏移角度
	 */
	public void setInitialAngle(final int Angle)
	{
		if(Angle < 0 || Angle > 360)
		{
			Log.e(TAG, "起始偏移角度不能小于0或大于360");
		}else
			mOffsetAngle = Angle;
	}
	

	/**
	 * 返回图的起始偏移角度
	 * @return 偏移角度
	 */
	public int getInitialAngle()
	{
		return mOffsetAngle;
	}
	
	
	/**
	 * 设置线上点标签显示格式
	 * 
	 * @param callBack
	 *            回调函数
	 */
	public void setDotLabelFormatter(IFormatterDoubleCallBack callBack) {
		this.mDotLabelFormatter = callBack;
	}

	/**
	 * 返回线上点标签显示格式
	 * 
	 * @param value 传入当前值
	 * @return 显示格式
	 */	
	protected String getFormatterDotLabel(double value) {
		String itemLabel = "";
		try {
			itemLabel = mDotLabelFormatter.doubleFormatter(value);
		} catch (Exception ex) {
			itemLabel = Double.toString(value);
			// DecimalFormat df=new DecimalFormat("#0");
			// itemLabel = df.format(value).toString();
		}
		return itemLabel;
	}
		
	/**
	 * 开放标签画笔
	 * @return 画笔
	 */
	public Paint getLabelPaint()
	{
		if(null == mPaintLabel)
		{
			mPaintLabel = new Paint();
			mPaintLabel.setColor(Color.BLACK);
			mPaintLabel.setTextSize(18);
			mPaintLabel.setAntiAlias(true);
			mPaintLabel.setTextAlign(Align.CENTER);
		}
		return mPaintLabel;
	}
	
	/**
	 * 开放画网线的画笔
	 * @return 画笔
	 */
	public Paint getLinePaint()
	{
		if(null == mPaintLine)
		{
			mPaintLine = new Paint();
			mPaintLine.setColor((int)Color.rgb(180, 205, 230));
			mPaintLine.setAntiAlias(true);
			mPaintLine.setStyle(Style.STROKE);
			mPaintLine.setStrokeWidth(3);	
		}		
		return mPaintLine;
	}
	
	
	@Override
	public boolean render(Canvas canvas) throws Exception {
		// TODO Auto-generated method stubcalcPlotRange
		boolean ret = true;
		try {
				if (null == canvas)
						return false;
				
				
				canvas.save();
				//设置原点位置
				canvas.translate(mTranslateXY[0],mTranslateXY[1]);
				//绘制图表							
				//ret = postRender(canvas);	
				
				super.render(canvas);
				
				//绘制边框
				//renderBorder(canvas);
				//还原								
				canvas.restore();
				
				
				//return postRender(canvas);	
				
				//return ret;
				
				return true;
				
		} catch (Exception e) {
			throw e;
		}
		//return ret;
	}
	
	

}
