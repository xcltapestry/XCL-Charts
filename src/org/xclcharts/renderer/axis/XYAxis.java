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
package org.xclcharts.renderer.axis;

import java.util.List;

import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.IFormatterTextCallBack;

import android.graphics.Canvas;

/**
 * @ClassName XYAxis
 * @Description XY坐标系类，定义了具体的绘制及格式回调函数的处理
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * 
 */

public class XYAxis extends Axis {

	protected DrawHelper mDrawHelper = null;
	// 数据集
	protected List<String> mDataSet = null;

	// 用于格式化标签的回调接口
	private IFormatterTextCallBack mLabelFormatter;

	public XYAxis() {
		super();
		mDrawHelper = new DrawHelper();
	}

	/**
	 * 设置标签的显示格式
	 * @param callBack 回调函数
	 */
	public void setLabelFormatter(IFormatterTextCallBack callBack) {
		this.mLabelFormatter = callBack;
	}

	/**
	 * 竖轴坐标标签，依左，中，右，决定标签横向显示在相对中心点的位置
	 * @param centerX 轴上中点X坐标
	 * @param centerY 轴上中点X坐标
	 * @param text    标签文本
	 */
	protected void renderHorizontalTick(Canvas canvas, float centerX, float centerY,
			String text) {
		if (false == getVisible())
			return;

		float marksStartX = centerX;
		float markeStopX = centerX;

		float lablesStartX = centerX;
		float lablesStartY = centerY;

		switch (getHorizontalTickAlign()) {
		case LEFT: {
			if (getTickMarksVisible()) {
				marksStartX = Math.round(centerX - getTickMarksLength());
				markeStopX = centerX;				
			}
			
			if(this.getTickLabelVisible())
				lablesStartX = marksStartX - getTickLabelMargin();
			
			break;
		}
		case CENTER: {
			if (getTickMarksVisible()) {
				marksStartX = Math.round(centerX - getTickMarksLength() / 2);
				markeStopX = Math.round(centerX + getTickMarksLength() / 2);
			}
			break;
		}
		case RIGHT:
			if (getTickMarksVisible()) {
				marksStartX = centerX;
				markeStopX = Math.round(centerX + getTickMarksLength());				
			}
			if(this.getTickLabelVisible())
				lablesStartX = markeStopX + getTickLabelMargin();
			
			break;
		default:
			break;
		}

		//横轴竖线
		if (getTickMarksVisible()) {
			canvas.drawLine(marksStartX, centerY, markeStopX
					+ this.getAxisPaint().getStrokeWidth() / 2, centerY,
					getTickMarksPaint());

		}

		//标签
		if (getTickLabelVisible()) {

			// 定制化显示格式
			String itemLabel = "";
			try {
				itemLabel = mLabelFormatter.textFormatter(text);
			} catch (Exception ex) {
				itemLabel = text;
			}
			
			float textHeight = this.mDrawHelper.getPaintFontHeight(getAxisTickLabelPaint());
			textHeight /=4;
			
			mDrawHelper.drawRotateText(itemLabel, lablesStartX, lablesStartY + textHeight,
					getTickLabelRotateAgent(), canvas,
					getAxisTickLabelPaint());
		}
	}

	/**
	 * 横轴坐标标签，决定标签显示在相对中心点的上方，中间还是底部位置
	 * @param centerX	轴上中点X坐标
	 * @param centerY	轴上中点Y坐标
	 * @param text		标签文本
	 */
	protected void renderVerticalTick(Canvas canvas, float centerX, float centerY, String text) {
		if (false == getVisible())
			return;

		float marksStartY = centerY;
		float marksStopY = centerY;
		float labelsStartY = centerY;

		switch (getVerticalTickPosition()) {
		case UP: {
			if (getTickMarksVisible()) {
				marksStartY = Math.round(centerY - getTickMarksLength());
				marksStopY = centerY;				
			}
			
			if(this.getTickLabelVisible())
			{
				labelsStartY = marksStartY
						- getTickLabelMargin()
						- mDrawHelper
								.getPaintFontHeight(getAxisTickLabelPaint());
			}
			
			break;
		}
		case CENTER: {
			if (getTickMarksVisible()) {
				marksStartY = Math.round(centerY - getTickMarksLength() / 2);
				marksStopY = Math.round(centerY + getTickMarksLength() / 2);
			}
			break;
		}
		case LOWER:

			if (getTickMarksVisible()) {
				marksStartY = centerY;
				marksStopY = Math.round(centerY + getTickMarksLength());				
			}
			
			if(this.getTickLabelVisible())
			{
				labelsStartY = marksStopY
						+ getTickLabelMargin()
						+ mDrawHelper
								.getPaintFontHeight(getAxisTickLabelPaint())
						/ 3;
			}
			break;
		default:
			break;
		}

		
		if (getTickMarksVisible()) {
			canvas.drawLine(centerX, marksStartY
					- this.getAxisPaint().getStrokeWidth() / 2, centerX,
					marksStopY, getTickMarksPaint());
		}
		
		
		if (getTickLabelVisible()) {

			// 定制化显示格式
			String itemLabel = "";
			try {
				itemLabel = mLabelFormatter.textFormatter(text);
			} catch (Exception ex) {
				itemLabel = text;
			}

			mDrawHelper.drawRotateText(itemLabel, centerX, labelsStartY,
					getTickLabelRotateAgent(), canvas,
					getAxisTickLabelPaint());
		}
		
		
	}

}
