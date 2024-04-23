package software.ujithamigara.helloShoesSystem.service;


import software.ujithamigara.helloShoesSystem.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {
    SupplierDTO saveSupplier(SupplierDTO supplierDTO);
    void deleteSupplier(String supplierCode);
    SupplierDTO getSelectedSupplier(String supplierCode);
    List<SupplierDTO> getAllSupplier();
    void updateSupplier(String supplierCode,SupplierDTO supplierDTO);
}
