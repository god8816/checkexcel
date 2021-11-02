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
 * 省市区校验
 * @author dzc
 */
public class ProvinceCityAreaCheckServerImpl extends ExcelCheckServer{

	public Boolean doCheck(Object o,FieldCache fieldCache) {
		List<FieldCache> fieldCacheList = ClassUtils.declaredFields(o);
		Object provinceValue = null,cityValue = null;
		for (FieldCache cityFieldCache : fieldCacheList) {
			Woodpecker woodpecker = cityFieldCache.getField().getAnnotation(Woodpecker.class);
			if(Objects.nonNull(woodpecker)) {
				if(BusinessCheckType.province.equals(cityFieldCache.getCheckType())) {
					provinceValue = cityFieldCache.getValue();
				} 
				
				if(BusinessCheckType.city.equals(cityFieldCache.getCheckType())) {
					cityValue = cityFieldCache.getValue();
				} 
			}
		}
		
		String value = StringUtil.toString(fieldCache.getValue());
		if(StringUtil.isNotEmpty(value)) {
			return RegionUtil.provinceCityAreaCheck(provinceValue,cityValue,fieldCache.getValue());
		}else {
			if(EmptyCheckType.empty.name().equals(fieldCache.getCheckEmptyType().name())) {
				return true;
			}else {
				return false;
			}
		}
	}
}
