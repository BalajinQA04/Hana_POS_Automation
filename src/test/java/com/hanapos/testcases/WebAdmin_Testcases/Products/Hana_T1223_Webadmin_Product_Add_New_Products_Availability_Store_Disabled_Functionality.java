package com.hanapos.testcases.WebAdmin_Testcases.Products;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import java.util.Locale;

public class Hana_T1223_Webadmin_Product_Add_New_Products_Availability_Store_Disabled_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private DashboardOrderPage dashboardorder;
    private WebAdmin_Home_Page webadmin_homepage;
    private WebAdmin_Products_Page webadmin_productspage;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;

    Faker faker = new Faker(new Locale("en-US"));
    String recifname1;
    String recilname2;
    String reci_full_address1;

    String custname;
    String custid;
    String invoicenumber;
    String balanceAmount;
    String invoiceNumber;

    @Epic("Web Admin Module - Products")
    @Owner("Balaji N")
    @Test(enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T1223_Webadmin_Product_Add_New_Products_Availability_Store_Disabled_Functionality_Test() {
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
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            delayWithGivenTime(2000);

            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3 : Cash And Carry page is not displayed");
            cashandcarry.SelectShopName(prop.getProperty("shopname"));
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("shopname"), "Test Step - 4 : Shop name is not matched with selected shop name");

            //Test Step - 5
            delayWithGivenTime(2000);
            cashandcarry.SelectClerkName(prop.getProperty("clerkname"));
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("clerkname"), "Test Step - 5 : Clerk name is not matched with selected clerk name");

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 6 : Employee name is not matched with selected employee name");

            // Test Step - 8
            delayWithGivenTime(2000);
            softassert.assertFalse(cashandcarry.is_Autosuggestion_Dropdown_NotDisplayed_ItemCode(item_code), "Test Step - 8 : Autosuggestion options is displayed for item code - for in-active product");

            delayWithGivenTime(2000);
            dashboard.ClickOrderEntry();
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 2 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickDeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnDeliveryTypeOnPhoneOrderPage(), "#676a6c", "Test Step - 2: Delivery type as Delivery is not highlighted in blue color");

            // Test Step - 3
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("custfullname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), prop.getProperty("cust_firstName"), "Test Step - 3 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), prop.getProperty("cust_lastName"), "Test Step - 3 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), prop.getProperty("cust_companyName"), "Test Step - 3 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), prop.getProperty("cust_email"), "Test Step - 3 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), prop.getProperty("cust_address1"), "Test Step - 3 address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 3 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), prop.getProperty("cust_zipcode"), "Test Step - 3 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), prop.getProperty("cust_city"), "Test Step - 3 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_phoneNumber"), "Test Step - 3 phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_Alt_phoneNumber"), "Test Step - 3 - Alt phone number is not displayed on phone order page");

            delayWithGivenTime(2000);
            softassert.assertFalse(phoneorder.is_Autosuggestion_Dropdown_Not_Displayed_for_ItemCode(item_code), "Test Step - 4 : Autosuggestion options is displayed for item code which is in-active product on web admin");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }


    }
}