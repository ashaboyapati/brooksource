package testcases;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.JobSearchPage;
import utils.TestBase;
import utils.Utilities;

public class ValidateSearchJobFunctionalityTest  extends TestBase{

	@Test
	public void validSearchJobsFunctionality(){
		
		HomePage hmPage = new HomePage(driver);
		JobSearchPage jsPage = new JobSearchPage(driver);
		
		hmPage.launchBrookSourceApplication();
		hmPage.navigateToJobSearchPage();
		jsPage.validateSearchFunctionality();
		
	}
}