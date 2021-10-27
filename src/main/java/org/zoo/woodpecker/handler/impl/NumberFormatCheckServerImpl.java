package org.zoo.woodpecker.handler.impl;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.zoo.woodpecker.handler.ExcelCheckServer;
import org.zoo.woodpecker.util.ClassUtils.FieldCache;
import org.zoo.woodpecker.util.StringUtil;


/**
 * 任意数字格式校验
 * @author dzc
 */
public class NumberFormatCheckServerImpl extends ExcelCheckServer{

	public Boolean doCheck(FieldCache fieldCache) {

		String number = StringUtil.toString(fieldCache.getValue());
		if(StringUtil.isEmpty(number)) {
			return false;
		}
		Pattern p = Pattern.compile("^\\d+$");
		Matcher m = p.matcher(number); 
		Boolean status =  m.matches();
		return status;
	}

	 
}
