package org.zoo.woodpecker.handler;

import org.zoo.woodpecker.util.ClassUtils.FieldCache;

/**
 * 
 * @author dzc
 * @since 2021-09-22
 */
public abstract class ExcelCheckServer {
	
	/**
	 * 字段检查
	 * @param fieldCache 
	 * */
	public abstract Boolean doCheck(FieldCache fieldCache);

	/**
	 * 获取错误提示语并打印
	 * @param fieldCache 
	 * */
	public void printRecord(Object o, FieldCache fieldCache) {
		// TODO Auto-generated method stub
		
	}
}
