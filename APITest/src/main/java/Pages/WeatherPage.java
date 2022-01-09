package Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Utils.RunConfig;

public class WeatherPage {
	public static int getUITempValue(WebDriver driver,String city) throws InterruptedException {
		
		driver.get(RunConfig.Test_ApplicationURL);
		 List<WebElement> understandEle=driver.findElements(By.xpath("//div[@class='banner-button policy-accept']"));
		if(understandEle.size()!=0) {
			for (WebElement webElement : understandEle) {
				webElement.click();
			}
		}
		WebElement searchBar=driver.findElement(By.xpath("//input[@placeholder='Search']"));
		searchBar.sendKeys(city+Keys.ENTER);
		TimeUnit.SECONDS.sleep(2);
		WebElement Suggest=driver.findElement(By.xpath("//div[@class='content-module']//a[1]"));
		Suggest.click();
		TimeUnit.SECONDS.sleep(2);
		List<WebElement> totalFrames = driver.findElements(By.id("google_ads_iframe_/6581/web/in/interstitial/admin/search_0"));

		if(!(totalFrames.size()==0)) {
			System.out.println("No of frames "+totalFrames.size());
			driver.switchTo().frame("google_ads_iframe_/6581/web/in/interstitial/admin/search_0");
			WebElement dismissButton=driver.findElement(By.xpath("//div[@id='dismiss-button']/div"));
			dismissButton.click();
			driver.switchTo().defaultContent();
		}

		WebElement tempEle=driver.findElement(By.xpath("//div[@class='temp'][1]"));
		StringBuffer sb= new StringBuffer(tempEle.getText().toString());  
		for(int i=1;i<=2;i++) {
			sb.deleteCharAt(sb.length()-1); 
		}
		String Stringcount=sb.toString();
		int actualcount=Integer.parseInt(Stringcount);
		System.out.println("Actual temp value for "+city+" is "+actualcount);
		return actualcount;
	}
	
}
