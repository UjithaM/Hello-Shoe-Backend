package software.ujithamigara.helloShoesSystem.service;

import software.ujithamigara.helloShoesSystem.dto.AccessoriesDTO;

import java.util.List;

public interface AccessoriesService {
    AccessoriesDTO saveAccessories(AccessoriesDTO accessoriesDTO);
    void deleteAccessories(String accessoriesCode);
    AccessoriesDTO getSelectedAccessories(String accessoriesCode);
    List<AccessoriesDTO> getAllAccessories();
    void updateAccessories(String accessoriesCode,AccessoriesDTO accessoriesDTO);
}
