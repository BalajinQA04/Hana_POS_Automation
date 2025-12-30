package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

import java.io.IOException;

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

public class Hana_T082_Pickup_Functionality_with_New_customer_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private CustomerPage customerpage;
    public static final String dataSheetName = "Hana_T82";
    String invoiceNumber;
    String customerName;
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Phone Order Module - Pickup Type")
    @Test(enabled = true, groups = {"Regression", "Smoke", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T082_Pickup_Functionality_with_New_customer_Test(String salesperson, String custaddress1, String custzip, String custphone, String recifname, String recilname, String reciaddress1, String reciaddress2, String recizip,
                                                                               String recicity, String recicountry, String reciphone, String recilocation, String occasion, String searchandselectitemcode, String paymenttype, String cashregistry) {

        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Hana_T82_Pickup_Functionality_with_New_customer_FT  ****");
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
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 2: Entered username is not matching with expected username");
            logger.info("User entered the username as " + prop.getProperty("username"));

            lp.EnterPassword(prop.getProperty("password"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 2: Entered password is not matching with expected password");
            logger.info("User entered the password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2: Page did not navigate to hana dashboard page");
            logger.info("User navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3: Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3: Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5: highlight color from config
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);

            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(),
                    prop.getProperty("ui_phoneorder_pickup_highlight_color"),
                    "Test Step - 5 - Pickup type is not highlighted in expected color");

            // Test Step - 6
            Faker usFaker = new Faker(new java.util.Locale("en-US"));
            Faker canadaFaker = new Faker(new java.util.Locale("en-CA"));
            // Generate random US customer details
            String usFirstName = usFaker.name().firstName();
            String usLastName = usFaker.name().lastName();
            String usFullName = usFirstName + " " + usLastName;

            String companyname = Generate_CompanyName();
            String usPhoneNumber = GenerateUsPhoneNumber();
            String caPhoneNumber = GenerateCAPhoneNumber();
            String usStreetAddress = usFaker.address().streetAddress();
            String usCity = usFaker.address().city();
            String usState = usFaker.address().state();
            String usZipCode = usFaker.address().zipCode();
            String usFullAddress = usStreetAddress + ", " + usCity + ", " + usState + " " + usZipCode;

            phoneorder.EnterCustomerFirstName(usFirstName);//"Mike" + twodigitrandomeString()
            phoneorder.EnterCustomerLastName(usLastName); //"Jones" + twodigitrandomeString()
            phoneorder.EnterAddress1(custaddress1);
            delayWithGivenTime(2000);
            phoneorder.EnterZipCode(custzip); // custzip
            phoneorder.EnterPhoneNumber(caPhoneNumber);//custphone
            delayWithGivenTime(2000);

            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage().contains(usFirstName), true, "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage().contains(usLastName), true, "Test Step - 6 - Last name is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Address 1 may be normalized to one of two expected values from config
            String expectedAddr1Option1 = prop.getProperty("new_customer_address1");
            String expectedAddr1Option2 = prop.getProperty("cust_new_address1");
            String actualAddr1 = phoneorder.getAddress1OnPhoneOrderPage();
            if (actualAddr1.contains(expectedAddr1Option1)) {
                softassert.assertEquals(actualAddr1, expectedAddr1Option1, "Test Step - 6 - address 1 is not displayed on phone order page");
            } else if (actualAddr1.contains(expectedAddr1Option2)) {
                softassert.assertEquals(actualAddr1, expectedAddr1Option2, "Test Step - 6 - address 1 is not displayed on phone order page");
            }

            // City is expected from config
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), prop.getProperty("cust_new_city"), "Test Step - 6 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), caPhoneNumber, "Test Step - 6 - phone number 1 is not displayed on phone order page");

            // ...existing new customer label verification...

            // Test Step - 7: recipient expectations using config and dynamic phones
            phoneorder.EnterReciFirstName(recifname);
            phoneorder.EnterReciLastName(recilname);
            phoneorder.EnterReciAddress1(reciaddress1);
            phoneorder.EnterReciAddress2(reciaddress2);
            phoneorder.EnterReciZipcode(recizip);
            delayWithGivenTime(1000);

            phoneorder.SelectReciCountry(recicountry);
            phoneorder.EnterReciPhone(usPhoneNumber);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recilocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(2000);

            softassert.assertEquals(phoneorder.getReciFirstName(), usFirstName, "Test Step - 7 - Entered first name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), usLastName, "Test Step - 7 - Entered last name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress1(), prop.getProperty("recipient_address1"), "Test Step - 7 - Entered address 1 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), prop.getProperty("default_address2"), "Test Step - 7 - Entered address 2 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), prop.getProperty("recipient_zipcode1"), "Test Step - 7 - Entered zipcode is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), prop.getProperty("recipient_city1"), "Test Step - 7 - Entered city is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), prop.getProperty("recipient_country1"), "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), usPhoneNumber, "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), prop.getProperty("recipient_location1"), "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");

            // Test Step - 8: occasion expectation from config
            delayWithGivenTime(2000);
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");

            // Test Step - 9: use product config instead of literals
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(searchandselectitemcode, prop.getProperty("productfulldesc1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), prop.getProperty("product_itemcode1"), "Test Step - 9 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), prop.getProperty("product_description1"), "Test Step - 9 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), prop.getProperty("rrd_product_quantity"), "Test Step - 9 - Item quantity is not displayed on phone order page product details section");
            delayWithGivenTime(1000);

            String unitPrice = phoneorder.getUnitPriceOnProdDetails();
            String expectedPrice1 = prop.getProperty("product_price");
            String expectedPrice2 = prop.getProperty("phoneorder_product_rrd_price_high");
            softassert.assertTrue(unitPrice.equals(expectedPrice1) || unitPrice.equals(expectedPrice2),
                    "Test Step - 9 - Item price " + unitPrice + " is not one of expected prices: " + expectedPrice1 + ", " + expectedPrice2);

            String extPrice = phoneorder.get_ExtPrice1OnProdDetails();
            String expectedExtPrice1 = prop.getProperty("product_ext_price");
            String expectedExtPrice2 = prop.getProperty("phoneorder_product_rrd_price_high");
            softassert.assertTrue(extPrice.equals(expectedExtPrice1) || extPrice.equals(expectedExtPrice2),
                    "Test Step - 9 - Item ext price " + extPrice + " is not one of expected prices: " + expectedExtPrice1 + ", " + expectedExtPrice2);

            // Test Step - 10
            delayWithGivenTime(2000);
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymenttype);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getDisplayedPaymentTypeSelectedOption(), paymenttype, "Test Step - 10 - Selected Payment type " + paymenttype + " is not displayed on phone order page payment section");
            delayWithGivenTime(1000);
            phoneorder.EnterCashAmount(phoneorder.getGrandTotalAmount());
            delayWithGivenTime(1000);
            phoneorder.SelectCashRegistry_On_CashPaymentType(cashregistry);
            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(2000);
            getDriver().switchTo().activeElement();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 10 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 11
            phoneorder.ClickCancelButton_On_ConfirmationPopup();

            // Test Step - 12
            delayWithGivenTime(1000);
            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 12 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(1000);

            // Test Step - 13
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 13 - Order confirmation page is not displayed");

            // Test Step - 14
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();
            delayWithGivenTime(500);
            customerName = orderconfirmationpage.getCustomerFname() + " " + orderconfirmationpage.getCustomerLname();
            orderconfirmationpage.click_orderInvoiceLink();

            /*  // Below code may causes exception. during parallel execution ....
             * dashboard.ClickOrder(); delayWithGivenTime(1000);
             * logger.info("User click the order menu on hana dashboard page");
             */

            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL")
                    , "Test Step - 14 - Dashboard order page is not displayed");

            // Test Step - 15/20: replace hardcoded delivery popup expectations
            delayWithGivenTime(1000);
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.Validate_PhoneOrder_Delivery_InvoiceNumber(prop.getProperty("Order_Type_PhoneOrder"), prop.getProperty("Delivery_Type_Pickup"), prop.getProperty("MOP_As_Cash")), "Test Step - 15 - In orders summary page - cash payment type invoice number is not displayed for placed order");
            dashboardorder.click_Status_Cell_On_AllOrdersPage(invoiceNumber);

            softassert.assertEquals(dashboardorder.getRecipientName_OnDeliveryPopup(), usFullName, "Test Step 15 - Recipient name is not displayed on delivery popup");
            softassert.assertEquals(dashboardorder.getRecipientPhoneNum_OnDeliveryPopup(), usPhoneNumber, "Test Step 15 - Recipient phone number is not displayed on delivery popup");
            softassert.assertEquals(dashboardorder.getRecipientAddress_OnDeliveryPopup(),
                    prop.getProperty("pickup_reci_full_address_display"),
                    "Test Step 15 - Recipient address is not displayed on delivery popup");
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.getCustAndcompyNameOnDeliveryPopup(), usFullName, "Test Step - 15 - customer and company name on delivery popup is not matched");

            delayWithGivenTime(2000);
            String custAddrOnPopup = dashboardorder.getCustAddressOnDeliveryPopup();
            if (custAddrOnPopup.contains(expectedAddr1Option1)) {
                softassert.assertEquals(custAddrOnPopup, expectedAddr1Option1, "Test Step - 15 -customer address on delivery popup is not matched");
            } else if (custAddrOnPopup.contains(expectedAddr1Option2)) {
                softassert.assertEquals(custAddrOnPopup, expectedAddr1Option2, "Test Step - 15 -customer address on delivery popup is not matched");
            }

            softassert.assertEquals(dashboardorder.getCustPhoneNumOnDeliveryPopup(), caPhoneNumber, "Test Step - 15 -customer phone number on delivery popup is not matched");
            logger.info("User verified displayed customer type, customer name, company name, Address, Phone number & email are matched");
            delayWithGivenTime(2000);

            softassert.assertEquals(dashboardorder.Verify_OrderType_Displayed_on_DeliveryPopup(),
                    prop.getProperty("ui_order_type_pickup_phone_order"),
                    "Test Step - 16 - Proper Order type is not displayed on delivery popup");

            // Test Step - 16
            delayWithGivenTime(2000);
            customerpage = new CustomerPage();
            dashboardorder.ClickCustomerMenuOnDashboard();
            logger.info("User clicks on customer menu on dashboard");
            delayWithGivenTime(2000);
            softassert.assertTrue(customerpage.VerifyCustomerMenuPage(), "Test Step - 16 - customer menu page is not displayed");
            logger.info("User verify that customer menu page is displayed successfully");

            // Test Step - 17
            delayWithGivenTime(500);
            customerpage.click_CustomerId_Header_OnCustTable();
            delayWithGivenTime(500);
            softassert.assertTrue(customerpage.verify_custId_AscendingIcon(), "Test Step - 17 - customer id on customer table page ascending icon is not displayed");
            delayWithGivenTime(2000);
            customerpage.click_CustomerId_Header_OnCustTable();
            delayWithGivenTime(1000);
            softassert.assertTrue(customerpage.verify_custId_DescendingIcon(), "Test Step - 17 - customer id on customer table page descending icon is not displayed");

            customerpage.Enter_CustomerName_searchbox_OnCustTable(usFullName);
            ThreadWait(1000);

            softassert.assertEquals(customerpage.VerifyPhoneNumberOnCustTable(), caPhoneNumber, "Test Step - 17 -Phone number on customer table is not matched");
            logger.info("User verify that phone number on customer table");

            if (customerpage.VerifyAddressOnCustTable().equals("2715 35th Ave Ct")) {
                softassert.assertEquals(customerpage.VerifyAddressOnCustTable(), "2715 35th Ave Ct", "Test Step - 17 - Address on customer table is not matched");
                logger.info("User verify that address on customer table");
            } else if (customerpage.VerifyAddressOnCustTable().equals("2715 35th Ave")) {
                softassert.assertEquals(customerpage.VerifyAddressOnCustTable(), "2715 35th Ave", "Test Step - 17 - Address on customer table is not matched");
            }

            // Test Step - 18
            delayWithGivenTime(2000);
            customerpage.ClickCustomerTableRow1();
            logger.info("User clicks the displayed customer in the table ");
            delayWithGivenTime(3000);
            softassert.assertTrue(customerpage.VerifyCustomerDetailsPopup(), "Test Step - 18 - Customer details pop up is not displayed");
            logger.info("User verify that customer details popup is displayed");

            // Test Step - 19
            delayWithGivenTime(2000);
            softassert.assertEquals(customerpage.getCustDetailsFirstNameTextBox().contains(usFirstName), true, "Test Step - 19 - Added on first name field are not properly displayed");
            logger.info("User verify the first name field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsLastNameTextBox().contains(usLastName), true, "Test Step - 19 - Added on first name field are not properly displayed");
            logger.info("User verify the last name field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsPhoneNumberTextbox(), caPhoneNumber, "Test Step - 19 - Added on phone number field are not properly displayed");
            logger.info("User verify the phone number field entered data is displayed");
            if (customerpage.getCustDetailsAddress1TextBox().equals("2715 35th Ave Ct")) {
                softassert.assertEquals(customerpage.getCustDetailsAddress1TextBox(), "2715 35th Ave Ct", "Test Step - 19 - Added address 1 field is not properly displayed");
                logger.info("User verify that address 1 field entered data is displayed");
            } else if (customerpage.getCustDetailsAddress1TextBox().equals("2715 35th Ave")) {
                softassert.assertEquals(customerpage.getCustDetailsAddress1TextBox(), "2715 35th Ave", "Test Step - 19 - Added address 1 field is not properly displayed");

            }
        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
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

