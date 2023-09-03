package mu.oth.HotelService.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {

    private String street;

    private String city;

    private String zipCode;

    private String country;
}
