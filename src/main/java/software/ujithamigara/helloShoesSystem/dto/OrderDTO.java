package software.ujithamigara.helloShoesSystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private String orderNo;

    @NotNull(message = "Purchase date cannot be blank")
    private Date purchaseDate;

    @NotBlank(message = "Payment method cannot be blank")
    private String paymentMethod;

    @PositiveOrZero(message = "Total price must be positive or zero")
    private double totalPrice;

    private List<OrderItemDTO> orderItems;

    private List<OrderAccessoriesDTO> orderAccessories;

    @NotNull(message = "customerCode cannot be null")
    private String customerCode;

    @NotNull(message = "employeeCode cannot be null")
    private String employeeCode;
}