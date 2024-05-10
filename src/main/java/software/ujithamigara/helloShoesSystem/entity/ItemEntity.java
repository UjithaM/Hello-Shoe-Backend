package software.ujithamigara.helloShoesSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.ujithamigara.helloShoesSystem.entity.enums.Occasion;
import software.ujithamigara.helloShoesSystem.entity.enums.Verities;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "item")
@Entity
public class ItemEntity implements SuperEntity{
    @Id
    private String itemCode;
    private String itemDescription;
    private String itemPicture;
    private String itemCategory;
    private int size;
    private double unitPriceSell;
    private double unitPriceBuy;
    private double expectedProfit;
    private double profitMargin;
    private int quantity;
    private String itemStatus;
    @Enumerated(EnumType.STRING)
    private Occasion occasion;
    @Enumerated(EnumType.STRING)
    private Verities verities;
    @ManyToOne
    @JoinColumn(name = "supplierCode", referencedColumnName = "supplierCode")
    @JoinColumn(name = "name", referencedColumnName = "name")
    private SupplierEntity supplierEntity;
    @OneToMany (mappedBy = "item", cascade = CascadeType.ALL)
    private List<Order_item> orderItems;
}
