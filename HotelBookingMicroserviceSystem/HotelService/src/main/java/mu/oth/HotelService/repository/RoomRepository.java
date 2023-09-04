package mu.oth.HotelService.repository;

import mu.oth.HotelService.entity.RoomEntity;
import mu.oth.HotelService.utils.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

    @Query(value = "select sum(r.room_count) from room_tb r\n" +
            "inner join hotel_tb h on h.hotel_id = r.hotel_id and h.hotel_code = :hotelCode and r.room_category = :#{#roomCategory.name()}", nativeQuery = true)
    Integer getTotalRoomsByRoomCategoryAndHotelCode(@Param("roomCategory") RoomCategory roomCategory, @Param("hotelCode") Integer hotelCode);

}
