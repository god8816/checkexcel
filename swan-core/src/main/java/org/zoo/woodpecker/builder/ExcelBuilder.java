package org.zoo.woodpecker.builder;

import java.util.ArrayList;
import java.util.List;

import org.zoo.woodpecker.util.ClassUtils;
/**
 * @author dzc
 */
public class ExcelBuilder {

	private List<Object> list = new ArrayList<Object>();
	
    private Class<?> clazz;
 
	public ExcelBuilder list(List data, Class head) {
		this.list = data;
		this.clazz = head;
		return this;
	}

 
	public <T> List<T> doRightRecord() {
		// TODO Auto-generated method stub
		ClassUtils.declaredFields(clazz);
		return (List<T>) list;
	}

 
	public <T> List<T> doErrorRecord() {
		// TODO Auto-generated method stub
		return (List<T>) list;
	}
	

}
