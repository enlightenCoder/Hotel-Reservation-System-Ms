package mu.oth.InventoryService.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private String hotelUrl;

    public WebClientConfig(@Value("${hotel.service.baseUrl}") String hotelUrl) {
        this.hotelUrl = hotelUrl;
    }

    @Bean
    public WebClient getWebClient() {
        return WebClient.builder()
                .baseUrl(hotelUrl + "/api/hotel")
                .build();
    }



}
