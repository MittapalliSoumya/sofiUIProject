package modules;

import base.BaseTest;
import com.swaglabs.sofiUi.pages.CheckoutPage;
import com.swaglabs.sofiUi.pages.LoginPage;
import com.swaglabs.sofiUi.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class AddToCartTest extends BaseTest {


    ProductsPage productsPage;
    CheckoutPage checkout;
    List<String> productList;


    @Test
    public void VerifyAddToCart(){

        //Variables specific to the test case
        String expectedProductLabel = "Products";
        String expectedPageTitle = "Your Cart";
        String productName1 = "Sauce Labs Onesie" ;
        String productName2 = "Sauce Labs Bike Light";


        // login to the application
        LoginPage login = new LoginPage(driver);
        login.enterUserName(props.getProperty("swaglabs.username"));
        login.enterpassword(props.getProperty("swaglabs.password"));
        productsPage = login.clickLogin() ;

        // verify that the user logged in
        Assert.assertEquals(productsPage.getProductLabel(),expectedProductLabel);

        productList = new ArrayList<>();
        productList.add(productName1);
        productList.add(productName2);

        //Add products to cart
        productsPage.addProductToCart(productList);
        //checkout products
        checkout =  productsPage.clickOnShoppingCart();

        //verify the checkout page title
        Assert.assertEquals(checkout.getPageTitle().getText(),expectedPageTitle);

        //verify the product added to cart
        Assert.assertEquals(checkout.getProductAddedList(),productList);



    }


    @AfterMethod
    public void deleteAddedItems(){
        //remove the added items for clean up
        checkout.removeProductFromCart(productList);
    }
}
