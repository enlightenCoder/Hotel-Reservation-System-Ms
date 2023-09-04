package mu.oth.PriceService.repository;

import mu.oth.PriceService.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<RateEntity, Integer> {

//    RateEntity findByHotelCodeAndRoomCodeAndStartDateNotInAndEndDateIsAfter(Integer hotelCode,
//                                                                            String roomCode,
//                                                                            LocalDate startDate,
//                                                                            LocalDate endDate,
//                                                                            LocalDate endDate2
//    );

    List<RateEntity> findByHotelCodeAndRoomCode(Integer hotelCode, String roomCode);
}
