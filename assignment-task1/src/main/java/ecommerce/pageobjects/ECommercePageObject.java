package ecommerce.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ECommercePageObject {
    WebDriver driver;

    public ECommercePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForKeyword() {
        String keyword = "headset";
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys(keyword);
        String xpathToSearchButton = "//button[@title = 'Search']";
        WebElement searchButton = driver.findElement(By.xpath(xpathToSearchButton));
        searchButton.click();
    }

    public String[] getProductLinks() {
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String xpathToProductLinks = "//a[@class = 'product-item-link']";
        List<WebElement> products = driver.findElements(By.xpath(xpathToProductLinks));

        if (products.size() == 0) {
            return null;
        }


        String[] productLinks = new String[products.size()];
        for (int i = 0; i < products.size(); i++) {
            productLinks[i] = products.get(i).getAttribute("href");
        }

        return productLinks;
    }

    public String getHeading() {
        String heading = driver.findElement(By.className("base")).getText();
        return heading;
    }

    public String getDescription() {
        String xpathToDescription = "//div[@class = 'value']";
        WebElement description = driver.findElement(By.xpath(xpathToDescription));
        return description.getText();
    }

    public String getImageUrl() {
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement imageUrl = driver.findElement(By.className("fotorama__img"));
        return imageUrl.getAttribute("src");
    }

    public String getPriceInCents() {
        String xpathToPrice = "//span[@class = 'price']";
        WebElement price = driver.findElement(By.xpath(xpathToPrice));

        String stringPrice = price.getText();
        stringPrice = stringPrice.replaceAll("[^\\x00-\\x7F]", "");
        double doublePrice = Double.parseDouble(stringPrice);
        double priceInCents = doublePrice * 100;
        int intPriceInCents = (int)priceInCents;
        String stringPriceInCents = Integer.toString(intPriceInCents);

        return stringPriceInCents;
    }
}
