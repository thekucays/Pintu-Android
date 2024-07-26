package com.luki;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.luki.common.Common;
import com.luki.pom.PageRegister;
import com.luki.pom.PageLogin;
import com.luki.util.AppLauncher;
import io.appium.java_client.android.AndroidDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {
	private AndroidDriver driver;
	
	@BeforeEach
	public void setUp() throws Exception{
		driver = AppLauncher.startApp("emulator-5554");
	}
	
	@AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
	
	@Test
	public void successRegister() throws Exception {
		PageLogin.textRegister(driver).click();
		
		// registration data 
		final String registerName = "Luki";
		final String registerEmail = "luki@gmail.com";
		final String registerPwd = "qwerty1234";
		
		// fill form
		// tambahin wait for element disini
		PageRegister.inputName(driver).sendKeys(registerName);
		PageRegister.inputEmail(driver).sendKeys(registerEmail);
		PageRegister.inputPassword(driver).sendKeys(registerPwd);
		PageRegister.inputConfirmPassword(driver).sendKeys(registerPwd);
		PageRegister.buttonRegister(driver).click();
		
		
		final String successMsg = "Registration Successful";
		boolean isSuccess = Common.checkElementExistsByText(successMsg, driver);
		assertTrue(isSuccess, "Registration Success text should exist");
		
		
		boolean isMsgCleared = Common.waitUntilTextDissapear(successMsg, 10, driver);
		assertTrue(isMsgCleared, "Message should be cleared");
		PageRegister.textLogin(driver).click();
	}
}
