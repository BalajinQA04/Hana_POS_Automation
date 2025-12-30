package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireOut_Type;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

public class Hana_T561_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_Sorting_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    @Epic("Phone Order Module - Wire out Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T561_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_Sorting_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting Validate_Hana_T561_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_Sorting_Functionality_Test  ****");
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
            phoneorder.Click_CustEditIcon();
            softassert.assertTrue(phoneorder.Verify_CustomerDetailsPopupAppears(), "Test Step - 8 - Customer details popup on is not displayed on phone order page");

            // Test Step - 8
            delayWithGivenTime(1000);
            phoneorder.ClickOn_PaymentDetails_CustomerDetailsPopup();
            delayWithGivenTime(4000);
            softassert.assertTrue(phoneorder.Verify_TableGridOnPaymentDetailsTab_IsAppear(), "Test Step - 8 - payment details tab webtable grid is not displayed");

            // Test Step - 9
            delayWithGivenTime(2000);
            phoneorder.Click_InvoiceHeader_PaymentDetails();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_Uparrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 9 - Invoice header tab upward arrow icon is not displayed");
            softassert.assertTrue(phoneorder.Verify_InvoiceHeader_Sorted_ascending_PaymentDetailsTab(), "Test Step - 9 -  Invoices are not sorting as ascending order on Invoice label at payment details tab on Customer details popup");

            // Test Step - 10
            delayWithGivenTime(2000);
            phoneorder.Click_InvoiceHeader_PaymentDetails();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_Downarrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 10 - Invoice header tab downward arrow icon is not displayed on webtable grid");
            softassert.assertTrue(phoneorder.Verify_InvoiceHeader_Sorted_descending_PaymentDetailsTab(), "Test Step - 10 -  Invoices are not sorting as ascending order on Invoice label at payment details tab on Customer details popup");

            // Test Step - 11
            phoneorder.Click_InvoiceHeader_PaymentDetails();
            //delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_InvoiceHeader_DefaultOrder_PaymentDetailsTab(), "Test Step - 11 - Invoices are not sorting as default order on Invoice label at payment details tab on Customer details popup");

            // Test Step - 12
            phoneorder.Click_PayAmountHeader_PaymentDetails();
            //delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_Uparrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 12 - Pay Amount header tab upward arrow icon is not displayed");
            softassert.assertTrue(phoneorder.Verify_PayAmountHeader_Sorted_ascending_PaymentDetailsTab(), "Test Step - 12 -  Pay Amounts are not sorting as ascending order on Pay amount label at payment details tab on Customer details popup");

            // Test Step - 13
            phoneorder.Click_PayAmountHeader_PaymentDetails();
            //delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_Downarrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 13 - Pay Amount header tab downward arrow icon is not displayed on webtable grid");
            softassert.assertTrue(phoneorder.Verify_PayAmountHeader_Sorted_descending_PaymentDetailsTab(), "Test Step - 13 -  Pay Amounts are not sorting as ascending order on pay amount label at payment details tab on Customer details popup");

            // Test Step - 14
            phoneorder.Click_PayAmountHeader_PaymentDetails();
            //delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_PayAmountHeader_DefaultOrder_PaymentDetailsTab(), "Test Step - 14 - Pay Amounts are not sorting as default order on Pay Amount label at payment details tab on Customer details popup");

            // Test Step - 15
            phoneorder.Click_PayDateHeader_PaymentDetails();
            //delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_Uparrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 15 - Pay Date header tab upward arrow icon is not displayed");
            softassert.assertTrue(phoneorder.Verify_PayDateHeader_Sorted_ascending_PaymentDetailsTab(), "Test Step - 15 - Pay Dates are not sorting as ascending order on Pay Date label at payment details tab on Customer details popup");

            // Test Step - 16
            phoneorder.Click_PayDateHeader_PaymentDetails();
            //delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_Downarrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 16 - Pay Date header tab downward arrow icon is not displayed on webtable grid");
            softassert.assertTrue(phoneorder.Verify_PayDateHeader_Sorted_descending_PaymentDetailsTab(), "Test Step - 16 -  Pay Date are not sorting as descending order on pay date label at payment details tab on Customer details popup");

            // Test Step - 17
            phoneorder.Click_PayDateHeader_PaymentDetails();
            //delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_PayDateHeader_DefaultOrder_PaymentDetailsTab(), "Test Step - 17 - Pay Date are not sorting as default order on Pay date label at payment details tab on Customer details popup");

            // Test Step - 18
            phoneorder.Click_MOPHeader_PaymentDetails();
            //delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_Uparrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 18 - MOP header tab upward arrow icon is not displayed");
            softassert.assertTrue(phoneorder.Verify_MOPHeader_Sorted_ascending_PaymentDetailsTab(), "Test Step - 18 - MOP are not sorting as ascending order on MOP label at payment details tab on Customer details popup");

            // Test Step - 19
            phoneorder.Click_MOPHeader_PaymentDetails();
            //delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_Downarrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 19 - MOP header tab downward arrow icon is not displayed on webtable grid");
            softassert.assertTrue(phoneorder.Verify_MOPHeader_Sorted_descending_PaymentDetailsTab(), "Test Step - 19 -  MOP are not sorting as descending order on mop label at payment details tab on Customer details popup");

            // Test Step - 20
            phoneorder.Click_MOPHeader_PaymentDetails();
            //delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_MOPHeader_DefaultOrder_PaymentDetailsTab(), "Test Step - 20 - MOP are not sorting as default order on MOP label at payment details tab on Customer details popup");

            // Test Step - 21
            phoneorder.Click_CheckNoHeader_PaymentDetails();
            //delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_Uparrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 21 - Check Number header tab upward arrow icon is not displayed");
            softassert.assertTrue(phoneorder.Verify_CheckNoHeader_Sorted_ascending_PaymentDetailsTab(), "Test Step - 21 - Check Number are not sorting as ascending order on check number label at payment details tab on Customer details popup");

            // Test Step - 22
            phoneorder.Click_CheckNoHeader_PaymentDetails();
            //delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_Downarrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 22 - Check Number header tab downward arrow icon is not displayed on webtable grid");
            softassert.assertTrue(phoneorder.Verify_CheckNoHeader_Sorted_descending_PaymentDetailsTab(), "Test Step - 22 -  Check Number are not sorting as descending order on check number label at payment details tab on Customer details popup");

            // Test Step - 23
            phoneorder.Click_CheckNoHeader_PaymentDetails();
            //delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_CheckNoHeader_DefaultOrder_PaymentDetailsTab(), "Test Step - 23 - Check Number are not sorting as default order on check number label at payment details tab on Customer details popup");

            // Test Step - 24
            phoneorder.Click_CCNoHeader_PaymentDetails();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_Uparrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 24 - Check Number header tab upward arrow icon is not displayed");
            softassert.assertTrue(phoneorder.Verify_CCNoHeader_Sorted_ascending_PaymentDetailsTab(), "Test Step - 24 - Check Number are not sorting as ascending order on check number label at payment details tab on Customer details popup");

            // Test Step - 25
            phoneorder.Click_CCNoHeader_PaymentDetails();
            //delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_Downarrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup(), "Test Step - 25 - Check Number header tab downward arrow icon is not displayed on webtable grid");
            softassert.assertTrue(phoneorder.Verify_CCNoHeader_Sorted_descending_PaymentDetailsTab(), "Test Step - 25 -  Check Number are not sorting as descending order on check number label at payment details tab on Customer details popup");

            // Test Step - 26
            phoneorder.Click_CCNoHeader_PaymentDetails();
            //delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_CCNoHeader_DefaultOrder_PaymentDetailsTab(), "Test Step - 26 - Check Number are not sorting as default order on check number label at payment details tab on Customer details popup");

        } catch (NoSuchElementException e) {
            softassert.fail("Element not found - " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}