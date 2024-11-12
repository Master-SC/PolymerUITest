package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductListingPage {
    private WebDriver driver;
//    private WebDriverWait wait;

    private final String cssSelector1 = "shop-app[page='list']";
    private final String cssSelector2 = "shop-list[name='list']";
    private final String cssSelector3 = "h1";
    private final String cssSelector4 = " ul:nth-child(6) > li:nth-child(1) > a:nth-child(1) > shop-list-item:nth-child(1)";
    private final String cssSelector5 = ".title";

    @FindBy(css= cssSelector1)
    private WebElement element1;

    @FindBy(css= cssSelector2)
    private WebElement element2;

    @FindBy(css= cssSelector3)
    private WebElement element3;

    public ProductListingPage (WebDriver driver){
        this.driver=driver;
//        this.wait = new WebDriverWait(driver, Duration.ofMillis(100));
        PageFactory.initElements(driver,this);
    }


    private SearchContext getShadow1() throws InterruptedException {
//        wait.until(ExpectedConditions.visibilityOf(element1));
        Thread.sleep(100);
        return element1.getShadowRoot();
    }

    private SearchContext getShadow2() throws InterruptedException {
//        wait.until(ExpectedConditions.visibilityOf(element2));
        Thread.sleep(100);
        return getShadow1().findElement(By.cssSelector(cssSelector2)).getShadowRoot();
    }

    private SearchContext getShadow3() throws InterruptedException {

//        wait.until(ExpectedConditions.visibilityOf(element2));
        Thread.sleep(100);
        return getShadow2().findElement(By.cssSelector(cssSelector4)).getShadowRoot();
    }

    private WebElement getListingPageHeader() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector3));
    }

    private WebElement getProductName() throws InterruptedException {
        return getShadow3().findElement(By.cssSelector(cssSelector5));
    }



    public String getHeaderText() throws InterruptedException {
       return getListingPageHeader().getText();
    }

    public void selectProduct() throws InterruptedException {
        Thread.sleep(100);
        getProductName().click();
    }










}
