package mu.oth.HotelService.service;

import jakarta.persistence.EntityManager;
import mu.oth.HotelService.repository.RoomRepository;
import mu.oth.HotelService.utils.RoomCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class HotelServiceImplTest {

    @Autowired
    private RoomRepository roomRepository;
    @Test
    void getTotalRoomsByRoomCategoryAndHotelCode() {
        int actualResult = roomRepository.getTotalRoomsByRoomCategoryAndHotelCode(RoomCategory.SUJ, 220);
        Assertions.assertEquals(5, actualResult);
    }
}