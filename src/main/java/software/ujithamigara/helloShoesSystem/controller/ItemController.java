package software.ujithamigara.helloShoesSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import software.ujithamigara.helloShoesSystem.dto.ItemDTO;
import software.ujithamigara.helloShoesSystem.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    @GetMapping("/health")
    public String healthTest(){
        return "Item Health Test";
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItem();
    }

    @PostMapping
    public ItemDTO saveItem(@RequestBody ItemDTO item) {
        return itemService.saveItem(item);
    }

    @GetMapping("/{id}")
    public ItemDTO getItemById(@PathVariable String id) {
        return itemService.getSelectedItem(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable String id, @RequestBody ItemDTO itemDTO) {
        itemService.updateItem(id, itemDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }
}
