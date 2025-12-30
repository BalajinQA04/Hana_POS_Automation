package com.hanapos.testcases.WebAdmin_Testcases.Categories;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static com.hanapos.pageObjects.Webadmin_Categories_Page.generate_Meta_Keywords;

public class Hana_T1245_Webadmin_Categories_Search_Functionality extends TestBaseClass {
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
    public void Validate_Hana_T1245_Webadmin_Categories_Search_Functionality_Test() {
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
            webadmin_categoriespage.click_Add_New_Category_Button();
            delayWithGivenTime(2000);

            categoryName = generateWebCategoryName();
            firstThreeChars = categoryName.substring(0, Math.min(3, categoryName.length()));
            metaTitle = generateCategoryMetaTitle();
            metaKeywords = generate_Meta_Keywords();
            webadmin_categoriespage.enter_Category_Name(categoryName);
            webadmin_categoriespage.select_Parent_Category("Balaji Flower category");
            webadmin_categoriespage.enter_Category_Description(categoryName + " Balaji Flower category as parent category ");
            webadmin_categoriespage.enter_Meta_Title(metaTitle);
            webadmin_categoriespage.enter_Meta_Keywords(metaKeywords);
            webadmin_categoriespage.click_Create_Web_Category_Button();
            delayWithGivenTime(2000);

            webadmin_productspage = new WebAdmin_Products_Page();
            softassert.assertEquals(webadmin_productspage.validate_toaster_message_Text(), "Category Details Saved.", "Test Step - 7: Product Saved Successfully toaster message is not displayed");

            webadmin_categoriespage.search_Web_Category(categoryName);
            delayWithGivenTime(2000);
            softassert.assertEquals(webadmin_categoriespage.get_Category_Name_On_Web_Categories_Page_Table_Grid(), categoryName, "Test Step - 7: Searched Category is not displayed");

            webadmin_categoriespage.search_Web_Category(firstThreeChars);
            delayWithGivenTime(2000);
            softassert.assertEquals(webadmin_categoriespage.get_Category_Name_On_Web_Categories_Page_Table_Grid(), categoryName, "Test Step - 8: Searched Category with first three characters is not displayed");

            delayWithGivenTime(2000);
            webadmin_categoriespage.clear_Search_Web_Category();
            delayWithGivenTime(3000);
            softassert.assertTrue(webadmin_categoriespage.is_Category_List_Displayed_After_Clear_Search(), "Test Step - 9: Category list is not displayed");

            // Avoid junk delete the created category
            webadmin_categoriespage.search_Web_Category(categoryName);
            delayWithGivenTime(2000);
            webadmin_categoriespage.click_Delete_Icon_On_Web_Categories_Page_Table_Grid();
            delayWithGivenTime(2000);
            softassert.assertEquals(webadmin_productspage.validate_toaster_message_Text(), "Category Deleted.", "Test Step - 7: Product Saved Successfully toaster message is not displayed");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }


    }


}
