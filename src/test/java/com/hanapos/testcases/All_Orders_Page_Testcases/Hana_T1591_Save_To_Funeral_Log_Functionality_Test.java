package com.hanapos.testcases.All_Orders_Page_Testcases;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import java.util.Locale;

public class Hana_T1591_Save_To_Funeral_Log_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private Configuration_SettingsPage settingsPage;
    String invoiceNumber;
    public static LoggerUtil logger_Util;
    Faker faker = new Faker(new Locale("en-US"));
    String recifname1;
    String recilname2;
    String recipientfullname;
    String reci_full_address1 = "207 Depot St, Marthasville, MO 63357";
    String reci_phone_number1;
    String reci_phone_number2;
    String floor_number;
    String automationshop_deliverydate;

    @Owner("Balaji N")
    @Epic("All Orders Page Module")
    @Test(enabled = true, groups = {"Regression", "Sanity"})
    public void validate_Hana_T1591_Save_To_Funeral_Log_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();
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
            String companyName = getData("customer.companyName", "custData.json");
            String fullName = getData("customer.fullName", "custData.json");
            String firstName = getData("customer.firstName", "custData.json");
            String lastName = getData("customer.lastName", "custData.json");
            String address1 = getData("customer.address1", "custData.json");
            String address2 = getData("customer.address2", "custData.json");
            String city = getData("customer.city", "custData.json");
            String state = getData("customer.state", "custData.json");
            String email = getData("customer.email", "custData.json");
            String phone = getData("customer.phoneNumber", "custData.json");
            String altphone = getData("customer.altPhoneNumber", "custData.json");
            String zip = getData("customer.zipCode", "custData.json");


            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            phoneorder.SearchAndSelectCustomerOnCust_Section(fullName);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), firstName, "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), lastName, "Test Step - 6 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), companyName, "Test Step - 6 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), prop.getProperty("customeremail"), "Test Step - 6 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), address1, "Test Step - 6 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), address2, "Test Step - 6 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), zip, "Test Step - 6 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), city, "Test Step - 6 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getStateOnPhoneOrderPage(), state, "Test Step - 6 - state is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), phone, "Test Step - 6 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), altphone, "Test Step - 6 - Alt phone number is not displayed on phone order page");

            // Test Step - 7
            recifname1 = faker.name().firstName();
            recilname2 = faker.name().lastName();
            recipientfullname = recifname1 + " " + recilname2;
            phoneorder.EnterReciFirstName(recifname1);
            phoneorder.EnterReciLastName(recilname2);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname1, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname2, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");
            delayWithGivenTime(1000);

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(reci_full_address1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "207 Depot St", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63357", "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Marthasville", "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.SelectReciCountry(prop.getProperty("recipient_country1"));
            phoneorder.EnterReciAddress2("301 West Front St");
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
            automationshop_deliverydate = phoneorder.getDeliveryDateOnReciSection();
            phoneorder.Enter_DeliveryTime_OnRecipientSection(14, 30);
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
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), prop.getProperty("product_itemcode1"), "Test Step - 9 - Item code " + prop.getProperty("product_itemcode1") + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), prop.getProperty("product_description1"), "Test Step - 9 - Item description " + prop.getProperty("product_description1") + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 9 - Item quantity is not displayed on phone order page product details section");

            if (phoneorder.getUnitPriceOnProdDetails() == "299.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            } else if (phoneorder.getUnitPriceOnProdDetails() == "309.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "309.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            }

            delayWithGivenTime(2000);

            phoneorder.EnterSpecialInstructions_ProductDetailsSection("Automation Special Instruction - Flowers must be delivered on a schedule time");
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SpecialInstructions_ProductDetailsSection(), "Automation Special Instruction - Flowers must be delivered on a schedule time", "Test Step - 9 - Special instructions is not displayed on phone order page product details section");

            phoneorder.EnterDriverInstructions_ProductDetailsSection("Automation Driver Instruction will be handle with care and dont delay to deliver the flower");
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_DriverInstructions_ProductDetailsSection(), "Automation Driver Instruction will be handle with care and dont delay to deliver the flower", "Test Step - 9 - Driver instructions is not displayed on phone order page product details section");

            phoneorder.EnterCustomerPrivateNotesInstructions_ProductDetailsSection("Automation Customer Private Notes");
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_CustomerPrivateNotesInstructions_ProductDetailsSection(), "Automation Customer Private Notes", "Test Step - 9 - Customer private notes is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Customer Notes updated successfully", "Test Step - 9 - Customer private notes is not displayed on phone order page product details section");

            phoneorder.Select_TaxType_OnPhoneOrderPage(prop.getProperty("product_taxtype"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_TaxType_OnPhoneOrderPage(), prop.getProperty("product_taxtype"), "Test Step - 9 - Tax type is not displayed on phone order page product details section");

            faker = new Faker(new Locale("en-US"));
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

            // Test Step - 14
            delayWithGivenTime(1000);
            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 14 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 15
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 15 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            // Test Step - 16
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 16 - Dashboard order page is not displayed");

            // Test Step - 17
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 17 - Invoice number: " + invoiceNumber + " is not displayed on all orders page");

            // Test Step - 18
            dashboardorder.Click_ActionMenu_For_Respective_Invoice(invoiceNumber);
            delayWithGivenTime(1000);
            dashboardorder.click_Save_To_Funeral_Log();
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.is_SaveToFuneral_Confirmation_Popup_Displayed(), "Test Step - 18 - Save to funeral log confirmation popup is not displayed");
            softassert.assertEquals(dashboardorder.get_SaveToFuneral_Confirmation_Popup_Header(), "Confirmation", "Test Step - 18 - Save to funeral log confirmation popup header is not displayed properly");
            softassert.assertEquals(dashboardorder.get_SaveToFuneral_Confirmation_Popup_Message(), "Are you sure you want to save this order to the Funeral Log?", "Test Step - 18 - Save to funeral log confirmation popup message is not displayed properly");
