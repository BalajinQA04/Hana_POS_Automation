package com.hanapos.pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebAdmin_Home_Page extends TestBaseClass {

    public WebAdmin_Home_Page() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//h2[@class='menu-heading' and contains(text(),'Website Admin Home')]")
    private WebElement webadmin_home_header;

    @FindBy(xpath = "//a[@class='order-mgtmenu' and contains(text(),'Products')]")
    private WebElement products_tab_webadmin_homepage;

    @FindBy(xpath = "//a[text()='Categories']")
    private WebElement categories_tab_webadmin_homepage;

    @FindBy(xpath = "//select[@id='shopList']")
    private WebElement shop_dropdown_webadmin_homepage;

    @FindBy(xpath = "//a[@class='order-mgtmenu' and contains(text(),'Add-Ons')]")
    private WebElement addons_tab_webadmin_homepage;


    public boolean Verify_WebAdmin_home_header_IsDisplayed() {
        try {
            HighlightElement(webadmin_home_header);
            return webadmin_home_header.isDisplayed();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Click_products_tab_webAdmin_homepage() {
        try {
            jsClick(products_tab_webadmin_homepage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void click_categories_tab_webAdmin_homepage() {
        click(categories_tab_webadmin_homepage, "Categories Tab on WebAdmin Home Page");
    }

    public String get_selected_shopname_on_WebAdmin_HomePage() {
        try {
            return get_selected_option(shop_dropdown_webadmin_homepage, "Shop Name Dropdown Field on WebAdmin Home Page");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void click_Addons_Tab_WebAdmin_HomePage() {
        if (is_Element_Displayed(addons_tab_webadmin_homepage, "Addons Tab on WebAdmin Home Page")) {
            jsClick(addons_tab_webadmin_homepage, "Addons Tab on WebAdmin Home Page");
        }
    }

}
