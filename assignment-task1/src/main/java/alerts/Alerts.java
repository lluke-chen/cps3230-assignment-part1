package alerts;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
public class Alerts {
    public int createAlert(String heading, String description, String url, String imageUrl, String postedBy, String priceInCents) {
        String json = String.format("{\r\n    \"alertType\": 6,\r\n    \"heading\": \"%s\",\r\n    \"description\": \"%s\",\r\n    \"url\": \"%s\",\r\n    \"imageUrl\": \"%s\",\r\n    \"postedBy\": \"%s\",\r\n    \"priceInCents\": \"%s\"\r\n}", heading, description, url, imageUrl, postedBy, priceInCents);
        System.out.println(json);
        HttpResponse<String> response = Unirest.post("https://api.marketalertum.com/Alert")
                .header("Content-Type", "application/json")
                .body(json)
                .asString();
        System.out.println(response.getStatus());
        return response.getStatus();
    }

    public int deleteAlerts(String url) {
        HttpResponse response = Unirest.delete(url).asEmpty();
        System.out.println(response.getStatus());
        return response.getStatus();
    }
}
