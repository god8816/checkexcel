package aaaaaaa;

import java.util.ArrayList;
import java.util.List;

import org.zoo.woodpecker.ExcelCheck;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<AaBean> listExcel = new ArrayList<AaBean>();
		for (int i=0;i<2;i++) {
			AaBean e = new AaBean();
			e.setName("名字_"+i);
			e.setPhone1("13193873308@");
			e.setPhone2("13193873308@");
			listExcel.add(e);
		}
		
		List<AaBean> listError = ExcelCheck.list(listExcel, AaBean.class).doErrorRecord();
		
		List<AaBean> listRight = ExcelCheck.list(listExcel, AaBean.class).doRightRecord();
		
		
		List<BaBean> blistError = ExcelCheck.list(listExcel, BaBean.class).doErrorRecord();
		
		List<BaBean> blistRight = ExcelCheck.list(listExcel, BaBean.class).doRightRecord();
		
		
		System.out.println(123);
	}

}
