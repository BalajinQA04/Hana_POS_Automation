package com.hanapos.testcases.WebAdmin_Testcases.Categories;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class Hana_T1248_Webadmin_Categories_SortBy_Category_Name_Ok_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private WebAdmin_Home_Page webadmin_homepage;
    private Webadmin_Categories_Page webadmin_categoriespage;
    private WebAdmin_Products_Page webadmin_productspage;

    String categoryName;
    String firstThreeChars;
    String metaTitle;
    String metaKeywords;

    @Epic("Web Admin Module - Categories")
    @Owner("Balaji N")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T1247_Webadmin_Categories_SortBy_Category_Name_Cancel_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 1: Entered username is not matching with expected username");
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 1: Entered password is not matching with expected password");
            lp.ClickLoginButton();

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));

            // Test Step - 3
            webadmin_homepage = new WebAdmin_Home_Page();
            dashboard.Click_WebAdmin_Submenu();

            softassert.assertTrue(webadmin_homepage.Verify_WebAdmin_home_header_IsDisplayed(), "Test Step - 3: Web Admin Home Page is not displayed");
            webadmin_homepage.click_categories_tab_webAdmin_homepage();

            delayWithGivenTime(2000);
            webadmin_categoriespage = new Webadmin_Categories_Page();
            softassert.assertEquals(webadmin_categoriespage.get_PageTitle(), "Web Categories", "Test Step - 3: Web Admin Categories Page is not displayed");

            // Test step - 4
            softassert.assertTrue(webadmin_categoriespage.is_SortBy_Dropdown_On_Web_Categories_Page_Visible(), "Test Step - 4: Sort by dropdown on Web Admin Categories Page is not displayed");

            webadmin_categoriespage.select_SortBy_Dropdown("Category Name");
            delayWithGivenTime(2000);
            softassert.assertTrue(webadmin_categoriespage.is_Alert_Popup_Displayed(), "Test Step - 4: Alert popup on Web Admin Categories Page is not displayed after select the sortt by dropdown option as Category Name");

            // Test Step - 5
            String confirmationCode = webadmin_categoriespage.get_Alert_Popup_Confirmation_Code_Text();
            webadmin_categoriespage.enter_Confirmation_Code(confirmationCode);

            // Test Step - 6
            webadmin_categoriespage.click_OK_Button_On_Alert_Popup();

            // Test Step - 7
            delayWithGivenTime(2000);
            webadmin_productspage = new WebAdmin_Products_Page();
            softassert.assertEquals(webadmin_productspage.validate_toaster_message_Text(), "Category Order Updated", "Test Step - 7: Product Saved Successfully toaster message is not displayed");
            softassert.assertEquals(webadmin_categoriespage.get_Selected_SortBy_Dropdown_Value(), "Category Name", "Test Step - 7: Sort by dropdown value is not matching with expected value - Category Name");

            // Test Step - 8
            softassert.assertTrue(webadmin_categoriespage.isCategoryListSortedDescending(), "Test Step - 8: Category list is not sorted in ascending alphabetical order once selected sort by dropdown value as Category Name");


        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }


    }


}
