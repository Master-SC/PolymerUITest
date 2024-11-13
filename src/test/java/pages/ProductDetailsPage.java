package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class ProductDetailsPage {
    private WebDriver driver;

    private final String cssSelector1 = "shop-app[page='detail']";
    private final String cssSelector2 = "shop-detail[name='detail']";
    private final String cssSelector3 = "#sizeSelect";
    private final String cssSelector4 = "#quantitySelect";
    private final String cssSelector5 = "button[aria-label='Add this item to cart']";
    private final String cssSelector6 = ".opened";
    private final String cssSelector7 = ".label";
    private final String cssSelector8 = "#viewCartAnchor";

    @FindBy(css= cssSelector1)
    private WebElement element1;

    @FindBy(css= cssSelector2)
    private WebElement element2;

    @FindBy(css= cssSelector3)
    private WebElement element3;

    @FindBy(css= cssSelector4)
    private WebElement element4;

    @FindBy(css= cssSelector5)
    private WebElement element5;

    @FindBy(css= cssSelector6)
    private  WebElement element6;

    @FindBy(css= cssSelector7)
    private WebElement element7;

    @FindBy(css= cssSelector8)
    private WebElement element8;


    private SearchContext getShadow1() throws InterruptedException {
        Thread.sleep(100);
        return element1.getShadowRoot();
    }

    private SearchContext getShadow2() throws InterruptedException {
        Thread.sleep(100);
        return getShadow1().findElement(By.cssSelector(cssSelector2)).getShadowRoot();
    }

    private SearchContext getShadow3() throws InterruptedException {
        Thread.sleep(100);
        return getShadow1().findElement(By.cssSelector(cssSelector6)).getShadowRoot();
    }


    private WebElement getSizeDropDown() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector3));
    }

    private WebElement getQtyDropDown() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector4));
    }

    private WebElement addToCartButton() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector5));
    }

    private WebElement addedToCartElement() throws InterruptedException {
        return getShadow3().findElement(By.cssSelector(cssSelector7));
    }

    private WebElement viewCartButton() throws InterruptedException {
        return getShadow3().findElement(By.cssSelector(cssSelector8));
    }


    private Select selectSizeElement() throws InterruptedException {
        return new Select(getSizeDropDown());
    }

    private Select selectQtyElement() throws InterruptedException {
        return new Select(getQtyDropDown());
    }

    public ProductDetailsPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void selectFromDropDownByValue(String size, String qty) throws InterruptedException {
        selectSizeElement().selectByValue(size);
        selectQtyElement().selectByValue(qty);
    }

    public void clickOnAddToCart() throws InterruptedException {
        addToCartButton().click();
    }

    public String getAddedToCartMsg() throws InterruptedException {
       return addedToCartElement().getText();
    }

    public void clickOnViewCartButton() throws InterruptedException {
        viewCartButton().click();
    }


}
