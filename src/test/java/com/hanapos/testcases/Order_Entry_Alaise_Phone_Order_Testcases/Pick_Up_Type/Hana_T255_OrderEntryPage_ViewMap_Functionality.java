package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T255_OrderEntryPage_ViewMap_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    @Epic("Phone Order Module - Pickup Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T255_OrderEntryPage_ViewMap_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T255_OrderEntryPage_ViewMap_Functionality_Test ****");
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
            softassert.assertTrue(phoneorder.Verify_RecipientSectionAppears(), "Test Step - 5 - Recipient section is not displayed on Phone order page is not displayed");

            // Test Step - 6
            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Test Step - 6 - Pickup type is not highlighted in blue color");

            //Test Step - 7
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            softassert.assertTrue(phoneorder.VerifyCustSectionAppears(), "Test Step - 7 - Customer section is not displayed on phone order page");
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("cust_firstName"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), "Abish", "Test Step - 7 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), "David", "Test Step - 7 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), "Hana_Sisterchicks", "Test Step - 7 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), "hanaposqateam@gmail.com", "Test Step - 7 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "3402 Park Blvd", "Test Step - 7 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 7 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "92103", "Test Step - 7 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "San Diego", "Test Step - 7 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 7 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 7 - Alt phone number is not displayed on phone order page");

            // Test Step - 8
            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Test Step - 8 - Pickup type is not highlighted in blue color");

            phoneorder.ClickReciNameOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), "Abish", "Test Step - 8 - Recipient first name is not auto populated on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "David", "Test Step - 8 - Recipient last name is not auto populated on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress1(), "114 N CHURCH ST", "Test Step - 8 - Recipient address 1 field is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "PICK UP", "Test Step - 8 - Recipient address 2 field is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), "956-655-0756", "Known Issue:- Test Step - 8 - Recipient phone number is not displayed on phone number 1 field in phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), "956-655-0756", "Known Issue: - Test Step - 8 - Recipient alt phone number is not displayed on phone number2 in phone order page recipient section");

            // Test Step - 9
            phoneorder.Click_view_map_icon_on_Top();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_MapDirectionPopup_IsAppear(), "Test Step - 10 - Map direction popup is not displayed on next to recipient address 1 field");

            // Test Step - 11
            phoneorder.Click_Row1DirectionInstructions_OnMapDirectionPopup();
            softassert.assertTrue(phoneorder.Verify_TooltipTextIsAppear_OnMapDirectionPopup(), "Test Step - 11 - Tooltip text is not displayed on selected row 1 direction instructions on next to recipient address 1 field");

            // Test Step - 12
            phoneorder.Click_CloseIcon_OnMapDirectionPopup();
            softassert.assertFalse(phoneorder.Verify_TooltipClosed_OnMapDirectionPopup(), "Test Step - 12 - Tooltip text is displayed on selected row 1 direction instructions on next to recipient address 1 field");

            // Test Step - 13
            phoneorder.Click_SatelliteMapIcon_OnMapDirectionPopup_ReciAddress1();

            // Test Step - 14
            phoneorder.MouseHoverSatelliteTabAndClickLabel_SatelliteMapIcon();

            // Test Step - 15
            phoneorder.ClickMapTab_OnMapDirectionPopup_ReciAddress1();

            // Test Step - 16
            phoneorder.MouseHoverMapTabAndClickTerrain_OnMapDirectionPopup_ReciAddress1();

            // Test Step - 17
            phoneorder.Click_PlusIcon_OnMapDirectionPopup_ReciAddress1();
            delayWithGivenTime(2000);
            // softassert.assertTrue(phoneorder.VerifyMapIsZoomIn(), "Test Step - 17 - Map direction popup is not zoom in recipient address 1 field");

            // Test Step - 18
            phoneorder.Click_MinusIcon_OnMapDirectionPopup_ReciAddress1();
            delayWithGivenTime(2000);
            // softassert.assertTrue(phoneorder.VerifyMapIsZoomOut(), "Test Step - 18 - Map direction popup is not zoom out recipient address 1 field");

            // Test Step - 19
            phoneorder.DragAndDrop_DragPegman_OnMapDirectionPopup_ReciAddress1();

            //Test Step - 20
            phoneorder.Click_FullscreenIcon_OnMapDirectionPopup_ReciAddress1();
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_MapDirectionPopup_DisplayedFullScreenMode(), "Test Step - 20 - Map direction popup is not displayed as full screen on recipient address 1 field");

            // Test Step - 21
            actionsEscapeKey();

            // Test Step - 22
            phoneorder.Click_CloseIconOnMapDirectionPopup_ReciAddress1();
            softassert.assertFalse(phoneorder.MapDirectionPopup_isClosed(), "Test Step - 22 - Map direction popup is not closed on  recipient address 1 field");

        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}