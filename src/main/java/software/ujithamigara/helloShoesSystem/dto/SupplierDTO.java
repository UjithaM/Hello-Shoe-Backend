package software.ujithamigara.helloShoesSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.ujithamigara.helloShoesSystem.entity.enums.Category;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDTO {
    private String supplierCode;
    private String name;
    private Category category;
    private String addressLine01;
    private String addressLine02;
    private String addressLine03;
    private String addressLine04;
    private String addressLine05;
    private String addressLine06;
    private String contactNumber1;
    private String contactNumber2;
    private String email;
}
