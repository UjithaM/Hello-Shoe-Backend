package software.ujithamigara.helloShoesSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import software.ujithamigara.helloShoesSystem.entity.ItemEntity;

@Repository
public interface ItemRepo extends JpaRepository<ItemEntity, String> {
}
