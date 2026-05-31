package Tests;

import Base.BaseClass;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {
    @Test
    public void test() {
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("Admin");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("admin123");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Time")).click();
        page.locator("form").getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("View")).click();
        page.locator("form").getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("View")).click();
//        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Performance"))).isVisible();
//        assertThat(page.getByLabel("Sidepanel").getByRole(AriaRole.LIST)).containsText("Leave");
    }
}
