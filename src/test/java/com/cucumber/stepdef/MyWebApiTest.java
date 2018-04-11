package com.cucumber.stepdef;

import java.util.List;

import org.junit.Assert;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.tools.SoapUITestCaseRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyWebApiTest {

	@Given("^user is on homepage$")
	public void user_is_on_homepage() throws Throwable {

		System.out.println("user_is_on_homepage");
		
		testTestCaseRunner();
	}

	@When("^user clicks on Sign in button$")
	public void user_clicks_on_Sign_in_button() throws Throwable {
		System.out.println("user_clicks_on_Sign_in_button");

	}

	@Then("^user is displayed login screen$")
	public void user_is_displayed_login_screen() throws Throwable {

		System.out.println("user_is_displayed_login_screen");

	}

	public void testRunner() throws Exception 
	{
		SoapUITestCaseRunner runner = new SoapUITestCaseRunner(); 
		runner.setProjectFile( "src/dist/sample-soapui-project.xml" );
		runner.run(); 
	}

	public void testTestCaseRunner() throws Exception 
	{
		WsdlProject project = new WsdlProject("C:\\Test_WorkSpace\\SOAP-UI\\globalweather-soapui-project.xml"); 
		List<TestSuite> testSuiteList = project.getTestSuiteList();

		// Iterate all TestSuites of project
		for (TestSuite ts : testSuiteList) 
		{
			System.out.println("****Running Test suite " + ts.getName() + "********");

			// Retrieve all TestCases from a particular TestSuite
			List<TestCase> testCaseList = ts.getTestCaseList();

			// Iterate all TestCases of the particular TestSuite
			for (TestCase testcase : testCaseList) 
			{
				System.out.println("****Running Test Case " + testcase.getName()+ "*****");

				if(testcase.getName().equals("TestCase 1 - Mahima"))
				{
					// Run the specific TestCase
					TestRunner testRunner = testcase.run(new PropertiesMap(), false);
					//verify where test case pass or not
					Assert.assertEquals(TestRunner.Status.FINISHED, testRunner.getStatus());
				}
			}
		}

		//WsdlTestSuite testSuite = project.getTestSuiteByName("Test Suite"); 
		//WsdlTestCase testCase = testSuite.getTestCaseByName( "Test Conversions" );

		// create empty properties and run synchronously
		//WsdlTestCaseRunner runner = testCase.run( new PropertiesMap(), false ); 
		//Assert.assertEquals(Status.FINISHED, runner.getStatus()); 

	}
}