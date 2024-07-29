package com.luki.pom;

import org.openqa.selenium.WebElement;

import com.luki.common.Common;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class PageLogin {
	
	private static final String textRegisterID = "com.loginmodule.learning:id/textViewLinkRegister";
	
	public static WebElement textRegister(AndroidDriver driver) {
		return driver.findElement(AppiumBy.id(textRegisterID));
	}
	
	public static WebElement inputEmail(AndroidDriver driver) {
		return driver.findElement(AppiumBy.id("com.loginmodule.learning:id/textInputEditTextEmail"));
	}
	
	public static WebElement inputPassword(AndroidDriver driver) {
		return driver.findElement(AppiumBy.id("com.loginmodule.learning:id/textInputEditTextPassword"));
	}
	
	public static WebElement buttonLogin(AndroidDriver driver) {
		return driver.findElement(AppiumBy.id("com.loginmodule.learning:id/appCompatButtonLogin"));
	}
	
	public static boolean waitUntilTextRegisterExists(AndroidDriver driver) {
		boolean result = false;
		try {
			result = Common.waitUntilElementShown(textRegisterID, "appiumID", 10, driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
