package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.EmployeeRepo;
import software.ujithamigara.helloShoesSystem.dto.EmployeeDTO;
import software.ujithamigara.helloShoesSystem.service.EmployeeService;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceIMPL implements EmployeeService {
    private final EmployeeRepo repo;
    private final Mapping mapping;
    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.setEmployeeCode(UUID.randomUUID().toString());
        return mapping.toEmployeeDTO(repo.save(mapping.toEmployeeEntity(employeeDTO)));
    }

    @Override
    public void deleteEmployee(String employeeId) {
        repo.delete(repo.findById(employeeId).get());
    }

    @Override
    public EmployeeDTO getSelectedEmployee(String employeeId) {
        return mapping.toEmployeeDTO(repo.findById(employeeId).get());
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        return mapping.toEmployeeDTOList(repo.findAll());
    }

    @Override
    public void updateEmployee(String employeeId, EmployeeDTO employeeDTO) {
        repo.save(mapping.toEmployeeEntity(employeeDTO));
    }
}
