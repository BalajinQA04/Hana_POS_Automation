package com.hanapos.testcases.Email_Marketing_Testcases;

import com.hanapos.pageObjects.Email_Marketing_Page;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

public class Hana_T1208_Email_Campaign_Regression_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private Email_Marketing_Page emailMarketingPage;

    @Epic("Email Marketing Module")
    @Owner("Balaji N")
    @Test(enabled = true, groups = {"Smoke", "Regression", "Sanity"})
    public void Validate_Hana_T1208_Email_Campaign_Regression_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();

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

            // Test Step - 3
            delayWithGivenTime(2000);
            dashboard.Click_EmailMarketing_SubMenu();
            emailMarketingPage = new Email_Marketing_Page();
            delayWithGivenTime(2000);
            softassert.assertTrue(emailMarketingPage.Verify_EmailCampaigns_header(), "Test Step - 3: Email Campaigns header is not displayed");

            // Test Step - 4
            emailMarketingPage.Click_create_campaign_button();
            delayWithGivenTime(2000);
            softassert.assertTrue(emailMarketingPage.verify_campaign_info_header(), "Test Step - 4: Campaign Info Header is not displayed after clicks on create campaign button");

            // Test Step - 5
            delayWithGivenTime(2000);
            emailMarketingPage.Enter_Campaign_Name("Occasion Campaign " + CurrentDate() + "_" + generate_random_three_digits());
            emailMarketingPage.Enter_Campaign_Email_Subject("Test Automation on Email Campaign at " + CurrentDate());
            emailMarketingPage.Enter_Campaign_From_Name("Test Automation");
            emailMarketingPage.Enter_Campaign_From_Email("hanaposqateam@gmail.com");
            emailMarketingPage.Select_Campaign_Type("Simple");
            emailMarketingPage.Select_Campaign_Occasion("Birthday");

            // Test Step - 6
            emailMarketingPage.Click_Next_Button();

            // Test Step - 7
            delayWithGivenTime(2000);
            // emailMarketingPage.Select_template_Occasion("Birthday1");

            delayWithGivenTime(1000);
            emailMarketingPage.Enter_Coupon_on_Template_Tab("Enjoy 5% OFF with Coupon Code TestAutomation5OFF");

            delayWithGivenTime(1000);
            emailMarketingPage.Enter_Title_on_Template_Tab("Test Automation Template Title");
            emailMarketingPage.Enter_Content_on_Template_Tab("As a valued customer, use the code SPRING20 at checkout to enjoy an additional 20% OFF your first purchase!");

            // Test Step - 8
            delayWithGivenTime(2000);
            emailMarketingPage.Select_Banner_Image_On_TemplateTab("emailmarketing_banner_image.jpg");
            delayWithGivenTime(3000);
            emailMarketingPage.Select_First_Product_Image_On_TemplateTab("first_product_image.png");
            delayWithGivenTime(2000);
            emailMarketingPage.Select_Second_Product_Image_On_TemplateTab("second_product_image.jpg");
            delayWithGivenTime(2000);
            emailMarketingPage.Select_Third_Product_Image_On_TemplateTab("third_product_image.jpg");

            // Test Step - 9
            emailMarketingPage.Enter_First_Product_Image_Name_On_TemplateTab("Birthday Bliss");
            emailMarketingPage.Enter_Second_Product_Image_Name_On_TemplateTab("Spring Symphony");
            emailMarketingPage.Enter_Third_Product_Image_Name_On_TemplateTab("Blossom Breeze");

            // Test Step - 10
            emailMarketingPage.Enter_FirstProduct_Price("199");
            emailMarketingPage.Enter_Second_Product_Price("299");
            emailMarketingPage.Enter_Third_Product_Price("399");
            delayWithGivenTime(3000);
            softassert.assertEquals(emailMarketingPage.getCouponCode(), "ENJOY 5% OFF WITH COUPON CODE TESTAUTOMATION5OFF", "Test Step - 10: Entered coupon code text is not displayed on template ");
            softassert.assertEquals(emailMarketingPage.getTitle(), "Test Automation Template Title", "Test Step - 10: Entered title of campaign text is not displayed on template ");
            softassert.assertEquals(emailMarketingPage.getContent(), "As a valued customer, use the code SPRING20 at checkout to enjoy an additional 20% OFF your first purchase!", "Test Step - 10: Entered content of campaign text is not displayed on template ");
            delayWithGivenTime(2000);
            softassert.assertEquals(emailMarketingPage.getProductName1(), "Birthday Bliss", "Test Step - 10: Entered Product Name 1 text is not displayed on template ");
            softassert.assertEquals(emailMarketingPage.getProductName2(), "Spring Symphony", "Test Step - 10: Entered Product Name 2 text is not displayed on template ");
            softassert.assertEquals(emailMarketingPage.getProductName3(), "Blossom Breeze", "Test Step - 10: Entered Product Name 3 text is not displayed on template ");
            delayWithGivenTime(2000);
            softassert.assertEquals(emailMarketingPage.getProductPrice1(), "199", "Test Step - 10: Entered Product Price 1 text is not displayed on template ");
            softassert.assertEquals(emailMarketingPage.getProductPrice2(), "299", "Test Step - 10: Entered Product Price 2 text is not displayed on template ");
            //softassert.assertEquals(emailMarketingPage.getProductPrice3(), "399", "Test Step - 10: Entered Product Price 3 text is not displayed on template ");

            delayWithGivenTime(2000);

            // Test Step - 11
            emailMarketingPage.Click_Next_Button();

            // Test Step - 12
            softassert.assertEquals(emailMarketingPage.getCouponCode(), "ENJOY 5% OFF WITH COUPON CODE TESTAUTOMATION5OFF", "Test Step - 12: Entered coupon code text is not displayed on template ");
            softassert.assertEquals(emailMarketingPage.getTitle(), "Test Automation Template Title", "Test Step - 12: Entered title of campaign text is not displayed on template ");
            softassert.assertEquals(emailMarketingPage.getContent(), "As a valued customer, use the code SPRING20 at checkout to enjoy an additional 20% OFF your first purchase!", "Test Step - 12: Entered content of campaign text is not displayed on template ");
            delayWithGivenTime(2000);
            softassert.assertEquals(emailMarketingPage.getProductName1(), "Birthday Bliss", "Test Step - 12: Entered Product Name 1 text is not displayed on template ");
            softassert.assertEquals(emailMarketingPage.getProductName2(), "Spring Symphony", "Test Step - 12: Entered Product Name 2 text is not displayed on template ");
            softassert.assertEquals(emailMarketingPage.getProductName3(), "Blossom Breeze", "Test Step - 12: Entered Product Name 3 text is not displayed on template ");
            delayWithGivenTime(2000);
            softassert.assertEquals(emailMarketingPage.getProductPrice1(), "199", "Test Step - 12: Entered Product Price 1 text is not displayed on template ");
            softassert.assertEquals(emailMarketingPage.getProductPrice2(), "299", "Test Step - 12: Entered Product Price 2 text is not displayed on template ");
            //  softassert.assertEquals(emailMarketingPage.getProductPrice3(), "399", "Test Step - 12: Entered Product Price 3 text is not displayed on template ");

            // Test Step - 13
            emailMarketingPage.click_preview_and_test_button();

            // Test Step - 14
            emailMarketingPage.Send_Test_Mail("hanaposqateam@gmail.com");
            softassert.assertEquals(emailMarketingPage.VerifySucessToasterMessageText(), "Test Mail Sent Successfully", "Test Step - 14: Sucess toaster message is not displayed after send the test mail");

            // Test Step - 15
            emailMarketingPage.Click_Next_Button();
            delayWithGivenTime(2000);
            emailMarketingPage.TurnOff_Customer_ToogleButton();
            delayWithGivenTime(1000);
            emailMarketingPage.Select_Customer("Abish David|15047561|hanaposqateam@gmail.com");

            // Test Step - 16
            delayWithGivenTime(3000);
            emailMarketingPage.Click_Next_Button();

            // Test Step - 17
            delayWithGivenTime(2000);
            emailMarketingPage.Click_Next_Button();
            delayWithGivenTime(2000);
            emailMarketingPage.Select_Email_Campaign_Date(NextDate());
            delayWithGivenTime(2000);
            // emailMarketingPage.Select_Email_Campaign_Time("");
            emailMarketingPage.Click_ScheduleAndFinalize_Button();
            delayWithGivenTime(2000);
            emailMarketingPage.EnterConfirmationCodeInConfirmationPopup(emailMarketingPage.Extract_the_Confirmation_Code());
            emailMarketingPage.ClickOkBtn_on_ConfirmationPopup();
            //  softassert.assertEquals(emailMarketingPage.VerifySucessToasterMessageText(), "Campaign Saved Successfully", "Test Step - 14: Sucess toaster message is not displayed after send the test mail");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }


}
