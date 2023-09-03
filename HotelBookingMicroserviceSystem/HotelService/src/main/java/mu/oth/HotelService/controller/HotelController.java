package mu.oth.HotelService.controller;

import lombok.RequiredArgsConstructor;
import mu.oth.HotelService.dto.HotelRequestDto;
import mu.oth.HotelService.dto.HotelResponseDto;
import mu.oth.HotelService.service.HotelServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelServiceInterface hotelService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public HotelResponseDto saveHotel(@RequestPart("data") HotelRequestDto hotelRequestDto, @RequestPart("img") MultipartFile file) {
         return hotelService.saveHotel(hotelRequestDto, file);
    }



    @GetMapping("/getHotel/{searchText}")
    public List<HotelResponseDto> getHotel(@PathVariable String searchText) {
        return hotelService.getHotelBySearchTerms(searchText);
    }



}
