package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.DriverFactory;

public class StepDefinition {

    WebDriver driver = DriverFactory.getDriver();
    HomePage homePage = new HomePage(driver);
    ProductListingPage listingPage = new ProductListingPage(driver);
    ProductDetailsPage detailsPage = new ProductDetailsPage(driver);
    BasketPage basketPage = new BasketPage(driver);
    CheckoutPage checkoutPage = new CheckoutPage(driver);
    ConfirmationPage confirmationPage = new ConfirmationPage(driver);

    @Then("I should be redirected to the Home Page")
    @Given("I am on the Home Page")
    public void i_am_on_the_home_page() {
       homePage.getShopLink().isDisplayed();
    }

    @When("I navigate to {string} Section")
    public void i_navigate_to_section(String string) throws InterruptedException {
        homePage.clickOnChosenCategoryLink(string);
        Assert.assertEquals(listingPage.getHeaderText(),string);
    }

    @When("I Select an item of my choice with Size {string} and Quantity {string}")
    public void i_select_an_item_of_my_choice_with_size_and_quantity(String size, String qty) throws InterruptedException {
        listingPage.selectProduct();
        detailsPage.selectFromDropDownByValue(size,qty);
    }

    @Then("the item should be {string} successfully")
    public void the_item_should_be_added_to_the_cart_successfully(String msg) throws InterruptedException {
        detailsPage.clickOnAddToCart();
        Assert.assertEquals(detailsPage.getAddedToCartMsg(),msg);
    }

    @When("I view the cart")
    public void i_view_the_cart() throws InterruptedException {
       detailsPage.clickOnViewCartButton();

    }
    @Then("I should see the {string} Outwear item with Size {string} and Quantity {string} in the cart")
    public void i_should_see_the_outwear_item_with_size_and_quantity_in_the_cart(String expectedProductName, String expectedSize, String expectedQty) throws InterruptedException {

        Assert.assertTrue(basketPage.getProduct1Name().contains(expectedProductName));
        Assert.assertEquals(basketPage.getProductQty(),expectedQty);
        Assert.assertEquals(basketPage.getProductSize(),expectedSize);
    }

    @When("I click on {string} to go back to the Home Page")
    public void i_click_on_to_go_back_to_the_home_page(String expectedString) throws InterruptedException {
        Assert.assertEquals(basketPage.getShopButtonText(),expectedString);
        basketPage.clickOnTheShopButton();
    }

    @Then("the {string} Outwear with Size {string} and Quantity {string} at line {string} in the cart")
    @Then("I should see both the {string} Outwear with Size {string} and Quantity {string} at line {string}")
    public void i_should_see_both_the_outwear_with_size_and_quantity_at_line(String expectedProductName, String expectedSize, String expectedQty, String line) throws InterruptedException {
        Assert.assertTrue(basketPage.getProductName(line).contains(expectedProductName));
        Assert.assertEquals(basketPage.getProductsQty(line),expectedQty);
        Assert.assertEquals(basketPage.getProductsSize(line),expectedSize);
    }

    @Then("the Total should be recalculated correctly")
    @Then("The Total should reflect the correct amount based on the items selected")
    public void the_total_should_reflect_the_correct_amount_based_on_the_items_selected() throws InterruptedException {
        double delta = 0.0001;
        Assert.assertEquals(basketPage.getActualBasketPrice(),basketPage.getCalculatedTotalBasketPrice(), delta);
    }

    @When("I update the quantity of the {string} Outwear item to {string}")
    public void i_update_the_quantity_of_the_item_to(String name, String qty) throws InterruptedException {
        Assert.assertTrue(basketPage.getProductName("2").contains(name));
        basketPage.updateProductQty("2",qty);
    }

    @Then("the cart should reflect the updated quantity {string}")
    public void the_cart_should_reflect_the_updated_quantity(String expQty) throws InterruptedException {
        Assert.assertEquals(basketPage.getProductsQty("2"),expQty);
    }

    @When("I click on {string} Button")
    public void I_click_on_button(String name) throws InterruptedException {
        Assert.assertEquals(basketPage.getCheckoutButtonText(),name);
        basketPage.clickOnCheckoutButton();
    }

    @When("I enter my account shipping and payment information")
    public void I_enter_my_account_shipping_and_payment_information() throws Exception {
        checkoutPage.loadDataFromExcel();
    }

    @When("I click on {string} at Checkout Page" )
    public void I_click_on_place_order_button(String name) throws InterruptedException {
        Assert.assertEquals(checkoutPage.placeOrderButtonText(),name);
        checkoutPage.placeOrder();
    }

    @Then("I should see a confirmation message saying {string}")
    public void I_should_see_a_confirmation_message_saying(String msg) throws InterruptedException {
        Assert.assertEquals(confirmationPage.getThankYouMsgText(),msg);
    }

    @When("I click on {string} at Confirmation Page")
    public void I_click_on_finish_order_button(String name) throws InterruptedException {
        Assert.assertEquals(confirmationPage.getFinishButtonText(),name);
        confirmationPage.clickOnFinishButton();
    }

}
