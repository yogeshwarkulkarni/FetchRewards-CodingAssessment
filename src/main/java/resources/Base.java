package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base 
{
	public WebDriver driver;
	public Properties prop;
	public WebDriver initializedDriver() throws IOException 
	{
		prop = new Properties();
		String workingDir = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(workingDir +"\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		String browsername = prop.getProperty("browser");
		System.out.println("\n\nBrowser :" + browsername);
		if (browsername.equals("Chrome")) {
			
			String chromeDriverExe = prop.getProperty("chromeDriverExePath");
			System.setProperty("webdriver.chrome.driver",chromeDriverExe);
			
			ChromeOptions handlingSSL = new ChromeOptions();
			handlingSSL.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(handlingSSL);
		} else if (browsername.equals("Firefox")) {

		} else if (browsername.equals("IE")) {

		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}	
}
