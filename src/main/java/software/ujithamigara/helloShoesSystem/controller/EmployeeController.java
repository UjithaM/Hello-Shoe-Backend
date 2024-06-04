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
import software.ujithamigara.helloShoesSystem.dto.EmployeeDTO;
import software.ujithamigara.helloShoesSystem.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    @GetMapping("/health")
    public String healthTest() {
        logger.info("Health test endpoint called");
        return "Employee Health Test";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveEmployee(@Validated @RequestBody EmployeeDTO employeeDTO, BindingResult bindingResult) {
        logger.info("Saving employee details");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            EmployeeDTO savedEmployee = employeeService.saveEmployee(employeeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
        } catch (Exception exception) {
            logger.error("Error saving employee: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Employee saved Unsuccessfully.\nMore Details\n" + exception);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        logger.info("Fetching all employees");
        try {
            List<EmployeeDTO> employeesList = employeeService.getAllEmployee();
            return ResponseEntity.ok(employeesList);
        } catch (Exception exception) {
            logger.error("Error fetching all employees: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch employees.\nMore Details\n" + exception);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String id) {
        logger.info("Fetching employee with ID: {}", id);
        try {
            EmployeeDTO employee = employeeService.getSelectedEmployee(id);
            return ResponseEntity.ok(employee);
        } catch (Exception exception) {
            logger.error("Error fetching employee by ID: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch employee.\nMore Details\n" + exception);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateEmployee(@PathVariable String id, @Validated @RequestBody EmployeeDTO employeeDTO, BindingResult bindingResult) {
        logger.info("Updating employee with ID: {}", id);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            employeeService.updateEmployee(id, employeeDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            logger.error("Error updating employee: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Employee update unsuccessful.\nMore Details\n" + exception);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id) {
        logger.info("Deleting employee with ID: {}", id);
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            logger.error("Error deleting employee: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to delete employee.\nMore Details\n" + exception);
        }
    }
}
