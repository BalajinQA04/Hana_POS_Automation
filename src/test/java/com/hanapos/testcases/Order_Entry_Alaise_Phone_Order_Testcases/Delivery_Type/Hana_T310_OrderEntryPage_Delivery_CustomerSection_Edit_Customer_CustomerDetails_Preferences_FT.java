package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Delivery_Type;

import java.io.IOException;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T310_OrderEntryPage_Delivery_CustomerSection_Edit_Customer_CustomerDetails_Preferences_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    public static LoggerUtil logger_Util;
    public static final String dataSheetName = "Hana_T103";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Phone Order Module - Delivery Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T310_OrderEntryPage_Delivery_CustomerSection_Edit_Customer_CustomerDetails_Preferences_Functionality_Test(String salesperson, String taxtype, String taxid, String cardmessage, String discountpercentage, String discountamount, String deliveryfee, String ponumber,
                                                                                                                                        String paymenttype, String paymentterms, String specialinstruction, String paymentType, String taxType, String taxId, String cardMessage, String discPercentage, String PaymentType, String PaymentTerms) {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();

        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
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
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana dashboard page is not displayed");
            logger.info("User navigated to hana dashboard page");

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
            softassert.assertTrue(phoneorder.VerifyCustSectionAppears(), "Test Step - 6 - Customer section is not displayed on phone order page");

            // Test Step - 7
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            delayWithGivenTime(2000);
            phoneorder.SearchAndSelect_CustomerOnCust_Section(prop.getProperty("cust_firstName"), "Abish David");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), "Abish", "Test Step - 7 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), "David", "Test Step - 7 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), "Hana_Sisterchicks", "Test Step - 7 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), "hanaposqateam@gmail.com", "Test Step -7 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "3402 Park Blvd", "Test Step - 7 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 7- Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "92103", "Test Step -7 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "San Diego", "Test Step -7 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 7 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 7 - Alt phone number is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 8
            softassert.assertTrue(phoneorder.Verify_CustEditIcon_Appears(), "Test Step - 8 - Customer clear button is not displayed on phone order page");
            phoneorder.Click_CustEditIcon();
            softassert.assertTrue(phoneorder.Verify_CustomerDetailsPopupAppears(), "Test Step - 9 - Customer details popup on is not displayed on phone order page");

            // Test Step - 9
            phoneorder.Click_PreferencesTab_OnCustDetailsPopup();
            delayWithGivenTime(1000);

            // Test Step - 10
            phoneorder.Select_TaxType_PreferencesTab_OnCustDetailsPopup(taxtype);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_TaxType_PreferencesTab_OnCustDetailsPopup(), "Standard", "Test Step - 10 - Customer details popup Tax type is not displayed on phone order page");

            // Test Step - 11
            phoneorder.Enter_TexId_PreferenceTab_OnCustDetailsPopup(taxid);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_TexId_PreferenceTab_OnCustDetailsPopup(), "ST1234", "Test Step - 11 - Customer details popup Tax id is not displayed on phone order page");

            // Test Step - 12
            phoneorder.Enter_CardMessage_PreferenceTab_OnCustDetailsPopup(cardmessage);
            softassert.assertEquals(phoneorder.get_CardMessage_PreferenceTab_OnCustDetailsPopup(), "Hello !!! Card Message", "Test Step - 12 - Customer details popup Card message is not displayed on phone order page");

            // Test Step - 13
            phoneorder.Enter_DiscountPercentage_PreferenceTab_OnCustDetailsPopup(discountpercentage);
            softassert.assertEquals(phoneorder.get_DiscountPercentage_PreferenceTab_OnCustDetailsPopup(), "10", "Test Step - 13 - Customer details popup Discount is not displayed on phone order page");

            // Test Step - 14
            phoneorder.Click_DiscountAmount_PreferenceTab_OnCustDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_DiscountAmount_PreferenceTab_OnCustDetailsPopup(), "0.00", "Test Step - 14 - Customer details popup Discount amount is not displayed on phone order page");


            //Test Step - 15
            phoneorder.Enter_DiscountAmount_PreferenceTab_OnCustDetailsPopup(discountamount);
            softassert.assertEquals(phoneorder.get_DiscountAmount_PreferenceTab_OnCustDetailsPopup(), "10.00", "Test Step - 15 - Customer details popup Discount amount is not displayed on phone order page");

            // Test Step - 16
            phoneorder.Click_DiscountPercentage_PreferenceTab_OnCustDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_DiscountPercentage_PreferenceTab_OnCustDetailsPopup(), "0", "Test Step - 16 - Customer details popup Discount is not displayed on phone order page");

            //Test Step - 17
            phoneorder.Enter_DeliveryFee_PreferenceTab_OnCustDetailsPopup(deliveryfee);
            softassert.assertEquals(phoneorder.get_DeliveryFee_PreferenceTab_OnCustDetailsPopup(), "30.00", "Test Step - 17 - Customer details popup Delivery fee is not displayed on phone order page");

            //Test Step - 18
            phoneorder.Enter_PONumber_PreferenceTab_OnCustDetailsPopup(ponumber);
            softassert.assertEquals(phoneorder.get_PONumber_PreferenceTab_OnCustDetailsPopup(), "PO2024AD", "Test Step - 18 - Customer details popup PO number is not displayed on phone order page");

            //Test Step - 19
            phoneorder.Select_PaymentType_PreferenceTab_OnCustDetailsPopup(paymenttype);
            softassert.assertEquals(phoneorder.get_PaymentType_PreferenceTab_OnCustDetailsPopup(), "Cash", "Test Step - 19 - Customer details popup Payment type is not displayed on phone order page");

            //Test Step - 20
            phoneorder.Select_PaymentTerms_PreferenceTab_OnCustDetailsPopup(paymentterms);
            softassert.assertEquals(phoneorder.get_PaymentTerms_PreferenceTab_OnCustDetailsPopup(), "Net 15", "Test Step - 20 - Customer details popup Payment terms is not displayed on phone order page");

            // Test Step - 21
            phoneorder.Enter_SpecialInstruction_PreferenceTab_OnCustDetailsPopup(specialinstruction);
            softassert.assertEquals(phoneorder.get_SpecialInstruction_PreferenceTab_OnCustDetailsPopup(), "Hello !!! Special Instruction", "Test Step - 21 - Customer details popup Special instruction is not displayed on phone order page");

            // Test Step - 22
            phoneorder.Click_UpdateCustDefaultBtn_PreferenceTab_OnCustDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_CustDefaultSucessMsg_PreferenceTab_OnCustDetailsPopup(), "Test Step - 22 - Customer details popup success message is not displayed on phone order page");

            if (phoneorder.Verify_ConfirmationAlert_Preference_OnCustDetailsPopup() == true) {
                phoneorder.Click_ConfirmationAlert_NotNeeded_Button_Preference_OnCustDetailsPopup();
                //  softassert.fail("*** Test Step - 22 - Unwanted this confirmation alert is displayed ***");
            } else {
                softassert.assertTrue(true, "Test Step - 22 - Customer details popup is not displayed on phone order page");
            }

            // Test Step - 23
            delayWithGivenTime(2000);
            phoneorder.SearchAndSelect_CustomerOnCust_Section(prop.getProperty("cust_firstName"), "Abish David");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), "Abish", "Test Step - 23 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), "David", "Test Step - 23 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), "Hana_Sisterchicks", "Test Step - 23 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), "hanaposqateam@gmail.com", "Test Step -23 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "3402 Park Blvd", "Test Step - 23 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 23 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "92103", "Test Step - 23 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "San Diego", "Test Step - 23 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 23 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 23 - Alt phone number is not displayed on phone order page");


            // Test Step - 24
            delayWithGivenTime(3000);
            softassert.assertEquals(phoneorder.get_SpecialInstruction_OnPhoneorderpage(), "Hello !!! Special Instruction",
                    "Test Step - 24 - Special instruction of phone order page and customer details popup at preference tab updated special instructions fields are not matched");

            // Test Step - 25
            softassert.assertEquals(phoneorder.getDisplayedPaymentTypeSelectedOption(), "Cash",
                    "Test Step - 25 - Payment type is on phone order page and customer details at preference tab updated payment type fields are not matched");

            // Test Step - 26
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymentType);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getDisplayedPaymentTypeSelectedOption(), "Invoice/House Account", "Test Step - 26 - Payment type of phone order page and selected payment type fields are not matched");

            // Test Step - 27
            softassert.assertEquals(phoneorder.get_PONumber_InvoiceHouseAccount_OnPhoneorderpage(), "PO2024AD",
                    "Test Step - 27 - PO number of payment type as Invoice House Account onphone order page and customer details popup at preference tab updated PO number fields are not matched");

            // Test Step - 28
            softassert.assertEquals(phoneorder.get_PaymentTerms_InvoiceHouseAccount_OnPhoneorderpage(), "15",
                    "Test Step - 28 - Payment terms of phone order page and customer details popup at preference tab updated payment terms fields are not matched");

            // Test Step - 29
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_DeliveryFee_InvoiceHouseAccount_OnPhoneorderpage(), "30.00",
                    "Test Step - 29 - Delivery fee of phone order page and customer details popup at preference tab updated delivery fee fields are not matched");

            // Test Step - 30
            softassert.assertEquals(phoneorder.get_PaymentSection_DiscountAmount_OnPhoneOrderPage(), "10.00",
                    "Test Step - 30 - Discount amount at payment section on phone order page and customer details popup at preference tab updated discount amount fields are not matched");

            // Test Step - 31
            softassert.assertEquals(phoneorder.getEnteredViewShortCode(), "Hello !!! Card Message",
                    "Test Step - 31 - Card message of phone order page and customer details popup at preference tab updated card message fields are not matched");

            // Test Step - 32
            softassert.assertEquals(phoneorder.get_TaxId_OnPhoneOrderPage(), "ST1234",
                    "Test Step - 31 - Tax id of phone order page and customer details popup at preference tab updated tax id fields are not matched");

            // Test Step - 33
            softassert.assertEquals(phoneorder.get_TaxType_OnPhoneOrderPage(), "Standard Tax",
                    "Test Step - 33 - Tax type of phone order page and customer details popup at preference tab updated tax type fields are not matched");

            delayWithGivenTime(2000);
            //Set previously updated value
            softassert.assertTrue(phoneorder.Verify_CustEditIcon_Appears(), "Test Step - 8 - Customer clear button is not displayed on phone order page");
            phoneorder.Click_CustEditIcon();
            softassert.assertTrue(phoneorder.Verify_CustomerDetailsPopupAppears(), "Test Step - 9 - Customer details popup on is not displayed on phone order page");

            phoneorder.Click_PreferencesTab_OnCustDetailsPopup();
            delayWithGivenTime(1000);

            phoneorder.Select_TaxType_PreferencesTab_OnCustDetailsPopup(taxType);

            phoneorder.Enter_TexId_PreferenceTab_OnCustDetailsPopup(taxId);

            phoneorder.Enter_CardMessage_PreferenceTab_OnCustDetailsPopup(cardMessage);

            phoneorder.Enter_DiscountPercentage_PreferenceTab_OnCustDetailsPopup(discPercentage);

            phoneorder.Click_DiscountAmount_PreferenceTab_OnCustDetailsPopup();

            phoneorder.Enter_DeliveryFee_PreferenceTab_OnCustDetailsPopup("");

            phoneorder.Enter_PONumber_PreferenceTab_OnCustDetailsPopup("");

            phoneorder.Select_PaymentType_PreferenceTab_OnCustDetailsPopup(PaymentType);

            phoneorder.Select_PaymentTerms_PreferenceTab_OnCustDetailsPopup(PaymentTerms);

            phoneorder.Enter_SpecialInstruction_PreferenceTab_OnCustDetailsPopup("");

            phoneorder.Click_UpdateCustDefaultBtn_PreferenceTab_OnCustDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_CustDefaultSucessMsg_PreferenceTab_OnCustDetailsPopup(), "Test Step - 22 - Customer details popup success message is not displayed on phone order page");


        } catch (Exception e) {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
            softassert.fail(e.getMessage());
        } finally {
            try {
                softassert.assertAll();
            } catch (AssertionError ae) {
                logger_Util = new LoggerUtil();
                logger_Util.attachNetworkLogs(testCaseName);
                ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
                throw ae;
            }
        }
    }
}