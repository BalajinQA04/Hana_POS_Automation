package com.hanapos.pageObjects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.mail.*;

/**
 * CashAndCarryPaymentPage class is a page object class for Cash and Carry Payment page
 * It contains all the web elements of Cash and Carry Payment page and thier interactions functions
 *
 * @Author Balaji N
 */
public class CashAndCarryPaymentPage extends TestBaseClass {
    public CashAndCarryPaymentPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public Select select;

    //===================================== Cash and Carry Payment Page WebElements =========================================
    @FindBy(xpath = "//a[@class='btn btn-warning btn-sm btn']")
    private WebElement backBtn;

    @FindBy(xpath = "//h3[contains(text(),'Payment')]")
    private WebElement PaymentPage;

    @FindBy(id = "tabCash")
    private WebElement CashTab;

    @FindBy(xpath = "//p[@id='lblCustomerId']")
    private WebElement CustomerId;

    @FindBy(xpath = "//p[@id='lblCustomerName']")
    private WebElement CustomerName;

    @FindBy(xpath = "//button[@id='btnProcessPayment']")
    private WebElement ProcessPaymentBtn;

    @FindBy(xpath = "(//span[@class='switchery switchery-default'])[3]")
    private WebElement SplitPaymentBtnOnCCPPage;

    @FindBy(xpath = "(//button[@class='btn btn-primary btnAddCustomer'][normalize-space()='Add'])[2]")
    private WebElement AddCustomerBtnOnCCPPage;

    @FindBy(xpath = "(//button[@class='btn btn-danger resetCutomerBtn'][normalize-space()='X'])[2]")
    private WebElement crossIconOnAddCustPopup;

    @FindBy(xpath = "//h4[@id='AddNewCustomerModalTitle']")
    private WebElement AddNewCustomerPopup;

    @FindBy(xpath = "(//div[@class='modal-header customer-modal-header']//a//i)[7]")
    private WebElement CloseIconAddNewCustPopup;

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

    @FindBy(xpath = "//h4[contains(text(),'Add New Customer')]/preceding::a[1]//i")
    private WebElement AddNewCustPopupCloseIcon;

    @FindBy(xpath = "(//input[@id='txtSearchProductForPayment'])[1]")
    private WebElement searchandselectcustomertextbox;

    @FindBy(xpath = "(//span[@class='switchery switchery-default'])[6]")
    private WebElement AddNewCustPopupcreditapprovedToogle;

    @FindBy(xpath = "(//span[@class='switchery switchery-default'])[7]")
    private WebElement AddNewCustPopupPaperlessStatementsToogle;

    @FindBy(xpath = "(//span[@class='switchery switchery-default'])[8]")
    private WebElement AddNewCustPopupEmailStatementToogle;

    @FindBy(xpath = "(//span[@class='switchery switchery-default'])[9]")
    private WebElement AddNewCustPopupEnableLoyaltyToogle;

    @FindBy(xpath = "(//span[@class='switchery switchery-default'])[10]")
    private WebElement AddNewCustPopupLateFeeSettingToogle;

    @FindBy(xpath = "(//span[@class='switchery switchery-default'])[11]")
    private WebElement AddNewCustPopupAllowSMSToogle;

    @FindBy(xpath = "(//span[@class='switchery switchery-default'])[12]")
    private WebElement AddNewCustPopupAllowFeedBackEmailToogle;

    @FindBy(xpath = "//a[@id='btnAddNewCustomer']")
    private WebElement AddNewCustAddNewCustomerButton;

    @FindBy(xpath = "(//p[@id='lblCustomerName'])[1]")
    private WebElement CustomerNameOnCCPPage;

    @FindBy(xpath = "(//p[@id='lblCustomerId'])[1]")
    private WebElement CustomerIdOnCCPPage;

    @FindBy(xpath = "(//button[@class='btn btn-danger resetCutomerBtn' or normalize-space()='X'])[2]")
    private WebElement crossIconOn_cashandcarry_paymentpage;


    @FindBy(xpath = "//div[@class='toast-title']")
    private WebElement ErrorToastMsg;

    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement ErrorToastMsgCloseBtn;

    @FindBy(xpath = "//table[@id='tblFinalProducts']")
    private WebElement paymentTableDescription;

    @FindBy(xpath = "(//td[@Class='pad-left'])[2]")
    private WebElement paymentTableRow1Description;

    @FindBy(xpath = "(//tbody//td[@align='right'])[1]")
    private WebElement paymentTableRow1DiscountAmount;

    @FindBy(xpath = "(//tbody//td[@align='right'])[2]")
    private WebElement paymentTableRow1ExtPrice;

    @FindBy(xpath = "//td[@id='tdSubTotalDisplay']")
    private WebElement paymentTableSubTotal;

    @FindBy(xpath = "//td[@id='tdGST']")
    private WebElement paymentTableGST;

    @FindBy(xpath = "//td[@id='tdPST']")
    private WebElement paymentTablePST;

    @FindBy(xpath = "//td[@id='tdTax']")
    private WebElement paymentTableTax;

    @FindBy(xpath = "//h4[@id='tdGrandTotal']")
    private WebElement paymentTableGrandTotal;

    @FindBy(xpath = "//input[@id='txtGivenAmount']")
    private WebElement GivenAmountTextboxField;

    @FindBy(xpath = "//input[@id='txtSearchProductForPayment']")
    private WebElement CustomerSelectionTextboxField;

    @FindBy(xpath = "//ul[@id='ui-id-3']")
    private WebElement customer_autocomplete_values_on_cashandcarry_paymentpage;

    // Last updated on Jan 2025, previous xpath will be //ul[@id='ui-id-2']//li//div
    @FindBy(xpath = "//ul[@id='ui-id-3']//li//div")
    private List<WebElement> ListOfCustomer;

    @FindBy(xpath = "//div[@id='PanelPaymentUserDetails']//button[contains(@class,'resetCutomerBtn')]")
    private WebElement cross_icon_customercancel_button;

    //----------------------Confirmation popup ------------------------------------
    @FindBy(xpath = "//div[@id='PrintOrderNewModal']//div[@class='modal-content']")
    private WebElement PopupOrderconfirmation;

    @FindBy(xpath = "//h3[normalize-space()='Order Confirmation']")
    private WebElement OrderConfirmationPopup;

    @FindBy(xpath = "//label[@id='lblMsg']")
    private WebElement OrderConfirmationMsgAndInvoiceNo;

    @FindBy(xpath = "//label[@id='lblBalance']")
    private WebElement BalanceGivenToCustomerMsg;

    @FindBy(xpath = "//h3[contains(text(),'Order Confirmation')]//following::div[1]//a//i")
    private WebElement OrderConfirmationPopupCloseBtn;

    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement OrderConfirmationToastMsg;

    @FindBy(id = "txtCustEmail")
    private WebElement OrderConfirmationPopupCustEmailTextbox;

    @FindBy(id = "SMSPhoneNo")
    private WebElement OrderConfirmationPopupSMSTextbox;

    @FindBy(id = "btnSendEmailReciept")
    private WebElement SendRecieptBtn;

    //-----------------Check Payment Page----------------------------------------------------

    @FindBy(xpath = "//h3[contains(text(),'Check')]")
    private WebElement CheckPaymentPage;

    @FindBy(xpath = "//a[@id='tabCheck']")
    private WebElement CheckTab;

    @FindBy(xpath = "//input[@id='txtCheckNumber']")
    private WebElement CheckNumberTextboxField;

    @FindBy(xpath = "(//input[@id='txtBankName'])[1]")
    private WebElement BankNameTextboxField;

    @FindBy(xpath = "(//input[@id='txtNameOnCheck'])[1]")
    private WebElement NameOnCheckTextboxField;

    @FindBy(xpath = "//input[@id='txtCheckAmount']")
    private WebElement CheckTabAmountTextboxField;

    //---------------------Split Payment Page---------------------------------------------------
    @FindBy(xpath = "//h3[contains(text(),'Split Payment')]")
    private WebElement SplitPaymentPage;

    @FindBy(xpath = "//span[@id='lblInvoiceNumber']")
    private WebElement SplitPayment_InvoiceNumberLeftTopCorner;

    //---------------------Product Description Table---------------------------------------------

    @FindBy(xpath = "//tbody[@id='tblFinalItem']//tr[1]//td[1]")
    private WebElement paymentTableRow1ProductDescription;

    @FindBy(xpath = "//tbody[@id='tblFinalItem']//tr[2]//td[1]")
    private WebElement paymentTableRow2ProductDescription;

    @FindBy(xpath = "//td[@id='tdSubTotalDisplay']")
    private WebElement TableSubTotalValues;

    @FindBy(xpath = "//td[@id='tdGST']")
    private WebElement TableGSTValues;

    @FindBy(xpath = "//td[@id='tdPST']")
    private WebElement TablePSTValues;

    @FindBy(xpath = "//td[@id='tdTax']")
    private WebElement TableTaxValues;

    @FindBy(xpath = "//td[@id='tdCCFee']")
    private WebElement paymentTableConvFee;

    @FindBy(xpath = "(//td[@Class='clsMPO'])[1]")
    private WebElement paymentTypeRow1;

    @FindBy(xpath = "(//td[@Class='clsMPO'])[2]")
    private WebElement paymentTypeRow2;

    @FindBy(xpath = "(//td[@Class='clsMPO'])[3]")
    private WebElement paymentTypeRow3;

    @FindBy(xpath = "(//td[@Class='clsMPO'])[4]")
    private WebElement paymentTypeRow4;

    @FindBy(xpath = "(//td[@Class='clsMPO'])[5]")
    private WebElement paymentTypeRow5;

    @FindBy(xpath = "(//td[@Class='clsMPO'])[6]")
    private WebElement paymentTypeRow6;

    @FindBy(xpath = "(//td[@Class='clsMPO'])[7]")
    private WebElement paymentTypeRow7;

    @FindBy(xpath = "(//td[@class='clsPaidAmt'])[1]")
    private WebElement paymentTablePaidAmtRow1;

    @FindBy(xpath = "(//td[@class='clsPaidAmt'])[7]")
    private WebElement paymentTablePaidAmtRow7;

    @FindBy(xpath = "(//td[@class='clsPaidAmt'])[2]")
    private WebElement paymentTablePaidAmtRow2;

    @FindBy(xpath = "(//td[@class='clsPaidAmt'])[3]")
    private WebElement paymentTablePaidAmtRow3;

    @FindBy(xpath = "(//td[@class='clsPaidAmt'])[4]")
    private WebElement paymentTablePaidAmtRow4;

    @FindBy(xpath = "(//td[@class='clsPaidAmt'])[5]")
    private WebElement paymentTablePaidAmtRow5;

    @FindBy(xpath = "(//td[@class='clsPaidAmt'])[6]")
    private WebElement paymentTablePaidAmtRow6;

    @FindBy(xpath = "(//tbody[@id='tblPayDetails']//td[3])[1]")
    private WebElement paymentDate_row1;

    @FindBy(xpath = "(//tbody[@id='tblPayDetails']//td[3])[2]")
    private WebElement paymentDate_row2;

