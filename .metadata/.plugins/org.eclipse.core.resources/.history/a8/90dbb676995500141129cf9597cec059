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
 * @version 1.8
 */
package org.xclcharts.renderer.plot;

import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;


/**
 * @ClassName LabelBrokenLineRender
 * @Description 绘制折线标签的绘制类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */
public class LabelBrokenLineRender extends LabelBrokenLine{
	
	private Path mBzLine = null;
	
	public LabelBrokenLineRender()
	{
		super();
	}
		
	public void renderLabelLine(String text,float itemAngle,
			float cirX,float cirY,float radius,float calcAngle,
			Canvas canvas,Paint paintLabel )
	{
		float pointRadius = 0.0f;
		if(getLinePointStyle() == XEnum.LabelLinePoint.END 
				||getLinePointStyle() == XEnum.LabelLinePoint.ALL)
			 pointRadius = getRadius();
		
			//显示在扇形的外部
			//1/4处为起始点		  
			float calcRadius = MathHelper.getInstance().sub(radius  , radius / mBrokenStartPoint);
			MathHelper.getInstance().calcArcEndPointXY(
											cirX, cirY, calcRadius, calcAngle);	
			
			float startX = MathHelper.getInstance().getPosX();
			float startY = MathHelper.getInstance().getPosY();
				    
		    //延长原来半径的一半在外面
		    calcRadius = radius / 2f;			    
		    MathHelper.getInstance().calcArcEndPointXY(startX, startY, calcRadius, calcAngle);			
			float stopX = MathHelper.getInstance().getPosX();
		    float stopY = MathHelper.getInstance().getPosY();
		 
		    
		    float borkenline = getBrokenLine(); //折线长度
		    		    
		    float endX = 0.0f,endLabelX = 0.0f;				    
		    if(Float.compare(stopX, cirX) == 0){ //位于中间竖线上				    			    	
		    	if(Float.compare(stopY, cirY) == 1 ) //中点上方,左折线
		    	{
		    		paintLabel.setTextAlign(Align.LEFT);
		    		endX = stopX + borkenline ; //+ pointRadius;	
		    		endLabelX = endX + pointRadius;	
		    	}else{ //中点下方,右折线		    				    		
		    		paintLabel.setTextAlign(Align.RIGHT);
		    		endX = stopX - borkenline;	
		    		endLabelX = endX - pointRadius;	
		    	}
		    }else if(Float.compare(stopY, cirY) == 0 ){ //中线横向两端
		    	endX = stopX;	
		    	if(Float.compare(stopX, cirX) == 0 ||
		    			Float.compare(stopX, cirX) == -1) //左边
		    	{
		    		paintLabel.setTextAlign(Align.RIGHT);
		    		endLabelX = endX - pointRadius;	
		    	}else{
		    		paintLabel.setTextAlign(Align.LEFT);
		    		endLabelX = endX + pointRadius;	
		    	}		    	
		    		    
		    }else if(Float.compare(stopX + borkenline , cirX) == 1 ) //右边
		    {
		    	paintLabel.setTextAlign(Align.LEFT);
		    	endX = stopX + borkenline;	
		    	endLabelX = endX + pointRadius;	
		    }else if(Float.compare(stopX - borkenline ,cirX) == -1  ) //左边
		    {
		    	paintLabel.setTextAlign(Align.RIGHT);
		    	endX = stopX - borkenline ;	
		    	endLabelX = endX - pointRadius;	
		    }else {
		    	endLabelX = endX = stopX;
		    	paintLabel.setTextAlign(Align.CENTER);
		    }
		    
    if(mIsBZLine)
    {
	   //绘制贝塞尔曲线  
	    drawBZLine(startX,startY,stopX, stopY, endX,canvas);
    }else{
    	 //转折线
        drawBrokenLine(startX,startY,stopX, stopY, endX,canvas);
    }
      	
    //标签点NONE,BEGIN,END,ALL    
    drawPoint(startX,startY,stopX, stopY, endX,pointRadius,canvas);
    
    //标签
    DrawHelper.getInstance().drawRotateText(text,endLabelX, stopY, itemAngle, 
													canvas, paintLabel);
}

	
	private void drawBrokenLine(float startX,float startY,
			float stopX,float stopY,float endX,
			Canvas canvas)
	{
	    //连接线
	    canvas.drawLine(startX, startY,stopX, stopY, getLabelLinePaint());		    
		
		//转折线
	    canvas.drawLine(stopX, stopY, endX, stopY, getLabelLinePaint());
	}

	private void drawBZLine(float startX,float startY,
							float stopX,float stopY,float endX,
							Canvas canvas)
	{
		 if(null == mBzLine)mBzLine = new Path();
		 
		 getLabelLinePaint().setStyle(Style.STROKE);		 		 
		 //绘制贝塞尔曲线  
		 mBzLine.reset();
		 mBzLine.moveTo(startX, startY);		    
		 mBzLine.quadTo(stopX, stopY, endX, stopY);  
		 canvas.drawPath(mBzLine, getLabelLinePaint());
	}
	
		
	private void drawPoint(float startX,float startY,
							float stopX,float stopY,float endX,
							float pointRadius,Canvas canvas)
	{
		 //NONE,BEGIN,END,ALL
	    switch(getLinePointStyle())
	    {
		    case NONE:
		    	break;
		    case BEGIN:	    	
		    	 canvas.drawCircle( startX, startY, 
		    			 pointRadius,getPointPaint());		    		    	
		    	break;
		    case END:
		    	 canvas.drawCircle( endX, stopY, 
		    			 pointRadius, getPointPaint());	    		    	
		    	 break;
		    case ALL:	    	
		    	 canvas.drawCircle( startX, startY,  pointRadius, getPointPaint());
			    		    	 
		    	 canvas.drawCircle( endX, stopY,  pointRadius, getPointPaint());
		    	break;
		    default:
		    	break;	    		
	    }
	    
	    
	}

}