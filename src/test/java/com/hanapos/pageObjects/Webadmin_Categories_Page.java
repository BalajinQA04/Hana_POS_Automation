package com.hanapos.pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Webadmin_Categories_Page extends TestBaseClass {
    public Webadmin_Categories_Page() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//h2[@id='PageTitle']")
    private WebElement PageTitle;

    @FindBy(xpath = "//input[@id='BtnAdddNewCat']")
    private WebElement add_new_category_button;

    @FindBy(xpath = "//input[@id='txtAddNewCatName']")
    private WebElement category_name_textbox;

    @FindBy(xpath = "//select[@id='ddlAddNewHigherCategory']")
    private WebElement parent_category_dropdown;

    @FindBy(xpath = "//textarea[@id='txtAddNewCatMemo']")
    private WebElement category_description_textbox;

    @FindBy(xpath = "//input[@id='txtAddNewCateMetaTitle']")
    private WebElement meta_title_textbox;

    @FindBy(xpath = "//textarea[@id='txtAddNewCatMetaKeywords']")
    private WebElement meta_keywords_textbox;

    @FindBy(xpath = "//input[@id='BtnSaveCategory']")
    private WebElement create_web_category_button;

    @FindBy(xpath = "//input[@id='SearchCategory']")
    private WebElement search_category_textbox;

    @FindBy(xpath = "//td[@class='DescColumnWidth']")
    private List<WebElement> list_of_category_name_on_web_categories_page_table_grid;

    @FindBy(xpath = "//div[@id='CategoryGrid']//td[@class='DescColumnWidth']//span")
    private WebElement category_name_on_web_categories_page_table_grid;

    @FindBy(xpath = "//div[@id='CategoryGrid']//td//span/preceding::td//a//span[@class=' k-icon k-i-delete']")
    private WebElement delete_icon_on_web_categories_page_table_grid;

    @FindBy(xpath = "//div[@id='CategoryGrid']//td//span/preceding::td//a//span[@class=' k-icon k-i-edit']")
    private WebElement edit_icon_on_web_categories_page_table_grid;

    @FindBy(xpath = "//button[@class='confirm']")
    private WebElement confirm_delete_button;

    @FindBy(xpath = "//input[@id='txtCatName']")
    private WebElement edit_category_name_textbox;

    @FindBy(xpath = "//select[@id='ddlHigherCategory']")
    private WebElement edit_parent_category_dropdown;

    @FindBy(xpath = "//textarea[@id='txtCatMemo']")
    private WebElement edit_category_description_textbox;

    @FindBy(xpath = "//input[@id='txtCateMetaTitle']")
    private WebElement edit_meta_title_textbox;

    @FindBy(xpath = "//textarea[@id='txtCatMetaKeywords']")
    private WebElement edit_meta_keywords_textbox;

    @FindBy(xpath = "//table[@class='switch-table']//label[@for='ChkHide']//span[1]")
    private WebElement active_toogle_button;

    @FindBy(xpath = "//input[@id='BtnUpdateCategory']")
    private WebElement update_web_category_button;

    @FindBy(xpath = "(//td[@class='SortColumnWidth'])[2]//a[contains(@class,'MoveUp')]")
    private WebElement move_up_sort_Icon_row2;

    @FindBy(xpath = "(//td[@class='SortColumnWidth'])[2]//a[contains(@class,'MoveDown')]")
    private WebElement move_down_sort_Icon_row2;

    @FindBy(xpath = "(//td[@class='SortColumnWidth'])[2]//a[contains(@class,'MoveToTop')]")
    private WebElement move_to_top_sort_Icon_row2;

    @FindBy(xpath = "(//td[@class='SortColumnWidth'])[1]//span[contains(@class,'kendo-move')]")
    private WebElement drag_and_drop_icon_row1;

    @FindBy(xpath = "(//td[@class='SortColumnWidth'])[2]//span[contains(@class,'kendo-move')]")
    private WebElement drag_and_drop_icon_row2;

    @FindBy(xpath = "(//td[@class='DescColumnWidth'])[1]")
    private WebElement row1_category_name_on_web_categories_page_table_grid;

    @FindBy(xpath = "(//td[@class='DescColumnWidth'])[2]")
    private WebElement row2_category_name_on_web_categories_page_table_grid;

    @FindBy(xpath = "(//td[@class='DescColumnWidth'])[3]")
    private WebElement row3_category_name_on_web_categories_page_table_grid;

    @FindBy(xpath = "//select[@id='ddlSortBy']")
    private WebElement sort_by_dropdown;

    @FindBy(xpath = "//div[contains(@class,'sweet-alert')]")
    private WebElement alert_popup;

    @FindBy(xpath = "(//div[contains(@class,'sweet-alert')]//p)[1]")
    private WebElement alert_message;

    @FindBy(xpath = "//input[@placeholder='Confirmation code']")
    private WebElement confirmation_code_textbox;

    @FindBy(xpath = "//div[@class='sa-button-container']//button[text()='Cancel']")
    private WebElement cancel_button;

    @FindBy(xpath = "//div[@class='sa-button-container']//button[text()='OK']")
    private WebElement ok_button;

    public String get_PageTitle() {
        return get_Element_Text(PageTitle, "Page Title of web admin categories page");
    }

    public void click_Add_New_Category_Button() {
        click(add_new_category_button, "Add new category button");
    }

    public void enter_Category_Name(String categoryname) {
        ClickAndType(category_name_textbox, categoryname, "Category name text box");
    }

    public void select_Parent_Category(String parentcategory) {
        drop_Down(parent_category_dropdown, parentcategory, "VisibleText", "Parent category dropdown on web admin categories page");
    }

    public void enter_Category_Description(String categorydescription) {
        ClickAndType(category_description_textbox, categorydescription, "Category description text box");
    }

    public void enter_Meta_Title(String metatitle) {
        ClickAndType(meta_title_textbox, metatitle, "Meta title text box");
    }

    public static String generate_Meta_Keywords() {
        List<String> metaKeywords = Arrays.asList(
                "birthday flowers, online flower delivery, fresh flowers",
                "roses, anniversary flowers, romantic bouquet",
                "sympathy flowers, funeral flowers, condolence bouquet",
                "tulips, spring flowers, colorful bouquets",
                "wedding flowers, bridal bouquet, floral arrangements",
                "orchids, exotic flowers, luxury flowers",
                "valentine flowers, love bouquet, red roses",
                "get well flowers, cheer up bouquet, hospital delivery",
                "same day delivery, local florist, fresh bloom",
                "flower basket, vase arrangement, premium bouquet",
                "thank you flowers, appreciation bouquet, floral gifts",
                "midnight delivery, express flower service, city florist"
        );
        String metaKeyword = metaKeywords.get(new Random().nextInt(metaKeywords.size()));
        return metaKeyword;
    }

    public void enter_Meta_Keywords(String metakeywords) {
        ClickAndType(meta_keywords_textbox, metakeywords, "Meta keywords text box");
    }

    public void click_Create_Web_Category_Button() {
        click(create_web_category_button, "Create web category button");
    }

    public void search_Web_Category(String categoryname) {
        ClickAndType(search_category_textbox, categoryname, "Search category text box");
    }

    public void clear_Search_Web_Category() {
        search_category_textbox.clear();
        search_category_textbox.sendKeys(Keys.ENTER);
    }

    public boolean is_Category_List_Displayed_After_Clear_Search() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

            // Wait until at least one category name element is visible
            wait.until(ExpectedConditions.visibilityOfAllElements(list_of_category_name_on_web_categories_page_table_grid));

            // Refresh the list after wait, in case DOM updated
            List<WebElement> categoryList = list_of_category_name_on_web_categories_page_table_grid;

            return categoryList.size() > 1;
        } catch (TimeoutException e) {
            System.out.println("Category list not visible after clearing search: " + e.getMessage());
            return false;
        }
    }

    public boolean isCategoryListSortedAscending() {
        List<String> categoryNames = list_of_category_name_on_web_categories_page_table_grid
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> sortedList = new ArrayList<>(categoryNames);
        Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
        System.out.println("Category Names: " + categoryNames);
        return categoryNames.equals(sortedList);
    }

    public boolean isCategoryListSortedDescending() {
        List<String> categoryNames = list_of_category_name_on_web_categories_page_table_grid
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> sortedList = new ArrayList<>(categoryNames);
        // Sort in descending order
        sortedList.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));

        System.out.println("Category Names: " + categoryNames);
        System.out.println("Sorted Descending: " + sortedList);

        return categoryNames.equals(sortedList);
    }



    public String get_Category_Name_On_Web_Categories_Page_Table_Grid() {
        return get_Element_Text(category_name_on_web_categories_page_table_grid, "Category name on web categories page table grid");
    }

    public void click_Delete_Icon_On_Web_Categories_Page_Table_Grid() {
        click(delete_icon_on_web_categories_page_table_grid, "Delete icon on web categories page table grid");
        delayWithGivenTime(2000);
        click(confirm_delete_button, "Confirm delete button");
    }

    public void click_Edit_Icon_On_Web_Categories_Page_Table_Grid() {
        click(edit_icon_on_web_categories_page_table_grid, "Edit icon on web categories page table grid");
    }

    public void edit_Category_Name(String categoryname) {
        ClickAndType(edit_category_name_textbox, categoryname, "Edit category name text box");
    }

    public void select_Edit_Parent_Category(String parentcategory) {
        drop_Down(edit_parent_category_dropdown, parentcategory, "VisibleText", "Edit parent category dropdown on web admin categories page");
    }

    public void edit_Category_Description(String categorydescription) {
        ClickAndType(edit_category_description_textbox, categorydescription, "Edit category description text box");
    }

    public void edit_Meta_Title(String metatitle) {
        ClickAndType(edit_meta_title_textbox, metatitle, "Edit meta title text box");
    }

    public void edit_Meta_Keywords(String metakeywords) {
        ClickAndType(edit_meta_keywords_textbox, metakeywords, "Edit meta keywords text box");
    }

    public void click_Update_Web_Category_Button() {
        click(update_web_category_button, "Update web category button");
    }

    public String get_Row1_Category_Name_On_Web_Categories_Page_Table_Grid() {
        int attempts = 0;
        while (attempts < 2) {
            try {
                return get_Element_Text(row1_category_name_on_web_categories_page_table_grid, "Row 1 category name on web categories page table grid");
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == 2) throw e;
                delayWithGivenTime(1000);
            }
        }
        return null;
    }

    public String get_Row2_Category_Name_On_Web_Categories_Page_Table_Grid() {
        int attempts = 0;
        while (attempts < 2) {
            try {
                return get_Element_Text(row2_category_name_on_web_categories_page_table_grid, "Row 2 category name on web categories page table grid");
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == 2) throw e;
                delayWithGivenTime(1000);
            }
        }
        return null;
    }

    public String get_Row3_Category_Name_On_Web_Categories_Page_Table_Grid() {
        int attempts = 0;
        while (attempts < 2) {
            try {
                return get_Element_Text(row3_category_name_on_web_categories_page_table_grid, "Row 3 category name on web categories page table grid");
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == 2) throw e;
                delayWithGivenTime(1000);
            }
        }
        return null;
    }


    public void click_Move_Up_Sort_Icon_Row2() {
        int attempts = 0;
        while (attempts < 2) {
            try {
                click(move_up_sort_Icon_row2, "Move up sort icon row 2");
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == 2) throw e;
                delayWithGivenTime(1000);
            }
        }
    }

    public void click_Move_Down_Sort_Icon_Row2() {
        int attempts = 0;
        while (attempts < 2) {
            try {
                click(move_down_sort_Icon_row2, "Move down sort icon row 2");
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == 2) throw e;
                delayWithGivenTime(1000);
            }
        }
    }

    public void click_Move_To_Top_Sort_Icon_Row2() {
        int attempts = 0;
        while (attempts < 2) {
            try {
                click(move_to_top_sort_Icon_row2, "Move to top sort icon row 2");
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == 2) throw e;
                delayWithGivenTime(1000);
            }
        }
    }

    public void drag_and_drop_Category() {
        int attempts = 0;
        while (attempts < 2) {
            try {
                dragAndDrop(drag_and_drop_icon_row2, drag_and_drop_icon_row1);
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == 2) throw e;
                delayWithGivenTime(1000);
            }
        }
    }

    public boolean is_SortBy_Dropdown_On_Web_Categories_Page_Visible() {
        return isElementDisplayed(sort_by_dropdown, "Sort by dropdown on web admin categories page");
    }

    public void select_SortBy_Dropdown(String sortby) {
        drop_Down(sort_by_dropdown, sortby, "VisibleText", "Sort by dropdown on web admin categories page");
    }

    public String get_Selected_SortBy_Dropdown_Value() {
        return get_Selected_Option(sort_by_dropdown, "Sort by dropdown on web admin categories page");
    }

    public boolean is_Alert_Popup_Displayed() {
        return isElementDisplayed(alert_popup, "Alert popup on web admin categories page");
    }

    public String get_Alert_Popup_Confirmation_Code_Text() {
        String code = "";
        String text = get_Element_Text(alert_message, "Alert popup text on web admin categories page");

        Pattern pattern = Pattern.compile("Type\\s+(\\d+)\\s+to confirm");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            code = matcher.group(1);
        }
        return code;
    }

    public void enter_Confirmation_Code(String confirmationcode) {
        ClickAndType(confirmation_code_textbox, confirmationcode, "Confirmation code text box");
    }

    public void click_Cancel_Button_On_Alert_Popup() {
        click(cancel_button, "Cancel button on alert popup");
    }

    public void click_OK_Button_On_Alert_Popup() {
        click(ok_button, "OK button on alert popup");
    }

}
