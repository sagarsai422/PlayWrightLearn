package Pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage {
    private final String timelink = "getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(\"Time\"))";

    private Page page;
    public HomePage(Page page) {
        this.page = page;
    }

    public void clicktimelink() {
        page.click(timelink);
//        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Time")).click();
    }

}
