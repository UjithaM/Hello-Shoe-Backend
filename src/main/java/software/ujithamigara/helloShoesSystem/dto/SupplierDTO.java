package software.ujithamigara.helloShoesSystem.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.ujithamigara.helloShoesSystem.entity.ItemEntity;
import software.ujithamigara.helloShoesSystem.entity.enums.Category;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDTO {
    private String supplierCode;
    private String name;
    private Category category;
    private String addressNo;
    private String lane;
    private String mainCity;
    private String mainState;
    private String postalCode;
    private String country;
    private String mobileNumber;
    private String landlineNumber;
    private String email;
}
