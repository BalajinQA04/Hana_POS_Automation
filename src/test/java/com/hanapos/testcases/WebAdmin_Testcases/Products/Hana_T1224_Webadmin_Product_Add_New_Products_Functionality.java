package com.hanapos.testcases.WebAdmin_Testcases.Products;

import com.hanapos.ecommerce_pageObjects.Ecommerce_HomePage;
import com.hanapos.ecommerce_pageObjects.Ecommerce_ProductDetails_Page;
import com.hanapos.ecommerce_pageObjects.Ecommerce_SearchResults_Page;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class Hana_T1224_Webadmin_Product_Add_New_Products_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPaymentPage cashandcarrypayment;
    private DashboardOrderPage dashboardorder;
    private WebAdmin_Home_Page webadmin_homepage;
    private WebAdmin_Products_Page webadmin_productspage;
    private CashAndCarryPage cashandcarry;
    private Ecommerce_HomePage ecommerce_homePage;
    private Ecommerce_SearchResults_Page searchResultsPage;
    private Ecommerce_ProductDetails_Page productDetailsPage;
    String custname;
    String custid;
    String invoicenumber;
    String balanceAmount;

    @Epic("Web Admin Module - Products")
    @Owner("Balaji N")
    @Test(enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T1224_Webadmin_Product_Add_New_Products_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 1: Entered username is not matching with expected username");
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 1: Entered password is not matching with expected password");
            lp.ClickLoginButton();

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));

            // Test Step - 3
            webadmin_homepage = new WebAdmin_Home_Page();
            dashboard.Click_WebAdmin_Submenu();

            softassert.assertTrue(webadmin_homepage.Verify_WebAdmin_home_header_IsDisplayed(), "Test Step - 3: Web Admin Home Page is not displayed");
            // softassert.assertEquals(webadmin_homepage.get_selected_shopname_on_WebAdmin_HomePage(), prop.getProperty("shopname"), "Test Step - 3: Selected Shopname is not displayed on web admin home page");
            webadmin_homepage.Click_products_tab_webAdmin_homepage();

            // Test Step - 4
            webadmin_productspage = new WebAdmin_Products_Page();
            softassert.assertTrue(webadmin_productspage.Verify_Products_Page_header(), "Test Step - 4: Web Admin Products Page is not displayed");

            // Test Step - 5
            webadmin_productspage.Click_AddNewButton();
            softassert.assertTrue(webadmin_productspage.Verify_AddNewProduct_Popup_IsDisplayed(), "Test Step - 4: Web Admin Products page - Add New Product Popup is not displayed");

            // Test Step - 6
            String item_name = getRandomFlowerName();
            String item_code = item_name + "_" + generaterandomeNumber(3);
            webadmin_productspage.Enter_ItemName_On_AddNewProduct_Popup(item_name);
            webadmin_productspage.Enter_ItemCode_On_AddNewProduct_Popup(item_code);
            delayWithGivenTime(2000);
            softassert.assertEquals(webadmin_productspage.get_entered_itemname_on_addnewproduct_popup(), item_name, "Test Step - 5: Entered item name on add new product popup is not displayed");
            softassert.assertEquals(webadmin_productspage.get_entered_itemcode_on_addnewproduct_popup(), item_code, "Test Step - 5: Entered item code on add new product popup is not displayed");

            delayWithGivenTime(2000);
            webadmin_productspage.Activate_Availability_toogle_button();
            webadmin_productspage.Activate_Web_toogle_button();
            webadmin_productspage.Activate_Store_toogle_button();

            String product_variant = generate_Product_Variant();
            webadmin_productspage.Enter_Product_Variant_Name1(product_variant);
            webadmin_productspage.Enter_Product_Variant_SKU1("");
            webadmin_productspage.Enter_Product_Variant_Cost1("20.67");
            webadmin_productspage.Enter_Product_Variant_Price1("30.67");

            softassert.assertEquals(webadmin_productspage.get_entered_product_variant_name1(), product_variant, "Test Step - 6: Entered item name Product Variant for row 1 is not matched");
            softassert.assertEquals(webadmin_productspage.get_entered_product_variant_sku1(), item_code + "_1", "Test Step - 6: Entered item sku Product Variant for row 1 is not matched");
            softassert.assertEquals(webadmin_productspage.get_entered_product_variant_cost1(), "20.67", "Test Step - 6: Entered cost Product Variant for row 1 is not matched");
            softassert.assertEquals(webadmin_productspage.get_entered_product_variant_price1(), "30.67", "Test Step - 6: Entered price Product Variant for row 1 is not matched");

            webadmin_productspage.Click_UploadIcon_for_ProductVariant1();
            delayWithGivenTime(2000);
            webadmin_productspage.drag_And_Drop_Image_On_ProductVariant1("color full flowers.jpg");
            webadmin_productspage.Click_Skip_Button();
            getDriver().switchTo().defaultContent();
            delayWithGivenTime(4000);
            webadmin_productspage.Click_Save_button_on_AddNewProduct_Popup();
            delayWithGivenTime(2000);

            softassert.assertEquals(webadmin_productspage.validate_toaster_message_Text(), "Product Details Saved.", "Test Step - 7: Product Saved Successfully toaster message is not displayed");
            delayWithGivenTime(2000);
            softassert.assertEquals(webadmin_productspage.get_Displayed_itemcode_on_basic_detailsTab(), item_code, "Test Step - 8: Entered Item Code is not displayed or not matched on basic details tab");
            softassert.assertEquals(webadmin_productspage.get_Displayed_itemname_on_basic_detailsTab(), item_name, "Test Step - 8: Entered Item Name is not displayed or not matched on basic details tab");

            delayWithGivenTime(1000);
            webadmin_productspage.select_Web_Categories();
            delayWithGivenTime(2000);
            //webadmin_productspage.Enable_Display_On_Home_Page();
            webadmin_productspage.select_Colors_On_Basic_Details_Tab();
            delayWithGivenTime(1000);
            webadmin_productspage.select_Addons_On_Basic_Details_Tab();
            delayWithGivenTime(2000);
            webadmin_productspage.select_Sales_Categories_On_Basic_Details_Tab("Birthday");
            delayWithGivenTime(1000);
            webadmin_productspage.search_Keyword_On_Basic_Details_Tab("color full flowers");
            delayWithGivenTime(1000);
            webadmin_productspage.description_On_Basic_Details_Tab(item_code + " " + item_name + " " + product_variant + " color full flowers");
            delayWithGivenTime(1000);
            webadmin_productspage.recipe_On_Basic_Details_Tab("Elegant Rose Medley " +
                    "\nIngredients - 12 Red Roses\n" +
                    "- 6 White Tulips\n" +
                    "- 3 Baby’s Breath stems," +
                    "\n Container Type - Glass Vase" +
                    "\nFiller Materials - Baby’s Breath,Eucalyptus " +
                    "\nLeavesArrangement Style - Classic European" +
                    "\nColor Theme - Red & White" +
                    "\nOccasion - Birthday");

            delayWithGivenTime(1000);
            webadmin_productspage.click_Update_Button_On_Basic_Details_Tab();

            delayWithGivenTime(2000);
            webadmin_productspage.click_Back_Button_On_Top();

            delayWithGivenTime(2000);
            webadmin_productspage.search_And_Select_Product(item_name);
            delayWithGivenTime(1000);
            softassert.assertEquals(webadmin_productspage.get_Displayed_Product_Name_On_Product_Details_TableGrid(), item_name, "Test Step - 9: Product name is not displayed or not matched on product details table grid");

            delayWithGivenTime(1000);
            webadmin_productspage.click_Filter_Button_On_Header();
            delayWithGivenTime(1000);
            webadmin_productspage.select_Product_Status_On_Filters_Popup("Active");
            delayWithGivenTime(1000);
            webadmin_productspage.click_Apply_Button_On_Filters_Popup();
            delayWithGivenTime(1000);
            softassert.assertEquals(webadmin_productspage.get_Displayed_Product_Name_On_Product_Details_TableGrid(), item_name, "Test Step - 9: Active Product name is not displayed on product details table grid");

            delayWithGivenTime(2000);
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            delayWithGivenTime(2000);

            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3 : Cash And Carry page is not displayed");
            cashandcarry.SelectShopName(prop.getProperty("shopname"));
            logger.info("User select the shop name");
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("shopname"), "Test Step - 4 : Shop name is not matched with selected shop name");

            //Test Step - 5
            delayWithGivenTime(2000);
            cashandcarry.SelectClerkName(prop.getProperty("clerkname"));
            logger.info("User select the clerk name");
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("clerkname"), "Test Step - 5 : Clerk name is not matched with selected clerk name");

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 6 : Employee name is not matched with selected employee name");

            // Test Step - 8
            cashandcarry.SearchAndSelectTheItemCode(item_code, item_name);
            softassert.assertEquals(cashandcarry.ItemCodeValueIsExists(), item_code, "Test Step - 8 : Item code is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), item_name + " " + product_variant, "Test Step - 8 : Item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 8 : Item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "30.67", "Test Step - 8 : Item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 8 : Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 8 : Item discount percentage is not matched with search and selected item code");

            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem(), "Test Step - 9 : Added item is not displayed on grid table row 1");
            softassert.assertEquals(cashandcarry.getAddedItemCode(), item_code, "Test Step - 9 : Added item code is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), item_name + " " + product_variant, "Test Step - 9 : Added item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Step - 9 : Added item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$30.67", "Test Step - 9 : Added item extended price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$30.67", "Test Step - 9 : Added item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 9 : Added item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 9 : Added item discount percentage is not matched with search and selected item code");

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 11 : Selected tax type is not displayed");
            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 11 : Selected occasion is not displayed");

            cashandcarry.Enter_Customer_Name_On_CashAndCarryPage(prop.getProperty("custfullname"));
            custname = cashandcarry.getDisplayedCustomerNameOnCCPage();
            custid = cashandcarry.get_Displayed_CustomerId();

            // Test Step - 12
            cashandcarry.ClickPayButton();
            delayWithGivenTime(2000);

            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 12 : Cash And Carry payment page is not displayed");

            // Test Step - 13
            cashandcarrypayment.ClickCashTab();
            cashandcarrypayment.EnterGivenAmount();

            softassert.assertEquals(cashandcarrypayment.getDisplayedCustomerNameOnCCPage(), custname, "Test Step - 13 - : Customer name is not displayed on Cash And Carry payment page when search & select the customer on this field");
            softassert.assertEquals(cashandcarrypayment.getCustomerIdDisplayed(), custid, "Test Step - 13 - : Customer ID is not displayed on Cash And Carry payment page when search & select the customer on this field");

            cashandcarrypayment.click_CrossIcon_On_AddCustomerSection_CashAndCarry_PaymentPage();

            cashandcarrypayment.SearchAndSelectCustomer(prop.getProperty("cust_firstName"), prop.getProperty("custfullname"));

            softassert.assertEquals(cashandcarrypayment.getDisplayedCustomerNameOnCCPage(), custname, "Test Step - 13 - : Search and Selected Customer name on Cash And Carry payment page is not displayed");
            softassert.assertEquals(cashandcarrypayment.getCustomerIdDisplayed(), custid, "Test Step - 13 - : Customer ID is not displayed on Cash And Carry payment page when search & select the customer on this field");

            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 13 : Success toast message is not displayed");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 13 : Order confirmation message is not displayed");

            delayWithGivenTime(1000);
            if (cashandcarrypayment.getConfirmationPopup()) {
                cashandcarrypayment.VerifyOrderConfirmationPopup();
                cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo();
                invoicenumber = cashandcarrypayment.GetInvoiceNumber();
                balanceAmount = cashandcarrypayment.GetTenderPrice();
            }

            delayWithGivenTime(2000);

            // Test Step - 14
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();

            // Test Step - 15
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);

            dashboardorder = new DashboardOrderPage();
            delayWithGivenTime(1000);

            //Test Step - 16
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 16 - Dashboard order page is not displayed");
            delayWithGivenTime(2000);

            dashboardorder.EnterGlobalSearch(invoicenumber);

            delayWithGivenTime(3000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoicenumber), "Test Step - 15 - Respective Invoice number : " + invoicenumber + " is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoicenumber), "Delivered", "Test Step - 11 - Order status is not displayed as delivered for cash and carry order");
        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }


    }
}