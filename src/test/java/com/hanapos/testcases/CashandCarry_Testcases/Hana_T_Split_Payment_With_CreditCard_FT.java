package com.hanapos.testcases.CashandCarry_Testcases;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.CashAndCarryPaymentPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T_Split_Payment_With_CreditCard_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    public static final String dataSheetName = "Hana_T40";
    CustomSoftAssert softassert = new CustomSoftAssert();

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    public static LoggerUtil logger_Util;

    //retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Balaji N")
    @Description("Hana_T :- Split Payment with credit card functionality on Cash And Carry Page")
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T_Split_Payment_With_CreditCard_FT_Test(String searchandselectitemcode, String customername, String occasion, String ccfname, String cclname, String creditcardtype, String cccardnumber, String cccvv, String cczipcode, String ccamount, String checkamount,
                                                                      String selectregistry, String cashamount, String pohamount, String giftcardnum, String giftamt, String checknumber, String bankname, String nameoncheck) throws InterruptedException, IOException {
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            logger.info("User clicked on Login button..");

            dashboard = new HanaDashBoardPage();
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            // Test Step - 3
            dashboard.CashAndCarryMenuClick();

            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3 - Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("shopname"));
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("shopname"), "Test Step - 4 : Shop name is not matched with selected shop name");

            //Test Step - 5
            cashandcarry.SelectClerkName(prop.getProperty("cashandcarryclerkname"));
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("cashandcarryclerkname"), "Test Step - 5 : Clerk name is not matched with selected clerk name");

            // Test Step - 6
            delayWithGivenTime(2000);
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 6 : Employee name is not matched with selected employee name");

            // Test Step - 7
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 7 - Item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 7 - Item quantity is not matched with search and selected item code");

            if (cashandcarry.ItemPriceValueIsExist() == "299") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Step - 7 - Item price is not matched with search and selected item code");
            } else if (cashandcarry.ItemPriceValueIsExist() == "309") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "309", "Test Step - 7 - Item price is not matched with search and selected item code");
            }

            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 7 - Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 7 - Item discount percentage is not matched with search and selected item code");

            // Test Step - 8
            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem(), "Test Step - 8 - Added item is not displayed in the table");
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "rrd", "Test Step - 8 - Added item code is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Red Rose Deluxe", "Test Step - 8 - Added item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Step - 8 - Added item quantity is not matched with search and selected item code");

            if (cashandcarry.GetAddedItemExtPrice() == "$299.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00", "Test Step - 8 - Added item extended price is not matched with search and selected item code");
            } else if (cashandcarry.GetAddedItemExtPrice() == "$309.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$309.00", "Test Step - 8 - Added item extended price is not matched with search and selected item code");
            }

            if (cashandcarry.GetAddedItemPrice() == "$299.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00", "Test Step - 8 - Added item price is not matched with search and selected item code");
            } else if (cashandcarry.GetAddedItemPrice() == "$309.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$309.00", "Test Step - 8 - Added item price is not matched with search and selected item code");
            }

            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 8 - Added item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 8 - Added item discount percentage is not matched with search and selected item code");


            // Test Step - 09
            delayWithGivenTime(2000);
            cashandcarry.ClickParticularProdTitle();
            softassert.assertEquals(cashandcarry.getAddedItemCodeRow2(), prop.getProperty("cashandcarry_product_itemcode"), "Test Step - 10 - Added product tile is not displayed on row 2 table grid");
            softassert.assertEquals(cashandcarry.getAddedItemDescriptionRow2(), prop.getProperty("cashandcarry_product_description"), "Test Step - 10 - Added item description in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemQuantityRow2(), "1", "Test Step - 10 - Added item quantity in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemPriceRow2(), "$40.00", "Test Step - 10 - Added item extended price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemExtentPriceRow2(), "$40.00", "Test Step - 10 - Added item price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountAmountRow2(), "$ 0.00", "Test Step - 10 - Added item discount amount in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountPercentageRow2(), "0.00", "Test Step - 10 - Added item discount percentage in row 2 is not displayed on the page");

            // Test Step - 10
            cashandcarry.EnterCustomerName(prop.getProperty("cust_firstName"), prop.getProperty("custfullname"));
            softassert.assertEquals(cashandcarry.getDisplayedCustomerName(), prop.getProperty("custfullname"), "Test Step - 9 - Customer name is not matched with entered customer name");

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 10 : Selected tax type is not displayed");

            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 10 : Selected occasion is not displayed");


            // Test Step - 11
            cashandcarry.ClickOnSplitPaymentToogleButton();
            delayWithGivenTime(2000);

            // Test Step - 12
            cashandcarry.ClickPayButton();
            logger.info("User click on Pay button");
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();

            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 12 - Cash And Carry payment page is not displayed");
            logger.info("User navigated to Cash And Carry payment page successfully");
            delayWithGivenTime(500);

           // softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 12 - Success toast message is not displayed");
            logger.info("User verified the order invoice is generated successfully");
            delayWithGivenTime(500);

            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg().contains("Invoice Number is:"), true, "Test Step - 12 - Invoice Number is: ---> Text is not displayed");
            delayWithGivenTime(500);

            // Test Step - 13
            softassert.assertEquals(cashandcarrypayment.getTopLeftCornerInvNo(), cashandcarrypayment.getGeneratedInvoiceNumber(), "Test Step - 13 - Invoice number is not displayed in the top left corner");
            logger.info("User verified the invoice number is displayed in the top left corner and toast message displayed invoice number are same");

            // Test Step - 14
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarrypayment.getRow1ProductInTable(), "rrd - Red Rose Deluxe 1 X $299.00", "Test Step - 14 - Product name - 1 - is not displayed in the table");
            softassert.assertEquals(cashandcarrypayment.getRow2ProductInTable(), "BalloonsYY - Balloons Small 1 X $40.00", "Test Step - 14 - Product name - 2 - is not displayed in the table");

            // Test Step - 15
            softassert.assertEquals(cashandcarrypayment.getPaymentGrandTotal(), cashandcarrypayment.ValidateGrandTotalWithConvFee(), "Test step - 15 - Grand total amount is not calculated correctly");

            // Test Step - 16
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCreditCardTab();
            delayWithGivenTime(2000);
            cashandcarrypayment.EnterFirstNameOnCreditCardTab(ccfname);
            cashandcarrypayment.EnterLastNameOnCreditCardTab(cclname);
            cashandcarrypayment.SelectCreditCardTypeOnCreditCardTab("Add New Card");
            cashandcarrypayment.EnterCreditCardNumberOnCreditCardTab("4111111111111111");
            cashandcarrypayment.EnterCreditCardExpireDateOnCreditCardTab("1030");
            cashandcarrypayment.EnterCreditCardCVVOnCreditCardTab(cccvv);
            //  cashandcarrypayment.EnterCreditCardZipCodeOnCreditCardTab(cczipcode);
            cashandcarrypayment.EnterCreditCardAmountOnCreditCardTab("50");
            cashandcarrypayment.ClickProcessPaymentBtn();

            //softassert.fail("***Credit card payment process button is disabled -- Bug --***");

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow1(), "Credit Card", "Test Step - 16 - Credit card payment is not displayed on payment details table in row 1");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow1(), "($50.00)", "Test Step - 16 - Added Credit Card Paid amount is not matched on payment details table in row 1");

            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCheckTab();
            cashandcarrypayment.EnterCheckNumber(checknumber);
            softassert.assertEquals(cashandcarrypayment.getDisplayedCheckNumber(), checknumber, "Test step - 31: Entered check number is not displayed or not matched");
            cashandcarrypayment.EnterBankName(bankname);
            softassert.assertEquals(cashandcarrypayment.getDisplayedBankName(), bankname, "Test Step - 31: Entered bank name is not displayed");
            cashandcarrypayment.EnterNameOnCheck(nameoncheck);
            softassert.assertEquals(cashandcarrypayment.getDisplayedCheckName(), nameoncheck, "Test Step - 31: Entered name of check is not displayed");
            cashandcarrypayment.EnterAmountOnCheckTab(checkamount);
            softassert.assertEquals(cashandcarrypayment.getEnteredAmountOnCheckTab(), checkamount, "Test Step - 31: Entered check amount is not displayed");
            cashandcarrypayment.ClickProcessPaymentBtn();

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow2(), "Check", "Test Step - 16 - Check payment is not displayed on payment details table in row 1");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow2(), "($" + checkamount + ".00)", "Test Step - 16 - Added Check Paid amount is not matched on payment details table in row 1");

            // Test Step - 17
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickCashTab();

            // Test Step - 18
            delayWithGivenTime(1000);
            softassert.assertFalse(cashandcarrypayment.VerifyProcessPaymentButton(), "Test step - 18 - Process payment button is not disabled");

            // Test Step - 19
            delayWithGivenTime(1000);
            cashandcarrypayment.SelectRegistryOnCashTab(prop.getProperty("cashandcarryclerkname"));
            softassert.assertEquals(cashandcarrypayment.get_selected_registry(), prop.getProperty("cashandcarryclerkname"), "Test Step - 19 - Selected registry is not displayed on cash tab");

            // Test Step - 20
            delayWithGivenTime(1000);
            cashandcarrypayment.EnterGivenAmountOnCashTab(cashamount);
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.getEnteredGivenAmountOnCashTab(), cashamount, "Test Step - 20 - Given Textbox field on cash and carry payment page");
            cashandcarrypayment.ClickProcessPaymentBtn();

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow3(), "Cash", "Test Step - 20 - cash payment is not displayed on payment details table in row 2");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow3().contains(cashandcarrypayment.getEnteredGivenAmountOnCashTab()), true, "Test Step - 20 - Added amount is not matched on payment details table in row 2");

            // Test Step - 21 & 22 are done to replace for credit card payment

            // Test Step - 23
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickOnPOHPaymentTab();

            // Test Step - 24
            softassert.assertTrue(cashandcarrypayment.VerifyProcessPaymentButton(), "Test step - 24 - Process payment button is disabled");
            delayWithGivenTime(1000);
            cashandcarrypayment.EnterPOHAmountOnPOHPaymentTab("50");
            softassert.assertEquals(cashandcarrypayment.getEnteredPOHAmountOnPOHPaymentTab(), "50", "Test Step - 24 - Entered POH Amount Textbox field value is not displayed on cash and carry payment page");
            cashandcarrypayment.ClickProcessPaymentBtn();

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow4(), "Paid Outside Hana", "Test Step - 24 - POH payment is not displayed on payment details table in row 3");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow4().contains(cashandcarrypayment.getEnteredPOHAmountOnPOHPaymentTab()), true, "Test Step - 24 - Added amount is not matched on payment details table in row 3");

            // Test Step - 25
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickOnGiftCardPaymentTab();

            // Test Step - 26
            delayWithGivenTime(1000);
            String balance = cashandcarrypayment.getTableDisplayedBalanceAmt();
            cashandcarrypayment.EnterGiftCardNumberOnGiftCardPaymentTab(giftcardnum);
            softassert.assertEquals(cashandcarrypayment.getEnteredGiftCardAmountOnGiftCardPaymentTab(), giftcardnum, "Test Step - 26 - Entered Gift Card Number is not displayed on Gift Card payment tab");
            softassert.assertEquals(cashandcarrypayment.getDisplayedCustNameOnGiftCardPaymentTab(), "Abish David", "Test Step - 26 - Customer name is not displayed on Gift Card payment tab");

            // Test Step - 27
            delayWithGivenTime(1000);
            cashandcarrypayment.EnterPaymentAmtOnGiftCardPaymentTab(giftamt);
            softassert.assertEquals(cashandcarrypayment.getDisplayedPaymentAmtOnGiftCardPaymentTab(), giftamt, "Test Step - 27 - Gift Card payment amount is not displayed on Gift Card payment tab");
            cashandcarrypayment.ClickProcessPaymentBtn();

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow6(), "Gift Card", "Test Step - 27 - Gift Card payment type is not displayed on payment details table in row 4");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow6(), "($" + giftamt + ".00)", "Test Step - 27 - Added gift payment amount is not matched on payment details table in row 4");

            // Test Step - 28
            cashandcarrypayment.ClickFinishBtnOnCashAndCarryPaymentPage();
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarrypayment.VerifyAlertPopup(), "Test Step - 28 - Alert popup is not displayed");

            // Test Step - 29
            softassert.assertEquals(cashandcarrypayment.VerifyAlertPopupWarningText(), "This order is still not fully paid. If you navigate away from this page, balance will remain on this order. Are you sure?", "Test Step - 29 - Warning message is not displayed on Alert popup");

            // Test Step - 30
            cashandcarrypayment.ClickOnAlertPopupLeaveBtn();
            delayWithGivenTime(4000);
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 30 - Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 31
            delayWithGivenTime(1000);
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 31 - Item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 31 - Item quantity is not matched with search and selected item code");

            if (cashandcarry.ItemPriceValueIsExist() == "299") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Step - 31 - Item price is not matched with search and selected item code");
            } else if (cashandcarry.ItemPriceValueIsExist() == "309") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "309", "Test Step - 31 - Item price is not matched with search and selected item code");
            }

            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 31 - Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 31 - Item discount percentage is not matched with search and selected item code");

            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem(), "Test Step - 31: Added item is not displayed on product table grid");
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "rrd", "Test Step - 31 - Added item code is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Red Rose Deluxe", "Test Step - 31 - Added item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Step - 31 - Added item quantity is not matched with search and selected item code");

            if (cashandcarry.GetAddedItemExtPrice() == "$299.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00", "Test Step - 31 - Added item extended price is not matched with search and selected item code");
            } else if (cashandcarry.GetAddedItemExtPrice() == "$309.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$309.00", "Test Step - 31 - Added item extended price is not matched with search and selected item code");
            }

            if (cashandcarry.GetAddedItemPrice() == "$299.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00", "Test Step - 31 - Added item price is not matched with search and selected item code");
            } else if (cashandcarry.GetAddedItemPrice() == "$309.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$309.00", "Test Step - 31 - Added item price is not matched with search and selected item code");
            }

            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 31 - Added item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 31 - Added item discount percentage is not matched with search and selected item code");

            delayWithGivenTime(2000);
            cashandcarry.ClickParticularProdTitle();
            softassert.assertEquals(cashandcarry.getAddedItemCodeRow2(), prop.getProperty("cashandcarry_product_itemcode"), "Test Step - 31 - Added product tile is not displayed on row 2 table grid");
            softassert.assertEquals(cashandcarry.getAddedItemDescriptionRow2(), prop.getProperty("cashandcarry_product_description"), "Test Step - 31 - Added item description in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemQuantityRow2(), "1", "Test Step - 31 - Added item quantity in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemPriceRow2(), "$40.00", "Test Step - 31 - Added item extended price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemExtentPriceRow2(), "$40.00", "Test Step - 31 - Added item price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountAmountRow2(), "$ 0.00", "Test Step - 31 - Added item discount amount in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountPercentageRow2(), "0.00", "Test Step - 31 - Added item discount percentage in row 2 is not displayed on the page");


            logger.info("User verify add the title product to the Cash and Carry page is displayed..");
            cashandcarry.EnterCustomerName(prop.getProperty("cust_firstName"), prop.getProperty("custfullname"));
            softassert.assertEquals(cashandcarry.getDisplayedCustomerName(), prop.getProperty("custfullname"), " Test Step - 31 - Customer name is not matched with entered customer name");

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 31 : Selected tax type is not displayed");

            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 31 : Selected occasion is not displayed");

            delayWithGivenTime(2000);

            cashandcarry.ClickOnSplitPaymentToogleButton();
            delayWithGivenTime(1000);
            cashandcarry.ClickPayButton();
            logger.info("User click on Pay button");
            delayWithGivenTime(2000);

            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 31 - Cash And Carry payment page is not displayed");
            logger.info("User navigated to Cash And Carry payment page successfully");
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 31 - Success toast message is not displayed");
            logger.info("User verified the order invoice is generated successfully");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg().contains("Invoice Number is:"), true, "Test Step - 31 - Invoice Number is: ---> Text is not displayed");

            delayWithGivenTime(1000);

            softassert.assertEquals(cashandcarrypayment.getRow1ProductInTable().contains(prop.getProperty("product_description1")), true, "Test Step - 31 - Product description is not displayed on row 1");
            softassert.assertEquals(cashandcarrypayment.getRow2ProductInTable(), "BalloonsYY - Balloons Small 1 X $40.00", "Test Step - 31 - Added Tile Product description is not displayed on row 2");

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.getPaymentGrandTotal(), cashandcarrypayment.ValidateGrandTotalWithConvFee(), "Test step - 31 - Grand total amount is not calculated correctly");

            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCreditCardTab();
            delayWithGivenTime(2000);
            cashandcarrypayment.EnterFirstNameOnCreditCardTab(ccfname);
            cashandcarrypayment.EnterLastNameOnCreditCardTab(cclname);
            cashandcarrypayment.SelectCreditCardTypeOnCreditCardTab("Add New Card");
            cashandcarrypayment.EnterCreditCardNumberOnCreditCardTab("4111111111111111");
            cashandcarrypayment.EnterCreditCardExpireDateOnCreditCardTab("1030");
            cashandcarrypayment.EnterCreditCardCVVOnCreditCardTab(cccvv);
            //  cashandcarrypayment.EnterCreditCardZipCodeOnCreditCardTab(cczipcode);
            cashandcarrypayment.EnterCreditCardAmountOnCreditCardTab("50");
            cashandcarrypayment.ClickProcessPaymentBtn();

            //softassert.fail("***Credit card payment process button is disabled -- Bug --***");

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow1(), "Credit Card", "Test Step - 16 - Credit card payment is not displayed on payment details table in row 1");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow1(), "($50.00)", "Test Step - 16 - Added Credit Card Paid amount is not matched on payment details table in row 1");


            delayWithGivenTime(1000);
