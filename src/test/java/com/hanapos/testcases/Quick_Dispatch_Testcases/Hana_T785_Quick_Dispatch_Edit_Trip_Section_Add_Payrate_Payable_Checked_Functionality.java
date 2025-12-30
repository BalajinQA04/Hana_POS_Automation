package com.hanapos.testcases.Quick_Dispatch_Testcases;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;

public class Hana_T785_Quick_Dispatch_Edit_Trip_Section_Add_Payrate_Payable_Checked_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private DispatchPage dispatch;
    private Advance_DispatchPage advanceDispatchPage;
    private ConfirmationPage confirmationPage;
    private Reports_PayrollReports_Page payrollreportpage;
    String invoice1;
    public static final String dataSheetName = "Hana_T783";
    String NonpayableText = "Driver Ignored Special Delivery Instructions";
    Faker faker = new Faker(new Locale("en-US"));
    String reci_phone_number2;
    String floor_number;
    String Delivery_Date;
    public static LoggerUtil logger_Util;
    String updateDriverName = "SR Sakrateesh R";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Quick Dispatch Module")
    @Test(enabled = true, priority = 1, groups = {"Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T785_Quick_Dispatch_Edit_Trip_Section_Add_Payrate_Payable_Checked_Functionality(
            String salesperson, String customername, String recifname, String recilname,
            String reci_full_address1, String reciaddress1, String reciaddress2, String recizip, String recicity,
            String recistate, String recicountry, String reciphone, String recilocation, String occasion,
            String searchandselectitemcode, String paymenttype) {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();

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
            phoneorder.ClickDeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnDeliveryTypeOnPhoneOrderPage(), "#676a6c", "Pickup type is not highlighted in blue color");

            // Test Step - 6
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            phoneorder.SearchAndSelectCustomerOnCust_Section(customername);
            delayWithGivenTime(2000);
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
            phoneorder.EnterReciFirstName(recifname);
            phoneorder.EnterReciLastName(recilname);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(reci_full_address1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), reciaddress1, "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), recizip, "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), recicity, "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), recistate, "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.EnterReciAddress2(reciaddress2);
            phoneorder.SelectReciCountry(recicountry);
            phoneorder.EnterReciPhone(reciphone);
            delayWithGivenTime(1000);
            reci_phone_number2 = faker.phoneNumber().cellPhone();
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
            softassert.assertEquals(phoneorder.getReciPhone(), reciphone, "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), recilocation, "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");

            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(12, 30);
            phoneorder.Select_DeliveryOnTime_Dropdown("Around");
            phoneorder.Select_Zone_OnRecipientSection("Automation Zone");
            delayWithGivenTime(1000);
            Delivery_Date = phoneorder.getDeliveryDateOnReciSection();

            System.out.println("Phone Order Page : " + get_AST_Date(0));
            System.out.println("Phone Order Page : " + getASTTime(0, 0));

            //Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase("Happy Birthday! Hope you have an amazing day!"), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 9
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(searchandselectitemcode, "rrd-Red Rose Deluxe");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), "rrd", "Test Step - 9 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), "Red Rose Deluxe", "Test Step - 9 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 9 - Item quantity is not displayed on phone order page product details section");

            if (phoneorder.getUnitPriceOnProdDetails() == "299.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            }

            delayWithGivenTime(2000);

            phoneorder.EnterSpecialInstructions_ProductDetailsSection("Deliver to the reception desk at office building.");
            delayWithGivenTime(1000);
            phoneorder.EnterDriverInstructions_ProductDetailsSection("Ensure flowers are fresh, upright, and water-filled");
            delayWithGivenTime(1000);
            phoneorder.EnterCustomerPrivateNotesInstructions_ProductDetailsSection("This is a secret admirer gift – please do not include sender’s name.");
            delayWithGivenTime(1000);

            // Test Step - 10
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymenttype);
            delayWithGivenTime(1000);
            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(1000);
            getDriver().switchTo().activeElement();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 10 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 11
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 13 - Order confirmation page is not displayed");
            delayWithGivenTime(2000);
            invoice1 = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            System.out.println("Order Confirmation Page : " + get_AST_Date(0));
            System.out.println("Order Confirmation Page : " + getASTTime(0, 0));


            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();                          //https://hanafloralpos3.com/Dashboard/Order
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 14 - Dashboard order page is not displayed");
            delayWithGivenTime(2000);

            dashboardorder.EnterGlobalSearch(invoice1);
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice1), "Test Step - 10 - Respective Invoice number : " + invoice1 + " is not displayed on all orders page");

            delayWithGivenTime(2000);
            // Test Step - 2
            dashboard.Hover_Dispatch_And_Click_QuickDispatch();
            dispatch = new DispatchPage();
            softassert.assertTrue(dispatch.Verify_DispatchPopup_IsDisplayed(), "Test Step - 3 - Dispatch popup is not displayed");

            // Test Step - 3
            delayWithGivenTime(3000);
           // dispatch.Select_Dispatch_Date();
            dispatch.Select_Delivery_Date_on_Quick_Dispatch_Page(Delivery_Date);
            delayWithGivenTime(3000);
            int inital_delivery_count = Integer.parseInt(dispatch.Get_total_deliveries_count_from_trip_section());

            dispatch.Click_The_Invoice_In_The_Pending_Deliveries_Section(invoice1);
            delayWithGivenTime(3000);
            softassert.assertEquals(dispatch.get_InvoiceNumber_On_TripSection(), invoice1, "Test Step 3 - Invoice number is not displaying in the Trip section");
            softassert.assertEquals(Integer.parseInt(dispatch.Get_total_deliveries_count_from_trip_section()), inital_delivery_count + 1, "Test Step 3 - Save button is not displaying in the Trip Section");

            dispatch.Select_Driver("LJB Liam Jack Benjamin");
            softassert.assertEquals(dispatch.get_Selected_Driver(), "LJB Liam Jack Benjamin", "Test Step - 3 - Selected Driver is not displayed on the dropdown");
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();

            dispatch.Click_DispatchSave_Button();
            delayWithGivenTime(3000);
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Dispatch Saved Successfully", "Test Step - 3 - Dispatch Saved Successfully toast success text message is not displayed");

            softassert.assertTrue(dispatch.verify_trip_no_is_generated_or_not(), "Test step 3 - Trip number is not generated");
            softassert.assertTrue(dispatch.verify_route_number_is_generated_or_not(), "Test step 3 - Route number is not displaying");
            softassert.assertTrue(dispatch.verify_trip_section_created_date_time_format_displaying_properly_or_not(), "Test Step 3 - Created Date Time format is not displaying properly");

            softassert.assertTrue(dispatch.Verify_Trip_Section_IsDisplayed(), "Test Step 4 - Trip section is not displayed");
            String Route_Number = dispatch.Get_Route_number();

            dispatch.Click_SavedTrips_based_on_route_number(Route_Number);

            //dispatch.Click_Confirmation_yes_button();
            delayWithGivenTime(2000);

            //Test Step 4
            softassert.assertTrue(dispatch.Verify_AddPayrateBtn_IsDisplayed(), "Test Step 4 - Add Payrate button is not displaying");
            delayWithGivenTime(3000);
            dispatch.Click_AddPayrate_Button();
            delayWithGivenTime(3000);
            softassert.assertTrue(dispatch.verify_add_payrate_popup_IsDisplayed(), "Test Step 4 - Add Payrate pop-up is not displaying");

            //Test Step 5
            getDriver().switchTo().frame("ifrmAddPayrate");
            delayWithGivenTime(2000);
            softassert.assertTrue(dispatch.verify_invoice_is_displaying_in_the_AddPayrate_popup(invoice1), "Test Step 5 - Invoice number is not displaying in the Add Payrate pop-up");

            // Test Step 6
            dispatch.Payable_checkbox_unchecked(invoice1);
            dispatch.Payable_checkbox_checked(invoice1);
            softassert.assertTrue(dispatch.Verify_Payable_Checkbox_IsChecked(invoice1), "Test Step 6 - Payable checkbox is checked");

            //Test Step 7
            dispatch.Enter_value_in_Drivers_Payrate_textbox("10", invoice1);
            delayWithGivenTime(2000);
            softassert.assertEquals(dispatch.Get_value_in_Drivers_Payrate_textbox(), "10", "Test Step 7 - Entered value is displaying properly");

            //Test Step 8
            getDriver().switchTo().defaultContent();
            dispatch.Click_close_icon_add_payrate_pop_up();

            //Test Step 9
            dispatch.Click_AddPayrate_Button();
            getDriver().switchTo().frame("ifrmAddPayrate");
            delayWithGivenTime(2000);
            dispatch.Payable_checkbox_unchecked(invoice1);
            dispatch.Payable_checkbox_checked(invoice1);
            softassert.assertTrue(dispatch.Verify_Payable_Checkbox_IsChecked(invoice1), "Test Step 6 - Payable checkbox is checked");

            //Test Step 10
            dispatch.Enter_value_in_Drivers_Payrate_textbox("10", invoice1);

            //Test Step 11

            getDriver().switchTo().defaultContent();
            dispatch.Click_add_payrate_update_button();
