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

import java.util.List;

import android.graphics.Canvas;
import org.xclcharts.chart.LnData;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.renderer.line.PlotDot;
import org.xclcharts.renderer.line.PlotLine;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;

/**
 * @ClassName XChart
 * @Description 所有线类，如折线，曲线等图表类的基类,在此主要用于Key及坐标系的绘制。
 * 
 * @author XiongChuanLiang<br/>
 *         (xcl_168@aliyun.com) 
 *         * MODIFIED YYYY-MM-DD REASON
 */

public class LnChart extends AxisChart {

	// 格式化柱形上的标签
	private IFormatterDoubleCallBack mItemLabelFormatter;
	// 绘制Key的画笔
	private Paint mPaintKey = null;

	// 是否显示顶轴
	private boolean mTopAxisVisible = true;
	// 是否显示底轴
	private boolean mRightAxisVisible = true;
	
	

	public LnChart() {
		super();
		initChart();
	}

	private void initChart() {
		mPaintKey = new Paint();
		mPaintKey.setTextSize(18);
		mPaintKey.setStyle(Style.FILL);
		mPaintKey.setAntiAlias(true);
		
		setPlotKeyVisible(true);
	}

	/**
	 * 开放Key绘制画笔
	 * 
	 * @return 画笔
	 */
	public Paint getKeyPaint() {
		return mPaintKey;
	}

	/**
	 * 竖向柱形图 Y轴的屏幕高度/数据轴的刻度标记总数 = 步长
	 * 
	 * @return Y轴步长
	 */
	private float getVerticalYSteps(double tickCount) {
		// float YSteps = (float) Math.r(getAxisScreenHeight()/ tickCount );

		float YSteps = (float) (getAxisScreenHeight() / tickCount);
		return YSteps;
	}

	/**
	 * 竖向柱形图 得到X轴的步长 X轴的屏幕宽度 / 刻度标记总数 = 步长
	 * 
	 * @param num
	 *            刻度标记总数
	 * @return X轴步长
	 */
	protected float getVerticalXSteps(int num) {
		float XSteps = (float) Math.ceil(getAxisScreenWidth() / num);
		return XSteps;
	}

	/**
	 * 是否显示顶轴
	 * 
	 * @param visible
	 */
	public void setTopAxisVisible(boolean visible) {
		mTopAxisVisible = visible;
	}

	/**
	 * 是否显示底轴
	 * 
	 * @param visible
	 */
	public void setRightAxisVisible(boolean visible) {
		mRightAxisVisible = visible;
	}

	//
	/**
	 * 绘制左边竖轴,Lines图，坐标轴都是封闭的
	 */
	protected void renderVerticalDataAxis(Canvas canvas) {
		// 数据轴数据刻度总个数
		double tickCount = dataAxis.getAixTickCount();
		// 数据轴高度步长
		float YSteps = getVerticalYSteps(tickCount);

		float plotLeft = plotArea.getPlotLeft();
		float plotTop = plotArea.getPlotTop();
		float plotRight = plotArea.getPlotRight();
		float plotBottom = plotArea.getPlotBottom();
		float currentY = plotBottom;
		float currentTickLabel = 0.0f;

		float maskHeight = dataAxis.getAxisTickMarksPaint().getStrokeWidth() / 2;

		// 数据轴(Y 轴)
		for (int i = 0; i <= tickCount; i++) {
			// if (i == 0)
			// continue;
			// 依起始数据坐标与数据刻度间距算出上移高度
			// currentY = (float) Math.rint(plotBottom - i * YSteps);
			currentY = (float) (plotBottom - i * YSteps);
			// 标签
			currentTickLabel = (float) (dataAxis.getAxisMin() + (i * dataAxis
					.getAxisSteps()));

			if (i > 0) {
				// 从左到右的横向网格线
				if (i % 2 != 0) {
					plotGrid.renderOddRowsFill(canvas,plotLeft, currentY + YSteps,
							plotRight, currentY);
				} else {
					plotGrid.renderEvenRowsFill(canvas,plotLeft, currentY + YSteps,
							plotRight, currentY);
				}

				if (i > 0 && i < tickCount)
					plotGrid.renderGridLinesHorizontal(canvas,plotLeft, currentY,
							plotRight, currentY);
			}
			dataAxis.renderAxisHorizontalTick(canvas,plotLeft, currentY,
					Float.toString(currentTickLabel));

		}

		// top X轴线
		if (mTopAxisVisible)
			dataAxis.renderAxis(canvas,plotLeft, plotTop, plotRight, plotTop);

		// 左Y轴 线
		dataAxis.renderAxis(canvas,plotLeft, plotBottom, plotLeft, plotTop);
	}

