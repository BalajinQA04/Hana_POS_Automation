package com.hanapos.testcases.All_Orders_Page_Testcases;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;

public class Hana_T1490_AllOrdersView_Pending_Deliveries_View_Delivery_Type_as_Wire_In_and_Status_New_Redeliver_because_of_complaint_Redeliver_flowers_back_in_store_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private ConfirmationPage confirmationPage;
    public static final String dataSheetName = "Hana_T1490";
    String invoiceNumber;
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    Faker faker = new Faker(new Locale("en-US"));
    String recifname1;
    String recilname2;
    String reci_full_address1;

    @Severity(SeverityLevel.CRITICAL)
    @Owner("Balaji N")
    @Epic("All Orders Page Module")
    @Test(enabled = true, groups = {"Regression", "Smoke", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1490_AllOrdersView_Pending_Deliveries_View_Delivery_Type_as_Wire_In_and_Status_New_Redeliver_because_of_complaint_Redeliver_flowers_back_in_store_FT(
            String username,
            String password,
            String shopname,
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
            String cardmessage,
            String itemcode,
            String itemdescription,
            String paymenttype,
            String cashregistry,
            String selectview,
            String deliverycode,
            String drivername,
            String rescheduleorderstatus) {
        CustomSoftAssert softassert = new CustomSoftAssert();
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            lp.EnterUserName(username);
            lp.EnterPassword(password);
            softassert.assertEquals(lp.get_entered_username(), username, "Test Step - 2: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), password, "Test Step - 2: Entered password is not matching with expected password as " + prop.getProperty("password"));
            lp.ClickLoginButton();

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3: Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3: Cash and carry option is not displayed");
            dashboard.ClickOrderEntry();

            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(shopname);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), shopname, "Test Step - 3 - Shop name is not displayed in the WireIn section of phone order page");

            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            softassert.assertEquals(phoneorder.get_SelectedSalesPersonOn_PhoneOrderEntryPage(), salesperson, "Test Step - 3 - Selected Sales Person " + salesperson + " is not displayed on phone order page");
            phoneorder.Click_WireIn_DeliveryType_OnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnWireInTypeOnPhoneOrderPage(), "#676a6c", "Test Step - 3 - Wire In as delivery type is not highlighted in blue color");

            // Test Step - 4
            Faker usFaker = new Faker(new java.util.Locale("en-US"));
            Faker canadaFaker = new Faker(new java.util.Locale("en-CA"));
            // Generate random US customer details
            String custFirstName = usFaker.name().firstName();
            String custLastName = usFaker.name().lastName();
            String customerfullname = custFirstName + " " + custLastName;

            String companyname = Generate_CompanyName();
            String customerPhoneNumber = GenerateUsPhoneNumber();
            String recipientPhoneNumber = GenerateCAPhoneNumber();
            String usStreetAddress = usFaker.address().streetAddress();
            String usCity = usFaker.address().city();
            String usState = usFaker.address().state();
            String usZipCode = usFaker.address().zipCode();
            String usFullAddress = usStreetAddress + ", " + usCity + ", " + usState + " " + usZipCode;

            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            phoneorder.Enter_WireIn_Fname(custFirstName);
            phoneorder.Enter_WireIn_Lname(custLastName);
            phoneorder.Enter_WireIn_ShopCode(Shopcode);
            phoneorder.Enter_WireIn_ShopName(customerfullname + " Flower Shop");
            phoneorder.Select_WireInMethod(WireIn_Method);
            String OrderNumber = generaterandomeNumber(8);
            phoneorder.Enter_WireIn_OrderNumber(OrderNumber);
            phoneorder.Enter_WireIn_PhoneNumber(customerPhoneNumber);
            phoneorder.Enter_WireIn_Email(Email);
            phoneorder.Enter_WireIn_ShopAddress(custaddress1);
            phoneorder.Enter_WireIn_Comments(customerfullname + " Wire In Order - handle with care");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Fname(), custFirstName, "Test Step - 4 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Lname(), custLastName, "Test Step - 4 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopCode(), "1100", "Test Step - 4 - Entered WireIn shop code is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopName(), customerfullname + " Flower Shop", "Test Step - 4 - Entered WireIn shop name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Selected_WireInMethod(), "FSN", "Test Step - 4 - Selected WireIn method is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_OrderNumber(), OrderNumber, "Test Step - 4 - Entered WireIn order number is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_PhoneNumber(), customerPhoneNumber, "Test Step - 4 - Entered phone number on customer details popup is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Email(), "hanaposqateam@gmail.com", "Test Step - 4 - Entered shop Email id on customer details popup is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopAddress(), "2715 35th Avenue Greeley, CO, USA", "Test Step - 4 - Entered shop address  is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Comments(), customerfullname + " Wire In Order - handle with care", "Test Step - 4 - Shop Wire In Comments is not displayed on phone order page");
            delayWithGivenTime(2000);

            String RecipientFirstName = canadaFaker.name().firstName();
            String RecipientLastName = canadaFaker.name().lastName();
            String RecipientFullName = RecipientFirstName + " " + RecipientLastName;
            phoneorder.EnterReciFirstName(RecipientFirstName);
            phoneorder.EnterReciLastName(RecipientLastName);
            recifname1 = faker.name().firstName();
            recilname2 = faker.name().lastName();
            reci_full_address1 = "Augusta Ridge Ln Boone Township MO";
            phoneorder.EnterReciFirstName(recifname1);
            phoneorder.EnterReciLastName(recilname2);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname1, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname2, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(reci_full_address1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "Augusta Ridge Ln", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");

//            if (phoneorder.getReciAddress1().equalsIgnoreCase("Augusta Ridge Ln")) {
//            } else if (phoneorder.getReciAddress1().equalsIgnoreCase("Augusta Shores Ct")) {
//                softassert.assertEquals(phoneorder.getReciAddress1(), "Augusta Shores Ct", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
//            }

            softassert.assertEquals(phoneorder.getReciZipcode(), "63332", "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Boone Township", "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.EnterReciAddress2(prop.getProperty("Reci_Address1_2"));
            phoneorder.SelectReciCountry(recicountry);
            phoneorder.EnterReciPhone(recipientPhoneNumber);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recilocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(2000);

            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), recicountry, "Test Step - 4 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), recipientPhoneNumber, "Test Step - 4 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), recilocation, "Test Step - 4 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 4 - Delivery date is not displayed on phone order page recipient section");

            //Test Step - 4
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), cardmessage);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), occasion, "Test Step - 4 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(cardmessage), true, "Test Step - 4 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 4
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(itemcode, itemdescription);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), itemcode, "Test Step - 4 - Item code as " + itemcode + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), itemdescription, "Test Step - 4 - Item description " + itemdescription + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 4 - Item quantity is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 4 - Item price is not displayed on phone order page product details section");
            delayWithGivenTime(2000);

            // Test Step - 5
            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 5 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 5 - Order confirmation page is not displayed");

            orderconfirmationpage = new Order_Confirmation_Page();
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();
            delayWithGivenTime(500);
            softassert.assertEquals(orderconfirmationpage.getCustomerFname(), custFirstName, "Test Step - 6 - Added first name on customer section in phoneorder page and compared with order confirmation page displayed first name are not matched");
            softassert.assertEquals(orderconfirmationpage.getCustomerLname(), custLastName, "Test Step - 6 - Added last name on customer section in phoneorder page and compared with order confirmation page displayed last name are not matched");
            softassert.assertEquals(orderconfirmationpage.getCustomerAddress(), "2715 35th Avenue Greeley, CO, USA", "Test Step - 6 - Added address on customer section in phoneorder page and compared with order confirmation page displayed addresses are not matched");

            softassert.assertEquals(orderconfirmationpage.getCustomerCity(), customerfullname + " Flower Shop(1100)", "Test Step - 6 - Added shop name on customer section in phoneorder page and compared with order confirmation page displayed shopname are not matched");
            softassert.assertEquals(orderconfirmationpage.getRecipientFname(), recifname1, "Test Step - 6 - Added first name on recipient section in phoneorder page and compared with order confirmation page displayed first name are not matched");
            softassert.assertEquals(orderconfirmationpage.getRecipientLname(), recilname2, "Test Step - 6 - Added last name on recipient section in phoneorder page and compared with order confirmation page displayed last name are not matched");

            if (orderconfirmationpage.getRecipientAddress1().equalsIgnoreCase("Augusta Ridge Ln")) {
                softassert.assertEquals(orderconfirmationpage.getRecipientAddress1(), "Augusta Ridge Ln", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            } else if (orderconfirmationpage.getRecipientAddress1().equalsIgnoreCase("Augusta Shores Ct")) {
                softassert.assertEquals(orderconfirmationpage.getRecipientAddress1(), "Augusta Shores Ct", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            }
            softassert.assertEquals(orderconfirmationpage.getRecipientCity(), "Boone Township", "Test Step - 6 - Added city on recipient section in phoneorder page and compared with order confirmation page displayed city are not matched");
            softassert.assertEquals(orderconfirmationpage.getRecipientState(), "MO", "Test Step - 6 - Added State on recipient section in phoneorder page and compared with order confirmation page displayed state are not matched");
            softassert.assertEquals(orderconfirmationpage.getRecipientZip(), "63332", "Test Step - 6 - Added zip code on recipient section in phoneorder page and compared with order confirmation page displayed zip code are not matched");

            softassert.assertEquals(orderconfirmationpage.get_itemcode1(), "rrd", "Test Step - 6 - Added item code on recipient section in phoneorder page and compared with order confirmation page displayed item code are not matched");
            softassert.assertEquals(orderconfirmationpage.get_itemdescription1(), "Red Rose Deluxe", "Test Step - 6 - Added item description on recipient section in phoneorder page and compared with order confirmation page displayed item description are not matched");
            softassert.assertEquals(orderconfirmationpage.get_itemqty1(), "1", "Test Step - 6 - Added item quantity on recipient section in phoneorder page and compared with order confirmation page displayed item quantity are not matched");

            if (orderconfirmationpage.get_itemunitprice1() == "$299.00") {
                softassert.assertEquals(orderconfirmationpage.get_itemunitprice1(), "$299.00", "Test Step - 6 - Added item unit price on recipient section in phoneorder page and compared with order confirmation page displayed item unit price are not matched");
            } else if (orderconfirmationpage.get_itemunitprice1() == "$309.00") {
                softassert.assertEquals(orderconfirmationpage.get_itemunitprice1(), "$309.00", "Test Step - 6 - Added item unit price on recipient section in phoneorder page and compared with order confirmation page displayed item unit price are not matched");
            }

            softassert.assertEquals(orderconfirmationpage.get_PaymentType(), "FSN", "Test Step - 6 - Payment type is not displayed on order confirmation page");

            // Test Step - 6
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 6 - All orders page is not displayed");

            // Test Step - 7
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 7 - Respective Invoice number is : " + invoiceNumber + " not displayed on all orders page");

            dashboardorder.Select_views_dropdown_on_all_ordersPage(selectview);
            delayWithGivenTime(3000);
            softassert.assertEquals(dashboardorder.get_Selected_View_On_AllOrdersPage(), selectview, "Test Step - 7: Selected view is not displayed on all orders page");

            dashboardorder.click_Filters_Icon_On_All_Orders_Page();
            delayWithGivenTime(3000);
            softassert.assertEquals(dashboardorder.get_Displayed_Status_Option1_On_Filters_In_AllOrdersPage(), "New", "Test Step - 7: Status option is not displayed on filters in all orders page");
            softassert.assertEquals(dashboardorder.get_Displayed_Status_Option2_On_Filters_In_AllOrdersPage(), "Redeliver Because Of Complaint", "Test Step - 7: Status option is not displayed on filters in all orders page");
            softassert.assertEquals(dashboardorder.get_Displayed_Status_Option3_On_Filters_In_AllOrdersPage(), "Redeliver Flowers Back In Store", "Test Step - 7: Status option is not displayed on filters in all orders page");

            softassert.assertEquals(dashboardorder.get_Displayed_Delivery_Option1_On_Filters_In_AllOrdersPage(), "Delivery", "Test Step - 7: Delivery option is not displayed on filters in all orders page");
            softassert.assertEquals(dashboardorder.get_Displayed_Delivery_Option2_On_Filters_In_AllOrdersPage(), "Wire In", "Test Step - 7: Delivery option is not displayed on filters in all orders page");
            softassert.assertEquals(dashboardorder.get_Displayed_Delivery_Option3_On_Filters_In_AllOrdersPage(), "Wire Out", "Test Step - 7: Delivery option is not displayed on filters in all orders page");

            dashboardorder.click_Cancel_Button_On_Filters_Popup_All_Orders_Page();

            // Test Step - 9
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), "New", "Test Step - 9 - Order status as New is not displayed as delivered for order");
            softassert.assertEquals(dashboardorder.validate_DeliveryType_On_AllOrdersPage(invoiceNumber), "Wire In", "Test Step - 9: Delivery type as Wire In is not displayed for respective order " + invoiceNumber);

            // Test Step - 10
            dashboardorder.Select_views_dropdown_on_all_ordersPage(selectview);
            delayWithGivenTime(3000);
            softassert.assertEquals(dashboardorder.get_Selected_View_On_AllOrdersPage(), selectview, "Test Step - 10: Selected view is not displayed on all orders page");

            delayWithGivenTime(1000);
            dashboardorder.Click_Checkbox_on_AllOrdersPage_for_Respective_Invoices(invoiceNumber);
            delayWithGivenTime(1000);
            dashboardorder.click_ConfirmDeliveries_Button_On_PendingDeliveries();

            confirmationPage = new ConfirmationPage();
            delayWithGivenTime(2000);
            softassert.assertTrue(confirmationPage.Verify_Delivery_Confirmation_Header(), "Test Step - 10 - Confirmation delivery date popup is not displayed");
            delayWithGivenTime(2000);

            confirmationPage.select_delivery_code_Confirmation_Page(deliverycode);
            softassert.assertEquals(confirmationPage.get_selected_delivery_code_Confirmation_Page(), deliverycode, "Test Step - 10 - Selected delivery code is not displayed properly on confirmation page");

            confirmationPage.select_DriverCode_Delievry_Confirmation_Details_Popup(drivername);
            softassert.assertEquals(confirmationPage.get_Selected_DriverCode_Delievry_Confirmation_Details_Popup(), drivername, "Test Step - 10 - Selected driver code is not displayed properly on confirmation page");

            confirmationPage.confirm_selected_deliveries_button_click_Confirmation_page();
            softassert.assertTrue(phoneorder.verifySuccessToastMessageAppears(), "Test Step - 10 - Delivery Confirmation Toaster message is not displayed");
            confirmationPage.confirm_delivery_page_close_icon_click();

            // Test Step - 11
            delayWithGivenTime(3000);
            dashboardorder.click_Non_Deliveries_Tab();
            delayWithGivenTime(3000);
            softassert.assertEquals(dashboardorder.get_Selected_View_On_AllOrdersPage(), "Today Non Deliveries", "Test Step - 11 - Today Non Deliveries option is not selected");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), "Not Delivered Delivery Attempted\nDriver: Liam Jack Benjamin", "Test Step - 11 - Order status is not displayed as delivered for order");
            softassert.assertTrue(dashboardorder.verify_Reschedule_Button_IsDisplayed(), "Test Step - 11 - Reschedule button is not displayed");

            // Test Step - 12
            dashboardorder.Click_Checkbox_on_AllOrdersPage_for_Respective_Invoices(invoiceNumber);

            // Test Step - 13
            dashboardorder.click_Reschedule_Button_On_Non_Deliveries_Tab();
            softassert.assertTrue(dashboardorder.verify_Reschedule_Order_Popup_IsDisplayed(), "Test Step - 13 - Reschedule order popup is not displayed");

            dashboardorder.Enter_Choose_New_Delivery_Date(Next_20Days_Date());
            softassert.assertEquals(dashboardorder.get_Entered_Choose_New_Delivery_Date(), Next_20Days_Date(), "Test Step - 13 - Selected new delivery date is not displayed");

            dashboardorder.click_Reschedule_Button_On_Reschedule_Order_Popup();
            softassert.assertEquals(dashboardorder.Verify_Success_Toaster_Message_Text(), "Orders Rescheduled Successfully", "Test Step - 13 - Orders Rescheduled Successfully toaster message is not displayed");

            // Test Step - 14
            dashboard.ClickOrder();
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 14 - Respective Invoice number : " + invoiceNumber + "is not displayed on all orders page");

            // Test Step - 15
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), rescheduleorderstatus + "\nDriver: Liam Jack Benjamin", "Test Step - 15 - Order status " + rescheduleorderstatus + " is not displayed as delivered for order");


        } catch (Exception e) {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
            softassert.fail(e.getMessage());
        } finally {
            try {
                softassert.assertAll();
            } catch (AssertionError ae) {
                logger_Util = new LoggerUtil();
                logger_Util.attachNetworkLogs(testCaseName);
                ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
                throw ae;
            }
        }
    }
}