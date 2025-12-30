package com.hanapos.pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductManagementPage extends TestBaseClass {

    public ProductManagementPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//div[@class='searchorders']//input[@id='SearchProducts']")
    private WebElement search_product_textbox_field_on_product_management_page;

    @FindBy(xpath = "(//span[text()='Red Rose']/preceding::td[2]//a[contains(@class,'Edit')])[1]")
    private WebElement edit_icon_on_product_management_page_table_grid;

    @FindBy(xpath = "//a[@id='tabInventorySetting']")
    private WebElement inventory_setting_tab_on_product_management_page;

    @FindBy(xpath = "//input[@id='txtInventory']")
    private WebElement product_level_inventory_on_product_management_page;

    @FindBy(xpath = "//a[text()='Pricing']")
    private WebElement pricing_tab_on_product_management_page;

    @FindBy(xpath = "(//td[normalize-space()='rrd2_1']//following-sibling::td[6]//a)[4]")
    private WebElement inventory_icon_on_pricing_tab_table_grid;

    @FindBy(xpath = "(//input[@id='CurrentAvailableStockOfVariant'])[1]")
    private WebElement current_available_stock_on_pricing_tab_product_management_page;

    @FindBy(xpath = "(//h4[text()='Update Inventory'])[1]/preceding-sibling::a//i")
    private WebElement close_icon_on_pricing_tab_inventory_control_product_management_page;


    public void search_Product_On_Product_Management_Page(String productname) {
        Double_Click_And_Type(search_product_textbox_field_on_product_management_page, productname,
                "Search Product textbox field on product management page");

        delayWithGivenTime(2000);
        search_product_textbox_field_on_product_management_page.sendKeys(Keys.ENTER);
    }

    public void click_Edit_Icon_On_Product_Management_Table_Grid() {
        click(edit_icon_on_product_management_page_table_grid, "Edit icon on product management page table grid");
    }

    public void click_Inventory_Tab_On_RespectiveProduct() {
        click(inventory_setting_tab_on_product_management_page, "Inventory setting tab on product management page");
    }

    public String get_Product_Level_Inventory_Value_On_Product_Management_Page() {
        return get_element_attribute(product_level_inventory_on_product_management_page, "Product level inventory on product management page");
    }

    public void click_Pricing_Tab_On_Product_Management_Page() {
        click(pricing_tab_on_product_management_page, "Pricing tab on product management page");
    }

    public void click_Inventory_Icon_On_Pricing_Tab_Table_Grid() {
        click(inventory_icon_on_pricing_tab_table_grid, "Inventory icon on pricing tab table grid");
    }

    public String get_Current_Available_Stock_Value_On_Pricing_Tab_Product_Management_Page() {
        return get_element_attribute(current_available_stock_on_pricing_tab_product_management_page, "Current available stock on pricing tab product management page");
    }

    public void click_Close_Icon_On_Pricing_Tab_Inventory_Control_Product_Management_Page() {
        click(close_icon_on_pricing_tab_inventory_control_product_management_page, "Close icon on pricing tab inventory control product management page");
    }
}
