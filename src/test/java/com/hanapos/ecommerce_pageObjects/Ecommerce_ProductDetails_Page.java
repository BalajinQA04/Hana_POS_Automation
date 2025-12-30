package com.hanapos.ecommerce_pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Ecommerce product details page in the bestflorist pos application - bestflorist ecommerce website
 *
 * @Author Balaji N
 */
public class Ecommerce_ProductDetails_Page extends TestBaseClass {

    public Ecommerce_ProductDetails_Page() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//h1[@class='hd-after product_title']")
    private WebElement product_displayed_description;

    @FindBy(xpath = "//div[@class='front-end box' and .//span[contains(text(), 'Medium')]]")
    private WebElement product_variant_as_medium;

    @FindBy(xpath = "//div[@class='pprice']")
    private WebElement product_displayed_price;

    @FindBy(id = "tabDelivery")
    private WebElement delivery_tab;

    @FindBy(xpath = "(//button[@class='btnDate'])[1]")
    private WebElement first_delivery_date_button;

    @FindBy(xpath = "(//button[@class='btnDate'])[1]//span[2]")
    private WebElement first_delivery_date_month_day;

    @FindBy(xpath = "(//button[@class='btnDate'])[2]")
    private WebElement delivery_date_as_current_date;

    @FindBy(xpath = "//input[@id='txtZipCode']")
    private WebElement zip_code_textbox;

    @FindBy(xpath = "//button[contains(text(),'Check')]")
    private WebElement check_button;

    @FindBy(xpath = "//span[@id='ZipcodeMsg']")
    private WebElement zip_code_validation_message;

    @FindBy(xpath = "//a[@class='readmore btnCreateOrder']")
    private WebElement create_order_button;

    /**
     * It retrieves the text of the product displayed on product details page
     *
     * @return If product details page is displayed then the text of the product displayed on product details page
     * Author Balaji N
     */
    public String Verify_Product_Displayed_Description() {
        wait_For_Page_To_Be_Stable(getDriver());
        return get_Element_Text(product_displayed_description, "Displayed product description");
    }

    /**
     * Clicks the product variant as medium on product details page
     * <p>
     * Author Balaji N
     */
    public void Click_Product_Variant_As_Medium() {
        js_Click(product_variant_as_medium, "Product Variant: Medium");
    }


    public boolean is_Product_Variant_Displayed(String variant) {
        String dynamicXPath = "//span[normalize-space(text())='" + variant + "']";
        By variantLocator = By.xpath(dynamicXPath);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement variantElement = wait.until(ExpectedConditions.presenceOfElementLocated(variantLocator));
        return isElementDisplayed(variantElement, "Product Variant: " + variant);
    }

    public void click_Product_Variant(String variant) {
        String dynamicXPath = "//span[normalize-space(text())='" + variant + "']";
        By variantLocator = By.xpath(dynamicXPath);
        int attempts = 0;

        while (attempts < 3) {
            try {
                WebElement variantElement = new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                        .until(ExpectedConditions.elementToBeClickable(variantLocator));
                click(variantElement, "Product Variant: " + variant);
                return;
            } catch (StaleElementReferenceException | TimeoutException e) {
                attempts++;
                if (attempts == 3) {
                    throw e;
                }
            }
        }
    }


    /**
     * Retrieves the price of the product displayed on product details page
     *
     * @return If product details page is displayed then the price of the product displayed on product details page
     * Author Balaji N
     */
    public String Verify_Displayed_Product_Price() {
        return get_Element_Text(product_displayed_price, "Displayed product price");
    }

    /**
     * Clicks the delivery tab on product details page
     * Author Balaji N
     */
    public void Click_Delivery_Tab() {
        click(delivery_tab, "Delivery Tab");
    }

    /**
     * Clicks the delivery date as current date on product details page
     * Author Balaji N
     */
    public void Click_Delivery_Date_As_Current_Date() {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime easternNow = ZonedDateTime.now(zone);
        ZonedDateTime nextDate = easternNow.plusDays(2);

        // Support both 2-digit day ("Aug 08") and 1-digit day ("Aug 8") formats
        DateTimeFormatter formatterTwoDigit = DateTimeFormatter.ofPattern("MMM dd", Locale.ENGLISH);
        DateTimeFormatter formatterOneDigit = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);
        String formattedDateTwoDigit = nextDate.format(formatterTwoDigit);
        String formattedDateOneDigit = nextDate.format(formatterOneDigit);

        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Try the two date formats to handle UI variations
        String[] candidates = {formattedDateTwoDigit, formattedDateOneDigit};

