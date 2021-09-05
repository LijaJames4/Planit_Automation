package com.planitautomation.contact.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.planitautomation.common.Planit_Base;
import com.planitautomation.contact.common.Constant_Contact;
import com.planitautomation.contact.pages.Page_Contact;

public class Contact extends Planit_Base {

	private Page_Contact pageContact;

	@BeforeMethod(alwaysRun=true)
	public void initPageObjects() {
		pageContact = PageFactory.initElements(driver, Page_Contact.class);
	}
	//Test case 1
	@Test(description = "Verify Error messages in Contact Page", priority = 1, groups=
			"regression", enabled = true)
	public void validate_Contact_Error_Messages() {
		try {

			navigate_To_Contact();
			wait_For_Element(Constant_Contact.SUBMIT_XPT);
			pageContact.get_xpt_Submit().click();

			Assert.assertTrue(pageContact.get_id_ForeNameErrorMessage().isDisplayed());
			Assert.assertTrue(pageContact.get_id_EmailErrorMessage().isDisplayed());
			Assert.assertTrue(pageContact.get_id_MessageBoxErrorMessage().isDisplayed());

			pageContact.get_id_ForeName().sendKeys("John");
			pageContact.get_id_Email().sendKeys("john.example@planit.net.au");
			pageContact.get_id_MessageBox().sendKeys("Good Quality Product. Children loved it!!");

			pageContact.get_xpt_Submit().click();
			wait_For_Element(Constant_Contact.THANKS_XPT);

			Assert.assertTrue(driver.findElements(By.id(Constant_Contact.FORENAME_ERR)).isEmpty());
			Assert.assertTrue(driver.findElements(By.id(Constant_Contact.EMAIL_ERR)).isEmpty());
			Assert.assertTrue(driver.findElements(By.id(Constant_Contact.MESSAGE_ERR)).isEmpty());

		} catch (Exception e) {
			Assert.fail("Excpetion thrown in : " + Contact.class + " \nException is : " + e.getMessage());
			e.printStackTrace();
		}

	}
	//Test Case 2
	@Test(description = "Verify Feedback submission in Contact Page", priority = 2, groups = { "smoke",
			"regression" }, enabled = true, invocationCount = 5)
	public void verify_Contact_Success() {
		try {

			navigate_To_Contact();
			wait_For_Element(Constant_Contact.SUBMIT_XPT);
			pageContact.get_id_ForeName().sendKeys("John");
			pageContact.get_id_Email().sendKeys("john.example@planit.net.au");
			pageContact.get_id_MessageBox().sendKeys("Good Quality Product. Children loved it!!");

			pageContact.get_xpt_Submit().click();
			wait_For_Element(Constant_Contact.THANKS_XPT);

			Assert.assertTrue(pageContact.get_xpt_Thanks().isDisplayed());

		} catch (Exception e) {
			Assert.fail("Excpetion thrown in : " + Contact.class  + " \nException is : " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void navigate_To_Contact() {
		pageContact.get_id_Contact_Tab().click();
	}

	public void wait_For_Element(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

}
