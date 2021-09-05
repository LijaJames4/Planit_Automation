package com.planitautomation.shop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.planitautomation.common.Planit_Base;
import com.planitautomation.common.pages.Page;
import com.planitautomation.contact.common.Constant_Contact;
import com.planitautomation.shop.common.Constant_Shop;

public class Page_Shop extends Page{

	public Page_Shop(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	 public WebElement get_id_Shop_Tab() {
			WebElement id = driver.findElement(By.id(Constant_Shop.SHOP_TAB));
			return id;
		}
	 public WebElement get_xpt_Product(int index) {
			WebElement xpt = 	driver.findElement(By.xpath("//*[@id=\"product-"+ index +"\"]/descendant::a"));
			return xpt;
		}
}
