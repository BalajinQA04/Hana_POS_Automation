package com.hanapos.testcases.CustomerModule_TestCases;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.Locale;

public class Hana_T1231_Customer_Delete_CustomerDetails_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CustomerPage customerpage = new CustomerPage();
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    String invoice;
    public static LoggerUtil logger_Util;
    CustomSoftAssert softassert = new CustomSoftAssert();

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
    @Description("Hana_T :- Delete Customer Details Functionality Test")
    @Epic("Customers Module")
    @Test(groups = {"Regression", "Sanity", "Smoke"})
    public void Validate_Hana_T1231_Customer_Delete_CustomerDetails_Functionality_Test() {
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();

        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            delayWithGivenTime(2000);
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana dashboard Page does not navigated to hana dashboard page");

            dashboard.click_Customer_Menu();
            customerpage = new CustomerPage();
            softassert.assertTrue(customerpage.VerifyCustomerMenuPage(), "Test Step - 3 - customer menu page is not displayed");

            // Test Step - 3
            customerpage.Select_ShopName_On_AddNewCustomer_Popup(prop.getProperty("shopname"));
            softassert.assertEquals(customerpage.get_Selected_ShopName_On_Customer_Page(), prop.getProperty("shopname"), "Test Step - 3 : Shop name is not matching with expected shop name as " + prop.getProperty("shopname"));

            customerpage.click_New_Customer_Button();
            delayWithGivenTime(2000);
            softassert.assertEquals(customerpage.verify_Add_New_Customer_Popup_IsDisplayed(), true, "Test Step - 3 : Add New Customer Popup is not displayed");

            customerpage.select_ShopName_On_AddNewCustomer_Popup(prop.getProperty("shopname"));
            softassert.assertEquals(customerpage.get_Selected_ShopName_On_AddNewCustomer_Popup(), prop.getProperty("shopname"), "Test Step - 3 : Add New Customer Popup selected shop name is not matching with expected shop name as " + prop.getProperty("shopname"));

            // Test Step - 4
            Faker usFaker = new Faker(new Locale("en-US"));
            Faker canadaFaker = new Faker(new Locale("en-CA"));
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
            delayWithGivenTime(500);

            PressTabKey();

            customerpage.enter_Address2_On_AddNewCustomer_Popup(canada_address);
            softassert.assertEquals(customerpage.get_entered_Address2_On_AddNewCustomer_Popup(), canada_address, "Test Step  4 - Entered Address 2 is not displayed correctly");

            customerpage.enter_PhoneNumber_On_AddNewCustomer_Popup(customerPhoneNumber);
            softassert.assertEquals(customerpage.get_entered_PhoneNumber_On_AddNewCustomer_Popup(), customerPhoneNumber, "Test Step - 4 : Entered Phone Number is not displayed");

            customerpage.enter_AltPhoneNumber_On_AddNewCustomer_Popup(altPhoneNumber);
            softassert.assertEquals(customerpage.get_entered_AltPhoneNumber_On_AddNewCustomer_Popup(), altPhoneNumber, "Test Step - 4 : Entered Alternate Alternate Phone Number is not displayed");

            customerpage.enter_Email_Id_On_AddNewCustomer_Popup(customerEmail);
            softassert.assertEquals(customerpage.get_Entered_EmailID_textbox_on_addnewcustomer_popup(), customerEmail, "Test Step - 4 : Entered Email ID is not displayed");
            delayWithGivenTime(500);

            customerpage.enter_ZipCode_On_AddNewCustomer_Popup(usZipCode);
            softassert.assertEquals(customerpage.get_entered_ZipCode_On_AddNewCustomer_Popup(), usZipCode, "Test Step - 4: Entered zipcode is not displayed ");
            delayWithGivenTime(500);

            customerpage.enter_City_On_AddNewCustomer_Popup(usCity);
            softassert.assertEquals(customerpage.get_entered_City_On_AddNewCustomer_Popup(), usCity, "Test Step - 4: Entered city is not displayed");
            delayWithGivenTime(500);

            customerpage.enter_State_On_AddNewCustomer_Popup(usState);
            softassert.assertEquals(customerpage.get_entered_State_On_AddNewCustomer_Popup(), usState, "Test Step - 4: Entered state is not displayed");
            delayWithGivenTime(500);

            customerpage.enter_Country_On_AddNewCustomer_Popup("United States");
            softassert.assertEquals(customerpage.get_entered_Country_On_AddNewCustomer_Popup(), "United States", "Test Step - 4: Entered country is not displayed");
            delayWithGivenTime(500);

            customerpage.select_CustomerType_AddNewCustomer_Popup("Corporate");
            softassert.assertEquals(customerpage.get_Selected_CustomerType_On_AddNewCustomer_Popup(), "Corporate", "Test Step - 4: Selected customer type is not displayed");
            delayWithGivenTime(500);

            customerpage.enter_Store_Credit_On_Add_New_Customer_Popup("5");
            softassert.assertEquals(customerpage.get_Entered_Store_credit_On_Add_New_Customer_Popup(), "5", "Entered Store credit is not displayed");
            delayWithGivenTime(500);

            customerpage.click_AddNewCustomer_Button();
            softassert.assertEquals(customerpage.verify_Toaster_Message_Text(), "New Customer added successfully", "Test Step - 5: New Customer added successfully - Success toaster message is not displayed");
            customerpage.click_Close_Icon_On_Success_Toaster_Message();

            // Test Step - 7
            delayWithGivenTime(3000);
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

            delayWithGivenTime(1000);
            customerpage.click_Customer_Close_Icon();
            delayWithGivenTime(2000);

            customerpage.click_ActionMenu_Icon_Row1_On_CustomerDetails_Tablegrid();
            delayWithGivenTime(1000);
            customerpage.click_Delete_Button_On_ActionMenu();
            delayWithGivenTime(1000);
            customerpage.click_No_Delete_Button_On_DeleteConfirmationPopup();

            delayWithGivenTime(1000);
            customerpage.click_ActionMenu_Icon_Row1_On_CustomerDetails_Tablegrid();
            delayWithGivenTime(1000);
            customerpage.click_Delete_Button_On_ActionMenu();
            delayWithGivenTime(1000);
            customerpage.click_Yes_Delete_Button_On_DeleteConfirmationPopup();
            softassert.assertEquals(customerpage.verify_Toaster_Message_Text(), "Customer deleted succesfully", "Test Step - 5: Customer deleted successfully - Success toaster message is not displayed");
            customerpage.click_Close_Icon_On_Success_Toaster_Message();

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
