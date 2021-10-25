package org.zoo.woodpecker.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.zoo.woodpecker.annotation.CheckType;
import org.zoo.woodpecker.annotation.Woodpecker;
import org.zoo.woodpecker.handler.ExcelCheckServer;
import org.zoo.woodpecker.handler.impl.PhoneCheckServerImpl;
import org.zoo.woodpecker.util.ClassUtils;
import org.zoo.woodpecker.util.ClassUtils.FieldCache;
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
		List<T> rightRecordList = new ArrayList<T>();
		
		for (Object o : list) {
			List<FieldCache> fieldCacheList = ClassUtils.declaredFields(o);
			for (FieldCache fieldCache : fieldCacheList) {
				Woodpecker woodpecker = fieldCache.getField().getAnnotation(Woodpecker.class);
				if(Objects.nonNull(woodpecker)) {
					CheckType checkType = woodpecker.commonCheck();
					recordCheck(rightRecordList,o,fieldCache,checkType,true);
				}
			}
		}
		return (List<T>) rightRecordList;
	}

 
	public <T> List<T> doErrorRecord() {
		List<T> errorRecordList = new ArrayList<T>();
		for (Object o : list) {
			List<FieldCache> fieldCacheList = ClassUtils.declaredFields(o);
			for (FieldCache fieldCache : fieldCacheList) {
				Woodpecker woodpecker = fieldCache.getField().getAnnotation(Woodpecker.class);
				if(Objects.nonNull(woodpecker)) {
					CheckType checkType = woodpecker.commonCheck();
					recordCheck(errorRecordList,o,fieldCache,checkType,false);
				}
			}
		}
		return (List<T>) errorRecordList;
	}
	
	/**
	 * 字段检查
	 * */
	@SuppressWarnings("unchecked")
	private <T>  void recordCheck(List<T> rightRecordList,Object o,FieldCache fieldCache,CheckType checkType,boolean statusRecord) {
		boolean status = true;
		//手机号校验
		if(checkType.equals(CheckType.phone)) {
			ExcelCheckServer excelCheckServer = new PhoneCheckServerImpl();
			if(statusRecord == excelCheckServer.doCheck(fieldCache) == false) {
				excelCheckServer.printRecord(o,fieldCache);
			}
		}
		
		//身份证校验
		if(checkType.equals(CheckType.idcard)) {
			if(true) {
				rightRecordList.add((T)o);
				//TODO
			}
		} 
		
		//时间格式
		if(checkType.equals(CheckType.timeFormat)) {
			if(true) {
				rightRecordList.add((T)o);
				//TODO
			}
		} 
		
		//邮箱格式
		if(checkType.equals(CheckType.email)) {
			if(true) {
				rightRecordList.add((T)o);
				//TODO
			}
		} 
		
		//是否是数字格式
		if(checkType.equals(CheckType.number)) {
			if(true) {
				rightRecordList.add((T)o);
				//TODO
			}
		} 
		
		//是否是字母
		if(checkType.equals(CheckType.en)) {
			if(true) {
				rightRecordList.add((T)o);
				//TODO
			}
		} 
		
		//是否是中文
		if(checkType.equals(CheckType.cn)) {
			if(true) {
				rightRecordList.add((T)o);
				//TODO
			}
		} 
		
		//是否是省
		if(checkType.equals(CheckType.province)) {
			if(true) {
				rightRecordList.add((T)o);
				//TODO
			}
		} 
		
		//是否是市
		if(checkType.equals(CheckType.city)) {
			if(true) {
				rightRecordList.add((T)o);
				//TODO
			}
		} 
		
		//是否是市
		if(checkType.equals(CheckType.area)) {
			if(true) {
				rightRecordList.add((T)o);
				//TODO
			}
		} 
		
		//是否是街道
		if(checkType.equals(CheckType.street)) {
			if(true) {
				rightRecordList.add((T)o);
				//TODO
			}
		} 
		
		rightRecordList.add((T)o);
	}
	
	
	

}