	// 坐标轴是封闭的
	/**
	 * 绘制右边数据轴
	 */
	protected void renderVerticalDataAxisRight(Canvas canvas) {
		// 数据轴数据刻度总个数
		double tickCount = dataAxis.getAixTickCount();
		// 数据轴高度步长
		float YSteps = getVerticalYSteps(tickCount);
		float currentY = plotArea.getPlotBottom();

		float maskHeight = dataAxis.getAxisTickMarksPaint().getStrokeWidth() / 2;

		// 数据轴(Y 轴)
		for (int i = 0; i <= tickCount; i++) {
			if (i == 0)
				continue;
			currentY = Math.round(plotArea.getPlotBottom() - i * YSteps);
			// 标签
			float currentTickLabel = (float) (dataAxis.getAxisMin() + (i * dataAxis
					.getAxisSteps()));

			if (i == tickCount) {
				dataAxis.renderAxisHorizontalTick(canvas,plotArea.getPlotRight(),
						plotArea.getPlotTop(), Float.toString(currentTickLabel));
			} else {
				this.dataAxis
						.renderAxisHorizontalTick(canvas,plotArea.getPlotRight(),
								currentY + maskHeight,
								Float.toString(currentTickLabel));
			}
			// 右边轴默认不显示网格,所以在此忽略不作处理
		}
		// 轴 线
		float paintWidth = dataAxis.getAxisPaint().getStrokeWidth() / 2;
		dataAxis.renderAxis(canvas,plotArea.getPlotRight() + paintWidth,
				plotArea.getPlotBottom(), plotArea.getPlotRight() + paintWidth,
				plotArea.getPlotTop());
	}

	/**
	 * 绘制底部标签轴
	 */
	protected void renderVerticalLabelsAxis(Canvas canvas) {
		// 标签轴(X 轴)
		float currentX = plotArea.getPlotLeft();

		// 得到标签轴数据集
		List<String> dataSet = labelsAxis.getDataSet();
		// 与柱形图不同，无须多弄一个
		float XSteps = getVerticalXSteps(dataSet.size() - 1);

		for (int i = 0; i < dataSet.size(); i++) {

			// 依初超始X坐标与标签间距算出当前刻度的X坐标
			currentX = Math.round(plotArea.getPlotLeft() + (i) * XSteps); // (i
																			// +
																			// 1)
																			// *
																			// XSteps);

			// 绘制竖向网格线
			if (plotGrid.isShowVerticalLines()) {
				if (i > 0 && i + 1 < dataSet.size())
					plotGrid.renderGridLinesVertical(canvas,currentX,
							plotArea.getPlotBottom(), currentX,
							plotArea.getPlotTop());
			}

			if (dataSet.size() == i + 1) {
				labelsAxis.renderAxisVerticalTick(canvas,plotArea.getPlotRight(),
						plotArea.getPlotBottom(), dataSet.get(i));
			} else {
				// 画上标签/刻度线
				labelsAxis.renderAxisVerticalTick(canvas,currentX,
						plotArea.getPlotBottom(), dataSet.get(i));
			}

		}
		// 右边轴线
	if (mRightAxisVisible)
		labelsAxis.renderAxis(canvas,plotArea.getPlotRight(),
				plotArea.getPlotBottom(), plotArea.getPlotRight(),
				plotArea.getPlotTop());

		// bottom轴 线		
		labelsAxis.renderAxis(canvas,plotArea.getPlotLeft(),
				plotArea.getPlotBottom(), plotArea.getPlotRight(),
				plotArea.getPlotBottom());
	}

	/**
	 * 设置标签显示格式
	 * 
	 * @param callBack
	 *            回调函数
	 */
	public void setItemLabelFormatter(IFormatterDoubleCallBack callBack) {
		this.mItemLabelFormatter = callBack;
	}

