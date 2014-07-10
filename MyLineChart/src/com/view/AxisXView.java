package com.view;

import com.data.Common;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * X轴
 * @author ZLL
 */
@SuppressLint("DrawAllocation")
public class AxisXView extends View {
	private float xPoint = 0;// 原点X坐标
    private float yPoint = 0;// 原点Y坐标
    private float xLengh = 240;// X轴长度
    private float yLengh = 320;// Y轴长度
    private float xScale = 5;// X轴一个刻度长度
    private int widthBorder = 5;// 内边缘宽度，为了统计图不靠在屏幕的边缘上，向边缘缩进距离
    private String[] xLableArray;// X轴标签
    private int count = 1;//每多少个刻度显示一次文本
    
    public AxisXView(Context context) {
        super(context);
    }

    /**
     * 实例化值
     *
     * @param screenWidth  图表宽度
     * @param ScreenHeight 图表高度
     * @param xLable       X轴标签
     * @param yLable       Y轴标签
     * @param dataArray    统计数据
     */
    public void initValue(int Width, int Height) {
        xPoint = widthBorder;
        yPoint = Height - 20;
        xLengh = Width - widthBorder * 2 ;
        yLengh = Height ;
        xScale = getScale(Common.xScaleArray.length - 1, xLengh);
        xLableArray = Common.xScaleArray;

        if (xLableArray.length <= 10) {
            count = 2;
        } else {
            count = 4;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 设置画笔
        Paint linepaint = new Paint();
        linepaint.setStyle(Paint.Style.FILL);// 设置画笔样式
        linepaint.setAntiAlias(true);// 去锯齿
        linepaint.setColor(Common.xScaleColor);// 设置颜色
        linepaint.setTextSize(16);// 设置字体
        linepaint.setStrokeWidth(3);
        // 画X轴轴线
        canvas.drawLine(xPoint, yPoint, xPoint + xLengh, yPoint, linepaint);
        for (int i = 0; xLableArray != null && i < xLableArray.length; i++) {
            // 画X轴刻度
        	if(i%count == 0 ){
        		canvas.drawLine(xPoint+xScale*i, yPoint, xPoint+xScale*i, yPoint - 8, linepaint);
        		canvas.drawText(xLableArray[i]+"", xPoint+xScale*i-5, yPoint+20, linepaint);
        	}
        	else 
        		canvas.drawLine(xPoint+xScale*i, yPoint, xPoint+xScale*i, yPoint - 4, linepaint);
            
        }
    }


    
    
    
    /**
     * 得到每一等分的长度
     *
     * @param num    所要分成的等份
     * @param length 要分割的总长度
     * @return
     */
    private float getScale(int num, float length) {
        if (num > 0 && length > 0) {
            length -= 10;// 为了美观，缩进
            length = length - (length % num);
            return length / num;
        } else {
            return 0;
        }

    }
    
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension((int)xLengh, (int)yLengh);  
	}
}
