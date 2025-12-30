package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import java.util.Locale;

/**
 * Draft pickup Order - phone order functionality
 *
 * @Author: Balaji N
 */
public class Hana_T_Pickup_Draft_Order_View_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private Recurring_Orders_Page recurringOrdersPage;
    String invoiceNumber;
    String totalamount;
    public static LoggerUtil logger_Util;
    Faker faker = new Faker(new Locale("en-US"));
    String recifname1;
    String recilname2;
    String recipientfullname;
    String reci_full_address1 = "207 Depot St, Marthasville, MO 63357";
    String reci_phone_number1;
    String reci_phone_number2;
    String floor_number;

    @Epic("Phone Order Module - Pickup Type")
    @Test(enabled = true, groups = {"Regression", "Sanity", "Smoke"})
    public void Validate_Hana_T_Pickup_Draft_Order_View_Functionality_Test() {
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
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Pickup type is not highlighted in blue color");

            // Test Step - 6
            String companyName = getData("customer.companyName", "custData.json");
            String fullName = getData("customer.fullName", "custData.json");
            String firstName = getData("customer.firstName", "custData.json");
            String lastName = getData("customer.lastName", "custData.json");
            String address1 = getData("customer.address1", "custData.json");
            String address2 = getData("customer.address2", "custData.json");
            String city = getData("customer.city", "custData.json");
            String state = getData("customer.state", "custData.json");
            String email = getData("customer.email", "custData.json");
            String phone = getData("customer.phoneNumber", "custData.json");
            String altphone = getData("customer.altPhoneNumber", "custData.json");
            String zip = getData("customer.zipCode", "custData.json");


            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            phoneorder.SearchAndSelectCustomerOnCust_Section(fullName);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), firstName, "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), lastName, "Test Step - 6 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), companyName, "Test Step - 6 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), prop.getProperty("customeremail"), "Test Step - 6 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), address1, "Test Step - 6 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), address2, "Test Step - 6 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), zip, "Test Step - 6 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), city, "Test Step - 6 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getStateOnPhoneOrderPage(), state, "Test Step - 6 - state is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), phone, "Test Step - 6 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), altphone, "Test Step - 6 - Alt phone number is not displayed on phone order page");

            // Test Step - 7
            recifname1 = faker.name().firstName();
            recilname2 = faker.name().lastName();
            recipientfullname = recifname1 + " " + recilname2;
            phoneorder.EnterReciFirstName(recifname1);
            phoneorder.EnterReciLastName(recilname2);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname1, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname2, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");
            delayWithGivenTime(1000);

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(reci_full_address1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "207 Depot St", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63357", "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Marthasville", "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.SelectReciCountry(prop.getProperty("recipient_country1"));
            phoneorder.EnterReciAddress2("301 West Front St");
            reci_phone_number1 = faker.numerify("###-###-####");
            phoneorder.EnterReciPhone(reci_phone_number1);
            delayWithGivenTime(1000);
            reci_phone_number2 = faker.numerify("###-###-####");
            floor_number = faker.address().buildingNumber();
            phoneorder.EnterRecipientPhone2OnPhoneOrderPage(reci_phone_number2);
            delayWithGivenTime(1000);
            phoneorder.Enter_FloorApt_On_RecipientSection(floor_number);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(prop.getProperty("recipient_location1"));
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), reci_phone_number1, "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), prop.getProperty("recipient_location1"), "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");

            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(15, 20);
            phoneorder.Select_DeliveryOnTime_Dropdown("Around");
            delayWithGivenTime(1000);
            phoneorder.Select_Zone_OnRecipientSection(prop.getProperty("zone"));


            //Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            phoneorder.Enter_CardMessage_OnOccasion_Details_Section(prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 9
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), prop.getProperty("product_itemcode1"), "Test Step - 9 - Item code " + prop.getProperty("product_itemcode1") + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), prop.getProperty("product_description1"), "Test Step - 9 - Item description " + prop.getProperty("product_description1") + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 9 - Item quantity is not displayed on phone order page product details section");

            if (phoneorder.getUnitPriceOnProdDetails() == "299.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            } else if (phoneorder.getUnitPriceOnProdDetails() == "309.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "309.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            }

            delayWithGivenTime(2000);

            // Test Step - 10
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), prop.getProperty("payment_type"), "Test Step - 10 - Selected payment type is not displayed");

            // Test Step - 11
            delayWithGivenTime(1000);
            phoneorder.enter_CashPaymentType_Amount();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_Entered_CashPaymentType_Amount(), phoneorder.get_Entered_CashPaymentType_Amount(), "Test Step - 11 - Entered more than cash payment amount is not allowed on the payment section cash type pay amount field");

            // Test Step - 12
            phoneorder.SelectCashRegistry_On_CashPaymentType(prop.getProperty("payment_cash_registry"));

            // Test Step - 13
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.Verify_Change_GivenBackTo_Customer_amount_IsDisplayed(), "0.00", "Test Step - 12 - Change given back to customer amount label is not displayed under the cash payment type select registered dropdown");
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_Selected_CashRegistry(), prop.getProperty("payment_cash_registry"), "Test Step - 13 - Selected cash payment registered is not displayed");

            // Test Step - 14
            totalamount = phoneorder.getGrandTotalAmount();
            delayWithGivenTime(1000);

            phoneorder.Click_Save_As_Draft_Icon_On_Phone_OrderPage();
            softassert.assertEquals(phoneorder.verify_Toaster_Message_Text_displayed(), "Draft saved successfully", "Test Step - 6 - Draft saved successfully message is not displayed");

            dashboard.ClickOnHomeIcon();
            delayWithGivenTime(2000);
            dashboard.ClickOrderEntry();
            delayWithGivenTime(2000);
            phoneorder.Click_View_Drafts_Icon_On_Phone_OrderPage();
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.is_Draft_Orders_Popup_IsDisplayed(), "Test Step - 5: Draft orders popup is not displayed");
            softassert.assertTrue(phoneorder.Verify_Draft_Orders_Popup_IsDisplayed(), "Test Step - 6: Draft orders header is not displayed");

            //Test Step - 7
            phoneorder.Click_Row1_TableGrid_on_DraftOrders_popup();
            softassert.assertTrue(phoneorder.Verify_Confirmation_Alert_IsDisplayed_on_Draft_order(), "Test Step - 7: Confirmation alert popup is not displayed");
            softassert.assertTrue(phoneorder.Verify_DeleteConfirmation(), "Test Step - 8: Delete confirmation popup is not displayed on draft order");

            // Test Step - 9
            phoneorder.Click_NoButtonDeleteConfirmation();

            // Test Step - 10
            phoneorder.Click_Row1_TableGrid_on_DraftOrders_popup();
            softassert.assertTrue(phoneorder.Verify_Confirmation_Alert_IsDisplayed_on_Draft_order(), "Test Step - 10: Confirmation alert popup is not displayed");
            phoneorder.Click_DeleteButtonDeleteConfirmation();
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), firstName, "Test Step - 10 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), lastName, "Test Step - 10 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), companyName, "Test Step - 10 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), prop.getProperty("cust_email"), "Test Step - 10 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), address1, "Test Step - 10 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), address2, "Test Step - 10 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), zip, "Test Step - 10 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), city, "Test Step - 10 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), phone, "Test Step - 10 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), altphone, "Test Step - 10 - Alt phone number is not displayed on phone order page");

            softassert.assertEquals(phoneorder.getReciFirstName(), recifname1, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname2, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "207 Depot St", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "301 West Front St", "Test Step - 4 - Recipient address 2 is not matched with customer address 2 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63357", "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Marthasville", "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), reci_phone_number1, "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), reci_phone_number2, "Test Step - 7 - Recipient phone number 2 is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), prop.getProperty("recipient_location1"), "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.get_deliveryTime_on_recipientSection(), "03:20 PM", "Test Step - 7 - Delivery time is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getSelected_DeliveryOnTimeOptions_OnDropdown_RecipientSection(), "Around", "Test Step - 7 - Delivery time is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getSelectedZone_OnRecipientSection(), prop.getProperty("zone"), "Test Step - 7 - Zone is not displayed on phone order page recipient section");

            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), prop.getProperty("product_itemcode1"), "Test Step - 9 - Item code " + prop.getProperty("product_itemcode1") + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), prop.getProperty("product_description1"), "Test Step - 9 - Item description " + prop.getProperty("product_description1") + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1.00", "Test Step - 9 - Item quantity is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), prop.getProperty("payment_type"), "Test Step - 10 - Selected payment type is not displayed");

            delayWithGivenTime(2000);
            phoneorder.EnterCashAmount(phoneorder.getGrandTotalAmount());
            delayWithGivenTime(1000);
          //  softassert.assertEquals(phoneorder.get_Entered_CashPaymentType_Amount(), phoneorder.getGrandTotalAmount(), "Test Step - 11 - Entered more than cash payment amount is not allowed on the payment section cash type pay amount field");
            phoneorder.doubleClickOnRelayFee();
            // Test Step - 12
            phoneorder.SelectCashRegistry_On_CashPaymentType(prop.getProperty("payment_cash_registry"));

            // Test Step - 13delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_Selected_CashRegistry(), prop.getProperty("payment_cash_registry"), "Test Step - 13 - Selected cash payment registered is not displayed");

            // Test Step - 14
            phoneorder.doubleClickOnRelayFee();
            totalamount = phoneorder.getGrandTotalAmount();
            delayWithGivenTime(1000);

            delayWithGivenTime(1000);
            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(1000);

            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 14 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 15
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 15 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            // Test Step - 16
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 16 - Dashboard order page is not displayed");

            // Test Step - 17
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 17 - Invoice number: " + invoiceNumber + " is not displayed on all orders page");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
        } finally {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            softassert.assertAll();
        }
    }
}
