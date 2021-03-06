package org.zoo.woodpecker.test;

import java.util.ArrayList;
import java.util.List;

import org.zoo.woodpecker.ExcelCheck;

public class ExcelTest {

	public static void main(String[] args) {
		List<ABean> listExcel = new ArrayList<ABean>();
		for (int i=0;i<2;i++) {
			ABean e = new ABean();
			e.setName("小明");
			e.setPhone("13193873308");
			e.setIdcard("41152619880906571X1");
			e.setNumber("-34324x");
			e.setEn("woshiyinwen");
			e.setCn("我是中文");
			e.setEmail("948@qq.com");
			e.setProvince("浙江省");
			e.setCity("杭州市");
			e.setArea("滨江区");
			e.setStreet("浦沿街道");
			listExcel.add(e);
		}
		
		System.out.println(123);
		
		List<ABean> listError = ExcelCheck.list(listExcel, ABean.class).doErrorRecord();
		
		List<ABean> listRight = ExcelCheck.list(listExcel, ABean.class).doRightRecord();
		
		

		
		System.out.println(123);
	}

}
