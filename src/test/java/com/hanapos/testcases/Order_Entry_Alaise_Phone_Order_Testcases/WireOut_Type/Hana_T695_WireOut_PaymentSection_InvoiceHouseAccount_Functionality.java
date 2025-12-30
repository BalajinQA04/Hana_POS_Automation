package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireOut_Type;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;

public class Hana_T695_WireOut_PaymentSection_InvoiceHouseAccount_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    String invoiceNumber;
    public static final String dataSheetName = "Hana_T81";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    Faker faker = new Faker(new Locale("en-US"));
    String recifname1;
    String recilname2;
    String reci_full_address1;
    String reci_phone_number1;
    String reci_phone_number2;
    String floor_number;

    @Epic("Phone Order Module - Wire out Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T695_WireOut_PaymentSection_InvoiceHouseAccount_Functionality_Test(
            String salesperson, String customername, String recifname, String recilname, String reciaddress1, String reciaddress2, String recizip, String recicity,
            String recicountry, String reciphone, String recilocation, String occasion, String searchandselectitemcode, String paymenttype) {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting Validate_Hana_T695_WireOut_PaymentSection_InvoiceHouseAccount_Functionality_Test ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

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
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Click_WireOut_DeliveryType_OnPhoneOrderPage();
            //  delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_On_WireOut_PhoneOrderPage(), "#2f9bc8", "Test Step - 5 - Wire out Delivery type is not highlighted in blue color");

            // Test Step - 6
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            phoneorder.SearchAndSelectCustomerOnCust_Section(customername);
            //    delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), "Abish", "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), "David", "Test Step - 6 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), "Hana_Sisterchicks", "Test Step - 6 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), "hanaposqateam@gmail.com", "Test Step - 6 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "3402 Park Blvd", "Test Step - 6 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 6 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "92103", "Test Step - 6 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "San Diego", "Test Step - 6 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 6 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 6 - Alt phone number is not displayed on phone order page");

            // Test Step - 7
            recifname1 = faker.name().firstName();
            recilname2 = faker.name().lastName();
            reci_full_address1 = prop.getProperty("Full_Reci_Address1_1");
            phoneorder.EnterReciFirstName(recifname1);
            phoneorder.EnterReciLastName(recilname2);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname1, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname2, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(reci_full_address1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "1160 W 5th St", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63090", "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Washington", "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");
            delayWithGivenTime(2000);
            phoneorder.SelectReciCountry(prop.getProperty("recipient_country1"));
            reci_phone_number1 = faker.numerify("###-###-####");
            delayWithGivenTime(2000);

            phoneorder.EnterReciPhone(reci_phone_number1);
            delayWithGivenTime(1000);
            reci_phone_number2 = faker.numerify("###-###-####");
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
            phoneorder.Enter_DeliveryTime_OnRecipientSection(4, 00);
            phoneorder.Select_DeliveryOnTime_Dropdown("Any");
            phoneorder.Select_Zone_OnRecipientSection("Automation Zone");
            delayWithGivenTime(1000);

            //Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode();
            //    delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");
            //   delayWithGivenTime(2000);

            // Test Step - 9
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(searchandselectitemcode, prop.getProperty("productfulldesc1"));
            //    delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), "rrd", "Test Step - 9 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), "Red Rose Deluxe", "Test Step - 9 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 9 - Item quantity is not displayed on phone order page product details section");

            if (phoneorder.getUnitPriceOnProdDetails() == "299.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            } else if (phoneorder.getUnitPriceOnProdDetails() == "309.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "309.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            }

            //   delayWithGivenTime(2000);

            // Test Step - 10

            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymenttype);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), "Invoice/House Account", "Test Step - 10 - Selected payment type is not displayed");

            // Test Step - 11
            softassert.assertTrue(phoneorder.Verify_PO_NumberField_IsDisplayed_On_InvoiceHouseAccount_PaymentSection(), "Test Step - 11 - Po Number field is not displayed on invoice house account payment section");
            softassert.assertTrue(phoneorder.Verify_Payment_TermsField_IsDisplayed_On_InvoiceHouseAccount_PaymentSection(), "Test Step - 11 - payment terms field is not displayed on invoice house account payment section");

            // Test Step - 12
            phoneorder.Select_WireOut_PaymentMethod(prop.getProperty("wireout_payment_method"));
            phoneorder.Enter_WireoutFlorist(prop.getProperty("wireout_choose_florist"));
            phoneorder.set_Amount_wireout_paymentsection();

            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 12 - Confirmation popup is not displayed on phone order page");
            //  delayWithGivenTime(2000);

            // Test Step - 13
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            //   delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 13 - Order confirmation page is not displayed");
            //  delayWithGivenTime(500);
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            // Test Step - 14
            //   delayWithGivenTime(1000);
            dashboard.ClickOrder();
            //   delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 14 - Dashboard order page is not displayed");

            // Test Step - 15
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            //  delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.Validate_PhoneOrder_Delivery_InvoiceNumber("Phone Order", "Wire Out", "Invoice/House Account"), "Test Step - 29 - In orders summary page Invoice/House Account payment type invoice number is not displayed for placed order");
            //  delayWithGivenTime(1000);
            dashboardorder.clickInvoiceNumber_On_TableGrid_AllOrdersPage(invoiceNumber);

            //Test Step - 16
            //   delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.getRecipientName_OnDeliveryPopup(), recifname1 + " " + recilname2, "Test Step 16 - Recipient name is not displayed on delivery popup");
            softassert.assertEquals(dashboardorder.getRecipientPhoneNum_OnDeliveryPopup(), reci_phone_number1 + " / " + reci_phone_number2, "Test Step 16 - Recipient phone number is not displayed on delivery popup");
            softassert.assertEquals(dashboardorder.getRecipientAddress_OnDeliveryPopup(), "1160 W 5th St (Apt/floor: "+floor_number+") Washington MO 63090 US", "Test Step 16 - Recipient address is not displayed on delivery popup");
            softassert.assertEquals(dashboardorder.getCustAndcompyNameOnDeliveryPopup(), "Hana_Sisterchicks | Abish David", "Test Step - 16 - customer and company name on delivery popup is not matched");
            softassert.assertEquals(dashboardorder.getCustAddressOnDeliveryPopup(), "3402 Park Blvd", "Test Step - 16 -customer address on delivery popup is not matched");
            softassert.assertEquals(dashboardorder.getCustPhoneNumOnDeliveryPopup(), "956-655-0756", "Test Step - 16 -customer phone number on delivery popup is not matched");
            softassert.assertEquals(dashboardorder.getCustEmailOnDeliveryPopup(), "hanaposqateam@gmail.com", "Test Step - 16 - customer phone number on delivery popup is not matched");
            logger.info("User verified displayed customer type, customer name, company name, Address, Phone number & email are matched");
            softassert.assertEquals(dashboardorder.Verify_OrderType_Displayed_on_DeliveryPopup(), "Wire Out Phone Order", "Test Step - 16 - Proper Order type is not displayed on delivery popup");

        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}
