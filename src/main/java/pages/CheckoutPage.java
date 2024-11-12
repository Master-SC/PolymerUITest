package pages;

import helper.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;

public class CheckoutPage {
    private WebDriver driver;
    HashMap<String, String> checkoutData;

    private final String cssSelector1 = "shop-app[page='checkout']";
    private final String cssSelector2 = "shop-checkout[name='checkout']";
    private final String cssSelector3 = "#accountEmail";
    private final String cssSelector4 = "#accountPhone";
    private final String cssSelector5 = "#shipAddress";
    private final String cssSelector6 = "#shipCity";
    private final String cssSelector7 = "#shipState";
    private final String cssSelector8 = "#shipZip";
    private final String cssSelector9 = "select#shipCountry";
    private final String cssSelector10 = "#ccName";
    private final String cssSelector11 = "#ccNumber";
    private final String cssSelector12 = "select#ccExpMonth";
    private final String cssSelector13 = "select#ccExpYear";
    private final String cssSelector14 = "#ccCVV";
    private final String cssSelector15 = "input[value='Place Order']";


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

    @FindBy(css= cssSelector12)
    private WebElement element12;

    @FindBy(css= cssSelector13)
    private WebElement element13;

    @FindBy(css= cssSelector14)
    private WebElement element14;

    @FindBy(css= cssSelector15)
    private WebElement element15;

    private SearchContext getShadow1() throws InterruptedException {
        Thread.sleep(100);
        return element1.getShadowRoot();
    }

    private SearchContext getShadow2() throws InterruptedException {
        Thread.sleep(100);
        return getShadow1().findElement(By.cssSelector(cssSelector2)).getShadowRoot();
    }

    private WebElement getEmailField() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector3));
    }

    private WebElement getPhoneNumberField() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector4));
    }

    private WebElement getAddressField() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector5));
    }

    private WebElement getCityField() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector6));
    }

    private WebElement getStateField() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector7));
    }

    private WebElement getZipField() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector8));
    }

    private WebElement getCountryField() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector9));
    }

    private WebElement getCardHolderNameField() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector10));
    }

    private WebElement getCardNumberField() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector11));
    }

    private WebElement getCardExpiryMonthField() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector12));
    }

    private WebElement getCardExpiryYearField() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector13));
    }

    private WebElement getCardCvvField() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector14));
    }

    private WebElement getPlaceOrderButtonField() throws InterruptedException {
        return getShadow2().findElement(By.cssSelector(cssSelector15));
    }


    private Select getCountrySelection() throws InterruptedException {
        return new Select(getCountryField());
    }

    private Select getCardExpiryMonthSelection() throws InterruptedException {
        return new Select(getCardExpiryMonthField());
    }

    private Select getCardExpiryYearSelection() throws InterruptedException {
        return new Select(getCardExpiryYearField());
    }

    public CheckoutPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    public void loadDataFromExcel() throws Exception {
       checkoutData = ExcelReader.readCheckoutData("src/test/resources/testdata/CheckoutData.xlsx");
        getEmailField().sendKeys(checkoutData.get("Email"));
        getPhoneNumberField().sendKeys(checkoutData.get("Phone"));
        getAddressField().sendKeys(checkoutData.get("Address"));
        getCityField().sendKeys(checkoutData.get("City"));
        getStateField().sendKeys(checkoutData.get("State"));
        getZipField().sendKeys(checkoutData.get("Zip"));
        getCountrySelection().selectByVisibleText(checkoutData.get("Country"));
        getCardHolderNameField().sendKeys(checkoutData.get("Cardholder Name"));
        getCardNumberField().sendKeys(checkoutData.get("Card Number"));
        getCardExpiryMonthSelection().selectByVisibleText(checkoutData.get("Expiry Month"));
        getCardExpiryYearSelection().selectByVisibleText(checkoutData.get("Expiry Year"));
        getCardCvvField().sendKeys(checkoutData.get("CVV"));
    }

    public void placeOrder() throws InterruptedException {
        getPlaceOrderButtonField().click();
    }

    public String placeOrderButtonText() throws InterruptedException {
        return getPlaceOrderButtonField().getCssValue("value").trim();
    }



}
