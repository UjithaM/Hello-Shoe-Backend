package software.ujithamigara.helloShoesSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import software.ujithamigara.helloShoesSystem.dto.EmployeeDTO;
import software.ujithamigara.helloShoesSystem.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @GetMapping("/health")
    public String healthTest(){
        return "Employee Health Test";
    }

    @PostMapping
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable String id) {
        return employeeService.getSelectedEmployee(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable String id, @RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
