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
import software.ujithamigara.helloShoesSystem.dto.CustomerDTO;
import software.ujithamigara.helloShoesSystem.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @GetMapping("/health")
    public String healthTest() {
        logger.info("Health test endpoint called");
        return "Customer Health Test";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCustomer(@Validated @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {
        logger.info("Saving customer details");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            CustomerDTO savedCustomer = customerService.saveCustomer(customerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
        } catch (Exception exception) {
            logger.error("Error saving customer: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Customer saved Unsuccessfully.\nMore Details\n" + exception);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllCustomers() {
        logger.info("Fetching all customers");
        try {
            List<CustomerDTO> customersList = customerService.getAllCustomers();
            return ResponseEntity.ok(customersList);
        } catch (Exception exception) {
            logger.error("Error fetching all customers: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch customers.\nMore Details\n" + exception);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id) {
        logger.info("Fetching customer with ID: {}", id);
        try {
            CustomerDTO customer = customerService.getSelectedCustomer(id);
            return ResponseEntity.ok(customer);
        } catch (Exception exception) {
            logger.error("Error fetching customer by ID: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch customer.\nMore Details\n" + exception);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable String id, @Validated @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {
        logger.info("Updating customer with ID: {}", id);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            customerService.updateCustomer(id, customerDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            logger.error("Error updating customer: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Customer update unsuccessful.\nMore Details\n" + exception);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        logger.info("Deleting customer with ID: {}", id);
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            logger.error("Error deleting customer: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to delete customer.\nMore Details\n" + exception);
        }
    }
}