	/**
	 * 返回标签显示格式
	 * 
	 * @param value
	 * @return 显示格式
	 */
	protected String getFormatterItemLabel(double value) {
		String itemLabel = "";
		try {
			itemLabel = mItemLabelFormatter.doubleFormatter(value);
		} catch (Exception ex) {
			itemLabel = Double.toString(value);
			// DecimalFormat df=new DecimalFormat("#0");
			// itemLabel = df.format(value).toString();
		}
		return itemLabel;
	}

	/**
	 * 绘制线上的坐标点
	 * 
	 * @param pDot
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 * @param paint
	 */
	protected void renderDot(Canvas canvas, PlotDot pDot, float left, float top, float right,
			float bottom, Paint paint) {

		float radius = pDot.getDotRadius();
		float halfRadius = radius / 2;

		switch (pDot.getDotStyle()) {
		case CIRCLE:
			canvas.drawCircle(left + Math.abs(right - left), bottom,
					radius, paint);
			break;
		case RING:
			int ringRadius = (int) Math.round(radius * 0.7);
            canvas.drawCircle(left + Math.abs(right - left), bottom,
					radius, paint);

			Paint paintfill = new Paint();
			paintfill.setColor(Color.WHITE);
			paintfill.setStyle(Style.FILL);
            canvas.drawCircle(left + Math.abs(right - left), bottom,
					ringRadius, paintfill);

			break;
		case TRIANGLE: // 等腰三角形
			float triganaleHeight = radius + radius / 2;
			Path path = new Path();
			path.moveTo(right - radius, bottom + halfRadius);
			path.lineTo(right, bottom - triganaleHeight);
			path.lineTo(right + radius, bottom + halfRadius);
			path.close();
            canvas.drawPath(path, paint);
			break;
		// Prismatic
		case PRISMATIC: // 棱形 Prismatic
			Path pathPir = new Path();
			pathPir.moveTo(right - radius, bottom);
			pathPir.lineTo(right, bottom - radius);
			pathPir.lineTo(right + radius, bottom);
			pathPir.lineTo(left + (right - left), bottom + radius);
			pathPir.close();
            canvas.drawPath(pathPir, paint);
			break;
		case RECT:
			paint.setStyle(Style.FILL);
            canvas.drawRect(right - radius, bottom + radius, right + radius,
					bottom - radius, paint);
			break;
		case HIDE:
		default:
		}
	}

	/**
	 * 绘制Key
	 * 
	 * @param dataSet
	 */
	protected void renderKey(Canvas canvas, List<LnData> dataSet) {
		if (getPlotKeyVisible() == false)
			return;

		DrawHelper dw = new DrawHelper();
		float textHeight = dw.getPaintFontHeight(this.mPaintKey);
		float rectWidth = 2 * textHeight;
		float currentX = 0.0f;
		float currentY = 0.0f;

		mPaintKey.setTextAlign(Align.LEFT);
		currentX = plotArea.getPlotLeft();
		currentY = plotArea.getPlotTop() - 5;

		int totalTextWidth = 0;
		for (LnData cData : dataSet) {
			mPaintKey.setColor(cData.getLineColor());

			// 竖屏
			int keyTextWidth = dw.getTextWidth(mPaintKey, cData.getLineKey());
			totalTextWidth += keyTextWidth;

			if (totalTextWidth > plotArea.getPlotWidth()) {
				currentY -= textHeight;
				currentX = plotArea.getPlotLeft();
				totalTextWidth = 0;
			}

            canvas.drawLine(currentX, currentY - textHeight / 2, currentX
					+ rectWidth, currentY - textHeight / 2, mPaintKey);

            canvas.drawText(cData.getLineKey(), currentX + rectWidth, currentY
					- textHeight / 3, mPaintKey);

			float dotLeft = currentX + rectWidth / 4;
			float dotRight = currentX + 2 * (rectWidth / 4);

			PlotLine pLine = cData.getPlotLine();

			if (!pLine.getDotStyle().equals(XEnum.DotStyle.HIDE)) {
				PlotDot pDot = pLine.getPlotDot();
				renderDot(canvas, pDot, dotLeft, currentY, dotRight, currentY
						- textHeight / 2, pLine.getDotPaint()); // 标识图形
			}

			currentX += rectWidth + keyTextWidth + 10;

		}
	}

}
