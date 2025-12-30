package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Delivery_Type;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T412_ProductSection_CreateRecipe_DisplayImagesDisabled_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private ProposalsPage proposal;
    private ManageProposalPage manageproposal;
    String proposalId;

    public static final String dataSheetName = "Hana_T198";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    /*@Epic("Phone Order Module - Delivery Type")
    @Test(enabled = false, groups = {"Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T412_ProductSection_CreateRecipe_DisplayImagesDisabled_Functionality_Test
            (String RecipieName, String ItemGallery, String ItemName, String Bridefname, String Bridelname, String Bride_phonenumber, String Bride_email, String Bride_City, String Bride_State, String Bride_Address,
             String Bride_Zipcode, String Ceremony_Location, String Ceremony_Address, String Ceremony_City, String Ceremony_State, String Ceremony_Zipcode, String Reception_Location, String Reception_Address, String Reception_City,
             String Reception_State, String Reception_Zipcode, String Wedding_Coordinator_fname, String Wedding_Coordinator_lname, String Wedding_Coordinator_Phonenumber, String Wedding_Coordinator_Email, String Wedding_Coordinator_City,
             String Wedding_Coordinator_State, String Wedding_Coordinator_Address, String Wedding_Coordinator_Zipcode, String item_category, String item_name, String item_description
            ) {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T412_ProductSection_CreateRecipe_DisplayImagesDisabled_Functionality_Test ****");
        logger.debug("capturing application debug logs....");
        try {

            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1: Login page is not displayed");
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
        *//*    dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));
*//*
            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3: Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3: Cash and carry option is not displayed");

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
            phoneorder.EnterReciFirstName(prop.getProperty("short_reci_fname"));
            phoneorder.SearchAndSelect_RecipientNameFromAutoSuggestion(prop.getProperty("recipient_firstName1"));
            delayWithGivenTime(2000);
            phoneorder.EnterReciAddress2(prop.getProperty("Reci_Address1_2"));
            phoneorder.SelectReciCountry(prop.getProperty("recipient_country1"));
            phoneorder.EnterReciPhone(prop.getProperty("recipient_phonenumber1"));
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(prop.getProperty("recipient_location1"));
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), "Abish", "Test Step - 8 - Entered first name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "David", "Test Step - 8 - Entered last name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress1(), prop.getProperty("cust_address1"), "Test Step - 8 - Entered address 1 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "112 Penny Ct,", "Test Step - 8 - Entered address 2 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), prop.getProperty("cust_zipcode"), "Test Step - 8 - Entered zipcode is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), prop.getProperty("cust_city"), "Test Step - 8 - Entered city is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), "956-655-0756", "Test Step - 8 - Recipient phone number is not displayed on phone order page recipient section");

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
            phoneorder.Enter_RecipeNameTextboxField(prop.getProperty("RecipeName"));
            softassert.assertEquals(phoneorder.get_recipename_OnRecipenameTextbox(), prop.getProperty("RecipeName"), "Test Step - 14 - Entered Recipe name " + prop.getProperty("RecipeName") + "is not displayed on create recipe popup");

            // Test Step - 15
            delayWithGivenTime(2000);
            phoneorder.Select_ItemGallery_onCreateRecipe(ItemGallery);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_Selected_ItemGallery_onCreateRecipe(), ItemGallery, "Test Step - 15 - Selected item " + ItemGallery + " is not displayed on item gallery dropdown at create recipe popup");

            // Test Step - 16
            delayWithGivenTime(3000);
            phoneorder.SearchAndSelect_ItemGallery(prop.getProperty("Component_itemgallery"), prop.getProperty("RecipeItemGallery2"));
            softassert.assertEquals(phoneorder.get_Searched_Item_Gallery(), prop.getProperty("Component_itemgallery"), "Test Step - 16 - Searched item " + prop.getProperty("Component_itemgallery") + " is not displayed on item gallery search textbox field at create recipe popup");

            // Test Step - 17
            delayWithGivenTime(4000);
            softassert.assertEquals(phoneorder.Verify_RecipeItemRow3_OnTableIsDisplayed(), prop.getProperty("Component_itemgallery"), "Test Step - 17 - Added Recipe item row 3 " + prop.getProperty("Component_itemgallery") + " is not displayed on item gallery table grid");

            // Test Step - 18
            phoneorder.Click_DisplayImage_ToogleButton_Off_CreateRecipePopup();

            // Test Step - 19
            phoneorder.Click_Recipe_SaveBtn();
            delayWithGivenTime(1000);
            phoneorder.Click_SaveOption_RecipePopup();

            // Test Step - 20
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Recipe saved...", "Test Step - 19: Recipe saved... success toaster message is not displayed");
            // softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Recipe successfully added to variant. ", "Test Step - 19: Recipe successfully added to variant. success toaster message is not displayed");

            delayWithGivenTime(3000);
            proposal = new ProposalsPage();
            proposal.ClickOnProposalsMenu();

            // Test Step - 21
            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(), "Test Step - 21 - Create a Proposal popup is not displayed");

            // Test Step - 22
            proposal.SearchandSelect_Customer_OnProposal(prop.getProperty("cust_firstName"));

            // Test Step - 23
            proposal.Click_AddProposal_On_CreateProposal_Popup();

            // Test Step - 24
            delayWithGivenTime(2000);
            manageproposal = new ManageProposalPage();
            softassert.assertEquals(manageproposal.get_ManageProposalPageTitle(), "Hana | ManageProposal", "Test Step - 24 - Manage Proposal page is not displayed");
            delayWithGivenTime(2000);
            manageproposal.Click_CoupleDetailsTab();
            proposalId = manageproposal.get_ProposalId();
            manageproposal.Select_Bride_Groom_DropDown1(prop.getProperty("Select_Groom"));
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Bride_FirstName(), "Abish", "Test Step - 24 - Entered bride first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_LastName(), "David", "Test Step - 24 - Entered bride last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_PhoneNumber(), "9566550756", "Test Step - 24 -Entered bride phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Email(), "hanaposqateam@gmail.com", "Test Step - 24 -Entered bride email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_City(), "San Diego", "Test Step - 24 -Entered bride city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_State(), "CA", "Test Step - 24 -Entered bride state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Address(), "3402 Park Blvd", "Test Step - 24 -Entered bride address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Zip(), "92103", "Test Step - 24 -Entered bride zip code is not displayed");
            delayWithGivenTime(2000);
            manageproposal.Select_Bride_Groom_DropDown2(prop.getProperty("Select_Bride"));
            delayWithGivenTime(2000);
            manageproposal.Enter_Groom_FirstName(Bridefname);
            manageproposal.Enter_Groom_LastName(Bridelname);
            manageproposal.Enter_Groom_PhoneNumber(Bride_phonenumber);
            manageproposal.Enter_Groom_Email(Bride_email);
            manageproposal.Enter_Groom_City(Bride_City);
            manageproposal.Enter_Groom_State(Bride_State);
            manageproposal.Enter_Groom_Address(Bride_Address);
            manageproposal.Enter_Groom_Zip(Bride_Zipcode);

            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Groom_FirstName(), "O'Brien", "Test Step - 24 -Entered groom first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_LastName(), "John", "Test Step - 24 -Entered groom last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_PhoneNumber(), "925-456-2305", "Test Step - 24 -Entered groom phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_Email(), "hanaposqateam@gmail.com", "Test Step - 24 -Entered groom email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_City(), "El Cajon", "Test Step - 24 -Entered groom city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_State(), "CA", "Test Step - 24 -Entered groom state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_Address(), "Blossom Valley", "Test Step - 24 -Entered groom address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_Zip(), "92021", "Test Step - 24 -Entered groom zip code is not displayed");
            delayWithGivenTime(2000);
            manageproposal.Click_Weddings_eventDetails_Tab();
            manageproposal.Enter_CeremonyLocation(Ceremony_Location);
            manageproposal.Enter_CeremonyDate_WithInNext30Days();
            manageproposal.Enter_CeremonyTime();
            manageproposal.Enter_Ceremony_Address(Ceremony_Address);
            manageproposal.Enter_Ceremony_City(Ceremony_City);
            manageproposal.Enter_Ceremony_State(Ceremony_State);
            manageproposal.Enter_Ceremony_Zip(Ceremony_Zipcode);
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_CeremonyLocation(), "The Ritz-Carlton, Lake Tahoe", "Test Step - 24 -Entered ceremony location is not displayed");
            //	softassert.assertEquals(manageproposal.get_Entered_CeremonyDate(), "Wed, 01 Oct 2022","Test Step - 24 -Entered ceremony date is not displayed");
            //	softassert.assertEquals(manageproposal.get_Entered_CeremonyTime(), "11:00 AM","Test Step - 24 -Entered ceremony time is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Ceremony_Address(), "13031 Ritz Carlton Highlands Court", "Test Step - 24 -Entered ceremony address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Ceremony_City(), "Truckee", "Test Step - 24 -Entered ceremony city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Ceremony_State(), "CA", "Test Step - 24 -Entered ceremony state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Ceremony_Zip(), "96161", "Test Step - 24 -Entered ceremony zip code is not displayed");
            delayWithGivenTime(2000);
            manageproposal.Enter_ReceptionLocation(Reception_Location);
            manageproposal.Enter_ReceptionDate_WithInNext30Days();
            manageproposal.Enter_ReceptionTime();
            manageproposal.Enter_Reception_Address(Reception_Address);
            manageproposal.Enter_Reception_City(Reception_City);
            manageproposal.Enter_Reception_State(Reception_State);
            manageproposal.Enter_Reception_Zip(Reception_Zipcode);
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_ReceptionLocation(), "Rutherford Wedding Venue", "Test Step - 24 -Entered reception location is not displayed");
            //	softassert.assertEquals(manageproposal.get_Entered_CeremonyDate(), "Wed, 01 Oct 2022","Test Step - 24 -Entered ceremony date is not displayed");
            //	softassert.assertEquals(manageproposal.get_Entered_CeremonyTime(), "11:00 AM","Test Step - 24 -Entered ceremony time is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Reception_Address(), "180 Rutherford Hill Rd, Rutherford", "Test Step - 24 -Entered reception address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Reception_City(), "Rutherford", "Test Step - 24 -Entered reception city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_reception_State(), "CA", "Test Step - 24 -Entered reception state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Reception_Zip(), "94573", "Test Step - 24 -Entered reception zip code is not displayed");
            delayWithGivenTime(2000);
            manageproposal.Enter_WeddingCoordinator_FirstName(Wedding_Coordinator_fname);
            manageproposal.Enter_WeddingCoordinator_LastName(Wedding_Coordinator_lname);
            manageproposal.Enter_WeddingCoordinator_PhoneNumber("604-685-5761");
            manageproposal.Enter_WeddingCoordinator_Email(Wedding_Coordinator_Email);
            manageproposal.Enter_WeddingCoordinator_City(Wedding_Coordinator_City);
            manageproposal.Enter_WeddingCoordinator_State(Wedding_Coordinator_State);
            manageproposal.Enter_WeddingCoordinator_Address(Wedding_Coordinator_Address);
            manageproposal.Enter_WeddingCoordinator_Zip(Wedding_Coordinator_Zipcode);

            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_FirstName(), "Daniel", "Test Step - 24 -Entered Wedding Coordinator first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_LastName(), "Wilton", "Test Step - 24 -Entered Wedding Coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_PhoneNumber(), "604-685-5761", "Test Step - 24 -Entered Wedding Coordinator phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_Email(), "hanaposqateam@gmail.com", "Test Step - 24 -Entered Wedding Coordinator email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_City(), "North Vancouver", "Test Step - 24 -Entered Wedding Coordinator city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_State(), "BC", "Test Step - 24 -Entered Wedding Coordinator state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_Address(), "1433 Pemberton Ave", "Test Step - 24 -Entered Wedding Coordinator address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_Zip(), "V7P 2R8", "Test Step - 24 -Entered Wedding Coordinator zip code is not displayed");

            // Test Step - 25
            delayWithGivenTime(2000);
            manageproposal.Click_DocumentsAndImages_Tab();
            delayWithGivenTime(2000);
            manageproposal.UploadFiles("roses red.jpg");
            delayWithGivenTime(2000);
            manageproposal.UploadFiles("samplepdf.pdf");
            delayWithGivenTime(2000);

            // Test Step - 26
            manageproposal.Click_ItemsAndProducts_Tab();

            // Test Step - 27
            delayWithGivenTime(2000);
            manageproposal.Select_CategoryDropdown_On_ItemsAndProducts(item_category);
            manageproposal.Enter_ItemName_On_ItemsAndProducts(item_name, item_description);
            manageproposal.Click_AddBtn_On_ItemsAndProducts();
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Row1_ItemCategory(), "Mother's Day", "Test Step - 27 - Selected category is not displayed on row 1 product table grid");
            softassert.assertEquals(manageproposal.get_row1_itemCode(), "rrd", "Test Step - 27 - Item code is not displayed on row 1 product table grid");
            softassert.assertEquals(manageproposal.get_row1_itemDescription(), "Red Rose Small", "Test Step - 27 - Item description is not displayed on row 1 product table grid");
            softassert.assertEquals(manageproposal.get_row1_itemQuantity(), "1", "Test Step - 27 - Item quantity is not displayed on row 1 product table grid");
            softassert.assertEquals(manageproposal.get_row1_item_TotalPrice(), "$199.00", "Test Step - 27 - Item total price is not displayed on row 1 product table grid");

            // Test Step - 28
            manageproposal.Click_Publish_Pending_Changes();
            softassert.assertTrue(manageproposal.Verify_Proposal_Pending_Changes_AlertIsDisplayed(), "Test Step - 28 - Publish pending changes alert popup is not displayed");

            // Test Step - 29
            delayWithGivenTime(2000);
            manageproposal.Click_PublishBtn_On_AlertPopup();
            proposal = new ProposalsPage();
            delayWithGivenTime(3000);
            softassert.assertEquals(proposal.get_ProposalsViewPageTitle(), "Hana | View Proposal", "Test Step - 29 - View proposal page is not displayed");

            // Test Step - 30
            delayWithGivenTime(2000);
            proposal.Enter_ProposalId_In_Proposal_GlobalSearchBox(proposalId);
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_Selected_Proposal_StatusType(), "Accepted", "Test Step - 19 - Selected proposal status type is not displayed");
            delayWithGivenTime(3000);
            proposal.Click_Proposal_Action_Button();

            // Test Step - 31
            proposal.Click_Proposal_CustProposalView_Button();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_ItemNameIsDisplayed_on_CustProposalView(), "Test Step - 31 - Item name is not displayed on customer proposal view");

        } catch (Exception e) {
            e.printStackTrace();
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }*/
}