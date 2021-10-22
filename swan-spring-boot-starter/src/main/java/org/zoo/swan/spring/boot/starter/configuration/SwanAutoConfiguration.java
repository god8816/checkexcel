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

package org.zoo.swan.spring.boot.starter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.zoo.swan.core.bootstrap.SwanTransactionBootstrap;
import org.zoo.swan.core.service.SwanInitService;
import org.zoo.swan.spring.boot.starter.config.SwanConfigProperties;

/**
 * SwanAutoConfiguration is spring boot starter handler.
 *
 * @author dzc
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableConfigurationProperties({SwanConfigProperties.class})
@ComponentScan(basePackages = {"org.zoo.swan"})
public class SwanAutoConfiguration {

    private final SwanConfigProperties swanConfigProperties;

    @Autowired(required = false)
    public SwanAutoConfiguration(SwanConfigProperties swanConfigProperties) {
        this.swanConfigProperties = swanConfigProperties;
    }

    @Bean
    @Qualifier("swanTransactionBootstrap")
    @Primary
    public SwanTransactionBootstrap swanTransactionBootstrap(SwanInitService swanInitService) {
         final SwanTransactionBootstrap swanTransactionBootstrap = new SwanTransactionBootstrap(swanInitService);
         swanTransactionBootstrap.setApplicationName(swanConfigProperties.getApplicationName());
         swanTransactionBootstrap.setModeType(swanConfigProperties.getModeType());
         swanTransactionBootstrap.setStarted(swanConfigProperties.getStarted());
         swanTransactionBootstrap.setSwanRedisConfig(swanConfigProperties.getSwanRedisConfig());
         swanTransactionBootstrap.setTokenSupport(swanConfigProperties.getTokenSupport());
         swanTransactionBootstrap.setTokenKey(swanConfigProperties.getTokenKey());
         swanTransactionBootstrap.setRepositorySupport(swanConfigProperties.getRepositorySupport());
         return swanTransactionBootstrap;
    }
}
