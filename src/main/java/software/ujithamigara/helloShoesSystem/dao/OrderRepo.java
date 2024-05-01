package software.ujithamigara.helloShoesSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import software.ujithamigara.helloShoesSystem.entity.OrderEntity;

public interface OrderRepo extends JpaRepository<OrderEntity, String> {
}
