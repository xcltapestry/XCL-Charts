package com.view;


import com.data.Common;
import com.data.MyData;
import com.example.mylinechart.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
/**
 * @author Seven
 */
public class TitleView extends View {

    public TitleView(Context context) {
        super(context);
    }

    /**
     * 实例化值
     *
     */
    public void initValue() {
    	
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

        
        drawTitle(canvas);
        drawRect(canvas);
    }
    
    private void drawTitle(Canvas canvas){
    	// 设置画笔
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);// 设置画笔样式
        paint.setAntiAlias(true);// 去锯齿
        paint.setColor(Common.titleColor);// 设置颜色
        paint.setTextSize(24);// 设置字体
        
        canvas.drawText(Common.title, Common.titleX, Common.titleY, paint);
    }    
    
    private void drawRect(Canvas canvas){
    	int count = 0;
    	
    	int width = Common.keyWidth;
    	int height = Common.keyHeight;
    	int toLeft = Common.keyToLeft;
    	int toTop = Common.keyToTop;
    	int toNext = Common.keyToNext;
    	int textPadding = Common.keyTextPadding;
    	
    	for(MyData data:Common.DataSeries){
    		Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL_AND_STROKE);// 设置画笔样式
            paint.setAntiAlias(true);// 去锯齿
            paint.setColor(data.getColor());// 设置颜色
            
            if(toLeft+toNext*count+width > Common.screenWidth){
            	toTop += height*3/2;
            	count = 0;
            }
	        canvas.drawRect(toLeft+toNext*count, toTop, toLeft+toNext*count+width, toTop+height, paint);
	        canvas.drawText(data.getName(), toLeft+toNext*count+width+textPadding, toTop+height, paint);
            
            count++;
    	}
    }
}
