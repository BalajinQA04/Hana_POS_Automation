package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Delivery_Type;

import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T324_OrderEntryPage_Delivery_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_ColumnReorder_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    @Epic("Phone Order Module - Delivery Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T324_OrderEntryPage_Delivery_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_ColumnReorder_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting Hana_T324_OrderEntryPage_Delivery_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_ColumnReorder_FT  ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana dashboard page is not displayed");
            logger.info("User navigated to hana dashboard page");
         /*   dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));
*/
            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3 : Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3 : Cash and carry option is not displayed");

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
            softassert.assertEquals(phoneorder.get_HighlightedColor_OnDelivery_TypeOnPhoneOrderPage(), "#676a6c", "Test Step - 5 - Delivery type is not highlighted in blue color");

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
            softassert.assertEquals(phoneorder.Validate_InvoiceHeader_ReorderFunctionality(), "Paid Amt", "Test Step - 10 - Invoice header is not reorder on column 2 on unpaid invoice tab on phone order page");
            phoneorder.Reorder_InvoiceHeader();

            // Test Step - 11
            softassert.assertEquals(phoneorder.Validate_PaidAmt_ReOrderFunctionality(), "Invoice", "Test Step - 11 - Paid Amt header is not reorder on column 1 on unpaid invoice tab on phone order page");
            phoneorder.Reorder_PaidAmtHeader();

            // Test Step - 12
            softassert.assertEquals(phoneorder.Validate_BalanceAmt_ReOrderFunctionality(), "Balance Amt", "Test Step - 12 - Balance Amt header is not reorder on column 4 on unpaid invoice tab on phone order page");
            phoneorder.Reorder_BalanceAmtHeader();

            // Test Step - 13
            softassert.assertEquals(phoneorder.Validate_ProductTotal_ReOrderFunctionality(), "Product Total", "Test Step - 13 - Product Total header is not reorder on column 3 on unpaid invoice tab on phone order page");
            phoneorder.Reorder_ProductTotalHeader();

            // Test Step - 14
            softassert.assertEquals(phoneorder.Validate_Delivery_ReOrderFunctionality(), "Delivery", "Test Step - 14 - Delivery header is not reorder on column 6 on unpaid invoice tab on phone order page");
            phoneorder.Reorder_DeliveryHeader();

            // Test Step - 15
            softassert.assertEquals(phoneorder.Validate_Tax_ReOrderFunctionality(), "Tax", "Test Step - 15 - Tax header is not reorder on column 5 on unpaid invoice tab on phone order page");
            phoneorder.Reorder_DeliveryHeader();

            // Test Step - 16
            softassert.assertEquals(phoneorder.Validate_Latefee_ReOrderFunctionality(), "Late Fee", "Test Step - 16 - Late fee header is not reorder on column 8 on unpaid invoice tab on phone order page");
            phoneorder.Reorder_LatefeeHeader();

            // Test Step - 17
            softassert.assertEquals(phoneorder.Validate_Total_ReOrderFunctionality(), "Total", "Test Step - 17 - Total header is not reorder on column 7 on unpaid invoice tab on phone order page");
            phoneorder.Reorder_TotalHeader();

            // Test Step - 18
            softassert.assertEquals(phoneorder.Validate_OrderDate_ReOrderFunctionality(), "Order Date", "Test Step - 18 - Order Date header is not reorder on column 10 on unpaid invoice tab on phone order page");
            phoneorder.Reorder_OrderDateHeader();

            // Test Step - 19
            softassert.assertEquals(phoneorder.Validate_DeliveryDate_ReOrderFunctionality(), "Delivery Date", "Test Step - 19 - Delivery Date header is not reorder on column 9 on unpaid invoice tab on phone order page");
            phoneorder.Reorder_DeliveryDateHeader();

        } catch (Exception e) {
            softassert.fail(e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}