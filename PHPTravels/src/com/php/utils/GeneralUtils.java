package com.php.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.php.base.BaseTest;

public class GeneralUtils extends BaseTest {
	
	public static WebElement getVisibleElement(String xpath){
		
		WebElement element = null;
		
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		
		int k = 0;
		for(WebElement e : elements){
			if(e.isDisplayed()){
				element = e;
				System.out.println("The matching element index :"+k);
				break;
			}
			k++;
		}
		
		return element;
	}
	
	public static void clickOnObject(WebElement obj){
		
		Actions action = new Actions(driver);
		action.moveToElement(obj);
		action.click(obj).build().perform();
	}
	
	public static void clickOnObjectJS(WebElement obj){
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();",obj);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",obj); 
	}
	
	public static void scrollInToView(By locator){
		
		WebElement element = driver.findElement(locator);
		JavascriptExecutor js =  (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		
	}
	
	public static void takeScreenshot(){
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			FileUtils.copyFile(src, new File("C:/selenium/Screenshot_"+System.currentTimeMillis()+".png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
