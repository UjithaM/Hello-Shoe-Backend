package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.controller.AccessoriesController;
import software.ujithamigara.helloShoesSystem.dao.AccessoriesRepo;
import software.ujithamigara.helloShoesSystem.dao.SupplierRepo;
import software.ujithamigara.helloShoesSystem.dto.AccessoriesDTO;
import software.ujithamigara.helloShoesSystem.entity.AccessoriesEntity;
import software.ujithamigara.helloShoesSystem.entity.SupplierEntity;
import software.ujithamigara.helloShoesSystem.service.AccessoriesService;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class AccessoriesServiceIMPL implements AccessoriesService {
    private final AccessoriesRepo repo;
    private final Mapping mapping;
    private final SupplierRepo supplierRepo;
    @Override
    public AccessoriesDTO saveAccessories(AccessoriesDTO accessoriesDTO) {
        accessoriesDTO.setAccessoriesCode("ACC"+(int)(Math.random()*10000));
        AccessoriesEntity accessories = mapping.toAccessoriesEntity(accessoriesDTO);
        SupplierEntity supplier = supplierRepo.findById(accessoriesDTO.getSupplierCode()).get();
        accessories.setSupplierEntity(supplier);
        AccessoriesDTO accessoriesDTO1 = mapping.toAccessoriesDTO(repo.save(accessories));
        accessoriesDTO1.setSupplierName(supplier.getName());
        return accessoriesDTO1;
    }

    @Override
    public void deleteAccessories(String accessoriesCode) {
        repo.delete(repo.getReferenceById(accessoriesCode));
    }

    @Override
    public AccessoriesDTO getSelectedAccessories(String accessoriesCode) {
        AccessoriesEntity accessories = repo.getReferenceById(accessoriesCode);
        AccessoriesDTO accessoriesDTO = mapping.toAccessoriesDTO(accessories);
        accessoriesDTO.setSupplierCode(accessories.getSupplierEntity().getSupplierCode());
        accessoriesDTO.setSupplierName(accessories.getSupplierEntity().getName());
        return accessoriesDTO;
    }

    @Override
    public List<AccessoriesDTO> getAllAccessories() {
        return mapping.toAccessoriesDTOList(repo.findAll());
    }

    @Override
    public void updateAccessories(String accessoriesCode, AccessoriesDTO accessoriesDTO) {
        AccessoriesEntity accessories = repo.findById(accessoriesCode).get();
        accessories.setAccessoriesDescription(accessoriesDTO.getAccessoriesDescription());
        accessories.setAccessoriesPicture(accessoriesDTO.getAccessoriesPicture());
        accessories.setUnitPriceSell(accessoriesDTO.getUnitPriceSell());
        accessories.setUnitPriceBuy(accessoriesDTO.getUnitPriceBuy());
        accessories.setExpectedProfit(accessoriesDTO.getExpectedProfit());
        accessories.setProfitMargin(accessoriesDTO.getProfitMargin());
        accessories.setQuantity(accessoriesDTO.getQuantity());
        accessories.setAccessoriesVerities(accessoriesDTO.getAccessoriesVerities());
        accessories.setSupplierEntity(supplierRepo.findById(accessoriesDTO.getSupplierCode()).get());

        repo.save(accessories);

    }
}
