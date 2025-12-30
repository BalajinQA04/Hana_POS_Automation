package com.hanapos.pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Recurring_Orders_Page extends TestBaseClass {

    public Recurring_Orders_Page() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//h2[text()='Recurring Orders']")
    private WebElement Recurring_Orders_Page_Heading;

    @FindBy(xpath = "//table[@id='tblRecurringData']//tbody//tr[1]//td[3]//b[1]")
    private WebElement customername_on_recurring_orders_table_grid;

    @FindBy(xpath = "//table[@id='tblRecurringData']//tbody//tr[1]//td[3]//b[2]")
    private WebElement recipientname_on_recurring_orders_table_grid;


    public boolean validate_Recurring_Orders_Page_Heading() {
        return isElementDisplayed(Recurring_Orders_Page_Heading, "Recurring Orders Page Heading");
    }

    public String get_customername_on_recurring_orders_table_grid() {
        return getElementText(customername_on_recurring_orders_table_grid, "Customer Name on Recurring Orders Table Grid");
    }

    public String get_recipientname_on_recurring_orders_table_grid() {
        return getElementText(recipientname_on_recurring_orders_table_grid, "Recipient Name on Recurring Orders Table Grid");
    }

    public String get_Displayed_MOP_on_Recurring_Orders_Table_Grid(String recipientname) {
        WebElement MOP = getDriver().findElement(By.xpath("(//table[@id='tblRecurringData']//tbody//tr//td[3]//b[text()='" + recipientname + "']/following::td[@class='recurring-mop-col'])[1]"));
        return getElementText(MOP, "Displayed MOP on Recurring Orders Table Grid");
    }

    public String get_Displayed_Total_Amount_on_Recurring_Orders_Table_Grid(String recipientname) {
        WebElement MOP = getDriver().findElement(By.xpath("(//table[@id='tblRecurringData']//tbody//tr//td[3]//b[text()='" + recipientname + "']/following::td[@class='recurring-total-col'])[1]"));
        return getElementText(MOP, "Displayed Total Amount on Recurring Orders Table Grid");
    }


    public String get_Displayed_Frequency_on_Recurring_Orders_Table_Grid(String recipientname) {
        WebElement MOP = getDriver().findElement(By.xpath("(//table[@id='tblRecurringData']//tbody//tr//td[3]//b[text()='" + recipientname + "']/following::td[@class='recurring-frequency-col editRecurring'])[1]"));
        return getElementText(MOP, "Displayed Frequency on Recurring Orders Table Grid");
    }

    public String get_Displayed_Delivery_Type_on_Recurring_Orders_Table_Grid(String recipientname) {
        WebElement MOP = getDriver().findElement(By.xpath("(//table[@id='tblRecurringData']//tbody//tr//td[3]//b[text()='" + recipientname + "']/following::td[@class='recurring-Delivery-col'])[1]"));
        return getElementText(MOP, "Displayed delivery type on Recurring Orders Table Grid");
    }

    public String get_Displayed_Next_Delivery_Date_on_Recurring_Orders_Table_Grid(String recipientname) {
        WebElement MOP = getDriver().findElement(By.xpath("(//table[@id='tblRecurringData']//tbody//tr//td[3]//b[text()='" + recipientname + "']/following::td[@class='recurring-nextdate-col']//input)[1]"));
        return getElementAttribute(MOP, "Displayed next delivery date on Recurring Orders Table Grid");
    }

    public String get_Displayed_End_Delivery_Date_on_Recurring_Orders_Table_Grid(String recipientname) {
        WebElement MOP = getDriver().findElement(By.xpath("(//table[@id='tblRecurringData']//tbody//tr//td[3]//b[text()='" + recipientname + "']/following::td[@class='recurring-recurring-end-date-col']//input)[1]"));
        return getElementAttribute(MOP, "Displayed End delivery date on Recurring Orders Table Grid");
    }


}
