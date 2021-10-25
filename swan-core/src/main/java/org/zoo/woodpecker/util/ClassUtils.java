package org.zoo.woodpecker.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.zoo.woodpecker.annotation.Woodpecker;

/**
 * @author dzc
 *
 **/
public class ClassUtils {
	
    public static final Map<Class<?>, FieldCache> FIELD_CACHE = new ConcurrentHashMap<>();

    /**
     * 获取类Field
     * */
    public static FieldCache declaredFields(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        
        return FIELD_CACHE.computeIfAbsent(clazz, key -> {
            List<Field> tempFieldList = new ArrayList<>();
            Class<?> tempClass = clazz;
            while (tempClass != null) {
                Collections.addAll(tempFieldList, tempClass.getDeclaredFields());
                tempClass = tempClass.getSuperclass();
            }
           
            Map<String, Field> fieldMap = new HashMap<String, Field>(16);
            for (Field field : tempFieldList) {
                declaredOneField(field,fieldMap);
            }
            return new FieldCache(fieldMap);
        });
    }
    
    /**
     * 获取Field注释
     * */
    private static void declaredOneField(Field field,Map<String, Field> fieldMap) {
    	    Woodpecker excelIgnore = field.getAnnotation(Woodpecker.class);
            if (excelIgnore != null) {
                fieldMap.put(field.getName(), field);
                return;
            }
    }
    
    /**
     * Field缓存
     * */
    private static class FieldCache {
    	
        private final Map<String, Field> fieldMap;

        public FieldCache(Map<String, Field> fieldMap) {
            this.fieldMap = fieldMap;
        }
        
        public Map<String, Field> getFieldMap() {
            return fieldMap;
        }
    }

}
