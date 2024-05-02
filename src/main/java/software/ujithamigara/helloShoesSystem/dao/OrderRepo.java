package software.ujithamigara.helloShoesSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import software.ujithamigara.helloShoesSystem.entity.OrderEntity;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, String> {
}
