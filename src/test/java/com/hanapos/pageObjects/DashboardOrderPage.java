package com.hanapos.pageObjects;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hanapos.utilities.PageLoadLoggerUtils;
import io.qameta.allure.Allure;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardOrderPage extends TestBaseClass {
    private CashAndCarryPaymentPage cashandcarrypayment;

    public DashboardOrderPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //=================================== Dashboard Order Page Web Elements ===========================================
    @FindBy(xpath = "//li[@class='navbar-right-item navbarsearch']//input")
    private WebElement Global_search_OrderPage;

    @FindBy(xpath = "(//ul[@class='typeahead dropdown-menu'])[1]//li//a")
    private List<WebElement> listOfGlobal_search_OrderPage;

    @FindBy(xpath = "//span[@class='set-invoice-number']")
    private List<WebElement> listOfInvoiceNumber;

    @FindBy(xpath = "//div[@id='order-info-full-view']//div[@id='full-view-modal-body']")
    private WebElement order_details_invoice_popup_all_order_page;

    @FindBy(xpath = "//div[@class='modal-close-icon']//i")
    private WebElement close_order_details_invoice_popup_all_order_page;

    @FindBy(xpath = "//span[@class='set-invoice-number']/following::td[1]")
    private List<WebElement> listOfStatus_on_all_OrderPage;

    @FindBy(xpath = "//div[@class='dashborad-tabs right-side-tabs']//span[normalize-space()='Pending Deliveries']")
    private WebElement pending_deliveries_tab_on_all_order_page;

    @FindBy(xpath = "//div[@class='dashborad-tabs right-side-tabs']//span[normalize-space()='Pending Confirmations']")
    private WebElement pending_confirmations_tab_on_all_order_page;

   /* @FindBy(xpath = "//span[@class='set-invoice-number' and contains(text(),'" + invoicenumber + "')]/following::td[1]")
    private WebElement status_on_all_OrderPage;*/

    @FindBy(xpath = "//td[contains(text(),'Walkin Sales')]")
    private List<WebElement> listOfWalkinSales;

    @FindBy(xpath = "//td[contains(text(),'Phone Order')]")
    private List<WebElement> listOfPhoneOrder;

    @FindBy(xpath = "//td[contains(text(),'Pick Up')]")
    private List<WebElement> listOfPickUp;

    @FindBy(xpath = "//td[contains(text(),'Cash')]")
    private List<WebElement> listOfCashMOP;

    @FindBy(xpath = "//td[contains(text(),'Invoice/House Account')]")
    private List<WebElement> listOfInvoiceHouseAccount;

    @FindBy(xpath = "//td[contains(text(),'Paid Outside Hana')]")
    private List<WebElement> listOfPaidOutsideHana;

    @FindBy(xpath = "//td[contains(text(),'Gift Card')]")
    private List<WebElement> listOfGiftCard;

    @FindBy(xpath = "//td[contains(text(),'Donation')]")
    private List<WebElement> listOfDonation;

    @FindBy(xpath = "//table[@role='grid']//tr[1]//td[4]")
    private List<WebElement> listOfOrderStatus;

    @FindBy(xpath = "//table[@role='grid']//tr[1]//td[4]")
    private WebElement Status_on_OrderPage;

    @FindBy(xpath = "//table[@role='grid']//tr[1]//td[4]")
    private List<WebElement> ListOfStatus_on_OrderPage;

    @FindBy(xpath = "//table[@role='grid']//tr//td[@class='set-order-detail-text']")
    private List<WebElement> listOfOrderDetail;

    @FindBy(xpath = "(//table[1]/tbody[1]/tr[1]/td[5])[1]")
    private List<WebElement> firstrowOfOrderDetail;

    @FindBy(xpath = "//span[@class='set-value-column-text-bold']")
    private List<WebElement> listOfInvoiceAmountValue;

    @FindBy(xpath = "//table[@role='grid']//tr[1]//td[7]")
    private List<WebElement> listOfSenderCustomer;

    @FindBy(xpath = "//table[@role='grid']//tr[1]//td[5]")
    private List<WebElement> listOfOrderDetailOnInvoicePopup;

    @FindBy(xpath = "//span[normalize-space()='Pick Up Walkin Sales']")
    private WebElement deliveryPopup;

    @FindBy(xpath = "//span[@class='CustType']")
    private WebElement deliveryCustType;

    @FindBy(xpath = "//strong[@class='set-sender-reciver-desc']")
    private WebElement custAndcompyNameOnDeliveryPopup;

    @FindBy(xpath = "(//p[@class='set-sender-reciver-desc remove-margin'])")
    private WebElement CustAddressOnDeliveryPopup;

    @FindBy(xpath = "//span[@class='custPhoneNum']//a")
    private WebElement CustPhoneNumOnDeliveryPopup;

    @FindBy(xpath = "//span[@class='custemail']//a")
    private WebElement CustEmailOnDeliveryPopup;

    @FindBy(xpath = "//div[@class='modal-close-icon']")
    private WebElement DeliveryPopupCloseIcon;

    @FindBy(xpath = "//a[@href='/Dashboard/CustomerNew']")
    private WebElement CustomerMenu;

    @FindBy(xpath = "//span[@class='RecipientDetails test']")
    private WebElement RecipientName_InvPopup;

    @FindBy(xpath = "//span[@class='RecipientPhone']")
    private WebElement RecipientPhone_InvPopup;

    @FindBy(css = "p.item-name")
    private List<WebElement> itemnamelist_on_invoicepopup;

    @FindBy(xpath = "//span[@class='set-receiveraddress-font']")
    private WebElement RecipientAddress_InvPopup;

    @FindBy(xpath = "//small[@class='set-color-ordertype']//span")
    private WebElement OrderType_On_InvPopup;

    @FindBy(xpath = "(//div[@class='list-group-item no-padding']//p)[3]") //[3] - removed
    private WebElement confidentialReq_Message_DeliveryPopup;

    @FindBy(xpath = "(//div[@class='list-group-item no-padding']//p)")
    private WebElement wirein_confidentialReq_Message_DeliveryPopup;

    @FindBy(xpath = " //span[contains(text(),'Occasion')]/following::div[1]")
    private WebElement occasion_InvPopup;

    @FindBy(xpath = "//h3[@class='CardMessageOccation']//following::div[2]")
    private WebElement cardmessage_InvPopup;

    @FindBy(css = "(//p[contains(@class,'item-name')])[2]")
    private WebElement itemname_2;

    @FindBy(xpath = "//p[@class='item-name text-wrap']")
    private WebElement productTab_ProductDescription_OnInvPopup;

    @FindBy(xpath = "(//div[@Class='PadLeft text-left no-padding'])[3]")
    private WebElement sourcecode_OnInvPopup;

    @FindBy(xpath = "(//div[contains(@class, 'sender-reciver-tbl')])[2]")
    private WebElement splInstruction_OnInvPopup;

    @FindBy(xpath = "(//div[contains(@class, 'sender-reciver-tbl')])[3]")
    private WebElement driverInstruction_OnInvPopup;

    @FindBy(xpath = "//small[@class='set-color-ordertype']//span")
    private WebElement deliveryType_OnInvoicePopup;

    @FindBy(xpath = "//td[contains(text(),'MOP:')]//following-sibling::td")
    private List<WebElement> ListOfMOP_OnOrderPageTable;

    @FindBy(xpath = "//table[@class='set-boarder-zero set-padding-order-detail']//tbody//tr[2]//td[2]")
    private List<WebElement> ListOfDeliveryType_OnOrderPageTable;

    @FindBy(xpath = "//table[@class='set-boarder-zero set-padding-order-detail']//tbody//tr[1]//td[2]")
    private List<WebElement> ListOfOrderType_OnOrderPageTable;

    @FindBy(xpath = "//td[contains(@class,'order-status')]")
    private WebElement list_of_OrderStatus_OnOrderPage;

    @FindBy(xpath = "//table[@class='set-boarder-zero set-padding-order-detail'][2]//tbody//tr[1]//td[2]")
    private List<WebElement> Wire_ListOfOrderType_OnOrderPageTable;

    @FindBy(xpath = "//td[6]")
    private List<WebElement> ListOfRecipient_OnOrderPageTable;

    @FindBy(xpath = "//td/span[@class='set-invoice-number']")
    private List<WebElement> InvoiceNumbers_list;

    @FindBy(xpath = "//td[contains(text(),'Sales Tax')]/following::td[1]")
    private WebElement salesTax_OnDeliveryPopup_Invoice;

    @FindBy(xpath = "//div[@class='fa fa-flag IsFlagColor']")
    private WebElement RedflagIcon_OnOrderpage;

    @FindBy(xpath = "//td[contains(@class,'hana-grid-row-fullview order-status-col')]")
    private WebElement OrderStatus_OnOrderPage;

    @FindBy(xpath = "//span[@class='label label-info']")
    private WebElement OrderStatus_InvPopup;

    @FindBy(xpath = "(//div[@class=' PadLeft text-left no-padding'])[3]")
    private WebElement driverName_InvPopup;

    @FindBy(xpath = "//a[text()='Dispatch']")
    private WebElement dispatchTab_InvPopup;

    @FindBy(xpath = "//p[@class='allinform']")
    private WebElement dispatch_ConfirmationMessage_InvPopup;

    @FindBy(xpath = "//div[@id='dispatchhistorybind']//b[text()='Delivery Confirmation Source ']/following::span[1]")
    private WebElement delivery_confirmation_source;

    @FindBy(css = "p.allinform")
    private WebElement confirmation_message_on_dispatch_tab;

    @FindBy(css = "p.infor-SignedBy")
    private WebElement delivery_code_on_dispatch_tab;

    @FindBy(css = "p.infor-note")
    private WebElement delivery_notes_on_dispatch_tab;

    @FindBy(css = "div .set-msg-border-container")
    private List<WebElement> activities_tab_Messages;

    @FindBy(xpath = "(//span[@class='set-font-msg-desc'])[1]")
    private WebElement activities_tab_delivery_confirmation_header;

    @FindBy(xpath = "//a[text()='Status']")
    private WebElement statusTab_InvPopup;

    @FindBy(xpath = "(//table[@class='table table-striped']//tbody)[7]//tr[1]/td[1]")
    private WebElement statusTable_row1_Date;

    @FindBy(xpath = "(//table[@class='table table-striped']//tbody)[7]//tr[1]/td[2]")
    private WebElement statusTable_row1_statusType;

    @FindBy(xpath = "//div[@class='set-msg-border-container']//span")
    private WebElement deliveryontruck_msg_on_activities_tab;

    @FindBy(xpath = "//span[@class='set-font-msg-desc']")
    private WebElement deliveryontruck_msg_on_Activity;

    @FindBy(xpath = "//div[@class='set-msg-border-container']//b")
    private WebElement deliveryontruck_dateandtime_on_Activity;

    @FindBy(xpath = "//div[@class='set-msg-border-container']")
    private WebElement deliveryontruck_fullmsg_on_Activity;

    @FindBy(xpath = "//div[@class='set-msg-border-container']")
    private WebElement activities_section_fullmsg;

    @FindBy(xpath = "(//div[@id='statushistorybind']//td)[2]")
    private WebElement status_description_row1;

    //================Payment tab invoice popup webelements =========================
    @FindBy(xpath = "//a[text()='Payments']")
    private WebElement paymentTab_deliveryInvoicePopup;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[1]//td[3]")
    private WebElement paymentdescription_row1;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[2]//td[3]")
    private WebElement paymentdescription_row2;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[3]//td[3]")
    private WebElement paymentdescription_row3;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[4]//td[3]")
    private WebElement paymentdescription_row4;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[5]//td[3]")
    private WebElement paymentdescription_row5;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[6]//td[3]")
    private WebElement paymentdescription_row6;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[7]//td[3]")
    private WebElement paymentdescription_row7;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[1]//td[1]")
    private WebElement paymentdate_row1;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[2]//td[1]")
    private WebElement paymentdate_row2;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[3]//td[1]")
    private WebElement paymentdate_row3;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[4]//td[1]")
    private WebElement paymentdate_row4;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[5]//td[1]")
    private WebElement paymentdate_row5;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[6]//td[1]")
    private WebElement paymentdate_row6;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[7]//td[1]")
    private WebElement paymentdate_row7;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[1]//td[5]")
    private WebElement payment_amount_row1;

    @FindBy(xpath = "(//div[@id='orderpaymentbind']//table[@class='table table-striped']//td[5])[1]")
    private WebElement payment_amount_row2;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[3]//td[5]")
    private WebElement payment_amount_row3;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[4]//td[5]")
    private WebElement payment_amount_row4;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[5]//td[5]")
    private WebElement payment_amount_row5;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[6]//td[5]")
    private WebElement payment_amount_row6;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tbody//tr[7]//td[5]")
    private WebElement payment_amount_row7;

    @FindBy(xpath = "//a[@class='btn btn-white dropdown-toggle']")
    private WebElement order_action_menu;

    @FindBy(xpath = "//li/a[contains(text(),'Fulfillment Details')]")
    private WebElement fulfillment_sub_menu_element_in_action_button;

    @FindBy(xpath = "//strong[contains(text(),'Fulfillment Details')]")
    private WebElement Fulfillment_popup_element;

    @FindBy(xpath = "//select[@id='actionFulfillmentDetailsDesigner']")
    private WebElement designer_dropdown_element;

    @FindBy(xpath = "//div[@class='form-group col-md-6']/span/small")
    private WebElement flower_design_complete_toggle_button_element;

    @FindBy(xpath = "//button[@class='btn btn-sm btn-primary m-t-n-xs btn-mt-3']")
    private WebElement fulfillment_submit_button;

    @FindBy(xpath = "//div[@class='ibox-tools']/a[2]/i")
    private WebElement fulfillment_close_icon_element;

    @FindBy(xpath = "(//table/thead/tr/th[@data-title = 'Sr.No.'])[1]")
    private WebElement pending_deliveries_table_column_header_srno;

    @FindBy(xpath = "(//table/thead/tr/th[2]/a)[4]")
    private WebElement getPending_deliveries_table_column_header_invoice;

    @FindBy(xpath = "(//table/thead/tr/th[3]/a)[4]")
    private WebElement getPending_deliveries_table_column_header_recipient;

    @FindBy(xpath = "(//table/thead/tr/th[4]/a)[3]")
    private WebElement getPending_deliveries_table_column_header_city;

    @FindBy(xpath = "(//table/thead/tr/th[5]/a)[3]")
    private WebElement getPending_deliveries_table_column_header_state;


    //================= Orders - Action menu Elements ===========================
    @FindBy(xpath = "//tbody/tr[1]/td[2]/div[1]/div[1]/div[1]/a[1]/span[1]")
    private WebElement actionmenu_row1;

    @FindBy(xpath = "//ul[@class='action-menu-links CustomActionUl']")
    private WebElement ListOfAction_submenu_row1;

    @FindBy(xpath = "//ul[@class='action-menu-links CustomActionUl']//li//a")
    private List<WebElement> listOfAction_submenus_row1;

    @FindBy(xpath = "(//ul[@class='action-menu-links CustomActionUl']//li//a[contains(@class,'cancelorder')])[1]")
    private WebElement cancelorder_submenu_row1;

    @FindBy(xpath = "//h2[text()='Transferring to Proposal page...']")
    private WebElement transferring_to_proposal_page_popup;

    @FindBy(xpath = "//*[text()='OK']")
    private WebElement ok_button_on_transferring_to_proposal_page_popup;

    @FindBy(xpath = "//div[@id='order-update']//h5//strong")
    private WebElement cancel_order_popup_title;

    @FindBy(xpath = "(//ul[@class='action-menu-links CustomActionUl']//li//a[contains(@class,'transferorder')])[1]")
    private WebElement transferorder_submenu;

    @FindBy(xpath = "(//ul[@class='action-menu-links CustomActionUl']//li//a[contains(@class,'redelivery')])")
    private WebElement redelivery_submenu;

    @FindBy(xpath = "//label[text()='This will change the status of this order and put it back in the Pending Deliveries status. Are you sure?']")
    private WebElement redelivery_confirmation_message;

    @FindBy(xpath = "//input[@id='reDevDate']")
    private WebElement redelivery_newdeliverydate;

    @FindBy(css = "div[class='datepicker-days'] th[class='datepicker-switch']")
    private WebElement deliverydate_monthyear_on_advancedispatchpage;

    @FindBy(css = "div[class='datepicker-days'] th[class='next']")
    private WebElement deliverydate_nextbutton_on_advancedispatchpage;

    @FindBy(xpath = "//div[@class='datepicker-days']//table//tbody//tr//td[@class='day' or @class='active day' or @class='today day' or @class='today active day']")
    private List<WebElement> listofdays_on_deliverydate_datepicker;

    @FindBy(xpath = "//div[@class='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-bottom']")
    private WebElement datepicker_on_deliverydate_advancedispatch;

    @FindBy(css = "button[id='btnSubmitRedelviery']")
    private WebElement redelivery_popup_submit_button;

    @FindBy(xpath = "(//ul[@class='action-menu-links CustomActionUl']//li//a[contains(@data-actiontype,'fulfillment')])[1]")
    private WebElement fulfillment_submenu;

    @FindBy(xpath = "//div[@id='grid-action-modal']//div[@class='modal-body']")
    private WebElement fulfillmentpopup;

    @FindBy(xpath = "//h5//strong[text()='Fulfillment Details']")
    private WebElement fulfillmentpopup_title;

    @FindBy(xpath = "//select[@id='actionFulfillmentDetailsDesigner']")
    private WebElement designer_dropdown_on_fulfillment_details_popup;

    @FindBy(xpath = "//label[text()='Flower Design Completed']/following-sibling::span")
    private WebElement flower_design_completed_toogle_button;

    @FindBy(xpath = "//label[@for='actionNotifyCustomer']/following::span[contains(@class,'switchery-disabled')]")
    private WebElement notify_customer_toogle_button_disabled;

    @FindBy(xpath = "//label[@for='actionNotifyCustomer']/following::span[1]")
    private WebElement notify_customer_toogle_button;

    @FindBy(css = "div .fullFillNote")
    private WebElement customer_notification_note;

    @FindBy(xpath = "//label[text()='Design Recipe']/following-sibling::textarea")
    private WebElement design_recipe_textbox_on_fulfillment_details_popup;

    @FindBy(xpath = "//label[text()='Design Recipe']/following::button//strong")
    private WebElement submit_button_on_fullfillment_details_popup;

    @FindBy(css = "#grid-action-modal-body .ibox-tools i.close")
    private WebElement close_icon_fullfilment_details_popup;

    @FindBy(xpath = "//div[@id='tranfar-order']/child::div//h5")
    private WebElement redelivery_popup_title;

    @FindBy(xpath = "//div[@id='tranfar-order']/child::div//button//strong[contains(text(),'No')]")
    private WebElement redelivery_popup_no_button;

    @FindBy(xpath = "//div[@id='tranfar-order']/child::div//button//strong[contains(text(),'Yes')]")
    private WebElement redelivery_popup_yes_button;

    @FindBy(xpath = "//h5[@class='no-wrap-text margin-0']//strong")
    private WebElement cancelorder_popup;

    @FindBy(xpath = "//span[@id='orderupdate-confirmmsg']")
    private WebElement cancelorder_popup_message;

    @FindBy(xpath = "//select[@id='CancelOrderReasonSelect']")
    private WebElement cancelorder_reason_dropdown;

    @FindBy(xpath = "//a[@id='btncancelupdate']")
    private WebElement cancelorderpopup_no_button;

    @FindBy(xpath = "//a[@id='btnconfirmupdate']")
    private WebElement cancelorderpopup_yes_button;

    @FindBy(xpath = "//h2[contains(text(),'Verify Password')]")
    private WebElement cancelorderpopup_verify_password_popup;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement cancelorderpopup_password_textboxfield;

    @FindBy(xpath = "//button[@class='confirm']")
    private WebElement cancelorder_okbutton_verify_password_popup;

    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement toaster_message_text;

    @FindBy(xpath = "//div[@class='set-msg-border-container']")
    private WebElement activitytab_cancelled_orderupdate_text;

    @FindBy(xpath = "//div[@class='OrderStatus text-center']//span")
    private WebElement orderstatus_text_In_Center_TopOfInvoice;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tr[1]//td[2]")
    private WebElement CancelledOrderStatus_payment_refund_description_row1;

    @FindBy(xpath = "//div[@id='orderpaymentbind']//table//tr[td[contains(text(), 'Refund')]]/td[last()]")
    private WebElement CancelledOrderStatus_payment_refund_amount_row1;

    @FindBy(xpath = "(//table[@class='table table-striped'])[6]//tr//td[contains(text(),'Cancelled')]")
    private WebElement StatusTab_Description_row2;

    @FindBy(xpath = "//ul[@class='action-menu-links CustomActionUl']//li//a[contains(text(),'Update Order')]")
    private WebElement updateorder_submenu_row1;

    @FindBy(css = "div#order-update")
    private WebElement update_order_popup;

    @FindBy(xpath = "//h5[@class='no-wrap-text margin-0']//strong")
    private WebElement updateorder_popup_label;

    @FindBy(xpath = "//div[@class='col-sm-12 col-xs-12 product-info-row RemoveLeftRightPadding']//div[4]//input")
    private WebElement product_price_row1_updateorder_popup;

    @FindBy(css = "#balanceAmountLabel")
    private WebElement balanceamountvalue_on_paymentgrid;

    @FindBy(css = "#lblmop")
    private WebElement mop_on_update_order_popup;

    @FindBy(css = "#UpdateDiscountAmount")
    private WebElement discount_on_updateorder_popup;

    @FindBy(css = "#UpdateOrderProductTotal")
    private WebElement sub_total_on_updateorder_popup;

    @FindBy(css = "#UpdateOrderRelayCharges")
    private WebElement relay_fee_on_updateorder_popup;

    @FindBy(css = "#deliveryChargesLabel")
    private WebElement delivery_fee_on_updateorder_popup;

    @FindBy(css = "#salesTaxAmountLabel")
    private WebElement sales_tax_on_updateorder_popup;

    @FindBy(css = "#UpdateOrderTotalAmount")
    private WebElement grand_total_on_updateorder_popup;

    @FindBy(xpath = "//select[@id='UpdateOrderRefundReasonSelect']")
    private WebElement updateorder_reason_dropdown_InUpdateOrderpopup;

    @FindBy(xpath = "//div[@id='UpdateOrderPendingPaymentActionLabel']//span")
    private WebElement updateorder_Refund_amount;

    @FindBy(css = "span#UpdateOrderSaveButton")
    private WebElement updateorder_button;

    @FindBy(xpath = "//td[contains(text(),'Cash Refund')]//following-sibling::td[2]")
    private WebElement cash_refund_amount_paymentTab;

    @FindBy(xpath = "//a[@class='product-row-action-btn']")
    private WebElement product_row_action_add_button;

    @FindBy(xpath = "(//div[@class='form-group col-sm-2 RemoveLeftRightPadding']//input)[1]/following::div[8]//a//i")
    private WebElement product_row1_cross_icon_updateorder_popup;

    @FindBy(xpath = "//h2[text()='Are you sure you want to remove this Product?']")
    private WebElement remove_product_confirmation_popup;

    @FindBy(xpath = "//button[text()='No']")
    private WebElement remove_product_confirmation_popup_no_button;

    @FindBy(xpath = "//button[text()='Yes']")
    private WebElement remove_product_confirmation_popup_yes_button;

    @FindBy(xpath = "(//input[contains(@class,'itemcode')])[1]")
    private WebElement product_itemcode_row1_updateorder_popup;

    @FindBy(xpath = "(//textarea[contains(@class,'description')])[1]")
    private WebElement product_description_row1_updateorder_popup;

    @FindBy(xpath = "(//input[contains(@class,'units')])[1]")
    private WebElement product_quantity_row1_updateorder_popup;

    @FindBy(xpath = "(//input[contains(@class,'price')])[1]")
    private WebElement row1_product_price_updateorder_popup;

    @FindBy(xpath = "(//input[contains(@class,'extprice')])[1]")
    private WebElement row1_product_extprice_updateorder_popup;


    @FindBy(xpath = "(//div[@class='form-group col-sm-2 RemoveLeftRightPadding']//input)[3]")
    private WebElement product_itemcode_row2_updateorder_popup;

    @FindBy(xpath = "//ul[@id='ui-id-11']//li//div")
    private List<WebElement> listOfProductItemcode_row2_updateorder_popup;

    @FindBy(xpath = "(//div[@class='form-group col-sm-2 RemoveLeftRightPadding']//textarea)[2]")
    private WebElement product_description_row2_updateorder_popup;

    @FindBy(xpath = "(//div[@class='form-group col-sm-1 RemoveLeftRightPadding has-success']//input)[3]")
    private WebElement product_quantity_row2_updateorder_popup;

    @FindBy(xpath = "(//div[@class='form-group col-sm-1 RemoveLeftRightPadding has-success']//input)[4]")
    private WebElement product_price_row2_updateorder_popup;

    @FindBy(xpath = "//select[@id='UpdateOrderPaymentReasonSelect']")
    private WebElement updateorder_payment_reason_dropdown_InUpdateOrderpopup;

    @FindBy(xpath = "//h2[@id='UpdateOrderProcessedMessageText']")
    private WebElement OrderUpdatedSuccessfully_Popup;

    @FindBy(xpath = "//div[@class='viewselector']/button/span[@class='ddlFilterSavedView']")
    private WebElement dashboardviewtext;

    @FindBy(xpath = "//div[@id='gvRecentOrdersNew']/div[2]/table/tbody/tr/td[3]")
    private List<WebElement> all_orders_invoice_lists;

    @FindBy(xpath = "//div[@class='filters']")
    private WebElement filters_icon_on_all_ordersPage;

    @FindBy(xpath = "//button[@id='btnRefreshsavedview' and contains(text(),'Cancel')]")
    private WebElement cancel_button_on_filters_popup;

    @FindBy(xpath = "//div[contains(@class,'viewselector')]//button")
    private WebElement views_dropdown_on_all_ordersPage;

    @FindBy(xpath = "//ul[@id='FilterList']//li//a")
    private List<WebElement> list_of_filters_options_on_all_ordersPage;

    @FindBy(xpath = "//span[@class='ddlFilterSavedView']")
    private WebElement view_displayed_option;

    @FindBy(xpath = "//button[@id='deliveryConfiurationForOutDeliveryNew' and text()='Confirm Delivery']")
    private WebElement confirm_delivery_button_on_pendingconfirmation;

    @FindBy(xpath = "//div[@id='clsNonDeliveries']//span[normalize-space(.)='Non Deliveries']")
    private WebElement non_deliveries_tab;

    @FindBy(xpath = "//button[normalize-space(.)='Reschedule']/preceding::div[@id='btnNondliveryOptionBtns']")
    private WebElement reschedule_button_on_non_deliveriestab;

    @FindBy(xpath = "//h4[normalize-space()='Reschedule Orders']")
    private WebElement reschedule_orders_popup_header;

    @FindBy(xpath = "//input[@id='txtnewDelDate']")
    private WebElement choose_new_delivery_date_on_reschedulepopup;

    @FindBy(xpath = "//h4[normalize-space()='Reschedule Orders']/following::button[normalize-space(.)='Reschedule']")
    private WebElement reschedule_button_on_reschedulepopup;

    @FindBy(xpath = "//div[@id='clsUnconfirm']//span[contains(text(),' Pending Confirmations ')]")
    private WebElement pending_confirmation_tab;

    @FindBy(xpath = "//div[@id='dashboard_datepicker']//input[@id='txtDelDate']")
    private WebElement datepicker_dateselection_on_allorderspage;

    @FindBy(xpath = "//div[@class='datepicker-days']//th[@class='datepicker-switch']")
    private WebElement monthandyear_dateselection;

    @FindBy(xpath = "//div[@class='datepicker-days']//th[@class='next']")
    private WebElement nextarrow_dateselection;

    @FindBy(xpath = "//div[@class='datepicker-days']//tbody//tr//td[(@class='day') or @class='active day' or @class='today day']")
    private List<WebElement> listofdays_dateselection;

    @FindBy(xpath = "//div[@id='ddlFilterMOP_chosen']//ul//li//input")
    private WebElement mop_dropdown_in_filters_on_all_ordersPage;

    @FindBy(xpath = "//div[@id='ddlFilterMOP_chosen']//ul[@class='chosen-results']//li")
    private List<WebElement> list_of_mop_options_in_filters_on_all_ordersPage;

    //    //div[@id='ddlFilterMOP_chosen']//ul[@class='chosen-choices']//li//span
    @FindBy(xpath = "//div[@id='ddlFilterMOP_chosen']//ul[@class='chosen-choices']//li//span")
    private WebElement selected_mop_option_in_filters_on_all_ordersPage;

    @FindBy(xpath = "//button[contains(text(),'Apply')]")
    private WebElement apply_button_in_filters_on_all_ordersPage;

    //  (//select[@id='ddlFilterStatus']/following-sibling::div//ul//li//span)[1]
    @FindBy(xpath = "//div[@id='ddlFilterStatus_chosen']//ul[@class='chosen-choices']//li//span")
    private WebElement status_option_in_filters_on_all_orderspage;

    @FindBy(xpath = "(//div[@id='ddlFilterStatus_chosen']//ul[@class='chosen-choices']//li//span)[1]")
    private WebElement status_option1_in_filters_on_all_orderspage;

    @FindBy(xpath = "(//div[@id='ddlFilterStatus_chosen']//ul[@class='chosen-choices']//li//span)[2]")
    private WebElement status_option2_in_filters_on_all_orderspage;

    @FindBy(xpath = "(//div[@id='ddlFilterStatus_chosen']//ul[@class='chosen-choices']//li//span)[3]")
    private WebElement status_option3_in_filters_on_all_orderspage;

    @FindBy(xpath = "(//div[@id='ddlFilterDelivery_chosen']//ul[@class='chosen-choices']//li//span)[1]")
    private WebElement delivery_option1_in_filters_on_all_orderspage;

    @FindBy(xpath = "(//div[@id='ddlFilterDelivery_chosen']//ul[@class='chosen-choices']//li//span)[2]")
    private WebElement delivery_option2_in_filters_on_all_orderspage;

    @FindBy(xpath = "(//div[@id='ddlFilterDelivery_chosen']//ul[@class='chosen-choices']//li//span)[3]")
    private WebElement delivery_option3_in_filters_on_all_orderspage;

    @FindBy(xpath = "//button[@id='deliveryConfiurationNew' and text()='Confirm Delivery']")
    private WebElement confirm_delivery_button_on_pendingdeliveries_view;

    @FindBy(xpath = "//div[contains(@class,'sweet-alert')]//h2")
    private WebElement pickup_confirmation_alert_message;

    @FindBy(xpath = "//div[contains(@class,'sweet-alert')]//div//button[text()='Yes']")
    private WebElement yes_button_on_pickup_confirmation_alert_message;

    @FindBy(xpath = "//div[contains(@class,'sweet-alert')]//div//button[text()='No']")
    private WebElement no_button_on_pickup_confirmation_alert_message;

    @FindBy(xpath = "//input[@id='txtFilterInvoice']")
    private WebElement invoice_textbox_on_filtericon_allorderspage;

    @FindBy(xpath = "//input[@id='txtFilterCustomer']")
    private WebElement customer_textbox_on_filtericon_allorderspage;

    @FindBy(xpath = " //span[normalize-space()='Pending Pickups']")
    private WebElement pending_pickups_tab_on_allorderspage;

    @FindBy(xpath = "//label[text()='Order Type']/following-sibling::div//ul//li[8]")
    private WebElement order_type_dropdown_on_allorderspage;
    //================================== Recipient Section Update Order Popup ============================
    @FindBy(css = "input#UpdateOrderFirstName")
    private WebElement recipient_firstname_textbox_on_updateorder_popup;

    @FindBy(css = "input#UpdateOrderLastName")
    private WebElement recipient_lastname_textbox_on_updateorder_popup;

    @FindBy(css = "input#UpdateOrderAddressLine1")
    private WebElement recipient_addressline1_textbox_on_updateorder_popup;

    @FindBy(css = "input#UpdateOrderPhone1")
    private WebElement recipient_phone_textbox_on_updateorder_popup;

    @FindBy(css = "input#UpdateOrderZipCode")
    private WebElement recipient_zipcode_textbox_on_updateorder_popup;

    @FindBy(css = "input#UpdateOrderCity")
    private WebElement recipient_city_textbox_on_updateorder_popup;

    @FindBy(css = "input#UpdateOrderState")
    private WebElement recipient_state_textbox_on_updateorder_popup;

    @FindBy(css = "select#UpdateOrderCountry")
    private WebElement recipient_country_dropdown_on_updateorder_popup;

    @FindBy(css = "select#UpdateOrderZonesSelect")
    private WebElement recipient_zone_dropdown_on_updateorder_popup;

    @FindBy(css = "select#SalesRepDropdown")
    private WebElement sales_rep_dropdown_on_updateorder_popup;

    @FindBy(css = "select#updateOrderOrderType")
    private WebElement order_type_dropdown_on_updateorder_popup;

    @FindBy(css = "select#UpdateOrderOccassionsSelect")
    private WebElement occasion_dropdown_on_updateorder_popup;

    @FindBy(css = "input#UpdateOrderEmail")
    private WebElement email_textbox_on_updateorder_popup;

    @FindBy(xpath = "//span[text()='Sold By']/following::div[1]")
    private WebElement soldby_label_on_order_invoice_details_popup;

    @FindBy(xpath = "//span[text()='Occasion']/following::div[1]")
    private WebElement occasion_label_on_order_invoice_details_popup;

    @FindBy(xpath = "//small[@class='set-color-ordertype']//span")
    private WebElement ordertype_label_on_order_invoice_details_popup;


    //================================== Recurring orders elements ============================
    @FindBy(xpath = "//li[@id='OrderMenu']")
    private WebElement orders_menu;

    @FindBy(xpath = "//a[text()='Standing / Recurring Orders']")
    private WebElement recurring_orders_submenu;

    //================================== Save to funeral log =====================================
    @FindBy(xpath = "//a[text()='Save to Funeral Log']")
    private WebElement savetofunerallog_submenu;

    @FindBy(xpath = "//div[contains(@class,'showSweetAlert')]")
    private WebElement save_to_funeral_log_alert_popup;

    @FindBy(xpath = "//div[contains(@class,'showSweetAlert')]//h2")
    private WebElement savetofuneral_confirmation_popup_header;

    @FindBy(xpath = "//div[contains(@class,'showSweetAlert')]//p//div[1]")
    private WebElement savetofuneral_confirmation_popup_message;

    @FindBy(xpath = "//div[contains(@class,'showSweetAlert')]//p//div[2]")
    private WebElement savetofuneral_confirmation_popup_message2;

    @FindBy(xpath = "//div[contains(@class,'showSweetAlert')]//p//div[3]")
    private WebElement savetofuneral_confirmation_code;

    @FindBy(xpath = "//input[@placeholder='Confirmation code']")
    private WebElement confirmation_code_textbox;

    @FindBy(xpath = "//div[contains(@class,'showSweetAlert')]//button[text()='Cancel']")
    private WebElement cancel_button_on_save_to_funeral_log_alert_popup;

    @FindBy(xpath = "//div[contains(@class,'showSweetAlert')]//button[text()='OK']")
    private WebElement ok_button_on_save_to_funeral_log_alert_popup;

    @FindBy(xpath = "(//div[@class='set-msg-border-container'])[1]")
    private WebElement activities_tab_on_order_update;

    @FindBy(xpath = "(//div[@class='set-msg-border-container'])[2]")
    private WebElement activities_tab_on_order_update_refund_message;

    // ===================== Assign designers Popup =====================
    @FindBy(xpath = "//span[normalize-space()='Orders']")
    private WebElement OrdersMenu;

    @FindBy(xpath = "//a[@class='li_AssignDesignersPopUp dropdown-item']")
    private WebElement assign_designers_button;

    @FindBy(xpath = "//div[@id='AssignDesignerModal']//div[@class='modal-content']")
    private WebElement assign_designers_popup;

    @FindBy(xpath = "//h4[text()='Assign Designers']/preceding::i[1]")
    private WebElement close_icon_on_assign_designers_popup;

    @FindBy(xpath = "//div[@id='AssignDesignerModal']//h4[text()='Assign Designers']")
    private WebElement assign_designers_popup_header;

    @FindBy(xpath = "//select[@id='ddlDesigner']")
    private WebElement select_designer_dropdown_on_assign_designers_popup;

    @FindBy(xpath = "//input[@id='txtFullFilmentDateTime']")
    private WebElement fullfilment_date_time_textbox_on_assign_designers_popup;

    @FindBy(xpath = "//input[@id='txtInvoiceNoOfAssignDesiner']")
    private WebElement scan_order_textbox_on_assign_designers_popup;

    @FindBy(xpath = "//table[@id='CurrentAssignDesigners']//td[2]")
    private WebElement assigned_designer_invoicenumber_on_tablegrid_assign_designers_popup;

    @FindBy(xpath = "//table[@id='CurrentAssignDesigners']//td[3]")
    private WebElement customer_name_assigneddesigner_on_tablegrid_assign_designers_popup;

    @FindBy(xpath = "//button[@id='btnSaveOrderAssignments']")
    private WebElement save_assigner_assignments_button_on_assign_designers_popup;

    //======================= Wire Out Details on order details popup ===========================
    @FindBy(xpath = "//h3[@class='CardMessageOccation' and text()='Wire Out']")
    private WebElement wire_out_message_header;

    @FindBy(xpath = "//div[@class='WireInInfo']")
    private WebElement wire_out_information_section;

    @FindBy(xpath = "//div[@class='WireInInfo']//span[text()=' Filling Shop:']/following::div[1]")
    private WebElement wire_out_filling_shop;

    @FindBy(xpath = "//div[@class='WireInInfo']//span[text()='Filling Shop Name: ']/following::div[1]")
    private WebElement wire_out_filling_shop_name;

    @FindBy(xpath = "//div[@class='WireInInfo']//span[text()='Wire Method: ']/following::div[1]")
    private WebElement wire_out_wire_method;

    @FindBy(xpath = "//div[@class='WireInInfo']//span[text()='Wire Amount:']/following::div[1]")
    private WebElement wire_out_wire_amount;

    //==================== Products Tab on Order Details Popup ======================
    @FindBy(xpath = "(//div[@class='productDetails productDetails-cms'])[1]//p[contains(@class,'item-name')]")
    private WebElement product_itemname_row1;

    @FindBy(xpath = "(//div[@class='productDetails productDetails-cms'])[2]//p[contains(@class,'item-name')]")
    private WebElement product_itemname_row2;

    @FindBy(xpath = "(//div[@class='productDetails productDetails-cms'])[1]//p[contains(@class,'item-Sku')]")
    private WebElement product_itemcode_row1;

    @FindBy(xpath = "(//div[@class='productDetails productDetails-cms'])[2]//p[contains(@class,'item-Sku')]")
    private WebElement product_itemcode_row2;

    @FindBy(xpath = "(//div[@class='productDetails productDetails-cms'])[1]//p[contains(@class,'item-qty')]")
    private WebElement product_itemqty_row1;

    @FindBy(xpath = "(//div[@class='productDetails productDetails-cms'])[2]//p[contains(@class,'item-qty')]")
    private WebElement product_itemqty_row2;

    @FindBy(xpath = "(//div[@class='productDetails productDetails-cms'])[1]//p[contains(@class,'item-price')]")
    private WebElement product_itemprice_row1;

    @FindBy(xpath = "(//div[@class='productDetails productDetails-cms'])[2]//p[contains(@class,'item-price')]")
    private WebElement product_itemprice_row2;

    @FindBy(xpath = "(//table[@id='Atable']//td//span)[1]")
    private WebElement product_description_table_row1;

    @FindBy(xpath = "(//table[@id='Atable']//td//span)[2]")
    private WebElement product_description_table_row2;

    @FindBy(xpath = "//td//span[text()='rrd - Red Rose Deluxe']/following::td[1]")
    private WebElement product_unitprice_table_row1;

    @FindBy(xpath = "//td//span[text()='BalloonsYY - Balloons Small']/following::td[1]")
    private WebElement product_unitprice_table_row2;

    @FindBy(xpath = "//td//span[text()='rrd - Red Rose Deluxe']/following::td[2]")
    private WebElement product_units_table_row1;

    @FindBy(xpath = "//td//span[text()='BalloonsYY - Balloons Small']/following::td[2]")
    private WebElement product_units_table_row2;

    @FindBy(xpath = "//td//span[text()='rrd - Red Rose Deluxe']/following::td[3]")
    private WebElement product_price_table_row1;

    @FindBy(xpath = "//td//span[text()='BalloonsYY - Balloons Small']/following::td[3]")
    private WebElement product_price_table_row2;

    @FindBy(xpath = "//td[normalize-space()='Product Total']/following-sibling::td")
    private WebElement product_total_table;

    @FindBy(xpath = "//td[normalize-space()='Relay']/following-sibling::td")
    private WebElement relay_fee_on_tablegrid;

    @FindBy(xpath = "//td[normalize-space()='Delivery Fee']/following-sibling::td")
    private WebElement delivery_fee_on_tablegrid;

    @FindBy(xpath = "//td[normalize-space()='Discount Amount']/following-sibling::td")
    private WebElement discount_amount_on_tablegrid;

    @FindBy(xpath = "//td[normalize-space()='Sales Tax']/following-sibling::td")
    private WebElement sales_tax_on_tablegrid;

    @FindBy(xpath = "//td[normalize-space()='Total']/following-sibling::td")
    private WebElement total_on_tablegrid;

    @FindBy(xpath = "//td[normalize-space()='Paid Amount']/following-sibling::td")
    private WebElement paid_amount_on_tablegrid;

    @FindBy(xpath = "//td[contains(@class,'DAmountlabel') and normalize-space()='Payment Type']/following-sibling::td")
    private WebElement payment_type_on_tablegrid;

    @FindBy(css = "div .popover-content div .quickview")
    private WebElement quickview_popup;

    @FindBy(css = "div .quickview i.modal-action-container-close-icon")
    private WebElement quickview_popup_close_icon;

    @FindBy(xpath = "//span[text()='Delivered' and @class='Dlabel']/following::div[1]")
    private WebElement delivered_date_time_on_leftside;

    @FindBy(xpath = "//span[text()='Driver' and @class='Dlabel']/following::div[1]")
    private WebElement driver_name_on_leftside;

    //======================================= Dashboard Order Page Functions =======================================

    /**
     * It retrieves the current url of the order page
     *
     * @return It retrieves the Current url value of the order page
     * @Description This function gets the current url of the order page
     * @Author Balaji N
     */
    public String validateDashboardOrderPage() {
        try {
            wait_For_Page_To_Be_Stable(getDriver());
            return getDriver().getCurrentUrl();
        } catch (WebDriverException e) {
            printError(null, "All Orders Page URL", "Error while fetching the current All Orders page URL: " + e.getMessage(), e);
            return "ERROR: Unable to fetch URL";
        }
    }


    /**
     * Get the title of all orders page
     *
     * @return
     */
    public String validate_All_Orders_Page_Title() {
        return getDriver().getTitle();
    }


    /**
     * Enters the provided data on global search in the order page
     *
     * @param globalsearch Provided global search value to be entered
     * @Description: This function will clears and enters the provided data on global search using selenium actions & press the enter key
     * @Author Balaji N
     */
    public void EnterGlobalSearch(String globalsearch_invoice_number) {
        PageLoadLoggerUtils.logPageLoad("Recent Orders Page → Global Order Search", () -> {
            wait_For_Page_To_Be_Stable(getDriver());

            int retryCount = 3;
            int attempt = 0;
            boolean isSuccessful = false;

            while (attempt < retryCount) {
                try {
                    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                    Global_search_OrderPage.clear();
                    delayWithGivenTime(1000);
                    Global_search_OrderPage.sendKeys(globalsearch_invoice_number);
                    delayWithGivenTime(1000);
                    Global_search_OrderPage.sendKeys(Keys.ENTER);

                    System.out.println("✅ Global search value entered: " + globalsearch_invoice_number);
                    isSuccessful = true;
                    break;

                } catch (StaleElementReferenceException | ElementNotInteractableException e) {
                    System.out.println("⚠️ Attempt " + (attempt + 1) + ": Retrying due to exception: " + e.getClass().getSimpleName());
                    delayWithGivenTime(2000); // Give some buffer time before retrying
                } catch (NoSuchElementException e) {
                    throw new NoSuchElementException("❌ Global search box not found or invoice number '" + globalsearch_invoice_number + "' not displayed", e);
                }

                attempt++;
            }

            if (!isSuccessful) {
                throw new RuntimeException("❌ Failed to enter global search after " + retryCount + " attempts for input: " + globalsearch_invoice_number);
            }
        });
    }

    /**
     * Enters the provided data on global search in the order page
     *
     * @param globalsearch Provided global search value to be entered
     * @Description: This function will clears and enters the provided data on global search using selenium actions & press the enter key
     * @Author Balaji N
     */
    public void Enter_Global_Search_On_OrderPage(String globalsearch) {
        js_ClickAndType(Global_search_OrderPage, globalsearch, "Global Search textbox field on order page");
        delayWithGivenTime(1000);
        Global_search_OrderPage.sendKeys(Keys.ENTER);
    }

    /**
     * Validates whether the invoice number is displayed or not on all orders page
     *
     * @param invoicenumber If the invoice number is displayed it returns true otherwise false
     * @Author Balaji N
     */
    public boolean validate_InvoiceNumber_on_AllOrdersPage(String invoiceNumber) {
        wait_For_Page_To_Be_Stable(getDriver());
        boolean invoiceFound = false;
        int maxRetries = 2;
        int retryDelay = 3000;

        By invoiceLocator = By.xpath("//span[@class='set-invoice-number' and text()='" + invoiceNumber + "']");

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(90));
                WebElement invoiceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(invoiceLocator));

                invoiceFound = isElementDisplayed(invoiceElement,
                        "Invoice Number - on All Orders Page Table Grid");

                if (invoiceFound) {
                    System.out.println("✅ Invoice number '" + invoiceNumber + "' is visible on All Orders Page.");
                    break;
                }

            } catch (TimeoutException e) {
                System.err.println("⏳ Attempt #" + attempt + ": Invoice number '" + invoiceNumber +
                        "' was not found on the All Orders page within 90 seconds.");
            } catch (StaleElementReferenceException e) {
                System.err.println("♻️ Attempt #" + attempt + ": Page content was refreshed or the grid reloaded " +
                        "while checking for invoice number '" + invoiceNumber + "'. Retrying...");
            } catch (WebDriverException e) {
                System.err.println("🧨 Attempt #" + attempt + ": A browser interaction issue occurred while " +
                        "searching for invoice number '" + invoiceNumber + "'. Retrying...");
                // Debug log for developers (not shown to business users)
                System.err.println("   [DEBUG] WebDriver error: " + e.getMessage().split("\n")[0]);
            } catch (Exception e) {
                System.err.println("⚠️ Attempt #" + attempt + ": An unexpected problem occurred " +
                        "while looking for invoice number '" + invoiceNumber + "'.");
                // Debug info for logs (but message simplified for readability)
                System.err.println("   [DEBUG] Root cause: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            }

            delayWithGivenTime(retryDelay);
        }

        if (!invoiceFound) {
            String message = "❌ Could not find invoice number '" + invoiceNumber +
                    "' on the All Orders page after " + maxRetries + " attempts. " +
                    "Please verify that the invoice was generated and is listed in the grid.";
            System.err.println(message);

            Allure.addAttachment("Invoice Validation Failed",
                    "Invoice Number: " + invoiceNumber + "\n" +
                            "Retries: " + maxRetries + "\n" +
                            "Final Result: Not Found");
        }

        return invoiceFound;
    }


    /**
     * Clicks the confirm pick-up button for the respective invoice number
     *
     * @param invoiceNumber Invoice number for which the button needs to be clicked
     * @throws NoSuchElementException if the button is not found
     * @Author Balaji N
     */
    public void click_ConfirmPickUp_Button(String invoiceNumber) {
        try {
            WebElement confirmPickUpButton = getDriver().findElement(By.xpath("//span[@class='set-invoice-number' and contains(text(),'" + invoiceNumber + "')]/following::td[1]//div//button"));
            click(confirmPickUpButton, "Confirm Pick Up Button");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Confirm Pick Up Button not found for Invoice: " + invoiceNumber, e);
        }
    }

    /**
     * Verifies if the confirm pick-up button is displayed for the given invoice number
     *
     * @param invoiceNumber
     * @return If the confirm pick-up button is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean verify_Confirm_PickUp_Button_IsDisplayed(String invoiceNumber) {
        WebElement confirmPickUpButton = getDriver().findElement(By.xpath("//span[@class='set-invoice-number' and (text()='" + invoiceNumber + "')]/following::td[1]//div//button"));
        return isElementDisplayed(confirmPickUpButton, "Confirm Pick Up Button");
    }

    /**
     * Retrieves the status of the invoice number on the All Orders page
     *
     * @param invoiceNumber Invoice number for which status needs to be retrieved
     * @return Status of the invoice if displayed; otherwise, returns null
     * @throws NoSuchElementException if the status is not found
     * @Author Balaji N
     */
    public String validate_Status_On_AllOrdersPage(String invoiceNumber) {
        String statusText = null;
        int maxRetries = 3;
        int retryDelay = 5000;

        By statusLocator = By.xpath("//span[@class='set-invoice-number' and contains(text(),'" + invoiceNumber + "')]/following::td[1]");

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                System.out.println("🔄 Attempt #" + attempt + " to validate status for Invoice: " + invoiceNumber);

                // ✅ Wait for the page and JS to settle
                wait_For_Page_To_Be_Stable(getDriver());

                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(90));
                WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(statusLocator));
                isElementDisplayed(statusElement, "Order Status for the Invoice Number " + invoiceNumber + "- on All Orders Page Table Grid");
                // ✅ Get status text
                statusText = get_Element_Text(statusElement, "Invoice Status on All Orders Page");

                if (statusText != null && !statusText.trim().isEmpty()) {
                    System.out.println("✅ Found status for Invoice '" + invoiceNumber + "': " + statusText);
                    break;
                }

            } catch (TimeoutException e) {
                System.err.println("⏳ Timeout: Status not visible for Invoice '" + invoiceNumber + "' after 90s (Attempt #" + attempt + ").");
            } catch (StaleElementReferenceException e) {
                System.err.println("♻️ StaleElementReference: DOM updated during status lookup for Invoice '" + invoiceNumber + "' (Attempt #" + attempt + ").");
            } catch (NoSuchElementException e) {
                System.err.println("❓ NoSuchElement: Status not found for Invoice '" + invoiceNumber + "' (Attempt #" + attempt + ").");
            } catch (WebDriverException e) {
                System.err.println("🧨 WebDriverException while getting status for Invoice '" + invoiceNumber + "' (Attempt #" + attempt + "): " + e.getMessage());
            } catch (Exception e) {
                System.err.println("⚠️ Unexpected error while validating status for Invoice '" + invoiceNumber + "' (Attempt #" + attempt + "): " + e.getMessage());
            }

            delayWithGivenTime(retryDelay); // Retry delay
        }

        if (statusText == null || statusText.trim().isEmpty()) {
            String errorMsg = "❌ Failed to retrieve status for Invoice '" + invoiceNumber + "' after " + maxRetries + " attempts.";
            System.err.println(errorMsg);

            Allure.addAttachment("Invoice Status Validation Failed",
                    "Invoice Number: " + invoiceNumber +
                            "\nRetries: " + maxRetries +
                            "\nStatus:  Not found or empty.");
            throw new NoSuchElementException(errorMsg);
        }

        return statusText;
    }


    /**
     * Validate the designer status is displayed for the order
     *
     * @param invoiceNumber
     * @return
     */
    public String validate_Designed_Status_On_AllOrdersPage(String invoiceNumber) {
        try {
            WebElement statusElement = getDriver().findElement(By.xpath("//span[@class='set-invoice-number' and contains(text(),'" + invoiceNumber + "')]/following::td[1]//span//small"));
            return get_Element_Text(statusElement, "Invoice Status on All Orders Page");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Designer Status not found for Invoice: " + invoiceNumber, e);
        }
    }

    public boolean verify_Designer_Name_On_Status_Cell_Not_Displayed(String invoiceNumber) {
        try {
            // Use a short explicit wait (5s max) for this check
            WebDriverWait shortWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

            WebElement statusElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                    "//span[@class='set-invoice-number' and contains(text(),'" + invoiceNumber + "')]/following::td//span//small"
            )));

            if (statusElement.isDisplayed()) {
                Allure.step("❌ Designer name is unexpectedly visible in the status cell for invoice: " + invoiceNumber);
                System.err.println("[ERROR] Designer name visible for invoice: " + invoiceNumber);
                return false;
            }

            return true;

        } catch (TimeoutException | NoSuchElementException e) {
            // Element not found within 5s → treated as "not displayed"
            Allure.step("✅ Designer name is not shown in the status cell for invoice: " + invoiceNumber);
            return true;

        } catch (Exception e) {
            Allure.step("❌ Error occurred while verifying designer name for invoice " + invoiceNumber + ": " + e.getMessage());
            return false;
        }
    }


    public String validate_Designed_label_Status_On_AllOrdersPage(String invoiceNumber) {
        try {
            WebElement statusElement = getDriver().findElement(By.xpath("//span[@class='set-invoice-number' and contains(text(),'" + invoiceNumber + "')]/following::td[1]//span[2]"));
            return get_Element_Text(statusElement, "Invoice Status on All Orders Page");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Designer Status not found for Invoice: " + invoiceNumber, e);
        }
    }

    /**
     * Clicks the status cell on all orders page
     *
     * @param invoiceNumber
     * @Author Balaji N
     */
    public void click_Status_Cell_On_AllOrdersPage(String invoiceNumber) {
        int maxAttempts = 3;
        int attempt = 0;
        boolean clickedAndPopupDisplayed = false;

        By locator = By.xpath("//span[@class='set-invoice-number' and contains(text(),'" + invoiceNumber + "')]");  //  /following::td[9]

        while (attempt < maxAttempts && !clickedAndPopupDisplayed) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                WebElement statusCell = wait.until(ExpectedConditions.elementToBeClickable(locator));
                click(statusCell, "Clicking status cell for invoice number: " + invoiceNumber);
                delayWithGivenTime(500);

                if (!isElementDisplayed(order_details_invoice_popup_all_order_page, "All Orders Page - Order details invoice popup")) {
                    System.out.println("Popup not displayed after click. Retrying click...");
                    delayWithGivenTime(1000);
                    statusCell = wait.until(ExpectedConditions.elementToBeClickable(locator)); // re-fetch to avoid stale reference
                    click(statusCell, "Retry clicking status cell for invoice number: " + invoiceNumber);
                }

                if (isElementDisplayed(order_details_invoice_popup_all_order_page, "All Orders Page - Order details invoice popup")) {
                    clickedAndPopupDisplayed = true;
                } else {
                    attempt++;
                }

            } catch (StaleElementReferenceException | ElementNotInteractableException | TimeoutException e) {
                System.out.println("Attempt " + (attempt + 1) + " failed to click or verify popup. Retrying...");
                delayWithGivenTime(1000);
                attempt++;
            }
        }

        if (!clickedAndPopupDisplayed) {
            throw new RuntimeException("Failed to click status cell or load popup for invoice number: " + invoiceNumber + " after " + maxAttempts + " attempts.");
        }
    }


    /**
     * Retrieves the order type for the respective invoice on the All Orders page
     *
     * @param invoiceNumber Invoice number for which order type needs to be retrieved
     * @return Order type if displayed; otherwise, returns null
     * @throws NoSuchElementException if the order type is not found
     * @Author Balaji N
     */
    public String validate_OrderType_On_AllOrdersPage(String invoiceNumber) {
        String orderTypeText = null;
        int attempts = 0;

        while (attempts < 3) {
            try {
                wait_For_Page_To_Be_Stable(getDriver()); // Wait for full page stability
                By orderTypeLocator = By.xpath("//span[@class='set-invoice-number' and contains(text(),'" + invoiceNumber + "')]/following::td[2]//td[(text()='Type: ')]/following-sibling::td");

                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
                WebElement orderTypeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(orderTypeLocator));
                // isElementDisplayed(orderTypeElement, "Order Type for Invoice Number -" + invoiceNumber + " - on All Orders Page Table Grid");
                orderTypeText = get_Element_Text(orderTypeElement, "Order Type for Invoice on All Orders Page");
                return orderTypeText;

            } catch (StaleElementReferenceException e) {
                System.err.println("⚠️ Attempt " + (attempts + 1) + " failed: StaleElementReferenceException while retrieving order type for invoice " + invoiceNumber);
            } catch (TimeoutException e) {
                System.err.println("⚠️ Attempt " + (attempts + 1) + " failed: Timed out waiting for order type for invoice " + invoiceNumber);
            } catch (NoSuchElementException e) {
                System.err.println("⚠️ Attempt " + (attempts + 1) + " failed: Order Type element not found for invoice " + invoiceNumber);
            } catch (WebDriverException e) {
                System.err.println("⚠️ Attempt " + (attempts + 1) + " failed: WebDriverException - " + e.getMessage());
            } catch (Exception e) {
                System.err.println("⚠️ Attempt " + (attempts + 1) + " failed: Unexpected error - " + e.getMessage());
            }

            attempts++;
            delayWithGivenTime(1000); // Small delay before retry
        }

        throw new NoSuchElementException("❌ Failed to locate Order Type for invoice '" + invoiceNumber + "' after 3 retries.");
    }


    /**
     * Retrieves the delivery type for the respective invoice on the All Orders page
     *
     * @param invoiceNumber Invoice number for which delivery type needs to be retrieved
     * @return Delivery type if displayed; otherwise, returns null
     * @throws NoSuchElementException if the delivery type is not found
     * @Author Balaji N
     */
    public String validate_DeliveryType_On_AllOrdersPage(String invoiceNumber) {
        String deliveryTypeText = null;
        int attempts = 0;

        while (attempts < 3) {
            try {
                wait_For_Page_To_Be_Stable(getDriver()); // Ensure page is fully stable

                By deliveryTypeLocator = By.xpath("//span[@class='set-invoice-number' and contains(text(),'" + invoiceNumber + "')]/following::td[2]//td[contains(text(),'Delivery Type: ')]/following-sibling::td");

                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
                WebElement deliveryTypeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryTypeLocator));

                deliveryTypeText = get_Element_Text(deliveryTypeElement, "Delivery Type for Invoice on All Orders Page");
                return deliveryTypeText;

            } catch (StaleElementReferenceException e) {
                System.err.println("⚠️ Attempt " + (attempts + 1) + " failed: StaleElementReferenceException while retrieving delivery type for invoice " + invoiceNumber);
            } catch (TimeoutException e) {
                System.err.println("⚠️ Attempt " + (attempts + 1) + " failed: Timed out waiting for delivery type for invoice " + invoiceNumber);
            } catch (NoSuchElementException e) {
                System.err.println("⚠️ Attempt " + (attempts + 1) + " failed: Delivery Type element not found for invoice " + invoiceNumber);
            } catch (WebDriverException e) {
                System.err.println("⚠️ Attempt " + (attempts + 1) + " failed: WebDriverException - " + e.getMessage());
            } catch (Exception e) {
                System.err.println("⚠️ Attempt " + (attempts + 1) + " failed: Unexpected error - " + e.getMessage());
            }

            attempts++;
            delayWithGivenTime(1000); // Delay between retries
        }

        throw new NoSuchElementException("❌ Failed to locate Delivery Type for invoice '" + invoiceNumber + "' after 3 retries.");
    }


    /**
     * Retrieves the Method of Payment (MOP) for the respective invoice on the All Orders page
     *
     * @param invoiceNumber Invoice number for which MOP needs to be retrieved
     * @return MOP if displayed; otherwise, returns null
     * @throws NoSuchElementException if the MOP is not found
     * @Author Balaji N
     */
    public String validate_MOP_On_AllOrdersPage(String invoiceNumber) {
        int retries = 2;
        By mopLocator = By.xpath("//span[@class='set-invoice-number' and contains(text(),'" + invoiceNumber + "')]/following::td[2]//td[contains(text(),'MOP: ')]/following-sibling::td");

        while (retries-- > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                WebElement mopElement = wait.until(ExpectedConditions.visibilityOfElementLocated(mopLocator));
                isElementDisplayed(mopElement, "MOP for Invoice Number " + invoiceNumber + "- on All Orders Page Table Grid");

                String mopValue = get_Element_Text(mopElement, "MOP for Invoice [" + invoiceNumber + "] on All Orders Page");

                if (mopValue == null || mopValue.trim().isEmpty()) {
                    logFail("❌ MOP value is empty for Invoice [" + invoiceNumber + "] on All Orders Page.");
                } else {
                    // logPass("✅ MOP for Invoice [" + invoiceNumber + "] on All Orders Page is: " + mopValue);
                }

                return mopValue;

            } catch (StaleElementReferenceException | TimeoutException e) {
                System.err.println("⚠️ Retry due to [" + e.getClass().getSimpleName() + "] while locating MOP for invoice [" + invoiceNumber + "]. Remaining attempts: " + retries);
            } catch (NoSuchElementException e) {
                logFail("❌ MOP element not found for Invoice [" + invoiceNumber + "] on All Orders Page.");
                return "";
            } catch (Exception e) {
                logFail("❌ Unexpected error while fetching MOP for Invoice [" + invoiceNumber + "]: " + e.getMessage());
                return "";
            }
        }

        logFail("❌ Failed to retrieve MOP for Invoice [" + invoiceNumber + "] after retries.");
        return "";
    }

    /**
     * Clicks the checkbox for the respective invoice on the All Orders page
     *
     * @param invoiceNumber Invoice number for which checkbox needs to be clicked
     * @throws NoSuchElementException if the checkbox is not found
     * @Author Balaji N
     */
    public void Click_Checkbox_on_AllOrdersPage_for_Respective_Invoices(String invoiceNumber) {
        try {
            WebElement checkboxElement = getDriver().findElement(By.xpath("//span[@class='set-invoice-number' and contains(text(),'" + invoiceNumber + "')]/preceding::td[2]//input"));
            click(checkboxElement, "Checkbox for Invoice on All Orders Page");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Checkbox not found for Invoice: " + invoiceNumber, e);
        }
    }

    /**
     * Retrieves the recipient info for the respective invoice on the All Orders page
     *
     * @param invoiceNumber
     * @return If the recipient info is displayed return the recipient info value; otherwise, returns null
     */
    public String validate_Recipient_Info_On_AllOrdersPage(String invoiceNumber) {
        WebElement recipient_info = getDriver().findElement(By.xpath("//span[@class='set-invoice-number' and contains(text(),'" + invoiceNumber + "')]/following::td[9]"));
        isElementDisplayed(recipient_info, "Recipient Info on all orders page");
        return get_Element_Text(recipient_info, "Recipient Info on all orders page");
    }

    public String getInvoiceNumber_Walkin_pickup_Cash_OnOrderPage() {
        // Ensure that listOfWalkinSales, listOfPickUp, and listOfCashMOP contain elements before accessing them
        if (!listOfWalkinSales.isEmpty() && !listOfPickUp.isEmpty() && !listOfCashMOP.isEmpty()) {

            if (listOfWalkinSales.get(0).getText().equals("Walkin Sales")
                    && listOfPickUp.get(0).getText().equals("Pick Up")
                    && listOfCashMOP.get(0).getText().equals("Cash")) {

                if (!listOfInvoiceNumber.isEmpty()) { // Ensure the list is not empty
                    System.out.println("Order Page walkin sales - pickup - Cash - Invoice Number is :" + listOfInvoiceNumber.get(0).getText());
                    return listOfInvoiceNumber.get(0).getText();
                }
            }
        }
        return null;
    }

    public String get_Walkins_Pickup_POH_MOP_DisplayedOnOrderPage() {
        String paidOutsideHana_Invoice = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().equals("Walkin Sales")
                && listOfPickUp.get(0).getText().equals("Pick Up")
                && listOfPaidOutsideHana.get(0).getText().equals("Paid Outside Hana")) {
            paidOutsideHana_Invoice = listOfInvoiceNumber.get(0).getText();
            System.out.println("Paid Outside Hana Invoice number: " + paidOutsideHana_Invoice);
        }
        return paidOutsideHana_Invoice;
    }

    public boolean Validate_POH_MOP_DisplayedOnOrderPage() {
        boolean paidOutsideHana = false;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().equals("Walkin Sales")
                && listOfPickUp.get(0).getText().equals("Pick Up")
                && listOfPaidOutsideHana.get(0).getText().equals("Paid Outside Hana")) {
            delayWithGivenTime(1000);
            paidOutsideHana = listOfPaidOutsideHana.get(0).isDisplayed();
        }
        return paidOutsideHana;
    }

    public String get_Walkins_Pickup_GiftCard_OnOrderPage() {
        String giftcard_Invoice = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().equals("Walkin Sales")
                && listOfPickUp.get(0).getText().equals("Pick Up")
                && listOfGiftCard.get(0).getText().equals("Gift Card")) {
            giftcard_Invoice = listOfInvoiceNumber.get(0).getText();
        }
        return giftcard_Invoice;
    }

    public boolean Validate_GiftCard_MOP_DisplayedOnOrderPage() {
        boolean giftcard_Invoice = false;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().equals("Walkin Sales")
                && listOfPickUp.get(0).getText().equals("Pick Up")
                && listOfGiftCard.get(0).getText().equals("Gift Card")) {
            giftcard_Invoice = listOfGiftCard.get(0).isDisplayed();
        }
        return giftcard_Invoice;
    }

    public String get_Walkins_Pickup_Donation_OnOrderPage() {
        String donation_Invoice = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().equals("Walkin Sales")
                && listOfPickUp.get(0).getText().equals("Pick Up")
                && listOfDonation.get(0).getText().equals("Donation")) {
            delayWithGivenTime(1000);
            donation_Invoice = listOfInvoiceNumber.get(0).getText();
        }
        return donation_Invoice;
    }

    public boolean Validate_Donation_MOP_DisplayedOnOrderPage() {
        boolean donation_Invoice = false;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().equals("Walkin Sales")
                && listOfPickUp.get(0).getText().equals("Pick Up")
                && listOfDonation.get(0).getText().equals("Donation")) {
            donation_Invoice = listOfDonation.get(0).isDisplayed();
        }
        return donation_Invoice;
    }

    public String getStatusOnOrderPage() {
        String status = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().equals("Walkin Sales")
                && listOfPickUp.get(0).getText().equals("Pick Up")
                && listOfDonation.get(0).getText().equals("Donation")) {
            status = listOfOrderStatus.get(0).getText();
        }
        return status;
    }

    /**
     * Retrieves the invoice amount for an order with the following conditions:
     * - Order type: Walkin Sales
     * - Pick-up type: Pick Up
     * - Mode of Payment (MOP): Cash
     *
     * @return The invoice amount as a {@code String} if all conditions are met, otherwise {@code null}.
     * @throws NoSuchElementException    if any required element is not found on the page.
     * @throws IndexOutOfBoundsException if the element lists are empty or accessed incorrectly.
     */
    public String GetInvoiceAmount_Walkin_pickup_Cash_OnOrderPage(String orderType, String deliveryType, String MOP) {
        String invoiceAmt = null;
        try {
            if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains(orderType)
                    && listOfPickUp.get(0).getText().contains(deliveryType)
                    && listOfCashMOP.get(0).getText().contains(MOP)) {
                invoiceAmt = getElementText(listOfInvoiceAmountValue.get(0), "Invoice Amount on Order Page for Walkin Sales - Pick Up - Cash MOP");
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Required element not found on the Order Page: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Element list is empty or index is out of range: " + e.getMessage());
        }
        return invoiceAmt;
    }

    /**
     * Validates whether the invoice number of phone order, Pickup, Cash type is displayed or not on order page based on the order type, delivery type and MOP
     *
     * @return if invoice number is displayed it returns true otherwise false
     * @Author Balaji N
     */
    public boolean Validate_PhoneOrder_Pickup_Cash_InvoiceNumber() {
        try {
            if (!ListOfOrderType_OnOrderPageTable.isEmpty() &&
                    !listOfPickUp.isEmpty() &&
                    !listOfCashMOP.isEmpty() &&
                    !listOfInvoiceNumber.isEmpty()) {
                if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                        && listOfPickUp.get(0).getText().contains("Pick Up")
                        && listOfCashMOP.get(0).getText().contains("Cash")) {
                    return listOfInvoiceNumber.get(0).isDisplayed();
                }
            }
        } catch (IndexOutOfBoundsException | NullPointerException | NoSuchElementException |
                 StaleElementReferenceException e) {
            System.err.println("Exception occurred while validating invoice number - Phone Order, Pickup, Cash: " + e.getMessage());
        }
        return false; // Return false in case of any failure

    }

    /**
     * verify whether the invoice number is displayed or not in the order page based on the delivery type as phone order, pick up and invoice/house account payment type
     *
     * @return If the invoice number is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Validate_PhoneOrder_InvoiceInHousePayment() {
        if (listOfPhoneOrder.get(0).getText().contains("Phone Order")
                && listOfPickUp.get(0).getText().contains("Pick Up")
                && listOfInvoiceHouseAccount.get(0).getText().contains("Invoice/House Account")) {
            listOfInvoiceHouseAccount.get(0).isDisplayed();
        }
        return listOfInvoiceNumber.get(0).isDisplayed();
    }

    public String get_InvoiceNumber_PhoneOrder_PickUp_Cash_PaymentType() {
        String invoiceNumber = null;
        if (listOfPhoneOrder.get(0).getText().contains("Phone Order")
                && listOfPickUp.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Cash")) {
            invoiceNumber = listOfInvoiceNumber.get(0).getText();
        }
        return invoiceNumber;
    }

    /**
     * Validate whether the invoice number is displayed or not in the order page based on the delivery type as phone order, pick up and cash payment type
     *
     * @return If the invoice number is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Validate_PhoneOrder_CashPaymentType_PickUp_InvoiceNumber() {
        if (listOfPhoneOrder.get(0).getText().contains("Phone Order")
                && listOfPickUp.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Cash")) {
            delayWithGivenTime(1000);
        }
        return is_Element_Displayed(listOfInvoiceNumber.get(0), "Invoice Number for Phone Order - Pick Up - Cash Payment Type");
    }

    public String Validate_PhoneOrder_CashPaymentType_PickUp_ModeOfPayment() {
        String Mode_Of_Pay = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Cash")) {
            //HighlightElement(ListOfMOP_OnOrderPageTable.get(0));
            Mode_Of_Pay = ListOfMOP_OnOrderPageTable.get(0).getText();
        }
        return Mode_Of_Pay;
    }

    public String get_InvoiceNumber_PhoneOrder_PickUp_Visa_PaymentType() {
        String invoiceNumber = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && listOfPickUp.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Visa")) {
            invoiceNumber = listOfInvoiceNumber.get(0).getText();
        }
        return invoiceNumber;
    }

    public boolean Validate_PhoneOrder_Visa_PaymentType_PickUp_InvoiceNumber() {
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && listOfPickUp.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Visa")) {
            //HighlightElement(listOfInvoiceNumber.get(0));
            delayWithGivenTime(1000);
        }
        return listOfInvoiceNumber.get(0).isDisplayed();
    }

    public String Validate_PhoneOrder_Visa_PaymentType_PickUp_ModeOfPayment() {
        String Mode_Of_Pay = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Visa")) {
            //HighlightElement(ListOfMOP_OnOrderPageTable.get(0));
            Mode_Of_Pay = ListOfMOP_OnOrderPageTable.get(0).getText();
        }
        return Mode_Of_Pay;
    }

    public String get_InvoiceNumber_PhoneOrder_PickUp_WriteOff_PaymentType() {
        String invoiceNumber = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && listOfPickUp.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Write Off")) {
            invoiceNumber = listOfInvoiceNumber.get(0).getText();
        }
        return invoiceNumber;
    }

    public boolean Validate_PhoneOrder_WriteOff_PaymentType_PickUp_InvoiceNumber() {
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && listOfPickUp.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Write Off")) {
            //HighlightElement(listOfInvoiceNumber.get(0));
            delayWithGivenTime(1000);
        }
        return listOfInvoiceNumber.get(0).isDisplayed();
    }

    public String Validate_PhoneOrder_WriteOff_PaymentType_PickUp_ModeOfPayment() {
        String Mode_Of_Pay = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Write Off")) {
            //HighlightElement(ListOfMOP_OnOrderPageTable.get(0));
            Mode_Of_Pay = ListOfMOP_OnOrderPageTable.get(0).getText();
        }
        return Mode_Of_Pay;
    }

    public String get_InvoiceNumber_PhoneOrder_PickUp_Check_PaymentType() {
        String invoiceNumber = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && listOfPickUp.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Check")) {
            invoiceNumber = listOfInvoiceNumber.get(0).getText();
        }
        return invoiceNumber;
    }

    public boolean Validate_PhoneOrder_Check_PaymentType_PickUp_InvoiceNumber() {
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && listOfPickUp.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Check")) {
            //HighlightElement(listOfInvoiceNumber.get(0));
            delayWithGivenTime(1000);
        }
        return listOfInvoiceNumber.get(0).isDisplayed();
    }

    public String Validate_PhoneOrder_Check_PaymentType_PickUp_ModeOfPayment() {
        String Mode_Of_Pay = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Check")) {
            //HighlightElement(ListOfMOP_OnOrderPageTable.get(0));
            Mode_Of_Pay = ListOfMOP_OnOrderPageTable.get(0).getText();
        }
        return Mode_Of_Pay;
    }

    public String get_InvoiceNumber_PhoneOrder_PickUp_Donation_PaymentType() {
        String invoiceNumber = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && listOfPickUp.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Donation")) {
            invoiceNumber = listOfInvoiceNumber.get(0).getText();
        }
        return invoiceNumber;
    }

    public boolean Validate_PhoneOrder_Donation_PaymentType_PickUp_InvoiceNumber() {
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && listOfPickUp.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Donation")) {
            //HighlightElement(listOfInvoiceNumber.get(0));
            delayWithGivenTime(1000);
        }
        return listOfInvoiceNumber.get(0).isDisplayed();
    }

    public String Validate_PhoneOrder_Donation_PaymentType_PickUp_ModeOfPayment() {
        String Mode_Of_Pay = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Donation")) {
            //HighlightElement(ListOfMOP_OnOrderPageTable.get(0));
            Mode_Of_Pay = ListOfMOP_OnOrderPageTable.get(0).getText();
        }
        return Mode_Of_Pay;
    }

    public String get_InvoiceNumber_PhoneOrder_PickUp_CC_PaymentType() {
        String invoiceNumber = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && listOfPickUp.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Credit Card")) {
            invoiceNumber = listOfInvoiceNumber.get(0).getText();
        }
        return invoiceNumber;
    }

    public boolean Validate_PhoneOrder_CC_PaymentType_PickUp_InvoiceNumber() {
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && listOfPickUp.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Credit Card")) {
            //HighlightElement(listOfInvoiceNumber.get(0));
            delayWithGivenTime(1000);
        }
        return listOfInvoiceNumber.get(0).isDisplayed();
    }

    public String Validate_PhoneOrder_CC_PaymentType_PickUp_ModeOfPayment() {
        String Mode_Of_Pay = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Credit Card")) {
            //HighlightElement(ListOfMOP_OnOrderPageTable.get(0));
            Mode_Of_Pay = ListOfMOP_OnOrderPageTable.get(0).getText();
        }
        return Mode_Of_Pay;
    }


    public String getInvoiceNumber_On_PhoneOrder_DeliveryInvoiceInHousePayment() {
        String invoiceNumber = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().contains("Delivery")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Invoice/House Account")) {
            fluentWait(listOfInvoiceNumber.get(0));
            invoiceNumber = listOfInvoiceNumber.get(0).getText().trim();
        }
        return invoiceNumber;
    }

    /**
     * Clicks the sender or customer on order page based on the Order type as Phone Order, Delivery type as Pick Up and Mode of payment type
     *
     * @Author Balaji N
     */
    public void ClickInvoiceInhousePayment_on_PhoneOrder_on_SenderorCustomer_OnOrderPage() {
        if (listOfPhoneOrder.get(0).getText().contains("Phone Order")
                && listOfPickUp.get(0).getText().contains("Pick Up")
                && listOfInvoiceHouseAccount.get(0).getText().contains("Invoice/House Account")) {

            js_Click(listOfSenderCustomer.get(0), "list Of SenderCustomer order summary table grid");
        }
    }

    public void Click_Cash_Payment_PickUp_PhoneOrder_on_SenderorCustomer_OnOrderPage() {
        if (listOfPhoneOrder.get(0).getText().contains("Phone Order")
                && listOfPickUp.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Cash")) {
            jsClick(listOfSenderCustomer.get(0));
        }
    }

    public String GetSenderorCustomerOnOrderPage() {
        String sender_cust = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Walkin Sales")
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().contains("Pick Up")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Cash")) {
            delayWithGivenTime(1000);
            sender_cust = listOfSenderCustomer.get(0).getText();
            System.out.println("Sender or Customer : " + sender_cust);
        }
        return sender_cust;
    }

    public String get_Sender_or_Customer_On_OrderPage(String ordertype, String deliverytype, String mop) {
        String sender_cust = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains(ordertype)
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().contains(deliverytype)
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains(mop)) {
            delayWithGivenTime(1000);
            sender_cust = listOfSenderCustomer.get(0).getText();
            System.out.println("Sender or Customer : " + sender_cust);
        }
        return sender_cust;
    }


    public void ClickonSenderorCustomerOnOrderPage() {
        if (listOfWalkinSales.get(0).getText().contains("Walkin Sales")
                && listOfPickUp.get(0).getText().contains("Pick Up")
                && listOfCashMOP.get(0).getText().contains("Cash")) {

           /* HighlightElement(listOfWalkinSales.get(0));
            delayWithGivenTime(500);
            HighlightElement(listOfOrderDetail.get(0));
            delayWithGivenTime(500);
            HighlightElement(firstrowOfOrderDetail.get(0));*/
            for (int i = 0; i < listOfSenderCustomer.size(); ) {
                break;
            }

            js_Click(listOfSenderCustomer.get(0), "Sender or Customer on order page");
            delayWithGivenTime(1000);
        }
    }

    /**
     * Clicks on the "Sender or Customer" field in the order page if the order type is "Phone Order",
     * the pickup type is "Pick Up", and the mode of payment is "Cash".
     *
     * @Description: This function verifies the order details and clicks on the sender/customer
     * field if conditions are met. Uses JavaScript click for reliability.
     * @Author Balaji N
     */
    public void ClickPhoneOrder_on_SenderorCustomer_OnOrderPage() {
        try {
            if (!listOfPhoneOrder.isEmpty() &&
                    !listOfPickUp.isEmpty() &&
                    !listOfCashMOP.isEmpty() &&
                    !listOfSenderCustomer.isEmpty()) {

                if (listOfPhoneOrder.get(0).getText().contains("Phone Order")
                        && listOfPickUp.get(0).getText().contains("Pick Up")
                        && listOfCashMOP.get(0).getText().contains("Cash")) {
                    js_Click(listOfSenderCustomer.get(0), "Sender or Customer on order page");
                }
            } else {
                System.err.println("One or more required elements are missing from the page.");
            }
        } catch (IndexOutOfBoundsException | NullPointerException | NoSuchElementException |
                 StaleElementReferenceException e) {
            System.err.println("Exception occurred while clicking Sender/Customer on order page: " + e.getMessage());
        }
    }


    public boolean VerifyDeliveryPopup() {
        return is_Element_Displayed(deliveryPopup, "Delivery popup on order page for respective invoice");
    }

    public String getCustomerTypeDeliveryPopup() {
        return getElementText(deliveryCustType, "Customer type on delivery popup in order page");
    }

    /**
     * It retrieves the customer and company name from the delivery popup
     *
     * @return If the customer and company name is displayed, it returns the value; otherwise it returns empty string
     * @Author Balaji N
     */
    public String getCustAndcompyNameOnDeliveryPopup() {
        return getElementText(custAndcompyNameOnDeliveryPopup, "Customer and Company name on delivery popup");
    }

    /**
     * It retrieves the customer address from the delivery popup
     *
     * @return If the customer address is displayed, it returns the value; otherwise it returns empty string
     * @Author Balaji N
     */
    public String getCustAddressOnDeliveryPopup() {
        return getElementText(CustAddressOnDeliveryPopup, "Customer address on delivery popup");
    }

    /**
     * It retrieves the customer phone number from the delivery popup
     *
     * @return If the customer phone number is displayed, it returns the value; otherwise it returns empty string
     * @Author Balaji N
     */
    public String getCustPhoneNumOnDeliveryPopup() {
        return getElementText(CustPhoneNumOnDeliveryPopup, "Customer phone number on delivery popup");
    }

    /**
     * It retrieves the customer email from the delivery popup
     *
     * @return If the customer email is displayed, it returns the value; otherwise it returns empty string
     * @Author Balaji N
     */
    public String getCustEmailOnDeliveryPopup() {
        return getElementText(CustEmailOnDeliveryPopup, "Customer email label on delivery popup");
    }

    /**
     * Clicks the close icon of displayed delivery popup
     *
     * @Author: Balaji N
     */
    public void ClickCloseIconOnDeliveryPopup() {
        click(DeliveryPopupCloseIcon, "Close icon on delivery popup");
    }

    /**
     * Click customer menu on dashboard
     *
     * @Author: Balaji N
     */
    public void ClickCustomerMenuOnDashboard() {
        Click(CustomerMenu, "Customer Menu on dashboard");
        wait_For_Page_To_Be_Stable(getDriver());
    }

    /**
     * It retrieves the recipient name from the delivery popup
     *
     * @return It returns the recipient name from the delivery popup if it exists else it returns empty string
     * @Author Balaji N
     */
    public String getRecipientName_OnDeliveryPopup() {
        return getElementText(RecipientName_InvPopup, "Recipient name on delivery popup");
    }

    /**
     * It retrieves the recipient address from the delivery popup
     *
     * @return If text is exists it returns the displayed recipient address as text, otherwise it returns empty string if no recipient address to be entered
     * @Author Balaji N
     */
    public String getRecipientAddress_OnDeliveryPopup() {
        return getElementText(RecipientAddress_InvPopup, "Recipient address on delivery popup");
    }

    /**
     * It retrieves the recipient phone number from the delivery popup
     *
     * @return If text is exists it returns the displayed recipient phone number as text, otherwise it returns empty string if no recipient phone number to be entered
     * @Author Balaji N
     */
    public String getRecipientPhoneNum_OnDeliveryPopup() {
        return getElementText(RecipientPhone_InvPopup, "Recipient phone number on delivery popup");
    }

    public String get_Updated_ProductName() {
        return getElementText(itemnamelist_on_invoicepopup.get(1), "Updated product name on delivery popup");
    }

    /**
     * It retrieves the order type displayed on the delivery popup
     *
     * @return If text is exists it returns the displayed order type as text, otherwise it returns empty string if no order type to be entered
     * @Author Balaji N
     */
    public String Verify_OrderType_Displayed_on_DeliveryPopup() {
        return getElementText(OrderType_On_InvPopup, "Order type on delivery popup");
    }

    /**
     * Verify whether the confidential request message appears on the delivery popup
     *
     * @return If the confidential request message is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_ConfidentialReqMessage_Appears() {
        return is_Element_Displayed(confidentialReq_Message_DeliveryPopup, "Confidential request message appears on delivery popup");
    }

    /**
     * It retrieves the confidential request message displayed on the delivery popup
     *
     * @return If the confidential request message is displayed, returns the confidential request message; otherwise, returns empty string
     * @Author Balaji N
     */
    public String Verify_ConfidentialReqTextMessage_Appears() {
        return getElementText(confidentialReq_Message_DeliveryPopup, "Confidential request message appears on delivery popup");
    }

    /**
     * Verify whether the wire-in confidential request message appears on the delivery popup
     *
     * @return If the wire-in confidential request message is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Verify_wirein_ConfidentialReqMessage_Appears() {
        return is_Element_Displayed(wirein_confidentialReq_Message_DeliveryPopup, "Wire-in confidential request message appears on delivery popup");
    }

    /**
     * It retrieves the wire-in confidential request message displayed on the delivery popup
     *
     * @return If the wire-in confidential request message is displayed, returns the wire-in confidential request message; otherwise, returns empty string
     * @Author Balaji N
     */
    public String Verify_wirein_ConfidentialReqTextMessage_Appears() {
        return getElementText(wirein_confidentialReq_Message_DeliveryPopup, "Wire-in confidential request message appears on delivery popup");
    }

    /**
     * It retrieves the occasion type displayed on the invoice popup
     *
     * @return If the occasion type is displayed, returns the occasion type; otherwise, returns empty string
     * @Author Balaji N
     */
    public String get_OccasionType_OnInvPopup() {
        return getElementText(occasion_InvPopup, "Occasion type on invoice popup");
    }

    /**
     * It retrieves the card message displayed on the invoice popup
     *
     * @return If the card message is displayed, returns the card message; otherwise, returns empty string
     * @Author Balaji N
     */
    public String get_CardMessage_OnInvPopup() {
        return getElementText(cardmessage_InvPopup, "Card message on invoice popup");
    }

    /**
     * It retrieves the product description displayed on the invoice popup of the order summary page
     *
     * @return If the product description is displayed, returns the product description; otherwise, returns empty string
     * @Author Balaji N
     */
    public String get_ProductDescription_OnInvPopup() {
        return getElementText(productTab_ProductDescription_OnInvPopup, "Product description on invoice popup");
    }

    /**
     * It retrieves the source code displayed on the invoice popup
     *
     * @return If the source code is displayed, returns the source code; otherwise, returns empty string
     * @Author Balaji N
     */
    public String get_sourcecode_displayed_OnInvPopup() {
        return getElementText(sourcecode_OnInvPopup, "Source code on invoice popup");
    }

    /**
     * It retrieves the special instruction displayed on the invoice popup
     *
     * @return If the special instruction is displayed, returns the special instruction; otherwise, returns empty string
     * @Author Balaji N
     */
    public String get_SpecialInstruction_displayed_OnInvPopup() {
        return getElementText(splInstruction_OnInvPopup, "Special instruction on invoice popup");
    }

    /**
     * It retrieves the driver instruction displayed on the invoice popup
     *
     * @return If the driver instruction is displayed, returns the driver instruction; otherwise, returns empty string
     * @Author Balaji N
     */
    public String get_DriverInstruction_displayed_OnInvPopup() {
        return getElementText(driverInstruction_OnInvPopup, "Driver instruction on invoice popup");
    }

    /**
     * It retrieves the delivery type displayed on the invoice popup
     *
     * @return If the delivery type is displayed, returns the delivery type; otherwise, returns empty string
     * @Author Balaji N
     */
    public String get_Displayed_DeliveryType_OnInvPopup() {
        return getElementText(deliveryType_OnInvoicePopup, "Delivery type on invoice popup");
    }

    public String getInvoiceNumber_Phoneorder_pickup_GiftCArd_OnOrderPage() {
        String Giftcardinvoicenumber = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().equals("Phone Order") &&
                ListOfDeliveryType_OnOrderPageTable.get(0).getText().equals("Pick Up") &&
                ListOfMOP_OnOrderPageTable.get(0).getText().equals("Gift Card")) {
            System.out.println("Order Page phoneorder - pickup - gift card - Invoice Number is :" + listOfInvoiceNumber.get(0).getText());
            Giftcardinvoicenumber = listOfInvoiceNumber.get(0).getText();
        }
        return Giftcardinvoicenumber;

    }

    public boolean Validate_MOP_As_GiftCard_OnOrderPageTable() {
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().equals("Phone Order") &&
                ListOfDeliveryType_OnOrderPageTable.get(0).getText().equals("Pick Up") &&
                ListOfMOP_OnOrderPageTable.get(0).getText().equals("Gift Card")) {
            HighlightElement(ListOfOrderType_OnOrderPageTable.get(0));
            ListOfOrderType_OnOrderPageTable.get(0).isDisplayed();
            HighlightElement(ListOfDeliveryType_OnOrderPageTable.get(0));
            ListOfDeliveryType_OnOrderPageTable.get(0).isDisplayed();
            HighlightElement(ListOfMOP_OnOrderPageTable.get(0));
            ListOfMOP_OnOrderPageTable.get(0).isDisplayed();

            if (ListOfRecipient_OnOrderPageTable.get(0).getText().contains("Abish David 1160 W 5th St PICK UP Washington MO 63090")) {
                HighlightElement(ListOfRecipient_OnOrderPageTable.get(0));
                ListOfRecipient_OnOrderPageTable.get(0).isDisplayed();
                return true;
            }
            return true;
        }
        return false;
    }


    public String getInvoiceNumber_WireInDeliveryType_Appears_OnOrderPage() {
        String invoicenumber = null;
        if (Wire_ListOfOrderType_OnOrderPageTable.get(0).getText().equals("FSN Order")
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().equals("Wire In")
                && ListOfMOP_OnOrderPageTable.get(0).getText().equals("FSN")) {
            invoicenumber = listOfInvoiceNumber.get(0).getText();
        }
        return invoicenumber;
    }

    /**
     * Verify whether the wire in order type appears on the order page
     *
     * @return If the wire in order type appears on the order page, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Validate_WireInOrderType_Appears_OnOrderPage() {
        boolean wireintype = false;
        if (Wire_ListOfOrderType_OnOrderPageTable.get(0).getText().equals("FSN Order")
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().equals("Wire In")
                && ListOfMOP_OnOrderPageTable.get(0).getText().equals("FSN")) {
            wireintype = Wire_ListOfOrderType_OnOrderPageTable.get(0).isDisplayed();
        }
        return wireintype;
    }

    /**
     * Verify whether the wire in delivery type appears on the order page
     *
     * @return If the wire in delivery type appears on the order page, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Validate_WireInDeliveryType_Appears_OnOrderPage() {
        boolean deliveryType = false;
        if (Wire_ListOfOrderType_OnOrderPageTable.get(0).getText().equals("FSN Order")
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().equals("Wire In")
                && ListOfMOP_OnOrderPageTable.get(0).getText().equals("FSN")) {
            deliveryType = ListOfDeliveryType_OnOrderPageTable.get(0).isDisplayed();
        }
        return deliveryType;
    }

    /**
     * Verify whether the wire in MOP appears on the order page
     *
     * @return If the wire in MOP appears on the order page, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean Validate_WireInMOP_Appears_OnOrderPage() {
        boolean WireInMop = false;
        if (Wire_ListOfOrderType_OnOrderPageTable.get(0).getText().equals("FSN Order")
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().equals("Wire In")
                && ListOfMOP_OnOrderPageTable.get(0).getText().equals("FSN")) {
            WireInMop = ListOfMOP_OnOrderPageTable.get(0).isDisplayed();
        }
        return WireInMop;
    }

    public String get_WireInOrderType_Text_OnOrderPage() {
        return get_Element_Text(Wire_ListOfOrderType_OnOrderPageTable.get(0), "Order Type label on Delivery Invoice popup order page");
    }

    public String get_WireInDeliveryType_Text_OnOrderPage() {
        return get_Element_Text(ListOfDeliveryType_OnOrderPageTable.get(0), "Delivery Type label on Delivery Invoice popup order page");
    }

    public String get_WireInMOP_Text_OnOrderPage() {
        return get_Element_Text(ListOfMOP_OnOrderPageTable.get(0), "MOP label on Delivery Invoice popup order page");
    }

    public String get_PhoneOrder_WireOut_InvoiceInHousePayment() {
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().contains("Wire Out")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Invoice/House Account")) {
            delayWithGivenTime(500);
        }
        return listOfInvoiceNumber.get(0).getText();
    }

    public boolean Validate_PhoneOrder_WireOut_InvoiceInHousePayment() {
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().contains("Wire Out")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Invoice/House Account")) {
            delayWithGivenTime(500);
        }
        return listOfInvoiceNumber.get(0).isDisplayed();
    }

    public void Click_PhoneOrder_WireOut_InvoiceInHousePayment() {
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains("Phone Order")
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().contains("Wire Out")
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains("Invoice/House Account")) {
        }
        jsClick(ListOfOrderType_OnOrderPageTable.get(0));
    }

    /**
     * It retrieves the sales tax on delivery popup invoice
     *
     * @return If the sales tax is displayed, returns the sales tax; otherwise, returns empty string
     * @Author Balaji N
     */
    public String get_SalesTax_On_DeliveryPopup_Invoice() {
        return getElementText(salesTax_OnDeliveryPopup_Invoice, "Sales Tax on Delivery Popup Invoice");
    }

    /**
     * Validates whether the Red Flag Icon is appears or not
     *
     * @return If the Red Flag Icon is appears, it returns true, otherwise false
     * @Description This method checks whether the Red Flag Icon is appears or not
     * @author Balaji N
     */
    public boolean Verify_RedFlagIcon_IsAppears() {
        return is_Element_Displayed(RedflagIcon_OnOrderpage, "Red Flag Icon on Order Page");
    }

    public String get_status_on_orderpage() {
        try {
            if (isElementDisplayed(OrderStatus_OnOrderPage, "Order Status in Order Page") == true) {
                Highlight_Element(OrderStatus_OnOrderPage, "Order Status in Order Page");
            }
            return get_Element_Text(OrderStatus_OnOrderPage, "Order Status in Order Page");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Click_OrderStatus() {
        jsClick(OrderStatus_OnOrderPage, "Order Status in Order Page");
    }

    /**
     * It retrieves the order status on delivery invoice popup
     *
     * @return If the order status is displayed, returns the order status; otherwise, returns empty string
     * @Author Balaji N
     */
    public String get_OrderStatus_DeliveryPopup() {
        return get_Element_Text(OrderStatus_InvPopup, "Order Status in Delivery Popup");
    }

    public String get_drivername_onInvoicePopup() {
        HighlightElement(driverName_InvPopup);
        return driverName_InvPopup.getText().trim();
    }

    public void Click_DispatchTab_onInvPopup() {
        jsClick(dispatchTab_InvPopup);
    }

    public String get_dispatchConfirmation_Date_and_Time() {
        String fullText = dispatch_ConfirmationMessage_InvPopup.getText();
        String[] parts = fullText.split("Dispatched on ");
        parts[1].trim();
        String dateTime = null;
        if (parts.length > 1) {
            dateTime = parts[1].split(" Delivered by")[0].trim().toUpperCase();
        }
        return dateTime;
    }

    public boolean Validate_dispatchConfirmation_Date_and_Time_With_AtlanticTimeZone() {
        if (get_dispatchConfirmation_Date_and_Time().contains(Atlantic_TimeZone())) {
            return true;
        } else {
            System.out.println("Dispatch Confirmation Date and Time is : " + get_dispatchConfirmation_Date_and_Time() + " not matched with : " + Atlantic_TimeZone());
            return false;
        }
    }

    public String get_dispatchConfirmation_DriverName() {
        String fullText = dispatch_ConfirmationMessage_InvPopup.getText();
        String[] parts = fullText.split("driver ");
        String drivername = parts[1].trim();
        return drivername;
    }

    /**
     * Clicks the status tab on invoice popup
     *
     * @Author Balaji N
     */
    public void Click_StatusTab_onInvPopup() {
        js_Click(statusTab_InvPopup, "Status Tab on Invoice Popup");
    }

    public String get_status_date_and_time() {
        String dateandtime = null;
        if (statusTable_row1_statusType != null && statusTable_row1_statusType.getText().contains("Dispatched")) {
            if (statusTable_row1_Date != null) {
                HighlightElement(statusTable_row1_Date);
                dateandtime = statusTable_row1_Date.getText().trim();
            } else {
                System.out.println("statusTable_row1_Date is null" + dateandtime);
            }
        } else {
            System.out.println("statusTable_row1_statusType is null or does not contain 'Dispatched'" + dateandtime);
        }
        return dateandtime;
    }

    public String get_StatusTab_date_and_time() {
        String dateandtime = null;
        HighlightElement(statusTable_row1_Date);
        dateandtime = statusTable_row1_Date.getText().trim();
        return dateandtime;
    }

    public String validate_statustab_dispatched_dateandtime() {
        return Atlantic_TimeZone_NumberDateFormat();
    }

    public boolean Validate_status_date_and_time() {
        if (get_status_date_and_time().contains(Atlantic_TimeZone_NumberDateFormat())) {
            return true;
        } else {
            // System.out.println("Status Tab confirmation dispatch Date and Time is : " + get_status_date_and_time() + " not matched with : " + Atlantic_TimeZone());
            return false;
        }
    }

/*    public String get_status_date_and_time() {
        String dateandtime = null;
        try {
            if (statusTable_row1_statusType != null && statusTable_row1_statusType.getText().contains("Dispatched")) {
                if (statusTable_row1_Date != null) {
                    HighlightElement(statusTable_row1_Date);
                    dateandtime = statusTable_row1_Date.getText().trim();
                } else {
                    System.out.println("Error: statusTable_row1_Date is null.");
                }
            } else {
                System.out.println("Error: statusTable_row1_statusType is null or does not contain 'Dispatched'.");
            }
        } catch (Exception e) {
            System.out.println("Error while retrieving status date and time: " + e.getMessage());
        }
        return dateandtime;
    }

    public boolean Validate_status_date_and_time() {
        String statusDateTimeStr = get_status_date_and_time();
        String currentTimeStr = Atlantic_TimeZone_NumberDateFormat();

        if (statusDateTimeStr == null || currentTimeStr == null) {
            System.out.println("Error: Either status date/time or current Atlantic time is null.");
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Adjust format to match your date/time strings
        dateFormat.setTimeZone(TimeZone.getTimeZone("Atlantic")); // Ensure Atlantic time zone is used

        try {
            // Parse both times into Date objects
            Date statusDateTime = dateFormat.parse(statusDateTimeStr);
            Date currentAtlanticTime = dateFormat.parse(currentTimeStr);

            // Calculate the difference in milliseconds
            long timeDifferenceMillis = statusDateTime.getTime() - currentAtlanticTime.getTime(); // Status - Current
            long twoMinutesMillis = 2 * 60 * 1000; // 2 minutes in milliseconds

            // Check if the status time is within the -2 to +2 minute range
            if (timeDifferenceMillis >= -twoMinutesMillis && timeDifferenceMillis <= twoMinutesMillis) {
                System.out.println("Validation passed: Status date/time is within the -2 to +2 minute range.");
                return true;
            } else {
                System.out.println("Validation failed: Time difference is " +
                        (timeDifferenceMillis < -twoMinutesMillis ? "too early" : "too late") +
                        " by " + Math.abs(timeDifferenceMillis / 1000) + " seconds.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error during validation: " + e.getMessage());
            return false;
        }
    }*/

    public String Verify_DeliveryOnTruck_at_ActivitySection() {
        HighlightElement(deliveryontruck_msg_on_Activity);
        return deliveryontruck_msg_on_Activity.getText().trim();
    }

    public String get_DeliveryOnTruck_DateandTime_at_ActivitySection() {
        try {
            HighlightElement(deliveryontruck_dateandtime_on_Activity);
            String dateandtime = deliveryontruck_dateandtime_on_Activity.getText().trim().toUpperCase();
            return dateandtime;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean Validate_DeliveryOnTruck_DateandTime_at_ActivitySection() {
      /*  String actualDateTimeStr = get_DeliveryOnTruck_DateandTime_at_ActivitySection();
        String expectedDateTimeStr = Atlantic_TimeZone();

        // Define the corrected date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

        try {
            // Parse strings into LocalDateTime objects
            LocalDateTime actualDateTime = LocalDateTime.parse(actualDateTimeStr, formatter);
            LocalDateTime expectedDateTime = LocalDateTime.parse(expectedDateTimeStr, formatter);

            // Calculate the difference in minutes
            long differenceInMinutes = Math.abs(ChronoUnit.MINUTES.between(actualDateTime, expectedDateTime));

            if (differenceInMinutes <= 2) {
                return true;
            } else {
                System.out.println("Confirmation on activity route number text Date and Time is : "
                        + actualDateTimeStr + " not matched within the range of : " + expectedDateTimeStr);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error parsing date and time: " + e.getMessage());
            return false;
        }
*/
        if (get_DeliveryOnTruck_DateandTime_at_ActivitySection().contains(Atlantic_TimeZone())) {
            return true;
        } else {
            System.out.println("Delivery on Truck Date and Time is : " + get_DeliveryOnTruck_DateandTime_at_ActivitySection() + " not matched with : " + Atlantic_TimeZone());
            return false;
        }
    }

    public String get_ConfirmationDate_and_Time_RouteNumber_on_ActivitySection() {
        HighlightElement(deliveryontruck_fullmsg_on_Activity);
        String fulltext = deliveryontruck_fullmsg_on_Activity.getText().trim();
        String[] parts = fulltext.split(" Your delivery driver is ");
        String dateTime = parts[0].split("loaded into truck on ")[1].trim().toUpperCase();
        return dateTime;
    }

    public boolean Validate_ConfirmationDate_and_Time_RouteNumber_on_ActivitySection() {

      /*  String actualDateTimeStr = get_ConfirmationDate_and_Time_RouteNumber_on_ActivitySection();
        String expectedDateTimeStr = Atlantic_TimeZone();

        // Define the corrected date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

        try {
            // Parse strings into LocalDateTime objects
            LocalDateTime actualDateTime = LocalDateTime.parse(actualDateTimeStr, formatter);
            LocalDateTime expectedDateTime = LocalDateTime.parse(expectedDateTimeStr, formatter);

            // Calculate the difference in minutes
            long differenceInMinutes = Math.abs(ChronoUnit.MINUTES.between(actualDateTime, expectedDateTime));

            if (differenceInMinutes <= 2) {
                return true;
            } else {
                System.out.println("Confirmation on activity route number text Date and Time is : "
                        + actualDateTimeStr + " not matched within the range of : " + expectedDateTimeStr);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error parsing date and time: " + e.getMessage());
            return false;
        }*/
        if (get_ConfirmationDate_and_Time_RouteNumber_on_ActivitySection().contains(Atlantic_TimeZone())) {
            return true;
        } else {
            System.out.println("Confirmation on activity route number text Date and Time is : " + get_ConfirmationDate_and_Time_RouteNumber_on_ActivitySection() + " not matched with : " + Atlantic_TimeZone());
            return false;
        }
    }

    public String get_Confirmation_DriverName_RouteNumber_on_ActivitySection() {
        HighlightElement(deliveryontruck_fullmsg_on_Activity);
        String fulltext = deliveryontruck_fullmsg_on_Activity.getText().trim();
        String[] parts = fulltext.split(" Your delivery driver is ");
        String drivername = parts[1].trim();
        return drivername;
    }


    public boolean Verify_Status_On_OrderPage() {
        return ListOfStatus_on_OrderPage.get(0).isDisplayed();
    }

    public String get_status_on_OrderPage() {
        String Status = null;
        Status = ListOfStatus_on_OrderPage.get(0).getText();
        return Status;
    }

    public boolean Validate_MOP_As_GiftCard_OnOrderPageTable(String OrderType, String DeliveryType, String MOP) {
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().equals(OrderType) &&
                ListOfDeliveryType_OnOrderPageTable.get(0).getText().equals(DeliveryType) &&
                ListOfMOP_OnOrderPageTable.get(0).getText().equals(MOP)) {
            HighlightElement(ListOfOrderType_OnOrderPageTable.get(0));
            ListOfOrderType_OnOrderPageTable.get(0).isDisplayed();
            HighlightElement(ListOfDeliveryType_OnOrderPageTable.get(0));
            ListOfDeliveryType_OnOrderPageTable.get(0).isDisplayed();
            HighlightElement(ListOfMOP_OnOrderPageTable.get(0));
            ListOfMOP_OnOrderPageTable.get(0).isDisplayed();

            if (ListOfRecipient_OnOrderPageTable.get(0).getText().contains("Abish David 1160 W 5th St PICK UP Washington MO 63090")) {
                HighlightElement(ListOfRecipient_OnOrderPageTable.get(0));
                ListOfRecipient_OnOrderPageTable.get(0).isDisplayed();
                return true;
            }
            return true;
        }
        return false;
    }

    /**
     * Validates whether the invoice number is displayed for a respective order based on order type, delivery type, and mode of payment.
     *
     * @param OrderType    The expected order type to be validated against.
     * @param DeliveryType The expected delivery type to be validated against.
     * @param MOP          The expected mode of payment to be validated against.
     * @return {@code true} if the conditions for order type, delivery type, and mode of payment are met, and the invoice number is displayed; {@code false} otherwise.
     * @Description This method checks if the provided order type, delivery type, and mode of payment match the displayed values on the order page.
     * If all conditions are met, it validates whether the invoice number is displayed. If not, it returns false.
     * @Author: Balaji N
     */
    public boolean Validate_PhoneOrder_Delivery_InvoiceNumber(String OrderType, String DeliveryType, String MOP) {
        boolean invoiceNumberFound = false;

        // 🔁 Retry for up to 15 seconds (customize as needed)
        int retries = 3;
        int delayBetweenRetriesMs = 3000;

        for (int attempt = 1; attempt <= retries; attempt++) {
            try {
                // ✅ Wait for DOM and network to stabilize
                wait_For_Page_To_Be_Stable(getDriver());

                // ✅ Verify all 3 conditions
                if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains(OrderType)
                        && ListOfDeliveryType_OnOrderPageTable.get(0).getText().contains(DeliveryType)
                        && ListOfMOP_OnOrderPageTable.get(0).getText().contains(MOP)) {

                    // ✅ Small buffer delay to ensure UI render finish
                    delayWithGivenTime(1000);

                    // ✅ Validate Invoice Number visibility
                    invoiceNumberFound = isElementDisplayed(listOfInvoiceNumber.get(0),
                            "Invoice Number on All orders page");

                    if (invoiceNumberFound) {
                        System.out.println("✅ Invoice number validated on attempt #" + attempt);
                        break;
                    }
                } else {
                    System.out.println(" Order Type / Delivery Type / MOP not matched on attempt #" + attempt);
                }

            } catch (Exception e) {
                System.out.println("Exception in attempt #" + attempt + ": " + e.getMessage());
            }

            // Retry if not found
            delayWithGivenTime(delayBetweenRetriesMs);
        }

        if (!invoiceNumberFound) {
            System.out.println(" Failed to validate invoice number after " + retries + " attempts.");
            Allure.addAttachment("Invoice Validation Failed", "OrderType: " + OrderType +
                    "\nDeliveryType: " + DeliveryType + "\nMOP: " + MOP);
        }

        return invoiceNumberFound;
    }


    /**
     * Validates the mode of payment (MOP) for a specific phone order based on the provided order type, delivery type, and mode of payment.
     *
     * @param OrderType    The expected order type to be validated against.
     * @param DeliveryType The expected delivery type to be validated against.
     * @param MOP          The expected mode of payment to be validated against.
     * @return The mode of payment as a string if the order type, delivery type, and mode of payment match the displayed values on the order page.
     * Returns {@code null} if the conditions are not met.
     * @Description This method checks whether the specified order type, delivery type, and mode of payment match the values displayed on the order page.
     * If they match, it returns the mode of payment; otherwise, it returns null.
     * @Author: Balaji N
     */
    public String Validate_PhoneOrder_ModeOfPayment(String OrderType, String DeliveryType, String MOP) {
        String Mode_Of_Pay = null;
        if (ListOfOrderType_OnOrderPageTable.get(0).getText().contains(OrderType)
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().contains(DeliveryType)
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains(MOP)) {
            delayWithGivenTime(1000);
            Mode_Of_Pay = ListOfMOP_OnOrderPageTable.get(0).getText();
        }
        return Mode_Of_Pay;
    }

    /**
     * Clicks the status for respective invoice number dynamically on the table grid on all orders page
     *
     * @param invoicenumber
     * @Author Balaji N
     */
    public void clickInvoiceNumber_On_TableGrid_AllOrdersPage(String invoiceNumber) {

        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
            By statusCellLocator = By.xpath("//span[@class='set-invoice-number' and text()='" + invoiceNumber + "']/following::td[1]");

            WebElement statusCell = wait.until(ExpectedConditions.elementToBeClickable(statusCellLocator));
            click(statusCell, "All Order page - clicking status column for invoice");
            delayWithGivenTime(500);
            if (!isElementDisplayed(order_details_invoice_popup_all_order_page, "All Orders Page - Order details invoice popup")) {
                click(statusCell, "All Order page - clicking status column for invoice");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout: Could not find/click status column for invoice '" + invoiceNumber + "'", e);
        } catch (Exception e) {
            throw new RuntimeException("Error on All Order page - clicking status column for invoice '" + invoiceNumber + "': " + e.getMessage(), e);
        }
    }

    /**
     * Clicks the payment tab on invoice popup in the order summary page
     *
     * @Description This function highlights the payment tab and perform click action on invoice popup in the order page
     * @Author Balaji N
     */
    public void Click_PaymentTab_On_InvoicePopup() {
        click(paymentTab_deliveryInvoicePopup, "Payment Tab on Invoice Popup");
    }

    /**
     * It retrieves the payment description displayed on row 1 payments tab
     *
     * @return If payment description is displayed on row 1 it returns value of payment description otherwise returns null
     * @Description: This function highlights the payment description on row1 and then it retrieves the payment description of row1 in the payments tab table
     * @Author Balaji N
     */
    public String get_paymentdescription_row1() {
        return getElementText(paymentdescription_row1, "Payment description at row 1");
    }

    /**
     * It retrieves the payment description displayed on row 2 payments tab
     *
     * @return If payment description is displayed on row 2 it returns value of payment description otherwise returns null
     * @Description: This function highlights the payment description on row2 and then it retrieves the payment description of row2 in the payments tab table
     * @Author Balaji N
     */
    public String get_paymentdescription_row2() {
        HighlightElement(paymentdescription_row2);
        return paymentdescription_row2.getText();
    }

    /**
     * It retrieves the payment description displayed on row 3 payments tab
     *
     * @return If payment description is displayed on row 3 it returns value of payment description otherwise returns null
     * @Description: This function highlights the payment description on row3 and then it retrieves the payment description of row3 in the payments tab table
     * @Author Balaji N
     */
    public String get_paymentdescription_row3() {
        HighlightElement(paymentdescription_row3);
        return paymentdescription_row3.getText();
    }

    /**
     * It retrieves the payment description displayed on row4 payments tab
     *
     * @return If payment description is displayed on row4 it returns value of payment description otherwise returns null
     * @Description: This function highlights the payment description on row4 and then it retrieves the payment description of row4 in the payments tab table
     * @Author Balaji N
     */
    public String get_paymentdescription_row4() {
        HighlightElement(paymentdescription_row4);
        return paymentdescription_row4.getText();
    }

    /**
     * It retrieves the payment description displayed on row 5 payments tab
     *
     * @return If payment description is displayed on row 5 it returns value of payment description otherwise returns null
     * @Description: This function highlights the payment description on row5 and then it retrieves the payment description of row5 in the payments tab table
     * @Author Balaji N
     */
    public String get_paymentdescription_row5() {
        HighlightElement(paymentdescription_row5);
        return paymentdescription_row5.getText();
    }

    /**
     * It retrieves the payment description displayed on row 6 payments tab
     *
     * @return If payment description is displayed on row 6 it returns value of payment description otherwise returns null
     * @Description: This function highlights the payment description on row6 and then it retrieves the payment description of row6 in the payments tab table
     * @Author Balaji N
     */
    public String get_paymentdescription_row6() {
        HighlightElement(paymentdescription_row6);
        return paymentdescription_row6.getText();
    }

    public String get_paymentdescription_row7() {
        HighlightElement(paymentdescription_row7);
        return paymentdescription_row7.getText();
    }

    /**
     * It retrieves the payment date displayed on row 1 payments tab
     *
     * @return If payment date is displayed on row 1 it returns value of payment date otherwise returns null
     * @Description: This function highlights the payment date on row1 and then it retrieves the payment date of row1 in the payments tab table
     * @Author Balaji N
     */
    public String get_paymentdate_row1() {
        HighlightElement(paymentdate_row1);
        return paymentdate_row1.getText();
    }

    /**
     * It retrieves the payment date displayed on row 2 payments tab
     *
     * @return If payment date is displayed on row 2 it returns value of payment date otherwise returns null
     * @Description: This function highlights the payment date on row2 and then it retrieves the payment date of row2 in the payments tab table
     * @Author Balaji N
     */
    public String get_paymentdate_row2() {
        HighlightElement(paymentdate_row2);
        return paymentdate_row2.getText();
    }

    /**
     * It retrieves the payment date displayed on row 3 payments tab
     *
     * @return If payment date is displayed on row 3 it returns value of payment date otherwise returns null
     * @Description: This function highlights the payment date on row3 and then it retrieves the payment date of row3 in the payments tab table
     * @Author Balaji N
     */
    public String get_paymentdate_row3() {
        HighlightElement(paymentdate_row3);
        return paymentdate_row3.getText();
    }

    /**
     * It retrieves the payment date displayed on row 4 payments tab
     *
     * @return If payment date is displayed on row 4 it returns value of payment date otherwise returns null
     * @Description: This function highlights the payment date on row4 and then it retrieves the payment date of row4 in the payments tab table
     * @Author Balaji N
     */
    public String get_paymentdate_row4() {
        HighlightElement(paymentdate_row4);
        return paymentdate_row4.getText();
    }

    /**
     * It retrieves the payment date displayed on row 5 payments tab
     *
     * @return If payment date is displayed on row 5 it returns value of payment date otherwise returns null
     * @Description: This function highlights the payment date on row5 and then it retrieves the payment date of row5 in the payments tab table
     * @Author Balaji N
     */
    public String get_paymentdate_row5() {
        HighlightElement(paymentdate_row5);
        return paymentdate_row5.getText();
    }

    /**
     * It retrieves the payment date displayed on row 6 payments tab
     *
     * @return If payment date is displayed on row 6 it returns value of payment date otherwise returns null
     * @Description: This function highlights the payment date on row6 and then it retrieves the payment date of row6 in the payments tab table
     * @Author Balaji N
     */
    public String get_paymentdate_row6() {
        HighlightElement(paymentdate_row6);
        return paymentdate_row6.getText();
    }

    public String get_paymentdate_row7() {
        HighlightElement(paymentdate_row7);
        return paymentdate_row7.getText();
    }

    /**
     * It retrieves the payment amount displayed on row 1 payments tab
     *
     * @return If payment amount is displayed on row 1 it returns value of payment amount otherwise returns null
     * @Description: This function highlights the payment amount on row1 and then it retrieves the payment amount of row1 in the payments tab table
     * @Author Balaji N
     */
    public String get_payment_amount_row1() {
        HighlightElement(payment_amount_row1);
        return payment_amount_row1.getText().replace("$", "");
    }

    /**
     * It retrieves the payment amount displayed on row 2 payments tab
     *
     * @return If payment amount is displayed on row 2 it returns value of payment amount otherwise returns null
     * @Description: This function highlights the payment amount on row2 and then it retrieves the payment amount of row2 in the payments tab table
     * @Author Balaji N
     */
    public String get_payment_amount_row2() {
        HighlightElement(payment_amount_row2);
        return payment_amount_row2.getText()
                .trim();
    }


    /**
     * It retrieves the payment amount displayed on row 3 payments tab
     *
     * @return If payment amount is displayed on row 3 it returns value of payment amount otherwise returns null
     * @Description: This function highlights the payment amount on row 3 and then it retrieves the payment amount of row 3 in the payments tab table
     * @Author Balaji N
     */
    public String get_payment_amount_row3() {
        HighlightElement(payment_amount_row3);
        return payment_amount_row3.getText().replace("$", "").split("\\.")[0];
    }

    /**
     * It retrieves the payment amount displayed on row 4 payments tab
     *
     * @return If payment amount is displayed on row 4 it returns value of payment amount otherwise returns null
     * @Description: This function highlights the payment amount on row4 and then it retrieves the payment amount of row4 in the payments tab table
     * @Author Balaji N
     */
    public String get_payment_amount_row4() {
        HighlightElement(payment_amount_row4);
        return payment_amount_row4.getText().replace("$", "").split("\\.")[0];
    }

    /**
     * It retrieves the payment amount displayed on row 5 payments tab
     *
     * @return If payment amount is displayed on row 5 it returns value of payment amount otherwise returns null
     * @Description: This function highlights the payment amount on row5 and then it retrieves the payment amount of row5 in the payments tab table
     * @Author Balaji N
     */
    public String get_payment_amount_row5() {
        HighlightElement(payment_amount_row5);
        return payment_amount_row5.getText().replace("$", "").split("\\.")[0];
    }

    /**
     * It retrieves the payment amount displayed on row 6 payments tab
     *
     * @return If payment amount is displayed on row 6 it returns value of payment amount otherwise returns null
     * @Description: This function highlights the payment amount on row5 and then it retrieves the payment amount of row6 in the payments tab table
     * @Author Balaji N
     */
    public String get_payment_amount_row6() {
        return getElementText(payment_amount_row6, "Payment amount row 6");
    }

    public String get_payment_amount_row7() {
        return getElementText(payment_amount_row7, "Payment amount row 6");
    }

    /**
     * Validates whether the "Wire In" order type appears on the order page.
     * <p>
     * This method checks the first row of the order type, delivery type, and method of payment (MOP)
     * in the order page table to verify if the order type is "FSN Order," the delivery type is "Wire In,"
     * and the method of payment is "FSN." If all these conditions are met, it confirms that the "Wire In"
     * order type is displayed on the order page.
     *
     * @param OrderType    The type of the order to be validated.
     * @param DeliveryType The delivery type to be validated.
     * @param MOP          The method of payment (MOP) to be validated.
     * @return {@code true} if the "Wire In" order type is displayed on the order page; {@code false} otherwise.
     * @author Balaji N
     */
    public boolean Validate_WireInOrderType_Appears_OnOrderPage(String OrderType, String DeliveryType, String MOP) {
        boolean wireintype = false;
        if (Wire_ListOfOrderType_OnOrderPageTable.get(0).getText().equals("FSN Order")
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().equals("Wire In")
                && ListOfMOP_OnOrderPageTable.get(0).getText().equals("FSN")) {
            wireintype = Wire_ListOfOrderType_OnOrderPageTable.get(0).isDisplayed();
        }
        return wireintype;
    }

    /**
     * Clicks the wire in displayed invoice based on conditions of order type, delivery type, and method of payment (MOP)
     *
     * @Description: This function clicks the wire in displayed invoice based on conditions of order type, delivery type, and method of payment (MOP)
     * @author Balaji N
     */
    public void Click_WireIn_OrderType_InvoiceNumber_On_OrderPage(String OrderType, String DeliveryType, String MOP) {
        if (Wire_ListOfOrderType_OnOrderPageTable.get(0).getText().equals(OrderType)
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().equals(DeliveryType)
                && ListOfMOP_OnOrderPageTable.get(0).getText().equals(MOP)) {
            WebElement invoice_number = listOfInvoiceNumber.get(0);
            click(invoice_number);
        }
    }

    /**
     * This method is used to click the Action menu displaying in the invoice
     *
     * @Description : This method is used to click the Action menu displaying in the invoice, the click action is performed using jsclick method and it will highlights the respective element
     * @Author : Sakrateesh R
     */

    public void Click_order_action_menu() {
       /* HighlightElement(order_action_menu);
        order_action_menu.click();*/
        click(order_action_menu, "Order action menu");
    }

    /**
     * This method is used to click the Fulfillment sub menu displaying in the action menu
     *
     * @Description : This method is used to click the Fulfillment sub menu displaying in the action menu, the click action is performed using jsclick method and it will highlights the respective element
     * @Author : Sakrateesh R
     */
    public void Click_fulfillment_sub_menu_in_action_icon() {
        HighlightElement(fulfillment_sub_menu_element_in_action_button);
        fulfillment_sub_menu_element_in_action_button.click();
    }

    /**
     * This method is used to verify whether the Fulfillment pop-up is displayed or not.
     *
     * @return : Boolean based on if the fulfillment popup is displayed it return true else false.
     * @Description : This method is used to verify whether the Fulfillment pop-up is displaying or not
     * @Author : Sakrateesh R
     */

    public boolean verify_fulfillment_popup_is_displayed() {
        return Fulfillment_popup_element.isDisplayed();
    }

    /**
     * This method is used to select the designer
     *
     * @Description : This method is used to select the designer in the Designer Dropdown, and it will highlights the respective element.
     * @Author : Sakrateesh R
     */
    public void Select_Designer_from_designer_dropdown() {
        HighlightElement(designer_dropdown_element);
        dropDown(designer_dropdown_element, "Stuart Markwood", "VisibleText");
    }

    /**
     * This method is performing the click action for Flower Design Toggle button
     *
     * @Description : This method is performing the click action for the Flower Design Toggle button, if the toggle button is turned on it will turn off and if the toggle button is turn Off it will turn on, it is performing a single click action in the toggle button.
     * @Author : Sakrateesh R
     */
    public void Click_flower_desing_complete_toggle_button() {
        HighlightElement(flower_design_complete_toggle_button_element);
        flower_design_complete_toggle_button_element.click();
    }

    /**
     * This method perform submit button click action in the Fulfillment popup
     *
     * @Description : This method is used to perform the submit button in the Fulfillment pop-up
     * @Author : Sakrateesh R
     */
    public void Click_fulfillment_submit_button() {
        HighlightElement(fulfillment_submit_button);
        fulfillment_submit_button.click();
    }

    /**
     * This method perform click action to close the fulfillment pop-up
     *
     * @Description : This method will close the Fulfillment pop-up by clicking the x button, it will highlights the element also.
     * @Author : Sakrateesh R
     */
    public void Close_fulfillment_popup_by_clicking_x_icon() {
        Click(fulfillment_close_icon_element, "Fulfillment close icon");
    }

    /**
     * Click the invoice number for a respective order based on order type, delivery type, and mode of payment.
     *
     * @param OrderType    The expected order type to be validated against.
     * @param DeliveryType The expected delivery type to be validated against.
     * @param MOP          The expected mode of payment to be validated against.
     * @Description This method checks if the provided order type, delivery type, and mode of payment match the displayed values on the order page.
     * If all conditions are met, it clicks the displayed invoice number.
     * @Author: Balaji N
     */
    public void Click_WireInOrder_FSN_InvoiceNumber(String OrderType, String DeliveryType, String MOP) {
        if (Wire_ListOfOrderType_OnOrderPageTable.get(0).getText().contains(OrderType)
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().contains(DeliveryType)
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains(MOP)) {
            delayWithGivenTime(1000);
            listOfInvoiceNumber.get(0).click();
        }
    }

    /**
     * Validates whether the invoice number is displayed for a respective order based on order type, delivery type, and mode of payment.
     *
     * @param OrderType    The expected order type to be validated against.
     * @param DeliveryType The expected delivery type to be validated against.
     * @param MOP          The expected mode of payment to be validated against.
     * @return {@code true} if the conditions for order type, delivery type, and mode of payment are met, and the invoice number is displayed; {@code false} otherwise.
     * @Description This method checks if the provided order type, delivery type, and mode of payment match the displayed values on the order page.
     * If all conditions are met, it validates whether the invoice number is displayed. If not, it returns false.
     * @Author: Balaji N
     */
    public boolean Validate_WireInOrder_FSN_InvoiceNumber(String OrderType, String DeliveryType, String MOP) {
        boolean invoice_number = false;
        if (Wire_ListOfOrderType_OnOrderPageTable.get(0).getText().contains(OrderType)
                && ListOfDeliveryType_OnOrderPageTable.get(0).getText().contains(DeliveryType)
                && ListOfMOP_OnOrderPageTable.get(0).getText().contains(MOP)) {
            delayWithGivenTime(1000);
            invoice_number = listOfInvoiceNumber.get(0).isDisplayed();
        }
        return invoice_number;
    }

    /**
     * Clicks the action menu row 1 in the order page
     *
     * @Description : This method will highlight and click the action menu row 1 in the order page
     * @Author : Balaji N
     */
    public void Click_ActionMenu_Row1() {
        click(actionmenu_row1, "Action menu row 1 in the order page");
    }

    /**
     * Clicks the action menu for respective invoice number
     *
     * @param invoicenumber Based on invoicenumber it will click the action menu
     * @Author : Balaji N
     */
    public void Click_ActionMenu_For_Respective_Invoice(String invoicenumber) {
        try {
            wait_For_Page_To_Be_Stable(getDriver());
            WebElement InvoiceNumber = getDriver().findElement(By.xpath(
                    "(//span[@class='set-invoice-number' and text()='" + invoicenumber + "']/preceding::td[1]//div/child::a//span)[1]"
            ));

            click(InvoiceNumber, "Action menu in the All Orders page for invoice: " + invoicenumber);
            Allure.step("✅ Successfully clicked on the Action menu for invoice number: " + invoicenumber);

        } catch (NoSuchElementException e) {
            Allure.step("❌ Could not find Action menu for invoice number: " + invoicenumber
                    + ". Please check if the invoice exists in the All Orders table.");
        } catch (Exception e) {
            Allure.step("❌ Failed to click Action menu for invoice number: " + invoicenumber
                    + ". Reason: " + e.getMessage());
        }
    }

    /**
     * Validate whether the action sub menu row 1 is displayed or not
     *
     * @return It returns true if the action sub menu row 1 is displayed and false otherwise
     * @Description : This method will highlight and validate whether the action sub menu row 1 is displayed or not
     * @Author : Balaji N
     */
    public boolean Verify_Action_submenu_is_displayed() {
        return isElementDisplayed(ListOfAction_submenu_row1, "Action submenu row 1 in the order page");
    }

    /**
     * Validate whether the respective submenu is displayed or not when clicks the action menu row 1
     *
     * @param submenu - This is the submenu to be validated
     * @return If the submenu is displayed it returns true otherwise returns false
     * @Description : This method will highlight and validate whether the respective submenu is displayed or not when clicks the action menu row 1
     * @Author : Balaji N
     */
    public boolean Verify_Respective_Submenu_is_displayed(String submenu) {
        for (int i = 0; i < listOfAction_submenus_row1.size(); i++) {
            if (listOfAction_submenus_row1.get(i).getText().equalsIgnoreCase(submenu)) {
                isElementDisplayed(listOfAction_submenus_row1.get(i), "Action submenu " + submenu + " in the order page");
                return true;
            }
        }
        return false;
    }

    /**
     * Clicks the cancel order action submenu row 1 in the order page
     *
     * @Description : This method will highlight and jsclick the cancel order action submenu row 1 in the order page
     * @Author : Balaji N
     */
    public void Click_CancelOrder_Submenu() {
        click(cancelorder_submenu_row1, "Cancel order submenu row 1 in the order page");
    }

    /**
     * Clicks the transfer order action submenu row 1 in the order page
     *
     * @Description : This method will highlight and jsclick the transfer order action submenu row 1 in the order page
     * @Author : Balaji N
     */
    public void click_TransferOrder_Submenu() {
        click(transferorder_submenu, "Transfer Order submenu in the action menu all orders page");
    }

    /**
     * Validate whether the redelivery submenu is displayed or not when clicks the action menu row 1
     *
     * @return If the redelivery submenu is displayed it returns true otherwise returns false
     * @Author : Balaji N
     */
    public boolean verify_Redelivery_Submenu_IsDisplayed() {
        return isElementDisplayed(redelivery_submenu, "Redelivery submenu in the action menu all orders page");
    }

    /**
     * Clicks the redelivery action submenu row 1 in the order page
     *
     * @Author : Balaji N
     */
    public void click_redelivery_Submenu() {
        click(redelivery_submenu, "Redelivery submenu in the action menu all orders page");
    }

    /**
     * Clicks the fullfillment action submenu row 1 in the order page
     *
     * @Author Balaji N
     */
    public void click_Fullfillment_Submenu() {
        wait_For_Page_To_Be_Stable(getDriver());
        click(fulfillment_submenu, "Fullfillment submenu in the action menu all orders page");
    }

    /**
     * Extracts the invoice number from the redelivery popup title
     *
     * @return If the invoice number is extracted it returns the invoice number otherwise returns empty string
     * @Author : Balaji N
     */
    public String extract_InvoiceNumber_On_Redelivery_Popup() {
        String invoiceText = redelivery_popup_title.getText();
        // Define regex pattern to extract invoice number
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(invoiceText);

        String invoiceNumber = "";
        if (matcher.find()) {
            invoiceNumber = matcher.group(); // Extracts first numeric sequence
        }
        return invoiceNumber;
    }

    /**
     * Clicks the no button in the redelivery popup
     *
     * @Author : Balaji N
     */
    public void click_Redelivery_Popup_No_Button() {
        click(redelivery_popup_no_button, "No button in the redelivery popup");
    }

    /**
     * Clicks the Yes button in the redelivery popup
     *
     * @Author : Balaji N
     */
    public void click_Redelivery_Popup_Yes_Button() {
        click(redelivery_popup_yes_button, "Yes button in the redelivery popup");
    }

    /**
     * Validate whether the cancel order popup is displayed or not when clicks the cancel order action submenu row 1
     *
     * @return If the cancel order popup is displayed it returns true otherwise returns false
     * @Description : This method will highlight and validate whether the cancel order popup is displayed or not when clicks the cancel order action submenu row 1
     * @Author : Balaji N
     */
    public boolean Verify_CancelOrder_Popup_IsDisplayed() {
        return is_Element_Displayed(cancelorder_popup, "Cancel order popup on All orders page");
    }

    public boolean is_Cancel_Order_Confirmation_Message_Displayed() {
        return is_Element_Displayed(cancelorder_popup_message, "Are you sure you want to cancel the order? confirmation message");
    }

    /**
     * Selects the cancel order reason dropdown in the cancel order popup
     *
     * @param Reason - This is the reason to be selected
     * @Description : This method will highlight and select the cancel order reason dropdown in the cancel order popup
     * @Author : Balaji N
     */
    public void Select_CancelOrder_Reason(String Reason) {
        drop_Down(cancelorder_reason_dropdown, Reason, "VisibleText", "cancel order reason dropdown option in the cancel order popup");
    }

    /**
     * It retrieves the cancel order reason selected in the cancel order popup
     *
     * @return If text is exists it returns the displayed cancel order reason as text, otherwise it returns empty string if no cancel order reason to be entered
     * @Description : This method will highlight and retrieve the cancel order reason selected in the cancel order popup
     * @Author : Balaji N
     */
    public String get_cancelOrder_Reason() {
        return get_selected_option(cancelorder_reason_dropdown, "cancel order reason dropdown option in the cancel order popup");
    }

    public void Click_NoButton_CancelOrder_Popup() {
        js_Click(cancelorderpopup_no_button, "No button in the cancel order popup");
    }

    /**
     * Clicks the yes button in the cancel order popup
     *
     * @Description : This method will highlight and click the yes button in the cancel order popup
     * @Author : Balaji N
     */
    public void Click_YesButton_CancelOrder_Popup() {
        js_Click(cancelorderpopup_yes_button, "Yes button in the cancel order popup");
        wait_For_Page_To_Be_Stable(getDriver());
    }

    /**
     * Validate whether the verify password popup is displayed or not
     *
     * @return If the verify password popup is displayed it returns true otherwise returns false
     * @Description : This method will highlight and validate whether the verify password popup is displayed or not
     * @Author : Balaji N
     */
    public boolean Validate_VerifyPassword_Popup_IsDisplayed() {
        try {
            return cancelorderpopup_verify_password_popup.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    /**
     * Entered the manager password on verify password popup for cancel order
     *
     * @param Password - This is the password to be entered
     * @Description : This method will highlight and enter the manager password on verify password popup for cancel order
     * @Author : Balaji N
     */
    public void Enter_Manager_Password_On_VerifyPassword_Popup(String Password) {
        cancelorderpopup_password_textboxfield.clear();
        js_Clear_And_Type(cancelorderpopup_password_textboxfield, Password, "Manager password textfield in the verify password popup for cancel order");
    }

    public void hover_On_Toaster_Message() {
        Mouse_Hover(this, toaster_message_text, "Toaster message");
    }

    /**
     * Validates the success toaster message text is correct or not
     *
     * @param message This is the message to be validated
     * @return If the success toaster message text is correct it returns the success toaster message text, otherwise it returns empty string
     * @Description : This method will highlight and validate the success toaster message text is correct or not
     * @Author : Balaji N
     */
//    public String Verify_Success_Toaster_Message_Text() {
//       // return getElementText(toaster_message_text, "success toaster message text");
//       return toaster_message_text.getText();
//    }
    public String Verify_Success_Toaster_Message_Text() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        try {
            // Wait briefly for the toaster to appear (no retries if it vanishes)
            WebElement toaster = wait.until(ExpectedConditions.visibilityOf(toaster_message_text));
            String message = toaster.getText().trim();

            Allure.step("✅ Captured Success Toaster Message: " + message);
            return message;

        } catch (TimeoutException e) {
            String errorMessage = "❌ Failed to capture the Success Toaster Message (timeout after 5s).\n\n"
                    + "🔍 Possible reasons:\n"
                    + "  • The toaster did not appear within expected time.\n"
                    + "  • The locator for the toaster message is incorrect.\n"
                    + "  • The toaster disappeared too quickly.\n\n"
                    + "👉 Please recheck the application flow or locator.";

            Allure.step(errorMessage);
            System.err.println(errorMessage);
            return ""; // so assertion fails cleanly
        } catch (Exception e) {
            String errorMessage = "❌ Exception while capturing the Success Toaster Message: "
                    + e.getClass().getSimpleName() + "\n"
                    + "Message: " + e.getMessage();

            Allure.step(errorMessage);
            System.err.println(errorMessage);
            return "";
        }
    }


    /**
     * Clicks the ok button in verify password popup for cancel order
     *
     * @Description : This method will highlight and click the ok button in verify password popup for cancel order
     * @Author : Balaji N
     */
    public void Click_OkayButton_VerifyPassword_Popup() {
        js_Click(cancelorder_okbutton_verify_password_popup, "Okay button in the verify password popup for cancel order");
    }

    /**
     * It retrieves the order status on order page
     *
     * @return If text is exists it returns the displayed order status as text, otherwise it returns empty string if no order status to be displayed
     * @Description : This method will highlight and retrieve the order status on order page
     * @Author : Balaji N
     */
    public String Validate_OrderStatus_On_OrderPage() {
        return get_Element_Text(Status_on_OrderPage, "Order status on order page");
    }

    /**
     * Clicks the Order status on order page in respective displayed invoice
     *
     * @Description : This method will highlight and click the Order status on order page in respective displayed invoice
     * @Author : Balaji N
     */
    public void Click_OrderStatus_OnOrderPage() {
        click(Status_on_OrderPage);
    }

    /**
     * It retrieves the order update cancelled message text on activity tab
     *
     * @return If text is exists it returns the displayed order update cancelled message text as text, otherwise it returns empty string if no order update cancelled message text to be displayed
     * @Description : This method will highlight and retrieve the order update cancelled message text on activity tab
     * @Author : Balaji N
     */
    public String get_OrderUpdate_CancelledMessageTextActivityTab() {
        String text = activitytab_cancelled_orderupdate_text.getText();
        if (text.contains("ORDER CANCELLED")) {
            int startIndex = text.indexOf("ORDER CANCELLED");
            return text.substring(startIndex, startIndex + "ORDER CANCELLED".length());
        }
        return "";
    }


    /**
     * It retrieves the top of invoice order status text in center
     *
     * @return If text is exists it returns the displayed top of invoice order status text as text, otherwise it returns empty string if no top of invoice order status text to be displayed
     * @Description : This method will highlight and retrieve the top of invoice order status text in center
     * @Author : Balaji N
     */
    public String Verify_Top_Of_Invoice_OrderStatus() {
        return getElementText(orderstatus_text_In_Center_TopOfInvoice, "Order status displayed on invoice popup - All order Page");
    }

    /**
     * It retrieves the payment description row 1 in payment tab
     *
     * @return If text is exists it returns the displayed payment description row 1 as text, otherwise it returns empty string if no payment description row 1 to be displayed
     * @Description : This method will highlight and retrieve the payment description row 1 in payment tab
     * @Author : Balaji N
     */
    public String get_paymentdescription_row1_paymenttab() {
        HighlightElement(CancelledOrderStatus_payment_refund_description_row1);
        return CancelledOrderStatus_payment_refund_description_row1.getText();
    }

    /**
     * It retrieves the payment refund amount row 1 in payment tab
     *
     * @return If text is exists it returns the displayed payment refund amount row 1 as text, otherwise it returns empty string if no payment refund amount row 1 to be displayed
     * @Description : This method will highlight and retrieve the payment refund amount row 1 in payment tab
     * @Author : Balaji N
     */
    public String get_CancelledOrderStatus_payment_refund_amount_row1() {
        return getElementText(CancelledOrderStatus_payment_refund_amount_row1, "Payment refund amount row 1");
    }

    /**
     * It retrieves the status tab description at row 2
     *
     * @return If text is exists it returns the displayed status tab description at row 2 as text, otherwise it returns empty string if no status tab description at row 2 to be displayed
     * @Description : This method will highlight and retrieve the status tab description at row 2
     * @Author : Balaji N
     */
    public String get_statustab_description_row2() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(7));
            wait.until(ExpectedConditions.visibilityOf(StatusTab_Description_row2));

            return getElementText(StatusTab_Description_row2,
                    "Status tab description at row 2");

        } catch (TimeoutException e) {
            throw new AssertionError(
                    "❌ Element NOT found in UI within 7 seconds → Status tab description at row 2"
            );
        }
    }


    /**
     * Clicks the update order action submenu row 1 in the order page
     *
     * @Description : This method will highlight and jsclick the update order action submenu row 1 in the order page
     * @Author : Balaji N
     */
    public void Click_UpdateOrder_Submenu() {
        click(updateorder_submenu_row1, "Update order submenu in the all orders page");
    }


    /**
     * Validate whether the update order popup is displayed or not when clicks the cancel order action submenu row 1
     *
     * @return If the update order popup is displayed it returns true otherwise returns false
     * @Description : This method will highlight and validate whether the update order popup is displayed or not when clicks the update order action submenu row 1
     * @Author : Balaji N
     */
    public boolean Verify_UpdateOrder_Popup_IsDisplayed() {
        isElementDisplayed(updateorder_popup_label, "Update order popup label");
        return isElementDisplayed(update_order_popup, "Update order popup");
    }

    /**
     * Entered the updated order price on update order popup
     *
     * @param OrderPrice - This is the order price to be entered
     * @Description : This method will highlight and entered the updated order price on update order popup
     * @Author : Balaji N
     */
    public void Enter_Updated_Order_Price_UpdateOrderPopup(String OrderPrice) {
        try {
            DoubleClickAndType(product_price_row1_updateorder_popup, OrderPrice);
        } catch (Exception e) {
            throw new RuntimeException("Unable to enter the product price textbox field" + e);
        }
    }

    /**
     * It retrieves the updated order price on update order popup
     *
     * @return If text is exists it returns the displayed updated order price as text, otherwise it returns empty string if no updated order price to be displayed
     * @Author : Balaji N
     */
    public String get_Entered_Updated_Order_Price_UpdateOrderPopup() {
        HighlightElement(product_price_row1_updateorder_popup);
        return product_price_row1_updateorder_popup.getAttribute("value");
    }

    public String getUpdatedPriceAfterSubtraction() {
        // Retrieve the displayed price as a string
        String displayedPrice = get_Entered_Updated_Order_Price_UpdateOrderPopup();

        try {
            // Parse the displayed price into a double for flexibility
            double price = Double.parseDouble(displayedPrice);
            // Subtract 50 from the price
            double updatedPrice = price - 50;
            // Format the updated price to ensure consistent decimal places (optional)
            return String.format("%.2f", updatedPrice);
        } catch (NumberFormatException e) {
            // Handle cases where the displayed price is not a valid number
            throw new IllegalArgumentException("Invalid price format: " + displayedPrice, e);
        }
    }


    /**
     * Selects the update order reason in update order popup
     *
     * @param updateorder_reason - This is the update order reason to be selected
     * @Description : This method will highlight and select the update order reason in update order popup
     * @Author : Balaji N
     */
    public void Select_UpdateOrder_Reason_InUpdateOrderPopup(String updateorder_reason) {
        //  updateorder_reason_dropdown_InUpdateOrderpopup.click();
        delayWithGivenTime(1000);
        drop_Down(updateorder_reason_dropdown_InUpdateOrderpopup, updateorder_reason, "VisibleText", "update order reason dropdown option in update order popup");
    }

    /**
     * It retrieves the selected update order reason in update order popup
     *
     * @return If text is exists it returns the displayed selected update order reason as text, otherwise it returns empty string if no selected update order reason to be displayed
     * @Author : Balaji N
     */
    public String get_Selected_UpdateOrder_Reason_InUpdateOrderPopup() {
        Select select = new Select(updateorder_reason_dropdown_InUpdateOrderpopup);
        return select.getFirstSelectedOption().getText();
    }


    /**
     * It retrieves the displayed refund amount
     *
     * @return If text is exists it returns the displayed refund amount as text, otherwise it returns empty string if no refund amount to be displayed
     * @Author : Balaji N
     */
    public String get_Displayed_refund_amount() {
        HighlightElement(updateorder_Refund_amount);
        return updateorder_Refund_amount.getText();
    }

    /**
     * Clicks the update order button in update order popup
     *
     * @Description : This method will highlight and jsclick the update order button in update order popup
     * @Author : Balaji N
     */
    public void Click_UpdateOrder_Button_InUpdateOrderPopup() {
        try {
            if (isElementDisplayed(updateorder_button, "Update Order submenu for respective") && isElementEnabled(updateorder_button, "Update Order button popup")) {
                js_Click(updateorder_button, "Update Order button in popup");
            }
        } catch (Exception e) {
            throw new RuntimeException("Update Order Submenu for respective invoice " + e);
        }
    }

    /**
     * Validate whether the cancel order popup is displayed or not when clicks the cancel order action submenu row 1
     *
     * @return If the cancel order popup is displayed it returns true otherwise returns false
     * @Description : This method will highlight and validate whether the cancel order popup is displayed or not when clicks the cancel order action submenu row 1
     * @Author : Balaji N
     */
    public boolean Verify_Update_Order_Popup_IsDisplayed() {
        return isElementDisplayed(cancelorder_popup, "Cancel order popup");
    }

    /**
     * It retrieves the order update cancelled message text on activity tab
     *
     * @return If text is exists it returns the displayed order update cancelled message text as text, otherwise it returns empty string if no order update cancelled message text to be displayed
     * @Description : This method will highlight and retrieve the order update cancelled message text on activity tab
     * @Author : Balaji N
     */
    public String get_OrderUpdated_ActiviesTab() {
        return get_Element_Text(activitytab_cancelled_orderupdate_text, "Activity Tab - Cancelled Order update text");
    }

    public String get_reason_on_activitiesTab() {
        String input = activitytab_cancelled_orderupdate_text.getText();
        // Split the input string into lines
        String[] lines = input.split("\n");

        // Loop through each line to find the refund amount
        String refundReason = null;
        for (String line : lines) {
            if (line.startsWith("Reason:")) {
                // Extract the value after "Refund Amount: "
                refundReason = line.split(":")[1].trim();
                break;
            }

        }
        return refundReason;
    }

    public String get_refund_amount_on_activitiesTab() {
        String input = activitytab_cancelled_orderupdate_text.getText();
        // Split the input string into lines
        String[] lines = input.split("\n");

        // Loop through each line to find the refund amount
        String refundAmount = null;
        for (String line : lines) {
            if (line.startsWith("Refund Amount:")) {
                // Extract the value after "Refund Amount: "
                refundAmount = line.split(":")[1].trim();
                break;
            }

        }
        return refundAmount;
    }

    public String get_paymentAmount_on_activitiesTab() {
        String input = activitytab_cancelled_orderupdate_text.getText();
        // Split the input string into lines
        String[] lines = input.split("\n");

        // Loop through each line to find the refund amount
        String refundReason = null;
        for (String line : lines) {
            if (line.startsWith("Payment Amount:")) {
                // Extract the value after "Refund Amount: "
                refundReason = line.split(":")[1].trim();
                break;
            }

        }
        return refundReason;
    }

    /**
     * It retrieves the refund balance amount on payment tab
     *
     * @return If text is exists it returns the displayed refund balance amount as text, otherwise it returns empty string if no refund balance amount to be displayed
     * @Author : Balaji N
     */
    public String Get_Refund_Balance_Amount() {
        return getElementText(balanceamountvalue_on_paymentgrid, "Balance amount value on payment grid");
    }

    public void validate_OrderUpdated_ActivitiesTab() {
        // Expected values
        List<String> expectedValues = Arrays.asList(
                "ORDER UPDATE",
                "Refund Processed",
                "Payment Method: Cash",
                "Reason: Credit For Customer Complaint"
        );

        // Extract the actual text from the web element
        String containerText = activitytab_cancelled_orderupdate_text.getText();

        // Split the text into lines for validation
        List<String> actualValues = Arrays.asList(containerText.split("\\n"));

        // Compare expected and actual values
        if (expectedValues.equals(actualValues)) {
            System.out.println("Validation passed: All values match.");
        } else {
            System.out.println("Validation failed:");
            System.out.println("Expected: " + expectedValues);
            System.out.println("Actual: " + actualValues);
        }

    }

    /**
     * It retrieves the payment refund amount on payment tab
     *
     * @return If text is exists it returns the displayed payment refund amount as text, otherwise it returns empty string if no payment refund amount to be displayed
     * @Description : This method will highlight and retrieve the payment refund amount on payment tab
     * @Author : Balaji N
     */
    public String get_payment_refund_paymentTab() {
        return cash_refund_amount_paymentTab.getText();
    }

    /**
     * Clicks the add new product plus icon
     *
     * @Description : This method will highlight and click the add new product plus icon
     * @Author : Balaji N
     */
    public void Click_Add_New_Product_Plus_Icon() {
        Click(product_row_action_add_button, "Plus Icon on product row");
    }

    /**
     * Enters the item code in row 2 update order popup
     *
     * @param itemcode - This is the item code to be entered
     * @Description : This method will highlight and enter the item code in row 2 update order popup
     * @Author : Balaji N
     */
    public void Enter_ItemCode2_In_Row2_Product_UpdateOrder(String itemcode) {
        try {
            product_itemcode_row2_updateorder_popup.clear();
            delayWithGivenTime(1000);
            jsClear(product_itemcode_row2_updateorder_popup);
            product_itemcode_row2_updateorder_popup.sendKeys(itemcode);
        } catch (Exception e) {
            throw new RuntimeException("Unable to enter the item code in Row2 update order popup" + e);
        }
    }

    public void click_Row1_Product_Cross_Icon_UpdateOrderPopup() {
        click(product_row1_cross_icon_updateorder_popup, "Cross icon for row 1 product");
    }

    public String get_Remove_Product_Confirmation_Message_On_UpdateOrderPopup() {
        return getElementText(remove_product_confirmation_popup, "Remove product confirmation message on update order popup");
    }

    public void click_Remove_Product_Confirmation_Yes_Button_On_UpdateOrderPopup() {
        click(remove_product_confirmation_popup_yes_button, "Remove product confirmation yes button on update order popup");
    }

    public void click_Remove_Product_Confirmation_No_Button_On_UpdateOrderPopup() {
        click(remove_product_confirmation_popup_no_button, "Remove product confirmation no button on update order popup");
    }


    /**
     * Retrieves the entered item code from row 1 in the Update Order popup.
     *
     * @return the entered item code as a String
     * @author Balaji N
     */
    public String get_entered_itemcode1_in_row1_product_updateorder() {
        // isElementDisplayed(product_itemcode_row1_updateorder_popup, "Item code in row 1 update order popup");
        //   return get_element_attribute(product_itemcode_row1_updateorder_popup, "Item code in row 1 update order popup");
        return product_itemcode_row1_updateorder_popup.getAttribute("value");
    }

    /**
     * Retrieves the entered description from row 1 in the Update Order popup.
     *
     * @return the entered description as a String
     * @author Balaji N
     */
    public String get_entered_description1_in_row1_product_updateorder() {
        // isElementDisplayed(product_description_row1_updateorder_popup, "Item code in row 1 update order popup");
        // return get_element_attribute(product_description_row1_updateorder_popup, "Description in row 1 update order popup");
        return product_description_row1_updateorder_popup.getAttribute("value");
    }

    /**
     * Retrieves the entered quantity from row 1 in the Update Order popup.
     *
     * @return the entered quantity as a String
     * @author Balaji N
     */
    public String get_entered_quantity1_in_row1_product_updateorder() {
        // isElementDisplayed(product_quantity_row1_updateorder_popup, "Item code in row 1 update order popup");
        // return get_element_attribute(product_quantity_row1_updateorder_popup, "Quantity in row 1 update order popup");
        return product_quantity_row1_updateorder_popup.getAttribute("value");
    }

    /**
     * Retrieves the entered price from row 1 in the Update Order popup.
     *
     * @return the entered price as a String
     * @author Balaji N
     */
    public String get_entered_price1_in_row1_product_updateorder() {
        //  isElementDisplayed(row1_product_price_updateorder_popup, "Price in row 1 update order popup");
        //return get_element_attribute(row1_product_price_updateorder_popup, "Price in row 1 update order popup");
        return row1_product_price_updateorder_popup.getAttribute("value");
    }

    /**
     * Retrieves the entered extended price from row 1 in the Update Order popup.
     *
     * @return the entered extended price as a String
     * author Balaji N
     */
    public String get_entered_extprice1_in_row1_product_updateorder() {
        //  isElementDisplayed(row1_product_extprice_updateorder_popup, "Extended price in row 1 update order popup");
        //  return get_element_attribute(row1_product_extprice_updateorder_popup, "Extended price in row 1 update order popup");
        return row1_product_extprice_updateorder_popup.getAttribute("value");
    }


    /**
     * It retrieves the item code in row 2 update order popup
     *
     * @return If text is exists it returns the displayed item code as text, otherwise it returns empty string if no item code to be displayed
     * @Description : This method will highlight and retrieve the item code in row 2 update order popup
     * @Author : Balaji N
     */
    public String get_entered_itemcode2_in_row2_product_updateorder() {
        HighlightElement(product_itemcode_row2_updateorder_popup);
        return product_itemcode_row2_updateorder_popup.getAttribute("value");
    }

    /**
     * Enters the item description in row 2 update order popup
     *
     * @param itemdescription - This is the item description to be entered
     * @Description : This method will highlight and enter the item description in row 2 update order popup
     * @Author : Balaji N
     */
    public void Enter_ItemDescription_Row2_Product_UpdateOrder(String itemdescription) {
        product_description_row2_updateorder_popup.clear();
        clickAndType(product_description_row2_updateorder_popup, itemdescription);
        jsClear(product_description_row2_updateorder_popup);
        delayWithGivenTime(2000);
        clickAndType(product_description_row2_updateorder_popup, itemdescription);
    }

    /**
     * It retrieves the item description in row 2 update order popup
     *
     * @return If text is exists it returns the displayed item description as text, otherwise it returns empty string if no item description to be displayed
     * @Description : This method will highlight and retrieve the item description in row 2 update order popup
     * @Author : Balaji N
     */
    public String get_entered_itemdescription2_in_row2_product_updateorder() {
        HighlightElement(product_description_row2_updateorder_popup);
        return product_description_row2_updateorder_popup.getAttribute("value");
    }

    /**
     * Enter the quantity in row 2 update order popup
     *
     * @param quantity - This is the quantity to be entered
     * @Description : This method will highlight and enter the quantity in row 2 update order popup
     * @Author : Balaji N
     */
    public void Enter_Quantity_Row2_Product_UpdateOrder(String quantity) {
        product_quantity_row2_updateorder_popup.clear();
        jsClear(product_quantity_row2_updateorder_popup);
        delayWithGivenTime(1000);
        product_quantity_row2_updateorder_popup.sendKeys(quantity);
    }

    /**
     * It retrieves the quantity in row 2 update order popup
     *
     * @return If text is exists it returns the displayed quantity as text, otherwise it returns empty string if no quantity to be displayed
     * @Description : This method will highlight and retrieve the quantity in row 2 update order popup
     * @Author : Balaji N
     */
    public String get_entered_quantity2_in_row2_product_updateorder() {
        HighlightElement(product_quantity_row2_updateorder_popup);
        return product_quantity_row2_updateorder_popup.getAttribute("value");
    }

    /**
     * Enter the price in row 2 update order popup
     *
     * @param price - This is the price to be entered
     * @Description : This method will highlight and enter the price in row 2 update order popup
     * @Author : Balaji N
     */
    public void Enter_Price_Row2_Product_UpdateOrder(String price) {
        product_price_row2_updateorder_popup.clear();
        jsClear(product_price_row2_updateorder_popup);
        delayWithGivenTime(1000);
        product_price_row2_updateorder_popup.sendKeys(price);
        product_price_row2_updateorder_popup.sendKeys(Keys.TAB);
    }

    /**
     * It retrieves the price in row 2 update order popup
     *
     * @return If text is exists it returns the displayed price as text, otherwise it returns empty string if no price to be displayed
     * @Description : This method will highlight and retrieve the price in row 2 update order popup
     * @Author : Balaji N
     */
    public String get_entered_price2_in_row2_product_updateorder() {
        HighlightElement(product_price_row2_updateorder_popup);
        return product_price_row2_updateorder_popup.getAttribute("value");
    }

    /**
     * Select the payment reason in update order popup
     *
     * @param paymentreason - This is the payment reason to be selected
     * @Description : This method will highlight and select the payment reason in update order popup
     * @Author : Balaji N
     */
    public void Select_PaymentReason_UpdateOrder_Popup(String paymentreason) {
        drop_Down(updateorder_payment_reason_dropdown_InUpdateOrderpopup, paymentreason, "VisibleText", "Payment Reason dropdown in update order popup");
    }

    /**
     * Retrieves the selected payment reason in update order popup
     *
     * @return If text is exists it returns the displayed selected payment reason as text, otherwise it returns empty string if no payment reason to be selected
     * @Author : Balaji N
     */
    public String get_selected_paymentreason_updateorder_popup() {
        return get_selected_option(updateorder_payment_reason_dropdown_InUpdateOrderpopup, "Payment Reason dropdown field value in update order popup");
    }

    public String Verify_OrderUpdatedSuccessfully_Popup_IsDisplayed() {
        HighlightElement(OrderUpdatedSuccessfully_Popup);
        return OrderUpdatedSuccessfully_Popup.getText();
    }

    /*    *//**
     * This method will verify whether the respective invoice is displaying in the Order Page or not.
     *
     * @param invoice
     * @return: If the invoice is displaying in the Order Page it will return true else false
     * @Author : Sakrateesh R
     *//*
    public boolean Verify_Invoice_IsDisplayed_In_Orders_page(String invoice) {
        delayWithGivenTime(2500);
        boolean invoice_ele = false;

        for (WebElement singleInvoice : all_orders_invoice_lists) {
            WebElement invoiceNumber = singleInvoice.findElement(By.xpath("./span[1]"));
            if (get_Element_Text(invoiceNumber, "Invoice Number on All Orders Page").equals(invoice)) {
                invoice_ele = true;
                break;
            }
        }

        return invoice_ele;
    }*/


    /**
     * This method will verify whether the Order Basic details are displaying properly or not
     *
     * @param invoice
     * @param OrderType
     * @param DeliveryType
     * @param MOP
     * @return: If all the details are displaying properly it will return true else it will return false
     * @Author : Sakrateesh R
     */
    public boolean Verify_Details_In_The_Order_Page_For_Invoices(String invoice, String OrderType, String
            DeliveryType, String MOP) {
        System.out.println("invoice total size " + InvoiceNumbers_list.size());
        try {
            for (int i = 0; i < InvoiceNumbers_list.size(); i++) {
                WebElement invoiceNo = InvoiceNumbers_list.get(i);

                if (invoiceNo.getText().equals(invoice)) {
                    if (ListOfOrderType_OnOrderPageTable.get(i).getText().contains(OrderType) &&
                            ListOfDeliveryType_OnOrderPageTable.get(i).getText().contains(DeliveryType) &&
                            ListOfMOP_OnOrderPageTable.get(i).getText().contains(MOP)) {

                        delayWithGivenTime(1000);
                        return true; // **Fixed missing semicolon**
                    }
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Error verifying invoice in Orders page", e);
        }
        return false;
    }

    /**
     * This method will return the text of the Filter View
     *
     * @return: Filter View text
     * @Author: Sakrateesh R
     */

    public String Get_Text_From_Filter_View() {
        fluentWait(dashboardviewtext, 30, 2);
        return get_Element_Text(dashboardviewtext, "Dashboard Filter View");

    }

    public String Get_Invoice_Status(String invoice) {
        for (WebElement singleInvoice : all_orders_invoice_lists) {
            WebElement invoiceNumber = singleInvoice.findElement(By.xpath("./span[1]"));
            if (get_Element_Text(invoiceNumber, "Invoice Number on All Orders Page").equals(invoice)) {
                WebElement invoiceStatus = singleInvoice.findElement(By.xpath("following-sibling::td[1]"));
                return get_Element_Text(invoiceStatus, "Invoice Status on All Orders Page");
            }
        }
        return "Status not found";
    }

    public String Get_Invoice_Order_Type(String invoice) {
        for (WebElement singleInvoice : all_orders_invoice_lists) {
            WebElement invoiceNumber = singleInvoice.findElement(By.xpath("./span[1]"));
            if (get_Element_Text(invoiceNumber, "Invoice Number on All Orders Page").equals(invoice)) {
                WebElement invoiceOrderType = singleInvoice.findElement(By.xpath("following-sibling::td[2]/table/tbody/tr/td[2]"));
                return get_Element_Text(invoiceOrderType, "Invoice Order Type on All Orders Page");
            }
        }
        return "Invoice Order Type is not displaying";
    }

    /**
     * Select the view from dropdown field on all orders page
     *
     * @Author Balaji N
     */
    public void Select_views_dropdown_on_all_ordersPage(String view) {
        wait_For_Page_To_Be_Stable(getDriver());
        click(views_dropdown_on_all_ordersPage, "View dropdown field on all orders page");
        delayWithGivenTime(2000);
        for (int i = 0; i < list_of_filters_options_on_all_ordersPage.size(); i++) {
            if (list_of_filters_options_on_all_ordersPage.get(i).getText().equalsIgnoreCase(view)) {
                click(list_of_filters_options_on_all_ordersPage.get(i), "View dropdown field on all orders page");
                wait_For_Page_To_Be_Stable(getDriver());
                break;
            }
        }
    }

    /**
     * It retrieves the selected view on all orders page
     *
     * @return If the view is selected it will return the view else it will return null
     * @Author Balaji N
     */
    public String get_Selected_View_On_AllOrdersPage() {
        explicitWait(view_displayed_option, 10);
        return get_Element_Text(view_displayed_option, "View displayed on all orders page");
    }

    /**
     * Clicks the confirm delivery button on all orders page
     *
     * @Author Balaji N
     */
    public void click_Confirm_Delivery_Button_On_AllOrderPages() {
        click(confirm_delivery_button_on_pendingconfirmation, "Confirm delivery button on all orders page");
    }

    /**
     * Clicks the non deliveries tab
     *
     * @Author Balaji N
     */
    public void click_Non_Deliveries_Tab() {
        explicitWait(non_deliveries_tab, 10);
        click(non_deliveries_tab, "Non Deliveries Tab");
    }

    /**
     * Verify whether the reschedule button on non deliveries tab is enabled or not
     *
     * @return If the reschedule button is enabled it will return true else it will return false
     * @Author Balaji N
     */
    public boolean verify_Reschedule_Button_IsDisabled() {
        return js_Verify_Element_Is_Disabled(reschedule_button_on_non_deliveriestab, "Reschedule button on non deliveries tab");
    }

    /**
     * Verify whether the reschedule button on non deliveries tab is displayed or not
     *
     * @return If the reschedule button is displayed it will return true else it will return false
     * @Author Balaji N
     */
    public boolean verify_Reschedule_Button_IsDisplayed() {
        return isElementDisplayed(reschedule_button_on_non_deliveriestab, "Reschedule button on non deliveries tab");
    }

    /**
     * Clicks the reschedule button on non deliveries tab
     *
     * @Author Balaji N
     */
    public void click_Reschedule_Button_On_Non_Deliveries_Tab() {
        click(reschedule_button_on_non_deliveriestab, "Reschedule button on non deliveries tab");
        wait_For_Page_To_Be_Stable(getDriver());
    }

    /**
     * Verify whether the reschedule order popup is displayed or not
     *
     * @return If the reschedule order popup is displayed it will return true else it will return false
     * @Author Balaji N
     */
    public boolean verify_Reschedule_Order_Popup_IsDisplayed() {
        return is_Element_Displayed(reschedule_orders_popup_header, "Reschedule order popup header on non deliveries tab");
    }

    /**
     * Enter choose new delivery date on reschedule order popup
     *
     * @param date
     * @Author Balaji N
     */
    public void Enter_Choose_New_Delivery_Date(String date) {
        ClickAndType(choose_new_delivery_date_on_reschedulepopup, date, "Choose new delivery date");
        press_Tab_Key();
    }

    /**
     * It retrieves the entered choose new delivery date on reschedule order popup
     *
     * @return If the choose new delivery date is entered it will return the choose new delivery date else it will return null
     * @Author Balaji N
     */
    public String get_Entered_Choose_New_Delivery_Date() {
        return getElementAttribute(choose_new_delivery_date_on_reschedulepopup, "Choose new delivery date");
    }

    /**
     * Clicks the reschedule button on reschedule order popup
     *
     * @Author Balaji N
     */
    public void click_Reschedule_Button_On_Reschedule_Order_Popup() {
        js_Click(reschedule_button_on_reschedulepopup, "Reschedule button on reschedule order popup");
    }

    /**
     * Clicks the pending confirmation tab on all orders page
     *
     * @Author Balaji N
     */
    public void click_PendingConfirmation_Tab() {
        js_Click(pending_confirmation_tab, "Pending confirmation tab on all orders page");
    }

    /**
     * Select the date on date selection datepicker on all orders page
     *
     * @param date
     * @Author Balaji N
     */
    public void select_Date_On_DateSelection_DatePicker(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate targetDate = LocalDate.parse(date, formatter);

            int targetDay = targetDate.getDayOfMonth();
            String targetMonthYear = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + targetDate.getYear();

            click(datepicker_dateselection_on_allorderspage, "Date Selection date picker on all orders page");

            while (true) {
                String displayedMonthYear = getElementText(monthandyear_dateselection, "Date Month year on the Date Selection datepicker field").trim();
                if (displayedMonthYear.equalsIgnoreCase(targetMonthYear)) {
                    break;
                }
                click(nextarrow_dateselection, "Date Picker Next Arrow or Button on the Date selection");
            }

            for (WebElement dayElement : listofdays_dateselection) {
                if (dayElement.isEnabled() && dayElement.getText().equalsIgnoreCase(String.valueOf(targetDay))) {
                    click(dayElement, "Date in the Calendar Date selection datepicker field");
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            printError(datepicker_dateselection_on_allorderspage, "Date Selection date picker field on all orders page", "No Such Element Exception", e);
        }

    }


    /**
     * Clicks the filters icon on all orders page
     *
     * @Author Balaji N
     */
    public void click_Filters_Icon_On_All_Orders_Page() {
        click(filters_icon_on_all_ordersPage, "Filters icon on all orders page");
    }

    /**
     * Clicks the cancel button on filters popup on all orders page
     *
     * @Author Balaji N
     */
    public void click_Cancel_Button_On_Filters_Popup_All_Orders_Page() {
        click(cancel_button_on_filters_popup, "Cancel button on all orders page");
    }

    /**
     * Clicks the MOP dropdown on all orders page
     *
     * @Author Balaji N
     */
    public void click_MOP_Dropdown_On_All_Orders_Page() {
        js_Click(mop_dropdown_in_filters_on_all_ordersPage, "MOP dropdown on all orders page");
    }

    /**
     * Selects the MOP dropdown value in filters section on all orders page
     *
     * @param value
     * @Author Balaji N
     */
    public void select_MOP_Dropdown_Value_On_All_Orders_Page(String value) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        for (WebElement option : list_of_mop_options_in_filters_on_all_ordersPage) {
            try {
                WebElement refreshedElement = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(option)));
                if (refreshedElement.getText().equalsIgnoreCase(value)) {
                    click(refreshedElement, "MOP dropdown value on all orders page");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Exception encountered while selecting MOP: " + e.getMessage());
            }
        }
    }

    /**
     * Returns the selected MOP dropdown value in filters section on all orders page
     *
     * @return if the MOP dropdown value is selected it returns the value, otherwise it returns null
     * @Author Balaji N
     */
    public String get_Selected_MOP_Dropdown_Value_On_All_Orders_Page() {
        return getElementText(selected_mop_option_in_filters_on_all_ordersPage, "MOP dropdown value on all orders page");
    }

    /**
     * Clicks the apply filter button on all orders page
     *
     * @Author Balaji N
     */
    public void click_Apply_Filter_Button_On_All_Orders_Page() {
        click(apply_button_in_filters_on_all_ordersPage, "Apply filter button on all orders page");
    }

    /**
     * Validates the MOP on all orders page table grid list
     *
     * @param expectedMOP
     * @return
     * @Author Balaji N
     */
    public boolean validate_MOP_On_All_Orders_Page_TableGrid_List(String expectedMOP) {
        boolean allMatch = true;

        for (WebElement mop : ListOfMOP_OnOrderPageTable) {
            String actualMOP = mop.getText().trim();
            if (!actualMOP.equalsIgnoreCase(expectedMOP)) {
                System.out.println("Mismatch Found: Expected - " + expectedMOP + ", Actual - " + actualMOP);
                allMatch = false;
            }
            return isElementDisplayed(mop, "MOP on table grid - all orders page");
        }
        return allMatch;
    }

    /**
     * It retrieves the status of filter in all orders page
     *
     * @return If the status is displayed on filter it return the value of status else null
     * @Author Balaji N
     */
    public String get_Displayed_Status_On_Filters_In_AllOrdersPage() {
        return getElementText(status_option_in_filters_on_all_orderspage, "Status option value on filters in the all orders page");
    }

    /**
     * It retrieves the status of filter in all orders page
     *
     * @return If the status is displayed on filter it return the value of status else null
     * @Author Balaji N
     */
    public String get_Displayed_Status_Option1_On_Filters_In_AllOrdersPage() {
        return getElementText(status_option1_in_filters_on_all_orderspage, "Status option value on filters in the all orders page");
    }

    /**
     * It retrieves the status of filter in all orders page
     *
     * @return If the status is displayed on filter it return the value of status else null
     * @Author Balaji N
     */
    public String get_Displayed_Status_Option2_On_Filters_In_AllOrdersPage() {
        return getElementText(status_option2_in_filters_on_all_orderspage, "Status option value on filters in the all orders page");
    }

    /**
     * It retrieves the status of filter in all orders page
     *
     * @return If the status is displayed on filter it return the value of status else null
     * @Author Balaji N
     */
    public String get_Displayed_Status_Option3_On_Filters_In_AllOrdersPage() {
        return getElementText(status_option3_in_filters_on_all_orderspage, "Status option value on filters in the all orders page");
    }

    /**
     * It retrieves the delivery option of filter in all orders page
     *
     * @return If the delivery option is displayed on filter it return the value of delivery option else null
     * @Author Balaji N
     */
    public String get_Displayed_Delivery_Option1_On_Filters_In_AllOrdersPage() {
        return getElementText(delivery_option1_in_filters_on_all_orderspage, "Delivery option value on filters in the all orders page");
    }

    /**
     * It retrieves the delivery option of filter in all orders page
     *
     * @return If the delivery option is displayed on filter it return the value of delivery option else null
     * @Author Balaji N
     */
    public String get_Displayed_Delivery_Option2_On_Filters_In_AllOrdersPage() {
        return getElementText(delivery_option2_in_filters_on_all_orderspage, "Delivery option value on filters in the all orders page");
    }

    /**
     * It retrieves the delivery option of filter in all orders page
     *
     * @return If the delivery option is displayed on filter it return the value of delivery option else null
     * @Author Balaji N
     */
    public String get_Displayed_Delivery_Option3_On_Filters_In_AllOrdersPage() {
        return getElementText(delivery_option3_in_filters_on_all_orderspage, "Delivery option value on filters in the all orders page");
    }

    /**
     * Clicks the confirm deliveries button on pending deliveries view
     *
     * @Author Balaji N
     */
    public void click_ConfirmDeliveries_Button_On_PendingDeliveries() {
        click(confirm_delivery_button_on_pendingdeliveries_view, "Confirm Deliveries button on pending deliveries view");
    }

    /**
     * It retrieves the pickup confirmation alert message
     *
     * @return If the pickup confirmation alert message is displayed it returns the message else null
     * @Author Balaji N
     */
    public String get_pickup_confirmation_alert_message() {
        return getElementText(pickup_confirmation_alert_message, "Pickup confirmation alert message");
    }

    /**
     * Clicks the yes button on pickup confirmation alert message
     *
     * @Author Balaji N
     */
    public void click_yes_button_on_pickup_confirmation_alert_message() {
        click(yes_button_on_pickup_confirmation_alert_message, "Yes button on pickup confirmation alert message");
    }

    /**
     * Clicks the no button on pickup confirmation alert message
     *
     * @Author Balaji N
     */
    public void click_no_button_on_pickup_confirmation_alert_message() {
        click(no_button_on_pickup_confirmation_alert_message, "No button on pickup confirmation alert message");
    }

    /**
     * Enters the invoice number on filter icon on all orders page
     *
     * @param invoicenumber
     * @Author Balaji N
     */
    public void Enter_Invoice_Number_FilterIcon_On_AllOrdersPage(String invoicenumber) {
        try {
            fluent_Wait_for_Visibility(invoice_textbox_on_filtericon_allorderspage, 20, 2);
            invoice_textbox_on_filtericon_allorderspage.sendKeys(invoicenumber);
            invoice_textbox_on_filtericon_allorderspage.sendKeys(Keys.TAB);
        } catch (Exception e) {
            printError(invoice_textbox_on_filtericon_allorderspage, "Invoice Textbox field on Filters On All Orders", "Generic Exception", e);
        }

    }

    /**
     * It retrieves the entered invoice number on filter icon on all orders page
     *
     * @return If the invoice number is entered it returns the invoice number else null
     * @Author Balaji N
     */
    public String get_Entered_Invoice_Number_FilterIcon_On_AllOrdersPage() {
        return getElementAttribute(invoice_textbox_on_filtericon_allorderspage, "Invoice Number textbox field on Filters On All Orders");
    }

    /**
     * Clicks the customer textbox field on filter icon on all orders page
     *
     * @Author Balaji N
     */
    public void click_Customer_Textbox_field_On_Filters_In_AllOrdersPage() {
        // click(customer_textbox_on_filtericon_allorderspage, "customer textbox field on filter icon on all orders page");
        try {
            fluent_Wait_for_Visibility(customer_textbox_on_filtericon_allorderspage, 20, 2);
            customer_textbox_on_filtericon_allorderspage.click();
        } catch (Exception e) {
            printError(customer_textbox_on_filtericon_allorderspage, "Customer Textbox field on Filters On All Orders", "Generic Exception", e);
        }
    }

    /**
     * Enters the invoice number on filter icon on all orders page
     *
     * @param invoicenumber
     * @Author Balaji N
     */
    public void Enter_Customer_FilterIcon_On_AllOrdersPage(String customer) {
        try {
            fluent_Wait_for_Visibility(customer_textbox_on_filtericon_allorderspage, 20, 2);
            customer_textbox_on_filtericon_allorderspage.sendKeys(customer);
            customer_textbox_on_filtericon_allorderspage.sendKeys(Keys.TAB);
        } catch (Exception e) {
            printError(customer_textbox_on_filtericon_allorderspage, "Customer Textbox field on Filters On All Orders", "Generic Exception", e);
        }

    }

    /**
     * It retrieves the entered Customer on filter icon on all orders page
     *
     * @return If the Customer is entered it returns the customer value else null
     * @Author Balaji N
     */
    public String get_Entered_Customer_FilterIcon_On_AllOrdersPage() {
        return getElementAttribute(customer_textbox_on_filtericon_allorderspage, "Customer textbox field on Filters On All Orders");
    }

    /**
     * It verifies the fullfillment details popup is displayed or not
     *
     * @return If the fullfillment details popup is displayed it returns true else false
     * @Author Balaji N
     */
    public boolean verify_FullFillment_Details_Popup_IsDisplayed() {
        isElementDisplayed(fulfillmentpopup, "Fulfillment popup on all orders page");
        return isElementDisplayed(fulfillmentpopup_title, "Fulfillment popup title on all orders page");
    }

    /**
     * Selects the designer on fullfillment details popup on all order page into action menu
     *
     * @param designer
     * @Author Balaji N
     */
    public void select_Designer_On_Fulfillment_Details_Popup(String designer) {
        drop_Down(designer_dropdown_on_fulfillment_details_popup, designer, "VisibleText", "Designer dropdown on fulfillment details popup");
    }

    /**
     * It retrieves the selected designer on fulfillment details popup
     *
     * @return
     */
    public String get_Selected_Designer_On_FulFillment_Details_Popup() {
        return get_selected_option(designer_dropdown_on_fulfillment_details_popup, "Designer dropdown value on fulfillment details popup");
    }

    /**
     * Clicks the flower design completed toogle button
     *
     * @Author Balaji N
     */
    public void click_Flower_Design_Completed_Toogle_Button_On() {
        try {
            boolean toggleState = flower_design_completed_toogle_button
                    .getAttribute("style")
                    .contains("rgb(223, 223, 223)");

            if (toggleState) { // Toggle is OFF → click to turn ON
                click(flower_design_completed_toogle_button,
                        "Flower design completed toggle button");
            } else {
                Allure.step("ℹ️ Flower design completed toggle button is already ON. No action needed.");
            }

        } catch (Exception e) {
            Allure.step("❌ Failed to click Flower design completed toggle button. Reason: " + e.getMessage());
        }
    }

    public void click_Flower_Design_Completed_Toogle_Button_Off() {
        try {
            boolean toggleState = flower_design_completed_toogle_button
                    .getAttribute("style")
                    .contains("rgb(223, 223, 223)");

            if (!toggleState) { // Toggle is OFF → click to turn ON
                click(flower_design_completed_toogle_button,
                        "Flower design completed toggle button");
            } else {
                Allure.step("ℹ️ Flower design completed toggle button is already Off. No action needed.");
            }

        } catch (Exception e) {
            Allure.step("❌ Failed to click Flower design completed toggle button. Reason: " + e.getMessage());
        }
    }


    public boolean is_Flower_Design_Completed_Toogle_Button_Off_State() {
        try {
            boolean toggleState = flower_design_completed_toogle_button
                    .getAttribute("style")
                    .contains("rgb(223, 223, 223)");

            if (toggleState) {
            } else {
                Allure.step("❌ Expected OFF state, but toggle button is ON.");
            }
            return toggleState;

        } catch (Exception e) {
            Allure.step("❌ Unable to verify Flower design completed toggle button OFF state. Reason: " + e.getMessage());
            return false;
        }
    }

    public boolean is_Flower_Design_Completed_Toogle_Button_On_State() {
        try {
            boolean toggleState = !flower_design_completed_toogle_button
                    .getAttribute("style")
                    .contains("rgb(223, 223, 223)");

            if (toggleState) {
            } else {
                Allure.step("❌ Expected ON state, but toggle button is OFF.");
            }
            return toggleState;

        } catch (Exception e) {
            Allure.step("❌ Unable to verify Flower design completed toggle button ON state. Reason: " + e.getMessage());
            return false;
        }
    }

    public boolean is_Notify_Customer_Toggle_Button_Disabled() {
        try {
            WebDriverWait shortWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
            By notifycustomerelement = By.xpath("//label[@for='actionNotifyCustomer']/following::span[contains(@class,'switchery-disabled')]");
            WebElement toggleBtn = shortWait.until(ExpectedConditions.presenceOfElementLocated(notifycustomerelement));

            boolean isDisplayed = toggleBtn.isDisplayed();
            if (!isDisplayed) {
                Allure.step("❌ Expected Notify Customer toggle button to be disabled, but it is enabled.");
            }

            return isDisplayed;

        } catch (TimeoutException e) {
            Allure.step("❌ Notify Customer toggle button (disabled state) not found within 5 seconds. " +
                    "Possible locator issue or state mismatch.");
            return false;

        } catch (NoSuchElementException e) {
            Allure.step("❌ Notify Customer toggle button (disabled state) is not present in the DOM.");
            return false;

        } catch (Exception e) {
            Allure.step("❌ Unable to verify Notify Customer toggle button disabled state. Reason: " + e.getMessage());
            return false;
        }
    }


    public void turn_On_Notify_Customer_Toogle_Button_On_FulfilmentPopup() {
        try {
            boolean toggleState = notify_customer_toogle_button
                    .getAttribute("style")
                    .contains("rgb(223, 223, 223)");

            if (toggleState) { // Toggle is OFF → click to turn ON
                click(notify_customer_toogle_button,
                        "Notify Customer toggle button");
            } else {
                Allure.step("ℹ️ Notify Customer toggle button is already on. No action needed.");
            }

        } catch (Exception e) {
            Allure.step("❌ Failed to click Notify Customer toggle button. Reason: " + e.getMessage());
        }
    }

    public void turn_Off_Notify_Customer_Toogle_Button_On_FulfilmentPopup() {
        try {
            boolean toggleState = notify_customer_toogle_button
                    .getAttribute("style")
                    .contains("rgb(223, 223, 223)");

            if (!toggleState) { // Toggle is OFF → click to turn ON
                click(notify_customer_toogle_button,
                        "Notify Customer toggle button");
            } else {
                Allure.step("ℹ️ Notify Customer toggle button is already off. No action needed.");
            }

        } catch (Exception e) {
            Allure.step("❌ Failed to click Notify Customer toggle button. Reason: " + e.getMessage());
        }
    }

    public boolean verify_Notify_Customer_Toogle_Button_On_State_FulfilmentPopup() {
        boolean toggleState = false;
        try {
            toggleState = notify_customer_toogle_button
                    .getAttribute("style")
                    .contains("rgb(223, 223, 223)");

            if (!toggleState) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            Allure.step("❌ Failed to verify Notify Customer toggle button. Reason: " + e.getMessage());
        }
        return toggleState;
    }

    public String getCustomerNotificationNote(String ast_date_time) {
        try {
            // Extract the label text ("Note:") dynamically
            WebElement labelElement = getDriver().findElement(By.xpath("//div[contains(@class,'fullFillNote')]//label"));
            String labelText = labelElement.getText().trim(); // "Note:"

            // Extract the date/time text dynamically
            WebElement dateTimeElement = getDriver().findElement(By.id("fullfilldatetime"));
            String dateTimeText = dateTimeElement.getText().trim(); // e.g., "Aug 25 2025 2:42AM"

            // Parse the given date/time (system assumes it's server/local time)
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma", Locale.ENGLISH);
            LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeText, inputFormatter);

            // Convert parsed time into AST timezone
            ZoneId astZone = ZoneId.of("America/Halifax"); // AST zone
            ZonedDateTime astTime = parsedDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(astZone);

            // Format the AST time for display
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma", Locale.ENGLISH);
            String formattedASTTime = astTime.format(outputFormatter);

            // Build final message
            String finalMessage = labelText + " Customer notified on " + ast_date_time + " about design completion";

            // Log to Allure in human-readable form
            Allure.step("✅ " + finalMessage);

            return finalMessage;

        } catch (Exception e) {
            Allure.step("❌ Unable to extract customer notification note. Reason: " + e.getMessage());
            return "Error extracting customer notification note.";
        }
    }

    public boolean verify_Notify_Customer_Toogle_Button_Off_State_FulfilmentPopup() {
        boolean toggleState = false;
        try {
            toggleState = notify_customer_toogle_button
                    .getAttribute("style")
                    .contains("rgb(223, 223, 223)");

            if (toggleState) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            Allure.step("❌ Failed to verify Notify Customer toggle button. Reason: " + e.getMessage());
        }
        return toggleState;
    }

    public String get_Actual_Customer_Notification_Notes() {
        return getElementText(customer_notification_note, "Customer Notification label on fullfilment details popup");
    }

    public boolean is_Customer_Notification_Notes_Label_Not_Displayed() {
        try {
            if (customer_notification_note.isDisplayed()) {
                //Allure.step("❌ Customer Notification Notes label is displayed, but it should NOT be visible.");
                return false;
            } else {
                Allure.step("✅ Customer Notification Notes label exists but is hidden (not displayed).");
                return true;
            }
        } catch (NoSuchElementException e) {
            Allure.step("✅ Customer Notification Notes label is not present in the DOM.");
            return true;
        } catch (Exception e) {
            Allure.step("⚠ Unexpected error while checking Customer Notification Notes label: " + e.getMessage());
            return false;
        }
    }


    /**
     * Enters the design recipe text on fullfilment details popup
     *
     * @param designrecipe
     * @Author Balaji N
     */
    public void Enter_Design_Recipe(String designrecipe) {
        ClickAndType(design_recipe_textbox_on_fulfillment_details_popup, designrecipe, "Design Recipe textbox field on fulfillment details popup");
    }

    public void clear_Entered_Design_Recipe() {
        design_recipe_textbox_on_fulfillment_details_popup.clear();
    }

    /**
     * It retrieves the entered design recipe value from the fullfillment details popup
     *
     * @return
     * @Author Balaji N
     */
    public String get_Entered_Design_Recipe_On_Fullfillment_Details_Popup() {
        return getElementAttribute(design_recipe_textbox_on_fulfillment_details_popup, "Design Recipe textbox field on fulfillment details popup");
    }

    /**
     * Submit button on fulfillment details popup
     *
     * @Author Balaji N
     */
    public void click_SubmitButton_On_Fulfillment_Details_Popup() {
        js_Click(submit_button_on_fullfillment_details_popup, "Submit button on fullfillment details popup");
    }

    public void click_Close_Icon_Fullfilment_Details_Popup() {
        js_Click(close_icon_fullfilment_details_popup, "Fullfilment popup close icon");
    }

    /**
     * It verifies the priority order flag icon on all orders page
     *
     * @return If the priority order flag icon is displayed it returns true else false
     * @Author Balaji N
     */
    public boolean verify_Priority_Order_Flag_Icon_On_All_Orders_Page(String invoice) {
        WebElement priority_flag = getDriver().findElement(By.xpath("//span[@class='set-invoice-number' and text()='" + invoice + "']/preceding::td[1]//div/child::div[contains(@id,'IsFlagColor')]//span"));
        return isElementDisplayed(priority_flag, "Priority Order Flag Icon");
    }

    public void click_Pending_Pickup_Tab() {
        click(pending_pickups_tab_on_allorderspage, "Pending pickup tab on all order page");
    }

    public String get_Order_Type_Dropdown_On_Filters() {
        return get_Element_Text(order_type_dropdown_on_allorderspage, "order type filters");
    }

    /**
     * It verifies the delivery status table grid is displayed or not based on status
     *
     * @return
     */
    public boolean verify_Pending_Delivery_Status_Table_Grid_IsDisplayed(String delivery_status) {
        click(pending_deliveries_tab_on_all_order_page, "Pending deliveries tab on all order page");
        delayWithGivenTime(3000);

        boolean allStatusArePending = true;
        for (WebElement cell : listOfStatus_on_all_OrderPage) {
            HighlightElement(cell);
            String status = cell.getText().trim();
            if (!status.equalsIgnoreCase(delivery_status)) {
                allStatusArePending = false;
                System.out.println("Mismatch Found on pending delivery status: " + status);
            }
        }
        return allStatusArePending;
    }

    /**
     * It verifies the delivery status table grid is displayed or not based on status
     *
     * @return
     */
    public boolean verify_Dispatched_Status_Table_Grid_IsDisplayed(String dispatched_status) {
        click(pending_confirmations_tab_on_all_order_page, "Pending Confirmation tab on all order page");
        delayWithGivenTime(3000);

        boolean allStatusAreDispatched = true;
        for (WebElement cell : listOfStatus_on_all_OrderPage) {
            HighlightElement(cell);
            String status = cell.getText().trim();
            if (!status.equalsIgnoreCase(dispatched_status)) {
                allStatusAreDispatched = false;
                System.out.println("Mismatch Found on dispatched status: " + status);
            }
        }
        return allStatusAreDispatched;
    }

    /**
     * It verifies the order info popup is displayed or not
     *
     * @return If the order info popup is displayed it returns true else false
     * @Author Balaji N
     */
    public boolean verify_Order_Info_Popup_IsDisplayed() {
        return isElementDisplayed(order_details_invoice_popup_all_order_page, "Order info popup");
    }

    public boolean is_Transfer_To_Proposal_Page_Popup_Displayed() {
        return isElementDisplayed(transferring_to_proposal_page_popup, "Transfer to proposal page popup");
    }

    public void click_Ok_Button_On_Transfer_To_Proposal_Page_Popup() {
        click(ok_button_on_transferring_to_proposal_page_popup, "Ok button on transfer to proposal page popup");
    }

    public boolean is_Cancel_Order_Popup_Displayed() {
        return is_Element_Displayed(cancel_order_popup_title, "Cancel order popup");
    }

    public boolean is_Redelivery_Order_Confirmation_Message_Displayed() {
        return is_Element_Displayed(redelivery_confirmation_message, "Redelivery order confirmation message");
    }

    public void set_New_Delivery_Date_On_Redelivery_Popup(String new_delivery_date) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate targetDate = LocalDate.parse(new_delivery_date, formatter);

            int targetDay = targetDate.getDayOfMonth();
            String targetMonthYear = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + targetDate.getYear();

            // Open the datepicker
            click(redelivery_newdeliverydate, "New delivery date on redelivery popup");

            // Navigate to the correct month and year
            while (true) {
                String displayedMonthYear = deliverydate_monthyear_on_advancedispatchpage.getText().trim();
                if (displayedMonthYear.equalsIgnoreCase(targetMonthYear)) {
                    break;
                }
                deliverydate_nextbutton_on_advancedispatchpage.click();
            }

            // Select the day
            for (WebElement dayElement : listofdays_on_deliverydate_datepicker) {
                if (dayElement.isEnabled() && dayElement.getText().equalsIgnoreCase(String.valueOf(targetDay))) {
                    click(dayElement, "Select the New delivery date on the Redelivery popup");  // dayElement.click();
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to set the new delivery date on Redelivery popup: " + e.getMessage());
        }

    }


    public void click_Submit_Button_On_Redelivery_Popup() {
        click(redelivery_popup_submit_button, "Submit button on redelivery popup");
    }

    public String get_Status_Description_On_Redelivery_Popup() {
        return get_Element_Text(status_description_row1, "Status description on redelivery popup");
    }

    public void click_Standing_Recurring_Orders_Submenu() {
        Mouse_Hover(this, orders_menu, "orders menu");
        delayWithGivenTime(2000);
        click(recurring_orders_submenu, "standing recurring orders submenu");
    }

    public void click_Save_To_Funeral_Log() {
        click(savetofunerallog_submenu, "Save to funeral log submenu");
    }


    public boolean is_SaveToFuneral_Confirmation_Popup_Displayed() {
        return isElementDisplayed(save_to_funeral_log_alert_popup, "Save to funeral log confirmation popup");
    }

    public String get_SaveToFuneral_Confirmation_Popup_Header() {
        return get_Element_Text(savetofuneral_confirmation_popup_header, "Save to funeral log confirmation popup header");
    }

    public String get_SaveToFuneral_Confirmation_Popup_Message() {
        return get_Element_Text(savetofuneral_confirmation_popup_message, "Save to funeral log confirmation popup message");
    }

    public String get_OrderDetails_Information_On_SaveToFuneral_Confirmation_Popup() {
        return get_Element_Text(savetofuneral_confirmation_popup_message2, "Save to funeral log confirmation popup message");
    }

    public String get_confirmation_code_with_message() {
        return get_Element_Text(savetofuneral_confirmation_code, "Confirmation code on save to funeral log confirmation popup");
    }

    public String get_Confirmation_Code_On_SaveToFuneral_Confirmation_Popup() {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(get_Element_Text(savetofuneral_confirmation_code, "Confirmation code on save to funeral log confirmation popup"));

        String code = "";
        if (matcher.find()) {
            code = matcher.group();
        }
        return code;
    }

    public void enter_Confirmation_Code_On_SaveToFuneral_Confirmation_Popup(String code) {
        ClickAndType(confirmation_code_textbox, code, "Confirmation code on save to funeral log confirmation popup");
    }

    public void click_Cancel_Button_On_SaveToFuneral_Confirmation_Popup() {
        click(cancel_button_on_save_to_funeral_log_alert_popup, "Cancel button on save to funeral log confirmation popup");
    }

    public void click_Ok_Button_On_SaveToFuneral_Confirmation_Popup() {
        click(ok_button_on_save_to_funeral_log_alert_popup, "Ok button on save to funeral log confirmation popup");
    }

    public String get_Activities_Tab_Refund_Message() {
        return get_Element_Text(activities_tab_on_order_update_refund_message, "Activities tab refund message");
    }

    public String get_Activities_Tab_Order_Updated_Message() {
        return get_Element_Text(activities_tab_on_order_update, "Activities tab order updated message");
    }

    public String get_Activities_Tab_Delivery_On_Truck_Label_IsDisplayed() {
        return get_Element_Text(deliveryontruck_msg_on_activities_tab, "Activities tab delivery on truck label");
    }
