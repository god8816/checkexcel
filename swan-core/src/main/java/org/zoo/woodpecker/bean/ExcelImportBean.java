package org.zoo.woodpecker.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import lombok.*;
import java.io.Serializable;

/**
 * @author dzc
 * @date 2020-08-26
 * @description excel导入提示类
 **/
@Getter
@Setter
public class ExcelImportBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8653019213817440189L;
	
	
	//异常原因
	@ExcelIgnore
	private String errorInfo;

}
