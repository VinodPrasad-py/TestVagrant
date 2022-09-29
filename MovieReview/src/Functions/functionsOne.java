package Functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ObjectRepos.imdbPage;
import ObjectRepos.wikiPage;

public class functionsOne {

	
	public WebDriver driver;
	public Actions act;
	public Properties prop;
	public WebDriverWait wait;
	public JavascriptExecutor JS;
	
	public functionsOne(WebDriver driver,Actions act,Properties prop, WebDriverWait wait, JavascriptExecutor JS) {
		
		this.driver=driver;
		this.act=act;
		this.prop=prop;
		this.wait=wait;
		this.JS=JS;
	}
	
	private String IMDBDatePrase(String inputString) {
		
		String day;
		String month;
		String year;
		
		String[] listOne= inputString.split(",");
		String[] dayMonth= listOne[0].split(" ");
		String[] yearList= listOne[1].split(" ");
		month=dayMonth[0];
		day=dayMonth[1];
		year=yearList[1];
		
		return(day+" "+month+" "+year);
		
	}
	
	/*This method can parse the date string obtained 
	 * in IMDB to the same format as the date string that will be 
	 * extracted in Wikipedia
	 */
	
	private String returnListString( List<WebElement> listInputOne,  List<WebElement> listInputTwo, String key) {
		
		String value = new String();
		int length = listInputOne.size();
		for(int i=0;i<length;i++) {
			
			String temp = listInputOne.get(i).getText();
			if (temp.equalsIgnoreCase(key)) {
				value= listInputTwo.get(i).getText();
				break;
			}
		
		}
		return value;
		
	}
	
	/* This Method will take in two of the lists obtained 
	 * from the wikipedia info table and based on either 
	 * date or country key parameter it will iterate and extract the
	 * string*/
	
	public void goToSiteIMDB() {
		
		imdbPage obj = new imdbPage(driver);
		driver.get(prop.getProperty("ImdbURL"));  //Gets URl from properties file
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.elementToBeClickable(obj.searchInput));
		
	}
	
	/* This method navigates straight to the IMDB home page and waits till the search
	 * input field is clickable*/
	
	public void searchFilmIMDB() {
		
		imdbPage obj = new imdbPage(driver);
		obj.searchInput.sendKeys(prop.getProperty("MovieName")); //Gets movie name from properties file
		obj.searchIcon.click();
		
		//act.sendKeys(Keys.ENTER).perform();
		String locator= String.format("//a[text()='%s']", prop.getProperty("MovieName"));
		wait.until(ExpectedConditions.elementToBeClickable(obj.titlesText));
		WebElement movieHyperlink = driver.findElement(By.xpath(locator));
		movieHyperlink.click();
        wait.until(ExpectedConditions.elementToBeClickable(obj.allTopicsIcon)); // Icon on top right corner of page
		
		
	}
	
	//Searches film after input name and waits till film page is loaded
	
	public String extractDateIMDB() {
		
		String date;
		
		imdbPage obj = new imdbPage(driver);
		date=obj.releaseDate.getText();
		date= IMDBDatePrase(date);
		return(date);
		
	}
	// Extracts date and calls date parse function to get suitable format
	
	
	public String extractCounrtyIMDB() {
		
		String country;
		
		imdbPage obj = new imdbPage(driver);
		country=obj.orgineCountry.getText();
		return(country);
	}
	
	//Extracts origin country of the movie
	
	public void goToSiteWiki() {
		
		wikiPage obj = new wikiPage(driver);
		driver.navigate().to(prop.getProperty("WikiURL"));
		wait.until(ExpectedConditions.elementToBeClickable(obj.searchInput));		
	}
	
	//Navigates from IMDB page to Wikipedia page
	
	public void searchFilmWiki() {
		
		wikiPage obj = new wikiPage(driver);
		obj.searchInput.sendKeys(prop.getProperty("MovieName"));
		obj.searchButton.click();
		wait.until(ExpectedConditions.visibilityOf(obj.wikiLogo)); // Wikipedia logo at top left corner
		
	}
	//Searches film after input name and waits till Wiki is loaded
	
	public String extractDateWiki() {
		
		wikiPage obj = new wikiPage(driver);
		String date= new String();
		date=returnListString(obj.dataTableKey,obj.dataTableValue,prop.getProperty("dataTableDateKey"));
		return(date);
	}
	
	/*Extracts all column webelements from the details datatable into two lists,
	 * Calls the returnListString function 
	 * returns the date 
	 */
	
	public String extractCountryWiki() {
		
		wikiPage obj = new wikiPage(driver);
		String country= new String();
		country=returnListString(obj.dataTableKey,obj.dataTableValue,prop.getProperty("dataTableCountryKey"));
		return(country);		
	}
	
	/*Extracts all column webelements from the details datatable into two lists,
	 * Calls the returnListString function 
	 * returns the country
	 */
	
	public void quitBrowser() {
		
		driver.quit();
	}
	
	
}
