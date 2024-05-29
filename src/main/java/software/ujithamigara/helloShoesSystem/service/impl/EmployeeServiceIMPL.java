package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.EmployeeRepo;
import software.ujithamigara.helloShoesSystem.dto.EmployeeDTO;
import software.ujithamigara.helloShoesSystem.entity.EmployeeEntity;
import software.ujithamigara.helloShoesSystem.exception.NotFoundException;
import software.ujithamigara.helloShoesSystem.service.EmployeeService;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceIMPL implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceIMPL.class);

    private final EmployeeRepo repo;
    private final Mapping mapping;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        long employeeCount = repo.count();
        String employeeCode = String.format("E%04d", employeeCount + 1);
        employeeDTO.setEmployeeCode(employeeCode);
        EmployeeDTO savedEmployeeDTO = mapping.toEmployeeDTO(repo.save(mapping.toEmployeeEntity(employeeDTO)));
        logger.info("Employee saved: {}", savedEmployeeDTO);
        return savedEmployeeDTO;
    }

    @Override
    public void deleteEmployee(String employeeId) {
        if (repo.existsById(employeeId)) {
            repo.deleteById(employeeId);
            logger.info("Employee deleted: {}", employeeId);
        } else {
            logger.warn("Employee not found: {}", employeeId);
            throw new NotFoundException("Employee not found with ID: " + employeeId);
        }
    }

    @Override
    public EmployeeDTO getSelectedEmployee(String employeeId) {
        EmployeeDTO employeeDTO = repo.findById(employeeId)
                .map(mapping::toEmployeeDTO)
                .orElseThrow(() -> {
                    logger.warn("Employee not found: {}", employeeId);
                    return new NotFoundException("Employee not found with ID: " + employeeId);
                });
        logger.info("Employee retrieved: {}", employeeDTO);
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<EmployeeDTO> employees = mapping.toEmployeeDTOList(repo.findAll());
        logger.info("All employees retrieved: {}", employees.size());
        return employees;
    }

    @Override
    public void updateEmployee(String employeeId, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = repo.findById(employeeId).orElseThrow(() -> {
            logger.warn("Employee not found: {}", employeeId);
            return new NotFoundException("Employee not found with ID: " + employeeDTO.getName());
        });
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
        logger.info("Employee updated: {}", employeeEntity);
    }
}
