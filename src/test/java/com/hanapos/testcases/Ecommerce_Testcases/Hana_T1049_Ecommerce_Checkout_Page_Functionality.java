package com.hanapos.testcases.Ecommerce_Testcases;

import com.github.javafaker.Faker;
import com.hanapos.ecommerce_pageObjects.*;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.Random;

import static com.hanapos.ecommerce_pageObjects.Ecommerce_Checkout_Page.convertDisplayedDateTo_MMddyyyy;

public class Hana_T1049_Ecommerce_Checkout_Page_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private Ecommerce_HomePage ecommerce_homePage;
    private Ecommerce_SearchResults_Page searchResultsPage;
    private Ecommerce_ProductDetails_Page productDetailsPage;
    private Ecommerce_Add_On_Page addOnPage;
    private Ecommerce_Order_Preview_Page orderPreviewPage;
    private Ecommerce_Checkout_Page checkoutPage;
    private Ecommerce_Order_Confirmation_Page order_confirmationPage;
    Faker faker = new Faker(new Locale("en-US"));
    String recifname1;
    String recilname2;
    String reci_full_address1;
    String reci_phone_number1;
    String reci_phone_number2;
    String floor_number;
    String delivery_Date;
    public static LoggerUtil logger_Util;

    @Epic("Ecommerce - Checkout Page")
    @Description("Ecommerce Bestflorist website - Checkout Page End to End Functionality")
    @Test(enabled = true, groups = {"Smoke", "Regression", "Sanity"})
    public void Validate_Hana_T1049_Ecommerce_Checkout_Page_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");

            lp.EnterUserName(prop.getProperty("bestuname"));
            lp.EnterPassword(prop.getProperty("bestpass"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("bestuname"), "Test Step - 1: Entered username is not matching with expected username");
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("bestpass"), "Test Step - 1: Entered password is not matching with expected password");
            lp.ClickLoginButton();

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 1: Hana dashboard page is not displayed");
            dashboard.SelectShopNameDropDown(prop.getProperty("bestshopname"));
            softassert.assertEquals(dashboard.get_selected_shopname_from_hanadashboard(), prop.getProperty("bestshopname"), "Test Step - 1: Selected shopname is not matching with expected shopname");
            delayWithGivenTime(2000);
            dashboard.Click_ProfileIcon_On_HanaDashBoardPage_And_Clicks_User_Website();
            switchToWindowbyIndex(1);

            ecommerce_homePage = new Ecommerce_HomePage();
            delayWithGivenTime(3000);
            softassert.assertEquals(ecommerce_homePage.get_HomePage(), prop.getProperty("ecommerce_checkoutpage_url"), "Test Step - 1: Ecommerce home page url is not matching with expected url");

            // Test Step - 2
            ecommerce_homePage.Search_Product_In_GlobalSearch(prop.getProperty("ecommerce_product"));
            searchResultsPage = new Ecommerce_SearchResults_Page();
            softassert.assertTrue(searchResultsPage.Verify_Search_Result_Page_IsDisplayed(), "Test Step - 2: Search result page is not displayed");

            // Test Step - 3
            searchResultsPage.Click_Product_Displayed_On_Search_Result_Page();

            productDetailsPage = new Ecommerce_ProductDetails_Page();
            softassert.assertEquals(productDetailsPage.Verify_Product_Displayed_Description(), prop.getProperty("ecommerce_product"), "Test Step - 4: Product description is not matching with expected product description");

            // Test Step - 4
            productDetailsPage.Click_Product_Variant_As_Medium();
            softassert.assertEquals(productDetailsPage.Verify_Displayed_Product_Price(), prop.getProperty("product_variant_medium_price"), "Test Step - 4: Product variant is not matching with expected product variant");

            productDetailsPage.Click_Delivery_Tab();
            delayWithGivenTime(500);
            delivery_Date = productDetailsPage.get_First_Option_Displayed_Delivery_Date();
            // productDetailsPage.clickDeliveryDateEST(2);
            productDetailsPage.click_Delivery_Date_Button_First_Option();


            //  softassert.assertEquals(productDetailsPage.Validate_Delivery_Date_As_Current_Date_Displayed_In_Green_Colour(), "rgba(0, 128, 0, 1)", "Test Step - 4: Selected Delivery date is not highlighted in green colour");
            productDetailsPage.Enter_Zip_Code(prop.getProperty("ecommerce_zipcode"));
            productDetailsPage.Click_Check_Button();
            softassert.assertEquals(productDetailsPage.Verify_Zip_Code_Validation_Message(), "Delivery Available 77201", "Test Step - 4: Validation message is not matching with expected validation message");
            productDetailsPage.Click_Create_Order_Button();

            // Test Step - 5
            addOnPage = new Ecommerce_Add_On_Page();
            softassert.assertTrue(addOnPage.verify_add_on_page_title(), "Test Step - 5: Add - On page is not displayed");
            addOnPage.Click_add_on_chocolates_medium_checkbox();
            addOnPage.Click_continueToCheckoutBtn();

            orderPreviewPage = new Ecommerce_Order_Preview_Page();
            softassert.assertTrue(orderPreviewPage.Verify_OrderPreviewPage(), "Test Step - 5: Order preview page is not displayed");

            // Test Step - 6
            softassert.assertEquals(orderPreviewPage.get_product_item_and_variant(), "Balaji_Test_Product-Medium", "Test Step - 6: Product item and variant is not matching with expected product item and variant");
            softassert.assertEquals(orderPreviewPage.get_product_qty_and_price(), "1 × $500.00", "Test Step - 6: Product item and variant is not matching with expected product item and variant");
            softassert.assertEquals(orderPreviewPage.get_addon_product_item_and_variant_price(), "Chocolates - Medium Box add-on for Test_Automation", "Test Step - 6: Product item and variant is not matching with expected product item and variant");
            softassert.assertEquals(orderPreviewPage.get_addon_product_qty_and_price(), "1 × $19.99", "Test Step - 6: Product item and variant is not matching with expected product item and variant");
            softassert.assertEquals(orderPreviewPage.get_subtotal_on_order_preview_page(), "$" + orderPreviewPage.calculated_subtotal(), "Test Step - 6: Sub-Total price is not matching with expected total price");

            // Test Step - 7
            orderPreviewPage.Click_proceed_to_checkout_button();

            checkoutPage = new Ecommerce_Checkout_Page();
            softassert.assertTrue(checkoutPage.Verify_CheckoutPage(), "Test Step - 7: Checkout page is not displayed");

            // There is a configuration to enable the occasion field in checkout page
            checkoutPage.Select_Occasion(prop.getProperty("cart_occasion"));
