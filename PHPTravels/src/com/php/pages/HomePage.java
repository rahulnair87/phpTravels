package com.php.pages;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.php.base.BaseTest;
import com.php.utils.DateUtils;
import com.php.utils.GeneralUtils;
import com.php.utils.Params;

public class HomePage extends BaseTest {
	
	By tabFlights = By.xpath("//div//li/a[@data-name='flights']");
	By inputFrom = By.xpath("//div/label[(text()='From')]/..//a");
	By selectFrom = By.xpath("//div[@id='select2-drop']//ul/li//span[text()='"+ Params.get("From") +"']");	
	By inputTo = By.xpath("//div/label[(text()='To')]/..//a");
	By selectTo = By.xpath("//div[@id='select2-drop']//ul/li//span[text()='"+ Params.get("To") +"']");
	By btnAdutlsPlus = By.xpath("//div/input[@name='fadults']/../span/button[contains(.,'+')]");
	By btnAdultMinus = By.xpath("//div/input[@name='fadults']/../span/button[contains(.,'-')]");
	By btnChildPlus = By.xpath("//div/input[@name='fchildren']/../span/button[contains(.,'+')]");
	By btnChildMinus = By.xpath("//div/input[@name='fchildren']/../span/button[contains(.,'-')]");
	By btnInfantPlus = By.xpath("//div/input[@name='finfant']/../span/button[contains(.,'+')]");
	By btnInfantMinus = By.xpath("//div/input[@name='finfant']/../span/button[contains(.,'-')]");
	By radioOneWayTrip = By.xpath("//div/input[@name='triptype']/../label[contains(.,'One Way')]");
	By radioRoundTrip = By.xpath("//div/input[@name='triptype']/../label[contains(.,'Round Trip')]");
	By btnSearch = By.xpath("//div/button[@type='submit' and contains(.,'Search') and not(preceding-sibling :: input[@type='hidden'])]");
	By dateDeparture = By.id("FlightsDateStart");
	By dateReturn = By.id("FlightsDateEnd");
	By datePickerMonth = By.xpath("//div[contains(@class,'datepicker') and contains(@class,'active')]//div[@class='datepicker--nav-title']");
//	String xpathDatePickerNextMonthArrow = "//div[(@class='datepicker--nav-action') and @data-action='next']//*[name()='svg']//*[name()='path']";
	String xpathDatePickerNextMonthArrow = "//div[(@class='datepicker--nav-action') and @data-action='next']";
	String xpathDatePickerDay = "//div[contains(@class,'datepicker--cell-day') and (@data-date='%s')]";
	By loaderSearching = By.xpath("//ul[@class='select2-results']//*[contains(text(),'Searching')]");
	
	public void clickOnFlightsTab(){
		driver.findElement(tabFlights).click();
	}
	
	
	public void setFromLocation(){
		driver.findElement(inputFrom).click();
		WebDriverWait wait = new WebDriverWait(driver,10);		
		
		driver.findElement(inputFrom).sendKeys(Params.get("From"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderSearching));
		wait.until(ExpectedConditions.visibilityOfElementLocated(selectFrom));
		driver.findElement(selectFrom).click();
	}
	
	public void setToLocation(){
		driver.findElement(inputTo).click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		driver.findElement(inputTo).sendKeys(Params.get("To"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderSearching));
		wait.until(ExpectedConditions.visibilityOfElementLocated(selectTo));	
		driver.findElement(selectTo).click();
	}
	
	public void setNumberofAdults(){
		String adults = Params.get("Adults");
		driver.findElement(btnAdultMinus).click();
		if(adults != ""){
			for(int i =0; i< Integer.parseInt(adults) ; i++){ 
				driver.findElement(btnAdutlsPlus).click();
			}		
		}
	}
	
	public void setNumberOfChild(){
		String child = Params.get("Child");
		if(child != ""){
			for(int i=0; i < Integer.parseInt(child) ; i++){
				driver.findElement(btnChildPlus).click();
			}
		}
	}
	
	public void setNumberOfInfant(){
		String infant = Params.get("Infant");
		if(infant != ""){
			for(int i=0; i < Integer.parseInt(infant) ; i++){
				driver.findElement(btnInfantPlus).click();
			}
		}
	}
	
