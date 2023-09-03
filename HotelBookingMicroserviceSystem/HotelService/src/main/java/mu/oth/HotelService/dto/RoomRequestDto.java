package mu.oth.HotelService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mu.oth.HotelService.utils.RoomCategory;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequestDto {

    private RoomCategory roomCategory;
    private Integer roomCount;
}
