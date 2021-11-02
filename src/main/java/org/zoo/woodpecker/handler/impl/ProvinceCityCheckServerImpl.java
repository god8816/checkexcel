package org.zoo.woodpecker.handler.impl;
import org.zoo.woodpecker.annotation.EmptyCheckType;
import org.zoo.woodpecker.handler.ExcelCheckServer;
import org.zoo.woodpecker.util.ClassUtils.FieldCache;
import org.zoo.woodpecker.util.RegionUtil;
import org.zoo.woodpecker.util.StringUtil;


/**
 * 省市校验
 * @author dzc
 */
public class ProvinceCityCheckServerImpl extends ExcelCheckServer{

	public Boolean doCheck(Object o,FieldCache fieldCache) {
		String value = StringUtil.toString(fieldCache.getValue());
		if(StringUtil.isNotEmpty(value)) {
			return RegionUtil.provinceCheck(fieldCache.getValue());
		}else {
			if(EmptyCheckType.empty.name().equals(fieldCache.getCheckEmptyType().name())) {
				return true;
			}else {
				return false;
			}
		}
	}
}
