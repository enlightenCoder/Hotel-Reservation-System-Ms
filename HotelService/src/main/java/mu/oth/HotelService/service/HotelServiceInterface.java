package mu.oth.HotelService.service;

import mu.oth.HotelService.entity.HotelEntity;

import java.util.List;

public interface HotelServiceInterface {

    HotelEntity saveHotel(HotelEntity hotelEntity);

    List<HotelEntity> getAllHotels();

    HotelEntity findHotelByHotelCode(String code);

    List<HotelEntity> findHotelByContactDetailsCity(String city);

    List<HotelEntity> findHotelByContactDetailsCountry(String country);

    void deleteHotel(HotelEntity hotelEntity);

    public List<HotelEntity> getHotelBySearchTerms(String searchText);


}
