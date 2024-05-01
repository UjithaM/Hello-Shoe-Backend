package software.ujithamigara.helloShoesSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order_itemDTO {
    private OrderDTO orderDTO;
    private ItemDTO itemDTO;
    private int quantity;
}
