package com.luki;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.luki.common.Common;
import com.luki.pom.PageRegister;
import com.luki.pom.PageHome;
import com.luki.pom.PageLogin;
import com.luki.util.AppLauncher;
import io.appium.java_client.android.AndroidDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
	
	
	// Success Cases ////////////////////////////
	@Test
	public void successRegister() throws Exception {
		// registration data 
		final String registerName = "Luki";
		final String registerEmail = "luki@gmail.com";
		final String registerPwd = "qwerty1234";
		
		Common.registerNewUser(registerName, registerEmail, registerPwd, registerPwd, driver);
		
		PageRegister.textLogin(driver).click();
	}
	
	
	@Test
	public void successLogin() throws Exception {
		// registration data 
		final String registerName = "Luki";
		final String registerEmail = "luki@gmail.com";
		final String registerPwd = "qwerty1234";
		
		assertTrue(Common.registerNewUser(registerName, registerEmail, registerPwd, registerPwd, driver), "registration process should be success");
		PageRegister.textLogin(driver).click();
		
		PageLogin.inputEmail(driver).sendKeys(registerEmail);
		PageLogin.inputPassword(driver).sendKeys(registerPwd);
		PageLogin.buttonLogin(driver).click();
		

		// verify login email text exists
		assertTrue(PageHome.isLoginEmailExists(driver), "Login email field should be exists");
		
		// verify login email text value correct
		final String shownEmail = PageHome.textLoginEmail(driver).getText();
		assertEquals(registerEmail, shownEmail, "Login email: " + registerEmail + ", should be shown correctly");
	}
	
	// End of Success Cases ////////////////////////////
	
	
	// Negative Cases - Login ////////////////////////////
	
	@Test
	public void failedLoginEmptyEmail() throws Exception {
		final String loginPwd = "qwerty1234";
		final String errMsg = "Enter Valid Email";
		
		PageLogin.inputPassword(driver).sendKeys(loginPwd);
		PageLogin.buttonLogin(driver).click();
		
		final boolean validationResult = Common.waitUntilElementShown(errMsg, "xpathText", 5, driver);
		assertTrue(validationResult, "validation: " + errMsg + ", should be visible");
		
	}
	
	@Test
	public void failedLoginEmptyPassword() throws Exception {
		final String loginEmail = "luki@gmail.com";
		final String errMsg = "Enter Valid Email";
//		final String errMsg = "Enter Valid Password";
		
		PageLogin.inputEmail(driver).sendKeys(loginEmail);
		PageLogin.buttonLogin(driver).click();
		
		final boolean validationResult = Common.waitUntilElementShown(errMsg, "xpathText", 5, driver);
		assertTrue(validationResult, "validation: " + errMsg + ", should be visible");
		
	}
	
	@Test
	public void failedLoginInvalidEmail() throws Exception {
		final String loginEmail = "lukiaaa";
		final String loginPwd = "qwerty1234";
		final String errMsg = "Enter Valid Email";
		
		PageLogin.inputEmail(driver).sendKeys(loginEmail);
		PageLogin.inputPassword(driver).sendKeys(loginPwd);
		PageLogin.buttonLogin(driver).click();
		
		final boolean validationResult = Common.waitUntilElementShown(errMsg, "xpathText", 5, driver);
		assertTrue(validationResult, "validation: " + errMsg + ", should be visible");
		
	}
	
	@Test
	public void failedLoginInvalidCredential() throws Exception {
		final String loginEmail = "dummy@foo.com";
		final String loginPwd = "qwerty1234";
		final String errMsg = "Wrong Email or Password";
		
		PageLogin.inputEmail(driver).sendKeys(loginEmail);
		PageLogin.inputPassword(driver).sendKeys(loginPwd);
		PageLogin.buttonLogin(driver).click();
		
		final boolean validationResult = Common.waitUntilElementShown(errMsg, "xpathText", 5, driver);
		assertTrue(validationResult, "validation: " + errMsg + ", should be visible");
		
	}
	
	// End of Negative Cases - Login ////////////////////////////
}
