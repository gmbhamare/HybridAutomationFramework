package com.flipkart.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//button[contains(@class,'_2KpZ6l _2doB4z')]")
	private WebElement closeBtnOnPopup;
	
	
	public LoginPage clickClosBtn() {
		getCloseBtnOnPopup().click();
		return new LoginPage(driver);
	}


	public WebElement getCloseBtnOnPopup() {
		return closeBtnOnPopup;
	}


	public void setCloseBtnOnPopup(WebElement closeBtnOnPopup) {
		this.closeBtnOnPopup = closeBtnOnPopup;
	}
	
}
