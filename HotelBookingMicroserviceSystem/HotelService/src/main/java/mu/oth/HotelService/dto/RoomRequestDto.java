package mu.oth.HotelService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import mu.oth.HotelService.utils.RoomCategory;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class RoomRequestDto {
    @Enumerated(EnumType.STRING)
    @JsonProperty("roomCategory")
    private RoomCategory roomCategory;
    private Integer roomCount;
}
