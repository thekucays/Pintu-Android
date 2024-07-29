package com.luki.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.luki.common.Common;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class PageRegister {
	private static final String inputNameID = "com.loginmodule.learning:id/textInputEditTextName";
	
	public static WebElement inputName(AndroidDriver driver) {
		return driver.findElement(By.id(inputNameID));
	}
	
	public static WebElement inputEmail(AndroidDriver driver) {
		return driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextEmail"));
	}
	
	public static WebElement inputPassword(AndroidDriver driver) {
		return driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextPassword"));
	}
	
	public static WebElement inputConfirmPassword(AndroidDriver driver) {
		return driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextConfirmPassword"));
	}
	
	public static WebElement buttonRegister(AndroidDriver driver) {
		return driver.findElement(By.id("com.loginmodule.learning:id/appCompatButtonRegister"));
	}
	
	public static WebElement textLogin(AndroidDriver driver) {
		return driver.findElement(By.id("com.loginmodule.learning:id/appCompatTextViewLoginLink"));
	}
	
	public static boolean waitUntilInputNameExists(AndroidDriver driver) {
		boolean result = false;
		try {
			result = Common.waitUntilElementShown(inputNameID, "ID", 10, driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
