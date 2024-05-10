package software.ujithamigara.helloShoesSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.ujithamigara.helloShoesSystem.entity.enums.AccessoriesVerities;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "accessories")
@Entity
public class AccessoriesEntity {
    @Id
    private String accessoriesCode;
    private String accessoriesDescription;
    private String accessoriesPicture;
    private double unitPriceSell;
    private double unitPriceBuy;
    private double expectedProfit;
    private double profitMargin;
    private int quantity;
    private AccessoriesVerities accessoriesVerities;
    @ManyToOne
    @JoinColumn(name = "supplierCode", referencedColumnName = "supplierCode")
    @JoinColumn(name = "name", referencedColumnName = "name")
    private SupplierEntity supplierEntity;
    @OneToMany (mappedBy = "accessoriesEntity", cascade = CascadeType.ALL)
    private List<OrderAccessories> orderAccessories;
}
