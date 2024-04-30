package software.ujithamigara.helloShoesSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
@Entity
public class OrderEntity implements SuperEntity{
    @Id
    private String orderNo;
    private String purchaseDate;
    private String paymentMethod;
    private double totalPrice;
    private int itemQuantity;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Order_item> orderItems;
    @ManyToOne
    @JoinColumn(name = "customerCode", referencedColumnName = "customerCode")
    private CustomerEntity customerEntity;
    @ManyToOne
    @JoinColumn(name = "employeeCode", referencedColumnName = "employeeCode")
    private EmployeeEntity employeeEntity;
    @OneToOne(mappedBy = "order")
    private RefundEntity refundEntity;
}
