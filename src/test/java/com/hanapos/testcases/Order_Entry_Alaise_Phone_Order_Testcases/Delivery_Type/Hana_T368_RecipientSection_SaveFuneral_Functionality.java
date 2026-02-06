package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Delivery_Type;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import java.util.Locale;

public class Hana_T368_RecipientSection_SaveFuneral_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private Configuration_SettingsPage settingsPage;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private DashboardOrderPage dashboardorder;
    private Order_Confirmation_Page orderconfirmationpage;

    @Epic("Phone Order Module - Delivery Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T368_RecipientSection_SaveFuneral_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T368_RecipientSection_SaveFuneral_Functionality_Test ****");
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
        /*    dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));
*/
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
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickdeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_OnDelivery_TypeOnPhoneOrderPage(), "#676a6c",
                    "Test Step - 5 - Delivery type is not highlighted as blue color");

            // Test Step - 6
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            softassert.assertTrue(phoneorder.VerifyCustSectionAppears(), "Test Step - 6 - Customer section is not displayed on phone order page");
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("cust_firstName"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), "Abish", "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), "David", "Test Step - 6 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), "Hana_Sisterchicks", "Test Step - 6 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), "hanaposqateam@gmail.com", "Test Step -6 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "3402 Park Blvd", "Test Step - 6 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 6 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "92103", "Test Step - 6 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "San Diego", "Test Step - 6 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 6 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 6 - Alt phone number is not displayed on phone order page");

            // Test Step - 7
            Faker faker = new Faker(new Locale("en-US"));
            String recifname;
            String recilname;
            String reci_phone_number1;
            String reci_phone_number2;
            String floor_number;

            recifname = faker.name().firstName();
            recilname = faker.name().lastName();
            phoneorder.EnterReciFirstName(recifname);
            phoneorder.EnterReciLastName(recilname);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            // Test Step - 8
            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1("1237 Weber Loop, Farmington");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "1237 Weber Loop", "Test Step - 3 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63640", "Test Step - 3 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Farmington", "Test Step - 3 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 3 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            // Test Step - 9
            phoneorder.EnterReciPhone(prop.getProperty("recipient_phonenumber1"));
            phoneorder.EnterRecipientPhone2OnPhoneOrderPage(prop.getProperty("recipient_phonenumber1"));
            softassert.assertEquals(phoneorder.getReciPhone(), "956-655-0756", "Test Step - 9 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), "956-655-0756", "Test Step - 9 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            // Test Step - 10
            phoneorder.Select_Location_OnRecipientSection(prop.getProperty("recipient_funeral_location"));
            softassert.assertEquals(phoneorder.getSelectedLocation_OnRecipientSection(), prop.getProperty("recipient_funeral_location"), "Test Step - 10 - Selected recipient location is not displayed on recipient location field");

            // Test Step - 11
            phoneorder.Select_Zone_OnRecipientSection(prop.getProperty("zone"));
            softassert.assertEquals(phoneorder.getSelectedZone_OnRecipientSection(), prop.getProperty("zone"), "Test Step - 11 - Selected recipient zone is not displayed on recipient zone field");

            // Test Step - 12
            delayWithGivenTime(1000);
            phoneorder.ClickSelectOptions_OnRecipient();
            phoneorder.Click_SaveFuneralOption();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedOptions_from_MultiSelectDropdown_on_RecipientSection(), "Save Funeral", "Test Step - 12 - Selected options are not displayed on recipient section");

            // Test Step - 13
            phoneorder.Enter_DeliveryTime_OnRecipientSection(18, 30);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_deliveryTime_on_recipientSection(), "06:30 PM", "Test Step - 13 - Delivery time is not displayed on recipient section");

            // Test Step - 14
            phoneorder.Select_DeliveryOnTime_Dropdown(prop.getProperty("deliveryOnTime"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getSelected_DeliveryOnTimeOptions_OnDropdown_RecipientSection(), prop.getProperty("deliveryOnTime"), "Test Step - 14 - Selected delivery on time option is not displayed on phone order page order details section");

            // Test Step - 15
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 15 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 15 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 16
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), prop.getProperty("product_itemcode1"), "Test Step - 16 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), prop.getProperty("product_description1"), "Test Step - 16 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 16 - Item quantity is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 16 - Item price is not displayed on phone order page product details section");
            delayWithGivenTime(2000);

            // Test Step - 17
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type_invoice"));
            delayWithGivenTime(1000);
            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(1000);

            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 17 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 18
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 18 - Order confirmation page is not displayed");

            // Test Step - 19
            dashboard = new HanaDashBoardPage();
            dashboard.Click_settingsSubmenu();
            delayWithGivenTime(2000);
            settingsPage = new Configuration_SettingsPage();
            softassert.assertTrue(settingsPage.Verify_Configuration_SettingsPage(), "Test Step - 19 - Settings page is not displayed");

            // Test Step - 20
            settingsPage.Click_OrderEntryLeftMenu();

            // Test Step - 21
            settingsPage.Click_FuneralLogMenu();

            // Test Step - 22
            delayWithGivenTime(3000);
            softassert.assertEquals(settingsPage.getDisplayedFirstName(), recifname, "Test Step - 22 - First Name Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedLastName(), recilname, "Test Step - 22 - Last Name Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedDeliveryDate(), CurrentDate(), "Test Step - 22 - Delivery Date Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedDeliveryTime(), "06:30 PM", "Test Step - 22 - Delivery Time Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedDeliveryOn(), "Exactly At", "Test Step - 22 - Delivery On Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedDeliveryAddress1(), "1237 Weber Loop", "Test Step - 22 - Delivery Address 1 Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedDeliveryAddress2(), "", "Test Step - 22 - Delivery Address 2 Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");

            // Test Step - 23
            delayWithGivenTime(3000);
            settingsPage.Click_EditIcon_FuneralLogGridTableRow1();
            softassert.assertTrue(settingsPage.Verify_FuneralLog_AddNewRecord_Popup(), "Test Step - 23 - Funeral Log Add new record popup is not displayed");
            softassert.assertEquals(settingsPage.getFirstNameFuneralLogAddNewRecordPopup(), recifname, "Test Step - 23 - First Name Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu ");
            softassert.assertEquals(settingsPage.getLastNameFuneralLogAddNewRecordPopup(), recilname, "Test Step - 23 - Last Name Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu ");
            delayWithGivenTime(1000);
            softassert.assertEquals(settingsPage.getSelectedDeliveryDateFuneralLogAddNewRecordPopup(), CurrentDate(), "Test Step - 23 - Delivery Date Compared with recipient section saved information is not displayed on configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getEnteredAddress1FuneralLogAddNewRecordPopup(), "1237 Weber Loop", "Test Step - 23 - Delivery Address 1 Compared with recipient section saved information is not displayed on configuration order entry menu funeral log submenu");
            delayWithGivenTime(1000);
            //========================Below webelements are unable to get immediately getting stop
            //	softassert.assertEquals(settingsPage.getSelectedDeliveryOnFuneralLogAddNewRecordPopup(), "Exactly At","Test Step - 23 - Delivery On Compared with recipient section saved information is not displayed on configuration order entry menu funeral log submenu");
            //	softassert.assertEquals(settingsPage.getSelectedDeliveryTimeFuneralLogAddNewRecordPopup(), "5:30 PM","Test Step - 23 - Delivery Time Compared with recipient section saved information is not displayed on configuration order entry menu funeral log submenu");
            delayWithGivenTime(1000);

            //======== To Avoid junk files due to automation delete the funeral log============//
            settingsPage.Click_CloseIcon_FuneralLogAddNewRecordPopup();
            delayWithGivenTime(1000);
            settingsPage.Click_DeleteIcon_FuneralLogGridTableRow1();
            delayWithGivenTime(1000);
            settingsPage.Click_Row1_DeleteIcon_FuneralLogGridTableRow2();

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}