        for (String dateText : candidates) {
            try {
                By dateLocator = By.xpath("//div[@id='fzipdeliverysection']//button//span[@class='date' and normalize-space(text())='" + dateText + "']");
                WebElement dateElement = wait.until(ExpectedConditions.presenceOfElementLocated(dateLocator));
                wait.until(ExpectedConditions.visibilityOf(dateElement));

                // Scroll into view to avoid hidden element issues
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", dateElement);

                // Wait until clickable with stale element retry
                wait.until(ExpectedConditions.elementToBeClickable(dateElement));

                // Try normal click, fallback to js_Click if intercepted
                try {
                    dateElement.click();
                } catch (ElementClickInterceptedException e) {
                    js_Click(dateElement, "CHOOSE A DELIVERY DATE Button for " + dateText);
                }
                return;

            } catch (TimeoutException | StaleElementReferenceException e) {
            }
        }

        // If we get here, neither format worked. Throw exception with diagnostic info.
        List<WebElement> visibleDates = driver.findElements(By.xpath("//div[@id='fzipdeliverysection']//button//span[@class='date']"));
        List<String> visibleTexts = visibleDates.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        throw new NoSuchElementException("Could not find clickable delivery date with text '"
                + formattedDateTwoDigit + "' or '" + formattedDateOneDigit + "'. Visible dates: " + visibleTexts);
    }

