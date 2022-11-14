import alerts.Alerts;
import ecommerce.pageobjects.ECommercePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        WebDriver driver;
        ECommercePageObject ecom;
        Alerts alerts;

        // Get 5 results from scraper and post 5 alerts
        System.setProperty("webdriver.chrome.driver", "C:/webtesting/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.scanmalta.com/shop/");
        ecom = new ECommercePageObject(driver);
        alerts = new Alerts();

        ecom.searchForKeyword();

        String postedBy = "d59bc878-de0f-459a-89dd-a5a4832067f6";
        String[] productLinks = ecom.getProductLinks();

        String[] fiveProductLinks = new String[5];
        for (int i = 0; i < 5; i++) {
            fiveProductLinks[i] = productLinks[i];
        }

        for (int i = 0; i < 5; i++) {
            String url = fiveProductLinks[i];
            driver.navigate().to(url);
            String priceInCents = ecom.getPriceInCents();
            String heading = ecom.getHeading();
            String description = ecom.getDescription();
            String imageUrl = ecom.getImageUrl();

            alerts.createAlert(heading, description, url, imageUrl, postedBy, priceInCents);
        }

        driver.quit();
    }
}