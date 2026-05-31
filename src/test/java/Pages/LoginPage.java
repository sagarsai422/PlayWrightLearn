package Pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {
     private Page page;
     private final String usernameTextbox = "input[name='username']";
     private final String passwordTextbox = "input[name='password']";
     private final String loginButton = "button[type='submit']";

     public LoginPage(Page page) {
          this.page = page;
     }

     public void addusername(String username) {
          page.fill(usernameTextbox, username);
     }
     public void addpassword(String password) {
          page.fill(passwordTextbox, password);
     }
     public void clickloginbutton() {
          page.click(loginButton);
     }

     public void login(String username, String password) {
          page.fill(usernameTextbox, username);
          page.fill(passwordTextbox, password);
          page.click(loginButton);
     }
}
