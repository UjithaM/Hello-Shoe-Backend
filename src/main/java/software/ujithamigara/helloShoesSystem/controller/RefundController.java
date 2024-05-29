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
import software.ujithamigara.helloShoesSystem.dto.RefundDTO;
import software.ujithamigara.helloShoesSystem.service.RefundServices;

import java.util.List;

@RestController
@RequestMapping("/api/v1/refund")
@RequiredArgsConstructor
public class RefundController {
    private static final Logger logger = LoggerFactory.getLogger(RefundController.class);

    private final RefundServices refundService;

    @GetMapping("/health")
    public String healthTest(){
        logger.info("Health test endpoint called");
        return "Refund Health Test";
    }

    @GetMapping
    public ResponseEntity<?> getAllRefund() {
        logger.info("Fetching all refunds");
        try {
            List<RefundDTO> refundList = refundService.getAllRefund();
            return ResponseEntity.ok(refundList);
        } catch (Exception exception) {
            logger.error("Error fetching all refunds: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch refunds.\nMore Details\n" + exception);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRefundById(@PathVariable String id) {
        logger.info("Fetching refund with ID: {}", id);
        try {
            RefundDTO refund = refundService.getSelectedRefund(id);
            return ResponseEntity.ok(refund);
        } catch (Exception exception) {
            logger.error("Error fetching refund by ID: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch refund.\nMore Details\n" + exception);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveRefund(@Validated @RequestBody RefundDTO refundDTO, BindingResult bindingResult) {
        logger.info("Saving refund details");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            RefundDTO savedRefund = refundService.saveRefund(refundDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRefund);
        } catch (Exception exception) {
            logger.error("Error saving refund: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Refund saved unsuccessfully.\nMore Details\n" + exception);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRefund(@PathVariable String id, @Validated @RequestBody RefundDTO refundDTO, BindingResult bindingResult) {
        logger.info("Updating refund with ID: {}", id);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            refundService.updateRefund(id, refundDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            logger.error("Error updating refund: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Refund update unsuccessful.\nMore Details\n" + exception);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRefund(@PathVariable String id) {
        logger.info("Deleting refund with ID: {}", id);
        try {
            refundService.deleteRefund(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            logger.error("Error deleting refund: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to delete refund.\nMore Details\n" + exception);
        }
    }
}
