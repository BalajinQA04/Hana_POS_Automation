package com.hanapos.pageObjects;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Configuration_SettingsPage class is a page object class for Configuration Settings page
 * It contains all the web elements of Configuration Settings page and thier interactions functions
 *
 * @Author Balaji N
 */
public class Configuration_SettingsPage extends TestBaseClass {

    public Configuration_SettingsPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //======================================== Configuration Settings Page WebElements ========================================

    @FindBy(xpath = "//div[@class='navbar-collapse collapse']")
    private WebElement HanaSupportPortal;

    @FindBy(xpath = "(//p[@class='configSaid-text'])[8]")
    private WebElement OrderEntryLeftMenu;

    @FindBy(xpath = "//h2[text()='Common Address Management']")
    private WebElement common_address_management_header;

    @FindBy(xpath = "//button[@onClick='AddNewRecord()' and text()='Add New']")
    private WebElement add_new_button_on_common_address_management;

    @FindBy(xpath = "//h4[text()='Common Address Management Add New Record']")
    private WebElement add_new_record_header_on_common_address_management_popup;

    @FindBy(xpath = "//h4[text()='Common Address Management Update Record']")
    private WebElement update_record_header_on_common_address_management_popup;

    @FindBy(xpath = "//input[@id='NAME']")
    private WebElement name_textBox_OnCommonAddressManagement_page;

    @FindBy(xpath = "//input[@id='ADDRESS1']")
    private WebElement address1_textBox_OnCommonAddressManagement_page;

    @FindBy(xpath = "//input[@id='ADDRESS2']")
    private WebElement address2_textBox_OnCommonAddressManagement_page;

    @FindBy(xpath = "//input[@id='ZIP']")
    private WebElement zip_textBox_OnCommonAddressManagement_page;

    @FindBy(xpath = "//input[@id='CITY']")
    private WebElement city_textBox_OnCommonAddressManagement_page;

    @FindBy(xpath = "//input[@id='STATE']")
    private WebElement state_textbox_On_CommonAddressManagement_page;

    @FindBy(xpath = "//input[@id='Country']")
    private WebElement country_textbox_On_CommonAddressManagement_page;

    @FindBy(xpath = "//input[@id='PHONE1']")
    private WebElement phonenumber1_textbox_On_CommonAddressManagement_page;

    @FindBy(xpath = "//input[@id='PHONE2']")
    private WebElement phonenumber2_textbox_On_CommonAddressManagement_page;

    @FindBy(xpath = "//input[@id='btn_Submit']")
    private WebElement submit_button_OnCommonAddressManagement_page;

    @FindBy(xpath = "//td[text()='Abish David']/following-sibling::td[1]")
    private WebElement address1_table_grid_on_common_address_management_page;

    @FindBy(xpath = "//td[text()='Abish David']/following-sibling::td[2]")
    private WebElement address2_table_grid_on_common_adddress_management_page;

    @FindBy(xpath = "//td[text()='Abish David']/following-sibling::td[3]")
    private WebElement zipcode_table_grid_on_common_adddress_management_page;

    @FindBy(xpath = "//td[text()='Abish David']/following-sibling::td[4]")
    private WebElement country_table_grid_on_common_adddress_management_page;

    @FindBy(xpath = "//td[text()='Abish David']/following-sibling::td[5]")
    private WebElement city_table_grid_on_common_adddress_management_page;

    @FindBy(xpath = "//td[text()='Abish David']/following-sibling::td[6]")
    private WebElement state_table_grid_on_common_adddress_management_page;

    @FindBy(xpath = "//td[text()='Abish David']/following-sibling::td[7]")
    private WebElement phonenumber1_table_grid_on_common_adddress_management_page;

    @FindBy(xpath = "//td[text()='Abish David']/following-sibling::td[8]")
    private WebElement phonenumber2_table_grid_on_common_adddress_management_page;


    //--------------------------------------------------------------------------------------------

    @FindBy(xpath = "//p[text()='Common Address Management']")
    private WebElement CommonAddressManagement;

    @FindBy(xpath = "(//table[@class='k-selectable'])[1]//tbody//tr[1]//td[2]//a")
    private WebElement Row1deleteIcon_On_CommonAddressGridTable;

    @FindBy(xpath = "(//button[text()='Yes'])[1]")
    private WebElement YesButton_DeleteConfirmation_On_CommonAddress;

    @FindBy(xpath = "(//table[@class='k-selectable'])[2]//tbody//tr//td[2]")
    private List<WebElement> ListOfAddress1_CommonAddressManagement;

    @FindBy(xpath = "((//div[@class='k-grid-content k-auto-scrollable'])[1]/preceding::td//a//span[@class=' k-icon k-i-pencil'])[1]")
    private WebElement editIcon_On_CommonAddressManagement_page_tablegrid;

    @FindBy(xpath = "((//div[@class='k-grid-content k-auto-scrollable'])[1]/preceding::td//a//span[@class=' k-icon k-i-delete'])[1]")
    private WebElement deleteIcon_On_CommonAddressManagement_page_tablegrid;

    @FindBy(xpath = "(//h4[text()='Confirmation'])[1]")
    private WebElement delete_confirmation_popup_Header;

    @FindBy(xpath = "(//button[text()='No'])[1]")
    private WebElement NoButton_DeleteConfirmationPopup;

    @FindBy(xpath = "(//button[text()='Yes'])[1]")
    private WebElement Yes_button_DeleteConfirmationPopup;

    @FindBy(xpath = "//span[@class='k-icon k-i-file-excel']")
    private WebElement exportToExcel_icon_Button;

    @FindBy(xpath = "//p[text()='Funeral Log']")
    private WebElement FuneralLogMenu;

    @FindBy(xpath = "//div[@id='SelecetdConfig']/child::div[1]//a")
    private WebElement orderEntryBreadcrumbLink;

    @FindBy(xpath = "(//div[@id='FuneralLogGrid']/child::div[2]//td//a)[1]")
    private WebElement EditIcon_FuneralLogGridTableRow1;

