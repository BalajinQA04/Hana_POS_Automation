package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireOut_Type;

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

public class Hana_T520_OrderEntryPage_WireOut_Functionality_with_New_customer_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private CustomerPage customerpage;
    public static final String dataSheetName = "Hana_T520";
    String invoiceNumber;
    String customerName;
    public static LoggerUtil logger_Util;
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
    // Generate random US customer details
    String RecipientFirstName = canadaFaker.name().firstName();
    String RecipientLastName = canadaFaker.name().lastName();
    String RecipientFullName = RecipientFirstName + " " + RecipientLastName;
    String wireout_amount;
    String Delivery_Date;
    String Ordered_Time;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Phone Order Module - Wire out Type")
    @Test(enabled = true, groups = {"Regression", "Smoke", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T520_OrderEntryPage_WireOut_Functionality_with_New_customer_Functionality_Test(String salesperson, String custaddress1, String custzip, String custphone, String recifname, String recilname,
                                                                                                             String reciaddress1, String reciaddress2, String recizip,
                                                                                                             String recicity, String recicountry, String reciphone, String recilocation, String occasion,
                                                                                                             String searchandselectitemcode, String itemdescription,
                                                                                                             String paymenttype, String cashregistry) {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T520_OrderEntryPage_WireOut_Functionality_with_New_customer_Functionality_Test  ****");
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

            phoneorder.Click_WireOut_DeliveryType_OnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_On_WireOut_PhoneOrderPage(), "#2f9bc8", "Test Step - 5 - Wire out Delivery type is not highlighted in blue color");

            // Test Step - 6
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            phoneorder.EnterCustomerFirstName(custFirstName);
            phoneorder.EnterCustomerLastName(custLastName);
            phoneorder.EnterAddress1(custaddress1);
            phoneorder.EnterZipCode(custzip);
            phoneorder.EnterPhoneNumber(customerPhoneNumber);
            delayWithGivenTime(2000);
            String firstname = phoneorder.getFirstnameOnPhoneOrderPage();
            String lastname = phoneorder.getLastnameOnPhoneOrderPage();

            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), custFirstName, "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), custLastName, "Test Step - 6 - Last name is not displayed on phone order page");

            if (phoneorder.getAddress1OnPhoneOrderPage().contains("2715 35th Ave Ct")) {
                softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "2715 35th Ave Ct", "Test Step - 6 - address 1 is not displayed on phone order page");
            } else if (phoneorder.getAddress1OnPhoneOrderPage().contains("2715 N 35th Ave")) {
                softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "2715 N 35th Ave", "Test Step - 6 - address 1 is not displayed on phone order page");
            }

            if (phoneorder.getZipCodeOnPhoneOrderPage().contains("80631")) {
                softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "80631", "Test Step - 6 - Zipcode is not displayed on phone order page");
            } else if (phoneorder.getZipCodeOnPhoneOrderPage().contains("80634")) {
                softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "80634", "Test Step - 6 - Zipcode is not displayed on phone order page");
            }

            if (phoneorder.getCityOnPhoneOrderPage().contains("San Francisco")) {
                softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "San Francisco", "Test Step - 6 - city is not displayed on phone order page");
            } else if (phoneorder.getCityOnPhoneOrderPage().contains("Greeley")) {
                softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "Greeley", "Test Step - 6 - city is not displayed on phone order page");
            }

            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), customerPhoneNumber, "Test Step - 6 - phone number 1 is not displayed on phone order page");
            phoneorder.EnterReciFirstName("");
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_NewCustomer_Label_Appears(), "Test Step - 6 - New customer label is not displayed on phone order page");

            // Test Step - 7
            phoneorder.EnterReciFirstName(RecipientFirstName);
            phoneorder.EnterReciLastName(RecipientLastName);
            String reci_full_address1 = "1841 W Linda Ln, Robertsville, MO 63072";
            String reci_address2 = "4840 Bergman Rd";

            phoneorder.SearchAndSelectReciAddress1(reci_full_address1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), RecipientFirstName, "Test Step - 7 - Entered first name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), RecipientLastName, "Test Step - 7 - Entered last name is not displayed on phone order page recipient section");

            softassert.assertEquals(phoneorder.getReciAddress1(), "1841 W Linda Ln", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63072", "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Robertsville", "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.EnterReciAddress2(reci_address2);
            delayWithGivenTime(1000);
            phoneorder.Enter_DeliveryTime_OnRecipientSection(15, 37);
            phoneorder.SelectReciCountry(recicountry);
            phoneorder.EnterReciPhone(recipientPhoneNumber);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recilocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Select_DeliveryOnTime_Dropdown("Exactly At");
            phoneorder.Select_Zone_OnRecipientSection("Automation Zone");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress2(), reci_address2, "Test Step - 7 - Entered address 2 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), recipientPhoneNumber, "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), "Church", "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");
            Delivery_Date = phoneorder.getDeliveryDateOnReciSection();

            //Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 9
            softassert.assertTrue(phoneorder.verify_ProductSuggestions_Appears(searchandselectitemcode), "Test Step - 9 - In Item code row 1 on product section autosuggestions are not displayed");
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(searchandselectitemcode, itemdescription);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), searchandselectitemcode, "Test Step - 9 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), itemdescription, "Test Step - 9 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "159.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 9 - Item quantity is not displayed on phone order page product details section");
            delayWithGivenTime(2000);

            // Test Step - 10
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymenttype);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getDisplayedPaymentTypeSelectedOption(), prop.getProperty("payment_type"), "Test Step - 10 - Selected Payment type is not displayed on phone order page payment section");
            delayWithGivenTime(1000);
            phoneorder.EnterCashAmount();
            delayWithGivenTime(1000);
            phoneorder.SelectCashRegistry_On_CashPaymentType(cashregistry);
            phoneorder.Select_WireOut_PaymentMethod(prop.getProperty("wireout_payment_method"));
            phoneorder.Enter_WireoutFlorist(prop.getProperty("wireout_choose_florist"));

            wireout_amount = phoneorder.get_Amount_wireout_paymentsection();
            phoneorder.set_Amount_wireout_paymentsection();
            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(2000);
            getDriver().switchTo().activeElement();
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
            Ordered_Time = get_AST_DateAndTime();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 13 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();
            customerName = orderconfirmationpage.getCustomerFname() + " " + orderconfirmationpage.getCustomerLname();

            // Test Step - 14
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 14 - Dashboard order page is not displayed");

            // Test Step - 15
            delayWithGivenTime(2000);
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.Validate_PhoneOrder_WireOut_InvoiceInHousePayment(), "Test Step - 15 - In order summary page, Respective wire out Phone order invoice in house payment order is not displayed");        //https://hanafloralpos3.com/Dashboard/Order/Validate_PhoneOrder_InvoiceInHousePayment();
            delayWithGivenTime(2000);
            dashboardorder.click_Status_Cell_On_AllOrdersPage(invoiceNumber);

            softassert.assertEquals(dashboardorder.getRecipientName_OnDeliveryPopup(), RecipientFullName, "Test Step 15 - Recipient name is not displayed on delivery popup");
            softassert.assertEquals(dashboardorder.getRecipientPhoneNum_OnDeliveryPopup(), recipientPhoneNumber, "Test Step 15 - Recipient phone number is not displayed on delivery popup");
            softassert.assertEquals(dashboardorder.getRecipientAddress_OnDeliveryPopup(), "1841 W Linda Ln 4840 Bergman Rd Robertsville MO 63072 US", "Test Step 15 - Recipient address is not displayed on delivery popup");
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.getCustAndcompyNameOnDeliveryPopup(), customerfullname, "Test Step - 15 - customer and company name on delivery popup is not matched");
            System.out.println(dashboardorder.getCustAndcompyNameOnDeliveryPopup() + " - Newly registered customer name");

            if (dashboardorder.getCustAddressOnDeliveryPopup().equals("2715 35th Ave Ct")) {
                softassert.assertEquals(dashboardorder.getCustAddressOnDeliveryPopup(), "2715 35th Ave Ct", "Test Step - 15 -customer address on delivery popup is not matched");
            } else if (dashboardorder.getCustAddressOnDeliveryPopup().contains("2715 N 35th Ave")) {
                softassert.assertEquals(dashboardorder.getCustAddressOnDeliveryPopup(), "2715 N 35th Ave", "Test Step - 15 -customer address on delivery popup is not matched");
            }

            softassert.assertTrue(dashboardorder.is_Wireout_Message_Header_Displayed(), "Wire Out Header is not displayed on Order details popup");
            softassert.assertTrue(dashboardorder.is_WireOut_Information_Displayed(), "Wire Out Information sectionis not displayed on Order details popup");
            softassert.assertEquals(dashboardorder.get_Wireout_Filling_Shop(), "FSNHQ", "Wire Out Filling Shop is not displayed on Invoice Order details popup");
            softassert.assertEquals(dashboardorder.get_Wireout_Filling_Shopname(), "FSN Headquarters", "Wire Out Filling Shop Name is not displayed on Invoice Order details popup");
            softassert.assertEquals(dashboardorder.get_Wireout_Wire_Method(), "FSN", "Wire Out Wire Method is not displayed on Invoice Order details popup");
            softassert.assertEquals(dashboardorder.get_Wireout_Wire_Amount(), wireout_amount, "Wire Out Wire Amount is not displayed on Invoice Order details popup");

            softassert.assertEquals(dashboardorder.get_Activities_Tab_Delivery_Confirmation_Header(), "SEND WIRE OUT", "Test Step - 15 - Delivery Confirmation header is not displayed on delivery popup");
            softassert.assertEquals(dashboardorder.get_Activities_Section_Full_Message(), "SEND WIRE OUT\n" +
                    "" + Ordered_Time + "\n" +
                    "Delivery Time: Exactly At 03:37 PM. New Price $" + wireout_amount + "\n" +
                    "Filling Florist: FSN Headquarters, FSNHQ\n" +
                    "Wireout Method: FSN", "Test Step - 15 - Activities Section Send Wire Out full message is not displayed on delivery popup");


            softassert.assertEquals(dashboardorder.getCustPhoneNumOnDeliveryPopup(), customerPhoneNumber, "Test Step - 15 -customer phone number on delivery popup is not matched");
            logger.info("User verified displayed customer type, customer name, company name, Address, Phone number & email are matched");
            delayWithGivenTime(2000);

            softassert.assertEquals(dashboardorder.Verify_OrderType_Displayed_on_DeliveryPopup(), "Wire Out Phone Order", "Test Step - 16 - Proper Order type is not displayed on delivery popup");
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
            delayWithGivenTime(1000);
            softassert.assertEquals(customerpage.VerifyPhoneNumberOnCustTable(), customerPhoneNumber, "Test Step - 17 -Phone number on customer table is not matched");
            logger.info("User verify that phone number on customer table");

            if (customerpage.VerifyAddressOnCustTable().equals("2715 35th Ave")) {
                softassert.assertEquals(customerpage.VerifyAddressOnCustTable(), "2715 35th Ave", "Test Step - 17 - Address on customer table is not matched");
                logger.info("User verify that address on customer table");
            } else if (customerpage.VerifyAddressOnCustTable().equals("2715 35th Ave Ct")) {
                softassert.assertEquals(customerpage.VerifyAddressOnCustTable(), "2715 35th Ave Ct", "Test Step - 17 - Address on customer table is not matched");
                logger.info("User verify that address on customer table");
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
            softassert.assertEquals(customerpage.getCustDetailsFirstNameTextBox(), custFirstName, "Test Step - 19 - Added on first name field are not properly displayed");
            logger.info("User verify the first name field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsLastNameTextBox(), custLastName, "Test Step - 19 - Added on first name field are not properly displayed");
            logger.info("User verify the last name field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsPhoneNumberTextbox(), customerPhoneNumber, "Test Step - 19 - Added on phone number field are not properly displayed");
            logger.info("User verify the phone number field entered data is displayed");

            if (customerpage.getCustDetailsAddress1TextBox().equals("2715 35th Ave")) {
                softassert.assertEquals(customerpage.getCustDetailsAddress1TextBox(), "2715 35th Ave", "Test Step - 19 - Added address 1 field is not properly displayed");
                logger.info("User verify that address 1 field entered data is displayed");
            } else if (customerpage.getCustDetailsAddress1TextBox().equals("2715 35th Ave Ct")) {
                softassert.assertEquals(customerpage.getCustDetailsAddress1TextBox(), "2715 35th Ave Ct", "Test Step - 19 - Added address 1 field is not properly displayed");
                logger.info("User verify that address 1 field entered data is displayed");
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