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
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.zoo.swan.annotation.Swan;
import org.zoo.swan.common.config.SwanConfig;
import org.zoo.swan.common.constant.CommonConstant;
import org.zoo.swan.common.exception.SwanException;
import org.zoo.swan.core.coordinator.SwanCoordinatorService;
import org.zoo.swan.core.service.SwanTransactionHandler;
import org.zoo.swan.core.utils.JoinPointUtils;

import com.alibaba.fastjson.JSON;


/**
 * 检查下发Token是否重复
 * @author dzc
 */
@Component
public class CheckTokenHandler implements SwanTransactionHandler {
	
    private static final Logger logger = LoggerFactory.getLogger(CheckTokenHandler.class);
      
    @Autowired
    private SwanConfig swanConfig;
    
    @Autowired
    private SwanCoordinatorService swanCoordinatorService;
    
    @Override
    public Object handler(final ProceedingJoinPoint point) throws Throwable {
    	    Method method = JoinPointUtils.getMethod(point);
        final Swan swan = method.getAnnotation(Swan.class);
        final String errorMsg = swan.errorMsg();
        final RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
        
        String tokenId = request.getHeader(swanConfig.getTokenKey());
        if(!CommonConstant.modeType.equals(swanConfig.getModeType())) {
         	Cookie[] cookies = request.getCookies();
         	if(cookies != null && cookies.length > 0){
         	     for (Cookie cookie : cookies){
         	    	   if(cookie.getName().equals(swanConfig.getTokenKey())) {
                	     	tokenId = cookie.getValue();
                 	    break;
         	    	   }
         	     }
         	 } 
        }
        
        /**
         * 1.间隔很短导致重复场景 - 建议使用分布式锁   比如：两次提交的时间间隔小于500毫秒建议使用分布式锁
         * 2.间隔时间比较长导致重复 - 建议不使用分布式锁提高效率  比如：两次提交的时间间隔大于500毫秒可以不使用分布式锁，因为布隆过滤器已经初始化完成
         * */
        if(swan.hasLock()==true) {
	    	    RLock rLock = swanCoordinatorService.getLock(tokenId);
	            try {
	             	  rLock.tryLock(2, TimeUnit.SECONDS);
	             	  boolean isExistStatus = swanCoordinatorService.isExist(tokenId);
	                  if(!isExistStatus) {
	                   	Boolean saveStatus = swanCoordinatorService.add(tokenId);
	                   	logger.info("用户TokenID保存状态,"+swanConfig.getTokenKey()+"=="+tokenId+",状态："+saveStatus);
	                   	return point.proceed();
	                   }
	                  logger.info("用户重复提交,"+swanConfig.getTokenKey()+"=="+tokenId);
	    		} finally {
	    			try {
	    				if(rLock.isLocked()) {
		    				swanCoordinatorService.unlock(rLock);
		    			}	
				} catch (Exception e) {
					logger.warn("屏蔽锁释放在次关闭导致异常");
				}
	    		}
        }else {
          	boolean isExistStatus = swanCoordinatorService.isExist(tokenId);
            if(!isExistStatus) {
              	Boolean saveStatus = swanCoordinatorService.add(tokenId);
              	logger.info("用户TokenID保存状态,"+swanConfig.getTokenKey()+"=="+tokenId+",状态："+saveStatus);
              	return point.proceed();
             }
             logger.info("用户重复提交,"+swanConfig.getTokenKey()+"=="+tokenId);
        }
        
        SwanException swanException = new SwanException(-1,errorMsg);
        String errorMsgObj = JSON.toJSONString(swanException);
		response.setContentType("application/json; charset=utf-8");
        ServletOutputStream sos = response.getOutputStream();
		sos.write(errorMsgObj.getBytes());
		return null;
    }
    
}
