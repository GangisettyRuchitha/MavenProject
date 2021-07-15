package Mavendemopackage.MavenDemoProject;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;

public class TNG_001 extends BaseTest
{
	@Test(groups= {"regression","sanity"})
	  public void amazon() 
	  {
		  System.out.println("f Test");
		  selectOption("amazonsearchdrop_id", "Books");
			test.log(LogStatus.PASS, "Selected the option Books By using the locator  :- " + orProp.getProperty("amazonsearchdrop_id"));
			
			typeText("amazonsearchtextbox_name", "Harry Potter");
			test.log(LogStatus.PASS, "Entered the text Harry Potter By using the locator :- " + orProp.getProperty("amazonsearchtextbox_name"));
			
			clickElement("amazonsearchbutton_xpath");
			test.log(LogStatus.PASS, "Clicked on Search Button By using the locator :- " + orProp.getProperty("amazonsearchbutton_xpath"));
	  }
	  
	  @BeforeMethod(groups= {"regression","sanity"})
	  @Parameters("browser")
	  public void stratProcess(String bType) throws Exception 
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

	  @AfterMethod(groups= {"regression","sanity"})
	  public void endProcess() throws Exception 
	  {
		  System.out.println("after method");
		  waitingProcess();
			
			closeBrowser();
			test.log(LogStatus.PASS, "Closing the browser : ");
			
			
			report.endTest(test);
			report.flush();

	  }
  

}
