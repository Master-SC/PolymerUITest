package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {

    private WebDriver driver;

    private final String cssSelector1 = "shop-app[page='home']";
    private final String cssSelector2 = "a[aria-label='SHOP Home']";
    private final String cssSelector3 = ".iron-selected";
    private final String cssSelector4 = "a[aria-label=\"%s Shop Now\"]";


    @FindBy(css = cssSelector1)
    private WebElement element1 ;

    @FindBy(css = cssSelector2)
    private WebElement element2;

    @FindBy(css = cssSelector3)
    private WebElement element3;
    
    private SearchContext getShadow1(){
        return element1.getShadowRoot();
    }

    private SearchContext getShadow2(){
        return getShadow1().findElement(By.cssSelector(cssSelector3)).getShadowRoot();
    }


    public HomePage (WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement getShopLink(){
       return getShadow1().findElement(By.cssSelector(cssSelector2));
    }

    private WebElement getChosenCategoryLink(String shop){
       return getShadow2().findElement(By.cssSelector(String.format(cssSelector4,shop)));
    }

    public void clickOnChosenCategoryLink(String shop){
        getChosenCategoryLink(shop).click();
    }

}

