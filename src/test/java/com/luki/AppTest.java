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
	}
	
	@Test
	public void successRegister2() throws Exception {
		PageLogin.textRegister(driver).click();
	}
}
