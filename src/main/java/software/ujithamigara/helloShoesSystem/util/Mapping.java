package software.ujithamigara.helloShoesSystem.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.ujithamigara.helloShoesSystem.controller.CustomerController;
import software.ujithamigara.helloShoesSystem.dto.*;
import software.ujithamigara.helloShoesSystem.entity.*;

import java.util.ArrayList;
import java.util.List;

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

    //EmployeeMapping
    public EmployeeDTO toEmployeeDTO(EmployeeEntity employeeEntity) {
        return  mapper.map(employeeEntity, EmployeeDTO.class);
    }
    public EmployeeEntity toEmployeeEntity(EmployeeDTO employeeDTO) {
        return  mapper.map(employeeDTO, EmployeeEntity.class);
    }
    public List<EmployeeDTO> toEmployeeDTOList(List<EmployeeEntity> employeeEntities) {
        return mapper.map(employeeEntities, List.class);
    }
    //ItemMapping
    public ItemDTO toItemDTO(ItemEntity itemEntity) {
        return  mapper.map(itemEntity, ItemDTO.class);
    }
    public ItemEntity toItemEntity(ItemDTO itemDTO) {
        return  mapper.map(itemDTO, ItemEntity.class);
    }
    public List<ItemDTO> toItemDTOList(List<ItemEntity> itemEntities) {
        return mapper.map(itemEntities, List.class);
    }
    //OrderItemMapping
    public Order_itemDTO toOrderItemDTO(Order_item order_item) {
        return  mapper.map(order_item, Order_itemDTO.class);
    }
    public Order_item toOrderItem(Order_itemDTO order_itemDTO) {
        return  mapper.map(order_itemDTO, Order_item.class);
    }
    public List<Order_itemDTO> toOrderItemDTOList(List<Order_item> order_items) {
        return mapper.map(order_items, List.class);
    }
    //OrderMapping
    public OrderDTO toOrderDTO(OrderEntity orderEntity) {
        CustomerEntity customerEntity = orderEntity.getCustomerEntity();
        EmployeeEntity employeeEntity = orderEntity.getEmployeeEntity();
        RefundEntity refundEntity = orderEntity.getRefundEntity();
        OrderDTO orderDTO = mapper.map(orderEntity, OrderDTO.class);
        orderDTO.setCustomerDTO(toCustomerDTO(customerEntity));
        orderDTO.setEmployeeDTO(toEmployeeDTO(employeeEntity));
        if (refundEntity != null) orderDTO.setRefundDTO(toRefundDTO(refundEntity));
        return  orderDTO;
    }
    public OrderEntity toOrderEntity(OrderDTO orderDTO) {

        return  mapper.map(orderDTO, OrderEntity.class);
    }
    public OrderEntity toOrderEntity(OrderDTO orderDTO, List<Order_itemDTO> orderItemDTOS) {
        OrderEntity orderEntity = mapper.map(orderDTO, OrderEntity.class);
        orderEntity.setOrderItems(toOrderItemList(orderItemDTOS));
        CustomerEntity customerEntity = toCustomer(orderDTO.getCustomerDTO());
        EmployeeEntity employeeEntity = toEmployeeEntity(orderDTO.getEmployeeDTO());
        RefundEntity refundEntity = toRefundEntity(orderDTO.getRefundDTO());
        orderEntity.setCustomerEntity(customerEntity);
        orderEntity.setEmployeeEntity(employeeEntity);
        orderEntity.setRefundEntity(refundEntity);
        return  orderEntity;
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
        return mapper.map(orderEntities, List.class);
    }
    //RefundMapping
    public RefundDTO toRefundDTO(RefundEntity refundEntity) {
        return  mapper.map(refundEntity, RefundDTO.class);
    }
    public RefundEntity toRefundEntity(RefundDTO refundDTO) {
        return  mapper.map(refundDTO, RefundEntity.class);
    }
    public List<RefundDTO> toRefundDTOList(List<RefundEntity> refundEntities) {
        return mapper.map(refundEntities, List.class);
    }
}
