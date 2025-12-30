package com.hanapos.ecommerce_pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Ecommerce Add On Page Class in the bestflorist POS3 application - bestflorist ecommerce website
 *
 * @Author Balaji N
 */
public class Ecommerce_Add_On_Page extends TestBaseClass {
    public Ecommerce_Add_On_Page() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//div[@id='AddOnsDisplay']//p[@class='subtitle']")
    private WebElement add_on_page_title;

    @FindBy(xpath = "//div[@id='AddOnsDisplay']//div[@class='gift_title_sec']//input[@id='Medium Box_2']")
    private WebElement add_on_chocolates_medium_checkbox;

    @FindBy(xpath = "//div[@id='AddOnsDisplay']/following::a[text()='CONTINUE TO CHECK OUT']")
    private WebElement continueToCheckoutBtn;


    /**
     * Validate whether add on page title is displayed or not
     *
     * @return If add on page title is displayed then true else false
     * @Description: This function highlights the add-on page title, and then validates whether add on page title is displayed or not
     * Author: Balaji N
     */
    public boolean verify_add_on_page_title() {
        wait_For_Page_To_Be_Stable(getDriver());
        return isElementDisplayed(add_on_page_title, "Add-on page title");
    }

    /**
     * Click on add-on chocolates as medium checkbox
     *
     * @Description: This function highlights the add-on chocolates as medium checkbox, and then clicks on it
     * Author: Balaji N
     */
    public void Click_add_on_chocolates_medium_checkbox() {
        add_on_chocolates_medium_checkbox.click();
    }

    /**
     * Click on continue to checkout button
     *
     * @Description: This function highlights the continue to checkout button, and then clicks on it
     * Author: Balaji N
     */
    public void Click_continueToCheckoutBtn() {
        js_Click(continueToCheckoutBtn, "Continue to checkout button");
    }

}
