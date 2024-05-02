package software.ujithamigara.helloShoesSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.ujithamigara.helloShoesSystem.entity.CustomerEntity;
import software.ujithamigara.helloShoesSystem.entity.EmployeeEntity;
import software.ujithamigara.helloShoesSystem.entity.RefundEntity;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private String orderNo;
    private String purchaseDate;
    private String paymentMethod;
    private double totalPrice;
    private int itemQuantity;
    private List<Order_itemDTO> orderItemDTOS;
    private CustomerEntity customerEntity;
    private EmployeeEntity employeeEntity;
    private RefundEntity refundDTOEntity;
}