//            delayWithGivenTime(1000);
//            softassert.assertEquals(checkoutPage.get_selected_occasion_on_cartTab_checkoutPage(), prop.getProperty("cart_occasion"), "Test Step - 7: Selected occasion is not matching with expected occasion");

            checkoutPage.Enter_Email_on_cartTab_checkoutPage(prop.getProperty("cart_email"));
            delayWithGivenTime(500);
            softassert.assertEquals(checkoutPage.get_entered_email_on_cartTab_checkoutPage(), prop.getProperty("cart_email"), "Test Step - 7: Entered email is not matching with expected email");

            checkoutPage.Enter_card_message_on_cartTab_checkoutPage(prop.getProperty("cart_card_message"));
            delayWithGivenTime(500);
            softassert.assertEquals(checkoutPage.get_entered_card_message_on_cartTab_checkoutPage(), prop.getProperty("cart_card_message"), "Test Step - 7: Entered card message is not matching with expected card message");

            checkoutPage.Click_cart_tab_Continue_toDelivery_PickUp_button();
            softassert.assertTrue(checkoutPage.verify_recipient_information_subtitle(), "Test Step - 7: Recipient information subtitle is not displayed");

            recifname1 = faker.name().firstName();
            recilname2 = faker.name().lastName();
            Random random = new Random();

            String[] allowedPrefixes = {"98", "94", "77", "78", "84"};
            String prefix = allowedPrefixes[random.nextInt(allowedPrefixes.length)];

            // Generate the remaining 8 digits
            String suffix = faker.numerify("#-###-####");

            reci_phone_number1 = prefix + suffix;

            reci_full_address1 = prop.getProperty("Full_Reci_Address1_1");

            checkoutPage.Enter_recipient_first_name(recifname1);
            delayWithGivenTime(500);
            softassert.assertEquals(checkoutPage.get_entered_firstname_on_recipient_information_section(), recifname1, "Test Step - 7: Entered recipient first name is not matching with expected recipient first name");

            checkoutPage.Enter_recipient_last_name(recilname2);
            delayWithGivenTime(500);
            softassert.assertEquals(checkoutPage.get_entered_lastname_on_recipient_information_section(), recilname2, "Test Step - 7: Entered recipient first name is not matching with expected recipient first name");

            checkoutPage.Enter_recipient_phone_number(reci_phone_number1);
            delayWithGivenTime(500);
            softassert.assertEquals(checkoutPage.get_entered_phone_number_on_recipient_information_section(), reci_phone_number1, "Test Step - 7: Entered recipient phone number is not matching with expected recipient phone number");

            checkoutPage.Search_and_select_with_BusinessName(prop.getProperty("ecommerce_business_name"));
            delayWithGivenTime(2000);
            softassert.assertEquals(checkoutPage.get_selected_country(), prop.getProperty("ecommerce_country"), "Test Step - 7: Selected country is not matching with expected country");
            softassert.assertEquals(checkoutPage.get_selected_state(), prop.getProperty("ecommerce_state"), "Test Step - 7: Selected state is not matching with expected city");
            softassert.assertEquals(checkoutPage.get_displayed_city(), prop.getProperty("ecommerce_city"), "Test Step - 7: Entered city is not matching with expected city");
            softassert.assertEquals(checkoutPage.get_displayed_zip_code(), prop.getProperty("ecommerce_recipient_zipcode"), "Test Step - 7: Entered zip code is not matching with expected zip code");

            checkoutPage.Enter_delivery_date(FutureDateEST(2));
            delayWithGivenTime(500);

            checkoutPage.select_Delivery_Location_Dropdown("Home");
            delayWithGivenTime(500);

