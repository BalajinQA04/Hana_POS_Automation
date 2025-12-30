package com.hanapos.testcases.Configuration_Settings_Testcases.Order_Entry;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T1277_Configuration_Settings_Order_Entry_Coupon_Details_Use_Delivery_Dates_Toogle_Button_Enabled_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private Advance_DispatchPage advanceDispatchPage;
    private ConfirmationPage confirmationPage;
    String invoiceNumber;
    public static final String dataSheetName = "Hana_T1432";


    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    private Configuration_SettingsPage settingsPage;

    String coupon_name1;
    String coupon_name2;

    @Epic("Configuration Settings Module - Order Entry")
    @Test(priority = 1, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1277_Configuration_Settings_Order_Entry_Coupon_Details_Use_Delivery_Dates_Toogle_Button_Enabled_Functionality_Test(
            String username,
            String password,
            String shopname,
            String salesperson,
            String customername,
            String recipientfname,
            String recipientlname,
            String recipientaddress1,
            String recipientfulladdress1,
            String recipientzipcode,
            String recipientcity,
            String recipientcountry,
            String recipientphone,
            String recipientlocation,
            String deliveryontime,
            String occasion,
            String cardmessage,
            String itemcode,
            String itemdescription,
            String paymenttype,
            String ordertype,
            String deliverytype,
            String mop
    ) {
        CustomSoftAssert softassert = new CustomSoftAssert();
        String testCaseName = getCurrentTestName();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 - Login page is not displayed");

            // Test Step - 2
            lp.EnterUserName(username);
            lp.EnterPassword(password);
            softassert.assertEquals(lp.get_entered_username(), username, "Test Step - 2: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), password, "Test Step - 2: Entered password is not matching with expected password as " + prop.getProperty("password"));
            lp.ClickLoginButton();

            // Test Step - 3
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 - Hana dashboard page is not displayed");
            delayWithGivenTime(2000);
            dashboard.SelectShopNameDropDown(shopname);
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboard.get_selected_shopname_from_hanadashboard(), shopname, "Test Step - 3: Selected shopname is not matching with expected shopname as " + dashboard.get_selected_shopname_from_hanadashboard());
            delayWithGivenTime(1000);
            dashboard.Click_settingsSubmenu();

            delayWithGivenTime(2000);
            settingsPage = new Configuration_SettingsPage();
            softassert.assertTrue(settingsPage.Verify_Configuration_SettingsPage(), "Test Step - 3 - Configuration Settings page is not displayed");

            // Test Step - 4
            settingsPage.Click_OrderEntryLeftMenu();
            settingsPage.click_CouponDetails_Submenu();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_CouponDetails_Section_Displayed(), "Test Step - 4 - Coupon details page is not displayed");

            // Test Step - 5
            delayWithGivenTime(1000);
            settingsPage.select_ShopName_On_CouponDetails(shopname);
            delayWithGivenTime(1000);
            softassert.assertEquals(settingsPage.get_Selected_ShopName_On_CouponDetails(), shopname, "Test Step - 5: Selected shopname is not matching with expected shopname as " + shopname);

            // Test Step - 6
            settingsPage.click_AddNew_Coupon_Button();
            delayWithGivenTime(1000);
            softassert.assertTrue(settingsPage.is_AddNew_Record_Popup_Coupon_Details(), "Add new record popup is not displayed on coupon details page");
            coupon_name1 = settingsPage.generate_Coupon_Name();

            settingsPage.enter_CouponName_For_CouponDetails_Popup(coupon_name1);
            softassert.assertEquals(settingsPage.get_CouponName_On_CouponDetails(), coupon_name1, "Test Step - 6: Entered coupon name is not matching ");

            delayWithGivenTime(500);
            settingsPage.set_Coupon_Percent_On_CouponDetails_popup("10");
            softassert.assertEquals(settingsPage.get_CouponPercent_On_CouponDetailsPopup(), "10", "Test Step - 6: Entered coupon percent is not matched");
            delayWithGivenTime(2000);

            // Test Step - 7
            settingsPage.set_Coupon_StartDate_On_CouponDetails_popup(CurrentDate());
            delayWithGivenTime(2000);

            settingsPage.set_Coupon_EndDate_On_CouponDetails_popup(Next_20Days_Date());
            delayWithGivenTime(2000);

            // Test Step - 8
            settingsPage.enter_Coupon_Limit_On_CouponDetails_Popup("1000");
            delayWithGivenTime(1000);
            settingsPage.enter_Coupon_Minimum_On_CouponDetails_Popup("20.00");
            delayWithGivenTime(2000);

            // Test Step - 9
            settingsPage.Turn_On_Toogle_Use_Delivery_Dates_On_CouponDetails_Popup();
            delayWithGivenTime(1000);

            // Test Step - 10
            settingsPage.Turn_On_Toogle_Display_Coupon_On_Website_On_CouponDetails_Popup();
            delayWithGivenTime(1000);

            // Test Step - 11
            settingsPage.click_Submit_Button_On_Add_New_Coupon_Details_Popup();
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();

            delayWithGivenTime(3000);
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Successfully updated settings", "Added New Common Address is not displayed");
            delayWithGivenTime(1000);

            softassert.assertTrue(settingsPage.is_Coupon_Name_Displayed_On_CouponDetails_TableGrid(coupon_name1), "Test Step - 11: Coupon name is not displayed on coupon details table grid");
            softassert.assertTrue(settingsPage.is_Coupon_Percent_Displayed_On_CouponDetails_TableGrid(coupon_name1), "Test Step - 11: Coupon percent is not displayed on coupon details table grid");
            softassert.assertTrue(settingsPage.is_Coupon_StartDate_Displayed_On_CouponDetails_TableGrid(coupon_name1), "Test Step - 11: Coupon start date is not displayed on coupon details table grid");
            softassert.assertTrue(settingsPage.is_Coupon_EndDate_Displayed_On_CouponDetails_TableGrid(coupon_name1), "Test Step - 11: Coupon end date is not displayed on coupon details table grid");

            // Test Step - 12
            delayWithGivenTime(2000);
            dashboard.ClickOnHomeIcon();
            delayWithGivenTime(2000);

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 12 : Hana dashboard Page does not navigated to hana dashboard page");

            // Test Step - 13
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 13: Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 13: Cash and carry option is not displayed");

            dashboard.ClickOrderEntry();
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(shopname);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), shopname, "Test Step - 13 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickDeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnDeliveryTypeOnPhoneOrderPage(), "#676a6c", "Test Step - 13: Delivery type as Delivery is not highlighted in blue color");

            // Test Step - 14
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            phoneorder.SearchAndSelectCustomerOnCust_Section(customername);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), prop.getProperty("cust_firstName"), "Test Step - 14 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), prop.getProperty("cust_lastName"), "Test Step - 14 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), prop.getProperty("cust_companyName"), "Test Step - 14 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), prop.getProperty("cust_email"), "Test Step - 14 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), prop.getProperty("cust_address1"), "Test Step - 14 address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 14 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), prop.getProperty("cust_zipcode"), "Test Step - 14 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), prop.getProperty("cust_city"), "Test Step - 14 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_phoneNumber"), "Test Step - 14 phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_Alt_phoneNumber"), "Test Step - 14 - Alt phone number is not displayed on phone order page");

            // Test Step - 14
            phoneorder.EnterReciFirstName(recipientfname);
            phoneorder.EnterReciLastName(recipientlname);
            softassert.assertEquals(phoneorder.getReciFirstName(), recipientfname, "Test Step - 14 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recipientlname, "Test Step - 14 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(recipientfulladdress1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "1160 W 5th St", "Test Step - 14 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63090", "Test Step - 14 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Washington", "Test Step - 14 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 14 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.SelectReciCountry(recipientcountry);
            phoneorder.EnterReciPhone(recipientphone);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recipientlocation);
            delayWithGivenTime(1000);
            phoneorder.Enter_DeliveryTime_OnRecipientSection(18, 30);
            phoneorder.Select_DeliveryOnTime_Dropdown(deliveryontime);
            delayWithGivenTime(1000);

            //Test Step - 15
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), cardmessage);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), occasion, "Test Step - 15 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(cardmessage), true, "Test Step - 5 - Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 16
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(itemcode, itemdescription);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), itemcode, "Test Step - 16 - Item code as " + itemcode + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), itemdescription, "Test Step - 16 - Item description " + prop.getProperty("product_description1") + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 16 - Item quantity is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 16 - Item price is not displayed on phone order page product details section");
            delayWithGivenTime(2000);

            // Test Step - 17
            delayWithGivenTime(2000);
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymenttype);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), paymenttype, "Test Step - 17 - Selected payment type " + paymenttype + " is not displayed on phone order page payment section");

            delayWithGivenTime(2000);
            phoneorder.EnterDeliveryDateOnReciSection(select_Next_Dates_Dynamically(28));
            delayWithGivenTime(2000);

            phoneorder.Enter_Coupon_Code_On_PhoneOrderPage_PaymentSection(coupon_name1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.verify_Toaster_Message_Text_displayed(), "Coupon not valid for selected delivery/pickup date.", "Test Step - 7 - Success toast message is not displayed on phone order page");

            delayWithGivenTime(2000);
            phoneorder.EnterDeliveryDateOnReciSection(select_Next_Dates_Dynamically(2));
            delayWithGivenTime(2000);
            phoneorder.Enter_Coupon_Code_On_PhoneOrderPage_PaymentSection(coupon_name1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_Discount_Percentage_On_PhoneOrderPage_PaymentSection(), "10.00", "Test Step - 7 - Discount percentage is not displayed on phone order page payment section");

            delayWithGivenTime(2000);
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymenttype);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), paymenttype, "Test Step - 7 - Selected payment type " + paymenttype + " is not displayed on phone order page payment section");

            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 7 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 8
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 8 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();


        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }

    }

    @Epic("Configuration Settings Module - Order Entry")
    @Test(priority = 2, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1287_Configuration_Settings_Order_Entry_Coupon_Details_Use_Delivery_Dates_Toogle_Button_Disabled_Functionality_Test(
            String username,
            String password,
            String shopname,
            String salesperson,
            String customername,
            String recipientfname,
            String recipientlname,
            String recipientaddress1,
            String recipientfulladdress1,
            String recipientzipcode,
            String recipientcity,
            String recipientcountry,
            String recipientphone,
            String recipientlocation,
            String deliveryontime,
            String occasion,
            String cardmessage,
            String itemcode,
            String itemdescription,
            String paymenttype,
            String ordertype,
            String deliverytype,
            String mop
    ) {
        CustomSoftAssert softassert = new CustomSoftAssert();
        String testCaseName = getCurrentTestName();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 - Login page is not displayed");

            // Test Step - 2
            lp.EnterUserName(username);
            lp.EnterPassword(password);
            softassert.assertEquals(lp.get_entered_username(), username, "Test Step - 2: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), password, "Test Step - 2: Entered password is not matching with expected password as " + prop.getProperty("password"));
            lp.ClickLoginButton();

            // Test Step - 3
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 - Hana dashboard page is not displayed");
            delayWithGivenTime(2000);
            dashboard.SelectShopNameDropDown(shopname);
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboard.get_selected_shopname_from_hanadashboard(), shopname, "Test Step - 3: Selected shopname is not matching with expected shopname as " + dashboard.get_selected_shopname_from_hanadashboard());
            delayWithGivenTime(1000);
            dashboard.Click_settingsSubmenu();

            delayWithGivenTime(2000);
            settingsPage = new Configuration_SettingsPage();
            softassert.assertTrue(settingsPage.Verify_Configuration_SettingsPage(), "Test Step - 3 - Configuration Settings page is not displayed");

            // Test Step - 4
            settingsPage.Click_OrderEntryLeftMenu();
            settingsPage.click_CouponDetails_Submenu();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_CouponDetails_Section_Displayed(), "Test Step - 4 - Coupon details page is not displayed");

            // Test Step - 5
            delayWithGivenTime(1000);
            settingsPage.select_ShopName_On_CouponDetails(shopname);
            delayWithGivenTime(1000);
            softassert.assertEquals(settingsPage.get_Selected_ShopName_On_CouponDetails(), shopname, "Test Step - 5: Selected shopname is not matching with expected shopname as " + shopname);

            // Test Step - 6
            settingsPage.click_AddNew_Coupon_Button();
            delayWithGivenTime(1000);
            softassert.assertTrue(settingsPage.is_AddNew_Record_Popup_Coupon_Details(), "Add new record popup is not displayed on coupon details page");
            coupon_name2 = settingsPage.generate_Coupon_Name();

            settingsPage.enter_CouponName_For_CouponDetails_Popup(coupon_name2);
            softassert.assertEquals(settingsPage.get_CouponName_On_CouponDetails(), coupon_name2, "Test Step - 6: Entered coupon name is not matching ");

            delayWithGivenTime(500);
            settingsPage.set_Coupon_Percent_On_CouponDetails_popup("10");
            softassert.assertEquals(settingsPage.get_CouponPercent_On_CouponDetailsPopup(), "10", "Test Step - 6: Entered coupon percent is not matched");
            delayWithGivenTime(2000);

            // Test Step - 7
            settingsPage.set_Coupon_StartDate_On_CouponDetails_popup(CurrentDate());
            delayWithGivenTime(2000);

            settingsPage.set_Coupon_EndDate_On_CouponDetails_popup(Next_20Days_Date());
            delayWithGivenTime(2000);

            // Test Step - 8
            settingsPage.enter_Coupon_Limit_On_CouponDetails_Popup("1000");
            delayWithGivenTime(1000);
            settingsPage.enter_Coupon_Minimum_On_CouponDetails_Popup("20.00");
            delayWithGivenTime(2000);

            // Test Step - 9
            settingsPage.turnOffToggle_UseDeliveryDates_CouponDetails();
            delayWithGivenTime(1000);
            softassert.assertTrue(settingsPage.verifyToggleIsOff_UseDeliveryDates_CouponDetails(), "Test Step - 7: Use delivery date toogle button is not displayed as disabled state once turn off");

            // Test Step - 10
            settingsPage.Turn_On_Toogle_Display_Coupon_On_Website_On_CouponDetails_Popup();
            delayWithGivenTime(1000);

            // Test Step - 11
            settingsPage.click_Submit_Button_On_Add_New_Coupon_Details_Popup();
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();

            delayWithGivenTime(3000);
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Successfully updated settings", "Added New Common Address is not displayed");
            delayWithGivenTime(1000);

            softassert.assertTrue(settingsPage.is_Coupon_Name_Displayed_On_CouponDetails_TableGrid(coupon_name2), "Test Step - 11: Coupon name is not displayed on coupon details table grid");
            softassert.assertTrue(settingsPage.is_Coupon_Percent_Displayed_On_CouponDetails_TableGrid(coupon_name2), "Test Step - 11: Coupon percent is not displayed on coupon details table grid");
            softassert.assertTrue(settingsPage.is_Coupon_StartDate_Displayed_On_CouponDetails_TableGrid(coupon_name2), "Test Step - 11: Coupon start date is not displayed on coupon details table grid");
            softassert.assertTrue(settingsPage.is_Coupon_EndDate_Displayed_On_CouponDetails_TableGrid(coupon_name2), "Test Step - 11: Coupon end date is not displayed on coupon details table grid");

            // Test Step - 12
            delayWithGivenTime(2000);
            dashboard.ClickOnHomeIcon();
            delayWithGivenTime(2000);

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 12 : Hana dashboard Page does not navigated to hana dashboard page");

            // Test Step - 13
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 13: Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 13: Cash and carry option is not displayed");

            dashboard.ClickOrderEntry();
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(shopname);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), shopname, "Test Step - 13 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickDeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnDeliveryTypeOnPhoneOrderPage(), "#676a6c", "Test Step - 13: Delivery type as Delivery is not highlighted in blue color");

            // Test Step - 14
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            phoneorder.SearchAndSelectCustomerOnCust_Section(customername);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), prop.getProperty("cust_firstName"), "Test Step - 14 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), prop.getProperty("cust_lastName"), "Test Step - 14 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), prop.getProperty("cust_companyName"), "Test Step - 14 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), prop.getProperty("cust_email"), "Test Step - 14 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), prop.getProperty("cust_address1"), "Test Step - 14 address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 14 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), prop.getProperty("cust_zipcode"), "Test Step - 14 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), prop.getProperty("cust_city"), "Test Step - 14 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_phoneNumber"), "Test Step - 14 phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_Alt_phoneNumber"), "Test Step - 14 - Alt phone number is not displayed on phone order page");

            // Test Step - 14
            phoneorder.EnterReciFirstName(recipientfname);
            phoneorder.EnterReciLastName(recipientlname);
            softassert.assertEquals(phoneorder.getReciFirstName(), recipientfname, "Test Step - 14 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recipientlname, "Test Step - 14 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(recipientfulladdress1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "1160 W 5th St", "Test Step - 14 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63090", "Test Step - 14 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Washington", "Test Step - 14 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 14 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.SelectReciCountry(recipientcountry);
            phoneorder.EnterReciPhone(recipientphone);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recipientlocation);
            delayWithGivenTime(1000);
            phoneorder.Enter_DeliveryTime_OnRecipientSection(18, 30);
            phoneorder.Select_DeliveryOnTime_Dropdown(deliveryontime);
            delayWithGivenTime(1000);

            //Test Step - 15
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), cardmessage);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), occasion, "Test Step - 15 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(cardmessage), true, "Test Step - 5 - Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 16
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(itemcode, itemdescription);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), itemcode, "Test Step - 16 - Item code as " + itemcode + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), itemdescription, "Test Step - 16 - Item description " + prop.getProperty("product_description1") + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 16 - Item quantity is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 16 - Item price is not displayed on phone order page product details section");
            delayWithGivenTime(2000);

            // Test Step - 17
            delayWithGivenTime(2000);
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymenttype);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), paymenttype, "Test Step - 17 - Selected payment type " + paymenttype + " is not displayed on phone order page payment section");

            delayWithGivenTime(2000);
            phoneorder.EnterDeliveryDateOnReciSection(select_Next_Dates_Dynamically(28));
            delayWithGivenTime(2000);

