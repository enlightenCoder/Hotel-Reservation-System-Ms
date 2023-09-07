package mu.oth.InventoryService.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequestDto {

    private int roomCount;

    private String roomType;
}