    @FindBy(xpath = "(//div[@id='FuneralLogGrid']/child::div[2]//td//a)[2]")
    private WebElement deleteIcon_FuneralLogGridTableRow1;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='×'])[1]")
    private WebElement closeIcon_FuneralLog_AddNewRecordPopup;

    @FindBy(xpath = "//button[@class='confirm']")
    private WebElement YesButton_DeleteConfirmationPopup_FuneralLogGridTableRow1;

    @FindBy(xpath = "//a[text()='First Name']")
    private WebElement firstname_Label_FuneralLog;

    @FindBy(xpath = "//div[@id='FuneralLogGrid']//table//tbody//tr[1]//td[3]")
    private WebElement FirstName_FuneralLogGridTableRow1;

    @FindBy(xpath = "//div[@id='FuneralLogGrid']//div[2]//table//tbody//tr[1]//td[4]")
    private WebElement LastName_FuneralLogGridTableRow1;

    @FindBy(xpath = "//div[@id='FuneralLogGrid']//div[2]//table//tbody//tr[1]//td[5]")
    private WebElement DeliveryDate_FuneralLogGridTableRow1;

    @FindBy(xpath = "//div[@id='FuneralLogGrid']//div[2]//table//tbody//tr[1]//td[6]")
    private WebElement DeliveryTime_FuneralLogGridTableRow1;

    @FindBy(xpath = "//div[@id='FuneralLogGrid']//div[2]//table//tbody//tr[1]//td[7]")
    private WebElement DeliveryOn_FuneralLogGridTableRow1;

    @FindBy(xpath = "//div[@id='FuneralLogGrid']//div[2]//table//tbody//tr[1]//td[8]")
    private WebElement DeliveryAddress1_FuneralLogGridTableRow1;

    @FindBy(xpath = "//div[@id='FuneralLogGrid']//div[2]//table//tbody//tr[1]//td[9]")
    private WebElement DeliveryAddress2_FuneralLogGridTableRow1;

    @FindBy(id = "lblTitle")
    private WebElement Funeral_Log_AddNewRecord_Popup;

    @FindBy(xpath = "//input[@id='FirstName']")
    private WebElement fname_Funeral_Log_AddNewRecord_Popup;

    @FindBy(xpath = "//input[@id='LastName']")
    private WebElement lname_Funeral_Log_AddNewRecord_Popup;

    @FindBy(xpath = "//select[@id='DeliveryOn']")
    private WebElement deliveryon_Funeral_Log_AddNewRecord_Popup;

    @FindBy(xpath = "//select[@id='DeliveryTime']")
    private WebElement deliverytime_Funeral_Log_AddNewRecord_Popup;

    @FindBy(xpath = "//input[@id='DeliveryDate']")
    private WebElement deliverydate_Funeral_Log_AddNewRecord_Popup;

    @FindBy(xpath = "//input[@id='Address1']")
    private WebElement address1_Funeral_Log_AddNewRecord_Popup;

    @FindBy(xpath = "//div[@class='ibox-content iframeContent']//iframe")
    private WebElement iframe_Funeral_Log_AddNewRecord_Popup;

    @FindBy(xpath = "//p[text()='Inventory Management']")
    private WebElement InventoryManagement_leftMenu;

    @FindBy(xpath = "(//h2['config-comp-head'])[9]")
    private WebElement InventoryManagement_Section;

    @FindBy(xpath = "//p[text()='Component Category']")
    private WebElement ComponentCategory_ConfigurationSettings_SubMenu;

    @FindBy(xpath = "//h2[@class='gridTitle']")
    private WebElement ComponentCategory_PageTitle;

    @FindBy(xpath = "//div[@class='ibox-content gridGrp']//div[4]//table//tr//td[3]")
    private List<WebElement> ListOfComponentCategoryTable_Markup;

    @FindBy(xpath = "//div[@class='ibox-content gridGrp']//div[4]//table//tr//td[1]")
    private List<WebElement> ListOfComponentCategoryTable_TypeName;

    @FindBy(xpath = "(//table//tbody//tr//td[normalize-space()='Flowers'])[1]/following::td[2]")
    private WebElement Markup_Value;

    @FindBy(xpath = "//a[@class='OrderEntry']")
    private WebElement OrderEntry_leftMenu;

    @FindBy(xpath = "//p[contains(text(),'Order entry settings')]")
    private WebElement OrderEntrySettings_Menu;

    @FindBy(xpath = "//select[@id='DeliveryFeeType']")
    private WebElement deliveryfee_type_dropdown_orderentrysettings_page;

    @FindBy(id = "btn_Save_Settings")
    private WebElement save_button_orderentrysettings_page;
    //========================= Coupon details settings element =========================
    @FindBy(xpath = "//p[text()='Coupon Details']")
    private WebElement CouponDetails_leftMenu;

    @FindBy(xpath = "//h2[text()='Coupon Details']")
    private WebElement CouponDetails_header;

    @FindBy(xpath = "//select[@id='shopList']")
    private WebElement shopList_dropdown_coupondetails_page;

    @FindBy(xpath = "//h2[text()='Coupon Details']/following-sibling::div//button[text()='Add New']")
    private WebElement addNew_btn_coupondetails_page;

    @FindBy(xpath = "//div[@class='CrudPopupDiv']//div/child::div//div[@class='modal-content']")
    private WebElement addNewPopup_coupondetails;

    @FindBy(xpath = "//input[@id='couponname']")
    private WebElement couponName_textbox_addNewRecord_popup_coupondetails;

    @FindBy(css = "#couponpercent")
    private WebElement couponPercent_textbox_addNewRecord_popup_coupondetails;

    @FindBy(css = "#couponstartdate")
    private WebElement couponStartDate_textbox_addNewRecord_popup_coupondetails;

    @FindBy(xpath = "//div[@class='datepicker-days']")
    private WebElement datepicker_coupon_start_date;

    @FindBy(xpath = "//div[@class='datepicker-days']//th[@class='datepicker-switch']")
    private WebElement datepicker_month_year_coupon_start_date;

    @FindBy(xpath = "//div[@class='datepicker-days']//th[@class='next']")
    private WebElement datepicker_next_month_coupon_start_date;

    @FindBy(xpath = "//div[@class='datepicker-days']//table//tbody//tr//td[@class='day' or @class='active day' or @class='today day']")
    private List<WebElement> listofdays_on_couponstartdate_datepicker;

    @FindBy(css = "#couponenddate")
    private WebElement couponEndDate_textbox_addNewRecord_popup_coupondetails;

    @FindBy(css = "#couponlimit")
    private WebElement couponLimit_textbox_addNewRecord_popup_coupondetails;

    @FindBy(css = "#couponminimum")
    private WebElement couponMinimum_textbox_addNewRecord_popup_coupondetails;

    @FindBy(xpath = "//label[@id='lbl_ApplyCouponOnDelivery']/following::span[2]")
    private WebElement use_delivery_dates_toogle_button_coupondetails_popup;

    @FindBy(xpath = "//label[@id='lbl_DisplayCouponOnWebsite']/following::span[2]")
    private WebElement display_coupon_on_website_toogle_button_coupondetails_popup;

    @FindBy(xpath = "//input[@id='btn_Submit' or text()='Submit']")
    private WebElement submit_button_add_new_record_coupondetails_popup;

    @FindBy(xpath = "(//button[text()='Yes'])[1]")
    private WebElement yes_button_delete_confirmation_coupondetails_popup;
