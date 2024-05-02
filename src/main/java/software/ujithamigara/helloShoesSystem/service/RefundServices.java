package software.ujithamigara.helloShoesSystem.service;

import software.ujithamigara.helloShoesSystem.dto.RefundDTO;
import software.ujithamigara.helloShoesSystem.dto.RefundDTO;

import java.util.List;

public interface RefundServices {
    RefundDTO saveRefund(RefundDTO refundDTO);
    void deleteRefund(String id);
    RefundDTO getSelectedRefund(String id);
    List<RefundDTO> getAllRefund();
    void updateRefund(String id,RefundDTO refundDTO);
}
