package Tests;

import Base.BaseClass;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class LocatorsDemo extends BaseClass {

    @Test
    public void testAllLocators() {
        System.out.println("Testing all locators...");
        // Add your test code here to demonstrate the use of different locators
        page.navigate("https://trytestingthis.netlify.app/");

        //First name using ID attribute
        page.locator("#fname").fill("Sagar");
        //Last name using name attribute
        page.locator("input[name='lname']").fill("Veeranki");
        //Gender radio button using value attribute
        page.locator("input[value='male']").check();
        //Nth Element - select second radio manually
        page.locator("input[type='radio']").nth(1).check();//Female
        //Dropdown using ID
        page.locator("select#option").selectOption("option 3");
        page.locator("#option").selectOption("option 2");
        //CheckBox
        page.locator("input[type='checkbox'][value='Option 3']").check();
        page.getByLabel("Option 1").check();
        page.getByLabel("Option 2").check();
        //Checkbox using name
        page.locator("input[name='option1']").check(); // Option 1
        page.locator("input[name='option2']").check(); // Option 2
        //  XPath (avoid if possible, but sometimes needed)
        //page.locator("//input[@type='date']").fill("2023-12-25");
        //Date
        page.locator("input[type='date']").fill("2024-06-01");
        //use visible text of buttons or links
        page.getByText("Click Me").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();

        //page.pause();


    }
}
