package mu.oth.PriceService.controller;

import mu.oth.PriceService.utils.RateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RateExceptionController {

    @ExceptionHandler(value = RateException.class)
    public ResponseEntity<Object> exception(RateException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.ALREADY_REPORTED);
    }
}
