package com.swaglabs.sofiUi.pages;


import com.swaglabs.sofiUi.exceptions.SwaglabsException;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    private static Logger LOGGER = Logger.getLogger(LoginPage.class);
    private WebDriver driver;


    @FindBy(id = "user-name")
    private WebElement swagLabusername;

    @FindBy(id = "password")
    private WebElement swagLabpassword;

    @FindBy(className = "btn_action")
    private WebElement loginbtn;

    @FindBy(className = "login_logo")
    private WebElement loginlogo;


    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitForElement(getLoginLogo());
    }


    public WebElement getLoginLogo(){
        return loginlogo;
    }
    public void enterUserName(String userName){
        swagLabusername.sendKeys(userName);
    }

    public void enterpassword(String password){
        swagLabpassword.sendKeys(password);
    }

    public ProductsPage clickLogin()throws SwaglabsException {
        try{
            if(loginbtn.isDisplayed() && loginbtn.isEnabled()){
                loginbtn.click();
                return new ProductsPage(driver);
            }else {
                LOGGER.error("login button not displayed or enabled");
                throw new SwaglabsException("Unable to click the login button as it is not displayed or enabled");
            }
        }catch(NoSuchElementException e){

            LOGGER.error("login button can;t be clicked");
            throw new SwaglabsException("Unable to click the login button" ,e);

        }

    }
}
