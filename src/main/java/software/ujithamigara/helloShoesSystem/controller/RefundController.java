package software.ujithamigara.helloShoesSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.ujithamigara.helloShoesSystem.dto.RefundDTO;
import software.ujithamigara.helloShoesSystem.service.RefundServices;

import java.util.List;

@RestController
@RequestMapping("/api/v1/refund")
@RequiredArgsConstructor
public class RefundController {
    public final RefundServices refundService;

    @GetMapping("/health")
    public String healthTest(){
        return "Refund Health Test";
    }

    @GetMapping
    public List<RefundDTO> getAllRefund() {
        return refundService.getAllRefund();
    }

    @GetMapping("/{id}")
    public RefundDTO getRefundById(@PathVariable String id) {
        return refundService.getSelectedRefund(id);
    }

    @PostMapping
    public RefundDTO saveRefund(@RequestBody RefundDTO refundDTO) {
        return refundService.saveRefund(refundDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRefund(@PathVariable String id, @RequestBody RefundDTO refundDTO) {
        refundService.updateRefund(id, refundDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRefund(@PathVariable String id) {
        refundService.deleteRefund(id);
        return ResponseEntity.ok().build();
    }
}
