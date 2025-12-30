package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireOut_Type;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T550_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_Pagination_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    @Epic("Phone Order Module - Wire out Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T550_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_Pagination_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting Validate_Hana_T550_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_Pagination_Functionality_Test  ****");
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
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3 : Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3 : Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Click_WireOut_DeliveryType_OnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_On_WireOut_PhoneOrderPage(), "#2f9bc8", "Test Step - 5 - Wire out Delivery type is not highlighted in blue color");

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
            softassert.assertTrue(phoneorder.Verify_Pagination_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 10 - Pagination section is not appears on the unpaid invoice tab under the table");

            // Test Step - 11
            softassert.assertTrue(phoneorder.Verify_FirstPagePaginationIcon_IsAppears(), "Test Step - 11 - Go to First page pagination icon is not appears on the unpaid invoice tab under the table");
            softassert.assertTrue(phoneorder.Verify_PreviousPagePaginationIcon_IsAppears(), "Test Step - 11 - Go to Previous page pagination icon is not appears on the unpaid invoice tab under the table");
            softassert.assertTrue(phoneorder.Verify_NextPagePaginationIcon_IsAppears(), "Test Step - 11 - Go to Next page pagination icon is not appears on the unpaid invoice tab under the table");
            softassert.assertTrue(phoneorder.Verify_LastPagePaginationIcon_IsAppears(), "Test Step - 11 - Go to Last page pagination icon is not appears on the unpaid invoice tab under the table");
            softassert.assertTrue(phoneorder.Verify_CurrentPageNumber_IsAppears(), "Test Step - 11 - Current page number is not appears on the unpaid invoice tab under the table");
            softassert.assertTrue(phoneorder.Verify_ItemsperPageLabelIsAppears_OnUnpaidTab_OnCustomerDetailsPopup(), "Test Step - 11 - Items per page label is not appears on the unpaid invoice tab under the table");
            softassert.assertTrue(phoneorder.Verify_RefreshIcon_IsAppears_OnUnpaidTab_OnCustomerDetailsPopup(), "Test Step - 11 - Refresh icon is not appears on the unpaid invoice tab under the table");
            phoneorder.close_QuickView();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_Totalpagenumber_IsAppears_OnUnpaidTab_OnCustomerDetailsPopup(), "Test Step - 11 - Total page number is not appears on the unpaid invoice tab under the table");

            // Test Step - 12
            softassert.assertTrue(phoneorder.Verify_pagelist_DisplayedNumber_OnUnpaidTab_OnCustomerDetailsPopup(), "Test Step - 12 - No. of page list is not appears on the unpaid invoice tab under the table");

            //Test Step - 13
            phoneorder.Click_NextPagePaginationIcon_OnUnpaidTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_TotalPagenumber_Appears_OnUnpaidTab_OnCustomerDetailsPopup().contains("26 - " + phoneorder.get_Second_NumberOfPage()), true, "Test Step - 13 - Florist does not navigated to the next page no.2 on the unpaid invoice tab under the table");

            // Test Step - 14
            phoneorder.Click_PreviousPagePaginationIcon_OnUnpaidTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_TotalPagenumber_Appears_OnUnpaidTab_OnCustomerDetailsPopup().contains("1 - 25"), true, "Test Step - 14 - Florist does not navigated to the previous page no.1 on the unpaid invoice tab under the table");

            // Test Step - 15
            phoneorder.Click_LastPagePaginationIcon_OnUnpaidTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertEquals(
                    phoneorder.get_TotalPagenumber_Appears_OnUnpaidTab_OnCustomerDetailsPopup(),
                    phoneorder.get_first_NumberOfPage() + " - " + phoneorder.get_TotalPageNumber_Appears_OnUnpaidTab(),
                    "Test Step - 15 - Florist did not navigate to the last page on the unpaid invoice tab under the table"
            );

            // Test Step - 16
            phoneorder.Click_FirstPagePaginationIcon_OnUnpaidTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_TotalPagenumber_Appears_OnUnpaidTab_OnCustomerDetailsPopup(), phoneorder.get_first_NumberOfPage() + " - " + phoneorder.get_Second_NumberOfPage(), "Test Step - 16 - Florist does not navigated to the first page on the unpaid invoice tab under the table");

            // Test Step - 17
            softassert.assertTrue(phoneorder.Verify_PreviousPagePaginationIcon_OnUnpaidTab_OnCustomerDetailsPopup(), "Test Step - 17 - Previous page pagination icon is enabled on the unpaid invoice tab under the table");
            softassert.assertTrue(phoneorder.Verify_FirstPagePaginationIconIsEnabled_OnUnpaidTab_OnCustomerDetailsPopup(), "Test Step - 17 - first page pagination icon is enabled on the unpaid invoice tab under the table");

            // Test Step - 18
            phoneorder.Click_LastPagePaginationIcon_OnUnpaidTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_TotalPagenumber_Appears_OnUnpaidTab_OnCustomerDetailsPopup().contains(phoneorder.get_first_NumberOfPage() + " - " + phoneorder.get_TotalPageNumber_Appears_OnUnpaidTab()), true, "Test Step - 18 - Florist does not navigated to the last page on the unpaid invoice tab under the table");

            // Test Step - 19
            softassert.assertTrue(phoneorder.Verify_NextPagePaginationIconIsEnabled_OnUnpaidTab_OnCustomerDetailsPopup(), "Test Step - 19 - Next page pagination icon is enabled on the unpaid invoice tab under the table");
            softassert.assertTrue(phoneorder.Verify_LastPagePaginationIconIsEnabled_OnUnpaidTab_OnCustomerDetailsPopup(), "Test Step - 19 - Last page pagination icon is enabled on the unpaid invoice tab under the table");

            // Test Step - 20
            phoneorder.Click_FirstPagePaginationIcon_OnUnpaidTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_TotalPagenumber_Appears_OnUnpaidTab_OnCustomerDetailsPopup().contains("1 - 25"), true, "Test Step - 20 - Florist does not navigated to the first page on the unpaid invoice tab under the table");

            // Test Step - 21
            phoneorder.SelectNumberDropdown_inPaginationSection_OnUnpaidTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getpagelistdropdown_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage(), "50", "Test Step - 21 - No. of page list is not appears on the unpaid invoice tab under the table");

            // Test Step - 22
            softassert.assertTrue(phoneorder.Verify_ListofInvoiceNumbers_Appears_InUnpaidInvoiceTable_OnUnpaidInvoiceTab() > 25, "Test Step - 22 - In the unpaid invoice table it displayed only 25 invoices all the number of invoices are not displayed on the unpaid invoice tab");

            // Test Step - 23
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_NoOfItemDisplayed_OnUnpaidTab_OnCustomerDetailsPopup(), "Test Step - 23 - No. of item is not appears on the unpaid invoice tab under the table");

        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}