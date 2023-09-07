package mu.oth.PriceService.service;

import lombok.RequiredArgsConstructor;
import mu.oth.PriceService.dto.BookingDto;
import mu.oth.PriceService.dto.HotelResponseDto;
import mu.oth.PriceService.dto.RateDto;
import mu.oth.PriceService.entity.RateEntity;
import mu.oth.PriceService.repository.RateRepository;
import mu.oth.PriceService.utils.Constant;
import mu.oth.PriceService.utils.DateUtils;
import mu.oth.PriceService.utils.RateException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RateService {

    private final RateRepository rateRepository;

    private final WebClient webClient;

    private final ModelMapper modelMapper;


    public String saveRate(RateDto rateDto) {
        System.out.println("i hit you");
        // check/get hotel based on hotel code and room code
        HotelResponseDto hotelResponseDto = webClient.get().uri("/inventory/{hotelCode}/{roomCategory}", rateDto.getHotelCode(), rateDto.getRoomCode())
                .retrieve()
                .bodyToMono(HotelResponseDto.class)
                .retryWhen(this.getRetryConfig())
                .doOnError(ex -> logAndNotifyException(ex, rateDto.getHotelCode(), rateDto.getRoomCode()))
                .block();

        //check date not already existed
        Period ratePeriod = Period.between(rateDto.getStartDate(), rateDto.getEndDate());
        List<RateEntity> rateEntities = rateRepository.findByHotelCodeAndRoomCode(rateDto.getHotelCode(), rateDto.getRoomCode());

//        Optional<RateEntity> rateEntity2 = rateEntities.stream().filter(rateEntity -> {
//            System.out.println(DateUtils.isDateRangeWithin(rateDto.getStartDate(), rateDto.getEndDate(),rateEntity.getStartDate(), rateEntity.getEndDate()));
//            if (DateUtils.isDateRangeWithin(rateDto.getStartDate(), rateDto.getEndDate(),rateEntity.getStartDate(), rateEntity.getEndDate())) {
//                throw new RateException("Unfortunately, we already has a rate offer at this period ");
//            } else {
//                return true;
//            }
//        }).findFirst();

        for (RateEntity rateEntity : rateEntities) {
            System.out.println(rateDto.getStartDate() + " " + rateDto.getEndDate() + " " + rateEntity.getStartDate() + " " + rateEntity.getEndDate());
            System.out.println(DateUtils.isDateRangeWithin(rateDto.getStartDate(), rateDto.getEndDate(), rateEntity.getStartDate(), rateEntity.getEndDate()));
            if (DateUtils.isDateRangeWithin(rateDto.getStartDate(), rateDto.getEndDate(), rateEntity.getStartDate(), rateEntity.getEndDate())) {
                throw new RateException("Unfortunately, we already has a rate offer at this period ");
            }
        }


        if (rateEntities.size() == 0) {
            saveRateEntity(rateDto, hotelResponseDto);
            return "Rate has been added successfully";
        }

        saveRateEntity(rateDto, hotelResponseDto);

        return "Rate has been added successfully";

    }



    public RateDto getRateByHotelAndRoomAndDate(BookingDto bookingDto) {
        List<RateEntity> rateEntities = rateRepository.findByHotelCodeAndRoomCode(bookingDto.getHotelCode(), bookingDto.getRoomCode());
        Optional<RateEntity> findRate = rateEntities.stream().filter(rateEntity -> DateUtils.isDateRangeWithin(bookingDto.getStartDate(), bookingDto.getEndDate(), rateEntity.getStartDate(), rateEntity.getEndDate())).findFirst();

        return findRate.isPresent() ? modelMapper.map(findRate.get(), RateDto.class) : new RateDto();
    }


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

        throw new RateException(errorMessage, ex);
    }

    private void saveRateEntity(RateDto rateDto, HotelResponseDto hotelResponseDto) {

        RateEntity toSaved = RateEntity.builder()
                .hotelCode(rateDto.getHotelCode())
                .hotelName(hotelResponseDto.getHotelName())
                .roomCode(rateDto.getRoomCode())
                .roomName(hotelResponseDto.getRooms().stream().findFirst().get().getRoomCategory())
                .currency(rateDto.getCurrency())
                .rateType(rateDto.getRateType())
                .adultPrice(rateDto.getAdultPrice())
                .childPrice(rateDto.getChildPrice())
                .teenPrice(rateDto.getTeenPrice())
                .startDate(rateDto.getStartDate())
                .endDate(rateDto.getEndDate())
                .build();

        rateRepository.save(toSaved);

    }
}
