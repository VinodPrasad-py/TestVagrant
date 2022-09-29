package ObjectRepos;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class wikiPage {

	
	public WebDriver Driver;
	
	public wikiPage(WebDriver Driver){
		
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}
	
	@FindBy(id="searchInput")
	public WebElement searchInput;
	
	@FindBy(xpath="//button[@type='submit']")
	public WebElement searchButton;	
	
	
    @FindBy(xpath="//table[@class='infobox vevent']/tbody/tr/th")
    public List<WebElement> dataTableKey;

    @FindBy(xpath="//table[@class='infobox vevent']/tbody/tr/td")
    public List<WebElement> dataTableValue;
    
	@FindBy(xpath="//*[@title='Visit the main page']")
	public WebElement wikiLogo;	  
	
	
	
	
}
