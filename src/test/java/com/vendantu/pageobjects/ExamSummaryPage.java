package com.vendantu.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vendantu.base.UiBaseSetup;

public class ExamSummaryPage extends UiBaseSetup {

	WebDriver driver;
	WebElement element;

	public ExamSummaryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	public WebElement examSummary() {
		element = driver.findElement(By.xpath("//div[@class='text-sum']"));
		return element;
	}

	public WebElement submit() {
		element = driver.findElement(By.xpath("//div[@class='onSubmit']"));
		return element;
	}

	public WebElement congratulationMsg() {

		element = driver.findElement(By.xpath("//div[@class='done-text']"));
		return element;
	}

	public WebElement okayText() {
		element = driver.findElement(By.xpath("//div[@class='desc-text']"));
		return element;
	}

	public String verifyExamSummary() {

		return readText(examSummary());

	}

	public void verifyConfiramtionPopup() {

		click(submit());
		String confirmationText = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();

	}

	public String verifyCongratulationMsg() {

		return readText(congratulationMsg());

	}

	public void clickOnOkay() {
		click(okayText());
	}

}
