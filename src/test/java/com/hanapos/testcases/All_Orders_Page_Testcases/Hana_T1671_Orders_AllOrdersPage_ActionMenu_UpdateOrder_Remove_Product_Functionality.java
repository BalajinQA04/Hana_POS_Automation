package com.hanapos.testcases.All_Orders_Page_Testcases;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T1671_Orders_AllOrdersPage_ActionMenu_UpdateOrder_Remove_Product_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    String invoiceNumber;
    String total_amount;
    String balance_amount;
    public static final String dataSheetName = "Hana_T1671";
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Owner("Balaji N")
    @Epic("All Orders Page Module")
    @Test(enabled = true, groups = {"Regression", "Smoke", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1671_Orders_AllOrdersPage_ActionMenu_UpdateOrder_Remove_Product_Functionality_Test(
            String shopname, String salesperson, String customername, String recifname, String recilname, String recipientfulladdress1, String reciaddress1, String reciaddress2, String recizip, String recicity,
            String recicountry, String reciphone, String recilocation, String occasion, String itemcode, String itemdescription, String paymenttype, String cash_registry, String action_submenu, String payment_reason, String updated_itemcode, String updated_itemdescription, String updated_itemprice, String updated_itemquantity) {

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
            phoneorder.Select_ShopName_On_PhoneOrder_Page(shopname);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), shopname, "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickDeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnDeliveryTypeOnPhoneOrderPage(), "#676a6c", "Pickup type is not highlighted in blue color");

            // Test Step - 6
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            phoneorder.SearchAndSelectCustomerOnCust_Section(customername);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), prop.getProperty("cust_firstName"), "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), prop.getProperty("cust_lastName"), "Test Step - 6 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), prop.getProperty("cust_companyName"), "Test Step - 6 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), prop.getProperty("cust_email"), "Test Step - 6 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), prop.getProperty("cust_address1"), "Test Step - 6 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 6 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), prop.getProperty("cust_zipcode"), "Test Step - 6 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), prop.getProperty("cust_city"), "Test Step - 6 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_phoneNumber"), "Test Step - 6 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_Alt_phoneNumber"), "Test Step - 6 - Alt phone number is not displayed on phone order page");

            // Test Step - 7
            phoneorder.EnterReciFirstName(recifname);
            phoneorder.EnterReciLastName(recilname);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname, "Test Step - 3 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname, "Test Step - 3 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(recipientfulladdress1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), reciaddress1, "Test Step - 3 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), recizip, "Test Step - 3 - Recipient Zipcode is not matched recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), recicity, "Test Step - 3 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 3 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.SelectReciCountry(recicountry);
            phoneorder.EnterReciPhone(reciphone);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recilocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(18, 30);
            delayWithGivenTime(1000);

            //Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 9
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(itemcode, itemdescription);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), "rrd", "Test Step - 9 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), "Red Rose Deluxe", "Test Step - 9 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 9 - Item quantity is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            delayWithGivenTime(2000);

            // Test Step - 10
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymenttype);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getDisplayedPaymentTypeSelectedOption(), paymenttype, "Test Step - 10 - Selected Payment type is not displayed on phone order page payment section");
            delayWithGivenTime(1000);
            phoneorder.EnterCashAmount();
            delayWithGivenTime(1000);
            phoneorder.SelectCashRegistry_On_CashPaymentType(cash_registry);

            // Test Step - 12
            total_amount = phoneorder.getGrandTotalAmount();
            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 12 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 13
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 13 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            // Test Step - 14
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 14 - Dashboard order page is not displayed");

            // Test Step - 15
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 12 - Respective Invoice number is not displayed on all orders page");

            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 12 - Searched Invoice number is not displayed on all orders page");
            delayWithGivenTime(2000);