    @FindBy(xpath = "(//tbody[@id='tblPayDetails']//td[3])[3]")
    private WebElement paymentDate_row3;

    @FindBy(xpath = "(//tbody[@id='tblPayDetails']//td[3])[4]")
    private WebElement paymentDate_row4;

    @FindBy(xpath = "(//tbody[@id='tblPayDetails']//td[3])[5]")
    private WebElement paymentDate_row5;

    @FindBy(xpath = "(//tbody[@id='tblPayDetails']//td[3])[6]")
    private WebElement paymentDate_row6;


    //----------------------Credit card Payment ---------------------------------------------

    @FindBy(xpath = "//input[@id='paymentDeductFromFile']")
    private WebElement storecredit_checkbox;

    @FindBy(xpath = "//strong[contains(text(), 'Use Store Credit')]")
    private WebElement usestorecredit_label;

    @FindBy(xpath = "//a[@id='tabCC']")
    private WebElement CreditCardTab;

    @FindBy(xpath = "(//span[@class='switchery switchery-default'])[2]")
    private WebElement CreditCardPresentToogleBtn;

    @FindBy(xpath = "(//input[@id='txtFirstName'])[1]")
    private WebElement CreditCardFirstNameTextboxField;

    @FindBy(xpath = "(//input[@id='txtLastName'])[1]")
    private WebElement CreditCardLastNameTextboxField;

    @FindBy(xpath = "(//select[@id='SelectCardOption'])[1]") //Add New Card
    private WebElement CreditCardSelectCardDropdown;

    @FindBy(xpath = "(//input[@id='txtCardNumber'])[1]")
    private WebElement CreditCardNumberTextboxField;

    @FindBy(xpath = "(//input[@id='txtExpiryDate'])[1]")
    private WebElement CreditCardExpiryDateTextboxField;

    @FindBy(xpath = "(//input[@id='txtCVV'])[1]")
    private WebElement CreditCardCVVTextboxField;

    @FindBy(xpath = "(//input[@id='txtZipCode'])[1]")
    private WebElement CreditCardZipCodeTextboxField;

    @FindBy(xpath = "(//input[@id='txtSplitCreditCardPayment'])[1]")
    private WebElement CreditCardPaymentTextboxField;

    @FindBy(xpath = "//select[@id='ddlClerkSplitPayment']")
    private WebElement SplitPaymentCashRegistryDropdown;

    //----------------------------- POH Payment ---------------------------------------------

    @FindBy(xpath = "//a[@id='tabPOH']")
    private WebElement POHPaymentTab;

    @FindBy(xpath = "(//input[@id='txtPOHAmt'])[1]")
    private WebElement POHAmountTextboxField;

    //------------------------- Gift card payment -----------------------------
    @FindBy(xpath = "//a[@id='tabGiftCard']")
    private WebElement GiftCardPaymentTab;

    @FindBy(xpath = "(//input[@id='btnSwipeCardForGiftCard'])[1]")
    private WebElement GiftCardSwipeCardBtn;

    @FindBy(xpath = "(//input[@id='txtCardNumberForGiftCard'])[1]")
    private WebElement GiftCardNumberTextboxField;

    @FindBy(xpath = "//input[@id='txtFirstNameForGiftCard']")
    private WebElement GiftCardCustomerNameTextboxField;

    @FindBy(xpath = "//span[@id='lblSplitedBalanaceAmount']")
    private WebElement splitedBalanceAmountOnTable;

    @FindBy(xpath = "//h4[contains(text(),'Grand Total')]/following::td//h4[@id='tdGrandTotal']")
    private WebElement grandTotal_amount;

    @FindBy(xpath = "(//input[@id='txtPayWithGiftCardAmount'])[1]")
    private WebElement GiftCardPaymentAmtTextboxField;

    @FindBy(xpath = "(//input[@id='txtGiftCardAvailableBalance'])[1]")
    private WebElement GiftCardAvailableBalanceTextboxField;

    @FindBy(xpath = "//button[@id='btnFinish']")
    private WebElement FinishBtn;

    @FindBy(xpath = "//div[@class='sweet-alert showSweetAlert visible']")
    private WebElement AlertPopup;

    @FindBy(xpath = "(//div[@class='sa-icon sa-warning pulseWarning'])[1]")
    private WebElement AlertPopupWarningIcon;

    @FindBy(xpath = "//h2[contains(text(),'This order is still not fully paid.')]")
    private WebElement AlertPopupWarningText;

    @FindBy(xpath = "//button[normalize-space()='Stay']")
    private WebElement AlertPopupStayBtn;

    @FindBy(xpath = "//button[normalize-space()='Leave']")
    private WebElement AlertPopupLeaveBtn;

    @FindBy(xpath = "//span[@id='lblFullPaidNote']")
    private WebElement OrderFullyPaidNote;

    @FindBy(xpath = "//a[@id='btnPhoneOrder']")
    private WebElement ConvertToDelivery;

    // ------------------------------------ Invoice Tab Payment ---------------------------------------
    @FindBy(xpath = "//a[@id='tbInvoice']")
    private WebElement InvoicePaymentTab;

    @FindBy(xpath = "//select[@id='ddlPaymentTerm']")
    private WebElement InvoicePaymentTermDropdown;

    @FindBy(xpath = "//a[@id='btnBackToPanelIntialInfo']")
    private WebElement BackButtonOnCCPpage;

    @FindBy(xpath = "(//a[normalize-space()='POH (F7)'])[1]")
    private WebElement POH_Tab;

    // ==================== Donation Payment Section ====================
    @FindBy(xpath = "(//a[normalize-space()='Donation (F9)'])[1]")
    private WebElement Donation_Tab;

    @FindBy(xpath = "(//label[contains(text(),'EIN Number')])[1]")
    private WebElement EINNumber_Label;

    @FindBy(xpath = "(//input[@id='txtEINNumber'])[1]")
    private WebElement EINNumber_Textbox;

    //================================ Cash and Carry Payment Section Functions ==============================

    /**
     * Verify whether the Cash and Carry Payment page is displayed or not
     *
     * @return If the Cash and Carry Payment page is displayed, return true, otherwise return false
     * @Author Balaji N
     */
    public boolean IsPaymentPageDisplayed() {
        return is_Element_Displayed(PaymentPage, "Cash and Carry Payment Page");
    }

    /**
     * Validate payment description table is displayed or not
     *
     * @return If the payment description table is displayed, return true, otherwise return false
     * @Author Balaji N
     */
    public boolean verifyPaymentDescriptionTableIsDisplayed() {
        return is_Element_Displayed(paymentTableDescription, "Payment Description Table");
    }

    /**
     * Retrieves the payment description value at row 1 on cash and carry payment page
     *
     * @return If the payment description value is exists it returns the displayed payment description as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getRow1PaymentDescription() {
        return get_Element_Text(paymentTableRow1Description, "Payment description value at row 1 on cash and carry payment page");
    }

    /**
     * It retrieves the payment discount amount value at row 1 on cash and carry payment page
     *
     * @return If the payment discount amount value is exists it returns the displayed payment discount amount as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getRow1PaymentDiscountAmount() {
        return get_Element_Text(paymentTableRow1DiscountAmount, "Payment discount amount value at row 1 on cash and carry payment page");
    }

    /**
     * It retrieves the payment ext price value at row 1 on cash and carry payment page
     *
     * @return If the payment ext price value is exists it returns the displayed payment ext price as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getRow1PaymentExtPrice() {
        return get_Element_Text(paymentTableRow1ExtPrice, "Payment ext price value at row 1 on cash and carry payment page");
    }

    /**
     * Get the payment sub total value on cash and carry payment page
     *
     * @return If the payment sub total value is exists it returns the displayed payment sub total as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getPaymentSubTotal() {
        // return paymentTableSubTotal.getText();
        return get_Element_Text(paymentTableSubTotal, "Payment sub total value on cash and carry payment page");
    }

    /**
     * Get the payment GST value on cash and carry payment page
     *
     * @return If the payment GST value is exists it returns the displayed payment GST as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getPaymentGST() {
        // return paymentTableGST.getText();
        return get_Element_Text(paymentTableGST, "Payment GST value on cash and carry payment page");
    }

    /**
     * Get the payment PST value on cash and carry payment page
     *
     * @return If the payment PST value is exists it returns the displayed payment PST as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getPaymentPST() {
        //  return paymentTablePST.getText();
        return get_Element_Text(paymentTablePST, "Payment PST value on cash and carry payment page");
    }

    /**
     * Get the payment tax value on cash and carry payment page
     *
     * @return If the payment tax value is exists it returns the displayed payment tax as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getPaymentTax() {
        // return paymentTableTax.getText();
        return get_Element_Text(paymentTableTax, "Payment Tax value on cash and carry payment page");
    }

    /**
     * Get the payment grand total value on cash and carry payment page
     *
     * @return If the payment grand total value is exists it returns the displayed payment grand total as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getPaymentGrandTotal() {
        try {
            String grandTotal = getElementText(paymentTableGrandTotal, "Grand total value on cash and carry payment page");
            return grandTotal.replace("$", "").trim();
        } catch (Exception e) {
            printError(paymentTableGrandTotal, "Grand total value on cash and carry payment page", "No such element exception", e);
            return "";
        }
    }

    /**
     * Validates whether the actual calculation for grand total is correct, including the convenience fee.
     *
     * @return The expected grand total as a formatted string (rounded to two decimal places).
     * @Author Balaji N
     */
    public String ValidateGrandTotalWithConvFee() {
       /* double subTotal = parseCurrencyValue(TableSubTotalValues.getText());
        double gst = parseCurrencyValue(TableGSTValues.getText());
        double psthstqst = parseCurrencyValue(TablePSTValues.getText());
        double tax = parseCurrencyValue(TableTaxValues.getText());
        double convFee = parseCurrencyValue(paymentTableConvFee.getText());
        double expectedGrandTotal = subTotal + gst + psthstqst + tax + convFee;

        // Format the numbers to two decimal places
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedExpectedGrandTotal = df.format(expectedGrandTotal);
        return formattedExpectedGrandTotal;*/

        try {
            double subTotal = parseCurrencyValue(TableSubTotalValues.getText());
            double gst = parseCurrencyValue(TableGSTValues.getText());
            double pstHstQst = parseCurrencyValue(TablePSTValues.getText());
            double tax = parseCurrencyValue(TableTaxValues.getText());
            double convFee = parseCurrencyValue(paymentTableConvFee.getText());
            double expectedGrandTotal = subTotal + gst + pstHstQst + tax + convFee;

            // Using BigDecimal for precise rounding
            BigDecimal formattedTotal = BigDecimal.valueOf(expectedGrandTotal).setScale(2, RoundingMode.HALF_UP);

            return formattedTotal.toString();
        } catch (Exception e) {
            logger.error("Error while calculating the expected grand total with convenience fee", e);
            return "0.00"; // Return a default value in case of error
        }
    }

