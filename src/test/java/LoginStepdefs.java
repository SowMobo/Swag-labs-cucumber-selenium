import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginStepdefs {
    private WebDriver driver = Hook.driver;
    public LoginStepdefs() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("incognito");
//        driver = new ChromeDriver(options);
        PageFactory.initElements(driver, this);
    }

    @Given("Je ouvre le site {string}")
    public void jeOuvreLeSite(String arg0) {
        driver.get(arg0);
    }

    @FindBy(id = "user-name")
    private WebElement username;

    @When("Je saisis le username {string}")
    public void jeSaisisLeUsername(String arg0) {
        username.sendKeys(arg0);

    }

    @And("Je saisis le mot de passe {string}")
    public void jeSaisisLeMotDePasse(String arg0) {
        driver.findElement(By.id("password")).sendKeys(arg0);
    }

    @And("Je clique sur le button {string}")
    public void jeCliqueSurLeButton(String arg0) {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("La page de accueil est affichee avec le titre {string}")
    public void laPageDeAccueilEstAfficheeAvecLeTitre(String arg0) {
        String actualTitle = driver.findElement(By.cssSelector("div.app_logo"))
                .getText();
        Assert.assertEquals(arg0, actualTitle);
    }

    @Then("I verify that a error massage is generated {string}")
    public void iVerifyThatAErrorMassageIsGenerated(String arg0) {
        String actualErrorMsg = driver.findElement(By.cssSelector(".error-message-container.error"))
                .getText();
        Assert.assertEquals(actualErrorMsg, arg0);
    }
}
