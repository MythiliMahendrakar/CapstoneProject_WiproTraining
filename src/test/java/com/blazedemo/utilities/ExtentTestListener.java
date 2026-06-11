package com.blazedemo.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import com.blazedemo.base.BaseTest;

public class ExtentTestListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test.set(extent.createTest(result.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        WebDriver driver = BaseTest.getDriver();
        String path = ScreenshotUtil.captureScreenshot(driver, result.getName() + "_PASS");
        test.get().pass("Test Passed").addScreenCaptureFromPath(path);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseTest.getDriver();
        String path = ScreenshotUtil.captureScreenshot(driver, result.getName() + "_FAIL");
        test.get().fail(result.getThrowable()).addScreenCaptureFromPath(path);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // writes the report to file
    }
}
