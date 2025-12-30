package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import com.github.javafaker.Faker;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T06_WalkingSettings_AddNewCashRegister_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    public static final String dataSheetName = "Hana_T06";
    CustomSoftAssert softassert = new CustomSoftAssert();
    Faker faker = new Faker();
    String clerk_id;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    //retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Severity(SeverityLevel.NORMAL)
    @Owner("Balaji N")
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T06_WalkingSetting_AddNew_CashRegister_Test(String clerkid, String clerkdescription, String printername, String manufacturer,
                                                                          String model, String cashdrawcode, String printercutcode, String customercopynote, String merchantcopynote) {
        //Test Step - 1
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");
            logger_Util.startNetworkLogging(testCaseName);

            lp.EnterUserName(prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            dashboard = new HanaDashBoardPage();

            //Test Step - 2
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana dashboard Page does not navigated to hana dashboard page");
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();

            //Test Step - 3
            Assert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3 : Cash And Carry page is not displayed");

            //Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("shopname")); //"Hana POS (Canada)"
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("shopname"), "Test Step - 4 : Shop name is not matched with selected shop name");

            cashandcarry.ClickWalkingSettingIcon();
            delayWithGivenTime(4000);
            softassert.assertTrue(cashandcarry.verifyWalkingSettingPopupIsDisplayed(), "Test Step - 4 : Walking Setting pop up is not displayed");
            delayWithGivenTime(3000);

            //Test Step - 5
            cashandcarry.ClickAddNewButtonOnWalkingSettingPopup();

            //Test Step - 6
            softassert.assertTrue(cashandcarry.VerifyAddNewWalkingSettingPopupIsDisplayed(), "Test Step - 6 : Add new walkins settings popup is not displayed");
            clerk_id = String.valueOf(faker.number().numberBetween(1000, 9999));

            cashandcarry.EnterClerkIDonAddnewPopup(clerk_id);
            softassert.assertEquals(cashandcarry.getEnteredClerkIDonAddnewPopup(), clerk_id, "Test Step - 6: Entered clerk id is not matching with expected clerk id as " + clerkid);

            cashandcarry.EnterClerkDescriptiononAddnewPopup(clerkdescription);
            softassert.assertEquals(cashandcarry.getEnteredClerkDescriptiononAddnewPopup(), clerkdescription, "Test Step - 6: Entered clerk description is not matching with expected clerk description as " + clerkdescription);

            cashandcarry.EnterPrinterNameonAddnewPopup(printername);
            softassert.assertEquals(cashandcarry.getEnteredPrinterNameonAddnewPopup(), printername, "Test Step - 6: Entered printer name is not matching with expected printer name as " + printername);

            // Test Step - 7
            cashandcarry.SelectManufacturerOnAddNewPopup(manufacturer);
            softassert.assertEquals(cashandcarry.getFirstSelectedManufacturerOptionOnAddNewPopup(), manufacturer, "Test Step - 7 : Selected Manufacturer is not matching");

            // Test Step - 8
            cashandcarry.SelectModelOnAddNewPopup(model);
            softassert.assertEquals(cashandcarry.getFirstSelectedModelOptionOnAddNewPopup(), model, "Test Step - 8 : Selected Model is not matching");

            //Test Step - 9
            softassert.assertEquals(cashandcarry.getCashdrawcodeOnAddnewPopup(), cashdrawcode, "Test Step - 9 : Cash draw code is not matching");

            //Test Step - 10
            softassert.assertEquals(cashandcarry.getPrinterCutCodeOnAddnewPopup(), printercutcode, "Test Step - 10 : Printer cut code is not matching");

            //Test Step - 11
            delayWithGivenTime(3000);
            cashandcarry.SelectManufacturerOnAddNewPopup(manufacturer);

            //Test Step - 12
            cashandcarry.SelectModelOnAddNewPopup(model);
            softassert.assertEquals(cashandcarry.getCashdrawcodeOnAddnewPopup(), cashdrawcode, "Test Step - 12 : Cash draw code is not matching");
            softassert.assertEquals(cashandcarry.getPrinterCutCodeOnAddnewPopup(), printercutcode, "Test Step - 12 : Printer cut code is not matching");

            //Test Step - 13
            cashandcarry.ClickCustomerCopyCheckboxOnAddnewPopup();
            softassert.assertTrue(cashandcarry.custCopyCheckBoxonAddNewpopUPIsSelected(), "Test Step - 13 : Customer Copy Checkbox is not selected");
            cashandcarry.ClickMerchantCopyCheckboxOnAddnewPopup();
            softassert.assertTrue(cashandcarry.merchantCopyCheckBoxonAddNewpopUPIsSelected(), "Test Step - 13 : Merchant Copy Checkbox is not selected");

            //Test Step - 14
            cashandcarry.EnterCustCopyNoteonAddnewPopup(customercopynote);
            softassert.assertEquals(cashandcarry.getEnteredCustCopyNoteonAddnewPopup(), customercopynote, "Test Step - 14 : Customer Copy Note is not matching");

            //Test Step - 15
            cashandcarry.EnterMerchantCopyNoteonAddnewPopup(merchantcopynote);
            softassert.assertEquals(cashandcarry.getEnteredMerchantCopyNoteonAddnewPopup(), merchantcopynote, "Test Step - 15 : Merchant Copy Note is not matching");

            //Test Step - 16
            cashandcarry.ClickCancelBtnonAddnewPopup();

            // Test Step - 17
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.verifyWalkingSettingPopupIsDisplayed(), "Test Step - 17 : Walking Setting pop up is not displayed");
            cashandcarry.ClickAddNewButtonOnWalkingSettingPopup();
            softassert.assertTrue(cashandcarry.VerifyAddNewWalkingSettingPopupIsDisplayed(), "Test Step - 17 : Add new walkins settings popup is not displayed");

            cashandcarry.EnterClerkIDonAddnewPopup(clerk_id);
            cashandcarry.EnterClerkDescriptiononAddnewPopup(clerkdescription);
            cashandcarry.EnterPrinterNameonAddnewPopup(printername);
            cashandcarry.SelectManufacturerOnAddNewPopup(manufacturer);
            cashandcarry.SelectModelOnAddNewPopup(model);
            softassert.assertEquals(cashandcarry.getCashdrawcodeOnAddnewPopup(), cashdrawcode, "Test Step - 17 : Cash draw code is not matching");
            softassert.assertEquals(cashandcarry.getPrinterCutCodeOnAddnewPopup(), printercutcode, "Test Step - 17 : Printer cut code is not matching");
            cashandcarry.SelectManufacturerOnAddNewPopup(manufacturer);
            cashandcarry.SelectModelOnAddNewPopup(model);
            softassert.assertEquals(cashandcarry.getCashdrawcodeOnAddnewPopup(), cashdrawcode, "Test Step - 17 : Cash draw code is not matching");
            softassert.assertEquals(cashandcarry.getPrinterCutCodeOnAddnewPopup(), printercutcode, "Test Step - 17 : Printer cut code is not matching");
            cashandcarry.ClickCustomerCopyCheckboxOnAddnewPopup();
            softassert.assertTrue(cashandcarry.custCopyCheckBoxonAddNewpopUPIsSelected(), "Test Step - 17 : Customer Copy Checkbox is not selected");
            cashandcarry.ClickMerchantCopyCheckboxOnAddnewPopup();
            softassert.assertTrue(cashandcarry.merchantCopyCheckBoxonAddNewpopUPIsSelected(), "Test Step - 17 : Merchant Copy Checkbox is not selected");
            cashandcarry.EnterCustCopyNoteonAddnewPopup(customercopynote);
            softassert.assertEquals(cashandcarry.getEnteredCustCopyNoteonAddnewPopup(), customercopynote, "Test STep - 17 : Customer Copy Note is not matching");
            cashandcarry.EnterMerchantCopyNoteonAddnewPopup(merchantcopynote);
            softassert.assertEquals(cashandcarry.getEnteredMerchantCopyNoteonAddnewPopup(), merchantcopynote, "Test Step - 17 : Merchant Copy Note is not matching");

            cashandcarry.ClickSaveBtnonAddnewPopup();
            softassert.assertTrue(cashandcarry.VerifySuccessMessageIsDisplayed(), "Test Step - 17 : Success message is not displayed");
            softassert.assertTrue(cashandcarry.VerifyWalkingSettingNewlyAdded(), "Test Step - 17 : Walking Setting is not added");
            delayWithGivenTime(1000);

            //Test Step - 18
            cashandcarry.ClickNewlyCreatedEditBtnOnWalkInSetting(clerk_id);
            delayWithGivenTime(1000);

            softassert.assertEquals(cashandcarry.getEnteredClerkIDonAddnewPopup(), clerk_id, "Test Step - 18 : Clerk ID: " + clerk_id + " is not matching");
            softassert.assertEquals(cashandcarry.getEnteredClerkDescriptiononAddnewPopup(), clerkdescription, "Test Step - 18 : Clerk Description is not matching");
            softassert.assertEquals(cashandcarry.getEnteredPrinterNameonAddnewPopup(), printername, "Test Step - 18 : Printer Name is not matching");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getFirstSelectedManufacturerOptionOnAddNewPopup(), manufacturer, "Test Step - 18 : Manufacturer is not matching");
            softassert.assertEquals(cashandcarry.getFirstSelectedModelOptionOnAddNewPopup(), model, "Test Step - 18 : Model is not matching");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getCashdrawcodeOnAddnewPopup(), cashdrawcode, "Test Step - 18 : Cash draw code is not matching");
            softassert.assertEquals(cashandcarry.getPrinterCutCodeOnAddnewPopup(), printercutcode, "Test Step - 18 : Printer cut code is not matching");
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.custCopyCheckBoxonAddNewpopUPIsSelected(), "Test Step - 18 : Customer Copy Checkbox is not selected");
            softassert.assertTrue(cashandcarry.merchantCopyCheckBoxonAddNewpopUPIsSelected(), "Test Step - 18 : Merchant Copy Checkbox is not selected");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getEnteredCustCopyNoteonAddnewPopup(), customercopynote, "Test Step - 18 : Customer Copy Note is not matching");
            softassert.assertEquals(cashandcarry.getEnteredMerchantCopyNoteonAddnewPopup(), merchantcopynote, "Test Step - 18 : Merchant Copy Note is not matching Known Issue");
        } catch (Exception e) {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
            softassert.fail("Known Issue:\n" + e.getMessage());
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
