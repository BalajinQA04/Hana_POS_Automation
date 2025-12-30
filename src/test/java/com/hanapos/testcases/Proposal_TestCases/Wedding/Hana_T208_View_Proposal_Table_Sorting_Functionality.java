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

public class Hana_T208_View_Proposal_Table_Sorting_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private ProposalsPage proposal;
    private ManageProposalPage manageproposal;
    String proposalId;

    @Epic("Proposal Module - Wedding")
    @Owner("Balaji N")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T208_View_Proposal_Table_Sorting_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T208_View_Proposal_Table_Sorting_Functionality_Test ****");
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

            //     softassert.assertTrue(proposal.validate_ProposalId_Arranged_As_Ascending(), "Test Step - 4: Proposal id are not displayed as ascending order");
            proposal.Click_Proposal_Id_Label();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_Proposal_Id_Sort_asc_Icon(), "Test Step - 4: Upward arrow icon is not displayed");
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.validate_ProposalId_Sorting_As_Ascending(), "Test Step - 4: Proposal ids are not ordered as ascending");

            // Test STep - 5
            delayWithGivenTime(2000);
            proposal.Click_Proposal_Id_Label();

            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_Proposal_Id_Sort_desc_Icon(), "Test - 5: Downward arrow icon is not displayed");
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_Proposal_Id_Sorted_As_Descending(), "Test Step - 5: Respective proposal ids are not ordered as descending");

            //Test Step - 6
            delayWithGivenTime(2000);
            proposal.Click_Proposal_Id_Label();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.proposal_id_list_originalOrder().containsAll(proposal.proposal_id_list_originalOrder()), "Test Step - 6: Respective proposal ids are not ordered as descending");
            delayWithGivenTime(2000);

            // Test STep - 7
            proposal.Click_proposal_type_label_tablegridheader();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_Proposal_Id_Sort_asc_Icon(), "Test Step - 7: Upward arrow icon is not displayed");
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.validate_ProposalItype_Sorting_As_Ascending(), "Test Step - 7: Proposal types are not ordered as ascending");

            // Test STep - 8
            delayWithGivenTime(2000);
            proposal.Click_proposal_type_label_tablegridheader();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_Proposal_Id_Sort_desc_Icon(), "Test - 8: Downward arrow icon is not displayed");
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_Proposal_type_Sorted_As_Descending(), "Test Step - 8: Respective proposal types are not ordered as descending");

            //Test Step - 9
            delayWithGivenTime(2000);
            proposal.Click_proposal_type_label_tablegridheader();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.proposal_type_list_originalOrder().containsAll(proposal.proposal_type_list_originalOrder()), "Test Step - 9: Respective proposal types are not ordered as default");

            // Test STep - 10
            proposal.Click_Invoice_label_headertablegrid_view_proposal();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_Proposal_Id_Sort_asc_Icon(), "Test Step - 10: Upward arrow icon is not displayed");
            softassert.assertTrue(proposal.validate_invoice_Sorting_As_Ascending(), "Test Step - 10: Proposal types are not ordered as ascending");

            // Test STep - 11
            delayWithGivenTime(2000);
            proposal.Click_Invoice_label_headertablegrid_view_proposal();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_Proposal_Id_Sort_desc_Icon(), "Test - 11: Downward arrow icon is not displayed");
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_invoice_Sorted_As_Descending(), "Test Step - 11: Respective proposal types are not ordered as descending");

            //Test Step - 12
            delayWithGivenTime(2000);
            proposal.Click_Invoice_label_headertablegrid_view_proposal();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.invoice_list_originalOrder().containsAll(proposal.invoice_list_originalOrder()), "Test Step - 12: Respective proposal types are not ordered as default");

            // Test Step - 13
            delayWithGivenTime(2000);
            proposal.Click_Date_Label_on_ViewProposal();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_Proposal_Id_Sort_asc_Icon(), "Test Step - 13: Upward arrow icon is not displayed");
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.validate_date_Sorting_As_Ascending(), "Test Step - 13: dates are not ordered as ascending");

            // Test STep - 14
            delayWithGivenTime(2000);
            proposal.Click_Date_Label_on_ViewProposal();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_Proposal_Id_Sort_desc_Icon(), "Test - 14: Downward arrow icon is not displayed");
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_invoice_Sorted_As_Descending(), "Test Step - 14: Respective dates are not ordered as descending");

            //Test Step - 15
            delayWithGivenTime(2000);
            proposal.Click_Date_Label_on_ViewProposal();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.invoice_list_originalOrder().containsAll(proposal.invoice_list_originalOrder()), "Test Step - 15: Respective dates are not ordered as default");


        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }

}
