package software.ujithamigara.helloShoesSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import software.ujithamigara.helloShoesSystem.entity.ItemEntity;

public interface ItemRepo extends JpaRepository<ItemEntity, String> {
}
