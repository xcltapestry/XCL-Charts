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
package org.xclcharts.common;

/**
 * @ClassName MathHelper
 * @Description  集中了 图形相关的一些用于计算的小函数
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  * MODIFIED    YYYY-MM-DD   REASON
 */

public class MathHelper {

	//Position位置
	private float posX = 0.0f;
	private float posY = 0.0f;

	public MathHelper()
	{
	
	}
	
	//依圆心坐标，半径，扇形角度，计算出扇形终射线与圆弧交叉点的xy坐标
	public void CalcArcEndPointXY(float cirX, float cirY, float mRadius, float cirAngle){
	
		//将角度转换为弧度		
	    float arcAngle = (float) (Math.PI * cirAngle / 180.0);
	    if (cirAngle < 90)
	    {
	        posX = (float) (cirX + (float)(Math.cos(arcAngle)) * mRadius);
	        posY = (float) (cirY + (float)(Math.sin(arcAngle)) * mRadius) ;// - 18;
	    }
	    else if (cirAngle == 90)
	    {
	        posX = cirX;
	        posY = (float) (cirY + mRadius);
	    }
	    else if (cirAngle > 90 && cirAngle < 180)
	    {
	    	arcAngle = (float) (Math.PI * (180 - cirAngle) / 180.0);
	        posX = (float) (cirX - (float)(Math.cos(arcAngle)) * mRadius);
	        posY = (float) (cirY + (float)(Math.sin(arcAngle)) * mRadius);
	    }
	    else if (cirAngle == 180)
	    {
	        posX = (float) (cirX - mRadius);
	        posY = cirY;
	    }
	    else if (cirAngle > 180 && cirAngle < 270)
	    {
	    	arcAngle = (float) (Math.PI * (cirAngle - 180) / 180.0);
	        posX = (float) (cirX - (float)(Math.cos(arcAngle)) * mRadius);
	        posY = (float) (cirY - (float)(Math.sin(arcAngle)) * mRadius);
	    }
	    else if (cirAngle == 270)
	    {
	        posX = cirX;
	        posY = (float) (cirY - mRadius);
	    }
	    else
	    {
	    	arcAngle = (float) (Math.PI * (360 - cirAngle) / 180.0);
	        posX = (float) (cirX + (float)(Math.cos(arcAngle)) * mRadius) ;//- 10;
	        posY = (float) (cirY - (float)(Math.sin(arcAngle)) * mRadius);
	    }
				
	}


	//////////////
	public float getPosX() {
		return posX;
	}
		
	public float getPosY() {
		return posY;
	}	
	//////////////
}
