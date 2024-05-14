package software.ujithamigara.helloShoesSystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.ujithamigara.helloShoesSystem.entity.SupplierEntity;
import software.ujithamigara.helloShoesSystem.entity.enums.AccessoriesVerities;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccessoriesDTO {
    @Null(message = "Accessories id generate by the programme")
    private String accessoriesCode;

    @NotBlank(message = "Accessories description cannot be blank")
    private String accessoriesDescription;

    @NotBlank(message = "Accessories picture cannot be blank")
    private String accessoriesPicture;

    @Positive(message = "Unit price sell must be positive")
    private double unitPriceSell;

    @Positive(message = "Unit price buy must be positive")
    private double unitPriceBuy;

    @PositiveOrZero(message = "Expected profit must be positive or zero")
    private double expectedProfit;

    @PositiveOrZero(message = "Profit margin must be positive or zero")
    private double profitMargin;

    @Min(value = 0, message = "Quantity must be zero or greater")
    private int quantity;

    @NotNull(message = "Accessories verities cannot be null")
    private AccessoriesVerities accessoriesVerities;

    @NotNull(message = "Supplier supplierCode cannot be null")
    private String supplierCode;

    @NotNull(message = "Supplier name cannot be null")
    private String supplierName;
}