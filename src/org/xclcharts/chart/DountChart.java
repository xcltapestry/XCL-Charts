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

import org.xclcharts.renderer.XEnum;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @ClassName DountChart
 * @Description  环形图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public class DountChart  extends PieChart{	

	//内环半径
	private int mFillRadius = 0;
	
	private float mInnerSize = 0.8f;
	
	//内环填充颜色
	private Paint mPaintFill;

	public DountChart()
	{
		super();	
		initChart();
	}
	
	private void initChart()
	{
		int fillColor = this.plotArea.getBackgroundPaint().getColor();
		
		mPaintFill = new Paint();
		mPaintFill.setColor(fillColor); 
		mPaintFill.setAntiAlias(true);
		
		this.setLabelLocation(XEnum.ArcLabelLocation.OUTSIDE);
	}
	
	/**
	 * 环内部填充画笔
	 * @return 画笔
	 */
	public Paint getInnerPaint()
	{
		return mPaintFill;
	}
		
	/**
	 * 设置环内部填充相对于环所占的比例
	 * @param precentage 环所占比例
	 */
	public void setInnerSize(float precentage)
	{
		mInnerSize = precentage;
	}
	
	/**
	 * 计算出环内部填充圆的半径
	 * @return 环的半径
	 */
	public float calcInnerRadius()
	{
		mFillRadius = (int) Math.round(getRadius() * mInnerSize);
		return mFillRadius;
	}

	/**
	 * 绘制图
	 */
	@Override
	protected void renderPlot(Canvas canvas)
	{
		 super.renderPlot(canvas);
		//中心点坐标
		 float cirX = plotArea.getCenterX();
	     float cirY = plotArea.getCenterY();
	     
	     calcInnerRadius();
	     canvas.drawCircle(cirX, cirY, mFillRadius, mPaintFill);		 			
	}	

}
