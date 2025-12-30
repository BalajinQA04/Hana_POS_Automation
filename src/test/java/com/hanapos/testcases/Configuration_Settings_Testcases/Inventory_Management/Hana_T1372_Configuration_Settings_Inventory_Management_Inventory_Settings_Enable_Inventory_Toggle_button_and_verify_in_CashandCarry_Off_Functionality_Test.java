package com.hanapos.testcases.Configuration_Settings_Testcases.Inventory_Management;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T1372_Configuration_Settings_Inventory_Management_Inventory_Settings_Enable_Inventory_Toggle_button_and_verify_in_CashandCarry_Off_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    String invoice;
    String inventory_value;
    String currentAvailableStock;
    private DashboardOrderPage dashboardorder;
    private ProductManagementPage productManagementPage;

    private Configuration_SettingsPage settingsPage;

    @Epic("Configuration Settings Module - Inventory Management")
    @Test(groups = {"Regression"})
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
            currentAvailableStock = productManagementPage.get_Current_Available_Stock_Value_On_Pricing_Tab_Product_Management_Page();
            int afterCurrentAvailableStock = Integer.parseInt(currentAvailableStock);
            softassert.assertEquals(afterCurrentAvailableStock, beforeCurrentAvailableStock, "Test Step - 23 : Current available stock is reduced if inventory is disabled on product management page");
            productManagementPage.click_Close_Icon_On_Pricing_Tab_Inventory_Control_Product_Management_Page();


        } catch (Exception e) {
            softassert.fail("Known Issue : " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }


}
