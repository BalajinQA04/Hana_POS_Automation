package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

import static com.hanapos.pageObjects.CashAndCarryPage.generateGiftCardNumber;

public class Hana_T28_Gift_Card_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    private DashboardOrderPage dashboardorder;
    public static ExecutorService executorService;
    private static final int THREAD_POOL_SIZE = 2;
    public static final String dataSheetName = "Hana_T28";
    String gift_cardno;
    String gift_amount;
    String invoice;
    String custid;
    // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
    CustomSoftAssert softassert = new CustomSoftAssert();
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    /*
     * HANA-T28 - 1 to 29 - Verify gift card Functionality
     */
//retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Smoke", "Sanity", "Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T28_GiftCard_Functionality_Test(String instantdenomination1, String giftamt, String giftvalidamt,
                                                              String processingfee, String giftcardno, String morethan25digitgiftcardno, String customershortname, String Instantdenomination1) throws InterruptedException, IOException {
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
            logger.info("User selected shop name as " + prop.getProperty("bestshopname") + "from dropdown in Hana dashboard page");
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3 - Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("bestshopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("bestshopname"), "Test Step - 4 : Shop name is not matched with selected shop name");

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
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.get_Card_Number_On_Preview_Popup(), gift_cardno, "Test Step - 14 - Gift card number values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Customer_Name_On_Preview_Popup(), prop.getProperty("custfullname"), "Test Step - 15 - Customer name values are not matched in preview popup");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.get_Total_Amount_On_Preview_Popup(), cashandcarry.expectedTotalGiftValue(), "Test Step - 16 - Total amount values are not matched in preview popup");

            delayWithGivenTime(2000);
            cashandcarry.click_Submit_Button_On_Preview_Popup();

            delayWithGivenTime(1000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertEquals(cashandcarrypayment.getSuccessToastMsg(), "Gift card has been added", "Test Step - 18 - Success toast message is not displayed after clicking submit button");

            delayWithGivenTime(2000);
            custid = cashandcarry.get_Displayed_CustomerId();
            softassert.assertEquals(cashandcarry.get_Displayed_CustomerId(), custid, "Test Step - 19 - Customer ID values are not matched");
            softassert.assertEquals(cashandcarry.getDisplayedCustomerNameOnCCPage(), prop.getProperty("custfullname"), "Test Step - 20 - Customer name values are not matched");

            cashandcarry.SelectClerkName(prop.getProperty("cashandcarryclerkname"));
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
