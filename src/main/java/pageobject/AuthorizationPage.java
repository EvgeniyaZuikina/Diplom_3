package pageobject;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class AuthorizationPage {
    public final static String AUTH_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    @FindBy(how = How.XPATH,using = ".//input[@name='name']")
    private SelenideElement emailInput;

    @Step("Input login")
    public AuthorizationPage setEmail(String email) {
        emailInput.shouldBe(empty).click();
        emailInput.setValue(email);
        return this;
    }

    @FindBy(how = How.XPATH,using = ".//input[@name='Пароль']")
    private SelenideElement passwordInput;

    @Step("Input password")
    public AuthorizationPage setPassword(String password) {
        passwordInput.shouldBe(empty).click();
        passwordInput.setValue(password);
        return this;
    }

    @FindBy(how = How.XPATH,using = ".//button[text()='Войти']")
    private SelenideElement enterButton;

    @Step("Push button 'Enter'")
    public MainPage enterButtonClick() {
        enterButton.shouldBe(Condition.enabled).click();
        return page(MainPage.class);
    }

    @Step ("Check button 'Enter' exists")
    public boolean isEnterButtonVisible() {
        return enterButton.shouldBe(visible).isDisplayed();
    }

    @FindBy(how = How.XPATH,using = ".//a[text()='Зарегистрироваться']")
    private SelenideElement registerLink;

    @Step("Go to link 'Register'")
    public RegistrationPage registerLinkClick() {
        registerLink.click();
        return page(RegistrationPage.class);
    }

    @FindBy(how = How.XPATH,using = ".//a[text()='Восстановить пароль']")
    private SelenideElement restorePasswordLink;

    @Step("Go to link 'Restore password'")
    public RestorePasswordPage restorePasswordLinkClick() {
        restorePasswordLink.click();
        return page(RestorePasswordPage.class);
    }

}
