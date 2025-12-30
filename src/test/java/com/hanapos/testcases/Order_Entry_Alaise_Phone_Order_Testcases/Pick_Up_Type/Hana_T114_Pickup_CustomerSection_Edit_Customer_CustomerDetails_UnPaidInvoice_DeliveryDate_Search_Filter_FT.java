package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T114_Pickup_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_DeliveryDate_Search_Filter_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    @Epic("Phone Order Module - Pickup Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T114_Pickup_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_DeliveryDate_Search_Filter_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting Hana_T114_OrderEntryPage_Pickup_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_DeliveryDate_Search_Filter_FT  ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 2: Entered username is not matching with expected username");
            logger.info("User entered the username as " + prop.getProperty("username"));

            lp.EnterPassword(prop.getProperty("password"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 2: Entered password is not matching with expected password");
            logger.info("User entered the password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2: Page did not navigate to hana dashboard page");
            logger.info("User navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3: Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3: Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Test Step - 5 - Pickup type is not highlighted in blue color");

            //Test Step - 6
            delayWithGivenTime(2000);
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
            delayWithGivenTime(2000);

            // Test Step - 7
            softassert.assertTrue(phoneorder.Verify_CustEditIcon_Appears(), "Test Step - 7 - Customer clear button is not displayed on phone order page");

            // Test Step  - 8
            phoneorder.Click_CustEditIcon();
            softassert.assertTrue(phoneorder.Verify_CustomerDetailsPopupAppears(), "Test Step - 8 - Customer details popup on is not displayed on phone order page");

            // Test Step - 9
            delayWithGivenTime(1000);
            phoneorder.Click_UnpaidInvoiceTab_OnCustDetailsPopup();
            delayWithGivenTime(1000);

            // Test Step - 10
            phoneorder.Enter_DeliveryDate_OnInvoiceSearchBox_UnpaidInvoiceTab(CurrentDate());
            softassert.assertEquals(phoneorder.get_EnteredDeliveryDateSearchtext_UnpaidInvoiceTab(), CurrentDate(), "Test Step - 10 - Entered delivery date on search text box which is not getting cleared ");

            // Test Step - 11
            RobotPressEnter();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_DeliveryDateValue_OnDisplayedInvoices(CurrentDate()), "Test Step - 11 - Delivery Date value is not displayed in unpaid invoice grid table");
            delayWithGivenTime(1000);

            // Test Step - 12
            phoneorder.Enter_DeliveryDate_OnInvoiceSearchBox_UnpaidInvoiceTab(CurrentDate());
            softassert.assertEquals(phoneorder.get_EnteredDeliveryDateSearchtext_UnpaidInvoiceTab(), CurrentDate(), "Test Step - 12 - Entered delivery date on search text box which is not getting cleared ");

            // Test Step - 13
            RobotPressEnter();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_DeliveryDateValue_OnDisplayedInvoices(CurrentDate()), "Test Step - 13 - Delivery Date value is not displayed in unpaid invoice grid table");

            // Test Step - 14
            delayWithGivenTime(1000);
            phoneorder.Enter_DeliveryDate_OnInvoiceSearchBox_UnpaidInvoiceTab("17/17/0000");
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_EnteredDeliveryDateSearchtext_UnpaidInvoiceTab(), "17/17/0000", "Test Step - 14 - Entered delivery date on search text box which is not getting cleared ");

            RobotPressEnter();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_DeliveryDate_UnpaidInvoiceTable_OnUnpaidInvoiceTab(), "", "Test step - 14 - Entered invalid delivery date value is displayed in unpaid invoice grid table");

            // Test Step - 15
            delayWithGivenTime(1000);
            phoneorder.Enter_DeliveryDate_OnInvoiceSearchBox_UnpaidInvoiceTab(Next_TwoYears_Date());
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_EnteredDeliveryDateSearchtext_UnpaidInvoiceTab(), Next_TwoYears_Date(), "Test Step - 17 - Entered delivery date on search text box which is not getting cleared ");

            RobotPressEnter();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_NoUnpaidInvoiceFound(), "Test step - 15 - No unpaid invoice found message is not displayed in unpaid invoice grid table while entering delivery date which is not present in unpaid invoice grid table");

            // Test Step - 16
            softassert.assertTrue(phoneorder.Verify_ClearButton_AppearsOnDeliveryDateSearchtextbox_UnpaidInvoiceTab(), "Test Step - 16 - Clear button is not displayed on delivery date search textbox at unpaid invoice tab in customer details popup");

            // Test Step - 17
            delayWithGivenTime(1000);
            phoneorder.Click_Clearbutton_DeliveryDatesearchtextbox_UnpaidInvoiceTab();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_EnteredDeliveryDateSearchtext_UnpaidInvoiceTab(), "", "Test Step - 17 - Entered delivery date on search text box which is not getting cleared ");
            softassert.assertNotEquals(phoneorder.Verify_ListofInvoiceNumbers_Appears_InUnpaidInvoiceTable_OnUnpaidInvoiceTab(), 0, "Test Step - 16 - Unpaid Invoices are not displayed in unpaid invoice table on unpaid invoice tab customer details popup");

        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}