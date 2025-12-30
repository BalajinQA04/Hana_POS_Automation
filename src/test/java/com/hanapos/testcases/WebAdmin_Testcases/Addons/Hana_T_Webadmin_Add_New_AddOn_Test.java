package com.hanapos.testcases.WebAdmin_Testcases.Addons;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class Hana_T_Webadmin_Add_New_AddOn_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private WebAdmin_Home_Page webadmin_homepage;
    private WebAdmin_Addon_Page addon_page;
    Faker faker = new Faker(new java.util.Locale("en-US"));
    String caption;
    String updated_caption;

    @Epic("Web Admin Module - Add-Ons")
    @Owner("Balaji N")
    @Test(enabled = true, priority = 1, groups = {"Smoke", "Regression", "Sanity"})
    public void Validate_Hana_T_Webadmin_Add_New_AddOn_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();
        try {
            // Test Step - 1: Initialize LoginPage and verify the login screen is displayed
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2: Authenticate with valid credentials, navigate to Dashboard and select shop
            lp.EnterUserName(prop.getProperty("username"));//bestuname
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));//bestpass
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page");
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));

            // Test Step - 3: Open Web Admin from Dashboard and navigate to Add-Ons tab
            webadmin_homepage = new WebAdmin_Home_Page();
            dashboard.Click_WebAdmin_Submenu();

            softassert.assertTrue(webadmin_homepage.Verify_WebAdmin_home_header_IsDisplayed(), "Test Step - 3: Web Admin Home Page is not displayed");
            softassert.assertEquals(webadmin_homepage.get_selected_shopname_on_WebAdmin_HomePage(), prop.getProperty("shopname"), "Test Step - 3: Selected Shopname is not displayed on web admin home page");

            delayWithGivenTime(2000);
            webadmin_homepage.click_Addons_Tab_WebAdmin_HomePage();

            // Test Step - 4: Verify Add-Ons page is displayed
            addon_page = new WebAdmin_Addon_Page();
            softassert.assertTrue(addon_page.is_Addon_Page_Displayed(), "Test Step - 4: Addon Page is not displayed");

            // Test Step - 5: Click on 'Add New Add-On' button
            addon_page.click_Add_New_AddOn_Button();

            // Test Step - 6: Enter basic details - Caption, Image, Sales Category
            caption = addon_page.generateCaption();
            addon_page.enter_Caption(caption);
            delayWithGivenTime(1000);
            softassert.assertEquals(addon_page.get_Caption(), caption, "Test Step - 10: Entered Caption is not displayed");

            addon_page.Upload_Image_for_Addon("roses red.jpg");
            delayWithGivenTime(2000);

            addon_page.select_Addon_Sales_Category("Birthday");
            delayWithGivenTime(2000);
            softassert.assertEquals(addon_page.get_Addon_Sales_Category(), "Birthday", "Test Step - 6: Selected Addon sales category is not properly displayed");

            // Test Step - 7: Select Add-On Type as 'Quantity'
            addon_page.select_Addon_Type("Quantity");
            delayWithGivenTime(2000);
            softassert.assertEquals(addon_page.get_Addon_Type(), "Quantity", "Test Step - 7: Selected Addon type is not properly displayed");

            // Test Step - 8: Provide item details - Name, Price, and Item Code
            String addon_name = faker.commerce().productName();
            String price = faker.commerce().price(5.0, 50.0);
            // Create unique itemCode: 3 letters from name + 4-digit number
            String prefix = addon_name.replaceAll("[^A-Za-z]", "").substring(0, 3).toUpperCase();
            String itemCode = prefix + faker.number().numberBetween(1000, 9999);

            addon_page.enter_Addon_Name(addon_name);
            delayWithGivenTime(2000);
            softassert.assertEquals(addon_page.get_Addon_Name(), addon_name, "Test Step - 8: Addon name is not matching with expected name");

            addon_page.enter_Addon_Price(price);
            delayWithGivenTime(2000);
            softassert.assertEquals(addon_page.get_Addon_Price(), price, "Test Step - 8: Addon price is not matching with expected price");

            addon_page.enter_Addon_ItemCode(itemCode);
            delayWithGivenTime(2000);
            softassert.assertEquals(addon_page.get_Addon_ItemCode(), itemCode, "Test Step - 8: Addon itemcode is not displayed");

            // Test Step - 9: Clear active dates and select Web Category
            addon_page.click_Clear_Active_Dates_Button();
            addon_page.select_Web_Category();
            press_Tab_Key();

            // Test Step - 10: Create the Add-On and verify it's listed in the grid
            delayWithGivenTime(2000);
            addon_page.click_Create_Addon_Button();

            delayWithGivenTime(3000);
            addon_page.select_Records_Per_Page();
            delayWithGivenTime(2000);
            softassert.assertEquals(addon_page.is_Expected_AddOn_Caption_Present(caption), true, "Test Step - 10: Expected Addon caption is not displayed in table grid");

        } catch (Exception e) {
            softassert.fail("Exception is: " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }

    @Epic("Web Admin Module - Add-Ons")
    @Owner("Balaji N")
    @Test(enabled = true, priority = 2, groups = {"Smoke", "Regression", "Sanity"})
    public void Validate_Hana_T_Webadmin_Update_AddOn_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();
        try {
            // Test Step - 1: Initialize LoginPage and verify the login screen is displayed
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2: Authenticate with valid credentials, navigate to Dashboard and select shop
            lp.EnterUserName(prop.getProperty("username"));//bestuname
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));//bestpass
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page");
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));

            // Test Step - 3: Open Web Admin from Dashboard and navigate to Add-Ons tab
            webadmin_homepage = new WebAdmin_Home_Page();
            dashboard.Click_WebAdmin_Submenu();

            softassert.assertTrue(webadmin_homepage.Verify_WebAdmin_home_header_IsDisplayed(), "Test Step - 3: Web Admin Home Page is not displayed");
            softassert.assertEquals(webadmin_homepage.get_selected_shopname_on_WebAdmin_HomePage(), prop.getProperty("shopname"), "Test Step - 3: Selected Shopname is not displayed on web admin home page");

            delayWithGivenTime(2000);
            webadmin_homepage.click_Addons_Tab_WebAdmin_HomePage();

            // Test Step - 4: Verify Add-Ons page is displayed
            addon_page = new WebAdmin_Addon_Page();
            softassert.assertTrue(addon_page.is_Addon_Page_Displayed(), "Test Step - 4: Addon Page is not displayed");

            // Test Step - 5: Click on 'Add New Add-On' button
            addon_page.click_Add_New_AddOn_Button();

            // Test Step - 6: Enter basic details - Caption, Image, Sales Category
            caption = addon_page.generateCaption();
            addon_page.enter_Caption(caption);
            delayWithGivenTime(1000);
            softassert.assertEquals(addon_page.get_Caption(), caption, "Test Step - 10: Entered Caption is not displayed");

            addon_page.Upload_Image_for_Addon("roses red.jpg");
            delayWithGivenTime(2000);

            addon_page.select_Addon_Sales_Category("Birthday");
            delayWithGivenTime(2000);
            softassert.assertEquals(addon_page.get_Addon_Sales_Category(), "Birthday", "Test Step - 6: Selected Addon sales category is not properly displayed");

            // Test Step - 7: Select Add-On Type as 'Quantity'
            addon_page.select_Addon_Type("Quantity");
            delayWithGivenTime(2000);
            softassert.assertEquals(addon_page.get_Addon_Type(), "Quantity", "Test Step - 7: Selected Addon type is not properly displayed");

            // Test Step - 8: Provide item details - Name, Price, and Item Code
            String addon_name = faker.commerce().productName();
            String price = faker.commerce().price(5.0, 50.0);
            // Create unique itemCode: 3 letters from name + 4-digit number
            String prefix = addon_name.replaceAll("[^A-Za-z]", "").substring(0, 3).toUpperCase();
            String itemCode = prefix + faker.number().numberBetween(1000, 9999);

            addon_page.enter_Addon_Name(addon_name);
            delayWithGivenTime(2000);
            softassert.assertEquals(addon_page.get_Addon_Name(), addon_name, "Test Step - 8: Addon name is not matching with expected name");

            addon_page.enter_Addon_Price(price);
            delayWithGivenTime(2000);
            softassert.assertEquals(addon_page.get_Addon_Price(), price, "Test Step - 8: Addon price is not matching with expected price");

            addon_page.enter_Addon_ItemCode(itemCode);
            delayWithGivenTime(2000);
            softassert.assertEquals(addon_page.get_Addon_ItemCode(), itemCode, "Test Step - 8: Addon itemcode is not displayed");

            // Test Step - 9: Clear active dates and select Web Category
            addon_page.click_Clear_Active_Dates_Button();

            addon_page.select_Web_Category();
            press_Tab_Key();

            // Test Step - 10: Create the Add-On and verify it's listed in the grid
            delayWithGivenTime(2000);
            addon_page.click_Create_Addon_Button();

            delayWithGivenTime(3000);
            addon_page.select_Records_Per_Page();
            delayWithGivenTime(2000);
            softassert.assertEquals(addon_page.is_Expected_AddOn_Caption_Present(caption), true, "Test Step - 10: Expected Addon caption is not displayed in table grid");
            delayWithGivenTime(3000);
            // Navigate to the Edit screen for the created Add-On
            addon_page.click_Edit_Icon_For_Respective_Addon(caption);

            // Test Step - 11: Update details - Caption, Image, Sales Category, Type, Name, Price, Item Code
            updated_caption = addon_page.generateCaption();
            addon_page.enter_Caption(updated_caption);
            delayWithGivenTime(1000);
            softassert.assertEquals(addon_page.get_Caption(), updated_caption, "Test Step - 10: Updated Caption is not displayed");

            addon_page.Upload_Image_for_Addon("roses red.jpg");
            delayWithGivenTime(2000);

            addon_page.select_Addon_Sales_Category("Mother's Day");
            delayWithGivenTime(2000);
            softassert.assertEquals(addon_page.get_Addon_Sales_Category(), "Mother's Day", "Test Step - 6: Selected Addon sales category Updated is not properly displayed");

            // Test Step - 7: Select Add-On Type as 'Quantity' (update scenario)
            addon_page.select_Addon_Type("Quantity");
            delayWithGivenTime(2000);
            softassert.assertEquals(addon_page.get_Addon_Type(), "Quantity", "Test Step - 7: Selected Addon type Updated is not properly displayed");

            // Test Step - 8: Update item details - Name, Price, and Item Code
            String update_addon_name = faker.commerce().productName();
            String update_price = faker.commerce().price(5.0, 50.0);
            // Create unique itemCode: 3 letters from name + 4-digit number
            String update_prefix = addon_name.replaceAll("[^A-Za-z]", "").substring(0, 3).toUpperCase();
            String update_itemCode = update_prefix + faker.number().numberBetween(1000, 9999);

            addon_page.enter_Addon_Name(update_addon_name);
            delayWithGivenTime(2000);
            softassert.assertEquals(addon_page.get_Addon_Name(), update_addon_name, "Test Step - 8: Updated Addon name is not matching with expected name");

            addon_page.enter_Addon_Price(update_price);
            delayWithGivenTime(2000);
            softassert.assertEquals(addon_page.get_Addon_Price(), update_price, "Test Step - 8: Updated Addon price is not matching with expected price");

            addon_page.enter_Addon_ItemCode(update_itemCode);
            delayWithGivenTime(2000);
            softassert.assertEquals(addon_page.get_Addon_ItemCode(), update_itemCode, "Test Step - 8: UpdatedAddon itemcode is not displayed");

            // Test Step - 9: Clear active dates (update scenario)
            addon_page.click_Clear_Active_Dates_Button();

            // Test Step - 10: Save the updated Add-On and validate success toaster
            delayWithGivenTime(2000);
            addon_page.click_Create_Addon_Button();

            delayWithGivenTime(3000);
            softassert.assertTrue(addon_page.validate_toaster_message_IsAppears(), "After updating the add-on, toaster message is not displayed");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }


    }


}
