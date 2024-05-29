package software.ujithamigara.helloShoesSystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.ujithamigara.helloShoesSystem.entity.OrderAccessories;
import software.ujithamigara.helloShoesSystem.entity.Order_item;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private String orderNo;

    @NotBlank(message = "Purchase date cannot be blank")
    private Date purchaseDate;

    @NotBlank(message = "Payment method cannot be blank")
    private String paymentMethod;

    @PositiveOrZero(message = "Total price must be positive or zero")
    private double totalPrice;


    private List<Order_item> orderItems;

    private List<OrderAccessories> orderAccessories;

    @NotNull(message = "customerCode cannot be null")
    private String customerCode;

    @NotNull(message = "employeeCode cannot be null")
    private String employeeCode;

    @NotNull(message = "refundCode cannot be null")
    private String refundCode;
}