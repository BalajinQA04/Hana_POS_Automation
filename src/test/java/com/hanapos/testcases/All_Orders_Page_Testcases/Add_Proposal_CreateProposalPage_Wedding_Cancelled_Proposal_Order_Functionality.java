package com.hanapos.testcases.All_Orders_Page_Testcases;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.LoggerUtil;
import com.hanapos.utilities.TestData;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Add_Proposal_CreateProposalPage_Wedding_Cancelled_Proposal_Order_Functionality extends TestBaseClass {

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

    @Epic("All Orders Page Module")
    @Test(enabled = true, groups = {"Regression", "Smoke", "Sanity"})
    public void Validate_Wedding_Proposal_Cancelled_Order_Functionality() {

        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T243_Add_Proposal_CreateProposalPage_Wedding_Functionality_Test ****");
        logger.debug("capturing application debug logs....");
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
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("cashandcarryshopname"));

            // Test Step - 4
            proposal = new ProposalsPage();
            proposal.ClickOnProposalsMenu();

            // Test Step - 5
            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(), "Test Step - 21 - Create a Proposal popup is not displayed");

            // Test Step - 6
            proposal.SearchandSelect_Customer_OnProposal(prop.getProperty("cust_firstName"));
            proposal.Click_CancelButton_On_createproposal_popup();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.isCreateProposalPopupClosed(), "Test Step - 6: Create proposal popup is not closed on view proposal page");

            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(), "Test Step - 7 - Create a Proposal popup is not displayed on view proposal page");

            // Test Step - 7
            proposal.SearchandSelect_Customer_OnProposal(prop.getProperty("cust_firstName"));
            softassert.assertEquals(proposal.Verify_CustomerNameIsDisplayed_On_SearchTextBox(), prop.getProperty("custfullname"), "Test Step - 7: Searched customer full name " + prop.getProperty("custfullname") + " is not displayed on create proposal popup");
            delayWithGivenTime(2000);
            proposal.Click_AddProposal_On_CreateProposal_Popup();

            // Test Step - 8
            delayWithGivenTime(2000);
            manageproposal = new ManageProposalPage();
            softassert.assertEquals(manageproposal.get_ManageProposalPageTitle(), "Hana | ManageProposal", "Test Step - 8 - Manage Proposal page is not displayed");
            delayWithGivenTime(2000);

            // Test Step - 9
            proposalId = manageproposal.get_ProposalId();
            manageproposal.Click_CoupleDetailsTab();
            manageproposal.Select_Bride_Groom_DropDown1("Groom");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Bride_FirstName(), "Abish", "Test Step - 9 - Entered bride first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_LastName(), "David", "Test Step - 9 - Entered bride last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_PhoneNumber(), "9566550756", "Test Step - 9 -Entered bride phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Email(), "hanaposqateam@gmail.com", "Test Step - 9 -Entered bride email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_City(), "San Diego", "Test Step - 9 -Entered bride city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_State(), "CA", "Test Step - 9 -Entered bride state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Address(), "3402 Park Blvd", "Test Step - 9 -Entered bride address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Zip(), "92103", "Test Step - 9 -Entered bride zip code is not displayed");

            // Test Step - 10
            delayWithGivenTime(2000);
            manageproposal.Select_Bride_Groom_DropDown2("Bride");
            delayWithGivenTime(2000);
            manageproposal.Enter_Groom_FirstName("O'Brien");
            manageproposal.Enter_Groom_LastName("John");
            manageproposal.Enter_Groom_PhoneNumber("925-456-2305");
            manageproposal.Enter_Groom_Email("hanaposqateam@gmail.com");
            manageproposal.Enter_Groom_City("El Cajon");
            manageproposal.Enter_Groom_State("CA");
            manageproposal.Enter_Groom_Address("Blossom Valley");
            manageproposal.Enter_Groom_Zip("92021");

            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Groom_FirstName(), "O'Brien", "Test Step - 10 -Entered groom first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_LastName(), "John", "Test Step - 10 -Entered groom last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_PhoneNumber(), "925-456-2305", "Test Step - 10 -Entered groom phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_Email(), "hanaposqateam@gmail.com", "Test Step - 10 -Entered groom email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_City(), "El Cajon", "Test Step - 10 -Entered groom city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_State(), "CA", "Test Step - 10 -Entered groom state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_Address(), "Blossom Valley", "Test Step - 10 -Entered groom address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_Zip(), "92021", "Test Step - 10 -Entered groom zip code is not displayed");

            // Test Step - 11
            delayWithGivenTime(2000);
            manageproposal.Click_Weddings_eventDetails_Tab();
            manageproposal.Enter_CeremonyLocation("The Ritz-Carlton, Lake Tahoe");
            manageproposal.Enter_CeremonyDate_WithInNext30Days();
            manageproposal.Enter_CeremonyTime();
            manageproposal.Enter_Ceremony_Address("13031 Ritz Carlton Highlands Court");
            manageproposal.Enter_Ceremony_City("Truckee");
            manageproposal.Enter_Ceremony_State("CA");
            manageproposal.Enter_Ceremony_Zip("96161");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_CeremonyLocation(), "The Ritz-Carlton, Lake Tahoe", "Test Step - 24 -Entered ceremony location is not displayed");
            //	softassert.assertEquals(manageproposal.get_Entered_CeremonyDate(), "Wed, 01 Oct 2022","Test Step - 24 -Entered ceremony date is not displayed");
            //	softassert.assertEquals(manageproposal.get_Entered_CeremonyTime(), "11:00 AM","Test Step - 24 -Entered ceremony time is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Ceremony_Address(), "13031 Ritz Carlton Highlands Court", "Test Step - 24 -Entered ceremony address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Ceremony_City(), "Truckee", "Test Step - 24 -Entered ceremony city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Ceremony_State(), "CA", "Test Step - 24 -Entered ceremony state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Ceremony_Zip(), "96161", "Test Step - 24 -Entered ceremony zip code is not displayed");

            // Test Step - 12
            delayWithGivenTime(2000);
            manageproposal.Enter_ReceptionLocation("Rutherford Wedding Venue");
            manageproposal.Enter_ReceptionDate_WithInNext30Days();
            manageproposal.Enter_ReceptionTime();
            manageproposal.Enter_Reception_Address("180 Rutherford Hill Rd, Rutherford");
            manageproposal.Enter_Reception_City("Rutherford");
            manageproposal.Enter_Reception_State("CA");
            manageproposal.Enter_Reception_Zip("94573");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_ReceptionLocation(), "Rutherford Wedding Venue", "Test Step - 24 -Entered reception location is not displayed");
            //	softassert.assertEquals(manageproposal.get_Entered_CeremonyDate(), "Wed, 01 Oct 2022","Test Step - 24 -Entered ceremony date is not displayed");
            //	softassert.assertEquals(manageproposal.get_Entered_CeremonyTime(), "11:00 AM","Test Step - 24 -Entered ceremony time is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Reception_Address(), "180 Rutherford Hill Rd, Rutherford", "Test Step - 24 -Entered reception address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Reception_City(), "Rutherford", "Test Step - 24 -Entered reception city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_reception_State(), "CA", "Test Step - 24 -Entered reception state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Reception_Zip(), "94573", "Test Step - 24 -Entered reception zip code is not displayed");

            // Test Step - 13
            delayWithGivenTime(2000);
            manageproposal.Enter_WeddingCoordinator_FirstName("Daniel");
            manageproposal.Enter_WeddingCoordinator_LastName("Wilton");
            manageproposal.Enter_WeddingCoordinator_PhoneNumber("604-685-5761");
            manageproposal.Enter_WeddingCoordinator_Email("hanaposqateam@gmail.com");
            manageproposal.Enter_WeddingCoordinator_City("North Vancouver");
            manageproposal.Enter_WeddingCoordinator_State("BC");
            manageproposal.Enter_WeddingCoordinator_Address("1433 Pemberton Ave");
            manageproposal.Enter_WeddingCoordinator_Zip("V7P 2R8");

            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_FirstName(), "Daniel", "Test Step - 13 -Entered Wedding Coordinator first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_LastName(), "Wilton", "Test Step - 13 -Entered Wedding Coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_PhoneNumber(), "604-685-5761", "Test Step - 13 -Entered Wedding Coordinator phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_Email(), "hanaposqateam@gmail.com", "Test Step - 13 -Entered Wedding Coordinator email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_City(), "North Vancouver", "Test Step - 13 -Entered Wedding Coordinator city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_State(), "BC", "Test Step - 13 -Entered Wedding Coordinator state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_Address(), "1433 Pemberton Ave", "Test Step - 13 -Entered Wedding Coordinator address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_Zip(), "V7P 2R8", "Test Step - 13 -Entered Wedding Coordinator zip code is not displayed");

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
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 18 - In orders summary page proposal invoice number is not displayed for placed order");
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoiceNumber), "Wedding proposal", "Test Step - 18 - Order type is not displayed as Wedding proposal");
            softassert.assertEquals(dashboardorder.validate_DeliveryType_On_AllOrdersPage(invoiceNumber), "Delivery", "Test Step - 18 - Delivery type is not displayed for placed order");
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoiceNumber), prop.getProperty("payment_type_invoice"), "Test Step - 19 - Mode of payment as " + prop.getProperty("payment_type_invoice") + " is not displayed");

            // Test Step - 20
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
            dashboard.ClickOrder();
            delayWithGivenTime(1000);

            // Test Step - 25
            getDriver().navigate().refresh();
            delayWithGivenTime(2000);
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 25 - Respective Invoice number is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), "Cancelled", "Test Step - 24 - Cancelled order status is not displayed on order page after clicking the yes button on cancel order popup");

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
