package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.RefundRepo;
import software.ujithamigara.helloShoesSystem.dto.RefundDTO;
import software.ujithamigara.helloShoesSystem.service.RefundServices;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RefundServicesIMPL implements RefundServices {
    private final RefundRepo refundRepo;
    private final Mapping mapping;

    @Override
    public RefundDTO saveRefund(RefundDTO refundDTO) {
        return mapping.toRefundDTO(refundRepo.save(mapping.toRefundEntity(refundDTO)));
    }

    @Override
    public void deleteRefund(String id) {
        refundRepo.deleteById(id);
    }

    @Override
    public RefundDTO getSelectedRefund(String id) {
        return mapping.toRefundDTO(refundRepo.getReferenceById(id));
    }

    @Override
    public List<RefundDTO> getAllRefund() {
        return mapping.toRefundDTOList(refundRepo.findAll());
    }

    @Override
    public void updateRefund(String id, RefundDTO refundDTO) {
        refundRepo.save(mapping.toRefundEntity(refundDTO));
    }
}
