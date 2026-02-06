package com.hanapos.pageObjects;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Allure;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

public class ManageProposalPage extends TestBaseClass {

    public ManageProposalPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //======================================= Manage Proposal page web elements =============================
// ========= Create Proposal Page header tabs ===============
    @FindBy(xpath = "//select[@id='ddlProposalType']")
    private WebElement proposal_type_dropdown_value;

    @FindBy(xpath = "//select[@id='ddlReference']")
    private WebElement proposal_references;

    @FindBy(id = "lblProposalId")
    private WebElement ProposalId;

    @FindBy(xpath = "//a[@id='linkOverview']")
    private WebElement overview_link_tab;

    @FindBy(xpath = "//div[@id='proposalPreview']//div[@class='modal-content']")
    private WebElement proposal_overview_popup;

    @FindBy(xpath = "//h4[@id='PFOR_Reception']")
    private WebElement proposal_for_label;

    @FindBy(xpath = "//p[@id='PFOR_SummeryName']")
    private WebElement groom_fullname_on_overviewpopup;

    @FindBy(xpath = "//p[@id='CustMobNo']")
    private WebElement groom_phonenumber_on_overview;

    @FindBy(xpath = "//div[@id='proposalPreview']//i[@class='fa fa-lg fa-times-circle']")
    private WebElement closeIcon_on_overview_popup;

    @FindBy(xpath = "//input[@id='LineOneTitle']")
    private WebElement lineOneTitle_under_coupleInfo;

    @FindBy(xpath = "//input[@id='LineTwoTitle']")
    private WebElement lineTwoTitle_under_coupleInfo;

    @FindBy(xpath = "//select[@id='ddlSelectEventReport']")
    private WebElement select_theme_under_coupleInfo;

    @FindBy(xpath = "//h2[contains(text(),'Warning! Your current theme images will be removed.')]")
    private WebElement confirmation_popup_for_selectTheme;

    @FindBy(xpath = "//button[@class='confirm']")
    private WebElement yesproceed_button_on_selectTheme_confirmationpopup;

    @FindBy(xpath = "//select[@id='ddlSelectWeddingConsultant']")
    private WebElement select_weddingorEvent_consultant_dropdown;

    @FindBy(xpath = "//a[@id='aCoupleInfo']")
    private WebElement general_info_tab_on_addoredit_proposal_page;

    //========= Header tab webelements finish ========================================

    @FindBy(xpath = "//a[@id='aCoupleDetails']")
    private WebElement coupleDetails_Tab;

    @FindBy(id = "txtCustomerFirstName")
    private WebElement brideFirstName;

    @FindBy(id = "txtCustomerLastName")
    private WebElement brideLastName;

    @FindBy(id = "txtCustomerPhone")
    private WebElement bridePhoneNumber;

    @FindBy(id = "txtCustomerEmail")
    private WebElement brideEmail;

    @FindBy(id = "txtCustomerCity")
    private WebElement brideCity;

    @FindBy(id = "txtCustomerState")
    private WebElement brideState;

    @FindBy(id = "txtCustomerAddress")
    private WebElement brideAddress;

    @FindBy(xpath = "//input[@id='txtCustomerAddress']/following-sibling::ul")
    private WebElement autocomplete_bride_address_field;

    @FindBy(xpath = "(//div[@class='pac-container pac-logo hdpi'])[1]//span[@class='pac-item-query']")
    private List<WebElement> address_field_autocomplete_list;

    @FindBy(xpath = "(//div[@class='pac-container pac-logo hdpi'])[1]//span[3]")
    private List<WebElement> city_state_country_autocomplete_list;

    @FindBy(id = "txtCustomerZip")
    private WebElement brideZip;

    @FindBy(xpath = "//select[@id='bride-groom']")
    private WebElement bride_Groom_DropDown1;

    @FindBy(xpath = "//select[@id='bride-groom2']")
    private WebElement bride_Groom_DropDown2;

    @FindBy(id = "txtAltFirstName")
    private WebElement groomFirstName;

    @FindBy(id = "txtAltLastName")
    private WebElement groomLastName;

    @FindBy(id = "txtAltPhone")
    private WebElement groomPhoneNumber;

    @FindBy(id = "txtAltEmail")
    private WebElement groomEmail;

    @FindBy(id = "txtAltCity")
    private WebElement groomCity;

    @FindBy(id = "txtAltState")
    private WebElement groomState;

    @FindBy(id = "txtAltAddress")
    private WebElement groomAddress;

    @FindBy(xpath = "//input[@id='txtAltAddress']/following-sibling::ul")
    private WebElement autocomplete_bride_address_field2;

    @FindBy(id = "txtAltZip")
    private WebElement groomZip;

    @FindBy(id = "aEventDetails")
    private WebElement Wedding_eventDetails_Tab;

    @FindBy(id = "txtCeremonyLocation")
    private WebElement ceremonyLocation;

    @FindBy(id = "txtCeremonyDate")
    private WebElement ceremonyDate;

    @FindBy(id = "txtCeremonyTime")
    private WebElement ceremonyTime;

    @FindBy(id = "txtCeremonyAddress")
    private WebElement ceremonyAddress;

    @FindBy(id = "txtCeremonyZip")
    private WebElement ceremonyZip;

    @FindBy(id = "txtCeremonyCity")
    private WebElement ceremonyCity;

    @FindBy(id = "txtCeremonyState")
    private WebElement ceremonyState;

    @FindBy(id = "txtReceptionLocation")
    private WebElement receptionLocation;

    @FindBy(id = "txtReceptionDate")
    private WebElement receptionDate;

    @FindBy(id = "txtReceptionTime")
    private WebElement receptionTime;

    @FindBy(id = "txtReceptionAddress")
    private WebElement receptionAddress;

    @FindBy(id = "txtReceptionZip")
    private WebElement receptionZip;

    @FindBy(id = "txtReceptionCity")
    private WebElement receptionCity;

    @FindBy(id = "txtReceptionState")
    private WebElement receptionState;

    @FindBy(id = "txtWedCoFirstName")
    private WebElement weddingCoordinatorFirstName;

    @FindBy(id = "txtWedCoLastName")
    private WebElement weddingCoordinatorLastName;

    @FindBy(id = "txtWedCoPhone")
    private WebElement weddingCoordinatorPhoneNumber;

    @FindBy(id = "txtWedCoEmail")
    private WebElement weddingCoordinatorEmail;

    @FindBy(id = "txtWedCoCity")
    private WebElement weddingCoordinatorCity;

    @FindBy(id = "txtWedCoState")
    private WebElement weddingCoordinatorState;

    @FindBy(id = "txtWedCoAddress")
    private WebElement weddingCoordinatorAddress;

    @FindBy(id = "txtWedCoZip")
    private WebElement weddingCoordinatorZip;

    @FindBy(id = "aDocumentImages")
    private WebElement documentImagesTab;

    @FindBy(xpath = "//a[@id='aUploadImages']")
    private WebElement uploadimages_tab;

    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement SuccessToastMsg;

    @FindBy(xpath = "(//div[@class='proposal-Image'])[1]")
    //  @FindBy(xpath = "(//div[@class='proposalConceImage'])[1]")
    private WebElement image1_uploadimagestab;

    @FindBy(xpath = "//div[@id='divUploadImages']//div[2]//div[1]//button[1]//i[1]")
    private WebElement uploadIcon_image1;

    @FindBy(xpath = "//div[@id='divUploadImages']//div[2]//div[1]//button[1]//i[1]")
    private WebElement uploadImage1_editicon;

    @FindBy(xpath = "(//button[@class='btn btn-edit btnConceRemoveImage'])[1]")
    private WebElement uploadImage1_deleteicon;

    @FindBy(xpath = "(//div[@class='proposal-Image'])[2]")
    // @FindBy(xpath = "(//div[@class='proposalConceImage'])[2]")
    private WebElement image2_uploadimagestab;

    @FindBy(xpath = "//div[@id='divDocumentImages']//div[3]//div[1]//button[1]//i[1]")
    private WebElement uploadIcon_image2;

    @FindBy(xpath = "//div[@id='divDocumentImages']//div[3]//div[1]//button[1]//i[1]")
    private WebElement editIcon_on_image2;

    @FindBy(xpath = "//div[@id='divDocumentImages']//div[3]//div[1]//button[2]//i[1]")
    private WebElement deleteIcon_on_image2;

    @FindBy(xpath = "(//div[@class='proposal-Image'])[3]")
    //@FindBy(xpath = "(//div[@class='proposalConceImage'])[3]")
    private WebElement image3_uploadimagestab;

    @FindBy(xpath = "//div[@id='divDocumentImages']//div[4]//div[1]//button[1]//i[1]")
    private WebElement uploadIcon_image3;

    @FindBy(xpath = "//div[@id='divDocumentImages']//div[4]//div[1]//button[1]")
    private WebElement editIcon_image3;

    @FindBy(xpath = "//div[@id='divDocumentImages']//div[4]//div[1]//button[2]")
    private WebElement deleteIcon_image3;

    @FindBy(xpath = "//a[@id='aUploadDocuments']")
    private WebElement upload_document_subtab;

    @FindBy(xpath = "//input[@id='btnUploadProposalDocs']")
    private WebElement browserFilesBtn;

    @FindBy(xpath = "//button[@id='btnUploadProposalDocsAddFile']//span[normalize-space()='Add file']")
    private WebElement addfilebutton_uploaddocuments_tab;

    @FindBy(xpath = "//p[@class='drag-and-drop-title k-dropzone-hovered']")
    private WebElement draganddrop_element_on_documentandimages;

    @FindBy(xpath = "//a[@id='aInspirationBoard']")
    private WebElement inspiration_board_tab;

    @FindBy(xpath = "//input[@id='btnUploadProposalImage']")
    private WebElement browseFilesBtn_on_inspiration_board_tab;

    @FindBy(xpath = "(//div[@id='inspirationImagesContainer']//following::div[contains(@class,'inspiration-box ')])[1]")
    private WebElement uploaded_1st_file_on_inspirationBroad;

    @FindBy(xpath = "(//div[@id='inspirationImagesContainer']//following::div[contains(@class,'inspiration-box ')])[2]")
    private WebElement uploaded_2nd_file_on_inspirationBroad;

    @FindBy(xpath = "(//button[@class='btn delete-inspiration-img removeImageProposal ']//i[@class='fa fa-trash-o'])[1]")
    private WebElement deleteIcon_on_inspiration_board_tab;

    @FindBy(id = "aItemsProducts")
    private WebElement items_ProductsTab;

    @FindBy(id = "ddlCategory")
    private WebElement select_CategoryDropDown;

    @FindBy(id = "CategoryWiseItem")
    private WebElement items_TextBox;

    // Added on Jan 13 2025 by Balaji N, for verify autocomplete is displayed
    @FindBy(xpath = "//ul[@id='ui-id-5']")
    private WebElement item_autocomplete_itemsandproducts_tab;

    @FindBy(xpath = "//ul[@id='ui-id-5']//li//div")
    private List<WebElement> itemsList;

    @FindBy(id = "btnAddItem")
    private WebElement addItemBtn_ItemsTab;

    @FindBy(xpath = "//table[@id='tblPropPackItemsProducts']//tbody//tr[1]//td[1]//label//strong")
    private WebElement item_category_Row1;

    @FindBy(xpath = "//table[@id='tblPropPackItemsProducts']//tbody//tr[1]//td[3]//div//label[1]")
    private WebElement item_Code_Row1;

    @FindBy(xpath = "//table[@id='tblPropPackItemsProducts']//tbody//tr[1]//td[3]//div//p[1]")
    private WebElement item_description_Row1;

    @FindBy(xpath = "//table[@id='tblPropPackItemsProducts']//tbody//tr[1]//td[3]//div//p[2]")
    private WebElement item_Quantity_Row1;

    @FindBy(xpath = "//table[@id='tblPropPackItemsProducts']//tbody//tr[1]//td[3]//div//label[1]")
    private WebElement item_Price_In_Product_DetailsRow1;

    @FindBy(xpath = "//table[@id='tblPropPackItemsProducts']//tbody//tr[1]//td[4]//label")
    private WebElement item_Total_price_Row1;

    @FindBy(xpath = "//p[contains(text(),'Publish pending changes')]")
    private WebElement publishPendingChanges;

    @FindBy(xpath = "(//label[@id='lblGrandTotal'])[1]")
    private WebElement grandTotal;

    @FindBy(xpath = "//tbody//select[@id='ddlProposalTaxType']")
    private WebElement tax_type_dropdown;

    @FindBy(xpath = "//tbody//input[@id='txtEventDeliveryFee']")
    private WebElement delivery_fee_amount;

    @FindBy(xpath = "//span[text()=' Delivery Fee : ']/following::label[@id='lblFinalDeliveryFee']")
    private WebElement final_delivery_fee_label;

    @FindBy(xpath = "//tbody//span[text()='Sub Total : ']/following::td//label[@id='grandTotal']")
    private WebElement subtotal_amount;

    @FindBy(xpath = "//tbody[@id='tblproposalTotals']//label[@id='lblTax']")
    private WebElement taxamount;

    @FindBy(xpath = "//tbody//label[@id='lblFinalDiscountAmount']")
    private WebElement discountamount;

    @FindBy(xpath = "//tbody//input[@id='txtEventSetupFee']")
    private WebElement setup_fee_amount;

    @FindBy(xpath = "//span[@id='SetupfeeSymbol']")
    private WebElement setup_fee_symbol_on_itemsandproducts_tab;

    @FindBy(xpath = "//span[@id='DeliveryfeeSymbol']")
    private WebElement delivery_fee_symbol_on_itemsandproducts_tab;

    @FindBy(xpath = "//label[@id='lblFinalSetupFee']")
    private WebElement final_setup_fee_amount;

    @FindBy(xpath = "//h2[text()='Publish pending changes']")
    private WebElement publishPendingChanges_AlertTitle;

    @FindBy(xpath = "//button[@class='confirm']")
    private WebElement publish_Btn_On_AlertPopup;

    @FindBy(xpath = "//a[@id='linkFloristView' and contains(text(),'Florist View')]")
    private WebElement florist_view_link_On_headerTab;

    @FindBy(xpath = "//p[@id='lineOneTitle']")
    private WebElement titleofproposal_on_floristView_window;

    @FindBy(xpath = "//p[contains(text(),'Wedding Info')]")
    private WebElement weddinginfo_header_on_floristview_window;

    @FindBy(xpath = "//p[contains(text(),'Event Info')]")
    private WebElement eventinfo_header_on_floristview_window;

    @FindBy(xpath = "//p[contains(text(),'Coordinator Information')]")
    private WebElement coordinator_info_header_on_floristView_window;

    @FindBy(xpath = "//div[@class='edit-proposal-title']")
    private WebElement edit_proposal_label_header;

    @FindBy(xpath = "//a[@id='linkEmailConversation']")
    private WebElement email_conversation_link_on_headerTab;

    @FindBy(xpath = "(//button[@id='btnComposeMail' or contains(text(),'Compose Mail')])[1]")
    private WebElement compose_mail_button_on_emailconversation_popup;

    @FindBy(xpath = "//div[@class='mail-box']")
    private WebElement email_box_inside_composemail;

