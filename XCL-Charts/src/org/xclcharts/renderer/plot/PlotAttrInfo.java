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
 * @version 1.5
 */
package org.xclcharts.renderer.plot;

import java.util.ArrayList;
import java.util.List;

import org.xclcharts.renderer.XEnum.Location;

import android.graphics.Paint;

/**
 * @ClassName PlotAttrInfo
 * @Description 图的附加信息绘制基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * 
 */
public class PlotAttrInfo {
	
	protected List<Location> mAttrInfoLocation = null;
	protected List<String> mAttrInfo = null;
	protected List<Float> mAttrInfoPostion = null;	
	protected List<Paint> mAttrInfoPaint = null;
	
	
	public PlotAttrInfo()
	{
	}
	
	/**
	 * 返回附加信息集合
	 * @return 集合
	 */
	public List<String> getPlotAttrInfo()
	{
		return mAttrInfo;
	}
	
	/**
	 * 返回附加信息位置集合
	 * @return 集合
	 */
	public List<Float> getPlotAttrInfoPostion()
	{
		return mAttrInfoPostion;
	}
	
	/**
	 * 返回附加信息画笔集合
	 * @return 集合
	 */
	public List<Paint> getPlotAttrInfoPaint()
	{
		return mAttrInfoPaint;
	}
	
	
	/**
	  * 清掉所有附加信息
	  */
	 public void clearPlotAttrInfo()
	 {
		if(null != mAttrInfoLocation) mAttrInfoLocation.clear();	
		if(null != mAttrInfo) mAttrInfo.clear();	
		if(null != mAttrInfoPostion) mAttrInfoPostion.clear();	
		if(null != mAttrInfoPaint) mAttrInfoPaint.clear();	
	 }		
	 
	
	 
	 /**
	  * 增加附加信息
	  * @param position		显示方位
	  * @param info			附加信息
	  * @param infoPosRadiusPercentage	信息显示在总半径指定比例所在位置
	  * @param paint		用来绘制用的画笔
	  */
	   public void addAttributeInfo(Location  position ,String info,
	   								float infoPosRadiusPercentage  ,Paint paint) { 
	   	
		   	if(null == mAttrInfoLocation) mAttrInfoLocation = new ArrayList<Location> ();
		   	if(null == mAttrInfo) mAttrInfo = new ArrayList<String>();
		   	
		   	if(null == mAttrInfoPostion) mAttrInfoPostion = new ArrayList<Float>();    	
		   	if(null == mAttrInfoPaint) mAttrInfoPaint = new ArrayList<Paint>();
		   	    	
		   	mAttrInfoLocation.add(position);
		   	mAttrInfo.add(info);
		   	mAttrInfoPostion.add(infoPosRadiusPercentage);
		   	mAttrInfoPaint.add(paint);    	
	   }
	

}
