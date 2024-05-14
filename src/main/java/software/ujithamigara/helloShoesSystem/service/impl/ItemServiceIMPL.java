package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.ItemRepo;
import software.ujithamigara.helloShoesSystem.dto.ItemDTO;
import software.ujithamigara.helloShoesSystem.service.ItemService;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceIMPL implements ItemService {
    private final ItemRepo repo;
    private final Mapping mapping;
    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) {
        itemDTO.setItemCode(UUID.randomUUID().toString());
        return mapping.toItemDTO(repo.save(mapping.toItemEntity(itemDTO)));
    }

    @Override
    public void deleteItem(String itemId) {
        repo.delete(repo.findById(itemId).get());
    }

    @Override
    public ItemDTO getSelectedItem(String itemId) {
        return mapping.toItemDTO(repo.findById(itemId).get());
    }

    @Override
    public List<ItemDTO> getAllItem() {
        return mapping.toItemDTOList(repo.findAll());
    }

    @Override
    public void updateItem(String itemId, ItemDTO itemDTO) {
        repo.save(mapping.toItemEntity(itemDTO));
    }
}
