package org.zoo.woodpecker.test;

import java.util.ArrayList;
import java.util.List;

import org.zoo.woodpecker.ExcelCheck;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<ABean> listExcel = new ArrayList<ABean>();
		for (int i=0;i<2;i++) {
			ABean e = new ABean();
			e.setName("名字_"+i);
			e.setPhone1("13193873308@");
			e.setPhone2("13193873308@");
			listExcel.add(e);
		}
		
		List<ABean> listError = ExcelCheck.list(listExcel, ABean.class).doErrorRecord();
		
		List<ABean> listRight = ExcelCheck.list(listExcel, ABean.class).doRightRecord();
		
		
		List<BBean> blistError = ExcelCheck.list(listExcel, BBean.class).doErrorRecord();
		
		List<BBean> blistRight = ExcelCheck.list(listExcel, BBean.class).doRightRecord();
		
		
		System.out.println(123);
	}

}
