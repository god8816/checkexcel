package org.zoo.woodpecker.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zoo.woodpecker.annotation.BusinessCheckType;
import org.zoo.woodpecker.annotation.EmptyCheckType;
import org.zoo.woodpecker.annotation.Woodpecker;
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
    		 Woodpecker woodpecker = field.getAnnotation(Woodpecker.class);
	       	 FieldCache fieldCache = new FieldCache();
	       	 field.setAccessible(true);
	       	 fieldCache.setField(field);
	       	 fieldCache.setName(field.getName());
	       	 fieldCache.setValue(field.get(obj));
	       	 if(Objects.nonNull(woodpecker)) {
	       		fieldCache.setCheckEmptyType(woodpecker.emptyCheckType());
	       		fieldCache.setCheckType(woodpecker.commonCheck());
	        	fieldCache.setRegularExp(woodpecker.regularExp());
	        	fieldCache.setContainParam(woodpecker.containParam());
	        	fieldCache.setNotContainParam(woodpecker.notContainParam());
	       	 }
	         fieldList.add(fieldCache);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取字段信息异常，异常信息："+ JSON.toJSONString(e));
		} 
    }
    
    /**
     * 写入异常字段提示语
     * */
    public static Object writeErrorInfoField(Object obj,FieldCache fieldCache) {
    	Class<?> clazz = obj.getClass();
        if (clazz == null) {
            return null;
        }
        
        try {
            Field errorInfo = obj.getClass().getSuperclass().getDeclaredField("errorInfo");
            errorInfo.setAccessible(true);
            String errorMassge = StringUtil.toString(errorInfo.get(obj)); //获取历史错误提示
            Woodpecker woodpecker = fieldCache.getField().getAnnotation(Woodpecker.class);
            if(Objects.nonNull(woodpecker)) {
                String newErrorMassge = new StringBuilder(errorMassge).append(woodpecker.errorMsg()).append(";").toString();
    			errorInfo.set(obj,newErrorMassge );
            }
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			LOGGER.error("未继承ExcelPrentBean。");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			LOGGER.error("反射参数获取。");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			LOGGER.error("反射写权限异常。");
		} 
        return obj;
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
        
        /**
         * 校验类型
         * */
        private BusinessCheckType checkType;
        
        /**
         * 判断空、非空
         * */
        private EmptyCheckType checkEmptyType;
        
    	/**
         * 自定义正则表达式校验参数 ps：格式要求是正则表达式
         */
        private String regularExp;
    	
    	/**
         * 包含那些参数
         * @return the string
         */
        private String[] containParam;
    	
    	/**
         * 不包含那些参数
         * @return the string
         */
        private String[] notContainParam;
    }

}
