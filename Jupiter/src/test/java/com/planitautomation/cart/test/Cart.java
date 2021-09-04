package com.planitautomation.cart.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.planitautomation.cart.common.Helper_Cart;
import com.planitautomation.cart.pages.Page_Cart;
import com.planitautomation.common.Planit_Base;
import com.planitautomation.contact.pages.Page_Contact;

public class Cart extends Planit_Base {
	private Page_Cart cartpage;

	@BeforeMethod
	public void initPageObjects() {
		cartpage = PageFactory.initElements(driver, Page_Cart.class);
	}

	@Test(description = "Verify the price of each product, each product's sub total=product price*quantity and total = sum(sub totals)", priority = 4, groups = {
			"smoke", "regression" }, invocationCount = 1)
	public void Validate_Total_In_Cart_Page() {
		try {

			driver.findElement(By.xpath("//*[@id=\"nav-shop\"]/a")).click();
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"product-4\"]")));

			driver.findElement(By.xpath("//*[@id=\"product-2\"]/descendant::a")).click();
			driver.findElement(By.xpath("//*[@id=\"product-2\"]/descendant::a")).click();
			driver.findElement(By.xpath("//*[@id=\"product-4\"]/descendant::a")).click();
			driver.findElement(By.xpath("//*[@id=\"product-4\"]/descendant::a")).click();
			driver.findElement(By.xpath("//*[@id=\"product-4\"]/descendant::a")).click();
			driver.findElement(By.xpath("//*[@id=\"product-4\"]/descendant::a")).click();
			driver.findElement(By.xpath("//*[@id=\"product-4\"]/descendant::a")).click();

			driver.findElement(By.xpath("//*[@id=\"product-7\"]/descendant::a")).click();
			driver.findElement(By.xpath("//*[@id=\"product-7\"]/descendant::a")).click();
			driver.findElement(By.xpath("//*[@id=\"product-7\"]/descendant::a")).click();

			driver.findElement(By.xpath("//*[@id=\"nav-cart\"]/a")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\"Check Out\"]")));
			Assert.assertTrue(!driver.findElement(By.xpath("//*[text()=\" Stuffed Frog\"]/following::td[1]")).getText()
					.isEmpty());
			Assert.assertTrue(!driver.findElement(By.xpath("//*[text()=\" Fluffy Bunny\"]/following::td[1]")).getText()
					.isEmpty());
			Assert.assertTrue(!driver.findElement(By.xpath("//*[text()=\" Valentine Bear\"]/following::td[1]"))
					.getText().isEmpty());

			double price_Stuffed_Frog = Helper_Cart.string_To_Float_Price_Converter(
					driver.findElement(By.xpath("//*[text()=\" Stuffed Frog\"]/following::td[1]")).getText());
			double price_Fluffy_Bunny = Helper_Cart.string_To_Float_Price_Converter(
					driver.findElement(By.xpath("//*[text()=\" Fluffy Bunny\"]/following::td[1]")).getText());
			double price_Valentine_Bear = Helper_Cart.string_To_Float_Price_Converter(
					driver.findElement(By.xpath("//*[text()=\" Valentine Bear\"]/following::td[1]")).getText());

			System.out.println("Price of Stuffed Frog : " + price_Stuffed_Frog);
			System.out.println("Price of Fluffy Bunny : " + price_Fluffy_Bunny);
			System.out.println("Price of Valentine Bear : " + price_Valentine_Bear);

			double subTotal_Stuffed_Frog = price_Stuffed_Frog * 2;
			double subTotal_Fluffy_Bunny = price_Fluffy_Bunny * 5;
			double subTotal_Valentine_Bear = price_Valentine_Bear * 3;

			double total = Helper_Cart.total_From_Text(
					driver.findElement(By.xpath("//*[text()=\"Check Out\"]/preceding::strong")).getText());
			Assert.assertEquals(total, subTotal_Stuffed_Frog + subTotal_Fluffy_Bunny + subTotal_Valentine_Bear,"Total = Sum(sub totals)");
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

}
