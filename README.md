# Android Tests
Luki Ramadon - https://github.com/thekucays/flptst-appium


## IDE / Plugins Used
- Eclipse 4.32.0
- OpenJDK Runtime Environment Zulu17.46+19-CA (build 17.0.9+8-LTS)
- appium java client: 9.2.3
- jupiter: 5.11.0-M2
- appium: 2.10.3

## Prerequisites
- Install Android SDK (and make sure `ANDROID_HOME` and `JAVA_HOME` path set correctly)
- Use appium 2.x instead of 1.x
- `appium-installer` package from npm installed
	- then install this appium driver: `uiautomator2`
- Make sure emulator / real devices detected by ADB by issuing this command
	- `adb devices`

## Structure
- `com.luki`: Test Cases
- `com.luki.common`: Common methods that used across Test Cases
- `com.luki.pom`: Page Object Models for each page tested
- `com.luki.util`: Utilities used for the tests


## Inspecting the app using Appium Inspector
Use this capability set (also available in `inspectorCapabilities.json` file, make sure to adjust `appium:app` value first)
```
{
  "appium:appium;deviceName": "emulator-5554",
  "appium:automationName": "UiAutomator2",
  "appium:app": "/Users/xxx/eclipse-workspace/projectName/apk/apptest.apk",
  "platformName": "android",
  "appium:appWaitActivity": "com.swaglabsmobileapp.MainActivity"
}
```

## Running The Project 
- Open this project folder on Eclipse 
- Open up `AppTest.java`
	- on `BeforeEach` hook, adjust emulator / real device name you want to use
	- you can obtain it by issuing `adb devices` command
- Run the test using this command
	- cd to root folder of the project, then `mvn test`
- Report generated in `target/surefire-reports`