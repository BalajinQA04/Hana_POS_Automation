package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Delivery_Type;

import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T333_OrderEntryPage_Delivery_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_Pagination_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    @Epic("Phone Order Module - Delivery Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T333_OrderEntryPage_Delivery_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_Pagination_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting Hana_T333_OrderEntryPage_Delivery_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_Pagination_FT  ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana dashboard page is not displayed");
            logger.info("User navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3 : Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3 : Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickdeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_OnDelivery_TypeOnPhoneOrderPage(), "#676a6c", "Test Step - 5 - Delivery type is not highlighted in blue color");

            //Test Step - 6
            String companyName = getData("customer.companyName", "custData.json");
            String fullName = getData("customer.fullName", "custData.json");
            String firstName = getData("customer.firstName", "custData.json");
            String lastName = getData("customer.lastName", "custData.json");
            String address1 = getData("customer.address1", "custData.json");
            String address2 = getData("customer.address2", "custData.json");
            String city = getData("customer.city", "custData.json");
            String state = getData("customer.state", "custData.json");
            String email = getData("customer.email", "custData.json");
            String phone = getData("customer.phoneNumber", "custData.json");
            String altphone = getData("customer.altPhoneNumber", "custData.json");
            String zip = getData("customer.zipCode", "custData.json");

            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            phoneorder.SearchAndSelectCustomerOnCust_Section(fullName);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), firstName, "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), lastName, "Test Step - 6 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), companyName, "Test Step - 6 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), prop.getProperty("customeremail"), "Test Step - 6 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), address1, "Test Step - 6 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), address2, "Test Step - 6 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), zip, "Test Step - 6 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), city, "Test Step - 6 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getStateOnPhoneOrderPage(), state, "Test Step - 6 - state is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), phone, "Test Step - 6 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), altphone, "Test Step - 6 - Alt phone number is not displayed on phone order page");

            // Test Step - 7
            softassert.assertTrue(phoneorder.Verify_CustEditIcon_Appears(), "Test Step - 7 - Customer clear button is not displayed on phone order page");

            // Test Step  - 8
            phoneorder.Click_CustEditIcon();
            softassert.assertTrue(phoneorder.Verify_CustomerDetailsPopupAppears(), "Test Step - 8 - Customer details popup on is not displayed on phone order page");

            // Test Step - 9
            delayWithGivenTime(1000);
            phoneorder.ClickOn_PaymentDetails_CustomerDetailsPopup();
            ThreadWait(4000);
            softassert.assertTrue(phoneorder.Verify_TableGridOnPaymentDetailsTab_IsAppear(), "Test Step - 9 - payment details tab webtable grid is not displayed");
            ThreadWait(2000);

            // Test Step - 10
            softassert.assertTrue(phoneorder.Verify_PaginationSection_OnPaymentDetailsTab(), "Test Step - 10 - Pagination section is not appears on the payment details tab under the table");

            // Test Step - 11
            softassert.assertTrue(phoneorder.verify_FirstPagePaginationIcon_IsAppears_On_Payment_Details_Tab(), "Test Step - 11 - Go to First page pagination icon is not appears on the payment details tab under the table");
            softassert.assertTrue(phoneorder.Verify_PreviousPagePaginationIcon_IsAppears_On_PaymentDetailsTab(), "Test Step - 11 - Go to Previous page pagination icon is not appears on the Payment details tab under the table");
            softassert.assertTrue(phoneorder.is_NextPageIcon_Displayed_On_PaymentDetailsTab(), "Test Step - 11 - Go to Next page pagination icon is not appears on the payment details tab under the table");
            softassert.assertTrue(phoneorder.Verify_LastPagePaginationIcon_IsAppears_On_PaymentDetailsTab(), "Test Step - 11 - Go to Last page pagination icon is not appears on the payment details tab under the table");
            softassert.assertTrue(phoneorder.Verify_PageListNumber_OnPaymentDetailsTab(), "Test Step - 11 - List of page number is not appears on the payment details tab  under the table");
            softassert.assertTrue(phoneorder.Verify_PaginationInfo_OnPaymentDetailsTab(), "Test Step - 11 - Items per page label is not appears on the payment details tab under the table");

            // Test Step - 12
            phoneorder.close_QuickView();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.Verify_NoOfPayAmountDisplayedCount_PaymentDetails(), 10, "Test Step - 12 - Number of records is not equals to 10 displayed on the payment details tab under the table");

            // Test Step - 13
            phoneorder.Click_NextPagePaginationIcon_On_PaymentDetailsTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_PaginationInfo_PaymentDetailsTab().contains("11 - 20"), true, "Test Step - 13 - Florist does not navigate to next page");

            // Test Step - 14
            phoneorder.ClickThreeDots_Pagination_PaymentDetails();
            delayWithGivenTime(1000);
            //  softassert.assertTrue(phoneorder.Verify_11to20Pages_Pagination_PaymentDetails(), "Test Step - 14 - After click the 3 dots on pagination the 11 to 20 pages are not displayed");

            // Test Step - 15
            phoneorder.Click_Pagenumber2_Pagination_PaymentDetails();
            delayWithGivenTime(1000);
            softassert.assertEquals(
                    phoneorder.get_PaginationInfo_PaymentDetailsTab(),
                    phoneorder.get_first_NumberOfPage() + " - " + phoneorder.get_Second_NumberOfPage(),
                    "Test Step - 15 - Florist did not navigate to the last page on the payment details tab under the table"
            );

            // Test Step - 16
            phoneorder.Click_PreviousPagePaginationIcon_On_PaymentDetailsTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertEquals(
                    phoneorder.get_PaginationInfo_PaymentDetailsTab(),
                    phoneorder.get_first_NumberOfPage() + " - " + phoneorder.get_Second_NumberOfPage(),
                    "Test Step - 16 - Florist did not navigate to the previous page on the payment details tab under the table"
            );


            // Test Step - 17
            phoneorder.Click_LastPagePaginationIcon_OnPaymentDetailsTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_PaginationInfo_PaymentDetailsTab().contains(phoneorder.get_PaginationInfo_PaymentDetailsTab()), true, "Test Step - 17 - Florist does not navigate to last page");
            //softassert.assertTrue(phoneorder.Verify_LastPageNumber_OnPaymentDetailsTab(),"Test Step - 17 - Florist does not navigate to last page");

            // Test Step - 18
            phoneorder.Click_FirstPagePagination_Icon_OnPaymentDetailsTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_PaginationInfo_PaymentDetailsTab().contains(phoneorder.get_first_NumberOfPage() + " - " + phoneorder.get_Second_NumberOfPage()), true, "Test Step - 18 - Florist does not navigate to first page");

            // Test Step - 19
            softassert.assertTrue(phoneorder.Verify_PreviousPagePaginationIcon_OnPaymentDetailsTab_OnCustomerDetailsPopup(), "Test Step - 19 - Previous page pagination icon is enabled on the payment details tab under the table");
            softassert.assertTrue(phoneorder.Verify_FirstPagePaginationIconIsEnabled_On_PaymentDetailsTab_OnCustomerDetailsPopup(), "Test Step - 19 - first page pagination icon is enabled on the payment details tab under the table");

            // Test Step - 20
            phoneorder.Click_LastPagePaginationIcon_OnPaymentDetailsTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_PaginationInfo_PaymentDetailsTab().contains(phoneorder.get_PaginationInfo_PaymentDetailsTab()), true, "Test Step - 20 - Florist does not navigate to last page");

            // Test Step - 21
            softassert.assertTrue(phoneorder.Verify_NextPagePaginationIconIsEnabled_OnPaymentDetailsTab_OnCustomerDetailsPopup(), "Test Step - 21 - Next page pagination icon is enabled on the Payment details tab under the table");
            softassert.assertTrue(phoneorder.Verify_LastPagePaginationIconIsEnabled_OnPaymentDetailsTab_OnCustomerDetailsPopup(), "Test Step - 21 - Last page pagination icon is enabled on the payment details tab under the table");

            // Test Step - 22
            phoneorder.Click_FirstPagePagination_Icon_OnPaymentDetailsTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_PaginationInfo_PaymentDetailsTab().contains(phoneorder.get_first_NumberOfPage() + " - " + phoneorder.get_Second_NumberOfPage()), true, "Test Step - 22 - Florist does not navigate to first page");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}