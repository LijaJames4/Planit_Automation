package com.planitautomation.shop.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.planitautomation.common.Planit_Base;

public class Shop extends Planit_Base{
	@Test(description = "Verify Contact Page", priority = 3, groups = {"smoke","regression"},invocationCount = 1)
	public void verify_Contact_Success_Validation() {
		try {
			
			driver.findElement(By.xpath("//*[@id=\"nav-shop\"]/a")).click();
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"product-4\"]")));
			
			driver.findElement(By.xpath("//*[@id=\"product-4\"]/descendant::a")).click();
			driver.findElement(By.xpath("//*[@id=\"product-4\"]/descendant::a")).click();
			driver.findElement(By.xpath("//*[@id=\"product-6\"]/descendant::a")).click();
			driver.findElement(By.xpath("//*[@id=\"nav-cart\"]/a")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\"Check Out\"]")));
			
			Assert.assertTrue(driver.findElement(By.xpath("//*[text()=\" Fluffy Bunny\"]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//*[text()=\" Funny Cow\"]")).isDisplayed());
					
			driver.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	

}