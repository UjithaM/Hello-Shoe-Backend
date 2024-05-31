package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.AccessoriesRepo;
import software.ujithamigara.helloShoesSystem.dao.SupplierRepo;
import software.ujithamigara.helloShoesSystem.dto.AccessoriesDTO;
import software.ujithamigara.helloShoesSystem.entity.AccessoriesEntity;
import software.ujithamigara.helloShoesSystem.entity.SupplierEntity;
import software.ujithamigara.helloShoesSystem.exception.NotFoundException;
import software.ujithamigara.helloShoesSystem.service.AccessoriesService;
import software.ujithamigara.helloShoesSystem.util.AccessoryCodeGenerator;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccessoriesServiceIMPL implements AccessoriesService {

    private static final Logger logger = LoggerFactory.getLogger(AccessoriesServiceIMPL.class);

    private final AccessoriesRepo accessoriesRepo;
    private final SupplierRepo supplierRepo;
    private final Mapping mapping;

    @Override
    public AccessoriesDTO saveAccessories(AccessoriesDTO accessoriesDTO) {

        AccessoriesEntity accessories = mapping.toAccessoriesEntity(accessoriesDTO);
        accessories.setAccessoriesCode(AccessoryCodeGenerator.generateAccessoryCode(accessories.getAccessoriesVerities(), accessoriesRepo.count()));
        SupplierEntity supplier = supplierRepo.findById(accessoriesDTO.getSupplierCode())
                .orElseThrow(() -> new NotFoundException("Supplier not found"));

        accessories.setSupplierEntity(supplier);
        AccessoriesDTO savedAccessoriesDTO = mapping.toAccessoriesDTO(accessoriesRepo.save(accessories));
        savedAccessoriesDTO.setSupplierCode(supplier.getSupplierCode());

        logger.info("Accessory saved successfully: {}", savedAccessoriesDTO.getAccessoriesCode());
        return savedAccessoriesDTO;
    }

    @Override
    public void deleteAccessories(String accessoriesCode) {
        if (accessoriesRepo.existsById(accessoriesCode)) {
            accessoriesRepo.deleteById(accessoriesCode);
            logger.info("Accessory deleted: {}", accessoriesCode);
        } else {
            logger.warn("Accessory not found: {}", accessoriesCode);
            throw new NotFoundException("Accessory not found" + accessoriesCode);
        }
    }

    @Override
    public AccessoriesDTO getSelectedAccessories(String accessoriesCode) {
        AccessoriesEntity accessories = accessoriesRepo.findById(accessoriesCode)
                .orElseThrow(() -> new NotFoundException("Accessory not found"));

        AccessoriesDTO accessoriesDTO = mapping.toAccessoriesDTO(accessories);
        accessoriesDTO.setSupplierCode(accessories.getSupplierEntity().getSupplierCode());

        logger.info("Accessory retrieved: {}", accessoriesCode);
        return accessoriesDTO;
    }

    @Override
    public List<AccessoriesDTO> getAllAccessories() {
        List<AccessoriesEntity> accessoriesList = accessoriesRepo.findAll();
        logger.info("All accessories retrieved. Count: {}", accessoriesList.size());
        return mapping.toAccessoriesDTOList(accessoriesList);
    }

    @Override
    public void updateAccessories(String accessoriesCode, AccessoriesDTO accessoriesDTO) {
        AccessoriesEntity accessories = accessoriesRepo.findById(accessoriesCode)
                .orElseThrow(() -> new NotFoundException("Accessory not found" + accessoriesDTO.getAccessoriesDescription()));

        SupplierEntity supplier = supplierRepo.findById(accessoriesDTO.getSupplierCode())
                .orElseThrow(() -> new NotFoundException("Supplier not found" + accessoriesDTO.getSupplierCode()));

        accessories.setAccessoriesDescription(accessoriesDTO.getAccessoriesDescription());
        accessories.setAccessoriesPicture(accessoriesDTO.getAccessoriesPicture());
        accessories.setUnitPriceSell(accessoriesDTO.getUnitPriceSell());
        accessories.setUnitPriceBuy(accessoriesDTO.getUnitPriceBuy());
        accessories.setExpectedProfit(accessoriesDTO.getExpectedProfit());
        accessories.setProfitMargin(accessoriesDTO.getProfitMargin());
        accessories.setQuantity(accessoriesDTO.getQuantity());
        accessories.setAccessoriesVerities(accessoriesDTO.getAccessoriesVerities());
        accessories.setSupplierEntity(supplier);

        accessoriesRepo.save(accessories);

        logger.info("Accessory updated: {}", accessoriesCode);
    }
}
