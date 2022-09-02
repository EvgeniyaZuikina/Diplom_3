package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ProfilePage {

    public final static String PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement logoutButton;

    @FindBy(how = How.XPATH, using = "//*[text()='Конструктор']")
    private SelenideElement constructorButton;

    @FindBy(how = How.XPATH, using = "//*[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement burgerLogo;

    @FindBy(how = How.XPATH, using = "(//div[@class='input__container']//div/input[@type='text'])[1]")
    private SelenideElement nameInput;

    @FindBy(how = How.XPATH, using = "(//div[@class='input__container']//div/input[@type='text'])[2]")
    private SelenideElement loginInput;

    public String getNameInputValue() {
        return nameInput.getValue();
    }

    public String getLoginInputValue() {
        return loginInput.getValue();
    }

    @Step("Push button 'Exit'")
    public MainPage logoutButtonClick() {
        logoutButton.click();
        return page(MainPage.class);
    }

    @Step("Push button 'Constructor'")
    public MainPage constructorButtonClick() {
        constructorButton.click();
        return page(MainPage.class);
    }

    @Step("Push logo")
    public MainPage burgerLogoClick() {
        burgerLogo.click();
        return page(MainPage.class);
    }
}
