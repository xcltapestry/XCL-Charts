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
package com.demo.xclcharts.view;

import org.xclcharts.common.MathHelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.View;


/**
 * @ClassName RadarChart
 * @Description  极性图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */

@SuppressLint("NewApi")
public class PolarChart01View extends View{

private int ScrWidth,ScrHeight;	
	
	//总环数
	private final int DuCount = 5;

	public PolarChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		//解决4.1版本 以下canvas.drawTextOnPath()不显示问题			
		this.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
		
		//屏幕信息
		DisplayMetrics dm = getResources().getDisplayMetrics();
		ScrHeight = dm.heightPixels;
		ScrWidth = dm.widthPixels;
	}
	
	
	@SuppressLint("DrawAllocation")
	public void onDraw(Canvas canvas){
		//画布背景
		canvas.drawColor(Color.WHITE);

		
		float cirX = ScrWidth / 2;
		float cirY = ScrHeight / 3 ;
		float radius = ScrHeight / 4 ;
		//先画个圆确定下显示位置
		//canvas.drawCircle(cirX,cirY,radius,PaintGree);
								
		float arcLeft = cirX - radius;
		float arcTop  = cirY - radius ;
		float arcRight = cirX + radius ;
		float arcBottom = cirY + radius ;
		RectF arcRF0 = new RectF(arcLeft ,arcTop,arcRight,arcBottom);			

		////////////////////////////////////////////////////////////			
		//位置计算类  
		MathHelper xcalc = new MathHelper();					
        
        //实际用于计算的半径
        float calcRadius = radius/2;
		////////////////////////////////////////////////////////////

    	//初始化各环颜色
	 	Paint[] arrPaint = new Paint[6];
	 
	 	arrPaint[0] = new Paint();			
		arrPaint[0].setColor(Color.CYAN);
							
		arrPaint[1] = new Paint();			
		arrPaint[1].setColor(Color.argb(255,1, 73, 157));
		
		arrPaint[2] = new Paint();			
		arrPaint[2].setColor(Color.argb(255,0, 94, 196));
		
		arrPaint[3] = new Paint();			
		arrPaint[3].setColor(Color.argb(255,73, 172, 222));
		
		arrPaint[4] = new Paint();			
		arrPaint[4].setColor(Color.argb(255,145, 218, 255));
					
		arrPaint[5] = new Paint();			
		arrPaint[5].setColor(Color.argb(255,204, 238, 255));
		
		arrPaint[1].setAntiAlias(true);
		arrPaint[2].setAntiAlias(true);
		arrPaint[3].setAntiAlias(true);
		arrPaint[4].setAntiAlias(true);
		arrPaint[5].setAntiAlias(true);
		
		//最外环红色填充
		Paint PaintDcred = new Paint();
		PaintDcred.setStyle(Style.FILL);
		PaintDcred.setARGB(255,221,19,223);
		PaintDcred.setAntiAlias(true);
		//分割线
		Paint PaintDcLine = new Paint();
		PaintDcLine.setStyle(Style.FILL);
		PaintDcLine.setARGB(255,36, 169, 199);			
		PaintDcLine.setTypeface(Typeface.DEFAULT_BOLD);
		//标注用画笔
		Paint PaintDcLabel = new Paint();
		PaintDcLabel.setStyle(Style.FILL);
		PaintDcLabel.setColor(Color.BLACK);
		PaintDcLabel.setTextSize(22);
		PaintDcLabel.setTypeface(Typeface.DEFAULT_BOLD);

		//附注
		canvas.drawText("仿网易数据酷",60,ScrHeight - 270, PaintDcLabel);	
		canvas.drawText("author:xiongchuanliang",60,ScrHeight - 250, PaintDcLabel);	

		
		//扇形角度 				
		float pAngle = 72f; 
		//偏移角度
		float pAngleOffset = 18f + 36f; 
		
		//分成五个环
		float avgRadius = radius / DuCount;
		
				
		for(int i=DuCount;i>=0;i--)
		{
			float curRadius = avgRadius * i;
			canvas.drawCircle(cirX,cirY,curRadius,arrPaint[i]);
			if(i == 5)
			{  
				//最外环扇区	
				canvas.drawArc(arcRF0, pAngleOffset + pAngle * (DuCount -1) ,pAngle, true,PaintDcred); 
			}			
		}		
		
		//外围标注
        String arrLabel[] = new String[]{"控球率","抢断","黄牌","犯规","失球数"};		
		for(int i=0;i<DuCount;i++)
		{
			Path PathLabel = new Path();
			PathLabel.addArc(arcRF0, pAngle*i+18, pAngle);
			canvas.drawTextOnPath(arrLabel[i],PathLabel,0,0,PaintDcLabel);
		}

		//各项数据数组
		//String[] arrSort3S = new String[]{"No.1","No.2","No.3","No.3","No.5"};
		String[] arrSort3N = new String[]{"马竞","切尔西","巴萨","拜仁","曼联"};
		String[] arrSort3V = new String[]{"5","7","8","8","9"};
		
		String[] arrSort4N = new String[]{"拜仁","巴萨","巴黎","皇马","马竞"};
		String[] arrSort4V = new String[]{"69.2%","68%","61.8%","56%","43.1%"};
		
		String[] arrSort1N = new String[]{"马竞","切尔西","巴萨","多特","巴黎"};
		String[] arrSort1V = new String[]{"23","21","19","19","19"};
		
		String[] arrSort2N = new String[]{"马竞","多特","切尔西","曼联","巴黎"};
		String[] arrSort2V = new String[]{"155","153","153","131","116"};
		
		String[] arrSort5N = new String[]{"马竞","多特","巴萨","切尔西","皇马"};
		String[] arrSort5V = new String[]{"268","245","200","197","195"};
		
		//圆心红色扇区
		arcLeft = cirX - avgRadius;
		arcTop  = cirY - avgRadius ;
		arcRight = cirX + avgRadius ;
		arcBottom = cirY + avgRadius ;
		RectF arcRF1 = new RectF(arcLeft ,arcTop,arcRight ,arcBottom );	
				
		PaintDcLabel.setTextSize(12);
		for(int j=0;j<DuCount;j++)
		{
			
		    if(j < DuCount -1) //最靠右外环填充为红色
		    {			  
			  canvas.drawArc(arcRF1, pAngleOffset ,pAngle, true,PaintDcred); 
		    }
			
			xcalc.calcArcEndPointXY(cirX, cirY, radius, pAngleOffset); 	
			 //扇形分割线
		    canvas.drawLine(cirX, cirY,  xcalc.getPosX(), xcalc.getPosY(), PaintDcLine);
		    		    
		    //标注俱乐部名字及各项数据
		   for(int i=0;i<DuCount;i++)
		   {			  		    	 
		    	 xcalc.calcArcEndPointXY(cirX, cirY, avgRadius * (DuCount-i), pAngleOffset); 
		    	 
		    	 int curNm = DuCount-1-i;
		   		    	  	
		    	 if(j == 0){ //System.getProperty("line.separator")
		    		 canvas.drawText(arrSort5N[curNm]+"("+arrSort5V[curNm]+")", xcalc.getPosX(),xcalc.getPosY(), PaintDcLabel);  
		    	 }else if(j == 1){
		    		 canvas.drawText(arrSort1N[curNm]+"("+arrSort1V[curNm]+")", xcalc.getPosX(),xcalc.getPosY(), PaintDcLabel);  
		    	 }else if(j == 2){
		    		 canvas.drawText(arrSort2N[curNm]+"("+arrSort2V[curNm]+")", xcalc.getPosX(),xcalc.getPosY(), PaintDcLabel);  
		    	 }else if(j == 3){		    	     
		    	     canvas.drawText(arrSort3N[curNm]+"("+arrSort3V[curNm]+")", xcalc.getPosX(),xcalc.getPosY(), PaintDcLabel);  
		    	 }else if(j == 4){
		    		 canvas.drawText(arrSort4N[curNm]+"("+arrSort4V[curNm]+")", xcalc.getPosX(),xcalc.getPosY(), PaintDcLabel);  
		    	 }
		    	 
		    }		    
			pAngleOffset += pAngle;
		}
		///////////////////////////////////
	}
	

}
