package mu.oth.PriceService.repository;

import mu.oth.PriceService.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<RateEntity, Integer> {

    List<RateEntity> findByHotelCodeAndRoomCode(Integer hotelCode, String roomCode);


}
