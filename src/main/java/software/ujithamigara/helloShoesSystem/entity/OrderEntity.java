package software.ujithamigara.helloShoesSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
@Entity
public class OrderEntity implements SuperEntity{
    @Id
    private String orderNo;
    private Date purchaseDate;
    private String paymentMethod;
    private double totalPrice;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Order_item> orderItems;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderAccessories> orderAccessories;
    @ManyToOne
    @JoinColumn(name = "customerCode", referencedColumnName = "customerCode")
    private CustomerEntity customerEntity;
    @ManyToOne
    @JoinColumn(name = "employeeCode", referencedColumnName = "employeeCode")
    private EmployeeEntity employeeEntity;
    @OneToOne(mappedBy = "order")
    private RefundEntity refundEntity;
}
