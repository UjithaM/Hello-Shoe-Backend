package software.ujithamigara.helloShoesSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.ujithamigara.helloShoesSystem.entity.OrderEntity;
import software.ujithamigara.helloShoesSystem.entity.RefundEntity;
import software.ujithamigara.helloShoesSystem.entity.enums.Gender;
import software.ujithamigara.helloShoesSystem.entity.enums.Role;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private String employeeCode;
    private String name;
    private String profilePicture;
    private Gender gender;
    private String civilStatus;
    private String Designation;
    private Role role;
    private Date dob;
    private Date joinedDate;
    private String attachedBranch;
    private String addressNo;
    private String lane;
    private String mainCity;
    private String mainState;
    private String postalCode;
    private String contactNumber;
    private String email;
    private String guardianName;
    private String guardianContact;
}
