package ObjectRepos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class imdbPage {
	
	public WebDriver Driver;
	public Properties prop;
	
	public imdbPage(WebDriver Driver){
		
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);

	}

	@FindBy(id="suggestion-search") // Search bar in home page
	public WebElement searchInput;
	
	@FindBy(xpath="//*[@id='iconContext-magnify'][1]") // Search Icon in home page
	public WebElement searchIcon;
	
	@FindBy(xpath="//h3[text()='Titles']") // Titles text in search results page (Used for wait)
	public WebElement titlesText;
	
	@FindBy(xpath="//li[@data-testid='title-details-releasedate']/div[1]") // release date parent to child
	public WebElement releaseDate;
	
	@FindBy(xpath="//li[@data-testid='title-details-origin']/div[1]") // origin country parent to child
	public WebElement orgineCountry;	
	
	@FindBy(xpath="//span[text()='All topics']") //icon in movie details page (Used for wait)
	public WebElement allTopicsIcon;	
}
