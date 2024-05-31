package software.ujithamigara.helloShoesSystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import software.ujithamigara.helloShoesSystem.entity.enums.Gender;
import software.ujithamigara.helloShoesSystem.entity.enums.Role;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private String employeeCode;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @ToString.Exclude
    private String profilePicture;

    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    @NotBlank(message = "Civil status cannot be blank")
    private String civilStatus;

    @NotBlank(message = "Designation cannot be blank")
    private String designation;

    @NotNull(message = "Role cannot be null")
    private Role role;

    @Past(message = "Date of birth should be in the past")
    @NotNull(message = "Date of birth cannot be null")
    private Date dob;

    @PastOrPresent(message = "Join date must be in the past or present")
    @NotNull(message = "Joined date cannot be null")
    private Date joinedDate;

    @NotBlank(message = "Attached branch cannot be blank")
    private String attachedBranch;

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

    @NotBlank(message = "Contact number cannot be blank")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Invalid contact number format")
    private String contactNumber;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Guardian name cannot be blank")
    private String guardianName;

    @NotBlank(message = "Guardian contact cannot be blank")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Invalid contact number format")
    private String guardianContact;
}