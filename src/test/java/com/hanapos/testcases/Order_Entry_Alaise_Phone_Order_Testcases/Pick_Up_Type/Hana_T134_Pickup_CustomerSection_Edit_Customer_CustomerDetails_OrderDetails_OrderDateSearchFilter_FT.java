package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T134_Pickup_CustomerSection_Edit_Customer_CustomerDetails_OrderDetails_OrderDateSearchFilter_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    @Epic("Phone Order Module - Pickup Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T134_Pickup_CustomerSection_Edit_Customer_CustomerDetails_OrderDetails_OrderDateSearchFilter_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting Hana_T134_Pickup_CustomerSection_Edit_Customer_CustomerDetails_OrderDetails_OrderDateSearchFilter_FT  ****");
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

            // Test Step - 8
            phoneorder.Click_CustEditIcon();
            softassert.assertTrue(phoneorder.Verify_CustomerDetailsPopupAppears(), "Test Step - 8 - Customer details popup on is not displayed on phone order page");

            // Test Step - 9
            delayWithGivenTime(1000);
            phoneorder.Click_OrderDetailsTab_CustomerDetailsPopup();
            delayWithGivenTime(4000);
            softassert.assertTrue(phoneorder.Verify_OrderDetailsTableGrid_IsAppears_OrderDetailsTab_CustomerDetailsPopup(), "Test Step - 9 - Order details tab webtable grid is not displayed");

            // Test Step - 10
            ThreadWait(1000);
            phoneorder.Enter_OrderDate_OrderDetailsTab_CustomerDetailsPopup(CurrentDate());
            softassert.assertEquals(phoneorder.get_OrderDate_Orderdetailstab(), CurrentDate(), "Test Step - 10 - Order date search box entered data is not displayed in order details tab at customer details popup");

            // Test Step - 11
            RobotPressEnter();
            ThreadWait(1500);
            softassert.assertTrue(phoneorder.Verify_OrderDate_Orderdetailstab(CurrentDate()), "Test Step - 11 - Filtered Order Date is not displayed on the order details tab at customer details popup");

            // Test Step - 12
            ThreadWait(2000);
            phoneorder.Enter_OrderDate_OrderDetailsTab_CustomerDetailsPopup(CurrentDate());
            softassert.assertEquals(phoneorder.get_OrderDate_Orderdetailstab(), CurrentDate(), "Test Step - 12 - Order date search box entered data is not displayed in order details tab at customer details popup");

            // Test Step - 13
            RobotPressEnter();
            ThreadWait(2000);
            softassert.assertTrue(phoneorder.Verify_OrderDate_Orderdetailstab(CurrentDate()), "Test Step - 13 - Filtered Order Date with more than one order is not displayed on the order details tab at customer details popup");

            // Test Step - 14
            ThreadWait(2000);
            phoneorder.Enter_OrderDate_OrderDetailsTab_CustomerDetailsPopup(Next_20Days_Date());
            ThreadWait(1000);
            softassert.assertEquals(phoneorder.get_OrderDate_Orderdetailstab(), Next_20Days_Date(), "Test Step - 14 - Order date search box entered data is not displayed in order details tab at customer details popup");

            RobotPressEnter();
            softassert.assertEquals(phoneorder.get_OrderDate_Orderdetailstab(), "", "Known Issue: Test Step - 14 - Filtered Order Date with invalid in Order date search box entered data is not cleared in order details tab at customer details popup");

            // Test Step - 15
            ThreadWait(1000);
            phoneorder.Enter_OrderDate_OrderDetailsTab_CustomerDetailsPopup(NextDate());
            softassert.assertEquals(phoneorder.get_OrderDate_Orderdetailstab(), NextDate(), "Test Step - 15 - Order date search box entered data is not displayed in order details tab at customer details popup");

            ThreadWait(1000);
            RobotPressEnter();
            softassert.assertTrue(phoneorder.Verify_NoCustomerOrdersFoundMessage_Appears_OrderDetailsTab(), "Test Step - 15 - Filtered with invalid order date is not displayed on the message like No Customer Order Found in order details tab at customer details popup");

            // Test Step - 16
            softassert.assertTrue(phoneorder.Verify_Clearbutton_OrderDateSearchBox_OrderDetailsTab(), "Test Step - 16 - Clear button on order date search box is not displayed in order details tab customer details popup");

            // Test Step - 17
            phoneorder.Click_clearbutton_OrderDateSearchBox_OrderDetailsTab();
            softassert.assertEquals(phoneorder.get_OrderDate_Orderdetailstab(), "", "Test Step - 17 - Order date search box entered data is not cleared in order details tab at customer details popup");
            softassert.assertTrue(phoneorder.Verify_AllTheOrderDate_AppearsOn_OrderDetailsTab(), "Test Step - 17 - All the order date are not displayed on order details tab");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}