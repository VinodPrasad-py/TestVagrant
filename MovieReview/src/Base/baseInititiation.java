package Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class baseInititiation {
	
	public WebDriver driver;
	public Actions act;
	public WebDriverWait wait;
	public JavascriptExecutor JS;
	
	private Properties ProptertiesLoader() throws IOException{
		
		Properties prop = new Properties();
		FileInputStream file1 = new FileInputStream("./src\\Base\\base.properties");
		prop.load(file1);
		//invoke base properties file for driver path depending on choice
	    return(prop);
	}
	
	
	public WebDriver InitiateDriver() throws IOException {
		
		Properties prop = ProptertiesLoader();
		String Browser = prop.getProperty("Browser");
		//by default it invoke chrome driver, can be changed by properties 
		
		if(Browser.equals("Chrome")) {
			
			String DriverPath = prop.getProperty("ChromeDriver");
			System.setProperty("webdriver.chrome.driver", DriverPath);
			driver = new ChromeDriver();
		}
		else if(Browser.equals("IE")) {
			
			String DriverPath = prop.getProperty("IEDriver");
			System.setProperty("webdriver.ie.driver", DriverPath);
			driver = new InternetExplorerDriver();
		}
		else if(Browser.equals("Edge")) {
			
			String DriverPath = prop.getProperty("EdgeDriver");
			System.setProperty("webdriver.edge.driver", DriverPath);
			driver = new EdgeDriver();
		}
		else if(Browser.equals("Firefox")) {
			
			String DriverPath = prop.getProperty("FireFoxDriver");
			System.setProperty("webdriver.chrome.driver", DriverPath);
			driver = new FirefoxDriver();
		}
		
		return driver;
		
	
	}
	
	public WebDriverWait InitiateWait(WebDriver Driver) {
		
		wait = new WebDriverWait(Driver, 15);
		return wait;
	}
	
	public Actions InitiateActions(WebDriver Driver) {
		
		act = new Actions(Driver);
		return act;
	}

}
