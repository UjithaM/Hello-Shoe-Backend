package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.EmployeeRepo;
import software.ujithamigara.helloShoesSystem.dto.EmployeeDTO;
import software.ujithamigara.helloShoesSystem.entity.CustomerEntity;
import software.ujithamigara.helloShoesSystem.entity.EmployeeEntity;
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
        long employeeCount = repo.count();
        String employeeCode = String.format("E%04d", employeeCount + 1);
        employeeDTO.setEmployeeCode(employeeCode);
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
        EmployeeEntity employeeEntity = repo.findById(employeeId).get();
        employeeEntity.setName(employeeDTO.getName());
        employeeEntity.setProfilePicture(employeeDTO.getProfilePicture());
        employeeEntity.setGender(employeeDTO.getGender());
        employeeEntity.setCivilStatus(employeeDTO.getCivilStatus());
        employeeEntity.setDesignation(employeeDTO.getDesignation());
        employeeEntity.setRole(employeeDTO.getRole());
        employeeEntity.setDob(employeeDTO.getDob());
        employeeEntity.setJoinedDate(employeeDTO.getJoinedDate());
        employeeEntity.setAttachedBranch(employeeDTO.getAttachedBranch());
        employeeEntity.setAddressNo(employeeDTO.getAddressNo());
        employeeEntity.setLane(employeeDTO.getLane());
        employeeEntity.setMainCity(employeeDTO.getMainCity());
        employeeEntity.setMainState(employeeDTO.getMainState());
        employeeEntity.setPostalCode(employeeDTO.getPostalCode());
        employeeEntity.setContactNumber(employeeDTO.getContactNumber());
        employeeEntity.setEmail(employeeDTO.getEmail());
        employeeEntity.setGuardianName(employeeDTO.getGuardianName());
        employeeEntity.setGuardianContact(employeeDTO.getGuardianContact());
        repo.save(employeeEntity);
    }
}
