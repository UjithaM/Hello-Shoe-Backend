package software.ujithamigara.helloShoesSystem.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.ujithamigara.helloShoesSystem.dto.OrderDTO;
import software.ujithamigara.helloShoesSystem.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    @GetMapping("/health")
    public String healthTest(){
        logger.info("Health test endpoint called");
        return "Order Health Test";
    }

    @GetMapping
    public List<OrderDTO> getAllOrder() {
        logger.info("Fetching all orders");
        return orderService.getAllOrder();
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable String id) {
        logger.info("Fetching order with ID: {}", id);
        return orderService.getSelectedOrder(id);
    }

    @PostMapping
    public OrderDTO saveOrder(@RequestBody OrderDTO orderDTO) {
        logger.info("Saving order: {}", orderDTO);
        return orderService.saveOrder(orderDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable String id, @RequestBody OrderDTO orderDTO) {
        logger.info("Updating order with ID: {}", id);
        orderService.updateOrder(id, orderDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable String id) {
        logger.info("Deleting order with ID: {}", id);
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}
