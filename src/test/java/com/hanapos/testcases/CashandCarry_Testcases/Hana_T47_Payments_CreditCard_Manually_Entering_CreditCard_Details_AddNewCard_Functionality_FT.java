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

public class Hana_T47_Payments_CreditCard_Manually_Entering_CreditCard_Details_AddNewCard_Functionality_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    public static final String dataSheetName = "Hana_T47";
    CustomSoftAssert softassert = new CustomSoftAssert();
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    //retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T47_Payments_CreditCard_Manually_Entering_CreditCard_Details_AddNewCard_Test(String searchandselectitemcode, String customername, String occasion, String ccfname, String cclname, String creditcardtype, String cccardnumber, String ccexpiredate, String cccvv, String cczipcode) throws InterruptedException, IOException {

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
            delayWithGivenTime(2000);
            dashboard = new HanaDashBoardPage();
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            // Test Step - 3
            dashboard.SelectShopNameDropDown(prop.getProperty("bestshopname"));
            logger.info("User selected the shop name as " + prop.getProperty("bestshopname") + "in dashboard page");
            delayWithGivenTime(2000);
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
            softassert.assertEquals(cashandcarry.getAddedItemCodeRow2(), "rrd", "Test Step - 9: Added particular tile product is not displayed");
            logger.info("User verify add the title product to the Cash and Carry page is displayed..");

            // Test Step - 10
            cashandcarry.EnterCustomerName(prop.getProperty("cust_firstName"), prop.getProperty("custfullname"));
            softassert.assertEquals(cashandcarry.getDisplayedCustomerName(), prop.getProperty("custfullname"), "Known Issue Test Step - 9 - Customer name is not matched with entered customer name");

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 10 : Selected tax type is not displayed");

            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 10 : Selected occasion is not displayed");
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

            // Below line are commented due to worked on best florist shop
            /*
             * if(cashandcarrypayment.VerifyCreditCardPresentToogleBtnIsAppear()==true) {
             * cashandcarrypayment.ClickCreditCardPresentToogleBtn();
             * softassert.assertTrue(cashandcarrypayment.
             * VerifyCreditCardPresentToogleBtnIsSelected()
             * ,"Test Step - 12 : Credit card is present toogle button is not turn on");
             * }else {
             * softassert.fail("***Credit card present toogle button is not displayed***");
             * }
             */

            // In best florist it seems like all fields are enabled

            /*
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
            // In best florist there is no toogle button to turn on/off credit card.
            //so it is not required to click on toogle button.
            //	cashandcarrypayment.ClickCreditCardPresentToogleBtn();

            // Test Step - 14
            cashandcarrypayment.EnterFirstNameOnCreditCardTab(ccfname);

            softassert.assertEquals(cashandcarrypayment.getEnteredCreditCardFirstName(), "Automation", "First name is not displayed");

            // Test Step - 15
            cashandcarrypayment.EnterLastNameOnCreditCardTab(cclname);

            softassert.assertEquals(cashandcarrypayment.getEnteredCreditCardLastName(), "Test", "Last name is not displayed");

            // Test Step - 16
            cashandcarrypayment.SelectCreditCardTypeOnCreditCardTab(creditcardtype);

            // Test Step - 17
            cashandcarrypayment.EnterCreditCardNumberOnCreditCardTab(prop.getProperty("creditcardnum"));

            softassert.assertEquals(cashandcarrypayment.getEnterCreditCardNumber(), "4111111111111111", "Test Step - 17 - credit card number entered data is not displayed");

            // Test Step - 18
            cashandcarrypayment.EnterCreditCardExpireDateOnCreditCardTab(prop.getProperty("ccexpiredate"));

            softassert.assertEquals(cashandcarrypayment.getEnterCreditCardExpireDate(), "1030", "Test Step - 18 - credit card expire date entered data is not displayed");

            // Test Step - 19
            cashandcarrypayment.EnterCreditCardCVVOnCreditCardTab(prop.getProperty("cccvv"));

            softassert.assertEquals(cashandcarrypayment.getEnterCreditCardCVV(), "123", "credit card cvv entered data is not displayed");

            // Test Step - 20
            cashandcarrypayment.EnterCreditCardZipCodeOnCreditCardTab(cczipcode);

            softassert.assertEquals(cashandcarrypayment.getEnterCreditCardZipCode(), "32012", "credit card zipcode entered data is not displayed");

            // Test Step - 21
            softassert.assertTrue(cashandcarrypayment.VerifyProcessPaymentButton(), "Process payment button is not displayed");
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.VerifyErrorToastMsg(), "Error toast message is not displayed");
            logger.info("Order payment done success message is cannot able to test on live environment");
            delayWithGivenTime(2000);
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
