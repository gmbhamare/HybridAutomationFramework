package com.flipkart.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() {

		ExtentReports extentReports = new ExtentReports();
		Properties configProp = new Properties();
		File configFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\flipkart\\qa\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(configFile);
			configProp.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		File extentReport = new File(
				System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter spark = new ExtentSparkReporter(extentReport);

		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Flipkart Test Automation Report");
		spark.config().setDocumentTitle("Flipkart Automation");
		spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extentReports.attachReporter(spark);
		extentReports.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReports.setSystemInfo("Browser Name", configProp.getProperty("browser"));
		extentReports.setSystemInfo("Username", System.getProperty("user.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));

		return extentReports;

	}

}