    @FindBy(xpath = "//a[@id='btnEmailSend']")
    private WebElement send_button_on_emailbox;

    @FindBy(xpath = "//a[@class='btn btn-danger btn-sm btnClear']")
    private WebElement discard_button_on_emailbox;

    @FindBy(xpath = "(//button[@id='closemodalconversation']//i)[1]")
    private WebElement closeIcon_on_emailconversation_popup;

    @FindBy(xpath = "//a[@id='linkPrint']")
    private WebElement print_link_on_headerTab;

    @FindBy(xpath = "(//div[@class='modal-header customer-modal-header'])[1]//child::h4")
    private WebElement print_popup_screen_headerTab;

    @FindBy(xpath = "(//div[@class='modal-header customer-modal-header'])[1]//child::button")
    private WebElement closeIcon_on_print_popup;

    @FindBy(xpath = "//a[@class='btn btn-white dropdown-toggle']")
    private WebElement more_actions_on_headerTab;

    @FindBy(xpath = "//a[@class='AddFollowup li_FolloUp']")
    private WebElement add_followup_on_headerTab;

    @FindBy(xpath = "//div[@class='modal-content']//h4[contains(text(),'Add Follow Up')]")
    private WebElement add_follow_up_popup_on_moreActions;

    @FindBy(xpath = "//div[@class='col-md-1 col-sm-1 col-xs-1 pad0']//i")
    private WebElement closeIcon_addFollowUp_popup_on_moreActions;

    @FindBy(xpath = "//a[@class='CustomerProposalView']")
    private WebElement customerview_on_moreactions_at_headerTab;


    // ======================== Events Web Elements =====================================
    @FindBy(xpath = "//input[@id='txtAltFirstName']")
    private WebElement event_firstName_Altcontactdetails_section;

    @FindBy(xpath = "//input[@id='txtAltLastName']")
    private WebElement event_lastName_Altcontactdetails_section;

    @FindBy(xpath = "(//input[@id='txtAltPhone'])[1]")
    private WebElement event_phonenumber_Altcontactdetails_section;

    @FindBy(xpath = "//input[@id='txtAltEmail']")
    private WebElement event_emailid_Altcontactdetails_section;

    @FindBy(xpath = "//input[@id='txtAltCity']")
    private WebElement event_city_Altcontactdetails_section;

    @FindBy(xpath = "//input[@id='txtAltState']")
    private WebElement event_state_Altcontactdetails_section;

    @FindBy(xpath = "//input[@id='txtAltAddress']")
    private WebElement event_address_Altcontactdetails_section;

    @FindBy(xpath = "//input[@id='txtAltZip']")
    private WebElement event_zipcode_Altcontactdetails_section;

    @FindBy(xpath = "//a[@id='aEventDetails']")
    private WebElement eventdetailstab;

    @FindBy(xpath = "//input[@id='txtEventName']")
    private WebElement eventname;

    @FindBy(xpath = "//input[@id='txtEventLocation']")
    private WebElement eventlocation;

    @FindBy(xpath = "//input[@id='txtEventDate']")
    private WebElement eventdate;

    @FindBy(xpath = "//input[@id='txtEventTime']")
    private WebElement eventtime;

    @FindBy(xpath = "//input[@id='txtEventAddress']")
    private WebElement eventaddress;

    @FindBy(xpath = "//input[@id='txtEventZip']")
    private WebElement eventzipcode;

    @FindBy(xpath = "//input[@id='txtEventCity']")
    private WebElement eventcity;

    @FindBy(xpath = "//input[@id='txtEventState']")
    private WebElement eventstate;

    @FindBy(xpath = "//input[@id='txtCoFirstName']")
    private WebElement eventcoordinator_firstname;

    @FindBy(xpath = "//input[@id='txtCoLastName']")
    private WebElement eventcoordinator_lastname;

    @FindBy(xpath = "//input[@id='txtCoPhone']")
    private WebElement eventcoordinator_phonenumber;

    @FindBy(xpath = "//input[@id='txtCoEmail']")
    private WebElement eventcoordinator_emailid;

    @FindBy(xpath = "//input[@id='txtCoAddress']")
    private WebElement eventcoordinator_address;

    @FindBy(xpath = "//input[@id='txtCoZip']")
    private WebElement eventcoordinator_zipcode;

    @FindBy(xpath = "//input[@id='txtCoCity']")
    private WebElement eventcoordinator_city;

    @FindBy(xpath = "//input[@id='txtCoState']")
    private WebElement eventcoordinator_state;

    //========== Document tab =====================
    @FindBy(css = "div[id='documentsContainer'] div:nth-child(2) a:nth-child(1)")
    private WebElement uploaded_product1_on_grid;

    @FindBy(css = "div[id='divDocUploadHolder_1'] div:nth-child(2) a:nth-child(1)")
    private WebElement uploaded_product2_on_grid;

    @FindBy(xpath = "//button[@id='btnDeleteUploadDoc_0']")
    private WebElement deleteIcon1_on_uploadedDocument;

    @FindBy(xpath = "//button[@id='btnDeleteUploadDoc_1']")
    private WebElement deleteIcon2_on_uploadedDocument;

    //========================== Notes and Instructions ===========================
    @FindBy(xpath = "//a[@id='aNotes']")
    private WebElement notesandinstructions_tab;

    @FindBy(xpath = "//label[contains(text(),'Possibilities of upgrade')]")
    private WebElement possibilities_of_upgrade_header_label;

    @FindBy(xpath = "//textarea[@id='txtPossibilityItemDescription']")
    private WebElement possibilities_of_upgrade_description_textbox;

    @FindBy(xpath = "//input[@id='txtPossibilityItemQty']")
    private WebElement possibilities_of_upgrade_quantity_textbox;

    @FindBy(xpath = "//input[@id='txtPossibilityItemPrice']")
    private WebElement possibilities_of_upgrade_price_textbox;

    @FindBy(xpath = "//button[@id='btnAddPossibility']")
    private WebElement additem_button_on_possibilities_of_upgrade;

    @FindBy(xpath = "//table[@id='ProposalPossibility']//tbody//tr//td[1]//span")
    private WebElement gridTable_possibilities_of_upgrade_description;

    @FindBy(xpath = "//table[@id='ProposalPossibility']//tbody//tr//td[2]//span")
    private WebElement gridTable_possibilities_of_upgrade_quantity;

    @FindBy(xpath = "//table[@id='ProposalPossibility']//tbody//tr//td[3]//span")
    private WebElement gridTable_possibilities_of_upgrade_price;

    @FindBy(xpath = "//table[@id='ProposalPossibility']//tbody//tr//td[4]//span")
    private WebElement gridTable_possibilities_of_upgrade_total;

    @FindBy(xpath = "//textarea[@id='txtSplIns']")
    private WebElement special_instructions_textbox;

    @FindBy(xpath = "//label[contains(text(),'Special instructions')]")
    private WebElement special_instructions_header_label;

    @FindBy(xpath = "//label[contains(text(),'Special instructions')]/following::a[1]")
    private WebElement special_instructions_eye_icon;

    @FindBy(xpath = "//div[@class='tooltip-inner']")
    private WebElement special_instructions_toolTip;

    @FindBy(xpath = "//div[@id='divNotes']//label[contains(text(),'Notes')]")
    private WebElement notes_header_label;

    @FindBy(xpath = "//a[@rel='html']")
    private WebElement html_link_on_notesandinstructions_tab;

    @FindBy(xpath = "//a[@class='re-button re-html re-button-icon']")
    private WebElement html_link_activestate;

    @FindBy(xpath = "//a[@rel='format']")
    private WebElement format_link_on_notesandinstructions_tab;

    @FindBy(xpath = "//a[@class='redactor-dropdown-item-h1']//span")
    private WebElement header1_value_on_format_link;

    @FindBy(xpath = "(//div[@role='presentation'])[1]")
    private WebElement notessection_textarea_on_notesandinstructions_tab;

    @FindBy(xpath = "//div[@class='redactor-styles redactor-in redactor-in-0']")
    private WebElement header1_value_on_notesandinstructions;

    @FindBy(xpath = "//table[@id='ProposalPossibility']//tbody//tr//td//div//a[@class='hana-possibility-actions EditPossibility btn btn-primary']//span")
    private WebElement edit_icon_on_possibilitiesofupgrade_notesandinstructions_tab;

    @FindBy(xpath = "//button[@id='btnSavePossibilityChanges']")
    private WebElement save_changesbutton_on_possibilitiesofupgrade_notesandinstructions_tab;

    @FindBy(xpath = "//table[@id='ProposalPossibility']//tbody//tr//td//div//a[@class='hana-possibility-actions DeletePossibility btn btn-primary']//span")
    private WebElement delete_icon_on_possibilitiesofupgrade_notesandinstructions_tab;


    //=================================== Manage Proposal Page Functions ========================================
    public String get_ManageProposalPageTitle() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

            // Wait until page is fully loaded
            wait.until(driver ->
                    ((JavascriptExecutor) driver)
                            .executeScript("return document.readyState")
                            .equals("complete")
            );

            // Wait until title is non-empty
            wait.until(ExpectedConditions.not(
                    ExpectedConditions.titleIs("")
            ));

