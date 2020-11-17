package com.vendantu.util;

public class Constants {

	public static String SCREENSHOT_PATH = System.getProperty("user.dir") + "/report/screenShots/";
	public static String DATA_CSV_PATH = System.getProperty("user.dir") + "/data/TestData.csv";
	public static String NAVI_INPUT_CSV_FILE_PATH = System.getProperty("user.dir") + "/data/TestData.csv";

	public static String TESTDATA_SHEET_UI = "TestDataUI";

	/* Browsers Driver */
	public static String MAC_CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/mac/chromedriver";
	public static String MAC_GEKO_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/mac/geckodriver";
	public static String LINUX_CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/linux/chromedriver";
	public static String LINUX_GEKO_DRIVER_PATH = System.getProperty("user.dir") + "/drivers/linux/geckodriver";
	public static int IMPLICIT_WAIT = 40;

	// public static String BROWSER_TYPE = "Mozilla"; /* For running the test cases
	// on Mozilla Browser*/
	public static String BROWSER_TYPE = "Chrome"; /* For running the test cases on Chrome Browser */
	public static String VEDANTU_URL = "https://www.vedantu.com/test/PUBLIC/5f8eb7b9dae9893894b0c034";

	/* Extent Report Properties */
	public static String ALL_REPORTS_PATH = System.getProperty("user.dir") + "/report/";
	public static String REPORTS_PATH = System.getProperty("user.dir") + "/report/htmlReports/";
	public static String REPORTS_NAME = "VEDANTU";
	public static String DOCUMENT_TITLE = "Vedantu Test Flow Automation Report";

}
