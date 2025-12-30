package com.hanapos.testcases.All_Orders_Page_Testcases;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;

public class Hana_T1504_AllOrdersView_Split_Payment_View_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    String invoiceNumber;
    private CashAndCarryPaymentPage cashandcarrypayment;
    String paid_date;
    String total_Amount;
    String paidcashamount;
    String paidcheckamount;
    String paid_pohamount;
    String paidgiftamount;
    public static final String dataSheetName = "Hana_T1504";
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    Faker faker = new Faker(new Locale("en-US"));
    String recifname1;
    String recilname2;
    String reci_full_address1;

    @Severity(SeverityLevel.CRITICAL)
    @Owner("Balaji N")
    @Epic("All Orders Page Module")
    @Test(enabled = true, groups = {"Regression", "Smoke", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1504_AllOrdersView_Split_Payment_View_Functionality_Test(
            String username,
            String password,
            String shopname,
            String salesperson,
            String customername,
            String recipientfname,
            String recipientlname,
            String recipientaddress1,
            String recipientfulladdress1,
            String recipientzipcode,
            String recipientcity,
            String recipientcountry,
            String recipientphone,
            String recipientlocation,
            String deliveryontime,
            String occasion,
            String cardmessage,
            String itemcode,
            String itemdescription,
            String paymenttype,
            String cashregistry,
            String ordertype,
            String deliverytype,
            String mop,
            String ccamount,
            String cashamount,
            String checknumber,
            String bankname,
            String nameoncheck,
            String checkamount,
            String pohamount,
            String giftcardnumber,
            String giftcardamount,
            String selectview
    ) {

        CustomSoftAssert softassert = new CustomSoftAssert();
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            lp.EnterUserName(username);
            lp.EnterPassword(password);
            softassert.assertEquals(lp.get_entered_username(), username, "Test Step - 2: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), password, "Test Step - 2: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2: Page does not navigated to hana dashboard page");

            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 2: Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 2: Cash and carry option is not displayed");

            dashboard.ClickOrderEntry();
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(shopname);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), shopname, "Test Step - 2 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickDeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnDeliveryTypeOnPhoneOrderPage(), "#676a6c", "Test Step - 2: Delivery type as Delivery is not highlighted in blue color");

            // Test Step - 3
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            phoneorder.SearchAndSelectCustomerOnCust_Section(customername);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), prop.getProperty("cust_firstName"), "Test Step - 3 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), prop.getProperty("cust_lastName"), "Test Step - 3 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), prop.getProperty("cust_companyName"), "Test Step - 3 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), prop.getProperty("cust_email"), "Test Step - 3 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), prop.getProperty("cust_address1"), "Test Step - 3 address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 3 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), prop.getProperty("cust_zipcode"), "Test Step - 3 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), prop.getProperty("cust_city"), "Test Step - 3 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_phoneNumber"), "Test Step - 3 phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_Alt_phoneNumber"), "Test Step - 3 - Alt phone number is not displayed on phone order page");

            // Test Step - 4
            recifname1 = faker.name().firstName();
            recilname2 = faker.name().lastName();
            reci_full_address1 = "Dutzow Ball Park";
            phoneorder.EnterReciFirstName(recifname1);
            phoneorder.EnterReciLastName(recilname2);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname1, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname2, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(reci_full_address1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "Dutzow Ballpark Dr", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63357", "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Charrette Township", "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.SelectReciCountry(recipientcountry);
            phoneorder.EnterReciPhone(recipientphone);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recipientlocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(18, 30);
            phoneorder.Select_DeliveryOnTime_Dropdown(deliveryontime);
            delayWithGivenTime(1000);

            //Test Step - 5
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), cardmessage);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), occasion, "Test Step - 5 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(cardmessage), true, "Test Step - 5 - Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 6
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(itemcode, itemdescription);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), itemcode, "Test Step - 6 - Item code as " + itemcode + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), itemdescription, "Test Step - 6 - Item description " + prop.getProperty("product_description1") + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 6 - Item quantity is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 6 - Item price is not displayed on phone order page product details section");
            delayWithGivenTime(2000);

            // Test Step - 7
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymenttype);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), paymenttype, "Test Step - 7 - Selected payment type " + paymenttype + " is not displayed on phone order page payment section");

            phoneorder.Select_SplitPayment_CashRegistry_OnPaymentSection(cashregistry);
            softassert.assertEquals(phoneorder.get_Selected_Cash_Registry_On_Payment_Section(), cashregistry, "Test Step - 7 - Selected Cash registry " + cashregistry + " is not displayed on phone order page payment section");

            total_Amount = phoneorder.getGrandTotalAmount();
            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 7 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 8
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);

            cashandcarrypayment = new CashAndCarryPaymentPage();
            delayWithGivenTime(2000);

            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 8: Cash And Carry payment page is not displayed");
            delayWithGivenTime(2000);

            invoiceNumber = cashandcarrypayment.getTopLeftCornerInvNo();
            delayWithGivenTime(2000);

            softassert.assertEquals(cashandcarrypayment.getRow1ProductInTable().contains(itemdescription), true, "Test Step - 8: Added item is not displayed on payment details table grid");
            delayWithGivenTime(2000);

            cashandcarrypayment.EnterFirstNameOnCreditCardTab(prop.getProperty("cust_firstName"));
            cashandcarrypayment.EnterLastNameOnCreditCardTab(prop.getProperty("cust_lastName"));
            //   cashandcarrypayment.SelectCreditCardTypeOnCreditCardTab(creditcardtype);
            cashandcarrypayment.EnterCreditCardNumberOnCreditCardTab(prop.getProperty("creditcardnum"));
            cashandcarrypayment.EnterCreditCardExpireDateOnCreditCardTab(prop.getProperty("ccexpiredate"));
            cashandcarrypayment.EnterCreditCardCVVOnCreditCardTab(prop.getProperty("cccvv"));
            cashandcarrypayment.EnterCreditCardZipCodeOnCreditCardTab(prop.getProperty("cust_zipcode"));
            cashandcarrypayment.EnterCreditCardAmountOnCreditCardTab(ccamount);

            softassert.assertTrue(cashandcarrypayment.VerifyProcessPaymentButton(), "Test Step - 16: Credit card payment process button is disabled");
            cashandcarrypayment.ClickProcessPaymentBtn();

            // Cash payment tab
            // Test Step - 8
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCashTab();
            cashandcarrypayment.SelectRegistryOnCashTab(cashregistry);
            softassert.assertEquals(cashandcarrypayment.get_selected_registry(), cashregistry, "Test Step - 8: Selected cash registry " + cashregistry + " is not displayed");

            delayWithGivenTime(1000);
            cashandcarrypayment.EnterGivenAmountOnCashTab(cashamount);
            softassert.assertEquals(cashandcarrypayment.getEnteredGivenAmountOnCashTab(), cashamount, "Test Step - 8: Displayed entered amount is not matched with entered amount");
            paidcashamount = cashandcarrypayment.getEnteredGivenAmountOnCashTab();
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);

            paid_date = getEasternDate("MM/dd/yyyy");
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow1(), "Cash", "Test Step - 8: Payment type as cash is not displayed");
            //cashandcarrypayment.getEnteredGivenAmountOnCashTab()
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow1().contains(cashamount), true, "Test Step - 8: Displayed paid amount is not matched");
            softassert.assertEquals(cashandcarrypayment.get_paymentDate_paymentdetailsTable_Row1(), paid_date, "Test Step - 8: Displayed payment date is not matched");

            // Test Step - 9
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickCheckTab();
            cashandcarrypayment.EnterCheckNumber(checknumber);
            cashandcarrypayment.EnterBankName(bankname);
            cashandcarrypayment.EnterNameOnCheck(nameoncheck);
            cashandcarrypayment.EnterAmountOnCheckTab(checkamount);
            paidcheckamount = cashandcarrypayment.getEnteredAmountOnCheckTab();
            cashandcarrypayment.ClickProcessPaymentBtn();

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow2(), "Check", "Test Step - 9: Check payment is not displayed on cash and carry payment details table grid ");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow2().contains(paidcheckamount), true, "Test Step - 9: Paid check amount is not matched");
            softassert.assertEquals(cashandcarrypayment.get_paymentDate_paymentdetailsTable_Row2(), paid_date, "Test Step - 9: Displayed payment date is not matched");

            // Test Step - 10
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickOnPOHPaymentTab();
            cashandcarrypayment.EnterPOHAmountOnPOHPaymentTab(pohamount);
            softassert.assertEquals(cashandcarrypayment.getEnteredPOHAmountOnPOHPaymentTab(), pohamount, "Test Step - 10: Entered POH amount is not matched with entered amount");
            delayWithGivenTime(1000);
            paid_pohamount = cashandcarrypayment.getEnteredPOHAmountOnPOHPaymentTab();
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow3(), "Paid Outside Hana", "Test Step - 10 : Paid Outside Hana Payment type is not displayed on payment details grid");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow3().contains(paid_pohamount), true, "Test Step - 10: Paid Outside Hana paid amount is not displayed on payment details grid");
            softassert.assertEquals(cashandcarrypayment.get_paymentDate_paymentdetailsTable_Row3(), paid_date, "Test Step - 10: Displayed payment date is not matched for paid outside hana payment");

            // Test Step - 11
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickOnGiftCardPaymentTab();
            delayWithGivenTime(2000);
            cashandcarrypayment.EnterGiftCardNumberOnGiftCardPaymentTab(giftcardnumber);
            softassert.assertEquals(cashandcarrypayment.getDisplayedCustNameOnGiftCardPaymentTab(), prop.getProperty("custfullname"), "Test Step - 10: Gift card name is not displayed");
            delayWithGivenTime(2000);
            cashandcarrypayment.EnterPaymentAmtOnGiftCardPaymentTab(giftcardamount);
            delayWithGivenTime(1000);
            paidgiftamount = cashandcarrypayment.getDisplayedPaymentAmtOnGiftCardPaymentTab();
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow4().contains(paidgiftamount), true, "Test Step - 10: Gift Card paid amount is not displayed on payment details grid");
            softassert.assertEquals(cashandcarrypayment.get_paymentDate_paymentdetailsTable_Row4(), paid_date, "Test Step - 10: Displayed payment date is not matched for gift card payment");

            String balance_split_amt = cashandcarrypayment.getTableDisplayedBalanceAmt();
            cashandcarrypayment.ClickCashTab();
            delayWithGivenTime(2000);
            cashandcarrypayment.SelectRegistryOnCashTab(prop.getProperty("payment_cash_registry"));
            delayWithGivenTime(1000);
            cashandcarrypayment.EnterGivenAmountOnCashTab(balance_split_amt);

            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);

            if (cashandcarrypayment.SuccessToastMsg()) {
                softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), cashandcarrypayment.getOrderConfirmationToastMsg(), "Test Step - 33 - Please tender $0.00 to Customer message is not displayed");
            }

            if (cashandcarrypayment.VerifyOrderPaidTextAppears()) {
                softassert.assertEquals(cashandcarrypayment.VerifyFullyPaidMessage(), "Order Fully Paid. No more payments required.", "Order fully paid message is not displayed");
            }

            cashandcarrypayment.ClickFinishBtnOnCashAndCarryPaymentPage();

            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 14 - Dashboard order page is not displayed");

            // Test Step - 12
            delayWithGivenTime(2000);

            if (!dashboardorder.get_Selected_View_On_AllOrdersPage().equalsIgnoreCase("Last 30 days")) {
                dashboardorder.Select_views_dropdown_on_all_ordersPage("Last 30 days");
            }
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 14 - Respective Invoice number is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoiceNumber), mop, "Test Step - 14 - Respective MOP is not displayed on all orders page");
            delayWithGivenTime(2000);

            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 14 - Respective Invoice number is not displayed on all orders page");

            dashboardorder.clickInvoiceNumber_On_TableGrid_AllOrdersPage(invoiceNumber);
            delayWithGivenTime(2000);
            dashboardorder.Click_PaymentTab_On_InvoicePopup();

            softassert.assertEquals(dashboardorder.get_paymentdescription_row1(), "Split Payment", "Test Step - 14: Payments Description as split is not displayed as Split payment in row1");
            softassert.assertEquals(dashboardorder.get_paymentdate_row1(), paid_date, "Test Step - 14: Payments date is not displayed as Current system date in row1");
            softassert.assertEquals(dashboardorder.get_payment_amount_row1(), total_Amount, "Test Step - 14: Payments amount as split is not displayed as Current system date in row1");

            softassert.assertEquals(dashboardorder.get_paymentdescription_row2(), "Cash", "Test Step - 14: Payments Description as cash is not displayed as Cash in row2");
            softassert.assertEquals(dashboardorder.get_paymentdate_row2(), paid_date, "Test Step - 14: Payments date is not displayed as Current system date in row2");
            softassert.assertEquals(dashboardorder.get_payment_amount_row2(), paidcashamount, "Test Step - 14: Paid amount as cash is not displayed as provided paid amount in row2");

            softassert.assertEquals(dashboardorder.get_paymentdescription_row3(), "Check", "Test Step - 14: Payments Description as check is not displayed as Check in row3");
            softassert.assertEquals(dashboardorder.get_paymentdate_row3(), paid_date, "Test Step - 14: Payments date is not displayed as Current system date in row3");
            softassert.assertEquals(dashboardorder.get_payment_amount_row3(), paidcheckamount, "Test Step - 14: Paid amount as check is not displayed as provided paid amount in row3");

            softassert.assertEquals(dashboardorder.get_paymentdescription_row4(), "Paid Outside Hana", "Test Step - 14: Payments Description as paid outside hana is not displayed as Paid Outside Hana in row4");
            softassert.assertEquals(dashboardorder.get_paymentdate_row4(), paid_date, "Test Step - 14: Payments date is not displayed as Current system date in row4");
            softassert.assertEquals(dashboardorder.get_payment_amount_row4(), pohamount, "Test Step - 14: Paid amount as paid outside hana is not displayed as provided paid amount in row4");

            softassert.assertEquals(dashboardorder.get_paymentdescription_row5(), "Gift Card", "Test Step - 14: Payments Description as gift card is not displayed as Gift Card in row5");
            softassert.assertEquals(dashboardorder.get_paymentdate_row5(), paid_date, "Test Step - 14: Payments date is not displayed as Current system date in row5");
            softassert.assertEquals(dashboardorder.get_payment_amount_row5(), giftcardamount, "Test Step - 14: Paid amount as gift card is not displayed as provided paid amount in row5");

            softassert.assertEquals(dashboardorder.get_paymentdescription_row6(), "Cash", "Test Step - 14: Payments Description as cash is not displayed as Cash in row6");
            softassert.assertEquals(dashboardorder.get_paymentdate_row6(), paid_date, "Test Step - 14: Payments date is not displayed as Current system date in row6");
            softassert.assertEquals(dashboardorder.get_payment_amount_row6(), "$" + balance_split_amt, "Test Step - 14: Paid amount as cash is not displayed as provided paid amount in row6");
            dashboardorder.ClickCloseIconOnDeliveryPopup();

            // Test Step - 15
            delayWithGivenTime(2000);
            dashboardorder.Select_views_dropdown_on_all_ordersPage(selectview);
            delayWithGivenTime(3000);
            softassert.assertEquals(dashboardorder.get_Selected_View_On_AllOrdersPage(), selectview, "Test Steps - 15: Selected view is not displayed on all orders page");
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 15 - Respective split payment type Invoice number : " + invoiceNumber + " is not displayed on all orders page - split payment filter view");
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoiceNumber), mop, "Test Step - 15 - Respective split payment type MOP as " + mop + " is not displayed on all orders page - selected split payment view");

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
