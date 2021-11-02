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
	BusinessCheckType commonCheck();
	
	/**
     * 字段空判断校验 校验类型：CheckType  默认不校验空
     * @return the string
     */
	EmptyCheckType emptyCheckType() default EmptyCheckType.empty; 
	
	/**
     * 字段空判断校验 校验类型：CheckType  默认不校验重复
     * @return the string
     */
	RepeatCheckType repeatCheckType() default RepeatCheckType.repeat; 
	
	/**
     * 自定义正则表达式校验参数 ps：格式要求是正则表达式
     * @return the string
     */
	String regularExp() default "";
	
	/**
     * 包含那些参数
     * @return the string
     */
	String[] containParam() default {};
	
	/**
     * 不包含那些参数
     * @return the string
     */
	String[] notContainParam() default {};
	
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
    String errorMsg() default "字段异常;";
}