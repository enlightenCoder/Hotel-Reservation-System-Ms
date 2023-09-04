package mu.oth.InventoryService.InventoryExceptionController;

import mu.oth.InventoryService.utils.InventoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InventoryExceptionController {

    @ExceptionHandler(value = InventoryException.class)
    public ResponseEntity<Object> exception(InventoryException inventoryException) {
        return new ResponseEntity<>(inventoryException.getMessage(), HttpStatus.ALREADY_REPORTED);
    }
}
