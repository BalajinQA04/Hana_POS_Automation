package com.hanapos.pageObjects;

import com.github.javafaker.Faker;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.List;
import java.util.Locale;

public class WebAdmin_Addon_Page extends TestBaseClass {
    public WebAdmin_Addon_Page() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//h2[text()='Add-on']")
    private WebElement add_on_page_header;

    @FindBy(xpath = "//input[@id='BtnAddNew']")
    private WebElement add_new_addon_button;

    @FindBy(xpath = "//select[@id='AddonSalesCategory']")
    private WebElement addon_sales_category_dropdown;

    @FindBy(xpath = "//select[@id='ddlFeatureType']")
    private WebElement addon_type_dropdown;

    @FindBy(xpath = "//input[@id='txtFeatureName1']")
    private WebElement addon_name_textbox;

    @FindBy(xpath = "//input[@id='txtFeaturePrice1']")
    private WebElement addon_price_textbox;

    @FindBy(xpath = "//input[@id='txtFeatureItemCode1']")
    private WebElement addon_itemcode_textbox;

    @FindBy(xpath = "//a[text()='Clear Active Dates']")
    private WebElement clear_active_dates_button;

    @FindBy(xpath = "//div[@id='AddtoCategory']//label[text()='Include Category  ']/following-sibling::div//button")
    private WebElement select_web_category_button;

    @FindBy(xpath = "//div[@class='btn-group open']//label[@class='checkbox'][normalize-space()='Balaji Flower category']")
    private WebElement balaji_flower_category_checkbox;

    @FindBy(xpath = "(//label[@for='excludeCategory'])[1]")
    private WebElement exclude_category_label;

    @FindBy(xpath = "//input[@id='BtnSubmit']")
    private WebElement create_addon_button;

    @FindBy(xpath = "//input[@id='txtCaption']")
    private WebElement caption_textbox;

    @FindBy(xpath = "//input[@value='Change']")
    private WebElement change_caption_button;

    @FindBy(xpath = "//span[@class='k-pager-sizes k-label']//span[@class='k-widget k-dropdown k-header']")
    private WebElement records_per_page_dropdown;

    @FindBy(xpath = "//div[contains(@class,'reset ')]//div[@class='k-list-scroller']//ul")
    private WebElement records_per_page_list;

    @FindBy(xpath = "//div[contains(@class,'reset ')]//div[@class='k-list-scroller']//ul//li[text()='100']")
    private WebElement records_per_page_100_option;

    @FindBy(xpath = "//div[@id='AddOnsGrid']//table//tbody//td[4]")
    private List<WebElement> list_of_addon_captions;

    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement toaster_message;


    public boolean is_Addon_Page_Displayed() {
        return isElementDisplayed(add_on_page_header, "Add-on Page header");
    }

    public void click_Add_New_AddOn_Button() {
        click(add_new_addon_button, "Add new addon button");
    }

    public void select_Addon_Sales_Category(String category) {
        drop_Down(addon_sales_category_dropdown, category, "VisibleText", "Addon sales category dropdown field");
    }

    public String get_Addon_Sales_Category() {
        return get_Selected_Option(addon_sales_category_dropdown, "Addon sales category dropdown field");
    }

    public void select_Addon_Type(String type) {
        drop_Down(addon_type_dropdown, type, "VisibleText", "Addon type dropdown field");
    }

    public String get_Addon_Type() {
        return get_Selected_Option(addon_type_dropdown, "Addon type dropdown field");
    }

    public void enter_Addon_Name(String name) {
        ClickAndType(addon_name_textbox, name, "Addon name text box");
    }

    public String get_Addon_Name() {
        return get_element_attribute(addon_name_textbox, "Addon name text box");
    }

    public void enter_Addon_Price(String price) {
        ClickAndType(addon_price_textbox, price, "Addon price text box");
    }

    public String get_Addon_Price() {
        return get_element_attribute(addon_price_textbox, "Addon price text box");
    }

    public void enter_Addon_ItemCode(String itemcode) {
        ClickAndType(addon_itemcode_textbox, itemcode, "Addon itemcode text box");
    }

    public String get_Addon_ItemCode() {
        return get_element_attribute(addon_itemcode_textbox, "Addon itemcode text box");
    }

    public void click_Clear_Active_Dates_Button() {
        click(clear_active_dates_button, "Clear active dates button");
    }

    public void select_Web_Category() {
        click(select_web_category_button, "Select web category button");
        delayWithGivenTime(2000);
        click(balaji_flower_category_checkbox, "Balaji flower category option for web category multi select dropdown");
        delayWithGivenTime(2000);
        click(exclude_category_label, "Exclude category label");
    }

    public void click_Create_Addon_Button() {
        click(create_addon_button, "Create addon button");
    }

    public static String generateCaption() {
        Faker faker = new Faker(new Locale("en-US"));

        // Components for the caption
        String adjective = faker.commerce().material(); // e.g., "Cotton", "Wooden"
        String productType = faker.commerce().productName(); // e.g., "Chair", "Lamp"
        String occasion = faker.options().option("Gift", "Holiday Special", "Romantic", "Birthday", "Anniversary", "Spring Collection");

        // Create caption like: "Romantic Wooden Chair" or "Spring Cotton Vase"
        String[] productWords = productType.split(" ");
        String coreProduct = productWords[productWords.length - 1]; // last word, like "Hat" from "Cotton Hat"

        return occasion + " " + adjective + " " + coreProduct;
    }

    public void enter_Caption(String caption) {
        ClickAndType(caption_textbox, caption, "Caption text box");
    }

    public String get_Caption() {
        return get_element_attribute(caption_textbox, "Caption text box");
    }

    // change_caption_button
    public void Upload_Image_for_Addon(String filename) {
        try {
            String projectPath = System.getProperty("user.dir");
            String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;
            uploadFile(change_caption_button, fullFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Unable to upload the addon image" + e.getMessage());
        }
    }

    public boolean is_Expected_AddOn_Caption_Present(String expectedCaption) {
        for (WebElement captionElement : list_of_addon_captions) {
            String actualCaption = captionElement.getText().trim();
            if (actualCaption.equalsIgnoreCase(expectedCaption) || actualCaption.contains(expectedCaption)) {
                System.out.println("Match found: " + actualCaption);
                return true;
            }
        }
        System.out.println("Expected caption not found: " + expectedCaption);
        return false;
    }

    public void select_Records_Per_Page() {
        click(records_per_page_dropdown, "Records per page dropdown");
        delayWithGivenTime(1000);
        if (isElementDisplayed(records_per_page_list, "Records per page list")) {
            click(records_per_page_100_option, "100 records per page option");
        }
    }

    public void click_Edit_Icon_For_Respective_Addon(String caption) {
        By ele = By.xpath("//div[@id='AddOnsGrid']//table//tbody//td[text()='" + caption + "']/preceding-sibling::td[@class='addons-grid-edit-col']");
        WebElement editIcon = getDriver().findElement(ele);
        click(editIcon, "Edit icon for respective " + caption);
    }

    public boolean validate_toaster_message_IsAppears() {
        return isElementDisplayed(toaster_message, "Toaster message");
    }

    public String validate_toaster_message_Text() {
        return get_Element_Text(toaster_message, "Toaster message");
    }

}