//            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(),"Pay Rate Updated Successfully","Test Step 11 - Success toaster message is not displaying properly");

            //Test Step 12
            dispatch.Click_close_icon_add_payrate_pop_up();
            softassert.assertFalse(dispatch.verify_add_payrate_popup_IsDisplayed(), "Test Step 12 - Add Payrate pop-up is not displaying");

            delayWithGivenTime(1500);

            //Test Step 13
            dispatch.Click_CloseIcon_dispatchPopup();
            dashboardorder = new DashboardOrderPage();

            dashboardorder.EnterGlobalSearch(invoice1);

            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.get_status_on_orderpage(), "Dispatched\nDriver: Liam Jack Benjamin", "Test Step - 13 - Order status displayed on dashboard order page is not matched");
            confirmationPage = new ConfirmationPage();
            dashboard.MouseAndClick_Confirmation_sub_menu();
            //softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 12 - Order confirmation page is not displayed");
            delayWithGivenTime(3000);
            confirmationPage.Select_Delivery_Date_on_Delivery_Confirmation_Page(Delivery_Date);
            delayWithGivenTime(1500);
            confirmationPage.click_Search_Button_On_Delivery_Confirmation_Details();
            delayWithGivenTime(1500);
            confirmationPage.Confirming_the_confirmation_pop_up();
            delayWithGivenTime(2000);
            confirmationPage.Enter_RecipientInvoice_ConfirmationPage(invoice1);
            delayWithGivenTime(2000);
            softassert.assertEquals(confirmationPage.get_entered_RecipientInvoice_ConfirmationPage(), invoice1, "Test Step - 13 - Recipient Invoice is not matched");
            delayWithGivenTime(2000);
            confirmationPage.select_delivery_code_Confirmation_Page("At The Door");

            confirmationPage.confirm_selected_deliveries_button_click_Confirmation_page();
            confirmationPage.confirm_delivery_page_close_icon_click();

            //Test Step 14
            dashboard.MouseAndClick_Payroll_Reports_sub_menu();
            payrollreportpage = new Reports_PayrollReports_Page();
            softassert.assertTrue(payrollreportpage.verify_payroll_report_page_ISDisplayed(), "Test Step 14 - Payroll Report page is not displaying");

            //Test Step 15
            payrollreportpage.Enter_Delivery_From_Date(Delivery_Date);
            payrollreportpage.Enter_Delivery_To_Date(Delivery_Date);
            payrollreportpage.Click_Search_button();
            //  softassert.assertTrue(payrollreportpage.verify_whether_the_delivery_review_row_IsDisplayed(), "Test Step 15 - Delivery Review rows is not displaying");
            delayWithGivenTime(2000);

            //Test Step 16
            //  softassert.assertTrue(payrollreportpage.verify_Invoice_is_displaying(invoice1), "Test Step 16 - Invoice is not displaying");
            payrollreportpage.click_Invoice_Header_Column_On_Payroll_Report_Table_Grid();
            delayWithGivenTime(2000);
            softassert.assertTrue(payrollreportpage.is_InvoiceNumber_Displayed_On_Payroll_Report_Table_Grid(invoice1), "Test Step 15 - Invoice: " + invoice1 + " is not displaying in the Payroll Report table grid");
            delayWithGivenTime(2000);

            //Test Step 17
            softassert.assertTrue(payrollreportpage.is_Payable_Checkbox_Checked_On_Payroll_Report_Table_Grid(invoice1), "Test Step 17 - Checkbox is not checked for invoice: " + invoice1);

            //Test Step 18
            softassert.assertEquals(payrollreportpage.get_PayRate_Label_From_PayrollReport(invoice1),"Not confirmed", "Test Step 18 - Entered Drivers Payrate is not displaying");

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

