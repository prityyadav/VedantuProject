package com.vendantu.basictestcases;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.vendantu.base.UiBaseSetup;
import com.vendantu.common.util.CommonUtils;
import com.vendantu.pageobjects.DifferentSectionPage;
import com.vendantu.pageobjects.ExamSummaryPage;
import com.vendantu.pageobjects.InstructionPage;

public class VedantuBasicTestFlow extends UiBaseSetup {

	SoftAssert softAssert = new SoftAssert();

	@Test()
	public void basicFlowVedantu() {
		testSteps.log(Status.INFO, "Read the Data CSV file for Assertions");
		Map<String, String> hm = CommonUtils.readCsvData();
		testSteps.log(Status.INFO, "Testing the Vedantu Test flow for kishore");
		InstructionPage homePageObj = new InstructionPage(driver);
		String instructionPopUp = homePageObj.getinstructionPopUp();
		testSteps.log(Status.INFO, "Verify The instruction Page for the Test");
		softAssert.assertEquals(instructionPopUp, hm.get("InstructionsPopup"),
				"Instrcution PopUp msg do not match expected");
		String startTest = homePageObj.getStartTest();
		testSteps.log(Status.INFO, "Verify The Start Test for the Test");
		softAssert.assertEquals(startTest, hm.get("StartTestMsg"), "Start Test text do not match expected");
		homePageObj.clickOnStartTest();

		testSteps.log(Status.INFO, "Verify Different sections and leaving early Popup Msges");
		DifferentSectionPage verifyDiffSections = new DifferentSectionPage(driver);
		List<String> listOfSection = verifyDiffSections.verifyDifferentSections();
		String differentSection = hm.get("DifferentSections");
		String diffSection[] = differentSection.split(",");
		for (int i = 0; i < listOfSection.size() - 1; i++) {

			softAssert.assertEquals(listOfSection.get(i + 1), diffSection[i],
					"Different section is not verified as expected");
		}
		testSteps.log(Status.INFO, "Verify the End Test Msg");
		softAssert.assertEquals(verifyDiffSections.verifyEndTest(), hm.get("EndTestMsg"),
				"End Test does not match with the expected");
		testSteps.log(Status.INFO, "Verify the Early leaving Pop up msg");
		softAssert.assertEquals(verifyDiffSections.verifyEarlyLeavingPopUp(hm.get("EarlyLeavingPopup")),
				hm.get("EarlyLeavingPopup"), "Early Leaving Popup does not match with the expected");
		verifyDiffSections.verifySubmitAndLeave();

		testSteps.log(Status.INFO, "Verify Exam Summary Page");
		ExamSummaryPage examSummaryPage = new ExamSummaryPage(driver);
		softAssert.assertEquals(examSummaryPage.verifyExamSummary(), hm.get("ExamSummary"),
				"Exam Summary does not match with the expected");
		examSummaryPage.verifyConfiramtionPopup();
		testSteps.log(Status.INFO, "Verify the Congratulation Msg");
		softAssert.assertEquals(examSummaryPage.verifyCongratulationMsg(), hm.get("CongratulationMsg"),
				"Exam Summary does not match with the expected");
		testSteps.log(Status.INFO, "Click on Okay button");
		examSummaryPage.clickOnOkay();

	}
}
