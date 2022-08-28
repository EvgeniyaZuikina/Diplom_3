import pageobject.*;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static pageobject.MainPage.MAIN_PAGE_URL;
import static org.junit.Assert.assertTrue;
import java.util.Map;

public class AuthorizationPageTest extends Config {
    UserActivity userActivity = new UserActivity();
    Map<String, String> newUser = userActivity.register();
    String email = newUser.get("email");
    String password = newUser.get("password");
    MainPage mainPage;

    @Before
    public void startUp() {
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    @After
    public void tearDown() {
        userActivity.delete();
        webdriver().driver().close();
    }

    @Test
    @DisplayName("Checkout: user can login using the link 'SignIn Account' on the Main Page")
    @Description("After successful login, a button 'Make Order' will appear on the Main Page")
    public void loginUserWithEnterButtonOnMainPageTest(){
        mainPage
                .signInButtonClick()
                .setEmail(email)
                .setPassword(password)
                .enterButtonClick();
        boolean isMakeOrderButtonDisplayed = page(MainPage.class).makeOrderButtonVisible();
        assertTrue("Button 'Make Order' didn't appear", isMakeOrderButtonDisplayed);
    }

    @Test
    @DisplayName("Checkout: user can login using the link 'Account Profile' on the Main Page")
    @Description("After successful login, a button 'Make Order' will appear on the Main Page")
    public void loginUserWithProfileButtonOnMainPageTest(){
        mainPage
                .profileButtonClick()
                .setEmail(email)
                .setPassword(password)
                .enterButtonClick();
        boolean isMakeOrderButtonDisplayed = page(MainPage.class).makeOrderButtonVisible();
        assertTrue("Button 'Make Order' didn't appear", isMakeOrderButtonDisplayed);
    }

    @Test
    @DisplayName("Checkout: user can login using the link 'Enter' on the registration form")
    @Description("After successful login, button 'Make Order' will appear on the Main Page")
    public void loginUserWithLoginLinkOnRegPageTest(){
        mainPage
                .signInButtonClick()
                .registerLinkClick()
                .loginLinkClick()
                .setEmail(email)
                .setPassword(password)
                .enterButtonClick();
        boolean isMakeOrderButtonDisplayed = page(MainPage.class).makeOrderButtonVisible();
        assertTrue("Button 'Make Order' didn't appear", isMakeOrderButtonDisplayed);
    }

    @Test
    @DisplayName("Checkout: user can login with link 'Enter' on restore password form")
    @Description("After successful login button 'Make Order' will appear on the Main Page")

    public void loginUserWithSignInLinkOnRestorePageTest(){
        mainPage
                .signInButtonClick()
                .restorePasswordLinkClick()
                .signInLinkClick()
                .setEmail(email)
                .setPassword(password)
                .enterButtonClick();
        boolean isMakeOrderButtonDisplayed = page(MainPage.class).makeOrderButtonVisible();
        assertTrue("Button 'Make Order' didn't appear", isMakeOrderButtonDisplayed);
    }
}