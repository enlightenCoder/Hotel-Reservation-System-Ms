package mu.oth.PriceService.controller;

import lombok.RequiredArgsConstructor;
import mu.oth.PriceService.dto.RateDto;
import mu.oth.PriceService.service.RateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rate")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveRate(@RequestBody  RateDto rateDto) {
        return rateService.saveRate(rateDto);
    }

}
