package playwright.testng.com.PlaywrightJenkinsDemo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class AppTest {
@Test
public void loginTest() {

    Playwright playwright = Playwright.create();

    Browser browser = playwright.chromium().launch(
        new BrowserType.LaunchOptions()
            .setHeadless(true)
    );

    Page page = browser.newPage();

    page.navigate("https://www.saucedemo.com/");

    page.locator("#user-name").fill("standard_user");

    page.locator("#password").fill("secret_sauce");

    page.locator("#login-button").click();

    String title = page.locator(".title").textContent();

    Assert.assertEquals(title, "Products");

    System.out.println("Login Successful!");

    browser.close();
    //playwright.close();
}
}
