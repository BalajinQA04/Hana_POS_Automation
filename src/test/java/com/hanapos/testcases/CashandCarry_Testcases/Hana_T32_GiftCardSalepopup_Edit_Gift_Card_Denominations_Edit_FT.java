package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T32_GiftCardSalepopup_Edit_Gift_Card_Denominations_Edit_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    public static final String dataSheetName = "Hana_T32";

    // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
    CustomSoftAssert softassert = new CustomSoftAssert();

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    /*
     * HANA-T32 - 1 to 10 - Verify Gift Card Sale pop-up- Edit Gift Card Denominations - Edit Functionality
     */
//retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Epic("Cash and Carry Module")
    @Test(enabled = false, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T32_GiftCardSalesPopup_EditGiftCardDenomination_AddFunctionality_Test(String denomination1, String instantdenomination1) throws InterruptedException, IOException {
        // Test Step - 1
        logger.info("**** Starting  HANA-T32_CashAndCarry - Gift Card Sale pop-up- Edit Gift Card Denominations - Edit FunctionalityTest  ****");
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("Clicked on Login button..");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucessfully");

            // Test Step - 3
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shop name as " + prop.getProperty("shopname") + "in the dashboard page");
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3: Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 4
            cashandcarry.ClickGiftCardTile();
            logger.info("User clicks on gift card tile..");
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.VerifyGiftCardSale(), "Test Step - 4: gift card sales pop up is not displayed");
            logger.info("User verify the gift card sales pop up is displayed..");

            // Test Step - 5
            cashandcarry.ClickEditIconOnGiftCardPopup();
            logger.info("User click edit icon on gift card popup..");
            softassert.assertTrue(cashandcarry.VerifyEditGiftCardDominationPopup(), "Test Step - 5: edit gift card denomination popup is not displayed");
            logger.info("User verify the edit gift card denomination pop up is displayed..");

            // Test Step - 6
            delayWithGivenTime(2000);
            cashandcarry.EditDenomination1TextBox(denomination1);
            logger.info("User edit the denomination 1 text box..");
            cashandcarry.ClickEditGiftCardDenominationPopupCancelButton();
            logger.info("User click cancel button on edit gift card denomination popup..");
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.VerifyGiftCardSale(), "Test Step - 6: gift card sales pop up is not displayed");
            logger.info("User verify the gift card sales pop up is displayed..");
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.getGiftCardPrice1Denomination(), "$30", "Test Step - 6: gift card denomination price 1 is not updated");

            logger.info("User verify the gift card denomination price1 is displayed..");

            // Test Step - 7
            delayWithGivenTime(2000);
            cashandcarry.ClickEditIconOnGiftCardPopup();
            logger.info("User Click on edit icon on gift card popup..");
            softassert.assertTrue(cashandcarry.VerifyEditGiftCardDominationPopup(), "Test Step - 7: edit gift card denomination popup is not displayed");
            logger.info("User verify the edit gift card denomination pop up is displayed..");
            cashandcarry.EditDenomination1TextBox(denomination1);
            logger.info("User entered denomination price in text box on gift card popup..");

            // Test Step - 8
            delayWithGivenTime(2000);
            cashandcarry.ClickEditGiftCardDenominationPopupSaveButton();
            logger.info("User Click on save button on gift card popup..");
            softassert.assertTrue(cashandcarry.VerifySuccessMessageIsDisplayed(), "Test Step - 8: Success toast message is not displayed");
            logger.info("User Verify the success toast message is displayed..");

            //Test Step - 9
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.getGiftCardPrice1Denomination(), "$20", "Test Step - 9: gift card denomination price1 is not updated");
            logger.info("User verify the gift card denomination price is displayed..");

            // Test Step - 10
            cashandcarry.ClickOnInstantDenomination(instantdenomination1);
            logger.info("User Click on Instant denomination on gift card popup..");
            softassert.assertEquals(cashandcarry.getGiftAmountValue(), "20.00", "Test Step - 10: Gift amount values are not matched");
            logger.info("User verify the gift amount on gift card popup..");
            softassert.assertEquals(cashandcarry.getTotalGiftValue(), "20.00", "Test Step - 10: Total gift values are not matched");
            logger.info("User verify the total gift amount on gift card popup..");

            // for parallel run test possible to failed -- so again update the previously saved
            delayWithGivenTime(2000);
            cashandcarry.ClickEditIconOnGiftCardPopup();
            logger.info("User click edit icon on gift card popup..");
            softassert.assertTrue(cashandcarry.VerifyEditGiftCardDominationPopup(), "Test Step - 11: edit gift card denomination popup is not displayed");
            logger.info("User verify the edit gift card denomination pop up is displayed..");
            cashandcarry.EditDenomination1TextBox("30");
            logger.info("User edit the denomination 1 text box..");
            cashandcarry.ClickEditGiftCardDenominationPopupSaveButton();
            logger.info("User Click on save button on gift card popup..");
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.getGiftCardPrice1Denomination(), "$30", "Test Step - 12: gift card denomination price1 is not updated");

            logger.info("**** Ended  HANA-T32_CashAndCarry - Gift Card Sale pop-up- Edit Gift Card Denominations - Edit FunctionalityTest  ****");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            softassert.assertAll();
        }
    }
}

