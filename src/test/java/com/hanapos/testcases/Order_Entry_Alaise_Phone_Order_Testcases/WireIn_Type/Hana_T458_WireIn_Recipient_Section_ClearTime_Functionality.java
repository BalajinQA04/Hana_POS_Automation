package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireIn_Type;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T458_WireIn_Recipient_Section_ClearTime_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    public static final String dataSheetName = "Hana_T455";
    String invoiceNumber;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Phone Order Module - Wire In Type")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T458_WireIn_Recipient_Section_ClearTime_Functionality_Test(String salesperson, String FirstName, String LastName, String Shopcode, String ShopName, String WireIn_Method, String Order_Number, String PhoneNumber,
                                                                                         String Email, String custaddress1, String Comments, String custzip, String custphone, String recifname, String recilname, String reciaddress1, String reciaddress2, String recizip,
                                                                                         String recicity, String recicountry, String reciphone, String recilocation, String occasion, String searchandselectitemcode, String paymenttype, String cashregistry) {

        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T458_WireIn_Recipient_Section_ClearTime_Functionality_Test  ****");
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
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page");
          /*  dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));
*/
            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 6 - Shop name is not displayed in the WireIn section of phone order page");

            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            softassert.assertEquals(phoneorder.get_SelectedSalesPersonOn_PhoneOrderEntryPage(), prop.getProperty("salesperson"), "Selected Sales Person " + prop.getProperty("salesperson") + " is not displayed on phone order page");
            phoneorder.Click_WireIn_DeliveryType_OnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnWireInTypeOnPhoneOrderPage(), "#676a6c", "Wire In as delivery type is not highlighted in blue color");

            // Test Step - 6
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            phoneorder.Enter_WireIn_Fname(FirstName);
            phoneorder.Enter_WireIn_Lname(LastName);
            phoneorder.Enter_WireIn_ShopCode(Shopcode);
            phoneorder.Enter_WireIn_ShopName(ShopName);
            phoneorder.Select_WireInMethod(WireIn_Method);
            phoneorder.Enter_WireIn_OrderNumber(Order_Number);
            phoneorder.Enter_WireIn_PhoneNumber(PhoneNumber);
            phoneorder.Enter_WireIn_Email(Email);
            phoneorder.Enter_WireIn_ShopAddress(custaddress1);
            phoneorder.Enter_WireIn_Comments(Comments);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Fname(), "Abish", "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Lname(), "David", "Test Step - 6 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopCode(), "1100", "Test Step - 6 - Entered WireIn shop code is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopName(), "Automation Test Flower Shop", "Test Step - 6 - Entered WireIn shop name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Selected_WireInMethod(), "FSN", "Test Step - 6 - Selected WireIn method is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_OrderNumber(), "1122FSN33", "Test Step - 6 - Entered WireIn order number is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_PhoneNumber(), "956-655-0756", "Test Step - 6 - Entered phone number on customer details popup is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Email(), "hanaposqateam@gmail.com", "Test Step - 6 - Entered shop Email id on customer details popup is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopAddress(), "2715 35th Avenue Greeley, CO, USA", "Test Step - 6 - Entered shop address  is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Comments(), "Test Automation Wire In Order", "Test Step - 6 - Shop Wire In Comments is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 7
            phoneorder.EnterReciFirstName(recifname);
            phoneorder.EnterReciLastName(recilname);
            softassert.assertEquals(phoneorder.getReciFirstName(), "Abish", "Test Step - 7 - Entered first name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "David", "Test Step - 7 - Entered last name is not displayed on phone order page recipient section");

            // Test Step - 8
            phoneorder.SearchAndSelectReciAddress1(reciaddress1);
            phoneorder.EnterReciAddress2(prop.getProperty("Reci_Address1_2"));
            phoneorder.EnterReciPhone(reciphone);
            phoneorder.EnterRecipientPhone2OnPhoneOrderPage(prop.getProperty("recipient_altphonenumber"));
            phoneorder.Enter_FloorApt_On_RecipientSection(prop.getProperty("recipient_aptfloor"));
            delayWithGivenTime(2000);
            phoneorder.SelectReciLocation(recilocation);
            phoneorder.Select_Zone_OnRecipientSection(prop.getProperty("zone"));
            phoneorder.ClickSelectOptions_OnRecipient();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "3402 Park Blvd", "Test Step - 8 - Entered address 1 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "92103", "Test Step - 8 - Entered zipcode is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "San Diego", "Test Step - 8 - Entered city is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "CA", "Test Steps - 8 : Recipient state value is not auto populated on phone order page recipient section");
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 8 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "112 Penny Ct,", "Test Step - 8 - Recipient address 2 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), "956-655-0756", "Test Step - 10 - Recipient phone number is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), "878-787-7878", "Test Step - 10 - Recipient floor and apt is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.get_FloorApt_On_RecipientSection(), "Floor 3, Apt 2", "Test Step - 10 - Recipient floor and apt is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), "Church", "Test Step - 10 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getSelectedZone_OnRecipientSection(), "Automation Zone", "Test Step - 10 - Recipient zone is not displayed on phone order page recipient section");

            // Test Step - 9
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 9 - Entered Delivery date is not displayed on recipient section");

            // Test Step - 10
            phoneorder.Select_DeliveryOnTime_Dropdown(prop.getProperty("deliveryOnTime"));
            softassert.assertEquals(phoneorder.getSelected_DeliveryOnTimeOptions_OnDropdown_RecipientSection(), "Exactly At", "Test Step - 10 - Recipient delivery options is not displayed on phone order page recipient section");

            //Test Step - 11
            delayWithGivenTime(2000);
            phoneorder.Enter_DeliveryTime_OnRecipientSection(18, 30);
            softassert.assertEquals(phoneorder.get_deliveryTime_on_recipientSection(), "06:30 PM", "Test Step - 11 - Recipient delivery time is not displayed on phone order page recipient section");

            //Test Step - 12
            phoneorder.Click_ClearTimeButton_OnRecipientSection();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getEnteredTimeOnRecipientSection(), "", "Test Step - 12 - Delivery date is not cleared with expected delivery date on phone order page recipient section");

        } catch (Exception e) {
            e.printStackTrace();
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}