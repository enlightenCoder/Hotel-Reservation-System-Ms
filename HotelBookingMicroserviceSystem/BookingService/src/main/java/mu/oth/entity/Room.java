package mu.oth.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Entity
@Table( name = "ROOM_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Jacksonized
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roomId;

    @Column(name = "room_count")
    private int roomCount;

    @Column(name = "room_cat")
    private String roomCat;
}
