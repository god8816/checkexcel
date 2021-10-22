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

package com.zoo.swan.demo.springmvc.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zoo.swan.annotation.Swan;
import org.zoo.swan.annotation.TransTypeEnum;

/**
 * AccountController.
 * @author dzc
 */
@RestController
public class SpringMvcController {


    @RequestMapping("/get")
    @Swan(TransTypeEnum.QUERY)
    public String get(String userId) {
        return "123";
    }
    
    
    @RequestMapping("/save")
    @Swan(value=TransTypeEnum.SAVE,errorMsg="小狗狗别重复保存")
    public String save(String userId) {
    	    //修改验证不通过
        return "14543234";
    }




}
