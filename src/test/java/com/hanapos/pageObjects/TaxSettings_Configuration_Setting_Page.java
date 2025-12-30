package com.hanapos.pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TaxSettings_Configuration_Setting_Page extends TestBaseClass {
    public TaxSettings_Configuration_Setting_Page() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//h2[text()='Taxes']")
    public WebElement tax_settings_page_header;

    @FindBy(xpath = "//label[@id='lbl_DeliveryTax']/following::span[1]")
    private WebElement include_delivery_fee_in_tax_calculation_toogle_button;

    @FindBy(xpath = "//input[@id='btn_Save_Settings']")
    private WebElement save_settings_button;

    public boolean is_Tax_Settings_Configuration_Setting_Page_Displayed() {
        return isElementDisplayed(tax_settings_page_header, "Tax settings page header");
    }

    public void click_On_Include_Delivery_Fee_In_Tax_Calculation_Toogle_Button() {
        String bordercolor = include_delivery_fee_in_tax_calculation_toogle_button.getCssValue("border-color");
        System.out.println("Include delivery fee in tax calculation toogle button border color is : " + bordercolor);
        if (bordercolor.equals("rgb(223, 223, 223)")) {
            click(include_delivery_fee_in_tax_calculation_toogle_button, "Include delivery fee in tax calculation toogle button");
        }
    }

    public void click_OFF_Include_Delivery_Fee_In_Tax_Calculation_Toogle_Button() {
        String bordercolor = include_delivery_fee_in_tax_calculation_toogle_button.getCssValue("border-color");
        System.out.println("Include delivery fee in tax calculation toogle button border color is : " + bordercolor);
        if (!bordercolor.equals("rgb(223, 223, 223)")) {
            click(include_delivery_fee_in_tax_calculation_toogle_button, "Include delivery fee in tax calculation toogle button");
        }
    }



    public void click_On_Save_Settings_Button() {
        click(save_settings_button, "Save settings button on tax settings page");
    }

}
