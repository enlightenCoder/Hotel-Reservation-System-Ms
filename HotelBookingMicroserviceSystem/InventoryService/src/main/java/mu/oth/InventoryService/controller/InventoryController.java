package mu.oth.InventoryService.controller;

import lombok.RequiredArgsConstructor;
import mu.oth.InventoryService.dto.InventoryRequestDto;
import mu.oth.InventoryService.service.InventoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/save")
    public String saveInventory(@RequestBody InventoryRequestDto inventoryRequestDto) {
        return inventoryService.saveInventory(inventoryRequestDto);
    }
}
