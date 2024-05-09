package software.ujithamigara.helloShoesSystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.ujithamigara.helloShoesSystem.entity.OrderEntity;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RefundDTO {
    @Null(message = "Refund id generate by the programme")
    private String refundId;

    @NotBlank(message = "Refund reason cannot be blank")
    private String refundReason;

    @PastOrPresent(message = "Refund date must be in the past or present")
    @NotNull(message = "Refund date cannot be null")
    private Date refundDate;

    @NotNull(message = "Order cannot be null")
    private OrderEntity order;

    @NotNull(message = "Employee cannot be null")
    private EmployeeDTO employeeDTO;
}