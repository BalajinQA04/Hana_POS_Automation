package com.hanapos.pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.PageLoadLoggerUtils;
import io.qameta.allure.Allure;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.io.File;
import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The OrderEntry_Alais_PhoneOrderPage class represents the page object for the phone order page in the Hanapos application.
 * It encapsulates all the UI elements and their interactions functions on this page.
 *
 * @Author Balaji N
 */
public class OrderEntry_Alais_PhoneOrderPage extends TestBaseClass {
    public Select s;

    /**
     * Initializes the elements on the phone order page using the PageFactory.
     *
     * @Description: This constructor sets up the phone order page object by initializing
     * all the WebElements associated with this page, allowing interaction with the UI elements.
     * It uses the PageFactory to map the WebElements to their corresponding fields in the class.
     * @Author: Balaji N
     */
    public OrderEntry_Alais_PhoneOrderPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //=============================================== Phone Order Page Web Elements =============================================
    @FindBy(xpath = "//div[@class='col-md-12 hana-fullview-container quickview']")
    private WebElement quickviewonphoneorderpage;

    @FindBy(xpath = "//div[@class='col-md-12 hana-fullview-container quickview']//div[@class='modal-close-icon']//a//i")
    private WebElement closequickviewonphoneorderpage;

    @FindBy(xpath = "//select[@id='drpShopName']")
    private WebElement shop_dropdown_phoneorder_page;

    @FindBy(css = "li[data-ordermode='verifyAddress'] a[class='headerIconAnchor']")
    private WebElement verify_address_icon;

    @FindBy(xpath = "(//select[@id='drpShopUser'])[1]")
    private WebElement salesPersonDDonPhoneOrder;

    @FindBy(xpath = "(//button[contains(text(),'Update')])[2]")
    private WebElement updateBtn_on_chooseDefaultValue;

    @FindBy(xpath = "//span[@class='fa fa-clone iconSize']")
    private WebElement defaultvaluesicon;

    @FindBy(xpath = "//h3[normalize-space()='Choose Page Default Values']")
    private WebElement choosepagedefaultvaluespopup;

    @FindBy(xpath = "//input[@id='txtItemCode']")
    private WebElement itemcodeonCPDV; // CPDV - choose page default value

    @FindBy(xpath = "(//input[@id='txtQty1'])[1]")
    private WebElement qtyonCPDV;

    @FindBy(xpath = "(//select[@id='ddlDefaultWireInMethod'])[1]")
    private WebElement defaultwireinmethodonCPDV;

    @FindBy(xpath = "(//select[@id='ddlDefaultWireOutMethod'])[1]")
    private WebElement defaultwireoutmethodonCPDV;

    @FindBy(xpath = "(//select[@id='ddlPaymentType'])[1]")
    private WebElement paymenttypeonCPDV;

    @FindBy(xpath = "(//select[@id='ddlSalesPerson'])[1]")
    private WebElement salespersononCPDV;

    @FindBy(xpath = "(//select[@id='ddlSelectCountry'])[1]")
    private WebElement selectcountryonCPDV;

    @FindBy(xpath = "(//select[@id='ddlSelectLocation'])[1]")
    private WebElement selectlocationonCPDV;

    @FindBy(xpath = "//select[@id='ddlSelectOccasion']")
    private WebElement selectoccasionOnCPDV;

    @FindBy(xpath = "//select[@id='ddlPhoneOrderCustomerType']")
    private WebElement selectcustomertypeOnCPDV;

    @FindBy(xpath = "(//select[@id='ddlSelectOrderType'])[1]")
    private WebElement selectordertypeOnCPDV;

    @FindBy(xpath = "(//div[@class='modal-header']//a//i)[7]")
    private WebElement CPDV_Popup_CloseIcon;

    @FindBy(xpath = "//select[@id='orderOccasion']")
    private WebElement occasionOnphoneorderpage;

    @FindBy(xpath = "//input[@id='orderPrintSuperTicketChkBox']")
    private WebElement printTicketCheckBox_OrderDetailsSection;

    @FindBy(xpath = "//input[@id='orderPriorityFlagChkBox']/following-sibling::label")
    private WebElement priorityCheckBox_OrderDetailsSection;

    @FindBy(xpath = "//input[@id='orderreminderChkBox']")
    private WebElement sendRemainderCheckbox_OrderDetailsSection;

    @FindBy(xpath = "(//input[@id='orderItem1'])[1]")
    private WebElement itemcodeOnphoneorderpage;

    @FindBy(xpath = "(//input[@id='orderItemQty1'])[1]")
    private WebElement qtyOnphoneorderpage;

    @FindBy(xpath = "(//select[@id='customerType'])[1]")
    private WebElement custTypeOnphoneorderpage;

    @FindBy(xpath = "(//input[@id='txtRecipientPhone'])[1]")
    private WebElement recipientphoneonCPDV;

    @FindBy(xpath = "(//input[@id='txtRecipientZipcode'])[1]")
    private WebElement recipientzipcodeonCPDV;

    @FindBy(xpath = "(//input[@id='productTaxId'])[1]")
    private WebElement taxid_OnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='paymentDiscAmtTxt'])[1]")
    private WebElement paymentdiscamt_OnPhoneOrderPage;

    @FindBy(xpath = "(//select[@id='productTaxType'])[1]")
    private WebElement taxtype_OnPhoneOrderPage;

    //======================Product Section===============================//
    @FindBy(xpath = " //fieldset[contains(@class,'productinfo')]")
    private WebElement productsection_OnPhoneOrderPage;

    @FindBy(xpath = "(//legend[@class='scheduler-border'])[5]")
    private WebElement productsectionOnPhoneOrderPage;

    @FindBy(xpath = "//span[contains(text(),'Add Gift Card')]")
    private WebElement addgiftcardIcon_OnPhoneOrderPage;

    @FindBy(id = "GiftCardCreateModalTitle")
    private WebElement giftcardPopupTitle_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[1]")
    private WebElement Instantdenomination1_OnGiftcardPopup_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[2]")
    private WebElement Instantdenomination2_OnGiftcardPopup_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[3]")
    private WebElement Instantdenomination3_OnGiftcardPopup_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[4]")
    private WebElement Instantdenomination4_OnGiftcardPopup_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[5]")
    private WebElement Instantdenomination5_OnGiftcardPopup_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[6]")
    private WebElement Instantdenomination6_OnGiftcardPopup_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[7]")
    private WebElement Instantdenomination7_OnGiftcardPopup_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[8]")
    private WebElement Instantdenomination8_OnGiftcardPopup_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[9]")
    private WebElement Instantdenomination9_OnGiftcardPopup_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[10]")
    private WebElement Instantdenomination10_OnGiftcardPopup_OnPhoneOrderPage;

    @FindBy(id = "txtGiftCardAmount")
    private WebElement giftcardAmountTextboxField;

    @FindBy(id = "txtGiftCardTotal")
    private WebElement giftcardTotalDisabledTextboxField;

    @FindBy(id = "txtGiftCardProcessingFee")
    private WebElement giftcardProcessingFeeTextboxField;

    @FindBy(id = "txtGiftCardCardNumber")
    private WebElement giftcardCardNumberTextboxField;

    @FindBy(id = "txtCustomerNameForGiftCard")
    private WebElement giftcardCustomerNameTextboxField;

    @FindBy(xpath = "//ul[@id='ui-id-21']//li//div")
    private List<WebElement> ListOfGiftCardName_Autosuggestions_OnGiftCardpopup;

    @FindBy(xpath = "//div[@id='GiftCardModal']//div//div//a//i")
    private WebElement giftcardPopupCloseIcon;

    @FindBy(xpath = "//button[@id='btnSaveGiftCard']")
    private WebElement submitbutton_OnGiftCardPopup;

    @FindBy(id = "btnUpdateGiftPriceValues")
    private WebElement editIcon_OnGiftCardPopup;

    @FindBy(id = "GiftCardCreateModalTitle")
    private WebElement editgiftcardDenominationPopup;

    @FindBy(id = "txtD1")
    private WebElement denomination1_OnEditDenomiantionGiftCardPopup;

    @FindBy(id = "txtD2")
    private WebElement denomination2_OnEditDenomiantionGiftCardPopup;

    @FindBy(id = "txtD3")
    private WebElement denomination3_OnEditDenomiantionGiftCardPopup;

    @FindBy(id = "txtD4")
    private WebElement denomination4_OnEditDenomiantionGiftCardPopup;

    @FindBy(id = "txtD5")
    private WebElement denomination5_OnEditDenomiantionGiftCardPopup;

    @FindBy(id = "txtD6")
    private WebElement denomination6_OnEditDenomiantionGiftCardPopup;

    @FindBy(id = "txtD7")
    private WebElement denomination7_OnEditDenomiantionGiftCardPopup;

    @FindBy(id = "txtD8")
    private WebElement denomination8_OnEditDenomiantionGiftCardPopup;

    @FindBy(id = "txtD9")
    private WebElement denomination9_OnEditDenomiantionGiftCardPopup;

    @FindBy(id = "txtD10")
    private WebElement denomination10_OnEditDenomiantionGiftCardPopup;

    @FindBy(id = "btnCancelGiftCardSave")
    private WebElement cancelbutton_OnEditDenomiantionGiftCardPopup;

    @FindBy(id = "btnSaveGiftCardPrice")
    private WebElement savebutton_OnEditDenomiantionGiftCardPopup;
//================================================================================

    @FindBy(xpath = "//div[@id='giftCardTypeModal']//div[@class='modal-content']")
    private WebElement gift_card_popup;

    @FindBy(xpath = "//input[@id='physical']")
    private WebElement physical_giftcard_type_radio_button;

    @FindBy(xpath = "//button[@id='gctnextButton']")
    private WebElement gift_card_type_next_button;

    @FindBy(xpath = "//h3[@id='PhysicalGiftCardCreateModalTitle' and text()='Enter Gift Card Details']")
    private WebElement physical_giftcard_enterdetails_header;

    @FindBy(xpath = "//h3[@id='EGiftCardCreateModalTitle' and text()='Enter Gift Card Details']")
    private WebElement e_giftcard_enterdetails_header;

    @FindBy(xpath = "//h3[contains(text(),'Gift Card Sale')]")
    private WebElement GiftCardSalePopup;

    @FindBy(xpath = "//input[@id='txtGiftCardRecipientName']")
    private WebElement recipientname_egift_card;

    @FindBy(xpath = "//input[@id='txtGiftCardRecipientEmail']")
    private WebElement recipientemail_egift_card;

    @FindBy(xpath = "//textarea[@id='txtGiftCardMessage']")
    private WebElement message_egift_card;

    @FindBy(xpath = "//button[@id='eGiftNextButton']")
    private WebElement egift_next_button;

    @FindBy(xpath = "//h3[@id='occasionTitle']")
    private WebElement occasion_header_egift_card;

    @FindBy(xpath = "//select[@id='occasionSelect']")
    private WebElement occasion_dropdown_egift_card;

    @FindBy(xpath = "//div[@id='occasionImages']//div[3]")
    private WebElement giftcard_design_3_egift_card;

    @FindBy(xpath = "//div[@class='selected-gift']//span")
    private WebElement gift_amount_gift_card_design;

    @FindBy(xpath = "//button[@id='occasionPreviewButton']")
    private WebElement preview_button_on_occasion_egift_card;

    @FindBy(xpath = "//h3[@id='occasionPreviewTitle']")
    private WebElement preview_header_on_egift_card_sale_popup;

    @FindBy(xpath = "//input[@id='previewCustomerName']")
    private WebElement preview_customer_name_textbox_on_egift_card_sale_popup;

    @FindBy(xpath = "//input[@id='previewRecipientName']")
    private WebElement preview_recipient_name_textbox_on_egift_card_sale_popup;

    @FindBy(xpath = "//input[@id='previewSelectedOccasion']")
    private WebElement preview_occasion_textbox_on_egift_card_sale_popup;

    @FindBy(xpath = "//input[@id='previewRecipientEmail']")
    private WebElement preview_recipient_email_textbox_on_egift_card_sale_popup;

    @FindBy(xpath = "//input[@id='previewAmount']")
    private WebElement preview_amount_textbox_on_egift_card_sale_popup;

    @FindBy(xpath = "//input[@id='previewProcessingFee']")
    private WebElement preview_processing_fee_textbox_on_egift_card_sale_popup;

    @FindBy(xpath = "//input[@id='previewTotalAmount']")
    private WebElement preview_total_amount_textbox_on_egift_card_sale_popup;

    @FindBy(xpath = "//input[@id='previewCardNumber']")
    private WebElement preview_card_number_textbox_on_egift_card_sale_popup;

    @FindBy(xpath = "//textarea[@id='previewMessage']")
    private WebElement preview_message_textbox_on_egift_card_sale_popup;

    @FindBy(xpath = "//input[@id='txtCustomerNameForGiftCard']")
    private WebElement CustomerNameTextBox;

    @FindBy(xpath = "//button[@id='physicalcardNextButton']")
    private WebElement preview_button_on_enter_giftcard_details_popup;

    @FindBy(xpath = "//input[@id='physicalpreviewAmount']")
    private WebElement preview_amount_textbox_on_enter_giftcard_details_popup;

    @FindBy(xpath = "//h3[@id='PhysicalGiftCardPreviewTitle']")
    private WebElement preview_header_on_enter_giftcard_details_popup;

    @FindBy(xpath = "//input[@id='physicalpreviewProcessingFee']")
    private WebElement preview_processing_fee_textbox_on_enter_giftcard_details_popup;

    @FindBy(xpath = "//input[@id='physicalpreviewCardNumber']")
    private WebElement preview_card_number_textbox_on_enter_giftcard_details_popup;

    @FindBy(xpath = "//input[@id='physicalpreviewCustomerName']")
    private WebElement preview_customer_name_textbox_on_enter_giftcard_details_popup;

    @FindBy(xpath = "//input[@id='physicalpreviewTotalAmount']")
    private WebElement preview_total_amount_textbox_on_enter_giftcard_details_popup;

    @FindBy(xpath = "//button[@id='btnPhysicalSaveGiftCard']")
    private WebElement preview_submit_button_on_popup;

    @FindBy(xpath = "//button[@id='btnSaveEGiftCard']")
    private WebElement preview_submit_button_on_egiftcard_popup;

    @FindBy(xpath = "//input[@id='egift']")
    private WebElement egift_radio_button;

    @FindBy(xpath = "//input[@id='txtGiftCardTotal']")
    private WebElement GiftCardTotalPriceTextBox;


    // -------------------------Recipient Section --------------------------------//
    @FindBy(xpath = "//fieldset[@class='col-md-7 col-sm-7 scheduler-border borderLeft recepientinfo']")
    private WebElement recipientsectionOnPhoneOrderPage;

    @FindBy(xpath = "//fieldset[contains(@class, 'recepientinfo')]//legend//span")
    private WebElement recipient_header_label_following_recipientKMS_label;

    @FindBy(xpath = "(//input[@id='reciFirstName'])[1]")
    private WebElement recipientfirstnameOnPhoneOrderPage;

    @FindBy(xpath = "//ul[starts-with(@id, 'ui-id-') and not(contains(@style,'display: none'))]")   //ul[@id='ui-id-23']
    private WebElement Recipient_firstname_autosugesstion_values;

    @FindBy(xpath = "//ul[starts-with(@id, 'ui-id-') and not(contains(@style,'display: none'))]//li//div")
    private List<WebElement> ListOfReciFirstName_Autosuggestions_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@class='toast toast-success']")
    private WebElement addressverifiedtoastmsg;

    @FindBy(xpath = "//div[@class='toast-title']")
    private WebElement addressverifiedmsg;

    @FindBy(xpath = "(//input[@id='reciLastName'])[1]")
    private WebElement recipientlastnameOnPhoneOrderPage;

    @FindBy(xpath = "//span[@id='viewMap']")
    private WebElement googleIcon_ReciAddress1_OnPhoneOrderPage;

    @FindBy(xpath = "//h4[contains(text(),'Map Directions')]")
    private WebElement mapdirectionsPopup_ReciAddress1_OnPhoneOrderPage;

    @FindBy(xpath = "//table[@class='adp-directions']//tr[1]")
    private WebElement directionsRow1_ReciAddress1_OnPhoneOrderPage;

    @FindBy(xpath = "//iframe[@id='mapPreview']")
    private WebElement mapiframe_ReciAddress1_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@class='gm-style-iw gm-style-iw-c']//div[2]//div")
    private WebElement tooltiptext_MapDirectionPopup_ReciAddress1_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@class='gm-style-iw gm-style-iw-c']//div[1]//button")
    private WebElement TooltipCloseIcon_MapDirectionPopup_ReciAddress1_OnPhoneOrderPage;

    @FindBy(xpath = "(//table[@id='control']//tbody//tr[2]//td[@valign='top'][2]//div//div[@class='gm-style-mtc']//button)[2]")
    private WebElement SateliteTab_MapDirectionPopupReciAddress1_OnPhoneOrderPage;

    @FindBy(xpath = "(//ul[@role='menu']//li//label)[2]")
    private WebElement satelliteTabLabel_MapDirectionPopupReciAddress1_OnPhoneOrderPage;

    @FindBy(xpath = "(//table[@id='control']//tbody//tr[2]//td[@valign='top'][2]//div//div[@class='gm-style-mtc']//button)[1]")
    private WebElement MapTab_MapDirectionPopupReciAddress1_OnPhoneOrderPage;

    @FindBy(xpath = "(//button[@class='gm-control-active'])[3]")
    private WebElement plusIcon_MapDirectionPopupReciAddress1_OnPhoneOrderPage;

    @FindBy(xpath = "(//button[@class='gm-control-active'])[4]")
    private WebElement minusIcon_MapDirectionPopupReciAddress1_OnPhoneOrderPage;

    @FindBy(xpath = "(//div[@class='gm-style']//div//div//div//div//div)[1]")
    private WebElement VerifyZoomIn_MapDirectionPopupReciAddress1_OnPhoneOrderPage;

    @FindBy(xpath = "//button[@class='gm-svpc']")
    private WebElement DragPegman_MapDirectionPopupReciAddress1_OnPhoneOrderPage;

    @FindBy(xpath = "(//button[@class='gm-control-active gm-fullscreen-control'])[1]")
    private WebElement FullScreen_MapDirectionPopupReciAddress1_OnPhoneOrderPage;

    @FindBy(xpath = "(//div[@class='modal-content']//a//i)[3]")
    private WebElement CloseIcon_MapDirectionPopupReciAddress1_OnPhoneOrderPage;

    @FindBy(xpath = "(//ul[@role='menu']//li//label)[1]")
    private WebElement satelliteTabTerrain_MapDirectionPopupReciAddress1_OnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='reciAddress1'])")
    private WebElement recipientaddress1OnPhoneOrderPage;

    @FindBy(xpath = "//a[@class='headerIconAnchor']//span[@class='fa fa-map-marker iconSize']")
    private WebElement view_map_icon_on_top;

    @FindBy(xpath = "//li[@data-ordermode='milecalc']//a[@class='headerIconAnchor']//span")
    private WebElement calculate_miles_icon;

    @FindBy(xpath = "//div[@class='pac-container pac-logo hdpi']//div//span[2]")
    private List<WebElement> ListOfReciAddress1_Autosuggestions_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@class='pac-container pac-logo hdpi']//div//span[3]")
    private List<WebElement> ListOfReciAddress1_CityStateCountry_Autosuggestions_OnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='reciAddress2'])")
    private WebElement recipientaddress2OnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='reciZipCode'])[1]")
    private WebElement recipientzipcodeOnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='reciCity'])[1]")
    private WebElement recipientcityOnPhoneOrderPage;

    @FindBy(xpath = "(//select[@id='reciCountry'])[1]")
    private WebElement recipientcountryOnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='reciPhone1'])[1]")
    private WebElement recipientphoneOnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='reciPhone2'])[1]")
    private WebElement recipientphone2OnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='reciAttention']")
    private WebElement recipientattentionOnPhoneOrderPage;

    @FindBy(xpath = "(//div[@class='pac-container pac-logo hdpi'])[3]")
    private WebElement attention_textbox_field_autosuggestion;

    @FindBy(xpath = "//input[@id='reciAptFloor']")
    private WebElement recipientaptfloorOnPhoneOrderPage;

    @FindBy(xpath = "(//select[@id='reciLocationType'])[1]")
    private WebElement recipientlocationOnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='reciDate']")
    private WebElement recipientDeliverydateOnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='reciTime']")
    private WebElement recipientDeliverytimeOnPhoneOrderPage;

    @FindBy(xpath = "//select[@id='reciTimeType']")
    private WebElement selectDeliverytimeonDropdown_RecipientSectionOnPhoneOrderPage;

    @FindBy(xpath = "//div[@class='col-md-3 col-sm-3 col-xs-12 text-right no-padding']//a//span")
    private WebElement ClearTimeButton_recipientSectionOnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='reciState']")
    private WebElement recipientstateOnPhoneOrderPage;

    @FindBy(xpath = "//button[contains(@class,'multiselect dropdown')]")
    private WebElement recipientSelectOptionsBtnOnPhoneOrderPage;

    @FindBy(xpath = "(//ul[@class='multiselect-container dropdown-menu']//li//a//label)[1]")
    private WebElement selectallOption_RecipientSelectOptionsDropDown;

    @FindBy(xpath = "(//ul[@class='multiselect-container dropdown-menu']//li//a//label)[2]")
    private WebElement confidentialOption_RecipientSelectOptionsDropDown;

    @FindBy(xpath = "(//ul[@class='multiselect-container dropdown-menu']//li//a//label)[3]")
    private WebElement FrequentAddrOption_RecipientSelectOptionsDropDown;

    @FindBy(xpath = "(//ul[@class='multiselect-container dropdown-menu']//li//a//label)[4]")
    private WebElement saveFuneralOption_RecipientSelectOptionsDropDown;

    @FindBy(xpath = "//ul[@class='multiselect-container dropdown-menu']//li//a//label")
    private List<WebElement> recipientSelectOptionsListOnPhoneOrderPage;

    @FindBy(xpath = "//span[@Class='multiselect-selected-text']")
    private WebElement selectedRecipientOptionsOnPhoneOrderPage;

    @FindBy(xpath = "//select[@id='reciLocationType']")
    private WebElement recipientLocation;

    @FindBy(xpath = "//select[@id='reciZone']")
    private WebElement recipientzone;

    @FindBy(xpath = "//input[@id='paymentCouponCodeTxt']")
    private WebElement coupon_code_textboxfield_payment_section;

    @FindBy(css = "#paymentDiscPercTxt")
    private WebElement discount_percentage_textboxfield_payment_section;


    // ================== Wire In section =====================================
    @FindBy(xpath = "//li[@data-ordermode='wirein']")
    private WebElement wireinmethodOnPhoneOrderPage;

    @FindBy(xpath = "(//select[@id='wireinMethods'])[1]")
    private WebElement wireIndefaultDDOnPhoneOrderPage;

    // ================ Wire Out section ===============================
    @FindBy(xpath = "//li[@data-ordermode='wireout']")
    private WebElement wireoutmethodOnPhoneOrderPage;

    @FindBy(xpath = "(//select[@id='wireoutMethods'])[1]")
    private WebElement wireOutdefaultDDOnPhoneOrderPage;

    // ================ Delivery Type Section========================
    @FindBy(xpath = "//li[@data-ordermode='delivery']")
    private WebElement deliverytypeOnPhoneOrderPage;

    @FindBy(xpath = "//fieldset[contains(@class,'paymentinfo')]")
    private WebElement paymentSectionOnPhoneOrderPage;

    @FindBy(css = "#paymentOptions")
    private WebElement paymentTypeDropdownOnPhoneOrderPage;

    @FindBy(xpath = "//select[@id='paymentOptions']//option[not(@disabled) and not(contains(@style, 'display: none'))]")
    private List<WebElement> paymentTypeDropdownOptionsOnPhoneOrderPage;

    @FindBy(id = "paymentSpiltCashRegister")
    private WebElement splitpayment_cashregistry;


    //==================== Wire In deliveryType Section ================
    @FindBy(xpath = "//ul[@id='orderModes']//li[@data-ordermode='wirein']//a")
    private WebElement wirein_deliverytype_OnPhoneOrderPage;

    @FindBy(id = "wireinFirstName")
    private WebElement wireinFirstName_OnPhoneOrderPage;

    @FindBy(id = "wireinLastName")
    private WebElement wireinLastName_OnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='wireinShopCode']")
    private WebElement wireinShopCode_OnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='wireinShopName']")
    private WebElement wireinShopName_OnPhoneOrderPage;

    @FindBy(xpath = "//select[@id='wireinMethods']")
    private WebElement wireinMethods_OnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='wireinOrderNum']")
    private WebElement wireinOrderNum_OnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='wireinEmail'])[1]")
    private WebElement wireinEmail_OnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='wireinPhone'])[1]")
    private WebElement wireinPhone_OnPhoneOrderPage;

    @FindBy(xpath = "//textarea[@id='wireinShopAddress']")
    private WebElement wireinShopAddress_OnPhoneOrderPage;

    @FindBy(xpath = "//textarea[@id='wireinComments']")
    private WebElement wireinComments_OnPhoneOrderPage;


    //============================== Wire Out deliveryType Section ================
    @FindBy(xpath = "//li[@data-ordermode='wireout']//a")
    private WebElement wireout_deliverytype_OnPhoneOrderPage;

    @FindBy(id = "wireoutMethods")
    private WebElement wireoutMethods_OnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='wireoutFlorist']")
    private WebElement wireoutFlorist_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='florist-action-modal']//div[@class='modal-content']")
    private WebElement wireout_chooseflorist_popup;

    @FindBy(xpath = "//input[@id='wireoutFlorist']/following::span[1]//a//span")
    private WebElement search_icon_On_WireOutInfo;

    @FindBy(xpath = "//strong[normalize-space()='Select Florist']")
    private WebElement choose_florist_popup_header_WireOutInfo;

    @FindBy(xpath = "//div[@class='col-lg-6 col-sm-12 wireoutAmt']")
    private WebElement wireoutAmt_OnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='wireoutAmount']")
    private WebElement wireoutAmount_OnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='actionSearchFlorist']")
    private WebElement wireout_chooseflorist_popup_searchbox;

    @FindBy(xpath = "//button[@class='btn btn-sm btn-primary']")
    private WebElement wireout_chooseflorist_popup_search_button;

    @FindBy(xpath = "//div[@id='divflorist']//div[1]//a")
    private List<WebElement> wireout_zipcode_search_result_florists;

    @FindBy(xpath = "(//div[@id='divflorist']//div[1]//a)[1]")
    private WebElement wireout_chooseflorist_popup_displayed_firstsearchresult;

    //======================= Check Payment Section =======================

    @FindBy(xpath = "//input[@id='paymentBankName']/following-sibling::div//div[@class='popover-content']")
    private WebElement BankNameRequired_Popup_ErrorMsg_On_Phoneorderpage;

    @FindBy(id = "paymentBankName")
    private WebElement BankName_CheckPaymentSection_On_PhoneOrderPage;

    @FindBy(id = "paymentNameOnCheck")
    private WebElement NameOnCheck_CheckPaymentSection_On_PhoneOrderPage;

    @FindBy(id = "paymentCheckNumber")
    private WebElement CheckNumber_CheckPaymentSection_On_PhoneOrderPage;

    @FindBy(id = "paymentCheckCashRegister")
    private WebElement CheckCashRegister_Dropdown_CheckPaymentSection_On_PhoneOrderPage;

    @FindBy(xpath = "//input[@id='paymentCheckDate']")
    private WebElement CheckDate_CheckPaymentSection_On_PhoneOrderPage;

    @FindBy(id = "paymentAccNumber")
    private WebElement CheckAccountNumber_CheckPaymentSection_On_PhoneOrderPage;

    @FindBy(id = "paymentRoutingNumber")
    private WebElement Check_RoutingNumber_CheckPaymentSection_On_PhoneOrderPage;

    // ================= Pickup section ============================

    @FindBy(id = "drpShopUser")
    private WebElement salesPersonDDOnPhoneOrderPage;

    @FindBy(xpath = "//li[@data-ordermode='pickup']")
    private WebElement pickupOnPhoneOrderPage;

    @FindBy(xpath = "(//a[@class='headerIconAnchor'])[10]")
    private WebElement pickupTypeOnPhoneOrderPage;

    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement SuccessToastMsg;

    @FindBy(xpath = "//div[@class='toast-title']")
    private WebElement toaster_message_title;

    @FindBy(xpath = "//div[@class='toast toast-success']")
    private WebElement successToast_draftorder;

    // ===================== Customer Section ==============================
    @FindBy(xpath = "//fieldset[contains(@class,'customerinfo')]")
    private WebElement custSectionOnPhoneOrderPage;

    @FindBy(xpath = "(//legend[@class='scheduler-border']//span[1])[1]")
    private WebElement custId_OnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='txtCutomerId'])[1]")
    private WebElement custIdTextbox_CustDetailsPopup_OnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='searchCustomer']")
    private WebElement searchCustomertextboxOnCustSection;

    @FindBy(xpath = "(//legend[@class='scheduler-border']//span)[1]")
    private WebElement newcustomerlabel;

    @FindBy(xpath = "//li[@class='ui-menu-item']//div")
    private List<WebElement> listOfCustomerNamesOnCustSection;

    @FindBy(xpath = "//ul[@id='ui-id-3']")
    private WebElement cust_unorderlistOnCustSection;

    @FindBy(xpath = "//input[@id='custFirstName']")
    private WebElement firstnameOnPhoneOrderPage;

    @FindBy(css = "input#custLastName")
    private WebElement lastnameOnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='custCompanyName'])[1]")
    private WebElement companyNameOnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='custEmail'])[1]")
    private WebElement emailIdOnPhoneOrderPage;

    @FindBy(xpath = "(//div[@class='pac-container pac-logo hdpi'])[2]")
    private WebElement address1_customersection_autosuggest_dropdown;

    @FindBy(xpath = "//div[@class='pac-container pac-logo hdpi']//div//span")
    private List<WebElement> listOfAddress1SuggestionsOnCustSection;

    @FindBy(xpath = "(//input[@id='custAddress1'])[1]")
    private WebElement address1OnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='custAddress2'])[1]")
    private WebElement address2OnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='custZipCode']")
    private WebElement zipcodeOnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='custCity'])[1]")
    private WebElement cityOnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='custState']")
    private WebElement stateOnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='custPhone']")
    private WebElement phoneNumOnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='custAltPhone'])[1]")
    private WebElement AltphoneNumOnPhoneOrderPage;

    /**
     * Customer clear button WebElements on Customer Section
     */
    @FindBy(xpath = "(//span[@class='glyphicon glyphicon-remove-circle iconSize clearInfo'])[1]")
    private WebElement clearCustInfoBtnOnPhoneOrderPage;

    /**
     * Edit customer button WebElements on Customer Section
     */
    @FindBy(xpath = "(//span[@class='glyphicon glyphicon-edit iconSize editCustomer'])[1]")
    private WebElement editCustInfoBtnOnPhoneOrderPage;

    @FindBy(xpath = "//ul[@id='ui-id-5']")
    private WebElement cust_FirstnameUnorderListOnCustSection;

    @FindBy(xpath = "//ul[@id='ui-id-4']//li//div")
    private List<WebElement> listOfFirstNamesOnCustSection;

    /**
     * Last Names autosuggestion WebElements on Customer Section
     */
    @FindBy(xpath = "//ul[@id='ui-id-6']")
    private WebElement cust_LastnameUnorderListOnCustSection;

    /**
     * List of Last Names autosuggestion WebElements on Customer Section
     */
    @FindBy(xpath = "//ul[@id='ui-id-6']//li//div")
    private List<WebElement> listOfLastNamesOnCustSection;

    @FindBy(css="li.ui-menu-item div")
    private WebElement customernameFromAutoSuggestion;

    @FindBy(xpath = "//ul[@id='ui-id-9']")
    private WebElement cust_companyNameUnorderListOnCustSection;

    @FindBy(xpath = "//ul[@id='ui-id-9']//li//div")
    private List<WebElement> listOfCompanyNamesOnCustSection;

    @FindBy(xpath = "//ul[@id='ui-id-7']")
    private WebElement cust_phoneNumberUnorderListOnCustSection;

    @FindBy(xpath = "//ul[@id='ui-id-7']//li//div")
    private List<WebElement> listOfPhoneNumbersOnCustSection;

    @FindBy(xpath = "//ul[@id='ui-id-8']")
    private WebElement cust_altphoneNumberUnorderListOnCustSection;

    @FindBy(xpath = "//ul[@id='ui-id-8']//li//div")
    private List<WebElement> listOfAltPhoneNumbersOnCustSection;

    @FindBy(xpath = "(//div[@id='CustomerDetailModal']//div[@class='modal-content ModalBodyHeight'])[1]")
    private WebElement customerDetailsPopup;

    @FindBy(css = "div[id='page-wrapper'] div[id='CustomerDetailModal'] div[class='modal-content ModalBodyHeight']")
    private WebElement custSectionCustomerDetailsPopup;

    @FindBy(xpath = "//a[@title='Customer Order History'] //span")
    private WebElement custHistoryIcon_OnCustDetailsSection;

    @FindBy(xpath = "//div[@id='florist-orderhistory-modal']//div[@class='modal-content']")
    private WebElement previous_order_history_popup_on_phoneorderpage;

    @FindBy(xpath = "//h4[contains(text(),'Previous Orders History')]")
    private WebElement previousOrdersHistoryHeaderPopup_OnCustDetailsSection;

    @FindBy(xpath = "//table[@id='tblCustomerOrderHistory']//tbody//tr//td[1]")
    private List<WebElement> ListOfPreviousOrdersInvoices_InOrderHistoryTable_CustHistoryPopup;

    @FindBy(xpath = "//label[@class='ChkBoxLbl PromotionsLbl']")
    private WebElement TextMepromotionsCheckBox_OnCustDetailsPopup;

    @FindBy(xpath = "(//label[normalize-space()='Allow SMS']/following::span[@class='switchery switchery-default'])[1]")
    private WebElement smstooglebutton_OnCustDetailsPopup;

    //================ Payment Section as Credit card ==========================
    @FindBy(xpath = "//select[@id='SelectCardFormOption']")
    private WebElement CreditCard_PaymentType_Dropdown_OnCustDetailsPopup;

    @FindBy(id = "cbDontSaveCC")
    private WebElement dontSaveCreditCardCheckbox_OnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='paymentFirstName']")
    private WebElement CreditCard_PaymentType_FirstName_OnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='paymentLastName']")
    private WebElement CreditCard_PaymentType_LastName_OnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='paymentBillZipCode']")
    private WebElement CreditCard_PaymentType_ZipCode_OnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='paymentBillCity']")
    private WebElement CreditCard_PaymentType_City_Dropdown_OnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='paymentBillState']")
    private WebElement CreditCard_PaymentType_State_OnCustDetailsPopup;

    @FindBy(xpath = "//select[@id='paymentBillCountry']")
    private WebElement CreditCard_PaymentType_Country_OnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='paymentCCNumber']")
    private WebElement CreditCard_PaymentType_CreditCardNumber_OnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='paymentCCNumber']/following::ul//li//a//strong")
    private WebElement existing_creditcard_number;

    @FindBy(xpath = "//input[@id='paymentCCExpDate']")
    private WebElement CreditCard_PaymentType_ExpDate_OnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='paymentCCV']")
    private WebElement CreditCard_PaymentType_CVV_OnCustDetailsPopup;


    // ============= Credit Card tab on Customer Details ===========================
    @FindBy(xpath = "(//li[@id='CustomerCardLi'])[1]")
    private WebElement creditCardTabOnCustDetailsPopup;

    @FindBy(xpath = "(//a[@id='btnAddNewCreditCard'])[1]")
    private WebElement addNewCreditCardBtnOnCustDetailsPopup;

    @FindBy(id = "btnAddNewCardGrid")
    private WebElement add_new_button_cc_oncustdetailspopup;

    @FindBy(xpath = "(//div[@id='bkCrditcardDetail']/child::div/child::div)[1]")
    private WebElement grid1_added_creditcardnum;

    @FindBy(xpath = "((//div[@id='bkCrditcardDetail']/child::div/child::div)[1]//div)[6]")
    private WebElement grid1_added_creditcard_number;

    @FindBy(xpath = "((//div[@id='bkCrditcardDetail']/child::div/child::div)[1]//div)[7]")
    private WebElement grid1_added_creditcard_expire_date;

    @FindBy(xpath = "//input[@id='txtCreditFirstName']")
    private WebElement creditCardTab_FirstName_OnCustDetailsPopup;

    @FindBy(id = "txtCreditLastName")
    private WebElement creditCardTab_LastName_OnCustDetailsPopup;

    @FindBy(xpath = "(//input[@id='txtCardNumber'])[1]")
    private WebElement creditCardTab_CreditCardNumber_OnCustDetailsPopup;

    @FindBy(xpath = "(//input[@id='txtExp'])[1]")
    private WebElement creditCardTab_ExpDate_OnCustDetailsPopup;

    @FindBy(xpath = "(//input[@id='txtCVV'])[1]")
    private WebElement creditCardTab_CVV_OnCustDetailsPopup;

    @FindBy(xpath = "(//input[@id='txtCardZipCode'])[1]")
    private WebElement creditCardTab_ZipCode_OnCustDetailsPopup;

    @FindBy(xpath = "(//a[@id='btnSaveCreditInfo'])[1]")
    private WebElement creditCardTab_SaveBtn_OnCustDetailsPopup;


    @FindBy(xpath = "//button[@class='close']//span//i")
    private WebElement creditCardTab_CloseIcon_OnCustDetailsPopup;

    @FindBy(xpath = "//h4[@id='UpdateCardModalTitle']")
    private WebElement AddNewInformationCreditCardOnCustDetailsPopup;

    // =========================Profile Tab customer details
    // popup===========================
    @FindBy(xpath = "//a[@id='CustomerProfileAnc']")
    private WebElement customerProfileTabOnCustDetailsPopup;

    @FindBy(xpath = "(//input[@id='txtCutomerId'])[1]")
    private WebElement customerIDOnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='txtCompanyName']")
    private WebElement companyNameOnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='txtAddress1']")
    private WebElement address1OnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='txtAddress2']")
    private WebElement address2OnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='txtZip']")
    private WebElement zipcodeOnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='txtCountry']")
    private WebElement countryOnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='txtCity']")
    private WebElement cityOnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='txtState']")
    private WebElement stateOnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='txtPhoneNumber']")
    private WebElement phoneNumOnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='txtAltPhone']")
    private WebElement altphoneNumOnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='txtEmail']")
    private WebElement emailIdOnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='txtFirstName']")
    private WebElement firstNameOnCustDetailsPopup;

    @FindBy(xpath = "//input[@id='txtLastName']")
    private WebElement lastNameOnCustDetailsPopup;

    @FindBy(xpath = "(//span[@class='switchery switchery-default'])[4]")
    private WebElement latesettingtooglebutton;

    @FindBy(xpath = "(//a[@id='btnUpdateCustomerDetails'])[1]")
    private WebElement updateCustomerDetailsBtnOnCustDetailsPopup;

    @FindBy(xpath = "(//div[@class='modal-header customer-modal-header']//a//i)[1]")
    private WebElement closeBtnOnCustDetailsPopup;

    // ========================Preferences tab customer details popup
    // ========================

    @FindBy(xpath = "(//a[@data-toggle='tab'][normalize-space()='Preferences'])[1]")
    private WebElement preferencesTabOnCustDetailsPopup;

    @FindBy(xpath = "(//select[@id='ddlDefaultTaxExempt'])[1]")
    private WebElement taxtype_preference_OnCustDetailsPopup;

    @FindBy(xpath = "(//input[@id='txtTaxId'])[1]")
    private WebElement taxid_preference_OnCustDetailsPopup;

    @FindBy(xpath = "(//input[@id='txtDefaultDiscount'])[1]")
    private WebElement discount_preference_OnCustDetailsPopup;

    @FindBy(xpath = "(//input[@id='txtDiscountAmount'])[1]")
    private WebElement discountamount_preference_OnCustDetailsPopup;

    @FindBy(xpath = "(//input[@id='txtDafultDeliveryFee'])[1]")
    private WebElement deliveryfee_preference_OnCustDetailsPopup;

    @FindBy(xpath = "//div[@id='page-wrapper']//textarea[@id='txtDefaultCardMsg']")
    private WebElement cardmessage_preference_OnCustDetailsPopup;

    @FindBy(xpath = "(//select[@id='ddlDefaultPaymentType'])[1]")
    private WebElement paymenttype_preference_OnCustDetailsPopup;

    @FindBy(xpath = "(//select[@id='ddlPaymentTerms'])[1]")
    private WebElement paymentterms_preference_OnCustDetailsPopup;

    @FindBy(xpath = "(//textarea[@id='txtDefaultSpecialInstrunction'])[1]")
    private WebElement specialinstruction_preference_OnCustDetailsPopup;

    @FindBy(css = "textarea#productSpInstructions")
    private WebElement special_instructions_on_phoneorderpage_productsection;

    @FindBy(xpath = "(//button[@id='btnUpdateCustomerDefaults'])[1]")
    private WebElement updatecustomerDefaultbtn_preference_OnCustDetailsPopup;

    @FindBy(xpath = "(//button[normalize-space()='Not Needed'])[1]")
    private WebElement confirmationalert_notneededbtn_preference_OnCustDetailsPopup;

    @FindBy(xpath = "(//div[@class='sweet-alert showSweetAlert visible'])[1]")
    private WebElement confirmationalert_preference_OnCustDetailsPopup;

    // ==========================Order Details Section ========================
    @FindBy(xpath = "//a[normalize-space()='View ShortCodes']")
    private WebElement viewShortcodesOnPhoneOrderPage;

    @FindBy(xpath = "(//textarea[@id='orderCardMsg'])[1]")
    private WebElement viewShortcodeTextboxOnPhoneOrderPage;

    @FindBy(xpath = "//ul[@id='textcomplete-dropdown-1']//li//a//div//span")
    private List<WebElement> listOfShortcodesOnPhoneOrderPage;

    @FindBy(xpath = "//span[@id='SectionSuggestions']//a//i")
    private WebElement cardmessage_suggestion_Icon;

    @FindBy(xpath = "//h4[contains(text(),'Not sure what to say? browse our suggestions below')]")
    private WebElement cardmessage_suggestion_popup;

    @FindBy(xpath = "//div[@id='SectionCardSuggestion']//div//div")
    private List<WebElement> cardmessage_suggestion_list;

    // ======================= Product Details Section ==============
    @FindBy(id = "orderItemDesc1")
    private WebElement prod_details_ItemDesc1;

    @FindBy(xpath = "//ul[@id='ui-id-30']")
    private WebElement itemDesc1_Autosuggestion;

    @FindBy(xpath = "//ul[@id='ui-id-30']//li//div")
    private List<WebElement> listOfItems_row1_UnderItemDesc1;

    @FindBy(xpath = "//ul[@id='ui-id-27']//li")
    private List<WebElement> itemcode_row1_Autosuggestion;

    @FindBy(xpath = "//td//input[@id='orderItem1']")
    private WebElement prod_details_Itemcode1;

    @FindBy(xpath = "//ul[@id='ui-id-16']")
    private WebElement row1_product_autosuggestion_listofoptions;

    //Xpath for the second shop for the product autosuggest
    @FindBy(xpath = "//ul[@id='ui-id-28']")
    private WebElement shop2_row1_product_autosuggestion_listofoptions;

    // xpath updated on jan 13th 2025....previous xpath will be //ul[@id='ui-id-15']//li//div
    @FindBy(xpath = "//ul[@id='ui-id-16']//li//div")
    private List<WebElement> listOfItemsUnderItemcode1;

    @FindBy(xpath = "//ul[@class='ui-menu ui-widget ui-widget-content ui-autocomplete ui-front']//li//div")
    private List<WebElement> listOfItems_UnderItemcode1;

    @FindBy(xpath = "//ul[@id='ui-id-17']")
    private WebElement row2_itemcode_textbox2_autosugesstions;

    // Last updated on Jan 13 2025 - previous xpath will be //ul[@id='ui-id-16']//li//div
    @FindBy(xpath = "//ul[@id='ui-id-17']//li//div")
    private List<WebElement> listOfItemsUnderItemcode2;

    @FindBy(xpath = "//td//textarea[@id='orderItemDesc1']")
    private WebElement prod_details_ItemDescription1;

    @FindBy(xpath = "//td//input[@id='orderItemQty1']")
    private WebElement prod_details_Qty1;

    @FindBy(xpath = "(//tbody[@class='productLines']//tr[1]//td[3]//input)")
    private WebElement prod_details_Quantity1;

    @FindBy(xpath = "(//tbody[@class='productLines']//tr[1]//td[4]//input)")
    private WebElement prod_details_Unitprice1;

    @FindBy(xpath = "(//tbody[@class='productLines']//tr[1]//td[5]//input)")
    private WebElement prod_details_Extendedprice1;

    @FindBy(xpath = "(//tbody[@class='productLines']//tr[1]//td[6]//input)")
    private WebElement prod_details_DiscountAmt1;

    @FindBy(xpath = "(//tbody[@class='productLines']//tr[1]//td[7]//input)")
    private WebElement prod_details_DiscountPercentage1;

    //========================== I icon product details section ===============================================
    @FindBy(xpath = "//table[@id='productGrid']//tbody[@class='productLines']//tr[1]//td[11]//a[2]//i")
    private WebElement prod_details_I_Icon;

    @FindBy(xpath = "//div[@id='ItemDetailsQuickView']//h4")
    private WebElement ItemDetail_Popup;

    @FindBy(xpath = "//div[@id='ItemDetailsQuickView']//div[@class='modal-content']")
    private WebElement ItemDetail_Popup_Content;

    @FindBy(xpath = "//div[@id='ItemDetailsQuickView']//div[@class='modal-content']//button[@type='button'][normalize-space()='×']")
    private WebElement ItemDetails_Popup_CloseIcon;

    @FindBy(xpath = "(//div[@id='ItemDetailsRecipeDetails']//p//span)[1]")
    private WebElement recipename_Itemdetailpopup;

    @FindBy(xpath = "(//div[@id='ItemDetailsRecipeDetails']//p//span)[2]")
    private WebElement recipe_totalcost_Itemdetailpopup;

    @FindBy(xpath = "(//div[@id='ItemDetailsRecipeDetails']//p//span)[3]")
    private WebElement recipe_totalLabour_Itemdetailpopup;

    @FindBy(xpath = "(//div[@id='ItemDetailsRecipeDetails']//p//span)[4]")
    private WebElement recipe_totalAmount_Itemdetailpopup;

    @FindBy(xpath = "((//table[@class='table table-striped'])[5]//tr//td)[1]")
    private WebElement componentname_itemrow1;

    @FindBy(xpath = "((//table[@class='table table-striped'])[5]//tr//td[1])[2]")
    private WebElement componentname_itemrow2;

    //=================================== End of I icon ====================================
    //=================================== Recipe flower icon product details Section ====================================
    @FindBy(xpath = "(//div[@class='totalpreview hana-totalreview-div']//table//td[2]//div//label)[1]")
    private WebElement recipe_total_details_section_RecipeCostTotal;

    @FindBy(xpath = "(//div[@class='totalpreview hana-totalreview-div']//table//td[2]//div//label)[2]")
    private WebElement recipe_total_details_section_RecipeRetailTotal;

    @FindBy(xpath = "(//div[@class='totalpreview hana-totalreview-div']//table//td[2]//div//label)[3]")
    private WebElement recipe_total_details_section_RecipeLabourCost;

    @FindBy(xpath = "(//div[@class='totalpreview hana-totalreview-div']//table//td[2]//div//label)[4]")
    private WebElement recipe_total_details_section_SuggestRetailPrice;

    @FindBy(xpath = "(//div[@class='totalpreview hana-totalreview-div']//table//td[2]//div//label)[5]")
    private WebElement recipe_total_details_section_InvoicePrice;

    @FindBy(xpath = "//input[@id='orderItem1']/following::td//a//div[@class='recipeIconWrap']")
    private WebElement prod_details_RecipeIcon1;

    @FindBy(xpath = "//div[@id='_addRecipe']//div[@class='modal-content']")
    private WebElement create_recipe_popup;

    @FindBy(xpath = "(//span[@class='hana-recipe-lbl'])[2]")
    private WebElement CreateRecipe_Popup;

    @FindBy(id = "drdItemComponentType")
    private WebElement Item_gallerydropdown_Popup;

    @FindBy(xpath = "//div[@id='itemComponentTypeFilterDataWrap']")
    private WebElement Item_gallerydropdown_OptionsPopup;

    @FindBy(xpath = "//div[@id='itemComponentTypeFilterDataWrap']//div//p")
    private List<WebElement> listOfItem_gallerydropdown_Popup;

    @FindBy(id = "searchByComponentType")
    private WebElement Item_gallerydropdown_SearchTextbox_Popup;

    @FindBy(xpath = "//input[@id='searchByComponentType']")
    private WebElement search_item_gallery_textbox_field;

    @FindBy(xpath = "//p[@class='componentRecipe']")
    private List<WebElement> list_of_items_componentname;

    @FindBy(id = "recipeAutoComplete")
    private WebElement Recipe_SearchTextbox_Popup;

    @FindBy(id = "markup_settings")
    private WebElement MarkupSettings_Icon;

    @FindBy(xpath = "//span[contains(text(),'Markup Settings')]")
    private WebElement MarkupSettings_Popup;

    @FindBy(xpath = "//label[@for='chkApplyMarkupPerFlag']/child::span")
    private WebElement ApplyMarkup_percentage_Togglebutton_Markupsettings_Popup;

    @FindBy(xpath = "(//span[@class='hana-recipe-lbl'])[4]/following::button[1]//i")
    private WebElement CloseButton_Markupsettings_Popup;

    @FindBy(xpath = "(//span[@class='hana-recipe-lbl'])[2]/following::button[1]//i")
    private WebElement CloseIcon_CreateRecipe_Popup;

    @FindBy(xpath = "//button[text()='Not Needed']")
    private WebElement NotNeeded_ButtonConfirmationAlert_CreateRecipe_Popup;
    //   //(//input[@class='form-control ComponentMarkupPer'])[1]
    @FindBy(xpath = "//td[@class='ComponentTypeName' and contains(text(),'Flowers')]/following::td[1]//input")
    private WebElement MarkupPercentage_Textbox_Field1;

    @FindBy(xpath = "//label[@for='chkApplyMarkupPerFlag']//input")
    private WebElement MarkupTogglebutton;

    @FindBy(id = "BtnUpdateComponent")
    private WebElement save_Button_Markupsettings_Popup;

    @FindBy(xpath = "//div[@id='gvRecipeItem']//table[@role='grid']//tr[1]//td[2]//input")
    private WebElement RecipeName_Row1;

    @FindBy(xpath = "//div[@id='gvRecipeItem']//table[@role='grid']//tr[1]//td[3]//label")
    private WebElement cost_label_table_grid_Row1;

    @FindBy(xpath = "//div[@id='gvRecipeItem']//table[@role='grid']//tr[1]//td[4]//input")
    private WebElement retail_textbox_table_grid_Row1;

    @FindBy(xpath = "//div[@id='gvRecipeItem']//table[@role='grid']//tr[1]//td[5]//input")
    private WebElement item_Quantity_textbox_table_grid_Row1;

    @FindBy(xpath = "//div[@id='gvRecipeItem']//table[@role='grid']//tr[1]//td[6]//label")
    private WebElement total_cost_label_table_grid_Row1;

    @FindBy(xpath = "//div[@id='gvRecipeItem']//table[@role='grid']//tr[1]//td[7]//label")
    private WebElement total_retail_label_table_grid_Row1;

    @FindBy(xpath = "//input[@id='txtItemName_1']")
    private WebElement ItemName_textbox_tablegrid_Row1;

    @FindBy(xpath = "//table[@role='grid']//tr[2]//td[2]//input")
    private WebElement RecipeName_Row2;

    @FindBy(xpath = "(//div[@id='gvRecipeItem']//table[@role='grid']//tr//td[2]//input)[last()]")
    private WebElement RecipeName_Row3;

    @FindBy(xpath = "(//div[@id='gvRecipeItem']//table[@role='grid']//tr//td[2]//input)")
    private List<WebElement> list_0f_recipe_name;

    @FindBy(xpath = "//table[@role='grid']//tr[3]//td[4]//input")
    private WebElement retail_Price_textbox_Row3;

    @FindBy(xpath = "(//div[@id='gvRecipeItem']//table[@role='grid']//tr//td[4]//input)[last()]")
    private WebElement retail_price_textbox_tablegrid_create_recipe_popup;

    @FindBy(xpath = "(//div[@id='gvRecipeItem']//table[@role='grid']//tr//td[5]//input)[last()]")
    private WebElement quantity_textbox_tablegrid_create_recipe_popup;

    @FindBy(xpath = "(//div[@id='gvRecipeItem']//table[@role='grid']//tr//td[6]//label)[last()]")
    private WebElement total_cost_label_tablegrid_create_recipe_popup;

    @FindBy(xpath = "(//div[@id='gvRecipeItem']//table[@role='grid']//tr//td[7]//label)[last()]")
    private WebElement total_retail_label_tablegrid_create_recipe_popup;

    @FindBy(xpath = "//table[@role='grid']//tr[3]//td[5]//input")
    private WebElement item_Quantity_Row3;

    @FindBy(xpath = "//ul[@id='ui-id-2']")
    private WebElement SearchRecipe_Autosuggestion;

    @FindBy(xpath = "//ul[@id='ui-id-2']//li//div")
    private List<WebElement> listOfRecipe_Autosuggestion;

    @FindBy(xpath = "//input[@id='BtnSaveProductDowns']")
    private WebElement SaveRecipie_button;

    @FindBy(xpath = "//button[@id='BtnSaveProductDown2']")
    private WebElement SaveButton_RecipePopup;

    @FindBy(xpath = "//a[@id='btnSaveRecipeSub']")
    private WebElement SaveButton_RecipeSaveAsPopup;

    @FindBy(xpath = "//a[@id='btnSaveRecipeSubAs']")
    private WebElement SaveAsButton_RecipeSaveAsPopup;

    @FindBy(xpath = "(//span[@class='hana-recipe-lbl'])[3]")
    private WebElement SaveAsRecipe_Popup;

    @FindBy(xpath = "//input[@id='saveAsRecipeName']")
    private WebElement SaveAsRecipeName_Textbox_Popup;

    @FindBy(id = "BtnSaveProductDown3")
    private WebElement saveButton_OnSaveAsRecipe_Popup;

    @FindBy(xpath = "//td//input[@id='orderItem2']")
    private WebElement prod_details_Itemcode2;

    @FindBy(xpath = "//td//textarea[@id='orderItemDesc2']")
    private WebElement prod_details_ItemDescription2;

    @FindBy(xpath = "(//tbody[@class='productLines']//tr[2]//td[3]//input)")
    private WebElement prod_details_Qty2;

    @FindBy(xpath = "(//tbody[@class='productLines']//tr[2]//td[4]//input)")
    private WebElement prod_details_Unitprice2;

    @FindBy(xpath = "(//tbody[@class='productLines']//tr[2]//td[5]//input)")
    private WebElement prod_details_Extendedprice2;

    @FindBy(id = "productGrid")
    private WebElement productDetailGrid;

    @FindBy(xpath = "//a[@id='orderItemRemoveIcon1']")
    private WebElement removeIconProdDetails1;

    @FindBy(xpath = "//textarea[@id='orderItemDesc2']")
    private WebElement prod_details_ItemDescription2OnPhoneOrderPage;

    @FindBy(xpath = "//ul[@id='ui-id-20']")
    private WebElement itemdescription_list_autosuggestion;

    @FindBy(xpath = "//ul[@id='ui-id-20']//li//div")
    private List<WebElement> listOfRow2_ItemDescription_OnPhoneOrderPage;

    @FindBy(xpath = "//a[@id='orderItemAddIcon3']")
    private WebElement addIconProdDetails3_OnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='orderItem4']")
    private WebElement prod_details_Itemcode4_OnPhoneOrderPage;

    @FindBy(xpath = "//table[@class='table productGrid']//tbody//tr")
    private List<WebElement> listOfRow_ProductDetails_OnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='orderItem2']")
    private WebElement prod_details_Itemcode2_OnPhoneOrderPage;

    @FindBy(xpath = "//textarea[@id='orderItemDesc2']")
    private WebElement prod_details_ItemDescription2_OnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='orderItemQty2']")
    private WebElement prod_details_ItemQty2_OnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='orderItemPrice2']")
    private WebElement prod_details_ItemPrice2_OnPhoneOrderPage;

    @FindBy(id = "orderItemTaxableCkhBox1")
    private WebElement item_row1_taxableCheckbox_OnProductDetailsSection;

    @FindBy(xpath = "//input[@id='Recipename']")
    private WebElement RecipeName_OnRecipePopup;

    // updated on dec 10 due to save to product changes
    @FindBy(css = "label[for='recipeSaveToProduct1'] span[class='slider round']")
    private WebElement savetoproduct_ToogleButton_OnCreateRecipePopup;

    // updated on dec 10 due to save to product changes
    @FindBy(css = "label[for='VisibleForCust'] span[class='slider round']")
    // label[for='VisibleForCust'] (//label[@class='control-label switch'])[1]
    private WebElement displayImages_ToogleButton_OnCreateRecipePopup;

    @FindBy(xpath = "//a[@class='btnClearLabour']")
    private WebElement clearLabourButton_OnCreateRecipePopup;

    @FindBy(xpath = "(//p[@class='hana-tbody-tr-ml'])[3]")
    private WebElement labourcost_label;

    @FindBy(xpath = "(//p[@class='hana-tbody-tr-ml'])[3]/following::td[1]//label")
    private WebElement labourCost_amount;

    @FindBy(xpath = "//button[@id='btnDelete_1']//i")
    private WebElement deleteIcon1_productTableGrid_OnCreateRecipePopup;

    @FindBy(xpath = "(//div[@id='gvRecipeItem']//table[@role='grid']//tr//td[9]//button)[last()]")
    private WebElement lastrow_deleteIcon_productTableGrid_OnCreateRecipePopup;

    @FindBy(id = "btnDelete_3")
    private WebElement deleteIcon3_productTableGrid_OnCreateRecipePopup;

    @FindBy(xpath = "(//ul[@Class='nav nav-tabs recipe-nav']//li//a)[1]")
    private WebElement privateNote_Tab_OnCreateRecipePopup;

    @FindBy(xpath = "(//div[@Class='tab-content hana-tabcontent-padding']//div)[1]//textarea")
    private WebElement privateNote_textbox_OnCreateRecipePopup;

    @FindBy(xpath = "(//ul[@Class='nav nav-tabs recipe-nav']//li//a)[2]")
    private WebElement publicNote_Tab_OnCreateRecipePopup;

    @FindBy(xpath = "(//div[@Class='tab-content hana-tabcontent-padding']//div)[2]//textarea")
    private WebElement publicNote_textbox_OnCreateRecipePopup;

    @FindBy(xpath = "(//ul[@Class='nav nav-tabs recipe-nav']//li//a)[3]")
    private WebElement photo_button_OnCreateRecipePopup;

    @FindBy(xpath = "(//div[@Class='tab-content hana-tabcontent-padding']//div)[3]//label")
    private WebElement addFile_button_OnCreateRecipePopup;

    @FindBy(xpath = "//img[@class='RecipeBgImage hana-RecipeBgImage']")
    private WebElement image1_PhotoTab_AddNotes_OnCreateRecipePopup;

    @FindBy(xpath = "(//a[@class='text-danger removeImageRecipe'])[1]")
    private WebElement deleteIcon_Image1_PhotoTab_AddNotes_OnCreateRecipePopup;

    @FindBy(xpath = "//input[@id='RecipeQty']")
    private WebElement recipeQty_Textbox_OnCreateRecipePopup;

    @FindBy(xpath = "(//div[contains(@class, 'create-recipe-div-boxes') and contains(@class, 'p-r-0')])[2]")
    private WebElement recipe_total_details_OnCreateRecipePopup;

    //================ Payment type as donation ========================

    @FindBy(id = "paymentEINNumber")
    private WebElement EINNumber_PhoneOrder_PaymentSection;


    // ===================== Payment type ==============================
    @FindBy(xpath = "//input[@id='paymentPayableBal']")
    private WebElement payableBalanceOnPhoneOrderPage;

    @FindBy(id = "paymentSubTotalTxt")
    private WebElement subTotalOnPhoneOrderPage;

    @FindBy(id = "paymentDiscAmtTxt")
    private WebElement discountAmt_PhoneOrder_PaymentSection;

    @FindBy(id = "paymentDelFeeTxt")
    private WebElement deliveryFee_PhoneOrder_PaymentSection;

    @FindBy(css = "#paymentOnlyRelayTxt")
    private WebElement relayFee_PhoneOrder_PaymentSection;

    @FindBy(id = "paymentTaxAmtTxt")
    private WebElement taxAmt_PhoneOrder_PaymentSection;

    @FindBy(xpath = "//input[@id='paymentGrandTotalTxt']")
    private WebElement grandTotalOnPhoneOrderPage;

    // ===================Payment Type as Gift Card =============================
    @FindBy(id = "paymentGiftCardNumber")
    private WebElement giftCardNumber_PhoneOrder_PaymentSection;

    @FindBy(id = "paymentGiftCustName")
    private WebElement giftCardCustName_PhoneOrder_PaymentSection;

    @FindBy(id = "paymentGiftAmt")
    private WebElement giftCardAmount_PhoneOrder_PaymentSection;

    @FindBy(id = "paymentGiftCardCurrentBalance")
    private WebElement giftCardCurrentBalance_PhoneOrder_PaymentSection;

    //====================== cash type ========================================
    @FindBy(xpath = "//input[@id='paymentPaidAmt']")
    private WebElement paidAmountOnPhoneOrderPage;

    @FindBy(xpath = "//select[@id='paymentCashCashRegister']")
    private WebElement cashRegisterDDOnPhoneOrderPage;

    @FindBy(xpath = "//button[@id='btnPlaceOrder']")
    private WebElement placeOrderButtonOnPhoneOrderPage;

    @FindBy(xpath = "//span[@id='paymentChangeGivenBack']")
    private WebElement change_GivenBack_atPayment_Section_OnPhoneOrderPage;

    // ======================= Recurring Order ================================
    @FindBy(xpath = "//input[@id='btnRecurringPopup']")
    private WebElement recurring_order_button_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='recurringInfoPopup']//div[@class='modal-content']")
    private WebElement recurring_info_Popup;

    @FindBy(xpath = "//label[@for='productRecurringChkBox']/following::span[1]")
    private WebElement recurring_toogle_button_onphoneorderpage;

    @FindBy(xpath = "//select[@id='recurMethodDrp']")
    private WebElement recurring_frequency_dropdown_onphoneorderpage;

    @FindBy(xpath = "//select[@id='recurMethodDrp']/optgroup[@label='Day based subscription']//option")
    private List<WebElement> list_of_day_based_subscriptions_on_recurring_freqency_dropdown;

    @FindBy(xpath = "//select[@id='recurMethodDrp']/optgroup[@label='Date based subscription']//option")
    private List<WebElement> list_of_date_based_subscriptions_on_recurring_freqency_dropdown;

    @FindBy(xpath = "//select[@id='recurMethodDrp']/optgroup[@label='Week based subscription']//option")
    private List<WebElement> list_of_week_based_subscriptions_on_recurring_freqency_dropdown;

    @FindBy(xpath = "//input[@id='frequencyDays']")
    private WebElement recurring_frequency_days_onphoneorderpage;

    @FindBy(xpath = "//input[@id='recurDate']")
    private WebElement next_recurring_date_onphoneorderpage;

    @FindBy(xpath = "//input[@id='recurEndDate']")
    private WebElement recurring_end_date_onphoneorderpage;

    @FindBy(xpath = "//div[@id='recurringInfoPopup']//div[@class='modal-content']//button[text()='Close']")
    private WebElement recurring_close_button_onphoneorderpage;

    @FindBy(xpath = "//span[@id='lblOrderRecurringInfo']")
    private WebElement recurring_info_onphoneorderpage;


    // =================Payments as Invoice/House Account ==================
    @FindBy(xpath = "(//input[@id='paymentPONumber'])[1]")
    private WebElement ponumber_paymenttype_InvHouseAct_OnPhoneOrderPage;

    @FindBy(xpath = "//input[@id='paymentPaymentTerm']")
    private WebElement PaymentTerms_paymenttype_InvHouseAct_OnPhoneOrderPage;

    // ====================== Confirmation popup ==============================

    @FindBy(xpath = "//div[@id='orderCfmModal']//div[@class='modal-content']")
    private WebElement order_confirmation_Popup_On_PhoneOrderPage;

    @FindBy(xpath = "//h4[contains(text(),'Order Confirmation')]")
    private WebElement confirmationPopupTitleOnPhoneOrderPage;

    @FindBy(xpath = "(//div[@class='col-md-6 col-sm-6 text-right'])[4]")
    private WebElement taxamount_OrderConfirmationPopupOnPhoneOrderPage;

    @FindBy(xpath = "(//button[@class='btn btn-default pull-right'])[1]")
    private WebElement cancelButtonOnPhoneOrderPage;

    @FindBy(xpath = "(//button[normalize-space()='Submit Order'])[1]")
    private WebElement submitOrderButtonOnPhoneOrderPage;

    // ============== Instruction textbox =================
    @FindBy(xpath = "//textarea[@id='productSpInstructions']")
    private WebElement Splinstruction_Textbox_OnPhoneOrderPage;

    @FindBy(xpath = "//textarea[@id='productDriverInstruct']")
    private WebElement Driverinstruction_Textbox_OnPhoneOrderPage;

    @FindBy(xpath = "(//textarea[@id='txtCustomerNotes'])[1]")
    private WebElement customerPrivateNotes_Textbox_OnPhoneOrderPage;

    @FindBy(id = "productTaxType")
    private WebElement producttaxType_OnPhoneOrderPage;

    @FindBy(id = "productTaxId")
    private WebElement producttaxId_OnPhoneOrderPage;

    @FindBy(xpath = "//select[@id='productReferral']")
    private WebElement productSourceCode_OnPhoneOrderPage;

    @FindBy(xpath = "//select[@id='customerType']")
    private WebElement productcustomerType_OnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='txtPONumber'])[1]")
    private WebElement ponumber_preference_OnCustDetailsPopup;

    @FindBy(xpath = "(//input[@id='paymentPONumber'])[1]")
    private WebElement ponumber_paymenttype_OnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='paymentPaymentTerm'])[1]")
    private WebElement paymentterm_paymenttype_OnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='paymentDelFeeTxt'])[1]")
    private WebElement deliveryfee_paymenttype_OnPhoneOrderPage;

    // =================Unpaid Invoice tab customer details popup ===============
    @FindBy(xpath = "(//a[@id='CustomerUnpaidAnc'])[1]")
    private WebElement customerDetailsPopup_Unpaid_TabOnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='gvCustomerUnpaidInvoices']//span[@class='k-icon k-i-file-excel']")
    private WebElement exportToExcel_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage;

    @FindBy(xpath = "(//iframe[@id='ifrmAccountStatementReport'])[1]")
    private WebElement generated_Statement;

    @FindBy(xpath = "(//div[@id='divFrame'])[1]")
    private WebElement frame_webelement;

    @FindBy(xpath = "(//div[@class='k-pager-wrap k-grid-pager k-widget k-floatwrap'])[1]")
    private WebElement pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='gvCustomerUnpaidInvoices']//div[@class='k-pager-wrap k-grid-pager k-widget k-floatwrap']")
    private WebElement pagination_UnpaidTabOn_CustomerDetailsPopup_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='gvCustomerUnpaidInvoices']//a[@title='Go to the first page']//span")
    private WebElement firstpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage;

    By first_page_pagination_unpaid_tab_on_customerdetails_popup = By.xpath("(//a[@title='Go to the first page'])[1]//span");

    @FindBy(xpath = "//div[@id='gvCustomerUnpaidInvoices']//a[@title='Go to the first page']//span")
    private WebElement firstpage_Pagination_UnpaidTabOn_CustomerDetailsPopup_OnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='gvCustomerOrderDtls']//a[@title='Go to the first page']//span")
    private WebElement firstpageicon_on_orderdetailstab_customerdetailspopup;

    @FindBy(xpath = "//div[@id='gvCustomerPaymentDtls']//a[@title='Go to the first page']//span")
    private WebElement firstpageicon_paymentdetailstab_customerdetailspopup;

    @FindBy(xpath = "(//a[@title='Go to the previous page'])[1]//span")
    private WebElement previouspage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage;

    By previouspage = By.xpath("//div[@id='gvCustomerPaymentDtls']//a[@title='Go to the previous page']");

    By previouspageorderdetails = By.xpath("//div[@id='gvCustomerOrderDtls']//a[@title='Go to the previous page']");

    By previous_page_pagination_unpaid_tab_IconLocator = By.xpath("(//a[@title='Go to the previous page'])[1]//span");

    @FindBy(xpath = "(//a[@title='Go to the next page'])[1]//span")
    private WebElement nextpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage;

    By nextpage_paymentdetails = By.xpath("//div[@id='gvCustomerPaymentDtls']//a[@title='Go to the next page']");

    By nextpage_orderdetails = By.xpath("//div[@id='gvCustomerOrderDtls']//a[@title='Go to the next page']");


    @FindBy(xpath = "(//a[@title='Go to the last page'])[1]//span")
    private WebElement lastpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage;

    By lastpage_pagination_unpaid_tab_customer_details_popup = By.xpath("//div[@id='gvCustomerUnpaidInvoices']//a[@title='Go to the last page'][1]//span");

    By lastpageicon_paymentdetailstab_customerdetailspopup = By.xpath("//div[@id='gvCustomerPaymentDtls']//a[@title='Go to the last page'][1]//span");

    By lastpage_pagination_icon_orderdetailsTabOn_CustomerDetailsPopupOnPhoneOrderPage = By.xpath("//div[@id='gvCustomerOrderDtls']//a[@title='Go to the last page'][1]//span");

    @FindBy(xpath = "(//span[@class='k-state-selected'])[1]")
    private WebElement selectedpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage;

    @FindBy(xpath = "//span[@class='k-input']")
    private WebElement pagelist_DisplayedNumber_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage;

    @FindBy(xpath = "//span[@class='k-select']")
    private WebElement pagelistdropdown_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='gvCustomerUnpaidInvoices']//span[@class='k-select']")
    private WebElement unpaidinvoices_tab_pagelistdropdown_customer_details_popup_unpaid;

    @FindBy(xpath = "//div[@id='gvCustomerOrderDtls']//a[@title='Go to the last page']//span/following::span[1]//span[2]")
    private WebElement pagelist_orderdetailsTabOn_CustomerDetailsPopupOnPhoneOrderPage;

    @FindBy(css = "div[class='k-list-container k-popup k-group k-reset k-state-border-down'] li:nth-child(2)")
    private WebElement pagelist_dropdownvalues_orderdetailsTabOn_CustomerDetailsPopupOnPhoneOrderPage;

    @FindBy(xpath = "//div[@id='gvCustomerOrderDtls']//span[@class='k-select']")
    private WebElement order_details_tab_pagelistdropdown_customer_details_popup;

    @FindBy(xpath = "//div[@class='k-animation-container']//ul[@class='k-list k-reset']//li")
    private List<WebElement> pagelist_dropdownvalues_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage;

    @FindBy(xpath = "//span[@class='k-pager-sizes k-label']")
    private WebElement itemsperpage_Label_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage;

    @FindBy(xpath = "//a[@class='k-pager-refresh k-link']//span")
    private WebElement refreshButton_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage;

    @FindBy(xpath = "//span[@class='k-pager-info k-label']")
    private WebElement pagenumber_Label_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage;

    By pagenumber_label_customer_details_popup = By.xpath("//span[@class='k-pager-info k-label']");

    @FindBy(xpath = "//div[@id='full-view-modal-body']")
    private WebElement InvoiceDetailsPopup_Unpaid_TabOnPhoneOrderPage;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr[1]//td[2]")
    private WebElement row1_InvoiceNo_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "//div[@class='modal-close-icon']//a//i")
    private WebElement closeIcon_InvoiceDetailsPopup_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//button[@id='btnMakePayment'])[1]")
    private WebElement MakePaymentButton_InvoiceDetailsPopup_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr[1]//td[1]/input")
    private WebElement row1_Checkbox_InvoiceDetailsPopup_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//div[@id='unpaidinvoices-confirm'])[1]")
    private WebElement invoicepaymentdetailssection_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//select[@id='ddlUnPaidInvoicePaymentType'])[1]")
    private WebElement paymenttype_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//select[@id='unpaidinv-cashregister'])[1]")
    private WebElement cashregister_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//span[@id='lblCreditOnFileTotalDue'])[1]")
    private WebElement totaldueamt_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='unpaidinv-paymentamount'])[1]")
    private WebElement paymentamount_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage;

    @FindBy(css = "div[id='page-wrapper'] button[class='btn btn-cancel']")
    private WebElement cancelButton_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//button[@class='btn btn-primary unpaidSubmitMakePayment'])[1]")
    private WebElement submitButton_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "((//div[@id='UnpaidInvoiceConfirmDiv'])[1]/child::div//div//div/child::span)[1]")
    private WebElement paymentSuccessMessage_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//select[@id='paymentconfirm-cashregister'])[1]")
    private WebElement cashregister_ConfirmationSection_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//select[@id='paymentconfirm-cashregister'])[1]/following::button[1]")
    private WebElement printReceiptButton_ConfirmationSection_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='paymentconfirmemail'])[1]/following::button[1]")
    private WebElement emailReceiptButton_ConfirmationSection_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//input[@id='paymentconfirmemail'])[1]")
    private WebElement emailtextbox_ConfirmationSection_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//button[@id='btn-unpaid-back'])[1]")
    private WebElement backButton_ConfirmationSection_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//div[@id='gvCustomerUnpaidInvoices'])[1]")
    private WebElement unpaidInvoiceTable_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//input[contains(@class,'k-textbox k-FullWidth')])[1]")
    private WebElement CustomerDetailsPopup_Invoicesearchtextbox_UnpaidTab;

    @FindBy(xpath = "(//td[@class='hana-cust-grid-row-fullview kendo-hana-invoice-column'])[1]")
    private WebElement InvoiceNumber_Row1_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//td[@class='hana-cust-grid-row-fullview kendo-hana-invoice-column'])")
    private List<WebElement> ListOfInvoiceNumber_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "//div[@class='k-grid-norecords']")
    private WebElement NoUnpaidInvoiceFound_InvoiceTable_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//button[@class='k-button k-button-icon'])[1]")
    private WebElement InvoiceClearbutton_Invoicesearchtextbox_UnpaidTab;

    @FindBy(xpath = "(//input[@class='k-textbox k-FullWidth'])[2]")
    private WebElement PaidAmtSearchTextbox_InvoiceTable_UnpaidTab_OnPhoneOrderPage;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr//td[3]")
    private List<WebElement> listOfPaidAmt__InvoiceTable_UnpaidTab_OnPhoneOrderPage;

    @FindBy(xpath = "(//button[@class='k-button k-button-icon'])[2]")
    private WebElement InvoiceClearbutton_PaidAmtsearchtextbox_UnpaidTab;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr[last()]//td[1]/input")
    private WebElement lastRow_Checkbox_InvoiceTable_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "//span[@data-field='Balance_amt']//input")
    private WebElement CustomerDetailsPopup_BalanceAmtsearchtextbox_UnpaidTab;

    @FindBy(xpath = "//span[@data-field='Balance_amt']//button")
    private WebElement InvoiceClearbutton_BalanceAmtsearchtextbox_UnpaidTab;

    @FindBy(xpath = "(//input[contains(@class,'k-textbox k-FullWidth')])[4]")
    private WebElement CustomerDetailsPopup_ProductTotalsearchtextbox_UnpaidTab;

    @FindBy(xpath = "(//button[@class='k-button k-button-icon'])[4]")
    private WebElement InvoiceClearbutton_ProductTotalsearchtextbox_UnpaidTab;

    @FindBy(xpath = "(//td[@class='hana-cust-grid-row-fullview hana-product-column'])")
    private List<WebElement> ListOfProductsTotal_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//input[contains(@class,'k-textbox k-FullWidth')])[5]")
    private WebElement CustomerDetailsPopup_Deliverysearchtextbox_UnpaidTab;

    @FindBy(xpath = "(//button[@class='k-button k-button-icon'])[5]")
    private WebElement InvoiceClearbutton_Deliverysearchtextbox_UnpaidTab;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr//td[6]")
    private List<WebElement> ListOfDelivery_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//input[contains(@class,'k-textbox k-FullWidth')])[6]")
    private WebElement CustomerDetailsPopup_Taxsearchtextbox_UnpaidTab;

    @FindBy(xpath = "(//button[@class='k-button k-button-icon'])[6]")
    private WebElement InvoiceClearbutton_Taxsearchtextbox_UnpaidTab;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr//td[7]")
    private List<WebElement> ListOfTax_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//input[contains(@class,'k-textbox k-FullWidth')])[8]")
    private WebElement CustomerDetailsPopup_Totalsearchtextbox_UnpaidTab;

    @FindBy(xpath = "(//button[@class='k-button k-button-icon'])[8]")
    private WebElement InvoiceClearbutton_Totalsearchtextbox_UnpaidTab;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr//td[9]")
    private List<WebElement> ListOfTotal_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//input[contains(@class,'k-textbox k-FullWidth')])[9]")
    private WebElement CustomerDetailsPopup_OrderDatesearchtextbox_UnpaidTab;

    @FindBy(xpath = "(//button[@class='k-button k-button-icon'])[9]")
    private WebElement InvoiceClearbutton_OrderDatesearchtextbox_UnpaidTab;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr//td[10]")
    private List<WebElement> ListOfOrderDate_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//input[contains(@class,'k-textbox k-FullWidth')])[10]")
    private WebElement CustomerDetailsPopup_DeliveryDatesearchtextbox_UnpaidTab;

    @FindBy(xpath = "(//button[@class='k-button k-button-icon'])[10]")
    private WebElement InvoiceClearbutton_DeliveryDatesearchtextbox_UnpaidTab;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr//td[11]")
    private List<WebElement> ListOfDeliveryDate_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage;

    @FindBy(xpath = "(//a[@class='k-link'])[1]")
    private WebElement InvoiceLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup;

    @FindBy(xpath = "(//span[@class='k-icon k-i-sort-asc-sm'])[1]")
    private WebElement Invoice_uparrow_UnpaidInvoiceTab_OnCustomerDetailsPopup;

    @FindBy(xpath = "//span[@class='k-icon k-i-sort-desc-sm']")
    private WebElement Invoice_downarrow_UnpaidInvoiceTab_OnCustomerDetailsPopup;

    @FindBy(xpath = "(//a[@class='k-link'])[2]")
    private WebElement PaidAmtLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup;

    @FindBy(xpath = "(//td[@class='hana-cust-grid-row-fullview'])[1]")
    private List<WebElement> ListOfPaidAmt_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup;

    @FindBy(xpath = "(//a[@class='k-link'])[3]")
    private WebElement BalanceAmtLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup;

    @FindBy(xpath = "(//td[@class='hana-cust-grid-row-fullview'])[2]")
    private List<WebElement> ListOfBalanceAmt_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup;

    @FindBy(xpath = "(//a[@class='k-link'])[4]")
    private WebElement ProductTotalLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup;

    @FindBy(xpath = "(//a[@class='k-link'])[5]")
    private WebElement DeliveryLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr//td[6]")
    private List<WebElement> ListOfDelivery_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup;

    @FindBy(xpath = "(//a[@class='k-link'])[6]")
    private WebElement TaxLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr//td[7]")
    private List<WebElement> ListOfTax_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup;

    @FindBy(xpath = "(//a[@class='k-link'])[7]")
    private WebElement LatefeeLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr//td[8]")
    private List<WebElement> ListOflatefee_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup;

    @FindBy(xpath = "(//a[@class='k-link'])[8]")
    private WebElement TotalLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr//td[9]")
    private List<WebElement> ListOfTotallabel_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup;

    @FindBy(xpath = "(//a[@class='k-link'])[9]")
    private WebElement OrderDateLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr//td[10]")
    private List<WebElement> ListOfOrderDate_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup;

    @FindBy(xpath = "(//a[@class='k-link'])[10]")
    private WebElement DeliveryDateLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr//td[11]")
    private List<WebElement> ListOfDeliveryDate_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup;

    //=============================Payment details======================

    @FindBy(xpath = "(//a[normalize-space()='Payment Detail(s)'])[1]")
    private WebElement PaymentDetailsTab;

    @FindBy(xpath = "(//div[@id='gvCustomerPaymentDtls'])[1]")
    private WebElement PaymentDetailsTabTable;

    @FindBy(xpath = "(//div[@id='gvCustomerPaymentDtls']//div//a)[1]")
    private WebElement exportExcel_PaymentDetailsTab;

    @FindBy(xpath = "(//table[@role='grid'])[3]//tbody//tr[1]//td[1]")
    private WebElement tablerow1cell1_PaymentDetailsTab;

    @FindBy(xpath = "(//thead[@class='k-grid-header']//tr[2]//th//span//input)[1]")
    private WebElement InvoiceSearchTextBox_PaymentDetailsTab;

    @FindBy(xpath = "(//table[@role='grid'])[3]//tbody//tr//td[1]")
    private List<WebElement> ListOfInvoice_PaymentDetailsTab;

    @FindBy(xpath = "//div[contains(text(),'No Customer Payments Found.')]")
    private WebElement NoCustomerFound_PaymentDetailsTab;

    @FindBy(xpath = "(//thead[@class='k-grid-header']//tr[2]//th//span//button)[1]")
    private WebElement clearbutton_InvoiceSearchTextBox_PaymentDetailsTab;

    @FindBy(xpath = "(//thead[@class='k-grid-header']//tr[2]//th//span//input)[2]")
    private WebElement payAmountSearchBox_PaymentDetailsTab;

    @FindBy(xpath = "//div[@id='gvCustomerPaymentDtls']//table//td[2]")
    private List<WebElement> ListOfPayAmount_PaymentDetailsTab;

    By list_of_pay_amount_payment_details_tab = By.xpath("//div[@id='gvCustomerPaymentDtls']//table//td[2]");

    @FindBy(xpath = "(//thead[@class='k-grid-header']//tr[2]//th//span//button)[2]")
    private WebElement clearbutton_PayAmountSearchBox_PaymentDetailsTab;

    @FindBy(xpath = "(//thead[@class='k-grid-header']//tr[2]//th//span//input)[3]")
    private WebElement payDateSearchBox_PaymentDetailsTab;

    By paydateSearchBox_PaymentDetailsTab = By.xpath("(//span[@data-field='PayDate']//input)[1]");

    @FindBy(xpath = "(//table[@role='grid'])//tbody[@role='rowgroup']//tr//td[3]")
    private List<WebElement> ListOfPayDate_PaymentDetailsTab;

    @FindBy(xpath = "(//thead[@class='k-grid-header']//tr[2]//th//span//button)[3]")
    private WebElement clearbutton_PayDateSearchBox_PaymentDetailsTab;

    @FindBy(xpath = "(//span[@data-field='PayDate'])//span[@class='k-dropdown-wrap k-state-default']")
    private WebElement filterIconOnPayDate_PaymentDetailsTab;

    @FindBy(xpath = "(//div[@class='k-animation-container']//ul[@class='k-list k-reset']//li)")
    private List<WebElement> ListOfFilterOptionsOnPayDate_PaymentDetailsTab;

    @FindBy(xpath = "(//span[@role='button'])[1]")
    private WebElement calendarIconOnPayDate_PaymentDetailsTab;

    @FindBy(xpath = "//a[@class='k-link k-nav-fast']")
    private WebElement calendar_MonthYear_PayDate_PaymentDetailsTab;

    @FindBy(xpath = "//table[@class='k-content k-month']//td[not(contains(@class,'k-other-month'))]//a")
    private List<WebElement> ListOfdates_PayDate_PaymentDetailsTab;

    @FindBy(xpath = "(//thead[@class='k-grid-header']//tr[2]//th//span//input)[5]")
    private WebElement MOPSearchBox_PaymentDetailsTab;

    @FindBy(xpath = "(//thead[@class='k-grid-header']//tr[2]//th//span//button)[4]")
    private WebElement clearbutton_MOPSearchBox_PaymentDetailsTab;

    @FindBy(xpath = "(//table[@role='grid'])//tbody[@role='rowgroup']//tr//td[4]")
    private List<WebElement> ListOfMOP_PaymentDetailsTab;

    @FindBy(xpath = "//span[@data-field='CheckNo']//input")
    private WebElement ChecknoSearchBox_PaymentDetailsTab;

    @FindBy(xpath = "(//table[@role='grid'])//tbody[@role='rowgroup']//tr//td[5]")
    private List<WebElement> ListOfCheckno_PaymentDetailsTab;

    @FindBy(xpath = "(//thead[@class='k-grid-header']//tr[2]//th//span//button)[5]")
    private WebElement clearbutton_ChecknoSearchBox_PaymentDetailsTab;

    @FindBy(xpath = "(//thead[@class='k-grid-header']//tr[2]//th//span//input)[7]")
    private WebElement CCNumberSearchBox_PaymentDetailsTab;

    @FindBy(xpath = "(//table[@role='grid'])//tbody[@role='rowgroup']//tr//td[6]")
    private List<WebElement> ListOfCCNumber_PaymentDetailsTab;

    @FindBy(xpath = "(//thead[@class='k-grid-header']//tr[2]//th//span//button)[6]")
    private WebElement clearbutton_CCNumberSearchBox_PaymentDetailsTab;

    @FindBy(xpath = "//div[@id='gvCustomerPaymentDtls']//div[@class='k-pager-wrap k-grid-pager k-widget k-floatwrap']")
    private WebElement paginationSection_PaymentDetailsTab;

    @FindBy(xpath = "//div[@id='gvCustomerOrderDtls']//div[@class='k-pager-wrap k-grid-pager k-widget k-floatwrap']")
    private WebElement paginationSection_OrderDetailsTab;

    @FindBy(xpath = "(//ul[@class='k-pager-numbers k-reset'])")
    private WebElement pagelistnumber_PaymentDetailsTab;

    @FindBy(xpath = "//span[@class='k-pager-info k-label']")
    private WebElement paginationInfo_PaymentDetailsTab;

    @FindBy(xpath = "(//a[@class='k-link'])[16]")
    private WebElement threedotsOnPagination_PaymentDetailsTab;

    @FindBy(xpath = "(//ul[@class='k-pager-numbers k-reset']//li/a)[2]")
    private WebElement page2_PaymentDetailsTab;

    @FindBy(xpath = "//div[@id='gvCustomerPaymentDtls']//a[normalize-space()='Invoice']")
    private WebElement invoicelabel_paymentdetailsTab;

    @FindBy(xpath = "(//thead[@class='k-grid-header']//tr//th//a)[2]")
    private WebElement payamountlabel_paymentdetailsTab;

    @FindBy(xpath = "(//thead[@class='k-grid-header']//tr//th//a)[3]")
    private WebElement payDatelabel_paymentdetailsTab;

    @FindBy(xpath = "(//thead[@class='k-grid-header']//tr//th//a)[4]")
    private WebElement moplabel_paymentdetailsTab;

    @FindBy(xpath = "(//thead[@class='k-grid-header']//tr//th//a)[5]")
    private WebElement checknolabel_paymentdetailsTab;

    @FindBy(xpath = "(//thead[@class='k-grid-header']//tr//th//a)[6]")
    private WebElement ccnumberlabel_paymentdetailsTab;

    //=============================Order Details Tab=======================================
    @FindBy(xpath = "//legend[text()='Order Details']")
    private WebElement OrderDetailsSection;

    @FindBy(xpath = "//label[@class='ChkBoxLbl reminderLbl']")
    private WebElement sendreminderlabel_OrderDetailsTab;

    @FindBy(xpath = "//a[text()='View ShortCodes']")
    private WebElement ViewShortCodesLink_OrderDetailsSection;

    @FindBy(xpath = "//div[@class='shortCodeListDiv']//table//tbody//tr//td[1]")
    private List<WebElement> ListOfShortCodes_OrderDetailsSection;

    @FindBy(xpath = "//div[@class='shortCodeListDiv']//table//tbody//tr[last()]//td[1]")
    private WebElement LastShortCode_OrderDetailsSection;

    @FindBy(xpath = "//div[@class='shortCodeListDiv']//table//tbody//tr//td[2]")
    private List<WebElement> ListOfValues_OrderDetailsSection;

    @FindBy(xpath = "//div[@class='shortCodeListDiv']//table//tbody//tr[last()]//td[2]")
    private WebElement LastValues_OrderDetailsSection;

    @FindBy(xpath = "//div[@class='shortCodeListDiv']//table//tbody//tr//td[3]")
    private List<WebElement> ListOfdeleteIcon_OrderDetailsSection;

    @FindBy(xpath = "//div[@class='shortCodeListDiv']//table//tbody//tr[last()]//td[3]//a")
    private WebElement LastdeleteIcon_OrderDetailsSection;

    @FindBy(xpath = "//div[@class='sweet-alert showSweetAlert visible']")
    private WebElement ConfirmationPopup;

    @FindBy(xpath = "//h2[text()='Confirmation']")
    private WebElement ConfirmationAlert_ShortCodesPopup_OrderDetailsSection;

    @FindBy(xpath = "//div[@class='sa-icon sa-info']")
    private WebElement ConfirmationAlertIcon_ShortCodesPopup_OrderDetailsSection;

    @FindBy(xpath = "//button[@class='cancel']")
    private WebElement ConfirmationAlertCancelButton_ShortCodesPopup_OrderDetailsSection;

    @FindBy(xpath = "//p[text()='Are you sure you want to delete this code?']")
    private WebElement ConfirmationAlertMessage_ShortCodesPopup_OrderDetailsSection;

    @FindBy(xpath = "//button[@class='confirm']")
    private WebElement YesButtonDeleteConfirmation_ShortCodesPopup_OrderDetailsSection;

    @FindBy(xpath = "//button[@class='cancel']")
    private WebElement NoButtonDeleteConfirmation_ShortCodesPopup_OrderDetailsSection;

    @FindBy(xpath = "(//div[@class='modal-content']//i)[1]")
    private WebElement closeIcon_ShortCodesPopup_OrderDetailsSection;

    @FindBy(xpath = "//h4[text()='Short Codes']")
    private WebElement ShortCodes_Popup_OrderDetailsSection;

    @FindBy(xpath = "//input[@id='txtNewShortCode']")
    private WebElement codeTextBox_ShortCodesPopup_OrderDetailsSection;

    @FindBy(xpath = "//div[@class='shortCodeListDiv']/child::table//tbody//tr//td[1]")
    private List<WebElement> ListOfCodes_ShortCodesPopup_OrderDetailsSection;

    @FindBy(xpath = "//input[@id='txtNewShortDesc']")
    private WebElement valueTextBox_ShortCodesPopup_OrderDetailsSection;

    @FindBy(xpath = "//input[@id='btnAddNewShortCode']")
    private WebElement addButton_ShortCodesPopup_OrderDetailsSection;

    @FindBy(xpath = "(//a[normalize-space()='Order Detail(s)'])[1]")
    private WebElement OrderDetailsTab;

    @FindBy(xpath = "(//div[@id='gvCustomerOrderDtls']//div//a)[1]")
    private WebElement exporttoexcelbutton_orderdetailsTab;

    @FindBy(xpath = "(//div[@id='gvCustomerOrderDtls'])[1]")
    private WebElement OrderDetailsTable;

    @FindBy(xpath = "(//thead[@role='rowgroup']//tr[2]//th//span//input)[1]")
    private WebElement InvoiceNoSearchBox_OrderDetailsTab;

    @FindBy(xpath = "(//div[@id='gvCustomerOrderDtls']//table//tr[2]//input[1]/following-sibling::button)[1]")
    private WebElement clear_button_InvoiceNoSearchBox_OrderDetailsTab;

    @FindBy(xpath = "(//td[@class='hana-cust-grid-row-fullview kendo-hana-invoice-column gvCustomerOrderDtls-set-invoice-font'])[1]")
    private WebElement InvoiceNo_row1_OrderDetailsTab;

    @FindBy(xpath = "//table[@role='grid']//tbody//tr//td[contains(@class,' gvCustomerOrderDtls')]")
    private List<WebElement> ListOfInvoiceNo_OrderDetailsTab;

    @FindBy(xpath = "//table[@role='grid']//tbody//tr//td[1]")
    private WebElement FirstInvoiceNo_OrderDetailsTab;

    @FindBy(xpath = "(//thead[@role='rowgroup']//tr[2]//th//span//button)[1]")
    private WebElement clearbutton_InvoiceNoSearchBox_OrderDetailsTab;

    @FindBy(xpath = "//div[contains(text(),'No Customer Orders Found.')]")
    private WebElement NoCustomerOrderFoundMessage_OrderDetailsTab;

    @FindBy(xpath = "//span[@data-field='TOTALAMOUNT']//input")
    private WebElement TotalAmountSearchBox_OrderDetailsTab;

    @FindBy(xpath = "//div[@id='gvCustomerOrderDtls']//table//tbody//tr[1]//td[5]")
    private WebElement FirstRow_TotalAmount_OrderDetailsTab;

    @FindBy(xpath = "//div[@id='gvCustomerOrderDtls']//table[@role='grid']//tbody//tr//td[5]")
    private List<WebElement> TotalAmountList_OrderDetailsTab;

    @FindBy(xpath = "(//thead[@role='rowgroup']//tr[2]//th//span//button)[5]")
    private WebElement clearbutton_TotalAmountSearchBox_OrderDetailsTab;

    @FindBy(xpath = "//div[@id='gvCustomerOrderDtls']//table//tr[2]//th[2]//input")
    private WebElement StatusSearchBox_OrderDetailsTab;

    @FindBy(xpath = "//div[@id='gvCustomerOrderDtls']//table[@role='grid']//tbody//tr//td[2]")
    private List<WebElement> ListOfStatus_OrderDetailsTab;

    @FindBy(xpath = "(//thead[@role='rowgroup']//tr[2]//th//span//button)[2]")
    private WebElement clearbutton_StatusSearchBox_OrderDetailsTab;

    @FindBy(xpath = "(//thead[@role='rowgroup']//tr[2]//th//span//input)[3]")
    private WebElement NameSearchBox_OrderDetailsTab;

    @FindBy(xpath = "//table[@role='grid']//tbody//tr//td[3]")
    private List<WebElement> ListOfName_OrderDetailsTab;

    @FindBy(xpath = "(//thead[@role='rowgroup']//tr[2]//th//span//button)[3]")
    private WebElement clearbutton_NameSearchBox_OrderDetailsTab;

    @FindBy(xpath = "(//thead[@role='rowgroup']//tr[2]//th//span//input)[4]")
    private WebElement AddressSearchBox_OrderDetailsTab;

    @FindBy(xpath = "//table[@role='grid']//tbody//tr//td[4]")
    private List<WebElement> ListOfAddress_OrderDetailsTab;

    @FindBy(xpath = "(//thead[@role='rowgroup']//tr[2]//th//span//button)[4]")
    private WebElement clearbutton_AddressSearchBox_OrderDetailsTab;

    @FindBy(xpath = "//span[@data-field='ORDER_ENTRY_DATETIMENEW']//input[@class='k-textbox k-FullWidth']")
    private WebElement OrderDateSearchBox_OrderDetailsTab;

    @FindBy(xpath = "//table[@role='grid']//tbody//tr//td[6]")
    private List<WebElement> ListOfOrderDate_OrderDetailsTab;

    @FindBy(xpath = "(//thead[@role='rowgroup']//tr[2]//th//span//button)[6]")
    private WebElement clearbutton_OrderDateSearchBox_OrderDetailsTab;

    @FindBy(xpath = "(//thead[@role='rowgroup']//tr[2]//th//span//input)[7]")
    private WebElement DescriptionSearchBox_OrderDetailsTab;

    @FindBy(xpath = "//div[@id='gvCustomerOrderDtls']//table[@role='grid']//tbody//tr//td[7]")
    private List<WebElement> ListOfDescription_OrderDetailsTab;

    @FindBy(xpath = "(//thead[@role='rowgroup']//tr[2]//th//span//button)[7]")
    private WebElement clearbutton_DescriptionSearchBox_OrderDetailsTab;

    //=========================Statements Tab ==========================
    @FindBy(xpath = "(//a[contains(text(),'Statement(s)')])[1]")
    private WebElement StatementsTab;

    @FindBy(xpath = "(//select[@id='ddlMonthYear'])[1]")
    private WebElement monthdropdown_statementsTab;

    @FindBy(xpath = "(//button[@id='btnDownloadStatement'])[1]")
    private WebElement downloadstatementbutton_statementsTab;

    @FindBy(xpath = "//div[@id='sizer']")
    private WebElement iframereport_statementsTab;

    @FindBy(xpath = "(//h2[@id='lblCustAccStmntMsg'])[1]")
    private WebElement NoStatementsFoundMessage_statementsTab;

    @FindBy(xpath = "//ul[@id='menuIcons']//li//span[@data-original-title='View Drafts']")
    private WebElement view_draft_icon;

    @FindBy(xpath = "//li[@data-ordermode='saveAsDrafts']/a/span")
    private WebElement save_as_draft_icon;

    @FindBy(xpath = "//div[@id='florist-draftorder-modal']//div[@class='modal-content']")
    private WebElement draft_orders_popup;

    @FindBy(xpath = "(//h4[contains(text(),'Draft Orders')])[1]")
    private WebElement draft_orders_popup_header;

    @FindBy(xpath = "//table[@class='table table-striped table-hover table-responsive']//tbody//tr[1]")
    private WebElement draft_orders_row1_on_tablegrid;

    @FindBy(xpath = "//table[@class='table table-striped table-hover table-responsive']//tbody//tr[1]//td[11]")
    private WebElement draft_order_row1_delete_icon_tablegrid;

    @FindBy(xpath = "//div[@class='sweet-alert showSweetAlert visible']")
    private WebElement confirmation_Alert_on_draft_orders_popup;

    // ========================= Phone Order page functions ======================================================//

    /**
     * Clicks the default value icon on the phone order page
     *
     * @Author Balaji N
     */
    public void ClickDefaultValuesIcon() {
        js_Click(defaultvaluesicon, "Default Values Icon on phone order page");
        wait_For_Page_To_Be_Stable(getDriver());
    }

    /**
     * Clicks the update button on the choose page default values popup on the phone order page
     *
     * @Author Balaji N
     */
    public void Click_UpdateBtn_ChoosePageDefaultValues_Popup() {
        js_Click(updateBtn_on_chooseDefaultValue, "Update button on choose page default values popup in phone order page");
    }

    /**
     * It return the Title of order entry page
     *
     * @return it return the title of phone order page
     * @Author Balaji N
     */
    public String GetOrderEntryPageTitle() {
        return getDriver().getTitle();
    }

    public boolean VerifyChoosePageDefaultValuesPopup() {
        return is_Element_Displayed(choosepagedefaultvaluespopup, "choose page default values popup");
    }

    public String getItemCodeOnCPDV() {
        return getElementAttribute(itemcodeonCPDV, "Item code on choose page default values popup in the phone order page");
    }

    public String getQtyOnCPDV() {
        return getElementAttribute(qtyonCPDV, "Qty on choose page default values popup in the phone order page");
    }

    public String getOccasionOnCPDV() {
        return get_Selected_Option(selectoccasionOnCPDV, "Occasion dropdown field on choose page default values popup in the phone order page");
    }

    /**
     * Select the occasion on the choose page default values popup on the phone order page
     *
     * @param occasion If the occasion is not provided it will select the default value
     * @Author Balaji N
     */
    public void Select_DefaultOccasion_On_ChoosePageDefaultValues(String occasion) {
        drop_Down(selectoccasionOnCPDV, occasion, "VisibleText", "Occasion dropdown field on choose page default values popup in the phone order page");
    }

    public String getcustomerTypeOnCPDV() {
        return get_Selected_Option(selectcustomertypeOnCPDV, "Customer Type drop down field on phoneorder page choose default values");
    }

    /**
     * Clicks the close icon on the choose page default values popup on the phone order page
     *
     * @Author Balaji N
     */
    public void ClickCloseIconOnChoosePageDefaultValuesPopup() {
        js_Click(CPDV_Popup_CloseIcon, "Close icon on choose page default values popup in the phone order page");
        delayWithGivenTime(500);
    }

    /**
     * Selects the occasion from drop down field on order details section in the phone order page
     *
     * @param occasion the visible text of the occasion to be selected from the dropdown field.
     * @Description: This function using the reusable method from testbaseclass dropDown function perform to select the occasion on order details section in the phone order page
     * @Author: Balaji N
     */
    public void SelectOccasion_On_OrderDetails_In_PhoneOrderPage(String occasion) {
        drop_Down(occasionOnphoneorderpage, occasion, "VisibleText", "Select occasion dropdown field on order details section in the phone order page");
    }

    /**
     * It retrieves the selected occasion from a dropdown field on order details section in the phone order page.
     *
     * @return It returns the selected occasion from dropdown field on order details section otherwise it returns default values
     * @Description: This function will retrieves selected occasion drop down field on order details section in the phone order page
     * @Author: Balaji N
     */
    public String getSelectedOccasionOnPhoneOrderPage() {
        return get_Selected_Option(occasionOnphoneorderpage, "Select Occasion dropdown field on order details section in the phone order page");
    }

    public String getItemcodeOnPhoneOrderPage() {
        return getElementAttribute(itemcodeOnphoneorderpage, "Item code textbox field value on phone order page");
    }

    public String getItemqtyOnPhoneOrderPage() {
        return getElementAttribute(qtyOnphoneorderpage, "Item Quantity on order details section in the phone order page");
    }

    public void select_Customer_Type_On_Product_Details_Section_PhoneOrderPage(String custType) {
        drop_Down(custTypeOnphoneorderpage, custType, "VisibleText", "Customer Type dropdown fields on product details section in the phone order page");
    }

    public String getSelectedCustTypeOnPhoneOrderPage() {
        return get_Selected_Option(custTypeOnphoneorderpage, "Customer Type on order details section in the phone order page");
    }

    /**
     * Retrieves the value of the 'First Name' field in the customer section on the phone order page.
     *
     * @return The entered value of the 'First Name' field in the customer section, otherwise it
     * returns an empty string if no value is entered
     * @Description - This function highlights the 'First Name' field and returns the value entered in the field.
     * @Author Balaji N
     */
    public String getFirstnameOnPhoneOrderPage() {
        return getElementAttribute(firstnameOnPhoneOrderPage, "First Name textbox field Value on customer section");
    }

    /**
     * Entering the first name on customer section on phone order page
     *
     * @param firstname the first name to be entered in the textbox field.
     * @Description: This method double-clicks on the 'First Name' field and enters the provided value.
     * @author Balaji N
     */
    public void EnterCustomerFirstName(String firstname) {
        Double_Click_And_Type(firstnameOnPhoneOrderPage, firstname, "First Name textbox field on customer section");
    }

    /**
     * Verifies that the 'First Name' field in the customer section on the phone order page is disabled or not
     *
     * @return If first name field on customer section is enabled it returns true else returns false
     */
    public boolean VerifyFnameField_On_CustSection_IsDisabled() {
        return isElementEnabled(firstnameOnPhoneOrderPage, "First Name textbox field on customer section in the phone order page");
    }

    /**
     * Retrieves the last name field entered text
     *
     * @return If text is exists it return the displayed last name text as string, otherwise it return empty string if no string is entered
     * @Description - This function highlights the last name field and retrieve & return the last name displayed text as string
     * @Author: Balaji N
     */
    public String getLastnameOnPhoneOrderPage() {
        return getElementAttribute(lastnameOnPhoneOrderPage, "Last Name field value on customer section");
    }

    /**
     * Entering the last name on customer section on phone order page
     *
     * @param lastname the last name to be entered in the textbox field.
     * @Description: This method double-clicks on the 'Last Name' field and enters the provided value.
     * @author Balaji N
     */
    public void EnterCustomerLastName(String lastname) {
        Double_Click_And_Type(lastnameOnPhoneOrderPage, lastname, "Last Name textbox field on customer section");
    }

    /**
     * Verifies the last name field on customer section on phone order page is disabled or not
     *
     * @return If last name field on customer section is enabled it returns true else returns false
     * @Author Balaji N
     */
    public boolean VerifyLnameField_On_CustSection_IsDisabled() {
        return isElementEnabled(lastnameOnPhoneOrderPage, "Last Name textbox field on customer section in the phone order page");
        /*HighlightElement(lastnameOnPhoneOrderPage);
        return lastnameOnPhoneOrderPage.isEnabled();*/
    }

    /**
     * Retrieves the company name field entered text from customer section on the phone order page
     *
     * @return If text is exists it returns displayed company name as text, otherwise it returns empty string
     * @Description: This function will highlight the company name field and retrieves the entered company name text as string
     * @Author: Balaji N
     */
    public String getCompanyNameOnPhoneOrderPage() {
        return getElementAttribute(companyNameOnPhoneOrderPage, "Company Name textbox field value on customer section");
    }

    /**
     * Verifies the company name field on customer section on phone order page is disabled or not
     *
     * @return If company name field on customer section is enabled it returns true else returns false
     * @Author Balaji N
     */
    public boolean VerifyCompanyNameField_On_CustSection_IsDisabled() {
        /*  HighlightElement(companyNameOn*//*PhoneOrderPage);
        return companyNameOnPhoneOrderPage.isEnabled();*/
        return isElementEnabled(companyNameOnPhoneOrderPage, "Company Name textbox field on customer section in the phone order page");
    }

    /**
     * Enters the company name on customer section on phone order page
     *
     * @param custcompanyname - company name to be entered
     * @Author Balaji N
     */
    public void EnterCustomerCompanyName(String custcompanyname) {
        Double_Click_And_Type(companyNameOnPhoneOrderPage, custcompanyname, "Company Name textbox field on customer section in the phone order page");
    }

    /**
     * Retrieves the email field entered text from customer section on the phone order page
     *
     * @return If text is exists it returns displayed the email as text, otherwise it returns empty string if no text entered on that field
     * @Description: This function will highlight the email field and retrieves the entered email text as string
     * @Author: Balaji N
     */
    public String getEmailIdOnPhoneOrderPage() {
        return getElementAttribute(emailIdOnPhoneOrderPage, "Email Id textbox field value on customer section");
        /*HighlightElement(emailIdOnPhoneOrderPage);
        return emailIdOnPhoneOrderPage.getAttribute("value");*/
    }

    /**
     * verify whether the email id field on customer section is disabled or not
     *
     * @return If the email id field is disabled it returns false otherwise returns true
     * @Author Balaji N
     */
    public boolean VerifyEmailIdField_On_CustSection_IsDisabled() {
        return isElementEnabled(emailIdOnPhoneOrderPage, "Email Id textbox field on customer section in the phone order page");
    }

    /**
     * Enter the email id on customer section on phone order page
     *
     * @param custemailid - email id to be entered
     * @Author Balaji N
     */
    public void EnterCustomerEmailId(String custemailid) {
        emailIdOnPhoneOrderPage.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(emailIdOnPhoneOrderPage, custemailid, "Email Id textbox field on customer section in the phone order page");
    }

    /**
     * Retrieves the address 1 field entered text from customer section on the phone order page
     *
     * @return If text is exists it returns the displayed address 1 field as text, otherwise it returns empty string if no text entered
     * @Description: This function will highlight the address 1 field and retrieves the entered address 1 text as string
     * @Author: Balaji N
     */
    public String getAddress1OnPhoneOrderPage() {
        return getElementAttribute(address1OnPhoneOrderPage, "Address 1 textbox field value on customer section"); //get
    }

    /**
     * Verifies the address 1 field on customer section on phone order page is disabled or not
     *
     * @return If the address 1 field on customer section is enabled it returns true else returns false
     * @Author Balaji N
     */
    public boolean VerifyAddress1Field_On_CustSection_IsDisabled() {
      /*  HighlightElement(address1OnPhoneOrderPage);
        return address1OnPhoneOrderPage.isEnabled();*/
        return isElementEnabled(address1OnPhoneOrderPage, "Address 1 textbox field on customer section in the phone order page");
    }

    /**
     * Enters the address 1 on customer section on phone order page
     *
     * @param custaddress1 - address 1 to be entered
     * @Author Balaji N
     */
    public void EnterAddress1(String custaddress1) {
        address1OnPhoneOrderPage.clear();
        delayWithGivenTime(500);
        ClickAndType(address1OnPhoneOrderPage, custaddress1, "Address 1 field on customer section in the phone order page");
    }

    /**
     * Searches and selects the address 1 field on customer section on phone order page
     *
     * @param custaddress1 - customer address 1 to be searched
     * @param fulladdress  - full address to be selected
     * @Author: Balaji N
     */
    public void SearchAndSelect_Address1_CustSection(String custaddress1, String fulladdress) {
        for (WebElement CustomerAddress1 : listOfAddress1SuggestionsOnCustSection) {
            fluentWait(listOfAddress1SuggestionsOnCustSection.get(0));
            Mouse_Hover(this, listOfAddress1SuggestionsOnCustSection.get(0), "Address 1 Search textbox field autosugesstion on customer section in the phone order page");
            fluentWait(listOfAddress1SuggestionsOnCustSection.get(0));
            Click(listOfAddress1SuggestionsOnCustSection.get(0), "Address 1 Search textbox field on customer section in the phone order page");
            break;
        }
    }
  /*  public void SearchAndSelect_Address1_CustSection(String custaddress1, String fulladdress) {
        int retries = 3;
        boolean isSelected = false;

        By suggestionListLocator = By.xpath("//div[@class='pac-container pac-logo hdpi']//div//span");

        while (retries > 0 && !isSelected) {
            try {
                List<WebElement> suggestions = getDriver().findElements(suggestionListLocator);

                if (suggestions.isEmpty()) {
                    String noSuggestions = "❌ No address suggestions displayed for customer address: '" + custaddress1 + "'";
                    System.err.println(noSuggestions);
                    Allure.step(noSuggestions);
                    break;
                }

                for (WebElement suggestion : suggestions) {
                    try {
                        fluentWait(suggestion);
                        Mouse_Hover(suggestion, "Address 1 suggestion");

                        String text = suggestion.getText().trim();
                        if (text.contains(fulladdress)) {
                            Click(suggestion, "Matching address suggestion: " + text);
                            String success = "✅ Selected matching address: '" + text + "'";
                            System.out.println(success);
                            Allure.step(success);
                            isSelected = true;
                            break;
                        }
                    } catch (StaleElementReferenceException e) {
                        // Inner loop stale, break to retry outer loop
                        throw e;
                    }
                }

            } catch (StaleElementReferenceException | TimeoutException e) {
                String retryMsg = String.format("⚠️ Retry %d: Failed due to [%s]. Retrying...", (4 - retries), e.getClass().getSimpleName());
                System.err.println(retryMsg);
                Allure.step(retryMsg);
                delayWithGivenTime(1000);
                retries--;
            } catch (Exception e) {
                String fatal = "❌ Unexpected error while selecting address: " + e.getMessage();
                System.err.println(fatal);
                Allure.step(fatal);
                throw new RuntimeException(fatal, e);
            }
        }

        if (!isSelected) {
            String finalFail = "❌ Failed to select address: '" + fulladdress + "' after 3 retries.";
            System.err.println(finalFail);
            Allure.step(finalFail);
            throw new RuntimeException(finalFail);
        }
    }*/


    /**
     * Search and select with city state country on customer section in the phone order page
     *
     * @param cityStateCountry
     */
    public void searchAndSelect_Address1_CustSection(String cityStateCountry) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
            WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='pac-container pac-logo hdpi']//div[contains(@class, 'pac-item')]//span[text()='" + cityStateCountry + "']")));
            boolean addressFound = false;
            String fullText = suggestion.getText();
            if (fullText.equalsIgnoreCase(cityStateCountry)) {
                delayWithGivenTime(2000);
                click(suggestion, "Auto-suggested address 1 field in customer section");
                addressFound = true;
            }

            if (!addressFound) {
                throw new RuntimeException("No matching address found in suggestions.");
            }

        } catch (
                Exception e) {
            throw new RuntimeException("Unable to search and select address 1 on customer section in the phone order page: " + e.getMessage(), e);
        }
    }


    /**
     * Search and select the customer address 1 on customer section in the phone order page
     *
     * @param citystatecountry The city, state, and country part of the address
     * @param fulladdress      The full address to match and select
     * @Author Balaji N
     */
    public void Search_And_Select_Customer_Address1(String citystatecountry, String fulladdress) {
        Highlight_Element(address1_customersection_autosuggest_dropdown, "Address 1 textbox field autosuggest dropdown on customer section in the phone order page");
        List<WebElement> addressOptions = getDriver().findElements(By.xpath("//div[@class='pac-container pac-logo hdpi']//div"));

        List<WebElement> street_Address = getDriver().findElements(By.xpath("//div[@class='pac-container pac-logo hdpi']//div/child::span[2]"));
        List<WebElement> city_State_Country = getDriver().findElements(By.xpath("//div[@class='pac-container pac-logo hdpi']//div/child::span[3]"));

        for (int i = 0; i < Math.min(street_Address.size(), city_State_Country.size()); i++) {
            String streetAddress = street_Address.get(i).getText().trim();
            String cityStateCountry = city_State_Country.get(i).getText().trim();

            String extractedFullAddress = streetAddress + " " + cityStateCountry;

            if (extractedFullAddress.equalsIgnoreCase(fulladdress) && cityStateCountry.equalsIgnoreCase(citystatecountry)) {
                Highlight_Element(city_State_Country.get(i), "Matching city-state-country in autosuggest dropdown");
                Actions action = new Actions(getDriver());
                action.moveToElement(city_State_Country.get(i)).click().perform();
                break;
            }
        }
    }


    /**
     * Retrieves the address 2 field entered text from customer section on the phone order page
     *
     * @return If text is exists it returns the displayed address 2 as text, otherwise it returns empty string if no text entered
     * @Description: This function will highlight the address 2 field and retrieves the entered address 2 text as string
     * @Author: Balaji N
     */
    public String getAddress2OnPhoneOrderPage() {
        return getElementAttribute(address2OnPhoneOrderPage, "Address 2 textbox field value on customer section");
      /*  HighlightElement(address2OnPhoneOrderPage);
        return address2OnPhoneOrderPage.getAttribute("value");*/
    }

    public boolean VerifyAddress2Field_On_CustSection_IsDisabled() {
        HighlightElement(address2OnPhoneOrderPage);
        return address2OnPhoneOrderPage.isEnabled();
    }

    /**
     * Retrieves the zipcode field entered text from customer section on the phone order page
     *
     * @return If text is exists it returns the displayed zipcode as text, otherwise it returns empty string if no text entered
     * @Description: This function will highlight the zipcode field and retrieves the entered zipcode text as string
     * @Author: Balaji N
     */
    public String getZipCodeOnPhoneOrderPage() {
        return getElementAttribute(zipcodeOnPhoneOrderPage, "Zipcode textbox field value on customer section");
       /* HighlightElement(zipcodeOnPhoneOrderPage);
        return zipcodeOnPhoneOrderPage.getAttribute("value");*/
    }

    public void EnterZipCode(String custzipcode) {
        zipcodeOnPhoneOrderPage.clear();
        delayWithGivenTime(1000);
        js_Clear_And_Type(zipcodeOnPhoneOrderPage, custzipcode, "Zipcode textbox field on customer section in the phone order page");
    }

    /**
     * Verifies the zipcode field on customer section on phone order page is disabled or not
     *
     * @return If the zipcode field on customer section is enabled it returns true else returns false
     * @Author Balaji N
     */
    public boolean VerifyZipcodeField_On_CustSection_IsDisabled() {
        return isElementEnabled(zipcodeOnPhoneOrderPage, "Zipcode textbox field on customer section in the phone order page");
    }

    /**
     * Retrieves the city field entered text from customer section on the phone order page
     *
     * @return If text is exists it returns the displayed city as text, otherwise it returns empty string if no text entered
     * @Description: This function will highlight the city field and retrieves the entered city text as string
     * @Author: Balaji N
     */
    public String getCityOnPhoneOrderPage() {
        return getElementAttribute(cityOnPhoneOrderPage, "City textbox field value on customer section");
      /*  HighlightElement(cityOnPhoneOrderPage);
        return cityOnPhoneOrderPage.getAttribute("value");*/
    }

    /**
     * Verifies the city field on customer section on phone order page is disabled or not
     *
     * @return If the city field on customer section is enabled it returns true else returns false
     * @Author Balaji N
     */
    public boolean VerifyCityField_On_CustSection_IsDisabled() {
       /* HighlightElement(cityOnPhoneOrderPage);
        return cityOnPhoneOrderPage.isEnabled();*/
        return isElementEnabled(cityOnPhoneOrderPage, "City textbox field on customer section in the phone order page");
    }

    /**
     * Retrieves the state field entered text from customer section on the phone order page
     *
     * @return If text is exists it returns the displayed state field as text, otherwise it returns empty string if no text entered
     * @Description: This function will highlight the state field and retrieves the entered state text as string
     * @Author: Balaji N
     */
    public String getStateOnPhoneOrderPage() {
        return get_element_attribute(stateOnPhoneOrderPage, "State textbox field value on customer section in the phone order page");
      /*  HighlightElement(stateOnPhoneOrderPage);
        return stateOnPhoneOrderPage.getAttribute("value");*/
    }

    public boolean VerifyStateField_On_CustSection_IsDisabled() {
        HighlightElement(stateOnPhoneOrderPage);
        return stateOnPhoneOrderPage.isEnabled();
    }

    /**
     * Retrieves the phone number 1 field entered text from customer section on the phone order page
     *
     * @return If text is exists it returns the displayed phone number 1 field as text, otherwise it returns empty string if no text entered
     * @Description: This function will highlight the phone number 1 field and retrieves the entered phone number 1 text as string
     * @Author: Balaji N
     */
    public String getPhoneNumberOnPhoneOrderPage() {
        return getElementAttribute(phoneNumOnPhoneOrderPage, "Phone Number 1 textbox field value on customer section");
        /*HighlightElement(phoneNumOnPhoneOrderPage);
        return phoneNumOnPhoneOrderPage.getAttribute("value");*/
    }

    /**
     * Verifies the phone number 1 field on customer section on phone order page is disabled or not
     *
     * @return If the phone number 1 field on customer section is enabled it returns true else returns false
     * @Author: Balaji N
     */
    public boolean VerifyPhonenumberField_On_CustSection_IsDisabled() {
     /*   HighlightElement(phoneNumOnPhoneOrderPage);
        return phoneNumOnPhoneOrderPage.isEnabled();*/
        return isElementEnabled(phoneNumOnPhoneOrderPage, "Phone number textbox field (enabled or not) on customer section in the phone order page");
    }

    /**
     * Entered the phone number 1 field on customer section in the phone order page
     *
     * @param custphonenumber phone number 1 to be entered
     * @Author Balaji N
     */
    public void EnterPhoneNumber(String custphonenumber) {
        phoneNumOnPhoneOrderPage.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(phoneNumOnPhoneOrderPage, custphonenumber, "Phone Number 1 field on customer section in the phone order page");
    }

    /**
     * Enter the alternative phone number on customer section on phone order page
     *
     * @param custphonenumber alternative phone number to be entered
     * @Author Balaji N
     */
    public void EnterAltPhoneNumber(String custphonenumber) {
        Double_Click_And_Type(AltphoneNumOnPhoneOrderPage, custphonenumber, "Alternative Phone Number field on customer section in the phone order page");
    }

    /**
     * Retrieves the Alternative phone number field entered text from customer section on the phone order page
     *
     * @return If text is exists it returns the displayed alternative phone number as text, otherwise it returns empty string if no text entered
     * @Description: This function will highlight the alternative phone number field and retrieves the entered alternative phone number text as string
     * @Author: Balaji N
     */
    public String getAltPhoneNumberOnPhoneOrderPage() {
        return getElementAttribute(AltphoneNumOnPhoneOrderPage, "Alternative Phone Number textbox field value on customer section");
       /* HighlightElement(AltphoneNumOnPhoneOrderPage);
        return AltphoneNumOnPhoneOrderPage.getAttribute("value");*/
    }

    /**
     * Verifies the alternative phone number field on customer section on phone order page is disabled or not
     *
     * @return If the alternative phone number field on customer section is enabled it returns true else returns false
     * @Author: Balaji N
     */
    public boolean VerifyAltPhonenumberField_On_CustSection_IsDisabled() {
       /* HighlightElement(AltphoneNumOnPhoneOrderPage);
        return AltphoneNumOnPhoneOrderPage.isEnabled();*/
        return isElementEnabled(AltphoneNumOnPhoneOrderPage, "Alternative Phone Number textbox field (enabled or not) on customer section in the phone order page");
    }

    public void Enter_ItemCodeRow1_Product(String itemcode) {
        clickAndType(prod_details_Itemcode1, itemcode);
    }

    public boolean is_Autosuggestion_Dropdown_Not_Displayed_for_ItemCode(String itemcode) {
        try {
            prod_details_Itemcode1.clear();
            Double_Click_And_Type(prod_details_Itemcode1, itemcode, "Item code textbox field - Cash and Carry Page");
            delayWithGivenTime(3000);
            return row1_product_autosuggestion_listofoptions.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public boolean Verify_ItemCodeRow1_ProductSection_Autosuggestion_Appears() {
        //HighlightElement(itemcode_row1_Autosuggestion); //
        boolean isAutosuggestionDisplayed = itemcode_row1_Autosuggestion.size() > 0;
        return isAutosuggestionDisplayed;
    }


    public void SearchandSelect_ItemDescription_Row1OnPhoneOrderPage(String proditemDesc, String itemdesc) {
        DoubleClickAndType(prod_details_ItemDesc1, proditemDesc);
        delayWithGivenTime(3000);
        for (WebElement itemdescription : listOfItems_row1_UnderItemDesc1) {
            if (itemdescription.getText().contains(itemdesc)) {
                itemdescription.click();
                //click(itemdescription);
                break;
            }
        }
    }

    public void SearchandSelect_Itemcode_OnPhoneOrderPage(String proditemcode, String itemdescription) {
        DoubleClickAndType(prod_details_Itemcode1, proditemcode);
        delayWithGivenTime(2000);
        for (int i = 0; i < listOfItems_UnderItemcode1.size(); i++) {
            if (listOfItems_UnderItemcode1.get(i).getText().contains(itemdescription)) {
                listOfItems_UnderItemcode1.get(i).sendKeys(Keys.ARROW_DOWN);
                delayWithGivenTime(3000);
                listOfItems_UnderItemcode1.get(i).sendKeys(Keys.ENTER);
                break;
            }
        }

    }


    public void SearchandSelect_ItemcodeOnPhoneOrderPage(String proditemcode, String itemdescription) {
        DoubleClickAndType(prod_details_Itemcode1, proditemcode);
        delayWithGivenTime(2000);
        for (int i = 0; i < listOfItems_UnderItemcode1.size(); i++) {
            if (listOfItems_UnderItemcode1.get(i).getText().contains(itemdescription)) {
                MouseHover(listOfItems_UnderItemcode1.get(i));
                delayWithGivenTime(1000);
                listOfItems_UnderItemcode1.get(i).click();
                break;
            }
        }

    }

    /**
     * Search with item code and Select the expected product item on product details section
     *
     * @param proditemcode    Search with the item code on row1 product details section
     * @param itemdescription Select the expected item from the autosuggest dropdown field
     * @Description: This function double click to select the existing data if exist, and then enter the item code to search & select the expected product from auto suggestion dropdown field row 1 on product details section
     * @Author: Balaji N
     */
/*    public void SearchandSelectItemcodeOnPhoneOrderPage(String proditemcode, String itemdescription) {
        try {
            fluentWait(prod_details_Itemcode1, 20, 3);
            prod_details_Itemcode1.clear();
            Double_Click_And_Type(prod_details_Itemcode1, proditemcode, "Item Code textbox field on product section in the Phone Order Page");
            delayWithGivenTime(3000);

            boolean itemFound = false;
            if (isElementDisplayed(row1_product_autosuggestion_listofoptions, "Item Code autosuggestion list on product details section in the phone order page")) {
                try {
                    WebElement item = getDriver().findElement(By.xpath("//ul[@id='ui-id-16']//li//div[contains(text(),'" + itemdescription + "')]"));
                    js_Click(item, "Item Code autosuggestion list on product details section");
                    itemFound = true;
                } catch (NoSuchElementException e) {
                    System.err.println("Item with description " + itemdescription + " was not found in the autosuggestion list.");
                } catch (ElementNotInteractableException e) {
                    System.err.println("The item with description " + itemdescription + " is not interactable.");
                } catch (Exception e) {
                    System.err.println("An unexpected error occurred while selecting the item: " + e.getMessage());
                }
            } else {
                System.err.println("Autosuggestion of product item entered is not displayed or not interactable.");
            }

            if (!itemFound) {
                System.out.println("Item with description " + itemdescription + " not found.");
            }

        } catch (Exception e) {
            System.err.println("An error occurred during the search and select process: " + e.getMessage());
        }
    }*/
 /*   public void SearchandSelectItemcodeOnPhoneOrderPage(String proditemcode, String itemdescription) {
        int maxRetries = 2; // retry up to 2 times if autosuggestion is not displayed
        boolean itemSelected = false;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
              //  fluentWait(prod_details_Itemcode1, 20, 3);
                prod_details_Itemcode1.clear();
                Double_Click_And_Type(prod_details_Itemcode1, proditemcode, "Item Code textbox field on product section in the Phone Order Page");
                delayWithGivenTime(3000);

                if (isElementDisplayed(row1_product_autosuggestion_listofoptions, "Item Code autosuggestion list on product details section in the phone order page")) {
                    try {
                        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
                        By item_ele = By.xpath("//ul[@id='ui-id-16']//li//div[contains(text(),'" + itemdescription + "')]");
                        wait.until(ExpectedConditions.visibilityOfElementLocated(item_ele));

                        WebElement item = getDriver().findElement(item_ele);
                        click(item, "Item Code autosuggestion list on product details section");
                        itemSelected = true;
                        break;
                    } catch (NoSuchElementException e) {
                        System.err.println("Item with description '" + itemdescription + "' was not found in the autosuggestion list.");
                    } catch (ElementNotInteractableException e) {
                        System.err.println("The item with description '" + itemdescription + "' is not interactable.");
                    } catch (Exception e) {
                        System.err.println("Unexpected error while selecting the item: " + e.getMessage());
                    }
                } else {
                    System.err.println("Attempt " + attempt + ": Autosuggestion not displayed. Retrying...");
                }

            } catch (Exception e) {
                System.err.println("Attempt " + attempt + ": Error during search and select: " + e.getMessage());
            }

            // Clear and retry if not selected
            if (!itemSelected && attempt < maxRetries) {
                prod_details_Itemcode1.clear();
                delayWithGivenTime(2000); // short delay before retry
            }
        }

        if (!itemSelected) {
            System.out.println("Item with description '" + itemdescription + "' was not selected after retries.");
        }
    }
*/
    public void SearchandSelectItemcodeOnPhoneOrderPage(String proditemcode, String itemdescription) {
        PageLoadLoggerUtils.logPageLoad("Phone Order Page → Search Item Code and Select item description from Autosuggestion Dropdown field", () -> {
            int maxRetries = 2;
            boolean itemSelected = false;

            for (int attempt = 1; attempt <= maxRetries; attempt++) {
                try {
                    prod_details_Itemcode1.clear();
                    Double_Click_And_Type(prod_details_Itemcode1, proditemcode, "Item Code textbox field on product section in the Phone Order Page");

                    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[starts-with(@id,'ui-id-')]")));

                    // Dynamic autosuggestion item based on description
                    By item_ele = By.xpath("//ul[starts-with(@id,'ui-id-')]//li//div[contains(text(),'" + itemdescription + "')]");
                    wait.until(ExpectedConditions.visibilityOfElementLocated(item_ele));

                    WebElement item = getDriver().findElement(item_ele);
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", item);
                    wait.until(ExpectedConditions.elementToBeClickable(item));
                    js_Click(item, "Item Code autosuggestion list on product details section");

                    itemSelected = true;
                    break;

                } catch (StaleElementReferenceException e) {
                    System.err.println("StaleElementReferenceException - Retrying attempt " + attempt);
                } catch (TimeoutException e) {
                    System.err.println("TimeoutException - Element not found or visible: " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Attempt " + attempt + ": Unexpected error - " + e.getMessage());
                }

                // Retry only if not selected
                if (!itemSelected && attempt < maxRetries) {
                    prod_details_Itemcode1.clear();
                    delayWithGivenTime(2000);
                }
            }

            if (!itemSelected) {
                System.err.println("Failed to select item with description: '" + itemdescription + "' after " + maxRetries + " attempts.");
            }
        });
    }


    /**
     * Verify the item code row2 autosuggestion is displayed or not
     *
     * @param proditemcode
     * @return If the item code row2 autosuggestion is displayed, returns true; otherwise, returns false
     * @Author: Balaji N
     */
    public boolean verify_item_code_row2_Autosuggestion_IsDisplayed(String proditemcode) {
        prod_details_Itemcode2.clear();
        prod_details_Itemcode2.sendKeys(proditemcode);
        delayWithGivenTime(2000);
        List<String> expectedProducts = Arrays.asList("rrd-Red Rose Small-199", "rrd-Red Rose Deluxe-299", "rrd-Red Rose Premium-399", "rrd-Red Rose Wedding Reception Flower-789", "rrd2-Red Rose Semi-premium-299");
        Set<String> actualProducts = new HashSet<>();

        for (WebElement products : listOfItemsUnderItemcode2) {
            HighlightElement(products);
            actualProducts.add(products.getText());
        }

        if (expectedProducts.containsAll(actualProducts)) {
            return true;
        } else {
            System.out.println("Failed: All expected products are displayed" + actualProducts);
            System.out.println("Failed: Expected products are displayed as: " + expectedProducts);
            return false;
        }
    }

    public void SearchandSelectItemcodeOnPhoneOrderPageShop2(String proditemcode, String itemdescription) {
        try {
            // Perform the double-click and type operation
            Double_Click_And_Type(prod_details_Itemcode1, proditemcode, "Item Code textbox field on product section in the Phone Order Page");
            delayWithGivenTime(3000);

            boolean itemFound = false;

            // Check if the autosuggestion element is displayed
            if (is_Element_Displayed(shop2_row1_product_autosuggestion_listofoptions, "Item Code autosuggestion list on product details section in the phone order page")) {
                try {
                    // Locate the item based on description and click
                    WebElement item = getDriver().findElement(By.xpath("//ul[@id='ui-id-28']//li//div[contains(text(),'" + itemdescription + "')]"));
                    js_Click(item, "Item Code autosuggestion list on product details section");
                    itemFound = true;
                } catch (NoSuchElementException e) {
                    // Handle scenario where item is not found in the list
                    System.err.println("Item with description " + itemdescription + " was not found in the autosuggestion list.");
                } catch (ElementNotInteractableException e) {
                    // Handle case where element is found but not interactable
                    System.err.println("The item with description " + itemdescription + " is not interactable.");
                } catch (Exception e) {
                    // Handle any other unexpected exceptions
                    System.err.println("An unexpected error occurred while selecting the item: " + e.getMessage());
                }
            } else {
                System.err.println("Autosuggestion of product item entered is not displayed or not interactable.");
            }

            if (!itemFound) {
                System.out.println("Item with description " + itemdescription + " not found.");
            }

        } catch (Exception e) {
            // General exception to catch any unforeseen errors during execution
            System.err.println("An error occurred during the search and select process: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Search with item code and Select the expected product item on product details section in row 2
     *
     * @param proditemcode
     * @param itemdescription
     * @Author Balaji N
     */
    public void SearchandSelectItemcode2OnPhoneOrderPage(String proditemcode, String itemdescription) {
       /* Double_Click_And_Type(prod_details_Itemcode2, proditemcode, "Item code textbox field row 2 in product details section");
        delayWithGivenTime(3000);
        if (isElementDisplayed(row2_itemcode_textbox2_autosugesstions, "Item code textbox field row 2 autosuggestion list in product details section") == true) {
            for (WebElement item : listOfItemsUnderItemcode2) {
                if (item.getText().contains(itemdescription)) {
                    js_Click(item, "Item Description autosuggestion list on product details section");
                    break;
                }
            }
        }*/
        try {
            Double_Click_And_Type(prod_details_Itemcode2, proditemcode, "Item Code row 2 textbox field on product section in the Phone Order Page");
            delayWithGivenTime(3000);

            boolean itemFound = false;
            if (is_Element_Displayed(row2_itemcode_textbox2_autosugesstions, "Item Code row 2 autosuggestion list on product details section in the phone order page")) {
                try {
                    WebElement item = getDriver().findElement(By.xpath("//ul[@id='ui-id-17']//li//div[contains(text(),'" + itemdescription + "')]"));
                    js_Click(item, "Item Code row 2 autosuggestion list on product details section");
                    itemFound = true;
                } catch (NoSuchElementException e) {
                    System.err.println("Item with description row 2 " + itemdescription + " was not found in the autosuggestion list.");
                } catch (ElementNotInteractableException e) {
                    System.err.println("The item with description row 2 " + itemdescription + " is not interactable.");
                } catch (Exception e) {
                    System.err.println("An unexpected error occurred while selecting the item: " + e.getMessage());
                }
            } else {
                System.err.println("Autosuggestion of product item entered is not displayed or not interactable.");
            }

            if (!itemFound) {
                System.out.println("Item with description " + itemdescription + " not found.");
            }

        } catch (Exception e) {
            System.err.println("An error occurred during the search and select process: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void Enter_Product_ItemCode_Row1(String itemcode) {
        prod_details_Itemcode1.clear();
        clickAndType(prod_details_Itemcode1, itemcode);
    }

    /**
     * It retrieves the row 1 item code entered or displayed value
     *
     * @return If entered row 1 item code is exists it returns the value of item code, otherwise it returns empty string
     * @Description This function will highlight the row 1 item code and retrieves the displayed value
     * @Author: Balaji N
     */
    public String getProdDetailsItemcode1OnPhoneOrderPage() {
        return getElementAttribute(prod_details_Itemcode1, "Item Code textbox field value on product section in the Phone Order Page");
    }

    /**
     * It retrieves the row 2 item code entered or displayed value
     *
     * @return If entered row 2 item code is exists it returns the value of item code, otherwise it returns empty string
     * @Description This function will highlight the row 2 item code and retrieves the displayed value
     * @Author: Balaji N
     */
    public String getProdDetailsItemcode2OnPhoneOrderPage() {
        HighlightElement(prod_details_Itemcode2);
        return prod_details_Itemcode2.getAttribute("value");
    }

    /**
     * Enters the item description on row 1 product details section
     *
     * @param itemdescription
     */
    public void Enter_Product_ItemDescription_Row1(String itemdescription) {
        prod_details_ItemDescription1.click();
        delayWithGivenTime(2000);
        prod_details_ItemDescription1.clear();
        delayWithGivenTime(1000);
        clickAndType(prod_details_ItemDescription1, itemdescription);
    }

    /**
     * It retrieves the row 1 item description entered or displayed value
     *
     * @return If entered row 1 item description is exists it returns the value of item description, otherwise it returns empty string
     * @Description This function will highlight the row 1 item description and retrieves the displayed value
     * @Author: Balaji N
     */
    public String getProdDetailsItemDescription1OnPhoneOrderPage() {
        HighlightElement(prod_details_ItemDescription1);
        return prod_details_ItemDescription1.getAttribute("value").trim();
    }

    /**
     * It retrieves the row 2 item description entered or displayed value
     *
     * @return If the item description row 2 value is retrieved, it returns the value; otherwise it returns empty string
     * @Description This function highlights the item description row 2 and then retrieves the item description value from the product details section on phone order page
     * @Author Balaji N
     */
    public String getProdDetailsItemDescription2OnPhoneOrderPage() {
        return get_element_attribute_with_trim(prod_details_ItemDescription2, "Item Description textbox field row 2 value on product section in the Phone Order Page");
    }

    /**
     * It retrieves the row 2 item quantity entered or displayed value
     *
     * @return If the item quantity row 2 value is retrieved, it returns the value; otherwise it returns empty string
     * @Author Balaji N
     */
    public String get_ProdDetails_ItemQty2OnPhoneOrderPage() {
       /* HighlightElement(prod_details_Qty2);
        return prod_details_Qty2.getAttribute("value");*/
        return getElementAttribute(prod_details_Qty2, "Item Quantity textbox field row 2 value on product section in the Phone Order Page");
    }

    public String get_ProdDetails_ItemUnitPrice2OnPhoneOrderPage() {
        HighlightElement(prod_details_Unitprice2);
        return prod_details_Unitprice2.getAttribute("value");
    }

    public String getDisplayedRecipientphoneOnPhoneOrderPage() {
        return getElementAttribute(recipientphoneonCPDV, "Recipient Phone textbox field value on customer details section in the Phone Order Page");
    }

    public String getDisplayedRecipientZipcodeOnPhoneOrderPage() {
        return getElementAttribute(recipientzipcodeonCPDV, "Recipient Zipcode textbox field value on customer details section in the Phone Order Page");
    }

    public String getSelectedWireInMethodOnPhoneOrderPage() {
        return get_Selected_Option(defaultwireinmethodonCPDV, "Wire in method value on phone order page");
    }

    public String getSelectedWireOutMethodOnPhoneOrderPage() {
        return get_Selected_Option(defaultwireoutmethodonCPDV, "Wire out method value on phone order page");
    }

    public String getSelectedPaymentTypeOnPhoneOrderPage() {
        return get_Selected_Option(paymenttypeonCPDV, "Payment type value on phone order page");
    }

    public String getSelectedCustomerTypeOnPhoneOrderPage() {
        return get_Selected_Option(selectcustomertypeOnCPDV, "Customer type value on phone order page");
    }

    public String getSelectedSalesPersonOnPhoneOrderPage() {
        return get_Selected_Option(salespersononCPDV, "sales person value on phone order page");
    }

    public String getSelectedCountryOnPhoneOrderPage() {
        return get_Selected_Option(selectcountryonCPDV, "Country value on phone order page");
    }

    public String getSelectedLocationOnPhoneOrderPage() {
        return get_Selected_Option(selectlocationonCPDV, "Location value on phone order page");
    }

    public String getSelectedOrderTypeOnPhoneOrderPage() {
        return get_Selected_Option(selectordertypeOnCPDV, "Order type value on phone order page");
    }

    /**
     * Enter the item quantity on textbox field in the product details section
     *
     * @param qty
     */
    public void Enter_Product_Qty_Row1(String qty) {
        jsClear(prod_details_Quantity1);
        delayWithGivenTime(1000);
        clickAndType(prod_details_Quantity1, qty);
    }

    /**
     * It retrieves the row 1 item quantity entered or displayed value
     *
     * @return If entered row 1 item quantity is exists it returns the value of item quantity, otherwise it returns empty string
     * @Description This function will highlight the row 1 item quantity and retrieves the displayed value
     * @Author: Balaji N
     */
    public String getProdItemQty1OnPhoneOrderPage() {
        return getElementAttribute(prod_details_Quantity1, "Product Item Quantity on Phone Order Page");
    }

    /**
     * It retrieves the row 2 item quantity entered or displayed value
     *
     * @return If the item quantity row 2 value is retrieved, it returns the value; otherwise it returns empty string
     * @Author Balaji N
     */
    public String getProdItemQty2OnPhoneOrderPage() {
        return getElementAttribute(prod_details_Qty2, "Item Quantity textbox field row 2 value on product section in the Phone Order Page");
    }

    public String getRecipientPhoneOnPhoneOrderPage() {
        return getElementAttribute(recipientphoneOnPhoneOrderPage, "Recipient Phone textbox field value on customer details section in the Phone Order Page");
    }

    /**
     * Enter the recipient phone number 2 in the phone order page
     *
     * @param reciphone2 The provided recipient phone 2 to be entered
     * @Description This function highlights and clicks the recipient phone 2 field and then enters the provided recipient phone number 2
     * @Author Balaji N
     */
    public void EnterRecipientPhone2OnPhoneOrderPage(String reciphone2) {
        clickAndType(recipientphone2OnPhoneOrderPage, reciphone2);
    }

    /**
     * It retrieves the recipient phone number 2 from the phone order page
     *
     * @return If text is exists it returns the displayed recipient phone number 2 as text, otherwise it returns empty string if no recipient phone number 2 to be entered
     * @Description: This function will highlight the recipient phone number 2 field and retrieves the entered recipient phone number 2 text as string
     * @Author: Balaji N
     */
    public String getRecipientPhone2OnPhoneOrderPage() {
//        HighlightElement(recipientphone2OnPhoneOrderPage);
//        return recipientphone2OnPhoneOrderPage.getAttribute("value");
        return getElementAttribute(recipientphone2OnPhoneOrderPage, "Recipient Phone 2 textbox field value on customer details section in the Phone Order Page");
    }


    public String getRecipientZipcodeOnPhoneOrderPage() {
        return getElementAttribute(recipientzipcodeOnPhoneOrderPage, "Recipient Zipcode textbox field value on customer details section in the Phone Order Page");
    }

    public void ClickWireInMethodOnPhoneOrderPage() {
        js_Click(wireinmethodOnPhoneOrderPage, "Wire In method on phone order page");
    }

    public void ClickWireOutMethodOnPhoneOrderPage() {
        js_Click(wireoutmethodOnPhoneOrderPage, "Wire Out method on phone order page");
    }

    public String getDisplayedWireInSelectedOption() {
        return get_Selected_Option(wireIndefaultDDOnPhoneOrderPage, "Wire in method value on phone order page");
    }

    public String getDisplayedWireOutSelectedOption() {
        return get_Selected_Option(wireOutdefaultDDOnPhoneOrderPage, "Wire out method value on phone order page");
    }

    /**
     * Click the delivery menu as delivery type on phone order page
     *
     * @Description: This method will click the delivery menu as delivery type on phone order page
     * @Author Balaji N
     */
    public void ClickdeliveryTypeOnPhoneOrderPage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));

        js_Click(deliverytypeOnPhoneOrderPage, "Delivery as delivery type button in the phone order page");
    }

    /**
     * It retrieves the delivery type selected option on phone order page
     *
     * @return If the delivery type selected option is retrieved, it returns the text; otherwise it returns empty string if no delivery type selected
     * @Author Balaji N
     */
    public String get_HighlightedColor_OnDelivery_TypeOnPhoneOrderPage() {
        String color = deliverytypeOnPhoneOrderPage.getCssValue("color");
        String hexColor = rgbaToHex(color);
        return hexColor;
    }

    /**
     * Click the WireIn menu as delivery type on phone order page
     *
     * @Description: This method will click the WireIn menu as delivery type on phone order page
     * @Author Balaji N
     */
    public void Click_WireIn_DeliveryType_OnPhoneOrderPage() {
        js_Click(wirein_deliverytype_OnPhoneOrderPage, "Wire in as delivery type button in the phone order page");
    }

    /**
     * Click the WireOut menu as delivery type on phone order page
     *
     * @Description: This method will click the WireOut menu as delivery type on phone order page
     * @Author Balaji N
     */
    public void Click_WireOut_DeliveryType_OnPhoneOrderPage() {
        js_Click(wireout_deliverytype_OnPhoneOrderPage, "Wire out as delivery type button in the phone order page");
    }

    /**
     * Get the delivery type selected option on phone order page
     *
     * @return It returns the displayed delivery type selected option
     * @Author: Balaji N
     */
    public String get_HighlightedColor_On_WireOut_PhoneOrderPage() {
        String color = wireout_deliverytype_OnPhoneOrderPage.getCssValue("color");
        String hexColor = rgbaToHex(color);
        return hexColor;
    }

    /**
     * Get the payment type selected option on phone order page
     *
     * @return It returns the displayed payment type selected option
     * @Author: Balaji N
     */
    public String getDisplayedPaymentTypeSelectedOption() {
        return get_Selected_Option(paymentTypeDropdownOnPhoneOrderPage, "Payment Type dropdown field value on payment section in the phone order page");
    }

    public boolean is_Payment_Type_Section_On_PhoneOrderPage_Displayed() {
        return isElementDisplayed(paymentSectionOnPhoneOrderPage, "Payment section in the phone order page");
    }

    public boolean verify_Payment_Type_Option_Present(String expectedOption) {
        Select select = new Select(paymentTypeDropdownOnPhoneOrderPage);
        boolean isOptionPresent = select.getOptions().stream()
                .anyMatch(option -> option.getText().trim().equals(expectedOption));

        return isOptionPresent;
    }

    public boolean verify_Payment_Type_Option_NotPresent(String optionNotExpected) {
        for (WebElement option : paymentTypeDropdownOptionsOnPhoneOrderPage) {
            if (option.getText().trim().equalsIgnoreCase(optionNotExpected)) {
                return false;
            }
        }
        return true;
    }


    public boolean is_Payment_Type_Dropdown_Displayed_OnPhoneOrderPage() {
        return is_Element_Displayed(paymentTypeDropdownOnPhoneOrderPage, "Payment Type dropdown field on payment section in the phone order page");
    }

    /**
     * Select the payment type on phone order page
     *
     * @param paymentType The payment type to be selected
     * @Author Balaji N
     */
    public void SelectPaymentTypeOnPhoneOrderPage_PaymentSection(String paymentType) {
        drop_Down(paymentTypeDropdownOnPhoneOrderPage, paymentType, "VisibleText", "Select Payment Type dropdown field on phone order page");
    }

    /**
     * Get the selected payment type on phone order page
     *
     * @return it will return the selected payment type on phone order page
     * @Author: Balaji N
     */
    public String get_SelectedPaymentType_OnPhoneOrderPage() {
        return get_selected_option(paymentTypeDropdownOnPhoneOrderPage, "Payment type dropdown field value on payment section in the phone order page");
    }

    /**
     * Click the pick up type menu as delivery type on phone order page
     *
     * @return It display pickup type required fields on phone order page
     * @Description: This function click the pick up type menu as delivery type on phone order page
     * @Author: Balaji N
     */
    public void ClickPickupTypeOnPhoneOrderPage() {
        js_Click(pickupOnPhoneOrderPage, "Pickup Delivery type button on Phone Order Page");
    }

    /**
     * Retrieves the hex color code for pickup type menu on phone order page
     *
     * @return it will return the highlighted color of pickup type menu on phone order page
     * @Description: This function will get the css value of pickup type menu as string and convert the rgba to hex code then return value hex code as string
     * @Author: Balaji N
     */

    public String getHighlightedColorOnPickupTypeOnPhoneOrderPage() {
        try {
            String color = pickupTypeOnPhoneOrderPage.getCssValue("color");
            return rgbaToHex(color);
        } catch (NoSuchElementException e) {
            System.err.println("Element 'pickupTypeOnPhoneOrderPage' not found: " + e.getMessage());
        } catch (WebDriverException e) {
            System.err.println("WebDriver error while getting color: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Clicks the delivery type menu as delivery type on phone order page
     *
     * @Author Balaji N
     */
    public void ClickDeliveryTypeOnPhoneOrderPage() {
        // Wait for the initial page to fully load
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        js_Click(deliverytypeOnPhoneOrderPage, "Delivery as Delivery type button on Phone Order Page");
    }


    /**
     * Retrieves the hex color code for delivery type menu on phone order page
     *
     * @return it will return the highlighted color of delivery type menu on phone order page
     * @Description: This function will get the css value of delivery type menu as string and convert the rgba to hex code then return value hex code as string
     * @Author: Balaji N
     */
    public String getHighlightedColorOnDeliveryTypeOnPhoneOrderPage() {
        String color = deliverytypeOnPhoneOrderPage.getCssValue("color");
        String hexColor = rgbaToHex(color);
        return hexColor;
    }

    /**
     * Retrieves the highlighted color of the Wire-In type on the Phone Order Page.
     *
     * @return The color in hexadecimal format.
     * @author Balaji N
     */
    public String getHighlightedColorOnWireInTypeOnPhoneOrderPage() {
       /* String color = wireinmethodOnPhoneOrderPage.getCssValue("color");
        String hexColor = rgbaToHex(color);
        return hexColor;*/
        try {
            String color = wireinmethodOnPhoneOrderPage.getCssValue("color");
            return rgbaToHex(color);
        } catch (Exception e) {
            printError(wireinmethodOnPhoneOrderPage, "Wire-In Type Highlighted Color", "Color Retrieval Exception", e);
            return null; // Returning null to avoid breaking the execution flow
        }
    }

    public String getDisplayedSalesPersonSelectedOption() {
        return get_Selected_Option(salesPersonDDonPhoneOrder, "Sales Person dropdown field value on phone order page");
    }

    public String getDisplayedCountryonRecipientSecOnPhoneOrderPage() {
        return get_Selected_Option(recipientcountryOnPhoneOrderPage, "Country dropdown field value on recipient section in the phone order page");
    }

    public String getDisplayedLocationonRecipientSecOnPhoneOrderPage() {
        return get_Selected_Option(recipientlocationOnPhoneOrderPage, "Location dropdown field value on recipient section in the phone order page");
    }

    public boolean verifyPickupOrderTypesIsHighlighted() {
        boolean isPickupHighlighted = pickupOnPhoneOrderPage.getAttribute("class").contains("active");
        return isPickupHighlighted;
    }

    public boolean verifyDeliveryOrderTypesIsHighlighted() {
        boolean isDeliveryHighlighted = deliverytypeOnPhoneOrderPage.getAttribute("class").contains("active");
        return isDeliveryHighlighted;
    }

    public boolean verifyWireInOrderTypesIsHighlighted() {
        boolean isWireInHighlighted = wireinmethodOnPhoneOrderPage.getAttribute("class").contains("active");
        return isWireInHighlighted;
    }

    public boolean verifyWireOutOrderTypesIsHighlighted() {
        boolean isWireOutHighlighted = wireoutmethodOnPhoneOrderPage.getAttribute("class").contains("active");
        return isWireOutHighlighted;
    }

    /**
     * Verifies if the success toast message appears on the phone order page
     *
     * @return If the success toast message appears it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean verifySuccessToastMessageAppears() {
        By toastLocator = By.xpath("//div[@class='toast-message']");
        int maxRetries = 1;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
                WebElement toastElement = wait.until(ExpectedConditions.presenceOfElementLocated(toastLocator));

                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='3px solid green'", toastElement);

                String msg = "✅ Success Toast Message appeared on the screen [Attempt: " + attempt + "]";
                System.out.println(msg);
                return true;

            } catch (TimeoutException e) {
                logRetry("TimeoutException", "Toaster Message", attempt, e);
            } catch (NoSuchElementException e) {
                logRetry("NoSuchElementException", "Toaster Message", attempt, e);
            } catch (StaleElementReferenceException e) {
                logRetry("StaleElementReferenceException", "Toaster Message", attempt, e);
            } catch (WebDriverException e) {
                logRetry("WebDriverException", "Toaster Message", attempt, e);
            } catch (Exception e) {
                logRetry("UnexpectedException", "Toaster Message", attempt, e);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }
        }

        String finalMsg = "❌ Toast Message not found after " + maxRetries + " attempts.";
        Allure.step(finalMsg);
        System.err.println(finalMsg);
        return false;
    }

    private void logRetry(String exceptionType, String fieldName, int attempt, Exception e) {
        String msg = "⚠️ " + exceptionType + " on '" + fieldName + "' [Attempt " + attempt + "] - " + e.getMessage();
        Allure.step(msg);
        System.err.println(msg);
        if (attempt == 3) {
            e.printStackTrace();
        }
    }


//    public boolean verifySuccessToastMessageAppears() {
//        return isElementDisplayed(SuccessToastMsg, "Toaster Message Text");
//    }

//    public boolean verifySuccessToastMessageAppears() {
//        try {
//            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
//            By toastLocator = By.xpath("//div[@class='toast-message']");
//
//            WebElement toastElement = wait.until(ExpectedConditions.presenceOfElementLocated(toastLocator));
//
//            // Highlight the element for visual debug
//            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='3px solid green'", toastElement);
//
//            String msg = "Success Toast Message appeared on the screen.";
//            Allure.step(msg);
//            System.out.println(msg);
//
//            return true;
//
//        } catch (TimeoutException e) {
//            logFailure("TimeoutException", "Toaster Message on  page", e);
//        } catch (NoSuchElementException e) {
//            logFailure("NoSuchElementException", "Toaster Message on page", e);
//        } catch (StaleElementReferenceException e) {
//            logFailure("StaleElementReferenceException", "Toaster Message on page", e);
//        } catch (WebDriverException e) {
//            logFailure("WebDriverException", "Toaster Message on page", e);
//        } catch (Exception e) {
//            logFailure("UnexpectedException", "Toaster Message on page", e);
//        }
//
//        return false;
//    }
//
//    private void logFailure(String exceptionType, String fieldName, Exception e) {
//        String msg = exceptionType + " caught for field '" + fieldName + "'. Error: " + e.getMessage();
//        Allure.step(msg);
//        System.err.println(msg);
//        e.printStackTrace();
//    }


    /**
     * Returns the text of the success toast message on the phone order page
     *
     * @return If the success toast message appears it returns the text of the success toast message, otherwise it returns an empty string
     * @Author Balaji N
     */
//    public String verifySuccessToastMessageText() {
//        return getElementText(SuccessToastMsg, "Toaster Message Text");
//    }

   /* public String verifySuccessToastMessageText() {
        String fieldName = "Toaster Message Text";
        int maxRetries = 3;
        int delayBetweenRetries = 1000;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                // Wait for toaster to be visible
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                wait.until(ExpectedConditions.visibilityOf(SuccessToastMsg));

                HighlightElement(SuccessToastMsg);
                String toastText = SuccessToastMsg.getText().trim();

                return toastText;

            } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException e) {
                String msg = "⚠️ Attempt " + attempt + ": Unable to retrieve " + fieldName + " due to " + e.getClass().getSimpleName();
                Allure.step(msg);
                delayWithGivenTime(delayBetweenRetries);
            } catch (Exception e) {
                String msg = "❌ Unexpected error on attempt " + attempt + ": " + e.getMessage();
                Allure.step(msg);
                printError(SuccessToastMsg, fieldName, msg, e);
                break;  // Don’t retry on unknown/unexpected exceptions
            }
        }

        String failMsg = "❌ Failed to retrieve " + fieldName + " after " + maxRetries + " attempts.";
        Allure.step(failMsg);
        printError(SuccessToastMsg, fieldName, failMsg, null);
        return "Error: Unable to retrieve " + fieldName;
    }
*/
    private By successToastBy = By.xpath("//div[@class='toast-message']");


    public void hover_On_Success_Toast_Message() {
        Mouse_Hover(this, getDriver().findElement(successToastBy), "Success Toast Message");
    }

    public String verifySuccessToastMessageText() {
        String fieldName = "Toaster Message Text";
        int maxRetries = 1;
        int delayBetweenRetries = 300;
        List<String> attemptLogs = new ArrayList<>();

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
//                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
//                WebElement toastElement = wait.until(ExpectedConditions.visibilityOfElementLocated(successToastBy));
//               // HighlightElement(toastElement);
                String toastText = getDriver().findElement(successToastBy).getText().trim();
                return toastText;

            } catch (TimeoutException e) {
                attemptLogs.add("Attempt " + attempt + ": TimeoutException → Element not visible within wait time.");
            } catch (StaleElementReferenceException e) {
                attemptLogs.add("Attempt " + attempt + ": StaleElementReferenceException → Element detached from DOM (possibly disappeared quickly).");
            } catch (NoSuchElementException e) {
                attemptLogs.add("Attempt " + attempt + ": NoSuchElementException → Element not found in DOM.");
            } catch (Exception e) {
                attemptLogs.add("Attempt " + attempt + ": UnexpectedException → " + e.getMessage());
                break; // No point retrying on unknown fatal errors
            }

            delayWithGivenTime(delayBetweenRetries);
        }

        // Final failure logging
        String failMsg = "❌ Failed to retrieve " + fieldName + " after " + maxRetries + " attempts.";
        Allure.step(failMsg);

        // Attach detailed debug info in Allure
        String debugInfo = "\n"
                + "❌ [TOASTER RETRIEVAL FAILURE]\n"
                + "Field Name: " + fieldName + "\n"
                + "Locator   : " + successToastBy.toString() + "\n"
                + "\n"
                + "🧪 Attempt Summary:\n"
                + String.join("\n", attemptLogs) + "\n"
                + "\n"
                + "🔍 POSSIBLE CAUSES:\n"
                + " - Toaster message disappears too quickly before being read\n"
                + " - Locator is incorrect or changed in UI\n"
                + " - Page refresh or DOM update occurred during retrieval\n"
                + " - Animation delays prevented visibility within timeout\n";

        Allure.addAttachment("Toaster Retrieval Failure Details", debugInfo);

        return "Error: Unable to retrieve " + fieldName;
    }

    By closeicontoaster = By.xpath("//button[@class='toast-close-button']");

    public void click_Close_Icon_On_Toaster_Message() {
        click(getDriver().findElement(closeicontoaster), "Close Icon on Toaster Message");
    }

    /**
     * Returns the toaster message text is displayed on the phone order page
     *
     * @return If the toaster message appears it returns the text of the toaster message, otherwise it returns an empty string
     * @Author Balaji N
     */
    public String verify_Toaster_Message_Text_displayed() {
        return getElementText(toaster_message_title, "Draft Order Toaster Message on phone order page");
    }

    /**
     * Search and Selects a customer name from the auto suggest dropdown on the phone order entry page.
     * <p>
     * This function interacts with a auto suggestion dropdown element and Search & selects a customer name based on the
     * visible text. It uses the provided customer name to locate the option in the auto suggest dropdown.
     * </p>
     *
     * @param customerName The name of the customer name to be selected from the autosuggest dropdown field
     * @Author: Balaji N
     */
/*    public void SearchAndSelectCustomerOnCust_Section(String customerName) {
        try {
            if (searchCustomertextboxOnCustSection == null) {
                throw new NullPointerException("Customer Search textbox field - Customer section phone order - WebElement is null.");
            }

            searchCustomertextboxOnCustSection.clear();
            delayWithGivenTime(500);

            Double_Click_And_Type(searchCustomertextboxOnCustSection, customerName,
                    "Search customer textbox field - customer section on phone order page");
            delayWithGivenTime(3000);


            // Null and visibility check for auto-suggestion list
            if (listOfCustomerNamesOnCustSection == null) {
                throw new NullPointerException("list Of Customer Names On Customer Section is null.");
            }

            if (listOfCustomerNamesOnCustSection.isEmpty()) {
                throw new NoSuchElementException("Autosuggestion list is empty for customer search textbox field: " + customerName);
            }

            if (!listOfCustomerNamesOnCustSection.get(0).isDisplayed()) {
                throw new NoSuchElementException("Autosuggestion list is not displayed for customer search textbox field: " + customerName);
            }
            // Iterate through suggestions and select matching customer
            for (WebElement customerElement : listOfCustomerNamesOnCustSection) {
                if (customerElement != null && customerElement.getText().contains(customerName)) {
                    js_Click(customerElement, "Customer search textbox field Autosuggestion Element");
                    return;
                }
            }

            throw new NoSuchElementException("No matching customer found in autosuggestion list for: " + customerName);

        } catch (NullPointerException npe) {
            printError(searchCustomertextboxOnCustSection,
                    "Customer Search textbox field - on customer section",
                    "NullPointerException: " + npe.getMessage(), npe);
        } catch (NoSuchElementException nse) {
            printError(searchCustomertextboxOnCustSection,
                    "Customer Search textbox field - on customer section",
                    "NoSuchElementException: " + nse.getMessage(), nse);
        } catch (StaleElementReferenceException sere) {
            printError(searchCustomertextboxOnCustSection,
                    "Customer Search textbox field - on customer section",
                    "StaleElementReferenceException: The element is no longer attached to the DOM.", sere);
        } catch (Exception e) {
            printError(searchCustomertextboxOnCustSection,
                    "Customer Search textbox field - on customer section",
                    "Unexpected Exception: " + e.getMessage(), e);
        }
    }*/
    public void SearchAndSelectCustomerOnCust_Section(String customerName) {
        PageLoadLoggerUtils.logPageLoad("Phone Order Page → Search and Select Customer Name Autosuggest Dropdown field", () -> {
            try {
                if (searchCustomertextboxOnCustSection == null) {
                    throw new NullPointerException("Customer Search textbox field - Customer section phone order - WebElement is null.");
                }

                int maxRetries = 3;
                boolean customerSelected = false;

                for (int attempt = 1; attempt <= maxRetries; attempt++) {
                    // Clear and enter customer name
                    searchCustomertextboxOnCustSection.clear();
                    delayWithGivenTime(500);

                    Double_Click_And_Type(searchCustomertextboxOnCustSection, customerName,
                            "Search customer textbox field - customer section on phone order page");
                    delayWithGivenTime(3000);

                    if (listOfCustomerNamesOnCustSection != null && !listOfCustomerNamesOnCustSection.isEmpty()) {
                        if (listOfCustomerNamesOnCustSection.get(0).isDisplayed()) {
                            for (WebElement customerElement : listOfCustomerNamesOnCustSection) {
                                if (customerElement != null && customerElement.getText().contains(customerName)) {
                                    js_Click(customerElement, "Customer search textbox field Autosuggestion Element");
                                    customerSelected = true;
                                    break;
                                }
                            }
                            if (customerSelected) {
                                break;
                            }
                        }
                    }

                    System.out.println("Attempt " + attempt + ": Customer autosuggestion not loaded or match not found. Retrying...");
                    delayWithGivenTime(2000);
                }

                if (!customerSelected) {
                    throw new NoSuchElementException("Customer not selected after retries. Autosuggestion did not display or no match found for: " + customerName);
                }

            } catch (NullPointerException npe) {
                printError(searchCustomertextboxOnCustSection,
                        "Customer Search textbox field - on customer section",
                        "NullPointerException: " + npe.getMessage(), npe);
            } catch (NoSuchElementException nse) {
                printError(searchCustomertextboxOnCustSection,
                        "Customer Search textbox field - on customer section",
                        "NoSuchElementException: " + nse.getMessage(), nse);
            } catch (StaleElementReferenceException sere) {
                printError(searchCustomertextboxOnCustSection,
                        "Customer Search textbox field - on customer section",
                        "StaleElementReferenceException: The element is no longer attached to the DOM.", sere);
            } catch (Exception e) {
                printError(searchCustomertextboxOnCustSection,
                        "Customer Search textbox field - on customer section",
                        "Unexpected Exception: " + e.getMessage(), e);
            }
        });
    }

    /**
     * Verifies if autosuggestion is displayed on customer section - Search customer textbox field phone order page
     *
     * @param customerName - The name of the customer name to be selected from the autosuggest dropdown field
     * @return If autosuggestion is displayed return true else return false
     * @Author Balaji N
     */
    public boolean VerifyAutosuggestion_Displayed_On_Cust_Section(String customerName) {
        try {
            searchCustomertextboxOnCustSection.clear();
            delayWithGivenTime(500);
            Double_Click_And_Type(searchCustomertextboxOnCustSection, customerName, "Search Customer textbox on customer section in the phone order page");
            delayWithGivenTime(2000);
            if (listOfCustomerNamesOnCustSection.isEmpty() || listOfCustomerNamesOnCustSection.size() == 0) {
                System.out.println("No suggestion displayed and list of customers size is " + listOfCustomerNamesOnCustSection.size());
                return true;
            } else if (cust_unorderlistOnCustSection.getAttribute("style").contains("none")) {
                System.out.println("No suggestion displayed status " + cust_unorderlistOnCustSection.getAttribute("style").contains("none"));
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Searches for a customer in the customer section, selects the matching customer, and clicks on it.
     *
     * @param customerName     The partial or full name of the customer to search.
     * @param customerFullName The full name of the customer to match and select.
     * @throws IllegalArgumentException        If the input parameters are null or empty.
     * @throws NullPointerException            If any required elements are null.
     * @throws NoSuchElementException          If the search textbox or customer list is not found.
     * @throws StaleElementReferenceException  If the elements become stale during interaction.
     * @throws TimeoutException                If the operation takes too long.
     * @throws ElementNotInteractableException If the element is present but not interactable.
     * @throws RuntimeException                For any other unexpected errors.
     */
    public void SearchAndSelect_CustomerOnCust_Section(String customerName, String customerFullName) {
        final int maxRetries = 2;
        int retryCount = 0;

        try {
            if (customerName == null || customerName.trim().isEmpty()) {
                throw new IllegalArgumentException("Customer name cannot be null or empty.");
            }
            if (customerFullName == null || customerFullName.trim().isEmpty()) {
                throw new IllegalArgumentException("Customer full name cannot be null or empty.");
            }

            if (searchCustomertextboxOnCustSection == null) {
                throw new NullPointerException("Search textbox element is null.");
            }
            if (listOfCustomerNamesOnCustSection == null) {
                throw new NullPointerException("Customer list elements are null.");
            }

            while (retryCount < maxRetries) {
                try {
                    searchCustomertextboxOnCustSection.clear();
                    delayWithGivenTime(500);
                    Double_Click_And_Type(searchCustomertextboxOnCustSection, customerName, "Search Customer textbox field on customer section in the phone order page");
                    delayWithGivenTime(2000); // Allow time for list to refresh

                    boolean customerFound = false;

                    for (WebElement customerElement : listOfCustomerNamesOnCustSection) {
                        if (customerElement != null && customerElement.getText().contains(customerFullName)) {
                            customerElement.click();
                            customerFound = true;
                            break;
                        }
                    }

                    if (customerFound) {
                        return; // Success
                    } else {
                        retryCount++;
                        if (retryCount >= maxRetries) {
                            throw new NoSuchElementException("Customer with name '" + customerFullName + "' not found after " + maxRetries + " attempts.");
                        }
                        delayWithGivenTime(1000); // Short wait before retry
                    }

                } catch (StaleElementReferenceException | ElementNotInteractableException e) {
                    retryCount++;
                    if (retryCount >= maxRetries) {
                        throw new RuntimeException("Retry failed due to stale or non-interactable elements after " + maxRetries + " attempts.", e);
                    }
                    delayWithGivenTime(1000); // Short wait before retry
                }
            }

        } catch (IllegalArgumentException | NullPointerException | NoSuchElementException e) {
            throw e;
        } catch (TimeoutException e) {
            throw new TimeoutException("Operation timed out: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage(), e);
        }
    }


    /**
     * Clicks on the recipient's first name on the phone order page
     *
     * @Description: This method clicks on the recipient's first name on the phone order page
     * @Author: Balaji N
     */
    public void ClickReciNameOnPhoneOrderPage() {
        recipientfirstnameOnPhoneOrderPage.click();
    }


    /**
     * Searches for the recipient by first name and selects the appropriate auto-suggestion
     * from the list on the Recipient Section of the Phone Order page.
     *
     * @param recipientFirstName The first name of the recipient to search for.
     * @throws NoSuchElementException if the recipient input field or auto-suggestion list is not found.
     * @throws TimeoutException       if the operation takes too long to execute.
     * @Author Balaji N
     */
    public void SearchAndSelect_Recipient_OnRecipient_Section(String recipientfirstname) {
        try {
            recipientfirstnameOnPhoneOrderPage.clear();
            delayWithGivenTime(500);

            // Type recipient name in the textbox
            Double_Click_And_Type(recipientfirstnameOnPhoneOrderPage, recipientfirstname,
                    "Recipient First Name textbox field on phone order page");
            delayWithGivenTime(2000);

            // Loop through the auto-suggestions list
            boolean recipientFound = false;
            for (WebElement autosuggestion : ListOfReciFirstName_Autosuggestions_OnPhoneOrderPage) {
                if (autosuggestion.getText().contains(recipientfirstname)) {
                    delayWithGivenTime(2000);
                    js_Click(autosuggestion, "Recipient First Name Autosuggestion Element");
                    recipientFound = true;
                    break;
                }
            }

            if (!recipientFound) {
                throw new NoSuchElementException("No matching recipient found in auto-suggestions for: " + recipientfirstname);
            }

        } catch (NoSuchElementException e) {
            System.err.println("Element not found: " + e.getMessage());
            throw e;
        } catch (TimeoutException e) {
            System.err.println("Operation timed out while searching for recipient: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while selecting recipient: " + e.getMessage());
            throw new RuntimeException("Error in searchAndSelectRecipientOnRecipientSection", e);
        }
    }

    /**
     * Searches for the recipient by first name and selects the appropriate auto-suggestion
     * from the list on the Recipient Section of the Phone Order page.
     *
     * @param recipientFirstName The first name of the recipient to search for.
     * @throws NoSuchElementException if the recipient input field or auto-suggestion list is not found.
     * @throws TimeoutException       if the operation takes too long to execute.
     * @Author Balaji N
     */
    public void SearchAndSelect_Recipient_OnRecipient_Section(String recipientfirstname, String recipient_address) {
        int maxRetries = 3;
        int delayBetweenRetries = 500; // ms
        List<String> attemptLogs = new ArrayList<>();
        boolean success = false;

        By auto_suggestion = By.xpath(
                "(//ul[starts-with(@id, 'ui-id-') and not(contains(@style,'display: none'))] //li//div[contains(normalize-space(.), '" + recipient_address + "')])[1]"
        );

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                // Clear and type recipient name
                recipientfirstnameOnPhoneOrderPage.clear();
                delayWithGivenTime(500);
                Double_Click_And_Type(recipientfirstnameOnPhoneOrderPage, recipientfirstname,
                        "Recipient First Name textbox field on phone order page");

                // Wait for auto-suggestion list to appear
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                isElementDisplayed(Recipient_firstname_autosugesstion_values, "Recipient firstname autosuggestion");
                delayWithGivenTime(2000);

                WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(auto_suggestion));

                if (ele != null && isElementDisplayed(ele, "Recipient firstname autosuggestion")) {
                    js_Click(ele, "recipient firstname autosuggest element");
                    success = true;
                    break; // Stop retry loop
                } else {
                    attemptLogs.add("Attempt " + attempt + ": Auto-suggest element not visible or mismatched address.");
                }
            } catch (TimeoutException e) {
                attemptLogs.add("Attempt " + attempt + ": TimeoutException → Auto-suggest not visible within wait time.");
            } catch (NoSuchElementException e) {
                attemptLogs.add("Attempt " + attempt + ": NoSuchElementException → Auto-suggest list not found.");
            } catch (Exception e) {
                attemptLogs.add("Attempt " + attempt + ": UnexpectedException → " + e.getMessage());
                break; // Don't retry unknown fatal errors
            }

            delayWithGivenTime(delayBetweenRetries);
        }

        // If failed after all retries
        if (!success) {
            logUserFriendlyFailureDetails(
                    "Recipient firstname autosuggestion",
                    auto_suggestion.toString(),
                    attemptLogs,
                    "RECIPIENT AUTO-SUGGESTION FAILURE",
                    "Auto-suggest not visible within wait time",
                    "No matching address found in the suggestions",
                    "Locator might be outdated or changed",
                    "UI took longer than expected to load suggestions",
                    "Network delay in fetching results"
            );
        }
    }


    /**
     * Entered the first name on Recipient section in the phone order page
     *
     * @param recifname The recipient's first name to be entered.
     * @Description: This method clears the existing data in the recipient first name field,
     * performs a double-click to select the field, and then enters the provided recipient first name.
     * @Author: Balaji N
     */
    public void EnterReciFirstName(String recifname) {
        recipientfirstnameOnPhoneOrderPage.clear();
        Double_Click_And_Type(recipientfirstnameOnPhoneOrderPage, recifname, "Recipient First Name field textbox on phone order page");
    }

    /**
     * Clicks the recipient's first name on the phone order page
     *
     * @Author Balaji N
     */
    public void Click_Recipient_FirstName() {
        recipientfirstnameOnPhoneOrderPage.click();
    }

    /**
     * Retrieves the first name field entered text from recipient section on the phone order page
     *
     * @return If text is exists it returns the displayed first name field as text in recipient section, otherwise it returns empty string if no text entered
     * @Description: This function will retrieves the entered first name in recipient section.
     * @Author: Balaji N
     */
    public String getReciFirstName() {
        return get_element_attribute_with_trim(recipientfirstnameOnPhoneOrderPage, "First Name textbox field on recipient section (displayed value) in the phone order page");
    }

    /**
     * Entered the last name on Recipient section in the phone order page
     *
     * @param recilname The recipient's last name to be entered.
     * @Description: This method clears the existing data in the recipient last name field,
     * performs a double-click to select the field, and then enters the provided recipient last name.
     * @Author: Balaji N
     */
    public void EnterReciLastName(String recilname) {
        recipientlastnameOnPhoneOrderPage.clear();
        Double_Click_And_Type(recipientlastnameOnPhoneOrderPage, recilname, "Recipient Last Name field textbox on phone order page");
    }

    /**
     * Retrieves the last name field entered text from recipient section on the phone order page
     *
     * @return If text is exists it returns the displayed last name field as text in recipient section, otherwise it returns empty string if no text entered
     * @Description: This function will retrieves the entered last name in recipient section.
     * @Author: Balaji N
     */
    public String getReciLastName() {
        return getElementAttribute(recipientlastnameOnPhoneOrderPage, "Last Name textbox field on recipient section (displayed value) in the phone order page");
    }


    /**
     * Entered the address 1 on Recipient section in the phone order page
     *
     * @param reciaddress1 The recipient's last name to be entered.
     * @Description: This method clears the existing data in the recipient last name field,
     * performs a double-click to select the field, and then enters the provided recipient last name.
     * @Author: Balaji N
     */
    public void EnterReciAddress1(String reciaddress1) {
        jsClear(recipientaddress1OnPhoneOrderPage);
        delayWithGivenTime(1000);
        //DoubleClickAndType(recipientaddress1OnPhoneOrderPage, reciaddress1);
        Double_Click_And_Type(recipientaddress1OnPhoneOrderPage, reciaddress1, "Recipient Address 1 field textbox on phone order page");
    }

    /**
     * Verifies if autosuggestion is displayed on recipient address 1 field
     *
     * @return true if autosuggestion is displayed otherwise false
     * @Description: This method verifies if autosuggestion is displayed on recipient address 1 field
     * @Author: Balaji N
     */
    public boolean Verify_Autosuggestion_Displayed_OnReciAddress1() {
        recipientaddress1OnPhoneOrderPage.clear();
        delayWithGivenTime(1000);
        for (int i = 0; i < ListOfReciAddress1_Autosuggestions_OnPhoneOrderPage.size(); i++) {
            int ReciAddress1_size = ListOfReciAddress1_Autosuggestions_OnPhoneOrderPage.size();
            if (ReciAddress1_size > 0) {
                return true;
            }
        }
        return false;

    }

    /**
     * Search and select the address 1 field on recipient section in the phone order page
     *
     * @param reciaddress1 The provided recipient's address 1 to be entered.
     * @Description This method clears the existing data in the recipient address 1 field, and search for the address 1 and select the expected address 1 from the auto suggest dropdown
     * @Author: Balaji N
     */
    public void SearchAndSelectReciAddress1(String reciaddress1) {
        By first_match_address = By.xpath(
                "(//div[contains(@class,'pac-container') and not(contains(@style,'display: none'))]" +"//div[contains(@class,'pac-item')])[1]"
        );

        try {
            wait_For_Page_To_Be_Stable(getDriver());

            recipientaddress1OnPhoneOrderPage.clear();
            delayWithGivenTime(500);
            recipientaddress1OnPhoneOrderPage.sendKeys(reciaddress1);
            delayWithGivenTime(2000);

            boolean clicked = false;
            int maxRetries = 3;

            for (int attempt = 1; attempt <= maxRetries; attempt++) {
                try {
                    System.out.println("🔄 Attempt " + attempt + ": Trying to select suggestion...");

                    WebElement suggestion = getDriver().findElement(first_match_address);
                    Click(suggestion, "Recipient Address 1 autosuggestion (attempt " + attempt + ")");

                    String msg = "✅ Successfully clicked suggestion for '" + reciaddress1 + "' on attempt " + attempt;
                    clicked = true;
                    break;

                } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException e) {
                    String warnMsg = "⚠️ Attempt " + attempt + " failed: " + e.getClass().getSimpleName();
                    Allure.step(warnMsg);
                    System.out.println(warnMsg);

                    if (attempt < maxRetries) {
                        recipientaddress1OnPhoneOrderPage.clear();
                        delayWithGivenTime(500);
                        recipientaddress1OnPhoneOrderPage.sendKeys(reciaddress1);
                        delayWithGivenTime(2000);
                    } else {
                        System.out.println("❌ Final attempt failed.");
                    }

                } catch (Exception e) {
                    String errMsg = "❌ Unexpected error on attempt " + attempt + ": " + e.getMessage();
                    Allure.step(errMsg);
                    System.out.println(errMsg);
                }
            }

            if (!clicked) {
                String failMsg = "❌ Failed to select Recipient Address 1 suggestion after " + maxRetries + " attempts for input: '" + reciaddress1 + "'";
                Allure.step(failMsg);
                throw new RuntimeException(failMsg);
            }

        } catch (Exception e) {
            throw new RuntimeException("🔴 Exception in SearchAndSelectReciAddress1: " + e.getMessage(), e);
        }
    }


    public void SearchAndSelectReciAddress1(String recipient_address1, String city, String state, String country) {
        By address_suggestion = By.xpath("//div[contains(@class,'pac-item')    and contains(.,'" + recipient_address1 + "')   and contains(.,'" + city + "')   and contains(.,'" + state + "')and contains(.,'" + country + "')]");
        try {
            wait_For_Page_To_Be_Stable(getDriver());
            recipientaddress1OnPhoneOrderPage.clear();
            delayWithGivenTime(500);
            recipientaddress1OnPhoneOrderPage.sendKeys(recipient_address1);
            delayWithGivenTime(2000);

            boolean clicked = false;
            int maxRetries = 3;

            for (int attempt = 1; attempt <= maxRetries; attempt++) {
                try {
                    System.out.println("🔄 Attempt " + attempt + ": Relocating address suggestions...");
                    WebElement suggestion = getDriver().findElement(address_suggestion);
                    Click(suggestion, "Recipient Address 1 autosuggestion (attempt " + attempt + ")");
                    String msg = "✅ Successfully clicked address suggestion for '" + recipient_address1 + "' on attempt " + attempt;
                    clicked = true;
                    break;

                } catch (StaleElementReferenceException | TimeoutException e) {
                    String msg = "⚠️ Attempt " + attempt + ": Element became stale or timed out. Retrying with relocated element...";
                    Allure.step(msg);
                    System.out.println(msg);
                    delayWithGivenTime(1000);
                } catch (Exception e) {
                    String msg = "❌ Unexpected error on attempt " + attempt + ": " + e.getMessage();
                    Allure.step(msg);
                    System.out.println(msg);
                    delayWithGivenTime(1000);
                }
            }

            if (!clicked) {
                String failMsg = "❌ Failed to click Recipient Address 1 suggestion after " + maxRetries + " attempts for input: '" + recipient_address1 + "'";
                Allure.step(failMsg);
                throw new RuntimeException(failMsg);
            }

        } catch (Exception e) {
            throw new RuntimeException("🔴 Exception occurred while interacting with Recipient Address 1 field: " + e.getMessage(), e);
        }
    }


    /**
     * Search and select with city state country on customer section in the phone order page
     *
     * @param cityStateCountry
     */
    public void searchAndSelect_Recipient_Address1_from_autocomplete(String reciaddress1, String cityStateCountry) {
        recipientaddress1OnPhoneOrderPage.clear();
        delayWithGivenTime(1000);
        recipientaddress1OnPhoneOrderPage.sendKeys(reciaddress1);
        delayWithGivenTime(2000);
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
            WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='pac-container pac-logo hdpi']//div[contains(@class, 'pac-item')]//span[text()='" + cityStateCountry + "']")));
            boolean addressFound = false;
            String fullText = suggestion.getText();
            if (fullText.contains(cityStateCountry)) {
                delayWithGivenTime(2000);
                js_Click(suggestion, "Auto-suggested recipient address 1 field in customer section");
                addressFound = true;
            }

            if (!addressFound) {
                throw new RuntimeException("No matching address found in recipient address 1 suggestions.");
            }

        } catch (
                Exception e) {
            throw new RuntimeException("Unable to search and select recipient address 1 on recipient section in the phone order page: " + e.getMessage(), e);
        }
    }


    /**
     * Search and select the recipient address 1 on customer section in the phone order page
     *
     * @param citystatecountry The city, state, and country part of the address
     * @param fulladdress      The full address to match and select
     * @Author Balaji N
     */
    public void Select_Recipient_Address1(String citystatecountry, String fulladdress) {
        Highlight_Element(address1_customersection_autosuggest_dropdown, "Address 1 textbox field autosuggest dropdown on recipient section in the phone order page");
        List<WebElement> addressOptions = getDriver().findElements(By.xpath("//div[@class='pac-container pac-logo hdpi']//div"));

        List<WebElement> street_Address = getDriver().findElements(By.xpath("//div[@class='pac-container pac-logo hdpi']//div/child::span[2]"));
        List<WebElement> city_State_Country = getDriver().findElements(By.xpath("//div[@class='pac-container pac-logo hdpi']//div/child::span[3]"));

        for (int i = 0; i < Math.min(street_Address.size(), city_State_Country.size()); i++) {
            String streetAddress = street_Address.get(i).getText().trim();
            String cityStateCountry = city_State_Country.get(i).getText().trim();

            String extractedFullAddress = streetAddress + " " + cityStateCountry;

            if (extractedFullAddress.equalsIgnoreCase(fulladdress) && cityStateCountry.equalsIgnoreCase(citystatecountry)) {
                Highlight_Element(city_State_Country.get(i), "Matching city-state-country in autosuggest dropdown");
                Actions action = new Actions(getDriver());
                action.moveToElement(city_State_Country.get(i)).click().perform();
                break;
            }
        }
    }


    /**
     * Clicks the recipient's address 1 on the phone order page
     *
     * @Author Balaji N
     */
    public void Click_ReciAddress1() {
        js_Click(recipientaddress1OnPhoneOrderPage, "Recipient Address 1 textbox field on phone order page");
        recipientaddress1OnPhoneOrderPage.sendKeys(Keys.TAB);
    }

    /**
     * Retrieves the address 1 field entered text from recipient section on the phone order page
     *
     * @return If text is exists it returns the displayed address 1 field as text from recipient section, otherwise it returns empty string if no text entered
     * @Description: This function will highlight the address 1 field and then, retrieves the entered address 1 field in recipient section.
     * @Author: Balaji N
     */
    public String getReciAddress1() {
        return getElementAttribute(recipientaddress1OnPhoneOrderPage, "Recipient Address 1 textbox field value on Phone Order Page");
       /* HighlightElement(recipientaddress1OnPhoneOrderPage);
        return recipientaddress1OnPhoneOrderPage.getAttribute("value");*/
    }

    /**
     * Clicks the address 2 field is displayed on the phone order page
     *
     * @Author Balaji N
     */
    public void Click_Address2_Field() {
        js_Click(recipientaddress2OnPhoneOrderPage, "Recipient Address 2 textbox field on phone order page");
    }

    /**
     * Entered the address 2 on Recipient section in the phone order page
     *
     * @param reciaddress2 The recipient's address 2 to be entered.
     * @Description: This method clears the existing data in the recipient address 2 field,
     * performs a double-click to select the field, and then enters the provided recipient address 2.
     * @Author: Balaji N
     */
    public void EnterReciAddress2(String reciaddress2) {
        jsClear(recipientaddress2OnPhoneOrderPage);
        Double_Click_And_Type(recipientaddress2OnPhoneOrderPage, reciaddress2, "Recipient Address 2 field textbox on phone order page");
    }

    /**
     * Retrieves the address 2 field entered text from recipient section on the phone order page
     *
     * @return If text is exists it returns the displayed address 2 field as text from recipient section, otherwise it returns empty string if no text entered
     * @Description: This function will highlight the address 2 field and then, retrieves the address 2 displayed text on recipient section.
     * @Author: Balaji N
     */
    public String getReciAddress2() {
        return getElementAttribute(recipientaddress2OnPhoneOrderPage, "Address 2 textbox field value on Recipient section in the Phone Order Page");
    }

    /**
     * Entered the zip code on Recipient section in the phone order page
     *
     * @param recizipcode The recipient's zip code to be entered.
     * @Description: This method clears the existing data in the recipient zipcode field,
     * performs a double-click to select the field, and then enters the provided recipient zipcode.
     * @Author: Balaji N
     */
    public void EnterReciZipcode(String recizipcode) {
        //DoubleClickAndType(recipientzipcodeOnPhoneOrderPage, recizipcode);
        recipientzipcodeOnPhoneOrderPage.clear();
        delayWithGivenTime(1500);
        Double_Click_And_Type(recipientzipcodeOnPhoneOrderPage, recizipcode, "Recipient Zipcode field textbox on phone order page");
    }

    public void Clear_Existing_Zipcode_Value_Recipient_Section() {
        jsClear(recipientzipcodeOnPhoneOrderPage);
    }

    /**
     * Retrieves the zip code field entered text from recipient section on the phone order page
     *
     * @return If text is exists it returns the displayed zip code field as text from recipient section, otherwise it returns empty string if no text entered
     * @Description: This function will highlight the zip code field and then, retrieves the displayed zip code text on recipient section.
     * @Author: Balaji N
     */
    public String getReciZipcode() {
        return getElementAttribute(recipientzipcodeOnPhoneOrderPage, "Zipcode textbox field value on Recipient section in the Phone order page");
        /*HighlightElement(recipientzipcodeOnPhoneOrderPage);
        return recipientzipcodeOnPhoneOrderPage.getAttribute("value");*/
    }

    public void Clear_Existing_City_Value_Recipient_Section() {
        jsClear(recipientcityOnPhoneOrderPage);
    }

    /**
     * Enter the city on Recipient section in the phone order page
     *
     * @param recicity The recipient's city to be entered.
     * @Author Balaji N
     */
    public void EnterReciCity(String recicity) {
        recipientcityOnPhoneOrderPage.clear();
        delayWithGivenTime(1000);
        recipientcityOnPhoneOrderPage.clear();
        ClickAndType(recipientcityOnPhoneOrderPage, recicity, "Recipient City field textbox on phone order page");
    }

    /**
     * Retrieves the city field entered text from recipient section on the phone order page
     *
     * @return If text is exists it returns the displayed city field as text from recipient section, otherwise it returns empty string if no text entered
     * @Description: This function will highlight the city field and then, retrieves the city displayed text on recipient section.
     * @Author: Balaji N
     */
    public String getReciCity() {
        return getElementAttribute(recipientcityOnPhoneOrderPage, "City textbox field value on Recipient section in the Phone Order Page");
    }

    /**
     * Selects the country from a dropdown field on recipient section in the phone order page.
     *
     * @param recicountry the visible text of the country to be selected from the dropdown.
     * @Description: This function using the reusable method of dropDown perform to select the country on recipient section in the phone order page
     * @Author: Balaji N
     */
    public void SelectReciCountry(String recicountry) {
        drop_Down(recipientcountryOnPhoneOrderPage, recicountry, "VisibleText", "Recipient Country dropdown field on phone order page");
    }

    /**
     * It retrieves the selected country from a dropdown field on recipient section in the phone order page.
     *
     * @return It returns the selected country from dropdown field on recipient section otherwise it returns default values
     * @Description: This function will retrieves selected country drop down field on recipient section in the phone order page
     * @Author: Balaji N
     */
    public String getSelectedCountryOnReciCountry() {
        return get_Selected_Option(recipientcountryOnPhoneOrderPage, "Select Country dropdown field on Recipient Section - phone order page");
    }

    /**
     * Entered the phone number 1 field on Recipient section in the phone order page
     *
     * @param reciphone The recipient's phone number 1 field to be entered.
     * @Description: This method clears the existing data in the recipient phone number 1 field,
     * performs a click action to the field, and then enters the provided recipient phone number 1 field.
     * @Author: Balaji N
     */
    public void EnterReciPhone(String reciphone) {
        recipientphoneOnPhoneOrderPage.clear();
        delayWithGivenTime(1000);
        ClickAndType(recipientphoneOnPhoneOrderPage, reciphone, "Recipient phone number 1 textbox field on phone order page");
    }

    /**
     * Retrieves the phone number 1 field entered text from recipient section on the phone order page
     *
     * @return If text is exists it returns the displayed phone number 1 field as text from recipient section, otherwise it returns empty string if no text entered
     * @Description: This function will highlight the phone number field and then, retrieves the phone number displayed text on recipient section.
     * @Author: Balaji N
     */
    public String getReciPhone() {
        return getElementAttribute(recipientphoneOnPhoneOrderPage, "Phone Number 1 textbox field value on recipient section in the Phone Order Page");
    }

    /**
     * Selects the location from a dropdown field on recipient section in the phone order page.
     *
     * @param recilocation the visible text of the location to be selected from the dropdown.
     * @Description: This function using the reusable method of dropDown perform to select the location on recipient section in the phone order page
     * @Author: Balaji N
     */
    public void SelectReciLocation(String recilocation) {
        drop_Down(recipientlocationOnPhoneOrderPage, recilocation, "VisibleText", "Recipient Location dropdown field on phone order page");
    }

    /**
     * It retrieves the selected location from a dropdown field on recipient section in the phone order page.
     *
     * @return It returns the selected location from dropdown field on recipient section otherwise it returns default values
     * @Description: This function will retrieves selected location drop down field on recipient section in the phone order page
     * @Author: Balaji N
     */
    public String getSelectedLocationOnReciLocation() {
        return get_Selected_Option(recipientlocationOnPhoneOrderPage, "Select Location dropdown field on Recipient Section - phone order page");
    }

    /**
     * Enters the current date as the delivery date in the recipient section.
     * <p>
     * This method fetches the current date, formats it in MM/dd/yyyy format, and inputs it into the recipient delivery
     * date field using the {@code jsClearAndType} method.
     * </p>
     *
     * @throws NullPointerException            if {@code recipientDeliverydateOnPhoneOrderPage} is null.
     * @throws DateTimeException               if an error occurs while fetching or formatting the date.
     * @throws WebDriverException              if Selenium encounters an issue interacting with the web element.
     * @throws ElementNotInteractableException if the input field is not interactable.
     */
    public void Enter_DeliveryDate_On_ReciSection() {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate nextDay = currentDate.plusDays(0);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String formattedNextDay = nextDay.format(formatter);

            js_Clear_And_Type(recipientDeliverydateOnPhoneOrderPage, formattedNextDay, "Delivery date datepicker field on Recipient Section in the phone order page"); // DoubleClickAndType();
        } catch (NullPointerException e) {
            System.err.println("Error: Element not found or is null - " + e.getMessage());
        } catch (DateTimeException e) {
            System.err.println("Error: Invalid date processing - " + e.getMessage());
        } catch (WebDriverException e) {
            System.err.println("Error: WebDriver interaction failed - " + e.getMessage());
        }
    }


    /**
     * Enters the next day's date as the delivery date in the recipient section.
     * <p>
     * This method fetches the current date, adds one day to it, formats it in MM/dd/yyyy format, and inputs it into
     * the recipient delivery date field using the {@code jsClearAndType} method.
     * </p>
     *
     * @throws NullPointerException            if {@code recipientDeliverydateOnPhoneOrderPage} is null.
     * @throws DateTimeException               if an error occurs while fetching or formatting the date.
     * @throws WebDriverException              if Selenium encounters an issue interacting with the web element.
     * @throws ElementNotInteractableException if the input field is not interactable.
     */
    public void EnterDeliveryDateOnReciSection() {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate nextDay = currentDate.plusDays(1);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String formattedNextDay = nextDay.format(formatter);

            js_Clear_And_Type(recipientDeliverydateOnPhoneOrderPage, formattedNextDay, "Delivery date datepicker field on Recipient Section in the phone order page"); // DoubleClickAndType();
        } catch (NullPointerException e) {
            System.err.println("Error: Element not found or is null - " + e.getMessage());
        } catch (DateTimeException e) {
            System.err.println("Error: Invalid date processing - " + e.getMessage());
        } catch (WebDriverException e) {
            System.err.println("Error: WebDriver interaction failed - " + e.getMessage());
        }
    }

    /**
     * Entered the delivery date field on Recipient section in the phone order page
     *
     * @param date The recipient's delivery date to be entered.
     * @Description: This function clears the existing data in the recipient delivery date field,
     * using reusable method from testbaseclass a javascript & clear action to the field, and then enters the provided recipient delivery date field.
     * @Author: Balaji N
     */
    public void EnterDeliveryDateOnReciSection(String date) {
        js_Clear_And_Type(recipientDeliverydateOnPhoneOrderPage, date, "Delivery Date field on Recipient Section in the phone order page");
    }

    /**
     * Retrieves the delivery date field entered text from recipient section on the phone order page
     *
     * @return If text is exists it returns the displayed delivery date field as text from recipient section, otherwise it returns empty string if no text entered
     * @Description: This function will highlight the delivery date field and then, retrieves the delivery date displayed text on recipient section.
     * @Author: Balaji N
     */
    public String getDeliveryDateOnReciSection() {
        return get_element_attribute(recipientDeliverydateOnPhoneOrderPage, "Recipient Delivery Date datepickerfield on phone order page");
    }

    public void ClickViewShortCode() {
        jsClick(viewShortcodesOnPhoneOrderPage);
    }

// Function used for card message textbox to pass as input test data

    /**
     * Enters a view shortcode in the textbox on the Phone Order page and selects the appropriate option from the list.
     * This method first clears the textbox, enters "@HB", waits for suggestions, and selects "Happy Birthday!" if found.
     *
     * @throws NoSuchElementException          if the textbox or shortcodes list is not found
     * @throws ElementNotInteractableException if the textbox or shortcode list is present but not interactable
     * @throws TimeoutException                if the elements are not available within the expected time
     * @throws StaleElementReferenceException  if the elements become detached from the DOM before interaction
     * @throws WebDriverException              for any other WebDriver-related exceptions
     * @author Balaji N
     */
    public void EnterViewShortCode() {
        try {
            viewShortcodeTextboxOnPhoneOrderPage.clear();
            delayWithGivenTime(1000);

            viewShortcodeTextboxOnPhoneOrderPage.sendKeys("@");
            delayWithGivenTime(1000);

            viewShortcodeTextboxOnPhoneOrderPage.sendKeys("HB");
            delayWithGivenTime(1000);

            for (WebElement viewShortCodeElement : listOfShortcodesOnPhoneOrderPage) {
                if (viewShortCodeElement.getText().contains("Happy Birthday! Hope You Have An Amazing Day!")) {
                    viewShortCodeElement.click();
                    return;
                }
            }
        } catch (NoSuchElementException e) {
            printError(viewShortcodeTextboxOnPhoneOrderPage, "View Shortcode Textbox", "NoSuchElementException", e);
            throw e;
        } catch (ElementNotInteractableException e) {
            printError(viewShortcodeTextboxOnPhoneOrderPage, "View Shortcode Textbox", "ElementNotInteractableException", e);
            throw e;
        } catch (TimeoutException e) {
            printError(viewShortcodeTextboxOnPhoneOrderPage, "View Shortcode Textbox", "TimeoutException", e);
            throw e;
        } catch (StaleElementReferenceException e) {
            printError(viewShortcodeTextboxOnPhoneOrderPage, "View Shortcode Textbox", "StaleElementReferenceException", e);
            throw e;
        } catch (WebDriverException e) {
            printError(viewShortcodeTextboxOnPhoneOrderPage, "View Shortcode Textbox", "WebDriverException", e);
            throw e;
        }
    }


    /**
     * Enter the card message on occasion details section
     *
     * @param cardmessage The card message to be entered on occasion details
     * @Description: This function clears the existing data and provided card message to be entered
     * @Author Balaji N
     */
    public void Enter_CardMessage_OnOccasion_Details_Section(String cardmessage) {
        viewShortcodeTextboxOnPhoneOrderPage.clear();
        delayWithGivenTime(1000);
        viewShortcodeTextboxOnPhoneOrderPage.sendKeys(cardmessage);
        delayWithGivenTime(1000);
    }

    /**
     * Search and Select the card message textbox on order details section in the phone order page
     *
     * @param shortcode Short code is used to search the card message
     * @param value     Expected Card message value to be selected
     * @Description: This function clear the existing data on card message textbox and then search with short code text and select the expected card message on order details section phone order page
     * @Author: Balaji N
     */
    public void EnterViewShortCode(String shortcode, String value) {
        try {
            viewShortcodeTextboxOnPhoneOrderPage.clear();
            delayWithGivenTime(1000);
            viewShortcodeTextboxOnPhoneOrderPage.sendKeys("@");
            delayWithGivenTime(1000);
            viewShortcodeTextboxOnPhoneOrderPage.sendKeys(shortcode);

            // Wait for the shortcodes list to load properly
            //   By cardmsg = By.xpath("//ul[@id='textcomplete-dropdown-1']//li//a//div//span[text()='" + value + "']");
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfAllElements(listOfShortcodesOnPhoneOrderPage));
            delayWithGivenTime(2000);


            for (int i = 0; i < listOfShortcodesOnPhoneOrderPage.size(); i++) {
                if (listOfShortcodesOnPhoneOrderPage.get(i).getText().contains(value)) {
                    listOfShortcodesOnPhoneOrderPage.get(i).click();
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            printError(viewShortcodeTextboxOnPhoneOrderPage, "View Shortcode Textbox", "NoSuchElementException", e);
            throw e;
        } catch (ElementNotInteractableException e) {
            printError(viewShortcodeTextboxOnPhoneOrderPage, "View Shortcode Textbox", "ElementNotInteractableException", e);
            throw e;
        } catch (TimeoutException e) {
            printError(viewShortcodeTextboxOnPhoneOrderPage, "View Shortcode Textbox", "TimeoutException", e);
            throw e;
        } catch (StaleElementReferenceException e) {
            printError(viewShortcodeTextboxOnPhoneOrderPage, "View Shortcode Textbox", "StaleElementReferenceException", e);
            throw e;
        } catch (WebDriverException e) {
            printError(viewShortcodeTextboxOnPhoneOrderPage, "View Shortcode Textbox", "WebDriverException", e);
            throw e;
        }
    }

    /**
     * It will clear the view short code card message data on order details section
     *
     * @Author Balaji N
     */
    public void Clear_ViewShortCode_Textbox_On_OrderDetails() {
        try {
            viewShortcodeTextboxOnPhoneOrderPage.clear();
        } catch (Exception e) {
            printError(viewShortcodeTextboxOnPhoneOrderPage, "View Shortcode Textbox", "Generic exception", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * It retrieve the entered view short code card message data on order details section
     *
     * @return it will return the string of view short code card message data on order details section. Otherwise it returns the empty string if no data entered.
     * @Description: This function will highlight the card message textbox field, and then retrieve the displayed card message on order details in the phone order page.
     * @Author: Balaji N
     */
    public String getEnteredViewShortCode() {
        return get_element_attribute_with_trim(viewShortcodeTextboxOnPhoneOrderPage, "Card Message textbox field value on order details section in the phone order page");
       /* HighlightElement(viewShortcodeTextboxOnPhoneOrderPage);
        return viewShortcodeTextboxOnPhoneOrderPage.getAttribute("value").trim();*/
    }


    /**
     * This function will enter the price data on row 1 product details section
     * It will clear the existing data & enter the item price in textbox field
     */
    public void Enter_Product_Price_Row1(String price) {
        prod_details_Unitprice1.clear();
        clickAndType(prod_details_Unitprice1, price);
    }

    /**
     * It retrieves the unit price on product details in row 1
     *
     * @return If unit price is displayed on row 1 of the product details it returns the value of unit price, otherwise it returns null string
     * @Description: This function will highlight the unit price field, and then retrieve the unit price on product details in row 1
     * @author: Balaji N
     */
    public String getUnitPriceOnProdDetails() {
        return getElementAttribute(prod_details_Unitprice1, "Unit Price row1 textbox field value on product details section in the phone order page");
    }

    /**
     * It retrieves the unit price on product details in row 2
     *
     * @return If unit price is displayed on row 2 of the product details it returns the value of unit price, otherwise it returns null string
     * @Description: This function will highlight the unit price field, and then retrieve the unit price on product details in row 2
     * @author: Balaji N
     */
    public String getUnitPrice2OnProdDetails() {
        return get_element_attribute(prod_details_Unitprice2, "Unit Price row2 textbox field value on product details section in the phone order page");
        // return prod_details_Unitprice2.getAttribute("value");
    }

    /**
     * It retrieves the extent price on product details in row 1
     *
     * @return If unit price is displayed on row 1 of the product details it returns the value of ext price, otherwise it returns null string
     * @Description: This function will highlight the extent price field, and then retrieve the ext price on product details in row 1
     * @author: Balaji N
     */
    public String get_ExtPrice1OnProdDetails() {
        return getElementAttribute(prod_details_Extendedprice1, "Extent Price row1 textbox field value on product details section in the phone order page");
    }

    /**
     * It retrieves the extent price on product details in row 2
     *
     * @return If extent price is displayed on row 2 of the product details it returns the value of ext price, otherwise it returns null string
     * @Description: This function will highlight the extent price field, and then retrieve the ext price on product details in row 2
     * @author: Balaji N
     */
    public String get_ExtPrice2OnProdDetails() {
        return get_element_attribute(prod_details_Extendedprice2, "Extent Price row2 textbox field value on product details section in the phone order page");
        //  return prod_details_Extendedprice2.getAttribute("value");
    }

    public boolean validateItemDetails(String expectedDescription, String expectedPrice, String expectedExtPrice, String expectedDiscountAmt, String expectedDiscountPercentage) {

        WebElement itemTable = productDetailGrid;
        List<WebElement> rows = itemTable.findElements(By.tagName("tr"));

        for (WebElement row : rows) {

            List<WebElement> cells = row.findElements(By.tagName("td"));

            String itemDescription = cells.get(1).getText();

            String itemPrice = cells.get(2).getText();

            String itemExtPrice = cells.get(3).getText();

            String itemDiscountAmt = cells.get(4).getText();

            String itemDiscountPercentage = cells.get(5).getText();

            if (itemDescription.equals(expectedDescription)) {
                Assert.assertEquals(itemDescription, expectedDescription, "Description does not match");
                Assert.assertEquals(itemPrice, expectedPrice, "Price does not match");
                Assert.assertEquals(itemExtPrice, expectedExtPrice, "Extended Price does not match");
                Assert.assertEquals(itemDiscountAmt, expectedDiscountAmt, "Discount Amount does not match");
                Assert.assertEquals(itemDiscountPercentage, expectedDiscountPercentage, "Discount Percentage does not match");
                break;
            }
        }
        return true;
    }

    public String getPayableAmount() {
        return payableBalanceOnPhoneOrderPage.getAttribute("value");
    }

    /**
     * It retrieves the grand total amount value from phone order page
     *
     * @return If grand total amount is displayed it returns the value of grand total, otherwise returns empty string
     * @Description: This function retrieves the grand total amount and trimming the unwanted space at the end
     * @Author Balaji N
     */
    public String getGrandTotalAmount() {
        //return get_element_attribute_with_trim(grandTotalOnPhoneOrderPage, "Grand Total Amount textbox field value on payment section in the phone order page");

        String amountText = get_element_attribute_with_trim(
                grandTotalOnPhoneOrderPage,
                "Grand Total Amount textbox field value on payment section in the phone order page"
        );

        try {
            double amount = Double.parseDouble(amountText.replaceAll("[^0-9.]", "")); // Remove currency symbols, commas, etc.
            return String.format("%.2f", amount); // Always 2 decimal places
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid grand total amount format: " + amountText, e);
        }
    }

    public void click_GrandTotalAmount() {
        js_Click(grandTotalOnPhoneOrderPage, "Grand Total Amount button on payment section in the phone order page");
    }

    /**
     * Retrieves the grand total amount from the payment section on the phone order page
     * and returns it with one decimal place.
     *
     * @return The grand total amount formatted to one decimal place.
     * @throws NumberFormatException If the retrieved value is not a valid number.
     */
    public String get_GrandTotal_Amount_with_single_Decimal() {
        String amountText = get_element_attribute_with_trim(grandTotalOnPhoneOrderPage,
                "Grand Total Amount textbox field value on payment section in the phone order page");

        try {
            double amount = Double.parseDouble(amountText); // Convert to double
            return String.format("%.2f", amount); // Format to one decimal place
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid grand total amount format: " + amountText);
        }
    }


    /**
     * Enter the cash amount on cash received field for cash payment type on payment section
     *
     * @Description: This function will double click to select the existing data, and then provided data to be entered.
     * getGrandTotalAmount function is used as parameter directly into this function
     * @Author: Balaji N
     */
    public void EnterCashAmount() {
        // Calculate the new payment amount
        double grandTotal = Double.parseDouble(getGrandTotalAmount());
        double current_balance = grandTotal;
        String Morebalance = Double.toString(current_balance);

        // Click to focus the field first
        paidAmountOnPhoneOrderPage.click();

        // First clear the field using Selenium `.clear()`
        paidAmountOnPhoneOrderPage.clear();

        // Then send BACKSPACE multiple times to ensure the field is empty
        String existingValue = paidAmountOnPhoneOrderPage.getAttribute("value");
        if (existingValue != null && !existingValue.isEmpty()) {
            for (int i = 0; i < existingValue.length(); i++) {
                paidAmountOnPhoneOrderPage.sendKeys(Keys.BACK_SPACE);
            }
        }

        // Small delay to ensure field is fully cleared
        delayWithGivenTime(500);

        // Type the new balance into the field — using your custom double-click method
        Double_Click_And_Type(
                paidAmountOnPhoneOrderPage,
                Morebalance,
                "Cash Amount textbox field on (Cash Type) - payment section in the phone order page"
        );
    }

    /**
     * Enter the cash amount on cash received field for cash payment type on payment section
     *
     * @Description: This function will double click to select the existing data, and then provided data to be entered.
     * getGrandTotalAmount function is used as parameter directly into this function
     * @Author: Balaji N
     */
    public void EnterCashAmount(String cashamount) {
        Double_Click_And_Type(paidAmountOnPhoneOrderPage, cashamount, "Cash Received textbox field on (Cash Type) - payment section  in the phone order page");
    }

    public void Enter_Cash_Amount(String cashamount) {
        DoubleClickAndType(paidAmountOnPhoneOrderPage, getGrandTotalAmount());
        paidAmountOnPhoneOrderPage.sendKeys(Keys.TAB);
    }

    /**
     * Enter the cash amount more than pay amount
     *
     * @Description: This function will convert the grand total amount into double format, and then we can able to add the amount value of 100 (used for providing more than pay amount).
     * Now clear the paid amount field and perform the double click action to select the existing data and entered the provided more than pay amount in the field
     * @Author: Balaji N
     */
//    public void Enter_CashPaymentType_Amount() {
//        double grandTotal = Double.parseDouble(getGrandTotalAmount());
//        double current_balance = grandTotal + 100;
//        String Morebalance = Double.toString(current_balance);
//        paidAmountOnPhoneOrderPage.clear();
//        delayWithGivenTime(1000);
//        Double_Click_And_Type(paidAmountOnPhoneOrderPage, Morebalance, "Cash Amount textbox field on (Cash Type) - payment section  in the phone order page");
//    }
    public void Enter_CashPaymentType_Amount() {
        // Calculate the new payment amount
        double grandTotal = Double.parseDouble(getGrandTotalAmount());
        double current_balance = grandTotal + 100;
        String Morebalance = Double.toString(current_balance);

        // Click to focus the field first
        paidAmountOnPhoneOrderPage.click();

        // First clear the field using Selenium `.clear()`
        paidAmountOnPhoneOrderPage.clear();

        // Then send BACKSPACE multiple times to ensure the field is empty
        String existingValue = paidAmountOnPhoneOrderPage.getAttribute("value");
        if (existingValue != null && !existingValue.isEmpty()) {
            for (int i = 0; i < existingValue.length(); i++) {
                paidAmountOnPhoneOrderPage.sendKeys(Keys.BACK_SPACE);
            }
        }

        // Small delay to ensure field is fully cleared
        delayWithGivenTime(500);

        // Type the new balance into the field — using your custom double-click method
        Double_Click_And_Type(
                paidAmountOnPhoneOrderPage,
                Morebalance,
                "Cash Amount textbox field on (Cash Type) - payment section in the phone order page"
        );
    }

    public void enter_CashPaymentType_Amount() {
        // Calculate the new payment amount
        double grandTotal = Double.parseDouble(getGrandTotalAmount());
        double current_balance = grandTotal;
        String Morebalance = Double.toString(current_balance);

        // Click to focus the field first
        paidAmountOnPhoneOrderPage.click();

        // First clear the field using Selenium `.clear()`
        paidAmountOnPhoneOrderPage.clear();

        // Then send BACKSPACE multiple times to ensure the field is empty
        String existingValue = paidAmountOnPhoneOrderPage.getAttribute("value");
        if (existingValue != null && !existingValue.isEmpty()) {
            for (int i = 0; i < existingValue.length(); i++) {
                paidAmountOnPhoneOrderPage.sendKeys(Keys.BACK_SPACE);
            }
        }

        // Small delay to ensure field is fully cleared
        delayWithGivenTime(500);

        // Type the new balance into the field — using your custom double-click method
        Double_Click_And_Type(
                paidAmountOnPhoneOrderPage,
                Morebalance,
                "Cash Amount textbox field on (Cash Type) - payment section in the phone order page"
        );
    }


    /**
     * Retrieves the entered cash amount on cash received field for cash payment type on payment section
     *
     * @return If the entered cash amount is displayed it returns the value of entered cash amount, otherwise returns empty string
     * @Author Balaji N
     */
    public String get_Entered_CashPaymentType_Amount() {
        return get_element_attribute_with_trim(paidAmountOnPhoneOrderPage, "Cash Received textbox field on (Cash Type) - payment section  in the phone order page");
    }

    public String get_MoreThanPayableAmount() {
        double grandTotal = Double.parseDouble(get_Entered_CashPaymentType_Amount());
        double current_balance = grandTotal;
        return Double.toString(current_balance);
    }

    /**
     * Select the Cash Registry on Cash Payment Type on payment section
     *
     * @param cashregistry - Cash Registry to be selected
     * @Author Balaji N
     */
    public void SelectCashRegistry_On_CashPaymentType(String cashregistry) {
        drop_Down(cashRegisterDDOnPhoneOrderPage, cashregistry, "VisibleText", "Cash Registry dropdown field on payment section in the phone order page");
    }

    /**
     * It retrieves the selected cash registry from a dropdown field on payment section in the phone order page.
     *
     * @return If the cash registry is selected, returns the selected cash registry; otherwise, returns null
     * @Author Balaji N
     */
    public String get_Selected_CashRegistry() {
        return get_selected_option(cashRegisterDDOnPhoneOrderPage, "Cash Registry dropdown field value on payment section in the phone order page");
    }

    /**
     * Clicks on the place order button on phone order page
     *
     * @Description: This function will click on the place order button using jsclick function
     * @Author: Balaji N
     */
    public void ClickPlaceOrderButton() {
        js_Click(placeOrderButtonOnPhoneOrderPage, "Place Order Button on payment section in the phone order page");
    }

    /**
     * Clicks on the place order button on phone order page
     *
     * @Description: This function will click on the place order button using selenium click function
     * @Author: Balaji N
     */
    public void Click_PlaceOrder_Button() {
        placeOrderButtonOnPhoneOrderPage.click();
    }

    /**
     * Validates whether the confirmation popup appears on phone order page
     *
     * @return If confirmation popup is displayed it returns true, otherwise it returns false
     * @Description: This function will validate whether the confirmation popup appears on phone order page
     * @Author: Balaji N
     */
    public boolean VerifyConfirmationPopupOnPhoneOrderPage() {
        try {
            isElementDisplayed(order_confirmation_Popup_On_PhoneOrderPage, "Order Confirmation Popup on phone order page");
            return is_Element_Displayed(confirmationPopupTitleOnPhoneOrderPage, "Order Confirmation Popup on phone order page after clicks the place order button");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("After clicks on Placed Order button on phone order page - Confirmation popup is not displayed", e);
        }
    }

    /**
     * Click the Cancel button on confirmation popup in the phone order page
     *
     * @Description: This function will click on the cancel button in confirmation popup
     * @Author Balaji N
     */
    public void ClickCancelButton_On_ConfirmationPopup() {
        js_Click(cancelButtonOnPhoneOrderPage, "Cancel button on confirmation popup");
    }

    /**
     * Clicks on the submit button in confirmation popup on phone order page
     *
     * @Description: This function will click on the submit button in confirmation popup on phone order page
     * @Author: Balaji N
     */
    public void ClickSubmitButton_On_ConfirmationPopup() {
        PageLoadLoggerUtils.logPageLoad("Phone Order Page → Order Confirmation Page", () -> {
            js_Click(submitOrderButtonOnPhoneOrderPage, "Submit button on confirmation popup in the phone order page");
            wait_For_Page_To_Be_Stable(getDriver());
        });
    }

    /**
     * Validate whether the New customer label is displayed on phone order page
     *
     * @return If new customer label is displayed it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_NewCustomer_Label_Appears() {
        return is_Element_Displayed(newcustomerlabel, "New Customer Label on phone order page");
    }

    /**
     * Verify whether the customer section appears on phone order page
     *
     * @return If customer section is displayed it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean VerifyCustSectionAppears() {
        return is_Element_Displayed(custSectionOnPhoneOrderPage, "Customer Section on phone order page");
       /* HighlightElement(custSectionOnPhoneOrderPage);
        return custSectionOnPhoneOrderPage.isDisplayed();*/
    }

    /**
     * Verify whether the customer clear button appears on customer section on phone order page
     *
     * @return If customer clear button is displayed it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_CustClearBtn_Appears() {
       /* HighlightElement(clearCustInfoBtnOnPhoneOrderPage);
        return clearCustInfoBtnOnPhoneOrderPage.isDisplayed();*/
        return is_Element_Displayed(clearCustInfoBtnOnPhoneOrderPage, "Customer Clear Button on customer section on phone order page");
    }

    /**
     * Clicks the clear button on customer section on phone order page
     *
     * @Author Balaji N
     */
    public void ClickOn_CustClearBtn() {
        js_Click(clearCustInfoBtnOnPhoneOrderPage, "Clear button on customer section on phone order page");
    }

    /**
     * Verify the google map icon is not displayed on customer section on phone order page
     *
     * @return If google map icon is not displayed it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean VerifyGoogleMap_Address1_CustSection() {
        try {
            HighlightElement(listOfAddress1SuggestionsOnCustSection.get(0));
            return listOfAddress1SuggestionsOnCustSection.get(0).getAttribute("style").contains("none");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifies whether the auto-suggestion list appears in the Customer First Name section.
     *
     * @return {@code true} if the auto-suggestion list is hidden (style contains "none"), otherwise {@code false}.
     * @Author Balaji N
     */
    public boolean Verify_CustFirstNameSection_AutosuggestionAppears() {
        try {
            String styleAttribute = cust_FirstnameUnorderListOnCustSection.getAttribute("style");
            if (styleAttribute == null || !styleAttribute.contains("none")) { //
                return true;
            }
        } catch (NullPointerException e) {
            printError(cust_FirstnameUnorderListOnCustSection, "Customer First Name Auto-suggestion List", "NullPointerException", e);
        } catch (StaleElementReferenceException e) {
            printError(cust_FirstnameUnorderListOnCustSection, "Customer First Name Auto-suggestion List", "StaleElementReferenceException", e);
        } catch (NoSuchElementException e) {
            printError(cust_FirstnameUnorderListOnCustSection, "Customer First Name Auto-suggestion List", "NoSuchElementException", e);
        } catch (Exception e) {
            printError(cust_FirstnameUnorderListOnCustSection, "Customer First Name Auto-suggestion List", "Generic Exception", e);
        }
        return false;
    }

    /**
     * Search and select the first name on customer section
     *
     * @param firstname the first name to be searched
     * @param fullname  the full name to be selected
     * @Author: Balaji N
     */
    public void SearchAndSelect_FirstnameOnCustSection(String firstname, String fullname) {
      /*
         firstnameOnPhoneOrderPage.clear();
        delayWithGivenTime(500);
       DoubleClickAndType(firstnameOnPhoneOrderPage, firstname);
        delayWithGivenTime(2000);
        firstnameOnPhoneOrderPage.sendKeys(Keys.DOWN);
            firstnameOnPhoneOrderPage.sendKeys(Keys.ENTER);

        for (WebElement customerfnameElement : listOfFirstNamesOnCustSection) {
            if (customerfnameElement.getText().contains(fullname)) {
                customerfnameElement.click();
                break;
            }
        }*/

        try {
            firstnameOnPhoneOrderPage.clear();
            delayWithGivenTime(500);
            ClickAndType(firstnameOnPhoneOrderPage, firstname, "First Name textbox field on customer section in the phone order page");
            delayWithGivenTime(2000);
            boolean customerfound = false;

            // Check if the autosuggestion element is displayed
            if (is_Element_Displayed(cust_FirstnameUnorderListOnCustSection, "First Name textbox field on autosugestion on customer section in the phone order page") == true) {
                try {
                    By customerEle = By.xpath("//ul[@id='ui-id-5']//li//div[contains(text(),'" + fullname + "')]");
                    // Locate the item based on description and click  // ui-id-4 - previous one
                    WebElement customer = getDriver().findElement(customerEle);
                    js_Click(customer, "First name textbox field on autosuggestion on customer section in the phone order page");
                    customerfound = true;
                } catch (NoSuchElementException e) {
                    // Handle scenario where item is not found in the list
                    System.err.println("Entered Customer name: " + fullname + " was not found in the autosuggestion list.");
                } catch (ElementNotInteractableException e) {
                    // Handle case where element is found but not interactable
                    System.err.println("The customer " + fullname + " is not interactable.");
                } catch (Exception e) {
                    // Handle any other unexpected exceptions
                    System.err.println("An unexpected error occurred while selecting the customer: " + e.getMessage());
                }
            } else {
                System.err.println("Autosuggestion of customer name entered is not displayed or not interactable.");
            }

            if (!customerfound) {
                System.out.println("Entered Customer Name : " + fullname + " not found.");
            }

        } catch (Exception e) {
            System.err.println("An error occurred during the search and select process: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Search and select the last name on customer section in the phone order page
     *
     * @param lastname Last Name to be searched
     * @param fullname Last Name to be selected
     * @Author Balaji N
     */
    public void SearchAndSelect_LastnameOnCustSection(String lastname, String fullname) {
        lastnameOnPhoneOrderPage.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(lastnameOnPhoneOrderPage, lastname, "Last Name textbox field on customer section in the phone order page");
        delayWithGivenTime(2000);
        if(getElementText(customernameFromAutoSuggestion,"Autosuggestion option on customer section").contains(fullname)){
            js_Click(customernameFromAutoSuggestion,"Autosuggestion option on customer section");
        }

//        for (WebElement customerlastnameElement : listOfLastNamesOnCustSection) {
//            if (customerlastnameElement.getText().contains(fullname)) {
//                customerlastnameElement.click();
//                break;
//            }
//        }




    }

    /**
     * Verifies whether the auto-suggestion list appears in the Customer Last Name section.
     *
     * @return {@code true} if the customer last name auto-suggestion list is hidden (style contains "none"), otherwise {@code false}.
     * @Author Balaji N
     */
    public boolean Verify_CustLastNameSection_AutosuggestionAppears() {
        try {
           /* String styleAttribute = cust_LastnameUnorderListOnCustSection.getAttribute("style");
            if (styleAttribute == null || !styleAttribute.contains("none")) {
                return true;
            }*/
            return is_Element_Displayed(cust_LastnameUnorderListOnCustSection, "Customer section - Last Name textbox field autosuggestion list");
        } catch (NullPointerException e) {
            printError(cust_LastnameUnorderListOnCustSection, "Customer Last Name Auto-suggestion List", "NullPointerException", e);
        } catch (StaleElementReferenceException e) {
            printError(cust_LastnameUnorderListOnCustSection, "Customer Last Name Auto-suggestion List", "StaleElementReferenceException", e);
        } catch (NoSuchElementException e) {
            printError(cust_LastnameUnorderListOnCustSection, "Customer Last Name Auto-suggestion List", "NoSuchElementException", e);
        } catch (Exception e) {
            printError(cust_LastnameUnorderListOnCustSection, "Customer Last Name Auto-suggestion List", "Generic Exception", e);
        }
        return false;
    }

    /**
     * Search and select the company name on customer section
     *
     * @param cmpyname     the company name to be searched
     * @param fullcmpyname the full company name to be selected
     * @Author Balaji N
     */
    public void SearchAndSelect_CompanynameOnCustSection(String cmpyname, String fullcmpyname) {
        companyNameOnPhoneOrderPage.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(companyNameOnPhoneOrderPage, cmpyname, "Company Name Textbox field on customer section in the phone order");
        delayWithGivenTime(2000);
        for (WebElement customercmpynameElement : listOfCompanyNamesOnCustSection) {
            if (customercmpynameElement.getText().contains(fullcmpyname)) {
                Click(customercmpynameElement, "Company Name autosugesstion value on customer section in the phone order page");
                break;
            }
        }
    }


    /**
     * Verifies whether the auto-suggestion list appears in the Customer Company Name section.
     *
     * @return If the customer company name auto-suggestion list is hidden (style contains "none"), returns {@code true}; otherwise, returns {@code false}.
     * @Author Balaji N
     */
    public boolean Verify_CustCompanyNameSection_AutosuggestionAppears() {
        try {
            String styleAttribute = cust_companyNameUnorderListOnCustSection.getAttribute("style");
            if (styleAttribute == null || !styleAttribute.contains("none")) {
                return true;
            }
        } catch (NullPointerException e) {
            printError(cust_companyNameUnorderListOnCustSection, "Customer Company Name Auto-suggestion List", "NullPointerException", e);
        } catch (StaleElementReferenceException e) {
            printError(cust_companyNameUnorderListOnCustSection, "Customer Company Name Auto-suggestion List", "StaleElementReferenceException", e);
        } catch (NoSuchElementException e) {
            printError(cust_companyNameUnorderListOnCustSection, "Customer Company Name Auto-suggestion List", "NoSuchElementException", e);
        } catch (Exception e) {
            printError(cust_companyNameUnorderListOnCustSection, "Customer Company Name Auto-suggestion List", "Generic Exception", e);
        }
        return false;
    }

    /**
     * Search and Select the phone number on customer section in the phone order page
     *
     * @param phonenum     the phone number to be searched
     * @param fullphonenum the full phone number to be selected
     * @Author Balaji N
     */
    public void SearchAndSelect_phonenumberOnCustSection(String phonenum, String fullphonenum) {
        phoneNumOnPhoneOrderPage.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(phoneNumOnPhoneOrderPage, phonenum, "Phone number textbox field on customer section in the phone order page");
        delayWithGivenTime(2000);
        for (int i = 0; i < listOfPhoneNumbersOnCustSection.size(); i++) {
            if (listOfPhoneNumbersOnCustSection.get(i).getText().contains(fullphonenum)) {
                js_Click(listOfPhoneNumbersOnCustSection.get(0), "Phone number autosugesstion value on customer section in the phone order page");
                break;
            }
        }
    }

    /**
     * Verifies whether the auto-suggestion list appears in the Customer Phone Number section.
     *
     * @return If the customer phone number auto-suggestion list is hidden (style contains "none"), returns {@code true}; otherwise, returns {@code false}.
     * @Author Balaji N
     */
    public boolean Verify_phonenumberCustSection_AutosuggestionAppears() {
        //   HighlightElement(cust_phoneNumberUnorderListOnCustSection);
        //   return cust_phoneNumberUnorderListOnCustSection.getAttribute("style").contains("none");

        try {
            HighlightElement(cust_phoneNumberUnorderListOnCustSection);
            String styleAttribute = cust_phoneNumberUnorderListOnCustSection.getAttribute("style");

            if (styleAttribute != null && styleAttribute.contains("none")) {
                return styleAttribute != null && styleAttribute.contains("none");
            }
        } catch (NullPointerException e) {
            printError(cust_phoneNumberUnorderListOnCustSection, "Customer Phone Number Auto-suggestion List", "NullPointerException", e);
        } catch (StaleElementReferenceException e) {
            printError(cust_phoneNumberUnorderListOnCustSection, "Customer Phone Number Auto-suggestion List", "StaleElementReferenceException", e);
        } catch (NoSuchElementException e) {
            printError(cust_phoneNumberUnorderListOnCustSection, "Customer Phone Number Auto-suggestion List", "NoSuchElementException", e);
        } catch (Exception e) {
            printError(cust_phoneNumberUnorderListOnCustSection, "Customer Phone Number Auto-suggestion List", "Generic Exception", e);
        }

        return false;
    }

    /**
     * Verifies whether the auto-suggestion list appears in the Customer Alternative Phone Number section.
     *
     * @return If the customer alternative phone number auto-suggestion list is hidden (style contains "none"), returns {@code true}; otherwise, returns {@code false}.
     * @Author Balaji N
     */
    public boolean Verify_AltphonenumberCustSection_AutosuggestionAppears() {
        try {
            HighlightElement(cust_altphoneNumberUnorderListOnCustSection);
            String styleAttribute = cust_altphoneNumberUnorderListOnCustSection.getAttribute("style");

            if (styleAttribute != null && styleAttribute.contains("none")) {
                return styleAttribute != null && styleAttribute.contains("none");
            }
        } catch (NullPointerException e) {
            printError(cust_altphoneNumberUnorderListOnCustSection, "Customer Alternative Phone Number Auto-suggestion List", "NullPointerException", e);
        } catch (StaleElementReferenceException e) {
            printError(cust_altphoneNumberUnorderListOnCustSection, "Customer Alternative Phone Number Auto-suggestion List", "StaleElementReferenceException", e);
        } catch (NoSuchElementException e) {
            printError(cust_altphoneNumberUnorderListOnCustSection, "Customer Alternative Phone Number Auto-suggestion List", "NoSuchElementException", e);
        } catch (Exception e) {
            printError(cust_altphoneNumberUnorderListOnCustSection, "Customer Alternative Phone Number Auto-suggestion List", "Generic Exception", e);
        }
        return false;
    }

    /**
     * Search and Select the alternative phone number on customer section in the phone order page
     *
     * @param altphonenum
     * @param altfullphonenum
     * @Author Balaji N
     */
    public void SearchAndSelect_AltphonenumberOnCustSection(String altphonenum, String altfullphonenum) {
        AltphoneNumOnPhoneOrderPage.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(AltphoneNumOnPhoneOrderPage, altphonenum, "Alternative phone number textbox field on customer section in the phone order page");
        delayWithGivenTime(2000);
        for (int i = 0; i < listOfAltPhoneNumbersOnCustSection.size(); i++) {
            if (listOfAltPhoneNumbersOnCustSection.get(i).getText().contains(altfullphonenum)) {
                (listOfAltPhoneNumbersOnCustSection.get(0)).click();
                break;
            }
        }
    }

    /**
     * Verifies whether the edit icon appears on customer section in the phone order page
     *
     * @return If the edit icon is displayed, returns {@code true}; otherwise, returns {@code false}
     * @Author Balaji N
     */
    public boolean Verify_CustEditIcon_Appears() {
        return is_Element_Displayed(editCustInfoBtnOnPhoneOrderPage, "Customer Edit Icon on customer section on phone order page");
    }

    /**
     * Clicks the edit icon on customer section in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_CustEditIcon() {
        js_Click(editCustInfoBtnOnPhoneOrderPage, "Edit Icon on customer section on phone order page");
        wait_For_Page_To_Be_Stable(getDriver());
    }

    /**
     * Verifies whether the customer details popup appears on customer section in the phone order page
     *
     * @return If the customer details popup is displayed, returns {@code true}; otherwise, returns {@code false}.
     * @Author Balaji N
     */
    public boolean Verify_CustomerDetailsPopupAppears() {
        try {
            boolean isDisplayed = is_Element_Displayed(
                    custSectionCustomerDetailsPopup,
                    "Customer details popup on customer section on phone order page"
            );

            if (!isDisplayed) {
                String locator = custSectionCustomerDetailsPopup.toString()
                        .replaceAll(".*-> ", "")
                        .replaceAll("]$", "");
                ;
                String userFriendlyMsg = String.format(
                        "❌ The 'Customer Details' popup did NOT appear on the phone order page.%n" +
                                "🔍 Expected: Popup should be visible%n" +
                                "📍 Locator: %s%n" +
                                "💡 Possible reasons:%n" +
                                "   1. The customer details section was not triggered correctly.%n" +
                                "   2. There was a delay in loading the popup due to network/UI lag.%n" +
                                "   3. The locator might be outdated or incorrect.%n" +
                                "   4. The popup was blocked by another modal or overlay.",
                        locator
                );

                Allure.step(userFriendlyMsg);
                throw new AssertionError(userFriendlyMsg);
            }

            Allure.step("✅ 'Customer Details' popup appeared successfully on the phone order page.");
            return true;

        } catch (Exception e) {
            throw new RuntimeException("Error while verifying 'Customer Details' popup visibility.", e);
        }
    }


    /**
     * Clicks the profile tab on the customer details popup.
     * This method ensures safe execution by handling potential exceptions.
     *
     * @throws NoSuchElementException           if the profile tab element is not found
     * @throws ElementClickInterceptedException if the element is not clickable
     * @throws TimeoutException                 if the element is not available within the expected time
     * @author Balaji N
     */
    public void Click_ProfileTab_OnCustDetailsPopup() {
        try {
            customerProfileTabOnCustDetailsPopup.click();
        } catch (NoSuchElementException e) {
            printError(customerProfileTabOnCustDetailsPopup, "Profile tab on Customer Details Popup", "NoSuchElementException", e);
        } catch (ElementClickInterceptedException e) {
            printError(customerProfileTabOnCustDetailsPopup, "Profile tab on Customer Details Popup", "ElementClickInterceptedException", e);
        } catch (TimeoutException e) {
            printError(customerProfileTabOnCustDetailsPopup, "Profile tab on Customer Details Popup", "TimeoutException", e);
        }
    }

    /**
     * Verifies if the Profile tab on the Customer Details popup is expanded.
     * This method highlights the element before verification and handles potential exceptions.
     *
     * @return true if the Profile tab is expanded, false otherwise
     * @throws NoSuchElementException          if the profile tab element is not found
     * @throws ElementNotInteractableException if the element is not interactable
     * @throws TimeoutException                if the element is not available within the expected time
     * @throws StaleElementReferenceException  if the element is no longer attached to the DOM
     * @throws WebDriverException              for any other WebDriver-related exceptions
     * @author Balaji N
     */
    public boolean Verify_CustomerDetails_Popup_ProfileTab() {
        try {
            HighlightElement(customerProfileTabOnCustDetailsPopup);
            String ariaExpanded = customerProfileTabOnCustDetailsPopup.getAttribute("aria-expanded");
            return "true".equals(ariaExpanded);
        } catch (NoSuchElementException e) {
            printError(customerProfileTabOnCustDetailsPopup, "Profile tab on Customer Details Popup", "NoSuchElementException", e);
        } catch (ElementNotInteractableException e) {
            printError(customerProfileTabOnCustDetailsPopup, "Profile tab on Customer Details Popup", "ElementNotInteractableException", e);
        } catch (TimeoutException e) {
            printError(customerProfileTabOnCustDetailsPopup, "Profile tab on Customer Details Popup", "TimeoutException", e);
        } catch (StaleElementReferenceException e) {
            printError(customerProfileTabOnCustDetailsPopup, "Profile tab on Customer Details Popup", "StaleElementReferenceException", e);
        } catch (WebDriverException e) {
            printError(customerProfileTabOnCustDetailsPopup, "Profile tab on Customer Details Popup", "WebDriverException", e);
        }
        return false;
    }


    /**
     * Verifies whether the customer ID field on the customer details popup is disabled or not
     *
     * @return If the customer ID field is enabled, returns {@code true}; otherwise, returns {@code false}.
     * @Author Balaji N
     */
    public boolean Verify_CustomerIDField_Disabled() {
       /* HighlightElement(customerIDOnCustDetailsPopup);
        return customerIDOnCustDetailsPopup.isEnabled();*/
        return isElementEnabled(customerIDOnCustDetailsPopup, "Customer Id textbox field on customer details popup in the phone order page");
    }

    /**
     * Enters the company name on the customer details popup
     *
     * @param cmpyname It represents the company name to be entered
     * @Author Balaji N
     */
    public void Enter_CompanyName_OnCustDetailsPopup(String cmpyname) {
        companyNameOnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(companyNameOnCustDetailsPopup, cmpyname, "Company Name textbox field on customer details popup in the phone order page");
    }

    /**
     * This method retrieves the company name display value on the customer details popup
     *
     * @return If company name is displayed, returns company name; otherwise, returns null
     * @Author Balaji N
     */
    public String get_CompanyName_OnCustDetailsPopup() {
       /* HighlightElement(companyNameOnCustDetailsPopup);
        return companyNameOnCustDetailsPopup.getAttribute("value");*/
        return getElementAttribute(companyNameOnCustDetailsPopup, "Company Name textbox field value on customer details popup in the phone order page");
    }

    /**
     * Enters the first name on the customer details popup
     *
     * @param firstname the first name to be entered
     * @Author Balaji N
     */
    public void Enter_FirstName_OnCustDetailsPopup(String firstname) {
        firstNameOnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(firstNameOnCustDetailsPopup, firstname, "First Name textbox field on customer details popup in the phone order page");
    }

    /**
     * Retrieves the first name display value on the customer details popup
     *
     * @return If first name is displayed, returns first name; otherwise, returns null
     * @Author Balaji N
     */
    public String get_FirstName_OnCustDetailsPopup() {
        /* return firstNameOnCustDetailsPopup.getAttribute("value");*/
        return get_element_attribute(firstNameOnCustDetailsPopup, "First Name textbox field value on customer details popup in the phone order page");
    }

    /**
     * Enter Lastname on customer details popup in the phone order page
     *
     * @param lastname
     * @Author Balaji N
     */
    public void Enter_LastName_OnCustDetailsPopup(String lastname) {
        lastNameOnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(lastNameOnCustDetailsPopup, lastname, "Last Name textbox field on customer details popup in the phone order page");
    }

    /**
     * Retrieves the last name display value on the customer details popup
     *
     * @return If the last name is displayed, returns the last name; otherwise, returns null
     * @Author Balaji N
     */
    public String get_LastName_OnCustDetailsPopup() {
        // return lastNameOnCustDetailsPopup.getAttribute("value");
        return get_element_attribute(lastNameOnCustDetailsPopup, "Last Name textbox field value on customer details popup in the phone order page");
    }

    /**
     * Enters the phone number on the customer details popup
     *
     * @param phonenum the phone number to be entered
     * @Author Balaji N
     */
    public void Enter_PhoneNumber_OnCustDetailsPopup(String phonenum) {
        phoneNumOnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(phoneNumOnCustDetailsPopup, phonenum, "Phone number textbox field on customer details popup in the phone order page");
    }

    /**
     * Retrieves the phone number display value on the customer details popup
     *
     * @return If the phone number is displayed, returns the phone number; otherwise, returns null
     * @Author Balaji N
     */
    public String get_PhoneNumber_OnCustDetailsPopup() {
        // return phoneNumOnCustDetailsPopup.getAttribute("value");
        return get_element_attribute(phoneNumOnCustDetailsPopup, "Phone number textbox field value on customer details popup in the phone order page");
    }

    /**
     * Enters the alternative phone number on the customer details popup
     *
     * @param altphonenum the alternative phone number to be entered
     * @Author Balaji N
     */
    public void Enter_AltPhoneNumber_OnCustDetailsPopup(String altphonenum) {
        altphoneNumOnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(altphoneNumOnCustDetailsPopup, altphonenum, "Alternative Phone number textbox field on customer details popup in the phone order page");
    }

    /**
     * Retrieves the alternative phone number display value on the customer details popup
     *
     * @return If the alternative phone number is displayed, returns the alternative phone number; otherwise, returns null
     * @Author Balaji N
     */
    public String get_AltPhoneNumber_OnCustDetailsPopup() {
        //return altphoneNumOnCustDetailsPopup.getAttribute("value");
        return get_element_attribute(altphoneNumOnCustDetailsPopup, "Alternative Phone number textbox field value on customer details popup in the phone order page");
    }

    /**
     * Enter Email on customer details popup in the phone order page
     *
     * @param email the email to be entered
     * @Author Balaji N
     */
    public void Enter_Email_OnCustDetailsPopup(String email) {
        emailIdOnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(emailIdOnCustDetailsPopup, email, "Email Id field on the customer details popup in the phone order page");
    }

    /**
     * Retrieves the email id display value on the customer details popup
     *
     * @return If the email is displayed, returns the email; otherwise, returns null
     * @Author Balaji N
     */
    public String get_Email_OnCustDetailsPopup() {
        // return emailIdOnCustDetailsPopup.getAttribute("value");
        return get_element_attribute(emailIdOnCustDetailsPopup, "Email Id field value on the customer details popup in the phone order page");
    }

    /**
     * Enter the country on the customer details popup
     *
     * @param country the country to be entered
     * @Author Balaji N
     */
    public void Enter_Country_OnCustDetailsPopup(String country) {
        countryOnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(countryOnCustDetailsPopup, country, "Country textbox field on the customer details popup in the phone order page");
    }

    /**
     * Retrieves the country display value on the customer details popup
     *
     * @return If the country is displayed, returns the country value; otherwise, returns null
     * @Author Balaji N
     */
    public String get_Country_OnCustDetailsPopup() {
        // return countryOnCustDetailsPopup.getAttribute("value");
        return get_element_attribute(countryOnCustDetailsPopup, "Country textbox field value on the customer details popup in the phone order page");
    }

    /**
     * Enter the city on the customer details popup
     *
     * @param city the city to be entered
     * @Author Balaji N
     */
    public void Enter_City_OnCustDetailsPopup(String city) {
        cityOnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(cityOnCustDetailsPopup, city, "City textbox field on the customer details popup in the phone order page");
    }

    /**
     * Retrieves the city display value on the customer details popup
     *
     * @return If the city is displayed, returns the city; otherwise, returns null
     * @Author Balaji N
     */
    public String get_City_OnCustDetailsPopup() {
        // return cityOnCustDetailsPopup.getAttribute("value");
        return get_element_attribute(cityOnCustDetailsPopup, "City textbox field value on the customer details popup in the phone order page");
    }

    /**
     * Enters the zipcode on the customer details popup
     *
     * @param zipcode the zipcode to be entered
     * @Author Balaji N
     */
    public void Enter_ZipCode_OnCustDetailsPopup(String zipcode) {
        zipcodeOnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(zipcodeOnCustDetailsPopup, zipcode, "Zipcode textbox field on the customer details popup in the phone order page");
    }

    /**
     * Retrieves the zipcode display value on the customer details popup
     *
     * @return If the zipcode is displayed, returns the zipcode; otherwise, returns null
     * @Author Balaji N
     */
    public String get_ZipCode_OnCustDetailsPopup() {
        //return zipcodeOnCustDetailsPopup.getAttribute("value");
        return get_element_attribute(zipcodeOnCustDetailsPopup, "Zipcode textbox field value on the customer details popup in the phone order page");
    }

    /**
     * Enter the address 1 on the customer details popup in the phone order page
     *
     * @param address1 the address 1 to be entered
     * @Author Balaji N
     */
    public void Enter_Address1_OnCustDetailsPopup(String address1) {
        address1OnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(address1OnCustDetailsPopup, address1, "Address 1 textbox field on the customer details popup in the phone order page");
    }

    /**
     * Retrieves the address 1 display value on the customer details popup
     *
     * @return If the address 1 is displayed, returns the address 1; otherwise, returns null
     * @Author Balaji N
     */
    public String get_Address1_OnCustDetailsPopup() {
        //  return address1OnCustDetailsPopup.getAttribute("value");
        return get_element_attribute(address1OnCustDetailsPopup, "Address 1 textbox field value on the customer details popup in the phone order page");
    }

    /**
     * Clicks the Late Fee Setting toggle button on the Customer Details popup.
     * This method ensures safe execution by handling potential exceptions.
     *
     * @throws NoSuchElementException           if the Late Fee Setting toggle button is not found
     * @throws ElementClickInterceptedException if the element is not clickable due to an overlay or another issue
     * @throws TimeoutException                 if the element is not available within the expected time
     * @throws StaleElementReferenceException   if the element is no longer attached to the DOM
     * @throws ElementNotInteractableException  if the element is present but not interactable
     * @throws WebDriverException               for any other WebDriver-related exceptions
     * @author Balaji N
     */
    public void Click_LateFeeSetting_OnCustDetailsPopup() {
        try {
            latesettingtooglebutton.click();
        } catch (NoSuchElementException e) {
            printError(latesettingtooglebutton, "Late Fee Setting toggle button", "NoSuchElementException", e);
        } catch (ElementClickInterceptedException e) {
            printError(latesettingtooglebutton, "Late Fee Setting toggle button", "ElementClickInterceptedException", e);
        } catch (TimeoutException e) {
            printError(latesettingtooglebutton, "Late Fee Setting toggle button", "TimeoutException", e);
        } catch (StaleElementReferenceException e) {
            printError(latesettingtooglebutton, "Late Fee Setting toggle button", "StaleElementReferenceException", e);
        } catch (ElementNotInteractableException e) {
            printError(latesettingtooglebutton, "Late Fee Setting toggle button", "ElementNotInteractableException", e);
        } catch (WebDriverException e) {
            printError(latesettingtooglebutton, "Late Fee Setting toggle button", "WebDriverException", e);
        }
    }


    /**
     * Verifies if the Late Fee Setting toggle button is enabled on the Customer Details popup.
     * The method highlights the element before checking its style attribute.
     *
     * @return true if the Late Fee Setting toggle button is enabled, false otherwise
     * @throws NoSuchElementException          if the Late Fee Setting toggle button is not found
     * @throws ElementNotInteractableException if the element is present but not interactable
     * @throws TimeoutException                if the element is not available within the expected time
     * @throws StaleElementReferenceException  if the element is no longer attached to the DOM
     * @throws WebDriverException              for any other WebDriver-related exceptions
     * @author Balaji N
     */
    public boolean Verify_LateFeeSetting_Enabled_OnCustDetailsPopup() {
        try {
            HighlightElement(latesettingtooglebutton); // rgb(197, 231, 244) - green
            return latesettingtooglebutton.getAttribute("style").contains("rgb(197, 231, 244)");
        } catch (NoSuchElementException e) {
            printError(latesettingtooglebutton, "Late Fee Setting toggle button", "NoSuchElementException", e);
            throw e;
        } catch (ElementNotInteractableException e) {
            printError(latesettingtooglebutton, "Late Fee Setting toggle button", "ElementNotInteractableException", e);
            throw e;
        } catch (TimeoutException e) {
            printError(latesettingtooglebutton, "Late Fee Setting toggle button", "TimeoutException", e);
            throw e;
        } catch (StaleElementReferenceException e) {
            printError(latesettingtooglebutton, "Late Fee Setting toggle button", "StaleElementReferenceException", e);
            throw e;
        } catch (WebDriverException e) {
            printError(latesettingtooglebutton, "Late Fee Setting toggle button", "WebDriverException", e);
            throw e;
        }
    }

    /**
     * Clicks the Update button on the Customer Details popup.
     *
     * @author Balaji N
     */
    public void Click_UpdateBtn_OnCustDetailsPopup() {
        js_Click(updateCustomerDetailsBtnOnCustDetailsPopup, "Update button on the customer details popup in the phone order page");
    }

    /**
     * Clicks the Close button on the Customer Details popup.
     *
     * @Author Balaji N
     */
    public void Click_Customer_DetailsPopupCloseBtn() {
        fluentWait(closeBtnOnCustDetailsPopup);
        js_Click(closeBtnOnCustDetailsPopup, "Close Button on the customer details popup in the phone order page");
    }

    /**
     * Clicks the Credit Card Tab on the Customer Details popup in the phone order page
     *
     * @author Balaji N
     */
    public void Click_CreditCardTab_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
        Click(creditCardTabOnCustDetailsPopup, "Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * Clicks the Add New Button on the Credit Card Tab on the Customer Details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_AddNewBtn_CreditCardTab_OnCustDetailsPopup() {
        js_Click(addNewCreditCardBtnOnCustDetailsPopup, "Add New Button on the Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * This function verify whether the credit card is displayed if it is present return true or else return false when no such element exception is occurs
     *
     * @return if it is present return true or else return false when no such element exception is occurs
     * @Author Balaji N
     */
    public boolean Verify_grid1_creditcard_num_IsDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10)); // Adjust timeout if necessary
            WebElement element = wait.until(ExpectedConditions.visibilityOf(grid1_added_creditcardnum));
            return isElementDisplayed(grid1_added_creditcardnum, "Credit card number row 1 table grid on the Credit Card Tab on the customer details popup in the phone order page");
        } catch (TimeoutException e) {
            System.out.println("Element not visible in time: " + e.getMessage());
            return false;
        } catch (NoSuchElementException e) {
            System.out.println("No such element found: " + e.getMessage());
            return false;
        }
    }


    public void Click_AddNewBtn_CreditCardTab_OnCustDetailsPopup_Bestflorist_shop() {
        js_Click(grid1_added_creditcardnum, "Add New Button on the Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * Verifies whether the credit card number is displayed on the Credit Card Tab on the Customer Details popup in the phone order page.
     *
     * @return If the credit card number is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean verify_credit_card_number_isDispalyed_on_CreditCardTab_OnCustDetailsPopup() {
        return isElementDisplayed(grid1_added_creditcard_number, "Credit card number row 1 table grid on the Credit Card Tab - Customer Details popup - Phone Order Page");
    }

    /**
     * Verifies whether the credit card expire date is displayed on the Credit Card Tab on the Customer Details popup in the phone order page.
     *
     * @return If the credit card expire date is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean verify_credit_card_expiredate_isDispalyed_on_CreditCardTab_OnCustDetailsPopup() {
        return isElementDisplayed(grid1_added_creditcard_expire_date, "Credit card expire date row 1 table grid on the Credit Card Tab - Customer Details popup - Phone Order Page");
    }


    /**
     * Verifies whether the Add New Button on the Credit Card Tab on the Customer Details popup in the phone order page is displayed.
     *
     * @return If the Add New Button is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_AddNewInformation_Popup() {
       /* HighlightElement(addNewCreditCardBtnOnCustDetailsPopup);
        return addNewCreditCardBtnOnCustDetailsPopup.isDisplayed();*/
        return is_Element_Displayed(addNewCreditCardBtnOnCustDetailsPopup, "Add New Button on the Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * Enters the First Name on the Credit Card Tab on the Customer Details popup in the phone order page
     *
     * @param creditcard_firstname the first name to be entered
     * @Author Balaji N
     */
    public void Enter_CreditCardTab_FirstName_OnCustDetailsPopup(String creditcard_firstname) {
        Double_Click_And_Type(creditCardTab_FirstName_OnCustDetailsPopup, creditcard_firstname, "FirstName textbox field on Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * It retrieves the First Name on the Credit Card Tab on the Customer Details popup in the phone order page
     *
     * @return If the First Name is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public String get_CreditCardTab_EnteredFirstName_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
      /*  HighlightElement(creditCardTab_FirstName_OnCustDetailsPopup);
        return creditCardTab_FirstName_OnCustDetailsPopup.getAttribute("value");*/
        return getElementAttribute(creditCardTab_FirstName_OnCustDetailsPopup, "FirstName textbox field on Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * It enters the Last Name on the Credit Card Tab on the Customer Details popup in the phone order page
     *
     * @param creditcard_lastname the lastname to be entered
     * @Author Balaji N
     */
    public void Enter_CreditCardTab_LastName_OnCustDetailsPopup(String creditcard_lastname) {
        Double_Click_And_Type(creditCardTab_LastName_OnCustDetailsPopup, creditcard_lastname, "LastName textbox field on Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * It retrieves the Last Name on the Credit Card Tab on the Customer Details popup in the phone order page
     *
     * @return If the Last Name is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public String get_CreditCardTab_EnteredLastName_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
       /* HighlightElement(creditCardTab_LastName_OnCustDetailsPopup);
        return creditCardTab_LastName_OnCustDetailsPopup.getAttribute("value");*/
        return getElementAttribute(creditCardTab_LastName_OnCustDetailsPopup, "LastName textbox field on Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * It enters the Credit Card Number on the Credit Card Tab on the Customer Details popup in the phone order page
     *
     * @param creditcard_number If the Credit Card Number is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public void Enter_CreditCardTab_CreditCardNumber_OnCustDetailsPopup(String creditcard_number) {
        Double_Click_And_Type(creditCardTab_CreditCardNumber_OnCustDetailsPopup, creditcard_number, "Credit Card Number textbox field on Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * It retrieves the Credit Card Number on the Credit Card Tab on the Customer Details popup in the phone order page
     *
     * @return If the Credit Card Number is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public String get_CreditCardTab_EnteredCreditCardNumber_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
       /* HighlightElement(creditCardTab_CreditCardNumber_OnCustDetailsPopup);
        return creditCardTab_CreditCardNumber_OnCustDetailsPopup.getAttribute("value");*/
        return getElementAttribute(creditCardTab_CreditCardNumber_OnCustDetailsPopup, "Credit Card Number textbox field value on Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * It enters the Expire Date on the Credit Card Tab on the Customer Details popup in the phone order page
     *
     * @param creditcard_expdate the expire date to be entered
     * @Author Balaji N
     */
    public void Enter_CreditCardTab_ExpDate_OnCustDetailsPopup(String creditcard_expdate) {
        creditCardTab_ExpDate_OnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(creditCardTab_ExpDate_OnCustDetailsPopup, creditcard_expdate, "Expire Date textbox field on Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * It retrieves the Expire Date on the Credit Card Tab on the Customer Details popup in the phone order page
     *
     * @return the expire date to be entered
     * @Author Balaji N
     */
    public String get_CreditCardTab_EnteredExpDate_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
      /*  HighlightElement(creditCardTab_ExpDate_OnCustDetailsPopup);
        return creditCardTab_ExpDate_OnCustDetailsPopup.getAttribute("value");*/
        return getElementAttribute(creditCardTab_ExpDate_OnCustDetailsPopup, "Expire date textbox field value on Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * It enters the CVV on the Credit Card Tab on the Customer Details popup in the phone order page
     *
     * @param creditcard_cvv the cvv to be entered
     * @Author Balaji N
     */
    public void Enter_CreditCardTab_CVV_OnCustDetailsPopup(String creditcard_cvv) {
        Double_Click_And_Type(creditCardTab_CVV_OnCustDetailsPopup, creditcard_cvv, "Credit Card CVV textbox field on Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * It retrieves the CVV on the Credit Card Tab on the Customer Details popup in the phone order page
     *
     * @return If the CVV is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public String get_CreditCardTab_EnteredCVV_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
       /* HighlightElement(creditCardTab_CVV_OnCustDetailsPopup);
        return creditCardTab_CVV_OnCustDetailsPopup.getAttribute("value");*/
        return getElementAttribute(creditCardTab_CVV_OnCustDetailsPopup, "CVV textbox field value on Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * It enters the Zipcode on the Credit Card Tab on the Customer Details popup in the phone order page
     *
     * @param creditcard_zipcode the zipcode to be entered
     * @Author Balaji N
     */
    public void Enter_CreditCardTab_Zipcode_OnCustDetailsPopup(String creditcard_zipcode) {
        Double_Click_And_Type(creditCardTab_ZipCode_OnCustDetailsPopup, creditcard_zipcode, "Zipcode textbox field on Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * It retrieves the Zipcode on the Credit Card Tab on the Customer Details popup in the phone order page
     *
     * @return If the Zipcode is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public String get_CreditCardTab_EnteredZipcode_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
       /* HighlightElement(creditCardTab_ZipCode_OnCustDetailsPopup);
        return creditCardTab_ZipCode_OnCustDetailsPopup.getAttribute("value");*/
        return getElementAttribute(creditCardTab_ZipCode_OnCustDetailsPopup, "Zipcode textbox field value on Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * Clicks the Save Button on the Credit Card Tab on the Customer Details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_SaveBtn_CreditCardTab_OnCustDetailsPopup() {
        Click(creditCardTab_SaveBtn_OnCustDetailsPopup, "Save Button on Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * It verifies if the Saved Information Popup toaster message is displayed on the Credit Card Tab on the Customer Details popup in the phone order page
     *
     * @return If the Saved Information Popup toaster message is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_SavedInformation_Popup() {
        delayWithGivenTime(1000);
        return is_Element_Displayed(SuccessToastMsg, "Saved Information Popup toaster message on Credit Card Tab on the customer details popup in the phone order page");
    }

    /**
     * Clicks the Close Icon on the Credit Card Tab on the Customer Details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_CloseIcon_CreditCardTab_OnCustDetailsPopup() {
        if (AddNewInformationCreditCardOnCustDetailsPopup.isDisplayed()) {
            js_Click(creditCardTab_CloseIcon_OnCustDetailsPopup, "Close Icon on Credit Card Tab on the customer details popup in the phone order page");
        }
    }

    /**
     * Clicks the preference tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_PreferencesTab_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
        js_Click(preferencesTabOnCustDetailsPopup, "Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Selects the tax type on the preference tab on customer details popup in the phone order page
     *
     * @param taxtype the tax type to be selected
     * @Author Balaji N
     */
    public void Select_TaxType_PreferencesTab_OnCustDetailsPopup(String taxtype) {
        delayWithGivenTime(1000);
        drop_Down(taxtype_preference_OnCustDetailsPopup, taxtype, "VisibleText", "Taxtype dropdown field on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the tax type on the preference tab on customer details popup in the phone order page
     *
     * @return If the tax type is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public String get_TaxType_PreferencesTab_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
       /* HighlightElement(taxtype_preference_OnCustDetailsPopup);
        s = new Select(taxtype_preference_OnCustDetailsPopup);
        return s.getFirstSelectedOption().getText();*/
        return get_Selected_Option(taxtype_preference_OnCustDetailsPopup, "Taxtype dropdown field value on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Enters the tax id on the preference tab on customer details popup in the phone order page
     *
     * @param taxid the tax id to be entered
     * @Author Balaji N
     */
    public void Enter_TexId_PreferenceTab_OnCustDetailsPopup(String taxid) {
        taxid_preference_OnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(taxid_preference_OnCustDetailsPopup, taxid, "Tax Id textbox field on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the tax id on the preference tab on customer details popup in the phone order page
     *
     * @return If the tax id is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public String get_TexId_PreferenceTab_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
       /* HighlightElement(taxid_preference_OnCustDetailsPopup);
        return taxid_preference_OnCustDetailsPopup.getAttribute("value");*/
        return getElementAttribute(taxid_preference_OnCustDetailsPopup, "Tax Id textbox field value on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Enters the discount percentage on the preference tab on customer details popup in the phone order page
     *
     * @param discount If the discount percentage is entered, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public void Enter_DiscountPercentage_PreferenceTab_OnCustDetailsPopup(String discount) {
        discount_preference_OnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(discount_preference_OnCustDetailsPopup, discount, "Discount percentage field on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the discount percentage on the preference tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_DiscountPercentage_PreferenceTab_OnCustDetailsPopup() {
        try {
            discount_preference_OnCustDetailsPopup.click();
        } catch (Exception e) {
            printError(discount_preference_OnCustDetailsPopup, "Discount Percentage textbox field", "Generic exception", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the discount percentage on the preference tab on customer details popup in the phone order page
     *
     * @return If the discount percentage is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public String get_DiscountPercentage_PreferenceTab_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
       /* HighlightElement(discount_preference_OnCustDetailsPopup);
        return discount_preference_OnCustDetailsPopup.getAttribute("value");
*/
        return getElementAttribute(discount_preference_OnCustDetailsPopup, "Discount percentage field value on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Enters the discount amount on the preference tab on customer details popup in the phone order page
     *
     * @param discountamount the discount amount to be entered
     * @Author Balaji N
     */
    public void Enter_DiscountAmount_PreferenceTab_OnCustDetailsPopup(String discountamount) {
        discountamount_preference_OnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(discountamount_preference_OnCustDetailsPopup, discountamount, "Discount amount textbox field on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the discount amount on the preference tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_DiscountAmount_PreferenceTab_OnCustDetailsPopup() {
        try {
            discountamount_preference_OnCustDetailsPopup.click();
        } catch (Exception e) {
            printError(discountamount_preference_OnCustDetailsPopup, "Discount amount field on Preference Tab on customer details popup in the phone order page", "", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the discount amount on the preference tab on customer details popup in the phone order page
     *
     * @return If the discount amount is displayed, returns value of the discount amount; otherwise, returns null
     * @Author Balaji N
     */
    public String get_DiscountAmount_PreferenceTab_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
       /* HighlightElement(discountamount_preference_OnCustDetailsPopup);
        return discountamount_preference_OnCustDetailsPopup.getAttribute("value");
*/
        return getElementAttribute(discountamount_preference_OnCustDetailsPopup, "Discount amount textbox field value on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Enters the delivery fee on the preference tab on customer details popup in the phone order page
     *
     * @param deliveryfee the delivery fee to be entered
     * @Author Balaji N
     */
    public void Enter_DeliveryFee_PreferenceTab_OnCustDetailsPopup(String deliveryfee) {
        deliveryfee_preference_OnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(deliveryfee_preference_OnCustDetailsPopup, deliveryfee, "Delivery Fee textbox field on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the delivery fee on the preference tab on customer details popup in the phone order page
     *
     * @return If the delivery fee is displayed, returns delivery fee value; otherwise, returns null
     * @Author Balaji N
     */
    public String get_DeliveryFee_PreferenceTab_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
       /* HighlightElement(deliveryfee_preference_OnCustDetailsPopup);
        return deliveryfee_preference_OnCustDetailsPopup.getAttribute("value");
*/
        return getElementAttribute(deliveryfee_preference_OnCustDetailsPopup, "Delivery Fee textbox field value on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Enters the card message on the preference tab on customer details popup in the phone order page
     *
     * @param cardmessage the card message to be entered
     * @Author Balaji N
     */
    public void Enter_CardMessage_PreferenceTab_OnCustDetailsPopup(String cardmessage) {
        cardmessage_preference_OnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(cardmessage_preference_OnCustDetailsPopup, cardmessage, "Card Message textbox field on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the card message on the preference tab on customer details popup in the phone order page
     *
     * @return If the card message is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public String get_CardMessage_PreferenceTab_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
      /*  HighlightElement(cardmessage_preference_OnCustDetailsPopup);
        return cardmessage_preference_OnCustDetailsPopup.getAttribute("value");
*/
        return getElementAttribute(cardmessage_preference_OnCustDetailsPopup, "Card Message textbox field value on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Enters the PO number on the preference tab on customer details popup in the phone order page
     *
     * @param ponumber the po number to be entered
     * @Author Balaji N
     */
    public void Enter_PONumber_PreferenceTab_OnCustDetailsPopup(String ponumber) {
        ponumber_preference_OnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(ponumber_preference_OnCustDetailsPopup, ponumber, "PO number textbox field on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the PO number on the preference tab on customer details popup in the phone order page
     *
     * @return If the PO number is displayed, returns PO number ; otherwise, returns null
     * @Author Balaji N
     */
    public String get_PONumber_PreferenceTab_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
       /* HighlightElement(ponumber_preference_OnCustDetailsPopup);
        return ponumber_preference_OnCustDetailsPopup.getAttribute("value");
*/
        return getElementAttribute(ponumber_preference_OnCustDetailsPopup, "PO number textbox field value on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Selects the payment type preference tab on customer details popup in the phone order page
     *
     * @param paymenttype the payment type to be selected
     * @Author Balaji N
     */
    public void Select_PaymentType_PreferenceTab_OnCustDetailsPopup(String paymenttype) {
        delayWithGivenTime(1000);
        drop_Down(paymenttype_preference_OnCustDetailsPopup, paymenttype, "VisibleText", "Payment Type dropdown field on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the payment type on the preference tab on customer details popup in the phone order page
     *
     * @return If the payment type is displayed, returns payment type; otherwise, returns null
     * @Author Balaji N
     */
    public String get_PaymentType_PreferenceTab_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
        return get_Selected_Option(paymenttype_preference_OnCustDetailsPopup, "Payment type dropdown field value on Preference Tab on customer details popup in the phone order page");
        /*HighlightElement(paymenttype_preference_OnCustDetailsPopup);
        s = new Select(paymenttype_preference_OnCustDetailsPopup);
        return s.getFirstSelectedOption().getText();*/
    }

    /**
     * Selects the payment terms preference tab on customer details popup in the phone order page
     *
     * @param paymentterms the payment terms to be selected
     * @Author Balaji N
     */
    public void Select_PaymentTerms_PreferenceTab_OnCustDetailsPopup(String paymentterms) {
        delayWithGivenTime(1000);
        drop_Down(paymentterms_preference_OnCustDetailsPopup, paymentterms, "VisibleText", "Payment terms dropdown field on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the payment terms on the preference tab on customer details popup in the phone order page
     *
     * @return If the payment terms is displayed, returns payment terms; otherwise, returns null
     * @Author Balaji N
     */
    public String get_PaymentTerms_PreferenceTab_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
        return get_Selected_Option(paymentterms_preference_OnCustDetailsPopup, "Payment terms dropdown field value on Preference Tab on customer details popup in the phone order page");
       /* HighlightElement(paymentterms_preference_OnCustDetailsPopup);
        s = new Select(paymentterms_preference_OnCustDetailsPopup);
        return s.getFirstSelectedOption().getText();*/
    }

    /**
     * Enters the special instruction on the preference tab on customer details popup in the phone order page
     *
     * @param specialinstruction the special instruction to be entered
     * @Author Balaji N
     */
    public void Enter_SpecialInstruction_PreferenceTab_OnCustDetailsPopup(String specialinstruction) {
        specialinstruction_preference_OnCustDetailsPopup.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(specialinstruction_preference_OnCustDetailsPopup, specialinstruction, "Special Instruction textbox field on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the special instruction on the preference tab on customer details popup in the phone order page
     *
     * @return If the special instruction is displayed, returns special instruction; otherwise, returns null
     * @Author Balaji N
     */
    public String get_SpecialInstruction_PreferenceTab_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
       /* HighlightElement(specialinstruction_preference_OnCustDetailsPopup);
        return specialinstruction_preference_OnCustDetailsPopup.getAttribute("value");
*/
        return getElementAttribute(specialinstruction_preference_OnCustDetailsPopup, "Special Instruction textbox field value on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the update customer default button on the preference tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_UpdateCustDefaultBtn_PreferenceTab_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
        Click(updatecustomerDefaultbtn_preference_OnCustDetailsPopup, "Update button on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies the success toast message on the preference tab on customer details popup in the phone order page
     *
     * @return If the success toast message is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_CustDefaultSucessMsg_PreferenceTab_OnCustDetailsPopup() {
        delayWithGivenTime(1000);
        return isElementDisplayed(SuccessToastMsg, "Success toaster message on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies the confirmation alert on the preference tab on customer details popup in the phone order page
     *
     * @return If the confirmation alert is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_ConfirmationAlert_Preference_OnCustDetailsPopup() {
        return isElementDisplayed(confirmationalert_preference_OnCustDetailsPopup, "Confirmation Alert on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the confirmation alert not needed button on the preference tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_ConfirmationAlert_NotNeeded_Button_Preference_OnCustDetailsPopup() {
        getDriver().switchTo().activeElement();
        Click(confirmationalert_notneededbtn_preference_OnCustDetailsPopup, "NotNeeded Button - Confirmation popup on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the special instruction on customer details popup in the phone order page
     *
     * @return If the special instruction is displayed, returns special instruction; otherwise, returns null
     * @Author Balaji N
     */
    public String get_SpecialInstruction_OnPhoneorderpage() {
        return getElementAttribute(special_instructions_on_phoneorderpage_productsection, "Special Instruction textbox field value on product details section in the phone order page");
    }

    /**
     * Retrieves the PO number on customer details popup in the phone order page
     *
     * @return If the PO number is displayed, returns PO number; otherwise, returns null
     * @Author Balaji N
     */
    public String get_PONumber_InvoiceHouseAccount_OnPhoneorderpage() {
        delayWithGivenTime(1000);
       /* HighlightElement(ponumber_paymenttype_OnPhoneOrderPage);
        return ponumber_paymenttype_OnPhoneOrderPage.getAttribute("value");
*/
        return getElementAttribute(ponumber_paymenttype_OnPhoneOrderPage, "PO Number textbox value on payment type Section in the phone order page");
    }

    /**
     * Retrieves the payment terms on customer details popup in the phone order page
     *
     * @return If the payment terms is displayed, returns payment terms; otherwise, returns null
     * @Author Balaji N
     */
    public String get_PaymentTerms_InvoiceHouseAccount_OnPhoneorderpage() {
        delayWithGivenTime(1000);
     /*   HighlightElement(paymentterm_paymenttype_OnPhoneOrderPage);
        return paymentterm_paymenttype_OnPhoneOrderPage.getAttribute("value");
*/
        return getElementAttribute(paymentterm_paymenttype_OnPhoneOrderPage, "Payment terms textbox value on payment type Section in the phone order page");
    }

    /**
     * Retrieves the delivery fee on payment type section in the phone order page
     *
     * @return If the delivery fee is displayed, returns delivery fee value; otherwise, returns null
     * @Author Balaji N
     */
    public String get_DeliveryFee_InvoiceHouseAccount_OnPhoneorderpage() {
      /*  HighlightElement(deliveryfee_paymenttype_OnPhoneOrderPage);
        delayWithGivenTime(1000);
        return deliveryfee_paymenttype_OnPhoneOrderPage.getAttribute("value");
*/
        return getElementAttribute(deliveryfee_paymenttype_OnPhoneOrderPage, "Delivery fee textbox value on payment type Section in the phone order page");
    }

    public void enter_TaxId_On_ProductDetails_Section_Phone_OrderPage(String taxid) {
        ClickAndType(taxid_OnPhoneOrderPage, taxid, "Tax ID field on phone order page - product details section");
    }

    /**
     * Retrieves the tax ID on customer details popup in the phone order page
     *
     * @return If the tax ID is displayed, returns tax ID; otherwise, returns null
     * @Author Balaji N
     */
    public String get_TaxId_OnPhoneOrderPage() {
        return getElementAttribute(taxid_OnPhoneOrderPage, "Tax ID textbox field value on payment section in the phone order page");
    }

    /**
     * Retrieves the discount amount on the payment section in the phone order page
     *
     * @return If the discount amount is displayed, returns discount amount; otherwise, returns null
     * @Author Balaji N
     */
    public String get_PaymentSection_DiscountAmount_OnPhoneOrderPage() {
        return getElementAttribute(paymentdiscamt_OnPhoneOrderPage, "Discount amount textbox field value on payment section in the phone order page");
    }

    /**
     * Selects the tax type on the product section in the phone order page
     *
     * @param taxtype
     * @Author Balaji N
     */
    public void Select_TaxType_OnPhoneOrderPage(String taxtype) {
        drop_Down(taxtype_OnPhoneOrderPage, taxtype, "VisibleText", "Tax type dropdown on phone order page");
    }

    /**
     * Retrieves the tax type on the payment section in the phone order page
     *
     * @return If the tax type is displayed, returns tax type; otherwise, returns null
     * @Author Balaji N
     */
    public String get_TaxType_OnPhoneOrderPage() {
        return get_Selected_Option(taxtype_OnPhoneOrderPage, "Tax type dropdown field value on payment section in the phone order page");
    }

    /**
     * Clicks the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_UnpaidInvoiceTab_OnCustDetailsPopup() {
        Click(customerDetailsPopup_Unpaid_TabOnPhoneOrderPage, "Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the row 1 invoice on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_Row1Inv_UnpaidInvoiceTab_OnCustDetailsPopup() {
        click(row1_InvoiceNo_UnpaidTabOnPhoneOrderPage, "Row 1 - Table grid on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies the invoice popup on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the invoice popup is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_InvoicePopup_Appears_OnUnpaidInvoiceTab() {
        return isElementDisplayed(InvoiceDetailsPopup_Unpaid_TabOnPhoneOrderPage, "Invoice Popup on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the close icon on the invoice popup on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void ClickCloseIcon_InvoicePopup_OnUnpaidInvoiceTab() {
        Click(closeIcon_InvoiceDetailsPopup_UnpaidTabOnPhoneOrderPage, "Close Icon on Invoice Popup on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies the make payment button on the invoice popup on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the make payment button is enabled, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_MakePaymentBtn_InvoicePopup_OnUnpaidInvoiceTab() {
        return isElementEnabled(MakePaymentButton_InvoiceDetailsPopup_UnpaidTabOnPhoneOrderPage, "Make Payment Button on Invoice Popup on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the make payment button on the invoice popup on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_MakePaymentBtn_InvoicePopup_OnUnpaidInvoiceTab() {
        Click(MakePaymentButton_InvoiceDetailsPopup_UnpaidTabOnPhoneOrderPage, "Make payment button on Invoice Popup on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the row 1 checkbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_Row1_Checkbox_UnpaidInvoiceTab_OnCustDetailsPopup() {
        Click(row1_Checkbox_InvoiceDetailsPopup_UnpaidTabOnPhoneOrderPage, "Row1 Checkbox on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies the invoice payment details section on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the invoice payment details section is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_InvoicePaymentDetails_UnpaidInvoiceTab_OnCustDetailsPopup() {
      /*  HighlightElement(invoicepaymentdetailssection_UnpaidTabOnPhoneOrderPage);
        return invoicepaymentdetailssection_UnpaidTabOnPhoneOrderPage.isDisplayed();*/
        return isElementDisplayed(invoicepaymentdetailssection_UnpaidTabOnPhoneOrderPage, "Invoice Payment Details Section on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Selects the payment method on the invoice payment details section on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @param paymenttype If the payment type is selected, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public void Select_PaymentMethod_OnUnpaidInvoiceTab(String paymenttype) {
        drop_Down(paymenttype_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage, paymenttype, "VisibleText", "Payment type dropdown field on Invoice Payment Details Section on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the selected payment method on the invoice payment details section on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the payment type is selected, returns payment type; otherwise, returns null
     * @Author Balaji N
     */
    public String get_selected_paymentmethod_on_unpaid_InvoiceTab() {
        return get_Selected_Option(paymenttype_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage, "Payment type dropdown field value on Invoice Payment Details Section on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Selects the cash registry on the invoice details section on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @param cashregistry If the cash registry is selected, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public void Select_CashRegistry_OnUnpaidInvoiceTab(String cashregistry) {
        drop_Down(cashregister_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage, cashregistry, "VisibleText", "Cash Registry dropdown field on Invoice Details Section on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the selected payment method on the invoice payment details section on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the payment type is selected, returns payment type; otherwise, returns null
     * @Author Balaji N
     */
    public String get_selected_cashregistry_on_unpaid_InvoiceTab() {
        return get_Selected_Option(cashregister_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage, "cash registry dropdown field value on Invoice Payment Details Section on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the total due amount on the invoice details section on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the payment type is selected, returns payment type; otherwise, returns null
     * @Author Balaji N
     */
    public String get_totaldueamount_InvoiceDetails_unpaidInvoiceTab() {
        // return totaldueamt_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage.getText();
        return getElementText(totaldueamt_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage, "Total Due Amount text on Invoice Details Section on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Enters the payment amount on the invoice details section on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void EnterPaymentAmount_InvoiceDetails_UnpaidInvoiceTab() {
        ClickAndType(paymentamount_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage, get_totaldueamount_InvoiceDetails_unpaidInvoiceTab(), "Payment Amount textbox field on Invoice Details Section on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Enters the payment amount on the invoice details section on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @param paymentamount the payment amount
     * @Author Balaji N
     */
    public void Enter_PaymentAmount_InvoiceDetails_UnpaidInvoiceTab(String paymentamount) {
        ClickAndType(paymentamount_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage, paymentamount, "Payment Amount textbox field on Invoice Details Section on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the cancel button on the invoice details section on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_cancelButton_InvoiceDetails_UnpaidInvoiceTab() {
        js_Click(cancelButton_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage, "Cancel button on Invoice Details Section on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the submit button on the invoice details section on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_SubmitButton_InvoiceDetails_UnpaidInvoiceTab() {
        js_Click(submitButton_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage, "Submit button on Invoice Details Section on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies the success message on the invoice details section on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the success message is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_SuccessMessage_InvoiceDetails_UnpaidInvoiceTab() {
        return is_Element_Displayed(paymentSuccessMessage_InvoiceDetailssection_UnpaidTabOnPhoneOrderPage, "Payment Success Message on Invoice Details Section on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the selected cash registry on the invoice details section on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the cash registry is selected, returns cash registry; otherwise, returns null
     * @Author Balaji N
     */
    public String get_SelectedCashRegistry_InvoiceDetails_UnpaidInvoiceTab() {
      /*  HighlightElement(cashregister_ConfirmationSection_UnpaidTabOnPhoneOrderPage);
        s = new Select(cashregister_ConfirmationSection_UnpaidTabOnPhoneOrderPage);
        return (s.getFirstSelectedOption().getText());*/
        return get_Selected_Option(cashregister_ConfirmationSection_UnpaidTabOnPhoneOrderPage, "Cash Registry dropdown field value - Invoice Payment Details Section on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the entered email address on the invoice details section on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the email address is entered, returns email address; otherwise, returns null
     * @Author Balaji N
     */
    public String get_emailreceipt_UnpaidInvoiceTab() {
     /*   HighlightElement(emailtextbox_ConfirmationSection_UnpaidTabOnPhoneOrderPage);
        return emailtextbox_ConfirmationSection_UnpaidTabOnPhoneOrderPage.getAttribute("value");
*/
        return getElementAttribute(emailtextbox_ConfirmationSection_UnpaidTabOnPhoneOrderPage, "Email Recipt textbox field value on Invoice Payment Details Section on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the back button on the invoice details section on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void ClickBackButton_ConfirmationSection_UnpaidInvoiceTab() {
        click(backButton_ConfirmationSection_UnpaidTabOnPhoneOrderPage, "Back button on Invoice Payment Details Section on Unpaid Invoice Tab on customer details popup in the phone order page");
        // wait_For_Page_To_Be_Stable(getDriver());
    }

    /**
     * Clicks the print receipt button on the invoice details section on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_PrintRecieptButton_ConfirmationSection_UnpaidInvoiceTab() {
        Click(printReceiptButton_ConfirmationSection_UnpaidTabOnPhoneOrderPage, "Receipt button on Invoice Payment Details Section on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the email receipt button on the invoice details section on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_EmailRecieptButton_ConfirmationSection_UnpaidInvoiceTab() {
        js_Click(emailReceiptButton_ConfirmationSection_UnpaidTabOnPhoneOrderPage, "Print Receipt button on Invoice Payment Details Section on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies the unpaid invoice table on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the unpaid invoice table is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_UnpaidInvoiceTable_UnpaidInvoiceTab() {
        int maxRetries = 3;
        int waitTimeInSeconds = 2;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                if (isElementDisplayed(unpaidInvoiceTable_UnpaidTabOnPhoneOrderPage,
                        "Unpaid Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page")) {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Attempt " + attempt + " failed: " + e.getMessage());
            }

            try {
                Thread.sleep(waitTimeInSeconds * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("Unpaid Invoice Table was not visible after " + maxRetries + " attempts.");
        return false;
    }


    /**
     * Enters the invoice number on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @param invoice the invoice number to be entered
     * @Author Balaji N
     */
    public void Enter_InvoiceSearchext_UnpaidInvoiceTab(String invoice) {
        CustomerDetailsPopup_Invoicesearchtextbox_UnpaidTab.clear();
        delayWithGivenTime(1000);
        ClickAndType(CustomerDetailsPopup_Invoicesearchtextbox_UnpaidTab, invoice, "Invoice Search textbox field on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the entered invoice number on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the invoice number is entered, returns invoice number; otherwise, returns null
     * @Author Balaji N
     */
    public String get_EnteredInvoiceSearchext_UnpaidInvoiceTab() {
        // return CustomerDetailsPopup_Invoicesearchtextbox_UnpaidTab.getAttribute("value");
        return get_element_attribute(CustomerDetailsPopup_Invoicesearchtextbox_UnpaidTab, "Search textbox field value on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Presses enter key on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void PressEnterKey() {
        try {
            delayWithGivenTime(1000);
            CustomerDetailsPopup_Invoicesearchtextbox_UnpaidTab.sendKeys(Keys.ENTER);
            RobotPressEnter();
        } catch (Exception e) {
            System.err.println("Unable to press enter key on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page");
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the invoice number from the unpaid invoice table on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the invoice number is displayed, returns invoice number; otherwise, returns null
     * @Author Balaji N
     */
    public String get_InvoiceNumber_UnpaidInvoiceTable_OnUnpaidInvoiceTab() {
      /*  HighlightElement(InvoiceNumber_Row1_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage);
        return InvoiceNumber_Row1_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage.getText();
*/
        return getElementText(InvoiceNumber_Row1_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage, "Invoice Number Row 1 on Unpaid Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies if the auto-suggestion for the invoice number is present
     * in the Unpaid Invoice Table on the Unpaid Invoice Tab.
     *
     * @param threedigitinvoicenumber The three-digit invoice number to search for.
     * @return true if the invoice number is found in the suggestions, false otherwise.
     * @throws NoSuchElementException         if the list of suggestions is not found.
     * @throws StaleElementReferenceException if the DOM is updated and elements become stale.
     * @throws WebDriverException             for any unexpected WebDriver-related issues.
     * @Author Balaji N
     */
    public boolean Verify_AutoSuggestion_InvoiceNumber_UnpaidInvoiceTable_OnUnpaidInvoiceTab(String threedigitinvoicenumber) {
        boolean isValid = false;
        try {
            if (ListOfInvoiceNumber_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage == null ||
                    ListOfInvoiceNumber_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage.isEmpty()) {
                throw new NoSuchElementException("No invoice suggestions found in the Unpaid Invoice Table.");
            }

            for (WebElement suggestion : ListOfInvoiceNumber_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage) {
                if (suggestion.getText().contains(threedigitinvoicenumber)) {
                    isValid = true;
                    break; // Stop iteration once found
                }
            }
        } catch (NoSuchElementException e) {
            System.err.println("Exception: " + e.getMessage());
        } catch (StaleElementReferenceException e) {
            System.err.println("Exception: DOM has changed, elements are stale - " + e.getMessage());
        } catch (WebDriverException e) {
            System.err.println("Unexpected WebDriver error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
        }
        return isValid;
    }

    /**
     * Retrieves the total number of invoice numbers displayed in the
     * Unpaid Invoice Table on the Unpaid Invoice Tab.
     *
     * @return The total count of invoice numbers found in the table.
     * @throws NoSuchElementException         if the list of invoice numbers is not found.
     * @throws StaleElementReferenceException if the DOM updates and elements become stale.
     * @throws WebDriverException             for any unexpected WebDriver-related issues.
     * @Author Balaji N
     */
    public int Verify_ListofInvoiceNumbers_Appears_InUnpaidInvoiceTable_OnUnpaidInvoiceTab() {
        int totalInvoiceCount = 0;
        try {
            if (ListOfInvoiceNumber_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage == null) {
                throw new NoSuchElementException("Invoice number list is not found on the Unpaid Invoice Tab.");
            }
            totalInvoiceCount = ListOfInvoiceNumber_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage.size();
            System.out.println("Displayed invoice total count is: " + totalInvoiceCount);
        } catch (NoSuchElementException e) {
            System.err.println("Exception: " + e.getMessage());
        } catch (StaleElementReferenceException e) {
            System.err.println("Exception: DOM has changed, elements are stale - " + e.getMessage());
        } catch (WebDriverException e) {
            System.err.println("Unexpected WebDriver error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
        }
        return totalInvoiceCount;
    }


    /**
     * Verifies if no unpaid invoice is found in the Unpaid Invoice Table on the Unpaid Invoice Tab.
     *
     * @return If the no unpaid invoice is found, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_NoUnpaidInvoiceFound() {
        return isElementDisplayed(NoUnpaidInvoiceFound_InvoiceTable_UnpaidTabOnPhoneOrderPage, "No Unpaid found on Unpaid Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies if the clear button appears on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the clear button is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_ClearButton_AppearsOnInvoiceSearchtextbox_UnpaidInvoiceTab() {
        return is_Element_Displayed(InvoiceClearbutton_Invoicesearchtextbox_UnpaidTab, "Clear button on invoice search textbox field on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the clear button on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_Clearbutton_Invoicesearchtextbox_UnpaidInvoiceTab() {
        Click(InvoiceClearbutton_Invoicesearchtextbox_UnpaidTab, "Clear button on invoice search textbox field on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Enters the paid amount on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @param paidamount the amount to be entered
     * @Author Balaji N
     */
    public void Enter_PaidAmount_OnInvoiceSearchBox_UnpaidInvoiceTab(String paidamount) {
        PaidAmtSearchTextbox_InvoiceTable_UnpaidTab_OnPhoneOrderPage.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(PaidAmtSearchTextbox_InvoiceTable_UnpaidTab_OnPhoneOrderPage, paidamount, "Paid Amount search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the paid amount on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the paid amount is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public String get_PaidAmount_UnpaidInvoiceTable_OnUnpaidInvoiceTab() {
      /*  HighlightElement(PaidAmtSearchTextbox_InvoiceTable_UnpaidTab_OnPhoneOrderPage);
        return PaidAmtSearchTextbox_InvoiceTable_UnpaidTab_OnPhoneOrderPage.getAttribute("value").trim();
*/
        return getElementAttribute(PaidAmtSearchTextbox_InvoiceTable_UnpaidTab_OnPhoneOrderPage, "Paid Amount search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies if the clear button appears on the paid amount search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the clear button is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_ClearButton_AppearsOnPaidAmtSearchtextbox_UnpaidInvoiceTab() {
       /* HighlightElement(InvoiceClearbutton_PaidAmtsearchtextbox_UnpaidTab);
        return InvoiceClearbutton_PaidAmtsearchtextbox_UnpaidTab.isDisplayed();
*/
        return is_Element_Displayed(InvoiceClearbutton_PaidAmtsearchtextbox_UnpaidTab, "Clear button on paid amount search textbox field on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the clear button on the paid amount search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @author Balaji N
     */
    public void Click_Clearbutton_PaidAmtsearchtextbox_UnpaidInvoiceTab() {
        Click(InvoiceClearbutton_PaidAmtsearchtextbox_UnpaidTab, "Clear button on paid amount search textbox field on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the last row checkbox on the unpaid invoice table on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_LastRowCheckbox_OnUnpaidInvoiceTable_UnpaidInvoiceTab() {
        js_Click(lastRow_Checkbox_InvoiceTable_UnpaidTabOnPhoneOrderPage, "Last row checkbox on Invoice Table - Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Enters the balance amount on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @param balanceamount the amount to be entered
     * @Author Balaji N
     */
    public void Enter_BalanceAmount_OnInvoiceSearchBox_UnpaidInvoiceTab(String balanceamount) {
        CustomerDetailsPopup_BalanceAmtsearchtextbox_UnpaidTab.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(CustomerDetailsPopup_BalanceAmtsearchtextbox_UnpaidTab, balanceamount, "Balance Amount search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the value of balance amount on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the balance amount is displayed, returns value of balance amount; otherwise, returns null
     * @Author Balaji N
     */
    public String get_BalanceAmount_UnpaidInvoiceTable_OnUnpaidInvoiceTab() {
        return getElementAttribute(CustomerDetailsPopup_BalanceAmtsearchtextbox_UnpaidTab, "Balance Amount search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies if the clear button appears on the balance amount search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the clear button is displayed on the balance amount search textbox, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_ClearButton_AppearsOnBalanceAmtSearchtextbox_UnpaidInvoiceTab() {
       /* HighlightElement(InvoiceClearbutton_BalanceAmtsearchtextbox_UnpaidTab);
        return InvoiceClearbutton_BalanceAmtsearchtextbox_UnpaidTab.isDisplayed();*/
        return is_Element_Displayed(InvoiceClearbutton_BalanceAmtsearchtextbox_UnpaidTab, "Clear button on balance amount search textbox field on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the clear button on the balance amount search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @author Balaji N
     */
    public void Click_Clearbutton_BalanceAmtsearchtextbox_UnpaidInvoiceTab() {
        Click(InvoiceClearbutton_BalanceAmtsearchtextbox_UnpaidTab, "Clear button on balance amount search textbox field on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Enters the product total on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @param producttotal the amount to be entered
     * @Author Balaji N
     */
    public void Enter_ProductTotal_OnInvoiceSearchBox_UnpaidInvoiceTab(String producttotal) {
        CustomerDetailsPopup_ProductTotalsearchtextbox_UnpaidTab.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(CustomerDetailsPopup_ProductTotalsearchtextbox_UnpaidTab, producttotal, "Product total search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the value of product total on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the product total is displayed, returns value of product total; otherwise, returns null
     * @Author Balaji N
     */
    public String get_ProductTotal_UnpaidInvoiceTable_OnUnpaidInvoiceTab() {
       /* HighlightElement(CustomerDetailsPopup_ProductTotalsearchtextbox_UnpaidTab);
        return CustomerDetailsPopup_ProductTotalsearchtextbox_UnpaidTab.getText();*/
        return getElementAttribute(CustomerDetailsPopup_ProductTotalsearchtextbox_UnpaidTab, "Product total search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * verifies if the clear button appears on the product total search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the clear button is displayed on the product total search textbox, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_ClearButton_AppearsOnProductTotalSearchtextbox_UnpaidInvoiceTab() {
      /*  HighlightElement(InvoiceClearbutton_ProductTotalsearchtextbox_UnpaidTab);
        return InvoiceClearbutton_ProductTotalsearchtextbox_UnpaidTab.isDisplayed();*/
        return is_Element_Displayed(InvoiceClearbutton_ProductTotalsearchtextbox_UnpaidTab, "Clear button on product total search textbox field on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the clear button on the product total search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @author Balaji N
     */
    public void Click_Clearbutton_ProductTotalsearchtextbox_UnpaidInvoiceTab() {
        Click(InvoiceClearbutton_ProductTotalsearchtextbox_UnpaidTab, "Clear button on product total search textbox field on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the value of product total on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the product total is displayed, returns value of product total; otherwise, returns null
     * @Author Balaji N
     */
    public String get_EnteredProductTotalSearchtext_UnpaidInvoiceTab() {
        //  return CustomerDetailsPopup_ProductTotalsearchtextbox_UnpaidTab.getAttribute("value");
        return get_element_attribute(CustomerDetailsPopup_ProductTotalsearchtextbox_UnpaidTab, "Product total search textbox field value on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the provided product total value is present in all displayed invoices.
     * <p>
     * This method iterates through a list of WebElements representing product total values
     * in the unpaid invoice table and checks if each value contains the expected product total.
     * If a match is found, the element is highlighted.
     * </p>
     *
     * @param producttotal The expected product total value to be verified.
     * @return {@code true} if all product total values match the expected value, otherwise {@code false}.
     * @throws StaleElementReferenceException if a WebElement becomes stale during iteration.
     * @throws NoSuchElementException         if the elements in the list are not found.
     * @throws TimeoutException               if the elements take too long to load.
     * @throws WebDriverException             for general WebDriver-related issues.
     * @throws NullPointerException           if the list of elements is not initialized.
     */
    public boolean Verify_ProductTotalValue_OnDisplayedInvoices(String producttotal) {
        boolean allValuesMatch = true;

        try {
            if (ListOfProductsTotal_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage == null) {
                throw new NullPointerException("ListOfProductsTotal_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage is null.");
            }

            for (WebElement cell : ListOfProductsTotal_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage) {
                try {
                    String productTotalValue = cell.getText();

                    if (productTotalValue.contains(producttotal)) {
                        HighlightElement(cell);
                    } else {
                        System.out.println("Validation Failed: " + productTotalValue);
                        allValuesMatch = false;
                    }

                } catch (StaleElementReferenceException e) {
                    System.err.println("StaleElementReferenceException: WebElement is no longer valid: " + e.getMessage());
                    allValuesMatch = false;
                } catch (NoSuchElementException e) {
                    System.err.println("NoSuchElementException: Unable to locate the element: " + e.getMessage());
                    allValuesMatch = false;
                } catch (WebDriverException e) {
                    System.err.println("WebDriverException: WebDriver encountered an issue: " + e.getMessage());
                    allValuesMatch = false;
                }
            }
        } catch (NullPointerException e) {
            System.err.println("NullPointerException: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Unexpected exception: " + e.getMessage());
            return false;
        }

        return allValuesMatch;
    }

    /**
     * Enters the delivery on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @param delivery the delivery amount to be entered
     * @author Balaji N
     */
    public void Enter_Delivery_OnInvoiceSearchBox_UnpaidInvoiceTab(String delivery) {
        CustomerDetailsPopup_Deliverysearchtextbox_UnpaidTab.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(CustomerDetailsPopup_Deliverysearchtextbox_UnpaidTab, delivery, "Delivery column search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the value of delivery amount on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the delivery amount is displayed, returns value of delivery amount; otherwise, returns null
     * @author Balaji N
     */
    public String get_Delivery_UnpaidInvoiceTable_OnUnpaidInvoiceTab() {
        return getElementAttribute(CustomerDetailsPopup_Deliverysearchtextbox_UnpaidTab, "Delivery column search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * verifies if the clear button appears on the delivery column search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the clear button is displayed in delivery column search textbox, returns true; otherwise, returns false
     * @author Balaji N
     */
    public boolean Verify_ClearButton_AppearsOnDeliverySearchtextbox_UnpaidInvoiceTab() {
//        HighlightElement(InvoiceClearbutton_Deliverysearchtextbox_UnpaidTab);
//        return InvoiceClearbutton_Deliverysearchtextbox_UnpaidTab.isDisplayed();
        return is_Element_Displayed(InvoiceClearbutton_Deliverysearchtextbox_UnpaidTab, "Clear button on delivery column search textbox field on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the clear button on the delivery column search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @author Balaji N
     */
    public void Click_Clearbutton_Deliverysearchtextbox_UnpaidInvoiceTab() {
        Click(InvoiceClearbutton_Deliverysearchtextbox_UnpaidTab, "Delivery column search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * It retrieves the value of delivery on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the delivery is displayed, returns value of delivery; otherwise, returns null
     * @Author Balaji N
     */
    public String get_EnteredDeliverySearchtext_UnpaidInvoiceTab() {
        //  return CustomerDetailsPopup_Deliverysearchtextbox_UnpaidTab.getAttribute("value");
        return get_element_attribute(CustomerDetailsPopup_Deliverysearchtextbox_UnpaidTab, "Delivery column search textbox field value on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the provided delivery amount is present in all displayed invoices.
     * <p>
     * This method iterates through a list of WebElements representing delivery amounts
     * in the unpaid invoice table and checks if each value contains the expected delivery amount.
     * If a match is found, the element is highlighted.
     * </p>
     *
     * @param deliveryamt The expected delivery amount to be verified.
     * @return {@code true} if all delivery values match the expected amount, otherwise {@code false}.
     * @throws StaleElementReferenceException if a WebElement becomes stale during iteration.
     * @throws NoSuchElementException         if the elements in the list are not found.
     * @throws TimeoutException               if the elements take too long to load.
     * @throws WebDriverException             for general WebDriver-related issues.
     * @throws NullPointerException           if the list of elements is not initialized.
     * @throws Exception                      for any unexpected runtime errors.
     * @Author Balaji N
     */
    public boolean Verify_DeliveryValue_OnDisplayedInvoices(String deliveryamt) {
        boolean allValuesMatch = true;
        try {
            if (ListOfDelivery_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage == null) {
                throw new NullPointerException("ListOfDelivery_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage is null.");
            }

            for (WebElement cell : ListOfDelivery_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage) {
                try {
                    String deliveryValue = cell.getText();

                    if (deliveryValue.contains(deliveryamt)) {
                        HighlightElement(cell);
                    } else {
                        System.out.println("Validation Failed: Expected to contain '" + deliveryamt + "', but found '" + deliveryValue + "'");
                        allValuesMatch = false;
                    }

                } catch (StaleElementReferenceException e) {
                    System.err.println("StaleElementReferenceException: WebElement is no longer valid: " + e.getMessage());
                    allValuesMatch = false;
                } catch (NoSuchElementException e) {
                    System.err.println("NoSuchElementException: Unable to locate the element: " + e.getMessage());
                    allValuesMatch = false;
                } catch (WebDriverException e) {
                    System.err.println("WebDriverException: WebDriver encountered an issue: " + e.getMessage());
                    allValuesMatch = false;
                }
            }
        } catch (NullPointerException e) {
            System.err.println("NullPointerException: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Unexpected exception: " + e.getMessage());
            return false;
        }
        return allValuesMatch;
    }

    /**
     * Enters the tax on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @param tax the entered tax amount
     * @Author Balaji N
     */
    public void Enter_Tax_OnInvoiceSearchBox_UnpaidInvoiceTab(String tax) {
        CustomerDetailsPopup_Taxsearchtextbox_UnpaidTab.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(CustomerDetailsPopup_Taxsearchtextbox_UnpaidTab, tax, "Tax search textbox field at tax column on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * It retrieves the value of tax on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the tax is displayed, returns value of tax; otherwise, returns null
     * @Author Balaji N
     */
    public String get_Tax_UnpaidInvoiceTable_OnUnpaidInvoiceTab() {
        return getElementAttribute(CustomerDetailsPopup_Taxsearchtextbox_UnpaidTab, "Tax search textbox field at tax column on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * verifys whether the clear button appears on the tax search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the clear button is displayed in tax column search textbox, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_ClearButton_AppearsOnTaxSearchtextbox_UnpaidInvoiceTab() {
       /* HighlightElement(InvoiceClearbutton_Taxsearchtextbox_UnpaidTab);
        return InvoiceClearbutton_Taxsearchtextbox_UnpaidTab.isDisplayed();*/
        return is_Element_Displayed(InvoiceClearbutton_Taxsearchtextbox_UnpaidTab, "Clear button on tax column search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the clear button on the tax column search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_Clearbutton_Taxsearchtextbox_UnpaidInvoiceTab() {
        Click(InvoiceClearbutton_Taxsearchtextbox_UnpaidTab, "Clear button on tax column search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * It retrieves the value of tax on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the tax is displayed, returns value of tax; otherwise, returns null
     * @Author Balaji N
     */
    public String get_EnteredTaxSearchtext_UnpaidInvoiceTab() {
        // return CustomerDetailsPopup_Taxsearchtextbox_UnpaidTab.getAttribute("value");
        return get_element_attribute(CustomerDetailsPopup_Taxsearchtextbox_UnpaidTab, "Tax column search textbox field value on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }


    /**
     * Verifies whether the tax value displayed on all invoices matches the expected tax amount.
     * If a match is found, the element is highlighted.
     *
     * @param taxamount The expected tax amount as a string.
     * @return {@code true} if all displayed tax values contain the expected tax amount, otherwise {@code false}.
     * @throws NoSuchElementException         If the list of tax elements is empty or elements are not found.
     * @throws StaleElementReferenceException If a WebElement becomes stale due to page refresh or DOM update.
     * @throws NullPointerException           If the list of elements is null.
     * @Author Balaji N
     */
    public boolean Verify_TaxValue_OnDisplayedInvoices(String taxamount) {
        boolean allValuesMatch = true;

        try {
            if (ListOfTax_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage == null) {
                throw new NullPointerException("The list of tax elements is null.");
            }

            if (ListOfTax_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage.isEmpty()) {
                throw new NoSuchElementException("No tax values found in the invoice table.");
            }

            for (WebElement cell : ListOfTax_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage) {
                try {
                    String taxValue = cell.getText();
                    if (taxValue.contains(taxamount)) {
                        HighlightElement(cell);
                    } else {
                        System.out.println("Validation Failed: " + taxValue);
                        allValuesMatch = false;
                    }
                } catch (StaleElementReferenceException e) {
                    System.out.println("Element became stale: " + e.getMessage());
                    allValuesMatch = false;
                }
            }
        } catch (NoSuchElementException | NullPointerException e) {
            System.out.println("Exception occurred: " + e.getMessage());
            return false;
        }
        return allValuesMatch;
    }

    /**
     * Enters the total on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @param total the total amount
     * @Author Balaji N
     */
    public void Enter_Total_OnInvoiceSearchBox_UnpaidInvoiceTab(String total) {
        CustomerDetailsPopup_Totalsearchtextbox_UnpaidTab.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(CustomerDetailsPopup_Totalsearchtextbox_UnpaidTab, total, "Total search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the value of total on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the total is displayed, returns value of total; otherwise, returns null
     * @Author Balaji N
     */
    public String get_Total_UnpaidInvoiceTable_OnUnpaidInvoiceTab() {
       /* HighlightElement(CustomerDetailsPopup_Totalsearchtextbox_UnpaidTab);
        return CustomerDetailsPopup_Totalsearchtextbox_UnpaidTab.getText();*/
        return getElementAttribute(CustomerDetailsPopup_Totalsearchtextbox_UnpaidTab, "Total Search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the clear button appears on the total search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the clear button is displayed in total column search textbox, returns true; otherwise, returns false
     * @author Balaji N
     */
    public boolean Verify_ClearButton_AppearsOnTotalSearchtextbox_UnpaidInvoiceTab() {
       /* HighlightElement(InvoiceClearbutton_Totalsearchtextbox_UnpaidTab);
        return InvoiceClearbutton_Totalsearchtextbox_UnpaidTab.isDisplayed();*/
        return is_Element_Displayed(InvoiceClearbutton_Totalsearchtextbox_UnpaidTab, "Clear button on total column search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the clear button on the total column search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_Clearbutton_Totalsearchtextbox_UnpaidInvoiceTab() {
        Click(InvoiceClearbutton_Totalsearchtextbox_UnpaidTab, "Clear button on total column search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the value of total on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the total is displayed, returns value of total; otherwise, returns null
     * @Author Balaji N
     */
    public String get_EnteredTotalSearchtext_UnpaidInvoiceTab() {
        //  return CustomerDetailsPopup_Totalsearchtextbox_UnpaidTab.getAttribute("value");
        return get_element_attribute(CustomerDetailsPopup_Totalsearchtextbox_UnpaidTab, "Total column search textbox field value on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the total value displayed on all invoices matches the expected subtotal amount.
     * If a match is found, the element is highlighted.
     *
     * @param subtotalamount The expected subtotal amount as a string.
     * @return {@code true} if all displayed total values contain the expected subtotal amount, otherwise {@code false}.
     * @throws NoSuchElementException         If the list of total value elements is empty or elements are not found.
     * @throws StaleElementReferenceException If a WebElement becomes stale due to page refresh or DOM update.
     * @throws NullPointerException           If the list of elements is null.
     * @Author Balaji N
     */
    public boolean Verify_TotalValue_OnDisplayedInvoices(String subtotalamount) {
        boolean allValuesMatch = true;

        try {
            if (ListOfTotal_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage == null) {
                throw new NullPointerException("The list of total value elements is null.");
            }

            if (ListOfTotal_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage.isEmpty()) {
                throw new NoSuchElementException("No total values found in the invoice table.");
            }

            for (WebElement cell : ListOfTotal_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage) {
                try {
                    String totalValue = cell.getText();
                    if (totalValue.contains(subtotalamount)) {
                        HighlightElement(cell);
                    } else {
                        System.out.println("Validation Failed: " + totalValue);
                        allValuesMatch = false;
                    }
                } catch (StaleElementReferenceException e) {
                    System.out.println("Element became stale: " + e.getMessage());
                    allValuesMatch = false;
                }
            }
        } catch (NoSuchElementException | NullPointerException e) {
            System.out.println("Exception occurred: " + e.getMessage());
            return false;
        }

        return allValuesMatch;
    }

    /**
     * Enters the order date on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @param orderdate
     * @Author Balaji N
     */
    public void Enter_OrderDate_OnInvoiceSearchBox_UnpaidInvoiceTab(String orderdate) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                CustomerDetailsPopup_OrderDatesearchtextbox_UnpaidTab.clear();
                delayWithGivenTime(500);
                Double_Click_And_Type(CustomerDetailsPopup_OrderDatesearchtextbox_UnpaidTab, orderdate,
                        "Order date search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
                break; // Exit loop if successful
            } catch (StaleElementReferenceException e) {
                System.out.println("Caught StaleElementReferenceException. Retrying... Attempt: " + (attempts + 1));
                CustomerDetailsPopup_OrderDatesearchtextbox_UnpaidTab = getDriver().findElement(By.xpath("(//input[contains(@class,'k-textbox k-FullWidth')])[9]"));
                attempts++;
            }
        }
    }


    /**
     * Retrieves the value of order date on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the order date is displayed, returns value of order date; otherwise, returns null
     * @Author Balaji N
     */
    public String get_OrderDate_UnpaidInvoiceTable_OnUnpaidInvoiceTab() {
      /*  HighlightElement(CustomerDetailsPopup_OrderDatesearchtextbox_UnpaidTab);
        return CustomerDetailsPopup_OrderDatesearchtextbox_UnpaidTab.getText();*/
        return getElementAttribute(CustomerDetailsPopup_OrderDatesearchtextbox_UnpaidTab, "Order date search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the clear button appears on the order date search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the clear button is displayed in order date column search textbox, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_ClearButton_AppearsOnOrderDateSearchtextbox_UnpaidInvoiceTab() {
        /*HighlightElement(InvoiceClearbutton_OrderDatesearchtextbox_UnpaidTab);
        return InvoiceClearbutton_OrderDatesearchtextbox_UnpaidTab.isDisplayed();*/
        return is_Element_Displayed(InvoiceClearbutton_OrderDatesearchtextbox_UnpaidTab, "Order date search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the clear button on the order date search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_Clearbutton_OrderDatesearchtextbox_UnpaidInvoiceTab() {
        Click(InvoiceClearbutton_OrderDatesearchtextbox_UnpaidTab, "Clear button on order date search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the value of order date on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the order date is displayed, returns value of order date; otherwise, returns null
     * @Author Balaji N
     */
    public String get_EnteredOrderDateSearchtext_UnpaidInvoiceTab() {
        //  return CustomerDetailsPopup_OrderDatesearchtextbox_UnpaidTab.getAttribute("value");
        return getElementAttribute(CustomerDetailsPopup_OrderDatesearchtextbox_UnpaidTab, "Order date search textbox field value on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the order date value displayed on all invoices matches the expected order date.
     * If a match is found, the element is highlighted.
     *
     * @param orderdate
     * @return If the order date value displayed on all invoices matches the expected order date, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_OrderDateValue_OnDisplayedInvoices(String orderdate) {
        boolean allValuesMatch = true;
        for (WebElement cell : ListOfOrderDate_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage) {
            String totalValue = cell.getText();
            if (totalValue.contains(orderdate)) {
                HighlightElement(cell);
            } else {
                allValuesMatch = false;
            }
        }
        return allValuesMatch;
    }

    /**
     * Enters the delivery date on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @param orderdate If the order date is displayed, returns value of order date; otherwise, returns null
     * @Author Balaji N
     */
    public void Enter_DeliveryDate_OnInvoiceSearchBox_UnpaidInvoiceTab(String orderdate) {
        CustomerDetailsPopup_DeliveryDatesearchtextbox_UnpaidTab.clear();
        delayWithGivenTime(500);
        Double_Click_And_Type(CustomerDetailsPopup_DeliveryDatesearchtextbox_UnpaidTab, orderdate, "Delivery date search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the value of delivery date on the invoice search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the delivery date is displayed, returns value of delivery date; otherwise, returns nul
     * @Author Balaji N
     */
    public String get_DeliveryDate_UnpaidInvoiceTable_OnUnpaidInvoiceTab() {
        //  HighlightElement(CustomerDetailsPopup_DeliveryDatesearchtextbox_UnpaidTab);
        //  return CustomerDetailsPopup_DeliveryDatesearchtextbox_UnpaidTab.getText();
        return getElementAttribute(CustomerDetailsPopup_DeliveryDatesearchtextbox_UnpaidTab, "Delivery date value search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the clear button appears on the delivery date search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the clear button is displayed in delivery date column search textbox, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_ClearButton_AppearsOnDeliveryDateSearchtextbox_UnpaidInvoiceTab() {
        //HighlightElement(InvoiceClearbutton_DeliveryDatesearchtextbox_UnpaidTab);
        //return InvoiceClearbutton_DeliveryDatesearchtextbox_UnpaidTab.isDisplayed();
        return is_Element_Displayed(InvoiceClearbutton_DeliveryDatesearchtextbox_UnpaidTab, "Clear button on delivery date search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the clear button on the delivery date search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_Clearbutton_DeliveryDatesearchtextbox_UnpaidInvoiceTab() {
        Click(InvoiceClearbutton_DeliveryDatesearchtextbox_UnpaidTab, "Clear button on delivery date search textbox field on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * It retrieves the delivery date value entered in the delivery date search textbox on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @return If the delivery date is displayed, returns value of delivery date; otherwise, returns null
     * @Author Balaji N
     */
    public String get_EnteredDeliveryDateSearchtext_UnpaidInvoiceTab() {
        //return CustomerDetailsPopup_DeliveryDatesearchtextbox_UnpaidTab.getAttribute("value");
        return get_element_attribute(CustomerDetailsPopup_DeliveryDatesearchtextbox_UnpaidTab, "Delivery date search textbox field value on Invoice Table on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * verifys whether the delivery date value displayed on all invoices matches the expected delivery date.
     *
     * @param deliverydate
     * @return If the delivery date value displayed on all invoices matches the expected delivery date, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_DeliveryDateValue_OnDisplayedInvoices(String deliverydate) {
        boolean allValuesMatch = true;
        for (WebElement cell : ListOfDeliveryDate_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage) {
            String DeliveryValue = cell.getText();
            if (DeliveryValue.contains(deliverydate)) {
                HighlightElement(cell);
            } else {
                System.out.println("Validation Failed: " + DeliveryValue);
                allValuesMatch = false;
            }
        }
        return allValuesMatch;
    }

    public void Verify_InvoicesAreSortedInAscendingOrder() {
        List<String> originalInvoiceNumbers = new ArrayList<>();
        for (WebElement invoiceElement : ListOfInvoiceNumber_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage) {
            originalInvoiceNumbers.add(invoiceElement.getText());
        }
    }

    /**
     * Clicks the invoice label on the unpaid invoice tab on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void ClickInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup() {
        Click(InvoiceLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup, "Invoice label on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verify uparrow appears on invoice label on unpaid invoice tab in the customer details popup
     *
     * @return If the up arrow appears on invoice label it returns true else returns false
     * @Author Balaji N
     */
    public boolean Verify_Uparrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup() {
        return is_Element_Displayed(Invoice_uparrow_UnpaidInvoiceTab_OnCustomerDetailsPopup, "Up arrow on Invoice label on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verify whether down arrow appears on invoice label on unpaid invoice tab in the customer details popup
     *
     * @return If the down arrow appears on invoice label it returns true else returns false
     * @Author Balaji N
     */
    public boolean Verify_Downarrow_Appears_OnInvoiceLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(Invoice_downarrow_UnpaidInvoiceTab_OnCustomerDetailsPopup));
        return is_Element_Displayed(Invoice_downarrow_UnpaidInvoiceTab_OnCustomerDetailsPopup, "Down arrow on Invoice label on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the invoice numbers in the unpaid invoice table on the unpaid invoice tab
     * in the customer details popup on the phone order page are sorted in ascending order.
     *
     * @return {@code true} if the invoice numbers are sorted in ascending order; otherwise, {@code false}.
     * @throws NoSuchElementException         if the invoice elements are not found on the page.
     * @throws StaleElementReferenceException if the invoice elements become stale during execution.
     * @throws TimeoutException               if the elements do not load within the expected time.
     * @Author Balaji N
     */
    public boolean Verify_InvoiceHeader_DefaultOrder() {
        try {
            delayWithGivenTime(1000);
            List<String> originalInvoiceNumbers = new ArrayList<>();
            for (WebElement invoiceElement : ListOfInvoiceNumber_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage) {
                originalInvoiceNumbers.add(invoiceElement.getText());
            }

            delayWithGivenTime(2000);
            List<String> sortedInvoiceNumbers = new ArrayList<>(originalInvoiceNumbers);
            Collections.sort(sortedInvoiceNumbers);
            return originalInvoiceNumbers.containsAll(sortedInvoiceNumbers);

        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Unable to find invoice elements in the unpaid invoice table.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Invoice elements became stale during execution.", e);
        } catch (TimeoutException e) {
            throw new TimeoutException("Invoice elements did not load within the expected time.");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while verifying invoice sorting.", e);
        }
    }


    /**
     * Verifies whether the invoice numbers in the unpaid invoice table on the unpaid invoice tab
     * in the customer details popup on the phone order page are sorted in ascending order.
     *
     * @return {@code true} if the invoice numbers are sorted in ascending order; otherwise, {@code false}.
     * @throws NoSuchElementException         if the invoice elements are not found on the page.
     * @throws StaleElementReferenceException if the invoice elements become stale during execution.
     * @throws TimeoutException               if the elements do not load within the expected time.
     * @throws RuntimeException               if any unexpected error occurs during execution.
     * @Author Balaji N
     */
    public boolean Verify_InvoiceHeader_Sorted_ascending() {
        try {
            // Store the invoice numbers in a list
            List<String> originalInvoiceNumbers = new ArrayList<>();
            for (WebElement invoiceElement : ListOfInvoiceNumber_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage) {
                originalInvoiceNumbers.add(invoiceElement.getText());
            }

            // Introduce a delay to allow all elements to load completely
            delayWithGivenTime(2000);

            // Create a copy of the list and sort it
            List<String> sortedInvoiceNumbers = new ArrayList<>(originalInvoiceNumbers);
            Collections.sort(sortedInvoiceNumbers);

            // Compare the original list with the sorted list
            if (originalInvoiceNumbers.equals(sortedInvoiceNumbers)) {
                return true;
            } else {
                return false;
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Unable to find invoice elements in the unpaid invoice table.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Invoice elements became stale during execution.", e);
        } catch (TimeoutException e) {
            throw new TimeoutException("Invoice elements did not load within the expected time.");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while verifying invoice sorting.", e);
        }
    }


    /**
     * Verifies whether the invoice numbers in the unpaid invoice table on the unpaid invoice tab
     * in the customer details popup on the phone order page are sorted in descending order.
     *
     * @return {@code true} if the invoice numbers are sorted in descending order; otherwise, {@code false}.
     * @throws NoSuchElementException         if the invoice elements are not found on the page.
     * @throws StaleElementReferenceException if the invoice elements become stale during execution.
     * @throws TimeoutException               if the elements do not load within the expected time.
     * @throws RuntimeException               if any unexpected error occurs during execution.
     * @Author Balaji N
     */
    public boolean Verify_InvoiceHeader_Sorted_descending() {
        try {
            // Store the invoice numbers in a list
            List<String> originalInvoiceNumbers = new ArrayList<>();
            for (WebElement invoiceElement : ListOfInvoiceNumber_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage) {
                originalInvoiceNumbers.add(invoiceElement.getText());
            }

            // Introduce a delay to allow elements to load completely
            delayWithGivenTime(2000);

            // Create a copy of the list and sort it in descending order
            List<String> sortedInvoiceNumbers = new ArrayList<>(originalInvoiceNumbers);
            Collections.sort(sortedInvoiceNumbers, Collections.reverseOrder());

            // Compare the original list with the sorted list
            if (originalInvoiceNumbers.equals(sortedInvoiceNumbers)) {
                return true;
            } else {
                System.out.println("Original invoice numbers: " + originalInvoiceNumbers);
                System.out.println("Sorted invoice numbers (expected descending order): " + sortedInvoiceNumbers);
                System.out.println("Invoices are not sorted in descending order.");
                return false;
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Unable to find invoice elements in the unpaid invoice table.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Invoice elements became stale during execution.", e);
        } catch (TimeoutException e) {
            throw new TimeoutException("Invoice elements did not load within the expected time.");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while verifying invoice sorting.", e);
        }
    }

    /**
     * Clicks the paid amount label on the unpaid invoice tab on the customer details popup in the phone order page.
     *
     * @Author Balaji N
     */
    public void Click_PaidAmtLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup() {
        Click(PaidAmtLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup, "Paid Amount Label on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the paid amounts in the unpaid invoice table on the unpaid invoice tab
     * in the customer details popup on the phone order page are in their default order.
     *
     * @return {@code true} if the paid amounts are in default order; otherwise, {@code false}.
     * @throws NoSuchElementException         if the paid amount elements are not found on the page.
     * @throws StaleElementReferenceException if the elements become stale during execution.
     * @throws TimeoutException               if the elements do not load within the expected time.
     * @throws RuntimeException               if any unexpected error occurs during execution.
     * @Author Balaji N
     */
    public boolean Verify_PaidAmtHeader_DefaultOrder() {
        try {
            List<String> originalPaidAmt = new ArrayList<>();
            for (WebElement paidAmtElement : ListOfPaidAmt_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
                originalPaidAmt.add(paidAmtElement.getText());
            }

            // Introduce a delay to allow elements to load completely
            delayWithGivenTime(2000);

            List<String> sortedPaidAmt = new ArrayList<>(originalPaidAmt);
            Collections.sort(sortedPaidAmt);

            if (originalPaidAmt.equals(sortedPaidAmt)) {
                System.out.println("Paid amounts are in default order.");
                return true;
            } else {
                System.out.println("Original Paid Amts: " + originalPaidAmt);
                System.out.println("Sorted Paid Amts (Expected Default Order): " + sortedPaidAmt);
                System.out.println("Paid amounts are not in default order.");
                return false;
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Unable to find paid amount elements.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Paid amount elements became stale during execution.", e);
        } catch (TimeoutException e) {
            throw new TimeoutException("Paid amount elements did not load within the expected time.");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while verifying paid amount sorting.", e);
        }
    }

    /**
     * Verifies whether the paid amounts in the unpaid invoice table are sorted in ascending order.
     *
     * @return {@code true} if the paid amounts are sorted in ascending order; otherwise, {@code false}.
     * @throws NoSuchElementException         if the paid amount elements are not found on the page.
     * @throws StaleElementReferenceException if the elements become stale during execution.
     * @throws TimeoutException               if the elements do not load within the expected time.
     * @throws RuntimeException               if any unexpected error occurs during execution.
     * @Author Balaji N
     */
    public boolean Verify_PaidAmtHeader_Sorted_ascending() {
        try {
            List<String> originalPaidAmt = new ArrayList<>();
            for (WebElement paidAmtElement : ListOfPaidAmt_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
                originalPaidAmt.add(paidAmtElement.getText());
            }

            delayWithGivenTime(2000);

            List<String> sortedPaidAmt = new ArrayList<>(originalPaidAmt);
            Collections.sort(sortedPaidAmt);

            if (originalPaidAmt.equals(sortedPaidAmt)) {
                return true;
            } else {
                System.out.println("Original Paid Amts: " + originalPaidAmt);
                System.out.println("Sorted Paid Amts (Expected Ascending Order): " + sortedPaidAmt);
                System.out.println("Paid amounts are not sorted in ascending order.");
                return false;
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Unable to find paid amount elements.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Paid amount elements became stale during execution.", e);
        } catch (TimeoutException e) {
            throw new TimeoutException("Paid amount elements did not load within the expected time.");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while verifying paid amount sorting.", e);
        }
    }

    /**
     * Verifies whether the paid amounts in the unpaid invoice table are sorted in descending order.
     *
     * @return {@code true} if the paid amounts are sorted in descending order; otherwise, {@code false}.
     * @throws NoSuchElementException         if the paid amount elements are not found on the page.
     * @throws StaleElementReferenceException if the elements become stale during execution.
     * @throws TimeoutException               if the elements do not load within the expected time.
     * @throws RuntimeException               if any unexpected error occurs during execution.
     * @Author Balaji N
     */
    public boolean Verify_PaidAmtHeader_Sorted_descending() {
        try {
            List<String> originalPaidAmt = new ArrayList<>();
            for (WebElement paidAmtElement : ListOfPaidAmt_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
                originalPaidAmt.add(paidAmtElement.getText());
            }

            delayWithGivenTime(2000);

            List<String> sortedPaidAmt = new ArrayList<>(originalPaidAmt);
            Collections.sort(sortedPaidAmt, Collections.reverseOrder());

            if (originalPaidAmt.equals(sortedPaidAmt)) {
                return true;
            } else {
                System.out.println("Original Paid Amts: " + originalPaidAmt);
                System.out.println("Sorted Paid Amts (Expected Descending Order): " + sortedPaidAmt);
                System.out.println("Paid amounts are not sorted in descending order.");
                return false;
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Unable to find paid amount elements.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Paid amount elements became stale during execution.", e);
        } catch (TimeoutException e) {
            throw new TimeoutException("Paid amount elements did not load within the expected time.");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while verifying paid amount sorting.", e);
        }
    }

    /**
     * Clicks the balance amount label on the unpaid invoice tab on the customer details popup in the phone order page.
     *
     * @Author Balaji N
     */
    public void Click_BalanceAmtLabel_OnCustomerDetailsPopup() {
        Click(BalanceAmtLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup, "Balance Amount Label on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the balance amounts in the unpaid invoice table are in their default order.
     *
     * @return If the balance amounts are in their default order, returns false; otherwise, returns true
     * @Author Balaji N
     */
    public boolean Verify_BalanceAmtHeader_DefaultOrder() {
        List<String> originalBalanceAmt = new ArrayList<>();
        for (WebElement invoiceElement : ListOfBalanceAmt_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            originalBalanceAmt.add(invoiceElement.getText());
        }
        delayWithGivenTime(2000);
        List<String> sortedInvoiceNumbers = new ArrayList<>(originalBalanceAmt);
        Collections.sort(sortedInvoiceNumbers);

        if (originalBalanceAmt.equals(sortedInvoiceNumbers)) {
            System.out.println("Original Balance Amts: " + originalBalanceAmt);
            System.out.println("Sorted Balance Amts: " + sortedInvoiceNumbers);
            System.out.println("Balance amount are sorted in default order.");
            return false;
        } else {
            System.out.println("Original Balance Amts: " + originalBalanceAmt);
            System.out.println("Sorted Balance Amts: " + sortedInvoiceNumbers);
            System.out.println("Balance amount are not sorted in default order.");
            return true;
        }
    }

    /**
     * Verifies whether the balance amounts in the unpaid invoice table are sorted in ascending order.
     *
     * @return If the balance amounts are sorted in ascending order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_BalanceAmtHeader_Sorted_ascending() {
        List<String> originalBalanceAmt = new ArrayList<>();
        for (WebElement balanceAmtElement : ListOfBalanceAmt_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            originalBalanceAmt.add(balanceAmtElement.getText());
        }
        List<String> sortedInvoiceNumbers = new ArrayList<>(originalBalanceAmt);
        Collections.sort(sortedInvoiceNumbers);

        if (originalBalanceAmt.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Original Balance Amts: " + originalBalanceAmt);
            System.out.println("Sorted Balance Amts: " + sortedInvoiceNumbers);
            System.out.println("Balance amount are not sorted in ascending order.");
            return false;
        }
    }

    /**
     * Verifies whether the balance amounts in the unpaid invoice table are sorted in descending order.
     *
     * @return If the balance amounts are sorted in descending order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_BalanceAmtHeader_Sorted_descending() {
        List<String> originalBalanceAmt = new ArrayList<>();
        for (WebElement balanceAmtElement : ListOfBalanceAmt_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            originalBalanceAmt.add(balanceAmtElement.getText());
        }

        List<String> sortedInvoiceNumbers = new ArrayList<>(originalBalanceAmt);
        Collections.sort(sortedInvoiceNumbers, Collections.reverseOrder());

        if (originalBalanceAmt.containsAll(sortedInvoiceNumbers)) {
            System.out.println("Balance Amount are not sorted in descending order.");
            return true;
        } else {
            System.out.println("Balance Amount are not sorted in descending order.");
            return false;
        }
    }

    /**
     * Clicks the product total label on the unpaid invoice tab on the customer details popup in the phone order page.
     *
     * @Author Balaji N
     */
    public void Click_ProductTotalLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup() {
        Click(ProductTotalLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup, "Product Total Label on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the product total amounts in the unpaid invoice table are sorted in ascending order.
     *
     * @return If the product total amounts are sorted in ascending order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_ProductTotalHeader_Sorted_ascending() {
        List<String> originalProductTotal = new ArrayList<>();
        for (WebElement productTotalElement : ListOfProductsTotal_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage) {
            originalProductTotal.add(productTotalElement.getText());
        }

        List<String> sortedInvoiceNumbers = new ArrayList<>(originalProductTotal);
        Collections.sort(sortedInvoiceNumbers);

        if (originalProductTotal.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Original Balance Amts: " + originalProductTotal);
            System.out.println("Sorted Balance Amts: " + sortedInvoiceNumbers);
            System.out.println("Balance amount are not sorted in ascending order.");
            return false;
        }

    }

    /**
     * Verifies whether the down arrow appears on the product total label on the unpaid invoice tab on the customer details popup in the phone order page.
     *
     * @return If the down arrow appears on the product total label, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_Downarrow_Appears_OnProductTotalLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup() {
      /*  HighlightElement(InvoiceLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup);
        return Invoice_downarrow_UnpaidInvoiceTab_OnCustomerDetailsPopup.isDisplayed();*/
        return is_Element_Displayed(InvoiceLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup, "Product Total Label on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the product total amounts in the unpaid invoice table are sorted in descending order.
     *
     * @return If the product total amounts are sorted in descending order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_ProductTotalHeader_Sorted_descending() {
        List<String> originalProductTotal = new ArrayList<>();
        for (WebElement prodTotalElement : ListOfProductsTotal_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage) {
            originalProductTotal.add(prodTotalElement.getText());
        }
        delayWithGivenTime(2000);
        List<String> sortedInvoiceNumbers = new ArrayList<>(originalProductTotal);
        Collections.sort(sortedInvoiceNumbers, Collections.reverseOrder());

        if (originalProductTotal.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Product total are not sorted in descending order.");
            return false;
        }
    }

    /**
     * Clicks the delivery label on the unpaid invoice tab on the customer details popup in the phone order page.
     *
     * @Author Balaji N
     */
    public void Click_DeliveryLabel_OnUnpaidTab_OnCustomerDetailsPopup() {
        Click(DeliveryLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, "Delivery Label on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the delivery amounts in the unpaid invoice table are sorted in ascending order.
     *
     * @return If the delivery amounts are sorted in ascending order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_DeliveryHeader_Sorted_ascending() {
        List<String> originalProductTotal = new ArrayList<>();
        for (WebElement productTotalElement : ListOfDelivery_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            originalProductTotal.add(productTotalElement.getText());
        }

        // Create a copy of the list and sort it
        List<String> sortedInvoiceNumbers = new ArrayList<>(originalProductTotal);
        Collections.sort(sortedInvoiceNumbers);

        // Compare the original list with the sorted list
        if (originalProductTotal.equals(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Original delivery Amts: " + originalProductTotal);
            System.out.println("Sorted delivery Amts: " + sortedInvoiceNumbers);
            System.out.println("Delivery amount are not sorted in ascending order.");
            return false;
        }
    }

    /**
     * Verifies whether the delivery amounts in the unpaid invoice table are sorted in descending order.
     *
     * @return If the delivery amounts are sorted in descending order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_DeliveryHeader_DefaultOrder() {
        List<String> actualOrder = new ArrayList<>();
        // Retrieve the text from each web element and store it in the actualOrder list
        for (WebElement productTotalElement : ListOfDelivery_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            actualOrder.add(productTotalElement.getText().trim());
        }
        List<String> expectedOrder = new ArrayList<>(actualOrder);

        return actualOrder.equals(expectedOrder);
    }

    /**
     * Verifies whether the down arrow appears on the delivery label on the unpaid invoice tab on the customer details popup in the phone order page.
     *
     * @return If the down arrow appears on the delivery label, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_Downarrow_Appears_OnDeliveryLabel_OnUnpaidInvoiceTab_OnCustomerDetailsPopup() {
        // HighlightElement(InvoiceLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup);
        // return Invoice_downarrow_UnpaidInvoiceTab_OnCustomerDetailsPopup.isDisplayed();
        return is_Element_Displayed(DeliveryLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, "Delivery Label on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the delivery amounts in the unpaid invoice table are sorted in descending order.
     *
     * @return If the delivery amounts are sorted in descending order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_DeliveryHeader_Sorted_descending() {
        List<String> originalProductTotal = new ArrayList<>();
        for (WebElement prodTotalElement : ListOfDelivery_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            originalProductTotal.add(prodTotalElement.getText());
        }
        delayWithGivenTime(2000);
        List<String> sortedInvoiceNumbers = new ArrayList<>(originalProductTotal);
        Collections.sort(sortedInvoiceNumbers, Collections.reverseOrder());

        if (originalProductTotal.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Product total are not sorted in descending order.");
            return false;
        }

    }

    /**
     * Clicks the tax label on the unpaid invoice tab on the customer details popup in the phone order page.
     *
     * @Author Balaji N
     */
    public void Click_TaxLabel_OnUnpaidTab_OnCustomerDetailsPopup() {
        Click(TaxLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, "Tax Label on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the tax amounts in the unpaid invoice table are sorted in ascending order.
     *
     * @return If the tax amounts are sorted in ascending order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_TaxHeader_Sorted_ascending() {
        List<String> originalTax = new ArrayList<>();
        for (WebElement taxElement : ListOfTax_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            originalTax.add(taxElement.getText());
        }

        // Create a copy of the list and sort it
        List<String> sortedtax = new ArrayList<>(originalTax);
        Collections.sort(sortedtax);

        // Compare the original list with the sorted list
        if (originalTax.containsAll(sortedtax)) {
            return true;
        } else {
            System.out.println("Original Tax Amts: " + originalTax);
            System.out.println("Sorted Tax Amts: " + sortedtax);
            System.out.println("Tax are not sorted in ascending order.");
            return false;
        }
    }

    /**
     * Verifies whether the tax column in the unpaid invoice table are sorted in descending order.
     *
     * @return If the tax column are sorted in descending order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean verify_TaxHeader_Sorted_Descending() {
        List<String> originaltax = new ArrayList<>();
        for (WebElement taxElement : ListOfTax_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            originaltax.add(taxElement.getText());
        }

        List<String> sortedtax = new ArrayList<>(originaltax);
        Collections.sort(sortedtax, Collections.reverseOrder());

        if (originaltax.containsAll(sortedtax)) {
            return true;
        } else {
            System.out.println("Original Tax Amts: " + originaltax);
            System.out.println("Sorted Tax Amts: " + sortedtax);
            System.out.println("Tax are not sorted in descending order.");
            return false;
        }
    }

    private List<String> defaultTax;

    public void captureDefaultOrder() {
        defaultTax = new ArrayList<>();
        for (WebElement taxElement : ListOfTax_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            defaultTax.add(taxElement.getText());
        }
    }

    /**
     * Verifies whether the tax column in the unpaid invoice table are sorted in default order.
     *
     * @return If the tax column are sorted in default order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean verify_Delivery_Sorted_DefaultOrder() {
        List<String> currenttax = new ArrayList<>();
        for (WebElement taxElement : ListOfTax_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            currenttax.add(taxElement.getText());
        }

        if (currenttax.equals(defaultTax)) {
            return true;
        } else {
            System.out.println("Default tax Amts: " + defaultTax);
            System.out.println("Current tax Amts: " + currenttax);
            System.out.println("Tax amounts are not in default order.");
            return false;
        }
    }

    /**
     * Clicks the late fee label on the unpaid invoice tab on the customer details popup in the phone order page.
     *
     * @Author Balaji N
     */
    public void Click_LateFee_Label_OnUnpaidTab_OnCustomerDetailsPopup() {
        Click(LatefeeLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, "Late fee Label on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the late fee amounts in the unpaid invoice table are sorted in ascending order.
     *
     * @return If the late fee amounts are sorted in ascending order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_LatefeeHeader_Sorted_ascending() {
        List<String> originallatefee = new ArrayList<>();
        for (WebElement latefeeElement : ListOflatefee_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            originallatefee.add(latefeeElement.getText());
        }

        // Create a copy of the list and sort it
        List<String> sortedInvoiceNumbers = new ArrayList<>(originallatefee);
        Collections.sort(sortedInvoiceNumbers);

        // Compare the original list with the sorted list
        if (originallatefee.equals(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Original late fee Amts: " + originallatefee);
            System.out.println("Sorted late fee Amts: " + sortedInvoiceNumbers);
            System.out.println("Late fees are not sorted in ascending order.");
            return false;
        }
    }

    /**
     * Verifies whether the late fee amounts in the unpaid invoice table are sorted in descending order.
     *
     * @return If the late fee amounts are sorted in descending order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean verify_LatefeeHeader_Sorted_Descending() {
        List<String> originallatefee = new ArrayList<>();
        for (WebElement latefeeElement : ListOflatefee_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            originallatefee.add(latefeeElement.getText());
        }

        List<String> sortedlatefee = new ArrayList<>(originallatefee);
        Collections.sort(sortedlatefee, Collections.reverseOrder());

        if (originallatefee.equals(sortedlatefee)) {
            return true;
        } else {
            System.out.println("Original Late fee Amts: " + originallatefee);
            System.out.println("Sorted Late fee Amts: " + sortedlatefee);
            System.out.println("Tax are not sorted in descending order.");
            return false;
        }
    }

    /**
     * Clicks the total label on the unpaid invoice tab on the customer details popup in the phone order page.
     *
     * @Author Balaji N
     */
    public void Click_Total_Label_OnUnpaidTab_OnCustomerDetailsPopup() {
        Click(TotalLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, "Total Label on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the total amounts in the unpaid invoice table are sorted in ascending order.
     *
     * @return If the total amounts are sorted in ascending order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_TotalHeader_Sorted_ascending() {
        List<String> originallatefee = new ArrayList<>();
        for (WebElement latefeeElement : ListOfTotallabel_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            originallatefee.add(latefeeElement.getText());
        }

        // Create a copy of the list and sort it
        List<String> sortedInvoiceNumbers = new ArrayList<>(originallatefee);
        Collections.sort(sortedInvoiceNumbers);

        // Compare the original list with the sorted list
        if (originallatefee.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Original late fee Amts: " + originallatefee);
            System.out.println("Sorted late fee Amts: " + sortedInvoiceNumbers);
            System.out.println("Late fees are not sorted in ascending order.");
            return false;
        }
    }

    /**
     * Verifys whether the total amounts in the unpaid invoice table are sorted in descending order.
     *
     * @return If the total amounts are sorted in descending order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean verify_TotalHeader_Sorted_Descending() {
        List<String> originallatefee = new ArrayList<>();
        for (WebElement latefeeElement : ListOfTotallabel_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            originallatefee.add(latefeeElement.getText());
        }

        List<String> sortedlatefee = new ArrayList<>(originallatefee);
        Collections.sort(sortedlatefee, Collections.reverseOrder());

        if (originallatefee.containsAll(sortedlatefee)) {
            return true;
        } else {
            System.out.println("Original Total Column Amts: " + originallatefee);
            System.out.println("Sorted Total column Amts: " + sortedlatefee);
            System.out.println("Total column are not sorted in descending order.");
            return false;
        }
    }

    /**
     * Verifys whether the total amounts in the unpaid invoice table are sorted in default order.
     *
     * @return If the total amounts are sorted in default order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_TotalHeader_DefaultOrder() {
        List<String> originalTotalLabel = new ArrayList<>();
        for (WebElement totalLabelElement : ListOfTotallabel_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            originalTotalLabel.add(totalLabelElement.getText());
        }

        // Create a copy of the list and sort it
        List<String> expectedTotalLabel = new ArrayList<>(originalTotalLabel);


        // Compare the original list with the sorted list
        if (originalTotalLabel.containsAll(expectedTotalLabel)) {
            return true;
        } else {
            System.out.println("Original Total column default order: " + originalTotalLabel);
            System.out.println("expected Total column default order: " + expectedTotalLabel);
            System.out.println("Total column Default order are not sorted in default order.");
            return false;
        }
    }

    /**
     * Clicks the order date label on the unpaid invoice tab on the customer details popup in the phone order page.
     *
     * @Author Balaji N
     */
    public void Click_OrderDate_Label_OnUnpaidTab_OnCustomerDetailsPopup() {
        Click(OrderDateLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, "Order Date Label on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the order date amounts in the unpaid invoice table are sorted in ascending order.
     *
     * @return If the order date amounts are sorted in ascending order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_OrderDateHeader_Sorted_ascending() {
        List<String> originallatefee = new ArrayList<>();
        for (WebElement latefeeElement : ListOfOrderDate_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            originallatefee.add(latefeeElement.getText());
        }

        // Create a copy of the list and sort it
        List<String> sortedInvoiceNumbers = new ArrayList<>(originallatefee);
        Collections.sort(sortedInvoiceNumbers);

        // Compare the original list with the sorted list
        if (originallatefee.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Original Order date Amts: " + originallatefee);
            System.out.println("Sorted Order date Amts: " + sortedInvoiceNumbers);
            System.out.println("Order date are not sorted in ascending order.");
            return false;
        }
    }

    /**
     * Verifys whether the order date amounts in the unpaid invoice table are sorted in descending order.
     *
     * @return If the order date amounts are sorted in descending order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean verify_OrderDateHeader_Sorted_Descending() {
        List<String> originallatefee = new ArrayList<>();
        for (WebElement latefeeElement : ListOfOrderDate_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            originallatefee.add(latefeeElement.getText());
        }

        List<String> sortedlatefee = new ArrayList<>(originallatefee);
        Collections.sort(sortedlatefee, Collections.reverseOrder());

        if (originallatefee.equals(sortedlatefee)) {
            return true;
        } else {
            System.out.println("Original Order date Amts: " + originallatefee);
            System.out.println("Sorted Order date Amts: " + sortedlatefee);
            System.out.println("Tax are not sorted in descending order.");
            return false;
        }
    }

    /**
     * Clicks the delivery date label on the unpaid invoice tab on the customer details popup in the phone order page.
     *
     * @Author Balaji N
     */
    public void Click_DeliveryDate_Label_OnUnpaidTab_OnCustomerDetailsPopup() {
        js_Click(DeliveryDateLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, "Delivery Date Label on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifies whether the delivery date Column in the unpaid invoice table are sorted in ascending order.
     *
     * @return If the delivery date Column are sorted in ascending order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_DeliveryDateHeader_Sorted_ascending() {
        List<String> originallatefee = new ArrayList<>();
        for (WebElement latefeeElement : ListOfDeliveryDate_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            originallatefee.add(latefeeElement.getText());
        }

        // Create a copy of the list and sort it
        List<String> sortedInvoiceNumbers = new ArrayList<>(originallatefee);
        Collections.sort(sortedInvoiceNumbers);

        // Compare the original list with the sorted list
        if (originallatefee.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Original delivery date Amts: " + originallatefee);
            System.out.println("Sorted delivery date Amts: " + sortedInvoiceNumbers);
            System.out.println("Delivery date are not sorted in ascending order.");
            return false;
        }
    }

    /**
     * Verifys whether the delivery date column in the unpaid invoice table are sorted in descending order.
     *
     * @return If the delivery date column are sorted in descending order, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean verify_DeliveryDateHeader_Sorted_Descending() {
        List<String> originallatefee = new ArrayList<>();
        for (WebElement latefeeElement : ListOfDeliveryDate_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup) {
            originallatefee.add(latefeeElement.getText());
        }

        List<String> sortedlatefee = new ArrayList<>(originallatefee);
        Collections.sort(sortedlatefee, Collections.reverseOrder());

        if (originallatefee.equals(sortedlatefee)) {
            return true;
        } else {
            System.out.println("Original Delivery date Amts: " + originallatefee);
            System.out.println("Sorted Delivery date Amts: " + sortedlatefee);
            System.out.println("Delivery date are not sorted in descending order.");
            return false;
        }
    }

    /**
     * Verifys whether the pagination on the unpaid invoice tab on the customer details popup in the phone order page is displayed.
     *
     * @return If the pagination is displayed, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_Pagination_OnUnpaidInvoiceTab_OnCustomerDetailsPopup() {
        wait_For_Page_To_Be_Stable(getDriver());
        return is_Element_Displayed(pagination_UnpaidTabOn_CustomerDetailsPopup_OnPhoneOrderPage, "Pagination on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifys whether the first page pagination icon on the unpaid invoice tab on the customer details popup in the phone order page is displayed.
     *
     * @return If the first page pagination icon is displayed, returns true; otherwise, returns false.
     * @Author Balaji N
     */
/*    public boolean Verify_FirstPagePaginationIcon_IsAppears() {
        return is_Element_Displayed(firstpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage, "First Page Pagination Icon on Unpaid Invoice Tab on customer details popup in the phone order page");
    }*/

    /**
     * Verifies whether the First Page Pagination icon appears on the Unpaid Invoice Tab.
     *
     * @return true if the icon is visible; false otherwise.
     * @Author Balaji N
     */
    public boolean Verify_FirstPagePaginationIcon_IsAppears() {
        int maxRetries = 3;
        By locator = By.xpath("//div[@id='gvCustomerUnpaidInvoices']//a[@title='Go to the first page']//span"); // Replace with your actual locator if needed

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                // Wait for page load and jQuery readiness
                wait.until(driver -> ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete"));
                wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return !!window.jQuery && jQuery.active === 0"));

                WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                wait.until(ExpectedConditions.elementToBeClickable(icon));
                wait.until(ExpectedConditions.visibilityOf(icon));

                return isElementDisplayed(icon, "First Page Pagination Icon on Unpaid Invoice Tab on customer details popup in the phone order page");

            } catch (TimeoutException | StaleElementReferenceException | ElementNotInteractableException e) {
                System.err.println("[Retry " + attempt + "/" + maxRetries + "] Waiting for First Page Pagination icon: " + e.getClass().getSimpleName());
                delayWithGivenTime(1000);
            } catch (Exception e) {
                printError(firstpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage,
                        "Verify_FirstPagePaginationIcon_IsAppears",
                        "Unexpected error occurred while verifying pagination icon: " + e.getMessage(), e);
                break;
            }
        }

        return false;
    }

    public boolean Verify_FirstPagePaginationIcon_IsAppears_On_UnpaidInvoiceTab() {
        int maxRetries = 3;
        By locator = By.xpath("//div[@id='gvCustomerUnpaidInvoices']//a[@title='Go to the first page'][1]//span");

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                // Wait for page load and jQuery readiness
                wait.until(driver -> ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete"));
                wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return !!window.jQuery && jQuery.active === 0"));

                WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                return isElementDisplayed(icon, "First Page Pagination Icon on Unpaid Invoice Tab on customer details popup in the phone order page");

            } catch (TimeoutException | StaleElementReferenceException | ElementNotInteractableException e) {
                System.err.println("[Retry " + attempt + "/" + maxRetries + "] Waiting for First Page Pagination icon: " + e.getClass().getSimpleName());
                delayWithGivenTime(1000);
            } catch (Exception e) {
                printError(firstpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage,
                        "Verify_FirstPagePaginationIcon_IsAppears",
                        "Unexpected error occurred while verifying pagination icon: " + e.getMessage(), e);
                break;
            }
        }

        return false;
    }

    /**
     * Verifies whether the First Page Pagination icon appears on the Unpaid Invoice Tab.
     *
     * @return true if the icon is visible; false otherwise.
     * @Author Balaji N
     */
    public boolean verify_FirstPagePaginationIcon_IsAppears_On_Payment_Details_Tab() {
        int maxRetries = 3;
        By locator = By.xpath("//div[@id='gvCustomerPaymentDtls']//a[@title='Go to the first page']");

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                wait.until(driver -> ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete"));
                wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return !!window.jQuery && jQuery.active === 0"));

                WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                return is_Element_Displayed(icon, "First Page Pagination Icon on Unpaid Invoice Tab on customer details popup in the phone order page");

            } catch (TimeoutException | StaleElementReferenceException | ElementNotInteractableException e) {
                System.err.println("[Retry " + attempt + "/" + maxRetries + "] Waiting for First Page Pagination icon: " + e.getClass().getSimpleName());
                delayWithGivenTime(1000);
            } catch (Exception e) {
                printError(firstpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage,
                        "Verify_FirstPagePaginationIcon_IsAppears",
                        "Unexpected error occurred while verifying pagination icon: " + e.getMessage(), e);
                break;
            }
        }

        return false;
    }

    public boolean verify_FirstPagePaginationIcon_IsAppears_On_Order_Details_Tab() {
        int maxRetries = 3;
        By locator = By.xpath("//div[@id='gvCustomerOrderDtls']//a[@title='Go to the first page']");

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                wait.until(driver -> ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete"));
                wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return !!window.jQuery && jQuery.active === 0"));

                WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                return isElementDisplayed(icon, "First Page Pagination Icon on Unpaid Invoice Tab on customer details popup in the phone order page");

            } catch (TimeoutException | StaleElementReferenceException | ElementNotInteractableException e) {
                System.err.println("[Retry " + attempt + "/" + maxRetries + "] Waiting for First Page Pagination icon: " + e.getClass().getSimpleName());
                delayWithGivenTime(1000);
            } catch (Exception e) {
                printError(firstpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage,
                        "Verify_FirstPagePaginationIcon_IsAppears",
                        "Unexpected error occurred while verifying pagination icon: " + e.getMessage(), e);
                break;
            }
        }

        return false;
    }


    /**
     * Clicks the first page pagination icon on the unpaid invoice tab on the customer details popup in the phone order page.
     *
     * @Author Balaji N
     */
    /*public void Click_FirstPagePaginationIcon_OnUnpaidTab_OnCustomerDetailsPopup() {
        js_Click(firstpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage, "First Page Pagination Icon on Unpaid Invoice Tab on customer details popup in the phone order page");
    }
*/
    public void Click_FirstPagePaginationIcon_OnUnpaidTab_OnCustomerDetailsPopup() {
        int retries = 3;
        while (retries > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));

                wait.until(webDriver -> ((Boolean) ((JavascriptExecutor) webDriver)
                        .executeScript("return !!window.jQuery && jQuery.active == 0")));

                WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(first_page_pagination_unpaid_tab_on_customerdetails_popup));
                js_Click(icon, "First Page Pagination Icon on Unpaid Invoice Tab on customer details popup in the phone order page");
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Stale element. Retrying... Attempt " + (4 - retries));
                retries--;
            } catch (Exception e) {
                throw new RuntimeException("Click failed on First Page Pagination Icon: " + e.getMessage(), e);
            }
        }
    }

    public void Click_FirstPagePaginationIcon_OnOrderDetailsTab_OnCustomerDetailsPopup() {
        int retries = 3;
        while (retries > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));

                wait.until(webDriver -> ((Boolean) ((JavascriptExecutor) webDriver)
                        .executeScript("return !!window.jQuery && jQuery.active == 0")));

                WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(firstpageicon_on_orderdetailstab_customerdetailspopup));
                js_Click(icon, "First Page Pagination Icon on Order details Tab on customer details popup in the phone order page");
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Stale element. Retrying... Attempt " + (4 - retries));
                retries--;
            } catch (Exception e) {
                throw new RuntimeException("Click failed on First Page Pagination Icon on Order details Tab: ", e); //  + e.getMessage()
            }
        }
    }


    public void Click_FirstPagePagination_Icon_OnUnpaidTab_OnCustomerDetailsPopup() {
        int retries = 3;
        while (retries > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));

                wait.until(webDriver -> ((Boolean) ((JavascriptExecutor) webDriver)
                        .executeScript("return !!window.jQuery && jQuery.active == 0")));

                WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(firstpage_Pagination_UnpaidTabOn_CustomerDetailsPopup_OnPhoneOrderPage));
                js_Click(icon, "First Page Pagination Icon on Unpaid Invoice Tab on customer details popup in the phone order page");
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Stale element. Retrying... Attempt " + (4 - retries));
                retries--;
            } catch (Exception e) {
                throw new RuntimeException("Click failed on First Page Pagination Icon: " + e.getMessage(), e);
            }
        }
    }

    public void Click_FirstPagePagination_Icon_OnPaymentDetailsTab_OnCustomerDetailsPopup() {
        int retries = 3;
        while (retries > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));

                wait.until(webDriver -> ((Boolean) ((JavascriptExecutor) webDriver)
                        .executeScript("return !!window.jQuery && jQuery.active == 0")));

                WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(firstpageicon_paymentdetailstab_customerdetailspopup));
                js_Click(icon, "First Page Pagination Icon on payment details Tab on customer details popup in the phone order page");
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Stale element. Retrying... Attempt " + (4 - retries));
                retries--;
            } catch (Exception e) {
                throw new RuntimeException("Click failed on First Page Pagination Icon: " + e.getMessage(), e);
            }
        }
    }

    /**
     * Verifies whether the first page pagination icon is enabled on the Unpaid tab
     * in the Customer Details popup on the Phone Order page.
     *
     * @return {@code true} if the pagination icon is enabled, {@code false} otherwise.
     * @throws NoSuchElementException         if the pagination element is not found on the page.
     * @throws StaleElementReferenceException if the pagination element is no longer attached to the DOM.
     * @throws WebDriverException             if there is an issue interacting with the web element.
     * @Author Balaji N
     */
    public boolean Verify_FirstPagePaginationIconIsEnabled_OnUnpaidTab_OnCustomerDetailsPopup() {
        try {
            HighlightElement(firstpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage);
            String disabledAttribute = firstpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage.getAttribute("disabled");
            return disabledAttribute == null || !disabledAttribute.equals("true");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Pagination icon not found on the Unpaid tab in Customer Details popup.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Pagination icon is no longer attached to the DOM.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("Unexpected WebDriver exception while verifying pagination icon state.", e);
        }
    }

    public boolean Verify_FirstPagePaginationIconIsEnabled_OnOrderDetailsTab_OnCustomerDetailsPopup() {
        try {
            HighlightElement(firstpageicon_on_orderdetailstab_customerdetailspopup);
            String disabledAttribute = firstpageicon_on_orderdetailstab_customerdetailspopup.getAttribute("disabled");
            return disabledAttribute == null || !disabledAttribute.equals("true");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Pagination icon not found on the Order details tab in Customer Details popup.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Pagination icon is no longer attached to the DOM.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("Unexpected WebDriver exception while verifying pagination icon state.", e);
        }
    }

    public boolean Verify_FirstPagePaginationIconIsEnabled_On_PaymentDetailsTab_OnCustomerDetailsPopup() {
        try {
            HighlightElement(firstpageicon_paymentdetailstab_customerdetailspopup);
            String disabledAttribute = firstpageicon_paymentdetailstab_customerdetailspopup.getAttribute("disabled");
            return disabledAttribute == null || !disabledAttribute.equals("true");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Pagination icon not found on the Unpaid tab in Customer Details popup.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Pagination icon is no longer attached to the DOM.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("Unexpected WebDriver exception while verifying pagination icon state.", e);
        }
    }


    /**
     * Verifys whether the previous page pagination icon on the unpaid invoice tab on the customer details popup in the phone order page is displayed.
     *
     * @return If the previous page pagination icon is displayed, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_PreviousPagePaginationIcon_IsAppears() {
        return is_Element_Displayed(previouspage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage, "Previous Page Pagination Icon on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    public boolean Verify_PreviousPagePaginationIcon_IsAppears_On_PaymentDetailsTab() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        WebElement previouspageicon = wait.until(ExpectedConditions.presenceOfElementLocated(previouspage));
        return isElementDisplayed(previouspageicon, "Previous Page Pagination Icon onpayment details Tab on customer details popup in the phone order page");
    }

    public boolean Verify_PreviousPagePaginationIcon_IsAppears_On_OrderDetailsTab() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        WebElement previouspageicon = wait.until(ExpectedConditions.presenceOfElementLocated(previouspageorderdetails));
        return isElementDisplayed(previouspageicon, "Previous Page Pagination Icon onpayment details Tab on customer details popup in the phone order page");
    }


    /**
     * Clicks the previous page pagination icon on the unpaid invoice tab on the customer details popup in the phone order page.
     *
     * @Author Balaji N
     */
  /*  public void Click_PreviousPagePaginationIcon_OnUnpaidTab_OnCustomerDetailsPopup() {
        js_Click(previouspage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage, "Previous page pagination icon on Unpaid Invoice Tab on customer details popup in the phone order page");
    }*/
    public void Click_PreviousPagePaginationIcon_OnUnpaidTab_OnCustomerDetailsPopup() {
        int retries = 3;
        while (retries > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
                WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(previous_page_pagination_unpaid_tab_IconLocator));

                js_Click(icon, "Previous page pagination icon on Unpaid Invoice Tab on customer details popup in the phone order page");
                break;

            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException caught. Retrying... " + (4 - retries));
                retries--;
            } catch (Exception e) {
                throw new RuntimeException("Failed to click previous page pagination icon: " + e.getMessage(), e);
            }
        }
    }

    public void Click_PreviousPagePaginationIcon_On_PaymentDetailsTab_OnCustomerDetailsPopup() {
        int retries = 3;
        while (retries > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
                WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(previouspage));

                js_Click(icon, "Previous page pagination icon on Payment Details Tab on customer details popup in the phone order page");
                break;

            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException caught. Retrying... " + (4 - retries));
                retries--;
            } catch (Exception e) {
                throw new RuntimeException("Failed to click previous page pagination icon on payment details tab: " + e.getMessage(), e);
            }
        }
    }

    public void Click_PreviousPagePaginationIcon_On_OrderDetailsTab_OnCustomerDetailsPopup() {
        int retries = 3;
        while (retries > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
                WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(previouspageorderdetails));

                js_Click(icon, "Previous page pagination icon on Order Details Tab on customer details popup in the phone order page");
                break;

            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException caught. Retrying... " + (4 - retries));
                retries--;
            } catch (Exception e) {
                throw new RuntimeException("Failed to click previous page pagination icon on Order details tab: " + e.getMessage(), e);
            }
        }
    }


    /**
     * Verify the previous page pagination icon is enabled on the unpaid tab in the Customer Details popup on the Phone Order page.
     *
     * @return If the pagination icon is enabled, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_PreviousPagePaginationIcon_OnUnpaidTab_OnCustomerDetailsPopup() {
        try {
            HighlightElement(previouspage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage);
            String disabledAttribute = previouspage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage.getAttribute("disabled");
            return disabledAttribute == null || !disabledAttribute.equals("true");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Previous page Pagination icon not found on the Unpaid tab in Customer Details popup.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Previous page Pagination icon is no longer attached to the DOM.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("Unexpected WebDriver exception while verifying pagination icon state.", e);
        }
    }

    public boolean Verify_PreviousPagePaginationIcon_OnPaymentDetailsTab_OnCustomerDetailsPopup() {
        try {
            HighlightElement(getDriver().findElement(previouspage));
            String disabledAttribute = getDriver().findElement(previouspage).getAttribute("disabled");
            return disabledAttribute == null || !disabledAttribute.equals("true");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Previous page Pagination icon not found on the Unpaid tab in Customer Details popup.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Previous page Pagination icon is no longer attached to the DOM.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("Unexpected WebDriver exception while verifying pagination icon state.", e);
        }
    }

    public boolean Verify_PreviousPagePaginationIcon_OnOrderDetailsTab_OnCustomerDetailsPopup() {
        try {
            HighlightElement(getDriver().findElement(previouspageorderdetails));
            String disabledAttribute = getDriver().findElement(previouspageorderdetails).getAttribute("disabled");
            return disabledAttribute == null || !disabledAttribute.equals("true");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Previous page Pagination icon not found on the Unpaid tab in Customer Details popup.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Previous page Pagination icon is no longer attached to the DOM.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("Unexpected WebDriver exception while verifying pagination icon state.", e);
        }
    }

    /**
     * Verifys whether the next page pagination icon on the unpaid invoice tab on the customer details popup in the phone order page is displayed.
     *
     * @return If the next page pagination icon is displayed, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_NextPagePaginationIcon_IsAppears() {
        return is_Element_Displayed(nextpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage, "Next Page Pagination Icon on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    public boolean is_NextPageIcon_Displayed_On_PaymentDetailsTab() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        WebElement nextpageicon = wait.until(ExpectedConditions.presenceOfElementLocated(nextpage_paymentdetails));
        return is_Element_Displayed(nextpageicon, "Next Page Pagination Icon on payment details Tab on customer details popup in the phone order page");
    }

    public boolean is_NextPageIcon_Displayed_On_OrderDetailsTab() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        WebElement nextpageicon = wait.until(ExpectedConditions.presenceOfElementLocated(nextpage_orderdetails));
        return is_Element_Displayed(nextpageicon, "Next Page Pagination Icon on payment details Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the next page pagination icon on the unpaid invoice tab on the customer details popup in the phone order page.
     *
     * @Author Balaji N
     */
    public void Click_NextPagePaginationIcon_OnUnpaidTab_OnCustomerDetailsPopup() {
        js_Click(nextpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage, "Next Page Pagination Icon on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    public void Click_NextPagePaginationIcon_On_PaymentDetailsTab_OnCustomerDetailsPopup() {
        js_Click(getDriver().findElement(nextpage_paymentdetails), "Next Page Pagination Icon on Payment details Tab on customer details popup in the phone order page");
    }

    public void Click_NextPagePaginationIcon_On_OrderDetailsTab_OnCustomerDetailsPopup() {
        js_Click(getDriver().findElement(nextpage_orderdetails), "Next Page Pagination Icon on Order details Tab on customer details popup in the phone order page");
    }

    /**
     * Verify the next page pagination icon is enabled on the unpaid tab in the Customer Details popup on the Phone Order page.
     *
     * @return If the pagination icon is enabled, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_NextPagePaginationIconIsEnabled_OnUnpaidTab_OnCustomerDetailsPopup() {
        try {
            HighlightElement(nextpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage);
            String disabledAttribute = nextpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage.getAttribute("disabled");
            return disabledAttribute == null || !disabledAttribute.equals("true");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Next page Pagination icon not found on the Unpaid tab in Customer Details popup.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Next page Pagination icon is no longer attached to the DOM.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("Unexpected WebDriver exception while verifying next page pagination icon state.", e);
        }
    }

    public boolean Verify_NextPagePaginationIconIsEnabled_OnPaymentDetailsTab_OnCustomerDetailsPopup() {
        try {
            HighlightElement(getDriver().findElement(nextpage_paymentdetails));
            String disabledAttribute = getDriver().findElement(nextpage_paymentdetails).getAttribute("disabled");
            return disabledAttribute == null || !disabledAttribute.equals("true");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Next page Pagination icon not found on the Unpaid tab in Customer Details popup.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Next page Pagination icon is no longer attached to the DOM.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("Unexpected WebDriver exception while verifying next page pagination icon state.", e);
        }
    }

    public boolean Verify_NextPagePaginationIconIsEnabled_On_OrderDetailsTab_OnCustomerDetailsPopup() {
        try {
            HighlightElement(getDriver().findElement(nextpage_orderdetails));
            String disabledAttribute = getDriver().findElement(nextpage_orderdetails).getAttribute("disabled");
            return disabledAttribute == null || !disabledAttribute.equals("true");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Next page Pagination icon not found on the Order details tab in Customer Details popup.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Next page Pagination icon is no longer attached to the DOM.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("Unexpected WebDriver exception while verifying next page pagination icon state.", e);
        }
    }

    /**
     * Verifys whether the last page pagination icon on the unpaid invoice tab on the customer details popup in the phone order page is displayed.
     *
     * @return If the last page pagination icon is displayed, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_LastPagePaginationIcon_IsAppears() {
        int retries = 3;
        while (retries > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
                WebElement icon = wait.until(ExpectedConditions.presenceOfElementLocated(lastpage_pagination_unpaid_tab_customer_details_popup));
                return icon.isDisplayed();
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException caught. Retrying... Attempt " + (4 - retries));
                retries--;
            } catch (Exception e) {
                System.out.println("Unexpected exception: " + e.getMessage());
                break;
            }
        }
        return false;
    }

    //  lastpageicon_paymentdetailstab_customerdetailspopup

    public boolean Verify_LastPagePaginationIcon_IsAppears_On_PaymentDetailsTab() {
        int retries = 3;
        while (retries > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
                WebElement icon = wait.until(ExpectedConditions.presenceOfElementLocated(lastpageicon_paymentdetailstab_customerdetailspopup));
                return icon.isDisplayed();
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException caught. Retrying... Attempt " + (4 - retries));
                retries--;
            } catch (Exception e) {
                System.out.println("Unexpected exception: " + e.getMessage());
                break;
            }
        }
        return false;
    }

//    public boolean Verify_LastPage_PaginationIcon_IsAppears_On_OrderDetailsTab() {
//        int retries = 3;
//        while (retries > 0) {
//            try {
//                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
//                WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(lastpage_pagination_icon_orderdetailsTabOn_CustomerDetailsPopupOnPhoneOrderPage));
//                return icon.isDisplayed();
//            } catch (StaleElementReferenceException e) {
//                System.out.println("StaleElementReferenceException caught. Retrying... Attempt " + (4 - retries));
//                retries--;
//            } catch (Exception e) {
//                System.out.println("Unexpected exception: " + e.getMessage());
//                break;
//            }
//        }
//        return false;
//    }

    public boolean Verify_LastPage_PaginationIcon_IsAppears_On_OrderDetailsTab() {
        int retries = 3;

        while (retries > 0) {
            try {
                wait_For_Page_To_Be_Stable(getDriver());

                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
                WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        lastpage_pagination_icon_orderdetailsTabOn_CustomerDetailsPopupOnPhoneOrderPage
                ));

                if (icon.isDisplayed()) {
                    String msg = "✅ 'Last Page' pagination icon is visible on Order Details Tab.";
                    System.out.println(msg);
                    Allure.step(msg);
                    return true;
                } else {
                    String msg = "❌ 'Last Page' pagination icon is located but not visible.";
                    System.err.println(msg);
                    Allure.step(msg);
                    return false;
                }

            } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException e) {
                String msg = String.format("⚠️ Attempt %d: Retry due to [%s]", (4 - retries), e.getClass().getSimpleName());
                System.err.println(msg);
                Allure.step(msg);
                retries--;
                delayWithGivenTime(1000); // short delay before retry
            } catch (Exception e) {
                String msg = "❌ Unexpected error while checking 'Last Page' icon: " + e.getMessage();
                System.err.println(msg);
                Allure.step(msg);
                break;
            }
        }

        String failMsg = "❌ 'Last Page' pagination icon was not found after 3 attempts.";
        System.err.println(failMsg);
        Allure.step(failMsg);
        return false;
    }


    public void waitForDomReady(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .toString().equals("complete")
        );
    }


    /**
     * Clicks the last page pagination icon on the unpaid invoice tab on the customer details popup in the phone order page.
     *
     * @Author Balaji N
     */
    public void Click_LastPagePaginationIcon_OnOrderDetailsTab_OnCustomerDetailsPopup() {
        wait_For_Page_To_Be_Stable(getDriver());
        int retries = 3;
        boolean success = false;

        while (retries > 0) {
            try {
                waitForDomReady(getDriver());

                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(lastpage_pagination_icon_orderdetailsTabOn_CustomerDetailsPopupOnPhoneOrderPage));

                HighlightElement(element);
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);

                String msg = "✅ Clicked on 'Last Page' icon under Order Details tab (Customer Details Popup)";
                System.out.println(msg);
                Allure.step(msg);

                wait_For_Page_To_Be_Stable(getDriver());
                success = true;
                break;

            } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException e) {
                String err = String.format("⚠️ Retry %d: Failed to click 'Last Page' icon due to [%s]",
                        (4 - retries), e.getClass().getSimpleName());
                System.err.println(err);
                Allure.step(err);
                retries--;
                delayWithGivenTime(1000);
            } catch (Exception e) {
                String fatal = "❌ Unexpected error while clicking 'Last Page' icon: " + e.getMessage();
                System.err.println(fatal);
                Allure.step(fatal);
                throw new RuntimeException(fatal, e);
            }
        }

        if (!success) {
            String failMsg = "❌ Could not click 'Last Page' icon on Order Details Tab after 3 attempts.";
            System.err.println(failMsg);
            Allure.step(failMsg);
            throw new RuntimeException(failMsg);
        }
    }


    public void Click_LastPagePaginationIcon_OnUnpaidTab_OnCustomerDetailsPopup() {
        wait_For_Page_To_Be_Stable(getDriver());
        int retries = 3;
        while (retries > 0) {
            try {
                waitForDomReady(getDriver());

                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(lastpage_pagination_unpaid_tab_customer_details_popup));

                click(element, "Last Page Pagination Icon on Unpaid Invoice Tab on customer details popup in the phone order page");

                wait_For_Page_To_Be_Stable(getDriver());
                break;
            } catch (StaleElementReferenceException | TimeoutException e) {
                System.out.println("Retrying due to: " + e.getClass().getSimpleName() + " - Attempt " + (4 - retries));
                retries--;
                delayWithGivenTime(1000); // small wait before retry
            } catch (Exception e) {
                throw new RuntimeException("Click failed on Last Page Pagination Icon: " + e.getMessage(), e);
            }
        }
    }

    public void Click_LastPagePaginationIcon_OnPaymentDetailsTab_OnCustomerDetailsPopup() {
        wait_For_Page_To_Be_Stable(getDriver());
        int retries = 3;
        while (retries > 0) {
            try {
                waitForDomReady(getDriver());

                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(lastpageicon_paymentdetailstab_customerdetailspopup));

                click(element, "Last Page Pagination Icon on payment details Tab on customer details popup in the phone order page");

                wait_For_Page_To_Be_Stable(getDriver());
                break;
            } catch (StaleElementReferenceException | TimeoutException e) {
                System.out.println("Retrying due to: " + e.getClass().getSimpleName() + " - Attempt " + (4 - retries));
                retries--;
                delayWithGivenTime(1000); // small wait before retry
            } catch (Exception e) {
                throw new RuntimeException("Click failed on Last Page Pagination Icon on payment details Tab: " + e.getMessage(), e);
            }
        }
    }

    /**
     * Verifys whether the last page pagination icon is enabled on the unpaid tab in the Customer Details popup on the Phone Order page.
     *
     * @return If the pagination icon is enabled, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_LastPagePaginationIconIsEnabled_OnUnpaidTab_OnCustomerDetailsPopup() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));

            wait.until(webDriver -> ((Boolean) ((JavascriptExecutor) webDriver)
                    .executeScript("return !!window.jQuery && jQuery.active == 0")));

            String disabledAttribute = lastpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage.getAttribute("disabled");
            return disabledAttribute == null || !disabledAttribute.equals("true");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Last page Pagination icon not found on the Unpaid tab in Customer Details popup.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Last page Pagination icon is no longer attached to the DOM.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("Unexpected WebDriver exception while verifying last page pagination icon state.", e);
        }
    }

    public boolean Verify_LastPagePaginationIconIsEnabled_OnPaymentDetailsTab_OnCustomerDetailsPopup() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));

            wait.until(webDriver -> ((Boolean) ((JavascriptExecutor) webDriver)
                    .executeScript("return !!window.jQuery && jQuery.active == 0")));

            String disabledAttribute = getDriver().findElement(lastpageicon_paymentdetailstab_customerdetailspopup).getAttribute("disabled");
            return disabledAttribute == null || !disabledAttribute.equals("true");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Last page Pagination icon not found on the Unpaid tab in Customer Details popup.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Last page Pagination icon is no longer attached to the DOM.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("Unexpected WebDriver exception while verifying last page pagination icon state.", e);
        }
    }

    public boolean Verify_LastPagePaginationIconIsEnabled_OnOrderDetailsTab_OnCustomerDetailsPopup() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));

            wait.until(webDriver -> ((Boolean) ((JavascriptExecutor) webDriver)
                    .executeScript("return !!window.jQuery && jQuery.active == 0")));

            String disabledAttribute = getDriver().findElement(lastpage_pagination_icon_orderdetailsTabOn_CustomerDetailsPopupOnPhoneOrderPage).getAttribute("disabled");
            return disabledAttribute == null || !disabledAttribute.equals("true");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Last page Pagination icon not found on the Order Details tab in Customer Details popup.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Last page Pagination icon is no longer attached to the DOM.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("Unexpected WebDriver exception while verifying last page pagination icon state.", e);
        }
    }

    /**
     * Verifys whether the selected page number on the unpaid invoice tab on the customer details popup in the phone order page is displayed.
     *
     * @return If the selected page number is displayed, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_CurrentPageNumber_IsAppears() {
        return is_Element_Displayed(selectedpage_Pagination_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage, "Selected Page Number on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifys whether the page list number on the unpaid invoice tab on the customer details popup in the phone order page is displayed.
     *
     * @return If the page list number is displayed, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_PageListNumber_IsAppears() {
        return is_Element_Displayed(pagelist_DisplayedNumber_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage, "Page List Number on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Returns the page list number on the unpaid invoice tab on the customer details popup in the phone order page.
     *
     * @return If the page list number is displayed, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public String getpagelistdropdown_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage() {
        return getElementText(pagelist_DisplayedNumber_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage, "Page List Number on Unpaid Invoice Tab on customer details popup in the phone order page").trim();
    }

    public void click_PageList_Dropdown_On_UnpaidTab_On_CustomerDetails_Popup() {
        js_Click(pagelistdropdown_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage, "Page list dropdown on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    public boolean Verify_pagelist_DisplayedNumber_OnUnpaidTab_OnCustomerDetailsPopup() {
        click_PageList_Dropdown_On_UnpaidTab_On_CustomerDetails_Popup();
        delayWithGivenTime(1000);

        List<String> expectedPaginationValues = Arrays.asList("25", "50", "100", "150", "200", "250");
        Set<String> actualPaginationValues = new HashSet<>();

        for (WebElement dropdownValue : pagelist_dropdownvalues_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage) {
            HighlightElement(dropdownValue);
            actualPaginationValues.add(dropdownValue.getText());
        }

        // Check if all expected pagination values are present in the actual dropdown
        // values
        boolean allOptionsDisplayed = actualPaginationValues.containsAll(expectedPaginationValues);

        if (!allOptionsDisplayed) {
            System.out.println("Missing pagination values in dropdown: " + expectedPaginationValues.stream().filter(value -> !actualPaginationValues.contains(value)).collect(Collectors.joining(", ")));
        }
        click_PageList_Dropdown_On_UnpaidTab_On_CustomerDetails_Popup();
        return allOptionsDisplayed;
    }

    /**
     * Verifys whether the items per page label on the unpaid invoice tab on the customer details popup in the phone order page is displayed.
     *
     * @return If the items per page label is displayed, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_ItemsperPageLabelIsAppears_OnUnpaidTab_OnCustomerDetailsPopup() {
        return is_Element_Displayed(itemsperpage_Label_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage, "Items per page label on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifys whether the refresh icon on the unpaid invoice tab on the customer details popup in the phone order page is displayed.
     *
     * @return If the refresh icon is displayed, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_RefreshIcon_IsAppears_OnUnpaidTab_OnCustomerDetailsPopup() {
        return is_Element_Displayed(refreshButton_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage, "Refresh Icon on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verifys whether the total page number on the unpaid invoice tab on the customer details popup in the phone order page is displayed.
     *
     * @return If the total page number is displayed, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_Totalpagenumber_IsAppears_OnUnpaidTab_OnCustomerDetailsPopup() {
        return is_Element_Displayed(pagenumber_Label_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage, "Total Page Number on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Retrieves the first number of the page from the pagination label
     * on the Unpaid tab in the Customer Details popup on the Phone Order page.
     *
     * @return The first number from the pagination label as a {@code String}.
     * @throws NoSuchElementException         if the pagination label element is not found on the page.
     * @throws StaleElementReferenceException if the pagination label element is no longer attached to the DOM.
     * @throws WebDriverException             if there is an issue interacting with the web element.
     * @throws IndexOutOfBoundsException      if the retrieved text does not contain expected page number format.
     * @throws NullPointerException           if the retrieved text is null.
     * @Author Balaji N
     */
    public String get_first_NumberOfPage() {
        try {
            String pageText = pagenumber_Label_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage.getText();

            if (pageText == null || pageText.isEmpty()) {
                throw new NullPointerException("Pagination label text is null or empty.");
            }

            String[] parts = pageText.split(" ");
            if (parts.length == 0) {
                throw new IndexOutOfBoundsException("Pagination label text does not contain expected format.");
            }
            return parts[0];
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Pagination label not found on the Unpaid tab in Customer Details popup.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Pagination label is no longer attached to the DOM.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("Unexpected WebDriver exception while retrieving the first page number.", e);
        }
    }

    /**
     * Retrieves the second number of the page from the pagination label on the Unpaid tab in the Customer Details popup on the Phone Order page.
     *
     * @return The second number from the pagination label as a {@code String}.
     * @Author Balaji N
     */
    public String get_Second_NumberOfPage() {
        String[] parts = pagenumber_Label_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage.getText().split(" ");
        String secondNumber = parts[2];
        return secondNumber;
    }

    /**
     * Retrieves the total page number from the pagination label on the Unpaid tab in the Customer Details popup on the Phone Order page.
     *
     * @return If the total page number is displayed, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public String get_TotalPageNumber_Appears_OnUnpaidTab() {
        HighlightElement(pagenumber_Label_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage);
        String paginationText = pagenumber_Label_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage.getText();

        // Use regular expressions to extract the total number of items
        Pattern pattern = Pattern.compile("of\\s(\\d+)\\sitems");
        Matcher matcher = pattern.matcher(paginationText);

        if (matcher.find()) {
            String totalItems = matcher.group(1);
            System.out.println("Total number of items: " + totalItems);
        } else {
            System.out.println("Total number of items not found.");
        }

        return matcher.group(1);
    }

    /**
     * Retrieves the first page number from the pagination label on the Unpaid tab in the Customer Details popup on the Phone Order page.
     *
     * @return If the first page number is displayed, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public String get_FirstPageNumber_Appears_OnUnpaidTab() {
        HighlightElement(pagenumber_Label_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage);
        String paginationText = pagenumber_Label_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage.getText();

        // Use regular expressions to extract the first number of items
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(paginationText);

        if (matcher.find()) {
            String firstPageNumber = matcher.group(0);
            System.out.println("First page number: " + firstPageNumber);
            return firstPageNumber;
        } else {
            System.out.println("First page number not found.");
            return null;
        }
    }

    /**
     * Retrieves the total page number from the pagination label on the Unpaid tab in the Customer Details popup on the Phone Order page.
     *
     * @return If the total page number is displayed, returns true; otherwise, returns false.
     * @Author Balaji N
     */
   /* public String get_TotalPagenumber_Appears_OnUnpaidTab_OnCustomerDetailsPopup() {
        try {
            HighlightElement(pagenumber_Label_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage);
            String paginationText = pagenumber_Label_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage.getText();

            Pattern pattern = Pattern.compile("(\\d+\\s-\\s\\d+)");
            Matcher matcher = pattern.matcher(paginationText);

            if (matcher.find()) {
                String itemRange = matcher.group(1);
            } else {
                System.out.println("Item range not found.");
            }
            return matcher.group(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
*/
    public String get_TotalPagenumber_Appears_OnUnpaidTab_OnCustomerDetailsPopup() {
        int retries = 3;
        while (retries > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                // Wait for JS load
                wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));

                // Optional: Wait for jQuery AJAX
                wait.until(webDriver -> ((Boolean) ((JavascriptExecutor) webDriver)
                        .executeScript("return !!window.jQuery && jQuery.active === 0")));
                wait_For_Page_To_Be_Stable(getDriver());
                // Wait for the pagination element
                wait.until(ExpectedConditions.visibilityOfElementLocated(pagenumber_label_customer_details_popup));
                WebElement pageNumberLabel = getDriver().findElement(pagenumber_label_customer_details_popup);

                HighlightElement(pageNumberLabel);
                String paginationText = pageNumberLabel.getText();
                System.out.println("Pagination Text: " + paginationText);

                // Extract range like "1 - 10"
                Pattern pattern = Pattern.compile("(\\d+\\s*-\\s*\\d+)");
                Matcher matcher = pattern.matcher(paginationText);

                if (matcher.find()) {
                    return matcher.group(1).trim();
                } else {
                    System.out.println("⚠️ No pagination range found in: " + paginationText);
                    return "";
                }

            } catch (StaleElementReferenceException staleEx) {
                retries--;
                System.out.println("⚠️ Stale element exception. Retrying... Attempts left: " + retries);
                delayWithGivenTime(1000);
            } catch (TimeoutException timeoutEx) {
                retries--;
                System.out.println("⚠️ Timeout waiting for element. Retrying... Attempts left: " + retries);
                delayWithGivenTime(1000);
            } catch (Exception e) {
                retries--;
                System.out.println("⚠️ Unexpected error occurred: " + e.getMessage());
                delayWithGivenTime(1000);
            }
        }

        System.out.println("❌ Failed to fetch total page number on unpaid tab after retries.");
        return "";
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    /**
     * Selects a salesperson from the dropdown field on the phone order entry page.
     * <p>
     *
     * @param salesperson The name of the salesperson to be selected from the dropdown field
     * @Description: This function interacts with a dropdown element and selects a salesperson based on the
     * visible text. It uses the provided salesperson name to locate the option in the dropdown field.
     * </p>
     * @Author: Balaji N
     */
    public void Select_SalesPersonOn_PhoneOrderEntryPage(String salesperson) {
        // wait_For_Page_To_Be_Stable(getDriver());
        drop_Down(salesPersonDDOnPhoneOrderPage, salesperson, "VisibleText", "Select User or Salesperson Dropdown field on Phone Order Entry Page");
    }

    /**
     * Retrieves the selected salesperson from the dropdown field on the phone order entry page.
     *
     * @return If the selected salesperson is displayed, returns value of selected salesperson; otherwise, returns null.
     * @Author Balaji N
     */
    public String get_SelectedSalesPersonOn_PhoneOrderEntryPage() {
        return get_Selected_Option(salesPersonDDOnPhoneOrderPage, "Sales Person dropdown field on phone order entry page");
    }
//order_details_tab_pagelistdropdown_customer_details_popup

    /**
     * Selects a number from the dropdown in the pagination section on the Unpaid tab in the Customer Details popup on the Phone Order page.
     *
     * @Author Balaji N
     */
    public void SelectNumberDropdown_inPaginationSection_OnUnpaidTab_OnCustomerDetailsPopup() {
        try {
            js_Click(pagelistdropdown_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage, "Page number dropdown in the Unpaid tab in the Customer Details popup on the Phone Order page");
            delayWithGivenTime(1000);
            // Direct xpath to locate 50 option
            WebElement option = getDriver().findElement(By.xpath("//div[@class='k-animation-container']//ul[@class='k-list k-reset']//li[text()='50']"));
            js_Click(option, "Clicks the desired value '50' in the dropdown");

        } catch (NoSuchElementException e) {
            System.err.println("Error: Dropdown or its options not found. Please check the locator or ensure the element is present on the page.");
        } catch (ElementNotInteractableException e) {
            System.err.println("Error: Dropdown or its options are not interactable. Please verify their visibility and state.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public void SelectNumberDropdown_inPaginationSection_On_OrderDetailsTab_OnCustomerDetailsPopup() {
        try {
            js_Click(pagelist_orderdetailsTabOn_CustomerDetailsPopupOnPhoneOrderPage, "Page number dropdown in the Unpaid tab in the Customer Details popup on the Phone Order page");
            delayWithGivenTime(2000);
            // WebElement option = pagelist_dropdownvalues_orderdetailsTabOn_CustomerDetailsPopupOnPhoneOrderPage;
            click(pagelist_dropdownvalues_orderdetailsTabOn_CustomerDetailsPopupOnPhoneOrderPage, "Clicks the desired value '50' in the dropdown");

        } catch (NoSuchElementException e) {
            System.err.println("Error: Dropdown or its options not found. Please check the locator or ensure the element is present on the page.");
        } catch (ElementNotInteractableException e) {
            System.err.println("Error: Dropdown or its options are not interactable. Please verify their visibility and state.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }


    /**
     * Verifies the number of items displayed on the Unpaid tab in the Customer Details popup on the Phone Order page.
     *
     * @return true if the number of items displayed matches the expected count, false otherwise
     * @Author Balaji N
     */
    public boolean Verify_NoOfItemDisplayed_OnUnpaidTab_OnCustomerDetailsPopup() {
        try {
            // Locate all rows in the table
            List<WebElement> tableRows = getDriver().findElements(By.xpath("//tbody[@role='rowgroup']//tr"));

            // Check if rows are found
            if (tableRows.isEmpty()) {
                System.err.println("Error: No rows found in the table. Please check if the table is populated or verify the locator.");
                return false;
            }

            // Count the rows and validate
            int actualRowsCount = tableRows.size();
            if (actualRowsCount == 50) {
                System.out.println("Verification passed. Actual rows count: " + actualRowsCount);
                return true;
            } else {
                System.err.println("Verification failed. Expected rows count: 50, but found: " + actualRowsCount);
                return false;
            }
        } catch (NoSuchElementException e) {
            System.err.println("Error: Table rows not found. Please check the locator or ensure the table is visible on the page.");
            return false;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            return false;
        }
    }

    /**
     * Reorders the Invoice and Paid Amount headers in the Unpaid tab in the Customer Details popup on the Phone Order page.
     *
     * @return If the reordering is successful, returns the text of the Invoice header; otherwise, returns an empty string.
     * @Author Balaji N
     */
    public String Validate_InvoiceHeader_ReorderFunctionality() {
        dragAndDrop(InvoiceLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup, PaidAmtLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup);
        delayWithGivenTime(2000);
        return InvoiceLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup.getText();
    }

    /**
     * Reorders the Invoice and Paid Amount headers in the Unpaid tab in the Customer Details popup
     * on the Phone Order page by performing a drag-and-drop action.
     *
     * @throws NoSuchElementException         if either the Paid Amount label or the Invoice label is not found on the page.
     * @throws StaleElementReferenceException if either element is no longer attached to the DOM.
     * @throws WebDriverException             if there is an issue interacting with the web elements.
     * @throws InterruptedException           if the thread sleep is interrupted while waiting after the drag-and-drop action.
     * @Author Balaji N
     */
    public void Reorder_InvoiceHeader() {
        try {
            dragAndDrop(PaidAmtLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup, InvoiceLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup);
            delayWithGivenTime(1000);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("One or both elements (Paid Amount label, Invoice label) not found on the Unpaid tab in Customer Details popup.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("One or both elements became stale before the drag-and-drop action.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("Unexpected WebDriver exception while performing drag-and-drop action.", e);
        }
    }

    /**
     * Reorders the Invoice and Paid Amount headers in the Unpaid tab in the Customer Details popup
     *
     * @return If the reordering is successful, returns the text of the Paid Amount header; otherwise, returns an empty string.
     * @Author Balaji N
     */
    public String Validate_PaidAmt_ReOrderFunctionality() {
        dragAndDrop(PaidAmtLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup, InvoiceLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup);
        delayWithGivenTime(1000);
        return get_Element_Text(PaidAmtLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup, "Paid Amount Label on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Reorders the Invoice and Paid Amount headers in the Unpaid tab in the Customer Details popup
     *
     * @Author Balaji N
     */
    public void Reorder_PaidAmtHeader() {
        try {
            dragAndDrop(InvoiceLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup, PaidAmtLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup);
            delayWithGivenTime(1000);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("One or both elements (Paid Amount label, Invoice label) not found on the Unpaid tab in Customer Details popup.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("One or both elements became stale before the drag-and-drop action.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("Unexpected WebDriver exception while performing drag-and-drop action.", e);
        }
    }

    /**
     * Reorders the Invoice and Paid Amount headers in the Unpaid tab in the Customer Details popup
     *
     * @return If the reordering is successful, returns the text of the Product Total header; otherwise, returns an empty string.
     * @Author Balaji N
     */
    public String Validate_BalanceAmt_ReOrderFunctionality() {
        dragAndDrop(BalanceAmtLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup, ProductTotalLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup);
        delayWithGivenTime(1000);
        return get_Element_Text(ProductTotalLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup, "Product Total Column on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Reorders the Product Total and Balance Amount label in the Unpaid tab in the Customer Details popup
     *
     * @Author Balaji N
     */
    public void Reorder_BalanceAmtHeader() {
        try {
            dragAndDrop(ProductTotalLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup, BalanceAmtLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup);
            delayWithGivenTime(1000);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("One or both elements (Product Total label, Balance Amount label) not found on the Unpaid tab in Customer Details popup.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("One or both elements became stale before the drag-and-drop action.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("Unexpected WebDriver exception while performing drag-and-drop action.", e);
        }
    }

    /**
     * Reorders the Product Total and Balance Amount label in the Unpaid tab in the Customer Details popup
     *
     * @return If the reordering is successful, returns the text of the Balance Amount header; otherwise, returns an empty string.
     * @Author Balaji N
     */
    public String Validate_ProductTotal_ReOrderFunctionality() {
        dragAndDrop(ProductTotalLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup, BalanceAmtLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup);
        delayWithGivenTime(1000);
        return get_Element_Text(BalanceAmtLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup, "Balance Amount Column on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    public void Reorder_ProductTotalHeader() {
        dragAndDrop(BalanceAmtLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup, ProductTotalLabel_UnpaidInvoiceTab_OnCustomerDetailsPopup);
        delayWithGivenTime(1000);
    }

    /**
     * Verify the delivery column reorder to tax column vice - versa
     *
     * @return If the delivery column reordered to tax column it will returns the tax column label else return delivery label
     * @Author Balaji N
     */
    public String Validate_Delivery_ReOrderFunctionality() {
        dragAndDrop(DeliveryLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, TaxLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup);
        delayWithGivenTime(1000);
        return get_Element_Text(TaxLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, "Tax column Label text on the unpaid invoices tab in the customer details popup");
    }

    /**
     * Reorder the tax column into delivery column on unpaid invoice tab in the customer details popup
     *
     * @Author Balaji N
     */
    public void Reorder_DeliveryHeader() {
        dragAndDrop(TaxLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, DeliveryLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup);
        delayWithGivenTime(1000);
    }

    /**
     * Verify whether the Tax column reorder to delivery column
     *
     * @return If the tax column is reorder to delivery column it returns the delivery column label
     * @Author Balaji N
     */
    public String Validate_Tax_ReOrderFunctionality() {
        dragAndDrop(TaxLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, DeliveryLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup);
        delayWithGivenTime(1000);
        return get_Element_Text(DeliveryLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, "Delivery Column label text on unpaid tab in the customer details popup");
    }

    /**
     * Reorder the delivery column into tax column on unpaid invoice tab in the customer details popup
     *
     * @Author Balaji N
     */
    public void Reorder_TaxHeader() {
        dragAndDrop(DeliveryLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, TaxLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup);
        delayWithGivenTime(1000);
    }

    /**
     * verifies the late fee reorder into Total label column text
     *
     * @return If the total label column text value is displayed it returns the text else it return null
     * @Author Balaji N
     */
    public String Validate_Latefee_ReOrderFunctionality() {
        dragAndDrop(LatefeeLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, TotalLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup);
        delayWithGivenTime(1000);
        return get_Element_Text(TotalLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, "Total column label text on unpaid invoice table tab in the customer details popup");
    }

    /**
     * Reorder Late fee column on unpaid invoices tab
     *
     * @Author Balaji N
     */
    public void Reorder_LatefeeHeader() {
        dragAndDrop(TotalLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, LatefeeLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup);
        delayWithGivenTime(1000);
    }

    /**
     * Retrieves the Late fee label text after reorder on unpaid invoices tab in the customer details popup
     *
     * @return If the the Late fee label text after reorder on unpaid invoices tab in the customer details popup
     * @Author Balaji N
     */
    public String Validate_Total_ReOrderFunctionality() {
        dragAndDrop(TotalLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, LatefeeLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup);
        delayWithGivenTime(1000);
        return get_Element_Text(LatefeeLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, "late fee column label text on unpaid invoice table tab in the customer details popup");
    }

    /**
     * Reorder latefee column into total column on unpaid invoice tab in the customer details popup
     *
     * @Author Balaji N
     */
    public void Reorder_TotalHeader() {
        dragAndDrop(LatefeeLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, TotalLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup);
        delayWithGivenTime(1000);
    }

    /**
     * verify the order date reorder into delivery date on unpaid invoices tab in the customer details popup
     *
     * @return If Order date reorder into delivery date it returns the delivery date text, else null
     * @Author Balaji N
     */
    public String Validate_OrderDate_ReOrderFunctionality() {
        dragAndDrop(OrderDateLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, DeliveryDateLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup);
        delayWithGivenTime(1000);
        return get_Element_Text(DeliveryDateLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, "Delivery label text on unpaid invoice tab in the customer details popup");
    }

    /**
     * Drag and Drop to reorder the delivery date column into order date column on unpaid invoices tab in the customer details popup
     *
     * @Author Balaji N
     */
    public void Reorder_OrderDateHeader() {
        dragAndDrop(DeliveryDateLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, OrderDateLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup);
        delayWithGivenTime(1000);
    }

    /**
     * verify the order date column is reorder into delivery date column on unpaid invoices tab in the customer details popup
     *
     * @return If the Order date column is displayed it return the order date column label text else it returns the delivery date label text
     * @Author Balaji N
     */
    public String Validate_DeliveryDate_ReOrderFunctionality() {
        dragAndDrop(DeliveryDateLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, OrderDateLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup);
        delayWithGivenTime(1000);
        return get_Element_Text(OrderDateLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, "Order date column label text on unpaid invoice tab in the customer details popup");
    }

    /**
     * Drag and Drop to reorder the order date column into delivery date column on unpaid invoices tab in the customer details popup
     *
     * @Author Balaji N
     */
    public void Reorder_DeliveryDateHeader() {
        dragAndDrop(OrderDateLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup, DeliveryDateLabelOn_UnpaidInvoiceTable_UnpaidTab_CustomerDetailsPopup);
        delayWithGivenTime(1000);
    }

    /**
     * Verify whether the export to excel button is displayed on the unpaid invoice tab on the customer details popup in the phone order page
     *
     * @return If the export to excel button is displayed, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_ExportToExcel_OnUnpaidInvoiceTab_CustomerDetailsPopup() {
        return isElementDisplayed(exportToExcel_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage, "Export to Excel button on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the export to excel button on the unpaid invoice tab on the customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_ExportToExcel_OnUnpaidInvoiceTab_CustomerDetailsPopup() {
        Click(exportToExcel_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage, "Export to Excel button on Unpaid Invoice Tab on customer details popup in the phone order page");
    }

    /**
     * Verify whether the specific file is downloaded or not
     *
     * @param pathfile The path of the file
     * @param filename The name of the file
     * @return If the specified file name is downloaded, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean VerifyFileIsDownloaded(String pathfile, String filename) {
        File filelocation = new File(pathfile);
        File[] totalfiles = filelocation.listFiles();

        for (File file : totalfiles) {
            if (file.getName().contains(filename)) {
                System.out.println("File is download successfully");
                break;
            }
        }
        return true;
    }

    public boolean Verify_Customer_Statement_Is_Generated() {
        try {
            if (frame_webelement.isDisplayed() == true) {
                // Locate the iframe using XPath
                WebElement iframeElement = new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//iframe[@id='ifrmAccountStatementReport'])[1]")));

                // Switch to iframe
                switchToFrame(iframeElement);

                // Wait for the statement to be displayed
                // Locate the element inside the iframe (example: the statement)
                WebElement generatedStatement = new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='generatedStatement']")));

                // Check if the statement is displayed inside the iframe
                if (isStatementVisibleInsideIframe()) {
                    return true; // Statement is generated and visible
                } else {
                    System.out.println("Customer statement is not displayed.");
                    return false;
                }
            }


        } catch (Exception e) {
            System.err.println("Error while verifying customer statement: " + e.getMessage());
            return false;
        } finally {
            // Ensure switching back to the default content
            getDriver().switchTo().defaultContent();
        }
        return false;
    }

    // Check if the statement (iframe) is properly loaded and visible
    public boolean isStatementVisibleInsideIframe() {
        try {
            WebElement iframeContent = getDriver().findElement(By.tagName("iframe"));
            return iframeContent.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clicks the payment details tab on the customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void ClickOn_PaymentDetails_CustomerDetailsPopup() {
        js_Click(PaymentDetailsTab, "Payment details tab on customer details popup");
    }

    /**
     * Verify whether the export to excel button is displayed on the payment details tab on the customer details popup in the phone order page
     *
     * @return If the export to excel button is displayed, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_ExportToExcel_OnPaymentDetailsTab() {
        return is_Element_Displayed(exportExcel_PaymentDetailsTab, "Export excel button on payment details tab on customer details popup");
    }

    /**
     * Clicks the export to excel button on the payment details tab on the customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_ExportToExcel_OnPaymentDetailsTab() {
        js_Click(exportExcel_PaymentDetailsTab, "Export excel button on payment details tab on customer details popup");
    }

    /**
     * @return
     */
    public String get_Lastrowcell1_Paymentdetailstab() {
        fluentWait(tablerow1cell1_PaymentDetailsTab);
        HighlightElement(tablerow1cell1_PaymentDetailsTab);
        return tablerow1cell1_PaymentDetailsTab.getText();
    }

    /* *//**
     * Enters the given invoice number in the Invoice Search TextBox on the Payment Details tab.
     *
     * @param invoiceno The invoice number to be entered.
     * @throws NoSuchElementException          If the element is not found in the DOM.
     * @throws ElementNotInteractableException If the element is not interactable.
     * @throws TimeoutException                If the element is not available within the wait time.
     * @throws StaleElementReferenceException  If the element becomes stale before interaction.
     * @Author Balaji N
     *//*
    public void EnterInvoiceNo_Paymentdetailstab(String invoiceno) {
        try {
            fluentWait(InvoiceSearchTextBox_PaymentDetailsTab);
            delayWithGivenTime(500);

            InvoiceSearchTextBox_PaymentDetailsTab.click();
            delayWithGivenTime(500);

            InvoiceSearchTextBox_PaymentDetailsTab.clear();
            delayWithGivenTime(500);

            InvoiceSearchTextBox_PaymentDetailsTab.sendKeys(invoiceno);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Invoice Search TextBox not found on Payment Details tab", e);
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException("Invoice Search TextBox is not interactable", e);
        } catch (TimeoutException e) {
            throw new TimeoutException("Timed out waiting for Invoice Search TextBox", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Invoice Search TextBox became stale", e);
        }

    }
*/

    /**
     * Enters the given invoice number in the Invoice Search TextBox on the Payment Details tab.
     *
     * @param invoiceno The invoice number to be entered.
     * @throws RuntimeException If the textbox is not interactable after all retry attempts.
     * @Author Balaji N
     */
    public void EnterInvoiceNo_Paymentdetailstab(String invoiceno) {
        int maxRetries = 3;
        int retryCount = 0;
        wait_For_Page_To_Be_Stable(getDriver());
        while (retryCount < maxRetries) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

                // Re-fetch the element each time to avoid stale reference
                WebElement invoiceTextBox = wait.until(ExpectedConditions.elementToBeClickable(InvoiceSearchTextBox_PaymentDetailsTab));

                invoiceTextBox.click();
                delayWithGivenTime(300);
                invoiceTextBox.clear();
                delayWithGivenTime(300);
                invoiceTextBox.sendKeys(invoiceno);
                wait_For_Page_To_Be_Stable(getDriver());
                return; // Success

            } catch (StaleElementReferenceException e) {
                retryCount++;
                System.out.println("[Retry " + retryCount + "/" + maxRetries + "] StaleElementReferenceException: Refetching element...");
                delayWithGivenTime(1000);

            } catch (ElementNotInteractableException e) {
                retryCount++;
                System.out.println("[Retry " + retryCount + "/" + maxRetries + "] ElementNotInteractableException: Element not interactable, retrying...");
                delayWithGivenTime(1000);

            } catch (NoSuchElementException e) {
                throw new NoSuchElementException("Invoice Search TextBox not found on Payment Details tab after retry #" + retryCount, e);

            } catch (TimeoutException e) {
                throw new TimeoutException("Timed out waiting for Invoice Search TextBox on Payment Details tab after retry #" + retryCount, e);

            } catch (Exception e) {
                throw new RuntimeException("Unexpected error while entering invoice number: " + e.getMessage(), e);
            }
        }

        throw new RuntimeException("Invoice Search TextBox was not interactable after " + maxRetries + " retries.");
    }


    /**
     * Retrieves the value of the Invoice Search TextBox on the Payment Details tab.
     *
     * @return If the Invoice Search TextBox is found, returns its value; otherwise, returns an empty string.
     * @Author Balaji N
     */
    public String get_InvoiceNo_Paymentdetailstab() {
        return getElementAttribute(InvoiceSearchTextBox_PaymentDetailsTab, "Invoice Search TextBox field value on Payment Details tab");
    }

    /**
     * Verify whether the table grid is displayed on the payment details tab.
     *
     * @return If the table grid is displayed, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean Verify_TableGridOnPaymentDetailsTab_IsAppear() {
        int maxRetries = 2;
        int retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
                wait.pollingEvery(Duration.ofSeconds(2));
                wait.ignoring(StaleElementReferenceException.class, NoSuchElementException.class);
                wait.until(ExpectedConditions.visibilityOf(PaymentDetailsTabTable));

                return isElementDisplayed(PaymentDetailsTabTable, "Payment Details Tab Table Grid in customer details popup");

            } catch (StaleElementReferenceException | TimeoutException e) {
                retryCount++;
                System.out.println("[Retry " + retryCount + "/" + maxRetries + "] " + e.getClass().getSimpleName() +
                        " occurred while waiting for Payment Details Table. Retrying...");
                delayWithGivenTime(2000); // small buffer before retry

            } catch (Exception e) {
                printError(null,
                        "Verify_TableGridOnPaymentDetailsTab_IsAppear",
                        "Unexpected error occurred: " + e.getMessage(),
                        e);
                return false;
            }
        }

        System.out.println("Payment Details Table not visible after " + maxRetries + " attempts.");
        return false;
    }


    /**
     * Verifies if the given three-digit invoice number is present in the list of invoice suggestions
     * under the Unpaid Invoice Table on the Unpaid Tab in the Phone Order Page.
     *
     * @param threedigitinvoicenumber The three-digit invoice number to search for.
     * @return {@code true} if the invoice number is found in the list, otherwise {@code false}.
     * @throws NoSuchElementException         If the invoice list elements are not found in the DOM.
     * @throws StaleElementReferenceException If the elements become stale before interaction.
     * @throws TimeoutException               If the elements are not available within the expected wait time.
     * @throws NullPointerException           If the element list is not initialized.
     * @Author Balaji N
     */
    public boolean Verify_InvoiceNo_Paymentdetailstab(String threedigitinvoicenumber) {
        try {
            boolean isValid = false;

            if (ListOfInvoiceNumber_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage == null) {
                throw new NullPointerException("Invoice number list is not initialized.");
            }

            for (WebElement suggestion : ListOfInvoiceNumber_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage) {
                if (suggestion.getText().contains(threedigitinvoicenumber)) {
                    isValid = true;
                    System.out.println("Displayed invoice autosuggestion total count: "
                            + ListOfInvoiceNumber_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage.size());
                    System.out.println("Displayed invoice suggestion: " + suggestion.getText());
                }
            }

            return isValid;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No invoice suggestions found in the unpaid invoice table.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("Invoice suggestion elements became stale.", e);
        } catch (TimeoutException e) {
            throw new TimeoutException("Timed out waiting for invoice suggestion elements.", e);
        }
    }

    /**
     * Verifies if the "No Customer Payments Found" label is displayed on the payment details tab
     *
     * @return If the label is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_NoCustomerPaymentsFound_PaymentDetailsTab() {
        return is_Element_Displayed(NoCustomerFound_PaymentDetailsTab, "No Customer Payments Found label on the payment details tab table grid in customer details popup");
    }

    /**
     * Verifies if the clear button is displayed on the invoice search box
     *
     * @return If the clear button is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_InvoiceSearchBox_ClearButton_Appears() {
        return is_Element_Displayed(clearbutton_InvoiceSearchTextBox_PaymentDetailsTab, "Invoice Search textbox clear button on payment details tab");
    }

    /**
     * Clicks the clear button on the invoice search box on the payment details tab
     *
     * @Author Balaji N
     */
    public void Click_InvoiceSearchBox_ClearButton() {
        js_Click(clearbutton_InvoiceSearchTextBox_PaymentDetailsTab, "Clear button on invoice search box on payment details tab");
    }

    /**
     * Enters the pay amount search textbox field on payment details tab - customer details popup.
     *
     * @param payamount The amount to be entered in the search textbox.
     * @Author Balaji N
     */
    public void Enter_PayAmountSearchBox_OnPaymentDetailsTab(String payamount) {
        wait_For_Page_To_Be_Stable(getDriver());
        int maxRetries = 3;
        By locator = By.xpath("(//thead[@class='k-grid-header']//tr[2]//th//span//input)[2]");

        for (int retry = 1; retry <= maxRetries; retry++) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                WebElement payAmountBox = wait.until(ExpectedConditions.elementToBeClickable(locator));

                payAmountBox.click();
                delayWithGivenTime(300);

                ClickAndType(payAmountBox, payamount,
                        "Payment amount search textbox field on payment details tab - customer edit popup");

                delayWithGivenTime(800);
                payAmountBox.sendKeys(Keys.ENTER);
                delayWithGivenTime(800);
                wait_For_Page_To_Be_Stable(getDriver());

                return; // Success
            } catch (StaleElementReferenceException | ElementNotInteractableException | TimeoutException e) {
                System.err.println("[Retry " + retry + "/" + maxRetries + "] Retrying due to: " + e.getClass().getSimpleName());
                delayWithGivenTime(1000); // short wait before retry
            } catch (Exception e) {
                printError(null,
                        "Enter_PayAmountSearchBox_OnPaymentDetailsTab",
                        "Unexpected error occurred while entering payment amount: " + e.getMessage(),
                        e);
                return;
            }
        }

        throw new RuntimeException("Failed to interact with Pay Amount textbox after " + maxRetries + " attempts.");
    }


    /**
     * Retrieves the value of the Pay Amount search textbox on the payment details tab
     *
     * @return If the Pay Amount search textbox is found, returns its value; otherwise, returns an empty string
     * @Author Balaji N
     */
    public String get_PayAmount_Paymentdetailstab() {
        return getElementAttribute(payAmountSearchBox_PaymentDetailsTab, "Pay Amount search textbox field value on Payment Details tab");
    }

    /**
     * Verifies if the given three-digit pay amount is present in the list of pay amount suggestions
     *
     * @param threedigitpayamount
     * @return If the pay amount is found in the list, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_PayAmount_Paymentdetailstab(String expectedAmount) {
        int retries = 2;

        while (retries > 0) {
            try {
                List<WebElement> paymentElements = getDriver().findElements(list_of_pay_amount_payment_details_tab);
                boolean allMatch = true;

                double expected = Math.abs(parseAmount(expectedAmount));

                for (WebElement paymentElement : paymentElements) {
                    HighlightElement(paymentElement);
                    String actualAmountText = paymentElement.getText().trim();

                    double actual = Math.abs(parseAmount(actualAmountText));

                    if (Double.compare(expected, actual) != 0) {
                        System.err.println("Mismatch found: " + actualAmountText);
                        allMatch = false;
                    }
                }

                return allMatch;

            } catch (StaleElementReferenceException staleEx) {
                retries--;
                System.out.println("StaleElementReferenceException caught. Retrying... Attempts left: " + retries);
                delayWithGivenTime(1000);

            } catch (Exception e) {
                printError(null, "Verify_PayAmount_Paymentdetailstab",
                        "Error verifying payment amount: " + e.toString(), e);
                return false;
            }
        }

        return false;
    }

    private double parseAmount(String amountText) {
        try {
            String clean = amountText.replaceAll("[$,]", "").trim();
            return Double.parseDouble(clean);
        } catch (NumberFormatException e) {
            System.out.println("Failed to parse amount: " + amountText);
            return 0.0;
        }
    }


    /**
     * Verifies the number of Pay Amount displayed on the payment details tab
     *
     * @return If the Pay Amount is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public int Verify_NoOfPayAmountDisplayedCount_PaymentDetails() {
        int no_of_paymentamount = ListOfPayAmount_PaymentDetailsTab.size();
        return no_of_paymentamount;
    }

    /**
     * Verify whether the pay amount search text box field clear button is displayed or not
     *
     * @return If the clear button or icon is displayed on pay amount search textbox field it return true, else returns false
     * @Author Balaji N
     */
    public boolean Verify_PayAmountSearchBox_ClearButton_Appears() {
        return is_Element_Displayed(clearbutton_PayAmountSearchBox_PaymentDetailsTab, "Pay Amount search textbox clear button on payment details tab");
    }

    /**
     * Clicks the clear button or icon in the pay amount search textbox field
     *
     * @Author Balaji N
     */
    public void Click_PayAmountSearchBox_ClearButton() {
        js_Click(clearbutton_PayAmountSearchBox_PaymentDetailsTab, "Clear button or icon on payment amount textbox field - customer details popup");
    }


    /*
     * LocalDate currentDate = LocalDate.now();
     *
     * // Calculate the next day LocalDate nextDay = currentDate.plusDays(1);
     *
     * // Format the date as a string (adjust the format to match the date picker's
     * // expected format) DateTimeFormatter formatter =
     * DateTimeFormatter.ofPattern("MM/dd/yyyy"); String formattedNextDay =
     * nextDay.format(formatter);
     * jsClearAndType(recipientDeliverydateOnPhoneOrderPage, formattedNextDay); //
     * DoubleClickAndType();
     */


    public void Enter_PayDateSearchBox_OnPaymentDetailsTab() { //String paydate
        payDateSearchBox_PaymentDetailsTab.clear();
        delayWithGivenTime(500);
        LocalDate currentDate = LocalDate.now();
        LocalDate previousDay = currentDate.plusDays(-1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedPreviousDay = previousDay.format(formatter);
        clickAndType(payDateSearchBox_PaymentDetailsTab, formattedPreviousDay);
    }

    /**
     * Enter the pay date on the pay date search box on the payment details tab
     *
     * @param paydate the pay date to enter
     * @Author Balaji N
     */
    public void Enter_PayDateSearchBox_OnPaymentDetailsTab(String paydate) {
        int retries = 3;

        while (retries > 0) {
            try {
                wait_For_Page_To_Be_Stable(getDriver());

                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(paydateSearchBox_PaymentDetailsTab));

                // Scroll into view if needed
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", searchBox);
                delayWithGivenTime(300);

                HighlightElement(searchBox); // Optional
                searchBox.click(); // Might fail if not interactable
                searchBox.clear();
                delayWithGivenTime(500);

                ClickAndType(searchBox, paydate, "Pay Date search textbox on payment details tab");
                delayWithGivenTime(500);
                searchBox.sendKeys(Keys.ENTER);

                wait_For_Page_To_Be_Stable(getDriver());

                System.out.println("✅ Pay date entered successfully: " + paydate);
                return;

            } catch (ElementNotInteractableException e) {
                retries--;
                System.out.println("⚠️ ElementNotInteractableException: Retrying... Attempts left: " + retries);
                delayWithGivenTime(1000);

                try {
                    WebElement searchBox = getDriver().findElement(paydateSearchBox_PaymentDetailsTab);
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", searchBox);
                } catch (Exception jsEx) {
                    System.out.println("⚠️ JS click also failed: " + jsEx.getMessage());
                }

            } catch (StaleElementReferenceException e) {
                retries--;
                System.out.println("⚠️ StaleElementReferenceException: Retrying... Attempts left: " + retries);
                delayWithGivenTime(1000);
            } catch (TimeoutException e) {
                retries--;
                System.out.println("⚠️ TimeoutException while waiting for element. Retrying... Attempts left: " + retries);
                delayWithGivenTime(1000);
            } catch (Exception e) {
                retries--;
                System.out.println("⚠️ Unexpected error: " + e.getMessage() + ". Retrying... Attempts left: " + retries);
                delayWithGivenTime(1000);
            }
        }

        throw new RuntimeException("❌ Failed to enter pay date after multiple retries.");
    }


    public String previousDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate previousDay = currentDate.plusDays(-1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedPreviousDay = previousDay.format(formatter);
        return formattedPreviousDay;
    }

    public String NextDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate previousDay = currentDate.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedPreviousDay = previousDay.format(formatter);
        return formattedPreviousDay;
    }

    /**
     * Retrieves the value of the Pay Date search textbox on the payment details tab
     *
     * @return If the Pay Date search textbox is found, returns its value; otherwise, returns an empty string
     * @Author Balaji N
     */
    public String get_PayDate_Paymentdetailstab() {
        wait_For_Page_To_Be_Stable(getDriver());
        return getElementAttribute(payDateSearchBox_PaymentDetailsTab, "Pay Date search textbox field value on Payment Details tab");
    }

    /**
     * Verifies if the Pay Date is displayed on the payment details tab
     *
     * @param paydate the pay date to verify
     * @return If the Pay Date is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_PayDate_Paymentdetailstab(String paydate) {
        boolean isValid = false;

        for (WebElement suggestion : ListOfPayDate_PaymentDetailsTab) {
            HighlightElement(suggestion);
            String actualPayDate = suggestion.getText().trim();

            // Log each value checked
            Allure.step("🔍 Checking Pay Date: " + actualPayDate);

            if (actualPayDate.contains(paydate)) {
                Allure.step("✅ Found matching Pay Date: " + paydate);
                isValid = true;
                break;
            }
        }

        if (!isValid) {
            Allure.step("❌ Expected Pay Date not found in the list: " + paydate);
        }

        return isValid;
    }


    public boolean Verify_PayDateSearchBox_ClearButton_Appears() {
        return isElementDisplayed(clearbutton_PayDateSearchBox_PaymentDetailsTab, "Clear button on pay date search box on payment details tab");
    }

    /**
     * Clicks the Clear button on the pay date search box on the payment details tab
     *
     * @Author Balaji N
     */
    public void Click_PayDateSearchBox_ClearButton() {
        js_Click(clearbutton_PayDateSearchBox_PaymentDetailsTab, "Clear button on pay date search box on payment details tab");
    }

    /**
     * Verify filter icon options on pay date on payment details tab
     *
     * @return If the filter icon options are displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_FilterIconOptions_PayDateOnPaymentDetailsTab_OnCustomerDetailsPopup() {
        HighlightElement(filterIconOnPayDate_PaymentDetailsTab);
        Click(filterIconOnPayDate_PaymentDetailsTab, "Filter Icon on Pay Date on payment details tab");
        delayWithGivenTime(1000);

        List<String> expectedPayDateFilterValues = Arrays.asList("Is equal to", "Is not equal to", "Is after or equal to", "Is after", "Is before or equal to", "Is before", "Is null", "Is not null");
        Set<String> actualPayDateFilterValues = new HashSet<>();

        for (WebElement dropdownValue : ListOfFilterOptionsOnPayDate_PaymentDetailsTab) {
            HighlightElement(dropdownValue);
            actualPayDateFilterValues.add(dropdownValue.getText());
        }

        boolean allOptionsDisplayed = actualPayDateFilterValues.containsAll(expectedPayDateFilterValues);

        if (!allOptionsDisplayed) {
            System.out.println("Missing pagination values in dropdown: " + expectedPayDateFilterValues.stream().filter(value -> !actualPayDateFilterValues.contains(value)).collect(Collectors.joining(", ")));
        }

        return allOptionsDisplayed;
    }

    /**
     * Clicks the calendar icon on the pay date on the payment details tab
     *
     * @Author Balaji N
     */
    public void Click_CalendarIconOnPayDate_OnPaymentDetailsTab() {
        Click(calendarIconOnPayDate_PaymentDetailsTab, "Calendar Icon on Pay Date on payment details tab");
    }

    /**
     * Selects the current date from the date picker icon on the Payment Details tab.
     *
     * @throws NoSuchElementException if any required element is not found
     * @throws TimeoutException       if an element does not become interactable within the expected time
     * @throws NumberFormatException  if the displayed year is not in the correct numeric format
     * @Author Balaji N
     */
    public void SelectTheDate_FromDatePickerIcon_OnPaymentDetailsTab() {
        try {
            // Get current date details
            LocalDate currentDate = LocalDate.now();
            int targetDay = currentDate.getDayOfMonth();
            String targetMonth = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH); // Full month name
            int targetYear = currentDate.getYear();

            delayWithGivenTime(2000);

            // Locate the month and year display
            HighlightElement(calendar_MonthYear_PayDate_PaymentDetailsTab);
            if (!calendar_MonthYear_PayDate_PaymentDetailsTab.isDisplayed()) {
                throw new NoSuchElementException("Calendar month-year display is not visible.");
            }

            delayWithGivenTime(2000);

            // Loop to navigate to the correct month and year
            while (true) {
                String displayedMonthYear = calendar_MonthYear_PayDate_PaymentDetailsTab.getText().trim();
                if (displayedMonthYear.isEmpty()) {
                    throw new NoSuchElementException("Failed to retrieve displayed month and year.");
                }

                String[] parts = displayedMonthYear.split(" ");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Unexpected format of month-year display: " + displayedMonthYear);
                }

                String displayedMonth = parts[0]; // Displayed month as a full name
                int displayedYear = Integer.parseInt(parts[1]);

                // Check if the displayed date matches the target date
                if (displayedYear == targetYear && displayedMonth.equalsIgnoreCase(targetMonth)) {
                    break;
                }

                WebElement arrowButton;
                if (displayedYear > targetYear || (displayedYear == targetYear && isDisplayedMonthAfter(displayedMonth, targetMonth))) {
                    // Click the previous arrow if the displayed date is after the target date
                    arrowButton = getDriver().findElement(By.xpath("//span[@class='k-icon k-i-arrow-60-left']"));
                } else {
                    // Click the next arrow if the displayed date is before the target date
                    arrowButton = getDriver().findElement(By.xpath("//span[@class='k-icon k-i-arrow-60-right']"));
                }

                if (arrowButton == null || !arrowButton.isDisplayed()) {
                    throw new NoSuchElementException("Navigation arrow button is missing in the date picker.");
                }
                arrowButton.click();
                delayWithGivenTime(1000);
            }

            // Select the desired date
            boolean dateSelected = false;
            for (WebElement date : ListOfdates_PayDate_PaymentDetailsTab) {
                if (date.getText().equals(String.valueOf(targetDay)) && date.isEnabled()) {
                    HighlightElement(date);
                    date.click();
                    dateSelected = true;
                    break;
                }
            }

            if (!dateSelected) {
                throw new NoSuchElementException("Unable to find and select the target date: " + targetDay);
            }
        } catch (NoSuchElementException | TimeoutException | NumberFormatException e) {
            throw new RuntimeException("Error selecting the date in the Payment Details tab: " + e.getMessage(), e);
        }
    }

    // Helper method to compare month order using full month names
    private boolean isDisplayedMonthAfter(String displayedMonth, String targetMonth) {
        List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
        return months.indexOf(displayedMonth) > months.indexOf(targetMonth);
    }

  /*  public void SelectTheDate_FromDatePickerIcon_OnPaymentDetailsTab() {
        try {
            // Get current date details
            LocalDate currentDate = LocalDate.now();
            int targetDay = currentDate.getDayOfMonth();
            String targetMonth = currentDate.getMonthValue();
            int targetYear = currentDate.getYear();
            delayWithGivenTime(2000);
            // Locate the month and year display
            HighlightElement(calendar_MonthYear_PayDate_PaymentDetailsTab);
            if (!calendar_MonthYear_PayDate_PaymentDetailsTab.isDisplayed()) {
                throw new NoSuchElementException("Calendar month-year display is not visible.");
            }

            delayWithGivenTime(2000);
            // Loop to navigate to the correct month and year
            while (true) {
                String displayedMonthYear = calendar_MonthYear_PayDate_PaymentDetailsTab.getText();
                if (displayedMonthYear.isEmpty()) {
                    throw new NoSuchElementException("Failed to retrieve displayed month and year.");
                }

                String[] parts = displayedMonthYear.split(" ");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Unexpected format of month-year display: " + displayedMonthYear);
                }

                String displayedMonth = (parts[0]); // Convert displayed month name to number
                int displayedYear = Integer.parseInt(parts[1]);

                if (displayedYear == targetYear && displayedMonth.equalsIgnoreCase(targetMonth)) {
                    break;
                }

                WebElement arrowButton;
                if (displayedYear > targetYear || (displayedYear == targetYear && displayedMonth > targetMonth)) {
                    // Click the previous arrow if the displayed date is after the target date
                    arrowButton = getDriver().findElement(By.xpath("//span[@class='k-icon k-i-arrow-60-left']"));
                } else {
                    // Click the next arrow if the displayed date is before the target date
                    arrowButton = getDriver().findElement(By.xpath("//span[@class='k-icon k-i-arrow-60-right']"));
                }
                if (arrowButton == null) {
                    throw new NoSuchElementException("Navigation arrow button is missing in the date picker.");
                }
                arrowButton.click();
            }

            // Select the desired date
            boolean dateSelected = false;
            for (WebElement date : ListOfdates_PayDate_PaymentDetailsTab) {
                if (date.getText().equals(String.valueOf(targetDay))) {
                    //  Click(date, "Date on Pay Date datepicker in Payment Details tab");
                    date.click();
                    dateSelected = true;
                    break;
                }
            }

            if (!dateSelected) {
                throw new NoSuchElementException("Unable to find and select the target date: " + targetDay);
            }
        } catch (NoSuchElementException | TimeoutException | NumberFormatException e) {
            throw new RuntimeException("Error selecting the date in the Payment Details tab: " + e.getMessage(), e);
        }
    }
*/

    // Method to convert month name to number (for example, "January" to 1, "February" to 2, etc.)
    private int getMonthAsNumber(String monthName) {
        switch (monthName.toLowerCase()) {
            case "january":
                return 1;
            case "february":
                return 2;
            case "march":
                return 3;
            case "april":
                return 4;
            case "may":
                return 5;
            case "june":
                return 6;
            case "july":
                return 7;
            case "august":
                return 8;
            case "september":
                return 9;
            case "october":
                return 10;
            case "november":
                return 11;
            case "december":
                return 12;
            default:
                throw new IllegalArgumentException("Invalid month name: " + monthName);
        }
    }

    /**
     * Clicks the filter icon on the pay date on the payment details tab
     *
     * @Author Balaji N
     */
    public void Click_FilterIconOptions_PayDateOnPaymentDetailsTab_OnCustomerDetailsPopup() {
        Click(filterIconOnPayDate_PaymentDetailsTab, "Filter Icon on Pay Date on payment details tab");
    }

    /**
     * Clicks the filter icon on the pay date on the payment details tab
     *
     * @Author Balaji N
     */
    public void Click_FilterIconOptions_PayDateOnPaymentDetailsTab_OnCustomerDetailsPopup(String filterValue) {
        Click(filterIconOnPayDate_PaymentDetailsTab, "Filter Icon on Pay Date on payment details tab");
        delayWithGivenTime(1000);
        for (int i = 0; i < ListOfFilterOptionsOnPayDate_PaymentDetailsTab.size(); i++) {
            if (ListOfFilterOptionsOnPayDate_PaymentDetailsTab.get(i).getText().contains(filterValue)) {
                HighlightElement(ListOfFilterOptionsOnPayDate_PaymentDetailsTab.get(i));
                ListOfFilterOptionsOnPayDate_PaymentDetailsTab.get(i).click();
                break;
            }
        }
    }

    /**
     * Enter MOP search box on payment details tab
     *
     * @param mop the mode of payment to be entered
     * @Author Balaji N
     */
    /**
     * Enters the given MOP (Mode of Payment) in the search box on the Payment Details tab.
     *
     * @param mop The MOP value to enter.
     * @Author Balaji N
     */
    public void Enter_MOPSearchBox_OnPaymentDetailsTab(String mop) {
        int maxRetries = 3;
        By locator = By.xpath("//span[@data-field='MOP']//input");

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                // Ensure page and jQuery/AJAX is fully loaded
                wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
                wait.until(driver -> (Boolean) ((JavascriptExecutor) driver).executeScript("return !!window.jQuery && jQuery.active === 0"));

                WebElement mopSearchBox = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

                // JavaScript scroll and visibility fix
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", mopSearchBox);
                delayWithGivenTime(500);

                // Ensure it's not covered by overlay
                if (!mopSearchBox.isDisplayed() || !mopSearchBox.isEnabled()) {
                    throw new ElementNotInteractableException("MOP search box not visible or not enabled.");
                }

                mopSearchBox.clear();
                mopSearchBox.click();
                mopSearchBox.sendKeys(mop);
                // ClickAndType(mopSearchBox, mop, "MOP search box on payment details tab");
                delayWithGivenTime(500);
                mopSearchBox.sendKeys(Keys.ENTER);
                return;

            } catch (ElementNotInteractableException | StaleElementReferenceException | TimeoutException e) {
                System.err.println("[Retry " + attempt + "/" + maxRetries + "] - Retrying due to: " + e.getClass().getSimpleName());
                delayWithGivenTime(1000);
            } catch (Exception e) {
                printError(null, "Enter_MOPSearchBox_OnPaymentDetailsTab",
                        "Unexpected error while interacting with MOP Search Box: " + e.getMessage(), e);
                break;
            }
        }

        throw new RuntimeException("Failed to enter MOP in Payment Details tab after " + maxRetries + " attempts.");
    }


    /**
     * It retrieves the value of MOP search box on payment details tab
     *
     * @return If the MOP search textbox is found, returns its value; otherwise, returns an empty string
     * @Author Balaji N
     */
    public String get_MOPSearchBox_Paymentdetailstab() {
        return getElementAttribute(MOPSearchBox_PaymentDetailsTab, "MOP search textbox field on payment details tab");
    }

    /**
     * Verify whether the Mode Of Pay - on Payment details tab in the customer details popup
     *
     * @param mop
     * @return If the mode of pay is displayed return true else false
     * @Author Balaji N
     */
    public boolean Verify_MOP_Paymentdetailstab(String mop) {
        boolean isValid = false;
        for (WebElement suggestion : ListOfMOP_PaymentDetailsTab) {
            HighlightElement(suggestion);
            System.out.println("Displayed suggestion of MOP: " + suggestion.getText());
            if (suggestion.getText().trim().equals(mop)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    /**
     * Verifies if the Clear button on the MOP search box on the payment details tab appears
     *
     * @return If the Clear button on the MOP search box on the payment details tab appears, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_MOPSearchBox_ClearButton_Appears() {
        return is_Element_Displayed(clearbutton_MOPSearchBox_PaymentDetailsTab, "Clear button on MOP search textbox field on payment details tab");
    }

    /**
     * Clicks the Clear button on the MOP search box on the payment details tab
     *
     * @Author Balaji N
     */
    public void Click_MOPSearchBox_ClearButton() {
        js_Click(clearbutton_MOPSearchBox_PaymentDetailsTab, "Clear button on MOP search box on payment details tab");
    }

    /**
     * Enter Check No search box on payment details tab
     *
     * @param checkno the check number to be entered
     * @Author Balaji N
     */
    /**
     * Enters the given check number in the Check No search box on the Payment Details tab.
     *
     * @param checkno The check number to enter.
     * @Author Balaji N
     */
    public void Enter_CheckNoSearchBox_OnPaymentDetailsTab(String checkno) {
        wait_For_Page_To_Be_Stable(getDriver());
        int maxRetries = 3;
        By locator = By.xpath("//span[@data-field='CheckNo']//input");

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                WebElement checkNoBox = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

                wait.until(ExpectedConditions.elementToBeClickable(checkNoBox));
                checkNoBox.click();
                delayWithGivenTime(300);
                checkNoBox.clear();
                delayWithGivenTime(300);
                checkNoBox.sendKeys(checkno);
                delayWithGivenTime(300);
                checkNoBox.sendKeys(Keys.ENTER);
                wait_For_Page_To_Be_Stable(getDriver());
                return;
            } catch (StaleElementReferenceException | ElementNotInteractableException |
                     TimeoutException e) {
                System.err.println("[Retry " + attempt + "/" + maxRetries + "] Retrying due to: " + e.getClass().getSimpleName());
                delayWithGivenTime(1000);
            } catch (Exception e) {
                printError(ChecknoSearchBox_PaymentDetailsTab,
                        "Enter_CheckNoSearchBox_OnPaymentDetailsTab",
                        "Unexpected error while entering Check No: " + e.getMessage(), e);
                break;
            }
        }

        throw new RuntimeException("Failed to enter Check No in Payment Details tab after " + maxRetries + " attempts.");
    }


    public String get_CheckNoSearchBox_Paymentdetailstab() {
        return getElementAttribute(ChecknoSearchBox_PaymentDetailsTab, "Check No search textbox field value on Payment Details tab");
    }

    /**
     * Verify whether the check number is displayed or not
     *
     * @param checkno
     * @return If the check number is displayed payment details tab it returns true else false
     * @Author Balaji N
     */
    public boolean Verify_CheckNo_Paymentdetailstab(String checkno) {
        boolean isValid = false;
        for (WebElement suggestion : ListOfCheckno_PaymentDetailsTab) {
            Highlight_Element(suggestion, "List of Check Number on Payment Details Tab");
            if (suggestion.getText().trim().contains(checkno)) {
                isValid = true;
                System.out.println("Displayed suggestion of Check No: " + suggestion.getText());
                System.out.println("Found matching Check No: " + checkno);
                break;
            }
        }
        return isValid;
    }

    /**
     * Verifies if the Clear button on the Check No search box on the payment details tab appears
     *
     * @return If the Clear button on the Check No search box on the payment details tab appears, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_CheckNoSearchBox_ClearButton_Appears() {
        return is_Element_Displayed(clearbutton_ChecknoSearchBox_PaymentDetailsTab, "Clear button on Check No search textbox field on payment details tab");
    }

    /**
     * Clicks the Clear button on the Check No search box on the payment details tab
     *
     * @Author Balaji N
     */
    public void Click_CheckNoSearchBox_ClearButton() {
        js_Click(clearbutton_ChecknoSearchBox_PaymentDetailsTab, "Clear button on Check No search box on payment details tab");
    }

    /**
     * Enter CC Number search box on payment details tab
     *
     * @param checkno the check number to be entered
     * @Author Balaji N
     */
    public void Enter_CCNumberSearchBox_OnPaymentDetailsTab(String ccNumber) {
        int maxRetries = 3;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                // Ensure page and AJAX/jQuery are fully loaded
                wait.until(driver -> ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete"));
                wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return !!window.jQuery && jQuery.active === 0"));

                // Re-fetch the WebElement in each attempt to avoid stale reference
                WebElement ccNumberBox = wait.until(ExpectedConditions.visibilityOf(CCNumberSearchBox_PaymentDetailsTab));
                wait.until(ExpectedConditions.elementToBeClickable(CCNumberSearchBox_PaymentDetailsTab));

                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block:'center'});", ccNumberBox);
                delayWithGivenTime(300);

                if (!ccNumberBox.isDisplayed() || !ccNumberBox.isEnabled()) {
                    throw new ElementNotInteractableException("CC Number input field is not interactable.");
                }

                wait.until(ExpectedConditions.elementToBeClickable(ccNumberBox));
                ccNumberBox.click();
                delayWithGivenTime(300);
                ccNumberBox.clear();
                delayWithGivenTime(300);
                ccNumberBox.sendKeys(ccNumber);
                delayWithGivenTime(300);
                ccNumberBox.sendKeys(Keys.ENTER);
                return;

            } catch (StaleElementReferenceException | ElementNotInteractableException | TimeoutException e) {
                System.err.println("[Retry " + attempt + "/" + maxRetries + "] Retrying due to: " + e.getClass().getSimpleName());
                delayWithGivenTime(1000);
            } catch (Exception e) {
                printError(CCNumberSearchBox_PaymentDetailsTab,
                        "Enter_CCNumberSearchBox_OnPaymentDetailsTab",
                        "Unexpected error while entering Credit Card Number: " + e.getMessage(), e);
                break;
            }
        }

        throw new RuntimeException("Failed to enter Credit Card Number after " + maxRetries + " retries.");
    }


    /**
     * It retrieves the value of CC Number search box on payment details tab
     *
     * @return If the CC Number search textbox is found, returns its value; otherwise, returns an empty string
     * @Author Balaji N
     */
    public String get_CCNumberSearchBox_Paymentdetailstab() {
        return getElementAttribute(CCNumberSearchBox_PaymentDetailsTab, "Credit card number search textbox field value on Payment Details tab");
    }

    /**
     * Verifies if the Credit Card Number on the CC Number search box on the payment details tab appears
     *
     * @param ccnumber
     * @return If the Credit Card Number on the CC Number search box on the payment details tab appears, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_CCNumber_Paymentdetailstab(String ccnumber) {
        boolean isValid = false;
        for (WebElement suggestion : ListOfCCNumber_PaymentDetailsTab) {
            Highlight_Element(suggestion, "List of Credit Card Number on Payment Details Tab");
            System.out.println("Displayed suggestion of Credit Card Number: " + suggestion.getText());
            if (suggestion.getText().trim().equals(ccnumber)) {
                isValid = true;
                System.out.println("Found matching Credit Card Number: " + ccnumber);
                break;
            }
        }
        return isValid;
    }

    /**
     * Verifies if the Clear button on the CC Number search box on the payment details tab appears
     *
     * @return If the Clear button on the CC Number search box on the payment details tab appears, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_CCNumberSearchBox_ClearButton_Appears() {
        return is_Element_Displayed(clearbutton_CCNumberSearchBox_PaymentDetailsTab, "Clear button on Credit Card Number search textbox field on payment details tab");
    }

    /**
     * Clicks the Clear button on the CC Number search box on the payment details tab
     *
     * @Author Balaji N
     */
    public void Click_CCNumberSearchBox_ClearButton() {
        js_Click(clearbutton_CCNumberSearchBox_PaymentDetailsTab, "Clear button on Credit Card Number search textbox field on payment details tab");
    }

    /**
     * Verifies if the pagination section on the payment details tab is displayed
     *
     * @return If the pagination section on the payment details tab is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_PaginationSection_OnPaymentDetailsTab() {
        wait_For_Page_To_Be_Stable(getDriver());

        int maxRetries = 3;
        int retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                WebElement paginationSection = paginationSection_PaymentDetailsTab;
                scrollAction(paginationSection);

                if (paginationSection.isDisplayed()) {
                    System.out.println("✅ Pagination section is displayed on payment details tab.");
                    return true;
                } else {
                    System.out.println("⚠️ Pagination section found but not visible.");
                }

            } catch (StaleElementReferenceException | NoSuchElementException e) {
                retryCount++;
                System.out.println("[Retry " + retryCount + "/" + maxRetries + "] Element issue: " + e.getClass().getSimpleName() +
                        " - Retrying to find pagination section...");
                delayWithGivenTime(1000);
            } catch (Exception e) {
                printError(paginationSection_PaymentDetailsTab, "Verify_PaginationSection_OnPaymentDetailsTab",
                        "Unexpected error while verifying pagination section: " + e.getMessage(), e);
                return false;
            }
        }

        System.out.println("❌ Pagination section not found or not visible after " + maxRetries + " retries.");
        return false;
    }


    public boolean Verify_PaginationSection_On_OrderDetailsTab() {
        wait_For_Page_To_Be_Stable(getDriver());

        int maxRetries = 3;
        int retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                WebElement paginationSection = getDriver().findElement(By.xpath("//div[@id='gvCustomerOrderDtls']//div[@class='k-pager-wrap k-grid-pager k-widget k-floatwrap']"));
                HighlightElement(paginationSection);
                if (paginationSection.isDisplayed()) {
                    System.out.println("✅ Pagination section is displayed on order details tab.");
                    return true;
                } else {
                    System.out.println("⚠️ Pagination section found but not visible.");
                }

            } catch (StaleElementReferenceException | NoSuchElementException e) {
                retryCount++;
                System.out.println("[Retry " + retryCount + "/" + maxRetries + "] Element issue: " + e.getClass().getSimpleName() +
                        " - Retrying to find pagination section on order details tab...");
                delayWithGivenTime(1000);
            } catch (Exception e) {
                printError(paginationSection_OrderDetailsTab, "Verify_PaginationSection_On_OrderDetailsTab",
                        "Unexpected error while verifying pagination section: " + e.getMessage(), e);
                return false;
            }
        }

        System.out.println("❌ Pagination section not found or not visible after " + maxRetries + " retries.");
        return false;
    }


    /**
     * Verifies if the page list number on the payment details tab is displayed
     *
     * @return If the page list number on the payment details tab is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_PageListNumber_OnPaymentDetailsTab() {
        return is_Element_Displayed(pagelistnumber_PaymentDetailsTab, "Pagelist number on payment details tab");
    }

    /**
     * Verifies if the pagination info on the payment details tab is displayed
     *
     * @return If the pagination info on the payment details tab is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_PaginationInfo_OnPaymentDetailsTab() {
        return is_Element_Displayed(paginationInfo_PaymentDetailsTab, "Pagination info on payment details tab");
    }

    /**
     * Retrieves the pagination info on the payment details tab
     *
     * @return If the pagination info on the payment details tab is displayed, returns the pagination info; otherwise, returns null
     * @Author Balaji N
     */
    public String get_PaginationInfo_PaymentDetailsTab() {
        wait_For_Page_To_Be_Stable(getDriver());
        Highlight_Element(paginationInfo_PaymentDetailsTab, "Pagination Info on Payment Details Tab");
        String paginationText = paginationInfo_PaymentDetailsTab.getText();

        Pattern pattern = Pattern.compile("(\\d+\\s-\\s\\d+)");
        Matcher matcher = pattern.matcher(paginationText);

        if (matcher.find()) {
            String itemRange = matcher.group(1);
            System.out.println("Item range: " + itemRange);
        } else {
            System.out.println("Item range not found.");
        }
        return matcher.group(1);
    }

    public String get_TotalInfo_Appears_OnUnpaidTab() {
        HighlightElement(paginationInfo_PaymentDetailsTab);
        String paginationText = paginationInfo_PaymentDetailsTab.getText();

        // Use regular expressions to extract the total number of items
        Pattern pattern = Pattern.compile("of\\s(\\d+)\\sitems");
        Matcher matcher = pattern.matcher(paginationText);

        if (matcher.find()) {
            String totalItems = matcher.group(1);
            System.out.println("Total number of items: " + totalItems);
        } else {
            System.out.println("Total number of items not found.");
        }

        return matcher.group(1);
    }

    /**
     * Clicks the three dots on the pagination section on the payment details tab
     *
     * @Author Balaji N
     */
    /**
     * Clicks the three dots on the pagination section of the Payment Details tab,
     * with retry mechanism for handling dynamic loading delays.
     *
     * @Author Balaji N
     */
    public void ClickThreeDots_Pagination_PaymentDetails() {
        int maxRetries = 3;
        int attempts = 0;
        while (attempts < maxRetries) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                // Ensure full page and jQuery are loaded
                wait.until(driver -> ((JavascriptExecutor) getDriver())
                        .executeScript("return document.readyState").equals("complete"));
                wait.until(driver -> (Boolean) ((JavascriptExecutor) getDriver())
                        .executeScript("return !!window.jQuery && jQuery.active === 0"));

                // Wait until element is visible and clickable
                wait.until(ExpectedConditions.visibilityOf(threedotsOnPagination_PaymentDetailsTab));
                wait.until(ExpectedConditions.elementToBeClickable(threedotsOnPagination_PaymentDetailsTab));

                // Scroll into view to ensure it's interactable
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", threedotsOnPagination_PaymentDetailsTab);
                delayWithGivenTime(300);

                // Click using JavaScript
                js_Click(threedotsOnPagination_PaymentDetailsTab, "Click three dots on pagination section on payment details tab");
                return;

            } catch (StaleElementReferenceException | ElementNotInteractableException | TimeoutException e) {
                System.err.println("[Retry " + (attempts + 1) + "/" + maxRetries + "] Retrying click due to: " + e.getClass().getSimpleName());
                delayWithGivenTime(1000); // allow DOM to settle
            } catch (Exception e) {
                printError(threedotsOnPagination_PaymentDetailsTab,
                        "ClickThreeDots_Pagination_PaymentDetails",
                        "Unexpected error during clicking three dots: " + e.getMessage(),
                        e);
                break;
            }

            attempts++;
        }

        throw new RuntimeException("Failed to click 'Three Dots' on Pagination after " + maxRetries + " attempts.");
    }


    public boolean Verify_11to20Pages_Pagination_PaymentDetails() {
        wait_For_Page_To_Be_Stable(getDriver());
        List<WebElement> paginationNumbers = getDriver().findElements(By.xpath("//ul[@class='k-pager-numbers k-reset']//li/a"));

        // Verify pagination numbers from 11 to 20
        boolean isPaginationCorrect = true;
        for (WebElement paginationNumber : paginationNumbers) {
            String text = paginationNumber.getText();
            if (!text.equals("...")) {
                try {
                    int pageNumber = Integer.parseInt(text);
                    if (pageNumber < 11 || pageNumber > 20) {
                        isPaginationCorrect = false;
                        break;
                    }
                } catch (NumberFormatException e) {
                    isPaginationCorrect = false;
                    break;
                }
            }
        }

        // Print the result
        if (isPaginationCorrect) {
            System.out.println("Pagination numbers from 11 to 20 are displayed correctly.");
            return true;
        } else {
            System.out.println("Pagination numbers from 11 to 20 are NOT displayed correctly.");
            return false;
        }
    }

    /**
     * Clicks the page number 2 on the pagination section on the payment details tab
     *
     * @Author Balaji N
     */
    public void Click_Pagenumber2_Pagination_PaymentDetails() {
        js_Click(page2_PaymentDetailsTab, "Page number 2 - pagination section on payment details tab");
    }

    public boolean Verify_LastPageNumber_OnPaymentDetailsTab() {
        List<WebElement> paginationNumbers = getDriver().findElements(By.xpath("//ul[@class='k-pager-numbers k-reset']//li/a"));

        // Find the highest page number
        int highestPageNumber = 0;
        for (WebElement paginationNumber : paginationNumbers) {
            String text = paginationNumber.getText();
            if (!text.equals("...")) {
                try {
                    int pageNumber = Integer.parseInt(text);
                    if (pageNumber > highestPageNumber) {
                        highestPageNumber = pageNumber;
                    }
                } catch (NumberFormatException e) {
                    e.getMessage();
                }
            }
        }

        // Verify the highest page number is displayed
        WebElement currentPageElement = getDriver().findElement(By.xpath("//ul[@class='k-pager-numbers k-reset']//li//span[@class='k-state-selected']"));
        int currentPageNumber = Integer.parseInt(currentPageElement.getText());

        if (currentPageNumber == highestPageNumber) {
            System.out.println("Last page is displayed correctly.");
            return true;
        } else {
            System.out.println("Last page is NOT displayed correctly.");
            return false;
        }
    }

    /**
     * Clicks the invoice label table grid on the payment details tab
     *
     * @Author Balaji N
     */
    public void Click_InvoiceHeader_PaymentDetails() {
        int attempts = 0;
        while (attempts < 3) {
            try {
                js_Click(invoicelabel_paymentdetailsTab, "Invoice Label table grid on payment details tab");
                wait_For_Page_To_Be_Stable(getDriver());
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException caught. Retrying... Attempt: " + (attempts + 1));
                invoicelabel_paymentdetailsTab = getDriver().findElement(By.xpath("//div[@id='gvCustomerPaymentDtls']//a[normalize-space()='Invoice']"));
                attempts++;
            }
        }
    }


    /**
     * verify the invoice header on the payment details tab
     *
     * @return If the invoice header is sorted in ascending order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_InvoiceHeader_Sorted_ascending_PaymentDetailsTab() {
        List<String> originalInvoiceNumbers = new ArrayList<>();
        for (WebElement invoiceElement : ListOfInvoiceNumber_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage) {
            originalInvoiceNumbers.add(invoiceElement.getText());
        }
        delayWithGivenTime(2000);

        List<String> sortedInvoiceNumbers = new ArrayList<>(originalInvoiceNumbers);
        Collections.sort(sortedInvoiceNumbers);

        if (originalInvoiceNumbers.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Original invoice numbers: " + originalInvoiceNumbers);
            System.out.println("Sorted invoice numbers: " + sortedInvoiceNumbers);
            System.out.println("Invoices are not sorted in ascending order.");
            return false;
        }
    }

    /**
     * verify the invoice header descending sort icon on the payment details tab
     *
     * @return If the invoice header is sorted in descending order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_InvoiceHeader_Sorted_descending_PaymentDetailsTab() {
        // Store the invoice numbers in a list
        List<String> originalInvoiceNumbers = new ArrayList<>();
        for (WebElement invoiceElement : ListOfInvoiceNumber_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage) {
            originalInvoiceNumbers.add(invoiceElement.getText());
        }
        delayWithGivenTime(2000);
        // Create a copy of the list and sort it
        List<String> sortedInvoiceNumbers = new ArrayList<>(originalInvoiceNumbers);
        Collections.sort(sortedInvoiceNumbers, Collections.reverseOrder());

        if (originalInvoiceNumbers.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Invoices are not sorted in descending order.");
            return false;
        }
    }

    /**
     * verify the invoice header default order on the payment details tab
     *
     * @return If the invoice header is sorted in default order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_InvoiceHeader_DefaultOrder_PaymentDetailsTab() {
        delayWithGivenTime(1000);
        List<String> originalInvoiceNumbers = new ArrayList<>();
        for (WebElement invoiceElement : ListOfInvoiceNumber_UnpaidInvoiceTable_UnpaidTabOnPhoneOrderPage) {
            originalInvoiceNumbers.add(invoiceElement.getText());
        }
        delayWithGivenTime(1000);
        List<String> sortedInvoiceNumbers = new ArrayList<>(originalInvoiceNumbers);
        Collections.sort(sortedInvoiceNumbers);

        if (originalInvoiceNumbers.equals(sortedInvoiceNumbers)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Clicks the pay amount label table grid on the payment details tab
     *
     * @Author Balaji N
     */
    /**
     * Clicks the Pay Amount label/header in the table grid on the Payment Details tab,
     * with retry mechanism to handle slow loading or dynamic refresh.
     *
     * @Author Balaji N
     */
    public void Click_PayAmountHeader_PaymentDetails() {
        int maxRetries = 3;
        int attempts = 0;

        while (attempts < maxRetries) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                // Wait for full page and jQuery readiness
                wait.until(driver -> ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete"));
                wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return !!window.jQuery && jQuery.active === 0"));

                // Wait for the header to be visible and clickable
                wait.until(ExpectedConditions.visibilityOf(payamountlabel_paymentdetailsTab));
                wait.until(ExpectedConditions.elementToBeClickable(payamountlabel_paymentdetailsTab));

                // Scroll to the element to ensure it's interactable
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block:'center'});", payamountlabel_paymentdetailsTab);
                delayWithGivenTime(300);

                // Perform JavaScript click
                js_Click(payamountlabel_paymentdetailsTab, "Pay Amount label in table grid on payment details tab");
                return;

            } catch (StaleElementReferenceException | ElementNotInteractableException | TimeoutException e) {
                System.err.println("[Retry " + (attempts + 1) + "/" + maxRetries + "] Retrying click on Pay Amount header due to: " + e.getClass().getSimpleName());
                delayWithGivenTime(1000);
            } catch (Exception e) {
                printError(payamountlabel_paymentdetailsTab,
                        "Click_PayAmountHeader_PaymentDetails",
                        "Unexpected error while clicking Pay Amount header: " + e.getMessage(),
                        e);
                break;
            }

            attempts++;
        }

        throw new RuntimeException("Failed to click Pay Amount header after " + maxRetries + " attempts.");
    }


    /**
     * verify the pay amount header - ascending sort icon on the payment details tab
     *
     * @return If the pay amount header is sorted in ascending order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_PayAmountHeader_Sorted_ascending_PaymentDetailsTab() {
        // Store the invoice numbers in a list
        List<String> originalInvoiceNumbers = new ArrayList<>();
        for (WebElement invoiceElement : ListOfPayAmount_PaymentDetailsTab) {
            originalInvoiceNumbers.add(invoiceElement.getText());
        }
        delayWithGivenTime(2000);

        List<String> sortedInvoiceNumbers = new ArrayList<>(originalInvoiceNumbers);
        Collections.sort(sortedInvoiceNumbers);

        if (originalInvoiceNumbers.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Original invoice numbers: " + originalInvoiceNumbers);
            System.out.println("Sorted invoice numbers: " + sortedInvoiceNumbers);
            System.out.println("Invoices are not sorted in ascending order.");
            return false;
        }
    }

    /**
     * verify the pay amount header - descending sort icon on the payment details tab
     *
     * @return If the pay amount header is sorted in descending order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_PayAmountHeader_Sorted_descending_PaymentDetailsTab() {
        // Store the invoice numbers in a list
        List<String> originalInvoiceNumbers = new ArrayList<>();
        for (WebElement invoiceElement : ListOfPayAmount_PaymentDetailsTab) {
            originalInvoiceNumbers.add(invoiceElement.getText());
        }
        delayWithGivenTime(2000);
        // Create a copy of the list and sort it
        List<String> sortedInvoiceNumbers = new ArrayList<>(originalInvoiceNumbers);
        Collections.sort(sortedInvoiceNumbers, Collections.reverseOrder());

        if (originalInvoiceNumbers.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Invoices are not sorted in descending order.");
            return false;
        }
    }

    /**
     * verify the pay amount header default order on the payment details tab
     *
     * @return If the pay amount header is sorted in default order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_PayAmountHeader_DefaultOrder_PaymentDetailsTab() {
        delayWithGivenTime(1000);
        List<String> originalInvoiceNumbers = new ArrayList<>();
        for (WebElement invoiceElement : ListOfPayAmount_PaymentDetailsTab) {
            originalInvoiceNumbers.add(invoiceElement.getText());
        }
        delayWithGivenTime(2000);
        List<String> sortedInvoiceNumbers = new ArrayList<>(originalInvoiceNumbers);
        Collections.sort(sortedInvoiceNumbers);

        if (originalInvoiceNumbers.containsAll(sortedInvoiceNumbers)) {
            System.out.println("Original invoice numbers: " + originalInvoiceNumbers);
            System.out.println("Sorted invoice numbers: " + sortedInvoiceNumbers);
            return true;
        } else {
            System.out.println(" Else condition: Original invoice numbers: " + originalInvoiceNumbers);
            System.out.println(" Else condition: Sorted invoice numbers: " + sortedInvoiceNumbers);
            return false;
        }
    }

    /**
     * Clicks the pay date label table grid on the payment details tab
     *
     * @Author Balaji N
     */
    /**
     * Clicks the Pay Date label/header in the table grid on the Payment Details tab,
     * with retry logic to handle delayed loading or DOM refresh.
     *
     * @Author Balaji N
     */
    public void Click_PayDateHeader_PaymentDetails() {
        int maxRetries = 3;
        int attempts = 0;

        while (attempts < maxRetries) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                // Wait for complete page load and jQuery/AJAX activity to finish
                wait.until(driver -> ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete"));
                wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return !!window.jQuery && jQuery.active === 0"));

                // Wait until the header is visible and clickable
                wait.until(ExpectedConditions.visibilityOf(payDatelabel_paymentdetailsTab));
                wait.until(ExpectedConditions.elementToBeClickable(payDatelabel_paymentdetailsTab));

                // Scroll to the element to ensure it's in view
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block:'center'});", payDatelabel_paymentdetailsTab);
                delayWithGivenTime(300);

                // Click using JavaScript
                js_Click(payDatelabel_paymentdetailsTab, "Pay Date label in table grid on payment details tab");
                return;

            } catch (StaleElementReferenceException | ElementNotInteractableException | TimeoutException e) {
                System.err.println("[Retry " + (attempts + 1) + "/" + maxRetries + "] Retrying click on Pay Date header due to: " + e.getClass().getSimpleName());
                delayWithGivenTime(1000); // wait before retry
            } catch (Exception e) {
                printError(payDatelabel_paymentdetailsTab,
                        "Click_PayDateHeader_PaymentDetails",
                        "Unexpected error while clicking Pay Date header: " + e.getMessage(), e);
                break;
            }

            attempts++;
        }

        throw new RuntimeException("Failed to click Pay Date header after " + maxRetries + " attempts.");
    }


    /**
     * verify the pay date header - ascending sort icon on the payment details tab
     *
     * @return If the pay date header is sorted in ascending order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_PayDateHeader_Sorted_ascending_PaymentDetailsTab() {
        // Store the invoice numbers in a list
        List<String> originalInvoiceNumbers = new ArrayList<>();
        for (WebElement invoiceElement : ListOfPayDate_PaymentDetailsTab) {
            originalInvoiceNumbers.add(invoiceElement.getText());
        }
        delayWithGivenTime(2000);
        // Create a copy of the list and sort it
        List<String> sortedInvoiceNumbers = new ArrayList<>(originalInvoiceNumbers);
        Collections.sort(sortedInvoiceNumbers);

        // Compare the original list with the sorted list
        if (originalInvoiceNumbers.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Original invoice numbers: " + originalInvoiceNumbers);
            System.out.println("Sorted invoice numbers: " + sortedInvoiceNumbers);
            System.out.println("Invoices are not sorted in ascending order.");
            return false;
        }
    }

    /**
     * verify the pay date header - descending sort icon on the payment details tab
     *
     * @return If the pay date header is sorted in descending order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_PayDateHeader_Sorted_descending_PaymentDetailsTab() {
        // Store the invoice numbers in a list
        List<String> originalInvoiceNumbers = new ArrayList<>();
        for (WebElement invoiceElement : ListOfPayDate_PaymentDetailsTab) {
            originalInvoiceNumbers.add(invoiceElement.getText());
        }
        delayWithGivenTime(2000);
        // Create a copy of the list and sort it
        List<String> sortedInvoiceNumbers = new ArrayList<>(originalInvoiceNumbers);
        Collections.sort(sortedInvoiceNumbers, Collections.reverseOrder());

        if (originalInvoiceNumbers.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Invoices are not sorted in descending order.");
            return false;
        }
    }

    /**
     * verify the pay date header default order on the payment details tab
     *
     * @return If the pay date header is sorted in default order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_PayDateHeader_DefaultOrder_PaymentDetailsTab() {
        delayWithGivenTime(1000);
        List<String> actualpaydate = new ArrayList<>();
        for (WebElement paydateElement : ListOfPayDate_PaymentDetailsTab) {
            actualpaydate.add(paydateElement.getText());
        }
        delayWithGivenTime(2000);
        List<String> defaultorder = new ArrayList<>(actualpaydate);

        if (actualpaydate.equals(defaultorder)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Clicks the MOP label table grid on the payment details tab
     *
     * @Author Balaji N
     */
    /**
     * Clicks the MOP label/header in the table grid on the Payment Details tab,
     * with retry logic to handle delayed loading or DOM refresh issues.
     *
     * @Author Balaji N
     */
    public void Click_MOPHeader_PaymentDetails() {
        int maxRetries = 3;
        int attempts = 0;

        while (attempts < maxRetries) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                // Wait for full DOM and AJAX/jQuery readiness
                wait.until(driver -> ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete"));
                wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return !!window.jQuery && jQuery.active === 0"));

                // Wait until MOP label is visible and clickable
                wait.until(ExpectedConditions.visibilityOf(moplabel_paymentdetailsTab));
                wait.until(ExpectedConditions.elementToBeClickable(moplabel_paymentdetailsTab));

                // Scroll to ensure visibility
                ((JavascriptExecutor) getDriver()).executeScript(
                        "arguments[0].scrollIntoView({block: 'center'});", moplabel_paymentdetailsTab);
                delayWithGivenTime(300);

                // JavaScript click for stability
                js_Click(moplabel_paymentdetailsTab, "MOP label payment details tab");
                return;

            } catch (StaleElementReferenceException | ElementNotInteractableException | TimeoutException e) {
                System.err.println("[Retry " + (attempts + 1) + "/" + maxRetries + "] Retrying click on MOP label due to: " + e.getClass().getSimpleName());
                delayWithGivenTime(1000);
            } catch (Exception e) {
                printError(moplabel_paymentdetailsTab,
                        "Click_MOPHeader_PaymentDetails",
                        "Unexpected error while clicking MOP label: " + e.getMessage(), e);
                break;
            }

            attempts++;
        }

        throw new RuntimeException("Failed to click MOP label after " + maxRetries + " attempts.");
    }


    /**
     * verify the MOP header - ascending sort icon on the payment details tab
     *
     * @return If the MOP header is sorted in ascending order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_MOPHeader_Sorted_ascending_PaymentDetailsTab() {
        // Store the invoice numbers in a list
        List<String> originalMOP = new ArrayList<>();
        for (WebElement mopElement : ListOfMOP_PaymentDetailsTab) {
            originalMOP.add(mopElement.getText());
        }
        delayWithGivenTime(2000);
        // Create a copy of the list and sort it
        List<String> sortedmop = new ArrayList<>(originalMOP);
        Collections.sort(sortedmop);

        // Compare the original list with the sorted list
        if (originalMOP.containsAll(sortedmop)) {
            return true;
        } else {
            System.out.println("Original MOP : " + originalMOP);
            System.out.println("Sorted MOP: " + sortedmop);
            System.out.println("MOP are not sorted in ascending order.");
            return false;
        }
    }

    /**
     * verify the MOP header - descending sort icon on the payment details tab
     *
     * @return If the MOP header is sorted in descending order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_MOPHeader_Sorted_descending_PaymentDetailsTab() {
        // Store the invoice numbers in a list
        List<String> originalMOP = new ArrayList<>();
        for (WebElement invoiceElement : ListOfMOP_PaymentDetailsTab) {
            originalMOP.add(invoiceElement.getText());
        }
        delayWithGivenTime(2000);
        // Create a copy of the list and sort it
        List<String> sortedInvoiceNumbers = new ArrayList<>(originalMOP);
        Collections.sort(sortedInvoiceNumbers, Collections.reverseOrder());

        if (originalMOP.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Invoices are not sorted in descending order.");
            return false;
        }
    }

    /**
     * verify the MOP header default order on the payment details tab
     *
     * @return If the MOP header is sorted in default order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_MOPHeader_DefaultOrder_PaymentDetailsTab() {
        delayWithGivenTime(1000);
        List<String> actualpaydate = new ArrayList<>();
        for (WebElement paydateElement : ListOfMOP_PaymentDetailsTab) {
            actualpaydate.add(paydateElement.getText());
        }
        delayWithGivenTime(2000);
        List<String> defaultorder = new ArrayList<>(actualpaydate);

        if (actualpaydate.equals(defaultorder)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Clicks the check no label table grid on the payment details tab
     *
     * @Author Balaji N
     */
    /**
     * Clicks the Check No label/header in the table grid on the Payment Details tab,
     * with retry logic to handle dynamic DOM and loading delays.
     *
     * @Author Balaji N
     */
    public void Click_CheckNoHeader_PaymentDetails() {
        int maxRetries = 3;
        int attempts = 0;

        while (attempts < maxRetries) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                // Ensure DOM and jQuery/AJAX are ready
                wait.until(driver -> ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete"));
                wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return !!window.jQuery && jQuery.active === 0"));

                // Wait for visibility and clickability
                wait.until(ExpectedConditions.visibilityOf(checknolabel_paymentdetailsTab));
                wait.until(ExpectedConditions.elementToBeClickable(checknolabel_paymentdetailsTab));

                // Scroll to view
                ((JavascriptExecutor) getDriver()).executeScript(
                        "arguments[0].scrollIntoView({block: 'center'});", checknolabel_paymentdetailsTab);
                delayWithGivenTime(300);

                // Perform JS click
                js_Click(checknolabel_paymentdetailsTab, "Check Number label in table grid on payment details tab");
                return;

            } catch (StaleElementReferenceException | ElementNotInteractableException | TimeoutException e) {
                System.err.println("[Retry " + (attempts + 1) + "/" + maxRetries + "] Retrying click on Check No label due to: " + e.getClass().getSimpleName());
                delayWithGivenTime(1000);
            } catch (Exception e) {
                printError(checknolabel_paymentdetailsTab,
                        "Click_CheckNoHeader_PaymentDetails",
                        "Unexpected error while clicking Check No label: " + e.getMessage(), e);
                break;
            }

            attempts++;
        }

        throw new RuntimeException("Failed to click Check No label after " + maxRetries + " attempts.");
    }


    /**
     * verify the check no header - ascending sort icon on the payment details tab
     *
     * @return If the check no header is sorted in ascending order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_CheckNoHeader_Sorted_ascending_PaymentDetailsTab() {
        // Store the invoice numbers in a list
        List<String> original = new ArrayList<>();
        for (WebElement checknoElement : ListOfCheckno_PaymentDetailsTab) {
            original.add(checknoElement.getText());
        }
        delayWithGivenTime(2000);
        // Create a copy of the list and sort it
        List<String> sortedmop = new ArrayList<>(original);
        Collections.sort(sortedmop);

        // Compare the original list with the sorted list
        if (original.containsAll(sortedmop)) {
            return true;
        } else {
            System.out.println("Original MOP : " + original);
            System.out.println("Sorted MOP: " + sortedmop);
            System.out.println("MOP are not sorted in ascending order.");
            return false;
        }
    }

    /**
     * verify the check no header - descending sort icon on the payment details tab
     *
     * @return If the check no header is sorted in descending order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_CheckNoHeader_Sorted_descending_PaymentDetailsTab() {
        // Store the invoice numbers in a list
        List<String> original = new ArrayList<>();
        for (WebElement checknoElement : ListOfCheckno_PaymentDetailsTab) {
            original.add(checknoElement.getText());
        }
        delayWithGivenTime(2000);
        // Create a copy of the list and sort it
        List<String> sortedInvoiceNumbers = new ArrayList<>(original);
        Collections.sort(sortedInvoiceNumbers, Collections.reverseOrder());

        if (original.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Invoices are not sorted in descending order.");
            return false;
        }
    }

    /**
     * verify the check no header default order on the payment details tab
     *
     * @return If the check no header is sorted in default order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_CheckNoHeader_DefaultOrder_PaymentDetailsTab() {
        delayWithGivenTime(1000);
        List<String> actual = new ArrayList<>();
        for (WebElement checknoElement : ListOfCheckno_PaymentDetailsTab) {
            actual.add(checknoElement.getText());
        }
        delayWithGivenTime(2000);
        List<String> defaultorder = new ArrayList<>(actual);

        if (actual.equals(defaultorder)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Clicks the CC no label table grid on the payment details tab
     *
     * @Author Balaji N
     */
    /**
     * Clicks the Credit Card Number (CC No) label/header in the table grid on the Payment Details tab,
     * with retry logic to handle delays or dynamic DOM changes.
     *
     * @Author Balaji N
     */
    public void Click_CCNoHeader_PaymentDetails() {
        int maxRetries = 3;
        int attempts = 0;

        while (attempts < maxRetries) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                // Wait for DOM and AJAX/jQuery readiness
                wait.until(driver -> ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete"));
                wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return !!window.jQuery && jQuery.active === 0"));

                // Wait for visibility and clickability
                wait.until(ExpectedConditions.visibilityOf(ccnumberlabel_paymentdetailsTab));
                wait.until(ExpectedConditions.elementToBeClickable(ccnumberlabel_paymentdetailsTab));

                // Scroll into view
                ((JavascriptExecutor) getDriver()).executeScript(
                        "arguments[0].scrollIntoView({block: 'center'});", ccnumberlabel_paymentdetailsTab);
                delayWithGivenTime(300);

                // JS click for stability
                js_Click(ccnumberlabel_paymentdetailsTab, "Credit Card Number label in table grid on payment details tab");
                return;

            } catch (StaleElementReferenceException | ElementNotInteractableException | TimeoutException e) {
                System.err.println("[Retry " + (attempts + 1) + "/" + maxRetries + "] Retrying click on CC No label due to: " + e.getClass().getSimpleName());
                delayWithGivenTime(1000);
            } catch (Exception e) {
                printError(ccnumberlabel_paymentdetailsTab,
                        "Click_CCNoHeader_PaymentDetails",
                        "Unexpected error while clicking CC No label: " + e.getMessage(),
                        e);
                break;
            }

            attempts++;
        }

        throw new RuntimeException("Failed to click CC No label after " + maxRetries + " attempts.");
    }


    /**
     * verify the CC no header - ascending sort icon on the payment details tab
     *
     * @return If the CC no header is sorted in ascending order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_CCNoHeader_Sorted_ascending_PaymentDetailsTab() {
        // Store the invoice numbers in a list
        List<String> original = new ArrayList<>();
        for (WebElement ccnumberElement : ListOfCCNumber_PaymentDetailsTab) {
            original.add(ccnumberElement.getText());
        }
        delayWithGivenTime(2000);
        // Create a copy of the list and sort it
        List<String> sorted = new ArrayList<>(original);
        Collections.sort(sorted);

        // Compare the original list with the sorted list
        if (original.containsAll(sorted)) {
            return true;
        } else {
            System.out.println("Original CCNumber : " + original);
            System.out.println("Sorted CCNumber: " + sorted);
            System.out.println("CC Number are not sorted in ascending order.");
            return false;
        }
    }

    /**
     * verify the CC no header - descending sort icon on the payment details tab
     *
     * @return If the CC no header is sorted in descending order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_CCNoHeader_Sorted_descending_PaymentDetailsTab() {
        // Store the invoice numbers in a list
        List<String> original = new ArrayList<>();
        for (WebElement ccnumberElement : ListOfCCNumber_PaymentDetailsTab) {
            original.add(ccnumberElement.getText());
        }
        delayWithGivenTime(2000);
        // Create a copy of the list and sort it
        List<String> sorted = new ArrayList<>(original);
        Collections.sort(sorted, Collections.reverseOrder());

        if (original.containsAll(sorted)) {
            return true;
        } else {
            System.out.println("CC Number are not sorted in descending order.");
            return false;
        }
    }

    /**
     * verify the CC no header default order on the payment details tab
     *
     * @return If the CC no header is sorted in default order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_CCNoHeader_DefaultOrder_PaymentDetailsTab() {
        delayWithGivenTime(1000);
        List<String> original = new ArrayList<>();
        for (WebElement ccnumberElement : ListOfCCNumber_PaymentDetailsTab) {
            original.add(ccnumberElement.getText());
        }
        delayWithGivenTime(2000);
        List<String> defaultorder = new ArrayList<>(original);

        if (original.equals(defaultorder)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Clicks the Order details tab on customer details popup
     *
     * @Author Balaji N
     */
    public void Click_OrderDetailsTab_CustomerDetailsPopup() {
        js_Click(OrderDetailsTab, "Order details Tab on customer details popup");
    }

    /**
     * verify the export to excel button on order details tab on customer details popup
     *
     * @return If the export to excel button is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_ExportToExcel_OrderDetailsTab_CustomerDetailsPopup() {
        return is_Element_Displayed(exporttoexcelbutton_orderdetailsTab, "Excel Icon on order details tab on customer details popup");
    }

    /**
     * Clicks the export to excel button on order details tab on customer details popup
     *
     * @Author Balaji N
     */
    public void Click_ExportToExcelButton_OrderDetailsTab_CustomerDetailsPopup() {
        js_Click(exporttoexcelbutton_orderdetailsTab, "Export to excel button on order details tab on customer details popup");
    }

    /**
     * verify the order details table grid on order details tab on customer details popup
     *
     * @return If the order details table grid is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_OrderDetailsTableGrid_IsAppears_OrderDetailsTab_CustomerDetailsPopup() {
        return isElementDisplayed(OrderDetailsTable, "Order details table grid on order details tab on customer details popup");
    }

    /**
     * Enters the invoice number on order details tab on customer details popup
     *
     * @param InvoiceNo If the invoice number is not found, it will be entered
     * @Author Balaji N
     */
    public void Enter_InvoiceNo_OrderDetailsTab_CustomerDetailsPopup(String invoiceNo) {
        int retries = 3;
        while (retries > 0) {
            try {
                wait_For_Page_To_Be_Stable(getDriver());

                // Re-locate the element each time to avoid stale reference
                WebElement invoiceInput = new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//thead[@role='rowgroup']//tr[2]//th//span//input)[1]")));

                delayWithGivenTime(500);
                invoiceInput.clear();
                delayWithGivenTime(500);

                ClickAndType(invoiceInput, invoiceNo,
                        "Invoice search textbox field on order details tab on customer details popup");
                delayWithGivenTime(1200);

                invoiceInput.sendKeys(Keys.ENTER);
                delayWithGivenTime(1000);
                wait_For_Page_To_Be_Stable(getDriver());

                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException encountered. Retrying... Attempt " + (4 - retries));
                retries--;
            } catch (Exception e) {
                throw new RuntimeException("Failed to enter invoice number: " + e.getMessage(), e);
            }
        }
    }


    /**
     * It retrieves the invoice number on order details tab on customer details popup
     *
     * @return If the invoice number is entered, returns invoice number; otherwise, returns null
     * @Author Balaji N
     */
    public String get_InvoiceNo_Orderdetailstab() {
        return getElementAttribute(InvoiceNoSearchBox_OrderDetailsTab, "Invoice search textbox field value on order details tab on customer details popup");
    }

    public void clear_EnteredInvoiceNo_Orderdetailstab() { //Clearing the entered Invoice
        click(clear_button_InvoiceNoSearchBox_OrderDetailsTab, "Clear button on Invoice search textbox field on order details tab on customer details popup");
    }

    /**
     * verifies the invoice number on order details tab on customer details popup is displayed or not
     *
     * @param threedigitinvoicenumber If the invoice number is not found, it will be entered
     * @return If the invoice number is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_InvoiceNo_Orderdetailstab(String threedigitinvoicenumber) {
        try {
            boolean isValid = false;
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@role='grid']//tbody//tr//td[contains(@class,' gvCustomerOrderDtls')]")));
            for (WebElement suggestion : ListOfInvoiceNo_OrderDetailsTab) {
                if (suggestion.getText().contains(threedigitinvoicenumber)) {
                    Highlight_Element(suggestion, "Invoice Number on order details tab on customer details popup");
                    isValid = true;
                }
            }
            return isValid;
        } catch (Exception e) {
            throw new RuntimeException("Invoice number is not displayed on order details tab on customer details popup" + e);
        }
    }

    /**
     * verifies the invoice number on order details tab on customer details popup is displayed or not
     *
     * @return If the invoice number is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_AllTheInvoiceNo_AppearsOn_OrderDetailsTab() {
        int InvoiceNo_count = ListOfInvoiceNo_OrderDetailsTab.size();
        if (InvoiceNo_count == 25) {
            return true;
        } else {
            System.out.println("The count of invoices is not 25. It is: " + InvoiceNo_count);
            return false;
        }
    }

    /**
     * Clicks the clear button on invoice no search box on order details tab
     *
     * @Author Balaji N
     */
    public void Click_clearbutton_InvoiceNoSearchBox_OrderDetailsTab() {
        js_Click(clearbutton_InvoiceNoSearchBox_OrderDetailsTab, "Clear button on invoice no search box on order details tab");
    }

    /**
     * Verify the clear button on invoice no search box on order details tab
     *
     * @return If the clear button is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_Clearbutton_InvoiceNoSearchBox_OrderDetailsTab() {
        return is_Element_Displayed(clearbutton_InvoiceNoSearchBox_OrderDetailsTab, "Clear button on invoice no search box on order details tab");
    }

    /**
     * Retrieves the first row invoice number on order details tab
     *
     * @return If the invoice number is found, it returns the invoice number; otherwise, it returns null
     * @Author Balaji N
     */
    public String getFirstRowInvoiceNo_OrderDetailsTab() {
        return getElementText(FirstInvoiceNo_OrderDetailsTab, "First Invoice Number in order details tab table grid on customer details popup");
    }

    /**
     * Verify the clear button on invoice no search box field on order details tab
     *
     * @return If the clear button is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean get_ClearBtnAppears_OnInvoiceSearchBox_OrderDetailsTab() {
        return is_Element_Displayed(clearbutton_InvoiceNoSearchBox_OrderDetailsTab, "Clear button on invoice no search box field on order details tab");
    }

    /**
     * Clicks the clear button on invoice no search box field on order details tab
     *
     * @Author Balaji N
     */
    public void Click_ClearBtn_InvoiceSearchBox_OrderDetailsTab() {
        Click(clearbutton_InvoiceNoSearchBox_OrderDetailsTab, "Clear button on invoice no search box field on order details tab");
    }

    /**
     * Verifies the no customer orders found message appears on order details tab
     *
     * @return If the no customer orders found message is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
//    public boolean Verify_NoCustomerOrdersFoundMessage_Appears_OrderDetailsTab() {
//        try {
//            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
//            wait.until(ExpectedConditions.visibilityOf(NoCustomerOrderFoundMessage_OrderDetailsTab));
//            return isElementDisplayed(NoCustomerOrderFoundMessage_OrderDetailsTab,
//                    "No customer orders found label on order details tab");
//        } catch (TimeoutException e) {
//            throw new RuntimeException("Expected message 'No customer orders found' did not appear within 20 seconds. Please verify if the order data exists.");
//        } catch (NoSuchElementException | NullPointerException e) {
//            throw new RuntimeException("Could not find the 'No customer orders found' message element on the Order Details tab. Please check if the UI has loaded properly or if the locator is still valid.");
//        } catch (Exception e) {
//            throw new RuntimeException("An unexpected error occurred while checking for the 'No customer orders found' message: " + e.getMessage());
//        }
//    }
    public boolean Verify_NoCustomerOrdersFoundMessage_Appears_OrderDetailsTab() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        try {
            wait.until(ExpectedConditions.visibilityOf(NoCustomerOrderFoundMessage_OrderDetailsTab));
        } catch (TimeoutException e) {
            throw new AssertionError("Expected message 'No customer orders found' did not appear within 30 seconds. Please verify if the order data exists.");
        } catch (NoSuchElementException | NullPointerException e) {
            throw new AssertionError("Could not find the 'No customer orders found' message element. Please check if UI loaded or locator is valid.");
        }

        boolean displayed = isElementDisplayed(NoCustomerOrderFoundMessage_OrderDetailsTab,
                "No customer orders found label on order details tab");
        if (!displayed) {
            throw new AssertionError("'No customer orders found' message element was not visible after waiting. Check UI or locator.");
        }
        return true;
    }


    /**
     * Enters the total amount on order details tab on customer details popup
     *
     * @param TotalAmount the total amount to be entered
     * @Author Balaji N
     */
    public void Enter_TotalAmount_OrderDetailsTab_CustomerDetailsPopup(String totalAmount) {
        By totalAmountBoxLocator = By.xpath("//span[@data-field='TOTALAMOUNT']//input");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        for (int attempt = 0; attempt < 2; attempt++) {
            try {
                WebElement totalAmountBox = wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOfElementLocated(totalAmountBoxLocator)
                ));

                fluentWait(totalAmountBox, 20, 2); // Stabilize element
                HighlightElement(totalAmountBox);
                delayWithGivenTime(1000);

                totalAmountBox.clear();
                delayWithGivenTime(500);

                ClickAndType(totalAmountBox, totalAmount,
                        "Total Amount search textbox field on order details tab on customer details popup");

                delayWithGivenTime(1000);

                // ✅ Confirm input is correctly typed
                String enteredValue = totalAmountBox.getAttribute("value");
                if (enteredValue == null || !enteredValue.contains(totalAmount)) {
                    System.out.println("Total amount not properly entered, retrying...");
                    continue; // Retry loop
                }

                totalAmountBox.sendKeys(Keys.ENTER);
                delayWithGivenTime(2000);
                break; // ✅ Exit on success

            } catch (StaleElementReferenceException sere) {
                System.out.println("StaleElementReferenceException caught — retrying...");
            } catch (Exception e) {
                System.out.println("Exception while entering total amount: " + e.getMessage());
                break;
            }
        }
    }


    /**
     * Retrieves the total amount value on order details tab
     *
     * @return If the total amount is entered, it returns the total amount; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_TotalAmount_Orderdetailstab() {
        return getElementAttribute(TotalAmountSearchBox_OrderDetailsTab, "Total amount search textbox field value on order details tab on customer details popup");
    }

    public String get_TotalAmount_Order_Details_Tab() {
        int attempts = 0;
        while (attempts < 3) {
            try {
                return getElementText(FirstRow_TotalAmount_OrderDetailsTab,
                        "Total amount search textbox field value on order details tab on customer details popup");
            } catch (StaleElementReferenceException e) {
                System.out.println("Caught StaleElementReferenceException. Retrying... Attempt: " + (attempts + 1));
                FirstRow_TotalAmount_OrderDetailsTab = getDriver().findElement(By.xpath("//div[@id='gvCustomerOrderDtls']//table//tbody//tr[1]//td[5]"));
                attempts++;
            }
        }
        throw new RuntimeException("Failed to retrieve Total Amount due to repeated stale element exception.");
    }


    /**
     * verifies the total amount on order details tab is displayed or not
     *
     * @param threedigitinvoicenumber
     * @return If the total amount is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_TotalAmount_Orderdetailstab(String threedigitinvoicenumber) {
        boolean isValid = false;
        By totalAmountListLocator = By.xpath("//div[@id='gvCustomerOrderDtls']//table[@role='grid']//tbody//tr//td[5]");
        int maxRetries = 2;

        for (int attempt = 0; attempt < maxRetries; attempt++) {
            try {
                List<WebElement> totalAmountList = getDriver().findElements(totalAmountListLocator);

                for (WebElement suggestion : totalAmountList) {
                    HighlightElement(suggestion);
                    if (suggestion.getText().contains(threedigitinvoicenumber)) {
                        isValid = true;
                        break;
                    }
                }
                break; // ✅ Exit loop if successful

            } catch (StaleElementReferenceException sere) {
                System.out.println("StaleElementReferenceException on attempt " + (attempt + 1) + ", retrying...");
                delayWithGivenTime(1000); // Optional delay before retry
            } catch (Exception e) {
                printError(null, "TotalAmountList_OrderDetailsTab", "Exception in Verify_TotalAmount_Orderdetailstab", e);
                break;
            }
        }

        return isValid;
    }


    /**
     * Clicks the clear button on total amount search box on order details tab
     *
     * @Author Balaji N
     */
    public void Click_clearbutton_TotalAmountSearchBox_OrderDetailsTab() {
        js_Click(clearbutton_TotalAmountSearchBox_OrderDetailsTab, "Clear button on total amount search box on order details tab");
    }

    /**
     * Verify the clear button on total amount search box on order details tab
     *
     * @return If the clear button is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_Clearbutton_TotalAmountSearchBox_OrderDetailsTab() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(clearbutton_TotalAmountSearchBox_OrderDetailsTab));
        return isElementDisplayed(clearbutton_TotalAmountSearchBox_OrderDetailsTab, "Clear button on total amount search box on order details tab");
    }

    /**
     * verifies the total amount on order details tab is displayed or not
     *
     * @return If the total amount is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_AllTheTotalAmounts_AppearsOn_OrderDetailsTab() {
        int InvoiceNo_count = TotalAmountList_OrderDetailsTab.size();
        if (InvoiceNo_count == 25) {
            return true;
        } else {
            System.out.println("The count of total amount is not 25. It is: " + InvoiceNo_count);
            return false;
        }
    }

    /**
     * Enters the status on order details tab on customer details popup
     *
     * @param status If the status is not found, it will be entered
     * @Author Balaji N
     */
    public void Enter_Status_OrderDetailsTab_CustomerDetailsPopup(String status) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                StatusSearchBox_OrderDetailsTab.clear();
                delayWithGivenTime(500);
                ClickAndType(StatusSearchBox_OrderDetailsTab, status,
                        "Status search textbox field on order details tab on customer details popup");
                delayWithGivenTime(1000);
                StatusSearchBox_OrderDetailsTab.sendKeys(Keys.ENTER);
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Caught StaleElementReferenceException. Retrying... Attempt: " + (attempts + 1));
                StatusSearchBox_OrderDetailsTab = getDriver().findElement(By.xpath("//div[@id='gvCustomerOrderDtls']//table//tr[2]//th[2]//input"));
                attempts++;
            }
        }
    }


    /**
     * Retrieves the status value on order details tab on customer details popup
     *
     * @return If the status is entered, it returns the status; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Status_Orderdetailstab() {
        return getElementAttribute(StatusSearchBox_OrderDetailsTab, "Status search textbox field value on order details tab on customer details popup");
    }

    /**
     * verifies the status on order details tab is displayed or not
     *
     * @param threeCharacterstatus
     * @return If the status is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
/*    public boolean Verify_Status_Orderdetailstab(String threeCharacterstatus) {
        boolean isValid = false;
        for (WebElement suggestion : ListOfStatus_OrderDetailsTab) {
            delayWithGivenTime(500);
            if (suggestion.getText().contains(threeCharacterstatus)) {
                HighlightElement(suggestion);
                isValid = true;
            }
        }
        return isValid;
    }*/
    public boolean Verify_Status_Orderdetailstab(String expectedStatus) {
        boolean isValid = false;

        // Step 1: Check if the list has elements
        if (ListOfStatus_OrderDetailsTab == null || ListOfStatus_OrderDetailsTab.isEmpty()) {
            String msg = String.format("❌ No customer orders found for status: '%s'. The status list is empty.", expectedStatus);
            System.err.println(msg);
            Allure.step(msg);
            return false;
        }

        // Step 2: Loop through the list and validate
        for (int i = 0; i < ListOfStatus_OrderDetailsTab.size(); i++) {
            try {
                delayWithGivenTime(500);
                WebElement status = ListOfStatus_OrderDetailsTab.get(i); // fresh reference
                String statusText = status.getText().trim();

                if (statusText.contains(expectedStatus)) {
                    HighlightElement(status);
                    String passMsg = String.format("✅ Status match found: '%s' contains expected value '%s' at index %d.", statusText, expectedStatus, i);
                    isValid = true;
                    break;
                }

            } catch (StaleElementReferenceException e) {
                // Retry once after catching stale exception
                try {
                    WebElement refreshedStatus = ListOfStatus_OrderDetailsTab.get(i);
                    String refreshedText = refreshedStatus.getText().trim();

                    if (refreshedText.contains(expectedStatus)) {
                        HighlightElement(refreshedStatus);
                        String retryMsg = String.format("✅ Status match (after retry) at index %d: '%s' contains '%s'.", i, refreshedText, expectedStatus);
                        System.out.println(retryMsg);
                        Allure.step(retryMsg);
                        isValid = true;
                        break;
                    }

                } catch (StaleElementReferenceException ex) {
                    String staleMsg = String.format("⚠️ Element became stale again at index %d during retry.", i);
                    System.err.println(staleMsg);
                    Allure.step(staleMsg);
                }
            } catch (IndexOutOfBoundsException io) {
                String failMsg = String.format("❌ Index %d is out of bounds for the status list. Possible DOM update issue.", i);
                System.err.println(failMsg);
                Allure.step(failMsg);
            } catch (Exception e) {
                String unknown = String.format("❌ Unexpected error at index %d: %s", i, e.getMessage());
                System.err.println(unknown);
                Allure.step(unknown);
            }
        }

        // Step 3: Final Result if no match
        if (!isValid) {
            String noMatch = String.format("❌ No matching status found in order details tab for: '%s'.", expectedStatus);
            System.err.println(noMatch);
            Allure.step(noMatch);
        }
        return isValid;
    }


    /**
     * Clicks the clear button on status search box on order details tab
     *
     * @Author Balaji N
     */
    public void Click_clearbutton_StatusSearchBox_OrderDetailsTab() {
        js_Click(clearbutton_StatusSearchBox_OrderDetailsTab, "Clear button on status search box on order details tab");
    }

    /**
     * verify the clear button on status search box field on order details tab
     *
     * @return If the clear button is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_Clearbutton_StatusSearchBox_OrderDetailsTab() {
        return is_Element_Displayed(clearbutton_StatusSearchBox_OrderDetailsTab, "Clear button on status search box field on order details tab");
    }

    /**
     * verify all the status on order details tab is displayed or not
     *
     * @return If the status is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_AllTheStatus_AppearsOn_OrderDetailsTab() {
        int InvoiceNo_count = ListOfStatus_OrderDetailsTab.size();
        if (InvoiceNo_count == 25) {
            return true;
        } else {
            System.out.println("The count of total amount is not 25. It is: " + InvoiceNo_count);
            return false;
        }
    }

    /**
     * Enters the name search textbox field on order details tab on customer details popup
     *
     * @param name If the name search textbox field is not found, it will be entered
     * @Author Balaji N
     */
    public void Enter_Name_OrderDetailsTab_CustomerDetailsPopup(String name) {
        int retries = 3;
        while (retries > 0) {
            try {
                By nameinput = By.xpath("//span[@data-field='RECIPIENTNAME']//input");
                wait_For_Page_To_Be_Stable(getDriver());
                // Re-fetch the element using an explicit wait
                WebElement nameInput = new WebDriverWait(getDriver(), Duration.ofSeconds(50))
                        .until(ExpectedConditions.visibilityOfElementLocated(nameinput));

                delayWithGivenTime(500);
                nameInput.clear();
                delayWithGivenTime(500);
                ClickAndType(nameInput, name,
                        "Name search textbox field on order details tab on customer details popup");
                delayWithGivenTime(500);
                nameInput.sendKeys(Keys.ENTER);
                delayWithGivenTime(1000);
                wait_For_Page_To_Be_Stable(getDriver());
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException on Name Search Box. Retrying... Attempt " + (4 - retries));
                retries--;
            } catch (TimeoutException e) {
                throw new RuntimeException("Timed out waiting for Name Search Box to appear: " + e.getMessage(), e);
            } catch (Exception e) {
                throw new RuntimeException("Unexpected error while entering name in Order Details tab: " + e.getMessage(), e);
            }
        }
    }


    /**
     * Retrieves the name value on order details tab on customer details popup
     *
     * @return If the name is entered, it returns the name; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Name_Orderdetailstab() {
        return getElementAttribute(NameSearchBox_OrderDetailsTab, "Name search textbox field value on order details tab on customer details popup");
    }

    /**
     * Verify the name search textbox field order details tab is displayed or not
     *
     * @param threeCharactername
     * @return If the name search textbox field order details tab autosuggestion is displayed it returns true else false
     * @Author Balaji N
     */
    public boolean Verify_Name_Orderdetailstab(String threeCharactername) {
        boolean isValid = false;
        for (WebElement suggestion : ListOfName_OrderDetailsTab) {
            HighlightElement(suggestion);
            if (suggestion.getText().contains(threeCharactername)) {
                isValid = true;
            }
        }
        return isValid;
    }

    /**
     * Clicks the clear icon on name search textbox field on order details tab - customer details popup
     *
     * @Author Balaji N
     */
    public void Click_clearbutton_NameSearchBox_OrderDetailsTab() {
        js_Click(clearbutton_NameSearchBox_OrderDetailsTab, "Clear icon on name search textbox field on order details tab");
    }

    /**
     * Verify the clear icon on name search textbox field on order details tab - customer details popup
     *
     * @return If the clear icon is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_Clearbutton_NameSearchBox_OrderDetailsTab() {
        return is_Element_Displayed(clearbutton_NameSearchBox_OrderDetailsTab, "Clear icon on name search textbox field");
    }

    public boolean Verify_AllTheName_AppearsOn_OrderDetailsTab() {
        int InvoiceNo_count = ListOfName_OrderDetailsTab.size();
        if (InvoiceNo_count == 25) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Enters the address search textbox field on order details tab
     *
     * @param address the address to be entered
     * @Author Balaji N
     */
    public void Enter_Address_OrderDetailsTab_CustomerDetailsPopup(String address) {
        By addressBoxLocator = By.xpath("(//thead[@role='rowgroup']//tr[2]//th//span//input)[4]");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        for (int attempt = 0; attempt < 2; attempt++) {
            try {
                WebElement addressBox = wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOfElementLocated(addressBoxLocator)
                ));

                fluentWait(addressBox, 20, 2);
                HighlightElement(addressBox);
                delayWithGivenTime(1000);

                // Clear and enter description
                addressBox.clear();
                delayWithGivenTime(500);
                addressBox.sendKeys(address);
                delayWithGivenTime(1000);

                // Optional: confirm the value is actually entered
                String enteredValue = addressBox.getAttribute("value");

                if (enteredValue == null || !enteredValue.contains(address)) {
                    System.out.println("Description not properly entered, retrying...");
                    continue; // retry
                }

                // Press Enter only after confirming input
                addressBox.sendKeys(Keys.ENTER);
                delayWithGivenTime(2000);
                break; // Exit loop if success

            } catch (StaleElementReferenceException sere) {
                System.out.println("StaleElementReferenceException - retrying...");
            } catch (Exception e) {
                System.out.println("Exception while entering description: " + e.getMessage());
                break;
            }
        }
    }

    /**
     * Retrieves the address value on order details tab
     *
     * @return If the address is entered, it returns the address; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Address_Orderdetailstab() {
        return getElementAttribute(AddressSearchBox_OrderDetailsTab, "Address search textbox field value on order details tab");
    }

    /**
     * verifies the address on order details tab is displayed or not
     *
     * @param threeCharacteraddress the address to be verified
     * @return If the address is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_Address_Orderdetailstab(String partialAddressText) {
        wait_For_Page_To_Be_Stable(getDriver());
        boolean isValid = false;

        if (ListOfAddress_OrderDetailsTab == null || ListOfAddress_OrderDetailsTab.isEmpty()) {
            String msg = String.format("❌ No address entries found on Order Details tab. Could not search for: '%s'", partialAddressText);
            System.err.println(msg);
            Allure.step(msg);
            return false;
        }

        for (int i = 0; i < ListOfAddress_OrderDetailsTab.size(); i++) {
            try {
                WebElement addressElement = ListOfAddress_OrderDetailsTab.get(i);

                explicitWait(addressElement);
                HighlightElement(addressElement);

                String fullAddress = addressElement.getText().trim();

                if (fullAddress.contains(partialAddressText)) {
                    String passMsg = String.format("✅ Match found: Address '%s' contains searched text '%s' at index %d.",
                            fullAddress, partialAddressText, i);
                    isValid = true;
                    break;
                }

            } catch (StaleElementReferenceException sere) {
                String retryMsg = String.format("⚠️ Address at index %d became stale. Retrying...", i);
                System.err.println(retryMsg);
                Allure.step(retryMsg);
                i--; // retry the same index
                delayWithGivenTime(500);

            } catch (Exception e) {
                String failMsg = String.format("❌ Unexpected error while verifying address at index %d: %s", i, e.getMessage());
                System.err.println(failMsg);
                Allure.step(failMsg);
            }
        }

        if (!isValid) {
            String notFound = String.format("❌ No address entry matched the searched text: '%s'.", partialAddressText);
            System.err.println(notFound);
            Allure.step(notFound);
        }

        return isValid;
    }


    /**
     * Clicks the clear button on address search box on order details tab
     *
     * @Author Balaji N
     */
    public void Click_clearbutton_AddressSearchBox_OrderDetailsTab() {
        js_Click(clearbutton_AddressSearchBox_OrderDetailsTab, "Clear button on address search box on order details tab");
    }

    /**
     * Verify the clear button on address search box field on order details tab
     *
     * @return If the clear button is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_Clearbutton_AddressSearchBox_OrderDetailsTab() {
        return is_Element_Displayed(clearbutton_AddressSearchBox_OrderDetailsTab, "Clear button on address search box field on order details tab");
    }

    /**
     * verify all the address on order details tab is displayed or not
     *
     * @return If the address is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_AllTheAddress_AppearsOn_OrderDetailsTab() {
        int InvoiceNo_count = ListOfAddress_OrderDetailsTab.size();
        if (InvoiceNo_count == 25) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Enters the order date in the search textbox field on order details tab
     *
     * @param orderdate the order date to be entered
     * @Author Balaji N
     */
    public void Enter_OrderDate_OrderDetailsTab_CustomerDetailsPopup(String orderdate) {
        By orderDateBoxLocator = By.xpath("//span[@data-field='ORDER_ENTRY_DATETIMENEW']//input[@class='k-textbox k-FullWidth']"); // Replace appropriately

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        for (int attempt = 0; attempt < 2; attempt++) {
            try {
                WebElement orderDateBox = wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOfElementLocated(orderDateBoxLocator)
                ));

                fluentWait(orderDateBox, 20, 2);
                HighlightElement(orderDateBox);
                delayWithGivenTime(500);

                orderDateBox.clear();
                delayWithGivenTime(500);

                orderDateBox.click();
                ClickAndType(orderDateBox, orderdate, "Order date search textbox field on order details tab");
                delayWithGivenTime(500);

                // ✅ Validate input
                String enteredValue = orderDateBox.getAttribute("value");
                if (enteredValue == null || !enteredValue.contains(orderdate)) {
                    System.out.println("Order date not entered correctly, retrying...");
                    continue; // Retry
                }

                orderDateBox.sendKeys(Keys.ENTER);
                delayWithGivenTime(1000);
                break; // ✅ Exit on success

            } catch (StaleElementReferenceException sere) {
                System.out.println("StaleElementReferenceException caught — retrying...");
            } catch (Exception e) {
                System.out.println("Exception while entering order date: " + e.getMessage());
                break;
            }
        }
    }


    /**
     * Retrieves the order date value on order details tab
     *
     * @return If the order date is entered, it returns the order date; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_OrderDate_Orderdetailstab() {
        return getElementAttribute(OrderDateSearchBox_OrderDetailsTab, "Order date search textbox field value on order details tab");
    }

    /**
     * verifies the order date on order details tab is displayed or not
     *
     * @param threeCharacterorderdate the order date to be verified
     * @return If the order date is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_OrderDate_Orderdetailstab(String threeCharacterorderdate) {
        boolean isValid = false;

        for (int i = 0; i < ListOfOrderDate_OrderDetailsTab.size(); i++) {
            for (int attempt = 0; attempt < 2; attempt++) {
                try {
                    WebElement suggestion = ListOfOrderDate_OrderDetailsTab.get(i);

                    explicitWait(suggestion);
                    HighlightElement(suggestion);

                    if (suggestion.getText().contains(threeCharacterorderdate)) {
                        isValid = true;
                    }
                    break; // ✅ success — no need to retry
                } catch (StaleElementReferenceException sere) {
                    System.out.println("StaleElementReferenceException caught for index " + i + " — retrying...");
                    // No break; retry this index
                } catch (Exception e) {
                    System.out.println("Unexpected error at index " + i + ": " + e.getMessage());
                    break; // Fail-fast on unknown exception
                }
            }
        }

        return isValid;
    }

    /**
     * Clear button on order date search box on order details tab
     *
     * @Author Balaji N
     */
    public void Click_clearbutton_OrderDateSearchBox_OrderDetailsTab() {
        js_Click(clearbutton_OrderDateSearchBox_OrderDetailsTab, "Clear button on order date search box on order details tab");
    }

    /**
     * Verify the clear button on order date search box on order details tab
     *
     * @return If the clear button is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_Clearbutton_OrderDateSearchBox_OrderDetailsTab() {
        return is_Element_Displayed(clearbutton_OrderDateSearchBox_OrderDetailsTab, "Clear button on order date search box on order details tab");
    }

    /**
     * verify all the order date on order details tab is displayed or not
     *
     * @return If the order date is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_AllTheOrderDate_AppearsOn_OrderDetailsTab() {
        int InvoiceNo_count = ListOfOrderDate_OrderDetailsTab.size();
        if (InvoiceNo_count == 25) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Enters the description search textbox field on order details tab
     *
     * @param description If the description is entered, it returns the description; otherwise, it returns null
     * @return If the description is entered, it returns the description; otherwise, it returns null
     * @Author Balaji N
     */
    public void Enter_Description_OrderDetailsTab_CustomerDetailsPopup(String description) {
        By descriptionBoxLocator = By.xpath("(//thead[@role='rowgroup']//tr[2]//th//span//input)[7]");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        for (int attempt = 0; attempt < 2; attempt++) {
            try {
                WebElement descriptionBox = wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOfElementLocated(descriptionBoxLocator)
                ));

                fluentWait(descriptionBox, 20, 2);
                HighlightElement(descriptionBox);
                delayWithGivenTime(1000);

                // Clear and enter description
                descriptionBox.clear();
                delayWithGivenTime(500);
                descriptionBox.sendKeys(description);
                delayWithGivenTime(1000);

                // Optional: confirm the value is actually entered
                String enteredValue = descriptionBox.getAttribute("value");

                if (enteredValue == null || !enteredValue.contains(description)) {
                    System.out.println("Description not properly entered, retrying...");
                    continue; // retry
                }

                // Press Enter only after confirming input
                descriptionBox.sendKeys(Keys.ENTER);
                delayWithGivenTime(2000);
                break; // Exit loop if success

            } catch (StaleElementReferenceException sere) {
                System.out.println("StaleElementReferenceException - retrying...");
            } catch (Exception e) {
                System.out.println("Exception while entering description: " + e.getMessage());
                break;
            }
        }
    }


    /**
     * Retrieves the description search textbox field value on order details tab
     *
     * @return If the description is entered, it returns the description; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Description_Orderdetailstab() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(DescriptionSearchBox_OrderDetailsTab));
        return getElementAttribute(DescriptionSearchBox_OrderDetailsTab, "Description search textbox field value on order details tab");
    }

    /**
     * verifies the description on order details tab is displayed or not
     *
     * @param threeCharacterdescription
     * @return If the description is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_Description_Orderdetailstab(String threeCharacterdescription) {
        boolean isValid = false;
        for (int i = 0; i < ListOfDescription_OrderDetailsTab.size(); i++) {
            try {
                WebElement description = ListOfDescription_OrderDetailsTab.get(i);
                HighlightElement(description);
                if (description.getText().contains(threeCharacterdescription)) {
                    isValid = true;
                }

            } catch (StaleElementReferenceException sere) {
                try {
                    ListOfDescription_OrderDetailsTab = getDriver().findElements(By.xpath("//div[@id='gvCustomerOrderDtls']//table[@role='grid']//tbody//tr//td[7]"));
                    WebElement description = ListOfDescription_OrderDetailsTab.get(i);
                    if (description.getText().contains(threeCharacterdescription)) {
                        isValid = true;
                    }

                } catch (Exception retryException) {
                    System.out.println("Retry failed for index " + i + ": " + retryException.getMessage());
                }

            } catch (Exception e) {
                System.out.println("Exception while verifying description at index " + i + ": " + e.getMessage());
            }
        }

        return isValid;
    }


    /**
     * Clear button on Description search textbox field on order details tab
     *
     * @Author Balaji N
     */
    public void Click_clearbutton_DescriptionSearchBox_OrderDetailsTab() {
        js_Click(clearbutton_DescriptionSearchBox_OrderDetailsTab, "Clear button on Description search textbox field on order details tab");
    }

    /**
     * Verify the clear button on Description search textbox field on order details tab
     *
     * @return If the clear button is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_Clearbutton_DescriptionSearchBox_OrderDetailsTab() {
        return is_Element_Displayed(clearbutton_DescriptionSearchBox_OrderDetailsTab, "Clear button on Description search textbox field on order details tab");
    }

    /**
     * verify all the description on order details tab is displayed or not
     *
     * @return If the description is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_AllTheDescription_AppearsOn_OrderDetailsTab() {
        int InvoiceNo_count = ListOfDescription_OrderDetailsTab.size();
        if (InvoiceNo_count == 25) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verify the no of pages dropdown on order details tab on customer details popup
     *
     * @return If the no of pages dropdown is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_NoOfpagesDropdown_OnOrderDetailsTab_OnCustomerDetailsPopup() {
        js_Click(pagelistdropdown_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage, "Pagination dropdown on Order details tab");
        delayWithGivenTime(2000);

        List<String> expectedPaginationValues = Arrays.asList("25", "50", "100", "150", "200", "250");
        Set<String> actualPaginationValues = new HashSet<>();

        for (WebElement dropdownValue : pagelist_dropdownvalues_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage) {
            HighlightElement(dropdownValue);
            actualPaginationValues.add(dropdownValue.getText());
        }

        boolean allOptionsDisplayed = actualPaginationValues.containsAll(expectedPaginationValues);

        if (!allOptionsDisplayed) {
            System.out.println("Missing pagination values in dropdown: " + expectedPaginationValues.stream().filter(value -> !actualPaginationValues.contains(value)).collect(Collectors.joining(", ")));
        }
        return allOptionsDisplayed;
    }

    public int Verify_ListofInvoiceNumbers_Appears_InOrderDetailsInvoiceTable_OnOrderDetailsTab() {
        int totalinvoicecount = ListOfInvoiceNo_OrderDetailsTab.size();
        return totalinvoicecount;
    }

    public boolean Verify_NoOfitemDisplayed_OnUnpaidTab_OnCustomerDetailsPopup() {
        String itemsCountText = pagenumber_Label_UnpaidTabOn_CustomerDetailsPopupOnPhoneOrderPage.getText(); // e.g., "1																												// items"
        int displayedItemsCount = Integer.parseInt(itemsCountText.split(" ")[4]);
        System.out.println("Number of items displayed on Order details Tab: " + displayedItemsCount);

        List<WebElement> tableRows = getDriver().findElements(By.xpath("//tbody[@role='rowgroup']//tr"));
        int actualRowsCount = tableRows.size();

        if (displayedItemsCount == actualRowsCount) {
            System.out.println("Actual number of items displayed is correct: " + actualRowsCount);
            return true;
        } else {
            System.out.println("Actual Number of items displayed on Order details Tab is incorrect: " + actualRowsCount);
            return false;
        }

    }

    /**
     * Clicks the Statements tab on customer details popup
     *
     * @Author Balaji N
     */
    public void Click_StatementsTab_CustomerDetailsPopup() {
        js_Click(StatementsTab, "Statements tab on customer details popup");
    }

    /**
     * Clicks and verifies the dropdown options on statements tab on customer details popup
     *
     * @return If the previous month is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean ClickAndVerifyDropDownOptions_StatementsTab() {
        Click(monthdropdown_statementsTab, "Month dropdown field on statements tab on customer details popup");
        delayWithGivenTime(2000);

        LocalDate currentDate = LocalDate.now();
        // Get the previous month
        LocalDate previousMonthDate = currentDate.minusMonths(1);
        int previousMonthValue = previousMonthDate.getMonthValue();

        // Convert the numeric month to its name using the helper function
        String previousMonth = getMonthName(previousMonthValue);

        Select s = new Select(monthdropdown_statementsTab);
        List<WebElement> dropdownOptions = s.getOptions();

        for (WebElement option : dropdownOptions) {
            if (option.getText().contains(previousMonth)) {
                HighlightElement(option);
                System.out.println("Previous month is displayed: " + previousMonth);
                return true;
            }
        }

        System.out.println("Previous month is not displayed: " + previousMonth);
        return false;
    }

    // Helper method to convert month number to month name
    private String getMonthName(int monthNumber) {
        switch (monthNumber) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                throw new IllegalArgumentException("Invalid month number: " + monthNumber);
        }
    }

    /**
     * Verify whether the month dropdown field is empty on statements tab on customer details popup
     *
     * @return If the month dropdown field is empty, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_Selectmonth_IsEmpty_StatementsTab() {
        HighlightElement(monthdropdown_statementsTab);
        Click(monthdropdown_statementsTab, "Month dropdown field on statements tab on customer details popup");
        s = new Select(monthdropdown_statementsTab);
        return s.getOptions().isEmpty();
    }

    /**
     * Select the month dropdown field on statements tab on customer details popup
     *
     * @param month the month to select
     * @Author Balaji N
     */
    public void SelectMonth_StatementsTab(String month) {
        drop_Down(monthdropdown_statementsTab, month, "VisibleText", "Month dropdown field on the statement tab on customer details popup");
    }

    /**
     * Retrieves the selected option on the month dropdown field on statements tab on customer details popup
     *
     * @return If the selected option is displayed, it returns value of the selected option; otherwise, it returns null
     * @Author Balaji N
     */
    public String getSelectedOptions_OnDropdown_StatementsTab() {
        return get_selected_option(monthdropdown_statementsTab, "Month dropdown field value on statements tab on customer details popup");
    }

    /**
     * Clicks the download button on statements tab on customer details popup
     *
     * @Author Balaji N
     */
    public void Click_DownloadBtn_OnStatementsTab() {
        js_Click(downloadstatementbutton_statementsTab, "Download button on statements tab on customer details popup");
    }

    public boolean Validate_GeneratedStatementRecord() {
        try {
            getDriver().switchTo().activeElement();
            delayWithGivenTime(2000);
            return iframereport_statementsTab.isDisplayed();
        } catch (Exception e) {
            throw new RuntimeException("Error validate generated statement record" + e);
        }
    }

    /**
     * Verify no statement found message on statements tab on customer details popup
     *
     * @return If the no statement found message is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_NoStatementFoundMessage_OnStatementsTab() {
        return isElementDisplayed(NoStatementsFoundMessage_statementsTab, "No statement found message on statements tab on customer details popup");
    }

    /**
     * Clicks the customer history icon on customer details section
     *
     * @Author Balaji N
     */
    public void Click_CustomerHistory_OnCustomerDetailsSection() {
        Click(custHistoryIcon_OnCustDetailsSection, "customer history icon on customer details section");
        wait_For_Page_To_Be_Stable(getDriver());
    }

    /**
     * verifies the customer history popup appears on phone order page
     *
     * @return If the customer history popup is displayed, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_CustomerHistoryPopupAppears_OnCustomerDetailsSection() {
        isElementDisplayed(previous_order_history_popup_on_phoneorderpage, "Previous orders history popup on customer details section - Phone order page");
        return is_Element_Displayed(previousOrdersHistoryHeaderPopup_OnCustDetailsSection, "Previous orders history header popup on customer details section");
    }


    /**
     * Clicks on a previous order in the Customer History popup based on the given invoice number.
     *
     * @param invoiceNumber The invoice number to find and click in the Customer History popup.
     * @throws NullPointerException   If the list of previous orders is null.
     * @throws NoSuchElementException If no matching order with the given invoice number is found.
     * @throws Exception              For any unexpected errors during execution.
     * @Author Balaji N
     */
    public void Click_PreviousOrder_OnCustomerHistoryPopup(String invoiceNumber) {
        int maxRetries = 3;
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                if (ListOfPreviousOrdersInvoices_InOrderHistoryTable_CustHistoryPopup == null || ListOfPreviousOrdersInvoices_InOrderHistoryTable_CustHistoryPopup.isEmpty()) {
                    throw new IllegalStateException("Previous order list is either null or empty.");
                }

                boolean orderFound = false;

                for (WebElement order : ListOfPreviousOrdersInvoices_InOrderHistoryTable_CustHistoryPopup) {
                    explicitWait(order); // Wait until element is ready
                    if (order.getText().trim().equals(invoiceNumber)) {
                        js_Click(order, "Invoice number '" + invoiceNumber + "' in the Customer History popup");
                        orderFound = true;
                        break;
                    }
                }

                if (!orderFound) {
                    throw new NoSuchElementException("Invoice number '" + invoiceNumber + "' not found in previous orders.");
                }

                // Success: break the retry loop
                break;

            } catch (StaleElementReferenceException | NoSuchElementException | IllegalStateException e) {
                attempt++;
                System.err.println("Attempt " + attempt + " failed: " + e.getMessage());
                if (attempt == maxRetries) {
                    throw new RuntimeException("Failed to click invoice number after " + maxRetries + " attempts: " + e.getMessage(), e);
                }
                // Optionally: add a short wait before retry
                delayWithGivenTime(2000);
            }
        }
    }


    public void Click_PreviousOrder_OnCustomerHistoryPopup() {
        for (WebElement order : ListOfPreviousOrdersInvoices_InOrderHistoryTable_CustHistoryPopup) {
            explicitWait(order);
            jsClick(order);
            break;
        }
    }

    /**
     * Verifies the text me promotions checkbox is checked on customer details popup
     *
     * @return If the text me promotions checkbox is checked, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_TextmepromotionCheckboxIsChecked_OnPhoneorderPage() {
        Highlight_Element(TextMepromotionsCheckBox_OnCustDetailsPopup, "Text me promotions checkbox on customer details popup");
        boolean isChecked = TextMepromotionsCheckBox_OnCustDetailsPopup.isSelected();
        if (isChecked) {
            System.out.println("The 'Text me promotions' checkbox is checked.");
        } else {
            System.out.println("The 'Text me promotions' checkbox is not checked.");
        }
        return isChecked;
    }

    /**
     * Clicks the text me promotions checkbox on customer details popup
     *
     * @Author Balaji N
     */
    public void Click_TextmePromotionCheckBox_OnphoneOrderPage() {
        js_Click(TextMepromotionsCheckBox_OnCustDetailsPopup, "Text me promotions checkbox on customer details popup");
    }

    /**
     * Verifies whether the SMS toggle button on customer details popup is in the 'on' state
     *
     * @return If the SMS toggle button is in the 'on' state, it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_SMSToogleOn_CustDetailsPopup() {
        HighlightElement(smstooglebutton_OnCustDetailsPopup);
        System.out.println(smstooglebutton_OnCustDetailsPopup.getAttribute("style").contains("rgb(197, 231, 244)"));
        return smstooglebutton_OnCustDetailsPopup.getAttribute("style").contains("rgb(197, 231, 244)");
    }

    /**
     * Clicks the SMS toggle button on customer details popup if it is not in the 'off' state
     *
     * @Author Balaji N
     */
    public void Click_SMSToogleOn_CustDetailsPopup() {
        if (!smstooglebutton_OnCustDetailsPopup.getAttribute("style").contains("rgb(197, 231, 244)")) {
            js_Click(smstooglebutton_OnCustDetailsPopup, "SMS toggle button on customer details popup");
        }
    }

    /**
     * Clicks the SMS toggle button on customer details popup
     *
     * @Author Balaji N
     */
    public void Click_SMS_Toogle_Button_On_Cust_Details_Popup() {
        js_Click(smstooglebutton_OnCustDetailsPopup, "SMS toggle button on customer details popup");
    }

    /**
     * Verifies if the recipient name auto-suggestions are displayed on the Phone Order page.
     *
     * @return true if auto-suggestions are listed, false otherwise.
     * @throws NoSuchElementException if the auto-suggestion list is not found.
     * @Author Balaji N
     */
    public boolean Verify_RecipientName_Autosuggestion() {
        try {
            int count = ListOfReciFirstName_Autosuggestions_OnPhoneOrderPage.size();
            if (count > 0) {
                System.out.println("The Recipient Name autosuggestion listed count is: " + count);
                return true;
            }
        } catch (NoSuchElementException e) {
            System.err.println("Recipient Name textbox field on Auto-suggestion list not found: " + e.getMessage());
            throw e;
        }
        return false;
    }

    /**
     * Search and Select the recipient first name from autosuggestion in the phone order page
     *
     * @param recifirstname
     */
    public void SearchAndSelect_RecipientNameFromAutoSuggestion(String recifirstname) {
      /*  for (int i = 0; i <= ListOfReciFirstName_Autosuggestions_OnPhoneOrderPage.size(); i++) {
            if (ListOfReciFirstName_Autosuggestions_OnPhoneOrderPage.get(i).getText().contains(recifirstname)) {
                delayWithGivenTime(2000);
                Mouse_Hover(ListOfReciFirstName_Autosuggestions_OnPhoneOrderPage.get(0), "First Name textbox autosugesstion value on recipient section in the phone order page");
                js_Click(ListOfReciFirstName_Autosuggestions_OnPhoneOrderPage.get(0), "First Name textbox autosugesstion value on recipient section in the phone order page");
                break;
            }
        }*/

        try {
            recipientfirstnameOnPhoneOrderPage.clear();
            delayWithGivenTime(500);
            ClickAndType(recipientfirstnameOnPhoneOrderPage, recifirstname, "First Name textbox field on Recipient section in the phone order page");
            delayWithGivenTime(2000);
            boolean customerfound = false;

            if (is_Element_Displayed(Recipient_firstname_autosugesstion_values, "First Name textbox field on autosugestion on Recipient section in the phone order page")) {
                try {
                    By customerEle = By.xpath("//ul[starts-with(@id, 'ui-id-') and not(contains(@style,'display: none'))]//li//div[contains(text(),'" + recifirstname + "')]");
                    WebElement customer = getDriver().findElement(customerEle);
                    js_Click(customer, "First name textbox field on autosuggestion on Recipient section in the phone order page");
                    customerfound = true;
                } catch (NoSuchElementException e) {
                    printError(Recipient_firstname_autosugesstion_values, "First Name textbox field on autosuggestion on Recipient section in the phone order page", "No such element exception", e);
                    System.err.println("Entered Recipient First name: " + recifirstname + " was not found in the autosuggestion list.");
                } catch (ElementNotInteractableException e) {
                    printError(Recipient_firstname_autosugesstion_values, "First Name textbox field on autosuggestion on Recipient section in the phone order page", "Element not interactable exception", e);
                    System.err.println("The customer " + recifirstname + " is not interactable.");
                } catch (Exception e) {
                    printError(Recipient_firstname_autosugesstion_values, "First Name textbox field on autosuggestion on Recipient section in the phone order page", "Generic  exception", e);
                    System.err.println("An unexpected error occurred while selecting the customer: " + e.getMessage());
                }
            } else {
                System.err.println("Autosuggestion of customer name entered is not displayed or not interactable.");
            }

            if (!customerfound) {
                System.out.println("Entered Recipient First Name : " + recifirstname + " not found.");
            }

        } catch (Exception e) {
            System.err.println("An error occurred during the search and select process: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void hover_On_Toaster_Message() {
        Mouse_Hover(this, addressverifiedmsg, "Toaster Message");
    }

    /**
     * Verifies the address verified by google toast message appears
     *
     * @return If the toast message appears it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_AddressverifiedByGoogle_ToastMsgAppears() {
        boolean isDisplayed = false;
        try {
            // Wait max 500ms for the toast to appear
//            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofMillis(500));
//            WebElement toast = wait.until(ExpectedConditions.visibilityOf(addressverifiedmsg));

            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='3px solid red'", addressverifiedmsg);
            isDisplayed = true;
        } catch (TimeoutException e) {
            Allure.step("❌ Google Address Verified toast message is NOT displayed when expected.");
        } catch (Exception e) {
            Allure.step("❌ Google Address Verified toast message element not found: " + e.getMessage());
        }
        return isDisplayed;
    }


    /**
     * Verify the address verified toast message text is displayed
     *
     * @return If the address verified toast message text is displayed it return the value of text otherwise return empty string or null
     * @Author Balaji N
     */
    public String Verify_ToastMsgText() {
        delayWithGivenTime(1000);
        fluent_Wait_for_Visibility(addressverifiedmsg, 10, 2);
        return getElementText(addressverifiedmsg, "Address Verified Toast Message Text");
    }

    /**
     * Verifies the recipient KMS appears on the phone order page
     *
     * @return If the recipient KMS appears then it returns the recipient KMS text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String Verify_RecipientKMS_Appears() {
        String Recipient_KMS = get_Element_Text(recipient_header_label_following_recipientKMS_label, "Recipient KMS text on Recipient section in the phone order page");
        return Recipient_KMS;
    }

    public boolean verify_RecipientKMS_Appears() {
        boolean Recipient_KMS = isElementDisplayed(recipient_header_label_following_recipientKMS_label, "Recipient KMS text on Recipient section in the phone order page");
        return Recipient_KMS;
    }

    /**
     * Verify whether recipient section is displayed or not
     *
     * @return If the recipient section is displayed it returns true else return false
     * @Author Balaji N
     */
    public boolean Verify_RecipientSectionAppears() {
        return is_Element_Displayed(recipientsectionOnPhoneOrderPage, "Recipient Section on Phone Order Page");
    }

    /**
     * Select delivery on time dropdown on recipient section on phone order page
     *
     * @param deliveryontime This is the visible text of the delivery on time to be selected
     * @Description This function will select delivery on time dropdown on recipient section on phone order page
     * @Author Balaji N
     */
    public void Select_DeliveryOnTime_Dropdown(String deliveryontime) {
        drop_Down(selectDeliverytimeonDropdown_RecipientSectionOnPhoneOrderPage, deliveryontime, "VisibleText", "Delivery on time dropdown on recipient section on phone order page");
    }

    /**
     * It retrieves the selected delivery on time dropdown on recipient section on phone order page
     *
     * @return If the delivery on time is selected then it returns the selected delivery on time otherwise it returns default values
     * @Description This function will select delivery on time dropdown on recipient section on phone order page
     * @Author Balaji N
     */
    public String getSelected_DeliveryOnTimeOptions_OnDropdown_RecipientSection() {
        return get_selected_option(selectDeliverytimeonDropdown_RecipientSectionOnPhoneOrderPage, "Delivery on Time dropdown on Recipient Section");
    }

    public void EnterTimeOnRecipientSection(String time) {
        recipientDeliverytimeOnPhoneOrderPage.sendKeys(time);
    }

    /**
     * It retrieves the entered time on recipient section
     *
     * @return If the time is entered then it returns the entered time otherwise it returns empty string
     * @Author Balaji N
     */
    public String getEnteredTimeOnRecipientSection() {
        return getElementAttribute(recipientDeliverytimeOnPhoneOrderPage, "Delivery Time textbox value on Recipient Section");
    }

    public String getCurrentTime() {
        // Get the current time
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = currentTime.format(formatter);
        System.out.println("Current Time: " + formattedTime);
        return formattedTime;
    }

    /**
     * Enter the delivery time on recipient section
     *
     * @Description This function will retrieve the current local time and set up 5:30 PM as default time then it converted into proper string format. After that it clears the existing data and then enter the delivery time in recipient section
     * @Author Balaji N
     */
    public void Enter_DeliveryTime_OnRecipientSection() {
        // Set the time 5:30 PM
        LocalTime time530PM = LocalTime.of(17, 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = time530PM.format(formatter);
        delayWithGivenTime(500);
        jsClearAndType(recipientDeliverytimeOnPhoneOrderPage, formattedTime);
    }

    /**
     * Enter the delivery time on recipient section in the phone order page
     *
     * @param hour   The provided  integer value to be entered as HOUR (Use 24 hr based time)
     * @param minute The provided integer value to be entered as HOUR
     * @Description This function will retrieve the current local time in the form of hours and minutes from provided then it converted into proper string format. After that it clears the existing data and then enter the delivery time in recipient section
     * @Author Balaji N
     */
    public void Enter_DeliveryTime_OnRecipientSection(int hour, int minute) {
        LocalTime time530PM = LocalTime.of(hour, minute);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = time530PM.format(formatter);
        delayWithGivenTime(500);
        js_Clear_And_Type(recipientDeliverytimeOnPhoneOrderPage, formattedTime, "Recipient Section - delivery time textbox field");
    }

    /**
     * It retrieves the delivery time on recipient section in the phone order page
     *
     * @return If the delivery time is selected then it returns the selected delivery time otherwise it returns default values
     * @Description This function will select delivery on time dropdown on recipient section on phone order page
     * @Author Balaji N
     */
    public String get_deliveryTime_on_recipientSection() {
        return getElementAttribute(recipientDeliverytimeOnPhoneOrderPage, "Delivery Time textbox value on Recipient Section");
    }

    /**
     * Clicks the clear time cross icon on recipient section on phone order page
     *
     * @Author Balaji N
     */
    public void Click_ClearTimeButton_OnRecipientSection() {
        js_Click(ClearTimeButton_recipientSectionOnPhoneOrderPage, "Clear cross icon on recipient section on phone order page");
    }

    /**
     * Validate whether the google map icon is displayed on recipient address 1 field on phone order page
     *
     * @return If the google map icon is displayed then it returns true otherwise it returns false
     * @Description This function will highlight the google map icon, and then validate whether the google map icon is displayed on recipient address 1 field on phone order page
     * @Author Balaji N
     */
    public boolean Verify_MapIconIsAppear_OnRecipientAddress1() {
        HighlightElement(googleIcon_ReciAddress1_OnPhoneOrderPage);
        return googleIcon_ReciAddress1_OnPhoneOrderPage.isDisplayed();
    }

    /**
     * Clicks the google map icon on recipient address 1 field on phone order page
     *
     * @Description This function will click the google map icon on recipient address 1 field on phone order page
     * @Author Balaji N
     */
    public void Click_MapIconOn_RecipientAddress1() {
        jsClick(googleIcon_ReciAddress1_OnPhoneOrderPage);
    }

    /**
     * Verify whether the map direction popup is displayed on recipient address 1 field on phone order page
     *
     * @return IF the map direction popup is displayed then it returns true otherwise it returns false
     * @Description This function will highlight the map direction popup, and then validate whether the map direction popup is displayed on recipient address 1 field on phone order page
     * @Author Balaji N
     */
    public boolean Verify_MapDirectionPopup_IsAppear() {
        return is_Element_Displayed(mapdirectionsPopup_ReciAddress1_OnPhoneOrderPage, "Map direction popup on recipient address 1 field on phone order page");
    }

    /**
     * CLicks the row1 direction instruction on map direction popup
     *
     * @Description This function will click the row1 direction instruction on map direction popup
     * @Author Balaji N
     */
    public void Click_Row1DirectionInstructions_OnMapDirectionPopup() {
        getDriver().switchTo().frame(mapiframe_ReciAddress1_OnPhoneOrderPage);
        delayWithGivenTime(1000);
        js_Click(directionsRow1_ReciAddress1_OnPhoneOrderPage, "Row 1 direction instruction on map direction popup");
    }

    /**
     * Validate Whether the tooltip text is displayed on map direction popup
     *
     * @return If the tool tip text is displayed then it returns true otherwise it returns false
     * @Description This function will validate whether the tooltip text is displayed on map direction popup
     * @Author Balaji N
     */
    public boolean Verify_TooltipTextIsAppear_OnMapDirectionPopup() {
        delayWithGivenTime(1000);
       /* HighlightElement(tooltiptext_MapDirectionPopup_ReciAddress1_OnPhoneOrderPage);
        return tooltiptext_MapDirectionPopup_ReciAddress1_OnPhoneOrderPage.isDisplayed();*/
        return is_Element_Displayed(tooltiptext_MapDirectionPopup_ReciAddress1_OnPhoneOrderPage, "Tooltip text on map direction popup");
    }

    /**
     * Validate whether the tooltip text is closed on map direction popup
     *
     * @return If the tool tip text is closed then it returns true otherwise it returns false
     * @throws Exception NoSuchElementException is thrown if the element is not found in the page
     * @Description This function will validate whether the tooltip text is closed on map direction popup
     * @Author Balaji N
     */
    public boolean Verify_TooltipClosed_OnMapDirectionPopup() {
        try {
            delayWithGivenTime(2000);
            return tooltiptext_MapDirectionPopup_ReciAddress1_OnPhoneOrderPage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clicks the close icon on map direction popup
     *
     * @Description This function will click the close icon on map direction popup
     * @Author Balaji N
     */
    public void Click_CloseIcon_OnMapDirectionPopup() {
        js_Click(TooltipCloseIcon_MapDirectionPopup_ReciAddress1_OnPhoneOrderPage, "Close icon on map direction popup");
    }

    /**
     * Clicks the satellite map icon on map direction popup
     *
     * @Description This function will click the satellite map icon on map direction popup
     * @Author Balaji N
     */
    public void Click_SatelliteMapIcon_OnMapDirectionPopup_ReciAddress1() {
        js_Click(SateliteTab_MapDirectionPopupReciAddress1_OnPhoneOrderPage, "satellite map icon on map direction popup");
    }

    /**
     * Mouse hover the satellite map icon and click the label of the satellite map icon on map direction popup
     *
     * @Description This function will mouse hover the satellite map icon and click the label of the satellite map icon on map direction popup
     * @Author Balaji N
     */
    public void MouseHoverSatelliteTabAndClickLabel_SatelliteMapIcon() {
        MouseHover(SateliteTab_MapDirectionPopupReciAddress1_OnPhoneOrderPage);
        delayWithGivenTime(2000);
        Click(satelliteTabLabel_MapDirectionPopupReciAddress1_OnPhoneOrderPage, "Label of the satellite map icon on map direction popup");
    }

    /**
     * Click Map tab on map direction popup
     *
     * @Description This function will click Map tab on map direction popup
     * @Author Balaji N
     */
    public void ClickMapTab_OnMapDirectionPopup_ReciAddress1() {
        js_Click(MapTab_MapDirectionPopupReciAddress1_OnPhoneOrderPage, "Map tab on map direction popup");
    }

    /**
     * Mouse hover the map tab and click the terrain on map direction popup
     *
     * @Description This function will mouse hover the map tab and click the terrain on map direction popup
     * @Author Balaji N
     */
    public void MouseHoverMapTabAndClickTerrain_OnMapDirectionPopup_ReciAddress1() {
        MouseHover(MapTab_MapDirectionPopupReciAddress1_OnPhoneOrderPage);
        delayWithGivenTime(2000);
        Click(satelliteTabTerrain_MapDirectionPopupReciAddress1_OnPhoneOrderPage, "Terrain on map direction popup");
    }

    /**
     * Validate whether the plus icon is displayed on map direction popup at recipient address 1 field on phone order page
     *
     * @return If the plus icon is displayed then it returns true otherwise it returns false
     * @Description This function will validate whether the plus icon is displayed on map direction popup at recipient address 1 field on phone order page
     * @Author Balaji N
     */
    public boolean Verify_PlusIcon_OnMapDirection_Popup_At_ReciAddress1_IsDisplayed() {
        HighlightElement(plusIcon_MapDirectionPopupReciAddress1_OnPhoneOrderPage);
        return plusIcon_MapDirectionPopupReciAddress1_OnPhoneOrderPage.isDisplayed();
    }

    /**
     * Click the plus icon on map direction popup at recipient address 1 field on phone order page
     *
     * @Description This function will click the plus icon on map direction popup at recipient address 1 field on phone order page
     * @Author Balaji N
     */
    public void Click_PlusIcon_OnMapDirectionPopup_ReciAddress1() {
        js_Click(plusIcon_MapDirectionPopupReciAddress1_OnPhoneOrderPage, "Plus icon on map direction popup at recipient address 1 field on phone order page");
    }

    /**
     * Validate whether the minus icon is displayed on map direction popup at recipient address 1 field on phone order page
     *
     * @return If the minus icon is displayed then it returns true otherwise it returns false
     * @Description This function will validate whether the minus icon is displayed on map direction popup at recipient address 1 field on phone order page
     * @Author Balaji N
     */
    public boolean Verify_MinusIcon_OnMapDirection_Popup_At_ReciAddress1_IsDisplayed() {
        HighlightElement(minusIcon_MapDirectionPopupReciAddress1_OnPhoneOrderPage);
        return minusIcon_MapDirectionPopupReciAddress1_OnPhoneOrderPage.isDisplayed();
    }

    /**
     * Click the minus icon on map direction popup at recipient address 1 field on phone order page
     *
     * @Description This function will click the minus icon on map direction popup at recipient address 1 field on phone order page
     * @Author Balaji N
     */
    public void Click_MinusIcon_OnMapDirectionPopup_ReciAddress1() {
        js_Click(minusIcon_MapDirectionPopupReciAddress1_OnPhoneOrderPage, "Minus icon on map direction popup at recipient address 1 field on phone order page");
    }

    /**
     * Validate whether the map is zoomed in or not
     *
     * @return IF the map is zoomed in then it returns true otherwise it returns false
     * @Description This function will validate whether the map is zoomed in or not with the help of style attribute
     * @Author Balaji N
     */
    public boolean VerifyMapIsZoomIn() {
        boolean zoomin = VerifyZoomIn_MapDirectionPopupReciAddress1_OnPhoneOrderPage.getAttribute("style").contains("matrix(1, 0, 0, 1, -93, -218)");
        return zoomin;
    }

    /**
     * Validate whether the map is zoomed out or not
     *
     * @return If the map is zoomed out then it returns true otherwise it returns false
     * @Description This function will validate whether the map is zoomed out or not with the help of style attribute
     * @Author Balaji N
     */
    public boolean VerifyMapIsZoomOut() {
        boolean zoomout = VerifyZoomIn_MapDirectionPopupReciAddress1_OnPhoneOrderPage.getAttribute("style").contains("matrix(1, 0, 0, 1, -47, -109)");
        return zoomout;
    }

    /**
     * Drag and drop the pegman on map direction popup recipient address 1 field on phone order page
     *
     * @Description This function will drag and drop the pegman on map direction popup recipient address 1 field on phone order page
     * @Author Balaji N
     */
    public void DragAndDrop_DragPegman_OnMapDirectionPopup_ReciAddress1() {
        dragAndDrop(DragPegman_MapDirectionPopupReciAddress1_OnPhoneOrderPage, DragPegman_MapDirectionPopupReciAddress1_OnPhoneOrderPage);
    }

    /**
     * Click the fullscreen icon on map direction popup recipient address 1 field on phone order page
     *
     * @Description This function will click the fullscreen icon on map direction popup recipient address 1 field on phone order page
     * @Author Balaji N
     */
    public void Click_FullscreenIcon_OnMapDirectionPopup_ReciAddress1() {
        js_Click(FullScreen_MapDirectionPopupReciAddress1_OnPhoneOrderPage, "Fullscreen icon on map direction popup recipient address 1 field on phone order page");
    }

    /**
     * Validate whether the map direction popup is displayed in fullscreen mode or not
     *
     * @return If the map direction popup is displayed in fullscreen mode then it returns true otherwise it returns false
     * @Description This function will validate whether the map direction popup is displayed in fullscreen mode or not
     * @Author Balaji N
     */
    public boolean Verify_MapDirectionPopup_DisplayedFullScreenMode() {
        return FullScreen_MapDirectionPopupReciAddress1_OnPhoneOrderPage.getAttribute("aria-pressed").contains("true");
    }

    /**
     * Click the close icon on map direction popup recipient address 1 field on phone order page
     *
     * @Description This function will click the close icon on map direction popup recipient address 1 field on phone order page
     * @Author Balaji N
     */
    public void Click_CloseIconOnMapDirectionPopup_ReciAddress1() {
        getDriver().switchTo().defaultContent();
        delayWithGivenTime(1000);
        js_Click(CloseIcon_MapDirectionPopupReciAddress1_OnPhoneOrderPage, "Close icon on map direction popup recipient address 1 field on phone order page");
    }

    /**
     * Validate whether the map direction popup is closed or not
     *
     * @return If the map direction popup is closed then it returns true otherwise it returns false
     * @Description This function will validate whether the map direction popup is closed or not
     * @Author Balaji N
     */
    public boolean MapDirectionPopup_isClosed() {
        try {
            delayWithGivenTime(1000);
            return mapdirectionsPopup_ReciAddress1_OnPhoneOrderPage.isDisplayed();

        } catch (NoSuchElementException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Enter the recipient state in the phone order page
     *
     * @param state The provided recipient state to be entered
     * @Description This function highlights and clicks the recipient state field and then enters the provided recipient state
     * @Author Balaji N
     */
    public void Enter_RecipientState(String state) {
        clickAndType(recipientstateOnPhoneOrderPage, state);
    }

    public void Clear_Existing_State_value_Recipient_Section() {
        jsClear(recipientstateOnPhoneOrderPage);
    }

    /**
     * It retrieves the displayed recipient state from the phone order page
     *
     * @return If text is exists it returns the displayed recipient state as text, otherwise it returns empty string if no recipient state to be entered
     * @Description This function highlights and clicks the recipient state field and then retrieves the displayed recipient state
     * @Author Balaji N
     */
    public String getRecipientState() {
        HighlightElement(recipientstateOnPhoneOrderPage);
        return recipientstateOnPhoneOrderPage.getAttribute("value");
    }

    /**
     * Validate whether the recipient attention field is displayed on the phone order page or not
     *
     * @return If the recipient attention field is displayed then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_RecipientAttentionField_IsDisplayed() {
        return is_Element_Displayed(recipientattentionOnPhoneOrderPage, "Recipient Attention field on Phone Order Page");
    }

    public boolean is_Attention_Field_Autosuggestion_Displayed() {
        return isElementDisplayed(attention_textbox_field_autosuggestion, "Attention field autosuggestion");
    }

    public void search_And_Select_Attention_Field_Recipient_Section(String attention, String city_state_country) {
        ClickAndType(recipientattentionOnPhoneOrderPage, attention, "Attention field - Recipient Section on Phone Order Page");
        delayWithGivenTime(2000);
        if (is_Attention_Field_Autosuggestion_Displayed()) {
            WebElement attention_textbox_field_autosuggestion = getDriver().findElement(By.xpath("//span[@class='pac-matched' and contains(text(),'" + attention + "')]"));
            WebElement attention_city_state_country = getDriver().findElement(By.xpath("//span[text()='" + city_state_country + "']"));
            if (attention_textbox_field_autosuggestion.getText().equals(attention) && attention_city_state_country.getText().equals(city_state_country)) {
                click(attention_textbox_field_autosuggestion, "Attention field autosuggestion");
            }
        }
    }

    /**
     * Enters the recipient's attention details into the designated text box on the Phone Order Page.
     *
     * @param recipientAttention The text to be entered into the Recipient Attention field.
     * @Author Balaji N
     */
    public void EnterRecipientAttention(String recipientattention) {
        try {
            Highlight_Element(recipientattentionOnPhoneOrderPage, "Recipient Attention textbox field on Phone Order Page");
            recipientattentionOnPhoneOrderPage.click();
            if (recipientattentionOnPhoneOrderPage.isDisplayed() && recipientattentionOnPhoneOrderPage.isEnabled()) {
                recipientattentionOnPhoneOrderPage.clear();
                delayWithGivenTime(1000);
                recipientattentionOnPhoneOrderPage.sendKeys(recipientattention);
            } else {
                throw new Exception("Recipient Attention textbox is either not visible or not enabled.");
            }
        } catch (Exception e) {
            System.err.println("Error while entering text in Recipient Attention field: " + e.getMessage());
        }
    }

    /**
     * Retrieves the value of the recipient attention field on the phone order page.
     *
     * @return If the recipient attention field is found and has a value, returns the value as a string; otherwise, returns an empty string.
     * @Author Balaji N
     */
    public String get_RecipientAttention() {
        return get_element_attribute(recipientattentionOnPhoneOrderPage, "Recipient Attention textbox field value on Phone Order Page");
    }

    /**
     * Searches and selects the recipient address from the autosuggestions on the phone order page.
     *
     * @param reciaAttention The recipient's address to be selected from the autosuggestions list.
     * @throws NoSuchElementException If the address is not found in the autosuggestions.
     * @throws NullPointerException   If the autosuggestions list is null.
     * @throws Exception              If any unexpected error occurs during the process.
     * @Author Balaji N
     */
    public void SearchAndSelect_ReciAttention(String reciaAttention) {
        try {
            if (recipientattentionOnPhoneOrderPage != null) {
                recipientattentionOnPhoneOrderPage.clear();
                delayWithGivenTime(1000);
                recipientattentionOnPhoneOrderPage.sendKeys(reciaAttention);
                delayWithGivenTime(2000);
            } else {
                System.out.println("Recipient attention field is not available");
                return;
            }


            if (is_Attention_Field_Autosuggestion_Displayed()) {//Washington Square, New York, NY, USA

            }

            // Check if suggestions list is not empty
            if (ListOfReciAddress1_Autosuggestions_OnPhoneOrderPage != null &&
                    !ListOfReciAddress1_Autosuggestions_OnPhoneOrderPage.isEmpty()) {
                if (is_Attention_Field_Autosuggestion_Displayed()) {
                    WebElement firstSuggestion = ListOfReciAddress1_Autosuggestions_OnPhoneOrderPage.get(0);
                    if (firstSuggestion != null) {
                        MouseHover(firstSuggestion);
                        click(firstSuggestion);
                        System.out.println("Selected recipient attention: " + reciaAttention);
                    } else {
                        System.out.println("First suggestion element is null.");
                    }
                }


            } else {
                System.out.println("No suggestions found for the given input: " + reciaAttention);
            }

        } catch (NoSuchElementException e) {
            System.err.println("Element not found: " + e.getMessage());
        } catch (StaleElementReferenceException e) {
            System.err.println("Element is no longer present on the DOM: " + e.getMessage());
        } catch (ElementNotInteractableException e) {
            System.err.println("Element not interactable: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }

    }

    /**
     * Searches and selects the recipient attention field on the phone order page.
     *
     * @param reciaAttention The attention address to be selected from the autosuggestions list.
     * @throws NoSuchElementException If the address is not found in the autosuggestions.
     * @throws NullPointerException   If the autosuggestions list is null.
     * @throws Exception              If any unexpected error occurs during the process.
     * @Author Balaji N
     */
    public void SearchAndSelect_Reci_AttentionField(String reciaAttention) {
        try {
            // Check if the autosuggestions list is null or empty
            if (ListOfReciAddress1_Autosuggestions_OnPhoneOrderPage == null ||
                    ListOfReciAddress1_Autosuggestions_OnPhoneOrderPage.isEmpty()) {
                throw new NullPointerException("The autosuggestions list is null or empty.");
            }

            boolean isSelected = false;

            // Iterate through the autosuggestions to find a match
            for (int i = 0; i <= ListOfReciAddress1_Autosuggestions_OnPhoneOrderPage.size(); i++) {
                {
                    if (ListOfReciAddress1_Autosuggestions_OnPhoneOrderPage.get(i).getText().contains(reciaAttention)) {
                        MouseHover(ListOfReciAddress1_Autosuggestions_OnPhoneOrderPage.get(0));
                        click(ListOfReciAddress1_Autosuggestions_OnPhoneOrderPage.get(0));
                        isSelected = true;
                        break;
                    } else {
                        System.out.println("List of attention is not matched with expected value");
                    }
                }
            }

            // If no match was found, throw an exception
            if (!isSelected) {
                throw new NoSuchElementException("Recipient address '" + reciaAttention + "' not found in the autosuggestions.");
            }

        } catch (NoSuchElementException e) {
            System.err.println("Error: " + e.getMessage());
            // Optionally log this error to a file or reporting system
        } catch (NullPointerException e) {
            System.err.println("Error: " + e.getMessage());
            // Optionally log this error to a file or reporting system
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error: Invalid index while accessing the autosuggestions list.");
            // Optionally log this error to a file or reporting system
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging purposes
            // Optionally log this error to a file or reporting system
        }
    }

    /**
     * Verify whether the recipient attention field is displayed on the phone order page or not
     *
     * @return If the recipient attention field is displayed then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_Autosuggestion_Displayed_OnReciAttention() {
        Highlight_Element(recipientattentionOnPhoneOrderPage, "Recipient Attention textbox field on Phone Order Page");
        recipientattentionOnPhoneOrderPage.clear();
        delayWithGivenTime(1000);
        for (int i = 0; i < ListOfReciAddress1_Autosuggestions_OnPhoneOrderPage.size(); i++) {
            int ReciAddress1_size = ListOfReciAddress1_Autosuggestions_OnPhoneOrderPage.size();
            if (ReciAddress1_size > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Clicks the selected options from the recipient dropdown it contains confidential, frequent address and save funeral options
     *
     * @Description This function clicks the selected options from the recipient dropdown
     * @Author Balaji N
     */
    public void ClickSelectOptions_OnRecipient() {
        //  actionClick(recipientSelectOptionsBtnOnPhoneOrderPage, "Select option multi-select dropdown on the recipient section");
        //   Actions action = new Actions(getDriver());
        //  action.keyDown(Keys.ENTER).build().perform();
        //  action.keyUp(Keys.ENTER).build().perform();
        js_Click(recipientSelectOptionsBtnOnPhoneOrderPage, "Select option multi-select dropdown on the recipient section");
    }

    /**
     * It retrieves the selected options from the recipient dropdown it contains confidential, frequent address and save funeral options
     *
     * @return If the recipient dropdown is displayed then it returns the selected options otherwise it returns null
     * @Author Balaji N
     */
    public String get_SelectedOptions_from_MultiSelectDropdown_on_RecipientSection() {
        return get_Element_Text(recipientSelectOptionsBtnOnPhoneOrderPage, "recipient Select Options Button On Phone Order Page");
    }

    /**
     * Clicks the select all option on select options multiselect dropdown field in recipient section
     *
     * @Author Balaji N
     */
    public void Click_SelectAllOption() {
        js_Click(selectallOption_RecipientSelectOptionsDropDown, "Select all options on recipient section");
    }

    /**
     * Clicks the confidential option on select options multiselect dropdown field in recipient section
     *
     * @Author Balaji N
     */
    public void Click_ConfidentialOption() {
        js_Click(confidentialOption_RecipientSelectOptionsDropDown, "Confidential option on multiselect dropdown in recipient section");
    }

    /**
     * Clicks the frequent address option on select options multiselect dropdown field in recipient section
     *
     * @Author Balaji N
     */
    public void Click_FrequentAddrOption() {
        js_Click(FrequentAddrOption_RecipientSelectOptionsDropDown, "Frequent address option on select options multiselect dropdown field in recipient section");
    }

    /**
     * Clicks the save funeral option on select options multiselect dropdown field in recipient section
     *
     * @Author Balaji N
     */
    public void Click_SaveFuneralOption() {
        js_Click(saveFuneralOption_RecipientSelectOptionsDropDown, "Save funeral options on select options multiselect dropdown field in recipient section");
    }


    public void Select_Location_OnRecipientSection(String recilocation) {
        drop_Down(recipientLocation, recilocation, "VisibleText", "Recipient Location dropdown field on phone order page");
    }

    public String getSelectedLocation_OnRecipientSection() {
        s = new Select(recipientLocation);
        return s.getFirstSelectedOption().getText();
    }

    /**
     * Selects the zone from the recipient section dropdown field in phone order page
     *
     * @param recizone Provided recipient zone to be entered
     * @Description This function using the reusable method of dropDown perform to select the zone on recipient section in the phone order page
     * @Author Balaji N
     */
    public void Select_Zone_OnRecipientSection(String recizone) {
        drop_Down(recipientzone, recizone, "VisibleText", "Recipient Zone dropdown field on phone order page");
    }

    /**
     * It retrieves the zone selected from the recipient section dropdown field in phone order page
     *
     * @return If text is exists it returns the displayed zone as text, otherwise it returns empty string if no zone to be entered
     * @Description This function highlights and clicks the zone field and then retrieves the displayed zone
     * @Author Balaji N
     */
    public String getSelectedZone_OnRecipientSection() {
        s = new Select(recipientzone);
        return s.getFirstSelectedOption().getText();
    }

    /**
     * It retrieves the delivery fees from the payment section in the phone order page
     *
     * @return If text is exists it returns the displayed delivery fees as text, otherwise it returns empty string if no delivery fees to be entered
     * @Note : If surcharge is activated then it will added with zone based delivery fees
     * @Description This function highlights and clicks the delivery fees field and then retrieves the displayed delivery fees
     * @Author Balaji N
     */
    public String getDeliveryFees_PaymentSection_OnPhoneOrder() {
        HighlightElement(deliveryFee_PhoneOrder_PaymentSection);
        return deliveryFee_PhoneOrder_PaymentSection.getAttribute("value");
    }

    public String get_RelayFees_PaymentSection_OnPhoneOrder() {
        HighlightElement(relayFee_PhoneOrder_PaymentSection);
        return relayFee_PhoneOrder_PaymentSection.getAttribute("value");
    }

    public String getSelectedOption() {
        return selectedRecipientOptionsOnPhoneOrderPage.getText();
    }

    /**
     * verify whether the order details section is displayed on the phone order page or not
     *
     * @return If the order details section is displayed then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_OrderDetailsSectionAppears() {
        return is_Element_Displayed(OrderDetailsSection, "Order Details Section in the phone order page");
    }

    /**
     * Verify whether the send remainder checkbox is displayed on the order details section or not
     *
     * @return If the send remainder checkbox is displayed then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_SendRemainder_OnOrderDetailsIsAppears() {
        try {
            delayWithGivenTime(2000);
            return sendreminderlabel_OrderDetailsTab.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clicks the send remainder checkbox on the order details section
     *
     * @Author Balaji N
     */
    public void Click_SendRemainderCheckBox() {
        js_Click(sendreminderlabel_OrderDetailsTab, "send remainder label on order details section");
    }

    /**
     * Verifies whether the send remainder checkbox is selected or not
     *
     * @return If the send remainder checkbox is selected then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_SendRemainderCheckBoxIsSelected() {
        String fieldName = "Send Remainder Checkbox in Order Details Section";
        try {
            boolean isSelected = sendRemainderCheckbox_OrderDetailsSection.isSelected();
            System.out.println(fieldName + " is selected: " + isSelected);
            return isSelected;
        } catch (NoSuchElementException e) {
            System.err.println("Error: " + fieldName + " not found on the page.");
        } catch (StaleElementReferenceException e) {
            System.err.println("Error: " + fieldName + " is stale. Retrying...");
        } catch (Exception e) {
            System.err.println("Unexpected error while checking " + fieldName + ": " + e.getMessage());
        }
        return false;
    }

    /**
     * Verifies whether the print ticket checkbox is selected or not
     *
     * @return If the print ticket checkbox is selected then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_PrintTicketCheckBoxIsSelected() {
        System.out.println("Print ticket checkbox is selected:" + printTicketCheckBox_OrderDetailsSection.isSelected());
        return printTicketCheckBox_OrderDetailsSection.isSelected();
    }

    /**
     * Verifies whether the priority checkbox is selected or not
     *
     * @return If the priority checkbox is selected then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_PriorityCheckBoxIsSelected() {
        return priorityCheckBox_OrderDetailsSection.isSelected();
    }

    /**
     * Clicks the priority checkbox on the order details section
     *
     * @Author Balaji N
     */
    public void Click_PriorityCheckBox_OnOrderDetails() {
        click(priorityCheckBox_OrderDetailsSection, "Priority checkbox on order details section");
    }

    /**
     * Clicks the view short codes hyperlink on the order details section
     *
     * @Author Balaji N
     */
    public void Click_ViewShortCodes_Hyperlink() {
        js_Click(ViewShortCodesLink_OrderDetailsSection, "View Short Code link on order details section");
    }

    /**
     * Verifies whether the view short codes hyperlink is displayed on the order details section or not
     *
     * @return If the view short codes hyperlink is displayed then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_ViewShortCodes_HyperlinkIsDisplayed() {
        return is_Element_Displayed(ViewShortCodesLink_OrderDetailsSection, "View Short Code link on order details section");
    }

    /**
     * Verifies whether the view short codes popup is displayed on the order details section or not
     *
     * @return If the view short codes popup is displayed then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_ViewShortCodes_PopupIsDisplayed() {
        return is_Element_Displayed(ShortCodes_Popup_OrderDetailsSection, "Short Code popup on order details section");
    }

    /**
     * Enter the Sets textbox field with the code on the view short codes popup
     *
     * @param code the code to enter
     * @Author Balaji N
     */
    public void SetCode_OnViewShortCodesPopup(String code) {
        for (WebElement ele : ListOfCodes_ShortCodesPopup_OrderDetailsSection) {
            if (ele.getText().equals(code)) {
                WebElement delete_icon = getDriver().findElement(By.xpath("//div[@class='shortCodeListDiv']/child::table//tbody//tr//td[contains(text(),'" + code + "')]//following::td[2]//a"));
                js_Click(delete_icon, "Delete icon on view short codes popup");
                delayWithGivenTime(500);
                if (Verify_ConfirmationPopup() == true) {
                    Click_DeleteButtonDeleteConfirmation();
                }
                break;
            }
        }
        delayWithGivenTime(2000);
        ClickAndType(codeTextBox_ShortCodesPopup_OrderDetailsSection, code, "Code textbox field on view short codes popup");
    }

    /**
     * Retrieves the code entered in the code textbox field on the view short codes popup
     *
     * @return If the code is displayed then it returns the code as text, otherwise it returns empty string if no code to be entered
     * @Author Balaji N
     */
    public String get_EnteredCode_OnViewShortCodesPopup() {
        return getElementAttribute(codeTextBox_ShortCodesPopup_OrderDetailsSection, "code textbox field value on view short codes popup");
    }

    /**
     * Enters the short code description value in the value textbox field on the view short codes popup
     *
     * @param value
     * @Author Balaji N
     */
    public void SetValue_OnViewShortCodesPopup(String value) {
        ClickAndType(valueTextBox_ShortCodesPopup_OrderDetailsSection, value, "Value textbox field on view short codes popup");
    }

    /**
     * Retrieves the value entered in the value textbox field on the view short codes popup
     *
     * @return If the value is displayed then it returns the value as text, otherwise it returns empty string if no value to be entered
     * @Author Balaji N
     */
    public String get_EnteredValue_OnViewShortCodesPopup() {
        return getElementAttribute(valueTextBox_ShortCodesPopup_OrderDetailsSection, "Value textbox field value on view short codes popup");
    }

    /**
     * Clicks the add button on the view short codes popup
     *
     * @Author Balaji N
     */
    public void Click_AddButton_OnViewShortCodesPopup() {
        js_Click(addButton_ShortCodesPopup_OrderDetailsSection, "Add button on view short codes popup");
    }

    /**
     * Verifies whether the added short code is displayed on the view short codes popup or not
     *
     * @return If the added short code is displayed then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_AddedShortCode_IsDisplayed_OnViewShortCodesPopup() {
        HighlightElement(LastShortCode_OrderDetailsSection);
        String Code = LastShortCode_OrderDetailsSection.getText();
        HighlightElement(LastValues_OrderDetailsSection);
        String value = LastValues_OrderDetailsSection.getText();
        System.out.println(Code + "  " + value);
        if (Code.equals("At") && value.equals("Automation Test")) {
            return true;
        } else {
            System.out.println("Added ShortCode is not displayed");
            return false;
        }
    }

    public void Click_CloseIcon_OnViewShortCodesPopup() {
        jsClick(closeIcon_ShortCodesPopup_OrderDetailsSection);
    }

    /**
     * Verifies whether the last short code delete icon is displayed on the order details section or not
     *
     * @return If the last short code delete icon is displayed then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_DeleteIconDisplayed() {
        return LastdeleteIcon_OrderDetailsSection.isDisplayed();
    }

    /**
     * Clicks the last short code delete icon on the order details section
     *
     * @Author Balaji N
     */
    public void Click_DeleteIcon_OnShortCodesPopup() {
        js_Click(LastdeleteIcon_OrderDetailsSection, "Last delete icon on short codes popup");
    }

    /**
     * Clicks the yes button on the delete confirmation popup
     *
     * @Author Balaji N
     */
    public void Click_DeleteButtonDeleteConfirmation() {
        delayWithGivenTime(1000);
        js_Click(YesButtonDeleteConfirmation_ShortCodesPopup_OrderDetailsSection, "Yes button on delete confirmation popup");
    }

    /**
     * Verify whether the delete confirmation popup is displayed or not
     *
     * @return If the delete confirmation popup is displayed then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_ConfirmationPopup() {
        delayWithGivenTime(1000);
        return is_Element_Displayed(ConfirmationAlert_ShortCodesPopup_OrderDetailsSection, "Delete Confirmation Alert in Short Code Popup");
    }

    /**
     * Verify whether the delete confirmation popup is displayed or not
     *
     * @return If the delete confirmation popup is displayed then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_DeleteConfirmation() {
        delayWithGivenTime(1000);
        if (isElementDisplayed(ConfirmationPopup, "Delete Confirmation Alert in Short Code Popup")) {
            is_Element_Displayed(ConfirmationAlertIcon_ShortCodesPopup_OrderDetailsSection, "Delete Confirmation alert icon");
            is_Element_Displayed(ConfirmationAlert_ShortCodesPopup_OrderDetailsSection, "Delete confirmation popup - confirmation text");
            is_Element_Displayed(YesButtonDeleteConfirmation_ShortCodesPopup_OrderDetailsSection, "Yes button on delete confirmation popup");
            is_Element_Displayed(NoButtonDeleteConfirmation_ShortCodesPopup_OrderDetailsSection, "No button on delete confirmation popup");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Clicks the no button on the delete confirmation popup
     *
     * @Author Balaji N
     */
    public void Click_NoButtonDeleteConfirmation() {
        js_Click(NoButtonDeleteConfirmation_ShortCodesPopup_OrderDetailsSection, "No button on delete confirmation popup");
    }

    public boolean Verify_ProductSectionLabel_Appears() {
        HighlightElement(productsectionOnPhoneOrderPage);
        return productsectionOnPhoneOrderPage.isDisplayed();
    }

    /**
     * Validate the Product section appears on phone order page
     *
     * @return If Product section is displayed it returns true otherwise it return false.
     * @Description: This function will validates whether product section is appears.
     * @Author: Balaji N
     */
    public boolean Verify_ProductSection_Appears_OnPhoneorder() {
        HighlightElement(productsection_OnPhoneOrderPage);
        boolean isProductSectionDisplayed = productsection_OnPhoneOrderPage.isDisplayed();
        return isProductSectionDisplayed;
    }

    /**
     * This function validates whether product auto-suggestions are displayed
     * on the product section's item code row 1 textbox field.
     *
     * @param proditemcode The product item code that is entered into the field
     * @return true if all expected products are displayed in the suggestions; otherwise it returns false
     */
    public boolean Verify_ProductSuggestions_Appears(String proditemcode) {
        prod_details_Itemcode1.clear();
        prod_details_Itemcode1.sendKeys(proditemcode);
        delayWithGivenTime(2000);
        List<String> expectedProducts = Arrays.asList("rrd-Red Rose Small-199", "rrd-Red Rose Deluxe-299", "rrd-Red Rose Premium-399", "rrd-Red Rose Wedding Reception Flower-789", "rrd2-Red Rose Semi-premium-299");
        Set<String> actualProducts = new HashSet<>();

        for (WebElement products : listOfItemsUnderItemcode1) {
            HighlightElement(products);
            actualProducts.add(products.getText());
        }

        if (expectedProducts.containsAll(actualProducts)) {
            return true;
        } else {
            System.out.println("Failed: All expected products are displayed" + actualProducts);
            System.out.println("Failed: Expected products are displayed as: " + expectedProducts);
            return false;
        }
    }

    /**
     * Verify the item code autosuggestion is displayed or not for plsr itemcode
     *
     * @param proditemcode
     * @return
     */
    public boolean verify_ProductSuggestions_Appears(String proditemcode) {
        prod_details_Itemcode1.clear();
        prod_details_Itemcode1.sendKeys(proditemcode);
        delayWithGivenTime(2000);
        List<String> expectedProducts = Arrays.asList("plsr-Premium Long-Stem Roses Red-159", "plsr-Premium Long-Stem Roses White-169", "plsr-Premium Long-Stem Roses Lavender-179");
        Set<String> actualProducts = new HashSet<>();

        for (WebElement products : listOfItemsUnderItemcode1) {
            HighlightElement(products);
            actualProducts.add(products.getText());
        }

        if (expectedProducts.containsAll(actualProducts)) {
            return true;
        } else {
            System.out.println("Failed: All expected products are displayed" + actualProducts);
            System.out.println("Failed: Expected products are displayed as: " + expectedProducts);
            return false;
        }
    }

    /**
     * Clicks the remove cross icon on the product row 1
     *
     * @Description: This function will click on the remove cross icon on the product row 1
     * @Author: Balaji N
     */
    public void Click_ProductRemoveIcon_Row1() {
        js_Click(removeIconProdDetails1, "Cross Icon Row 1 at product details section");
    }

    /**
     * Validates whether the product row 1 is removed from the product section
     *
     * @return If the product row 1 is removed, it returns true; otherwise false
     * @Description: This function will validate whether the product row 1 is removed from the product section
     * @Author: Balaji N
     */
    public boolean Verify_ProductRow1Details_Removed() {
        try {
            if (prod_details_Itemcode1.isDisplayed()) {
                return false;
            }
        } catch (NoSuchElementException e) {
            return true;
        } catch (Exception e) {
            System.err.println("An error occurred while verifying Row 1 deletion: " + e.getMessage());
        }
        return false;
    }

    /**
     * Validates whether the autosuggestions list appears on the item description field
     *
     * @param proditemdescription The provided item description value to be entered
     * @return If the autosuggestions list appears, it returns true, otherwise false
     * @Description: This function will validate whether the autosuggestions list appears on the item description field
     * @Author: Balaji N
     */
    public boolean Verify_AutosugestionsList_OnItemDescription_Appears(String proditemdescription) {
        prod_details_ItemDescription2OnPhoneOrderPage.clear();
        prod_details_ItemDescription2OnPhoneOrderPage.sendKeys(proditemdescription);
        delayWithGivenTime(2000);
        List<String> expectedProducts = Arrays.asList("rrd-Red Rose Small-199", "rrd-Red Rose Deluxe-299", "rrd-Red Rose Premium-399", "rrd-Red Rose Wedding Reception Flower-789", "rrd2-Red Rose Semi-premium-299");
        Set<String> actualProducts = new HashSet<>();
        for (WebElement products : listOfRow2_ItemDescription_OnPhoneOrderPage) {
            HighlightElement(products);
            actualProducts.add(products.getText());
        }
        if (expectedProducts.containsAll(actualProducts)) {
            return true;
        } else {
            System.out.println("Expected products descriptions are not displayed" + actualProducts);
            System.out.println("Expected products  descriptions are displayed as: " + expectedProducts);
            return false;
        }
    }

    /**
     * Search and select the product with item description on product section in phoneorder page
     *
     * @param proditemShortdesc   provided item short description value to be entered
     * @param proditemdescription provided item description value to be entered
     * @Description This function will search and select the product with item description on product section in phoneorder page
     * @Author: Balaji N
     */
    public void SearchAndSelect_WithItemDescription(String proditemShortdesc, String proditemdescription) {
       /* prod_details_ItemDescription2OnPhoneOrderPage.clear();
        prod_details_ItemDescription2OnPhoneOrderPage.sendKeys(proditemShortdesc);
        delayWithGivenTime(2000);
        for (WebElement products : listOfRow2_ItemDescription_OnPhoneOrderPage) {
            if (products.getText().contains(proditemdescription)) {
                js_Click(products,"Row 2 item description on product section in phoneorder page");
                break;
            }
        }*/

        try {
            Double_Click_And_Type(prod_details_ItemDescription2OnPhoneOrderPage, proditemShortdesc, "Item description textbox field on product section in the Phone Order Page");
            delayWithGivenTime(3000);

            boolean itemFound = false;
            if (is_Element_Displayed(itemdescription_list_autosuggestion, "Item Code autosuggestion list on product details section in the phone order page")) {
                try {
                    WebElement item = getDriver().findElement(By.xpath("//ul[@id='ui-id-20']//li//div[contains(text(),'" + proditemdescription + "')]"));
                    js_Click(item, "Item description autosuggestion list on product details section");
                    itemFound = true;
                } catch (NoSuchElementException e) {
                    System.err.println("Item with description " + proditemdescription + " was not found in the autosuggestion list.");
                } catch (ElementNotInteractableException e) {
                    System.err.println("The item with description " + proditemdescription + " is not interactable.");
                } catch (Exception e) {
                    System.err.println("An unexpected error occurred while selecting the item: " + e.getMessage());
                }
            } else {
                System.err.println("Autosuggestion of product item description entered is not displayed or not interactable.");
            }

            if (!itemFound) {
                System.out.println("Item with description " + proditemdescription + " not found.");
            }

        } catch (Exception e) {
            System.err.println("An error occurred during the search and select process: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * Clicks the add icon at row 3 on the product details sections
     *
     * @Author Balaji N
     */
    public void Click_AddIcon_Row3() {
        js_Click(addIconProdDetails3_OnPhoneOrderPage, "Add Icon Row 3 on Product details section");
    }

    /**
     * Verify whether the row 4 product details section is displayed or not
     *
     * @return If the row 4 product details
     */
    public boolean Verify_Row4_ProductDetailsItemcode_Appears() {
        int row_count = listOfRow_ProductDetails_OnPhoneOrderPage.size();
        if (row_count == 3) {
            is_Element_Displayed(prod_details_Itemcode4_OnPhoneOrderPage, "Item code row 4 on product details section");
            return true;
        } else {
            return false;
        }
    }

    /**
     * It retrieves the item code row 2 value from the product details section on phone order page
     *
     * @return If the item code row 2 value is retrieved, it returns the value; otherwise it returns empty string
     * @Description This function highlights the item code row 2 and then retrieves the item code value from the product details section on phone order page
     * @Author: Balaji N
     */
    public String get_ItemCodeRow2_ProductDetails() {
        HighlightElement(prod_details_Itemcode2_OnPhoneOrderPage);
        return prod_details_Itemcode2_OnPhoneOrderPage.getAttribute("value");
    }

    /**
     * It retrieves the item description row 2 value from the product details section on phone order page
     *
     * @return If the item description row 2 value is retrieved, it returns the value; otherwise it returns empty string
     * @Description This function highlights the item description row 2 and then retrieves the item description value from the product details section on phone order page
     * @Author: Balaji N
     */
    public String get_ItemDescriptionRow2_ProductDetails() {
        HighlightElement(prod_details_ItemDescription2_OnPhoneOrderPage);
        return prod_details_ItemDescription2_OnPhoneOrderPage.getAttribute("value");
    }

    /**
     * It retrieves the item qty row 2 value from the product details section on phone order page
     *
     * @return If the item qty row 2 value is retrieved, it returns the value; otherwise it returns empty string
     * @Description This function highlights the item qty row 2 and then retrieves the item qty value from the product details section on phone order page
     * @Author: Balaji N
     */
    public String get_ItemQtyRow2_ProductDetails() {
        HighlightElement(prod_details_ItemQty2_OnPhoneOrderPage);
        return prod_details_ItemQty2_OnPhoneOrderPage.getAttribute("value");
    }

    /**
     * It retrieves the item price row 2 value from the product details section on phone order page
     *
     * @return If the item price row 2 value is retrieved, it returns the value; otherwise it returns empty string
     * @Description This function highlights the item price row 2 and then retrieves the item price value from the product details section on phone order page
     * @Author: Balaji N
     */
    public String get_ItemPriceRow2_ProductDetails() {
        HighlightElement(prod_details_ItemPrice2_OnPhoneOrderPage);
        return prod_details_ItemPrice2_OnPhoneOrderPage.getAttribute("value");
    }

    /**
     * Enters the special instructions on product details section on phone order page
     *
     * @param SpecialInstructions Provided special instructions value to be entered
     * @Description This function clears the existing data in the special instructions field, then enters the provided special instructions value
     * @Author: Balaji N
     */
    public void EnterSpecialInstructions_ProductDetailsSection(String SpecialInstructions) {
        //  HighlightElement(Splinstruction_Textbox_OnPhoneOrderPage);
        Splinstruction_Textbox_OnPhoneOrderPage.clear();
        ClickAndType(Splinstruction_Textbox_OnPhoneOrderPage, SpecialInstructions, "Special instruction textbox field on product details section");
    }

    /**
     * Retrieves the special instructions on product details section on phone order page
     *
     * @return If the special instructions value is retrieved, it returns the value; otherwise it returns empty string
     * @Description This function retrieves the special instructions value from the product details section on phone order page
     * @Author: Balaji N
     */
    public String get_SpecialInstructions_ProductDetailsSection() {
        return getElementAttribute(Splinstruction_Textbox_OnPhoneOrderPage, "Special instruction textbox value on product details section");
    }

    /**
     * Enters the driver instructions on product details section on phone order page
     *
     * @param DriverInstructions Provided driver instructions value to be entered
     * @Description This function clears the existing data in the driver instructions field, then enters the provided driver instructions value
     * @Author: Balaji N
     */
    public void EnterDriverInstructions_ProductDetailsSection(String DriverInstructions) {
        Driverinstruction_Textbox_OnPhoneOrderPage.clear();
        ClickAndType(Driverinstruction_Textbox_OnPhoneOrderPage, DriverInstructions, "Driver Instruction textbox field on product details section");
    }

    /**
     * Retrieves the driver instructions on product details section on phone order page
     *
     * @return If the driver instructions value is retrieved, it returns the value; otherwise it returns empty string
     * @Description This function retrieves the driver instructions value from the product details section on phone order page
     * @Author: Balaji N
     */
    public String get_DriverInstructions_ProductDetailsSection() {
        return getElementAttribute(Driverinstruction_Textbox_OnPhoneOrderPage, "Driver instruction textbox field value on product details section");
    }

    /**
     * Enters the customer private notes instructions on product details section on phone order page
     *
     * @param customerPrivateNotesInstructions Provided customer private notes instructions value to be entered
     * @Description This function clears the existing data in the customer private notes instructions field, then enters the provided customer private notes instructions value
     * @Author: Balaji N
     */
    public void EnterCustomerPrivateNotesInstructions_ProductDetailsSection(String customerPrivateNotesInstructions) {
        customerPrivateNotes_Textbox_OnPhoneOrderPage.clear();
        ClickAndType(customerPrivateNotes_Textbox_OnPhoneOrderPage, customerPrivateNotesInstructions, "Customer Private Notes textbox field on product details section");
        delayWithGivenTime(1000);
        customerPrivateNotes_Textbox_OnPhoneOrderPage.sendKeys(Keys.TAB);
    }

    /**
     * Retrieves the customer private notes instructions on product details section on phone order page
     *
     * @return If the customer private notes instructions value is retrieved, it returns the value; otherwise it returns empty string
     * @Description This function retrieves the customer private notes instructions value from the product details section on phone order page
     * @Author: Balaji N
     */
    public String get_CustomerPrivateNotesInstructions_ProductDetailsSection() {
        return getElementAttribute(customerPrivateNotes_Textbox_OnPhoneOrderPage, "Customer private notes textbox field on product details section");
    }

    /**
     * Selects the product tax type dropdown field on product details section
     *
     * @param taxtype
     * @Author Balaji N
     */
    public void Select_ProductTaxType(String taxtype) {
        drop_Down(producttaxType_OnPhoneOrderPage, taxtype, "VisibleText", "Product tax type dropdown field on product details section");
    }

    /**
     * It retrieves the product tax type dropdown value on product details section
     *
     * @return if the product tax type dropdown value is retrieved, it returns the value; otherwise it returns empty string
     * @Author: Balaji N
     */
    public String get_selected_ProductTaxType() {
        return get_selected_option(producttaxType_OnPhoneOrderPage, "product tax type dropdown field value");
    }

    /**
     * Enters the product tax id on phone order page
     *
     * @param prodtaxid the product tax id to be entered
     * @Author Balaji N
     */
    public void Enter_ProductTaxId(String prodtaxid) {
        producttaxId_OnPhoneOrderPage.clear();
        ClickAndType(producttaxId_OnPhoneOrderPage, prodtaxid, "Product Tax Id");
    }

    /**
     * It retrieves the product tax id on phone order page
     *
     * @return If the product tax id is retrieved, it returns the value; otherwise it returns empty string
     * @Author Balaji N
     */
    public String get_ProductTaxId() {
        return getElementAttribute(producttaxId_OnPhoneOrderPage, "Product tax id on phone order page");
    }

    /**
     * Selects the product source code on phone order page
     *
     * @param prodsourcecode The Given product source code to be selected
     * @Author: Balaji N
     */
    public void Select_ProdSourceCode(String prodsourcecode) {
        drop_Down(productSourceCode_OnPhoneOrderPage, prodsourcecode, "VisibleText", "Product Source Code dropdown field on phone order page");
    }

    /**
     * It retrieves the selected product source code on phone order page
     *
     * @return If the selected product source code it is selected, it returns the value; otherwise it returns empty string
     * @Author: Balaji N
     */
    public String get_selected_ProdSourceCode() {
        return get_selected_option(productSourceCode_OnPhoneOrderPage, "product Source Code value On Phone Order Page");
    }

    /**
     * Selects the product customer type on phone order page
     *
     * @param custType The Given product customer type to be selected
     * @Description This function using the reusable method from testbaseclass dropDown function perform to select the product customer type on phone order page
     * @Author: Balaji N
     */
    public void Select_ProdCustType(String custType) {
        drop_Down(productcustomerType_OnPhoneOrderPage, custType, "VisibleText", "Customer Type dropdown field on product details sections");
    }

    /**
     * It retrieves the selected product customer type on phone order page
     *
     * @return If the selected product customer type is displayed, it returns the value; otherwise it returns empty string
     * @Description This function retrieves the selected product customer type on phone order page
     * @Author: Balaji N
     */
    public String get_selected_ProdCustType() {
        return get_selected_option(productcustomerType_OnPhoneOrderPage, "product customer type value on phone order page");
    }

    public void Enter_GiftCardNumber_OnPhoneOrderPage(String giftcardnumber) {
        HighlightElement(giftCardNumber_PhoneOrder_PaymentSection);
        giftCardNumber_PhoneOrder_PaymentSection.clear();
        clickAndType(giftCardNumber_PhoneOrder_PaymentSection, giftcardnumber);
    }

    public String get_GiftCardNumber_OnPhoneOrderPage() {
        HighlightElement(giftCardNumber_PhoneOrder_PaymentSection);
        return giftCardNumber_PhoneOrder_PaymentSection.getAttribute("value");
    }


    public String get_GiftCardDisplayedCustName_OnPhoneOrderPage() {
        giftCardNumber_PhoneOrder_PaymentSection.sendKeys(Keys.TAB);
        HighlightElement(giftCardCustName_PhoneOrder_PaymentSection);
        return giftCardCustName_PhoneOrder_PaymentSection.getAttribute("value");
    }

    public String get_GiftCardCurrentBalanceDisplayed_OnPhoneOrderPage() {
        HighlightElement(giftCardCurrentBalance_PhoneOrder_PaymentSection);
        return giftCardCurrentBalance_PhoneOrder_PaymentSection.getAttribute("value");
    }

    public String get_GiftCardDisplayed_Amount_OnPhoneOrderPage() {
        HighlightElement(giftCardAmount_PhoneOrder_PaymentSection);
        return giftCardAmount_PhoneOrder_PaymentSection.getAttribute("value");
    }

    public String Validate_GiftCardAmountCalculation() {
        double currentbalance = Double.parseDouble(get_GiftCardCurrentBalanceDisplayed_OnPhoneOrderPage());
        double amount = Double.parseDouble(getGrandTotalAmount());
        double current_balance = currentbalance - amount;
        System.out.println("Current Balance);" + current_balance);
        return Double.toString(current_balance);
    }

    /**
     * Enters the first name on wire info in the phone order page
     *
     * @param firstname The provided first name to be entered
     * @Description: This method double-clicks on the 'First Name' field and enters the provided value.
     * @Author Balaji N
     */
    public void Enter_WireIn_Fname(String firstname) {
        wireinFirstName_OnPhoneOrderPage.clear();
        ClickAndType(wireinFirstName_OnPhoneOrderPage, firstname, "First Name textbox field on wire in delivery type in the phone order page");
    }

    /**
     * It retrieves the entered first name on wire info in the phone order page
     *
     * @return If the entered value is displayed it returns the value of firstname field, otherwise it returns null
     * @Description: This method retrieves the entered first name on wire info in the phone order page
     * @Author Balaji N
     */
    public String get_Entered_WireIn_Fname() {
        return getElementAttribute(wireinFirstName_OnPhoneOrderPage, "First Name value on wire in delivery type in the phone order page");
    }

    /**
     * Enter the last name on wire info in the phone order page
     *
     * @param lastname The provided last name to be entered
     * @Description: This method double-clicks on the 'Last Name' field and enters the provided value
     * @author Balaji N
     */
    public void Enter_WireIn_Lname(String lastname) {
        wireinLastName_OnPhoneOrderPage.clear();
        ClickAndType(wireinLastName_OnPhoneOrderPage, lastname, "Last Name textbox field on wire in delivery type in the phone order page");
    }

    /**
     * It retrieves the entered last name on wire info in the phone order page
     *
     * @return If the entered value is displayed it returns the value of lastname field, otherwise it returns null
     * @Description: This method retrieves the entered last name on wire info in the phone order page
     * @author Balaji N
     */
    public String get_Entered_WireIn_Lname() {
        return getElementAttribute(wireinLastName_OnPhoneOrderPage, "Last Name value on wire in delivery type in the phone order page");
    }

    /**
     * Enter the shop code on wire info in the phone order page
     *
     * @param shopcode The provided shop code to be entered
     * @Description: This method double-clicks on the 'Shop Code' field and enters the provided value
     * @author Balaji N
     */
    public void Enter_WireIn_ShopCode(String shopcode) {
        wireinShopCode_OnPhoneOrderPage.clear();
        ClickAndType(wireinShopCode_OnPhoneOrderPage, shopcode, "Shop code textbox field on wire in delivery type in the phone order page");
    }

    /**
     * It retrieves the entered shop code on wire info in the phone order page
     *
     * @return If shop code is displayed it returns the value of shopcode field, otherwise it returns null
     * @Description: This method retrieves the entered shop code on wire info in the phone order page
     * @author Balaji N
     */
    public String get_Entered_WireIn_ShopCode() {
        return getElementAttribute(wireinShopCode_OnPhoneOrderPage, "Shop code value on wire in delivery type in the phone order page");
    }

    /**
     * Enter the shop name on wire info in the phone order page
     *
     * @param shopcode The provided shop name to be entered
     * @Description: This method clicks on the 'Shop Name' field and enters the provided value
     * @author Balaji N
     */
    public void Enter_WireIn_ShopName(String shopcode) {
        wireinShopName_OnPhoneOrderPage.clear();
        ClickAndType(wireinShopName_OnPhoneOrderPage, shopcode, "Shop name textbox field on wire in delivery type in the phone order page");
    }

    /**
     * It retrieves the entered shop name on wire info in the phone order page
     *
     * @return If shop name is displayed it returns the value of shopname field, otherwise it returns null
     * @Description This method retrieves the entered shop name on wire info in the phone order page
     * @author Balaji N
     */
    public String get_Entered_WireIn_ShopName() {
        return getElementAttribute(wireinShopName_OnPhoneOrderPage, "wire in shop name value on wire in delivery type in the phone order page");
    }

    /**
     * Selects the wire in method from the drop down field on phone order page
     *
     * @param wireinmethod The provided wire in method to be selected from the drop down
     * @Description This method using the reusable method of dropDown perform to select the wire in method from the drop down field on phone order page
     * @author Balaji N
     */
    public void Select_WireInMethod(String wireinmethod) {
        drop_Down(wireinMethods_OnPhoneOrderPage, wireinmethod, "VisibleText", "Wire in method dropdown on phone order page");
    }

    /**
     * It retrieves the displayed wire in method on phone order page from the dropdown
     *
     * @return If the wire in method is displayed it returns the value of wireinmethod field, otherwise it returns null
     * @Description This method retrieves the displayed wire in method on phone order page from the dropdown
     * @author Balaji N
     */
    public String get_Selected_WireInMethod() {
        return get_selected_option(wireinMethods_OnPhoneOrderPage, "Wire in method value on phone order page");
    }

    /**
     * Enters the order number on wire info in the phone order page
     *
     * @param ordernumber The provided order number to be entered
     * @Description This method clicks on the 'Order Number' field and enters the provided value
     * @author Balaji N
     */
    public void Enter_WireIn_OrderNumber(String ordernumber) {
        wireinOrderNum_OnPhoneOrderPage.clear();
        ClickAndType(wireinOrderNum_OnPhoneOrderPage, ordernumber, "Order number textbox field on wire in delivery type in the phone order page");
    }

    /**
     * It retrieves the entered order number on wire info in the phone order page
     *
     * @return If order number is displayed it returns the value of ordernumber field, otherwise it returns null
     * @Description This method retrieves the entered order number on wire info in the phone order page
     * @author Balaji N
     */
    public String get_Entered_WireIn_OrderNumber() {
        return getElementAttribute(wireinOrderNum_OnPhoneOrderPage, "Order number value on wire in delivery type in the phone order page");
    }

    /**
     * Enter the phone number on wire info in the phone order page
     *
     * @param phonenumber The provided phone number to be entered
     * @Description This method clicks on the 'Phone Number' field and enters the provided value
     * @author Balaji N
     */
    public void Enter_WireIn_PhoneNumber(String phonenumber) {
        wireinPhone_OnPhoneOrderPage.clear();
        ClickAndType(wireinPhone_OnPhoneOrderPage, phonenumber, "Phone number textbox field on wire in delivery type in the phone order page");
    }

    /**
     * It retrieves the entered phone number on wire info in the phone order page
     *
     * @return If phone number is displayed it returns the value of phonenumber field, otherwise it returns null
     * @Description This method retrieves the entered phone number on wire info in the phone order page
     * @author Balaji N
     */
    public String get_Entered_WireIn_PhoneNumber() {
        return getElementAttribute(wireinPhone_OnPhoneOrderPage, "Phone number value on wire in delivery type in the phone order page");
    }

    /**
     * Enter the email on wire info in the phone order page
     *
     * @param email The provided email to be entered
     * @Description This method clicks on the 'Email' field and enters the provided value
     * @author Balaji N
     */
    public void Enter_WireIn_Email(String email) {
        wireinEmail_OnPhoneOrderPage.clear();
        ClickAndType(wireinEmail_OnPhoneOrderPage, email, "Email textbox field on wire in delivery type in the phone order page");
    }

    /**
     * It retrieves the entered email on wire info in the phone order page
     *
     * @return If email is displayed it returns the value of email field, otherwise it returns null
     * @Description This method retrieves the entered email on wire info in the phone order page
     * @author Balaji N
     */
    public String get_Entered_WireIn_Email() {
        return getElementAttribute(wireinEmail_OnPhoneOrderPage, "Email value on wire in delivery type in the phone order page");
    }

    /**
     * Enter the shop address on wire info in the phone order page
     *
     * @param shopaddress If shop address is displayed it returns the value of shopaddress field, otherwise it returns null
     * @Description This method clicks on the 'Shop Address' field and enters the provided value
     * @author Balaji N
     */
    public void Enter_WireIn_ShopAddress(String shopaddress) {
        wireinShopAddress_OnPhoneOrderPage.clear();
        ClickAndType(wireinShopAddress_OnPhoneOrderPage, shopaddress, "Shop address textbox field on wire in delivery type in the phone order page");
    }

    /**
     * It retrieves the entered shop address on wire info in the phone order page
     *
     * @return If shop address is displayed it returns the value of shopaddress field, otherwise it returns null
     * @Description This method retrieves the entered shop address on wire info in the phone order page
     * @author Balaji N
     */
    public String get_Entered_WireIn_ShopAddress() {
        return getElementAttribute(wireinShopAddress_OnPhoneOrderPage, "Shop address value on wire in delivery type in the phone order page");
    }

    /**
     * Enter the comments on wire info in the phone order page
     *
     * @param comments If comments is displayed it returns the value of comments field, otherwise it returns null
     * @Description This method clicks on the 'Comments' field and enters the provided value
     * @author Balaji N
     */
    public void Enter_WireIn_Comments(String comments) {
        wireinComments_OnPhoneOrderPage.clear();
        ClickAndType(wireinComments_OnPhoneOrderPage, comments, "Comments textbox field on wire in delivery type in the phone order page");
    }

    /**
     * It retrieves the entered comments on wire info in the phone order page
     *
     * @return If comments is displayed it returns the value of comments field, otherwise it returns null
     * @Description This method retrieves the entered comments on wire info in the phone order page
     * @author Balaji N
     */
    public String get_Entered_WireIn_Comments() {
        return getElementAttribute(wireinComments_OnPhoneOrderPage, "Comments value on wire in delivery type in the phone order page");
    }

    /**
     * Select the wire out payment method on payment section
     *
     * @param paymentmethod
     * @Author Balaji N
     */
    public void Select_WireOut_PaymentMethod(String paymentmethod) {
        drop_Down(wireoutMethods_OnPhoneOrderPage, paymentmethod, "VisibleText", "Wireout payment method dropdown on phone order page");
    }

    /**
     * Get the selected wireout option in payment method
     *
     * @return
     */
    public String get_Selected_WireOut_PaymentMethod() {
        return get_selected_option(wireoutMethods_OnPhoneOrderPage, "Wireout payment method dropdown value on phone order page");
    }

    public boolean is_WireOut_Option_PaymentMethod_Displayed_OnPhoneOrderPage(String expectedoption) {
        Select select = new Select(wireoutMethods_OnPhoneOrderPage);
        for (WebElement option : select.getOptions()) {
            if (option.getText().trim().equalsIgnoreCase(expectedoption)) {
                return true;
            }
        }
        return false;
    }

    public boolean is_WireOut_Option_PaymentMethod_NotDisplayed_OnPhoneOrderPage(String expectedoption) {
        try {
            click(wireoutMethods_OnPhoneOrderPage, "Wireout payment method dropdown on phone order page");
            Select select = new Select(wireoutMethods_OnPhoneOrderPage);
            for (WebElement option : select.getOptions()) {
                if (option.getText().trim().equalsIgnoreCase(expectedoption)) {
                    return false;
                }
            }
            return true;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    /**
     * Enter the wireout florist zipcode by the given value
     *
     * @param florist The given florist value to be entered
     * @Author Balaji N
     */
    public void Enter_WireoutFlorist(String florist) {
        try {
            click(search_icon_On_WireOutInfo, "Search Icon choose florist field on wire out as delivery type");
            isElementDisplayed(wireout_chooseflorist_popup, "Choose florist popup wireout as delivery type in the phone order page");
            delayWithGivenTime(2000);
            Search_and_Select_with_Zipcode_on_ChooseFlorist_Popup(florist);
            delayWithGivenTime(2000);
            if (verify_based_on_zipcode_searchresult_listofflorist_is_Appears_on_chooseflorist_popup()) {
                Click_chooseflorist_popup_displayed_first_search_result();
            } else {
                System.err.println("Entered Florist zipcode " + florist + "- Florist is not found");
            }


        } catch (Exception e) {
            printError(search_icon_On_WireOutInfo, "Choose Wireout florist Search Icon field on phone order page", "generic exception", e);
        }
    }

    /**
     * It retrieves the displayed wireout florist zipcode
     *
     * @return If florist zipcode displayed it return the value of string otherwise it returns null
     * @Author Balaji N
     */
    public String get_displayed_wireout_florist_zipcode() {
        return getElementAttribute(wireoutFlorist_OnPhoneOrderPage, "Wireout florist zipcode value on phone order page");
    }

    public String get_Amount_wireout_paymentsection() {
        String amountStr = wireoutAmt_OnPhoneOrderPage.getText();
        String amountValue = amountStr.replaceAll("[^0-9.]", "");
        return amountValue;
    }

    /**
     * Clicks the search icon on the wire out info choose florist field
     *
     * @Author Balaji N
     */
    public void Click_SearchIcon_OnWireOut_Info() {
        click(search_icon_On_WireOutInfo);
    }

    /**
     * It validates whether the choose florist popup header is displayed are not
     *
     * @return It returns true if popup header is displayed or else returns false
     * @author Balaji N
     */
    public boolean verify_chooseFlorist_popup_isDisplayed() {
        return is_Element_Displayed(choose_florist_popup_header_WireOutInfo, "Choose florist popup header on wire out delivery type in the phone order page");
    }

    /**
     * Set the wireout payment amount dynamically what ever the payment displayed on phoneorder page
     *
     * @author Balaji N
     */
    public void set_Amount_wireout_paymentsection() {
        fluentWait(wireoutAmount_OnPhoneOrderPage);
        wireoutAmount_OnPhoneOrderPage.clear();
        ClickAndType(wireoutAmount_OnPhoneOrderPage, get_Amount_wireout_paymentsection(), "Wire out amount textbox field in payment section on phone order page");
    }

    /**
     * The function will clears the existing data and enters the zipcode
     *
     * @param zipcode The given data to be entered
     * @Author Balaji N
     */
    public void Search_and_Select_with_Zipcode_on_ChooseFlorist_Popup(String zipcode) {
        ClickAndType(wireout_chooseflorist_popup_searchbox, zipcode, "Search box field on choose florist popup wireout delivery type in the phone order page");
        wireout_chooseflorist_popup_searchbox.sendKeys(Keys.ENTER);
    }

    /**
     * Clicks the search button on choose florist popup wireout delivery type
     *
     * @Author Balaji N
     */
    public void Click_SearchButton_on_ChooseFlorist_Popup() {
        js_Click(wireout_chooseflorist_popup_search_button, "search button on choose florist popup wireout delivery type in the phone order page");
    }

    /**
     * Validate whether the based on zipcode search result it displayed the list of florist available to take order on the zipcode
     *
     * @return If list of florist shop is displayed it returns true or else returns false
     * @Author Balaji N
     */
    public boolean verify_based_on_zipcode_searchresult_listofflorist_is_Appears_on_chooseflorist_popup() {
        List<WebElement> flowerShopList = wireout_zipcode_search_result_florists;
        // Check if the list contains elements and if at least one element is displayed
        if (!flowerShopList.isEmpty()) {
            for (WebElement shop : flowerShopList) {
                // HighlightElement(shop);
                if (is_Element_Displayed(shop, "Florist shop displayed on choose florist popup wireout delivery type in the phone order page")) {
                    return true; // Return true if any element is displayed
                }
            }
        }
        return false;

    }

    /**
     * Clicks the first florist shop displayed after search with zipcode
     *
     * @Author Balaji N
     */
    public void Click_chooseflorist_popup_displayed_first_search_result() {
        js_Click(wireout_chooseflorist_popup_displayed_firstsearchresult, "first search result on choose florist popup wireout delivery type in the phone order page");
    }

    /**
     * Clicks the add gift card link on phone order page
     *
     * @Description - This method clicks the add gift card link on phone order page
     * @author Balaji N
     */
    public void Click_AddGiftCardIcon_OnPhoneorderPage() {
        click(addgiftcardIcon_OnPhoneOrderPage, "Add gift card icon on phone order page");
    }

    /**
     * Validates whether the gift card popup is displayed on phone order page
     *
     * @return If gift card popup is displayed it returns true, otherwise it returns false
     * @author Balaji N
     */
    public boolean Verify_GiftCardPopup_OnPhoneorderPage() {
        HighlightElement(giftcardPopupTitle_OnPhoneOrderPage);
        boolean isGiftCardPopupDisplayed = giftcardPopupTitle_OnPhoneOrderPage.isDisplayed();
        return isGiftCardPopupDisplayed;
    }

    public void Click_GiftCardInstantDenomination1() {
        jsClick(Instantdenomination1_OnGiftcardPopup_OnPhoneOrderPage);
    }

    public void Click_GiftCardInstantDenomination7() {
        jsClick(Instantdenomination7_OnGiftcardPopup_OnPhoneOrderPage);
    }

    public void Enter_GiftCardAmount_onGiftCardPopup(String giftcardamount) {
        giftcardAmountTextboxField.clear();
        clickAndType(giftcardAmountTextboxField, giftcardamount);
    }

    public String get_giftcardamountTextboxField() {
        HighlightElement(giftcardAmountTextboxField);
        String giftcardamount = giftcardAmountTextboxField.getAttribute("value");
        return giftcardamount;
    }

    public String get_giftCardTotalAmount() {
        String giftcardtotalamount = giftcardTotalDisabledTextboxField.getAttribute("value");
        return giftcardtotalamount;
    }

    public boolean Verify_GiftCardTotalAmount_ISDisabled() {
        boolean giftcardtotalamount = giftcardTotalDisabledTextboxField.isDisplayed();
        System.out.println("Gift card total amount is disabled : " + giftcardtotalamount);
        return giftcardtotalamount;
    }

    public void Enter_GiftcardProcessingFee(String giftcardprocessingfee) {
        giftcardProcessingFeeTextboxField.clear();
        clickAndType(giftcardProcessingFeeTextboxField, giftcardprocessingfee);
    }

    public String get_GiftcardprocessingfeeTextboxField() {
        String giftcardprocessingfee = giftcardProcessingFeeTextboxField.getAttribute("value");
        System.out.println("Gift card processing fee : " + giftcardprocessingfee);
        return giftcardprocessingfee;
    }

    public void Enter_GiftCardNumberTextBoxField(String giftcardnumber) {
        giftcardCardNumberTextboxField.clear();
        clickAndType(giftcardCardNumberTextboxField, giftcardnumber);
    }

    public String get_GiftCardNumberTextBoxField() {
        String giftcardnumber = giftcardCardNumberTextboxField.getAttribute("value");
        System.out.println("Gift card number : " + giftcardnumber);
        return giftcardnumber;
    }

    public void Enter_CustomerName_OnGiftCardPopup(String customername, String customerfullname) {
        giftcardCustomerNameTextboxField.clear();
        clickAndType(giftcardCustomerNameTextboxField, customername);
        delayWithGivenTime(2000);
        for (WebElement customer : ListOfGiftCardName_Autosuggestions_OnGiftCardpopup) {
            if (customer.getText().equals(customerfullname)) {
                jsClick(customer);
                break;
            }
        }
    }

    public String get_GiftCardEnteredCustomerName() {
        String giftcardcustomername = giftcardCustomerNameTextboxField.getAttribute("value");
        return giftcardcustomername;
    }

    public void Click_GiftCardPopupCloseIcon() {
        jsClick(giftcardPopupCloseIcon);
    }

    public void Click_GiftCardPopupSubmitButton() {
        jsClick(submitbutton_OnGiftCardPopup);
    }

    public void Click_EditIcon_OnGiftCardPopup() {
        jsClick(editIcon_OnGiftCardPopup);
    }

    public boolean Verify_EditGiftCardDenomination_Popup_IsDisplayed() {
        HighlightElement(editgiftcardDenominationPopup);
        boolean EditgiftcardDenominationPopup = editgiftcardDenominationPopup.isDisplayed();
        return EditgiftcardDenominationPopup;
    }

    public void Enter_Denomination1_OnEditDenominationGiftCardPopup(String denomination1) {
        denomination1_OnEditDenomiantionGiftCardPopup.clear();
        clickAndType(denomination1_OnEditDenomiantionGiftCardPopup, denomination1);
    }

    public void Enter_Denomination2_OnEditDenominationGiftCardPopup(String denomination2) {
        denomination2_OnEditDenomiantionGiftCardPopup.clear();
        clickAndType(denomination2_OnEditDenomiantionGiftCardPopup, denomination2);
    }

    public void Enter_Denomination3_OnEditDenominationGiftCardPopup(String denomination3) {
        denomination3_OnEditDenomiantionGiftCardPopup.clear();
        clickAndType(denomination3_OnEditDenomiantionGiftCardPopup, denomination3);
    }

    public void Enter_Denomination4_OnEditDenominationGiftCardPopup(String denomination4) {
        denomination4_OnEditDenomiantionGiftCardPopup.clear();
        clickAndType(denomination4_OnEditDenomiantionGiftCardPopup, denomination4);
    }

    public void Enter_Denomination5_OnEditDenominationGiftCardPopup(String denomination5) {
        denomination5_OnEditDenomiantionGiftCardPopup.clear();
        clickAndType(denomination5_OnEditDenomiantionGiftCardPopup, denomination5);
    }

    public void Enter_Denomination6_OnEditDenominationGiftCardPopup(String denomination6) {
        denomination6_OnEditDenomiantionGiftCardPopup.clear();
        clickAndType(denomination6_OnEditDenomiantionGiftCardPopup, denomination6);
    }

    public void Enter_Denomination7_OnEditDenominationGiftCardPopup(String denomination7) {
        denomination7_OnEditDenomiantionGiftCardPopup.clear();
        clickAndType(denomination7_OnEditDenomiantionGiftCardPopup, denomination7);
    }

    public void Enter_Denomination8_OnEditDenominationGiftCardPopup(String denomination8) {
        denomination8_OnEditDenomiantionGiftCardPopup.clear();
        clickAndType(denomination8_OnEditDenomiantionGiftCardPopup, denomination8);
    }

    public void Enter_Denomination9_OnEditDenominationGiftCardPopup(String denomination9) {
        denomination9_OnEditDenomiantionGiftCardPopup.clear();
        clickAndType(denomination9_OnEditDenomiantionGiftCardPopup, denomination9);
    }

    public void Enter_Denomination10_OnEditDenominationGiftCardPopup(String denomination10) {
        denomination10_OnEditDenomiantionGiftCardPopup.clear();
        clickAndType(denomination10_OnEditDenomiantionGiftCardPopup, denomination10);
    }

    public void Click_CancelButton_OnEditDenominationGiftCardPopup() {
        jsClick(cancelbutton_OnEditDenomiantionGiftCardPopup);
    }

    public void Click_SubmitButton_OnEditDenominationGiftCardPopup() {
        jsClick(savebutton_OnEditDenomiantionGiftCardPopup);
    }

    public String get_Entered_Denomination1() {
        HighlightElement(denomination1_OnEditDenomiantionGiftCardPopup);
        return denomination1_OnEditDenomiantionGiftCardPopup.getAttribute("value");
    }

    public String get_Entered_Denomination2() {
        HighlightElement(denomination2_OnEditDenomiantionGiftCardPopup);
        return denomination2_OnEditDenomiantionGiftCardPopup.getAttribute("value");
    }

    public String get_Entered_Denomination3() {
        HighlightElement(denomination3_OnEditDenomiantionGiftCardPopup);
        return denomination3_OnEditDenomiantionGiftCardPopup.getAttribute("value");
    }

    public String get_Entered_Denomination4() {
        HighlightElement(denomination4_OnEditDenomiantionGiftCardPopup);
        return denomination4_OnEditDenomiantionGiftCardPopup.getAttribute("value");
    }

    public String get_Entered_Denomination5() {
        HighlightElement(denomination5_OnEditDenomiantionGiftCardPopup);
        return denomination5_OnEditDenomiantionGiftCardPopup.getAttribute("value");
    }

    public String get_Entered_Denomination6() {
        HighlightElement(denomination6_OnEditDenomiantionGiftCardPopup);
        return denomination6_OnEditDenomiantionGiftCardPopup.getAttribute("value");
    }

    public String get_Entered_Denomination7() {
        HighlightElement(denomination7_OnEditDenomiantionGiftCardPopup);
        return denomination7_OnEditDenomiantionGiftCardPopup.getAttribute("value");
    }

    public String get_Entered_Denomination8() {
        HighlightElement(denomination8_OnEditDenomiantionGiftCardPopup);
        return denomination8_OnEditDenomiantionGiftCardPopup.getAttribute("value");
    }

    public String get_Entered_Denomination9() {
        HighlightElement(denomination9_OnEditDenomiantionGiftCardPopup);
        return denomination9_OnEditDenomiantionGiftCardPopup.getAttribute("value");
    }

    public String get_Entered_Denomination10() {
        HighlightElement(denomination10_OnEditDenomiantionGiftCardPopup);
        return denomination10_OnEditDenomiantionGiftCardPopup.getAttribute("value");
    }

    public String get_InstantDenomination1() {
        HighlightElement(Instantdenomination1_OnGiftcardPopup_OnPhoneOrderPage);
        String instantdenomination1 = Instantdenomination1_OnGiftcardPopup_OnPhoneOrderPage.getText();
        instantdenomination1 = instantdenomination1.replaceAll("$", "");
        return instantdenomination1;
    }

    public String get_InstantDenomination2() {
        HighlightElement(Instantdenomination2_OnGiftcardPopup_OnPhoneOrderPage);
        String instantdenomination2 = Instantdenomination2_OnGiftcardPopup_OnPhoneOrderPage.getText();
        instantdenomination2 = instantdenomination2.replaceAll("$", "");
        return instantdenomination2;
    }

    public String get_InstantDenomination3() {
        HighlightElement(Instantdenomination3_OnGiftcardPopup_OnPhoneOrderPage);
        String instantdenomination3 = Instantdenomination3_OnGiftcardPopup_OnPhoneOrderPage.getText();
        instantdenomination3 = instantdenomination3.replaceAll("$", "");
        return instantdenomination3;
    }

    public String get_InstantDenomination4() {
        HighlightElement(Instantdenomination4_OnGiftcardPopup_OnPhoneOrderPage);
        String instantdenomination4 = Instantdenomination4_OnGiftcardPopup_OnPhoneOrderPage.getText();
        instantdenomination4 = instantdenomination4.replaceAll("$", "");
        return instantdenomination4;
    }

    public String get_InstantDenomination5() {
        HighlightElement(Instantdenomination5_OnGiftcardPopup_OnPhoneOrderPage);
        String instantdenomination5 = Instantdenomination5_OnGiftcardPopup_OnPhoneOrderPage.getText();
        instantdenomination5 = instantdenomination5.replaceAll("$", "");
        return instantdenomination5;
    }

    public String get_InstantDenomination6() {
        HighlightElement(Instantdenomination6_OnGiftcardPopup_OnPhoneOrderPage);
        String instantdenomination6 = Instantdenomination6_OnGiftcardPopup_OnPhoneOrderPage.getText();
        instantdenomination6 = instantdenomination6.replaceAll("$", "");
        return instantdenomination6;
    }

    public String get_InstantDenomination7() {
        HighlightElement(Instantdenomination7_OnGiftcardPopup_OnPhoneOrderPage);
        String instantdenomination7 = Instantdenomination7_OnGiftcardPopup_OnPhoneOrderPage.getText();
        instantdenomination7 = instantdenomination7.replaceAll("$", "");
        return instantdenomination7;
    }

    public String get_InstantDenomination8() {
        HighlightElement(Instantdenomination8_OnGiftcardPopup_OnPhoneOrderPage);
        String instantdenomination8 = Instantdenomination8_OnGiftcardPopup_OnPhoneOrderPage.getText();
        instantdenomination8 = instantdenomination8.replaceAll("$", "");
        return instantdenomination8;
    }

    public String get_InstantDenomination9() {
        HighlightElement(Instantdenomination9_OnGiftcardPopup_OnPhoneOrderPage);
        String instantdenomination9 = Instantdenomination9_OnGiftcardPopup_OnPhoneOrderPage.getText();
        instantdenomination9 = instantdenomination9.replaceAll("$", "");
        return instantdenomination9;
    }

    public String get_InstantDenomination10() {
        HighlightElement(Instantdenomination10_OnGiftcardPopup_OnPhoneOrderPage);
        String instantdenomination10 = Instantdenomination10_OnGiftcardPopup_OnPhoneOrderPage.getText();
        instantdenomination10 = instantdenomination10.replaceAll("$", "");
        return instantdenomination10;
    }

    /**
     * Clicks on the row 1 taxable checkbox on product details section
     *
     * @Author Balaji N
     */
    public void ClickOn_Row1_Item_ProdDetails_TaxableCheckBox() {
        js_Click(item_row1_taxableCheckbox_OnProductDetailsSection, "Row 1 Taxable checkbox on the product details section");
    }

    /**
     * verify the row 1 taxable checkbox on product details section
     *
     * @return If the checkbox is checked it returns true, else it returns false
     * @Author Balaji N
     */
    public boolean Verify_Item_Row1_ProdDetails_TaxableCheckBox_IsChecked() {
        boolean isChecked = item_row1_taxableCheckbox_OnProductDetailsSection.isEnabled();
        return isChecked;
    }

    /**
     * It retrieves the tax amount value from the payment section
     *
     * @return If the tax amount is displayed it returns the tax amount value, else it return null
     * @Author Balaji N
     */
    public String get_TaxAmount_PaymentSection() {
        String taxamount = get_element_attribute_with_trim(taxAmt_PhoneOrder_PaymentSection, "Tax amount on payment section");
        System.out.println("taxamount on application: " + taxamount);
        return taxamount;
    }

    /**
     * It retrieves the actual calculated grand total value from the payment section
     *
     * @return If the grand total is displayed it returns the grand total value, else it return null
     * @Author Balaji N
     */
    public String validate_grandTotal_OnPaymentSection() {
        double subTotal = Double.parseDouble(subTotalOnPhoneOrderPage.getAttribute("value").replace("$", "").trim());

        double discountAmount = Double.parseDouble(discountAmt_PhoneOrder_PaymentSection.getAttribute("value").replace("$", "").trim());
        double deliveryfee = Double.parseDouble(deliveryFee_PhoneOrder_PaymentSection.getAttribute("value").replace("$", "").trim());
        double relayFee = Double.parseDouble(relayFee_PhoneOrder_PaymentSection.getAttribute("value").replace("$", "").trim());
        double taxFee = Double.parseDouble(taxAmt_PhoneOrder_PaymentSection.getAttribute("value").replace("$", "").trim());

        System.out.println("subTotal: " + subTotal);
        System.out.println("deliveryfee: " + deliveryfee);
        System.out.println("relayFee: " + relayFee);
        System.out.println("taxFee: " + taxFee);
        System.out.println("discountAmount: " + discountAmount);
        double expectedGrandTotal = (subTotal + deliveryfee + relayFee + taxFee) - discountAmount;

        // Format the numbers to two decimal places
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedExpectedGrandTotal = df.format(expectedGrandTotal);
        System.out.println("Calculated Expected Grand Total: " + formattedExpectedGrandTotal);
        return formattedExpectedGrandTotal;
    }

    /**
     * Retrieves the subtotal amount displayed on the Phone Order page.
     * The value is extracted from the input field, formatted to two decimal places, and returned as a string.
     *
     * @return The formatted subtotal amount as a string.
     * @throws NoSuchElementException         If the element {@code subTotalOnPhoneOrderPage} is not found.
     * @throws StaleElementReferenceException If the element becomes stale due to a page reload or DOM update.
     * @throws NullPointerException           If {@code subTotalOnPhoneOrderPage} is null.
     * @throws NumberFormatException          If the extracted value cannot be parsed as a double.
     * @Author Balaji N
     */
    public String get_SubtotalAmount_OnPhoneOrderPage() {
        try {
            if (subTotalOnPhoneOrderPage == null) {
                throw new NullPointerException("The subtotal element is null on payment section");
            }

            String rawValue = subTotalOnPhoneOrderPage.getAttribute("value");
            if (rawValue == null || rawValue.trim().isEmpty()) {
                throw new NoSuchElementException("Subtotal value is not present in the element is null on payment section.");
            }

            String cleanedValue = rawValue.replace("$", "").trim();
            double subTotal = Double.parseDouble(cleanedValue);

            DecimalFormat df = new DecimalFormat("#.00");
            return df.format(subTotal);
        } catch (NoSuchElementException | StaleElementReferenceException | NullPointerException |
                 NumberFormatException e) {
            System.err.println("Exception occurred while retrieving subtotalis on payment section: " + e.getMessage());
            return "0.00"; // Default value in case of an error
        }
    }

    /**
     * It retrieves the actual calculation of tax type from phone order page
     *
     * @return It returns the actual calculation of tax type (we used tax type as QA TAX =  1% in tax type configuration settings)
     * @Description This function takes the sub total value and based on tax type calculates the tax value
     * @author Balaji N
     * @Last-Modified-By : Sakrateesh R
     */
    public String get_actual_calculation_taxtype() {
        double subTotal = Double.parseDouble(subTotalOnPhoneOrderPage.getAttribute("value").replace("$", "").trim());
        double discount = Double.parseDouble(discountAmt_PhoneOrder_PaymentSection.getAttribute("value").trim());
        double deliveryfee = Double.parseDouble(deliveryFee_PhoneOrder_PaymentSection.getAttribute("value").replace("$", "").trim());
        double relayFee = Double.parseDouble(relayFee_PhoneOrder_PaymentSection.getAttribute("value").replace("$", "").trim());

        long deliveryFee = Math.round(deliveryfee);
        System.out.println("Discount : " + discount);
        System.out.println("Subtotal : " + subTotal);
        System.out.println("Delivery Fee : " + deliveryFee);
        System.out.println("Relay Fee : " + relayFee);
        DecimalFormat df = new DecimalFormat("#.00");
        // subTotal - discount /100 Wrong calculation made...
        // Last updated by Balaji - 19/02/2025
        String taxvalue = df.format((subTotal + relayFee) / 100);
        System.out.println("================ Expected Calculated Tax Value subtotal - discount by 1 percent: " + taxvalue);
        return taxvalue;
    }

    public String get_Pickup_Expected_TaxAmount() {
        double subTotal = Double.parseDouble(subTotalOnPhoneOrderPage.getAttribute("value").replace("$", "").trim());
        DecimalFormat df = new DecimalFormat("#.00");
        String taxvalue = df.format((subTotal) / 100);
        return taxvalue;
    }


    /**
     * It retrieves the tax amount value from the order confirmation popup
     *
     * @return If the tax amount is displayed on the order confirmation popup it returns the tax amount; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_taxAmountOnOrderconfirmation_Popup() {
        return getElementText(taxamount_OrderConfirmationPopupOnPhoneOrderPage, "Tax Amount label on Order Confirmation Popup");
    }

    /**
     * Verifies if the recipe icon is displayed on the product details row 1
     *
     * @return If the recipe icon is displayed on the product details row 1 it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_ProductDetails_Row1_Recipe() {
        return is_Element_Displayed(prod_details_RecipeIcon1, "Product Details Row1 Recipe Icon");
    }

    /**
     * Clicks the recipe icon on the product details row 1
     *
     * @Author Balaji N
     */
    public void Click_ProductDetails_Row1_Recipe() {
//        prod_details_RecipeIcon1.click();
        click(prod_details_RecipeIcon1, "Reciepe Icon on product details section - Row 1");
    }

    /**
     * Verifies if the create recipe popup is displayed
     *
     * @return If the create recipe popup is displayed it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_CreateRecipe_Popup() {
        isElementDisplayed(create_recipe_popup, "Create Recipe Popup for the row 1 product details");
        return is_Element_Displayed(CreateRecipe_Popup, "Create Recipe Popup");
    }

    /**
     * Verify whether the save recipe button is displayed
     *
     * @return
     * @Author Balaji N
     */
    public boolean Is_Save_Recipe_Button_Displayed() {
        return is_Element_Displayed(SaveRecipie_button, "Save Recipe button on Create Recipie Popup");
    }

    /**
     * Clicks the save recipe button on the create recipe popup
     *
     * @Author Balaji N
     */
    public void Click_SaveRecipe_Button_On_CreateRecipePopup() {
        js_Click(SaveRecipie_button, "Save Recipe button on Create Recipie Popup");
    }

    /**
     * Clicks the save button on the create recipe popup
     *
     * @Author Balaji N
     */
    public void Click_Recipe_SaveBtn() {
        if (isElementDisplayed(SaveButton_RecipePopup, "Save Button on Recipie Popup")) {
            js_Click(SaveButton_RecipePopup, "Save Button on Recipie Popup");
        } else {
            Click_SaveRecipe_Button_On_CreateRecipePopup();
        }

    }

    /**
     * Clicks the save as button on the recipe save as popup
     *
     * @Author Balaji N
     */
    public void Click_SaveAsOn_RecipePopup() {
        js_Click(SaveAsButton_RecipeSaveAsPopup, "Save As button on Recipie save as Popup");
    }

    /**
     * Clicks the save button on the recipe save as popup
     *
     * @Author Balaji N
     */
    public void Click_SaveOption_RecipePopup() {
        if (isElementDisplayed(SaveButton_RecipeSaveAsPopup, "Save Option on Recipie save as Popup") == true) {
            js_Click(SaveButton_RecipeSaveAsPopup, "Save button on Recipie save as Popup");
        } else {
            System.out.println("Save button is not displayed");
        }
    }

    /**
     * Verify the save as popup is displayed
     *
     * @return If the save as popup is displayed then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_SaveAsPopup_IsAppears() {
        return is_Element_Displayed(SaveAsRecipe_Popup, "Save As Recipie Popup");
    }

    /**
     * Enters the new recipe name on the save as recipe popup
     *
     * @param recipeName
     * @Author Balaji N
     */
    public void Enter_Recipe_OnsaveAsPopup(String recipeName) {
        ClickAndType(SaveAsRecipeName_Textbox_Popup, recipeName, "New Recipe Name on textbox field on Save As Recipie Popup");
    }

    /**
     * Retrieves the entered new recipe name on the save as recipe popup
     *
     * @return If the new recipe name is entered on the save as recipe popup it returns the entered recipe name; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Entered_Recipe_OnsaveAsPopup() {
       /* HighlightElement(SaveAsRecipeName_Textbox_Popup);
        return SaveAsRecipeName_Textbox_Popup.getAttribute("value").trim();*/
        return get_element_attribute_with_trim(SaveAsRecipeName_Textbox_Popup, "New Recipe Name textbox field on Save As Recipie Popup");
    }

    /**
     * Clicks the save button on the save as recipe popup
     *
     * @Author Balaji N
     */
    public void Click_SaveButton_OnSaveAsRecipePopup() {
        js_Click(saveButton_OnSaveAsRecipe_Popup, "Save button on Save As Recipie Popup");
    }

    public void Search_Recipe_SearchBox(String recipeName) {
        ClickAndType(Recipe_SearchTextbox_Popup, recipeName, "Search Recipe textbox field on Create Recipe Popup");
    }

    /**
     * Verify whether the search recipe autosuggestion is displayed
     *
     * @return If the search recipe autosuggestion is displayed then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_SearchRecipeAutosuggestion() {
        delayWithGivenTime(2000);
        return isElementDisplayed(SearchRecipe_Autosuggestion, "Search Recipe Autosuggestion on create recipe popup");
    }

    public String Verify_RecipeName_Autosuggestion() {
        delayWithGivenTime(2000);
        HighlightElement(listOfRecipe_Autosuggestion.get(0));
        return (listOfRecipe_Autosuggestion.get(0).getText().trim());
    }

    /**
     * Search and select the recipe name on create recipe popup
     *
     * @Author Balaji N
     */
    public void Click_SearchAndSelectRecipeName() {
//        for (WebElement ele : listOfRecipe_Autosuggestion) {
//            if (ele.getText().contains(prop.getProperty("SearchRecipeName"))) {
//                js_Click(ele, "Search and Select Recipe Name");
//                break;
//            }
//        }

    }

    public void search_And_Select_Recipe(String recipeToSelect) {
        Search_Recipe_SearchBox(recipeToSelect);
        delayWithGivenTime(2000);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        try {
            // Wait until the autosuggestion is visible
            wait.until(ExpectedConditions.visibilityOf(SearchRecipe_Autosuggestion));

            // Wait until list is populated
            wait.until(driver -> !listOfRecipe_Autosuggestion.isEmpty());

            boolean isRecipeFound = false;

            for (WebElement recipe : listOfRecipe_Autosuggestion) {
                String recipeName = recipe.getText().trim();
                System.out.println("🔍 Found recipe: " + recipeName);

                if (recipeName.equalsIgnoreCase(recipeToSelect.trim())) {
                    HighlightElement(recipe);
                    recipe.click();
                    System.out.println("✅ Selected recipe: " + recipeName);
                    isRecipeFound = true;
                    break;
                }
            }

            if (!isRecipeFound) {
                System.out.println("❌ Recipe not found in auto-suggestions: " + recipeToSelect);
                throw new NoSuchElementException("Recipe not found in suggestions: " + recipeToSelect);
            }

        } catch (StaleElementReferenceException e) {
            System.err.println("⚠️ StaleElementReferenceException occurred, retrying...");
            search_And_Select_Recipe(recipeToSelect);  // retry once
        } catch (TimeoutException e) {
            System.err.println("❌ Auto-suggestion did not appear in time.");
            throw new RuntimeException("Auto-suggestion not visible or list not populated.");
        } catch (Exception e) {
            throw new RuntimeException("Error during selecting recipe: " + e.getMessage(), e);
        }
    }


    /**
     * Verify the recipe name row1 on the table grid is displayed
     *
     * @return If the recipe name row1 on the table grid is displayed then it returns the recipe name; otherwise, it returns null
     * @Author Balaji N
     */
    public String Verify_RecipeItemRow1_OnTableIsDisplayed() {
        return get_element_attribute_with_trim(RecipeName_Row1, "Recipe Name Row1");
    }

    /**
     * It retrieve the cost label at table grid row1 is displayed
     *
     * @return If the cost label at table grid row1 is displayed then it returns the cost label; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Cost_Label_Table_Grid_Row1() {
        return getElementText(cost_label_table_grid_Row1, "Cost Label at Table Grid Row1");
    }

    /**
     * It retrives the retail textbox at table grid row1 is displayed
     *
     * @return If the retail textbox at table grid row1 is displayed then it returns the retail textbox; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Retail_Textbox_Table_Grid_Row1() {
        return get_element_attribute(retail_textbox_table_grid_Row1, "Retail Textbox at Table Grid Row1");
    }

    /**
     * It retrives the quantity textbox at table grid row1 is displayed
     *
     * @return If the quantity textbox at table grid row1 is displayed then it returns the quantity textbox; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Quantity_textbox_table_grid_Row1() {
        return getElementAttribute(item_Quantity_textbox_table_grid_Row1, "Quantity Textbox at Table Grid Row1");
    }

    /**
     * It retrives the total cost label at table grid row1 is displayed
     *
     * @return If the total cost label is displayed it returns the total cost label value; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_total_cost_label_table_grid_Row1() {
        return getElementText(total_cost_label_table_grid_Row1, "Total Cost Label at Table Grid Row1");
    }

    /**
     * It retrives the total retail label at table grid row1 is displayed
     *
     * @return If the total retail label is displayed it returns the total retail label value; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_total_retail_label_table_grid_Row1() {
        return get_Element_Text(total_retail_label_table_grid_Row1, "Total Retail Label at Table Grid Row 1");
    }

    /**
     * Verify whether the recipe name row2 on the table grid is displayed or not
     *
     * @return If the recipe name row2 on the table grid is displayed then it returns the recipe name; otherwise, it returns null
     * @Author Balaji N
     */
    public String Verify_RecipeItemRow2_OnTableIsDisplayed() {
        HighlightElement(RecipeName_Row2);
        String row2Item = get_element_attribute_with_trim(RecipeName_Row2, "Recipe name Row2 on the table grid in the create recipe popup");//RecipeName_Row2.getAttribute("value").trim();
        return row2Item;
    }

    /**
     * Verify whether the recipe name row3 on the table grid is displayed or not
     *
     * @return If the recipe name row3 on the table grid is displayed then it returns the recipe name; otherwise, it returns null
     * @Author Balaji N
     */
    public String Verify_RecipeItemRow3_OnTableIsDisplayed() {
        String row3Item = get_element_attribute_with_trim(RecipeName_Row3, "Recipe name Row3 on the product table grid in the create recipe popup");
        return row3Item;
    }

    public String verify_Recipe_Item_IsDisplayed_On_TableGrid(String recipe_item) {
        for (int i = 0; i < list_0f_recipe_name.size(); i++) {
            if (list_0f_recipe_name.get(i).getAttribute("value").equals(recipe_item)) {
                return list_0f_recipe_name.get(i).getAttribute("value");
            }
        }
        return recipe_item;
    }


    /**
     * Clicks the recipe name textbox field on the create recipe popup
     *
     * @param recipeName
     * @Author Balaji N
     */
    public void Enter_RecipeNameTextboxField(String recipeName) {
        ClickAndType(RecipeName_OnRecipePopup, recipeName, "Recipe textbox field on the create recipe popup");
    }

    /**
     * It retrieve the entered recipe name on the recipe name textbox field on the create recipe popup
     *
     * @return If the recipe name is entered on the recipe name textbox field on the create recipe popup it returns the entered recipe name; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_recipename_OnRecipenameTextbox() {
        return get_element_attribute_with_trim(RecipeName_OnRecipePopup, "Recipe name textbox field value");
    }

    /**
     * Select the Item gallery on create recipe popup
     *
     * @param itemName
     * @Author Balaji N
     */
    public void Select_ItemGallery_onCreateRecipe(String itemName) {
        drop_Down(Item_gallerydropdown_Popup, itemName, "VisibleText", "Item gallery dropdown field on create recipe popup");
        wait_For_Page_To_Be_Stable(getDriver());
    }

    /**
     * It retrieves the selected item gallery on create recipe popup
     *
     * @return If the item gallery is selected on the create recipe popup it returns the selected item gallery; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Selected_ItemGallery_onCreateRecipe() {
        s = new Select(Item_gallerydropdown_Popup);
        return s.getFirstSelectedOption().getText().trim();
    }

    /**
     * Verify whether the item gallery list of options is displayed
     *
     * @return If the item gallery list of options is displayed then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_ImageGallery_ListOfOptionsIsDisplayed() {
        return is_Element_Displayed(Item_gallerydropdown_OptionsPopup, "Item gallery dropdown field on create recipe popup");
    }

    /**
     * Clicks the item gallery dropdown field on create recipe popup
     *
     * @param galleryname the name of the gallery to be clicked
     * @Author Balaji N
     */
    public void Click_ListOfItemGallery_IsDisplayed(String galleryname) {
        for (int i = 0; i < listOfItem_gallerydropdown_Popup.size(); i++) {
            if (listOfItem_gallerydropdown_Popup.get(i).getText().equalsIgnoreCase(prop.getProperty("SearchItemGallery"))) {
                click(listOfItem_gallerydropdown_Popup.get(i), "Item gallery dropdown field on create recipe popup");
                break;
            }
        }
    }

    public void select_ItemGallery_Create_Recipe_Popup(String itemgalleryname) {
        WebElement itemgallery = getDriver().findElement(By.xpath("//div[@id='itemComponentTypeFilterDataWrap']//p[text()='" + itemgalleryname + "']"));
        click(itemgallery, "Item gallery option on create recipe popup");
    }

    /**
     * Clicks the item gallery dropdown field on create recipe popup
     *
     * @param galleryname the name of the gallery to be clicked
     * @Author Balaji N
     */
    public void SearchAndSelect_ItemGallery(String galleryname, String itemname) {
        ClickAndType(Item_gallerydropdown_SearchTextbox_Popup, galleryname, "Searched item gallery search textbox field on Create recipe popup");
        Item_gallerydropdown_SearchTextbox_Popup.sendKeys(Keys.ENTER);
        delayWithGivenTime(3000);
        wait_For_Page_To_Be_Stable(getDriver());

        boolean itemFound = false;

        for (int i = 0; i < listOfItem_gallerydropdown_Popup.size(); i++) {
            if (listOfItem_gallerydropdown_Popup.get(i).getText().equalsIgnoreCase(itemname)) {
                click(listOfItem_gallerydropdown_Popup.get(i),
                        "Item gallery autosuggestion list on create recipe popup");
                itemFound = true;
                break;
            }
        }
        if (!itemFound) {
            throw new NoSuchElementException("Autosuggestion list not displayed for item: '"
                    + itemname + "' in field: 'Item gallery autosuggestion list on create recipe popup'");
        }
    }

    /**
     * It retrives the searched item gallery value on the search text box field
     *
     * @return If the item gallery is searched on the search text box field it returns the searched item gallery; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Searched_Item_Gallery() {
        return getElementAttribute(search_item_gallery_textbox_field, "Searched item gallery");
    }

    /**
     * Search and select the item gallery on Create recipie popup
     *
     * @param itemName
     * @Author Balaji N
     */
    public void SearchandSelect_ItemGallery(String itemName) {
        ClickAndType(Item_gallerydropdown_SearchTextbox_Popup, itemName, "Search text box field on item gallery dropdown");
        delayWithGivenTime(3000);
    }

    /**
     * This method is used only list of item recipie
     *
     * @return It returns list of item
     */
    public List<WebElement> refreshDropdownItems() {
        return getDriver().findElements(By.xpath("//div[@id='itemComponentTypeFilterDataWrap']//div//p"));
    }


    /**
     * Search and select the item gallery on Create recipie popup
     *
     * @param itemName - provided item name to be selected
     * @Description: This function Click and type the item name to search in search text box and clicks the expected item name.
     * @Author Balaji N
     */
    public void SearchandSelect_ItemGallery_CreateRecipePopup(String itemName) {
        try {
            // Clear the search box and type the item name
            ClickAndType(Item_gallerydropdown_SearchTextbox_Popup, itemName, "Search and Select the item gallery on Create recipie popup");
            RobotPressEnter();
            delayWithGivenTime(2000);

            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='itemComponentTypeFilterDataWrap']//div//p")));

            boolean itemClicked = false;
            int maxRetries = 3; // Maximum retries for stale element handling

            for (int retry = 0; retry < maxRetries; retry++) {
                try {
                    List<WebElement> dropdownItems = getDriver().findElements(By.xpath("//div[@id='itemComponentTypeFilterDataWrap']//div//p"));

                    for (WebElement element : dropdownItems) {
                        HighlightElement(element);

                        String elementText = element.getText().trim();
                        System.out.println("Dropdown item gallery text: " + elementText);

                        if (elementText.contains(prop.getProperty("RecipeItemGallery2"))) {
                            wait.until(ExpectedConditions.elementToBeClickable(element));
                            js_Click(element, "Item gallery autosugesstion list on create recipe popup");
                            System.out.println("Clicked on matching item: " + elementText);
                            itemClicked = true;
                            break;
                        }
                    }
                    if (itemClicked) break;

                } catch (StaleElementReferenceException e) {
                    System.out.println("Encountered stale element. Retrying (" + (retry + 1) + "/" + maxRetries + ")...");
                }
            }

            if (!itemClicked) {
                System.out.println("No matching items gallery found for: " + itemName);
            }
        } catch (Exception e) {
            System.err.println("Unable to search and select item on the item gallery: " + itemName + ". An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Verify whether the settings button is displayed on the create recipe popup
     *
     * @return If the settings button is displayed then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_CreateRecipe_Popup_SettingsIcon_IsDisplayed() {
        return is_Element_Displayed(MarkupSettings_Icon, "Settings button on the create recipe popup");
    }

    /**
     * Clicks the settings button on the create recipe popup
     *
     * @Author Balaji N
     */
    public void Click_CreateRecipePopup_Settings_Button() {
        js_Click(MarkupSettings_Icon, "Setting button on the create recipe popup");
        wait_For_Page_To_Be_Stable(getDriver());
    }

    /**
     * Verify whether the markup settings popup header is displayed or not
     *
     * @return If the markup settings popup header is displayed then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_MarkupSettings_Popup_IsDisplayed() {
        return is_Element_Displayed(MarkupSettings_Popup, "Markup settings popup header");
    }

    /**
     * This method is used to enable the markup toggle button on markup settings popup
     *
     * @Author Balaji N
     */
    public void Enable_Markup_Toggle_button() {
        By markup_percentage_toggle = By.xpath("//label[@for='chkApplyMarkupPerFlag']/child::span");
        WebElement element = getDriver().findElement(markup_percentage_toggle);

        // Get the background color style property
        String backgroundColor = element.getCssValue("background-color");
        System.out.println("Current background color of toggle is: " + backgroundColor);

        // Expected color when the toggle is off (grey)
        String offColor = "rgba(237, 85, 101, 1)";
        System.out.println("**** Actual Color *******" + element.getAttribute("style"));
        // Check if the toggle is off, then click to turn it on
        if (backgroundColor.contains(offColor)) {
            System.out.println("Toggle is OFF. Clicking to turn it ON.");
            // element.click();
            js_Click(element, "Markup percentage toogle button on markup settings popup");
        } else {
            System.out.println("Toggle is already ON. No action needed.");
        }


       /* if (ApplyMarkup_percentage_Togglebutton_Markupsettings_Popup != null &&
                ApplyMarkup_percentage_Togglebutton_Markupsettings_Popup.getAttribute("style") != null &&
                ApplyMarkup_percentage_Togglebutton_Markupsettings_Popup.getAttribute("style").contains("rgb(255, 0, 0,1)")) {
            js_Click(ApplyMarkup_percentage_Togglebutton_Markupsettings_Popup, "Apply Markup % toggle button on markup settings popup");
        }*/
    }

    /**
     * Enters the markup percentage value in markup percentage field1
     *
     * @param percentage The markup percentage value to be entered
     * @Author Balaji N
     */
    public void Enter_MarkupPercentage_Field1(String percentage) {
        ClickAndType(MarkupPercentage_Textbox_Field1, percentage, "Markup percentage field textbox 1 on markup settings popup");
    }

    /**
     * Returns the entered markup percentage value in markup percentage field1
     *
     * @return If the markup percentage value is entered then it returns the entered markup percentage value otherwise it returns null
     * @Author Balaji N
     */
    public String get_entered_markupPercentage_Field1() {
        return get_element_attribute_with_trim(MarkupPercentage_Textbox_Field1, "Markup percentage field textbox 1 on markup settings popup");
    }

    /**
     * Clicks the save button on the markup settings popup
     *
     * @Author Balaji N
     */
    public void Click_SaveBtn_Markup_SettingsPopup() {
        js_Click(save_Button_Markupsettings_Popup, "Save button on the markup settings popup");
    }

    /**
     * Clicks the close icon on the markup settings popup
     *
     * @Author Balaji N
     */
    public void Click_CloseIconButton_MarkupSettingsPopup() {
        js_Click(CloseButton_Markupsettings_Popup, "Close Icon on the markup settings popup");
    }

    /**
     * Clicks the close icon on the create recipe popup
     *
     * @Author Balaji N
     */
    public void Click_CloseIcon_CreateRecipePopup() {
        js_Click(CloseIcon_CreateRecipe_Popup, "Close icon on create recipe popup");
    }

    /**
     * Clicks the no need button on the confirmation alert popup displayed on create recipe popup
     *
     * @Author Balaji N
     */
    public void Click_NoNeedConfirmationAlert_CreateRecipe_Popup() {
        js_Click(NotNeeded_ButtonConfirmationAlert_CreateRecipe_Popup, "Not neede button on confirmation alert popup displayed on create recipe popup");
    }

    /**
     * CLicks the display Image toogle button on the create recipe popup
     *
     * @Author Balaji N
     */
    public void Click_DisplayImage_ToogleButton_CreateRecipePopup() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(displayImages_ToogleButton_OnCreateRecipePopup));
        HighlightElement(displayImages_ToogleButton_OnCreateRecipePopup);

        String backgroundColor = displayImages_ToogleButton_OnCreateRecipePopup.getCssValue("background-color");
        System.out.println("state of display image toogle button color : " + backgroundColor);

        if (backgroundColor.equals("rgba(237, 85, 101, 1)")) {  // Color for off state: rgb(237, 85, 101) corresponds to #ed5565
            System.out.println("Toggle button clicked to turn on.");
            //displayImages_ToogleButton_OnCreateRecipePopup.click();
            js_Click(displayImages_ToogleButton_OnCreateRecipePopup, "Display image toogle button on create recipe popup");
        } else {
            System.out.println("Toggle button is already in the 'on' state.");
        }

        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(displayImages_ToogleButton_OnCreateRecipePopup, "background-color", "rgb(237, 85, 101)")));
        String newBackgroundColor = displayImages_ToogleButton_OnCreateRecipePopup.getCssValue("background-color");

        if (newBackgroundColor.equals("rgba(26, 179, 148, 1)")) {  // Color for on state: rgb(26, 179, 148) corresponds to #1ab394
            System.out.println("Toggle button successfully turned on.");
        } else {
            System.out.println("Toggle button did not change to 'on' state.");
        }
    }

    /**
     * Clicks the display Image toogle button state as off on the create recipe popup
     *
     * @Author Balaji N
     */
    public void Click_DisplayImage_ToogleButton_Off_CreateRecipePopup() {
        delayWithGivenTime(2000);
        // Wait until the toggle button is visible
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(displayImages_ToogleButton_OnCreateRecipePopup));
        HighlightElement(displayImages_ToogleButton_OnCreateRecipePopup);
        // Get the background color of the toggle button
        String backgroundColor = displayImages_ToogleButton_OnCreateRecipePopup.getCssValue("background-color");
        System.out.println("state of toogle button color : " + backgroundColor);
        // Check if the background color matches the 'off' state
        if (backgroundColor.equals("rgba(26, 179, 148, 1)")) {  // Color for off state: rgb(237, 85, 101) corresponds to #ed5565
            // Click the toggle button to turn it on
            displayImages_ToogleButton_OnCreateRecipePopup.click();
            System.out.println("Toggle button clicked to turn off successfully.");
        } else {
            System.out.println("Toggle button is already in the 'off' state.");
        }

        // Wait for the background color to change to the 'on' state (rgb(26, 179, 148) corresponds to #1ab394)
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(displayImages_ToogleButton_OnCreateRecipePopup, "background-color", "rgb(237, 85, 101)")));

        // Verify if the toggle button is now in the 'on' state
        String newBackgroundColor = displayImages_ToogleButton_OnCreateRecipePopup.getCssValue("background-color");

        if (newBackgroundColor.equals("rgba(237, 85, 101, 1)")) {  // Color for on state: rgb(26, 179, 148) corresponds to #1ab394
            System.out.println("Toggle button successfully turned off.");
        } else {
            System.out.println("Toggle button did not change to 'off' state.");
        }


    }

    /**
     * Verify whether the save to product toogle button is in the off state on the create recipe popup
     *
     * @return If the save to product toogle button is in the off state it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_SaveToProduct_ToogleButton_On_CreateRecipePopup() { // red color background
        Highlight_Element(savetoproduct_ToogleButton_OnCreateRecipePopup, "Save to product toogle button on create recipe popup");
        String backgroundColor = savetoproduct_ToogleButton_OnCreateRecipePopup.getCssValue("background-color");
        System.out.println("state of save to product toogle button color : " + backgroundColor);

        if (backgroundColor.equals("rgba(26, 179, 148, 1)")) {  // Color for off state: rgb(237, 85, 101) corresponds to #ed5565
            return true;
        } else {
            System.out.println("Toggle button is in the 'off' state...");
            return false;
        }
    }

    /**
     * Clicks the save to product toogle button on the create recipe popup
     *
     * @Author Balaji n
     */
    public void Click_SaveToProduct_ToogleButton_On_CreateRecipePopup() {
        if (Verify_SaveToProduct_ToogleButton_On_CreateRecipePopup()) {
            js_Click(savetoproduct_ToogleButton_OnCreateRecipePopup, "Save to product toogle button on create recipe popup");
        }
    }

    /**
     * Verify whether the clear labour hyperlink is displayed on the create recipe popup
     *
     * @return If the clear labour hyperlink is displayed it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_Clearhyperlink_OnCreateRecipePopup_IsDisplayed() {
        return is_Element_Displayed(clearLabourButton_OnCreateRecipePopup, "Clear labour button hyperlink on create recipe popup");
    }

    /**
     * Verify the tooltip of the clear labour hyperlink on the create recipe popup
     *
     * @return If the tooltip of the clear labour hyperlink is displayed it returns value of tooltip, otherwise it returns null
     * @Author Balaji N
     */
    public String Verify_Clearhyperlink_Tooltip_OnCreateRecipePopup() {
        HighlightElement(clearLabourButton_OnCreateRecipePopup);
        Actions action = new Actions(getDriver());
        explicitWait(clearLabourButton_OnCreateRecipePopup);
        action.moveToElement(clearLabourButton_OnCreateRecipePopup).build().perform();
        delayWithGivenTime(1500);
        String title = clearLabourButton_OnCreateRecipePopup.getAttribute("data-original-title");
        return title;
        // get title method not working
        //return HandleTooltip(clearLabourButton_OnCreateRecipePopup);
    }

    /**
     * Clicks the clear labour hyperlink on the create recipe popup
     *
     * @Author Balaji N
     */
    public void Click_Clearhyperlink_OnCreateRecipePopup() {
        js_Click(clearLabourButton_OnCreateRecipePopup, "Clear hyperlink - Labour button on create recipe popup");
    }

    /**
     * Verify whether the labour cost label is displayed on the create recipe popup
     *
     * @return If the labour cost label is displayed it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_labourcost_label() {
        return is_Element_Displayed(labourcost_label, "Labour Cost label on create recipe popup");
    }

    /**
     * Verify whether the labour cost amount is displayed on the create recipe popup
     *
     * @return If the labour cost amount is displayed it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_labourcost_amount() {
        return is_Element_Displayed(labourCost_amount, "Labour Cost amount on create recipe popup");
    }

    /**
     * It retrieves the labour cost amount displayed on the create recipe popup
     *
     * @return If the labour cost amount is displayed it returns value of labour cost, otherwise it returns null
     * @Author Balaji N
     */
    public String get_labourcost_amount() {
        return get_Element_Text(labourCost_amount, "Labour cost amount on create recipe popup").trim();
    }

    /**
     * Verify whether the delete icon 3 is displayed on the product table grid on the create recipe popup
     *
     * @return If the delete icon 3 is displayed it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_DeleteIcon3_isDisplayed() {
        return is_Element_Displayed(deleteIcon3_productTableGrid_OnCreateRecipePopup, "Delete icon row 3 on product table grid on create recipe popup");
    }

    public boolean is_DeleteIcon_Displayed() {
        return isElementDisplayed(lastrow_deleteIcon_productTableGrid_OnCreateRecipePopup, "Last Row delete icon on product table grid on create recipe popup");
    }

    /**
     * Verify the tooltip of the delete icon 3 on the product table grid is displayed on the create recipe popup
     *
     * @return If the tooltip of the delete icon 3 is displayed it returns value of tooltip, otherwise it returns null
     * @Author Balaji N
     */
    public String Verify_Tooltip_OnDeleteIcon_IsDisplayed() {
        return HandleTooltip(deleteIcon3_productTableGrid_OnCreateRecipePopup, "Delete Icon 3 on product table grid on create recipe popup Tool Tip Element");
    }

    public String Verify_Tooltip_OnLastRowDeleteIcon_IsDisplayed() {
        return HandleTooltip(lastrow_deleteIcon_productTableGrid_OnCreateRecipePopup, "Last Row Delete Icon on product table grid on create recipe popup Tool Tip Element");
    }

    public void click_Last_DeleteIcon_OnCreateRecipePopup() {
        js_Click(lastrow_deleteIcon_productTableGrid_OnCreateRecipePopup, "Last Row Delete Icon on product table grid on create recipe popup");
    }

    public void Click_DeleteIcon3_OnCreateRecipePopup() {
        js_Click(deleteIcon3_productTableGrid_OnCreateRecipePopup, "Delete icon 3 on product table grid on create recipe popup");
    }


    public boolean Verify_Row3_ProductIsDelete(String itemname) {
        try {
            // Locate the product in the table (row 3 or based on some identifier)
            WebElement productElement = item_Quantity_Row3;
            // Check if the input element is displayed or has a specific value
            String productValue = productElement.getAttribute("value"); // Assuming 'value' holds product info
            return productValue == null || productValue.isEmpty(); // If value is null/empty, consider it deleted

        } catch (NoSuchElementException e) {
            // If NoSuchElementException is thrown, it means the product is deleted
            return true; // Product is deleted
        }

        //        try {
//            if (Verify_RecipeItemRow3_OnTableIsDisplayed() != "Lily - Gloriosa Blue") {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (NoSuchElementException e) {
//            return true;
//        }
    }


    /**
     * Verify whether the delete icon 1 is displayed on the product table grid on the create recipe popup
     *
     * @return If the delete icon 1 is displayed it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_DeleteIcon1_isDisplayed() {
        return is_Element_Displayed(deleteIcon1_productTableGrid_OnCreateRecipePopup, "Delete icon row 3 on product table grid on create recipe popup");
    }

    /**
     * Verify the tooltip of the delete icon 3 on the product table grid is displayed on the create recipe popup
     *
     * @return If the tooltip of the delete icon 3 is displayed it returns value of tooltip, otherwise it returns null
     * @Author Balaji N
     */
    public String Verify_Tooltip_OnDeleteIcon1_IsDisplayed() {
        return HandleTooltip(deleteIcon1_productTableGrid_OnCreateRecipePopup, "Delete Icon 3 on product table grid on create recipe popup Tool Tip Element");
    }

    /**
     * Clicks the delete icon row 1 on product table grid on the create recipe popup
     *
     * @Author Balaji N
     */
    public void Click_DeleteIcon1_OnCreateRecipePopup() {
        js_Click(deleteIcon1_productTableGrid_OnCreateRecipePopup, "Delete icon 1 on product table grid on create recipe popup");
    }

    /**
     * Verify whether the row 1 product is deleted on the product table grid on the create recipe popup
     *
     * @param itemname the name of the item
     * @return If the row 1 product is deleted it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_Row1_ProductIsDelete(String itemname) {
        try {
            WebElement productElement = item_Quantity_textbox_table_grid_Row1;
            String productValue = getElementAttribute(productElement, "Item Quantity textbox field row 1 value on product section in the Create Recipe Popup");
            return productValue == null || productValue.isEmpty();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    /**
     * Enters the private notes on the create recipe popup
     *
     * @param privatenotes the private notes to be entered
     * @Author Balaji N
     */
    public void Enter_PrivateNotes_CreateRecipe_Popup(String privatenotes) {
        js_Clear_And_Type(privateNote_textbox_OnCreateRecipePopup, privatenotes, "Private notes tab textbox field on create recipe popup");
    }

    /**
     * It retrieves the private notes entered on the create recipe popup
     *
     * @return If the private notes are entered on the create recipe popup it returns the private notes; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_entered_privatenotes_CreateRecipe_Popup() {
        return getElementAttribute(privateNote_textbox_OnCreateRecipePopup, "private notes tab textbox field value on create recipe popup");
    }

    /**
     * Clears the privates notes existing text on the create recipe popup
     *
     * @Author Balaji N
     */
    public void Clear_PrivateNotes_CreateRecipe_Popup() {
        privateNote_textbox_OnCreateRecipePopup.clear();
    }

    /**
     * Clicks the private notes tab on the create recipe popup
     *
     * @Author Balaji N
     */
    public void Click_Private_NotesTab_AddNotesSection() {
        js_Click(privateNote_Tab_OnCreateRecipePopup, "private note tab on create recipe popup");
    }

    /**
     * Clears the public notes on the create recipe popup
     *
     * @Author Balaji N
     */
    public void Clear_PublicNotes_CreateRecipe_Popup() {
        publicNote_textbox_OnCreateRecipePopup.clear();
    }

    /**
     * Clicks the public notes tab on add notes section on the create recipe popup
     *
     * @Author Balaji N
     */
    public void Click_Public_NotesTab_AddNotesSection() {
        js_Click(publicNote_Tab_OnCreateRecipePopup, "Public note tab on create recipe popup");
    }

    /**
     * Enters the public notes on the create recipe popup
     *
     * @param privatenotes the public notes
     * @Author Balaji N
     */
    public void Enter_PublicNotes_CreateRecipe_Popup(String privatenotes) {
        js_Clear_And_Type(publicNote_textbox_OnCreateRecipePopup, privatenotes, "Public notes on create recipe popup");
    }

    /**
     * It retrieves the public notes entered on the create recipe popup
     *
     * @return If the public notes are entered on the create recipe popup it returns the public notes; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_entered_publicnotes_CreateRecipe_Popup() {
        return getElementAttribute(publicNote_textbox_OnCreateRecipePopup, "public notes tab textbox field ");
    }

    /**
     * Click the photo tab - Add notes section on the create recipe popup
     *
     * @Author Balaji N
     */
    public void Click_PhotoTab_AddNotesSection() {
        // actionScrollClick(photo_button_OnCreateRecipePopup);
        js_Click(photo_button_OnCreateRecipePopup, "Photo tab on create recipe popup");
    }

    /**
     * Upload the photo on the photo tab - Add notes section on the create recipe popup
     *
     * @param filename the name of the file to be uploaded
     * @Author Balaji N
     */
    public void Upload_photo_AddFile_Button_OnCreateRecipePopup(String filename) {
        try {
            String projectPath = System.getProperty("user.dir");
            String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;
            By fileInputLocator = By.xpath("//input[@type='file']");

            WebElement uploadElement = new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(fileInputLocator));
            uploadElement.sendKeys(fullFilePath);
            String msg = "✅ File uploaded successfully on photo tab - Add notes section on create recipe popup : " + filename;
        } catch (Exception e) {
            String errorMsg = "❌ Failed to upload file: " + filename + " — Reason: " + e.getMessage();
            System.err.println(errorMsg);
            Allure.step(errorMsg);
            throw new RuntimeException(errorMsg, e);
        }
    }

    /**
     * Verify whether the image is displayed on the photo tab - Add notes section on the create recipe popup
     *
     * @return If the image is displayed it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_ImageIsDisplayed_After_UploadTheFile() {
        delayWithGivenTime(2000);
        return is_Element_Displayed(image1_PhotoTab_AddNotes_OnCreateRecipePopup, "Uploaded image on photo tab - Add notes section on create recipe popup");
    }

    /**
     * Clicks the delete icon on image 1 on photo tab - Add notes section on the create recipe popup
     *
     * @Author Balaji N
     */
    public void Delete_Image1_Photo_AddFile_Button_OnCreateRecipePopup() {
        js_Click(deleteIcon_Image1_PhotoTab_AddNotes_OnCreateRecipePopup, "Delete icon on image 1 on photo tab - Add notes section on create recipe popup");
    }

    /**
     * Enters the quantity on the create recipe popup
     *
     * @param the quantity to be entered
     * @Author Balaji N
     */
    public void Enter_Quantity_OnCreateRecipe_Popup(String quantity) {
        ClickAndType(recipeQty_Textbox_OnCreateRecipePopup, quantity, "Recipe quantity textbox field on create recipe popup");
    }

    /**
     * It Retrieves the quantity entered on the create recipe popup
     *
     * @return If the quantity is entered it returns the quantity; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Entered_Quantity_OnCreateRecipe_Popup() {
        return getElementAttribute(recipeQty_Textbox_OnCreateRecipePopup, "Quantity textbox field value on create recipe popup");
    }

    /**
     * Enters the retail price on the component table at row3
     *
     * @param retailprice
     * @Author Balaji N
     */
    public void Enter_Retail_Price_OnComponentTable_At_Row3(String retailprice) {
        ClickAndType(retail_Price_textbox_Row3, retailprice, "Retail price textbox field on component table at row3");
    }

    /**
     * It retrieves the retail price entered on the component table at row3
     *
     * @return If the retail price is entered on the component table at row3 it returns the retail price; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Entered_Retail_Price_OnComponentTable_At_Row3() {
        return getElementAttribute(retail_Price_textbox_Row3, "Retail price textbox field value on component table at row3").trim();
    }

    public String get_Entered_Retail_Price_TextBox_Value_On_TableGrid_CreateRecipe_Popup() {
        return getElementAttribute(retail_price_textbox_tablegrid_create_recipe_popup, "Retail price textbox field value on table grid on create recipe popup");
    }


    public String get_Entered_Quantity_TextBox_Value_On_TableGrid_CreateRecipe_Popup() {
        return getElementAttribute(quantity_textbox_tablegrid_create_recipe_popup, "Quantity textbox field value on table grid on create recipe popup");
    }

    /**
     * Enters the quantity on the component table at row3
     *
     * @param quantity
     * @Author Balaji N
     */
    public void Enter_Quantity_OnComponentTable_At_Row3(String quantity) {
        ClickAndType(item_Quantity_Row3, quantity, "Quantity textbox field on component table at row3");
    }

    /**
     * It retrieves the quantity entered on the component table at row3
     *
     * @return If the quantity is entered on the component table at row3 it returns the quantity; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Entered_Quantity_OnComponentTable_At_Row3() {
        return getElementAttribute(item_Quantity_Row3, "Quantity textbox field value on component table at row3").trim();
    }

    public boolean Verify_totalRecipe_details_Section_IsDisplayed() {
        HighlightElement(recipe_total_details_OnCreateRecipePopup);
        return recipe_total_details_OnCreateRecipePopup.isDisplayed();
    }

    public String get_Displayed_Total_Cost_OnCreateRecipePopup() {
        return getElementText(total_cost_label_tablegrid_create_recipe_popup, "Total cost on create recipe popup");
    }

    public String get_Displayed_Total_Retail_OnCreateRecipePopup() {
        return getElementText(total_retail_label_tablegrid_create_recipe_popup, "Total retail on create recipe popup");
    }

    /**
     * It retrieves the change amount at payment section on phone order page
     *
     * @return If the change amount is displayed it returns the change amount; otherwise, it returns null
     * @Author Balaji N
     */
    public String Verify_Change_GivenBackTo_Customer_amount_IsDisplayed() {
        return getElementText(change_GivenBack_atPayment_Section_OnPhoneOrderPage, "Change Amount at Payment Section on phone order page");
    }

    /**
     * Verify whether the bank name is required validation message is displayed
     *
     * @return If the
     */
    public String Verify_BankNameIsRequired_Validation_Message_IsDisplayed() {
        return getElementText(BankNameRequired_Popup_ErrorMsg_On_Phoneorderpage, "Bank Name textbox field validation message on payment section"); // (BankNameRequired_Popup_ErrorMsg_On_Phoneorderpage.getText());
    }

    /**
     * Enters the bank name on the payment section
     *
     * @param bankname
     * @Author Balaji N
     */
    public void Enter_BankName_On_TextBox(String bankname) {
        js_Clear_And_Type(BankName_CheckPaymentSection_On_PhoneOrderPage, bankname, "Bank Name textbox field on payment section");
    }

    /**
     * It retrieves the entered bank name on the payment section
     *
     * @return If the entered bank name is displayed it returns the bank name; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Entered_BankName_On_TextBox() {
        return getElementAttribute(BankName_CheckPaymentSection_On_PhoneOrderPage, "bank name textbox field value on payment section in the phone order page");
    }

    /**
     * Enters the name on check on the payment section
     *
     * @param nameoncheck
     * @Author Balaji N
     */
    public void Enter_NameOnCheck_On_TextBox(String nameoncheck) {
        js_Clear_And_Type(NameOnCheck_CheckPaymentSection_On_PhoneOrderPage, nameoncheck, "Name of check textbox field on payment section");
    }

    /**
     * It retrieves the entered name on check on the payment section
     *
     * @return If the entered name on check is displayed it returns the name on check; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Entered_NameOnCheck_On_TextBox() {
        return getElementAttribute(NameOnCheck_CheckPaymentSection_On_PhoneOrderPage, "Name of check textbox field value on payment section in the phone order page");
    }

    /**
     * Enters the check number on the payment section
     *
     * @param checkno
     * @Author Balaji N
     */
    public void Enter_CheckNumber_On_TextBox(String checkno) {
        CheckNumber_CheckPaymentSection_On_PhoneOrderPage.clear();
        ClickAndType(CheckNumber_CheckPaymentSection_On_PhoneOrderPage, checkno, "Check Number textbox field on payment section");
    }

    /**
     * It retrieves the entered check number on the payment section
     *
     * @return If the entered check number is displayed it returns the check number; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Entered_CheckNumber_On_TextBox() {
        return getElementAttribute(CheckNumber_CheckPaymentSection_On_PhoneOrderPage, "Check number textbox field value on payment section in the phone order page");
    }

    /**
     * Selects the check cash registry on the payment section
     *
     * @param checkcashregistry
     */
    public void Select_CheckCashRegistry_Dropdown(String checkcashregistry) {
        drop_Down(CheckCashRegister_Dropdown_CheckPaymentSection_On_PhoneOrderPage, checkcashregistry, "VisibleText", "Check Cash Registry drop down field");
    }

    /**
     * It retrieves the selected check cash registry on the payment section
     *
     * @return If the selected check cash registry is displayed it returns the check cash registry; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Selected_CheckCashRegistry_Dropdown() {
        return get_selected_option(CheckCashRegister_Dropdown_CheckPaymentSection_On_PhoneOrderPage, "Check Cash Registry dropdown field value on payment section in the phone order page");
    }

    /**
     * Enters the check date textbox field on payment section
     *
     * @param checkdate
     * @Author Balaji N
     */
    public void Enter_CheckDate_On_TextBox(String checkdate) {
        js_Clear_And_Type(CheckDate_CheckPaymentSection_On_PhoneOrderPage, checkdate, "Check Date textbox field on payment section");
    }

    /**
     * It retrieves the entered check date on the payment section
     *
     * @return If the entered check date is displayed it returns the check date; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Entered_CheckDate_On_TextBox() {
        return getElementAttribute(CheckDate_CheckPaymentSection_On_PhoneOrderPage, "Check Date textbox field value on payment section in the phone order page");
    }

    /**
     * Enters the check account number on the payment section
     *
     * @param checkAccountNumber
     */
    public void Enter_CheckAccountNumber_On_TextBox(String checkAccountNumber) {
        CheckAccountNumber_CheckPaymentSection_On_PhoneOrderPage.clear();
        ClickAndType(CheckAccountNumber_CheckPaymentSection_On_PhoneOrderPage, checkAccountNumber, "Check Account Number textbox field on payment section");
    }

    /**
     * It retrieves the entered check account number on the payment section
     *
     * @return If the entered check account number is displayed it returns the check account number; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Entered_CheckAccountNumber_On_TextBox() {
        return getElementAttribute(CheckAccountNumber_CheckPaymentSection_On_PhoneOrderPage, "Check Account Number textbox field value on payment section in the phone order page");
    }

    /**
     * Enters the check rounting number on the payment section
     *
     * @param checkRountingNumber
     * @Author Balaji N
     */
    public void Enter_CheckRountingNumber_On_TextBox(String checkRountingNumber) {
        js_Clear_And_Type(Check_RoutingNumber_CheckPaymentSection_On_PhoneOrderPage, checkRountingNumber, "Check Rounting Number textbox field on payment section");
    }

    /**
     * It retrieves the entered check rounting number on the payment section
     *
     * @return If the entered check rounting number is displayed it returns the check rounting number; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Entered_CheckRountingNumber_On_TextBox() {
        return getElementAttribute(Check_RoutingNumber_CheckPaymentSection_On_PhoneOrderPage, "Check rounting number textbox field value on payment section in the phone order page");
    }

    /**
     * Enters the EIN number on the payment section
     *
     * @param EINNumber
     * @Author Balaji N
     */
    public void Enter_EINNumber_Textbox_On_PaymentSection(String EINNumber) {
        js_Clear_And_Type(EINNumber_PhoneOrder_PaymentSection, EINNumber, "EIN number textbox field on payment section");
    }

    /**
     * It retrieves the EIN number entered on the payment section
     *
     * @return If the EIN number is displayed it returns the EIN number; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_EINNumber_Textbox_On_PaymentSection() {
        return getElementAttribute(EINNumber_PhoneOrder_PaymentSection, "EIN number textbox field value on payment section in the phone order page");
    }

    /**
     * It retrieves the firstname field displayed or entered value on cash and carry payment page
     *
     * @return If firstname value is displayed it returns the firstname field displayed or entered value, otherwise it returns null
     * @Description This function will highlight the first name field, and then it retrieves the firstname field displayed or entered value
     * @Author Balaji N
     */
    public String get_autopopulated_creditcard_paymenttype_FirstName() {
        return getElementAttribute(CreditCard_PaymentType_FirstName_OnCustDetailsPopup, "First Name textbox field value on credit card payment section in the phone order page");
    }

    /**
     * Enter the first name on credit card tab in cash and carry payment page
     *
     * @param firstname Provided first name to be entered on credit card tab
     * @Description: This function highlight the firstname field on credit card tab, and then click and entered the provided firstname
     * @Author Balaji N
     */
    public void Enter_FirstName_CreditCardTab_PaymentPage(String firstname) {
        clickAndType(CreditCard_PaymentType_FirstName_OnCustDetailsPopup, firstname);
    }

    /**
     * Enter the last name on credit card tab in cash and carry payment page
     *
     * @param lastname Provided last name to be entered on credit card tab
     * @Description: This function highlight the lastname field on credit card tab, and then click and entered the provided lastname
     * @Author Balaji N
     */
    public void Enter_LastName_CreditCardTab_PaymentPage(String lastname) {
        ClickAndType(CreditCard_PaymentType_LastName_OnCustDetailsPopup, lastname, "Last Name textbox field on credit card payment section");
    }

    /**
     * It retrieves the last name field displayed or entered value on cash and carry payment page
     *
     * @return If the last name value is displayed it returns the last name field displayed or entered value, otherwise it returns null
     * @Author Balaji N
     */
    public String get_autopopulated_creditcard_paymenttype_LastName() {
        return getElementAttribute(CreditCard_PaymentType_LastName_OnCustDetailsPopup, "Last Name textbox field value on credit card payment section in the phone order page");
    }

    /**
     * It retrieves the zipcode value from credit card tab on cash and carry payment page
     *
     * @return If the zipcode text is exists it returns the value of displayed zipcode otherwise it returns empty string
     * @Description: This function highlights the zipcode field on credit card tab and then it retrieves the zipcode value on cash and carry payment page
     * @Author Balaji N
     */
    public String get_autopopulated_creditcard_paymenttype_Zipcode() {
        return getElementAttribute(CreditCard_PaymentType_ZipCode_OnCustDetailsPopup, "Zipcode textbox field value on credit card payment section in the phone order page");
    }

    /**
     * It retrieves the city value from credit card tab on cash and carry payment page
     *
     * @return If the city text is exists it returns the value of displayed city otherwise it returns empty string
     * @Author Balaji N
     */
    public String get_autopopulated_creditcard_paymenttype_City() {
        return getElementAttribute(CreditCard_PaymentType_City_Dropdown_OnCustDetailsPopup, "City textbox field value on credit card payment section in the phone order page");
    }

    /**
     * It retrieves the state value from credit card payment section in the phone order page
     *
     * @return
     */
    public String get_autopopulated_creditcard_paymenttype_State() {
        return getElementAttribute(CreditCard_PaymentType_State_OnCustDetailsPopup, "State textbox field value on credit card payment section in the phone order page");
    }

    /**
     * It retrieves the country value from credit card payment section in the phone order page
     *
     * @return If the country text is exists it returns the value of displayed country otherwise it returns empty string
     * @Author Balaji N
     */
    public String get_Selected_creditcard_paymenttype_Country() {
        return get_Selected_Option(CreditCard_PaymentType_Country_OnCustDetailsPopup, "Country dropdown field value on credit card payment section in the phone order page");
    }

    /**
     * Click and select the existing credit card
     *
     * @Description This function click the credit card textbox field, and then select the displayed existing credit card number using press "arrow down and Enter key"
     * @Author Balaji N
     */
    public void ClickAndSelect_CreditCardNumber_TextBoxField() {
        try {
            if (CreditCard_PaymentType_CreditCardNumber_OnCustDetailsPopup != null) {
                CreditCard_PaymentType_CreditCardNumber_OnCustDetailsPopup.click();
                delayWithGivenTime(1000);
//                if (isElementDisplayed(existing_creditcard_number)) {
//                    js_Click(existing_creditcard_number, "Existing credit card number");
//                } else{
//                    System.err.println("Existing credit card number not found");
//                }
                CreditCard_PaymentType_CreditCardNumber_OnCustDetailsPopup.sendKeys(Keys.ARROW_DOWN);
                delayWithGivenTime(1000);
                CreditCard_PaymentType_CreditCardNumber_OnCustDetailsPopup.sendKeys(Keys.ENTER);
            } else {
                throw new NoSuchElementException("Credit Card textbox field not found on Customer Details popup.");
            }
        } catch (NoSuchElementException e) {
            printError(CreditCard_PaymentType_CreditCardNumber_OnCustDetailsPopup,
                    "Credit Card textbox field", "No such element exception", e);
        } catch (ElementNotInteractableException e) {
            printError(CreditCard_PaymentType_CreditCardNumber_OnCustDetailsPopup,
                    "Credit Card textbox field", "Element not interactable exception", e);
        } catch (Exception e) {
            printError(CreditCard_PaymentType_CreditCardNumber_OnCustDetailsPopup,
                    "Credit Card textbox field", "Unexpected exception", e);
        }
    }

    /**
     * Enters the credit card number on credit card payment section in the phone order page
     *
     * @param creditcardnumber
     * @Author Balaji N
     */
    public void Enter_CreditCardNumber_CCPaymentSection_On_PhoneOrderPage(String creditcardnumber) {
        ClickAndType(CreditCard_PaymentType_CreditCardNumber_OnCustDetailsPopup, creditcardnumber, "Credit card number textbox field on credit card payment section");
    }

    /**
     * It retrieves the entered credit card number from credit card payment section in the phone order page
     *
     * @return If the credit card number text is exists it returns the value of displayed credit card number otherwise it returns empty string
     * @Author Balaji N
     */
    public String get_entered_CreditcardNumber_CCPaymentSection_On_PhoneOrderPage() {
        return getElementAttribute(CreditCard_PaymentType_CreditCardNumber_OnCustDetailsPopup, "Credit card number textbox field value on credit card payment section in the phone order page");
    }

    /**
     * Enters the expired date on credit card payment section in the phone order page
     *
     * @param creditexpdate If the credit card number text is exists it returns the value of displayed credit card number otherwise it returns empty string
     * @Author Balaji N
     */
    public void Enter_ExpiredDate_CCPaymentSection_On_PhoneOrderPage(String creditexpdate) {
        // jsClearAndType(CreditCard_PaymentType_ExpDate_OnCustDetailsPopup, creditexpdate);
        ClickAndType(CreditCard_PaymentType_ExpDate_OnCustDetailsPopup, creditexpdate, "Expired date textbox field on credit card payment section");
    }

    /**
     * It retrieves the expired date from credit card payment section in the phone order pag e
     *
     * @return If the expired date text is exists it returns the value of displayed expired date otherwise it returns empty string
     * @Author Balaji N
     */
    public String get_entered_ExpiredDate_CCPaymentSection_On_PhoneOrderPage() {
        return getElementAttribute(CreditCard_PaymentType_ExpDate_OnCustDetailsPopup, "Expired date textbox field value on credit card payment section in the phone order page");
    }

    /**
     * Enters the CCV on credit card payment section in the phone order page
     *
     * @param creditCCV
     * @Author Balaji N
     */
    public void Enter_CCV_CCPaymentSection_On_PhoneOrderPage(String creditCCV) {
        //jsClearAndType(CreditCard_PaymentType_CVV_OnCustDetailsPopup, creditCCV);
        ClickAndType(CreditCard_PaymentType_CVV_OnCustDetailsPopup, creditCCV, "CCV textbox field on credit card payment section");
    }

    /**
     * It retrieves the entered CCV from credit card payment section in the phone order page
     *
     * @return If the CCV text is exists it returns the value of displayed CCV otherwise it returns empty string
     * @Author Balaji N
     */
    public String get_entered_CVV_CCPaymentSection_On_PhoneOrderPage() {
        return getElementAttribute(CreditCard_PaymentType_CVV_OnCustDetailsPopup, "CCV textbox field value on credit card payment section in the phone order page");
    }

    /**
     * Selects the credit card type on credit card payment section in the phone order page
     *
     * @param creditcardtype
     * @Author Balaji N
     */
    public void Select_CreditcardType_CCPaymentSection_On_PhoneOrderPage(String creditcardtype) {
        drop_Down(CreditCard_PaymentType_Dropdown_OnCustDetailsPopup, creditcardtype, "VisibleText", "Credit card type dropdown field on credit card payment section in the phone order page");
    }

    /**
     * It retrieves the selected credit card type from credit card payment section in the phone order page
     *
     * @return If the credit card type text is exists it returns the value of displayed credit card type otherwise it returns empty string
     * @Author Balaji N
     */
    public String get_selected_CreditcardType_CCPaymentSection_On_PhoneOrderPage() {
        return get_Selected_Option(CreditCard_PaymentType_Dropdown_OnCustDetailsPopup, "Payment type dropdown field value on Preference Tab on customer details popup in the phone order page");
    }

    /**
     * Clicks the dont save checkbox on credit card payment section in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_Dont_Save_Checkbox_CCPaymentSection_On_PhoneOrderPage() {
        js_Click(dontSaveCreditCardCheckbox_OnCustDetailsPopup, "Dont Save credit card checkbox ");
    }

    /**
     * Verify the dont save checkbox on credit card payment section in the phone order page is checked or not
     *
     * @return If the dont save checkbox is checked on credit card payment section in the phone order page it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_DontSave_Checkbox_CCPaymentSection_On_PhoneOrderPage_IsChecked() {
        Highlight_Element(dontSaveCreditCardCheckbox_OnCustDetailsPopup, "Dont Save credit card checkbox");
        return dontSaveCreditCardCheckbox_OnCustDetailsPopup.isSelected();
    }

    /**
     * It retrieves the customer id in the customer section on the phone order page
     *
     * @return If the customer id is displayed it returns the value otherwise it returns empty string
     * @Author Balaji N
     */
    public String get_custId_OnPhoneorderPage() {
        return getElementText(custId_OnPhoneOrderPage, "Customer ID - Customer Section on the phone order page");
    }

    /**
     * Verify the PO Number field is displayed on invoice house account payment section on phone order page
     *
     * @return If the PO Number field is displayed on invoice house account payment section on phone order page it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_PO_NumberField_IsDisplayed_On_InvoiceHouseAccount_PaymentSection() {
        return is_Element_Displayed(ponumber_paymenttype_InvHouseAct_OnPhoneOrderPage, "PO Number textbox field on invoice house account payment section on phone order page");
    }

    /**
     * Enters the PO number on invoice house account payment section on phone order page
     *
     * @param po_number
     * @Author Balaji N
     */
    public void Enter_PO_Number_Details_Payment_Section(String po_number) {
        jsClick(ponumber_paymenttype_InvHouseAct_OnPhoneOrderPage, "PO Number textbox field on invoice house account payment section on phone order page");
        jsClear(ponumber_paymenttype_InvHouseAct_OnPhoneOrderPage);
        doubleClickUsingJS(ponumber_paymenttype_InvHouseAct_OnPhoneOrderPage, "PO Number textbox field on invoice house account payment section on phone order page");
        ClickAndType(ponumber_paymenttype_InvHouseAct_OnPhoneOrderPage, po_number, "PO Number textbox field on invoice house account payment section on phone order page");
    }

    /**
     * Verify the Payment Terms field is displayed on invoice house account payment section on phone order page
     *
     * @return If the Payment Terms field is displayed on invoice house account payment section on phone order page it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean Verify_Payment_TermsField_IsDisplayed_On_InvoiceHouseAccount_PaymentSection() {
        return is_Element_Displayed(PaymentTerms_paymenttype_InvHouseAct_OnPhoneOrderPage, "Payment Terms textbox field on invoice house account payment section on phone order page");
    }

    /**
     * It retrieves the customer id displayed on customer details popup on phone order page
     *
     * @return If the customer id is displayed on customer details popup on phone order page it returns the value otherwise it returns empty string
     * @Author Balaji N
     */
    public String get_CustomerId_OnCustomerDetailsPopup_OnPhoneOrderPage() {
        return getElementAttribute(custIdTextbox_CustDetailsPopup_OnPhoneOrderPage, "Customer ID textbox field value on customer details popup");
    }

    public boolean Verify_ItemDescription_Autosuggestion_DropdownOptions(String proditemDesc) {
        DoubleClickAndType(prod_details_ItemDesc1, proditemDesc);
        delayWithGivenTime(3000);
        HighlightElement(itemDesc1_Autosuggestion);
        return itemDesc1_Autosuggestion.isDisplayed();
    }

    public String get_orderdetailsTab_invoicenumber_row1() {
        HighlightElement(InvoiceNo_row1_OrderDetailsTab);
        return InvoiceNo_row1_OrderDetailsTab.getText().trim();
    }

    /**
     * Clicks the card message suggestion icon on order details section
     *
     * @Author Balaji N
     */
    public void Click_CardMessage_Suggestion_Icon_On_OrderDetailsSection() {
        js_Click(cardmessage_suggestion_Icon, "Card message suggestion icon on order details section - Card Message textbox field");
        wait_For_Page_To_Be_Stable(getDriver());
    }

    /**
     * Verify the card message suggestion popup is displayed or not
     *
     * @return If the card message suggestion popup is displayed it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean verify_cardmessage_suggestion_popup_IsDisplayed() {
        return is_Element_Displayed(cardmessage_suggestion_popup, "Card message suggestion popup");
    }

    /**
     * Clicks the first card message from suggestion popup
     *
     * @Author Balaji N
     */
    public void Click_Cardmessage_on_SuggestionPopup() {
        js_Click(cardmessage_suggestion_list.get(0), "Selecting first card message from suggestion popup");
    }


    /**
     * Verify whether the Item Detail i icon is displaying next to the respective product row
     *
     * @return If i icon is displayed it return true otherwise it return false
     * @Description: This function will verify whether I icon is displayed on product details section
     * @Author: Balaji N
     */
    public boolean Verify_I_Icon_IsDisplayed_on_ProductDetails_Section() {
        return is_Element_Displayed(prod_details_I_Icon, "i icon is row 1 on product details section");
    }

    /**
     * Clicks the i Icon for respective added product on row 1 product details section
     *
     * @return It will Open the item details popup otherwise return null
     * @Description: This function will click on i icon on product details for respective added product
     * @Author: Balaji N
     */

    public void Click_I_Icon_on_ProductDetails_Section() {
        js_Click(prod_details_I_Icon, "Clicking i icon on product details for row 1");
        wait_For_Page_To_Be_Stable(getDriver());
    }

    /**
     * Validate the item details popup is displayed on phone order product details section
     *
     * @return If item detail popup is displayed it return true, otherwise it returns false
     * @Description: This function will validate if item details popup is displayed or not
     * @Author: Balaji N
     */
    public boolean verify_itemdetails_popup_isDisplayed() {
        isElementDisplayed(ItemDetail_Popup_Content, "Item details popup for product details section");
        return is_Element_Displayed(ItemDetail_Popup, "Item details popup");
    }

    /**
     * Retrieves the value of the item details recipe name
     *
     * @return If item details popup is displayed it return the string value of recipe name otherwise it returns null
     * @Description: This function will highlight the recipe name element and getting the displayed recipe name on item details popup
     * @Author: Balaji N
     */
    public String get_recipe_name_on_ItemDetails_popup() {
        return getElementText(recipename_Itemdetailpopup, "Recipe name on item details popup");
    }

    /**
     * Retrieves the value of the item details recipe total cost
     *
     * @return If item details popup is displayed it return the string value of recipe total cost otherwise it returns null
     * @Description: This function will highlight the recipe total cost element and getting the displayed recipe total cost on item details popup
     * @Author: Balaji N
     */

    public String get_recipe_totalcost_on_ItemDetails_popup() {
        return getElementText(recipe_totalcost_Itemdetailpopup, "Recipe total cost on item details popup");
    }

    /**
     * Retrieves the value of the item details recipe total labour
     *
     * @return If item details popup is displayed it return the string value of recipe total labour otherwise it returns null
     * @Description: This function will highlight the recipe total labour element and getting the displayed recipe total cost on item details popup
     * @Author: Balaji N
     */

    public String get_recipe_totalLabour_on_ItemDetails_popup() {
        return getElementText(recipe_totalLabour_Itemdetailpopup, "Recipe total Labour on item details popup");
    }


    /**
     * Retrieves the value of the item details recipe total amount
     *
     * @return If item details popup is displayed it return the string value of recipe total amount otherwise it returns null
     * @Description: This function will highlight the recipe total amount element and getting the displayed recipe total amount on item details popup
     * @Author: Balaji N
     */

    public String get_recipe_totalAmount_on_ItemDetails_popup() {
        return getElementText(recipe_totalAmount_Itemdetailpopup, "Recipe total amount on item details popup");
    }


    /**
     * It retrieves the value of the item details component name row 1
     *
     * @return If item details popup retrieve the row 1 component name otherwise it returns null
     * @Description: This function will highlight the component name row1 and getting the displayed value of component name row1 on item details popup
     * @Author: Balaji N
     */
    public String get_displayed_componentname_row1_on_ItemDetails_popup() {
        return getElementText(componentname_itemrow1, "Component name row 1 on item details popup");
    }

    /**
     * It retrieves the value of the item details component name row 2
     *
     * @return If item details popup retrieve the row 2 component name otherwise it returns null
     * @Description: This function will highlight the component name row1 and getting the displayed value of component name row2 on item details popup
     * @Author: Balaji N
     */
    public String get_displayed_componentname_row2_on_ItemDetails_popup() {
        return getElementText(componentname_itemrow2, "Component name row 2 on item details popup");
    }

    /**
     * Click the close icon on item details popup
     *
     * @Description: This function will click on close icon on item details popup
     * @Expected functionality: If we click the close icon on item details popup it should close the item details popup
     * @Author: Balaji N
     */

    public void Click_ItemDetails_CloseIcon() {
        js_Click(ItemDetails_Popup_CloseIcon, "Close icon on item details popup");
    }

    /**
     * Selects the cash registry for split payment section on phoneorder page
     *
     * @param splitpaymentregistry The visible text of provided split cash registry to be selected
     * @Description: This function highlight the split cash registry dropdown, and then select the provided visible text of displayed from static dropdown
     * @Author: Balaji N
     */
    public void Select_SplitPayment_CashRegistry_OnPaymentSection(String splitpaymentregistry) {
        drop_Down(splitpayment_cashregistry, splitpaymentregistry, "VisibleText", "Split payment cash registry dropdown field on phone order page");
    }

    /**
     * It retrieves the selected value of the split payment cash registry dropdown field on payment section in the phone order page
     *
     * @return
     * @Author Balaji N
     */
    public String get_Selected_Cash_Registry_On_Payment_Section() {
        return get_Selected_Option(splitpayment_cashregistry, "Split payment cash registry dropdown field on payment section in the phone order page");
    }

    /**
     * Enters the floor apt in the recipient section on phone order page
     *
     * @param floorApt The provided floor apt to be entered
     * @Description: This function will enter the floor apt in the recipient section on phone order page
     * @Author: Balaji N
     */
    public void Enter_FloorApt_On_RecipientSection(String floorApt) {
        ClickAndType(recipientaptfloorOnPhoneOrderPage, floorApt, "Floor apt textbox field on recipient section on phone order page");
    }

    /**
     * It retrieves the value of the floor apt in the recipient section on phone order page
     *
     * @return If the floor apt recipient section is displayed it returns the value of floor apt otherwise it returns empty string
     * @Description : This function will highlight the floor apt recipient section and getting the displayed value of floor apt in the recipient section on phone order page
     * @Author: Balaji N
     */
    public String get_FloorApt_On_RecipientSection() {
        return get_element_attribute_with_trim(recipientaptfloorOnPhoneOrderPage, "Floor apt textbox field on recipient section on phone order page");
    }

    /**
     * This method is used to calculate the total amount in the phone order page
     *
     * @return : It will return the total amount as string
     * @Description : This method is used to calculate the amount which is displaying in the phone order page
     * @Author: Sakrateesh R
     */
    public String calculate_total_amount() {
        DecimalFormat df = new DecimalFormat("###.00");
        double sub_total = Double.parseDouble(subTotalOnPhoneOrderPage.getAttribute("value"));
        double discount_amount = Double.parseDouble(paymentdiscamt_OnPhoneOrderPage.getAttribute("value"));
        double delivery_fee_amount = Double.parseDouble(deliveryFee_PhoneOrder_PaymentSection.getAttribute("value"));
        double relay_fee_amount = Double.parseDouble(relayFee_PhoneOrder_PaymentSection.getAttribute("value"));
        double tax_amount = Double.parseDouble(taxAmt_PhoneOrder_PaymentSection.getAttribute("value"));

        double total_amount = ((sub_total + delivery_fee_amount + relay_fee_amount + tax_amount) - discount_amount);
        String formattedTotal = df.format(total_amount);

        return formattedTotal;
    }

    public void doubleClickOnRelayFee() {
        Double_Click(relayFee_PhoneOrder_PaymentSection, "Relay fee field on payment section on phone order page");
    }

    /**
     * Selects the shopname dropdown on phone order page
     *
     * @param shopname The given shopname to be selected
     * @Description Selects the shop name on phone order page using dropDown reusable method
     * @Author Balaji N
     */
    public void Select_ShopName_On_PhoneOrder_Page(String shopname) {
        PageLoadLoggerUtils.logPageLoad("Phone Order Page → Shop Name Dropdown field", () -> {

            // Step 1: Wait for page stability
            wait_For_Page_To_Be_Stable(getDriver());

            // Step 2: Try selecting from dropdown
            try {
                drop_Down(
                        shop_dropdown_phoneorder_page,
                        shopname,
                        "VisibleText",
                        "Shop Name Dropdown field on Phone Order Page"
                );
                wait_For_Page_To_Be_Stable(getDriver());
            } catch (TimeoutException | NoSuchElementException e) {
                String errorMessage = "\n"
                        + "❌ Failed to select shop name from dropdown on Phone Order Page.\n"
                        + " - Requested shop name : '" + shopname + "'\n"
                        + " - Element locator     : " + shop_dropdown_phoneorder_page.toString() + "\n"
                        + "\n"
                        + "🔍 Possible causes:\n"
                        + " - Dropdown element is not visible or interactable\n"
                        + " - The requested shop name is not available in the dropdown list\n"
                        + " - Page took too long to load or became stale\n";

                // Attach to Allure report
                Allure.addAttachment("Shop Name Dropdown Failure", errorMessage);

                // Throw with clean message so Allure & Jenkins logs are clear
                throw new RuntimeException(errorMessage, e);
            }
        });
    }


    /**
     * It retrieves the selected shop from shopname drop down field in phone order page
     *
     * @return If the shop name is selected in phoneorder page it return displayed shopname
     * @Author Balaji N
     */
    public String get_selected_shopname_on_phoneorder_page() {
        return get_selected_option(shop_dropdown_phoneorder_page, "Shop Name dropdown field in the phone order page");
    }

    /**
     * Clicks the veridy address icon
     *
     * @Author Balaji N
     */
    public void Click_Verify_Address_Icon() {
        Click(verify_address_icon, "Verify address icon on Phone Order Page");
    }

    /**
     * Clicks the view map icon on top
     *
     * @Author Balaji N
     */
    public void Click_view_map_icon_on_Top() {
        Click(view_map_icon_on_top, "View Map Icon on Top");
    }

    /**
     * Clicks the calculate mile icon on top of phone order page
     *
     * @Author Balaji N
     */
    public void Click_Calculate_Miles_Icon() {
        Click(calculate_miles_icon, "Calculate Miles Icon on Top");
    }

    /**
     * CLicks the view drafts icon on phone order page
     *
     * @Author Balaji N
     */
    public void Click_View_Drafts_Icon_On_Phone_OrderPage() {
        js_Click(view_draft_icon, "View Drafts Icon on Phone Order Page");
    }

    /**
     * Clicks the save as draft icon on phone order page
     *
     * @Author Balaji N
     */
    public void Click_Save_As_Draft_Icon_On_Phone_OrderPage() {
        js_Click(save_as_draft_icon, "Save As Draft Icon on Phone Order Page");
    }

    public boolean is_Draft_Orders_Popup_IsDisplayed() {
        return isElementDisplayed(draft_orders_popup, "Draft Orders Popup on phone order page");
    }

    /**
     * Validates whether draft orders popup is displayed when florist clicks on draft icon
     *
     * @return If draft orders popup is displayed it returns true otherwise returns false
     * @Author Balaji N
     */
    public boolean Verify_Draft_Orders_Popup_IsDisplayed() {
        return isElementDisplayed(draft_orders_popup_header, "Draft Orders Popup Header");
    }

    /**
     * Clicks the row1 table grid on draft orders popup
     *
     * @Author Balaji N
     */
    public void Click_Row1_TableGrid_on_DraftOrders_popup() {
        Click(draft_orders_row1_on_tablegrid, "Row 1 Table Grid on Draft Orders Popup");
    }

    /**
     * Validate the confirmation alert is display or not on draft order popup when clicks the displayed drafts order
     *
     * @return Confirmation alert is displayed it returns true otherwise false
     * @Author Balaji N
     */
    public boolean Verify_Confirmation_Alert_IsDisplayed_on_Draft_order() {
        return is_Element_Displayed(confirmation_Alert_on_draft_orders_popup, "Confirmation Alert on Draft Orders Popup");
    }

    /**
     * Clicks the delete icon on row 1 draft order popup
     *
     * @author Balaji N
     */
    public void Click_Delete_Icon_On_Row1_Draft_Order() {
        click(draft_order_row1_delete_icon_tablegrid);
    }


    public String get_recipe_total_details_section_RecipeCostTotal() {
        return get_element_attribute(recipe_total_details_section_RecipeCostTotal, "recipe_total_details_section_RecipeCostTotal");
    }

    public void Enter_Coupon_Code_On_PhoneOrderPage_PaymentSection(String coupon_code) {
        Click(coupon_code_textboxfield_payment_section, "Coupon Code on Payment Section");
        coupon_code_textboxfield_payment_section.clear();
        ClickAndType(coupon_code_textboxfield_payment_section, coupon_code, "Coupon Code on Payment Section");
        delayWithGivenTime(1000);
        coupon_code_textboxfield_payment_section.sendKeys(Keys.TAB);
        delayWithGivenTime(1000);
    }

    public String get_Coupon_Code_On_PhoneOrderPage_PaymentSection() {
        return get_element_attribute(coupon_code_textboxfield_payment_section, "Coupon Code on Payment Section");
    }

    public String get_Discount_Percentage_On_PhoneOrderPage_PaymentSection() {
        return get_element_attribute(discount_percentage_textboxfield_payment_section, "Discount Percentage on Payment Section");
    }

    /**
     * Select the gift card type popup on cash and carry page
     *
     * @Author : Balaji N
     */
    public boolean is_GiftCard_Select_Type_Popup_Displayed() {
        return isElementDisplayed(gift_card_popup, "Gift Card Select Type Popup");
    }

    public void click_Physical_Gift_Card_Type_Radio_Button() {
        click(physical_giftcard_type_radio_button, "Physical Gift Card Type Radio Button");
    }

    public void click_Next_Button_On_GiftCard_Select_Type_Popup() {
        click(gift_card_type_next_button, "Next Button on Gift Card Select Type Popup");
    }

    public boolean is_Enter_Gift_Card_Details_Popup_Displayed() {
        return isElementDisplayed(physical_giftcard_enterdetails_header, "Enter Gift Card Details header Popup - Physical Gift Card");
    }

    public boolean is_EGift_Card_Details_Popup_Displayed() {
        return isElementDisplayed(e_giftcard_enterdetails_header, "Enter Gift Card Details header Popup - E-Gift Card");
    }

    /**
     * Search and select the customer from the list on enter gift card details popup
     *
     * @param customer
     * @Author : Balaji N
     */
    public void search_And_Select_Customer_On_Enter_Gift_Card_Details_Popup(String customer) {
        try {
            ClickAndType(CustomerNameTextBox, customer, "Customer Name Text Box");

            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            By dynamicDropdownOption = By.xpath("//ul[starts-with(@id, 'ui-id-')]//li//div[contains(text(), '" + customer + "')]");

            // Wait for the dropdown item to be visible
            WebElement customerOption = wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicDropdownOption));

            click(customerOption, "Customer Option in Dropdown");

        } catch (TimeoutException e) {
            throw new RuntimeException("Customer '" + customer + "' not found in the dropdown list within timeout.");
        } catch (Exception e) {
            throw new RuntimeException("Exception while selecting customer '" + customer + "': " + e.getMessage());
        }
    }


    public void click_Preview_Button_On_Enter_Gift_Card_Details_Popup() {
        click(preview_button_on_enter_giftcard_details_popup, "Preview Button on Enter Gift Card Details Popup");
    }

    public boolean is_Preview_Header_Displayed_On_Popup() {
        return isElementDisplayed(preview_header_on_enter_giftcard_details_popup, "Preview Header on Enter Gift Card Details Popup");
    }

    public String get_Amount_On_Preview_Popup() {
        return get_element_attribute(preview_amount_textbox_on_enter_giftcard_details_popup, "Preview Amount Text Box on Enter Gift Card Details Popup");
    }

    public String get_Processing_Fee_On_Preview_Popup() {
        return get_element_attribute(preview_processing_fee_textbox_on_enter_giftcard_details_popup, "Preview Processing Fee Text Box on Enter Gift Card Details Popup");
    }

    public String get_Card_Number_On_Preview_Popup() {
        return get_element_attribute(preview_card_number_textbox_on_enter_giftcard_details_popup, "Preview Card Number Text Box on Enter Gift Card Details Popup");
    }

    public String get_Customer_Name_On_Preview_Popup() {
        return get_element_attribute(preview_customer_name_textbox_on_enter_giftcard_details_popup, "Preview Customer Name Text Box on Enter Gift Card Details Popup");
    }

    public String get_Total_Amount_On_Preview_Popup() {
        return get_element_attribute(preview_total_amount_textbox_on_enter_giftcard_details_popup, "Preview Total Amount Text Box on Enter Gift Card Details Popup");
    }

    public void click_Submit_Button_On_Preview_Popup() {
        click(preview_submit_button_on_popup, "Submit Button on Preview Popup");
    }

    public void click_EGift_Card_Submit_Button_On_Preview_Popup() {
        click(preview_submit_button_on_egiftcard_popup, "eGift Card Button on Preview Popup");
    }

    public void click_EGiftCard_Radio_Button() {
        click(egift_radio_button, "eGift Card Type Radio Button");
    }

    public void enter_Recipient_Name_Egift_Card_Popup(String recipient_name) {
        ClickAndType(recipientname_egift_card, recipient_name, "Recipient Name Text Box");
    }

    public String get_Recipient_Name_Egift_Card_Popup() {
        return get_element_attribute(recipientname_egift_card, "Recipient Name Text Box");
    }

    public void set_Recipient_Email_Egift_Card_Popup(String recipient_email) {
        ClickAndType(recipientemail_egift_card, recipient_email, "Recipient Email Text Box");
    }

    public String get_Recipient_Email_Egift_Card_Popup() {
        return get_element_attribute(recipientemail_egift_card, "Recipient Email Text Box");
    }

    public void enter_Message_Egift_Card_Popup(String message) {
        ClickAndType(message_egift_card, message, "Message Text Box");
    }

    public void click_Next_Button_On_EGiftCard_Popup() {
        click(egift_next_button, "Next Button on eGift Card Popup");
    }

    public boolean is_Select_Gift_Card_Design_Popup_Displayed() {
        return isElementDisplayed(occasion_header_egift_card, "Select Gift Card Design header Popup");
    }

    public void select_Occasion_Egift_Card_Popup(String occasion) {
        drop_Down(occasion_dropdown_egift_card, occasion, "VisibleText", "Occasion Dropdown - eGift Card Popup");
    }

    public String get_Occasion_Egift_Card_Popup() {
        return get_selected_option(occasion_dropdown_egift_card, "Occasion Dropdown - eGift Card Popup");
    }

    public void click_Gift_Card_Design_Button() {
        click(giftcard_design_3_egift_card, "Gift Card Design Button");
    }

    public String get_Gift_Amount_On_Select_Gift_Card_Design_Popup() {
        return get_Element_Text(gift_amount_gift_card_design, "Gift Card Amount on label gift card design popup");
    }

    public void click_Preview_Button_On_Select_Gift_Card_Design_Popup() {
        click(preview_button_on_occasion_egift_card, "Preview Button on Select Gift Card Design Popup");
    }

    public boolean is_Gift_Card_Sale_Preview_Popup_for_Egiftcard_Displayed() {
        return isElementDisplayed(preview_header_on_egift_card_sale_popup, "Gift Card Sale Preview Popup");
    }

    public String get_Customer_Name_Gift_Card_Sale_Preview_Popup_for_Egiftcard() {
        return get_element_attribute(preview_customer_name_textbox_on_egift_card_sale_popup, "Customer Name Text Box on Gift Card Sale Preview Popup");
    }

    public String get_Recipient_Name_Gift_Card_Sale_Preview_Popup_for_Egiftcard() {
        return get_element_attribute(preview_recipient_name_textbox_on_egift_card_sale_popup, "Recipient Name Text Box on Gift Card Sale Preview Popup");
    }

    public String get_Occasion_Gift_Card_Sale_Preview_Popup_for_Egiftcard() {
        return get_element_attribute(preview_occasion_textbox_on_egift_card_sale_popup, "Occasion Text Box on Gift Card Sale Preview Popup");
    }

    public String get_Recipient_Email_Gift_Card_Sale_Preview_Popup_for_Egiftcard() {
        return get_element_attribute(preview_recipient_email_textbox_on_egift_card_sale_popup, "Recipient Email Text Box on Gift Card Sale Preview Popup");
    }

    public String get_Gift_Amount_Gift_Card_Sale_Preview_Popup_for_Egiftcard() {
        return get_element_attribute(preview_amount_textbox_on_egift_card_sale_popup, "Gift Amount Text Box on Gift Card Sale Preview Popup");
    }

    public String get_Processing_Fee_Gift_Card_Sale_Preview_Popup_for_Egiftcard() {
        return get_element_attribute(preview_processing_fee_textbox_on_egift_card_sale_popup, "Processing Fee Text Box on Gift Card Sale Preview Popup");
    }

    public String get_Total_Amount_Gift_Card_Sale_Preview_Popup_for_Egiftcard() {
        return get_element_attribute(preview_total_amount_textbox_on_egift_card_sale_popup, "Total Amount Text Box on Gift Card Sale Preview Popup");
    }

    public String get_Gift_Card_Number_Gift_Card_Sale_Preview_Popup_for_Egiftcard() {
        return get_element_attribute(preview_card_number_textbox_on_egift_card_sale_popup, "Gift Card Number Text Box on Gift Card Sale Preview Popup");
    }

    public String get_Message_Gift_Card_Sale_Preview_Popup_for_Egiftcard() {
        return get_element_attribute(preview_message_textbox_on_egift_card_sale_popup, "Message Text Box on Gift Card Sale Preview Popup");
    }

    public void click_Recurring_Order_Button() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(recurring_order_button_OnPhoneOrderPage));
        click(recurring_order_button_OnPhoneOrderPage, "Recurring Order Button on phone order page");
    }

    public boolean is_Recurring_Order_Popup_Displayed() {
        return isElementDisplayed(recurring_info_Popup, "Recurring Order Popup");
    }

    public boolean is_Recurring_Order_Popup_Closed() {
        try {
            return !recurring_info_Popup.isDisplayed();  // If still attached and visible
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return true; // Element is gone = popup is closed
        }
    }

    public boolean is_Recurring_Toogle_Button_Enabled() {
        String backgroundColor = recurring_toogle_button_onphoneorderpage.getCssValue("background-color");
        return backgroundColor.equals("rgba(26, 179, 148, 1)");
    }

    public void click_Recurring_Frequency_Dropdown() {
        click(recurring_frequency_dropdown_onphoneorderpage, "Recurring Frequency Dropdown");
    }

    public void select_Recurring_Frequency_Dropdown(String frequency) {
        drop_Down(recurring_frequency_dropdown_onphoneorderpage, frequency, "VisibleText", "Recurring Frequency Dropdown");
    }

    public String get_Selected_Recurring_Frequency() {
        return get_selected_option(recurring_frequency_dropdown_onphoneorderpage, "Recurring Frequency Dropdown");
    }

    public boolean validate_Day_Based_Subscription_Dropdown_Options() {
        List<String> expectedOptions = Arrays.asList(
                "Daily", "Weekly", "Bi-Weekly", "Monthly",
                "Every 60 days", "Every 90 days", "Every 120 days",
                "Yearly", "Custom Days"
        );

        List<String> actualOptions = list_of_day_based_subscriptions_on_recurring_freqency_dropdown
                .stream()
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());

        return actualOptions.contains(expectedOptions);
    }

    public boolean validate_Date_Based_Subscriptions_Frequency_Dropdown_Options_Displayed() {

        List<String> expectedOptions = Arrays.asList("Date of Every Month", "Date of Every 3 Months", "Date of Every Year");

        List<String> actualOptions = new ArrayList<>();
        for (WebElement option : list_of_date_based_subscriptions_on_recurring_freqency_dropdown) {
            actualOptions.add(option.getText().trim());
        }

        if (actualOptions.containsAll(expectedOptions) && expectedOptions.containsAll(actualOptions)) {
            return true;
        } else {
            return false;
        }

    } //list_of_week_based_subscriptions_on_recurring_freqency_dropdown

    public boolean validate_Week_Based_Subscriptions_Frequency_Dropdown_Options_Displayed() {

        List<String> expectedOptions = Arrays.asList("Week of Month");

        List<String> actualOptions = new ArrayList<>();
        for (WebElement option : list_of_week_based_subscriptions_on_recurring_freqency_dropdown) {
            actualOptions.add(option.getText().trim());
        }

        if (actualOptions.containsAll(expectedOptions) && expectedOptions.containsAll(actualOptions)) {
            return true;
        } else {
            return false;
        }

    }

    public void enter_Frequency_Days(String frequency) {
        ClickAndType(recurring_frequency_days_onphoneorderpage, frequency, "Frequency Days Text Box on Recurring Order Popup");
    }

    public String get_Displayed_Frequency_Days() {
        return get_element_attribute(recurring_frequency_days_onphoneorderpage, "Frequency Days Text Box on Recurring Order Popup");
    }

    public String get_Selected_Next_Delivery_Date() {
        return get_element_attribute(next_recurring_date_onphoneorderpage, "Next Delivery Date Text Box on Recurring Order Popup");
    }

    public void enter_End_Date_Frequency(String end_date) {
        ClickAndType(recurring_end_date_onphoneorderpage, end_date, "End Date Text Box on Recurring Order Popup");
        delayWithGivenTime(500);
        recurring_end_date_onphoneorderpage.sendKeys(Keys.TAB);
    }

    public String get_End_Date_Frequency() {
        return get_element_attribute(recurring_end_date_onphoneorderpage, "End Date Text Box on Recurring Order Popup");
    }

    public void click_Close_Button_On_Recurring_Popup() {
        if (isElementDisplayed(recurring_close_button_onphoneorderpage, "Close Button on Recurring Order Popup")) {
            js_Click(recurring_close_button_onphoneorderpage, "Close Button on Recurring Order Popup");
        }
    }

    /**
     * Get Recurring Info on Order Details Section
     *
     * @return If Recurring Info on Order Details Section is displayed it returns the value of Recurring Info on Order Details Section otherwise it returns null
     * @Author Balaji N
     */
    public String get_Recurring_Info_On_OrderDetails_Section() {
        return getElementText(recurring_info_onphoneorderpage, "Recurring Info on Order Details Section");
    }

    public void close_QuickView() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
            WebElement quickViewPopup = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@class='col-md-12 hana-fullview-container quickview']")
            ));

            if (quickViewPopup.isDisplayed()) {
                WebElement closeBtn = getDriver().findElement(By.xpath("//div[@class='col-md-12 hana-fullview-container quickview']//div[@class='modal-close-icon']//a//i"));
                js_Click(closeBtn, "QuickView Close Button");
                String msg = "✅ QuickView popup was displayed and successfully closed.";
                System.out.println(msg);
                // Allure.step(msg);
            }

        } catch (TimeoutException | NoSuchElementException e) {
            String msg = "ℹ️ QuickView popup not displayed. Continuing with flow.";
            // System.out.println(msg);
            //  Allure.step(msg);
        } catch (Exception e) {
            String err = "❌ Unexpected error while closing Quick View Popup: " + e.getMessage();
            System.err.println(err);
            Allure.step(err);
            throw new RuntimeException(err, e);
        }
    }


}
	
	







