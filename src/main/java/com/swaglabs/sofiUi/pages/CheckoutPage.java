package com.swaglabs.sofiUi.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage extends BasePage {

    private static Logger LOGGER = Logger.getLogger(CheckoutPage.class);
    private WebDriver driver;

    @FindBy(className = "subheader")
    private WebElement pageTitle;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productAddedList;


    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitForElement(getPageTitle());

    }

    public WebElement getPageTitle(){
       return pageTitle;
    }



    /***
     * This method is used to get the product list names
     * @return - returns the products lit
     */

    public List<String> getProductAddedList(){
        List<String> list = new ArrayList<>();
       for(WebElement productName:productAddedList){
          list.add(productName.getText());
       }
       return list;
    }


    /***
     * This method removes the products that areAddto bag
     * @param productNames - it will pass the productNames to the method
     */

    public void removeProductFromCart(List<String> productNames){
        LOGGER.info("==> click on products and to bag");

        for(String productName : productNames) {
            String locator = "//div[contains(text(),'" + productName + "')]/ancestor::div[@class='cart_item_label']//button";
            WebElement element = driver.findElement(By.xpath(locator));
            element.click();
        }
    }

}
