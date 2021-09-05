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

import com.planitautomation.cart.common.Constant_Cart;
import com.planitautomation.cart.common.Helper_Cart;
import com.planitautomation.cart.pages.Page_Cart;
import com.planitautomation.common.Planit_Base;
import com.planitautomation.contact.test.Contact;
import com.planitautomation.shop.common.Constant_Shop;
import com.planitautomation.shop.pages.Page_Shop;

public class Cart extends Planit_Base {
	private Page_Cart pageCart;
	private Page_Shop pageShop;

	@BeforeMethod(alwaysRun = true)
	public void initPageObjects() {
		pageCart = PageFactory.initElements(driver, Page_Cart.class);
		pageShop = PageFactory.initElements(driver, Page_Shop.class);
	}

	//Test Case 3
	@Test(description = "Verify if shopped items are displayed correctly in Cart", priority = 3, groups = { "smoke",
			"regression" }, invocationCount = 1, enabled = true)
	public void verify_Items_In_Cart() {
		try {

			navigate_To_Shop();
			wait_For_Element(Constant_Shop.PRODUCT);

			// create shopping list
			HashMap<String, Integer> shoppingList = new HashMap<String, Integer>();
			shoppingList.put("Fluffy Bunny", 2);
			shoppingList.put("Valentine Bear", 1);

			// iterate over each item in shopping list and add the required quantity
			add_Items_To_Cart(shoppingList);
			navigate_To_Cart();
			wait_For_Element(Constant_Cart.CHECKOUT);

			for (Entry<String, Integer> mapElement : shoppingList.entrySet()) {
				String product = mapElement.getKey();
				Assert.assertTrue(pageCart.get_xpt_Product(product).isDisplayed());
			}

		} catch (Exception e) {
			Assert.fail("Excpetion thrown in : " + Contact.class + " \nException is : " + e.getMessage());
			e.printStackTrace();
		}
	}

	//Test case 4
	@Test(description = "Verify the price of each product, each product's sub total=product price*quantity and total = sum(sub totals)", priority = 4, groups = {
			"regression" }, invocationCount = 1, enabled = true)
	public void Validate_Total_In_Cart_Page() {
		try {

			navigate_To_Shop();
			wait_For_Element(Constant_Shop.PRODUCT);

			// create shopping list
			HashMap<String, Integer> shoppingList = new HashMap<String, Integer>();
			shoppingList.put("Stuffed Frog", 2);
			shoppingList.put("Fluffy Bunny", 5);
			shoppingList.put("Valentine Bear", 3);

			// iterate over each item in shopping list and add the required quantity
			add_Items_To_Cart(shoppingList);
			navigate_To_Cart();
			wait_For_Element(Constant_Cart.CHECKOUT);

			double total = Helper_Cart.total_From_Text(pageCart.get_xpt_Total().getText());
			Assert.assertEquals(total, sub_Total_Cart_Items(shoppingList));

		} catch (Exception e) {
			Assert.fail("Excpetion thrown in : "
					+ Contact.class /*
									 * + " At " + e.getCause().getStackTrace()[0].getLineNumber()
									 */ + " \nException is : " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void navigate_To_Shop() {
		driver.findElement(By.xpath("//*[@id=\"" + Constant_Shop.SHOP_TAB + "\"]/a")).click();
	}

	public void navigate_To_Cart() {
		driver.findElement(By.xpath("//*[@id=\"" + Constant_Cart.CART_TAB + "\"]/a")).click();
	}

	public void wait_For_Element(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public double price_of_Item_In_Cart(String productName) {
		return Helper_Cart.string_To_Float_Price_Converter(pageCart.get_xpt_ItemPrice(productName).getText());
	}

	// Argument is shopping list
	public void add_Items_To_Cart(HashMap<String, Integer> shoppingList) {
		for (Entry<String, Integer> mapElement : shoppingList.entrySet()) {
			String product = mapElement.getKey();
			int quantity = (int) mapElement.getValue();
			// add the quantity required for each item
			IntStream.range(0, quantity).forEach(i -> pageShop.get_xpt_Buy(product).click());
		}
	}

	public double sub_Total_Cart_Items(HashMap<String, Integer> shoppingList) {
		double Sum = 0;
		for (Entry<String, Integer> mapElement : shoppingList.entrySet()) {
			String product = mapElement.getKey();
			int quantity = (int) mapElement.getValue();
			// add the quantity required for each item
			Sum += price_of_Item_In_Cart(product) * quantity;
		}
		return Sum;
	}

}
