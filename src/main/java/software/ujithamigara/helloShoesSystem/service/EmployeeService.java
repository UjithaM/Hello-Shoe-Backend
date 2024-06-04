package software.ujithamigara.helloShoesSystem.service;


import software.ujithamigara.helloShoesSystem.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    void deleteEmployee(String employeeId);
    EmployeeDTO getSelectedEmployee(String employeeId);
    List<EmployeeDTO> getAllEmployee();
    void updateEmployee(String employeeId,EmployeeDTO employeeDTO);
    EmployeeDTO getEmployeeByEmail(String email);
}
