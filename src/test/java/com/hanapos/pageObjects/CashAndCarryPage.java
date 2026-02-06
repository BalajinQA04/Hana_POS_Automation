package com.hanapos.pageObjects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


/**
 * Cash And Carry Page class is a page object class for Cash And Carry page
 * It contains all the web elements of CashAndCarry page and thier interactions functions
 *
 * @Author - Balaji N
 */
public class CashAndCarryPage extends TestBaseClass {
    public CashAndCarryPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public Select select;

    /**
     * @Param - Cash And Carry Page
     * @author Balaji N - Hana Soft AI Solutions India Pvt Ltd
     * @Date - 06/06/2024
     */

    // ==================================Cash and cary page webelements ======================
    @FindBy(css = "h2.set-text-heading")
    private WebElement CashAndCarryPage;

    @FindBy(css = "select#ddlShop")
    private WebElement ShopNameDropDown;

    @FindBy(css = "#ddlClerk")
    private WebElement ClerkDropDown;

    @FindBy(css = "#ddlEmployee")
    private WebElement EmployeeDropDown;

    @FindBy(xpath = "//p[normalize-space()='Item Code']")
    private WebElement ItemCodeLabel;

    @FindBy(css = "#txtItemCode")
    private WebElement ItemCode;

    // Last updated on Jan 2025, previous xpath will be //ul[@id='ui-id-3']
    @FindBy(xpath = "//ul[@id='ui-id-4' or contains(class,'ui-autocomplete')]")
    private WebElement itemcode_autocomplete;

    // Last updated on Jan 2025, previous xpath will be //ul[@id='ui-id-3']//li//div
    @FindBy(xpath = "//ul[@id='ui-id-4' or contains(class,'ui-autocomplete')]//li//div")
    private List<WebElement> itemcodelist;

    @FindBy(xpath = "//p[normalize-space()='Description']")
    private WebElement ItemDescriptionLabel;

    @FindBy(id = "txtDescription")
    private WebElement ItemDescription;

    @FindBy(xpath = "//input[@id='txtDescription']")
    private WebElement ItemDescription1;

    // Last updated on Jan 2025, previous xpath will be //ul[@id='ui-id-4']//li
    @FindBy(xpath = "//ul[@id='ui-id-5']//li//div")
    private List<WebElement> ItemDescList;

    @FindBy(xpath = "//p[normalize-space()='Qty']")
    private WebElement ItemQuantityLabel;

    @FindBy(id = "txtQty")
    private WebElement ItemQuantity;

    @FindBy(xpath = "//p[normalize-space()='Price']")
    private WebElement ItemPriceLabel;

    @FindBy(xpath = "//input[@id='txtPrice']")
    private WebElement ItemPrice;

    @FindBy(xpath = "//p[normalize-space()='Discount']")
    private WebElement ItemDiscountLabel;

    @FindBy(xpath = "//input[@id='txtDiscountAmtItem']")
    private WebElement ItemDiscount;

    @FindBy(xpath = "//p[normalize-space()='Discount %']")
    private WebElement ItemDiscountPercentageLabel;

    @FindBy(xpath = "//input[@id='txtDiscountPerItem']")
    private WebElement ItemDiscountPercentage;

    @FindBy(xpath = "//p[normalize-space()='Action']")
    private WebElement ActionLabel;

    @FindBy(xpath = "//a[@id='btnAdd']")
    private WebElement AddItemBtn;

    @FindBy(xpath = "//a[@id='btnRefresh']")
    private WebElement RefreshFormBtn;

    @FindBy(xpath = "//tr[@id='Row_1']")
    private WebElement AddedItemTableRow1;

    @FindBy(xpath = "//table[@id='tblProducts']")
    private WebElement AddedItemTable;

    @FindBy(xpath = "//a[@id='btnDelete_1']//span")
    private WebElement AddedItemTableRow1DeleteIcon;

    @FindBy(xpath = "//tbody[@id='tblItem']//td[1]")
    private WebElement AddedItemTableRow1ItemCode;

    @FindBy(xpath = "//tbody[@id='tblItem']//td[2]")
    private WebElement AddedItemTableRow1Description;

    @FindBy(xpath = "//tbody[@id='tblItem']//td[3]")
    private WebElement AddedItemTableRow1Quantity;

    @FindBy(xpath = "//input[@id='txtEditQty_1']")
    private WebElement EditedQtyRow1Textbox;

    @FindBy(xpath = "//tbody[@id='tblItem']//td[4]")
    private WebElement AddedItemTableRow1Price;

    @FindBy(xpath = "(//tbody[@id='tblItem']//td[4])[2]")
    private WebElement AddedItemTableRow2Price;

    @FindBy(xpath = "//input[@id='txtEditItemPrice_1']")
    private WebElement EditedPriceRow1Textbox;

    @FindBy(xpath = "//tbody[@id='tblItem']//td[5]")
    private WebElement AddedItemTableRow1ExtPrice;

    @FindBy(xpath = "//tbody[@id='tblItem']//td[6]")
    private WebElement AddedItemTableRow1DiscountAmount;

    @FindBy(xpath = "//tbody[@id='tblItem']//td[7]")
    private WebElement AddedItemTableRow1DiscountPercentage;

    @FindBy(xpath = "(//tbody[@id='tblItem']//td[1])[2]")
    private WebElement AddedItemTableRow2ItemCode;

    @FindBy(xpath = "(//tbody[@id='tblItem']//td[2])[2]")
    private WebElement AddedItemTableRow2Description;

    @FindBy(xpath = "(//tbody[@id='tblItem']//td[3])[2]")
    private WebElement AddedItemTableRow2Quantity;

    @FindBy(xpath = "//tbody[@id='tblItem']//td[8]")
    private WebElement AddedItemTableRow1Action;

    @FindBy(xpath = "//tbody[@id='tblItem']//td[8]/a[1]")
    private WebElement EditAddedItemTableRow1Action;

    @FindBy(xpath = "//span[@class='glyphicon glyphicon-floppy-saved']")
    private WebElement SavedAddedItemTableRow1Action;

    @FindBy(xpath = "//span[@class='glyphicon glyphicon-floppy-remove']")
    private WebElement CancelAddedItemTableRow1Action;

    @FindBy(xpath = "//a[@id='btnDelete_1']//span")
    private WebElement DeleteAddedItemTableRow1;

    @FindBy(xpath = "//a[@id='btnDelete_2']//span")
    private WebElement DeleteAddedItemTableRow2;

    @FindBy(xpath = "//tr[@id='Row_2']")
    private WebElement AddedItemTableRow2;

    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement toasterrormsg;

    @FindBy(xpath = "(//label[contains(text(),'Coupon')])[1]")
    private WebElement CouponLabel;

    @FindBy(xpath = "//input[@id='txtCouponCode']")
    private WebElement CouponCodeTextbox;

    @FindBy(xpath = "//label[contains(text(),'Discount(%) ')]")
    private WebElement DiscountLabel;

    @FindBy(xpath = "//input[@id='txtDiscount']")
    private WebElement DiscountPercentTextbox;

    @FindBy(xpath = "//label[contains(text(),'Discount($)')]")
    private WebElement DiscountDollarLabel;

    @FindBy(xpath = "(//input[@id='txtDiscountAmount'])[1]") //(//input[@id='txtDiscountAmount'])[1]
    private WebElement DiscountDollarTextbox;

    @FindBy(xpath = "//a[@id='changeUpdateDiscountMainCash']") //a[@id='changeUpdateDiscountItemCash']
    private WebElement changediscountlink;

    @FindBy(xpath = "//a[@id='changeUpdateDiscountItemCash']")
    private WebElement changediscountitemlink;

    @FindBy(xpath = "//div[@class='sweet-alert showSweetAlert visible']")
    private WebElement changeDiscountProductLevelPopup;

    @FindBy(xpath = "//button[normalize-space()='Yes']")
    private WebElement changeDiscountProductLevelPopupYesButton;

    @FindBy(xpath = "//h2[normalize-space()='Are you sure you wish to change discount amount?']")
    private WebElement changeDiscountPopupmessage;

    @FindBy(xpath = "//div[@class='sa-icon sa-info']")
    private WebElement changeDiscountPopupicon;

    @FindBy(xpath = "//p[contains(text(),'Updating discount at the order level will remove a')]")
    private WebElement changeDiscountPopupUnboldedText;

    @FindBy(xpath = "//button[normalize-space()='No']")
    private WebElement changeDiscountPopupNoButton;

    @FindBy(xpath = "//button[normalize-space()='Yes']")
    private WebElement changeDiscountPopupYesButton;

    @FindBy(xpath = "(//label[contains(text(),'Select Customer')])[1]")
    private WebElement SelectCustomerLabel;

    @FindBy(xpath = "//div[@class='form-group selectedCustomerWrap']//button[text()='Add']")
    private WebElement AddCustomerButton;

    @FindBy(xpath = "(//a[@class='QuickInsertItem'])[1]")
    private WebElement flowerstd_leftside_displayedTile_Prod;

    @FindBy(xpath = "(//a[@class='QuickInsertItem'])")
    private List<WebElement> flowerstd_leftside_displayedTile;

    // Add New Customer Pop up - web elements

    @FindBy(xpath = "//h4[@id='AddNewCustomerModalTitle']")
    private WebElement AddNewCustomerPopup;

    @FindBy(xpath = "//select[@id='ddlCustomershop']")
    private WebElement AddNewCustShopDropdown;

    @FindBy(xpath = "//input[@id='txtAddCompanyName']")
    private WebElement AddNewCustCompanyNameTextbox;

    @FindBy(xpath = "(//input[@id='txtAddAddress1'])[1]")
    private WebElement AddNewCustAddress1Textbox;

    @FindBy(xpath = "(//input[@id='txtAddAddress2'])[1]")
    private WebElement AddNewCustAddress2Textbox;

    @FindBy(xpath = "//input[@id='txtAddZip']")
    private WebElement AddNewCustZipCodeTextbox;

    @FindBy(xpath = "//input[@id='txtAddCity']")
    private WebElement AddNewCustCityTextbox;

    @FindBy(xpath = "//input[@id='txtAddState']")
    private WebElement AddNewCustStateTextbox;

    @FindBy(xpath = "//input[@id='txtAddCountry']")
    private WebElement AddNewCustCountryTextbox;

    @FindBy(xpath = "//input[@id='txtAddEmail']")
    private WebElement AddNewCustEmailTextbox;

    @FindBy(xpath = "//select[@id='ddlAddCustomerType']")
    private WebElement AddNewCustTypeDropdown;

    @FindBy(xpath = "//input[@id='txtStoretxtAddStoreCreditCredit']")
    private WebElement AddNewCustCreditTextbox;

    @FindBy(xpath = "//input[@id='txtAddFirstName']")
    private WebElement AddNewCustFirstNameTextbox;

    @FindBy(xpath = "//input[@id='txtAddLastName']")
    private WebElement AddNewCustLastNameTextbox;

    @FindBy(xpath = "//input[@id='txtAddPhoneNumber']")
    private WebElement AddNewCustPhoneNumberTextbox;

    @FindBy(xpath = "//input[@id='txtAddAltPhone']")
    private WebElement AddNewCustAltPhoneNumberTextbox;

    @FindBy(xpath = "//h4[contains(text(),'Add New Customer')]/preceding-sibling::a//i")
    private WebElement AddNewCustPopupCloseIcon;

    @FindBy(xpath = "//form[@id='frmAddCustomer']//label[text()='Credit Approved ']/following::span[1]")
    private WebElement AddNewCustPopupcreditapprovedToogle;

    @FindBy(xpath = "//form[@id='frmAddCustomer']//label[text()='Paperless Statements ']/following::span[1]")
    private WebElement AddNewCustPopupPaperlessStatementsToogle;

    @FindBy(xpath = "//form[@id='frmAddCustomer']//label[text()='Email Statements ']/following::span[1]")
    private WebElement AddNewCustPopupEmailStatementToogle;

    @FindBy(xpath = "//form[@id='frmAddCustomer']//label[text()='Enable Loyalty ']/following::span[1]")
    private WebElement AddNewCustPopupEnableLoyaltyToogle;

    @FindBy(xpath = "//form[@id='frmAddCustomer']//label[text()='Late Fee Setting ']/following::span[1]")
    private WebElement AddNewCustPopupLateFeeSettingToogle;

    @FindBy(xpath = "//form[@id='frmAddCustomer']//label[text()='Allow SMS ']/following::span[1]")
    private WebElement AddNewCustPopupAllowSMSToogle;

    @FindBy(xpath = "//form[@id='frmAddCustomer']//label[text()='Allow Feedback Email ']/following::span[1]")
    private WebElement AddNewCustPopupAllowFeedBackEmailToogle;

    @FindBy(xpath = "//a[@id='btnAddNewCustomer']")
    private WebElement AddNewCustAddNewCustomerButton;

    //****** ended - Add New Customer Pop up - web elements *****

    @FindBy(xpath = "(//label[contains(text(),'Tax Type')])[1]")
    private WebElement TaxTypeLabel;

    @FindBy(xpath = "(//label[contains(text(),'Occasion')])[1]")
    private WebElement OccasionLabel;

    @FindBy(xpath = "(//label[contains(text(),'Source Code')])[1]")
    private WebElement SourceCodeLabel;

    @FindBy(xpath = "//label[contains(text(),'Discount(%)')]")
    private WebElement DiscountPercentageLabel;

    @FindBy(xpath = "//input[@id='txtDiscount']")
    private WebElement Discountpertextbox;

    @FindBy(xpath = "(//input[@id='txtDiscountAmount'])[1]")
    private WebElement DiscountAmtTextbox;

    @FindBy(xpath = "//input[@id='txtSearchProduct']")
    private WebElement SelectCustomer;

   /* @FindBy(xpath = "//ul[@id='ui-id-2']//li[@class='ui-menu-item']//div")
    private WebElement list_of_customer_autocomplete_dropdown;*/

    // Last updated on Jan 2025, previous xpath will be //ul[@id='ui-id-1']//li[@class='ui-menu-item']//div
    @FindBy(xpath = "//ul[@id='ui-id-2']//li[@class='ui-menu-item']//div")
    private List<WebElement> CustomerList_autocomplete_dropdown_Option;

    @FindBy(xpath = "(//button[@class='btn btn-primary btnAddCustomer'][normalize-space()='Add'])[1]")
    private WebElement AddCustomerBtn;

    @FindBy(xpath = "//p[@id='lblCustomerIdMain']")
    private WebElement CustomerId;

    @FindBy(xpath = "//label[text()='Customer Name']/following-sibling::p[@id='lblCustomerNameMain']")
    private WebElement CustomerName;

    @FindBy(xpath = "//div[@class='selectedCustomerDetailWrap']//button[normalize-space()='X']")
    private WebElement cancelcustIcon;

    @FindBy(xpath = "//select[@id='ddlTaxType']")
    private WebElement TaxType;

    @FindBy(xpath = "//select[@id='ddlOccasion']")
    private WebElement Occasion;

    @FindBy(xpath = "//select[@id='productReferral']")
    private WebElement SourceCode;

    @FindBy(xpath = "//div[normalize-space()='Sub Total']")
    private WebElement SubTotalLabel;

    @FindBy(xpath = "(//div[@class='col-md-6 col-sm-5 col-xs-6 BoldText text-right'])[1]//span")
    private WebElement DefaultSubTotalValues;

    @FindBy(xpath = "(//span[@id='lblSubTotal'])[1]")
    private WebElement SubTotal;

    @FindBy(xpath = "//div[normalize-space()='GST']")
    private WebElement GSTLabel;

    @FindBy(xpath = "(//div[@class='col-md-6 col-sm-5 col-xs-6 BoldText text-right hidetax'])[1]//span")
    private WebElement GSTDefaultValue;

    @FindBy(id = "lblGSTTax")
    private WebElement GSTTax;

    @FindBy(xpath = "//div[contains(text(),'PST/HST/QST')]")
    private WebElement PSTHSTQSTLabel;

    @FindBy(xpath = "(//div[@class='col-md-6 col-sm-5 col-xs-6 BoldText text-right hidetax'])[2]//span")
    private WebElement PSTHSTQSTDefaultValues;

    @FindBy(id = "lblPSTTax")
    private WebElement PSTTax;

    @FindBy(xpath = "//div[contains(text(),'Tax')]")
    private WebElement TaxLabel;

    @FindBy(xpath = "(//div[@class='col-md-6 col-sm-5 col-xs-6 BoldText text-right'])[2]//span")
    private WebElement TaxDefaultValues;

    @FindBy(xpath = "//span[@id='lblTax']")
    private WebElement Tax;

    @FindBy(xpath = "(//div[@class='col-md-6 col-sm-7 col-xs-6 BoldText text-right'][normalize-space()='Discount'])[1]")
    private WebElement Discountlabel;

    @FindBy(xpath = "(//div[@class='col-md-6 col-sm-5 col-xs-6 BoldText text-right'])[3]//span")
    private WebElement DiscountDefaultValues;

    @FindBy(xpath = "//span[@id='lblDiscount']")
    private WebElement Discount;

    @FindBy(xpath = "//div[contains(text(),'Grand Total')]")
    private WebElement GrandTotalLabel;

    @FindBy(xpath = "//span[@id='GrandTotal']")
    private WebElement GrandTotalDefaultValue;

    @FindBy(xpath = "//span[@id='GrandTotal']")
    private WebElement GrandTotal;

    @FindBy(id = "lblTotal")
    private WebElement Total;

    @FindBy(xpath = "//button[@id='lnkPay']")
    private WebElement PayBtn;

    @FindBy(xpath = "//span[@id='lblPay']")
    private WebElement PayBtnText;

    @FindBy(xpath = "//div[@class='toast-title']")
    private WebElement ErrorToastMsg;

    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement ErrorToastMsgCloseBtn;

    @FindBy(xpath = "//a[@class='QuickInsertItem']//div")
    private List<WebElement> CashAndCarryTile;

    @FindBy(xpath = "//div[@class='MyBoxes'][normalize-space()='Balloons_YY-Balloons small-$40.00']")
    private WebElement cashandcarry_balloon_title_product;

    @FindBy(xpath = "//div[@class='MyBoxes'][normalize-space()='BalloonsYY-Balloons Small-$40.00']")
    private WebElement CashAndCarryBalloonTitle;

    @FindBy(xpath = "//div[@class='MyBoxes'][normalize-space()='RSS-Rose Flowers Rose Flowers-$80.00']")
    private WebElement CashAndCarry_RSS_ProductTitle;

    @FindBy(xpath = "//div[@class='MyBoxes']")
    private List<WebElement> ListOfCashAndCarryTileProducts;

    @FindBy(xpath = "//a[@class='QuickInsertItem']")
    private List<WebElement> ListOfProdTiles;

    @FindBy(xpath = "//button[@id='btnReconcile']")
    private WebElement ReconcileBtn;

    @FindBy(xpath = "(//h3[normalize-space()='Reconcile Cash Draw Balance'])[1]")
    private WebElement ReconcilePopupHeader;

    @FindBy(xpath = "(//select[@id='ddlClerkReconcile'])[1]")
    private WebElement ClerkReconcileDropDown;

    @FindBy(xpath = "//select[@id='ddlClerkReconcile']//option")
    private List<WebElement> listOfClerkReconcileDropDown;

    @FindBy(xpath = "(//select[@id='ddlShopReconcile'])[1]")
    private WebElement ShopReconcileDropDown;

    @FindBy(xpath = "//span[@id='lblLastBalanceDate']")
    private WebElement lastReconcillationDate;

    @FindBy(xpath = "//span[@id='lblOpenBalance']")
    private WebElement openBalance;

    @FindBy(xpath = "//span[@id='lblExpectedBalance']")
    private WebElement expectedBalance;

    @FindBy(xpath = "//span[@id='lblActualBalance']")
    private WebElement actualBalance;

    @FindBy(xpath = "//span[@id='lblDifference']")
    private WebElement difference;

    @FindBy(xpath = "//div[@id='divDiff']")
    private WebElement differencefieldcolor;

    @FindBy(xpath = "//span[@id='lbldatetime']")
    private WebElement CashRegisterSalesReview_dateTime;

    @FindBy(xpath = "//span[@id='lblCash']")
    private WebElement CashRegisterSalesReview_Cash;

    @FindBy(xpath = "//span[@id='lblCashTax']")
    private WebElement CashRegisterSalesReview_AfterTax;

    @FindBy(xpath = "(//span[@id='lblTotalSales'])[1]")
    private WebElement CashRegisterSalesReview_TotalSales;

    @FindBy(xpath = "(//span[@id='lblStartCash'])[1]")
    private WebElement CashRegisterSalesReview_StartCash;

    @FindBy(xpath = "(//span[@id='lblPaymentAmt'])[1]")
    private WebElement CashRegisterSalesReview_PayoutAmt;

    @FindBy(xpath = "(//span[@id='lblCashInDrawer'])[1]")
    private WebElement CashRegisterSalesReview_CashInDrawer;

    @FindBy(id = "txt1Dollars")
    private WebElement oneDollars_denomination;

    @FindBy(id = "txt2Dollars")
    private WebElement twoDollars_denomination;

    @FindBy(id = "txt5Dollars")
    private WebElement fiveDollars_denomination;

    @FindBy(id = "txt20Dollars")
    private WebElement twentyDollars_denomination;

    @FindBy(id = "txt50Dollars")
    private WebElement fiftyDollars_denomination;

    @FindBy(id = "txt100Dollars")
    private WebElement oneHundredDollars_denomination;

    @FindBy(id = "txtPennies")
    private WebElement pennies_denomination;

    @FindBy(id = "txtNickles")
    private WebElement nickles_denomination;

    @FindBy(id = "txtDimes")
    private WebElement dimes_denomination;

    @FindBy(id = "txtQuarters")
    private WebElement quarters_denomination;

    @FindBy(xpath = "(//button[normalize-space()='Reconcile'])[1]")
    private WebElement ReconcileBtn_ReconcilePopup;

    @FindBy(xpath = "//div[@id='ReconcileCCPasswordPopup']//div[@class='modal-content']")
    private WebElement reconcile_password_popup;

    @FindBy(xpath = "//div[@id='ReconcileCCPasswordPopup']//div[@class='modal-content']//input[1]")
    private WebElement reconcile_password_textbox;

    @FindBy(xpath = "//div[@id='ReconcileCCPasswordPopup']//div[@class='modal-content']//button[text()='Verify']")
    private WebElement reconcile_verify_password_btn;

    @FindBy(xpath = "(//span[@id='lblPaymentAmt'])[1]")
    private WebElement payOutAmt_ReconcilePopup;

    @FindBy(xpath = "//button[@id='btnPayOut']")
    private WebElement PayOutBtn;

    @FindBy(xpath = "//button[@id='btnSalesHistory']")
    private WebElement SalesHistoryBtn;

    @FindBy(xpath = "//button[@id='btnOpenCashDrawer']")
    private WebElement OpenCashDrawerBtn;

    @FindBy(xpath = "//div[@id='CCPasswordPopup']//div[@class='modal-content']")
    private WebElement verifyPasswordPopup;

    @FindBy(xpath = "//div[@id='CCPasswordPopup']//div[@class='modal-content']//h3[normalize-space()='Verify Password']")
    private WebElement VerifyPasswordPopupHeader;

    @FindBy(xpath = "//div[@class='modal-content']//input[@id='txtCCPassword']")
    private WebElement VerifyPasswordTextbox;

    @FindBy(xpath = "//div[@id='CCPasswordPopup']//button[normalize-space()='Verify']")
    private WebElement verifyBtn_PasswordPopup;

    @FindBy(xpath = "(//h3[normalize-space()='Cash Draw Payout'])[1]")
    private WebElement CashDrawPopupHeader;

    @FindBy(xpath = "(//select[@id='ddlShopPayout'])[1]")
    private WebElement ShopPayoutDropDown;

    @FindBy(xpath = "(//select[@id='ddlClerkPayout'])[1]")
    private WebElement ClerkPayoutDropDown;

    @FindBy(xpath = "//select[@id='ddlClerkPayout']//option")
    private List<WebElement> ClerkPayoutDropDownList;

    @FindBy(xpath = "(//input[@id='txtNamePayout'])[1]")
    private WebElement NamePayoutTextBox;

    @FindBy(xpath = "//select[@id='ddlEmployeeList']")
    private WebElement EmployeeNamedrpElement;

    @FindBy(xpath = "(//textarea[@id='txtReasonPayout'])[1]")
    private WebElement ReasonPayoutTextBox;

    @FindBy(xpath = "(//input[@id='txtSubTotalPayout'])[1]")
    private WebElement SubTotalPayoutTextBox;

    @FindBy(xpath = "(//input[@id='txtTaxAmountPayout'])[1]")
    private WebElement TaxAmountPayoutTextBox;

    @FindBy(xpath = "(//input[@id='txtAmountPayout'])[1]")
    private WebElement GrandTotalPayoutTextBox;

    @FindBy(xpath = "//div[@id='PayoutModal']//a/i")
    private WebElement CloseiconCashDrawPopup;

    @FindBy(xpath = "(//button[@id='btnSavePayout'])[1]")
    private WebElement SavePayoutBtn;


    @FindBy(xpath = "//span[contains(text(),'Press')]")
    private WebElement PressAltPtocontinuetopaymentpageText;

    @FindBy(xpath = "//input[@id='chkSplitPayment']/following::span[1]")
    private WebElement SplitPaymentToogleButton;

    @FindBy(xpath = "//a[@title='Choose Default Values']//span")
    private WebElement chooseDefaultValuesIcon;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//div[@class='modal-content']")
    private WebElement choose_page_default_values_popup;

    @FindBy(xpath = "(//h3[normalize-space()='Choose Page Default Values'])[1]")
    private WebElement choosepagedefaultvaluespopupHeader;

    @FindBy(xpath = "//select[@id='ddlSalesPerson']")
    private WebElement salespersondd;

    @FindBy(xpath = "//select[@id='ddlDefaultOccasion']")
    private WebElement defaultoccasiondd;

    @FindBy(xpath = "//select[@id='ddlSourceCode']")
    private WebElement sourcecodedd;

    @FindBy(xpath = "//button[@id='btnUpdate']")
    private WebElement updateBtn;

    /**
     * @Param - Gift Card Section
     * @Author - Balaji
     * @Date - 06/06/2024
     */

    @FindBy(xpath = "//a[@class='btnShowGiftPopUp']//div[@class='MyBoxes']")
    private WebElement GiftCardTile;

    @FindBy(xpath = "//div[@id='giftCardTypeModal']/div/div")
    private WebElement selectgiftcardtype;

    @FindBy(xpath = "//h3[@id='GiftCardTypeTitle']")
    private WebElement select_gift_card_type_header;
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

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button")
    private List<WebElement> GiftCardPriceListButton;

    @FindBy(xpath = "(//div[@id='divGiftCardPrice']//button)[11]")
    private WebElement GiftCardPriceListEditButton;

    @FindBy(xpath = "//input[@id='txtGiftCardAmount']")
    private WebElement GiftCardAmountTextBox;

    @FindBy(xpath = "//input[@id='txtGiftCardProcessingFee']")
    private WebElement ProcessingFeeTextBox;

    @FindBy(xpath = "//input[@id='txtGiftCardCardNumber']")
    private WebElement GiftCardNumberTextBox;

    @FindBy(xpath = "//input[@id='txtCustomerNameForGiftCard']")
    private WebElement CustomerNameTextBox;

    @FindBy(xpath = "//ul[@id='ui-id-1']/li")
    private List<WebElement> customersearchlist;

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

    @FindBy(xpath = "(//button[contains(text(),'Submit')])[1]")
    private WebElement GiftCardSubmitButton;

    @FindBy(xpath = "(//div[@class='modal-header']//a//i)[7]")
    private WebElement CloseGiftCardPopup;

    @FindBy(xpath = "//button[@id='btnUpdateGiftPriceValues']")
    private WebElement EditButtonOnGiftCardPopup;

    @FindBy(xpath = "//h3[@id='GiftCardCreateModalTitle']")
    private WebElement EditGiftCardDominationPopup;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[1]")
    private WebElement giftcarddenominationprice1;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[2]")
    private WebElement giftcarddenominationprice2;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[3]")
    private WebElement giftcarddenominationprice3;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[4]")
    private WebElement giftcarddenominationprice4;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[5]")
    private WebElement giftcarddenominationprice5;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[6]")
    private WebElement giftcarddenominationprice6;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[7]")
    private WebElement giftcarddenominationprice7;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[8]")
    private WebElement giftcarddenominationprice8;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[9]")
    private WebElement giftcarddenominationprice9;

    @FindBy(xpath = "//div[@id='divGiftCardPrice']//button[10]")
    private WebElement giftcarddenominationprice10;

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


    /**
     * @Param - Edit Gift Card Denominations
     * @Author - Balaji
     * @Date - 06/06/2024
     */

    @FindBy(xpath = "//input[@id='txtD1']") // txtD2, txtD3 similarly for other denominations textbox
    private WebElement EditGiftCardDenomination1TextBox;

    @FindBy(xpath = "//input[@id='txtD2']")
    private WebElement EditGiftCardDenomination2TextBox;

    @FindBy(xpath = "//input[@id='txtD3']")
    private WebElement EditGiftCardDenomination3TextBox;

    @FindBy(xpath = "//input[@id='txtD4']")
    private WebElement EditGiftCardDenomination4TextBox;

    @FindBy(xpath = "//input[@id='txtD5']")
    private WebElement EditGiftCardDenomination5TextBox;

    @FindBy(xpath = "//input[@id='txtD6']")
    private WebElement EditGiftCardDenomination6TextBox;

    @FindBy(xpath = "//input[@id='txtD7']")
    private WebElement EditGiftCardDenomination7TextBox;

    @FindBy(xpath = "//input[@id='txtD8']")
    private WebElement EditGiftCardDenomination8TextBox;

    @FindBy(xpath = "//input[@id='txtD9']")
    private WebElement EditGiftCardDenomination9TextBox;

    @FindBy(xpath = "//input[@id='txtD10']")
    private WebElement EditGiftCardDenomination10TextBox;

    @FindBy(xpath = "//button[@id='btnSaveGiftCardPrice']")
    private WebElement EditGiftCardDenominationSaveButton;

    @FindBy(xpath = "(//button[contains(text(),'Cancel')])[2]")
    private WebElement EditGiftCardDenominationCancelButton;

    @FindBy(xpath = "(//i[@data-dismiss='modal'])[8]")
    private WebElement CloseEditGiftCardDominationPopup;

    /**
     * @Param - Cash And Carry Page walkins setting & Their pop up sections
     * @Author - Balaji
     * @Date - 06/06/2024
     */

    @FindBy(xpath = "//a[@title='WalkIns Settings']")
    private WebElement CashAndCarryPageWalkinsSettingIcon;

    @FindBy(xpath = "//h3[normalize-space()='Walkin Settings']")
    private WebElement WalkinsSettingPopup;

    @FindBy(xpath = "(//i[@class='fa fa-2x fa-times-circle modal-action-container-close-icon hana-drawer-container-close close no-padding hana-initialized'])[1]")
    private WebElement WalkinsSettingPopupCloseIcon;

    @FindBy(xpath = "//select[@id='ddlShopForWalkinsSettings']")
    private WebElement WalkinsSettingPopupShopDropDown;

    @FindBy(xpath = "//button[@id='btnAddNewWalkin']")
    private WebElement WalkinsSettingPopupAddNewBtn;

    @FindBy(xpath = "//h3[normalize-space()='Walkin Settings']")
    private WebElement WalkinsSettingAddNewPopupScreen;

    @FindBy(xpath = "//label[normalize-space()='Clerk Id']")
    private WebElement ClerkIdLabel;

    @FindBy(id = "txtClerkID")
    private WebElement ClerkIDTextBox;

    @FindBy(xpath = "//label[normalize-space()='Clerk Description']")
    private WebElement ClerkDescLabel;

    @FindBy(id = "txtClerkDesc")
    private WebElement ClerkDescTextBox;

    @FindBy(xpath = "//label[normalize-space()='Printer Name']")
    private WebElement PrinterNameLabel;

    @FindBy(id = "txtPrinterName")
    private WebElement PrinterNameTextBox;

    @FindBy(xpath = "//label[normalize-space()='Manufacturer']")
    private WebElement ManufacturerLabel;

    @FindBy(id = "ddlManufacturer")
    private WebElement ManufacturerDropDown; //  Epson

    @FindBy(xpath = "//label[normalize-space()='Model']")
    private WebElement ModelLabel;

    @FindBy(id = "ddlModel")
    private WebElement ModelDropDown; //  LX-300+

    @FindBy(xpath = "//label[normalize-space()='Cash Draw Code']")
    private WebElement CashDrawCodeLabel;

    @FindBy(id = "txtCashDrawCode")
    private WebElement CashDrawCodeTextBox;

    @FindBy(xpath = "//label[normalize-space()='Printer Cut Code']")
    private WebElement PrinterCutCodeLabel;

    @FindBy(id = "txtPrinterCutCode")
    private WebElement PrinterCutCodeTextBox;

