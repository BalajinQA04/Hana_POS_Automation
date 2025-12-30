package com.hanapos.pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TaxTypes_Configuration_Setting_Page extends TestBaseClass {
    public TaxTypes_Configuration_Setting_Page() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//h2[text()='Tax Types']")
    private WebElement tax_types_page_header;

    public boolean is_Tax_Types_SettingsPage_Displayed() {
        return isElementDisplayed(tax_types_page_header, "Tax types page header");
    }


}
