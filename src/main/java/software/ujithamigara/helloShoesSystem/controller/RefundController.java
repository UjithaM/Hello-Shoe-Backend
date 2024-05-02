package software.ujithamigara.helloShoesSystem.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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
    public List<RefundDTO> getAllRefund() {
        logger.info("Fetching all refunds");
        return refundService.getAllRefund();
    }

    @GetMapping("/{id}")
    public RefundDTO getRefundById(@PathVariable String id) {
        logger.info("Fetching refund with ID: {}", id);
        return refundService.getSelectedRefund(id);
    }

    @PostMapping
    public RefundDTO saveRefund(@RequestBody RefundDTO refundDTO) {
        logger.info("Saving refund: {}", refundDTO);
        return refundService.saveRefund(refundDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRefund(@PathVariable String id, @RequestBody RefundDTO refundDTO) {
        logger.info("Updating refund with ID: {}", id);
        refundService.updateRefund(id, refundDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRefund(@PathVariable String id) {
        logger.info("Deleting refund with ID: {}", id);
        refundService.deleteRefund(id);
        return ResponseEntity.ok().build();
    }
}
