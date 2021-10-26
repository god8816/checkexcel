package org.zoo.woodpecker.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.zoo.woodpecker.annotation.CheckType;
import org.zoo.woodpecker.annotation.Woodpecker;
import org.zoo.woodpecker.bean.ExcelPrentBean;
import org.zoo.woodpecker.exception.WoodpeckerRuntimeException;
import org.zoo.woodpecker.handler.ExcelCheckServer;
import org.zoo.woodpecker.handler.impl.PhoneCheckServerImpl;
import org.zoo.woodpecker.util.ClassUtils;
import org.zoo.woodpecker.util.ClassUtils.FieldCache;
import org.zoo.woodpecker.util.StringUtil;
/**
 * @author dzc
 * @param excel处理
 */
public class ExcelBuilder<T> {

    private volatile static ExcelBuilder singleton;  
    
    private List<Object> rightRecordList = new ArrayList<Object>();
	
    private List<Object> errorRecordList = new ArrayList<Object>();
    
	/**
	 * 构建单利模式ExcelBuilder
	 * */
    public static ExcelBuilder list(List dataList, Class className) {  
	    if (singleton == null) {  
	        synchronized (ExcelBuilder.class) {  
		        if (singleton == null) {  
		            singleton = new ExcelBuilder().createExcelBuilder(dataList, className);  
		        }  
	        }  
	    }  
	    return singleton;  
    }  
    
    /**
     * 创建ExcelBuilder
     * */
	public ExcelBuilder createExcelBuilder(List dataList, Class className) {
		doRecord(dataList);
		return this;
	}
	
    /**
     * 记录处理
     * */
	private void doRecord(List<Object> dataList) {
		List<Woodpecker> woodpeckerList = new ArrayList<>();
		for (Object o : dataList) {
			List<FieldCache> fieldCacheList = ClassUtils.declaredFields(o);
			for (FieldCache fieldCache : fieldCacheList) {
				Woodpecker woodpecker = fieldCache.getField().getAnnotation(Woodpecker.class);
				if(Objects.nonNull(woodpecker)) {
					if(Objects.nonNull(woodpecker.selfCheckClassName()) && StringUtil.isEmpty(woodpecker.selfCheckMethodName()) ) {
						throw new WoodpeckerRuntimeException("selfCheckMethodName和selfCheckClassName只能同时存在");
					}
					if(Objects.isNull(woodpecker.selfCheckClassName()) && StringUtil.isNotEmpty(woodpecker.selfCheckMethodName()) ) {
						throw new WoodpeckerRuntimeException("selfCheckMethodName和selfCheckClassName只能同时存在");					
					}
					woodpeckerList.add(woodpecker);
				}
			}
			
			//记录是否正确校验
			Object obj = recordCheck(o,fieldCacheList);
			ExcelPrentBean excelBean = (ExcelPrentBean)o;
			if(StringUtil.isEmpty(excelBean.getErrorInfo())) {
				rightRecordList.add(obj);
			}else {
				errorRecordList.add(obj);
			}
		}
	}
	

	public List<T> doRightRecord() {
		return (List<T>) rightRecordList;
	}

 
	public List<T> doErrorRecord() {
		return (List<T>) errorRecordList;
	}
	
	

	/**
	 * 字段检查
	 * @return 
	 * */
	private Object recordCheck(Object o,List<FieldCache> fieldCacheList) { 
		//手机号校验
		List<FieldCache> fieldCachePhoneList = fieldCacheList.stream().filter(x->CheckType.phone.equals(x.getCheckType())).collect(Collectors.toList());
		for (FieldCache fieldCachePhone : fieldCachePhoneList) {
			if(Objects.nonNull(fieldCachePhone)) {
				ExcelCheckServer excelCheckServer = new PhoneCheckServerImpl();
				if(excelCheckServer.doCheck(fieldCachePhone) == false) {
					excelCheckServer.printRecord(o,fieldCachePhone);
				}
			}
		}
		
		
		return o;
	}
	
	
	

}
