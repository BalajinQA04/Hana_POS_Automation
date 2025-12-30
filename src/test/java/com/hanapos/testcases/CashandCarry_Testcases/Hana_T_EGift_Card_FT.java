package com.hanapos.testcases.CashandCarry_Testcases; // Package for Cash & Carry related test cases

import com.github.javafaker.Faker; // Faker library for generating test data
import com.hanapos.pageObjects.*; // Page Object Model classes for UI actions
import com.hanapos.seleniumProjectBase.TestBaseClass; // Base class providing test setup and utilities
import com.hanapos.utilities.ConsoleLogUtils; // Utility to capture browser console logs
import com.hanapos.utilities.CustomSoftAssert; // Custom soft assertion wrapper
import com.hanapos.utilities.DataLibrary; // Utility to fetch test data (e.g., from Excel)
import com.hanapos.utilities.LoggerUtil; // Utility to attach logs to test report
import io.qameta.allure.Epic; // Allure annotation for epic grouping
import org.testng.Assert; // TestNG hard assertions
import org.testng.annotations.DataProvider; // TestNG data provider
import org.testng.annotations.Test; // TestNG test annotation

import java.io.IOException; // For handling IO operations when reading data

public class Hana_T_EGift_Card_FT extends TestBaseClass { // Test class for validating E-Gift Card flow
    private LoginPage lp; // Page object for Login
    private HanaDashBoardPage dashboard; // Page object for Dashboard
    private CashAndCarryPage cashandcarry; // Page object for Cash & Carry
    private CashAndCarryPaymentPage cashandcarrypayment; // Page object for Payment
    private DashboardOrderPage dashboardorder; // Page object for Orders page

    public static final String dataSheetName = "Hana_T28"; // Excel sheet name for test data
    String gift_cardno; // Generated gift card number
    String gift_amount; // Gift amount entered/validated
    String invoice; // Invoice number captured post-payment
    String processingfee = String.valueOf(5); // Processing fee applied to gift card
    String recipient_name; // Recipient name for e-gift card
    String recipient_email; // Recipient email for e-gift card
    String message; // Personalized message for e-gift card
    CustomSoftAssert softassert = new CustomSoftAssert(); // Soft assert instance
    public static LoggerUtil logger_Util; // Logger utility instance

    @DataProvider(name = "fetch_Excel_Data") // Provides datasets for data-driven tests
    public Object[][] fetchData() throws IOException { // Reads test data from the specified sheet
        return DataLibrary.readExcelData(dataSheetName); // Return 2D Object array
    }


