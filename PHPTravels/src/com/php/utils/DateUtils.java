package com.php.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class DateUtils {
	
	public static LocalDate getFirstFriday(){
		LocalDate now = LocalDate.now();
		now = now.plusMonths(1);
		LocalDate firstFriday = now.with(firstInMonth(DayOfWeek.FRIDAY));
		return firstFriday;
	}

	
	public static void main(String[] args){
		LocalDate var = getFirstFriday();
		String mont = getMonthName(var);
		String day = getDayFromDate(var);
		System.out.println(var.toString());
		System.out.println(mont);
		System.out.println(day);
		
	}
	
	public static String getMonthName(LocalDate date){
		String month = "";
		try{
			month = date.getMonth().toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return month;		
	}
	
	public static String getDayFromDate(LocalDate date){
		String day = "";
		day = String.valueOf(date.getDayOfMonth());
		return day;
	}
}
