import pageobject.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static pageobject.MainPage.MAIN_PAGE_URL;
import static org.junit.Assert.assertTrue;

public class ConstructorTest extends Config {
    MainPage mainPage;

    @Before
    public void startUp() {
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    @After
    public void tearDown() {
        webdriver().driver().close();
    }

    @Test
    @DisplayName("Checkout: user can go to the tab 'Sauces' on the Main Page")
    @Description("Section 'Sauces' and sauce 'Spicy-X' are displayed")
    public void saucesSectionTest(){
        boolean isSaucesSectionDisplayed =
                mainPage
                        .saucesTabClick()
                        .isSaucesTabSelected();
        assertTrue("Section 'Sauces' didn't appear", isSaucesSectionDisplayed);
        assertTrue("Sauce 'Spicy-X' didn't appear", mainPage.isSauceSpicyVisible());
    }

    @Test
    @DisplayName("Checkout: user can go to the tab 'Buns' on the Main Page from another tabs")
    @Description("Section 'Buns' and 'Fluorescent bun R2-D3 are displayed")
    public void bunsSectionTest(){
        boolean isBunsSectionDisplayed =
                mainPage
                        .saucesTabClick()
                        .bunsTabClick()
                        .isBunTabSelected();
        assertTrue("Section 'Buns' didn't appear", isBunsSectionDisplayed);
        assertTrue("Fluorescent bun R2-D3 didn't appear", mainPage.isBunR2D3Visible());
    }

    @Test
    @DisplayName("Checkout: user can go to the tab 'Fillings' on the Main Page")
    @Description("Section 'Fillings' and filling 'Immortal Clam Meat Protostomia' are displayed")
    public void fillingsSectionTest(){
        boolean isFillingsSectionDisplayed =
                mainPage
                        .fillingsTabClick()
                        .isFillingsTabSelected();
        assertTrue("Section 'Fillings' didn't appear", isFillingsSectionDisplayed);
        assertTrue("Filling 'Immortal Clam Meat Protostomia' didn't appear", mainPage.isFillingMeatVisible());
    }
}