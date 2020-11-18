package com.vendantu.common.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CommonHelperMethods {

	public CommonHelperMethods() {

	}

	public String takeScreenShot(WebDriver driver) {

		File file = new File(Constants.SCREENSHOT_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}

		Date date = new Date();
		String screenShotFile = date.toString().replace(":", "_").replace(" ", "_") + ".png";
		String screenShotPath = Constants.SCREENSHOT_PATH + screenShotFile;

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(screenShotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenShotPath;
	}

	public String getTestCaseType(String packageName) {
		String testType = null;
		if (packageName.contains("ui.tests")) {
			testType = "UI Tests";
		}
		return testType;
	}

	public String getOSType() {
		String osName = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
		if (osName.contains("windows")) {
			osName = "windows";
		} else if (osName.contains("linux") || osName.contains("freebsd") || osName.contains("irix")
				|| osName.contains("digital unix") || osName.contains("unix")) {
			osName = "linux";
		} else if (osName.contains("mac")) {
			osName = "mac";
		} else {
			osName = "other";
		}
		return osName;
	}
	
}
