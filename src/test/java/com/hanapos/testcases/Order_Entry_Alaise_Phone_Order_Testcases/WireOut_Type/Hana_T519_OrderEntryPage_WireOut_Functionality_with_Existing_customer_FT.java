package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireOut_Type;

import java.io.IOException;
import java.util.Locale;

import com.github.javafaker.Faker;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.DashboardOrderPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.pageObjects.Order_Confirmation_Page;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T519_OrderEntryPage_WireOut_Functionality_with_Existing_customer_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    String invoiceNumber;
    String wireout_amount;
    String Ordered_Time;
    public static final String dataSheetName = "Hana_T519";
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    Faker faker = new Faker(new Locale("en-US"));
    String recifname1;
    String recilname2;
    String reci_phone_number1;
    String reci_phone_number2;
    String floor_number;

    @Epic("Phone Order Module - Wire out Type")
    @Test(enabled = true, groups = {"Regression", "Smoke", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T519_OrderEntryPage_WireOut_Functionality_with_Existing_customer_Functionality_Test(
            String salesperson, String customername, String recifname, String recilname, String reciaddress1, String reciaddress2, String recizip, String recicity,
            String recicountry, String reciphone, String recilocation, String occasion, String searchandselectitemcode, String itemdescription, String paymenttype) {
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Hana-Validate_Hana_T519_OrderEntryPage_WireOut_Functionality_with_Existing_customer_Functionality_Test  ****");
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
            phoneorder.SearchAndSelectCustomerOnCust_Section(customername);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), "Eagle", "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), "Electric", "Test Step - 6 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), "Eagle Electric LLC", "Test Step - 6 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), "hanaposqateam@gmail.com", "Test Step - 6 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "183 E Union Ridge Rd", "Test Step - 6 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "310 E Dalby Rd Unit 86", "Test Step - 6 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "98592", "Test Step - 6 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "Union", "Test Step - 6 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), "360-463-7411", "Test Step - 6 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), "360-490-7096", "Test Step - 6 - Alt phone number is not displayed on phone order page");

            // Test Step - 7
            recifname1 = faker.name().firstName();
            recilname2 = faker.name().lastName();
            phoneorder.EnterReciFirstName(recifname1);
            phoneorder.EnterReciLastName(recilname2);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname1, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname2, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            String reci_full_address1 = "2765LaSalleSt, St.Louis,MO63104‑1917";
            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(reci_full_address1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "2765 Lasalle St", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63104", "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "St. Louis", "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

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
            phoneorder.Enter_DeliveryTime_OnRecipientSection(13, 57);
            phoneorder.Select_DeliveryOnTime_Dropdown("Around");
            phoneorder.Select_Zone_OnRecipientSection("Automation Zone");
            delayWithGivenTime(1000);

            //Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
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
            phoneorder.Select_WireOut_PaymentMethod(prop.getProperty("wireout_payment_method"));
            phoneorder.Enter_WireoutFlorist(prop.getProperty("wireout_choose_florist"));
            delayWithGivenTime(2000);

            wireout_amount = phoneorder.get_Amount_wireout_paymentsection();
            phoneorder.set_Amount_wireout_paymentsection();

            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 10 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 11
            phoneorder.ClickCancelButton_On_ConfirmationPopup();

            // Test Step - 12
            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 12 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 13
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            Ordered_Time = get_AST_DateAndTime();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 13 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

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

            //Test Step - 16
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.getRecipientName_OnDeliveryPopup(), recifname1 + " " + recilname2, "Test Step 16 - Recipient name is not displayed on delivery popup");
            softassert.assertEquals(dashboardorder.getRecipientPhoneNum_OnDeliveryPopup(), reci_phone_number1 + " / " + reci_phone_number2, "Test Step 16 - Recipient phone number is not displayed on delivery popup");
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.getRecipientAddress_OnDeliveryPopup(), "2765 Lasalle St " + reciaddress2 + " (Apt/floor: " + floor_number + ") St. Louis MO 63104 US", "Test Step 16 - Recipient address is not displayed on delivery popup");
            softassert.assertEquals(dashboardorder.getCustAndcompyNameOnDeliveryPopup(), "Eagle Electric LLC | Eagle Electric", "Test Step - 16 - customer and company name on delivery popup is not matched");
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.getCustAddressOnDeliveryPopup(), "183 E Union Ridge Rd 310 E Dalby Rd Unit 86", "Test Step - 16 -customer address on delivery popup is not matched");
            softassert.assertEquals(dashboardorder.getCustPhoneNumOnDeliveryPopup(), "360-463-7411 x 1", "Test Step - 16 -customer phone number on delivery popup is not matched");
            softassert.assertEquals(dashboardorder.getCustEmailOnDeliveryPopup(), "hanaposqateam@gmail.com", "Test Step - 16 - customer phone number on delivery popup is not matched");
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.Verify_OrderType_Displayed_on_DeliveryPopup(), "Wire Out Phone Order", "Test Step - 16 - Proper Order type is not displayed on delivery popup");

            softassert.assertTrue(dashboardorder.is_Wireout_Message_Header_Displayed(), "Wire Out Header is not displayed on Order details popup");
            softassert.assertTrue(dashboardorder.is_WireOut_Information_Displayed(), "Wire Out Information sectionis not displayed on Order details popup");
            softassert.assertEquals(dashboardorder.get_Wireout_Filling_Shop(), "FSNHQ", "Wire Out Filling Shop is not displayed on Invoice Order details popup");
            softassert.assertEquals(dashboardorder.get_Wireout_Filling_Shopname(), "FSN Headquarters", "Wire Out Filling Shop Name is not displayed on Invoice Order details popup");
            softassert.assertEquals(dashboardorder.get_Wireout_Wire_Method(), "FSN", "Wire Out Wire Method is not displayed on Invoice Order details popup");
            softassert.assertEquals(dashboardorder.get_Wireout_Wire_Amount(), wireout_amount, "Wire Out Wire Amount is not displayed on Invoice Order details popup");

            softassert.assertEquals(dashboardorder.get_Activities_Section_Full_Message(), "SEND WIRE OUT\n" +
                    "" + Ordered_Time + "\n" +
                    "Delivery Time: Around 01:57 PM. New Price $" + wireout_amount + "\n" +
                    "Filling Florist: FSN Headquarters, FSNHQ\n" +
                    "Wireout Method: FSN", "Test Step - 16 - Activities Section Send Wire Out full message is not displayed on delivery popup");


        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
        } finally {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            softassert.assertAll();
        }
    }
}
