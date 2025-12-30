package com.hanapos.testcases.LoginPage_Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T1_LoginPage_FT extends TestBaseClass {

    private LoginPage lp;
    private HanaDashBoardPage dashboard;

    @Test(priority = 1, groups = {"Smoke", "Regression"})
    public void VerifyLoginTest() {
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate Login Page  ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(getData("login.username", "testData.json"));//(prop.getProperty("username"));
            //  logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(getData("login.username", "testData.json"));//prop.getProperty("password")
            // logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            //   logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page");
        } catch (Exception e) {
            e.printStackTrace();
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }

    @Test(priority = 2, groups = {"Regression"}, enabled = false)
    public void ValidateLoginPageTitle() {
        lp = new LoginPage();
        Assert.assertEquals(lp.VerifyLoginPageTitle(), "HANA POS | Login");
    }

    @Test(priority = 3, groups = {"Regression"}, enabled = false)
    public void VerifyLoginPage() {
        lp = new LoginPage();
        Assert.assertEquals(lp.ValidateLoginPage(), "Welcome to HANA POS");
    }

    @Test(priority = 4, enabled = false, groups = {"Regression"})
    public void ValidateLoginPageUIElementsPresenceTest() {
        lp = new LoginPage();
//        softassert.assertEquals(lp.VerifyLoginPageTitle(), "HANA POS | Login");
//        softassert.assertEquals(lp.ValidateLoginPage(), "Welcome to HANA POS");
//        softassert.assertTrue(lp.ValidateUsernameFieldIsPresence(), "Username field is not displayed");
//        softassert.assertTrue(lp.ValidatePasswordFieldIsPresence(), "Password field is not displayed");
//        softassert.assertTrue(lp.ValidateLoginButtonIsPresence(), "Login button is not displayed");
//        softassert.assertTrue(lp.ValidateForgotUserNameIsPresence(), "Forgot UserName link is not displayed");
//        softassert.assertTrue(lp.ValidateForgotPasswordIsPresence(), "Forgot Password link is not displayed");
//        softassert.assertTrue(lp.ValidateDonothaveanaccountIsPresence(), "Do not have an account link is not displayed");
//        softassert.assertTrue(lp.ValidateCreateAccountIsPresence(), "Create Account button is not displayed");
//        softassert.assertAll();
//
    }

    @Test(priority = 5, enabled = false, groups = {"Regression"})
    public void ValidateForgotUsernamePageTest() {
        lp = new LoginPage();
        lp.ClickForgotUserName();
        Assert.assertTrue(lp.ValidateForgotUserNamePage());
    }

    @Test(priority = 6, enabled = false, groups = {"Regression"})
    public void ValidateForgotPasswordPageTest() {
        lp = new LoginPage();
        lp.ClickForgotPassword();
        Assert.assertTrue(lp.ValidateForgotPasswordPage());
    }
}
