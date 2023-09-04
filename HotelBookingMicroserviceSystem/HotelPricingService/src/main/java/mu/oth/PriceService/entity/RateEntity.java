package mu.oth.PriceService.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mu.oth.PriceService.utils.Currency;
import mu.oth.PriceService.utils.RateType;

import java.time.LocalDate;

@Entity
@Table( name = "RATE_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="HOTEL_CODE")
    private Integer hotelCode;

    @Column(name="HOTEL_NAME")
    private String hotelName;

    @Column(name="ROOM_CODE")
    private String roomCode;

    @Column(name="ROOM_NAME")
    private String roomName;

    @Column(name="CURRENCY")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name="RATE_TYPE")
    @Enumerated(EnumType.STRING)
    private RateType rateType;

    @Column(name="ADULT_PRICE")
    private double adultPrice;

    @Column(name="CHILD_PRICE")
    private double childPrice;

    @Column(name="TEEN_PRICE")
    private double teenPrice;

    @Column(name="START_DATE")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @Column(name="END_DATE")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;


}
