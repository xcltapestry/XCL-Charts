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

package org.xclcharts.renderer;

/**
 * @ClassName XChart
 * @Description 所有图表类的基类,定义了图表区，标题，背景等
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * MODIFIED    YYYY-MM-DD   REASON
 */

import org.xclcharts.chart.common.DrawHelper;
import org.xclcharts.renderer.plot.PlotArea;
import org.xclcharts.renderer.plot.PlotAreaRender;
import org.xclcharts.renderer.plot.PlotGrid;
import org.xclcharts.renderer.plot.PlotGridRender;
import org.xclcharts.renderer.plot.PlotTitle;
import org.xclcharts.renderer.plot.PlotTitleRender;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class XChart implements IRender {

	// 画布
	protected Canvas mCanvas = null;
	// 开放主图表区
	protected PlotAreaRender plotArea = null;
	// 开放主图表区网格
	protected PlotGridRender plotGrid = null;
	// 标题栏
	private PlotTitleRender plotTitle = null;
	// 图大小范围
	private float mChartLeft = 0.0f;
	private float mChartTop = 0.0f;
	private float mChartRight = 0.0f;
	private float mChartBottom = 5f;
	// 图宽高
	private float mChartWidth = 0.0f;
	private float mChartHeight = 0.0f;

	// 图的内边距属性
	private float mPaddingPercentTop = 0f;
	private float mPaddingPercentBottom = 0f;
	private float mPaddingPercentLeft = 0f;
	private float mPaddingPercentRight = 0f;
	// 图表背景色
	private Paint mChartBackgroundPaint = null;
	// 是否画背景色
	private boolean mBackgroupColorVisible = false;

	public XChart() {
		initChart();
	}

	private void initChart() {
		// 图表
		plotArea = new PlotAreaRender();
		plotGrid = new PlotGridRender();
		plotTitle = new PlotTitleRender();
		plotTitle.setChartTitlePosition(XEnum.Postion.CENTER);
		plotTitle.setChartTitleAlign(XEnum.ChartTitleAlign.CENTER);

		initPaint();
	}

	private void initPaint() {
		// 背景画笔
		mChartBackgroundPaint = new Paint();
		mChartBackgroundPaint.setStyle(Style.FILL);
		mChartBackgroundPaint.setColor(Color.WHITE);
	}

	// 图的内边距属性
	/**
	 * 设置内边距百分比,即绘图区与图边距相隔距离的百分比
	 * 
	 * @param top
	 *            顶
	 * @param bottom
	 *            底
	 * @param left
	 *            左边
	 * @param right
	 *            右边
	 */
	public void setPadding(float top, float bottom, float left, float right) {
		if (top > 0)
			mPaddingPercentTop = top;
		if (bottom > 0)
			mPaddingPercentBottom = bottom;
		if (left > 0)
			mPaddingPercentLeft = left;
		if (right > 0)
			mPaddingPercentRight = right;
	}

	/**
	 * 返回主图表区基类
	 * 
	 * @return 主图表区基类
	 */
	public PlotArea getPlotArea() {
		return plotArea;
	}

	/**
	 * 返回主图表区网格基类
	 * 
	 * @return 网格基类
	 */
	public PlotGrid getPlotGrid() {
		return plotGrid;
	}

	/**
	 * 返回图的标题基类
	 * 
	 * @return 标题基类
	 */
	public PlotTitle getPlotTitle() {
		return plotTitle;
	}

	/**
	 * 设置图表绘制范围
	 * 
	 * @param startX
	 *            图表起点X坐标
	 * @param startY
	 *            图表起点Y坐标
	 * @param width
	 *            图表宽度
	 * @param height
	 *            图表高度
	 */
	public void setChartRange(float startX, float startY, float width,
			float height) {
		if (startX > 0)
			mChartLeft = startX;
		if (startY > 0)
			mChartTop = startY;
		mChartRight = startX + width;
		mChartBottom = startY + height;

		if (width > 0)
			mChartWidth = width;
		if (height > 0)
			mChartHeight = height;
	}

	/**
	 * 设置图表绘制范围
	 * 
	 * @param left
	 *            图表左上X坐标
	 * @param top
	 *            图表左上Y坐标
	 * @param right
	 *            图表右下X坐标
	 * @param bottom
	 *            图表右上Y坐标
	 */
	public void setChartRect(float left, float top, float right, float bottom) {

		if (left > 0)
			mChartLeft = left;
		if (top > 0)
			mChartTop = top;
		if (right > 0)
			mChartRight = right;
		if (bottom > 0)
			mChartBottom = bottom;
		mChartWidth = right - left;
		mChartHeight = bottom - top;
	}

	/**
	 * 是否为竖屏显示
	 * 
	 * @return 是否为竖屏
	 */
	public boolean isVerticalScreen() {
		if (mChartWidth < mChartHeight) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 开放背景画笔
	 * 
	 * @return 画笔
	 */
	public Paint getChartBackgroundPaint() {
		return mChartBackgroundPaint;
	}

	/**
	 * 设置标题
	 * 
	 * @param title 标题
	 */
	public void setChartTitle(String title) {
		plotTitle.setChartTitle(title);
	}

	/**
	 * 设置子标题
	 * 
	 * @param subtitle 子标题
	 */
	public void setChartSubTitle(String subtitle) {
		plotTitle.setChartSubTitle(subtitle);
	}

	/**
	 * 设置标题上下显示位置,即图上边距与绘图区间哪个位置(靠上，居中，靠下).
	 */
	public void setChartTitlePosition(XEnum.Postion postion) {
		plotTitle.setChartTitlePosition(postion);
	}

	/**
	 * 设置标题横向显示位置(靠左，居中，靠右)
	 * 
	 * @param align 显示位置
	 */
	public void setChartTitleAlign(XEnum.ChartTitleAlign align) {
		plotTitle.setChartTitleAlign(align);
	}

	/**
	 * 返回图表左边X坐标
	 * 
	 * @return 左边X坐标
	 */
	public float getChartLeft() {
		return mChartLeft;
	}

	/**
	 * 返回图表上方Y坐标
	 * 
	 * @return 上方Y坐标
	 */
	public float getChartTop() {
		return mChartTop;
	}

	/**
	 * 返回图表右边X坐标
	 * 
	 * @return 右边X坐标
	 */
	public float getChartRight() {
		return mChartRight;
	}

	/**
	 * 返回图表底部Y坐标
	 * 
	 * @return 底部Y坐标
	 */
	public float getChartBottom() {
		return mChartBottom;
	}

	/**
	 * 返回图表宽度
	 * 
	 * @return 宽度
	 */
	public float getChartWidth() {
		return mChartWidth;
	}

	/**
	 * 返回图表高度
	 * 
	 * @return 高度
	 */
	public float getChartHeight() {
		return mChartHeight;
	}

	/**
	 * 图绘制区相对图底部边距的缩进比例
	 * 
	 * @return 缩进比例
	 */
	public float getPaddingPercentBottom() {
		return mPaddingPercentBottom;
	}

	/**
	 * 图绘制区相对图左边边距的缩进比例
	 * 
	 * @return 缩进比例
	 */
	public float getPaddingPercentLeft() {
		return mPaddingPercentLeft;
	}

	/**
	 * 图绘制区相对图右边边距的缩进比例
	 * 
	 * @return 缩进比例
	 */
	public float getPaddingPercentRight() {
		return mPaddingPercentRight;
	}

	/**
	 * 设置是否绘制背景
	 * 
	 * @param visible 是否绘制背景
	 */
	public void setBackgroupColorVisible(boolean visible) {
		mBackgroupColorVisible = visible;
	}

	/**
	 * 设置图的背景色
	 * 
	 * @param visible 是否绘制背景
	 * @param color   背景色
	 */
	public void setBackgroupColor(boolean visible, int color) {
		mBackgroupColorVisible = visible;
		getChartBackgroundPaint().setColor(color);
		getPlotArea().getBackgroundPaint().setColor(color);
	}

	/**
	 * 绘制图的背景
	 */
	protected void drawChartBackgroup() {
		if (mBackgroupColorVisible)
			this.mCanvas.drawRect(mChartLeft, mChartTop, mChartRight,
					mChartBottom, mChartBackgroundPaint);
	}

	/**
	 * 计算图的显示范围
	 */
	protected void calcPlotRange() {
		DrawHelper dw = new DrawHelper();

		// 图的内边距属性，默认按竖屏算
		float perLeft = mPaddingPercentLeft;
		float perRight = mPaddingPercentRight;
		float perTop = mPaddingPercentTop;
		float perBottom = mPaddingPercentBottom;

		// 要依长宽比，区分横竖屏间的比例
		if (mChartWidth > this.mChartHeight) // 当前状态为横屏
		{
			float scrPer = mChartHeight / mChartWidth;
			perTop += scrPer;
			perBottom += scrPer;
			perLeft -= scrPer;
			perRight -= scrPer;
		}
		plotArea.setPlotBottom(this.mChartBottom
				- Math.round(this.mChartHeight / 100 * perBottom));
		plotArea.setPlotLeft(this.mChartLeft
				+ Math.round(this.mChartWidth / 100 * perLeft));
		plotArea.setPlotRight(this.mChartRight
				- Math.round(this.mChartWidth / 100 * perRight));

		float rederTop = 0.0f;
		float titleHeight = 0.0f;
		float subtitleHeight = 0.0f;
		// float titlePercentage = 0.0f;
		if (plotTitle.getChartTitle().length() > 0) {
			titleHeight = dw.getPaintFontHeight(plotTitle.getChartTitlePaint());
		}
		if (plotTitle.getChartSubTitle().length() > 0) {
			subtitleHeight = dw.getPaintFontHeight(plotTitle
					.getChartTitlePaint());
		}
		rederTop = Math.round(this.mChartHeight / 100 * perTop);

		if (rederTop < titleHeight + subtitleHeight) {
			rederTop = titleHeight + subtitleHeight;
		}
		plotArea.setPlotTop(this.mChartTop + rederTop);

	}

	// 导出成文件,待实现
	// public void exportAsBmpfile(String fileName)
	// {

	// }

	/**
	 * 绘制标题
	 */
	protected void renderTitle() {
		this.plotTitle.renderTitle(mChartLeft, mChartRight, mChartTop,
				mChartWidth, this.plotArea.getPlotTop(), this.mCanvas);
	}

	/**
	 * 返回当前画布
	 * 
	 * @return 画布
	 */
	public Canvas getCanvas() {
		return this.mCanvas;
	}

	@Override
	public void setCanvas(Canvas canvas) {
		// TODO Auto-generated method stub
		mCanvas = canvas;
	}

	@Override
	public boolean render() throws Exception {
		// TODO Auto-generated method stubcalcPlotRange

		try {
			// 检查画笔
			if (null == mCanvas)
				return false;
			// 绘制图背景
			drawChartBackgroup();
			// 设置图表区画布
			plotArea.setCanvas(mCanvas);
			// 设置图表区网格画布
			plotGrid.setCanvas(mCanvas);
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

}
