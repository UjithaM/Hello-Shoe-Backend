package software.ujithamigara.helloShoesSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.ujithamigara.helloShoesSystem.dto.OrderDTO;
import software.ujithamigara.helloShoesSystem.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    public final OrderService orderService;

    @GetMapping("/health")
    public String healthTest(){
        return "Order Health Test";
    }

    @GetMapping
    public List<OrderDTO> getAllOrder() {
        return orderService.getAllOrder();
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable String id) {
        return orderService.getSelectedOrder(id);
    }

    @PostMapping
    public OrderDTO saveOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable String id, @RequestBody OrderDTO orderDTO) {
        orderService.updateOrder(id, orderDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}
