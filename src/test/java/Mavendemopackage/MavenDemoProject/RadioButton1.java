package Mavendemopackage.MavenDemoProject;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;

public class RadioButton1 extends BaseTest
{
  @Test(groups= {"regression","smoke"})
  public void f() 
  {
	  WebElement radio=driver.findElement(By.xpath("(//td[@class='table5'])[2]"));
		List<WebElement> rbutton=radio.findElements(By.name("group1"));
		for(int i=0;i<rbutton.size();i++)
		{
			System.out.println(rbutton.get(i).getAttribute("value")+ "----" + rbutton.get(i).getAttribute("checked"));
		}
  }
  @BeforeMethod(groups= {"regression","smoke"})
  @Parameters("browser")
  public void beforeMethod(String bType) throws Exception 
  {
	  init();
		test = report.startTest("CheckBox1");
		test.log(LogStatus.INFO, "Init the Properties files.....");
		
		launch(bType);
		test.log(LogStatus.INFO, "Opened the browser :-" + bType);
		
		navigateUrl("radiourl");
		test.log(LogStatus.INFO, "Navigated to url :-" + childprop.getProperty("checkboxurl"));

  }

  @AfterMethod(groups= {"regression","smoke"})
  public void afterMethod() throws Exception
  {
	  System.out.println("iam aftermethod....");
	  waitingProcess();
		
		closeBrowser();
		test.log(LogStatus.PASS, "Closing the browser : ");
		report.endTest(test);
		report.flush();
  }

}
