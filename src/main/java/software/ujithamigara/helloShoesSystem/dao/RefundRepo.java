package software.ujithamigara.helloShoesSystem.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import software.ujithamigara.helloShoesSystem.entity.RefundEntity;

@Repository
public interface RefundRepo extends JpaRepository<RefundEntity,String> {
}
