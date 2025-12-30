package com.hanapos.testcases.All_Orders_Page_Testcases;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.LoggerUtil;
import com.hanapos.utilities.TestData;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Add_Proposal_CreateProposalPage_Event_Cancelled_Order_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private ProposalsPage proposal;
    private ManageProposalPage manageproposal;
    private DashboardOrderPage dashboardorder;
    String proposalId;
    String invoiceNumber;
    String total_amount;
    public static LoggerUtil logger_Util;
    Faker faker = new Faker();

    @Epic("All Orders Page Module")
    @Test(enabled = true, groups = {"Regression", "Smoke", "Sanity"})
    public void Validate_Add_Proposal_CreateProposalPage_Event_Cancelled_Order_Functionality() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();

        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");
            logger_Util.startNetworkLogging(testCaseName);

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
            dashboard.SelectShopNameDropDown(prop.getProperty("cashandcarryshopname"));

            // Test Step - 4
            proposal = new ProposalsPage();
            proposal.ClickOnProposalsMenu();

            // Test Step - 5
            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(), "Test Step - 21 - Create a Proposal popup is not displayed");

            // Test Step - 6
            proposal.Click_event_tab_on_Createproposal_popup();
            delayWithGivenTime(1000);
            proposal.SearchandSelect_Customer_OnProposal(prop.getProperty("cust_firstName"));
            proposal.Click_CancelButton_On_createproposal_popup();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.isCreateProposalPopupClosed(), "Test Step - 6: Create proposal popup is not closed on view proposal page");

            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(), "Test Step - 7 - Create a Proposal popup is not displayed on view proposal page");

            // Test Step - 7
            proposal.Click_event_tab_on_Createproposal_popup();
            delayWithGivenTime(1000);
            proposal.SearchandSelect_Customer_OnProposal(prop.getProperty("cust_firstName"));
            softassert.assertEquals(proposal.Verify_CustomerNameIsDisplayed_On_SearchTextBox(), prop.getProperty("custfullname"), "Test Step - 6: Searched customer full name is not displayed on create proposal popup");
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_createproposalpopup_firstname_field(), prop.getProperty("cust_firstName"), "Test Step - 6: First name of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_lasttname_field(), prop.getProperty("cust_lastName"), "Test Step - 6: Last name of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_companyname_field(), prop.getProperty("cust_companyName"), "Test Step - 6: COmpany Name of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_email_field(), prop.getProperty("cust_email"), "Test Step - 6: Email Id of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_city_field(), prop.getProperty("cust_city"), "Test Step - 6: City of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_state_field(), prop.getProperty("cust_state"), "Test Step - 6: State of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_address_field(), prop.getProperty("cust_address1"), "Test Step - 6: Address of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_zipcode_field(), prop.getProperty("cust_zipcode"), "Test Step - 6: Zipcode of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_phonenumber_field(), prop.getProperty("cust_phoneNumber"), "Test Step - 6: Phone number of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_altphonenumber_field(), prop.getProperty("cust_Alt_phoneNumber"), "Test Step - 6: Alternative Phone number of customer is not displayed in create proposal popup");
            delayWithGivenTime(2000);
            //    proposal.Select_createproposalpopup_selectreport_dropdownfield("Conceptual Design1");
            //    proposal.Select_createproposalpopup_selectpackage_dropdownfield("Gold Event Package");
            //    proposal.Select_createproposalpopup_select_eventorwedding_dropdownfield("Abish");
            delayWithGivenTime(2000);
            // softassert.assertEquals(proposal.get_Select_createproposalpopup_selectreport_dropdownfield(), "Conceptual Design1", "Test Step - 7: Selected report is not displayed in create proposal popup");
            // softassert.assertEquals(proposal.get_createproposalpopup_selectpackage_dropdownfield(), "Gold Event Package", "Test Step - 7: Selected package is not displayed in create proposal popup");
            // softassert.assertEquals(proposal.get_createproposalpopup_select_eventorwedding_dropdownfield(), "Abish", "Test Step - 7: Selected event or wedding consultant is not displayed in create proposal popup");

            delayWithGivenTime(2000);
            proposal.Click_AddProposal_On_CreateProposal_Popup();

            // Test Step - 8
            delayWithGivenTime(2000);
            manageproposal = new ManageProposalPage();
            proposalId = manageproposal.get_ProposalId();
            softassert.assertEquals(manageproposal.get_ManageProposalPageTitle(), "Hana | ManageProposal", "Test Step - 8 - Manage Proposal page is not displayed");
            delayWithGivenTime(2000);

            // Test Step - 9
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_FirstName(), prop.getProperty("cust_firstName"), "Test Step - 9 - Customer first name is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_LastName(), prop.getProperty("cust_lastName"), "Test Step - 9 - Customer last name is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_PhoneNumber(), "9566550756", "Test Step - 9 - Customer phonenumber is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Email(), prop.getProperty("cust_email"), "Test Step - 9 - Customer Email Id is not autopopulated in customer details section ");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_City(), prop.getProperty("cust_city"), "Test Step - 9 - Customer City is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_State(), prop.getProperty("cust_state"), "Test Step - 9 - Customer State is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Address(), prop.getProperty("cust_address1"), "Test Step - 9 - Customer Address is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Zip(), prop.getProperty("cust_zipcode"), "Test Step - 9 - Customer Zipcode is not autopopulated in customer details section ");
            delayWithGivenTime(2000);