//            softassert.assertEquals(
//                    dashboardorder.get_OrderDetails_Information_On_SaveToFuneral_Confirmation_Popup(),
//                    "Order Details\n" +
//                            "Order Number: " + invoiceNumber + "\n" +
//                            "Delivery Date & Time: " + CurrentDate() + ", 02:30 PM\n" +
//                            "Recipient Name:  " + recifname1 + " " + recilname2,
//                    "Test Step - 18 - Order details information is not displayed properly on save to funeral log confirmation popup"
//            );

            String actualText = dashboardorder.get_OrderDetails_Information_On_SaveToFuneral_Confirmation_Popup().replaceAll("\\s+", " ").trim();
            String expectedText = ("Order Details\n" +
                    "Order Number: " + invoiceNumber + "\n" +
                    "Delivery Date & Time: " + CurrentDate() + ", 02:30 PM\n" +
                    "Recipient Name: " + recifname1 + " " + recilname2).replaceAll("\\s+", " ").trim();

            softassert.assertEquals(actualText, expectedText, "Test Step - 18 - Order details info mismatch on save to funeral log confirmation popup");


            softassert.assertEquals(dashboardorder.get_confirmation_code_with_message(), "Type " + dashboardorder.get_Confirmation_Code_On_SaveToFuneral_Confirmation_Popup() + " to confirm", "Test Step - 18 - Confirmation code is not displayed properly on save to funeral log confirmation popup");

            delayWithGivenTime(1000);
            String confirmation_code = dashboardorder.get_Confirmation_Code_On_SaveToFuneral_Confirmation_Popup();
            dashboardorder.enter_Confirmation_Code_On_SaveToFuneral_Confirmation_Popup(confirmation_code);
            delayWithGivenTime(1000);
            dashboardorder.click_Cancel_Button_On_SaveToFuneral_Confirmation_Popup();
            delayWithGivenTime(1000);

            // Test Step - 19
            dashboardorder.Click_ActionMenu_For_Respective_Invoice(invoiceNumber);
            delayWithGivenTime(1000);
            dashboardorder.click_Save_To_Funeral_Log();
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.is_SaveToFuneral_Confirmation_Popup_Displayed(), "Test Step - 18 - Save to funeral log confirmation popup is not displayed");
            softassert.assertEquals(dashboardorder.get_SaveToFuneral_Confirmation_Popup_Header(), "Confirmation", "Test Step - 18 - Save to funeral log confirmation popup header is not displayed properly");
            softassert.assertEquals(dashboardorder.get_SaveToFuneral_Confirmation_Popup_Message(), "Are you sure you want to save this order to the Funeral Log?", "Test Step - 18 - Save to funeral log confirmation popup message is not displayed properly");
            String actual_Text = dashboardorder.get_OrderDetails_Information_On_SaveToFuneral_Confirmation_Popup().replaceAll("\\s+", " ").trim();
            String expected_Text = ("Order Details\n" +
                    "Order Number: " + invoiceNumber + "\n" +
                    "Delivery Date & Time: " + CurrentDate() + ", 02:30 PM\n" +
                    "Recipient Name: " + recifname1 + " " + recilname2).replaceAll("\\s+", " ").trim();

            softassert.assertEquals(actual_Text, expected_Text, "Test Step - 18 - Order details info mismatch on save to funeral log confirmation popup");

            softassert.assertEquals(dashboardorder.get_confirmation_code_with_message(), "Type " + dashboardorder.get_Confirmation_Code_On_SaveToFuneral_Confirmation_Popup() + " to confirm", "Test Step - 18 - Confirmation code is not displayed properly on save to funeral log confirmation popup");

            delayWithGivenTime(1000);
            String confirmation_code2 = dashboardorder.get_Confirmation_Code_On_SaveToFuneral_Confirmation_Popup();
            dashboardorder.enter_Confirmation_Code_On_SaveToFuneral_Confirmation_Popup(confirmation_code2);
            delayWithGivenTime(1000);
            dashboardorder.click_Ok_Button_On_SaveToFuneral_Confirmation_Popup();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Order successfully added to Funeral Log", "Test Step - 19 - Order successfully added to Funeral Log toaster messageis not displayed");

            dashboard = new HanaDashBoardPage();
            dashboard.Click_settingsSubmenu();
            delayWithGivenTime(2000);
            settingsPage = new Configuration_SettingsPage();
            softassert.assertTrue(settingsPage.Verify_Configuration_SettingsPage(), "Test Step - 19 - Settings page is not displayed");

            // Test Step - 20
            settingsPage.Click_OrderEntryLeftMenu();

            // Test Step - 21
            settingsPage.Click_FuneralLogMenu();

            // Test Step - 22
            delayWithGivenTime(3000);
            softassert.assertEquals(settingsPage.getDisplayedFirstName(), recifname1, "Test Step - 22 - First Name Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedLastName(), recilname2, "Test Step - 22 - Last Name Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedDeliveryDate(), automationshop_deliverydate, "Test Step - 22 - Delivery Date Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedDeliveryTime(), "2:30PM", "Test Step - 22 - Delivery Time Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedDeliveryOn(), "Around", "Test Step - 22 - Delivery On Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedDeliveryAddress1(), "207 Depot St", "Test Step - 22 - Delivery Address 1 Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getDisplayedDeliveryAddress2(), "301 West Front St", "Test Step - 22 - Delivery Address 2 Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu");

            delayWithGivenTime(3000);
            settingsPage.Click_EditIcon_FuneralLogGridTableRow1();
            softassert.assertTrue(settingsPage.Verify_FuneralLog_AddNewRecord_Popup(), "Test Step - 23 - Funeral Log Add new record popup is not displayed");
            softassert.assertEquals(settingsPage.getFirstNameFuneralLogAddNewRecordPopup(), recifname1, "Test Step - 23 - First Name Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu ");
            softassert.assertEquals(settingsPage.getLastNameFuneralLogAddNewRecordPopup(), recilname2, "Test Step - 23 - Last Name Compared with recipient section saved information is not matched with configuration order entry menu funeral log submenu ");
            delayWithGivenTime(1000);
            softassert.assertEquals(settingsPage.getSelectedDeliveryDateFuneralLogAddNewRecordPopup(), automationshop_deliverydate, "Test Step - 23 - Delivery Date Compared with recipient section saved information is not displayed on configuration order entry menu funeral log submenu");
            softassert.assertEquals(settingsPage.getEnteredAddress1FuneralLogAddNewRecordPopup(), "207 Depot St", "Test Step - 23 - Delivery Address 1 Compared with recipient section saved information is not displayed on configuration order entry menu funeral log submenu");
            delayWithGivenTime(1000);
            //========================Below webelements are unable to get immediately getting stop
            //	softassert.assertEquals(settingsPage.getSelectedDeliveryOnFuneralLogAddNewRecordPopup(), "Exactly At","Test Step - 23 - Delivery On Compared with recipient section saved information is not displayed on configuration order entry menu funeral log submenu");
            //	softassert.assertEquals(settingsPage.getSelectedDeliveryTimeFuneralLogAddNewRecordPopup(), "5:30 PM","Test Step - 23 - Delivery Time Compared with recipient section saved information is not displayed on configuration order entry menu funeral log submenu");
            delayWithGivenTime(1000);

            //======== To Avoid junk files due to automation delete the funeral log============//
            settingsPage.Click_CloseIcon_FuneralLogAddNewRecordPopup();
            delayWithGivenTime(1000);
            settingsPage.Click_DeleteIcon_FuneralLogGridTableRow1();
            delayWithGivenTime(1000);
            settingsPage.Click_Row1_DeleteIcon_FuneralLogGridTableRow2();


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
