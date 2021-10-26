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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zoo.woodpecker.util.StringUtil;


/**
 * 校验字段标记
 * @author dzc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Woodpecker {

	/**
     * 公共校验 校验类型：CheckType
     * @return the string
     */
	CheckType commonCheck();
	
	/**
     * 自定义类校验
     * @return the string
     */
    Class<?> selfCheckClassName() default StringUtil.class;
    
	/**
     * 自定义类校验方法名称
     * @return the string
     */
    String selfCheckMethodName() default "";
	
    /**
     * 异常提示语
     * @return the string
     */
    String errorMsg() default "字段异常";
}