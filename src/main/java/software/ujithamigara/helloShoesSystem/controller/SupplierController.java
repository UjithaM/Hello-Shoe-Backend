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
import software.ujithamigara.helloShoesSystem.dto.SupplierDTO;
import software.ujithamigara.helloShoesSystem.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supplier")
@RequiredArgsConstructor
public class SupplierController {
    private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);

    private final SupplierService supplierService;

    @GetMapping("/health")
    public String healthTest() {
        logger.info("Health test endpoint called");
        return "Supplier Health Test";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveSupplier(@Validated @RequestBody SupplierDTO supplierDTO, BindingResult bindingResult) {
        logger.info("Saving supplier details");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            SupplierDTO savedSupplier = supplierService.saveSupplier(supplierDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSupplier);
        } catch (Exception exception) {
            logger.error("Error saving supplier: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Supplier saved Unsuccessfully.\nMore Details\n" + exception);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllSupplier() {
        logger.info("Fetching all suppliers");
        try {
            List<SupplierDTO> suppliersList = supplierService.getAllSupplier();
            return ResponseEntity.ok(suppliersList);
        } catch (Exception exception) {
            logger.error("Error fetching all suppliers: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch suppliers.\nMore Details\n" + exception);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable String id) {
        logger.info("Fetching supplier with ID: {}", id);
        try {
            SupplierDTO supplier = supplierService.getSelectedSupplier(id);
            return ResponseEntity.ok(supplier);
        } catch (Exception exception) {
            logger.error("Error fetching supplier by ID: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch supplier.\nMore Details\n" + exception);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable String id, @Validated @RequestBody SupplierDTO supplierDTO, BindingResult bindingResult) {
        logger.info("Updating supplier with ID: {}", id);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            supplierService.updateSupplier(id, supplierDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            logger.error("Error updating supplier: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Supplier update unsuccessful.\nMore Details\n" + exception);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable String id) {
        logger.info("Deleting supplier with ID: {}", id);
        try {
            supplierService.deleteSupplier(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            logger.error("Error deleting supplier: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to delete supplier.\nMore Details\n" + exception);
        }
    }
}
