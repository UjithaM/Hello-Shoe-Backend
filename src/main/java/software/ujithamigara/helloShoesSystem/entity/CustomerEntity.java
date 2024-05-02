package software.ujithamigara.helloShoesSystem.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.ujithamigara.helloShoesSystem.entity.enums.Gender;
import software.ujithamigara.helloShoesSystem.entity.enums.Level;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "customer")
@Entity
public class CustomerEntity implements SuperEntity {
    @Id
    private String customerCode;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date joinDate;
    @Enumerated(EnumType.STRING)
    private Level level;
    private int totalPoints;
    private Date dob;
    private String addressNo;
    private String lane;
    private String mainCity;
    private String mainState;
    private String postalCode;
    private String contactNumber;
    @Column(unique = true)
    private String email;
    private Timestamp recentPurchaseDate;
    @OneToMany(mappedBy = "customerEntity")
    private List<OrderEntity> orderEntities;

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "customerCode='" + customerCode + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", joinDate=" + joinDate +
                ", level=" + level +
                ", totalPoints=" + totalPoints +
                ", dob=" + dob +
                ", addressNo='" + addressNo + '\'' +
                ", lane='" + lane + '\'' +
                ", mainCity='" + mainCity + '\'' +
                ", mainState='" + mainState + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", recentPurchaseDate=" + recentPurchaseDate +
                '}';
    }

}
