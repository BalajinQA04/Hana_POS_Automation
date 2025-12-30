package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import com.github.javafaker.Faker;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.CashAndCarryPaymentPage;
import com.hanapos.pageObjects.CustomerPage;
import com.hanapos.pageObjects.DashboardOrderPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T37_Add_New_Customer_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    private DashboardOrderPage dashboardorder;
    private CustomerPage customerpage = new CustomerPage();
    public static final String dataSheetName = "Hana_T37";
    String invoiceNumber;
    String newlyCreatedCustName;
    public static LoggerUtil logger_Util;
    CustomSoftAssert softassert = new CustomSoftAssert();
    String custId;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }


    //retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Balaji N")
    @Description("Hana_T37 :- Add New Customer functionality on Cash And Carry Page")
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Smoke", "Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T37_Add_New_Customer_Test(String searchandselectitemcode, String custfirstname, String custlastname, String custphonenumber, String custsplfname, String custspllname, String custsplphonenumber, String custsplaltphnumber,
                                                        String custalphaphnumber, String custalphaltphnumber, String custphnumber1, String custphnumber2, String companyname, String custphonenumber1, String custaltphonenumber, String address1, String address2, String zipcode,
                                                        String city, String state, String country, String emailid, String custtype, String custcredit, String customername, String occasion) throws InterruptedException, IOException {
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("bestuname"));
            logger.info("User entered username as " + prop.getProperty("bestuname"));
            lp.EnterPassword(prop.getProperty("bestpass"));
            logger.info("User entered username as " + prop.getProperty("bestpass"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("bestuname"), "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("bestuname"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("bestpass"), "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("bestpass"));

            lp.ClickLoginButton();
            logger.info("User clicked on Login button..");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            // Test Step - 3
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3 - Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("bestshopname"));
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("bestshopname"), "Test Step - 4 : Shop name is not matched with selected shop name");

            //Test Step - 5
            delayWithGivenTime(2000);
            cashandcarry.SelectClerkName(prop.getProperty("cashandcarryclerkname"));
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("cashandcarryclerkname"), "Test Step - 5 : Clerk name is not matched with selected clerk name");

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 6 : Employee name is not matched with selected employee name");

            // Test Step - 7
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 7 - Item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1");

            if (cashandcarry.ItemPriceValueIsExist() == "299") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Step - 7 - Item price is not matched with search and selected item code");
            } else if (cashandcarry.ItemPriceValueIsExist() == "309") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "309", "Test Step - 7 - Item price is not matched with search and selected item code");
            }

            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 7 - Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 7 - Item discount percentage is not matched with search and selected item code");

            // Test Step - 8
            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem(), "Test Step - 8 - Added item is not displayed");
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "rrd", "Test Step - 8 - Added item code is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Red Rose Deluxe", "Test Step - 8 - Added item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Step - 8 - Added item quantity is not matched with search and selected item code");

            if (cashandcarry.GetAddedItemExtPrice() == "$299.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00", "Test Step - 8 - Added item extended price is not matched with search and selected item code");
            } else if (cashandcarry.GetAddedItemExtPrice() == "$309.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$309.00", "Test Step - 8 - Added item extended price is not matched with search and selected item code");
            }

            if (cashandcarry.GetAddedItemPrice() == "$299.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00", "Test Step - 8 - Added item price is not matched with search and selected item code");
            } else if (cashandcarry.GetAddedItemPrice() == "$309.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$309.00", "Test Step - 8 - Added item price is not matched with search and selected item code");
            }

            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 8 - Added item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 8 - Added item discount percentage is not matched with search and selected item code");

            // Test Step - 09
            delayWithGivenTime(2000);
            cashandcarry.ClickParticularProdTitle();
            softassert.assertEquals(cashandcarry.getAddedItemCodeRow2(), prop.getProperty("cashandcarry_product_itemcode"), "Test Step - 10 - Added product tile is not displayed on row 2 table grid");
            softassert.assertEquals(cashandcarry.getAddedItemDescriptionRow2(), prop.getProperty("cashandcarry_product_description"), "Test Step - 10 - Added item description in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemQuantityRow2(), "1", "Test Step - 10 - Added item quantity in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemPriceRow2(), "$40.00", "Test Step - 10 - Added item extended price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemExtentPriceRow2(), "$40.00", "Test Step - 10 - Added item price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountAmountRow2(), "$ 0.00", "Test Step - 10 - Added item discount amount in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountPercentageRow2(), "0.00", "Test Step - 10 - Added item discount percentage in row 2 is not displayed on the page");

            // Test Step - 10
            cashandcarry.ClickAddCustomerButton();
            delayWithGivenTime(2000);
            logger.info("User click the add button on customer select field");
            softassert.assertTrue(cashandcarry.VerifyAddNewCustomerPopup(), "Test Step - 10 - Add new customer popup is not displayed");
            logger.info("User verify the add new customer popup is displayed");

            // Test Step - 11
            cashandcarry.EnterAddNewCustFirstName(custfirstname);
            logger.info("User entered the first name as ");
            cashandcarry.EnterAddNewCustLastName(custlastname);
            logger.info("User entered the last name as ");
            cashandcarry.EnterAddNewCustPhoneNumber(custphonenumber);
            logger.info("User entered the phone number as ");

            // Test Step - 12
            cashandcarry.ClickAddNewCustPopupCloseIcon();
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.is_AddNewCustomer_Popup_Closed(), "Test Step - 12 - Add new customer popup is not closed after clicking the close icon");

            // Test Step - 13
            cashandcarry.ClickAddCustomerButton();
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.VerifyAddNewCustomerPopup(), "Test Step - 13 - Add new customer popup is not displayed");

            // Test Step - 14
            cashandcarry.EnterAddNewCustFirstName(custsplfname);
            cashandcarry.EnterAddNewCustLastName(custspllname);
            cashandcarry.EnterAddNewCustPhoneNumber(custsplphonenumber);
            cashandcarry.EnterAddNewCustAltPhoneNumber(custsplaltphnumber);

            softassert.assertEquals(cashandcarry.getEnteredaddnewcustfirstname(), "", "Test Step - 14 - special character is added on first name field");
            logger.info("User verify the special character is not accepted on first name field ");
            softassert.assertEquals(cashandcarry.getEnteredaddnewcustlastname(), "", "Test Step - 14 - special character is added on last name field");
            logger.info("User verify the special character is not accepted on last name field ");
            softassert.assertEquals(cashandcarry.getEnteredaddnewcustphonenumber(), "", "Test Step - 14 - special character is added on phone number field");
            logger.info("User verify the special character is not accepted on phone number field ");
            softassert.assertEquals(cashandcarry.getEnteredaddnewcustaltphonenumber(), "", "Test Step - 14 - special character is added on alternative phone number field");
            logger.info("User verify the special character is not accepted on alternative phone number field ");

            // Test Step - 15
            cashandcarry.EnterAddNewCustPhoneNumber(custalphaphnumber);
            logger.info("User entered the phone number as ");
            cashandcarry.EnterAddNewCustAltPhoneNumber(custalphaltphnumber);
            logger.info("User entered the alternate phone number as ");

            softassert.assertEquals(cashandcarry.getEnteredaddnewcustphonenumber(), "", "Test Step - 15 - alphabetic character is added on phone number field");
            logger.info("User verify the alphabetic character is not accepted on phone number field ");
            softassert.assertEquals(cashandcarry.getEnteredaddnewcustaltphonenumber(), "", "Test Step - 15 - alphabetic character is added on alternative phone number field");
            logger.info("User verify the alphabetic character is not accepted on alternative phone number field ");

            // Test Step - 16
            cashandcarry.EnterAddNewCustPhoneNumber(custphnumber1);
            logger.info("User entered the phone number as ");
            cashandcarry.EnterAddNewCustAltPhoneNumber(custphnumber2);
            logger.info("User entered the alternate phone number as ");

            softassert.assertEquals(cashandcarry.getEnteredaddnewcustphonenumber(), "+99-123-456-7890", "Test Step - 16 - enter more than 12 character is added on phone number field");
            logger.info("User verify the alphabetic character is not accepted on phone number field ");
            softassert.assertEquals(cashandcarry.getEnteredaddnewcustaltphonenumber(), "+99-123-456-7890", "Test Step - 16 - enter more than 12 character is added on alternative phone number field");
            logger.info("User verify the alphabetic character is not accepted on alternative phone number field ");

            // Test Step - 17
            Faker usFaker = new Faker(new java.util.Locale("en-US"));
            Faker canadaFaker = new Faker(new java.util.Locale("en-CA"));
            // Generate random US customer details
            String usFirstName = usFaker.name().firstName();
            String usLastName = usFaker.name().lastName();
            String usFullName = usFirstName + " " + usLastName;
            String usPhoneNumber = usFaker.phoneNumber().cellPhone();
            String caPhoneNumber = canadaFaker.phoneNumber().cellPhone();
            String usStreetAddress = usFaker.address().streetAddress();
            String usCity = usFaker.address().city();
            String usState = usFaker.address().state();
            String usZipCode = usFaker.address().zipCode();
            String usFullAddress = usStreetAddress + ", " + usCity + ", " + usState + " " + usZipCode;

            cashandcarry.SelectShopNameOnAddNewCustPopup(prop.getProperty("bestshopname"));
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getSelectedShopNameOnAddNewCustPopup(), prop.getProperty("bestshopname"), "Test Step - 17 - Shop name is not matching with expected shop name as " + prop.getProperty("bestshopname"));

            cashandcarry.EnterAddNewCustFirstName(usFirstName); //"Phanthom" + twodigitrandomeString()
            cashandcarry.EnterAddNewCustLastName(usLastName); //"Francis" + twodigitrandomeString()

            String firstname_newlycreated = usFirstName; //cashandcarry.getEnteredaddnewcustfirstname();
            String lastname_newlycreated = usLastName; //cashandcarry.getEnteredaddnewcustlastname();
            cashandcarry.EnterCompanyNameOnAddnewCustPopup(firstname_newlycreated + " " + lastname_newlycreated + " pvt ltd");
            cashandcarry.EnterAddNewCustPhoneNumber(custphonenumber1);
            cashandcarry.EnterAddNewCustAltPhoneNumber(custaltphonenumber);
            cashandcarry.EnterAddress1OnAddnewCustPopup(address1);
            cashandcarry.EnterAddress2OnAddnewCustPopup(address2);
            cashandcarry.EnterZipCodeOnAddnewCustPopup(zipcode);
            cashandcarry.EnterCityOnAddnewCustPopup(city);
            cashandcarry.EnterStateOnAddnewCustPopup(state);
            cashandcarry.EnterCountryOnAddnewCustPopup(country);
            cashandcarry.EnterEmailOnAddnewCustPopup(emailid);
            cashandcarry.SelectCustTypeOnAddNewCustPopup(custtype);
            cashandcarry.EnterCustCreditOnAddNewCustPopup(custcredit);
            delayWithGivenTime(2000);


            if (cashandcarry.getEnteredaddnewcustcompanyname().equals(firstname_newlycreated + " " + lastname_newlycreated + " pvt ltd")) {
                softassert.assertEquals(cashandcarry.getEnteredaddnewcustcompanyname(), firstname_newlycreated + " " + lastname_newlycreated + " pvt ltd", " Test Step-17 -Added on company name field are not properly displayed");
                logger.info("User verified that entered company name entered data is displayed on add new customer popup");
            } else if (cashandcarry.getEnteredaddnewcustcompanyname().equals(firstname_newlycreated + " " + lastname_newlycreated + " pvt ltd")) {
                softassert.assertEquals(cashandcarry.getEnteredaddnewcustcompanyname(), firstname_newlycreated + " " + lastname_newlycreated + " pvt ltd", " Test Step-17 -Added on company name field are not properly displayed");
                logger.info("User verified that entered company name entered data is displayed on add new customer popup");
            }

            softassert.assertEquals(cashandcarry.getEnteredaddnewcustfirstname(), firstname_newlycreated, "Test Step-17 -Added on first name field are not properly displayed");
            logger.info("User verify the first name field entered data is displayed");
            softassert.assertEquals(cashandcarry.getEnteredaddnewcustlastname(), lastname_newlycreated, "Test Step-17 -Added on first name field are not properly displayed");
            logger.info("User verify the last name field entered data is displayed");
            softassert.assertEquals(cashandcarry.getEnteredaddnewcustphonenumber(), "919-293-9495", "Test Step-17 -Added on phone number field are not properly displayed");
            logger.info("User verify the phone number field entered data is displayed");
            softassert.assertEquals(cashandcarry.getEnteredaddnewcustaltphonenumber(), "919-293-9896", "Test Step-17 -Added on alternative phone number field are not properly displayed");
            logger.info("User verify the alternative phone number field entered data is displayed");
            softassert.assertEquals(cashandcarry.getEnteredaddnewcustaddress1(), "Test Automation Address, India", "Test Step-17 -Added address 1 field is not properly displayed");
            logger.info("User verify that address 1 field entered data is displayed");
            softassert.assertEquals(cashandcarry.getEnteredaddnewcustaddress2(), "QA Test Automation, Cbe, Tn, India", "Test Step-17 -Added address 2 field is not properly displayed");
            logger.info("User verify that address 2 field entered data is displayed");
            softassert.assertEquals(cashandcarry.getEnteredaddnewcustzipcode(), "641004", "Test Step-17 -Added zip code is not displayed");
            logger.info("User verify that zipcode field entered data is displayed");

            if (cashandcarry.getEnteredaddnewcustcity().equals("Coimbatore")) {
                softassert.assertEquals(cashandcarry.getEnteredaddnewcustcity(), "Coimbatore", "Test Step-17 -Added city field is not displayed");
                logger.info("User verify that city field entered data is displayed");
            } else if (cashandcarry.getEnteredaddnewcustcity().equals("Coimabtore")) {
                softassert.assertEquals(cashandcarry.getEnteredaddnewcustcity(), "Coimbatore", "Test Step-17 -Added city field is not displayed");
                logger.info("User verify that city field entered data is displayed");
            }

            softassert.assertEquals(cashandcarry.getEnteredaddnewcuststate(), "Tamilnadu", "Test Step-17 -Added state field is not displayed");
            logger.info("User verify that state field entered data is displayed");
            softassert.assertEquals(cashandcarry.getEnteredaddnewcustcountry(), "India", "Test Step-17 -Added country field is not displayed");
            logger.info("User verify that country field entered data is displayed");
            softassert.assertEquals(cashandcarry.getEnteredaddnewcustemail(), "hanaposqateam@gmail.com", "Test Step-17 -Added email id field is not displayed");
            logger.info("User verify that email id field entered data is displayed");
            softassert.assertEquals(cashandcarry.getSelectedCustTypeOnAddNewCustPopup(), "Corporate", "Test Step-17 -Added customer type dropdown field is not displayed");
            logger.info("User verify that customer type dropdown field selected data is displayed");

            if (cashandcarry.getEnteredaddnewcustcredit().equals("100")) {
                softassert.assertEquals(cashandcarry.getEnteredaddnewcustcredit(), "100", "Test Step-17 -Added customer type dropdown field is not displayed");
                logger.info("User verify that customer type dropdown field selected data is displayed");
            } else if (cashandcarry.getEnteredaddnewcustcredit().equals("0")) {
                softassert.assertEquals(cashandcarry.getEnteredaddnewcustcredit(), "0", "Test Step-17 -Added customer type dropdown field is not displayed");
                logger.info("User verify that customer type dropdown field selected data is displayed");
            }

            // Test Step - 18
            cashandcarry.click_CreditApproved_Toogle_Button_OFF_State_On_AddNewCustPopup();
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.validate_CreditApproved_Toogle_Button_OFF_State_On_AddNewCustPopup(), "Test Step-18 Credit approved toogle is enabled");

            cashandcarry.click_PaperlessStatement_Toogle_Button_OFF_State_On_AddNewCustPopup();
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.Validate_PaperlessStatement_Toogle_Button_OFF_State_OnAddNewCustPopup(), "Test Step-18 paperless statement toogle is enabled");

            cashandcarry.click_EmailStatement_Toogle_Button_OFF_State_On_AddNewCustPopup();
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.Validate_EmailStatement_Toogle_Button_OFF_State_OnAddNewCustPopup(), "Test Step-18 Email statement toogle is enabled");

            cashandcarry.click_EnableLoyalty_Toogle_Button_OFF_State_On_AddNewCustPopup();
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.Validate_EnableLoyalty_Toogle_Button_OFF_State_OnAddNewCustPopup(), "Test Step-18 Enable loyalty toogle is enabled");

            cashandcarry.click_LateFeeSetting_Toogle_Button_OFF_State_On_AddNewCustPopup();
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.Validate_LateFeeSetting_Toogle_Button_OFF_State_OnAddNewCustPopup(), "Test Step-18 Late Fee Setting toogle is enabled");

            //  softassert.assertTrue(cashandcarry.ValidateAllowSMSTToogleOnAddNewCustPopup(), "Test Step-18 Allow SMS toogle is enabled");
            //  logger.info("User verify that allow sms toogle is disabled");

            cashandcarry.click_AllowFeedbackEmail_Toogle_Button_ON_State_On_AddNewCustPopup();
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.validate_AllowFeedbackEmail_Toogle_Button_ON_State_OnAddNewCustPopup(), "Test Step-18 Allow feedback email toogle is not enabled");

            // Test Step - 19
            cashandcarry.click_AddNewCustomer_Button_On_AddNewCustPopup();
            logger.info("User click on the add new customer button");
            softassert.assertTrue(cashandcarry.VerifySuccessMessageIsDisplayed(), "Test Step - 19 -Success toast message is not displayed after creating customer");
            logger.info("User verified success toast message is displayed ");
            softassert.assertEquals(cashandcarry.getDisplayedCustomerNameOnCCPage(), firstname_newlycreated + " " + lastname_newlycreated, "Test Step - 19 - Newly created Customer name is not displayed");
            logger.info("User verified created customer name is displayed on cash and carry page");
            System.out.println("Test Step - 19 - Newly Created Customer Id: " + cashandcarry.get_Displayed_CustomerId());
            custId = cashandcarry.get_Displayed_CustomerId();
            newlyCreatedCustName = cashandcarry.getDisplayedCustomerNameOnCCPage();

            // Test Step - 20
            delayWithGivenTime(1000);
            cashandcarry.ClickCancelCustIcon();
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.is_Search_Customer_TextBox_Field_Displayed(), "Test Step - 20 - Search Customer textbox field is not displayed");

            // Test Step - 21
            cashandcarry.EnterCustomerName(newlyCreatedCustName, newlyCreatedCustName);
            logger.info("User search and select the created customer");

            // Test Step - 22
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getDisplayedCustomerNameOnCCPage(), firstname_newlycreated + " " + lastname_newlycreated, "Test Step - 22 - Customer name is not displayed");
            softassert.assertTrue(cashandcarry.CustomerIdIsExist(), "Test Step - 22 -Test Automation customer id is not displayed");

            // Test Step - 23
            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 10 : Selected tax type is not displayed");
            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 10 : Selected occasion is not displayed");

            logger.info("User select the occasion as birthday");
            cashandcarry.ClickPayButton();
            logger.info("User clicks on the pay button");

            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 23 -Cash And Carry payment page is not displayed");
            logger.info("User is on Cash And Carry payment page");

            // Test Step - 24
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCashTab();
            logger.info("User select the payment type as cash tab");
            cashandcarrypayment.EnterGivenAmount();
            logger.info("User enter the amount in given amount field");
            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 24 -Order payment done successfully toast message is not displayed");
            logger.info("User verified the order payment done successfully");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 24 -Order confirmation message is not displayed");

            delayWithGivenTime(1000);
            if (cashandcarrypayment.getConfirmationPopup()) {
                cashandcarrypayment.VerifyOrderConfirmationPopup();
                logger.info("User verify the order confirmation popup is displayed");
                cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo();
                logger.info("User verify the order confirmation message and invoice number is displayed");
                invoiceNumber = cashandcarrypayment.GetInvoiceNumber();
                logger.info(("Generated Order invoice number is :" + cashandcarrypayment.GetInvoiceNumber()));
                cashandcarrypayment.GetTenderPrice();
                logger.info(("The remaining amount given to customer is :" + cashandcarrypayment.GetTenderPrice()));
            }

            logger.info("User click the cancel button on webclientprint window popup");
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();
            logger.info("User select the payment type as cash tab");

            // Test Step - 25
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 25 -Dashboard order page is not displayed");
            logger.info("User verify that the order page is navigated to dashboard order page");

            delayWithGivenTime(1000);
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 25 - Invoice number is not displayed on order page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), "Delivered", "Test Step - 25 - Order status is not displayed as delivered for cash and carry order");

            // Test Step - 26
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.get_Sender_or_Customer_On_OrderPage("Walkin Sales", "Pick Up", "Cash").contains(firstname_newlycreated + " " + lastname_newlycreated + " pvt ltd | " + firstname_newlycreated + " " + lastname_newlycreated), true, "Test Step - 26 - sender or customer name on order page is not matched");

            // Test Step - 27
            dashboardorder.ClickonSenderorCustomerOnOrderPage();
            softassert.assertTrue(dashboardorder.VerifyDeliveryPopup(), "Test Step - 27 -Delivery popup is not displayed");

            // Test STep - 28
            softassert.assertEquals(dashboardorder.getCustomerTypeDeliveryPopup(), "(New Customer)", "Test Step - 28 -customer type on delivery popup is not matched");
            softassert.assertEquals(dashboardorder.getCustAndcompyNameOnDeliveryPopup(), firstname_newlycreated + " " + lastname_newlycreated + " pvt ltd | " + firstname_newlycreated + " " + lastname_newlycreated, "Test Step - 28 -customer and company name on delivery popup is not matched");
            softassert.assertEquals(dashboardorder.getCustAddressOnDeliveryPopup(), "Test Automation Address, India QA Test Automation, Cbe, Tn, India", "Test Step - 28 -customer address on delivery popup is not matched");
            softassert.assertEquals(dashboardorder.getCustPhoneNumOnDeliveryPopup(), "919-293-9495", "Test Step - 28 -customer phone number on delivery popup is not matched");
            softassert.assertEquals(dashboardorder.getCustEmailOnDeliveryPopup(), "hanaposqateam@gmail.com", "Test Step - 28 -customer phone number on delivery popup is not matched");
            logger.info("User verified displayed customer type, customer name, company name, Address, Phone number & email are matched");

            // Test Step - 29
            dashboardorder.ClickCloseIconOnDeliveryPopup();
            logger.info("User clicks on close icon on delivery popup ");

            // Test Step - 30
            customerpage = new CustomerPage();
            dashboardorder.ClickCustomerMenuOnDashboard();
            logger.info("User clicks on customer menu on dashboard");
            delayWithGivenTime(2000);
            softassert.assertTrue(customerpage.VerifyCustomerMenuPage(), "Test Step - 30 -customer menu page is not displayed");
            logger.info("User verify that customer menu page is displayed successfully");

            // Test Step - 31
            delayWithGivenTime(1000);
            customerpage.Enter_CustomerId_SearchTextBox_OnCustomerTable(custId);
            delayWithGivenTime(2000);
            customerpage.ClickCustomerId_LastRow_OnCustTable();

            // Test Step - 32
            logger.info("User clicks the displayed customer in the table ");
            delayWithGivenTime(3000);
            softassert.assertTrue(customerpage.VerifyCustomerDetailsPopup(), "Test Step - 32 - Customer details pop up is not displayed");
            logger.info("User verify that customer details popup is displayed");

            // Test Step - 33
            delayWithGivenTime(2000);
            softassert.assertEquals(customerpage.getCustDetailsCompanyNameTextBox(), firstname_newlycreated + " " + lastname_newlycreated + " pvt ltd", " Test - 33 - Added on company name field are not properly displayed");
            logger.info("User verified that entered company name entered data is displayed on add new customer popup");
            softassert.assertEquals(customerpage.getCustDetailsFirstNameTextBox(), firstname_newlycreated, "Test Step - 33 - Added on first name field are not properly displayed");
            logger.info("User verify the first name field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsLastNameTextBox(), lastname_newlycreated, "Test Step - 33 - Added on first name field are not properly displayed");
            logger.info("User verify the last name field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsPhoneNumberTextbox(), "919-293-9495", "Test Step - 33 - Added on phone number field are not properly displayed");
            logger.info("User verify the phone number field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsAltPhoneNumberTextBox(), "919-293-9896", "Test Step - 33 - Added on alternative phone number field are not properly displayed");
            logger.info("User verify the alternative phone number field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsAddress1TextBox(), "Test Automation Address, India", "Test Step - 33 - Added address 1 field is not properly displayed");
            logger.info("User verify that address 1 field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsAddress2TextBox(), "QA Test Automation, Cbe, Tn, India", "Test Step - 33 - Added address 2 field is not properly displayed");
            logger.info("User verify that address 2 field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsZipCodeTextBox(), "641004", "Test Step - 33 - Added zip code is not displayed");
            logger.info("User verify that zipcode field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsCityTextBox(), "Coimbatore", "Test Step - 33 - Added city field is not displayed");
            logger.info("User verify that city field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsStateTextBox(), "Tamilnadu", "Test Step - 33 - Added state field is not displayed");
            logger.info("User verify that state field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsCountryTextBox(), "India", "Test Step - 33 - Added country field is not displayed");
            logger.info("User verify that country field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsEmailTextBox(), "hanaposqateam@gmail.com", "Test Step - 33 - Added email id field is not displayed");
            logger.info("User verify that email id field entered data is displayed");
            softassert.assertEquals(customerpage.getDisplayedCustDetailsCustomerType(), "Corporate", "Test Step - 33 - Added customer type dropdown field is not displayed");
            logger.info("User verify that customer type dropdown field selected data is displayed");

            if (customerpage.getCustDetailsStoreCreditTextBox() == "100") {
                softassert.assertEquals(customerpage.getCustDetailsStoreCreditTextBox(), "100", "Test Step - 33 - Added customer store credit is not displayed");
                logger.info("User verify that customer type dropdown field selected data is displayed");
            } else if (customerpage.getCustDetailsStoreCreditTextBox() == "0") {
                softassert.assertEquals(customerpage.getCustDetailsStoreCreditTextBox(), "0", "Test Step - 33 - Added customer store credit is not displayed");
                logger.info("User verify that customer type dropdown field selected data is displayed");
            }

            softassert.assertTrue(customerpage.validateCustDetailsCreditApprovedToogle(), "Test Step - 33 - Credit approved toogle is enabled");
            logger.info("User verify that credit approved toogle is disabled");
            softassert.assertTrue(customerpage.ValidateCustDetailsPaperlessStatementToogle(), "Test Step - 33 - paperless statement toogle is enabled");
            logger.info("User verify that paperless statement toogle is disabled");
            softassert.assertTrue(customerpage.ValidateCustDetailsEmailStatementToogle(), "Test Step - 33 - Email statement toogle is enabled");
            logger.info("User verify that email statement toogle is disabled");
            softassert.assertTrue(customerpage.ValidateCustDetailsEnableLoyaltyToogle(), "Test Step - 33 - Enable loyalty toogle is enabled");
            logger.info("User verify that enable loyalty toogle is disabled");
            softassert.assertTrue(customerpage.ValidateCustDetailsLateFeeSettingToogle(), "Test Step - 33 - Late Fee Setting toogle is enabled");
            logger.info("User verify that enable loyalty toogle is disabled");
            softassert.assertTrue(customerpage.ValidateCustDetailsAllowFeedbackEmailTToogle(), "Test Step - 33 - Allow feedback email toogle is not enabled");
            logger.info("User verify that allow feedback email toogle is enabled");

            // Test Step - 34
            customerpage.ClickCustomerDetailsPopupCloseIcon();
            logger.info("User clicks on customer details popup close icon");

            //=========== We cannot able to delete customer in the pos ==============
            /*
             * softassert.assertTrue(customerpage.VerifyCustomerMenuPage()
             * ,"customer menu page is not displayed");
             * logger.info("User verify the customer menu page successfully");
             *
             * customerpage.ClickOnCustomerTableAllCheckBox(); delayWithGivenTime(2000);
             * customerpage.ClickOnActionButton(); delayWithGivenTime(2000);
             * customerpage.ClickOnCustomerDeleteButton();
             */

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
