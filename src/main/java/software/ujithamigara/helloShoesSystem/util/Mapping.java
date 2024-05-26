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
        return  mapper.map(itemEntity, ItemDTO.class);
    }
    public ItemEntity toItemEntity(ItemDTO itemDTO) {
        return  mapper.map(itemDTO, ItemEntity.class);
    }
    public List<ItemDTO> toItemDTOList(List<ItemEntity> itemEntities) {
        return itemEntities.stream()
                .map(itemEntity -> mapper.map(itemEntity, ItemDTO.class))
                .collect(Collectors.toList());
    }
    //OrderItemMapping
    public Order_itemDTO toOrderItemDTO(Order_item order_item) {
        return  mapper.map(order_item, Order_itemDTO.class);
    }
    public Order_item toOrderItem(Order_itemDTO order_itemDTO) {
        return  mapper.map(order_itemDTO, Order_item.class);
    }
    public List<Order_itemDTO> toOrderItemDTOList(List<Order_item> order_items) {
        return order_items.stream()
                .map(order_item -> mapper.map(order_item, Order_itemDTO.class))
                .collect(Collectors.toList());
    }
    //OrderMapping
    public OrderDTO toOrderDTO(OrderEntity orderEntity) {
        return  mapper.map(orderEntity, OrderDTO.class);
    }
    public OrderEntity toOrderEntity(OrderDTO orderDTO) {

        return  mapper.map(orderDTO, OrderEntity.class);
    }
    public OrderEntity toOrderEntity(OrderDTO orderDTO, List<Order_itemDTO> orderItemDTOS) {
        return mapper.map(orderDTO, OrderEntity.class);
    }
    public List<Order_item> toOrderItemList(List<Order_itemDTO> order_itemDTOS) {
        List<Order_item> order_items = new ArrayList<>();

        for (Order_itemDTO order_itemDTO : order_itemDTOS) {
            OrderEntity orderEntity = toOrderEntity(order_itemDTO.getOrderDTO());
            ItemEntity itemEntity = toItemEntity(order_itemDTO.getItemDTO());
            Order_item order_item = new Order_item();
            order_item.setOrders(orderEntity);
            order_item.setItem(itemEntity);
            order_item.setQuantity(order_itemDTO.getQuantity());
            order_items.add(order_item);
        }
        return order_items;
    }
    public List<OrderDTO> toOrderDTOList(List<OrderEntity> orderEntities) {
        return orderEntities.stream()
                .map(orderEntity -> mapper.map(orderEntity, OrderDTO.class))
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
                .map(accessoriesEntity -> mapper.map(accessoriesEntity, AccessoriesDTO.class))
                .collect(Collectors.toList());
    }
}
