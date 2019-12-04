/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.Properties;
import java.util.Random;

/**
 *
 * @author Sami
 */
public class Tester {

    public static void main(String[] args) {
    
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sami\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:8080");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("Lisää lukuvinkki"));
        element.click();
        
        element = driver.findElement(By.name("addBook"));
        element.click();
        sleep(2);
        
        element = driver.findElement(By.id("bookTitle"));
        element.sendKeys("");
        element = driver.findElement(By.id("bookAuthor"));
        element.sendKeys("Nimekas Kirjailija");
        element = driver.findElement(By.id("bookIsbn"));
        element.sendKeys("1234");
        sleep(2);
        
        element = driver.findElement(By.name("submitBook"));        
        element.submit();
        
        sleep(2);
        
        element = driver.findElement(By.name("fmBookTitle"));
        String tst = element.getText();
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
    
}
