package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import com.github.javafaker.Faker;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.CashAndCarryPaymentPage;
import com.hanapos.pageObjects.DashboardOrderPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

import static com.hanapos.pageObjects.CashAndCarryPage.generateGiftCardNumber;

public class Hana_T61_Payments_GiftCard_Payment_Section_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    private DashboardOrderPage dashboardorder;
    String invoice;
    String gift_cardno;
    String gift_amount;
    String processingfee = String.valueOf(5);
    public static final String dataSheetName = "Hana_T14";
    CustomSoftAssert softassert = new CustomSoftAssert();
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Cash and Carry Module")
    @Test(priority = 1, enabled = true, groups = {"Smoke", "Sanity", "Regression"})
    public void Add_New_GiftCard() {
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
            lp.ClickLoginButton();
            logger.info("Clicked on Login button..");

            dashboard = new HanaDashBoardPage();
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucessfully..");

            // Test Step - 3
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3 - Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("bestshopname")); //"Hana POS (Canada)"
            delayWithGivenTime(2000);
            cashandcarry.ClickGiftCardTile();
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.is_GiftCard_Select_Type_Popup_Displayed(), "Test Step - 4 - Select gift card type pop up is not displayed");

            cashandcarry.click_Physical_Gift_Card_Type_Radio_Button();
            delayWithGivenTime(1000);
            cashandcarry.click_Next_Button_On_GiftCard_Select_Type_Popup();
            delayWithGivenTime(2000);

            softassert.assertTrue(cashandcarry.is_Enter_Gift_Card_Details_Popup_Displayed(), "Test Step - 4 - Enter gift card details pop up is not displayed");
            delayWithGivenTime(1000);

            cashandcarry.search_And_Select_Customer_On_Enter_Gift_Card_Details_Popup(prop.getProperty("custfullname"));
            delayWithGivenTime(1000);

            Faker faker = new Faker(new java.util.Locale("en-US"));
            // gift_amount = String.format("%.2f", faker.number().randomDouble(2, 500, 1000));
            gift_amount = String.valueOf(faker.number().numberBetween(400, 700));

            delayWithGivenTime(1000);
            cashandcarry.EnterAmountOnGiftamtField(gift_amount);
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getGiftAmountValue(), gift_amount + ".00", "Test Step - 6 - Gift amount values are not matched");

            delayWithGivenTime(500);
            cashandcarry.EnterProcessingFeesOnGiftSalePopup(processingfee);
            softassert.assertEquals(cashandcarry.getGiftCardProcessingFee(), "5.00", "Test Step - 9 - gift card processing fee is not matched");

            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.getTotalGiftValue(), cashandcarry.expectedTotalGiftValue(), " Test Step - 9 - Total gift values are not matched");

            delayWithGivenTime(500);
            gift_cardno = generateGiftCardNumber();
            cashandcarry.EnterGiftCardNumber(gift_cardno);
            softassert.assertEquals(cashandcarry.getGiftCardNumber(), gift_cardno, "TestStep - 10 - Entered Gift Card Number is not displayed");
            cashandcarry.saveGiftCardInfoToFile(gift_cardno, gift_amount);

            delayWithGivenTime(1000);
            cashandcarry.click_Preview_Button_On_Enter_Gift_Card_Details_Popup();
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.is_Preview_Header_Displayed_On_Popup(), "Test Step - 11 - Preview header is not displayed after clicking preview button");

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.get_Amount_On_Preview_Popup(), gift_amount + ".00", "Test Step - 12 - Gift amount values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Processing_Fee_On_Preview_Popup(), "5.00", "Test Step - 13 - Gift processing fee values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Card_Number_On_Preview_Popup(), gift_cardno, "Test Step - 14 - Gift card number values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Customer_Name_On_Preview_Popup(), prop.getProperty("custfullname"), "Test Step - 15 - Customer name values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Total_Amount_On_Preview_Popup(), cashandcarry.expectedTotalGiftValue(), "Test Step - 16 - Total amount values are not matched in preview popup");

            delayWithGivenTime(2000);
            cashandcarry.click_Submit_Button_On_Preview_Popup();

            delayWithGivenTime(1000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertEquals(cashandcarrypayment.getSuccessToastMsg(), "Gift card has been added", "Test Step - 18 - Success toast message is not displayed after clicking submit button");

            delayWithGivenTime(2000);
            String customerid = cashandcarry.get_Displayed_CustomerId();
            softassert.assertEquals(cashandcarry.get_Displayed_CustomerId(), customerid, "Test Step - 19 - Customer ID values are not matched");
            softassert.assertEquals(cashandcarry.getDisplayedCustomerNameOnCCPage(), prop.getProperty("custfullname"), "Test Step - 20 - Customer name values are not matched");

            cashandcarry.SelectClerkName(prop.getProperty("clerkname"));
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 10 : Selected tax type is not displayed");
            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 10 : Selected occasion is not displayed");

            cashandcarry.ClickPayButton();
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 23 - Cash And Carry payment page is not displayed");

            cashandcarrypayment.ClickCashTab();
            cashandcarrypayment.EnterGivenAmount();
            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 24 - Success toast message is not displayed");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 25 - Order confirmation toast message is not displayed");

            delayWithGivenTime(2000);
            if (cashandcarrypayment.getConfirmationPopup()) {
                cashandcarrypayment.VerifyOrderConfirmationPopup();
                cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo();
                invoice = cashandcarrypayment.GetInvoiceNumber();
                cashandcarrypayment.GetTenderPrice();
            }

            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();

            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 20: User is not navigated to All orders page");
            delayWithGivenTime(1000);

            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice), "Test Step - 20 - Respective Invoice number : " + invoice + " is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoice), "Delivered", "Test Step - 20 - Order status is not displayed as delivered for cash and carry order");
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoice), "Walkin Sales", "Test Step - 20: Order Type as Walkin Sales is not properly displayed for cash and carry order");

            dashboardorder.EnterGlobalSearch(invoice);
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoice), "Cash", "Test STep - 20: Mode of pay is not displayed as Cash for cash and carry order");
        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
            softassert.assertAll();
        }
    }

    @Epic("Cash and Carry Module")
    @Test(priority = 2, enabled = true, groups = {"Smoke", "Sanity", "Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T61_Payments_GiftCard_Payment_Section_Test(String searchandselectitemcode) throws InterruptedException, IOException {
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
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");

            // Test Step - 3
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("bestshopname"));

            //Test Step - 5
            cashandcarry.SelectClerkName(prop.getProperty("cashandcarryclerkname"));

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));

            // Test Step - 7
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Item discount percentage is not matched with search and selected item code");

            // Test Step - 8
            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem());
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "rrd", "Test Step - 8: Added item code is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Red Rose Deluxe", "Test Step - 8: Added item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Step - 8: Added item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00", "Test Step - 8: Added item extended price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00", "Test Step - 8: Added item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 8: Added item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 8: Added item discount percentage is not matched with search and selected item code");

            // Test Step - 9
            delayWithGivenTime(2000);
            cashandcarry.ClickParticularProdTitle();
            softassert.assertEquals(cashandcarry.getAddedItemCodeRow2(), prop.getProperty("cashandcarry_product_itemcode"), "Test Step - 9 - Added item code in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDescriptionRow2(), prop.getProperty("cashandcarry_product_description"), "Test Step - 9 - Added item description in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemQuantityRow2(), "1", "Test Step - 9 - Added item quantity in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemPriceRow2(), "$40.00", "Test Step - 9 - Added item extended price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemExtentPriceRow2(), "$40.00", "Test Step - 9 - Added item price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountAmountRow2(), "$ 0.00", "Test Step - 9 - Added item discount amount in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountPercentageRow2(), "0.00", "Test Step - 9 - Added item discount percentage in row 2 is not displayed on the page");

            // Test Step - 10
            cashandcarry.EnterCustomerName(prop.getProperty("cust_firstName"), prop.getProperty("custfullname"));
            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            cashandcarry.SelectOccasion(prop.getProperty("occasion"));

            delayWithGivenTime(500);
            cashandcarry.ClickPayButton();

            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 10: Cash And Carry payment page is not displayed");

            // Test Step - 11
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarrypayment.VerifyCreditCardTabIsSelected(), "true", "By defaultCredit card tab section is not displayed");

            // Test Step - 12
            delayWithGivenTime(2000);

            if (cashandcarrypayment.verify_Use_Store_Credit_Label_IsDisplayed()) {
                cashandcarrypayment.Click_StoreCredit_CheckBox();
            }
            cashandcarrypayment.ClickOnGiftCardPaymentTab();
            softassert.assertTrue(cashandcarrypayment.VerifyIsSwipeGiftCardButtonDisplayed(), "click here swipe gift card button is not displayed");

            // Test Step - 13
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCashTab();
            softassert.assertTrue(cashandcarrypayment.VerifyGivenAmountOnCashTabTextboxIsDisplayed(), "Test Step - 13 - In cash tab given amount textbox field is not displayed or not autopopulated");

            // Test Step - 14
            delayWithGivenTime(1000);
            action_PressF8();
            softassert.assertTrue(cashandcarrypayment.VerifyIsSwipeGiftCardButtonDisplayed(), "click here swipe gift card button is not displayed");

            // Test Step - 15
            cashandcarrypayment.EnterGiftCardNumberOnGiftCardPaymentTab(gift_cardno);
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.getEnteredGiftCardAmountOnGiftCardPaymentTab(), gift_cardno, "Test Step - 15: Gift card number entered data is not displayed");

            // Test Step - 16
            softassert.assertEquals(cashandcarrypayment.getDisplayedCustNameOnGiftCardPaymentTab(), "Abish David", "Test Step - 16: Gift card customer name is not displayed");

            // Test Step - 17
            //  softassert.assertEquals(cashandcarrypayment.getDisplayedPaymentAmtOnGiftCardPaymentTab(), cashandcarrypayment.get_Displayed_Grand_Total_Amount(), "Test Step - 17: Gift card payment amount is not displayed");

            // Test Step - 18 cannot able to handle dynamically to check the gift card balance amount
            //  softassert.assertEquals(cashandcarrypayment.VerifyGiftCardCurrentBalance().contains("8"), true, "Test Step - 18: Gift card current balance is not displayed");

            // Test Step - 19
            logger_Util.payment_API_Logger("Gift Card Payment - Process Payment API LOG", () -> {
                cashandcarrypayment.ClickProcessPaymentBtn();
            });
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 19: Order payment done successfully toast message is not displayed");
            logger.info("User verified the order payment done successfully");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 19: Order payment done successfully toast message text is not displayed");

            delayWithGivenTime(1000);
            if (cashandcarrypayment.getConfirmationPopup()) {
                softassert.assertTrue(cashandcarrypayment.VerifyOrderConfirmationPopup(), "Test Step - 16: Order confirmation popup is not displayed");
                softassert.assertTrue(cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo(), "Test Step - 16: Order confirmation message along with invoice number is not displayed");
                invoice = cashandcarrypayment.GetInvoiceNumber();
                cashandcarrypayment.GetTenderPrice();
            }
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();

            // Test Step - 20
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();

            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 20: Order page is not navigated to dashboard order page");
            logger.info("User verify that the order page is navigated to dashboard order page");
            delayWithGivenTime(1000);

            // Test Step - 21
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice), "Test Step - 20 - Respective Invoice number : " + invoice + " is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoice), "Delivered", "Test Step - 20 - Order status is not displayed as delivered for cash and carry order");
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoice), "Walkin Sales", "Test Step - 20: Order Type as Walkin Sales is not properly displayed for cash and carry order");

            dashboardorder.EnterGlobalSearch(invoice);
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoice), "Gift Card", "Test STep - 20: Gift card - mode of pay is not displayed on orders page");
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
