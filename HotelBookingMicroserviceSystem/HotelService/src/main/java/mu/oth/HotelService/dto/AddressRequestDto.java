package mu.oth.HotelService.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDto {

    private String street;

    private String city;

    private String zipCode;

    private String country;
}
