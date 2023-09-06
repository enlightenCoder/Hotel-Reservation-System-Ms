package mu.oth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import mu.oth.entity.Person;

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


    private String searchText;

    private String roomType;

    @JsonSubTypes.Type(JsonFormatTypes.class)
    @Transient
    private List<Person> persons = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
