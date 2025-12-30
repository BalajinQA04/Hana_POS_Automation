
package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Delivery_Type;

import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T322_OrderEntryPage_Delivery_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_Sorting_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    @Epic("Phone Order Module - Delivery Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T322_OrderEntryPage_Delivery_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_Sorting_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting Hana_T322_OrderEntryPage_Delivery_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_Sorting_FT  ****");
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

            if (dashboard.VerifyHanaDashBoardPage() == false) {
                getDriver().navigate().refresh();
            }

            logger.info("User navigated to hana dashboard page");
           /* dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
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

            // Test Step - 10
            phoneorder.ClickInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Uparrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 10 - Up arrow is not displayed on Invoice label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.Verify_InvoiceHeader_Sorted_ascending(), "Test Step - 10 -  Invoices are not sorting as ascending order on Invoice label at Unpaid invoice tab on Customer details popup");

            // Test Step - 11
            phoneorder.ClickInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Downarrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 11 - Down arrow is not displayed on Invoice label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.Verify_InvoiceHeader_Sorted_descending(), "Test Step - 11 - Invoices are not sorting as descending order on Invoice label at Unpaid invoice tab on Customer details popup");

            // Test Step - 12
            phoneorder.ClickInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_InvoiceHeader_DefaultOrder(), "Test Step - 12 - Invoices are not sorting as default order on Invoice label at Unpaid invoice tab on Customer details popup");

            // Test Step - 13
            phoneorder.Click_PaidAmtLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Uparrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 13 - Up arrow is not displayed on paid amount label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.Verify_PaidAmtHeader_Sorted_ascending(), "Test Step - 13 - Paid Amounts are not sorting as ascending order on paid amount label at Unpaid invoice tab on Customer details popup");

            // Test Step - 14
            phoneorder.Click_PaidAmtLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Downarrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 14 - Down arrow is not displayed on paid amount label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.Verify_PaidAmtHeader_Sorted_descending(), "Test Step - 14 - Paid Amounts are not sorting as descending order on paid amount label at Unpaid invoice tab on Customer details popup");

            // Test Step - 15
            phoneorder.Click_PaidAmtLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup();
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_PaidAmtHeader_Sorted_ascending(), "Test Step - 15 - Paid Amounts are not sorting as default order on Paid amount label at Unpaid invoice tab on Customer details popup");

            // Test Step - 16
            phoneorder.Click_BalanceAmtLabel_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Uparrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(),"Test Step - 16 - Up arrow is not displayed on balance amount label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.Verify_BalanceAmtHeader_Sorted_ascending(), "Test Step - 16 - Balance Amounts are not sorting as ascending order on balance amount label at Unpaid invoice tab on Customer details popup");

            // Test Step - 17
            phoneorder.Click_BalanceAmtLabel_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Downarrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(),"Test Step - 17 - Down arrow is not displayed on Balance amount label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.Verify_BalanceAmtHeader_Sorted_descending(), "Test Step - 17 - Balance Amounts are not sorting as descending order on balance amount label at Unpaid invoice tab on Customer details popup");

            // Test Step - 18
            phoneorder.Click_BalanceAmtLabel_OnCustomerDetailsPopup();
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_BalanceAmtHeader_Sorted_ascending(), "Test Step - 18 -  Balance Amounts are not sorting as default order on Balance Amount label at Unpaid invoice tab on Customer details popup");

            // Test Step - 19
            phoneorder.Click_ProductTotalLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Uparrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 19 - Up arrow is not displayed on product total label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.Verify_ProductTotalHeader_Sorted_ascending(), "Test Step - 19 - Product Totals are not sorting as ascending order on product total label at Unpaid invoice tab on Customer details popup");

            // Test Step - 20
            phoneorder.Click_ProductTotalLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Downarrow_Appears_OnProductTotalLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 20 - Down arrow is not displayed on Product total label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.Verify_ProductTotalHeader_Sorted_descending(), "Test Step - 20 - Product Totals are not sorting as descending order on product total label at Unpaid invoice tab on Customer details popup");

            // Test Step - 21
            phoneorder.Click_ProductTotalLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_ProductTotalHeader_Sorted_ascending(), "Test Step - 21 - Product Totals are not sorting as default order on product total label at Unpaid invoice tab on Customer details popup");

            // Test Step - 22
            phoneorder.Click_DeliveryLabel_OnUnpaidTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Uparrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 22 - Up arrow is not displayed on product total label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.Verify_DeliveryHeader_Sorted_ascending(), "Test Step - 22 - Product Totals are not sorting as ascending order on product total label at Unpaid invoice tab on Customer details popup");

            // Test Step - 23
            phoneorder.Click_DeliveryLabel_OnUnpaidTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Downarrow_Appears_OnProductTotalLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 23 - Down arrow is not displayed on delivery label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.Verify_DeliveryHeader_Sorted_descending(), "Test Step - 23 - Delivery are not sorting as descending order on product total label at Unpaid invoice tab on Customer details popup");

            // Test Step - 24
            phoneorder.Click_DeliveryLabel_OnUnpaidTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_DeliveryHeader_DefaultOrder(), "Test Step - 24 - Delivery Amounts are not sorting as default order on delivery label at Unpaid invoice tab on Customer details popup");

            // Test Step - 25
            phoneorder.Click_TaxLabel_OnUnpaidTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Uparrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 25 - Up arrow is not displayed on tax label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.Verify_TaxHeader_Sorted_ascending(), "Test Step - 25 - Tax are not sorting as ascending order on tax label at Unpaid invoice tab on Customer details popup");

            // Test Step - 26
            delayWithGivenTime(1000);
            phoneorder.Click_TaxLabel_OnUnpaidTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Downarrow_Appears_OnProductTotalLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 26 - Down arrow is not displayed on tax label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.verify_TaxHeader_Sorted_Descending(), "Test Step - 26 - Tax are not sorting as descending order on tax label at Unpaid invoice tab on Customer details popup");

            // Test Step - 27
            delayWithGivenTime(1000);
            phoneorder.Click_TaxLabel_OnUnpaidTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_TaxHeader_Sorted_ascending(), "Test Step - 27 - Tax are not sorting as default order on tax label at Unpaid invoice tab on Customer details popup");

            // Test Step - 28
            phoneorder.Click_LateFee_Label_OnUnpaidTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Uparrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 28 - Up arrow is not displayed on late fee label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.Verify_LatefeeHeader_Sorted_ascending(), "Test Step - 28 - Late fees are not sorting as ascending order on late fee label at Unpaid invoice tab on Customer details popup");

            // Test Step - 29
            phoneorder.Click_LateFee_Label_OnUnpaidTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Downarrow_Appears_OnProductTotalLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 29 - Down arrow is not displayed on late fee label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.verify_LatefeeHeader_Sorted_Descending(), "Test Step - 29 - Late fees are not sorting as descending order on late fee label at Unpaid invoice tab on Customer details popup");

            //Test Step - 30
            phoneorder.Click_LateFee_Label_OnUnpaidTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_LatefeeHeader_Sorted_ascending(), "Test Step - 30 - Late fees are not sorting as ascending order on tax label at Unpaid invoice tab on Customer details popup");

            // Test Step - 31
            phoneorder.Click_Total_Label_OnUnpaidTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Uparrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 31 - Up arrow is not displayed on total label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.Verify_TotalHeader_Sorted_ascending(), "Test Step - 31 - Total amounts are not sorting as ascending order on total label at Unpaid invoice tab on Customer details popup");

            // Test Step - 32
            phoneorder.Click_Total_Label_OnUnpaidTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Downarrow_Appears_OnProductTotalLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 32 - Down arrow is not displayed on total label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.verify_TotalHeader_Sorted_Descending(), "Test Step - 32 - Total amounts are not sorting as descending order on total label at Unpaid invoice tab on Customer details popup");

            //Test Step - 33
            phoneorder.Click_Total_Label_OnUnpaidTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_TotalHeader_Sorted_ascending(), "Test Step - 33 - Total amounts are not sorting as default order on total label at Unpaid invoice tab on Customer details popup");

            // Test Step - 34
            phoneorder.Click_OrderDate_Label_OnUnpaidTab_OnCustomerDetailsPopup();
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_Uparrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 34 - Up arrow is not displayed on order date label at Unpaid invoice tab on Customer details popup");
            //    softassert.assertTrue(phoneorder.Verify_OrderDateHeader_Sorted_ascending(), "Test Step - 34 - Order dates are not sorting as ascending order on order date label at Unpaid invoice tab on Customer details popup");

            // Test Step - 35
            phoneorder.Click_OrderDate_Label_OnUnpaidTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Downarrow_Appears_OnProductTotalLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 35 - Down arrow is not displayed on late fee label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.verify_OrderDateHeader_Sorted_Descending(), "Test Step - 35 - Order dates are not sorting as descending order on Order date label at Unpaid invoice tab on Customer details popup");

            //Test Step - 36
            phoneorder.Click_OrderDate_Label_OnUnpaidTab_OnCustomerDetailsPopup();
            delayWithGivenTime(2000);
            //  softassert.assertTrue(phoneorder.Verify_OrderDateHeader_Sorted_ascending(), "Test Step - 36 - Order Date are not sorting as default order on order date label at Unpaid invoice tab on Customer details popup");

            // Test Step - 37
            phoneorder.Click_DeliveryDate_Label_OnUnpaidTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Uparrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 37 - Up arrow is not displayed on delivery date label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.Verify_DeliveryDateHeader_Sorted_ascending(), "Test Step - 37 - Delivery date are not sorting as ascending order on delivery date label at Unpaid invoice tab on Customer details popup");

            // Test Step - 38
            phoneorder.Click_DeliveryDate_Label_OnUnpaidTab_OnCustomerDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_Downarrow_Appears_OnProductTotalLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 38 - Down arrow is not displayed on delivery date label at Unpaid invoice tab on Customer details popup");
            softassert.assertTrue(phoneorder.verify_DeliveryDateHeader_Sorted_Descending(), "Test Step - 38 - Delivery date are not sorting as descending order on deliev label at Unpaid invoice tab on Customer details popup");

            //Test Step - 39
            phoneorder.Click_DeliveryDate_Label_OnUnpaidTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_DeliveryDateHeader_Sorted_ascending(), "Test Step - 39 - Deivery dates are not sorting as default order on delivery date label at Unpaid invoice tab on Customer details popup");

        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}