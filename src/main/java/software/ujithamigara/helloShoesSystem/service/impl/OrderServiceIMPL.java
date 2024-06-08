package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.*;
import software.ujithamigara.helloShoesSystem.dto.OrderDTO;
import software.ujithamigara.helloShoesSystem.entity.*;
import software.ujithamigara.helloShoesSystem.entity.enums.Level;
import software.ujithamigara.helloShoesSystem.exception.NotFoundException;
import software.ujithamigara.helloShoesSystem.exception.QuantityExceededException;
import software.ujithamigara.helloShoesSystem.service.OrderService;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceIMPL implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceIMPL.class);

    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final ItemRepo itemRepo;
    private final AccessoriesRepo accessoriesRepo;
    private final EmployeeRepo employeeRepo;
    private final Mapping mapping;

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        long orderCode = orderRepo.count();
        String orderCodeString = String.format("O%04d", orderCode + 1);
        orderDTO.setOrderNo(orderCodeString);

        CustomerEntity customer = null;
        if (orderDTO.getCustomerCode() != null){
            customer = customerRepo.findById(orderDTO.getCustomerCode())
                    .orElseThrow(() -> new NotFoundException("Customer not found"));
            updateCustomerPointsAndLevel(customer, orderDTO.getTotalPrice());
            customer.setRecentPurchaseDate(new Timestamp(orderDTO.getPurchaseDate().getTime()));
            customerRepo.save(customer);
        }


        OrderEntity orderEntity = mapping.toOrderEntity(orderDTO);

        orderEntity.setCustomerEntity(customer);

        orderEntity.setEmployeeEntity(employeeRepo.findById(orderDTO.getEmployeeCode())
                .orElseThrow(() -> new NotFoundException("Employee not found")));

        orderEntity.setOrderItems(orderDTO.getOrderItems().stream().map(orderItemDTO -> {
            ItemEntity item = itemRepo.findById(orderItemDTO.getItemCode())
                    .orElseThrow(() -> new NotFoundException("Item not found"));
            int quantity = item.getQuantity() - orderItemDTO.getQuantity();
            if (quantity < 0) {
                logger.warn("Item out of stock: {}", item.getItemCode());
                throw new QuantityExceededException("Quantity for accessory " + item.getItemCode() + " cannot be less than zero.");
            }
            item.setQuantity(quantity);
            itemRepo.save(item);

            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setItem(item);
            orderItemEntity.setOrders(orderEntity);
            orderItemEntity.setQuantity(orderItemDTO.getQuantity());
            return orderItemEntity;
        }).toList());

        orderEntity.setOrderAccessories(orderDTO.getOrderAccessories().stream().map(orderAccessoriesDTO -> {
            AccessoriesEntity accessories = accessoriesRepo.findById(orderAccessoriesDTO.getAccessoriesCode())
                    .orElseThrow(() -> new NotFoundException("Accessories not found"));
            int quantity = accessories.getQuantity() - orderAccessoriesDTO.getQuantity();
            if (quantity < 0) {
                logger.warn("Accessories out of stock: {}", accessories.getAccessoriesCode());
                throw new QuantityExceededException("Quantity for accessory " + accessories.getAccessoriesCode() + " cannot be less than zero.");
            }
            accessories.setQuantity(quantity);
            accessoriesRepo.save(accessories);

            OrderAccessoriesEntity orderAccessoriesEntity = new OrderAccessoriesEntity();
            orderAccessoriesEntity.setAccessoriesEntity(accessories);
            orderAccessoriesEntity.setOrders(orderEntity);
            orderAccessoriesEntity.setQuantity(orderAccessoriesDTO.getQuantity());
            return orderAccessoriesEntity;
        }).toList());

        logger.info("Order placed successfully: {}", orderCodeString);
        return mapping.toOrderDTO(orderRepo.save(orderEntity));
    }

    private void updateCustomerPointsAndLevel(CustomerEntity customer, double totalPrice) {
        if (totalPrice > 800) {
            customer.setTotalPoints(customer.getTotalPoints() + 1);
        }
        if (customer.getTotalPoints() >= 200) {
            customer.setLevel(Level.GOLD);
        } else if (customer.getTotalPoints() >= 100) {
            customer.setLevel(Level.SILVER);
        } else if (customer.getTotalPoints() >= 50) {
            customer.setLevel(Level.BRONZE);
        }
    }


    @Override
    public OrderDTO getSelectedOrder(String orderCode) {
        return orderRepo.findById(orderCode)
                .map(mapping::toOrderDTO)
                .orElseThrow(() -> new NotFoundException("Order not found"));
    }

    @Override
    public List<OrderDTO> getAllOrder() {
        return mapping.toOrderDTOList(orderRepo.findAll());
    }

    @Override
    public void updateOrder(String orderCode, OrderDTO orderDTO) {
        if (orderRepo.existsById(orderCode)) {
            orderRepo.save(mapping.toOrderEntity(orderDTO));
            logger.info("Order updated: {}", orderCode);
        } else {
            logger.warn("Order not found: {}", orderCode);
            throw new NotFoundException("Order not found");
        }
    }
}
