package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T31_GiftCardSalepopup_Edit_Gift_Card_Denominations_Add_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    public static final String dataSheetName = "Hana_T31";

    // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
    CustomSoftAssert softassert = new CustomSoftAssert();

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    /*
     * HANA-T31 - 1 to 10 - Verify Gift Card Sale pop-up- Edit Gift Card Denominations - Add Functionality
     */

    // retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Epic("Cash and Carry Module")
    @Test(enabled = false, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T31_GiftCardSalesPopup_EditGiftCardDenomination_AddFunctionalityTest(String denomination1, String denomination2, String denomination3, String denomination4, String denomination5, String denomination6,
                                                                                                   String denomination7, String denomination8, String denomination9, String denomination10, String instantdenomination1) throws InterruptedException, IOException {
        // Test Step - 1
        logger.info("**** Starting HANA_T31_CashAndCarryTest_GiftCardSalesPopup_EditGiftCardDenomination_AddFunctionalityTest  ****");
        logger.info("User on the hana pos login page and entering valid username and password..");
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
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2: Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucessfully..");

            // Test Step - 3
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User selected shop name as " + prop.getProperty("shopname"));
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            Assert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3: Cash And Carry page is not displayed");
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
            cashandcarry.EditDenomination1TextBox(denomination1);
            logger.info("User edit the denomination 1 text box..");
            cashandcarry.ClickEditGiftCardDenominationPopupCancelButton();
            logger.info("User click cancel button on edit gift card denomination popup..");
            softassert.assertTrue(cashandcarry.VerifyGiftCardSale(), "Test Step - 6: gift card sales pop up is not displayed");
            logger.info("User verify the gift card sales pop up is displayed..");
            softassert.assertEquals(cashandcarry.getGiftCardPrice1Denomination(), "$30", "Test Step - 6: gift card denomination price 1 is not updated");
            logger.info("User verify the gift card denomination price1 is displayed..");

            // Test Step - 7
            cashandcarry.ClickEditIconOnGiftCardPopup();
            logger.info("User Click on edit icon on gift card popup..");
            softassert.assertTrue(cashandcarry.VerifyEditGiftCardDominationPopup(), "Test Step - 7: edit gift card denomination popup is not displayed");
            logger.info("User verify the edit gift card denomination pop up is displayed..");
            cashandcarry.EditDenomination1TextBox(denomination1);
            logger.info("User edit and enter the denomination 1 text box as " + denomination1);
            cashandcarry.EditDenomination2TextBox(denomination2);
            logger.info("User edit and enter the denomination 2 text box as " + denomination2);
            cashandcarry.EditDenomination3TextBox(denomination3);
            logger.info("User edit and enter the denomination 3 text box as " + denomination3);
            cashandcarry.EditDenomination4TextBox(denomination4);
            logger.info("User edit and enter the denomination 4 text box as " + denomination4);
            cashandcarry.EditDenomination5TextBox(denomination5);
            logger.info("User edit and enter the denomination 5 text box as " + denomination5);
            cashandcarry.EditDenomination6TextBox(denomination6);
            logger.info("User edit and enter the denomination 6 text box as " + denomination6);
            cashandcarry.EditDenomination7TextBox(denomination7);
            logger.info("User edit and enter the denomination 7 text box as " + denomination7);
            cashandcarry.EditDenomination8TextBox(denomination8);
            logger.info("User edit and enter the denomination 8 text box as " + denomination8);
            cashandcarry.EditDenomination9TextBox(denomination9);
            logger.info("User edit and enter the denomination 9 text box as " + denomination9);
            cashandcarry.EditDenomination10TextBox(denomination10);
            logger.info("User edit and enter the denomination 10 text box as " + denomination10);

            // Test Step - 8
            cashandcarry.ClickEditGiftCardDenominationPopupSaveButton();
            logger.info("User Click on save button on gift card popup..");
            softassert.assertTrue(cashandcarry.VerifySuccessMessageIsDisplayed(), "Test Step - 8: Success toast message is not displayed");
            logger.info("User verify the success toast message is displayed..");

            //Test Step - 9
            delayWithGivenTime(2000);
            if (cashandcarry.getGiftCardPrice1Denomination().equals("$30") || cashandcarry.getGiftCardPrice1Denomination().equals("$20")) {
                softassert.assertEquals(cashandcarry.getGiftCardPrice1Denomination(), "$30", "Test Step - 9: gift card denomination price1 is not updated");
            }
            softassert.assertEquals(cashandcarry.getGiftCardPrice2Denomination(), "$60", "gift card denomination price2 is not updated");
            softassert.assertEquals(cashandcarry.getGiftCardPrice3Denomination(), "$90", "gift card denomination price3 is not updated");
            softassert.assertEquals(cashandcarry.getGiftCardPrice4Denomination(), "$120", "gift card denomination price4 is not updated");
            softassert.assertEquals(cashandcarry.getGiftCardPrice5Denomination(), "$150", "gift card denomination price5 is not updated");
            softassert.assertEquals(cashandcarry.getGiftCardPrice6Denomination(), "$180", "gift card denomination price6 is not updated");
            softassert.assertEquals(cashandcarry.getGiftCardPrice7Denomination(), "$210", "gift card denomination price7 is not updated");
            softassert.assertEquals(cashandcarry.getGiftCardPrice8Denomination(), "$240", "gift card denomination price8 is not updated");
            softassert.assertEquals(cashandcarry.getGiftCardPrice9Denomination(), "$270", "gift card denomination price9 is not updated");
            softassert.assertEquals(cashandcarry.getGiftCardPrice10Denomination(), "$300", "gift card denomination price10 is not updated");
            logger.info("User verify all the gift card denomination price is displayed..");

            // Test Step - 10
            cashandcarry.ClickOnInstantDenomination("$30");
            logger.info("User Click on Instant denomination on gift card popup..");
            softassert.assertEquals(cashandcarry.getGiftAmountValue(), "30.00", "Test Step - 10: Gift amount values are not matched");
            logger.info("User verify the gift amount on gift card popup..");
            softassert.assertEquals(cashandcarry.getTotalGiftValue(), "30.00", "Test Step - 10:Total gift values are not matched");
            logger.info("User verify the total gift amount on gift card popup..");
        } catch (Exception e) {
            softassert.fail(e.getMessage());
            logger.error(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}
