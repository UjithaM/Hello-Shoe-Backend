package software.ujithamigara.helloShoesSystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.ujithamigara.helloShoesSystem.entity.enums.Category;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDTO {
   @Null(message = "Supplier id generate by the programme")
    private String supplierCode;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Category cannot be null")
    private Category category;

    @NotBlank(message = "Address no cannot be blank")
    private String addressNo;

    @NotBlank(message = "Lane cannot be blank")
    private String lane;

    @NotBlank(message = "Main city cannot be blank")
    private String mainCity;

    @NotBlank(message = "Main state cannot be blank")
    private String mainState;

    @NotBlank(message = "Postal code cannot be blank")
    private String postalCode;

    @NotBlank(message = "Country cannot be blank")
    private String country;

    @NotBlank(message = "Mobile number cannot be blank")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Invalid mobile number format")
    private String mobileNumber;

    @NotBlank(message = "Landline number cannot be blank")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Invalid landline number format")
    private String landlineNumber;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;
}