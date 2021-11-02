package org.zoo.woodpecker.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.zoo.woodpecker.annotation.BusinessCheckType;
import org.zoo.woodpecker.annotation.Woodpecker;
import org.zoo.woodpecker.bean.ExcelPrentBean;
import org.zoo.woodpecker.exception.WoodpeckerRuntimeException;
import org.zoo.woodpecker.handler.ExcelCheckServer;
import org.zoo.woodpecker.handler.impl.CnFormatCheckServerImpl;
import org.zoo.woodpecker.handler.impl.EnFormatCheckServerImpl;
//import org.zoo.woodpecker.handler.impl.DateFormatCheckServerImpl;
import org.zoo.woodpecker.handler.impl.IdCardCheckServerImpl;
import org.zoo.woodpecker.handler.impl.NumberFormatCheckServerImpl;
//import org.zoo.woodpecker.handler.impl.NumberFormatCheckServerImpl;
import org.zoo.woodpecker.handler.impl.PhoneCheckServerImpl;
//import org.zoo.woodpecker.handler.impl.TimeFormatCheckServerImpl;
import org.zoo.woodpecker.util.ClassUtils;
import org.zoo.woodpecker.util.ClassUtils.FieldCache;
import org.zoo.woodpecker.util.StringUtil;
import org.zoo.woodpecker.util.WoodpeckerReflector;
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
					if(!woodpecker.selfCheckClassName().equals(StringUtil.class) && StringUtil.isEmpty(woodpecker.selfCheckMethodName()) ) {
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
		for (FieldCache fieldCache : fieldCacheList) {
			//手机号校验
			if(BusinessCheckType.phone.equals(fieldCache.getCheckType())) {
				ExcelCheckServer excelCheckServer = new PhoneCheckServerImpl();
				if(excelCheckServer.doCheck(fieldCache) == false) {
					excelCheckServer.printRecord(o,fieldCache);
				}
			}
			
			//身份证号校验
			if(BusinessCheckType.idcard.equals(fieldCache.getCheckType())) {
				ExcelCheckServer excelCheckServer = new IdCardCheckServerImpl();
				if(excelCheckServer.doCheck(fieldCache) == false) {
					excelCheckServer.printRecord(o,fieldCache);
				}
			}
			
			//日期格式校验
//			if(BusinessCheckType.dateFormat.equals(fieldCache.getCheckType())) {
//				ExcelCheckServer excelCheckServer = new DateFormatCheckServerImpl();
//				if(excelCheckServer.doCheck(fieldCache) == false) {
//					excelCheckServer.printRecord(o,fieldCache);
//				}
//			}
			
			//时间格式校验
//			if(BusinessCheckType.timeFormat.equals(fieldCache.getCheckType())) {
//				ExcelCheckServer excelCheckServer = new TimeFormatCheckServerImpl();
//				if(excelCheckServer.doCheck(fieldCache) == false) {
//					excelCheckServer.printRecord(o,fieldCache);
//				}
//			}
			
			//数字格式校验
			if(BusinessCheckType.number.equals(fieldCache.getCheckType())) {
				ExcelCheckServer excelCheckServer = new NumberFormatCheckServerImpl();
				if(excelCheckServer.doCheck(fieldCache) == false) {
					excelCheckServer.printRecord(o,fieldCache);
				}
			}
			
			//英文格式校验
			if(BusinessCheckType.en.equals(fieldCache.getCheckType())) {
				ExcelCheckServer excelCheckServer = new EnFormatCheckServerImpl();
				if(excelCheckServer.doCheck(fieldCache) == false) {
					excelCheckServer.printRecord(o,fieldCache);
				}
			}
			
			//中文格式校验
			if(BusinessCheckType.cn.equals(fieldCache.getCheckType())) {
				ExcelCheckServer excelCheckServer = new CnFormatCheckServerImpl();
				if(excelCheckServer.doCheck(fieldCache) == false) {
					excelCheckServer.printRecord(o,fieldCache);
				}
			}
			
			// TODO 待完成其他场景 ........
			
			
		   //自定义校验
		   if(!WoodpeckerReflector.execute(o,fieldCache)) {
				ClassUtils.writeErrorInfoField(o, fieldCache);
		   }
		}
		return o;
	}
	
	
	

}
