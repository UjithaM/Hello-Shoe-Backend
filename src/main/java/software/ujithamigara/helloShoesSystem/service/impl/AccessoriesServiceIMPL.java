package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.AccessoriesRepo;
import software.ujithamigara.helloShoesSystem.dto.AccessoriesDTO;
import software.ujithamigara.helloShoesSystem.entity.AccessoriesEntity;
import software.ujithamigara.helloShoesSystem.service.AccessoriesService;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class AccessoriesServiceIMPL implements AccessoriesService {
    private final AccessoriesRepo repo;
    private final Mapping mapping;
    @Override
    public AccessoriesDTO saveAccessories(AccessoriesDTO accessoriesDTO) {
        accessoriesDTO.setAccessoriesCode("ACC" + (repo.count() + 1));
        return mapping.toAccessoriesDTO(repo.save(mapping.toAccessoriesEntity(accessoriesDTO)));
    }

    @Override
    public void deleteAccessories(String accessoriesCode) {
        repo.delete(repo.getReferenceById(accessoriesCode));
    }

    @Override
    public AccessoriesDTO getSelectedAccessories(String accessoriesCode) {
        return mapping.toAccessoriesDTO(repo.getReferenceById(accessoriesCode));
    }

    @Override
    public List<AccessoriesDTO> getAllAccessories() {
        return mapping.toAccessoriesDTOList(repo.findAll());
    }

    @Override
    public void updateAccessories(String accessoriesCode, AccessoriesDTO accessoriesDTO) {
        AccessoriesEntity accessories = repo.getReferenceById(accessoriesCode);
        accessories.setAccessoriesDescription(accessoriesDTO.getAccessoriesDescription());
        accessories.setAccessoriesPicture(accessoriesDTO.getAccessoriesPicture());
        accessories.setUnitPriceSell(accessoriesDTO.getUnitPriceSell());
        accessories.setUnitPriceBuy(accessoriesDTO.getUnitPriceBuy());
        accessories.setExpectedProfit(accessoriesDTO.getExpectedProfit());
        accessories.setProfitMargin(accessoriesDTO.getProfitMargin());
        accessories.setQuantity(accessoriesDTO.getQuantity());
        accessories.setAccessoriesVerities(accessoriesDTO.getAccessoriesVerities());
        accessories.setSupplierEntity(accessoriesDTO.getSupplierEntity());

        repo.save(accessories);

    }
}
