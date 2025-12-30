package com.hanapos.testcases.Configuration_Settings_Testcases.Inventory_Management;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import java.util.Locale;

public class Hana_Configuration_Settings_Inventory_Management_Inventory_Settings_Inventory_Toggle_button_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;

    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private ProductManagementPage productManagementPage;
    private Configuration_SettingsPage settingsPage;

    String invoice1;
    String inventory_value1;
    String currentAvailableStock1;

    @Epic("Configuration Settings Module - Inventory Management")
    @Test(groups = {"Regression"}, priority = 1, enabled = true)
    public void Validate_Hana_T1285_Configuration_Settings_Inventory_Management_Inventory_Settings_Enable_Inventory_Toggle_button_and_verify_in_CashandCarry_On_Functionality_Test() {
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
            inventory_value1 = productManagementPage.get_Product_Level_Inventory_Value_On_Product_Management_Page();
            int beforeInventoryValue = Integer.parseInt(inventory_value1);


            delayWithGivenTime(2000);
            productManagementPage.click_Pricing_Tab_On_Product_Management_Page();
            delayWithGivenTime(2000);
            productManagementPage.click_Inventory_Icon_On_Pricing_Tab_Table_Grid();
            delayWithGivenTime(2000);
            currentAvailableStock1 = productManagementPage.get_Current_Available_Stock_Value_On_Pricing_Tab_Product_Management_Page();
            int beforeCurrentAvailableStock = Integer.parseInt(currentAvailableStock1);
            delayWithGivenTime(2000);
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
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");

            // Test Step - 15
            cashandcarry.SelectShopName(prop.getProperty("shopname"));
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("shopname"), "Test Step - 15 : Shop name is not matched with selected shop name");

            cashandcarry.SelectClerkName(prop.getProperty("clerkname"));
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("clerkname"), "Test Step - 15 : Clerk name is not matched with selected clerk name");

            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 15 : Employee name is not matched with selected employee name");

            // Test Step - 16
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.is_Autosuggestion_Dropdown_Displayed_for_ItemCode(prop.getProperty("product_itemcode1")), "Test Step - 16 - Autosuggestion dropdown is not displayed for item code");

            // Test Step - 17
            delayWithGivenTime(2000);
            cashandcarry.SearchAndSelectTheItemCode(prop.getProperty("itemcode"), prop.getProperty("itemdescription"));
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), prop.getProperty("itemdescription"), "Test Step - 17: Item description is not displayed");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Steps - 17 - Item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Steps - 17 - Item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Steps - 17 - Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Steps - 17 - Item discount percentage is not matched with search and selected item code");

            // Test Step - 18
            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem(), "Test Step - 18: Added Item code is not displayed on product table grid");
            softassert.assertEquals(cashandcarry.getAddedItemCode(), prop.getProperty("itemcode"), "Test Steps - 18 - Added item code is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), prop.getProperty("itemdescription"), "Test Steps - 18 - Added item description is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Steps - 18 - Added item quantity is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00", "Test Steps - 18 - Added item extended price is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00", "Test Steps - 18 - Added item price is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Steps - 18 - Added item discount amount is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Steps - 18 - Added item discount percentage is not matched displayed on table grid row1");

            delayWithGivenTime(2000);

            // Test Step - 19
            cashandcarry.EnterCustomerName(prop.getProperty("cust_firstName"), prop.getProperty("custfullname"));
            softassert.assertEquals(cashandcarry.getDisplayedCustomerName(), prop.getProperty("custfullname"), "Test Step - 19 - Customer name is not matched with entered customer name");

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 19 : Selected tax type is not displayed");

            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 19 : Selected occasion is not displayed");
            delayWithGivenTime(1000);

            cashandcarry.ClickPayButton();
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 19: Cash And Carry payment page is not displayed");

            // Test Step - 20
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyCreditCardTabIsSelected(), "true", "Test Step - 20: By defaultCredit card tab section is not displayed");

            delayWithGivenTime(1000);
            cashandcarrypayment.ClickCashTab();
            cashandcarrypayment.EnterGivenAmountOnCashTab(cashandcarrypayment.getGrandTotaltoPay());

            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 20: Success toaster message is not displayed");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step- 20: success toaster message is not displayed");

            if (cashandcarrypayment.getConfirmationPopup()) {
                softassert.assertTrue(cashandcarrypayment.VerifyOrderConfirmationPopup(), "Test Step - 20: Order confirmation popup is not displayed");
                softassert.assertTrue(cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo(), "Test Step - 20: Order confirmation message along with invoice number is not displayed");
                invoice1 = cashandcarrypayment.GetInvoiceNumber();
                cashandcarrypayment.GetTenderPrice();
            }
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();

            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 20: User is not navigated to All orders page");
            delayWithGivenTime(1000);

            dashboardorder.EnterGlobalSearch(invoice1);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice1), "Test Step - 20 - Respective Invoice number : " + invoice1 + " is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoice1), "Delivered", "Test Step - 20 - Order status is not displayed as delivered for cash and carry order");
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoice1), "Walkin Sales", "Test Step - 20: Order Type as Walkin Sales is not properly displayed for cash and carry order");
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoice1), "Cash", "Test STep - 20: Cash mode of pay is not displayed on orders page");

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
            currentAvailableStock1 = productManagementPage.get_Current_Available_Stock_Value_On_Pricing_Tab_Product_Management_Page();
            int afterCurrentAvailableStock = Integer.parseInt(currentAvailableStock1);
            softassert.assertTrue(afterCurrentAvailableStock < beforeCurrentAvailableStock, "Test Step - 23 : Current available stock is not reduced on product management page");
            productManagementPage.click_Close_Icon_On_Pricing_Tab_Inventory_Control_Product_Management_Page();

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }

    Faker faker = new Faker(new Locale("en-US"));
    String recifname1;
    String recilname2;
    String reci_phone_number1;
    String reci_phone_number2;
    String floor_number;
    String invoice2;
    String inventory_value2;
    String currentAvailableStock2;

    @Epic("Configuration Settings Module - Inventory Management")
    @Test(groups = {"Regression"}, priority = 2, enabled = true)
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
            inventory_value2 = productManagementPage.get_Product_Level_Inventory_Value_On_Product_Management_Page();
            int beforeInventoryValue = Integer.parseInt(inventory_value2);

            delayWithGivenTime(2000);
            productManagementPage.click_Pricing_Tab_On_Product_Management_Page();
            delayWithGivenTime(2000);
            productManagementPage.click_Inventory_Icon_On_Pricing_Tab_Table_Grid();
            delayWithGivenTime(2000);
            currentAvailableStock2 = productManagementPage.get_Current_Available_Stock_Value_On_Pricing_Tab_Product_Management_Page();
            int beforeCurrentAvailableStock = Integer.parseInt(currentAvailableStock2);

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
            invoice2 = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 20: User is not navigated to All orders page");
            delayWithGivenTime(1000);

            dashboardorder.EnterGlobalSearch(invoice2);
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoice2), "Invoice/House Account", "Test Step - 20: Invoice/House Account - mode of pay is not displayed on orders page");
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice2), "Test Step - 20 - Respective Invoice number : " + invoice2 + " is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoice2), "New", "Test Step - 20 - Order status is not displayed as New");
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoice2), "Phone Order", "Test Step - 20: Order Type as Phone Order is not properly displayed for cash and carry order");

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
            currentAvailableStock2 = productManagementPage.get_Current_Available_Stock_Value_On_Pricing_Tab_Product_Management_Page();
            int afterCurrentAvailableStock = Integer.parseInt(currentAvailableStock2);
            softassert.assertTrue(afterCurrentAvailableStock < beforeCurrentAvailableStock, "Test Step - 23 : Current available stock is not reduced on product management page");
            productManagementPage.click_Close_Icon_On_Pricing_Tab_Inventory_Control_Product_Management_Page();

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }

    String invoice3;
    String inventory_value3;
    String currentAvailableStock3;

    @Epic("Configuration Settings Module - Inventory Management")
    @Test(groups = {"Regression"}, enabled = true, priority = 3)
    public void Validate_Hana_T1372_Configuration_Settings_Inventory_Management_Inventory_Settings_Enable_Inventory_Toggle_button_and_verify_in_CashandCarry_Off_Functionality_Test() {
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
            inventory_value3 = productManagementPage.get_Product_Level_Inventory_Value_On_Product_Management_Page();
            int beforeInventoryValue = Integer.parseInt(inventory_value3);

            delayWithGivenTime(2000);
            productManagementPage.click_Pricing_Tab_On_Product_Management_Page();
            delayWithGivenTime(2000);
            productManagementPage.click_Inventory_Icon_On_Pricing_Tab_Table_Grid();
            delayWithGivenTime(2000);
            currentAvailableStock3 = productManagementPage.get_Current_Available_Stock_Value_On_Pricing_Tab_Product_Management_Page();
            int beforeCurrentAvailableStock = Integer.parseInt(currentAvailableStock3);

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

            settingsPage.turn_Off_Enable_Inventory_Control_Toogle_Button();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.Verify_Turn_Off_Enable_Inventory_Control_Toogle_Button(), "Test Step - 6 - Enable Inventory control toogle button is not turned off state");

            settingsPage.click_Inventory_Management_BreadCrumbs_HyperLink();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_Inventory_Management_Section_Displayed(), "Test Step - 7 - Inventory Management section on configuration settings page is not displayed after clicking on bread crumb link");

            settingsPage.click_Inventory_Settings_Submenu();

            softassert.assertTrue(settingsPage.is_Inventory_Settings_Page_Displayed(), "Test Step - 7 - Inventory settings page is not displayed");
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_Enable_Inventory_Toogle_Button_Displayed(), "Test Step - 7 - Enable Inventory control toogle button is not displayed");

            // Test Step - 8
            settingsPage.turn_On_Enable_Inventory_Control_Toogle_Button();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.Verify_Turn_On_Enable_Inventory_Control_Toogle_Button(), "Test Step - 8 - Enable Inventory control toogle button is not turned on state");

            // Test Step - 9
            settingsPage.turn_Off_Enable_Inventory_Control_Toogle_Button();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.Verify_Turn_Off_Enable_Inventory_Control_Toogle_Button(), "Test Step - 9 - Enable Inventory control toogle button is not turned off state");

            // Test Step - 10
            settingsPage.click_Inventory_Settings_Save_Settings_Button();
            delayWithGivenTime(2000);
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Successfully updated settings", "Test Step - 10 - Successfully updated settings");
            refreshPage();

            // Test Step - 14
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 14 - Cash And Carry page is not displayed");

            // Test Step - 15
            cashandcarry.SelectShopName(prop.getProperty("shopname"));
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("shopname"), "Test Step - 15 : Shop name is not matched with selected shop name");

            cashandcarry.SelectClerkName(prop.getProperty("clerkname"));
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("clerkname"), "Test Step - 15 : Clerk name is not matched with selected clerk name");

            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 15 : Employee name is not matched with selected employee name");

            // Test Step - 16
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.is_Autosuggestion_Dropdown_Displayed_for_ItemCode(prop.getProperty("itemcode")), "Test Step - 16 - Autosuggestion dropdown is not displayed for item code");

            // Test Step - 17
            delayWithGivenTime(2000);
            cashandcarry.SearchAndSelectTheItemCode(prop.getProperty("itemcode"), prop.getProperty("itemdescription"));
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), prop.getProperty("itemdescription"), "Test Step - 17: Item description is not displayed");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Steps - 17 - Item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Steps - 17 - Item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Steps - 17 - Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Steps - 17 - Item discount percentage is not matched with search and selected item code");

            // Test Step - 18
            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem(), "Test Step - 18: Added Item code is not displayed on product table grid");
            softassert.assertEquals(cashandcarry.getAddedItemCode(), prop.getProperty("itemcode"), "Test Steps - 18 - Added item code is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), prop.getProperty("itemdescription"), "Test Steps - 18 - Added item description is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Steps - 18 - Added item quantity is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00", "Test Steps - 18 - Added item extended price is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00", "Test Steps - 18 - Added item price is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Steps - 18 - Added item discount amount is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Steps - 18 - Added item discount percentage is not matched displayed on table grid row1");

            delayWithGivenTime(2000);

            // Test Step - 19
            cashandcarry.EnterCustomerName(prop.getProperty("cust_firstName"), prop.getProperty("custfullname"));
            softassert.assertEquals(cashandcarry.getDisplayedCustomerName(), prop.getProperty("custfullname"), "Test Step - 19 - Customer name is not matched with entered customer name");

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 19 : Selected tax type is not displayed");

            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 19 : Selected occasion is not displayed");
            delayWithGivenTime(1000);

            cashandcarry.ClickPayButton();
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 19: Cash And Carry payment page is not displayed");

            // Test Step - 20
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyCreditCardTabIsSelected(), "true", "Test Step - 20: By defaultCredit card tab section is not displayed");

            delayWithGivenTime(1000);
            cashandcarrypayment.ClickCashTab();
            cashandcarrypayment.EnterGivenAmountOnCashTab(cashandcarrypayment.getGrandTotaltoPay());

            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 20: Success toaster message is not displayed");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step- 20: success toaster message is not displayed");

            if (cashandcarrypayment.getConfirmationPopup()) {
                softassert.assertTrue(cashandcarrypayment.VerifyOrderConfirmationPopup(), "Test Step - 20: Order confirmation popup is not displayed");
                softassert.assertTrue(cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo(), "Test Step - 20: Order confirmation message along with invoice number is not displayed");
                invoice3 = cashandcarrypayment.GetInvoiceNumber();
                cashandcarrypayment.GetTenderPrice();
            }
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();

            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 20: User is not navigated to All orders page");
            delayWithGivenTime(1000);

            dashboardorder.EnterGlobalSearch(invoice3);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice3), "Test Step - 20 - Respective Invoice number : " + invoice3 + " is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoice3), "Delivered", "Test Step - 20 - Order status is not displayed as delivered for cash and carry order");
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoice3), "Walkin Sales", "Test Step - 20: Order Type as Walkin Sales is not properly displayed for cash and carry order");
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoice3), "Cash", "Test STep - 20: Cash mode of pay is not displayed on orders page");

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
            softassert.assertEquals(afterInventoryValueInt, beforeInventoryValue, "Test Step - 22 : Product level inventory value is reduced if inventory toogle is disabled on product management page");

            // Test Step - 23
            productManagementPage.click_Pricing_Tab_On_Product_Management_Page();
            delayWithGivenTime(2000);
            productManagementPage.click_Inventory_Icon_On_Pricing_Tab_Table_Grid();
            delayWithGivenTime(2000);
            currentAvailableStock3 = productManagementPage.get_Current_Available_Stock_Value_On_Pricing_Tab_Product_Management_Page();
            int afterCurrentAvailableStock = Integer.parseInt(currentAvailableStock3);
            softassert.assertEquals(afterCurrentAvailableStock, beforeCurrentAvailableStock, "Test Step - 23 : Current available stock is reduced if inventory is disabled on product management page");
            productManagementPage.click_Close_Icon_On_Pricing_Tab_Inventory_Control_Product_Management_Page();


        } catch (Exception e) {
            softassert.fail("Known Issue : " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }

    String invoice4;
    String inventory_value4;
    String currentAvailableStock4;

    @Epic("Configuration Settings Module - Inventory Management")
    @Test(groups = {"Regression"}, priority = 4, enabled = true)
    public void Validate_Hana_T1376_Configuration_Settings_Inventory_Management_Inventory_Settings_Enable_Inventory_Toggle_button_and_verify_in_PhoneOrderPage_Alias_Order_Entry_Off_Functionality_Test() {
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
            inventory_value4 = productManagementPage.get_Product_Level_Inventory_Value_On_Product_Management_Page();
            int beforeInventoryValue = Integer.parseInt(inventory_value4);

            delayWithGivenTime(2000);
            productManagementPage.click_Pricing_Tab_On_Product_Management_Page();
            delayWithGivenTime(2000);
            productManagementPage.click_Inventory_Icon_On_Pricing_Tab_Table_Grid();
            delayWithGivenTime(2000);
            currentAvailableStock4 = productManagementPage.get_Current_Available_Stock_Value_On_Pricing_Tab_Product_Management_Page();
            int beforeCurrentAvailableStock = Integer.parseInt(currentAvailableStock4);

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

            if (settingsPage.is_Enable_Inventory_Toogle_Button_Displayed()) {
                settingsPage.turn_Off_Enable_Inventory_Control_Toogle_Button();
                delayWithGivenTime(2000);
            }
            softassert.assertTrue(settingsPage.Verify_Turn_Off_Enable_Inventory_Control_Toogle_Button(), "Test Step - 6 - Enable Inventory control toogle button is not turned off state - after clicking off toogle button in configuration settings page");

            settingsPage.click_Inventory_Management_BreadCrumbs_HyperLink();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_Inventory_Management_Section_Displayed(), "Test Step - 7 - Inventory Management section on configuration settings page is not displayed after clicking on bread crumb link");

            settingsPage.click_Inventory_Settings_Submenu();

            softassert.assertTrue(settingsPage.is_Inventory_Settings_Page_Displayed(), "Test Step - 7 - Inventory settings page is not displayed");
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_Enable_Inventory_Toogle_Button_Displayed(), "Test Step - 7 - Enable Inventory control toogle button is not displayed");

            // Test Step - 8
            settingsPage.turn_On_Enable_Inventory_Control_Toogle_Button();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.Verify_Turn_On_Enable_Inventory_Control_Toogle_Button(), "Test Step - 8 - Enable Inventory control toogle button is not turned on state - after clicking on toogle button in configuration settings page");

            // Test Step - 9
            settingsPage.turn_Off_Enable_Inventory_Control_Toogle_Button();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.Verify_Turn_Off_Enable_Inventory_Control_Toogle_Button(), "Test Step - 9 - Enable Inventory control toogle button is not turned off state - after clicking on toogle button in configuration settings page");

            // Test Step - 10
            settingsPage.click_Inventory_Settings_Save_Settings_Button();
            delayWithGivenTime(2000);
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Successfully updated settings", "Test Step - 10 - Successfully updated settings toast message is not displayed as expected");

            // Test Step - 14
            dashboard.ClickOrderEntry();

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
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), prop.getProperty("itemcode"), "Test Step - 19 - Item code " + prop.getProperty("product_itemcode1") + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), prop.getProperty("itemdescription"), "Test Step - 19 - Item description " + prop.getProperty("product_description1") + " is not displayed on phone order page product details section");
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
            invoice4 = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 20: User is not navigated to All orders page");
            delayWithGivenTime(1000);

            dashboardorder.EnterGlobalSearch(invoice4);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice4), "Test Step - 20 - Respective Invoice number : " + invoice4 + " is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoice4), "New", "Test Step - 20 - Order status is not displayed as New");
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoice4), "Phone Order", "Test Step - 20: Order Type as Phone Order is not properly displayed for cash and carry order");
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoice4), "Invoice/House Account", "Test Step - 20: Invoice/House Account - mode of pay is not displayed on orders page");

            // Test Step - 22
            dashboard.Click_Product_Management_Submenu();
            delayWithGivenTime(2000);
            productManagementPage = new ProductManagementPage();
            productManagementPage.search_Product_On_Product_Management_Page(prop.getProperty("product_itemcode1"));
            delayWithGivenTime(2000);
            productManagementPage.click_Edit_Icon_On_Product_Management_Table_Grid();
            delayWithGivenTime(2000);
            productManagementPage.click_Inventory_Tab_On_RespectiveProduct();
            delayWithGivenTime(2000);

            String afterInventoryValue = productManagementPage.get_Product_Level_Inventory_Value_On_Product_Management_Page();
            int afterInventoryValueInt = Integer.parseInt(afterInventoryValue);
            softassert.assertEquals(afterInventoryValueInt, beforeInventoryValue, "Test Step - 22 : Product level inventory value is reduced if disabled the inventory toggle button on product management page");

            // Test Step - 23
            productManagementPage.click_Pricing_Tab_On_Product_Management_Page();
            delayWithGivenTime(2000);
            productManagementPage.click_Inventory_Icon_On_Pricing_Tab_Table_Grid();
            delayWithGivenTime(2000);
            currentAvailableStock4 = productManagementPage.get_Current_Available_Stock_Value_On_Pricing_Tab_Product_Management_Page();
            int afterCurrentAvailableStock = Integer.parseInt(currentAvailableStock4);
            softassert.assertEquals(afterCurrentAvailableStock, beforeCurrentAvailableStock, "Test Step - 23 : Current available stock is reduced if disabled the inventory toggle button on product management page");
            productManagementPage.click_Close_Icon_On_Pricing_Tab_Inventory_Control_Product_Management_Page();

        } catch (Exception e) {
            softassert.fail("Known Issue:- " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }


}
