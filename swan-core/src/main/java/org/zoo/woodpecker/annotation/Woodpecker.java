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