package utils;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase{

	public WebDriver driver;
	
	@BeforeSuite
	public void startDriver()
	{
		try {
			Utilities dc = new Utilities(driver);
			driver = dc.initlaizeDriver();
		} catch (Exception e) {
			System.out.println("Exception details => "+e.getMessage());
			Reporter.log("Exception details => "+e.getMessage());
		}
	}
	
	@AfterMethod
	public void closeBrowser()
	{
		driver.quit();
		Reporter.log("Successfully closed the browser.");
	}
}
