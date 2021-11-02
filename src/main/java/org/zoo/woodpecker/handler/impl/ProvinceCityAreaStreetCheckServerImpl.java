package org.zoo.woodpecker.handler.impl;
import java.util.List;
import java.util.Objects;

import org.zoo.woodpecker.annotation.BusinessCheckType;
import org.zoo.woodpecker.annotation.EmptyCheckType;
import org.zoo.woodpecker.annotation.Woodpecker;
import org.zoo.woodpecker.handler.ExcelCheckServer;
import org.zoo.woodpecker.util.ClassUtils.FieldCache;
import org.zoo.woodpecker.util.ClassUtils;
import org.zoo.woodpecker.util.RegionUtil;
import org.zoo.woodpecker.util.StringUtil;


/**
 * 省市校验
 * @author dzc
 */
public class ProvinceCityAreaStreetCheckServerImpl extends ExcelCheckServer{

	public Boolean doCheck(Object o,FieldCache fieldCache) {
		List<FieldCache> fieldCacheList = ClassUtils.declaredFields(o);
		Object provinceValue = null,cityValue = null,areaValue = null;
		for (FieldCache cityFieldCache : fieldCacheList) {
			Woodpecker woodpecker = cityFieldCache.getField().getAnnotation(Woodpecker.class);
			if(Objects.nonNull(woodpecker)) {
				if(BusinessCheckType.province.equals(fieldCache.getCheckType())) {
					provinceValue = fieldCache.getValue();
				} 
				
				if(BusinessCheckType.city.equals(fieldCache.getCheckType())) {
					cityValue = fieldCache.getValue();
				} 
				
				if(BusinessCheckType.area.equals(fieldCache.getCheckType())) {
					areaValue = fieldCache.getValue();
				} 
			}
		}
		
		String value = StringUtil.toString(fieldCache.getValue());
		if(StringUtil.isNotEmpty(value)) {
			return RegionUtil.provinceCityAreaStreetCheck(provinceValue,cityValue,areaValue,fieldCache.getValue());
		}else {
			if(EmptyCheckType.empty.name().equals(fieldCache.getCheckEmptyType().name())) {
				return true;
			}else {
				return false;
			}
		}
	}
}