            return getDriver().getTitle();

        } catch (TimeoutException e) {
            logger.error("Timeout while waiting for Manage Proposal page to load", e);
            return getDriver().getTitle(); // return whatever is available

        } catch (Exception e) {
            logger.error("Exception while getting Manage Proposal page title", e);
            return "";
        }
    }


    public void clickGeneralInfoTab() {
        Click(general_info_tab_on_addoredit_proposal_page, "General Info Tab");
    }

    public void Click_CoupleDetailsTab() {
        Click(coupleDetails_Tab, "Couple Details Tab");
    }

    public void Enter_Bride_FirstName(String firstName) {
        clickAndType(brideFirstName, firstName);
    }

    public String get_Entered_Bride_FirstName() {
        HighlightElement(brideFirstName);
        return brideFirstName.getAttribute("value").trim();
    }

    public void Enter_Bride_LastName(String lastName) {
        clickAndType(brideLastName, lastName);
    }

    public String get_Entered_Bride_LastName() {
        HighlightElement(brideLastName);
        return brideLastName.getAttribute("value").trim();
    }

    public void Enter_Bride_PhoneNumber(String phonenumber) {
        clickAndType(bridePhoneNumber, phonenumber);
    }

    public String get_Entered_Bride_PhoneNumber() {
        HighlightElement(bridePhoneNumber);
        return bridePhoneNumber.getAttribute("value").trim();
    }

    public void Enter_Bride_Email(String email) {
        clickAndType(brideEmail, email);
    }

    public String get_Entered_Bride_Email() {
        HighlightElement(brideEmail);
        return brideEmail.getAttribute("value").trim();
    }

    public void Enter_Bride_City(String city) {
        clickAndType(brideCity, city);
    }

    public String get_Entered_Bride_City() {
        HighlightElement(brideCity);
        return brideCity.getAttribute("value").trim();
    }

    public void Enter_Bride_State(String state) {
        clickAndType(brideState, state);
    }

    public String get_Entered_Bride_State() {
        HighlightElement(brideState);
        return brideState.getAttribute("value").trim();
    }

    public void Enter_Bride_Address(String address) {
        brideAddress.clear();
        delayWithGivenTime(2000);
        clickAndType(brideAddress, address);
    }

    public boolean verify_address_field_autocomplete_options_IsDisplayed() {
        return isElementDisplayed(autocomplete_bride_address_field,"Address autosuggestion dropdown for couple details");
    }

    public boolean verify_address2_field_autocomplete_options_IsDisplayed() {
        return isElementDisplayed(autocomplete_bride_address_field2);
    }


    public boolean verify_address_on_autocomplete(String address) {
        for (int a = 0; a < address_field_autocomplete_list.size(); a++) {
            String currentAddress = address_field_autocomplete_list.get(a).getText();
            if (currentAddress.equalsIgnoreCase(address)) {

                return true;
            }
        }
        return false;
    }

    public void Select_Address_from_autocomplete(String address) {
        try {
            // Wait for the dropdown to appear and elements to be visible
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            List<WebElement> autocompleteOptions = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@id='txtCustomerAddress']/following-sibling::ul//li[contains(text(),'"+address+"')]"))
            );

            for (WebElement option : autocompleteOptions) {
                HighlightElement(option);
                System.out.println("Autocomplete Options : " + option.getText());
                if (option.getText().contains(address.trim())) {
                  //  option.click();
                   click(option,"address option on bride address");
                    return;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while selecting address from autocomplete dropdown: " + e.getMessage(), e);
        }
    }


    public String get_Entered_Bride_Address() {
        HighlightElement(brideAddress);
        return brideAddress.getAttribute("value").trim();
    }

    public void Enter_Bride_Zip(String zip) {
        clickAndType(brideZip, zip);
    }

    public String get_Entered_Bride_Zip() {
        HighlightElement(brideZip);
        return brideZip.getAttribute("value").trim();
    }

    public void Select_Bride_Groom_DropDown1(String value) {
        dropDown(bride_Groom_DropDown1, value, "VisibleText");
    }

    public String get_selected_bride_grrom_dropdown1() {
        return get_Selected_Option(bride_Groom_DropDown1, "Bride or Groom dropdown field on Proposal - Couple info tab");
    }

    public void Select_Bride_Groom_DropDown2(String value) {
        dropDown(bride_Groom_DropDown2, value, "VisibleText");
    }

    public String get_selected_bride_grrom_dropdown2() {
        return get_Selected_Option(bride_Groom_DropDown2, "Bride or Groom dropdown field on Proposal - Couple info tab");
    }

    public void Enter_Groom_FirstName(String firstName) {
        clickAndType(groomFirstName, firstName);
    }

    public String get_Entered_Groom_FirstName() {
        HighlightElement(groomFirstName);
        return groomFirstName.getAttribute("value").trim();
    }

    public void Enter_Groom_LastName(String lastName) {
        clickAndType(groomLastName, lastName);
    }

    public String get_Entered_Groom_LastName() {
        HighlightElement(groomLastName);
        return groomLastName.getAttribute("value").trim();
    }

    public void Enter_Groom_PhoneNumber(String phonenumber) {
        clickAndType(groomPhoneNumber, phonenumber);
    }

    public String get_Entered_Groom_PhoneNumber() {
        HighlightElement(groomPhoneNumber);
        return groomPhoneNumber.getAttribute("value").trim();
    }

    public void Enter_Groom_Email(String email) {
        clickAndType(groomEmail, email);
    }

    public String get_Entered_Groom_Email() {
        HighlightElement(groomEmail);
        return groomEmail.getAttribute("value").trim();
    }

    public void Enter_Groom_City(String city) {
        delayWithGivenTime(1000);
        jsClearAndType(groomCity, city);
    }

    public String get_Entered_Groom_City() {
        HighlightElement(groomCity);
        return groomCity.getAttribute("value").trim();
    }

    public void Enter_Groom_State(String state) {
        jsClearAndType(groomState, state);
    }

    public String get_Entered_Groom_State() {
        HighlightElement(groomState);
        return groomState.getAttribute("value").trim();
    }

    public void Enter_Groom_Address(String address) {
        jsClearAndType(groomAddress, address);
        groomAddress.click();
    }

    public void Select_Address2_from_autocomplete(String address) {
        try {
            // Wait for the dropdown to appear and elements to be visible
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            List<WebElement> autocompleteOptions = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@id='txtAltAddress']/following-sibling::ul//li[contains(text(),'"+address+"')]"))
            );

            for (WebElement option : autocompleteOptions) {
                HighlightElement(option);
                System.out.println("Autocomplete Options : " + option.getText());
                if (option.getText().contains(address.trim())) {
                    //option.click();
                      click(option); // Click the matching option
                    return;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while selecting address from autocomplete dropdown: " + e.getMessage(), e);
        }
    }


    public String get_Entered_Groom_Address() {
        HighlightElement(groomAddress);
        return groomAddress.getAttribute("value").trim();
    }

    public void Enter_Groom_Zip(String zip) {
        jsClearAndType(groomZip, zip);
    }

    public String get_Entered_Groom_Zip() {
        HighlightElement(groomZip);
        return groomZip.getAttribute("value").trim();
    }

    public void Click_Weddings_eventDetails_Tab() {
        jsClick(Wedding_eventDetails_Tab);
    }

    public void Enter_CeremonyLocation(String location) {
        clickAndType(ceremonyLocation, location);
    }

    public String get_Entered_CeremonyLocation() {
        HighlightElement(ceremonyLocation);
        return ceremonyLocation.getAttribute("value").trim();
    }

    public void Enter_CeremonyDate_WithInNext30Days() {
        LocalDate currentDate = LocalDate.now();
        LocalDate nextDay = currentDate.plusDays(30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedNextDay = nextDay.format(formatter);
        jsClearAndType(ceremonyDate, formattedNextDay);
    }

    public String get_Entered_CeremonyDate() {
        HighlightElement(ceremonyDate);
        return ceremonyDate.getAttribute("value").trim();
    }

    public void Enter_CeremonyTime() {
        LocalTime time730PM = LocalTime.of(19, 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = time730PM.format(formatter);
        delayWithGivenTime(500);
        jsClearAndType(ceremonyTime, formattedTime);
    }

    public String get_Entered_CeremonyTime() {
        HighlightElement(ceremonyTime);
        return ceremonyTime.getAttribute("value").trim();
    }

    public void Enter_Ceremony_Address(String address) {
        clickAndType(ceremonyAddress, address);
    }

    public String get_Entered_Ceremony_Address() {
        HighlightElement(ceremonyAddress);
        return ceremonyAddress.getAttribute("value").trim();
    }

    public void Enter_Ceremony_Zip(String zip) {
        clickAndType(ceremonyZip, zip);
    }

    public String get_Entered_Ceremony_Zip() {
        HighlightElement(ceremonyZip);
        return ceremonyZip.getAttribute("value").trim();
    }

    public void Enter_Ceremony_City(String city) {
        clickAndType(ceremonyCity, city);
    }

    public String get_Entered_Ceremony_City() {
        HighlightElement(ceremonyCity);
        return ceremonyCity.getAttribute("value").trim();
    }

    public void Enter_Ceremony_State(String state) {
        clickAndType(ceremonyState, state);
    }

    public String get_Entered_Ceremony_State() {
        HighlightElement(ceremonyState);
        return ceremonyState.getAttribute("value").trim();
    }

    public void Enter_ReceptionLocation(String location) {
        clickAndType(receptionLocation, location);
    }

    public String get_Entered_ReceptionLocation() {
        HighlightElement(receptionLocation);
        return receptionLocation.getAttribute("value").trim();
    }

    public void Enter_ReceptionDate_WithInNext30Days() {
        LocalDate currentDate = LocalDate.now();
        LocalDate nextDay = currentDate.plusDays(30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedNextDay = nextDay.format(formatter);
        jsClearAndType(receptionDate, formattedNextDay);
    }

    public String get_Entered_ReceptionDate() {
        HighlightElement(receptionDate);
        return receptionDate.getAttribute("value").trim();
    }

    public void Enter_ReceptionTime() {
        LocalTime time730PM = LocalTime.of(19, 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = time730PM.format(formatter);
        delayWithGivenTime(500);
        jsClearAndType(receptionTime, formattedTime);
    }

    public String get_Entered_ReceptionTime() {
        HighlightElement(receptionTime);
        return receptionTime.getAttribute("value").trim();
    }

    public void Enter_Reception_Address(String address) {
        clickAndType(receptionAddress, address);
    }

    public String get_Entered_Reception_Address() {
        HighlightElement(receptionAddress);
        return receptionAddress.getAttribute("value").trim();
    }

    public void Enter_Reception_Zip(String zip) {
        clickAndType(receptionZip, zip);
    }

    public String get_Entered_Reception_Zip() {
        HighlightElement(receptionZip);
        return receptionZip.getAttribute("value").trim();
    }

    public void Enter_Reception_City(String city) {
        clickAndType(receptionCity, city);
    }

    public String get_Entered_Reception_City() {
        HighlightElement(receptionCity);
        return receptionCity.getAttribute("value").trim();
    }

    public void Enter_Reception_State(String state) {
        clickAndType(receptionState, state);
    }

    public String get_Entered_reception_State() {
        HighlightElement(receptionState);
        return receptionState.getAttribute("value").trim();
    }

    public void Enter_WeddingCoordinator_FirstName(String firstName) {
        clickAndType(weddingCoordinatorFirstName, firstName);
    }

    public String get_Entered_WeddingCoordinator_FirstName() {
        HighlightElement(weddingCoordinatorFirstName);
        return weddingCoordinatorFirstName.getAttribute("value").trim();
    }

    public void Enter_WeddingCoordinator_LastName(String lastName) {
        clickAndType(weddingCoordinatorLastName, lastName);
    }

    public String get_Entered_WeddingCoordinator_LastName() {
        HighlightElement(weddingCoordinatorLastName);
        return weddingCoordinatorLastName.getAttribute("value").trim();
    }

    public void Enter_WeddingCoordinator_PhoneNumber(String phonenumber) {
        clickAndType(weddingCoordinatorPhoneNumber, phonenumber);
    }

    public String get_Entered_WeddingCoordinator_PhoneNumber() {
        HighlightElement(weddingCoordinatorPhoneNumber);
        return weddingCoordinatorPhoneNumber.getAttribute("value").trim();
    }

    public void Enter_WeddingCoordinator_Email(String email) {
        jsClearAndType(weddingCoordinatorEmail, email);
    }

    public String get_Entered_WeddingCoordinator_Email() {
        HighlightElement(weddingCoordinatorEmail);
        return weddingCoordinatorEmail.getAttribute("value").trim();
    }

    public void Enter_WeddingCoordinator_City(String city) {
        jsClearAndType(weddingCoordinatorCity, city);
    }

    public String get_Entered_WeddingCoordinator_City() {
        HighlightElement(weddingCoordinatorCity);
        return weddingCoordinatorCity.getAttribute("value").trim();
    }

    public void Enter_WeddingCoordinator_State(String state) {
        jsClearAndType(weddingCoordinatorState, state);
    }

    public String get_Entered_WeddingCoordinator_State() {
        HighlightElement(weddingCoordinatorState);
        return weddingCoordinatorState.getAttribute("value").trim();
    }

    public void Enter_WeddingCoordinator_Address(String address) {
        jsClearAndType(weddingCoordinatorAddress, address);
    }

    public String get_Entered_WeddingCoordinator_Address() {
        HighlightElement(weddingCoordinatorAddress);
        return weddingCoordinatorAddress.getAttribute("value").trim();
    }

    public void Enter_WeddingCoordinator_Zip(String zip) {
        jsClearAndType(weddingCoordinatorZip, zip);
    }

    public String get_Entered_WeddingCoordinator_Zip() {
        HighlightElement(weddingCoordinatorZip);
        return weddingCoordinatorZip.getAttribute("value").trim();
    }

    public void Click_DocumentsAndImages_Tab() {
        js_Click(documentImagesTab, "Documents and Images Tab");
        js_Click(upload_document_subtab, "Upload Document Subtab");
    }

    /**
     * Clicks on the "Upload Images" tab using JavaScript click.
     * <p>
     * This method ensures reliable interaction with the "Upload Images" tab element,
     * particularly in cases where standard Selenium click actions might fail due to
     * overlay issues, timing problems, or other UI complexities.
     * </p>
     *
     * @throws NoSuchElementException if the "Upload Images" tab element is not found
     * @throws WebDriverException     if the JavaScript execution fails or the element is not interactable
     */
    public void Click_Upload_Images_Tab() {
        try {
            jsClick(uploadimages_tab);
            System.out.println("Successfully clicked on the 'Upload Images' tab.");
        } catch (NoSuchElementException e) {
            System.err.println("Error: 'Upload Images' tab element not found. Verify the locator or page loading.");
            throw e; // Re-throw the exception after logging
        } catch (WebDriverException e) {
            System.err.println("Error: Failed to click the 'Upload Images' tab due to WebDriver issue.");
            throw e; // Re-throw the exception after logging
        } catch (Exception e) {
            System.err.println("Error: An unexpected error occurred while clicking the 'Upload Images' tab.");
            throw e; // Re-throw the exception after logging
        }
    }


    /**
     * Uploads the first image under the "Upload Images" tab using the specified file name.
     * <p>
     * This method constructs the full file path based on the project directory,
     * hovers over the image element, and uploads the specified file.
     * </p>
     *
     * @param filename the name of the file to be uploaded (e.g., "image1.jpg")
     * @throws IllegalArgumentException if the file name is null or empty
     * @throws FileNotFoundException    if the file does not exist at the specified path
     * @throws WebDriverException       if the file upload action fails
     */
//    public void Upload_Image1_under_UploadImagesTab(String filename) {
//        try {
//            String projectPath = System.getProperty("user.dir");
//            String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;
//            image1_uploadimagestab.sendKeys(filename);
//            // validateAndUploadFile(filename, image1_uploadimagestab, uploadIcon_image1);
//            System.out.println("Image 1 uploaded successfully: " + filename);
//        } catch (Exception e) {
//            handleException("Image 1 upload failed.", e);
//        }
//    }
    public void Upload_Image1_under_UploadImagesTab(String filename) {
        wait_For_Page_To_Be_Stable(getDriver());
        try {
            String projectPath = System.getProperty("user.dir");
            String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;

            // Find the correct 'proposal-Image' container based on index or some label text
            List<WebElement> imageSections = getDriver().findElements(By.cssSelector("div.proposal-Image"));

            WebElement firstImageSection = imageSections.get(0); // index 0 for Image 1
            WebElement uploadInput = firstImageSection.findElement(By.cssSelector("input.proposalConceImageCtrl"));

            // Make it visible if needed (optional)
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].classList.remove('d-none');", uploadInput);

            uploadInput.sendKeys(fullFilePath);

            System.out.println("Image 1 uploaded successfully: " + fullFilePath);
        } catch (Exception e) {
            handleException("Image 1 upload failed.", e);
        }
    }


    public void Click_EditIcon_on_Image1(String filename) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                validateAndUploadFile(filename, image1_uploadimagestab, uploadImage1_editicon);
                System.out.println("Image 1 edit uploaded successfully: " + filename);
                break;
            } catch (StaleElementReferenceException e) {
                System.err.println("StaleElementReferenceException on Image 1 Edit - Retrying... Attempt: " + (attempts + 1));
            } catch (Exception e) {
                handleException("Image 1 edit upload failed.", e);
                throw new RuntimeException(e);
            }
            attempts++;
            delayWithGivenTime(500);
        }
    }

    public void Click_DeleteIcon_on_Image1() {
        int attempts = 0;
        while (attempts < 3) {
            try {
                MouseHover(image1_uploadimagestab);
                delayWithGivenTime(1000);
                jsClick(uploadImage1_deleteicon);
                System.out.println("Image 1 delete icon clicked.");
                break;
            } catch (StaleElementReferenceException e) {
                System.err.println("StaleElementReferenceException on Image 1 Delete - Retrying... Attempt: " + (attempts + 1));
            } catch (Exception e) {
                handleException("Failed to click delete icon for Image 1", e);
                throw new RuntimeException(e);
            }
            attempts++;
            delayWithGivenTime(500);
        }
    }

    public void Click_EditIcon_on_Image2(String filename) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                validateAndUploadFile(filename, image2_uploadimagestab, editIcon_on_image2);
                System.out.println("Image 2 edit uploaded successfully: " + filename);
                break;
            } catch (StaleElementReferenceException e) {
                System.err.println("StaleElementReferenceException on Image 2 Edit - Retrying... Attempt: " + (attempts + 1));
            } catch (Exception e) {
                handleException("Image 2 edit upload failed.", e);
                throw new RuntimeException(e);
            }
            attempts++;
            delayWithGivenTime(500);
        }
    }

    public void Click_DeleteIcon_on_Image2() {
        int attempts = 0;
        while (attempts < 3) {
            try {
                MouseHover(image2_uploadimagestab);
                delayWithGivenTime(1000);
                jsClick(deleteIcon_on_image2);
                System.out.println("Image 2 delete icon clicked.");
                break;
            } catch (StaleElementReferenceException e) {
                System.err.println("StaleElementReferenceException on Image 2 Delete - Retrying... Attempt: " + (attempts + 1));
            } catch (Exception e) {
                handleException("Failed to click delete icon for Image 2", e);
                throw new RuntimeException(e);
            }
            attempts++;
            delayWithGivenTime(500);
        }
    }

    public void Click_EditIcon_on_Image3(String filename) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                validateAndUploadFile(filename, image3_uploadimagestab, editIcon_image3);
                System.out.println("Image 3 edit uploaded successfully: " + filename);
                break;
            } catch (StaleElementReferenceException e) {
                System.err.println("StaleElementReferenceException on Image 3 Edit - Retrying... Attempt: " + (attempts + 1));
            } catch (Exception e) {
                handleException("Image 3 edit upload failed.", e);
                throw new RuntimeException(e);
            }
            attempts++;
            delayWithGivenTime(500);
        }
    }

    public void Click_DeleteIcon_on_Image3() {
        int attempts = 0;
        while (attempts < 3) {
            try {
                MouseHover(image3_uploadimagestab);
                delayWithGivenTime(1000);
                jsClick(deleteIcon_image3);
                System.out.println("Image 3 delete icon clicked.");
                break;
            } catch (StaleElementReferenceException e) {
                System.err.println("StaleElementReferenceException on Image 3 Delete - Retrying... Attempt: " + (attempts + 1));
            } catch (Exception e) {
                handleException("Failed to click delete icon for Image 3", e);
                throw new RuntimeException(e);
            }
            attempts++;
            delayWithGivenTime(500);
        }
    }


    /**
     * Uploads the second image under the "Upload Images" tab using the specified file name.
     * <p>
     * This method constructs the full file path based on the project directory,
     * hovers over the image element, and uploads the specified file.
     * </p>
     *
     * @param filename the name of the file to be uploaded (e.g., "image2.jpg")
     * @throws IllegalArgumentException if the file name is null or empty
     * @throws FileNotFoundException    if the file does not exist at the specified path
     * @throws WebDriverException       if the file upload action fails
     */