	public void setTripTypeOneWay(){
		driver.findElement(radioOneWayTrip).click();		
	}
	
	public void setTripTypeRound(){
		driver.findElement(radioRoundTrip).click();		
	}
	
	public void clickSearchButton(){
		driver.findElement(btnSearch).click();
		GeneralUtils.takeScreenshot();
	}
	
	public void setDepartureDate(){
		driver.findElement(dateDeparture).click();
		LocalDate dateDeparture = DateUtils.getFirstFriday(); 
		System.out.println("Departure date to be selected :" +dateDeparture);
		String monthDeparture = DateUtils.getMonthName(dateDeparture);
		String dayDeparture = DateUtils.getDayFromDate(dateDeparture);
		String datePickerMonthDisplayed = "";
		
	   	 int clickNextCount = 0;
		 WebElement nextArrowDatePicker = null ;
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 
		 do{
			 wait.until(ExpectedConditions.visibilityOfElementLocated(datePickerMonth));
			 datePickerMonthDisplayed  = driver.findElement(datePickerMonth).getText();	
			 GeneralUtils.scrollInToView(datePickerMonth);
		//	 GeneralUtils.takeScreenshot();
			 
			 datePickerMonthDisplayed = datePickerMonthDisplayed.substring(0, datePickerMonthDisplayed.indexOf(','));
			 
			 nextArrowDatePicker = GeneralUtils.getVisibleElement(xpathDatePickerNextMonthArrow);
			
			 GeneralUtils.clickOnObjectJS(nextArrowDatePicker);
			 datePickerMonthDisplayed  = driver.findElement(datePickerMonth).getText();	
			 datePickerMonthDisplayed = datePickerMonthDisplayed.substring(0, datePickerMonthDisplayed.indexOf(','));
			 clickNextCount++;			 
		 }
		 while(!datePickerMonthDisplayed.equalsIgnoreCase(monthDeparture) && clickNextCount<12);
		 
		 xpathDatePickerDay = String.format(xpathDatePickerDay, dayDeparture);		 		 
		 WebElement day = GeneralUtils.getVisibleElement(xpathDatePickerDay);
		 day.click();		
	}
	
	public void setReturnDate(){
		driver.findElement(dateReturn).click();
		LocalDate dateReturn = DateUtils.getFirstFriday().plusDays(4); 
		System.out.println("Return date to be selected :" +dateReturn);
		String monthReturn = DateUtils.getMonthName(dateReturn);
		String dayRetrun = DateUtils.getDayFromDate(dateReturn);
		String datePickerMonthDisplayed = "";
		
	   	 int clickNextCount = 0;
		 WebElement nextArrowDatePicker = null ;
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 
		 do{
			 wait.until(ExpectedConditions.visibilityOfElementLocated(datePickerMonth));
			 datePickerMonthDisplayed  = driver.findElement(datePickerMonth).getText();	
			 GeneralUtils.scrollInToView(datePickerMonth);
		//	 GeneralUtils.takeScreenshot();
			 
			 datePickerMonthDisplayed = datePickerMonthDisplayed.substring(0, datePickerMonthDisplayed.indexOf(','));
			 
			 nextArrowDatePicker = GeneralUtils.getVisibleElement(xpathDatePickerNextMonthArrow);
			
			 if(!datePickerMonthDisplayed.equalsIgnoreCase(monthReturn)) 
				 GeneralUtils.clickOnObjectJS(nextArrowDatePicker);
			 
			 datePickerMonthDisplayed  = driver.findElement(datePickerMonth).getText();	
			 datePickerMonthDisplayed = datePickerMonthDisplayed.substring(0, datePickerMonthDisplayed.indexOf(','));
			 clickNextCount++;			 
		 }
		 while(!datePickerMonthDisplayed.equalsIgnoreCase(monthReturn) && clickNextCount<12);
		 
		 xpathDatePickerDay = String.format(xpathDatePickerDay, dayRetrun);		 		 
		 WebElement day = GeneralUtils.getVisibleElement(xpathDatePickerDay);
		 day.click();		
	}

	
}