//======================================================================================

            // Test Step - 10
            // Generate dynamic test data
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String phoneNumber = faker.numerify("###-###-####");
            String email = faker.internet().emailAddress();
            String city = faker.address().city();
            String state = faker.address().stateAbbr();
            String address = faker.address().streetAddress();
            String zip = get_Random_US_ZipCode();

            String altFirstName = faker.name().firstName();
            String altLastName = faker.name().lastName();
            String altPhoneNumber = faker.numerify("###-###-####");
            String altEmail = faker.internet().emailAddress();
            String altCity = faker.address().city();
            String altState = faker.address().stateAbbr();
            String altAddress = faker.address().streetAddress();
            String altZip = get_Random_US_ZipCode();

            String eventName = faker.company().name();
            String eventLocation = faker.address().streetName();
            String eventAddress = faker.address().streetAddress();
            String eventZip = get_Random_US_ZipCode();
            String eventCity = faker.address().city();
            String eventState = faker.address().stateAbbr();

            String coordFirstName = faker.name().firstName();
            String coordLastName = faker.name().lastName();
            String coordPhone = faker.numerify("###-###-####");
            String coordEmail = faker.internet().emailAddress();
            String coordAddress = faker.address().fullAddress();
            String coordZip = get_Random_US_ZipCode();
            String coordCity = faker.address().city();
            String coordState = faker.address().stateAbbr();

            // Test Step - 10 - Customer Info
            manageproposal.Enter_EventCustSection_FirstName(firstName);
            manageproposal.Enter_Event_CustSection_LastName(lastName);
            manageproposal.Enter_Event_CustSection_PhoneNumber(phoneNumber);
            manageproposal.Enter_Event_CustSection_Email(email);
            manageproposal.Enter_Event_CustSection_City(city);
            manageproposal.Enter_Event_CustSection_State(state);
            manageproposal.Enter_Event_CustSection_Address(address);
            manageproposal.Enter_Event_CustSection_Zip(zip);

            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_FirstName(), firstName, "Test Step - 10 - Entered customer first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_LastName(), lastName, "Test Step - 10 - Entered customer last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_PhoneNumber(), phoneNumber, "Test Step - 10 - Entered customer phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Email(), email, "Test Step - 10 - Entered customer email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_City(), city, "Test Step - 10 - Entered customer city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_State(), state, "Test Step - 10 - Entered customer state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Address(), address, "Test Step - 10 - Entered customer address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Zip(), zip, "Test Step - 10 - Entered customer zip code is not displayed");

            // Test Step - 11 - Alternate Contact
            manageproposal.enter_AltContact_FirstName(altFirstName);
            manageproposal.enter_AltContact_LastName(altLastName);
            manageproposal.enter_AltContact_PhoneNumber(altPhoneNumber);
            manageproposal.enter_AltContact_EmailID(altEmail);
            manageproposal.enter_AltContact_Address(altAddress);
            manageproposal.enter_AltContact_City(altCity);
            manageproposal.enter_AltContact_State(altState);
            manageproposal.enter_AltContact_Zipcode(altZip);
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_AltContact_FirstName(), altFirstName, "Test Step - 11 - Alternate Entered first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_LastName(), altLastName, "Test Step - 11 - Alternate Entered last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_PhoneNumber(), altPhoneNumber, "Test Step - 11 - Alternate Entered phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_EmailID(), altEmail, "Test Step - 11 - Alternate Entered email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_City(), altCity, "Test Step - 11 - Alternate Entered city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_State(), altState, "Test Step - 11 - Alternate Entered state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_Address(), altAddress, "Test Step - 11 - Alternate Entered address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_Zipcode(), altZip, "Test Step - 11 - Alternate Entered zip code is not displayed");

            // Test Step - 12 - Event Details
            manageproposal.Click_On_eventdetailstab();
            manageproposal.enter_Event_Name(eventName);
            manageproposal.enter_Event_Location(eventLocation);
            manageproposal.enter_Event_Date();
            manageproposal.enter_Event_Time(); // or pass eventTime if needed
            manageproposal.enter_Event_Address(eventAddress);
            manageproposal.enter_Event_ZipCode(eventZip);
