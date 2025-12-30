package com.hanapos.testcases.CustomerModule_TestCases;

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

public class Hana_T1229_Customer_NewCustomer_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CustomerPage customerpage = new CustomerPage();
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    String invoice;
    public static final String dataSheetName = "Hana_T1229";
    public static LoggerUtil logger_Util;
    CustomSoftAssert softassert = new CustomSoftAssert();


    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    String invoiceNumber;
    Faker faker = new Faker(new Locale("en-US"));
    String recifname1;
    String recilname2;
    String reci_full_address1;
    String reci_phone_number1;
    String reci_phone_number2;
    String floor_number;

    @Severity(SeverityLevel.CRITICAL)
    @Owner("Balaji N")
    @Description("Hana_T1229 :- Create New Customer Functionality Test")
    @Epic("Customers Module")
    @Test(groups = {"Regression", "Sanity", "Smoke"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1229_Customer_NewCustomer_Functionality_Test(
            String username,
            String password,
            String shopname

    ) {
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();

        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            lp.EnterUserName(username);
            lp.EnterPassword(password);
            softassert.assertEquals(lp.get_entered_username(), username, "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), password, "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            delayWithGivenTime(2000);
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana dashboard Page does not navigated to hana dashboard page");

            dashboard.click_Customer_Menu();
            customerpage = new CustomerPage();
            softassert.assertTrue(customerpage.VerifyCustomerMenuPage(), "Test Step - 3 - customer menu page is not displayed");

            // Test Step - 3
            customerpage.Select_ShopName_On_AddNewCustomer_Popup(shopname);
            softassert.assertEquals(customerpage.get_Selected_ShopName_On_Customer_Page(), shopname, "Test Step - 3 : Shop name is not matching with expected shop name as " + shopname);

            customerpage.click_New_Customer_Button();
            delayWithGivenTime(2000);
            softassert.assertEquals(customerpage.verify_Add_New_Customer_Popup_IsDisplayed(), true, "Test Step - 3 : Add New Customer Popup is not displayed");

            customerpage.select_ShopName_On_AddNewCustomer_Popup(shopname);
            softassert.assertEquals(customerpage.get_Selected_ShopName_On_AddNewCustomer_Popup(), shopname, "Test Step - 3 : Add New Customer Popup selected shop name is not matching with expected shop name as " + shopname);

            // Test Step - 4
            Faker usFaker = new Faker(new java.util.Locale("en-US"));
            Faker canadaFaker = new Faker(new java.util.Locale("en-CA"));
            // Generate random US customer details
            String custFirstName = usFaker.name().firstName();
            String custLastName = usFaker.name().lastName();
            String customerfullname = custFirstName + " " + custLastName;

            String companyname = Generate_CompanyName();
            String customerPhoneNumber = GenerateUsPhoneNumber();
            String altPhoneNumber = GenerateCAPhoneNumber();
            String customerEmail = Generate_Random_EmailId();
            String usStreetAddress = usFaker.address().streetAddress();
            String usCity = usFaker.address().city();
            String usState = usFaker.address().state();
            String usZipCode = usFaker.address().zipCode();
            String usFullAddress = usStreetAddress + ", " + usCity + ", " + usState + " " + usZipCode;
            String canada_address = canadaFaker.address().fullAddress();
            String city_state_zipcode = usCity + " " + usState + " " + usZipCode;

            customerpage.enter_CompanyName_On_AddNewCustomer_Popup(companyname);
            softassert.assertEquals(customerpage.get_entered_CompanyName_On_AddNewCustomer_Popup(), companyname, "Test Step - 4 : Company name is not matching with expected company name as " + companyname);

            customerpage.enter_FirstName_On_AddNewCustomer_Popup(custFirstName);
            softassert.assertEquals(customerpage.get_entered_FirstName_On_AddNewCustomer_Popup(), custFirstName, "Test Step - 4: Entered first name is not displayed");

            customerpage.enter_LastName_On_AddNewCustomer_Popup(custLastName);
            softassert.assertEquals(customerpage.get_entered_LastName_On_AddNewCustomer_Popup(), custLastName, "Test Step - 4: Entered last name is not displayed");

            customerpage.enter_Address1_On_AddNewCustomer_Popup(usFullAddress);
            softassert.assertEquals(customerpage.get_entered_Address1_On_AddNewCustomer_Popup(), usFullAddress, "Test Step 4 - Entered Address1 is not displayed");
            delayWithGivenTime(2000);

            PressTabKey();

            customerpage.enter_Address2_On_AddNewCustomer_Popup(canada_address);
            softassert.assertEquals(customerpage.get_entered_Address2_On_AddNewCustomer_Popup(), canada_address, "Test Step  4 - Entered Address 2 is not displayed correctly");

            // customerpage.enter_ZipCode_On_AddNewCustomer_Popup(usZipCode);
            customerpage.enter_PhoneNumber_On_AddNewCustomer_Popup(customerPhoneNumber);
            softassert.assertEquals(customerpage.get_entered_PhoneNumber_On_AddNewCustomer_Popup(), customerPhoneNumber, "Test Step - 4 : Entered Phone Number is not displayed");

            customerpage.enter_AltPhoneNumber_On_AddNewCustomer_Popup(altPhoneNumber);
            softassert.assertEquals(customerpage.get_entered_AltPhoneNumber_On_AddNewCustomer_Popup(), altPhoneNumber, "Test Step - 4 : Entered Alternate Alternate Phone Number is not displayed");

            customerpage.enter_Email_Id_On_AddNewCustomer_Popup(customerEmail);
            softassert.assertEquals(customerpage.get_Entered_EmailID_textbox_on_addnewcustomer_popup(), customerEmail, "Test Step - 4 : Entered Email ID is not displayed");
            delayWithGivenTime(2000);

            customerpage.enter_ZipCode_On_AddNewCustomer_Popup(usZipCode);
            softassert.assertEquals(customerpage.get_entered_ZipCode_On_AddNewCustomer_Popup(), usZipCode, "Test Step - 4: Entered zipcode is not displayed ");
            delayWithGivenTime(2000);

            customerpage.enter_City_On_AddNewCustomer_Popup(usCity);
            softassert.assertEquals(customerpage.get_entered_City_On_AddNewCustomer_Popup(), usCity, "Test Step - 4: Entered city is not displayed");
            delayWithGivenTime(2000);

            customerpage.enter_State_On_AddNewCustomer_Popup(usState);
            softassert.assertEquals(customerpage.get_entered_State_On_AddNewCustomer_Popup(), usState, "Test Step - 4: Entered state is not displayed");
            delayWithGivenTime(2000);

            customerpage.enter_Country_On_AddNewCustomer_Popup("United States");
            softassert.assertEquals(customerpage.get_entered_Country_On_AddNewCustomer_Popup(), "United States", "Test Step - 4: Entered country is not displayed");
            delayWithGivenTime(2000);

            customerpage.select_CustomerType_AddNewCustomer_Popup("Corporate");
            softassert.assertEquals(customerpage.get_Selected_CustomerType_On_AddNewCustomer_Popup(), "Corporate", "Test Step - 4: Selected customer type is not displayed");
            delayWithGivenTime(2000);

            customerpage.enter_Store_Credit_On_Add_New_Customer_Popup("5");
            softassert.assertEquals(customerpage.get_Entered_Store_credit_On_Add_New_Customer_Popup(), "5", "Entered Store credit is not displayed");
            delayWithGivenTime(2000);

            customerpage.click_AddNewCustomer_Button();
            softassert.assertEquals(customerpage.verify_Toaster_Message_Text(), "New Customer added successfully", "Test Step - 5: New Customer added successfully - Success toaster message is not displayed");
            customerpage.click_Close_Icon_On_Success_Toaster_Message();
            // Test Step - 7
            customerpage.search_Customer_On_Customer_Search_TextBox(custFirstName + " " + custLastName);
            delayWithGivenTime(1000);
            softassert.assertEquals(customerpage.VerifyCompanyNameOnCustTable(), companyname, "Test Step - 8 - Company Name on customer table is not matched");
            softassert.assertEquals(customerpage.VerifyPhoneNumberOnCustTable(), customerPhoneNumber, "Test Step - 8 - Phone number on customer table is not matched");
            softassert.assertEquals(customerpage.VerifyAddressOnCustTable(), usFullAddress, "Test Step - 8 - Address on customer table is not matched");
            softassert.assertEquals(customerpage.VerifyCityStateZipCodeOnCustTable(), city_state_zipcode, "Test Step - 8 - City, State & Zipcode on customer table is not matched");
            delayWithGivenTime(1000);

            customerpage.ClickCustomerId_LastRow_OnCustTable();
            delayWithGivenTime(1000);
            softassert.assertTrue(customerpage.VerifyCustomerDetailsPopup(), "Customer details pop up is not displayed");
            logger.info("User verify that customer details popup is displayed");

            delayWithGivenTime(2000);
            softassert.assertEquals(customerpage.getCustDetailsCompanyNameTextBox(), companyname, "Test Step - 8 - Company Name on customer details popup is not matched as expected");

            softassert.assertEquals(customerpage.getCustDetailsFirstNameTextBox(), custFirstName, "Added on first name field are not properly displayed");
            softassert.assertEquals(customerpage.getCustDetailsLastNameTextBox(), custLastName, "Added on first name field are not properly displayed");
            softassert.assertEquals(customerpage.getCustDetailsPhoneNumberTextbox(), customerPhoneNumber, "Added on phone number field are not properly displayed");
            softassert.assertEquals(customerpage.getCustDetailsAltPhoneNumberTextBox(), altPhoneNumber, "Added on alternative phone number field are not properly displayed");
            softassert.assertEquals(customerpage.getCustDetailsAddress1TextBox(), usFullAddress, "Added address 1 field is not properly displayed");
            softassert.assertEquals(customerpage.getCustDetailsAddress2TextBox(), canada_address, "Added address 2 field is not properly displayed");
            softassert.assertEquals(customerpage.getCustDetailsZipCodeTextBox(), usZipCode, "Added zip code is not displayed");
            softassert.assertEquals(customerpage.getCustDetailsCityTextBox(), usCity, "Test Step - 8 - Added city field is not displayed");

            softassert.assertEquals(customerpage.getCustDetailsStateTextBox(), usState, "Added state field is not displayed");
            softassert.assertEquals(customerpage.getCustDetailsCountryTextBox(), "United States", "Added country field is not displayed");
            softassert.assertEquals(customerpage.getCustDetailsEmailTextBox(), customerEmail, "Added email id field is not displayed");
            softassert.assertEquals(customerpage.getDisplayedCustDetailsCustomerType(), "Corporate", "Added customer type dropdown field is not displayed");
            softassert.assertEquals(customerpage.getCustDetailsStoreCreditTextBox(), "5", "Test Step - 8 - Added credit card field is not displayed");
            delayWithGivenTime(2000);
            customerpage.click_Customer_Close_Icon();
            delayWithGivenTime(3000);
            dashboard.ClickOnHomeIcon();
            delayWithGivenTime(3000);
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("shopname"));
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("shopname"), "Test Step - 4 : Shop name is not matched with selected shop name");

            //Test Step - 5
            cashandcarry.SelectClerkName(prop.getProperty("clerkname"));
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("clerkname"), "Test Step - 5 : Clerk name is not matched with selected clerk name");

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 6 : Employee name is not matched with selected employee name");

            // Test Step - 7
            softassert.assertTrue(cashandcarry.IsPayButtonDisabled(), "Pay button is not disabled");

            // Test Step - 8
            cashandcarry.SearchAndSelectTheItemCode(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));
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
            cashandcarry.EnterCustomerName(custFirstName, customerfullname);
            softassert.assertEquals(cashandcarry.getDisplayedCustomerName(), customerfullname, "Test Step - 9 - Customer name is not matched with entered customer name");

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 10 : Selected tax type is not displayed");

            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 10 : Selected occasion is not displayed");
            delayWithGivenTime(1000);

            cashandcarry.ClickPayButton();
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 11: Cash And Carry payment page is not displayed");
            logger.info("User navigated to Cash And Carry payment page successfully");

            // Test Step - 12
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyCreditCardTabIsSelected(), "true", "Test Step - 12: By defaultCredit card tab section is not displayed");

            // Test Step - 13
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickCashTab();

            // Test Step - 15
            delayWithGivenTime(1000);
            cashandcarrypayment.EnterGivenAmountOnCashTab("100");
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarrypayment.getEnteredGivenAmountOnCashTab(), "100", "Test Step - 15: Entered given amount on the textbox field is not displayed");

            // Test Step - 16
            softassert.assertFalse(cashandcarrypayment.VerifyProcessPaymentButton(), "Test step 16 : process payment button is disabled");

            // Test Step - 17
            cashandcarrypayment.EnterGivenAmount();

            // Test Step - 18
            softassert.assertTrue(cashandcarrypayment.VerifyProcessPaymentButton(), "Test Step 17: process payment button is disabled");

            // Test Step - 19
            cashandcarrypayment.EnterGivenAmountOnCashTab("500");

            // Test Step - 20
            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 20: Success toaster message is not displayed");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step- 20: success toaster message is not displayed");

            if (cashandcarrypayment.getConfirmationPopup()) {
                softassert.assertTrue(cashandcarrypayment.VerifyOrderConfirmationPopup(), "Order confirmation popup is not displayed");
                softassert.assertTrue(cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo(), "Order confirmation message along with invoice number is not displayed");
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
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoice), "Cash", "Test STep - 20: Cash mode of pay is not displayed on orders page");

            delayWithGivenTime(2000);
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickdeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_OnDelivery_TypeOnPhoneOrderPage(), "#676a6c", "Test Step - 6 - Delivery type is not highlighted as blue color");

            // Test Step - 6
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            phoneorder.SearchAndSelectCustomerOnCust_Section(customerfullname);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), custFirstName, "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), custLastName, "Test Step - 6 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage().equalsIgnoreCase(companyname), true, "Test Step - 6 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), customerEmail, "Test Step - 6 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), usFullAddress, "Test Step - 6 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), canada_address, "Test Step - 6 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), usZipCode, "Test Step - 6 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), usCity, "Test Step - 6 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), customerPhoneNumber, "Test Step - 6 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), altPhoneNumber, "Test Step - 6 - Alt phone number is not displayed on phone order page");

            // Test Step - 7
            recifname1 = faker.name().firstName();
            recilname2 = faker.name().lastName();
            reci_full_address1 = "230 Green St, Augusta, MO 63332";

            phoneorder.EnterReciFirstName(recifname1);
            phoneorder.EnterReciLastName(recilname2);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname1, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname2, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(reci_full_address1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "230 Green St", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63332", "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Augusta", "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.EnterReciAddress2("275 Webster St, Augusta");
            phoneorder.SelectReciCountry(prop.getProperty("recipient_country1"));
            reci_phone_number1 = faker.numerify("###-###-####");
            phoneorder.EnterReciPhone(reci_phone_number1);
            delayWithGivenTime(1000);
            reci_phone_number2 = faker.phoneNumber().cellPhone();
            floor_number = faker.address().buildingNumber();
            phoneorder.EnterRecipientPhone2OnPhoneOrderPage(reci_phone_number2);
            delayWithGivenTime(1000);
            phoneorder.Enter_FloorApt_On_RecipientSection(floor_number);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(prop.getProperty("recipient_location1"));
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), reci_phone_number1, "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), prop.getProperty("recipient_location1"), "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");

            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(1, 50);
            phoneorder.Select_DeliveryOnTime_Dropdown("Around");
            phoneorder.Select_Zone_OnRecipientSection("Automation Zone");
            delayWithGivenTime(1000);

            //Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 9
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), "rrd", "Test Step - 9 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), "Red Rose Deluxe", "Test Step - 9 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 9 - Item quantity is not displayed on phone order page product details section");

            if (phoneorder.getUnitPriceOnProdDetails() == "299.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            }

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

            // Test Step - 14
            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 14 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 15
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 15 - Order confirmation page is not displayed");
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            // Test Step - 16
            softassert.assertEquals(orderconfirmationpage.get_PaymentType(), prop.getProperty("payment_type"), "Test Step - 16 - Payment type is not displayed on order confirmation page");

            // Test Step - 17
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);

            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 17 - Dashboard order page is not displayed");

            // Test Step - 18
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 18 - In orders summary page cash payment type invoice number is not displayed for placed order");

            //Test Step - 19
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoiceNumber), prop.getProperty("payment_type"), "Test Step - 19 - Mode of payment as " + prop.getProperty("payment_type") + " is not displayed");


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
