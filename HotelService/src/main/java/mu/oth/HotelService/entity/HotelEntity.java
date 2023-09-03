package mu.oth.HotelService.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "HOTEL_TB")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hotel_id")
    private Integer hotelId;

    @Column(name = "hotel_code", unique = true, nullable = false)
    private String hotelCode;

    @Column(name = "hotel_name", nullable = false)
    private String hotelName;

    @Column(name = "hotel_photo", nullable = false)
    private String hotelPhoto;

    @OneToOne
    private Address address;

    @OneToMany
    private List<RoomEntity> rooms;

    @Column(name = "hotel_description", nullable = false)
    private String hotelDescription;
}