    /**
     * Parses a currency value from a string and converts it to a double.
     *
     * @param text The currency string to be parsed.
     * @return The parsed double value, or 0.0 if parsing fails.
     */
    private double parseCurrencyValue(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0.0; // Handling empty/null values
        }

        try {
            String cleanedText = text.replaceAll("[^0-9.]", ""); // Remove non-numeric characters
            return Double.parseDouble(cleanedText);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /**
     * It retrieves the grand total value to be paid on cash and carry payment page
     *
     * @return If the grand total is exist it returns the value of grand total, otherwise it returns null string
     * @Author Balaji N
     */
    public String getGrandTotaltoPay() {
        String grandTotal = paymentTableGrandTotal.getText();
        grandTotal = grandTotal.replace("$", "");
        return grandTotal;
    }

    /**
     * Enters the given amount on cash tab on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void EnterGivenAmount() {
        Double_Click_And_Type(GivenAmountTextboxField, getGrandTotaltoPay(), "Given Amount textbox field - Cash Tab - on Cash and Carry Payment Page");
    }

    /**
     * Enters the given amount textbox field on cash tab on cash and carry payment page
     *
     * @param cashamount
     * @Author Balaji N
     */
    public void EnterGivenAmountOnCashTab(String cashamount) {
        Double_Click_And_Type(GivenAmountTextboxField, cashamount, "Given Amount textbox field - Cash Tab - on Cash and Carry Payment Page");
    }

    /**
     * Verifies whether the given amount textbox field is displayed on cash tab on cash and carry payment page
     *
     * @return If the given amount textbox field value is displayed on cash tab on cash and carry payment page it returns true, otherwise it returns false
     */
    public boolean VerifyGivenAmountOnCashTabTextboxIsDisplayed() {
        return is_Element_Displayed(GivenAmountTextboxField, "Given Amount textbox field on cash and carry payment page");
    }

    /**
     * It retrieves the given amount textbox field value on cash tab on cash and carry payment page
     *
     * @return If the given amount is entered on cash tab on cash and carry payment page it returns the value of given amount, otherwise it returns null
     * @Author Balaji N
     */
    public String getEnteredGivenAmountOnCashTab() {
        return getElementAttribute(GivenAmountTextboxField, "Given Amount textbox field on cash and carry payment page");
    }

    /**
     * Cash Tab on cash and carry payment page
     *
     * @Author Balaji N
     */
    public CashAndCarryPaymentPage ClickCashTab() {
        js_Click(CashTab, "Cash Tab on Cash and Carry Payment Page");
        return this;
    }

    /**
     * It retrieves the customer id on cash and carry payment page
     *
     * @return If the customer id is exist it returns the value of customer id, otherwise it returns null
     * @Author Balaji N
     */
    public String GetCustomerId() {
        // return CustomerId.getText();
        return get_Element_Text(CustomerId, "Customer Id on Cash and Carry Payment Page");
    }

    /**
     * It retrieves the customer name on cash and carry payment page
     *
     * @return If the customer name is exist it returns the value of customer name, otherwise it returns null
     * @Author Balaji N
     */
    public String GetCustomerName() {
        // return CustomerName.getText();
        return get_Element_Text(CustomerName, "Customer Name on Cash and Carry Payment Page");
    }

    /**
     * Process payment button on cash and carry payment page
     *
     * @return
     */
    public void ClickProcessPaymentBtn() {
        logPageLoad("Cash and Carry Payment Page → Process Payment Button field", () -> {
            fluentWait(ProcessPaymentBtn);
            js_Click(ProcessPaymentBtn, "Process Payment Button on Cash and Carry Payment Page");
        });
    }

    /**
     * Process payment button on cash and carry payment page
     *
     * @return If the process payment button is enabled it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean VerifyProcessPaymentButton() {
        return is_Element_Enabled(ProcessPaymentBtn, "Process Payment Button on Cash and Carry Payment Page");
    }

    /**
     * Verify whether the process payment button is disabled or not
     *
     * @return
     */
    public boolean VerifyProcessPaymentDisabled() {
        Highlight_Element(ProcessPaymentBtn, "Process Payment Button on Cash and Carry Payment Page");
        boolean isDisabled = ProcessPaymentBtn.getAttribute("disabled") != null;
        return isDisabled;
    }

    /**
     * Message and Invoice number - Order Confirmation Popup on Cash and Carry Payment Page
     *
     * @return
     */
    public boolean GetOrderConfirmationMsgAndInvoiceNo() {
        return is_Element_Displayed(OrderConfirmationMsgAndInvoiceNo, "Message and Invoice No in Order Confirmation Popup on Cash and Carry Payment Page");
    }

    /**
     * Close button - Order Confirmation Popup on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void ClickOrderConfirmationPopupCloseBtn() {
        Click(OrderConfirmationPopupCloseBtn, "Order Confirmation Popup Close Button on Cash and Carry Payment Page");
    }

    /**
     * Order Confirmation Popup on cash and carry payment page
     *
     * @return If the order confirmation popup is displayed it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean VerifyOrderConfirmationPopup() {
        return is_Element_Displayed(OrderConfirmationPopup, "Order Confirmation Popup on Cash and Carry Payment Page");
    }

    /**
     * It will return invoice number displayed on cash and carry payment page
     *
     * @return If the invoice number is displayed it returns invoice number else it returns null
     * @Author Balaji N
     */
    public String GetInvoiceNumber() {
        String OrderMsg = get_Element_Text(OrderConfirmationMsgAndInvoiceNo, "Message and Invoice No in Order Confirmation Popup on Cash and Carry Payment Page");
        String[] InvoiceNumber = OrderMsg.split(":");
        String InvoiceNo = InvoiceNumber[1].trim();
        return InvoiceNo;
    }

    /**
     * It retrieves the tender price on cash and carry payment page
     *
     * @return If the tender price is exist it returns the value of tender price, otherwise it returns null
     * @Author Balaji N
     */
    public String GetTenderPrice() {
        String RemBal = getElementText(BalanceGivenToCustomerMsg, "Balance Given To Customer Message on Cash and Carry Payment Page");
        String[] RemainingBalance = RemBal.split("\\s+");
        return RemainingBalance[2];
    }

    /**
     * Verify whether the Success toaster message on cash and carry payment page
     *
     * @return
     */
    public boolean SuccessToastMsg() {
        Mouse_Hover(this, OrderConfirmationToastMsg, "Order confirmation toast message");
        return is_Element_Displayed(OrderConfirmationToastMsg, "Order Confirmation Toast Message");
    }

    public String getSuccessToastMsg() {
        return getElementText(OrderConfirmationToastMsg, "Toast message in cash and carry page & payment page");
    }

    /**
     * Verify whether the error toaster message on cash and carry payment page
     *
     * @return If the error toaster message is displayed it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean VerifyErrorToastMsg() {
        return is_Element_Displayed(OrderConfirmationToastMsg, "Order Confirmation Toast Message");
    }

    /**
     * Order confirmation toast message displayed on cash and carry payment page
     *
     * @return
     */
    public String getOrderConfirmationToastMsg() {
        Mouse_Hover(this, OrderConfirmationToastMsg, "Order confirmation toast message");
        return getElementText(OrderConfirmationToastMsg, "Order confirmation toast message in cash and carry payment page");
    }

    /**
     * It retrieves the invoice number displayed on cash and carry payment page
     *
     * @return If the invoice number is exist it returns the value of invoice number, otherwise it returns null
     * @Author Balaji N
     */
    public String getGeneratedInvoiceNumber() {
        String invoiceNo = getElementText(OrderConfirmationToastMsg, "Order confirmation toast message in cash and carry payment page");
        String[] InvoiceNo = invoiceNo.split(":");
        String generatedInvoiceNo = InvoiceNo[1].trim();
        return generatedInvoiceNo;
    }

    /**
     * Verify whether the confirmation popup is displayed
     *
     * @return If the confirmation popup is displayed returns true otherwise returns false
     * @Author Balaji N
     */
    public boolean getConfirmationPopup() {
        return is_Element_Displayed(PopupOrderconfirmation, "Order Confirmation Popup");
    }

    /* *//**
     * Search and select the customer on cash and carry payment page
     *
     * @param customerShortText
     * @param customerFullName
     * @return If the customer is selected it returns the current instance of CashAndCarryPaymentPage otherwise it returns null
     * @Author Balaji N
     *//*
    public CashAndCarryPaymentPage SearchAndSelectCustomer(String customerShortText, String customerFullName) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='txtSearchProductForPayment']")));
            CustomerSelectionTextboxField.clear();
            CustomerSelectionTextboxField.sendKeys(customerShortText);
            delayWithGivenTime(2000);

            if (isElementDisplayed(customer_autocomplete_values_on_cashandcarry_paymentpage)) {
                WebElement customer = getDriver().findElement(By.xpath(
                        "//ul[@id='ui-id-3']//li//div[contains(text(),'" + customerFullName + "')]"
                ));
                js_Click(customer, "Customer Autosuggestion list from select customer textbox field - on cash and carry payment page");
            }
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Customer not found in the dropdown: " + customerFullName, e);
        } catch (ElementNotInteractableException e) {
            throw new RuntimeException("Customer element is not interactable: " + customerFullName, e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout while waiting for customer element: " + customerFullName, e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error in searchAndSelectCustomer: " + customerFullName, e);
        }
        return this;
    }
*/

    /**
     * Search and select the customer on cash and carry payment page
     *
     * @param customerShortText Short text to trigger autosuggestion
     * @param customerFullName  Full customer name to select from suggestions
     * @return If the customer is selected it returns the current instance of CashAndCarryPaymentPage otherwise it returns null
     * @Author Balaji N
     */
    public CashAndCarryPaymentPage SearchAndSelectCustomer(String customerShortText, String customerFullName) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        int retryCount = 0;
        int maxRetries = 3;

        while (retryCount < maxRetries) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='txtSearchProductForPayment']")));
                CustomerSelectionTextboxField.clear();
                CustomerSelectionTextboxField.sendKeys(customerShortText);
                delayWithGivenTime(2000);

