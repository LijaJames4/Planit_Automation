package com.planitautomation.contact.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.planitautomation.common.Planit_Base;
import com.planitautomation.contact.pages.Page_Contact;

public class Contact extends Planit_Base{
	
	 private Page_Contact contactpage;

	  @BeforeMethod
	  public void initPageObjects() {
		  contactpage = PageFactory.initElements(driver, Page_Contact.class);
	  }
	@Test(description = "Verify Contact Page", priority = 1, groups = {"smoke","regression"})
	public void verify_Contact_Error_Validation() {
		try {
			driver.findElement(By.xpath("//*[@id=\"nav-contact\"]/a")).click();
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.='Submit']")));
			driver.findElement(By.xpath("//a[.='Submit']")).click();
			
			contactpage.get_xpt_Submit().click();
			
			Assert.assertTrue(driver.findElement(By.id("forename-err")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.id("email-err")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.id("message-err")).isDisplayed());
			
			driver.findElement(By.id("forename")).sendKeys("John");
			driver.findElement(By.id("email")).sendKeys("john.example@planit.net.au");
			driver.findElement(By.id("message")).sendKeys("Good Quality Product. Children loved it!!");
			driver.findElement(By.xpath("//a[.='Submit']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Thanks')]")));
		
			Assert.assertTrue(driver.findElements(By.id("forename-err")).isEmpty());
			Assert.assertTrue(driver.findElements(By.id("email-err")).isEmpty());
			Assert.assertTrue(driver.findElements(By.id("message-err")).isEmpty());
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test(description = "Verify Contact Page", priority = 2, groups = {"smoke","regression"},invocationCount = 5)
	public void verify_Contact_Success_Validation() {
		try {
				
			driver.findElement(By.xpath("//*[@id=\"nav-contact\"]/a")).click();
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.='Submit']")));
			
			driver.findElement(By.id("forename")).sendKeys("John");
			driver.findElement(By.id("email")).sendKeys("john.example@planit.net.au");
			driver.findElement(By.id("message")).sendKeys("Good Quality Product. Children loved it!!");
			driver.findElement(By.xpath("//a[.='Submit']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Thanks')]")));
		
			Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Thanks')]")).isDisplayed());
					
		
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}

}
