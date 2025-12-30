package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T160_OrderEntry_Pickup_OrderDetailsSection_SendRemainderField_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    @Epic("Phone Order Module - Pickup Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T160_OrderEntry_Pickup_OrderDetailsSection_SendRemainderField_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T160_OrderEntry_Pickup_OrderDetailsSection_SendRemainderField_Functionality_Test ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 - Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page did not navigate to hana dashboard page");
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

            softassert.assertTrue(phoneorder.Verify_OrderDetailsSectionAppears(), "Test Step - 5 - Order details section is not displayed");

            // Test Step - 6
            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Test Step - 6 - Pickup type is not highlighted in blue color");

            // Test Step - 7
            // Pre - requiste - verify the default values of occasion is setup
            phoneorder.ClickDefaultValuesIcon();
            delayWithGivenTime(3000);
            phoneorder.Select_DefaultOccasion_On_ChoosePageDefaultValues("--select--");
            phoneorder.Click_UpdateBtn_ChoosePageDefaultValues_Popup();
            phoneorder.ClickCloseIconOnChoosePageDefaultValuesPopup();
            //softassert.assertEquals(phoneorder.getOccasionOnCPDV(),"");

            delayWithGivenTime(2000);
            softassert.assertFalse(phoneorder.Verify_SendRemainder_OnOrderDetailsIsAppears(), "Test Step - 7 - Send remainder checkbox is displayed on Order details section");

            // Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 8 - Order details selected occasion is not displayed");

            // Test Step - 9
            softassert.assertTrue(phoneorder.Verify_SendRemainder_OnOrderDetailsIsAppears(), "Test Step - 9 - Send remainder checkbox is not displayed on Order details section");

            // Test Step - 10
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion2"));
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion2"), "Test Step - 10 - Order details selected occasion is not displayed");

            // Test Step - 11
            softassert.assertFalse(phoneorder.Verify_SendRemainder_OnOrderDetailsIsAppears(), "Test Step - 11 - Send remainder checkbox is not displayed on Order details section");

            // Previously default values of occasion setup to avoid
            phoneorder.ClickDefaultValuesIcon();
            delayWithGivenTime(3000);
            phoneorder.Select_DefaultOccasion_On_ChoosePageDefaultValues(prop.getProperty("occasion"));
            phoneorder.Click_UpdateBtn_ChoosePageDefaultValues_Popup();
            phoneorder.ClickCloseIconOnChoosePageDefaultValuesPopup();

        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}