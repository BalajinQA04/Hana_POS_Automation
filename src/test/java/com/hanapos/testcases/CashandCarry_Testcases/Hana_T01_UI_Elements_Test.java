package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

/**
 * BDD Steps:
 * Feature: Validate UI elements on Cash and Carry page
 *   Given the user is on the Hana POS login page
 *   When the user logs in and navigates to the Cash and Carry page
 *   Then all required UI elements should be displayed and have correct default values
 *   And the Choose Default Value popup should show correct default options and icons
 *
 * This Hana_T01 testcase will validate whether all the UI elements are displayed on cash and carry page
 *
 * @Author Balaji N
 */
public class Hana_T01_UI_Elements_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    public static final String dataSheetName = "TC_002_CashAndCarryTest";
    CustomSoftAssert softassert = new CustomSoftAssert();

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }
    public static LoggerUtil logger_Util;

    @Test(priority = 1, groups = {"Regression"}, enabled = true)
    @Severity(SeverityLevel.MINOR)
    @Owner("Balaji N")
    @Epic("Cash and Carry Module")
    public void Validate_Hana_T01_CashAndCarryPage_UI_ElementsTest() {
        // Step: Start test and log initial info
        logger.info("**** Starting  Hana-T01_CashAndCarry_UI_ElementsTest  ****");
        logger.debug("capturing application debug logs....");

        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            // Step: Instantiate LoginPage and verify login page is displayed
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            Allure.step("User on the hana pos login page");

            // Step: Enter username and password
            lp.EnterUserName(prop.getProperty("username"));
            Allure.step("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            Allure.step("User entered the password as " + prop.getProperty("password"));

            // Step: Assert entered credentials match expected
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("password"));

            // Step: Click login button
            lp.ClickLoginButton();
            Allure.step("User clicked on Login button");
            logger.info("User clicked on Login button");

            // Step: Instantiate dashboard and verify navigation
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Hana dashboard page is not displayed");
            Allure.step("User navigated to hana dashboard page sucessfully");
            logger.info("User navigated to hana dashboard page");

            // Step: Select shop name for Cash and Carry
            dashboard.SelectShopNameDropDown(prop.getProperty("cashandcarryshopname"));
            softassert.assertEquals(dashboard.get_selected_shopname_from_hanadashboard(), prop.getProperty("cashandcarryshopname"), "Test Step - 2: Selected shopname is not matching with expected shopname as " + prop.getProperty("cashandcarryshopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("cashandcarryshopname"));
            Allure.step("User select the shopname on dashbaord page as " + prop.getProperty("cashandcarryshopname"));

            // Step: Click Cash and Carry menu
            dashboard.CashAndCarryMenuClick();
            Allure.step("User hover the mouse on New order and click on Cash and Carry..");

            // Step: Instantiate CashAndCarryPage and verify navigation
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");
            Allure.step("User verify the Cash and Carry page is displayed..");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Step: Verify Shop Name dropdown is displayed
            softassert.assertTrue(cashandcarry.shopnamedropdownIsDisplayed(), "***Shop Name dropdown field is not displayed***");
            Allure.step("User verify the Shop Name dropdown field is displayed..");

            // Step: Verify Clerk dropdown is displayed
            softassert.assertTrue(cashandcarry.clerkdropdownIsDisplayed(), "***clerk dropdown field is not displayed***");
            Allure.step("User verify the clerk dropdown field is displayed..");

            // Step: Verify Employee dropdown is displayed
            softassert.assertTrue(cashandcarry.employeedropdownIsDisplayed(), "***employee dropdown field is not displayed***");
            Allure.step("User verify the employee dropdown field is displayed..");

            // Step: Verify Gift Card and Tiles section is displayed
            softassert.assertTrue(cashandcarry.GiftCardAndTilesIsDisplayed(), "***Gift card and tiles is not displayed***");
            // Step: Verify Item Entry labels are displayed
            softassert.assertTrue(cashandcarry.ItemEntryLabels(), "***Item entry labels are not displayed***");
            // Step: Verify Item Code placeholder
            softassert.assertEquals(cashandcarry.ItemCodePlaceholder(), "Item Code", "***Item code placeholder is not displayed***");
            // Step: Verify Item Description placeholder
            softassert.assertEquals(cashandcarry.ItemDescription(), "Description", "***Item description placeholder is not displayed***");
            // Step: Verify Item Quantity placeholder
            softassert.assertEquals(cashandcarry.ItemQtyPlaceholder(), "Qty", "***Item quantity placeholder is not displayed***");
            // Step: Verify Item Price placeholder
            softassert.assertEquals(cashandcarry.ItemPricePlaceholder(), "Price", "***Item price placeholder is not displayed***");
            // Step: Verify Item Discount placeholder
            softassert.assertEquals(cashandcarry.ItemDiscountPlaceholder(), "Discount", "***Item total placeholder is not displayed***");
            // Step: Verify Item Discount Percentage placeholder
            softassert.assertEquals(cashandcarry.ItemDiscountPercentagePlaceholder(), "Discount %", "***Item discount placeholder is not displayed***");
            // Step: Verify Coupon Code placeholder
            softassert.assertEquals(cashandcarry.CouponCodePlaceHolder(), "Enter Coupon Code", "***Coupon code placeholder is not displayed***");
            // Step: Verify Discount Percentage textbox placeholder
            softassert.assertEquals(cashandcarry.DiscountpercentageTextbox(), "Enter Discount(%)", "***Discount percentage placeholder is not displayed***");
            // Step: Verify Discount Amount placeholder
            softassert.assertEquals(cashandcarry.DiscountAmountPlaceHolder(), "Enter Discount Amount", "***Discount amount placeholder is not displayed***");
            // Step: Verify Tax Type placeholder
            softassert.assertEquals(cashandcarry.TaxTypePlaceholder(), "Tax Type", "***Tax type placeholder is not displayed***");
            // Step: Verify Select Customer placeholder
            softassert.assertEquals(cashandcarry.SelectCustomerPlaceholder(), "Select Customer", "***Select customer placeholder is not displayed***");
            // Step: Verify Customer Add button is displayed
            softassert.assertTrue(cashandcarry.CustomerAddButton(), "***Customer Add button is not displayed***");

            // Step: Verify Reconcile button is displayed
            softassert.assertTrue(cashandcarry.ReconcileBtnIsDisplayed(), "***Reconcile button is not displayed***");
            // Step: Verify Payout button is displayed
            softassert.assertTrue(cashandcarry.PayOutBtnIsDisplayed(), "***Payout button is not displayed***");
            // Step: Verify Sales History button is displayed
            softassert.assertTrue(cashandcarry.SalesHistoryIsDisplayed(), "***Sales history button is not displayed***");
            // Step: Verify Open Drawer button is displayed
            softassert.assertTrue(cashandcarry.OpenDrawerIsDisplayed(), "***Open drawer button is not displayed***");

            // Step: Verify Alt+P text/button is displayed
            softassert.assertTrue(cashandcarry.AltPTextIsDisplayed(), "***Alt+P button is not displayed***");
            // Step: Verify Split Payment toggle button is displayed
            softassert.assertTrue(cashandcarry.ValidateSplitPaymentToogleButton(), "***Split payment toggle button is not displayed***");

            // Step: Verify Sub Total label and default value
            softassert.assertTrue(cashandcarry.ValidateSubTotalLabelPresence(), "***Sub total label is not displayed***");
            softassert.assertEquals(cashandcarry.ValidateSubTotalDefaultValue(), "0.00", "***Sub total default value is not displayed***");
            // Step: (GST/PST/HST/QST commented out)
            // softassert.assertTrue(cashandcarry.ValidateGSTLabel(), "***GST label is not displayed***");
            // softassert.assertEquals(cashandcarry.ValidateGSTDefaultValue(), "0.00", "***GST default value is not displayed***");
            // softassert.assertTrue(cashandcarry.ValidatePSTHSTQSTLabel(), "***PST/HST/QST label is not displayed***");
            // softassert.assertEquals(cashandcarry.ValidatePSTHSTQSTDefaultValues(), "0.00", "***PST/HST/QST default value is not displayed***");
            // Step: Verify Tax label and default value
            softassert.assertTrue(cashandcarry.ValidateTaxLabel(), "***Tax label is not displayed***");
            softassert.assertEquals(cashandcarry.ValidateTaxDefaultValue(), "0.00", "***Tax default value is not displayed***");
            // Step: Verify Discount label and default value
            softassert.assertTrue(cashandcarry.ValidateDiscountLabel(), "***Discount label is not displayed***");
            softassert.assertEquals(cashandcarry.ValidateDiscountDefaultValues(), "0.00", "***Discount default value is not displayed***");
            // Step: Verify Grand Total label and default value
            softassert.assertTrue(cashandcarry.ValidateGrandTotalLabel(), "***Grand total label is not displayed***");
            softassert.assertEquals(cashandcarry.ValidateGrandTotalDefaultValue(), "0.00", "***Grand total default value is not displayed***");

            // Step: Delay for UI stabilization
            delayWithGivenTime(2000);
            // Step: Click Home icon and verify dashboard
            dashboard.ClickOnHomeIcon();
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Hana dashboard page is not displayed");
            logger.info("User navigated to hana dashboard page sucess..");
            // Step: Select shop name for next test
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            softassert.assertEquals(dashboard.get_selected_shopname_from_hanadashboard(), prop.getProperty("shopname"), "shop name is not matched");

            // Step: Click Cash and Carry menu again
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Step: Open Choose Default Value popup and verify
            cashandcarry.ClickChooseDefaultValue();
            softassert.assertTrue(cashandcarry.ValidateChoosePageDefaultValuePopupIsDisplayed());
            // Step: Select default values in popup
            cashandcarry.SelectDefaultOccasion();
            cashandcarry.SelectDefaultSalesPerson();
            cashandcarry.SelectDefaultSourcecode();
            cashandcarry.ClickUpdateButtonOnChooseDefaultPopup();

            // Step: Verify Employee dropdown default option
            if (cashandcarry.GetEmployeeDDfirstOption().equals("--Select User--")) {
                softassert.assertEquals(cashandcarry.GetEmployeeDDfirstOption(), "--Select User--", "default employee is not matched in choose default values popup");
            } else if (cashandcarry.GetEmployeeDDfirstOption().equals("Abish David")) {
                softassert.assertEquals(cashandcarry.GetEmployeeDDfirstOption(), "Abish David", "default employee is not matched in choose default values popup");
            }

            // Step: Verify Occasion dropdown default option
            if (cashandcarry.GetOccasionDDfirstOption().equals("--Choose Occasion--")) {
                softassert.assertEquals(cashandcarry.GetOccasionDDfirstOption(), "--Choose Occasion--", "default Occasion is not matched in choose default values popup");
            } else if (cashandcarry.GetOccasionDDfirstOption().equals("Birthday")) {
                softassert.assertEquals(cashandcarry.GetOccasionDDfirstOption(), "Birthday", "default Occasion is not matched in choose default values popup");
            } else {
                softassert.fail("default Occasion is not matched in choose default values popup");
            }

            // Step: Verify Source Code dropdown default option
            softassert.assertEquals(cashandcarry.GetSourceCodeDDfirstOption(), "--Select Source Code--", "default sourcecode is not matched in choose default values popup");
            // Step: Verify icons in popup
            softassert.assertTrue(cashandcarry.WalkingSettingIconIsExist(), "***Walking setting icon is not displayed in choose default values popup***");
            softassert.assertTrue(cashandcarry.ChooseDefaultValueIconIsExist(), "***Choose default value icon is not displayed in choose default values popup***");
            logger.info("***** Finished Hana-T01_CashAndCarry_UI_ElementsTest *****");
        } catch (Exception e) {
            // Step: Attach logs and fail on exception
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
            softassert.fail(e.getMessage());
        } finally {
            try {
                // Step: Assert all collected soft assertions
                softassert.assertAll();
            } catch (AssertionError ae) {
                // Step: Attach logs and rethrow on assertion error
                logger_Util = new LoggerUtil();
                logger_Util.attachNetworkLogs(testCaseName);
                ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
                throw ae;
            }
        }

    }


}
