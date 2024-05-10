package software.ujithamigara.helloShoesSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import software.ujithamigara.helloShoesSystem.entity.AccessoriesEntity;

@Repository
public interface AccessoriesRepo extends JpaRepository<AccessoriesEntity, String> {
}
