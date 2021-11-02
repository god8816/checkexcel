package org.zoo.woodpecker.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zoo.woodpecker.bean.RegionBean;
import org.zoo.woodpecker.builder.ExcelBuilder;

import com.alibaba.fastjson.JSON;
import com.google.common.io.ByteStreams;

/**
 * 区域校验
 * @author dzc
 */
public class RegionUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegionUtil.class);

	
	private volatile static List<RegionBean> singletonRegionList;  
	
    /**加载省市区街道数据*/ 
	private static List<RegionBean> regionList(){
    	if (singletonRegionList == null) {  
	        synchronized (ExcelBuilder.class) {  
		        if (singletonRegionList == null) {  
		        	try {
		            	InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("region.json"); 
		    			String json = new String(ByteStreams.toByteArray(inputStream));
		    			singletonRegionList = JSON.parseArray(json, RegionBean.class);
		    		} catch (IOException e) {
		    			e.printStackTrace();
		    			LOGGER.error("省市区数据加载异常");
		    		} 
		        }  
	        }  
	    }  
	    return singletonRegionList; 
	} 
	
	
    /**加载省市区街道数据*/ 
	public static boolean provinceCheck(Object province){
		//名称校验
		if(province instanceof String) {
			long num =  regionList().stream().filter(x->x.getProvinceName().equals(province)).count();
			if(num>0) {
				return true;
			}
		}
		
		//编码校验
		if(province instanceof Integer) {
			long num =  regionList().stream().filter(x->x.getProvinceCode().equals(province)).count();
			if(num>0) {
				return true;
			}
		}
		return false;
	}
	
	
    
    public static void main(String[] args) {
    	regionList();
    }
}
