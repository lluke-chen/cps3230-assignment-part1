package test.marketalertum;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class MarketalertumSteps {
    WebDriver driver;
    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum() {
        System.setProperty("webdriver.chrome.driver", "C:/webtesting/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.marketalertum.com/");
    }

    @When("I login using valid credentials")
    public void iLoginUsingValidCredentials() {
        driver.navigate().to("https://www.marketalertum.com/Alerts/Login");
        driver.findElement(By.name("UserId")).sendKeys("d59bc878-de0f-459a-89dd-a5a4832067f6");
        String xpathToSubmit = "//input[@type = 'submit']";
        driver.findElement(By.xpath(xpathToSubmit)).click();
    }

    @Then("I should see my alerts")
    public void iShouldSeeMyAlerts() {
        Assertions.assertEquals("https://www.marketalertum.com/Alerts/List", driver.getCurrentUrl());
    }

    @When("I login using invalid credentials")
    public void iLoginUsingInvalidCredentials() {
        driver.navigate().to("https://www.marketalertum.com/Alerts/Login");
        driver.findElement(By.name("UserId")).sendKeys("bad credentials");
        String xpathToSubmit = "//input[@type = 'submit']";
        driver.findElement(By.xpath(xpathToSubmit)).click();
    }

    @Then("I should see the login screen again")
    public void iShouldSeeTheLoginScreenAgain() {
        Assertions.assertEquals("https://www.marketalertum.com/Alerts/Login", driver.getCurrentUrl());
        driver.quit();
    }

    @Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int arg0) {
        for (int i = 0; i < arg0; i++) {
            HttpResponse<String> response = Unirest.post("https://api.marketalertum.com/Alert")
                    .header("Content-Type", "application/json")
                    .body("{\r\n    \"alertType\": 6,\r\n    \"heading\": \"Jumper Windows 11 Laptop\",\r\n    \"description\": \"Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD\",\r\n    \"url\": \"https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth\",\r\n    \"imageUrl\": \"https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg\",\r\n    \"postedBy\": \"d59bc878-de0f-459a-89dd-a5a4832067f6\",\r\n    \"priceInCents\": \"24999\"\r\n}")
                    .asString();
        }
    }

    @When("I view a list of alerts")
    public void iViewAListOfAlerts() {
        driver.navigate().to("https://www.marketalertum.com/Alerts/Login");
        driver.findElement(By.name("UserId")).sendKeys("d59bc878-de0f-459a-89dd-a5a4832067f6");
        String xpathToSubmit = "//input[@type = 'submit']";
        driver.findElement(By.xpath(xpathToSubmit)).click();
        driver.navigate().to("https://www.marketalertum.com/Alerts/List");
    }

    @And("each alert should contain an icon")
    public void eachAlertShouldContainAnIcon() {
        String xpathToAlertsIcons = "//img[@width = '100']";
        List<WebElement> alertsListIcons = driver.findElements(By.xpath(xpathToAlertsIcons));
        for (int i = 0; i < 3; i++) {
            String icon = alertsListIcons.get(i).getAttribute("src");
            Assertions.assertEquals("https://www.marketalertum.com/images/icon-electronics.png", icon);
        }
    }

    @And("each alert should contain a heading")
    public void eachAlertShouldContainAHeading() {
        List<WebElement> alertsListHeadings = driver.findElements(By.tagName("h4"));
        for (int i = 0; i < 3; i++) {
            String heading = alertsListHeadings.get(i).getText();
            Assertions.assertNotNull(heading);
        }
    }

    @And("each alert should contain an image")
    public void eachAlertShouldContainAnImage() {
        String xpathToAlertsImages = "//img[@width = '200']";
        List<WebElement> alertsListImages = driver.findElements(By.xpath(xpathToAlertsImages));
        for (int i = 0; i < 3; i++) {
            String image = alertsListImages.get(i).getAttribute("src");
            Assertions.assertEquals("https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg", image);
        }
    }

    @And("each alert should contain a price")
    public void eachAlertShouldContainAPrice() {
        List<WebElement> alertsListPrices = driver.findElements(By.tagName("b"));
        for (int i = 0; i < 3; i++) {
            String price = alertsListPrices.get(i).getText();
            Assertions.assertNotNull(price);
        }
    }

    @And("each alert should contain a link to the original product website")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite() {
        String xpathToLinks = "//a[text()='Visit item']";
        List<WebElement> alertsListLinks = driver.findElements(By.xpath(xpathToLinks));
        for (int i = 0; i < 3; i++) {
            String link = alertsListLinks.get(i).getAttribute("href");
            Assertions.assertEquals("https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth", link);
        }
    }

    @Given("I am an administrator of the website and I upload more than {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts(int arg0) {
        for (int i = 0; i <= arg0; i++) {
            HttpResponse<String> response = Unirest.post("https://api.marketalertum.com/Alert")
                    .header("Content-Type", "application/json")
                    .body("{\r\n    \"alertType\": 6,\r\n    \"heading\": \"Jumper Windows 11 Laptop\",\r\n    \"description\": \"Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD\",\r\n    \"url\": \"https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth\",\r\n    \"imageUrl\": \"https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg\",\r\n    \"postedBy\": \"d59bc878-de0f-459a-89dd-a5a4832067f6\",\r\n    \"priceInCents\": \"24999\"\r\n}")
                    .asString();
        }
    }

    @Then("I should see {int} alerts")
    public void iShouldSeeAlerts(int arg0) {
        driver.navigate().to("https://www.marketalertum.com/Alerts/Login");
        driver.findElement(By.name("UserId")).sendKeys("d59bc878-de0f-459a-89dd-a5a4832067f6");
        String xpathToSubmit = "//input[@type = 'submit']";
        driver.findElement(By.xpath(xpathToSubmit)).click();

        List<WebElement> alertsList = driver.findElements(By.tagName("table"));

        Assertions.assertEquals(arg0, alertsList.size());
    }

    @Given("I am an administrator of the website and I upload an alert of type {string}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfType(String arg0) {
        HttpResponse responseDelete = Unirest.delete("https://api.marketalertum.com/Alert?userId=d59bc878-de0f-459a-89dd-a5a4832067f6").asEmpty();

        String json = String.format("{\r\n    \"alertType\": %s,\r\n    \"heading\": \"Jumper Windows 11 Laptop\",\r\n    \"description\": \"Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD\",\r\n    \"url\": \"https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth\",\r\n    \"imageUrl\": \"https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg\",\r\n    \"postedBy\": \"d59bc878-de0f-459a-89dd-a5a4832067f6\",\r\n    \"priceInCents\": \"24999\"\r\n}", arg0);
        HttpResponse<String> response = Unirest.post("https://api.marketalertum.com/Alert")
                .header("Content-Type", "application/json")
                .body(json)
                .asString();
    }

    @And("the icon displayed should be {string}")
    public void theIconDisplayedShouldBe(String arg0) {
        String xpathToAlertsIcon = "//img[@width = '100']";
        WebElement alertsIcon = driver.findElement(By.xpath(xpathToAlertsIcon));
        String iconLink = alertsIcon.getAttribute("src");
        System.out.println(iconLink);
        String[] iconSplit = iconLink.split("/");
        for (String icon : iconSplit) {
            System.out.println(icon);
        }
        String icon = iconSplit[4];
        Assertions.assertEquals(arg0, icon);
        driver.quit();
    }
}
