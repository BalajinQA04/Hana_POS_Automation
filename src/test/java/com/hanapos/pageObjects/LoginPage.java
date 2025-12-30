package com.hanapos.pageObjects;

import com.hanapos.utilities.PageLoadLoggerUtils;
import io.qameta.allure.Allure;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.hanapos.seleniumProjectBase.TestBaseClass;

/**
 * LoginPage class represents the login page of the web application.
 *
 * @Author Balaji N
 */
public class LoginPage extends TestBaseClass {
    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
    }

    // ================ Login page Webelements ================
    @FindBy(css = "div.loginscreen h3")
    private WebElement LoginPage;

    @FindBy(css = "#Username")
    private WebElement Username;

    @FindBy(css = "#Password")
    private WebElement Password;

    @FindBy(css = "#btnLogin")
    private WebElement loginbutton;

    @FindBy(xpath = "//span[@class='field-validation-error']")
    private WebElement loginPageErrorMsg;

    @FindBy(xpath = "//span[contains(text(),'Please Enter Valid User Details')]")
    private WebElement IncorrectValidationMsg;

    @FindBy(xpath = "//small[normalize-space()='Forgot UserName?']")
    private WebElement forgotUserName;

    @FindBy(xpath = "//h3[normalize-space()='FORGOT USERNAME']")
    private WebElement forgotUserNamePage;

    @FindBy(xpath = "//small[normalize-space()='Forgot password?']")
    private WebElement forgotpassword;

    @FindBy(xpath = "//h3[normalize-space()='FORGOT PASSWORD']")
    private WebElement forgotPasswordPage;

    @FindBy(xpath = "//small[normalize-space()='Do not have an account?']")
    private WebElement donothaveanaccount;

    @FindBy(xpath = "//a[normalize-space()='Create an account']")
    private WebElement CreateAccount;

    @FindBy(xpath = "//div[@class='llogin text-center loginscreen animated fadeInDown']//div//h3")
    private WebElement loginPage;

    @FindBy(xpath = "//div[@class='form-group text-left']")
    private WebElement terms_and_conditions_entire_element;

    @FindBy(xpath = "//div[@class='form-group text-left']//input[1]")
    private WebElement terms_and_conditions_checkbox;

    // ================ Login page Functions ================

    // Used to verify login page title
    public String VerifyLoginPageTitle() {
        return getDriver().getTitle();
    }

    // Used to validate login page welcome text
    public String ValidateLoginPage() {
        return getElementText(LoginPage, "Login Page welcome to Hana Florist POS label");
    }

    /**
     * Verifies if the Login Page is displayed by checking the welcome text.
     *
     * <p>The method checks for two possible welcome messages:
     * <ul>
     *   <li>"Welcome to HANA POS"</li>
     *   <li>"Welcome to HANA Florist POS"</li>
     * </ul>
     *
     * <p>Returns true if the welcome text is displayed; otherwise, returns false.
     *
     * <p><b>Possible Exceptions:</b>
     * <ul>
     *   <li>{@link org.openqa.selenium.NoSuchElementException} - If the login page element is not found.</li>
     *   <li>{@link org.openqa.selenium.StaleElementReferenceException} - If the reference to the element is stale due to page refresh or DOM update.</li>
     *   <li>{@link org.openqa.selenium.TimeoutException} - If the element is not found within the specified time.</li>
     *   <li>{@link java.net.ConnectException} - If the server is down or unreachable.</li>
     *   <li>{@link org.openqa.selenium.WebDriverException} - If there are browser-related failures.</li>
     *   <li>{@link java.lang.NullPointerException} - If the LoginPage object is not initialized.</li>
     * </ul>
     *
     * @return {@code true} if the Login Page welcome text is displayed, otherwise {@code false}.
     */
    public boolean LoginPageIsDisplayed() {
        wait_For_Page_To_Be_Stable(getDriver());
        return isElementDisplayed(LoginPage, "Login Page Welcome text");
    }

    /**
     * Enters the username into the login field based on the current environment.
     *
     * @param username The username to be entered into the username field.
     * @return The LoginPage instance for method chaining.
     * @throws RuntimeException If an exception occurs during interaction.
     * @Author: Balaji N
     */
    public LoginPage EnterUserName(String username) {
        String environment = prop.getProperty("env");
        switch (environment) {
            case "dev":
                //ClickAndType(Username, username,"Username Field on Login Page");
                ClickAndType(Username, username, "Username Textbox Field on Login Page");
                break;
            case "qa-final":
                ClickAndType(Username, username, "Username Textbox Field on Login Page");
                break;
            case "staging":
                ClickAndType(Username, username, "Username Textbox Field on Login Page");
                break;
            case "live":
                ClickAndType(Username, username, "Username Textbox Field on Login Page");
                //  clickAndType(Username, username);
                break;
            default:
                throw new IllegalArgumentException("Unknown environment: " + environment);
        }

        return this;
    }

    /**
     * Is used to get the entered username
     *
     * @return Is entered username is displayed it gets the entered username else return null
     * @Description: This function highlights the username field and checks if it is displayed.
     * @Author: Balaji N
     */
    public String get_entered_username() {
        return getElementAttribute(Username, "Username value of Textbox Field on Login Page");
    }

    /**
     * Validates whether the password field is present on the page.
     *
     * @return true if the password field is displayed on the page; false otherwise
     * @Description: This function highlights the password field and checks if it is displayed.
     */
    public boolean ValidatePasswordFieldIsPresence() {
        HighlightElement(Password);
        return Password.isDisplayed();
    }

    /**
     * Enters the given password into the password field on the login page.
     *
     * @param password The password to be entered in the password field
     * @return The current instance of the LoginPage, allowing method chaining
     * @Description: This function clicks on the password field and enters the provided password.
     * @Author: Balaji N
     */
    public LoginPage EnterPassword(String password) {
        ClickAndType(Password, password, "Password Textbox Field on Login Page");
        return this;
    }

    /**
     * It returns the entered password.
     *
     * @return If the password entered is exist it returns the value of password entered, otherwise it returns null string
     * @Description: This function highlights the password field and checks if it is displayed.
     * @Author: Balaji N
     */
    public String get_entered_password() {
        return getElementAttribute(Password, "Password value of Textbox Field on Login Page");
    }

    /**
     * Clicks the login button on the login page.
     *
     * @return A new instance of the HanaDashBoardPage after successful login.
     * @Description: This function checks if the Terms and Conditions element is displayed before clicking the login button.
     * @Author: Balaji N
     */
