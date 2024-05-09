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

    @Min(value = 1, message = "Item quantity must be greater than 0")
    private int itemQuantity;

    @NotEmpty(message = "Order item list cannot be empty")
    private List<Order_itemDTO> orderItemDTOS;

    @NotNull(message = "Customer cannot be null")
    private CustomerDTO customerDTO;

    @NotNull(message = "Employee cannot be null")
    private EmployeeDTO EmployeeDTO;

    @NotNull(message = "Refund cannot be null")
    private RefundDTO RefundDTO;
}