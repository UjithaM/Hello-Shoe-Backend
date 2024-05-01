package software.ujithamigara.helloShoesSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.ujithamigara.helloShoesSystem.entity.OrderEntity;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RefundDTO {
    private String refundId;
    private String refundReason;
    private Date refundDate;
    private OrderEntity order;
    private EmployeeDTO employeeDTO;
}
