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

public class Hana_T07_WalkingSettings_EditCashRegister_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    public static final String dataSheetName = "Hana_T06";
    CustomSoftAssert softassert = new CustomSoftAssert();
    Faker faker = new Faker();
    String clerk_id;
    String clerk_id1;
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data", priority = 1)
    public void Validate_AddNew_CashRegister_Test(
            String clerkid,
            String clerkdescription,
            String printername,
            String manufacturer,
            String model,
            String cashdrawcode,
            String printercutcode,
            String customercopynote,
            String merchantcopynote) {
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            logger.info("Entering valid username and password..");
            lp.EnterUserName(prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            logger.info("Clicked on Login button..");

            dashboard = new HanaDashBoardPage();

            //Test Step - 2
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana dashboard Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User selected the shop name as " + prop.getProperty("shopname") + "in dashboard page");
            softassert.assertEquals(dashboard.get_selected_shopname_from_hanadashboard(), prop.getProperty("shopname"), "Test Step - 2: Selected shop name is not matching with expected shop name as " + prop.getProperty("shopname"));

            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();

            //Test Step - 3
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            Assert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3 : Cash And Carry page is not displayed");

            logger.info("User verify the Cash and Carry page is displayed..");

            //Test Step - 4
            cashandcarry.ClickWalkingSettingIcon();
            delayWithGivenTime(4000);
            softassert.assertTrue(cashandcarry.verifyWalkingSettingPopupIsDisplayed(), "Test Step - 4 : Walking Setting pop up is not displayed");
            delayWithGivenTime(3000);

            //Test Step - 5
            cashandcarry.ClickAddNewButtonOnWalkingSettingPopup();

            //Test Step - 6
            softassert.assertTrue(cashandcarry.VerifyAddNewWalkingSettingPopupIsDisplayed(), "Test Step - 6 : Add new walkins settings popup is not displayed");
            clerk_id = String.valueOf(faker.number().numberBetween(1000, 9999));
            delayWithGivenTime(2000);
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

            //Test Step - 11 - test failed due to validation error
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
            delayWithGivenTime(2000);
            cashandcarry.EnterClerkIDonAddnewPopup(clerk_id);
            cashandcarry.EnterClerkDescriptiononAddnewPopup(clerkdescription);
            cashandcarry.EnterPrinterNameonAddnewPopup("Automation");
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

            softassert.assertEquals(cashandcarry.getEnteredClerkIDonAddnewPopup(), clerk_id, "Test Step - 18 : Clerk ID is not matching");
            softassert.assertEquals(cashandcarry.getEnteredClerkDescriptiononAddnewPopup(), clerkdescription, "Test Step - 18 : Clerk Description is not matching");
            softassert.assertEquals(cashandcarry.getEnteredPrinterNameonAddnewPopup(), "Automation", "Test Step - 18 : Printer Name is not matching");
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

        } catch (Exception e) {
            softassert.fail("Known Issue: \n" + e.getMessage());
        } finally {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
            softassert.assertAll();
        }
    }

    @Severity(SeverityLevel.NORMAL)
    @Owner("Balaji N")
    @Epic("Cash and Carry Module")
    @Test(enabled = true, priority = 2, dependsOnMethods = "Validate_AddNew_CashRegister_Test", groups = {"Regression"})
    public void Validate_Hana_T07_WalkingSetting_Edit_CashRegister_Test() {
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
            logger.info("User on the hana pos login page");

            logger.info("Entering valid username and password..");
            lp.EnterUserName(prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            logger.info("Clicked on Login button..");

            dashboard = new HanaDashBoardPage();

            //Test Step - 2
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana dashboard Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            softassert.assertEquals(dashboard.get_selected_shopname_from_hanadashboard(), prop.getProperty("shopname"), "Test Step - 2: Selected shop name is not matching with expected shop name as " + prop.getProperty("shopname"));

            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();

            //Test Step - 3
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            Assert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3 : Cash And Carry page is not displayed");

            logger.info("User verify the Cash and Carry page is displayed..");

            //Test Step - 4
            cashandcarry.ClickWalkingSettingIcon();
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.verifyWalkingSettingPopupIsDisplayed(), "Test Step - 4 : Walking Setting pop up is not displayed");
            delayWithGivenTime(1000);

            //Test Step - 5
            softassert.assertEquals(cashandcarry.geteditBtnToolTiponAddnewPopupIsDisplayed(), "Edit", "Test Step - 5 : Edit button tool tip text is not displayed");

            //Test Step - 6
            delayWithGivenTime(1000);
            cashandcarry.ClickNewlyCreatedEditBtnOnWalkInSetting(clerk_id);
            delayWithGivenTime(1000);

            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.VerifyAddNewWalkingSettingPopupIsDisplayed(), "Test Step - 6 : Add New Walking Setting pop up is not displayed");

            //Test Step - 7
            delayWithGivenTime(3000);
            clerk_id1 = String.valueOf(faker.number().numberBetween(1000, 9999));
            delayWithGivenTime(2000);
            cashandcarry.EnterClerkIDonAddnewPopup(clerk_id);
            delayWithGivenTime(1000);
            cashandcarry.ClickCancelBtnonAddnewPopup();

            //Test Step - 8
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.verifyWalkingSettingPopupIsDisplayed(), "Test Step - 8 : Walking Setting pop up is not displayed");
            delayWithGivenTime(1000);
            cashandcarry.ClickNewlyCreatedEditBtnOnWalkInSetting(clerk_id);
            delayWithGivenTime(2000);

            softassert.assertTrue(cashandcarry.VerifyAddNewWalkingSettingPopupIsDisplayed(), "Test Step - 8 : Add New Walking Setting pop up is not displayed");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getEnteredClerkIDonAddnewPopup(), clerk_id, "Test Step - 8 : Clerk ID is not entered");
            softassert.assertEquals(cashandcarry.getEnteredClerkDescriptiononAddnewPopup(), "Test Automation Clerk description", "Test Step - 8 : Clerk Description is not entered");
            softassert.assertEquals(cashandcarry.getEnteredPrinterNameonAddnewPopup(), "Automation", "Test STep - 8 : Printer Name is not entered");

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getFirstSelectedManufacturerOptionOnAddNewPopup(), "Epson", "Test Step - 8 : Manufacturer is not selected");
            softassert.assertEquals(cashandcarry.getFirstSelectedModelOptionOnAddNewPopup(), "M129C", "Test Step - 8 : Model is not selected");

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getCashdrawcodeOnAddnewPopup(), "27,112,0,64,240", "Test Step - 8 : Cash Draw Code is not selected");
            softassert.assertEquals(cashandcarry.getPrinterCutCodeOnAddnewPopup(), "27,109", "Test Step - 8 : Printer Cut Code is not selected");

            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.custCopyCheckBoxonAddNewpopUPIsSelected(), "Test Step - 8 : Customer Copy CheckBox is not selected");
            softassert.assertTrue(cashandcarry.merchantCopyCheckBoxonAddNewpopUPIsSelected(), "Test Step - 8 : Merchant Copy CheckBox is not selected");
            delayWithGivenTime(1500);

            //Test Step - 9
            delayWithGivenTime(1000);
            cashandcarry.EnterClerkIDonAddnewPopup(clerk_id1);
            delayWithGivenTime(1000);
            cashandcarry.ClickSaveBtnonAddnewPopup();
            softassert.assertTrue(cashandcarry.VerifySuccessMessageIsDisplayed(), "Test Step - 9 : Success message is not displayed");
            delayWithGivenTime(1000);

            softassert.assertTrue(cashandcarry.verifyWalkingSettingPopupIsDisplayed(), "Test Step - 9 : Walking Setting pop up is not displayed");

            cashandcarry.ClickNewlyCreatedEditBtnOnWalkInSetting(clerk_id1);
            delayWithGivenTime(2000);

            softassert.assertTrue(cashandcarry.VerifyAddNewWalkingSettingPopupIsDisplayed(), "Test Step - 10 : Add New Walking Setting pop up is not displayed");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getEnteredClerkIDonAddnewPopup(), clerk_id1, "Test Step - 10 : Clerk ID is not entered");
            softassert.assertEquals(cashandcarry.getEnteredClerkDescriptiononAddnewPopup(), "Test Automation Clerk description", "Test Step - 10 : Clerk Description is not entered");
            softassert.assertEquals(cashandcarry.getEnteredPrinterNameonAddnewPopup(), "Automation", "Test STep - 10 : Printer Name is not entered");

            logger.info("**** Completed Hana_T07_WalkingSettings_EditCashRegister_FT ****");
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
