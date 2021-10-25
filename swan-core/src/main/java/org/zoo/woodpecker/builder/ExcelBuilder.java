package org.zoo.woodpecker.builder;

import java.util.ArrayList;
import java.util.List;

import org.zoo.woodpecker.util.ClassUtils;
import org.zoo.woodpecker.util.ClassUtils.FieldCache;
/**
 * @author dzc
 */
public class ExcelBuilder {

	private List<Object> list = new ArrayList<Object>();
	
	private FieldCache fieldCache;
	
    private Class<?> clazz;
    
 
	public ExcelBuilder list(List data, Class head) {
		this.list = data;
		this.clazz = head;
		fieldCache = ClassUtils.declaredFields(clazz);
		return this;
	}

 
	public <T> List<T> doRightRecord() {
		List<T> rightRecordList = new ArrayList<T>();
		
	
		return (List<T>) list;
	}

 
	public <T> List<T> doErrorRecord() {
		List<T> rightRecordList = new ArrayList<T>();
		return (List<T>) list;
	}
	

}
