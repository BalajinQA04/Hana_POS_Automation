package com.hanapos.pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.tracing.opentelemetry.SeleniumSpanExporter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Order Confirmation Page Class
 */
public class Order_Confirmation_Page extends TestBaseClass {
    public Order_Confirmation_Page() {
        PageFactory.initElements(getDriver(), this);
    }

    //===================================== Order Confirmation Page WebElements ===============================
    @FindBy(xpath = "//a[@id='odrinvoicelnk']")
    private WebElement OrderInvoiceLink;

    @FindBy(xpath = "//div[@class='OrderreviewPage']//h1")
    private WebElement OrderConfirmationPage;

    @FindBy(id = "customerFName")
    private WebElement CustomerFirstName;

    @FindBy(id = "customerLName")
    private WebElement CustomerLastName;

    @FindBy(id = "customerEmail")
    private WebElement CustomerEmail;

    @FindBy(id = "customerPhone1")
    private WebElement CustomerPhone1;

    @FindBy(id = "customerPhone2")
    private WebElement CustomerPhone2;

    @FindBy(xpath = "//span[@id='customerAddress1']")
    private WebElement CustomerAddress;

    @FindBy(id = "customerCity")
    private WebElement CustomerCity;

    @FindBy(id = "customerState")
    private WebElement CustomerState;

    @FindBy(id = "customerZip")
    private WebElement CustomerZip;

    @FindBy(id = "recipentFName")
    private WebElement RecipientFirstName;

    @FindBy(id = "recipentLName")
    private WebElement RecipientLastName;

    @FindBy(id = "recipentPhone1")
    private WebElement RecipientPhone1;

    @FindBy(id = "recipentPhone2")
    private WebElement RecipientPhone2;

    @FindBy(id = "recipentAddress1")
    private WebElement RecipientAddress1;

    @FindBy(id = "recipentAddress2")
    private WebElement RecipientAddress2;

    @FindBy(id = "recipentCity")
    private WebElement RecipientCity;

    @FindBy(id = "recipentState")
    private WebElement RecipientState;

    @FindBy(id = "recipentZip")
    private WebElement RecipientZip;

    @FindBy(id = "btnSubmit")
    private WebElement SubmitButton;

    @FindBy(xpath = "//td[@class='text-wrap-cms text-wrap-cms-small']//div")
    private WebElement itemcode1;

    @FindBy(xpath = "//td[@class='text-wrap-cms text-wrap-cms-small']//small")
    private WebElement itemdescription1;

    @FindBy(xpath = "//tbody[@id='tbodyitems']//td[2]")
    private WebElement qty1;

    @FindBy(xpath = "//tbody[@id='tbodyitems']//td[3]")
    private WebElement unitprice1;

    @FindBy(xpath = "//td//span[@id='orderpaymentType']")
    private WebElement PaymentType;

    @FindBy(xpath = "//tbody[@id='tbodyitems']//td[4]")
    private WebElement taxable;

    @FindBy(xpath = "(//tbody[@id='tbodyitems']//td[4])[2]")
    private WebElement taxable2;

    @FindBy(xpath = "//span[@id='orderinvoice1']")
    private WebElement invoiceNumber_on_Order_Confirmationpage;

    @FindBy(xpath = "//table[@class='table invoice-total no-padding']//tr[11]//td[2]//span")
    private WebElement ccNumber_on_Order_Confirmationpage;

    //====================================== Order Confirmation Page Functions ================================

