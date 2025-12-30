package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

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

public class Hana_T123_Pickup_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_MOPSearchFilter_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    public static final String dataSheetName = "Hana_T123";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Phone Order Module - Pickup Type")
    //,dataProvider="fetch_Excel_Data"
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T123_Pickup_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_MOPSearchFilter_FT_Functionality_Test(String validMOP, String three_digit_MOP, String invalid_MOP) {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting Hana_T123_OrderEntryPage_Pickup_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_MOPSearchFilter_FT  ****");
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
            phoneorder.Click_CustEditIcon();
            softassert.assertTrue(phoneorder.Verify_CustomerDetailsPopupAppears(), "Test Step - 8 - Customer details popup on is not displayed on phone order page");

            // Test Step - 8
            delayWithGivenTime(1000);
            phoneorder.ClickOn_PaymentDetails_CustomerDetailsPopup();
            ThreadWait(4000);
            softassert.assertTrue(phoneorder.Verify_TableGridOnPaymentDetailsTab_IsAppear(), "Test Step - 9 - payment details tab webtable grid is not displayed");

            // Test Step - 9
            ThreadWait(1000);
            phoneorder.Enter_MOPSearchBox_OnPaymentDetailsTab(validMOP);
            softassert.assertEquals(phoneorder.get_MOPSearchBox_Paymentdetailstab(), validMOP, "Test Step - 9 - In Mode Of Payment searchbox entered data is not displayed on the payment details tab at customer details popup");

            // Test Step - 10
            RobotPressEnter();
            softassert.assertTrue(phoneorder.Verify_MOP_Paymentdetailstab(validMOP), "Test Step - 10 - Entered Mode Of Payment is not displayed on the payment details tab at customer details popup");

            // Test Step - 11
            phoneorder.Enter_MOPSearchBox_OnPaymentDetailsTab(three_digit_MOP);
            softassert.assertEquals(phoneorder.get_MOPSearchBox_Paymentdetailstab(), three_digit_MOP, "Test Step - 11 - In Mode Of Payment searchbox entered data is not displayed on the payment details tab at customer details popup");

            // Test Step - 12
            RobotPressEnter();
            softassert.assertTrue(phoneorder.Verify_MOP_Paymentdetailstab(validMOP), "Test Step - 12 - Entered Mode Of Payment is not displayed on the payment details tab at customer details popup");

            // Test Step - 13
            phoneorder.Enter_MOPSearchBox_OnPaymentDetailsTab(invalid_MOP);
            softassert.assertEquals(phoneorder.get_MOPSearchBox_Paymentdetailstab(), invalid_MOP, "Test Step - 13 - In Mode Of Payment searchbox entered data is not displayed on the payment details tab at customer details popup");

            RobotPressEnter();
            softassert.assertTrue(phoneorder.Verify_NoCustomerPaymentsFound_PaymentDetailsTab(), "Test Step - 13 - Entered invalid Mode Of Payment is displayed on the payment details tab at customer details popup");

            // Test Step - 14
            softassert.assertTrue(phoneorder.Verify_MOPSearchBox_ClearButton_Appears(), "Test Step - 14 - Clear button in Mode Of Payment search box is not displayed on the payment details tab at customer details popup");

            // Test Step - 15
            phoneorder.Click_MOPSearchBox_ClearButton();
            softassert.assertEquals(phoneorder.get_MOPSearchBox_Paymentdetailstab(), "", "Test Step - 15 - In Mode Of Payment searchbox entered data is not cleared on the payment details tab at customer details popup");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}