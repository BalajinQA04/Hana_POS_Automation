package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T138_Pickup_CustomerSection_Edit_Customer_CustomerDetails_Statements_LessThanOneMonthCustomer_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    @Epic("Phone Order Module - Pickup Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T138_Pickup_CustomerSection_Edit_Customer_CustomerDetails_Statements_LessThanOneMonthCustomer_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting Hana_T138_Pickup_CustomerSection_Edit_Customer_CustomerDetails_Statements_LessThanOneMonthCustomer_FT  ****");
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
            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("custName"));
            delayWithGivenTime(1000);

            // Test Step - 7
            softassert.assertTrue(phoneorder.Verify_CustEditIcon_Appears(), "Test Step - 7 - Customer clear button is not displayed on phone order page");

            // Test Step - 8
            phoneorder.Click_CustEditIcon();
            softassert.assertTrue(phoneorder.Verify_CustomerDetailsPopupAppears(), "Test Step - 8 - Customer details popup on is not displayed on phone order page");

            // Test Step - 9
            delayWithGivenTime(1000);
            phoneorder.Click_StatementsTab_CustomerDetailsPopup();

            // Test Step - 10
            softassert.assertTrue(phoneorder.Verify_Selectmonth_IsEmpty_StatementsTab(), "Test Step - 10 - Select month dropdown options is not empty on Statements tab on customer details popup is not displayed");

            // Test Step - 11
            if (phoneorder.Verify_NoStatementFoundMessage_OnStatementsTab()) {
                softassert.assertTrue(phoneorder.Verify_NoStatementFoundMessage_OnStatementsTab(), "Test Step - 11 - There are no statements available for the selected month message is not displayed on statements tab on customer details popup");
            } else {
                softassert.fail("For Customer statement is displayed on statements tab on customer details popup");
            }

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}