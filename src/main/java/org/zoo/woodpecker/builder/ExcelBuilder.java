package org.zoo.woodpecker.builder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zoo.woodpecker.annotation.BusinessCheckType;
import org.zoo.woodpecker.annotation.RepeatCheckType;
import org.zoo.woodpecker.annotation.Woodpecker;
import org.zoo.woodpecker.bean.ExcelPrentBean;
import org.zoo.woodpecker.exception.WoodpeckerRuntimeException;
import org.zoo.woodpecker.handler.ExcelCheckServer;
import org.zoo.woodpecker.handler.impl.CnFormatCheckServerImpl;
import org.zoo.woodpecker.handler.impl.EmailFormatCheckServerImpl;
import org.zoo.woodpecker.handler.impl.EnFormatCheckServerImpl;
import org.zoo.woodpecker.handler.impl.IdCardCheckServerImpl;
import org.zoo.woodpecker.handler.impl.NumberFormatCheckServerImpl;
import org.zoo.woodpecker.handler.impl.PhoneCheckServerImpl;
import org.zoo.woodpecker.handler.impl.ProvinceCheckServerImpl;
import org.zoo.woodpecker.handler.impl.ProvinceCityAreaCheckServerImpl;
import org.zoo.woodpecker.handler.impl.ProvinceCityCheckServerImpl;
import org.zoo.woodpecker.util.ClassUtils;
import org.zoo.woodpecker.util.ClassUtils.FieldCache;
import org.zoo.woodpecker.util.Constant;
import org.zoo.woodpecker.util.RegionUtil;
import org.zoo.woodpecker.util.StringUtil;
import org.zoo.woodpecker.util.WoodpeckerReflector;


/**
 * @author dzc
 * @param excel处理
 */
public class ExcelBuilder<T> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelBuilder.class);

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
			Object obj = recordCheck(o,fieldCacheList,dataList);
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
	 * @param dataList 
	 * @return 
	 * */
	private Object recordCheck(Object o,List<FieldCache> fieldCacheList, List<Object> dataList) { 
		for (FieldCache fieldCache : fieldCacheList) {
			//手机号校验
			if(BusinessCheckType.phone.equals(fieldCache.getCheckType())) {
				ExcelCheckServer excelCheckServer = new PhoneCheckServerImpl();
				if(excelCheckServer.doCheck(o,fieldCache) == false) {
					excelCheckServer.printRecord(o,Constant.phoneMsg,fieldCache);
				}
			}
			
			//身份证号校验
			if(BusinessCheckType.idcard.equals(fieldCache.getCheckType())) {
				ExcelCheckServer excelCheckServer = new IdCardCheckServerImpl();
				if(excelCheckServer.doCheck(o,fieldCache) == false) {
					excelCheckServer.printRecord(o,Constant.idcardMsg,fieldCache);
				}
			}
			
			//数字格式校验
			if(BusinessCheckType.number.equals(fieldCache.getCheckType())) {
				ExcelCheckServer excelCheckServer = new NumberFormatCheckServerImpl();
				if(excelCheckServer.doCheck(o,fieldCache) == false) {
					excelCheckServer.printRecord(o,"",fieldCache);
				}
			}
			
			//英文格式校验
			if(BusinessCheckType.en.equals(fieldCache.getCheckType())) {
				ExcelCheckServer excelCheckServer = new EnFormatCheckServerImpl();
				if(excelCheckServer.doCheck(o,fieldCache) == false) {
					excelCheckServer.printRecord(o,"",fieldCache);
				}
			}
			
			//中文格式校验
			if(BusinessCheckType.cn.equals(fieldCache.getCheckType())) {
				ExcelCheckServer excelCheckServer = new CnFormatCheckServerImpl();
				if(excelCheckServer.doCheck(o,fieldCache) == false) {
					excelCheckServer.printRecord(o,"",fieldCache);
				}
			}

			//邮箱格式校验
			if(BusinessCheckType.email.equals(fieldCache.getCheckType())) {
				ExcelCheckServer excelCheckServer = new EmailFormatCheckServerImpl();
				if(excelCheckServer.doCheck(o,fieldCache) == false) {
					excelCheckServer.printRecord(o,Constant.emailMsg,fieldCache);
				}
			}
			
			if(BusinessCheckType.province.equals(fieldCache.getCheckType()) || BusinessCheckType.city.equals(fieldCache.getCheckType()) || BusinessCheckType.area.equals(fieldCache.getCheckType()) || BusinessCheckType.street.equals(fieldCache.getCheckType())) {
				
				//省校验
				if(BusinessCheckType.province.equals(fieldCache.getCheckType())) {
					ExcelCheckServer excelCheckServer = new ProvinceCheckServerImpl();
					if(excelCheckServer.doCheck(o,fieldCache) == false) {
						excelCheckServer.printRecord(o,Constant.provinceMsg,fieldCache);
					}
				}
				
				//省、市校验
				if(BusinessCheckType.city.equals(fieldCache.getCheckType())) {
					ExcelCheckServer excelCheckServer = new ProvinceCityCheckServerImpl();
					if(excelCheckServer.doCheck(o,fieldCache) == false) {
						excelCheckServer.printRecord(o,Constant.provinceCityMsg,fieldCache);
					}
				}
				
				//省、市、区校验
				if(BusinessCheckType.area.equals(fieldCache.getCheckType())) {
					ExcelCheckServer excelCheckServer = new  ProvinceCityAreaCheckServerImpl();
					if(excelCheckServer.doCheck(o,fieldCache) == false) {
						excelCheckServer.printRecord(o,Constant.provinceCityAreaMsg,fieldCache);
					}
				}
				
				//省、市、区、街道校验
//				if(BusinessCheckType.street.equals(fieldCache.getCheckType())) {
//					ExcelCheckServer excelCheckServer = new EmailFormatCheckServerImpl();
//					if(excelCheckServer.doCheck(o,fieldCache) == false) {
//						excelCheckServer.printRecord(o,Constant.phoneMsg,fieldCache);
//					}
//				}
			}
			
			//判断列重复
			if(RepeatCheckType.notRepeat.equals(fieldCache.getCheckRepeatType())) {
				for (Object obj : dataList) {
					Class<?> clazz = obj.getClass();
			        if (clazz == null) {
			            return null;
			        }
			        
			        try {
			            Field fileInfo = obj.getClass().getDeclaredField(fieldCache.getName());
			            fileInfo.setAccessible(true);
			            if(fieldCache.getValue().equals(fileInfo.get(obj))) {
			            	ClassUtils.writeErrorInfoField(o,Constant.repeatMsg,fieldCache);
			            }
			            
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
						LOGGER.error("未继承ExcelPrentBean。");
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						LOGGER.error("反射参数获取。");
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						LOGGER.error("反射读权限异常。");
					}   
				}
			}
			
		
			
			
		   //自定义校验
		   if(!WoodpeckerReflector.execute(o,fieldCache)) {
				ClassUtils.writeErrorInfoField(o,"", fieldCache);
		   }
		}
		return o;
	}
	
	
	

}