    /**
     * Validates whether the order confirmation page is displayed or not
     *
     * @return If Order confirmation page is displayed it returns true otherwise it returns false
     * @Description: This function highlights the elements and validates whether the order confirmation page is displayed
     * @Author Balaji N
     */
    public boolean VerifyOrderConfirmationPage() {
       /* try {
            return isElementDisplayed(OrderConfirmationPage, "Order Confirmation Header - (Order Created Successfully Label) - on Order Confirmation Page");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Place Order on Order Entry - Phone Order Page is Unsuccessful or not placed" + e);
        }*/
        int retries = 2;
        By locator = By.xpath("//div[@class='OrderreviewPage']//h1"); // Replace with actual locator of OrderConfirmationPage

        while (retries > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(90));
                WebElement confirmationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                return isElementDisplayed(confirmationElement, "Order Confirmation Header - (Order Created Successfully Label) - on Order Confirmation Page");

            } catch (StaleElementReferenceException | TimeoutException e) {
                System.out.println("Retrying due to exception: " + e.getClass().getSimpleName());
                retries--;
            } catch (NoSuchElementException e) {
                throw new RuntimeException("Place Order on Order Entry - Phone Order Page is Unsuccessful or not placed. " + e.getMessage());
            }
        }

        return false; // After all retries, the element was not found/stable
    }

    /**
     * It retrieves the customer first name from the order confirmation page
     *
     * @return It returns the customer first name displayed on the order confirmation page
     * @Author Balaji N
     */
    public String getCustomerFname() {
        return getElementText(CustomerFirstName, "Customer First Name label value on Order Confirmation Page");
    }

    /**
     * It retrieves the customer last name from the order confirmation page
     *
     * @return It returns the customer last name displayed on the order confirmation page
     * @Author Balaji N
     */
    public String getCustomerLname() {
        return getElementText(CustomerLastName, "Customer Last Name label value on Order Confirmation Page");
    }

    /**
     * Retrieves the customer email from the order confirmation page
     *
     * @return If the customer email is displayed, it returns the customer email; otherwise, it returns null
     * @Author Balaji N
     */
    public String getCustomerEmail() {
       /* HighlightElement(CustomerEmail);
        return CustomerEmail.getText();*/
        return getElementText(CustomerEmail, "Customer Email label value on Order Confirmation Page");
    }

    /**
     * Retrieves the customer phone number from the order confirmation page
     *
     * @return If the customer phone number is displayed, it returns the customer phone number; otherwise, it returns null
     * @Author Balaji N
     */
    public String getCustomerPhone1() {
       /* HighlightElement(CustomerPhone1);
        return CustomerPhone1.getText();*/
        return getElementText(CustomerPhone1, "Customer Phone 1 label value on Order Confirmation Page");
    }

    /**
     * Retrieves the customer phone number 2 from the order confirmation page
     *
     * @return If the customer phone number 2 is displayed, it returns the customer phone number 2; otherwise, it returns null
     * @Author Balaji N
     */
    public String getCustomerPhone2() {
        /*HighlightElement(CustomerPhone2);
        return CustomerPhone2.getText();*/
        return getElementText(CustomerPhone2, "Customer Phone 2 label value on Order Confirmation Page");
    }

    /**
     * Retrieves the customer address from the order confirmation page
     *
     * @return If the customer address is displayed, it returns the customer address; otherwise, it returns null
     * @Author Balaji N
     */
    public String getCustomerAddress() {
       /* HighlightElement(CustomerAddress);
        return CustomerAddress.getText();*/
        return getElementText(CustomerAddress, "Customer Address label value on Order Confirmation Page");
    }

    /**
     * Retrieves the customer city from the order confirmation page
     *
     * @return If the customer city is displayed, it returns the customer city; otherwise, it returns null
     * @Author Balaji N
     */
    public String getCustomerCity() {
        /*HighlightElement(CustomerCity);
        return CustomerCity.getText();*/
        return getElementText(CustomerCity, "Customer City label value on Order Confirmation Page");
    }

    /**
     * Retrieves the customer state from the order confirmation page
     *
     * @return If the customer state is displayed, it returns the customer state; otherwise, it returns null
     * @Author Balaji N
     */
    public String getCustomerState() {
        /*HighlightElement(CustomerState);
        return CustomerState.getText();*/
        return getElementText(CustomerState, "Customer State label value on Order Confirmation Page");
    }

    /**
     * Retrieves the customer zipcode from the order confirmation page
     *
     * @return If the customer zipcode is displayed, it returns the customer zipcode; otherwise, it returns null
     * @Author Balaji N
     */
    public String getCustomerZip() {
        /*HighlightElement(CustomerZip);
        return CustomerZip.getText();*/
        return getElementText(CustomerZip, "Customer Zipcode label value on Order Confirmation Page");
    }

    /**
     * Retrieves the recipient first name from the order confirmation page
     *
     * @return If the recipient first name is displayed, it returns the recipient first name; otherwise, it returns null
     * @Author Balaji N
     */
    public String getRecipientFname() {
        /*HighlightElement(RecipientFirstName);
        return RecipientFirstName.getText();*/
        return getElementText(RecipientFirstName, "Recipient First Name label value on Order Confirmation Page");
    }

    /**
     * Retrieves the recipient last name from the order confirmation page
     *
     * @return If the recipient last name is displayed, it returns the recipient last name; otherwise, it returns null
     * @Author Balaji N
     */
    public String getRecipientLname() {
       /* HighlightElement(RecipientLastName);
        return RecipientLastName.getText();*/
        return getElementText(RecipientLastName, "Recipient Last Name label value on Order Confirmation Page");
    }

    /**
     * Retrieves the recipient phone number from the order confirmation page
     *
     * @return If the recipient phone number is displayed, it returns the recipient phone number; otherwise, it returns null
     * @Author Balaji N
     */
    public String getRecipientPhone1() {
        /*HighlightElement(RecipientPhone1);
        return RecipientPhone1.getText();*/
        return getElementText(RecipientPhone1, "Recipient Phone 1 label value on Order Confirmation Page");
    }

    /**
     * Retrieves the recipient phone number 2 from the order confirmation page
     *
     * @return If the recipient phone number 2 is displayed, it returns the recipient phone number 2; otherwise, it returns null
     * @Author Balaji N
     */
    public String getRecipientPhone2() {
       /* HighlightElement(RecipientPhone2);
        return RecipientPhone2.getText();*/
        return getElementText(RecipientPhone2, "Recipient Phone 2 label value on Order Confirmation Page");
    }

    /**
     * Retrieves the recipient address from the order confirmation page
     *
     * @return If the recipient address is displayed, it returns the recipient address; otherwise, it returns null
     * @Author Balaji N
     */
    public String getRecipientAddress1() {
       /* HighlightElement(RecipientAddress1);
        return RecipientAddress1.getText();*/
        return getElementText(RecipientAddress1, "Recipient Address 1 label value on Order Confirmation Page");
    }

    /**
     * Retrieves the recipient address 2 from the order confirmation page
     *
     * @return If the recipient address 2 is displayed, it returns the recipient address 2; otherwise, it returns null
     * @Author Balaji N
     */
    public String getRecipientAddress2() {
       /* HighlightElement(RecipientAddress2);
        return RecipientAddress2.getText();*/
        return getElementText(RecipientAddress2, "Recipient Address 2 label value on Order Confirmation Page");
    }

    /**
     * Retrieves the recipient city from the order confirmation page
     *
     * @return If the recipient city is displayed, it returns the recipient city; otherwise, it returns null
     * @Author Balaji N
     */
    public String getRecipientCity() {
       /* HighlightElement(RecipientCity);
        return RecipientCity.getText();*/
        return getElementText(RecipientCity, "Recipient City label value on Order Confirmation Page");
    }

    /**
     * Retrieves the recipient state from the order confirmation page
     *
     * @return If the recipient state is displayed, it returns the recipient state; otherwise, it returns null
     * @Author Balaji N
     */
    public String getRecipientState() {
      /*  HighlightElement(RecipientState);
        return RecipientState.getText();*/
        return getElementText(RecipientState, "Recipient State label value on Order Confirmation Page");
    }

    /**
     * Retrieves the recipient zipcode from the order confirmation page
     *
     * @return If the recipient zipcode is displayed, it returns the recipient zipcode; otherwise, it returns null
     * @Author Balaji N
     */
    public String getRecipientZip() {
       /* HighlightElement(RecipientZip);
        return RecipientZip.getText();*/
        return getElementText(RecipientZip, "Recipient Zipcode label value on Order Confirmation Page");
    }

    /**
     * Retrieves the item code 1 from the order confirmation page
     *
     * @return If the item code 1 is displayed, it returns the item code 1; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_itemcode1() {
       /* HighlightElement(itemcode1);
        return itemcode1.getText();*/
        return getElementText(itemcode1, "Item Code 1 label value on Order Confirmation Page");
    }

    /**
     * Retrieves the item description 1 from the order confirmation page
     *
     * @return If the item description 1 is displayed, it returns the item description 1; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_itemdescription1() {
       /* HighlightElement(itemdescription1);
        return itemdescription1.getText();*/
        return getElementText(itemdescription1, "Item Description 1 label value on Order Confirmation Page");
    }

    /**
     * Retrieves the item quantity 1 from the order confirmation page
     *
     * @return If the item quantity 1 is displayed, it returns the item quantity 1; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_itemqty1() {
//        HighlightElement(qty1);
//        return qty1.getText();
        return getElementText(qty1, "Item Quantity 1 label value on Order Confirmation Page");
    }

    /**
     * Retrieves the item unit price 1 from the order confirmation page
     *
     * @return If the item unit price 1 is displayed, it returns the item unit price 1; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_itemunitprice1() {
        /*HighlightElement(unitprice1);
        return unitprice1.getText();*/
        return getElementText(unitprice1, "Item Unit Price 1 label value on Order Confirmation Page");
    }

    /**
     * Retrieves the payment type from the order confirmation page
     *
     * @return If the payment type is displayed, it returns the payment type; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_PaymentType() {
        return getElementText(PaymentType, "Payment Type label value on Order Confirmation Page");
    }

    /**
     * Retrieves the taxable 1 value from the order confirmation page
     *
     * @return If the taxable 1 is displayed, it returns the taxable; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Taxable() {
        return get_Element_Text(taxable, "Taxable 1 label value on Order Confirmation Page");
    }

    /**
     * Retrieves the taxable 2 value from the order confirmation page
     *
     * @return If the taxable 2 is displayed, it returns the taxable; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_Taxable2() {
        return getElementText(taxable2, "Taxable 2 label value on Order Confirmation Page");
    }

    /**
     * Retrieves the invoice number from the order confirmation page
     *
     * @return If the invoice number is displayed, it returns the invoice number; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_InvoiceNumber() {
        return get_Element_Text(invoiceNumber_on_Order_Confirmationpage, "Invoice Number label value on Order Confirmation Page");
    }

    /**
     * It retrieves the credit card last 4 number from the order confirmation page
     *
     * @return If the credit card last 4 number is displayed on the order confirmation page it returns the credit card last 4 number; otherwise, it returns null
     * @Author Balaji N
     */
    public String get_CC_Number_OrderConfirmationPage() {
        return getElementText(ccNumber_on_Order_Confirmationpage, "Credit Card Number label value on Order Confirmation Page");
    }

    /**
     * Clicks the invoice number link on the order confirmation page
     *
     * @Description This function clicks the invoice number link on the order confirmation page
     * @Author Balaji N
     */
    public void click_orderInvoiceLink() {
        js_Click(OrderInvoiceLink, "Invoice Number Link on Order Confirmation Page");
    }

    /**
     * It retrieves the invoice number from the order confirmation page
     *
     * @return If the invoice number is displayed
     * @Author Balaji N
     */
    public String get_invoiceNumber_on_OrderConfirmation_Page() {
        try {
            return get_Element_Text(OrderInvoiceLink, "Invoice Number HyperLink on Order Confirmation Page");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Invoice Number hyperlink is not found on Order Confirmation Page", e);
        }
    }
}
