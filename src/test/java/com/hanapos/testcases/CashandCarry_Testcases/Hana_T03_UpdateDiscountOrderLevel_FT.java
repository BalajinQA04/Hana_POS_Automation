package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
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

public class Hana_T03_UpdateDiscountOrderLevel_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    public static final String dataSheetName = "Hana_T03";
    CustomSoftAssert softassert = new CustomSoftAssert();

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }
    public static LoggerUtil logger_Util;

    @Severity(SeverityLevel.NORMAL)
    @Owner("Balaji N")
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T03_Update_DiscountOrder_LevelTest(String searchandselectitemdescription, String itemdiscountpercentage, String couponcode, String discount) throws InterruptedException, IOException, NoSuchElementException {
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
            Allure.step("User on the hana pos login page");

            lp.EnterUserName(prop.getProperty("bestuname"));
            Allure.step("User entered the username as " + prop.getProperty("bestuname"));
            lp.EnterPassword(prop.getProperty("bestpass"));
            Allure.step("User entered the password as " + prop.getProperty("bestpass"));

            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("bestuname"), "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("bestuname"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("bestpass"), "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("bestpass"));

            lp.ClickLoginButton();
            Allure.step("Clicked on Login button..");

            dashboard = new HanaDashBoardPage();
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana dashboard Page does not navigated to hana dashboard page");
            Allure.step("User navigated to hana dashboard page sucess..");
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();

            Allure.step("User hover the mouse on New order and click on Cash and Carry..");
            Assert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3 : Cash And Carry page is not displayed");

            Allure.step("User verify the Cash and Carry page is displayed..");
            delayWithGivenTime(2000);
            cashandcarry.SelectShopName(prop.getProperty("bestshopname"));
            Allure.step("User selected the shopname on Cash and Carry page as " + prop.getProperty("bestshopname"));
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("bestshopname"), "Test Step - 4: Selected shopname is not matching with expected shopname as " + prop.getProperty("bestshopname"));

            cashandcarry.SelectClerkName(prop.getProperty("cashandcarryclerkname"));
            Allure.step("User selected the clerk name on Cash and Carry page as " + prop.getProperty("cashandcarryclerkname"));
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("cashandcarryclerkname"), "Test Step - 5: Selected clerkname is not matching with expected clerkname as " + prop.getProperty("cashandcarryclerkname"));

            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            Allure.step("User selected the employee name on Cash and Carry page as " + prop.getProperty("employeename"));
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 6: Selected employeename is not matching with expected employeename as " + prop.getProperty("employeename"));

            //Test Step - 7
            cashandcarry.SearchAndSelectProdByItemDescription(prop.getProperty("cashandcarry_product_short_description"), prop.getProperty("cashandcarry_product_description"));
            Allure.step("User selected the item description on Cash and Carry page as " + searchandselectitemdescription);
            softassert.assertEquals(cashandcarry.ItemCodeValueIsExists(), prop.getProperty("cashandcarry_product_itemcode_without_SKUcode"), "Test Step - 7 : Search and selected item description is not matched with displayed item code");
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), prop.getProperty("cashandcarry_product_description"), "Test Step - 7 : Search and selected item description is not matched with displayed item description");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 7 : Search and selected item description is not matched with displayed item quantity");
            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "40", "Test Step - 7 : Search and selected item description is not matched with displayed item price");
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 7 : Search and selected item description is not matched with displayed item discount amount");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 7 : Search and selected item description is not matched with displayed item discount percentage");

            //Test Step - 8
            cashandcarry.EnterDiscountPercentageOnAddItem(itemdiscountpercentage);
            Allure.step("User entered the discount percentage on Cash and Carry page as " + itemdiscountpercentage);
            softassert.assertEquals(cashandcarry.getDiscountPercentageOnDiscIsAddedByDiscAmtField(), "010", "Test Step - 8 : Entered discount percentage is not displayed on discount amount field cash and carry page");

            //Test Step - 9
            cashandcarry.ClickAddItem();
            Allure.step("User clicked on Add item button on Cash and Carry page");
            softassert.assertTrue(cashandcarry.VerifyAddedItem(), "Test Step - 9 : Item is not added successfully on table grid on Cash and Carry page");

            //Test Step - 10
            softassert.assertFalse(cashandcarry.CouponcodeIsDisabled(), "Test Step - 10 : coupon code is enabled");
            Allure.step("User verified the coupon code textbox field is enabled");
            softassert.assertFalse(cashandcarry.DiscountPercentageFieldIsDisabled(), "Test Step - 10 : Discount percentage textbox field is enabled");
            Allure.step("User verified the discount percentage textbox field is enabled");
            softassert.assertFalse(cashandcarry.DiscountAmountFieldIsDisabled(), "Test Step - 10 : Discount amount textbox field is enabled");
            Allure.step("User verified the discount amount textbox field is enabled");

            //Test - 11
            softassert.assertTrue(cashandcarry.DiscountChangeLinkIsDisplayed(), "Test Step - 11 : Discount change link is not displayed");
            Allure.step("User verified the discount change link is displayed");

            //Test Step - 12
            cashandcarry.ClickChangeLinkOnDiscountPercentageField();
            Allure.step("User clicked on Discount change link on Cash and Carry page");

            //Test Step - 13
            softassert.assertTrue(cashandcarry.verifyChangePopUpMessage(), "Test Step - 13 : Change pop up message is not displayed");
            softassert.assertTrue(cashandcarry.VerifyWarningIconIsDisplayed(), "Test Step - 13 : Warning icon is not displayed");
            softassert.assertTrue(cashandcarry.VerifyUpdatingDiscountText(), "Test Step - 13 : update discount text is not displayed");
            softassert.assertTrue(cashandcarry.ChangeDiscountNoButton(), "Test Step - 13 : No button is not displayed");
            softassert.assertTrue(cashandcarry.ChangeDiscountYesButton(), "Test Step - 13 : Yes button is not displayed");
            Allure.step("User verified the change pop up message is displayed");
            Allure.step("User verified the warning icon is displayed");
            Allure.step("User verified the update discount text is displayed");
            Allure.step("User verified the No button is displayed");
            Allure.step("User verified the Yes button is displayed");

            //Test Step - 14
            cashandcarry.ClickChangeDiscountNoButton();
            Allure.step("User clicked on No button on Change pop up message");

            //Test Step - 15
            cashandcarry.ClickChangeLinkOnDiscountPercentageField();
            Allure.step("User clicked on Discount change link on Cash and Carry page");

            //Test Step - 16
            delayWithGivenTime(2000);
            cashandcarry.ClickChangeDiscountYesButton();
            Allure.step("User clicked on Yes button on Change pop up message");

            softassert.assertTrue(cashandcarry.CouponcodeIsDisabled(), "Test Step - 16 : coupon code is not enabled");
            softassert.assertTrue(cashandcarry.DiscountPercentageFieldIsDisabled(), "Test Step - 16 : Discount percentage textbox field is not enabled");
            softassert.assertTrue(cashandcarry.DiscountAmountFieldIsDisabled(), "Test Step - 16 : Discount amount textbox field is not enabled");
            delayWithGivenTime(2000);

            //Test Step - 17
            cashandcarry.SearchAndSelectProdByItemDescription(prop.getProperty("cashandcarry_product_short_description"), prop.getProperty("cashandcarry_product_full_desc"));
            Allure.step("User selected the item description on Cash and Carry page as " + searchandselectitemdescription);

            //Test Step - 18
            cashandcarry.ClickAddItem();
            Allure.step("User clicked on Add item button on Cash and Carry page");

            //Test Step - 19
            delayWithGivenTime(2000);
            cashandcarry.SetCouponCode(couponcode);
            softassert.assertEquals(cashandcarry.get_entered_coupon_code_value(), "Automation", "Test Step - 19 - Entered coupon code on Cash and Carry page is not displayed as Automation");
            Allure.step("User entered the coupon code on Cash and Carry page as " + couponcode);
            cashandcarry.ClickDiscountDollarField();
            Allure.step("User click on Discount dollar field on Cash and Carry page");

            //Test Step - 20
            delayWithGivenTime(2000);
            cashandcarry.EnterDiscount(discount);
            softassert.assertEquals(cashandcarry.get_discountTextBox_value(), "10.00", "Test Step - 20 - Entered Discount on Cash and Carry page is not displayed as 10.00");
            Allure.step("User entered the discount on Cash and Carry page as " + discount);
            delayWithGivenTime(2000);
            cashandcarry.ClickDiscountDollarField();
            Allure.step("User click on Discount dollar field on Cash and Carry page");
            //Test Step - 21
            delayWithGivenTime(3000);
            softassert.assertEquals(cashandcarry.getCouponCode(), "", "Test Step - 21 - coupon code is not getting blank");
            Allure.step("User verified the coupon code is blank");

            // Test Step - 22
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.verifyChangeDiscountOnProductLevelIsDispalyed(), "Test Step - 22 - Change discount on product level link is not displayed");
            Allure.step("User verified the Change discount on product level link is displayed");

            //Test Step - 23
            delayWithGivenTime(2000);
            cashandcarry.ClickChangeDiscountOnProductLevel();
            Allure.step("User clicked on Change discount on product level link on Cash and Carry page");
            softassert.assertTrue(cashandcarry.verifyChangeDiscountOnProductLevelPopupIsDispalyed(), "Test Step - 23 - Change discount on product level popup is not displayed");
            Allure.step("User verified the Change discount on product level popup is displayed");

            //Test Step - 24
            cashandcarry.ClickChangeDiscountOnProductLevelYesButton();
            Allure.step("User clicked on Yes button on Change discount on product level popup");
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.getDiscountAmountvalueOrderLevel(), "0.00", "Test Step - 24 - Discount amount is not 0.00 on order level");
            Allure.step("User verified the discount amount is 0.00 on order level");
            logger.info("**** Ended Hana_T03_UpdateDiscountOrderLevel_CashAndCarryTest  ****");
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
