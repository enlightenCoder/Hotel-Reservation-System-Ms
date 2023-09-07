package mu.oth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }


    // We need to inject this filter here and apply it to our custom url defined in routelocator
//    @Autowired
//    public TokenRelayGatewayFilterFactory tokenFilter;
//
//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route( p -> p.path("/oth/hotel/**")
//                        // Add filters on this path
//                        .filters(f ->
//                                // this is the first filter that we apply on the request having the designated url i.e accounts endpoint, it will check the access token
//                                f.filters(tokenFilter.apply())
//                                        // second filter will filter the segment part and concat with uri
//                                        .rewritePath("/oth/hotel/(?<segment>.*)", "/${segment}")
//                                        // third filter will remove the cookie header part because the gatewat does not need such information like to keep session of user etc... the gateway need just to pass access token
//                                        .removeRequestHeader("Cookie")
//
//
//                        )
//                        .uri("lb://HOTEL-SERVICE"))
//
//
//                .route( p -> p.path( "/oth/inventory/**")
//                        .filters( f-> f.filters(tokenFilter.apply()).rewritePath("/oth/inventory/(?<segment>.*)", "/${segment}").addResponseHeader("X-Response-Time", LocalDate.now().toString()))
//                        .uri("lb://INVENTORY-SERVICE"))
//
//
//                .route( p -> p.path( "/oth/booking/**")
//                        .filters( f-> f.filters(tokenFilter.apply()).rewritePath("/oth/booking/(?<segment>.*)", "/${segment}").addResponseHeader("X-Response-Time", LocalDate.now().toString()))
//                        .uri("lb://BOOKING-SERVICE"))
//
//
//                .route( p -> p.path( "/oth/rate/**")
//                        .filters( f-> f.filters(tokenFilter.apply()).rewritePath("/oth/rate/(?<segment>.*)", "/${segment}").addResponseHeader("X-Response-Time", LocalDate.now().toString()))
//                        .uri("lb://RATING-SERVICE"))
//                .build();
//
//
//    }


}