package com.flipkart.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.flipkart.qa.utils.CommonUtils;
import com.flipkart.qa.utils.ExtentReporter;

public class TestNGListeners implements ITestListener {
	WebDriver driver = null;
	ExtentReports ext;
	ExtentTest etest;

	@Override
	public void onStart(ITestContext context) {
		ext = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {

		etest = ext.createTest(result.getName());
		etest.log(Status.INFO, result.getName() + " Started executing");

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		etest.log(Status.PASS, result.getName() + " Got pass successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String destinationPath = CommonUtils.captureScreenshots(driver, result.getName());

		etest.addScreenCaptureFromPath(destinationPath);
		etest.log(Status.INFO, result.getThrowable());
		etest.log(Status.FAIL, result.getName() + " Got failed");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		etest.log(Status.INFO, result.getThrowable());
		etest.log(Status.SKIP, result.getName() + " Got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {

		ext.flush();

		String extentReportPath = System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html";
		File getReportFile = new File(extentReportPath);

		try {
			Desktop.getDesktop().browse(getReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
