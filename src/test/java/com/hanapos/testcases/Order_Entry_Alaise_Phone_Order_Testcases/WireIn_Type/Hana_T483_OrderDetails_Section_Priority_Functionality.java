package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireIn_Type;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T483_OrderDetails_Section_Priority_Functionality extends TestBaseClass {

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
    public void Validate_Hana_T483_OrderDetails_Section_Priority_Functionality_Test(
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
        logger.info("========== STARTING Hana_T483 Priority WireIn Test ==========");

        try {

            loginToHanaPOS(softassert);
            navigateToPhoneOrderPage(softassert);
            fillWireInCustomerDetails(softassert);
            fillRecipientDetails(
                    softassert, recifname, recilname, reciaddress1,
                    reciaddress2, recizip, recicity, recicountry, reciphone, recilocation
            );
            verifyAndSelectPriority(softassert);
            addProductAndPlaceOrder(softassert, occasion);
            verifyPriorityOrderInDashboard(softassert);

        } catch (Exception e) {
            logger.error("❌ Test failed due to unexpected exception", e);
            softassert.fail("Test failed due to exception: " + e.getMessage());
            throw e;
        } finally {
            softassert.assertAll();
            logger.info("========== END OF Hana_T483 Priority WireIn Test ==========");
        }
    }

    // ================= STEP METHODS =================

    private void loginToHanaPOS(CustomSoftAssert softassert) {
        logger.info("STEP 1: Login to Hana POS");

        lp = new LoginPage();
        softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");

        lp.EnterUserName(prop.getProperty("username"));
        lp.EnterPassword(prop.getProperty("password"));
        lp.ClickLoginButton();

        dashboard = new HanaDashBoardPage();
        softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Dashboard page is not displayed");
        dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
    }

    private void navigateToPhoneOrderPage(CustomSoftAssert softassert) {
        logger.info("STEP 2: Navigate to Phone Order Entry");

        softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(),
                "Order Entry option is not displayed");

        dashboard.ClickOrderEntry();

        phoneorder = new OrderEntry_Alais_PhoneOrderPage();
        softassert.assertTrue(phoneorder.Verify_OrderDetailsSectionAppears(),
                "Order details section not displayed on Phone Order page");
    }

    private void fillWireInCustomerDetails(CustomSoftAssert softassert) {
        logger.info("STEP 3: Fill WireIn Customer Details");

        phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
        softassert.assertEquals(
                phoneorder.get_selected_shopname_on_phoneorder_page(),
                prop.getProperty("shopname"),
                "Shop name mismatch on Phone Order page"
        );

        phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
        phoneorder.Click_WireIn_DeliveryType_OnPhoneOrderPage();

        softassert.assertEquals(
                phoneorder.getHighlightedColorOnWireInTypeOnPhoneOrderPage(),
                "#676a6c",
                "Wire In delivery type highlight color mismatch"
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
        softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Delivery date mismatch");
    }

    private void verifyAndSelectPriority(CustomSoftAssert softassert) {
        logger.info("STEP 5: Verify Priority Checkbox");

        softassert.assertFalse(
                phoneorder.Verify_PriorityCheckBoxIsSelected(),
                "Priority checkbox is selected by default"
        );

        if (!phoneorder.Verify_PriorityCheckBoxIsSelected()) {
            phoneorder.Click_PriorityCheckBox_OnOrderDetails();
            logger.info("Priority checkbox selected");
        }
    }

    private void addProductAndPlaceOrder(CustomSoftAssert softassert, String occasion) {
        logger.info("STEP 6: Add Product and Place Order");

        phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
        softassert.assertEquals(
                phoneorder.getSelectedOccasionOnPhoneOrderPage(),
                occasion,
                "Occasion mismatch on Phone Order page"
        );

        phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(
                prop.getProperty("product_itemcode1"),
                "rrd-Red Rose Deluxe"
        );

        phoneorder.Select_ProductTaxType("Tax Exemption");
        phoneorder.ClickPlaceOrderButton();

        softassert.assertTrue(
                phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(),
                "Order confirmation popup not displayed"
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

    private void verifyPriorityOrderInDashboard(CustomSoftAssert softassert) {
        logger.info("STEP 7: Verify Priority Order in Dashboard");

        dashboard.ClickOrder();
        dashboardorder = new DashboardOrderPage();

        softassert.assertEquals(
                dashboardorder.validateDashboardOrderPage(),
                prop.getProperty("livedashboardorderURL"),
                "Dashboard order page URL mismatch"
        );

        dashboardorder.EnterGlobalSearch(invoiceNumber);

        softassert.assertTrue(
                dashboardorder.Verify_RedFlagIcon_IsAppears(),
                "Priority red flag icon not displayed for the order"
        );
    }
}
