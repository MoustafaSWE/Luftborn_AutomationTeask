package pages;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import util.JSONdata;

import javax.swing.*;
import java.io.IOException;
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

    public List<WebElement> listOfItemsTitle (){
        return driver.findElements(listOfItemsTitleLocator);
    }

    public List<WebElement> listOfCheckBoxTransmission (){
        return driver.findElements(listOfCheckBoxTransmissionLocator);
    }

    public void assertOnListOfItemsTitles (String itemTitle) throws IOException, ParseException {

        System.out.println("Search Result for Keyword: "+ JSONdata.getTestData("searchKeyword"));
        for (int i = 0; i < listOfItemsTitle().size() ;i++){
            System.out.println((i+1) + ") " +listOfItemsTitle().get(i).getText());

            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(listOfItemsTitle().get(i)));

            softAssert.assertTrue(listOfItemsTitle().get(i).getText().toLowerCase().contains(itemTitle.toLowerCase()));
            softAssert.assertAll();
        }
    }

    public void printNumberOfObtainedSearchResults (){
        System.out.println("Number of obtained Search Results: "+driver.findElements(listOfItemsTitleLocator).size());
    }

    public void selectTransmissionByType (String transmissionType){

        for (int i = 0;i < listOfCheckBoxTransmission().size();i++){
            if (listOfCheckBoxTransmission().get(i).getAttribute("aria-label").toLowerCase().contains(transmissionType)){
                System.out.println("Check box: "+ listOfCheckBoxTransmission().get(i).getAttribute("aria-label").toLowerCase()+" will be selected");
                listOfCheckBoxTransmission().get(i).click();
            }
        }
    }
}
