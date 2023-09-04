package mu.oth.PriceService.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private String hotelUrl;

    public WebClientConfig(@Value("${hotel.service.baseUrl}") String inventoryUrl) {
        this.hotelUrl = inventoryUrl;
    }

    @Bean
    public WebClient getWebClient() {
        return WebClient.builder()
                .baseUrl(hotelUrl + "/api/hotel")
                .build();
    }



}
