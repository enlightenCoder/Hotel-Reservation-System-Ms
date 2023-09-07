package mu.oth.dto;


import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class BookResponseDto {

    private String hotelName;

    private String roomCategory;

    private int numberOfPerson;

    private double totalPrice;

}
