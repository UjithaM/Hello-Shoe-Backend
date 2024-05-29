package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.SupplierRepo;
import software.ujithamigara.helloShoesSystem.dto.SupplierDTO;
import software.ujithamigara.helloShoesSystem.exception.NotFoundException;
import software.ujithamigara.helloShoesSystem.service.SupplierService;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierServiceIMPL implements SupplierService {

    private static final Logger logger = LoggerFactory.getLogger(SupplierServiceIMPL.class);

    private final SupplierRepo repo;
    private final Mapping mapping;

    @Override
    public SupplierDTO saveSupplier(SupplierDTO supplierDTO) {
        long supplierCount = repo.count();

        String supplierCode = String.format("S%04d", supplierCount + 1);

        supplierDTO.setSupplierCode(supplierCode);
        return mapping.toSupplierDTO(repo.save(mapping.toSupplierEntity(supplierDTO)));
    }

    @Override
    public void deleteSupplier(String supplierCode) {
        if (repo.existsById(supplierCode)) {
            repo.delete(repo.findById(supplierCode).get());
            logger.info("Supplier deleted: {}", supplierCode);
        } else {
            logger.warn("Supplier not found: {}", supplierCode);
            throw new NotFoundException("Supplier not found with code: " + supplierCode);
        }
    }

    @Override
    public SupplierDTO getSelectedSupplier(String supplierCode) {
        return repo.findById(supplierCode)
                .map(mapping::toSupplierDTO)
                .orElseThrow(() -> {
                    logger.warn("Supplier not found: {}", supplierCode);
                    return new NotFoundException("Supplier not found with code: " + supplierCode);
                });
    }

    @Override
    public List<SupplierDTO> getAllSupplier() {
        return mapping.toSupplierDTOList(repo.findAll());
    }

    @Override
    public void updateSupplier(String supplierCode, SupplierDTO supplierDTO) {
        if (repo.existsById(supplierCode)) {
            repo.save(mapping.toSupplierEntity(supplierDTO));
            logger.info("Supplier updated: {}", supplierCode);
        } else {
            logger.warn("Supplier not found: {}", supplierCode);
            throw new NotFoundException("Supplier not found with code: " + supplierCode);
        }
    }
}
