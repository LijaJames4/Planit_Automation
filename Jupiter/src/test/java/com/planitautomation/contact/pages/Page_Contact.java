package com.planitautomation.contact.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;

import com.planitautomation.common.pages.Page;

public class Page_Contact extends Page {
	
		public Page_Contact(WebDriver driver) {
			super(driver);
			// TODO Auto-generated constructor stub
		}
		private Page_Contact contactPage;

		  @BeforeMethod
		  public void initPageObjects() {
			  contactPage = PageFactory.initElements(driver, Page_Contact.class);
		  }
		public WebElement get_xpt_Submit() {
			WebElement xpath = driver.findElement(By.xpath("//a[.='Submit']"));
			return xpath;
			
		}
		public String get_id_ForeNameErrorMessage(String id) {
			return id;
			
		}
		public String get_id_EmailErrorMessage(String id) {
			return id;
			
		}
		public String get_xpt_MessageBoxErrorMessage(String id) {
			return id;
			
		}
		public String get_id_ForeName(String id) {
			return id;
			
		}
		public String get_id_Email(String id) {
			return id;
			
		}
		public String get_xpt_MessageBox(String id) {
			return id;
			
		}
		
		public String get_xpt_Thanks(String xpath) {
			return xpath;
			
		}
}
