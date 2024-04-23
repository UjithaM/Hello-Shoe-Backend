package software.ujithamigara.helloShoesSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.ujithamigara.helloShoesSystem.entity.enums.Category;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "supplier")
@Entity
public class SupplierEntity {
    @Id
    private String supplierCode;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String addressLine01;
    private String addressLine02;
    private String addressLine03;
    private String addressLine04;
    private String addressLine05;
    private String addressLine06;
    private String contactNumber1;
    private String contactNumber2;
    @Column(unique = true)
    private String email;
}
