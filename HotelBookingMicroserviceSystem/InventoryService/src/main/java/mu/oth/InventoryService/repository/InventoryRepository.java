package mu.oth.InventoryService.repository;

import mu.oth.InventoryService.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Integer> {

    List<InventoryEntity> findAllByHotelCodeEqualsAndRoomCodeEqualsIgnoreCase(int hotelCode, String roomCode);

    List<InventoryEntity> findByHotelNameAndRoomCategory(String hotelName, String roomCategory);
}
