package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireIn_Type;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T468_RecipientSection_SelectOptionMultiSelect_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    String invoiceNumber;
    private DashboardOrderPage dashboardorder;

    @Epic("Phone Order Module - Wire In Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T468_RecipientSection_SelectOptionMultiSelect_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T468_RecipientSection_SelectOptionMultiSelect_Functionality_Test ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 - Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 - Hana dashboard page is not displayed");
            logger.info("User navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3 - Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3 - Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 6 - Shop name is not displayed in the WireIn section of phone order page");

            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            softassert.assertEquals(phoneorder.get_SelectedSalesPersonOn_PhoneOrderEntryPage(), prop.getProperty("salesperson"), "Selected Sales Person " + prop.getProperty("salesperson") + " is not displayed on phone order page");
            phoneorder.Click_WireIn_DeliveryType_OnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnWireInTypeOnPhoneOrderPage(), "#676a6c", "Wire In as delivery type is not highlighted in blue color");

            // Test Step - 6
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

            softassert.assertEquals(phoneorder.get_Entered_WireIn_Fname(), "Abish", "Test Step - 7 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Lname(), "David", "Test Step - 7 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopCode(), "1100", "Test Step - 7 - Entered WireIn shop code is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopName(), "Automation Test Flower Shop", "Test Step - 7 - Entered WireIn shop name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Selected_WireInMethod(), "FSN", "Test Step - 7 - Selected WireIn method is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_OrderNumber(), "1122FSN33", "Test Step - 7 - Entered WireIn order number is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_PhoneNumber(), "996-955-0706", "Test Step - 7 - Entered phone number on customer details popup is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Email(), "hanaposqateam@gmail.com", "Test Step - 7 - Entered shop Email id on customer details popup is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopAddress(), "2715 35th Avenue Greeley, CO, USA", "Test Step - 7 - Entered shop address  is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Comments(), "Test Automation Wire In Order", "Test Step - 7 - Shop Wire In Comments is not displayed on phone order page");

            // Test Step - 7
            phoneorder.ClickReciNameOnPhoneOrderPage();
            softassert.assertEquals(phoneorder.getReciFirstName(), "", "Test Step - 8 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "", "Test Step - 8 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            if (phoneorder.getReciAddress2().equals("")) {
                softassert.assertEquals(phoneorder.getReciAddress1(), "", "Test Step - 8 - Displayed recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            } else if (phoneorder.getReciAddress2().equals(prop.getProperty("default_address1"))) {
                softassert.assertEquals(phoneorder.getReciAddress1(), prop.getProperty("default_address1"), "Test Step - 8 - Displayed recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            }

            if (phoneorder.getReciAddress2().equals("")) {
                softassert.assertEquals(phoneorder.getReciAddress2(), "", "Test Step - 8 - Displayed recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            } else if (phoneorder.getReciAddress2().equals(prop.getProperty("default_address2"))) {
                softassert.assertEquals(phoneorder.getReciAddress2(), prop.getProperty("default_address2"), "Test Step - 8 - Displayed recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            }
            if (phoneorder.getReciPhone().equals(prop.getProperty("cust_phoneNumber"))) {
                softassert.assertEquals(phoneorder.getReciPhone(), prop.getProperty("cust_phoneNumber"), "Test Step - 8 - Recipient phone number is not auto populated on recipient section");
            } else if (phoneorder.getReciPhone().equals(prop.getProperty("default_phone_number1"))) {
                softassert.assertEquals(phoneorder.getReciPhone(), prop.getProperty("default_phone_number1"), "Test Step - 8 - Recipient phone number is not auto populated on recipient section");
            }

            if (phoneorder.getRecipientPhone2OnPhoneOrderPage().equals(prop.getProperty("cust_phoneNumber"))) {
                softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), prop.getProperty("cust_phoneNumber"), "Test Step - 8 - Recipient alt phone number is not auto populated on recipient section");
            } else if (phoneorder.getRecipientPhone2OnPhoneOrderPage().equals("")) {
                softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), "", "Test Step - 8 - Recipient alt phone number is not auto populated on recipient section");
            }

            // Test Step - 8
            phoneorder.EnterReciFirstName(prop.getProperty("recipient_firstName1"));
            phoneorder.EnterReciLastName(prop.getProperty("recipient_lastName1"));
            phoneorder.EnterReciAddress1(prop.getProperty("Reci_Address1_1"));
            phoneorder.EnterReciAddress2(prop.getProperty("Reci_Address1_2"));
            phoneorder.SearchAndSelectReciAddress1(prop.getProperty("Full_Reci_Address1_1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "1160 W 5th St", "Test Step - 8 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63090", "Test Step - 8 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Washington", "Test Step - 8 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 8 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.EnterReciPhone(prop.getProperty("recipient_phonenumber1"));
            phoneorder.EnterRecipientPhone2OnPhoneOrderPage(prop.getProperty("recipient_altphonenumber"));

            softassert.assertEquals(phoneorder.getReciPhone(), "956-655-0756", "Test Step - 8 - Entered Recipient phone number is not displayed on recipient section");
            softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), "878-787-7878", "Test Step - 8 - Alternate Recipient phone number is not displayed on recipient section");

            phoneorder.SelectReciLocation(prop.getProperty("recipient_location1"));
            softassert.assertEquals(phoneorder.getSelectedLocation_OnRecipientSection(), "Church", "Test Step - 8 - Selected Location is not displayed on phone order page recipient section");

            phoneorder.Select_Zone_OnRecipientSection(prop.getProperty("zone"));
            softassert.assertEquals(phoneorder.getSelectedZone_OnRecipientSection(), "Automation Zone", "Test Step - 8 - Selected Zone is not displayed on phone order page recipient section");

            // Test Step - 9
            delayWithGivenTime(1000);
            phoneorder.ClickSelectOptions_OnRecipient();
            delayWithGivenTime(2000);
            phoneorder.Click_ConfidentialOption();
            phoneorder.Click_FrequentAddrOption();
            phoneorder.Click_SaveFuneralOption();
            delayWithGivenTime(2000);

            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getSelectedOption(), "All selected (3)", "Test Step - 11 - Selected All options is not displayed on phone order page recipient section");

            // Test Step - 12
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(17, 30);
            phoneorder.Select_DeliveryOnTime_Dropdown(prop.getProperty("deliveryOnTime"));
            delayWithGivenTime(1000);
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            phoneorder.EnterViewShortCode();
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 12 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 12 -Entered Short code is not displayed on phone order page order details section");

            // Test Step - 13
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), "rrd", "Test Step - 13 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), "Red Rose Deluxe", "Test Step - 13 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 13 - Item quantity is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 13 - Item price is not displayed on phone order page product details section");

            // Test Step - 14
            delayWithGivenTime(1000);
            phoneorder.ClickPlaceOrderButton();
            getDriver().switchTo().activeElement();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 14 - Confirmation popup is not displayed on phone order page");

            // Test Step - 15
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 15 - Order confirmation page is not displayed");
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            // Test Step - 16
            dashboard.ClickOrder();
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 16 - Dashboard order page is not displayed");

            // Test Step - 17
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.Validate_WireInOrder_FSN_InvoiceNumber(prop.getProperty("Order_Type_FSN"), prop.getProperty("Delivery_Type_WireIn"), prop.getProperty("MOP_As_FSN")), "Test Step - 17 - Wire in order invoice number " + prop.getProperty("MOP_As_FSN") + " is not displayed");        //https://hanafloralpos3.com/Dashboard/Order/Validate_PhoneOrder_InvoiceInHousePayment();

            // Test Step - 18
            dashboardorder.Click_WireInOrder_FSN_InvoiceNumber(prop.getProperty("Order_Type_FSN"), prop.getProperty("Delivery_Type_WireIn"), prop.getProperty("MOP_As_FSN"));

            // Test Step - 19
            softassert.assertTrue(dashboardorder.Verify_wirein_ConfidentialReqMessage_Appears(), "Test Step - 19- Customer requested confidentiality message is not displayed on delivery popup");
            softassert.assertEquals(dashboardorder.Verify_wirein_ConfidentialReqTextMessage_Appears(), "Customer requested confidentiality", "Test Step - 17 - Customer requested confidentiality text message is not displayed properly on delivery popup");
            dashboardorder.ClickCloseIconOnDeliveryPopup();

            // Test Step - 20
            dashboard = new HanaDashBoardPage();
            dashboard.Click_settingsSubmenu();
            delayWithGivenTime(2000);
            Configuration_SettingsPage settingsPage = new Configuration_SettingsPage();
            softassert.assertTrue(settingsPage.Verify_Configuration_SettingsPage(), "Test Step - 20 - Settings page is not displayed");

            // Test Step - 21
            delayWithGivenTime(2000);
            settingsPage.Click_OrderEntryLeftMenu();

            // Test Step - 22
            settingsPage.Click_FuneralLogMenu();

            // Test Step - 23
            delayWithGivenTime(3000);
            softassert.assertEquals(settingsPage.getDisplayedFirstName(), "Abish", "Test Step - 23 - First Name Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedLastName(), "David", "Test Step - 23 - Last Name Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedDeliveryDate(), CurrentDate(), "Test Step - 23 - Delivery Date Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedDeliveryTime(), "05:30 PM", "Test Step - 23 - Delivery Time Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedDeliveryOn(), "Exactly At", "Test Step - 23 - Delivery On Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedDeliveryAddress1(), "1160 W 5th St", "Test Step - 23 - Delivery Address 1 Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedDeliveryAddress2(), "112 Penny Ct,", "Test Step - 23 - Delivery Address 2 Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");

            // Test Step - 24
            settingsPage.Click_EditIcon_FuneralLogGridTableRow1();
            softassert.assertTrue(settingsPage.Verify_FuneralLog_AddNewRecord_Popup(), "Test Step - 24 - Funeral Log Add new record popup is not displayed");
            softassert.assertEquals(settingsPage.getFirstNameFuneralLogAddNewRecordPopup(), "Abish", "Test Step - 24 - First Name Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu ");
            softassert.assertEquals(settingsPage.getLastNameFuneralLogAddNewRecordPopup(), "David", "Test Step - 24 - Last Name Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu ");
            delayWithGivenTime(1000);
            softassert.assertEquals(settingsPage.getSelectedDeliveryDateFuneralLogAddNewRecordPopup(), CurrentDate(), "Test Step - 23 - Delivery Date Compared with recipient section saved information is not displayed on configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getEnteredAddress1FuneralLogAddNewRecordPopup(), "1160 W 5th St", "Test Step - 23 - Delivery Address 1 Compared with recipient section saved information is not displayed on configuration order entry menu funeral log submenu");
            delayWithGivenTime(1000);
            //softassert.assertEquals(settingsPage.getSelectedDeliveryOnFuneralLogAddNewRecordPopup(), "Exactly At","Test Step - 23 - Delivery On Compared with recipient section saved information is not displayed on configuration order entry menu funeral log submenu");
            //softassert.assertEquals(settingsPage.getSelectedDeliveryTimeFuneralLogAddNewRecordPopup(), "5:30 PM","Test Step - 23 - Delivery Time Compared with recipient section saved information is not displayed on configuration order entry menu funeral log submenu");

            settingsPage.Click_CloseIcon_FuneralLogAddNewRecordPopup();
            delayWithGivenTime(1000);
            settingsPage.Click_DeleteIcon_FuneralLogGridTableRow1();
            delayWithGivenTime(1000);
            settingsPage.Click_Row1_DeleteIcon_FuneralLogGridTableRow2();

            // Test Step - 25
            delayWithGivenTime(1000);
            settingsPage.Click_OrderEntryBreadCrumbLink();

            // Test Step - 26
            settingsPage.Click_CommonAddressManagement();
            delayWithGivenTime(2000);

            // Test Step - 27
            softassert.assertTrue(settingsPage.Verify_Address1_CommonAddressManagement_IsDisplayed("1160 W 5th St"), "Test Step - 27 - Recipient address 1 field is not displayed on order entry menu common address management section");

            // Test Step - 28
            softassert.assertEquals(settingsPage.Verify_DisplayedAddress1_CommonAddressManagement(), "1160 W 5th St", "Test Step - 28 - Displayed address1 in configuration settings page on common address management page is not matched with recipient address1 field");
            delayWithGivenTime(1000);
            //==================Delete the address to avoid junks==========================
            settingsPage.Click_Row1_DeleteIcon();
            settingsPage.Click_YesButton_On_DeleteConfirmation();
        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}