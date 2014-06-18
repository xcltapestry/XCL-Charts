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

package org.xclcharts.renderer;

/**
 * @ClassName XEnum
 * @Description 枚举定义
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */

public class XEnum {

		/**
		 * 图标题的显示时靠左，中间，还是靠右
		 * @author XCL
		 *
		 */
		public enum ChartTitleAlign {  
			LEFT, CENTER ,RIGHT
		}
		
		/**
		 * 对于圆形的图，如饼图之类，用来确定标签的显示位置
		 * @author XCL
		 *
		 */
		public enum DisplayPosition {
			CENTER,OUTSIDE,HIDE
		}
				
		/**
		 * 是显示全圆还是半圆
		 * @author XCL
		 *
		 */
		public enum CircleDisplayType {		
			FULL,HALF
		}
				
		
		/**
		 * 三角形的朝向
		 * @author XCL
		 *
		 */
		public enum TriangleDirection {
			UP,DOWN,LEFT,RIGHT
		}
		 
		/**
		 * 三角形填充风格 
		 * @author XCL
		 *
		 */
		public enum TriangleStyle {
			OUTLINE,FILL 
		}
		
		
		/**
		 * 网格线:奇数行/偶数行
		 * @author XCL
		 *
		 */
		public enum GridLinesRows {
			ODD,EVEN 
		}
		
		
		/**
		 * 线图，线的几种显示风格:Solid、Dot、Dash
		 * @author XCL
		 *
		 */
		public enum LineDashStyle {
			SOLID,DOT,DASH
		}
		
		
		/**
		 * 横向或竖向网格线
		 * @author XCL
		 *
		 */
		public enum Direction {
			HORIZONTAL, VERTICAL
		}
		
		
		/**
		 * 轴类型(数据轴与标签轴)
		 * @author XCL
		 *
		 */
		public enum AxisType {
			DATA, LABEL
		}
		
		
		/**
		 * 轴标记类别:刻度线/标签
		 * @author XCL
		 *
		 */
		public enum TickType {
			MARKS,LABELS
		}
		
		
		/**
		 * 图例的位置,左边图例，底部图例,右边图例
		 * @author XCL
		 *
		 */
		public enum LegendPostion
		{
			LEFT,LOWER,RIGHT
		}
		
		
		/**
		 * 坐标轴标签位置,上方，中间，底部
		 * @author XCL
		 *
		 */
		public enum Position
		{
			UP,CENTER,LOWER
		}
		
		
		/**
		 * 线的类型，默认的直线还是贝塞尔曲线(Bézier curve)
		 * @author XCL
		 *
		 */
		public enum LineStyle
		{
			Default,BezierCurve
		}
	
		
		
		/**
		 * 点的类型，隐藏，三角形,方形,实心圆,空心圆,棱形
		 * @author XCL
		 * @param HIDE	隐藏，不显示点
		 * @param TRIGANALE	三角形
		 * @param RECT	方形
		 * @param CIRCLE	实心圆
		 * @param RING	空心圆
		 * @param PRISMATIC	棱形
		 */
		public enum DotStyle {		
			HIDE,TRIANGLE,RECT,CIRCLE,RING,PRISMATIC
		}
		
		
		/**
		 * 线图，坐标轴的显示位置:左边轴,右边轴
		 * @author XCL
		 *
		 */
		public enum LineDataAxisPosition {
			LEFT, RIGHT
		}
		
}
