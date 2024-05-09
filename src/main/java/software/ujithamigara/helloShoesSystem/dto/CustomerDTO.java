package software.ujithamigara.helloShoesSystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.ujithamigara.helloShoesSystem.entity.enums.Gender;
import software.ujithamigara.helloShoesSystem.entity.enums.Level;

import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    @Null(message = "Customer id generate by the programme")
    private String customerCode;
    @NotBlank(message = "customer name cannot be blank")
    @Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 characters")
    private String name;
    @NotNull(message = "Gender cannot be a null")
    private Gender gender;
    @NotNull(message = "Joined date cannot be a null")
    @PastOrPresent(message = "Join date must be in the past or present")
    private Date joinDate;
    private Level level;
    @PositiveOrZero(message = "Total points must be positive or zero")
    private int totalPoints;
    @Past(message = "Date of birth should be in the past")
    @NotNull(message = "Date of birth cannot be null")
    private Date dob;
    @NotBlank(message = "Address no cannot be blank")
    private String addressNo;
    @NotNull(message = "Street cannot be null")
    private String lane;
    @NotNull(message = "City cannot be null")
    private String mainCity;
    @NotNull(message = "State cannot be null")
    private String mainState;
    @NotNull(message = "Postal code cannot be null")
    private String postalCode;
    @NotBlank(message = "Contact No cannot be blank")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Invalid contact number format")
    private String contactNumber;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;
    @NotNull(message = "Recent purchased date cannot be null")
    private Timestamp recentPurchaseDate;
}
