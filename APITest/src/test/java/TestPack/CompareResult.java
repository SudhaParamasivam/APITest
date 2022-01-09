package TestPack;



import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.DismissAlert;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import API.Weather;
import Pages.WeatherPage;
import Utils.RunConfig;
import jxl.read.biff.BiffException;

public class CompareResult {
	WebDriver driver;
	String[][] data=null;
	@BeforeTest
	public void openUrl() {
		String DriverDtl="Chrome";

		if(DriverDtl.contentEquals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", RunConfig.CHROME_DRIVER_EXE);
			driver= new ChromeDriver();
		}
		else if (DriverDtl.contentEquals("Firefox")) {
			System.setProperty("webdriver.gecko.driver",RunConfig.MOZILLA_DRIVER_EXE);
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();

	}
	@Test(dataProvider = "cityData")
	public void TempCompare(String city) throws InterruptedException, IOException {
		PageFactory.initElements(driver, WeatherPage.class);
		int actualcount = 0;
		try {
			 actualcount=WeatherPage.getUITempValue(driver,city);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.fail("Exception occured "+e);
		}
		//api data
		
		int expectedValue= Weather.getTemp(city);
		System.out.println("Expected API value for "+city+" is "+expectedValue);
		int lowVar=expectedValue-1;
		int highvar=expectedValue+1;
		
		if((actualcount>=lowVar)&&(actualcount<=highvar)) {
			System.out.println("Temperatures are equal");
		}
		else {
			System.out.println("Temperatures are not equal");
			//Assert.fail("Temperatures are not equal");
		}
	}
	@AfterTest
	public void close() {
		driver.close();
	}
	@DataProvider(name="cityData")
	public String[][] cityData() throws BiffException, IOException {
		ExcelData excelData=new ExcelData();
		data=excelData.getExcelData();
		return data;
	}
}
