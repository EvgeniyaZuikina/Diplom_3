package pageobject;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    public final static String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.XPATH, using = ".//button [text()='Войти в аккаунт']")
    public SelenideElement signInButton;

    @FindBy(how = How.XPATH,using = "//p[text()='Личный Кабинет']")
    public SelenideElement profileButton;

    @FindBy (how = How.XPATH, using = ".//span [@class='text text_type_main-default'][text()='Булки']")
    private SelenideElement bunsTab;

    @FindBy (how = How.XPATH, using = ".//span[@class='text text_type_main-default'][text()='Соусы']")
    private SelenideElement saucesTab;

    @FindBy (how = How.XPATH, using = ".//span[@class='text text_type_main-default'][text()='Начинки']")
    private SelenideElement fillingsTab;

    @FindBy(how = How.XPATH,using = "//div[contains(@class, 'current')]/span[text()='Булки']")
    private SelenideElement bunsTabSelected;

    @FindBy(how = How.XPATH,using = "//div[contains(@class, 'current')]/span[text()='Начинки']")
    private SelenideElement fillingsTabSelected;

    @FindBy(how = How.XPATH,using = "//div[contains(@class, 'current')]/span[text()='Соусы']")
    private SelenideElement saucesTabSelected;

    @FindBy(how = How.XPATH,using = "//p[text()='Флюоресцентная булка R2-D3']")
    private SelenideElement bunR2D3;

    @FindBy(how = How.XPATH,using = "//p[text()='Соус Spicy-X']")
    private SelenideElement sauceSpicy;

    @FindBy(how = How.XPATH,using = "//p[text()='Мясо бессмертных моллюсков Protostomia']")
    private SelenideElement fillingMeat;

    @FindBy (how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement makeOrderButton;

    public AuthorizationPage signInButtonClick (){
        signInButton.click();
        return page(AuthorizationPage.class);
    }

    public AuthorizationPage profileButtonClick (){
        profileButton.click();
        return page(AuthorizationPage.class);
    }

    public ProfilePage authUserProfileButtonClick (){
        profileButton.click();
        return page(ProfilePage.class);
    }

    @Step("Push 'Buns'")
    public MainPage bunsTabClick(){
        bunsTab.shouldBe(enabled).click();
        return this;
    }

    @Step ("Push 'Sauces'")
    public MainPage saucesTabClick(){
        saucesTab.shouldBe(enabled).click();
        return this;
    }

    @Step ("Push 'Fillings'")
    public MainPage fillingsTabClick(){
        fillingsTab.shouldBe(enabled).click();
        return this;
    }

    @Step("Check display of the selected tab 'Buns'")
    public boolean isBunTabSelected() {
        return bunsTabSelected.shouldBe(visible).isDisplayed();
    }

    @Step("Check display of the selected tab 'Fillings'")
    public boolean isFillingsTabSelected() {
        return fillingsTabSelected.shouldBe(visible).isDisplayed();
    }

    @Step("Check display of the selected tab 'Sauces'")
    public boolean isSaucesTabSelected() {
        return saucesTabSelected.shouldBe(visible).isDisplayed();
    }

    @Step("Check the display of the tab 'Fluorescent bun R2-D3'")
    public boolean isBunR2D3Visible() {
        return bunR2D3.shouldBe(visible).isDisplayed();
    }

    @Step("Check display of the tab 'Sauce Spicy-X'")
    public boolean isSauceSpicyVisible() {
        return sauceSpicy.shouldBe(visible).isDisplayed();
    }

    @Step("Check display of the tab 'Meat of Immortal Clams Protostomia'")
    public boolean isFillingMeatVisible() {
        return fillingMeat.shouldBe(visible).isDisplayed();
    }

    public boolean makeOrderButtonVisible (){
        return makeOrderButton.shouldBe(visible).isDisplayed();
    }

}