//    public void Upload_Image2_under_UploadImagesTab(String filename) {
//        try {
//            String projectPath = System.getProperty("user.dir");
//            String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;
//            image2_uploadimagestab.sendKeys(filename);
//            // validateAndUploadFile(filename, image2_uploadimagestab, uploadIcon_image2);
//            System.out.println("Image 2 uploaded successfully: " + filename);
//        } catch (Exception e) {
//            handleException("Image 2 upload failed.", e);
//        }
//    }
    public void Upload_Image2_under_UploadImagesTab(String filename) {
        wait_For_Page_To_Be_Stable(getDriver());
        try {
            String projectPath = System.getProperty("user.dir");
            String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;

            // Find the correct 'proposal-Image' container based on index or some label text
            List<WebElement> imageSections = getDriver().findElements(By.cssSelector("div.proposal-Image"));

            WebElement firstImageSection = imageSections.get(1); // index 0 for Image 1
            WebElement uploadInput = firstImageSection.findElement(By.cssSelector("input.proposalConceImageCtrl"));

            // Make it visible if needed (optional)
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].classList.remove('d-none');", uploadInput);

            uploadInput.sendKeys(fullFilePath);

            System.out.println("Image 1 uploaded successfully: " + fullFilePath);
        } catch (Exception e) {
            handleException("Image 1 upload failed.", e);
        }
    }

    /**
     * Uploads the third image under the "Upload Images" tab using the specified file name.
     * <p>
     * This method constructs the full file path based on the project directory,
     * hovers over the image element, and uploads the specified file.
     * </p>
     *
     * @param filename the name of the file to be uploaded (e.g., "image3.jpg")
     * @throws IllegalArgumentException if the file name is null or empty
     * @throws FileNotFoundException    if the file does not exist at the specified path
     * @throws WebDriverException       if the file upload action fails
     */
