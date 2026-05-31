package Tests;

import Base.BaseClass;
import Pages.HomePage;
import Pages.LoginPage;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class LoginTest2 extends BaseClass {
    @Test
    public void logintest1() {
        LoginPage loginPage = new LoginPage(page);
        HomePage homePage = new HomePage(page);

        test.info("Navigating to the login page");
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        test.info("Entering username");
        loginPage.addusername("Admin");
        test.info("Entering password");
        loginPage.addpassword("admin123");
        test.info("Clicking on login button");
        loginPage.clickloginbutton();
        test.info("Clicking on Time link");
        homePage.clicktimelink();
        test.info("All steps completed");
    }
    @Test
    public void logintest2() {
        test.skip("Skipping this test");
        throw new SkipException("Skipping this test");
    }
    @Test
    public void logintest3() {
        LoginPage loginPage = new LoginPage(page);
        HomePage homePage = new HomePage(page);

        test.info("Navigating to the login page");
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        test.info("Entering username");
        loginPage.addusername("Admin");
        test.info("Entering password");
        loginPage.addpassword("admin123");
        test.info("Clicking on login button");
        loginPage.clickloginbutton();
        test.info("Clicking on Time link");
    }

}
