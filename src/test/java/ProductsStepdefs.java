import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class ProductsStepdefs {
    private WebDriver driver = Hook.driver;
    public ProductsStepdefs() {
        PageFactory.initElements(Hook.driver, this);
    }
    private void snooze() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Given("I open web site {string}")
    public void iOpenWebSite(String arg0) {
        driver.get(arg0);
    }

    @FindBy(id="user-name")
        private WebElement username;
    @And("I enter my username {string}")
    public void iEnterMyUsername(String arg0) {
        username.sendKeys(arg0);
    }

    @FindBy(id = "password")
        private WebElement password;
    @And("I enter my password {string}")
    public void iEnterMyPassword(String arg0) {
        password.sendKeys(arg0);
    }
    @And("I click on login button")
    public void iClickOnLoginButton() {
        driver.findElement(By.id("login-button")).click();
    }
    @FindBy(className = "inventory_item_name")
        private List<WebElement> products;
    @When("I select the first product {string}")
    public void iSelectTheFirstProduct(String index) {
        products.get(Integer.valueOf(index)).click();
    }
    String actualTitle;
    @FindBy(css = ".inventory_details_name.large_size")
        private WebElement titleElt;
    @And("I find product title")
    public void iFindProductTitle() {
        actualTitle = titleElt.getText();
    }

    String actualPrice;
    @FindBy(css = ".inventory_details_price")
    private WebElement priceElt;
    @And("I find product price")
    public void iFindProductPrice() {
        actualPrice = priceElt.getText();
    }


    @And("I add product to cart")
    public void iAddProductToCart() {
        driver.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).click();
    }

    @And("I go back to products page")
    public void iGoBackToProductsPage() {
        driver.findElement(By.id("back-to-products")).click();
    }

    @And("I open my cart")
    public void iOpenMyCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    String actualProductTitleInCart;
    @And("I find the title of product in my cart")
    public void iFindTheTitleOfProductInMyCart() {
        actualProductTitleInCart = driver.findElement(By.className("inventory_item_name")).getText();
    }


    @Then("Products page title is {string}")
    public void productsPageTitleIs(String arg0) {
        String homePageTitle = driver.findElement(By.className("app_logo")).getText();
        Assert.assertEquals(homePageTitle, arg0);
    }

    @And("Product title is {string}")
    public void productTitleIs(String arg0) {
        Assert.assertEquals(arg0, actualTitle);
    }


    @And("Product price is {string}")
    public void productPriceIs(String arg0) {
        Assert.assertEquals(arg0, actualPrice);
        snooze();
    }


    @And("Product title in my cart is as {string}")
    public void productTitleInMyCartIsAs(String arg0) {
        Assert.assertEquals(arg0, actualProductTitleInCart);
        snooze();
    }
}
