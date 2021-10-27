package org.zoo.woodpecker.test;

import org.zoo.woodpecker.util.StringUtil;

public class MyCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * obj: 此行记录
	 * key：待检查可以记录
	 * */
	public static boolean checkName(Object obj,Object key) {
		if("小明".equals(StringUtil.toString(key))) {
			return true;
		}
		return false;
	}

	
}
