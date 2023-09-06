package mu.oth.entity;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Entity;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes(
        {
                @JsonSubTypes.Type(
                        name = "adult",
                        value = Adult.class
                ),
                @JsonSubTypes.Type(
                        name = "teen",
                        value = Teen.class
                ),
                @JsonSubTypes.Type(
                        name = "child",
                        value = Child.class
                ),
        }
)
public class Person {
    int numberOfPerson;

}
