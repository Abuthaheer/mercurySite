package com.tests.flight;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.objects.common.BasePage;
import com.objects.common.commonObjects;
import com.objects.flights.BookAFlightObject;
import com.objects.homePage.homePageObjects;

public class BookAFlightTests {

	BookAFlightObject bookFlight;
	homePageObjects homePage;
	commonObjects commonPage;
	BasePage bp;

	public BookAFlightTests() {
		bookFlight = new BookAFlightObject();
		homePage = new homePageObjects();
		commonPage = new commonObjects();
		bp = new BasePage();
	}

	@Parameters({"UserName", "PassWord"})
	@Test()
	public void loginTest(String name, String password) {
		homePage.clickLnkHome();
		homePage.enterUserName(name);
		homePage.enterPassWord(password);
		homePage.clickLogin();
		Assert.assertTrue(bp.elementFound(commonPage.getSignOff()));
	}

	@BeforeMethod
	public void beforeMethod() {
		homePage.clickLnkFlights();
	}

	@AfterMethod
	public void afterMethod() {
		homePage.clickLnkHome();
	}

	@Test
	public void verifyDropDownFrom() {
		String place = "London";
		String selectedPassenger = bookFlight.selectDepartFrom(place);
		Assert.assertTrue(selectedPassenger.equalsIgnoreCase(place));
	}

	@Test
	public void verifyDropDownPassengers() {
		String passengers = "3";
		String selectedPassenger = bookFlight.selectPassengers(passengers);
		Assert.assertTrue(passengers.equalsIgnoreCase(selectedPassenger.trim()));
	}

	@AfterClass
	public void afterClass() {
		commonPage.getSignOff().click();
		bp.quitDriver();
	}
}