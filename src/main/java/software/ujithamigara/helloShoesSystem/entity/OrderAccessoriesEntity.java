package software.ujithamigara.helloShoesSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "order_accessories")
@Entity
public class OrderAccessoriesEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "orderNo", referencedColumnName = "orderNo")
    private OrderEntity orders;

    @Id
    @ManyToOne
    @JoinColumn(name = "accessoriesCode", referencedColumnName = "accessoriesCode")
    private AccessoriesEntity accessoriesEntity;

    private int quantity;
}
