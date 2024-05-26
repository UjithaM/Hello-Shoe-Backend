package software.ujithamigara.helloShoesSystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    @Null(message = "Order id generate by the programme")
    private String orderNo;

    @NotBlank(message = "Purchase date cannot be blank")
    private String purchaseDate;

    @NotBlank(message = "Payment method cannot be blank")
    private String paymentMethod;

    @PositiveOrZero(message = "Total price must be positive or zero")
    private double totalPrice;


    @NotEmpty(message = "Order item list cannot be empty")
    private List<Order_itemDTO> orderItemDTOS;

    @NotNull(message = "customerCode cannot be null")
    private String customerCode;

    @NotNull(message = "employeeCode cannot be null")
    private String employeeCode;

    @NotNull(message = "refundCode cannot be null")
    private String refundCode;
}