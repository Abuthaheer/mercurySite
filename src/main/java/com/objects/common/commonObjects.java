package com.objects.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class commonObjects extends BasePage {

	@FindBy(linkText = "SIGN-OFF")
	private WebElement lnkSignOff;
	
	@FindBy(linkText = "SUPPORT")
	private WebElement lnkSupport;
	
	@FindBy(linkText = "CONTACT")
	private WebElement lnkContact;
	
	
	public commonObjects(){
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getSignOff(){
		return lnkSignOff;
	}
	
	public WebElement getSupport(){
		return lnkSupport;
	}
	
	public WebElement getContact() {
		return lnkContact;
	}
	
	public void clickSignOff(){
		lnkSignOff.click();;
	}
	
	public void clickSupport(){
		lnkSupport.click();;
	}
	
	public void clickContact() {
		lnkContact.click();;
	}
	
	
}
