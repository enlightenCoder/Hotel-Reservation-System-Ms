package mu.oth.HotelService.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequestDto {

    private Integer hotelCode;

    private String hotelName;

    private AddressRequestDto addressRequestDto;

    private List<RoomRequestDto> rooms;

    private String hotelDescription;

}
