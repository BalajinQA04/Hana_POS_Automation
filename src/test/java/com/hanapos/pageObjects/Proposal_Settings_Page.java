package com.hanapos.pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Proposal_Settings_Page extends TestBaseClass {
    public Proposal_Settings_Page() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//h2[text()='Proposal settings']")
    private WebElement proposal_settings_header;

    @FindBy(xpath = "(//label[@id='lblDeliveryFeeTax'])[1]")
    private WebElement weddingproposalsettings_IncludeDeliveryFeeinTaxCalculation_label;

    @FindBy(xpath = "(//label[@id='lblDeliveryFeeTax'])[2]")
    private WebElement eventproposalsettings_IncludeDeliveryFeeinTaxCalculation_label;

    @FindBy(xpath = "//select[@id='ddltxtWeddingSetupfeeType']")
    private WebElement weddingproposalsettings_WeddingSetupFeeType_dropdown;

    @FindBy(xpath = "//select[@id='ddltxtWeddingDeliveryfeeType']")
    private WebElement weddingproposalsettings_WeddingDeliveryFeeType_dropdown;

    @FindBy(xpath = "//input[@id='txtWeddingSetupfee']")
    private WebElement weddingproposalsettings_WeddingSetupFee_textbox;

    @FindBy(xpath = "//input[@id='txtWeddingDeliveryfee']")
    private WebElement weddingproposalsettings_WeddingDeliveryFee_textbox;

    @FindBy(xpath = "//button[@id='btnSaveWeddingSettings']")
    private WebElement weddingproposalsettings_save_settings_button;

    @FindBy(xpath = "//button[@id='btnSaveEventSettings']")
    private WebElement eventproposalsettings_save_settings_button;

    @FindBy(xpath = "//button[@id='btnWeddingUpload']")
    private WebElement uploadWeddingProposal_logo_button;

    @FindBy(xpath = "//select[@id='ddltxtEventSetupfeeType']")
    private WebElement eventproposalsettings_EventSetupFeeType_dropdown;

    @FindBy(xpath = "//select[@id='ddltxtEventDeliveryfeeType']")
    private WebElement eventproposalsettings_EventDeliveryFeeType_dropdown;

    @FindBy(xpath = "//input[@id='txtEventSetupfee']")
    private WebElement eventproposalsettings_EventSetupFee_textbox;

    @FindBy(xpath = "//input[@id='txtEventDeliveryfee']")
    private WebElement eventproposalsettings_EventDeliveryFee_textbox;


    public boolean is_Proposal_Settings_Page_Displayed() {
        return isElementDisplayed(proposal_settings_header, "Proposal settings page header");
    }

    public boolean is_Weddding_IncludeDeliveryFeeinTaxCalculation_Displayed() {
        return isElementDisplayed(weddingproposalsettings_IncludeDeliveryFeeinTaxCalculation_label, "Include Delivery Fee in Tax Calculation label");
    }

    public boolean is_Event_IncludeDeliveryFeeinTaxCalculation_Displayed() {
        return isElementDisplayed(eventproposalsettings_IncludeDeliveryFeeinTaxCalculation_label, "Include Delivery Fee in Tax Calculation label");
    }

    public void select_Wedding_Setup_Fee_Type(String setupFeeType) {
        drop_Down(weddingproposalsettings_WeddingSetupFeeType_dropdown, setupFeeType, "VisibleText", "Wedding Setup Fee Type Dropdown");
    }

    public String get_Wedding_Setup_Fee_Type() {
        return get_selected_option(weddingproposalsettings_WeddingSetupFeeType_dropdown, "Wedding Setup Fee Type Dropdown");
    }

    public void select_Wedding_Delivery_Fee_Type(String deliveryFeeType) {
        drop_Down(weddingproposalsettings_WeddingDeliveryFeeType_dropdown, deliveryFeeType, "VisibleText", "Wedding Delivery Fee Type Dropdown");
    }

    public String get_Wedding_Delivery_Fee_Type() {
        return get_selected_option(weddingproposalsettings_WeddingDeliveryFeeType_dropdown, "Wedding Delivery Fee Type Dropdown");
    }

    public void enter_Wedding_Setup_Fee(String setupFee) {
        clickAndType(weddingproposalsettings_WeddingSetupFee_textbox, setupFee);
    }

    public String get_Wedding_Setup_Fee() {
        return getElementAttribute(weddingproposalsettings_WeddingSetupFee_textbox, "Wedding Setup Fee");
    }

    public void enter_Wedding_Delivery_Fee(String deliveryFee) {
        clickAndType(weddingproposalsettings_WeddingDeliveryFee_textbox, deliveryFee);
    }

    public String get_Wedding_Delivery_Fee() {
        return getElementAttribute(weddingproposalsettings_WeddingDeliveryFee_textbox, "Wedding Delivery Fee");
    }

    public void click_WeddingProposalSettings_save_settings_button() {
        click(weddingproposalsettings_save_settings_button, "Wedding Proposal Settings save settings button");
    }

    public void click_EventProposalSettings_save_settings_button() {
        click(eventproposalsettings_save_settings_button, "Event Proposal Settings save settings button");
    }

    public void select_Event_Setup_Fee_Type(String setupFeeType) {
        drop_Down(eventproposalsettings_EventSetupFeeType_dropdown, setupFeeType, "VisibleText", "Event Setup Fee Type Dropdown");
    }

    public String get_Event_Setup_Fee_Type() {
        return get_selected_option(eventproposalsettings_EventSetupFeeType_dropdown, "Event Setup Fee Type Dropdown");
    }

    public void select_Event_Delivery_Fee_Type(String deliveryFeeType) {
        drop_Down(eventproposalsettings_EventDeliveryFeeType_dropdown, deliveryFeeType, "VisibleText", "Event Delivery Fee Type Dropdown");
    }

    public String get_Event_Delivery_Fee_Type() {
        return get_selected_option(eventproposalsettings_EventDeliveryFeeType_dropdown, "Event Delivery Fee Type Dropdown");
    }

    public void enter_Event_Setup_Fee(String setupFee) {
        clickAndType(eventproposalsettings_EventSetupFee_textbox, setupFee);
    }

    public String get_Event_Setup_Fee() {
        return getElementAttribute(eventproposalsettings_EventSetupFee_textbox, "Event Setup Fee");
    }

    public void enter_Event_Delivery_Fee(String deliveryFee) {
        clickAndType(eventproposalsettings_EventDeliveryFee_textbox, deliveryFee);
    }

    public String get_Event_Delivery_Fee() {
        return getElementAttribute(eventproposalsettings_EventDeliveryFee_textbox, "Event Delivery Fee");
    }

}