//========================= Above are pre - requisite steps for this test case =============================================

            // Test Step - 3
            dashboardorder.Click_ActionMenu_For_Respective_Invoice(invoiceNumber);
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.Verify_Action_submenu_is_displayed(), "Test Step - 3 - Action sub menus are not displayed when clicks the action menu in order page");
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.Verify_Respective_Submenu_is_displayed(action_submenu), "Test Step - 3 - Update Order sub menu is not displayed when clicks the action menu in order page");

            // Test Step - 4
            dashboardorder.Click_UpdateOrder_Submenu();
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.Verify_UpdateOrder_Popup_IsDisplayed(), "Test Step - 4 - Update order popup is not displayed when clicks the cancel order action submenu row 1");

            // Test Step - 5
            dashboardorder.Click_Add_New_Product_Plus_Icon();
            delayWithGivenTime(2000);
            dashboardorder.Enter_ItemCode2_In_Row2_Product_UpdateOrder(updated_itemcode);
            delayWithGivenTime(2000);
            dashboardorder.Enter_ItemDescription_Row2_Product_UpdateOrder(updated_itemdescription);
            dashboardorder.Enter_Quantity_Row2_Product_UpdateOrder(updated_itemquantity);
            dashboardorder.Enter_Price_Row2_Product_UpdateOrder(updated_itemprice);

            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_entered_itemcode2_in_row2_product_updateorder(), updated_itemcode, "Test Step - 5 - Entered Item code is not displayed on update order popup");
            softassert.assertEquals(dashboardorder.get_entered_itemdescription2_in_row2_product_updateorder(), updated_itemdescription, "Test Step - 5 - Item description is not displayed on update order popup");
            softassert.assertEquals(dashboardorder.get_entered_quantity2_in_row2_product_updateorder(), updated_itemquantity, "Test Step - 5 - Item quantity is not displayed on update order popup");
            softassert.assertEquals(dashboardorder.get_entered_price2_in_row2_product_updateorder(), updated_itemprice, "Test Step - 5 - Item price is not displayed on update order popup");

            // Test Step - 6
//            dashboardorder.click_Row1_Product_Cross_Icon_UpdateOrderPopup();
//            delayWithGivenTime(500);
//            softassert.assertEquals(dashboardorder.get_Remove_Product_Confirmation_Message_On_UpdateOrderPopup(), "Are you sure you want to remove this Product?", "Test Step - 6 - Remove product confirmation message popupis not displayed on update order popup after clicks the remove product cross icon");
//            dashboardorder.click_Remove_Product_Confirmation_No_Button_On_UpdateOrderPopup();
//            delayWithGivenTime(1000);
//            softassert.assertEquals(dashboardorder.get_entered_itemcode1_in_row1_product_updateorder(), "rrd", "Test Step - 6 - Row 1 Item code as rrd is not displayed on update order popup after clicks the no button on remove product confirmation popup");
//            softassert.assertEquals(dashboardorder.get_entered_description1_in_row1_product_updateorder(), "Red Rose Deluxe", "Test Step - 6 - Row 1 Item description as Red Rose Deluxe is not displayed on update order popup after clicks the no button on remove product confirmation popup");
//            softassert.assertEquals(dashboardorder.get_entered_quantity1_in_row1_product_updateorder(), "1", "Test Step - 6 - Row 1 Item quantity as 1 is not displayed on update order popup after clicks the no button on remove product confirmation popup");
//            softassert.assertEquals(dashboardorder.get_entered_price1_in_row1_product_updateorder(), "299", "Test Step - 6 - Row 1 Item price as 299 is not displayed on update order popup after clicks the no button on remove product confirmation popup");
//            softassert.assertEquals(dashboardorder.get_entered_extprice1_in_row1_product_updateorder(), "299", "Test Step - 6 - Row 1 Item extended price as 299 is not displayed on update order popup after clicks the no button on remove product confirmation popup");


            // Test Step - 7
            dashboardorder.click_Row1_Product_Cross_Icon_UpdateOrderPopup();
            delayWithGivenTime(500);
            softassert.assertEquals(dashboardorder.get_Remove_Product_Confirmation_Message_On_UpdateOrderPopup(), "Are you sure you want to remove this Product?", "Test Step - 6 - Remove product confirmation message popupis not displayed on update order popup after clicks the remove product cross icon");
            dashboardorder.click_Remove_Product_Confirmation_Yes_Button_On_UpdateOrderPopup();
            delayWithGivenTime(1000);
