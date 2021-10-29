package org.zoo.woodpecker.handler.impl;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.zoo.woodpecker.annotation.EmptyCheckType;
import org.zoo.woodpecker.handler.ExcelCheckServer;
import org.zoo.woodpecker.util.ClassUtils.FieldCache;
import org.zoo.woodpecker.util.StringUtil;


/**
 * 手机号校验
 * @author dzc
 */
public class PhoneCheckServerImpl extends ExcelCheckServer{

	public Boolean doCheck(FieldCache fieldCache) {
		String value = StringUtil.toString(fieldCache.getValue());
		String regularExpVlue = fieldCache.getRegularExp();
		if(StringUtil.isEmpty(regularExpVlue)) {
			regularExpVlue = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		}
		
		if(StringUtil.isNotEmpty(value)) {
			Pattern p = Pattern.compile(regularExpVlue);
			Matcher m = p.matcher(value); 
			Boolean status =  m.matches();
			return status;
		}else {
			if(EmptyCheckType.empty.name().equals(fieldCache.getCheckEmptyType().name())) {
				return true;
			}else {
				return false;
			}
		}
	}

	 
}
