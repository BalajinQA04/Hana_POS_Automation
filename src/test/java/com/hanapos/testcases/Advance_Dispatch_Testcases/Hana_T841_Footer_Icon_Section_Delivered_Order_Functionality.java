package com.hanapos.testcases.Advance_Dispatch_Testcases;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T841_Footer_Icon_Section_Delivered_Order_Functionality extends TestBaseClass {

    public DashboardOrderPage dashboardOrderPage;
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private DispatchPage dispatch;
    private Advance_DispatchPage advanceDispatchPage;
    private HanaDashBoardPage hanaDashBoardPage;
    private ConfirmationPage confirmationPage;
    String invoiceNumber;
    String automationshop_deliverydate;

    public static final String dataSheetName = "Hana_T302";


    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Advance Dispatch Module")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T841_Footer_Icon_Section_Delivered_Order_Functionality(
            String salesperson, String customername, String recifname, String recilname, String reciaddress1, String reciaddress2, String recizip, String recicity,
            String recicountry, String reciphone, String recilocation, String occasion, String searchandselectitemcode, String paymenttype) {

        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 - Login page is not displayed");
            logger.info("User on the hana pos login page");


            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 - Hana dashboard page is not displayed");
            logger.info("User navigated to hana dashboard page");

            // ======================= * Beginning of the pre requiste * ========================
        /*    dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));
*/

            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3 - Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3  - Cash and carry option is not displayed");


            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");


            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickDeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnDeliveryTypeOnPhoneOrderPage(), "#676a6c", "Pickup type is not highlighted in blue color");


            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            phoneorder.SearchAndSelectCustomerOnCust_Section(customername);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), prop.getProperty("cust_firstName"), "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), prop.getProperty("cust_lastName"), "Test Step - 6 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), prop.getProperty("cust_companyName"), "Test Step - 6 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), prop.getProperty("cust_email"), "Test Step - 6 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), prop.getProperty("cust_address1"), "Test Step - 6 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 6 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), prop.getProperty("cust_zipcode"), "Test Step - 6 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), prop.getProperty("cust_city"), "Test Step - 6 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_phoneNumber"), "Test Step - 6 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_Alt_phoneNumber"), "Test Step - 6 - Alt phone number is not displayed on phone order page");


            phoneorder.EnterReciFirstName(recifname);
            phoneorder.EnterReciLastName(recilname);
            phoneorder.EnterReciAddress1(prop.getProperty("Full_Reci_Address1_1"));
            phoneorder.EnterReciAddress2(prop.getProperty("Full_Reci_Address1_2"));
            phoneorder.EnterReciZipcode(recizip);
            delayWithGivenTime(1000);

            phoneorder.SelectReciCountry(recicountry);
            phoneorder.EnterReciPhone(reciphone);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recilocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), "Abish", "Test Step - 7 - Entered first name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "David", "Test Step - 7 - Entered last name is not displayed on phone order page recipient section");

            softassert.assertEquals(phoneorder.getReciAddress1(), prop.getProperty("Full_Reci_Address1_1"), "Test Step - 7 - Entered address 1 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), prop.getProperty("Full_Reci_Address1_2"), "Test Step - 7 - Entered address 2 is not displayed on phone order page recipient section");

            softassert.assertEquals(phoneorder.getReciZipcode(), "92103", "Test Step - 7 - Entered zipcode is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "San Diego", "Test Step - 7 - Entered city is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), "956-655-0756", "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), "Church", "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");
            automationshop_deliverydate = phoneorder.getDeliveryDateOnReciSection();

            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase("Happy Birthday! Hope you have an amazing day!"), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(searchandselectitemcode, "rrd-Red Rose Deluxe");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), "rrd", "Test Step - 9 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), "Red Rose Deluxe", "Test Step - 9 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 9 - Item quantity is not displayed on phone order page product details section");

            if (phoneorder.getUnitPriceOnProdDetails() == "299.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            } else if (phoneorder.getUnitPriceOnProdDetails() == "309.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "309.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            }

            delayWithGivenTime(2000);
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymenttype);
            delayWithGivenTime(1000);
            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(1000);
            getDriver().switchTo().activeElement();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 10 - Confirmation popup is not displayed on phone order page");
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);

            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 13 - Order confirmation page is not displayed");
            delayWithGivenTime(500);

            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 14 - Dashboard order page is not displayed");

            dashboardorder.EnterGlobalSearch(invoiceNumber); //dashboardorder.getInvoiceNumber_On_PhoneOrder_DeliveryInvoiceInHousePayment()
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 10 - Respective Invoice number : " + invoiceNumber + " is not displayed on all orders page");
            delayWithGivenTime(2000);

            // ======================= * End of the pre requiste * ========================
            // Test Step - 2

            delayWithGivenTime(3000);
            softassert.assertTrue(dashboardorder.Verify_Status_On_OrderPage(), "Test Step - 2 - Order page status column is not displayed");
            softassert.assertEquals(dashboardorder.get_status_on_OrderPage(), "New", "Test Step - 2 - Order status is not matched as expected");

            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.Verify_AdvanceDispatch_OptionIsDisplayed(), "Test Step - 2 - Advance dispatch option is not displayed");
            softassert.assertTrue(dashboard.Verify_QuickDispatch_OptionIsDisplayed(), "Test Step - 2 - Quick dispatch option is not displayed");

            // Test Step 3
            advanceDispatchPage = new Advance_DispatchPage();
            dispatch = new DispatchPage();

            dashboard.Hover_Dispatch_And_Click_QuickDispatch();
            softassert.assertTrue(dispatch.Verify_DispatchPopup_IsDisplayed(), "Test Step - 3 - Dispatch popup is not displayed");
            delayWithGivenTime(2000);

            // Test Step 4
            dispatch.Select_Delivery_Date_on_Quick_Dispatch_Page(automationshop_deliverydate);
            delayWithGivenTime(1000);
            dispatch.Enter_InvoiceNumber_on_ScanOrderTextbox(invoiceNumber);
            delayWithGivenTime(2000);
            softassert.assertEquals(dispatch.get_InvoiceNumber_On_TripSection(), dashboardorder.getInvoiceNumber_On_PhoneOrder_DeliveryInvoiceInHousePayment(), "Test Step - 4 - Entered Invoice number is not matched on trip section"); // + " " + prop.getProperty("cust_city") + " " + prop.getProperty("cust_state") + " " + prop.getProperty("cust_zipcode")
            softassert.assertEquals(dispatch.get_NameAndAddress_On_TripSection(), prop.getProperty("custfullname") + " " + prop.getProperty("Full_Reci_Address1_1") + " " + prop.getProperty("Full_Reci_Address1_2"), "Test Step - 4 - Displayed Name and address are not matched");
            softassert.assertEquals(dispatch.get_City_On_TripSection(), "San Diego", "Test Step - 4 - Displayed city is not matched");
            softassert.assertEquals(dispatch.get_State_On_TripSection(), "CA", "Test Step - 4 - Displayed state is not matched");
            softassert.assertEquals(dispatch.get_Zipcode_On_TripSection(), "92103", "Test Step - 4 - Displayed zipcode is not matched");

            delayWithGivenTime(2000);
            dispatch.Select_Driver("LJB Liam Jack Benjamin");
            dispatch.Click_DispatchSave_Button();
            dispatch.Click_CloseIcon_dispatchPopup();

            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.Verify_Status_On_OrderPage(), "Test Step - 4 - Order page status column is not displayed");
            softassert.assertEquals(dashboardorder.get_status_on_OrderPage(), "Dispatched\nDriver: Liam Jack Benjamin", "Test Step - 4 - Order status is not matched as expected");

            // Confirmation Pop-up Functionality to deliver the Order
            hanaDashBoardPage = new HanaDashBoardPage();
            hanaDashBoardPage.MouseAndClick_Confirmation_sub_menu();
            confirmationPage = new ConfirmationPage();

            delayWithGivenTime(2000);
            softassert.assertTrue(confirmationPage.Verify_Delivery_Confirmation_Header(), "Test Step - 5 - Confirmation delivery date popup is not displayed");

            confirmationPage.Select_Date_On_DeliveryConfirmationDetails(CurrentDate());
            delayWithGivenTime(2000);
            confirmationPage.Select_ToDate_On_DeliveryConfirmationDetails(CurrentDate());
            delayWithGivenTime(2000);
            confirmationPage.click_Search_Button_On_Delivery_Confirmation_Details();
            delayWithGivenTime(2000);

            if (confirmationPage.Confirmation_Popup_Verification()) {
                confirmationPage.Confirming_the_confirmation_pop_up();
            }

            delayWithGivenTime(2000);
            confirmationPage.Enter_RecipientInvoice_ConfirmationPage(invoiceNumber);
            delayWithGivenTime(2000);
            softassert.assertEquals(confirmationPage.get_entered_RecipientInvoice_ConfirmationPage(), invoiceNumber, "Test Step - 6 - Recipient Invoice is not matched");

            delayWithGivenTime(2000);
            confirmationPage.select_delivery_code_Confirmation_Page("At The Door");

            confirmationPage.confirm_selected_deliveries_button_click_Confirmation_page();
            confirmationPage.confirm_delivery_page_close_icon_click();

            getDriver().navigate().refresh();
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.Verify_Status_On_OrderPage(), "Test Step - 6 - Order page status column is not displayed");
            softassert.assertEquals(dashboardorder.get_status_on_OrderPage(), "Delivered\nDriver: Liam Jack Benjamin", "Test Step - 6 - Order status is not matched as expected");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}
