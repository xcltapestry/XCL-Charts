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

package org.xclcharts.chart;

import java.util.List;

import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.event.click.BarPosition;
import org.xclcharts.renderer.AxesChart;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.bar.Bar;
import org.xclcharts.renderer.bar.FlatBar;
import org.xclcharts.renderer.info.AnchorDataPoint;
import org.xclcharts.renderer.info.PlotAxisTick;
import org.xclcharts.renderer.line.PlotCustomLine;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.Log;

/**
 * @ClassName BarChart
 * @Description  柱形图的基类,包含横向和竖向柱形图
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public class BarChart extends AxesChart {
	
	private static final String TAG = "BarChart";

	// 柱形基类
	private FlatBar mFlatBar = new FlatBar();
	// 数据源
	private List<BarData> mDataSet;
	
	//用于绘制定制线(分界线)
	private PlotCustomLine mCustomLine = null;	
	
	//批注
	private List<AnchorDataPoint> mAnchorSet;
	
	//值与轴最小值相等
	protected boolean mEqualAxisMin  = false;
	
	//标签和对象依哪种风格居中显示
	private XEnum.BarCenterStyle mBarCenterStyle = XEnum.BarCenterStyle.SPACE;
		
		
	public BarChart() {
		
		//默认为竖向设置
		defaultAxisSetting();
	}
	
	@Override
	public XEnum.ChartType getType()
	{
		return XEnum.ChartType.BAR;
	}
	
	/**
	 * 设置柱形居中位置,依刻度线居中或依刻度中间点居中。
	 * @param style 居中风格
	 */
	public void setBarCenterStyle(XEnum.BarCenterStyle style)
	{
		mBarCenterStyle = style;
	}
	
	/**
	 * 返回柱形居中位置,依刻度线居中或依刻度中间点居中。
	 * @return 居中风格
	 */
	public XEnum.BarCenterStyle getBarCenterStyle()
	{
		return mBarCenterStyle;
	}

	/**
	 * 开放柱形绘制类
	 * @return 柱形绘制类
	 */
	public Bar getBar() {
		return mFlatBar;
	}
	
	/**
	 * 当值与轴最小值相等时，不显示柱形及标签
	 */
	public void hideEqualAxisMinBar()
	{
		mEqualAxisMin = false;
	}
	
	/**
	 *  当值与轴最小值相等时，正常显示柱形及标签
	 */
	public void showEqualAxisMinBar()
	{
		mEqualAxisMin = true;
	}	

	/**
	 * 设置定制线值
	 * @param customLineDataSet 定制线数据集合
	 */
	public void setCustomLines( List<CustomLineData> customLineDataSet)
	{
		if(null == mCustomLine)mCustomLine = new PlotCustomLine();
		mCustomLine.setCustomLines(customLineDataSet);
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
	 * 设置批注
	 * @param anchor 批注
	 */
	public void setAnchorDataPoint( List<AnchorDataPoint> anchor) {	
		mAnchorSet = anchor;
	}
	
	/**
	 * 返回批注
	 * @return 批注
	 */
	public List<AnchorDataPoint> getAnchorDataPoint()
	{
		return mAnchorSet;
	}

	/**
	 * 设置数据轴的数据源
	 * 
	 * @param dataSeries
	 *            数据源
	 */
	public void setDataSource( List<BarData> dataSeries) {		
		this.mDataSet = dataSeries;
	}

	/**
	 * 返回数据库的数据源
	 * @return 数据源
	 */
	public List<BarData> getDataSource() {
		return mDataSet;
	}

	/**
	 * 设置图的显示方向,即横向还是竖向显示柱形
	 * @param direction 横向/竖向
	 */
	public void setChartDirection( XEnum.Direction direction) {
		mDirection = direction;
		
		defaultAxisSetting();		
	}
	
	/**
	 * 返回图的显示方向,即横向还是竖向显示柱形
	 * @return 横向/竖向
	 */
	public XEnum.Direction getChartDirection()
	{
		return mDirection;
	}
	
	/**
	 * 图为横向或竖向时，轴和Bar的默认显示风格
	 */
	protected void defaultAxisSetting()
	{				
		if(null == mDirection) return;
		
		categoryAxisDefaultSetting();
		dataAxisDefaultSetting();
			
		if(null != getBar())
		{				
				switch (mDirection) {
					case HORIZONTAL: 	
						getBar().getItemLabelPaint().setTextAlign(Align.LEFT);						
						getBar().setBarDirection(XEnum.Direction.HORIZONTAL);							
						break;				
					case VERTICAL: 
						getBar().setBarDirection(XEnum.Direction.VERTICAL);				
						break;				
				}
		}
	}
	
	
	protected void categoryAxisDefaultSetting()
	{		
		if(null == categoryAxis) return;
		
		switch (mDirection) {
			case HORIZONTAL:					
					categoryAxis.setHorizontalTickAlign(Align.LEFT);		
					categoryAxis.getTickLabelPaint().setTextAlign(Align.RIGHT);
					categoryAxis.setVerticalTickPosition(XEnum.VerticalAlign.MIDDLE);
					
					setCategoryAxisLocation(XEnum.AxisLocation.LEFT);
				break;			
			 case VERTICAL: 					
					categoryAxis.setHorizontalTickAlign(Align.CENTER);			
					categoryAxis.getTickLabelPaint().setTextAlign(Align.CENTER);					
					categoryAxis.setVerticalTickPosition(XEnum.VerticalAlign.BOTTOM);
					
					setCategoryAxisLocation(XEnum.AxisLocation.BOTTOM);
				break;		
		}
	}
	

	protected void dataAxisDefaultSetting()
	{		
		if(null == dataAxis) return;
			
		switch (mDirection) {
			case HORIZONTAL:					
					dataAxis.setHorizontalTickAlign(Align.CENTER);
					dataAxis.getTickLabelPaint().setTextAlign(Align.CENTER);
					dataAxis.setVerticalTickPosition(XEnum.VerticalAlign.BOTTOM);
					
					setDataAxisLocation(XEnum.AxisLocation.BOTTOM);
				break;
			case VERTICAL: 					
					dataAxis.setHorizontalTickAlign(Align.LEFT);
					dataAxis.getTickLabelPaint().setTextAlign(Align.RIGHT);	
					dataAxis.setVerticalTickPosition(XEnum.VerticalAlign.MIDDLE);
					
					setDataAxisLocation(XEnum.AxisLocation.LEFT);										
				break;
		}
	}
	
	
	/**
	 * 比较传入的各个数据集，找出最大数据个数
	 * @return 最大数据个数
	 */
	protected int getDataAxisDetailSetMaxSize() {
		if(mDataSet == null)return 0;
		// 得到最大size个数
		int dsetMaxSize = 0;
		int size = mDataSet.size();
		for (int i = 0; i < size; i++) {
			if (dsetMaxSize < mDataSet.get(i).getDataSet().size())
				dsetMaxSize = mDataSet.get(i).getDataSet().size();
		}
		return dsetMaxSize;
	}

	
	protected int getDataTickCount()
	{
		int tickCount = dataAxis.getAixTickCount();
		return tickCount +1;
	}
	
	protected int getCateTickCount()
	{
		int count = categoryAxis.getDataSet().size() ;
		if( XEnum.BarCenterStyle.SPACE != mBarCenterStyle)
											count +=1;
		return count;
	}
	
	@Override
	protected void drawClipDataAxisGridlines(Canvas canvas) 
	{						
		// 与柱形图不同，无须多弄一个
		float XSteps = 0.0f,YSteps = 0.0f;
		
		// 数据轴数据刻度总个数
		int tickCount = dataAxis.getAixTickCount();
		int labeltickCount = tickCount+1;
				
		if( 0 == tickCount)
		{
			//Log.e(TAG,"数据轴数据源为0!");
			return ;
		}
		//}else if (1 == tickCount)  //label仅一个时右移
		//	    labeltickCount = tickCount - 1 ;
		
					
		// 标签轴(X 轴)		
		float axisX = 0.0f,axisY = 0.0f,currentX = 0.0f,currentY = 0.0f;
		// 标签
		double currentTickLabel = 0d;
		// 轴位置
		XEnum.AxisLocation pos = getDataAxisLocation();
				
		//步长
		switch(pos)
		{			 
			case LEFT: //Y
			case RIGHT:		
			case VERTICAL_CENTER:
				YSteps = getVerticalYSteps(tickCount); 	
				
				currentX = axisX = getAxisXPos(pos);
				currentY = axisY = plotArea.getBottom();
				break;						
			case TOP: //X
			case BOTTOM:
			case HORIZONTAL_CENTER:
				XSteps = getVerticalXSteps(tickCount);	
				
				currentY = axisY =  getAxisYPos(pos);
				currentX = axisX = plotArea.getLeft();
				break;			
		}
		
		mLstDataTick.clear();			
		//绘制
		for (int i = 0; i < labeltickCount; i++)
		{					
			switch(pos)
			{				 
				case LEFT: //Y
				case RIGHT:								
				case VERTICAL_CENTER:	
					// 依起始数据坐标与数据刻度间距算出上移高度
					currentY = sub(plotArea.getBottom(), mul(i,YSteps));
					
					// 从左到右的横向网格线
					drawHorizontalGridLines(canvas,plotArea.getLeft(),plotArea.getRight(),
																i,tickCount,YSteps,currentY);
																			
					//if(!dataAxis.isShowAxisLabels())continue;
					 
					// 标签					
					currentTickLabel = MathHelper.getInstance().add(
										dataAxis.getAxisMin(),i * dataAxis.getAxisSteps());	
															
					//sub(axisX ,get3DOffsetX())
					mLstDataTick.add(new PlotAxisTick(i, axisX, 
												currentY, Double.toString(currentTickLabel)));										
					break;							
				case TOP: //X
				case BOTTOM:	
				case HORIZONTAL_CENTER:	
					//bar
					// 依起始数据坐标与数据刻度间距算出上移高度
					currentX = add(axisX , mul(i , XSteps));
																	
					 //绘制竖向网格线
					 drawVerticalGridLines(canvas,plotArea.getTop(),plotArea.getBottom(),
													i ,tickCount,XSteps,currentX);
				
					// if(!dataAxis.isShowAxisLabels())continue;
					 
					// 画上标签/刻度线	
					currentTickLabel = MathHelper.getInstance().add(
											dataAxis.getAxisMin(),i * dataAxis.getAxisSteps());	
					
					mLstDataTick.add(new PlotAxisTick(i,currentX, axisY, 
														Double.toString(currentTickLabel)));
														
					break;	
			} //switch end						
		} //end for	
	}
	
	
	/**
	 * 绘制底部标签轴
	 */
	@Override
	protected void drawClipCategoryAxisGridlines(Canvas canvas) 
	{				
		// 得到标签轴数据集
		List<String> dataSet = categoryAxis.getDataSet();
		// 与柱形图不同，无须多弄一个
		float XSteps = 0.0f,YSteps = 0.0f;		
	
		int tickCount = dataSet.size() ;
		int labeltickCount = getCateTickCount();
			
		if( 0 == tickCount)
		{
			//Log.e(TAG,"分类轴数据源为0!");
			return ;
		}
			
		// 标签轴(X 轴)
		float axisX = 0.0f,axisY = 0.0f,currentX = 0.0f,currentY = 0.0f;
		
		XEnum.AxisLocation pos = getCategoryAxisLocation();
								
		if( XEnum.AxisLocation.LEFT == pos || 
				XEnum.AxisLocation.RIGHT == pos|| 
				XEnum.AxisLocation.VERTICAL_CENTER == pos)
		{		
			//line
			YSteps = getVerticalYSteps( labeltickCount) ;
			currentX = axisX = getAxisXPos(pos);
			currentY = axisY = plotArea.getBottom();										
		}else{ //TOP BOTTOM																	
			// 依传入的分类个数与轴总宽度算出要画的分类间距数是多少
			// 总宽度 / 分类个数 = 间距长度    //getAxisScreenWidth() 			
			XSteps = getVerticalXSteps(labeltickCount);
			currentY = axisY = getAxisYPos(pos);	
			currentX = axisX = plotArea.getLeft();
		}
					
		mLstCateTick.clear();	
		
		float labelX,labelY;
		boolean showTicks = true;
		
		//绘制
		for (int i = 0; i < tickCount; i++)  //tickCount
		{			
			switch(pos)
			{				 
				case LEFT: //Y
				case RIGHT:			
				case VERTICAL_CENTER:								
					// 依初超始Y坐标与分类间距算出当前刻度的Y坐标
					currentY = sub(axisY, mul((i + 1) , YSteps));		
					
					
					// 从左到右的横向网格线
					drawHorizontalGridLines(canvas,plotArea.getLeft(),plotArea.getRight(),
																i,tickCount,YSteps,currentY);
					
					if(!categoryAxis.isShowAxisLabels()) continue;	
					
					labelY = currentY;
					labelX = currentX = sub(axisX ,get3DOffsetX());
					if(XEnum.BarCenterStyle.SPACE == mBarCenterStyle)
					{						
						//if(i == tickCount - 1)continue;
						 if(i == tickCount - 1)
						 {
							 showTicks = false;
						 }
						 
						labelY = add(currentY,div(YSteps,2));
					}
																																	 
					// 分类
					mLstCateTick.add(new PlotAxisTick(currentX,currentY,categoryAxis.getDataSet().get(i) ,labelX,labelY,showTicks));					
					break;							
				case TOP: //X
				case BOTTOM:			
				case HORIZONTAL_CENTER:	
					 // 依初超始X坐标与分类间距算出当前刻度的X坐标
					 currentX = add(plotArea.getLeft(),mul((i + 1) , XSteps)); 					 					
															
					 //绘制竖向网格线
					 drawVerticalGridLines(canvas,plotArea.getTop(),plotArea.getBottom(),
													i ,tickCount,XSteps,currentX);
				
					 if(!categoryAxis.isShowAxisLabels()) continue;	
					 	
					 float currentY2 = add(axisY,get3DBaseOffsetY());
					  currentX =  sub(currentX, get3DBaseOffsetX() );	
					  					  
					 labelX = currentX;
					 labelY = currentY2;
					 if(XEnum.BarCenterStyle.SPACE == mBarCenterStyle) 
					 {
						 if(i == tickCount - 1)
						 {
							 showTicks = false;
						 }
						 labelX = sub(currentX,div(XSteps,2));
					 }				
					 mLstCateTick.add(new PlotAxisTick( currentX,currentY2, dataSet.get(i),labelX,labelY,showTicks));	
																
					break;			
			} //switch end
		
		} //end for
	
	}
	
	
	protected float get3DOffsetX()
	{
		return 0.0f;
	}
	
	
	protected float get3DBaseOffsetX()// 分类		
	{						
		return 0.0f;
	}	
	
	protected float get3DBaseOffsetY()// 分类		
	{	
		return 0.0f;
	}


	/**
	 * 绘制横向柱形图
	 * @throws InterruptedException 例外
	 */
	protected boolean renderHorizontalBar(Canvas canvas) {
		
		if(null == mDataSet) return false;				

		// 得到Y 轴分类横向间距高度
		float YSteps = getVerticalYSteps(getCateTickCount()); 
		float barInitX = plotArea.getLeft() ;
		float barInitY = plotArea.getBottom() ;
			
		// 依柱形宽度，多柱形间的偏移值 与当前数据集的总数据个数得到当前分类柱形要占的高度
		int barNumber = getDatasetSize(mDataSet); 
		int currNumber = 0;
		
		float[] ret = mFlatBar.getBarHeightAndMargin(YSteps, barNumber);
		if(null == ret||ret.length != 2)
		{
			Log.e(TAG,"分隔间距计算失败.");
			return false;
		}
		float barHeight = ret[0];
		float barInnerMargin = ret[1];
		float labelBarUseHeight = add(mul(barNumber , barHeight) ,
									  mul(sub(barNumber , 1) , barInnerMargin));		
	
		Double bv = 0d;		
		float dataAxisStd = getHPDataAxisStdX();		
		float itemLabelWidth = 0.f;										
		float barLeft = 0.0f,barBottom = 0.0f,barTop = 0.f,barRight =0.f;	
		float labelLeftX,labelLeftY,currLabelY,drawBarButtomY,rightX;
		
		for (int i = 0; i < barNumber; i++) {
			// 得到分类对应的值数据集
			BarData bd = mDataSet.get(i);
			List<Double> barValues = bd.getDataSet();
			if(null == barValues) continue ;
			
			List<Integer> barDataColor = bd.getDataColor();
			// 设置成对应的颜色
			mFlatBar.getBarPaint().setColor(bd.getColor());		
																		
			// 画同分类下的所有柱形
			int vSize = barValues.size();
			for (int j = 0; j < vSize; j++) {
				bv = barValues.get(j);							
				setBarDataColor(mFlatBar.getBarPaint(),barDataColor,j,bd.getColor());
											
				currLabelY = sub(barInitY , mul((j + 1) , YSteps));		
				
				if(XEnum.BarCenterStyle.SPACE == mBarCenterStyle)
				{										
					drawBarButtomY = add(add(currLabelY,div(YSteps,2)) ,labelBarUseHeight / 2);		
				}else{
					drawBarButtomY = add(currLabelY,labelBarUseHeight / 2);										
				}
				drawBarButtomY = sub(drawBarButtomY, add(barHeight,barInnerMargin) * currNumber);	
							
			
				labelLeftX = rightX = getHPValPosition(bv);
				
				String label = getFormatterItemLabel(bv);				
				if(mFlatBar.getItemLabelsVisible())
					itemLabelWidth = DrawHelper.getInstance().getTextWidth(
														mFlatBar.getItemLabelPaint(), label);
				
				if(dataAxis.getAxisStdStatus())
				{	
					if(bv < dataAxis.getAxisStd()) //反向
					{						 											
						 barLeft = rightX ;
						 barTop = sub(drawBarButtomY,barHeight);						
						 barRight = dataAxisStd;
						 barBottom = drawBarButtomY ;		
						 						 
						 labelLeftX = rightX - itemLabelWidth;
					}else{						
						 barLeft = dataAxisStd;
						 barTop = sub(drawBarButtomY,barHeight);						
						 barRight = rightX;
						 barBottom = drawBarButtomY ;
					}
				}else{							
					 barLeft = barInitX;
					 barTop = sub(drawBarButtomY,barHeight);						
					 barRight = rightX;
					 barBottom = drawBarButtomY ;
				}	
				
				
				// 画出柱形
				mFlatBar.renderBar(barLeft,barBottom,barRight,barTop,canvas);
				
				//保存位置
				saveBarRectFRecord(i,j,barLeft + mMoveX,barTop  + mMoveY,
										barRight + mMoveX, barBottom + mMoveY);
			
				labelLeftY = sub(barBottom , barHeight / 2);
				
				//在柱形的顶端显示上柱形的批注形状
				drawAnchor(this.mAnchorSet,i,j,canvas,labelLeftX,labelLeftY);				
				
				// 柱形顶端标识
				if(!mEqualAxisMin && Double.compare(dataAxis.getAxisMin(), bv)  == 0)
				{					
				}else{
					mFlatBar.renderBarItemLabel(label,labelLeftX, labelLeftY,canvas);
				}
				
				//显示焦点框
				drawFocusRect(canvas,i,j,barLeft,barTop,barRight ,barBottom);
			}
			currNumber++;
		}

		//画横向柱形图，竖向的定制线
		if(null != mCustomLine)
		{
			mCustomLine.setHorizontalPlot(dataAxis, plotArea, this.getAxisScreenWidth());
			mCustomLine.renderHorizontalCustomlinesDataAxis(canvas);
		}
		return true;
	}		
	
	/**
	 * 返回指定数据在图中的坐标位置
	 * @param bv 数据
	 * @return 坐标位置
	 */
	public float getHPValPosition(double bv)
	{							
		double vaxlen = MathHelper.getInstance().sub(bv, dataAxis.getAxisMin());				
		float valuePostion = mul(getPlotScreenWidth(), div((float) (vaxlen) ,dataAxis.getAxisRange()) );		
		return (add(plotArea.getLeft() , valuePostion));						
	}
	
	private float getHPDataAxisStdX()
	{
		if(dataAxis.getAxisStdStatus())
		{		
			return getHPValPosition(dataAxis.getAxisStd());
		}else{
			return plotArea.getLeft();
		}
	}
		
	private float getVPDataAxisStdY()
	{
		if(dataAxis.getAxisStdStatus())
		{
			return getVPValPosition(dataAxis.getAxisStd());
		}else{
			return plotArea.getBottom();
		}
	}
	
	/**
	 * 返回指定数据在图中的坐标位置
	 * @param bv 数据
	 * @return 坐标位置
	 */
	private float getVPValPosition(double bv)
	{
		float vaxlen = (float) MathHelper.getInstance().sub(bv, dataAxis.getAxisMin());				
		float valuePostion = mul(getPlotScreenHeight(), div( vaxlen,dataAxis.getAxisRange() ) );
		return (sub(plotArea.getBottom() , valuePostion));
	}

	@Override
	protected float getAxisXPos(XEnum.AxisLocation location)
	{		
		if(XEnum.Direction.HORIZONTAL == mDirection &&
				dataAxis.getAxisStdStatus() && categoryAxis.getAxisBuildStdStatus())
		{
			return getHPDataAxisStdX();
		}else{
			return super.getAxisXPos(location);
		}	
	}
	
	@Override
	protected float getAxisYPos(XEnum.AxisLocation location)
	{						 		
		if(XEnum.Direction.VERTICAL == mDirection &&
				dataAxis.getAxisStdStatus() && categoryAxis.getAxisBuildStdStatus())
		{
			return getVPDataAxisStdY();
		}else{
			return super.getAxisYPos(location);
		}		
	}		
	
	@Override
	protected void drawClipCategoryAxisLine(Canvas canvas)
	{
		if(XEnum.Direction.VERTICAL == mDirection &&
				dataAxis.getAxisStdStatus() && categoryAxis.getAxisBuildStdStatus())
		{
		   float y =  getVPDataAxisStdY();			
			categoryAxis.renderAxis(canvas,plotArea.getLeft(), y, plotArea.getRight(), y); 	
		}else if(XEnum.Direction.HORIZONTAL == mDirection &&
				dataAxis.getAxisStdStatus() && categoryAxis.getAxisBuildStdStatus()){		
			float x = getHPDataAxisStdX();
			categoryAxis.renderAxis(canvas,x, plotArea.getTop(), x, plotArea.getBottom()); 			
		}else{
			super.drawClipCategoryAxisLine(canvas);
		}
	}
	
	/**
	 * 绘制竖向柱形图
	 */
	protected boolean renderVerticalBar(Canvas canvas) {
		
		if(null == mDataSet) return false;	
		// 得到分类轴数据集
		List<String> dataSet = categoryAxis.getDataSet();
		if(null == dataSet) return false;	
				
		float XSteps = getVerticalXSteps(getCateTickCount());	
		float dataAxisStd = getVPDataAxisStdY();		
		float itemFontHeight = 0.f;		
		if(mFlatBar.getItemLabelsVisible())
			itemFontHeight = DrawHelper.getInstance().getPaintFontHeight(
												mFlatBar.getItemLabelPaint());
					
		int barNumber = getDatasetSize(mDataSet); 
		int currNumber = 0;
		float[] ret = mFlatBar.getBarWidthAndMargin(XSteps, barNumber);
		if(null == ret||ret.length != 2)
		{
			Log.e(TAG,"分隔间距计算失败.");
			return false;
		}
		float barWidth = ret[0];
		float barInnerMargin = ret[1];
		float labelBarUseWidth = add(mul(barNumber , barWidth) , 
									 mul(sub(barNumber , 1) , barInnerMargin));
		
		float barLeft = 0.0f,barBottom = 0.0f,barTop = 0.f,barRight =0.f;
		float currLabelX,drawBarStartX,topY,labelTopX,labelTopY;

		
		// X 轴 即分类轴
		int size = mDataSet.size();
		for (int i = 0; i < size; i++) {
			// 得到分类对应的值数据集
			BarData bd = mDataSet.get(i);	
			
			List<Double> barValues = bd.getDataSet();
			if(null == barValues) continue ;
			
			//用于处理单独针对某些柱子指定颜色的情况
			List<Integer> barDataColor = bd.getDataColor();		
			
			// 设成对应的颜色
			mFlatBar.getBarPaint().setColor(bd.getColor());
												
			// 画出分类对应的所有柱形
			int countChild = barValues.size();
			for (int j = 0; j < countChild; j++) {
				Double bv = barValues.get(j);
					
				setBarDataColor(mFlatBar.getBarPaint(),barDataColor,j,bd.getColor());				
				currLabelX = add(plotArea.getLeft() , mul((j + 1) , XSteps));
												
				 if(XEnum.BarCenterStyle.SPACE == mBarCenterStyle)
				 {
					 drawBarStartX = sub(currLabelX ,div(XSteps,2)); 
					 drawBarStartX = sub(drawBarStartX , labelBarUseWidth / 2);
				 }else{
					 drawBarStartX = sub(currLabelX , labelBarUseWidth / 2);	
				 }

				// 计算同分类多柱 形时，新柱形的起始X坐标
				drawBarStartX = add(drawBarStartX , add(barWidth,barInnerMargin)  * currNumber);											
				labelTopY = topY = getVPValPosition(bv);	
				
				if(dataAxis.getAxisStdStatus())
				{	
					if(bv < dataAxis.getAxisStd()) //反向
					{						 											
						 barLeft = drawBarStartX;
						 barTop = dataAxisStd;						
						 barRight = add(drawBarStartX , barWidth);
						 barBottom = topY ;						 
						 labelTopY = labelTopY + itemFontHeight;
					}else{						
						 barLeft = drawBarStartX;
						 barTop = topY;						
						 barRight = add(drawBarStartX , barWidth);
						 barBottom = dataAxisStd ;
					}
				}else{							
					 barLeft = drawBarStartX;
					 barTop = topY;						
					 barRight = add(drawBarStartX , barWidth);
					 barBottom = plotArea.getBottom() ;
				}				
				// 画出柱形
				mFlatBar.renderBar(barLeft,barBottom,barRight,barTop,canvas);
				
				//保存位置
				saveBarRectFRecord(i,j,barLeft + mMoveX,barTop + mMoveY,
									barRight  + mMoveX,barBottom + mMoveY); 
				
				//显示焦点框
				drawFocusRect(canvas,i,j,barLeft,barTop,barRight ,barBottom);
				
				labelTopX = add(drawBarStartX , barWidth / 2);
				
				//在柱形的顶端显示批注
				drawAnchor(this.mAnchorSet,i,j,canvas,labelTopX,labelTopY);
								
				// 在柱形的顶端显示上柱形当前值
				if(!mEqualAxisMin && Double.compare(dataAxis.getAxisMin(), bv)  == 0)
				{					
				}else{
					mFlatBar.renderBarItemLabel(getFormatterItemLabel(bv),
												labelTopX,labelTopY, canvas);	
				}
			}
			currNumber++;
		}

		//画竖向柱形图的定制线		
		if(null != mCustomLine)
		{
			mCustomLine.setVerticalPlot(dataAxis, plotArea, getAxisScreenHeight());
			mCustomLine.renderVerticalCustomlinesDataAxis(canvas);
		}
		return true;
	}

	@Override
	protected void drawClipPlot(Canvas canvas)
	{		
		switch (mDirection) 
		{
		case HORIZONTAL: 
			renderHorizontalBar(canvas);
			break;				
		case VERTICAL: 
			renderVerticalBar(canvas);
			break;				
		}
	}
			
	@Override
	protected void drawClipLegend(Canvas canvas)
	{
		plotLegend.renderBarKey(canvas, this.mDataSet);	
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
		
	protected int getDatasetSize(List<BarData> dataSource)
	{		
		 if(null == dataSource) return 0;
		 
		 int ret = dataSource.size();
		// X 轴 即分类轴
		for (int i = 0; i < dataSource.size(); i++) 
		{			
			BarData bd = dataSource.get(i);	
			List<Double> barValues = bd.getDataSet();
			
			if(barValues.size() == 1)
			{
				if( Double.compare( barValues.get(0) , dataAxis.getAxisMin()) == 0 )
					ret--;
			}
		}
		return ret;
	}
		
	
	/**
	 * 对于有为单个柱形设置颜色的情况，以这个函数来为画笔设置相应的颜色
	 * @param paint			柱形画笔
	 * @param lstDataColor	数据颜色集
	 * @param currNumber	当前序号
	 * @param defaultColor	默认的柱形颜色
	 */
	protected void setBarDataColor(Paint paint,
								   List<Integer> lstDataColor,
								   int currNumber,								  
								   int defaultColor)
	{		
		if(null != lstDataColor)
		{
			if( lstDataColor.size() > currNumber)
			{
				paint.setColor( lstDataColor.get(currNumber));	
			}else{
				paint.setColor( defaultColor);
			}		
		}		
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
	
	///////////////////////////////////////
}
