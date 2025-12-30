package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.CashAndCarryPaymentPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T48_Payments_CreditCard_Manually_Entering_CreditCard_Details_ExistingCard_Functionality_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    public static LoggerUtil logger_Util;

    public static final String dataSheetName = "Hana_T48";

    // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
    CustomSoftAssert softassert = new CustomSoftAssert();

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    //retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T48_Payments_CreditCard_Manually_Entering_CreditCard_Details_ExistingCard_Functionality_Test(String searchandselectitemcode, String customername, String occasion, String ccfname, String cclname, String creditcardtype, String cccardnumber, String ccexpiredate, String cccvv, String cczipcode) throws InterruptedException, IOException {

        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("bestuname"));
            logger.info("User entered username as " + prop.getProperty("bestuname"));
            lp.EnterPassword(prop.getProperty("bestpass"));
            logger.info("User entered username as " + prop.getProperty("bestpass"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button..");

            dashboard = new HanaDashBoardPage();
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            // Test Step - 3
            dashboard.SelectShopNameDropDown(prop.getProperty("bestshopname"));
            logger.info("User selected the shop name as " + prop.getProperty("bestshopname") + "in dashboard page");
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("bestshopname")); //"Hana POS (Canada)"
            logger.info("User select the shop name as " + prop.getProperty("bestshopname") + "in cash and carry page");

            //Test Step - 5
            cashandcarry.SelectClerkName(prop.getProperty("bestclerk")); //"Automation clerk desc"
            logger.info("User select the clerk name as " + prop.getProperty("bestclerk"));

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("bestemployee")); //"QA Team Automation"
            logger.info("User select the employee name as " + prop.getProperty("bestemployee"));

            // Test Step - 7
            softassert.assertTrue(cashandcarry.IsPayButtonDisabled(), "Pay button is not disabled");
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1");

            if (cashandcarry.ItemPriceValueIsExist() == "299") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Item price is not matched with search and selected item code");
            } else if (cashandcarry.ItemPriceValueIsExist() == "309") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "309", "Item price is not matched with search and selected item code");
            }

            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0");

            // Test Step - 8
            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem());
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "rrd");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Red Rose Deluxe");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1");

            if (cashandcarry.GetAddedItemExtPrice() == "$299.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00");
            } else if (cashandcarry.GetAddedItemExtPrice() == "$309.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$309.00");
            }

            if (cashandcarry.GetAddedItemPrice() == "$299.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00");
            } else if (cashandcarry.GetAddedItemPrice() == "$309.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$309.00");
            }

            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00");

            // Test Step - 9
            delayWithGivenTime(2000);
            cashandcarry.Click_Displayed_Tile_Product("rrd-");
            logger.info("User click on the particular product tile");
            softassert.assertEquals(cashandcarry.getAddedItemCodeRow2(), "rrd");
            logger.info("User verify add the title product to the Cash and Carry page is displayed..");

            // Test Step - 10
            cashandcarry.EnterCustomerName("Abish", "Abish David");
            logger.info("User search and select the created customer ");
            cashandcarry.SelectTaxType("Tax Exemption");
            cashandcarry.SelectOccasion(occasion);
            logger.info("User select the Occasion as Birthday");
            delayWithGivenTime(1000);
            cashandcarry.ClickPayButton();
            logger.info("User click on Pay button");
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Cash And Carry payment page is not displayed");
            logger.info("User navigated to Cash And Carry payment page successfully");

            // Test Step - 11
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarrypayment.VerifyCreditCardTabIsSelected(), "true", "By defaultCredit card tab section is not displayed");

            // Test Step - 12
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCreditCardTab();
            /*
             * if(cashandcarrypayment.VerifyCreditCardPresentToogleBtnIsAppear()==true) {
             * cashandcarrypayment.ClickCreditCardPresentToogleBtn();
             * softassert.assertTrue(cashandcarrypayment.
             * VerifyCreditCardPresentToogleBtnIsSelected()
             * ,"Test Step - 12 : Credit card is present toogle button is not turn on");
             * }else {
             * softassert.fail("***Credit card present toogle button is not displayed***");
             * }
             *
             * if(cashandcarrypayment.VerifyCreditCardFirstNameIsDisabled()==true) {
             * softassert.assertTrue(cashandcarrypayment.VerifyCreditCardFirstNameIsDisabled
             * (),"Credit card first name field is not disabled");
             * softassert.assertTrue(cashandcarrypayment.VerifyCreditCardLastNameIsDisabled(
             * ),"Credit card last name field is not disabled");
             * softassert.assertTrue(cashandcarrypayment.
             * VerifySelectCreditCardTypeIsDisabled()
             * ,"Credit card selected card type is not disabled");
             * softassert.assertTrue(cashandcarrypayment.
             * VerifyEnterCreditCardNumberIsDisabled()
             * ,"Credit card number field is not disabled");
             * softassert.assertTrue(cashandcarrypayment.
             * VerifyEnterCreditCardExpireDateIsDisabled()
             * ,"Credit card expire date is not disabled");
             * softassert.assertTrue(cashandcarrypayment.VerifyEnterCreditCardCVVIsDisabled(
             * ),"Credit card cvv field is not disabled");
             * softassert.assertTrue(cashandcarrypayment.
             * VerifyEnterCreditCardZipCodeIsDisabled()
             * ,"Credit card zipcode field is not disabled"); }else { softassert.
             * fail("Test Step - 12 : Credit card tab all the fields are not disabled"); }
             */

            // Test Step - 13
            //cashandcarrypayment.ClickCreditCardPresentToogleBtn();
            cashandcarrypayment.EnterFirstNameOnCreditCardTab(ccfname);
            softassert.assertEquals(cashandcarrypayment.getEnteredCreditCardFirstName(), "Automation", "First name is not displayed");
            cashandcarrypayment.EnterLastNameOnCreditCardTab(cclname);
            softassert.assertEquals(cashandcarrypayment.getEnteredCreditCardLastName(), "Test", "Last name is not displayed");
            cashandcarrypayment.SelectCreditCardTypeOnCreditCardTab(creditcardtype);
            cashandcarrypayment.EnterCreditCardNumberOnCreditCardTab(prop.getProperty("creditcardnum"));
            softassert.assertEquals(cashandcarrypayment.getEnterCreditCardNumber(), "4111111111111111", "credit card number entered data is not displayed");
            cashandcarrypayment.EnterCreditCardExpireDateOnCreditCardTab(prop.getProperty("ccexpiredate"));
            softassert.assertEquals(cashandcarrypayment.getEnterCreditCardExpireDate(), "1030", "credit card expire date entered data is not displayed");
            cashandcarrypayment.EnterCreditCardCVVOnCreditCardTab(prop.getProperty("cccvv"));
            softassert.assertEquals(cashandcarrypayment.getEnterCreditCardCVV(), "123", "credit card cvv entered data is not displayed");
            cashandcarrypayment.EnterCreditCardZipCodeOnCreditCardTab(cczipcode);
            softassert.assertEquals(cashandcarrypayment.getEnterCreditCardZipCode(), "32012", "credit card zipcode entered data is not displayed");
            softassert.assertTrue(cashandcarrypayment.VerifyProcessPaymentButton(), "Process payment button is not displayed");

            // Test Step - 14 to 21 - are done by manually
            softassert.assertAll();
        } catch (Exception e) {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
            softassert.fail(e.getMessage());
        } finally {
            try {
                softassert.assertAll();
            } catch (AssertionError ae) {
                logger_Util = new LoggerUtil();
                logger_Util.attachNetworkLogs(testCaseName);
                ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
                throw ae;
            }
        }
    }
}
