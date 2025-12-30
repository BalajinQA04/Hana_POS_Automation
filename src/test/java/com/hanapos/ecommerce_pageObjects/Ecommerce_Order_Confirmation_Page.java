package com.hanapos.ecommerce_pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Ecommerce Order Confirmation Page Class in the bestflorist pos application - bestflorist ecommerce website
 *
 * @Author Balaji N
 */
public class Ecommerce_Order_Confirmation_Page extends TestBaseClass {
    public Ecommerce_Order_Confirmation_Page() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[1]//p)[1]")
    private WebElement product_row1;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[1]//p)[3]")
    private WebElement product_row2;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[2])[1]")
    private WebElement quantity_row1;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[2])[2]")
    private WebElement quantity_row2;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[3])[1]")
    private WebElement unit_price_row1;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[3])[2]")
    private WebElement unit_price_row2;

    @FindBy(xpath = "//table[@id='printTable']//tr[3]//p[1]")
    private WebElement expedited_delivery_fee_label;

    @FindBy(xpath = "//table[@id='printTable']//tr[3]//p[1]/following::td[3]")
    private WebElement expedited_delivery_fee_value;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[4])[1]")
    private WebElement total_price_row1;

    @FindBy(xpath = "(//table[@id='printTable']//tbody//tr//td[4])[2]")
    private WebElement total_price_row2;

    @FindBy(xpath = "//td//span[text()='Product Cost']/following::td[1]")
    private WebElement product_cost;

    @FindBy(xpath = "//td//span[text()='Delivery Fee']/following::td[1]")
    private WebElement delivery_fee;

    @FindBy(xpath = "//td//span[text()='Tax']/following::td[1]")
    private WebElement tax;

    @FindBy(xpath = "//span[text()='Processing Fees']/following::td[1]")
    private WebElement processing_fees;

    @FindBy(xpath = "//td//span[text()='Total']/following::td[1]")
    private WebElement total_product_cost;

    @FindBy(xpath = "//h3[text()='Customer Information']/following::td[1]")
    private WebElement customer_information;

    @FindBy(xpath = "//h3[text()='Delivery Information']/following::td[1]")
    private WebElement delivery_information;

    @FindBy(xpath = "//h3[text()='Payment Information']/following::td[2]")
    private WebElement payment_type;

    @FindBy(xpath = "//h3[text()='Payment Information']/following::td[4]")
    private WebElement payment_card_number;

    @FindBy(xpath = "//h5[text()='Comments/Special Instructions']/following-sibling::p")
    private WebElement commentsOrspecialInstructions;


    public double Calculate_Total_Cost() {
        double Product_cost = Double.parseDouble(product_cost.getText().replace("$", "").trim());
        double Delivery_fee = Double.parseDouble(delivery_fee.getText().replace("$", "").trim());
        double Tax = Double.parseDouble(tax.getText().replace("$", "").trim());
        // double Total_product_cost = Double.parseDouble(total_product_cost.getText().replace("$", "").trim());
        double Total_cost = Product_cost + Delivery_fee + Tax;
        System.out.println("Product cost is " + Product_cost);
        System.out.println("Delivery fee is " + Delivery_fee);
        System.out.println("Tax is " + Tax);
        System.out.println("Total cost of order is " + Total_cost);
        return Total_cost;
    }

    public double Calculate_Total_Cost_With_Processing_Fee() {
        double Product_cost = Double.parseDouble(product_cost.getText().replace("$", "").trim());
        double Delivery_fee = Double.parseDouble(delivery_fee.getText().replace("$", "").trim());
        double Tax = Double.parseDouble(tax.getText().replace("$", "").trim());
        // double Total_product_cost = Double.parseDouble(total_product_cost.getText().replace("$", "").trim());
        double processing_fee = Double.parseDouble(processing_fees.getText().replace("$", "").trim());
        double Total_cost = Product_cost + Delivery_fee + Tax + processing_fee;
        System.out.println("Product cost is " + Product_cost);
        System.out.println("Delivery fee is " + Delivery_fee);
        System.out.println("Tax is " + Tax);
        System.out.println("Total cost of order is " + Total_cost);
        return Total_cost;
    }

    public double Expected_Total_Cost_Without_Processing_Fee() {
        double Product_cost = Double.parseDouble(product_cost.getText().replace("$", "").trim());
        double Delivery_fee = Double.parseDouble(delivery_fee.getText().replace("$", "").trim());
        double Tax = Double.parseDouble(tax.getText().replace("$", "").trim());
        // double Total_product_cost = Double.parseDouble(total_product_cost.getText().replace("$", "").trim());
        //  double processing_fee = Double.parseDouble(processing_fees.getText().replace("$", "").trim());
        double Total_cost = Product_cost + Delivery_fee + Tax;
        System.out.println("Product cost is " + Product_cost);
        System.out.println("Delivery fee is " + Delivery_fee);
        System.out.println("Tax is " + Tax);
        System.out.println("Total cost of order is " + Total_cost);
        return Total_cost;
    }


    // Getter method for Product Row 1
    public String getProductRow1Text() {
        wait_For_Page_To_Be_Stable(getDriver());
        return getElementText(product_row1, "Product Row 1 on Order Confirmation Page");
    }

    // Getter method for Product Row 2
    public String getProductRow2Text() {
        HighlightElement(product_row2);
        return product_row2.getText();
    }

    // Getter method for Quantity Row 1
    public String getQuantityRow1Text() {
        HighlightElement(quantity_row1);
        return quantity_row1.getText();
    }

    // Getter method for Quantity Row 2
    public String getQuantityRow2Text() {
        HighlightElement(quantity_row2);
        return quantity_row2.getText();
    }

    // Getter method for Unit Price Row 1
    public String getUnitPriceRow1Text() {
        HighlightElement(unit_price_row1);
        return unit_price_row1.getText();
    }

    // Getter method for Unit Price Row 2
    public String getUnitPriceRow2Text() {
        HighlightElement(unit_price_row2);
        return unit_price_row2.getText();
    }

    // Getter method for Total Price Row 1
    public String getTotalPriceRow1Text() {
        HighlightElement(total_price_row1);
        return total_price_row1.getText();
    }

    // Getter method for Total Price Row 2
    public String getTotalPriceRow2Text() {
        HighlightElement(total_price_row2);
        return total_price_row2.getText();
    }

    // Getter method for Product Cost
    public String getProductCostText() {
        HighlightElement(product_cost);
        return product_cost.getText();
    }

    // Getter method for Delivery Fee
    public String getDeliveryFeeText() {
        HighlightElement(delivery_fee);
        return delivery_fee.getText();
    }

    // Getter method for Tax
    public String getTaxText() {
        HighlightElement(tax);
        return tax.getText();
    }

    // Getter method for Total Product Cost
    public String getTotalProductCostText() {
        HighlightElement(total_product_cost);
        return total_product_cost.getText();
    }

    // Getter method for Customer Information
    public String getCustomerInformationText() {
        /*HighlightElement(customer_information);
        return customer_information.getText();*/
        return getElementText(customer_information, "Customer Information on Order Confirmation Page");
    }

    // Getter method for Delivery Information
    public String getDeliveryInformationText() {
      /*  HighlightElement(delivery_information);
        return delivery_information.getText();*/
        return getElementText(delivery_information, "Delivery Information on Order Confirmation Page");
    }

    // Getter method for Payment Type
    public String getPaymentTypeText() {
        HighlightElement(payment_type);
        return payment_type.getText();
    }

    // Getter method for Payment Card Number
    public String getPaymentCardNumberText() {
        HighlightElement(payment_card_number);
        return payment_card_number.getText();
    }

    // Getter method for Comments/Special Instructions
    public String getCommentsOrSpecialInstructionsText() {
        HighlightElement(commentsOrspecialInstructions);
        return commentsOrspecialInstructions.getText();
    }

    public boolean isExpeditedDeliveryFeeDisplayed() {
        return is_Element_Displayed(expedited_delivery_fee_label, "Expedited Delivery Fee label");
    }

    public String getExpeditedDeliveryFeeText() {
        return getElementText(expedited_delivery_fee_value, "Expedited Delivery Fee label on checkout page");
    }

}
