/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.Properties;
import java.util.Random;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author Sami
 */
public class Tester {

    public static void main(String[] args) {
    
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sami\\Downloads\\chromedriver_win32\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
        
        WebDriver driver = new HtmlUnitDriver(true);

        driver.get("http://localhost:8080");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.id("picker1"));      
        
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        
        sleep(2);
//        element.click();
        
        ////select[@id='picker1']/option[@value='tags-class'] : single option
        List<WebElement> elements = driver.findElements(By.xpath("//select[@id='picker1']/option"));
        
        for (WebElement elem : elements) {
            String str = elem.getAttribute("innerHTML");
            
            if (elem.getAttribute("innerHTML").contains("Tyyppi")) {
                elem.click();
            }
        }            
        
        element = driver.findElement(By.id("filterInput1"));
        element.sendKeys("Y");
        
        element = driver.findElement(By.tagName("body"));
        String filteredList = element.getText();
        boolean found = !filteredList.contains("Tie");

        
//        element = driver.findElement(By.name("addBook"));
//        element.click();
//        sleep(2);
//        
//        element = driver.findElement(By.id("bookTitle"));
//        element.sendKeys("");
//        element = driver.findElement(By.id("bookAuthor"));
//        element.sendKeys("Nimekas Kirjailija");
//        element = driver.findElement(By.id("bookIsbn"));
//        element.sendKeys("1234");
//        sleep(2);
//        
//        element = driver.findElement(By.name("submitBook"));        
//        element.submit();
//        
//        sleep(2);
//        
//        element = driver.findElement(By.name("fmBookTitle"));
//        String tst = element.getText();
        
        sleep(5);
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
    
}