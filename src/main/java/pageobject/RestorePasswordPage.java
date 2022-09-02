package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.page;

    public class RestorePasswordPage {
        public final static String RESTORE_PASS_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

        @FindBy(how = How.XPATH,using = ".//a[text()='Войти']")
        private SelenideElement signInLink;

        @Step("Go to link 'Enter'")
        public AuthorizationPage signInLinkClick (){
            signInLink.click();
            return page(AuthorizationPage.class);
        }
    }

