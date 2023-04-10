package com.flipkart.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.flipkart.qa.utils.CommonUtils;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public Base() {

		prop = new Properties();
		dataProp = new Properties();
		File propFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\flipkart\\qa\\config\\config.properties");

		File datapropFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\flipkart\\qa\\testdata\\testData.properties");
		try {
			FileInputStream configFis = new FileInputStream(propFile);
			FileInputStream dataFIS = new FileInputStream(datapropFile);
			prop.load(configFis);
			prop.load(dataFIS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver initializeBrowserAndLaunchApp(String browserName) {

		switch (browserName) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Browser option is not available, kindly select a proper browser");
			break;
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(CommonUtils.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(CommonUtils.PAGE_LOAD_TIMEOUT));
		driver.get(prop.getProperty("url"));

		return driver;
	}

}
