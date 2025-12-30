package com.hanapos.testcases.WebAdmin_Testcases.Categories;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class Hana_T1244_Webadmin_Categories_Sorting_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private WebAdmin_Home_Page webadmin_homepage;
    private Webadmin_Categories_Page webadmin_categoriespage;
    private WebAdmin_Products_Page webadmin_productspage;

    String categoryName;
    String metaTitle;
    String metaKeywords;
    String editcategoryName;
    String editmetaTitle;
    String editmetaKeywords;

    @Epic("Web Admin Module - Categories")
    @Owner("Balaji N")
    @Test(enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T1244_Webadmin_Categories_Sorting_Functionality_Test() {
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
            String categoryname1 = webadmin_categoriespage.get_Row1_Category_Name_On_Web_Categories_Page_Table_Grid();
            String categoryname2 = webadmin_categoriespage.get_Row2_Category_Name_On_Web_Categories_Page_Table_Grid();
            String categoryname3 = webadmin_categoriespage.get_Row3_Category_Name_On_Web_Categories_Page_Table_Grid();

            delayWithGivenTime(2000);
            webadmin_categoriespage.click_Move_Up_Sort_Icon_Row2();
            delayWithGivenTime(2000);
            softassert.assertEquals(webadmin_categoriespage.get_Row2_Category_Name_On_Web_Categories_Page_Table_Grid(), categoryname1, "Test Step - 4: Category is not sorted using move up");

            // Test step - 5
            webadmin_categoriespage.click_Move_Down_Sort_Icon_Row2();
            delayWithGivenTime(2000);
            softassert.assertEquals(webadmin_categoriespage.get_Row2_Category_Name_On_Web_Categories_Page_Table_Grid(), categoryname3, "Test Step - 5: Category is not sorted using move down");

            // Test step - 6
            webadmin_categoriespage.click_Move_To_Top_Sort_Icon_Row2();
            delayWithGivenTime(2000);
            softassert.assertEquals(webadmin_categoriespage.get_Row1_Category_Name_On_Web_Categories_Page_Table_Grid(), categoryname3, "Test Step - 6: Category is not sorted using move to top");

            // Test step - 7
            webadmin_categoriespage.drag_and_drop_Category();
            delayWithGivenTime(2000);
            softassert.assertEquals(webadmin_categoriespage.get_Row1_Category_Name_On_Web_Categories_Page_Table_Grid(), categoryname2, "Test Step - 7: Category is not sorted using drag and drop");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }


    }


}
