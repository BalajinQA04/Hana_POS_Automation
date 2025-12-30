package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Delivery_Type;

import java.io.IOException;
import java.util.Locale;

import com.github.javafaker.Faker;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CustomerPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.pageObjects.Order_Confirmation_Page;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T306_OrderEntryPage_Delivery_CustomerSection_Edit_Customer_CustomerDetails_ProfileUpdate_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private CustomerPage customerpage;
    String customerName;
    public static final String dataSheetName = "Hana_T98";

    Faker usFaker = new Faker(new java.util.Locale("en-US"));
    Faker canadaFaker = new Faker(new java.util.Locale("en-CA"));
    // Generate random US customer details
    String custFirstName = usFaker.name().firstName();
    String custLastName = usFaker.name().lastName();
    String customerfullname = custFirstName + " " + custLastName;
    String customer_email = usFaker.internet().emailAddress();
    String company_name = Generate_CompanyName();
    String customer_PhoneNumber = GenerateUsPhoneNumber();
    String alternate_PhoneNumber = GenerateCAPhoneNumber();
    String usStreetAddress = usFaker.address().streetAddress();
    String usCity = usFaker.address().city();
    String usState = usFaker.address().state();
    String usZipCode = usFaker.address().zipCode();
    String usFullAddress = usStreetAddress + ", " + usCity + ", " + usState + " " + usZipCode;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Phone Order Module - Delivery Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T306_OrderEntryPage_Delivery_CustomerSection_Edit_Customer_CustomerDetails_ProfileUpdate_Functionality_Test(String salesperson, String customername, String companyname, String firstname, String lastname, String emailid, String address1, String zipcode, String country, String city, String phonenumber,
                                                                                                                                          String altphonenumber, String recifirstname, String recilastname, String reciaddress1, String reciaddress2, String recizipcode, String recicountry, String reciphone, String recilocation, String occasion,
                                                                                                                                          String searchandselectitemcode, String itemdescription, String paymenttype, String cashregistry) {
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T306_OrderEntryPage_Delivery_CustomerSection_Edit_Customer_CustomerDetails_ProfileUpdate_Functionality_Test  ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana dashboard page is not displayed");
            logger.info("User navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3 : Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3 : Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickdeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_OnDelivery_TypeOnPhoneOrderPage(), "#676a6c", "Test Step - 5 - Delivery type is not highlighted in blue color");

            //Test Step - 6
            softassert.assertTrue(phoneorder.VerifyCustSectionAppears(), "Test Step - 6 - Customer section is not displayed on phone order page");

            // Test Step - 7
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            delayWithGivenTime(2000);
            phoneorder.SearchAndSelectCustomerOnCust_Section(customername);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), "Bertram", "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), "Maggio", "Test Step - 6 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), "Bertram Maggio LLC", "Test Step - 6 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), "bmllc@gmail.com", "Test Step - 6 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "2715 35th Ave Ct", "Test Step - 6 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "125 Carter Ln", "Test Step - 6 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "80634", "Test Step - 6 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "Greeley", "Test Step - 6 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), "918-617-8507", "Test Step - 6 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), "989-878-9899", "Test Step - 6 - Alt phone number is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 8
            softassert.assertTrue(phoneorder.Verify_CustEditIcon_Appears(), "Test Step - 8 - Customer clear button is not displayed on phone order page");

            // Test Step - 9
            phoneorder.Click_CustEditIcon();
            softassert.assertTrue(phoneorder.Verify_CustomerDetailsPopupAppears(), "Test Step - 9 - Customer details popup on is not displayed on phone order page");

            // Test Step - 10
            phoneorder.Click_ProfileTab_OnCustDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertFalse(phoneorder.Verify_CustomerDetails_Popup_ProfileTab(), "Test Step - 10 -profile tab is not displayed on Customer details popup on is not displayed on phone order page");

            // Test Step - 11
            softassert.assertFalse(phoneorder.Verify_CustomerIDField_Disabled(), "Test Step - 11 -Customer Id field is not disabled on Customer details popup on is not displayed on phone order page");

            // Test Step - 12
            phoneorder.Enter_CompanyName_OnCustDetailsPopup(company_name);
            phoneorder.Enter_FirstName_OnCustDetailsPopup(custFirstName);
            phoneorder.Enter_LastName_OnCustDetailsPopup(custLastName);
            phoneorder.Enter_Email_OnCustDetailsPopup(customer_email);
            phoneorder.Enter_Address1_OnCustDetailsPopup(usStreetAddress);
            phoneorder.Enter_ZipCode_OnCustDetailsPopup(usZipCode);
            phoneorder.Enter_Country_OnCustDetailsPopup("USA");
            phoneorder.Enter_City_OnCustDetailsPopup(usCity);
            phoneorder.Enter_PhoneNumber_OnCustDetailsPopup(customer_PhoneNumber);
            phoneorder.Enter_AltPhoneNumber_OnCustDetailsPopup(alternate_PhoneNumber);

            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_CompanyName_OnCustDetailsPopup(), company_name, "Test Step - 12 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_FirstName_OnCustDetailsPopup(), custFirstName, "Test Step - 12 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_LastName_OnCustDetailsPopup(), custLastName, "Test Step - 12 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Email_OnCustDetailsPopup(), customer_email, "Test Step - 12 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Address1_OnCustDetailsPopup(), usStreetAddress, "Test Step - 12 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Country_OnCustDetailsPopup(), "USA", "Test Step - 12 - Country is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_ZipCode_OnCustDetailsPopup(), usZipCode, "Test Step - 12 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_City_OnCustDetailsPopup(), usCity, "Test Step - 12 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_PhoneNumber_OnCustDetailsPopup(), customer_PhoneNumber, "Test Step - 12 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_AltPhoneNumber_OnCustDetailsPopup(), alternate_PhoneNumber, "Test Step - 12 - Alt phone number is not displayed on phone order page");

            // Test Step - 13
            phoneorder.Click_LateFeeSetting_OnCustDetailsPopup();
            delayWithGivenTime(1000);
            //  softassert.assertTrue(phoneorder.Verify_LateFeeSetting_Enabled_OnCustDetailsPopup(), "Test Step - 13 - late fee toogle button is not enabled");
            delayWithGivenTime(1000);
            phoneorder.Click_LateFeeSetting_OnCustDetailsPopup();

            // Test Step - 14
            phoneorder.Click_UpdateBtn_OnCustDetailsPopup();
            softassert.assertTrue(phoneorder.verifySuccessToastMessageAppears(), "Test Step - 14 - success message is not displayed");
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Customer details updated successfully", "Test step - 14 - success message displayed text is not matched");

            // Test Step - 15
            delayWithGivenTime(2000);
            phoneorder.Click_Customer_DetailsPopupCloseBtn();

            delayWithGivenTime(3000);

            // Test Step - 16
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), custFirstName, "Test Step - 16 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), custLastName, "Test Step - 16 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage().equalsIgnoreCase(company_name), true, "Test Step - 16 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), customer_email, "Test Step - 16 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), usStreetAddress, "Test Step - 16 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "125 Carter Ln", "Test Step - 16 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), usZipCode, "Test Step - 16 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), usCity, "Test Step - 16 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), customer_PhoneNumber, "Test Step - 16 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), alternate_PhoneNumber, "Test Step - 16 - Alt phone number is not displayed on phone order page");

            // Test Step -17
            delayWithGivenTime(3000);
            Faker faker = new Faker(new Locale("en-US"));
            String recifname1;
            String recilname2;
            String reci_phone_number1;
            String reci_phone_number2;
            String floor_number;

            recifname1 = faker.name().firstName();
            recilname2 = faker.name().lastName();
            phoneorder.EnterReciFirstName(recifname1);
            phoneorder.EnterReciLastName(recilname2);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname1, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname2, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");
            String reci_full_address1 = "160 E 5th St, Union, WA 98592-9615";
            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(reci_full_address1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "160 E 5th St", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "98592", "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Union", "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "WA", "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.EnterReciAddress2(reciaddress2);
            phoneorder.SelectReciCountry(recicountry);
            reci_phone_number1 = faker.numerify("###-###-####");
            phoneorder.EnterReciPhone(reci_phone_number1);
            delayWithGivenTime(1000);
            reci_phone_number2 = faker.numerify("###-###-####");
            floor_number = faker.address().buildingNumber();
            phoneorder.EnterRecipientPhone2OnPhoneOrderPage(reci_phone_number2);
            delayWithGivenTime(1000);
            phoneorder.Enter_FloorApt_On_RecipientSection(floor_number);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recilocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), reci_phone_number1, "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), recilocation, "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");

            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(12, 59);
            phoneorder.Select_DeliveryOnTime_Dropdown("Around");
            phoneorder.Select_Zone_OnRecipientSection("Automation Zone");
            delayWithGivenTime(1000);

            // Test Step - 18
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 18 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 18 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 19
            softassert.assertTrue(phoneorder.verify_ProductSuggestions_Appears(searchandselectitemcode), "Test Step - 19 - In Item code row 1 on product section autosuggestions are not displayed");
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(searchandselectitemcode, itemdescription);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), searchandselectitemcode, "Test Step - 19 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), itemdescription, "Test Step - 19 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "159.00", "Test Step - 19 - Item price is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 19 - Item quantity is not displayed on phone order page product details section");
            delayWithGivenTime(2000);

            // Test Step - 20
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), prop.getProperty("payment_type"), "Test Step - 20 - Selected payment type as " + prop.getProperty("payment_type") + " is not displayed");

            // Test Step - 20
            delayWithGivenTime(1000);
            phoneorder.Enter_CashPaymentType_Amount();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_Entered_CashPaymentType_Amount(), phoneorder.get_Entered_CashPaymentType_Amount(), "Test Step - 20 - Entered more than cash payment amount is not allowed on the payment section cash type pay amount field");

            phoneorder.SelectCashRegistry_On_CashPaymentType(prop.getProperty("payment_cash_registry"));

            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.Verify_Change_GivenBackTo_Customer_amount_IsDisplayed(), "100.00", "Test Step - 20 - Change given back to customer amount label is not displayed under the cash payment type select registered dropdown");
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_Selected_CashRegistry(), prop.getProperty("payment_cash_registry"), "Test Step - 20 - Selected cash payment cash registery " + prop.getProperty("payment_cash_registry") + " is not displayed");

            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 20 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 21
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);

            // Test Step - 22
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 22 - Order confirmation page is not displayed");
            customerName = orderconfirmationpage.getCustomerFname() + " " + orderconfirmationpage.getCustomerLname();
            softassert.assertEquals(orderconfirmationpage.getCustomerFname(), custFirstName, "Test Step - 22 - first name is not matched");
            softassert.assertEquals(orderconfirmationpage.getCustomerLname(), custLastName, "Test Step - 22 - Last name is not matched");
            softassert.assertEquals(orderconfirmationpage.getCustomerPhone1(), customer_PhoneNumber, "Test Step - 22 - phone number 1 is not matched");
            softassert.assertEquals(orderconfirmationpage.getCustomerPhone2(), "/ " + alternate_PhoneNumber, "Test Step - 22 - phone number 2 is not matched");
            softassert.assertEquals(orderconfirmationpage.getCustomerAddress(), usStreetAddress, "Test Step - 22 - address 1 is not matched");
            softassert.assertEquals(orderconfirmationpage.getCustomerCity(), usCity, "Test Step - 22 - city is not matched");
            // softassert.assertEquals(orderconfirmationpage.getCustomerState(), usState, "Test Step - 22 - state is not matched");
            softassert.assertEquals(orderconfirmationpage.getCustomerZip(), usZipCode, "Test Step - 22 - zip is not matched");

            // Test Step - 23
            delayWithGivenTime(3000);
            dashboard.ClickCustomersMenu();
            customerpage = new CustomerPage();
            delayWithGivenTime(5000);
            softassert.assertTrue(customerpage.VerifyCustomerMenuPage(), "Test Step - 23 - customer menu page heading is not displayed");

            // Test Step - 24
            delayWithGivenTime(4000);
            customerpage.Enter_CustomerName_searchbox_OnCustTable(customerName);

            delayWithGivenTime(4000);
            softassert.assertEquals(customerpage.VerifyCompanyNameOnCustTable(), company_name, "Test Step 24 - Company Name on customer table is not matched");
            logger.info("User verify that company name on customer table");
            softassert.assertEquals(customerpage.VerifyPhoneNumberOnCustTable(), customer_PhoneNumber, "Test Step - 24 - Phone number on customer table is not matched");
            logger.info("User verify that phone number on customer table");
            softassert.assertEquals(customerpage.VerifyAddressOnCustTable(), usStreetAddress, "Test Step - 24 - Address on customer table is not matched");
            logger.info("User verify that address on customer table");
            // softassert.assertEquals(customerpage.VerifyCityStateZipCodeOnCustTable(), "San Diego CA 92103", "Test Step - 24 - City, State & Zipcode on customer table is not matched");
            logger.info("User verify that city, state and zipcode on customer table");

            // Test Step - 25
            customerpage.ClickCustomerTableRow1();
            logger.info("User clicks the displayed customer in the table ");
            delayWithGivenTime(2000);
            softassert.assertTrue(customerpage.VerifyCustomerDetailsPopup(), "Customer details pop up is not displayed");
            logger.info("User verify that customer details popup is displayed");
            delayWithGivenTime(2000);
            softassert.assertEquals(customerpage.getCustDetailsCompanyNameTextBox(), company_name, " Test - 25 - Added on company name field are not properly displayed");
            logger.info("User verified that entered company name entered data is displayed on add new customer popup");
            softassert.assertEquals(customerpage.getCustDetailsFirstNameTextBox(), custFirstName, "Test Step - 25 - on first name field are not properly displayed");
            logger.info("User verify the first name field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsLastNameTextBox(), custLastName, "Test Step - 25 - on last name field are not properly displayed");
            logger.info("User verify the last name field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsPhoneNumberTextbox(), customer_PhoneNumber, "Test Step - 25 - on phone number field are not properly displayed");
            logger.info("User verify the phone number field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsAltPhoneNumberTextBox(), alternate_PhoneNumber, "Test Step - 25 - on alternative phone number field are not properly displayed");
            logger.info("User verify the alternative phone number field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsAddress1TextBox(), usStreetAddress, "Test Step - 25 - address 1 field is not properly displayed");
            logger.info("User verify that address 1 field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsZipCodeTextBox(), usZipCode, "Test Step - 25 - zip code field in customer details popup is not displayed");
            logger.info("User verify that zipcode field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsCityTextBox(), usCity, "Test Step - 25 - city field in customer details popup is not displayed");
            logger.info("User verify that city field entered data is displayed");
            //   softassert.assertEquals(customerpage.getCustDetailsStateTextBox(), usState, "Test Step - 25 - state field in customer details popup is not displayed");
            logger.info("User verify that state field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsEmailTextBox(), customer_email, "Test Step - 25 - email id field in customer details popup is not displayed");
            logger.info("User verify that email id field entered data is displayed");
            //   softassert.assertTrue(customerpage.ValidateCustDetailsLateFeeSettingToogle(), "Test Step - 25 - Late Fee Setting toogle in customer details popup is enabled");
            logger.info("User verify that enable loyalty toogle is disabled");
            customerpage.ClickCustomerDetailsPopupCloseIcon();
            logger.info("User clicks on customer details popup close icon");

            // ====================Update previous customer details================================//
            delayWithGivenTime(2000);
            dashboard.ClickOnHomeIcon();

            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.ClickdeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_OnDelivery_TypeOnPhoneOrderPage(), "#676a6c", "Test Step - 5 - Delivery type is not highlighted in blue color");

            //Test Step - 6
            softassert.assertTrue(phoneorder.VerifyCustSectionAppears(), "Test Step - 6 - Customer section is not displayed on phone order page");

            // Test Step - 7
            delayWithGivenTime(2000);
            phoneorder.SearchAndSelectCustomerOnCust_Section(custFirstName + " " + custLastName);
            delayWithGivenTime(2000);
            phoneorder.Click_CustEditIcon();
            delayWithGivenTime(2000);
            phoneorder.Enter_CompanyName_OnCustDetailsPopup("Bertram Maggio LLC");
            phoneorder.Enter_FirstName_OnCustDetailsPopup("Bertram");
            phoneorder.Enter_LastName_OnCustDetailsPopup("Maggio");
            phoneorder.Enter_Email_OnCustDetailsPopup("bmllc@gmail.com");
            phoneorder.Enter_Address1_OnCustDetailsPopup("2715 35th Ave Ct");
            phoneorder.Enter_ZipCode_OnCustDetailsPopup("80634");
            phoneorder.Enter_Country_OnCustDetailsPopup("United States");
            phoneorder.Enter_City_OnCustDetailsPopup("Greeley");
            phoneorder.Enter_PhoneNumber_OnCustDetailsPopup("918-617-8507");
            phoneorder.Enter_AltPhoneNumber_OnCustDetailsPopup("989-878-9899");

            phoneorder.Click_UpdateBtn_OnCustDetailsPopup();
            softassert.assertTrue(phoneorder.verifySuccessToastMessageAppears(), "Test Step - 14 - success message is not displayed");
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Customer details updated successfully", "Test step - 14 - success message displayed text is not matched");
            delayWithGivenTime(2000);
            phoneorder.Click_Customer_DetailsPopupCloseBtn();

            delayWithGivenTime(2000);
            phoneorder.SearchAndSelectCustomerOnCust_Section(customername);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), "Bertram", "Test Step - 6 - First name is not displayed properly on phone order page - after update customer details");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), "Maggio", "Test Step - 6 - Last name is not displayed on phone order page - after update customer details");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), "Bertram Maggio LLC", "Test Step - 6 - Company name is not displayed on phone order page - after update customer details");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), "bmllc@gmail.com", "Test Step - 6 - email id is not displayed on phone order page - after update customer details");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "2715 35th Ave Ct", "Test Step - 6 - address 1 is not displayed on phone order page - after update customer details");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "125 Carter Ln", "Test Step - 6 - Address 2 is not displayed on phone order page - after update customer details");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "80634", "Test Step - 6 - Zipcode is not displayed on phone order page - after update customer details");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "Greeley", "Test Step - 6 - city is not displayed on phone order page - after update customer details");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), "918-617-8507", "Test Step - 6 - phone number 1 is not displayed on phone order page - after update customer details");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), "989-878-9899", "Test Step - 6 - Alt phone number is not displayed on phone order page - after update customer details");
            delayWithGivenTime(2000);

        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}