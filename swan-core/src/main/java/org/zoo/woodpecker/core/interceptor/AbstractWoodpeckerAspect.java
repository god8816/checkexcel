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

package org.zoo.woodpecker.core.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.zoo.swan.core.service.SwanTransactionHandler;

/**
 * 切入点
 * @author dzc
 */
@Aspect
@Component
public abstract class AbstractWoodpeckerAspect {

    private SwanTransactionHandler swanTransactionHandler;

    /**
     * 切入处理入口
     * @param SwanTransactionInterceptor the  transaction interceptor
     */
    protected void setSwanTransactionInterceptor(final SwanTransactionHandler swanTransactionInterceptor) {
        this.swanTransactionHandler = swanTransactionHandler;
    }

    /**
     * 切入点 
     */
    @Pointcut("@annotation(org.zoo.woodpecker.annotation.Woodpecker)")
    public void woodpeckerInterceptor() {
    }

    /**
     * 环绕通知
     * @param proceedingJoinPoint proceedingJoinPoint
     * @return Object object
     * @throws Throwable Throwable
     */
    @Around("woodpeckerInterceptor()")
    public Object interceptMethod(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return swanTransactionHandler.handler(proceedingJoinPoint);
    } 
}
