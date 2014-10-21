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
 * @version 1.5
 */
package org.xclcharts.chart;

import java.util.ArrayList;
import java.util.List;

import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.event.click.BarPosition;
import org.xclcharts.renderer.AxisChart;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.axis.AxisTick;
import org.xclcharts.renderer.bar.Bar;
import org.xclcharts.renderer.bar.FlatBar;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.util.Log;


/**
 * @ClassName RangeBarChart
 * @Description  范围柱形图的基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public class RangeBarChart  extends AxisChart {
	
	private static final String TAG = "RangeBarChart";

	// 柱形基类
	private FlatBar mFlatBar = new FlatBar();
	// 数据源
	private List<RangeBarData> mDataSet;
	
	private String mKey = "";
	private float mBarWidth = 50f;	
	private boolean mLabelVisible = true;
	

	//分类轴的最大，最小值
	private double mMaxValue = 0d;
	private double mMinValue = 0d;
	
	//轴刻度
	protected ArrayList<AxisTick> mLstDataTick = new  ArrayList<AxisTick>();
	protected ArrayList<AxisTick> mLstCateTick = new  ArrayList<AxisTick>();


	public RangeBarChart() {
				
		// 默认显示Key		
		//if(null !=plotLegend)plotLegend.show();
		
		categoryAxisDefaultSetting();
		dataAxisDefaultSetting();
	}
	
	@Override
	public XEnum.ChartType getType()
	{
		return XEnum.ChartType.RANGEBAR;
	}

	/**
	 * 开放柱形绘制类
	 * @return 柱形绘制类
	 */
	public Bar getBar() {
		return mFlatBar;
	}

	/**
	 * 分类轴的数据源
	 * 
	 * @param categories
	 *            分类集
	 */
	public void setCategories( List<String> categories) {	
		if(null != categoryAxis)categoryAxis.setDataBuilding(categories);
	}

	/**
	 * 设置数据轴的数据源
	 * 
	 * @param dataSeries
	 *            数据源
	 */
	public void setDataSource( List<RangeBarData> dataSeries) {	
		this.mDataSet = dataSeries;
	}

	/**
	 * 返回数据库的数据源
	 * @return 数据源
	 */
	public List<RangeBarData> getDataSource() {
		return mDataSet;
	}
	
	/**
	 * 设置柱形宽度
	 * @param width 宽度
	 */
	public void setBarWidth(float width)
	{
		mBarWidth = width;
	}
	
	/**
	 * 返回柱形宽度
	 * @return 柱形宽度
	 */
	public float getBarWidth()
	{
		return mBarWidth;
	}
	
	/**
	 * 设置图例
	 * @param key 图例
	 */
	public void setKey(String key)
	{
		mKey = key;
	}
	
	/**
	 * 返回图例 
	 * @return 图例
	 */
	public String getKey()
	{
		return mKey;
	}
	
	/**
	 * 设置是否在线上显示标签
	 * @param visible 是否显示
	 */
	public void setLabelVisible(boolean visible) 
	{
		mLabelVisible = visible;
	}
	
	/**
	 * 返回是否在线上显示标签
	 * @return 是否显示
	 */
	public boolean getLabelVisible()
	{
		return mLabelVisible;
	}

	
	
	private void categoryAxisDefaultSetting()
	{		
		if(null != categoryAxis)
		{
			categoryAxis.setHorizontalTickAlign(Align.CENTER);			
			categoryAxis.getTickLabelPaint().setTextAlign(Align.CENTER);					
			categoryAxis.setVerticalTickPosition(XEnum.VerticalAlign.BOTTOM);
		}
		
	}
	
	private void dataAxisDefaultSetting()
	{		
		if(null != dataAxis)
		{
			dataAxis.setHorizontalTickAlign(Align.LEFT);
			dataAxis.getTickLabelPaint().setTextAlign(Align.RIGHT);					
		}
	}
		

	/**
	 * 	竖向柱形图
	 *  Y轴的屏幕高度/数据轴的刻度标记总数 = 步长
	 * @return Y轴步长
	 */
	private float getVerticalYSteps(int tickCount) {		
		return (div(getPlotScreenHeight(),tickCount));
	}

	/**
	 * 竖向柱形图
	 * 得到X轴的步长
	 * X轴的屏幕宽度 / 刻度标记总数  = 步长
	 * @param num 刻度标记总数 
	 * @return X轴步长
	 */
	protected float getVerticalXSteps(int num) {
		//柱形图为了让柱形显示在tick的中间，会多出一个步长即(dataSet.size()+1)			
		return  div(getPlotScreenWidth() ,num);
	}	

	/**
	 * 绘制左边竖轴，及上面的刻度线和分类
	 */
	protected void renderVerticalBarDataAxis(Canvas canvas) {
		// 数据轴数据刻度总个数
		int tickCount = dataAxis.getAixTickCount();
		// 数据轴高度步长
		float YSteps = getVerticalYSteps(tickCount);
		float currentY = plotArea.getBottom();
		
		double slen = 0d;
		double currentTickLabel =  0d;

		// 数据轴(Y 轴)
		for (int i = 0; i <= tickCount; i++) {
			if (i == 0)
				continue;
			
			//将当前为第几个tick传递轴，用以区分是否为主明tick
			dataAxis.setAxisTickCurrentID(i);
			
			// 依起始数据坐标与数据刻度间距算出上移高度
			currentY = sub(plotArea.getBottom(), mul(i,YSteps));
			
			// 分类		
			slen = i * dataAxis.getAxisSteps();			
			currentTickLabel = MathHelper.getInstance().add(dataAxis.getAxisMin(), slen);
					
			// 从左到右的横向网格线		
			if ( i % 2 != 0) {
				plotGrid.renderOddRowsFill(canvas, plotArea.getLeft(),
						add(currentY,YSteps), plotArea.getPlotRight(), currentY);
			} else {
				plotGrid.renderEvenRowsFill(canvas, plotArea.getLeft(),
						add(currentY,YSteps), plotArea.getPlotRight(), currentY);
			}
			
			plotGrid.setPrimaryTickLine(dataAxis.isPrimaryTick());
			plotGrid.renderGridLinesHorizontal(canvas, plotArea.getLeft(), currentY,
											   plotArea.getPlotRight(), currentY);			
					
			if(i == tickCount)
			{
				mLstDataTick.add(new AxisTick(i,plotArea.getLeft(),plotArea.getTop(), Double.toString(currentTickLabel) ));
			}else{
				mLstDataTick.add(new AxisTick(i,plotArea.getLeft(),currentY, Double.toString(currentTickLabel) ));
				
			}
			
		}
		// 轴 线
		dataAxis.renderAxis(canvas, plotArea.getLeft(), plotArea.getBottom(),
				plotArea.getLeft(), plotArea.getTop());
	}

	/**
	 * 绘制竖向柱形图中的底部分类轴
	 */
	protected void renderVerticalBarCategoryAxis(Canvas canvas) {	
		// 得到分类轴数据集
		List<String> dataSet = categoryAxis.getDataSet();
		if(null == dataSet) return ;
		
		// 分类轴(X 轴)
		float currentX = plotArea.getLeft();
	
		// 依传入的分类个数与轴总宽度算出要画的分类间距数是多少
		// 总宽度 / 分类个数 = 间距长度  
		float XSteps = div(getPlotScreenWidth() , (dataSet.size() + 1)); 

		for (int i = 0; i < dataSet.size(); i++) {
			// 依初超始X坐标与分类间距算出当前刻度的X坐标
			currentX = add(plotArea.getLeft(),mul((i + 1) , XSteps)); 			

			// 绘制横向网格线
			if (plotGrid.isShowVerticalLines()) {
				canvas.drawLine(currentX, plotArea.getBottom(),
								currentX, plotArea.getTop(),
								plotGrid.getVerticalLinePaint());
			}
			// 画上分类/刻度线
			mLstCateTick.add(new AxisTick(i,currentX,plotArea.getBottom(), dataSet.get(i)));
			
			//categoryAxis.renderAxisVerticalTick(canvas,currentX,
			//				plotArea.getBottom(), dataSet.get(i));
		}
	}
	

	private float[] cataPosition(double min,double max)
	{
		float[] pos = new float[2];
		float axisDataHeight = (float) dataAxis.getAxisRange();	
		float scrHeight = getAxisScreenHeight();
		
		double t = MathHelper.getInstance().sub( min , dataAxis.getAxisMin() );	
		pos[0] = mul( scrHeight,div( dtof(t),axisDataHeight) );
				
		t = MathHelper.getInstance().sub( max , dataAxis.getAxisMin() );	
		pos[1] = mul( scrHeight,div( dtof(t),axisDataHeight) );
				
		return pos;
	}
	
	/**
	 *  显示数据的数据轴最大值
	 * @param value 数据轴最大值
	 */
	public void setCategoryAxisMax( double value)
	{
		mMaxValue = value;
	}	
	
	/**
	 * 设置分类轴最小值
	 * @param value 最小值
	 */
	public void setCategoryAxisMin( double value)
	{
		mMinValue = value;
	}	
		

	/**
	 * 绘制竖向柱形图
	 */
	protected boolean renderVerticalBar(Canvas canvas) {
				
		if(null == mDataSet) return false;	
		// 得到分类轴数据集
		List<String> cateDataSet = categoryAxis.getDataSet();
		if(null == cateDataSet) return false;
				
		renderVerticalBarDataAxis(canvas);
		renderVerticalBarCategoryAxis(canvas);		
		
		float XSteps = getVerticalXSteps(cateDataSet.size() + 1 );	
		float currentX = 0.0f,barMaxPos = 0.0f,barMinPos = 0.0f;
		 		 				
		float barWidthHalf = mBarWidth/2;
	
		float axisScreenWidth = getPlotScreenWidth(); 
		float fontHeight = DrawHelper.getInstance().getPaintFontHeight(
												mFlatBar.getItemLabelPaint());

		// X 轴 即分类轴
		int dataSetSize = mDataSet.size();	
		for (int i = 0; i < dataSetSize; i++) {
			// 得到分类对应的值数据集
			RangeBarData bd = mDataSet.get(i);					
			//currentX = add(plotArea.getLeft() , mul( (i+1) , XSteps));
			//
			currentX = (float) (axisScreenWidth * ( (bd.getX() - mMinValue ) / (mMaxValue - mMinValue))) ;  
			currentX =  add(plotArea.getLeft(),currentX);
			
			float[] pos = cataPosition(bd.getMin(), bd.getMax());
			
			barMaxPos = sub(plotArea.getBottom(),pos[0]);
			barMinPos = sub(plotArea.getBottom(),pos[1]);
			
			mFlatBar.renderBar(currentX - barWidthHalf ,barMaxPos,
							   currentX + barWidthHalf ,barMinPos,
							   canvas);
			//保存位置
			saveBarRectFRecord(i,0,currentX - barWidthHalf, barMaxPos,
								   currentX + barWidthHalf, barMinPos );
			
			if(getLabelVisible())
			{
				//柱形标签
				mFlatBar.renderBarItemLabel(getFormatterItemLabel(bd.getMax()), 
										currentX, barMinPos - fontHeight/2, canvas);	
			
				//柱形标签
				mFlatBar.renderBarItemLabel( getFormatterItemLabel(bd.getMin()), 
										currentX, barMaxPos + fontHeight + fontHeight/2, canvas);
			}
		}
		 
		// 轴 线
		dataAxis.renderAxis(canvas, plotArea.getLeft(), plotArea.getBottom(),
									plotArea.getPlotRight(), plotArea.getBottom());
		
		//轴刻度
		renderAxesTick(canvas);

		// 绘制柱形图例
		plotLegend.renderRangeBarKey(canvas,getKey(),mFlatBar.getBarPaint().getColor());
		
		return true;
	}
	
	//轴刻度
	private void renderAxesTick(Canvas canvas)
	{				
		drawCategoryAxisLabels(canvas,XEnum.Direction.VERTICAL,mLstCateTick);		
		mLstCateTick.clear();
		
		drawDataAxisLabels(canvas,XEnum.Direction.VERTICAL,mLstDataTick);		
		mLstDataTick.clear();
	}
	
	
	/**
	 * 返回当前点击点的信息
	 * @param x 点击点X坐标
	 * @param y	点击点Y坐标
	 * @return 返回对应的位置记录
	 */
	public BarPosition getPositionRecord(float x,float y)
	{		
		return getBarRecord(x,y);
	}
	

	@Override
	protected boolean postRender(Canvas canvas) throws Exception 
	{
		
		// 绘制图表
		try {
			super.postRender(canvas);
			
			//检查是否有设置分类轴的最大最小值		
			if(mMaxValue == mMinValue && 0 == mMaxValue)
			{
				Log.e(TAG,"请检查是否有设置分类轴的最大最小值。");
				return false;
			}
			
			if(null == mDataSet)
			{
				Log.e(TAG,"数据轴数据源为空");
				return false;
			}
			
			// 得到分类轴数据集
			List<String> dataSet = categoryAxis.getDataSet();
			if(null == dataSet)
			{
				Log.e(TAG,"分类轴数据集为空.");
				return false;
			}		
			 renderVerticalBar(canvas);
			
			//显示焦点
			renderFocusShape(canvas);
			//响应提示
			renderToolTip(canvas);
			
			return true;
		} catch (Exception e) {
			throw e;
		}
		
	}

}
