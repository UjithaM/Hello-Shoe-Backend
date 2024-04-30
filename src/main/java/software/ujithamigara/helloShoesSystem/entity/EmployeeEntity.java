package software.ujithamigara.helloShoesSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.ujithamigara.helloShoesSystem.entity.enums.Gender;
import software.ujithamigara.helloShoesSystem.entity.enums.Role;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "employee")
@Entity
public class EmployeeEntity implements SuperEntity{
    @Id
    private String employeeCode;
    private String name;
    private String profilePicture;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String civilStatus;
    private String Designation;
    @Enumerated(EnumType.STRING)
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
    @Column(unique = true)
    private String email;
    private String guardianName;
    private String guardianContact;
    @OneToMany(mappedBy = "employeeEntity")
    private List<OrderEntity> orderEntities;

    @OneToMany(mappedBy = "employeeEntity")
    private List<RefundEntity> refundEntities;
}
