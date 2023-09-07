package mu.oth.controller;


import lombok.RequiredArgsConstructor;
import mu.oth.dto.BookDto;
import mu.oth.dto.BookResponseDto;
import mu.oth.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;


    @PostMapping("/book")
    public List<BookResponseDto> bookHotel(@RequestBody  BookDto bookDto) {
        return bookingService.bookAHotel(bookDto);
    }


}
