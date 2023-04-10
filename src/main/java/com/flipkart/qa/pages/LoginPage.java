package com.flipkart.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Login")
	private WebElement loginBtn;

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	public void setLoginBtn(WebElement loginBtn) {
		this.loginBtn = loginBtn;
	}

	public WebElement getEmailTextBox() {
		return emailTextBox;
	}

	public void setEmailTextBox(WebElement emailTextBox) {
		this.emailTextBox = emailTextBox;
	}

	public WebElement getRequestOTPBtn() {
		return requestOTPBtn;
	}

	public void setRequestOTPBtn(WebElement requestOTPBtn) {
		this.requestOTPBtn = requestOTPBtn;
	}

	@FindBy(xpath = "//input[contains(@class,'_2IX_2- VJZDxU')]")
	private WebElement emailTextBox;

	@FindBy(xpath = "//button[text()='Request OT']")
	private WebElement requestOTPBtn;

	public void clickLoginBtn() {
		getLoginBtn().click();
	}

	public void enterEmailID(String emailID) {
		getEmailTextBox().sendKeys(emailID);
	}

	public void requestOTP() {
		getRequestOTPBtn().click();
	}

}
