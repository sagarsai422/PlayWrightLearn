package Base;

import Utils.ExtentManager;
import Utils.ScreenShotUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseClass {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setUp(Method method) {
        System.out.println("Setting up the test environment...");

        extent = ExtentManager.getInstance();
        test = extent.createTest(method.getName());

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        page = browser.newPage();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        System.out.println("Tearing down the test environment...");

        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());

            //Adding ScreenShot in Reports
            String screenShotPath = ScreenShotUtils.takeScreenShot(page, result.getName());
            test.addScreenCaptureFromPath(screenShotPath, "Screenshot on Failure");

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test skipped");
        }
        extent.flush();

        if (page != null) {
            page.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
