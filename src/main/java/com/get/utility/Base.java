package com.get.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.sql.Driver;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {
	
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;
	public AppiumDriverLocalService startServer() {
		int port = 4723;
		boolean flag = checkIfServerIsRunning(4723);
		if(!flag) {
			
		
		service=AppiumDriverLocalService.buildDefaultService();
		service.start();
		
		}
		return service;
	}
	public static boolean checkIfServerIsRunning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			isServerRunning = true;
			
		}
		finally {
			serverSocket = null;
		}
		return isServerRunning;
		
	}
	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(8000);
	}

	public static AndroidDriver<AndroidElement> caps(String app) throws IOException, InterruptedException {
		
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\get\\utility\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
File f = new File("src");
File fs = new File(f,(String) prop.get(app));
String device =(String)prop.get("device");
if(device.contains("emulator")) {
	startEmulator();
}
DesiredCapabilities caps = new DesiredCapabilities();

caps.setCapability(MobileCapabilityType.DEVICE_NAME, device);
caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
//caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
caps.setCapability("chromedriverExecutable", "C:\\Users\\KUNAL BHATTA\\Desktop\\chromedriver.exe");
caps.setCapability(MobileCapabilityType.APP,fs.getAbsolutePath());
driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),caps);
return driver;
	}
public static void screenshot(String s) throws IOException {
	File srcfile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(srcfile, new File(System.getProperty("user.dir")+"\\FailedTestCase\\"+s+".png"));
}
}
