package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BasketPage {

    private WebDriver driver;

    private final String cssSelector1 = "shop-app[page='cart']";
    private final String cssSelector2 = "shop-cart[name='cart']";
    private final String cssSelector3 = "shop-cart-item";
    private final String cssSelector4 = " div:nth-child(3) > div:nth-child(1) > a:nth-child(1)";
    private final String cssSelector5 = "#quantitySelect";
    private final String cssSelector6 = "span";
    private final String cssSelector7 = "a[aria-label='SHOP Home']";
    private final String cssSelector8 = " div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > shop-cart-item:nth-child(%s)";
    private final String cssSelector9 = ".price";
    private final String cssSelector10 = ".subtotal";
    private final String cssSelector11 = "a[href='/checkout']";


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
    private WebElement element6;

    @FindBy(css= cssSelector7)
    private WebElement element7;

    @FindBy(css= cssSelector8)
    private WebElement element8;

    @FindBy(css= cssSelector9)
    private WebElement element9;

    @FindBy(css= cssSelector10)
    private WebElement element10;

    @FindBy(css= cssSelector11)
    private WebElement element11;


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
        return getShadow2().findElement(By.cssSelector(cssSelector3)).getShadowRoot();
    }

    private SearchContext getShadow4(String line) throws InterruptedException {
        Thread.sleep(100);
        return getShadow2().findElement(By.cssSelector(String.format(cssSelector8,line))).getShadowRoot();
    }

    private WebElement getProduct1NameElement() throws InterruptedException {
        return getShadow3().findElement(By.cssSelector(cssSelector4));
    }

    private WebElement getQtyElement() throws InterruptedException {
        return getShadow3().findElement(By.cssSelector(cssSelector5));
    }

    private WebElement getSizeElement() throws InterruptedException {
        return getShadow3().findElement(By.cssSelector(cssSelector6));
    }

    private WebElement getShopButtonElement() throws InterruptedException {
        return getShadow1().findElement(By.cssSelector(cssSelector7));
    }

    private WebElement getProductsNameElement(String line) throws InterruptedException {
        return getShadow4(line).findElement(By.cssSelector(cssSelector4));
    }

    private WebElement getProductsQtyElement(String line) throws InterruptedException {
        return getShadow4(line).findElement(By.cssSelector(cssSelector5));
    }

    private WebElement getProductsSizeElement(String line) throws InterruptedException {
        return getShadow4(line).findElement(By.cssSelector(cssSelector6));
    }

    private WebElement getProductsPriceElement(String line) throws InterruptedException {
        return getShadow4(line).findElement(By.cssSelector(cssSelector9));
    }

    private WebElement getTotalBasketElement() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector10));
    }

    private WebElement getCheckoutButtonElement() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector11));
    }


    private Select selectQtyElement() throws InterruptedException {
        return new Select(getQtyElement());
    }

    private Select selectProductsQtyElement(String line) throws InterruptedException {
        return new Select(getProductsQtyElement(line));
    }



    public BasketPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public String getProduct1Name() throws InterruptedException {
        return getProduct1NameElement().getText().trim();
    }

    public String getProductQty() throws InterruptedException {
        return selectQtyElement().getFirstSelectedOption().getText().trim();
    }

    public String getProductSize() throws InterruptedException {
        return getSizeElement().getText().trim();
    }

    public void clickOnTheShopButton() throws InterruptedException {
        getShopButtonElement().click();
    }

    public String getShopButtonText() throws InterruptedException {
        return getShopButtonElement().getText().trim();
    }

    public String getProductName(String line) throws InterruptedException {
        return getProductsNameElement(line).getText().trim();
    }

    public String getProductsQty(String line) throws InterruptedException {
        return selectProductsQtyElement(line).getFirstSelectedOption().getText().trim();
    }

    public String getProductsSize(String line) throws InterruptedException {
        return getProductsSizeElement(line).getText().trim();
    }

    public String getProductsPrice(String line) throws InterruptedException {
        return getProductsPriceElement(line).getText().trim().replace("$","").strip();
    }

    public double getCalculatedTotalBasketPrice() throws InterruptedException {
        double totalPrice = 0.00;
        for(int i=1; i<=2; i++){
           String prodQty = getProductsQty(String.valueOf(i));
           String prodPrice = getProductsPrice(String.valueOf(i));
           totalPrice += Double.parseDouble(prodQty) * Double.parseDouble(prodPrice);

        }
        return totalPrice;
    }

    public double getActualBasketPrice() throws InterruptedException {
        String price = getTotalBasketElement().getText().trim().replace("$","").strip();
        return Double.parseDouble(price);
    }

    public void updateProductQty(String line, String Qty) throws InterruptedException {
        selectProductsQtyElement(line).selectByValue(Qty);
    }

    public void clickOnCheckoutButton() throws InterruptedException {
        getCheckoutButtonElement().click();
    }

    public String getCheckoutButtonText() throws InterruptedException {
        return getCheckoutButtonElement().getText().trim();
    }




}
