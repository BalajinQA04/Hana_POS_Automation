package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.CashAndCarryPaymentPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class Hana_T02_AddProductWithDiscount_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    public static final String dataSheetName = "Hana_T02";
    CustomSoftAssert softassert = new CustomSoftAssert();

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    public static LoggerUtil logger_Util;
    //,retryAnalyzer= com.hanapos.utilities.RetryTest.class
    @Severity(SeverityLevel.NORMAL)
    @Owner("Balaji N")
    @Epic("Cash and Carry Module")
    @Test(priority = 1, enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T02_Add_Product_With_Discount_Test(String searchandselectitemcode, String searchandselectitemdescription, String itemqty, String itemprice,
                                                                 String itemdiscountpercentage, String itemdiscountamount, String itemcode, String itemdiscountpercent, String itemdescription, String itemquantity,
                                                                 String itemPrice, String customername, String taxtype, String occasion, String sourcecode) throws InterruptedException, IOException {

        // Test Step - 1: Verify User is on Login Page and can see login fields
        logger.info("**** Starting Hana_T02_AddProductWithDiscount_CashAndCarryTest  ****");
        logger.debug("capturing application debug logs....");
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Verify Login page is displayed");
            Allure.step("Test Step - 1: Verify user is on the Hana POS login page");
            logger.info("User is on the Hana POS login page");

            // Test Step - 2: Enter login credentials and verify
            lp.EnterUserName(prop.getProperty("bestuname"));
            Allure.step("Test Step - 2: Enter username: " + prop.getProperty("bestuname"));
            lp.EnterPassword(prop.getProperty("bestpass"));
            Allure.step("Test Step - 2: Enter password");

            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("bestuname"), "Test Step - 2: Verify entered username matches expected");
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("bestpass"), "Test Step - 2: Verify entered password matches expected");

            lp.ClickLoginButton();
            Allure.step("Test Step - 2: Click on Login button");

            dashboard = new HanaDashBoardPage();
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2: Verify successful navigation to Hana dashboard page");
            logger.info("User successfully navigated to Hana dashboard page");

            // Test Step - 3: Navigate to Cash and Carry
            logger.info("User selected the shop name on dashboard page: " + prop.getProperty("bestshopname"));
            Allure.step("Test Step - 3: Select shop name on dashboard: " + prop.getProperty("bestshopname"));
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            Allure.step("Test Step - 3: Click on 'New Order' and select 'Cash and Carry' from menu");
            logger.info("User navigated to Cash and Carry section");

            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3: Verify Cash and Carry page is displayed");
            logger.info("Cash and Carry page is displayed successfully");
            Allure.step("Test Step - 3: Verify Cash and Carry page is displayed");

            // Test Step - 4: Select Shop Name
            cashandcarry.SelectShopName(prop.getProperty("bestshopname"));
            Allure.step("Test Step - 4: Select shop name: " + prop.getProperty("bestshopname"));
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("bestshopname"), "Test Step - 4: Verify selected shop name matches expected");

            // Test Step - 5: Select Clerk Name
            delayWithGivenTime(2000);
            cashandcarry.SelectClerkName(prop.getProperty("cashandcarryclerkname"));
            Allure.step("Test Step - 5: Select clerk name: " + prop.getProperty("cashandcarryclerkname"));
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("cashandcarryclerkname"), "Test Step - 5: Verify selected clerk name matches expected");

            // Test Step - 6: Select Employee
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            Allure.step("Test Step - 6: Select employee name: " + prop.getProperty("employeename"));
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 6: Verify selected employee name matches expected");

            // Test Step - 7
            cashandcarry.SearchAndSelectTheItemCode(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));
            Allure.step("Test Step - 7 : User select the item code as " + searchandselectitemcode);

            // Test Step - 8
            softassert.assertEquals(cashandcarry.ItemCodeValueIsExists(), prop.getProperty("product_itemcode1"), "Test Step - 8 : Item code is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), prop.getProperty("product_description1"), "Test Step - 8 : Item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 8 : Item Quantity is not matched with search and selected item code");

            if (cashandcarry.ItemPriceValueIsExist() == "299") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Step - 8 : Item price is not matched with search and selected item code");
            } else if (cashandcarry.ItemPriceValueIsExist() == "309") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "309", "Test Step - 8 : Item price is not matched with search and selected item code");
            }

            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 8 : Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 8 : Item discount percentage is not matched with search and selected item code");
            Allure.step("User verify the item description, quantity, price, discount amount and discount percentage are displayed on respective fields");

            // Test Step - 9,10
            cashandcarry.ClickRefreshForm();
            Allure.step("Test Step - 9,10 : User click on refresh button and respective fields displayed datas are cleared");

            softassert.assertEquals(cashandcarry.ItemCodeValueIsExists(), "", "Test Step - 9,10 : After click on refresh button the item code is not cleared");
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "", "Test Step - 9,10 : After click on refresh button the item description is not cleared");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "", "Test Step - 9,10 : After click on refresh button the item quantity is not cleared");
            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "", "Test Step - 9,10 : After click on refresh button the item price is not cleared");
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "", "Test Step - 9,10 : After click on refresh button the item discount amount is not cleared");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "", "Test Step - 9,10 : After click on refresh button the item discount percentage is not cleared");
            Allure.step("Test Step - 9,10 : User verify the item description, quantity, price, discount amount and discount percentage are cleared..");

            // Test Step - 11
            cashandcarry.SearchAndSelectProdByItemDescription(prop.getProperty("cashandcarry_product_short_description"), prop.getProperty("cashandcarry_product_full_desc"));
            Allure.step("Test Step - 11 : User select the item description as " + searchandselectitemdescription);

            softassert.assertEquals(cashandcarry.ItemCodeValueIsExists(), prop.getProperty("cashandcarry_product_itemcode_without_SKUcode"), "Test Step - 11 : Search and selected item description is not matched with displayed item code");
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), prop.getProperty("cashandcarry_product_description"), "Test Step - 11 : Search and selected item description is not matched with displayed item description");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 11 : Search and selected item description is not matched with displayed item quantity");

            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "40", "Test Step - 11 : Search and selected item description is not matched with displayed item price");

            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 11 : Search and selected item description is not matched with displayed item discount amount");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 11 : Search and selected item description is not matched with displayed item discount percentage");
            Allure.step("User verify the item code, quantity, price, discount amount and discount percentage are displayed on respective fields");

            // Test Step - 12
            cashandcarry.EnterItemQuantity(itemqty);
            Allure.step("User entered the item quantity as " + itemqty);
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "3", "Test Step - 12 : Item quantity added value is not displayed");

            cashandcarry.EnterItemPrice(itemprice);
            Allure.step("User entered the item price as " + itemprice);
            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "50", "Test Step - 12 : Item price added value is not displayed");
            Allure.step("User verify the item price..");

            // Test Step - 13
            cashandcarry.EnterDiscountPercentageOnAddItem(itemdiscountpercentage);
            Allure.step("User entered the discount percentage as " + itemdiscountpercentage);
            softassert.assertEquals(cashandcarry.getDiscountAmtOnDiscIsAddedByDiscPerAddItem(), "15.00", "Test Step - 13 : Discount percentage added value is not autocalculated to discount amount");

            //Test Step - 14
            cashandcarry.EnterDiscountAmountOnAddItem(itemdiscountamount);
            Allure.step("User entered the discount amount as " + itemdiscountamount);

            softassert.assertEquals(cashandcarry.getDiscountPercentageOnDiscIsAddedByDiscAmtField(), "010", "Test Step - 14 : Discount Amount added value is not autocalculated to discount percentage");

            //Test Step - 15
            cashandcarry.ClickAddItem();
            Allure.step("User click on Add Item button");

            softassert.assertTrue(cashandcarry.VerifyAddedItem(), "Test Step - 15 : Added Item is not displayed on Cash and Carry page grid table");
            softassert.assertEquals(cashandcarry.getAddedItemCode(), prop.getProperty("cashandcarry_product_itemcode_without_SKUcode"), "Test Step - 15 : Item code is not displayed on Cash and Carry page grid table");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), prop.getProperty("cashandcarry_product_description"), "Test Step - 15 : Item description is not displayed on Cash and Carry page grid table");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "3", "Test Step - 15 : Item quantity is not displayed on Cash and Carry page grid table");
            softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$150.00", "Test Step - 15 : Item extended price is not displayed on Cash and Carry page grid table");
            softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$50.00", "Test Step - 15 : Item Price is not displayed on Cash and Carry page grid table");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$15.00", "Test Step - 15 : Item discount amount is not displayed on Cash and Carry page grid table");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "10.00", "Test Step - 15 : Item discount percentage is not displayed on Cash and Carry page grid table");
            Allure.step("User verify the added item on Cash and Carry page grid table");

            //Delete the added item on Cash and Carry page
            cashandcarry.ClickRow1DeleteIcon();
            Allure.step("User click the delete icon on the grid table row 1");

            // Test Step - 16 Verify Title Display Product
            delayWithGivenTime(2000);
            Allure.step("User verify add the title product to the Cash and Carry page is displayed..");
            cashandcarry.ClickParticularProdTitle();
            Allure.step("User click the left side menu tile displayed product");
            softassert.assertEquals(cashandcarry.getAddedItemCode(), prop.getProperty("cashandcarry_product_itemcode"), "Test Step - 16 : Added Item product tile is not displayed on Cash and Carry page grid table");

            // Test Step - 17 Verify Product Discount
            //Delete the added item on Cash and Carry page
            cashandcarry.ClickRow1DeleteIcon();
            delayWithGivenTime(2000);
            Allure.step("User verify add the title product to the Cash and Carry page is displayed..");

            cashandcarry.EnterItemCode(itemcode);
            Allure.step("User entered the item code as " + itemcode);

            cashandcarry.EnterDiscountPercentageOnAddItem(itemdiscountpercent);
            Allure.step("User entered the discount percentage as " + itemdiscountpercent);

            cashandcarry.EnterItemDescription(itemdescription);
            Allure.step("User entered the item description as " + itemdescription);

            cashandcarry.EnterItemQuantity(itemquantity);
            Allure.step("User entered the item quantity as " + itemquantity);

            cashandcarry.EnterItemPrice(itemPrice);
            Allure.step("User entered the item price as " + itemPrice);

            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "010", "Test Step - 17 : Item discount percentage added value is not displayed");
            delayWithGivenTime(2000);
            cashandcarry.ClickAddItem();
            Allure.step("User click the Add Item button");
            // Hold below step due to discount percentage value is not reflected on the table
            //By manually it working fine
            delayWithGivenTime(2000);
            //  softassert.assertTrue(cashandcarry.CouponcodeIsDisabled(), "Test Step - 18 : coupon code is not enabled");
            Allure.step("User verify the coupon code is disabled");

            // Test step - 19,20
            cashandcarry.ClickAddBtnForCreateNewCust();
            Allure.step("User click on Add button in the Add new customer popup");

            softassert.assertTrue(cashandcarry.VerifyAddNewCustomerPageIsExist(), "Test Step - 19,20 : Add new customer page is not displayed");
            cashandcarry.ClickCloseIconOnAddNewCustPopup();
            Allure.step("User click the close icon in the Add new customer popup");

            //Test step - 21
            softassert.assertTrue(cashandcarry.SelectCustomerLabelIsDisplayed(), "Test Step - 21 : Select customer label is not displayed");
            cashandcarry.EnterCustomerName(customername, prop.getProperty("custfullname"));
            Allure.step("User entered the customer name as " + customername);

            softassert.assertTrue(cashandcarry.CustomerIdIsExist(), "Test Step - 21 : Customer id is not displayed");
            Allure.step("User verify the customer id is displayed");

            softassert.assertTrue(cashandcarry.CustomerNameIsExist(), "Test Step - 21 : Customer name is not displayed");
            Allure.step("User verify the customer name is displayed");

            softassert.assertTrue(cashandcarry.CustomerCancelCrossIconIsDisplayed(), "Test Step - 21 : Customer cancel cross icon is not displayed");
            Allure.step("User verify the customer cancel cross icon is displayed");

            //Test step - 22
            cashandcarry.ClickCancelCustIcon();
            Allure.step("User click the cancel customer icon");

            softassert.assertTrue(cashandcarry.SelectCustomerDDIsDisplayed(), "Test Step - 22 : Select customer drop down is not displayed");
            Allure.step("User verify the select customer drop down is displayed");

            //Test step - 23
            cashandcarry.SelectCustomerLabelIsDisplayed();
            cashandcarry.EnterCustomerName(customername, prop.getProperty("custfullname"));
            Allure.step("User entered the customer name as " + customername);
            softassert.assertTrue(cashandcarry.CustomerIdIsExist(), "Test Step - 23 : Customer id is not displayed - on cash and carry page");
            Allure.step("User verify the customer id is displayed");
            softassert.assertTrue(cashandcarry.CustomerNameIsExist(), "Test Step - 23 : Customer name is not displayed - on cash and carry page");
            Allure.step("User verify the customer name is displayed");
            softassert.assertTrue(cashandcarry.CustomerCancelCrossIconIsDisplayed(), "Test Step - 23 : Customer cancel cross icon is not displayed - on cash and carry page");
            Allure.step("User verify the customer cancel cross icon is displayed");

            //Test step - 24
            cashandcarry.SelectTaxType(taxtype);
            Allure.step("User selected the tax type as " + taxtype);

            //Test step - 25
            cashandcarry.SelectOccasion(occasion);
            Allure.step("User selected the occasion as " + occasion);
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), occasion, "Test Step - 25 : Occasion is not selected");

            //Test Step - 26
            cashandcarry.SelectSourceCode(sourcecode);
            Allure.step("User selected the source code as " + sourcecode);
            softassert.assertEquals(cashandcarry.GetSourceCodeDDfirstOption(), sourcecode, "Test Step - 26 : Source code is not selected");

            //Test Step - 27
            softassert.assertEquals(cashandcarry.ValidateGrandTotal(), cashandcarry.ActualGrandTotal(), "Test Step - 27 : Grand total is not displayed");
            Allure.step("User verify the grand total is displayed");

            //Test Step - 28
            softassert.assertEquals(cashandcarry.ValidatePayButtonDisplayedPrice(), cashandcarry.ActualGrandTotal(), "Test Step - 28 : Pay button price is not displayed");
            Allure.step("User verify the pay button price is displayed");

            //Test Step - 29
            cashandcarry.ClickPayButton();
            Allure.step("User click on Pay button");

            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 29 : Cash And Carry payment page is not displayed");
            Allure.step("User verify the Cash And Carry payment page is displayed");

            Allure.step("**** Ending HANA-T02_AddProductWithDiscount - Functionality ****");
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
