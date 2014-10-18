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
 * @version v 1.3
 */


package org.xclcharts.chart;

import java.util.ArrayList;
import java.util.List;

import org.xclcharts.renderer.CirChart;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.XEnum.Location;
import org.xclcharts.renderer.axis.RoundAxis;
import org.xclcharts.renderer.axis.RoundAxisRender;
import org.xclcharts.renderer.plot.PlotAttrInfo;
import org.xclcharts.renderer.plot.PlotAttrInfoRender;
import org.xclcharts.renderer.plot.Pointer;
import org.xclcharts.renderer.plot.PointerRender;

import android.graphics.Canvas;
import android.util.Log;


/**
 * @ClassName DialChart
 * @Description  仪表盘基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public class DialChart  extends CirChart{
	
	private static final String TAG = "DialChart";
			
	private static final int INIT_ANGLE = 135;
	private static final int FIX_TOTAL_ANGLE  = 270;
	
	private float mStartAngle = 0.0f;
	private float mTotalAngle = 0.0f;
	
	private List<RoundAxis> mRoundAxis =  new ArrayList<RoundAxis>();
	
	//指针[默认1个，其它add]
	private PointerRender mPointer = null;
	List<Pointer> mPointerSet = new ArrayList<Pointer>();
	
	//附加信息类
	private PlotAttrInfoRender plotAttrInfoRender = null;
		

	public DialChart()
	{			
		mStartAngle	= INIT_ANGLE;
		mTotalAngle = FIX_TOTAL_ANGLE;
		
		if(null == plotAttrInfoRender)
			plotAttrInfoRender = new PlotAttrInfoRender();
	}
	
	public void setStartAngle(float startAngle)
	{	
		mStartAngle = startAngle;
	}
	
	public void setTotalAngle(float totalAngle)
	{		
		mTotalAngle = totalAngle;
	}
	
	
	/**
	 * 返回图轴集合
	 * @return 集合
	 */
	public List<RoundAxis> getPlotAxis()
	{
		return mRoundAxis;
	}
	
	/**
	 * 返回额外的指针集合
	 * @return	指针集合
	 */
	public List<Pointer> getPlotPointer()
	{
		return mPointerSet;
	}
	
	/**
	 * 附加信息绘制处理类
	 * @return 信息基类
	 */
	public PlotAttrInfo getPlotAttrInfo()
	{
		return plotAttrInfoRender; 
	}
	
	
	/**
	 * 增加额外的指针
	 */
	 public void addPointer()
	 {
		 mPointerSet.add(new PointerRender());
	 }
	 	
	 /**
	  * 清掉指针信息
	  */
	 public void clearPlotPointer()
	 {
		 if(null != mPointerSet) mPointerSet.clear();
	 }
	 
	/**
	 * 清空当前所有轴数据集合
	 */
	 public void clearPlotAxis()
	 {
		if(null != mRoundAxis) mRoundAxis.clear();	
	 }		
	 
	
	 /**
	  * 清掉所有相关信息
	  */
	 public void clearAll()
	 {
		 clearPlotPointer();
		 clearPlotAxis();
		 this.plotAttrInfoRender.clearPlotAttrInfo();
	 }
	 
		
	/**
	 * 返回指针绘制类
	 * @return 指针绘制类
	 */
	public Pointer getPointer()
	{
		if(null == mPointer) mPointer = new PointerRender();
		return mPointer;
	}

	
    /**
     * 绘制指针
     * @param canvas	画布
     */
	private void renderPointerLine(Canvas canvas)
	{											
		if(null == mPointerSet) return;	
		float radius = getRadius();
		for(int i=0;i< mPointerSet.size();i++)
		{
			PointerRender pointer = (PointerRender) mPointerSet.get(i);
			pointer.setParentRadius(radius);
			pointer.setStartXY(plotArea.getCenterX(), plotArea.getCenterY());
			pointer.setTotalAngle(mTotalAngle );
			pointer.setStartAngle(mStartAngle);
			pointer.render(canvas);
		}
		
		if(null == mPointer) mPointer = new PointerRender();	
		mPointer.setStartXY(plotArea.getCenterX(), plotArea.getCenterY());
		mPointer.setTotalAngle(mTotalAngle );
		mPointer.setStartAngle(mStartAngle);
		mPointer.setParentRadius(getRadius());
		mPointer.render(canvas);
	}
	

	/**
	 * 增加 标签环形轴
	 * @param radiusPercentage 显示在总半径的指定比例所在位置
	 * @param labels 标签集合
	 */
	public void addInnerTicksAxis(float radiusPercentage,List<String> labels)
	{
		 addTicksAxis(radiusPercentage,labels,XEnum.RoundTickAxisType.INNER_TICKAXIS);
	}
	
	/**
	 * 增加 标签环形轴
	 * @param radiusPercentage 显示在总半径的指定比例所在位置
	 * @param labels 标签集合
	 */
	public void addOuterTicksAxis(float radiusPercentage,List<String> labels)
	{	
		 addTicksAxis(radiusPercentage,labels,XEnum.RoundTickAxisType.OUTER_TICKAXIS);
	}
	
	
	private void addTicksAxis(float radiusPercentage,
								List<String> labels,XEnum.RoundTickAxisType type)
	{
		RoundAxisRender roundAxis = new RoundAxisRender();	
		roundAxis.setRoundAxisType(XEnum.RoundAxisType.TICKAXIS);	
		roundAxis.setRadiusPercentage(radiusPercentage);
		roundAxis.setAxisLabels(labels);		
		roundAxis.setRoundTickAxisType(type);
		mRoundAxis.add(roundAxis);
	}
	
		
	/**
	 * 增加空心环形轴集合
	 * @param outerRadiusPercentage	外环显示在总半径的指定比例所在位置
	 * @param innerRadiusPercentage	内环显示在总半径的指定比例所在位置
	 * @param percentage	百分比
	 * @param color	颜色
	 */
	public void addStrokeRingAxis(float outerRadiusPercentage,float innerRadiusPercentage,
			List<Float> percentage,List<Integer> color)
	{	
		addRingAxis(outerRadiusPercentage,innerRadiusPercentage,percentage,color,null);		
	} 
			
	/**
	 * 增加空心环形轴集合
	 * @param outerRadiusPercentage	外环显示在总半径的指定比例所在位置
	 * @param innerRadiusPercentage	内环显示在总半径的指定比例所在位置
	 * @param percentage	百分比
	 * @param color	产色
	 * @param labels	标签集合
	 */
	public void addStrokeRingAxis(float outerRadiusPercentage,
									float innerRadiusPercentage,
									List<Float> percentage,
									List<Integer> color,
									List<String> labels)
	{		
		addRingAxis(outerRadiusPercentage,innerRadiusPercentage,percentage,color,labels);		
	} 
	
	
	/**
	 * 增加实心环形轴集合
	 * @param radiusPercentage	显示在总半径的指定比例所在位置
	 * @param percentage	百分比
	 * @param color	颜色
	 */
	public void addFillRingAxis(final float radiusPercentage,
								final List<Float> percentage,final List<Integer> color)
	{
		addRingAxis(radiusPercentage,0.0f,percentage,color,null);
	}   
	
	/**
	 *  增加实心环形轴集合
	 * @param radiusPercentage 显示在总半径的指定比例所在位置
	 * @param percentage	百分比
	 * @param color	颜色
	 * @param labels	标签集合
	 */
	public void addFillRingAxis(float radiusPercentage,
										List<Float> percentage,
										List<Integer> color,List<String> labels)
	{
		addRingAxis(radiusPercentage,0.0f,percentage,color,labels);
	}   
	

	/**
	 * 增加环形轴集合
	 * @param outerRadiusPercentage	外环显示在总半径的指定比例所在位置
	 * @param innerRadiusPercentage	内环显示在总半径的指定比例所在位置
	 * @param percentage	百分比
	 * @param color	颜色
	 * @param labels	标签集合
	 */
	public void addRingAxis(float outerRadiusPercentage,float innerRadiusPercentage,
										List<Float> percentage,
										List<Integer> color,List<String> labels)
	{						
		RoundAxisRender roundAxis = new RoundAxisRender();	
		roundAxis.setRoundAxisType(XEnum.RoundAxisType.RINGAXIS);
		roundAxis.setRadiusPercentage(outerRadiusPercentage);
		roundAxis.setRingInnerRadiusPercentage(innerRadiusPercentage);	
		roundAxis.setAxisPercentage(percentage);		
		roundAxis.setAxisColor(color);
		roundAxis.setAxisLabels(labels);
		
		mRoundAxis.add(roundAxis);
	}
	
	/**
	 * 增加弧线轴
	 * @param radiusPercentage	显示在总半径的指定比例所在位置
	 */
	public void addArcLineAxis(float radiusPercentage)
	{
		RoundAxisRender roundAxis = new RoundAxisRender();	
		roundAxis.setRoundAxisType(XEnum.RoundAxisType.ARCLINEAXIS);
		roundAxis.setRadiusPercentage(radiusPercentage);
		mRoundAxis.add(roundAxis);
	}
	
	/**
	 * 增加填充环形轴
	 * @param radiusPercentage	显示在总半径的指定比例所在位置
	 * @param color	颜色
	 */
	public void addFillAxis(float radiusPercentage,int color)
	{
		RoundAxisRender roundAxis = new RoundAxisRender();	
		roundAxis.setRoundAxisType(XEnum.RoundAxisType.FILLAXIS);
		roundAxis.setRadiusPercentage(radiusPercentage);
		List<Integer> lstColor = new ArrayList<Integer>();
		lstColor.add(color);		
		roundAxis.setAxisColor(lstColor);
		mRoundAxis.add(roundAxis);
	}
	
	
	/**
	 * 增加填充圆轴
	 * @param radiusPercentage	显示在总半径的指定比例所在位置
	 * @param color	颜色
	 */
	public void addCircleAxis(float radiusPercentage,int color)
	{
		RoundAxisRender roundAxis = new RoundAxisRender();	
		roundAxis.setRoundAxisType(XEnum.RoundAxisType.CIRCLEAXIS);
		roundAxis.setRadiusPercentage(radiusPercentage);
		List<Integer> lstColor = new ArrayList<Integer>();
		lstColor.add(color);		
		roundAxis.setAxisColor(lstColor);
		mRoundAxis.add(roundAxis);
	}
	
	
	/**
	 * 增加线轴
	 * @param location			位置
	 * @param radiusPercentage  占总半径的指定比例
	 */
	public void addLineAxis(Location  location ,float radiusPercentage)
							//XEnum.LineStyle lineStyle,XEnum.DotStyle dotStyle) 
	{ 
		RoundAxisRender roundAxis = new RoundAxisRender();	
		roundAxis.setRoundAxisType(XEnum.RoundAxisType.LINEAXIS);
		roundAxis.setRadiusPercentage(radiusPercentage);		
		roundAxis.setLineAxisLocation(location);
		
		//没心情搞了，不弄这个线类型及箭头啥的了
		//roundAxis.setLineStyle(lineStyle);
		//roundAxis.setLineCap(dotStyle);
		mRoundAxis.add(roundAxis);		
	}	

	
	/**
	 * 绘制图
	 */
	protected void renderPlot(Canvas canvas)
	{
		try{						
			float radius = getRadius();
			//画上各组环形轴
			for(int i = 0; i < this.mRoundAxis.size(); i++)  
	        {  				
				RoundAxisRender roundAxis = (RoundAxisRender)mRoundAxis.get(i);
				roundAxis.setCenterXY(plotArea.getCenterX(), plotArea.getCenterY());
				roundAxis.setAngleInfo(mTotalAngle , mStartAngle);	
				roundAxis.setOrgRadius(radius);
				roundAxis.render(canvas);
	        }  		
			
			//绘制附加信息
			 plotAttrInfoRender.renderAttrInfo(canvas, 
					 plotArea.getCenterX(), plotArea.getCenterY(), this.getRadius());
			
			//最后再画指针
			 renderPointerLine(canvas);
			 			 
			 mRoundAxis.clear();
			 mPointerSet.clear();					 
			 plotAttrInfoRender.clearPlotAttrInfo();
		}catch( Exception e){
			Log.e(TAG,e.toString());
		}		
	}
				
	@Override
	protected boolean postRender(Canvas canvas) throws Exception 
	{
		try {
			super.postRender(canvas);
			
			//绘制图表
			renderPlot(canvas);
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
			

}
