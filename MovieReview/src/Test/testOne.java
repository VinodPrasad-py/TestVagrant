package Test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.baseInititiation;
import Functions.functionsOne;

public class testOne extends baseInititiation {
	
	private Properties prop;
	private functionsOne Function;
	
    @BeforeTest
	public void Initiate() throws IOException {
		
	driver=InitiateDriver();
	wait=InitiateWait(driver);
	act=InitiateActions(driver);
	//Reports = InitiateReport(this.getClass().getName());
	prop = new Properties();
	JS= (JavascriptExecutor) driver;
	
	//Use all instantiation methods to methods to instantiate inherited variables
	FileInputStream File = new FileInputStream("./src\\Data\\functionsOne.properties");
	prop.load(File);
	Function = new functionsOne(driver,act,prop,wait,JS);
	//instantiates the variable with functionsOne to access methods
	}
    
    @Test
    public void compareDetails() {
    	
    	String dateIMDB = new String();
    	String dateWiki = new String();
    	String countryIMDB = new String();
    	String countryWiki = new String();
    	
    	Function.goToSiteIMDB();
    	Function.searchFilmIMDB();
    	dateIMDB = Function.extractDateIMDB();
    	countryIMDB = Function.extractCounrtyIMDB();
    	
    	// From IMBD to Wikipedia navigation
    	
    	Function.goToSiteWiki();
    	Function.searchFilmWiki();
    	dateWiki = Function.extractDateWiki();
    	countryWiki = Function.extractCountryWiki();
        Function.quitBrowser();
        
        //Details stored in local variable and now validated 
  
    	AssertJUnit.assertEquals(countryIMDB, countryWiki);
    	AssertJUnit.assertEquals(dateIMDB, dateWiki);
    }
}
