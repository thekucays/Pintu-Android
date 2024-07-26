package com.luki.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class PageRegister {
	public static WebElement inputName(AndroidDriver driver) {
		return driver.findElement(By.id("com.loginmodule.learning:id/textInputEditTextName"));
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

}