//            phoneorder.Enter_Coupon_Code_On_PhoneOrderPage_PaymentSection(coupon_name);
//            delayWithGivenTime(2000);
//          //  softassert.assertEquals(phoneorder.verify_Toaster_Message_Text_displayed(), "Coupon not valid for selected delivery/pickup date.", "Test Step - 7 - Success toast message is not displayed on phone order page");

//            delayWithGivenTime(2000);
//            phoneorder.EnterDeliveryDateOnReciSection(select_Next_Dates_Dynamically(2));
//            delayWithGivenTime(2000);
            phoneorder.Enter_Coupon_Code_On_PhoneOrderPage_PaymentSection(coupon_name2);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_Discount_Percentage_On_PhoneOrderPage_PaymentSection(), "10.00", "Test Step - 7 - Discount percentage is not displayed on phone order page payment section");

            delayWithGivenTime(2000);
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymenttype);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), paymenttype, "Test Step - 7 - Selected payment type " + paymenttype + " is not displayed on phone order page payment section");

            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 7 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 8
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 8 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            // Test Step - 9
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 10 - Dashboard order page is not displayed");
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 10 - Respective Invoice number is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), "New", "Test Step - 10 - Respective Invoice status is not displayed on all orders page");
            delayWithGivenTime(1000);


        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }

    }

}
