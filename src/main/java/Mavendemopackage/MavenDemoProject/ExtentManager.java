package Mavendemopackage.MavenDemoProject;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager extends BaseTest
{
	public static ExtentReports report;
	public static ExtentReports getInstance()
	{
		if(report==null)
		{
			report=new ExtentReports(projectpath+"//HtmlReports//report.html");
			report.loadConfig(new File(projectpath+"//extentreportconfig.xml"));
			report.addSystemInfo("Selenium language Binding","3.141.59").addSystemInfo("Environment",mainprop.getProperty("env"));
			
		}
		return report;
	}

}
