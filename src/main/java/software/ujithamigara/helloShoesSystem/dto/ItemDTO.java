package software.ujithamigara.helloShoesSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
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
    private SupplierDTO supplierDTO;
    private List<Order_itemDTO> orderItemDTO;
}
