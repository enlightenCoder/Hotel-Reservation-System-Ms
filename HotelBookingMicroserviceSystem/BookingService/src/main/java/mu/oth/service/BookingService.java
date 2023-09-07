package mu.oth.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.oth.dto.*;
import mu.oth.entity.Adult;
import mu.oth.entity.Child;
import mu.oth.entity.Person;
import mu.oth.entity.Teen;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;


@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService {

    @Qualifier(value = "hotelWebClient")
    private final WebClient hotelWebClient;
    @Qualifier(value = "hotelWebClient")
    private final WebClient inventoryWebClient;
    @Qualifier(value = "rateWebClient")
    private final WebClient rateWebClient;
    private final ObjectMapper objectMapper;

    public List<BookResponseDto> bookAHotel(BookDto bookDto) {

        // find hotel with searching criteria
        Map<String, List<HotelResponseDto>> hotelResponse = hotelWebClient.get().uri(uriBuilder -> uriBuilder.path("/search")
                        .queryParam("hotelName", bookDto.getHotelName())
                        .queryParam("country", bookDto.getWhere())
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        var hotels = objectMapper.convertValue(hotelResponse.get("hotels"), new TypeReference<List<HotelResponseDto>>() {
        });
        boolean isInStock = false;
        List<InventoryDto> findInventory;
        //check inventory
        if (!hotels.isEmpty()) {
            hotels.forEach(hotelResponseDto -> log.info("Hotels Dto: {}", hotelResponseDto));
            findInventory = hotels.stream()
                    .map(hotelResponseDto -> mapToInventoryDto(hotelResponseDto, bookDto))
                    .filter(this::isRoomValid)
                    .toList();

            if (findInventory.size() > 0) {
                isInStock = true;
            }

        } else {
            System.out.println("");
            return new ArrayList<>();
        }

        //find rates
        AtomicReference<Double> totalPrice = new AtomicReference<>((double) 0);
        AtomicReference<Integer> totalNumberOfPersons = new AtomicReference<>((int) 0);
        if (isInStock) {

            List<BookResponseDto> bookResponseDtos = findInventory.stream().map(inventoryDto -> {
                RateDto rateDto = RateDto.builder()
                        .hotelCode(inventoryDto.getHotelCode())
                        .roomCode(inventoryDto.getRoom().getRoomType())
                        .startDate(inventoryDto.getStartDate())
                        .endDate(inventoryDto.getEndDate())
                        .build();

                RateResponseDto rateResponseDto = rateWebClient.post()
                        .uri("/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(rateDto)
                        .retrieve()
                        .bodyToMono(RateResponseDto.class)
                        .block();

                // calculate rate for each one
                //get number of adult

                List<Person> persons = bookDto.getPersons();
                for (Person p : persons) {
                    if (p instanceof Adult adult) {
                        int numOfAdults = adult.getNumberOfPerson();
                        totalNumberOfPersons.updateAndGet(i -> Integer.valueOf(i + numOfAdults));
                        totalPrice.updateAndGet(v -> Double.valueOf(v + rateResponseDto.getAdultPrice() * numOfAdults));
                    }
                    if (p instanceof Teen teen) {
                        int numOfTeens = teen.getNumberOfPerson();
                        totalNumberOfPersons.updateAndGet(i -> Integer.valueOf(i + numOfTeens));
                        totalPrice.updateAndGet(v -> Double.valueOf((double) (v + rateResponseDto.getTeenPrice() * numOfTeens)));
                    }
                    if (p instanceof Child child) {
                        int numOfChild = child.getNumberOfPerson();
                        totalNumberOfPersons.updateAndGet(i -> Integer.valueOf(i + numOfChild));
                        totalPrice.updateAndGet(v -> Double.valueOf((double) (v + rateResponseDto.getChildPrice() * numOfChild)));
                    }
                }
                return BookResponseDto.builder()
                        .hotelName(inventoryDto.getHotelName())
                        .roomCategory(inventoryDto.getRoom().getRoomType())
                        .numberOfPerson(totalNumberOfPersons.get())
                        .totalPrice(totalPrice.get())
                        .build();
            }).toList();

            return bookResponseDtos;
        }


        return new ArrayList<>();
    }

    private InventoryDto mapToInventoryDto(HotelResponseDto hotelResponseDto, BookDto bookDto) {
        return InventoryDto.builder()
                .hotelName(hotelResponseDto.getHotelName())
                .hotelCode(hotelResponseDto.getHotelCode())
                .room(bookDto.getRoom())
                .startDate(bookDto.getStartDate())
                .endDate(bookDto.getEndDate())
                .build();
    }

    private boolean isRoomValid(InventoryDto inventoryDto) {
        return inventoryWebClient.post()
                .uri("/stock")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(inventoryDto)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
