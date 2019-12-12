package ohtu.systemTests;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.Random;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import com.gargoylesoftware.css.parser.CSSErrorHandler;
import com.gargoylesoftware.css.parser.CSSException;
import com.gargoylesoftware.css.parser.CSSParseException;
import com.gargoylesoftware.htmlunit.WebClient;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.logging.Logger;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(
  locations = "classpath:application-test.properties")
public class Stepdefs {

    WebDriver driver;

    String courseName1;
    String courseName2;
    
    private Wait<WebDriver> waiter;

    @LocalServerPort
    private int springPort;

    public Stepdefs() {
        this.driver = new HtmlUnitDriver(true) {
        	@Override
        	protected WebClient modifyWebClient(WebClient client) {
        		client.setCssErrorHandler(new CSSErrorHandler() {
					@Override
					public void warning(CSSParseException exception) throws CSSException {
					}

					@Override
					public void error(CSSParseException exception) throws CSSException {
					}

					@Override
					public void fatalError(CSSParseException exception) throws CSSException {
					}
        		});
        		
        		return client;
    		}
        };
                
        this.waiter = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
    
    private String getMainUrl() {
    	return "http://localhost:" + this.springPort + "/";
    }

    @Before
    public void initializeUnicNames() {
        Random rand = new Random();
        courseName1 = "TKT1uniikki" + rand.nextInt(1000000000);
        courseName2 = "TKT2uniikki" + rand.nextInt(1000000000);
    }

    @Given("^user is at the main page$")
    public void user_is_at_the_main_page() throws Throwable {
        driver.get(this.getMainUrl());
    }

    @When("Kurssit is clicked")
    public void kurssit_is_clicked() throws Throwable {
        this.findAndClick(By.linkText("Kurssit"));
    }

    @Then("Lisää uusi kurssi is shown")
    public void lisaa_uusi_kurssi_is_shown() throws Throwable {
        assertTrue(this.find(By.tagName("body")).getText().contains("Lisää uusi kurssi"));
    }

    @When("a new course is created")
    public void a_new_course_is_created() throws Throwable {
        this.findAndClick(By.linkText("Kurssit"));

        WebElement element = this.find(By.id("courseCode"));
        element.sendKeys(courseName1);
        
        element = this.find(By.id("courseName"));
        element.sendKeys("Kiva kurssi");

        element = this.find(By.name("submitCourse"));
        element.submit();
    }

    @When("a new course is created without a name")
    public void a_new_course_is_created_without_a_name() throws Throwable {
        this.findAndClick(By.linkText("Kurssit"));

        WebElement element = this.find(By.id("courseCode"));
        element.sendKeys(courseName2);
        
        element = this.find(By.id("courseName"));
        element.sendKeys("");

        element = this.find(By.name("submitCourse"));
        element.submit();
    }

    @Then("the new course is shown")
    public void the_new_course_is_shown() throws Throwable {
        assertTrue(this.find(By.tagName("body")).getText().contains(courseName1));
        assertTrue(this.find(By.tagName("body")).getText().contains("Kiva kurssi"));
    }

    @Then("an error notification for missing course name is shown")
    public void an_error_notification_for_missing_course_name_is_shown() throws Throwable {
    	WebElement element = this.find(By.tagName("body"));
    	String msg = element.getText();
    	assertTrue(msg.contains("Kurssille tulee syöttää nimi"));
	}

    @When("Lisää lukuvinkki is clicked")
    public void lisaa_lukuvinkki_clicked() throws Throwable {
        this.findAndClick(By.linkText("Lisää lukuvinkki"));
    }

    @Then("Lisää lukuvinkki is shown")
    public void lisaa_lukuvinkki_shown() throws Throwable {
        assertTrue(this.find(By.tagName("body")).getText().contains("Lisää uusi lukuvinkki"));
    }
    
    @Given("user is at the new recommendation page")
    public void user_is_at_the_new_recommendation_page() throws Throwable {
        driver.get(this.getMainUrl());
    }
    
    @When("a new kirja is created")
    public void a_new_kirja_is_created() throws Throwable {
        this.findAndClick(By.linkText("Lisää lukuvinkki"));
        
        this.findAndClick(By.name("addBook"));
        
        WebElement element = this.find(By.id("bookTitle"));
        element.sendKeys("Test Book");
        
        element = this.find(By.id("bookAuthor"));
        element.sendKeys("Nimekas Kirjailija");
        
        element = this.find(By.id("bookIsbn"));
        element.sendKeys("1234");
        
        element = this.find(By.name("submitBook"));        
        element.submit();
    }

    @Then("the new kirja is shown")
    public void the_new_kirja_is_shown() throws Throwable {

        assertTrue(this.find(By.tagName("body")).getText().contains("Test Book"));
        assertTrue(this.find(By.tagName("body")).getText().contains("Nimekas Kirjailija"));
    }

    @Then("a success notification is shown")
    public void a_success_notification_is_shown() throws Throwable {
        assertTrue(this.find(By.tagName("body")).getText().contains("Lisäys onnistui!"));
    }
    
    @When("a faulty kirja is created")
    public void a_faulty_kirja_is_created() throws Throwable {
        this.findAndClick(By.linkText("Lisää lukuvinkki"));
        this.findAndClick(By.name("addBook"));
        
        WebElement element = this.find(By.id("bookTitle"));
        element.sendKeys("");
        
        element = this.find(By.id("bookAuthor"));
        element.sendKeys("");
        
        element = this.find(By.id("bookIsbn"));
        element.sendKeys("1234");
        
        element = this.find(By.name("submitBook"));        
        element.submit();
    }
    
    @Then("an error notification for missing title is shown")
    public void an_error_notification_for_missing_title_is_shown() throws Throwable {
        WebElement element = this.find(By.tagName("body"));

        assertTrue(element.getText().contains("Kirjalle tulee syöttää nimi"));
    }
    
    @Then("an error notification for missing author is shown")
    public void an_error_notification_for_missing_author_is_shown() throws Throwable {
        WebElement element = this.find(By.tagName("body"));

        assertTrue(element.getText().contains("Kirjalle tulee syöttää kirjailija"));
    }

    @When("a new nettilähde is created")
    public void a_new_link_is_created() throws Throwable {
    	
        this.findAndClick(By.linkText("Lisää lukuvinkki"));
        this.findAndClick(By.name("addLink"));
        
        WebElement element = this.find(By.id("linkTitle"));
        element.sendKeys("Test Link");
        
        element = this.find(By.id("linkUrl"));
        element.sendKeys("http://testi.com");
        
        element = this.find(By.name("submitLink"));        
        element.submit();
    }

    @When("a new nettilähde is created without url")
    public void a_new_link_is_created_without_url() throws Throwable {
        this.findAndClick(By.linkText("Lisää lukuvinkki"));

        this.findAndClick(By.name("addLink"));

        WebElement element = this.find(By.id("linkTitle"));
        element.sendKeys("Test Link");
        
        element = this.find(By.id("linkUrl"));
        element.sendKeys("");

        element = this.find(By.name("submitLink"));
        element.submit();
    }

    @When("a new nettilähde without html-scheme is created")
    public void a_new_link_without_html_is_created() throws Throwable {
        this.findAndClick(By.linkText("Lisää lukuvinkki"));
        this.findAndClick(By.name("addLink"));
        
        WebElement element = this.find(By.id("linkTitle"));
        element.sendKeys("Test Link");
        
        element = this.find(By.id("linkUrl"));
        element.sendKeys("testi.com");
        
        element = this.find(By.name("submitLink"));        
        element.submit();
    }
    
    @Then("the new nettilähde is shown")
    public void the_new_link_is_shown() throws Throwable {
        assertTrue(this.find(By.tagName("body"))
                .getText().contains("Test Link"));
        assertTrue(this.find(By.tagName("body"))
                .getText().contains("http://testi.com"));
    }

    @When("a new podcast is created")
    public void a_new_podcast_is_created() throws Throwable {
        this.findAndClick(By.linkText("Lisää lukuvinkki"));
        this.findAndClick(By.name("addPod"));
        
        WebElement element = this.find(By.id("podcastTitle"));
        element.sendKeys("Test Podcast");
        
        element = this.find(By.id("podcastAuthor"));
        element.sendKeys("Kuuluva Ääni");
        
        element = this.find(By.id("podcastUrl"));
        element.sendKeys("bbc.areena.be");
        
        element = this.find(By.id("podcastDescription"));
        element.sendKeys("äänekäs");
        
        element = this.find(By.name("submitPod"));        
        element.submit();
    }

    @Then("the new podcast is shown")
    public void the_new_podcast_is_shown() throws Throwable {
        assertTrue(this.find(By.tagName("body"))
                .getText().contains("Test Podcast"));
        assertTrue(this.find(By.tagName("body"))
                .getText().contains("Kuuluva Ääni"));
    }

    @When("a faulty podcast is created")
    public void a_faulty_podcast_is_created() throws Throwable {
        this.findAndClick(By.linkText("Lisää lukuvinkki"));
        this.findAndClick(By.name("addPod"));
        
        WebElement element = this.find(By.id("podcastTitle"));
        element.sendKeys("");
        
        element = this.find(By.id("podcastAuthor"));
        element.sendKeys("");
        
        element = this.find(By.id("podcastUrl"));
        element.sendKeys("bbc.areena.be");
        
        element = this.find(By.id("podcastDescription"));
        element.sendKeys("äänekäs");
        
        element = this.find(By.name("submitPod"));        
        element.submit();
    }

    @Then("an error notification for missing link url is shown")
    public void an_error_notification_for_missing_link_url_is_shown() throws Throwable {
    	WebElement element = this.find(By.tagName("body"));
    	String msg = element.getText();
    	assertTrue(msg.contains("Linkille tulee syöttää url-osoite"));
	}

    @Then("an error notification for missing podcast title is shown")
    public void an_error_notification_for_missing_podcast_title_is_shown() throws Throwable {
    	WebElement element = this.find(By.tagName("body"));
        String msg = element.getText();
        assertTrue(msg.contains("Podcastille tulee syöttää otsikko"));
    }

    @Then("an error notification for missing podcast author is shown")
    public void an_error_notification_for_missing_podcast_author_is_shown() throws Throwable {
        WebElement element = this.find(By.tagName("body"));
        String msg = element.getText();
        assertTrue(msg.contains("Podcastille tulee syöttää tekijä"));
    }

    @When("a new youtube is created")
    public void a_new_youtube_is_created() throws Throwable {
        this.findAndClick(By.linkText("Lisää lukuvinkki"));
        this.findAndClick(By.name("addTube"));
        
        WebElement element = this.find(By.id("youtubeTitle"));
        element.sendKeys("Test Youtube");
        
        element = this.find(By.id("youtubeAuthor"));
        element.sendKeys("Kaunis Kasvo");
        
        element = this.find(By.id("youtubeUrl"));
        element.sendKeys("you.tu.be");
        
        element = this.find(By.id("youtubeDescription"));
        element.sendKeys("näyttävä");
        
        element = this.find(By.name("submitYoutube"));        
        element.submit();
    }

    @Then("a new youtube is shown")
    public void a_new_youtube_is_shown() throws Throwable {
        assertTrue(this.find(By.tagName("body"))
                .getText().contains("Test Youtube"));
        assertTrue(this.find(By.tagName("body"))
                .getText().contains("Kaunis Kasvo"));
    }

    @When("a faulty youtube is created")
    public void a_faulty_youtube_is_created() throws Throwable {
        this.findAndClick(By.linkText("Lisää lukuvinkki"));
        this.findAndClick(By.name("addTube"));
        
        WebElement element = this.find(By.id("youtubeTitle"));
        element.sendKeys("");
        
        element = this.find(By.id("youtubeAuthor"));
        element.sendKeys("");
        
        element = this.find(By.id("youtubeUrl"));
        element.sendKeys("");
        
        element = this.find(By.id("youtubeDescription"));
        element.sendKeys("");
        
        element = this.find(By.name("submitYoutube"));        
        element.submit();
    }

    @Then("an error notification for missing youtube title is shown")
    public void an_error_notification_for_missing_youtube_title_is_shown() throws Throwable {
        WebElement element = this.find(By.tagName("body"));
        String msg = element.getText();
        assertTrue(msg.contains("Youtube-videolle tulee syöttää otsikko"));
   }

    @Then("an error notification for missing youtube author is shown")
    public void an_error_notification_for_missing_youtube_author_is_shown() throws Throwable {
        WebElement element = this.find(By.tagName("body"));
        String msg = element.getText();
        assertTrue(msg.contains("Youtube-videolle tulee syöttää tekijä"));
    }
    
    @Then("an error notification for missing youtube url is shown")
    public void an_error_notification_for_missing_youtube_url_is_shown() throws Throwable {
        WebElement element = this.find(By.tagName("body"));
        String msg = element.getText();
        assertTrue(msg.contains("Youtube-videolle tulee syöttää url-osoite"));
    }
    
    @Then("an error notification for missing youtube description is shown")
    public void an_error_notification_for_missing_youtube_description_is_shown() throws Throwable {
        WebElement element = this.find(By.tagName("body"));
        String msg = element.getText();
        assertTrue(msg.contains("Youtube-videolle tulee syöttää lyhyt kuvaus"));
    }        

    @When("new book is filtered out")
    public void new_book_is_filtered_out() throws Throwable {
        WebElement element = this.find(By.id("filterInput1"));
        element.sendKeys("And");
    }
    
    @Then("new book is not shown")
    public void new_book_is_not_shown() throws Throwable {
        WebElement element = this.find(By.tagName("body"));
        String filteredList = element.getText();
        assertTrue(!filteredList.contains("Test Book"));
    }

    @When("the book is clicked")
    public void the_book_is_clicked() throws Throwable {
        
        this.findAndClick(By.linkText("Lukuvinkkisovellus"));
        driver.findElement(By.linkText("Test Book, kirjoittanut Nimekas Kirjailija")).click();
    }
    
    @When("muokkaa tietoja is clicked")
    public void muokkaa_tietoja_is_clicked() throws Throwable {

        this.findAndClick(By.linkText("Muokkaa tietoja"));
        Thread.sleep(500);
    }

    @When("tag is added")
    public void tag_is_added() throws Throwable {
       
        WebElement element = this.find(By.xpath("//input[@placeholder='Tagi']"));
        element.sendKeys("TestTag");

        element = this.find(By.name("submitBook"));        
        element.submit();
    }

    @Then("edits are shown")
    public void edits_are_shown() throws Throwable {
        WebElement element = this.find(By.tagName("body"));
        String tags = element.getText();
        assertTrue(tags.contains("TestTag"));
    }

    @When("youtube type is filtered")
    public void youtube_type_is_filtered() throws Throwable {

        List<WebElement> resultElementList = driver.findElements(By.xpath("//select[@id='picker1']/option"));

        for(int i=0;i<resultElementList.size();i++)
        {
            WebElement resultElement = resultElementList.get(i);
            String value = resultElement.getText();
            if (value.contains("Tyyppi")) {
                resultElement.click();
                break;
            }
        }
                
        WebElement element = driver.findElement(By.id("filterInput1"));
        element.sendKeys("Y");        
    }
    
    @When("book type and tag are filtered")
    public void book_type_and_tag_filtered() throws Throwable {

        // 1st filter type
        List<WebElement> resultElementList = driver.findElements(By.xpath("//select[@id='picker1']/option"));

        for(int i=0;i<resultElementList.size();i++)
        {
            WebElement resultElement = resultElementList.get(i);
            String value = resultElement.getText();
            if (value.contains("Tyyppi")) {
                resultElement.click();
                break;
            }
        }
        
        // 1st filter text
        WebElement element = driver.findElement(By.id("filterInput1"));
        element.sendKeys("Ki");        
        
        // AND condition
        resultElementList = driver.findElements(By.xpath("//select[@id='andor']/option"));

        for(int i=0;i<resultElementList.size();i++)
        {
            WebElement resultElement = resultElementList.get(i);
            String value = resultElement.getText();
            if (value.contains("AND")) {
                resultElement.click();
                break;
            }
        }
        
        // 2nd filter type
        resultElementList = driver.findElements(By.xpath("//select[@id='picker2']/option"));

        for(int i=0;i<resultElementList.size();i++)
        {
            WebElement resultElement = resultElementList.get(i);
            String value = resultElement.getText();
            if (value.contains("Tagi")) {
                resultElement.click();
                break;
            }
        }
        
        // 2nd filter text
        element = driver.findElement(By.id("filterInput2"));
        element.sendKeys("Jotain");        
        
    }
    
    private void findAndClick(By by) {
    	this.find(by).click();
    }
    private WebElement find(By by) {
    	return this.waiter.until(new Function<WebDriver, WebElement>() {
    	    public WebElement apply(WebDriver driver) {
    	        return driver.findElement(by);
    	    }
    	});
    }
}
