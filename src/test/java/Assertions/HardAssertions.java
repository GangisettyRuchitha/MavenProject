package Assertions;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Mavendemopackage.MavenDemoProject.BaseTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class HardAssertions extends BaseTest
{
  @Test
  public void f() 
  {
	  String actualTitle=driver.getTitle();
	  System.out.println(actualTitle);
	  String expectedTitle="Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
	  Assert.assertEquals(actualTitle, expectedTitle,"Titels both are equal....");
	  
	  
	 String actualLink=driver.findElement(By.linkText("Customer Service")).getText(); 
	 String expectedLink="Customer Service";
	 
	 //Assert.assertEquals(actualLink, expectedLink);
	 
	 Assert.assertTrue(actualLink.equals(expectedLink));
	 //Assert.assertTrue(actualLink.equals(expectedLink), "Both links are not equal....");
	 
  }
  @BeforeMethod
  @Parameters("browser")
  public void beforeMethod(String bType) throws Exception 
  {
	  System.out.println("before method");
	  init();
		test = report.startTest("TNG_001");
		test.log(LogStatus.INFO, "loading the Properties files.....");
				
		launch(bType);
		test.log(LogStatus.PASS, "Launching the Browser : " + bType);
				
		navigateUrl("amazonurl");
		test.log(LogStatus.PASS, "Navigated to url : " + childprop.getProperty("amazonurl"));
  }

  @AfterMethod
  public void afterMethod() throws Exception 
  {
	  System.out.println("after method");
	  waitingProcess();
		
		closeBrowser();
		test.log(LogStatus.PASS, "Closing the browser : ");
		
		
		report.endTest(test);
		report.flush();
  }

}
