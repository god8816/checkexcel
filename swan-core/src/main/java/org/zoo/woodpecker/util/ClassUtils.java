package org.zoo.woodpecker.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import lombok.Data;

/**
 * @author dzc
 * 反射核心类
 **/
public class ClassUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtils.class);

    /**
     * 获取类Field
     * */
    public static List<FieldCache> declaredFields(Object obj) {
    	Class<?> clazz = obj.getClass();
        if (clazz == null) {
            return null;
        }
        
        List<Field> tempFieldList = new ArrayList<>();
        Class<?> tempClass = clazz;
        while (tempClass != null) {
            Collections.addAll(tempFieldList, tempClass.getDeclaredFields());
            tempClass = tempClass.getSuperclass();
        }

        List<FieldCache> fieldList = new ArrayList<>();
        for (Field field : tempFieldList) {
            declaredOneField(field,obj,fieldList);
        }
        return fieldList;
    }
    
    /**
     * 获取Field注释
     * */
    private static void declaredOneField(Field field,Object obj,List<FieldCache> fieldList) {
    	try {
	       	 FieldCache fieldCache = new FieldCache();
	       	 field.setAccessible(true);
	       	 fieldCache.setField(field);
	       	 fieldCache.setName(field.getName());
	       	 fieldCache.setValue(field.get(obj));
	         fieldList.add(fieldCache);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取字段信息异常，异常信息："+ JSON.toJSONString(e));
		} 
    }
    
    /**
     * Field缓存
     * */
    @Data
    public static class FieldCache {
		/**
    	 * 字段Field
    	 * */
        private Field field;
        
        /**
    	 * 字段name
    	 * */
        private String name;
        
        /**
    	 * 字段value
    	 * */
        private Object value;
    }

}
