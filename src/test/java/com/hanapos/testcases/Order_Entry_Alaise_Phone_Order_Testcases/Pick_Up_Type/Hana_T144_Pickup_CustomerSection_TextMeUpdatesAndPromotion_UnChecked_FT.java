package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.DashboardOrderPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T144_Pickup_CustomerSection_TextMeUpdatesAndPromotion_UnChecked_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private DashboardOrderPage dashboardorder;

    @Epic("Phone Order Module - Pickup Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T144_Pickup_CustomerSection_TextMeUpdatesAndPromotion_UnChecked_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting Hana_T144_Pickup_CustomerSection_TextMeUpdatesAndPromotion_UnChecked_FT  ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("bestuname"));
            logger.info("User entered the username as " + prop.getProperty("bestuname"));
            lp.EnterPassword(prop.getProperty("bestpass"));
            logger.info("User entered the password as " + prop.getProperty("bestpass"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2: Page did not navigate to hana dashboard page");
            logger.info("User navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3: Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3: Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("bestshopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("bestshopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            softassert.assertTrue(phoneorder.VerifyCustSectionAppears(), "Test Step - 5 - Customer section is not displayed on phone order page");

            // Test Step - 6
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("cust_firstName"));

            delayWithGivenTime(2000);
            phoneorder.Click_CustEditIcon();
            delayWithGivenTime(2000);

            if (phoneorder.Verify_SMSToogleOn_CustDetailsPopup()) {
                phoneorder.Click_SMS_Toogle_Button_On_Cust_Details_Popup();
                phoneorder.Click_UpdateBtn_OnCustDetailsPopup();
            }

            PressEscapeKey();

            dashboard.ClickOnHomeIcon();
            delayWithGivenTime(1000);
            dashboard.ClickOrderEntry();

            delayWithGivenTime(1000);
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            softassert.assertTrue(phoneorder.VerifyCustSectionAppears(), "Test Step - 6 - Customer section is not displayed on phone order page");
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("cust_firstName"));
            delayWithGivenTime(3000);
            softassert.assertFalse(phoneorder.Verify_TextmepromotionCheckboxIsChecked_OnPhoneorderPage(), "Test Step - 6 - Text me promotion checkbox is not checked on phone order page");

            // Test Step - 7
            delayWithGivenTime(1000);
            phoneorder.Click_TextmePromotionCheckBox_OnphoneOrderPage();
            delayWithGivenTime(1000);
            softassert.assertFalse(phoneorder.Verify_TextmepromotionCheckboxIsChecked_OnPhoneorderPage(), "Test Step - 7 - Text me promotion checkbox is not unchecked on phone order page");

            // Test Step - 8
            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8",
                    "Test Step - 8 - Pickup type is not highlighted in blue color");

            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("cust_firstName"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), "Abish",
                    "Test Step - 8 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), "David",
                    "Test Step - 8 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), "Hana_Sisterchicks",
                    "Test Step - 8 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), "hanaposqateam@gmail.com",
                    "Test Step - 8 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "3402 Park Blvd",
                    "Test Step - 8 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "",
                    "Test Step - 8 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "92103",
                    "Test Step - 8 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "San Diego",
                    "Test Step - 8 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), "956-655-0756",
                    "Test Step - 8 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), "956-655-0756",
                    "Test Step - 8 - Alt phone number is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 9
            phoneorder.Click_TextmePromotionCheckBox_OnphoneOrderPage();
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Select_Zone_OnRecipientSection(prop.getProperty("zone"));

            softassert.assertEquals(phoneorder.getReciFirstName(), "Abish",
                    "Test Step - 9 - Entered first name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "David",
                    "Test Step - 9 - Entered last name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress1(), "701 JC Center Ct",
                    "Test Step - 9 - Entered address 1 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "PICK UP",
                    "Test Step - 9 - Entered address 2 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "55008",
                    "Test Step - 9 - Entered zipcode is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Cambridge",
                    "Test Step - 9 - Entered city is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States",
                    "Test Step - 9 - Selected country is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);


            // Test Step - 10
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            phoneorder.Enter_CardMessage_OnOccasion_Details_Section(prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 10 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertTrue(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), "Test Step - 10 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 11
            // 	sometimes search and select product is not working
            phoneorder.SearchandSelect_ItemcodeOnPhoneOrderPage(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));
          /*  phoneorder.Enter_Product_ItemCode_Row1(prop.getProperty("itemcode"));
            phoneorder.Enter_Product_ItemDescription_Row1(prop.getProperty("itemdescription"));
            phoneorder.Enter_Product_Qty_Row1(prop.getProperty("itemqty"));
            phoneorder.Enter_Product_Price_Row1(prop.getProperty("itemprice"));*/
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), prop.getProperty("product_itemcode1"), "Test Step - 12 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), prop.getProperty("product_description1"), "Test Step - 12 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 12 - Item quantity is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 12 - Item price is not displayed on phone order page product details section");
            delayWithGivenTime(2000);


            // Test Step - 12
            phoneorder.EnterDeliveryDateOnReciSection();
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getDisplayedPaymentTypeSelectedOption(), prop.getProperty("payment_type"),
                    "Test Step - 12 - Selected Payment type is not displayed on phone order page payment section");

            delayWithGivenTime(2000);
            phoneorder.EnterCashAmount(phoneorder.getGrandTotalAmount());
            phoneorder.SelectCashRegistry_On_CashPaymentType(prop.getProperty("payment_cash_registry"));

            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(),
                    "Test Step - 12 - Confirmation popup is not displayed on phone order page");

            // Test Step - 13
            delayWithGivenTime(2000);
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(1000);

            // Test Step - 14
            delayWithGivenTime(2000);
            dashboard.ClickOrder();
            delayWithGivenTime(2000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();

            // Test Step - 15
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(),
                    prop.getProperty("livedashboardorderURL"),
                    "Test Step - 15 - Dashboard order page is not displayed");


            // Test Step - 16
            // Not feasible to automate email reader functionality
            //	ThreadWait(15000);
            //	EmailReader.getInstance();
            //	System.out.println("Received Email Invoice number is :" + EmailReader.getInvoiceNumber());


            // Set previous test data
            dashboard.ClickOnHomeIcon();
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(),
                    "Test Step - 3 : Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(),
                    "Test Step - 3 : Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            softassert.assertTrue(phoneorder.VerifyCustSectionAppears(), "Test Step - 5 - Customer section is not displayed on phone order page");

            // Test Step - 6
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("cust_firstName"));
            delayWithGivenTime(2000);
            phoneorder.Click_CustEditIcon();
            delayWithGivenTime(2000);
            phoneorder.Click_SMSToogleOn_CustDetailsPopup();
            phoneorder.Click_UpdateBtn_OnCustDetailsPopup();
            phoneorder.Click_Customer_DetailsPopupCloseBtn();


        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}