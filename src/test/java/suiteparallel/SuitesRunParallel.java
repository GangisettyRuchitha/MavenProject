package suiteparallel;

import java.util.Arrays;

import org.testng.TestNG;

public class SuitesRunParallel {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		TestNG ng=new TestNG();
		ng.setTestSuites(Arrays.asList(new String[] {System.getProperty("user.dir")+"//megasuite.xml"}));
		ng.setSuiteThreadPoolSize(2);
		ng.run();

	}

}
