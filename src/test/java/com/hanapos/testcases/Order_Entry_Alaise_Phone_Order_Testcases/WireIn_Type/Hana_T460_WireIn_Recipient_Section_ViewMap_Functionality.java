package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireIn_Type;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T460_WireIn_Recipient_Section_ViewMap_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    public static final String dataSheetName = "Hana_T455";
    String invoiceNumber;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Phone Order Module - Wire In Type")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T460_WireIn_Recipient_Section_ViewMap_Functionality_Test(String salesperson, String FirstName, String LastName, String Shopcode, String ShopName, String WireIn_Method, String Order_Number, String PhoneNumber,
                                                                                       String Email, String custaddress1, String Comments, String custzip, String custphone, String recifname, String recilname, String reciaddress1, String reciaddress2, String recizip,
                                                                                       String recicity, String recicountry, String reciphone, String recilocation, String occasion, String searchandselectitemcode, String paymenttype, String cashregistry) {

        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T460_WireIn_Recipient_Section_ViewMap_Functionality_Test  ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page");
         /*   dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));
*/
            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 6 - Shop name is not displayed in the WireIn section of phone order page");

            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            softassert.assertEquals(phoneorder.get_SelectedSalesPersonOn_PhoneOrderEntryPage(), prop.getProperty("salesperson"), "Selected Sales Person " + prop.getProperty("salesperson") + " is not displayed on phone order page");
            phoneorder.Click_WireIn_DeliveryType_OnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnWireInTypeOnPhoneOrderPage(), "#676a6c", "Wire In as delivery type is not highlighted in blue color");

            // Test Step - 7
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            phoneorder.Enter_WireIn_Fname(FirstName);
            phoneorder.Enter_WireIn_Lname(LastName);
            phoneorder.Enter_WireIn_ShopCode(Shopcode);
            phoneorder.Enter_WireIn_ShopName(ShopName);
            phoneorder.Select_WireInMethod(WireIn_Method);
            phoneorder.Enter_WireIn_OrderNumber(Order_Number);
            phoneorder.Enter_WireIn_PhoneNumber(PhoneNumber);
            phoneorder.Enter_WireIn_Email(Email);
            phoneorder.Enter_WireIn_ShopAddress(custaddress1);
            phoneorder.Enter_WireIn_Comments(Comments);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Fname(), "Abish", "Test Step - 7 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Lname(), "David", "Test Step - 7 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopCode(), "1100", "Test Step - 7 - Entered WireIn shop code is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopName(), "Automation Test Flower Shop", "Test Step - 7 - Entered WireIn shop name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Selected_WireInMethod(), "FSN", "Test Step - 7 - Selected WireIn method is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_OrderNumber(), "1122FSN33", "Test Step - 7 - Entered WireIn order number is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_PhoneNumber(), "956-655-0756", "Test Step - 7 - Entered phone number on customer details popup is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Email(), "hanaposqateam@gmail.com", "Test Step - 7 - Entered shop Email id on customer details popup is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopAddress(), "2715 35th Avenue Greeley, CO, USA", "Test Step - 7 - Entered shop address  is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Comments(), "Test Automation Wire In Order", "Test Step - 7 - Shop Wire In Comments is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 8
            phoneorder.EnterReciFirstName(recifname);
            phoneorder.EnterReciLastName(recilname);
            phoneorder.EnterReciPhone(reciphone);
            phoneorder.EnterRecipientPhone2OnPhoneOrderPage(prop.getProperty("recipient_altphonenumber"));

            softassert.assertEquals(phoneorder.getReciFirstName(), "Abish", "Test Step - 8 - Entered first name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "David", "Test Step - 8 - Entered last name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), "956-655-0756", "Test Step - 8 - Recipient phone number is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), "878-787-7878", "Test Step - 8 - Recipient floor and apt is not displayed on phone order page recipient section");

            // Test Step - 9
            phoneorder.EnterReciAddress1(prop.getProperty("recipient_address1"));
            press_Tab_Key();
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_MapIconIsAppear_OnRecipientAddress1(), "Test Step - 9 - Map icon is not displayed on next to recipient address 1 field");

            // Test Step - 10
            delayWithGivenTime(3000);
            phoneorder.Click_MapIconOn_RecipientAddress1();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_MapDirectionPopup_IsAppear(), "Test Step - 10 - Map direction popup is not displayed on next to recipient address 1 field");

            // Test Step - 11
            phoneorder.Click_Row1DirectionInstructions_OnMapDirectionPopup();
            softassert.assertTrue(phoneorder.Verify_TooltipTextIsAppear_OnMapDirectionPopup(), "Test Step - 11 - Tooltip text is not displayed on selected row 1 direction instructions on next to recipient address 1 field");

            // Test Step - 12
            phoneorder.Click_CloseIcon_OnMapDirectionPopup();
            softassert.assertFalse(phoneorder.Verify_TooltipClosed_OnMapDirectionPopup(), "Test Step - 12 - Tooltip text is displayed on selected row 1 direction instructions on next to recipient address 1 field");

            // Test Step - 13
            phoneorder.Click_SatelliteMapIcon_OnMapDirectionPopup_ReciAddress1();

            // Test Step - 14
            phoneorder.MouseHoverSatelliteTabAndClickLabel_SatelliteMapIcon();

            // Test Step - 15
            phoneorder.ClickMapTab_OnMapDirectionPopup_ReciAddress1();

            // Test Step - 16
            phoneorder.MouseHoverMapTabAndClickTerrain_OnMapDirectionPopup_ReciAddress1();

            // Test Step - 17
          /*  if (phoneorder.Verify_PlusIcon_OnMapDirection_Popup_At_ReciAddress1_IsDisplayed() == true) {
                phoneorder.Click_PlusIcon_OnMapDirectionPopup_ReciAddress1();
                delayWithGivenTime(2000);
            }

            softassert.assertTrue(phoneorder.VerifyMapIsZoomIn(), "Test Step - 17 - Map direction popup is not zoom in recipient address 1 field");

            // Test Step - 18
            if (phoneorder.Verify_MinusIcon_OnMapDirection_Popup_At_ReciAddress1_IsDisplayed() == true) {
                phoneorder.Click_MinusIcon_OnMapDirectionPopup_ReciAddress1();
                delayWithGivenTime(2000);
            }*/

         //   softassert.assertTrue(phoneorder.VerifyMapIsZoomOut(), "Test Step - 18 - Map direction popup is not zoom out recipient address 1 field");

            // Test Step - 19
            phoneorder.DragAndDrop_DragPegman_OnMapDirectionPopup_ReciAddress1();

            //Test Step - 20
            phoneorder.Click_FullscreenIcon_OnMapDirectionPopup_ReciAddress1();
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_MapDirectionPopup_DisplayedFullScreenMode(), "Test Step - 20 - Map direction popup is not displayed as full screen on recipient address 1 field");

            // Test Step - 21
            actionsEscapeKey();

            // Test Step - 22
            phoneorder.Click_CloseIconOnMapDirectionPopup_ReciAddress1();
            softassert.assertFalse(phoneorder.MapDirectionPopup_isClosed(), "Test Step - 22 - Map direction popup is not closed on  recipient address 1 field");

        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}