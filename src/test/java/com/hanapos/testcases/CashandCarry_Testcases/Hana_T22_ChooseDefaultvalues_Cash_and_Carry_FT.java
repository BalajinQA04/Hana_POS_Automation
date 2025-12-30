package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import com.hanapos.pageObjects.*;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T22_ChooseDefaultvalues_Cash_and_Carry_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    public static final String dataSheetName = "Hana_T22";
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
    CustomSoftAssert softassert = new CustomSoftAssert();

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    public static LoggerUtil logger_Util;

    //retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Severity(SeverityLevel.NORMAL)
    @Owner("Balaji N")
    @Description("Hana_T22 :- Choose Default Values functionality on Cash and Carry Payment Page")
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"}, priority = 1, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T22_Choose_Default_Values_Page_Test(String bankname, String checknumber, String nameofcheck, String Splqty, String qty, String occasion, String salespersonname, String customershortname,
                                                                  String sourcecode, String searchandselectitemcode) {
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
            logger.info("User entered password as " + prop.getProperty("bestpass"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("bestuname"), "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("bestpass"), "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            logger.info("Clicked on Login button..");

            dashboard = new HanaDashBoardPage();
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucessfully");

            // Test Step - 3
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            Assert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            cashandcarry.SelectShopName(prop.getProperty("bestshopname"));
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("bestshopname"), "Test Step - 4 : Shop name is not matched with selected shop name");

            // Test Step - 4
            delayWithGivenTime(2000);
            wait_For_Page_To_Be_Stable(getDriver());
            cashandcarry.ClickChooseDefaultValue();
            logger.info("User clicked on Choose Default Values button..");
            softassert.assertTrue(cashandcarry.ValidateChoosePageDefaultValuePopupIsDisplayed(), "Test Step - 4: Choose Default Values popup is not displayed - on Cash And Carry page");
            logger.info("User verify the Choose Default Values popup is displayed..");

            // Test Step - 5
            cashandcarry.SelectShopOnChoosePageDefaultValues(prop.getProperty("bestshopname"));
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getFirstSelectedOptionOnPageNameDD(), "Cash & Carry", "Test Step - 5 - Page Name is not displayed as Cash & Carry");

            // Test Step - 6
            cashandcarry.EnterBankNameOnBankNameOnChooseDefaultValues(bankname);
            logger.info("User entered Bank Name as " + bankname);
            softassert.assertEquals(cashandcarry.get_entered_bankNameOnBankNameOnChooseDefaultValues(), bankname, "Test Step - 6: Entered Bank Name is not matching with expected Bank Name as " + bankname);

            // Test Step - 7
            cashandcarry.EnterCheckNoOnCheckNoOnChooseDefaultValues(checknumber);
            logger.info("User entered Check Number as " + checknumber);
            softassert.assertEquals(cashandcarry.getEnteredCheckNoOnCheckNoOnChooseDefaultValues(), checknumber, "Test Step - 7: Entered Check Number is not matching with expected Check Number as " + checknumber);

            // Test Step - 8
            cashandcarry.EnterNameofCheckOnChooseDefaultValues(nameofcheck);
            logger.info("User entered Name of Check as " + nameofcheck);
            softassert.assertEquals(cashandcarry.getEnteredNameofCheckOnChooseDefaultValues(), nameofcheck, "Test Step - 8: Entered Name of Check is not matching with expected Name of Check as " + nameofcheck);

            // Test Step - 9
            cashandcarry.EnterSplCharInQuantityOnChooseDefaultValues(Splqty);
            logger.info("User entered Special Character in Quantity as " + Splqty);
            softassert.assertEquals(cashandcarry.getQtyOnChooseDefaultValues(), Splqty, "Test Step - 9: Entered Special Character in Quantity is not matching with expected Special Character in Quantity as " + Splqty);

            cashandcarry.ClearQuantityFieldOnChooseDefaultValues();
            cashandcarry.EnterQuantityOnChooseDefaultValues(qty);
            logger.info("User entered Quantity as " + qty);
            softassert.assertEquals(cashandcarry.getEnteredDefaultQuantityOnChooseDefaultValue(), qty, "Test Step - 9: Entered Quantity is not matching with expected Quantity as " + qty);

            // Test Step - 10
            cashandcarry.SelectDefaultOccasionOnChooseDefaultValues(occasion);
            logger.info("User selected Occasion as " + occasion);
            softassert.assertEquals(cashandcarry.getDefaultoccasionselected(), occasion, "Test Step - 10: Selected Occasion is not matching with expected Occasion as " + cashandcarry.getDefaultoccasionselected());

            // Test Step - 11
            cashandcarry.SelectDefaultSalesPersonOnChooseDefaultValues(salespersonname);
            logger.info("User selected Sales Person as " + salespersonname);
            softassert.assertEquals(cashandcarry.getSalesPersonSelected(), salespersonname, "Test Step - 11: Selected Sales Person is not matching with expected Sales Person as " + cashandcarry.getSalesPersonSelected());

            //Test Step - 12
            cashandcarry.SelectDefaultSourceCodeOnChooseDefaultValues(sourcecode);
            logger.info("User selected Source Code as " + sourcecode);

            //TestStep - 13
            // softassert.fail("***Test Step - 13 - Toast error message for providing negative value on field is not displayed on Choose Default Values popup***");
            cashandcarry.ClickUpdateBtnOnChooseDefaultValues();
            logger.info("User clicked on Update button on Choose Default Values popup");
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.VerifySuccessMessageIsDisplayed(), "Test Step - 13 - ***Default values success toast message is not displayed***");
            logger.info("User verify the Default values success toast message is displayed..");
            delayWithGivenTime(2000);

            // TestStep - 14
            cashandcarry.ClickCloseIconOnChooseDefaultValuesPopup();
            logger.info("User clicked on Close icon on Choose Default Values popup");

            // Test Step - 15
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 15 - Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            //Test Step - 16
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.getItemQtyOnProductLevel(), "1", "Test Step - 16 - Quantity is not updated or autopopulated");
            logger.info("User verify the Quantity is updated successfully");

            // Test Step - 17
            softassert.assertEquals(cashandcarry.GetOccasionDDfirstOption(), "Birthday", "Test Step - 17 - Occasion is not updated or autopopulated");
            logger.info("User verify the Occasion is updated successfully");

            //Test Step - 18
            softassert.assertEquals(cashandcarry.GetEmployeeDDfirstOption(), "Abish David", "Test Step - 18 - Sales Person is not updated or autopopulated");
            logger.info("User verify the Sales Person is updated successfully");

            // Test Step - 19
            softassert.assertEquals(cashandcarry.GetSourceCodeDDfirstOption(), "Friend", "Test Step - 19 - Source Code is not updated or autopopulated");
            logger.info("User verify the Source Code is updated successfully");

            //Test Step - 20
            cashandcarry.SelectClerkName(prop.getProperty("cashandcarryclerkname"));
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("cashandcarryclerkname"), "Test Step - 20 - Clerk Name is not updated or autopopulated");

            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 20 - Item Description is not updated or autopopulated");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 20 - Item Quantity is not updated or autopopulated");

            if (cashandcarry.ItemPriceValueIsExist() == "299") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Step - 20 - Item price is not matched with search and selected item code");
            } else if (cashandcarry.ItemPriceValueIsExist() == "309") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "309", "Test Step - 20 - Item price is not matched with search and selected item code");
            }

            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 20 - Item Discount Amount is not updated or autopopulated");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 20 - Item Discount Percentage is not updated or autopopulated");
            logger.info("User verify the Item Details are updated successfully in the table grid");

            // Test Step - 21
            cashandcarry.ClickAddItem();

            // Test Step - 22
            cashandcarry.ClickParticularProdTitle();
            logger.info("User clicked on Particular Product Title as ballonsYY");
            softassert.assertEquals(cashandcarry.getAddedItemCodeRow2(), prop.getProperty("cashandcarry_product_itemcode"), "Test Step - 22 - Added Item Code row 2 is not updated or autopopulated in table grid");
            softassert.assertEquals(cashandcarry.getAddedItemDescriptionRow2(), prop.getProperty("cashandcarry_product_description"), "Test Step - 22 - Added item description in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemQuantityRow2(), "1", "Test Step - 22 - Added item quantity in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemPriceRow2(), "$40.00", "Test Step - 22 - Added item extended price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemExtentPriceRow2(), "$40.00", "Test Step - 22 - Added item price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountAmountRow2(), "$ 0.00", "Test Step - 22 - Added item discount amount in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountPercentageRow2(), "0.00", "Test Step - 22 - Added item discount percentage in row 2 is not displayed on the page");

            // Test Step - 23
            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 23 : Selected tax type is not displayed");

            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 23 : Selected occasion is not displayed");

            cashandcarry.ClickPayButton();
            logger.info("User clicked on Pay button");
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 23 - Cash And Carry payment page is not displayed");
            logger.info("User verify the Cash and Carry payment page is displayed..");

            // Test Step - 24
            cashandcarrypayment.ClickCheckTab();
            logger.info("User clicked on Check tab as payment method");

            // Test Step - 25
            softassert.assertEquals(cashandcarrypayment.getDisplayedCheckNumber(), "1844020000000010", "Test Step - 25 - Check number is not autopopulated");
            logger.info("User verify the Check number is autopopulated");

            // Test Step - 26
            softassert.assertEquals(cashandcarrypayment.getDisplayedBankName(), "Bank Of America", "Test Step - 26 - Bank name is not autopopulated");
            logger.info("User verify the Bank name is autopopulated");

            // Test Step - 27
            softassert.assertEquals(cashandcarrypayment.getDisplayedCheckName(), "Hana POS", "Test Step - 27 - Name of check is not autopopulated");
            logger.info("User verify the Name of check is autopopulated");

            // Test Step - 28customershortname prop.getProperty("custfullname")
            //cashandcarrypayment.SearchAndSelectCustomer("Abish David","Abish David" );
            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 28 - Order payment done successfully toast message is not displayed");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 28 - Order payment done successfully toast message text is not displayed");

            delayWithGivenTime(2000);
            if (cashandcarrypayment.getConfirmationPopup() == true) {
                softassert.assertTrue(cashandcarrypayment.VerifyOrderConfirmationPopup(), "Test Step - 28 - Order confirmation popup is not displayed");
                logger.info("User verify the order confirmation popup is displayed");
                softassert.assertTrue(cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo(), "Test Step - 28 - Order confirmation message and invoice number is not displayed");
                logger.info("User verify the order confirmation message and invoice number is displayed");
                cashandcarrypayment.GetInvoiceNumber();
                logger.info(("Generated Order invoice number is :" + cashandcarrypayment.GetInvoiceNumber()));
                cashandcarrypayment.GetTenderPrice();
                logger.info("The remaining amount given to customer is :" + cashandcarrypayment.GetTenderPrice());
            }

            // Test Step - 29
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();
            logger.info("User clicked on Close button on order confirmation popup");


        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
            softassert.assertAll();
        }
    }

    @Severity(SeverityLevel.NORMAL)
    @Owner("Balaji N")
    @Description("Hana_T25 :- Choose Default Values functionality on Phone Order Page")
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"}, priority = 2, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T25_Choose_Default_Values_OrderEntryAliasPhoneOrderPage_Test(String bankname, String checknumber, String nameofcheck, String Splqty, String qty, String occasion, String salespersonname, String customershortname,
                                                                                           String sourcecode, String searchandselectitemcode) {
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
            logger.info("User entered password as " + prop.getProperty("bestpass"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("bestuname"), "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("bestpass"), "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            logger.info("Clicked on Login button..");

            dashboard = new HanaDashBoardPage();
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucessfully");

            // Test Step - 3
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            Assert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3 - Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("bestshopname"));
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("bestshopname"), "Test Step - 4 : Shop name is not matched with selected shop name");

            wait_For_Page_To_Be_Stable(getDriver());
            cashandcarry.ClickChooseDefaultValue();
            logger.info("User clicked on Choose Default Values button..");
            softassert.assertTrue(cashandcarry.ValidateChoosePageDefaultValuePopupIsDisplayed(), "Test Step - 4 - Choose Default Values popup is not displayed");
            logger.info("User verify the Choose Default Values popup is displayed..");

            // Test Step - 5
            cashandcarry.SelectShopOnChoosePageDefaultValues(prop.getProperty("bestshopname"));
            softassert.assertEquals(cashandcarry.getSelectShopOnCPDV(), prop.getProperty("bestshopname"), "Test Step - 5 - Selected Shop is not displayed");
            logger.info("User verify the Page Name is displayed as Cash & Carry..");

            // Test Step - 6
            cashandcarry.SelectPageNameOnCPDV("PhoneOrder");
            softassert.assertEquals(cashandcarry.getFirstSelectedOptionOnPageNameDD(), "PhoneOrder", "Test Step - 6 - Page Name is not displayed as Phone Order");

            // Test Step - 7
            cashandcarry.EnterItemCodeOnCPDV("RRD");
            delayWithGivenTime(300);
            softassert.assertEquals(cashandcarry.getEnteredItemCodeOnCPDV(), "RRD", "Test Step - 7 - Entered item code is displayed");

            //Test Step - 8
            cashandcarry.EnterQtyonPhoneOrderCPDV("1");
            delayWithGivenTime(300);
            softassert.assertEquals(cashandcarry.getEnteredQtyonPhoneOrderCPDV(), "1", "Test Step - 8 - Entered Qty is displayed");

            // Test Step - 9
            cashandcarry.EnterQtyonPhoneOrderCPDV("AB*&");
            delayWithGivenTime(300);
            softassert.assertEquals(cashandcarry.getEnteredQtyonPhoneOrderCPDV(), "", "Test Step - 9 - Entered alpha & spl characters are accepted in qty field");
            cashandcarry.EnterQtyonPhoneOrderCPDV("1");
            softassert.assertEquals(cashandcarry.getEnteredQtyonPhoneOrderCPDV(), "1", "Test Step - 9 - Entered valid characters is not accepted in qty field");

            // Test Step - 10
            cashandcarry.EnterRecipientPhone("HANAPOSPHN");
            delayWithGivenTime(300);
            softassert.assertEquals(cashandcarry.getEnteredRecipientPhoneonPhoneOrderCPDV(), "", "Test Step - 10 - Entered alpha characters are accepted in recipent phone field");

            // Test Step - 11
            cashandcarry.EnterRecipientPhone("8324872387");
            delayWithGivenTime(300);
            softassert.assertEquals(cashandcarry.getEnteredRecipientPhoneonPhoneOrderCPDV(), "832-487-2387", "Test Step - 11 -Entered alpha characters are accepted in recipent phone field");

            // Test Step - 12
            cashandcarry.EnterZipcodeOnPhoneOrderCPDV("sd&^&");
            delayWithGivenTime(300);
            softassert.assertEquals(cashandcarry.getEnteredZipcodeOnPhoneOrderCPDV(), "", "Test Step - 12 - Entered alpha characters are accepted in zipcode field");

            // Test Step - 13
            cashandcarry.EnterZipcodeOnPhoneOrderCPDV("32013");
            delayWithGivenTime(300);
            softassert.assertEquals(cashandcarry.getEnteredZipcodeOnPhoneOrderCPDV(), "32013", "Test Step - 13 -Entered alpha characters are accepted in zipcode field");

            // Test Step - 14
            cashandcarry.SelectDefaultWireInMethod("FSN");
            delayWithGivenTime(300);
            softassert.assertEquals(cashandcarry.getSelectedDefaultWireInMethod(), "FSN", "Test Step - 14 - Selected data is not displayed");

            // Test Step - 15
            cashandcarry.SelectDefaultWireOutMethod("Bloomnet");
            delayWithGivenTime(300);
            softassert.assertEquals(cashandcarry.getSelectedDefaultWireOutMethod(), "Bloomnet", "Test Step - 15 - Selected data is not displayed");

            // Test Step - 16
            cashandcarry.SelectDefaultPaymentType("Check");
            delayWithGivenTime(300);
            softassert.assertEquals(cashandcarry.getSelectedDefaultPaymentType(), "Check", "Test Step - 16 - Selected data is not displayed");

            // Test Step -17
            cashandcarry.SelectDefaultCustomerType("Retail");
            delayWithGivenTime(300);
            softassert.assertEquals(cashandcarry.getSelectedDefaultCustomerType(), "Retail", "Test Step - 17 - Selected data is not displayed");

            // Test Step - 18
            cashandcarry.SelectDefaultSalesPerson("Stuart");
            delayWithGivenTime(300);
            softassert.assertEquals(cashandcarry.getSelectedDefaultSalesPerson(), "Stuart", "Test Step - 18 - Selected data is not displayed");

            // Test Step - 19
            cashandcarry.SelectDefaultCountry("United States");
            delayWithGivenTime(300);
            softassert.assertEquals(cashandcarry.getSelectedDefaultCountry(), "United States", "Test Step - 19 - Selected data is not displayed");

            // Test Step - 20
            cashandcarry.SelectDefaultLocation("Business");
            delayWithGivenTime(300);
            softassert.assertEquals(cashandcarry.getSelectedDefaultLocation(), "Business", "Test Step - 20 - Selected data is not displayed");

            // Test Step - 21
            cashandcarry.SelectDefaultOccasion("Birthday");
            delayWithGivenTime(300);
            softassert.assertEquals(cashandcarry.getSelectedDefaultOccasion(), "Birthday", "Test Step - 21 - Selected data is not displayed");

            // Test Step - 22
            cashandcarry.SelectDefaultOrderType("Delivery");
            delayWithGivenTime(300);
            softassert.assertEquals(cashandcarry.getSelectedDefaultOrderType(), "Delivery", "Test Step - 22 - Selected data is not displayed");

            cashandcarry.select_Source_Code_Dropdown("Friend");
            delayWithGivenTime(500);
            softassert.assertEquals(cashandcarry.getSelectedDefaultSourceCode(), "Friend", "Test Step - 22 - Selected Source Code is not displayed");

            // Test Step - 23
            cashandcarry.ClickCloseIconOnChooseDefaultValuesPage();
            Assert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 23 - Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 24
            delayWithGivenTime(3000);
            cashandcarry.ClickChooseDefaultValue();
            logger.info("User clicked on Choose Default Values button..");
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.ValidateChoosePageDefaultValuePopupIsDisplayed(), "Test Step - 24 - Choose Default Values popup is not displayed");
            logger.info("User verify the Choose Default Values popup is displayed..");

            cashandcarry.SelectShopOnChoosePageDefaultValues(prop.getProperty("bestshopname"));
            logger.info("User selected shop name as " + prop.getProperty("bestshopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.getFirstSelectedOptionOnPageNameDD(), "Cash & Carry", "Test Step - 24 - Page Name is not displayed as Cash & Carry");
            logger.info("User verify the Page Name is displayed as Cash & Carry..");
            softassert.assertEquals(cashandcarry.getSelectShopOnCPDV(), prop.getProperty("bestshopname"), "Test Step - 24 - Page Name is not displayed as Cash & Carry");
            logger.info("User verify the Page Name is displayed as Cash & Carry..");

            cashandcarry.SelectPageNameOnCPDV("PhoneOrder");

            cashandcarry.EnterItemCodeOnCPDV("RRD");
            softassert.assertEquals(cashandcarry.getEnteredItemCodeOnCPDV(), "RRD", "Test Step - 24 - Entered item code is displayed");

            cashandcarry.EnterQtyonPhoneOrderCPDV("1");
            softassert.assertEquals(cashandcarry.getEnteredQtyonPhoneOrderCPDV(), "1", "Test Step - 24 - Entered Qty is displayed");

            cashandcarry.EnterQtyonPhoneOrderCPDV("AB*&");

            softassert.assertEquals(cashandcarry.getEnteredQtyonPhoneOrderCPDV(), "", "Test Step - 24 - Entered alpha & spl characters are accepted in qty field");
            cashandcarry.EnterQtyonPhoneOrderCPDV("1");

            cashandcarry.EnterRecipientPhone("HANAPOSPHN");

            softassert.assertEquals(cashandcarry.getEnteredRecipientPhoneonPhoneOrderCPDV(), "", "Test Step - 24 - Entered alpha characters are accepted in recipent phone field");

            cashandcarry.EnterRecipientPhone("832-487-2387");

            softassert.assertEquals(cashandcarry.getEnteredRecipientPhoneonPhoneOrderCPDV(), "832-487-2387", "Test Step - 24 - Entered alpha characters are accepted in recipent phone field");

            cashandcarry.EnterZipcodeOnPhoneOrderCPDV("sd&^&");

            softassert.assertEquals(cashandcarry.getEnteredZipcodeOnPhoneOrderCPDV(), "", "Test Step - 24 - Entered alpha characters are accepted in zipcode field");

            cashandcarry.EnterZipcodeOnPhoneOrderCPDV("32013");

            softassert.assertEquals(cashandcarry.getEnteredZipcodeOnPhoneOrderCPDV(), "32013", "Test Step - 24 - Entered alpha characters are accepted in zipcode field");

            cashandcarry.SelectDefaultWireInMethod("FSN");

            softassert.assertEquals(cashandcarry.getSelectedDefaultWireInMethod(), "FSN", "Test Step - 24 - Selected data is not displayed");

            cashandcarry.SelectDefaultWireOutMethod("Bloomnet");

            softassert.assertEquals(cashandcarry.getSelectedDefaultWireOutMethod(), "Bloomnet", "Test Step - 24 - Selected data is not displayed");

            cashandcarry.SelectDefaultPaymentType("Check");

            softassert.assertEquals(cashandcarry.getSelectedDefaultPaymentType(), "Check", "Test Step - 24 - Selected data is not displayed");

            cashandcarry.SelectDefaultCustomerType("Corporate");

            softassert.assertEquals(cashandcarry.getSelectedDefaultCustomerType(), "Corporate", "Test Step - 24 - Selected data is not displayed");

            cashandcarry.SelectDefaultSalesPerson("Stuart");

            softassert.assertEquals(cashandcarry.getSelectedDefaultSalesPerson(), "Stuart", "Test Step - 24 - Selected data is not displayed");

            cashandcarry.SelectDefaultCountry("Canada");

            softassert.assertEquals(cashandcarry.getSelectedDefaultCountry(), "Canada", "Test Step - 24 - Selected data is not displayed");

            cashandcarry.SelectDefaultLocation("Business");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getSelectedDefaultLocation(), "Business", "Test Step - 24 - Selected data is not displayed");

            cashandcarry.SelectDefaultOccasion("Birthday");

            softassert.assertEquals(cashandcarry.getSelectedDefaultOccasion(), "Birthday", "Test Step - 24 - Selected data is not displayed");

            cashandcarry.SelectDefaultOrderType("Delivery");

            softassert.assertEquals(cashandcarry.getSelectedDefaultOrderType(), "Delivery", "Test Step - 24 - Selected data is not displayed");

            cashandcarry.ClickUpdateButtonOnChooseDefaultValuesPage();

            // Test Step - 25
            cashandcarry.ClickCloseIconOnChooseDefaultValuesPage();
            delayWithGivenTime(1000);

            softassert.assertTrue(cashandcarry.VerifySuccessMessageIsDisplayed(), "Test Step - 25 - Toast message is not displayed");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 25 - Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 26
            dashboard.ClickOrderEntry();
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            softassert.assertEquals(phoneorder.GetOrderEntryPageTitle(), "Hana | Order", "Test Step - 26 - Phone Order page is not displayed");

            // Test Step - 27
            phoneorder.ClickDefaultValuesIcon();
            phoneorder.VerifyChoosePageDefaultValuesPopup();

            // Test Step - 28
            softassert.assertEquals(phoneorder.getItemCodeOnCPDV(), "RRD", "Test Step - 28 - Entered Default values for item code are not displayed");
            softassert.assertEquals(phoneorder.getItemqtyOnPhoneOrderPage(), "1", "Test Step - 28 - Default values for item qty are not displayed");
            softassert.assertEquals(phoneorder.getDisplayedRecipientphoneOnPhoneOrderPage(), "832-487-2387", "Test Step - 28 - Default values for recipient phone are not displayed");
            softassert.assertEquals(phoneorder.getDisplayedRecipientZipcodeOnPhoneOrderPage(), "32013", "Test Step - 28 - Default values for recipient zipcode are not displayed");
            softassert.assertEquals(phoneorder.getSelectedWireInMethodOnPhoneOrderPage(), "FSN", "Test Step - 28 - Default values for wire in method are not displayed");
            softassert.assertEquals(phoneorder.getSelectedWireOutMethodOnPhoneOrderPage(), "Bloomnet", "Test Step - 28 - Default values for wire out method are not displayed");
            softassert.assertEquals(phoneorder.getSelectedPaymentTypeOnPhoneOrderPage(), "Check", "Test Step - 28 - Default values for payment type are not displayed");
            softassert.assertEquals(phoneorder.getSelectedCustomerTypeOnPhoneOrderPage(), "Corporate", "Test Step - 28 - Default values for customer type are not displayed");
            softassert.assertEquals(phoneorder.getSelectedSalesPersonOnPhoneOrderPage(), "Stuart", "Test Step - 28 - Default values for sales person are not displayed");
            softassert.assertEquals(phoneorder.getSelectedCountryOnPhoneOrderPage(), "Canada", "Test Step - 28 - Default values for country are not displayed");
            softassert.assertEquals(phoneorder.getSelectedLocationOnPhoneOrderPage(), "Business", "Test Step - 28 - Default values for location are not displayed");
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 28 - Default values for occasion are not displayed");
            softassert.assertEquals(phoneorder.getSelectedOrderTypeOnPhoneOrderPage(), "Delivery", "Test Step - 28 - Default values for order type are not displayed");

            // Test Step - 29
            phoneorder.ClickCloseIconOnChoosePageDefaultValuesPopup();

            // Test Step - 30
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), "RRD", "Test Step - 30 - Item code is not displayed on phone order page product details section");

            // Test Step - 31
            delayWithGivenTime(500);
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 31 - Item qty is not displayed on phone order page product details section");

            // Test Step - 32
            delayWithGivenTime(500);
            softassert.assertEquals(phoneorder.getRecipientPhoneOnPhoneOrderPage(), "832-487-2387", "Test Step - 32 - Recipient phone is not displayed on phone order page product details section");

            // Test Step - 33
            delayWithGivenTime(500);
            softassert.assertEquals(phoneorder.getRecipientZipcodeOnPhoneOrderPage(), "32013", "Test Step - 33 - Recipient zipcode is not displayed on phone order page product details section");

            // Test Step - 34
            delayWithGivenTime(500);
            phoneorder.ClickWireInMethodOnPhoneOrderPage();

            // Test Step - 35
            softassert.assertEquals(phoneorder.getDisplayedWireInSelectedOption(), "FSN", "Test Step - 35 - Wire in method is not displayed on phone order page product details section");
            ;

            // Test Step - 36
            delayWithGivenTime(500);
            phoneorder.ClickWireOutMethodOnPhoneOrderPage();

            // Test Step - 37
            softassert.assertEquals(phoneorder.getDisplayedWireOutSelectedOption(), "Bloomnet", "Test Step - 37 - Wire out method is not displayed on phone order page product details section");

            // Test Step - 38
            delayWithGivenTime(500);
            phoneorder.ClickdeliveryTypeOnPhoneOrderPage();

            // Test Step - 39
            softassert.assertEquals(phoneorder.getDisplayedPaymentTypeSelectedOption(), "Check", "Test Step - 39 - After changing the delivery type - \nSelected Payment type is not displayed on phone order page Payment Section");
            delayWithGivenTime(500);
            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            softassert.assertEquals(phoneorder.getDisplayedPaymentTypeSelectedOption(), "Check", "Test Step - 39 - After changing the delivery type - \nSelected Payment type is not displayed on phone order page payment section");

            // Test Step - 40
            delayWithGivenTime(500);
            softassert.assertEquals(phoneorder.getSelectedCustTypeOnPhoneOrderPage(), "Corporate", "Test Step - 40 - Customer type is not displayed on phone order page product details section");

            // Test Step - 41
            delayWithGivenTime(500);
            softassert.assertEquals(phoneorder.getDisplayedSalesPersonSelectedOption(), "Stuart Markwood", "Test Step - 41 - Sales person is not displayed on phone order page product details section");

            // Test Step - 42
            delayWithGivenTime(500);
            softassert.assertEquals(phoneorder.getDisplayedCountryonRecipientSecOnPhoneOrderPage(), "Canada", "Test Step - 42 - Country is not displayed on phone order page product details section");

            // Test Step - 43
            delayWithGivenTime(500);
            softassert.assertEquals(phoneorder.getDisplayedLocationonRecipientSecOnPhoneOrderPage(), "Business", "Test Step - 43 - Location is not displayed on phone order page product details section");

            // Test Step - 44
            delayWithGivenTime(500);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 44 - Occasion is not displayed on phone order page product details section");

            // Test Step - 45
            delayWithGivenTime(1000);
            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Test Step - 45 - Pickup type is not highlighted in blue color");
            delayWithGivenTime(2000);
            phoneorder.ClickdeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_OnDelivery_TypeOnPhoneOrderPage(), "#676a6c", "Test Step - 45 - Delivery type is not highlighted in blue color");
            delayWithGivenTime(1000);
            phoneorder.ClickWireInMethodOnPhoneOrderPage();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnWireInTypeOnPhoneOrderPage(), "#676a6c", "Test Step - 45 - Wire In as delivery type is not highlighted in blue color");
            delayWithGivenTime(1000);
            phoneorder.ClickWireOutMethodOnPhoneOrderPage();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_On_WireOut_PhoneOrderPage(), "#2f9bc8", "Test Step - 45 - Wire out Delivery type is not highlighted in blue color");
            logger.info("**** Finished Hana_T25_ChooseDefaultvalues_OrderEntryAliasPhoneOrder_FT Test Case  ****");
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
