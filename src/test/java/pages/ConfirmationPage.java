package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ConfirmationPage {

    private WebDriver driver;

    private final String cssSelector1 = "shop-app[page='checkout']";
    private final String cssSelector2 = "shop-checkout[name='checkout']";
    private final String cssSelector3 = " div:nth-child(2) > iron-pages:nth-child(1) > header:nth-child(2) > h1:nth-child(1)";
    private final String cssSelector4 = "a[href='/']";


    @FindBy(css= cssSelector1)
    private WebElement element1;

    @FindBy(css= cssSelector2)
    private WebElement element2;

    @FindBy(css= cssSelector3)
    private WebElement element3;

    @FindBy(css= cssSelector4)
    private WebElement element4;


    private SearchContext getShadow1() throws InterruptedException {
        Thread.sleep(100);
        return element1.getShadowRoot();
    }

    private SearchContext getShadow2() throws InterruptedException {
        Thread.sleep(100);
        return getShadow1().findElement(By.cssSelector(cssSelector2)).getShadowRoot();
    }

    private WebElement getThankYouMsgLocator() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector3));
    }

    private WebElement getFinishButtonLocator() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector4));
    }



    public ConfirmationPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public String getThankYouMsgText() throws InterruptedException {
        Thread.sleep(1000);
        return getThankYouMsgLocator().getText().trim();
    }

    public void clickOnFinishButton() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-1050)", "");
        getFinishButtonLocator().click();
    }

    public String getFinishButtonText() throws InterruptedException {
        return getFinishButtonLocator().getText().trim();
    }



 }
