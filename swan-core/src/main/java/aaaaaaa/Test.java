package aaaaaaa;

import java.util.ArrayList;
import java.util.List;

import org.zoo.woodpecker.ExcelCheck;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<AaBean> listExcel = new ArrayList<AaBean>();
		for (int i=0;i<10;i++) {
			AaBean e = new AaBean();
			e.setName(i+"");
			e.setAge(i+"");
			e.setXg(i+"");
			listExcel.add(e);
		}
		
		List<AaBean> listError = ExcelCheck.list(listExcel, AaBean.class).doErrorRecord();
		
		List<AaBean> listRight = ExcelCheck.list(listExcel, AaBean.class).doRightRecord();
		
		System.out.println(123);
	}

}
