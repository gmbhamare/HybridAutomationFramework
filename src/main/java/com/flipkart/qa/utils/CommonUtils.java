package com.flipkart.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.formula.functions.Replace;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class CommonUtils {

	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIMEOUT = 5;

	public static String createNewEmailIds() {
		Date date = new Date();
		String getNewTime = date.toString().replace(" ", "_").replace(":", "_");
		return "gauravbhamare24" + getNewTime + "@gmail.com	";
	}

	public static String getTimestampDate() {
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}

	public static Object[][] getTestDataFromExcel(String sheetName) {
		XSSFWorkbook workbook = null;
		try {
			File excelFile = new File(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\flipkart\\qa\\testData\\flipkart_Web_testData.xlsx");
			FileInputStream fisExcel = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fisExcel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][columns];

		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < columns; j++) {

				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();

				switch (cellType) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;

				}
			}
		}
		return data;
	}

	public static String captureScreenshots(WebDriver driver, String testName) {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + getTimestampDate()+ ".png";
		try {
			FileHandler.copy(screenshot, new File(destinationPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationPath;
	}

}
