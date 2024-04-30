package software.ujithamigara.helloShoesSystem.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "order_item")
@Entity
public class Order_item implements SuperEntity{
    @Id
    @ManyToOne
    @JoinColumn(name = "orderNo", referencedColumnName = "orderNo")
    private OrderEntity orders;

    @Id
    @ManyToOne
    @JoinColumn(name = "itemCode", referencedColumnName = "itemCode")
    private ItemEntity item;

    private int quantity;
}