//=========================== Payment Types configuration settings ==========================

    @FindBy(xpath = "//p[text()='Payment Types']")
    private WebElement payment_type_submenu;

    @FindBy(xpath = "//h2[text()='Payment Types']")
    private WebElement payment_types_page_header;
    // we cannot able to write direct xpath to locate edit icon of payment type
    @FindBy(xpath = "(//a[@role='button'])[16]//span")
    private WebElement marigold_payment_type_edit_icon;

    @FindBy(xpath = "//h4[text()='Payment Types Update Record']")
    private WebElement update_record_header_payment_types_popup;

    @FindBy(xpath = "//input[@id='PaymentLabel']")
    private WebElement payment_label;

    @FindBy(xpath = "(//label[@id='lbl_isRequired']/following::div//span)[1]")
    private WebElement is_enabled_toggle_button_payment_types_popup;

    @FindBy(xpath = "//input[@id='btn_Submit']")
    private WebElement submit_button_payment_types_popup;

    @FindBy(xpath = "//td[text()='Marigold']")
    private WebElement marigold_payment_type_tablegrid;

    @FindBy(xpath = "//a[@class='k-grid-filter k-state-active']")
    private WebElement filter_icon_payment_types_page;

    @FindBy(xpath = "//label[text()='is false']")
    private WebElement is_false_radio_button_payment_types_page;

    @FindBy(xpath = "//button[text()='Filter']")
    private WebElement filter_button_payment_types_page;

    @FindBy(xpath = "(//a[@role='button'])[10]")
    private WebElement marigold_payment_type_edit_icon1;

    //================= Product categories ============================
    @FindBy(xpath = "//p[text()='Product Categories']")
    private WebElement product_categories_submenu;

    @FindBy(xpath = "//h2[text()='Product Categories']")
    private WebElement product_categories_page_header;

    @FindBy(xpath = "(//td//a//span[contains(@class,'pencil')])[1]")
    private WebElement firstedit_icon_product_categories_page_tablegrid;

    @FindBy(xpath = "(//td//a//span[contains(@class,'delete')])[1]")
    private WebElement firstdelete_icon_product_categories_page_tablegrid;

    @FindBy(xpath = "//div[@class='CrudPopupDiv']//h4[normalize-space()='Confirmation']")
    private WebElement delete_confirmation_header_popup_product_categories;

    @FindBy(xpath = "//div[@class='CrudPopupDiv']//button[normalize-space()='Yes']")
    private WebElement yes_button_delete_confirmation_product_categories;

    @FindBy(xpath = "//h2[text()='Product Categories']/following-sibling::div//button[text()='Add New']")
    private WebElement add_new_button_product_categories_page;

    @FindBy(xpath = "//h4[text()='Product Categories Add New Record']")
    private WebElement add_new_record_header_product_categories_popup;

    @FindBy(xpath = "//input[@id='ProdCatName']")
    private WebElement product_category_name_textbox_addNewRecord_popup_product_categories;

    @FindBy(xpath = "//input[@id='ProdCode']")
    private WebElement product_category_code_textbox_addNewRecord_popup_product_categories;

    @FindBy(xpath = "//textarea[@id='ProdDesc']")
    private WebElement product_category_description_textbox_addNewRecord_popup_product_categories;

    @FindBy(xpath = "//label[@id='lbl_IsEvent']/following::span[1]")
    private WebElement is_event_toggle_button_product_categories_popup;

    @FindBy(xpath = "//label[@id='lbl_IsWedding']/following::span[1]")
    private WebElement is_wedding_toggle_button_product_categories_popup;

    @FindBy(xpath = "//label[@id='lbl_IsEnable']/following::span[1]")
    private WebElement is_enable_toggle_button_product_categories_popup;

    @FindBy(xpath = "//input[@id='btn_Submit']")
    private WebElement submit_button_add_new_record_product_categories_popup;

    @FindBy(xpath = "//h4[normalize-space()='Product Categories Update Record']")
    private WebElement update_record_header_product_categories_popup;

    //============ Wire out methods ==================

    @FindBy(xpath = "//p[text()='Wireout Methods']")
    private WebElement wireout_methods_submenu;

    @FindBy(xpath = "//h2[text()='Wireout Methods']")
    private WebElement wireout_methods_page_header;

    @FindBy(xpath = "(//a[@role='button'])[12]")
    private WebElement cfs_edit_icon_wireout_methods_page_tablegrid;

    @FindBy(xpath = "//h4[normalize-space()='Wireout Methods Update Record']")
    private WebElement update_record_header_wireout_methods_popup;

    @FindBy(xpath = "//label[@id='lbl_IsEnabled']/following::span[1]")
    private WebElement is_enabled_toggle_button_wireout_methods_popup;

    @FindBy(xpath = "//input[@id='btn_Save']")
    private WebElement save_button_on_wireout_methods_updaterecord_popup;
    //=========================== Inventory Management testcases ======================================
    @FindBy(xpath = "//p[normalize-space()='Inventory Management']")
    private WebElement inventory_management_menu;

    @FindBy(xpath = "//p[normalize-space()='Inventory Settings']")
    private WebElement inventory_settings_submenu;

    @FindBy(xpath = "//h2[normalize-space()='Inventory Settings']")
    private WebElement inventory_settings_page_header;

    @FindBy(xpath = "//label[@id='lbl_InventoryControlFlg']/following::span[@class='switchery switchery-default'][1]")
    private WebElement enable_inventory_control_toggle_button;

    @FindBy(xpath = "//div[@id='SelecetdConfig']//a")
    private WebElement inventory_management_breadcrumbs_link;

    @FindBy(xpath = "//section[@id='InventoryManagement']")
    private WebElement inventory_management_section_configuration_settings_page;

    @FindBy(xpath = "//input[@id='btn_Save_Settings']")
    private WebElement save_settings_button_on_inventory_management_page;
    //------------------------- Taxes configuration settings elements ----------------------------------------------
    @FindBy(xpath = "//p[text()='Taxes']")
    private WebElement taxes_submenu;

    @FindBy(xpath = "//h2[text()='Taxes']")
    private WebElement taxes_section_header;

    @FindBy(xpath = "//p[@class='config-comp' and text()='Tax Types']")
    private WebElement tax_types_menu_on_taxes_section;

    @FindBy(xpath = "//p[text()='Tax Settings']")
    private WebElement tax_settings_menu_on_taxes_section;

    @FindBy(xpath = "//h2[text()='Tax Settings']")
    private WebElement tax_settings_page_header;


    // ============================== Configuration Settings Page Functions==============================

    /**
     * Validate whether configuration settings page is displayed or not
     *
     * @return If configuration settings page is displayed it returns true otherwise false
     * @@Description: This function will validate whether configuration settings page is displayed or not
     * @Author Balaji N
     */
    public boolean Verify_Configuration_SettingsPage() {
        return isElementDisplayed(HanaSupportPortal, "Configuration Settings Page - Hana support portal");
    }

    /**
     * Clicks the order entry sub menu from left list of menus on configuration settings
     *
     * @Description: This function will highlight the order entry sub menu and then clicks the order entry sub menu
     * @Author Balaji N
     */
    public void Click_OrderEntryLeftMenu() {
        js_Click(OrderEntryLeftMenu, "Clicks the order entry sub menu from left list of menus on configuration settings");
    }

    /**
     * Clicks the common address management sub menu from left list of menus on configuration settings
     *
     * @Author Balaji N
     */
    public void Click_CommonAddressManagement() {
        js_Click(CommonAddressManagement, "common address management sub menu from left list of menus on configuration settings");
    }

    /**
     * Verifies whether the common address management page is displayed or not
     *
     * @return If the common address management page is displayed it returns true otherwise false
     * @Author Balaji N
     */
    public boolean is_CommonAddressManagement_Page_Displayed() {
        return isElementDisplayed(common_address_management_header, "common address management sub menu from left list of menus on configuration settings");
    }

    public void click_CommonAddressManagement_AddButton() {
        js_Click(add_new_button_on_common_address_management, "common address management sub menu from left list of menus on configuration settings");
    }

    public boolean is_AddNewRecord_Popup_Displayed_On_CommonAddressManagement() {
        return isElementDisplayed(add_new_record_header_on_common_address_management_popup, "common address management sub menu from left list of menus on configuration settings");
    }

    public boolean is_UpdateRecord_Popup_Displayed_On_CommonAddressManagement() {
        return isElementDisplayed(update_record_header_on_common_address_management_popup, "common address management sub menu from left list of menus on configuration settings");
    }

    public void set_Name_On_Common_Address_Management_Page(String name) {
        ClickAndType(name_textBox_OnCommonAddressManagement_page, name, "common address management sub menu from left list of menus on configuration settings");
    }

    public String get_Name_On_Common_Address_Management_Page() {
        return getElementAttribute(name_textBox_OnCommonAddressManagement_page, "common address management sub menu from left list of menus on configuration settings");
    }

    public void set_Address1_On_Common_Address_Management_Page(String address1) {
        ClickAndType(address1_textBox_OnCommonAddressManagement_page, address1, "common address management sub menu from left list of menus on configuration settings");
        delayWithGivenTime(500);
        address1_textBox_OnCommonAddressManagement_page.sendKeys(Keys.TAB);
        delayWithGivenTime(2000);
    }

    public String get_Address1_On_Common_Address_Management_Page() {
        return getElementAttribute(address1_textBox_OnCommonAddressManagement_page, "common address management sub menu from left list of menus on configuration settings");
    }


    public void set_Address2_On_Common_Address_Management_Page(String address2) {
        ClickAndType(address2_textBox_OnCommonAddressManagement_page, address2, "common address management sub menu from left list of menus on configuration settings");
    }

    public String get_Address2_On_Common_Address_Management_Page() {
        return getElementAttribute(address2_textBox_OnCommonAddressManagement_page, "common address management sub menu from left list of menus on configuration settings");
    }

    public void set_Zipcode_On_Common_Address_Management_Page(String zipcode) {
        ClickAndType(zip_textBox_OnCommonAddressManagement_page, zipcode, "common address management sub menu from left list of menus on configuration settings");
        delayWithGivenTime(500);
        zip_textBox_OnCommonAddressManagement_page.sendKeys(Keys.TAB);
        delayWithGivenTime(2000);
    }

    public String get_Zipcode_On_Common_Address_Management_Page() {
        return getElementAttribute(zip_textBox_OnCommonAddressManagement_page, "common address management sub menu from left list of menus on configuration settings");
    }

    public String get_City_On_Common_Address_Management_Page() {
        return getElementAttribute(city_textBox_OnCommonAddressManagement_page, "common address management sub menu from left list of menus on configuration settings");
    }

    public String get_State_On_Common_Address_Management_Page() {
        return getElementAttribute(state_textbox_On_CommonAddressManagement_page, "common address management sub menu from left list of menus on configuration settings");
    }

    public String get_Country_On_Common_Address_Management_Page() {
        return getElementAttribute(country_textbox_On_CommonAddressManagement_page, "common address management sub menu from left list of menus on configuration settings");
    }

    public void set_PhoneNumber1_On_Common_Address_Management_Page(String phonenumber1) {
        ClickAndType(phonenumber1_textbox_On_CommonAddressManagement_page, phonenumber1, "Phone number 1 textbox field on common address management page");
    }

    public String get_PhoneNumber1_On_Common_Address_Management_Page() {
        return getElementAttribute(phonenumber1_textbox_On_CommonAddressManagement_page, "Phone number 1 textbox field on common address management page");
    }

    public void set_PhoneNumber2_On_Common_Address_Management_Page(String phonenumber1) {
        ClickAndType(phonenumber2_textbox_On_CommonAddressManagement_page, phonenumber1, "Phone number 2 textbox field on common address management page");
    }

    public String get_PhoneNumber2_On_Common_Address_Management_Page() {
        return getElementAttribute(phonenumber2_textbox_On_CommonAddressManagement_page, "Phone number 2 textbox field on common address management page");
    }

    public void click_Submit_Button_On_Common_Address_Management_Page() {
        js_Click(submit_button_OnCommonAddressManagement_page, "Submit button on common address management page");
    }

    public String get_Displayed_Name_table_grid_common_address_management_page(String fullname) {
        WebElement name = getDriver().findElement(By.xpath("(//td[text()='" + fullname + "'])[1]"));
        return get_Element_Text(name, "Full name label on table grid row 1 - common address management");
    }

    public String get_Displayed_PhoneNumber1_table_grid_common_address_management_page(String fullname) {
        WebElement Phn1 = getDriver().findElement(By.xpath("((//td[text()='" + fullname + "'])/following-sibling::td[7])[1]"));
        return get_Element_Text(Phn1, "Phone number 2 label on table grid row 1 - common address management");
    }

    public String get_Displayed_PhoneNumber2_table_grid_common_address_management_page(String fullname) {
        WebElement Phn2 = getDriver().findElement(By.xpath("((//td[text()='" + fullname + "'])/following-sibling::td[8])[1]"));
        return get_Element_Text(Phn2, "Phone number 2 label on table grid row 1 - common address management");
    }

    public String get_Displayed_Address1_table_grid_common_address_management_page() {
        return get_Element_Text(address1_table_grid_on_common_address_management_page, "Address 1 label on table grid - common address management");
    }

    public String get_Displayed_Address2_table_grid_common_address_management_page() {
        return get_Element_Text(address2_table_grid_on_common_adddress_management_page, "Address 2 label on table grid - common address management");
    }

    public String get_Displayed_zipcode_table_grid_common_address_management_page() {
        return get_Element_Text(zipcode_table_grid_on_common_adddress_management_page, "zipcode label on table grid - common address management");
    }

    public String get_Displayed_country_table_grid_common_address_management_page() {
        return get_Element_Text(country_table_grid_on_common_adddress_management_page, "Country label on table grid - common address management");
    }

    public String get_Displayed_city_table_grid_common_address_management_page() {
        return get_Element_Text(city_table_grid_on_common_adddress_management_page, "City label on table grid - common address management");
    }

    public String get_Displayed_state_table_grid_common_address_management_page() {
        return get_Element_Text(state_table_grid_on_common_adddress_management_page, "State label on table grid - common address management");
    }

    public String get_Displayed_phonenumber1_table_grid_common_address_management_page() {
        return get_Element_Text(phonenumber1_table_grid_on_common_adddress_management_page, "Phone number 1 label on table grid - common address management");
    }

    public String get_Displayed_phonenumber2_table_grid_common_address_management_page() {
        return get_Element_Text(phonenumber2_table_grid_on_common_adddress_management_page, "Phone number 2 label on table grid - common address management");
    }

    /**
     * Verifies whether the address1 is displayed or not on common address management page
     *
     * @param Address1
     * @return If the address1 is displayed it returns true otherwise false
     * @Author Balaji N
     */
    public boolean Verify_Address1_CommonAddressManagement_IsDisplayed(String Address1) {
        delayWithGivenTime(3000);
        for (WebElement ele : ListOfAddress1_CommonAddressManagement) {
            HighlightElement(ele);
            if (ele.getText().equals(Address1)) {
                return is_Element_Displayed(ele, "Address1 Element on Common Address Management");
            }
        }
        return false;
    }

    public boolean Verify_Address1_CommonAddressManagement_IsDisplayed(String phonenumber, String Address1) {
        By address1 = By.xpath("(//td[text()='" + phonenumber + "']/preceding-sibling::td)[2]");
        WebElement ele = getDriver().findElement(address1);
        return is_Element_Displayed(ele, "Address1 Element on Common Address Management");
    }


    /**
     * Verify the displayed address1 on common address management page and returns the displayed address1
     *
     * @return If the address1 is displayed it returns the displayed address1, otherwise it returns null
     * @Author Balaji N
     */
    public String Verify_DisplayedAddress1_CommonAddressManagement() {
        for (WebElement ele : ListOfAddress1_CommonAddressManagement) {
            return getElementText(ele, "Address1 Element on Common Address Management");
        }
        return null;
    }

    public void click_Edit_Icon_On_Common_Address_Management_Page() {
        js_Click(editIcon_On_CommonAddressManagement_page_tablegrid, "Edit icon on common address management page");
    }


    public void click_Delete_Icon_On_Common_Address_Management_Page() {
        js_Click(deleteIcon_On_CommonAddressManagement_page_tablegrid, "Delete icon on common address management page");
    }

    public boolean is_Delete_Confirmation_Message_Popup_Displayed() {
        return isElementDisplayed(delete_confirmation_popup_Header, "Delete Confirmation message popup");
    }

    public void click_No_Button_On_Delete_Confirmation_Message_Popup() {
        js_Click(NoButton_DeleteConfirmationPopup, "No button on delete confirmation message popup");
    }

    public void click_Yes_Button_On_Delete_Confirmation_Message_Popup() {
        js_Click(Yes_button_DeleteConfirmationPopup, "Yes button on delete confirmation message popup");
    }

    public void click_ExportToExcel_Button_On_CommonAddressManagement() {
        js_Click(exportToExcel_icon_Button, "Export to excel button");
    }


    /**
     * Clicks the funeral log menu from left list of menus on configuration settings
     *
     * @Author Balaji N
     */
    public void Click_FuneralLogMenu() {
        js_Click(FuneralLogMenu, "Funeral Log Menu");
    }

    /**
     * Clicks the edit icon on funeral log grid table row 1 on funeral log page
     *
     * @Author Balaji N
     */
    public void Click_EditIcon_FuneralLogGridTableRow1() {
        if (is_Element_Displayed(EditIcon_FuneralLogGridTableRow1,
                "Edit Icon on funeral log grid table row 1")) {

            EditIcon_FuneralLogGridTableRow1.click();
            wait_For_Page_To_Be_Stable(getDriver());

        } else {
            String locator = (EditIcon_FuneralLogGridTableRow1).toString().replaceAll(".*-> ", "")
                    .replaceAll("]$", "");
            ;
            String errorMessage = String.format(
                    "❌ Could not click the 'Edit' icon for the first row in the Funeral Log grid table.%n" +
                            "Reason: The element was not visible or present on the page.%n" +
                            "Possible causes:%n" +
                            "  • The funeral log table did not load as expected%n" +
                            "  • There are no rows in the table to edit%n" +
                            "  • The icon's locator may have changed in the UI%n" +
                            "Element locator: %s",
                    locator
            );
            throw new RuntimeException(errorMessage);
        }
    }


    /**
     * It retrieves the first name on funeral log grid table row 1
     *
     * @return If the text is exists it returns the displayed first name as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getDisplayedFirstName() {
        wait_For_Page_To_Be_Stable(getDriver());
        try {
            getDriver().switchTo().frame(0);
            if (isElementDisplayed(FirstName_FuneralLogGridTableRow1, "First Name - Funeral Log Row1 Table Grid")) {
                return get_Element_Text(FirstName_FuneralLogGridTableRow1,
                        "First Name textbox field value on funeral log grid table row 1");
            }
        } catch (NoSuchFrameException e) {
            printError(FirstName_FuneralLogGridTableRow1, "First name textbox field - funeral log page",
                    "No such frame exception", e);
        } catch (Exception e) {
            printError(FirstName_FuneralLogGridTableRow1, "First name textbox field - funeral log page",
                    "Unexpected exception", e);
        }
        return "";
    }

    /**
     * Verify the last name on funeral log grid table row 1
     *
     * @return If the text is exists it returns the displayed last name as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getDisplayedLastName() {
        return get_Element_Text(LastName_FuneralLogGridTableRow1, "Last Name textbox field value on funeral log grid table row 1");
    }

    /**
     * Verify the delivery date on funeral log grid table row 1
     *
     * @return If the text is exists it returns the displayed delivery date as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getDisplayedDeliveryDate() {
        return getElementText(DeliveryDate_FuneralLogGridTableRow1, "Delivery Date datepicker field value on funeral log grid table row 1");
    }

    /**
     * It retrieves the delivery time on funeral log grid table row 1
     *
     * @return If the text is exists it returns the displayed delivery time as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getDisplayedDeliveryTime() {
        return getElementText(DeliveryTime_FuneralLogGridTableRow1, "Delivery Time textbox field value on funeral log grid table row 1");
    }

    /**
     * It retrieves the delivery on on funeral log grid table row 1
     *
     * @return If the text is exists it returns the displayed delivery on as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getDisplayedDeliveryOn() {
        return getElementText(DeliveryOn_FuneralLogGridTableRow1, "Delivery On textbox field value on funeral log grid table row 1");
    }

    /**
     * It retrieves the delivery address 1 on funeral log grid table row 1
     *
     * @return If the text is exists it returns the displayed delivery address 1 as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getDisplayedDeliveryAddress1() {
        return getElementText(DeliveryAddress1_FuneralLogGridTableRow1, "Delivery Address 1 textbox field value on funeral log grid table row 1");
    }

    /**
     * It retrieves the delivery address 2 on funeral log grid table row 1
     *
     * @return If the text is exists it returns the displayed delivery address 2 as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getDisplayedDeliveryAddress2() {
        return getElementText(DeliveryAddress2_FuneralLogGridTableRow1, "Delivery Address 2 textbox field value on funeral log grid table row 1");
    }

    /**
     * verify funeral log add new record popup is displayed or not
     *
     * @return If the funeral log add new record popup is displayed it returns true otherwise false
     * @Author Balaji N
     */
    public boolean Verify_FuneralLog_AddNewRecord_Popup() {
        return is_Element_Displayed(Funeral_Log_AddNewRecord_Popup, "Funeral Log Add New Record Popup");
    }

    /**
     * It retrieves the first name on funeral log add new record popup
     *
     * @return If the text is exists it returns the displayed first name as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getFirstNameFuneralLogAddNewRecordPopup() {
        return getElementAttribute(fname_Funeral_Log_AddNewRecord_Popup, "First Name textbox field value on funeral log add new record popup");
    }

    /**
     * It retrieves the last name on funeral log add new record popup
     *
     * @return If the text is exists it returns the displayed last name as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getLastNameFuneralLogAddNewRecordPopup() {
        return getElementAttribute(lname_Funeral_Log_AddNewRecord_Popup, "Last Name textbox field value on funeral log add new record popup");
    }

    /**
     * It retrieves the selected delivery on on funeral log add new record popup
     *
     * @return If the text is exists it returns the displayed delivery on as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getSelectedDeliveryOnFuneralLogAddNewRecordPopup() {
        return get_Selected_Option(deliveryon_Funeral_Log_AddNewRecord_Popup, "Delivery On dropdown field value on funeral log add new record popup");
    }

    /**
     * It retrieves the selected delivery time on funeral log add new record popup
     *
     * @return If the text is exists it returns the displayed delivery time as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getSelectedDeliveryTimeFuneralLogAddNewRecordPopup() {
        return get_Selected_Option(deliverytime_Funeral_Log_AddNewRecord_Popup, "Delivery Time dropdown field value on funeral log add new record popup");
    }

    /**
     * It retrieves the selected delivery date on funeral log add new record popup
     *
     * @return If the text is exists it returns the displayed delivery date as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getSelectedDeliveryDateFuneralLogAddNewRecordPopup() {
        return get_element_attribute_with_trim(deliverydate_Funeral_Log_AddNewRecord_Popup, "Delivery Date datepicker field value on funeral log add new record popup");
    }

    /**
     * It retrieves the address 1 on funeral log add new record popup
     *
     * @return If the text is exists it returns the displayed address 1 as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getEnteredAddress1FuneralLogAddNewRecordPopup() {
        return getElementAttribute(address1_Funeral_Log_AddNewRecord_Popup, "Address 1 textbox field value on funeral log add new record popup");
    }

    /**
     * Clicks the delete icon on funeral log grid table row 1
     *
     * @Author Balaji N
     */
    public void Click_DeleteIcon_FuneralLogGridTableRow1() {
        if (is_Element_Displayed(deleteIcon_FuneralLogGridTableRow1, "Delete icon on funeral log grid table row 1")) {
            js_Click(deleteIcon_FuneralLogGridTableRow1, "Delete icon on funeral log grid table row 1");
        }
    }

    /**
     * Click the yes button on delete confirmation popup on funeral log grid table row 1
     *
     * @Author Balaji N
     */
    public void Click_Row1_DeleteIcon_FuneralLogGridTableRow2() {
        js_Click(YesButton_DeleteConfirmationPopup_FuneralLogGridTableRow1, "Yes button on delete confirmation popup on funeral log grid table row 1");
    }

    /**
     * Clicks the close icon on funeral log add new record popup
     *
     * @Author Balaji N
     */
    public void Click_CloseIcon_FuneralLogAddNewRecordPopup() {
        js_Click(closeIcon_FuneralLog_AddNewRecordPopup, "Close icon on funeral log add new record popup");
    }

    /**
     * Clicks the order entry bread crumb link
     *
     * @Author Balaji N
     */
    public void Click_OrderEntryBreadCrumbLink() {
        getDriver().switchTo().defaultContent();
        delayWithGivenTime(1000);
        js_Click(orderEntryBreadcrumbLink, "Order Entry bread crumb link on funeral logs - configuration settings page");
    }

    /**
     * Clicks the row 1 delete icon on common address grid table
     *
     * @Author Balaji N
     */
    public void Click_Row1_DeleteIcon() {
        js_Click(Row1deleteIcon_On_CommonAddressGridTable, "Delete icon on common address grid table row 1");
    }

    /**
     * Clicks the yes button on delete confirmation popup on common address grid table
     *
     * @Author Balaji N
     */
    public void Click_YesButton_On_DeleteConfirmation() {
        delayWithGivenTime(1000);
        js_Click(YesButton_DeleteConfirmation_On_CommonAddress, "Yes button on delete confirmation popup");
    }

    /**
     * Clicks the inventory management left menu
     *
     * @Author Balaji N
     */
    public void Click_InventoryManagement_LeftMenu() {
        js_Click(InventoryManagement_leftMenu, "Inventory Management left menu");
    }

    /**
     * Verify whether the Inventory Management section is displayed or not
     *
     * @return If the Inventory Management section is displayed it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_InventoryManagement_Section_ConfigurationSetting_IsDisplayed() {
        return is_Element_Displayed(InventoryManagement_Section, "Inventory Management section");
    }

    /**
     * Clicks the component category inventory management menu from left list of menus on configuration settings
     */
    public void Click_InventoryManagement_Section_ComponentCategory() {
        js_Click(ComponentCategory_ConfigurationSettings_SubMenu, "Component category - inventory management section");
    }

    /**
     * Verify whether the component category page is displayed or not
     *
     * @return If the component category page is displayed it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_componentCategory_Page_IsDisplayed() {
        delayWithGivenTime(3000);
        return is_Element_Displayed(ComponentCategory_PageTitle, "Component category page title");
    }

    /**
     * It retreives the component markup value for flowers type name on component category page
     *
     * @return If the text is exists it returns the displayed component markup value as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String get_ComponentMarkup_Value() {
        delayWithGivenTime(2000);
        return getElementText(Markup_Value, "Component markup percentage - flower item value");
    }

    /**
     * Clicks the order entry settings menu on configuration page
     *
     * @Description: This function will highlight the order entry settings menu and then clicks the order entry settings menu
     * @Author Balaji N
     */
    public void Click_OrderEntrySettings_Menu() {
        js_Click(OrderEntrySettings_Menu, "Order Entry settings menu on configuration settings page");
    }

    /**
     * Selects the delivery fee type in order entry settings page on configuration settings
     *
     * @param deliveryType The delivery type to be selected
     * @Description This function will highlight the delivery fee type dropdown and then select the delivery fee type from dropdown
     * @Author Balaji N
     */
    public void Select_DeliveryFeeType_on_OrderEntrySettings(String deliveryType) {
        drop_Down(deliveryfee_type_dropdown_orderentrysettings_page, deliveryType, "VisibleText", "Delivery fee type dropdown on order entry configuration settings page");
    }

    /**
     * It retrieves the selected delivery fee type in order entry settings page on configuration settings
     *
     * @return If the text is exists it returns the displayed delivery fee type as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String get_SelectedDeliveryFeeType_on_OrderEntrySettings() {
        return get_selected_option(deliveryfee_type_dropdown_orderentrysettings_page, "Delivery fee type dropdown field value on order entry configuration settings page");
    }

    /**
     * Clicks the save button on order entry settings page on configuration settings
     *
     * @Description This function will highlight the save button and then clicks the save button
     * @Author Balaji N
     */
    public void Click_SaveButton_On_OrderEntrySettings() {
        js_Click(save_button_orderentrysettings_page, "Save button on order entry configuration settings page");
    }

    /**
     * Clicks the coupon details left menu
     *
     * @Author Balaji N
     */
    public void click_CouponDetails_Submenu() {
        js_Click(CouponDetails_leftMenu, "Coupon details left menu");
    }

    /**
     * Validate whether the coupon details section is displayed or not
     *
     * @return If the coupon details section is displayed it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean is_CouponDetails_Section_Displayed() {
        return is_Element_Displayed(CouponDetails_header, "Coupon details section");
    }

    /**
     * Selects the shop name from dropdown on coupon details page
     *
     * @param shopName The shop name to be selected
     * @Author Balaji N
     */
    public void select_ShopName_On_CouponDetails(String shopName) {
        drop_Down(shopList_dropdown_coupondetails_page, shopName, "VisibleText", "Shop name dropdown on coupon details page");
    }

    public String get_Selected_ShopName_On_CouponDetails() {
        return get_selected_option(shopList_dropdown_coupondetails_page, "Shop name dropdown field value on coupon details page");
    }

    /**
     * Clicks the add new coupon button on coupon details page
     *
     * @Author Balaji N
     */
    public void click_AddNew_Coupon_Button() {
        js_Click(addNew_btn_coupondetails_page, "Add new coupon button");
    }

    /**
     * Verify whether the add new record popup for coupon details page is displayed or not
     *
     * @return If the add new record popup is displayed it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean is_AddNew_Record_Popup_Coupon_Details() {
        return is_Element_Displayed(addNewPopup_coupondetails, "Add new record popup on coupon details page");
    }

    /**
     * Enters the coupon name on add new record popup for coupon details page
     *
     * @param couponName
     * @Author Balaji N
     */
    public void enter_CouponName_For_CouponDetails_Popup(String couponName) {
        ClickAndType(couponName_textbox_addNewRecord_popup_coupondetails, couponName, "Coupon name on coupon details page");
    }

    public String generate_Coupon_Name() {
        Faker faker = new Faker(new Locale("en"));

        // Generate a dynamic coupon code using custom logic
        String adjective = faker.commerce().productName().replaceAll("\\s+", "").toUpperCase(); // e.g. "GOLDENWATCH"
        int randomNumber = faker.number().numberBetween(1000, 9999);

        String coupon = "HANA-" + adjective + "-" + randomNumber;
        return coupon;
    }

    public String get_CouponName_On_CouponDetails() {
        return getElementAttribute(couponName_textbox_addNewRecord_popup_coupondetails, "Coupon name on coupon details page");
    }

    /**
     * Set the coupon percent on add new record popup for coupon details page
     *
     * @param percent
     * @Author Balaji N
     */
    public void set_Coupon_Percent_On_CouponDetails_popup(String percent) {
        ClickAndType(couponPercent_textbox_addNewRecord_popup_coupondetails, percent, "Coupon percent on coupon details page");
    }

    public String get_CouponPercent_On_CouponDetailsPopup() {
        return getElementAttribute(couponPercent_textbox_addNewRecord_popup_coupondetails, "Coupon percent on coupon details page");
    }

    public void set_Coupon_StartDate_On_CouponDetails_popup(String startDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate targetDate = LocalDate.parse(startDate, formatter);

        int targetDay = targetDate.getDayOfMonth();
        String targetMonthYear = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + targetDate.getYear();


        Click(couponStartDate_textbox_addNewRecord_popup_coupondetails, "Coupon start date on coupon details page");

        while (true) {
            String displayedMonthYear = getElementText(datepicker_month_year_coupon_start_date, "Coupon start date month year").trim();
            if (displayedMonthYear.equalsIgnoreCase(targetMonthYear)) {
                break;
            }
            click(datepicker_next_month_coupon_start_date, "Next Button on the coupon start date popup");
        }

        for (WebElement dayElement : listofdays_on_couponstartdate_datepicker) {
            if (dayElement.isEnabled() && dayElement.getText().equalsIgnoreCase(String.valueOf(targetDay))) {
                click(dayElement, "Days on the coupon start date popup");
                break;
            }
        }

    }

    /**
     * Set the coupon end date on add new record popup for coupon details page
     *
     * @param endDate
     */
    public void set_Coupon_EndDate_On_CouponDetails_popup(String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate targetDate = LocalDate.parse(endDate, formatter);

        int targetDay = targetDate.getDayOfMonth();
        String targetMonthYear = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + targetDate.getYear();

        Click(couponEndDate_textbox_addNewRecord_popup_coupondetails, "Coupon end date on coupon details page");

        while (true) {
            String displayedMonthYear = getElementText(datepicker_month_year_coupon_start_date, "Coupon end date month year").trim();
            if (displayedMonthYear.equalsIgnoreCase(targetMonthYear)) {
                break;
            }
            click(datepicker_next_month_coupon_start_date, "Next Button on the coupon end date popup");
        }

        for (WebElement dayElement : listofdays_on_couponstartdate_datepicker) {
            if (dayElement.isEnabled() && dayElement.getText().equalsIgnoreCase(String.valueOf(targetDay))) {
                click(dayElement, "Days on the coupon end date popup");
                break;
            }
        }

    }

    public void enter_Coupon_Limit_On_CouponDetails_Popup(String limit) {
        ClickAndType(couponLimit_textbox_addNewRecord_popup_coupondetails, limit, "Coupon limit on coupon details page");
    }

    public void enter_Coupon_Minimum_On_CouponDetails_Popup(String minimum) {
        ClickAndType(couponMinimum_textbox_addNewRecord_popup_coupondetails, minimum, "Coupon minimum on coupon details page");
    }

    public void Turn_On_Toogle_Use_Delivery_Dates_On_CouponDetails_Popup() {
        String backgroundColor = use_delivery_dates_toogle_button_coupondetails_popup.getCssValue("background-color");
        if (!backgroundColor.equals("rgba(26, 179, 148, 1)")) {
            Click(use_delivery_dates_toogle_button_coupondetails_popup, "Use delivery dates toggle on coupon details page");
        }
    }


    public void Turn_On_Toogle_Display_Coupon_On_Website_On_CouponDetails_Popup() {
        String backgroundColor = display_coupon_on_website_toogle_button_coupondetails_popup.getCssValue("background-color");
        System.out.println("Display coupon on website toggle on coupon details page background color:" + backgroundColor);
        if (backgroundColor.equals("rgba(237, 85, 101, 1)")) {
            Click(display_coupon_on_website_toogle_button_coupondetails_popup, "Use display coupon on website toggle on coupon details page");
        }
    }

    public boolean Verify_Toogle_TurnOn_State_Displayed_Coupon_On_Website_On_CouponDetails_Popup() {
        String backgroundColor = display_coupon_on_website_toogle_button_coupondetails_popup.getCssValue("border-color");
        if (backgroundColor.equals("rgb(197, 231, 244)")) {
            return true;
        }
        return false;
    }

    public void Turn_Off_Toogle_Display_Coupon_On_Website_On_CouponDetails_Popup() {
        String backgroundColor = display_coupon_on_website_toogle_button_coupondetails_popup.getCssValue("background-color");
        System.out.println("Display coupon on website toggle on coupon details page background color:" + backgroundColor);
        if (!backgroundColor.equals("rgba(237, 85, 101, 1)")) {
            Click(display_coupon_on_website_toogle_button_coupondetails_popup, "Use display coupon on website toggle on coupon details page");
        }
    }

    public boolean Verify_Toogle_TurnOff_State_Displayed_Coupon_On_Website_On_CouponDetails_Popup() {
        try {
            String backgroundColor = display_coupon_on_website_toogle_button_coupondetails_popup.getCssValue("border-color");
            if (!backgroundColor.equals("rgb(197, 231, 244)")) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return true;
        }
        return false;
    }

    public void turnOffToggle_UseDeliveryDates_CouponDetails() {
        String borderColor = use_delivery_dates_toogle_button_coupondetails_popup.getCssValue("border-color");
        if (borderColor.equals("rgb(197, 231, 244)")) { // Toggle is ON
            Click(use_delivery_dates_toogle_button_coupondetails_popup, "Turning OFF 'Use delivery dates' toggle");
        }
    }

    // 2. Verify that the toggle is OFF
    public boolean verifyToggleIsOff_UseDeliveryDates_CouponDetails() {
        String borderColor = use_delivery_dates_toogle_button_coupondetails_popup.getCssValue("border-color");
        if (borderColor.equals("rgb(223, 223, 223)")) {
            System.out.println("Toggle is OFF as expected.");
            return true;
        }
        return false;
    }


    public void click_Submit_Button_On_Add_New_Coupon_Details_Popup() {
        Click(submit_button_add_new_record_coupondetails_popup, "Submit button on add new record popup for coupon details page");
    }

    public void click_Yes_Button_On_Delete_Confirmation_Coupon_Popup() {
        Click(yes_button_delete_confirmation_coupondetails_popup, "Yes button on delete coupon popup");
    }

    public boolean is_Coupon_Name_Displayed_On_CouponDetails_TableGrid(String couponName) {
        WebElement element = getDriver().findElement(By.xpath("//td[text()='" + couponName + "']"));
        return is_Element_Displayed(element, "Coupon name on coupon details table grid");
    }

    public void click_Delete_Icon_On_CouponDetails_TableGrid(String couponName) {
        By element = By.xpath("(//td[text()='" + couponName + "']/preceding::td//a//span[contains(@class,'delete')])[1]");
        WebElement ele = getDriver().findElement(element);
        Click(ele, "Delete icon on coupon details table grid");
    }

    public boolean is_Coupon_Percent_Displayed_On_CouponDetails_TableGrid(String couponName) {
        WebElement element = getDriver().findElement(By.xpath("//td[text()='" + couponName + "']/following-sibling::td[1]"));
        return is_Element_Displayed(element, "Coupon name on coupon details table grid");
    }

    public boolean is_Coupon_StartDate_Displayed_On_CouponDetails_TableGrid(String couponName) {
        WebElement element = getDriver().findElement(By.xpath("//td[text()='" + couponName + "']/following-sibling::td[3]"));
        return is_Element_Displayed(element, "Coupon name on coupon details table grid");
    }

    public boolean is_Coupon_EndDate_Displayed_On_CouponDetails_TableGrid(String couponName) {
        WebElement element = getDriver().findElement(By.xpath("//td[text()='" + couponName + "']/following-sibling::td[4]"));
        return is_Element_Displayed(element, "Coupon name on coupon details table grid");
    }

    public void click_Payment_Types_Submenu() {
        js_Click(payment_type_submenu, "Payment types submenu on configuration settings page");
    }

    public boolean is_Payment_Types_Page_Header_Displayed() {
        return is_Element_Displayed(payment_types_page_header, "Payment types submenu on configuration settings page");
    }

    public void click_Edit_Icon_On_Marigold_Payment_Type() {
        js_Click(marigold_payment_type_edit_icon, "Edit icon on marigold payment type");
    }

    public boolean is_Update_Record_Payment_Type_Popup_Displayed() {
        return is_Element_Displayed(update_record_header_payment_types_popup, "Update record popup on marigold payment type");
    }

    public String get_Payment_Label() {
        return getElementAttribute(payment_label, "Payment Label");
    }

    public void click_TurnOff_Toogle_Button_On_Marigold_Payment_Type() {
        String backgroundColor = is_enabled_toggle_button_payment_types_popup.getCssValue("border-color");
        if (backgroundColor.equals("rgb(197, 231, 244)")) {
            Click(is_enabled_toggle_button_payment_types_popup, "Turn off toggle button on marigold payment type");
        }
    }

    public boolean verify_TurnOff_Toogle_Button_On_Marigold_Payment_Type() {
        String backgroundColor = is_enabled_toggle_button_payment_types_popup.getCssValue("border-color");
        if (backgroundColor.equals("rgb(223, 223, 223)")) {
            return true;
        }
        return false;
    }

    public void Enabled_Marigold_Payment_Type() {
        String backgroundColor = is_enabled_toggle_button_payment_types_popup.getCssValue("border-color");
        if (backgroundColor.equals("rgb(223, 223, 223)")) {
            Click(is_enabled_toggle_button_payment_types_popup, "Enabled toggle button on marigold payment type");
        }
    }

    public void click_Submit_Button_On_Update_Record_Payment_Type_Popup() {
        Click(submit_button_payment_types_popup, "Submit button on add new record popup for payment types page");
    }

    public boolean is_Marigold_Payment_Type_Displayed_On_PaymentTypes_TableGrid() {
        try {
            if (marigold_payment_type_tablegrid.getText().equals("Marigold")) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return true;
        }
        return false;
    }

    public void click_Filter_Icon_On_PaymentTypes_TableGrid() {
        Click(filter_icon_payment_types_page, "Filter icon on payment types table grid");
    }

    public void click_isFalse_Radio_Button_On_Filter_Popup() {
        Click(is_false_radio_button_payment_types_page, "isFalse radio button on filter popup");
    }

    public void click_Filter_Button_On_Filter_Popup() {
        Click(filter_button_payment_types_page, "Filter button on filter popup");
    }

    public void click_EditIcon_On_Marigold_Payment_Type() {
        js_Click(marigold_payment_type_edit_icon1, "Edit icon on marigold payment type");
    }

    public void click_Product_Categories_Submenu() {
        js_Click(product_categories_submenu, "Product categories submenu on configuration settings page");
    }

    public boolean is_Product_Categories_Page_Header_Displayed() {
        return is_Element_Displayed(product_categories_page_header, "Product categories submenu on configuration settings page");
    }

    public void click_AddNew_Button_On_ProductCategories_TableGrid() {
        js_Click(add_new_button_product_categories_page, "Add new button on product categories table grid");
    }

    public boolean is_Add_New_Record_ProductCategories_Popup_Displayed() {
        return is_Element_Displayed(add_new_record_header_product_categories_popup, "Add new record popup on product categories page");
    }

    public void enter_ProductCategories_Name_On_AddNewRecord_Popup(String productCategoriesName) {
        ClickAndType(product_category_name_textbox_addNewRecord_popup_product_categories, productCategoriesName, "Product categories name on add new record popup for product categories page");
    }

    public String get_ProductCategories_Name_On_AddNewRecord_Popup() {
        return getElementAttribute(product_category_name_textbox_addNewRecord_popup_product_categories, "Product categories name on add new record popup for product categories page");
    }

    public void enter_ProductCategories_Code_On_AddNewRecord_Popup(String productCategoriesCode) {
        ClickAndType(product_category_code_textbox_addNewRecord_popup_product_categories, productCategoriesCode, "Product categories code on add new record popup for product categories page");
    }

    public String get_ProductCategories_Code_On_AddNewRecord_Popup() {
        return getElementAttribute(product_category_code_textbox_addNewRecord_popup_product_categories, "Product categories code on add new record popup for product categories page");
    }

    public void enter_ProductCategories_Description_On_AddNewRecord_Popup(String productCategoriesDescription) {
        ClickAndType(product_category_description_textbox_addNewRecord_popup_product_categories, productCategoriesDescription, "Product categories description on add new record popup for product categories page");
    }

    public String get_ProductCategories_Description_On_AddNewRecord_Popup() {
        return getElementAttribute(product_category_description_textbox_addNewRecord_popup_product_categories, "Product categories description on add new record popup for product categories page");
    }

    public void turnOn_IsEvent_Toogle_Button_On_AddNewRecord_Popup() {
        String backgroundColor = is_event_toggle_button_product_categories_popup.getCssValue("border-color");
        if (backgroundColor.equals("rgb(223, 223, 223)")) {
            Click(is_event_toggle_button_product_categories_popup, "Turn on toggle button on add new record popup for product categories page");
        }
    }

    public boolean verify_IsEvent_Toogle_Button_On_TurnOn_State_AddNewRecord_Popup() {
        String backgroundColor = is_event_toggle_button_product_categories_popup.getCssValue("border-color");
        if (!backgroundColor.equals("rgb(223, 223, 223)")) {
            return true;
        }
        return false;
    }

    public void turnOff_IsEvent_Toogle_Button_On_AddNewRecord_Popup() {
        String backgroundColor = is_event_toggle_button_product_categories_popup.getCssValue("border-color");
        if (!backgroundColor.equals("rgb(223, 223, 223)")) {
            Click(is_event_toggle_button_product_categories_popup, "Turn on toggle button on add new record popup for product categories page");
        }
    }

    public boolean verify_IsEvent_Toogle_Button_On_TurnOff_State_AddNewRecord_Popup() {
        String backgroundColor = is_event_toggle_button_product_categories_popup.getCssValue("border-color");
        if (backgroundColor.equals("rgb(223, 223, 223)")) {
            return true;
        }
        return false;
    }

    public void turnOn_IsWedding_Toogle_Button_On_AddNewRecord_Popup() {
        String backgroundColor = is_wedding_toggle_button_product_categories_popup.getCssValue("border-color");
        if (backgroundColor.equals("rgb(223, 223, 223)")) {
            Click(is_wedding_toggle_button_product_categories_popup, "Turn on toggle button on add new record popup for product categories page");
        }
    }

    public boolean verify_IsWedding_Toogle_Button_On_TurnOn_State_AddNewRecord_Popup() {
        String backgroundColor = is_wedding_toggle_button_product_categories_popup.getCssValue("border-color");
        if (!backgroundColor.equals("rgb(223, 223, 223)")) {
            return true;
        }
        return false;
    }

    public void turnOff_IsWedding_Toogle_Button_On_AddNewRecord_Popup() {
        String backgroundColor = is_wedding_toggle_button_product_categories_popup.getCssValue("border-color");
        if (!backgroundColor.equals("rgb(223, 223, 223)")) {
            Click(is_wedding_toggle_button_product_categories_popup, "Turn on toggle button on add new record popup for product categories page");
        }
    }

    public boolean verify_IsWedding_Toogle_Button_On_TurnOff_State_AddNewRecord_Popup() {
        String backgroundColor = is_wedding_toggle_button_product_categories_popup.getCssValue("border-color");
        if (backgroundColor.equals("rgb(223, 223, 223)")) {
            return true;
        }
        return false;
    }

    public void turnOn_IsEnabled_Toogle_Button_On_AddNewRecord_Popup() {
        String backgroundColor = is_enable_toggle_button_product_categories_popup.getCssValue("border-color");
        if (backgroundColor.equals("rgb(223, 223, 223)")) {
            Click(is_enable_toggle_button_product_categories_popup, "Turn on toggle button on add new record popup for product categories page");
        }
    }

    public boolean verify_IsEnabled_Toogle_Button_On_TurnOn_State_AddNewRecord_Popup() {
        String backgroundColor = is_enable_toggle_button_product_categories_popup.getCssValue("border-color");
        if (!backgroundColor.equals("rgb(223, 223, 223)")) {
            return true;
        }
        return false;
    }

    public void click_Submit_Button_On_AddNewRecord_Product_Categories_Popup() {
        js_Click(submit_button_add_new_record_product_categories_popup, "Submit button on add new record popup for product categories page");
    }

    public void click_First_Edit_Icon_On_ProductCategories_Table() {
        js_Click(firstedit_icon_product_categories_page_tablegrid, "First edit icon on product categories table");
    }

    public boolean is_Update_Record_ProductCategories_Popup_Displayed() {
        return is_Element_Displayed(update_record_header_product_categories_popup, "Update record popup on product categories page");
    }

    public void click_Delete_Icon_On_ProductCategories_Table() {
        js_Click(firstdelete_icon_product_categories_page_tablegrid, "First delete icon on product categories table");
    }

    public boolean is_Delete_Confirmation_Popup_Displayed() {
        return is_Element_Displayed(delete_confirmation_header_popup_product_categories, "Delete confirmation popup on product categories page");
    }

    public void click_Yes_Button_On_DeleteConfirmation_Popup() {
        js_Click(yes_button_delete_confirmation_product_categories, "Yes button on delete confirmation popup");
    }

    public void click_WireOut_Methods_Submenu() {
        js_Click(wireout_methods_submenu, "Wire out methods submenu");
    }

    public boolean is_WireOut_Methods_Page_Header_Displayed() {
        return is_Element_Displayed(wireout_methods_page_header, "Wire out methods submenu on configuration settings page");
    }

    public void click_CFS_Edit_Icon_On_WireOutMethods_Table() {
        js_Click(cfs_edit_icon_wireout_methods_page_tablegrid, "First edit icon on wire out methods table");
    }

    public boolean is_Update_Record_WireOutMethods_Popup_Displayed() {
        return is_Element_Displayed(update_record_header_wireout_methods_popup, "Update record popup on wire out methods page");
    }

    public void turnOff_IsEnabled_Toogle_Button_On_WireOutMethods_Popup() {
        String backgroundColor = is_enabled_toggle_button_wireout_methods_popup.getCssValue("border-color");
        if (backgroundColor.equals("rgb(197, 231, 244)")) {
            js_Click(is_enabled_toggle_button_wireout_methods_popup, "Turn off toggle button on update record popup for wire out methods page");
        }
    }

    public boolean verify_IsEnabled_Toogle_Button_On_WireOutMethods_PopupTurnOff_State() {
        String backgroundColor = is_enabled_toggle_button_wireout_methods_popup.getCssValue("border-color");
        if (!backgroundColor.equals("rgb(197, 231, 244)")) {
            return true;
        }
        return false;
    }

    public void turnOn_IsEnabled_Toogle_Button_On_WireOutMethods_Popup() {
        String backgroundColor = is_enabled_toggle_button_wireout_methods_popup.getCssValue("border-color");
        if (backgroundColor.equals("rgb(223, 223, 223)")) {
            js_Click(is_enabled_toggle_button_wireout_methods_popup, "Turn on toggle button on update record popup for wire out methods page");
        }
    }

    public boolean verify_IsEnabled_Toogle_Button_On_WireOutMethods_PopupTurnOn_State() {
        String backgroundColor = is_enabled_toggle_button_wireout_methods_popup.getCssValue("border-color");
        if (!backgroundColor.equals("rgb(223, 223, 223)")) {
            return true;
        }
        return false;
    }


    public void click_Save_Button_On_WireOutMethods_Popup() {
        js_Click(save_button_on_wireout_methods_updaterecord_popup, "Save button on wire out methods popup");
    }

    //=============================== Inventory Management ===============================
    public void click_Inventory_Management_Menu() {
        click(inventory_management_menu, "Inventory Management menu");
    }

    public void click_Inventory_Settings_Submenu() {
        click(inventory_settings_submenu, "Inventory Management submenu");
    }

    public boolean is_Inventory_Settings_Page_Displayed() {
        return is_Element_Displayed(inventory_settings_page_header, "Inventory Management submenu on configuration settings page");
    }

    public boolean is_Enable_Inventory_Toogle_Button_Displayed() {
        return is_Element_Displayed(enable_inventory_control_toggle_button, "Enable inventory control toggle button");
    }

   /* public void turn_On_Enable_Inventory_Control_Toogle_Button() {
        String backgroundColor = enable_inventory_control_toggle_button.getCssValue("background-color");
        System.out.println("Turn On Background Color" + backgroundColor);
        if (backgroundColor.contains("rgba(237, 85, 101, 1)")) {
            Click(enable_inventory_control_toggle_button, "Turn on toggle button on update record popup for wire out methods page");
        }
    }

    public boolean Verify_Turn_On_Enable_Inventory_Control_Toogle_Button() {
        String backgroundColor = enable_inventory_control_toggle_button.getCssValue("border-color");
        if (backgroundColor.equals("rgb(197, 231, 244)")) {
            return true;
        }
        return false;
    }

    public void turn_Off_Enable_Inventory_Control_Toogle_Button() {
        String backgroundColor = enable_inventory_control_toggle_button.getCssValue("border-color");
        System.out.println("Before Turn Off Background Color: " + backgroundColor);
        if (!backgroundColor.equals("rgba(237, 85, 101, 1)")) {
            Click(enable_inventory_control_toggle_button, "Turn on toggle button on update record popup for wire out methods page");
            System.out.println("---- Turn Off EIC: " + backgroundColor);
        }
    }

    public boolean Verify_Turn_Off_Enable_Inventory_Control_Toogle_Button() {
        String backgroundColor = enable_inventory_control_toggle_button.getCssValue("border-color");
        System.out.println("After Turn Off Background Color: " + backgroundColor);
        if (backgroundColor.equals("rgb(223, 223, 223)")) {
            return true;
        }
        return false;
    }
*/

    /**
     * Turns ON the Enable Inventory Control toggle if it is OFF.
     */
    public void turn_On_Enable_Inventory_Control_Toogle_Button() {
        String current_background_Color = enable_inventory_control_toggle_button.getCssValue("background-color");
        System.out.println("Current Background Color: " + current_background_Color);
        if (current_background_Color.equals("rgba(237, 85, 101, 1)")) {
            Click(enable_inventory_control_toggle_button, "======= Turning ON toggle: Enable Inventory Control ==========");
        } else {
            System.out.println("Toggle already ON. No action needed.");
        }
    }

    /**
     * Verifies if the toggle is currently ON.
     */
    public boolean Verify_Turn_On_Enable_Inventory_Control_Toogle_Button() {
        String currentBorderColor = enable_inventory_control_toggle_button.getCssValue("background-color");
        System.out.println("Verify_Turn_On_Enable_Inventory_Control_Toogle_Button = Current Border Color: " + currentBorderColor);
        //  rgba(26, 179, 148, 1) - Green
        return !currentBorderColor.equals("rgba(237, 85, 101, 1)");
    }

    /**
     * Turns OFF the Enable Inventory Control toggle if it is ON.
     */
    public void turn_Off_Enable_Inventory_Control_Toogle_Button() {
        String currentBorderColor = enable_inventory_control_toggle_button.getCssValue("background-color");
        if (!currentBorderColor.equals("rgba(237, 85, 101, 1)")) {
            Click(enable_inventory_control_toggle_button, "Turning OFF toggle: Enable Inventory Control");
        } else {
            System.out.println("Toggle already OFF. No action needed.");
        }
    }

    /**
     * Verifies if the toggle is currently OFF.
     */
    public boolean Verify_Turn_Off_Enable_Inventory_Control_Toogle_Button() {
        String currentBorderColor = enable_inventory_control_toggle_button.getCssValue("background-color");
        return currentBorderColor.equals("rgba(237, 85, 101, 1)");
    }


    public void click_Inventory_Management_BreadCrumbs_HyperLink() {
        click(inventory_management_breadcrumbs_link, "Inventory Management breadcrumbs link");
    }

    public boolean is_Inventory_Management_Section_Displayed() {
        return is_Element_Displayed(inventory_management_section_configuration_settings_page, "Inventory Management section on configuration settings page");
    }

    public void click_Inventory_Settings_Save_Settings_Button() {
        click(save_settings_button_on_inventory_management_page, "Save settings button on inventory management page");
    }

    public void click_Taxes_Submenu() {
        click(taxes_submenu, "Taxes submenu from left side");
    }

    public boolean is_Taxes_Section_Displayed() {
        return is_Element_Displayed(taxes_section_header, "Taxes submenu on configuration settings page");
    }

    public void click_Tax_Types_Menu() {
        click(tax_types_menu_on_taxes_section, "Tax types menu on taxes section");
    }

    public void click_Tax_Settings_Menu_On_Taxes_Section() {
        click(tax_settings_menu_on_taxes_section, "Tax settings menu on taxes section");
    }

}

