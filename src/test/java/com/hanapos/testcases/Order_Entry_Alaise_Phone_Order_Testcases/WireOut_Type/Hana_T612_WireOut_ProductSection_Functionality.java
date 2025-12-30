package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireOut_Type;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import java.util.Locale;

public class Hana_T612_WireOut_ProductSection_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    String invoiceNumber;

    @Epic("Phone Order Module - Wire out Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T612_WireOut_ProductSection_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T612_WireOut_ProductSection_Functionality_Test ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 - Login page is not displayed");
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
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3 - Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3 - Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            softassert.assertTrue(phoneorder.Verify_ProductSectionLabel_Appears(), "Test Step - 5 - product section is not appears on phoneorder page");

            // Test Step - 6
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Click_WireOut_DeliveryType_OnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_On_WireOut_PhoneOrderPage(), "#2f9bc8", "Test Step - 5 - Wire out Delivery type is not highlighted in blue color");

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

            // Test Step - 8
            Faker faker = new Faker(new Locale("en-US"));
            String recifname1;
            String recilname2;
            String reci_phone_number1;
            String reci_phone_number2;
            String floor_number;
            String reci_full_address1;

            recifname1 = faker.name().firstName();
            recilname2 = faker.name().lastName();
            phoneorder.EnterReciFirstName(recifname1);
            phoneorder.EnterReciLastName(recilname2);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname1, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname2, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1("814 Missouri Avenue, Washington, MO");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "814 Missouri Ave", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63090", "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Washington", "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection();
            phoneorder.Select_DeliveryOnTime_Dropdown(prop.getProperty("deliveryOnTime"));
            delayWithGivenTime(1000);

            // Test Step - 9
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 9 - Selected Occasion is not displayed on phone order page order details section");
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase("Happy Birthday! Hope you have an amazing day!"), true, "Test Step - 9 -Entered Short code is not displayed on phone order page order details section");

            // Test Step - 10
            softassert.assertTrue(phoneorder.Verify_ProductSuggestions_Appears(prop.getProperty("product_itemcode1")), "Test Step - 10 - Product autosugestion are not displayed");

            // Test Step - 11
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(prop.getProperty("product_itemcode1"), "rrd-Red Rose Deluxe");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), "rrd", "Test Step - 11 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), "Red Rose Deluxe", "Test Step - 11 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 11 - Item quantity is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 11 - Item price is not displayed on phone order page product details section");
            delayWithGivenTime(2000);

            // Test Step - 12
            phoneorder.Click_ProductRemoveIcon_Row1();
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_ProductRow1Details_Removed(), "Test Step - 12 - Row 1 added products is not removed on product section");

            // Test Step - 13
            softassert.assertTrue(phoneorder.Verify_AutosugestionsList_OnItemDescription_Appears("Red Rose"), "Test Step - 10 - Product autosugestion are not displayed");

            // Test Step - 14
            phoneorder.SearchAndSelect_WithItemDescription("Red Rose", prop.getProperty("product_description1"));
            softassert.assertEquals(phoneorder.get_ItemCodeRow2_ProductDetails(), "rrd", "Test Step - 14 - Item code row 2 on product details section");
            softassert.assertEquals(phoneorder.get_ItemDescriptionRow2_ProductDetails(), "Red Rose Deluxe", "Test Step - 14 - Item description row 2 on product details section");
            softassert.assertEquals(phoneorder.get_ItemQtyRow2_ProductDetails(), "1", "Test Step - 14 - Item quantity row 2 on product details section");
            softassert.assertEquals(phoneorder.get_ItemPriceRow2_ProductDetails(), "299.00", "Test Step - 14 - Item price row 2 on product details section");

            // Test Step - 15
            phoneorder.Click_AddIcon_Row3();
            softassert.assertTrue(phoneorder.Verify_Row4_ProductDetailsItemcode_Appears(), "Test Step - 15 - New row is added on product details table grid");

            // Test Step - 16
            phoneorder.EnterSpecialInstructions_ProductDetailsSection(prop.getProperty("special_instruction"));
            softassert.assertEquals(phoneorder.get_SpecialInstructions_ProductDetailsSection(), "Automation Test special instruction", "Test Step - 16 - Special Instruction on product details section");

            // Test Step - 17
            phoneorder.EnterDriverInstructions_ProductDetailsSection(prop.getProperty("driver_instruction"));
            softassert.assertEquals(phoneorder.get_DriverInstructions_ProductDetailsSection(), "Automation Test Driver Instruction", "Test Step - 17 - Driver Instruction on product details section");

            // Test Step - 18
            phoneorder.EnterCustomerPrivateNotesInstructions_ProductDetailsSection(prop.getProperty("customer_private_notes"));
            softassert.assertEquals(phoneorder.get_CustomerPrivateNotesInstructions_ProductDetailsSection(), "Automation Test Private Notes", "Test Step - 17 - Driver Instruction on product details section");
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Customer Notes updated successfully", "Test Step - 18 - Success toast message text as customer notes updated successfully is not displayed properly");
            phoneorder.click_Close_Icon_On_Toaster_Message();
            phoneorder.click_Close_Icon_On_Toaster_Message();

            //Test Step - 19
            phoneorder.Select_ProductTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(phoneorder.get_selected_ProductTaxType(), "Tax Exemption", "Test Step - 19 - Selected product tax type is not displayed on phone order page");

            // Test Step - 20
            phoneorder.Enter_ProductTaxId(prop.getProperty("product_taxid"));
            softassert.assertEquals(phoneorder.get_ProductTaxId(), "1234", "Test Step - 20 - Entered Product tax id is not displayed on phone order page");

            // Test Step - 21
            phoneorder.Select_ProdSourceCode(prop.getProperty("product_sourcecode"));
            softassert.assertEquals(phoneorder.get_selected_ProdSourceCode(), "Friend", "Test Step - 21 - Selected Product source code is not displayed on phone order page");

            // Test Step - 22
            phoneorder.Select_ProdCustType(prop.getProperty("product_custtype"));
            softassert.assertEquals(phoneorder.get_selected_ProdCustType(), "Retail", "Test Step - 22 - Selected product customer type is not displayed on phoneorder");

            // Test Step - 23
