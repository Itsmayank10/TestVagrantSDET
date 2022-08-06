package Test;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



import Default.BrowserCode;
import Pages.IMDBPage;
import Pages.WikiPage;

public class CompareData {

	WebDriver driver;


	@BeforeMethod
	//opening Browser with given URL
	public void openBrowser() {
		driver=BrowserCode.openChromeBrowser("https://www.imdb.com/");
	}
	@Test
	@Parameters({"movieName"})
	public void getData(String movieName) throws InterruptedException {
		IMDBPage imdbPage=new IMDBPage(driver);
		imdbPage.getSearch(movieName,driver);
		String date_imdb=imdbPage.getDate();
		String country_imdb=imdbPage.getCountry();

		driver.navigate().to("https://en.wikipedia.org/wiki/Main_Page");
		WikiPage wikiPage=new WikiPage(driver);
		wikiPage.searchOnWiki(movieName, driver);
		String date_wiki=wikiPage.getDate(); 
		String country_wiki=wikiPage.getCountry();
		
		//Comparing the Data
		if(date_imdb.equals(date_wiki)){
			Reporter.log("Test passed",true);
		}else {
			Reporter.log("Test Failed",true);
		}
		if(country_imdb.equals(country_wiki)){
			Reporter.log("Test passed",true);
		}else 
		{
			Reporter.log("Test Failed",true);
		}




	}

	@AfterTest
	public void tearDownTest() {

		//close browser
		driver.close();
		//driver.quit();
		System.out.println("Test Completed Successfully");
	}


}
