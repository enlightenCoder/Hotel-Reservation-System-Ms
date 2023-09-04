package mu.oth.InventoryService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import mu.oth.InventoryService.utils.Status;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequestDto {

    private Integer hotelCode;
    private String roomCode;
    private Integer bookingLimit;
    private Status status;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;
}
