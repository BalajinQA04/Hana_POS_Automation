package com.hanapos.pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.bidi.browsingcontext.Locator;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class WebAdmin_Products_Page extends TestBaseClass {

    public WebAdmin_Products_Page() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//h2[@class='menu-heading' and contains(text(),'Products')]")
    private WebElement product_page_header;

    @FindBy(xpath = "//input[@id='BtnNew' and @value='Add New Product']")
    private WebElement add_new_product_button;

    @FindBy(xpath = "//h4[@class='modal-title popupTitle' and contains(text(),'Add New Product')]")
    private WebElement add_new_product_popup_header;

    @FindBy(id = "QuickItemName")
    private WebElement item_name_textbox_on_addnewproducts_popup;

    @FindBy(id = "QuickItemCode")
    private WebElement itemcode_textbox_on_addnewproducts_popup;

    @FindBy(xpath = "(//label[@for='QuickIsHide'])[1]//span")
    private WebElement availability_activestate_toogle_button;

    @FindBy(xpath = "(//label[@for='QuickChkWeb'])[1]")
    private WebElement web_activestate_toogle_button;

    @FindBy(xpath = "(//label[@for='QuickChkShop'])[1]")
    private WebElement store_activestate_toogle_button;

    @FindBy(xpath = "//tbody[@id='QuickVariantform']//tr[1]//td[1]//input[1]")
    private WebElement product_variant_name_textbox1;

    @FindBy(xpath = "//tbody[@id='QuickVariantform']//tr[1]//td[2]//input")
    private WebElement product_variant_sku_textbox1;

    @FindBy(xpath = "//tbody[@id='QuickVariantform']//tr[1]//td[3]//input")
    private WebElement product_variant_cost_textbox1;

    @FindBy(xpath = "//tbody[@id='QuickVariantform']//tr[1]//td[4]//input")
    private WebElement product_variant_price_textbox1;

    @FindBy(xpath = "//iframe[@title='Upload Widget']")
    private WebElement upload_image_iframe_element;

    @FindBy(css = "input.cloudinary_fileupload")
    private WebElement uploadInput;

    @FindBy(xpath = "//div[@class='drag_content']")
    private WebElement drag_content_element;

    @FindBy(xpath = "(//a[@data-container='body' and @data-original-title='Upload image' ])[1]")
    private WebElement product_variant_upload_image1;

    @FindBy(xpath = "(//div[@data-test='drag-area']//div//div//div[@class='upload_button_holder'])")
    private WebElement product_variant_upload_image_browse_button1;

    @FindBy(xpath = "//input[@id='btnAddNewProduct' and @value='Save']")
    private WebElement save_button_on_addnewproduct_popup;

    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement toaster_message;

    @FindBy(xpath = "(//div[@class='flex items-center justify-end'])[2]//button[3]")
    private WebElement skip_button_uploadimage1;

    @FindBy(id = "txtItemCode")
    private WebElement basicDetails_Tab_item_code;

    @FindBy(id = "txtDescription")
    private WebElement basicDetails_Tab_item_name;

    @FindBy(xpath = "//a[text()='Pricing']")
    private WebElement pricing_tab_link;

    @FindBy(xpath = "//a[contains(@data-original-title,'Edit')]")
    private WebElement edit_product_pricing_tab;

    @FindBy(xpath = "//input[@id='txtAddEditVariantName']")
    private WebElement edit_variant_name_textbox_on_pricing_tab;

    @FindBy(xpath = "//input[@id='txtAddEditSKUCode']")
    private WebElement edit_variant_sku_textbox_on_pricing_tab;

    @FindBy(xpath = "//input[@id='txtAddEditPrice']")
    private WebElement editprice_textbox_on_pricing_tab;

    @FindBy(xpath = "//button[@id='SaveAddEditVariant']")
    private WebElement save_button_edit_product_variant_on_pricing_tab;

    @FindBy(css = "#tab-ProductDetails div.mainProdAutoSave")
    private WebElement webcategories_multiselect_dropdown;

    @FindBy(xpath = "//ul[@id='ddlCategories_listbox']//li[text()='Birthday']")
    private WebElement birthday_webcategories_dropdown_option;

    @FindBy(xpath = "//label[normalize-space()='Display On Home Page']/following::span[1]")
    private WebElement display_on_homepage_toggle_button;

    @FindBy(xpath = "(//div[@id='tab-ProductDetails']//label[@class='control-label' and normalize-space()='Color']/following::div[1]//span)[1]")
    private WebElement color_multiselect_dropdown_basicdetails_tab;

    @FindBy(xpath = "//ul[@class='select2-results__options']//li[normalize-space()='Blue']")
    private WebElement blue_color_option_on_color_dropdown_basicdetails_tab;

    @FindBy(xpath = "(//div[@id='tab-ProductDetails']//label[@class='control-label' and normalize-space()='Add-Ons']/following::div[1]//span)[1]")
    private WebElement add_ons_multiselect_dropdown_basicdetails_tab;

    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu']//li//a[normalize-space()='Select all']")
    private WebElement selectall_option_on_addons_multiselect_dropdown_basicdetails_tab;

    @FindBy(xpath = "//select[@id='ddlSalesCategory']")
    private WebElement sales_category_dropdown_basicdetails_tab;

    @FindBy(xpath = "//input[@id='txtkeywords']")
    private WebElement search_keywords_textbox_basicdetails_tab;

    @FindBy(xpath = "//textarea[@id='txtShortDescription']")
    private WebElement short_description_textarea_basicdetails_tab;

    @FindBy(xpath = "//textarea[@id='txtRecipe']")
    private WebElement recipe_textarea_basicdetails_tab;

    @FindBy(xpath = "//input[@id='BtnSaveProductDown']")
    private WebElement update_button_on_bottom;

    @FindBy(xpath = "//input[@id='BtnCancelTop']")
    private WebElement back_button_on_top;

    @FindBy(xpath = "//div[@class='filters']")
    private WebElement filters_icon_on_header;

    @FindBy(xpath = "//select[@id='ddlProductType']")
    private WebElement product_status_type_dropdown;

    @FindBy(xpath = "//button[@id='BtnSearchProducts']")
    private WebElement apply_filter_button;

    @FindBy(xpath = "//button[@id='removeFilters']")
    private WebElement remove_filter_button;

    @FindBy(xpath = "//div[@class='searchorders']//input[@id='SearchProducts']")
    private WebElement search_product_textbox;

    @FindBy(xpath = "//div[@id='ProductDetails']//table//tbody//td[4]")
    private WebElement product_itemcode_on_product_details_table;

    @FindBy(xpath = "//div[@id='ProductDetails']//table//tbody//td[4]//span[1]")
    private WebElement product_name_on_product_details_table_grid;

    @FindBy(xpath = "//div[@id='ProductDetails']//table//tbody//td[4]//span[2]")
    private WebElement product_price_on_product_details_table_grid;


    public boolean Verify_Products_Page_header() {
        try {
            HighlightElement(product_page_header);
            return product_page_header.isDisplayed();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Click_AddNewButton() {
        try {
            jsClick(add_new_product_button);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean Verify_AddNewProduct_Popup_IsDisplayed() {
        try {
            return isElementDisplayed(add_new_product_popup_header);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Enter_ItemName_On_AddNewProduct_Popup(String item_name) {
        try {
            clickAndType(item_name_textbox_on_addnewproducts_popup, item_name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String get_entered_itemname_on_addnewproduct_popup() {
        try {
            return get_Element_Attribute(item_name_textbox_on_addnewproducts_popup);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Enter_ItemCode_On_AddNewProduct_Popup(String item_code) {
        try {
            clickAndType(itemcode_textbox_on_addnewproducts_popup, item_code);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String get_entered_itemcode_on_addnewproduct_popup() {
        try {
            return get_Element_Attribute(itemcode_textbox_on_addnewproducts_popup);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Activate_Availability_toogle_button() {
        try {
            click(availability_activestate_toogle_button, "Active toogle button on availability section");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Deactivate_Availability_toogle_button() {
        String toogle_button_state = availability_activestate_toogle_button.getCssValue("background-color");
        if (toogle_button_state.equals("#1ab394") || toogle_button_state.equals("rgba(26, 179, 148, 1)")) {
            click(availability_activestate_toogle_button, "Deactive toogle button on availability section");
            System.out.println("Toggle was green. Deactivated it.");
        }
    }


    public void Activate_Web_toogle_button() {
        try {
            click(web_activestate_toogle_button, "Web toogle button on web section");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Activate_Store_toogle_button() {
        try {
            click(store_activestate_toogle_button, "Store toogle button on store section");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Enter_Product_Variant_Name1(String product_variant) {
        try {
            clickAndType(product_variant_name_textbox1, product_variant);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String get_entered_product_variant_name1() {
        try {
            return get_Element_Attribute(product_variant_name_textbox1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Enter_Product_Variant_SKU1(String sku) {
        try {
            clickAndType(product_variant_sku_textbox1, sku);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String get_entered_product_variant_sku1() {
        try {
            return get_Element_Attribute(product_variant_sku_textbox1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Enter_Product_Variant_Cost1(String cost) {
        try {
            clickAndType(product_variant_cost_textbox1, cost);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String get_entered_product_variant_cost1() {
        try {
            return get_Element_Attribute(product_variant_cost_textbox1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Enter_Product_Variant_Price1(String price) {
        try {
            clickAndType(product_variant_price_textbox1, price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String get_entered_product_variant_price1() {
        try {
            return get_Element_Attribute(product_variant_price_textbox1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Click_UploadIcon_for_ProductVariant1() {
        try {
            click(product_variant_upload_image1, "Upload icon for product variant 1");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click upload icon on product variant1: " + e.getMessage());
        }
    }

    public void drag_And_Drop_Image_On_ProductVariant1(String filename) {
        try {
            getDriver().switchTo().frame(upload_image_iframe_element);
            delayWithGivenTime(2000);
            String projectPath = System.getProperty("user.dir");
            String filePath = projectPath + File.separator + "testFiles" + File.separator + filename;

            File file = new File(filePath);
            if (!file.exists()) {
                throw new RuntimeException("File not found: " + filePath);
            }

            uploadInput.sendKeys(file.getAbsolutePath());
            System.out.println("Upload triggered for: " + filename);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file", e);
        }

    }

    public void Upload_Image_for_ProductVariant1(String filename) {
        try {
            getDriver().switchTo().frame(upload_image_iframe_element);
            delayWithGivenTime(1000);
            String projectPath = System.getProperty("user.dir");
            String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;

            uploadFile(product_variant_upload_image_browse_button1, fullFilePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Click_Save_button_on_AddNewProduct_Popup() {
        try {
            jsClick(save_button_on_addnewproduct_popup);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validate_toaster_message_IsAppears() {
        return isElementDisplayed(toaster_message, "Toaster message");
    }

    public String validate_toaster_message_Text() {
        try {
            return get_Element_Text(toaster_message, "Toaster Message Text");
        } catch (Exception e) {
            throw new RuntimeException("Toaster message text not found: " + e.getMessage());
        }
    }

    public void Click_Skip_Button() {
        try {
            jsClick(skip_button_uploadimage1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void enter_Itemcode_On_BasicDetails_Tab(String item_code) {
        ClickAndType(basicDetails_Tab_item_code, item_code, "Item code on basic details tab");
    }

    public String get_Displayed_itemcode_on_basic_detailsTab() {
        try {
            return get_Element_Attribute(basicDetails_Tab_item_code);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void enter_Itemname_On_BasicDetails_Tab(String item_name) {
        ClickAndType(basicDetails_Tab_item_name, item_name, "Item name on basic details tab");
    }

    public String get_Displayed_itemname_on_basic_detailsTab() {
        try {
            return get_Element_Attribute(basicDetails_Tab_item_name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void select_Web_Categories() {
        Actions actions = new Actions(getDriver());
        actions.doubleClick(webcategories_multiselect_dropdown).perform();
       // doubleClickJS(webcategories_multiselect_dropdown, "Web categories dropdown");
        delayWithGivenTime(2000);

        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].scrollIntoView(true);", birthday_webcategories_dropdown_option);

        js_Click(birthday_webcategories_dropdown_option, "Birthday Web categories dropdown option");
        delayWithGivenTime(500);
       // click(webcategories_multiselect_dropdown, "Web categories multiselect dropdown field");
       // delayWithGivenTime(2000);
//        ClickAndType(webcategories_multiselect_dropdown, "Birthday", "Web categories multi-select dropdown field");
//        delayWithGivenTime(500);
//        webcategories_multiselect_dropdown.sendKeys(Keys.ENTER);
    }


    public void Enable_Display_On_Home_Page() {
        try {
            // Wait until the toggle is visible and enabled
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(display_on_homepage_toggle_button));

            // Get the background color after ensuring it's interactable
            String bgColor = display_on_homepage_toggle_button.getCssValue("background-color");
            System.out.println("Toggle color: " + bgColor);

            if (!bgColor.equals("rgba(26, 179, 148, 1)") && !bgColor.equals("#1ab394")) {
                // Scroll into view and click with JS
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", display_on_homepage_toggle_button);
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", display_on_homepage_toggle_button);
                System.out.println("Toggle was red. Enabled it.");
            } else {
                System.out.println("Toggle is already green. No action taken.");
            }
        } catch (ElementNotInteractableException e) {
            System.err.println("Element not interactable: " + e.getMessage());
        } catch (TimeoutException e) {
            System.err.println("Element not clickable within timeout.");
        }
    }


    public void click_Back_Button_On_Top() {
        js_Click(back_button_on_top, "Back button on top");
    }

    public void click_Filter_Button_On_Header() {
        click(filters_icon_on_header, "Filter button on header");
    }

    public void select_Product_Status_On_Filters_Popup(String product_status) {
        drop_Down(product_status_type_dropdown, product_status, "VisibleText", "Product status dropdown field on filters popup");
    }

    public void click_Apply_Button_On_Filters_Popup() {
        click(apply_filter_button, "Apply button on filters popup");
    }

    public void click_Remove_Filter_Button_On_Header() {
        click(remove_filter_button, "Remove filter button on header");
    }

    public void search_And_Select_Product(String product_name) {
        // search_product_textbox
        ClickAndType(search_product_textbox, product_name, "Search product text box");
        delayWithGivenTime(500);
        search_product_textbox.sendKeys(Keys.ENTER);
    }


    public String get_Displayed_Itemcode_On_Product_Details_TableGrid() {
        String[] extracted_itemcode = get_Element_Text(product_itemcode_on_product_details_table, "Item code on product details table grid").trim().split("\\s+");
        return extracted_itemcode.length > 0 ? extracted_itemcode[0] : "";
    }

    public String get_Displayed_Product_Name_On_Product_Details_TableGrid() {
        return get_Element_Text(product_name_on_product_details_table_grid, "Product name on product details table grid");
    }

    public String get_Displayed_Product_Price_On_Product_Details_TableGrid() {
        return get_Element_Text(product_price_on_product_details_table_grid, "Product price on product details table grid");
    }

    public boolean is_InActive_Product_Displayed_On_Product_Details_TableGrid(String itemcode) {
        try {
            WebElement inactive_product = getDriver().findElement(By.xpath("//div[@id='ProductDetails']//table//tbody//td[4]/text()[contains(normalize-space(), '" + itemcode + "')]"));
            return isElementDisplayed(inactive_product, "In-active product on product details table grid");
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public void select_Colors_On_Basic_Details_Tab() {
        click(color_multiselect_dropdown_basicdetails_tab, "Color multiselect dropdown on basic details tab");
        delayWithGivenTime(500);
        click(blue_color_option_on_color_dropdown_basicdetails_tab, "Blue Color dropdown option");
        delayWithGivenTime(500);
    }

    public void select_Addons_On_Basic_Details_Tab() {
        click(add_ons_multiselect_dropdown_basicdetails_tab, "Addons multiselect dropdown on basic details tab");
        delayWithGivenTime(500);
        click(selectall_option_on_addons_multiselect_dropdown_basicdetails_tab, "Select all dropdown option");
        delayWithGivenTime(500);
    }

    public void select_Sales_Categories_On_Basic_Details_Tab(String sales_category) {
        drop_Down(sales_category_dropdown_basicdetails_tab, sales_category, "VisibleText", "Sales categories dropdown on basic details tab");
    }

    public void search_Keyword_On_Basic_Details_Tab(String keyword) {
        ClickAndType(search_keywords_textbox_basicdetails_tab, keyword, "Keyword text box on basic details tab");
        delayWithGivenTime(500);
    }

    public void description_On_Basic_Details_Tab(String description) {
        ClickAndType(short_description_textarea_basicdetails_tab, description, "Description text box on basic details tab");
        delayWithGivenTime(500);
    }

    public void recipe_On_Basic_Details_Tab(String recipe) {
        ClickAndType(recipe_textarea_basicdetails_tab, recipe, "Recipe text box on basic details tab");
        delayWithGivenTime(500);
    }

    public void click_Update_Button_On_Basic_Details_Tab() {
        click(update_button_on_bottom, "Update button on basic details tab");
    }

    public void click_Edit_Icon_On_Product_Details_TableGrid(String itemname) {
        By edit_icon = By.xpath("//div[@id='ProductDetails']//table//tbody//td[4]//span[text()='" + itemname + "']/preceding::td[2]//a//span[@class=' k-icon k-i-edit']");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        WebElement edit_product = wait.until(ExpectedConditions.elementToBeClickable(edit_icon));
        click(edit_product, "Edit icon on product details table grid");

    }

    public void click_Pricing_Tab() {
        js_Click(pricing_tab_link, "Pricing tab on product details page");
    }

    public void click_Edit_Icon_Product_Variant1_On_Pricing_Tab() {
        click(edit_product_pricing_tab, "Edit icon on product variant 1 on pricing tab");
    }

    public void edit_Product_Variant1_On_Pricing_Tab(String product_variant) {
        ClickAndType(edit_variant_name_textbox_on_pricing_tab, product_variant, "Product variant text box on pricing tab");
    }

    public void edit_SKU_On_Product_Variant1_On_Pricing_Tab(String sku) {
        ClickAndType(edit_variant_sku_textbox_on_pricing_tab, sku, "Edit sku on product variant 1 on pricing tab");
    }

    public void edit_Price_On_Product_Variant1_On_Pricing_Tab(String price) {
        ClickAndType(editprice_textbox_on_pricing_tab, price, "Edit price on product variant 1 on pricing tab");
    }

    public void click_Save_Button_Product_Variant1_On_Pricing_Tab() {
        click(save_button_edit_product_variant_on_pricing_tab, "Save button on pricing tab");
    }
}

