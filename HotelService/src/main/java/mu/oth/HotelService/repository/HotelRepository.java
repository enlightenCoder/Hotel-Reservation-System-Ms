package mu.oth.HotelService.repository;

import mu.oth.HotelService.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Integer> {

    List<HotelEntity> findHotelEntitiesByAddressCity(String city);
    List<HotelEntity> findHotelEntitiesByAddressCountry(String country);
    List<HotelEntity> findHotelEntitiesByHotelNameLikeOrAddressCountryLikeOrAddressCityLike(String string, String string2, String string3);
}
