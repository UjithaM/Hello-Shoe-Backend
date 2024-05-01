package software.ujithamigara.helloShoesSystem.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.ujithamigara.helloShoesSystem.entity.OrderEntity;
import software.ujithamigara.helloShoesSystem.entity.enums.Gender;
import software.ujithamigara.helloShoesSystem.entity.enums.Level;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private String customerCode;
    private String name;
    private Gender gender;
    private Date jionDate;
    private Level level;
    private int totalPoints;
    private Date dob;
    private String addressNo;
    private String lane;
    private String mainCity;
    private String mainState;
    private String postalCode;
    private String contactNumber;
    private String email;
    private Timestamp recentPurchaseDate;
    private List<OrderDTO> orderDTOS;
}
