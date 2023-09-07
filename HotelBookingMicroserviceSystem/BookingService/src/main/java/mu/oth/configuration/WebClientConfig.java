package mu.oth.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private String hotelUrl;

    private String inventoryUrl;

    private String rateUrl;

    public WebClientConfig(@Value("${hotel.service.baseUrl}") String hotelUrl,
                           @Value("${inventory.service.baseUrl}") String inventoryUrl,
                           @Value("${rate.service.baseUrl}") String rateUrl
                           ) {
        this.hotelUrl = hotelUrl;
        this.inventoryUrl = inventoryUrl;
        this.rateUrl = rateUrl;
    }

    @Bean(name="hotelWebClient")
    public WebClient hotelWebClient() {
        return WebClient.builder()
                .baseUrl(hotelUrl + "/api/hotel")
                .build();
    }

    @Bean(name="inventoryWebClient")
    public WebClient inventoryWebClient() {
        return WebClient.builder()
                .baseUrl(inventoryUrl + "/api/inventory")
                .build();
    }

    @Bean(name="rateWebClient")
    public WebClient rateWebClient() {
        return WebClient.builder()
                .baseUrl(rateUrl + "/api/rate")
                .build();
    }



}
