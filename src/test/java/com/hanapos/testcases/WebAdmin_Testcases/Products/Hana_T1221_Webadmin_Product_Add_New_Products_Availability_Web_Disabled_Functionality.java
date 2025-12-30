package com.hanapos.testcases.WebAdmin_Testcases.Products;

import com.hanapos.ecommerce_pageObjects.*;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class Hana_T1221_Webadmin_Product_Add_New_Products_Availability_Web_Disabled_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private WebAdmin_Home_Page webadmin_homepage;
    private WebAdmin_Products_Page webadmin_productspage;
    private Ecommerce_HomePage ecommerce_homePage;
    private Ecommerce_SearchResults_Page searchResultsPage;

    @Epic("Web Admin Module - Products")
    @Owner("Balaji N")
    @Test(enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T1221_Webadmin_Product_Add_New_Products_Availability_Web_Disabled_Functionality() {
        CustomSoftAssert softassert = new CustomSoftAssert();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("bestuname"));
            lp.EnterPassword(prop.getProperty("bestpass"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("bestuname"), "Test Step - 1: Entered username is not matching with expected username");
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("bestpass"), "Test Step - 1: Entered password is not matching with expected password");
            lp.ClickLoginButton();

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            dashboard.SelectShopNameDropDown(prop.getProperty("bestshopname"));

            // Test Step - 3
            webadmin_homepage = new WebAdmin_Home_Page();
            dashboard.Click_WebAdmin_Submenu();

            softassert.assertTrue(webadmin_homepage.Verify_WebAdmin_home_header_IsDisplayed(), "Test Step - 3: Web Admin Home Page is not displayed");
            // softassert.assertEquals(webadmin_homepage.get_selected_shopname_on_WebAdmin_HomePage(), prop.getProperty("shopname"), "Test Step - 3: Selected Shopname is not displayed on web admin home page");
            webadmin_homepage.Click_products_tab_webAdmin_homepage();

            // Test Step - 4
            webadmin_productspage = new WebAdmin_Products_Page();
            softassert.assertTrue(webadmin_productspage.Verify_Products_Page_header(), "Test Step - 4: Web Admin Products Page is not displayed");

            // Test Step - 5
            webadmin_productspage.Click_AddNewButton();
            softassert.assertTrue(webadmin_productspage.Verify_AddNewProduct_Popup_IsDisplayed(), "Test Step - 4: Web Admin Products page - Add New Product Popup is not displayed");

            // Test Step - 6
            String item_name = getRandomFlowerName();
            String item_code = item_name + "_" + generaterandomeNumber(3);
            webadmin_productspage.Enter_ItemName_On_AddNewProduct_Popup(item_name);
            webadmin_productspage.Enter_ItemCode_On_AddNewProduct_Popup(item_code);
            delayWithGivenTime(2000);
            softassert.assertEquals(webadmin_productspage.get_entered_itemname_on_addnewproduct_popup(), item_name, "Test Step - 5: Entered item name on add new product popup is not displayed");
            softassert.assertEquals(webadmin_productspage.get_entered_itemcode_on_addnewproduct_popup(), item_code, "Test Step - 5: Entered item code on add new product popup is not displayed");

            delayWithGivenTime(2000);
            String product_variant = generate_Product_Variant();
            webadmin_productspage.Enter_Product_Variant_Name1(product_variant);
            webadmin_productspage.Enter_Product_Variant_SKU1("");
            webadmin_productspage.Enter_Product_Variant_Cost1("20.67");
            webadmin_productspage.Enter_Product_Variant_Price1("30.67");

            softassert.assertEquals(webadmin_productspage.get_entered_product_variant_name1(), product_variant, "Test Step - 6: Entered item name Product Variant for row 1 is not matched");
            softassert.assertEquals(webadmin_productspage.get_entered_product_variant_sku1(), item_code + "_1", "Test Step - 6: Entered item sku Product Variant for row 1 is not matched");
            softassert.assertEquals(webadmin_productspage.get_entered_product_variant_cost1(), "20.67", "Test Step - 6: Entered cost Product Variant for row 1 is not matched");
            softassert.assertEquals(webadmin_productspage.get_entered_product_variant_price1(), "30.67", "Test Step - 6: Entered price Product Variant for row 1 is not matched");

            webadmin_productspage.Click_UploadIcon_for_ProductVariant1();
            delayWithGivenTime(2000);
            webadmin_productspage.drag_And_Drop_Image_On_ProductVariant1("color full flowers.jpg");
            webadmin_productspage.Click_Skip_Button();
            getDriver().switchTo().defaultContent();
            delayWithGivenTime(4000);
            webadmin_productspage.Click_Save_button_on_AddNewProduct_Popup();
            delayWithGivenTime(2000);

            softassert.assertEquals(webadmin_productspage.validate_toaster_message_Text(), "Product Details Saved.", "Test Step - 7: Product Saved Successfully toaster message is not displayed");
            delayWithGivenTime(2000);
            softassert.assertEquals(webadmin_productspage.get_Displayed_itemcode_on_basic_detailsTab(), item_code, "Test Step - 7: Entered Item Code is not displayed or not matched on basic details tab");
            softassert.assertEquals(webadmin_productspage.get_Displayed_itemname_on_basic_detailsTab(), item_name, "Test Step - 7: Entered Item Name is not displayed or not matched on basic details tab");

            delayWithGivenTime(1000);
            webadmin_productspage.select_Web_Categories();

            delayWithGivenTime(2000);
            webadmin_productspage.click_Back_Button_On_Top();

            delayWithGivenTime(2000);
            webadmin_productspage.click_Filter_Button_On_Header();
            delayWithGivenTime(1000);
            webadmin_productspage.select_Product_Status_On_Filters_Popup("InActive");
            delayWithGivenTime(1000);
            webadmin_productspage.click_Apply_Button_On_Filters_Popup();

            delayWithGivenTime(1000);
            webadmin_productspage.search_And_Select_Product(item_name);
            delayWithGivenTime(1000);
            softassert.assertEquals(webadmin_productspage.get_Displayed_Product_Name_On_Product_Details_TableGrid(), item_name, "Test Step - 9: Product name is not displayed or not matched on product details table grid");
            delayWithGivenTime(1000);
            webadmin_productspage.click_Remove_Filter_Button_On_Header();
            delayWithGivenTime(1000);
            webadmin_productspage.click_Filter_Button_On_Header();
            delayWithGivenTime(1000);
            webadmin_productspage.select_Product_Status_On_Filters_Popup("Active");
            delayWithGivenTime(1000);
            webadmin_productspage.click_Apply_Button_On_Filters_Popup();

            delayWithGivenTime(1000);
            webadmin_productspage.search_And_Select_Product(item_code);
            delayWithGivenTime(2000);
            softassert.assertTrue(webadmin_productspage.is_InActive_Product_Displayed_On_Product_Details_TableGrid(item_code), "Test Step - 10: In-active product is displayed on Active product details table grid");

            delayWithGivenTime(2000);
            dashboard.Click_ProfileIcon_On_HanaDashBoardPage_And_Clicks_User_Website();
            switchToWindowbyIndex(1);

            ecommerce_homePage = new Ecommerce_HomePage();
            delayWithGivenTime(3000);
            softassert.assertEquals(ecommerce_homePage.get_HomePage(), prop.getProperty("ecommerce_checkoutpage_url"), "Test Step - 7: Ecommerce home page url is not matching with expected url");

            // Test Step - 8
            ecommerce_homePage.Search_Product_In_GlobalSearch(item_code);
            delayWithGivenTime(3000);
            searchResultsPage = new Ecommerce_SearchResults_Page();
            softassert.assertTrue(searchResultsPage.Verify_Search_Result_Page_IsDisplayed(), "Test Step - 8: Search result page is not displayed");
            softassert.assertTrue(searchResultsPage.isNoProductFoundHeaderDisplayed(), "Test Step - 8: No product found header is not displayed on search result page");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }


    }
}