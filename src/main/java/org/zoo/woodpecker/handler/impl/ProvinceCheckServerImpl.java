package org.zoo.woodpecker.handler.impl;
import org.zoo.woodpecker.handler.ExcelCheckServer;
import org.zoo.woodpecker.util.ClassUtils.FieldCache;
import org.zoo.woodpecker.util.RegionUtil;


/**
 * 省校验
 * @author dzc
 */
public class ProvinceCheckServerImpl extends ExcelCheckServer{

	public Boolean doCheck(Object o,FieldCache fieldCache) {
		return RegionUtil.provinceCheck(fieldCache.getValue());
	}
	
	
}
