package com.vendantu.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vendantu.base.UiBaseSetup;

public class DifferentSectionPage extends UiBaseSetup {

	WebDriver driver;
	WebElement element;
	List<WebElement> elements;

	public DifferentSectionPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	public List<WebElement> differentSections() {
		elements = driver.findElements(By.xpath("//div[@class='CategoryHeaderMain']/div"));
		return elements;
	}

	public WebElement endTest() {

		element = driver.findElement(By.xpath("//div[@class='v-button endTest endTestMains']"));
		return element;
	}

	public WebElement earlyLeavingPopUp(String text) {

		element = driver.findElement(By.xpath("//div[@class='option-container']/div[text()='" + text + "']"));
		return element;
	}

	public WebElement submitAndLeave() {
		element = driver.findElement(By.xpath("//div[@class='v-button submitLeave']"));
		return element;
	}

	public List<String> verifyDifferentSections() {

		List<WebElement> differentSections = new ArrayList<WebElement>();
		List<String> listOfSections = new ArrayList<String>();

		differentSections = differentSections();
		for (WebElement element : differentSections) {
			listOfSections.add(element.getText());
		}
		return listOfSections;

	}

	public String verifyEndTest() {

		return readText(endTest());
	}

	public String verifyEarlyLeavingPopUp(String text) {

		click(endTest());
		String earlyPopupMsg = readText(earlyLeavingPopUp(text));
		click(earlyLeavingPopUp(text));
		return earlyPopupMsg;

	}

	public void verifySubmitAndLeave() {
		String verifySubmitText = readText(submitAndLeave());
		click(submitAndLeave());

	}
}
