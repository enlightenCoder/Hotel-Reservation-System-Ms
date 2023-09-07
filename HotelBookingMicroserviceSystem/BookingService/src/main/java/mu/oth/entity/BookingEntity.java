package mu.oth.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RATE_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@JsonSubTypes.Type(JsonFormatTypes.class)
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String searchText;

    @OneToMany
    private List<Room> room;

    @Column(name="START_DATE")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @Column(name="END_DATE")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;


}
