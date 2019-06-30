package com.swaglabs.sofiUi.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductsPage extends BasePage {

    private static Logger LOGGER = Logger.getLogger(ProductsPage.class);
    private WebDriver driver;

    @FindBy(className = "product_label")
    private WebElement productLabel;

    @FindBy(xpath = "//div[contains(text(),'Sauce Labs Onesie')]/ancestor::div[@class='inventory_item']//button")
    private WebElement product;

    @FindBy(xpath = "//div[@id='shopping_cart_container']/a")
    private WebElement cart;



    public ProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //get label how
    }

    public WebElement productLabel() {
        return productLabel;
    }

    public String getProductLabel() {
        return productLabel().getText();
    }

    /***
     * This method gets the list of product names and clicks on each product Addto bag
     * @param productNames - it will pass the productNames to the method
     */

    public void addProductToCart(List<String> productNames){
        LOGGER.info("==> click on products and add to bag");

        try{
        for(String productName : productNames) {
            String locator = "//div[contains(text(),'" + productName + "')]/ancestor::div[@class='inventory_item']//button";
            WebElement element = driver.findElement(By.xpath(locator));
            element.click();
        }
        }catch (NoSuchElementException e){
            LOGGER.info(" product not available to click");
        }

    }


    /***
     * This method clicks on shopping cart
     * @return - it will return CheckoutPage
     */
    public CheckoutPage clickOnShoppingCart(){
        cart.click();
        LOGGER.info("clicked on cart");
        return new CheckoutPage(driver);
    }



}
