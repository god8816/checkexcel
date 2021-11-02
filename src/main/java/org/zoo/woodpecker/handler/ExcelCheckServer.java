package org.zoo.woodpecker.handler;

import org.zoo.woodpecker.util.ClassUtils;
import org.zoo.woodpecker.util.ClassUtils.FieldCache;

/**
 * 
 * @author dzc
 */
public abstract class ExcelCheckServer {
	
	/**
	 * 字段检查
	 * @param fieldCache 
	 * @param o
	 * */
	public abstract Boolean doCheck(Object o,FieldCache fieldCache);

	/**
	 * 获取错误提示语并打印
	 * @param fieldCache 
	 * */
	public void printRecord(Object o,String errorMsg, FieldCache fieldCache) {
		ClassUtils.writeErrorInfoField(o,errorMsg, fieldCache);
	}
}