    @FindBy(xpath = "//label[normalize-space()='Clearent API Key']")
    private WebElement ClearantAPIKeyLabel;

    @FindBy(id = "txtAPIKey")
    private WebElement APIKeyTextBox;

    @FindBy(xpath = "//label[normalize-space()='Clover Device Id']")
    private WebElement CloverDeviceIDLabel;

    @FindBy(xpath = "//input[@id='txtCloverDeviceId']")
    private WebElement CloverDeviceIDTextBox;

    @FindBy(xpath = "//label[normalize-space()='Clover Cash Drawer Id']")
    private WebElement CloverCashDrawerIdLabel;

    @FindBy(xpath = "//div[@class='input-group-addon']")
    private WebElement CloverCashDrawerIdSearchIcon;

    @FindBy(xpath = "//label[normalize-space()='Enable Clover Printer']")
    private WebElement EnableCloverPrinterLabel;

    @FindBy(xpath = "//input[@id='chkEnableCloverPrinter']")
    private WebElement EnableCloverPrinterCheckBox;

    @FindBy(xpath = "//label[normalize-space()='OpenEdge Web ID']")
    private WebElement OpenEdgeWebIDLabel;

    @FindBy(xpath = "//input[@id='txtWebID']")
    private WebElement OpenEdgeWebIDTextBox;

    @FindBy(xpath = "//label[normalize-space()='OpenEdge Terminal ID']")
    private WebElement OpenEdgeTerminalIDLabel;

    @FindBy(xpath = "//input[@id='txtTerminalID']")
    private WebElement OpenEdgeTerminalIDTextBox;

    @FindBy(xpath = "//label[normalize-space()='OpenEdge AuthKey']")
    private WebElement OpenEdgeAuthKeyLabel;

    @FindBy(xpath = "//input[@id='txtAuthKey']")
    private WebElement OpenEdgeAuthKeyTextBox;

    @FindBy(xpath = "//label[normalize-space()='Customer Copy']")
    private WebElement OpenEdgeCustomerCopyLabel;

    @FindBy(xpath = "//input[@id='chkCustomerCopy']")
    private WebElement OpenEdgeCustomerCopyCheckBox;

    @FindBy(xpath = "//label[normalize-space()='Merchant Copy']")
    private WebElement OpenEdgeMerchantCopyLabel;

    @FindBy(xpath = "//input[@id='chkMerchantCopy']")
    private WebElement OpenEdgeMerchantCopyCheckBox;

    @FindBy(xpath = "//label[normalize-space()='Enable OpenEdge Printer']")
    private WebElement EnableOpenEdgePrinterLabel;

    @FindBy(xpath = "//label[normalize-space()='Customer Copy Note']")
    private WebElement CustomerCopyNoteLabel;

    @FindBy(xpath = "//textarea[@id='txtwalkingdescription']")
    private WebElement CustomerCopyNoteTextBox;

    @FindBy(xpath = "//label[normalize-space()='Merchant Copy Note']")
    private WebElement MerchantCopyNoteLabel;

    @FindBy(xpath = "//textarea[@id='txtwalkingMerchantdescription']")
    private WebElement MerchantCopyNoteTextBox;

    @FindBy(xpath = "//button[@id='btnSaveWalkIn']")
    private WebElement SaveWalkInSettingButton;

    @FindBy(xpath = "//div[@class='toast-message']") //WalkIns Settings saved successfully
    private WebElement successToastMessageOnAddNewWalkInSetting;

    @FindBy(xpath = "(//td[contains(text(),'1')])[5]")
    private WebElement NewlyCreatedClerkIdWalkInSetting;

    @FindBy(xpath = "(//td[contains(text(),'Test Automation Clerk description')]/following-sibling::td//a[@id='btnEditWalkInSetting'])[1]")
    private WebElement Automation_Clerk_description_NewlyCreatedEditWalkInSettingButton;

    @FindBy(xpath = "(//div[@id='divWalkInSettingsTableData']//tbody//td)[1]/following-sibling::td[7]//a[2]")
    private WebElement NewlyCreatedDeleteWalkInSettingButton;

    @FindBy(xpath = "//button[@id='btnBackToIntialWalkin']")
    private WebElement CancelWalkInSettingButton;

    @FindBy(xpath = "//table[@class='table table-responsive table-striped']//tbody[1]//tr//td[3]")
    private List<WebElement> listOfPrinterName;

    @FindBy(xpath = "(//i[@data-dismiss='modal'])[5]")
    private WebElement WalkingSettingPopupCloseIcon;

    @FindBy(xpath = "//table[@id='tblProducts']//tbody//tr")
    private List<WebElement> listoftablerows;

    @FindBy(xpath = "//label[@id='lblItemCode_1']")
    private WebElement AddedItemCodecol1;

    @FindBy(xpath = "//label[@id='lblItemCode_2']")
    private WebElement AddedItemCodeRow2;

    @FindBy(xpath = "//label[@id='lblItemDesc_1']")
    private WebElement AddedItemDescriptioncol1;

    @FindBy(xpath = "//label[@id='lblItemDesc_2']")
    private WebElement AddedItemDescriptioncol2;

    @FindBy(xpath = "//label[@id='lblQty_1']")
    private WebElement AddedItemQuantitycol1;

    @FindBy(xpath = "//label[@id='lblQty_2']")
    private WebElement AddedItemQuantitycol2;

    @FindBy(xpath = "//label[@id='lblItemPrice_1']")
    private WebElement AddedItemPricecol1;

    @FindBy(xpath = "//label[@id='lblItemPrice_2']")
    private WebElement AddedItemPricecol2;

    @FindBy(xpath = "//span[@id='lblExtPrice_1']")
    private WebElement AddedItemExtendedPricecol1;

    @FindBy(xpath = "//span[@id='lblExtPrice_2']")
    private WebElement AddedItemExtendedPricecol2;

    @FindBy(xpath = "//label[@id='lblDicountAmt_1']")
    private WebElement AddedItemDiscountAmountcol1;

    @FindBy(xpath = "//label[@id='lblDicountAmt_2']")
    private WebElement AddedItemDiscountAmountcol2;

    @FindBy(xpath = "//label[@id='lblDicountPer_1']")
    private WebElement AddedItemDiscountPercentagecol1;

    @FindBy(xpath = "//label[@id='lblDicountPer_2']")
    private WebElement AddedItemDiscountPercentagecol2;

    @FindBy(xpath = "//div[@id='AddNewCustomerModal']//div[@class='modal-content']")
    private WebElement add_new_customer_popup_cashandcarry_page;

    @FindBy(xpath = "//h4[@id='AddNewCustomerModalTitle']")
    private WebElement AddNewCustomerTitle;

    @FindBy(xpath = "//h4[text()='Add New Customer']/preceding-sibling::a//i")
    private WebElement AddNewCustomerCloseIcon;

    @FindBy(xpath = "(//a[@Class='close text-right no-padding'])[1]")
    private WebElement WalkingSettingPopupCloseIcon1;

    @FindBy(xpath = "//a[@id='btnEditWalkInSetting']")
    private List<WebElement> EditWalkInSettingButton;

    @FindBy(xpath = "//td[contains(text(),'Hana Testing Description')]/following-sibling::td//a[@id='btnEditWalkInSetting']")
    private WebElement Hana_Testing_DescriptionEditWalkInSettingButton1;

    @FindBy(xpath = "//td[contains(text(),'Automation_Don')]/following-sibling::td//a[@id='btnEditWalkInSetting']")
    private WebElement Automation_DontDeletetEditWalkInSettingButton;

    @FindBy(xpath = "//td[(text()='Automation')]/following-sibling::td//a[@id='btnEditWalkInSetting']")
    private WebElement AutomationEditWalkInSettingButton1;

    @FindBy(xpath = "//td[text()='Automation Clerk Desc']/following-sibling::td//a[@id='btnEditWalkInSetting']")
    private WebElement Automation_Clerk_DescEditWalkInSettingButton;
    //Cash Register unchecked FT
    @FindBy(xpath = "//td[text()='Cash Register unchecked FT']/following-sibling::td//a[@id='btnEditWalkInSetting']")
    private WebElement CashRegisterUncheckedFTEditWalkInSettingButton;

    @FindBy(xpath = "//td[text()='Cash Register2']/following-sibling::td//a[@id='btnEditWalkInSetting']")
    private WebElement CashRegister2EditWalkInSettingButton1;

    @FindBy(xpath = "//a[@class='m-r-50 CashRegisterInactive']")
    private List<WebElement> deleteWalkInSettingButton;

    @FindBy(xpath = "//td[contains(text(),'Automation Printer')]/following-sibling::td//a[@class='m-r-50 CashRegisterInactive']")
    private WebElement AutomationdeleteWalkInSettingButton1;

    @FindBy(xpath = "//h2[normalize-space()='Confirmation']")
    private WebElement DeleteConfirmationPopUpInWalkinSetting;

    @FindBy(xpath = "//p[contains(text(),'Are you sure you wish to remove this cash register')]")
    private WebElement AreYouSureYouWishToWalkinSettingText;

    @FindBy(xpath = "//input[@placeholder='Confirmation code']")
    private WebElement ConfirmationCodeTextBox;

    @FindBy(xpath = "//button[@class='cancel']")
    private WebElement CancelButtonInDeleteConfirmationPopUp;

    @FindBy(xpath = "//button[normalize-space()='OK']")
    private WebElement OKButtonInDeleteConfirmationPopUp;

    @FindBy(xpath = "//p[normalize-space()='Incorrect confirmation code!']")
    private WebElement IncorrectConfirmationCodeText;

    @FindBy(xpath = "(//div[@class='sa-input-error show'])[1]")
    private WebElement IncorrectDeleteConfirmationCodeErrorIcon;

    @FindBy(xpath = "((//table[@class='table table-responsive table-striped'])[2]//tr[1]//td[1])[1]")
    private WebElement WalkingSettingPopUpShopSiNo;

    @FindBy(xpath = "((//table[@class='table table-responsive table-striped'])[2]//tr[1]//td[2])[1]")
    private WebElement WalkingSettingPopUpShopClerkId;

    @FindBy(xpath = "((//table[@class='table table-responsive table-striped'])[2]//tr[1]//td[3])[1]")
    private WebElement WalkingSettingPopUpShopPrinterName;

    @FindBy(xpath = "((//table[@class='table table-responsive table-striped'])[2]//tr[1]//td[4])[1]")
    private WebElement WalkingSettingPopUpShopCashDrawCode;

    @FindBy(xpath = "((//table[@class='table table-responsive table-striped'])[2]//tr[1]//td[5])[1]")
    private WebElement WalkingSettingPopUpShopPrinterCutCode;

    @FindBy(xpath = "((//table[@class='table table-responsive table-striped'])[2]//tr[1]//td[6])[1]")
    private WebElement WalkingSettingPopUpShopClerkDesc;

    @FindBy(xpath = "((//table[@class='table table-responsive table-striped'])[2]//tr[1]//td[7])[1]")
    private WebElement WalkingSettingPopUpShopCustCopy;

    @FindBy(xpath = "((//table[@class='table table-responsive table-striped'])[2]//tr[1]//td[8])[1]")
    private WebElement WalkingSettingPopUpShopAction;

    @FindBy(xpath = "(//a[@class='close text-right no-padding'])[1]")
    private WebElement AddNewWalkInSettingPopupCloseIcon;

    // Choose page default values web elements
    @FindBy(xpath = "//select[@id='ddlShopShopDefaultNew']")
    private WebElement ShopChooseDefaultValuesDD;

    @FindBy(xpath = "//select[@id='ddlPagename']")
    private WebElement PageNameChooseDefaultValuesDD;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//div[@class='modal-content']//input[@id='txtBankName']")
    private WebElement BankNameTextBoxChooseDefaultValues;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//div[@class='modal-content']//input[@id='txtCheckNo']")
    private WebElement CheckNoTextBoxChooseDefaultValues;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//div[@class='modal-content']//input[@id='txtNameOnCheck']")
    private WebElement NameOnCheckTextBoxChooseDefaultValues;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//div[@class='modal-content']//input[@id='txtQty']")
    private WebElement QuantityTextBoxChooseDefaultValues;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//select[@id='ddlDefaultOccasion']")
    private WebElement DefaultOccasionDD;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//select[@id='ddlSalesPerson']")
    private WebElement SalesPersonDefaultValuesDD;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//select[@id='ddlSourceCode']")
    private WebElement SourceCodeDefaultValuesDD;

    @FindBy(xpath = "//button[@id='btnUpdate']")
    private WebElement ChooseDefaultUpdateButton;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//i")
    private WebElement CloseIconOnChooseDefaultValuesPopup;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//input[@id='txtItemCode']")
    private WebElement ItemCodeTextBoxChooseDefaultValues;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//input[@id='txtQty1']")
    private WebElement PhoneOrderQTYTextBoxChooseDefaultValues;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//input[@id='txtRecipientPhone']")
    private WebElement PhoneOrderRecipientPhoneTextBoxChooseDefaultValues;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//input[@id='txtRecipientZipcode']")
    private WebElement PhoneOrderRecipientZipcodeTextBoxChooseDefaultValues;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//select[@id='ddlDefaultWireInMethod']")
    private WebElement PhoneOrderWireInMethodDefaultValuesDD;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//select[@id='ddlDefaultWireOutMethod']")
    private WebElement PhoneOrderWireOutMethodDefaultValuesDD;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//select[@id='ddlPaymentType']")
    private WebElement PhoneOrderPaymentTypeDefaultValuesDD;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//select[@id='ddlPhoneOrderCustomerType']")
    private WebElement PhoneOrderCustomerTypeDefaultValuesDD;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//select[@id='ddlSalesPerson']")
    private WebElement PhoneOrderSalesPersonDefaultValuesDD;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//select[@id='ddlSelectCountry']")
    private WebElement PhoneOrderSelectCountryDefaultValuesDD;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//select[@id='ddlSelectLocation']")
    private WebElement PhoneOrderSelectLocationDefaultValuesDD;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//select[@id='ddlSelectOccasion']")
    private WebElement PhoneOrderSelectOccasionDefaultValuesDD;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//select[@id='ddlSelectOrderType']")
    private WebElement PhoneOrderSelectOrderTypeDefaultValuesDD;

    @FindBy(xpath = "//div[@id='ShopDefaultModal']//select[@id='ddlSourceCode']")
    private WebElement PhoneOrderSourceCodeDefaultValuesDD;

    @FindBy(xpath = "(//div[@class='modal-content']//div//a//i)[1]")
    private WebElement CloseIconOnChooseDefaultValuesPagePopup;

    @FindBy(xpath = "(//button[@id='btnUpdate'])[1]")
    private WebElement ChooseDefaultValuesUpdateButton;

    @FindBy(xpath = "//select[@id='ddlExpenseType']")
    private WebElement Expense_Type_element;

    @FindBy(xpath = "//select[@id='ddlExpenseType']/option[1]")
    private WebElement Expense_Type_option_1_element;

    /**
     @Param - Cash And Carry Page Action methods implementations
     @Author - Balaji
     */

    //=================================== Cash and Carry Page functions ================================

    /**
     * Validates whether cash and carry page is appears
     *
     * @return If cash and carry page is displayed it returns true otherwise it return false
     * @Description: This function highlights the cash and carry page header, and then validate whether cash and carry page is appears
     * @Author: Balaji N
     */
    public boolean VerifyCashAndCarryPage() {
        return is_Element_Displayed(CashAndCarryPage, "Cash and Carry Page");
    }

    /**
     * Validates whether shop name dropdown field is appears on cash and carry page
     *
     * @return If shop name dropdown field is displayed it returns true otherwise it return false
     * @Description: This function highlights the shop name dropdown field, and then validate whether shop name dropdown field is appears on cash and carry page
     * @Author: Balaji N
     */
    public boolean shopnamedropdownIsDisplayed() {
        return is_Element_Displayed(ShopNameDropDown, "Shop Name dropdown field - Cash and Carry Page");
    }

    /**
     * It retrieves the selected shopname from a dropdown field on cash and carry page.
     *
     * @return It returns the selected shopname from dropdown field on cash and carry page otherwise it returns default values
     * @Description: This function will retrieves selected shopname drop down field on cash and carry page
     * @Author: Balaji N
     */
    public String getShopNameSelectedOption() {
        return get_selected_option(ShopNameDropDown, "Shop Name dropdown field - Cash and Carry Page");
    }

    /**
     * Validates whether clerk name field is appears on cash and carry page
     *
     * @return If clerk name dropdown field is displayed it returns true otherwise it return false
     * @Description: This function highlights the clerk name dropdown field, and then validate whether clerk name dropdown field is appears on cash and carry page
     * @Author: Balaji N
     */
    public boolean clerkdropdownIsDisplayed() {
        return is_Element_Displayed(ClerkDropDown, "Clerk Name/Cash Registry dropdown field - Cash and Carry Page");
    }

    /**
     * Validates whether employee name dropdown field is appears on cash and carry page
     *
     * @return If employee name dropdown field is displayed it returns true otherwise it return false
     * @Description: This function highlights the employee name dropdown field, and then validate whether employee name dropdown field is appears on cash and carry page
     * @Author: Balaji N
     */
    public boolean employeedropdownIsDisplayed() {
        return is_Element_Displayed(EmployeeDropDown, "Employee Name dropdown field - Cash and Carry Page");
    }

    /**
     * Selects the shopname from dropdown field on cash and carry page
     *
     * @param shopname Provided shopname to be selected on shop dropdown field
     * @Description: This function used the reusable function as dropDown to select the value of visible text from dropdown field on cash and carry page
     * @Author: Balaji N
     */
    public void SelectShopName(String shopname) {
        logPageLoad("Cash and Carry Page → Shop Name Dropdown field", () -> {
            wait_For_Page_To_Be_Stable(getDriver());
            //fluentWait(ShopNameDropDown);
            drop_Down(ShopNameDropDown, shopname, "VisibleText", "Shop Name dropdown field - Cash and Carry Page");
        });
    }

    /**
     * It retrieves the selected shopname from a dropdown field on cash and carry page.
     *
     * @return It returns the selected shopname from dropdown field on cash and carry page otherwise it returns default values
     * @Description: This function will retrieves selected shopname drop down field on cash and carry page
     * @Author: Balaji N
     */
    public String get_selected_shopname() {
        return get_selected_option(ShopNameDropDown, "shopname dropdown field - Cash and Carry Page");
    }

    /**
     * Selects the clerk name from dropdown field on cash and carry page
     *
     * @param clerk Provided clerk name to be selected on clerk dropdown field
     * @Description: This function used the reusable function as dropDown to select the value of visible text from dropdown field on cash and carry page
     * @Author: Balaji N
     */
    public CashAndCarryPage SelectClerkName(String clerk) {
        fluentWait(ClerkDropDown);
        drop_Down(ClerkDropDown, clerk, "VisibleText", "Clerk Name dropdown field - Cash and Carry Page");
        return this;
    }

    /**
     * It retrieves the selected clerk name from a dropdown field on cash and carry page.
     *
     * @return If clerk name dropdown field is displayed it returns value of clerk name otherwise it return false
     * @Description: This function will retrieves selected clerk name drop down field on cash and carry page
     * @Author: Balaji N
     */
    public String get_selected_clerkname() {
        return get_selected_option(ClerkDropDown, "Clerk Name dropdown field - Cash and Carry Page");
    }

    /**
     * Selects the Employee name from dropdown field on cash and carry page
     *
     * @param employee Provided employee name to be selected on clerk dropdown field
     * @Description: This function used the reusable function as dropDown to select the value of visible text from dropdown field on cash and carry page
     * @Author: Balaji N
     */
    public CashAndCarryPage SelectEmployeeName(String employee) {
        delayWithGivenTime(500);
        drop_Down(EmployeeDropDown, employee, "VisibleText", "Employee dropdown field - Cash and Carry Page");
        return this;
    }

    /**
     * It retrieves the selected employee name from a dropdown field on cash and carry page.
     *
     * @return If employee name dropdown field is displayed it returns value of employee name otherwise it return false
     * @Description: This function will retrieves selected employee name drop down field on cash and carry page
     * @Author: Balaji N
     */
    public String get_selected_employeename() {
        return get_selected_option(EmployeeDropDown, "Employee dropdown field - Cash and Carry Page");
    }

    /**
     * Validates if the gift card & tile product is displayed on the cash and carry page.
     * <p>
     * This method iterates through all product tiles displayed on the cash and carry page,
     * highlights each tile, and checks if it is displayed.
     *
     * @return {@code true} if the gift card tile product is displayed, {@code false} otherwise.
     * @Description Iterates through all the product tiles on the cash and carry page,
     * highlights each tile, and checks its visibility.
     * @Author: Balaji N
     */
    public boolean GiftCardAndTilesIsDisplayed() {
        Boolean giftcardandtile = false;
        try {
            for (int i = 0; i < CashAndCarryTile.size(); i++) {
                WebElement tileElement = CashAndCarryTile.get(i);
                giftcardandtile = is_Element_Displayed(tileElement, "Product tile - Cash and Carry Page");
                if (!giftcardandtile) {
                    printError(tileElement, "Product tile - Cash and Carry Page",
                            "ElementNotVisibleException",
                            new Exception("Tile is not displayed as expected."));
                }
            }
        } catch (Exception e) {
            printError(null, "GiftCardAndTilesIsDisplayed",
                    e.getClass().getSimpleName(), e);
        }
        return giftcardandtile;
    }

    /**
     * Enters the item code on cash and carry page
     *
     * @param itemcode Item code to be entered on cash and carry page
     * @Description This function highlight the item code and then, performs a click action and enters the provided item code on cash and carry page
     * @Author Balaji N
     */
    public CashAndCarryPage EnterItemCode(String itemcode) {
        ClickAndType(ItemCode, itemcode, "Item code textbox field - Cash and Carry Page");
        return this;
    }

    /**
     * Enters the item description on cash and carry page
     *
     * @param description Item description to be entered
     * @Description This function highlight the item description and then, performs a click action and enters the provided item description on cash and carry page
     * @Author Balaji N
     */
    public CashAndCarryPage EnterItemDescription(String description) {
        ClickAndType(ItemDescription, description, "Item description textbox field - Cash and Carry Page");
        return this;
    }

    /**
     * Enters the item quantity on cash and carry page
     *
     * @param qty item quantity to be entered
     * @Description This function used jsClearAndType reusable method to clear the existing data, and then provided quantity to be entered
     * @Author Balaji N
     */
    public CashAndCarryPage EnterItemQuantity(String qty) {
        js_Clear_And_Type(ItemQuantity, qty, "Item Quantity textbox field - Cash and Carry Page");
        return this;
    }

    /**
     * Enters the item price on cash and carry page
     *
     * @param price item quantity to be entered
     * @Description This function used jsClearAndType reusable method to clear the existing data, and then provided item price to be entered
     * @Author Balaji N
     */
    public CashAndCarryPage EnterItemPrice(String price) {
        js_Clear_And_Type(ItemPrice, price, "Item Price textbox field - Cash and Carry Page");
        return this;
    }


    public double getDiscountAmtBasedonPercentage() {
        Actions actions = new Actions(getDriver());
        delayWithGivenTime(4000);
        // Perform Shift + Tab key press
        actions.keyDown(Keys.SHIFT)
                .sendKeys(Keys.TAB)
                .keyUp(Keys.SHIFT)
                .perform();

        delayWithGivenTime(4000);
        double originalPrice = Double.parseDouble(ItemPrice.getAttribute("value"));
        double discountPercentage = Double.parseDouble(ItemDiscountPercentage.getAttribute("value"));

        double expectedDiscountAmount = (originalPrice * discountPercentage) / 100;
        return expectedDiscountAmount;

    }


    public double getDiscountAmt() {
        Actions actions = new Actions(getDriver());
        delayWithGivenTime(4000);
        // Perform Shift + Tab key press
        actions.keyDown(Keys.SHIFT)
                .sendKeys(Keys.TAB)
                .keyUp(Keys.SHIFT)
                .perform();

        delayWithGivenTime(4000);
        double discountAmount = Double.parseDouble(ItemDiscount.getAttribute("value"));
        System.out.println("Discount Amount: " + discountAmount);
        return discountAmount;
    }

    /**
     * Enter the item discount on cash and carry page
     *
     * @param discount Provided Item discount value to be entered
     * @Description This function will clears the existing data and then entered the provided item discount
     * @Author Balaji N
     */
    public CashAndCarryPage EnterItemDiscount(String discount) {
        js_Clear_And_Type(ItemDiscount, discount, "Item Discount textbox field - Cash and Carry Page");
        return this;
    }

    /**
     * Clicks the add item plus icon on cash and carry page
     *
     * @Description This function used js_Click reusable method to perform scroll action to the respective element, and then click the element
     * @Expected functionality : If all the required fields to add item is exist respective item to to be added in table grid otherwise it throws validation message
     * @Author Balaji N
     */
    public CashAndCarryPage ClickAddItem() {
        js_Click(AddItemBtn, "Add Item Plus Icon on cash and carry page");
        return this;
    }

    /**
     * Enter the item discount percentage on cash and carry page
     *
     * @param discount_Percentage Provided Item discount percentage to be entered
     * @Description This function will send the provided item discount percentage, and then perform click action on item discount amount field
     * @Author Balaji N
     */
    public CashAndCarryPage EnterDiscountPercentageOnAddItem(String discount_Percentage) {
        try {
            ItemDiscountPercentage.sendKeys(discount_Percentage);
            delayWithGivenTime(2000);
            ItemDiscount.click();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while entering discount percentage cash and carry page: " + e.getMessage());
        }
        return this;
    }

    /**
     * Enter the item discount amount on cash and carry page
     *
     * @param discount_Amount Provided item discount amount to be entered
     * @Description This function will send the item discount amount, and then performs the click action on item discount percentage field
     * @Author Balaji N
     */
    public CashAndCarryPage EnterDiscountAmountOnAddItem(String discount_Amount) {
        try {
            ItemDiscount.sendKeys(discount_Amount);
            delayWithGivenTime(2000);
            ItemDiscountPercentage.click();
            return this;
        } catch (Exception e) {
            throw new RuntimeException("Unable to enter discount amount cash and carry page:" + e.getMessage());
        }
    }

    /**
     * It retrieves the discount percentage from the "Discount Percentage" field.
     *
     * @return If the discount percentage value is exist it return the displayed value, otherwise it returns the empty string
     * @Description This method returns the value of the item discount percentage
     * @Author Balaji N
     */
    public String getDiscountPercentageOnDiscIsAddedByDiscAmtField() {
        return get_element_attribute(ItemDiscountPercentage, "Discount Percentage value from discount percentage textbox field on cash and carry page");
    }

    /**
     * It retrieves the discount amount from the "Discount amount" field.
     *
     * @return If the discount amount value is exist it return the displayed value, otherwise it returns the empty string
     * @Description This method returns the value of the item discount amount
     * @Author Balaji N
     */
    public String getDiscountAmtOnDiscIsAddedByDiscPerAddItem() {
        return get_element_attribute(ItemDiscount, "Discount Amount value from discount amount textbox field on cash and carry page");
    }

    /**
     * Validate whether added item is displayed in table grid on cash and carry page
     *
     * @return If added item is displayed on table grid in row 1 it returns true, otherwise it return false
     * @Description This function highlight the added item row 1 in table grid, and then validate whether added item is displayed on row 1 in table grid
     * @Author Balaji N
     */
    public boolean VerifyAddedItem() {
        return is_Element_Displayed(AddedItemTableRow1, "Added Item row 1 in table grid on cash and carry page");
    }

    /**
     * Validate whether toast error message is displayed on cash and carry page
     *
     * @return If toast error message is displayed it returns true, otherwise it returns false
     * @Description This function will highlight the toast error message, and then validate whether toast error message is displayed
     * @Author Balaji N
     */
    public boolean IsToastErrorMessageDisplayed() {
        return isElementDisplayed(toasterrormsg, "Toast error message on cash and carry page");
    }

    /**
     * Searches and selects the customer name on the cash and carry page.
     *
     * @param customershorttext The customer name or short text to search for on select customer field
     * @param customername      Expected customer name to be selected from autosugest dropdown
     * @return The current instance of {@code CashAndCarryPage} for method chaining.
     * @Description This method clears the existing data in the "Select Customer" field, highlights the field,
     * clicks on it, and then enters the provided customer name to search for and select the
     * respective customer from the auto-complete dropdown.
     * @Author Balaji N
     */
    public void EnterCustomerName(String customershorttext, String customername) {
        int maxRetries = 3;   // You can adjust the retry count as needed
        int attempt = 0;
        boolean success = false;
        while (attempt < maxRetries && !success) {
            try {
                attempt++;
                if (SelectCustomer == null) {
                    throw new NullPointerException("Customer Search textbox field - cash and carry page - WebElement is null.");
                }

                SelectCustomer.clear();
                delayWithGivenTime(500);

                Double_Click_And_Type(SelectCustomer, customershorttext,
                        "Search customer textbox field - cash and carry page - WebElement");
                delayWithGivenTime(3000);

                if (CustomerList_autocomplete_dropdown_Option == null) {
                    throw new NullPointerException("list Of Customer Names On cash and carry page is null.");
                }

                if (CustomerList_autocomplete_dropdown_Option.isEmpty()) {
                    throw new NoSuchElementException("Autosuggestion list is empty for customer search textbox field: " + customername);
                }

                if (!CustomerList_autocomplete_dropdown_Option.get(0).isDisplayed()) {
                    throw new NoSuchElementException("Autosuggestion list is not displayed for customer search textbox field: " + customername);
                }

                boolean customerFound = false;
                for (WebElement customerElement : CustomerList_autocomplete_dropdown_Option) {
                    if (customerElement != null && customerElement.getText().contains(customername)) {
                        js_Click(customerElement, "Customer search textbox field Autosuggestion Element");
                        customerFound = true;
                        break;
                    }
                }

                if (!customerFound) {
                    throw new NoSuchElementException("No matching customer found in autosuggestion list for: " + customername);
                }

                // If we reach here, success!
                success = true;

            } catch (NullPointerException npe) {
                printError(SelectCustomer,
                        "Customer Search textbox field - on cash and carry page",
                        "NullPointerException: " + npe.getMessage(), npe);
                if (attempt >= maxRetries) throw npe;
                delayWithGivenTime(1500);  // Wait before retrying

            } catch (NoSuchElementException nse) {
                printError(SelectCustomer,
                        "Customer Search textbox field - on cash and carry page",
                        "NoSuchElementException: " + nse.getMessage(), nse);
                if (attempt >= maxRetries) throw nse;
                delayWithGivenTime(1500);

            } catch (StaleElementReferenceException sere) {
                printError(SelectCustomer,
                        "Customer Search textbox field - on cash and carry page",
                        "StaleElementReferenceException: The element is no longer attached to the DOM.", sere);
                if (attempt >= maxRetries) throw sere;
                delayWithGivenTime(1500);

            } catch (Exception e) {
                printError(SelectCustomer,
                        "Customer Search textbox field - on cash and carry page",
                        "Unexpected Exception: " + e.getMessage(), e);
                if (attempt >= maxRetries) throw e;
                delayWithGivenTime(1500);
            }
        }
    }

