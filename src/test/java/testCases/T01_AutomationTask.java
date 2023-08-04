package testCases;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.P01_Homepage;
import pages.Scrollable;
import util.JSONdata;

import java.io.IOException;

import static io.qameta.allure.SeverityLevel.BLOCKER;
import static util.PropertyFileReader.getProperty;
import static util.driver.DriverHolder.getDriver;

@Epic("Google Search")
@Feature("Keyword result")
public class T01_AutomationTask extends BaseTest {
    private P01_Homepage homepage;
    private Scrollable scrollable;


    @Test
    @Severity(BLOCKER)
    @Description("""
                Test Scenario:
                    1-  Navigate to https://www.ebay.com/
                    2-  Validate that you have landed on the main page.
                    3-  Search for "mazda mx-5".
                    4-  Validate the results obtained.
                    5-  Print/log the number of obtained search results.
                    6-  From the left hand side filter using "Transmission" -> "Manual"
            """)
    @Step
    @Owner("Moustafa Ismail")
    public void ValidateThatSearchResultsMatched() throws IOException, org.json.simple.parser.ParseException {

        // 2-  Validate that you have landed on the main page.
        Assert.assertTrue(getDriver().getCurrentUrl().equals(getProperty("application_url")));
        Assert.assertTrue(getDriver().getTitle().equals(JSONdata.getTestData("homepageTitle")));

        // 3-  Search for "mazda mx-5".
        homepage.searchByKeyword(JSONdata.getTestData("searchKeyword"));

        // 4-  Validate the results obtained.
        homepage.assertOnListOfItemsTitles(JSONdata.getTestData("searchKeyword"));

        // 5-  Print/log the number of obtained search results.
        homepage.printNumberOfObtainedSearchResults();

        // 6-  From the left hand side filter using "Transmission" -> "Manual"
        homepage.selectTransmissionByType(JSONdata.getTestData("transmissionType"));


    }

    @BeforeMethod
    public void setUp (){
        homepage = new P01_Homepage(getDriver());
        scrollable = new Scrollable(getDriver());
    }

}
