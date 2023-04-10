package com.flipkart.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.flipkart.qa.base.Base;
import com.flipkart.qa.pages.HomePage;
import com.flipkart.qa.pages.LoginPage;
import com.flipkart.qa.utils.CommonUtils;

public class LoginTest extends Base {

	public LoginTest() {
		super();
	}

	public WebDriver driver;
	HomePage homePage;
	LoginPage login;

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndLaunchApp(prop.getProperty("browser"));
		homePage = new HomePage(driver);
		
	}

	@DataProvider(name = "validLoginCredentials")
	public Object[][] provideTestData() {

		Object[][] data = CommonUtils.getTestDataFromExcel("Login");
		return data;
	}

	@Test(dataProvider = "validLoginCredentials")
	public void verfiyLoginWithValidCredentials(String email, String password) throws InterruptedException {

		login= homePage.clickClosBtn();
		Thread.sleep(1000);
		login.clickLoginBtn();
		login.enterEmailID(email);
		login.requestOTP();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
