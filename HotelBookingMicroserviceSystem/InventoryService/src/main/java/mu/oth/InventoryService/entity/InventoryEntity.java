package mu.oth.InventoryService.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mu.oth.InventoryService.utils.Status;

import java.time.LocalDate;

@Entity
@Table(name = "INVENTORY_TB")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="HOTEL_CODE")
    private Integer hotelCode;
    @Column(name="HOTEL_NAME")
    private String hotelName;
    @Column(name="ROOM_CATEGORY")
    private String roomCategory;
    @Column(name="ROOM_CODE")
    private String roomCode;
    @Column(name="BOOKING_LIMIT")
    private Integer bookingLimit;
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "end_date", columnDefinition = "DATE")
    private LocalDate endDate;


}
