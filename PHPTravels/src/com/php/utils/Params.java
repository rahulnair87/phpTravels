package com.php.utils;

import java.util.HashMap;

public class Params {
	
	private static HashMap<String, String> dataMap;
	
	Params(){
		dataMap = new HashMap<String,String>();
	}
	
	public static  void LoadInputData(String testCaseName){
		
		ExcelUtils.setExcelFile();
		
		dataMap = ExcelUtils.getRowData(testCaseName);				
	}
	
	public static String get(String key){
		
		if(dataMap.containsKey(key))
			return dataMap.get(key);
		else
			return "";
	}		
}
