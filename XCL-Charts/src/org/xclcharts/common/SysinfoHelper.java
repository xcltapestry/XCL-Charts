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
 * @version 1.0
 */
package org.xclcharts.common;

/**
 * @ClassName SysinfoHelper
 * @Description  得到系统信息,如版本之类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public class SysinfoHelper {
	
	private static SysinfoHelper instance = null;
	
	public SysinfoHelper()
	{	
	}
	
	public static synchronized SysinfoHelper getInstance(){
		if(instance == null){
			instance = new SysinfoHelper();
		}
		return instance;
	}
	
	
	/**
	 * android自3.0引入了硬件加速，即使用GPU进行绘图.但低版本的Android不支持这个类，
	 * 为了兼容性，在低版本中将其硬件加速相关的代码设为不可用。
	 * @return 系统是否包含硬件加速类
	 */
	public boolean supportHardwareAccelerated()
	{
		boolean result = true;
		int currentVersion = android.os.Build.VERSION.SDK_INT;	
		//android 3.0 == android.os.Build.VERSION_CODES.HONEYCOMB
		if(currentVersion < 11) result = false;
		return result;
	}

}
