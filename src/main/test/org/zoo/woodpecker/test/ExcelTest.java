package org.zoo.woodpecker.test;

import java.util.ArrayList;
import java.util.List;

import org.zoo.woodpecker.ExcelCheck;

public class ExcelTest {

	public static void main(String[] args) {
		List<ABean> listExcel = new ArrayList<ABean>();
		for (int i=0;i<2;i++) {
			ABean e = new ABean();
			e.setName("名字_"+i);
			e.setPhone1("13193873308@");
			//e.setPhone2("13193873308@");
			e.setIdcard("41152619880906571X1");
			e.setNumber("0.123");
			listExcel.add(e);
		}
		
		List<ABean> listError = ExcelCheck.list(listExcel, ABean.class).doErrorRecord();
		
		List<ABean> listRight = ExcelCheck.list(listExcel, ABean.class).doRightRecord();
		
		

		
		System.out.println(123);
	}

}