//==================== Assign Designer Popup methods ========================================

    /**
     * Clicks the Assign Designer Button on dashboard order page
     *
     * @Author Balaji N
     */
    public void click_Assign_Designer_Button() {
        Mouse_Hover(this, OrdersMenu, "Orders - Menu on Hana Dashboard Page");
        delayWithGivenTime(3000);
        click(assign_designers_button, "Assign designer button");
    }

    public boolean is_Assign_Designer_Popup_Displayed() {
        return isElementDisplayed(assign_designers_popup, "Assign designer popup");
    }

    public void click_Close_Icon_On_Assign_Designer_Popup() {
        js_Click(close_icon_on_assign_designers_popup, "Close icon on assign designer popup");
    }

    public boolean is_Assign_Designer_Header_Displayed() {
        return isElementDisplayed(assign_designers_popup_header, "Assign designer popup header");
    }

    public void select_Designer_On_Assign_Designer_Popup(String designerName) {
        drop_Down(select_designer_dropdown_on_assign_designers_popup, designerName, "VisibleText", "Select Designer dropdown on assign designer popup");
    }

    public String get_Selected_Designer_On_Assign_Designer_Popup() {
        return get_selected_option(select_designer_dropdown_on_assign_designers_popup, "Designer dropdown value on assign designer popup");
    }

    public void set_Fulfillment_Date_Time_On_Assign_Designer_Popup(String date) {
        ClickAndType(fullfilment_date_time_textbox_on_assign_designers_popup, date, "Fulfillment date time text box on assign designer popup");
    }

    public String get_Entered_Date_Time_On_Assign_Designer_Popup() {
        return getElementAttribute(fullfilment_date_time_textbox_on_assign_designers_popup, "Fullfilment date and time textbox field");
    }

    public void enter_Scan_Order_Number_On_Assign_Designer_Popup(String orderNumber) {
        ClickAndType(scan_order_textbox_on_assign_designers_popup, orderNumber, "Scan order number text box on assign designer popup");
        delayWithGivenTime(500);
        scan_order_textbox_on_assign_designers_popup.sendKeys(Keys.ENTER);
    }

    public String get_Displayed_InvoiceNumber_On_Assign_Designer_TableGrid() {
        return getElementText(assigned_designer_invoicenumber_on_tablegrid_assign_designers_popup, "Invoice number row 1 on assign designer table grid");
    }

    public String get_Displayed_CustomerName_On_Assign_Designer_TableGrid() {
        return getElementText(customer_name_assigneddesigner_on_tablegrid_assign_designers_popup, "Order number row 1 on assign designer table grid");
    }

    public void click_save_Designer_Assignment_Button() {
        click(save_assigner_assignments_button_on_assign_designers_popup, "Save designer assignment button on assign designer popup");
    }

    public String get_Delivery_Confirmation_Source() {
        return get_Element_Text(delivery_confirmation_source, "Delivery confirmation source - Dispatch Tab Invoice order popup");
    }

    public String get_Activities_Tab_Delivery_Confirmation_Header() {
        return getElementText(activities_tab_delivery_confirmation_header, "Activities tab delivery confirmation header");
    }

    public boolean is_Wireout_Message_Header_Displayed() {
        return isElementDisplayed(wire_out_message_header, "Wireout message header on invoice order popup");
    }

    public boolean is_WireOut_Information_Displayed() {
        return isElementDisplayed(wire_out_information_section, "Wireout information on invoice order details popup");
    }

    public String get_Wireout_Filling_Shop() {
        return get_Element_Text(wire_out_filling_shop, "Wireout filling shop label on invoice order details popup");
    }

    public String get_Wireout_Filling_Shopname() {
        return get_Element_Text(wire_out_filling_shop_name, "Wireout filling shopname label on invoice order details popup");
    }

    public String get_Wireout_Wire_Method() {
        return get_Element_Text(wire_out_wire_method, "Wireout wire method label on invoice order details popup");
    }

    public String get_Wireout_Wire_Amount() {
        return get_Element_Text(wire_out_wire_amount, "Wireout amount label on invoice order details popup");
    }

    public String get_Activities_Section_Full_Message() {
        return get_Element_Text(activities_section_fullmsg, "Activities section full message label on invoice order details popup");
    }

    public String get_Product_Section_ItemName1() {
        return getElementText(product_itemname_row1, "Product section item name 1 label on invoice order details popup");
    }

    public String get_Product_Section_ItemName2() {
        return getElementText(product_itemname_row2, "Product section item name 2 label on invoice order details popup");
    }

    public String get_Product_Section_ItemCode1() {
        return getElementText(product_itemcode_row1, "Product section item code 1 label on invoice order details popup");
    }

    public String get_Product_Section_ItemCode2() {
        return getElementText(product_itemcode_row2, "Product section item code 2 label on invoice order details popup");
    }

    public String get_Product_Section_Quantity1() {
        return getElementText(product_itemqty_row1, "Product quantity section quantity 1 label on invoice order details popup");
    }

    public String get_Product_Section_Quantity2() {
        return getElementText(product_itemqty_row2, "Product quantity section quantity 2 label on invoice order details popup");
    }

    public String get_Product_Section_Price1() {
        return getElementText(product_itemprice_row1, "Product price section price 1 label on invoice order details popup");
    }

    public String get_Product_Section_Price2() {
        return getElementText(product_itemprice_row2, "Product price section price 2 label on invoice order details popup");
    }

    public String get_Product_Description_Row1() {
        return getElementText(product_description_table_row1, "Product description row 1 label on invoice order details popup");
    }

    public String get_Product_Description_Row2() {
        return getElementText(product_description_table_row2, "Product description row 2 label on invoice order details popup");
    }

    public String get_Product_unitprice_Row1() {
        return getElementText(product_unitprice_table_row1, "Product unit price row 2 label on invoice order details popup");
    }

    public String get_Product_unitprice_Row2() {
        return getElementText(product_unitprice_table_row2, "Product unit price row 2 label on invoice order details popup");
    }

    public String get_Product_units_Row1() {
        return getElementText(product_units_table_row1, "Product units row 2 label on invoice order details popup");
    }

    public String get_Product_units_Row2() {
        return getElementText(product_units_table_row2, "Product units row 2 label on invoice order details popup");
    }

    public String get_Product_Total_Row1() {
        return getElementText(product_price_table_row1, "Product total row 1 label on invoice order details popup");
    }

    public String get_Product_Total_Row2() {
        return getElementText(product_price_table_row2, "Product total row 2 label on invoice order details popup");
    }

    public String get_Product_Total() {
        return getElementText(product_total_table, "Product total label on invoice order details popup");
    }

    public String get_Expected_Product_Total() {
        double row1Total = Double.parseDouble(get_Product_Total_Row1().replace("$", ""));
        double row2Total = Double.parseDouble(get_Product_Total_Row2().replace("$", ""));
        double expectedTotal = row1Total + row2Total;
        return String.format("%.2f", expectedTotal);
    }


    public String get_Single_Expected_Product_Total() {
        double row1Total = Double.parseDouble(get_Product_Total_Row1().replace("$", ""));
        double expectedTotal = row1Total;
        return String.format("%.2f", expectedTotal);
    }

    public String get_Relay_Fee() {
        return getElementText(relay_fee_on_tablegrid, "relay fee on invoice order details popup");
    }

    public String get_Delivery_Fee() {
        return getElementText(delivery_fee_on_tablegrid, "Delivery fee on invoice order details popup");
    }

    public String get_Discount_Amount() {
        return getElementText(discount_amount_on_tablegrid, "Grand total on invoice order details popup");
    }

    public String get_Sales_Tax() {
        return getElementText(sales_tax_on_tablegrid, "Sales tax on invoice order details popup");
    }

    public String get_Grand_Total() {
        return getElementText(total_on_tablegrid, "Grand total on invoice order details popup");
    }

    public String get_Expected_Grand_Total() {
        // Parse product total
        double productTotal = Double.parseDouble(get_Product_Total().replace("$", "").replace(",", ""));

        // Parse relay and delivery fee
        double relayFee = Double.parseDouble(get_Relay_Fee().replace("$", "").replace(",", ""));
        double deliveryFee = Double.parseDouble(get_Delivery_Fee().replace("$", "").replace(",", ""));

        // Parse sales tax
        double salesTax = Double.parseDouble(get_Sales_Tax().replace("$", "").replace(",", ""));

        // Parse discount, handling ($0.00) format
        String discountRaw = get_Discount_Amount().replace("$", "").replace(",", "").replace("(", "-").replace(")", "");
        double discount = Double.parseDouble(discountRaw);

        // Calculate grand total
        double grandTotal = productTotal + relayFee + deliveryFee + salesTax - discount;

        // Format to 2 decimal places
        return String.format("%.2f", grandTotal);
    }

    public String get_Paid_Amount() {
        return getElementText(paid_amount_on_tablegrid, "Paid amount on invoice order details popup");
    }

    public String get_Payment_Type() {
        return getElementText(payment_type_on_tablegrid, "Paid amount on invoice order details popup");
    }

    public String get_MOP_On_Update_Order_Popup() {
        return getElementText(mop_on_update_order_popup, "MOP on update order popup");
    }

    public String expected_Discount_On_Update_Order_Popup() {
        double subTotal = Double.parseDouble(getElementText(sub_total_on_updateorder_popup, "Subtotal").replace("$", ""));
        double expectedDiscount = (subTotal) * 0.10;
        return String.format("%.2f", expectedDiscount);
    }

    public String get_Discount_On_Update_Order_Popup() {
        return getElementText(discount_on_updateorder_popup, "Discount on update order popup");
    }

    public String get_GrandTotal_On_Update_Order_Popup() {
        return getElementText(grand_total_on_updateorder_popup, "Grand total on update order popup");
    }

    public String get_Expected_GrandTotal_On_Update_Order_Popup() {
        // Parse subtotal
        double subTotal = Double.parseDouble(getElementText(sub_total_on_updateorder_popup, "Subtotal").replace("$", ""));

        // Parse relay and delivery fee
        double relayFee = Double.parseDouble(getElementText(relay_fee_on_updateorder_popup, "Relay Fee").replace("$", ""));
        double deliveryFee = Double.parseDouble(getElementText(delivery_fee_on_updateorder_popup, "Delivery Fee").replace("$", ""));

        // Parse sales tax
        double salesTax = Double.parseDouble(getElementText(sales_tax_on_updateorder_popup, "Sales Tax").replace("$", ""));

        // Parse discount, handling ($0.00) format
        String discountRaw = getElementText(discount_on_updateorder_popup, "Discount").replace("$", "");
        double discount = Double.parseDouble(discountRaw);

        // Calculate grand total
        double grandTotal = subTotal + relayFee + deliveryFee + salesTax - discount;

        // Format to 2 decimal places
        return String.format("%.2f", grandTotal);
    }

    //    public void click_Close_Icon_QuickView_Popup() {
