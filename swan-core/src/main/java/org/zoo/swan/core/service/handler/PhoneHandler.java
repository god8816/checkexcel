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

package org.zoo.swan.core.service.handler;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.zoo.swan.core.service.SwanTransactionHandler;
import org.zoo.woodpecker.annotation.Woodpecker;
import org.zoo.woodpecker.core.utils.JoinPointUtils;

/**
 * 手机号校验
 * @author dzc
 */
@Component
public class PhoneHandler implements SwanTransactionHandler {
	
    private static final Logger logger = LoggerFactory.getLogger(PhoneHandler.class);
         
    @Override
    public Object handler(final ProceedingJoinPoint point) throws Throwable {
    	    Method method = JoinPointUtils.getMethod(point);
        final Woodpecker swan = method.getAnnotation(Woodpecker.class);
        final String errorMsg = swan.errorMsg();
        final RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();

		return null;
    }
    
}
