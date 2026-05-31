package Tests;

import Base.BaseClass;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class FirstTest extends BaseClass {

    @Test
    public void verifyTitle() {
        page.navigate("https://google.com/");
        if (page.isVisible("button:has-text('Accept all')")) {
            page.click("button:has-text('Accept all')");
        }
        System.out.println("Page title: " + page.title());
        System.out.println("Page URL: " + page.url());
        System.out.println("First Test");
        Reporter.log("First Test executed successfully!", true);
    }

    @Test
    public void verifyURL() {
        page.navigate("https://google.com/");
        if (page.isVisible("button:has-text('Accept all')")) {
            page.click("button:has-text('Accept all')");
        }
        System.out.println("Page title: " + page.title());
        System.out.println("Page URL: " + page.url());
        System.out.println("Second Test");
        Reporter.log("Second Test executed successfully!", true);
    }

//    public static void main(String[] args) {
//        try (Playwright playwright = Playwright.create()) {
//            System.out.println("Playwright initialized successfully!");
//            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//            System.out.println("Browser launched successfully!");
//            Page page = browser.newPage();
//            System.out.println("New page created successfully!");
//            page.navigate("https://playwright.dev/");
//            System.out.println("Page navigated successfully!");
//            System.out.println("Page title: " + page.title());
//            browser.close();
//            System.out.println("Browser closed successfully!");
//        }
//    }
}
