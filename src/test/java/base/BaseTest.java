package base;

import com.swaglabs.sofiUi.common.CommonHelper;
import com.swaglabs.sofiUi.pages.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    private static Logger LOGGER = Logger.getLogger(BaseTest.class);
    protected CommonHelper commonHelper = new CommonHelper();
    protected static Properties props;
    protected WebDriver driver;
    protected static String browser;
    protected static String environment;
    


    @BeforeSuite(alwaysRun=true)
    public void setUpSuite() throws IOException {
        LOGGER.info("==> Before Suite reading properties file");
        props = commonHelper.readProperties("application.properties");
        browser = System.getProperty("browser");
        environment = System.getProperty("environment");
        if(browser == null || browser.equalsIgnoreCase("")){
            browser = props.getProperty("browser");
        }
        if(environment == null || environment.equalsIgnoreCase("")){
            environment = props.getProperty("env.url");
        }
    }

    @BeforeClass(alwaysRun=true)
    public void setupClass(){
        LOGGER.info("==> Before class getting driver object and starting the browser");
        driver = BasePage.getNewDriver(BasePage.BrowserType.valueOf(browser.toUpperCase()));
        driver.get(environment);
        driver.manage().window().maximize();
    }


    @AfterMethod(alwaysRun=true)
    public void afterMethod(ITestResult result){
        if(ITestResult.FAILURE==result.getStatus()) {
            commonHelper.captureScreenshot(driver,result.getName() );
        }
    }


    @AfterClass(alwaysRun=true)
    public void teardownClass()throws IOException{
        if(driver!=null) {
            LOGGER.info("Closing chrome browser");
            driver.quit();
        }

    }


}