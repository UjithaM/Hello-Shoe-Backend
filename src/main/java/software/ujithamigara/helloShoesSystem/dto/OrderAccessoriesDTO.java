package software.ujithamigara.helloShoesSystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderAccessoriesDTO {
    @NotNull(message = "Item cannot be null")
    private String accessoriesCode;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;
}