// manageproposal.enter_Event_City(eventCity);
// manageproposal.enter_Event_State(eventState);
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Event_Name(), eventName, "Test Step - 12 - Entered Event Name is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Location(), eventLocation, "Test Step - 12 - Entered Event Location is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Date(), Next_20Days_Date(), "Test Step - 12 - Entered Event Date is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Time(), prop.getProperty("Event_Time"), "Test Step - 12 - Entered Event Time is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Address(), eventAddress, "Test Step - 12 - Entered Event Address is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_ZipCode(), eventZip, "Test Step - 12 - Entered Event Zipcode is not displayed correctly");
            // softassert.assertEquals(manageproposal.get_Entered_Event_City(), eventCity, ...);
            // softassert.assertEquals(manageproposal.get_Entered_Event_State(), eventState, ...);

            // Test Step - 13 - Event Coordinator
            manageproposal.Enter_FirstName_Event_Coordinator(coordFirstName);
            manageproposal.Enter_LastName_Event_Coordinator(coordLastName);
            manageproposal.Enter_Phonenumber_Event_Coordinator(coordPhone);
            manageproposal.Enter_EmailId_Event_Coordinator(coordEmail);
            manageproposal.Enter_Address_Event_Coordinator(coordAddress);
            manageproposal.Enter_Zipcode_Event_Coordinator(coordZip);
            // manageproposal.Enter_City_Event_Coordinator(coordCity);
            // manageproposal.Enter_State_Event_Coordinator(coordState);
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_entered_firstname_event_coordinator(), coordFirstName, "Test Step - 13 - Entered first name is not displayed properly on event coordinator");
            softassert.assertEquals(manageproposal.get_entered_lastname_event_coordinator(), coordLastName, "Test Step - 13 - Entered last name is not displayed properly on event coordinator");
            softassert.assertEquals(manageproposal.get_entered_phonenumber_event_coordinator(), coordPhone, "Test Step - 13 - Entered phone number is not displayed properly on event coordinator");
            softassert.assertEquals(manageproposal.get_entered_emailid_event_coordinator(), coordEmail, "Test Step - 13 - Entered email is not displayed properly on event coordinator");
            softassert.assertEquals(manageproposal.get_entered_address_event_coordinator(), coordAddress, "Test Step - 13 - Entered address is not displayed properly on event coordinator");
            softassert.assertEquals(manageproposal.get_entered_zipcode_event_coordinator(), coordZip, "Test Step - 13 - Entered zip code is not displayed properly on event coordinator");