    @Epic("Cash and Carry Module") // Allure epic grouping for reporting
    @Test(enabled = true, groups = {"Smoke", "Sanity", "Regression"}) // TestNG groups for this test
    public void Validate_Hana_T_EGift_Card_FT() { // Validates end-to-end E-Gift Card purchase flow
        logger_Util = new LoggerUtil(); // Initialize logger utility
        String testCaseName = getCurrentTestName(); // Capture current test name
        try { // Begin test steps
            lp = new LoginPage(); // Create Login page object
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed"); // Verify login page

            // Test Step - 2: Login to application
            lp.EnterUserName(prop.getProperty("bestuname")); // Enter username from properties
            lp.EnterPassword(prop.getProperty("bestpass")); // Enter password from properties
            lp.ClickLoginButton(); // Click login button

            dashboard = new HanaDashBoardPage(); // Create Dashboard page object
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page"); // Validate dashboard

            // Test Step - 3: Navigate to Cash & Carry
            dashboard.SelectShopNameDropDown(prop.getProperty("bestshopname")); // Select shop from dropdown
            dashboard.CashAndCarryMenuClick(); // Click Cash & Carry menu
            cashandcarry = new CashAndCarryPage(); // Create Cash & Carry page object
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3 - Cash And Carry page is not displayed"); // Verify page load

            // Test Step - 4: Start gift card selection
            cashandcarry.ClickGiftCardTile(); // Click on Gift Card tile
            delayWithGivenTime(1000); // Wait for popup to appear
            softassert.assertTrue(cashandcarry.is_GiftCard_Select_Type_Popup_Displayed(), "Test Step - 4 - Select gift card type pop up is not displayed"); // Verify popup visible

            cashandcarry.click_EGiftCard_Radio_Button(); // Choose E-Gift Card option
            delayWithGivenTime(1000); // Small wait
            cashandcarry.click_Next_Button_On_GiftCard_Select_Type_Popup(); // Proceed to next step
            delayWithGivenTime(2000); // Wait for details popup

            softassert.assertTrue(cashandcarry.is_EGift_Card_Details_Popup_Displayed(), "Test Step - 4 - E-gift card Enter gift card details pop up is not displayed"); // Verify details popup
            delayWithGivenTime(1000); // Stabilize UI

            cashandcarry.search_And_Select_Customer_On_Enter_Gift_Card_Details_Popup(prop.getProperty("custfullname")); // Search and select customer
            delayWithGivenTime(1000); // Wait for selection

            Faker faker = new Faker(new java.util.Locale("en-US")); // Initialize Faker with locale
            recipient_name = faker.name().firstName(); // Generate random recipient name
            recipient_email = "hanaposqateam@gmail.com"; // Use test recipient email
            gift_amount = String.valueOf(faker.number().numberBetween(400, 700)); // Generate gift amount
            //gift_amount = String.format("%.2f", faker.number().randomDouble(2, 500, 700)); // Alternative with decimals
            delayWithGivenTime(1000); // Buffer before inputs

            cashandcarry.enter_Recipient_Name_Egift_Card_Popup(recipient_name); // Enter recipient name
            delayWithGivenTime(1000); // Wait for reflection
            softassert.assertEquals(cashandcarry.get_Recipient_Name_Egift_Card_Popup(), recipient_name, "Test Step - 5 - Recipient name is not displayed"); // Validate recipient name

            cashandcarry.set_Recipient_Email_Egift_Card_Popup(recipient_email); // Enter recipient email
            delayWithGivenTime(1000); // Wait for reflection
            softassert.assertEquals(cashandcarry.get_Recipient_Email_Egift_Card_Popup(), recipient_email, "Test Step - 6 - Recipient email is not displayed"); // Validate email

            cashandcarry.EnterAmountOnGiftamtField(gift_amount); // Enter gift amount
            delayWithGivenTime(1000); // Wait for calculation
            softassert.assertEquals(cashandcarry.getGiftAmountValue(), gift_amount + ".00", "Test Step - 6 - Gift amount values are not matched"); // Validate amount

            delayWithGivenTime(500); // Short wait
            cashandcarry.EnterProcessingFeesOnGiftSalePopup(processingfee); // Enter processing fee
            softassert.assertEquals(cashandcarry.getGiftCardProcessingFee(), "5.00", "Test Step - 9 - gift card processing fee is not matched"); // Validate fee

            delayWithGivenTime(2000); // Allow total computation
            softassert.assertEquals(cashandcarry.getTotalGiftValue(), cashandcarry.expectedTotalGiftValue(), " Test Step - 9 - Total gift values are not matched"); // Validate total

            delayWithGivenTime(500); // Wait before reading values
            gift_cardno = cashandcarry.getGiftCardNumber(); // Capture generated gift card number
            message = faker.lorem().sentence(); // Generate short message
            cashandcarry.enter_Message_Egift_Card_Popup(message); // Enter message
            cashandcarry.saveGiftCardInfoToFile(gift_cardno, gift_amount); // Save card info externally

            cashandcarry.click_Next_Button_On_EGiftCard_Popup(); // Proceed to design selection
            delayWithGivenTime(2000); // Wait for design popup
            softassert.assertTrue(cashandcarry.is_Select_Gift_Card_Design_Popup_Displayed(), "Test Step - 10 - Select gift card design pop up is not displayed after clicking next button"); // Verify design popup
            delayWithGivenTime(1000); // UI settle
            cashandcarry.select_Occasion_Egift_Card_Popup("Birthday"); // Select occasion
            delayWithGivenTime(1000); // Wait for selection
            softassert.assertEquals(cashandcarry.get_Occasion_Egift_Card_Popup(), "Birthday", "Test Step - 11 - Occasion is not displayed"); // Validate occasion

            cashandcarry.click_Gift_Card_Design_Button(); // Pick gift card design

            softassert.assertEquals(cashandcarry.get_Gift_Amount_On_Select_Gift_Card_Design_Popup(), "$" + gift_amount + ".00", "Test Step - 12 - Gift amount values are not matched in select gift card design popup"); // Validate amount on design popup

            delayWithGivenTime(1000); // Pause before preview
            cashandcarry.click_Preview_Button_On_Select_Gift_Card_Design_Popup(); // Open preview popup
            delayWithGivenTime(2000); // Wait for preview to load
            softassert.assertTrue(cashandcarry.is_Gift_Card_Sale_Preview_Popup_for_Egiftcard_Displayed(), "Test Step - 11 - Gift card sale preview popup for egift card is not displayed"); // Verify preview popup

            delayWithGivenTime(1000); // Ensure full data rendered
            softassert.assertEquals(cashandcarry.get_Customer_Name_Gift_Card_Sale_Preview_Popup_for_Egiftcard(), prop.getProperty("custfullname"), "Test Step - 15 - Customer name values are not matched in preview popup"); // Validate customer name
            softassert.assertEquals(cashandcarry.get_Recipient_Name_Gift_Card_Sale_Preview_Popup_for_Egiftcard(), recipient_name, "Test Step - 12 - Recipient name values are not matched in preview popup"); // Validate recipient name
            softassert.assertEquals(cashandcarry.get_Occasion_Gift_Card_Sale_Preview_Popup_for_Egiftcard(), "Birthday", "Test Step - 13 - Selected Occasion are not matched in preview popup"); // Validate occasion
            softassert.assertEquals(cashandcarry.get_Gift_Amount_Gift_Card_Sale_Preview_Popup_for_Egiftcard(), gift_amount + ".00", "Test Step - 14 - Gift amount values are not matched in preview popup"); // Validate amount
            softassert.assertEquals(cashandcarry.get_Processing_Fee_Gift_Card_Sale_Preview_Popup_for_Egiftcard(), "5.00", "Test Step - 15 - Gift processing fee values are not matched in preview popup"); // Validate processing fee
            softassert.assertEquals(cashandcarry.get_Total_Amount_Gift_Card_Sale_Preview_Popup_for_Egiftcard(), cashandcarry.expectedTotalGiftValue(), "Test Step - 16 - Total amount values are not matched in preview popup"); // Validate total amount
            softassert.assertEquals(cashandcarry.get_Gift_Card_Number_Gift_Card_Sale_Preview_Popup_for_Egiftcard(), gift_cardno, "Test Step - 17 - Gift card number values are not matched in preview popup"); // Validate gift card number
            softassert.assertEquals(cashandcarry.get_Message_Gift_Card_Sale_Preview_Popup_for_Egiftcard(), message, "Test Step - 18 - Message values are not matched in preview popup"); // Validate message

            delayWithGivenTime(2000); // Buffer before submit
            cashandcarry.click_EGift_Card_Submit_Button_On_Preview_Popup(); // Submit e-gift card

            delayWithGivenTime(1000); // Wait for toast
            cashandcarrypayment = new CashAndCarryPaymentPage(); // Initialize payment page
            softassert.assertEquals(cashandcarrypayment.getSuccessToastMsg(), "Gift card has been added", "Test Step - 18 - Success toast message is not displayed after clicking submit button"); // Verify success toast
            String customerid = cashandcarry.get_Displayed_CustomerId(); // Capture customer ID
            delayWithGivenTime(2000); // Wait for UI stability
            softassert.assertEquals(cashandcarry.get_Displayed_CustomerId(), customerid, "Test Step - 19 - Customer ID values are not matched"); // Validate ID
            softassert.assertEquals(cashandcarry.getDisplayedCustomerNameOnCCPage(), prop.getProperty("custfullname"), "Test Step - 20 - Customer name values are not matched"); // Validate customer name

            cashandcarry.SelectClerkName(prop.getProperty("cashandcarryclerkname")); // Select clerk name
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename")); // Select employee name
            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype")); // Select tax type
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 10 : Selected tax type is not displayed"); // Validate tax type
            cashandcarry.SelectOccasion(prop.getProperty("occasion")); // Select occasion
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 10 : Selected occasion is not displayed"); // Validate occasion selection

            cashandcarry.ClickPayButton(); // Proceed to payment page
            delayWithGivenTime(2000); // Wait for navigation
            cashandcarrypayment = new CashAndCarryPaymentPage(); // Re-initialize payment page object
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 23 - Cash And Carry payment page is not displayed"); // Verify payment page

            cashandcarrypayment.ClickCashTab(); // Select cash payment method
            cashandcarrypayment.EnterGivenAmount(); // Enter tendered amount
            cashandcarrypayment.ClickProcessPaymentBtn(); // Click process payment
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 24 - Success toast message is not displayed"); // Verify payment success toast
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Mail has been sent successfully", "Test Step - 25 - Order confirmation toast message is not displayed"); // Verify confirmation toast

            delayWithGivenTime(2000); // Wait for confirmation popup
            if (cashandcarrypayment.getConfirmationPopup()) { // If confirmation popup is displayed
                cashandcarrypayment.VerifyOrderConfirmationPopup(); // Verify popup UI and contents
                cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo(); // Capture confirmation details
                invoice = cashandcarrypayment.GetInvoiceNumber(); // Store invoice number
                cashandcarrypayment.GetTenderPrice(); // Capture tender price
            }

            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn(); // Close the confirmation popup

            dashboard.ClickOrder(); // Navigate to Orders from dashboard
            delayWithGivenTime(1000); // Wait for page load
            dashboardorder = new DashboardOrderPage(); // Initialize Orders page object
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 20: User is not navigated to All orders page"); // Validate Orders page
            delayWithGivenTime(1000); // Stabilize

            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice), "Test Step - 20 - Respective Invoice number : " + invoice + " is not displayed on all orders page"); // Verify invoice appears
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoice), "Delivered", "Test Step - 20 - Order status is not displayed as delivered for cash and carry order"); // Verify status
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoice), "Walkin Sales", "Test Step - 20: Order Type as Walkin Sales is not properly displayed for cash and carry order"); // Verify order type

            dashboardorder.EnterGlobalSearch(invoice); // Search order by invoice number
            delayWithGivenTime(1000); // Wait for results to refresh
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoice), "Cash", "Test STep - 20: Mode of pay is not displayed as Cash for cash and carry order"); // Verify mode of payment

        } catch (Exception e) { // Catch unexpected exceptions
            logger_Util = new LoggerUtil(); // Reinitialize logger
            logger_Util.attachNetworkLogs(testCaseName); // Attach network logs for debugging
            ConsoleLogUtils.CaptureConsoleLogs(testCaseName); // Capture browser console logs
            softassert.fail(e.getMessage()); // Mark test as failed with error
        } finally { // Always execute this block
            try {
                softassert.assertAll(); // Assert all soft assertions
            } catch (AssertionError ae) { // If any soft assertion failed
                logger_Util = new LoggerUtil(); // Reinitialize logger for attachments
                logger_Util.attachNetworkLogs(testCaseName); // Attach network logs on failure
                ConsoleLogUtils.CaptureConsoleLogs(testCaseName); // Capture console logs on failure
                throw ae; // Rethrow to fail the test
            }
        }
    }


}
