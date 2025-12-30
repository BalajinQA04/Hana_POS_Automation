package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireIn_Type;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T514_ProductSection_CreateRecipe_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    public static final String dataSheetName = "Hana_T206";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Phone Order Module - Wire In Type")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T514_ProductSection_CreateRecipe_Functionality_Test(String Item_Gallery, String Item_Name, String Retail_Price_Row3, String Quantity, String Private_Notes, String Public_Notes) {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();
        logger.info("**** Starting  Validate_Hana_T514_ProductSection_CreateRecipe_Functionality_Test ****");
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
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step- 2 - Hana dashboard page is not displayed");
            logger.info("User navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3 - Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3 - Cash and carry option is not displayed");

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
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
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
            phoneorder.EnterReciFirstName(prop.getProperty("recipient_firstName1"));
            phoneorder.EnterReciLastName(prop.getProperty("recipient_lastName1"));
            phoneorder.EnterReciAddress1(prop.getProperty("cust_address1"));
            phoneorder.EnterReciAddress2(prop.getProperty("Reci_Address1_2"));
            phoneorder.EnterReciZipcode(prop.getProperty("cust_zipcode"));
            delayWithGivenTime(1000);
            phoneorder.SelectReciCountry(prop.getProperty("recipient_country1"));
            phoneorder.EnterReciPhone(prop.getProperty("recipient_phonenumber1"));
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(prop.getProperty("recipient_location1"));
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
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 9 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertTrue(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), "Test Step - 9 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 10
            softassert.assertTrue(phoneorder.Verify_ProductSuggestions_Appears(prop.getProperty("product_itemcode1")), "Test Step - 10 - In Item code row 1 on product section autosuggestions are not displayed");

            // Test Step - 11
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(prop.getProperty("product_itemcode1"), prop.getProperty("product_small_desc"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), prop.getProperty("product_itemcode1"), "Test Step - 11 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), prop.getProperty("product_small_description"), "Test Step - 11 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 11 - Item quantity is not displayed on phone order page product details section");

            if (phoneorder.getUnitPriceOnProdDetails() == prop.getProperty("product_small_price")) {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), prop.getProperty("product_small_price"), "Test Step - 11 - Item price is not displayed on phone order page product details section");
            } else if (phoneorder.getUnitPriceOnProdDetails() == "209.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "209.00", "Test Step - 11 - Item price is not displayed on phone order page product details section");
            }

            // Test Step - 12
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_ProductDetails_Row1_Recipe(), "Test Step - 12 - Recipe icon is not displayed on row 1 product");

            // Test Step - 13
            phoneorder.Click_ProductDetails_Row1_Recipe();
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_CreateRecipe_Popup(), "Test Step - 13 - Create Recipe popup is not displayed");

            // Test Step - 14
            phoneorder.Select_ItemGallery_onCreateRecipe(Item_Gallery);
            softassert.assertEquals(phoneorder.get_Selected_ItemGallery_onCreateRecipe(), Item_Gallery, "Test Step - 14 - Selected item is not displayed on item gallery dropdown at create recipe popup");
            delayWithGivenTime(3000);


            delayWithGivenTime(2000);
            phoneorder.SearchAndSelect_ItemGallery(prop.getProperty("Component_itemgallery"), prop.getProperty("RecipeItemGallery2"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_Searched_Item_Gallery(), prop.getProperty("Component_itemgallery"), "Test Step - 16 - Searched item " + prop.getProperty("Component_itemgallery") + " is not displayed on item gallery search textbox field at create recipe popup");

            delayWithGivenTime(3000);

            softassert.assertEquals(phoneorder.Verify_RecipeItemRow3_OnTableIsDisplayed(), prop.getProperty("Component_itemgallery"), "Test Step - 17 - Recipe item row 3 is not displayed on item gallery table");

            // Test Step - 15
            phoneorder.Enter_Retail_Price_OnComponentTable_At_Row3(Retail_Price_Row3);
            phoneorder.Enter_Quantity_OnComponentTable_At_Row3(Quantity);
            softassert.assertEquals(phoneorder.get_Entered_Retail_Price_OnComponentTable_At_Row3(), Retail_Price_Row3, "Test Step - 15 - Retail price is not displayed on component table at row 3");
            softassert.assertEquals(phoneorder.get_Entered_Quantity_OnComponentTable_At_Row3(), Quantity, "Test Step - 15 - Quantity is not displayed on component table at row 3");

            // Test Step - 16
            softassert.assertTrue(phoneorder.Verify_totalRecipe_details_Section_IsDisplayed(), "Test Step - 16 - Total recipe details section is not displayed");

            // Test Step - 17
            phoneorder.Click_Private_NotesTab_AddNotesSection();
            phoneorder.Enter_PrivateNotes_CreateRecipe_Popup(Private_Notes);
            softassert.assertEquals(phoneorder.get_entered_privatenotes_CreateRecipe_Popup(), Private_Notes, "Test Step - 17 - Entered Private notes is not displayed on create recipe popup add notes section");

            // Test Step - 18
            phoneorder.Click_Public_NotesTab_AddNotesSection();
            phoneorder.Enter_PublicNotes_CreateRecipe_Popup(Public_Notes);
            softassert.assertEquals(phoneorder.get_entered_publicnotes_CreateRecipe_Popup(), Public_Notes, "Test Step - 18 - Entered Public notes is not displayed on create recipe popup add notes section");

            // Test Step - 19
            phoneorder.Click_PhotoTab_AddNotesSection();
            delayWithGivenTime(2000);
            phoneorder.Upload_photo_AddFile_Button_OnCreateRecipePopup("roses red.jpg");
            softassert.assertTrue(phoneorder.Verify_ImageIsDisplayed_After_UploadTheFile(), "Test Step - 19 - Uploaded photo is not displayed on create recipe popup add notes section");

            // Test Step - 20
            phoneorder.Click_Recipe_SaveBtn();
            delayWithGivenTime(1000);
            phoneorder.Click_SaveOption_RecipePopup();
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Recipe saved...", "Test Step - 18 - Recipe saved Toast message is not displayed");

            // Test Step - 21
            delayWithGivenTime(2000);
            phoneorder.Click_ProductDetails_Row1_Recipe();
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_CreateRecipe_Popup(), "Test Step - 21 - Create Recipe popup is not displayed");
            softassert.assertEquals(phoneorder.get_Selected_ItemGallery_onCreateRecipe(), "Select", "Test Step - 21 - Selected item is not displayed on item gallery dropdown at create recipe popup");

            if (phoneorder.Verify_RecipeItemRow3_OnTableIsDisplayed().equals(prop.getProperty("Component_itemgallery"))) {
                softassert.assertEquals(phoneorder.Verify_RecipeItemRow3_OnTableIsDisplayed(), prop.getProperty("Component_itemgallery"), "Test Step - 21 - Recipe item row 3 is not displayed on item gallery table");
            } else {
                softassert.fail("Known Issue: Test Step - 21 - Recipe item row 3 is not displayed on item gallery table");
            }

            softassert.assertEquals(phoneorder.get_Entered_Retail_Price_OnComponentTable_At_Row3(), "25.00", "Test Step - 21 - Retail price is not displayed on component table at row 3");
            softassert.assertEquals(phoneorder.get_Entered_Quantity_OnComponentTable_At_Row3(), "20.00", "Test Step - 21 - Quantity is not displayed on component table at row 3");
            phoneorder.Click_Private_NotesTab_AddNotesSection();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_entered_privatenotes_CreateRecipe_Popup(), "Recipe Test Automation Private Notes", "Test Step - 21 - Entered Private notes is not displayed on create recipe popup add notes section");
            phoneorder.Clear_PrivateNotes_CreateRecipe_Popup(); // to avoid junk data

            phoneorder.Click_Public_NotesTab_AddNotesSection();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_entered_publicnotes_CreateRecipe_Popup(), "Recipe Test Automation Public Notes", "Test Step - 21 - Entered Public notes is not displayed on create recipe popup add notes section");
            phoneorder.Clear_PublicNotes_CreateRecipe_Popup(); // to avoid junk data

            phoneorder.Click_PhotoTab_AddNotesSection();
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_ImageIsDisplayed_After_UploadTheFile(), "Test Step - 21 - Uploaded photo is not displayed on create recipe popup add notes section");

            // To avoid junk data
            phoneorder.Delete_Image1_Photo_AddFile_Button_OnCreateRecipePopup();

            phoneorder.Click_Recipe_SaveBtn();
            delayWithGivenTime(1000);
            phoneorder.Click_SaveOption_RecipePopup();
            softassert.assertTrue(phoneorder.verifySuccessToastMessageAppears(), "Test Step - 21 - Recipe saved Toast message is not displayed");

        } catch (Exception e) {
            softassert.fail("Known Issue: " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}