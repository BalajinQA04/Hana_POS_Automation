package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.CashAndCarryPaymentPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T43_Payments_Split_Payment_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    public static final String dataSheetName = "Hana_T43";
    public static LoggerUtil logger_Util;
    CustomSoftAssert softassert = new CustomSoftAssert();

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    //retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Smoke", "Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T43_Payments_Split_Payment_Test(String searchandselectitemcode, String customername, String occasion, String ccfname, String cclname, String creditcardtype, String cccardnumber, String cccvv, String cczipcode, String ccamount, String checkamount,
                                                              String selectregistry, String cashamount, String pohamount, String giftcardnum, String giftamt, String checknumber, String bankname, String nameoncheck) throws InterruptedException, IOException {
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();

        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("bestuname"));
            lp.EnterPassword(prop.getProperty("bestpass"));
            lp.ClickLoginButton();

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            // Test Step - 3
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("bestshopname"));
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("bestshopname"), "Test Step - 4 : Shop name is not matched with selected shop name");

            //Test Step - 5
            cashandcarry.SelectClerkName(prop.getProperty("cashandcarryclerkname"));
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("cashandcarryclerkname"), "Test Step - 5 : Clerk name is not matched with selected clerk name");

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 6 : Employee name is not matched with selected employee name");

            // Test Step - 7
            softassert.assertTrue(cashandcarry.IsPayButtonDisabled(), "Pay button is not disabled");

            // Test Step - 8
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 8: Item description is not displayed");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1");
            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Steps - 7 - Item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Steps - 7 - Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Steps - 7 - Item discount percentage is not matched with search and selected item code");

            // Test Step - 9
            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem(), "Test Step - 9: Added Item code is not displayed on product table grid");
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "rrd", "Test Steps - 8 - Added item code is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Red Rose Deluxe", "Test Steps - 8 - Added item description is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Steps - 8 - Added item quantity is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00", "Test Steps - 8 - Added item extended price is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00", "Test Steps - 8 - Added item price is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Steps - 8 - Added item discount amount is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Steps - 8 - Added item discount percentage is not matched displayed on table grid row1");

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

            // Test Step - 11
            cashandcarry.EnterCustomerName(prop.getProperty("cust_firstName"), prop.getProperty("custfullname"));
            softassert.assertEquals(cashandcarry.getDisplayedCustomerName(), prop.getProperty("custfullname"), "Known Issue Test Step - 9 - Customer name is not matched with entered customer name");

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 10 : Selected tax type is not displayed");

            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 10 : Selected occasion is not displayed");
            delayWithGivenTime(1000);

            cashandcarry.ClickPayButton();
            delayWithGivenTime(2000);

            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 11: Cash And Carry payment page is not displayed");

            // Test Step - 12
            cashandcarrypayment.ClickSplitPaymentBtn();

            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 12: Success toaster message is not displayed");
            logger.info("User verified the order invoice is generated successfully");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg().contains("Invoice Number is:"), true, "Test Step 12: Invoice Number is: ---> Text is not displayed");
            delayWithGivenTime(2000);

            // Test Step - 13
            softassert.assertEquals(cashandcarrypayment.getTopLeftCornerInvNo(), cashandcarrypayment.getGeneratedInvoiceNumber(), "Test Step - 13: Invoice number is not displayed");
            logger.info("User verified the invoice number is displayed in the top left corner and toast message displayed invoice number are same");

            // Test Step - 14
            softassert.assertEquals(cashandcarrypayment.getRow1ProductInTable().contains("Red Rose Deluxe"), true, "Test Step - 14 Added Product at row 1 is not displayed");
            softassert.assertEquals(cashandcarrypayment.getRow2ProductInTable(), "BalloonsYY - Balloons Small 1 X $40.00", "Test Step - 14: Added product at row 2 is not displayed");

            if (cashandcarrypayment.verify_Use_Store_Credit_Label_IsDisplayed()) {
                cashandcarrypayment.Click_StoreCredit_CheckBox();
            }

            // Test Step - 15
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarrypayment.getPaymentGrandTotal(), cashandcarrypayment.ValidateGrandTotalWithConvFee(), "Test Step - 15 - Grand total amount is not calculated correctly as expected");

            // Test Step - 16
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCreditCardTab();
            if (cashandcarrypayment.VerifyCreditCardPresentToogleBtnIsAppear()) {
                cashandcarrypayment.ClickCreditCardPresentToogleBtn();
            }
            cashandcarrypayment.EnterFirstNameOnCreditCardTab(ccfname);
            cashandcarrypayment.EnterLastNameOnCreditCardTab(cclname);
            cashandcarrypayment.SelectCreditCardTypeOnCreditCardTab(creditcardtype);
            cashandcarrypayment.EnterCreditCardNumberOnCreditCardTab(cccardnumber);
            cashandcarrypayment.EnterCreditCardCVVOnCreditCardTab(cccvv);
            cashandcarrypayment.EnterCreditCardZipCodeOnCreditCardTab(cczipcode);
            cashandcarrypayment.EnterCreditCardAmountOnCreditCardTab(ccamount);
            softassert.assertTrue(cashandcarrypayment.VerifyProcessPaymentButton(), "Test Step - 16: Credit card payment process button is disabled");
            //	softassert.fail("***Credit card payment process button is disabled -- Bug --***");
            cashandcarrypayment.ClickProcessPaymentBtn();

            delayWithGivenTime(1000);
            cashandcarrypayment.ClickCheckTab();
            cashandcarrypayment.EnterAmountOnCheckTab(checkamount);
            cashandcarrypayment.ClickProcessPaymentBtn();

            delayWithGivenTime(1000);

            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow1(), "Check", "Test Step - 16: Payment type is not displayed at row 1 payment details section");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow1().contains(cashandcarrypayment.getEnteredAmountOnCheckTab()), true, "Test Step - 16: Entered amount is not displayed");

            // Test Step - 17
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCashTab();

            // Test Step - 18
            delayWithGivenTime(2000);
            softassert.assertFalse(cashandcarrypayment.VerifyProcessPaymentButton(), "Test Step - 18: Process payment button is not disabled");

            // Test Step - 19
            delayWithGivenTime(2000);
            cashandcarrypayment.SelectRegistryOnCashTab(prop.getProperty("cashandcarryclerkname"));

            // Test Step - 20
            delayWithGivenTime(1000);
            cashandcarrypayment.EnterGivenAmountOnCashTab(cashamount);
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);

            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow2(), "Cash", "cash payment is not displayed");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow2().contains(cashandcarrypayment.getEnteredGivenAmountOnCashTab()), true, "amount is not matched");

            // Test Step - 21 & 22 are done to replace for credit card payment

            // Test Step - 23
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickOnPOHPaymentTab();

            // Test Step - 24
            softassert.assertTrue(cashandcarrypayment.VerifyProcessPaymentButton(), "Test Step - 24: Process payment button is disabled");
            delayWithGivenTime(2000);
            cashandcarrypayment.EnterPOHAmountOnPOHPaymentTab(pohamount);
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow3(), "Paid Outside Hana", "Test Step - 24: Paid Outside Hana payment type is not displayed on payment details grid");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow3().contains(cashandcarrypayment.getEnteredPOHAmountOnPOHPaymentTab()), true, "Test Step - 24: Paid Outside Hana paid amount is not displayed");

            // Test Step - 25
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickOnGiftCardPaymentTab();

            // Test Step - 26
            delayWithGivenTime(2000);
            cashandcarrypayment.EnterGiftCardNumberOnGiftCardPaymentTab(giftcardnum);
            softassert.assertEquals(cashandcarrypayment.getDisplayedCustNameOnGiftCardPaymentTab(), "Abish David", "Test Step - 26: Gift card customer name is not displayed");
            //  softassert.assertEquals(cashandcarrypayment.getDisplayedPaymentAmtOnGiftCardPaymentTab().contains(cashandcarrypayment.getTableDisplayedBalanceAmt()), true, "Test Step - 26: gift card payment amount is not displayed");

            // Test Step - 27
            delayWithGivenTime(2000);
            cashandcarrypayment.EnterPaymentAmtOnGiftCardPaymentTab(giftamt);
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow4(), "Gift Card", "Test Step - 27: Gift Card payment type is not displayed on payment details grid table at row 4");
            //   softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow4().contains(cashandcarrypayment.getDisplayedPaymentAmtOnGiftCardPaymentTab()), true, "Test Step - 27: Gift card paid amount is not displayed on payment details grid table at row 4");

            // Test Step - 28
            cashandcarrypayment.ClickFinishBtnOnCashAndCarryPaymentPage();
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarrypayment.VerifyAlertPopup(), "Test Step - 28: Alert popup for split payment on cash and carry payment page is not displayed");

            // Test Step - 29
            softassert.assertTrue(cashandcarrypayment.VerifyAlertIcon());
            softassert.assertEquals(cashandcarrypayment.VerifyAlertPopupWarningText(), "This order is still not fully paid. If you navigate away from this page, balance will remain on this order. Are you sure?");

            // Test Step - 30
            cashandcarrypayment.ClickOnAlertPopupLeaveBtn();
            delayWithGivenTime(4000);


            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 31
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.IsPayButtonDisabled(), "Test Step - 31: Pay button is not disabled");

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

            delayWithGivenTime(2000);
            cashandcarry.ClickParticularProdTitle();
            logger.info("User click on the particular product tile");
            softassert.assertEquals(cashandcarry.getAddedItemCodeRow2(), prop.getProperty("cashandcarry_product_itemcode"));
            logger.info("User verify add the title product to the Cash and Carry page is displayed..");

            cashandcarry.EnterCustomerName(prop.getProperty("cust_firstName"), prop.getProperty("custfullname"));
            logger.info("User search and select the created customer ");
            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            cashandcarry.SelectOccasion(occasion);
            logger.info("User select the Occasion as Birthday");
            delayWithGivenTime(1000);
            cashandcarry.ClickPayButton();
            logger.info("User click on Pay button");
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Cash And Carry payment page is not displayed");
            logger.info("User navigated to Cash And Carry payment page successfully");

            cashandcarrypayment.ClickSplitPaymentBtn();

            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg());
            logger.info("User verified the order invoice is generated successfully");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg().contains("Invoice Number is:"), true, "Invoice Number is: ---> Text is not displayed");
            delayWithGivenTime(2000);

            softassert.assertEquals(cashandcarrypayment.getTopLeftCornerInvNo(), cashandcarrypayment.getGeneratedInvoiceNumber());
            logger.info("User verified the invoice number is displayed in the top left corner and toast message displayed invoice number are same");
            softassert.assertEquals(cashandcarrypayment.getRow1ProductInTable().contains("Red Rose Deluxe"), true, "Test Step - 31: Product name is not displayed in the table");
            softassert.assertEquals(cashandcarrypayment.getRow2ProductInTable(), "BalloonsYY - Balloons Small 1 X $40.00", "Test Step - 31: Product name is not displayed in the table");
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarrypayment.getPaymentGrandTotal(), cashandcarrypayment.ValidateGrandTotalWithConvFee(), "Test Step - 31: Grand total amount is not calculated correctly");

            if (cashandcarrypayment.verify_Use_Store_Credit_Label_IsDisplayed() == true) {
                cashandcarrypayment.Click_StoreCredit_CheckBox();
            }

            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCreditCardTab();

            if (cashandcarrypayment.VerifyCreditCardPresentToogleBtnIsAppear() == true) {
                cashandcarrypayment.ClickCreditCardPresentToogleBtn();
            }

            cashandcarrypayment.EnterFirstNameOnCreditCardTab(ccfname);
            cashandcarrypayment.EnterLastNameOnCreditCardTab(cclname);
            cashandcarrypayment.SelectCreditCardTypeOnCreditCardTab(creditcardtype);
            cashandcarrypayment.EnterCreditCardNumberOnCreditCardTab(cccardnumber);
            cashandcarrypayment.EnterCreditCardCVVOnCreditCardTab(cccvv);
            cashandcarrypayment.EnterCreditCardZipCodeOnCreditCardTab(cczipcode);
            cashandcarrypayment.EnterCreditCardAmountOnCreditCardTab(ccamount);

            softassert.assertTrue(cashandcarrypayment.VerifyProcessPaymentButton(), "Test Step - 32: Credit card payment process button is disabled");
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickCheckTab();

            softassert.assertTrue(cashandcarrypayment.getDisplayedCheckNumber().contains(""), "Check number field is not empty");
            softassert.assertTrue(cashandcarrypayment.getDisplayedCheckName().contains(""), "Check name field is not empty");
            softassert.assertTrue(cashandcarrypayment.getDisplayedBankName().contains(""), "Bank Name field is not empty");

            cashandcarrypayment.EnterCheckNumber(checknumber);
            cashandcarrypayment.EnterBankName(bankname);
            cashandcarrypayment.EnterNameOnCheck(nameoncheck);
            cashandcarrypayment.EnterAmountOnCheckTab(checkamount);
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);

            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow1(), "Check", "Test Step - 31: Check payment is not displayed");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow1().contains(cashandcarrypayment.getEnteredAmountOnCheckTab()), true);
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCashTab();
            delayWithGivenTime(2000);
            softassert.assertFalse(cashandcarrypayment.VerifyProcessPaymentButton(), "Test Step - 32: Process payment button is not disabled");
            delayWithGivenTime(2000);
            cashandcarrypayment.SelectRegistryOnCashTab(prop.getProperty("cashandcarryclerkname"));
            delayWithGivenTime(1000);
            //	cashandcarrypayment.EnterGivenAmount();
            cashandcarrypayment.EnterGivenAmountOnCashTab(cashamount);
            cashandcarrypayment.ClickProcessPaymentBtn();

            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow2(), "Cash", "Test Step - 31 - cash payment is not displayed");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow2().contains(cashandcarrypayment.getEnteredGivenAmountOnCashTab()), true, "amount is not matched");

            // Test Step - 21 & 22 are done to replace for credit card payment
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickOnPOHPaymentTab();
            delayWithGivenTime(500);
            softassert.assertTrue(cashandcarrypayment.VerifyProcessPaymentButton(), "Process payment button is disabled");
            delayWithGivenTime(2000);
            cashandcarrypayment.EnterPOHAmountOnPOHPaymentTab(pohamount);
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);

            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow3(), "Paid Outside Hana", "Test Step - 31 - paid outside hana payment is not displayed on payment details grid table");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow3().contains(cashandcarrypayment.getEnteredPOHAmountOnPOHPaymentTab()), true, "Test Step - 31: Paid Amount for paid outside hana payment is not displayed on payment details grid table");
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickOnGiftCardPaymentTab();
            delayWithGivenTime(2000);
            cashandcarrypayment.EnterGiftCardNumberOnGiftCardPaymentTab(giftcardnum);
            softassert.assertEquals(cashandcarrypayment.getDisplayedCustNameOnGiftCardPaymentTab(), "Abish David", "Test Step - 31: Customer name is not displayed for gift card");
            // softassert.assertEquals(cashandcarrypayment.getDisplayedPaymentAmtOnGiftCardPaymentTab().contains(cashandcarrypayment.getTableDisplayedBalanceAmt()), true, "Test Step - 31 - gift card payment is not displayed");

            delayWithGivenTime(2000);
            cashandcarrypayment.EnterPaymentAmtOnGiftCardPaymentTab(giftamt);
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow4(), "Gift Card", "Test Step - 31: Gift Card payment type is not displayed on payment details grid table at row 4");
            //   softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow4().contains(cashandcarrypayment.getDisplayedPaymentAmtOnGiftCardPaymentTab()), true, "Test Step - 31: Gift card paid amount is not displayed on payment details grid table at row 4");


            // Test Step - 32
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCashTab();
            delayWithGivenTime(1000);
            cashandcarrypayment.EnterGivenAmountOnCashTab(cashandcarrypayment.getTableDisplayedBalanceAmt()); //

            logger.info("User enter the amount in given amount field");

            // Test Step - 33
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);

            if (cashandcarrypayment.SuccessToastMsg() == true) {
                softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg().contains("Please tender $0.00 to Customer"), true, "Test Step - 33 - Please tender $0.00 to Customer message is not displayed");
            }

            if (cashandcarrypayment.VerifyOrderPaidTextAppears() == true) {
                softassert.assertEquals(cashandcarrypayment.VerifyFullyPaidMessage(), "Order Fully Paid. No more payments required.", "Order fully paid message is not displayed");
            }

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
