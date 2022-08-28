import pageobject.*;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static pageobject.MainPage.MAIN_PAGE_URL;

public class ProfilePageTest extends Config {
    UserActivity userActivity = new UserActivity();
    Map<String, String> newUser = userActivity.register();
    String email = newUser.get("email");
    String password = newUser.get("password");
    String name = newUser.get("name");
    MainPage mainPage;
    AuthorizationPage authPage = page(AuthorizationPage.class);
    ProfilePage profilePage = page(ProfilePage.class);

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
    @DisplayName("Checkout: authorized user can logout to click button 'Exit' from personal account")
    @Description("If logout was success button 'Enter' will be displayed")
    public void logoutWithExitButtonTest() {
        mainPage
                .signInButtonClick()
                .setEmail(email)
                .setPassword(password)
                .enterButtonClick()
                .authUserProfileButtonClick()
                .logoutButtonClick();
        assertTrue("Button 'Enter' didn't appear", authPage.isEnterButtonVisible());
    }

    @Test
    @DisplayName("Checkout: authorized user can go from the Main page to the Personal Account if click the button 'Personal Account'")
    @Description("If transition is successful Profile page will be displayed with the name and mail")
    public void enterAuthUserProfileTest() {
        mainPage.signInButtonClick()
                .setEmail(email)
                .setPassword(password)
                .enterButtonClick()
                .profileButtonClick();
        assertNotNull("Login isn't displayed", profilePage.getLoginInputValue());
        assertNotNull("Name isn't displayed", profilePage.getNameInputValue());
        assertEquals("Incorrect login is displayed in profile", email, profilePage.getLoginInputValue());
        assertEquals("Incorrect name is displayed in profile", name, profilePage.getNameInputValue());
    }

    @Test
    @DisplayName("Checkout: unauthorized user can't go from the Main page to the Personal Account by clicking the 'Personal Account'")
    @Description("After successful transition button 'Enter' will be displayed")
    public void notEnterNonAuthUserProfileTest() {
        mainPage.profileButtonClick();
        assertTrue("Button 'Enter' didn't appear", authPage.isEnterButtonVisible());
    }

    @Test
    @DisplayName("Checkout: authorized user can go from the Personal Account to the Constructor if click on the button 'Constructor'")
    @Description("After successful transition button 'Make Order' will be displayed")
    public void goToConstructorFromProfileTest() {
        mainPage
                .signInButtonClick()
                .setEmail(email)
                .setPassword(password)
                .enterButtonClick()
                .authUserProfileButtonClick()
                .constructorButtonClick();
        assertTrue("Button 'Make Order' didn't appear", mainPage.makeOrderButtonVisible());
    }

    @Test
    @DisplayName("Checkout: authorized user can go from the Personal Account to the Main Page if click on the logo button")
    @Description("After successful transition button 'Make Order' will be displayed")

    public void goToMainPageFromProfileWithBurgerLogoTest() {
        mainPage.signInButtonClick()
                .setEmail(email)
                .setPassword(password)
                .enterButtonClick()
                .authUserProfileButtonClick()
                .burgerLogoClick();
        assertTrue("Button 'Make Order' didn't appear", mainPage.makeOrderButtonVisible());
    }
}
