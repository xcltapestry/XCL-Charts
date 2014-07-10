package com.view;


import com.data.Common;
import com.example.mylinechart.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Y轴
 * @author ZLL
 */
@SuppressLint("DrawAllocation")
public class AxisYView extends View {

    private float xPoint = 0;// 原点X坐标
    private float yPoint = 0;// 原点Y坐标
    private float yLengh = 320;// Y轴长度
    private int widthBorder = 0;// 内边缘宽度，为了统计图不靠在屏幕的边缘上，向边缘缩进距离。最好大于30。
    private int[] yLableArray;// Y轴标签,用于计算
    private String levelName[];// 污染级别
    private float[] begins;//Y轴刻度的y坐标
    private float each;//均分后刻度之间的长度
    public AxisYView(Context context) {
        super(context);
        init();
    }

    private void init() {
    	widthBorder = Common.YWidth;
    }

    /**
     * 实例化值
     * @param Width  图表宽度
     * @param Height 图表高度
     * @param yLable       Y轴标签
     */
    public void initValue(int Height) {
        xPoint = widthBorder;
        yPoint = Height ;
        yLengh = Height ;
        yLableArray = Common.yScaleArray;
        this.levelName = Common.levelName;
        
        help2getPoint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 设置画笔
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);// 设置画笔样式
        paint.setAntiAlias(true);// 去锯齿
        paint.setColor(Color.BLACK);// 设置颜色
        paint.setTextSize(Integer.parseInt(getResources().getString(R.string.textsizeX)));// 设置字体
        paint.setStrokeWidth(1);

        for (int i = 0; yLableArray != null && i < yLableArray.length; i++) {
            int j = i;
            // 画Y轴刻度标签
            if (j < yLableArray.length) {
                if (j==yLableArray.length-1){
                    canvas.drawText(String.valueOf(yLableArray[j]), 2,
                            begins[i], paint);
                } else {
                	canvas.drawText(String.valueOf(yLableArray[j]), 2,
                    begins[i], paint);
                }
            }
        }

        drawLevelName(canvas, paint);
        drawLelveBitmap(canvas);
    }

    private void drawLevelName(Canvas canvas, Paint paint) {
        // 设置画笔
        paint.setStyle(Paint.Style.FILL);// 设置画笔样式
        paint.setAntiAlias(true);// 去锯齿
        paint.setColor(Color.BLACK);// 设置颜色
        paint.setTextSize(Integer.parseInt(getResources().getString(R.string.textsizeX)));// 设置字体
        for (int i = 0; i < levelName.length; i++) {

            if (i==levelName.length-1){
                canvas.drawText(levelName[i], 0, begins[i]-each/2, paint);
            }  else {
            	canvas.drawText(levelName[i], 0, (begins[i]+begins[i+1])/2, paint);
            }
        }

    }

    private void drawLelveBitmap(Canvas canvas) {
        // 设置画笔
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);// 设置画笔样式
        paint.setAntiAlias(true);// 去锯齿
        
        for(int i=0;i<Common.yScaleColor.length;i++){
        	paint.setColor(Common.yScaleColor[i]);
        	canvas.drawRect(xPoint - 10, begins[i+1], xPoint, begins[i], paint);
        }
    }
    
    private void help2getPoint(){
    	begins = new float[yLableArray.length];
    	each = yLengh / (yLableArray.length-1);
    	for(int i=0;i<yLableArray.length-1;i++){
    		if(i==0){
    			begins[i] = yPoint;
    		}
    		else{
    			begins[i] = begins[i-1] - each;
    		}
    	}
    }
    
    
//    @Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		setMeasuredDimension((int)xLengh, (int)yLengh);  
//	}
}
