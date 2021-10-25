/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.zoo.woodpecker.annotation;

/**
 * 公共校验类型
 * @author dzc
 */
public enum CheckType {
	
	 //手机号校验
	 phone,
	 //身份证校验
	 idcard,
	 //时间格式校验
	 timeFormat,
	 //邮箱校验
	 email,
	 //是否是数字
	 number,
	 //是否是字母
	 en,
	 //是否是中文
	 cn,
	 //省
	 province,
	 //市
	 city,
	 //区
	 area,
	 //街道
	 street;
}
