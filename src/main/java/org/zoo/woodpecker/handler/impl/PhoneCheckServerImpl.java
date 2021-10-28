package org.zoo.woodpecker.handler.impl;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.zoo.woodpecker.handler.ExcelCheckServer;
import org.zoo.woodpecker.util.ClassUtils.FieldCache;
import org.zoo.woodpecker.util.StringUtil;


/**
 * 手机号校验
 * @author dzc
 */
public class PhoneCheckServerImpl extends ExcelCheckServer{

	public Boolean doCheck(FieldCache fieldCache) {
		String phone = StringUtil.toString(fieldCache.getValue());
		if(StringUtil.isEmpty(phone)) {
			return false;
		}
		
		// notnull
	
		
		
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(phone); 
		Boolean status =  m.matches();
		return status;
	}

	 
}
