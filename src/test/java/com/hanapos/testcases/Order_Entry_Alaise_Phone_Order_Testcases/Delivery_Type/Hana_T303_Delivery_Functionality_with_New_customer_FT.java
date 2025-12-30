package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Delivery_Type;

import java.io.IOException;
import java.util.Locale;

import com.github.javafaker.Faker;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CustomerPage;
import com.hanapos.pageObjects.DashboardOrderPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.pageObjects.Order_Confirmation_Page;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T303_Delivery_Functionality_with_New_customer_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private CustomerPage customerpage;
    public static final String dataSheetName = "Hana_T303";
    String invoiceNumber;
    String customerName;
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Phone Order Module - Delivery Type")
    @Test(enabled = true, groups = {"Regression", "Smoke", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T303_Delivery_Functionality_with_New_customer_Functionality_Test(String salesperson, String custaddress1, String custzip, String custphone, String recifname, String recilname, String reciaddress1, String reciaddress2, String recizip, String recicity, String recicountry, String reciphone, String recilocation, String occasion, String searchandselectitemcode, String paymenttype, String cashregistry) {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T303_Delivery_Functionality_with_New_customer_Functionality_Test  ****");
        logger.debug("capturing application debug logs....");
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickDeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnDeliveryTypeOnPhoneOrderPage(), "#676a6c", "Test Step - 5: Delivery type as Delivery is not highlighted in blue color");

            // Test Step - 6
            Faker usFaker = new Faker(new java.util.Locale("en-US"));
            Faker canadaFaker = new Faker(new java.util.Locale("en-CA"));
            // Generate random US customer details
            String custFirstName = usFaker.name().firstName();
            String custLastName = usFaker.name().lastName();
            String customerfullname = custFirstName + " " + custLastName;

            String companyname = Generate_CompanyName();
            String customerPhoneNumber = GenerateUsPhoneNumber();
            String recipientPhoneNumber = GenerateCAPhoneNumber();
            String usStreetAddress = usFaker.address().streetAddress();
            String usCity = usFaker.address().city();
            String usState = usFaker.address().state();
            String usZipCode = usFaker.address().zipCode();
            String usFullAddress = usStreetAddress + ", " + usCity + ", " + usState + " " + usZipCode;


            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            phoneorder.EnterCustomerFirstName(custFirstName);
            phoneorder.EnterCustomerLastName(custLastName);
            phoneorder.EnterAddress1(custaddress1);
            phoneorder.EnterZipCode(custzip);
            phoneorder.EnterPhoneNumber(customerPhoneNumber);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), custFirstName, "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), custLastName, "Test Step - 6 - Last name is not displayed on phone order page");

            if (phoneorder.getAddress1OnPhoneOrderPage().contains(prop.getProperty("new_customer_address1"))) {
                softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), prop.getProperty("new_customer_address1"), "Test Step - 6 - address 1 is not displayed on phone order page");
            } else if (phoneorder.getAddress1OnPhoneOrderPage().contains("2715 N 35th Ave")) {
                softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "2715 N 35th Ave", "Test Step - 6 - address 1 is not displayed on phone order page");
            }

            if (phoneorder.getZipCodeOnPhoneOrderPage() == "94116") {
                softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "94116", "Test Step - 6 - Zipcode is not displayed on phone order page");
            } else if (phoneorder.getZipCodeOnPhoneOrderPage() == "80634") {
                softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "80634", "Test Step - 6 - Zipcode is not displayed on phone order page");
            }

            if (phoneorder.getCityOnPhoneOrderPage() == "San Francisco") {
                softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "San Francisco", "Test Step - 6 - city is not displayed on phone order page");
            } else if (phoneorder.getCityOnPhoneOrderPage() == "Greeley") {
                softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "Greeley", "Test Step - 6 - city is not displayed on phone order page");
            }

            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), customerPhoneNumber, "Test Step - 6 - phone number 1 is not displayed on phone order page");
            phoneorder.EnterReciFirstName("");
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_NewCustomer_Label_Appears(), "Test Step - 6 - New customer label is not displayed Customer Section on phone order page");

            // Test Step - 7
            // Generate random US customer details
            String RecipientFirstName = canadaFaker.name().firstName();
            String RecipientLastName = canadaFaker.name().lastName();
            String RecipientFullName = RecipientFirstName + " " + RecipientLastName;

            phoneorder.EnterReciFirstName(RecipientFirstName);
            phoneorder.EnterReciLastName(RecipientLastName);
            phoneorder.EnterReciAddress1(reciaddress1);
            phoneorder.EnterReciAddress2(prop.getProperty("recipient_address2"));
            phoneorder.EnterReciZipcode(recizip);
            delayWithGivenTime(1000);
            //phoneorder.EnterReciCity(recicity);
            phoneorder.SelectReciCountry(recicountry);
            phoneorder.EnterReciPhone(recipientPhoneNumber);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recilocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(NextDate());
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), RecipientFirstName, "Test Step - 7 - Entered first name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), RecipientLastName, "Test Step - 7 - Entered last name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress1(), "3402 Park Blvd", "Test Step - 7 - Entered address 1 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), prop.getProperty("recipient_address2"), "Test Step - 7 - Entered address 2 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "92103", "Test Step - 7 - Entered zipcode is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "San Diego", "Test Step - 7 - Entered city is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), recipientPhoneNumber, "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), "Church", "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), NextDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");

            //Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 9
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(searchandselectitemcode, prop.getProperty("product_description1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), prop.getProperty("product_itemcode1"), "Test Step - 9 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), prop.getProperty("product_description1"), "Test Step - 9 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 9 - Item quantity is not displayed on phone order page product details section");

            if (phoneorder.getUnitPriceOnProdDetails() == "299.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            } else if (phoneorder.getUnitPriceOnProdDetails() == "309.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "309.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            }

            delayWithGivenTime(2000);

            // Test Step - 10
            phoneorder.EnterSpecialInstructions_ProductDetailsSection("Automation Special Instruction - Flowers must be delivered on a schedule time");
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SpecialInstructions_ProductDetailsSection(), "Automation Special Instruction - Flowers must be delivered on a schedule time", "Test Step - 9 - Special instructions is not displayed on phone order page product details section");

            phoneorder.EnterDriverInstructions_ProductDetailsSection("Automation Driver Instruction will be handle with care and dont delay to deliver the flower");
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_DriverInstructions_ProductDetailsSection(), "Automation Driver Instruction will be handle with care and dont delay to deliver the flower", "Test Step - 9 - Driver instructions is not displayed on phone order page product details section");

            phoneorder.EnterCustomerPrivateNotesInstructions_ProductDetailsSection("Automation Customer Private Notes");
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_CustomerPrivateNotesInstructions_ProductDetailsSection(), "Automation Customer Private Notes", "Test Step - 9 - Customer private notes is not displayed on phone order page product details section");

            phoneorder.Select_TaxType_OnPhoneOrderPage(prop.getProperty("product_taxtype"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_TaxType_OnPhoneOrderPage(), prop.getProperty("product_taxtype"), "Test Step - 9 - Tax type is not displayed on phone order page product details section");

            Faker faker = new Faker(new Locale("en-US"));
            String taxId = "AT_" + faker.number().digits(4);
            phoneorder.enter_TaxId_On_ProductDetails_Section_Phone_OrderPage(taxId);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_TaxId_OnPhoneOrderPage(), taxId, "Test Step - 9 - Entered Tax id is not displayed on phone order page product details section");

            phoneorder.Select_ProdSourceCode("Google Search");
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_selected_ProdSourceCode(), "Google Search", "Test Step - 9 - Selected product source code is not displayed on phone order page product details section");

            phoneorder.select_Customer_Type_On_Product_Details_Section_PhoneOrderPage("Staff");
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getSelectedCustTypeOnPhoneOrderPage(), "Staff", "Test Step - 9 - Selected customer type is not displayed on phone order page product details section");

            delayWithGivenTime(2000);

            // Test Step - 10
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), prop.getProperty("payment_type"), "Test Step - 10 - Selected payment type is not displayed");

            // Test Step - 11
            delayWithGivenTime(1000);
            phoneorder.Enter_CashPaymentType_Amount();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_Entered_CashPaymentType_Amount(), phoneorder.get_Entered_CashPaymentType_Amount(), "Test Step - 11 - Entered more than cash payment amount is not allowed on the payment section cash type pay amount field");

            // Test Step - 12
            phoneorder.SelectCashRegistry_On_CashPaymentType(prop.getProperty("payment_cash_registry"));

            // Test Step - 13
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.Verify_Change_GivenBackTo_Customer_amount_IsDisplayed(), "100.00", "Test Step - 12 - Change given back to customer amount label is not displayed under the cash payment type select registered dropdown");
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_Selected_CashRegistry(), prop.getProperty("payment_cash_registry"), "Test Step - 13 - Selected cash payment registered is not displayed");


            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 10 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 11
            phoneorder.ClickCancelButton_On_ConfirmationPopup();

            // Test Step - 12
            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 12 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(1000);

            // Test Step - 13
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 13 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();
            customerName = orderconfirmationpage.getCustomerFname() + " " + orderconfirmationpage.getCustomerLname();

            // Test Step - 14
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 14 - Dashboard order page is not displayed");

            // Test Step - 15,20
            dashboardorder.EnterGlobalSearch(invoiceNumber); //dashboardorder.get_InvoiceNumber_PhoneOrder_Delivery_AsCashPayment()
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 12 - In orders summary page - " + prop.getProperty("payment_type") + " payment type invoice number is not displayed for placed order");

            delayWithGivenTime(2000);
            dashboardorder.clickInvoiceNumber_On_TableGrid_AllOrdersPage(invoiceNumber);
            delayWithGivenTime(1000);

            softassert.assertEquals(dashboardorder.getRecipientName_OnDeliveryPopup(), RecipientFullName, "Test Step 15 - Recipient name is not displayed on delivery popup");
            softassert.assertEquals(dashboardorder.getRecipientPhoneNum_OnDeliveryPopup(), recipientPhoneNumber, "Test Step 15 - Recipient phone number is not displayed on delivery popup");
            softassert.assertEquals(dashboardorder.getRecipientAddress_OnDeliveryPopup(), "3402 Park Blvd " + prop.getProperty("recipient_address2") + " San Diego CA 92103 US", "Test Step 15 - Recipient address is not displayed on delivery popup");
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.getCustAndcompyNameOnDeliveryPopup(), customerfullname, "Test Step - 15 - customer and company name on delivery popup is not matched");
            System.out.println(dashboardorder.getCustAndcompyNameOnDeliveryPopup() + " - Newly registered customer name");
            //  softassert.assertEquals(dashboardorder.getCustAddressOnDeliveryPopup(), "2715 35th Ave Ct", "Test Step - 15 -customer address on delivery popup is not matched");
            if (dashboardorder.getCustAddressOnDeliveryPopup().contains("2715 35th Ave Ct")) {
                softassert.assertEquals(dashboardorder.getCustAddressOnDeliveryPopup(), "2715 35th Ave Ct", "Test Step - 6 - address 1 is not displayed on phone order page");
            } else if (dashboardorder.getCustAddressOnDeliveryPopup().contains("2715 N 35th Ave")) {
                softassert.assertEquals(dashboardorder.getCustAddressOnDeliveryPopup(), "2715 N 35th Ave", "Test Step - 6 - address 1 is not displayed on phone order page");
            }


            softassert.assertEquals(dashboardorder.getCustPhoneNumOnDeliveryPopup(), customerPhoneNumber, "Test Step - 15 -customer phone number on delivery popup is not matched");
            logger.info("User verified displayed customer type, customer name, company name, Address, Phone number & email are matched");
            delayWithGivenTime(2000);

            softassert.assertEquals(dashboardorder.Verify_OrderType_Displayed_on_DeliveryPopup(), "Delivery Phone Order", "Test Step - 16 - Proper Order type is not displayed on delivery popup");
            dashboardorder.ClickCloseIconOnDeliveryPopup();

            // Test Step - 16
            delayWithGivenTime(2000);
            customerpage = new CustomerPage();
            dashboardorder.ClickCustomerMenuOnDashboard();
            logger.info("User clicks on customer menu on dashboard");
            delayWithGivenTime(2000);
            softassert.assertTrue(customerpage.VerifyCustomerMenuPage(), "Test Step - 16 - customer menu page is not displayed");
            logger.info("User verify that customer menu page is displayed successfully");

            // Test Step - 17
            customerpage.Enter_CustomerName_searchbox_OnCustTable(customerName);
            ThreadWait(1000);
            delayWithGivenTime(2000);
            softassert.assertEquals(customerpage.VerifyPhoneNumberOnCustTable(), customerPhoneNumber, "Test Step - 17 -Phone number on customer table is not matched");
            logger.info("User verify that phone number on customer table");
            //  softassert.assertEquals(customerpage.VerifyAddressOnCustTable(), "2715 35th Ave Ct", "Test Step - 17 - Address on customer table is not matched");
            if (customerpage.VerifyAddressOnCustTable().contains("2715 35th Ave Ct")) {
                softassert.assertEquals(customerpage.VerifyAddressOnCustTable(), "2715 35th Ave Ct", "Test Step - 6 - address 1 is not displayed on phone order page");
            } else if (customerpage.VerifyAddressOnCustTable().contains("2715 N 35th Ave")) {
                softassert.assertEquals(customerpage.VerifyAddressOnCustTable(), "2715 N 35th Ave", "Test Step - 6 - address 1 is not displayed on phone order page");
            }


            // Test Step - 18
            delayWithGivenTime(2000);
            customerpage.ClickCustomerTableRow1();
            delayWithGivenTime(3000);
            softassert.assertTrue(customerpage.VerifyCustomerDetailsPopup(), "Test Step - 18 - Customer details pop up is not displayed");

            // Test Step - 19
            delayWithGivenTime(2000);
            softassert.assertEquals(customerpage.getCustDetailsFirstNameTextBox(), custFirstName, "Test Step - 19 - Added on first name field are not properly displayed");
            logger.info("User verify the first name field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsLastNameTextBox(), custLastName, "Test Step - 19 - Added on first name field are not properly displayed");
            logger.info("User verify the last name field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsPhoneNumberTextbox(), customerPhoneNumber, "Test Step - 19 - Added on phone number field are not properly displayed");
            logger.info("User verify the phone number field entered data is displayed");
            //  softassert.assertEquals(customerpage.getCustDetailsAddress1TextBox(), "2715 35th Ave Ct", "Test Step - 19 - Added address 1 field is not properly displayed");
            if (customerpage.VerifyAddressOnCustTable().contains("2715 35th Ave Ct")) {
                softassert.assertEquals(customerpage.VerifyAddressOnCustTable(), "2715 35th Ave Ct", "Test Step - 6 - address 1 is not displayed on phone order page");
            } else if (customerpage.VerifyAddressOnCustTable().contains("2715 N 35th Ave")) {
                softassert.assertEquals(customerpage.VerifyAddressOnCustTable(), "2715 N 35th Ave", "Test Step - 6 - address 1 is not displayed on phone order page");
            }
        } catch (Exception e) {
            softassert.fail("Test case failed due to exception\n " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
        } finally {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            softassert.assertAll();
        }
    }
}