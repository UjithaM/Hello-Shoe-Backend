package software.ujithamigara.helloShoesSystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order_itemDTO {
    @NotNull(message = "Order cannot be null")
    private OrderDTO orderDTO;

    @NotNull(message = "Item cannot be null")
    private ItemDTO itemDTO;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;
}