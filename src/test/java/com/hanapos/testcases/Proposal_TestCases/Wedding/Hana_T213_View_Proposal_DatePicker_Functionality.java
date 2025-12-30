package com.hanapos.testcases.Proposal_TestCases.Wedding;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.ManageProposalPage;
import com.hanapos.pageObjects.ProposalsPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class Hana_T213_View_Proposal_DatePicker_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private ProposalsPage proposal;
    private ManageProposalPage manageproposal;
    String proposalId;

    @Epic("Proposal Module - Wedding")
    @Owner("Balaji N")
    // @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T213_View_Proposal_DatePicker_Functionality() {
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T213_View_Proposal_DatePicker_Functionality ****");
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
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));

            // Test Step - 4
            proposal = new ProposalsPage();
            proposal.ClickOnProposalsMenu();

            delayWithGivenTime(2000);
            proposal.Click_DatePicker_InputBox();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_DatePicker_Popup_IsDisplayed(), "Test Step - 4: Date Picker Popup is not displayed");

            // Test Step - 5
            softassert.assertTrue(proposal.Verify_fromDate_Mini_input_textbox_IsDisplayed(), "Test Step - 5: Mini Input textbox of from date picker is not displayed");
            softassert.assertTrue(proposal.Verify_ToDate_Mini_input_textbox_IsDisplayed(), "Test Step - 5: Mini Input textbox of to date picker is not displayed");
            softassert.assertTrue(proposal.Verify_Apply_Button_On_Calendar_Section1_IsDisplayed(), "Test Step - 5: Apply button is not displayed on calendar section");
            softassert.assertTrue(proposal.Verify_ClearButton_IsDisplayed_On_Calendar_Section1(), "Test Step - 5: Clear button is not displayed on calendar section");

            // Test Step - 6
            softassert.assertTrue(proposal.Verify_LeftCalendar_section_isDisplayed(), "Test Step - 6: Left calendar section is not displayed");
            softassert.assertTrue(proposal.Verify_leftArrow_isDisplayed_On_leftcalendar(), "Test Step - 6: Left Arrow is not displayed on left calendar");
            softassert.assertTrue(proposal.Verify_selectmonth_dropdown_isDisplayedOn_leftcalendar(), "Test Step - 6: Select month dropdown is not displayed on left calendar");
            softassert.assertTrue(proposal.Verify_selectyear_dropdown_isDisplayedOn_leftcalendar(), "Test Step - 6: Select year dropdown is not displayed on left calendar");
            softassert.assertTrue(proposal.Verify_rightArow_isDisplayed_On_leftcalendar(), "Test Step - 6: Right arrow is not displayed on left calendar");

            // Test Step - 7
            softassert.assertTrue(proposal.Verify_selectmonth_dropdown_isDisplayed_on_rightcalendar(), "Test Step - 7: Select Month dropdown is not displayed on right calendar");
            softassert.assertTrue(proposal.Verify_selectyear_dropdown_isDisplayed_on_rightcalendar(), "Test Step - 7: Select year dropdown is not displayed on right calendar");
            softassert.assertTrue(proposal.Verify_rightArrow_IsDisplayed_On_RightCalendar(), "Test Step - 7: Right Arrow is not displayed on right calendar");
            softassert.assertTrue(proposal.Verify_rightcalendar_IsDisplayed(), "Test Step - 7: Right calendar is not displayed on view proposal date picker");

            // Test Step - 8
            proposal.Click_FromDate_on_LeftCalendar(NextDate());
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_fromdate_mini_input_textbox(), NextDate(), "Test Step - 8: Selected from date is not displayed on mini input textbox");

            // Test Step - 9
            delayWithGivenTime(2000);
            proposal.Click_ToDate_on_RightCalendar(Next_10Days_Date());
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_todate_mini_input_textbox(), Next_10Days_Date(), "Test Step - 9: Selected to date is not displayed on mini input textbox");

            // Test Step - 10
            proposal.Click_LeftArrow_FromDate_Calendar();
            softassert.assertTrue(proposal.verify_previous_Month_IsDisplayed(), "Test Step - 10: Previous month is not displayed on from date picker");

            // Test Step - 11
            proposal.Click_RightArrow_FromDate_Calendar();
            delayWithGivenTime(1000);
            softassert.assertTrue(proposal.verify_next_Month_IsDisplayed(), "Test Step - 11: Next month is not displayed from date picker");

            // Test Step - 12
            delayWithGivenTime(1000);
            proposal.Click_RightArrow_FromDate_RightCalendar();
            softassert.assertTrue(proposal.verify_next_Month_IsDisplayed_on_ToDate(), "Test Step - 12: Next month is not displayed on from date picker");

            // Test Step - 13
            delayWithGivenTime(1000);
            proposal.Click_LeftArrow_ToDate_on_RightCalendar();
            softassert.assertTrue(proposal.verify_previous_Month_IsDisplayed_on_ToDate(), "Test Step - 13: Next month is not displayed on from date picker");

            // Test Step - 14
            proposal.Click_ClearButton_on_Calendar_Section();

            //Test Step - 15
            proposal.Click_DatePicker_InputBox();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_DatePicker_Popup_IsDisplayed(), "Test Step - 4: Date Picker Popup is not displayed");

            softassert.assertTrue(proposal.Verify_fromDate_Mini_input_textbox_IsDisplayed(), "Test Step - 5: Mini Input textbox of from date picker is not displayed");
            softassert.assertTrue(proposal.Verify_ToDate_Mini_input_textbox_IsDisplayed(), "Test Step - 5: Mini Input textbox of to date picker is not displayed");
            softassert.assertTrue(proposal.Verify_Apply_Button_On_Calendar_Section1_IsDisplayed(), "Test Step - 5: Apply button is not displayed on calendar section");
            softassert.assertTrue(proposal.Verify_ClearButton_IsDisplayed_On_Calendar_Section1(), "Test Step - 5: Clear button is not displayed on calendar section");

            softassert.assertTrue(proposal.Verify_LeftCalendar_section_isDisplayed(), "Test Step - 6: Left calendar section is not displayed");
            softassert.assertTrue(proposal.Verify_leftArrow_isDisplayed_On_leftcalendar(), "Test Step - 6: Left Arrow is not displayed on left calendar");
            softassert.assertTrue(proposal.Verify_selectmonth_dropdown_isDisplayedOn_leftcalendar(), "Test Step - 6: Select month dropdown is not displayed on left calendar");
            softassert.assertTrue(proposal.Verify_selectyear_dropdown_isDisplayedOn_leftcalendar(), "Test Step - 6: Select year dropdown is not displayed on left calendar");
            softassert.assertTrue(proposal.Verify_rightArow_isDisplayed_On_leftcalendar(), "Test Step - 6: Right arrow is not displayed on left calendar");

            softassert.assertTrue(proposal.Verify_selectmonth_dropdown_isDisplayed_on_rightcalendar(), "Test Step - 7: Select Month dropdown is not displayed on right calendar");
            softassert.assertTrue(proposal.Verify_selectyear_dropdown_isDisplayed_on_rightcalendar(), "Test Step - 7: Select year dropdown is not displayed on right calendar");
            softassert.assertTrue(proposal.Verify_rightArrow_IsDisplayed_On_RightCalendar(), "Test Step - 7: Right Arrow is not displayed on right calendar");
            softassert.assertTrue(proposal.Verify_rightcalendar_IsDisplayed(), "Test Step - 7: Right calendar is not displayed on view proposal date picker");

            proposal.Click_FromDate_on_LeftCalendar(NextDate());
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_fromdate_mini_input_textbox(), NextDate(), "Test Step - 8: Selected from date is not displayed on mini input textbox");

            delayWithGivenTime(2000);
            proposal.Click_ToDate_on_RightCalendar(Next_10Days_Date());
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_todate_mini_input_textbox(), Next_10Days_Date(), "Test Step - 9: Selected to date is not displayed on mini input textbox");

            proposal.Click_LeftArrow_FromDate_Calendar();
            softassert.assertTrue(proposal.verify_previous_Month_IsDisplayed(), "Test Step - 10: Previous month is not displayed on from date picker");

            proposal.Click_RightArrow_FromDate_Calendar();
            delayWithGivenTime(1000);
            softassert.assertTrue(proposal.verify_next_Month_IsDisplayed(), "Test Step - 11: Next month is not displayed from date picker");

            proposal.Click_RightArrow_FromDate_RightCalendar();
            softassert.assertTrue(proposal.verify_next_Month_IsDisplayed_on_ToDate(), "Test Step - 12: Next month is not displayed on from date picker");

            proposal.Click_LeftArrow_ToDate_on_RightCalendar();
            softassert.assertTrue(proposal.verify_previous_Month_IsDisplayed_on_ToDate(), "Test Step - 13: Next month is not displayed on from date picker");

            proposal.Click_ApplyButton_On_CalendarSection();
            delayWithGivenTime(2000);

            softassert.assertEquals(proposal.get_displayed_filtered_dates_datepicker_inputtextbox(), NextDate() + " - " + Next_10Days_Date(), "Test Step - 15: Datepicker filter textbox values are not displayed");


        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }

}
