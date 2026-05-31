package Utils;

import com.microsoft.playwright.Page;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotUtils {

    private static final String REPORTS_DIR = "Reports";
    private static final String SCREENSHOTS_DIR = "screenshots";

    public static String takeScreenShot(Page page, String testName) {

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".jpg";
        Path screenshotDir = Paths.get(REPORTS_DIR, SCREENSHOTS_DIR);
        Path screenshotPath = screenshotDir.resolve(fileName);

        try {
            Files.createDirectories(screenshotDir);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create screenshot directory: " + screenshotDir, e);
        }

        page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath).setFullPage(false));
        return SCREENSHOTS_DIR + "/" + fileName;
    }

}
