package test.ecommerce;

import alerts.Alerts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlertsTests {
    Alerts alerts;

    @BeforeEach
    public void setup() {
        alerts = new Alerts();
    }

    @Test
    public void testCreateAlertSuccessfullyWithCode201() {
        // Setup
        String heading = "TestHeading";
        String description = "TestDescription";
        String url = "https://www.scanmalta.com/shop/epos-sennheiser-gsp-301-wired-over-ear-noise-cancelling-gaming-headset-w-mic-for-pc-mac-console.html";
        String imageUrl = "https://www.scanmalta.com/shop/pub/media/catalog/product/cache/51cb816cf3b30ca1f94fc6cfcae49286/3/4/3450770-l-b.jpg";
        String postedBy = "d59bc878-de0f-459a-89dd-a5a4832067f6";
        String priceInCents = "7900";

        // Exercise
        int responseStatusCode = alerts.createAlert(heading, description, url, imageUrl, postedBy, priceInCents);

        // Verify
        Assertions.assertEquals(201, responseStatusCode);
    }

    @Test
    public void testCreateAlertNotSuccessfullyWithCode400() {
        // Setup
        String heading = "TestHeading";
        String description = "TestDescription";
        String url = "https://www.scanmalta.com/shop/epos-sennheiser-gsp-301-wired-over-ear-noise-cancelling-gaming-headset-w-mic-for-pc-mac-console.html";
        String imageUrl = "https://www.scanmalta.com/shop/pub/media/catalog/product/cache/51cb816cf3b30ca1f94fc6cfcae49286/3/4/3450770-l-b.jpg";
        String postedBy = "random";
        String priceInCents = "7900";

        // Exercise
        int responseStatusCode = alerts.createAlert(heading, description, url, imageUrl, postedBy, priceInCents);

        // Verify
        Assertions.assertEquals(400, responseStatusCode);
    }

    @Test
    public void testDeleteAlerts() {
        // Setup
        String url = "https://api.marketalertum.com/Alert?userId=d59bc878-de0f-459a-89dd-a5a4832067f6";

        // Exercise
        int responseStatusCode = alerts.deleteAlerts(url);

        // Verify
        Assertions.assertEquals(200, responseStatusCode);
    }
}
