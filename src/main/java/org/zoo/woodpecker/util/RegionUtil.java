package org.zoo.woodpecker.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

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
	
	
    /**省判断*/ 
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
	
	
    /**市判断*/ 
	public static boolean provinceCityCheck(Object province,Object city){
		//名称校验
        if(city instanceof String) {
			Stream<RegionBean> stream = regionList().stream();
			if(Objects.nonNull(province)) {
				stream.filter(x->x.getProvinceName().equals(province));
			}
			if(Objects.nonNull(city)) {
				stream.filter(x->x.getCityName().equals(city));
			}
			long num =  stream.count();
			if(num>0) {
				return true;
			}
		}
			
		//编码校验
		if(city instanceof Integer) {
			Stream<RegionBean> stream = regionList().stream();
			if(Objects.nonNull(province)) {
				stream.filter(x->x.getProvinceCode().equals(province));
			}
			if(Objects.nonNull(city)) {
				stream.filter(x->x.getCityCode().equals(city));
			}
			
			long num =  stream.count();
			if(num>0) {
				return true;
			}
		}
		return false;
	}
	

	public static Boolean provinceCityAreaCheck(Object province,Object city,Object area) {
		//名称校验
        if(area instanceof String) {
			Stream<RegionBean> stream = regionList().stream();
			if(Objects.nonNull(province)) {
				stream.filter(x->x.getProvinceName().equals(province));
			}
			if(Objects.nonNull(city)) {
				stream.filter(x->x.getCityName().equals(city));
			}
			if(Objects.nonNull(area)) {
				stream.filter(x->x.getAreaName().equals(area));
			}
			long num =  stream.count();
			if(num>0) {
				return true;
			}
		}
			
		//编码校验
		if(area instanceof Integer) {
			Stream<RegionBean> stream = regionList().stream();
			if(Objects.nonNull(province)) {
				stream.filter(x->x.getProvinceCode().equals(province));
			}
			if(Objects.nonNull(city)) {
				stream.filter(x->x.getCityCode().equals(city));
			}
			if(Objects.nonNull(area)) {
				stream.filter(x->x.getAreaCode().equals(area));
			}
			
			long num =  stream.count();
			if(num>0) {
				return true;
			}
		}
		return false;
	}
	
	public static Boolean provinceCityAreaStreetCheck(Object province,Object city,Object area,Object street) {
		//名称校验
        if(area instanceof String) {
			Stream<RegionBean> stream = regionList().stream();
			if(Objects.nonNull(province)) {
				stream.filter(x->x.getProvinceName().equals(province));
			}
			if(Objects.nonNull(city)) {
				stream.filter(x->x.getCityName().equals(city));
			}
			if(Objects.nonNull(area)) {
				stream.filter(x->x.getAreaName().equals(area));
			}
			if(Objects.nonNull(street)) {
				stream.filter(x->x.getStreetName().equals(street));
			}
			long num =  stream.count();
			if(num>0) {
				return true;
			}
		}
			
		//编码校验
		if(area instanceof Integer) {
			Stream<RegionBean> stream = regionList().stream();
			if(Objects.nonNull(province)) {
				stream.filter(x->x.getProvinceCode().equals(province));
			}
			if(Objects.nonNull(city)) {
				stream.filter(x->x.getCityCode().equals(city));
			}
			if(Objects.nonNull(area)) {
				stream.filter(x->x.getAreaCode().equals(area));
			}
			if(Objects.nonNull(street)) {
				stream.filter(x->x.getStreetCode().equals(street));
			}
			
			long num =  stream.count();
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
