package software.ujithamigara.helloShoesSystem.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public String healthTest() {
        logger.info("Health test endpoint called");
        return "Order Health Test";
    }

    @GetMapping
    public ResponseEntity<?> getAllOrder() {
        logger.info("Fetching all orders");
        try {
            List<OrderDTO> orderList = orderService.getAllOrder();
            return ResponseEntity.ok(orderList);
        } catch (Exception exception) {
            logger.error("Error fetching all orders: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch orders.\nMore Details\n" + exception);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id) {
        logger.info("Fetching order with ID: {}", id);
        try {
            OrderDTO order = orderService.getSelectedOrder(id);
            return ResponseEntity.ok(order);
        } catch (Exception exception) {
            logger.error("Error fetching order by ID: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch order.\nMore Details\n" + exception);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOrder(@Validated @RequestBody OrderDTO orderDTO, BindingResult bindingResult) {
        logger.info("Saving order details");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            OrderDTO savedOrder = orderService.saveOrder(orderDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
        } catch (Exception exception) {
            logger.error("Error saving order: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Order saved unsuccessfully.\nMore Details\n" + exception);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable String id, @Validated @RequestBody OrderDTO orderDTO, BindingResult bindingResult) {
        logger.info("Updating order with ID: {}", id);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            orderService.updateOrder(id, orderDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            logger.error("Error updating order: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Order update unsuccessful.\nMore Details\n" + exception);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id) {
        logger.info("Deleting order with ID: {}", id);
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            logger.error("Error deleting order: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to delete order.\nMore Details\n" + exception);
        }
    }
}
