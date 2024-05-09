package software.ujithamigara.helloShoesSystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    @Null(message = "Item id generate by the programme")
    private String itemCode;

    @NotBlank(message = "Item description cannot be blank")
    private String itemDescription;

    private String itemPicture;

    @NotBlank(message = "Item category cannot be blank")
    private String itemCategory;

    @Min(value = 1, message = "Size must be greater than 0")
    private int size;

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

    @NotBlank(message = "Item status cannot be blank")
    private String itemStatus;

    @NotNull(message = "Supplier cannot be null")
    private SupplierDTO supplierDTO;

    @NotEmpty(message = "Order item list cannot be empty")
    private List<Order_itemDTO> orderItemDTO;
}