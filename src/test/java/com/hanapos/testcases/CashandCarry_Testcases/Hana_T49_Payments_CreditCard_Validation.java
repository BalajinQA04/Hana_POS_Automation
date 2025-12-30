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

public class Hana_T49_Payments_CreditCard_Validation extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    public static final String dataSheetName = "Hana_T49";
    CustomSoftAssert softassert = new CustomSoftAssert();
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    //retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T49_Payments_CreditCard_Validation_Test(String searchandselectitemcode, String customername, String occasion, String ccfname, String cclname, String creditcardtype, String cccardnumber, String ccexpiredate, String cccvv, String cczipcode) throws InterruptedException, IOException {
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered username as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button..");

            dashboard = new HanaDashBoardPage();
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            // Test Step - 3
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User selected the shop name as " + prop.getProperty("shopname") + "in dashboard page");
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("shopname"));
            logger.info("User select the shop name as " + prop.getProperty("shopname") + "in cash and carry page");

            //Test Step - 5
            cashandcarry.SelectClerkName(prop.getProperty("clerkname"));
            logger.info("User select the clerk name as " + prop.getProperty("clerkname"));

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            logger.info("User select the employee name as " + prop.getProperty("employeename"));

            // Test Step - 7
            softassert.assertTrue(cashandcarry.IsPayButtonDisabled(), "Pay button is not disabled");
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1");
            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0");

            // Test Step - 8
            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem());
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "rrd");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Red Rose Deluxe");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1");
            softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00");
            softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00");

            // Test Step - 9
            delayWithGivenTime(2000);
            cashandcarry.ClickParticularProdTitle();
            logger.info("User click on the particular product tile");
            softassert.assertEquals(cashandcarry.getAddedItemCodeRow2(), "BalloonsYY", "Selected product tile is not displayed");
            logger.info("User verify add the title product to the Cash and Carry page is displayed..");

            // Test Step - 10
            cashandcarry.EnterCustomerName(customername, customername);
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
   /*         if (cashandcarrypayment.VerifyCreditCardPresentToogleBtnIsAppear() == true) {
                cashandcarrypayment.ClickCreditCardPresentToogleBtn();
                softassert.assertTrue(cashandcarrypayment.VerifyCreditCardPresentToogleBtnIsSelected(), "Test Step - 12 : Credit card is present toogle button is not turn on");
            } else {
                softassert.fail("***Credit card present toogle button is not displayed***");
            }*/
            /*
            if (cashandcarrypayment.VerifyCreditCardFirstNameIsDisabled() == true) {
                softassert.assertTrue(cashandcarrypayment.VerifyCreditCardFirstNameIsDisabled(), "Credit card first name field is not disabled");
                softassert.assertTrue(cashandcarrypayment.VerifyCreditCardLastNameIsDisabled(), "Credit card last name field is not disabled");
                softassert.assertTrue(cashandcarrypayment.VerifySelectCreditCardTypeIsDisabled(), "Credit card selected card type is not disabled");
                softassert.assertTrue(cashandcarrypayment.VerifyEnterCreditCardNumberIsDisabled(), "Credit card number field is not disabled");
                softassert.assertTrue(cashandcarrypayment.VerifyEnterCreditCardExpireDateIsDisabled(), "Credit card expire date is not disabled");
                softassert.assertTrue(cashandcarrypayment.VerifyEnterCreditCardCVVIsDisabled(), "Credit card cvv field is not disabled");
                softassert.assertTrue(cashandcarrypayment.VerifyEnterCreditCardZipCodeIsDisabled(), "Credit card zipcode field is not disabled");
            } else {
                softassert.fail("Test Step - 12 : Credit card tab all the fields are not disabled");
            }

            cashandcarrypayment.ClickCreditCardPresentToogleBtn();

            softassert.assertFalse(cashandcarrypayment.VerifyCreditCardFirstNameIsDisabled(), "Credit card first name field is not enabled");
            softassert.assertFalse(cashandcarrypayment.VerifyCreditCardLastNameIsDisabled(), "Credit card last name field is not enabled");
            softassert.assertFalse(cashandcarrypayment.VerifySelectCreditCardTypeIsDisabled(), "Credit card selected card type is not enabled");
            softassert.assertFalse(cashandcarrypayment.VerifyEnterCreditCardNumberIsDisabled(), "Credit card number field is not enabled");
            softassert.assertFalse(cashandcarrypayment.VerifyEnterCreditCardExpireDateIsDisabled(), "Credit card expire date is not enabled");
            softassert.assertFalse(cashandcarrypayment.VerifyEnterCreditCardCVVIsDisabled(), "Credit card cvv field is not enabled");
            softassert.assertFalse(cashandcarrypayment.VerifyEnterCreditCardZipCodeIsDisabled(), "Credit card zipcode field is not enabled");
*/
            // Test Step - 13
            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.VerifyErrorToastMsg(), "Error toast message is not displayed");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Please Enter First Name", "Toast message displayed are not matched as expected");

            // Test Step - 14
            cashandcarrypayment.EnterFirstNameOnCreditCardTab(ccfname);
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.VerifyErrorToastMsg(), "Error toast message is not displayed");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Please Enter Last Name", "Toast message displayed are not matched as expected");

            // Test Step - 15
            cashandcarrypayment.EnterLastNameOnCreditCardTab(cclname);
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.VerifyErrorToastMsg(), "Error toast message is not displayed");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Please Enter Card Number", "Toast message displayed are not matched as expected");

            // Test Step - 16
            cashandcarrypayment.EnterCreditCardNumberOnCreditCardTab(cccardnumber);
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.VerifyProcessPaymentButton(), "Process payment button is not displayed");

            // Test Step - 17
            cashandcarrypayment.EnterCreditCardExpireDateOnCreditCardTab(ccexpiredate);
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.getEnterCreditCardExpireDate(), "0628", "credit card expire date entered data is not displayed");
            cashandcarrypayment.EnterCreditCardCVVOnCreditCardTab(cccvv);
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.getEnterCreditCardCVV(), "567", "credit card cvv entered data is not displayed");
            cashandcarrypayment.EnterCreditCardZipCodeOnCreditCardTab(cczipcode);

            // Test Step - 18
            softassert.assertTrue(cashandcarrypayment.VerifyProcessPaymentButton(), "process payment button is not displayed");
            cashandcarrypayment.ClickProcessPaymentBtn();

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
