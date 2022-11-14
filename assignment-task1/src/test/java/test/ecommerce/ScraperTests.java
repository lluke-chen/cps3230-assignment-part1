package test.ecommerce;

import ecommerce.pageobjects.ECommercePageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScraperTests {
    ECommercePageObject ecom;
    WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/webtesting/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.scanmalta.com/shop/");
        ecom = new ECommercePageObject(driver);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testSearchForKeyword() {
        // Exercise
        ecom.searchForKeyword();
        String currentUrl = driver.getCurrentUrl();

        // Verify
        Assertions.assertEquals("https://www.scanmalta.com/shop/catalogsearch/result/?q=headset", currentUrl);
    }

    @Test
    public void testGetProductLinksFound() {
        // Setup
        ecom.searchForKeyword();

        // Exercise
        String[] productLinks = ecom.getProductLinks();

        // Verify
        Assertions.assertNotNull(productLinks);
    }

    @Test
    public void testGetHeadingFound() {
        // Setup
        ecom.searchForKeyword();
        String[] productLinks = ecom.getProductLinks();
        String firstProductLink = productLinks[0];
        driver.navigate().to(firstProductLink);

        // Exercise

        String heading = ecom.getHeading();

        // Verify
        Assertions.assertNotNull(heading);
    }

    @Test
    public void testGetDescriptionFound() {
        // Setup
        ecom.searchForKeyword();
        String[] productLinks = ecom.getProductLinks();
        String firstProductLink = productLinks[0];
        driver.navigate().to(firstProductLink);

        // Exercise
        String description = ecom.getDescription();

        // Verify
        Assertions.assertNotNull(description);
    }

    @Test
    public void testGetImageUrlFound() {
        // Setup
        ecom.searchForKeyword();
        String[] productLinks = ecom.getProductLinks();
        String firstProductLink = productLinks[0];
        driver.navigate().to(firstProductLink);

        // Exercise
        String imageUrl = ecom.getImageUrl();

        // Verify
        Assertions.assertNotNull(imageUrl);
    }

    @Test
    public void testGetPriceInCentsFound() {
        // Setup
        ecom.searchForKeyword();
        String[] productLinks = ecom.getProductLinks();
        String firstProductLink = productLinks[0];
        driver.navigate().to(firstProductLink);

        // Exercise
        String price = ecom.getPriceInCents();

        // Verify
        Assertions.assertNotNull(price);
    }
}
