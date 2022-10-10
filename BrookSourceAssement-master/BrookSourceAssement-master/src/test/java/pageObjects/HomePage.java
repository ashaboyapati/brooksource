package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import utils.Utilities;

public class HomePage extends Utilities{
	

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	@FindBy(xpath = "//a/div[contains(.,'IT Openings') and contains(.,'Search')]")
	private WebElement link_SearchITOpenings;
	
	
	


	public void launchBrookSourceApplication()
	{
		try {
			String url = getPropertyValue("Data.properties", "URL");
			openURL(url);
			waitforPageLoad();
			waitforElementDisplayed(link_SearchITOpenings);
			
		} catch (Exception e) {
			System.out.println("Exception details are ->"+e.getMessage());
			Reporter.log("Exception details => "+e.getMessage() );
		}
	}
	
	
	public void navigateToJobSearchPage()
	{
		try {
			waitforElementDisplayed(link_SearchITOpenings);
			click(link_SearchITOpenings, "Search IT Openings");
			waitforPageLoad();
			String parentWindow = getCurrentWindow();
			switchWindowToNewWindow(parentWindow);
			
		} catch (Exception e) {
			System.out.println("Exception details are ->"+e.getMessage());
			Reporter.log("Exception details => "+e.getMessage() );
		}
	}
	
}
