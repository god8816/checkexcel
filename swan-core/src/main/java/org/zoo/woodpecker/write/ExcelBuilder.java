package org.zoo.woodpecker.write;

import java.util.ArrayList;
import java.util.List;
/**
 * @author dzc
 */
public class ExcelBuilder {

	private List<Object> list = new ArrayList<Object>();
 
	public ExcelBuilder list(List data, Class head) {
		this.list = data;
		return this;
	}

 
	public <T> List<T> doRightRecord() {
		// TODO Auto-generated method stub
		return (List<T>) list;
	}

 
	public <T> List<T> doErrorRecord() {
		// TODO Auto-generated method stub
		return (List<T>) list;
	}
	

}
