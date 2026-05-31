package Tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

public class Amazon {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            System.out.println("website opening");
            page.navigate("https://www.amazon.in/");
            page.waitForLoadState(LoadState.DOMCONTENTLOADED);
            page.locator("#twotabsearchtextbox, body").first().waitFor();

            System.out.println("Page title: " + page.title());
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("amazon.png")));

            browser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
