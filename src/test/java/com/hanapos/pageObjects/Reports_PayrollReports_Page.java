package com.hanapos.pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Reports_PayrollReports_Page extends TestBaseClass {
    public Reports_PayrollReports_Page() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(id = "DeliveryFromDate")
    private WebElement payroll_report_delivery_from_date;

    @FindBy(id = "DeliveryToDate")
    private WebElement payroll_report_delivery_to_date;

    @FindBy(xpath = "//div[@id='divHeader']/div/div/h2")
    private WebElement payroll_report_header_label;

    @FindBy(id = "seachData")
    private WebElement search_button;

    @FindBy(xpath = "//div[@class='k-grid-content k-auto-scrollable gridscrollershow']/table/tbody/tr")
    private List<WebElement> delivery_review_row_lists;

    @FindBy(xpath = "//th//a[normalize-space()='Invoice']")
    private WebElement invoice_column_header;


    // =============================== Report Methods ============================= //

    /**
     * This method verifies whether the payroll report page is displayed
     *
     * @return : true if the payroll page is displayed else false
     * @Author: Sakrateesh R
     */
    public boolean verify_payroll_report_page_ISDisplayed() {
        return is_Element_Displayed(payroll_report_header_label, "payroll report header label");
    }

    /**
     * This method is used to enter the Delivery date in the payroll delivery from date.
     *
     * @param Date
     * @Author: Sakrateesh R
     */
    public void Enter_Delivery_From_Date(String Date) {
        clearAndType(payroll_report_delivery_from_date, Date);
    }

    /**
     * This method is used to enter the Delivery date in the payroll delivery to date.
     *
     * @param Date
     * @Author: Sakrateesh R
     */
    public void Enter_Delivery_To_Date(String Date) {
        clearAndType(payroll_report_delivery_to_date, Date);
    }

    public void Click_Search_button() {
        Click(search_button, "Payroll Report Search button");
    }

    public boolean verify_whether_the_delivery_review_row_IsDisplayed() {
        if (delivery_review_row_lists.size() > 1) {
            return true;
        }
        return false;
    }

    public boolean verify_Invoice_is_displaying(String Invoice) {
        if (delivery_review_row_lists.size() > 0) {
            for (int i = 1; i < delivery_review_row_lists.size(); i++) {
                WebElement row = delivery_review_row_lists.get(i);
                List<WebElement> cells = row.findElements(By.xpath(".//td"));
                if (cells.size() >= 9) {
                    WebElement Invoice_cell = cells.get(0);
                    if (Invoice_cell.getText().equals(Invoice)) {
                        Highlight_Element(Invoice_cell, "Invoice in Payroll report"); // Assuming HighlightElement highlights the matched cell
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean verify_Entered_non_Payable_Reason_IsDisplaying(String Invoice, String NonpayableText) {
        if (delivery_review_row_lists.size() > 0) {
            System.out.println("Total rows found: " + delivery_review_row_lists.size());

            for (int i = 1; i < delivery_review_row_lists.size(); i++) {
                WebElement row = delivery_review_row_lists.get(i);

                // Locate the Invoice input inside the first cell with class 'hdnInvoice'
                WebElement invoiceInput = row.findElement(By.cssSelector("td input.hdnInvoice"));
                String invoiceStr = invoiceInput.getAttribute("value");

                // Locate the Non-Payable Reason input inside the appropriate cell with class 'NonPayableReason'
                WebElement nonPayableReasonInput = row.findElement(By.cssSelector("td input.NonPayableReason"));
                String nonPayableReasonText = nonPayableReasonInput.getAttribute("value");

                if (invoiceStr != null && invoiceStr.equals(Invoice)) {
                    System.out.println("Matching invoice found in row " + i);
                    HighlightElement(invoiceInput);

                    if (nonPayableReasonText != null && nonPayableReasonText.equals(NonpayableText)) {
                        Highlight_Element(nonPayableReasonInput, "Non Payable Reason Input field");
                        return true;
                    } else {
                        System.out.println("Non-Payable Reason did not match. Expected: " + NonpayableText);
                    }
                }
            }
        } else {
            System.out.println("No rows found in delivery_review_row_lists.");
        }

        System.out.println("No matching invoice and non-payable reason found.");
        return false;
    }

    public boolean Verify_Payable_Checkbox_IsChecked(String Invoice) {
        if (delivery_review_row_lists.size() > 0) {
            System.out.println("Total rows found: " + delivery_review_row_lists.size());

            for (int i = 1; i < delivery_review_row_lists.size(); i++) {
                WebElement row = delivery_review_row_lists.get(i);

                // Locate the Invoice input inside the first cell with class 'hdnInvoice'
                WebElement invoiceInput = row.findElement(By.cssSelector("td input.hdnInvoice"));
                String invoiceStr = invoiceInput.getAttribute("value");

                // Locate the Non-Payable Reason input inside the appropriate cell with class 'NonPayableReason'
                WebElement nonPayableReasonInput = row.findElement(By.cssSelector("td input.IsPayable"));
                System.out.println();
                if (invoiceStr != null && invoiceStr.equals(Invoice)) {
                    System.out.println("Matching invoice found in row " + i);
                    HighlightElement(nonPayableReasonInput);
                    return nonPayableReasonInput.isSelected();
                }
            }
        }
        System.out.println("No matching invoice and non-payable reason found.");
        return false;
    }

    public boolean Verify_Driver_Payrate_IsDisplayed(String Invoice, String Payrate) {
        if (delivery_review_row_lists.size() > 0) {
            System.out.println("Total rows found: " + delivery_review_row_lists.size());

            for (int i = 1; i < delivery_review_row_lists.size(); i++) {
                WebElement row = delivery_review_row_lists.get(i);

                // Locate the Invoice input inside the first cell with class 'hdnInvoice'
                WebElement invoiceInput = row.findElement(By.cssSelector("td input.hdnInvoice"));
                String invoiceStr = invoiceInput.getAttribute("value");

                // Locate the Non-Payable Reason input inside the appropriate cell with class 'NonPayableReason'
                WebElement DriverPayrate = row.findElement(By.cssSelector("td input.PayableAmount.form-control"));
                //System.out.println(DriverPayrate.getAttribute("value")+ "This is printed from the Amount input field");
                if (invoiceStr != null && invoiceStr.equals(Invoice)) {
                    System.out.println("Matching invoice found in row " + i);
                    HighlightElement(DriverPayrate);
                    if (DriverPayrate.getAttribute("value").equals(Payrate)) {
                        return true;
                    }
                }
            }
        }
        System.out.println("No matching invoice and non-payable reason found.");
        return false;
    }

    /**
     * Verify whether the Invoice Number on Payroll Report Table Grid is displayed or not
     *
     * @param invoice_number
     * @return
     */
    public boolean is_InvoiceNumber_Displayed_On_Payroll_Report_Table_Grid(String invoice_number) {
        try {
            WebElement invoice_label = getDriver().findElement(By.xpath(
                    "//div[@class='k-grid-content k-auto-scrollable gridscrollershow']//table//tbody//tr//td[text()='"
                            + invoice_number + "']"
            ));
            // explicitWait(invoice_label, 10);

            boolean isDisplayed = isElementDisplayed(invoice_label, "Invoice Number on Payroll report - Table grid");
            if (isDisplayed) {
                //  Allure.step("✅ Invoice number '" + invoice_number + "' is displayed in the Payroll report table grid.");
            } else {
                Allure.step("❌ Invoice number '" + invoice_number + "' is NOT displayed in the Payroll report table grid.");
            }
            return isDisplayed;

        } catch (Exception e) {
            Allure.step("❌ Could not verify invoice number '" + invoice_number
                    + "' in Payroll report table grid. Possible reasons: invoice not generated, table not loaded, or locator issue.");
            return false;
        }
    }


    /**
     * It retrieves the Non Payable Reason on Payroll Report Table Grid
     *
     * @param invoice_number
     * @return
     */
    public String is_NonPayable_Reason_Displayed_On_Payroll_Report_Table_Grid(String invoice_number) {
        WebElement invoice_label = getDriver().findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable gridscrollershow']//table//tbody//tr//td[text()='" + invoice_number + "']//following-sibling::td[7]//input"));
        return getElementAttribute(invoice_label, "Invoice Number on Payroll report - Table grid");
    }

    /**
     * Verify Payable checkbox is checked on Payroll Report Table Grid
     *
     * @param invoice_number
     * @return
     */
    public boolean is_Payable_Checkbox_Checked_On_Payroll_Report_Table_Grid(String invoice_number) {
        try {
            WebElement invoice_label = getDriver().findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable gridscrollershow']//table//tbody//tr//td[text()='" + invoice_number + "']//following-sibling::td[6]//input"));
            String checked = invoice_label.getAttribute("checked");
            if (checked != null && checked.equals("true")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while checking Payable checkbox for invoice number: " + invoice_number + " on Payroll Report Table Grid - " + e.getMessage());
        }
    }

    /**
     * Clicks the Invoice Column Header on Payroll Report Table Grid
     */
    public void click_Invoice_Header_Column_On_Payroll_Report_Table_Grid() {
        js_Click(invoice_column_header, "Invoice Column Header on Payroll report - Table grid");
        delayWithGivenTime(3000);
        js_Click(invoice_column_header, "Invoice Column Header on Payroll report - Table grid");
    }

    public String get_PayRate_From_PayrollReport(String Invoice) {
        WebElement payrate = getDriver().findElement(By.xpath("//td[text()='" + Invoice + "']/following-sibling::td[8]//input[@class='PayableAmount form-control']"));
        return getElementAttribute(payrate, "Payrate on Payroll report - Table grid");
    }

    public String get_PayRate_Label_From_PayrollReport(String Invoice) {
        WebElement payrate = getDriver().findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable gridscrollershow']//table//tbody//tr//td[text()='2061013588']//following-sibling::td[8]"));
        return getElementText(payrate, "Payrate on Payroll report - Table grid");
    }

}