    /**
     * Search and select the customer on select customer field on cash and carry page
     *
     * @param customershorttext Provided the customer name to be entered
     * @return The current instance of {@code CashAndCarryPage} for method chaining.
     * @Description This function clears the existing data on select customer field, and then click on it, then enters the provided customer name to search for & to select the customer it used (arrow down key + Enter key)
     * @Author Balaji N
     */
    public CashAndCarryPage Enter_CustomerName(String customershorttext) {
        try {
            SelectCustomer.clear();
            clickAndType(SelectCustomer, customershorttext);
            delayWithGivenTime(3000);
            SelectCustomer.sendKeys(Keys.ARROW_DOWN);
            delayWithGivenTime(3000);
            SelectCustomer.sendKeys(Keys.ENTER);
            return this;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean is_Search_Customer_TextBox_Field_Displayed() {
        return isElementDisplayed(SelectCustomer, "Customer search textbox field on cash and carry page");
    }

  /*  public void Enter_Customer_Name_On_CashAndCarryPage(String customer_name) {
        try {
            // Clear the customer input field
            SelectCustomer.clear();

            // Type the customer name
            ClickAndType(SelectCustomer, customer_name, "Customer search textbox field on CashAndCarryPage");

            // Adding a delay to ensure the dropdown options load
            delayWithGivenTime(3000);

            // Locate and click the desired customer from the dropdown list
            WebElement SelectCustomer = getDriver().findElement(By.xpath("//ul[starts-with(@id, 'ui-id-')]//li//div[contains(text(), '" + customer_name + "')]"));
            js_Click(SelectCustomer, "Customer name Autosuggestion list from select customer textbox field - on cash and carry page");

        } catch (NoSuchElementException e) {
            throw new RuntimeException("Customer element not found for name: " + customer_name, e);
        } catch (ElementNotInteractableException e) {
            throw new RuntimeException("Customer element is not interactable for name: " + customer_name, e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout occurred while selecting customer: " + customer_name, e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while entering customer name on CashAndCarryPage: " + customer_name, e);
        }
    }
*/

    /**
     * Enter and select customer name from the autosuggestion dropdown on Cash and Carry page
     *
     * @param customer_name - Name of the customer to select
     * @Author Balaji N
     */
    public void Enter_Customer_Name_On_CashAndCarryPage(String customer_name) {
        int attempt = 0;
        int maxAttempts = 3;
        boolean isCustomerSelected = false;

        while (attempt < maxAttempts) {
            try {
                attempt++;
                SelectCustomer.clear();
                ClickAndType(SelectCustomer, customer_name, "Customer search textbox field on CashAndCarryPage");
                delayWithGivenTime(2000);

                By customerLocator = By.xpath("//ul[starts-with(@id, 'ui-id-')]//li//div[contains(text(), '" + customer_name + "')]");
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                WebElement customerOption = wait.until(ExpectedConditions.visibilityOfElementLocated(customerLocator));

                js_Click(customerOption, "Customer name from autosuggestion list on Cash and Carry Page");
                isCustomerSelected = true;
                break; // break the loop once selected

            } catch (Exception e) {
                System.out.println("Attempt " + attempt + ": Could not select customer '" + customer_name + "' - " + e.getMessage());
            }
        }

        if (!isCustomerSelected) {
            throw new RuntimeException("Failed to select customer '" + customer_name + "' after " + maxAttempts + " attempts.");
        }
    }

    /**
     * Select the tax type on cash and carry page
     *
     * @param taxtype Provided tax type to be selected from the drop down field on cash and carry page
     * @Description This method highlights the tax type dropdown field, and then selects a tax type based on the
     * visible text on cash and carry page
     * @Author Balaji N
     */
    public CashAndCarryPage SelectTaxType(String taxtype) {
        try {
            drop_Down(TaxType, taxtype, "VisibleText", "Tax Type dropdown field on cash and carry page");
            return this;
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Tax Type dropdown not found on the Cash and Carry page. Details: " + e);
        } catch (ElementNotInteractableException e) {
            throw new RuntimeException("Tax Type dropdown is not interactable on the Cash and Carry page. Details: " + e);
        } catch (Exception e) {
            throw new RuntimeException("Select the tax type on cash and carry page element not interactable due to " + e);
        }
    }

    /**
     * It retrieves the selected tax type on cash and carry page
     *
     * @return If tax type is selected it returns that as string value, otherwise it returns default value of tax type on cash and carry page
     * @Description This function retrieves the selected tax type on cash and carry page
     * @Author Balaji N
     */
    public String get_selected_tax_type() {
        return get_selected_option(TaxType, "Tax Type dropdown field value - Cash and Carry Page");
    }

    /**
     * Select the occasion from occasion dropdown field on cash and carry page
     *
     * @param occasion Provided occasion to be selected
     * @Description This method highlight the occasion dropdown field, and then select the visible text of provided occasion
     * @Author Balaji N
     */
    public CashAndCarryPage SelectOccasion(String occasion) {
        try {
            drop_Down(Occasion, occasion, "VisibleText", "Occasion dropdown field on cash and carry page");
            return this;
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Occasion dropdown not found on the Cash and Carry page. Details: " + e);
        } catch (ElementNotInteractableException e) {
            throw new RuntimeException("Occasion dropdown is not interactable on the Cash and Carry page. Details: " + e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred while selecting occasion on the Cash and Carry page. Details: " + e);
        }
    }


    /**
     * It retrieves the selected occasion on cash and carry page
     *
     * @return If occasion is selected it returns that as string value, otherwise it returns default value of occasion on cash and carry page
     * @Description This function retrieves the selected occasion on cash and carry page
     * @Author Balaji N
     */
    public String get_selected_occasion_value() {
        return get_selected_option(Occasion, "Occasion dropdown field value - Cash and Carry Page");
    }

    /**
     * Select the source code from source code dropdown field on cash and carry page
     *
     * @param sourcecode Provided source code to be selected
     * @Description This function highlight the source code dropdown field, and then select the visible text of source code dropdowm option
     * @Author Balaji N
     */
    public CashAndCarryPage SelectSourceCode(String sourcecode) {
        drop_Down(SourceCode, sourcecode, "VisibleText", "Source Code dropdown field on cash and carry page");
        return this;
    }

    /**
     * It retrieve the sub total displayed price on cash and carry page
     *
     * @return If sub total price is display it returns sub total price as double format, otherwise it returns null
     * @Description This method retrieve the sub total as string format, and remove the special characters, then converted it into double format
     * @Author Balaji N
     */
    public double getSubTotalPrice() {
        String subtotal = get_Element_Text(SubTotal, "Sub Total - Cash and Carry Page");
        String sub_total = subtotal.replaceAll("[^a-zA-Z0-9]", "");
        double Sub_Total = Double.parseDouble(sub_total);
        return Sub_Total;
    }

    /**
     * Click the pay button on cash and carry page
     *
     * @Description This method highlight the pay button, and then perform click action using js_Click reusable function
     * @Author Balaji N
     */
    public void ClickPayButton() {
        try {
            logPageLoad("Cash and Carry Page → Cash and Carry Payment Page", () -> {
                click(PayBtn, "Pay Button - Cash and Carry Page");
            });
        } catch (TimeoutException toe) {
            Allure.step("After clicking Pay Button, did not navigate to the Cash and Carry Payment Page within expected time.", () -> {
                throw toe;
            });
            throw toe;
        } catch (Exception e) {
            Allure.step("Failed during Pay Button click or navigation.", () -> {
                throw e;
            });
            throw e;
        }
    }

    /**
     * Validate whether add product table grid column labels are appear on cash and carry page
     *
     * @return If all the column labels are displayed it returns true, Otherwise return false
     * @Description This function highlights the each and every column labels on added product table grid, and then validate whether each and every column labels are displayed
     * @Author Balaji N
     */
    public boolean ItemEntryLabels() {
        is_Element_Displayed(ItemCodeLabel, "Item Code Label");
        is_Element_Displayed(ItemDescriptionLabel, "Item Description Label");
        is_Element_Displayed(ItemQuantityLabel, "Item Quantity Label");
        is_Element_Displayed(ItemPriceLabel, "Item Price Label");
        is_Element_Displayed(ItemDiscountLabel, "Item Discount Label");
        is_Element_Displayed(ItemDiscountPercentageLabel, "Item Discount Percentage Label");
        is_Element_Displayed(ActionLabel, "Action Label");
        return true;
    }

    /**
     * It retrieves the item code placeholder text is appears on cash and carry page
     *
     * @return If item code place holder value is exists, it returns value of item code placeholder, otherwise it returns empty string
     * @Description This function highlights the item code field, and then retreives the item code placeholder
     */
    public String ItemCodePlaceholder() {
        return get_Placeholder_Element_Attribute(ItemCode, "Item Code textbox fieldPlaceholder - Cash and Carry Page");
    }

    /**
     * Search and Select item by entering the item code value on item code textbox field in cash and carry page
     *
     * @param itemcode The item code to be entered for searching the expected item to select
     * @Description: This function highlight & clears the exists item code on itemcode textbox field, Double click to select the item code field then enter the provided item code search for and Select the expected item description from autosuggest dropdown field
     * @Author Balaji N
     */
    public void SearchAndSelectTheItemCode(String itemcode) {
        try {
            HighlightElement(ItemCode);
            ItemCode.clear();
            DoubleClickAndType(ItemCode, itemcode);
            delayWithGivenTime(3000);
            if (isElementDisplayed(itemcode_autocomplete) == true) {
                for (WebElement ele : itemcodelist) {
                    if (ele.getText().contains(prop.getProperty("productfulldesc1"))) {
                        js_Click(ele, "Item code from autocomplete list - Cash and Carry Page");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean is_Autosuggestion_Dropdown_Displayed_for_ItemCode(String itemcode) {
        ItemCode.clear();
        Double_Click_And_Type(ItemCode, itemcode, "Item code textbox field - Cash and Carry Page");
        delayWithGivenTime(3000);
        return isElementDisplayed(itemcode_autocomplete, "Autosuggestion dropdown for item code");
    }

    /**
     * Search and select the item code textbox field on cash and carry page
     *
     * @param itemcode        - the item code to be searched
     * @param itemdescription - the item description to be selected
     * @Author Balaji N
     */
    public void SearchAndSelectTheItemCode(String itemcode, String itemdescription) {
        int attempt = 0;
        int maxAttempts = 3;
        boolean isSelected = false;

        while (attempt < maxAttempts) {
            try {
                attempt++;
                ItemCode.clear();
                Double_Click_And_Type(ItemCode, itemcode, "Item code textbox field - Cash and Carry Page");
                delayWithGivenTime(2000);

                if (is_Element_Displayed(itemcode_autocomplete, "Autocomplete list for item code: " + itemcode)) {
                    By productLocator = By.xpath("//ul[starts-with(@id, 'ui-id-')]//li//div[contains(text(),'" + itemdescription + "')]");
                    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                    WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator));
                    js_Click(product, "Item code from autocomplete list - Cash and Carry Page");
                    isSelected = true;
                    break; // success — exit loop
                } else {
                    System.out.println("Attempt " + attempt + ": Autocomplete not visible, retrying...");
                }

            } catch (Exception e) {
                System.out.println("Attempt " + attempt + ": Exception occurred - " + e.getMessage());
            }
        }

        if (!isSelected) {
            throw new RuntimeException("Failed to select item code '" + itemcode + "' with description '" + itemdescription + "' after " + maxAttempts + " attempts on cash and carry page.");
        }
    }

    public boolean is_Autosuggestion_Dropdown_NotDisplayed_ItemCode(String itemcode) {
        try {
            ItemCode.clear();
            Double_Click_And_Type(ItemCode, itemcode, "Item code textbox field - Cash and Carry Page");
            delayWithGivenTime(3000);

            return itemcode_autocomplete.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    /**
     * Search and Select item by entering the item code value on item code textbox field in cash and carry page
     *
     * @param itemcode        Provided item code to be entered for search expected item
     * @param itemdescription Provided expected item to be select
     * @Description: This function highlight the item code textbox field, and then perform double click action to select the itemcode if exist, and enter the provided item code for searching the expected item and select the expected item from autosuggest dropdown field
     * @See {@link SearchAndSelectTheItemCode} This method will be alternative for this method
     * @Author Balaji N
     */
    public void SearchAndSelect_ItemCode(String itemcode, String itemdescription) {
        HighlightElement(ItemCode);
        DoubleClickAndType(ItemCode, itemcode);
        delayWithGivenTime(3000);

        for (WebElement ele : itemcodelist) {
            if (ele.getText().contains(itemdescription)) {
                js_Click(ele);
                break;
            }
        }
    }

    /**
     * Search and select the item from item description textbox field on cash and carry page
     *
     * @param itemdescription Provided item description to be entered
     * @Description This function highlight the item description textbox field, and perform click action then enter the provided item description, and Select the expected item from autosuggest dropdown field on cash and carry page
     * @Author Balaji N
     */
    public void searchAndselectWithItemDescription(String itemdescription) {
        clickAndType(ItemDescription, itemdescription);
        delayWithGivenTime(2500);

        for (int i = 0; i < ItemDescList.size(); i++) {
            if (ItemDescList.get(i).getText().equals("RDWB-Premium Red Roses Double Wrapped Bouquet -40")) {
                js_Click(ItemDescList.get(i));
                break;
            }
        }
    }

    /**
     * It retrieves the entered or displayed item code on cash and carry page
     *
     * @return If entered item code is exists it returns the value of item code, otherwise it returns empty string
     * @Description: This function highlight the item code, and then retrieves the entered or displayed item code on cash and carry page
     * @Author Balaji N
     */
    public String ItemCodeValueIsExists() {
        return getElementAttribute(ItemCode, "Item code value on cash and carry page");
    }

    /**
     * It retrieves the item description field displayed placeholder value on cash and carry page
     *
     * @return If item description placeholder displayed it returns the placeholder value of item description on cash and carry page
     * @Description: This function highlight the item description field, and then retrieves the item description placeholder displayed text from cash and carry page
     * @Author Balaji N
     */
    public String ItemDescription() {
        return get_Placeholder_Element_Attribute(ItemDescription, "Item description placeholder on cash and carry page");
    }

    /**
     * It retrieves the item description field displayed or entered value on cash and carry page
     *
     * @return If item description entered or displayed value is exists, it returns the value of item description on cash and carry page
     * @Description: This function highlight the item description field, and then retrieves the item description entered or displayed text from cash and carry page
     * @Author Balaji N
     */
    public String ItemDescriptionValueIsExist() {
        return getElementAttribute(ItemDescription, "Item description value on cash and carry page");
    }

    /**
     * It retrieves the item quantity field displayed or entered value on cash and carry page
     *
     * @return If item quantity entered or displayed value is exists, it returns the value of item quantity on cash and carry page
     * @Description: This function highlight the item quantity field, and then retrieves the item quantity entered or displayed text from cash and carry page
     * @Author Balaji N
     */
    public String ItemQtyValueIsExist() {
        return getElementAttribute(ItemQuantity, "Item quantity value on cash and carry page");
    }

    /**
     * It retrieves the item quantity field displayed placeholder value on cash and carry page
     *
     * @return If item quantity displayed placeholder value is exists, it returns the placeholder of item quantity on cash and carry page
     * @Description: This function highlight the item quantity field, and then retrieves the item quantity displayed placeholder text from cash and carry page
     * @Author Balaji N
     */
    public String ItemQtyPlaceholder() {
        return get_Placeholder_Element_Attribute(ItemQuantity, "Item quantity placeholder on cash and carry page");
    }

    /**
     * It retrieves the item price field displayed placeholder text on cash and carry page
     *
     * @return If item price displayed placeholder value is exists, it returns the placeholder text of item price on cash and carry page
     * @Description: This function highlight the item price field, and then retrieves the item price displayed placeholder text from cash and carry page
     * @Author Balaji N
     */
    public String ItemPricePlaceholder() {
        return get_Placeholder_Element_Attribute(ItemPrice, "Item price placeholder on cash and carry page");
    }

    /**
     * It retrieves the item price field displayed or entered value on cash and carry page
     *
     * @return If item price entered or displayed value is exists, it returns the value of item price on cash and carry page
     * @Description: This function highlight the item price field, and then retrieves the item price entered or displayed text from cash and carry page
     * @Author Balaji N
     */
    public String ItemPriceValueIsExist() {
        return getElementAttribute(ItemPrice, "Item price value on cash and carry page");
    }

    /**
     * It retrieves the item discount amount field displayed placeholder text on cash and carry page
     *
     * @return If item discount amount displayed placeholder value is exists, it returns the placeholder text of item discount amount field on cash and carry page
     * @Description: This function highlight the item discount amount field, and then retrieves the item discount amount displayed placeholder text from cash and carry page
     * @Author Balaji N
     */
    public String ItemDiscountPlaceholder() {
        return get_Placeholder_Element_Attribute(ItemDiscount, "Item discount placeholder on cash and carry page");
    }

    /**
     * It retrieves the item discount amount field displayed or entered value on cash and carry page
     *
     * @return If item discount amount entered or displayed value is exists, it returns the value of item discount amount on cash and carry page
     * @Description: This function highlight the item discount amount field, and then retrieves the item discount amount entered or displayed text from cash and carry page
     * @Author Balaji N
     */
    public String ItemDiscountAmountIsExist() {
        return getElementAttribute(ItemDiscount, "Item discount amount value on cash and carry page");
    }

    /**
     * It retrieves the item discount percentage field displayed placeholder text on cash and carry page
     *
     * @return If item discount percentage displayed placeholder value is exists, it returns the placeholder text of item discount percentage field on cash and carry page
     * @Description: This function highlight the item discount percentage field, and then retrieves the item discount percentage displayed placeholder text from cash and carry page
     * @Author Balaji N
     */
    public String ItemDiscountPercentagePlaceholder() {
        return get_Placeholder_Element_Attribute(ItemDiscountPercentage, "Item discount percentage placeholder on cash and carry page");
    }

    /**
     * It retrieves the item discount percentage field displayed or entered value on cash and carry page
     *
     * @return If item discount percentage entered or displayed value is exists, it returns the value of item discount percentage on cash and carry page
     * @Description: This function highlight the item discount percentage field, and then retrieves the item discount percentage entered or displayed text from cash and carry page
     * @Author Balaji N
     */
    public String ItemDiscountPercentageValueIsExist() {
        return getElementAttribute(ItemDiscountPercentage, "Item discount percentage value on cash and carry page");
    }

    /**
     * Search and selects the item from item description field on cash and carry page
     *
     * @param itemdescription The item description to be entered on that field to search
     * @Description: This function highlights the item description field, and click on it, then enter the item description to search and select the expected item
     * @Author Balaji N
     */
    public void SearchAndSelectProdByItemDescription(String itemdescription) {
        HighlightElement(ItemDescription); //"premium"
        clickAndType(ItemDescription, itemdescription);
        delayWithGivenTime(3000);

        for (int i = 0; i < ItemDescList.size(); i++) {
            if (ItemDescList.get(i).getText().equals("RDWB-Premium Red Roses Double Wrapped Bouquet -40")) {
                ItemDescList.get(i).click();
                break;
            }
        }
    }

    /**
     * Search and selects the item from item description field on cash and carry page
     *
     * @param itemdescription The item description to be entered on that field to search
     * @Description: This function highlights the item description field, and click on it, then enter the item description to search and select the expected item
     * @Author Balaji N
     */
    public void SearchAndSelectProdByItemDescription(String itemdescription, String fulldesc) {
        ClickAndType(ItemDescription, itemdescription, "product description textbox field row 1 Cash and carry page");
        delayWithGivenTime(3000);
        boolean productFound = false;

        for (WebElement item : ItemDescList) {
            try {
                String itemText = item.getText().trim();
                if (itemText.contains(fulldesc)) {
                    js_Click(item, "Autosuggestion item - Product description textbox (Row 1) - Cash and Carry page");
                    productFound = true;
                    break;
                }
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException occurred, retrying...");
                continue;
            }
        }

        if (!productFound) {
            System.err.println("No matching product found for description: " + fulldesc);
        }
    }


    /**
     * Validate whether coupon label is appears on cash and carry page
     *
     * @return If coupon label is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the coupon label, and then validate whether coupon label is appears on cash and carry page
     * @Author Balaji N
     */
    public boolean CouponLabel() {
        HighlightElement(CouponLabel);
        return CouponLabel.isDisplayed();
    }

    /**
     * Validate whether discount label is appears on cash and carry page
     *
     * @return If discount label is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the discount label, and then validate whether discount label is appears on cash and carry page
     * @Author Balaji N
     */
    public boolean DiscountLabel() {
        HighlightElement(DiscountLabel);
        return DiscountLabel.isDisplayed();
    }

    /**
     * Validate whether discount dollar label is appears on cash and carry page
     *
     * @return If discount dollar label is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the discount dollar label, and then validate whether discount dollar label is appears on cash and carry page
     * @Author Balaji N
     */
    public boolean DiscountDollarLabel() {
        HighlightElement(DiscountDollarLabel);
        return DiscountDollarLabel.isDisplayed();
    }

    /**
     * Validate whether select customer label is appears on cash and carry page
     *
     * @return If select customer label is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the select customer label, and then validate whether select customer label is appears on cash and carry page
     * @Author Balaji N
     */
    public boolean SelectCustomerLabel() {
        HighlightElement(SelectCustomerLabel);
        return SelectCustomerLabel.isDisplayed();
    }

    /**
     * Validate whether tax type label is appears on cash and carry page
     *
     * @return If tax type label is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the tax type label, and then validate whether tax type label is appears on cash and carry page
     * @Author Balaji N
     */
    public boolean TaxtypeLabel() {
        HighlightElement(TaxTypeLabel);
        return TaxTypeLabel.isDisplayed();
    }


    /**
     * Validate whether occasion label is appears on cash and carry page
     *
     * @return If occasion label is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the occasion label, and then validate whether occasion label is appears on cash and carry page
     * @Author Balaji N
     */
    public boolean occasionLabel() {
        HighlightElement(OccasionLabel);
        return OccasionLabel.isDisplayed();
    }

    /**
     * Validate whether source code label is appears on cash and carry page
     *
     * @return If source code label is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the source code label, and then validate whether source code label is appears on cash and carry page
     * @Author Balaji N
     */
    public void sourceCodeLabel() {
        HighlightElement(SourceCodeLabel);
        SourceCodeLabel.isDisplayed();
    }

    /**
     * Enter the coupon code on cash and carry page
     *
     * @param couponcode Provided coupon code to be entered
     * @Description: This function highlight the coupon field, and double click to select if coupon code is existed and then enter the provided coupon code value on that field & perform to press Tab key to move the cursor on next field
     * @Author Balaji N
     */
    public void EnterCouponCode(String couponcode) {
        Double_Click_And_Type(CouponCodeTextbox, couponcode, "Coupon code textbox field on cash and carry page");
        delayWithGivenTime(1000);
        CouponCodeTextbox.sendKeys(Keys.TAB);
    }

    /**
     * Enter the coupon code on cash and carry page
     *
     * @param couponcode The coupon code to be entered
     * @Description: This function entered the provided coupon code value on coupon code field
     * @Author Balaji N
     */
    public void SetCouponCode(String couponcode) {
        try {
            CouponCodeTextbox.sendKeys(couponcode);
        } catch (Exception e) {
            throw new RuntimeException(" Error while entering the coupon code: " + couponcode + " " + e);
        }
    }

    /**
     * Retrieve the entered coupon code value on cash and carry page
     *
     * @return If coupon code entered on coupon code field is exists, it returns the value of coupon code, otherwise it returns empty string
     * @Description: This function retrieves the coupon code entered on coupon code field
     * @Author Balaji N
     */
    public String get_entered_coupon_code_value() {
        return CouponCodeTextbox.getAttribute("value");
    }

    /**
     * Click on discount amount field on cash and carry page
     *
     * @Description: This function click on discount amount field
     * @Author Balaji N
     */
    public void ClickDiscountDollarField() {
       /* HighlightElement(DiscountDollarTextbox);
        DiscountDollarTextbox.click();*/
        js_Click(DiscountDollarTextbox, "Click on discount amount field on cash and carry page");
    }

    /**
     * It retrieve the coupon code entered on coupon code field
     *
     * @return If coupon code entered on coupon code field is exists, it returns the value of coupon code, otherwise it returns empty string
     * @Description: This function retrieves the coupon code entered on coupon code field
     * @Author Balaji N
     */
    public String getCouponCode() {
        return getElementAttribute(CouponCodeTextbox, "Coupon code value on cash and carry page");
    }

    /**
     * It retrieves the coupon code placeholder text on cash and carry page
     *
     * @return If coupon code displayed placeholder value is exists, it returns the placeholder text of coupon code on cash and carry page
     * @Description: This function highlight the coupon code field, and then retrieves the coupon code displayed placeholder text from cash and carry page
     * @Author Balaji N
     */
    public String CouponCodePlaceHolder() {
        return get_Placeholder_Element_Attribute(CouponCodeTextbox, "Coupon code placeholder on cash and carry page");
    }

    /**
     * Enter the discount percentage on cash and carry page
     *
     * @param discount The discount percentage to be entered
     * @Description: This function clears the discount percentage field, and then entered the provided discount percentage value on discount percentage field & perform to press Tab key to move the cursor on next field
     * @Author Balaji N
     */
    public void SetDiscount(String discount) {
        DiscountPercentTextbox.clear();
        delayWithGivenTime(1000);
        DiscountPercentTextbox.sendKeys(discount);
        delayWithGivenTime(1000);
        DiscountPercentTextbox.sendKeys(Keys.TAB);
    }

    /**
     * Enter the discount percentage on cash and carry page
     *
     * @param discount The discount percentage to be entered
     * @Description: This function highlights and entered the provided discount percentage value on discount percentage field
     * @Author Balaji N
     */
    public void EnterDiscount(String discount) {
        Double_Click_And_Type(DiscountPercentTextbox, discount, "Discount percentage textbox field on cash and carry page");
        PressTabKey();
    }

    /**
     * It retrieves the discount percentage placeholder text on cash and carry page
     *
     * @return If discount percentage displayed placeholder value is exists, it returns the placeholder text of discount percentage on cash and carry page
     * @Description: This function highlight the discount percentage field, and then retrieves the discount percentage displayed placeholder text from cash and carry page
     * @Author Balaji N
     */
    public String DiscountTextBoxPlaceHolder() {
        HighlightElement(DiscountPercentTextbox);
        return DiscountPercentTextbox.getAttribute("placeholder");
    }

    /**
     * It retrieves the discount percentage text on cash and carry page
     *
     * @return If discount percentage displayed  value is exists, it returns the placeholder text of discount percentage on cash and carry page
     * @Description: This function highlight the discount percentage field, and then retrieves the discount percentage displayed  text from cash and carry page
     * @Author Balaji N
     */
    public String get_discountTextBox_value() {
        HighlightElement(DiscountPercentTextbox);
        return DiscountPercentTextbox.getAttribute("value");
    }

    /**
     * It retrieves the discount percentage field displayed placeholder text on cash and carry page
     *
     * @return If discount percentage displayed placeholder value is exists, it returns the placeholder text of discount percentage on cash and carry page
     * @Description: This function highlight the discount percentage field, and then retrieves the discount percentage displayed placeholder text from cash and carry page
     * @Author Balaji N
     */
    public String DiscountpercentageTextbox() {
        return get_Placeholder_Element_Attribute(Discountpertextbox, "Discount percentage placeholder on cash and carry page");
    }

    /**
     * Enter the discount percentage on cash and carry page
     *
     * @param discountpercentage The discount percentage to be entered
     * @Description: This function highlights and entered the provided discount percentage value on discount percentage field
     * @Author Balaji N
     */
    public void EnterDiscountPercentage(String discountpercentage) {
        jsClearAndType(Discountpertextbox, discountpercentage);
    }

    /**
     * It retrieves the discount percentage field displayed placeholder text on cash and carry page
     *
     * @return If discount percentage displayed placeholder value is exists, it returns the placeholder text of discount percentage on cash and carry page
     * @Description: This function highlight the discount percentage field, and then retrieves the discount percentage displayed placeholder text from cash and carry page
     * @Author Balaji N
     */
    public String DiscountPercentagePlaceHolder() {
        HighlightElement(Discountpertextbox);
        return Discountpertextbox.getAttribute("placeholder");
    }

    /**
     * Validate whether discount amount field is disabled or not
     *
     * @return If discount amount field is disabled, it returns true or vise versa
     * @Description: This function highlights the discount amount field, and then validate whether discount amount field is disabled or not
     * @Author Balaji N
     */
    public boolean DiscountAmountFieldIsDisabled() {
        return is_Element_Enabled(DiscountDollarTextbox, "Discount amount field on cash and carry page");
    }

    /**
     * It retrieves the discount amount field displayed placeholder text on cash and carry page
     *
     * @return If discount amount displayed placeholder value is exists, it returns the placeholder text of discount amount on cash and carry page
     * @Description: This function highlight the discount amount field, and then retrieves the discount amount displayed placeholder text from cash and carry page
     * @Author Balaji N
     */
    public String DiscountAmountPlaceHolder() {
        return get_Placeholder_Element_Attribute(DiscountAmtTextbox, "Discount amount placeholder on cash and carry page");
    }

    /**
     * It retrieves the tax type field displayed placeholder text on cash and carry page
     *
     * @return If tax type displayed placeholder value is exists, it returns the placeholder text of tax type on cash and carry page
     * @Description: This function highlight the tax type field, and then retrieves the tax type displayed placeholder text from cash and carry page
     * @Author Balaji N
     */
    public String TaxTypePlaceholder() {
        return get_Placeholder_Element_Attribute(TaxType, "Tax type placeholder on cash and carry page");
    }

    /**
     * It retrieves the select customer field displayed placeholder text on cash and carry page
     *
     * @return If select customer field displayed placeholder value is exists, it returns the placeholder text of select customer on cash and carry page
     * @Description: This function highlight the select customer field, and then retrieves the select customer displayed placeholder text from cash and carry page
     * @Author Balaji N
     */
    public String SelectCustomerPlaceholder() {
        return get_Placeholder_Element_Attribute(SelectCustomer, "Select customer placeholder on cash and carry page");
    }

    /**
     * It retrieves the customer name displayed on cash and carry page
     *
     * @return If customer name displayed value is exists, it returns the customer name on cash and carry page. Otherwise it returns empty string
     * @Description: This function highlight the select customer field, and then retrieves the customer name displayed value from cash and carry page
     * @Author Balaji N
     */
    public String getDisplayedCustomerName() {
        return getElementText(CustomerName, "customer name label on cash and carry page");
    }

    /**
     * It validates whether add customer button is displayed on cash and carry page
     *
     * @return If add customer button is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the add customer button, and then validates whether add customer button is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean CustomerAddButton() {
        return is_Element_Displayed(AddCustomerBtn, "Add customer button on cash and carry page");
    }

    /**
     * It validates whether reconcile button is displayed on cash and carry page
     *
     * @return If reconcile button is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the reconcile button, and then validates whether reconcile button is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean ReconcileBtnIsDisplayed() {
        return is_Element_Displayed(ReconcileBtn, "Reconcile button on cash and carry page");
    }

    /**
     * Validate whether pay out button is disabled on cash and carry page
     *
     * @return If pay out button is disabled on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the pay out button, and then validates whether pay out button is disabled on cash and carry page
     * @Author Balaji N
     */
    public boolean is_Payout_Button_Disabled() {
        return !isElementEnabled(PayOutBtn, "Pay out button on cash and carry page");
    }

    /**
     * It validates whether pay out button is displayed on cash and carry page
     *
     * @return If pay out button is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the pay out button, and then validates whether pay out button is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean PayOutBtnIsDisplayed() {
        return is_Element_Displayed(PayOutBtn, "Pay out button on cash and carry page");
    }

    /**
     * Click on pay out button on cash and carry page
     *
     * @Description: This function click on pay out button using js_Click method
     * @Author Balaji N
     */
    public void ClickPayOutBtn() {
        click(PayOutBtn, "Pay out button on cash and carry page");
    }

    /**
     * Validate whether sales history button is displayed on cash and carry page
     *
     * @return If sales history button is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the sales history button, and then validates whether sales history button is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean SalesHistoryIsDisplayed() {
        return is_Element_Displayed(SalesHistoryBtn, "Sales history button on cash and carry page");
    }

    /**
     * Click on sales history button on cash and carry page
     *
     * @Description: This function click on sales history button using click method
     * @Author Balaji N
     */
    public void ClickSalesHistoryBtn() {
        click(SalesHistoryBtn, "Sales history button on cash and carry page");
    }

    /**
     * Validate whether open drawer button is displayed on cash and carry page
     *
     * @return If open drawer button is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the open drawer button, and then validates whether open drawer button is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean OpenDrawerIsDisplayed() {
        return is_Element_Displayed(OpenCashDrawerBtn, "Open drawer button on cash and carry page");
    }

    /**
     * Validate whether verify password popup is displayed on cash and carry page
     *
     * @return If verify password popup is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the verify password popup, and then validates whether verify password popup is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean VerifyPasswordPopupIsDisplayed() {
        return is_Element_Displayed(verifyPasswordPopup, "Verify password popup on cash and carry page");
    }

    /**
     * Validate whether verify password popup header is displayed on cash and carry page
     *
     * @return If verify password popup header is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the verify password popup header, and then validates whether verify password popup header is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean VerifyPasswordPopup() {
        return is_Element_Displayed(VerifyPasswordPopupHeader, "Verify password popup header on cash and carry page");
    }

    /**
     * Enter verify password in verify password popup on cash and carry page
     *
     * @param verifyPassword provided verify password to be entered
     * @Description: This function highlights the verify password textbox, and then double click to select, and enters the provided verify password in verify password textbox on cash and carry page
     * @Author Balaji N
     */
    public void EnterVerifyPassword(String verifyPassword) {
        Double_Click_And_Type(VerifyPasswordTextbox, verifyPassword, "Verify password textbox field on verify password popup on cash and carry page");
    }

    /**
     * It retrieves the entered verify password on verify password popup on cash and carry page
     *
     * @return If verify password is entered on verify password popup on cash and carry page it returns the value of verify password, otherwise it returns empty string
     * @Description: This function retrieves the entered verify password on verify password popup on cash and carry page
     * @Author Balaji N
     */
    public String getEnteredVerifyPassword() {
        return getElementAttribute(VerifyPasswordTextbox, "Verify password textbox field value on verify password popup on cash and carry page");
    }

    /**
     * Click on verify button on verify password popup on cash and carry page
     *
     * @Description: This function clicks on verify button using js_Click method on verify password popup on cash and carry page
     * @Author Balaji N
     */
    public void ClickVerifyBtnOnPasswordPopup() {
        js_Click(verifyBtn_PasswordPopup, "Verify button on verify password popup on cash and carry page");
    }

    /**
     * Click on open drawer button on cash and carry page
     *
     * @Description: This function clicks on open drawer button using js_Click method on cash and carry page
     * @Author Balaji N
     */
    public void ClickOpenDrawerBtn() {
        js_Click(OpenCashDrawerBtn, "Open cash drawer button on cash and carry page");
    }

    public boolean is_Open_Drawer_Button_Disabled() {
        return !isElementEnabled(OpenCashDrawerBtn, "Open cash drawer button on cash and carry page");
    }

    /**
     * Validate whether alt P text is displayed on cash and carry page
     *
     * @return If alt P text is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the alt P text, and then validates whether alt P text is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean AltPTextIsDisplayed() {
        return is_Element_Displayed(PressAltPtocontinuetopaymentpageText, "Alt P text on cash and carry page");
    }

    /**
     * Click on alt P text on cash and carry page
     *
     * @Description: This function clicks on alt P text using click method on cash and carry page
     * @Author Balaji N
     */
    public void ClickSplitPaymentToogleButton() {
        click(PressAltPtocontinuetopaymentpageText, "Press Alt P to continue to payment page text on cash and carry page");
    }

    /**
     * Validate whether split payment toggle button is enabled or disabled on cash and carry page
     *
     * @return If split payment toggle button is enabled on cash and carry page it returns true or vice-versa, otherwise it returns false
     * @Description: This function highlights the split payment toggle button, and then validates whether split payment toggle button is enabled or disabled on cash and carry page
     * @Author Balaji N
     */
    public boolean ValidateSplitPaymentToogleButton() {
        is_Element_Displayed(SplitPaymentToogleButton, "Split Payment Toggle Button on cash and carry page");
        return is_Element_Enabled(SplitPaymentToogleButton, "Split Payment Toggle Button on cash and carry page");
    }

    /**
     * Validate whether sub total label is displayed on cash and carry page
     *
     * @return If sub total label is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the sub total label, and then validates whether sub total label is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean ValidateSubTotalLabelPresence() {
        return is_Element_Displayed(SubTotalLabel, "Sub Total Label on cash and carry page");
    }

    /**
     * It retrieves the sub total default value on cash and carry page
     *
     * @return If sub total default value is displayed on cash and carry page it returns the value of sub total default value, otherwise it returns empty string
     * @Description: This function highlights the sub total default value, and then retrieves the sub total default value on cash and carry page
     * @Author Balaji N
     */
    public String ValidateSubTotalDefaultValue() {
        return getElementText(DefaultSubTotalValues, "Sub Total Default Value on cash and carry page");
    }

    /**
     * Validate whether GST label is displayed on cash and carry page
     *
     * @return If GST label is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the GST label, and then validates whether GST label is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean ValidateGSTLabel() {
        return is_Element_Displayed(GSTLabel, "GST Label on cash and carry page");
    }

    /**
     * Validate whether GST default value is displayed on cash and carry page
     *
     * @return If GST default value is displayed on cash and carry page it returns the value of GST default value, otherwise it returns empty string
     * @Description: This function highlights the GST default value, and then retrieves the GST default value on cash and carry page
     * @Author Balaji N
     */
    public String ValidateGSTDefaultValue() {
        return getElementText(GSTDefaultValue, "GST Default Value on cash and carry page");
    }

    /**
     * Validate whether PST/HST/QST label is displayed on cash and carry page
     *
     * @return If PST/HST/QST label is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the PST/HST/QST label, and then validates whether PST/HST/QST label is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean ValidatePSTHSTQSTLabel() {
        return is_Element_Displayed(PSTHSTQSTLabel, "PST/HST/QST Label on cash and carry page");
    }

    /**
     * It retrieves the PST/HST/QST default value on cash and carry page
     *
     * @return If PST/HST/QST default value is displayed on cash and carry page it returns the value of PST/HST/QST default value, otherwise it returns empty string
     * @Description: This function highlights the PST/HST/QST default value, and then retrieves the PST/HST/QST default value on cash and carry page
     * @Author Balaji N
     */
    public String ValidatePSTHSTQSTDefaultValues() {
        return getElementText(PSTHSTQSTDefaultValues, "PST/HST/QST Default Value on cash and carry page");
    }

    /**
     * Validate whether Tax label is displayed on cash and carry page
     *
     * @return If Tax label is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the Tax label, and then validates whether Tax label is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean ValidateTaxLabel() {
        return is_Element_Displayed(TaxLabel, "Tax Label on cash and carry page");
    }

    /**
     * It retrieves the Tax default value on cash and carry page
     *
     * @return If Tax default value is displayed on cash and carry page it returns the value of Tax default value, otherwise it returns empty string
     * @Description: This function highlights the Tax default value, and then retrieves the Tax default value on cash and carry page
     * @Author Balaji N
     */
    public String ValidateTaxDefaultValue() {
        return getElementText(TaxDefaultValues, "Tax Default Value on cash and carry page");
    }

    /**
     * Validate whether Discount label is displayed on cash and carry page
     *
     * @return If Discount label is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the Discount label, and then validates whether Discount label is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean ValidateDiscountLabel() {
        return is_Element_Displayed(Discountlabel, "Discount Label on cash and carry page");
    }

    /**
     * It retrieves the Discount default value on cash and carry page
     *
     * @return If Discount default value is displayed on cash and carry page it returns the value of Discount default value, otherwise it returns null string
     * @Description: This function highlights the Discount default value, and then retrieves the Discount default value on cash and carry page
     * @Author Balaji N
     */
    public String ValidateDiscountDefaultValues() {
        return getElementText(DiscountDefaultValues, "Discount Default Value on cash and carry page");
    }

    /**
     * Validate whether Grand Total label is displayed on cash and carry page
     *
     * @return If Grand Total label is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the Grand Total label, and then validates whether Grand Total label is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean ValidateGrandTotalLabel() {
        return is_Element_Displayed(GrandTotalLabel, "Grand Total Label on cash and carry page");
    }

    /**
     * It retrieves the Grand Total default value on cash and carry page
     *
     * @return If Grand Total default value is displayed on cash and carry page it returns the value of Grand Total default value, otherwise it returns null string
     * @Description: This function highlights the Grand Total default value, and then retrieves the Grand Total default value on cash and carry page
     * @Author Balaji N
     */
    public String ValidateGrandTotalDefaultValue() {
        return getElementText(GrandTotalDefaultValue, "Grand Total Default Value on cash and carry page");
    }

    /**
     * Click on Choose Default Values icon on cash and carry page
     *
     * @Description: This function highlights the Choose Default Values icon, and then click on it using js_Click method
     * @Author Balaji N
     */
    public void ClickChooseDefaultValue() {
        js_Click(chooseDefaultValuesIcon, "Choose Default Values icon on cash and carry page");
    }

    /**
     * Validate whether Choose Default Values popup header is displayed on cash and carry page
     *
     * @return If Choose Default Values popup header is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the Choose Default Values popup header, and then validate whether Choose Default Values popup header is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean ValidateChoosePageDefaultValuePopupIsDisplayed() {
        isElementDisplayed(choose_page_default_values_popup, "Choose Default Values popup on cash and carry page");
        return is_Element_Displayed(choosepagedefaultvaluespopupHeader, "Choose Default Values popup header on cash and carry page");
    }

    /**
     * Selects the default occasion value from the dropdown on cash and carry page
     *
     * @Description: This function highlights the default occasion dropdown, and then selects the default occasion value from the dropdown
     * @Author Balaji N
     */
    public void SelectDefaultOccasion() {
        drop_Down(defaultoccasiondd, "--select--", "VisibleText", "Occasion dropdown field on choose default values popup - Cash and Carry Page");
    }

    /**
     * Selects the default sales person value from the dropdown on cash and carry page
     *
     * @Description: This function highlights the default sales person dropdown, and then selects the default sales person value from the dropdown
     * @Author Balaji N
     */
    public void SelectDefaultSalesPerson() {
        drop_Down(salespersondd, "--select--", "VisibleText", "Sales Person dropdown field on choose default values popup - Cash and Carry Page");
    }

    /**
     * Selects the default source code value from the dropdown on cash and carry page
     *
     * @Description: This function highlights the default source code dropdown, and then selects the default source code value from the dropdown
     * @Author Balaji N
     */
    public void SelectDefaultSourcecode() {
        drop_Down(sourcecodedd, "--select--", "VisibleText", "Source Code dropdown field on choose default values popup - Cash and Carry Page");
    }

    /**
     * Click on Update button in choose default popup on cash and carry page
     *
     * @Description: This function highlights the Update button, and then click on it using click method
     * @Author Balaji N
     */
    public void ClickUpdateButtonOnChooseDefaultPopup() {
        Click(updateBtn, "Update button on choose default values popup - Cash and Carry Page");
    }

    /**
     * It retrieves the selected option from the employeedropdown on cash and carry page
     *
     * @return If employee selected value is displayed on cash and carry page it returns the value of employee selected value, otherwise it returns null string
     * @Description: This function highlights the employee dropdown, and then retrieves the selected option from the dropdown
     * @Author Balaji N
     */
    public String GetEmployeeDDfirstOption() {
        return get_Selected_Option(EmployeeDropDown, "Employee dropdown field value on cash and carry page");
    }

    /**
     * It retrieves the selected option from the occasion dropdown on cash and carry page
     *
     * @return If occasion selected value is displayed on cash and carry page it returns the value of occasion selected value, otherwise it returns null string
     * @Description: This function highlights the occasion dropdown, and then retrieves the selected option from the dropdown
     * @Author Balaji N
     */
    public String GetOccasionDDfirstOption() {
        return get_Selected_Option(Occasion, "Occasion dropdown field value on cash and carry page");
    }

    /**
     * It retrieves the selected option from the source code dropdown on cash and carry page
     *
     * @return If source code selected value is displayed on cash and carry page it returns the value of source code selected value, otherwise it returns null string
     * @Description: This function highlights the source code dropdown, and then retrieves the selected option from the dropdown
     * @Author Balaji N
     */
    public String GetSourceCodeDDfirstOption() {
        return get_Selected_Option(SourceCode, "Source Code dropdown field value on cash and carry page");
    }

    /**
     * Checks whether the walking setting icon is displayed on cash and carry page
     *
     * @return If the walking setting icon is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the walking setting icon, and then checks whether the walking setting icon is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean WalkingSettingIconIsExist() {
        return is_Element_Displayed(CashAndCarryPageWalkinsSettingIcon, "Walking setting icon on cash and carry page");
    }

    /**
     * Checks whether the default values icon is displayed on cash and carry page
     *
     * @return If the default values icon is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the default values icon, and then checks whether the default values icon is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean ChooseDefaultValueIconIsExist() {
        return is_Element_Displayed(chooseDefaultValuesIcon, "Default values icon on cash and carry page");
    }

    /**
     * Clicks on Refresh form button on cash and carry page
     *
     * @Description: This function highlights the Refresh form button, and then click on it using click method
     * @Author Balaji N
     */
    public void ClickRefreshForm() {
        Click(RefreshFormBtn, "Refresh form button on cash and carry page");
    }

    /**
     * Clicks on product tile on cash and carry page
     *
     * @return The current instance of {@code CashAndCarryPage} for method chaining.
     * @throws Exception If an exception occurs while clicking on the product tile.
     * @Description: This function highlights the product tile, and then clicks on it using js_Click method
     * @Author Balaji N
     */
    public CashAndCarryPage ClickProductTitle() {
        int i;
        for (i = 0; i < CashAndCarryTile.size(); i++) {
            if (CashAndCarryTile.get(i).getAttribute("title").contains("ballonsYY-Ballons small-$40.00")) {
                js_Click(CashAndCarryTile.get(i), "Product tile on cash and carry page");
                break;
            }
        }
        return this;
    }

    /**
     * Checks whether the product tile is displayed on cash and carry page
     *
     * @return If the product tile is displayed on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the product tile, and then checks whether the product tile is displayed on cash and carry page
     * @Author Balaji N
     */
    public boolean ProdTitleAddedItemIsExist() {
        int i;
        for (i = 0; i < listoftablerows.size(); i++) {
            if (listoftablerows.get(i).getText().contains("ballonsYY-Ballons small-$40.00")) {
                HighlightElement(listoftablerows.get(i));
                break;
            }
        }
        return listoftablerows.get(i).isDisplayed();
    }

    /**
     * It retrieves the added item code displayed on row1 of the cash and carry page
     *
     * @return If the added item code is displayed on row1 of the cash and carry page it returns the value of added item code, otherwise it returns null string
     * @Description: This function highlights the added item code, and then retrieves the added item code displayed on row1 of the cash and carry page
     * @Author Balaji N
     */
    public String getAddedItemCode() {
        return getElementText(AddedItemCodecol1, "Row 1 added item code");
    }

    /**
     * It retrieves the added item code displayed on row2 of the cash and carry page
     *
     * @return If the added item code is displayed on row2 of the cash and carry page it returns the value of added item code, otherwise it returns null string
     * @Description: This function highlights the added item code, and then retrieves the added item code displayed on row2 of the cash and carry page
     * @Author Balaji N
     */
    public String getAddedItemCodeRow2() {
        return getElementText(AddedItemCodeRow2, "Row 2 added item code");
    }

    /**
     * It retrieves the added item description displayed on row2 of the cash and carry page
     *
     * @return If the added item description is displayed on row2 of the cash and carry page it returns the value of added item description, otherwise it returns null string
     * @Author Balaji N
     */
    public String getAddedItemDescriptionRow2() {
        return getElementText(AddedItemDescriptioncol2, "Row 2 added item description");
    }

    /**
     * It retrieves the added item quantity displayed on row2 of the cash and carry page
     *
     * @return If the added item quantity is displayed on row2 of the cash and carry page it returns the value of added item quantity, otherwise it returns null string
     * @Author Balaji N
     */
    public String getAddedItemQuantityRow2() {
        return getElementText(AddedItemQuantitycol2, "Row 2 added item Quantity");
    }

    /**
     * It retrieves the added item price displayed on row2 of the cash and carry page
     *
     * @return If the added item price is displayed on row2 of the cash and carry page it returns the value of added item price, otherwise it returns null string
     * @Author Balaji N
     */
    public String getAddedItemPriceRow2() {
        return getElementText(AddedItemPricecol2, "Row 2 added item Price");
    }

    /**
     * It retrieves the added item extent price displayed on row2 of the cash and carry page
     *
     * @return If the added item extent price is displayed on row2 of the cash and carry page it returns the value of added item extent price, otherwise it returns null string
     * @Author Balaji N
     */
    public String getAddedItemExtentPriceRow2() {
        return getElementText(AddedItemExtendedPricecol2, "Row 2 added item Extent price");
    }

    /**
     * It retrieves the added item discount amount displayed on row2 of the cash and carry page
     *
     * @return If the added item discount amount is displayed on row2 of the cash and carry page it returns the value of added item discount amount, otherwise it returns null string
     * @Author Balaji N
     */
    public String getAddedItemDiscountAmountRow2() {
        return getElementText(AddedItemDiscountAmountcol2, "Row 2 added item discount amount");
    }

    /**
     * It retrieves the added item discount percentage displayed on row2 of the cash and carry page
     *
     * @return If the added item discount percentage is displayed on row2 of the cash and carry page it returns the value of added item discount percentage, otherwise it returns null string
     * @Author Balaji N
     */
    public String getAddedItemDiscountPercentageRow2() {
        return getElementText(AddedItemDiscountPercentagecol2, "Row 2 added item discount percentage");
    }

    /**
     * Clicks on particular - ballonsYY small - product tile on cash and carry page
     *
     * @Description: This function highlights the product tile, and then clicks on it using js_Click method
     * @Author Balaji N
     */
    public void ClickParticularProdTitle() {
        js_Click(CashAndCarryBalloonTitle, "Ballon tile product on cash and carry page");
    }

    public void clickParticularProdTitle() {
        js_Click(cashandcarry_balloon_title_product, "Ballon tile product on cash and carry page");
    }

    /**
     * Clicks on particular product tile (RSS Product in Hana POS shop)on cash and carry page
     *
     * @Description: This function highlights the product tile, and then clicks on it using js_Click method
     * @Author Balaji N
     */
    public void Select_Displayed_ProdTitle() {
        js_Click(CashAndCarry_RSS_ProductTitle, "RSS Product tile on cash and carry page");
    }

    /**
     * Selects particular provided product tile on cash and carry page
     *
     * @param prodtile The product tile to be selected
     * @Description: This function highlights the product tile, and then selects the product tile using js_Click method
     */
    public CashAndCarryPage SelectProductTile(String prodtile) {
        for (WebElement prodTile : ListOfProdTiles) {
            delayWithGivenTime(1000);
            if (prodTile.getAttribute("title").contains(prodtile)) {
                js_Click(prodTile);
                break;
            }
        }

        return this;

    }

    /**
     * Selects particular provided product tile on cash and carry page
     *
     * @param prodtile The product tile to be selected
     * @return The current instance of {@code CashAndCarryPage} for method chaining.
     * @Description: This function highlights the product tile, and then selects the product tile using js_Click method
     * @Author Balaji N
     */
    public CashAndCarryPage Select_Product_Tile(String prodtile) {
        for (WebElement prodTile : ListOfProdTiles) {
            delayWithGivenTime(1000);
            if (prodTile.getAttribute("title").contains(prodtile)) {
                HighlightElement(prodTile);
                js_Click(prodTile);
                break;
            }
        }
        return this;
    }

    /**
     * It retrieves the displayed product title tooltip on cash and carry page
     *
     * @return If the displayed product title tooltip is displayed on cash and carry page it returns the value of displayed product title tooltip, otherwise it returns null string
     * @Description: This function highlights the displayed product title tooltip, and then retrieves the displayed product title tooltip displayed on cash and carry page
     * @Author Balaji N
     */
    public String getDisplayedProductTitletooltip() {
        Actions action = new Actions(getDriver());
        explicitWait(CashAndCarryBalloonTitle, 10);
        action.moveToElement(CashAndCarryBalloonTitle).build().perform();
        delayWithGivenTime(1500);
        return get_Element_Text(CashAndCarryBalloonTitle, "Balloon Small tile product on cash and carry page"); // CashAndCarryBalloonTitle.getText();
    }

    /**
     * It retrieves the displayed product title tooltip on cash and carry page
     *
     * @return If the displayed product title tooltip is displayed on cash and carry page it returns the value of displayed product title tooltip, otherwise it returns null string
     * @Description: This function highlights the displayed product title tooltip, and then retrieves the displayed product title tooltip displayed on cash and carry page
     * @Author Balaji N
     */
    public String getDisplayed_ProductTitle_tooltip() {
        Actions action = new Actions(getDriver());
        explicitWait(CashAndCarry_RSS_ProductTitle);
        HighlightElement(CashAndCarry_RSS_ProductTitle);
        action.moveToElement(CashAndCarry_RSS_ProductTitle).build().perform();
        delayWithGivenTime(1500);
        return CashAndCarry_RSS_ProductTitle.getText();
    }

    /**
     * It checks whether the coupon code textbox is disabled or not
     *
     * @return If the coupon code textbox is disabled it returns true, otherwise it returns false
     * @Description: This function highlights the coupon code textbox, and then checks whether the coupon code textbox is disabled or not
     * @Author Balaji N
     */
    public boolean CouponcodeIsDisabled() {
        return is_Element_Enabled(CouponCodeTextbox, "Coupon code textbox on cash and carry page");
    }

    /**
     * It checks whether the discount percentage textbox is disabled or not (We can use vice-versa)
     *
     * @return If the discount percentage textbox is disabled it returns true, otherwise it returns false
     * @Description: This function highlights the discount percentage textbox, and then checks whether the discount percentage textbox is disabled or not
     * @Author Balaji N
     */
    public boolean DiscountPercentageFieldIsDisabled() {
        return is_Element_Enabled(DiscountPercentTextbox, "Discount percentage textbox on cash and carry page");
    }

    /**
     * Clicks on Add button for create new customer on cash and carry page
     *
     * @Description: This function highlights the Add button, and then clicks on it using click method
     * @Author Balaji N
     */
    public void ClickAddBtnForCreateNewCust() {
        Click(AddCustomerBtn, "Add customer button on cash and carry page");
    }

    /**
     * Validates whether add new customer popup title is exist or not
     *
     * @return If the add new customer popup title is exist it returns true, otherwise it returns false
     * @Description: This function highlights the add new customer popup title, and then validates whether add new customer popup title is exist or not
     * @Author Balaji N
     */
    public boolean VerifyAddNewCustomerPageIsExist() {
        isElementDisplayed(add_new_customer_popup_cashandcarry_page, "Add new customer popup on cash and carry page");
        return is_Element_Displayed(AddNewCustomerTitle, "Add new customer popup title on cash and carry page");
    }

    /**
     * Clicks on close icon in add new customer popup on cash and carry page
     *
     * @Description: This function highlights the close icon, and then clicks on it using js_Click method
     * @Author Balaji N
     */
    public void ClickCloseIconOnAddNewCustPopup() {
        js_Click(AddNewCustomerCloseIcon, "Close Icon on add new customer popup on cash and carry page");
    }

    /**
     * Validates whether customer id is exist or not on cash and carry page
     *
     * @return If the customer id is exist it returns true, otherwise it returns false
     * @Description: This function highlights the customer id, and then validates whether customer id is exist or not
     * @Author Balaji N
     */
    public boolean CustomerIdIsExist() {
        fluentWait(CustomerId);
        return is_Element_Displayed(CustomerId, "Customer Id on cash and carry page");
    }

    /**
     * It retrieves the newly created customer id on cash and carry page
     *
     * @return If the newly created customer id is exist it returns the value of newly created customer id, otherwise it returns null string
     * @Description: This function highlights the newly created customer id, and then retrieves the newly created customer id
     * @Author Balaji N
     */
    public String get_Displayed_CustomerId() {
        return getElementText(CustomerId, "Customer Id label on cash and carry page");
    }

    /**
     * Validates whether customer name is exist or not on cash and carry page
     *
     * @return If the customer name is exist it returns true, otherwise it returns false
     * @Description: This function highlights the customer name, and then validates whether customer name is exist or not
     * @Author Balaji N
     */
    public boolean CustomerNameIsExist() {
        fluentWait(CustomerName);
        return is_Element_Displayed(CustomerName, "Customer Name on cash and carry page");
    }

    /**
     * It retrieves the newly created customer name on cash and carry page
     *
     * @return If the newly created customer name is exist it returns the value of newly created customer name, otherwise it returns null string
     * @Description: This function highlights the newly created customer name, and then retrieves the newly created customer name
     * @Author Balaji N
     */
    public String getDisplayedCustomerNameOnCCPage() {
        return getElementText(CustomerName, "Selected customer name on cash and carry page");
    }

    /**
     * Validates whether cancel cross icon is displayed or not on cash and carry page
     *
     * @return If the cancel cross icon is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the cancel cross icon, and then validates whether cancel cross icon is displayed or not
     * @Author Balaji N
     */
    public boolean CustomerCancelCrossIconIsDisplayed() {
        fluentWait(cancelcustIcon, 10, 2);
        return is_Element_Displayed(cancelcustIcon, "Cancel cross icon on selected customer in cash and carry page");
    }

    /**
     * Clicks on cancel cross icon on cash and carry page
     *
     * @Description: This function highlights the cancel cross icon, and then clicks on it using js_Click method
     * @Author Balaji N
     */
    public void ClickCancelCustIcon() {
        js_Click(cancelcustIcon, "Cancel cross icon under selected customer in cash and carry page");
    }

    /**
     * Validates whether select customer dropdown is displayed or not on cash and carry page
     *
     * @return If the select customer dropdown is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the select customer dropdown, and then validates whether select customer dropdown is displayed or not
     * @Author Balaji N
     */
    public boolean SelectCustomerDDIsDisplayed() {
        explicitWait(SelectCustomer);
        return SelectCustomer.isDisplayed();
    }

    /**
     * Validates whether select customer label is displayed or not on cash and carry page
     *
     * @return If the select customer label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the select customer label, and then validates whether select customer label is displayed or not
     * @Author Balaji N
     */
    public boolean SelectCustomerLabelIsDisplayed() {
        explicitWait(SelectCustomerLabel);
        return is_Element_Displayed(SelectCustomerLabel, "Select customer label on cash and carry page");
    }

    /**
     * It retrieves the grand total on cash and carry page
     *
     * @return If the grand total is exist it returns the value of grand total, otherwise it returns null string
     * @Description: This function highlights the grand total, and then retrieves the grand total
     * @Author Balaji N
     */
    public String ActualGrandTotal() {
        return getElementText(GrandTotalDefaultValue, "Grand Total on cash and carry page");
    }

    public String getGrandTotalAmountOnCashAndCarryPage(){
       HighlightElement(GrandTotal);
        return GrandTotal.getText();
    }

    /**
     * Validates the grand total calculation on cash and carry page
     *
     * @return It returns the value of grand total calculated value from subtotal,tax,discount. If the grand total is calculated it returns the value of grand total, otherwise it returns null string
     * @Description: This function calculates subtotal, tax, discount value displayed value from cash and carry page and then we gets the grand total value based on calculation
     * @Author Balaji N
     */
    public String ValidateGrandTotal() {
        double subTotal = Double.parseDouble(DefaultSubTotalValues.getText().replace("$", "").trim());
        //double gst = Double.parseDouble(GSTDefaultValue.getText().replace("$", "").trim());
        //double psthstqst = Double.parseDouble(PSTHSTQSTDefaultValues.getText().replace("$", "").trim());
        double tax = Double.parseDouble(TaxDefaultValues.getText().replace("$", "").trim());
        double discount = Double.parseDouble(DiscountDefaultValues.getText().replace("$", "").trim());
        // gst + psthstqst ---- functionality removed in UI july 2024

        double expectedGrandTotal = (subTotal + tax) - discount;

        // Format the numbers to two decimal places
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedExpectedGrandTotal = df.format(expectedGrandTotal);
        return formattedExpectedGrandTotal;
    }

    public String getDiscountAmountOnCashAndCarryPage() {
        HighlightElement(Discount);
        return Discount.getText();
    }

    public String getTaxAmountOnCashAndCarryPage(){
        HighlightElement(Tax);
        return Tax.getText();
    }

    /**
     * It retrieves the pay button displayed price on cash and carry page
     *
     * @return If the pay button displayed price is exist it returns the value of pay button displayed price, otherwise it returns null string
     * @Description: This function retrieves the pay button displayed price and removed unwanted space & dollar sign
     * @Author Balaji N
     */
    public String ValidatePayButtonDisplayedPrice() {
        return get_Element_Text(PayBtnText, "Pay button displayed price on cash and carry page").replace("$", "").trim();
    }

    /**
     * Enters the discount percentage value on cash and carry page
     *
     * @Description: This function enters the discount percentage value on discount percentage field
     * @Author Balaji N
     */
    public void SetDiscountpercentageValue() {
        jsClearAndType(Discountpertextbox, "10");
    }

    /**
     * It retrieves the discount percentage value on cash and carry page
     *
     * @return If the discount percentage value is exist it returns the value of discount percentage value, otherwise it returns null string
     * @Description: This function retrieves the discount percentage value from discount percentage field
     * @Author Balaji N
     */
    public String getDiscountAmountvalue() {
        String discountAmt = DiscountAmtTextbox.getAttribute("value");
        return discountAmt;
    }

    /**
     * Clicks on change discount link on cash and carry page
     *
     * @return If the change discount link is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the change discount link, and then clicks on change discount link
     * @Author Balaji N
     */
    public boolean DiscountChangeLinkIsDisplayed() {
        return is_Element_Displayed(changediscountlink, "Change discount link above discount percentage field - cash and carry page");
    }

    /**
     * Validates the change discount pop up message is displayed or not
     *
     * @return If the change discount pop up message is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the change discount pop up message, and then validates whether change discount pop up message is displayed or not
     * @Author Balaji N
     */
    public boolean verifyChangePopUpMessage() {
        return is_Element_Displayed(changeDiscountPopupmessage, "Change discount pop up message on cash and carry page");
    }

    /**
     * Clicks on change discount link on cash and carry page
     *
     * @Description: This function clicks on change discount link on discount percentage field
     * @Author Balaji N
     */
    public void ClickChangeLinkOnDiscountPercentageField() {
        Click(changediscountlink, "Change discount link above discount percentage field - cash and carry page");
    }

    /**
     * Validates the warning icon is displayed or not on cash and carry page
     *
     * @return If the warning icon is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the warning icon, and then validates whether warning icon is displayed or not
     * @Author Balaji N
     */
    public boolean VerifyWarningIconIsDisplayed() {
        return is_Element_Displayed(changeDiscountPopupicon, "Warning icon on alert popup on cash and carry page");
    }

    /**
     * Validates the warning text is displayed or not on cash and carry page
     *
     * @return If the warning text is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the warning text, and then validates whether warning text is displayed or not
     * @Author Balaji N
     */
    public boolean VerifyUpdatingDiscountText() {
        return is_Element_Displayed(changeDiscountPopupUnboldedText, "Warning text on alert popup on cash and carry page");
    }

    /**
     * Validates the no button is displayed or not on change discount pop up on cash and carry page
     *
     * @return If the no button is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the no button, and then validates whether no button is displayed or not
     * @Author Balaji N
     */
    public boolean ChangeDiscountNoButton() {
        return is_Element_Displayed(changeDiscountPopupNoButton, "No button on alert popup on cash and carry page");
    }

    /**
     * Validates the yes button is displayed or not on change discount pop up on cash and carry page
     *
     * @return If the yes button is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the yes button, and then validates whether yes button is displayed or not
     * @Author Balaji N
     */
    public boolean ChangeDiscountYesButton() {
        return is_Element_Displayed(changeDiscountPopupYesButton, "Yes button on alert popup on cash and carry page");
    }

    /**
     * Clicks on no button on change discount pop up on cash and carry page
     *
     * @Description: This function clicks on no button on change discount pop up on discount percentage field using js_Clicks
     * @Author Balaji N
     */
    public void ClickChangeDiscountNoButton() {
        js_Click(changeDiscountPopupNoButton, "Change discount No button on alert popup on cash and carry page");
    }

    /**
     * Clicks on yes button on change discount pop up on cash and carry page
     *
     * @Description: This function clicks on yes button on change discount pop up on discount percentage field using clicks
     * @Author Balaji N
     */
    public void ClickChangeDiscountYesButton() {
        Click(changeDiscountPopupYesButton, "Change discount Yes button on alert popup on cash and carry page");
    }

    /**
     * Retrieves the discount amount value from discount percentage field in order level on cash and carry page
     *
     * @return If the discount amount value is exist it returns the value of discount amount value, otherwise it returns null string
     * @Description: This function retrieves the discount amount value from discount percentage field in order level on cash and carry page
     * @Author Balaji N
     */
    public String getDiscountAmountvalueOrderLevel() {
        String discountAmt = get_element_attribute(DiscountDollarTextbox, "Discount dollar textbox field");
        return discountAmt;
    }

    /**
     * Validates the change discount item link on product level is displayed or not on cash and carry page
     *
     * @return If the change discount item like on product level is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the change discount on product level, and then validates whether change discount on product level is displayed or not
     * @Author Balaji N
     */
    public boolean verifyChangeDiscountOnProductLevelIsDispalyed() {
        return isElementDisplayed(changediscountitemlink, "Change discount item link on product level on cash and carry page");
    }

    /**
     * Clicks on change discount item link on product level on cash and carry page
     *
     * @Description: This function clicks on change discount item link on product level on cash and carry page
     * @Author Balaji N
     */
    public void ClickChangeDiscountOnProductLevel() {
        js_Click(changediscountitemlink, "Change discount item link on product level on cash and carry page");
    }

    /**
     * Validates the change discount on product level popup is displayed or not on cash and carry page
     *
     * @return If the change discount on product level popup is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the change discount on product level popup, and then validates whether change discount on product level popup is displayed or not
     * @Author Balaji N
     */
    public boolean verifyChangeDiscountOnProductLevelPopupIsDispalyed() {
        return is_Element_Displayed(changeDiscountProductLevelPopup, "Change discount on product level popup on cash and carry page");
    }

    /**
     * Clicks yes button on change discount on product level popup on cash and carry page
     *
     * @Description: This function clicks yes button on change discount on product level popup on cash and carry page
     * @Author Balaji N
     */
    public void ClickChangeDiscountOnProductLevelYesButton() {
        Click(changeDiscountProductLevelPopupYesButton, "Change discount on product level popup - Yes button - on cash and carry page");
    }

    /**
     * Clicks on walkin setting icon on cash and carry page
     *
     * @Description: This function clicks on walkin setting icon on cash and carry page using js_Click function
     * @Author Balaji N
     */
    public void ClickWalkingSettingIcon() {
        js_Click(CashAndCarryPageWalkinsSettingIcon, "Walkins setting icon");
        if (verifyWalkingSettingPopupIsDisplayed() == false) {
            CashAndCarryPageWalkinsSettingIcon.click();
        }
    }

    /**
     * Clicks row1 delete icon on added item table on cash and carry page
     *
     * @Description: This function clicks row1 delete icon on added item table on cash and carry page using js_Click function
     * @Author Balaji N
     */
    public void ClickRow1DeleteIcon() {
        js_Click(AddedItemTableRow1DeleteIcon, "Delete Icon on item table Row 1");
    }

    /**
     * It retrieves the tooltip text when hover on walkin setting icon on cash and carry page
     *
     * @return If the tooltip text is exist it returns the value of tooltip text, otherwise it returns null string
     * @Description: This function highlights the walkin setting icon, and then retrieves the tooltip text when hover on walkin setting icon on cash and carry page
     * @Author Balaji N
     */
    public String VerifyWalkingSettingsToolTip() {
        HighlightElement(CashAndCarryPageWalkinsSettingIcon);
        MouseHover(CashAndCarryPageWalkinsSettingIcon);
        delayWithGivenTime(1500);
        String tooltipText = CashAndCarryPageWalkinsSettingIcon.getAttribute("title");
        return tooltipText;
    }

    /**
     * validates whether walkin setting popup is displayed or not on cash and carry page
     *
     * @return If the walkin setting popup is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the walkin setting popup, and then validates whether walkin setting popup is displayed or not
     * @Author Balaji N
     */
    public boolean verifyWalkingSettingPopupIsDisplayed() {
        explicitWait(WalkinsSettingPopup);
        return is_Element_Displayed(WalkinsSettingPopup, "Walkins setting popup");
    }

    /**
     * It retrieves the default shop name displayed dropdown value on walkin setting popup in cash and carry page
     *
     * @return If the default shop name is exist it returns the value of displayed shop name, otherwise it returns null string
     * @Description: This function highlights the walkin setting popup, and then retrieves the default shop name displayed dropdown value on walkin setting popup in cash and carry page
     * @Author Balaji N
     */
    public String VerifyDefaultShopNameonWalkingSettings() {
        return get_selected_option(WalkinsSettingPopupShopDropDown, "Default Shop Name on walkin setting popup");
    }

    /**
     * validates whether add new button is displayed on walkin setting popup on cash and carry page
     *
     * @return If the add new button is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the add new button, and then validates whether add new button is displayed on walkin setting popup on cash and carry page
     * @Author Balaji N
     */
    public boolean VerifyAddNewButtonIsDisplayedonWalkinsSettings() {
        return is_Element_Displayed(WalkinsSettingPopupAddNewBtn, "Add new button on walkin setting popup");
    }

    /**
     * validates whether close icon is displayed on walkin setting popup on cash and carry page
     *
     * @return If the close icon is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the close icon, and then validates whether close icon is displayed on walkin setting popup on cash and carry page
     * @Author Balaji N
     */
    public boolean VerifyWalkingPopupCloseIcon() {
        return is_Element_Displayed(WalkinsSettingPopupCloseIcon, "Close icon on walkin setting popup on cash and carry page");
    }

    /**
     * Clicks on close icon on walkin setting popup on cash and carry page
     *
     * @Description: This function highlights the close icon, and then clicks on it using js_Click function
     * @Author Balaji N
     */
    public void ClickWalkingPopupCloseIcon() {
        js_Click(WalkinsSettingPopupCloseIcon, "Close icon on walkin setting popup on cash and carry page");
    }

    /**
     * Selects shop name on walkin setting popup on cash and carry page
     *
     * @Description: This function highlights the shop name dropdown field, and then selects the value of visible text from dropdown field
     * @Author Balaji N
     */
    public void SelectShopNameonWalkingSetting() {
        drop_Down(WalkinsSettingPopupShopDropDown, prop.getProperty("ShopName"), "VisibleText", "Shop name drop down on walkins setting popup");
    }

    /**
     * It retrieves the shop table header SI No on walkin setting popup on cash and carry page
     *
     * @return If the shop table header SI No is exist it returns the value of shop table header SI No, otherwise it returns null string
     * @Description: This function highlights the shop table header SI No, and then retrieves the shop table header SI No on walkin setting popup on cash and carry page
     * @Author Balaji N
     */
    public String VerifyShopTableHeaderonWalkingSettingSno() {
        return getElementText(WalkingSettingPopUpShopSiNo, "Shop table header SI No on walkin setting popup on cash and carry page");
    }

    /**
     * It retrieves the shop table header Clerk Id on walkin setting popup on cash and carry page
     *
     * @return If the shop table header Clerk Id is exist it returns the value of shop table header Clerk Id, otherwise it returns null string
     * @Description: This function highlights the shop table header Clerk Id, and then retrieves the shop table header Clerk Id on walkin setting popup on cash and carry page
     * @Author Balaji N
     */
    public String VerifyShopTableHeaderonWalkingSettingClerkId() {
        return getElementText(WalkingSettingPopUpShopClerkId, "Shop table header Clerk Id on walkin setting popup on cash and carry page");
    }

    /**
     * It retrieves the shop table header Printer Name on walkin setting popup on cash and carry page
     *
     * @return If the shop table header Printer Name is exist it returns the value of shop table header Printer Name, otherwise it returns null string
     * @Description: This function highlights the shop table header Printer Name, and then retrieves the shop table header Printer Name on walkin setting popup on cash and carry page
     * @Author Balaji N
     */
    public String VerifyWalkingSettingShopPrinterName() {
        return getElementText(WalkingSettingPopUpShopPrinterName, "Shop table header Printer Name on walkin setting popup on cash and carry page");
    }

    /**
     * It retrieves the shop table header Cash Draw Code on walkin setting popup on cash and carry page
     *
     * @return If the shop table header Cash Draw Code is exist it returns the value of shop table header Cash Draw Code, otherwise it returns null string
     * @Description: This function highlights the shop table header Cash Draw Code, and then retrieves the shop table header Cash Draw Code on walkin setting popup on cash and carry page
     * @Author Balaji N
     */
    public String VerifyWalkingSettingShopCashDrawcode() {
        return getElementText(WalkingSettingPopUpShopCashDrawCode, "Shop table header Cash Draw Code on walkin setting popup on cash and carry page");
    }

    /**
     * It retrieves the shop table header Printer Cut Code on walkin setting popup on cash and carry page
     *
     * @return If the shop table header Printer Cut Code is exist it returns the value of shop table header Printer Cut Code, otherwise it returns null string
     * @Description: This function highlights the shop table header Printer Cut Code, and then retrieves the shop table header Printer Cut Code on walkin setting popup on cash and carry page
     * @Author Balaji N
     */
    public String VerifyWalkingSettingPrinterCutCode() {
        return getElementText(WalkingSettingPopUpShopPrinterCutCode, "Shop table header Printer Cut Code on walkin setting popup on cash and carry page");
    }

    /**
     * It retrieves the shop table header Clerk Description on walkin setting popup on cash and carry page
     *
     * @return If the shop table header Clerk Description is exist it returns the value of shop table header Clerk Description, otherwise it returns null string
     * @Description: This function highlights the shop table header Clerk Description, and then retrieves the shop table header Clerk Description on walkin setting popup on cash and carry page
     * @Author Balaji N
     */
    public String VerifyClerkDescWalkingSetting() {
        return getElementText(WalkingSettingPopUpShopClerkDesc, "Shop table header Clerk Description on walkin setting popup on cash and carry page");
    }

    /**
     * It retrieves the shop table header Customer Copy on walkin setting popup on cash and carry page
     *
     * @return If the shop table header Customer Copy is exist it returns the value of shop table header Customer Copy, otherwise it returns null string
     * @Description: This function highlights the shop table header Customer Copy, and then retrieves the shop table header Customer Copy on walkin setting popup on cash and carry page
     * @Author Balaji N
     */
    public String VerifyWalkingSettingCustcopy() {
        return getElementText(WalkingSettingPopUpShopCustCopy, "Shop table header Customer Copy on walkin setting popup on cash and carry page");
    }

    /**
     * It retrieves the shop table header Action icon on walkin setting popup on cash and carry page
     *
     * @return If the shop table header Action icon is exist it returns the value of shop table header Action, otherwise it returns null string
     * @Description: This function highlights the shop table header Action icon, and then retrieves the shop table header Action on walkin setting popup on cash and carry page
     * @Author Balaji N
     */
    public String VerifyWalkingSettingActionheader() {
        return getElementText(WalkingSettingPopUpShopAction, "Shop table header Action icon on walkin setting popup on cash and carry page");
    }

    /**
     * Click on close icon on walkin setting popup on cash and carry page
     *
     * @Description: This function highlights the close icon, and then clicks on it using click method
     * @Author Balaji N
     */
    public void ClickWalkingSettingPopupCloseIcon() {
        Click(WalkingSettingPopupCloseIcon1, "Close icon on walkin setting popup on cash and carry page");
    }

    /**
     * Validate Edit icon button presence on walkin setting popup on cash and carry page
     *
     * @return If the Edit icon button is exist it returns true, otherwise it returns false
     * @Description: This function highlights the Edit icon button, and then validates whether Edit button is exist on walkin setting popup on cash and carry page
     * @Author Balaji N
     */
    public boolean ValidateEditBtnPresenceOnWalkingSettingPopup() {
        int i = 0;
        for (i = 0; i < EditWalkInSettingButton.size(); i++) {
            if (is_Element_Displayed(EditWalkInSettingButton.get(i), "Edit icon button on walkin setting popup on cash and carry page")) {
                HighlightElement(EditWalkInSettingButton.get(i));
                break;
            }
        }
        return is_Element_Displayed(EditWalkInSettingButton.get(i), "Edit icon button on walkin setting popup on cash and carry page");
    }

    /**
     * Validate Delete icon presence on walkin setting popup on cash and carry page
     *
     * @return If the Delete icon button is exist it returns true, otherwise it returns false
     * @Description: This function highlights the Delete icon button, and then validates whether Delete button is exist on walkin setting popup on cash and carry page
     * @Author Balaji N
     */
    public boolean ValidateDeleteIconPresenceOnWalkingSettingPopup() {
        int i = 0;
        for (i = 0; i < deleteWalkInSettingButton.size(); i++) {
            if (is_Element_Displayed(deleteWalkInSettingButton.get(i), "Delete icon button on walkin setting popup on cash and carry page")) {
                HighlightElement(deleteWalkInSettingButton.get(i));
                break;
            }
        }
        return is_Element_Displayed(deleteWalkInSettingButton.get(i), "Delete icon button on walkin setting popup on cash and carry page");
    }

    /**
     * Click on Add New button on walkin setting popup on cash and carry page
     *
     * @Description: This function highlights the Add New button, and then clicks on it using click method
     * @Author Balaji N
     */
    public void ClickAddNewButtonOnWalkingSettingPopup() {
        js_Click(WalkinsSettingPopupAddNewBtn, "Add new button on walkin setting popup on cash and carry page");
    }

    /**
     * validates whether add new button popup screen is displayed on walkin setting popup on cash and carry page
     *
     * @return If the add new button popup screen is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the add new button popup screen, and then validates whether add new button popup screen is displayed on walkin setting popup on cash and carry page
     * @Author Balaji N
     */
    public boolean VerifyAddNewWalkingSettingPopupIsDisplayed() {
        return is_Element_Displayed(WalkinsSettingAddNewPopupScreen, "Add new walkins settings popup screen");
    }

    /**
     * validates whether clerk id label is displayed on add new popup on cash and carry page
     *
     * @return If the clerk id label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the clerk id label, and then validates whether clerk id label is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean clerkIdLabelonAddnewPopupIsExists() {
        return is_Element_Displayed(ClerkIdLabel, "Clerk id label on add new popup on cash and carry page");
    }

    /**
     * validates whether clerk id textbox is displayed on add new popup on cash and carry page
     *
     * @return If the clerk id textbox is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the clerk id textbox, and then validates whether clerk id textbox is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean clerkIdTextboxonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(ClerkIDTextBox, "Clerk id textbox on add new popup on cash and carry page");
    }

    /**
     * validates whether clerk description label is displayed on add new popup on cash and carry page
     *
     * @return If the clerk description label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the clerk description label, and then validates whether clerk description label is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean clerkDescriptionLabelonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(ClerkDescLabel, "Clerk description label on add new popup on cash and carry page");
    }

    /**
     * validates whether clerk description textbox is displayed on add new popup on cash and carry page
     *
     * @return If the clerk description textbox is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the clerk description textbox, and then validates whether clerk description textbox is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean clerkDescriptionTextboxonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(ClerkDescTextBox, "Clerk description textbox on add new popup on cash and carry page");
    }

    /**
     * validates whether printer name label is displayed on add new popup on cash and carry page
     *
     * @return If the printer name label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the printer name label, and then validates whether printer name label is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean printerNameonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(PrinterNameLabel, "Printer name label on add new popup on cash and carry page");
    }

    /**
     * validates whether printer name textbox is displayed on add new popup on cash and carry page
     *
     * @return If the printer name textbox is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the printer name textbox, and then validates whether printer name textbox is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean printerNameTextboxonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(PrinterNameTextBox, "Printer name textbox on add new popup on cash and carry page");
    }

    /**
     * validates whether manufacturer label is displayed on add new popup on cash and carry page
     *
     * @return If the manufacturer label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the manufacturer label, and then validates whether manufacturer label is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean manufacturerLabelonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(ManufacturerLabel, "Manufacturer label on add new popup on cash and carry page");
    }

    /**
     * validates whether manufacturer dropdown is displayed on add new popup on cash and carry page
     *
     * @return If the manufacturer dropdown is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the manufacturer dropdown, and then validates whether manufacturer dropdown is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean manufacturerDropdownonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(ManufacturerDropDown, "Manufacturer dropdown on add new popup on cash and carry page");
    }

    /**
     * Validates whether model label is displayed on add new popup on cash and carry page
     *
     * @return If the model label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the model label, and then validates whether model label is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean modelLabelonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(ModelLabel, "Model label on add new popup on cash and carry page");
    }

    /**
     * Validates whether model dropdown is displayed on add new popup on cash and carry page
     *
     * @return If the model dropdown is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the model dropdown, and then validates whether model dropdown is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean modelDropdownonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(ModelDropDown, "Model dropdown on add new popup on cash and carry page");
    }

    /**
     * validates whether cashdraw code label is displayed on add new popup on cash and carry page
     *
     * @return If the cashdraw code label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the cashdraw code label, and then validates whether cashdraw code label is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean cashdrawcodeLabelonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(CashDrawCodeLabel, "Cash draw code label on add new popup on cash and carry page");
    }

    /**
     * validates whether cashdraw code textbox is displayed on add new popup on cash and carry page
     *
     * @return If the cashdraw code textbox is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the cashdraw code textbox, and then validates whether cashdraw code textbox is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean cashdrawcodeTextboxonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(CashDrawCodeTextBox, "Cash draw code textbox on add new popup on cash and carry page");
    }

    /**
     * validates whether printercut code label is displayed on add new popup on cash and carry page
     *
     * @return If the printercut code label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the printercut code label, and then validates whether printercut code label is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean printercutcodeLabelonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(PrinterCutCodeLabel, "Printer cut code label on add new popup on cash and carry page");
    }

    /**
     * validates whether printercut code textbox is displayed on add new popup on cash and carry page
     *
     * @return If the printercut code textbox is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the printercut code textbox, and then validates whether printercut code textbox is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean printercutcodeTextboxonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(PrinterCutCodeTextBox, "Printer cut code textbox on add new popup on cash and carry page");
    }

    /**
     * validates whether clearant api key label is displayed on add new popup on cash and carry page
     *
     * @return If the clearant api key label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the clearant api key label, and then validates whether clearant api key label is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean clearantApiKeyLabelonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(ClearantAPIKeyLabel, "Clearant API key label on add new popup on cash and carry page");
    }

    /**
     * validates whether clearant api key textbox is displayed on add new popup on cash and carry page
     *
     * @return If the clearant api key textbox is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the clearant api key textbox, and then validates whether clearant api key textbox is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean clearantApiKeyTextboxonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(APIKeyTextBox, "API key textbox on add new popup on cash and carry page");
    }

    /**
     * validates whether clover device id label is displayed on add new popup on cash and carry page
     *
     * @return If the clover device id label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the clover device id label, and then validates whether clover device id label is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean cloverDeviceIdLabelonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(CloverDeviceIDLabel, "Clover device id label on add new popup on cash and carry page");
    }

    /**
     * validates whether clover device id textbox is displayed on add new popup on cash and carry page
     *
     * @return If the clover device id textbox is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the clover device id textbox, and then validates whether clover device id textbox is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean cloverDeviceIdTextboxonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(CloverDeviceIDTextBox, "Clover device id textbox on add new popup on cash and carry page");
    }

    /**
     * validates whether clover cash drawer id label is displayed on add new popup on cash and carry page
     *
     * @return If the clover cash drawer id label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the clover cash drawer id label, and then validates whether clover cash drawer id label is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean clovercashdrawerIdLabelonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(CloverCashDrawerIdLabel, "Clover cash drawer id label on add new popup on cash and carry page");
    }

    /**
     * Validates whether clover cash drawer id search icon is displayed on add new popup on cash and carry page
     *
     * @return If the clover cash drawer id search icon is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the clover cash drawer id search icon, and then validates whether clover cash drawer id search icon is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean clovercashdrawerIdsearchicononAddnewPopupIsDisplayed() {
        return is_Element_Displayed(CloverCashDrawerIdSearchIcon, "Clover cash drawer id search icon on add new popup on cash and carry page");
    }

    /**
     * validates whether enable clover printer label is displayed on add new popup on cash and carry page
     *
     * @return If the enable clover printer label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the enable clover printer label, and then validates whether enable clover printer label is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean enableCloverPrinteronAddnewPopupIsDisplayed() {
        return is_Element_Displayed(EnableCloverPrinterLabel, "Enable Clover printer label on add new popup on cash and carry page");
    }

    /**
     * validates whether enable clover printer checkbox is displayed on add new popup on cash and carry page
     *
     * @return If the enable clover printer checkbox is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the enable clover printer checkbox, and then validates whether enable clover printer checkbox is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean enableCloverPrinterCheckboxonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(EnableCloverPrinterCheckBox, "Enable Clover printer checkbox on add new popup on cash and carry page");
    }

    /**
     * validates whether open edge web id label is displayed on add new popup on cash and carry page
     *
     * @return If the open edge web id label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the open edge web id label, and then validates whether open edge web id label is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean openEdgeWebIDonAddnewPopupIsDisplayed() {
        HighlightElement(OpenEdgeWebIDLabel);
        return OpenEdgeWebIDLabel.isDisplayed();
    }

    /**
     * validates whether open edge web id textbox is displayed on add new popup on cash and carry page
     *
     * @return If the open edge web id textbox is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the open edge web id textbox, and then validates whether open edge web id textbox is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean openEdgeWebIDTextboxonAddnewPopupIsDisplayed() {
        HighlightElement(OpenEdgeWebIDTextBox);
        return OpenEdgeWebIDTextBox.isDisplayed();
    }

    /**
     * validates whether open edge terminal id label is displayed on add new popup on cash and carry page
     *
     * @return If the open edge terminal id label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the open edge terminal id label, and then validates whether open edge terminal id label is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean openEdgeTerminalIDLabelonAddnewPopupIsDisplayed() {
        HighlightElement(OpenEdgeTerminalIDLabel);
        return OpenEdgeTerminalIDLabel.isDisplayed();
    }

    /**
     * validates whether open edge terminal id textbox is displayed on add new popup on cash and carry page
     *
     * @return If the open edge terminal id textbox is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the open edge terminal id textbox, and then validates whether open edge terminal id textbox is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean openEdgeTerminalIDTextboxonAddnewPopupIsDisplayed() {
        HighlightElement(OpenEdgeTerminalIDTextBox);
        return OpenEdgeTerminalIDTextBox.isDisplayed();
    }

    /**
     * validates whether open edge auth key label is displayed on add new popup on cash and carry page
     *
     * @return If the open edge auth key label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the open edge auth key label, and then validates whether open edge auth key label is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean openEdgeAuthKeyLabelonAddnewPopupIsDisplayed() {
        HighlightElement(OpenEdgeAuthKeyLabel);
        return OpenEdgeAuthKeyLabel.isDisplayed();
    }

    /**
     * validates whether open edge auth key textbox is displayed on add new popup on cash and carry page
     *
     * @return If the open edge auth key textbox is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the open edge auth key textbox, and then validates whether open edge auth key textbox is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean openEdgeAuthKeyTextboxonAddnewPopupIsDisplayed() {
        HighlightElement(OpenEdgeAuthKeyTextBox);
        return OpenEdgeAuthKeyTextBox.isDisplayed();
    }

    /**
     * validates whether open edge customer copy label is displayed on add new popup on cash and carry page
     *
     * @return If the open edge customer copy label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the open edge customer copy label, and then validates whether open edge customer copy label is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean OpenEdgeCustomerCopyLabelonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(OpenEdgeCustomerCopyLabel, "Open edge customer copy label on add new popup on cash and carry page");
    }

    /**
     * validates whether open edge customer copy checkbox is displayed on add new popup on cash and carry page
     *
     * @return If the open edge customer copy checkbox is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the open edge customer copy checkbox, and then validates whether open edge customer copy checkbox is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean openEdgeCustomerCopyCheckboxonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(OpenEdgeCustomerCopyCheckBox, "Open Edge customer copy checkbox on add new popup on cash and carry page");
    }

    /**
     * validates whether open edge merchant copy label is displayed on add new popup on cash and carry page
     *
     * @return If the open edge merchant copy label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the open edge merchant copy label, and then validates whether open edge merchant copy label is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean OpenEdgeMerchantCopyLabelonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(OpenEdgeMerchantCopyLabel, "Open Edge merchant copy label on add new popup on cash and carry page");
    }

    /**
     * validates whether open edge merchant copy checkbox is displayed on add new popup on cash and carry page
     *
     * @return If the open edge merchant copy checkbox is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the open edge merchant copy checkbox, and then validates whether open edge merchant copy checkbox is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean openEdgeMerchantCopyCheckboxonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(OpenEdgeMerchantCopyCheckBox, "Open Edge merchant copy checkbox on add new popup on cash and carry page");
    }

    /**
     * validates whether customer copy note label is displayed on add new popup on cash and carry page
     *
     * @return If the cust copy note label is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the cust copy note label, and then validates whether cust copy note label is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean custCopyNoteLabelonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(CustomerCopyNoteLabel, "Customer copy note label on add new popup on cash and carry page");
    }

    /**
     * validates whether customer copy note text box is displayed on add new popup on cash and carry page
     *
     * @return If the cust copy note text box is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the cust copy note text box, and then validates whether cust copy note text box is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean custCopyNoteTextboxonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(CustomerCopyNoteTextBox, "Customer copy note text box on add new popup on cash and carry page");
    }

    /**
     * validates whether save button is displayed on add new popup on cash and carry page
     *
     * @return If the save button is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the save button, and then validates whether save button is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean merchantCopyNoteLabelonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(CustomerCopyNoteLabel, "Customer copy note label on add new popup on cash and carry page");
    }

    /**
     * validates whether save button is displayed on add new popup on cash and carry page
     *
     * @return If the save button is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the save button, and then validates whether save button is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean merchantCopyNoteTextboxonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(CustomerCopyNoteTextBox, "Customer copy note text box on add new popup on cash and carry page");
    }

    /**
     * validates whether save button is displayed on add new popup on cash and carry page
     *
     * @return If the save button is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the save button, and then validates whether save button is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean saveBtnonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(SaveWalkInSettingButton, "Save button on add new popup on cash and carry page");
    }

    /**
     * validates whether cancel button is displayed on add new popup on cash and carry page
     *
     * @return If the cancel button is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the cancel button, and then validates whether cancel button is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean cancelBtnonAddnewPopupIsDisplayed() {
        return is_Element_Displayed(CancelWalkInSettingButton, "Cancel button on add new popup on cash and carry page");
    }

    /**
     * validates whether close icon is displayed on add new popup on cash and carry page
     *
     * @return If the close icon is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the close icon, and then validates whether close icon is displayed on add new popup on cash and carry page
     * @Author Balaji N
     */
    public boolean closeIconOnAddnewPopupIsDisplayed() {
        return is_Element_Displayed(AddNewWalkInSettingPopupCloseIcon, "Add new popup close icon on cash and carry page");
    }

    /**
     * Enters ClerkID on add new popup walkin setting on cash and carry page
     *
     * @param ClerkID ClerkID to be entered
     * @Description: This function double click to select if clerk ID already exist & then enters ClerkID on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public void EnterClerkIDonAddnewPopup(String ClerkID) {
        js_ClickAndType(ClerkIDTextBox, ClerkID, "Clerk ID textbox field on add new popup on cash and carry page");
    }

    /**
     * It retrieves the clerk id entered on add new popup walkin setting on cash and carry page
     *
     * @return If the clerk id entered is exist it returns the value of clerk id entered, otherwise it returns null string
     * @Description: This function highlights the clerk id textbox, and then retrieves the clerk id entered on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public String getEnteredClerkIDonAddnewPopup() {
        return getElementAttribute(ClerkIDTextBox, "Clerk Id textbox field on add new popup on cash and carry page");
    }

    /**
     * Enters Clerk Description on add new popup walkin setting on cash and carry page
     *
     * @param ClerkDescription Provided Clerk Description to be entered
     * @Description: This function highlights & clicks the clerk description textbox, and then enters Clerk Description on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public void EnterClerkDescriptiononAddnewPopup(String ClerkDescription) {
        ClickAndType(ClerkDescTextBox, ClerkDescription, "Clerk Description textbox field on add new popup on cash and carry page");
    }

    /**
     * It retrieves the clerk description entered on add new popup walkin setting on cash and carry page
     *
     * @return If the clerk description entered is exist it returns the value of clerk description entered, otherwise it returns null string
     * @Description: This function highlights the clerk description textbox, and then retrieves the clerk description entered on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public String getEnteredClerkDescriptiononAddnewPopup() {
        return getElementAttribute(ClerkDescTextBox, "Clerk Description textbox field on add new popup on cash and carry page");
    }

    /**
     * Enters Printer Name on add new popup walkin setting on cash and carry page
     *
     * @param printerName Provided Printer Name to be entered
     * @Description: This function highlights & clicks the printer name textbox, and then enters Printer Name on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public void EnterPrinterNameonAddnewPopup(String printerName) {
        ClickAndType(PrinterNameTextBox, printerName, "Printer name textbox field on add new popup on cash and carry page");
    }

    /**
     * It retrieves the printer name entered on add new popup walkin setting on cash and carry page
     *
     * @return It retrieves the printer name entered on add new popup walkin setting on cash and carry page
     * @Description: This function highlights the printer name textbox, and then retrieves the printer name entered on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public String getEnteredPrinterNameonAddnewPopup() {
        return getElementAttribute(PrinterNameTextBox, "Printer name textbox field on add new popup on cash and carry page");
    }

    /**
     * Selects Manufacturer on add new popup walkin setting on cash and carry page
     *
     * @param manufacturer Provided Manufacturer to be selected
     * @Description: This function highlights the manufacturer dropdown, and then selects the manufacturer on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public void SelectManufacturerOnAddNewPopup(String manufacturer) {
        drop_Down(ManufacturerDropDown, manufacturer, "VisibleText", "Manufacturer dropdown field on add new popup on cash and carry page");
    }

    /**
     * It retrieves the selected manufacturer option on add new popup walkin setting on cash and carry page
     *
     * @return If the selected manufacturer option is exist it returns the value of selected manufacturer option, otherwise it returns null string
     * @Description: This function highlights the manufacturer dropdown, and then retrieves the selected manufacturer option on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public String getFirstSelectedManufacturerOptionOnAddNewPopup() {
        return get_selected_option(ManufacturerDropDown, "Manufacturer dropdown field on add new popup on cash and carry page");
    }

    /**
     * Selects Model on add new popup walkin setting on cash and carry page
     *
     * @param model Provided Model to be selected
     * @Description: This function highlights the model dropdown, and then selects the model on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public void SelectModelOnAddNewPopup(String model) {
        drop_Down(ModelDropDown, model, "VisibleText", "Model dropdown field on add new popup on cash and carry page");
    }

    /**
     * It retrieves the selected model option on add new popup walkin setting on cash and carry page
     *
     * @return If the selected model option is exist it returns the value of selected model option, otherwise it returns null string
     * @Description: This function highlights the model dropdown, and then retrieves the selected model option on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public String getFirstSelectedModelOptionOnAddNewPopup() {
        return get_selected_option(ModelDropDown, "Model dropdown field on add new popup on cash and carry page");
    }

    /**
     * It retrieves the cash draw code entered on add new popup walkin setting on cash and carry page
     *
     * @return If the cash draw code entered is exist it returns the value of cash draw code entered, otherwise it returns null string
     * @Description: This function highlights the cash draw code textbox, and then retrieves the cash draw code entered on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public String getCashdrawcodeOnAddnewPopup() {
        return getElementAttribute(CashDrawCodeTextBox, "Cash draw code textbox field on add new popup on cash and carry page");
    }

    /**
     * It retrieves the printer cut code entered on add new popup walkin setting on cash and carry page
     *
     * @return If the printer cut code entered is exist it returns the value of printer cut code entered, otherwise it returns null string
     * @Description: This function highlights the printer cut code textbox, and then retrieves the printer cut code entered on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public String getPrinterCutCodeOnAddnewPopup() {
        return getElementAttribute(PrinterCutCodeTextBox, "Printer cut code textbox field on add new popup on cash and carry page");
    }

    /**
     * Clicks on Customer copy checkbox on add new popup walkin setting on cash and carry page
     *
     * @Description: This function highlights the customer copy checkbox, and then clicks on customer copy checkbox on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public void ClickCustomerCopyCheckboxOnAddnewPopup() {
        Click(OpenEdgeCustomerCopyCheckBox, "Customer copy checkbox on add new popup walkin setting on cash and carry page");
    }

    /**
     * Clicks on Merchant copy checkbox on add new popup walkin setting on cash and carry page
     *
     * @Description: This function highlights the merchant copy checkbox, and then clicks on merchant copy checkbox on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public void ClickMerchantCopyCheckboxOnAddnewPopup() {
        Click(OpenEdgeMerchantCopyCheckBox, "Open Edge Merchant Copy CheckBox on add new popup walkin setting on cash and carry page");
    }

    /**
     * It checks the customer copy checkbox on add new popup walkin setting on cash and carry page
     *
     * @return If the customer copy checkbox is selected it returns true, otherwise it returns false
     * @Description: This function highlights the customer copy checkbox, and then checks the customer copy checkbox on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public boolean custCopyCheckBoxonAddNewpopUPIsSelected() {
        return OpenEdgeCustomerCopyCheckBox.isSelected();
    }

    /**
     * It checks the merchant copy checkbox on add new popup walkin setting on cash and carry page
     *
     * @return If the merchant copy checkbox is selected it returns true, otherwise it returns false
     * @Description: This function highlights the merchant copy checkbox, and then checks the merchant copy checkbox on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public boolean merchantCopyCheckBoxonAddNewpopUPIsSelected() {
        return OpenEdgeMerchantCopyCheckBox.isSelected();
    }

    /**
     * Enters the customer copy note on add new popup walkin setting on cash and carry page
     *
     * @param custCopyNote Provided customer copy note to be entered
     * @Description: This function highlights & Clicks the customer copy note textbox, and then enters the customer copy note on add new popup walkin setting on cash and carry page
     */
    public void EnterCustCopyNoteonAddnewPopup(String custCopyNote) {
        ClickAndType(CustomerCopyNoteTextBox, custCopyNote, "Customer Copy Note textbox field");
    }

    /**
     * Enters the merchant copy note on add new popup walkin setting on cash and carry page
     *
     * @param merchantCopyNote Provided merchant copy note to be entered
     * @Description: This function highlights & Clicks the merchant copy note textbox, and then enters the merchant copy note on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public void EnterMerchantCopyNoteonAddnewPopup(String merchantCopyNote) {
        ClickAndType(MerchantCopyNoteTextBox, merchantCopyNote, "Merchant Copy Note textbox field");
    }

    /**
     * It retrieves the entered customer copy note on add new popup walkin setting on cash and carry page
     *
     * @return If the customer copy note entered is exist it returns the value of customer copy note entered, otherwise it returns null string
     * @Description: This function highlights the customer copy note textbox, and then retrieves the customer copy note entered on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public String getEnteredCustCopyNoteonAddnewPopup() {
        return getElementAttribute(CustomerCopyNoteTextBox, "Customer copy note - on add new walkin setting popup on cash and carry page");
    }

    /**
     * It retrieves the entered merchant copy note on add new popup walkin setting on cash and carry page
     *
     * @return If the merchant copy note entered is exist it returns the value of merchant copy note entered, otherwise it returns null string
     * @Description: This function highlights the merchant copy note textbox, and then retrieves the merchant copy note entered on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public String getEnteredMerchantCopyNoteonAddnewPopup() {
        return get_element_attribute(MerchantCopyNoteTextBox, "Merchant copy note - on add new walkin setting popup on cash and carry page");
    }

    /**
     * Clicks on Cancel button on add new popup walkin setting on cash and carry page
     *
     * @Description: This function highlights the Cancel button, and then clicks on Cancel button on add new popup walkin setting on cash and carry page using js_Click method
     * @Author Balaji N
     */
    public void ClickCancelBtnonAddnewPopup() {
        js_Click(CancelWalkInSettingButton, "Cancel button - on add new walkin setting popup on cash and carry page");
    }

    /**
     * Clicks on Save button on add new popup walkin setting on cash and carry page
     *
     * @Description: This function highlights the Save button, and then clicks on Save button on add new popup walkin setting on cash and carry page using js_Click method
     * @Author Balaji N
     */
    public void ClickSaveBtnonAddnewPopup() {
        js_Click(SaveWalkInSettingButton, "save button - on add new walkin setting popup on cash and carry page");
    }

    /**
     * Validates whether success message is displayed on add new popup walkin setting on cash and carry page
     *
     * @return If the success message is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the success message, and then validates whether success message is displayed on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public boolean VerifySuccessMessageIsDisplayed() {
        return is_Element_Displayed(successToastMessageOnAddNewWalkInSetting, "Success message - on add new walkin setting popup on cash and carry page");
    }

    public String getSuccessMessageText() {
        return getElementText(successToastMessageOnAddNewWalkInSetting, "Success message on cash and carry page");
    }

    /**
     * Validates whether newly created clerk id is displayed on add new popup walkin setting on cash and carry page
     *
     * @return If the newly created clerk id is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the newly created clerk id, and then validates whether newly created clerk id is displayed on add new popup walkin setting on cash and carry page
     * @Author Balaji N
     */
    public boolean VerifyWalkingSettingNewlyAdded() {
        return is_Element_Displayed(NewlyCreatedClerkIdWalkInSetting, "Newly created clerk id - on add new walkin setting popup on cash and carry page");
    }

    /**
     * Clicks on newly created clerk edit button on add new popup walkin setting on cash and carry page
     *
     * @Description: This function highlights the newly created clerk edit button, and then clicks on newly created clerk edit button on add new popup walkin setting on cash and carry page using js_Click method
     * @Author Balaji N
     */
    public void ClickNewlyCreatedEditBtnOnWalkInSetting(String newlyCreatedClerkId) {
        WebElement edit_icon = getDriver().findElement(By.xpath("(//td[contains(text(),'" + newlyCreatedClerkId + "')]/following-sibling::td//a[@id='btnEditWalkInSetting'])[1]"));
        js_Click(edit_icon, "Newly created clerk edit button - on Walkin Setting Popup");
    }

    /**
     * Clicks on newly created clerk delete button on add new popup walkin setting on cash and carry page
     *
     * @Description This function highlights the newly created clerk delete button, and then clicks on newly created clerk delete button on add new popup walkin setting on cash and carry page using js_Click method
     * @Author Balaji N
     */
    public void ClickAutomationClerkDescEditBtnOnWalkInSetting() {
        js_Click(Automation_Clerk_DescEditWalkInSettingButton, "Automation Clerk Desc Edit button - on Walkin Setting Popup");
    }

    /**
     * Clicks on cash registry unchecked edit button walkin setting on cash and carry page
     *
     * @Description: This function highlights the cash registry unchecked edit button, and then clicks on cash registry unchecked edit button walkin setting on cash and carry page using js_Click method
     * @Author Balaji N
     */
    public void ClickCashRegistryUncheckedEditBtnOnWalkInSetting() {
        js_Click(CashRegisterUncheckedFTEditWalkInSettingButton, "Cash Registry Unchecked Edit Walkin Setting Button");
    }

    /**
     * Retrieves the edit button tooltip on walkin setting on cash and carry page
     *
     * @return If the edit button tooltip is exist it returns the value of edit button tooltip, otherwise it returns null string
     * @Description: This function Mouse hovers on edit button, and then retrieves the edit button tooltip on walkin setting on cash and carry page
     * @Author Balaji N
     */
    public String geteditBtnToolTiponAddnewPopupIsDisplayed() {
        MouseHover(Automation_Clerk_description_NewlyCreatedEditWalkInSettingButton);
        delayWithGivenTime(1000);
        return Automation_Clerk_description_NewlyCreatedEditWalkInSettingButton.getAttribute("title");
    }

    /**
     * Retrieves the delete button tooltip on walkin setting on cash and carry page
     *
     * @return If the delete button tooltip is exist it returns the value of delete button tooltip, otherwise it returns null string
     * @Description: This function Mouse hovers on delete button, and then retrieves the delete button tooltip on walkin setting on cash and carry page
     * @Author Balaji N
     */
    public String getDeleteBtnToolTiponAddnewPopupIsDisplayed() {
        MouseHover(NewlyCreatedDeleteWalkInSettingButton);
        delayWithGivenTime(1000);
        return NewlyCreatedDeleteWalkInSettingButton.getAttribute("title");
    }

    /**
     * Clicks on edit button 1 on walkin setting on cash and carry page
     *
     * @Description: This function highlights the edit button 1, and then clicks on edit button 1 on walkin setting on cash and carry page using js_Click method
     * @Author Balaji N
     */
    public void ClickAutomationEditBtnOnWalkInSetting() {
        fluentWait(AutomationEditWalkInSettingButton1);
        js_Click(AutomationEditWalkInSettingButton1, "Automation Edit button - on Walkin Setting Popup");
    }

    /**
     * Clicks on automation dont delete edit button on walkin setting in cash and carry page
     *
     * @Description This function highlights the automation dont delete edit button, and then clicks on automation dont delete edit button on walkin setting in cash and carry page using js_Click method
     * @Author Balaji N
     */
    public void ClickAutomationDontDeleteEditBtnOnWalkInSetting() {
        fluentWait(Automation_DontDeletetEditWalkInSettingButton);
        js_Click(Automation_DontDeletetEditWalkInSettingButton, "Automation Dont Delete Edit Button in Walkin Setting popup");
    }

    /**
     * Clicks on cash registry edit button on walkin setting in cash and carry page
     *
     * @Description This function highlights the cash registry edit button, and then clicks on cash registry edit button on walkin setting in cash and carry page using js_Click method
     * @Author Balaji N
     */
    public void ClickCashRegistery2EditBtnOnWalkInSetting() {
        fluentWait(CashRegister2EditWalkInSettingButton1);
        js_Click(CashRegister2EditWalkInSettingButton1);
    }

    /**
     * Clicks on edit button 1 on walkin setting in cash and carry page
     *
     * @Description: This function highlights the edit button 1, and then clicks on edit button 1 on walkin setting in cash and carry page using js_Click method
     * @Author Balaji N
     */
    public void ClickEditBtnOnWalkInSetting() {
        fluentWait(Hana_Testing_DescriptionEditWalkInSettingButton1);
        js_Click(Hana_Testing_DescriptionEditWalkInSettingButton1);
    }

    /**
     * Clicks on save button on walkin setting in cash and carry page
     *
     * @Description: This function highlights the save button, and then clicks on save button on walkin setting in cash and carry page using js_Click method
     * @Author Balaji N
     */
    public void ClickSaveBtnOnAddNewPopup() {
        js_Click(SaveWalkInSettingButton);
    }

    /**
     * It retrieves the updated printer name on walkin setting in cash and carry page
     *
     * @return If the updated printer name is exist it returns the value of updated printer name, otherwise it returns null string
     * @Description: This function highlights the updated printer name, and then retrieves the updated printer name on walkin setting in cash and carry page
     */
    public String VerifyUpdatedPrinterName() {
        int p;
        for (p = 0; p < listOfPrinterName.size(); ) {
            if (listOfPrinterName.get(p).getText().equals("Saka Printer")) {
                listOfPrinterName.get(p).getText();
                p++;
                break;
            }
            break;
        }
        return listOfPrinterName.get(p).getText();
    }

    /**
     * Clicks on delete button 1 on walkin setting in cash and carry page
     *
     * @Description: This function highlights the delete button 1, and then clicks on delete button 1 on walkin setting in cash and carry page using js_Click method
     * @Author Balaji N
     */
    public void ClickDeleteBtnOnParticularWalkInSetting(String newlycreatedclerkid) {
        WebElement delete_icon = getDriver().findElement(By.xpath("//td[contains(text(),'" + newlycreatedclerkid + "')]/following-sibling::td//a[@class='m-r-50 CashRegisterInactive']"));
        js_Click(delete_icon, "Automation Delete button - on Walkin Setting Popup");
    }

    /**
     * Validate whether delete confirmation popup is displayed on walkin setting in cash and carry page
     *
     * @return If the delete confirmation popup is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the delete confirmation popup, and then validates whether delete confirmation popup is displayed on walkin setting in cash and carry page
     */
    public boolean verifyDeleteConfirmationPopupIsDisplayed() {
        return is_Element_Displayed(DeleteConfirmationPopUpInWalkinSetting, "Delete Confirmation Popup in Walkin Setting");
    }

    /**
     * Validate whether delete confirmation popup text is displayed on walkin setting in cash and carry page
     *
     * @return If the delete confirmation popup text is displayed it returns the text, otherwise it returns null string
     * @Description: This function highlights the delete confirmation popup text, and then validates whether delete confirmation popup text is displayed on walkin setting in cash and carry page
     * @Author Balaji N
     */
    public String verifyDeleteConfirmationPopuText() {
        return getElementText(AreYouSureYouWishToWalkinSettingText, "Delete Confirmation Popup Text in Walkin Setting");
    }

    /**
     * Validate whether confirmation code text box is displayed on walkin setting in cash and carry page
     *
     * @return If the confirmation code text box is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the confirmation code text box, and then validates whether confirmation code text box is displayed on walkin setting in cash and carry page
     * @Author Balaji N
     */
    public boolean confirmationcodeTextBoxIsDisplayed() {
        return is_Element_Displayed(ConfirmationCodeTextBox, "Confirmation Code Text Box in Walkin Setting");
    }

    /**
     * Validate whether confirmation code placeholder text is displayed on walkin setting in cash and carry page
     *
     * @return If the confirmation code placeholder text is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the confirmation code placeholder text, and then validates whether confirmation code placeholder text is displayed on walkin setting in cash and carry page
     * @Author Balaji N
     */
    public String VerifyconfirmationCodePlaceholder() {
        return ConfirmationCodeTextBox.getAttribute("placeholder");
    }

    /**
     * Validate whether Cancel button in delete confirmation popup is displayed on walkin setting in cash and carry page
     *
     * @return If the Cancel button in delete confirmation popup is displayed it returns true, otherwise it returns false
     * @Description : This function highlights the Cancel button in delete confirmation popup, and then validates whether Cancel button in delete confirmation popup is displayed on walkin setting in cash and carry page
     * @Author Balaji N
     */
    public boolean CancelBtnInDeleteConfirmationPopupIsDisplayed() {
        return is_Element_Displayed(CancelButtonInDeleteConfirmationPopUp, "Cancel Button in Delete Confirmation Popup");
    }

    /**
     * Validate whether Ok button in delete confirmation popup is displayed on walkin setting in cash and carry page
     *
     * @return If the Ok button in delete confirmation popup is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the Ok button in delete confirmation popup, and then validates whether Ok button in delete confirmation popup is displayed on walkin setting in cash and carry page
     * @Author Balaji N
     */
    public boolean OkBtnInDeleteConfirmationPopup() {
        /*HighlightElement(OKButtonInDeleteConfirmationPopUp);
        return OKButtonInDeleteConfirmationPopUp.isDisplayed();*/
        return is_Element_Displayed(OKButtonInDeleteConfirmationPopUp, "Ok button in delete confirmation popup");
    }

    /**
     * Clicks on Cancel button in delete confirmation popup on walkin setting in cash and carry page
     *
     * @Description: This function highlights the Cancel button in delete confirmation popup, and then clicks on Cancel button in delete confirmation popup on walkin setting in cash and carry page
     * @Author Balaji N
     */
    public void ClickCancelBtnInDeleteConfirmationPopup() {
        js_Click(CancelButtonInDeleteConfirmationPopUp, "Cancel Button in Delete Confirmation Popup");
    }

    /**
     * Enter confirmation code in delete confirmation popup on walkin setting in cash and carry page
     *
     * @param confirmationCode confirmation code to be entered
     * @Description: This function highlights the confirmation code text box, and then enters the confirmation code in delete confirmation popup on walkin setting in cash and carry page
     * @Author Balaji N
     */
    public void EnterConfirmationCodeInDeleteConfirmationPopup(String confirmationCode) {
        ClickAndType(ConfirmationCodeTextBox, confirmationCode, "confirmation code text");
    }

    /**
     * Clicks on Ok button in delete confirmation popup on walkin setting in cash and carry page
     *
     * @Description: This function highlights the Ok button in delete confirmation popup, and then clicks on Ok button in delete confirmation popup on walkin setting in cash and carry page
     * @Author Balaji N
     */
    public void ClickOkBtnInDeleteConfirmationPopup() {
        js_Click(OKButtonInDeleteConfirmationPopUp, "Ok button in delete confirmation popup");
    }

    /**
     * It retrieves the invalid delete confirmation code text on walkin setting in cash and carry page
     *
     * @return If the invalid delete confirmation code text is displayed it returns the text, otherwise it returns null string
     * @Description: This function highlights the invalid delete confirmation code text, and then retrieves the invalid delete confirmation code text on walkin setting in cash and carry page
     * @Author Balaji N
     */
    public String validateInvalidDeleteConfirmationCode() {
        delayWithGivenTime(2000);
        return getElementText(IncorrectConfirmationCodeText, "Invalid Delete Confirmation Code");
    }

    /**
     * Validate whether invalid delete confirmation code cross icon is displayed on walkin setting in cash and carry page
     *
     * @return If the invalid delete confirmation code cross icon is displayed it returns true, otherwise it returns false
     * @Description : This function highlights the invalid delete confirmation code cross icon, and then validates whether invalid delete confirmation code cross icon is displayed on walkin setting in cash and carry page
     * @Author Balaji N
     */
    public boolean validateInvalidDeleteConfirmationCodeCrossIcon() {
        return is_Element_Displayed(IncorrectDeleteConfirmationCodeErrorIcon, "Incorrect Delete Confirmation Code Error Icon");
    }

    /**
     * It retrieves the delete confirmation code on walkin setting in cash and carry page
     *
     * @return If the delete confirmation code is displayed it returns the text, otherwise it returns null string
     * @Description: This function highlights the delete confirmation code, and then retrieves the delete confirmation code on walkin setting in cash and carry page
     * @Author Balaji N
     */
    public String getDeleteConfirmationCode() {
        String confirmationCode = AreYouSureYouWishToWalkinSettingText.getText();
        String[] ExtractedconfirmationCode = confirmationCode.split("Type")[1].trim().split(" ");
        return ExtractedconfirmationCode[0];
    }

    /**
     * Enter delete confirmation code on walkin setting in cash and carry page
     *
     * @Description: This function highlights the delete confirmation code text box, and then enters the delete confirmation code on walkin setting in cash and carry page. "getDeleteConfirmationCode()" method used as confirmation code to be entered
     * @Author Balaji N
     */
    public void EnterDeleteConfirmationCode(String confirmationCode) {
        ConfirmationCodeTextBox.click();
        delayWithGivenTime(1000);
        ConfirmationCodeTextBox.clear();
        delayWithGivenTime(1000);
        js_Clear_And_Type(ConfirmationCodeTextBox, confirmationCode, "confirmation code textbox field");
    }

    /**
     * Retrieves the added item code on row 1 of product table grid on the cash and carry page
     *
     * @return If the added item code is displayed on row 1 of the cash and carry page it returns the value of added item code, otherwise it returns null string
     * @Description: This function highlights the added item code, and then retrieves the added item code on row 1 of the cash and carry page
     * @Author Balaji N
     */
    public String GetAddedItemCode() {
        return AddedItemTableRow1ItemCode.getText();
    }

    /**
     * Retrieves the added item code on row 2 of product table grid on the cash and carry page
     *
     * @return If the added item code is displayed on row 2 of the cash and carry page it returns the value of added item code, otherwise it returns null string
     * @Description: This function highlights the added item code, and then retrieves the added item code on row 2 of the cash and carry page
     * @Author Balaji N
     */
    public String GetAddedRow2ItemCode() {
        return AddedItemTableRow2ItemCode.getText();
    }

    /**
     * Retrieves the added item description on row 1 of product table grid on the cash and carry page
     *
     * @return If the added item description is displayed on row 1 of the cash and carry page it returns the value of added item description, otherwise it returns null string
     * @Description: This function highlights the added item description, and then retrieves the added item description on row 1 of the cash and carry page
     * @Author Balaji N
     */
    public String GetAddedItemDescription() {
        return getElementText(AddedItemTableRow1Description, "Added item description");
    }

    /**
     * Retrieves the added item description on row 1 of product table grid on the cash and carry page
     *
     * @return If the added item description is displayed on row 1 of the cash and carry page it returns the value of added item description, otherwise it returns null string
     * @Description: This function highlights the added item description, and then retrieves the added item description on row 1 of the cash and carry page
     * @Author Balaji N
     */
    public String GetAddedItemDescription2() {
        return getElementText(AddedItemTableRow1Description, "Added item description");
    }

    /**
     * Retrieves the added item description on row 2 of product table grid on the cash and carry page
     *
     * @return If the added item description is displayed on row 2 of the cash and carry page it returns the value of added item description, otherwise it returns null string
     * @Description: This function highlights the added item description, and then retrieves the added item description on row 2 of the cash and carry page
     * @Author Balaji N
     */
    public String GetAddedItemDescriptionRow2() {
        return AddedItemTableRow2Description.getText();
    }

    /**
     * Retrieves the added item quantity on row 1 of product table grid on the cash and carry page
     *
     * @return If the added item quantity is displayed on row 1 of the cash and carry page it returns the value of added item quantity, otherwise it returns null string
     * @Description: This function highlights the added item quantity, and then retrieves the added item quantity on row 1 of the cash and carry page
     * @Author Balaji N
     */
    public String GetAddedItemQty() {
        return getElementText(AddedItemTableRow1Quantity, "Added item quantity");
    }

    /**
     * Retrieves the added item quantity on row 2 of product table grid on the cash and carry page
     *
     * @return If the added item quantity is displayed on row 2 of the cash and carry page it returns the value of added item quantity, otherwise it returns null string
     * @Description: This function highlights the added item quantity, and then retrieves the added item quantity on row 2 of the cash and carry page
     * @Author Balaji N
     */
    public String GetAddedItemQtyRow2() {
        return AddedItemTableRow2Quantity.getText();
    }

    /**
     * Retrieves the added item price on row 1 of product table grid on the cash and carry page
     *
     * @return If the added item price is displayed on row 1 of the cash and carry page it returns the value of added item price, otherwise it returns null string
     * @Description: This function highlights the added item price, and then retrieves the added item price on row 1 of the cash and carry page
     * @Author Balaji N
     */
    public String GetAddedItemPrice() {
        return getElementText(AddedItemTableRow1Price, "Added item price at Row1");
    }

    /**
     * Retrieves the added item extended price on row 1 of product table grid on the cash and carry page
     *
     * @return If the added item extended price is displayed on row 1 of the cash and carry page it returns the value of added item extended price, otherwise it returns null string
     * @Description: This function highlights the added item extended price, and then retrieves the added item extended price on row 1 of the cash and carry page
     * @Author Balaji N
     */
    public String GetAddedItemExtPrice() {
        return getElementText(AddedItemTableRow1ExtPrice, "Added item extended price");
    }

    /**
     * Retrieves the added item discount amount on row 1 of product table grid on the cash and carry page
     *
     * @return If the added item discount amount is displayed on row 1 of the cash and carry page it returns the value of added item discount amount, otherwise it returns null string
     * @Description: This function highlights the added item discount amount, and then retrieves the added item discount amount on row 1 of the cash and carry page
     * @Author Balaji N
     */
    public String GetAddedItemDiscountAmount() {
        return getElementText(AddedItemTableRow1DiscountAmount, "Added item discount amount");
    }

    /**
     * Retrieves the added item discount percentage on row 1 of product table grid on the cash and carry page
     *
     * @return If the added item discount percentage is displayed on row 1 of the cash and carry page it returns the value of added item discount percentage, otherwise it returns null string
     * @Description: This function highlights the added item discount percentage, and then retrieves the added item discount percentage on row 1 of the cash and carry page
     * @Author Balaji N
     */
    public String GetAddedItemDiscountPercentage() {
        return getElementText(AddedItemTableRow1DiscountPercentage, "Added item discount percentage");
    }

    /**
     * Validates whether the pay button is disabled or not (Vice-versa used)
     *
     * @return If the pay button is disabled on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the pay button, and then validates whether the pay button is disabled or not
     * @Author Balaji N
     */
    public boolean IsPayButtonDisabled() {
        HighlightElement(PayBtn);
        String classAttribute = PayBtn.getAttribute("class");
        boolean isDisabledClassPresent = classAttribute.contains("BoldText disabled");
        return isDisabledClassPresent;
    }

    /**
     * Validates whether the pay button is enabled or not (Vice-versa used)
     *
     * @return If the pay button is enabled on cash and carry page it returns true, otherwise it returns false
     * @Description: This function highlights the pay button, and then validates whether the pay button is enabled or not
     * @Author Balaji N
     */
    public boolean VerifyPayButtonIsEnabled() {
        HighlightElement(PayBtn);
        boolean isDisabled = PayBtn.getAttribute("class").contains("disabled");
        return isDisabled;
    }

    /**
     * It retrieves the displayed item description on row 1 of product table grid on the cash and carry page
     *
     * @return If the added item description is displayed on row 1 of the cash and carry page it returns the value of added item description, otherwise it returns null string
     * @Description: This function highlights the added item description, and then retrieves the added item description on row 1 of the cash and carry page
     * @Author Balaji N
     */
    public String getDisplayedItemDescription() {
        return getElementText(AddedItemDescriptioncol1, "Row 1 added item description");
    }

    /**
     * It retrieves the displayed item quantity on row 1 of product table grid on the cash and carry page
     *
     * @return If the added item quantity is displayed on row 1 of the cash and carry page it returns the value of added item quantity, otherwise it returns null string
     * @Description: This function highlights the added item quantity, and then retrieves the added item quantity on row 1 of the cash and carry page
     * @Author Balaji N
     */
    public String getDisplayedItemQty() {
        return getElementText(AddedItemQuantitycol1, "Row 1 added item quantity");
    }

    /**
     * It retrieves the displayed item price on row 1 of product table grid on the cash and carry page
     *
     * @return If the added item price is displayed on row 1 of the cash and carry page it returns the value of added item price, otherwise it returns null string
     * @Description: This function highlights the added item price, and then retrieves the added item price on row 1 of the cash and carry page
     * @Author Balaji N
     */
    public String getDisplayedItemPrice() {
        return getElementText(AddedItemPricecol1, "Row 1 added item price");
    }

    /**
     * It retrieves the displayed item extended price on row 1 of product table grid on the cash and carry page
     *
     * @return If the added item extended price is displayed on row 1 of the cash and carry page it returns the value of added item extended price, otherwise it returns null string
     * @Description: This function highlights the added item extended price, and then retrieves the added item extended price on row 1 of the cash and carry page
     * @Author Balaji N
     */
    public String getDisplayedItemExtPrice() {
        return getElementText(AddedItemExtendedPricecol1, "Row 1 added item extended price");
    }

    /**
     * It retrieves the displayed item discount amount on row 1 of product table grid on the cash and carry page
     *
     * @return If the added item discount amount is displayed on row 1 of the cash and carry page it returns the value of added item discount amount, otherwise it returns null string
     * @Description: This function highlights the added item discount amount row1, and then retrieves the added item discount amount on row 1 of the cash and carry page
     * @Author Balaji N
     */
    public String getDisplayedItemDiscountAmount() {
        return getElementText(AddedItemDiscountAmountcol1, "Row 1 added item discount amount");
    }

    /**
     * It retrieves the displayed item discount percentage on row 1 of product table grid on the cash and carry page
     *
     * @return If the added item discount percentage is displayed on row 1 of the cash and carry page it returns the value of added item discount percentage, otherwise it returns null string
     * @Description: This function highlights the added item discount percentage row1, and then retrieves the added item discount percentage on row 1 of the cash and carry page
     * @Author Balaji N
     */
    public String getDisplayedItemDiscountPercentage() {
        HighlightElement(AddedItemDiscountPercentagecol1);
        return AddedItemDiscountPercentagecol1.getText();
    }

    /**
     * Clicks the edit button on row 1 of the added item table grid on cash and carry page
     *
     * @Description: This function clicks the edit button on row 1 of the added item table grid on cash and carry page
     * @Author Balaji N
     */
    public void ClickAddedRow1EditButton() {
        Click(EditAddedItemTableRow1Action, "Edit button on row 1 of the added item table grid on cash and carry page");
    }

    /**
     * Verify that the saved icon is displayed on row 1 of the added item table grid on cash and carry page
     *
     * @return If the saved icon is displayed on row 1 of the added item table grid on cash and carry page it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean IsSavedIconDisplayedOnAddedItemTableRow1() {
        return is_Element_Displayed(SavedAddedItemTableRow1Action, "Saved icon on row 1 of the added item table grid on cash and carry page");
    }

    /**
     * Verify that the cancel icon is displayed on row 1 of the added item table grid on cash and carry page
     *
     * @return If the cancel icon is displayed on row 1 of the added item table grid on cash and carry page it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean IsCancelIconDisplayedOnAddedItemTableRow1() {
        return is_Element_Displayed(CancelAddedItemTableRow1Action, "Cancel icon on row 1 of the added item table grid on cash and carry page");
    }

    /**
     * Clicks the cancel icon on row 1 of the added item table grid on cash and carry page
     *
     * @Author Balaji N
     */
    public void ClickTheCancelIconOnAddedItemTableRow1() {
        Click(CancelAddedItemTableRow1Action, "Cancel button on row 1 of the added item table grid on cash and carry page");
    }

    /**
     * Clicks the saved icon on row 1 of the added item table grid on cash and carry page
     *
     * @Author Balaji N
     */
    public void ClickTheSavedIconOnAddedItemTableRow1() {
        Click(SavedAddedItemTableRow1Action, "Saved button on row 1 of the added item table grid on cash and carry page");
    }

    /**
     * It edits the quantity on row 1 of product table grid on the cash and carry page
     *
     * @param QTY
     * @Author Balaji N
     */
    public void EditQuantityOnTableRow1(String QTY) {
        Actions actions = new Actions(getDriver());
        explicitWait(EditedQtyRow1Textbox);
        actions.doubleClick(EditedQtyRow1Textbox).build().perform();
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].value='" + QTY + "'", EditedQtyRow1Textbox);
        delayWithGivenTime(1000);
        EditedPriceRow1Textbox.click();
    }

    /**
     * It gets the quantity on row 1 of product table grid on the cash and carry page
     *
     * @return If the quantity is displayed on row 1 of the cash and carry page it returns the value of quantity, otherwise it returns null string
     * @Author Balaji N
     */
    public String getQuantity_on_row1() {
        return getElementAttribute(EditedQtyRow1Textbox, "Quantity on row 1");
    }

    /**
     * Clicks on the delete icon on row 2 of the added item table grid on cash and carry page
     *
     * @Author Balaji N
     */
    public void ClickOnDeleteIconAtRow2() {
        Click(DeleteAddedItemTableRow2, "Delete icon at row 2 - Table grid cash and carry page");
    }

    public boolean IsRow2TableAddedItemDisplayed() {
        List<WebElement> rows = AddedItemTable.findElements(By.tagName("tr"));
        boolean isDataDeleted = true;
        for (WebElement row : rows) {
            if (row.getText().contains("Red Rose Deluxe")) { // Replace "data to delete" with the actual data
                isDataDeleted = false;
                break;
            }
        }
        return isDataDeleted;
    }

    public void ClickOnDeleteIconAtRow1() {
        Click(DeleteAddedItemTableRow1, "Delete icon at row 1 - Table grid cash and carry page");
    }

    public boolean IsRow1TableAddedItemDisplayed() {
        List<WebElement> rows = AddedItemTable.findElements(By.tagName("tr"));
        boolean isDataDeleted = true;
        for (WebElement row : rows) {
            if (row.getText().contains("ballonsYY")) { // Replace "data to delete" with the actual data
                isDataDeleted = false;
                break;
            }
        }
        return isDataDeleted;
    }

    /**
     * Selects the shop on choose page default values
     *
     * @param shop
     * @Author Balaji N
     */
    public void SelectShopOnChoosePageDefaultValues(String shop) {
        drop_Down(ShopChooseDefaultValuesDD, shop, "VisibleText", "Shop dropdown field on choose page default values on cash and carry page");
    }

    /**
     * Gets the shop on choose page default values
     *
     * @return If the shop is displayed on choose page default values it returns the value of shop, otherwise it returns null string
     * @Author Balaji N
     */
    public String getSelectShopOnCPDV() {
        return get_Selected_Option(ShopChooseDefaultValuesDD, "Shop dropdown field value - Cash and Carry Page");
    }

    /**
     * Selects the page name on choose page default values
     *
     * @param pagename
     * @Author Balaji N
     */
    public void SelectPageNameOnCPDV(String pagename) {
        drop_Down(PageNameChooseDefaultValuesDD, pagename, "VisibleText", "Page name dropdown field on choose page default values on cash and carry page");
    }

    /**
     * Gets the page name on choose page default values
     *
     * @return If the page name is displayed on choose page default values it returns the value of page name, otherwise it returns null string
     * @Author Balaji N
     */
    public String getFirstSelectedOptionOnPageNameDD() {
        return get_Selected_Option(PageNameChooseDefaultValuesDD, "Page name dropdown field value - Cash and Carry Page");
    }

    /**
     * It enters the bank name on bank name on choose page default values
     *
     * @param bankname
     * @Author Balaji N
     */
    public void EnterBankNameOnBankNameOnChooseDefaultValues(String bankname) {
        ClickAndType(BankNameTextBoxChooseDefaultValues, bankname, "Bank name textbox field on choose page default values on cash and carry page");
    }

    /**
     * Gets the entered bank name on bank name on choose page default values
     *
     * @return If the bank name is displayed on bank name on choose page default values it returns the value of bank name, otherwise it returns null string
     * @Author Balaji N
     */
    public String get_entered_bankNameOnBankNameOnChooseDefaultValues() {
        return getElementAttribute(BankNameTextBoxChooseDefaultValues, "Bank name on choose page default values");
    }

    /**
     * It enters the check no on check no on choose page default values
     *
     * @param checkno
     * @Author Balaji N
     */
    public void EnterCheckNoOnCheckNoOnChooseDefaultValues(String checkno) {
        ClickAndType(CheckNoTextBoxChooseDefaultValues, checkno, "Check no textbox field on choose page default values on cash and carry page");
    }

    /**
     * Gets the entered check no on check no on choose page default values
     *
     * @return
     * @Author Balaji N
     */
    public String getEnteredCheckNoOnCheckNoOnChooseDefaultValues() {
        return getElementAttribute(CheckNoTextBoxChooseDefaultValues, "Check no on choose page default values");
    }

    /**
     * It enters the name of check on name of check on choose page default values
     *
     * @param nameofcheck
     * @Author Balaji N
     */
    public void EnterNameofCheckOnChooseDefaultValues(String nameofcheck) {
        ClickAndType(NameOnCheckTextBoxChooseDefaultValues, nameofcheck, "Name of check textbox field on choose page default values on cash and carry page");
    }

    /**
     * Gets the entered name of check on name of check on choose page default values
     *
     * @return If the name of check is displayed on name of check on choose page default values it returns the value of name of check, otherwise it returns null string
     * @Author Balaji N
     */
    public String getEnteredNameofCheckOnChooseDefaultValues() {
        return getElementAttribute(NameOnCheckTextBoxChooseDefaultValues, "Name of check on choose page default values");
    }

    /**
     * It enters the quantity on quantity on choose page default values
     *
     * @param quantity
     * @Author Balaji N
     */
    public void EnterSplCharInQuantityOnChooseDefaultValues(String quantity) {
        Double_Click_And_Type(QuantityTextBoxChooseDefaultValues, quantity, "Quantity textbox field on choose page default values on cash and carry page");
    }

    /**
     * Gets the entered quantity on quantity on choose page default values
     *
     * @return If the quantity is displayed on quantity on choose page default values it returns the value of quantity, otherwise it returns null string
     * @Author Balaji N
     */
    public String getQtyOnChooseDefaultValues() {
        return getElementAttribute(QuantityTextBoxChooseDefaultValues, "Quantity on choose page default values");
    }

    public void ClearQuantityFieldOnChooseDefaultValues() {
        jsClear(QuantityTextBoxChooseDefaultValues);
    }

    public void EnterQuantityOnChooseDefaultValues(String quantity) {
        Double_Click_And_Type(QuantityTextBoxChooseDefaultValues, quantity, "Quantity textbox field on choose page default values on cash and carry page");
    }

    public String getEnteredDefaultQuantityOnChooseDefaultValue() {
        return QuantityTextBoxChooseDefaultValues.getAttribute("value");
    }

    /**
     * It selects the default occasion on choose page default values
     *
     * @param defaultoccasion
     * @Author Balaji N
     */
    public void SelectDefaultOccasionOnChooseDefaultValues(String defaultoccasion) {
        drop_Down(DefaultOccasionDD, defaultoccasion, "VisibleText", "Occasion dropdown field on choose page default values on cash and carry page");
    }

    /**
     * Gets the selected default occasion on choose page default values
     *
     * @return If the occasion is displayed on default occasion on choose page default values it returns the value of occasion, otherwise it returns null string
     * @Author Balaji N
     */
    public String getDefaultoccasionselected() {
        return get_Selected_Option(DefaultOccasionDD, "Occasion dropdown field on choose page default values on cash and carry page");
    }

    /**
     * Selects the default sales person on choose page default values
     *
     * @param defaultsalesperson
     * @Author Balaji N
     */
    public void SelectDefaultSalesPersonOnChooseDefaultValues(String defaultsalesperson) {
        drop_Down(SalesPersonDefaultValuesDD, defaultsalesperson, "VisibleText", "Sales person dropdown field on choose page default values on cash and carry page");
    }

    /**
     * Gets the selected default sales person on choose page default values
     *
     * @return If the sales person is displayed on default sales person on choose page default values it returns the value of sales person, otherwise it returns null string
     * @Author Balaji N
     */
    public String getSalesPersonSelected() {
        return get_Selected_Option(SalesPersonDefaultValuesDD, "Sales person dropdown field on choose page default values on cash and carry page");
    }

    /**
     * Selects the default source code on choose page default values
     *
     * @param defaultsourcecode
     * @Author Balaji N
     */
    public void SelectDefaultSourceCodeOnChooseDefaultValues(String defaultsourcecode) {
        drop_Down(SourceCodeDefaultValuesDD, defaultsourcecode, "VisibleText", "Source code dropdown field on choose page default values on cash and carry page");
    }

    /**
     * Gets the selected default source code on choose page default values
     *
     * @return If the source code is displayed on default source code on choose page default values it returns the value of source code, otherwise it returns null string
     * @Author Balaji N
     */
    public String getSelectedSourceCodeOnChooseDefaultValues() {
        return get_Selected_Option(SourceCodeDefaultValuesDD, "Source code dropdown field on choose page default values on cash and carry page");
    }

    /**
     * Clicks the update button on choose page default values
     *
     * @Author Balaji N
     */
    public void ClickUpdateBtnOnChooseDefaultValues() {
        js_Click(ChooseDefaultUpdateButton, "Update button field on choose page default values on cash and carry page");
    }

    /**
     * Clicks the close icon on choose page default values
     *
     * @Author Balaji N
     */
    public void ClickCloseIconOnChooseDefaultValuesPopup() {
        js_Click(CloseIconOnChooseDefaultValuesPopup, "Close Icon on choose page default values popup on cash and carry page");
    }

    /**
     * Gets the item quantity on product level
     *
     * @return If the item quantity is displayed on product level it returns the value of quantity, otherwise it returns null string
     * @Author Balaji N
     */
    public String getItemQtyOnProductLevel() {
        return getElementAttribute(ItemQuantity, "Item Quantity on product level");
    }

    /**
     * Clicks the gift card tile on cash and carry page
     *
     * @Author Balaji N
     */
    public void ClickGiftCardTile() {
        js_Click(GiftCardTile, "Gift Card tile on cash and carry page");
    }

    /**
     * This method is used to verify whether the Gift Card sale pop-up i.e., Select Gift Card Type popp is displayed
     *
     * @return : if popup is displayed it will return true else false.
     * @author : Sakrateesh R
     */
    public boolean VerifyGiftCardSale() {
        getDriver().switchTo().activeElement();
        Highlight_Element(selectgiftcardtype, "Select GiftCard Type popup");
        return selectgiftcardtype.isDisplayed();
    }

    /**
     * This method will return the Gift_Card_Type_Header text.
     *
     * @return
     */
    public String Get_Select_GiftCardType_Popup_Header() {
        return get_Element_Text(select_gift_card_type_header, "Select Gift Card Type Header");
    }

    public void set_DefaultDenomination() {
        js_Click(EditButtonOnGiftCardPopup);
        delayWithGivenTime(1000);
        EditGiftCardDenomination1TextBox.clear();
        EditGiftCardDenomination1TextBox.sendKeys("30");
        js_Click(EditGiftCardDenominationSaveButton);
    }

    public void ClickOnInstantDenomination(String instantdenomination) {
        for (WebElement ele : GiftCardPriceListButton) {
            if (ele.getText().equals(instantdenomination)) {
                js_Click(ele);
                break;
            }
        }

    }

    public String getGiftAmountValue() {
        return getElementAttribute(GiftCardAmountTextBox, "Amount textbox field on Enter Gift Card Details Popup");
    }

    public void EnterAmountOnGiftamtField(String giftamount) {
        GiftCardAmountTextBox.click();
        delayWithGivenTime(500);
        GiftCardAmountTextBox.sendKeys(Keys.BACK_SPACE);
        delayWithGivenTime(500);
        ClickAndType(GiftCardAmountTextBox, giftamount, "Amount textbox field on Enter Gift Card Details Popup");
        delayWithGivenTime(2000);
        GiftCardAmountTextBox.sendKeys(Keys.TAB);
    }


    public String getTotalGiftValue() {
        HighlightElement(GiftCardTotalPriceTextBox);
        return GiftCardTotalPriceTextBox.getAttribute("value");
    }

    public String VerifyTotalGiftValueTextboxIsDisabled() {
        HighlightElement(GiftCardTotalPriceTextBox);
        DoubleClick(GiftCardTotalPriceTextBox);
        GiftCardTotalPriceTextBox.sendKeys("20");
        delayWithGivenTime(2000);
        return GiftCardTotalPriceTextBox.getAttribute("value");
        // IsDisabled(GiftCardTotalPriceTextBox);
    }

    public void EnterProcessingFeesOnGiftSalePopup(String processingfee) {
        HighlightElement(ProcessingFeeTextBox);
        DoubleClick(ProcessingFeeTextBox);
        ProcessingFeeTextBox.sendKeys(processingfee);
        ProcessingFeeTextBox.sendKeys(Keys.TAB);
    }

    public String getGiftCardProcessingFee() {
        return ProcessingFeeTextBox.getAttribute("value");
    }

    public void EnterGiftCardNumber(String giftcardnumber) {
        clickAndType(GiftCardNumberTextBox, giftcardnumber);
    }

    public void ClearGiftCardNumber() {
        GiftCardNumberTextBox.clear();
    }

    public String getGiftCardNumber() {
        HighlightElement(GiftCardNumberTextBox);
        return GiftCardNumberTextBox.getAttribute("value");
    }

    public void SelectExistingGiftCardCustomer(String custname) {
        explicitWait(CustomerNameTextBox);
        HandleAutocomplete(CustomerNameTextBox, custname);
    }

    public void ClickGiftSalesPopupCloseIcon() {
        js_Click(CloseGiftCardPopup);
    }

    public void ClickSubmitBtnInGiftSalesPopup() {
        click(GiftCardSubmitButton);
    }

    public void ClickEditIconOnGiftCardPopup() {
        js_Click(EditButtonOnGiftCardPopup);
    }

    public boolean VerifyEditGiftCardDominationPopup() {
        HighlightElement(EditGiftCardDominationPopup);
        return EditGiftCardDominationPopup.isDisplayed();
    }

    public void EditDenomination1TextBox(String denomination1) {
        HighlightElement(EditGiftCardDenomination1TextBox);
        DoubleClickAndType(EditGiftCardDenomination1TextBox, denomination1);
    }

    public void ClickEditGiftCardDenominationPopupCancelButton() {
        click(EditGiftCardDenominationCancelButton);
    }

    public String getGiftCardPrice1Denomination() {
        HighlightElement(giftcarddenominationprice1);
        return giftcarddenominationprice1.getText();
    }

    public void EditDenomination2TextBox(String denomination2) {
        HighlightElement(EditGiftCardDenomination2TextBox);
        DoubleClickAndType(EditGiftCardDenomination2TextBox, denomination2);
    }

    public void EditDenomination3TextBox(String denomination3) {
        HighlightElement(EditGiftCardDenomination3TextBox);
        DoubleClickAndType(EditGiftCardDenomination3TextBox, denomination3);
    }

    public void EditDenomination4TextBox(String denomination4) {
        HighlightElement(EditGiftCardDenomination4TextBox);
        DoubleClickAndType(EditGiftCardDenomination4TextBox, denomination4);
    }

    public void EditDenomination5TextBox(String denomination5) {
        HighlightElement(EditGiftCardDenomination5TextBox);
        DoubleClickAndType(EditGiftCardDenomination5TextBox, denomination5);
    }

    public void EditDenomination6TextBox(String denomination6) {
        HighlightElement(EditGiftCardDenomination6TextBox);
        DoubleClickAndType(EditGiftCardDenomination6TextBox, denomination6);
    }

    public void EditDenomination7TextBox(String denomination7) {
        HighlightElement(EditGiftCardDenomination7TextBox);
        DoubleClickAndType(EditGiftCardDenomination7TextBox, denomination7);
    }

    public void EditDenomination8TextBox(String denomination8) {
        HighlightElement(EditGiftCardDenomination8TextBox);
        DoubleClickAndType(EditGiftCardDenomination8TextBox, denomination8);
    }

    public void EditDenomination9TextBox(String denomination9) {
        HighlightElement(EditGiftCardDenomination9TextBox);
        DoubleClickAndType(EditGiftCardDenomination9TextBox, denomination9);
    }

    public void EditDenomination10TextBox(String denomination10) {
        HighlightElement(EditGiftCardDenomination10TextBox);
        DoubleClickAndType(EditGiftCardDenomination10TextBox, denomination10);
    }

    public String getGiftCardPrice2Denomination() {
        HighlightElement(giftcarddenominationprice2);
        return giftcarddenominationprice2.getText();
    }

    public String getGiftCardPrice3Denomination() {
        HighlightElement(giftcarddenominationprice3);
        return giftcarddenominationprice3.getText();
    }

    public String getGiftCardPrice4Denomination() {
        HighlightElement(giftcarddenominationprice4);
        return giftcarddenominationprice4.getText();
    }

    public String getGiftCardPrice5Denomination() {
        HighlightElement(giftcarddenominationprice5);
        return giftcarddenominationprice5.getText();
    }

    public String getGiftCardPrice6Denomination() {
        HighlightElement(giftcarddenominationprice6);
        return giftcarddenominationprice6.getText();
    }

    public String getGiftCardPrice7Denomination() {
        HighlightElement(giftcarddenominationprice7);
        return giftcarddenominationprice7.getText();
    }

    public String getGiftCardPrice8Denomination() {
        HighlightElement(giftcarddenominationprice8);
        return giftcarddenominationprice8.getText();
    }

    public String getGiftCardPrice9Denomination() {
        HighlightElement(giftcarddenominationprice9);
        return giftcarddenominationprice9.getText();
    }

    public String getGiftCardPrice10Denomination() {
        HighlightElement(giftcarddenominationprice10);
        return giftcarddenominationprice10.getText();
    }

    public void ClickEditGiftCardDenominationPopupSaveButton() {
        click(EditGiftCardDenominationSaveButton);
    }

    public boolean ISErrorToastMessageDisplayed() {
        HighlightElement(ErrorToastMsg);
        return ErrorToastMsg.isDisplayed();
    }

    public void ClickCloseIconOnErrorToastMessage() {
        click(ErrorToastMsgCloseBtn);
    }

    /**
     * Clicks the add customer button on cash and carry page
     *
     * @Author Balaji N
     */
    public void ClickAddCustomerButton() {
        js_Click(AddCustomerButton, "Add customer button on cash and carry page");
    }

    /**
     * Verifies the add new customer popup on cash and carry page is displayed or not
     *
     * @return If the add new customer popup on cash and carry page is displayed it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean VerifyAddNewCustomerPopup() {
        return is_Element_Displayed(AddNewCustomerPopup, "Add new customer popup on cash and carry page");
    }

    /**
     * Verifies the add new customer popup on cash and carry page is closed or not
     *
     * @return If the add new customer popup on cash and carry page is closed it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean is_AddNewCustomer_Popup_Closed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
            return shortWait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(AddNewCustomerPopup)));
        } catch (TimeoutException e) {
            return false;
        }
    }


    /**
     * Enters the first name on add new customer popup on cash and carry page
     *
     * @param firstname
     * @Author Balaji N
     */
    public void EnterAddNewCustFirstName(String firstname) {
        Double_Click_And_Type(AddNewCustFirstNameTextbox, firstname, "First Name textbox field on add new customer popup on cash and carry page");
    }

    /**
     * It retrieves the first name entered on add new customer popup on cash and carry page
     *
     * @return If the first name is displayed it returns the displayed first name as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getEnteredaddnewcustfirstname() {
        return getElementAttribute(AddNewCustFirstNameTextbox, "First Name textbox field value on add new customer popup on cash and carry page");
    }

    public void EnterAddNewCustLastName(String lastname) {
        Double_Click_And_Type(AddNewCustLastNameTextbox, lastname, "Last Name textbox field on add new customer popup on cash and carry page");
    }

    /**
     * It retrieves the last name entered on add new customer popup on cash and carry page
     *
     * @return If the last name is displayed it returns the displayed last name as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getEnteredaddnewcustlastname() {
        return getElementAttribute(AddNewCustLastNameTextbox, "Last Name textbox field value on add new customer popup on cash and carry page");
    }

    /**
     * Enters the phone number on add new customer popup on cash and carry page
     *
     * @param phonenumber
     * @Author Balaji N
     */
    public void EnterAddNewCustPhoneNumber(String phonenumber) {
        AddNewCustPhoneNumberTextbox.clear();
        Double_Click_And_Type(AddNewCustPhoneNumberTextbox, phonenumber, "Phone Number textbox field on add new customer popup on cash and carry page");
    }

    public String getEnteredaddnewcustphonenumber() {
        return getElementAttribute(AddNewCustPhoneNumberTextbox, "Phone Number textbox field value on add new customer popup on cash and carry page");
    }

    public void EnterAddNewCustAltPhoneNumber(String altphonenumber) {
        AddNewCustAltPhoneNumberTextbox.clear();
        Double_Click_And_Type(AddNewCustAltPhoneNumberTextbox, altphonenumber, "Alternative phone number textbox field on add new customer popup in cash and carry page");
    }

    public String getEnteredaddnewcustaltphonenumber() {
        return getElementAttribute(AddNewCustAltPhoneNumberTextbox, "Alternative phone number textbox field value on add new customer popup in cash and carry page");
    }

    /**
     * Clicks the close icon on add new customer popup in the cash and carry page
     *
     * @Author Balaji N
     */
    public void ClickAddNewCustPopupCloseIcon() {
        js_Click(AddNewCustPopupCloseIcon, "Close Icon on add new customer popup in cash and cary page");
    }

    /**
     * Enters the company name on add new customer popup at cash and carry page
     *
     * @param companyname
     * @Author Balaji N
     */
    public void EnterCompanyNameOnAddnewCustPopup(String companyname) {
        Double_Click_And_Type(AddNewCustCompanyNameTextbox, companyname, "Company Name textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcustcompanyname() {
        return getElementAttribute(AddNewCustCompanyNameTextbox, "Company textbox field on add new customer popup at cash and carry page");
    }

    public void EnterAddress1OnAddnewCustPopup(String address1) {
        Double_Click_And_Type(AddNewCustAddress1Textbox, address1, "Address1 textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcustaddress1() {
        return getElementAttribute(AddNewCustAddress1Textbox, "Address1 textbox field on add new customer popup at cash and carry page");
    }

    public void EnterAddress2OnAddnewCustPopup(String address2) {
        Double_Click_And_Type(AddNewCustAddress2Textbox, address2, "Address2 textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcustaddress2() {
        return getElementAttribute(AddNewCustAddress2Textbox, "Address2 textbox field value on add new customer popup at cash and carry page");
    }

    public void EnterZipCodeOnAddnewCustPopup(String zipcode) {
        Double_Click_And_Type(AddNewCustZipCodeTextbox, zipcode, "zipcode textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcustzipcode() {
        return getElementAttribute(AddNewCustZipCodeTextbox, "Zipcode textbox field value on add new customer popup at cash and carry page");
    }

    public void EnterCityOnAddnewCustPopup(String city) {
        Double_Click_And_Type(AddNewCustCityTextbox, city, "City textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcustcity() {
        return getElementAttribute(AddNewCustCityTextbox, "City textbox field value on add new customer popup at cash and carry page");
    }

    public void EnterStateOnAddnewCustPopup(String state) {
        Double_Click_And_Type(AddNewCustStateTextbox, state, "State textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcuststate() {
        return getElementAttribute(AddNewCustStateTextbox, "State textbox field value on add new customer popup at cash and carry page");
    }

    public void EnterCountryOnAddnewCustPopup(String country) {
        Double_Click_And_Type(AddNewCustCountryTextbox, country, "Country textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcustcountry() {
        return getElementAttribute(AddNewCustCountryTextbox, "Country textbox field value on add new customer popup at cash and carry page");
    }

    public void EnterEmailOnAddnewCustPopup(String email) {
        Double_Click_And_Type(AddNewCustEmailTextbox, email, "Email textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcustemail() {
        return getElementAttribute(AddNewCustEmailTextbox, "Email textbox field value on add new customer popup at cash and carry page");
    }

    public void SelectCustTypeOnAddNewCustPopup(String custtype) {
        drop_Down(AddNewCustTypeDropdown, custtype, "VisibleText", "Customer Type dropdown field on add new customer popup at cash and carry page");
    }

    public String getSelectedCustTypeOnAddNewCustPopup() {
        return get_selected_option(AddNewCustTypeDropdown, "Customer Type dropdown field value on add new customer popup at cash and carry page");
    }

    public void EnterCustCreditOnAddNewCustPopup(String custcredit) {
        Double_Click_And_Type(AddNewCustCreditTextbox, custcredit, "Customer Credit textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcustcredit() {
        return getElementAttribute(AddNewCustCreditTextbox, "Customer credit textbox field value on add new customer popup at cash and carry page");
    }

    public void SelectShopNameOnAddNewCustPopup(String shopname) {
        drop_Down(AddNewCustShopDropdown, shopname, "VisibleText", "Shopname dropdown field on add new customer popup at cash and carry page");
    }

    public String getSelectedShopNameOnAddNewCustPopup() {
        return get_selected_option(AddNewCustShopDropdown, "Shopname dropdown field value on add new customer popup at cash and carry page");
    }

   /* public boolean ValidateCreditApprovedToogleOnAddNewCustPopup() {
        HighlightElement(AddNewCustPopupcreditapprovedToogle);
        return AddNewCustPopupcreditapprovedToogle.getAttribute("style").contains("rgb(223, 223, 223)");
    }

    public boolean ValidatePaperlessStatementToogleOnAddNewCustPopup() {
        HighlightElement(AddNewCustPopupPaperlessStatementsToogle);
        return AddNewCustPopupPaperlessStatementsToogle.getAttribute("style").contains("rgb(223, 223, 223)");
    }

    public boolean ValidateEmailStatementToogleOnAddNewCustPopup() {
        HighlightElement(AddNewCustPopupEmailStatementToogle);
        return AddNewCustPopupEmailStatementToogle.getAttribute("style").contains("rgb(223, 223, 223)");
    }

    public boolean ValidateEnableLoyaltyToogleOnAddNewCustPopup() {
        HighlightElement(AddNewCustPopupEnableLoyaltyToogle);
        return AddNewCustPopupEnableLoyaltyToogle.getAttribute("style").contains("rgb(223, 223, 223)");
    }

    public boolean ValidateLateFeeSettingToogleOnAddNewCustPopup() {
        HighlightElement(AddNewCustPopupLateFeeSettingToogle);
        return AddNewCustPopupLateFeeSettingToogle.getAttribute("style").contains("rgb(223, 223, 223)");
    }

    public boolean ValidateAllowSMSTToogleOnAddNewCustPopup() {
        HighlightElement(AddNewCustPopupAllowSMSToogle); // Red background color
        return AddNewCustPopupAllowSMSToogle.getAttribute("style").contains("rgb(223, 223, 223)");
    }

    public boolean ValidateAllowFeedbackEmailTToogleOnAddNewCustPopup() {
        HighlightElement(AddNewCustPopupAllowFeedBackEmailToogle); // green background color
        return AddNewCustPopupAllowFeedBackEmailToogle.getAttribute("style").contains("rgb(197, 231, 244)");
    }
*/

    /* *//**
     * Validates if the 'Credit Approved' toggle is disabled on the 'Add New Customer' popup.
     *
     * @return true if the toggle is disabled, false otherwise.
     *//*
    public boolean validate_CreditApproved_Toogle_Button_OFF_State_On_AddNewCustPopup() {
        String bordercolor = get_Style_Attribute_Of_Element(AddNewCustPopupcreditapprovedToogle, "Credit Approved toggle on add new customer popup at cash and carry page");
        System.out.println("Credit Approved toogle: " + bordercolor);
        if (bordercolor.equals("rgba(26, 179, 148, 1)")) {
            return true;
        }
        return false;
    }

    public void click_CreditApproved_Toogle_Button_OFF_State_On_AddNewCustPopup() {
        String bordercolor = get_Style_Attribute_Of_Element(AddNewCustPopupcreditapprovedToogle, "Credit Approved toggle on add new customer popup at cash and carry page");
        if (!bordercolor.equals("rgba(26, 179, 148, 1)")) {
            click(AddNewCustPopupcreditapprovedToogle, "Credit Approved toggle on add new customer popup at cash and carry page");
        }
    }

    *//**
     * Validates if the 'Paperless Statement' toggle is disabled on the 'Add New Customer' popup.
     *
     * @return true if the toggle is disabled, false otherwise.
     *//*
    public boolean ValidatePaperlessStatementToogleOnAddNewCustPopup() {
        String bordercolor = get_Style_Attribute_Of_Element(AddNewCustPopupPaperlessStatementsToogle, "Paperless Statement toggle on add new customer popup at cash and carry page");
        System.out.println("Paperless Statement toogle: " + bordercolor);
        if (bordercolor.equals("rgba(26, 179, 148, 1)")) {
            return true;
        }
        return false;

    }

    public void click_PaperlessStatement_Toogle_Button_OFF_State_On_AddNewCustPopup() {
        String bordercolor = get_Style_Attribute_Of_Element(AddNewCustPopupPaperlessStatementsToogle, "Paperless Statement toggle on add new customer popup at cash and carry page");
        if (!bordercolor.equals("rgba(26, 179, 148, 1)")) {
            click(AddNewCustPopupPaperlessStatementsToogle, "Paperless Statement toggle on add new customer popup at cash and carry page");
        }
    }

    *//**
     * Validates if the 'Email Statement' toggle is disabled on the 'Add New Customer' popup.
     *
     * @return true if the toggle is disabled, false otherwise.
     *//*
    public boolean ValidateEmailStatementToogleOnAddNewCustPopup() {
        String bordercolor = get_Style_Attribute_Of_Element(AddNewCustPopupEmailStatementToogle, "Email Statement toggle on add new customer popup at cash and carry page");
        System.out.println("Email Statement toogle: " + bordercolor);

        if (bordercolor.equals("rgba(26, 179, 148, 1)")) {
            return true;
        }
        return false;
    }

    public void click_EmailStatement_Toogle_Button_OFF_State_On_AddNewCustPopup() {
        String bordercolor = get_Style_Attribute_Of_Element(AddNewCustPopupEmailStatementToogle, "Email Statement toggle on add new customer popup at cash and carry page");
        if (!bordercolor.equals("rgba(26, 179, 148, 1)")) {
            click(AddNewCustPopupEmailStatementToogle, "Email Statement toggle on add new customer popup at cash and carry page");
        }
    }

    *//**
     * Validates if the 'Enable Loyalty' toggle is disabled on the 'Add New Customer' popup.
     *
     * @return true if the toggle is disabled, false otherwise.
     *//*
    public boolean ValidateEnableLoyaltyToogleOnAddNewCustPopup() {
        String bordercolor = get_Style_Attribute_Of_Element(AddNewCustPopupEnableLoyaltyToogle, "Enable Loyalty toggle on add new customer popup at cash and carry page");
        System.out.println("Enable Loyalty toogle: " + bordercolor);
        if (bordercolor.equals("rgba(26, 179, 148, 1)")) {
            return true;
        }
        return false;
    }

    public void click_EnableLoyalty_Toogle_Button_OFF_State_On_AddNewCustPopup() {
        String bordercolor = get_Style_Attribute_Of_Element(AddNewCustPopupEnableLoyaltyToogle, "Enable Loyalty toggle on add new customer popup at cash and carry page");
        if (!bordercolor.equals("rgba(26, 179, 148, 1)")) {
            click(AddNewCustPopupEnableLoyaltyToogle, "Enable Loyalty toggle on add new customer popup at cash and carry page");
        }
    }

    *//**
     * Validates if the 'Late Fee Setting' toggle is disabled on the 'Add New Customer' popup.
     *
     * @return true if the toggle is disabled, false otherwise.
     *//*
    public boolean ValidateLateFeeSettingToogleOnAddNewCustPopup() {
        String bordercolor = get_Style_Attribute_Of_Element(AddNewCustPopupLateFeeSettingToogle, "Late Fee Setting toggle on add new customer popup at cash and carry page");
        System.out.println("Late Fee Setting toogle: " + bordercolor);

        if (bordercolor.equals("rgba(26, 179, 148, 1)")) {
            return true;
        }
        return false;
    }

    public void click_LateFeeSetting_Toogle_Button_OFF_State_On_AddNewCustPopup() {
        String bordercolor = get_Style_Attribute_Of_Element(AddNewCustPopupLateFeeSettingToogle, "Late Fee Setting toggle on add new customer popup at cash and carry page");
        if (!bordercolor.equals("rgba(26, 179, 148, 1)")) {
            click(AddNewCustPopupLateFeeSettingToogle, "Late Fee Setting toggle on add new customer popup at cash and carry page");
        }
    }

    *//**
     * Validates if the 'Allow SMS' toggle is disabled on the 'Add New Customer' popup.
     *
     * @return true if the toggle is disabled, false otherwise.
     *//*
    public boolean ValidateAllowSMSTToogleOnAddNewCustPopup() {
        try {
            HighlightElement(AddNewCustPopupAllowSMSToogle);
            boolean isDisabled = AddNewCustPopupAllowSMSToogle.getAttribute("style").contains("rgb(223, 223, 223)");
            logger.info("Allow SMS Toggle validation result: " + isDisabled);
            return isDisabled;
        } catch (Exception e) {
            logger.error("Error validating Allow SMS Toggle: ", e);
            return false;
        }
    }

    *//**
     * Validates if the 'Allow Feedback Email' toggle is disabled on the 'Add New Customer' popup.
     *
     * @return true if the toggle is disabled, false otherwise.
     *//*
    public boolean validate_AllowFeedbackEmail_Toogle_Button_OFF_State_OnAddNewCustPopup() {
        String bordercolor = get_Style_Attribute_Of_Element(AddNewCustPopupAllowFeedBackEmailToogle, "Allow Feedback Email toggle on add new customer popup at cash and carry page");
        System.out.println("Allow Feedback Email toogle: " + bordercolor);
        if (bordercolor.equals("rgba(26, 179, 148, 1)")) {
            return true;
        }
        return false;
    }

    *//**
     * Validates if the 'Allow Feedback Email' toggle is disabled on the 'Add New Customer' popup.
     *
     * @return true if the toggle is enabled, false otherwise.
     *//*
    public boolean validate_AllowFeedbackEmail_Toogle_Button_ON_State_OnAddNewCustPopup() {
        String bordercolor = get_Style_Attribute_Of_Element(AddNewCustPopupAllowFeedBackEmailToogle, "Allow Feedback Email toggle on add new customer popup at cash and carry page");
        System.out.println("Allow Feedback Email toogle: " + bordercolor);
        if (bordercolor.equals("rgb(197, 231, 244)")) {
            return true;
        }
        return false;
    }

    public void click_AllowFeedbackEmail_Toogle_Button_ON_State_On_AddNewCustPopup() {
        String bordercolor = get_Style_Attribute_Of_Element(AddNewCustPopupAllowFeedBackEmailToogle, "Allow Feedback Email toggle on add new customer popup at cash and carry page");
        if (bordercolor.equals("rgba(26, 179, 148, 1)")) {
            click(AddNewCustPopupAllowFeedBackEmailToogle, "Allow Feedback Email toggle on add new customer popup at cash and carry page");
        }
    }*/


// --- Toggle validation and click functions using the utility ---

    /**
     * Validates if the 'Credit Approved' toggle is ON on the 'Add New Customer' popup.
     *
     * @return true if the toggle is ON, false otherwise.
     * @Author Balaji N
     */
    public boolean validate_CreditApproved_Toogle_Button_ON_State_On_AddNewCustPopup() {
        return is_Toggle_On_By_Style(AddNewCustPopupcreditapprovedToogle, "Credit Approved toggle on add new customer popup");
    }

    /**
     * Validates if the 'Credit Approved' toggle is OFF on the 'Add New Customer' popup.
     *
     * @return If the toggle is OFF return true, false otherwise.
     * @Author Balaji N
     */
    public boolean validate_CreditApproved_Toogle_Button_OFF_State_On_AddNewCustPopup() {
        return !is_Toggle_On_By_Style(AddNewCustPopupcreditapprovedToogle, "Credit Approved toggle on add new customer popup");
    }

    /**
     * Turn off the 'Credit Approved' toggle on the 'Add New Customer' popup.
     *
     * @Author Balaji N
     */
    public void click_CreditApproved_Toogle_Button_OFF_State_On_AddNewCustPopup() {
        if (is_Toggle_On_By_Style(AddNewCustPopupcreditapprovedToogle, "Credit Approved toggle on add new customer popup")) {
            click(AddNewCustPopupcreditapprovedToogle, "Credit Approved toggle on add new customer popup");
        }
    }

    public boolean Validate_PaperlessStatement_Toogle_Button_OFF_State_OnAddNewCustPopup() {
        return !is_Toggle_On_By_Style(AddNewCustPopupPaperlessStatementsToogle, "Paperless Statement toggle on add new customer popup");
    }

    public void click_PaperlessStatement_Toogle_Button_OFF_State_On_AddNewCustPopup() {
        if (is_Toggle_On_By_Style(AddNewCustPopupPaperlessStatementsToogle, "Paperless Statement toggle on add new customer popup")) {
            click(AddNewCustPopupPaperlessStatementsToogle, "Paperless Statement toggle on add new customer popup");
        }
    }

    public boolean Validate_EmailStatement_Toogle_Button_OFF_State_OnAddNewCustPopup() {
        return !is_Toggle_On_By_Style(AddNewCustPopupEmailStatementToogle, "Email Statement toggle on add new customer popup");
    }

    public void click_EmailStatement_Toogle_Button_OFF_State_On_AddNewCustPopup() {
        if (is_Toggle_On_By_Style(AddNewCustPopupEmailStatementToogle, "Email Statement toggle on add new customer popup")) {
            click(AddNewCustPopupEmailStatementToogle, "Email Statement toggle on add new customer popup");
        }
    }

    public boolean Validate_EnableLoyalty_Toogle_Button_OFF_State_OnAddNewCustPopup() {
        return !is_Toggle_On_By_Style(AddNewCustPopupEnableLoyaltyToogle, "Enable Loyalty toggle on add new customer popup");
    }

    public void click_EnableLoyalty_Toogle_Button_OFF_State_On_AddNewCustPopup() {
        if (is_Toggle_On_By_Style(AddNewCustPopupEnableLoyaltyToogle, "Enable Loyalty toggle on add new customer popup")) {
            click(AddNewCustPopupEnableLoyaltyToogle, "Enable Loyalty toggle on add new customer popup");
        }
    }

    public boolean Validate_LateFeeSetting_Toogle_Button_OFF_State_OnAddNewCustPopup() {
        return !is_Toggle_On_By_Style(AddNewCustPopupLateFeeSettingToogle, "Late Fee Setting toggle on add new customer popup");
    }

    public void click_LateFeeSetting_Toogle_Button_OFF_State_On_AddNewCustPopup() {
        if (is_Toggle_On_By_Style(AddNewCustPopupLateFeeSettingToogle, "Late Fee Setting toggle on add new customer popup")) {
            click(AddNewCustPopupLateFeeSettingToogle, "Late Fee Setting toggle on add new customer popup");
        }
    }

    public boolean ValidateAllowSMSTToogleOnAddNewCustPopup() {
        return !is_Toggle_On_By_Style(AddNewCustPopupAllowSMSToogle, "Allow SMS toggle on add new customer popup");
    }

    public void click_Allow_SMS_Toogle_OFF_State_On_AddNewCustPopup() {
        if (is_Toggle_On_By_Style(AddNewCustPopupAllowSMSToogle, "Allow SMS toggle on add new customer popup")) {
            click(AddNewCustPopupAllowSMSToogle, "Allow SMS toggle on add new customer popup");
        }
    }

    public boolean validate_AllowFeedbackEmail_Toogle_Button_OFF_State_OnAddNewCustPopup() {
        return !is_Toggle_On_By_Style(AddNewCustPopupAllowFeedBackEmailToogle, "Allow Feedback Email toggle on add new customer popup");
    }

    public boolean validate_AllowFeedbackEmail_Toogle_Button_ON_State_OnAddNewCustPopup() {
        return is_Toggle_On_By_Style(AddNewCustPopupAllowFeedBackEmailToogle, "Allow Feedback Email toggle on add new customer popup");
    }

    public void click_AllowFeedbackEmail_Toogle_Button_ON_State_On_AddNewCustPopup() {
        if (!is_Toggle_On_By_Style(AddNewCustPopupAllowFeedBackEmailToogle, "Allow Feedback Email toggle on add new customer popup")) {
            click(AddNewCustPopupAllowFeedBackEmailToogle, "Allow Feedback Email toggle on add new customer popup");
        }
    }


    /**
     * Clicks the 'Add New Customer' button on the 'Add New Customer' popup.
     *
     * @Author Balaji N
     */
    public void click_AddNewCustomer_Button_On_AddNewCustPopup() {
        js_Click(AddNewCustAddNewCustomerButton, "Add New Customer button on cash and carry page");
    }

    public void ClickOnSplitPaymentToogleButton() {
        js_Click(SplitPaymentToogleButton, "Split Payment toogle button on cash and carry page");
    }

    public void EnterItemCodeOnCPDV(String itemcodeCPDV) {
        Double_Click_And_Type(ItemCodeTextBoxChooseDefaultValues, itemcodeCPDV, "Item code field on choose default values popup in the cash and carry page");
    }

    /**
     * Retrieves the item code on the preference tab on customer details popup in the cash and carry page
     *
     * @return If the item code is not found, returns an empty string
     * @Author Balaji N
     */
    public String getEnteredItemCodeOnCPDV() {
        return getElementAttribute(ItemCodeTextBoxChooseDefaultValues, "Item code field on choose default values popup in the cash and carry page");
    }

    /**
     * Enters the quantity on the preference tab on customer details popup in the cash and carry page
     *
     * @param PhoneOrderqtyCPDV
     * @Author Balaji N
     */
    public void EnterQtyonPhoneOrderCPDV(String PhoneOrderqtyCPDV) {
        Double_Click_And_Type(PhoneOrderQTYTextBoxChooseDefaultValues, PhoneOrderqtyCPDV, "Quantity field on choose default values popup in the cash and carry page");
    }

    /**
     * Retrieves the quantity on the preference tab on customer details popup in the cash and carry page
     *
     * @return If the quantity is displayed it returns the quantity, returns an empty string
     * @Author Balaji N
     */
    public String getEnteredQtyonPhoneOrderCPDV() {
        return getElementAttribute(PhoneOrderQTYTextBoxChooseDefaultValues, "Quantity field on choose default values popup in the cash and carry page");
    }

    /**
     * Enters the recipient phone on the preference tab on customer details popup in the cash and carry page
     *
     * @param recipientphone
     * @Author Balaji N
     */
    public void EnterRecipientPhone(String recipientphone) {
        Double_Click_And_Type(PhoneOrderRecipientPhoneTextBoxChooseDefaultValues, recipientphone, "");
    }

    /**
     * Retrieves the recipient phone on the preference tab on customer details popup in the cash and carry page
     *
     * @return If the recipient phone is displayed it returns the recipient phone, returns an empty string
     */
    public String getEnteredRecipientPhoneonPhoneOrderCPDV() {
        return getElementAttribute(PhoneOrderRecipientPhoneTextBoxChooseDefaultValues, "Recipient phone field on choose default values popup in the cash and carry page");
    }

    /**
     * Enters the zipcode on the preference tab on customer details popup in the cash and carry page
     *
     * @param zipcodePhoneCPDV
     * @Author Balaji N
     */
    public void EnterZipcodeOnPhoneOrderCPDV(String zipcodePhoneCPDV) {
        Double_Click_And_Type(PhoneOrderRecipientZipcodeTextBoxChooseDefaultValues, zipcodePhoneCPDV, "Zipcode field on choose default values popup in the cash and carry page");
    }

    /**
     * Retrieves the zipcode on the preference tab on customer details popup in the cash and carry page
     *
     * @return If the zipcode is displayed it returns the zipcode, returns an empty string
     * @Author Balaji N
     */
    public String getEnteredZipcodeOnPhoneOrderCPDV() {
        return getElementAttribute(PhoneOrderRecipientZipcodeTextBoxChooseDefaultValues, "Recipient zipcode field on choose default values popup in the cash and carry page");
    }

    /**
     * Select the default wire in method on choose default values popup in the cash and carry page
     *
     * @param defaultWireInMethod
     */
    public void SelectDefaultWireInMethod(String defaultWireInMethod) {
        drop_Down(PhoneOrderWireInMethodDefaultValuesDD, defaultWireInMethod, "VisibleText", "Wire in method field on choose default values popup in the cash and carry page");
    }

    /**
     * Retrieves the default wire in method on choose default values popup in the cash and carry page
     *
     * @return If the default wire in method is displayed it returns the default wire in method, returns an empty string
     * @Author Balaji N
     */
    public String getSelectedDefaultWireInMethod() {
        return get_Selected_Option(PhoneOrderWireInMethodDefaultValuesDD, "Wire in method field on choose default values popup in the cash and carry page");
    }

    /**
     * Select the default wire out method on choose default values popup in the cash and carry page
     *
     * @param defaultWireOutMethod
     * @Author Balaji N
     */
    public void SelectDefaultWireOutMethod(String defaultWireOutMethod) {
        drop_Down(PhoneOrderWireOutMethodDefaultValuesDD, defaultWireOutMethod, "VisibleText", "Wire Out Method dropdown field on choose default values popup in the cash and carry page");
    }

    /**
     * Retrieves the default wire out method on choose default values popup in the cash and carry page
     *
     * @return If the default wire out method is displayed it returns the default wire out method, returns an empty string
     * @Author Balaji N
     */
    public String getSelectedDefaultWireOutMethod() {
        return get_Selected_Option(PhoneOrderWireOutMethodDefaultValuesDD, "Wire Out Method dropdown field on choose default values popup in the cash and carry page");
    }

    /**
     * Select the default payment type on cash and carry page
     *
     * @param paymentType
     * @Author Balaji N
     */
    public void SelectDefaultPaymentType(String paymentType) {
        drop_Down(PhoneOrderPaymentTypeDefaultValuesDD, paymentType, "VisibleText", "Payment Type dropdown field on choose default values popup in cash and carry page");
    }

    public String getSelectedDefaultPaymentType() {
        return get_Selected_Option(PhoneOrderPaymentTypeDefaultValuesDD, "Payment Type dropdown field on choose default values popup in cash and carry page");
    }

    public void SelectDefaultCustomerType(String customerType) {
        drop_Down(PhoneOrderCustomerTypeDefaultValuesDD, customerType, "VisibleText", "Customer Type dropdown field on choose default values popup in cash and carry page");
    }

    public String getSelectedDefaultCustomerType() {
        return get_Selected_Option(PhoneOrderCustomerTypeDefaultValuesDD, "Customer Type dropdown field on chose default values popup in cash and carry page");
    }

    public void SelectDefaultSalesPerson(String salesPerson) {
        drop_Down(PhoneOrderSalesPersonDefaultValuesDD, salesPerson, "VisibleText", "Sales Person dropdown field on choose default values popup in cash and carry page");
    }

    public String getSelectedDefaultSalesPerson() {
        return get_Selected_Option(PhoneOrderSalesPersonDefaultValuesDD, "Sales Person dropdown field on choose default values popup in cash and carry page");
    }

    public void SelectDefaultCountry(String country) {
        drop_Down(PhoneOrderSelectCountryDefaultValuesDD, country, "VisibleText", "Country dropdown field on choose default values popup in cash and carry page");
    }

    public String getSelectedDefaultCountry() {
        return get_Selected_Option(PhoneOrderSelectCountryDefaultValuesDD, "Country dropdown field on choose default values popup in cash and carry page");
    }

    public void SelectDefaultLocation(String location) {
        drop_Down(PhoneOrderSelectLocationDefaultValuesDD, location, "VisibleText", "Location dropdown field on choose default values popup in cash and carry page");
    }

    public String getSelectedDefaultLocation() {
        return get_Selected_Option(PhoneOrderSelectLocationDefaultValuesDD, "Location dropdown field on choose default values popup in cash and carry page");
    }

    public void SelectDefaultOccasion(String occasion) {
        drop_Down(PhoneOrderSelectOccasionDefaultValuesDD, occasion, "VisibleText", "Occasion dropdown field on choose default values popup in cash and carry page");
    }

    public String getSelectedDefaultOccasion() {
        return get_Selected_Option(PhoneOrderSelectOccasionDefaultValuesDD, "Occasion dropdown field on choose default values popup in cash and carry page");
    }

    public void SelectDefaultOrderType(String ordertype) {
        drop_Down(PhoneOrderSelectOrderTypeDefaultValuesDD, ordertype, "VisibleText", "Order Type dropdown field on choose default values popup in cash and carry page");
    }

    public String getSelectedDefaultOrderType() {
        return get_Selected_Option(PhoneOrderSelectOrderTypeDefaultValuesDD, "Order Type dropdown field on choose default values popup in cash and carry page");
    }

    public void select_Source_Code_Dropdown(String sourceCode) {
        drop_Down(PhoneOrderSourceCodeDefaultValuesDD, sourceCode, "VisibleText", "Source Code dropdown field on choose default values popup in cash and carry page");
    }

    public String getSelectedDefaultSourceCode() {
        return get_Selected_Option(PhoneOrderSourceCodeDefaultValuesDD, "Source Code dropdown field on choose default values popup in cash and carry page");
    }

    public void ClickCloseIconOnChooseDefaultValuesPage() {
        js_Click(CloseIconOnChooseDefaultValuesPagePopup, "Close icon on choose default values popup in cash and carry page");
    }

    public void ClickUpdateButtonOnChooseDefaultValuesPage() {
        js_Click(ChooseDefaultValuesUpdateButton, "Update button on choose default values popup in cash and carry page");
    }

    public String VerifyToolTipOnLeftSideTileProduct(String prodTile) {
        String prodTile_tooltip = "";

        if (ListOfProdTiles != null && !ListOfProdTiles.isEmpty()) {
            for (int i = 0; i < ListOfProdTiles.size(); i++) {
                delayWithGivenTime(1000);
                if (ListOfProdTiles.get(i).getAttribute("title").contains(prodTile)) {
                    prodTile_tooltip = ListOfProdTiles.get(i).getAttribute("title");
                    break;
                }
            }
        }

        return prodTile_tooltip;
    }


    public void Click_ToolTip_OnLeftSideTileProduct(String prodTile_tooltip) {
        for (WebElement ele : flowerstd_leftside_displayedTile) {
            if (ele.getAttribute("title").equals(prodTile_tooltip)) {
                ele.click();
                break;
            }
        }
    }

    public String Verify_ToolTip_OnLeftSideTileProduct() {
        String flag = null;
        for (WebElement ele : flowerstd_leftside_displayedTile) {
            if (ele.getAttribute("title").contains("Tile Product-Test Automation Tile Product Small-$220.00")) {
                HighlightElement(ele);
                delayWithGivenTime(2000);
                flag = ele.getText();
                return flag;
            }
        }
        return flag;
    }

    /**
     * Press Alt + H key on cash and carry page to open sales history
     *
     * @Author Balaji N
     */
    public void PressAltKeyH_SalesHistory() {
        Actions actions = new Actions(getDriver());
        actions.keyDown(Keys.ALT).sendKeys("h").keyUp(Keys.ALT).build().perform();
    }

    /**
     * Verify Cash Draw popup is displayed or not on cash and carry page
     *
     * @return If the Cash Draw popup is displayed it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean VerifyCashDrawPopupDisplayed() {
        return is_Element_Displayed(CashDrawPopupHeader, "Cash Draw popup header in cash and carry page");
    }

    public void SelectShopCashDrawPopup(String shopname) {
        drop_Down(ShopPayoutDropDown, shopname, "VisibleText", "Shop payout dropdown field on cash draw popup in cash and carry page");
    }

    public void SelectCashRegisterId(String cashregisterid) {
        //ClerkPayoutDropDown.click();
        //	delayWithGivenTime(1000);
        drop_Down(ClerkPayoutDropDown, cashregisterid, "value", "Cash Register ID payout dropdown field on cash draw popup in cash and carry page");
    }

    public void Select_CashRegisterId(String cashregisterid) {
        ClerkPayoutDropDown.click();
        delayWithGivenTime(1000);
        for (int i = 0; i < ClerkPayoutDropDownList.size(); i++) {
            if (ClerkPayoutDropDownList.get(i).getText().contains(cashregisterid)) {
                (ClerkPayoutDropDownList.get(i)).click();
                break;
            }
        }
    }

    /**
     * Get the selected Cash Register ID from Cash Register ID payout dropdown field on cash draw popup in cash and carry page
     *
     * @return If the Cash Register ID is selected it returns the selected Cash Register ID; otherwise, it returns empty string
     * @Author Balaji N
     */
    public String getSelectedCashRegisterId() {
        return get_selected_option(ClerkPayoutDropDown, "Cash Register ID payout dropdown field on cash draw popup in cash and carry page");
    }

    public void EnterNameonCashDrawPopup(String name) {
        DoubleClickAndType(NamePayoutTextBox, name);
    }

    public String getEnteredNameonCashDrawPopup() {
        return NamePayoutTextBox.getAttribute("value");
    }

    public void EnterReasononCashDrawPopup(String reason) {
        Double_Click_And_Type(ReasonPayoutTextBox, reason, "Reason Payout textbox field on cash draw popup in cash and carry page");
    }

    public String getEnteredReasononCashDrawPopup() {
        return get_element_attribute(ReasonPayoutTextBox, "Reason Payout textbox field on cash draw popup in cash and carry page");
    }

    public void EnterSubTotalonCashDrawPopup(String subtotal) {
        jsClear(SubTotalPayoutTextBox);
        Double_Click_And_Type(SubTotalPayoutTextBox, subtotal, "Subtotal Payout textbox field on cash draw popup in cash and carry page");
    }

    public String getEnteredSubTotalonCashDrawPopup() {
        return get_element_attribute(SubTotalPayoutTextBox, "Subtotal Payout textbox field on cash draw popup in cash and carry page");
    }

    public void EnterTaxAmountonCashDrawPopup(String taxamount) {
        jsClear(TaxAmountPayoutTextBox);
        Double_Click_And_Type(TaxAmountPayoutTextBox, taxamount, "Tax Amount Payout textbox field on cash draw popup in cash and carry page");
    }

    public String getEnteredTaxAmountonCashDrawPopup() {
        return TaxAmountPayoutTextBox.getAttribute("value");
    }

    public boolean VerifyGrandTotalonCashDrawPopup() {
        HighlightElement(GrandTotalPayoutTextBox);
        boolean isDisabled = false;
        if (GrandTotalPayoutTextBox.isEnabled() == false) {
            isDisabled = GrandTotalPayoutTextBox.isEnabled();
        }
        return isDisabled;
    }

    public void ClickCloseIcon() {
        js_Click(CloseiconCashDrawPopup, "close icon on cash draw popup in cash and carry page");
    }

    public void ClickSaveBtnOnCashDrawPopup() {
        js_Click(SavePayoutBtn, "Save payout button on cash draw popup in cash and carry page");
    }

    public void ClickOnReconcileBtn() {
        js_Click(ReconcileBtn, "Reconcile button on cash and carry page");
    }

    public boolean VerifyReconcilePopupDisplayed() {
      /*  HighlightElement(ReconcilePopupHeader);
        return ReconcilePopupHeader.isDisplayed();*/
        return is_Element_Displayed(ReconcilePopupHeader, "Reconcile popup header in cash and carry page");
    }

    public String getSelectedCashRegistryonReconcilePopup() {
        return get_selected_option(ClerkReconcileDropDown, "Clerk Reconcile dropdown field on reconcile popup in cash and carry page");
    }

    public void Select_CashRegistryIdonReconcilePopup(String reconcilecashregisterid) {
        ClerkReconcileDropDown.click();
        for (int i = 0; i < listOfClerkReconcileDropDown.size(); i++) {
            if (listOfClerkReconcileDropDown.get(i).getText().contains(reconcilecashregisterid)) {
                (listOfClerkReconcileDropDown.get(i)).click();
                break;
            }
        }

        //dropDown(ClerkReconcileDropDown, reconcilecashregisterid, "value");
    }

    public void SelectShopReconcilePopup(String reconcileshopname) {
        drop_Down(ShopReconcileDropDown, reconcileshopname, "VisibleText", "Shop Reconcile dropdown field on reconcile popup in cash and carry page");
    }

    public String getSelectedShopNameonReconcilePopup() {
        return get_selected_option(ShopReconcileDropDown, "Shop Reconcile dropdown field on reconcile popup in cash and carry page");
    }

    public String getPayOut_Amount_OnReconcilePopup() {
        return getElementText(payOutAmt_ReconcilePopup, "Pay Out Amount on reconcile popup in cash and carry page");
    }

    public String ExpectedCalculatedPayoutAmount() {
        String previousactualamount = getPayOut_Amount_OnReconcilePopup();
        double addedamount = 100.00;
        String Expected = previousactualamount;
        return Expected;
    }

    /**
     * It verifies last reconciliation date is displayed or not on cash and carry page
     *
     * @return If the last reconciliation date is displayed it returns true, otherwise it returns false
     * @Author: Balaji N
     */
    public boolean verify_Last_Reconciliation_Date_IsDisplayed() {
        return is_Element_Displayed(lastReconcillationDate, "Last Reconciliation Date");
    }

    public String ValidateLastReconciliationdate() {
        return getElementText(lastReconcillationDate, "Last Reconciliation Date");
    }

    public boolean VerifyOpenBalanceOnReconcilePopup() {
        return is_Element_Displayed(openBalance, "Open Balance");
    }

    public boolean VerifyExpectedBalance() {
        return is_Element_Displayed(expectedBalance, "Expected Balance");
    }

    public String getExpectedBalance() {
        return getElementText(expectedBalance, "Expected Balance");
    }

    public boolean VerifyActualBalance() {
        return is_Element_Displayed(actualBalance, "Actual Balance");
    }

    public String getActualBalance() {
        return getElementText(actualBalance, "Actual Balance");
    }

    public String ExpectedBalanceDifference() {
        double expectedBalanceDifference = Double.parseDouble(getActualBalance()) - Double.parseDouble(getExpectedBalance());
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedexpectedBalanceDifference = df.format(expectedBalanceDifference);

        System.out.println("Expected Balance Difference is :" + formattedexpectedBalanceDifference);
        return formattedexpectedBalanceDifference;
    }

    public String ExpectedActualBalance() {
        double OneDollar = Double.parseDouble(oneDollars_denomination.getAttribute("value").trim());
        double TwoDollars = Double.parseDouble(twoDollars_denomination.getAttribute("value").trim());
        double FiveDollars = Double.parseDouble(fiveDollars_denomination.getAttribute("value").trim());
        double TwentyDollars = Double.parseDouble(twentyDollars_denomination.getAttribute("value").trim());
        double FiftyDollars = Double.parseDouble(fiftyDollars_denomination.getAttribute("value").trim());
        double OneHundredDollars = Double.parseDouble(oneHundredDollars_denomination.getAttribute("value").trim());

        double actualDenominationBalance = (OneDollar * 1 + TwoDollars * 2 + FiveDollars * 5 + TwentyDollars * 20 + FiftyDollars * 50 + OneHundredDollars * 100);
        System.out.println("Actual Denomination Balance is :" + actualDenominationBalance);

        double pennies = Double.parseDouble(pennies_denomination.getAttribute("value").trim());
        double nickles = Double.parseDouble(nickles_denomination.getAttribute("value").trim());
        double dimes = Double.parseDouble(dimes_denomination.getAttribute("value").trim());
        double quarters = Double.parseDouble(quarters_denomination.getAttribute("value").trim());

        double actualCoinBalance = (pennies * 0.01) + (nickles * 0.05) + (dimes * 0.10) + (quarters * 0.25);
        System.out.println("Actual Coin Balance is :" + actualCoinBalance);

        double expected_Actual_Balance = actualDenominationBalance + actualCoinBalance;

        DecimalFormat df = new DecimalFormat("#.00");
        String formattedexpected_Actual_Balance = df.format(expected_Actual_Balance);

        System.out.println("Expected Actual Balance is :" + formattedexpected_Actual_Balance);
        return formattedexpected_Actual_Balance;
    }


    public double parseOrDefault(String value) {

        if (value == null || value.trim().isEmpty()) {
            return 0;
        } else {
            return Double.parseDouble(value.trim());
        }

    }

    public String Expected_Actual_Balance() {
        // Method to safely parse double from string, defaulting to 0.0 if the string is empty

        // Parse denomination values
        double OneDollar = parseOrDefault(oneDollars_denomination.getAttribute("value").trim());
        double TwoDollars = parseOrDefault(twoDollars_denomination.getAttribute("value").trim());
        double FiveDollars = parseOrDefault(fiveDollars_denomination.getAttribute("value").trim());
        double TwentyDollars = parseOrDefault(twentyDollars_denomination.getAttribute("value").trim());
        double FiftyDollars = parseOrDefault(fiftyDollars_denomination.getAttribute("value").trim());
        double OneHundredDollars = parseOrDefault(oneHundredDollars_denomination.getAttribute("value").trim());

        double actualDenominationBalance = (OneDollar * 1 + TwoDollars * 2 + FiveDollars * 5 + TwentyDollars * 20 + FiftyDollars * 50 + OneHundredDollars * 100);
        System.out.println("Actual Denomination Balance is :" + actualDenominationBalance);

        // Parse coin values
        double pennies = parseOrDefault(pennies_denomination.getAttribute("value").trim());
        double nickles = parseOrDefault(nickles_denomination.getAttribute("value").trim());
        double dimes = parseOrDefault(dimes_denomination.getAttribute("value").trim());
        double quarters = parseOrDefault(quarters_denomination.getAttribute("value").trim());

        double actualCoinBalance = (pennies * 0.01) + (nickles * 0.05) + (dimes * 0.10) + (quarters * 0.25);
        System.out.println("Actual Coin Balance is :" + actualCoinBalance);

        double expected_Actual_Balance = actualDenominationBalance + actualCoinBalance;

        DecimalFormat df = new DecimalFormat("#.00");
        String formattedexpected_Actual_Balance = df.format(expected_Actual_Balance);

        System.out.println("Expected Actual Balance is :" + formattedexpected_Actual_Balance);
        return formattedexpected_Actual_Balance;
    }


    public boolean VerifyDiffernce() {
        return is_Element_Displayed(difference, "Difference");
    }

    public String getActualBalanceDifference() {
        /*HighlightElement(difference);
        return difference.getText();*/
        return getElementText(difference, "Actual Balance Difference");
    }

    public boolean VerifyDifferenceFieldRedColorAppears() {
        return differencefieldcolor.getAttribute("class").contains("Red");
    }

    public boolean VerifyDifferenceFieldGreenColorAppears() {
        return differencefieldcolor.getAttribute("class").contains("Green");
    }

    public boolean VerifyCashRegisterSalesReview_dateTime() {
        return is_Element_Displayed(CashRegisterSalesReview_dateTime, "Date and Time on Cash Register Sales Review page");
    }

    public boolean VerifyCashRegisterSalesReview_Cash() {
        return is_Element_Displayed(CashRegisterSalesReview_Cash, "Cash on Cash Register Sales Review page");
    }

    public boolean VerifyCashRegisterSalesReview_AfterTax() {
       /* HighlightElement(CashRegisterSalesReview_AfterTax);
        return CashRegisterSalesReview_AfterTax.isDisplayed();*/
        return is_Element_Displayed(CashRegisterSalesReview_AfterTax, "After tax on Cash Register Sales Review page");
    }

    public boolean VerifyCashRegisterSalesReview_TotalSales() {
        return is_Element_Displayed(CashRegisterSalesReview_TotalSales, "Total sales on Cash Register Sales Review page");
    }

    public boolean VerifyCashRegisterSalesReview_StartCash() {
        return is_Element_Displayed(CashRegisterSalesReview_StartCash, "Start Cash on Cash Register Sales Review page");
    }

    public String getCashInDrawerCalculation() {
        String cash = CashRegisterSalesReview_AfterTax.getText();
        String startcash = CashRegisterSalesReview_StartCash.getText();
        String payout = CashRegisterSalesReview_PayoutAmt.getText();

        double cashvalue = Double.parseDouble(cash);
        double startcashvalue = Double.parseDouble(startcash);
        double payoutvalue = Double.parseDouble(payout);

        double cashInDrawer = (cashvalue + startcashvalue) - payoutvalue;

        DecimalFormat df = new DecimalFormat("#.00");
        String formattedExpectedcashInDrawer = df.format(cashInDrawer);


        //	String formattedCashInDrawer =	String.format("%.2f", cashInDrawer);
        System.out.println("Cash In Drawer is :" + formattedExpectedcashInDrawer);
        return formattedExpectedcashInDrawer;
    }

    public String getCashInDrawer() {
        return getElementText(CashRegisterSalesReview_CashInDrawer, "Cash in drawer");
    }

    public void EnterOneDollarDenomination(String onedollardenomination) {
        Double_Click_And_Type(oneDollars_denomination, onedollardenomination, "One dollar denomination textbox field on reconcile popup");
    }

    public void EnterTwoDollarDenomination(String twodollardenomination) {
        Double_Click_And_Type(twoDollars_denomination, twodollardenomination, "Two dollar denomination textbox field on reconcile popup");
    }

    public void EnterfiveDollarDenomination(String fivedollardenomination) {
        Double_Click_And_Type(fiveDollars_denomination, fivedollardenomination, "Five dollar denomination textbox field on reconcile popup");
    }

    public void EnterFiftyDollarDenomination(String fiftydollardenomination) {
        Double_Click_And_Type(fiftyDollars_denomination, fiftydollardenomination, "Fifty dollar denomination textbox field on reconcile popup");
    }

    public void EnterOneHundredDollarDenomination(String onehundreddollardenomination) {
        Double_Click_And_Type(oneHundredDollars_denomination, onehundreddollardenomination, "One hundred dollar denomination textbox field on reconcile popup");
    }

    public void EnterPenniesDenomination(String penniesdenomination) {
        Double_Click_And_Type(pennies_denomination, penniesdenomination, "Pennies denomination textbox field on reconcile popup");
    }

    public void EnterTwentyDollarDenomination(String twentydollardenomination) {
        Double_Click_And_Type(twentyDollars_denomination, twentydollardenomination, "Twenty dollar denomination textbox field on reconcile popup");
    }

    public String getEnteredTwentyDollarDenomination() {
        return getElementAttribute(twentyDollars_denomination, "Twenty dollar denomination value on reconcile popup");
    }

    /**
     * Clicks the reconcile button in reconcile popup
     *
     * @Author Balaji N
     */
    public void ClickReconcileBtnInPopup() {
        Click(ReconcileBtn_ReconcilePopup, "Reconcile Button in Reconcile Popup");
    }

    public void ValidateDifferenceFieldDisplayedColor() {
        HighlightElement(difference);
        String color = difference.getCssValue("color");
        String backgroundColor = difference.getCssValue("background-color");

        // Print the color and background color values
        System.out.println("Color: " + color);
        System.out.println("Background Color: " + backgroundColor);

        // Expected color values for red (these values may vary depending on the implementation)
        String expectedColor = "rgba(255, 0, 0, 1)"; // This is a common value for red
        String expectedBackgroundColor = "rgba(255, 255, 255, 1)"; // Assuming white background for contrast

        // Validate that the color is red
        if (color.equals(expectedColor) || backgroundColor.equals(expectedColor)) {
            System.out.println("The difference field is highlighted in red.");
        } else {
            System.out.println("The difference field is not highlighted in red.");
        }
    }

    /**
     * This method is used to click on a displayed tile
     *
     * @param tile
     * @Author : Sakrateesh R
     */
    public void Click_Displayed_Tile_Product(String tile) {
        for (WebElement Tile : ListOfCashAndCarryTileProducts) {
            HighlightElement(Tile);
            if (Tile.getText().contains(tile)) {
                Click(Tile, "Product tile on cash and carry page");
                break;
            }

        }
    }

    /**
     * This method is used to select a value from dropdown
     *
     * @Description: This method is used to select a value from the Expense Type dropdown List
     * @Author : Sakrateesh R
     */
    public void Select_Payroll_Expense_Type(String expense) {
        drop_Down(Expense_Type_element, expense, "VisibleText", "Expense Type dropdown field - payout popup");
    }

    /**
     * This method is used to select a value from dropdown
     *
     * @Description: This method is used to select a value from the Expense Type dropdown List
     * @Author : Sakrateesh R
     */
    public void Select_Employee_from_dropdown(String employee) {
        drop_Down(EmployeeNamedrpElement, employee, "VisibleText", "Employee dropdown field on cash and carry page");
    }


    /**
     * This method is used to enter the Card Number in the Enter Gift Card Details pop-up
     *
     * @param CardNumber
     * @Author: Sakrateesh R
     */
    public void Physical_GiftCard_Search_and_Select_Customer(String customerName) {
        js_ClickAndType(CustomerNameTextBox, customerName, "Customer Search Field");
        if (customersearchlist.size() > 0) {
            for (WebElement customer : customersearchlist) {
                if (customer.getText().contains(customerName)) {
                    Click(customer, "Physical Gift Card Customer Search List");
                }
            }
        }
    }

    /**
     * This method is used to Click the Edit icon based on the given ClerkId
     *
     * @param ClerkID
     * @Author: Sakrateesh R
     */
    public void Click_Edit_icon_For_The_Given_Clerk_ID(String ClerkID) {
        WebElement EditIcon = getDriver().findElement(By.xpath("//div[@id='divWalkInSettingsTableData']//table//tbody//tr/td[text()='" + ClerkID + "']/following-sibling::td[6]//a[@id='btnEditWalkInSetting']"));
        Click(EditIcon, "Walk In Settings Cash Register Edit Icon");
    }

    public boolean Verify_Cash_Registry_Is_Displayed(String ClerkID) {
        WebElement displayed = getDriver().findElement(By.xpath("//div[@id='divWalkInSettingsTableData']//table//tbody//tr/td[text()='" + ClerkID + "']/following-sibling::td[6]//a[@id='btnEditWalkInSetting']"));
        if (displayed.isDisplayed()) {
            return true;
        }
        return false;
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

    public static String generateGiftCardNumber() {
        Faker faker = new Faker();
        String part1 = faker.number().digits(4);
        String part2 = faker.number().digits(4);
        String part3 = faker.number().digits(4);
        String part4 = faker.number().digits(4);
        return part1 + "" + part2 + "" + part3 + "" + part4;
    }

    public void saveGiftCardInfoToFile(String giftCardNumber, String giftAmount) {
        String filePath = "src//test//resources//giftcardnum.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Optional: Add timestamp for traceability
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            writer.write("GeneratedTime: " + timeStamp);
            writer.newLine();
            writer.write("GiftCardNumber: " + giftCardNumber);
            writer.newLine();
            writer.write("GiftAmount: " + giftAmount);
            writer.newLine();
            writer.write("=====================================");
            writer.newLine();

            System.out.println("Gift card number and amount saved to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to giftcardnum.txt: " + e.getMessage());
        }
    }

    public void saveGiftCardInfoToFile(String giftCardNumber, String giftAmount, String invoiceNumber, String ordertype, String methodOfPayment) {
        String filePath = "src//test//resources//giftcardpaymentinfo.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            writer.write("Gift Card Generated Time : " + timeStamp);
            writer.newLine();
            writer.write("Gift Card Number         : " + giftCardNumber);
            writer.newLine();
            writer.write("Gift Amount              : " + giftAmount);
            writer.newLine();
            writer.write("Order Type               : " + ordertype);
            writer.newLine();
            writer.write("Invoice Number           : " + invoiceNumber);
            writer.newLine();
            writer.write("Mode Of Payment          : " + methodOfPayment);
            writer.newLine();
            writer.write("=====================================");
            writer.newLine();

            System.out.println("Gift card number and amount saved to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to giftcardnum.txt: " + e.getMessage());
        }
    }

    /**
     * Calculate the expected total gift value
     *
     * @return If the total gift value is displayed it returns the total gift value, else it return null
     * @Author: Balaji N
     */
    public String expectedTotalGiftValue() {
        Double processingfee = Double.parseDouble(get_element_attribute(ProcessingFeeTextBox, "Processing Fee Text Box on Gift Card Sale Preview Popup"));
        Double giftamount = Double.parseDouble(get_element_attribute(GiftCardAmountTextBox, "Gift Amount Text Box on Gift Card Sale Preview Popup")); // Double.parseDouble(GiftCardAmountTextBox.getAttribute("value"));
        Double totalgiftvalue = processingfee + giftamount;
        return String.format("%.2f", totalgiftvalue);
    }

    public boolean is_Verify_Password_Popup_Displayed_For_Reconcile() {
        return is_Element_Displayed(reconcile_password_popup, "Verify Password Popup");
    }

    public void enter_Password_For_Reconcile_Verify_Password_Popup(String password) {
        ClickAndType(reconcile_password_textbox, password, "Password Text Box on Verify Password Popup");
    }

    public void click_Verify_Button_For_Reconcile_Verify_Password_Popup() {
        click(reconcile_verify_password_btn, "Verify Button on Verify Password Popup");
    }

}
	




