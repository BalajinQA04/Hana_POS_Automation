package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireOut_Type;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T569_CustomerSection_Edit_Customer_CustomerDetails_OrderDetails_DescriptionSearchFilter_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    @Epic("Phone Order Module - Wire out Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T569_CustomerSection_Edit_Customer_CustomerDetails_OrderDetails_DescriptionSearchFilter_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting Validate_Hana_T569_CustomerSection_Edit_Customer_CustomerDetails_OrderDetails_DescriptionSearchFilter_Functionality_Test  ****");
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
            phoneorder.Click_OrderDetailsTab_CustomerDetailsPopup();
            delayWithGivenTime(4000);
            softassert.assertTrue(phoneorder.Verify_OrderDetailsTableGrid_IsAppears_OrderDetailsTab_CustomerDetailsPopup(), "Test Step - 9 - Order details tab webtable grid is not displayed");

            // Test Step - 9
            ThreadWait(1000);
            phoneorder.Enter_Description_OrderDetailsTab_CustomerDetailsPopup(prop.getProperty("product_description1"));
            softassert.assertEquals(phoneorder.get_Description_Orderdetailstab(), prop.getProperty("product_description1"), "Test Step - 9: product description is not displayed");

            // Test Step - 10
            RobotPressEnter();
            ThreadWait(1500);
            softassert.assertTrue(phoneorder.Verify_Description_Orderdetailstab(prop.getProperty("product_description1")), "Test Step - 10 - Filtered Description is not displayed on the order details tab at customer details popup");

            // Test Step - 11
            ThreadWait(2000);
            phoneorder.Enter_Description_OrderDetailsTab_CustomerDetailsPopup("Bal");
            softassert.assertEquals(phoneorder.get_Description_Orderdetailstab(), "Bal", "Test Step - 9: product description is not displayed");

            // Test Step - 12
            RobotPressEnter();
            ThreadWait(2000);
            softassert.assertTrue(phoneorder.Verify_Description_Orderdetailstab("Ballons small"), "Test Step - 12 - Filtered Description with 3 characters is not displayed on the order details tab at customer details popup");

            // Test Step - 13
            ThreadWait(1000);
            phoneorder.Enter_Description_OrderDetailsTab_CustomerDetailsPopup("xxx description");
            ThreadWait(1000);
            RobotPressEnter();
            softassert.assertTrue(phoneorder.Verify_NoCustomerOrdersFoundMessage_Appears_OrderDetailsTab(), "Test Step - 13 - Filtered with invalid description is not displayed on the message like No Customer Order Found in order details tab at customer details popup");

            // Test Step - 14
            ThreadWait(1000);
            softassert.assertTrue(phoneorder.Verify_Clearbutton_DescriptionSearchBox_OrderDetailsTab(), "Test Step - 14 - Clear button on Address search box is not displayed in order details tab customer details popup");

            // Test Step - 15
            phoneorder.Click_clearbutton_DescriptionSearchBox_OrderDetailsTab();
            softassert.assertEquals(phoneorder.get_Description_Orderdetailstab(), "", "Test Step - 15 - Address search box entered data is not cleared in order details tab at customer details popup");
            softassert.assertTrue(phoneorder.Verify_AllTheDescription_AppearsOn_OrderDetailsTab(), "Test Step - 15 - All the address are not displayed on order details tab");

        } catch (Exception e) {
            e.printStackTrace();
            softassert.fail();
        } finally {
            softassert.assertAll();
        }
    }
}