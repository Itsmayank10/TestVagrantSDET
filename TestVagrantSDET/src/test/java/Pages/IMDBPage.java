package Pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IMDBPage {

	//Declaration of all the data globally & restricted to private with private access specifier
	@FindBy(xpath="//input[@id='suggestion-search']")private WebElement searchIMDb;
	@FindBy(xpath="//a[contains(@class,'const')]")private List<WebElement> movieList;
	@FindBy(xpath="//a[@data-testid='search-result--link']")private WebElement mList;
	@FindBy(xpath="(//h3[@class='ipc-title__text'])[8]")private WebElement details;
	@FindBy(xpath="(//a[@data-testid='search-result--const'])[1]")private WebElement mName;
	@FindBy(xpath="(//a[contains(@href,'/releaseinfo?ref_=tt_dt_rdat')])[2]")private WebElement date;
	@FindBy(xpath="//a[contains(@href,'/search/title/?country_of_origin')]")private WebElement country;
	WebDriver driver;

	/*To initialize the elements in PageFactory,we have to write this initElements static method of PageFactory
		inside the constructor*/
	public IMDBPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	//Entering the movieName inside the searchBox
	public void getSearch(String movieName,WebDriver driver) throws InterruptedException {
		searchIMDb.sendKeys(movieName);
		Thread.sleep(3000);
		//By directly clicking on the film
		Actions act=new Actions(driver);
		act.moveToElement(mName).click().build().perform();


	}
	//For scrolling to details part which contains date and released date
	public Boolean scrollToDetailTitle(WebDriver driver){
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",details);
		return details.isDisplayed();

	}
	//To findOut the released date
	public String getDate() {
		String d=date.getText();
		System.out.println(d);
		return d;

	}
	//To findOut the origin of country
	public String getCountry() {
		String c=country.getText();
		System.out.println(c);
		return c;
	}

}