//            if (checkoutPage.is_Expedited_Delivery_Fee_Dropdown_Displayed()) {
//                checkoutPage.select_Expedited_Delivery_Fee();
//            }

            //     delayWithGivenTime(1000);

            if (checkoutPage.Verify_Message_Popup_isDisplay()) {
                checkoutPage.close_Message_Popup_isDisplay();
            }

            checkoutPage.enter_Florist_Instructions("Test Automation - Florist Instructions");
            delayWithGivenTime(100);
            //  String expeditedDeliveryFee = checkoutPage.get_Displayed_Expedited_Delivery_Cost();
            //  delayWithGivenTime(2000);

            checkoutPage.Click_cart_tab_Continue_toDelivery_PickUp_button();
            softassert.assertEquals(checkoutPage.get_Displayed_Product_Total(), "$519.99", "Test Step - 7: Displayed product total is not matching with expected product total");
            //   softassert.assertEquals(checkoutPage.get_Displayed_Delivery_Cost(), "$ 20.00", "Test Step - 7: Displayed delivery cost is not matching with expected delivery cost");

            //   softassert.assertEquals(checkoutPage.get_Displayed_Expedited_Delivery_Cost(), "$13.00", "Test Step - 7: Displayed expedited delivery fee is not matching with expected expedited delivery fee");
            //   softassert.assertEquals(checkoutPage.get_Displayed_Grand_Total(), "$594.94", "Test Step - 7: Displayed grand total is not matching with expected grand total");


            softassert.assertTrue(checkoutPage.Verify_Payment_Information_Tab_isDisplayed(), "Test Step - 7: Payment information Tab is not displayed");

            checkoutPage.Select_Payment_Method(prop.getProperty("ecommerce_payment_type"));
            softassert.assertEquals(checkoutPage.get_selected_payment_method(), prop.getProperty("ecommerce_payment_type"), "Test Step - 7: Selected payment method is not matching with expected payment method");

            checkoutPage.Enter_Name_of_Card_on_Payment_Info(prop.getProperty("ecommerce_name_on_card"));
            softassert.assertEquals(checkoutPage.get_entered_name_of_card_on_Payment_Info(), prop.getProperty("ecommerce_name_on_card"), "Test Step - 7: Entered name of card is not matching with expected name of card");

            checkoutPage.Enter_Card_Number_on_Payment_Info(prop.getProperty("ecommerce_credit_card_number"));
            softassert.assertEquals(checkoutPage.get_entered_card_number_on_Payment_Info(), prop.getProperty("ecommerce_credit_card_number"), "Test Step - 7: Entered card number is not matching with expected card number");

            checkoutPage.Enter_expiry_date_on_Payment_Info(prop.getProperty("ecommerce_expiry_date"));
            softassert.assertEquals(checkoutPage.get_entered_expiry_date_on_Payment_Info(), prop.getProperty("ecommerce_expiry_date"), "Test Step - 7: Entered expiry date is not matching with expected expiry date");

            checkoutPage.Enter_CVV_on_Payment_Info(prop.getProperty("ecommerce_cvv"));
            softassert.assertEquals(checkoutPage.get_entered_CVV_on_Payment_Info(), prop.getProperty("ecommerce_cvv"), "Test Step - 7: Entered CVV is not matching with expected CVV");

            checkoutPage.enterBillingFirstName(prop.getProperty("billing_first_name"));
            softassert.assertEquals(checkoutPage.getBillingFirstName(), prop.getProperty("billing_first_name"),
                    "Test Step - 1: Entered billing first name is not matching with expected value");

            checkoutPage.enterBillingLastName(prop.getProperty("billing_last_name"));
            softassert.assertEquals(checkoutPage.getBillingLastName(), prop.getProperty("billing_last_name"),
                    "Test Step - 2: Entered billing last name is not matching with expected value");

            checkoutPage.enterBillingPhoneNumber(prop.getProperty("ecommerce_phone_number"));
            softassert.assertEquals(checkoutPage.getBillingPhoneNumber(), prop.getProperty("ecommerce_phone_number"),
                    "Test Step - 3: Entered billing phone number is not matching with expected value");

            checkoutPage.enterBillingStreetAddress(prop.getProperty("billing_street_address"));
            softassert.assertEquals(checkoutPage.getBillingStreetAddress(), prop.getProperty("billing_street_address"),
                    "Test Step - 4: Entered billing street address is not matching with expected value");

            checkoutPage.selectBillingCountry(prop.getProperty("billing_country"));
            softassert.assertEquals(checkoutPage.getBillingCountry(), prop.getProperty("billing_country"),
                    "Test Step - 5: Selected billing country is not matching with expected value");

            checkoutPage.selectBillingState(prop.getProperty("billing_state"));
            softassert.assertEquals(checkoutPage.getBillingState(), prop.getProperty("billing_state"),
                    "Test Step - 6: Selected billing state is not matching with expected value");

            delayWithGivenTime(2000);

            if (checkoutPage.Verify_Message_Popup_isDisplay()) {
                checkoutPage.close_Message_Popup_isDisplay();
            }

            delayWithGivenTime(2000);
            checkoutPage.enterBillingCity(prop.getProperty("billing_city"));
            softassert.assertEquals(checkoutPage.getBillingCity(), prop.getProperty("billing_city"),
                    "Test Step - 7: Entered billing city is not matching with expected value");

            if (checkoutPage.Verify_Message_Popup_isDisplay()) {
                checkoutPage.close_Message_Popup_isDisplay();
            }

            checkoutPage.enterBillingZipCode(prop.getProperty("billing_zip_code"));
            softassert.assertEquals(checkoutPage.getBillingZipCode(), prop.getProperty("billing_zip_code"),
                    "Test Step - 8: Entered billing zip code is not matching with expected value");

            checkoutPage.click_Continue_To_Review_Button_On_Payment_Section();
            delayWithGivenTime(2000);
            softassert.assertTrue(checkoutPage.Verify_Review_Information_Tab_isDisplayed(), "Test Step - 9: Review information Tab is not displayed");

            // This field is not displayed in Checkout page - after Aug 2025
            //   checkoutPage.Click_Agree_Substitution_Checkbox();

            softassert.assertEquals(checkoutPage.get_Displayed_CardMessage_on_ReviewPage(), prop.getProperty("cart_card_message"), "Test Step - 9: Displayed card message on review page is not matching with expected card message on review page: " + prop.getProperty("cart_card_message"));
            softassert.assertEquals(checkoutPage.get_Displayed_EmailID_on_ReviewPage(), prop.getProperty("cart_email"), "Test Step - 9: Displayed email id on review page is not matching with expected email id on review page: " + prop.getProperty("cart_email"));
            softassert.assertEquals(checkoutPage.get_Displayed_RecipientInfo_on_ReviewPage(), recifname1 + " " + recilname2 + "\n" +
                    "POST Houston\n" +
                    "401 Franklin St\n" +
                    "\n" +
                    "Houston, TX, 77201 United States\n" +
                    "Phone: +1 " + reci_phone_number1, "Test Step - 9: Displayed recipient information on review page is not matching with expected recipient information on review page");

            softassert.assertEquals(checkoutPage.get_Displayed_Customer_Information_on_ReviewPage(), "John Doe\n" +
                    "123 Main St\n" +
                    "Louisville, KY, 77022 United States\n" +
                    "Phone: +1 561-555-7689", "Test Step - 9: Displayed Customer information on review page is not matching with expected recipient information on review page");

            softassert.assertEquals(checkoutPage.get_Displayed_CreditCardInfo_on_ReviewPage(), "Credit Card\n" +
                    "1111", "Test Step - 9: Displayed Credit Card information on review page is not matching with expected recipient information on review page");

            String formattedDate = convertDisplayedDateTo_MMddyyyy(delivery_Date, 2025);
            softassert.assertEquals(checkoutPage.get_Displayed_DeliveryDate_on_ReviewPage(), formattedDate, "Test Step - 9: Displayed Delivery date on review page is not matching with expected recipient information on review page: " + delivery_Date);
            softassert.assertEquals(checkoutPage.get_Displayed_FloristOrDeliveryInstruction_on_ReviewPage(), "Test Automation - Florist Instructions", "Test Step - 9: Displayed Delivery date on review page is not matching with expected recipient information on review page: Test Automation - Florist Instructions");
            checkoutPage.Click_Place_Your_Order_Button();
            softassert.assertEquals(checkoutPage.get_toast_message_text(), "Your payment is completed.\n" +
                    "Page is redirecting, please wait.", "Test Step - 9: Success Toast message is not matching with expected toast message");

            softassert.assertTrue(checkoutPage.Verify_Order_Confirmation_Message(), "Test Step - 10: Order confirmation message is not displayed");
            order_confirmationPage = new Ecommerce_Order_Confirmation_Page();

            softassert.assertEquals(order_confirmationPage.getProductRow1Text(), "Balaji_Test_Product-Medium", "Test Step - 1: Product Row 1 text is not empty");

            softassert.assertEquals(order_confirmationPage.getProductRow2Text(), "Chocolates - Medium Box add-on for Test_Automation", "Test Step - 2: Product Row 2 text is not empty");

            softassert.assertEquals(order_confirmationPage.getQuantityRow1Text(), "1", "Test Step - 3: Quantity Row 1 text is not empty");

            softassert.assertEquals(order_confirmationPage.getQuantityRow2Text(), "1", "Test Step - 4: Quantity Row 2 text is not empty");

            softassert.assertEquals(order_confirmationPage.getUnitPriceRow1Text(), "$500.00", "Test Step - 5: Unit Price Row 1 text is not empty");

            softassert.assertEquals(order_confirmationPage.getUnitPriceRow2Text(), "$19.99", "Test Step - 6: Unit Price Row 2 text is not empty");

            //   softassert.assertTrue(order_confirmationPage.isExpeditedDeliveryFeeDisplayed(), "Test Step - 6: Expedited Delivery Fee label is not displayed");
            //  softassert.assertEquals(order_confirmationPage.getExpeditedDeliveryFeeText(), expeditedDeliveryFee, "Test Step - 6: Expedited Delivery Fee text is not displayed or Expedited Delivery Fee Value is incorrect");

            softassert.assertEquals(order_confirmationPage.getTotalPriceRow1Text(), "$500.00", "Test Step - 7: Total Price Row 1 text is not empty");

            softassert.assertEquals(order_confirmationPage.getTotalPriceRow2Text(), "$19.99", "Test Step - 8: Total Price Row 2 text is not empty");

            softassert.assertEquals(order_confirmationPage.getProductCostText(), "$519.99", "Test Step - 9: Product Cost text is not empty");

            //  softassert.assertEquals(order_confirmationPage.getDeliveryFeeText(), "$20.00", "Test Step - 10: Delivery Fee text is not empty");

            //  softassert.assertEquals(order_confirmationPage.getTaxText(), "$37.45", "Test Step - 11: Tax text is not empty");

            softassert.assertEquals(order_confirmationPage.getTotalProductCostText(), "$" + order_confirmationPage.Calculate_Total_Cost_With_Processing_Fee(), "Test Step - 12: Total Product Cost text is not empty");

            softassert.assertEquals(order_confirmationPage.getCustomerInformationText(), "John Doe\n" +
                    "123 Main St\n" +
                    "Louisville, KY 77022 US\n" +
                    "hanaposqateam@gmail.com\n" +
                    "Phone: 1-561-555-7689", "Test Step - 13: Customer Information text is not empty");

            softassert.assertEquals(order_confirmationPage.getDeliveryInformationText(), recifname1 + " " + recilname2 + "\n" +
                    "" + prop.getProperty("ecommerce_business_name") + "\n" +
                    "" + prop.getProperty("ecommerce_addressfield") + "\n" +
                    "" + prop.getProperty("ecommerce_city") + ", TX" + " " + prop.getProperty("ecommerce_zipcode") + " US\n" +
                    "Phone: 1-" + reci_phone_number1 + "\n" +
                    "Delivery Date: " + FutureDateEST(2), "Test Step - 14: Delivery Information text is not empty");

            softassert.assertEquals(order_confirmationPage.getPaymentTypeText(), "CREDIT CARD", "Test Step - 15: Payment Type text is not empty");

            softassert.assertEquals(order_confirmationPage.getPaymentCardNumberText(), "1111", "Test Step - 16: Payment Card Number text is not empty");

            softassert.assertEquals(order_confirmationPage.getCommentsOrSpecialInstructionsText(), prop.getProperty("cart_card_message"), "Test Step - 17: Comments or Special Instructions text is not empty");

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
