package org.zoo.woodpecker.handler.impl;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.zoo.woodpecker.handler.ExcelCheckServer;
import org.zoo.woodpecker.util.ClassUtils.FieldCache;
import org.zoo.woodpecker.util.StringUtil;


/**
 * 日期格式校验
 * @author dzc
 */
public class DateFormatCheckServerImpl extends ExcelCheckServer{

	public Boolean doCheck(FieldCache fieldCache) {
		if(fieldCache.getValue() instanceof String) {
			
		}
		
		if(fieldCache.getValue() instanceof Date) {
			
		}
		
		String phone = StringUtil.toString(fieldCache.getValue());
		if(StringUtil.isEmpty(phone)) {
			return false;
		}
		Pattern p = Pattern.compile("^(\\d{18,18}|\\d{15,15}|\\d{17,17}X)$");
		Matcher m = p.matcher(phone); 
		Boolean status =  m.matches();
		return status;
	}

	 
}
