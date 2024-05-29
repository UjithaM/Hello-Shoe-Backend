package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.ItemRepo;
import software.ujithamigara.helloShoesSystem.dao.SupplierRepo;
import software.ujithamigara.helloShoesSystem.dto.ItemDTO;
import software.ujithamigara.helloShoesSystem.entity.ItemEntity;
import software.ujithamigara.helloShoesSystem.entity.SupplierEntity;
import software.ujithamigara.helloShoesSystem.entity.enums.Gender;
import software.ujithamigara.helloShoesSystem.entity.enums.Occasion;
import software.ujithamigara.helloShoesSystem.entity.enums.Verities;
import software.ujithamigara.helloShoesSystem.exception.NotFoundException;
import software.ujithamigara.helloShoesSystem.service.ItemService;
import software.ujithamigara.helloShoesSystem.util.ItemCodeGenerator;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceIMPL implements ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemServiceIMPL.class);

    private final ItemRepo repo;
    private final Mapping mapping;
    private final SupplierRepo supplierRepo;

    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) {
        itemDTO.setItemCode(ItemCodeGenerator.generateItemCode(itemDTO.getGender(), itemDTO.getOccasion(), itemDTO.getVerities(), itemDTO.getSize(), repo.count() + 1));
        SupplierEntity supplierEntity = supplierRepo.findById(itemDTO.getSupplierCode())
                .orElseThrow(() -> {
                    logger.warn("Supplier not found: {}", itemDTO.getSupplierCode());
                    return new NotFoundException("Supplier not found with ID: " + itemDTO.getSupplierCode());
                });
        ItemEntity itemEntity = mapping.toItemEntity(itemDTO);
        itemEntity.setSupplierEntity(supplierEntity);
        ItemDTO savedItemDTO = mapping.toItemDTO(repo.save(itemEntity));
        logger.info("Item saved: {}", savedItemDTO);
        return savedItemDTO;
    }

    @Override
    public void deleteItem(String itemId) {
        if (repo.existsById(itemId)) {
            repo.deleteById(itemId);
            logger.info("Item deleted: {}", itemId);
        } else {
            logger.warn("Item not found: {}", itemId);
            throw new NotFoundException("Item not found with ID: " + itemId);
        }
    }

    @Override
    public ItemDTO getSelectedItem(String itemId) {
        ItemDTO itemDTO = repo.findById(itemId)
                .map(mapping::toItemDTO)
                .orElseThrow(() -> {
                    logger.warn("Item not found: {}", itemId);
                    return new NotFoundException("Item not found with ID: " + itemId);
                });
        logger.info("Item retrieved: {}", itemDTO);
        return itemDTO;
    }

    @Override
    public List<ItemDTO> getAllItem() {
        List<ItemDTO> items = mapping.toItemDTOList(repo.findAll());
        logger.info("All items retrieved: {}", items.size());
        return items;
    }

    @Override
    public void updateItem(String itemId, ItemDTO itemDTO) {
        ItemEntity itemEntity = repo.findById(itemId)
                .orElseThrow(() -> {
                    logger.warn("Item not found: {}", itemId);
                    return new NotFoundException("Item not found with ID: " + itemId);
                });
        itemEntity.setSupplierEntity(supplierRepo.findById(itemDTO.getSupplierCode())
                .orElseThrow(() -> {
                    logger.warn("Supplier not found: {}", itemDTO.getSupplierCode());
                    return new NotFoundException("Supplier not found with ID: " + itemDTO.getSupplierCode());
                }));
        itemEntity.setItemDescription(itemDTO.getItemDescription());
        itemEntity.setItemPicture(itemDTO.getItemPicture());
        itemEntity.setItemCategory(itemDTO.getItemCategory());
        itemEntity.setSize(itemDTO.getSize());
        itemEntity.setUnitPriceSell(itemDTO.getUnitPriceSell());
        itemEntity.setUnitPriceBuy(itemDTO.getUnitPriceBuy());
        itemEntity.setExpectedProfit(itemDTO.getExpectedProfit());
        itemEntity.setProfitMargin(itemDTO.getProfitMargin());
        itemEntity.setQuantity(itemDTO.getQuantity());
        itemEntity.setItemStatus(itemDTO.getItemStatus());
        itemEntity.setOccasion(itemDTO.getOccasion());
        itemEntity.setVerities(itemDTO.getVerities());
        itemEntity.setGender(itemDTO.getGender());
        itemEntity.setOrderItems(itemEntity.getOrderItems());
        repo.save(itemEntity);
        logger.info("Item updated: {}", itemEntity);
    }
}
