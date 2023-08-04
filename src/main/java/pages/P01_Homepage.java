package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class P01_Homepage extends BasePage{

    public P01_Homepage(WebDriver driver) {
        super(driver);
    }
    public SoftAssert softAssert = new SoftAssert();

    private final By searchLocator = By.id("gh-ac");
    private final By listOfItemsTitleLocator = By.cssSelector("ul[class='srp-results srp-list clearfix'] div[class='s-item__title'] ");
    private final By listOfCheckBoxTransmissionLocator = By.cssSelector("li[name='Transmission'] input");

    public void searchByKeyword (String keyword){
        driver.findElement(searchLocator).sendKeys(keyword);
        driver.findElement(searchLocator).sendKeys(Keys.ENTER);
    }

    public void assertOnListOfItemsTitles (String itemTitle){

        for (int i = 0; i < driver.findElements(listOfItemsTitleLocator).size()-1 ;i++){
            System.out.println(driver.findElements(listOfItemsTitleLocator).get(i).getText());
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElements(listOfItemsTitleLocator).get(i)));
            softAssert.assertTrue(driver.findElements(listOfItemsTitleLocator).get(i).getText().toLowerCase().contains(itemTitle.toLowerCase()));
            softAssert.assertAll();
        }
    }

    public void printNumberOfObtainedSearchResults (){
        System.out.println(driver.findElements(listOfItemsTitleLocator).size());
    }

    public void selectTransmissionByType (String transmissionType){

        for (int i = 0;i < driver.findElements(listOfCheckBoxTransmissionLocator).size();i++){
            if (driver.findElements(listOfCheckBoxTransmissionLocator).get(i).getAttribute("aria-label").toLowerCase().contains(transmissionType)){
                System.out.println("Check box: "+driver.findElements(listOfCheckBoxTransmissionLocator).get(i).getAttribute("aria-label").toLowerCase()+" will be selected");
                driver.findElements(listOfCheckBoxTransmissionLocator).get(i).click();
            }
        }
    }
}
