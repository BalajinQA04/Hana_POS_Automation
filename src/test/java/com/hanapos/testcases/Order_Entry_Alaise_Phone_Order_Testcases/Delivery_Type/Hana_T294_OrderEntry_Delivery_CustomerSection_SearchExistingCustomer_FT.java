package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Delivery_Type;

import java.io.IOException;

import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T294_OrderEntry_Delivery_CustomerSection_SearchExistingCustomer_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    public static final String dataSheetName = "Hana_T85";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Phone Order Module - Delivery Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T294_OrderEntry_Delivery_CustomerSection_SearchExistingCustomer_Functionality_Test(String salesperson, String customername, String searchandselectcust, String invalidsearchandselect, String searchandselectwith3char) {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T294_OrderEntry_Delivery_CustomerSection_SearchExistingCustomer_Functionality_Test  ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Cash and carry option is not displayed");

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
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            softassert.assertTrue(phoneorder.VerifyCustSectionAppears(), "Test Step - 6 - Customer section is not displayed on phone order page");

            // Test Step - 7
            phoneorder.SearchAndSelect_CustomerOnCust_Section(prop.getProperty("cust_firstName"), customername);
            delayWithGivenTime(2000);

            // Test Step - 8
            phoneorder.ClickOn_CustClearBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.VerifyAutosuggestion_Displayed_On_Cust_Section("@#$$!$#"), "Autosuggestion is displayed on customer section");
            delayWithGivenTime(2000);

            // Test Step - 9
            phoneorder.ClickOn_CustClearBtn();
            delayWithGivenTime(2000);
            phoneorder.SearchAndSelect_CustomerOnCust_Section(prop.getProperty("cust_firstName"), searchandselectcust);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), prop.getProperty("cust_firstName"), "Test Step - 9 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), prop.getProperty("cust_lastName"), "Test Step - 9 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), prop.getProperty("cust_companyName"), "Test Step - 9 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), prop.getProperty("cust_email"), "Test Step - 9 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), prop.getProperty("cust_address1"), "Test Step - 9 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 9 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), prop.getProperty("cust_zipcode"), "Test Step - 9 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), prop.getProperty("cust_city"), "Test Step - 9 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_phoneNumber"), "Test Step - 9 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_Alt_phoneNumber"), "Test Step - 9 - Alt phone number is not displayed on phone order page");

            // Test Step - 10
            softassert.assertFalse(phoneorder.VerifyFnameField_On_CustSection_IsDisabled(), "Test STep - 10 - First name field is not disabled");
            softassert.assertFalse(phoneorder.VerifyLnameField_On_CustSection_IsDisabled(), "Test Step - 10 - Last name field is not disabled");
            softassert.assertFalse(phoneorder.VerifyCompanyNameField_On_CustSection_IsDisabled(), "Test Step - 10 - Company name field is not disabled");
            softassert.assertFalse(phoneorder.VerifyEmailIdField_On_CustSection_IsDisabled(), "Test Step - 10 - Email id field is not disabled");
            softassert.assertFalse(phoneorder.VerifyAddress1Field_On_CustSection_IsDisabled(), "Test Step - 10 - Address 1 field is not disabled");
            softassert.assertFalse(phoneorder.VerifyZipcodeField_On_CustSection_IsDisabled(), "Test Step - 10 - Zip code field is not disabled");
            softassert.assertFalse(phoneorder.VerifyCityField_On_CustSection_IsDisabled(), "Test Step - 10 - City field is not disabled");
            softassert.assertFalse(phoneorder.VerifyPhonenumberField_On_CustSection_IsDisabled(), "Test Step - 10 - Phone number 1 field is not disabled");
            softassert.assertFalse(phoneorder.VerifyAltPhonenumberField_On_CustSection_IsDisabled(), "Test Step - 10 - Alt phone number field is not disabled");
        } catch (Exception e) {
            e.printStackTrace();
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}