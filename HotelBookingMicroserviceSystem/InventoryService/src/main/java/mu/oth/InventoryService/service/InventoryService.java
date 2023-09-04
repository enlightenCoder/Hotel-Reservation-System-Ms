package mu.oth.InventoryService.service;

import lombok.RequiredArgsConstructor;
import mu.oth.InventoryService.dto.HotelResponseDto;
import mu.oth.InventoryService.dto.InventoryRequestDto;
import mu.oth.InventoryService.entity.InventoryEntity;
import mu.oth.InventoryService.repository.InventoryRepository;
import mu.oth.InventoryService.utils.Constant;
import mu.oth.InventoryService.utils.InventoryException;
import mu.oth.InventoryService.utils.Status;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    private final WebClient webClient;

    private Retry getRetryConfig() {
        return Retry.backoff(Constant.RETRY_MAX_ATTEMPTS, Duration.ofSeconds(Constant.RETRY_MIN_DELAY_SECONDS));
    }

    private void logAndNotifyException(Throwable ex, final int hotelCode, final String roomCategory) {
        String errorDetails = ex.getMessage();
        // due to retry, the root exception is reactor.core.Exceptions$RetryExhaustedException. Get cause exception message
        if (!(ex instanceof WebClientResponseException) && ex.getCause() != null) {
            errorDetails = ex.getCause().getMessage();
        }
        final String errorMessage = "Inventory call to Hotel Service failed: " + errorDetails;

        throw new InventoryException(errorMessage, ex);
    }

    public String saveInventory(InventoryRequestDto inventoryRequestDto) {
        AtomicInteger availableRooms = new AtomicInteger();
        // check/get hotel based on hotel code and room code
        HotelResponseDto hotelResponseDto = webClient.get().uri("/inventory/{hotelCode}/{roomCategory}", inventoryRequestDto.getHotelCode(), inventoryRequestDto.getRoomCode())
                .retrieve()
                .bodyToMono(HotelResponseDto.class)
                .retryWhen(this.getRetryConfig())
                .doOnError(ex -> logAndNotifyException(ex, inventoryRequestDto.getHotelCode(), inventoryRequestDto.getRoomCode()))
                .block();
        //get total of rooms of given room category in the hotel code
        Integer roomCount = webClient.get().uri("/{roomCategory}/{hotelCode}", inventoryRequestDto.getRoomCode(), inventoryRequestDto.getHotelCode())
                .retrieve()
                .bodyToMono(Integer.class)
                .retryWhen(this.getRetryConfig())
                .doOnError(ex -> logAndNotifyException(ex, inventoryRequestDto.getHotelCode(), inventoryRequestDto.getRoomCode()))
                .block();

        //get total of rooms of given room category of given hotel in inventory tb and given period
        List<InventoryEntity> inventoryEntities = inventoryRepository.findAllByHotelCodeEqualsAndRoomCodeEqualsIgnoreCase(inventoryRequestDto.getHotelCode(), inventoryRequestDto.getRoomCode());
        List<InventoryEntity> inventoryWithDateChecked = inventoryEntities.stream().filter(inventoryEntity -> {
            if ((inventoryRequestDto.getStartDate().isEqual(inventoryEntity.getStartDate()) || inventoryRequestDto.getStartDate().isAfter(inventoryEntity.getStartDate()))
                    && (inventoryRequestDto.getEndDate().isEqual(inventoryEntity.getEndDate()) || inventoryRequestDto.getEndDate().isBefore(inventoryEntity.getEndDate()))) {
                throw new InventoryException("Already have iventory at this date");
            } else {
                return true;
            }
        }).toList();

        inventoryWithDateChecked.stream().forEach(inventoryEntity -> availableRooms.addAndGet(inventoryEntity.getBookingLimit()));
        //check number of available room cat in the hotel by deducting the 2 figures and compare with needed
        if (roomCount != availableRooms.get() && (roomCount - availableRooms.get()) > inventoryRequestDto.getBookingLimit()) {
            InventoryEntity toSavedInventory = InventoryEntity.builder()
                    .bookingLimit(inventoryRequestDto.getBookingLimit())
                    .hotelName(hotelResponseDto.getHotelName())
                    .roomCode(inventoryRequestDto.getRoomCode())
                    .roomCategory(hotelResponseDto.getRooms().stream().filter(roomResponseDto -> roomResponseDto.getRoomCategory().equals(inventoryRequestDto.getRoomCode())).findAny().get().getRoomCategory())
                    .status(Status.OPEN)
                    .startDate(inventoryRequestDto.getStartDate())
                    .endDate(inventoryRequestDto.getEndDate())
                    .hotelCode(inventoryRequestDto.getHotelCode())
                    .build();
            inventoryRepository.save(toSavedInventory);
            return "Inventory saved successfully";
        } else {

            return "Unfortunately, the available rooms for hotel " + hotelResponseDto.getHotelName() + " with room category " + inventoryRequestDto.getRoomCode() + " is " + (roomCount - availableRooms.get());
        }
    }
}


