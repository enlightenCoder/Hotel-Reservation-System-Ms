package mu.oth.InventoryService.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponseDto {

    private Integer hotelCode;

    private String hotelName;

    private String hotelPhoto;

    private AddressResponseDto addressResponseDto;

    private List<RoomResponseDto> rooms;

    private String hotelDescription;


}
