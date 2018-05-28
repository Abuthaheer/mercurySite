package com.objects.flights;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.objects.common.BasePage;

public class BookAFlightObject extends BasePage {

	@FindBy(name = "passCount")
	private WebElement passengers;

	@FindBy(name = "fromPort")
	private WebElement departFrom;

	@FindBy(name = "fromMonth")
	private WebElement departMonth;

	public BookAFlightObject() {
		PageFactory.initElements(driver, this);
	}

	public String selectPassengers(String count) {
		return selectFromDropDown(passengers, count);
	}

	public String selectDepartFrom(String name) {
		return selectFromDropDown(departFrom, name);
	}

	public String selectMonth(String month) {
		return selectFromDropDown(departMonth, month);
	}

	
}
