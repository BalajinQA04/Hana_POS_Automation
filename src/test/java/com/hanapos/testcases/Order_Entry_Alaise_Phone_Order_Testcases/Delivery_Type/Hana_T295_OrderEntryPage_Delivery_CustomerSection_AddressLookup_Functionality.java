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

public class Hana_T295_OrderEntryPage_Delivery_CustomerSection_AddressLookup_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    public static final String dataSheetName = "Hana_T295";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Phone Order Module - Delivery Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T295_OrderEntryPage_Delivery_CustomerSection_AddressLookup_Functionality_Test(String salesperson, String custfname, String custlname, String custcompanyname, String custemail, String custaddress1,
                                                                                                            String customeraddress1, String city_state_country, String searchandselectaddress1, String custphone, String againcustaddress1,
                                                                                                            String againsearchandselectaddress1, String city_state_country1) {
        CustomSoftAssert softassert = new CustomSoftAssert();
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
            softassert.assertTrue(phoneorder.VerifyCustSectionAppears(), "Test Step - 6 - Customer section is not displayed on phone order page");

            // Test Step - 7
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            phoneorder.EnterCustomerFirstName(custfname);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage().contains("Mike"), true, "Test Step - 7 - First name is not displayed on phone order page");

            // Test Step - 8
            phoneorder.EnterCustomerLastName(custlname);
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage().contains("Tyson"), true, "Test Step - 8 - Last name is not displayed on phone order page");

            // Test Step - 9
            phoneorder.EnterCustomerCompanyName(custcompanyname);
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage().contains("Mike tyson ltd"), true, "Test Step - 9 - Company name is not displayed on phone order page");

            // Test Step - 10
            phoneorder.EnterCustomerEmailId(custemail);
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage().contains("hanaposqateam@gmail.com"), true, "Test Step - 10 - Email ID is not displayed on phone order page");

            // Test Step - 11
            phoneorder.EnterAddress1("12345 West Bend Drive");
            delayWithGivenTime(1000);
            phoneorder.verifyCustomerAddressAutosuggestionDisplayed();

            // Test Step - 12
            delayWithGivenTime(1000);
            phoneorder.EnterAddress1("12345 West Bend Drive");
            phoneorder.verifyCustomerAddressAutosuggestionDisplayed();
            delayWithGivenTime(1000);
            phoneorder.searchAndSelect_Address1_CustSection("12345 West Bend Drive");
            delayWithGivenTime(3000);
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "12345 W Bend Dr", "Test Step - 12 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "63128", "Test Step - 12 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "Sappington", "Test Step - 12 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getStateOnPhoneOrderPage(), "MO", "Test Step - 12 - state is not displayed on phone order page");
            phoneorder.EnterPhoneNumber(custphone);

            // Test Step - 13
            delayWithGivenTime(1000);
            phoneorder.EnterAddress1("7372 Hwy O, Perryville, MO, USA");
            delayWithGivenTime(2000);
            phoneorder.searchAndSelect_Address1_CustSection("7372 Hwy O, Perryville, MO, USA");
            delayWithGivenTime(3000);
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "7372 Hwy O", "Test Step - 12 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "63775", "Test Step - 12 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "Perryville", "Test Step - 12 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getStateOnPhoneOrderPage(), "MO", "Test Step - 12 - state is not displayed on phone order page");
            phoneorder.EnterPhoneNumber(custphone);
        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}