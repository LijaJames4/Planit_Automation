package com.planitautomation.contact.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;

import com.planitautomation.common.pages.Page;
import com.planitautomation.contact.common.Constant_Contact;

public class Page_Contact extends Page {
	
		public Page_Contact(WebDriver driver) {
			super(driver);
			// TODO Auto-generated constructor stub
		}
		private Page_Contact pageContact;

		  @BeforeMethod
		  public void initPageObjects() {
			  pageContact = PageFactory.initElements(driver, Page_Contact.class);
		  }
		 public WebElement get_id_Contact_Tab() {
				WebElement id = driver.findElement(By.id(Constant_Contact.CONTACT_TAB));
				return id;
			}
		public WebElement get_xpt_Submit() {
			WebElement xpath = driver.findElement(By.xpath("//a[.='"+Constant_Contact.SUBMIT+"']"));
			return xpath;
			
		}
		public WebElement get_id_ForeNameErrorMessage() {
			WebElement id  = driver.findElement(By.id(Constant_Contact.FORENAME_ERR));
			return id;
			
		}
		public WebElement get_id_EmailErrorMessage() {
			WebElement id  = driver.findElement(By.id(Constant_Contact.EMAIL_ERR));
			return id;
			
		}
		public WebElement get_id_MessageBoxErrorMessage() {
			WebElement id  = driver.findElement(By.id(Constant_Contact.MESSAGE_ERR));
			return id;
			
		}
		public WebElement get_id_ForeName() {
			WebElement id  = driver.findElement(By.id(Constant_Contact.FORENAME));
			return id;
			
		}
		public WebElement get_id_Email() {
			WebElement id  = driver.findElement(By.id(Constant_Contact.EMAIL));
			return id;
			
		}
		public WebElement get_id_MessageBox() {
			WebElement id  = driver.findElement(By.id(Constant_Contact.MESSAGE));
			return id;
			
		}
		
		public WebElement get_xpt_Thanks() {
			WebElement xpath  = driver.findElement(By.xpath("//*[contains(text(),'"+Constant_Contact.THANKS+"')]"));
			return xpath;
			
		}
}
