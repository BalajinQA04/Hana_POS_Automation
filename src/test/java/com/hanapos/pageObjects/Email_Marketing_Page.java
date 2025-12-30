package com.hanapos.pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email_Marketing_Page extends TestBaseClass {

    public Email_Marketing_Page() {
        PageFactory.initElements(getDriver(), this);
    }


    @FindBy(xpath = "//h2[contains(text(),'Campaigns')]")
    private WebElement campaigns_header;

    @FindBy(xpath = "//a[contains(text(),'Create Campaign')]")
    private WebElement create_campaign_button;

    @FindBy(xpath = "//h2[contains(text(),'Campaign info')]")
    private WebElement campaign_info_header;

    @FindBy(xpath = "//input[@id='CampaignName']")
    private WebElement campaign_name_textbox;

    @FindBy(xpath = "//input[@id='EmailSubject']")
    private WebElement campaign_email_subject_textbox;

    @FindBy(xpath = "//input[@id='FromName']")
    private WebElement campaign_fromName_textbox;

    @FindBy(xpath = "//input[@id='FromEmail']")
    private WebElement campaign_fromEmail_textbox;

    @FindBy(xpath = "//select[@id='ddlCampaignType']")
    private WebElement campaign_type_dropdown;

    @FindBy(xpath = "//select[@id='ddlsetupOccasion']")
    private WebElement campaign_occasion_dropdown;

    @FindBy(xpath = "//a[@class='btnNext']")
    private WebElement next_button;

    @FindBy(xpath = "//select[@id='ddlSTOccasion']")
    private WebElement occasion_dropdown_on_template_tab;

    @FindBy(xpath = "//input[@id='txtCouponCode']")
    private WebElement coupon_textbox;

    @FindBy(xpath = "//input[@id='txtTitle']")
    private WebElement title_textbox;

    @FindBy(xpath = "//input[@id='txtContent']")
    private WebElement content_textbox;

    @FindBy(xpath = "//label[text()='Banner Image']/following::div[2]")
    private WebElement banner_image_upload_element;

    @FindBy(xpath = "//label[text()='First Product Image']/following::div[2]")
    private WebElement first_product_image_upload;

    @FindBy(xpath = "//label[text()='First Product Image']/following::div[6]//input")
    private WebElement first_product_image_name;

    @FindBy(xpath = "//label[text()='First Product Image']/following::div[9]//input")
    private WebElement first_product_image_price;

    @FindBy(xpath = "//label[text()='Second Product Image']/following::div[2]")
    private WebElement second_product_image_upload;

    @FindBy(xpath = "//label[text()='Second Product Image']/following::div[6]//input")
    private WebElement second_product_image_name;

    @FindBy(xpath = "//label[text()='Second Product Image']/following::div[9]//input")
    private WebElement second_product_image_price;

    @FindBy(xpath = "//label[text()='Third Product Image']/following::div[2]")
    private WebElement third_product_image_upload;

    @FindBy(xpath = "//label[text()='Third Product Image']/following::div[6]//input")
    private WebElement third_product_image_name;

    @FindBy(xpath = "//label[text()='Third Product Image']/following::div[9]//input")
    private WebElement third_product_image_price;

  /*  @FindBy(xpath = "(//h1[@id='lblCouponCode'])[1]")
    private WebElement couponcode_on_template_design;

    @FindBy(xpath = "(//h1[@id='lblHeading'])[1]")
    private WebElement title_on_template_design;

    @FindBy(xpath = "((//h1[@id='lblHeading'])[1]/following::p[@id='centerbody'])[1]")
    private WebElement content_on_template_design;

    @FindBy(xpath = "(//h3[@id='Product1Name']//strong)[1]")
    private WebElement product_name1_on_template_design;

    @FindBy(xpath = "(//h3[@id='Product2Name']//strong)[1]")
    private WebElement product_name2_on_template_design;

    @FindBy(xpath = "(//h3[@id='Product3Name']//strong)[1]")
    private WebElement product_name3_on_template_design;

    @FindBy(xpath ="(//span[@id='Product1Price'])[1]")
    private WebElement product_price1_on_template_design;

    @FindBy(xpath ="(//span[@id='Product2Price'])[1]")
    private WebElement product_price2_on_template_design;

    @FindBy(xpath ="(//span[@id='Product3Price'])[1]")
    private WebElement product_price3_on_template_design;*/

    @FindBy(xpath = "(//h1[@id='lblCouponCode'])[1]")
    private WebElement couponCode;

    @FindBy(xpath = "(//h1[@id='lblHeading'])[1]")
    private WebElement title;

    @FindBy(xpath = "((//h1[@id='lblHeading'])[1]/following::p[@id='centerbody'])[1]")
    private WebElement content;

    @FindBy(xpath = "(//h3[@id='Product1Name']//strong)[1]")
    private WebElement productName1;

    @FindBy(xpath = "(//h3[@id='Product2Name']//strong)[1]")
    private WebElement productName2;

    @FindBy(xpath = "(//h3[@id='Product3Name']//strong)[1]")
    private WebElement productName3;

    @FindBy(xpath = "(//span[@id='Product1Price'])[1]")
    private WebElement productPrice1;

    @FindBy(xpath = "(//span[@id='Product2Price'])[1]")
    private WebElement productPrice2;

    @FindBy(xpath = "(//span[@id='Product3Price'])[1]")
    private WebElement productPrice3;

    @FindBy(xpath = "//a[contains(text(),'Preview and Test')]")
    private WebElement preview_and_test_button;

    @FindBy(xpath = "(//a[contains(text(),'Preview and Test')]/following::ul//li//a)[1]")
    private WebElement testmail_button_on_previewDesign;

    @FindBy(xpath = "//input[@id='TestEmailID']")
    private WebElement emailaddress_textbox_on_previewDesign;

    @FindBy(xpath = "//button[normalize-space()='Send']")
    private WebElement send_email_button_on_previewDesign;

    @FindBy(xpath = "//div[@class='common_gapping email_toggle divcenter']//span[@class='switchery switchery-default']")
    private WebElement select_customer_tooglebutton;

    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement SuccessToastMsg;

    @FindBy(xpath = "//div[@id='ddlCustomer_chosen']//ul[@class='chosen-choices']")
    private WebElement customer_select_dropdown;


    @FindBy(xpath = "//input[@id='CampaignDate']")
    private WebElement select_campaign_date;

    @FindBy(css = "div[class='datepicker-days'] th[class='datepicker-switch']")
    private WebElement deliverydate_monthyear_on_advancedispatchpage;

    @FindBy(css = "div[class='datepicker-days'] th[class='next']")
    private WebElement deliverydate_nextbutton_on_advancedispatchpage;

    @FindBy(xpath = "//div[@class='datepicker-days']//table//tbody//tr//td[@class='day' or @class='active day' or @class='today day']")
    private List<WebElement> listofdays_on_deliverydate_datepicker;

    @FindBy(xpath = "//div[@class='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-bottom']")
    private WebElement datepicker_on_deliverydate_advancedispatch;

    @FindBy(xpath = "//input[@id='CampaignTime']")
    private WebElement select_campaign_time;

    @FindBy(xpath = "//a[@id='btnScheduleCampaign']")
    private WebElement schedule_and_finalize_campaign_button;

    @FindBy(xpath = "//h2[normalize-space()='Confirmation']")
    private WebElement DeleteConfirmationPopUpInWalkinSetting;

    @FindBy(xpath = "//h2[normalize-space()='Confirmation']/following::p[1]")
    private WebElement AreYouSureOnceToSubmited_text_message_on_confirmationpopup;

    @FindBy(xpath = "//div[contains(@class,'sweet-alert')]")
    private WebElement confirmation_alert_popup;

    @FindBy(xpath = "//input[@placeholder='Confirmation code']")
    private WebElement ConfirmationCodeTextBox;

    @FindBy(xpath = "//button[@class='cancel']")
    private WebElement CancelButtonInDeleteConfirmationPopUp;

    @FindBy(xpath = "//button[normalize-space()='OK']")
    private WebElement OKButtonInDeleteConfirmationPopUp;

    @FindBy(xpath = "//p[normalize-space()='Incorrect confirmation code!']")
    private WebElement IncorrectConfirmationCodeText;


    public boolean Verify_EmailCampaigns_header() {
        try {
            return isElementDisplayed(campaigns_header);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Click_create_campaign_button() {
        try {
            jsClick(create_campaign_button);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verify_campaign_info_header() {
        try {
            return isElementDisplayed(campaign_info_header);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Enter_Campaign_Name(String campaign_name) {
        try {
            clickAndType(campaign_name_textbox, campaign_name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Enter_Campaign_Email_Subject(String email_subject) {
        try {
            clickAndType(campaign_email_subject_textbox, email_subject);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Enter_Campaign_From_Name(String from_name) {
        try {
            clickAndType(campaign_fromName_textbox, from_name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Enter_Campaign_From_Email(String from_email) {
        try {
            clickAndType(campaign_fromEmail_textbox, from_email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Select_Campaign_Type(String campaign_type) {
        try {
            dropDown(campaign_type_dropdown, campaign_type, "VisibleText");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Select_Campaign_Occasion(String campaign_occasion) {
        try {
            dropDown(campaign_occasion_dropdown, campaign_occasion, "VisibleText");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Click_Next_Button() {
        try {
            explicitWait_for_ClickAction(next_button);
            jsClick(next_button);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void Select_template_Occasion(String template_occasion) {
        try {
            dropDown(occasion_dropdown_on_template_tab, template_occasion, "VisibleText");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Enter_Coupon_on_Template_Tab(String coupon) {
        try {
            coupon_textbox.clear();
            delayWithGivenTime(1000);
            coupon_textbox.click();
            delayWithGivenTime(1000);
            coupon_textbox.sendKeys(coupon);
            //  clickAndType(coupon_textbox, coupon);
        } catch (Exception e) {
            throw new RuntimeException("Coupon input textbox field is not interactable " + e.getMessage());
        }
    }

    public void Enter_Title_on_Template_Tab(String title) {
        try {
            title_textbox.clear();
            delayWithGivenTime(1000);
            title_textbox.click();
            delayWithGivenTime(1000);
            title_textbox.sendKeys(title);
            //  clickAndType(title_textbox, title);
        } catch (Exception e) {
            throw new RuntimeException("Title on input textbox field is not interactable " + e.getMessage());
        }
    }

    public void Enter_Content_on_Template_Tab(String content) {
        try {
            content_textbox.clear();
            delayWithGivenTime(500);
            content_textbox.click();
            delayWithGivenTime(500);
            content_textbox.sendKeys(content);
            // clickAndType(content_textbox, content);
        } catch (Exception e) {
            throw new RuntimeException("Content on input textbox field is not interactable " + e.getMessage());
        }
    }

    public void Select_Banner_Image_On_TemplateTab(String filename) {
        try {
            explicitWait_for_ClickAction(banner_image_upload_element);
            if (banner_image_upload_element != null) {
                String projectPath = System.getProperty("user.dir");
                String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;

                // uploadFile(banner_image_upload_element, fullFilePath);
                banner_image_upload_element.click();
                // jsClick(ele);
                delayWithGivenTime(2000);
                Robot rb = new Robot();

                StringSelection str = new StringSelection(fullFilePath);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
                rb.keyPress(KeyEvent.VK_CONTROL);
                rb.keyPress(KeyEvent.VK_V);
                rb.keyRelease(KeyEvent.VK_CONTROL);
                rb.keyRelease(KeyEvent.VK_V);
                rb.keyPress(KeyEvent.VK_ENTER);
                rb.keyRelease(KeyEvent.VK_ENTER);

            } else {
                throw new NullPointerException("Select Button on Banner Image element is null.");
            }
        } catch (Exception e) {
            System.err.println("Failed to Select Button on Banner Image element: " + e.getMessage());
        }

    }

    public void Select_First_Product_Image_On_TemplateTab(String filename) {
        try {
            explicitWait_for_ClickAction(first_product_image_upload);

            if (first_product_image_upload != null) {
                String projectPath = System.getProperty("user.dir");
                String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;
                // uploadFile(first_product_image_upload, fullFilePath);

                first_product_image_upload.click();
                // jsClick(ele);
                delayWithGivenTime(2000);
                Robot rb = new Robot();

                StringSelection str = new StringSelection(fullFilePath);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
                rb.keyPress(KeyEvent.VK_CONTROL);
                rb.keyPress(KeyEvent.VK_V);
                rb.keyRelease(KeyEvent.VK_CONTROL);
                rb.keyRelease(KeyEvent.VK_V);
                rb.keyPress(KeyEvent.VK_ENTER);
                rb.keyRelease(KeyEvent.VK_ENTER);
            } else {
                throw new NullPointerException("Select Button on First Product Image element is null.");
            }
        } catch (Exception e) {
            System.err.println("Failed to Select Button on First Product Image element: " + e.getMessage());
        }

    }

    public void Enter_First_Product_Image_Name_On_TemplateTab(String name) {
        first_product_image_name.click();
        delayWithGivenTime(1000);
        first_product_image_name.clear();
        delayWithGivenTime(500);
        first_product_image_name.sendKeys(name);
    }

    public void Enter_FirstProduct_Price(String price) {
        first_product_image_price.click();
        delayWithGivenTime(1000);
        first_product_image_price.clear();
        delayWithGivenTime(500);
        first_product_image_price.sendKeys(price);
    }


    public void Select_Second_Product_Image_On_TemplateTab(String filename) {
        try {
            explicitWait_for_ClickAction(second_product_image_upload);

            if (second_product_image_upload != null) {
                String projectPath = System.getProperty("user.dir");
                String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;
                // uploadFile(first_product_image_upload, fullFilePath);

                second_product_image_upload.click();
                // jsClick(ele);
                delayWithGivenTime(2000);
                Robot rb = new Robot();

                StringSelection str = new StringSelection(fullFilePath);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
                rb.keyPress(KeyEvent.VK_CONTROL);
                rb.keyPress(KeyEvent.VK_V);
                rb.keyRelease(KeyEvent.VK_CONTROL);
                rb.keyRelease(KeyEvent.VK_V);
                rb.keyPress(KeyEvent.VK_ENTER);
                rb.keyRelease(KeyEvent.VK_ENTER);
            } else {
                throw new NullPointerException("Select Button on First Product Image element is null.");
            }
        } catch (Exception e) {
            System.err.println("Failed to Select Button on First Product Image element: " + e.getMessage());
        }

    }

    public void Enter_Second_Product_Image_Name_On_TemplateTab(String name) {
        second_product_image_name.click();
        delayWithGivenTime(1000);
        second_product_image_name.clear();
        delayWithGivenTime(500);
        second_product_image_name.sendKeys(name);
    }

    public void Enter_Second_Product_Price(String price) {
        second_product_image_price.click();
        delayWithGivenTime(1000);
        second_product_image_price.clear();
        delayWithGivenTime(500);
        second_product_image_price.sendKeys(price);
    }

    public void Select_Third_Product_Image_On_TemplateTab(String filename) {
        try {
            explicitWait_for_ClickAction(third_product_image_upload);

            if (third_product_image_upload != null) {
                String projectPath = System.getProperty("user.dir");
                String fullFilePath = projectPath + File.separator + "testFiles" + File.separator + filename;
                // uploadFile(first_product_image_upload, fullFilePath);

                third_product_image_upload.click();
                // jsClick(ele);
                delayWithGivenTime(2000);
                Robot rb = new Robot();

                StringSelection str = new StringSelection(fullFilePath);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
                rb.keyPress(KeyEvent.VK_CONTROL);
                rb.keyPress(KeyEvent.VK_V);
                rb.keyRelease(KeyEvent.VK_CONTROL);
                rb.keyRelease(KeyEvent.VK_V);
                rb.keyPress(KeyEvent.VK_ENTER);
                rb.keyRelease(KeyEvent.VK_ENTER);
            } else {
                throw new NullPointerException("Select Button on First Product Image element is null.");
            }
        } catch (Exception e) {
            System.err.println("Failed to Select Button on First Product Image element: " + e.getMessage());
        }

    }

    public void Enter_Third_Product_Image_Name_On_TemplateTab(String name) {
        third_product_image_name.click();
        delayWithGivenTime(1000);
        third_product_image_name.clear();
        delayWithGivenTime(500);
        third_product_image_name.sendKeys(name);
    }

    public void Enter_Third_Product_Price(String price) {
        third_product_image_price.click();
        delayWithGivenTime(1000);
        third_product_image_price.clear();
        delayWithGivenTime(500);
        third_product_image_price.sendKeys(price);
    }

    /**
     * Helper method to get the text of a WebElement safely.
     *
     * @param element WebElement to retrieve text from
     * @return The text of the element or "N/A" if not found
     */
    private String getTextSafely(WebElement element) {
        try {
            HighlightElement(element);
            if (element.isDisplayed()) {
                String text = element.getText().trim();
                return text.isEmpty() ? "N/A" : text;
            }
        } catch (Exception e) {
            System.err.println("Error retrieving text: " + e.getMessage());
        }
        return "N/A";
    }

    /**
     * Retrieves the coupon code from the template design.
     *
     * @return Coupon code as a String
     */
    public String getCouponCode() {
        return getTextSafely(couponCode).toUpperCase();
    }

    /**
     * Retrieves the title from the template design.
     *
     * @return Title as a String
     */
    public String getTitle() {
        return getTextSafely(title);
    }

    /**
     * Retrieves the content from the template design.
     *
     * @return Content as a String
     */
    public String getContent() {
        return getTextSafely(content);
    }

    /**
     * Retrieves the name of the first product from the template design.
     *
     * @return Product 1 name as a String
     */
    public String getProductName1() {
        return getTextSafely(productName1);
    }

    /**
     * Retrieves the name of the second product from the template design.
     *
     * @return Product 2 name as a String
     */
    public String getProductName2() {
        return getTextSafely(productName2);
    }

    /**
     * Retrieves the name of the third product from the template design.
     *
     * @return Product 3 name as a String
     */
    public String getProductName3() {
        return getTextSafely(productName3);
    }

    /**
     * Retrieves the price of the first product from the template design.
     *
     * @return Product 1 price as a String
     */
    public String getProductPrice1() {
        return getTextSafely(productPrice1);
    }

    /**
     * Retrieves the price of the second product from the template design.
     *
     * @return Product 2 price as a String
     */
    public String getProductPrice2() {
        return getTextSafely(productPrice2);
    }

    /**
     * Retrieves the price of the third product from the template design.
     *
     * @return Product 3 price as a String
     */
    public String getProductPrice3() {
        return getTextSafely(productPrice3);
    }

    public void click_preview_and_test_button() {
        jsClick(preview_and_test_button);
    }

    public void Send_Test_Mail(String email) {
        jsClick(testmail_button_on_previewDesign);
        delayWithGivenTime(1000);

        emailaddress_textbox_on_previewDesign.click();
        delayWithGivenTime(1000);

        emailaddress_textbox_on_previewDesign.sendKeys(email);
        delayWithGivenTime(1000);
        send_email_button_on_previewDesign.click();
    }

    public boolean IsCustomer_Toogle_Button_TurnOff() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(select_customer_tooglebutton));

        HighlightElement(select_customer_tooglebutton);

        // Get the background color of the toggle button
        String backgroundColor = select_customer_tooglebutton.getCssValue("background-color");
        System.out.println("state of customer toogle button color : " + backgroundColor);

        // Check if the background color matches the 'off' state
        if (backgroundColor.equals("rgba(26, 179, 148, 1)")) {  // Color for off state: rgb(237, 85, 101) corresponds to #ed5565
            return true;
        } else {
            System.out.println("Toggle button is in the 'off' state...");
            return false;
        }

    }

    public void TurnOff_Customer_ToogleButton() {
        By cust_toogle = By.xpath("//div[@class='common_gapping email_toggle divcenter']//span[@class='switchery switchery-default']");
        // Locate the element with the background color
        WebElement element = getDriver().findElement(cust_toogle);

        // Get the background color style property
        String backgroundColor = element.getCssValue("background-color");

        // Expected background color (rgba format for rgb(197, 231, 244))
        String expectedColor = "rgba(197, 231, 244, 1)";

        String style = element.getAttribute("style");
        System.out.println("Background style color of toogle is :  " + style);
        if (style.contains("background-color: rgb(197, 231, 244)")) {
            System.out.println("Background color matches. Clicking the toggle button.");
            element.click();
        } else {
            System.out.println("Background color does not match. Retrieved style: " + style);
        }
    }

    public String VerifySucessToasterMessageText() {
        return getElementText(SuccessToastMsg);
    }

    public void Select_Customer(String customer) {
        explicitWait_for_ClickAction(customer_select_dropdown);
        customer_select_dropdown.click();
        //  jsClick(customer_select_dropdown);
        delayWithGivenTime(2000);

        By list_of_customer = By.xpath("(//ul[@class='chosen-results'])[4]//li");
        List<WebElement> listofcustomers_on_selectcustomer = getDriver().findElements(list_of_customer);
        for (int i = 0; i < listofcustomers_on_selectcustomer.size(); i++) {
            if (listofcustomers_on_selectcustomer.get(i).getText().contains(customer)) {
                listofcustomers_on_selectcustomer.get(0).click();
                break;
            }
        }
    }

    public void Select_Email_Campaign_Date(String date) {
        // Parse the target date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println("Test Data : " + date);
        LocalDate targetDate = LocalDate.parse(date, formatter);

        int targetDay = targetDate.getDayOfMonth();
        String targetMonthYear = targetDate.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH)); // "January 2025"
        System.out.println("Target Month Year : " + targetMonthYear);
        // Open the datepicker
        explicitWait_for_ClickAction(select_campaign_date);
        //   select_campaign_date.click();
        Click(select_campaign_date, "Datepicker in Schedule Email Campaign Page");
        delayWithGivenTime(2000);

        // Navigate to the correct month and year
        while (true) {
            String displayedMonthYear = deliverydate_monthyear_on_advancedispatchpage.getText().trim();
            System.out.println("Month Year:  " + displayedMonthYear);
            if (displayedMonthYear.equalsIgnoreCase(targetMonthYear)) {
                break;
            }
            deliverydate_nextbutton_on_advancedispatchpage.click();
        }

        // Select the day
        for (WebElement dayElement : listofdays_on_deliverydate_datepicker) {
            if (dayElement.isEnabled() && dayElement.getText().equalsIgnoreCase(String.valueOf(targetDay))) {
                js_Click(dayElement, "Date Element on datepicker in Schedule Email Campaign Page");
                break;
            }
        }

        // select_campaign_date.clear();
        // clickAndType(select_campaign_date, date);
    }


    public void Select_Email_Campaign_Time(String time) {
        select_campaign_time.sendKeys(time);
    }

    public void Click_ScheduleAndFinalize_Button() {
        explicitWait_for_ClickAction(schedule_and_finalize_campaign_button);
        schedule_and_finalize_campaign_button.click();
    }

    public String Extract_the_Confirmation_Code() {
        // Fetch the text from the WebElement
        String confirmationText = AreYouSureOnceToSubmited_text_message_on_confirmationpopup.getText();

        // Use a regular expression to extract the number
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(confirmationText);

        String extractedNumber = null;
        if (matcher.find()) {
            extractedNumber = matcher.group(); // Extract the first number
        }

        // Print or use the extracted number
        System.out.println("Extracted Number: " + extractedNumber);
        return extractedNumber;

    }


    /**
     * Validate whether confirmation code text box is displayed on walkin setting in cash and carry page
     *
     * @return If the confirmation code text box is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the confirmation code text box, and then validates whether confirmation code text box is displayed on walkin setting in cash and carry page
     * @Author Balaji N
     */
    public boolean confirmationcodeTextBoxIsDisplayed() {
        HighlightElement(ConfirmationCodeTextBox);
        return ConfirmationCodeTextBox.isDisplayed();
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
        HighlightElement(CancelButtonInDeleteConfirmationPopUp);
        return CancelButtonInDeleteConfirmationPopUp.isDisplayed();
    }

    /**
     * Validate whether Ok button in delete confirmation popup is displayed on walkin setting in cash and carry page
     *
     * @return If the Ok button in delete confirmation popup is displayed it returns true, otherwise it returns false
     * @Description: This function highlights the Ok button in delete confirmation popup, and then validates whether Ok button in delete confirmation popup is displayed on walkin setting in cash and carry page
     * @Author Balaji N
     */
    public boolean OkBtnInDeleteConfirmationPopup() {
        HighlightElement(OKButtonInDeleteConfirmationPopUp);
        return OKButtonInDeleteConfirmationPopUp.isDisplayed();
    }

    /**
     * Clicks on Cancel button in delete confirmation popup on walkin setting in cash and carry page
     *
     * @Description: This function highlights the Cancel button in delete confirmation popup, and then clicks on Cancel button in delete confirmation popup on walkin setting in cash and carry page
     * @Author Balaji N
     */
    public void ClickCancelBtnInDeleteConfirmationPopup() {
        jsScrollClick(CancelButtonInDeleteConfirmationPopUp);
    }

    /**
     * Enter confirmation code in delete confirmation popup on walkin setting in cash and carry page
     *
     * @param confirmationCode confirmation code to be entered
     * @Description: This function highlights the confirmation code text box, and then enters the confirmation code in delete confirmation popup on walkin setting in cash and carry page
     * @Author Balaji N
     */
    public void EnterConfirmationCodeInConfirmationPopup(String confirmationCode) {
        if (is_Element_Displayed(confirmation_alert_popup, "Confirmation Popup")) {
            ClickAndType(ConfirmationCodeTextBox, confirmationCode, "confirmation code textbox field");
        }
    }

    /**
     * Clicks on Ok button in delete confirmation popup on walkin setting in cash and carry page
     *
     * @Description: This function highlights the Ok button in delete confirmation popup, and then clicks on Ok button in delete confirmation popup on walkin setting in cash and carry page
     * @Author Balaji N
     */
    public void ClickOkBtn_on_ConfirmationPopup() {
        jsScrollClick(OKButtonInDeleteConfirmationPopUp);
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
        HighlightElement(IncorrectConfirmationCodeText);
        return IncorrectConfirmationCodeText.getText();
    }


}
