package com.objects.homePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.objects.common.BasePage;

public class homePageObjects extends BasePage {

	@FindBy(name = "userName")
	private WebElement userName;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(name = "login")
	private WebElement login;

	@FindBy(xpath = ".//*[@alt='Specials']")
	private WebElement specials;
	
	@FindBy(linkText = "Home")
	private WebElement lnkHome;
	
	@FindBy(linkText = "Flights")
	private WebElement lnkFlights;

	public homePageObjects() {
		PageFactory.initElements(driver, this);
	}

	public void enterUserName(String name) {
		userName.sendKeys(name);
	}

	public void enterPassWord(String pass) {
		password.sendKeys(pass);
	}

	public void clickLogin() {
		login.click();
	}

	public WebElement getSpecials() {
		return specials;
	}
	
	public void clickLnkHome() {
		lnkHome.click();
	}
	
	public void clickLnkFlights() {
		lnkFlights.click();
	}

}
