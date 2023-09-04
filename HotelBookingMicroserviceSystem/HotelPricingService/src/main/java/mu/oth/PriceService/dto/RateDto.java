package mu.oth.PriceService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import mu.oth.PriceService.utils.Currency;
import mu.oth.PriceService.utils.RateType;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RateDto {

    private Integer hotelCode;
    private String roomCode;
    private Currency currency;
    private RateType rateType;
    private double adultPrice;
    private double childPrice;
    private double teenPrice;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;
}
