package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.CashAndCarryPaymentPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T20_OrderConfirmationEmail_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    String invoiceNumber;
    public static final String dataSheetName = "Hana_T20";
    CustomSoftAssert softassert = new CustomSoftAssert();
    String custname;
    String custid;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    public static LoggerUtil logger_Util;

    @Severity(SeverityLevel.NORMAL)
    @Owner("Balaji N")
    @Description("Hana_T20 :- Order Confirmation Popup Email functionality on Cash and Carry Payment Page")
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T20_OrderConfirmationEmail_Test(String searchandselectitemcode, String customershortname) throws InterruptedException, IOException {
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
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucessfully..");

            // Test Step - 3
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

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
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));
            logger.info("User search and select the item code as " + searchandselectitemcode);
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 7 : Item description is not matched with search and selected with item code");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 7 : Item quantity is not matched with search and selected item code");

            if (cashandcarry.ItemPriceValueIsExist() == "299") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Step - 7 : Item price is not matched with search and selected item code");
            } else if (cashandcarry.ItemPriceValueIsExist() == "309") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "309", "Test Step - 7 : Item price is not matched with search and selected item code");
            }


            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 7 : Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 7 : Item discount percentage is not matched with search and selected item code");
            logger.info("Validate search and selected item is displayed correctly into the table grid");

            // Test Step - 8
            cashandcarry.ClickAddItem();
            logger.info("User click on the add item at action");
            softassert.assertTrue(cashandcarry.VerifyAddedItem(), "Test Step - 8 : Added item is not displayed");
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "rrd", "Test Step - 8 : Added item code is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Red Rose Deluxe", "Test Step - 8 : Added item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Step - 8 : Added item quantity is not matched with search and selected item code");

            if (cashandcarry.GetAddedItemExtPrice() == "$299.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00", "Test Step - 8 : Added item ext price is not matched with search and selected item code");
            } else if (cashandcarry.GetAddedItemExtPrice() == "$309.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$309.00", "Test Step - 8 : Added item ext price is not matched with search and selected item code");
            }

            if (cashandcarry.GetAddedItemPrice() == "$299.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00", "Test Step - 8 : Added item price is not matched with search and selected item code");
            } else if (cashandcarry.GetAddedItemPrice() == "$309.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$309.00", "Test Step - 8 : Added item price is not matched with search and selected item code");
            }

            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 8 : Added item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 8 : Added item discount percentage is not matched with search and selected item code");
            logger.info("User verify the search and selected data are displayed in table grid ");

            // Test Step - 09
            delayWithGivenTime(1000);
            cashandcarry.ClickParticularProdTitle();
            logger.info("User click on the particular product title on left side");
            softassert.assertEquals(cashandcarry.getAddedItemCodeRow2(), prop.getProperty("cashandcarry_product_itemcode"), "Test Step - 09 : Added item code is not matched with search and selected item code");
            logger.info("User verify that added tile product is displayed on table grid");
            softassert.assertEquals(cashandcarry.getAddedItemDescriptionRow2(), prop.getProperty("cashandcarry_product_description"), "Test Step - 9 - Added item description in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemQuantityRow2(), "1", "Test Step - 9 - Added item quantity in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemPriceRow2(), "$40.00", "Test Step - 9 - Added item extended price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemExtentPriceRow2(), "$40.00", "Test Step - 9 - Added item price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountAmountRow2(), "$ 0.00", "Test Step - 9 - Added item discount amount in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountPercentageRow2(), "0.00", "Test Step - 9 - Added item discount percentage in row 2 is not displayed on the page");

            // Test Step - 10
            cashandcarry.EnterCustomerName(customershortname, prop.getProperty("custfullname"));
            custid = cashandcarry.get_Displayed_CustomerId();
            softassert.assertEquals(cashandcarry.getDisplayedCustomerNameOnCCPage(), "Abish David", "Test Step - 9 - Customer name is not matched with entered customer name");
            softassert.assertEquals(cashandcarry.get_Displayed_CustomerId(), custid, "Test Step - 9 - Customer Id is not matched with entered customer name");

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 10 : Selected tax type is not displayed");
            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 10 : Selected occasion is not displayed");

            cashandcarry.ClickPayButton();

            logger.info("User click on Pay button");
            cashandcarrypayment = new CashAndCarryPaymentPage();
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Cash And Carry payment page is not displayed");
            logger.info("User is on Cash And Carry payment page");

            // Test Step - 11
            softassert.assertEquals(cashandcarrypayment.getDisplayedCustomerNameOnCCPage(), "Abish David", "Test Step - 11 - : Customer name is not displayed on Cash And Carry payment page when search & select the customer on this field");
            softassert.assertEquals(cashandcarrypayment.getCustomerIdDisplayed(), custid, "Test Step - 11 - : Customer ID is not displayed on Cash And Carry payment page when search & select the customer on this field");

            cashandcarrypayment.ClickCashTab();
            cashandcarrypayment.EnterGivenAmount();

            // Test Step - 12
            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 12 : Success toast message is not displayed");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 12 : Order confirmation toast message text is not displayed");

            delayWithGivenTime(2000);
            if (cashandcarrypayment.getConfirmationPopup()) {
                softassert.assertTrue(cashandcarrypayment.VerifyOrderConfirmationPopup(), "Order confirmation popup is not displayed");
                softassert.assertTrue(cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo(), "Order confirmation message and invoice number is not displayed");
                invoiceNumber = cashandcarrypayment.GetInvoiceNumber();
                cashandcarrypayment.GetTenderPrice();
            }

            delayWithGivenTime(1000);

            // Test Step - 13
            softassert.assertEquals(cashandcarrypayment.GetConfirmationPopupCustEmail(), "hanaposqateam@gmail.com", "******Test Step - 13: Email id is not autopopulated from customer details******");

            // Not configure to trigger sms in application...
            //  softassert.assertEquals(cashandcarrypayment.GetConfirmationPopupCustSMS(), "956-655-0756", "******Test Step - 13: SMS Phone number is not autopopulated from customer details******");
            //  logger.info("User verify the customer popup customer sms " + cashandcarrypayment.GetConfirmationPopupCustSMS());

            //Test Step - 14
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickSendReciptBtnOnOrderConfirmationPopup();
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Receipt sent successfully.", "Test Step - 14 : Recipt sent toast message text is not displayed");

            // Test Step - 15
            delayWithGivenTime(100000);
            softassert.assertTrue(isOrderConfirmationEmailReceived(invoiceNumber), "Order confirmation email is not received");

            //	EmailReader.getInstance();
            //	System.out.println("Received Email Invoice number is :" + EmailReader.getInvoiceNumber());

            logger.info("User click the send recipt button on order confirmation popup");
            logger.info("***** Need to verify email notification is received on registered mail id for confirmation order invoice is generated by tested manually *****");

            //	softassert.assertEquals(EmailReader.getInvoiceNumber(),cashandcarrypayment.GetInvoiceNumber());
            //	logger.info("User validate the email notification and validate the invoice number as "+EmailReader.getInvoiceNumber());


            // Test Step - 16,17 & 18 are need to be done manually
            logger.info("***** Need to verify email body messages is received on registered mail id which is done by tested manually *****");

            logger.info("**** Finished Hana_T20_CashAndCarryOrderConfirmationEmail_FT  ****");
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
