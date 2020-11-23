package com.get.project.MavenAppium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumTest {
	public static WebDriver driver ;

	@Test
	public void launchsite() throws IOException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\KUNAL BHATTA\\Desktop\\chromedriver.exe");
	    driver = new ChromeDriver();
		driver.get("https://jio.com");
		String title = driver.getTitle();
		System.out.println(title);
		//Assert.assertEquals(title, "Jio Network - Jio Fiber Internet Connection & Jio ");
		//screenshot();
		
	}
	public static void screenshot(String s) throws IOException {
		TakesScreenshot ts =((TakesScreenshot)driver);
		File SrcFile=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(SrcFile, new File(System.getProperty("user.dir")+"\\FailedTestCase\\"+s+".png"));
	}

}
