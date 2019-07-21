package com.tests.flight;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.objects.common.BasePage;
import com.objects.common.commonObjects;
import com.objects.homePage.homePageObjects;

public class HomePageTests {

	homePageObjects homePage;
	BasePage bp;
	commonObjects co;

	public HomePageTests() {
		homePage = new homePageObjects();
		bp = new BasePage();
		co = new commonObjects();
	}

	@DataProvider(name = "details")
	public static Object[][] data() {
		return new Object[][] { { "Abuthaheer", "test123" }, { "Abuthaheer1", "test1234" } };
	}

	@Test(dataProvider = "credentials")
	public void loginTest(String name, String password) {
		homePage.clickLnkHome();
		homePage.enterUserName(name);
		homePage.enterPassWord(password);
		homePage.clickLogin();
		Assert.assertTrue(bp.elementFound(co.getSignOff()));
	}
}
