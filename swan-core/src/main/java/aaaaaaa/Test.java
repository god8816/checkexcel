package aaaaaaa;

import java.util.ArrayList;
import java.util.List;

import org.zoo.woodpecker.check.ExcelCheck;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<AaBean> listExcel = new ArrayList<AaBean>();
		
		List<AaBean> listError = ExcelCheck.list(listExcel, AaBean.class).doErrorRecord();
		
		List<AaBean> listRight = ExcelCheck.list(listExcel, AaBean.class).doRightRecord();
	}

}
