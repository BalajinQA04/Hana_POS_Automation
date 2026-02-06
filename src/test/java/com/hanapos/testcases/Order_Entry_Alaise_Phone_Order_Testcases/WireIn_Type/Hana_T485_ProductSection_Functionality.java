package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireIn_Type;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T485_ProductSection_Functionality extends TestBaseClass {

    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;

    private String invoiceNumber;
    public static final String dataSheetName = "Hana_T455";

    // ================= DATA PROVIDER =================
    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    // ================= TEST CASE =================
    @Epic("Phone Order Module - Wire In Type")
    @Test(groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T485_ProductSection_Functionality_Test(
            String salesperson,
            String FirstName,
            String LastName,
            String Shopcode,
            String ShopName,
            String WireIn_Method,
            String Order_Number,
            String PhoneNumber,
            String Email,
            String custaddress1,
            String Comments,
            String custzip,
            String custphone,
            String recifname,
            String recilname,
            String reciaddress1,
            String reciaddress2,
            String recizip,
            String recicity,
            String recicountry,
            String reciphone,
            String recilocation,
            String occasion,
            String searchandselectitemcode,
            String paymenttype,
            String cashregistry) {

        CustomSoftAssert softassert = new CustomSoftAssert();
        logger.info("========== STARTING Hana_T485 Product Section Test ==========");

        try {

            loginToHanaPOS(softassert);
            navigateToPhoneOrderPage(softassert);
            fillWireInCustomerDetails(softassert);
            fillRecipientDetails(
                    softassert, recifname, recilname, reciaddress1,
                    reciaddress2, recizip, recicity, recicountry, reciphone, recilocation
            );
            verifyProductSectionAndOperations(softassert, occasion);
            placeOrderAndVerify(softassert);
            verifyProductDetailsInDashboard(softassert);

        } catch (Exception e) {
            logger.error("❌ Test execution failed", e);
            softassert.fail("Test failed due to exception: " + e.getMessage());
            throw e;
        } finally {
            softassert.assertAll();
            logger.info("========== END OF Hana_T485 Product Section Test ==========");
        }
    }

    // ================= STEP METHODS =================

    private void loginToHanaPOS(CustomSoftAssert softassert) {
        logger.info("STEP 1: Login to Hana POS");

        lp = new LoginPage();
        softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page not displayed");

        lp.EnterUserName(prop.getProperty("username"));
        lp.EnterPassword(prop.getProperty("password"));
        lp.ClickLoginButton();

        dashboard = new HanaDashBoardPage();
        softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Dashboard page not displayed");
        dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
    }

    private void navigateToPhoneOrderPage(CustomSoftAssert softassert) {
        logger.info("STEP 2: Navigate to Phone Order Entry");

        softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(),
                "Order Entry option not displayed");

        dashboard.ClickOrderEntry();

        phoneorder = new OrderEntry_Alais_PhoneOrderPage();
        softassert.assertTrue(phoneorder.Verify_ProductSectionLabel_Appears(),
                "Product section label not displayed on Phone Order page");
    }

    private void fillWireInCustomerDetails(CustomSoftAssert softassert) {
        logger.info("STEP 3: Fill WireIn Customer Details");

        phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
        softassert.assertEquals(
                phoneorder.get_selected_shopname_on_phoneorder_page(),
                prop.getProperty("shopname"),
                "Shop name mismatch"
        );

        phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
        phoneorder.Click_WireIn_DeliveryType_OnPhoneOrderPage();

        softassert.assertEquals(
                phoneorder.getHighlightedColorOnWireInTypeOnPhoneOrderPage(),
                "#676a6c",
                "Wire In delivery type highlight mismatch"
        );

        phoneorder.Enter_WireIn_Fname(prop.getProperty("wirein_cust_firstName"));
        phoneorder.Enter_WireIn_Lname(prop.getProperty("wirein_cust_lastName"));
        phoneorder.Enter_WireIn_ShopCode(prop.getProperty("wirein_shopcode"));
        phoneorder.Enter_WireIn_ShopName(prop.getProperty("wirein_shopname"));
        phoneorder.Select_WireInMethod(prop.getProperty("wirein_type"));
        phoneorder.Enter_WireIn_OrderNumber(prop.getProperty("wirein_ordernumber"));
        phoneorder.Enter_WireIn_PhoneNumber(prop.getProperty("wirein_phonenumber"));
        phoneorder.Enter_WireIn_Email(prop.getProperty("wirein_email"));
        phoneorder.Enter_WireIn_ShopAddress(prop.getProperty("wirein_shopaddress"));
        phoneorder.Enter_WireIn_Comments(prop.getProperty("wirein_comments"));
    }

    private void fillRecipientDetails(CustomSoftAssert softassert,
                                      String fname, String lname, String address1, String address2,
                                      String zip, String city, String country,
                                      String phone, String location) {

        logger.info("STEP 4: Fill Recipient Details");

        phoneorder.EnterReciFirstName(fname);
        phoneorder.EnterReciLastName(lname);
        phoneorder.EnterReciAddress1(address1);
        phoneorder.EnterReciAddress2(address2);
        phoneorder.EnterReciZipcode(zip);
        phoneorder.SelectReciCountry(country);
        phoneorder.EnterReciPhone(phone);
        phoneorder.SelectReciLocation(location);
        phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());

        softassert.assertEquals(phoneorder.getReciFirstName(), fname, "Recipient first name mismatch");
        softassert.assertEquals(phoneorder.getReciLastName(), lname, "Recipient last name mismatch");
        softassert.assertEquals(phoneorder.getReciAddress1(), address1, "Recipient address1 mismatch");
        softassert.assertEquals(phoneorder.getReciAddress2(), address2, "Recipient address2 mismatch");
        softassert.assertEquals(phoneorder.getReciZipcode(), zip, "Recipient zipcode mismatch");
        softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), country, "Recipient country mismatch");
        softassert.assertEquals(phoneorder.getReciPhone(), phone, "Recipient phone mismatch");
        softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), location, "Recipient location mismatch");
    }

    private void verifyProductSectionAndOperations(CustomSoftAssert softassert, String occasion) {
        logger.info("STEP 5: Verify Product Section Functionality");

        phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
        softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(),
                occasion, "Occasion mismatch");

        phoneorder.EnterViewShortCode(
                prop.getProperty("short_card_message"),
                prop.getProperty("card_message")
        );

        softassert.assertTrue(
                phoneorder.Verify_ProductSuggestions_Appears(prop.getProperty("product_itemcode1")),
                "Product auto-suggestions not displayed"
        );

        phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(
                prop.getProperty("product_itemcode1"),
                "rrd-Red Rose Deluxe"
        );

        phoneorder.Click_ProductRemoveIcon_Row1();
        softassert.assertTrue(
                phoneorder.Verify_ProductRow1Details_Removed(),
                "Added product row not removed"
        );

        phoneorder.SearchAndSelect_WithItemDescription(
                "Red Rose",
                prop.getProperty("product_description1")
        );

        phoneorder.Click_AddIcon_Row3();
        softassert.assertTrue(
                phoneorder.Verify_Row4_ProductDetailsItemcode_Appears(),
                "New product row not added"
        );

        phoneorder.EnterSpecialInstructions_ProductDetailsSection(
                prop.getProperty("special_instruction")
        );
        phoneorder.EnterDriverInstructions_ProductDetailsSection(
                prop.getProperty("driver_instruction")
        );
        phoneorder.EnterCustomerPrivateNotesInstructions_ProductDetailsSection(
                prop.getProperty("customer_private_notes")
        );

        phoneorder.Select_ProductTaxType(prop.getProperty("product_taxtype"));
        phoneorder.Enter_ProductTaxId(prop.getProperty("product_taxid"));
        phoneorder.Select_ProdSourceCode(prop.getProperty("product_sourcecode"));
        phoneorder.Select_ProdCustType(prop.getProperty("product_custtype"));
    }

    private void placeOrderAndVerify(CustomSoftAssert softassert) {
        logger.info("STEP 6: Place Order");

        phoneorder.ClickPlaceOrderButton();
        softassert.assertTrue(
                phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(),
                "Confirmation popup not displayed"
        );

        phoneorder.ClickSubmitButton_On_ConfirmationPopup();
        orderconfirmationpage = new Order_Confirmation_Page();

        softassert.assertTrue(
                orderconfirmationpage.VerifyOrderConfirmationPage(),
                "Order confirmation page not displayed"
        );

        invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();
        logger.info("Order placed successfully. Invoice: " + invoiceNumber);
    }

    private void verifyProductDetailsInDashboard(CustomSoftAssert softassert) {
        logger.info("STEP 7: Verify Product Details in Dashboard");

        dashboard.ClickOrder();
        dashboardorder = new DashboardOrderPage();

        softassert.assertEquals(
                dashboardorder.validateDashboardOrderPage(),
                prop.getProperty("livedashboardorderURL"),
                "Dashboard order page URL mismatch"
        );

        dashboardorder.EnterGlobalSearch(invoiceNumber);
        dashboardorder.Click_WireIn_OrderType_InvoiceNumber_On_OrderPage(
                "FSN Order", "Wire In", "FSN"
        );

        softassert.assertEquals(
                dashboardorder.get_Displayed_DeliveryType_OnInvPopup(),
                "Wire In FSN Order",
                "Delivery type mismatch on invoice popup"
        );
    }
}
