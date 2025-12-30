package com.hanapos.ecommerce_pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Ecommerce Order Preview Page in the bestflorist pos application - bestflorist ecommerce website
 *
 * @Author Balaji N
 */
public class Ecommerce_Order_Preview_Page extends TestBaseClass {
    public Ecommerce_Order_Preview_Page() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//h4[@class='alert alert-info']")
    private WebElement OrderPreviewPage;

    @FindBy(xpath = "//div[contains(@class,'summarytext')]//h4/following::h2")
    private WebElement product_item_and_variant;

    @FindBy(xpath = "//div[contains(@class,'summarytext')]//h4/following::h3[1]")
    private WebElement product_qty_and_price;

    @FindBy(xpath = "//div[@class='col-lg-4 col-md-4 col-sm-12 col-xs-12 col-12 summarytext']//div//p")
    private WebElement addon_product_item_and_variant_price;

    @FindBy(xpath = "//div[@class='col-lg-4 col-md-4 col-sm-12 col-xs-12 col-12 summarytext']//div//small")
    private WebElement addon_product_qty_and_price;

    @FindBy(xpath = "//strong[@class='previewCartCheckout-price']")
    private WebElement sub_total;

    @FindBy(xpath = "//a[@id='CheckOut']")
    private WebElement proceed_to_checkout_button;


    //========================= Order Preview Page functions ==============================

    /**
     * Validate whether the order preview page is displayed or not
     *
     * @return If order preview page is displayed then true else false
     * @Description : This function will validate whether the order preview page is displayed or not
     * Author Balaji N
     */
    public boolean Verify_OrderPreviewPage() {
        wait_For_Page_To_Be_Stable(getDriver());
        return isElementDisplayed(OrderPreviewPage, "Order Preview Page");
    }

    /**
     * It retrieves the product item and variant from order preview page
     *
     * @return If product item and variant is displayed then the product item and variant from order preview page
     * @Description : This function will retrieve the product item and variant from order preview page
     * Author Balaji N
     */
    public String get_product_item_and_variant() {
        /*HighlightElement(product_item_and_variant);
        return (product_item_and_variant.getText());*/
        return get_Element_Text(product_item_and_variant, "Product item and variant");
    }

    /**
     * It retrieves the product quantity and price from order preview page
     *
     * @return If product quantity and price is displayed then the product quantity and price from order preview page
     * @Description : This function will retrieve the product quantity and price from order preview page
     * Author Balaji N
     */
    public String get_product_qty_and_price() {
       /* HighlightElement(product_qty_and_price);
        return (product_qty_and_price.getText());*/
        return get_Element_Text(product_qty_and_price, "Product quantity and price");
    }

    /**
     * It retrieves the addon product item and variant price from order preview page
     *
     * @return If addon product item and variant price is displayed then the addon product item and variant price from order preview page
     * @Description : This function will retrieve the addon product item and variant price from order preview page
     * Author Balaji N
     */
    public String get_addon_product_item_and_variant_price() {
        HighlightElement(addon_product_item_and_variant_price);
        return (addon_product_item_and_variant_price.getText());
    }

    /**
     * It retrieves the addon product quantity and price from order preview page
     *
     * @return If addon product quantity and price is displayed then the addon product quantity and price from order preview page
     * @Description : This function will retrieve the addon product quantity and price from order preview page
     * Author Balaji N
     */
    public String get_addon_product_qty_and_price() {
        HighlightElement(addon_product_qty_and_price);
        return addon_product_qty_and_price.getText();
    }

    /**
     * It retrieves the subtotal from order preview page
     *
     * @return If subtotal is displayed then the subtotal from order preview page
     * @Description : This function will retrieve the subtotal from order preview page
     * Author Balaji N
     */
    public String get_subtotal_on_order_preview_page() {
        /*HighlightElement(sub_total);
        return sub_total.getText();*/
        return get_Element_Text(sub_total, "Subtotal");
    }

    /**
     * It calculates the subtotal from order preview page
     *
     * @return If subtotal is displayed then the subtotal from order preview page
     * @Description : This function will calculate the subtotal from order preview page
     * Author Balaji N
     */
    public Double calculated_subtotal() {
        Double product = Double.parseDouble(product_qty_and_price.getText().replace("1 × $", "").trim());
        Double addon = Double.parseDouble(addon_product_qty_and_price.getText().replace("1 × $", "").trim());
        return product + addon;
    }

    public Double calculate_Subtotal_Without_Addon() {
        Double product = Double.parseDouble(product_qty_and_price.getText().replace("1 × $", "").trim());
        return product;
    }

    /**
     * It clicks on proceed to checkout button
     *
     * @Description : This function will click on proceed to checkout button
     * Author Balaji N
     */
    public void Click_proceed_to_checkout_button() {
        jsClick(proceed_to_checkout_button, "Proceed to checkout button");
    }

}
