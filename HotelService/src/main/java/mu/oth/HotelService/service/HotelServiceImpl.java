package mu.oth.HotelService.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import mu.oth.HotelService.entity.HotelEntity;
import mu.oth.HotelService.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HotelServiceImpl implements HotelServiceInterface {


    private final HotelRepository hotelRepository;

    @Override
    public HotelEntity saveHotel(HotelEntity hotelEntity) {
        return null;
    }

    @Override
    public List<HotelEntity> getAllHotels() {
        return null;
    }

    @Override
    public HotelEntity findHotelByHotelCode(String code) {
        return null;
    }

    @Override
    public List<HotelEntity> findHotelByContactDetailsCity(String city) {
        return null;
    }

    @Override
    public List<HotelEntity> findHotelByContactDetailsCountry(String country) {
        return null;
    }

    @Override
    public void deleteHotel(HotelEntity hotelEntity) {

    }

    public List<HotelEntity> getHotelBySearchTerms(String searchText) {

        return hotelRepository.findHotelByHotelNameLikeOrCityLikeOrStateLike("%"+searchText+"%", "%"+searchText+"%", "%"+searchText+"%");
    }
}
