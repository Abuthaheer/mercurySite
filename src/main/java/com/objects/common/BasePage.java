package com.objects.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BasePage {

	public static WebDriver driver;
	public String browser;
	JsonObject jsonObject = readJSon();

	public BasePage() {
		if (driver == null) {

			browser = jsonObject.get("browser").getAsString();
			if (browser.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"C:/Users/35775/Desktop/automation/chromedriver_win32/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						"C:\\Users\\35775\\Downloads\\geckodriver-v0.20.1-win64\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.get(jsonObject.get("URL").getAsString());

		}
	}

	public JsonObject readJSon() {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = null;
		try {
			File f1 = new File("./src/test/resources/configuration.json");
			Object obj = parser.parse(new FileReader(f1.getAbsolutePath()));
			jsonObject = (JsonObject) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public boolean elementFound(WebElement element) {
		boolean res = false;
		try {
			res = element.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public void setText(WebElement element, String name) {
		if (name != null) {
			element.clear();
			element.sendKeys(name);
		}
	}

	public String getTxtAttribute(WebElement element) {
		return element.getAttribute("value");
	}

	public String selectFromDropDown(WebElement element, String option) {
		Select obj = new Select(element);
		obj.selectByValue(option);
		return obj.getFirstSelectedOption().getText();
	}

	public boolean isElementVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getTitlte() {
		return driver.getTitle();
	}

	public void quitDriver() {
		driver.quit();
	}

	public static List<HashMap<String, String>> readValueFromExcelSheet() {
		List<HashMap<String, String>> mapDatasList = new ArrayList<HashMap<String, String>>();
		try {
			File excelLocaltion = new File("./Excel/Facebook.xlsx");
			String sheetName = "Sheet1";
			FileInputStream f = new FileInputStream(excelLocaltion.getAbsolutePath());
			Workbook w = new HSSFWorkbook(f);
			Sheet sheet = w.getSheet(sheetName);
			Row headerRow = sheet.getRow(0);
			for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
				Row currentRow = sheet.getRow(i);
				HashMap<String, String> mapDatas = new HashMap<String, String>();
				for (int j = 0; j < headerRow.getPhysicalNumberOfCells(); j++) {
					Cell currentCell = currentRow.getCell(j);
					switch (currentCell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						mapDatas.put(headerRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						mapDatas.put(headerRow.getCell(j).getStringCellValue(),
								String.valueOf(currentCell.getNumericCellValue()));
						break;
					}
				}
				mapDatasList.add(mapDatas);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return mapDatasList;
	}

}
