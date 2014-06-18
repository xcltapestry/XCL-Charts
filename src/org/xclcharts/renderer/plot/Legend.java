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
package org.xclcharts.renderer.plot;


/**
 * @ClassName Legend
 * @Description  图例(Legend)基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * * MODIFIED    YYYY-MM-DD   REASON
 */

import android.graphics.Paint;
import android.graphics.Paint.Align;

public class Legend {
	
	//图例文字画笔
	 protected Paint mPaintLeftLegend = null;
	 protected Paint mPaintLowerLegend = null;
	 protected Paint mPaintRightLegend = null;
	//图例文字说明
	 private String mLeftLegend = "";
	 private String mLowerLegend = "";
	 private String mRightLegend = "";	 
	
	public Legend()
	{							
		initPaint();
	}
	
	/**
	 * 初始化画笔 
	 */
	private void initPaint()
	{
		 mPaintLeftLegend = new Paint();
		 mPaintLowerLegend = new Paint();
		 mPaintRightLegend = new Paint();
		 
		 mPaintLeftLegend.setTextAlign(Align.CENTER);
		 mPaintLowerLegend.setTextAlign(Align.CENTER);
		 mPaintRightLegend.setTextAlign(Align.CENTER);	
		 
		 mPaintLeftLegend.setAntiAlias(true);
		 mPaintLowerLegend.setAntiAlias(true);
		 mPaintRightLegend.setAntiAlias(true);
	}
	
	
	/**
	 * 开放左边图例画笔
	 * @return 画笔
	 */
	public Paint getLeftLegendPaint() {
		return mPaintLeftLegend;
	}

	/**
	 * 开放底部图例画笔
	 * @return 画笔
	 */
	public Paint getLowerLegendPaint() {
		return mPaintLowerLegend;
	}

	/**
	 * 开放右边图例画笔
	 * @return 画笔
	 */
	public Paint getRightLegendPaint() {
		return mPaintRightLegend;
	}

	/**
	 * 设置左边图例内容
	 * @param text 图例内容
	 */
	public void setLeftLegend(String text) {
		this.mLeftLegend = text;
	}

	/**
	 * 设置底部图例内容
	 * @param text 图例内容
	 */
	public void setLowerLegend(String text) {
		this.mLowerLegend = text;
	}


	/**
	 * 设置右边图例内容
	 * @param text 图例内容
	 */
	public void setRightLegend(String text) {
		this.mRightLegend = text;
	}
	
	/**
	 * 返回左边图例内容
	 * @return 图例内容
	 */
	public String getLeftLegend() {
		return mLeftLegend;
	}
	
	/**
	 * 返回底部图例内容
	 * @return 图例内容
	 */
	public String getLowerLegend() {
		return mLowerLegend;
	}
	
	/**
	 * 返回右边图例内容
	 * @return 图例内容
	 */
	public String getRightLegend() {
		return mRightLegend;
	}
	

}
