package software.ujithamigara.helloShoesSystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDTO {

    @NotNull(message = "Item cannot be null")
    private String itemCode;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;
}