package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.OrderRepo;
import software.ujithamigara.helloShoesSystem.dto.OrderDTO;
import software.ujithamigara.helloShoesSystem.service.OrderService;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceIMPL implements OrderService {
    public  final OrderRepo orderRepo;
    public final Mapping mapping;
    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        orderDTO.setOrderNo(UUID.randomUUID().toString());
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
