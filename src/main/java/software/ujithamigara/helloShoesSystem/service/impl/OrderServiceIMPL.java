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
    private final Mapping mapping;

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        long orderCode = orderRepo.count();
        String orderCodeString = String.format("O%04d", orderCode + 1);
        orderDTO.setOrderNo(orderCodeString);

        CustomerEntity customer = customerRepo.findById(orderDTO.getCustomerCode())
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        updateCustomerPointsAndLevel(customer, orderDTO.getTotalPrice());
        customer.setRecentPurchaseDate(Timestamp.valueOf(orderDTO.getPurchaseDate().toString()));
        customerRepo.save(customer);

        for (Order_item orderItem : orderDTO.getOrderItems()) {
            ItemEntity item = itemRepo.findById(orderItem.getItem().getItemCode())
                    .orElseThrow(() -> new NotFoundException("Item not found"));

            int newQuantity = item.getQuantity() - orderItem.getQuantity();
            if (newQuantity < 0) {
                throw new RuntimeException("Insufficient stock for item: " + item.getItemDescription());
            }
            item.setQuantity(newQuantity);
            itemRepo.save(item);
        }

        for (OrderAccessories orderAccessories : orderDTO.getOrderAccessories()) {
            AccessoriesEntity accessories = accessoriesRepo.findById(orderAccessories.getAccessoriesEntity().getAccessoriesCode())
                    .orElseThrow(() -> new NotFoundException("Accessories not found"));
            int newQuantity = accessories.getQuantity() - orderAccessories.getQuantity();
            if (newQuantity < 0) {
                throw new RuntimeException("Insufficient stock for accessories: " + accessories.getAccessoriesDescription());
            }
            accessories.setQuantity(newQuantity);
            accessoriesRepo.save(accessories);
        }

        logger.info("Order placed successfully: {}", orderCodeString);
        return mapping.toOrderDTO(orderRepo.save(mapping.toOrderEntity(orderDTO)));
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
    public void deleteOrder(String orderCode) {
        if (orderRepo.existsById(orderCode)) {
            orderRepo.deleteById(orderCode);
            logger.info("Order deleted: {}", orderCode);
        } else {
            logger.warn("Order not found: {}", orderCode);
            throw new NotFoundException("Order not found");
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