//            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type_invoice"));
//            delayWithGivenTime(1000);
//            phoneorder.Select_WireOut_PaymentMethod(prop.getProperty("wireout_payment_method"));
//            phoneorder.Enter_WireoutFlorist(prop.getProperty("wireout_choose_florist"));
//            phoneorder.set_Amount_wireout_paymentsection();
//            phoneorder.ClickPlaceOrderButton();
//            delayWithGivenTime(1000);


            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), "Cash", "Test Step - 23 - Selected payment type is not displayed");

            phoneorder.Enter_CashPaymentType_Amount(); //(phoneorder.getGrandTotalAmount());
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_Entered_CashPaymentType_Amount(), phoneorder.get_Entered_CashPaymentType_Amount(), "Test Step - 23 - Entered more than cash payment amount is not allowed on the payment section cash type pay amount field");

            phoneorder.SelectCashRegistry_On_CashPaymentType(prop.getProperty("payment_cash_registry"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.Verify_Change_GivenBackTo_Customer_amount_IsDisplayed(), "100.00", "Test Step - 23 - Change given back to customer amount label is not displayed under the cash payment type select registered dropdown");
            softassert.assertEquals(phoneorder.get_Selected_CashRegistry(), "Cash Register2", "Test Step - 23 - Selected cash payment registered is not displayed");

            phoneorder.Select_WireOut_PaymentMethod(prop.getProperty("wireout_payment_method"));

            phoneorder.Click_SearchIcon_OnWireOut_Info();
            softassert.assertTrue(phoneorder.verify_chooseFlorist_popup_isDisplayed(), "Test Step - 23: Choose Florist popup header is not displayed");
            phoneorder.Search_and_Select_with_Zipcode_on_ChooseFlorist_Popup(prop.getProperty("wireout_fsn_zipcode"));
            phoneorder.Click_SearchButton_on_ChooseFlorist_Popup();
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.verify_based_on_zipcode_searchresult_listofflorist_is_Appears_on_chooseflorist_popup(), "Test Step - 23: Based on zipcode search results on choose florist popup it not displayed florist");
            phoneorder.Click_chooseflorist_popup_displayed_first_search_result();

            softassert.assertEquals(phoneorder.get_displayed_wireout_florist_zipcode(), prop.getProperty("wireout_chooseflorist_fsn_text"), "Test Step - 23: Wire out florist zipcode is not displayed");
            phoneorder.set_Amount_wireout_paymentsection();

            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 23 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 24
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 24 - Order confirmation page is not displayed");
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            // Test Step - 25
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 16 - Dashboard order page is not displayed");

            // Test Step - 26
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 15 - Respective Invoice number is not displayed on order page");
            delayWithGivenTime(2000);
            dashboardorder.clickInvoiceNumber_On_TableGrid_AllOrdersPage(invoiceNumber);

            // Test Step - 27
            softassert.assertEquals(dashboardorder.get_ProductDescription_OnInvPopup(), "rrd - Red Rose Deluxe", "Test Step - 27 - Product Item description on invoice popup on order page compared with added product on phone order page are not matched");

            // Test Step - 28
            softassert.assertEquals(dashboardorder.get_sourcecode_displayed_OnInvPopup(), "Friend", "Test Step - 28 - Displayed source code on invoice popup on order page compared with added product on phone order page are not matched");

            // Test Step - 29
            softassert.assertEquals(dashboardorder.get_SpecialInstruction_displayed_OnInvPopup(), "Automation Test Special Instruction", "Test Step - 29 - Special instruction displayed on invoice popup on order page compared with added product section on phone order page are not matched ");

            // Test Step - 30
            softassert.assertEquals(dashboardorder.get_DriverInstruction_displayed_OnInvPopup(), "Automation Test Driver Instruction", "Test Step - 30 - Driver instruction displayed on invoice popup on order page compared with added product section on phone order page are not matched ");

            // Test Step - 31
            softassert.assertEquals(dashboardorder.get_Displayed_DeliveryType_OnInvPopup(), "Wire Out Phone Order", "Test Step - 31 - Displayed delivery type on invoice popup on order page compared with phoneorder selected delivery type is not matched");

        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}