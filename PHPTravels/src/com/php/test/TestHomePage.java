package com.php.test;

import java.lang.reflect.Method;

import org.testng.annotations.*;

import com.php.base.BaseTest;
import com.php.pages.HomePage;

public class TestHomePage extends BaseTest {
	
	public TestHomePage(){
		super();
	}
	 
	HomePage homePage;
	
	@BeforeMethod
	public void setup(Method method){
		testCaseName = method.getName();
		System.out.println("Test Case Name :"+testCaseName);
		initialize();
		homePage = new HomePage();		
	}
	
	@Test(priority=1, enabled=true)
	public void Book2Adults() {
		homePage.clickOnFlightsTab();
		homePage.setFromLocation();			
		homePage.setToLocation();
		homePage.setTripTypeRound();
		homePage.setDepartureDate();
		homePage.setReturnDate();
		homePage.setNumberofAdults();
		homePage.setNumberOfChild();
		homePage.setNumberOfInfant();		
		homePage.clickSearchButton();				
	}
	
	@Test(priority=2)
	public void Book2Children(){
		homePage.clickOnFlightsTab();
		homePage.setFromLocation();			
		homePage.setToLocation();
		homePage.setTripTypeRound();
		homePage.setDepartureDate();
		homePage.setReturnDate();
		homePage.setNumberofAdults();
		homePage.setNumberOfChild();
		homePage.setNumberOfInfant();		
		homePage.clickSearchButton();				
	}
	
	@AfterMethod
	public void teardown(){
		driver.quit();		
	}
}
