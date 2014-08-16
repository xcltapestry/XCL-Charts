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
 * @version 1.3
 */
package org.xclcharts.common;

import android.graphics.PointF;

/**
 * @ClassName PointHelper
 * @Description  点相关的一些小函数
 * @author cmeiyuan<br/>(334388454@qq.com)
 *  
 */

public class PointHelper {
	
	// 求两点中点
		public static PointF center(PointF p1, PointF p2) {
			return new PointF((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
		}

		// 求两点间距离
		public static float distance(PointF p1, PointF p2) {
			float dx = Math.abs(p2.x - p1.x);
			float dy = Math.abs(p2.y - p1.y);
			return (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		}

		// 平移点
		public static PointF translate(PointF p, float x, float y) {
			return new PointF(p.x + x, p.y + y);
		}

		// 计算两点连线中的一点，这个点把这条线分成两段，比例是percent
		public static PointF percent(PointF p1, PointF p2, float percent) {
			float x = (p2.x - p1.x) * percent + p1.x;
			float y = (p2.y - p1.y) * percent + p1.y;
			return new PointF(x, y);
		}
	
}
