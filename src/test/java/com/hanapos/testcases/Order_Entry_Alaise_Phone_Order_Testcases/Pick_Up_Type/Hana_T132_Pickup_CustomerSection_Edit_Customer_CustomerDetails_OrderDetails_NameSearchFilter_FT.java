package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T132_Pickup_CustomerSection_Edit_Customer_CustomerDetails_OrderDetails_NameSearchFilter_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    @Epic("Phone Order Module - Pickup Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T132_Pickup_CustomerSection_Edit_Customer_CustomerDetails_OrderDetails_NameSearchFilter_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting Hana_T132_Pickup_CustomerSection_Edit_Customer_CustomerDetails_OrderDetails_NameSearchFilter_FT  ****");
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
            phoneorder.Click_CustEditIcon();
            softassert.assertTrue(phoneorder.Verify_CustomerDetailsPopupAppears(), "Test Step - 8 - Customer details popup on is not displayed on phone order page");

            // Test Step - 8
            delayWithGivenTime(1000);
            phoneorder.Click_OrderDetailsTab_CustomerDetailsPopup();
            delayWithGivenTime(4000);
            softassert.assertTrue(phoneorder.Verify_OrderDetailsTableGrid_IsAppears_OrderDetailsTab_CustomerDetailsPopup(), "Test Step - 9 - Order details tab webtable grid is not displayed");

            // Test Step - 9
            ThreadWait(1000);
            phoneorder.Enter_Name_OrderDetailsTab_CustomerDetailsPopup(prop.getProperty("cust_firstName"));
            softassert.assertEquals(phoneorder.get_Name_Orderdetailstab(), prop.getProperty("cust_firstName"), "Test Step - 9 - Name search box entered data is not cleared in order details tab at customer details popup");

            // Test Step - 10
            delayWithGivenTime(1500);
            softassert.assertTrue(phoneorder.Verify_Name_Orderdetailstab(prop.getProperty("cust_firstName")), "Test Step - 10 - Filtered Name is not displayed on the order details tab at customer details popup");

            // Test Step - 11
            delayWithGivenTime(2000);
            phoneorder.Enter_Name_OrderDetailsTab_CustomerDetailsPopup("Abi");
            softassert.assertEquals(phoneorder.get_Name_Orderdetailstab(), "Abi", "Test Step - 11 - Name search box entered data is not cleared in order details tab at customer details popup");

            // Test Step - 12
            ThreadWait(2000);
            softassert.assertTrue(phoneorder.Verify_Name_Orderdetailstab(prop.getProperty("custfullname")), "Test Step - 12 - Filtered name with 3 characters is not displayed on the order details tab at customer details popup");

            // Test Step - 13
            ThreadWait(1000);
            phoneorder.Enter_Name_OrderDetailsTab_CustomerDetailsPopup(prop.getProperty("custName") + "1");
            ThreadWait(1000);
            softassert.assertEquals(phoneorder.get_Name_Orderdetailstab(), prop.getProperty("custName") + "1", "Test Step - 13 - Name search box entered data is not cleared in order details tab at customer details popup");
            softassert.assertTrue(phoneorder.Verify_NoCustomerOrdersFoundMessage_Appears_OrderDetailsTab(), "Test Step - 13 - Filtered with invalid Name is not displayed on the message like No Customer Order Found in order details tab at customer details popup");

            // Test Step - 14
            ThreadWait(1000);
            softassert.assertTrue(phoneorder.Verify_Clearbutton_NameSearchBox_OrderDetailsTab(), "Test Step - 14 - Clear button on Name search box is not displayed in order details tab customer details popup");

            // Test Step - 15
            phoneorder.Click_clearbutton_NameSearchBox_OrderDetailsTab();
            softassert.assertEquals(phoneorder.get_Name_Orderdetailstab(), "", "Test Step - 15 - Name search box entered data is not cleared in order details tab at customer details popup");
            softassert.assertTrue(phoneorder.Verify_AllTheName_AppearsOn_OrderDetailsTab(), "Test Step - 15 - All the Name are not displayed on order details tab");
        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}