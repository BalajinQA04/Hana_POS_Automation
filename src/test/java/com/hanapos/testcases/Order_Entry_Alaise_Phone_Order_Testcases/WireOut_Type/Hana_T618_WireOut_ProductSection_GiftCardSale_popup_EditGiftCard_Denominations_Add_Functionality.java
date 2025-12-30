package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireOut_Type;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T618_WireOut_ProductSection_GiftCardSale_popup_EditGiftCard_Denominations_Add_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    @Epic("Phone Order Module - Wire out Type")
    @Test(enabled = false, groups = {"Regression"})
    public void Validate_Hana_T618_WireOut_ProductSection_GiftCardSale_popup_EditGiftCard_Denominations_Add_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T618_WireOut_ProductSection_GiftCardSale_popup_EditGiftCard_Denominations_Add_Functionality_Test ****");
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
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Hana dashboard page is not displayed");
            logger.info("User navigated to hana dashboard page");
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Cash and carry option is not displayed");
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 4
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Click_WireOut_DeliveryType_OnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_On_WireOut_PhoneOrderPage(), "#2f9bc8", "Test Step - 4 - Wire out Delivery type is not highlighted in blue color");

            phoneorder.Click_AddGiftCardIcon_OnPhoneorderPage();
            softassert.assertTrue(phoneorder.Verify_GiftCardPopup_OnPhoneorderPage(), "Test Step - 4 - Gift Card popup is not displayed");

            // Test Step - 5
            phoneorder.Click_EditIcon_OnGiftCardPopup();
            softassert.assertTrue(phoneorder.Verify_EditGiftCardDenomination_Popup_IsDisplayed(), "Test Step - 5 - Edit gift card denomination popup is not displayed");

            //Test Step - 6
            phoneorder.Enter_Denomination7_OnEditDenominationGiftCardPopup("210");
            phoneorder.Click_CancelButton_OnEditDenominationGiftCardPopup();
            softassert.assertTrue(phoneorder.Verify_GiftCardPopup_OnPhoneorderPage(), "Test Step - 6 - Gift Card popup is not displayed");

            // Test Step - 7
            phoneorder.Click_EditIcon_OnGiftCardPopup();
            softassert.assertTrue(phoneorder.Verify_EditGiftCardDenomination_Popup_IsDisplayed(), "Test Step - 7 - Edit gift card denomination popup is not displayed");
            phoneorder.Enter_Denomination1_OnEditDenominationGiftCardPopup("40");
            phoneorder.Enter_Denomination2_OnEditDenominationGiftCardPopup("80");
            phoneorder.Enter_Denomination3_OnEditDenominationGiftCardPopup("120");
            phoneorder.Enter_Denomination4_OnEditDenominationGiftCardPopup("160");
            phoneorder.Enter_Denomination5_OnEditDenominationGiftCardPopup("200");
            phoneorder.Enter_Denomination6_OnEditDenominationGiftCardPopup("240");
            phoneorder.Enter_Denomination7_OnEditDenominationGiftCardPopup("280");
            phoneorder.Enter_Denomination8_OnEditDenominationGiftCardPopup("320");
            phoneorder.Enter_Denomination9_OnEditDenominationGiftCardPopup("360");
            phoneorder.Enter_Denomination10_OnEditDenominationGiftCardPopup("400");

            softassert.assertEquals(phoneorder.get_Entered_Denomination1(), "40", "Test Step - 7 - Entered denomination 1 is not displayed");
            softassert.assertEquals(phoneorder.get_Entered_Denomination2(), "80", "Test Step - 7 - Entered denomination 2 is not displayed");
            softassert.assertEquals(phoneorder.get_Entered_Denomination3(), "120", "Test Step - 7 - Entered denomination 3 is not displayed");
            softassert.assertEquals(phoneorder.get_Entered_Denomination4(), "160", "Test Step - 7 - Entered denomination 4 is not displayed");
            softassert.assertEquals(phoneorder.get_Entered_Denomination5(), "200", "Test Step - 7 - Entered denomination 5 is not displayed");
            softassert.assertEquals(phoneorder.get_Entered_Denomination6(), "240", "Test Step - 7 - Entered denomination 6 is not displayed");
            softassert.assertEquals(phoneorder.get_Entered_Denomination7(), "280", "Test Step - 7 - Entered denomination 7 is not displayed");
            softassert.assertEquals(phoneorder.get_Entered_Denomination8(), "320", "Test Step - 7 - Entered denomination 8 is not displayed");
            softassert.assertEquals(phoneorder.get_Entered_Denomination9(), "360", "Test Step - 7 - Entered denomination 9 is not displayed");
            softassert.assertEquals(phoneorder.get_Entered_Denomination10(), "400", "Test Step - 7 - Entered denomination 10 is not displayed");

            // Test Step - 8
            phoneorder.Click_SubmitButton_OnEditDenominationGiftCardPopup();
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Gift Card Denominations Saved Successfully", "Test Step - 8 - Gift card denomination saved successfully has been added toasted message is not displayed");
            softassert.assertTrue(phoneorder.Verify_GiftCardPopup_OnPhoneorderPage(), "Test Step - 8 - Gift Card popup is not displayed");

            // Test Step - 9
            softassert.assertEquals(phoneorder.get_InstantDenomination1(), "$40", "Test Step - 9 - Instant denomination 1 on gift card popup is not updated properly");
            softassert.assertEquals(phoneorder.get_InstantDenomination2(), "$80", "Test Step - 9 - Instant denomination 2 on gift card popup is not updated properly");
            softassert.assertEquals(phoneorder.get_InstantDenomination3(), "$120", "Test Step - 9 - Instant denomination 3 on gift card popup is not updated properly");
            softassert.assertEquals(phoneorder.get_InstantDenomination4(), "$160", "Test Step - 9 - Instant denomination 4 on gift card popup is not updated properly");
            softassert.assertEquals(phoneorder.get_InstantDenomination5(), "$200", "Test Step - 9 - Instant denomination 5 on gift card popup is not updated properly");
            softassert.assertEquals(phoneorder.get_InstantDenomination6(), "$240", "Test Step - 9 - Instant denomination 6 on gift card popup is not updated properly");
            softassert.assertEquals(phoneorder.get_InstantDenomination7(), "$280", "Test Step - 9 - Instant denomination 7 on gift card popup is not updated properly");
            softassert.assertEquals(phoneorder.get_InstantDenomination8(), "$320", "Test Step - 9 - Instant denomination 8 on gift card popup is not updated properly");
            softassert.assertEquals(phoneorder.get_InstantDenomination9(), "$360", "Test Step - 9 - Instant denomination 9 on gift card popup is not updated properly");
            softassert.assertEquals(phoneorder.get_InstantDenomination10(), "$400", "Test Step - 9 - Instant denomination 10 on gift card popup is not updated properly");

            // Test Step - 10
            phoneorder.Click_GiftCardInstantDenomination1();
            softassert.assertEquals(phoneorder.get_giftcardamountTextboxField(), "40.00", "Test Step - 10 - Gift card amount is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_giftCardTotalAmount(), "40.00", "Test Step - 10 - Gift card total amount is not displayed as expected behaviour");

            // Set previously displayed data...
            phoneorder.Click_EditIcon_OnGiftCardPopup();
            softassert.assertTrue(phoneorder.Verify_EditGiftCardDenomination_Popup_IsDisplayed(), "Test Step - 7 - Edit gift card denomination popup is not displayed");
            phoneorder.Enter_Denomination1_OnEditDenominationGiftCardPopup("30");
            phoneorder.Enter_Denomination2_OnEditDenominationGiftCardPopup("60");
            phoneorder.Enter_Denomination3_OnEditDenominationGiftCardPopup("90");
            phoneorder.Enter_Denomination4_OnEditDenominationGiftCardPopup("120");
            phoneorder.Enter_Denomination5_OnEditDenominationGiftCardPopup("150");
            phoneorder.Enter_Denomination6_OnEditDenominationGiftCardPopup("180");
            phoneorder.Enter_Denomination7_OnEditDenominationGiftCardPopup("210");
            phoneorder.Enter_Denomination8_OnEditDenominationGiftCardPopup("240");
            phoneorder.Enter_Denomination9_OnEditDenominationGiftCardPopup("270");
            phoneorder.Enter_Denomination10_OnEditDenominationGiftCardPopup("300");

            phoneorder.Click_SubmitButton_OnEditDenominationGiftCardPopup();
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Gift Card Denominations Saved Successfully", "Test Step - 11 - Gift card denomination saved successfully has been added toasted message is not displayed");
            softassert.assertTrue(phoneorder.Verify_GiftCardPopup_OnPhoneorderPage(), "Test Step - 11 - Gift Card popup is not displayed");
        } catch (Exception e) {
            e.printStackTrace();
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}