package com.luki.common;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput.Kind;

import com.luki.pom.PageLogin;
import com.luki.pom.PageRegister;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class Common {
	public static boolean waitUntilElementShown(String text, String locatorStrategy, int loopTimeout, AndroidDriver driver) throws Exception {
		boolean result = false;
		for(int i=0; i<loopTimeout; i++) {
			List<WebElement> elements = null;
			
			if(locatorStrategy.equalsIgnoreCase("xpathText")) {
				elements = driver.findElements(By.xpath("//*[@text='" + text + "']"));
			} else if(locatorStrategy.equalsIgnoreCase("xpath")) {
				elements = driver.findElements(By.xpath(text));
			} else if(locatorStrategy.equalsIgnoreCase("appiumID")) {
				elements = driver.findElements(AppiumBy.id(text));
			} else if(locatorStrategy.equalsIgnoreCase("ID")) {
				elements = driver.findElements(By.id(text));
			}
			
			if(elements.size() != 0) {
				result = true;
				break;
			}
			Thread.sleep(1000);
		}
		
		return result;
	}
	
	public static boolean waitUntilElementDissapear(String text, String locatorStrategy, int loopTimeout, AndroidDriver driver) throws Exception {
		boolean result = false;
		for(int i=0; i<loopTimeout; i++) {
			List<WebElement> elements = null;
			
			if(locatorStrategy.equalsIgnoreCase("xpathText")) {
				elements = driver.findElements(By.xpath("//*[@text='" + text + "']"));
			}
			
			if(elements.size() == 0) {
				result = true;
				break;
			}
			Thread.sleep(1000);
		}
		return result;
	}
	
	public static boolean checkElementExistsByAccessibilityID(String accID, AndroidDriver driver) throws Exception {
		List<WebElement> elements = driver.findElements(AppiumBy.accessibilityId(accID));
		
		return elements.isEmpty();
	}
	
	public static void scrollVertical(double deltaY, AndroidDriver driver) {
		Dimension size = driver.manage().window().getSize();
		int startX = size.getWidth() / 2;
		int startY = size.getHeight() / 2;
		int endX = startX;
		int endY = (int) (size.getHeight() * deltaY);
		
		PointerInput finger1 = new PointerInput(Kind.TOUCH, "finger1");
		Sequence sequence = new Sequence(finger1, 1)
				.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger1, Duration.ofMillis(200)))
				.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Collections.singletonList(sequence));
	}
	
	
	// overloaded method, to accept errMsg parameter when used for registration negative case
	public static boolean registerNewUser(String registerName, String registerEmail, String registerPwd, String registerConfirmPwd, boolean isSuccessExpected, AndroidDriver driver) throws Exception {
		boolean processCompleted = registerNewUser(registerName, registerEmail, registerPwd, registerConfirmPwd, isSuccessExpected, "dummy err Message", driver);
		return processCompleted;
	}

	
	public static boolean registerNewUser(String registerName, String registerEmail, String registerPwd, String registerConfirmPwd, boolean isSuccessExpected, String errMsg, AndroidDriver driver) throws Exception {
		boolean isProcessCompleted = true;
		
		try {
			// fill form
			PageLogin.waitUntilTextRegisterExists(driver);
			PageLogin.textRegister(driver).click();
			
			PageRegister.waitUntilInputNameExists(driver);
			PageRegister.inputName(driver).sendKeys(registerName);
			PageRegister.inputEmail(driver).sendKeys(registerEmail);
			PageRegister.inputPassword(driver).sendKeys(registerPwd);
			PageRegister.inputConfirmPassword(driver).sendKeys(registerConfirmPwd);
			PageRegister.buttonRegister(driver).click();
			
			String warningMessage = "";
			
			if(isSuccessExpected) {
				warningMessage = "Registration Successful";
				boolean isSuccess = Common.waitUntilElementShown(warningMessage, "xpathText", 10, driver);
				assertTrue(isSuccess, "Registration Success text should exist");
				
				boolean isMsgCleared = Common.waitUntilElementDissapear(warningMessage, "xpathText", 10, driver);
				assertTrue(isMsgCleared, "Message should be cleared");
			} else {
				// use errMsg variable here to validate registration error messages
				warningMessage = errMsg;
				boolean isRegistrationFailed = Common.waitUntilElementShown(warningMessage, "xpathText", 10, driver);
				assertTrue(isRegistrationFailed, "Registration should be failed with error message: " + warningMessage);
			}
		} catch(Exception e) {
			isProcessCompleted  = false;
		}
		
		return isProcessCompleted;
	}
}
