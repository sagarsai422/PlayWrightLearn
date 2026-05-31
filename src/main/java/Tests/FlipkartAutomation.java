package Tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

public class FlipkartAutomation {
    private static final String PRODUCT_CARD_SELECTOR = "div[data-id], a[href*='/p/']";
    private static final String PRODUCT_NAME_KEYWORD = "iphone";

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            Page page = browser.newPage();
            page.navigate("https://www.flipkart.com/");
            page.waitForLoadState(LoadState.DOMCONTENTLOADED);

            closeLoginPopupIfVisible(page);
            searchProduct(page, "iphone 15");
            printFirstResult(page);

            browser.close();
        }
    }

    private static void closeLoginPopupIfVisible(Page page) {
        page.keyboard().press("Escape");

        Locator closeButton = page.locator("button:has-text('x'), button:has-text('X')");

        if (closeButton.count() > 0 && closeButton.first().isVisible()) {
            closeButton.first().click();
        }
    }

    private static void searchProduct(Page page, String productName) {
        Locator searchBox = page.locator("input[name='q'], input[title='Search for Products, Brands and More']");

        searchBox.first().fill(productName);
        searchBox.first().press("Enter");

        try {
            page.waitForURL("**/search?**");
        } catch (Exception ignored) {
            page.waitForLoadState();
        }
    }

    private static void printFirstResult(Page page) {
        try {
            page.waitForSelector(PRODUCT_CARD_SELECTOR);
        } catch (Exception exception) {
            throw new RuntimeException(
                    "No Flipkart product results were found. Current URL: " + page.url()
                            + ", title: " + page.title()
                            + ". Flipkart may have changed its layout or shown a captcha/block page.",
                    exception
            );
        }

        String firstProductName = firstVisibleText(
                page,
                "a[href*='/p/'] div:has-text('iPhone')",
                "div[data-id] div:has-text('iPhone')",
                "div[data-id] div.KzDlHZ",
                "div[data-id] a.IRpwTa",
                "div[data-id] a.s1Q9rs",
                "a[href*='/p/'][title]",
                "a[href*='/p/']"
        );

        String firstProductPrice = firstVisibleText(
                page,
                "div[data-id] div.Nx9bqj",
                "div[data-id] div._30jeq3",
                "a[href*='/p/'] div.Nx9bqj",
                "a[href*='/p/'] div._30jeq3"
        );

        System.out.println("Page title: " + page.title());
        System.out.println("First product: " + firstProductName);

        if (!firstProductPrice.isBlank()) {
            System.out.println("First product price: " + firstProductPrice);
        }
    }

    private static String firstVisibleText(Page page, String... selectors) {
        for (String selector : selectors) {
            Locator locator = page.locator(selector);

            for (int index = 0; index < locator.count(); index++) {
                Locator item = locator.nth(index);

                if (item.isVisible()) {
                    String text = item.innerText().trim();

                    if (!text.isBlank()) {
                        String productLine = firstProductLine(text);

                        if (!productLine.isBlank()) {
                            return productLine;
                        }
                    }

                    String title = item.getAttribute("title");

                    if (title != null && isProductText(title)) {
                        return title.trim();
                    }
                }
            }
        }

        return "";
    }

    private static String firstProductLine(String text) {
        for (String line : text.split("\\R")) {
            String trimmedLine = line.trim();

            if (isProductText(trimmedLine)) {
                return trimmedLine;
            }
        }

        return "";
    }

    private static boolean isProductText(String text) {
        String normalizedText = text.trim().toLowerCase();

        return !normalizedText.isBlank()
                && normalizedText.contains(PRODUCT_NAME_KEYWORD)
                && !normalizedText.equals("add to compare")
                && !normalizedText.startsWith("sponsored")
                && !normalizedText.startsWith("ad");
    }
}
