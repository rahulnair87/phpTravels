package com.php.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	private static XSSFWorkbook ExcelWB;
	private static XSSFSheet ExcelSheet;
	private static XSSFCell ExcelCell;
	private static XSSFRow ExcelRow;
	
	
	
	public static void setExcelFile(){
		
		try{
			File fileExcel = new File(System.getProperty("user.dir")+"/src/com/php/resources/PhpTravelsBookingData.xlsx");
			FileInputStream fisExcel = new FileInputStream(fileExcel);
			
			ExcelWB = new XSSFWorkbook(fisExcel);
			ExcelSheet = ExcelWB.getSheet("Standard");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String getCellData(int row, int col){
		
		try{
			ExcelCell = ExcelSheet.getRow(row).getCell(col);
			return ExcelCell.toString();			
		}catch(Exception e){
			return "";	
		}
			
	}
	
	public static HashMap<String,String> getRowData(String testCaseName){
		
		int rowCount = ExcelSheet.getLastRowNum();
		int colCount = ExcelSheet.getRow(0).getLastCellNum();
		int rownum = 0;
		HashMap<String,String> dataMap;
		
		for(int i=1; i<=rowCount; i++){
			if(getCellData(i,0).toString().trim().equals(testCaseName)){
				rownum = i;
				break;
			}else{
				System.out.println("Data not available for this test case in input file. Please check and add");
				System.exit(400);
			}
		}
			
		dataMap = new HashMap<String,String>();	
		for(int j =0; j<colCount; j++){			
			dataMap.put(getCellData(0,j).toString().trim(), getCellData(rownum,j).toString().trim());			
			}
		
		return dataMap;
	}

}
