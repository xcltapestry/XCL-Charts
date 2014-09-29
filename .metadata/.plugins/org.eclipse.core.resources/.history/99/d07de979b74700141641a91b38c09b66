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
 * @version 1.7
 */
package org.xclcharts.renderer.info;

import org.xclcharts.renderer.line.PlotDot;

import android.graphics.Paint;


/**
 * @ClassName Legend
 * @Description 动态图例 基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */
public class Legend extends DyInfo{
	
	protected float mXPercentage = 0.0f;
	protected float mYPercentage = 0.0f;
	
	
	public Legend()
	{
		super();
	}
	
	public void setPosition(float xPercentage,float yPercentage)
	{
		mXPercentage = xPercentage;
		mYPercentage = yPercentage;
	}
	
	public void addLegend(String text,Paint paint)
	{
		addInfo(text,paint);
	}
	
	public void addLegend(PlotDot dotStyle,String text,Paint paint)
	{
		addInfo(dotStyle,text,paint);
	}
	

}
