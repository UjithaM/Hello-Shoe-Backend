package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.SupplierRepo;
import software.ujithamigara.helloShoesSystem.dto.SupplierDTO;
import software.ujithamigara.helloShoesSystem.service.SupplierService;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierServiceIMPL implements SupplierService {

    private final SupplierRepo repo;
    private final Mapping mapping;

    @Override
    public SupplierDTO saveSupplier(SupplierDTO supplierDTO) {
        supplierDTO.setSupplierCode(UUID.randomUUID().toString());
        return mapping.toSupplierDTO(repo.save(mapping.toSupplierEntity(supplierDTO)));
    }

    @Override
    public void deleteSupplier(String supplierCode) {
        repo.delete(repo.findById(supplierCode).get());
    }

    @Override
    public SupplierDTO getSelectedSupplier(String supplierCode) {
        return mapping.toSupplierDTO(repo.findById(supplierCode).get());
    }

    @Override
    public List<SupplierDTO> getAllSupplier() {
        return mapping.toSupplierDTOList(repo.findAll());
    }

    @Override
    public void updateSupplier(String supplierCode, SupplierDTO supplierDTO) {
        repo.save(mapping.toSupplierEntity(supplierDTO));
    }
}
