package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireOut_Type;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T542_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_ProductTotal_Search_Filter_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    public static final String dataSheetName = "Hana_T108";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Phone Order Module - Wire out Type")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T542_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_ProductTotal_Search_Filter_Functionality_Test(String valid_prodtotal_amount, String twodigit_prodtotal_amount, String invalid_prodtotal_amount) {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting Validate_Hana_T542_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_ProductTotal_Search_Filter_Functionality_Test  ****");
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
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
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
            phoneorder.Enter_ProductTotal_OnInvoiceSearchBox_UnpaidInvoiceTab(valid_prodtotal_amount);

            // Test Step - 11
            RobotPressEnter();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_ProductTotalValue_OnDisplayedInvoices(valid_prodtotal_amount), "Test Step - 11 - Product total value is not displayed in unpaid invoice grid table");
            delayWithGivenTime(1000);

            // Test Step - 12
            phoneorder.Enter_ProductTotal_OnInvoiceSearchBox_UnpaidInvoiceTab(twodigit_prodtotal_amount);

            // Test Step - 13
            RobotPressEnter();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_ProductTotalValue_OnDisplayedInvoices(twodigit_prodtotal_amount), "Test Step - 13 - Product total value is not displayed in unpaid invoice grid table");

            // Test Step - 14
            delayWithGivenTime(1000);
            phoneorder.Enter_ProductTotal_OnInvoiceSearchBox_UnpaidInvoiceTab(invalid_prodtotal_amount);
            delayWithGivenTime(1000);
            RobotPressEnter();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_NoUnpaidInvoiceFound(), "Test step - 14 - No unpaid invoice found message is not displayed in unpaid invoice grid table");

            // Test Step - 15
            softassert.assertTrue(phoneorder.Verify_ClearButton_AppearsOnProductTotalSearchtextbox_UnpaidInvoiceTab(), "Test Step - 15 - Clear button is not displayed on invoice search textbox at unpaid invoice tab in customer details popup");

            // Test Step - 16
            delayWithGivenTime(1000);
            phoneorder.Click_Clearbutton_ProductTotalsearchtextbox_UnpaidInvoiceTab();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_EnteredProductTotalSearchtext_UnpaidInvoiceTab(), "", "Test Step - 16 - Entered Product total on search text box is not getting cleared ");
            softassert.assertNotEquals(phoneorder.Verify_ListofInvoiceNumbers_Appears_InUnpaidInvoiceTable_OnUnpaidInvoiceTab(), 0, "Test Step - 16 - Unpaid Invoices are not displayed in unpaid invoice table on unpaid invoice tab customer details popup");
        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}