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
		initialize();
		homePage = new HomePage();
		
	}
	
	@Test
	public void Book2Adults() throws InterruptedException{
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
		
		Thread.sleep(5000);
		
	}
	
	@AfterMethod
	public void teardown(){
		driver.quit();
		
	}
	

}
