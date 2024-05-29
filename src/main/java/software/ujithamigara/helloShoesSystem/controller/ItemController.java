package software.ujithamigara.helloShoesSystem.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import software.ujithamigara.helloShoesSystem.dto.ItemDTO;
import software.ujithamigara.helloShoesSystem.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;

    @GetMapping("/health")
    public String healthTest() {
        logger.info("Health test endpoint called");
        return "Item Health Test";
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllItems() {
        logger.info("Fetching all items");
        try {
            List<ItemDTO> itemsList = itemService.getAllItem();
            return ResponseEntity.ok(itemsList);
        } catch (Exception exception) {
            logger.error("Error fetching all items: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch items.\nMore Details\n" + exception);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveItem(@Validated @RequestBody ItemDTO itemDTO, BindingResult bindingResult) {
        logger.info("Saving item details");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            ItemDTO savedItem = itemService.saveItem(itemDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
        } catch (Exception exception) {
            logger.error("Error saving item: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Item saved unsuccessfully.\nMore Details\n" + exception);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable String id) {
        logger.info("Fetching item with ID: {}", id);
        try {
            ItemDTO item = itemService.getSelectedItem(id);
            return ResponseEntity.ok(item);
        } catch (Exception exception) {
            logger.error("Error fetching item by ID: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch item.\nMore Details\n" + exception);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable String id, @Validated @RequestBody ItemDTO itemDTO, BindingResult bindingResult) {
        logger.info("Updating item with ID: {}", id);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            itemService.updateItem(id, itemDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            logger.error("Error updating item: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Item update unsuccessful.\nMore Details\n" + exception);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable String id) {
        logger.info("Deleting item with ID: {}", id);
        try {
            itemService.deleteItem(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            logger.error("Error deleting item: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to delete item.\nMore Details\n" + exception);
        }
    }
}
