package Pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WikiPage {

	//Declaration of all the dataMembers globally & restricted to private with private access specifier
	@FindBy(xpath="//input[@id='searchInput']")private WebElement searchWiki;
	@FindBy(xpath="//div[@class='suggestions-result']")private List<WebElement> pMovieList;
	@FindBy(xpath="//h1[@id='firstHeading']")private WebElement newPageHeaeding;
	@FindBy(xpath="//table/tbody/tr[12]/td[@class='infobox-data']/div[@class='plainlist']/ul/li")private WebElement date;
	@FindBy(xpath="//table/tbody/tr[14]/td[@class='infobox-data']")private WebElement country;
	WebDriver driver;

	/*To initialize the elements in PageFactory,we have to write this initElements static method of Pagefactory
		inside the constructor*/
	public WikiPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	//Entering the movieName inside the searchBox
	public void searchOnWiki(String MovieName,WebDriver driver) throws InterruptedException {
		searchWiki.sendKeys(MovieName);
		Thread.sleep(2000);

		//List of all auto-suggestion movie
		//System.out.println(pMovieList.size());

		//Iterate the list till we get desired movie
		for(int i=0;i<pMovieList.size();i++) {
			WebElement movie=pMovieList.get(i);
			String moviename=movie.getText();

			//Compare the movie with, as we given in movieName
			if(moviename.equalsIgnoreCase(MovieName)) {
				Actions act=new Actions(driver);
				act.moveToElement(movie).perform();
				act.click().perform();
			}
		}
	}
	public String displayTitle(WebDriver driver) {
		return driver.getTitle();
	}
	//For scrolling to details part which contains date and released date
	public void diplayTable(WebDriver driver) {
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,400)");


	}
	//To findOut the released date
	public String getDate() {
		String dd=date.getText();
		System.out.println(dd);
		return dd;
	}
	//To findOut the origin of country
	public String getCountry() {
		String cc=country.getText();
		System.out.println(cc);
		return cc;
	}

}
