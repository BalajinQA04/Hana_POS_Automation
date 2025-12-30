package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireIn_Type;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T494_ProductSection_Tax_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    String invoiceNumber;
    String taxtypevalue;
    public static final String dataSheetName = "Hana_T455";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Phone Order Module - Wire In Type")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T494_ProductSection_Tax_Functionality_Test(String salesperson, String FirstName, String LastName, String Shopcode, String ShopName, String WireIn_Method, String Order_Number, String PhoneNumber,
                                                                         String Email, String custaddress1, String Comments, String custzip, String custphone, String recifname, String recilname, String reciaddress1, String reciaddress2, String recizip,
                                                                         String recicity, String recicountry, String reciphone, String recilocation, String occasion, String searchandselectitemcode, String paymenttype, String cashregistry) {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T494_ProductSection_Tax_Functionality_Test ****");
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
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Hana dashboard page is not displayed");
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
            softassert.assertTrue(phoneorder.Verify_ProductSection_Appears_OnPhoneorder(), "Test Step - 5 - Product section is not displayed on phoneorder page");

            // Test Step - 6
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 6 - Shop name is not displayed in the WireIn section of phone order page");

            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            softassert.assertEquals(phoneorder.get_SelectedSalesPersonOn_PhoneOrderEntryPage(), prop.getProperty("salesperson"), "Selected Sales Person " + prop.getProperty("salesperson") + " is not displayed on phone order page");
            phoneorder.Click_WireIn_DeliveryType_OnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnWireInTypeOnPhoneOrderPage(), "#676a6c", "Wire In as delivery type is not highlighted in blue color");

            // Test Step - 7
            phoneorder.Enter_WireIn_Fname(prop.getProperty("wirein_cust_firstName"));
            phoneorder.Enter_WireIn_Lname(prop.getProperty("wirein_cust_lastName"));
            phoneorder.Enter_WireIn_ShopCode(prop.getProperty("wirein_shopcode"));
            phoneorder.Enter_WireIn_ShopName(prop.getProperty("wirein_shopname"));
            phoneorder.Select_WireInMethod(prop.getProperty("wirein_type"));
            phoneorder.Enter_WireIn_OrderNumber(prop.getProperty("wirein_ordernumber"));
            phoneorder.Enter_WireIn_PhoneNumber(prop.getProperty("wirein_phonenumber"));
            phoneorder.Enter_WireIn_Email(prop.getProperty("wirein_email"));
            phoneorder.Enter_WireIn_ShopAddress(prop.getProperty("wirein_shopaddress"));
            phoneorder.Enter_WireIn_Comments(prop.getProperty("wirein_comments"));

            softassert.assertEquals(phoneorder.get_Entered_WireIn_Fname(), "Abish", "Test Step - 7 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Lname(), "David", "Test Step - 7 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopCode(), "1100", "Test Step - 7 - Entered WireIn shop code is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopName(), "Automation Test Flower Shop", "Test Step - 7 - Entered WireIn shop name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Selected_WireInMethod(), "FSN", "Test Step - 7 - Selected WireIn method is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_OrderNumber(), "1122FSN33", "Test Step - 7 - Entered WireIn order number is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_PhoneNumber(), "996-955-0706", "Test Step - 7 - Entered phone number on customer details popup is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Email(), "hanaposqateam@gmail.com", "Test Step - 7 - Entered shop Email id on customer details popup is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopAddress(), "2715 35th Avenue Greeley, CO, USA", "Test Step - 7 - Entered shop address  is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Comments(), "Test Automation Wire In Order", "Test Step - 7 - Shop Wire In Comments is not displayed on phone order page");

            // Test Step - 8
            phoneorder.EnterReciFirstName(recifname);
            phoneorder.EnterReciLastName(recilname);
            phoneorder.EnterReciAddress1(reciaddress1);
            phoneorder.EnterReciAddress2(prop.getProperty("Reci_Address1_2"));
            phoneorder.EnterReciZipcode(recizip);
            delayWithGivenTime(1000);
            phoneorder.SelectReciCountry(recicountry);
            phoneorder.EnterReciPhone(reciphone);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recilocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), "Abish", "Test Step - 7 - Entered first name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "David", "Test Step - 7 - Entered last name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress1(), "3402 Park Blvd", "Test Step - 7 - Entered address 1 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "112 Penny Ct,", "Test Step - 7 - Recipient address 2 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "92103", "Test Step - 7 - Entered zipcode is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "San Diego", "Test Step - 7 - Entered city is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), "956-655-0756", "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), "Church", "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");

            // Test Step - 9
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            phoneorder.EnterViewShortCode();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 9 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertTrue(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), "Test Step - 9 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 10
            softassert.assertTrue(phoneorder.Verify_ProductSuggestions_Appears(prop.getProperty("product_itemcode1")), "Test Step - 10 - In Item code row 1 on product section autosuggestions are not displayed");

            // Test Step - 11
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), "rrd", "Test Step - 11 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), "Red Rose Deluxe", "Test Step - 11 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 11 - Item quantity is not displayed on phone order page product details section");

            if (phoneorder.getUnitPriceOnProdDetails() == "299.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 11 - Item price is not displayed on phone order page product details section");
            } else if (phoneorder.getUnitPriceOnProdDetails() == "309.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "309.00", "Test Step - 11 - Item price is not displayed on phone order page product details section");
            }

            delayWithGivenTime(2000);

            // Test Step - 12
            softassert.assertTrue(phoneorder.Verify_ProductSuggestions_Appears(prop.getProperty("product_itemcode1")), "Test Step - 12 - In Item code row 1 on product section autosuggestions are not displayed");
            delayWithGivenTime(2000);
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), "rrd", "Test Step - 12 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), "Red Rose Deluxe", "Test Step - 12 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 12 - Item quantity is not displayed on phone order page product details section");

            if (phoneorder.getUnitPriceOnProdDetails() == "299.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 12 - Item price is not displayed on phone order page product details section");
            } else if (phoneorder.getUnitPriceOnProdDetails() == "309.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "309.00", "Test Step - 12 - Item price is not displayed on phone order page product details section");
            }

            // Test Step - 13
            softassert.assertTrue(phoneorder.Verify_Item_Row1_ProdDetails_TaxableCheckBox_IsChecked(), "Test Step - 13 - Item row 1 on product section taxable checkbox is not checked as default");

            // Test Step - 14
            phoneorder.Select_ProductTaxType(prop.getProperty("product_taxtype_withtax"));
            softassert.assertEquals(phoneorder.get_selected_ProductTaxType(), prop.getProperty("product_taxtype_withtax"), "Test Step - 14 - Selected product tax type is not displayed on phone order page");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_TaxAmount_PaymentSection(), phoneorder.get_actual_calculation_taxtype(), "Test Step - 14 - Tax amount is not displayed on phone order page payment section");

            // Test Step- 15
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.validate_grandTotal_OnPaymentSection(), phoneorder.getGrandTotalAmount(), "Test Step - 15 - Grand total is not displayed on phone order page payment section");

            // Test Step - 16
            phoneorder.SearchandSelectItemcode2OnPhoneOrderPage(prop.getProperty("product_itemcode1"), prop.getProperty("prod_full_desc2"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode2OnPhoneOrderPage(), "rrd", "Test Step - 16 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription2OnPhoneOrderPage(), "Red Rose Premium", "Test Step - 16 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty2OnPhoneOrderPage(), "1", "Test Step - 16 - Item quantity is not displayed on phone order page product details section");

            if (phoneorder.getUnitPriceOnProdDetails() == "399.00") {
                softassert.assertEquals(phoneorder.getUnitPrice2OnProdDetails(), "399.00", "Test Step - 16 - Item price is not displayed on phone order page product details section");
            } else if (phoneorder.getUnitPriceOnProdDetails() == "409.00") {
                softassert.assertEquals(phoneorder.getUnitPrice2OnProdDetails(), "409.00", "Test Step - 16 - Item price is not displayed on phone order page product details section");
            }

            phoneorder.Select_ProductTaxType(prop.getProperty("product_taxtype_withtax"));
            softassert.assertEquals(phoneorder.get_selected_ProductTaxType(), prop.getProperty("product_taxtype_withtax"), "Test Step - 16 - Selected product tax type is not displayed on phone order page");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_TaxAmount_PaymentSection(), phoneorder.get_actual_calculation_taxtype(), "Test Step - 16 - Tax amount is not displayed on phone order page payment section");

            // Test Step - 17
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.validate_grandTotal_OnPaymentSection(), phoneorder.getGrandTotalAmount(), "Test Step - 17 - Grand total is not displayed on phone order page payment section");
            taxtypevalue = phoneorder.get_actual_calculation_taxtype();

            // Test Step - 18
            phoneorder.ClickPlaceOrderButton();

            // Test Step - 19
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 19 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_taxAmountOnOrderconfirmation_Popup(), phoneorder.get_actual_calculation_taxtype(), "Test Step - 19 - Tax amount on order confirmation page is not displayed");

            // Test Step - 20
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 20 - Order confirmation page is not displayed");
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            // Test Step - 21
            softassert.assertEquals(orderconfirmationpage.get_Taxable(), "Yes", "Test Step - 21 - Product 1 Taxable Yes is not displayed on order confirmation page");
            softassert.assertEquals(orderconfirmationpage.get_Taxable2(), "Yes", "Test Step - 21 - Product 2 Taxable Yes is not displayed on order confirmation page");

            // Test Step - 22
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 22 - Dashboard order page is not displayed");

            // Test Step - 23
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.Validate_WireInOrderType_Appears_OnOrderPage("FSN Order", "Wire In", "FSN"), "Test Step - 15 - Wire in order type is not displayed on order page");
            delayWithGivenTime(1000);

            // Test Step - 24
            dashboardorder.Click_WireIn_OrderType_InvoiceNumber_On_OrderPage("FSN Order", "Wire In", "FSN");

            // Test Step - 25
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.get_SalesTax_On_DeliveryPopup_Invoice(), "$0" + taxtypevalue, "Test Step - 25 - Sales tax on delivery popup invoice is not displayed");

        } catch (Exception e) {
            e.printStackTrace();
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}