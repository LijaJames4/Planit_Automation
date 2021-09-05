package com.planitautomation.cart.test;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.IntStream;

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
import com.planitautomation.contact.test.Contact;
import com.planitautomation.shop.pages.Page_Shop;

public class Cart extends Planit_Base {
	private Page_Cart pageCart;
	private Page_Shop pageShop;

	@BeforeMethod(alwaysRun=true)
	public void initPageObjects() {
		pageCart = PageFactory.initElements(driver, Page_Cart.class);
		pageShop = PageFactory.initElements(driver, Page_Shop.class);
	}

	@Test(description = "Verify if shopped items are displayed correctly in Cart", priority = 3, groups = { "smoke",
			"regression" }, invocationCount = 1, enabled=true)
	public void verify_Items_In_Cart() {
		try {

			driver.findElement(By.xpath("//*[@id=\"nav-shop\"]/a")).click();
			wait_For_Element("//*[@id=\"product-4\"]");

			pageShop.get_xpt_Product(4).click();
			pageShop.get_xpt_Product(5).click();
			pageShop.get_xpt_Product(6).click();

			navigate_To_Cart();
			wait_For_Element("//*[text()=\"Check Out\"]");

			Assert.assertTrue(pageCart.get_xpt_Product("Fluffy Bunny").isDisplayed());
			Assert.assertTrue(pageCart.get_xpt_Product("Funny Cow").isDisplayed());

		} catch (Exception e) {
			Assert.fail("Excpetion thrown in : " + Contact.class + " \nException is : " + e.getMessage());

			// e.printStackTrace();
		}
	}

	@Test(description = "Verify the price of each product, each product's sub total=product price*quantity and total = sum(sub totals)", priority = 4, groups = {
			"regression" }, invocationCount = 1, enabled = true)
	public void Validate_Total_In_Cart_Page() {
		try {

			driver.findElement(By.xpath("//*[@id=\"nav-shop\"]/a")).click();
			wait_For_Element("//*[@id=\"product-4\"]");
			
			//create shopping list
			HashMap<Integer, Integer> shoppingList = new HashMap<Integer, Integer>();
			shoppingList.put(2, 2);
			shoppingList.put(4, 5);
			shoppingList.put(7, 3);
			
			//iterate over each item in shopping list
			for(Entry mapElement : shoppingList.entrySet()) {
				int productIndex = (int) mapElement.getKey();
				int quantity = (int) mapElement.getValue();
				//add the quantity required for each item
				IntStream.range(0, quantity).forEach(i -> pageCart.get_xpt_Product(productIndex).click());
			}
			
			navigate_To_Cart();
			wait_For_Element("//*[text()=\"Check Out\"]");
			
			Assert.assertTrue(!pageCart.get_xpt_ItemPrice("Stuffed Frog").getText().isEmpty());
			Assert.assertTrue(!pageCart.get_xpt_ItemPrice("Fluffy Bunny").getText().isEmpty());
			Assert.assertTrue(!pageCart.get_xpt_ItemPrice("Valentine Bear").getText().isEmpty());

			double subTotal_Stuffed_Frog = price_of_Item_In_Cart("Stuffed Frog") * 2;
			double subTotal_Fluffy_Bunny = price_of_Item_In_Cart("Fluffy Bunny") * 5;
			double subTotal_Valentine_Bear = price_of_Item_In_Cart("Valentine Bear") * 3;

			double total = Helper_Cart.total_From_Text(
					pageCart.get_xpt_Total().getText());
			Assert.assertEquals(total, subTotal_Stuffed_Frog + subTotal_Fluffy_Bunny + subTotal_Valentine_Bear,
					"Total = Sum(sub totals)");

		} catch (Exception e) {
			Assert.fail("Excpetion thrown in : " + Contact.class /*+ " At "
					+ e.getCause().getStackTrace()[0].getLineNumber() */+ " \nException is : " + e.getMessage());

		//	e.printStackTrace();
		}
	}

	public void navigate_To_Shop() {
		driver.findElement(By.xpath("//*[@id=\"nav-shop\"]/a")).click();
	}

	public void navigate_To_Cart() {
		driver.findElement(By.xpath("//*[@id=\"nav-cart\"]/a")).click();
	}

	public void wait_For_Element(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public double price_of_Item_In_Cart(String productName) {
		return Helper_Cart.string_To_Float_Price_Converter(
				pageCart.get_xpt_ItemPrice(productName).getText());
	}

}
