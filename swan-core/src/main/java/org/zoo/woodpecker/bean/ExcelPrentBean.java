package org.zoo.woodpecker.bean;

import com.alibaba.excel.annotation.ExcelIgnore;

import lombok.*;
import java.io.Serializable;

/**
 * @author dzc
 * @description excel导入提示类
 **/
@Getter
@Setter
public class ExcelPrentBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8653019213817440189L;
	
	
	//异常原因
	@ExcelIgnore
	private String errorInfo = "";

}
