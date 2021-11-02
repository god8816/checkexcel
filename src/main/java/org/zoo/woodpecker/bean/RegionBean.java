package org.zoo.woodpecker.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String provinceName;
	
	private String provinceCode;
	
	
	private String cityName;
	
	private String cityCode;
	
	
	private String areaName;
	
	private String areaCode;
	
	
	
	private String streetName;
	
	private String streetCode;

}
