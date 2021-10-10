package com.test;

import java.util.Calendar;
import java.util.Date;

public class DemoTime {

	public static void main(String[] args) {
		Date date = new Date(2020, 21, 11);
		//date.setDate(11);;
		//date.setMonth(3);
		//date.setYear(2020);
		
		
		System.out.println("ngay " + date.getDay() + " Thang: " + date.getMonth() + " nam: " + date.getYear());
		//System.out.println(cal.get(Calendar.THURSDAY));
	}
}