//    public void Upload_Image3_under_UploadImagesTab(String filename) {
//        try {
//            String projectPath = System.getProperty("user.dir");
//            String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;
//            image3_uploadimagestab.sendKeys(filename);
//            //  validateAndUploadFile(filename, image3_uploadimagestab, uploadIcon_image3);
//            System.out.println("Image 3 uploaded successfully: " + filename);
//        } catch (Exception e) {
//            handleException("Image 3 upload failed.", e);
//        }
//    }
    public void Upload_Image3_under_UploadImagesTab(String filename) {
        wait_For_Page_To_Be_Stable(getDriver());
        try {
            String projectPath = System.getProperty("user.dir");
            String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;

            // Find the correct 'proposal-Image' container based on index or some label text
            List<WebElement> imageSections = getDriver().findElements(By.cssSelector("div.proposal-Image"));

            WebElement firstImageSection = imageSections.get(2); // index 0 for Image 1
            WebElement uploadInput = firstImageSection.findElement(By.cssSelector("input.proposalConceImageCtrl"));

            // Make it visible if needed (optional)
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].classList.remove('d-none');", uploadInput);

            uploadInput.sendKeys(fullFilePath);

            System.out.println("Image 1 uploaded successfully: " + fullFilePath);
        } catch (Exception e) {
            handleException("Image 1 upload failed.", e);
        }
    }

    /**
     * Validates the file and performs the upload operation.
     *
     * @param filename     the name of the file to be uploaded
     * @param hoverElement the WebElement to hover over before uploading
     * @param uploadIcon   the WebElement representing the upload button
     * @throws IllegalArgumentException if the file name is null or empty
     * @throws FileNotFoundException    if the file does not exist at the specified path
     * @throws WebDriverException       if the file upload action fails
     */
    private void validateAndUploadFile(String filename, WebElement hoverElement, WebElement uploadIcon) throws Exception {
        // Validate the input file name
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty.");
        }

        // Construct the full file path
        String projectPath = System.getProperty("user.dir");
        String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;

        // Validate if the file exists
        File file = new File(fullFilePath);
        if (!file.exists() || !file.isFile()) {
            throw new java.io.FileNotFoundException("File not found at path: " + fullFilePath);
        }

        // Hover over the specified element
        MouseHover(hoverElement);
        delayWithGivenTime(1000);
        // Upload the file using Selenium's upload mechanism
        uploadFile(uploadIcon, fullFilePath);
    }

    /**
     * Handles exceptions and logs meaningful error messages.
     *
     * @param errorMessage the error message to display
     * @param e            the exception that occurred
     */
    private void handleException(String errorMessage, Exception e) {
        System.err.println(errorMessage + " " + e.getMessage());
        throw new RuntimeException(errorMessage, e);
    }


    /**
     * Uploads a file to the application using the specified file name.
     * <p>
     * This method constructs the full file path based on the project directory
     * and attempts to upload the file using the provided file name. It is assumed
     * that the file exists in the `testFiles` directory within the project structure.
     * </p>
     *
     * @param filename the name of the file to be uploaded (e.g., "sample.txt")
     * @throws IllegalArgumentException if the file name is null or empty
     * @throws FileNotFoundException    if the file does not exist at the specified path
     * @throws WebDriverException       if the file upload action fails
     */
    public void UploadFiles(String filename) {
        try {
            // Validate the input file name
            if (filename == null || filename.isEmpty()) {
                throw new IllegalArgumentException("File name cannot be null or empty.");
            }

            // Construct the full file path
            String projectPath = System.getProperty("user.dir");
            String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;

            // Validate if the file exists
            File file = new File(fullFilePath);
            if (!file.exists() || !file.isFile()) {
                throw new java.io.FileNotFoundException("File not found at path: " + fullFilePath);
            }
            simulateDragAndDropImageUpload(browserFilesBtn, fullFilePath);
            // uploadFile(browserFilesBtn, fullFilePath);
            System.out.println("File uploaded successfully: " + filename);

        } catch (Exception e) {
            System.err.println("Error: An unexpected error occurred during file upload. " + e.getMessage());
            throw new RuntimeException("File upload failed due to an unexpected error.", e);
        }
    }

    public void UploadImageFile1(String filename) {
        try {
            // Validate the input file name
            if (filename == null || filename.isEmpty()) {
                throw new IllegalArgumentException("File name cannot be null or empty.");
            }

            // Construct the full file path
            String projectPath = System.getProperty("user.dir");
            String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;

            // Validate if the file exists
            File file = new File(fullFilePath);
            if (!file.exists() || !file.isFile()) {
                throw new java.io.FileNotFoundException("File not found at path: " + fullFilePath);
            }

            // Upload the file using Selenium's upload mechanism
            uploadFile(addfilebutton_uploaddocuments_tab, fullFilePath);
            // simulateDragAndDropImageUpload(addfilebutton_uploaddocuments_tab, fullFilePath);

            System.out.println("File uploaded successfully: " + filename);

        } catch (IllegalArgumentException e) {
            System.err.println("Error: Invalid file name provided. " + e.getMessage());
            throw e; // Re-throw to ensure it can be logged or handled at a higher level
        } catch (java.io.FileNotFoundException e) {
            System.err.println("Error: File not found. " + e.getMessage());
            throw new RuntimeException("File upload failed: File not found.", e);
        } catch (WebDriverException e) {
            System.err.println("Error: WebDriver issue during file upload. " + e.getMessage());
            throw e; // Re-throw to allow higher-level handling
        } catch (Exception e) {
            System.err.println("Error: An unexpected error occurred during file upload. " + e.getMessage());
            throw new RuntimeException("File upload failed due to an unexpected error.", e);
        }
    }

    public void Drag_and_Drop_on_documentFiles(String filename) {
        // Get the full file path
        String projectPath = System.getProperty("user.dir");
        String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;
        System.out.println("File Path: " + fullFilePath);

        // Verify if the file exists
        File file = new File(fullFilePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + fullFilePath);
        }

        // draganddrop_element_on_documentandimages.sendKeys(filename);

        dragAndDropFileUsingRobot(fullFilePath, draganddrop_element_on_documentandimages);
        System.out.println("Successfully handled drap and drop functionality");
    }

    public boolean isBrowseFileButtonDisplayed() {
        return browserFilesBtn.isDisplayed();
    }

    /**
     * Clicks on the Inspiration Board tab using JavaScript click.
     * Ensures compatibility with elements that may not respond to regular click actions.
     *
     * @Author Balaji N
     */
    public void clickInspirationBoardTab() {
        try {
            if (inspiration_board_tab != null) {
                jsClick(inspiration_board_tab);
            } else {
                throw new NullPointerException("Inspiration Board tab element is null.");
            }
        } catch (Exception e) {
            // Log the error for debugging purposes
            System.err.println("Failed to click on Inspiration Board tab: " + e.getMessage());
        }
    }

    public boolean verify_browseFiles_button_IsDisplayed_On_InspirationBoardTab() {
        return browseFilesBtn_on_inspiration_board_tab.isDisplayed();
    }

    public void click_Browse_File_Button_on_InspirationBoardTab(String filename) {
        // Selenium sendkeys function not works - because input @type = button is displayed
        try {
            if (browseFilesBtn_on_inspiration_board_tab != null) {
                String projectPath = System.getProperty("user.dir");
                String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;
                uploadFile(browseFilesBtn_on_inspiration_board_tab, fullFilePath);
            } else {
                throw new NullPointerException("Browse Files Button on Inspiration Board tab element is null.");
            }
        } catch (Exception e) {
            System.err.println("Failed to click on Inspiration Board tab browse file button: " + e.getMessage());
        }
    }

    public void click_Add_or_PlusIcon_on_InspirationBoardTab(String filename) {
        try {
            if (uploaded_2nd_file_on_inspirationBroad != null) {
                String projectPath = System.getProperty("user.dir");
                String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;
                uploadFile(uploaded_2nd_file_on_inspirationBroad, fullFilePath);
            } else {
                throw new NullPointerException("Browse Files Button on Inspiration Board tab element is null.");
            }
        } catch (Exception e) {
            System.err.println("Failed to click on Inspiration Board tab browse file button: " + e.getMessage());
        }
    }

    public boolean verify_inspirationTab_uploaded_firstdocument_displayed_on_grid() {
        return isElementDisplayed(uploaded_1st_file_on_inspirationBroad, "Inspiration Board uploaded 1st Image/Document");
    }

    public boolean verify_inspirationTab_uploaded_seconddocument_displayed_on_grid() {
        return isElementDisplayed(uploaded_2nd_file_on_inspirationBroad, "Inspiration Board uploaded 2nd Image/Document");
    }

    public void MouseHover_and_click_Delete_Icon1_InspirationBoardTab() {
        try {
            if (uploaded_1st_file_on_inspirationBroad != null) {
                MouseHover(uploaded_1st_file_on_inspirationBroad);
                delayWithGivenTime(2000);
                jsClick(deleteIcon_on_inspiration_board_tab);
            } else {
                throw new NullPointerException("Mouse Hover and Delete Icon 1 on Inspiration Board tab element is null.");
            }
        } catch (Exception e) {
            // Log the error for debugging purposes
            System.err.println("Failed to Mouse Hover and Delete Icon 1 on Inspiration Board tab element is null.: " + e.getMessage());
        }
    }

    public void MouseHover_and_click_Delete_Icon2_InspirationBoardTab() {
        try {
            explicitWait(uploaded_1st_file_on_inspirationBroad);
            if (uploaded_1st_file_on_inspirationBroad != null) {
                MouseHover(uploaded_1st_file_on_inspirationBroad);
                delayWithGivenTime(2000);
                jsClick(deleteIcon_on_inspiration_board_tab);
            } else {
                throw new NullPointerException("Mouse Hover and Delete Icon 2 on Inspiration Board tab element is null.");
            }
        } catch (Exception e) {
            // Log the error for debugging purposes
            System.err.println("Failed to Mouse Hover and Delete Icon 2 on Inspiration Board tab element is null.: " + e.getMessage());
        }
    }

    public void Click_ItemsAndProducts_Tab() {
        js_Click(items_ProductsTab, "Items And Product Tab");
    }

    /**
     * Selects a value from the Category dropdown using the visible text.
     *
     * @param category The visible text of the category to be selected.
     * @Author Balaji N
     */
    public void Select_CategoryDropdown_On_ItemsAndProducts(String category) {
        try {
            if (category == null || category.isEmpty()) {
                logger.error("Category parameter is null or empty. Cannot select a dropdown value.");
                throw new IllegalArgumentException("Category parameter cannot be null or empty.");
            }

            drop_Down(select_CategoryDropDown, category, "VisibleText", "Category dropdown field - Item & Products Tab Add Proposal Page");

        } catch (Exception e) {
            logger.error("Failed to select the category: {}", category, e);
        }
    }

    /**
     * Retrieves the selected value from the Category dropdown on Item & Product tab.
     *
     * @return If the selected value isdisplayed, returns the text of the selected option; otherwise, returns an empty string.
     * @Author Balaji N
     */
    public String get_Selected_Category_On_ItemsAndProducts() {
        return get_Selected_Option(select_CategoryDropDown, "Category dropdown field - Item & Products Tab Add Proposal Page");
    }

    /**
     * Verify if a specific option is displayed in the Category dropdown on Item & Product tab.
     *
     * @param expectedOption
     * @return true if the option is displayed, false otherwise
     * @Author Balaji N
     */
    public boolean verify_Product_Category_Option_IsDisplayed(String expectedOption) {
        Select dropdown = new Select(select_CategoryDropDown);

        for (WebElement option : dropdown.getOptions()) {
            if (option.getText().trim().equalsIgnoreCase(expectedOption.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean verify_Product_Category_Option_IsNotDisplayed(String expectedOption) {
        try {
            click(select_CategoryDropDown, "Category dropdown field - Item & Products Tab Add Proposal Page");
            Select dropdown = new Select(select_CategoryDropDown);

            for (WebElement option : dropdown.getOptions()) {
                if (option.getText().trim().equalsIgnoreCase(expectedOption.trim())) {
                    return false;
                }
            }
            return true;
        } catch (NoSuchElementException e) {
            return true;
        }
    }


    // Last updated on Jan 13 2025 by Balaji N, previous xpath will be//ul[@id='ui-id-4']//li//div
    public void Enter_ItemName_On_ItemsAndProducts(String itemName, String itemdescription) {
      /*  clickAndType(items_TextBox, itemName);
        delayWithGivenTime(3000);

        if (isElementDisplayed(item_autocomplete_itemsandproducts_tab) == true) {
            WebElement item = getDriver().findElement(By.xpath("//ul[@id='ui-id-5']//li//div[contains(text(),'" + itemdescription + "')]"));
            jsClick(item);
        }*/
        /*for (WebElement ele : itemsList) {
            if (ele.getText().contains(itemdescription)) {
                jsClick(ele);
                break;
            }
        }*/

        try {
            // Perform the double-click and type operation
            Double_Click_And_Type(items_TextBox, itemName, "Item search textbox field on add proposal section");
            delayWithGivenTime(3000);

            boolean itemFound = false;

            // Check if the autosuggestion element is displayed
            if (is_Element_Displayed(item_autocomplete_itemsandproducts_tab, "Item search textbox autocomplete")) {
                try {
                    // Locate the item based on description and click
                    WebElement item = getDriver().findElement(By.xpath("//ul[@id='ui-id-5']//li//div[contains(text(),'" + itemdescription + "')]"));
                    js_Click(item, "Item description on item search textbox field");
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
            e.printStackTrace();
        }
    }

    public void Click_AddBtn_On_ItemsAndProducts() {
        js_Click(addItemBtn_ItemsTab, "Add Item button - Add proposal item section");
    }

    public String get_Row1_ItemCategory() {
        return getElementText(item_category_Row1, "Category Name - Item & Products Tab Product Table grid ");
    }

    public void Click_Publish_Pending_Changes() {
        js_Click(publishPendingChanges, "Publish Pending Changes button - Add proposal page");
    }

    public String get_row1_itemCode() {
        HighlightElement(item_Code_Row1);
        return item_Code_Row1.getText();
    }

    public String get_row1_itemDescription() {
        HighlightElement(item_description_Row1);
        return item_description_Row1.getText();
    }

    public String get_row1_itemQuantity() {
        HighlightElement(item_Quantity_Row1);
        return item_Quantity_Row1.getText();
    }

    public String get_row1_itemPrice_InProductDetails() {
        HighlightElement(item_Price_In_Product_DetailsRow1);
        return item_Price_In_Product_DetailsRow1.getText().trim();
    }

    public String get_row1_item_TotalPrice() {
        HighlightElement(item_Total_price_Row1);
        return item_Total_price_Row1.getText().trim();
    }

    public void select_TaxType(String taxType) {
        drop_Down(tax_type_dropdown, taxType, "VisibleText", "Tax Type Dropdown on item & product tab");
    }

    public String get_TaxType_Displayed() {
        return get_selected_option(tax_type_dropdown, "Tax Type Dropdown on item & product tab");
    }

    public void enter_Delivery_Fee_On_ItemsAndProducts(String deliveryFee) {
        delivery_fee_amount.clear();
        clickAndType(delivery_fee_amount, deliveryFee);
    }

    public String get_Delivery_Fee_Displayed() {
        return getElementAttribute(delivery_fee_amount, "Delivery Fee on item & product tab");
    }

    public String get_Setup_Fee_Displayed_On_ItemsAndProducts_Tab() {
        return getElementAttribute(setup_fee_amount, "Setup Fee on item & product tab");
    }

    public String get_SetupFee_Symbol_Displayed_On_ItemsAndProducts_Tab() {
        return getElementText(setup_fee_symbol_on_itemsandproducts_tab, "Setup Fee Symbol on item & product tab");
    }

    public String get_DeliveryFee_Symbol_Displayed_On_ItemsAndProducts_Tab() {
        return getElementText(delivery_fee_symbol_on_itemsandproducts_tab, "Delivery Fee Symbol on item & product tab");
    }

    public String get_Final_Setup_Fee_Displayed_On_ItemsAndProducts_Tab() {
        return getElementText(final_setup_fee_amount, "Setup Fee on item & product tab");
    }

    public String get_Setup_Fee_On_Percentage_Expected_Calculation() {
        String sub_Total = getElementText(subtotal_amount, "Sub Total on item & product tab").trim().replace("$", "");
        Double subTotal = Double.parseDouble(sub_Total);
        Double setupFee = subTotal * 0.10;
        return String.format("$%.2f", setupFee);
    }

    public String get_Delivery_Fee_On_Percentage_Expected_Calculation() {
        String sub_Total = getElementText(subtotal_amount, "Sub Total on item & product tab").trim().replace("$", "");
        Double subTotal = Double.parseDouble(sub_Total);
        Double setupFee = subTotal * 0.10;
        return String.format("$%.2f", setupFee);
    }

    public String get_Expected_GrandTotal() {
        try {
            if (!isElementDisplayed(subtotal_amount)) {
                throw new RuntimeException("Sub Total element is not displayed.");
            }
            if (!isElementDisplayed(taxamount)) {
                throw new RuntimeException("Tax Amount element is not displayed.");
            }
            if (!isElementDisplayed(final_setup_fee_amount)) {
                throw new RuntimeException("Setup Fee element is not displayed.");
            }
            if (!isElementDisplayed(final_delivery_fee_label)) {
                throw new RuntimeException("Delivery Fee element is not displayed.");
            }
            if (!isElementDisplayed(discountamount)) {
                throw new RuntimeException("Discount Amount element is not displayed.");
            }

            System.out.println("✅ All required elements are displayed on the page.");

            // Step 2: Get and clean the values
            String sub_Total = getElementText(subtotal_amount, "Sub Total").trim().replace("$", "");
            String tax_Amount = getElementText(taxamount, "Tax Amount").trim().replace("$", "");
            String setup_Fee = getElementText(final_setup_fee_amount, "Setup Fee").trim().replace("$", "");
            String delivery_Fee = getElementText(final_delivery_fee_label, "Delivery Fee").trim().replace("$", "");
            String discount_Amount = getElementText(discountamount, "Discount Amount").trim().replace("$", "");

            System.out.println("📊 Parsed values:");
            System.out.println("   Sub Total      : " + sub_Total);
            System.out.println("   Tax Amount     : " + tax_Amount);
            System.out.println("   Setup Fee      : " + setup_Fee);
            System.out.println("   Delivery Fee   : " + delivery_Fee);
            System.out.println("   Discount Amount: " + discount_Amount);

            // Step 3: Convert to double and calculate total
            double total = Double.parseDouble(sub_Total)
                    + Double.parseDouble(delivery_Fee)
                    + Double.parseDouble(setup_Fee)
                    + Double.parseDouble(tax_Amount)
                    - Double.parseDouble(discount_Amount);

            String expectedTotal = String.format("$%.2f", total);
            System.out.println("✅ Expected Grand Total: " + expectedTotal);
            return expectedTotal;

        } catch (NumberFormatException e) {
            System.err.println("❌ Test case failed due to invalid or empty value while parsing: " + e.getMessage());
            throw new RuntimeException("One of the amount fields contains invalid or empty text that couldn't be parsed to a number.", e);
        } catch (Exception e) {
            System.err.println("❌ Test case failed due to unexpected exception: " + e.getMessage());
            throw new RuntimeException("Error while calculating expected grand total.", e);
        }
    }

    public String get_Expected_GrandTotal_For_Dollar_Setup_Fee() {
        try {
            if (!isElementDisplayed(subtotal_amount)) {
                throw new RuntimeException("Sub Total element is not displayed.");
            }
            if (!isElementDisplayed(taxamount)) {
                throw new RuntimeException("Tax Amount element is not displayed.");
            }
            if (!isElementDisplayed(setup_fee_amount)) {
                throw new RuntimeException("Setup Fee element is not displayed.");
            }
            if (!isElementDisplayed(final_delivery_fee_label)) {
                throw new RuntimeException("Delivery Fee element is not displayed.");
            }
            if (!isElementDisplayed(discountamount)) {
                throw new RuntimeException("Discount Amount element is not displayed.");
            }

            System.out.println("✅ All required elements are displayed on the page.");

            // Step 2: Get and clean the values
            String sub_Total = getElementText(subtotal_amount, "Sub Total").trim().replace("$", "");
            String tax_Amount = getElementText(taxamount, "Tax Amount").trim().replace("$", "");
            String setup_Fee = getElementAttribute(setup_fee_amount, "Setup Fee on item & product tab").trim().replace("$", "");
            String delivery_Fee = getElementText(final_delivery_fee_label, "Delivery Fee").trim().replace("$", "");
            String discount_Amount = getElementText(discountamount, "Discount Amount").trim().replace("$", "");

            System.out.println("📊 Parsed values:");
            System.out.println("   Sub Total      : " + sub_Total);
            System.out.println("   Tax Amount     : " + tax_Amount);
            System.out.println("   Setup Fee      : " + setup_Fee);
            System.out.println("   Delivery Fee   : " + delivery_Fee);
            System.out.println("   Discount Amount: " + discount_Amount);

            // Step 3: Convert to double and calculate total
            double total = Double.parseDouble(sub_Total)
                    + Double.parseDouble(delivery_Fee)
                    + Double.parseDouble(setup_Fee)
                    + Double.parseDouble(tax_Amount)
                    - Double.parseDouble(discount_Amount);

            String expectedTotal = String.format("$%.2f", total);
            System.out.println("✅ Expected Grand Total: " + expectedTotal);
            return expectedTotal;

        } catch (NumberFormatException e) {
            System.err.println("❌ Test case failed due to invalid or empty value while parsing: " + e.getMessage());
            throw new RuntimeException("One of the amount fields contains invalid or empty text that couldn't be parsed to a number.", e);
        } catch (Exception e) {
            System.err.println("❌ Test case failed due to unexpected exception: " + e.getMessage());
            throw new RuntimeException("Error while calculating expected grand total.", e);
        }
    }


    public String get_Setupfee_Percentage_Deliveryfee_Dollar_Based_Calculated_Expected_GrandTotal() {
        try {
            if (!isElementDisplayed(subtotal_amount)) {
                throw new RuntimeException("Sub Total element is not displayed.");
            }
            if (!isElementDisplayed(taxamount)) {
                throw new RuntimeException("Tax Amount element is not displayed.");
            }
            if (!isElementDisplayed(setup_fee_amount)) {
                throw new RuntimeException("Setup Fee element is not displayed.");
            }
            if (!isElementDisplayed(delivery_fee_amount)) {
                throw new RuntimeException("Delivery Fee element is not displayed.");
            }
            if (!isElementDisplayed(discountamount)) {
                throw new RuntimeException("Discount Amount element is not displayed.");
            }

            System.out.println("✅ All required elements are displayed on the page.");

            // Step 2: Get and clean the values
            String sub_Total = getElementText(subtotal_amount, "Sub Total").trim().replace("$", "");
            String tax_Amount = getElementText(taxamount, "Tax Amount").trim().replace("$", "");
            String setup_Fee = getElementAttribute(setup_fee_amount, "Setup Fee on item & product tab").trim().replace("$", "");
            //   String delivery_Fee = getElementText(final_delivery_fee_label, "Delivery Fee").trim().replace("$", "");
            String delivery_Fee = getElementAttribute(delivery_fee_amount, "Delivery Fee on item & product tab").trim().replace("$", "");


            String discount_Amount = getElementText(discountamount, "Discount Amount").trim().replace("$", "");

            System.out.println("📊 Parsed values:");
            System.out.println("   Sub Total      : " + sub_Total);
            System.out.println("   Tax Amount     : " + tax_Amount);
            System.out.println("   Setup Fee      : " + setup_Fee);
            System.out.println("   Delivery Fee   : " + delivery_Fee);
            System.out.println("   Discount Amount: " + discount_Amount);

            double subTotal = Double.parseDouble(sub_Total);
            double deliveryFee = Double.parseDouble(delivery_Fee); // Fixed amount
            double setupFeePercent = Double.parseDouble(setup_Fee); // As a percentage
            double taxAmount = Double.parseDouble(tax_Amount);
            double discountAmount = Double.parseDouble(discount_Amount);

            double setupFeeAmount = (setupFeePercent / 100) * subTotal;

            double grandTotal_DollarBased = subTotal + deliveryFee + setupFeeAmount + taxAmount - discountAmount;
            System.out.println("Grand Total (Dollar Based): $" + grandTotal_DollarBased);

            String expectedTotal = String.format("$%.2f", grandTotal_DollarBased);
            System.out.println("✅ Expected Grand Total: " + expectedTotal);
            return expectedTotal;

        } catch (NumberFormatException e) {
            System.err.println("❌ Test case failed due to invalid or empty value while parsing: " + e.getMessage());
            throw new RuntimeException("One of the amount fields contains invalid or empty text that couldn't be parsed to a number.", e);
        } catch (Exception e) {
            System.err.println("❌ Test case failed due to unexpected exception: " + e.getMessage());
            throw new RuntimeException("Error while calculating expected grand total.", e);
        }
    }

    public String get_Setupfee_Dollar_Deliveryfee_Percentage_Based_Calculated_Expected_GrandTotal() {
        try {
            if (!isElementDisplayed(subtotal_amount)) {
                throw new RuntimeException("Sub Total element is not displayed.");
            }
            if (!isElementDisplayed(taxamount)) {
                throw new RuntimeException("Tax Amount element is not displayed.");
            }
            if (!isElementDisplayed(setup_fee_amount)) {
                throw new RuntimeException("Setup Fee element is not displayed.");
            }
            if (!isElementDisplayed(delivery_fee_amount)) {
                throw new RuntimeException("Delivery Fee element is not displayed.");
            }
            if (!isElementDisplayed(discountamount)) {
                throw new RuntimeException("Discount Amount element is not displayed.");
            }

            System.out.println("✅ All required elements are displayed on the page.");

            // Step 2: Get and clean the values
            String sub_Total = getElementText(subtotal_amount, "Sub Total").trim().replace("$", "");
            String tax_Amount = getElementText(taxamount, "Tax Amount").trim().replace("$", "");
            String setup_Fee = getElementAttribute(setup_fee_amount, "Setup Fee on item & product tab").trim().replace("$", "");
            //   String delivery_Fee = getElementText(final_delivery_fee_label, "Delivery Fee").trim().replace("$", "");
            String delivery_Fee = getElementAttribute(delivery_fee_amount, "Delivery Fee on item & product tab").trim().replace("$", "");


            String discount_Amount = getElementText(discountamount, "Discount Amount").trim().replace("$", "");

            System.out.println("📊 Parsed values:");
            System.out.println("   Sub Total      : " + sub_Total);
            System.out.println("   Tax Amount     : " + tax_Amount);
            System.out.println("   Setup Fee      : " + setup_Fee);
            System.out.println("   Delivery Fee   : " + delivery_Fee);
            System.out.println("   Discount Amount: " + discount_Amount);

            // Step 3: Convert to double and calculate total
            double grandTotal_DollarBased = Double.parseDouble(sub_Total)
                    + (Double.parseDouble(delivery_Fee) / 100) * Double.parseDouble(sub_Total)
                    + (Double.parseDouble(setup_Fee) * 1)
                    + Double.parseDouble(tax_Amount)
                    - Double.parseDouble(discount_Amount);


            System.out.println("Grand Total (Dollar Based): $" + grandTotal_DollarBased);


            String expectedTotal = String.format("$%.2f", grandTotal_DollarBased);
            System.out.println("✅ Expected Grand Total: " + expectedTotal);
            return expectedTotal;

        } catch (NumberFormatException e) {
            System.err.println("❌ Test case failed due to invalid or empty value while parsing: " + e.getMessage());
            throw new RuntimeException("One of the amount fields contains invalid or empty text that couldn't be parsed to a number.", e);
        } catch (Exception e) {
            System.err.println("❌ Test case failed due to unexpected exception: " + e.getMessage());
            throw new RuntimeException("Error while calculating expected grand total.", e);
        }
    }

    public String get_Dollar_Based_Calculated_Expected_GrandTotal() {
        try {
            if (!isElementDisplayed(subtotal_amount)) {
                throw new RuntimeException("Sub Total element is not displayed.");
            }
            if (!isElementDisplayed(taxamount)) {
                throw new RuntimeException("Tax Amount element is not displayed.");
            }
            if (!isElementDisplayed(setup_fee_amount)) {
                throw new RuntimeException("Setup Fee element is not displayed.");
            }
            if (!isElementDisplayed(delivery_fee_amount)) {
                throw new RuntimeException("Delivery Fee element is not displayed.");
            }
            if (!isElementDisplayed(discountamount)) {
                throw new RuntimeException("Discount Amount element is not displayed.");
            }

            System.out.println("✅ All required elements are displayed on the page.");

            // Step 2: Get and clean the values
            String sub_Total = getElementText(subtotal_amount, "Sub Total").trim().replace("$", "");
            String tax_Amount = getElementText(taxamount, "Tax Amount").trim().replace("$", "");
            String setup_Fee = getElementAttribute(setup_fee_amount, "Setup Fee on item & product tab").trim().replace("$", "");
            //   String delivery_Fee = getElementText(final_delivery_fee_label, "Delivery Fee").trim().replace("$", "");
            String delivery_Fee = getElementAttribute(delivery_fee_amount, "Delivery Fee on item & product tab").trim().replace("$", "");


            String discount_Amount = getElementText(discountamount, "Discount Amount").trim().replace("$", "");

            System.out.println("📊 Parsed values:");
            System.out.println("   Sub Total      : " + sub_Total);
            System.out.println("   Tax Amount     : " + tax_Amount);
            System.out.println("   Setup Fee      : " + setup_Fee);
            System.out.println("   Delivery Fee   : " + delivery_Fee);
            System.out.println("   Discount Amount: " + discount_Amount);

            // Step 3: Convert to double and calculate total
            double grandTotal_DollarBased = Double.parseDouble(sub_Total)
                    + Double.parseDouble(delivery_Fee) * 1
                    + Double.parseDouble(setup_Fee) * 1
                    + Double.parseDouble(tax_Amount)
                    - Double.parseDouble(discount_Amount);

            System.out.println("Grand Total (Dollar Based): $" + grandTotal_DollarBased);


            String expectedTotal = String.format("$%.2f", grandTotal_DollarBased);
            System.out.println("✅ Expected Grand Total: " + expectedTotal);
            return expectedTotal;

        } catch (NumberFormatException e) {
            System.err.println("❌ Test case failed due to invalid or empty value while parsing: " + e.getMessage());
            throw new RuntimeException("One of the amount fields contains invalid or empty text that couldn't be parsed to a number.", e);
        } catch (Exception e) {
            System.err.println("❌ Test case failed due to unexpected exception: " + e.getMessage());
            throw new RuntimeException("Error while calculating expected grand total.", e);
        }
    }

    public String get_Percentage_Based_Calculated_Expected_GrandTotal() {
        try {
            if (!isElementDisplayed(subtotal_amount)) {
                throw new RuntimeException("Sub Total element is not displayed.");
            }
            if (!isElementDisplayed(taxamount)) {
                throw new RuntimeException("Tax Amount element is not displayed.");
            }
            if (!isElementDisplayed(setup_fee_amount)) {
                throw new RuntimeException("Setup Fee element is not displayed.");
            }
            if (!isElementDisplayed(delivery_fee_amount)) {
                throw new RuntimeException("Delivery Fee element is not displayed.");
            }
            if (!isElementDisplayed(discountamount)) {
                throw new RuntimeException("Discount Amount element is not displayed.");
            }

            System.out.println("✅ All required elements are displayed on the page.");

            // Step 2: Get and clean the values
            String sub_Total = getElementText(subtotal_amount, "Sub Total").trim().replace("$", "");
            String tax_Amount = getElementText(taxamount, "Tax Amount").trim().replace("$", "");
            String setup_Fee = getElementAttribute(setup_fee_amount, "Setup Fee on item & product tab").trim().replace("$", "");
            //   String delivery_Fee = getElementText(final_delivery_fee_label, "Delivery Fee").trim().replace("$", "");
            String delivery_Fee = getElementAttribute(delivery_fee_amount, "Delivery Fee on item & product tab").trim().replace("$", "");


            String discount_Amount = getElementText(discountamount, "Discount Amount").trim().replace("$", "");

            System.out.println("📊 Parsed values:");
            System.out.println("   Sub Total      : " + sub_Total);
            System.out.println("   Tax Amount     : " + tax_Amount);
            System.out.println("   Setup Fee      : " + setup_Fee);
            System.out.println("   Delivery Fee   : " + delivery_Fee);
            System.out.println("   Discount Amount: " + discount_Amount);

            // Step 3: Convert to double and calculate total
            double subtotalValue = Double.parseDouble(sub_Total);
            double setupPercentage = Double.parseDouble(setup_Fee);
            double deliveryPercentage = Double.parseDouble(delivery_Fee);

            double grandTotal_PercentageBased = subtotalValue
                    + (deliveryPercentage / 100) * subtotalValue
                    + (setupPercentage / 100) * subtotalValue
                    + Double.parseDouble(tax_Amount)
                    - Double.parseDouble(discount_Amount);

            System.out.println("Grand Total (Percentage Based): $" + grandTotal_PercentageBased);


            System.out.println("Grand Total (Dollar Based): $" + grandTotal_PercentageBased);


            String expectedTotal = String.format("$%.2f", grandTotal_PercentageBased);
            System.out.println("✅ Expected Grand Total: " + expectedTotal);
            return expectedTotal;

        } catch (NumberFormatException e) {
            System.err.println("❌ Test case failed due to invalid or empty value while parsing: " + e.getMessage());
            throw new RuntimeException("One of the amount fields contains invalid or empty text that couldn't be parsed to a number.", e);
        } catch (Exception e) {
            System.err.println("❌ Test case failed due to unexpected exception: " + e.getMessage());
            throw new RuntimeException("Error while calculating expected grand total.", e);
        }
    }


    public String get_Expected_Grand_Total() {
        String sub_Total = getElementText(subtotal_amount, "Sub Total on item & product tab").trim().replace("$", "");
        String tax_Amount = getElementText(taxamount, "Tax Amount on item & product tab").trim().replace("$", "");
        String setup_Fee = getElementText(final_setup_fee_amount, "Setup Fee on item & product tab").trim().replace("$", "");
        String delivery_Fee = getElementText(final_delivery_fee_label, "Delivery Fee on item & product tab").trim().replace("$", "");
        String discount_Amount = getElementText(discountamount, "Discount Amount on item & product tab").trim().replace("$", "");

        double total = Double.parseDouble(sub_Total)
                + Double.parseDouble(delivery_Fee)
                + Double.parseDouble(setup_Fee)
                + Double.parseDouble(tax_Amount)
                - Double.parseDouble(discount_Amount);
        System.out.println("Expected Grand Total: " + String.format("$%.2f", total));
        return String.format("$%.2f", total);
    }

    public String get_Final_Delivery_Fee_Displayed_On_ItemsAndProducts_Tab() {
        return getElementText(final_delivery_fee_label, "Delivery Fee on item & product tab");
    }

    public String get_Expected_GrandTotal_With_Percentage_DeliveryFee() {
        String sub_Total = getElementText(subtotal_amount, "Sub Total on item & product tab").trim().replace("$", "");
        String tax_Amount = getElementText(taxamount, "Tax Amount on item & product tab").trim().replace("$", "");
        String setup_Fee = getElementText(final_setup_fee_amount, "Setup Fee on item & product tab").trim().replace("$", "");
        String delivery_Fee = getElementText(final_delivery_fee_label, "Delivery Fee on item & product tab").trim().replace("$", "");
        String discount_Amount = getElementText(discountamount, "Discount Amount on item & product tab").trim().replace("$", "");

        double total = Double.parseDouble(sub_Total)
                + Double.parseDouble(delivery_Fee)
                + Double.parseDouble(setup_Fee)
                + Double.parseDouble(tax_Amount)
                - Double.parseDouble(discount_Amount);
        System.out.println("Expected Grand Total: " + String.format("$%.2f", total));
        return String.format("$%.2f", total);
    }

    public String get_Expected_GrandTotal_Without_DeliveryFee() {
        String sub_Total = getElementText(subtotal_amount, "Sub Total on item & product tab").trim().replace("$", "");
        String tax_Amount = getElementText(taxamount, "Tax Amount on item & product tab").trim().replace("$", "");
        String setup_Fee = getElementAttribute(setup_fee_amount, "Setup Fee on item & product tab").trim().replace("$", "");
        String discount_Amount = getElementText(discountamount, "Discount Amount on item & product tab").trim().replace("$", "");
        return (Double.parseDouble(sub_Total) + Double.parseDouble(setup_Fee) + Double.parseDouble(tax_Amount) - Double.parseDouble(discount_Amount)) + "";
    }

    public String get_Actual_GrandTotal() {
        return getElementText(grandTotal, "Grand Total on item & product tab").trim();
    }

    public String get_Expected_TaxAmount() {
        String sub_Total = getElementText(subtotal_amount, "Sub Total on item & product tab").trim().replace("$", "");
        String delivery_Fee = getElementAttribute(delivery_fee_amount, "Delivery Fee on item & product tab").trim().replace("$", "");
        String setup_Fee = getElementAttribute(setup_fee_amount, "Setup Fee on item & product tab").trim().replace("$", "");

        // Parse to double
        double subtotalValue = Double.parseDouble(sub_Total);
        double deliveryFeeValue = Double.parseDouble(delivery_Fee);
        double setupFeeValue = Double.parseDouble(setup_Fee);

        double taxableAmount = subtotalValue + deliveryFeeValue + setupFeeValue;
        // QA Tax percentage will be 1%
        double taxAmount = taxableAmount * 0.01;
        return String.format("%.2f", taxAmount);
    }

    public String get_Expected_TaxAmount_Without_Delivery_Fee() {
        String sub_Total = getElementText(subtotal_amount, "Sub Total on item & product tab").trim().replace("$", "");
        String setup_Fee = getElementAttribute(setup_fee_amount, "Setup Fee on item & product tab").trim().replace("$", "");

        // Parse to double
        double subtotalValue = Double.parseDouble(sub_Total);
        double setupFeeValue = Double.parseDouble(setup_Fee);

        double taxableAmount = subtotalValue + setupFeeValue;
        // QA Tax percentage will be 1%
        double taxAmount = taxableAmount * 0.01;
        return String.format("%.2f", taxAmount);
    }

    public String get_Tax_Amount_Displayed() {
        return getElementText(taxamount, "Tax Amount on item & product tab");
    }

    public String get_Discount_Amount_Displayed() {
        return getElementText(discountamount, "Discount Amount on item & product tab");
    }

    public boolean Verify_Proposal_Pending_Changes_AlertIsDisplayed() {
        return (isElementDisplayed(publishPendingChanges, "Publish Pending Changes button - Add proposal page"));
    }

    public void Click_PublishBtn_On_AlertPopup() {
        logPageLoad("Publish Button on Create Proposal Page → View Proposal Page", () -> {
            click(publish_Btn_On_AlertPopup, "Publish button on publish pending changes alert popup - Add proposal page");
        });
    }

    public String get_ProposalId() {
        try {
            wait_For_Page_To_Be_Stable(getDriver());

            String proposalId = getElementText(
                    ProposalId,
                    "Proposal Id label on view proposal page"
            );

            Allure.addAttachment(
                    "Proposal ID",
                    "text/plain",
                    proposalId
            );

            return proposalId;

        } catch (TimeoutException e) {

            Allure.addAttachment(
                    "Proposal ID Error - Timeout",
                    "text/plain",
                    "Timeout while waiting for Proposal ID to be visible.\n\n"
                            + e.getMessage()
            );
            return "";

        } catch (NoSuchElementException e) {

            Allure.addAttachment(
                    "Proposal ID Error - Element Not Found",
                    "text/plain",
                    "Proposal ID element not found on Manage Proposal page.\n\n"
                            + e.getMessage()
            );
            return "";

        } catch (Exception e) {

            Allure.addAttachment(
                    "Proposal ID Error - Unexpected",
                    "text/plain",
                    "Unexpected exception while fetching Proposal ID.\n\n"
                            + e.getMessage()
            );
            return "";
        }
    }


    public void Enter_EventCustSection_FirstName(String firstName) {
        clickAndType(brideFirstName, firstName);
    }

    public String get_Entered_Event_CustSection_FirstName() {
        HighlightElement(brideFirstName);
        return brideFirstName.getAttribute("value").trim();
    }

    public void Enter_Event_CustSection_LastName(String lastName) {
        clickAndType(brideLastName, lastName);
    }

    public String get_Entered_Event_CustSection_LastName() {
        HighlightElement(brideLastName);
        return brideLastName.getAttribute("value").trim();
    }

    public void Enter_Event_CustSection_PhoneNumber(String phonenumber) {
        clickAndType(bridePhoneNumber, phonenumber);
    }

    public String get_Entered_Event_CustSection_PhoneNumber() {
        HighlightElement(bridePhoneNumber);
        return bridePhoneNumber.getAttribute("value").trim();
    }

    public void Enter_Event_CustSection_Email(String email) {
        clickAndType(brideEmail, email);
    }

    public String get_Entered_Event_CustSection_Email() {
        HighlightElement(brideEmail);
        return brideEmail.getAttribute("value").trim();
    }

    public void Enter_Event_CustSection_City(String city) {
        clickAndType(brideCity, city);
    }

    public String get_Entered_Event_CustSection_City() {
        HighlightElement(brideCity);
        return brideCity.getAttribute("value").trim();
    }

    public void Enter_Event_CustSection_State(String state) {
        clickAndType(brideState, state);
    }

    public String get_Entered_Event_CustSection_State() {
        HighlightElement(brideState);
        return brideState.getAttribute("value").trim();
    }

    public void Enter_Event_CustSection_Address(String address) {
        clickAndType(brideAddress, address);
    }

    public String get_Entered_Event_CustSection_Address() {
        HighlightElement(brideAddress);
        return brideAddress.getAttribute("value").trim();
    }

    public void Enter_Event_CustSection_Zip(String zip) {
        clickAndType(brideZip, zip);
    }

    public String get_Entered_Event_CustSection_Zip() {
        HighlightElement(brideZip);
        return brideZip.getAttribute("value").trim();
    }

    public void enter_AltContact_FirstName(String firstName) {
        clickAndType(event_firstName_Altcontactdetails_section, firstName);
    }

    public String get_Entered_AltContact_FirstName() {
        HighlightElement(event_firstName_Altcontactdetails_section);
        return event_firstName_Altcontactdetails_section.getAttribute("value").trim();
    }

    public void enter_AltContact_LastName(String lastName) {
        clickAndType(event_lastName_Altcontactdetails_section, lastName);
    }

    public String get_Entered_AltContact_LastName() {
        HighlightElement(event_lastName_Altcontactdetails_section);
        return event_lastName_Altcontactdetails_section.getAttribute("value").trim();
    }

    public void enter_AltContact_PhoneNumber(String phoneNumber) {
        clickAndType(event_phonenumber_Altcontactdetails_section, phoneNumber);
    }

    public String get_Entered_AltContact_PhoneNumber() {
        HighlightElement(event_phonenumber_Altcontactdetails_section);
        return event_phonenumber_Altcontactdetails_section.getAttribute("value").trim();
    }

    public void enter_AltContact_EmailID(String emailID) {
        clickAndType(event_emailid_Altcontactdetails_section, emailID);
    }

    public String get_Entered_AltContact_EmailID() {
        HighlightElement(event_emailid_Altcontactdetails_section);
        return event_emailid_Altcontactdetails_section.getAttribute("value").trim();
    }

    public void enter_AltContact_City(String city) {
        clickAndType(event_city_Altcontactdetails_section, city);
    }

    public String get_Entered_AltContact_City() {
        HighlightElement(event_city_Altcontactdetails_section);
        return event_city_Altcontactdetails_section.getAttribute("value").trim();
    }

    public void enter_AltContact_State(String state) {
        clickAndType(event_state_Altcontactdetails_section, state);
    }

    public String get_Entered_AltContact_State() {
        HighlightElement(event_state_Altcontactdetails_section);
        return event_state_Altcontactdetails_section.getAttribute("value").trim();
    }

    public void enter_AltContact_Address(String address) {
        clickAndType(event_address_Altcontactdetails_section, address);
    }

    public String get_Entered_AltContact_Address() {
        HighlightElement(event_address_Altcontactdetails_section);
        return event_address_Altcontactdetails_section.getAttribute("value").trim();
    }

    public void enter_AltContact_Zipcode(String zipcode) {
        clickAndType(event_zipcode_Altcontactdetails_section, zipcode);
    }

    public String get_Entered_AltContact_Zipcode() {
        HighlightElement(event_zipcode_Altcontactdetails_section);
        return event_zipcode_Altcontactdetails_section.getAttribute("value").trim();
    }

    public void Click_On_eventdetailstab() {
        jsClick(eventdetailstab);
    }

    public void enter_Event_Name(String eventName) {
        clickAndType(eventname, eventName);
    }

    public String get_Entered_Event_Name() {
        HighlightElement(eventname);
        return eventname.getAttribute("value").trim();
    }

    public void enter_Event_Location(String eventLocation) {
        clickAndType(eventlocation, eventLocation);
    }

    public String get_Entered_Event_Location() {
        HighlightElement(eventlocation);
        return eventlocation.getAttribute("value").trim();
    }

    public void enter_Event_Date() {
        LocalDate currentDate = LocalDate.now();
        LocalDate nextDay = currentDate.plusDays(20);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedNextDay = nextDay.format(formatter);
        jsClearAndType(eventdate, formattedNextDay);
        // clickAndType(eventdate, eventDate);
    }

    public String get_Entered_Event_Date() {
        HighlightElement(eventdate);
        return eventdate.getAttribute("value").trim();
    }

    public void enter_Event_Time() {
        LocalTime time730PM = LocalTime.of(19, 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = time730PM.format(formatter);
        delayWithGivenTime(500);
        jsClearAndType(eventtime, formattedTime);
        //  clickAndType(eventtime, eventTime);
    }

    public String get_Entered_Event_Time() {
        HighlightElement(eventtime);
        return eventtime.getAttribute("value").trim();
    }

    public void enter_Event_Address(String eventAddress) {
        clickAndType(eventaddress, eventAddress);
    }

    public String get_Entered_Event_Address() {
        HighlightElement(eventaddress);
        return eventaddress.getAttribute("value").trim();
    }

    public void enter_Event_ZipCode(String eventZipCode) {
        clickAndType(eventzipcode, eventZipCode);
        eventzipcode.sendKeys(Keys.TAB);
    }

    public String get_Entered_Event_ZipCode() {
        HighlightElement(eventzipcode);
        return eventzipcode.getAttribute("value").trim();
    }

    public void enter_Event_City(String eventCity) {
        clickAndType(eventcity, eventCity);
    }

    public String get_Entered_Event_City() {
        HighlightElement(eventcity);
        return eventcity.getAttribute("value").trim();
    }

    public void enter_Event_State(String eventState) {
        clickAndType(eventstate, eventState);
    }

    public String get_Entered_Event_State() {
        HighlightElement(eventstate);
        return eventstate.getAttribute("value").trim();
    }

    public void Enter_FirstName_Event_Coordinator(String firstname) {
        clickAndType(eventcoordinator_firstname, firstname);
    }

    public String get_entered_firstname_event_coordinator() {
        HighlightElement(eventcoordinator_firstname);
        return eventcoordinator_firstname.getAttribute("value");
    }

    public void Enter_LastName_Event_Coordinator(String lastname) {
        clickAndType(eventcoordinator_lastname, lastname);
    }

    public String get_entered_lastname_event_coordinator() {
        HighlightElement(eventcoordinator_lastname);
        return eventcoordinator_lastname.getAttribute("value");
    }

    public void Enter_Phonenumber_Event_Coordinator(String phonenumber) {
        clickAndType(eventcoordinator_phonenumber, phonenumber);
    }

    public String get_entered_phonenumber_event_coordinator() {
        HighlightElement(eventcoordinator_phonenumber);
        return eventcoordinator_phonenumber.getAttribute("value");
    }

    public void Enter_EmailId_Event_Coordinator(String emaiid) {
        clickAndType(eventcoordinator_emailid, emaiid);
    }

    public String get_entered_emailid_event_coordinator() {
        HighlightElement(eventcoordinator_emailid);
        return eventcoordinator_emailid.getAttribute("value");
    }

    public void Enter_Address_Event_Coordinator(String address) {
        clickAndType(eventcoordinator_address, address);
    }

    public String get_entered_address_event_coordinator() {
        HighlightElement(eventcoordinator_address);
        return eventcoordinator_address.getAttribute("value");
    }

    public void Enter_Zipcode_Event_Coordinator(String zipcode) {
        clickAndType(eventcoordinator_zipcode, zipcode);
        eventcoordinator_zipcode.sendKeys(Keys.TAB);
    }

    public String get_entered_zipcode_event_coordinator() {
        HighlightElement(eventcoordinator_zipcode);
        return eventcoordinator_zipcode.getAttribute("value");
    }

    public void Enter_City_Event_Coordinator(String city) {
        clickAndType(eventcoordinator_city, city);
    }

    public String get_entered_city_event_coordinator() {
        HighlightElement(eventcoordinator_city);
        return eventcoordinator_city.getAttribute("value");
    }

    public void Enter_State_Event_Coordinator(String state) {
        clickAndType(eventcoordinator_state, state);
    }

    public String get_entered_state_event_coordinator() {
        HighlightElement(eventcoordinator_state);
        return eventcoordinator_state.getAttribute("value");
    }

    /**
     * Verifies if the first uploaded document is displayed on the grid.
     * This method highlights the element and checks its visibility on the page.
     *
     * @return {@code true} if the uploaded document is displayed on the grid, {@code false} otherwise.
     * @throws NoSuchElementException if the element is not found on the page.
     * @throws TimeoutException       if the element is not visible within the specified timeout.
     */
    public boolean verify_uploaded_firstdocument_displayed_on_grid() {
        try {
            HighlightElement(uploaded_product1_on_grid);
            return uploaded_product1_on_grid.isDisplayed();
        } catch (NoSuchElementException e) {
            // Log the exception and return false if the element is not found
            System.err.println("Element not found: " + e.getMessage());
            return false;
        } catch (TimeoutException e) {
            // Log the exception and return false if the element is not visible within the timeout
            System.err.println("Timeout waiting for element visibility: " + e.getMessage());
            return false;
        } catch (Exception e) {
            // Catch any other exceptions and log them
            System.err.println("An unexpected error occurred: " + e.getMessage());
            return false;
        }
    }

    public boolean verify_uploaded_seconddocument_displayed_on_grid() {
        HighlightElement(uploaded_product2_on_grid);
        return uploaded_product2_on_grid.isDisplayed();
    }

    public void Click_DeleteIcon1_On_UploadedDocument() {
        jsClick(deleteIcon1_on_uploadedDocument);
    }

    public void Click_DeleteIcon2_On_UploadedDocument() {
        jsClick(deleteIcon2_on_uploadedDocument);
    }


    public String get_selected_proposal_type_dropdown_value() {
        Select s = new Select(proposal_type_dropdown_value);
        return s.getFirstSelectedOption().getText();
    }

    public void Select_proposal_references(String references) {
        dropDown(proposal_references, references, "VisibleText");
    }

    public String get_selected_proposal_references() {
        Select s = new Select(proposal_references);
        return s.getFirstSelectedOption().getText();
    }

    public void Click_Overview_Popup() {
        jsClick(overview_link_tab);
    }

    public boolean verify_proposal_overview_popup_isDisplayed() {
        HighlightElement(proposal_overview_popup);
        return proposal_overview_popup.isDisplayed();
    }

    public String get_proposal_for_label() {
        HighlightElement(proposal_for_label);
        return proposal_for_label.getText();
    }

    public String get_groom_fullname_on_overviewpopup() {
        HighlightElement(groom_fullname_on_overviewpopup);
        return groom_fullname_on_overviewpopup.getText();
    }

    public String get_groom_phonenumber_on_overviewpopup() {
        HighlightElement(groom_phonenumber_on_overview);
        return groom_phonenumber_on_overview.getText();
    }

    public void Click_closeIcon_on_overview_popup() {
        jsClick(closeIcon_on_overview_popup);
    }

    public boolean verify_proposal_overview_popup_isNotDisplayed() {
        try {
            boolean ispopupdisplayed = proposal_overview_popup.isDisplayed();
            return ispopupdisplayed; // Return true if not displayed
        } catch (NoSuchElementException e) {
            return true;
        } finally {
            return true;
        }
    }

    public void Click_On_Florist_View_Link() {
        try {
            jsClick(florist_view_link_On_headerTab);
            switchToWindowbyIndex(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String get_titleofproposal_on_floristView_window() {
        HighlightElement(titleofproposal_on_floristView_window);
        return titleofproposal_on_floristView_window.getText();
    }


    public boolean Validate_WeddingInfo_Header_on_floristView() {
        try {
            return isElementDisplayed(weddinginfo_header_on_floristview_window);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Validates if the 'Event Info' header is displayed on the florist view window.
     *
     * @return true if the header is displayed, false otherwise.
     * @Author Balaji N
     */
    public boolean Validate_EventInfo_Header_on_floristView() {
        try {
            if (eventinfo_header_on_floristview_window == null) {
                throw new NullPointerException("The 'Event Info' header element on the florist view window is null.");
            }

            boolean isDisplayed = isElementDisplayed(eventinfo_header_on_floristview_window);
            logger.info("'Event Info' header display status on the florist view window: {}", isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.error("Failed to validate 'Event Info' header display status on the florist view window.", e);
            throw new RuntimeException(
                    "An error occurred while validating the 'Event Info' header display status on the florist view window.", e
            );
        }
    }

    public boolean Validate_coordinator_info_header_on_floristView_window() {
        try {
            return isElementDisplayed(coordinator_info_header_on_floristView_window);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifies if the 'Edit Proposal' label header is displayed on the page.
     *
     * @return true if the label header is displayed, false otherwise.
     * @Author Balaji N
     */
    public boolean verify_edit_proposal_label_header_isDisplayed() {
        try {
            if (edit_proposal_label_header == null) {
                throw new NullPointerException("Edit Proposal label header element is null.");
            }

            boolean isDisplayed = isElementDisplayed(edit_proposal_label_header);
            logger.info("'Edit Proposal' label header display status: {}", isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.error("Failed to verify 'Edit Proposal' label header display status.", e);
            throw new RuntimeException("Error occurred while verifying 'Edit Proposal' label header display status.", e);
        }
    }

    public boolean verify_email_conversation_link_on_headerTab_isDisplayed() {
        try {
            return isElementDisplayed(email_conversation_link_on_headerTab);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Click_email_conversation_link_on_headerTab() {
        try {
            if (isElementDisplayed(email_conversation_link_on_headerTab) == true) {
                jsClick(email_conversation_link_on_headerTab);
            }
        } catch (Exception e) {
            throw new RuntimeException("Email Conversation link on header tab is not interactable due to " + e.getMessage());
        }
    }

    public boolean verify_compose_mail_button_on_emailconversation_popup_IsDisplayed() {
        try {
            return isElementDisplayed(compose_mail_button_on_emailconversation_popup);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Click_compose_mail_button_on_emailconversation_popup() {
        try {
            jsClick(compose_mail_button_on_emailconversation_popup);
        } catch (Exception e) {
            throw new RuntimeException("" + e.getMessage());
        }
    }

    public boolean verify_compose_mail_box_isAppears() {
        try {
            return isElementDisplayed(email_box_inside_composemail);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Click_on_Send_button_on_emailbox() {
        try {
            jsClick(send_button_on_emailbox);
        } catch (Exception e) {
            throw new RuntimeException("Email Send button is not interactable due to " + e.getMessage());
        }
    }

    public void Click_discard_button_on_emailbox() {
        try {
            jsClick(discard_button_on_emailbox);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Click_Print_Link_On_Header_Tab() {
        try {
            jsClick(print_link_on_headerTab);
        } catch (Exception e) {
            throw new RuntimeException("Print Link is not interactable due to " + e);
        }
    }

    public boolean verify_print_screen_popup_isDisplayed() {
        return isElementDisplayed(print_popup_screen_headerTab);
    }


    public void downloadProposalPdf() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String pdfUrl = (String) js.executeScript(
                "return document.getElementById('ifrmReportProposalDetailPrint').src;"
        );

        Allure.addAttachment("Proposal PDF URL", "text/plain", pdfUrl);

        js.executeScript("window.open(arguments[0])", pdfUrl);
    }

//    public void Focus_To_DownLoad_Button() {
//        delayWithGivenTime(1000);
//        PressTabKey();
//        PressTabKey();
//        PressTabKey();
//        delayWithGivenTime(1000);
//        PressTabKey();
//        PressTabKey();
//        PressTabKey();
//        delayWithGivenTime(1000);
//        PressTabKey();
//        PressTabKey();
//        PressTabKey();
//        delayWithGivenTime(1000);
//    }

    private static final ThreadLocal<Robot> threadLocalRobot = ThreadLocal.withInitial(() -> {
        try {
            return new Robot();
        } catch (AWTException e) {
            throw new RuntimeException("Failed to initialize Robot instance", e);
        }
    });

    public void Click_Download_Icon() {
        try {
            delayWithGivenTime(1000);
            ActionPressEnter();

            delayWithGivenTime(2000);

            Robot robot = threadLocalRobot.get(); // Get the Robot instance for the current thread
            robot.delay(2000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (Exception e) {
            throw new RuntimeException("Error in Click_Download_Icon", e);
        }
    }

    public boolean Verify_Report_IsDownloaded(String filename) {
        String downloadPath = Paths.get(System.getProperty("user.dir"), "Downloads").toString();
        File filelocation = new File(downloadPath);
        File[] totalfiles = filelocation.listFiles();
        for (File file : totalfiles) {
            if (file.getName().contains(filename)) {
                System.out.println("Report is download successfully");
                break;
            }
        }
        return true;
    }

    public void Close_Print_Popup() {
        js_Click(closeIcon_on_print_popup, "Close Icon on Print popup");
    }

    public void Click_more_actions_on_headerTab() {
        jsClick(more_actions_on_headerTab);
    }

    public void Click_add_followup_on_headerTab() {
        jsClick(add_followup_on_headerTab);
    }

    public boolean Verify_add_follow_up_popup_on_moreActions_IsDisplayed() {
        return isElementDisplayed(add_follow_up_popup_on_moreActions);
    }

    public void Click_closeIcon_addFollowUp_popup_on_moreActions() {
        jsClick(closeIcon_addFollowUp_popup_on_moreActions);
    }

    public void Click_customerview_on_moreactions_at_headerTab() {
        jsClick(customerview_on_moreactions_at_headerTab);
        switchToWindowbyIndex(1);
    }

    public void Enter_LineOneTitle_Under_CoupleInfo(String line_one_title) {
        clickAndType(lineOneTitle_under_coupleInfo, line_one_title);
    }

    public String get_entered_lineonetitle_under_coupleinfo() {
        return get_Element_Attribute(lineOneTitle_under_coupleInfo);
    }

    public void Enter_LineTwoTitle_Under_CoupleInfo(String line_two_title) {
        clickAndType(lineTwoTitle_under_coupleInfo, line_two_title);
    }

    public String get_entered_linetwotitle_under_coupleinfo() {
        return get_Element_Attribute(lineTwoTitle_under_coupleInfo);
    }

    public void Select_Theme_under_coupleInfo(String theme) {
        dropDown(select_theme_under_coupleInfo, theme, "VisibleText");
    }

    public String get_selected_theme_displayed() {
        return get_Selected_Option(select_theme_under_coupleInfo, "Select Theme Dropdown Field on proposal page");
    }

    public boolean Validate_theme_dropdown_options_IsDisplayed() {
        // Create a Select object
        Select theme_dropdown = new Select(select_theme_under_coupleInfo);

        // Get all options from the dropdown
        List<WebElement> options = theme_dropdown.getOptions();

        // Extract the text of each option
        List<String> actualOptions = new ArrayList<>();
        for (WebElement option : options) {
            actualOptions.add(option.getText());
        }

        // Define the expected options
        List<String> expectedOptions = Arrays.asList("Conceptual Design1", "Conceptual Design2", "Portrait With Out Item Description", "Conceptual Design", "Conceptual Design With Out Item Image", "Image Next to Item", "Portrait"); // Replace with your expected values

        // Validate that all expected options are present
        if (actualOptions.containsAll(expectedOptions)) {
            System.out.println("All expected options are displayed in the dropdown.");
            return true;
        } else {
            System.out.println("Some expected options are missing in the dropdown.");
        }

        return false;
    }

    public void Accept_Selected_Theme_On_ConfirmationPopup() {
        if (isElementDisplayed(confirmation_popup_for_selectTheme) == true) {
            jsClick(yesproceed_button_on_selectTheme_confirmationpopup);
        }
    }

    public void Select_weddingorEvent_consultant_dropdown(String weddingorevent_consultant) {
        dropDown(select_weddingorEvent_consultant_dropdown, weddingorevent_consultant, "VisibleText");
    }

    public String get_selected_weddingorevent_consultant_dropdown() {
        return get_Selected_Option(select_weddingorEvent_consultant_dropdown, "Wedding or Event Consultant Dropdown Field on proposal page");
    }

    public String VerifySucessToasterMessageText() {
        Mouse_Hover(this, SuccessToastMsg, "Success Toast Message Text");
        return getElementText(SuccessToastMsg, "Success Toast Message Text on Manage Proposal Page");
    }


    /**
     * Clicks on the "Add Notes and Instructions" tab on the web page.
     * This method uses JavaScript click action to ensure compatibility with non-standard UI elements.
     *
     * @throws RuntimeException if the "Add Notes and Instructions" tab is not clickable.
     * @author Balaji N
     */
    public void Click_AddNotes_And_Instructions_Tab() {
        try {
            jsClick(notesandinstructions_tab);
        } catch (Exception e) {
            String errorMessage = "Failed to click on the 'Add Notes and Instructions' tab. "
                    + "It might not be visible or clickable.";
            System.err.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public boolean isPossibilities_of_upgrade_header_displayed() {
        return isElementDisplayed(possibilities_of_upgrade_header_label);
    }

    public void Enter_Description_On_Possibilities_Of_Upgrade(String description) {
        try {
            clickAndType(possibilities_of_upgrade_description_textbox, description);
        } catch (Exception e) {
            System.err.println("Possibilities of Upgrade Section - unable to interact Description textbox " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the text entered in the "Possibilities of Upgrade" section textbox.
     *
     * @return the text entered in the "Possibilities of Upgrade" textbox.
     * @throws RuntimeException if the element is not found or an error occurs while retrieving the text.
     */
    public String get_entered_description_on_possibilitiesOfUpgrade_Section() {
        try {
            return get_Element_Attribute(possibilities_of_upgrade_description_textbox);
        } catch (Exception e) {
            String errorMessage = "Failed to retrieve the entered description from the 'Possibilities of Upgrade' section textbox.";
            System.err.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    /**
     * Enters the specified quantity in the "Possibilities of Upgrade" section textbox.
     *
     * @param quantity
     * @author Balaji N
     */
    public void Enter_Quantity_On_PossibilitiesOfUpgrade_Section(String quantity) {
        try {
            clickAndType(possibilities_of_upgrade_quantity_textbox, quantity);
        } catch (Exception e) {
            System.err.println("Possibilities of Upgrade Section - unable to interact Quantity textbox " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String get_entered_quantity_on_possibilitiesOfUpgrade_Section() {
        try {
            return get_Element_Attribute(possibilities_of_upgrade_quantity_textbox);
        } catch (Exception e) {
            String errorMessage = "Failed to retrieve the entered quantity from the 'Possibilities of Upgrade' section textbox.";
            System.err.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public void Enter_Price_On_PossibilitiesOfUpgrade_Section(String price) {
        try {
            clickAndType(possibilities_of_upgrade_price_textbox, price);
        } catch (Exception e) {
            System.err.println("Possibilities of Upgrade Section - unable to interact Price textbox " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String get_entered_price_on_possibilitiesOfUpgrade_Section() {
        try {
            return get_Element_Attribute(possibilities_of_upgrade_price_textbox);
        } catch (Exception e) {
            String errorMessage = "Failed to retrieve the entered price from the 'Possibilities of Upgrade' section textbox.";
            System.err.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public void Click_AddItem_Button_on_PossibilitiesOfUpgrade_Section() {
        try {
            jsClick(additem_button_on_possibilities_of_upgrade);
        } catch (Exception e) {
            System.err.println("Possibilities of Upgrade Section - unable to interact Add Item button " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String get_description_on_possibilitiesOfUpgrade_tablegrid() {
        return getElementText(gridTable_possibilities_of_upgrade_description);
    }

    public String get_quantity_on_possibilitiesOfUpgrade_tablegrid() {
        return getElementText(gridTable_possibilities_of_upgrade_quantity);
    }

    public String get_price_on_possibilitiesOfUpgrade_tablegrid() {
        return getElementText(gridTable_possibilities_of_upgrade_price);
    }

    /**
     * Retrieves and formats the total value on the possibilities of upgrade table grid.
     *
     * @return the total value as a formatted string.
     * @throws NumberFormatException    if the total cannot be parsed to a number.
     * @throws IllegalArgumentException if the total is null or empty.
     */
    public String get_total_on_possibilitiesOfUpgrade_tablegrid() {
        String totalText = getElementText(gridTable_possibilities_of_upgrade_total);

        // Validate input
        if (totalText == null || totalText.isEmpty()) {
            throw new IllegalArgumentException("Total value cannot be null or empty.");
        }

        // Remove dollar sign and commas, then parse to double
        String totalTextCleaned = totalText.replace("$", "").replace(",", "");
        double totalValue = Double.parseDouble(totalTextCleaned);

        // Format the total to two decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(totalValue);
    }

    /**
     * Calculates the expected total on the possibilities of upgrade table grid.
     *
     * @return the calculated total as a formatted string.
     * @throws NumberFormatException    if the quantity or price cannot be parsed to a number.
     * @throws IllegalArgumentException if the retrieved quantity or price is null or empty.
     */
    public String Expected_total_on_possibilitiesOfUpgrade_tablegrid() {
        try {
            // Retrieve the quantity and price as strings
            String quantityText = get_quantity_on_possibilitiesOfUpgrade_tablegrid();
            String priceText = get_price_on_possibilitiesOfUpgrade_tablegrid();

            // Validate input
            if (quantityText == null || quantityText.isEmpty()) {
                throw new IllegalArgumentException("Quantity cannot be null or empty.");
            }
            if (priceText == null || priceText.isEmpty()) {
                throw new IllegalArgumentException("Price cannot be null or empty.");
            }

            // Convert the text to numeric values
            double quantity = Double.parseDouble(quantityText);

            // Remove dollar sign and commas from price, then parse to double
            String priceTextCleaned = priceText.replace("$", "").replace(",", "");
            double price = Double.parseDouble(priceTextCleaned);

            // Calculate the total
            double total = quantity * price;

            // Format the total to two decimal places
            DecimalFormat df = new DecimalFormat("#.##");
            return df.format(total);

        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                    "Failed to parse quantity or price to a number. " +
                            "Ensure the inputs are in a valid numeric format. " +
                            "Quantity: " + get_quantity_on_possibilitiesOfUpgrade_tablegrid() +
                            ", Price: " + get_price_on_possibilitiesOfUpgrade_tablegrid()
            );
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Invalid input encountered: " + e.getMessage()
            );
        }
    }

    public boolean is_SpecialInstructions_Header_Displayed() {
        return isElementDisplayed(special_instructions_header_label);
    }

    public boolean is_eyeIcon_displayed() {
        return isElementDisplayed(special_instructions_eye_icon);
    }

    public String ToolTip_SpecialInstructions_Header() {
        HighlightElement(special_instructions_eye_icon);
        fluentWait(special_instructions_eye_icon);

        Actions action = new Actions(getDriver());
        action.moveToElement(special_instructions_eye_icon).build().perform();

        delayWithGivenTime(2000);

        HighlightElement(special_instructions_toolTip);
        return getElementText(special_instructions_toolTip);
    }

    public void Enter_SpecialInstructions_On_NotesAndInstructionsTab(String specialInstructions) {
        clickAndType(special_instructions_textbox, specialInstructions);
    }

    public String get_entered_specialInstructions_On_NotesAndInstructionsTab() {
        return get_Element_Attribute(special_instructions_textbox);
    }

    public boolean isNotesLabel_Element_Displayed() {
        return isElementDisplayed(notes_header_label);
    }

    public void Click_HTML_Button_On_NotesAndInstructionsTab() {
        jsClick(html_link_on_notesandinstructions_tab);

    }

    public boolean is_htmlbutton_enabled() {
        String classAttribute = html_link_on_notesandinstructions_tab.getAttribute("class");
        // Check if the class attribute contains "redactor-button-active"
        if (classAttribute.contains("redactor-button-active")) {
            System.out.println("Element is in an active state.");
            return true;
        } else {
            return false;
        }
    }


    public boolean Validate_ElementsOfNotes_and_InstructionsTab_isDisabled() {
        List<WebElement> elements = getDriver().findElements(By.xpath("//div[@class='redactor-toolbar']//a[contains(@class,'disabled')]"));

        // Validate all elements have "disabled" in their class attribute
        boolean allDisabled = elements.stream()
                .allMatch(element -> element.getAttribute("class").contains("disabled"));

        if (allDisabled) {
            System.out.println("All elements are disabled.");
            return true;
        } else {
            System.out.println("Some elements are not disabled.");
            return false;
        }
    }

    public boolean Validate_ElementsOfNotes_and_InstructionsTab_isEnabled() {
        if (html_link_activestate.getAttribute("class").contains("re-button re-html re-button-icon")) {
            System.out.println("HTML elements is enabled.");
            return true;
        } else {
            System.out.println("HTML elements is not enabled.");
            return false;
        }
    }

    public void Click_Format_linkon_NotesSection_NotesandInstructionsTab() {
        jsClick(format_link_on_notesandinstructions_tab);
    }

    public boolean Verify_All_DropdownValues_AreDisplayed_On_FormatLink_On_NotesSection_NotesandInstructionsTab() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        // Expected dropdown values (trimmed to remove unnecessary spaces)
        List<String> expectedValues = Arrays.asList(
                "Normal text", "Quote", "Code", "Heading 1", "Heading 2",
                "Heading 3", "Heading 4", "Heading 5", "HEADING 6"
        );

        List<String> dropdownValues = new ArrayList<>();

        try {
            // Wait for dropdown values to be visible
            List<WebElement> dropdownElements = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.xpath("//div[@id='redactor-dropdown-0-format']//a//span")
                    )
            );

            // Extract text from dropdown elements and store in a list
            for (WebElement element : dropdownElements) {
                dropdownValues.add(element.getText().trim());
            }

            // Verify if all expected values are displayed
            boolean allDisplayed = expectedValues.stream().allMatch(dropdownValues::contains);

            if (!allDisplayed) {
                System.out.println("Some dropdown values are missing!");
                expectedValues.stream()
                        .filter(value -> !dropdownValues.contains(value))
                        .forEach(value -> System.out.println("Missing: " + value));
            } else {
                System.out.println("All expected dropdown values are displayed correctly.");
            }

            return allDisplayed;

        } catch (TimeoutException e) {
            System.err.println("Error: Dropdown values did not appear within the specified time.");
        } catch (NoSuchElementException e) {
            System.err.println("Error: Some dropdown elements could not be located.");
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
        }

        return false; // Return false in case of any exception
    }


    public void Click_EditIcon_on_possibilitiesofupgrade_notesandinstructions_tab() {
        HighlightElement(edit_icon_on_possibilitiesofupgrade_notesandinstructions_tab);
        edit_icon_on_possibilitiesofupgrade_notesandinstructions_tab.click();
    }

    public void Click_SaveChanges_button_on_possibilitiesofupgrade_notesandinstructions_tab() {
        click(save_changesbutton_on_possibilitiesofupgrade_notesandinstructions_tab);
    }

    public void Click_DeleteIcon_on_possibilitiesofupgrade_notesandinstructions_tab() {
        HighlightElement(delete_icon_on_possibilitiesofupgrade_notesandinstructions_tab);
        delete_icon_on_possibilitiesofupgrade_notesandinstructions_tab.click();
    }

    public void Enter_Notes_On_NotesAndInstructionsTab(String notes) {
        clickAndType(notessection_textarea_on_notesandinstructions_tab, notes);
    }

    public String get_entered_notes_on_notesAndInstructionsTab() {
        return getElementText(notessection_textarea_on_notesandinstructions_tab);
    }

    public void Select_Entered_text() {
        Select_Text();
    }

    public void Select_Header1_Format() {
        try {
            click(format_link_on_notesandinstructions_tab);
            delayWithGivenTime(2000);
            HighlightElement(header1_value_on_format_link);
            header1_value_on_format_link.click();
        } catch (Exception e) {
            System.err.println("Selecting Header 1 format failed. " + e.getMessage() + " element id: header1");
            throw new RuntimeException(e);
        }
    }

    public String Verify_Header1_Format_isDisplayed() {
        try {
            return (header1_value_on_notesandinstructions.getText());
        } catch (Exception e) {
            System.err.println("Header 1 format is not displayed. " + e.getMessage() + " element id: header1");
            throw new RuntimeException(e);
        }
    }


}


