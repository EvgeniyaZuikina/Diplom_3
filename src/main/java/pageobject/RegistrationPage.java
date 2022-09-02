package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {
    public final static String REG_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    @FindBy(how = How.XPATH, using = "//input[@type='text']")
    private SelenideElement nameInput;

    @FindBy(how = How.XPATH, using = ".//fieldset[2]//input")
    private SelenideElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@type='password']")
    private SelenideElement passwordInput;

    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement regButton;

    @FindBy(how = How.XPATH, using = ".//*[@class='input__error text_type_main-default']")
    private SelenideElement wrongPassErrorMessage;

    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginLink;

    @Step("Input name")
    public RegistrationPage setName(String name) {
        nameInput.shouldBe(Condition.enabled).shouldBe(empty).click();
        nameInput.setValue(name);
        return this;
    }

    @Step ("Input email")
    public RegistrationPage setEmail(String email) {
        emailInput.shouldBe(Condition.enabled).shouldBe(empty).click();
        emailInput.setValue(email);
        return this;
    }

    @Step ("Input password")
    public RegistrationPage setPassword(String password) {
        passwordInput.shouldBe(Condition.enabled).shouldBe(empty).click();
        passwordInput.setValue(password);
        return this;
    }

    @Step ("Push button 'Registration'")
    public AuthorizationPage regButtonClick() {
        regButton.shouldBe(Condition.enabled).scrollTo().click();
        return page(AuthorizationPage.class);
    }

    @Step ("Go to link 'Enter'")
    public AuthorizationPage loginLinkClick() {
        loginLink.scrollTo().click();
        return page(AuthorizationPage.class);
    }

    @Step ("Check if the error text is visible when the password is incorrect")
    public boolean isWrongPassErrorMessageVisible() {
        return wrongPassErrorMessage.isDisplayed();
    }
}