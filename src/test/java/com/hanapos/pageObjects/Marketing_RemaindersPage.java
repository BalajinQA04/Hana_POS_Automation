package com.hanapos.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.hanapos.seleniumProjectBase.TestBaseClass;

public class Marketing_RemaindersPage extends TestBaseClass {

    public Marketing_RemaindersPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //==================================== Marketing Remainders page web elements =========================
    @FindBy(xpath = "//h2[text()='Reminders']")
    private WebElement RemindersHeader;

    @FindBy(xpath = "//select[@id='reminderShop']")
    private WebElement ShopDropDownRemaindersPage;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr[1]//td[1]")
    private WebElement Row1actionButton;

    @FindBy(xpath = "(//ul[@class='dropdown-menu']//li//a[@class='EditReminder'])[1]")
    private WebElement Row1EditReminder;

    @FindBy(xpath = "//h4[@id='editReminder']")
    private WebElement EditReminderPopupHeader;

    @FindBy(xpath = "//input[@id='txtCustomerName']")
    private WebElement CustomerName_EditReminderPopup;

    @FindBy(xpath = "(//div[@class='bootstrap-tagsinput']//span)[1]")
    private WebElement CustomerEmail_EditReminderPopup;

    @FindBy(xpath = "//input[@id='txtRecipientFirstName']")
    private WebElement RecipientFirstName_EditReminderPopup;

    @FindBy(xpath = "//input[@id='txtRecipientLastName']")
    private WebElement RecipientLastName_EditReminderPopup;

    @FindBy(xpath = "//input[@id='txtRecipientPhone']")
    private WebElement RecipientPhone_EditReminderPopup;

    @FindBy(xpath = "//select[@id='ddlOccation']")
    private WebElement Occation_EditReminderPopup;

    @FindBy(xpath = "//select[@id='ddlReminderType']")
    private WebElement ReminderType_EditReminderPopup;

    @FindBy(xpath = "//input[@id='txtReminderDate']")
    private WebElement ReminderDate_EditReminderPopup;

    @FindBy(xpath = "(//input[@class='k-textbox k-FullWidth'])[2]")
    private WebElement customer_Searchtextbox_remainderspage;

    //================================= Marketing Remainders page functions =========================

    /**
     * Verify whether the Reminders Header is displayed or not
     *
     * @return If the Reminders Header is displayed return true else return false
     * @Author Balaji N
     */
    public boolean Verify_RemaindersHeaderAppears() {
        return is_Element_Displayed(RemindersHeader, "Reminders Header");
    }

    /**
     * Selects the shop name from the dropdown list on the remainders page
     *
     * @param shopname the shop name to be selected
     * @Author Balaji N
     */
    public void Select_ShopDropDownRemaindersPage(String shopname) {
        drop_Down(ShopDropDownRemaindersPage, shopname, "VisibleText", "Shop Name Dropdown field on Remainders Page");
    }

    /**
     * It retrieves the selected option from the dropdown list on the remainders page
     *
     * @return If the selected option is displayed return the value of selected shop else return null
     * @Author Balaji N
     */
    public String get_ShopDropdown_Remainder_Page() {
        return get_selected_option(ShopDropDownRemaindersPage, "Shop Name Dropdown field on Remainders Page");
    }

    /**
     * Clicks the row 1 action menu button on the remainders page
     *
     * @Author Balaji N
     */
    public void Click_Row1ActionButton() {
        HighlightElement(Row1actionButton);
        if (Row1actionButton.isDisplayed() == true) {
            Row1actionButton.click();
        } else {
            js_Click(Row1actionButton, "Row 1 Action Menu Button on Remainders Page");
        }
    }

    /**
     * Clicks the row 1 edit reminder button on the remainders page
     *
     * @Author Balaji N
     */
    public void Click_Row1EditReminder() {
        js_Click(Row1EditReminder, "Edit Reminder Button on Remainders Page");
    }

    /**
     * Verify whether the edit reminder popup is displayed or not
     *
     * @return If the edit reminder popup is displayed return true else return false
     * @Author Balaji N
     */
    public boolean Verify_EditReminderPopupAppears() {
        delayWithGivenTime(2000);
        return is_Element_Displayed(EditReminderPopupHeader, "Edit Reminder Popup Header");
    }

    /**
     * It retrieves the customer name textbox from the edit reminder popup
     *
     * @return If the customer name is displayed return the value of customer name else return null
     * @Author Balaji N
     */
    public String get_CustomerName_EditReminderPopup() {
        return getElementAttribute(CustomerName_EditReminderPopup, "Customer Name textbox field on Edit Reminder Popup");
    }

    public String get_CustomerEmail_EditReminderPopup() {
        HighlightElement(CustomerEmail_EditReminderPopup);
        return CustomerEmail_EditReminderPopup.getText();
    }

    public String get_RecipientFirstName_EditReminderPopup() {
        HighlightElement(RecipientFirstName_EditReminderPopup);
        return RecipientFirstName_EditReminderPopup.getAttribute("value");
    }

    public String get_RecipientLastName_EditReminderPopup() {
        HighlightElement(RecipientLastName_EditReminderPopup);
        return RecipientLastName_EditReminderPopup.getAttribute("value");
    }

    public String get_RecipientPhone_EditReminderPopup() {
        HighlightElement(RecipientPhone_EditReminderPopup);
        return RecipientPhone_EditReminderPopup.getAttribute("value");
    }

    public String get_Occation_EditReminderPopup() {
        HighlightElement(Occation_EditReminderPopup);
        Select select = new Select(Occation_EditReminderPopup);
        String Occation = select.getFirstSelectedOption().getText();
        return Occation;
    }

    public String get_ReminderType_EditReminderPopup() {
        HighlightElement(ReminderType_EditReminderPopup);
        Select select = new Select(ReminderType_EditReminderPopup);
        String RemainderType = select.getFirstSelectedOption().getText();
        return RemainderType;
    }

    public String get_ReminderDate_EditReminderPopup() {
        HighlightElement(ReminderDate_EditReminderPopup);
        return ReminderDate_EditReminderPopup.getAttribute("value");
    }

    public void Enter_CustomerName_RemaindersPage(String customername) {
        clickAndType(customer_Searchtextbox_remainderspage, customername);
        customer_Searchtextbox_remainderspage.sendKeys(Keys.ENTER);
    }

}
