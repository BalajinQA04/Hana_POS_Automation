package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Delivery_Type;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T364_OrderEntryPage_Delivery_RecipientSection_ViewMap_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    @Epic("Phone Order Module - Delivery Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T364_OrderEntryPage_Delivery_RecipientSection_ViewMap_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T364_OrderEntryPage_Delivery_RecipientSection_ViewMap_Functionality_Test ****");
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
        /*    dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));
*/
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

            softassert.assertTrue(phoneorder.Verify_RecipientSectionAppears(), "Test Step - 5 - Recipient section is not displayed on Phone order page is not displayed");

            // Test Step - 6
            phoneorder.ClickdeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_OnDelivery_TypeOnPhoneOrderPage(), "#676a6c",
                    "Test Step - 6 - Delivery type is not highlighted as blue color");

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
            phoneorder.ClickReciNameOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), "", "Test Step - 8 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "", "Test Step - 8 - Displayed last name is not matched with customer lastname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress1(), "", "Test Step - 8 - Displayed recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "", "Test Step - 8 - Displayed recipient address 2 is not matched with customer address 2 on phone order page recipient section");

            if (phoneorder.getReciPhone().equals("956-655-0756")) {
                softassert.assertEquals(phoneorder.getReciPhone(), "956-655-0756", "Test Step - 8 - Recipient phone number is not auto populated on recipient section");
            } else if (phoneorder.getReciPhone().equals(prop.getProperty("default_phone_number1"))) {
                softassert.assertEquals(phoneorder.getReciPhone(), prop.getProperty("default_phone_number1"), "Test Step - 8 - Recipient phone number is not auto populated on recipient section");
            }

            if (phoneorder.getRecipientPhone2OnPhoneOrderPage().equals("956-655-0756")) {
                softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), "956-655-0756", "Test Step - 8 - Recipient alt phone number is not auto populated on recipient section");
            } else if (phoneorder.getRecipientPhone2OnPhoneOrderPage().equals("")) {
                softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), "", "Test Step - 8 - Recipient alt phone number is not auto populated on recipient section");
            }

            // Test Step - 9
            phoneorder.EnterReciAddress1(prop.getProperty("recipient_address1"));
            press_Tab_Key();
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_MapIconIsAppear_OnRecipientAddress1(), "Test Step - 9 - Map icon is not displayed on next to recipient address 1 field");

            // Test Step - 10
            phoneorder.Click_MapIconOn_RecipientAddress1();
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

        /*    // Test Step - 17
            if (phoneorder.Verify_PlusIcon_OnMapDirection_Popup_At_ReciAddress1_IsDisplayed() == true) {
                phoneorder.Click_PlusIcon_OnMapDirectionPopup_ReciAddress1();
                delayWithGivenTime(2000);
            }

            softassert.assertTrue(phoneorder.VerifyMapIsZoomIn(), "Test Step - 17 - Map direction popup is not zoom in recipient address 1 field");

            // Test Step - 18
            if (phoneorder.Verify_MinusIcon_OnMapDirection_Popup_At_ReciAddress1_IsDisplayed() == true) {
                phoneorder.Click_MinusIcon_OnMapDirectionPopup_ReciAddress1();
                delayWithGivenTime(2000);
            }
*/
            softassert.assertTrue(phoneorder.VerifyMapIsZoomOut(), "Test Step - 18 - Map direction popup is not zoom out recipient address 1 field");

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
            e.printStackTrace();
            softassert.fail("Test case failed due to exception " + e.getMessage());

        } finally {
            softassert.assertAll();
        }
    }
}