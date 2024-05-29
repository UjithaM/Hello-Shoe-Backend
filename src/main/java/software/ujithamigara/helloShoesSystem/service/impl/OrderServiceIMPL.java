package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.*;
import software.ujithamigara.helloShoesSystem.dto.OrderDTO;
import software.ujithamigara.helloShoesSystem.dto.Order_itemDTO;
import software.ujithamigara.helloShoesSystem.entity.CustomerEntity;
import software.ujithamigara.helloShoesSystem.entity.enums.Level;
import software.ujithamigara.helloShoesSystem.service.OrderService;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceIMPL implements OrderService {
    private  final OrderRepo orderRepo;
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
        CustomerEntity customer = customerRepo.findById(orderDTO.getCustomerCode()).get();

        if (orderDTO.getTotalPrice() > 800) customer.setTotalPoints(customer.getTotalPoints() + 1);

        if (customer.getTotalPoints() >= 200) customer.setLevel(Level.GOLD);
        else if (customer.getTotalPoints() >= 100) customer.setLevel(Level.SILVER);
        else if (customer.getTotalPoints() >= 50) customer.setLevel(Level.BRONZE);

        customer.setRecentPurchaseDate((Timestamp) orderDTO.getPurchaseDate());

        customerRepo.save(customer);
        return mapping.toOrderDTO(orderRepo.save(mapping.toOrderEntity(orderDTO)));
    }

    @Override
    public void deleteOrder(String orderCode) {
        orderRepo.deleteById(orderCode);
    }

    @Override
    public OrderDTO getSelectedOrder(String orderCode) {
        return mapping.toOrderDTO(orderRepo.findById(orderCode).get());
    }

    @Override
    public List<OrderDTO> getAllOrder() {
        return mapping.toOrderDTOList(orderRepo.findAll());
    }

    @Override
    public void updateOrder(String orderCode, OrderDTO orderDTO) {
        orderRepo.save(mapping.toOrderEntity(orderDTO));
    }
}