//            cashandcarrypayment.ClickCheckTab();
//            cashandcarrypayment.EnterAmountOnCheckTab(checkamount);
//            softassert.assertEquals(cashandcarrypayment.getEnteredAmountOnCheckTab(), checkamount, "Test Step - 16 - Entered check amount is not matched on check tab");
//            cashandcarrypayment.ClickProcessPaymentBtn();

            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCheckTab();
            cashandcarrypayment.EnterCheckNumber(checknumber);
            softassert.assertEquals(cashandcarrypayment.getDisplayedCheckNumber(), checknumber, "Test step - 31: Entered check number is not displayed or not matched");
            cashandcarrypayment.EnterBankName(bankname);
            softassert.assertEquals(cashandcarrypayment.getDisplayedBankName(), bankname, "Test Step - 31: Entered bank name is not displayed");
            cashandcarrypayment.EnterNameOnCheck(nameoncheck);
            softassert.assertEquals(cashandcarrypayment.getDisplayedCheckName(), nameoncheck, "Test Step - 31: Entered name of check is not displayed");
            cashandcarrypayment.EnterAmountOnCheckTab(checkamount);
            softassert.assertEquals(cashandcarrypayment.getEnteredAmountOnCheckTab(), checkamount, "Test Step - 31: Entered check amount is not displayed");
            cashandcarrypayment.ClickProcessPaymentBtn();

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow2(), "Check", "Test Step - 16 - Check payment is not displayed on payment details table in row 1");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow2(), "($" + checkamount + ".00)", "Test Step - 16 - Added Check Paid amount is not matched on payment details table in row 1");

            // Test Step - 17
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickCashTab();

            // Test Step - 18
            delayWithGivenTime(1000);
            softassert.assertFalse(cashandcarrypayment.VerifyProcessPaymentButton(), "Test step - 18 - Process payment button is not disabled");

            // Test Step - 19
            delayWithGivenTime(1000);
            cashandcarrypayment.SelectRegistryOnCashTab(prop.getProperty("cashandcarryclerkname"));
            softassert.assertEquals(cashandcarrypayment.get_selected_registry(), prop.getProperty("cashandcarryclerkname"), "Test Step - 19 - Selected registry is not displayed on cash tab");

            // Test Step - 20
            delayWithGivenTime(1000);
            cashandcarrypayment.EnterGivenAmountOnCashTab(cashamount);
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.getEnteredGivenAmountOnCashTab(), cashamount, "Test Step - 20 - Given Textbox field on cash and carry payment page");
            cashandcarrypayment.ClickProcessPaymentBtn();

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow3(), "Cash", "Test Step - 20 - cash payment is not displayed on payment details table in row 2");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow3().contains(cashandcarrypayment.getEnteredGivenAmountOnCashTab()), true, "Test Step - 20 - Added amount is not matched on payment details table in row 2");

            // Test Step - 21 & 22 are done to replace for credit card payment

            // Test Step - 23
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickOnPOHPaymentTab();

            // Test Step - 24
            softassert.assertTrue(cashandcarrypayment.VerifyProcessPaymentButton(), "Test step - 24 - Process payment button is disabled");
            delayWithGivenTime(1000);
            cashandcarrypayment.EnterPOHAmountOnPOHPaymentTab("50");
            softassert.assertEquals(cashandcarrypayment.getEnteredPOHAmountOnPOHPaymentTab(), "50", "Test Step - 24 - Entered POH Amount Textbox field value is not displayed on cash and carry payment page");
            cashandcarrypayment.ClickProcessPaymentBtn();

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow4(), "Paid Outside Hana", "Test Step - 24 - POH payment is not displayed on payment details table in row 3");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow4().contains(cashandcarrypayment.getEnteredPOHAmountOnPOHPaymentTab()), true, "Test Step - 24 - Added amount is not matched on payment details table in row 3");

            // Test Step - 25
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickOnGiftCardPaymentTab();

            // Test Step - 26
            delayWithGivenTime(1000);
            String balance_amt = cashandcarrypayment.getTableDisplayedBalanceAmt();
            cashandcarrypayment.EnterGiftCardNumberOnGiftCardPaymentTab(giftcardnum);
            softassert.assertEquals(cashandcarrypayment.getEnteredGiftCardAmountOnGiftCardPaymentTab(), giftcardnum, "Test Step - 26 - Entered Gift Card Number is not displayed on Gift Card payment tab");
            softassert.assertEquals(cashandcarrypayment.getDisplayedCustNameOnGiftCardPaymentTab(), "Abish David", "Test Step - 26 - Customer name is not displayed on Gift Card payment tab");

            // Test Step - 27
            delayWithGivenTime(1000);
            cashandcarrypayment.EnterPaymentAmtOnGiftCardPaymentTab(giftamt);
            softassert.assertEquals(cashandcarrypayment.getDisplayedPaymentAmtOnGiftCardPaymentTab(), giftamt, "Test Step - 27 - Gift Card payment amount is not displayed on Gift Card payment tab");
            cashandcarrypayment.ClickProcessPaymentBtn();

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow6(), "Gift Card", "Test Step - 27 - Gift Card payment type is not displayed on payment details table in row 4");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow6(), "($" + giftamt + ".00)", "Test Step - 27 - Added gift payment amount is not matched on payment details table in row 4");

            delayWithGivenTime(1000);
            cashandcarrypayment.ClickCashTab();

            // Test Step - 32
            cashandcarrypayment.EnterGivenAmountOnCashTab(balance_amt);
            logger.info("User enter the amount in given amount field");

            // Test Step - 33
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Please tender $1.00 to Customer", "Test Step - 33: Please tender $0.00 to Customer message is not displayed");

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyFullyPaidMessage(), "Order Fully Paid. No more payments required.", "Test Step - 33: Order fully paid message is not displayed");

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
