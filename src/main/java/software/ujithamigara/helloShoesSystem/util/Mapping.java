package software.ujithamigara.helloShoesSystem.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import software.ujithamigara.helloShoesSystem.dto.CustomerDTO;
import software.ujithamigara.helloShoesSystem.dto.SupplierDTO;
import software.ujithamigara.helloShoesSystem.dto.UserDTO;
import software.ujithamigara.helloShoesSystem.entity.CustomerEntity;
import software.ujithamigara.helloShoesSystem.entity.SupplierEntity;
import software.ujithamigara.helloShoesSystem.entity.UserEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Mapping {
    private final ModelMapper mapper;
    public CustomerDTO toCustomerDTO(CustomerEntity customer) {
        return  mapper.map(customer, CustomerDTO.class);
    }
    public CustomerEntity toCustomer(CustomerDTO customerDTO) {
        return  mapper.map(customerDTO, CustomerEntity.class);
    }
    public List<CustomerDTO> toCustomerDTOList(List<CustomerEntity> customers) {
        return mapper.map(customers, List.class);
    }
    //UserMapping
    public UserEntity toUserEntity(UserDTO userDTO) {
        return mapper.map(userDTO, UserEntity.class);
    }
    public void toUserDTO(UserEntity userEntity) {
        mapper.map(userEntity, UserDTO.class);
    }

    //SupplierMapping
    public SupplierDTO toSupplierDTO(SupplierEntity supplierEntity) {
        return  mapper.map(supplierEntity, SupplierDTO.class);
    }
    public SupplierEntity toSupplierEntity(SupplierDTO supplierDTO) {
        return  mapper.map(supplierDTO, SupplierEntity.class);
    }
    public List<SupplierDTO> toSupplierDTOList(List<SupplierEntity> supplierEntities) {
        return mapper.map(supplierEntities, List.class);
    }
}