// softassert.assertEquals(manageproposal.get_entered_city_event_coordinator(), coordCity, ...);
// softassert.assertEquals(manageproposal.get_entered_state_event_coordinator(), coordState, ...);



         /*   manageproposal.Enter_EventCustSection_FirstName("O'Brien");
            manageproposal.Enter_Event_CustSection_LastName("John");
            manageproposal.Enter_Event_CustSection_PhoneNumber("925-456-2305");
            manageproposal.Enter_Event_CustSection_Email("hanaposqateam@gmail.com");
            manageproposal.Enter_Event_CustSection_City("El Cajon");
            manageproposal.Enter_Event_CustSection_State("CA");
            manageproposal.Enter_Event_CustSection_Address("Blossom Valley");
            manageproposal.Enter_Event_CustSection_Zip("92021");

            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_FirstName(), "O'Brien", "Test Step - 10 - Entered customer first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_LastName(), "John", "Test Step - 10 - Entered customer last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_PhoneNumber(), "925-456-2305", "Test Step - 10 - Entered customer phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Email(), "hanaposqateam@gmail.com", "Test Step - 10 - Entered customer email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_City(), "El Cajon", "Test Step - 10 - Entered customer city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_State(), "CA", "Test Step - 10 - Entered customer state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Address(), "Blossom Valley", "Test Step - 10 - Entered customer address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Zip(), "92021", "Test Step - 10 - Entered customer zip code is not displayed");

            // Test Step - 11
            manageproposal.enter_AltContact_FirstName(prop.getProperty("AltContact_FirstName_1"));
            manageproposal.enter_AltContact_LastName(prop.getProperty("AltContact_LastName_1"));
            manageproposal.enter_AltContact_PhoneNumber(prop.getProperty("AltContact_PhoneNumber_1"));
            manageproposal.enter_AltContact_EmailID(prop.getProperty("AltContact_EmailID_1"));
            manageproposal.enter_AltContact_Address(prop.getProperty("AltContact_Address_1"));
            manageproposal.enter_AltContact_City(prop.getProperty("AltContact_City_1"));
            manageproposal.enter_AltContact_State(prop.getProperty("AltContact_State_1"));
            manageproposal.enter_AltContact_Zipcode(prop.getProperty("AltContact_Zipcode_1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_AltContact_FirstName(), prop.getProperty("AltContact_FirstName_1"), "Test Step - 11 - Alternate Entered first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_LastName(), prop.getProperty("AltContact_LastName_1"), "Test Step - 11 - Alternate Entered last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_PhoneNumber(), prop.getProperty("AltContact_PhoneNumber_1"), "Test Step - 11 - Alternate Entered phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_EmailID(), prop.getProperty("AltContact_EmailID_1"), "Test Step - 11 - Alternate Entered email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_City(), prop.getProperty("AltContact_City_1"), "Test Step - 11 - Alternate Entered city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_State(), prop.getProperty("AltContact_State_1"), "Test Step - 11 - Alternate Entered state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_Address(), prop.getProperty("AltContact_Address_1"), "Test Step - 11 - Alternate Entered address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_Zipcode(), prop.getProperty("AltContact_Zipcode_1"), "Test Step - 11 - Alternate Entered zip code is not displayed");

            // Test Step - 12
            manageproposal.Click_On_eventdetailstab();
            manageproposal.enter_Event_Name(prop.getProperty("Event_Name"));
            manageproposal.enter_Event_Location(prop.getProperty("Event_Location"));
            manageproposal.enter_Event_Date();
            manageproposal.enter_Event_Time();
            manageproposal.enter_Event_Address(prop.getProperty("Event_Address"));
            manageproposal.enter_Event_ZipCode(prop.getProperty("Event_Zipcode"));
            //  manageproposal.enter_Event_City(prop.getProperty("Event_City"));
            //  manageproposal.enter_Event_State(prop.getProperty("Event_State"));
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Event_Name(), prop.getProperty("Event_Name"), "Test Step - 12 - Entered Event Name is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Location(), prop.getProperty("Event_Location"), "Test Step - 12 - Entered Event Location is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Date(), Next_20Days_Date(), "Test Step - 12 - Entered Event Date is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Time(), prop.getProperty("Event_Time"), "Test Step - 12 - Entered Event Time is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Address(), prop.getProperty("Event_Address"), "Test Step - 12 - Entered Event Address is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_ZipCode(), prop.getProperty("Event_Zipcode"), "Test Step - 12 - Entered Event Zipcode is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_City(), prop.getProperty("Event_City"), "Test Step - 12 - Entered Event City is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_State(), prop.getProperty("Event_State"), "Test Step - 12 - Entered Event State is not displayed correctly");

            // Test Step - 13
            manageproposal.Enter_FirstName_Event_Coordinator(prop.getProperty("Event_Coordinator_Fname"));
            manageproposal.Enter_LastName_Event_Coordinator(prop.getProperty("Event_Coordinator_Lname"));
            manageproposal.Enter_Phonenumber_Event_Coordinator(prop.getProperty("Event_Coordinator_Phonenumber"));
            manageproposal.Enter_EmailId_Event_Coordinator(prop.getProperty("Event_Coordinator_EmailId"));
            manageproposal.Enter_Address_Event_Coordinator(prop.getProperty("Event_Coordinator_Address"));
            manageproposal.Enter_Zipcode_Event_Coordinator(prop.getProperty("Event_Coordinator_Zipcode"));
            // manageproposal.Enter_City_Event_Coordinator(prop.getProperty("Event_Coordinator_City"));
            // manageproposal.Enter_State_Event_Coordinator(prop.getProperty("Event_Coordinator_State"));
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_entered_firstname_event_coordinator(), prop.getProperty("Event_Coordinator_Fname"), "Test Step - 13: Entered event coordinator first name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_lastname_event_coordinator(), prop.getProperty("Event_Coordinator_Lname"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_phonenumber_event_coordinator(), prop.getProperty("Event_Coordinator_Phonenumber"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_emailid_event_coordinator(), prop.getProperty("Event_Coordinator_EmailId"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_address_event_coordinator(), prop.getProperty("Event_Coordinator_Address"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_zipcode_event_coordinator(), prop.getProperty("Event_Coordinator_Zipcode"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_city_event_coordinator(), prop.getProperty("Event_Coordinator_City"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_state_event_coordinator(), prop.getProperty("Event_Coordinator_State"), "Test Step - 13: Entered event coordinator last name is not displayed");
*///===========================================================================

            // Test Step - 14
            delayWithGivenTime(2000);
            manageproposal.Click_DocumentsAndImages_Tab();
            delayWithGivenTime(2000);
            manageproposal.UploadFiles("roses red.jpg");
            delayWithGivenTime(2000);
            softassert.assertTrue(manageproposal.verify_uploaded_firstdocument_displayed_on_grid(), "Test Step - 10: Uploaded first document is not displayed on documents and images tab");

            // Test Step - 15
            manageproposal.Click_ItemsAndProducts_Tab();

            // Test Step - 16
            delayWithGivenTime(2000);
            manageproposal.Select_CategoryDropdown_On_ItemsAndProducts("Body Care");
            manageproposal.Enter_ItemName_On_ItemsAndProducts("bh_1", "bh_1 Standard");
            manageproposal.Click_AddBtn_On_ItemsAndProducts();
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Row1_ItemCategory(), "Body Care", "Test Step - 16 - Selected category is not displayed on row 1 product table grid");
            softassert.assertEquals(manageproposal.get_row1_itemCode(), "bh_1", "Test Step - 16 - Item code is not displayed on row 1 product table grid");
            softassert.assertEquals(manageproposal.get_row1_itemDescription(), "Blessed Heart Standard", "Test Step - 16 - Item description is not displayed on row 1 product table grid");
            softassert.assertEquals(manageproposal.get_row1_itemQuantity(), "1", "Test Step - 16 - Item quantity is not displayed on row 1 product table grid");
            softassert.assertEquals(manageproposal.get_row1_item_TotalPrice(), "$249.99", "Test Step - 16 - Item total price is not displayed on row 1 product table grid");

            // Test Step - 17
            manageproposal.Click_Publish_Pending_Changes();
            softassert.assertTrue(manageproposal.Verify_Proposal_Pending_Changes_AlertIsDisplayed(), "Test Step - 17 - Publish pending changes alert popup is not displayed");

            // Test Step - 18
            delayWithGivenTime(2000);
            manageproposal.Click_PublishBtn_On_AlertPopup();
            proposal = new ProposalsPage();
            delayWithGivenTime(3000);
            softassert.assertEquals(proposal.get_ProposalsViewPageTitle(), "Hana | View Proposal", "Test Step - 18 - View proposal page is not displayed");

            // Test Step - 19
            dashboardorder = new DashboardOrderPage();
            delayWithGivenTime(2000);
            proposal.Enter_ProposalId_In_Proposal_GlobalSearchBox(proposalId);
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_Selected_Proposal_StatusType(), "Accepted", "Test Step - 19 - Selected proposal status type is not displayed");

            delayWithGivenTime(3000);
            invoiceNumber = proposal.get_Invoice_Number_on_Created_Proposal();
            total_amount = proposal.get_Total_Amount_on_Created_Proposal();
            proposal.Click_on_Invoice_Number_on_Created_Proposal();

            delayWithGivenTime(3000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 18 - In orders summary page event proposal invoice number is not displayed for placed order");
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoiceNumber), "Event proposal", "Test Step - 18 - Order type is not displayed as Event proposal");
            softassert.assertEquals(dashboardorder.validate_DeliveryType_On_AllOrdersPage(invoiceNumber), "Delivery", "Test Step - 18 - Delivery type is not displayed for placed order");
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoiceNumber), prop.getProperty("payment_type_invoice"), "Test Step - 19 - Mode of payment as " + prop.getProperty("payment_type_invoice") + " is not displayed");

            // Test Step - 20
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(2000);
            dashboardorder.Click_ActionMenu_Row1();
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.Verify_Action_submenu_is_displayed(), "Test Step - 20 - Action sub menus are not displayed when clicks the action menu in order page");
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.Verify_Respective_Submenu_is_displayed("Cancel Order"), "Test Step - 20 - Cancel Order sub menu is not displayed when clicks the action menu in order page");

            // Test Step - 21
            dashboardorder.Click_CancelOrder_Submenu();
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.is_Transfer_To_Proposal_Page_Popup_Displayed(), "Test Step - 21 - Transfer to proposal page popup is not displayed when clicks the cancel order action submenu row 1");
            delayWithGivenTime(2000);
            dashboardorder.click_Ok_Button_On_Transfer_To_Proposal_Page_Popup();
            delayWithGivenTime(3000);

            proposal.Select_Proposal_StatusType_DropDown("Cancelled");
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_Selected_Proposal_StatusType(), "Cancelled", "Test Step - 21 - Selected proposal status type is not displayed as Cancelled");

            proposal.click_on_Tick_Icon_for_Cancelled_Proposal_On_Created_Proposal();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.is_Cancelled_Proposal_Popup_On_Proposal_Grid(), "Test Step - 21 - Cancelled proposal popup is not displayed");
            delayWithGivenTime(1000);

            proposal.enter_Confirmation_Code_On_Cancelled_Proposal_Popup(proposal.extract_Confirmation_Code_From_Cancelled_Proposal_Popup());
            delayWithGivenTime(1000);
            proposal.click_OK_Button_On_Confirm_Button_On_Cancelled_Proposal_Popup();
            delayWithGivenTime(2000);
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            //   softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Order has been cancelled successfully.", "Test Step - 21 - Success message is not displayed");
            softassert.assertTrue(dashboardorder.is_Cancel_Order_Popup_Displayed(), "Test Step - 21 - Cancel order popup is not displayed when clicks the cancel order action submenu row 1");

            // Test Step - 22
            softassert.assertTrue(dashboardorder.Verify_CancelOrder_Popup_IsDisplayed(), "Test Step - 21 - Cancel order popup is not displayed when clicks the cancel order action submenu row 1");

            // Test Step - 22
            dashboardorder.Select_CancelOrder_Reason(TestData.Order_Cancel_Reason.getValue());
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_cancelOrder_Reason(), "Credit: Reverse Charges in Error", "Test Step - 22 - Cancel order reason is not displayed on cancel order popup");

            // Test Step - 23
            dashboardorder.Click_YesButton_CancelOrder_Popup();
            delayWithGivenTime(2000);

            if (dashboardorder.Validate_VerifyPassword_Popup_IsDisplayed()) {
                softassert.assertTrue(dashboardorder.Validate_VerifyPassword_Popup_IsDisplayed(), "Test Step - 23 - Verify password popup is not displayed when clicks the yes button on cancel order popup");
                dashboardorder.Enter_Manager_Password_On_VerifyPassword_Popup(TestData.Manager_Password.getValue());
                delayWithGivenTime(1000);
                dashboardorder.Click_OkayButton_VerifyPassword_Popup();
                softassert.assertEquals(dashboardorder.Verify_Success_Toaster_Message_Text(), "Order Cancelled Successfully", "Test Step - 23 - Success toaster message is not displayed when clicks the yes button on cancel order popup");
            }

            // Test Step - 24
            softassert.assertEquals(dashboardorder.Verify_Success_Toaster_Message_Text(), "Order Cancelled Successfully", "Test Step - 23 - Success toaster message is not displayed when clicks the yes button on cancel order popup");
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);

            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 24 - Dashboard order page is not displayed");

            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 24 - Respective Invoice number : " + invoiceNumber + " is not displayed on all orders page");

            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), "Cancelled", "Test Step - 24 - Cancelled order status is not displayed on order page after clicking the yes button on cancel order popup");

            // Test Step - 25
            getDriver().navigate().refresh();
            delayWithGivenTime(2000);
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 25 - Respective Invoice number is not displayed on all orders page");
            delayWithGivenTime(2000);
            dashboardorder.clickInvoiceNumber_On_TableGrid_AllOrdersPage(invoiceNumber);
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_OrderUpdate_CancelledMessageTextActivityTab(), "ORDER CANCELLED", "Test Step - 26 - Cancelled order message is not displayed on invoice activities tab order page");

            // Test Step - 26
            softassert.assertEquals(dashboardorder.Verify_Top_Of_Invoice_OrderStatus(), "Cancelled", "Test Step - 27 - Cancelled order status is not displayed on top of invoice on order page");

            // Test Step - 27
            delayWithGivenTime(2000);
            dashboardorder.Click_PaymentTab_On_InvoicePopup();
            delayWithGivenTime(2000);
            total_amount = total_amount.replace("$", "");
            softassert.assertEquals(dashboardorder.get_CancelledOrderStatus_payment_refund_amount_row1(), "$-" + total_amount, "Test Step - 27 - Invoice/House Account Refund Payment description is not displayed on payment tab");

            // Test Step - 28
            delayWithGivenTime(2000);
            dashboardorder.Click_StatusTab_onInvPopup();
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_statustab_description_row2(), "Cancelled", "Test Step - 28 - Cancelled order status is not displayed on Status tab");


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
