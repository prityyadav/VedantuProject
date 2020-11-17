package com.vendantu.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vendantu.base.UiBaseSetup;

public class InstructionPage extends UiBaseSetup {

	WebDriver driver;
	WebElement element;

	public InstructionPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	public WebElement instructionPopUp() {
		element = driver.findElement(By.xpath("//div[@class='testName']"));
		return element;
	}

	public WebElement startTest() {
		element = driver.findElement(By.xpath("//div[@class='button']"));
		return element;
	}

	public String getinstructionPopUp() {
		return readText(instructionPopUp());

	}

	public String getStartTest() {
		return readText(startTest());

	}

	public void clickOnStartTest() {

		click(startTest());
		stopTheScript(10);

	}
}
