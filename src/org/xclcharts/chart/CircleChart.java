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


package org.xclcharts.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.renderer.CirChart;
import org.xclcharts.renderer.XEnum;

import java.util.List;


/**
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *         * MODIFIED    YYYY-MM-DD   REASON
 * @ClassName CircleChart
 * @Description 圆形图基类
 */

public class CircleChart extends CirChart {

    private String mDataInfo = "";
    private XEnum.CircleDisplayType mDisplayType = XEnum.CircleDisplayType.FULL;
    //内环填充颜色
    private Paint mPaintBgCircle = null;
    private Paint mPaintFillCircle = null;
    private Paint mPaintDataInfo = null;

    //数据源
    protected List<PieData> mDataSet;

    public CircleChart() {
        super();
        initChart();
    }

    private void initChart() {
        mPaintBgCircle = new Paint();
        mPaintBgCircle.setColor((int) Color.rgb(148, 159, 181));
        mPaintBgCircle.setAntiAlias(true);

        mPaintFillCircle = new Paint();
        mPaintFillCircle.setColor((int) Color.rgb(77, 83, 97));
        mPaintFillCircle.setAntiAlias(true);

        getLabelsPaint().setColor(Color.WHITE);
        getLabelsPaint().setTextSize(36);
        getLabelsPaint().setTextAlign(Align.CENTER);


        mPaintDataInfo = new Paint();
        mPaintDataInfo.setTextSize(22);
        mPaintDataInfo.setColor(Color.WHITE);
        mPaintDataInfo.setTextAlign(Align.CENTER);
        mPaintDataInfo.setAntiAlias(true);

        //设置起始偏移角度
        setInitialAngle(180);
    }


    /**
     * 设置图表的数据源
     *
     * @param piedata 来源数据集
     */
    public void setDataSource(List<PieData> piedata) {
        this.mDataSet = piedata;
    }

    /**
     * 设置附加信息
     *
     * @param info 附加信息
     */
    public void setAttributeInfo(String info) {
        mDataInfo = info;
    }

    /**
     * 设置圆是显示成完整的一个图还是只显示成一个半圆
     *
     * @param display 半圆/完整圆
     */
    public void setCircleDisplayType(XEnum.CircleDisplayType display) {
        mDisplayType = display;
    }

    /**
     * 开放内部填充的画笔
     *
     * @return 画笔
     */
    public Paint getPaintFillCircle() {
        return mPaintFillCircle;
    }

    /**
     * 开放内部背景填充的画笔
     *
     * @return 画笔
     */
    public Paint getPaintBgCircle() {
        return mPaintBgCircle;
    }

    /**
     * 开放绘制附加信息的画笔
     *
     * @return 画笔
     */
    public Paint getPaintDataInfo() {
        return mPaintDataInfo;
    }


    /**
     * 依比例绘制扇区
     *
     * @param paintArc    画笔
     * @param cirX        x坐标
     * @param cirY        y坐标
     * @param radius      半径
     * @param offsetAgent 偏移
     * @param curretAgent 当前值
     * @throws Exception 例外
     */
    protected void drawPercent(Canvas canvas, Paint paintArc,
                               final float cirX,
                               final float cirY,
                               final float radius,
                               final float offsetAgent,
                               final float curretAgent) throws Exception {
        try {
            float arcLeft = cirX - radius;
            float arcTop = cirY - radius;
            float arcRight = cirX + radius;
            float arcBottom = cirY + radius;
            RectF arcRF0 = new RectF(arcLeft, arcTop, arcRight, arcBottom);
            //在饼图中显示所占比例
            canvas.drawArc(arcRF0, offsetAgent, curretAgent, true, paintArc);
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * 绘制图
     */
    protected void renderPlot(Canvas canvas) {
        try {

            //中心点坐标
            float cirX = plotArea.getCenterX();
            float cirY = plotArea.getCenterY();
            float radius = getRadius();

            //确定去饼图范围
            float arcLeft = cirX - radius;
            float arcTop = cirY - radius;
            float arcRight = cirX + radius;
            float arcBottom = cirY + radius;
            RectF arcRF0 = new RectF(arcLeft, arcTop, arcRight, arcBottom);

            //画笔初始化
            Paint paintArc = new Paint();
            paintArc.setAntiAlias(true);

            //用于存放当前百分比的圆心角度
            float currentAgent = 0.0f;

            DrawHelper dw = new DrawHelper();

            int infoHeight = dw.getPaintFontHeight(mPaintDataInfo);
            int LabelHeight = dw.getPaintFontHeight(getLabelsPaint());
            int textHeight = LabelHeight + infoHeight;

            for (PieData cData : mDataSet) {
                paintArc.setColor(cData.getSliceColor());
                if (XEnum.CircleDisplayType.HALF == mDisplayType) {
                    setInitialAngle(180);

                    drawPercent(canvas, mPaintBgCircle, cirX, cirY, radius, 180f, 180f);
                    drawPercent(canvas, mPaintFillCircle, cirX, cirY, (float) (Math.round(radius * 0.9)), 180f, 180);

                    currentAgent = (int) Math.round(180 * (cData.getPercentage() / 100));
                    drawPercent(canvas, paintArc, cirX, cirY, radius, 180f, currentAgent);
                    drawPercent(canvas, mPaintFillCircle, cirX, cirY, (float) (Math.round(radius * 0.8)), 180f, 180);
                    canvas.drawText(cData.getLabel(), cirX, cirY - textHeight, getLabelsPaint());
                    canvas.drawText(mDataInfo, cirX, cirY - infoHeight, mPaintDataInfo);

                } else {
                    currentAgent = cData.getSliceAgent();
                    canvas.drawCircle(cirX, cirY, radius, mPaintBgCircle);
                    canvas.drawCircle(cirX, cirY, (float) (Math.round(radius * 0.9)), mPaintFillCircle);

                    canvas.drawArc(arcRF0, mOffsetAgent, currentAgent, true, paintArc);
                    canvas.drawCircle(cirX, cirY, (float) (Math.round(radius * 0.8)), mPaintFillCircle);
                    canvas.drawText(cData.getLabel(), cirX, cirY, getLabelsPaint());

                    if (mDataInfo.length() > 0)
                        canvas.drawText(mDataInfo, cirX, cirY + LabelHeight, mPaintDataInfo);
                }

                //下次的起始角度
                // mOffsetAgent += currentAgent;
                break;
            }

        } catch (Exception e) {

        }

    }


    public boolean render(Canvas canvas) throws Exception {
        // TODO Auto-generated method stub

        try {
            super.render(canvas);
            //绘制图表
            renderPlot(canvas);

        } catch (Exception e) {
            throw e;
        }
        return true;
    }

}
