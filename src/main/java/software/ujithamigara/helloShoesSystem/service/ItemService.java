package software.ujithamigara.helloShoesSystem.service;


import software.ujithamigara.helloShoesSystem.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO saveItem(ItemDTO itemDTO);
    void deleteItem(String itemId);
    ItemDTO getSelectedItem(String itemId);
    List<ItemDTO> getAllItem();
    void updateItem(String itemId,ItemDTO itemDTO);
}
