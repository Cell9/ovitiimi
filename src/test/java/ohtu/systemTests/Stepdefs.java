package ohtu.systemTests;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.File;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

public class Stepdefs {

    WebDriver driver;

    public Stepdefs() {
        this.driver = new HtmlUnitDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("^user is at the main page$")
    public void user_is_at_the_main_page() throws Throwable {
        driver.get("http://localhost:" + 8080 + "/");
        Thread.sleep(500);
    }
    
    @Given("user is at the nettilähteet page")
    public void user_is_at_the_nettilahteet_page() throws Throwable {
        driver.get("http://localhost:8080/links");
        Thread.sleep(500);
    }
    
    @When("a new nettilähde is created")
    public void a_new_nettilahde_is_created() throws Throwable {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys("Test Title");
        element = driver.findElement(By.name("url"));
        element.sendKeys("https://notarealaddress.com");
        element = driver.findElement(By.name("submit"));
        element.submit();
        Thread.sleep(500);
    }

    @When("Nettilähteet is clicked")
    public void nettilahteet_clicked() throws Throwable {
        Thread.sleep(500);
        clickLinkWithText("Nettilähteet");
        Thread.sleep(500);
    }

    @Then("Nettilähteet is shown")
    public void nettilahteet_shown() throws Throwable {
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("Nettilähteet"));
        Thread.sleep(500);
    }
    
    @Then("the new nettilähde is shown")
    public void the_new_nettilahde_is_shown() throws Throwable {
        Thread.sleep(500);
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("Test Title"));
        Thread.sleep(500);
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("https://notarealaddress.com"));
        Thread.sleep(500);
    }
    
    
    
    /*
    @Then("^\"([^\"]*)\" is shown$")
    public void is_shown(String arg1) throws Throwable {
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains(arg1));
    }
    */

    private void clickLinkWithText(String text) {
        int trials = 0;
        while (trials++ < 5) {
            try {
                WebElement element = driver.findElement(By.linkText(text));
                element.click();
                break;
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }

}