//            if (is_Element_Displayed(quickview_popup, "Invoice Quick view popup")) {
//                js_Click(quickview_popup_close_icon, "Quick view popup close icon");
//            }
//    }
    public void click_Close_Icon_QuickView_Popup() {
        try {
            WebDriverWait shortWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
            WebElement popup = shortWait.until(ExpectedConditions.visibilityOf(quickview_popup));

            if (popup.isDisplayed()) {
                // click(quickview_popup_close_icon, "Quick view popup close icon");
                quickview_popup_close_icon.click();
            }
        } catch (TimeoutException e) {
            System.out.println("Quick view popup not displayed within 5 seconds.");
        } catch (Exception e) {
            System.out.println("Error while closing quick view popup: " + e.getMessage());
        }
    }


    public void close_QuickView() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
            WebElement quickViewPopup = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("div .popover-content div .quickview")
            ));

            if (quickViewPopup.isDisplayed()) {
                WebElement closeBtn = getDriver().findElement(By.cssSelector("div .quickview i.modal-action-container-close-icon"));
                js_Click(closeBtn, "QuickView Close Button");
                String msg = "✅ QuickView popup was displayed and successfully closed.";
                System.out.println(msg);
                // Allure.step(msg);
            }

        } catch (TimeoutException | NoSuchElementException e) {
            String msg = "ℹ️ QuickView popup not displayed. Continuing with flow.";
            // System.out.println(msg);
            //  Allure.step(msg);
        } catch (Exception e) {
            String err = "❌ Unexpected error while closing Quick View Popup: " + e.getMessage();
            System.err.println(err);
            Allure.step(err);
            throw new RuntimeException(err, e);
        }
    }

    public String get_Displayed_Delivery_Confirmation_Message() {
        return getElementText(activities_tab_Messages.get(0), "Activities tab Delivery Confirmation Message").trim();
    }

    public String get_Displayed_Delivered_Date_Time_LeftSide() {
        return getElementText(delivered_date_time_on_leftside, "Delivered date time on left side of Invoice order details popup");
    }

    public String get_Displayed_Driver_Name_LeftSide() {
        return getElementText(driver_name_on_leftside, "Driver name on left side of Invoice order details popup");
    }

    public String get_Displayed_Confirmation_Message_On_Dispatch_Tab() {
        return getElementText(confirmation_message_on_dispatch_tab, "Confirmation message on Dispatch tab Invoice order popup");
    }

    public String get_Displayed_Delivered_Code_On_Dispatch_Tab() {
        return getElementText(delivery_code_on_dispatch_tab, "Delivered date time on Dispatch tab Invoice order popup");
    }

    public String get_Displayed_Driver_Notes_On_Dispatch_Tab() {
        return getElementText(delivery_notes_on_dispatch_tab, "Driver notes on Dispatch tab Invoice order popup");
    }

    public void update_Recipient_FirstName(String firstName) {
        ClickAndType(recipient_firstname_textbox_on_updateorder_popup, firstName, "Recipient first name textbox on update recipient popup");
    }

    public String get_Recipient_FirstName_On_UpdateOrder_Popup() {
        return getElementAttribute(recipient_firstname_textbox_on_updateorder_popup, "Recipient first name textbox on update recipient popup");
    }

    public void update_Recipient_LastName(String lastName) {
        ClickAndType(recipient_lastname_textbox_on_updateorder_popup, lastName, "Recipient last name textbox on update recipient popup");
    }

    public String get_Recipient_LastName_On_UpdateOrder_Popup() {
        return getElementAttribute(recipient_lastname_textbox_on_updateorder_popup, "Recipient last name textbox on update recipient popup");
    }

    public void update_Recipient_PhoneNumber1(String phoneNumber) {
        ClickAndType(recipient_phone_textbox_on_updateorder_popup, phoneNumber, "Recipient phone number1 textbox on update recipient popup");
    }

    public String get_Recipient_PhoneNumber1_On_UpdateOrder_Popup() {
        return getElementAttribute(recipient_phone_textbox_on_updateorder_popup, "Recipient phone number1 textbox on update recipient popup");
    }

    public void search_And_Select_Address1_On_UpdateOrder_Popup(String address1) {
        By first_match_address = By.xpath(
                "(//div[contains(@class,'pac-container') and not(contains(@style,'display: none'))]" +
                        "//div[contains(@class,'pac-item')])[1]"
        );

        try {
            wait_For_Page_To_Be_Stable(getDriver());

            recipient_addressline1_textbox_on_updateorder_popup.clear();
            delayWithGivenTime(500);
            recipient_addressline1_textbox_on_updateorder_popup.sendKeys(address1);
            delayWithGivenTime(2000);

            boolean clicked = false;
            int maxRetries = 3;

            for (int attempt = 1; attempt <= maxRetries; attempt++) {
                try {
                    System.out.println("🔄 Attempt " + attempt + ": Trying to select suggestion...");

                    WebElement suggestion = getDriver().findElement(first_match_address);
                    Click(suggestion, "Recipient Address 1 autosuggestion (attempt " + attempt + ")");

                    String msg = "✅ Successfully clicked suggestion for '" + address1 + "' on attempt " + attempt;
                    clicked = true;
                    break;

                } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException e) {
                    String warnMsg = "⚠️ Attempt " + attempt + " failed: " + e.getClass().getSimpleName();
                    Allure.step(warnMsg);
                    System.out.println(warnMsg);

                    if (attempt < maxRetries) {
                        recipient_addressline1_textbox_on_updateorder_popup.clear();
                        delayWithGivenTime(500);
                        recipient_addressline1_textbox_on_updateorder_popup.sendKeys(address1);
                        delayWithGivenTime(2000);
                    } else {
                        System.out.println("❌ Final attempt failed.");
                    }

                } catch (Exception e) {
                    String errMsg = "❌ Unexpected error on attempt " + attempt + ": " + e.getMessage();
                    Allure.step(errMsg);
                    System.out.println(errMsg);
                }
            }

            if (!clicked) {
                String failMsg = "❌ Failed to select Recipient Address 1 suggestion after " + maxRetries + " attempts for input: '" + address1 + "'";
                Allure.step(failMsg);
                throw new RuntimeException(failMsg);
            }

        } catch (Exception e) {
            throw new RuntimeException("🔴 Exception in SearchAndSelectReciAddress1: " + e.getMessage(), e);
        }
    }

    public String get_Recipient_Address1_On_UpdateOrder_Popup() {
        return getElementAttribute(recipient_addressline1_textbox_on_updateorder_popup, "Recipient address1 textbox on update recipient popup");
    }

    public String get_Zipcode_On_UpdateOrder_Popup() {
        return getElementAttribute(recipient_zipcode_textbox_on_updateorder_popup, "Recipient zipcode textbox on update recipient popup");
    }

    public String get_City_On_UpdateOrder_Popup() {
        return getElementAttribute(recipient_city_textbox_on_updateorder_popup, "Recipient city textbox on update recipient popup");
    }

    public String get_State_On_UpdateOrder_Popup() {
        return getElementAttribute(recipient_state_textbox_on_updateorder_popup, "Recipient state textbox on update recipient popup");
    }

    public void select_Updated_Country_On_UpdateOrder_Popup(String country) {
        drop_Down(recipient_country_dropdown_on_updateorder_popup, country, "VisibleText", "Recipient country dropdown on update recipient popup");
    }

    public String get_Country_On_UpdateOrder_Popup() {
        return get_selected_option(recipient_country_dropdown_on_updateorder_popup, "Recipient country textbox on update recipient popup");
    }

    public void select_Updated_Zone_On_UpdateOrder_Popup(String zone) {
        drop_Down(recipient_zone_dropdown_on_updateorder_popup, zone, "VisibleText", "Recipient zone dropdown on update recipient popup");
    }

    public String get_Zone_On_UpdateOrder_Popup() {
        return get_selected_option(recipient_zone_dropdown_on_updateorder_popup, "Recipient zone textbox on update recipient popup");
    }

    public void select_Updated_SalesRep_On_UpdateOrder_Popup(String salesRep) {
        drop_Down(sales_rep_dropdown_on_updateorder_popup, salesRep, "VisibleText", "Sales rep dropdown on update order popup");
    }

    public String get_SalesRep_On_UpdateOrder_Popup() {
        return get_selected_option(sales_rep_dropdown_on_updateorder_popup, "Sales rep textbox on update order popup");
    }

    public void select_OrderType_On_UpdateOrder_Popup(String orderType) {
        drop_Down(order_type_dropdown_on_updateorder_popup, orderType, "VisibleText", "Order type dropdown on update order popup");
    }

    public String get_OrderType_On_UpdateOrder_Popup() {
        return get_selected_option(order_type_dropdown_on_updateorder_popup, "Order type textbox on update order popup");
    }

    public void select_Occasion_Dropdown_On_UpdateOrder_Popup(String occasion) {
        drop_Down(occasion_dropdown_on_updateorder_popup, occasion, "VisibleText", "Occasion dropdown on update order popup");
    }

    public String get_Occasion_On_UpdateOrder_Popup() {
        return get_selected_option(occasion_dropdown_on_updateorder_popup, "Occasion textbox on update order popup");
    }

    public void update_Email_On_UpdateOrder_Popup(String email) {
        ClickAndType(email_textbox_on_updateorder_popup, email, "Email textbox in order details on update order popup");
    }

    public String get_Email_On_UpdateOrder_Popup() {
        return getElementAttribute(email_textbox_on_updateorder_popup, "Email textbox in order details on update order popup");
    }

    public String get_Sold_By_On_Invoice_Order_Popup() {
        return getElementText(soldby_label_on_order_invoice_details_popup, "Sold By on invoice order popup");
    }

    public String get_Occasion_On_Invoice_Order_Popup() {
        return getElementText(occasion_label_on_order_invoice_details_popup, "Occasion on invoice order popup");
    }

    public String get_OrderType_On_Invoice_Order_Popup() {
        return getElementText(ordertype_label_on_order_invoice_details_popup, "Order type on invoice order popup");
    }


}


	
	

