package com.hanapos.ecommerce_pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Ecommerce Search Results Page Class in the bestflorist pos application - bestflorist ecommerce website
 *
 * @Author Balaji N
 */
public class Ecommerce_SearchResults_Page extends TestBaseClass {

    public Ecommerce_SearchResults_Page() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//h1[@id='CatName']")
    private WebElement search_result_title;

    @FindBy(xpath = "//h2[@id='NoProducttitle']")
    private WebElement no_product_found_header;

    @FindBy(xpath = "//a[@class='product-name']")
    private WebElement product_displayed_description;


    //======================= Search Result Functions =======================

    /**
     * Validates whether the search result page is displayed or not
     *
     * @return If search result page is displayed then true else false
     * Author Balaji N
     */
    public boolean Verify_Search_Result_Page_IsDisplayed() {
        return isElementDisplayed(search_result_title, "Search result page");
    }

    public boolean isNoProductFoundHeaderDisplayed() {
        return isElementDisplayed(no_product_found_header, "No product found header");
    }

    /**
     * Clicks the product displayed on search result page
     * <p>
     * This function will click the product displayed on search result page
     * Author Balaji N
     */
    public void Click_Product_Displayed_On_Search_Result_Page() {
        js_Click(product_displayed_description, "Product displayed on search result page");
    }

}
