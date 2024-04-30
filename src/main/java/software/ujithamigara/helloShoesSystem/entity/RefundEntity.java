package software.ujithamigara.helloShoesSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "refund")
@Entity
public class RefundEntity implements SuperEntity{
    @Id
    private String refundId;
    private String refundReason;
    private Date refundDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderNo", referencedColumnName = "orderNo")
    private OrderEntity order;
    @ManyToOne
    @JoinColumn(name = "employeeCode", referencedColumnName = "employeeCode")
    private EmployeeEntity employeeEntity;
}
