package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.Marketing_RemaindersPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.pageObjects.Order_Confirmation_Page;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T161_OrderEntry_Pickup_OrderDetailsSection_SendRemainderChecked_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Marketing_RemaindersPage remainders;

    private Order_Confirmation_Page orderconfirmationpage;

    @Epic("Phone Order Module - Pickup Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T161_OrderEntry_Pickup_OrderDetailsSection_SendRemainderChecked_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T161_OrderEntry_Pickup_OrderDetailsSection_SendRemainderChecked_Functionality_Test ****");
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
            retryAction(() -> softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page did not navigate to hana dashboard page"), 3);
            logger.info("User navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            retryAction(() -> softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3: Order entry option is not displayed"), 3);
            retryAction(() -> softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3: Cash and carry option is not displayed"), 3);

            // Test Step - 4
            retryAction(() -> dashboard.ClickOrderEntry(), 3);
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            softassert.assertTrue(phoneorder.Verify_OrderDetailsSectionAppears(), "Test Step - 5 - Order details section is not displayed");

            // Test Step - 6
            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Test Step - 6 - Pickup type is not highlighted in blue color");

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
            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1("245 Columbine St, Denver, CO 80206, United States");
            delayWithGivenTime(2000);
            phoneorder.Enter_DeliveryTime_OnRecipientSection(18, 30);
            phoneorder.Select_DeliveryOnTime_Dropdown(prop.getProperty("deliveryOnTime"));

            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "245 Columbine St", "Test Step - 8 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "80206", "Test Step - 8 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Denver", "Test Step - 8 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "CO", "Test Step - 8 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            // Test Step - 9
            // Pre - requiste - verify the default values of occasion is setup
            phoneorder.ClickDefaultValuesIcon();
            delayWithGivenTime(3000);
            phoneorder.Select_DefaultOccasion_On_ChoosePageDefaultValues("--select--");
            phoneorder.Click_UpdateBtn_ChoosePageDefaultValues_Popup();
            phoneorder.ClickCloseIconOnChoosePageDefaultValuesPopup();

            softassert.assertFalse(phoneorder.Verify_SendRemainder_OnOrderDetailsIsAppears(), "Test Step - 9 - Send remainder checkbox is displayed on Order details section");

            // Test Step - 10
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 10 - Order details selected occasion is not displayed");

            // Test Step - 11
            softassert.assertTrue(phoneorder.Verify_SendRemainderCheckBoxIsSelected(), "Test Step - 11 - Send remainder checkbox is not displayed on Order details section");

            // Test Step - 12
            phoneorder.ClickDefaultValuesIcon();
            delayWithGivenTime(3000);
            phoneorder.Select_DefaultOccasion_On_ChoosePageDefaultValues(prop.getProperty("occasion"));
            phoneorder.Click_UpdateBtn_ChoosePageDefaultValues_Popup();
            phoneorder.ClickCloseIconOnChoosePageDefaultValuesPopup();

            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));

            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), prop.getProperty("product_itemcode1"), "Test Step - 12 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), prop.getProperty("product_description1"), "Test Step - 12 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 12 - Item quantity is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 12 - Item price is not displayed on phone order page product details section");
            delayWithGivenTime(2000);

            // Test Step - 13
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type_invoice"));
            delayWithGivenTime(1000);
             softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), prop.getProperty("payment_type_invoice"), "Test Step - 13 - Selected Payment type is not displayed on phone order page");
            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(1000);

            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 13 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 14
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 14 - Order confirmation page is not displayed");

            // Test Step - 15
            dashboard.Click_Marketing_RemainderMenu();

            // Test Step - 16
            delayWithGivenTime(2000);
            remainders = new Marketing_RemaindersPage();
            softassert.assertTrue(remainders.Verify_RemaindersHeaderAppears(), "Test Step - 16 - Marketing menu Remainders page is not displayed");

            // Test Step - 17
            remainders.Select_ShopDropDownRemaindersPage(prop.getProperty("shopname"));
            delayWithGivenTime(3000);
            softassert.assertEquals(remainders.get_ShopDropdown_Remainder_Page(), prop.getProperty("shopname"), "Test Step - 17 - Selected Shop name is not displayed on Remainders page");
            remainders.Click_Row1ActionButton();
            remainders.Click_Row1EditReminder();

            // Test Step - 18
            remainders.Verify_EditReminderPopupAppears();
            softassert.assertTrue(remainders.Verify_EditReminderPopupAppears(), "Test Step - 18 - Edit reminder popup is not displayed");
            // Commented due to email remainder will be sent 1 day after... So we cannot able to automate
			/*softassert.assertEquals(remainders.get_CustomerName_EditReminderPopup(), "Abish David","Test Step - 18 - Customer name is not displayed on Edit reminder popup");
			softassert.assertEquals(remainders.get_CustomerEmail_EditReminderPopup(), "hanaposqateam@gmail.com","Test Step - 18 - Customer email is not displayed on Edit reminder popup");
			softassert.assertEquals(remainders.get_RecipientFirstName_EditReminderPopup(), "Abish","Known Issue Test Step - 18 - Recipient first name is not displayed on Edit reminder popup");
			softassert.assertEquals(remainders.get_RecipientLastName_EditReminderPopup(), "David"," Known Issue Test Step - 18 - Recipient last name is not displayed on Edit reminder popup");
			softassert.assertEquals(remainders.get_Occation_EditReminderPopup(), "Birthday","Test Step - 18 - Occasion is not displayed on Edit reminder popup");
			softassert.assertEquals(remainders.get_ReminderType_EditReminderPopup(), "Birthday","Test Step - 18 - Remainder type is not displayed on Edit reminder popup");
			softassert.assertEquals(remainders.get_ReminderDate_EditReminderPopup(), remainders.get_ReminderDate_EditReminderPopup(),"Test Step - 18 - Remainder date is not displayed on Edit reminder popup");
*/

        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}