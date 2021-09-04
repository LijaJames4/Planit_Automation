package com.planitautomation.cart.common;

import org.openqa.selenium.WebDriver;

import com.planitautomation.common.pages.Page;

public class Helper_Cart extends Page {
	
	public Helper_Cart(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static double string_To_Float_Price_Converter(String textPrice) {
		StringBuilder sb = new StringBuilder(textPrice);
		sb.deleteCharAt(0);
		double numPrice = Double.parseDouble(sb.toString());
		return numPrice;
	} 
	public static double total_From_Text(String textTotal) {
		textTotal = textTotal.replaceAll("Total: ", "");
		double numTotal = Double.parseDouble(textTotal);
		return numTotal;
		
	}

}
