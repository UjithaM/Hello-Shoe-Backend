package software.ujithamigara.helloShoesSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import software.ujithamigara.helloShoesSystem.entity.SupplierEntity;
@Repository
public interface SupplierRepo extends JpaRepository<SupplierEntity,String> {
}
