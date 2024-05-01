package software.ujithamigara.helloShoesSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import software.ujithamigara.helloShoesSystem.dto.SupplierDTO;
import software.ujithamigara.helloShoesSystem.service.SupplierService;

@RestController
@RequestMapping("/api/v1/supplier")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;
    @GetMapping("/health")
    public String healthTest(){
        return "Supplier Health Test";
    }

    @PostMapping
    public SupplierDTO saveSupplier(@RequestBody SupplierDTO supplierDTO) {
        return supplierService.saveSupplier(supplierDTO);
    }


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<SupplierDTO> getAllSupplier() {
        return supplierService.getAllSupplier();
    }

    @GetMapping("/{id}")
    public SupplierDTO getSupplierById(@PathVariable String id) {
        return supplierService.getSelectedSupplier(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable String id, @RequestBody SupplierDTO supplierDTO) {
        supplierService.updateSupplier(id, supplierDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable String id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok().build();
    }
}
