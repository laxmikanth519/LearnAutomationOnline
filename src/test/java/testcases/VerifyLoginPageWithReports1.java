package testcases;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import factory.BrowserFactory;
import factory.DataProviderFactory;
import pages.HomePage;
import pages.LoginPage;
import utility.Helper;

public class VerifyLoginPageWithReports1
{
WebDriver driver;
ExtentReports report;
ExtentTest logger;

@BeforeMethod
public void setUP()
{
	report=new ExtentReports(".\\Reports\\LoginPageReport.html",true);
	logger=report.startTest("Verify Test login");
driver=BrowserFactory.getBrowser("firefox");
driver.get(DataProviderFactory.getConfig().getApplicationUrl());
logger.log(LogStatus.INFO, "Application is up and running");
}

@Test
public void testHomePage()
{
	HomePage home=PageFactory.initElements(driver, HomePage.class);
	String title=home.getApplicationTitle();
	Assert.assertTrue(title.contains("Demo Store"));
	logger.log(LogStatus.PASS, "Home Page loaded and Verified");
	home.clickOnSignLink();
	logger.log(LogStatus.INFO, "click on signout link");
	LoginPage login=PageFactory.initElements(driver, LoginPage.class);
	login.loginApplication(DataProviderFactory.getExcel().getData(0, 0, 0), DataProviderFactory.getExcel().getData(0, 0, 1));
	logger.log(LogStatus.INFO, "login to application");
	login.verifySignOutLink();
	logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captureScreenshot(driver, "validation2")));
	logger.log(LogStatus.PASS, "signout link is present");
}

@AfterMethod
public void tearDown(ITestResult result)
{
	if(result.getStatus()==ITestResult.FAILURE)
	{
		String path=Helper.captureScreenshot(driver, result.getName());
		logger.log(LogStatus.FAIL, logger.addScreenCapture(path));
	}
	BrowserFactory.closeBrowser(driver);
	report.endTest(logger);
	report.flush();
}
}