package software.ujithamigara.helloShoesSystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.ujithamigara.helloShoesSystem.entity.OrderItemEntity;
import software.ujithamigara.helloShoesSystem.entity.enums.Gender;
import software.ujithamigara.helloShoesSystem.entity.enums.Occasion;
import software.ujithamigara.helloShoesSystem.entity.enums.Verities;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    private String itemCode;

    @NotBlank(message = "Item description cannot be blank")
    private String itemDescription;

    private String itemPicture;

    @NotBlank(message = "Item category cannot be blank")
    private String itemCategory;

    @Positive(message = "Size must be positive")
    private int size;

    @Positive(message = "Unit price sell must be positive")
    private double unitPriceSell;

    @Positive(message = "Unit price buy must be positive")
    private double unitPriceBuy;

    @PositiveOrZero(message = "Expected profit must be positive or zero")
    private double expectedProfit;

    @PositiveOrZero(message = "Profit margin must be positive or zero")
    private double profitMargin;

    @PositiveOrZero(message = "Quantity must be positive or zero")
    private int quantity;

    @NotBlank(message = "Item status cannot be blank")
    private String itemStatus;

    @NotNull(message = "Occasion cannot be null")
    private Occasion occasion;

    @NotNull(message = "Verities cannot be null")
    private Verities verities;

    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    @NotNull(message = "Supplier entity cannot be null")
    private String supplierCode;

}
