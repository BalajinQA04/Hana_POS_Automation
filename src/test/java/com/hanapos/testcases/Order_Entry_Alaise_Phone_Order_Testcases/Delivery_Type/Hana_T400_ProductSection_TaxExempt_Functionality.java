package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Delivery_Type;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import java.util.Locale;

public class Hana_T400_ProductSection_TaxExempt_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    String invoiceNumber;
    Faker faker = new Faker(new Locale("en-US"));
    String recifname1;
    String recilname2;
    String reci_full_address1;
    String reci_phone_number1;
    String reci_phone_number2;
    String floor_number;

    @Epic("Phone Order Module - Delivery Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T400_ProductSection_TaxExempt_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T400_ProductSection_TaxExempt_Functionality_Test ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2: Hana dashboard page is not displayed");
            logger.info("User navigated to hana dashboard page");
          /*  dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));
*/
            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3 : Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3 : Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            softassert.assertTrue(phoneorder.Verify_ProductSection_Appears_OnPhoneorder(), "Test Step - 5 - Product section is not displayed on phoneorder page");

            // Test Step - 6
            phoneorder.ClickdeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_OnDelivery_TypeOnPhoneOrderPage(), "#676a6c", "Test Step - 6 - Delivery type is not highlighted as blue color");

            // Test Step - 7
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            softassert.assertTrue(phoneorder.VerifyCustSectionAppears(), "Test Step - 7 - Customer section is not displayed on phone order page");
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("cust_firstName"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), "Abish", "Test Step - 7 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), "David", "Test Step - 7 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), "Hana_Sisterchicks", "Test Step - 7 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), "hanaposqateam@gmail.com", "Test Step - 7 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "3402 Park Blvd", "Test Step - 7 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 7 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "92103", "Test Step - 7 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "San Diego", "Test Step - 7 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 7 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 7 - Alt phone number is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 8
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

            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(1, 50);
            phoneorder.Select_DeliveryOnTime_Dropdown("Around");
            phoneorder.Select_Zone_OnRecipientSection("Automation Zone");
            delayWithGivenTime(1000);

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
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), prop.getProperty("product_itemcode1"), "Test Step - 11 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), prop.getProperty("product_description1"), "Test Step - 11 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 11 - Item quantity is not displayed on phone order page product details section");

            if (phoneorder.getUnitPriceOnProdDetails() == "299.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 11 - Item price is not displayed on phone order page product details section");
            }

            delayWithGivenTime(2000);

            // Test Step - 12
            softassert.assertTrue(phoneorder.Verify_ProductSuggestions_Appears(prop.getProperty("product_itemcode1")), "Test Step - 12 - In Item code row 1 on product section autosuggestions are not displayed");
            delayWithGivenTime(2000);
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), prop.getProperty("product_itemcode1"), "Test Step - 12 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), prop.getProperty("product_description1"), "Test Step - 12 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 12 - Item quantity is not displayed on phone order page product details section");

            if (phoneorder.getUnitPriceOnProdDetails() == "299.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 12 - Item price is not displayed on phone order page product details section");
            } else if (phoneorder.getUnitPriceOnProdDetails() == "309.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "309.00", "Test Step - 12 - Item price is not displayed on phone order page product details section");
            }


            // Test Step - 13
            softassert.assertTrue(phoneorder.Verify_Item_Row1_ProdDetails_TaxableCheckBox_IsChecked(), "Test Step - 13 - Item row 1 on product section taxable checkbox is not checked as default");

            // Test Step - 14
            phoneorder.Select_ProductTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(phoneorder.get_selected_ProductTaxType(), prop.getProperty("product_taxtype"), "Test Step - 14 - Selected product tax type is not displayed on phone order page");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_TaxAmount_PaymentSection(), "0.00", "Test Step - 14 - Tax amount is not displayed on phone order page payment section");

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

            phoneorder.Select_ProductTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(phoneorder.get_selected_ProductTaxType(), prop.getProperty("product_taxtype"), "Test Step - 16 - Selected product tax type is not displayed on phone order page");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_TaxAmount_PaymentSection(), "0.00", "Test Step - 16 - Tax amount is not displayed on phone order page payment section");

            // Test Step - 17
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.validate_grandTotal_OnPaymentSection(), phoneorder.getGrandTotalAmount(), "Test Step - 17 - Grand total is not displayed on phone order page payment section");

            // Test Step - 18
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type_invoice"));
            delayWithGivenTime(1000);
            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(1000);

            // Test Step - 19
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 19 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_taxAmountOnOrderconfirmation_Popup(), "0.00", "Test Step - 19 - Tax amount on order confirmation page is not displayed");

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
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 15 - Respective Invoice number is not displayed on order page");
            delayWithGivenTime(1000);

            // Test Step - 24
            dashboardorder.clickInvoiceNumber_On_TableGrid_AllOrdersPage(invoiceNumber);

            // Test Step - 25
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.get_SalesTax_On_DeliveryPopup_Invoice(), "$0.00", "Test Step - 25 - Sales tax on delivery popup invoice is not displayed");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}