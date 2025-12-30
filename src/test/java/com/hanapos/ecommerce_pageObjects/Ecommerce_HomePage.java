package com.hanapos.ecommerce_pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Ecommerce Home Page Class in the bestflorist pos application - bestflorist ecommerce website
 *
 * @Author Balaji N
 */
public class Ecommerce_HomePage extends TestBaseClass {
    public Ecommerce_HomePage() {
        PageFactory.initElements(getDriver(), this);
    }


    //============ Global Search elements ============
    @FindBy(xpath = "//input[@id='txtSearch']")
    private WebElement global_search_textBox;

    @FindBy(xpath = "//button[@id='BtnSearch']")
    private WebElement global_search_btn;

    @FindBy(xpath = "//div[@id='HeaderBanner']//marquee")
    private WebElement coupon_information_on_home_page;


//======================= Home Page Functions =======================

    /**
     * This method will get the home page url
     *
     * @return
     * @Author Balaji N
     */
    public String get_HomePage() {
        wait_For_Page_To_Be_Stable(getDriver());
        return getDriver().getCurrentUrl();
    }

    /**
     * Search the product in Global Search and click on search button on home page
     *
     * @param productName product name to be searched
     * @Author Balaji N
     */
//    public void Search_Product_In_GlobalSearch(String productName) {
//        global_search_textBox.clear();
//        global_search_textBox.click();
//        ClickAndType(global_search_textBox, productName, "Search product in Global Search");
//        delayWithGivenTime(1000);
//        js_Click(global_search_btn, "Search button on home page");
//    }
    public void Search_Product_In_GlobalSearch(String productName) {
        wait_For_Page_To_Be_Stable(getDriver());
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name must not be null or empty.");
        }

        int maxRetries = 3;
        int attempt = 0;
        boolean success = false;

        while (attempt < maxRetries && !success) {
            try {
                attempt++;

                // Wait until search box is visible and enabled
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(global_search_textBox));

                // Clear and type
                searchBox.clear();
                ClickAndType(searchBox, productName, "Search product in Global Search");

                delayWithGivenTime(1000);

                // Wait for search button clickable and click via JS for safety
                WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(global_search_btn));
                js_Click(searchButton, "Search button on home page");

                success = true;

            } catch (StaleElementReferenceException sere) {
                System.out.println("StaleElementReferenceException occurred, retrying... Attempt: " + attempt);
                if (attempt == maxRetries) {
                    System.err.println();
                    throw sere;
                }
            } catch (TimeoutException te) {
                System.err.println();
                throw te;
            } catch (Exception e) {
                System.err.println();
                throw e;
            }
        }
    }


    public String get_coupon_information_on_home_page() {
        return get_Element_Text(coupon_information_on_home_page, "Coupon information on home page").trim();
    }

    public boolean is_coupon_information_on_home_page_displayed() {
        try {
            return !coupon_information_on_home_page.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            // Element is not present or is stale, which means it's not displayed
            return true;
        }
    }


}
