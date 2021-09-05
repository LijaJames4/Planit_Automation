package com.planitautomation.cart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.planitautomation.common.pages.Page;
import com.planitautomation.shop.common.Constant_Shop;

public class Page_Cart extends Page{

	public Page_Cart(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	 public WebElement get_id_Cart_Tab() {
			WebElement id = driver.findElement(By.id(Constant_Shop.SHOP_TAB));
			return id;
		}
	 public WebElement get_xpt_Product(int index) {
			WebElement xpt = 	driver.findElement(By.xpath("//*[@id=\"product-"+ index +"\"]/descendant::a"));
			return xpt;
		}
	 public WebElement get_xpt_Product(String productName) {
			WebElement xpt = 	driver.findElement(By.xpath("//*[text()=\" "+productName+"\"]"));
			return xpt;
		}
	 public WebElement get_xpt_ItemPrice(String productName) {
			WebElement xpt = 	driver.findElement(By.xpath("//*[text()=\" "+productName+"\"]/following::td[1]"));
			return xpt;
		}
	 public WebElement get_xpt_Total() {
			WebElement xpt = 	driver.findElement(By.xpath("//*[text()=\"Check Out\"]/preceding::strong"));
			return xpt;
		}
}
