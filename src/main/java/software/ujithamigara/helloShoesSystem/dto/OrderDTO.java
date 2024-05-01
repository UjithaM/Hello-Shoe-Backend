package software.ujithamigara.helloShoesSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private CustomerDTO customerDTO;
    private EmployeeDTO employeeDTO;
    private RefundDTO refundDTO;
}
