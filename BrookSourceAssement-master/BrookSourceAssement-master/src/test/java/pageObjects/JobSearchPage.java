package pageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import utils.Utilities;

public class JobSearchPage extends Utilities{
	public JobSearchPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	
	@FindBy(xpath= "//h2[text()='Job Search']/following-sibling::div//input") 
	private WebElement input_JobSearch;
	
	@FindBy(xpath= "//i[@class='facetwp-icon']") 
	WebElement btn_Search;
	
	@FindBy(xpath= "//ul[contains(@class,'job_listings')]/li//h3") 
	List<WebElement> list_JobListingsHeaders;

	SoftAssert softassert = new SoftAssert();
	
	public void validateSearchFunctionality(){
		
		try {
			
			String enterValue = getPropertyValue("Data.properties", "searchValue");
			
			waitforElementDisplayed(input_JobSearch);
			
			enterText(input_JobSearch, enterValue, "Job Search Value");
			click(btn_Search, "Search Icon");
			waitforPageLoad();
			waitTimeSec(5);
			waitforListOfElementsDisplayed(list_JobListingsHeaders);
			
			
			//Here Getting List of search results and Validating...
			int size = list_JobListingsHeaders.size();
			System.out.println("size "+size);
			Reporter.log("After Search No of records present is => "+size);
			for (int i=0;i<size;i++)
			{
				String act_ListHeaderText = list_JobListingsHeaders.get(i).getText();
				System.out.println("act_ListHeaderText =>"+act_ListHeaderText);
				System.out.println(act_ListHeaderText.contains(enterValue));
				softassert.assertTrue(act_ListHeaderText.contains(enterValue),"Enter value => "+enterValue+" is not contain with Searched value => "+act_ListHeaderText);
				
			}
			softassert.assertAll();
			
		} catch (Exception e) {
			System.out.println("Exception Detials are =>"+e.getMessage());
			Reporter.log("Exception details => "+e.getMessage() );
		}
	}
}
