package mu.oth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import mu.oth.entity.Person;
import mu.oth.entity.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class BookDto {


    private String hotelName;

    private String where;

    private RoomRequestDto room;

    @JsonSubTypes.Type(JsonFormatTypes.class)
    @Transient
    private List<Person> persons = new ArrayList<>();

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate endDate;
}
