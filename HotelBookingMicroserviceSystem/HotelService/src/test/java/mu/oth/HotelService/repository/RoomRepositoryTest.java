package mu.oth.HotelService.repository;

import mu.oth.HotelService.utils.RoomCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoomRepositoryTest {
    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void getTotalRoomsByRoomCategoryAndHotelCodeTest() {
        int actualResult = roomRepository.getTotalRoomsByRoomCategoryAndHotelCode(RoomCategory.SUJ, 220);
        assertEquals(5, actualResult);
    }
}