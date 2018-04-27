package com.htzhny.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		Calendar cal=Calendar.getInstance();
		Date dt =new Date(); 
		cal.setTime(dt);
		int month=cal.get(Calendar.MONTH);
		int year=cal.get(Calendar.YEAR);
		String a="2018-3";
		String year1=a.substring(0, 4);
		System.out.println(year1);
		String month1=a.substring(5);
		System.out.println(month1);
		Integer b=1;
		Integer c=2;
		String d=b+"-"+c;
		System.out.println(d.getClass());
		
	//	String formatDate = "";  
	//	DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；  
	 //   formatDate = dFormat.format(dt); 
	 //   String time = DateFormat.getDateInstance().format(dt);  
	    
	}

}
