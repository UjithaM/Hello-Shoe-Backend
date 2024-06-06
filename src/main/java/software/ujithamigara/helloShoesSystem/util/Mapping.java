package software.ujithamigara.helloShoesSystem.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import software.ujithamigara.helloShoesSystem.dto.*;
import software.ujithamigara.helloShoesSystem.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapping {
    private final ModelMapper mapper;
    Mapping(ModelMapper mapper) {
        this.mapper = mapper;
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
    public CustomerDTO toCustomerDTO(CustomerEntity customer) {
        return  mapper.map(customer, CustomerDTO.class);
    }
    public CustomerEntity toCustomer(CustomerDTO customerDTO) {
        return  mapper.map(customerDTO, CustomerEntity.class);
    }
    public List<CustomerDTO> toCustomerDTOList(List<CustomerEntity> customers) {
        return customers.stream()
                .map(customer -> mapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }
    //UserMapping
    public UserEntity toUserEntity(UserDTO userDTO) {
        return mapper.map(userDTO, UserEntity.class);
    }
    public void toUserDTO(UserEntity userEntity) {
        mapper.map(userEntity, UserDTO.class);
    }
    public List<UserDTO> toUserDTOList(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(userEntity -> mapper.map(userEntity, UserDTO.class))
                .collect(Collectors.toList());
    }

    //SupplierMapping
    public SupplierDTO toSupplierDTO(SupplierEntity supplierEntity) {
        return  mapper.map(supplierEntity, SupplierDTO.class);
    }
    public SupplierEntity toSupplierEntity(SupplierDTO supplierDTO) {
        return  mapper.map(supplierDTO, SupplierEntity.class);
    }
    public List<SupplierDTO> toSupplierDTOList(List<SupplierEntity> supplierEntities) {
        return supplierEntities.stream()
                .map(supplierEntity -> mapper.map(supplierEntity, SupplierDTO.class))
                .collect(Collectors.toList());
    }

    //EmployeeMapping
    public EmployeeDTO toEmployeeDTO(EmployeeEntity employeeEntity) {
        return  mapper.map(employeeEntity, EmployeeDTO.class);
    }
    public EmployeeEntity toEmployeeEntity(EmployeeDTO employeeDTO) {
        return  mapper.map(employeeDTO, EmployeeEntity.class);
    }
    public List<EmployeeDTO> toEmployeeDTOList(List<EmployeeEntity> employeeEntities) {
        return employeeEntities.stream()
                .map(employeeEntity -> mapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }
    //ItemMapping
    public ItemDTO toItemDTO(ItemEntity itemEntity) {
        ItemDTO itemDTO = mapper.map(itemEntity, ItemDTO.class);
        itemDTO.setSupplierCode(itemEntity.getSupplierEntity().getSupplierCode());
        return  itemDTO;
    }
    public ItemEntity toItemEntity(ItemDTO itemDTO) {
        return  mapper.map(itemDTO, ItemEntity.class);
    }
    public List<ItemDTO> toItemDTOList(List<ItemEntity> itemEntities) {
        return itemEntities.stream()
                .map(itemEntity -> {
                    ItemDTO itemDTO = mapper.map(itemEntity, ItemDTO.class);
                    if (itemEntity.getSupplierEntity() != null) {
                        itemDTO.setSupplierCode(itemEntity.getSupplierEntity().getSupplierCode());
                    }
                    return itemDTO;
                })
                .collect(Collectors.toList());
    }
    public OrderDTO toOrderDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = mapper.map(orderEntity, OrderDTO.class);
        orderDTO.setCustomerCode(orderEntity.getCustomerEntity().getCustomerCode());
        orderDTO.setEmployeeCode(orderEntity.getEmployeeEntity().getEmployeeCode());
        orderDTO.setOrderItems(new ArrayList<>());
        for (OrderItemEntity orderItemEntity : orderEntity.getOrderItems()) {
            OrderItemDTO order_itemDTO = new OrderItemDTO();
            order_itemDTO.setItemCode(orderItemEntity.getItem().getItemCode());
            order_itemDTO.setQuantity(orderItemEntity.getQuantity());
            orderDTO.getOrderItems().add(order_itemDTO);
        }
        orderDTO.setOrderAccessories(new ArrayList<>());
        for (OrderAccessoriesEntity orderAccessoriesEntity : orderEntity.getOrderAccessories()) {
            OrderAccessoriesDTO orderAccessoriesDTO = new OrderAccessoriesDTO();
            orderAccessoriesDTO.setAccessoriesCode(orderAccessoriesEntity.getAccessoriesEntity().getAccessoriesCode());
            orderAccessoriesDTO.setQuantity(orderAccessoriesEntity.getQuantity());
            orderDTO.getOrderAccessories().add(orderAccessoriesDTO);
        }
        return  orderDTO;
    }
    public OrderEntity toOrderEntity(OrderDTO orderDTO) {

        return  mapper.map(orderDTO, OrderEntity.class);
    }
    public List<OrderDTO> toOrderDTOList(List<OrderEntity> orderEntities) {
        return orderEntities.stream()
                .map(orderEntity -> {
                    OrderDTO orderDTO = mapper.map(orderEntity, OrderDTO.class);
                    if (orderEntity.getCustomerEntity() != null) {
                        orderDTO.setCustomerCode(orderEntity.getCustomerEntity().getCustomerCode());
                    }
                    if (orderEntity.getEmployeeEntity() != null) {
                        orderDTO.setEmployeeCode(orderEntity.getEmployeeEntity().getEmployeeCode());
                    }
                    return orderDTO;
                })
                .collect(Collectors.toList());
    }

    //RefundMapping
    public RefundDTO toRefundDTO(RefundEntity refundEntity) {
        return  mapper.map(refundEntity, RefundDTO.class);
    }
    public RefundEntity toRefundEntity(RefundDTO refundDTO) {
        return  mapper.map(refundDTO, RefundEntity.class);
    }
    public List<RefundDTO> toRefundDTOList(List<RefundEntity> refundEntities) {
        return refundEntities.stream()
                .map(refundEntity -> mapper.map(refundEntity, RefundDTO.class))
                .collect(Collectors.toList());
    }
    //AccessoriesMapping
    public AccessoriesDTO toAccessoriesDTO(AccessoriesEntity accessoriesEntity) {
        return  mapper.map(accessoriesEntity, AccessoriesDTO.class);
    }
    public AccessoriesEntity toAccessoriesEntity(AccessoriesDTO accessoriesDTO) {
        return  mapper.map(accessoriesDTO, AccessoriesEntity.class);
    }
    public List<AccessoriesDTO> toAccessoriesDTOList(List<AccessoriesEntity> accessoriesEntities) {
        return accessoriesEntities.stream()
                .map(accessoriesEntity -> {
                    AccessoriesDTO accessoriesDTO = mapper.map(accessoriesEntity, AccessoriesDTO.class);
                    if (accessoriesEntity.getSupplierEntity() != null) {
                        accessoriesDTO.setSupplierCode(accessoriesEntity.getSupplierEntity().getSupplierCode());
                    }
                    return accessoriesDTO;
                })
                .collect(Collectors.toList());
    }
}
