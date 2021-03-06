package org.zoo.woodpecker.handler.impl;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.zoo.woodpecker.annotation.EmptyCheckType;
import org.zoo.woodpecker.handler.ExcelCheckServer;
import org.zoo.woodpecker.util.ClassUtils.FieldCache;
import org.zoo.woodpecker.util.StringUtil;


/**
 * 身份证号校验
 * @author dzc
 */
public class IdCardCheckServerImpl extends ExcelCheckServer{

	public Boolean doCheck(Object o,FieldCache fieldCache) {
		String value = StringUtil.toString(fieldCache.getValue());
		String regularExpVlue = fieldCache.getRegularExp();
		if(StringUtil.isEmpty(regularExpVlue)) {
			regularExpVlue = "^(\\d{18,18}|\\d{15,15}|\\d{17,17}X)$";
		}
		
		if(StringUtil.isNotEmpty(value)) {
			return regularExpCheck(regularExpVlue,value) && containParamCheck(fieldCache) && notContainParam(fieldCache);
		}else {
			if(EmptyCheckType.empty.name().equals(fieldCache.getCheckEmptyType().name())) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	/**
	 * 正则表达式校验
	 * */
	public Boolean regularExpCheck(String regularExpVlue,String value) {
		Pattern p = Pattern.compile(regularExpVlue);
		Matcher m = p.matcher(value); 
		Boolean status =  m.matches();
		return status;
	}
	
	
	/**
	 * 包含判断
	 * */
	public Boolean containParamCheck(FieldCache fieldCache) {
		String[] arr = fieldCache.getContainParam();
		String value = StringUtil.toString(fieldCache.getValue());
		
		if(Objects.nonNull(arr) && arr.length>0) {
			return Arrays.asList(arr).contains(value);
		}
		return true;
	}

	/**
	 * 不包含判断
	 * */
	public Boolean notContainParam(FieldCache fieldCache) {
		String[] arr = fieldCache.getContainParam();
		String value = StringUtil.toString(fieldCache.getValue());
		
		if(Objects.nonNull(arr) && arr.length>0) {
			return !Arrays.asList(arr).contains(value);
		}
		return true;
	}
	 
}
