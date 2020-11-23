package com.get.project.MavenAppium;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.get.utility.Base;
import com.get.utility.FormPage;
import com.get.utility.Utility;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;


public class E_CommerceTC_04Test extends Base {
@Test
	public  void execution() throws InterruptedException, IOException {
	service=startServer();
		AndroidDriver<AndroidElement> driver = caps("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		FormPage form = new FormPage(driver);
		form.femaleOption.click();
		form.nameField.sendKeys("Hello");
		driver.hideKeyboard();
		form.getCountryName().click();
		Utility utility = new Utility(driver);
		utility.scrollToView("Argentina");
		driver.findElement(By.xpath("//*[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(4000);
		int totalsize = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
		double sum =0;
		for(int i=0;i<totalsize; i++) {
			String amount1 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
			double amount=getAmount(amount1);
			sum+=amount;
		}
		/*String amount1 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText();
		
	double amount1value=getAmount(amount1);
		String amount2 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText();
		
		double amount2value = getAmount(amount2);
		
		double sumOfProducts = amount1value+ amount2value;*/
		String total =driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		 total = total.substring(1);
		 double amount2value1 = Double.parseDouble(total);
		 System.out.println(sum+"amount2value1");
		 org.testng.Assert.assertEquals(sum, amount2value1);
		 //mobile gestures
		 WebElement checkbox = driver.findElement(By.className("android.widget.CheckBox"));
		 TouchAction touchaction = new TouchAction(driver);
		 touchaction.tap(tapOptions().withElement(element(checkbox))).perform();
		 WebElement tc =driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
		 
		 touchaction.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();
		 driver.findElement(By.id("android:id/button1")).click();
		 driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		 service.stop();
		 
	}
@BeforeTest
public void killProcess() throws IOException, InterruptedException {
	Runtime.getRuntime().exec("taskkill /F /IM node.exe");
	Thread.sleep(3000);
}
	public static double getAmount(String value) {
		value = value.substring(1);
		double amount1value = Double.parseDouble(value);
		return amount1value;
	}

}
