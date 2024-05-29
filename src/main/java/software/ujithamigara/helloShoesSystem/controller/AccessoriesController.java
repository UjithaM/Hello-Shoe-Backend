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
import software.ujithamigara.helloShoesSystem.dto.AccessoriesDTO;
import software.ujithamigara.helloShoesSystem.service.AccessoriesService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accessories")
@RequiredArgsConstructor
public class AccessoriesController {
    private static final Logger logger = LoggerFactory.getLogger(AccessoriesController.class);

    private final AccessoriesService accessoriesService;

    @GetMapping("/health")
    public String healthTest() {
        return "Accessories Health Test";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveAccessories(@Validated @RequestBody AccessoriesDTO accessoriesDTO, BindingResult bindingResult) {
        logger.info("Saving accessories details: {}", accessoriesDTO);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            AccessoriesDTO savedAccessories = accessoriesService.saveAccessories(accessoriesDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAccessories);
        } catch (Exception exception) {
            logger.error("Error saving accessories: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Accessories saved Unsuccessfully.\nMore Details\n" + exception);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllAccessories() {
        logger.info("Fetching all accessories");
        try {
            List<AccessoriesDTO> accessoriesList = accessoriesService.getAllAccessories();
            return ResponseEntity.ok(accessoriesList);
        } catch (Exception exception) {
            logger.error("Error fetching all accessories: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch accessories.\nMore Details\n" + exception);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccessoriesById(@PathVariable String id) {
        logger.info("Fetching accessories with ID: {}", id);
        try {
            AccessoriesDTO accessories = accessoriesService.getSelectedAccessories(id);
            return ResponseEntity.ok(accessories);
        } catch (Exception exception) {
            logger.error("Error fetching accessories by ID: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch accessories.\nMore Details\n" + exception);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccessories(@PathVariable String id, @Validated @RequestBody AccessoriesDTO accessoriesDTO, BindingResult bindingResult) {
        logger.info("Updating accessories with ID: {}", id);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            accessoriesService.updateAccessories(id, accessoriesDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            logger.error("Error updating accessories: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Accessories update unsuccessful.\nMore Details\n" + exception);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccessories(@PathVariable String id) {
        logger.info("Deleting accessories with ID: {}", id);
        try {
            accessoriesService.deleteAccessories(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            logger.error("Error deleting accessories: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to delete accessories.\nMore Details\n" + exception);
        }
    }
}