/*    public HanaDashBoardPage ClickLoginButton() {
        if (isElementDisplayed(terms_and_conditions_entire_element, "Terms and Conditions label on Login Page")) {
            delayWithGivenTime(1000);
            js_Click(loginbutton, "Login Button on Hana POS - Login Page");
            wait_For_Page_To_Be_Stable(getDriver());
        }
        return new HanaDashBoardPage();
    }*/
    public HanaDashBoardPage ClickLoginButton() {
        PageLoadLoggerUtils.logPageLoad("Login page → Dashboard page", () -> {
            try {
                if (isElementDisplayed(terms_and_conditions_entire_element,
                        "Terms and Conditions label on Login Page")) {

                    delayWithGivenTime(1000);
                    js_Click(loginbutton, "Login Button on Hana POS - Login Page");
                    wait_For_Page_To_Be_Stable(getDriver());

                    Allure.step("✅ Successfully clicked the **Login button** and navigated from **Login Page → Hana Dashboard Page**.");
                }
            } catch (Exception e) {
                Allure.step("❌ The system was **unable to click the Login button on the Hana POS Login Page**. "
                        + "This means the login process did not proceed as expected. "
                        + "Possible causes: slow page load, browser unresponsive, or element not clickable.");

            }
        });
        return new HanaDashBoardPage();
    }


    public boolean ValidateLoginButtonIsPresence() {
        HighlightElement(loginbutton);
        return loginbutton.isDisplayed();
    }

    // Used to validate login page error message
    public boolean IsValidationMessageExist() {
        HighlightElement(loginPageErrorMsg);
        return loginPageErrorMsg.isDisplayed();
    }

    public boolean IncorrectErrorMsgIsDisplayed() {
        HighlightElement(IncorrectValidationMsg);
        return IncorrectValidationMsg.isDisplayed();
    }

    public String NullValidationErrorMsg() {
        HighlightElement(loginPageErrorMsg);
        return loginPageErrorMsg.getText();
    }

    public String IncorrectValidationErrorMsg() {
        HighlightElement(IncorrectValidationMsg);
        return IncorrectValidationMsg.getText();
    }


    public boolean ValidateForgotUserNameIsPresence() {
        HighlightElement(forgotUserName);
        return forgotUserName.isDisplayed();
    }

    public void ClickForgotUserName() {
        click(forgotUserName);
    }

    public void ClickForgotPassword() {
        click(forgotpassword);
    }

    public boolean ValidateForgotPasswordIsPresence() {
        HighlightElement(forgotpassword);
        return forgotpassword.isDisplayed();
    }

    public boolean ValidateDonothaveanaccountIsPresence() {
        HighlightElement(donothaveanaccount);
        return donothaveanaccount.isDisplayed();
    }

    public boolean ValidateCreateAccountIsPresence() {
        HighlightElement(CreateAccount);
        return CreateAccount.isDisplayed();
    }

    public boolean ValidateForgotUserNamePage() {
        HighlightElement(forgotUserNamePage);
        return forgotUserNamePage.isDisplayed();
    }

    public boolean ValidateForgotPasswordPage() {
        HighlightElement(forgotPasswordPage);
        return forgotPasswordPage.isDisplayed();
    }

}
