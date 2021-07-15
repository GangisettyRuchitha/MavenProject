

package Mavendemopackage.MavenDemoProject;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTest 
{
	public static WebDriver driver;
	public static String projectpath=System.getProperty("user.dir");
	public static Properties p;
	public static Properties mainprop;
	public static Properties childprop;
	public static Properties log4jprop;
	public static FileInputStream fis;
	public static Properties orProp;
	public static ExtentTest test;
	public static ExtentReports report;
	public static String fname;
	
	static
	{
		SimpleDateFormat sf=new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss");
		Date d=new Date();
		fname=sf.format(d)+".png";
	}
	
	public static void init() throws Exception
	{
		fis=new FileInputStream(projectpath+"\\data.properties");
		p=new Properties();
		p.load(fis);
		
		fis=new FileInputStream(projectpath+"\\Environment.properties");
		mainprop=new Properties();
		mainprop.load(fis);
		String e=mainprop.getProperty("env");
		System.out.println(e);
		
		fis=new FileInputStream(projectpath+"\\"+e+".properties");
		childprop=new Properties();
		childprop.load(fis);
		String url=childprop.getProperty("googleurl");
		System.out.println(url);
		
		fis =new FileInputStream(projectpath + "\\or.propertirs");
		orProp = new Properties();
		orProp.load(fis);
		
		fis=new FileInputStream(projectpath+"//log4jconfig.properties");
		PropertyConfigurator.configure(fis);
		//log4jprop=new Properties();
		//log4jprop.load(fis);
		
		report=ExtentManager.getInstance();
	}
	public static void launch(String browser)
	
	{
		if(browser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", projectpath+"\\drivers\\chromedriver.exe");
			
			ChromeOptions option=new ChromeOptions();
			option.addArguments("user-data-dir=C:\\Users\\ravir\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 2");
			option.addArguments("--disable-notifications");
			
			driver=new ChromeDriver(option);
		}
		else if(browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", projectpath+"\\drivers\\geckodriver.exe");
			
			ProfilesIni p=new ProfilesIni();
			FirefoxProfile profile=p.getProfile("default");
			profile.setPreference("dom.webnotifications.enabled", false);
			
			FirefoxOptions option=new FirefoxOptions();
			option.setProfile(profile);
			driver=new FirefoxDriver(option);
		}
		else if(p.getProperty(browser).equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", projectpath+"\\drivers\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		else if(p.getProperty(browser).equals("edge"))
		{
			System.setProperty("webdriver.edge.driver", projectpath+"\\drivers\\msedgedriver.exe");
			driver=new EdgeDriver();
		}
		
		
		
	}
	public static void navigateUrl(String url)
	{
		driver.get(childprop.getProperty(url));
	}
	public static void maximize()
	{
		driver.manage().window().maximize();
	}
	public static void waitingProcess() throws Exception
	{
		Thread.sleep(5000);
	}
	
	public static void closeBrowser()
	{
		driver.close();
	}
	public static void clickElement(String locatorKey) 
	{
		getElement(locatorKey).click();
		//driver.findElement(By.xpath(orProp.getProperty(locatorKey))).click();
	}

	public static void typeText(String locatorKey, String text) 
	{
		getElement(locatorKey).sendKeys(text);
		//driver.findElement(By.name(orProp.getProperty(locatorKey))).sendKeys(text);
	}

	public static void selectOption(String locatorKey, String option) 
	{
		getElement(locatorKey).sendKeys(option);
		//driver.findElement(By.id(orProp.getProperty(locatorKey))).sendKeys(option);
	}
	
	public static WebElement getElement(String locatorKey)
	{
		WebElement element=null;
		
		if(locatorKey.endsWith("_id")) {
			element = driver.findElement(By.id(orProp.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_name")) {
			element = driver.findElement(By.name(orProp.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_classname")) {
			element = driver.findElement(By.className(orProp.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_xpath")) {
			element = driver.findElement(By.xpath(orProp.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_css")) {
			element = driver.findElement(By.cssSelector(orProp.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_linktext")) {
			element = driver.findElement(By.linkText(orProp.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_partiallinktext")) {
			element = driver.findElement(By.partialLinkText(orProp.getProperty(locatorKey)));
		}
		return element;
		
	}
	public static void data()
	{
		driver.findElement(By.name("q")).sendKeys("ruchi");
	}

}
