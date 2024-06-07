package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.EmployeeRepo;
import software.ujithamigara.helloShoesSystem.dao.OrderRepo;
import software.ujithamigara.helloShoesSystem.dao.RefundRepo;
import software.ujithamigara.helloShoesSystem.dto.RefundDTO;
import software.ujithamigara.helloShoesSystem.entity.CustomerEntity;
import software.ujithamigara.helloShoesSystem.entity.RefundEntity;
import software.ujithamigara.helloShoesSystem.exception.NotFoundException;
import software.ujithamigara.helloShoesSystem.service.RefundServices;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RefundServicesIMPL implements RefundServices {

    private static final Logger logger = LoggerFactory.getLogger(RefundServicesIMPL.class);

    private final RefundRepo refundRepo;
    private final EmployeeRepo employeeRepo;
    private final OrderRepo orderRepo;
    private final Mapping mapping;

    @Override
    public RefundDTO saveRefund(RefundDTO refundDTO) {
        long refundCount = refundRepo.count();
        String refCode = String.format("R%04d", refundCount + 1);
        refundDTO.setRefundId(refCode);

        RefundEntity refundEntity = mapping.toRefundEntity(refundDTO);

        refundEntity.setEmployeeEntity(employeeRepo.findById(refundDTO.getEmployeeCode())
                .orElseThrow(() -> new NotFoundException("Employee not found")));

        refundEntity.setOrder(orderRepo.findById(refundDTO.getOrderId())
                .orElseThrow(() -> new NotFoundException("Order not found")));


        return mapping.toRefundDTO(refundRepo.save(refundEntity));
    }

    @Override
    public void deleteRefund(String id) {
        if (refundRepo.existsById(id)) {
            refundRepo.deleteById(id);
            logger.info("Refund deleted: {}", id);
        } else {
            logger.warn("Refund not found: {}", id);
            throw new NotFoundException("Refund not found with ID: " + id);
        }
    }

    @Override
    public RefundDTO getSelectedRefund(String id) {
        return refundRepo.findById(id)
                .map(mapping::toRefundDTO)
                .orElseThrow(() -> {
                    logger.warn("Refund not found: {}", id);
                    return new NotFoundException("Refund not found with ID: " + id);
                });
    }

    @Override
    public List<RefundDTO> getAllRefund() {
        return mapping.toRefundDTOList(refundRepo.findAll());
    }

    @Override
    public void updateRefund(String id, RefundDTO refundDTO) {
        if (refundRepo.existsById(id)) {
            refundRepo.save(mapping.toRefundEntity(refundDTO));
            logger.info("Refund updated: {}", id);
        } else {
            logger.warn("Refund not found: {}", id);
            throw new NotFoundException("Refund not found with ID: " + id);
        }
    }
}
