package com.php.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.php.utils.Constants;
import com.php.utils.Params;

public class BaseTest {
	
	public static Properties prop;
	public static WebDriver driver;
	public static String testCaseName;
		
	public BaseTest(){		
		try{
			prop = new Properties();
			File fs = new File(System.getProperty("user.dir")+"/src/com/php/config/config.properties");
			FileInputStream ip = new FileInputStream(fs);	
			prop.load(ip);
		}
		catch(Exception e){
			e.printStackTrace();			
		}
	}
	
	public void initialize(){
		
		Params.LoadInputData(testCaseName);
		
		String browser = prop.getProperty("browser");
		
		switch(browser.trim()){
		
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/src/com/php/resources/chromedriver.exe");
			driver = new ChromeDriver();
		break;
		case "IE":
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+ "/src/com/php/resources/chromedriver.exe");
			driver = new InternetExplorerDriver();
		break;
		default:
			System.out.println("The requested browser is not setup for testing");
			System.exit(0);		
		}
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Constants.ImplicitWait, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Constants.PageLoadWait, TimeUnit.SECONDS);	
		driver.get(prop.getProperty("url"));
	}
	
	
}