//    public static String convertDisplayedDateTo_MMddyyyy(String displayedDate, int year) {
//        // Build the full date string
//        String fullDate = displayedDate + " " + year; // e.g., "Aug 8 2025" or "Aug 08 2025"
//        // Parse using DateTimeFormatter
//        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
//        LocalDate date = LocalDate.parse(fullDate, inputFormat);
//        // Format as MM/dd/yyyy
//        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        return date.format(outputFormat);
//    }

    public String get_First_Option_Displayed_Delivery_Date(){
        return getElementText(first_delivery_date_month_day,"Delivery Date button - first option");
    }

    public void click_Delivery_Date_Button_First_Option(){
        js_Click(first_delivery_date_button,"Delivery Date button - first option");
    }


    public void clickDeliveryDateEST(int daysFromToday) {
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime targetDate = ZonedDateTime.now(zone).plusDays(daysFromToday);

        // Support both 2-digit day ("Aug 08") and 1-digit day ("Aug 8") formats
        DateTimeFormatter formatterTwoDigit = DateTimeFormatter.ofPattern("MMM dd", Locale.ENGLISH);
        DateTimeFormatter formatterOneDigit = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);
        String formattedDateTwoDigit = targetDate.format(formatterTwoDigit);
        String formattedDateOneDigit = targetDate.format(formatterOneDigit);
        System.out.println(get_Current_EST_Time());
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Try both formats to handle UI variations
        String[] candidates = {formattedDateTwoDigit, formattedDateOneDigit};

        for (String dateText : candidates) {
            try {
                By dateLocator = By.xpath(
                        "//div[@id='fzipdeliverysection']//button//span[@class='date' and normalize-space(text())='" + dateText + "']"
                );
                WebElement dateElement = wait.until(ExpectedConditions.presenceOfElementLocated(dateLocator));
                wait.until(ExpectedConditions.visibilityOf(dateElement));

                // Scroll into view to avoid hidden element issues
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center'});", dateElement
                );

                // Ensure clickable
                wait.until(ExpectedConditions.elementToBeClickable(dateElement));

                // Click with fallback
                try {
                    dateElement.click();
                } catch (ElementClickInterceptedException e) {
                    js_Click(dateElement, "CHOOSE A DELIVERY DATE Button for " + dateText);
                }
                return; // Success — stop searching

            } catch (TimeoutException | StaleElementReferenceException e) {
                // Try next format if this one failed
            }
        }

        // If we get here, neither format worked — gather debug info
        List<WebElement> visibleDates = getDriver().findElements(
                By.xpath("//div[@id='fzipdeliverysection']//button//span[@class='date']")
        );
        List<String> visibleTexts = visibleDates.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        throw new NoSuchElementException(
                "Could not find clickable delivery date with text '" +
                        formattedDateTwoDigit + "' or '" + formattedDateOneDigit +
                        "'. Visible dates: " + visibleTexts
        );
    }


    /**
     * Clicks the delivery date on product details page
     *
     * @param date - The date to be clicked
     * @Author Balaji N
     */
    public void click_Delivery_Date(String date) {
        String dateLocator = "//button[@data-date='" + date + "']";
        By deliveryDateLocator = By.xpath(dateLocator);

        int maxAttempts = 3;
        int attempt = 0;

        while (attempt < maxAttempts) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                WebElement delivery_date_element = wait.until(ExpectedConditions.elementToBeClickable(deliveryDateLocator));

                // Scroll into view (optional safety step)
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", delivery_date_element);

                // Use JS Click
                js_Click(delivery_date_element, "Delivery Date: " + date);
                return;
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException on attempt " + (attempt + 1) + " for date: " + date);
            } catch (ElementClickInterceptedException e) {
                System.out.println("ElementClickInterceptedException on attempt " + (attempt + 1) + " for date: " + date);
            } catch (ElementNotInteractableException e) {
                System.out.println("ElementNotInteractableException on attempt " + (attempt + 1) + " for date: " + date);
            } catch (TimeoutException e) {
                System.out.println("Timeout waiting for delivery date button: " + date);
                throw e; // Serious enough to rethrow
            } catch (NoSuchElementException e) {
                System.out.println("Delivery date button not found: " + date);
                throw e;
            } catch (Exception e) {
                System.out.println("Unexpected error while clicking delivery date: " + date + " - " + e.getMessage());
                throw e;
            }

            attempt++;
            delayWithGivenTime(1000); // Wait 1s before retry
        }

        throw new RuntimeException("Failed to click delivery date: " + date + " after " + maxAttempts + " attempts.");
    }


    /**
     * Retrieves the background color of the delivery date as current date on product details page
     * Author Balaji N
     */
    public String Validate_Delivery_Date_As_Current_Date_Displayed_In_Green_Colour() {
        return delivery_date_as_current_date.getCssValue("background-color");
    }

    /**
     * Enters the zip code on product details page
     *
     * @param zip_code The given zip code to be entered
     *                 Author Balaji N
     */
    public void Enter_Zip_Code(String zip_code) {
        int maxAttempts = 3;
        int attempt = 0;

        while (attempt < maxAttempts) {
            try {
                fluentWait(zip_code_textbox, 10, 500);
                zip_code_textbox.clear();
                zip_code_textbox.click();
                zip_code_textbox.sendKeys(zip_code);
                return;
            } catch (StaleElementReferenceException | ElementNotInteractableException | NoSuchElementException e) {
                System.out.println("Attempt " + (attempt + 1) + " failed due to: " + e.getClass().getSimpleName());
            } catch (TimeoutException e) {
                System.out.println("Timeout while waiting for zip code field.");
                throw e; // serious issue - rethrow
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                throw e; // unknown issue - rethrow
            }
            attempt++;
            delayWithGivenTime(1000); // Optional small wait before retry
        }

        throw new RuntimeException("Failed to enter ZIP code after " + maxAttempts + " attempts.");
    }


    /**
     * Clicks the check button on product details page
     * Author Balaji N
     */
    public void Click_Check_Button() {
        click(check_button, "Check zip code Button");
    }

    /**
     * Retrieves the zip code validation message on product details page
     *
     * @return If product details page is displayed then the zip code validation message on product details page
     * Author Balaji N
     */
    public String Verify_Zip_Code_Validation_Message() {
        /*HighlightElement(zip_code_validation_message);
        return zip_code_validation_message.getText();*/
        return get_Element_Text(zip_code_validation_message, "Zip code validation message");
    }

    /**
     * Clicks the create order button on product details page
     * Author Balaji N
     */
    public void Click_Create_Order_Button() {
        js_Click(create_order_button, "Order Button");
    }

}
