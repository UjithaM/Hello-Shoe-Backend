package software.ujithamigara.helloShoesSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import software.ujithamigara.helloShoesSystem.entity.enums.AccessoriesVerities;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "accessories")
@Entity
public class AccessoriesEntity implements SuperEntity {
    @Id
    private String accessoriesCode;
    private String accessoriesDescription;
    @Column(columnDefinition = "LONGTEXT")
    private String accessoriesPicture;
    private double unitPriceSell;
    private double unitPriceBuy;
    private double expectedProfit;
    private double profitMargin;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private AccessoriesVerities accessoriesVerities;
    @ManyToOne
    @JoinColumn(name = "supplierCode", referencedColumnName = "supplierCode")
    @JoinColumn(name = "name", referencedColumnName = "name")
    private SupplierEntity supplierEntity;
    @ToString.Exclude
    @OneToMany (mappedBy = "accessoriesEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderAccessoriesEntity> orderAccessories;
}