                if (isElementDisplayed(customer_autocomplete_values_on_cashandcarry_paymentpage)) {
                    WebElement customer = getDriver().findElement(By.xpath(
                            "(//ul[starts-with(@id,'ui-id') and contains(@class,'ui-autocomplete')]//li//div[contains(text(),'" + customerFullName + "')])[2]"
                    ));

                    js_Click(customer, "Customer Autosuggestion list from select customer textbox field - on cash and carry payment page");
                    break;
                } else {
                    throw new NoSuchElementException("Customer suggestion not found: " + customerFullName);
                }

            } catch (NoSuchElementException | ElementNotInteractableException | TimeoutException e) {
                retryCount++;
                if (retryCount >= maxRetries) {
                    throw new RuntimeException("Failed to select customer after " + maxRetries + " attempts: " + customerFullName, e);
                }
                delayWithGivenTime(1000); // wait before retry
            } catch (Exception e) {
                throw new RuntimeException("Unexpected error in searchAndSelectCustomer: " + customerFullName, e);
            }
        }

        return this;
    }

    public void click_Cancel_Selected_CustomerDetails() {
        js_Click(cross_icon_customercancel_button, "Cancel Selected Customer Details on cash and carry payment page");
    }


    /**
     * Clicks the back button on top right corner on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void ClickBackButtonOnTopRightCorner() {
        js_Click(backBtn, "Back Button on Cash and Carry Payment Page");
    }

    /**
     * It retrieves the customer email from the order confirmation popup
     *
     * @return If the customer email is retrieved it returns the customer email otherwise it returns empty string
     * @Author Balaji N
     */
    public String GetConfirmationPopupCustEmail() {
        return getElementAttribute(OrderConfirmationPopupCustEmailTextbox, "Customer Email textbox field on Order Confirmation Popup");
    }

    /**
     * Clicks the send reciept button on order confirmation popup
     *
     * @Author Balaji N
     */
    public void ClickSendReciptBtnOnOrderConfirmationPopup() {
        js_Click(SendRecieptBtn, "Send Reciept Button on Order Confirmation Popup");
    }

    /**
     * Clicks the check Tab on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void ClickCheckTab() {
        js_Click(CheckTab, "Check Tab on Cash and Carry payment page");
    }

    /**
     * Verify whether the check Tab is displayed on cash and carry payment page
     *
     * @return If the check Tab is displayed returns true otherwise returns false
     * @Author Balaji N
     */
    public boolean VerifyCheckTabDisplayed() {
        return is_Element_Displayed(CheckNumberTextboxField, "Check Number textbox field on cash and carry payment page");
    }

    /**
     * It retrieves the displayed check number from check Tab -     cash and carry payment page
     *
     * @return If check number is displayed on check Tab - cash and carry payment page it returns the value of check number, otherwise it returns null
     * @Author Balaji N
     */
    public String getDisplayedCheckNumber() {
        return getElementAttribute(CheckNumberTextboxField, "Check Number textbox field on cash and carry payment page");
    }

    /**
     * Enters the check number on check Tab - cash and carry payment page
     *
     * @param checknumber
     * @Author Balaji N
     */
    public void EnterCheckNumber(String checknumber) {
        Double_Click_And_Type(CheckNumberTextboxField, checknumber, "Check Number textbox field on cash and carry payment page");
    }

    /**
     * It retrieves the displayed bank name from cash and carry payment page
     *
     * @return If the bank name is displayed on cash and carry payment page it returns the value of bank name, otherwise it returns null
     * @Author Balaji N
     */
    public String getDisplayedBankName() {
        return getElementAttribute(BankNameTextboxField, "Bank Name textbox field on cash and carry payment page");
    }

    /**
     * Enters the bank name on cash and carry payment page
     *
     * @param bankname
     * @Author Balaji N
     */
    public void EnterBankName(String bankname) {
        Double_Click_And_Type(BankNameTextboxField, bankname, "Bank Name textbox field on cash and carry payment page");
    }

    /**
     * It retrieves the displayed check name from cash and carry payment page
     *
     * @return If the check name is displayed on cash and carry payment page it returns the value of check name, otherwise it returns null
     * @Author Balaji N
     */
    public String getDisplayedCheckName() {
        return getElementAttribute(NameOnCheckTextboxField, "Name On Check textbox field on cash and carry payment page");
    }

    /**
     * Enters the check name on cash and carry payment page
     *
     * @param nameoncheck
     * @Author Balaji N
     */
    public void EnterNameOnCheck(String nameoncheck) {
        Double_Click_And_Type(NameOnCheckTextboxField, nameoncheck, "Name On Check textbox field on cash and carry payment page");
    }

    /**
     * It retrieves the invoice number from cash and carry payment page
     *
     * @return If invoice number is exists on cash and carry payment page it returns the value of invoice number, otherwise it returns null
     * @Description: This function will highlight the invoice number on cash and carry payment page and then it retrieves the invoice number value
     * @Author Balaji N
     */
    public String getTopLeftCornerInvNo() {
        return getElementText(SplitPayment_InvoiceNumberLeftTopCorner, "Invoice number on cash and carry payment page at left top corner");
    }

    /**
     * It retrieves the payment table row 1 product description
     *
     * @return
     */
    public String getRow1ProductInTable() {
        return getElementText(paymentTableRow1ProductDescription, "Product description in payment table row 1 on cash and carry payment page");
    }

    /**
     * It retrieves the payment table row 2 product description
     *
     * @return If the product description value is exists it returns the displayed product description as text, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getRow2ProductInTable() {
        return getElementText(paymentTableRow2ProductDescription, "Product description in payment table row 2 on cash and carry payment page");
    }

    /**
     * Clicks the credit card tab on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void ClickCreditCardTab() {
        js_Click(CreditCardTab, "Credit Card Tab on cash and carry payment page");
    }

    /**
     * Verify by default the credit card tab is selected or not
     *
     * @return
     */
    public String VerifyCreditCardTabIsSelected() {
        try {
            Highlight_Element(CreditCardTab, "Credit card tab on cash and carry payment page");
            String CreditCardTabSelected = CreditCardTab.getAttribute("aria-expanded"); // aria-expanded
            return CreditCardTabSelected;
        } catch (Exception e) {
            printError(CreditCardTab, "Credit card tab on cash and carry payment page", "No such element exception", e);
        }
        return "Credit card tab is not selected as default";
    }

    /**
     * Clicks the credit card present toogle button on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void ClickCreditCardPresentToogleBtn() {
        click(CreditCardPresentToogleBtn, "Credit card present toogle button on cash and carry payment page");
    }

    /**
     * Verify the credit card present toogle button is appear or not
     *
     * @return If the credit card present toogle button is appear it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean VerifyCreditCardPresentToogleBtnIsAppear() {
        //  return CreditCardPresentToogleBtn.isDisplayed();
        return isElementDisplayed(CreditCardPresentToogleBtn, "Credit card present toogle button on cash and carry payment page");
    }

    /**
     * Enters the first name on credit card tab on cash and carry payment page
     *
     * @param creditcardfirstname
     * @Author Balaji N
     */
    public void EnterFirstNameOnCreditCardTab(String creditcardfirstname) {
        Double_Click_And_Type(CreditCardFirstNameTextboxField, creditcardfirstname, "FirstName textbox field on Credit Card Tab on cash and carry payment page");
    }

    /**
     * It retrieves the first name on credit card tab
     *
     * @return If the first name is displayed on credit card tab it returns the value of first name, otherwise it returns null
     * @Author Balaji N
     */
    public String getEnteredCreditCardFirstName() {
        // return CreditCardFirstNameTextboxField.getAttribute("value");
        return getElementAttribute(CreditCardFirstNameTextboxField, "First name textbox field value on credit card tab - cash and carry payment page");
    }

    /**
     * Verify the first name on credit card tab is disabled or not
     *
     * @return If the first name on credit card tab is disabled it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean VerifyCreditCardFirstNameIsDisabled() {
        return IsDisabled(CreditCardFirstNameTextboxField);
    }

    /**
     * It enters the last name on credit card tab
     *
     * @param creditcardlastname
     * @Author Balaji N
     */
    public void EnterLastNameOnCreditCardTab(String creditcardlastname) {
        Double_Click_And_Type(CreditCardLastNameTextboxField, creditcardlastname, "LastName textbox field on Credit Card Tab on cash and carry payment page");
    }

    /**
     * It retrieves the last name on credit card tab
     *
     * @return If the last name is displayed on credit card tab it returns the value of last name, otherwise it returns null
     * @Author Balaji N
     */
    public String getEnteredCreditCardLastName() {
        // return CreditCardLastNameTextboxField.getAttribute("value");
        return get_element_attribute(CreditCardLastNameTextboxField, "Last name textbox field value on credit card tab - cash and carry payment page");
    }

    /**
     * Verify the last name on credit card tab is disabled or not
     *
     * @return If the last name on credit card tab is disabled it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean VerifyCreditCardLastNameIsDisabled() {
        return IsDisabled(CreditCardLastNameTextboxField);
    }

    public void SelectCreditCardTypeOnCreditCardTab(String creditcardtype) {
        dropDown(CreditCardSelectCardDropdown, creditcardtype, "VisibleText");
    }

    public String getSelectCreditCardType() {
        return CreditCardLastNameTextboxField.getAttribute("value");
    }

    public boolean VerifySelectCreditCardTypeIsDisabled() {
        return IsDisabled(CreditCardSelectCardDropdown);
    }

    public void EnterCreditCardNumberOnCreditCardTab(String creditcardnumber) {
        DoubleClickAndType(CreditCardNumberTextboxField, creditcardnumber);
    }

    public String getEnterCreditCardNumber() {
        return CreditCardNumberTextboxField.getAttribute("value");
    }

    public boolean VerifyEnterCreditCardNumberIsDisabled() {
        return IsDisabled(CreditCardNumberTextboxField);
    }

    public void EnterCreditCardExpireDateOnCreditCardTab(String creditcardExpireDate) {
        DoubleClickAndType(CreditCardExpiryDateTextboxField, creditcardExpireDate);
    }

    public String getEnterCreditCardExpireDate() {
        return CreditCardExpiryDateTextboxField.getAttribute("value");
    }

    public boolean VerifyEnterCreditCardExpireDateIsDisabled() {
        return IsDisabled(CreditCardExpiryDateTextboxField);
    }

    public void EnterCreditCardCVVOnCreditCardTab(String creditcardCVV) {
        DoubleClickAndType(CreditCardCVVTextboxField, creditcardCVV);
    }

    public String getEnterCreditCardCVV() {
        return CreditCardCVVTextboxField.getAttribute("value");
    }

    public boolean VerifyEnterCreditCardCVVIsDisabled() {
        return IsDisabled(CreditCardCVVTextboxField);
    }

    public void EnterCreditCardZipCodeOnCreditCardTab(String creditcardZipCode) {
        DoubleClickAndType(CreditCardZipCodeTextboxField, creditcardZipCode);
    }

    public String getEnterCreditCardZipCode() {
        return CreditCardZipCodeTextboxField.getAttribute("value");
    }

    public boolean VerifyEnterCreditCardZipCodeIsDisabled() {
        return IsDisabled(CreditCardZipCodeTextboxField);
    }

    public void EnterCreditCardAmountOnCreditCardTab(String creditcardamount) {
        DoubleClickAndType(CreditCardPaymentTextboxField, creditcardamount);
    }

    public String get_creditcard_entered_amount() {
        return CreditCardPaymentTextboxField.getAttribute("value");
    }

    /**
     * Selects the registry on cash tab on cash and carry payment page
     *
     * @param registry
     * @Author Balaji N
     */
    public void SelectRegistryOnCashTab(String registry) {
        drop_Down(SplitPaymentCashRegistryDropdown, registry, "VisibleText", "Registry dropdown field on cash and carry payment page");
    }

    /**
     * It retrieves the selected registry on cash tab on cash and carry payment page
     *
     * @return If the cash registry dropdown value on cash tab return the value else return null
     * @Author Balaji N
     */
    public String get_selected_registry() {
        return get_Selected_Option(SplitPaymentCashRegistryDropdown, "Registry dropdown field on cash and carry payment page");
    }

    /**
     * Enters the check amount on cash and carry payment page
     *
     * @param checkamount
     * @Author Balaji N
     */
    public void EnterAmountOnCheckTab(String checkamount) {
        Double_Click_And_Type(CheckTabAmountTextboxField, checkamount, "Check Amount textbox field on cash and carry payment page");
    }

    /**
     * It retrieves the check amount entered on cash and carry payment page
     *
     * @return If the check amount is entered on cash and carry payment page it returns the value of check amount, otherwise it returns null
     * @Author Balaji N
     */
    public String getEnteredAmountOnCheckTab() {
        return getElementAttribute(CheckTabAmountTextboxField, "Check Amount textbox field value on cash and carry payment page");
    }

    /**
     * Verify whether the payment type on table row 1 is displayed on cash and carry payment page
     * And then it returns the payment type value
     *
     * @return
     * @Author Balaji N
     */
    public String VerifyPaymentTypeOnTableRow1() {
        String paymentType = "";
        if (is_Element_Displayed(paymentTypeRow2, "Payment Type Row 2")) {
            paymentType = getElementText(paymentTypeRow2, "Payment Type Row 2");
        }
        return paymentType;
    }

    public String VerifyPaymentTypeOnTableRow() {
        String paymentType = "";
        if (is_Element_Displayed(paymentTypeRow1, "Payment Type Row 1")) {
            paymentType = getElementText(paymentTypeRow1, "Payment Type Row 1");
        }
        return paymentType;
    }

    /**
     * Verify whether the paid amount label is displayed on cash and carry payment page under payment type row 1
     * And then it returns the paid amount value
     *
     * @return
     * @Author Balaji N
     */
    public String VerifyPaidAmountOnTableRow1() {
        return getElementText(paymentTablePaidAmtRow2, "Paid Amount label at Row 2 on cash and carry payment page");
    }

    /**
     * It retrieves the payment type on table row 2 on cash and carry payment page
     *
     * @return If the payment type on table row 2 value is displayed it return value else return null
     * @Author Balaji N
     */
    public String VerifyPaymentTypeOnTableRow2() {
        is_Element_Displayed(paymentTypeRow3, "Payment Type Row 3 at cash and carry payment page");
        return getElementText(paymentTypeRow3, "Payment Type Row 3 at cash and carry payment page");
    }

    /**
     * Paid amount Row 2 - payment section on cash and carry payment page
     *
     * @return If the paid amount is displayed on row 2 it returns the value of paid amount, otherwise it returns null
     * @Author Balaji N
     */
    public String VerifyPaidAmountOnTableRow2() {
        is_Element_Displayed(paymentTablePaidAmtRow3, "Payment table paid amount row 2");
        return getElementText(paymentTablePaidAmtRow3, "Payment table paid amount row 2");
    }

    /**
     * It retrieves the payment type on payment table row 3 on cash and carry payment page
     *
     * @return
     */
    public String VerifyPaymentTypeOnTableRow3() {
        is_Element_Displayed(paymentTypeRow4, "Payment Type Row 4 at cash and carry payment page");
        return getElementText(paymentTypeRow4, "Payment Type Row 4 at cash and carry payment page");
    }

    public String VerifyPaidAmountOnTableRow3() {
        is_Element_Displayed(paymentTablePaidAmtRow4, "Payment table paid amount row 4");
        return getElementText(paymentTablePaidAmtRow4, "Payment table paid amount row 4");
    }

    public String VerifyPaymentTypeOnTableRow4() {
        return getElementText(paymentTypeRow5, "payment type row 5 at cash and carry payment page");
    }

    public String VerifyPaymentTypeOnTableRow6() {
        return getElementText(paymentTypeRow6, "payment type row 5 at cash and carry payment page");
    }

    public String VerifyPaymentTypeOnTableRow5() {
        is_Element_Displayed(paymentTypeRow5, "Payment Type Row 5 at cash and carry payment page");
        return getElementText(paymentTypeRow5, "payment type row 5 at cash and carry payment page");
    }

    public String VerifyPaidAmountOnTableRow4() {
        is_Element_Displayed(paymentTablePaidAmtRow5, "Payment table paid amount row 5");
        return getElementText(paymentTablePaidAmtRow5, "Payment table paid amount row 5");
    }

    /**
     * It retrieves the paid amount displayed on payment grid table on cash and carry payment page
     *
     * @return If paid amount is displayed it returns the value of amount paid on row 5 from payment table grid otherwise it returns null
     * @Description: This function highlight the paid amount row 5 on payment grid table, and then it retrieve the paid amount on row 5 payment grid table
     * @Author Balaji N
     */
    public String VerifyPaidAmountOnTableRow5() {
        isElementDisplayed(paymentTablePaidAmtRow5, "Payment table paid amount row 5");
        return getElementText(paymentTablePaidAmtRow5, "Payment table paid amount row 5");
    }

    /**
     * It retrieves the paid amount displayed on payment grid table on cash and carry payment page
     *
     * @return If paid amount is displayed it returns the value of amount paid on row 5 from payment table grid otherwise it returns null
     * @Description: This function highlight the paid amount row 5 on payment grid table, and then it retrieve the paid amount on row 5 payment grid table
     * @Author Balaji N
     */
    public String VerifyPaidAmountOnTableRow6() {
        isElementDisplayed(paymentTablePaidAmtRow6, "Payment table paid amount row 5");
        return getElementText(paymentTablePaidAmtRow6, "Payment table paid amount row 5");
    }

    /**
     * It retrieves the payment date on payment details table grid row 1 on cash and carry payment page
     *
     * @return If the payment date value is displayed it return the payment date otherwise it returns null
     * @Description This function highlights the payment date on payment details table grid row 1 and then it retrieves the displayed payment date on cash and carry payment page
     * @Author Balaji N
     */
    public String get_paymentDate_paymentdetailsTable_Row1() {
        HighlightElement(paymentDate_row1);
        return paymentDate_row1.getText();
    }

    /**
     * It retrieves the payment date on payment details table grid row 2 on cash and carry payment page
     *
     * @return If the payment date value is displayed it return the payment date otherwise it returns null
     * @Description This function highlights the payment date on payment details table grid row 2 and then it retrieves the displayed payment date on cash and carry payment page
     * @Author Balaji N
     */
    public String get_paymentDate_paymentdetailsTable_Row2() {
        return getElementText(paymentDate_row2, "payment date row 2 at cash and carry payment page");
    }

    /**
     * It retrieves the payment date on payment details table grid row 3 on cash and carry payment page
     *
     * @return If the payment date value is displayed it return the payment date otherwise it returns null
     * @Description This function highlights the payment date on payment details table grid row 3 and then it retrieves the displayed payment date on cash and carry payment page
     * @Author Balaji N
     */
    public String get_paymentDate_paymentdetailsTable_Row3() {
        return getElementText(paymentDate_row3, "payment date row 3 at cash and carry payment page");
    }

    /**
     * It retrieves the payment date on payment details table grid row 4 on cash and carry payment page
     *
     * @return If the payment date value is displayed it return the payment date otherwise it returns null
     * @Description This function highlights the payment date on payment details table grid row 4 and then it retrieves the displayed payment date on cash and carry payment page
     * @Author Balaji N
     */
    public String get_paymentDate_paymentdetailsTable_Row4() {
        return getElementText(paymentDate_row4, "payment date row 4 at cash and carry payment page");
    }

    /**
     * It retrieves the payment date on payment details table grid row 5 on cash and carry payment page
     *
     * @return If the payment date value is displayed it return the payment date otherwise it returns null
     * @Description This function highlights the payment date on payment details table grid row 5 and then it retrieves the displayed payment date on cash and carry payment page
     * @Author Balaji N
     */
    public String get_paymentDate_paymentdetailsTable_Row5() {
        return getElementText(paymentDate_row5, "payment date row 5 at cash and carry payment page");
    }

    /**
     * Clicks the POH tab on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void ClickOnPOHPaymentTab() {
        js_Click(POHPaymentTab, "POH tab on cash and carry payment page");
    }

    /**
     * Enters the POH amount on POH tab on cash and carry payment page
     *
     * @param pohamount
     * @Author Balaji N
     */
    public void EnterPOHAmountOnPOHPaymentTab(String pohamount) {
        Double_Click_And_Type(POHAmountTextboxField, pohamount, "POH Amount textbox field - on Cash and Carry Payment Page");
    }

    /**
     * It retrieves the POH amount entered on POH tab on cash and carry payment page
     *
     * @return If the POH amount is entered on POH tab on cash and carry payment page it returns the value of POH amount, otherwise it returns null
     * @Author Balaji N
     */
    public String getEnteredPOHAmountOnPOHPaymentTab() {
        return getElementAttribute(POHAmountTextboxField, "POH amount textbox field value on cash and carry payment page");
    }

    /**
     * Clicks the gift card payment tab on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void ClickOnGiftCardPaymentTab() {
        js_Click(GiftCardPaymentTab, "Gift Card Payment Tab on Cash and Carry Payment Page");
    }

//    public String fetchGiftCardFromEmail(String emailSubjectKeyword) {
//        String host = "imap.gmail.com";
//        String username = "hanaposqateam@gmail.com";
//        String password = "bcfburrmktksjckr";
//
//        Properties props = new Properties();
//        props.put("mail.store.protocol", "imaps");
//
//        try {
//            Session session = Session.getInstance(props);
//            Store store = session.getStore();
//            store.connect(host, username, password);
//
//            Folder inbox = store.getFolder("INBOX");
//            inbox.open(Folder.READ_ONLY);
//
//            Message[] messages = inbox.getMessages();
//
//            // Read emails from latest to oldest
//            for (int i = messages.length - 1; i >= 0; i--) {
//                Message message = messages[i];
//                String subject = message.getSubject();
//
//                if (subject != null && subject.contains(emailSubjectKeyword)) {
//                    Object contentObj = message.getContent();
//                    String content = "";
//
//                    if (contentObj instanceof String) {
//                        content = (String) contentObj;
//                    } else if (contentObj instanceof Multipart) {
//                        Multipart multipart = (Multipart) contentObj;
//                        for (int j = 0; j < multipart.getCount(); j++) {
//                            BodyPart part = multipart.getBodyPart(j);
//                            if (part.getContentType().contains("text/plain") || part.getContentType().contains("text/html")) {
//                                content += part.getContent().toString();
//                            }
//                        }
//                    }
//
//                    // Extract 16-digit gift card number
//                    Pattern pattern = Pattern.compile("\\b\\d{16}\\b");
//                    Matcher matcher = pattern.matcher(content);
//
//                    if (matcher.find()) {
//                        String giftCardNumber = matcher.group();
//                        System.out.println("Extracted E-Gift Card Number: " + giftCardNumber);
//                        return giftCardNumber;
//                    }
//                }
//            }
//
//            inbox.close(false);
//            store.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

//    public String fetchGiftCardFromEmail(String emailSubjectKeyword, String expectedOrderNumber) throws Exception {
//        String host = "imap.gmail.com";
//        String username = "hanaposqateam@gmail.com";
//        String password = "bcfburrmktksjckr";
//
//        Properties props = new Properties();
//        props.put("mail.store.protocol", "imaps");
//
//        int maxAttempts = 3;
//        Exception lastException = null;
//
//        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
//            System.out.println("Attempt " + attempt + " to fetch e gift card email...");
//            try {
//                Session session = Session.getInstance(props);
//                Store store = session.getStore();
//                store.connect(host, username, password);
//
//                Folder inbox = store.getFolder("INBOX");
//                inbox.open(Folder.READ_ONLY);
//
//                Message[] messages = inbox.getMessages();
//
//                for (int i = messages.length - 1; i >= 0; i--) {
//                    Message message = messages[i];
//                    String subject = message.getSubject();
//
//                    if (subject != null && subject.contains(emailSubjectKeyword)) {
//                        Object contentObj = message.getContent();
//                        String content = "";
//
//                        if (contentObj instanceof String) {
//                            content = (String) contentObj;
//                        } else if (contentObj instanceof Multipart) {
//                            Multipart multipart = (Multipart) contentObj;
//                            for (int j = 0; j < multipart.getCount(); j++) {
//                                BodyPart part = multipart.getBodyPart(j);
//                                if (part.getContentType().contains("text/plain") || part.getContentType().contains("text/html")) {
//                                    content += part.getContent().toString();
//                                }
//                            }
//                        }
//
//                        // Regex to extract order number (handles invisible/Unicode whitespace)
//                        Pattern orderPattern = Pattern.compile("Order Number[:：]?\\s*(\\d+)");
//                        Matcher orderMatcher = orderPattern.matcher(content);
//
//                        if (orderMatcher.find()) {
//                            String foundOrderNumber = orderMatcher.group(1).trim();
//                            System.out.println("Found order number in email: " + foundOrderNumber);
//
//                            if (foundOrderNumber.equals(expectedOrderNumber)) {
//                                // Extract 16-digit gift card number
//                                Pattern gcPattern = Pattern.compile("\\b\\d{16}\\b");
//                                Matcher gcMatcher = gcPattern.matcher(content);
//
//                                if (gcMatcher.find()) {
//                                    String giftCardNumber = gcMatcher.group();
//                                    System.out.println("Extracted E-Gift Card Number: " + giftCardNumber);
//
//                                    inbox.close(false);
//                                    store.close();
//                                    return giftCardNumber;
//                                } else {
//                                    System.out.println("Gift card number not found in email content.");
//                                }
//                            } else {
//                                System.out.println("Order number does not match expected: " + expectedOrderNumber);
//                            }
//                        } else {
//                            System.out.println("Order number not found in email content.");
//                        }
//                    }
//                }
//
//                inbox.close(false);
//                store.close();
//
//                // Retry delay (10 seconds by default)
//                if (attempt < maxAttempts) {
//                    Thread.sleep(10_000);
//                }
//
//            } catch (Exception e) {
//                lastException = e;
//                System.out.println("Error in attempt " + attempt + ": " + e.getMessage());
//                if (attempt < maxAttempts) {
//                    try {
//                        Thread.sleep(5_000);
//                    } catch (InterruptedException ie) {
//                    }
//                }
//            }
//        }
//
//        throw new Exception("Failed to extract e-gift card number for order: " + expectedOrderNumber, lastException);
//    }

    public String fetchGiftCardFromEmail(String emailSubjectKeyword, String expectedOrderNumber) throws Exception {
        String host = "imap.gmail.com";
        String username = "hanaposqateam@gmail.com";
        String password = "bcfburrmktksjckr";

        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");

        int maxAttempts = 3;
        Exception lastException = null;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            System.out.println("Attempt " + attempt + " to fetch e-gift card email...");

            try {
                Session session = Session.getInstance(props);
                Store store = session.getStore();
                store.connect(host, username, password);

                Folder inbox = store.getFolder("INBOX");
                inbox.open(Folder.READ_ONLY);

                Message[] messages = inbox.getMessages();

                for (int i = messages.length - 1; i >= 0; i--) {
                    Message message = messages[i];
                    String subject = message.getSubject();

                    if (subject != null && subject.contains(emailSubjectKeyword)) {
                        String content = getTextFromMessage(message);

                        if (content == null || content.isEmpty()) continue;

                        // Debug: Print content once if needed
                        // System.out.println("---- Email Content ----\n" + content + "\n-----------------------");

                        Pattern orderPattern = Pattern.compile("(?i)Order\\s*Number\\s*[:：]?\\s*(\\d{6,})");
                        Matcher orderMatcher = orderPattern.matcher(content);

                        boolean orderNumberMatched = false;
                        while (orderMatcher.find()) {
                            String foundOrderNumber = orderMatcher.group(1).trim();
                            System.out.println("✅ Found order number in email: " + foundOrderNumber);

                            if (foundOrderNumber.equals(expectedOrderNumber)) {
                                orderNumberMatched = true;

                                Pattern gcPattern = Pattern.compile("\\b\\d{16}\\b");
                                Matcher gcMatcher = gcPattern.matcher(content);

                                if (gcMatcher.find()) {
                                    String giftCardNumber = gcMatcher.group();
                                    System.out.println("✅ Extracted E-Gift Card Number: " + giftCardNumber);

                                    inbox.close(false);
                                    store.close();
                                    return giftCardNumber;
                                } else {
                                    System.out.println("⚠️ Gift card number not found in email content.");
                                }
                            } else {
                                System.out.println("❌ Found order number doesn't match expected: " + expectedOrderNumber);
                            }
                        }

                        if (!orderNumberMatched) {
                            System.out.println("❌ No matching order number found in this email.");
                        }
                    }
                }

                inbox.close(false);
                store.close();

                if (attempt < maxAttempts) {
                    Thread.sleep(10_000); // Retry after 10 seconds
                }

            } catch (Exception e) {
                lastException = e;
                System.out.println("Error in attempt " + attempt + ": " + e.getMessage());

                if (attempt < maxAttempts) {
                    Thread.sleep(5_000);
                }
            }
        }
        // Final failure after all retries
        throw new Exception(
                "❌ Unable to fetch the e-gift card number for Order [" + expectedOrderNumber + "].\n" +
                        "Possible reasons:\n" +
                        " - The email with subject containing '" + emailSubjectKeyword + "' was not received.\n" +
                        " - The expected order number was not found in any matching email.\n" +
                        " - The gift card number could not be extracted from the email content.\n\n" +
                        "Please verify the order and check if the email has arrived in the inbox.",
                lastException
        );
//        throw new Exception("❌ Failed to extract e-gift card number for order: " + expectedOrderNumber, lastException);
    }

    private String getTextFromMessage(Message message) throws Exception {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("text/html")) {
            return org.jsoup.Jsoup.parse(message.getContent().toString()).text();
        } else if (message.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) message.getContent();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart part = multipart.getBodyPart(i);

                if (part.isMimeType("text/plain")) {
                    result.append(part.getContent());
                } else if (part.isMimeType("text/html")) {
                    result.append(org.jsoup.Jsoup.parse(part.getContent().toString()).text());
                }
            }

            return result.toString();
        }
        return "";
    }


    /**
     * Enters the gift card number on gift card payment tab on cash and carry payment page
     *
     * @param giftcardnumber
     * @Author Balaji N
     */
    public void EnterGiftCardNumberOnGiftCardPaymentTab(String giftcardnumber) {
        Double_Click_And_Type(GiftCardNumberTextboxField, giftcardnumber, "Gift card number textbox field on cash and carry payment page");
        delayWithGivenTime(1000);
        press_Tab_Key();
    }

    /**
     * It retrieves the gift card number entered on gift card payment tab on cash and carry payment page
     *
     * @return If the gift card number is displayed it returns the value of gift card number otherwise it returns null
     * @Author Balaji N
     */
    public String getEnteredGiftCardAmountOnGiftCardPaymentTab() {
        return getElementAttribute(GiftCardNumberTextboxField, "Gift card number textbox field value on gift card tab - cash and carry payment page");
    }

    /**
     * It retreives the customer name on gift card payment tab on cash and carry payment page
     *
     * @return If the customer name is displayed on gift card tab it return the value of customer name, otherwise it returns null
     * @Author Balaji N
     */
    public String getDisplayedCustNameOnGiftCardPaymentTab() {
        return getElementAttribute(GiftCardCustomerNameTextboxField, "Customer name textbox field value on gift card tab - cash and carry payment page");
    }

    /**
     * It retrieves the payment amount on gift card payment tab on cash and carry payment page
     *
     * @return If the payment amount is displayed on gift card tab it return the value of payment amount, otherwise it returns null
     * @Author Balaji N
     */
    public String getDisplayedPaymentAmtOnGiftCardPaymentTab() {
        return getElementAttribute(GiftCardPaymentAmtTextboxField, "Payment amount textbox field value on gift card tab - cash and carry payment page");
    }

    /**
     * It retrieves the balance amount on table on cash and carry payment page
     *
     * @return If the balance amount is displayed on table it return the value of balance amount, otherwise it returns null
     * @Author Balaji N
     */
    public String getTableDisplayedBalanceAmt() {
        return getElementText(splitedBalanceAmountOnTable, "Balance amount on table");
    }

    /**
     * It retrieves the grand total amount on cash and carry payment page
     *
     * @return If the grand total amount is displayed on cash and carry payment page it return the value of grand total amount, otherwise it returns null
     * @Author Balaji N
     */
    public String get_Displayed_Grand_Total_Amount() {
        String amountText = getElementText(grandTotal_amount, "Grand total amount on cash and carry payment page").replace("$", "").trim();
        double amount = Double.parseDouble(amountText);
        return new DecimalFormat("0.0").format(amount);
    }


    /**
     * Enters the payment amount on gift card payment tab  - on cash and carry payment page
     *
     * @param paymentamount
     * @Author Balaji N
     */
    public void EnterPaymentAmtOnGiftCardPaymentTab(String paymentamount) {
        Double_Click_And_Type(GiftCardPaymentAmtTextboxField, paymentamount, "Payment amount on gift card tab in cash and carry payment page");
    }

    /**
     * Clicks the finish button on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void ClickFinishBtnOnCashAndCarryPaymentPage() {
        js_Click(FinishBtn, "Finish button on cash and carry payment page");
    }

    public boolean VerifyAlertPopup() {
        return is_Element_Displayed(AlertPopup, "Alert popup for split payment on cash and carry payment page");
    }

    public boolean VerifyAlertIcon() {
        return is_Element_Displayed(AlertPopupWarningIcon, "Warning icon on alert popup - on cash and carry payment page");
    }

    public String VerifyAlertPopupWarningText() {
        // is_Element_Displayed(AlertPopupWarningText, "Alert Warning text on alert popup - on cash and carry payment page");
        return getElementText(AlertPopupWarningText, "Alert Warning text on alert popup - on cash and carry payment page");
    }

    @FindBy(xpath = "//h2[contains(text(),'Cash And Carry')]")
    private WebElement CashAndCarryPage;

    public boolean VerifyCashAndCarryPage() {
        return is_Element_Displayed(CashAndCarryPage, "Cash and Carry Page");
    }
    /**
     * Clicks the leave button on alert popup on cash and carry payment page
     *
     * @Author Balaji N
     */
    /**
     * Clicks the Leave button on the alert popup in the Cash and Carry payment page.
     * Retries up to 3 times if redirection to Cash and Carry page fails.
     *
     * @Author Balaji N
     */
    public void ClickOnAlertPopupLeaveBtn() {
        int maxRetries = 3;
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                click(AlertPopupLeaveBtn, "Leave button on alert popup - on cash and carry payment page");
                return;
            } catch (TimeoutException | ElementNotInteractableException | StaleElementReferenceException e) {
                System.err.println("[Attempt " + attempt + "] Exception while clicking Leave button: " + e.getClass().getSimpleName());
            } catch (Exception e) {
                printError(AlertPopupLeaveBtn,
                        "Leave button on alert popup - on cash and carry payment page",
                        "Unexpected error while clicking Leave button: " + e.getMessage(), e);
                break;
            }

            delayWithGivenTime(1000);
        }

        throw new RuntimeException("Failed to redirect to Cash and Carry page after clicking Leave button.");
    }


    /**
     * Verify whether the order fully paid note is displayed or not on cash and carry payment page
     *
     * @return If the order fully paid note is displayed, return true, otherwise return false
     * @Author Balaji N
     */
    public boolean VerifyOrderPaidTextAppears() {
        return isElementDisplayed(OrderFullyPaidNote, "Order fully paid note on cash and carry payment page");
    }

    /**
     * Verify the order fully paid note on cash and carry payment page
     *
     * @return If the order fully paid note is displayed on cash and carry payment page it returns the value of order fully paid note otherwise it returns null
     * @Author Balaji N
     */
    public String VerifyFullyPaidMessage() {
        is_Element_Displayed(OrderFullyPaidNote, "Order fully paid note on cash and carry payment page");
        return getElementText(OrderFullyPaidNote, "Order fully paid note on cash and carry payment page");
    }

    /**
     * Clicks on the "Convert to Delivery" button on the Cash and Carry Payment page
     *
     * @Author Balaji N
     */
    public void ClickConvertToDeliveryBtn() {
        js_Click(ConvertToDelivery, "Convert to Delivery button on Cash and Carry Payment page");
    }

    /**
     * Clicks the split payment button on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void ClickSplitPaymentBtn() {
        js_Click(SplitPaymentBtnOnCCPPage, "Split payment button on cash and carry payment page");
    }

    /**
     * Clicks the invoice tab on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void ClickInvoiceTab() {
        js_Click(InvoicePaymentTab, "Invoice tab on cash and carry payment page");
    }

    /**
     * Verify whether the invoice payment term dropdown is displayed or not
     *
     * @return If the invoice payment term dropdown is displayed it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean VerifyInvoiceTermDropdown() {
        return is_Element_Displayed(InvoicePaymentTermDropdown, "Invoice Payment Term Dropdown");
    }

    /**
     * Selects the invoice payment term on invoice tab on cash and carry payment page
     *
     * @param invterms
     * @Author Balaji N
     */
    public void SelectPaymentTermsOnInvDD(String invterms) {
        drop_Down(InvoicePaymentTermDropdown, invterms, "VisibleText", "Invoice Payment Term Dropdown on cash and carry payment page");
    }

    /**
     * Retrieves the selected invoice payment term on invoice tab on cash and carry payment page
     *
     * @return If the invoice payment term is selected on invoice tab on cash and carry payment page it returns the invoice payment term, otherwise it returns null
     * @Author Balaji N
     */
    public String getSelectedPaymentTermsOnInvDD() {
        return get_selected_option(InvoicePaymentTermDropdown, "Invoice Payment Term Dropdown on cash and carry payment page");
    }

    /**
     * Clicks the back button on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void ClickBackButton() {
        js_Click(BackButtonOnCCPpage, "Back button on cash and carry payment page");
    }

    /**
     * Clicks the POH tab on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void ClickPOHTab() {
        js_Click(POH_Tab, "POH tab on cash and carry payment page");
    }

    /**
     * Verify whether the swipe gift card button is displayed or not
     *
     * @return If the swipe gift card button is displayed it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean VerifyIsSwipeGiftCardButtonDisplayed() {
        return is_Element_Displayed(GiftCardSwipeCardBtn, "Swipe Gift Card Button");
    }

    /**
     * Clicks the swipe gift card button on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void ClickSwipeGiftCardButton() {
        js_Click(GiftCardSwipeCardBtn, "Swipe Gift Card Button on cash and carry payment page");
    }

    public void ClickAddNewCustButtonOnCCP() {
        jsClick(AddCustomerBtnOnCCPPage);
    }

    public void ClickAddNewCustCloseIconPopup() {
        jsClick(CloseIconAddNewCustPopup);
    }

    /**
     * Verify whether the add new customer popup is displayed or not on cash and carry payment page
     *
     * @return If the add new customer popup is displayed it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean VerifyAddNewCustPopupIsDisplayed() {
        return is_Element_Displayed(AddNewCustomerPopup, "Add new customer popup on cash and carry payment page");
    }

    /**
     * Clicks the close icon on add new customer popup on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void ClickAddNewCustPopupCloseIcon() {
        js_Click(AddNewCustPopupCloseIcon, "Close Icon on add new customer popup on cash and carry payment page");
    }

    /**
     * It retrieves the search and select customer textbox value on cash and carry payment page
     *
     * @return If the search and select customer textbox value is displayed it returns the value of search and select customer textbox, otherwise it returns null
     * @Author Balaji N
     */
    public String getSearchandselectCustDisplayedData() {
        return get_element_attribute(searchandselectcustomertextbox, "Search and select customer textbox value on cash and carry payment page");
    }

    /**
     * Enters the first name on add new customer popup on cash and carry payment page
     *
     * @param firstname
     * @Author Balaji N
     */
    public void EnterAddNewCustFirstName(String firstname) {
        Double_Click_And_Type(AddNewCustFirstNameTextbox, firstname, "First Name textbox field on add new customer popup on cash and carry payment page");
    }

    /**
     * Retrieves the entered first name on add new customer popup on cash and carry payment page
     *
     * @return If the first name is entered on add new customer popup on cash and carry payment page it returns the value of first name, otherwise it returns null
     * @Author Balaji N
     */
    public String getEnteredaddnewcustfirstname() {
        return getElementAttribute(AddNewCustFirstNameTextbox, "First Name textbox field value on add new customer popup on cash and carry payment page");
    }

    /**
     * Enters the last name on add new customer popup on cash and carry payment page
     *
     * @param lastname
     * @Author Balaji N
     */
    public void EnterAddNewCustLastName(String lastname) {
        Double_Click_And_Type(AddNewCustLastNameTextbox, lastname, "Last Name textbox field on add new customer popup on cash and carry payment page");
    }

    public String getEnteredaddnewcustlastname() {
      /*  HighlightElement(AddNewCustLastNameTextbox);
        return AddNewCustLastNameTextbox.getAttribute("value");*/
        return getElementAttribute(AddNewCustLastNameTextbox, "Last Name textbox field value on add new customer popup on cash and carry payment page");
    }

    public void EnterAddNewCustPhoneNumber(String phonenumber) {
        AddNewCustPhoneNumberTextbox.clear();
        Double_Click_And_Type(AddNewCustPhoneNumberTextbox, phonenumber, "Phone Number textbox field on add new customer popup on cash and carry payment page");
    }

    public String getEnteredaddnewcustphonenumber() {
       /* HighlightElement(AddNewCustPhoneNumberTextbox);
        return AddNewCustPhoneNumberTextbox.getAttribute("value");*/
        return getElementAttribute(AddNewCustPhoneNumberTextbox, "Phone Number textbox field value on add new customer popup on cash and carry payment page");
    }

    public void EnterAddNewCustAltPhoneNumber(String altphonenumber) {
        AddNewCustAltPhoneNumberTextbox.clear();
        Double_Click_And_Type(AddNewCustAltPhoneNumberTextbox, altphonenumber, "Alternative phone number textbox field on add new customer popup in cash and carry payment page");
    }

    public String getEnteredaddnewcustaltphonenumber() {
       /* HighlightElement(AddNewCustAltPhoneNumberTextbox);
        return AddNewCustAltPhoneNumberTextbox.getAttribute("value");*/
        return getElementAttribute(AddNewCustAltPhoneNumberTextbox, "Alternative phone number textbox field value on add new customer popup in cash and carry payment page");
    }

    public void EnterCompanyNameOnAddnewCustPopup(String companyname) {
        Double_Click_And_Type(AddNewCustCompanyNameTextbox, companyname, "Company Name textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcustcompanyname() {
       /* HighlightElement(AddNewCustCompanyNameTextbox);
        return AddNewCustCompanyNameTextbox.getAttribute("value").*/
        return getElementAttribute(AddNewCustCompanyNameTextbox, "Company textbox field on add new customer popup at cash and carry page").trim();
    }

    public void EnterAddress1OnAddnewCustPopup(String address1) {
        Double_Click_And_Type(AddNewCustAddress1Textbox, address1, "Address1 textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcustaddress1() {
       /* HighlightElement(AddNewCustAddress1Textbox);
        return AddNewCustAddress1Textbox.getAttribute("value");*/
        return getElementAttribute(AddNewCustAddress1Textbox, "Address1 textbox field on add new customer popup at cash and carry page");
    }

    public void EnterAddress2OnAddnewCustPopup(String address2) {
        Double_Click_And_Type(AddNewCustAddress2Textbox, address2, "Address2 textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcustaddress2() {
        /*HighlightElement(AddNewCustAddress2Textbox);
        return AddNewCustAddress2Textbox.getAttribute("value");*/
        return getElementAttribute(AddNewCustAddress2Textbox, "Address2 textbox field on add new customer popup at cash and carry page");
    }

    public void EnterZipCodeOnAddnewCustPopup(String zipcode) {
        Double_Click_And_Type(AddNewCustZipCodeTextbox, zipcode, "Zipcode textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcustzipcode() {
        /*HighlightElement(AddNewCustZipCodeTextbox);
        return AddNewCustZipCodeTextbox.getAttribute("value");*/
        return getElementAttribute(AddNewCustZipCodeTextbox, "Zipcode textbox field value on add new customer popup at cash and carry page");
    }

    public void EnterCityOnAddnewCustPopup(String city) {
        Double_Click_And_Type(AddNewCustCityTextbox, city, "City textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcustcity() {
        /*HighlightElement(AddNewCustCityTextbox);
        return AddNewCustCityTextbox.getAttribute("value");*/
        return getElementAttribute(AddNewCustCityTextbox, "City textbox field value on add new customer popup at cash and carry page");
    }


    public void EnterStateOnAddnewCustPopup(String state) {
        Double_Click_And_Type(AddNewCustStateTextbox, state, "State textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcuststate() {
      /*  HighlightElement(AddNewCustStateTextbox);
        return AddNewCustStateTextbox.getAttribute("value");*/
        return getElementAttribute(AddNewCustStateTextbox, "State textbox field value on add new customer popup at cash and carry page");
    }

    public void EnterCountryOnAddnewCustPopup(String country) {
        Double_Click_And_Type(AddNewCustCountryTextbox, country, "Country textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcustcountry() {
        /*HighlightElement(AddNewCustCountryTextbox);
        return AddNewCustCountryTextbox.getAttribute("value");*/
        return getElementAttribute(AddNewCustCountryTextbox, "Country textbox field value on add new customer popup at cash and carry page");
    }

    public void EnterEmailOnAddnewCustPopup(String email) {
        Double_Click_And_Type(AddNewCustEmailTextbox, email, "Email textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcustemail() {
       /* HighlightElement(AddNewCustEmailTextbox);
        return AddNewCustEmailTextbox.getAttribute("value");*/
        return getElementAttribute(AddNewCustEmailTextbox, "Email textbox field value on add new customer popup at cash and carry page");
    }

    public void SelectCustTypeOnAddNewCustPopup(String custtype) {
        drop_Down(AddNewCustTypeDropdown, custtype, "VisibleText", "Customer Type dropdown field on add new customer popup at cash and carry page");
    }

    public String getSelectedCustTypeOnAddNewCustPopup() {
     /*   select = new Select(AddNewCustTypeDropdown);
        return select.getFirstSelectedOption().getText();*/
        return get_selected_option(AddNewCustTypeDropdown, "Customer Type dropdown field value on add new customer popup at cash and carry page");
    }

    public void EnterCustCreditOnAddNewCustPopup(String custcredit) {
        Double_Click_And_Type(AddNewCustCreditTextbox, custcredit, "Customer Credit textbox field on add new customer popup at cash and carry page");
    }

    public String getEnteredaddnewcustcredit() {
        /*HighlightElement(AddNewCustCreditTextbox);
        return AddNewCustCreditTextbox.getAttribute("value");*/
        return getElementAttribute(AddNewCustCreditTextbox, "Customer Credit textbox field value on add new customer popup at cash and carry page");
    }

    public void SelectShopNameOnAddNewCustPopup(String shopname) {
        drop_Down(AddNewCustShopDropdown, shopname, "VisibleText", "Shopname dropdown field on add new customer popup at cash and carry page");
    }

    public String getSelectedShopNameOnAddNewCustPopup() {
        return get_selected_option(AddNewCustShopDropdown, "Shopname dropdown field value on add new customer popup at cash and carry page");
    }

    /**
     * Turn off the 'Credit Approved' toggle on the 'Add New Customer' popup.
     *
     * @Author Balaji N
     */
    public void turn_OFF_Credit_Approved_Toogle_on_Add_New_Cust_Popup() {
        if (!AddNewCustPopupcreditapprovedToogle.getAttribute("style").contains("rgb(223, 223, 223)")) {
            click(AddNewCustPopupcreditapprovedToogle, "Credit Approved Toogle on add new customer popup at cash and carry page");
        }
    }

    /**
     * Verifies if the 'Credit Approved' toggle is OFF on the 'Add New Customer' popup.
     *
     * @return true if the toggle is OFF, false otherwise.
     * @Author Balaji N
     */
    public boolean validate_CreditApproved_Toogle_OFF_State_On_AddNewCustPopup() {
        return AddNewCustPopupcreditapprovedToogle.getAttribute("style").contains("rgb(223, 223, 223)");
    }

    /**
     * Turn off the 'Paperless Statement' toggle on the 'Add New Customer' popup.
     *
     * @Author Balaji N
     */
    public void turn_OFF_PaperlessStatement_Toogle_on_Add_New_Cust_Popup() {
        if (!AddNewCustPopupPaperlessStatementsToogle.getAttribute("style").contains("rgb(223, 223, 223)")) {
            click(AddNewCustPopupPaperlessStatementsToogle, "Paperless Statement toogle on add new customer popup at cash and carry page");
        }
    }

    /**
     * Verifies if the 'Paperless Statement' toggle is OFF on the 'Add New Customer' popup.
     *
     * @return true if the toggle is OFF, false otherwise.
     * @Author Balaji N
     */
    public boolean validate_PaperlessStatement_Toogle_OFF_State_On_AddNewCustPopup() {
        return AddNewCustPopupPaperlessStatementsToogle.getAttribute("style").contains("rgb(223, 223, 223)");
    }

    public void turn_OFF_EmailStatement_Toogle_on_Add_New_Cust_Popup() {
        if (!AddNewCustPopupEmailStatementToogle.getAttribute("style").contains("rgb(223, 223, 223)")) {
            click(AddNewCustPopupEmailStatementToogle, "Email Statement toogle on add new customer popup at cash and carry page");
        }
    }

    public boolean validate_EmailStatement_Toogle_OFF_State_OnAddNewCustPopup() {
        return AddNewCustPopupEmailStatementToogle.getAttribute("style").contains("rgb(223, 223, 223)");
    }

    public void turn_OFF_Enable_Loyalty_Toogle_on_Add_New_Cust_Popup() {
        if (!AddNewCustPopupEnableLoyaltyToogle.getAttribute("style").contains("rgb(223, 223, 223)")) {
            click(AddNewCustPopupEnableLoyaltyToogle, "Enable Loyalty toogle on add new customer popup at cash and carry page");
        }
    }

    public boolean validate_EnableLoyalty_Toogle_OFF_State_OnAddNewCustPopup() {
        return AddNewCustPopupEnableLoyaltyToogle.getAttribute("style").contains("rgb(223, 223, 223)");
    }

    public void turn_OFF_LateFeeSetting_Toogle_on_Add_New_Cust_Popup() {
        if (!AddNewCustPopupLateFeeSettingToogle.getAttribute("style").contains("rgb(223, 223, 223)")) {
            click(AddNewCustPopupLateFeeSettingToogle, "Late Fee Setting toogle on add new customer popup at cash and carry page");
        }
    }

    public boolean validate_LateFeeSetting_Toogle_OFF_State_OnAddNewCustPopup() {
        return AddNewCustPopupLateFeeSettingToogle.getAttribute("style").contains("rgb(223, 223, 223)");
    }

    public boolean ValidateAllowSMSTToogleOnAddNewCustPopup() {
        HighlightElement(AddNewCustPopupAllowSMSToogle); // Red background color
        return AddNewCustPopupAllowSMSToogle.getAttribute("style").contains("rgb(223, 223, 223)");
    }

    public void turn_ON_AllowFeedbackEmailTToogle_on_Add_New_Cust_Popup() {
        if (!AddNewCustPopupAllowFeedBackEmailToogle.getAttribute("style").contains("rgb(197, 231, 244)")) {
            click(AddNewCustPopupAllowFeedBackEmailToogle, "Allow Feedback Email toogle on add new customer popup at cash and carry page");
        }
    }

    public boolean validate_AllowFeedbackEmail_Toogle_ON_State_OnAddNewCustPopup() {
        return AddNewCustPopupAllowFeedBackEmailToogle.getAttribute("style").contains("rgb(197, 231, 244)");
    }

    /**
     * Clicks the add new customer button on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void ClickAddNewCustPopupAddNewCustBtn() {
        js_Click(AddNewCustAddNewCustomerButton, "Add New Customer button on cash and carry page");
    }

    public String getDisplayedCustomerNameOnCCPage() {
        return getElementText(CustomerNameOnCCPPage, "Customer Name label on cash and carry payment page");
    }

    public boolean Verify_CustomerName_AppearsOnCCPage() {
        return isElementDisplayed(CustomerNameOnCCPPage, "Customer Name label on cash and carry payment page");
    }

    public void ClickCrossIconOnAddNewCust() {
        js_Click(crossIconOnAddCustPopup, "Cross icon on add new customer popup at cash and carry page");
    }

    public CashAndCarryPaymentPage EnterCustomerName(String customername) {
        explicitWait(searchandselectcustomertextbox);
        HandleAutocomplete(searchandselectcustomertextbox, customername); //"abish"
        return this;
    }

    /**
     * It retrieves the customer id from cash and carry payment page
     *
     * @return If the customer id is displayed it returns the value of customer id, otherwise it returns null
     * @Author Balaji N
     */
    public String getCustomerIdDisplayed() {
        return getElementText(CustomerIdOnCCPPage, "Customer Id label on cash and carry payment page");
    }

    public boolean Verify_CustomerIdAppears_OnCCPage() {
        //  return CustomerIdOnCCPPage.isDisplayed();
        return isElementDisplayed(CustomerIdOnCCPPage, "Customer Id label on cash and carry payment page");
    }

    /**
     * Clicks the cross icon - under customer section on cash and carry payment page
     */
    public void click_CrossIcon_On_AddCustomerSection_CashAndCarry_PaymentPage() {
        js_Click(crossIconOn_cashandcarry_paymentpage, "Cross icon on cash and carry payment page");
    }

    /**
     * Clicks the donation tab button on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void ClickDonationTab() {
        click(Donation_Tab, "Donation tab button on cash and carry payment page");
    }

    /**
     * Verify whether the EIN number label is displayed or not
     *
     * @return If the EIN number label is displayed it returns true, otherwise it returns false
     * @author Balaji N
     */
    public boolean Verify_EIN_Number_IsDisplayed() {
        return isElementDisplayed(EINNumber_Label, "EIN number label on cash and carry payment page");
    }

    /**
     * Enters the EIN number on cash and carry payment page
     *
     * @param EINNumber
     * @Author Balaji N
     */
    public void EnterEINNumber(String EINNumber) {
        Double_Click_And_Type(EINNumber_Textbox, EINNumber, "EIN number text box field on cash and carry payment page");
    }

    /**
     * It returns the value of the number entered in EIN number textbox field on cash and carry payment page
     *
     * @return If the EIN number is displayed it returns the value of the number entered in EIN number textbox field
     * @Author Balaji N
     */
    public String getEnteredEinNumber() {
        return getElementAttribute(EINNumber_Textbox, "EIN number textbox field value on cash and carry payment page");
    }


    /**
     * Verify whether the store credit checkbox is displayed or not
     *
     * @return If the store credit checkbox is displayed then it returns true otherwise it returns false
     * @Author Balaji N
     */
    public boolean verify_Use_Store_Credit_Label_IsDisplayed() {
        return isElementDisplayed(usestorecredit_label, "Store Credit label on cash and carry payment page");
    }

    /**
     * Clicks the store credit label on cash and carry payment page
     *
     * @Author Balaji N
     */
    public void Click_StoreCredit_CheckBox() {
        js_Click(usestorecredit_label, "Store Credit label on cash and carry payment page");
    }

    /**
     * Get the gift card current balance value on cash and carry payment page
     *
     * @return If the gift card current balance is displayed it returns the value of gift card current balance
     * @Author Balaji N
     */
    public String get_Gift_Card_Current_Balance() {
        return get_element_attribute(GiftCardAvailableBalanceTextboxField, "Gift Card Current Balance textbox field on cash and carry payment page");
    }

}
