package com.luki.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.luki.common.Common;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class PageHome {
	
	private static final String loginNameXpath = "//android.widget.TextView[@resource-id=\"com.loginmodule.learning:id/textViewName\"]";
	
	public static WebElement textLoginEmail(AndroidDriver driver) {
		return driver.findElement(By.xpath(loginNameXpath));
	}
	
	public static boolean isLoginEmailExists(AndroidDriver driver) {
		boolean result = false;
		try {
			result = Common.waitUntilElementShown(loginNameXpath, "xpath", 10, driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
