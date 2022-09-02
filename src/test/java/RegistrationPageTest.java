import pageobject.*;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.RegistrationPage;

import java.time.Instant;
import java.util.Map;
import static com.codeborne.selenide.Selenide.*;
import static pageobject.MainPage.MAIN_PAGE_URL;
import static org.junit.Assert.*;

public class RegistrationPageTest extends Config {
    UserActivity userActivity = new UserActivity();
    Map<String, String> newUser = userActivity.register();
    String email = newUser.get("email");
    String password = newUser.get("password");
    String name = newUser.get("name");
    MainPage mainPage;
    RegistrationPage registrationPage = page(RegistrationPage.class);

    Instant instant = Instant.now();
    long timeStampMillis = instant.toEpochMilli();

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
    @DisplayName("Checkout: user can register")
    @Description("If register is successful button 'Enter' appears on Authorization Page")
    public void successRegistrationNewUserTest() {
        mainPage
                .signInButtonClick()
                .registerLinkClick()
                .setName(name)
                .setEmail(timeStampMillis+email)
                .setPassword(password)
                .regButtonClick();
        boolean isEnterButtonDisplayed = page(AuthorizationPage.class).isEnterButtonVisible();
        assertTrue("Button 'Enter' didn't appear", isEnterButtonDisplayed);
    }

    @Test
    @DisplayName("Checkout: user can't register with password less 6 symbols")
    @Description("Error 'Incorrect password' appears")
    public void registrationNewUserWithIncorrectPasswordTest() {
        String wrongPass = "fxhgv";
        mainPage
                .signInButtonClick()
                .registerLinkClick()
                .setName(name)
                .setEmail(email)
                .setPassword(wrongPass)
                .regButtonClick();
        assertTrue("Error 'Incorrect password' did not appear", registrationPage.isWrongPassErrorMessageVisible());
    }
}