package com.hanapos.testcases.Configuration_Settings_Testcases.Inventory_Management;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import java.util.Locale;

public class Hana_T1370_Configuration_Settings_Inventory_Management_Inventory_Settings_Enable_Inventory_Toggle_button_and_verify_in_PhoneOrderPage_Alias_Order_Entry_On_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private ProductManagementPage productManagementPage;
    private Configuration_SettingsPage settingsPage;

    Faker faker = new Faker(new Locale("en-US"));
    String recifname1;
    String recilname2;
    String reci_phone_number1;
    String reci_phone_number2;
    String floor_number;
    String invoice;
    String inventory_value;
    String currentAvailableStock;

    @Epic("Configuration Settings Module - Inventory Management")
    @Test(groups = {"Regression"})
    public void Validate_Hana_T1370_Configuration_Settings_Inventory_Management_Inventory_Settings_Enable_Inventory_Toggle_button_and_verify_in_PhoneOrderPage_Alias_Order_Entry_On_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();
        String testCaseName = getCurrentTestName();

        logger_Util = new LoggerUtil();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 - Login page is not displayed");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 2: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 2: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();

            // Test Step - 3
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 - Hana dashboard page is not displayed");

            //============================ Pre-requiste ===============================
            dashboard.Click_Product_Management_Submenu();
            delayWithGivenTime(2000);
            productManagementPage = new ProductManagementPage();
            productManagementPage.search_Product_On_Product_Management_Page(prop.getProperty("itemcode"));
            delayWithGivenTime(2000);
            productManagementPage.click_Edit_Icon_On_Product_Management_Table_Grid();
            delayWithGivenTime(2000);
            productManagementPage.click_Inventory_Tab_On_RespectiveProduct();
            delayWithGivenTime(2000);
            inventory_value = productManagementPage.get_Product_Level_Inventory_Value_On_Product_Management_Page();
            int beforeInventoryValue = Integer.parseInt(inventory_value);

            delayWithGivenTime(2000);
            productManagementPage.click_Pricing_Tab_On_Product_Management_Page();
            delayWithGivenTime(2000);
            productManagementPage.click_Inventory_Icon_On_Pricing_Tab_Table_Grid();
            delayWithGivenTime(2000);
            currentAvailableStock = productManagementPage.get_Current_Available_Stock_Value_On_Pricing_Tab_Product_Management_Page();
            int beforeCurrentAvailableStock = Integer.parseInt(currentAvailableStock);

            productManagementPage.click_Close_Icon_On_Pricing_Tab_Inventory_Control_Product_Management_Page();

            //============================================================================

            delayWithGivenTime(2000);
            dashboard.Click_settingsSubmenu();

            delayWithGivenTime(2000);
            settingsPage = new Configuration_SettingsPage();
            softassert.assertTrue(settingsPage.Verify_Configuration_SettingsPage(), "Test Step - 3 - Configuration Settings page is not displayed");

            settingsPage.click_Inventory_Management_Menu();
            settingsPage.click_Inventory_Settings_Submenu();
            softassert.assertTrue(settingsPage.is_Inventory_Settings_Page_Displayed(), "Test Step - 4 - Inventory settings page is not displayed");
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_Enable_Inventory_Toogle_Button_Displayed(), "Test Step - 5 - Enable Inventory control toogle button is not displayed");

            settingsPage.turn_On_Enable_Inventory_Control_Toogle_Button();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.Verify_Turn_On_Enable_Inventory_Control_Toogle_Button(), "Test Step - 6 - Enable Inventory control toogle button is not turned on");

            settingsPage.click_Inventory_Management_BreadCrumbs_HyperLink();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_Inventory_Management_Section_Displayed(), "Test Step - 7 - Inventory Management section on configuration settings page is not displayed after clicking on bread crumb link");

            settingsPage.click_Inventory_Settings_Submenu();

            softassert.assertTrue(settingsPage.is_Inventory_Settings_Page_Displayed(), "Test Step - 7 - Inventory settings page is not displayed");
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_Enable_Inventory_Toogle_Button_Displayed(), "Test Step - 7 - Enable Inventory control toogle button is not displayed");

            // Test Step - 8
            settingsPage.turn_Off_Enable_Inventory_Control_Toogle_Button();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.Verify_Turn_Off_Enable_Inventory_Control_Toogle_Button(), "Test Step - 8 - Enable Inventory control toogle button is not turned off");

            // Test Step - 9
            settingsPage.turn_On_Enable_Inventory_Control_Toogle_Button();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.Verify_Turn_On_Enable_Inventory_Control_Toogle_Button(), "Test Step - 9 - Enable Inventory control toogle button is not turned on");

            // Test Step - 10
            settingsPage.click_Inventory_Settings_Save_Settings_Button();
            delayWithGivenTime(2000);
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Successfully updated settings", "Test Step - 10 - Successfully updated settings");

            // Test Step - 14
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 15
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 15 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickDeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnDeliveryTypeOnPhoneOrderPage(), "#676a6c", "Test Step - 15: Delivery type as Delivery is not highlighted in blue color");

            // Test Step - 16
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("cust_firstName"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), prop.getProperty("cust_firstName"), "Test Step - 16 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), prop.getProperty("cust_lastName"), "Test Step - 16 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), prop.getProperty("cust_companyName"), "Test Step - 16 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), prop.getProperty("cust_email"), "Test Step - 16 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), prop.getProperty("cust_address1"), "Test Step - 16 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 16 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), prop.getProperty("cust_zipcode"), "Test Step - 16 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), prop.getProperty("cust_city"), "Test Step - 16 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_phoneNumber"), "Test Step - 16 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_Alt_phoneNumber"), "Test Step - 16 - Alt phone number is not displayed on phone order page");

            // Test Step - 17
            recifname1 = faker.name().firstName();
            recilname2 = faker.name().lastName();
            phoneorder.EnterReciFirstName(recifname1);
            phoneorder.EnterReciLastName(recilname2);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname1, "Test Step - 17 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname2, "Test Step - 17 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(prop.getProperty("Full_Reci_Address1_1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "1160 W 5th St", "Test Step - 17 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63090", "Test Step - 17 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Washington", "Test Step - 17 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 17 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.EnterReciAddress2("1160 W 5th St");
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
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 17 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), reci_phone_number1, "Test Step - 17 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), prop.getProperty("recipient_location1"), "Test Step - 17 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 17 - Delivery date is not displayed on phone order page recipient section");

            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(4, 50);
            phoneorder.Select_DeliveryOnTime_Dropdown("Around");
            phoneorder.Select_Zone_OnRecipientSection("Automation Zone");
            delayWithGivenTime(1000);

            //Test Step - 18
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 18 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 18 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 19
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(prop.getProperty("itemcode"), prop.getProperty("itemdescription"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), prop.getProperty("itemcode"), "Test Step - 19 - Item code " + prop.getProperty("itemcode") + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), prop.getProperty("itemdescription"), "Test Step - 19 - Item description " + prop.getProperty("itemdescription") + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 19 - Item quantity is not displayed on phone order page product details section");

            if (phoneorder.getUnitPriceOnProdDetails() == "299.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 19 - Item price is not displayed on phone order page product details section");
            }

            delayWithGivenTime(2000);

            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type_invoice"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), prop.getProperty("payment_type_invoice"), "Test Step - 19 - Selected payment type " + prop.getProperty("payment_type_invoice") + " is not displayed on phone order page payment section");

            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 19 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 19 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoice = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 20: User is not navigated to All orders page");
            delayWithGivenTime(1000);

            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice), "Test Step - 20 - Respective Invoice number : " + invoice + " is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoice), "New", "Test Step - 20 - Order status is not displayed as New");
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoice), "Phone Order", "Test Step - 20: Order Type as Phone Order is not properly displayed for cash and carry order");

            dashboardorder.EnterGlobalSearch(invoice);
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoice), "Invoice/House Account", "Test Step - 20: Invoice/House Account - mode of pay is not displayed on orders page");

            // Test Step - 22
            dashboard.Click_Product_Management_Submenu();
            delayWithGivenTime(2000);
            productManagementPage = new ProductManagementPage();
            productManagementPage.search_Product_On_Product_Management_Page(prop.getProperty("itemcode"));
            delayWithGivenTime(2000);
            productManagementPage.click_Edit_Icon_On_Product_Management_Table_Grid();
            delayWithGivenTime(2000);
            productManagementPage.click_Inventory_Tab_On_RespectiveProduct();
            delayWithGivenTime(2000);

            String afterInventoryValue = productManagementPage.get_Product_Level_Inventory_Value_On_Product_Management_Page();
            int afterInventoryValueInt = Integer.parseInt(afterInventoryValue);
            softassert.assertTrue(afterInventoryValueInt < beforeInventoryValue, "Test Step - 22 : Product level inventory value is not reduced on product management page");

            // Test Step - 23
            productManagementPage.click_Pricing_Tab_On_Product_Management_Page();
            delayWithGivenTime(2000);
            productManagementPage.click_Inventory_Icon_On_Pricing_Tab_Table_Grid();
            delayWithGivenTime(2000);
            currentAvailableStock = productManagementPage.get_Current_Available_Stock_Value_On_Pricing_Tab_Product_Management_Page();
            int afterCurrentAvailableStock = Integer.parseInt(currentAvailableStock);
            softassert.assertTrue(afterCurrentAvailableStock < beforeCurrentAvailableStock, "Test Step - 23 : Current available stock is not reduced on product management page");
            productManagementPage.click_Close_Icon_On_Pricing_Tab_Inventory_Control_Product_Management_Page();

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }


}
