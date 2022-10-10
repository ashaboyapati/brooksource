package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;

public class Utilities{
	
	public WebDriver driver;
	
	public  Utilities(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public  WebDriver initlaizeDriver()
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
		
	}
	
	public void enterText(WebElement element, String value, String elementName) throws Exception {
		try {
			element.clear();
			element.sendKeys(value);
			Reporter.log("Entered value  "+value+" for "+elementName);
			
		} catch (Exception e) {
			System.out.println("Exception details => "+e.getMessage() );
			Reporter.log("Exception details => "+e.getMessage() );
		}
	}
	
	public void scrollToElement(WebElement element){
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			Reporter.log("Moved to element");
		} catch (Exception e) {
			System.out.println("Exception details => "+e.getMessage() );
			Reporter.log("Exception details => "+e.getMessage() );
		}
	}
	
	public void click(WebElement element, String elementName) throws Exception {
		try {

			element.click();
			Reporter.log("Clicked on  ->"+elementName);
			System.out.println("Clicked on  ->"+elementName);
		} catch (Exception e) {
			System.out.println("Exception details => "+e.getMessage() );
			Reporter.log("Exception details => "+e.getMessage() );
		}
	}
	
	
	public void openURL(String url) throws Exception {
		try {
			driver.get(url);
			Reporter.log("Successfully Launched URL  ->"+url);
			driver.manage().window().maximize();
			Reporter.log("Maximized window");
		} catch (Exception e) {
			System.out.println("Exception details => "+e.getMessage() );
			Reporter.log("Exception details => "+e.getMessage() );
		}
	}
	
	public void waitforPageLoad() throws Exception
	{
		try {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println("Exception details => "+e.getMessage() );
			Reporter.log("Exception details => "+e.getMessage() );
		}
	}
	
	public void waitforElementDisplayed(WebElement element) throws Exception
	{
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				       .withTimeout(30, TimeUnit.SECONDS)
				       .pollingEvery(1, TimeUnit.SECONDS)
				       .ignoring(NoSuchElementException.class);
			
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			System.out.println("Exception details => "+e.getMessage() );
			Reporter.log("Exception details => "+e.getMessage() );
		}
	}
	
	public void waitforListOfElementsDisplayed(List<WebElement> elements) throws Exception
	{
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				       .withTimeout(30, TimeUnit.SECONDS)
				       .pollingEvery(1, TimeUnit.SECONDS)
				       .ignoring(NoSuchElementException.class);
			
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		} catch (Exception e) {
			System.out.println("Exception details => "+e.getMessage() );
			Reporter.log("Exception details => "+e.getMessage() );
		}
	}
	
	public void verifyElementDisplayed(WebElement element,String elementName) throws Exception
	{
		try {
			if(element.isDisplayed())
			{
				System.out.println("Successfully element "+elementName+" is Displayed");
				Reporter.log("Successfully element "+elementName+" is Displayed");
			}else{
				System.out.println(elementName+" is not Displayed");
				Reporter.log("Element "+elementName+" is NotDisplayed");
			}
		} catch (Exception e) {
			System.out.println("Exception details => "+e.getMessage() );
			Reporter.log("Exception details => "+e.getMessage() );
		}
	}
	
	public String getPropertyValue(String filePath, String keyName)
	{
		try {
			File file = new File(".//"+filePath);
			FileInputStream fileInput = null;
			try {
				fileInput = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Properties prop = new Properties();
			
			//load properties file
			try {
				prop.load(fileInput);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//searchValue
			String exp_Value =  prop.getProperty(keyName);
			
			return exp_Value;
		} catch (Exception e) {
			System.out.println("Exception details => "+e.getMessage() );
			Reporter.log("Exception details => "+e.getMessage());
			return null;
		}
	}
	
	public void switchWindowToNewWindow(String parentWindow) {
		try {
			System.out.println("Windows size" + driver.getWindowHandles().size());
			
			for (String winHandle : driver.getWindowHandles()) {
				if(!(parentWindow.equalsIgnoreCase(winHandle)))
				{
					driver.switchTo().window(winHandle);
					Reporter.log("Successfully switched to new Window");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception details => "+e.getMessage() );
			Reporter.log("Exception details => "+e.getMessage());
		}
	}
	
	public String getCurrentWindow() {
		try {
			return  driver.getWindowHandle();
		} catch (Exception e) {
			System.out.println("Exception details => "+e.getMessage() );
			Reporter.log("Exception details => "+e.getMessage());
			return null;
		}
	}
	
	
	public void waitTimeSec(int timeSeconds)
	{
		try {
			Thread.sleep(timeSeconds*1000);
		} catch (Exception e) {
			System.out.println("Exception details => "+e.getMessage() );
			Reporter.log("Exception details => "+e.getMessage() );
		}
	}
	
	
}