//            softassert.assertEquals(dashboardorder.get_entered_itemcode1_in_row1_product_updateorder(), updated_itemcode, "Test Step - 6 - Row 1 Item code as rrd is not displayed on update order popup after clicks the no button on remove product confirmation popup");
//            softassert.assertEquals(dashboardorder.get_entered_description1_in_row1_product_updateorder(), updated_itemdescription, "Test Step - 6 - Row 1 Item description as Red Rose Deluxe is not displayed on update order popup after clicks the no button on remove product confirmation popup");
//            softassert.assertEquals(dashboardorder.get_entered_quantity1_in_row1_product_updateorder(), updated_itemquantity, "Test Step - 6 - Row 1 Item quantity as 1 is not displayed on update order popup after clicks the no button on remove product confirmation popup");
//            softassert.assertEquals(dashboardorder.get_entered_price1_in_row1_product_updateorder(), updated_itemprice, "Test Step - 6 - Row 1 Item price as 299 is not displayed on update order popup after clicks the no button on remove product confirmation popup");
//            softassert.assertEquals(dashboardorder.get_entered_extprice1_in_row1_product_updateorder(), updated_itemprice, "Test Step - 6 - Row 1 Item extended price as 299 is not displayed on update order popup after clicks the no button on remove product confirmation popup");

            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_entered_itemcode2_in_row2_product_updateorder(), updated_itemcode, "Test Step - 5 - Entered Item code is not displayed on update order popup");
            softassert.assertEquals(dashboardorder.get_entered_itemdescription2_in_row2_product_updateorder(), updated_itemdescription, "Test Step - 5 - Item description is not displayed on update order popup");
            softassert.assertEquals(dashboardorder.get_entered_quantity2_in_row2_product_updateorder(), updated_itemquantity, "Test Step - 5 - Item quantity is not displayed on update order popup");
            softassert.assertEquals(dashboardorder.get_entered_price2_in_row2_product_updateorder(), updated_itemprice, "Test Step - 5 - Item price is not displayed on update order popup");


            // Test Step - 8
            delayWithGivenTime(3000);
            dashboardorder.Select_PaymentReason_UpdateOrder_Popup(payment_reason);
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.get_selected_paymentreason_updateorder_popup(), payment_reason, "Test Step - 6 - Update order reason is not displayed on update order popup");

            // Test Step - 7
            delayWithGivenTime(1000);
            balance_amount = dashboardorder.Get_Refund_Balance_Amount();
            softassert.assertEquals(dashboardorder.get_Displayed_refund_amount(), balance_amount, "Test Step - 7 - Refund amount is not displayed on update order popup");

            // Test Step - 8
            dashboardorder.Click_UpdateOrder_Button_InUpdateOrderPopup();
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.Verify_Update_Order_Popup_IsDisplayed(), "Test Step - 8 - Update order popup is not displayed when clicks the cancel order action submenu row 1");

            // Test Step - 9
            dashboardorder.Click_YesButton_CancelOrder_Popup();
            delayWithGivenTime(2000);

            // Test Step - 10
            softassert.assertEquals(dashboardorder.Verify_Success_Toaster_Message_Text(), "Order Updated Successfully", "Test Step - 10 - Success toaster message is not displayed when clicks the yes button on cancel order popup");
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.Verify_OrderUpdatedSuccessfully_Popup_IsDisplayed(), "Order Updated Successfully", "Test Step - 10 - Order updated Successfully popup is not displayed");

            // Test Step - 11
            delayWithGivenTime(1000);
            getDriver().navigate().refresh();
            delayWithGivenTime(2000);
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), "New", "Test Step - 12 - Cancelled order status is not displayed on order page after clicking the yes button on cancel order popup");

            // Test Step - 12
            dashboardorder.clickInvoiceNumber_On_TableGrid_AllOrdersPage(invoiceNumber);
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_OrderUpdated_ActiviesTab().contains("Payment Processed"), true, "Test Step - 12 - Payment Processed label message is not displayed on activities tab order page");
            softassert.assertEquals(dashboardorder.get_paymentAmount_on_activitiesTab().contains(balance_amount), true, "Test Step - 12 - Payment Amount is not displayed on activities tab order page");
            softassert.assertEquals(dashboardorder.get_OrderUpdated_ActiviesTab().contains("Payment Method: Cash"), true, "Test Step - 12 - Payment Method as Cash message is not displayed on activities tab order page");
            softassert.assertEquals(dashboardorder.get_OrderUpdated_ActiviesTab().contains("Reason: " + dashboardorder.get_reason_on_activitiesTab()), true, "Test Step - 12 - Reason message is not displayed on activities tab order page");

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