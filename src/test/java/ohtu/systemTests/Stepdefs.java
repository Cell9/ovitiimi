package ohtu.systemTests;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Stepdefs {

    WebDriver driver;

    public Stepdefs() {
        this.driver = new HtmlUnitDriver(true);        
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

    @When("Lisää lukuvinkki is clicked")
    public void lisaa_lukuvinkki_clicked() throws Throwable {
        Thread.sleep(500);
        clickLinkWithText("Lisää lukuvinkki");
        Thread.sleep(500);
    }

    @Then("Lisää lukuvinkki is shown")
    public void lisaa_lukuvinkki_shown() throws Throwable {
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("Lisää uusi lukuvinkki"));
        Thread.sleep(500);
    }
    
    @Given("user is at the new recommendation page")
    public void user_is_at_the_new_recommendation_page() throws Throwable {
        driver.get("http://localhost:8080");
        Thread.sleep(500);
    }
    
    @When("a new kirja is created")
    public void a_new_kirja_is_created() throws Throwable {
        WebElement element = driver.findElement(By.linkText("Lisää lukuvinkki"));
        element.click();
        
        element = driver.findElement(By.name("addBook"));
        element.click();
        Thread.sleep(500);
        
        element = driver.findElement(By.id("bookTitle"));
        element.sendKeys("Test Book");
        element = driver.findElement(By.id("bookAuthor"));
        element.sendKeys("Nimekas Kirjailija");
        element = driver.findElement(By.id("bookIsbn"));
        element.sendKeys("1234");
        Thread.sleep(500);
        
        element = driver.findElement(By.name("submitBook"));        
        element.submit();
        
        Thread.sleep(500);
    }

    @Then("the new kirja is shown")
    public void the_new_kirja_is_shown() throws Throwable {
        Thread.sleep(500);
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("Test Book"));
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("Nimekas Kirjailija"));
        Thread.sleep(500);
    }

    @Then("a success notification is shown")
    public void a_success_notification_is_shown() throws Throwable {
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("Lisäys onnistui!"));
        Thread.sleep(500);
    }
    
    @When("a faulty kirja is created")
    public void a_faulty_kirja_is_created() throws Throwable {
        WebElement element = driver.findElement(By.linkText("Lisää lukuvinkki"));
        element.click();
        
        element = driver.findElement(By.name("addBook"));
        element.click();
        Thread.sleep(500);
        
        element = driver.findElement(By.id("bookTitle"));
        element.sendKeys("");
        element = driver.findElement(By.id("bookAuthor"));
        element.sendKeys("");
        element = driver.findElement(By.id("bookIsbn"));
        element.sendKeys("1234");
        Thread.sleep(500);
        
        element = driver.findElement(By.name("submitBook"));        
        element.submit();
        
        Thread.sleep(500);
    }
    
    @Then("an error notification for missing title is shown")
    public void an_error_notification_for_missing_title_is_shown() throws Throwable {
        WebElement element = driver.findElement(By.tagName("body"));
        String msg = element.getText();
        assertTrue(msg.contains("Kirjalle tulee syöttää nimi"));
        Thread.sleep(500);
    }
    
    @Then("an error notification for missing author is shown")
    public void an_error_notification_for_missing_author_is_shown() throws Throwable {
        WebElement element = driver.findElement(By.tagName("body"));
        String msg = element.getText();
        assertTrue(msg.contains("Kirjalle tulee syöttää kirjailija"));
        Thread.sleep(500);
    }

    @When("a new nettilähde is created")
    public void a_new_link_is_created() throws Throwable {
        WebElement element = driver.findElement(By.linkText("Lisää lukuvinkki"));
        element.click();
        
        element = driver.findElement(By.name("addLink"));
        element.click();
        Thread.sleep(500);
        
        element = driver.findElement(By.id("linkTitle"));
        element.sendKeys("Test Link");
        element = driver.findElement(By.id("linkUrl"));
        element.sendKeys("http://testi.com");
        Thread.sleep(500);
        
        element = driver.findElement(By.name("submitLink"));        
        element.submit();
        
        Thread.sleep(500);
    }

    @When("a new nettilähde without html-scheme is created")
    public void a_new_link_without_html_is_created() throws Throwable {
        WebElement element = driver.findElement(By.linkText("Lisää lukuvinkki"));
        element.click();
        
        element = driver.findElement(By.name("addLink"));
        element.click();
        Thread.sleep(500);
        
        element = driver.findElement(By.id("linkTitle"));
        element.sendKeys("Test Link");
        element = driver.findElement(By.id("linkUrl"));
        element.sendKeys("testi.com");
        Thread.sleep(500);
        
        element = driver.findElement(By.name("submitLink"));        
        element.submit();
        
        Thread.sleep(500);
    }
    
    @Then("the new nettilähde is shown")
    public void the_new_link_is_shown() throws Throwable {
        Thread.sleep(500);
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("Test Link"));
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("http://testi.com"));
        Thread.sleep(500);
    }

    @When("a new podcast is created")
    public void a_new_podcast_is_created() throws Throwable {
        WebElement element = driver.findElement(By.linkText("Lisää lukuvinkki"));
        element.click();
        
        element = driver.findElement(By.name("addPod"));
        element.click();
        Thread.sleep(500);
        
        element = driver.findElement(By.id("podcastTitle"));
        element.sendKeys("Test Podcast");
        element = driver.findElement(By.id("podcastAuthor"));
        element.sendKeys("Kuuluva Ääni");
        element = driver.findElement(By.id("podcastUrl"));
        element.sendKeys("bbc.areena.be");
        element = driver.findElement(By.id("podcastDescription"));
        element.sendKeys("äänekäs");

        Thread.sleep(500);
        
        element = driver.findElement(By.name("submitPod"));        
        element.submit();
        
        Thread.sleep(500);
    }

    @Then("the new podcast is shown")
    public void the_new_podcast_is_shown() throws Throwable {
        Thread.sleep(500);
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("Test Podcast"));
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("Kuuluva Ääni"));
        Thread.sleep(500);
    }

    @When("a faulty podcast is created")
    public void a_faulty_podcast_is_created() throws Throwable {
        WebElement element = driver.findElement(By.linkText("Lisää lukuvinkki"));
        element.click();
        
        element = driver.findElement(By.name("addPod"));
        element.click();
        Thread.sleep(500);
        
        element = driver.findElement(By.id("podcastTitle"));
        element.sendKeys("");
        element = driver.findElement(By.id("podcastAuthor"));
        element.sendKeys("");
        element = driver.findElement(By.id("podcastUrl"));
        element.sendKeys("bbc.areena.be");
        element = driver.findElement(By.id("podcastDescription"));
        element.sendKeys("äänekäs");

        Thread.sleep(500);
        
        element = driver.findElement(By.name("submitPod"));        
        element.submit();
        
        Thread.sleep(500);
    }

    @Then("an error notification for missing podcast title is shown")
    public void an_error_notification_for_missing_podcast_title_is_shown() throws Throwable {
        WebElement element = driver.findElement(By.tagName("body"));
        String msg = element.getText();
        assertTrue(msg.contains("Podcastille tulee syöttää nimi"));
        Thread.sleep(500);
    }

    @Then("an error notification for missing podcast author is shown")
    public void an_error_notification_for_missing_podcast_author_is_shown() throws Throwable {
        WebElement element = driver.findElement(By.tagName("body"));
        String msg = element.getText();
        assertTrue(msg.contains("Podcastille tulee syöttää tekijä"));
        Thread.sleep(500);
    }

    @When("a new youtube is created")
    public void a_new_youtube_is_created() throws Throwable {
        WebElement element = driver.findElement(By.linkText("Lisää lukuvinkki"));
        element.click();
        
        element = driver.findElement(By.name("addTube"));
        element.click();
        Thread.sleep(500);
        
        element = driver.findElement(By.id("youtubeTitle"));
        element.sendKeys("Test Youtube");
        element = driver.findElement(By.id("youtubeAuthor"));
        element.sendKeys("Kaunis Kasvo");
        element = driver.findElement(By.id("youtubeUrl"));
        element.sendKeys("you.tu.be");
        element = driver.findElement(By.id("youtubeDescription"));
        element.sendKeys("näyttävä");

        Thread.sleep(500);
        
        element = driver.findElement(By.name("submitYoutube"));        
        element.submit();
        
        Thread.sleep(500);
    }

    @Then("a new youtube is shown")
    public void a_new_youtube_is_shown() throws Throwable {
        Thread.sleep(500);
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("Test Youtube"));
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("Kaunis Kasvo"));
        Thread.sleep(500);
    }

    @When("a faulty youtube is created")
    public void a_faulty_youtube_is_created() throws Throwable {
        WebElement element = driver.findElement(By.linkText("Lisää lukuvinkki"));
        element.click();
        
        element = driver.findElement(By.name("addTube"));
        element.click();
        Thread.sleep(500);
        
        element = driver.findElement(By.id("youtubeTitle"));
        element.sendKeys("");
        element = driver.findElement(By.id("youtubeAuthor"));
        element.sendKeys("");
        element = driver.findElement(By.id("youtubeUrl"));
        element.sendKeys("");
        element = driver.findElement(By.id("youtubeDescription"));
        element.sendKeys("");

        Thread.sleep(500);
        
        element = driver.findElement(By.name("submitYoutube"));        
        element.submit();
        
        Thread.sleep(500);
    }

    @Then("an error notification for missing youtube title is shown")
    public void an_error_notification_for_missing_youtube_title_is_shown() throws Throwable {
        WebElement element = driver.findElement(By.tagName("body"));
        String msg = element.getText();
        assertTrue(msg.contains("Youtube-videolle tulee syöttää nimi"));
        Thread.sleep(500);
    }

    @Then("an error notification for missing youtube author is shown")
    public void an_error_notification_for_missing_youtube_author_is_shown() throws Throwable {
        WebElement element = driver.findElement(By.tagName("body"));
        String msg = element.getText();
        assertTrue(msg.contains("Youtube-videolle tulee syöttää tekijä"));
        Thread.sleep(500);
    }
    
    @Then("an error notification for missing youtube url is shown")
    public void an_error_notification_for_missing_youtube_url_is_shown() throws Throwable {
        WebElement element = driver.findElement(By.tagName("body"));
        String msg = element.getText();
        assertTrue(msg.contains("Youtube-videolle tulee antaa url-osoite."));
        Thread.sleep(500);
    }
    @Then("an error notification for missing youtube description is shown")
    public void an_error_notification_for_missing_youtube_description_is_shown() throws Throwable {
        WebElement element = driver.findElement(By.tagName("body"));
        String msg = element.getText();
        assertTrue(msg.contains("Youtube-videolle tulee antaa lyhyt kuvaus."));
        Thread.sleep(500);
    }        

    @When("new book is filtered out")
    public void new_book_is_filtered_out() throws Throwable {
        WebElement element = driver.findElement(By.id("filterInput"));
        element.sendKeys("And");

        Thread.sleep(500);
    }
    
    @Then("new book is not shown")
    public void new_book_is_not_shown() throws Throwable {
        WebElement element = driver.findElement(By.tagName("body"));
        String filteredList = element.getText();
        assertTrue(!filteredList.contains("Test Book"));
        Thread.sleep(500);
    }
    
    
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
